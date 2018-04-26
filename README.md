# openshift-microservices

This repository holds Spring Boot sample applications on OpenShift. The idea is to help quickly create and tear down projects to deliver small, quick and agile proof of concepts which are looking to be self-contained and might last a few days or weeks at most.

## Microservices Application
The microservice application is broken into two bounded contexts namely Customer Management and Payment Account Management and each of them holds services to manage customers and payment accounts.

These bounded contexts are autonomous and integrate with each other via messaging for a business process. For example, when a customer is created Customer Management raises a command to create a payment account. Payment Account Management consumes the command and creates a Payment Account for a given customer.

The composite service retrieves the data (customer data and payment account data) from both the bounded contexts, aggregates the same to deliver the same to the caller.

* customers-service - maintains the customer information. On creating a customer, it publishes an event (command event) to create a payment account for the given customer.
* payment-account-service - maintains the payment account information. It consumes the event for creating a payment account for a customer and creates an account for a given customer.
* customer-composite-service - calls customers and payment account services to aggregate the customer and payment account information
* Demo database - Hold the tables for customer and account information.
* Kafka - A messaging broker 

The end-to-end architecture of the application is shown below.

## Prerequisite for Deployment

### minishift
Before you try this example download and setup [minishift](https://docs.openshift.org/latest/minishift/index.html). Create a profile with parametersand start minishift.
```
minishift profile set msa-demo
minishift config set memory 8GB
minishift config set cpus 3
minishift config set image-caching true
minishift addon enable admin-user

minishift start
```

### Create OpenShift Project
Execute following command to create new project called "demo-project". 
```
oc login -u developer # login in to OpenShift with developer login
oc new-project demo-project # create new project
```

### CLI commands
Below are some handy commands to get a list of projects, PODs, services, routes.
```
 oc get projects
 oc get pods
 oc get svc
 oc get routes
```
## Deploying services 
OpenShift does not provide the capability for building and running Spring Boot applications out of the box. There are multiple ways for deploying a spring boot application, however we will use the first option.
* deploy microservice by applying yml files
* deploy microservice using fabric8 plugin

### Deploying backing services
Deploy MySQL database by executing following command on openshift CLI
```
$ oc new-app -e MYSQL_USER=demouser MYSQL_PASSWORD=password MYSQL_DATABASE=demodb registry.access.redhat.com/rhscl/mysql-56-rhel7 --name=demo-database -l demo-database=dbsvc
```
Deploy Kafka by executing following command on openshift CLI. We will be using yml files from this [GitHub repo](https://github.com/rondinif/openshift-kafka) to deploy Kafka.

```
# Load resources (Templates, DeploymentConfigs, Services)
oc create -f https://raw.githubusercontent.com/rondinif/openshift-kafka/master/resources.yaml

# Deploy the Apache Kafka + Apache Zookeeper pod
oc new-app apache-kafka

# Deploy a debugging container and connect to it
oc run -it --rm kafka-debug --image=rondinif/openshift-kafka --command -- bash
```

### Deploying Microservices
Compile and create application jar by executing maven command for all three microservices
```
mvn clean install
```
Create and push customer-service docker image to docker hub and apply yml files to create OpenShift objects 
```
docker build -t mrudul03/customer-service:v01 .
docker push mrudul03/customer-service:v01

oc apply -f customer-service-deployment.yml
oc apply -f customer-service.yml
oc expose svc customer-service
```
Create and push payment-account-service docker image to docker hub and apply yml files to create OpenShift objects
```
docker build -t mrudul03/payment-account-service:v01 .
docker push mrudul03/payment-account-service:v01

oc apply -f payment-account-deployment.yml
oc apply -f payment-account-service.yml
oc expose svc payment-account-service
```
Create and push customer-composite-service docker image to docker hub and apply yml files to create OpenShift objects
```
docker build -t mrudul03/customer-composite-service:v01 .
docker push mrudul03/customer-composite-service:v01

oc apply -f customer-composite-deployment.yml
oc apply -f customer-composite-service.yml
oc expose svc customer-composite-service
```

## Verify the deployment
Check if all the pods are successfully created and running.

oc get pods # should give below result
```
apache-kafka-1-24mv9                          2/2       Running   22         14d
customer-composite-service-2982933011-bhfct   1/1       Running   6          11d
customer-service-2191057949-kn7tv             0/1       Running   12         11d
demo-database-2-mnkjg                         1/1       Running   8          14d
kafka-debug-1-ff6pc                           1/1       Running   8          13d
payment-account-service-2074609440-m8xkj      0/1       Running   12         11d
```
oc get svc # should give below result
```
apache-kafka                 172.30.234.79    <none>        9092/TCP,2181/TCP   14d
customer-composite-service   172.30.77.189    <none>        8080/TCP            11d
customer-service             172.30.3.65      <none>        8080/TCP            11d
demo-database                172.30.62.13     <none>        3306/TCP            14d
payment-account-service      172.30.159.210   <none>        8080/TCP            11d
```
oc get routes # should give below result
```
customer-composite-service   customer-composite-service-demo-project.192.168.64.3.nip.io             customer-composite-service   http                    None
customer-service             customer-service-demo-project.192.168.64.3.nip.io                       customer-service             http                    None
payment-account-service      payment-account-service-demo-project.192.168.64.3.nip.io                payment-account-service      http                    None
```

## Running Microservices

### Create a customer
The hostname (customer-service-demo-project.192.168.64.3.nip.io) can be taken from route exposed for customer-service.
POST http://customer-service-demo-project.192.168.64.3.nip.io/customers/ 
```
{
  "firstName": "John",
  "lastName": "Doe",
  "addresses": [
      {
          "streetAddress": "Paradise",
          "state": "CA",
          "city": "Monaco",
          "areaCode": "3000",
          "addressType": "Home Address"
      }
  ],
  "contacts": [
      {
          "contactType": "mobile",
          "contactNumber": "12312312312"
      }
  ]
}
```

### Get Payment Account
Successful creation of customer record raises an event (CreatePaymentAccountCommand) on kafka. Payment Account Service consumes this event and creates a payment account.You can check the logs in openshift web console. At the same time, you can query Payment Account Service.
```
GET http://payment-account-service-demo-project.192.168.64.3.nip.io/accounts/
```

### POST Payment Account
POST http://payment-account-service-demo-project.192.168.64.3.nip.io/customers/1/accounts
```
{
  "accountName": "My Account",
  "accountNumber": "Acc Name 2",
  "balance": 123.00
  
}
```

### Get Customer and Account details
The composite service can be invoked by 
```
GET http://customer-composite-service-demo-project.192.168.64.3.nip.io/customers/1 
```



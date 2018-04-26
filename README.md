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
Before you try this example download and setup [minishift](https://docs.openshift.org/latest/minishift/index.html)

Start a minishift with no parameters
```
minishift start 
```
You can also start a minishift with 4GB memory and 2 CPUS and profile name called `springboot_msa_on_oc`
```
minishift --profile springboot_msa_on_oc --memory=4096 --cpus=2 start
```
### CLI commands
Get a list of PODs, services, routes.
```
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

* Load resources (Templates, DeploymentConfigs, Services)
```
oc create -f https://raw.githubusercontent.com/rondinif/openshift-kafka/master/resources.yaml
```
* Deploy the Apache Kafka + Apache Zookeeper pod
```
oc new-app apache-kafka
```
* Deploy a debugging container and connect to it
```
oc run -it --rm kafka-debug --image=rondinif/openshift-kafka --command -- bash
```

### Deploying Microservices to OpenShift
* Compile and create application jar by executing command
```
mvn clean install
```
* Create docker image by running below command. The -t option tags the docker image with "mrudul03/customer-service:v01". After creating the docker image, push the image to docker hub.
```
docker build -t mrudul03/customer-service:v01 .
docker push mrudul03/customer-service:v01
```

* apply yml files to create OpenShift objects
```
oc apply -f customer-service-deployment.yml
oc apply -f customer-service.yml
oc expose svc customer-service
```

## Verify the deployment
Check if all the pods are successfully created and running.
```
oc get pods
oc get svc
oc get routes
```

## Running application
Create a customer by POST http://customer-service-demo-project.192.168.64.3.nip.io/customers/ 
```
{
  "firstName": "Mary 123",
  "lastName": "Popins 123",
  "addresses": [
      {
          "streetAddress": "Street Address",
          "state": "State",
          "city": "City",
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
Successful creation of customer record raises an event (CreatePaymentAccountCommand) on kafka. Payment Account Service consumes this event and creates a payment account.
You can check the logs in openshift web console. At the same time, you can query Payment Account Service GET http://payment-account-service-demo-project.192.168.64.3.nip.io/accounts/ 

The composite service can be invoked by http://customer-composite-service-demo-project.192.168.64.3.nip.io/customers/1 



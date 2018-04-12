https://github.com/IBM/spring-boot-microservices-on-kubernetes

mvn clean install

# create docker image
# docker build -t mrudul03/payment-account-service:01 .
sh build.sh

# push to docker registry
docker push mrudul03/payment-account-service:01

# create OpenShift components
oc create -f payment-account-service.yaml

====================
oc create -f account-database.yaml

Try with new docker file
cd /Users/Mrudul/Documents/workspace-github/openshift-microservices/payment-account-service
docker build -t mrudul03/payment-account-service:01 .
docker push mrudul03/payment-account-service:01
oc create -f payment-account-service.yaml
oc expose svc payment-account-service

oc delete all --selector app=demo-app


http://payment-account-service-demo-project.192.168.64.3.nip.io/customers/1/accounts/123
http://payment-account-service-demo-project.192.168.64.3.nip.io/customers/1/accounts/123
===================================


oc create -f demo-database.yaml
oc create -f payment-account-service.yaml
oc expose svc payment-account-service


docker build -t mrudul03/customers-service:03 .
docker push mrudul03/customers-service:03
oc create -f customers-service.yaml

oc create -f apache-kafka-2.yaml

#oc create -f customer-database.yaml


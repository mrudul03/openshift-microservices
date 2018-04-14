https://github.com/IBM/spring-boot-microservices-on-kubernetes


oc create -f demo-database.yaml
oc create -f kafka-config.yaml

cd /Users/Mrudul/Documents/workspace-github/openshift-microservices/payment-account-service
mvn clean install
docker build -t mrudul03/payment-account-service:01 .
docker push mrudul03/payment-account-service:01
oc create -f payment-account-service.yaml
oc expose svc payment-account-service

oc delete all --selector app=demo-app

http://payment-account-service-demo-project.192.168.64.3.nip.io/customers/1/accounts/123
===================================

## https://github.com/bijukunjummen/sample-spring-kafka-producer-consumer

docker build -t mrudul03/payment-account-service:21 .
docker push mrudul03/payment-account-service:21
oc apply -f payment-account-deployment.yml
oc apply -f payment-account-service.yml
oc expose svc payment-account-service

oc delete all --selector app=payment-account-service
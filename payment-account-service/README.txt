
oc create -f demo-database.yaml
oc create -f kafka-config.yaml
## https://github.com/bijukunjummen/sample-spring-kafka-producer-consumer

docker build -t mrudul03/payment-account-service:28 .
docker push mrudul03/payment-account-service:28
oc apply -f payment-account-deployment.yml
oc apply -f payment-account-service.yml
oc expose svc payment-account-service

oc delete all --selector app=payment-account-service

http://payment-account-service-demo-project.192.168.64.3.nip.io/customers/1/accounts/123

===================================

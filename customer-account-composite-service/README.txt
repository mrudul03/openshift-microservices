## https://github.com/donovanmuller/echo-example/tree/feign
## https://github.com/piomin/sample-spring-microservices/tree/kubernetes

docker build -t mrudul03/customer-composite-service:10 .
docker push mrudul03/customer-composite-service:10
oc apply -f customer-composite-deployment.yml
oc apply -f customer-composite-service.yml
oc expose svc customer-composite-service

oc delete all --selector app=customer-composite-service

http://customer-composite-service-demo-project.192.168.64.3.nip.io/customers/1
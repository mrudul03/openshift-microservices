## https://github.com/donovanmuller/echo-example/tree/feign
## https://github.com/piomin/sample-spring-microservices/tree/kubernetes

docker build -t mrudul03/customer-composite-service:v01 .
docker push mrudul03/customer-composite-service:v01
oc apply -f customer-composite-deployment.yml
oc apply -f customer-composite-service.yml
oc expose svc customer-composite-service

oc delete all --selector app=customer-composite-service

http://customer-composite-service-demo-project.192.168.64.3.nip.io/customers/1


oc apply -f <(istioctl kube-inject -f customer-composite-deployment.yml) -n tutorial
oc apply -f customer-composite-service.yml -n tutorial
oc expose svc customer-composite-service
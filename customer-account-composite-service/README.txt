
docker build -t mrudul03/customer-composite-service:01 .
docker push mrudul03/customer-composite-service:01
oc apply -f customer-composite-deployment.yml
oc apply -f customer-composite-service.yml
oc expose svc customer-composite-service

oc delete all --selector app=customer-composite-service
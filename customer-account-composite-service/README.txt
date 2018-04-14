
docker build -t mrudul03/customer-composite-service:01 .
docker push mrudul03/customer-composite-service:01
oc create -f customer-composite-service.yaml
oc expose svc customer-composite-service

oc delete all --selector app=customer-composite-app
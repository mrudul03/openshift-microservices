
## rsh will ssh into the mysql pod
oc rsh $(oc get pods | grep demodatabase | grep Running | awk '{print $1}')

## inside the pod 
mysql -u $MYSQL_USER -p$MYSQL_PASSWORD -h $HOSTNAME $MYSQL_DATABASE

oc env dc customers-service -e MYSQL_USER=app_user -e MYSQL_PASSWORD=password -e MYSQL_DATABASE=microservices
oc env dc customers-service -e MYSQL_DB_HOST=demo-database -e MYSQL_DB_PORT=3360 -e MYSQL_DB_USER=demouser -e MYSQL_DB_PASSWORD=password 

## https://github.com/bijukunjummen/sample-spring-kafka-producer-consumer

docker build -t mrudul03/customer-service:v01 .
docker push mrudul03/customer-service:v01
oc apply -f customer-service-deployment.yml
oc apply -f customer-service.yml
oc expose svc customer-service

oc delete all --selector app=customer-service

oc apply -f <(istioctl kube-inject -f customer-service-deployment.yml) -n tutorial
oc apply -f customer-service.yml -n tutorial
oc expose svc customer-service



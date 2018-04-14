

Try with new docker file
cd /Users/Mrudul/Documents/workspace-github/openshift-microservices/payment-account-service
docker build -t mrudul03/customers-service:21 .
docker push mrudul03/customers-service:21
oc create -f customers-service.yaml
oc expose svc customers-service

oc delete all --selector buildconfigs=customers-service-s2i
oc delete all --selector app=customers-app

## rsh will ssh into the mysql pod
oc rsh $(oc get pods | grep demo-database | grep Running | awk '{print $1}')
## inside the pod 
mysql -u $MYSQL_USER -p$MYSQL_PASSWORD -h $HOSTNAME $MYSQL_DATABASE

## https://github.com/bijukunjummen/sample-spring-kafka-producer-consumer


docker build -t mrudul03/customer-service:21 .
docker push mrudul03/customer-service:21
oc apply -f customer-service-deployment.yml
oc apply -f customer-service.yml
oc expose svc customer-service

oc delete all --selector app=customer-service

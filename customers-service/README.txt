

Try with new docker file
cd /Users/Mrudul/Documents/workspace-github/openshift-microservices/payment-account-service
docker build -t mrudul03/customers-service:1 .
docker push mrudul03/customers-service:1
oc create -f customers-service.yaml
oc expose svc customers-service

oc delete all --selector buildconfigs=customers-service-s2i

## rsh will ssh into the mysql pod
oc rsh $(oc get pods | grep demo-database | grep Running | awk '{print $1}')
## inside the pod 
mysql -u $MYSQL_USER -p$MYSQL_PASSWORD -h $HOSTNAME $MYSQL_DATABASE

## https://github.com/bijukunjummen/sample-spring-kafka-producer-consumer
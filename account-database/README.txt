
docker pull registry.access.redhat.com/rhscl/mysql-56-rhel7

oc new-app \
    -e MYSQL_USER=<username> \
    -e MYSQL_PASSWORD=<password> \
    -e MYSQL_DATABASE=<database_name> \
    registry.access.redhat.com/rhscl/mysql-56-rhel7
    
oc new-app -e MYSQL_USER='app_user' MYSQL_PASSWORD='password' MYSQL_DATABASE=microservices registry.access.redhat.com/rhscl/mysql-56-rhel7 --name='mysql' -l microservice=dbsvc    


oc create -f account-database.yaml
oc rsh $(oc get pods | grep account-database | grep Running | awk '{print $1}')
mysql -u $MYSQL_USER -p$MYSQL_PASSWORD -h $HOSTNAME $MYSQL_DATABASE
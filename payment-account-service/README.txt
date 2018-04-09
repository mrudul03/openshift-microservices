https://github.com/IBM/spring-boot-microservices-on-kubernetes

mvn clean install

# create docker image
# docker build -t mrudul03/payment-account-service:01 .
sh build.sh

# push to docker registry
docker push mrudul03/payment-account-service:01

# create OpenShift components
oc create -f payment-account-service.yaml

====================
Try with new docker file
docker build -t mrudul03/payment-account-service:03 .
docker push mrudul03/payment-account-service:03

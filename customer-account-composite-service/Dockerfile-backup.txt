FROM java:8
COPY . /app
WORKDIR /app
CMD java -jar target/*.jar
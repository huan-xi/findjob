git pull
mvn clean package docker:build -DskipTests
docker stop findjob0
docker rm findjob0
images=findjob
docker run --name findjob0 -d -p 8080:8080 $images
#docker run --name findjob1 -d -p 8081:8080 $images


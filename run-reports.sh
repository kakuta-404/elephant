mvn -f ./java-spark-app/pom.xml clean package
echo "Building java spark app was successful. Trying to submit the generated jar file to spark."
docker exec -it spark-master-1 /spark/bin/spark-submit --class app.Main --master spark://spark-master-1:7077,spark-master-2:7077 /opt/spark-app/target/java-spark-app-1.0-SNAPSHOT.jar
sleep 10
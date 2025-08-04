mvn -f ./java-spark-app/pom.xml clean package
echo "Buidling java spark app was successfull. Trying to submint the generated jar file to spark."
docker exec -it spark-master /spark/bin/spark-submit --class app.Main --master spark://spark-master:7077 /opt/spark-app/java-spark-app-1.0-SNAPSHOT.jar
sleep 100


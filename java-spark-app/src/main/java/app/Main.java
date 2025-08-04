package app;

import org.apache.spark.sql.*;

public class Main {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("Java Spark HDFS Test")
                .getOrCreate();

        String path = "hdfs://namenode:9000/tmp/spark_java_test";

        // Generate numbers 0 to 9 and write as Parquet
        Dataset<Row> numbers = spark.range(10).toDF("value");
        numbers.write().mode(SaveMode.Overwrite).parquet(path);

        // Read it back and count
        Dataset<Row> reloaded = spark.read().parquet(path);
        long count = reloaded.count();

        System.out.println("Row count = " + count);

        spark.stop();
    }
}

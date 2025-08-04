package app;

import app.queries.*;
import org.apache.spark.sql.*;

public class Main {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("NYC Taxi Data Analysis")
                .getOrCreate();

        Dataset<Row> taxiData = spark.read()
                .option("header", "true")
                .parquet("hdfs://namenode:9000/data/taxi/yellow_tripdata_2025-01.parquet");

        taxiData.createOrReplaceTempView("data");

        // Execute queries
        MaxTipQuery.execute(taxiData);
        DurationMinutesQuery.execute(taxiData);

        spark.stop();
    }
}

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

        Dataset<Row> zoneData = spark.read()
            .format("csv")
            .option("header", "true")
            .load("hdfs://namenode:9000/data/taxi/taxi_zone_lookup.csv");

        // query 1
        DurationMinutesQuery.execute(taxiData);
        // query 2
        FarePriceByZone.execute(taxiData, zoneData);
        // query 3
        TotalAmountByBorough.execute(taxiData, zoneData);
        // query 4
        MaxTipQuery.execute(taxiData);

        spark.stop();
    }
}

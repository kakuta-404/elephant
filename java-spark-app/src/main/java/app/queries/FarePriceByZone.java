package app.queries;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import static org.apache.spark.sql.functions.*;

public class FarePriceByZone {
    public static Dataset<Row> execute(Dataset<Row> taxiData, Dataset<Row> zoneData) {
        Dataset<Row> joined = taxiData
                .join(zoneData, taxiData.col("PULocationID").equalTo(zoneData.col("LocationID")));

        Dataset<Row> result = joined
                .groupBy(zoneData.col("Zone"))
                .agg(
                    first(zoneData.col("Borough")).alias("Borough"),
                    avg("fare_amount").alias("average_fare_amount")
                );

        result.write()
                .mode("overwrite")
                .parquet("hdfs://namenode:9000/output/average_fare_by_zone");

        return result;
    }
}

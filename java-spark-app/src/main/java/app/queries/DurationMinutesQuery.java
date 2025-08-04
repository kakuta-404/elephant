package app.queries;

import org.apache.spark.sql.*;
import static org.apache.spark.sql.functions.*;

public class DurationMinutesQuery {
    public static Dataset<Row> execute(Dataset<Row> taxiData) {
        Dataset<Row> results = taxiData
                .withColumn("duration_minutes", 
                    round(unix_timestamp(col("tpep_dropoff_datetime"))
                    .minus(unix_timestamp(col("tpep_pickup_datetime")))
                    .divide(60)))
                .where(col("passenger_count").gt(2)
                    .and(col("trip_distance").gt(5)))
                .orderBy(col("duration_minutes").desc());

        results.write()
                .mode(SaveMode.Overwrite)
                .parquet("hdfs://namenode:9000/output/long_trips_multiple_passengers");

        return results;
    }
}

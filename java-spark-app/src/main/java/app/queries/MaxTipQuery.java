package app.queries;

import org.apache.spark.sql.*;
import static org.apache.spark.sql.functions.*;

public class MaxTipQuery {
    public static Dataset<Row> execute(Dataset<Row> taxiData) {
        Dataset<Row> results = taxiData
                .withColumn("day", date_trunc("day", col("tpep_pickup_datetime")))
                .groupBy("day")
                .agg(max("tip_amount").as("max_tip_amount"));

        results.write()
                .mode(SaveMode.Overwrite)
                .parquet("hdfs://namenode:9000/output/max_tips_by_day");

        return results;
    }
}

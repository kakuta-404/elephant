package app.queries;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import static org.apache.spark.sql.functions.*;

public class TotalAmountByBorough {
    public static Dataset<Row> execute(Dataset<Row> taxiData, Dataset<Row> zoneData) {
        Dataset<Row> result = taxiData
            .join(zoneData, taxiData.col("DOLocationID").equalTo(zoneData.col("LocationID")))
            .groupBy(zoneData.col("Borough"))
            .agg(
                sum("total_amount").alias("sum_total_amount"),
                count("*").alias("trips_count")
            );

        result.write()
            .mode("overwrite")
            .parquet("hdfs://namenode:9000/output/sum_total_amount_by_borough");

        return result;
    }
}

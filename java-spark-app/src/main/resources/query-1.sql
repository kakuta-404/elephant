-- calculate tpep_dropoff_datetime , tpep_pickup_datetime difference by minutes and set as coulmn named duration_minutes and sort the result by duration_minutes descending order
SELECT * 
FROM data
WHERE (passenger_count > 2) 
AND
trip_distance > 5;
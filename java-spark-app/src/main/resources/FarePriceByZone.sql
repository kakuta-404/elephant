SELECT 
    cs.Zone,
    first(cs.Borough) AS Borough,
    AVG(pq.fare_amount) AS average_fare_amount
FROM 
    pq
JOIN 
    cs ON pq.PULocationID = cs.LocationID
GROUP BY 
    cs.Zone;

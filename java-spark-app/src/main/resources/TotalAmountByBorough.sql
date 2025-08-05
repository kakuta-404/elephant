SELECT 
    cs.Borough,
    SUM(pq.total_amount) AS sum_total_amount,
    COUNT(*) AS trips_count
FROM 
    pq
JOIN 
    cs ON pq.DOLocationID = cs.LocationID
GROUP BY 
    cs.Borough;
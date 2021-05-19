SELECT a.id
FROM 
    Weather AS a JOIN Weather AS b
ON DATEDIFF(a.recordDate, b.recordDate) = 1 AND a.Temperature > b.Temperature  

-------------------
SELECT a.id
FROM 
    Weather AS a, Weather AS b
WHERE DATEDIFF(a.recordDate, b.recordDate) = 1 AND a.Temperature > b.Temperature  

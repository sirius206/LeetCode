SELECT machine_id, round(AVG(time),3) AS processing_time
FROM
    (SELECT a.machine_id, a.timestamp - b.timestamp AS time
    FROM Activity AS a JOIN Activity AS b
    ON a.machine_id = b.machine_id AND a.process_id = b.process_id  AND a.activity_type = 'end' AND b.activity_type = 'start') AS c
GROUP BY machine_id


-------------------------
SELECT s.machine_id, ROUND(AVG(e.timestamp-s.timestamp), 3) AS processing_time
FROM Activity s JOIN Activity e ON
    s.machine_id = e.machine_id AND s.process_id = e.process_id AND
    s.activity_type = 'start' AND e.activity_type = 'end'
GROUP BY s.machine_id

------------------------
SELECT machine_id, ROUND((SUM(CASE WHEN activity_type = 'end' THEN timestamp END)-SUM(CASE WHEN activity_type = 'start' THEN timestamp END))/COUNT(DISTINCT process_id), 3) processing_time
FROM Activity
GROUP BY 1

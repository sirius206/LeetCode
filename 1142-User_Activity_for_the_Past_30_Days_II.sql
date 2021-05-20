SELECT IFNULL(round(AVG(a), 2), 0.00) AS average_sessions_per_user
FROM 
    (SELECT COUNT(DISTINCT session_id) AS a
    FROM Activity
    WHERE DATEDIFF('2019-07-27', activity_date) < 30
    GROUP BY user_id ) AS b
    
    
----------------
SELECT ifnull(ROUND(COUNT(DISTINCT session_id)/COUNT(DISTINCT user_id), 2),0.00) 
AS average_sessions_per_user
FROM Activity 
WHERE activity_date >= '2019-06-28' and activity_date <= '2019-07-27'; 

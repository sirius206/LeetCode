SELECT activity_date AS day, COUNT(DISTINCT user_id) AS active_users
FROM Activity
WHERE DATEDIFF('2019-07-27', activity_date) < 30
GROUP BY activity_date 
------------------------------------------------------------
SELECT
    IFNULL(COUNT(DISTINCT user_id), 0) as active_users,
    activity_date as day
FROM Activity
where 
    datediff('2019-07-27', activity_date) < 30
        and
    activity_date <= '2019-07-27'
GROUP BY activity_date```

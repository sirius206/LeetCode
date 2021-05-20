SELECT class
From courses
GROUP BY class
HAVING COUNT(DISTINCT student) >= 5

----------------
SELECT
    class
FROM
    (SELECT
        class, COUNT(DISTINCT student) AS num
    FROM
        courses
    GROUP BY class) AS temp_table
WHERE
    num >= 5

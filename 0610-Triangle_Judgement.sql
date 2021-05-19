SELECT x, y, z,
CASE 
    WHEN x + y > z AND x + z > y AND y + z > x THEN 'Yes'
    ELSE 'No'
    END AS 'triangle'
FROM triangle
------------------
select *, 
    IF(x + y > z AND x + z > y AND y + z > x, 'Yes', 'No') as triangle 
    from triangle;

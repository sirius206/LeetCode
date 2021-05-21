SELECT DISTINCT s.name
FROM salesperson AS s 
WHERE s.sales_id NOT IN (
    SELECT o.sales_id 
    FROM orders AS o LEFT JOIN company AS c ON c.com_id = o.com_id
    WHERE c.name = 'RED'
)
--------------------------------------
select salesperson.name
from orders o join company c on (o.com_id = c.com_id and c.name = 'RED')
right join salesperson on salesperson.sales_id = o.sales_id
where o.sales_id is null

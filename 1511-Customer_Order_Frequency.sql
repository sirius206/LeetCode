SELECT c.customer_id, c.name 
FROM Customers AS c 
LEFT JOIN Orders AS o ON c.customer_id = o.customer_id 
LEFT JOIN Product AS p ON p.product_id = o.product_id
GROUP BY c.customer_id
HAVING SUM(CASE WHEN MONTH(o.order_date) = 6 THEN p.price*o.quantity ELSE 0 END) >= 100
AND
SUM(CASE WHEN MONTH(o.order_date) = 7 THEN p.price*o.quantity ELSE 0 END) >= 100

-----------------------------------------------

SELECT customer_id, name
FROM (
    SELECT c.customer_id, c.name, 
    SUM(CASE WHEN LEFT(o.order_date, 7) = '2020-06' THEN p.price*o.quantity ELSE 0 END) AS t1, 
    SUM(CASE WHEN LEFT(o.order_date, 7) = '2020-07' THEN p.price*o.quantity ELSE 0 END) AS t2
    FROM customers c
    JOIN orders o
    ON c.customer_id = o.customer_id
    JOIN product p
    ON p.product_id = o.product_id
    GROUP BY 1
    ) tmp
WHERE t1 >= 100 AND t2 >= 100

--------------------------------
SELECT customer_id, name
FROM Customers JOIN Orders USING(customer_id) 
    JOIN Product USING(product_id)
GROUP BY customer_id
HAVING SUM(IF(LEFT(order_date, 7) = '2020-06', quantity, 0) * price) >= 100
    AND SUM(IF(LEFT(order_date, 7) = '2020-07', quantity, 0) * price) >= 100
    
  

-----------------------------------
with temp as(
    select 
    c.customer_id, c.name, sum(p.price * o.quantity) as price
    from Customers c
    inner join 
    Orders o
    on c.customer_id = o.customer_id
    inner join
    Product p
    on o.product_id = p.product_id
    where o.order_date like '2020-06%' or o.order_date like '2020-07%'
    group by c.customer_id, c.name, month(o.order_date))

select customer_id, name from temp
group by customer_id, name
having sum(case when price >= 100 then 1 else 0 end) = 2

SELECT Customers.name as 'Customers'
FROM Customers
WHERE Customers.Id NOT IN
(SELECT customerId from Orders)

----------------------
SELECT Name as 'Customers'
FROM Customers LEFT JOIN Orders
ON Customers.id = Orders.CustomerId
WHERE Orders.CustomerId IS NULL

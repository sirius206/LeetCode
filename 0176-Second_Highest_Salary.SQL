SELECT
    (SELECT DISTINCT Salary 
    FROM Employee
    ORDER BY Salary DESC
    LIMIT 1 OFFSET 1)
AS SecondHighestSalary


-------------------------
SELECT
    IFNULL(
    (SELECT DISTINCT Salary 
    FROM Employee
    ORDER BY Salary DESC
    LIMIT 1 OFFSET 1), NULL)
AS SecondHighestSalary

-------------------------
select max(Salary) as SecondHighestSalary from Employee where Salary not in (select max(Salary) from Employee)
------------------------
SELECT MAX(Salary) AS SecondHighestSalary
FROM Employee
WHERE Salary < (SELECT MAX(Salary) FROM Employee)

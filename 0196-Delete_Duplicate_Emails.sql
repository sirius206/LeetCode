DELETE p1 
FROM 
    Person AS p1,
    Person AS p2
WHERE 
    p1.Email = p2.Email 
    AND
    p1.Id > p2.Id
    
    
--------------------------
DELETE 
FROM 
    Person
WHERE 
    Id NOT IN
    (SELECT * FROM
        (SELECT MIN(Id) 
        FROM Person 
        GROUP BY Email) AS p)

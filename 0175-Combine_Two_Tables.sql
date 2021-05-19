SELECT FirstName, LastName, City, State
FROM Person LEFT JOIN Address
On Person.PersonID = Address.PersonID

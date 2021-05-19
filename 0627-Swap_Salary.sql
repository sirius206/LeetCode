UPDATE Salary
SET 
    sex = CASE sex
        WHEN 'm' THEN 'f'
        ELSE 'm'
    END
------------------------

UPDATE salary
SET sex = IF(sex='f','m','f')

------------------------
UPDATE salary
SET sex = Case
When sex = 'm' Then 'f'
When sex = 'f' Then 'm'
End
    

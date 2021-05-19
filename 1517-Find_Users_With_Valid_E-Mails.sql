select * from Users 
where regexp_like(mail, '^[A-Za-z]+[A-Za-z0-9\_\.\-]*@leetcode.com')
"""
A detailed explanation of the following regular expression solution:

'^[A-Za-z]+[A-Za-z0-9\_\.\-]*@leetcode.com'

1. ^ means the beginning of the string
    - This is important because without it, we can have something like
    '.shapiro@leetcode.com'
    This is because *part* of the regex matches the pattern perfectly. 
    The part that is 'shapiro@leetcode.com'.
    This is how I understand it: regex will return the whole 
    thing as long as part of it matches. By adding ^ we are saying: you have to
    match FROM THE START.
	
2. [] means character set. [A-Z] means any upper case chars. In other words, 
    the short dash in the character set means range.
	
3. After the first and the second character set, there is a notation: + or *.
    + means at least one of the character from the preceding charset, and * means 
    0 or more. 
	
4. \ inside the charset mean skipping. In other words, \. means we want the dot as 
    it is. Remember, for example, - means range in the character set. So what if
     we would like to find - itself as a character? use \-. 
	 
5. Everything else, like @leetcode.com refers to exact match.
"""
----------------
SELECT *
FROM Users
WHERE mail REGEXP '^[A-Za-z][A-Za-z0-9\_\.\-]*@leetcode\.com$'

^[A-Za-z] the first character must be a letter. after that...
[A-Za-z0-9_.-]* match any number of letters, numbers, underscore, periods, dashes (the * sign indicating "any number of, including zero"). after that...
@leetcode.com$ the email must end with exactly "@leetcode.com" (the $ sign indicating "ending with")

Note that the escape characters are not strictly necessary. This means that the backslash ( \ ) symbol that precedes the period, dash, etc is not needed here. I've left them in because it's what I'm accustomed to. I don't know why exactly MySQL does not require them, but so it is.

Also note that, since SQL is not case sensitive generally, it's also not necessary to include both uppercase and lowercase letters (ie one could just as easily use [A-Z] instead of [A-Za-z]. Again, I've included them because outside of SQL that would be necessary.


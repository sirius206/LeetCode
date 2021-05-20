select id, 
	sum(case when month = 'jan' then revenue else null end) as Jan_Revenue,
	sum(case when month = 'feb' then revenue else null end) as Feb_Revenue,
	sum(case when month = 'mar' then revenue else null end) as Mar_Revenue,
	sum(case when month = 'apr' then revenue else null end) as Apr_Revenue,
	sum(case when month = 'may' then revenue else null end) as May_Revenue,
	sum(case when month = 'jun' then revenue else null end) as Jun_Revenue,
	sum(case when month = 'jul' then revenue else null end) as Jul_Revenue,
	sum(case when month = 'aug' then revenue else null end) as Aug_Revenue,
	sum(case when month = 'sep' then revenue else null end) as Sep_Revenue,
	sum(case when month = 'oct' then revenue else null end) as Oct_Revenue,
	sum(case when month = 'nov' then revenue else null end) as Nov_Revenue,
	sum(case when month = 'dec' then revenue else null end) as Dec_Revenue
from department
group by id
order by id
----------------------------------------
;WITH ids AS 
(
    SELECT DISTINCT id FROM Department
)
SELECT 
    ids.id, 
    d_jan.revenue AS Jan_Revenue,
    d_feb.revenue AS Feb_Revenue,
    d_mar.revenue AS Mar_Revenue,
    d_apr.revenue AS Apr_Revenue,
    d_may.revenue AS May_Revenue,
    d_jun.revenue AS Jun_Revenue,
    d_jul.revenue AS Jul_Revenue,
    d_aug.revenue AS Aug_Revenue,
    d_sep.revenue AS Sep_Revenue,
    d_oct.revenue AS Oct_Revenue,
    d_nov.revenue AS Nov_Revenue,
    d_dec.revenue AS Dec_Revenue
FROM ids
LEFT JOIN Department d_jan ON d_jan.id = ids.id AND d_jan.month = 'Jan'
LEFT JOIN Department d_feb ON d_feb.id = ids.id AND d_feb.month = 'Feb'
LEFT JOIN Department d_mar ON d_mar.id = ids.id AND d_mar.month = 'Mar'
LEFT JOIN Department d_apr ON d_apr.id = ids.id AND d_apr.month = 'Apr'
LEFT JOIN Department d_may ON d_may.id = ids.id AND d_may.month = 'May'
LEFT JOIN Department d_jun ON d_jun.id = ids.id AND d_jun.month = 'Jun'
LEFT JOIN Department d_jul ON d_jul.id = ids.id AND d_jul.month = 'Jul'
LEFT JOIN Department d_aug ON d_aug.id = ids.id AND d_aug.month = 'Aug'
LEFT JOIN Department d_sep ON d_sep.id = ids.id AND d_sep.month = 'Sep'
LEFT JOIN Department d_oct ON d_oct.id = ids.id AND d_oct.month = 'Oct'
LEFT JOIN Department d_nov ON d_nov.id = ids.id AND d_nov.month = 'Nov'
LEFT JOIN Department d_dec ON d_dec.id = ids.id AND d_dec.month = 'Dec'

-------------------------------------
Pivot table solution

SELECT 
    id,
    Jan AS Jan_Revenue,
    Feb AS Feb_Revenue, 
    Mar AS Mar_Revenue, 
    Apr AS Apr_Revenue,
    May AS May_Revenue,
    Jun AS Jun_Revenue,
    Jul AS Jul_Revenue,
    Aug AS Aug_Revenue,
    Sep AS Sep_Revenue,
    Oct AS Oct_Revenue,
    Nov AS Nov_Revenue,
    Dec AS Dec_Revenue
FROM Department
PIVOT 
(
    MAX(revenue)
    FOR month IN (Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec)        
) AS MonthsRevenue

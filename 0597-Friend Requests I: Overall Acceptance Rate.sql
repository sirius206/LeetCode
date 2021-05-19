SELECT 
ROUND(
    IFNULL(
        ((SELECT COUNT(DISTINCT requester_id, accepter_id) FROM RequestAccepted AS a)
        /
        (SELECT COUNT(DISTINCT sender_id, send_to_id) FROM FriendRequest AS b)), 0), 2)
as accept_rate


----------------
Second, follow-up1:Can you write a query to return the accept rate but for every month?
This solution comes from this post:
https://leetcode.com/problems/friend-requests-i-overall-acceptance-rate/discuss/103579/Following-up-questions.-Solved-Q1-how-to-solve-Q2

##  create a subquery which contains count, month, join each other with month and group by month so we can get the finally result.
select if(d.req =0, 0.00, round(c.acp/d.req,2)) as accept_rate, c.month from 
(select count(distinct requester_id, accepter_id) as acp, Month(accept_date) as month from request_accepted) c, 
(select count(distinct sender_id, send_to_id) as req, Month(request_date) as month from friend_request) d 
where c.month = d.month 
group by c.month
Third, follow-up2:How about the cumulative accept rate for every day?
This solution comes from this post:
https://leetcode.com/problems/friend-requests-i-overall-acceptance-rate/discuss/103583/one-possible-answer-for-question-2-cumulative-sums-by-day.

## sum up the case when ind is 'a', which means it belongs to accept table, divided by sum of ind is 'r', which means it belong to request table
select s.date1, ifnull(round(sum(case when t.ind = 'a' then t.cnt else 0 end)/sum(case when t.ind = 'r' then t.cnt else 0 end),2),0) 
from
## get a table of all unique dates
(select distinct x.request_date as date1 from friend_request x
## The reason here use union sicne we don't want duplicate date
union 
 select distinct y.accept_date as date1 from request_accepted y 
) s
## left join to make sure all dates are in the final output
left join 
## get a table of all dates, count of each days, ind to indicate which table it comes from
(select v.request_date as date1, count(*) as cnt,'r' as ind from friend_request v group by v.request_date
## The reason here use union all sicne union all will be faster
union all
select w.accept_date as date1, count(*) as cnt,'a' as ind from request_accepted w group by w.accept_date) t
## s.date1 >= t.date1, which for each reacord in s, it will join with all records earlier than it in t
on s.date1 >= t.date1
# group by s.date1 then we can get a cumulative result to that day
group by s.date1
order by s.date1
;

SELECT DISTINCT a.seat_id
FROM cinema AS a,
     cinema AS b
WHERE a.free = 1 AND b.free = 1 AND (a.seat_id = b.seat_id + 1 OR a.seat_id = b.seat_id - 1)
ORDER BY a.seat_id


----------------
#abs
select distinct a.seat_id
from cinema a join cinema b
  on abs(a.seat_id - b.seat_id) = 1
  and a.free = true and b.free = true
order by a.seat_id

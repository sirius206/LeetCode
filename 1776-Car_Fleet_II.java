/*
use Stack, Time O(n), Space O(n)
Intuition
Go from right to left, and only consider cars up ahead. If someone bumps into us - it does not matter as our speed won't change.

Solution
Process cars right-to-left, and maintain a stack s of cars with monotonically increasing speeds:

If the current car is slower than the slowest car up ahead - it won't collide, and the stack becomes empty.
If there are cars in the stack, the current car sure would collide. Also, all cars in the stack - except the first one - have collided at some point res[i].

So, we go from the top of the stack (fastest car) and check if we collide with it before it collides with cars up ahead. 
If we collide with that car after it collided, we can ignore that car and also remove it from the stack.
*/
//C++
vector<double> getCollisionTimes(vector<vector<int>>& c) {
    auto col_time = [&](int i, int j) { 
        return (double)(c[i][0] - c[j][0]) / (c[j][1] - c[i][1]); 
    };
    vector<double> res(c.size(), -1), s;
    for (int i = c.size() - 1; i >= 0; --i) {
        while (!s.empty() && (c[i][1] <= c[s.back()][1] || 
                              (s.size() > 1 && col_time(i, s.back()) >= res[s.back()])))
            s.pop_back();
        res[i] = s.empty() ? -1 : col_time(i, s.back());
        s.push_back(i);
    }
    return res;
}

//2
/*
We care about the collision time of the cars in front us.
We iteratre from the last to the first car,
and we main a stack of car indices,
where their collision time is strict decreasing.

Imagine a,b,c on the road
if the a catches b later than b catched c,
then a won't catch b but b+c.

*/
    public double[] getCollisionTimes(int[][] A) {
        int n = A.length;
        Deque<Integer> stack = new LinkedList<>();
        double[] res = new double[n];
        for (int i = n - 1; i >= 0; --i) {
            res[i] = -1.0;
            int p = A[i][0], s = A[i][1];
            while (stack.size() > 0) {
                int j = stack.peekLast(), p2 = A[j][0], s2 = A[j][1];
              //If the current car is slower than the slowest car up ahead - it won't collide, and the stack becomes empty.
              //If we collide with that car after it collided, we can ignore that car and also remove it from the stack.
                if ((s <= s2 || 
                     (1.0 * (p2 - p) / (s - s2) >= res[j]) && res[j] > 0))
                    stack.pollLast();
                else
                    break;
            }
            if (stack.size() > 0) {
                int j = stack.peekLast(), p2 = A[j][0], s2 = A[j][1];
                res[i] = 1.0 * (p2 - p) / (s - s2);
            }
            stack.add(i);
        }
        return res;
    }
    
    
//3. priorityqueue
/*
Intuitive thoughts: always choose the earlist collision to deal with, update the car fleet.

For every two adjacent cars, there could be collision if and only if the first car is faster than the second car. 
Once these two cars collides, they would become a new car with the lower speed (the second car). So it will only influence 
the condition of the previous car of the first car.

Example: 0-------1------2-----3 -> car 1 collides with car 2 -> 0-------(1,2)-------3
If car 1 and car 2 collides, the new car (1,2) still maintain the speed same with car 2 (car 2 is slower than car 1). 
That is to say, this collision only influence the relationship between car 0 and car (1,2).

So here I create a priority_queue to record the condition of each car with its next car. 
I first create a Node class to store the car_id and the time it needs to catch up its next car. 
For each car from 0 ~ n-2 (the last car has no next car), I create the Node object and push into priority_queue. 
For each Node object popped from the priority_queue, assign its time to the answer with the car_id, 
then update the information for its previous car. Here I create a TreeSet to record all alive cars so that I can quickly find the previous car and next car.
*/
    class Solution {
    class Node {
        int id; // car_id
        double time; // the time for collision with the next car
        
        public Node(int _id, double _time) {
            id = _id;
            time = _time;
        }
    }
    public double[] getCollisionTimes(int[][] cars) {
        PriorityQueue<Node> pq = new PriorityQueue<>(200000, (a, b) -> Double.compare(a.time, b.time));
        
        int n = cars.length;
        for (int i = 0; i < n - 1; ++i) {
            if (cars[i][1] > cars[i+1][1]) { // if there could be collision for i-th car and i+1-th car
                pq.offer(new Node(i, (double)(cars[i+1][0] - cars[i][0]) / (double)(cars[i][1] - cars[i+1][1])));
            }
        }
        
        double[] res = new double[n];
        Arrays.fill(res, -1.0);
        TreeSet<Integer> ts = new TreeSet<>(); // used to record all alive cars
        for (int i = 0; i < n; ++i) {
            ts.add(i);
        }
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (res[cur.id] != -1.0) // this means such car is already processed in previous steps
                continue;
            res[cur.id] = cur.time;
            if (ts.lower(cur.id) == null) // if there is no previous car, no need for update
                continue;
            int prev = ts.lower(cur.id); // find the previous alive car
            int next = ts.higher(cur.id); // find the next car, since this is the next car of the previous car now
            if (cars[prev][1] > cars[next][1]) { // update the information of previous car
                pq.offer(new Node(prev, (double)(cars[next][0] - cars[prev][0]) / (double)(cars[prev][1] - cars[next][1]))); 
            }
            ts.remove(cur.id); // such car is dead, remove it
        }
        
        return res;
    }
}

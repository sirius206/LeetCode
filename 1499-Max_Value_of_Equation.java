/*

Because xi < xj,
yi + yj + |xi - xj| = (yi - xi) + (yj + xj)

So for each pair of (xj, yj),
we have xj + yj, and we only need to find out the maximum yi - xi.
To find out the maximum element in a sliding window,
we can use priority queue or stack.
*/
//1. Priority Queue, Time O(NlogN), Space O(N)
class Solution {
    public int findMaxValueOfEquation(int[][] points, int k) {
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> (a.getKey() == b.getKey() ? a.getValue() - b.getValue() : b.getKey() - a.getKey()));
        int res = Integer.MIN_VALUE;
        for (int[] point : points) {
            while (!pq.isEmpty() && point[0] - pq.peek().getValue() > k) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
              //because i < j
                res = Math.max(res, pq.peek().getKey() + point[0] + point[1]);
            }
            pq.offer(new Pair<>(point[1] - point[0], point[0]));
        }
        return res;
    }
}

//2 Deque Time O(N), Space O(N)
    public int findMaxValueOfEquation(int[][] points, int k) {
        Deque<Pair<Integer, Integer>> ms = new ArrayDeque<>();
        int res = Integer.MIN_VALUE;
        for (int[] point : points) {
            while (!ms.isEmpty() && point[0] - ms.peekFirst().getValue() > k) {
                ms.pollFirst();
            }
            if (!ms.isEmpty()) {
                res = Math.max(res, ms.peekFirst().getKey() + point[0] + point[1]);
            }
            while (!ms.isEmpty() && point[1] - point[0] > ms.peekLast().getKey()) {
                ms.pollLast();
            }
            ms.offerLast(new Pair<>(point[1] - point[0], point[0]));
        }
        return res;
    }

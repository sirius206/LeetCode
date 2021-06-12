// use priority queue instead of sort, Time O(nlogn), Space O(n)
// each insertion/removal is logn, total nlogn, 
//if sort, first nlogn, then each insertion is n, n inserstions, so total n^2
// also not right just to keep adding, because in the sticks array there might be shorter ones, just add the two shortest 
class Solution {
    public int connectSticks(int[] sticks) {
        if (sticks.length <= 1) return 0;
        int total = 0;
        int connect = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int stick : sticks){
            pq.add(stick);
        }
        while (pq.size() > 1){
            connect = pq.remove() + pq.remove();
            pq.add(connect);
            total += connect; 
        }
        return total;      
    }
}

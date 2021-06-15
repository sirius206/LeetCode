// remove the smallest count number, Time: O(nlogn), space: O(n)

class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < arr.length; i++){
            hm.put(arr[i], hm.getOrDefault(arr[i], 0) + 1);
        }
        
        PriorityQueue<Pair<Integer,Integer>> minHeap = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        for (int key : hm.keySet()){
            minHeap.add(new Pair(key, hm.get(key)));
        }
        
        for (int i = 0; i < k; i++){
            Pair<Integer,Integer> p = minHeap.peek();
            hm.put(p.getKey(), hm.get(p.getKey()) - 1);
            if (hm.get(p.getKey()) == 0){
                minHeap.poll();
            }
        }
        int count = 0;
        for (int key : hm.keySet()){
            if (hm.get(key) != 0){
                count++;
            }
        }
        return count;
    }
}


/*HashMap and Array Time: O(n), space: O(n)
Count number then count occurrence:

Count the occurrences of each number using HashMap;
Using Array to count each occurrence, since max occurrence <= arr.length;
From small to big, for each unvisited least frequent element, deduct from k the multiplication with the number of elements of same occurrence, 
check if reaching 0, then deduct the correponding unique count remaining.
*/
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int a : arr)
            count.put(a, 1 + count.getOrDefault(a, 0));
        int remaining = count.size(), occur = 1;
        int[] occurrenceCount = new int[arr.length + 1];
        for (int v : count.values())
            ++occurrenceCount[v];
        while (k > 0) {
            if (k - occur * occurrenceCount[occur] >= 0) {
                k -= occur * occurrenceCount[occur];
                remaining -= occurrenceCount[occur++];
            }else {
                return remaining - k / occur;
            }
        }
        return remaining;        
    }

/*
1. 
If we can do 0 move, return max(A) - min(A)
If we can do 1 move, return min(the second max(A) - min(A), the max(A) - second min(A))
and so on.

sort the array, 
replace by median(doen't need this step)
We have 4 plans:
kill 3 biggest elements
kill 2 biggest elements + 1 smallest elements
kill 1 biggest elements + 2 smallest elements
kill 3 smallest elements
choose the smallest of the above four plans
*/

// Time, Space both O(nlogn) for sort
class Solution {
    public int minDifference(int[] A) {
        int n = A.length, res = Integer.MAX_VALUE;
        if (n < 5) return 0;
        Arrays.sort(A);
        for (int i = 0; i < 4; ++i) {
            res = Math.min(res, A[n - 4 + i] - A[i]);
        }
        return res;
    }
}


/*
2. use heap, Just calculate smallest 4 numbers and largest 4 numbers, and compare the differences
Time:
O(N * lg4 * 2) == O(N), if N > 8;
O(NlgN) if N <= 8;
space: O(1);
*/
class Solution {
    public int minDifference(int[] nums) {
        if (nums.length < 5) return 0;
        if (nums.length <= 8) return getDiff(nums, true);
        PriorityQueue<Integer> top4 = new PriorityQueue<>(), bot4 = new PriorityQueue<>((a, b) -> b - a);
        for (int n : nums) {
            top4.offer(n);
            bot4.offer(n);
            if (top4.size() > 4) top4.poll();
            if (bot4.size() > 4) bot4.poll();
        }
        int[] arr = new int[8];
        for (int l = 3, r = 4; l >= 0 && r < 8; l--, r++) {
            arr[l] = bot4.poll();
            arr[r] = top4.poll();
        } 
        return getDiff(arr, false);
    }
    
    private int getDiff(int[] arr, boolean needSort) {
        if (needSort) Arrays.sort(arr);
        int res = Integer.MAX_VALUE, n = arr.length;
        for (int i = 0; i < 4; i++) {
            res = Math.min(res, arr[n - (4 - i)] - arr[i]);
        }
        return res;
    }
}

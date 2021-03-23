//binary search Time O(log n), Space O(1) 
class Solution {
    public int mySqrt(int x) {
        if (x < 2) return x;
        long hi = x / 2;
        long lo = 2;
        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            if (mid * mid == x) return (int) mid;
            else if (mid * mid < x){
                lo = mid + 1;
            } 
            else hi = mid - 1;
        }
        return (int) hi;
    }
}

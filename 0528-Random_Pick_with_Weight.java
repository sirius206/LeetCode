// prefix sum + binary search
//1. answer
class Solution {
    private int[] prefixSums;
    private int totalSum;

    public Solution(int[] w) {
        this.prefixSums = new int[w.length];

        int prefixSum = 0;
        for (int i = 0; i < w.length; ++i) {
            prefixSum += w[i];
            this.prefixSums[i] = prefixSum;
        }
        this.totalSum = prefixSum;
    }

    public int pickIndex() {
        double target = this.totalSum * Math.random();

        // run a binary search to find the target zone
        int low = 0, high = this.prefixSums.length;
        while (low < high) {
            // better to avoid the overflow
            int mid = low + (high - low) / 2;
            if (target > this.prefixSums[mid])
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }
}

//2. mine 
class Solution {
    int[] prefixSums;
    int total = 0; 
    public Solution(int[] w) {
        prefixSums = new int[w.length];
        for (int i = 0; i < w.length; i++){
            if (i == 0) prefixSums[i] = w[i];
            else prefixSums[i] = w[i] + prefixSums[i - 1];
            //Or: total += w[i]; prefixSums[i] = total;
        }
        total = prefixSums[prefixSums.length - 1];
    }
    
    public int pickIndex() {
        Random rand = new Random();
        int pick = rand.nextInt(total);
        return binarySearch(pick);
    }
     
    public int binarySearch(int pick){
        int lo = 0;
        int hi = prefixSums.length;
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            if (mid == 0 && pick < prefixSums[mid] || (prefixSums[mid - 1] <= pick && prefixSums[mid] > pick)) return mid;
            else if (prefixSums[mid] < pick) {
                lo = mid + 1;
            }
            else{
                hi = mid - 1;
            }
        }
        return -1;
    }
}


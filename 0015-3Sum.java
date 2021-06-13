//1. Better. Two pinters Time Complexity: O(n^2), twoSumII is O(n), and we call it n times. 
// Space Complexity: from O(logn) to O(n), depending on the implementation of the sorting algorithm   

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len < 3) return new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < len; i++){
            if (nums[i] > 0) break;
            if (i >= 1 && nums[i] == nums[i - 1]) continue; //check for duplicates
            int lo = i + 1;
            int hi = len - 1;
            while (lo < hi){
                if (nums[lo] + nums[hi] == (-1) * nums[i]){
                    res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                    lo++;
                    hi--;
                    while (lo < hi && nums[lo] == nums[lo - 1]){     //check for duplicates again
                        lo++;
                    }
                }
                else if (nums[lo] + nums[hi] < (-1) * nums[i]){
                    lo++;
                }
                else {
                    hi--;
                }
            }
        }
        return res;
    }
}

//2. HashMap Time O(n^2) Space O(n)

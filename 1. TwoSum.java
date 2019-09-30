// --------------------------------------------
//1. use 2 for loops:
//HashMap: key: num[i], value: i(index)
//Runtime O(n), Space O(n)
//If brutal force : O(n^2)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hm = new HashMap <Integer, Integer>();
        for (int i=0; i < nums.length; i++){
            hm.put(nums[i],i);
        }
        int res[] = new int[2];
        for (int i = 0; i < nums.length; i++){
            int x = target - nums[i];
            if (hm.containsKey(x) && hm.get(x) != i) {
                res[0] = i;
                res[1] = hm.get(x);
                return res;
            }
        } 
        throw new IllegalArgumentException("No two sum solution");
            
    }
}
// --------------------------------------------
//2. use 1 for loop:
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hm = new HashMap <Integer, Integer>();
        for (int i=0; i < nums.length; i++){
            int x = target - nums[i];
            if (hm.containsKey(x)) {
                return new int[]{i,hm.get(x)};
            }
            hm.put(nums[i],i);
        }
        throw new IllegalArgumentException("No two sum solution");
            
    }
}

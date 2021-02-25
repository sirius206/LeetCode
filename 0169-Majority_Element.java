//1 Hashmap Time O(n) Space O(n)
class Solution {
    public int majorityElement(int[] nums) {
        HashMap <Integer, Integer> count = new HashMap <Integer, Integer>();
        int max_count = 0;
        int maj_el = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count.containsKey(nums[i])) {
                count.put(nums[i], count.get(nums[i]) + 1);
                }
            else {
                count.put(nums[i], 1);
            }
            if (count.get(nums[i]) > max_count) {
            max_count = count.get(nums[i]);
            maj_el = nums[i];
            }
        }
        return maj_el;
    }
}

//2 Moore Voting，需要 O(n) 的时间和 O(1) 的空间
public class Solution {
    public int majorityElement(int[] nums) {
        int res = 0, n = nums.length;
        for (int i = 0; i < 32; ++i) {
            int ones = 0, zeros = 0;
            for (int num : nums) {
                if (ones > n / 2 || zeros > n / 2) break;
                if ((num & (1 << i)) != 0) ++ones;
                else ++zeros;
            }
            if (ones > zeros) res |= (1 << i);
        }
        return res;
    }
}

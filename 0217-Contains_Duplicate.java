// Time O(n), Space O(n)
 
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set <Integer> exist = new HashSet<Integer>();
        for (int num : nums) {
            if (exist.contains(num)) {
                return true;
            } else {
                exist.add(num);
            }
        }
        return false;
    }
}

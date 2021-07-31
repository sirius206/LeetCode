//backtrack
// ?? Time O(sum Cnk), k = 1... n, n! <= Time <= n*n!, can say O(n * n!) or O(n)
// Time O(n * n!) n! permutations, Notice that at the end when adding the list to the result list, it takes O(n).
// Space O(n * n!) because n! solutions, each takes n

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        List<Integer> current = new ArrayList<>();
        helper(current, nums, res);
        return res;
    }
    
    public void helper(List<Integer> current, int[] nums, List<List<Integer>> res){
        if (current.size() == nums.length) {
            res.add(new ArrayList<Integer>(current));
            return;
        }
        for (int i = 0; i < nums.length; i++){
            if (current.contains(nums[i])) continue;
            else {
                current.add(nums[i]);
                helper(current, nums, res);
                current.remove(current.size() - 1);
            }
        }
    }
}

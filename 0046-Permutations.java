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

// 2. answer, don't understand
class Solution {
  public void backtrack(int n,
                        ArrayList<Integer> nums,
                        List<List<Integer>> output,
                        int first) {
    // if all integers are used up
    if (first == n)
      output.add(new ArrayList<Integer>(nums));
    for (int i = first; i < n; i++) {
      // place i-th integer first 
      // in the current permutation
      Collections.swap(nums, first, i);
      // use next integers to complete the permutations
      backtrack(n, nums, output, first + 1);
      // backtrack
      Collections.swap(nums, first, i);
    }
  }

  public List<List<Integer>> permute(int[] nums) {
    // init output list
    List<List<Integer>> output = new LinkedList();

    // convert nums into list since the output is a list of lists
    ArrayList<Integer> nums_lst = new ArrayList<Integer>();
    for (int num : nums)
      nums_lst.add(num);

    int n = nums.length;
    backtrack(n, nums_lst, output, 0);
    return output;
  }
}

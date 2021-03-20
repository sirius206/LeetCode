//1. DFS, Backtracking Time O(kCNk)where C_N^k = N!/((N - k)! k!) Space O(kCNk)
 
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        helper(1, n, k, current, res);
        return res;
         
    }
    
    private void helper(int start, int n, int k, List<Integer> current, List<List<Integer>> res){
        if (current.size() == k) {
            res.add(new ArrayList<Integer>(current));
        }
        
        for (int i = start; i < n + 1; i++) {
            if (current.size() < k) {
                current.add(i);
                helper(i + 1, n, k, current, res);
                current.remove(current.size() - 1);                
            }

        }
    }
}


//2 Lexicographic, (binary sorted) combinations Time O(kCNk)where C_N^k = N!/((N - k)! k!) Space O(kCNk)
class Solution {
  public List<List<Integer>> combine(int n, int k) {
    // init first combination
    LinkedList<Integer> nums = new LinkedList<Integer>();
    for(int i = 1; i < k + 1; ++i)
      nums.add(i);
    nums.add(n + 1);

    List<List<Integer>> output = new ArrayList<List<Integer>>();
    int j = 0;
    while (j < k) {
      // add current combination
      output.add(new LinkedList(nums.subList(0, k)));
      // increase first nums[j] by one
      // if nums[j] + 1 != nums[j + 1]
      j = 0;
      while ((j < k) && (nums.get(j + 1) == nums.get(j) + 1))
        nums.set(j, j++ + 1);
      nums.set(j, nums.get(j) + 1);
    }
    return output;
  }
}

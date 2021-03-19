
//1. recursion, DFS, Time O(n * 2^n)(need O(n) to store ), Space: O(n)
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        List<Integer> current = new ArrayList<>();
        int len = nums.length;
        helper(0, res, current, nums);
        
        return res;
    }
    
    public void helper(int i, 
                       List<List<Integer>> res, 
                       List<Integer> current, 
                       int[] nums) {
        
        if (i == nums.length) {
            res.add(new ArrayList<Integer>(current));
            return;
        }
        
        helper(i + 1, res, current, nums);
        
        current.add(nums[i]);
        helper(i + 1, res, current, nums);
        current.remove(current.size() - 1);
    }
}

//2. Backtracking Time O(n * 2^n), Space: O(n)
//生成含0个， 1个， 。。。 n个的组合
class Solution {
  List<List<Integer>> output = new ArrayList();
  int n, k;

  public void backtrack(int first, ArrayList<Integer> curr, int[] nums) {
    // if the combination is done
    if (curr.size() == k) {
      output.add(new ArrayList(curr));
      return;
    }
    for (int i = first; i < n; ++i) {
      // add i into the current combination
      curr.add(nums[i]);
      // use next integers to complete the combination
      backtrack(i + 1, curr, nums);
      // backtrack
      curr.remove(curr.size() - 1);
    }
  }

  public List<List<Integer>> subsets(int[] nums) {
    n = nums.length;
    for (k = 0; k < n + 1; ++k) {
      backtrack(0, new ArrayList<Integer>(), nums);
    }
    return output;
  }
}


//3. Cascading Time O(n * 2^n), Space: O(n * 2^n)
//在原来output基础上 下一个元素加或不加， 每次output size * 2
class Solution {
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> output = new ArrayList();
    output.add(new ArrayList<Integer>());

    for (int num : nums) {
      List<List<Integer>> newSubsets = new ArrayList();
      for (List<Integer> curr : output) {
        newSubsets.add(new ArrayList<Integer>(curr){{add(num);}});
      }
      for (List<Integer> curr : newSubsets) {
        output.add(curr);
      }
    }
    return output;
  }
}


//4. Bitmask (Lexicographic) (Binary Sorted) Subsets Time O(n * 2^n), Space: O(n * 2^n)
// bitmask 0..00 (all zeros) corresponds to an empty subset, and the bitmask 1..11 (all ones) corresponds to the entire input array nums
class Solution {
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> output = new ArrayList();
    int n = nums.length;

    for (int i = (int)Math.pow(2, n); i < (int)Math.pow(2, n + 1); ++i) {
      // generate bitmask, from 0..00 to 1..11, shift, so 10000 becomes 0000
      String bitmask = Integer.toBinaryString(i).substring(1);

      // append subset corresponding to that bitmask
      List<Integer> curr = new ArrayList();
      for (int j = 0; j < n; ++j) {
        if (bitmask.charAt(j) == '1') curr.add(nums[j]);
      }
      output.add(curr);
    }
    return output;
  }
}

//1. Backtracking Time O(P(9,K)) = O(9!/(9-K)!) Space O(k) to keep the current combination, also another k for recursion
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>(); 
        List<Integer> current = new ArrayList<>();
        helper(n, 1, k, current, res);
        return res;
    }
    
    private void helper(int remain, int start, int k, List<Integer> current, 
                        List<List<Integer>> res){
        if (remain == 0 && current.size() == k) {
            res.add(new ArrayList<Integer>(current));
        }
        for (int i = start; i < 10; i++) {
            if (i > remain) break;
            current.add(i);
            helper(remain - i, i + 1, k, current, res);
            current.remove(current.size() - 1);
        }                
    }
}

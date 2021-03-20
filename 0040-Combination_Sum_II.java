
//1. DFS, Recursion DFS, O(n * 2^n), Space: O(n)
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>(); 
        if (candidates == null || candidates.length == 0) return res;
        Arrays.sort(candidates);
        List<Integer> current = new ArrayList<>();
        helper(target, 0, current, candidates , res);
        return res;
    }
    
    private void helper(int remain, int start, List<Integer> current, 
                        int[] candidates, List<List<Integer>> res){
        if (remain == 0) {
            res.add(new ArrayList<Integer>(current));
        }
        int len = candidates.length;
        for (int i = start; i < len; i++) {
            if (candidates[i] > remain) break;
            if (i > start && candidates[i - 1] == candidates[i]) continue; // see 90 subset sum II
            current.add(candidates[i]);
            helper(remain - candidates[i], i + 1, current, candidates, res);
            current.remove(current.size() - 1);
        }        
    }
}

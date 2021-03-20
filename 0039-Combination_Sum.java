
//1. DFS, Backtracking Time O(n^(T/M + 1)), T is target, M is min. Use N-ary tree for backtracking.  Space: O(T/M) number of recursive calls can pile up to T/M , 
//also using O(N)space to maintain curr, and are modifying curr in-place with backtracking. 
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
            current.add(candidates[i]);
            helper(remain - candidates[i], i, current, candidates, res);
            current.remove(current.size() - 1);
        }
    }
}

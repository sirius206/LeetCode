//1. Backtracking with memo 
// Time: O(n⋅2^n) 2^n possible substrings and O(n) to generate substring, Space: O(n^2)

class Solution {
    List<List<String>> res = new ArrayList<List<String>>();
    public List<List<String>> partition(String s) {
        Boolean[][] memo = new Boolean[s.length()][s.length()];
        List<String> current = new ArrayList<>();
        helper(0, current, s, memo);
        return res;
    }

    private void helper(int start, List<String> current, String s, Boolean[][] memo){
        if (start == s.length()) {
            res.add(new ArrayList<String>(current));
        }
        
        for (int end = start; end < s.length(); end++){
            if (memo[start][end] == null){
                if (isPalindrome(s, start, end, memo)){
                    memo[start][end] = true;
                }
                else memo[start][end] = false;
            }
            if (memo[start][end]){
                current.add(s.substring(start, end + 1));
                helper(end + 1, current, s, memo);
                current.remove(current.size() - 1);
            }
        }
    }
    

    private boolean isPalindrome(String s, int lo, int hi, Boolean[][] memo){
        if (hi - lo == 0) return true;
        if (memo[lo][hi] != null) {
            if (memo[lo][hi] == true) {
                return true;
            }
        }
        while(lo < hi){
            if (s.charAt(lo) != s.charAt(hi)){
                return false;
            }
            if (s.charAt(lo) == s.charAt(hi)){
                if (hi - lo >= 2 && memo[lo + 1][hi - 1] == true)
                return true;
            }            
            lo++;
            hi--;
        }
        return true;
    }
}

//2. Backtracking with DP
class Solution {
    public List<List<String>> partition(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        List<List<String>> result = new ArrayList<>();
        dfs(result, s, 0, new ArrayList<>(), dp);
        return result;
    }

    void dfs(List<List<String>> result, String s, int start, List<String> currentList, boolean[][] dp) {
        if (start >= s.length()) result.add(new ArrayList<>(currentList));
        for (int end = start; end < s.length(); end++) {
            if (s.charAt(start) == s.charAt(end) && (end - start <= 2 || dp[start + 1][end - 1])) {
                dp[start][end] = true;
                currentList.add(s.substring(start, end + 1));
                dfs(result, s, end + 1, currentList, dp);
                currentList.remove(currentList.size() - 1);
            }
        }
    }
}

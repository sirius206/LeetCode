// DP, twice dp Time O(n^2) Space O(n^2)
class Solution {
    public int minCut(String s) {
        int len = s.length();
        if (len == 0) return 0;
        boolean[][] p = new boolean[len][len];
        int[] dp = new int[len];
        for (int i = 0; i < len; ++i) {
            dp[i] = i;
            for (int j = 0; j <= i; ++j) {
                if (s.charAt(i) == s.charAt(j) && (i - j < 2 || p[j + 1][i - 1])) {
                    p[j][i] = true;
                    dp[i] = (j == 0) ? 0 : Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }
        return dp[len - 1];
    }
}

// Backtracking with memo, excceed time limit 
class Solution {
    int min = Integer.MAX_VALUE;
    public int minCut(String s) {
        Boolean[][] memo = new Boolean[s.length()][s.length()];
        List<String> current = new ArrayList<>();
        helper(0, current, s, memo);
        return min;
    }

    private void helper(int start, List<String> current, String s, Boolean[][] memo){
        if (start == s.length()) {
            min = Math.min(min, current.size() - 1);
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

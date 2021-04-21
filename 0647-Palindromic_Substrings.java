//1. dp Time O(n^2) Space O(n^2)
class Solution {
    public int countSubstrings(String s) {
        int count = 0;
        int len = s.length();
        if (len <= 1) return len;
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++){
            for (int j = 0; j <= i; j++){
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                    count++;                    
                }
            }
        }
        return count;
    }
}


//2. Expand Around Possible Centers Time O(n^2) Space O(1)
class Solution {
    public int countSubstrings(String s) {
        int ans = 0;

        for (int i = 0; i < s.length(); ++i) {
            // odd-length palindromes, single character center
            ans += countPalindromesAroundCenter(s, i, i);

            // even-length palindromes, consecutive characters center
            ans += countPalindromesAroundCenter(s, i, i + 1);
        }

        return ans;
    }

    private int countPalindromesAroundCenter(String ss, int lo, int hi) {
        int ans = 0;

        while (lo >= 0 && hi < ss.length()) {
            if (ss.charAt(lo) != ss.charAt(hi))
                break;      // the first and last characters don't match!

            // expand around the center
            lo--;
            hi++;

            ans++;
        }

        return ans;
    }
}

//recursion + dp, time limit exceeded Time O(n^3) Space: O(n^2)

class Solution {
    int count = 0;
    public int countSubstrings(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        dfs(s, 0, dp);
        return count;
    }

    void dfs(String s, int start, boolean[][] dp) {
        if (start == s.length()) return;
        for (int end = start; end < s.length(); end++) {
            if (s.charAt(start) == s.charAt(end) && (end - start <= 2 || dp[start + 1][end - 1])) {
                if (dp[start][end] == false) {
                    dp[start][end] = true;
                    count++;
                }
                dfs(s, end + 1, dp);
            }
        }
    }
}

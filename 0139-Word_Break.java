
//1. recursion with memory Time: O(n^3) Space: O(n). 
//Size of recursion tree can go up to n^2, The depth of recursion tree can go up to n
// memo[i] is true if i ~ n is substring
public class Solution {
    private Map<Integer, Integer> memo = new HashMap<>();
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        for (int i = 0; i < len; i++){
            memo.put(i, -1);
        }
        return wordBreakRecur(s, new HashSet<>(wordDict), 0);
    }

    private boolean wordBreakRecur(String s, Set<String> wordDict, int start) {
        if (start == s.length()) {
            return true;
        }
        if (memo.get(start) != -1) return memo.get(start) == 1;
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && wordBreakRecur(s, wordDict, end)) {
                memo.put(start, 1);
                return true;
            }
        }            
        memo.put(start, 0);
        return false;
    }
}
//1b recursion with memory Time: O(n^3) Space: O(n) 
// use Boolean, initialize to null, if boolean initialize to false
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        return wordBreakMemo(s, new HashSet<>(wordDict), 0, new Boolean[s.length()]);
    }

    private boolean wordBreakMemo(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && wordBreakMemo(s, wordDict, end, memo)) {
                return memo[start] = true;
            }
        }
        return memo[start] = false;
    }
}
//1c  recursion Time: O(2^n) Space: O(n) 
//Given a string of length nn, there are n + 1n+1 ways to split it into two parts. At each step, we have a choice: to split or not to split. In the worse case, when all choices are to be checked
//Time Limit Exceeded
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        return wordBreakRecur(s, new HashSet<>(wordDict), 0);
    }

    private boolean wordBreakRecur(String s, Set<String> wordDict, int start) {
        if (start == s.length()) {
            return true;
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && wordBreakRecur(s, wordDict, end)) {
                return true;
            }
        }
        return false;
    }
}

//2. DP Time: O(n^3) Space: O(n) from end
//There are two nested loops, and substring computation at each iteration so O(n^3)
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        HashSet<String> wordSet = new HashSet<>(wordDict);
        dp[len] = true;
        for (int i = len - 1; i >= 0; i--){
            for (int j = i + 1; j <= len; j++){
                if (wordSet.contains(s.substring(i,j)) && dp[j]){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }
//2b from start
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}

//3. BFS Time: O(n^3) Space: O(n) 
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[s.length()];
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.remove();
            if (visited[start]) {
                continue;
            }
            for (int end = start + 1; end <= s.length(); end++) {
                if (wordDictSet.contains(s.substring(start, end))) {
                    queue.add(end);
                    if (end == s.length()) {
                        return true;
                    }
                }
            }
            visited[start] = true;
        }
        return false;
    }
}









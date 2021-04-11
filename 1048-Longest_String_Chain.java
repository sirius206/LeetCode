//1. DP use hashmap DP Time:O(nlogn) for sorting, Time O(NSS) for the for loop,  Space:O(nS)

class Solution {
    public int longestStrChain(String[] words) {
        Map<String, Integer> dp = new HashMap<>();
        Arrays.sort(words, (a, b)->a.length() - b.length());
        int res = 0;
        for (String word : words) {
            int best = 0;
            for (int i = 0; i < word.length(); ++i) {
                String prev = word.substring(0, i) + word.substring(i + 1);
                best = Math.max(best, dp.getOrDefault(prev, 0) + 1);
            }
            dp.put(word, best);
            res = Math.max(res, best);
        }
        return res;
    }
}
//2. DP use array
class Solution {
    public int longestStrChain(String[] words) {
        int len = words.length;
        Arrays.sort(words, (a,b) -> a.length() - b.length());
        int[] dp = new int[len];
        int res = 1;
        for (int i = 0; i < len; i++) {
            String word = words[i];
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                String prev = words[j];
                if (prev.length() + 1 < word.length()) break;
                if (prev.length() == word.length()) continue;
                int n = 0; 
                for (int k = 0; k < word.length(); k++) {
                    if (prev.equals(word.substring(0, k) + word.substring(k + 1))){
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                        res = Math.max(res, dp[i]);
                    }
                }
            }
        }
        return res;
    }
}

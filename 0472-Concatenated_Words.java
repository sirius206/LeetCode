//1. dp, call 139 word break 
// Time O(N * L^3) Space O(L) N = Number of Words L = Highest length of word
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        Set<String> wordsSet = new HashSet<>();
        List<String> res = new ArrayList<>();
        wordsSet.add(words[0]);
        for (int i = 1; i < words.length; i++){
            if (wordBreak(words[i], wordsSet)){
                res.add(words[i]);
            }
            wordsSet.add(words[i]);
        }
        return res;
    }
    
    public boolean wordBreak(String s, Set<String> wordDictSet) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++){
            for (int j = 0; j < i; j++){
                if (dp[j] && wordDictSet.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}




//2. Time limit exceed, Time O(2^n)
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words, (a,b) -> a.length() - b.length());
        List<String> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++){
            if (!words[i].equals("")){
                 dfs(words, words[i], i, res);
            }
        }
        return res;
    }
    public void dfs(String[] words, String word, int i, List<String> res){
        if (word.length() == 0) {
            if (!res.contains(words[i])){
                res.add(words[i]);
            }
            return;
        }        
        for (int j = 0; j < i; j++){
            int idx = word.indexOf(words[j]);
            if (idx != -1){
                String newWord = word.substring(0, idx) + word.substring(idx + words[j].length());
                dfs(words, newWord, i, res);
            }
        }
    }
}

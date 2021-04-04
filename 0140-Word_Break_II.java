// DFS Backtracking Time: O(2^n) Space: O(2^n * n) 
class Solution {
    private List<String> res = new LinkedList<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> wordSet = new HashSet<>(wordDict);
        helper(s, 0, wordSet, "");
        return res;
    }
    
    public void helper(String s, int start, HashSet<String> wordSet, String current){
        if (start == s.length()) {
            res.add(current.substring(0, current.length() - 1));
        }
        
        for (int end = start + 1; end <= s.length(); end++){
            if(wordSet.contains(s.substring(start, end))){
                current = current + (s.substring(start, end)) + " ";
                helper(s, end, wordSet, current);
                current = current.substring(0, current.length() - (end - start + 1));
            }
        }
    }
}

//DFS with memo
//C++
class Solution {
public:
    vector<string> wordBreak(string s, vector<string>& wordDict) {
        unordered_map<string, vector<string>> m;
        return helper(s, wordDict, m);
    }
    vector<string> helper(string s, vector<string>& wordDict, unordered_map<string, vector<string>>& m) {
        if (m.count(s)) return m[s];
        if (s.empty()) return {""};
        vector<string> res;
        for (string word : wordDict) {
            if (s.substr(0, word.size()) != word) continue;
            vector<string> rem = helper(s.substr(word.size()), wordDict, m);
            for (string str : rem) {
                res.push_back(word + (str.empty() ? "" : " ") + str);
            }
        }
        return m[s] = res;
    }
};

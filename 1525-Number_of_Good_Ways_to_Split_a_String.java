// Time O(n), Space O(1)
// prefix and suffix
class Solution {
    public int numSplits(String s) {
        Map<Character, Integer> prefMap = new HashMap<>();
        Map<Character, Integer> suffMap = new HashMap<>();
        
        int[] pref = new int[s.length()];
        int[] suff = new int[s.length()];
        
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            prefMap.put(c, prefMap.getOrDefault(c, 0) + 1);     
            pref[i] = prefMap.size();
                
            char c_r = s.charAt(s.length() - 1 - i);
            suffMap.put(c_r, suffMap.getOrDefault(c_r, 0) + 1);     
            suff[s.length() - 1 - i] = suffMap.size();
        }
        int res = 0; 
        for (int i = 0; i < s.length() - 1; i++){
            if (pref[i] == suff[i + 1]) res++;
        }
        return res;    
    }
}

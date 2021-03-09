//Time O(n) Space O(1)
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] count = new int[26];
        int len = s.length();
        for (int i = 0; i < len; i++){
            int idx1 = s.charAt(i) - 'a';
            count[idx1]++;
            int idx2 = t.charAt(i) - 'a';
            count[idx2]--;
        }
        for (int j = 0; j < 26; j++) {
            if (count[j] != 0) return false;
        }
        return true;
    }
}

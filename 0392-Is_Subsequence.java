// Time O(T) Space O(1)
class Solution {
    public boolean isSubsequence(String s, String t) {
        int i = 0;
        int j = 0;
        int lens = s.length();
        int lent = t.length();
        while (i < lens && j < lent) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            }
            else {
                j++;
            }
        }
        return i == lens;
    }
}

// Time O(n), Space O(1)

class Solution {
    public boolean validPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r ) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                return isValid(s, l + 1, r) || isValid(s, l, r - 1);
                }
            }
        return true;
        }
    
    public boolean isValid(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }    
}

//
//Method 1: two pointers
//run time: O(n)
class Solution {
    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        int len = s.length();
        int l = 0;
        int r = len -1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }
}

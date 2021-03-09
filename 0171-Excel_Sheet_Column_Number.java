//1. right to left, Time O(n) Space O(1)
class Solution {
    public int titleToNumber(String columnTitle) {
        int res = 0;
        int len = columnTitle.length();
        for (int i = 0; i < len; i++) {
            char ch = columnTitle.charAt(len - i - 1);
            res += Math.pow(26, i) * (ch - 'A' + 1);
        }
        return res;
    }
}

//2: Left to Right Time O(n) Space O(1)
class Solution {
    public int titleToNumber(String s) {
        int result = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            result = result * 26;
            // In Java, subtracting characters is subtracting ASCII values of characters
            result += (s.charAt(i) - 'A' + 1);
        }
        return result;
    }
}

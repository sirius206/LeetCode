//
//Method 1 by my self, compares the two digits from each end
//slow
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x < 10) return true;
        int numdigit = 0;
        int y = x;
        while (y > 0) {
            y = y /10;
            numdigit++;
        }
        for (int i = 1; i <= numdigit/2; i++) {
            int a = (int)(x / Math.pow(10, (numdigit - i))) - (int)(x / Math.pow(10, (numdigit - i +1))) * 10;
            int m = (int)(x % Math.pow(10, i));
            int n = (int)(x % Math.pow(10, i - 1));
            int b = (m - n ) / ((int) Math.pow(10, i - 1));
            if (a != b)
                return false;
        }
        return true;
    }
}


//Method 2 from book, compare two ends, then chop the two ends
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x < 10) return true;
        int div = 1;
        while (x / div >= 10) {
            div = div * 10;
        }
        while (x > 0) {
            if (x / div != x % 10) {
                return false;
            }
            else {
                x = (x % div) / 10;
                div = div / 100;
            }
        }
        return true;
    }
}



//Method 3: two pointers 
//Need extra space
//run time: O(n) Space: o(logn)
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

// Time O(max(m,n)), Space O(max(m,n)) to keep the answer

class Solution {
    public String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        int x = 0, y = 0;
        int value = 0;
        int carry = 0;
        String res = "";
        while (i >= 0 || j >=0) {
            if (i < 0) {x = 0; }
            else {
                x = Integer.valueOf(a.charAt(i) - '0');  // important to - '0'
            }
            if (j < 0) {y = 0; }
            else {
                y = Integer.valueOf(b.charAt(j) - '0');
            }
            value = x + y + carry;
            carry = value / 2;
            value = value % 2;
            res = Integer.toString(value) + res;
            i--;
            j--;
        }
        if (carry == 1) res = "1" + res;
        return res;
    }
}

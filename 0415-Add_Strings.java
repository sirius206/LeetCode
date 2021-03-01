
// Time O(m+n) Space O(1)
class Solution {
    public String addStrings(String num1, String num2) {
        int sum = 0;
        int carry = 0;
        int value = 0;
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int a = 0;
        int b = 0;
        String res = "";
        while (i >= 0 || j >= 0) {
            if (i >= 0) a = num1.charAt(i) - '0';
            else a = 0;
            if (j >= 0) b = num2.charAt(j) - '0';
            else b = 0;            
            sum = a + b + carry;
            carry = sum / 10;
            value = sum % 10;
            res = value + res;
            i--;
            j--;
        }
        if  (carry == 1) res = "1" + res;
        return res;
        
    }
}

//2 
class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder();

        int carry = 0;
        int p1 = num1.length() - 1;
        int p2 = num2.length() - 1;
        while (p1 >= 0 || p2 >= 0) {
            int x1 = p1 >= 0 ? num1.charAt(p1) - '0' : 0;
            int x2 = p2 >= 0 ? num2.charAt(p2) - '0' : 0;
            int value = (x1 + x2 + carry) % 10;
            carry = (x1 + x2 + carry) / 10;
            res.append(value);
            p1--;
            p2--;    
        }
        
        if (carry != 0)
            res.append(carry);
        
        return res.reverse().toString();
    }
}

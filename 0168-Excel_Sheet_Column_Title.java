//Time O(log26n) Space O(1)
//1. n--  because 'A' + 25 = 'Z'
class Solution {
    public String convertToTitle(int columnNumber) {
        String res = "";
        int n = columnNumber;
        char ch;
        while (n != 0) {
            n--;
            int val = n % 26;
            ch = (char)('A' + val);
            res = ch + res;
            n = n / 26;
            
        }
        return res;
    }
}

//2. Special case Z because 'A' - 1 != 'Z'

class Solution {
    public String convertToTitle(int columnNumber) {
        String res = "";
        int n = columnNumber;
        char ch;
        while (n != 0) {
            int val = n % 26;
            if (val == 0) {
                ch = 'Z';
                n = (n - 26) / 26;
            }
            else {
                ch = (char)('A' + val - 1);
                n = n / 26;
            }
            res = ch + res;
        }
        return res;
    }
}



//3 递归
class Solution {
public:
    string convertToTitle(int n) {
        return n == 0 ? "" : convertToTitle(n / 26) + (char)(--n % 26 + 'A');
    }
};

//Method 1 by myself
//Runtime: O(N) Space: O(1) in place
class Solution {
    public void reverseWords(char[] s) {
        int len = s.length;
        reverse(s, 0, len);
        int l = 0;
        int r;
        for (int i = 0; i < len; i++) {
            if (i == 0)  l = i;
            else if (s[i-1] == ' ') l = i;
            if (s[i] != ' '){
                continue;
            }
            r = i;
            reverse(s, l, r);
        }
    }
    public void reverse(char[] s, int l, int r) {
        char temp;
        int j = r;
        for (int i = l; i < (l+r)/2; i++) {
            temp = s[i];
            s[i] = s[j-1];
            s[j-1] = temp;
            j--;
        }
    }
}

//Method 2: LC all in One
class Solution {
public:
    void reverseWords(vector<char>& str) {
        int left = 0, n = str.size();
        for (int i = 0; i <= n; ++i) {
            if (i == n || str[i] == ' ') {
                reverse(str, left, i - 1);
                left = i + 1;
            }
        }
        reverse(str, 0, n - 1);
    }
    void reverse(vector<char>& str, int left, int right) {
        while (left < right) {
            char t = str[left];
            str[left] = str[right];
            str[right] = t;
            ++left; --right;
        }
    }
};

//Method 3: Book
class Solution {
    public void reverseWords(char[] s) {
        reverse(s, 0, s.length);
        for (int i = 0, j = 0; j <= s.length; j++) {
            if (j == s.length || s[j] == ' ') {
                reverse(s, i, j);
                i = j + 1;
            }
        }
    }

    private void reverse(char[] s, int begin, int end) {
        for (int i = 0; i < (end - begin) / 2; i++) {
            char temp = s[begin + i];
            s[begin + i] = s[end - i - 1];
            s[end - i - 1] = temp;
        }
    }
}

//1. DP 2D array Time:O(n^2) Space:O(n^2)
class Solution {
    public String longestPalindrome(String s) {
        if (s.equals("")) return "";
        int len = s.length();
        int[][] f = new int[len][len];
        int max = 1;
        int st = 0;
        int end = 0;
      //base case: 对角线 f[i,i]和f[i, i+ 1]
        for (int i = 0; i < len; i++){
            f[i][i] = 1;
            if (i + 1 < len){
                if (s.charAt(i) == s.charAt(i + 1)){
                    f[i][i + 1] = 2;
                    if (f[i][i + 1] > max){
                        st = i;
                        end = i + 1;
                    }
                }
            }
        }
        for (int k = 2; k< len; k++){
            for (int i = 0; i < len - k; i++){
                int j = i + k;
                if (s.charAt(i) == s.charAt(j) && (f[i + 1][j - 1] != 0)) {
                    f[i][j] = f[i + 1][j - 1] + 2;
                }
                else f[i][j] = 0;
                if (f[i][j] > max){
                    st = i;
                    end = j;
                }
            }
        }
        return s.substring(st, end + 1);
    }
}

//C++ DP
class Solution {
public:
    string longestPalindrome(string s) {
        if (s.empty()) return "";
        int n = s.size(), dp[n][n] = {0}, left = 0, len = 1;
        for (int i = 0; i < n; ++i) {
            dp[i][i] = 1;
            for (int j = 0; j < i; ++j) {
                dp[j][i] = (s[i] == s[j] && (i - j < 2 || dp[j + 1][i - 1]));
                if (dp[j][i] && len < i - j + 1) {
                    len = i - j + 1;
                    left = j;
                }
            }
        }
        return s.substr(left, len);
    }
};

//2.对于奇数形式的，我们就从遍历到的位置为中心，向两边进行扩散，对于偶数情况，我们就把当前位置和下一个位置当作偶数行回文的最中间两个字符，然后向两边进行搜索
//C++ Time:O(n^2) Space:O(1)
class Solution {
public:
    string longestPalindrome(string s) {
        if (s.size() < 2) return s;
        int n = s.size(), maxLen = 0, start = 0;
        for (int i = 0; i < n - 1; ++i) {
            searchPalindrome(s, i, i, start, maxLen);
            searchPalindrome(s, i, i + 1, start, maxLen);
        }
        return s.substr(start, maxLen);
    }
    void searchPalindrome(string s, int left, int right, int& start, int& maxLen) {
        while (left >= 0 && right < s.size() && s[left] == s[right]) {
            --left; ++right;
        }
        if (maxLen < right - left - 1) {
            start = left + 1;
            maxLen = right - left - 1;
        }
    }
};

//3. Answer Expand Around Center Time:O(n^2) Space:O(1)
public String longestPalindrome(String s) {
    if (s == null || s.length() < 1) return "";
    int start = 0, end = 0;
    for (int i = 0; i < s.length(); i++) {
        int len1 = expandAroundCenter(s, i, i);
        int len2 = expandAroundCenter(s, i, i + 1);
        int len = Math.max(len1, len2);
        if (len > end - start) {
            start = i - (len - 1) / 2;
            end = i + len / 2;
        }
    }
    return s.substring(start, end + 1);
}

private int expandAroundCenter(String s, int left, int right) {
    int L = left, R = right;
    while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
        L--;
        R++;
    }
    return R - L - 1;
}

//4. Manacher's Algorithm O(n)
class Solution {
public:
    string longestPalindrome(string s) {
        string t ="$#";
        for (int i = 0; i < s.size(); ++i) {
            t += s[i];
            t += '#';
        }
        int p[t.size()] = {0}, id = 0, mx = 0, resId = 0, resMx = 0;
        for (int i = 1; i < t.size(); ++i) {
            p[i] = mx > i ? min(p[2 * id - i], mx - i) : 1;
            while (t[i + p[i]] == t[i - p[i]]) ++p[i];
            if (mx < i + p[i]) {
                mx = i + p[i];
                id = i;
            }
            if (resMx < p[i]) {
                resMx = p[i];
                resId = i;
            }
        }
        return s.substr((resId - resMx) / 2, resMx - 1);
    }
};

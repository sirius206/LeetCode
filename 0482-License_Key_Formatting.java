//1. use string, slow, Time O(n) Space O(n)
class Solution {
    public String licenseKeyFormatting(String s, int k) {
        String res = "";
        s = s.replace("-", "").toUpperCase();
        int i = s.length() - 1;
        for (i = s.length() - 1; i - k + 1 > 0; i -= k){
            res = "-" + s.substring(i - k + 1, i + 1) + res;
        }
        res = s.substring(0, i + 1) + res;
        return res;
    }
}


//2 better
 public String licenseKeyFormatting(String S, int K) {
    S = S.replaceAll("-", "").toUpperCase();
    StringBuilder sb = new StringBuilder(S);
    // Starting from the end of sb, and going backwards. 
    int i = sb.length() - K;
    while(i > 0) {
        sb.insert(i, '-');
        i = i - K;
    }
    return sb.toString();
}

//3
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--)
            if (s.charAt(i) != '-')
                sb.append(sb.length() % (k + 1) == k ? '-' : "").append(s.charAt(i));
        return sb.reverse().toString().toUpperCase();
    } 

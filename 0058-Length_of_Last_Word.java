//1. Time O(n) Space O(1)

class Solution {
    public int lengthOfLastWord(String s) {
        int len = s.length();
        if (len == 0) return 0;
        int res = 0;
        for (int i = len - 1; i >= 0; i--){
            if (s.charAt(i) == ' '){
                if (res > 0) break;  //防止最后一位是空格
            }
            else res++;
        }
        return res;
    }
}

//2. to array, Time O(n) Space O(n)
class Solution {
    public int lengthOfLastWord(String s) {
        String[] words = s.split(" ");
        if (words.length == 0) return 0;
        String last = words[words.length - 1];
        return last.length();
    }
}

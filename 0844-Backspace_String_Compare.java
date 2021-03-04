//1. Stack Time O(m+n), Space O(m+n)
class Solution {
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> s = new Stack<>();
        Stack<Character> t = new Stack<>();
        for (int i = 0; i < S.length(); i++){
            if (S.charAt(i) != '#'){ 
                s.push(S.charAt(i)); 
            }
            else if (!s.isEmpty()) {
                s.pop();
                }
        }
        for (int j = 0; j < T.length(); j++){
            if (T.charAt(j) == '#'){ 
                t.push(T.charAt(j));
            }
            else if (!t.isEmpty()) {
                t.pop();
                }    
        }
      // or use S = String.valueOf(s);
        if (s.size() != t.size()) return false;    
        while (!s.isEmpty()){
            if (s.pop() != t.pop()) return false;
        }
        return true;
    }
}

//2. Two pointers from the back, Time O(m+n), Space O(1)
class Solution {
    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        int skipS = 0, skipT = 0;

        while (i >= 0 || j >= 0) { // While there may be chars in build(S) or build (T)
            while (i >= 0) { // Find position of next possible char in build(S)
                if (S.charAt(i) == '#') {skipS++; i--;}
                else if (skipS > 0) {skipS--; i--;}
                else break;
            }
            while (j >= 0) { // Find position of next possible char in build(T)
                if (T.charAt(j) == '#') {skipT++; j--;}
                else if (skipT > 0) {skipT--; j--;}
                else break;
            }
            // If two actual characters are different
            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j))
                return false;
            // If expecting to compare char vs nothing
            if ((i >= 0) != (j >= 0))
                return false;
            i--; j--;
        }
        return true;
    }
}

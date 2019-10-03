//Method 1 by myself
//Runtime: O(n), Space: O(n)
class Solution {
    public String reverseWords(String s) {
        String rString = "";
        int len = s.length();
        String word = "";
        boolean spaceFlag = false;
        boolean firstWord = true;
        for (int i = len-1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                spaceFlag = true;
                word = word + s.charAt(i);
                if (i != 0) {
                    continue;
                }
            }
            if (spaceFlag != false || i == 0) {
                if (!firstWord && !word.equals("")) {
                    rString = rString + " ";
                }
                else {
                    firstWord = false;
                }
                for (int j = word.length() - 1; j >= 0; j--) {
                    rString += word.charAt(j);
                }
                word = "";
                spaceFlag = false;
            }
        }
        return rString;
    }
}

//Method 2 

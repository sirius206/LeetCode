//stack Time O(n), Space O(n - D) where D is a total length for all duplicates.

class Solution {
    public String removeDuplicates(String S) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            if (stack.isEmpty()) stack.push(S.charAt(i)); 
            else 
                if (S.charAt(i) != stack.peek()) stack.push(S.charAt(i));
                else stack.pop();
        }
        String res = "";
        while (!stack.isEmpty()){
            res = stack.pop() + res;
        }
        return res;
    }
}

//stack 2, String builder 
class Solution {
  public String removeDuplicates(String S) {
    StringBuilder sb = new StringBuilder();
    int sbLength = 0;
    for(char character : S.toCharArray()) {
      if (sbLength != 0 && character == sb.charAt(sbLength - 1))
        sb.deleteCharAt(sbLength-- - 1);
      else {
        sb.append(character);
        sbLength++;
      }
    }
    return sb.toString();
  }
}
//2. replace Time O(n^2), Space O(n)

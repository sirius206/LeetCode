//Two pointers, Time O(n), Space O(n)
class Solution {
    public String reverseVowels(String s) {
        int i = 0;
        int j = s.length() - 1;
        char[] chars = s.toCharArray();     //can't change string? can't use s.charAt(i) = s.charAt(j)
        while (i < j) {
            if(("AEIOUaeiou".indexOf(chars[i]) != -1) && ("AEIOUaeiou".indexOf(chars[j]) != -1)){
            char temp = chars[i]; 
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
            }
          
          //or use 
          //String vowels = "aeiouAEIOU"; 
          //vowels.contains(chars[i]) 
            else if (("AEIOUaeiou".indexOf(chars[i]) != -1) && ("AEIOUaeiou".indexOf(chars[j]) == -1)){
                j--;
            }
            else if (("AEIOUaeiou".indexOf(chars[i]) == -1) && ("AEIOUaeiou".indexOf(chars[j]) != -1)){
                i++;
            }
            else {
                i++;
                j--;
            }
        }
        return String.valueOf(chars); 
    }
}


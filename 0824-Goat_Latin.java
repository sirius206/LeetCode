//Time: O(n)+O(k^2) Space: O(n)+O(k^2) worst case O(n^2)
class Solution {
    public String toGoatLatin(String S) {
        Set<Character> voews = new HashSet<>();
        voews.add('a');
        voews.add('e');
        voews.add('i');
        voews.add('o');
        voews.add('u');
        voews.add('A');
        voews.add('E');
        voews.add('I');
        voews.add('O');
        voews.add('U');        
        StringBuffer res = new StringBuffer();
        String[] words = S.split(" ");
        int len = words.length;
        String as = "";
        for (int i = 0; i < len; i++){
            as += "a";
            String word = words[i];
            int lenW = word.length();
            StringBuffer newWord = new StringBuffer();
            if (voews.contains(word.charAt(0))){
                newWord.append(word).append("ma").append(as);
            }
            else {
                newWord.append(word.substring(1)).append(word.substring(0, 1)).append("ma").append(as);
            }
            res.append(newWord);
            if (i != len - 1) {
                res.append(" ");
            }
        }
        return res.toString();
    }
}

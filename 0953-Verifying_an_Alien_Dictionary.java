
//1 Hashmap Time O(C) Space O(1) C is the total content of words
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        HashMap<Character, Integer> orderMap = new HashMap<Character, Integer>();
        int len = order.length();
        for (int i = 0; i < len; i++) {
            orderMap.put(order.charAt(i), i);
        }
        
        int len_w = words.length;
        if (len_w <= 1) return true;
        for (int i = 0; i < len_w - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            int len1 = w1.length();
            int len2 = w2.length();
            int p = 0;
            int q = 0;
            while (p < len1 && q < len2) {
                if (orderMap.get(w1.charAt(p)) < orderMap.get(w2.charAt(q))) {
                    break;
                }
                else if (orderMap.get(w1.charAt(p)) > orderMap.get(w2.charAt(q))) {
                    return false;
                }
                p++;
                q++;
            }
            if (q == len2 && p != len1 && orderMap.get(w1.charAt(q - 1)) == orderMap.get(w2.charAt(q - 1))) return false;
        }
        return true;        
    }
}


//2. array, 2 loops continue Time O(C) Space O(1) C is the total content of words
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] index = new int[26];
        for (int i = 0; i < order.length(); ++i)
            index[order.charAt(i) - 'a'] = i;

        search: for (int i = 0; i < words.length - 1; ++i) {
            String word1 = words[i];
            String word2 = words[i+1];

            // Find the first difference word1[k] != word2[k].
            for (int k = 0; k < Math.min(word1.length(), word2.length()); ++k) {
                if (word1.charAt(k) != word2.charAt(k)) {
                    // If they compare badly, it's not sorted.
                    if (index[word1.charAt(k) - 'a'] > index[word2.charAt(k) - 'a'])
                        return false;
                    continue search;
                }
            }

            // If we didn't find a first difference, the
            // words are like ("app", "apple").
            if (word1.length() > word2.length())
                return false;
        }

        return true;
    }
}

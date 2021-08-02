//Time O(m * n), Space O(1). m: length of any word, n: length of input array, 
// One pass, only need to update when found a new word
class Solution {
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int p_1 = -1;
        int p_2 = -1;
        int min_dist = wordsDict.length;
        for (int i = 0; i < wordsDict.length; i++){
            if (wordsDict[i].equals(word1)) p_1 = i;
            else if (wordsDict[i].equals(word2)) p_2 = i;
          // don't need to update if haven't found new id
            else continue;
            if (p_1 != - 1 && p_2 != -1){
                min_dist = Math.min(min_dist, Math.abs(p_1 - p_2));
            } 
        }
        return min_dist;
    }
}

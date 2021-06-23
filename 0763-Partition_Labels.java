// Greedy, check the last occurrance of a letter.
// Time O(n) Space O(1)

class Solution {
    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        List<Integer> res = new ArrayList<>();
        int prev = 0;
        int p = 0;
        Arrays.fill(last, -1);
        for (int i = 0; i < s.length(); i++){
            int pos = s.charAt(i) - 'a';
            last[pos]= i;
        }

        for (int i = 0; i < s.length(); i++){
            int pos = s.charAt(i) - 'a';
            p = Math.max(last[pos], p);
//            if (last[pos] != -1){
//                while (p <= last[pos] && p < s.length()){
//                    p++;
//                }
//            }
            if (p == i){
                res.add(p - prev + 1);
                prev = p + 1;
            }
        }
        return res;
    }
}

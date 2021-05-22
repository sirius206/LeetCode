//DP Time O(n) Space O(m) ? O(1)? since ASCII has 128
//dp[i]: longest str ending in i

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.equals("")) return 0;
        Map<Character, Integer> seen = new HashMap<>();
        int[] dp = new int[s.length()];
        dp[0] = 1;
        seen.put(s.charAt(0), 0);
        int max = 1;
        int left = -1;
        for (int i = 1; i < s.length(); i++){
            if (seen.containsKey(s.charAt(i)) && seen.get(s.charAt(i)) > left){
                left = seen.get(s.charAt(i));
                dp[i] = i - left;
            }
            else {
                dp[i] = dp[i - 1] + 1;
                }
            seen.put(s.charAt(i), i);
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}

//ans
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
}

//DP, C++
/**
 * Solution (DP, O(n)):
 * 
 * Assume L[i] = s[m...i], denotes the longest substring without repeating
 * characters that ends up at s[i], and we keep a hashmap for every
 * characters between m ... i, while storing <character, index> in the
 * hashmap.
 * We know that each character will appear only once.
 * Then to find s[i+1]:
 * 1) if s[i+1] does not appear in hashmap
 *    we can just add s[i+1] to hash map. and L[i+1] = s[m...i+1]
 * 2) if s[i+1] exists in hashmap, and the hashmap value (the index) is k
 *    let m = max(m, k), then L[i+1] = s[m...i+1], we also need to update
 *    entry in hashmap to mark the latest occurency of s[i+1].
 * 
 * Since we scan the string for only once, and the 'm' will also move from
 * beginning to end for at most once. Overall complexity is O(n).
 *
 * If characters are all in ASCII, we could use array to mimic hashmap.
 */

int lengthOfLongestSubstring(string s) {
    // for ASCII char sequence, use this as a hashmap
    vector<int> charIndex(256, -1);
    int longest = 0, m = 0;

    for (int i = 0; i < s.length(); i++) {
        m = max(charIndex[s[i]] + 1, m);    // automatically takes care of -1 case
        charIndex[s[i]] = i;
        longest = max(longest, i - m + 1);
    }

    return longest;
}

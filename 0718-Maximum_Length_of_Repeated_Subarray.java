//1.DP Time:O(m*n) Space:O(m*n)
//dp[i][j]: longest repeated subarray ending in A[i] and B[j]
class Solution {
    public int findLength(int[] A, int[] B) {
        int lenA = A.length;
        int lenB = B.length;
        int[][] dp = new int[lenA][lenB];
        for (int i = 0; i < lenA; i++){
            if (A[i] == B[0]){
                dp[i][0] = 1;
            }
        }
        for (int j = 1; j < lenB; j++){
            if (A[0] == B[j]){
                dp[0][j] = 1;
            }
        }
        int max = 0;
        for (int i = 1; i < lenA; i++){
            for (int j = 1; j < lenB; j++){
                if (A[i] == B[j]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }
        return max;
    }
}

//2. DP Time:O(m*n) Space:O(n)
class Solution {
    public int findLength(int[] A, int[] B) {
        int lenA = A.length;
        int lenB = B.length;
        int[] dp = new int[lenB];
        for (int j = 0; j < lenB; j++){
            if (A[0] == B[j]){
                dp[j] = 1;
            }
        }
        int max = 0;
        for (int i = 1; i < lenA; i++){
            int[] prev = new int[lenB];
            for (int j = 0; j < lenB; j++){
                prev[j] = dp[j];
            }
            if (A[i] == B[0]){
                dp[0] = 1;
            }
            else dp[0] = 0;
            for (int j = 1; j < lenB; j++){
                if (A[i] == B[j]){
                    dp[j] = prev[j - 1] + 1;
                    max = Math.max(dp[j], max);
                }
                else dp[j] = 0;
            }
        }
        return max;
    }
}

//3. sliding window Time:O(m*n) Space:O(1)
class Solution(object):
    def findLength(self, A, B):
        B,A = sorted([A,B],key=len)
        m = len(A)
        n = len(B)
        maxLen = 0
        for a in xrange(-n+1,m+n-1):
            cnt = 0
            for ptrB in xrange(n):
                ptrA = a+ptrB
                if ptrA < 0 : continue
                if ptrA >= m : break
                if A[ptrA]==B[ptrB]:
                    cnt += 1
                    if cnt > maxLen: maxLen = cnt
                else:
                    cnt = 0
        return maxLen

//4.Binary Search with Rolling Hash Time:O(m + n)log(min(m,n)) Space:O(m)
import java.math.BigInteger;

class Solution {
    int P = 113;
    int MOD = 1_000_000_007;
    int Pinv = BigInteger.valueOf(P).modInverse(BigInteger.valueOf(MOD)).intValue();

    private int[] rolling(int[] source, int length) {
        int[] ans = new int[source.length - length + 1];
        long h = 0, power = 1;
        if (length == 0) return ans;
        for (int i = 0; i < source.length; ++i) {
            h = (h + source[i] * power) % MOD;
            if (i < length - 1) {
                power = (power * P) % MOD;
            } else {
                ans[i - (length - 1)] = (int) h;
                h = (h - source[i - (length - 1)]) * Pinv % MOD;
                if (h < 0) h += MOD;
            }
        }
        return ans;
    }
    
    private boolean check(int guess, int[] A, int[] B) {
        Map<Integer, List<Integer>> hashes = new HashMap();
        int k = 0;
        for (int x: rolling(A, guess)) {
            hashes.computeIfAbsent(x, z -> new ArrayList()).add(k++);
        }
        int j = 0;
        for (int x: rolling(B, guess)) {
            for (int i: hashes.getOrDefault(x, new ArrayList<Integer>()))
                if (Arrays.equals(Arrays.copyOfRange(A, i, i+guess),
                                  Arrays.copyOfRange(B, j, j+guess))) {
                    return true;
                }
            j++;
        }
        return false;
    }

    public int findLength(int[] A, int[] B) {
        int lo = 0, hi = Math.min(A.length, B.length) + 1;
        while (lo < hi) {
            int mi = (lo + hi) / 2;
            if (check(mi, A, B)) lo = mi + 1;
            else hi = mi;
        }
        return lo - 1;
    }
}

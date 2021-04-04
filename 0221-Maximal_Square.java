//1. DP Time: O(mn), Space: O(mn)
//dp(i,j) represents the side length of the maximum square whose bottom right corner is the cell with index (i,j) in the original matrix.
//Starting from index (0,0), for every 1 found in the original matrix, we update the value of the current element as
//dp(i,j)=min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1
class Solution {
    public int maximalSquare(char[][] matrix) {
        int h = matrix.length;
        int w = matrix[0].length;
        int min = Math.min(h, w);
        if (h == 0 || w == 0) return 0;
        int[][] dp = new int[h][w];
        int max = 0;
        for (int i = 0; i < h; i++){
          if (matrix[i][0] == '1') {
            dp[i][0] = 1;
            max = 1;
          }
        }
        for (int j = 1; j < w; j++){
          if (matrix[0][j] == '1') {
            dp[0][j] = 1;
            max = 1;
          }
        }

        for (int i = 1; i < h; i++){
            for (int j = 1; j < w; j++){
                if (matrix[i][j] == '1'){
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j],dp[i][j - 1])) + 1;
                    max = dp[i][j] > max ? dp[i][j] : max;                    
                }
            }
        }
        return max * max;
    }
}
//2. better dp Time: O(mn), Space: O(n)
//dp[j]=min(dp[j−1],dp[j],prev), where prev refers to the old dp[j−1]
public class Solution {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[] dp = new int[cols + 1];
        int maxsqlen = 0, prev = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return maxsqlen * maxsqlen;
    }
}

//3. mine, not good. BFS Time:O(n^3) 1. Space:O(n^2) 
class Solution {
    public int maximalSquare(char[][] matrix) {
        int h = matrix.length;
        int w = matrix[0].length;
        int len = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < h; i++){
            for (int j = 0; j < w; j++){
                q.offer(i * w + j);
            }
        }
        
        while (!q.isEmpty()){
            int n = q.size();
            search: for (int k = 0; k < n; k++) {
                int val = q.poll();
                int r = val / w;
                int c = val % w;
                if (r + len >= h || c + len >= w) continue search;
                for (int i = 0; i <= len; i++){
                    if (matrix[r + i][c + len] != '1') continue search;
                }
                for (int j = 0; j < len; j++){
                    if (matrix[r + len][c + j] != '1') continue search;
                }                
                q.offer(val);       
            }
            len++;
        }
        return (len - 1) * (len - 1);
    }
}

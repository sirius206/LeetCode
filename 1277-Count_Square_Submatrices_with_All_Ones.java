// DP, Time O(m *n) Space O(m *n), space can be optimized to O(m)

class Solution {
    public int countSquares(int[][] matrix) {
        //dp[i][j] : number of squares ending with position (i,j)
        if (matrix == null) return 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int total = 0; 
        
        int[][] dp = new int[rows][cols];
        //initialize first row and column
        for (int i = 0; i < rows; i++){
            if (matrix[i][0] == 1) {
                dp[i][0] = 1;
                total += dp[i][0];
            }
        }
        //do not start with j = 0, otherwise matrix[0][0] is counted twice 
        for (int j = 1; j < cols; j++){
            if (matrix[0][j] == 1) {
                dp[0][j] = 1;
                total += dp[0][j];
            }
        }        
        
        for (int i = 1; i < rows; i++){
            for (int j = 1; j < cols; j++){
                if (matrix[i][j] == 1){
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    total += dp[i][j];
                }
            }
        }
        return total;
    }
}

//1. DP, 2D, Time:O(mn) Space: O(mn)
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] f = new int[m][n];
        f[0][0] = grid[0][0];
        for (int i = 1; i < m; i++){
            f[i][0] = f[i -1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++){
            f[0][j] = f[0][j -1] + grid[0][j];
        }
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                f[i][j] = Math.min(f[i - 1][j], f[i][j - 1]) + grid[i][j];
            }
        }
        return f[m - 1][n - 1];
    }
}

//2. DP, 1D Time:O(mn) Space: O(n)
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] f = new int[n];
        f[0] = grid[0][0];
        for (int j = 1; j < n; j++){
            f[j] = f[j -1] + grid[0][j];
        }
        for (int i = 1; i < m; i++){
            for (int j = 0; j < n; j++){
                if (j == 0) {
                    f[j] = f[j] + grid[i][j];
                }
                else{
                    f[j] = Math.min(f[j], f[j - 1]) + grid[i][j];
                }
            }
        }
        return f[n - 1];
    }
}

//3. DP, without extra space, modify the original array Time:O(mn) Space: O(1)
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 1; i < m; i++){
            grid[i][0] = grid[i -1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++){
            grid[0][j] = grid[0][j -1] + grid[0][j];
        }
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[m - 1][n - 1];
    }
}

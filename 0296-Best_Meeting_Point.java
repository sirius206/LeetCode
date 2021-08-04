// Time O(mn), Space O(mn)
// treat X and Y as independent 1D problem, 
// Collect X and Y separately in ascending order
// Find median, then calculate distance

class Solution {
    public int minTotalDistance(int[][] grid) {
        List<Integer> X = new ArrayList<>();
        List<Integer> Y = new ArrayList<>();
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == 1) {
                    X.add(i);
                }
            }
        }
        
        for (int j = 0; j < grid[0].length; j++){
            for (int i = 0; i < grid.length; i++){
                if (grid[i][j] == 1) {
                    Y.add(j);
                }
            }
        }        
        
        int n = X.size();
        int x = X.get(n / 2);
        int y = Y.get(n / 2);
        
        int res = 0;
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == 1){
                    res += Math.abs(i - x) + Math.abs(j - y);
                }
            }
        }
        return res;
    }
}

//1. DP Time: O(n^2) Space: O(n), 1D array extra space
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        int[] sum = new int[len];
        for (int i = 0; i < len; i++){
            sum[i] = triangle.get(len - 1).get(i);
        }
        for (int i = len - 2; i >= 0; i--){
            for (int j = 0; j <= i; j++){
                sum[j] = triangle.get(i).get(j) + Math.min(sum[j], sum[j + 1]);
            }
        }
        return sum[0];
    }
}

//2. DP Time: O(n^2) Space: O(n^2) more space with 2D array
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        int[][] sum = new int[len][len];
        for (int i = 0; i < len; i++){
            sum[len - 1][i] = triangle.get(len - 1).get(i);
        }
        for (int i = len - 2; i >= 0; i--){
            for (int j = 0; j <= i; j++){
                sum[i][j] = triangle.get(i).get(j) + Math.min(sum[i + 1][j], sum[i + 1][j + 1]);
            }
        }
        return sum[0][0];
    }
}


//3. DFS exceed time limit Time: O(2^n) Space: O(n)
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 1) return triangle.get(0).get(0);
        return helper(0, 0, triangle);
    }
    
    public int helper(int i, int j, List<List<Integer>> triangle){
        if (i == triangle.size()){
            return 0;
        }
        return triangle.get(i).get(j) + Math.min(helper(i + 1, j, triangle),  helper(i + 1, j + 1, triangle));
    }
}

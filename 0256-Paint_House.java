//1.  DP Time:O(n) Space:O(n)
// dp[i][j]: paint the ith house with color j, start from the last row, just the cost, then add up to top
class Solution {
    public int minCost(int[][] costs) {
        int n = costs.length;
        if (n == 0) return 0;
        if (n == 1) return Math.min(costs[0][0], Math.min(costs[0][1], costs[0][2]));
        int[][] dp = new int[n][costs[0].length];
        for (int j = 0; j < costs[0].length; j++){
            dp[n - 1][j] = costs[n - 1][j];
        }
        for (int i = n - 2; i >= 0; i--){
            for (int j = 0; j < costs[0].length; j++){
                if (j == 0) {
                    dp[i][j] = costs[i][j] + Math.min(dp[i + 1][1], dp[i + 1][2]);
                }
                else if (j == 1){
                    dp[i][j] = costs[i][j] + Math.min(dp[i + 1][0], dp[i + 1][2]);
                }
                else if (j == 2){
                    dp[i][j] = costs[i][j] + Math.min(dp[i + 1][0], dp[i + 1][1]);
                }
            }
        }
        return Math.min(dp[0][0], Math.min(dp[0][1], dp[0][2]));
    }
}

//2. DP with less space Time:O(n) Space:O(1)
class Solution {
    public int minCost(int[][] costs) {

        if (costs.length == 0) return 0;

        int[] previousRow = costs[costs.length -1];

        for (int n = costs.length - 2; n >= 0; n--) {

            int[] currentRow = costs[n].clone();
            // Total cost of painting the nth house red.
            currentRow[0] += Math.min(previousRow[1], previousRow[2]);
            // Total cost of painting the nth house green.
            currentRow[1] += Math.min(previousRow[0], previousRow[2]);
            // Total cost of painting the nth house blue.
            currentRow[2] += Math.min(previousRow[0], previousRow[1]);
            previousRow = currentRow;
        }  

        return Math.min(Math.min(previousRow[0], previousRow[1]), previousRow[2]);
    }
}

// Recursion with Memo Time:O(n) Space:O(n)
class Solution {

    private int[][] costs;
    private Map<String, Integer> memo;

    public int minCost(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        this.costs = costs;
        this.memo = new HashMap<>();
        return Math.min(paintCost(0, 0), Math.min(paintCost(0, 1), paintCost(0, 2)));
    }

    private int paintCost(int n, int color) {
        if (memo.containsKey(getKey(n, color))) {
            return memo.get(getKey(n, color));   
        }
        int totalCost = costs[n][color];
        if (n == costs.length - 1) {
        } else if (color == 0) { // Red
            totalCost += Math.min(paintCost(n + 1, 1), paintCost(n + 1, 2));
        } else if (color == 1) { // Green
            totalCost += Math.min(paintCost(n + 1, 0), paintCost(n + 1, 2));
        } else { // Blue
            totalCost += Math.min(paintCost(n + 1, 0), paintCost(n + 1, 1));
        }        
        memo.put(getKey(n, color), totalCost);

        return totalCost;
    }

    private String getKey(int n, int color) {
        return String.valueOf(n) + " " + String.valueOf(color);
    }
}

// DP
class Solution {
    public int minCost(int[][] costs) {
        int n = costs.length;
        if (n == 0) return 0;
        if (n == 1) return Math.min(costs[0][0], Math.min(costs[0][1], costs[0][2]));
        int[][] dp = new int[n][costs[0].length];
        for (int j = 0; j < costs[0].length; j++){
            dp[n - 1][j] = costs[n - 1][j];
        }
        for (int i = n - 2; i >= 0; i--){
            for (int j = 0; j < costs[0].length; j++){
                if (j == 0) {
                    dp[i][j] = costs[i][j] + Math.min(dp[i + 1][1], dp[i + 1][2]);
                }
                else if (j == 1){
                    dp[i][j] = costs[i][j] + Math.min(dp[i + 1][0], dp[i + 1][2]);
                }
                else if (j == 2){
                    dp[i][j] = costs[i][j] + Math.min(dp[i + 1][0], dp[i + 1][1]);
                }
            }
        }
        return Math.min(dp[0][0], Math.min(dp[0][1], dp[0][2]));
    }
}

// Recursion Time limit exceed Time:O(2^n) Space:O(n)
class Solution {

    private int[][] costs;

    public int minCost(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        this.costs = costs;
        return Math.min(paintCost(0, 0), Math.min(paintCost(0, 1), paintCost(0, 2)));
    }

    private int paintCost(int n, int color) {
        int totalCost = costs[n][color];
        if (n == costs.length - 1) {
        } else if (color == 0) { // Red
            totalCost += Math.min(paintCost(n + 1, 1), paintCost(n + 1, 2));
        } else if (color == 1) { // Green
            totalCost += Math.min(paintCost(n + 1, 0), paintCost(n + 1, 2));
        } else { // Blue
            totalCost += Math.min(paintCost(n + 1, 0), paintCost(n + 1, 1));
        }        
        return totalCost;
    }
}

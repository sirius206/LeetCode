//1. BFS, Time O(n), Space O(n), n is size of the grid
class Solution {
    public int orangesRotting(int[][] grid) {
        int time = 0;
        int m = grid.length;
        int n = grid[0].length;
        int [][] directions = new int[][]{{-1,0}, {0, 1}, {1,0}, {0, -1}};  // better to use array for directions
        Queue<Integer> q = new LinkedList<>();
        int freshOranges = 0;
        for (int i = 0 ; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 2) {
                    q.offer(i * n + j);
                }
                else if (grid[i][j] == 1){
                    freshOranges++;  // better use count than loop again at the end
                }
            }
        }
        while (q.size() != 0){
            int i = q.size();  //use this count to determin each level of BFS
            time++;
            while (i > 0) {
                int id = q.poll();
                int r = id / n;
                int c = id % n;
                for (int[] d : directions){  //better 
                    int new_r = r + d[0];
                    int new_c = c + d[1];
                    if (new_r >= 0 && new_c >= 0 && new_r <= m - 1 && new_c <= n - 1 
                        && grid[new_r][new_c] == 1){
                        grid[new_r][new_c] = 2;
                        freshOranges--;
                        q.offer((new_r) * n + new_c);
                    }
                }
                i--;
            }
        }
        if (freshOranges != 0) return -1;
        else return Math.max(time - 1, 0);        
    }
}

//2. In place BFS Time O(n^2), Space O(1),
// don't need queue, instead mark grid with time stamp, then in the next round, scan the grid for corresponding timestamp.
class Solution {
    // run the rotting process, by marking the rotten oranges with the timestamp
    public boolean runRottingProcess(int timestamp, int[][] grid, int ROWS, int COLS) {
        int[][] directions = { {-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        // flag to indicate if the rotting process should be continued
        boolean toBeContinued = false;
        for (int row = 0; row < ROWS; ++row)
            for (int col = 0; col < COLS; ++col)
                if (grid[row][col] == timestamp)
                    // current contaminated cell
                    for (int[] d : directions) {
                        int nRow = row + d[0], nCol = col + d[1];
                        if (nRow >= 0 && nRow < ROWS && nCol >= 0 && nCol < COLS)
                            if (grid[nRow][nCol] == 1) {
                                // this fresh orange would be contaminated next
                                grid[nRow][nCol] = timestamp + 1;
                                toBeContinued = true;
                            }
                    }
        return toBeContinued;
    }

    public int orangesRotting(int[][] grid) {
        int ROWS = grid.length, COLS = grid[0].length;
        int timestamp = 2;
        while (runRottingProcess(timestamp, grid, ROWS, COLS))
            timestamp++;

        // end of process, to check if there are still fresh oranges left
        for (int[] row : grid)
            for (int cell : row)
                // still got a fresh orange left
                if (cell == 1)
                    return -1;


        // return elapsed minutes if no fresh orange left
        return timestamp - 2;
    }
}

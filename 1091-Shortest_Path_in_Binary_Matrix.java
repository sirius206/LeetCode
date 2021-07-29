//BFS, Time O(mn), space O(mn)

class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0][0] == 1) return -1;
        int[][] dir = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}, 
                             {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        
        int res = 0; 
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        visited[0][0] = true;
        while(q.size() != 0) {
            int size = q.size();
            res++;
            for (int k = 0; k < size; k++){
                int val = q.poll();
                int r = val / cols;
                int c = val % cols;
                if (r == rows - 1 && c == cols - 1){
                    return res;
                }

                for (int i = 0; i < 8; i++){
                    int new_r = r + dir[i][0];
                    int new_c = c + dir[i][1];
                    if (new_r >= 0 && new_r < rows && new_c >= 0 && new_c < cols 
                        && grid[new_r][new_c] == 0 && !visited[new_r][new_c]){
                        visited[new_r][new_c] = true;
                        q.offer(new_r * cols + new_c);
                    }
                }                
            }
        }
        return -1;
    }
}

//2. BFS with overwrite, set visited to 1, 
class Solution {
    
    private static final int[][] directions = 
        new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    
    public int shortestPathBinaryMatrix(int[][] grid) {
        
        // Firstly, we need to check that the start and target cells are open.
        if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) {
            return -1;
        }
        
        // Set up the BFS.
        Queue<int[]> queue = new ArrayDeque<>();
        grid[0][0] = 1;
        queue.add(new int[]{0, 0});
        
        // Carry out the BFS
        while (!queue.isEmpty()) {
            int[] cell = queue.remove();
            int row = cell[0];
            int col = cell[1];
            int distance = grid[row][col];
            if (row == grid.length - 1 && col == grid[0].length - 1) {
                return distance;
            }
            for (int[] neighbour : getNeighbours(row, col, grid)) {
                int neighbourRow = neighbour[0];
                int neighbourCol = neighbour[1];
                queue.add(new int[]{neighbourRow, neighbourCol});
                grid[neighbourRow][neighbourCol] = distance + 1;
            }
        }
        
        // The target was unreachable.
        return -1;
    }
    
    private List<int[]> getNeighbours(int row, int col, int[][] grid) {
        List<int[]> neighbours = new ArrayList<>();
        for (int i = 0; i < directions.length; i++) {
            int newRow = row + directions[i][0];
            int newCol = col + directions[i][1];
            if (newRow < 0 || newCol < 0 || newRow >= grid.length 
                    || newCol >= grid[0].length
                    || grid[newRow][newCol] != 0) {
                continue;    
            }
            neighbours.add(new int[]{newRow, newCol});
        }
        return neighbours; 
    }
    
}

//1. DFS, Time O(M×N) Space O(M×N)
class Solution {
    public int numIslands(char[][] grid) {
        if (grid.length == 0) return 0;
        int nr = grid.length;
        int nc = grid[0].length;
        int count = 0;
        for (int r = 0; r < nr; r++){
            for (int c = 0; c < nc; c++){
                if (grid[r][c] == '1'){
                    count++;
                    dfs(grid, r, c);
                }
            }
        }
        return count;
    }
    
    private void dfs(char[][] grid, int r, int c){
        int nr = grid.length;
        int nc = grid[0].length;
        if (r < 0 || r >= nr || c < 0 || c >= nc || grid[r][c] == '0') {
            return;
        }
        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }
}

//2. BFS Time O(M×N) Space O(min(M, N)) in worst case where the grid is filled with lands, the size of queue can grow up to min(M,N) (diagonal)
class Solution {
    public int numIslands(char[][] grid) {
        if (grid.length == 0) return 0;
        int nr = grid.length;
        int nc = grid[0].length;
        int count = 0;
        for (int r = 0; r < nr; r++){
            for (int c = 0; c < nc; c++){
                if (grid[r][c] == '1'){
                    count++;
                    grid[r][c] = '0';
                    Queue<Integer> q = new LinkedList<>();
                    q.offer(r * nc + c);
                    while (!q.isEmpty()){
                        int id = q.poll();
                        int row = id / nc;
                        int col = id % nc;
                        if (row - 1 >= 0 && grid[row - 1][col] == '1'){
                            q.offer((row - 1) * nc + col);
                            grid[row - 1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col - 1] == '1'){
                            q.offer(row * nc + col - 1);
                            grid[row][col - 1] = '0';
                        }     
                        if (col + 1 <= nc - 1 && grid[row][col + 1] == '1'){
                            q.offer(row * nc + col + 1);
                            grid[row][col + 1] = '0';
                        } 
                        if (row + 1 <= nr - 1 && grid[row + 1][col] == '1'){
                            q.offer((row + 1) * nc + col);
                            grid[row + 1][col] = '0';
                        }                    
                    }
                }
            }
        }
        return count;
    }
}

//3. Union find
class Solution {
    private int count = 0;
    private int[] parent;
    public int numIslands(char[][] grid) {
        if (grid.length == 0) return 0;
        int nr = grid.length;
        int nc = grid[0].length;
        parent = new int[nr * nc]; 
        for (int r = 0; r < nr; r++){
            for (int c = 0; c < nc; c++){
                if (grid[r][c] == '1'){
                    parent[r * nc + c] = r * nc + c;
                    count++;
                }
            }
        }
        
         for (int r = 0; r < nr; r++){
            for (int c = 0; c < nc; c++){
                if (grid[r][c] == '1'){
                    grid[r][c] = '0';
                    if (r - 1 >= 0 && grid[r - 1][c] == '1'){
                        union(r * nc + c, (r - 1) * nc + c);
                        //System.out.println(count);
                    }
                    if (c - 1 >= 0 && grid[r][c - 1] == '1'){
                        union(r * nc + c, r * nc + c - 1);
                    }  
                    if (c + 1 <= nc - 1 && grid[r][c + 1] == '1'){
                        union(r * nc + c, r * nc + c + 1);
                    }
                    if (r + 1 <= nr - 1 && grid[r + 1][c] == '1'){
                        union(r * nc + c, (r + 1) * nc + c);
                    }
                }
            }
        }       
        return count;
    }
    
    private int find(int x){
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    
    private void union(int p, int q) {
        int root_p = find(p);
        int root_q = find(q);
        if (root_p != root_q){
            parent[root_p] = root_q;
            count--;
        }
    }
    
}

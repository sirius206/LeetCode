//1. Union find Time O(n^3),We traverse over the complete matrix once. Union and find operations take O(n) time in the worst case 
// Space O(n) parent array of size n is used

class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int[] parent = new int[n];
        int count = n;
        for (int i = 0; i < n; i++){
            parent[i] = i;
        }
        
        for (int i = 0; i < n; i++){
            for (int j = i + 1; j < n; j++){
                if (isConnected[i][j] == 1){
                    count = union(parent, i, j, count);
                }
            }
        }
        return count;
    }
    
    private int find(int[] parent, int x){
        if (x == parent[x]){
            return x;
        }
        return parent[x] = find(parent, parent[x]);
        
    }
    
    private int union(int[] parent, int p, int q, int count){
        int root_p = find(parent, p);
        int root_q = find(parent, q);
        if(root_p != root_q) { //important to check if equal, avoid cycle
            parent[root_p] = root_q;
            count--;
        }
        return count;
    }
}

//2. DFS faster, Time O(n^2), Space O(n)
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int cc = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++){
            if (!visited[i]) {
                cc++;
                dfs(isConnected, visited, i);
            }
        }
        
        return cc;
    }
    
    private void dfs(int[][] isConnected, boolean[] visited, int i){
        visited[i] = true;
        int n = isConnected.length;
        for (int j = 0; j < n; j++){
            if (isConnected[i][j] == 1) {
                if (!visited[j]){
                    dfs(isConnected, visited, j);
                }
            }
        }
    }
}

//3. BFS Time O(n^2), Space O(n)
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int cc = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++){
            if (!visited[i]){
                cc++;
                bfs(isConnected, visited, i);
            }
        }
        return cc;
    }
    
    private void bfs(int[][] isConnected, boolean[] visited, int i){
        int n = isConnected.length;
        Queue<Integer> q = new LinkedList<>();
        q.offer(i);
        while(q.size() != 0){
            int j = q.poll();
            visited[j] = true;
            for (int k = 0; k < n; k++){
                if (isConnected[j][k] == 1 && ! visited[k]){
                    q.offer(k);
                }
            }
        }
    }
}

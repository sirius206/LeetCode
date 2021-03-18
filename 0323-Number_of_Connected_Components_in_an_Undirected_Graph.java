
//1. Union find with path compression Time O(n + m log*n) (same as n+m), Space O(n)
class Solution {
    private int[] father;
    private int cc;
    
    public int find(int x) {
        if (x == father[x])  return x;
        return father[x] = find(father[x]); 
    }
    
    public void union(int p, int q) {
        int root_p = find(p);
        int root_q = find(q);
        if (root_p != root_q) {
            father[root_p] = root_q;
            cc--;
        }
    }
    
    public int countComponents(int n, int[][] edges) {
        cc = n;
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
        
        int len = edges.length;
        for (int i = 0; i < len; i++) {
            int p = edges[i][0];
            int q = edges[i][1];
            union(p, q);    
        }
        return cc;
    }
}

//2. DFS, Time: O(n+m), Space: O(n+m)
class Solution {
    public int countComponents(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        int components = 0;
        boolean[] visited = new boolean[n];
        for (int v = 0; v < n; v++) components += dfs(v, graph, visited);
        return components;
    }
    int dfs(int u, List<Integer>[] graph, boolean[] visited) {
        if (visited[u]) return 0;
        visited[u] = true;
        for (int v : graph[u]) dfs(v, graph, visited);
        return 1;
    }
}

//3. BFS O(n+m), Space: O(n+m)
class Solution {
    public int countComponents(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        int components = 0;
        boolean[] visited = new boolean[n];
        for (int v = 0; v < n; v++) components += bfs(v, graph, visited);
        return components;
    }
    int bfs(int src, List<Integer>[] graph, boolean[] visited) {
        if (visited[src]) return 0;
        visited[src] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(src);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : graph[u]) {
                if (!visited[v]) {
                    visited[v] = true;
                    q.offer(v);
                }
            }
        }
        return 1;
    }
}


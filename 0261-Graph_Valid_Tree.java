
//1. BFS Time O(m + n) Space O(m + n)
class Solution {
    public boolean validTree(int n, int[][] edges) {
        if (n == 0) return false;
        if (edges.length != n - 1) return false; //if the graph is fully connected and contains exactly n - 1 edges, it can't possibly contain a cycle 
      //and therefore must be a tree
        
        List<List<Integer>> adjacencyList = initializeGraph(n, edges);
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> seen = new HashSet<>();
        
        queue.offer(0);
        seen.add(0);
        
        while (!queue.isEmpty()){
            int node = queue.poll();
            
            for (int neighbor : adjacencyList.get(node)) {
                if (seen.contains(neighbor)) continue;
                queue.offer(neighbor);
                seen.add(neighbor);
            }
        }
        
        return seen.size() == n;
    }
    
    private List<List<Integer>> initializeGraph(int n, int[][] edges){
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<Integer>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);
        }
        return adjacencyList;
    }
}

//2. Iterative DFS Time O(m + n) Space O(m + n)
class Solution {
    public boolean validTree(int n, int[][] edges) {
        if (n == 0) return false;
        if (edges.length != n - 1) return false;
        
        List<List<Integer>> adjacencyList = initializeGraph(n, edges);
        Stack<Integer> stack = new Stack<>();
        Set<Integer> seen = new HashSet<>();
        
        stack.push(0);
        seen.add(0);
        
        while (!stack.isEmpty()){
            int node = stack.pop();
            
            for (int neighbor : adjacencyList.get(node)) {
                if (seen.contains(neighbor)) continue;
                stack.push(neighbor);
                seen.add(neighbor);
            }
        }
        
        return seen.size() == n;
    }
    
    private List<List<Integer>> initializeGraph(int n, int[][] edges){
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<Integer>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);
        }
        return adjacencyList;
    }
}

//3. Recursive DFS 
class Solution {
    
    private List<List<Integer>> adjacencyList = new ArrayList<>();
    private Set<Integer> seen = new HashSet<>();
    
    
    public boolean validTree(int n, int[][] edges) {
        
        if (edges.length != n - 1) return false;
        
        // Make the adjacency list.
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
        
        // Carry out depth first search.
        dfs(0);
        // Inspect result and return the verdict.
        return seen.size() == n;   
    }
    
    public void dfs(int node) {
        if (seen.contains(node)) return;
        seen.add(node);
        for (int neighbour : adjacencyList.get(node)) {
            dfs(neighbour);
        }
    }
}


//4 Union find
class Solution {
    
    private List<List<Integer>> adjacencyList = new ArrayList<>();
    private Set<Integer> seen = new HashSet<>();
    
    
    public boolean validTree(int n, int[][] edges) {
        
        if (edges.length != n - 1) return false;
        unionFind uf = new unionFind(n);
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }
        return uf.getCount() == 1;
    }
    
}

class unionFind {
    private int[] parent;
    private int count;
    
    public unionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        count = n;
    }
    
    public int find(int x) {
        if (x  == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    
    public void union(int p, int q){
        int root_p = find(p);
        int root_q = find(q);
        if (root_p != root_q) {
            parent[root_p] = root_q;
            count--;
        }
    }
    
    public int getCount() {
        return count;
    }
}



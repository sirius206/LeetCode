//1. DFS Time O(m + n), Space O(n)

class Solution {
    private Map<Node, Node> mapping = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (node == null) return node;
        if (mapping.containsKey(node)) {
            return mapping.get(node);
        }
        
        Node cloneNode = new Node(node.val, new ArrayList());
        mapping.put(node, cloneNode);
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));   //
        }
        return cloneNode;   //
    }
}

//2. BFS Time O(m + n), Space O(n)

class Solution {
    private Map<Node, Node> mapping = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (node == null) return node;
        Queue<Node> queue= new LinkedList<>();
        queue.offer(node);
        mapping.put(node, new Node(node.val, new ArrayList<Node>()));
        while (!queue.isEmpty()) {
            Node oldNode = queue.poll();

            for (Node neighbor : oldNode.neighbors) {
                if (!mapping.containsKey(neighbor)) {
                    mapping.put(neighbor, new Node(neighbor.val, new ArrayList<Node>()));
                    queue.add(neighbor);
                }
                //Add the clones of the neighbors to the corresponding list of the clone node
                mapping.get(oldNode).neighbors.add(mapping.get(neighbor));
            }
        }
        return mapping.get(node);
    }
}

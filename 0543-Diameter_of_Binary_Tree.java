//Time O(n), Space O(n)
//Let's calculate the depth of a node in the usual way: max(depth of node.left, depth of node.right) + 1. 
//While we do, a path "through" this node uses 1 + (depth of node.left) + (depth of node.right) nodes. 
//Let's search each node and remember the highest number of nodes used in some path. The desired length is 1 minus this number.

class Solution {
    int max;  // important to declare here so both functions can modify it
    
    public int diameterOfBinaryTree(TreeNode root) {
        max = 0;  
        depth(root);  // call function to calculate max
        return max;  
    }
    private int depth (TreeNode root){
        if (root == null) return 0;
        int L = depth(root.left);   // calculate before as the it's used twice later
        int R = depth(root.right);
        max = Math.max(max, L + R);
        return Math.max(L, R) + 1;
    }
}

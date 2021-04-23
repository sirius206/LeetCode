//Traverse Time O(n) Space O(n)
//We need a way to record if we've seen both p and q
//We need to traverse the entire tree even after we've found one of them.
class Solution {
    boolean pFound = false;
    boolean qFound = false;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
      //因为有全局变量，所以需要辅助函数先traverse entire tree
        TreeNode LCA = LCA(root, p, q);
        //return pFound && qFound ? LCA : null;
        if (pFound && qFound) return LCA;
        else return null;
    }
    
    public TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return root;
        TreeNode left = LCA(root.left, p, q);     
        TreeNode right = LCA(root.right, p, q);
       // check if root is p or q after the recursion, so visited the entire tree 
        if (root == p) {
            pFound = true;
            return root;
        }
        if (root == q) {
            qFound = true;
            return root;
        }
      
        //return left == null ? right : right == null ? left : root;
        if (left != null && right !=null) return root;
        return left != null ? left : right; 
    }
}

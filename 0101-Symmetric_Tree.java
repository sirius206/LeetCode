//Time O(n), Space: Completely balanced Olog(n), completely unbalanced O(n)
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return isSubTreeSymmetric(root.left, root.right);
    }
    
    private boolean isSubTreeSymmetric(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        //or
        //if (a == null || b == null) return false;
        if ((a !=null && b == null) || (a ==null && b != null)) return false;
        //or
        //    if (a.val != b.val) return false;
        //     return isSubTreeSymmetric(a.right, b.right) && isSubTreeSymmetric(a.left, b.left);
        return a.val == b.val && isSubTreeSymmetric(a.left, b.right) && isSubTreeSymmetric(a.right, b.left);
    }
}

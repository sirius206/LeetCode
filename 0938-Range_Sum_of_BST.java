//1. for Binary search tree, Time O(n), Space O(n)
class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;
        if (root.val > high) return rangeSumBST(root.left, low, high);   //only need to look at left subtree
        else if (root.val < low) return rangeSumBST(root.right, low, high); //only need to look at right subtree
        else 
            return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
    }
}

//2. for any binary tree, not ordered, take more time

class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;
        if (root.val >= low && root.val <= high) {
            return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
        } else {
            return rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
        }
        
    }
}

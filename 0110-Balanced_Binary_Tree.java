// 1. my solution Top-down recursion, redundant, Time O(nlogn), Space O(n)

class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        if (Math.abs(height(root.left) - height(root.right)) > 1) return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }
    
    private int height(TreeNode root) {
        if (root == null) return 0;      // -1????
        return 1 + Math.max(height(root.left), height(root.right));
    }
}

//2: DC, Bottom-up recursion Time O(n), Space O(n)
// Use "result type" (包装的类): Utility class to store information from recursive calls
final class TreeInfo {
  public final int height;
  public final boolean balanced;

  public TreeInfo(int height, boolean balanced) {
    this.height = height;
    this.balanced = balanced;
  }
}

class Solution {
  // Return whether or not the tree at root is balanced while also storing
  // the tree's height in a reference variable.
  private TreeInfo isBalancedTreeHelper(TreeNode root) {
    // An empty tree is balanced and has height = -1
    if (root == null) {
      return new TreeInfo(-1, true);
    }

    // Check subtrees to see if they are balanced.
    TreeInfo left = isBalancedTreeHelper(root.left);
    TreeInfo right = isBalancedTreeHelper(root.right);
    if (left.balanced && right.balanced && Math.abs(left.height - right.height) < 2) {
        return new TreeInfo(Math.max(left.height, right.height) + 1, true);
    }
    else return new TreeInfo(-1, false);
  }

  public boolean isBalanced(TreeNode root) {
    return isBalancedTreeHelper(root).balanced;
  }
};

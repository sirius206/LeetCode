// 1. recursion Time O(n), Space O(n), n represents the minimum number of nodes from the two given trees
class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;           
        }
        if (root2 == null) {
            return root1;           
        }
        int a = root1.val;
        int b = root2.val;
        TreeNode node = new TreeNode(a + b);
        node.left = mergeTrees(root1.left, root2.left);
        node.right = mergeTrees(root1.right, root2.right);            
        return node;
    }
}

//2. Iterative Time O(n), Space O(n), n represents the minimum number of nodes from the two given trees

public class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null)
            return t2;
        Stack < TreeNode[] > stack = new Stack < > ();
        stack.push(new TreeNode[] {t1, t2});
        while (!stack.isEmpty()) {
            TreeNode[] t = stack.pop();
            if (t[0] == null || t[1] == null) {
                continue;
            }
            t[0].val += t[1].val;
            if (t[0].left == null) {
                t[0].left = t[1].left;
            } else {
                stack.push(new TreeNode[] {t[0].left, t[1].left});
            }
            if (t[0].right == null) {
                t[0].right = t[1].right;
            } else {
                stack.push(new TreeNode[] {t[0].right, t[1].right});
            }
        }
        return t1;
    }
}

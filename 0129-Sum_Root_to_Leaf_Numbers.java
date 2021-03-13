//1. DFS Time O(n), Space O(H)
class Solution {
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        constructPath(root, 0);
        return sum;
    }
    private void constructPath(TreeNode root, int currNum){
        if (root == null) return;
        currNum = currNum * 10 + root.val;
        if (root.left == null && root.right == null) {
            sum += currNum;
        }
        constructPath(root.left, currNum);
        constructPath(root.right, currNum);
    }
}

//2. Iterative Time O(n), Space O(H)
class Solution {
  public int sumNumbers(TreeNode root) {
    int rootToLeaf = 0, currNumber = 0;
    Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque();
    stack.push(new Pair(root, 0));

    while (!stack.isEmpty()) {
      Pair<TreeNode, Integer> p = stack.pop();
      root = p.getKey();
      currNumber = p.getValue();

      if (root != null) {
        currNumber = currNumber * 10 + root.val;
        // if it's a leaf, update root-to-leaf sum
        if (root.left == null && root.right == null) {
          rootToLeaf += currNumber;
        } else {
          stack.push(new Pair(root.right, currNumber));
          stack.push(new Pair(root.left, currNumber));
        }
      }
    }
    return rootToLeaf;
  }
}

//3. Morris Preorder Traversal Time O(n), Space O(1)
class Solution {
  public int sumNumbers(TreeNode root) {
    int rootToLeaf = 0, currNumber = 0;
    int steps;
    TreeNode predecessor;

    while (root != null) {
      // If there is a left child,
      // then compute the predecessor.
      // If there is no link predecessor.right = root --> set it.
      // If there is a link predecessor.right = root --> break it.
      if (root.left != null) {
        // Predecessor node is one step to the left
        // and then to the right till you can.
        predecessor = root.left;
        steps = 1;
        while (predecessor.right != null && predecessor.right != root) {
          predecessor = predecessor.right;
          ++steps;
        }

        // Set link predecessor.right = root
        // and go to explore the left subtree
        if (predecessor.right == null) {
          currNumber = currNumber * 10 + root.val;
          predecessor.right = root;
          root = root.left;
        }
        // Break the link predecessor.right = root
        // Once the link is broken,
        // it's time to change subtree and go to the right
        else {
          // If you're on the leaf, update the sum
          if (predecessor.left == null) {
            rootToLeaf += currNumber;
          }
          // This part of tree is explored, backtrack
          for(int i = 0; i < steps; ++i) {
            currNumber /= 10;
          }
          predecessor.right = null;
          root = root.right;
        }
      }

      // If there is no left child
      // then just go right.
      else {
        currNumber = currNumber * 10 + root.val;
        // if you're on the leaf, update the sum
        if (root.right == null) {
          rootToLeaf += currNumber;
        }
        root = root.right;
      }
    }
    return rootToLeaf;
  }
}

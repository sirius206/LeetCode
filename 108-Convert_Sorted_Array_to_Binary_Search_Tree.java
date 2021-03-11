//1. My solution Time O(n), Space O(n) (O(logn) for recursion stack, O(n) to keep output)
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        int len = nums.length;
        if (len == 0) return null;
        int mid = len / 2;
        TreeNode root = new TreeNode(nums[mid]);
        int[] leftArray = Arrays.copyOfRange(nums, 0, mid);
        int[] rightArray = Arrays.copyOfRange(nums, mid + 1, nums.length);
        root.left = sortedArrayToBST(leftArray);
        root.right = sortedArrayToBST(rightArray);
        return root;
    }
}

//2. Answer, with helper functoin
class Solution {
  int[] nums;

  public TreeNode helper(int left, int right) {
    if (left > right) return null;

    // always choose left middle node as a root
    int p = (left + right) / 2;

    // preorder traversal: node -> left -> right
    TreeNode root = new TreeNode(nums[p]);
    root.left = helper(left, p - 1);
    root.right = helper(p + 1, right);
    return root;
  }

  public TreeNode sortedArrayToBST(int[] nums) {
    this.nums = nums;
    return helper(0, nums.length - 1);
  }
}

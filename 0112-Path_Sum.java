// DFS 
// Time O(N), Space: Completely balanced Olog(N), completely unbalanced O(n)
//1. 简单方法
/*One is going through the tree by considering at each step the node itself and its children. 
If node is not a leaf, one calls recursively hasPathSum method for its children with a sum decreased by the current node value. 
If node is a leaf, one checks if the the current sum is zero, i.e if the initial sum was discovered.
*/
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {return false;}
        if (root.left == null && root.right == null && targetSum == root.val) return true;
        return (hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val));
    }
}

//2. 麻烦方法
 */
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        ArrayList<Integer> sums = new ArrayList<Integer>(); 
        int sum = 0;
        addPathSum(root, sum, sums);
        for (int x : sums) {
            System.out.println(x);
            if (x == targetSum) return true;
        }
        return false;
    }
    
    private void addPathSum(TreeNode root, int sum, ArrayList<Integer> sums) {
        if (root != null) {
            sum += root.val;
            if (root.left == null && root.right == null) {
                sums.add(sum);
            }
            addPathSum(root.left, sum, sums);
            addPathSum(root.right, sum, sums);
        }
    }
}

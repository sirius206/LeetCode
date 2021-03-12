// Prefix Sum Time O(n), Space O(n)
class Solution {
    private int count = 0;
    private HashMap<Integer, Integer> h = new HashMap<Integer, Integer>();
    public int pathSum(TreeNode root, int sum) {
        int cursum = 0;
        return preOrder(root, sum, cursum);
    }
    private int preOrder(TreeNode root, int sum, int cursum) {
        if (root != null) {   
            cursum += root.val;
            if (cursum == sum) count++;
            count += h.getOrDefault(cursum - sum, 0);
            h.put(cursum, 1 + h.getOrDefault(cursum, 0));
            preOrder(root.left, sum, cursum);
            preOrder(root.right, sum, cursum);
            h.put(cursum, h.get(cursum) - 1);
        }
        return count;
    }
}

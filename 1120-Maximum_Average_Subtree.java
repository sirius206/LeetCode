//1. DC + result type (post order traversal), Time O(n) Space O(n)
public class resultType{
    int sum;
    int num;
    resultType(int sum, int num){
        this.sum = sum;
        this.num = num;
    }
}

class Solution {
    private Double maxAve = 0.0;
    public double maximumAverageSubtree(TreeNode root) {
        helper(root);
        return maxAve;
    }
    
    public resultType helper(TreeNode root){
        if (root == null) return new resultType(0, 0);
        resultType leftRes = helper(root.left);
        resultType rightRes = helper(root.right);
        
        int sum = root.val + leftRes.sum + rightRes.sum;
        int num = 1 + leftRes.num + rightRes.num;
        
        if (sum * 1.0 / num > maxAve) maxAve = sum * 1.0 / num;
        return new resultType(sum, num);
    }
}

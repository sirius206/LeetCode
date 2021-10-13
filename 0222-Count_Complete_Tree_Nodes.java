//1. short recursive Time O(log(n)^2), no optimization
//Since I have the tree in every recursive step, I have O(log(n)) steps. Finding a height costs O(log(n)). So overall O(log(n)^2)
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}

//2. ans, divide and conquer, with optimization
// Time O(log(n)^2)?
public int countNodes(TreeNode root) {

    int leftDepth = leftDepth(root);
	int rightDepth = rightDepth(root);

	if (leftDepth == rightDepth)
		return (1 << leftDepth) - 1; //optimized
	else
		return 1+countNodes(root.left) + countNodes(root.right);

}

private int rightDepth(TreeNode root) {
	// TODO Auto-generated method stub
	int dep = 0;
	while (root != null) {
		root = root.right;
		dep++;
	}
	return dep;
}

private int leftDepth(TreeNode root) {
	// TODO Auto-generated method stub
	int dep = 0;
	while (root != null) {
		root = root.left;
		dep++;
	}
	return dep;
}

//3. Mine, BFS O(n)?

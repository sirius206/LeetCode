//DFS n nodes, called the function 2n + 1 times
//Time O(N), Space: Completely balanced Olog(N), completely unbalanced O(n)
//Time complexity : we visit each node exactly once, thus the time complexity is O(N), where NN is the number of nodes.

//Space complexity : in the worst case, the tree is completely unbalanced, e.g. each node has only left child node, 
//the recursion call would occur NN times (the height of the tree), therefore the storage to keep the call stack would be \mathcal{O}(N)O(N). 
//But in the best case (the tree is completely balanced), the height of the tree would be Olog(N). Therefore, 
//the space complexity in this case would be )O(log(N)).

class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {return 0;}
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}

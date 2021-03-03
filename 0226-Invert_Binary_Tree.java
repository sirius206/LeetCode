
//1. my solution  麻烦
class Solution {
    public TreeNode invertTree(TreeNode root) {
        invertSub(root);
        return root;
    }
    
    private void invertSub(TreeNode root) {
        if (root == null) return;
        else if (root.left == null && root.right != null) {
            invertSub(root.right);
            root.left = root.right;
            root.right = null;
        }
        else if (root.right == null) {
            invertSub(root.left);
            root.right = root.left;
            root.left = null;
        } 
        else {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            invertSub(root.left);
            invertSub(root.right); 
        }
    }
}

////2. LC solution 简单
public TreeNode invertTree(TreeNode root) {
    if (root == null) {
        return null;
    }
    TreeNode right = invertTree(root.right);
    TreeNode left = invertTree(root.left);
    root.left = right;
    root.right = left;
    return root;
}
//3. 更简单
// C++
class Solution {
public:
    TreeNode* invertTree(TreeNode* root) {
        if (!root) return NULL;
        TreeNode *tmp = root->left;
        root->left = invertTree(root->right);
        root->right = invertTree(tmp);
        return root;
    }
};


// 4.iterative
//C++
class Solution {
public:
    TreeNode* invertTree(TreeNode* root) {
        if (!root) return NULL;
        queue<TreeNode*> q;
        q.push(root);
        while (!q.empty()) {
            TreeNode *node = q.front(); q.pop();
            TreeNode *tmp = node->left;
            node->left = node->right;
            node->right = tmp;
            if (node->left) q.push(node->left);
            if (node->right) q.push(node->right);
        }
        return root;
    }
};

// DFS
//Time O(N), Space: Completely balanced Olog(N), completely unbalanced O(n)

class Solution {
    private List<String> paths = new ArrayList<String>();
    public List<String> binaryTreePaths(TreeNode root) {
        String path = "";
        constructPaths(root, path);
        return paths;
    }
    private void constructPaths(TreeNode root, String path) {
        if (root == null) return;
        path = path + String.valueOf(root.val);
        if (root.left == null && root.right == null) {
            paths.add(path);  // if is array, need to make new, see #113
        }
        else {
            path = path + "->";
            constructPaths(root.left, path);
            constructPaths(root.right, path);  
          // if is array, need to do a remove; 
        }
    }
    
}

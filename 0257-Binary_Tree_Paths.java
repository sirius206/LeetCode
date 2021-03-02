// DFS
//Time O(N), Space: Completely balanced Olog(N), completely unbalanced O(n)

class Solution {
  public void construct_paths(TreeNode root, String path, ArrayList<String> paths) {
    if (root != null) {
      path += Integer.toString(root.val);
      if ((root.left == null) && (root.right == null))  // if reach a leaf
        paths.add(path);  // update paths
      else {
        path += "->";  // extend the current path
        construct_paths(root.left, path, paths);
        construct_paths(root.right, path, paths);
      }
    }
  }

  public List<String> binaryTreePaths(TreeNode root) {
    ArrayList<String> paths = new ArrayList<String>();
    construct_paths(root, "", paths);
    return paths;
  }
}

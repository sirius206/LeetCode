//Time O(n^2) or (nlogn?), Space O(n) 
class Solution {
    private List<List<Integer>> all_path = new ArrayList<List<Integer>>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<Integer> path = new ArrayList<Integer>();
        construct_path(root, targetSum, path);
        return all_path;
    }
    
    private void construct_path(TreeNode root, int targetSum, List<Integer> path) {
        if (root == null) return;   //only return here, not on leaf
        path.add(root.val);
        if (root.left == null & root.right == null && targetSum == root.val) {
            all_path.add(new ArrayList<>(path));   
          //need to make new, because array is a reference
          //by doing the list method, you are initializing a new array in an entire new address so when you pop later on in the code, 
          //you won't lose the numbers in the array.
        }
        construct_path(root.left, targetSum - root.val, path);
        construct_path(root.right, targetSum - root.val, path);
        path.remove(path.size() - 1);
    }
}

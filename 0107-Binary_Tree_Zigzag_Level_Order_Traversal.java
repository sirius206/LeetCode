1. BFS Time O(n), Space O(n) 
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
         List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> currentLevel = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            if (res.size() % 2 != 0) {
                Collections.reverse(currentLevel);
            }
            res.add(currentLevel);
        }
        return res;       
    }
}

//2 BFS Answer

class Solution {
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    if (root == null) {
      return new ArrayList<List<Integer>>();
    }

    List<List<Integer>> results = new ArrayList<List<Integer>>();

    // add the root element with a delimiter to kick off the BFS loop
    LinkedList<TreeNode> node_queue = new LinkedList<TreeNode>();
    node_queue.addLast(root);
    node_queue.addLast(null);

    LinkedList<Integer> level_list = new LinkedList<Integer>();
    boolean is_order_left = true;

    while (node_queue.size() > 0) {
      TreeNode curr_node = node_queue.pollFirst();
      if (curr_node != null) {
        if (is_order_left)
          level_list.addLast(curr_node.val);
        else
          level_list.addFirst(curr_node.val);

        if (curr_node.left != null)
          node_queue.addLast(curr_node.left);
        if (curr_node.right != null)
          node_queue.addLast(curr_node.right);

      } else {
        // we finish the scan of one level
        results.add(level_list);
        level_list = new LinkedList<Integer>();
        // prepare for the next level
        if (node_queue.size() > 0)
          node_queue.addLast(null);
        is_order_left = !is_order_left;
      }
    }
    return results;
  }
}

//3. DFS answer

class Solution {
  protected void DFS(TreeNode node, int level, List<List<Integer>> results) {
    if (level >= results.size()) {
      LinkedList<Integer> newLevel = new LinkedList<Integer>();
      newLevel.add(node.val);
      results.add(newLevel);
    } else {
      if (level % 2 == 0)
        results.get(level).add(node.val);
      else
        results.get(level).add(0, node.val);
    }

    if (node.left != null) DFS(node.left, level + 1, results);
    if (node.right != null) DFS(node.right, level + 1, results);
  }

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    if (root == null) {
      return new ArrayList<List<Integer>>();
    }
    List<List<Integer>> results = new ArrayList<List<Integer>>();
    DFS(root, 0, results);
    return results;
  }
}

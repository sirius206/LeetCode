// 1 Time O(1) Space O(1)
// 用下个节点覆盖要删除节点 再删除下个节点
class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}

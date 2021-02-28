// Dummy head Time O(n), Space O(1)

class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode node = head;
        ListNode preNode = dummyHead;
        while (node != null) {
            if (node.val == val) {
                preNode.next = node.next;
            }
            else preNode = node;
            node = node.next;
        }
        return dummyHead.next;
    }
}

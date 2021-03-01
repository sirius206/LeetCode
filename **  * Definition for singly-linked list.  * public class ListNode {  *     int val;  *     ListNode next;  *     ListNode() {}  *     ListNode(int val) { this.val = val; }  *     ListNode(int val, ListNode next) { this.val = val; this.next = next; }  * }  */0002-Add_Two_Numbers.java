// Time O(n), Space O(1)
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode preNode = dummyHead;        
        ListNode nodeA = l1;
        ListNode nodeB = l2;
        int a = 0;
        int b = 0;
        int sum = 0;
        int value = 0;
        int carry = 0;
        while (nodeA != null || nodeB != null) {
            if (nodeA != null) a = nodeA.val;
            else a = 0;
            if (nodeB != null) b = nodeB.val;
            else b = 0;
            sum = a + b + carry;
            carry = sum / 10;
            value = sum % 10;
            if (nodeA != null) nodeA = nodeA.next;   // check if null
            if (nodeB != null) nodeB = nodeB.next;  
            preNode.next = new ListNode(value);
            preNode = preNode.next;
        }
        if (carry == 1) {
            preNode.next = new ListNode(1);
            //node.next = null;
        }
        return dummyHead.next;
    }
}

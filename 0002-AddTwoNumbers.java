// Method 1 Time O(n), Space O(1)
// l1 或 l2 为null时也可继续
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        int carryOver = 0;
        int x, y;
        while (l1 != null || l2 != null) {
            x = (l1 == null) ? 0 : l1.val;
            y = (l2 == null) ? 0 : l2.val;
            curr.next = new ListNode (x + y + carryOver);
            carryOver = curr.next.val / 10;
            curr.next.val = curr.next.val % 10;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
            curr = curr.next;
        }
        if (carryOver == 1) {
            curr.next = new ListNode(1);
        }
        return dummyHead.next;
    }
}


//Method 2 self 
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
            if (nodeA != null) nodeA = nodeA.next;
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

// Method 1 by myself <- Book
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


//Method 2 by myself
//需要简化

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode result = dummyHead;
        int carryOver = 0;
        while (l1 != null && l2 != null) {
            result.next = new ListNode (l1.val + l2.val + carryOver);
            if (result.next.val >= 10) {
                result.next.val = result.next.val % 10;
                carryOver = 1;
            }
            else carryOver = 0;
            l1 = l1.next;
            l2 = l2.next;
            result = result.next;
        }
        if (l1 != null) {
            while (l1 != null) {
                result.next = new ListNode(l1.val + carryOver);
                if (result.next.val == 10) {
                    result.next.val = 0;
                    carryOver = 1;
                    l1 = l1.next;
                    result = result.next;
                }
                else {
                    carryOver = 0;
                    result = result.next;
                    result.next = l1.next;
                    break;
                }
            }
        }

        if (l2 != null) {
            while (l2 != null) {
                result.next = new ListNode(l2.val + carryOver);
                if (result.next.val == 10) {
                    result.next.val = 0;
                    carryOver = 1;
                    l2 = l2.next;
                    result = result.next;
                }
                else {
                    carryOver = 0;
                    result = result.next;
                    result.next = l2.next;
                    break;
                }
            }
        }
        if (carryOver == 1) {
            result.next = new ListNode(1);
        }
        return dummyHead.next;
    }
}

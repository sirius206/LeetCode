//1. stack Time O(n), Space O(n)

class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) return head;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        Stack<ListNode> stack = new Stack<>();
        ListNode node = dummyHead;
        int pos = -1;
        ListNode preLeft = null;
        while (pos <= right - 1) {
            if (pos >= left - 1) stack.push(node);
            else if(left - 1 - pos == 1) preLeft = node;
            node = node.next;
            pos++;    
            }
        ListNode node2 = preLeft;
        while (!stack.isEmpty()) {
            node2.next = stack.pop();
            node2 = node2.next;
        }
        node2.next = node;
        return dummyHead.next;

    }
}

// 2. Iterative Time O(n), Space O(1)
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {

        // Empty list
        if (head == null) {
            return null;
        }

        // Move the two pointers until they reach the proper starting point
        // in the list.
        ListNode cur = head, prev = null;
        while (m > 1) {
            prev = cur;
            cur = cur.next;
            m--;
            n--;
        }

        // The two pointers that will fix the final connections.
        ListNode con = prev, tail = cur;

        // Iteratively reverse the nodes until n becomes 0.
        ListNode third = null;
        while (n > 0) {
            third = cur.next;
            cur.next = prev;
            prev = cur;
            cur = third;
            n--;
        }

        // Adjust the final connections as explained in the algorithm
        if (con != null) {
            con.next = prev;
        } else {
            head = prev;
        }

        tail.next = cur;
        return head;
    }
}

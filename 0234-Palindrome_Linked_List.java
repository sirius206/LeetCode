//1. Stack Time O(n), Space O(n)

class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        Stack<ListNode> s = new Stack<ListNode>();
        ListNode node = head;
        ListNode left = head;
        int len = 0;
        while (node != null) {
            s.push(node);
            node = node.next;
            len++;
        }
        for (int i = 0; i < len/2; i++) {
            if (left.val != s.peek().val) return false;
            s.pop();
            left = left.next;
        }
        return true;
    }
}


//Reverse half in place, Time O(n), Space O(1)
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode endFistHalfNode = endOfFirstHalf(head);
        ListNode secondHalfStartNode = reverse(endFistHalfNode.next);
        ListNode left = head;
        ListNode right = secondHalfStartNode;
        boolean result = true;
        while (result && right != null) {
            if (left.val != right.val) result = false;
            left = left.next; 
            right = right.next;
        }
        endFistHalfNode.next = reverse(secondHalfStartNode);
        return result;
        
    }
    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next !=null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    } 
    private ListNode reverse(ListNode head) {
        ListNode curNode = head;
        ListNode preNode = null;
        ListNode nextNode = null;
        while(curNode != null) {
            nextNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }
        //preNode.next = null;
        return preNode;
    }
    
}

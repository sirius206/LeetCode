// 1. Set Time O(n), Space O(n) 

public class Solution {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<ListNode>();
        ListNode curNode = head;
        while (curNode != null) {
            if (set.contains(curNode)) return true;
            set.add(curNode);
            curNode = curNode.next;
        }
        return false;
    }
}

//2 Fast slow pointers 1 Time O(n), Space O(1) 
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            if (fast == slow) return true;
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}

//2 Fast slow pointers 2 Time O(n), Space O(1) 
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}

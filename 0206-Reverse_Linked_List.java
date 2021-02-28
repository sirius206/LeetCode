//1. Iterative Time O(n), Space O(1)

class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode curNode = head;
        ListNode preNode = null;
        ListNode nextNode = null;
        
        while (curNode != null) {
            nextNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }
        head = preNode;
        return head;
    }
}

//or
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode curNode = head;
        ListNode preNode = null;
        ListNode nextNode = null;
        
        while (curNode.next != null) {
            nextNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }
        curNode.next = preNode;
        head = curNode;
        return head;
    }
}

//2. stack Time O(n), Space O(n)

class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        Stack<ListNode> s = new Stack<ListNode>();
        ListNode node = head;
        while(node.next != null) {
            s.push(node);
            node = node.next;
        }
        head = node;
        while(! s.isEmpty()) {
            node.next = s.peek();
            s.pop();
            node = node.next;
        }
        node.next = null;
        return head;
    }
}

//3. recursion Time O(n), Space O(n)
public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode p = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return p;
}


//two pointers Time O(n), Space O(1)
//compare memery reference of intersect, not value
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        int lenA = 0;
        int lenB = 0;
        while (nodeA != null) {
            lenA++;
            nodeA = nodeA.next;
        }
        while (nodeB != null) {
            lenB++;
            nodeB = nodeB.next;
        } 
        ListNode startA = headA;
        ListNode startB = headB;
        if (lenA >= lenB) {
            int diff = lenA - lenB;
            while (diff > 0) {
                startA = startA.next;
                diff--;
            }
            
        }
        if (lenA < lenB) {
            int diff = lenB - lenA;
            while (diff > 0) {
                startB = startB.next;
                diff--;
            }           
        }
        while (startA != null) {
            if (startA == startB) return startA;  // not startA.val
            startA = startA.next;
            startB = startB.next;
        }
        return null;
    }
        
}

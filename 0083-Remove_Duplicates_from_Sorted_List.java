// 1 iterative Time O(n), Space O(1)

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode curNode = head;
        while (curNode != null && curNode.next != null) {
            if (curNode.val == curNode.next.val) {
                curNode.next = curNode.next.next;
            }
            else curNode = curNode.next;
        }
        return head;
    }
}

// 2 recursion Time O(n), Space O(n)
// C++ 
class Solution {
public:
    ListNode* deleteDuplicates(ListNode* head) {
        ListNode *cur = head;
        while (cur && cur->next) {
            if (cur->val == cur->next->val) {
                cur->next = cur->next->next;
            } else {
                cur = cur->next;
            }
        }
        return head;
    }
};

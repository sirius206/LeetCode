// 1 3Stacks
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();
        Stack<Integer> stack3 = new Stack<Integer>();
        ListNode nodeA = l1;
        ListNode nodeB = l2;   
        while (nodeA != null) {
            stack1.push(nodeA.val);
            nodeA = nodeA.next;
        }
        while (nodeB != null) {
            stack2.push(nodeB.val);
            nodeB = nodeB.next;
        }        
        int a = 0;
        int b = 0;
        int sum = 0;
        int carry = 0;
        int value = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() ) {
            if (!stack1.isEmpty()) a = stack1.pop();
            else a = 0;
            if (!stack2.isEmpty()) b = stack2.pop();
            else b = 0;     
            sum = a + b + carry;
            carry = sum / 10;
            value = sum % 10;
            stack3.push(value);
        }
        if (carry == 1) stack3.push(1);
        ListNode dummyHead = new ListNode(0);
        ListNode preNode = dummyHead;
        while  (!stack3.isEmpty()) {
            preNode.next = new ListNode(stack3.pop());
            preNode = preNode.next;
        }
        return dummyHead.next;
    }
}


//2 2stacks 移动head

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();
        ListNode nodeA = l1;
        ListNode nodeB = l2;   
        while (nodeA != null) {
            stack1.push(nodeA.val);
            nodeA = nodeA.next;
        }
        while (nodeB != null) {
            stack2.push(nodeB.val);
            nodeB = nodeB.next;
        }        
        int a = 0;
        int b = 0;
        int sum = 0;
        int carry = 0;
        int value = 0;
        ListNode head = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() ) {
            if (!stack1.isEmpty()) a = stack1.pop();
            else a = 0;
            if (!stack2.isEmpty()) b = stack2.pop();
            else b = 0;     
            sum = a + b + carry;
            carry = sum / 10;
            value = sum % 10;
            ListNode node = new ListNode(value);
            node.next = head;
            head = node;
        }
        if (carry == 1) {
            ListNode node = new ListNode(1);
            node.next = head;
            head = node;
        }
        
        return head;
    }
}

//3. Reverse

//4. recursion 
//C++
class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        int n1 = getLength(l1), n2 = getLength(l2);
        ListNode *head = new ListNode(1);
        head->next = (n1 > n2) ? helper(l1, l2, n1 - n2) : helper(l2, l1, n2 - n1);
        if (head->next->val > 9) {
            head->next->val %= 10;
            return head;
        }
        return head->next;
    }
    int getLength(ListNode* head) {
        int cnt = 0;
        while (head) {
            ++cnt;
            head = head->next;
        }
        return cnt;
    }
    ListNode* helper(ListNode* l1, ListNode* l2, int diff) {
        if (!l1) return NULL;
        ListNode *res = (diff == 0) ? new ListNode(l1->val + l2->val) : new ListNode(l1->val);
        ListNode *post = (diff == 0) ? helper(l1->next, l2->next, 0) : helper(l1->next, l2, diff - 1);
        if (post && post->val > 9) {
            post->val %= 10;
            ++res->val;
        }
        res->next = post;
        return res;
    }
};

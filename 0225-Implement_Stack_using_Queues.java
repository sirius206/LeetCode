// 2 queues
// Push: Time O(n), Space O(n), pop, peek, isEmpty: Time O(1), Space O(1)
class MyStack {
    Queue<Integer> q1 = new LinkedList<>();    // Do not use PriorityQueue!!!!
    Queue<Integer> q2 = new LinkedList<>();
    /** Initialize your data structure here. */
    public MyStack() {
        
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        q2.add(x);
        while (!q1.isEmpty()){
            q2.add(q1.remove());
        }
        while (!q2.isEmpty()){
            q1.add(q2.remove());
        }
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return q1.remove();
    }
    
    /** Get the top element. */
    public int top() {
        return q1.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q1.isEmpty();
    }
}

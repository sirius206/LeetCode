//2 stacks, 
//push: Time O(n), Space O(n)
//pop, peek, isEmpty: Time O(1), Space O(1)
class MyQueue {
    private Stack<Integer> s1= new Stack<>();
    private Stack<Integer> s2= new Stack<>();
    
    /** Initialize your data structure here. */
    public MyQueue() {
        
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        while (!s1.isEmpty()){
            s2.push(s1.pop());
        }
        s2.push(x);
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return s1.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        return s1.peek();        
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.isEmpty();
    }
}

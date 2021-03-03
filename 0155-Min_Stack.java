//use exsiting Stack interface 
//1. use 2 stacks
//Time O(1), Space O(1)

class MinStack {
    
    private Stack<int[]> stack = new Stack<>();
    /** initialize your data structure here. */
    public MinStack() {
        
    }
    
    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(new int[] {x, x});
            return;
        }
        else {
            int curMin = stack.peek()[1];
            stack.push(new int[]{x, Math.min(curMin, x)});
        }  
    }
    
    public void pop() {
        stack.pop();
    }
    
    public int top() {
        return stack.peek()[0];
    }
    
    public int getMin() {
        return stack.peek()[1];
    }
}

//2. use 1 stack
public class MinStack {
    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();
    
    public MinStack() {}  
    public void push(int x) {
        s1.push(x);
        if (s2.isEmpty() || s2.peek() >= x) s2.push(x);
    }
    public void pop() {
        int x = s1.pop();
        if (s2.peek() == x) s2.pop();
    }   
    public int top() {
        return s1.peek();
    }  
    public int getMin() {
        return s2.peek();
    }
}

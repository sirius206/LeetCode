//use exsiting Stack interface 
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

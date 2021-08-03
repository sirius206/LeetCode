//can't be all O(1), otherwise sort would be O(n), so at least O(logn)
//1. TreeMap + Doubly linked list
//Time: O(logN) for all operations except peek which is O(1), Space: O(n)
class MaxStack {
    TreeMap<Integer, List<Node>> map;
    DoubleLinkedList dll;

    public MaxStack() {
        map = new TreeMap();
        dll = new DoubleLinkedList();
    }

    public void push(int x) {
        Node node = dll.add(x);
        if(!map.containsKey(x))
            map.put(x, new ArrayList<Node>());
        map.get(x).add(node);
    }

    public int pop() {
        int val = dll.pop();
        List<Node> L = map.get(val);
        L.remove(L.size() - 1);
        if (L.isEmpty()) map.remove(val);
        return val;
    }

    public int top() {
        return dll.peek();
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        int max = peekMax();
        List<Node> L = map.get(max);
        Node node = L.remove(L.size() - 1);
        dll.unlink(node);
        if (L.isEmpty()) map.remove(max);
        return max;
    }
}

class DoubleLinkedList {
    Node head, tail;

    public DoubleLinkedList() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }

    public Node add(int val) {
        Node x = new Node(val);
        x.next = tail;
        x.prev = tail.prev;
        tail.prev = tail.prev.next = x;
        return x;
    }

    public int pop() {
        return unlink(tail.prev).val;
    }

    public int peek() {
        return tail.prev.val;
    }

    public Node unlink(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return node;
    }
}

class Node {
    int val;
    Node prev, next;
    public Node(int v) {val = v;}
}


// 2. two stacks 
// Time: O(N) for popMax, and O(1) for the others, Space O(n)
class MaxStack {
    Stack<Integer> stack;
    Stack<Integer> maxStack;
    /** initialize your data structure here. */
    public MaxStack() {
        this.stack = new Stack<>();
        this.maxStack = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);
        int max = x;
        if (!maxStack.isEmpty()){
            max = Math.max(x, maxStack.peek());
        }
        maxStack.push(max);
    }
    
    public int pop() {
        maxStack.pop();
        return stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int peekMax() {
        return maxStack.peek();
    }
    
    public int popMax() {
        int max = maxStack.peek();
        Stack<Integer> temp = new Stack();
        while (top() != max){
            temp.push(pop());
        }
        pop();
        while (!temp.isEmpty()){
            push(temp.pop());
        }
        return max;
    }
}
// 3. stack + priorityQueue
// Time: O(n) for popMax, O(logn) for push and pop, O(1) for others, Space O(n)
class MaxStack {
    Stack<Integer> stack;
    PriorityQueue<Integer> pq;
    /** initialize your data structure here. */
    public MaxStack() {
        this.stack = new Stack<>();
        this.pq = new PriorityQueue<>((a,b) -> b - a);
    }
    
    public void push(int x) {
        stack.push(x);
        pq.offer(x);
    }
    
    public int pop() {
        int x = stack.peek();
        pq.remove(x);
        return stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int peekMax() {
        return pq.peek();
    }
    
    public int popMax() {
        int max = pq.peek();
        Stack<Integer> temp = new Stack<>();
        while (top() != max){
            temp.push(pop());
        }
        pop();
        while (!temp.isEmpty()){
            push(temp.pop());
        }
        return max;
    }
}

//4. priorityQueue + double ended queue
// Time: O(n) for popMax, O(logn) for push and pop, O(1) for others, Space O(n)
class MaxStack {
    
    Deque<Integer> dqStack;
    PriorityQueue<Integer> pqMax;

    /** initialize your data structure here. */
    public MaxStack() {
        dqStack = new ArrayDeque<>();
        pqMax = new PriorityQueue<>( (a,b) ->(b - a) );
    }
    
    public void push(int x) {
        dqStack.addLast(x);
        pqMax.offer(x);
    }
    
    public int pop() {
        int deleteE = dqStack.removeLast();
        pqMax.remove(deleteE);
        return deleteE;
    }
    
    public int top() {
        return dqStack.getLast();
    }
    
    public int peekMax() {
        return pqMax.peek();
    }
    
    public int popMax() {
        int deleteE = pqMax.poll();
        dqStack.removeLastOccurrence(deleteE);
        return deleteE;
    }
}

Deque:
deque.addLast(x); - O(1)
deque.removeLast(); - O(1)
deque.getLast(); O(1)
deque.removeLastOccurrence(delElement); - O(n)

Priority Queue
pqMax.offer(x); - O(logn)
pqMax.remove(delElement); - O(logn)
pqMax.poll(); O(1)

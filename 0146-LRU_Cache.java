// Doule linked list + hashmap

class LRUCache {
    class DLinkedNode {
        int key;
        int val;
        DLinkedNode prev;
        DLinkedNode next;
    }
    
    private void addNode(DLinkedNode node){
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
    
    private void removeNode(DLinkedNode node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    private void moveToHead(DLinkedNode node){
        removeNode(node);
        addNode(node);
    }
    
    private DLinkedNode popTail(){
        DLinkedNode node = tail.prev;
        removeNode(node);
        return node;
    }
    
    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int capacity;
    private DLinkedNode head, tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (cache.containsKey(key)){
            DLinkedNode node = cache.get(key);
            moveToHead(node);
            return node.val;            
        }
        else return -1;
    }
    
    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null){
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.val = value;
            
            cache.put(key, newNode);
            addNode(newNode);
            
            if (cache.size() > capacity){
                DLinkedNode currTail = popTail();
                cache.remove(currTail.key);
            }
        }
        else {
            node.val = value;
            moveToHead(node);
        }
    }
}

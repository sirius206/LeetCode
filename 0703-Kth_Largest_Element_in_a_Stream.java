//1. min Heap, Time O(n log k), Space O(k) 
class KthLargest {
    private int k;
    private PriorityQueue<Integer> minPQ;
    
    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.minPQ = new PriorityQueue<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            minPQ.offer(nums[i]);
            if (minPQ.size() > k) minPQ.poll();
        }
    }
    
    public int add(int val) {
        minPQ.offer(val);
        if (minPQ.size() > k) {
            minPQ.poll();
        }
        return minPQ.peek();
    }
}


//2. C++ multi Set
class KthLargest {
public:
    KthLargest(int k, vector<int> nums) {
        for (int num : nums) {
            st.insert(num);
            if (st.size() > k) st.erase(st.begin());
        }
        K = k;
    }
    
    int add(int val) {
        st.insert(val);
        if (st.size() > K) st.erase(st.begin());
        return *st.begin();
    }

private:
    multiset<int> st;
    int K;
};

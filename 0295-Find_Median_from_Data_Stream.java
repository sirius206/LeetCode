//1. Two heaps Time O(log n), Space O(n)
//left is max heap, right is min heap. 
class MedianFinder {
    public PriorityQueue<Integer> left;
    public PriorityQueue<Integer> right;
    /** initialize your data structure here. */
    public MedianFinder() {
        this.right = new PriorityQueue<>();
        this.left = new PriorityQueue<>((a,b) -> b - a);  //this.left = new PriorityQueue<>(Collections.reverseOrder());
    }
    
    public void addNum(int num) {
        if (left.size() > right.size()) {
            left.offer(num);
            int newNum = left.poll();
            right.offer(newNum);
        }
        else{
            right.offer(num);
            int newNum = right.poll();
            left.offer(newNum);            
        }
    }
    
    public double findMedian() {
        if (left.size() == right.size()){
            return (left.peek() * 1.0) / 2 + (right.peek() * 1.0) / 2;
        }
        else return (double) left.peek();
    }
}


//2. Insertion Sort Time O(n), Space O(n) 
class MedianFinder {
    vector<int> store; // resize-able container

public:
    // Adds a number into the data structure.
    void addNum(int num)
    {
        if (store.empty())
            store.push_back(num);
        else
            store.insert(lower_bound(store.begin(), store.end(), num), num);     // binary search and insertion combined // Move array for insertion, O(n)
    }

    // Returns the median of current data stream
    double findMedian()
    {
        int n = store.size();
        return n & 1 ? store[n / 2] : ((double) store[n / 2 - 1] + store[n / 2]) * 0.5;
    }
};

//3. Multiset and Two Pointers, Time O(log n), Space O(n)

class MedianFinder {
    multiset<int> data;
    multiset<int>::iterator lo_median, hi_median;

public:
    MedianFinder()
        : lo_median(data.end())
        , hi_median(data.end())
    {
    }

    void addNum(int num)
    {
        const size_t n = data.size();   // store previous size

        data.insert(num);               // insert into multiset

        if (!n) {
            // no elements before, one element now
            lo_median = hi_median = data.begin();
        }
        else if (n & 1) {
            // odd size before (i.e. lo == hi), even size now (i.e. hi = lo + 1)

            if (num < *lo_median)       // num < lo
                lo_median--;
            else                        // num >= hi
                hi_median++;            // insertion at end of equal range
        }
        else {
            // even size before (i.e. hi = lo + 1), odd size now (i.e. lo == hi)

            if (num > *lo_median && num < *hi_median) {
                lo_median++;                    // num in between lo and hi
                hi_median--;
            }
            else if (num >= *hi_median)         // num inserted after hi
                lo_median++;
            else                                // num <= lo < hi
                lo_median = --hi_median;        // insertion at end of equal range spoils lo
        }
    }

    double findMedian()
    {
        return ((double) *lo_median + *hi_median) * 0.5;
    }
};

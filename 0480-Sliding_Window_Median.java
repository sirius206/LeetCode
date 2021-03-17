
//1. Two heaps, mine Time O(n k) (removal takes O(k)), Space O(k)

class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        double[] res = new double[len - k + 1];
        PriorityQueue<Integer> left = new PriorityQueue<Integer>(Collections.reverseOrder());
        PriorityQueue<Integer> right = new PriorityQueue<Integer>();
        for (int i = 0; i < len; i++) {
            if (i >= k) {
                int x = nums[i - k];
                if (x <= left.peek()) {
                    left.remove(x);
                } else right.remove(x);
            } 
            right.offer(nums[i]);
            left.offer(right.poll());
            if (left.size() > right.size() + 1) {
            right.offer(left.poll());            
            }
            if (i >= k - 1) {
                if (k % 2 != 0) res[i - k + 1] = left.peek() * 1.0;
                else res[i - k + 1] = (left.peek() * 1.0 + right.peek()) / 2.0;
            }
        }
        return res;
    }
}

//2. Two Heaps (Lazy Removal) Time O(n logk), Space O(n)
//C++
vector<double> medianSlidingWindow(vector<int>& nums, int k)
{
    vector<double> medians;
    unordered_map<int, int> hash_table;
    priority_queue<int> lo;                                 // max heap
    priority_queue<int, vector<int>, greater<int> > hi;     // min heap

    int i = 0;      // index of current incoming element being processed

    // initialize the heaps
    while (i < k)
        lo.push(nums[i++]);
    for (int j = 0; j < k / 2; j++) {
        hi.push(lo.top());
        lo.pop();
    }

    while (true) {
        // get median of current window
        medians.push_back(k & 1 ? lo.top() : ((double)lo.top() + (double)hi.top()) * 0.5);

        if (i >= nums.size())
            break;                          // break if all elements processed

        int out_num = nums[i - k],          // outgoing element
            in_num = nums[i++],             // incoming element
            balance = 0;                    // balance factor

        // number `out_num` exits window
        balance += (out_num <= lo.top() ? -1 : 1);
        hash_table[out_num]++;

        // number `in_num` enters window
        if (!lo.empty() && in_num <= lo.top()) {
            balance++;
            lo.push(in_num);
        }
        else {
            balance--;
            hi.push(in_num);
        }

        // re-balance heaps
        if (balance < 0) {                  // `lo` needs more valid elements
            lo.push(hi.top());
            hi.pop();
            balance++;
        }
        if (balance > 0) {                  // `hi` needs more valid elements
            hi.push(lo.top());
            lo.pop();
            balance--;
        }

        // remove invalid numbers that should be discarded from heap tops
        while (hash_table[lo.top()]) {
            hash_table[lo.top()]--;
            lo.pop();
        }
        while (!hi.empty() && hash_table[hi.top()]) {
            hash_table[hi.top()]--;
            hi.pop();
        }
    }

    return medians;
}

// 1. regular queue. Time O(n), Space O(m)
// n is the size of the moving window, since we need to retrieve N elements from the queue at each invocation of next(val) function.
// m is the length of the queue 

class MovingAverage {

    private int size;
    private ArrayList<Integer> nums = new ArrayList<Integer>();
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.size = size;
    }
    
    public double next(int val) {
        int sum = 0;
        nums.add(val);
        for (int i = 0; i < size; i++) {
            if (nums.size() - i > 0) sum += nums.get(nums.size() -1 - i);
        }
        return sum * 1.0 / Math.min(nums.size(), size);
    }
}

// 2. double-ended queue Time O(1), Space O(n)
class MovingAverage {
  int size, windowSum = 0, count = 0;
  Deque queue = new ArrayDeque<Integer>();

  public MovingAverage(int size) {
    this.size = size;
  }

  public double next(int val) {
    ++count;
    // calculate the new sum by shifting the window
    queue.add(val);
    int tail = count > size ? (int)queue.poll() : 0;

    windowSum = windowSum - tail + val;

    return windowSum * 1.0 / Math.min(size, count);
  }
}

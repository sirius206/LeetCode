//1. max Heap, Time: O (XN), Space: O(K)
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
        int n = matrix.length;
        int i_max = n;
        int j_max = n;
        for (int i = 0; i < n; i++) {
            if (j_max == 0) break;
            for (int j = 0; j < j_max; j++) {
                if (maxPQ.size() < k) maxPQ.offer(matrix[i][j]);
                else if (matrix[i][j] < maxPQ.peek()) {
                    maxPQ.poll();
                    maxPQ.offer(matrix[i][j]);
                }
                else {
                    j_max = j;
                }
            }
        }
        return maxPQ.peek();
    }
}

//2. Min heap, Time: let X=min(K,N);X+Klog(X), Space: O(X)
class MyHeapNode {

  int row;
  int column;
  int value;

  public MyHeapNode(int v, int r, int c) {
    this.value = v;
    this.row = r;
    this.column = c;
  }

  public int getValue() {
    return this.value;
  }

  public int getRow() {
    return this.row;
  }

  public int getColumn() {
    return this.column;
  }
}

class MyHeapComparator implements Comparator<MyHeapNode> {
  public int compare(MyHeapNode x, MyHeapNode y) {
    return x.value - y.value;
  }
}

class Solution {

  public int kthSmallest(int[][] matrix, int k) {

    int N = matrix.length;

    PriorityQueue<MyHeapNode> minHeap =
        new PriorityQueue<MyHeapNode>(Math.min(N, k), new MyHeapComparator());

    // Preparing our min-heap
    for (int r = 0; r < Math.min(N, k); r++) {

      // We add triplets of information for each cell
      minHeap.offer(new MyHeapNode(matrix[r][0], r, 0));
    }

    MyHeapNode element = minHeap.peek();
    while (k-- > 0) {

      // Extract-Min
      element = minHeap.poll();
      int r = element.getRow(), c = element.getColumn();

      // If we have any new elements in the current row, add them
      if (c < N - 1) {

        minHeap.offer(new MyHeapNode(matrix[r][c + 1], r, c + 1));
      }
    }

    return element.getValue();
  }
}

//3. Binaray search Time: O(N×log(Max−Min)), Space: O(1) 
class Solution {

  public int kthSmallest(int[][] matrix, int k) {

    int n = matrix.length;
    int start = matrix[0][0], end = matrix[n - 1][n - 1];
    while (start < end) {

      int mid = start + (end - start) / 2;
      // first number is the smallest and the second number is the largest
      int[] smallLargePair = {matrix[0][0], matrix[n - 1][n - 1]};

      int count = this.countLessEqual(matrix, mid, smallLargePair);

      if (count == k) return smallLargePair[0];

      if (count < k) start = smallLargePair[1]; // search higher
      else end = smallLargePair[0]; // search lower
    }
    return start;
  }

  private int countLessEqual(int[][] matrix, int mid, int[] smallLargePair) {

    int count = 0;
    int n = matrix.length, row = n - 1, col = 0;

    while (row >= 0 && col < n) {

      if (matrix[row][col] > mid) {

        // as matrix[row][col] is bigger than the mid, let's keep track of the
        // smallest number greater than the mid
        smallLargePair[1] = Math.min(smallLargePair[1], matrix[row][col]);
        row--;

      } else {

        // as matrix[row][col] is less than or equal to the mid, let's keep track of the
        // biggest number less than or equal to the mid
        smallLargePair[0] = Math.max(smallLargePair[0], matrix[row][col]);
        count += row + 1;
        col++;
      }
    }

    return count;
  }
}

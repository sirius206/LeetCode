//1. DP use set Time:O(n^1.5) Space:O(n)

class Solution {
    public int numSquares(int n) {
        if (n == 0) return 0;
        int[] dp = new int[n + 1];
        Set<Integer> ps = new HashSet<Integer>();
        int i = 1;
        while (i * i <= n){
            ps.add(i * i);
            i++;
        }
        for (i = 1; i <= n; i++){
            int min = i;
            for (int x : ps){
                if (i - x == 0) {
                    min = 1;
                    break;
                }
                else if (i - x > 0 && dp[i - x] != 0){
                    min = Math.min(min, 1 + dp[i - x]);
                }
            }
            dp[i] = min;
        }
        return dp[n];
    }
}

//1.5 use array, faster
class Solution {

  public int numSquares(int n) {
    int dp[] = new int[n + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    // bottom case
    dp[0] = 0;

    // pre-calculate the square numbers.
    int max_square_index = (int) Math.sqrt(n) + 1;
    int square_nums[] = new int[max_square_index];
    for (int i = 1; i < max_square_index; ++i) {
      square_nums[i] = i * i;
    }

    for (int i = 1; i <= n; ++i) {
      for (int s = 1; s < max_square_index; ++s) {
        if (i < square_nums[s])
          break;
        dp[i] = Math.min(dp[i], dp[i - square_nums[s]] + 1);
      }
    }
    return dp[n];
  }
}

//2. Greedy Enumeration Time:O(n^h) Space:O(n^0.5) where h is the maximal number of recursion that could happen.
class Solution {
  Set<Integer> square_nums = new HashSet<Integer>();

  protected boolean is_divided_by(int n, int count) {
    if (count == 1) {
      return square_nums.contains(n);
    }

    for (Integer square : square_nums) {
      if (is_divided_by(n - square, count - 1)) {
        return true;
      }
    }
    return false;
  }

  public int numSquares(int n) {
    this.square_nums.clear();

    for (int i = 1; i * i <= n; ++i) {
      this.square_nums.add(i * i);
    }

    int count = 1;
    for (; count <= n; ++count) {
      if (is_divided_by(n, count))
        return count;
    }
    return count;
  }
}

//3.Greedy + BFS  Time:O(n^h/2) Space:O(n^0.5h) where h is the height of the N-ary tree
class Solution {
  public int numSquares(int n) {

    ArrayList<Integer> square_nums = new ArrayList<Integer>();
    for (int i = 1; i * i <= n; ++i) {
      square_nums.add(i * i);
    }

    Set<Integer> queue = new HashSet<Integer>();
    queue.add(n);

    int level = 0;
    while (queue.size() > 0) {
      level += 1;
      Set<Integer> next_queue = new HashSet<Integer>();

      for (Integer remainder : queue) {
        for (Integer square : square_nums) {
          if (remainder.equals(square)) {
            return level;
          } else if (remainder < square) {
            break;
          } else {
            next_queue.add(remainder - square);
          }
        }
      }
      queue = next_queue;
    }
    return level;
  }
}

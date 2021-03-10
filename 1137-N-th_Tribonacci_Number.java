//1. DP Time O(n) Space O(n)
class Solution {
    public int tribonacci(int n) {
        if (n <= 1) return n;
        if (n == 2) return 1;
        int[] res = new int[n + 1];
        res[0] = 0;
        res[1] = 1;
        res[2] = 1;
        if (n <= 2) return res[n];
        for (int i = 3; i <= n; i++) {
            res[i] = res[i - 1] + res[i - 2] + res[i - 3];
        }
        return res[n];
    }
}

// optimize DP space, only keep the last 3, Time O(n) Space O(1)
class Solution {
  public int tribonacci(int n) {
    if (n < 3) return n == 0 ? 0 : 1;

    int tmp, x = 0, y = 1, z = 1;
    for (int i = 3; i <= n; ++i) {
      tmp = x + y + z;
      x = y;
      y = z;
      z = tmp;
    }
    return z;
  }
}

//2. recursion time limit exceeded
class Solution {
    public int tribonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 1;
        return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
    }
}

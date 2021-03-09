1. DP Time O(n), Space O(n)
class Solution {
    public int climbStairs(int n) {
        if (n <= 2) return n; 
        int [] count = new int[n];
        count[0] = 1;
        count[1] = 2;
        for (int i = 2; i < n; i++) {
            count [i] = count[i-1] + count[i-2]; 
        }
        return count[n-1];
    }
}

2. Recursion brutal force Time O(2^n), Space O(n)
public class Solution {
    public int climbStairs(int n) {
        return climb_Stairs(0, n);
    }
    public int climb_Stairs(int i, int n) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        return climb_Stairs(i + 1, n) + climb_Stairs(i + 2, n);
    }
}

3. Recursion with Memoization Time O(n), Space O(n)
public class Solution {
    public int climbStairs(int n) {
        int memo[] = new int[n + 1];
        return climb_Stairs(0, n, memo);
    }
    public int climb_Stairs(int i, int n, int memo[]) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (memo[i] > 0) {
            return memo[i];
        }
        memo[i] = climb_Stairs(i + 1, n, memo) + climb_Stairs(i + 2, n, memo);
        return memo[i];
    }
}

4 Fibbonacci

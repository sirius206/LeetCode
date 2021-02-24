//1: recursion Time O(2^n), Space O(n) 
class Solution {
    public int fib(int n) {
        if (n <= 1) return n;
        return (fib(n-1) + fib(n-2));
    }
}


//2 DP Time O(n), Space O(n) (can be O(1))
class Solution {
    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int [] fibArray = new int[n+1];
        fibArray[0] = 0;
        fibArray[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            fibArray[i] = fibArray[i - 1] + fibArray[i - 2];
        }
        return fibArray[n];
    }
}

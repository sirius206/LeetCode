//1. Set Time O(logn) Space O(1)
//Finding the next value for a given number has a cost of O(\log n)O(logn) because we are processing each digit in the number, 
//and the number of digits in a number is given by logn
class Solution {
    public boolean isHappy(int n) {
        Set <Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)){
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
    }
    
    private int getNext(int n) {
        int res = 0;
        while (n > 0) {
            int d = (n % 10);
            res += d * d;
            n = n / 10;
        }
        return res;
    }
}


//2. Floyd's Cycle-Finding Algorithm, fast slow pointer
class Solution {

     public int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public boolean isHappy(int n) {
        int slowRunner = n;
        int fastRunner = getNext(n);
        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }
}

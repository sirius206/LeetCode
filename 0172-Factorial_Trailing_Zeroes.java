//1.  count 5 Time O(n), Space O(1) 
class Solution {
    public int trailingZeroes(int n) {

        int zeroCount = 0;
        for (int i = 5; i <= n; i += 5) {
            int currentFactor = i;
            while (currentFactor % 5 == 0) {
                zeroCount++;
                currentFactor /= 5;
            }
        }
        return zeroCount;
    }
}

//2 count 5 efficiently  Time O(log n), Space O(1) 

class Solution {
public int trailingZeroes(int n) {
    int zeroCount = 0;
    // We need to use long because currentMultiple can potentially become
    // larger than an int.
    long currentMultiple = 5;
    while (n >= currentMultiple) {
        zeroCount += (n / currentMultiple);
        currentMultiple *= 5;
    }
    return zeroCount;
}
}

//or 
class Solution {
public int trailingZeroes(int n) {
    int zeroCount = 0;
    long currentMultiple = 5;
    while (n > 0) {
        n /= 5;
        zeroCount += n;
    }
    return zeroCount;
}
}

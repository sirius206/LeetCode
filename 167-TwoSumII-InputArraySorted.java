//-----------------------------------------------------------
//Method 1: binary search
//runtime: O(NlogN)
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int x = target - numbers[i];
//对称性，i肯定比x的index小， lo从i+1开始考虑即可， 也避免了 x=target/2时num[i]跟num[i+1]相等时的index情况   
            int lo = i+1;
            int hi = numbers.length -1;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (x < numbers[mid])  hi = mid - 1;
                else if (x > numbers[mid])  lo = mid + 1;
                else {
                    return new int[]{i+1, mid+1};
                }
                continue;
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}

//-----------------------------------------------------------
//method 2: two pointers
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int lo = 0;
        int hi = numbers.length -1;
        while (lo <= hi) {
            int sum = numbers[lo] + numbers[hi];
            if (sum > target) hi--;
            else if (sum < target) lo++;
            else return new int[]{lo+1, hi+1};
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    
}

//-----------------------------------------------------------
//Method 1: binary search
//runtime: O(NlogN)
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int x = target - numbers[i];
            if (x < numbers[i]) {
                int lo = 0;
                int hi = i-1;
            }
            else if (x > numbers[i]) {
                int lo = i+1;
                int hi = numbers.length -1;
            }
            else {
                if (numbers[i+1] == numbers[i]) return new int[]{i+1,i+2};
                else throw new IllegalArgumentException("No two sum solution");
            }
            int lo = 0;
            int hi = numbers.length -1;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (x < numbers[mid])  hi = mid - 1;
                else if (x > numbers[mid])  lo = mid + 1;
                else {
                    if (i < mid) return new int[]{i+1, mid+1};
                    else if (i > mid)return new int[]{mid+1, i+1};
                }
                continue;
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}

//-----------------------------------------------------------
//method 2: two pointers


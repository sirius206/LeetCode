//1. two pointers, Time O(n), Space O(1) 
// i is the location of target element, only increment when being replaced
//没找到之前i和j都是一起移动，找到之后i停住， j后移直到找到非target之后nums[i]被nums[j]取代（nums[j]不变）， i，j再移动
//有unnessesary swap
class Solution {
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
}

// 2.Two Pointers - when elements to remove are rare Time O(n), Space O(1) 
// 和最后一位换，并不断缩小size

public int removeElement(int[] nums, int val) {
    int i = 0;
    int n = nums.length;
    while (i < n) {  //跟n比较 却跟n-1换，保证最后一个element也被check
        if (nums[i] == val) {
            nums[i] = nums[n - 1];
            // reduce array size by one
            n--;
        } else {
            i++;
        }
    }
    return n;
}

//3. binary search after sort， mine， Time O(n log n), Space O(1) 
class Solution {
    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        Arrays.sort(nums);
        int lo = 0;
        int hi = len - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == val){
                hi = mid + 1;
                while (hi <= len - 1 && nums[hi] == val){
                    hi++;
                }                    

                lo = mid - 1;
                while (lo >= 0 && nums[lo] == val){
                    lo--;
                }                    

                break;
            }
            if (nums[mid] < val) {
                lo = mid + 1;
            }
            else {
                hi = mid - 1;
            }
        }
        hi--;
        lo++;
        int removed = hi - lo + 1;
        if (removed == len){
            nums = null;
            return 0;
        }
        for (int i = 0; i < removed; i++){
            int temp = nums[hi - i];
            nums[hi - i] = nums[len - 1 - i];
            nums[len - 1 - i] = temp;
        }
        
        return len - removed;
    }
}

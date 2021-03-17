//1. DP 遍历两遍 Time O(n), Space O(n)
class Solution {
    public int trap(int[] height) {
        int len = height.length;
        int[] left = new int[len];
        int[] right = new int[len];
        int left_max = 0;
        int right_max = 0;
        int res = 0;
        for (int i = 0; i < len; i++) {
            left_max = Math.max(left_max, height[i]);
            right_max = Math.max(right_max, height[len - 1- i]);
            left[i] = left_max;
            right[len - 1 - i] = right_max;
        }
        for (int i = 0; i < len; i++) {
            res += Math.min(left[i], right[i]) - height[i];
        }
        return res;
    }
}

//2.Two pointers 一遍 Time O(n), Space O(1)
class Solution {
    public int trap(int[] height) {
        int len = height.length;
        int left = 0;
        int right = len - 1;
        int res = 0;
        int low = 0;
        while (left < right) {
            low = Math.min(height[left],height[right]);
            if (height[left] <= height[right]){
                left++;
                while (left < right && height[left] < low) {
                    res += low - height[left];
                    left++;
                }
            } else{
                right--;
                while (left < right && height[right] < low) {
                    res += low - height[right];
                    right--;
                }
            }
                
        }
        return res;
    }
}

//3. Stack Time O(n), Space O(n)
class Solution {
    public int trap(int[] height) {
        Stack<Integer> s = new Stack<Integer>();
        int i = 0, n = height.length, res = 0;
        while (i < n) {
            if (s.isEmpty() || height[i] <= height[s.peek()]) {
                s.push(i++);
            } else {
                int t = s.pop();
                if (s.isEmpty()) continue;
                res += (Math.min(height[i], height[s.peek()]) - height[t]) * (i - s.peek() - 1);
            }
        }
        return res;
    }
}

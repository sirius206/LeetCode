//Two pointers Time O(n) Space O(1)
//If we try to move the pointer at the longer line inwards, we won't gain any increase in area, since it is limited by the shorter line.
//移动短的那条线
class Solution {
    public int maxArea(int[] height) {
        int lo = 0;
        int hi = height.length - 1;
        int max = 0; 
        while(lo < hi){
            max = Math.max(max, (Math.min(height[lo],height[hi]) * (hi - lo)));
            if (height[lo] <= height[hi]){
                lo++;
                }
            else{
                hi--;
            }           
        }
        return max;
    }
}

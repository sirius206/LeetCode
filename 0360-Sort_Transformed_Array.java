Time O(n), Space O(1)
/*
Always start handling from the two ends of the parabola. 
The difference is when a > 0, they are larger at two ends, when a < 0, they are smaller at two ends. 
So when a > 0, we just start to fill out the result array from end to beginning, 
in other case, we start filling out it from start to the end of result array.
*/

pclass Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int[] res = new int[n];
        if (nums == null || n == 0) return res;
        int left = 0; 
        int right = n - 1;
        int i = 0;
        while (i < n){
            int y_left = func(nums[left], a, b, c);
            int y_right = func(nums[right], a, b, c);
            //a > 0, larger at two ends, fill from right to left
            if (a >= 0){
                if (y_left >= y_right){
                    res[n - i - 1] = y_left;
                    left++;
                }
                else {
                    res[n - i - 1] = y_right;
                    right--;
                }
            }
            //a < 0, smaller at two ends, fill from left to right
            else{
                if (y_left <= y_right){
                    res[i] = y_left;
                    left++;
                }
                else {
                    res[i] = y_right;
                    right--;
                }                
            }
            i++;
        }        
       return res; 
    }
    
    public int func(int x, int a, int b, int c){
      return a * x * x + b * x + c;    
    }
}

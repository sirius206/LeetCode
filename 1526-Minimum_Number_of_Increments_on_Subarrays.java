Time O(n) Space O(1)
/*
We encounter a number less than a[0] (a[1] < a[0]): In this case we can simply reuse the same operations that we did for a[0]. 
i.e If array was (3, 2), we can first perform 3 operations and then use 2 of the same operations in next term. 
However, going forward, we will only have a[1] operations available for reuse.

We encounter a number greater than a[0] (a[1] > a[0]): In this case we can simply reuse the same operations that we did for a[0]. 
And additionally, we will perform a[1] - a[0] more operation to reach a[1]. Again, going forward, we will have a[1] operations available for reuse.
*/

class Solution {
    public int minNumberOperations(int[] target) {
        int totalOperations = target[0];
        for (int i = 1; i < target.length; ++i) {
            if (target[i] > target[i-1]) {
                totalOperations += target[i] - target[i-1];
            }
        }
        return totalOperations;
    }
}

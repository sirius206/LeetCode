//Method 1 by myself
//add two boudaries start - 1 and end + 1
//Runtime: O(N), space O(1)

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> findMissingRanges(int[] vals, int start, int end) {
        List<String> ranges = new ArrayList<>();
        if (vals.length == 0) {
            ranges.add(Integer.toString(start) + "" + Integer.toString(end));
            return ranges;
        }
        int prev = start - 1;
        int current;
        for (int i = 1; i < vals.length + 2; i++) {
            if (i != vals.length + 1) {
            current = vals[i-1];}
            else current = end + 1;
            if (current - prev == 2) {
                ranges.add(Integer.toString(prev + 1));
            }
            if (current - prev > 2) {
                ranges.add(Integer.toString(prev + 1) + "->" + Integer.toString(current - 1));
            }
            prev = current;
        }

    return ranges;
    }

}



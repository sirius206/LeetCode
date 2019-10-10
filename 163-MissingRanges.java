import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> findMissingRanges(int[] vals, int start, int end) {
        List<String> ranges = new ArrayList<>();
        if (vals.length == 0) {
            ranges.add(Integer.toString(start) + "->" + Integer.toString(end));
            return ranges;
        }
        int prev = start - 1;
        int current;
        for (int i = 0; i <= vals.length; i++) {
            if (i != vals.length) {
            current = vals[i];}
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



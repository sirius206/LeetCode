// Time O(nlogn), Space O(1)
class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        
        Arrays.sort(boxTypes, (a,b) -> b[1] - a[1]);
        int max_Unit = 0;
        int remainSize = truckSize;
        for (int i = 0; i < Math.min(truckSize, boxTypes.length); i++) {
            if (remainSize >= boxTypes[i][0]) {
                max_Unit += boxTypes[i][1] * boxTypes[i][0];
                remainSize -= boxTypes[i][0];
            }
            else {
                max_Unit += remainSize * boxTypes[i][1];
                return max_Unit;
            }
        }
        return max_Unit;
    }
}

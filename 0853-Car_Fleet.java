// Time O(nlogn), Space O(n)
//put position and speed together, sort by distance closet to target, 
//calculate time to arrival, if next car's eta < previous car, they are in the same fleet 

class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int len = position.length;
        if (len == 0) return 0;
        int[][] cars = new int[position.length][2];
        for (int i = 0; i < len; i++){
            cars[i][0] = target - position[i];
            cars[i][1] = speed[i];
        }
        Arrays.sort(cars, (a, b) -> a[0] - b[0]);
        double[] eta = new double[len];
        for (int i = 0; i < position.length; i++){
            eta[i] = cars[i][0] * 1.0 / cars[i][1];
        }
        
        int count = 1;
        for (int i = 1; i < len; i++){
            if (eta[i] <= eta[i - 1]){
                eta[i] = eta[i - 1];
            }
            else count++;
        }
        return count;
        // or use cur to indicate current slowest
        // double cur = 0; for: if eta[i] > cur: cur = eta[i]; count++;      
      
    }
}

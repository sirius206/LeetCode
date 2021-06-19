//1. 只有三种情况，上或下是top[0]， 或是bottom[0], 或不可能
// Time O(n), space O(1)

class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int[] picks = new int[]{tops[0], bottoms[0]};
        int res = check(tops[0], tops, bottoms);
        if (res != -1) return res;        // better if (res != -1 || tops[0] == bottoms[0]) 
        else return (check(bottoms[0], tops, bottoms));
        
    }
    
    private int check(int x, int[] tops, int[] bottoms){
        int n = tops.length;
        int changeTop = 0;
        int changeBottom = 0;
        for (int i = 0; i < n; i++){
            if (tops[i] != x && bottoms[i] != x){
                return -1;
            }
            else if (tops[i] == x && bottoms[i] != x){
                changeBottom++;
            }
            else if (tops[i] != x && bottoms[i] == x){
                changeTop++;
            }
        }
        return Math.min(changeTop, changeBottom);
    }
    
}


// 2. mine，find max ocurrance,  not efficient, 
class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int[] totalCount = new int[6];
        int[] topCount = new int[6];
        int[] bottomCount = new int[6];
        int maxId = -1;
        int maxCount = 0;
        for (int i = 0; i < tops.length; i++){
            totalCount[tops[i] - 1]++;
            topCount[tops[i] - 1]++;
            if (totalCount[tops[i] - 1] > maxCount){
                maxCount = totalCount[tops[i] - 1];
                maxId = tops[i] - 1;
            }            
            
            totalCount[bottoms[i] - 1]++;
            bottomCount[bottoms[i] - 1]++;
            if (totalCount[bottoms[i] - 1] > maxCount){
                maxCount = totalCount[bottoms[i] - 1];
                maxId = bottoms[i] - 1;
            }           
        }
        
        int count = 0;
        for (int i = 0; i < tops.length; i++){
            if (tops[i] - 1 != maxId && bottoms[i] - 1 != maxId){
                return -1;
            }
            if (topCount[maxId] > bottomCount[maxId]){
                if (tops[i] - 1 != maxId && bottoms[i] - 1 == maxId){
                    count++;
                }
            }
            else {
                if (bottoms[i] - 1 != maxId && tops[i] - 1 == maxId){
                    count++;
                }
            }            
        }
        
        return count;
    }
}

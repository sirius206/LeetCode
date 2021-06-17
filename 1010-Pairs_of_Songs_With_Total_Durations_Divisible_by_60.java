//take mod, save mod frequencies in an array of length 60, then count
//ans: loop while count
//Time O(n) Space O(1)
class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int remainders[] = new int[60];
        int count = 0;
        for (int t: time) {
            if (t % 60 == 0) { // check if a%60==0 && b%60==0
                count += remainders[0];
            } else { // check if a%60+b%60==60
                count += remainders[60 - t % 60];
            }
            remainders[t % 60]++; // remember to update the remainders
        }
        return count;
    }
}




//Mine, more complicated for 30 and 60, n choose 2, Cn2 = n *(n - 1) / 2
//Time O(n) Space O(1)
class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int[] count = new int[60];
        for (int i = 0; i < time.length; i++){
            int rem = time[i] % 60;
            count[rem]++;
        }
        int res = count[0] * (count[0] - 1) / 2+ count[30] * (count[30] - 1) / 2;
        int i = 1; 
        int j = 59;
        while (i < j){
            res += count[i] * count[j];
            i++;
            j--;
        }
        return res;
    }
}

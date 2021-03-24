//1. HashMap Time O(n) , Space O(n) 
class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        for (int i : count.keySet()){
            if (count.get(i) == 1) return i;
        }
        return -1;
    }
}

//2. Bit Manipulation Time O(n) , Space O(1) 

class Solution {
  public int singleNumber(int[] nums) {
    int a = 0;
    for (int num : nums) {
      a ^= num;
    }
    return a;
  }
}

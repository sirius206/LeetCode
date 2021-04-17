//better dp, 3 pointers Time O(n) Space O(1)
public class Solution {
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        for(int i = 1;i < n; i++){
            int min = Math.min(Math.min(ugly[i2] * 2, ugly[i3] * 3), ugly[i5] * 5);
            ugly[i] = min;
            if(ugly[i2] * 2 == min) i2++;
            if(ugly[i3] * 3 == min) i3++;
            if(ugly[i5] * 5 == min) i5++;
        }
        return ugly[n-1];
    }
}

//heap Time O(n) Space O(1)
class Ugly {
  public int[] nums = new int[1690];
  Ugly() {
    HashSet<Long> seen = new HashSet();
    PriorityQueue<Long> heap = new PriorityQueue<Long>();
    seen.add(1L);
    heap.add(1L);

    long currUgly, newUgly;
    int[] primes = new int[]{2, 3, 5};
    for(int i = 0; i < 1690; ++i) {
      currUgly = heap.poll();
      nums[i] = (int)currUgly;
      for(int j : primes) {
        newUgly = currUgly * j;
        if (!seen.contains(newUgly)) {
          seen.add(newUgly);
          heap.add(newUgly);
        }
      }
    }
  }
}

class Solution {
  public static Ugly u = new Ugly();
  public int nthUglyNumber(int n) {
    return u.nums[n - 1];
  }
}


//naiive dp, exceed time limit
class Solution {
    public int nthUglyNumber(int n) {
        int count = 1;
        Set<Integer> dp = new HashSet<>();
        dp.add(1);
        int i = 1;
        search: while (count < n) {
            i++;
            int x = i;
            while (x % 2 == 0) {
                x = x / 2;
                if (dp.contains(x)){
                    dp.add(i);
                    count++;
                    continue search;
                }
            }
            while (x % 3 == 0) {
                x = x / 3;
                if (dp.contains(x)){
                    dp.add(i);
                    count++;
                    continue search;
                }
            }
            while (x % 5 == 0) {
                x = x / 5;
                if (dp.contains(x)){
                    dp.add(i);
                    count++;
                    continue search;
                }
            }
        }
        return i;
    }
}

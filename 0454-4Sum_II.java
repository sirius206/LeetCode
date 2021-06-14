//1. HashMap 前两数组的和排列组合 hm.add(和，重复数) 后两数组排列组合查找hm里有无
// Time O(n^2) Space O(n^2)
public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
    int cnt = 0;
    Map<Integer, Integer> m = new HashMap<>();
    for (int a : A)
        for (int b : B)
            m.put(a + b, m.getOrDefault(a + b, 0) + 1);
    for (int c : C)
        for (int d : D)
            cnt += m.getOrDefault(-(c + d), 0);
    return cnt;
}

//2. Mine 两两合并数组 two pointers 注意重复的数
class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int count = 0;
        int n = nums1.length;
        int[] temp1 = new int[n * n];
        int[] temp2 = new int[n * n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                temp1[i * n + j] = nums1[i] + nums2[j];
                temp2[i * n + j] = nums3[i] + nums4[j];
            }
        }
        Arrays.sort(temp1);
        Arrays.sort(temp2);
        int lo = 0;
        int hi = n * n - 1;
        while (lo < n * n && hi >= 0){
            if (temp1[lo] + temp2[hi] == 0) {
                int dup1 = 1;
                int dup2 = 1;
                lo++;
                hi--;
                while (lo < n * n && temp1[lo] == temp1[lo - 1]){
                    dup1++;
                    lo++;
                }
                while (hi >= 0 && temp2[hi] == temp2[hi + 1]){
                    dup2++;
                    hi--;
                }                
                count+= dup1 * dup2;

            }
            else if (temp1[lo] + temp2[hi] < 0){
                lo++;
            }
            else hi--;
        }
    return count;
    }
    
}

//3. K-sum II
//we will divide k arrays into two groups. For the first group, we will have k/2
//  nested loops to count sums. Another k/2 nested loops will enumerate arrays in the second group and search for complements.

public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
    return kSumCount(new int[][]{A, B, C, D});
}
public int kSumCount(int[][] lists) {
    Map<Integer, Integer> m = new HashMap<>();
    addToHash(lists, m, 0, 0);
    return countComplements(lists, m, lists.length / 2, 0);
}
void addToHash(int[][] lists, Map<Integer, Integer> m, int i, int sum) {
    if (i == lists.length / 2)
        m.put(sum, m.getOrDefault(sum, 0) + 1);
    else
        for (int a : lists[i])
            addToHash(lists, m, i + 1, sum + a);
}
int countComplements(int[][] lists, Map<Integer, Integer> m, int i, int complement) {
    if (i == lists.length)
        return m.getOrDefault(complement, 0);
    int cnt = 0;
    for (int a : lists[i])
        cnt += countComplements(lists, m, i + 1, complement - a);
    return cnt;
}

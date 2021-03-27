//1. HashMap Time O(n) , Space O(L)
class SparseVector {
    Map<Integer, Integer> map = new HashMap<>();
    int len;
    SparseVector(int[] nums) {
        this.len = nums.length;
        for (int i = 0; i < len; i++){
            if (nums[i] != 0) {
                map.put(i, nums[i]);
            }
        }
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        Map<Integer, Integer> map2 = vec.map;
        int res = 0;
        for (int i : map.keySet()){   //don't iterate to n
            if (map2.containsKey(i)){
                res += map.get(i) * map2.get(i);
            }
        }
        return res;
    }
}

//2. List of pairs Time O(n) , Space O(L)
class SparseVector {

    List<int[]> pairs;

    SparseVector(int[] nums) {
        pairs = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                pairs.add(new int[]{i, nums[i]});
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int result = 0, p = 0, q = 0;
        while (p < pairs.size() && q < vec.pairs.size()) {
            if (pairs.get(p)[0] == vec.pairs.get(q)[0]) {
                result += pairs.get(p)[1] * vec.pairs.get(q)[1];
                p++;
                q++;
            }
            else if (pairs.get(p)[0] > vec.pairs.get(q)[0]) {
                q++;
            }
            else {
                p++;
            }
        }
        return result;
    }
}

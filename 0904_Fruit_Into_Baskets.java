//1. ans  Time O(n), Space O(1)
//longest subarry with 2 elements 

class Solution {
    public int totalFruit(int[] tree) {
        // track last two fruits seen
        int lastFruit = -1;
        int secondLastFruit = -1;
        int lastFruitCount = 0;
        int currMax = 0;
        int max = 0;
        
        for (int fruit : tree) {
            if (fruit == lastFruit || fruit == secondLastFruit)
                currMax++;
            else
                currMax = lastFruitCount + 1; // last fruit + new fruit
            
            if (fruit == lastFruit)
                lastFruitCount++;
            else {
                lastFruitCount = 1; 
                secondLastFruit = lastFruit;
                lastFruit = fruit;
            }
            
            max = Math.max(max, currMax);
        }
        
        return max;
    }
}

//2. ans Time O(n), Space O(1) (hashmap has 2 items)
class Solution {
    public int totalFruit(int[] tree) {
        int start = 0;
        int n = tree.length;
        int maxLength = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int end = 0; end < n; end++) {
            map.put(tree[end], end);      //update last location every time
            if (map.size() > 2) {
                int minIndex = Collections.min(map.values());   //find the second last tree's index
                map.remove(tree[minIndex]);  
                start = minIndex + 1;   //new start from last 
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }
}

//2. Mine Time O(n^2), Space O(1)
class Solution {
    public int totalFruit(int[] tree) {
        int p1 = 0;
        int p2 = 0;
        int count = 0;
        int curr_count = 0;
        List<Integer> types = new ArrayList<>();
        while (p2 <= tree.length - 1){
            if (types.size() < 2){
                if (!types.contains(tree[p2])){
                    types.add(tree[p2]);
                }
                curr_count++;
            }
            else{
                if (types.contains(tree[p2])){
                    curr_count++;                
                }
                else{
                    p1 = p2 - 1;
                    while(p1 - 1 >= 0 && tree[p1 - 1] == tree[p1]){
                        p1--;
                    }
                    types.remove(0);
                    types.remove(0);
                    types.add(tree[p1]);
                    types.add(tree[p2]);
                    curr_count = p2 - p1 + 1;
                }
            }
            count = Math.max(curr_count, count);
            p2++;
        }
        return count;
    }
}

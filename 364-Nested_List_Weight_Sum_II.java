//1. One pass, BFS
//Time O(n), Space O(n)
/*
Idea behind this is
a + (a+b) + (a+b+c) = 3a + 2b + c

a is integers at level 1.
b is integers at level 2.
c is integers at level 3.

a is levelSum
a + b is prevSum + levelSum
a + (a +b) is totalSum + prevSum
*/
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int prevSum = 0, totalSum = 0;
        Deque<NestedInteger> queue = new ArrayDeque();
        for (NestedInteger ni : nestedList) {
            queue.offerLast(ni);
        }
        
        while (!queue.isEmpty()) {
            int size = queue.size(), levelSum = 0;
            for (int i = 0; i < size; i++) {
                NestedInteger current = queue.pollFirst();
                if (current.isInteger()) {
                    levelSum += current.getInteger();
                } else {
                    for (NestedInteger ni: current.getList()) {
                        queue.offerLast(ni);
                    }
                }
            }
            prevSum += levelSum;
            totalSum += prevSum;
        }
        return totalSum;
    }


//1. DFS, mine, two passes, first find out depth
class Solution {
    List<int[]> record = new ArrayList<>();
    int max_depth = 0;
    public int depthSumInverse(List<NestedInteger> nestedList) {
        dfs(nestedList, 1);
        int res = 0;
        for (int[] info : record){
            int weight = max_depth - info[1] + 1;
            res += info[0] * weight;
        }
        return res;        
    }
    public void dfs(List<NestedInteger> nestedList, int depth){
        for (NestedInteger item : nestedList){
            if (item.isInteger()){
                record.add(new int[]{item.getInteger(), depth});
                max_depth = Math.max(max_depth, depth);
            }
            else{
                dfs(item.getList(), depth + 1);
            }
        }
    }
}

//2. BFS, mine two passes, first find out depth
class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        List<int[]> record = new ArrayList<>();
        Queue<NestedInteger> q = new LinkedList<>();
        //q.addAll(nestedList);
        for (NestedInteger item : nestedList){
            q.offer(item);
        }
        int depth = 1;
        int res = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size; i++){
                NestedInteger item = q.poll();
                if (item.isInteger()) {
                    record.add(new int[]{item.getInteger(), depth});
                }
                else {
                //q.addAll(item.getList());
                    for (NestedInteger next_item : item.getList()){
                        q.offer(next_item);
                    }
                }
            }
            depth++;
        }
        depth--;
        for (int[] info : record){
            int weight = depth - info[1] + 1;
            res += info[0] * weight;
        }
        return res;
    }
}

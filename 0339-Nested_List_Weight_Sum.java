
//1.a dfs, Time O(n), Space O(n)
//mine

class Solution {
    int res = 0;
    public int depthSum(List<NestedInteger> nestedList) {
        dfs(nestedList, 1);
        return res;
    }
    
    public void dfs(List<NestedInteger> nestedList, int depth) {
        for (NestedInteger item : nestedList){
            if (item.isInteger()) {
                res += item.getInteger() * depth;
                //do not return here since list is not finished
            }    
            else {
                List<NestedInteger> list = item.getList();
                dfs(list, depth + 1);
            }
            
        }

    }    
}

//1.b answer, dfs has return type
class Solution {

    public int depthSum(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }

    private int dfs(List<NestedInteger> list, int depth) {
        int total = 0;
        for (NestedInteger nested : list) {
            if (nested.isInteger()) {
                total += nested.getInteger() * depth;
            } else {
                total += dfs(nested.getList(), depth + 1);
            }
        }
        return total;
    }
}

//1.c mine
class Solution {
    int res = 0;
    public int depthSum(List<NestedInteger> nestedList) {
        for (NestedInteger nestedInt : nestedList) {
            dfs(nestedInt, 1);
        }
        return res;
    }
    
    public void dfs(NestedInteger nestedInt, int depth) {
        if (nestedInt.isInteger()) {
            res += nestedInt.getInteger() * depth;
            return;
        }
        else {
            List<NestedInteger> list = nestedInt.getList();
            for (NestedInteger item : list){
                dfs(item, depth + 1);
            }
        }
    }    
}

//2. BFS
class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
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
                    res += item.getInteger() * depth;
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
        return res;
    }
}

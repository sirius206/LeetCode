//1 DFS Time O(n * 2^n)(need O(n) to store ), Space: O(n), good solution
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        List<Integer> current = new ArrayList<>();
        helper(nums, 0, res, current);
        return res;
    }
    
    public void helper(int[] nums, int start, List<List<Integer>> res, List<Integer> current) {
        res.add(new ArrayList<>(current));
        for(int i = start; i < nums.length; i++) {
            //如果 nums[i - 1]被放进来了， 下一个recursion的时候start就是i， 
            //所以判断一下i如果不等于start，nums[i-1]不在current里，如果再加nums[i],则重复之前加了nums[i-1]的结果，所以跳过
            if(i > start && nums[i] == nums[i - 1]) continue;
            current.add(nums[i]);
            helper(nums, i + 1, res, current);     
            current.remove(current.size() - 1);
        }
    }        
}

//2. DFS Time O(n * 2^n)(need O(n) to store ), Space: O(n)

public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result= new ArrayList<>();
        dfs(nums,0,new ArrayList<Integer>(),result);
        return result;
    }
    
    public void dfs(int[] nums,int index,List<Integer> path,List<List<Integer>> result){
        result.add(path);
        for(int i=index;i<nums.length;i++){
            if(i>index&&nums[i]==nums[i-1]) continue;
            List<Integer> nPath= new ArrayList<>(path);
            nPath.add(nums[i]);
            dfs(nums,i+1,nPath,result);
        }
    }
}

//3. recursion C++
class Solution {
public:
    vector<vector<int>> subsetsWithDup(vector<int> &S) {
        if (S.empty()) return {};
        vector<vector<int>> res(1);
        sort(S.begin(), S.end());
        int size = 1, last = S[0];
        for (int i = 0; i < S.size(); ++i) {
            if (last != S[i]) {
                last = S[i];
                size = res.size();
            }
            int newSize = res.size();
            for (int j = newSize - size; j < newSize; ++j) {
                res.push_back(res[j]);
                res.back().push_back(S[i]);
            }
        }
        return res;
    }
};

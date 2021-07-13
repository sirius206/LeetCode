//1. HashMap + helper function, faster 
// Time: O(n), Space O(n)
class Solution {
    Map<Integer, Employee> emap;
    public int getImportance(List<Employee> employees, int queryid) {
        emap = new HashMap();
        for (Employee e: employees) emap.put(e.id, e);
        return dfs(queryid);
    }
    public int dfs(int eid) {
        Employee employee = emap.get(eid);
        int ans = employee.importance;
        for (Integer subid: employee.subordinates)
            ans += dfs(subid);
        return ans;
    }
}

//2. recursion, look up id in list, slow
// Time: O(n), Space O(n)
class Solution {
    public int getImportance(List<Employee> employees, int id) {
        Employee current_employee = new Employee();
        for (Employee employee : employees){
            if (employee.id == id){
                current_employee = employee;
            }
        }
        List<Integer> subordinates = current_employee.subordinates;
        int res = 0;
        if (subordinates.size() == 0) return current_employee.importance;
        else{
            res += current_employee.importance;
            for (int sub_id : subordinates){
                res += getImportance(employees, sub_id);
            }
        } 
        return res;
    }
}



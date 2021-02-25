
//Time O(n^2), Space O(n^2)
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();
        if (numRows == 0) {
            return triangle;
        }
        triangle.add(new ArrayList<>());
        triangle.get(0).add(1);
        for (int i = 1; i < numRows; i++) {
            List<Integer> row_i = new ArrayList<>();
            List<Integer> pre_row = triangle.get(i-1);
            row_i.add(1);
            for (int j = 1; j < i; j++) {
                int num = pre_row.get(j-1) + pre_row.get(j);
                row_i.add(num);
            }
            row_i.add(1);
            triangle.add(row_i);
        }
        return triangle;
    }
}

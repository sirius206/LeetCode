//1. Time: O(n) Space: O(n)

class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        char[] c = s.toCharArray();
        StringBuffer[] res = new StringBuffer[numRows];
        for (int i = 0; i < numRows; i++){
            res[i] = new StringBuffer();
        }
        int len = s.length();
        int i = 0; 
        while (i < len){
            for (int j = 0; j < numRows && i < len; j++){
                res[j].append(c[i]);
                i++;
            }
            for (int j = numRows - 2; j >= 1 && i < len; j--){
                res[j].append(c[i]);
                i++;
            }
        }
        for (int k = 1; k < numRows; k++){
            res[0].append(res[k]);
        }
        return res[0].toString();
    }
}

//2. use direction
class Solution {
    public String convert(String s, int numRows) {

        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }
}

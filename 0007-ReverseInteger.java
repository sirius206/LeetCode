//

class Solution {
    public int reverse(int x) {
        int rInt = 0;
//        while (Math.abs(x) > 0) {
        while (x != 0) {            
            if (Math.abs (rInt) > Integer.MAX_VALUE / 10) {
                return 0;
            }
            rInt = x % 10 + rInt * 10;
            x = x / 10;
        }
    return rInt;
    }
}

//Method 2 wrong

class Solution {
    public int reverse(int x) {
        String s = Integer.toString(x);
        StringBuilder sb = new StringBuilder();
        int j = 0;
        int index = 0;
        int len = s.length();
//        if (x > Integer.Max_Value || x < Integer.Min_Value) {
//            return 0;
//        }
        if (s.toCharArray()[0] == '-') {
            index = 1;
        }
        for (int i = len - 1; i >= index; i--) {
            if (s.toCharArray()[i]== '0') continue;
            else {
                sb.append(s.toCharArray()[i]);
            }
        }
        if (index == 1) return Integer.parseInt(sb.toString()) * (-1);
        else return Integer.parseInt(sb.toString());
    }
}

//不需要考虑符号
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


//为什么不用 check 是否等于 214748364 呢，因为输入的x也是一个整型数，所以x的范围也应该在 -2147483648～2147483647 之间，
//那么x的第一位只能是1或者2，翻转之后 res 的最后一位只能是1或2，所以 res 只能是 2147483641 或 2147483642 都在 int 的范围内。
//但是它们对应的x为 1463847412 和 2463847412，后者超出了数值范围。所以当过程中 res 等于 214748364 时， 输入的x只能为 1463847412， 
//翻转后的结果为 2147483641，都在正确的范围内，所以不用 check。


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

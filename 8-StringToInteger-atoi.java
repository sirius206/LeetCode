// wrong
//package edu.gatech.seclass;

class Solution {
    public int myAtoi(String str) {
        if (str.equals("")) return 0;
        String [] strAr = str.trim().split(" +");
        char[] strChar = strAr[0].toCharArray();
        char first = strChar[0];
        int len = strChar.length;
        if (first != '+' && first != '-'
                && !(first >= '0' && first <= '9')) {
            return 0;
        }
        else {
            int x = 0;
            int max = (int) Math.pow(2, 31) - 1;
            int min = ((int) Math.pow(2, 31))*(-1) - 1;
            int a;
            if (first == '+' || first == '-'){
                a = 1;
            }
            else a = 0;
            for (int i = a; i < len; i++){
                if (!(strChar[i] >= '0' && strChar[i] <= '9')){
                    return x;
                }
                if (first == '-' && (x > (min * (-1) - Character.getNumericValue(strChar[i]))/10)) {
                    return min;
                }
                else if (x > (max - Character.getNumericValue(strChar[i]))/10){
                    return max;
                }
                x = 10 * x + Character.getNumericValue(strChar[i]);
            }
            if (first == '-') return x * (-1);
            else return x;
        }
    }
}

//Method 2 from book
//package edu.gatech.seclass;

class Solution {
    private static final int maxDiv10 = Integer.MAX_VALUE / 10;
    public int myAtoi(String str) {
        int i = 0, n = str.length();
        while (i < n && Character.isWhitespace(str.charAt(i))) i++;
        int sign = 1;
        if (i < n && str.charAt(i) == '+') {
            i++;
        } else if (i < n && str.charAt(i) == '-') {
            sign = -1;
            i++;
        }
        int num = 0;
        while (i < n && Character.isDigit(str.charAt(i))) {
            int digit = Character.getNumericValue(str.charAt(i));
            if (num > maxDiv10 || num == maxDiv10 && digit >= 8) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            num = num * 10 + digit;
            i++;
        }
        return sign * num;
    }
}


// Time:O(n) Space:O(n)
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        StringBuilder res = new StringBuilder();
        if (numerator < 0 ^ denominator < 0) res.append("-");
        long num = Math.abs((long) numerator);
        long div = Math.abs((long) denominator);
        res.append(num / div);
        long rem = num % div;
        if (rem == 0) return res.toString();
        res.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (rem != 0){
            if (map.containsKey(rem)){
                res.insert(map.get(rem), "(");
                res.append(")");
                break;
            }
            map.put(rem, res.length());
            rem *= 10;
            res.append(rem / div);
            rem = rem % div;
        }
        return res.toString();
    }
}

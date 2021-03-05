//第一，如果当前数字是最后一个数字，或者之后的数字比它小的话，则加上当前数字。
//第二，其他情况则减去这个数字。
//Time O(1), Space O(1)
class Solution {
    public int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i == s.length() - 1 || map.get(s.charAt(i + 1)) <= map.get(s.charAt(i))) {
                res += map.get(s.charAt(i));
            }
            
            else res -= map.get(s.charAt(i));
        }
        return res;
    }
}

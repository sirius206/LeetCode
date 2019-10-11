// Method 1 by myself

class Solution {
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9){
                ++digits[i];
                return digits;
            }
            else digits[i] = 0;                
        }
        int[] newDigits = new int[digits.length + 1];
        Arrays.fill(newDigits, 0); 
        newDigits[0] = 1;
        return newDigits;
    }
}


//Method 2 from book
//List

class Solution {
    public void plusOne(List<Integer> digits) {
        for (int i = digits.size() - 1; i >= 0; i--) {
            int digit = digits.get(i);
            if (digit < 9) {
                digits.set(i, digit + 1);
                return;
            } else {
                digits.set(i, 0);
            }
        }
        digits.add(0);
        digits.set(0, 1);
    }
}
//When all digits are 9, we did something slightly strange (See line 11). We append the
//digit 0 and modify the most significant digit to 1. Some of you might ask why not insert 1
//to the front of list? Assume that the list is implemented as an ArrayList, appending an
//element is far more efficient than inserting to the front, because all elements have to be
//shifted one place to the right otherwise.




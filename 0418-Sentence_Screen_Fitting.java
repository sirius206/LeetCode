
/*
String s = String.join(" ", sentence) + " " ;. This line gives us a formatted sentence to be put to our screen.
start is the counter for how many valid characters from s have been put to our screen.
if (s.charAt(start % l) == ' ') is the situation that we don't need an extra space for current row. The current row could be successfully fitted. 
So that we need to increase our counter by using start++.
The else is the situation, which the next word can't fit to current row. So that we need to remove extra characters from next word.
start / s.length() is (# of valid characters) / our formatted sentence.

Imagine an infinite sentence that are concatenated by words from the given sentence, infiStr. 
We want to cut the infiStr properly and put a piece at each row of the screen.
We maintain a pointer ptr. The ptr points to a position at infiStr, where next row will start. 
Cutting the infiStr and putting a piece at a row can be simulated as advancing the pointer by cols positions.
After advancing the pointer, if ptr points to a space, it means the piece can fit in row perfectly. 
If ptr points to the middle of a word, we must retreat the pointer to the beginning of the word, because a word cannot be split into two lines.
*/

public class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int start = 0, l = s.length();
        for (int i = 0; i < rows; i++) {
            start += cols;
            if (s.charAt(start % l) == ' ') {
                start++;
            } else {
                while (start > 0 && s.charAt((start-1) % l) != ' ') {
                    start--;
                }
            }
        }
        
        return start / s.length();
    }
}

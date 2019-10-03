//Method 1 by myself #not so good 
//Runtime: O(n), Space: O(n)
class Solution {
    public String reverseWords(String s) {
        String rString = "";
        int len = s.length();
        String word = "";
        boolean spaceFlag = false;
        boolean firstWord = true;
        for (int i = len-1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                spaceFlag = true;
                word = word + s.charAt(i);
                if (i != 0) {
                    continue;
                }
            }
            if (spaceFlag != false || i == 0) {
                if (!firstWord && !word.equals("")) {
                    rString = rString + " ";
                }
                else {
                    firstWord = false;
                }
                for (int j = word.length() - 1; j >= 0; j--) {
                    rString += word.charAt(j);
                }
                word = "";
                spaceFlag = false;
            }
        }
        return rString;
    }
}

//Method 1.5 by myself, use StringBuilder instead of String
class Solution {
    public String reverseWords(String s) {
        StringBuilder rString = new StringBuilder();
        int len = s.length();
        StringBuilder word = new StringBuilder();
        boolean newWordFlag = false;
        for (int i = len-1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                newWordFlag = true;
                word.append(s.charAt(i));
                if (i != 0) {
                    continue;
                }
            }
            if (newWordFlag != false || i == 0) {
                if (rString.length() != 0 && !word.toString().equals("")) {
                    rString.append(" ");
                }
                
                rString.append(word.reverse());

                word.setLength(0);
                newWordFlag = false;
            }
        }
        return rString.toString();
    }
}

//Method 2 from book
//O(n) runtime, O(n) space:
//We can do better in one-pass. While iterating the string in reverse order, we keep track of
//a word’s begin and end position. When we are at the beginning of a word, we append it.

class Solution {
    public String reverseWords(String s) {
        StringBuilder reversed = new StringBuilder();
        int j = s.length();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                j = i;
            } else if (i == 0 || s.charAt(i - 1) == ' ') {
                if (reversed.length() != 0) {
                    reversed.append(' ');
                }
                reversed.append(s.substring(i, j));
            }
        }
        return reversed.toString();
    }
}

//Method 3 from LC all in one, myself
//而如果我们使用Java的String的split函数来做的话就非常简单了，没有那么多的幺蛾子，简单明了，我们首先将原字符串调用trim()来去除冗余空格，然后调用
//split()来分隔，分隔符设为"\\s+"，这其实是一个正则表达式，\\s表示空格字符，+表示可以有一个或多个空格字符，那么我们就可以把单词分隔开装入一个字
//符串数组中，然后我们从末尾开始，一个个把单词取出来加入结果res中，并且单词之间加上空格字符，注意我们把第一个单词留着不取，然后返回的时候再加上即可
class Solution {
    public String reverseWords(String s) {
        StringBuilder rString = new StringBuilder();
        String[] words = s.trim().split("\\s+");
            for (int i = words.length - 1; i > 0; i--) {
                rString.append(words[i]).append(" ");
            }
        return rString.append(words[0]).toString();
        }
}

//Method 4 from LC all in one
//利用到了Java的内置函数，这也是Java的强大之处，注意这里的分隔符没有用正则表达式，而是直接放了个空格符进去，后面还是有+号，跟上面的写法得到的效果是
//一样的，然后我们对字符串数组进行翻转，然后调用join()函数来把字符串数组拼接成一个字符串，中间夹上空格符即可
public class Solution {
    public String reverseWords(String s) {
        String[] words = s.trim().split(" +");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }
}


// Time O(n) space O(n)
//in String "\n", "\t", "\123" will all be count the length as one


//1
public int lengthLongestPath(String input) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0); // "dummy" length
        int maxLen = 0;
        for(String s:input.split("\n")){
            int lev = s.lastIndexOf("\t")+1; // number of "\t"
            while(lev+1<stack.size()) stack.pop(); // find parent
            int len = stack.peek()+s.length()-lev+1; // remove "/t", add"/"
            stack.push(len);
            // check if it is file
            if(s.contains(".")) maxLen = Math.max(maxLen, len-1); 
        }
        return maxLen;
    }


//2
public class Solution {

    public int lengthLongestPath(String input) {
        String[] item = input.split("\n");
        int longestLength = 0;
        int currLength = 0;
        Stack<Integer> stack = new Stack<Integer>();
        
        for (String s : item) {
            // Find level of the current item, level is calculated by counting number of \t
            int level = 0;
            boolean isFile = false;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '\t') level++;
                else if (s.charAt(i) == '.') isFile = true;
            }
            
            // If the current item is a few levels back up, we need to pop out all the items until we get to the parent of the current item so we can push the current item into the stack
            while (stack.size() > level) {
                currLength -= stack.pop();
            }
            
            int itemSize = s.length() - level + 1; // plus one because directories and files start with '/'' e.g. "/subdir2"
            currLength += itemSize;
            
            // Only update the longestLength if a file is found.
            if (isFile) {
                // We subtract 1 from the currLength because the first directory doesn't have '/'' in the front. e.g. "dir/subdir2/subsubdir2/file2.ext"
                if (currLength - 1 > longestLength) {
                    longestLength = currLength - 1;
                }
            }
            
            stack.add(itemSize);
        }
        
        return longestLength;
    }
}

//3
public class Solution {
    public int lengthLongestPath(String input) {
        String[] tokens = input.split("\n");
        int result = 0;
        int curLen = 0;
        Stack<Integer> stack = new Stack<>();

        for (String s : tokens) {
            int level = countLevel(s);

            // if current directory/file depth is lower that the top directory/file on the stack, pop from stack 
            while (stack.size() > level) {
                curLen -= stack.pop();
            }

            // +1 here because a "/" needs to be counted following each diretory
            int len = s.replaceAll("\t", "").length() + 1;
            curLen += len;

            // if s contains ".", we have found a file!
            if (s.contains(".")) {
                result = curLen - 1 > result ? curLen - 1 : result;
            }
            stack.add(len);
        }
        return result;
    }
    
    private int countLevel(String s) {
        String cur = s.replaceAll("\t", "");
        return s.length() - cur.length();
    }
}



// Time O(m * n)?? Space O(max(m,n))?? 
//It depends on the string.Contains algorithm, OpenJDK uses a O(A*B) time complexity
/*
Let x be the theoretical lower bound, which is ceil(len(B)/len(A)).
The answer n can only be x or x + 1 (in the case where len(B) is a multiple of len(A) like in A = "abcd" and B = "cdabcdab") and not more. 
Because if B is already in A * n, B is definitely in A * (n + 1).
*/


// mine

class Solution {
    public int repeatedStringMatch(String a, String b) {
        List<Character> charsA = new ArrayList<>();
        for (char charA : a.toCharArray()){
            charsA.add(charA);
        }
        for (char charB : b.toCharArray()){
            if (!charsA.contains(charB)){
                return -1;
            }
        }
        // if don't do above diagnostics, time increases a lot
      
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (sb.length() - a.length() <= b.length()){   //add another a after sb's length > b's length for case "abc" "cabcabca"
            count++;
            sb.append(a);
            if (sb.indexOf(b) != -1){
                return count;
            }
        }
        return -1;
    }
}


//ans
class Solution {
     public int repeatedStringMatch(String A, String B) {

        int count = 0;
        StringBuilder sb = new StringBuilder();
        while (sb.length() < B.length()) {
            sb.append(A);
            count++;
        }
        if(sb.toString().contains(B)) return count;
        if(sb.append(A).toString().contains(B)) return ++count;
        return -1;
    }
}

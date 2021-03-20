
//1. Recursive, Backtracking Time O(n * 4^n), Space: O(n)
class Solution {
    private List<String> combinations = new ArrayList<>();
    private Map<Character, String> letters = Map.of(
        '2', "abc", '3', "def", '4', "ghi", '5', "jkl", 
        '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz");
    private String phoneDigits;
    
    public List<String> letterCombinations(String digits) {
        // If the input is empty, immediately return an empty answer array
        if (digits.length() == 0) {
            return combinations;
        }
        
        // Initiate backtracking with an empty path and starting index of 0
        phoneDigits = digits;
        backtrack(0, new StringBuilder());
        return combinations;
    }
    
    private void backtrack(int index, StringBuilder path) {
        // If the path is the same length as digits, we have a complete combination
        if (path.length() == phoneDigits.length()) {
            combinations.add(path.toString());
            return; // Backtrack
        }
        
        // Get the letters that the current digit maps to, and loop through them
        String possibleLetters = letters.get(phoneDigits.charAt(index));
        for (char letter: possibleLetters.toCharArray()) {
            // Add the letter to our current path
            path.append(letter);
            // Move on to the next digit
            backtrack(index + 1, path);
            // Backtrack by removing the letter before moving onto the next
            path.deleteCharAt(path.length() - 1);
        }
    }
}

//2. Iterative Time O(n * 4^n), Space: O(n)

class Solution {
    public List<String> letterCombinations(String digits) {
        String[][] lookup= new String [][] {{"a", "b", "c"}, 
                                             {"d","e", "f"}, 
                                             {"g", "h", "i"}, 
                                             {"j", "k", "l"}, 
                                             {"m", "n", "o"}, 
                                             {"p", "q", "r", "s"}, 
                                             {"t", "u", "v"}, 
                                             {"w", "x", "y", "z"}};
        List<String> res = new ArrayList<>();
        if (digits.equals("")) return res;
        res.add("");
        int len = digits.length();
        int[] input = new int[len];
        for (int i = 0; i < len; i++) {
            input[i] = Character.getNumericValue(digits.charAt(i)) - 2; 
        }
        String current = "";
        for (int i : input){
            res = helper(i, current, new ArrayList<String>(res), lookup);
        }
        return res;
    }
    private List<String> helper(int i, String current, ArrayList<String> res, String[][] lookup){
        List<String> newres = new ArrayList<>();
        for (String curr : res) {
            for (int j = 0; j < lookup[i].length; j++) {
                newres.add(curr+lookup[i][j]);
            }
        }
        return newres;
    }
    
}

//1. add string Time O(n) Space O(1), not counting the result, if count the result, O(1)
class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<String>();
        for (int i = 1; i <= n; i++) {
            String resString = "";
            if (i % 3 == 0){
                resString += "Fizz";
            }
            if (i % 5 == 0){
                resString += "Buzz";
            }
            if (resString.equals("")) resString = String.valueOf(i);
            res.add(resString);
        }
        return res;
    }
}


//2. HashMap, if conditions too complicated
class Solution {
  public List<String> fizzBuzz(int n) {

    // ans list
    List<String> ans = new ArrayList<String>();

    // Hash map to store all fizzbuzz mappings.
    HashMap<Integer, String> fizzBizzDict =
        new HashMap<Integer, String>() {
          {
            put(3, "Fizz");
            put(5, "Buzz");
          }
        };

    for (int num = 1; num <= n; num++) {

      String numAnsStr = "";

      for (Integer key : fizzBizzDict.keySet()) {

        // If the num is divisible by key,
        // then add the corresponding string mapping to current numAnsStr
        if (num % key == 0) {
          numAnsStr += fizzBizzDict.get(key);
        }
      }

      if (numAnsStr.equals("")) {
        // Not divisible by 3 or 5, add the number
        numAnsStr += Integer.toString(num);
      }

      // Append the current answer str to the ans list
      ans.add(numAnsStr);
    }

    return ans;
  }
}

//3. naiive don't use
class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<String>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0){
                res.add("FizzBuzz");
            }
            else if (i % 3 == 0){
                res.add("Fizz");
            }
            else if (i % 5 == 0){
                res.add("Buzz");
            }
            else res.add(String.valueOf(i));
        }
        return res;
    }
}

//

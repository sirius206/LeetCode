//1. Stack, Time O(n), Space O(n)
// stack pushes and pops each asteroid at most once.
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int current : asteroids){
            //[5,10], [-2]
            if (current >= 0 || stack.isEmpty()) {
                stack.push(current);
            }
            // current < 0
            else{
                while (!stack.isEmpty()){
                    //prev < 0, just add current, eg: [-2, -1]
                    if (stack.peek() <= 0) {
                        stack.push(current);
                        break;
                    }
                    //prev > 0
                    else{
                        // prev > -current, ignore current
                        if (stack.peek() + current > 0){
                            break;
                        }
                        // prev == -current, anihliate
                        else if (stack.peek() + current == 0){
                            stack.pop();
                            break;
                        }
                        else{
                            // prev < current, pop and continue while loop
                            stack.pop();
                            // if stack becomes empty, add current, eg:[1, 2, -5] -> [-5]
                            if (stack.isEmpty()) {
                                stack.push(current);
                                break;
                            }
                        }
                    }                    
                }
            }
        }
        //backwards because stack is LIFO, use len instead of stack.size because stack.size changes
        int len = stack.size();
        int[] res = new int[len];
        for (int i = 0; i < len; i++){
            res[len - 1 - i] = stack.pop();
        }
        return res;
    }
}

//2. use flag and stack
  public int[] asteroidCollision(int[] asteroids) {
    if (null == asteroids || 0 == asteroids.length)
      return new int[] {};
    
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < asteroids.length; ++i) {
      int curr = asteroids[i];
      boolean push = true;
      while (!stack.isEmpty() && stack.peek() > 0 && curr < 0) {
        int peekAbs = Math.abs(stack.peek());
        int currAbs = Math.abs(curr);
        
        if (peekAbs >= currAbs) {
          push = false;
          if (peekAbs == currAbs)
            stack.pop();
          
          break;
        } else
          stack.pop();
      }
      
      if (push)
        stack.push(curr);
    }
    
    int[] result = new int[stack.size()];
    for (int i = result.length - 1; i >= 0; --i)
      result[i] = stack.pop();
    
    return result;
  }

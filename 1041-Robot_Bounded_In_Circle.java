//After 1 cycle, if facing north or didn't return to (0,0), not a circle
// Time O(n) Space O(1)
// or could check after 4 cycles, if return to (0,0), is a circle

class Solution {
    public boolean isRobotBounded(String instructions) {
        int len = instructions.length();
        int p = 0;
        int dir = 90;
        int x = 0;
        int y = 0;
        while (p < len){
            if (instructions.charAt(p) == 'G'){
                if (dir == 90) {
                    y++;
                }
                else if (dir == 180) {
                    x--;
                }
                else if (dir == 270) {
                    y--;
                }                
                else if (dir == 0) {
                    x++;
                }                
            }
            else if (instructions.charAt(p) == 'L'){
                dir += 90;
            }
            else if (instructions.charAt(p) == 'R'){
                dir += 270;
            }
            dir = dir % 360;
            p++;
        }
        if (x == 0 && y == 0 || dir != 90) return true;
        else return false;
        
    }
}

//2. answer
class Solution {
    public boolean isRobotBounded(String instructions) {
        // north = 0, east = 1, south = 2, west = 3
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        // Initial position is in the center
        int x = 0, y = 0;
        // facing north
        int idx = 0;
        
        for (char i : instructions.toCharArray()) {
            if (i == 'L')
                idx = (idx + 3) % 4;
            else if (i == 'R')
                idx = (idx + 1) % 4;
            else {
                x += directions[idx][0];
                y += directions[idx][1];   
            }    
        }
        
        // after one cycle:
        // robot returns into initial position
        // or robot doesn't face north
        return (x == 0 && y == 0) || (idx != 0);
    }
}

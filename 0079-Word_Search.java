
//backtracking Time Complexity: O(N⋅3^L) where N is the number of cells in the board and L is the length of the word to be matched.
// can go 4 directions but won't go back so base is 3.
// Space O(L) L recuresion stack

class Solution {
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                if (board[i][j] == word.charAt(0)){
                    if (dfs(board, word, i, j, 0)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean dfs(char[][] board, String word, int row, int col, int index){
        if (index == word.length() - 1 && board[row][col] == word.charAt(index)) {
            return true;
        }
        int numRows = board.length;
        int numCols = board[0].length;
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        if (board[row][col] != word.charAt(index)) {
            return false;}

        board[row][col] = '#';
        //boolean ret = false;
        for (int i = 0; i < 4; i++){
            int newRow = row + directions[i][0];
            int newCol = col + directions[i][1];
            if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols){
                if (dfs(board, word, newRow, newCol, index + 1)){
                // clean up before return
                   //ret = true;
                   //break;
                //return without clean up
                    return true;
                }
            }
        }
        board[row][col] = word.charAt(index);    
        //return ret;
        return false;
    }
}


//2. use a visited array
public class Solution {
    static boolean[][] visited;
    public boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if((word.charAt(0) == board[i][j]) && search(board, word, i, j, 0)){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean search(char[][]board, String word, int i, int j, int index){
        if(index == word.length()){
            return true;
        }
        
        if(i >= board.length || i < 0 || j >= board[i].length || j < 0 || board[i][j] != word.charAt(index) || visited[i][j]){
            return false;
        }
        
        visited[i][j] = true;
        if(search(board, word, i-1, j, index+1) || 
           search(board, word, i+1, j, index+1) ||
           search(board, word, i, j-1, index+1) || 
           search(board, word, i, j+1, index+1)){
            return true;
        }
        
        visited[i][j] = false;
        return false;
    }
}

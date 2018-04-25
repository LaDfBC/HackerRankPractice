//LeetCode #79
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        boolean[][] seenBoard = new boolean[board.length][board[0].length];
        if(word.length() == 0) { return true; }
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == word.charAt(0)) {
                    if(checkSearchAt(board, i, j, word.substring(1), seenBoard)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkSearchAt(char[][] board, int i, int j, String word, boolean[][] seen) {
        if (word.length() == 0) { return true; }
        char next = word.charAt(0);
        seen[i][j] = true;

        //Left
        if(i > 0 && board[i - 1][j] == next) {
            if(!seen[i - 1][j]) {
                if(checkSearchAt(board, i - 1, j, word.substring(1), seen)) {
                    return true;
                }
            }
        }

        //Right
        if(i < (board.length - 1) && board[i + 1][j] == next) {
            if(!seen[i + 1][j]) {
                if(checkSearchAt(board, i + 1, j, word.substring(1), seen)) {
                    return true;
                }
            }
        }

        //Down
        if(j > 0 && board[i][j - 1] == next) {
            if(!seen[i][j - 1]) {
                if(checkSearchAt(board, i, j - 1, word.substring(1), seen)) {
                    return true;
                }
            }
        }

        //Up
        if(j < (board[0].length - 1) && board[i][j + 1] == next) {
            if(!seen[i][j + 1]) {
                if(checkSearchAt(board, i, j + 1, word.substring(1), seen)) {
                    return true;
                }
            }
        }

        seen[i][j] = false;
        return false;
    }
    
}

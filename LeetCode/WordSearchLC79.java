package LeetCode;
public class WordSearchLC79 {
    public static void main(String[] args) {
        System.out.println(new WordSearchLC79().exist(new char[][] {{'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}} , "ABCCED"));
    }
    public boolean exist(char[][] board, String word) {
        for(int i =0 ;i < board.length; i++) {
            for(int j = 0;j < board[0].length; j++) {
                if(backTrack(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backTrack(char[][] board, int i, int j, String word, int  wordIdx) {
        if(wordIdx >= word.length()) {
            return true;
        }
        if(i == board.length || j == board[0].length || i < 0 || j < 0 || word.charAt(wordIdx) != board[i][j]) {
            return false;
        }

        char existingChar = board[i][j];
        board[i][j] = '#';
        if(backTrack(board, i+1, j, word, wordIdx+1))
            return true;
        if(backTrack(board, i-1, j, word, wordIdx+1))
            return true;
        if(backTrack(board, i, j+1, word, wordIdx+1))
            return true;
        if(backTrack(board, i, j-1, word, wordIdx+1))
            return true;
        board[i][j] = existingChar;
        return false;
    }
}

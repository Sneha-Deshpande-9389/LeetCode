package LeetCode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class CrossWord {
    public static void main(String[] args) {
        char[][] board = new char[][]{ {'#',' ',' '},
                {'#',' ','#'},
                {'#',' ','#'},
                {'#',' ','c'}
        };
        System.out.println(new CrossWord().placeWordInCrossword(board, "ca"));
    }


    public boolean placeWordInCrossword(char[][] board, String word) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] != '#') {
                    if(topToDown(board, word, i, j)) return true;
                    if(downToUp(board, word, i, j)) return true;
                    if(leftToRight(board, word, i, j)) return true;
                    if(rightToLeft(board, word, i, j)) return true;
                }
            }
        }
        return false;
    }

    private boolean leftToRight(char[][] board, String word, int i, int j) {
        int m = board.length, n = board[0].length;
        if(j-1 >= 0 && board[i][j-1] != '#') {
            return false;
        }
        if(j+word.length() <= n-1 && board[i][j+word.length()] !='#') {
            return false;
        }

        for(int wordIndex = 0; wordIndex < word.length(); wordIndex++) {
            if(j+wordIndex > n-1) {
                return false;
            }
            if(board[i][j+wordIndex] == ' ' || board[i][j+wordIndex] == word.charAt(wordIndex)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }


    private boolean topToDown(char[][] board, String word, int i, int j) {
        int m = board.length, n = board[0].length;
        if(i-1 >= 0 && board[i-1][j] != '#') {
            return false;
        }
        if(i+word.length() <= m-1 && board[i+word.length()][j] !='#') {
            return false;
        }

        for(int wordIndex = 0; wordIndex < word.length(); wordIndex++) {
            if(i+wordIndex > m-1) {
                return false;
            }
            if(board[i+wordIndex][j] == ' ' || board[i+wordIndex][j] == word.charAt(wordIndex)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean rightToLeft(char[][] board, String word, int i, int j) {
        int m = board.length, n = board[0].length;
        if(j+1 <= n-1 && board[i][j+1] != '#') {
            return false;
        }
        if(j-word.length() >= 0 && board[i][j-word.length()] !='#') {
            return false;
        }

        for(int wordIndex = 0; wordIndex < word.length(); wordIndex++) {
            if(j-wordIndex < 0) {
                return false;
            }
            if(board[i][j-wordIndex] == ' ' || board[i][j-wordIndex] == word.charAt(wordIndex)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean downToUp(char[][] board, String word, int i, int j) {
        int m = board.length, n = board[0].length;
        if(i+1 <= m-1 && board[i+1][j] != '#') {
            return false;
        }
        if(i-word.length() >= 0 && board[i-word.length()][j] !='#') {
            return false;
        }

        for(int wordIndex = 0; wordIndex < word.length(); wordIndex++) {
            if(i-wordIndex < 0) {
                return false;
            }
            if(board[i-wordIndex][j] == ' ' || board[i-wordIndex][j] == word.charAt(wordIndex)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
}

package LeetCode;

import java.util.Arrays;

public class SurroundinvRegionsLC130 {
    public void solve(char[][] board) {
        if (board.length == 0 || board[0].length == 0)
            return;
        if (board.length < 2 || board[0].length < 2)
            return;
        int m = board.length, n = board[0].length;

        for(int i = 0; i < m; i++) {
            if(board[i][0] == '0') {
                dfs(board, i, 0, m, n);
            }
            if(board[i][n-1] == '0') {
                dfs(board, i, n-1, m, n);
            }
        }

        for(int j = 0; j < n; j++) {
            if(board[0][j] == '0') {
                dfs(board, 0, j, m, n);
            }
            if(board[m-1][j] == '0') {
                dfs(board, m-1, j, m, n);
            }
        }

        //for(char arr[] : board) {
        //    System.out.println(Arrays.toString(arr));
        //}
        for(int i =0; i < m; i++) {

            for(int j = 0; j < n; j++) {

                if(board[i][j] == '0') {
                    board[i][j] = 'X';
                }
                else if(board[i][j] == '*') {
                    board[i][j] = '0';
                }
            }
        }

    }

    void dfs(char[][] board, int i, int j, int m, int n) {
        if(i< 0|| j <0 || i > m-1 || j > n-1 || board[i][j] != '0') {
            return;
        }
        if(board[i][j] == '0')
            board[i][j] = '*';

        //if(i>0)
            dfs(board, i-1, j, m, n);
        //if(i<m-2)
            dfs(board, i+1, j, m, n);
        //if(j<n-2)
            dfs(board, i, j+1, m, n);
        //if(j>0)
            dfs(board, i, j-1, m, n);
    }

    public static void main(String[] args) {
        char[][] board = {
                {'X','X','X','X'},
                {'X','0','0','X'},
                {'X','X','0','X'},
                {'X','0','X','X'}
        };
        new SurroundinvRegionsLC130().solve(board);
        Arrays.stream(board).forEach(System.out::println);
    }
}

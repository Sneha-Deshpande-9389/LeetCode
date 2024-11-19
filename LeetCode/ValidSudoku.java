package LeetCode;

import java.util.HashSet;

public class ValidSudoku {
    public static void main(String[] args) {
        System.out.println(validSudoku(new char[][] {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}
        }));
    }

    public static boolean validSudoku(char[][] board) {
        int N = 9;
        HashSet<Character>[] rows = new HashSet[N];
        HashSet<Character>[] cols = new HashSet[N];
        HashSet<Character>[] boxes = new HashSet[N];
        for(int i = 0; i < N; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for(int r = 0; r < N ; r++) {
            for(int c = 0; c < N; c++) {
                char val = board[r][c];
                if(val == '.') {
                    continue;
                }

                if(rows[r].contains(val)) {
                    return false;
                }
                rows[r].add(val);
                if(cols[c].contains(val)) {
                    return false;
                }
                cols[c].add(val);

                int boxIndex = (r/3) * 3 + c / 3;
                if(boxes[boxIndex].contains(val)) {
                    return false;
                }
                boxes[boxIndex].add(val);
            }
        }
        return true;
    }
}

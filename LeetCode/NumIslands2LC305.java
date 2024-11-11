package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumIslands2LC305 {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {

        int[][] grid = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n ; j++) {
                grid[i][j] = 0;
            }
        }
        List<Integer> numIslands = new ArrayList<>();

        for(int[] position : positions) {
            int row = position[0], col = position[1];
            grid[row][col] = 1;
            numIslands.add(findIslands(grid, row, col, m, n));
        }
        return numIslands;
    }

    private Integer findIslands(int[][] grid, int row, int col, int m, int n) {
        int res = 0;
        int[][] visited = Arrays.stream(grid).map(int[]::clone).toArray(int[][]::new);

        System.out.println("findIslands: ");
        Arrays.stream(visited).forEach(ints -> System.out.println(Arrays.toString(ints)));
        for(int i =0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(visited[i][j] == 1) {
                    res++;
                    dfs(i, j, m, n, visited);
                }
            }
        }
        return res;
    }

    void dfs(int row, int col, int m, int n, int[][] visited) {
        if(row < 0 || col < 0 || row > m-1 || col > n-1 || visited[row][col] == 0) {
            return;
        }
        visited[row][col] = 0;
        dfs(row-1, col, m, n, visited);
        dfs( row+1, col, m, n, visited);
        dfs( row, col+1, m, n, visited);
        dfs(row, col-1, m, n, visited);

    }

    public static void main(String[] args) {
        int m = 3, n = 3;
        int[][] positions = {{0,0},{0,1},{1,2},{2,1}};
        System.out.println(new NumIslands2LC305().numIslands2(m, n, positions));
    }
}


package LeetCode;

import java.util.Arrays;

public class MaxPointsWithCost {
    public static void main(String[] args) {
        //System.out.println(new MaxPointsWithCost().maxPoints(new int[][]{{1,2,3},{1,5,1},{3,1,1}}));
        System.out.println(new MaxPointsWithCost().maxPoints(new int[][]{{0,3,0,4,2},{5,4,2,4,1},{5,0,0,5,1},{2,0,1,0,3}}));
    }
    public long maxPoints(int[][] p) {
        int m = p.length, n = p[0].length;

        long[] prevRow = new long[n];
        for (int col = 0; col < n; ++col) {
            prevRow[col] = p[0][col];
        }
        for(int rowIndex = 1; rowIndex < m; rowIndex++) {
            long[] currRow = new long[n];
            long[] leftToRight = new long[n];
            long[] rightToLeft = new long[n];
            leftToRight[0] = prevRow[0];
            for(int prevRowColIndex = 1; prevRowColIndex < n; prevRowColIndex++) {
                leftToRight[prevRowColIndex] = Math.max(leftToRight[prevRowColIndex-1] -1, prevRow[prevRowColIndex]);
            }
            rightToLeft[n-1] = prevRow[n-1];
            for(int prevRowColIndex = n-2; prevRowColIndex >= 0; prevRowColIndex--) {
                rightToLeft[prevRowColIndex] = Math.max(rightToLeft[prevRowColIndex+1] -1, prevRow[prevRowColIndex]);
            }

            for(int col = 0; col < n; col++) {
                currRow[col] = Math.max(leftToRight[col], rightToLeft[col]) +
                        p[rowIndex][col];
            }

            prevRow = currRow;
        }

        long maxValue = Long.MIN_VALUE;
        for(int j = 0; j < n; j++) {
            maxValue = Math.max(maxValue, prevRow[j]);
        }
        return maxValue;
    }
}

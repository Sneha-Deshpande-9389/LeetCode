package LeetCode;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class FindLargestNumberInSubSeqOfSizeK {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FindLargestNumberInSubSeqOfSizeK().findLargestNumberInSubSeq(
                new int[]{4, 9, 0, 2}, 2
        )));

        System.out.println(Arrays.toString(new FindLargestNumberInSubSeqOfSizeK().findLargestNumberInSubSeq(
                new int[]{4, 9, 0, 2}, 3
        )));

        System.out.println(Arrays.toString(new FindLargestNumberInSubSeqOfSizeK().findLargestNumberInSubSeq(
                new int[]{1, 4, 2, 8, 5, 7}, 1
        )));

        System.out.println(Arrays.toString(new FindLargestNumberInSubSeqOfSizeK().findLargestNumberInSubSeq(
                new int[]{1, 4, 2, 8, 5, 7}, 2
        )));

        System.out.println(Arrays.toString(new FindLargestNumberInSubSeqOfSizeK().findLargestNumberInSubSeq(
                new int[]{1, 4, 2, 8, 5, 7}, 4
        )));
    }
    public int[] findLargestNumberInSubSeq(int[] input, int k) {
        int[] res = new int[k];
        int[][] indexToVal = new int[input.length][2];
        for(int i = 0; i < input.length; i++) {
            indexToVal[i][0] = i;
            indexToVal[i][1] = input[i];
        }

        Arrays.sort(indexToVal, Comparator.comparingInt(a -> -a[1]));

        int[][] maxK = Arrays.copyOf(indexToVal, k);

        Arrays.sort(maxK, Comparator.comparingInt(a -> a[0]));

        for(int i =0; i < k; i++) {
            res[i] = maxK[i][1];
        }

        return res;
    }
}

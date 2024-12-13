package slidingwindow;

import java.util.Arrays;

public class ASubArrayAvg {

    public static void main(String[] args) {
        int[] input = {1, 2, 4, 7, -3, 9, 6, 8};
        // Assumptions: Ints, -ve numbers, can fit into memory
        int k = 3; // window Size

        double[] output = findSubArrAvgOptimized(input, k);
        System.out.println(Arrays.toString(output));
    }

    private static double[] findSubArrAvgOptimized(int[] arr, int k){
        if (arr == null || arr.length < k || k <= 0) {
            return null;
        }
        double[] res = new double[arr.length-k+1];
        double windowSum = 0;
        int windowStart = 0;

        for(int windowEnd = 0;windowEnd<arr.length;windowEnd++){
            windowSum += arr[windowEnd];

            if(windowEnd >= k-1){
                res[windowStart] = windowSum/k;
                windowSum -= arr[windowStart];
                windowStart++;
            }
        }
        return res;
    }
}

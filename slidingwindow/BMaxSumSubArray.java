package slidingwindow;

public class BMaxSumSubArray {

    public static void main(String[] args) {
        int[] input = {8, 2, 3, 5, 6, 1, 7, 8, -2, 4, 1, 6};
        int k = 3; // window length

        System.out.println(maxSumSubArray(input, k));
    }

    public static int maxSumSubArray(int[] input, int k) {
        if(input == null || k <=0 || input.length < k) {
            return 0;
        }

        int windowSum = 0;
        int startIndex = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int currentIndex = 0; currentIndex < input.length ; currentIndex++) {
            windowSum += input[currentIndex];

            if (currentIndex >= k-1) {
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= input[startIndex];
                startIndex++;
            }
        }
        return maxSum;
    }
}

package ArraysnString;

public class MedianSortedArray {

    public static void main(String[] args) {
        int[] first = {1, 5, 8, 10, 18, 20};
        int[] second = {2, 3, 6, 7};
        int ar1[] = { 900 };
        int ar2[] = { 5, 8, 10, 20 };

        System.out.println("******** Approach 1 ************************");
        System.out.println(getMedianOfSortedArray(mergeSortedArrays(first, second)));
        // Space O(m) + O(n)
        // Time -> O(m+n) + constant
        System.out.println("******** Approach 2 ************************");

        if (first==null && second==null) {
            System.out.println(0); // Can throw exception as well Bad req or logging
            System.exit(0);
        }
        else if(first==null) {
            if (second.length %2 ==0) {
                System.out.println(getMedian(second[second.length/2], second[(second.length-1)/2]));
            }else {
                System.out.println((float)second[second.length/2]);
            }
        }
        else if (second == null) {
            if (first.length %2 ==0) {
                System.out.println(getMedian(first[first.length/2], first[(first.length-1)/2]));
            }else {
                System.out.println((float)first[first.length/2]);
            }
        }
        else {
            System.out.println(getMedianSortedArraysImproved(first, second));
        }
    }

    private static int[] mergeSortedArrays(int[] first, int[] second) {

        if (first==null && second==null) {
            return null; // Can throw exception as well Bad req or logging
        }
        if(first==null) {
            return second;
        } else if (second == null) {
            return first;
        }
        int[] result = new int[first.length + second.length];
        int i=0, j=0, k=0;
        while(i<first.length & j< second.length){
            result[k++] = first[i] < second[j] ? first[i++] : second[j++];
        }
        while (i<first.length){
            result[k++] = first[i++];
        }
        while (j<second.length){
            result[k++] = second[j++];
        }
        return result;
    }
    private static float getMedianOfSortedArray(int[] input) {
        if(input == null) {
            return 0;
        }
        if(input.length %2 == 0) {
            return (getMedian(input[input.length/2] , input[(input.length-1)/ 2]));
        } else {
            return input[(input.length)/ 2];
        }
    }

    private static float getMedian(int a, int b) {
        return (float) ((a + b) / 2.0);
    }

    // Method to find median
    static double getMedianSortedArraysImproved(int[] A, int[] B)
    {
        int n = A.length;
        int m = B.length;
        if (n > m)
            return getMedianSortedArraysImproved(B, A); // Swapping to make A smaller

        int start = 0;
        int end = n;
        int realmidinmergedarray = (n + m + 1) / 2;

        while (start <= end) {
            int mid = (start + end) / 2;
            int leftAsize = mid;
            int leftBsize = realmidinmergedarray - mid;
            int leftA
                    = (leftAsize > 0)
                      ? A[leftAsize - 1]
                      : Integer.MIN_VALUE; // checking overflow of indices
            int leftB
                    = (leftBsize > 0) ? B[leftBsize - 1] : Integer.MIN_VALUE;
            int rightA
                    = (leftAsize < n) ? A[leftAsize] : Integer.MAX_VALUE;
            int rightB
                    = (leftBsize < m) ? B[leftBsize] : Integer.MAX_VALUE;

            // if correct partition is done
            if (leftA <= rightB && leftB <= rightA) {
                if ((m + n) % 2 == 0)
                    return (Math.max(leftA, leftB)
                            + Math.min(rightA, rightB))
                           / 2.0;
                return Math.max(leftA, leftB);
            }
            else if (leftA > rightB) {
                end = mid - 1;
            }
            else
                start = mid + 1;
        }
        return 0.0;
    }
}

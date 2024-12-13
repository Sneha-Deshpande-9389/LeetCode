package Docusign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ArrayPartitioner {

    public static List<int[]> partitionArray(int[] arr, int partitionSize) {

        // Validate the partition size
        if (partitionSize <= 0) {
            throw new IllegalArgumentException("Partition size must be greater than 0");
        }

        List<int[]> partitions = new ArrayList<>();

        // Loop through the array and create partitions
        for (int i = 0; i < arr.length; i += partitionSize) {
            int end = Math.min(i + partitionSize, arr.length);
            int[] partition = new int[end - i];
            System.arraycopy(arr, i, partition, 0, end - i);
            partitions.add(partition);
        }

        return partitions;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int size = 3;
        List<int[]> partitions = partitionArray(array, size);

        // Print the partitions
        for (int[] partition : partitions) {
            for (int num : partition) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}

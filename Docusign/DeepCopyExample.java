package Docusign;

import java.util.Arrays;

public class DeepCopyExample {
    public static void main(String[] args) {
        String[] originalArray = {"A", "B", "C"};

        // Create a deep copy using a loop (useful for non-primitive types)
        String[] backupArray = new String[originalArray.length];
        for (int i = 0; i < originalArray.length; i++) {
            backupArray[i] = new String(originalArray[i]); // Creates a new instance
        }

        // Print both arrays to verify
        System.out.println("Original Array of strings: " + Arrays.toString(originalArray));
        System.out.println("Backup Array of strings: " + Arrays.toString(backupArray));



        int[] originalArrayInt = {1, 2, 3, 4, 5};

        // Create a backup using the clone method
        int[] backupArrayInt = originalArrayInt.clone();

        // Print both arrays to verify
        System.out.println("Original Array: " + Arrays.toString(originalArrayInt));
        System.out.println("Backup Array: " + Arrays.toString(backupArrayInt));
    }
}

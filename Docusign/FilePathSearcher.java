package Docusign;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilePathSearcher {
    
    public static List<String> findPathsContainingString(File directory, String searchString) {
        List<String> matchingPaths = new ArrayList<>();

        if (directory != null && directory.exists()) {
            // If the current directory path contains the search string, add it to the list
            if (directory.isFile() && directory.getName().contains(searchString)) {
                matchingPaths.add(directory.getAbsolutePath());
            } else if (directory.isDirectory()) {
                // Recursively search through all subdirectories
                File[] files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        matchingPaths.addAll(findPathsContainingString(file, searchString));
                    }
                }
            }
        }
        return matchingPaths;
    }

    public static void main(String[] args) {
        // Replace with the directory path you want to search and the predefined string
        File rootDirectory = new File("/Users/snehadeshpande/Downloads/Lets-do-this-Krishna/Prep/MarketReady");
        String searchString = "hi";  // Replace with your predefined string

        List<String> paths = findPathsContainingString(rootDirectory, searchString);

        System.out.println(paths.toString());
    }
}

package ArraysnString;

import java.util.HashMap;

public interface StringPermutationOfOther {

    public static void main(String[] args) {
        String input1 = "qscestrfbthyi";
        String input2 = "sqsrcefbthyitg";
        System.out.println(isPermutationOfOther(input1, input2));
    }
    //Approach -1 : Sort & equals

    //Approach-2 character count match
    private static boolean isPermutationOfOther(String first, String second) {
        if(first == null && second == null) {
            return true;
        }
        if((first != null && second != null) && first.length() == second.length()) {
            int[] letters = new int[256]; // Assumption Ascii characters
            for(char c: first.toCharArray()){
                letters[c]++;
            }
            for(int i=0; i<second.length(); i++) {
                int c = second.charAt(i);
                if(--letters[c] <0) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
}

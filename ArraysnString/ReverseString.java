package ArraysnString;

public class ReverseString {

    public static void main(String[] args) {
        String input = "wsxedcrfv";
        System.out.println(reverseCharArray(input.toCharArray()));

    }
    private static char[] reverseCharArray(char[] inputArray) {
        if (inputArray == null || inputArray.length == 0) {
            return null;
        }
        char val = ' ';
        int i=0, j=inputArray.length-1;
        while(i<j) {
            val = inputArray[i];
            inputArray[i++] = inputArray[j];
            inputArray[j--] = val;
        }
        return inputArray;
    }
}

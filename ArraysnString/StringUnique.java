package ArraysnString;

public class StringUnique {

    public static void main(String[] args) {
        String input = "qwer23ty45ui67opas90dfghnmjklx;";

        System.out.println("Is all characters unique: "+ isAllUniqueCharacters(input));
    }
    //  Ascii-256

    private static boolean isAllUniqueCharacters(String input){
        if(input == null || input.isEmpty())
            return true;
        if(input.length() > 256)
            return false;
        boolean[] charIndex =  new boolean[256];
        for (int i=0; i<input.length();i++) {
            int val = input.charAt(i);
            if(charIndex[val]) {
                return false;
            }
            charIndex[val] = true;
        }
        return true;
    }
}

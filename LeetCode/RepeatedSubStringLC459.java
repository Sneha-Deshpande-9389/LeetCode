package LeetCode;

/*
abab
a

strLen = 4
patternLen = 1

subStr = 0, 1 = a
 */

public class RepeatedSubStringLC459 {
    public static void main(String[] args) {
        System.out.println(isRepeatedSubString("abab"));
        System.out.println(isRepeatedSubString("aba"));
        System.out.println(isRepeatedSubString("abcabcabcabc"));

    }
    public static boolean isRepeatedSubString(String str) {
        int strLen = str.length();

        for(int patternLen = 1; patternLen < strLen; patternLen++) {
            if(strLen % patternLen == 0) {
                int patternCount = strLen / patternLen;
                String pattern = str.substring(0, patternLen);

                StringBuilder sb = new StringBuilder();

                for(int i = 0; i < patternCount; i++) {
                    sb.append(pattern);
                }
                if(sb.toString().equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }
}

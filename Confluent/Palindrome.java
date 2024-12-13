package Confluent;

/*
Check if a string is a palindrome recursively.
https://leetcode.com/discuss/interview-question/5987161/Confluent-Interview-or-Senior-software-engineer-or-oct-2024
 */
public class Palindrome {

        public static void main(String[] args) {

            String str = "abcdcba";
            int i =0, j = str.length()-1;
            boolean isPalindrome = true;
            while(i < j) {
                if(str.charAt(i) == str.charAt(j)) {
                    i++;
                    j--;
                } else {
                    isPalindrome=false;
                    break;
                }
            }
            System.out.println(isPalindrome);
        }

}

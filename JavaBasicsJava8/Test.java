package JavaBasicsJava8;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        String s1 = "Hello";
        String s2 = s1 + "World";
        String s3 = "HelloWorld";
        String s4 = "Hello" + "World";

        //System.out.println(s2 == s3);
        //System.out.println(s3 == s4);
        List<String> words = Arrays.asList("cat", "cats", "and", "sand", "dog");
        HashSet<String> set = new HashSet<>(words);
        String s = "catsanddog";
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for(int i = 1; i <= n;i++) {
            for(int j = 0; j < i; j++) {
                System.out.println(s.substring(j, i));
                if(dp[j] == true && set.contains(s.substring(j, i))) {

                    dp[i] = true;
                    System.out.println("Valid " + s.substring(j, i) + " " + Arrays.toString(dp));
                    break;
                }
                System.out.println("===");
            }
        }
        System.out.println(dp[n]);
    }
}

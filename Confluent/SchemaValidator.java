package Confluent;

import java.util.Deque;
import java.util.LinkedList;

public class SchemaValidator {
    public static boolean isValidJason(String str) {
        Deque<Character> stack = new LinkedList<>();
        for(Character c : str.toCharArray()) {
            if(c.equals('{') || c.equals('[')) {
                stack.push(c);
            } else if (c.equals('\'')){

            }
        }
        return false;
    }
}

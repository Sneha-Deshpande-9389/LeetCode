package Docusign;

public class JsonValidator {

    public static boolean isValidJson(String jsonString) {
        // Trim the input string to avoid issues with extra whitespace
        jsonString = jsonString.trim();
        
        // A valid JSON object must start with '{' and end with '}'
        // A valid JSON array must start with '[' and end with ']'
        if ((jsonString.startsWith("{") && jsonString.endsWith("}")) ||
            (jsonString.startsWith("[") && jsonString.endsWith("]"))) {
            try {
                // Basic nesting validation using a stack
                return hasValidNesting(jsonString);
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    private static boolean hasValidNesting(String jsonString) {
        // Stack to ensure matching braces/brackets
        java.util.Stack<Character> stack = new java.util.Stack<>();

        for (char c : jsonString.toCharArray()) {
            if (c == '{' || c == '[') {
                stack.push(c);
            } else if (c == '}' || c == ']') {
                if (stack.isEmpty()) return false; // Closing without opening
                char last = stack.pop();
                if ((c == '}' && last != '{') || (c == ']' && last != '[')) {
                    return false; // Mismatched braces/brackets
                }
            }
        }

        // Valid if stack is empty after parsing
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String validJson = "{\"name\": \"John\", \"age\": 30}";
        String invalidJson = "{\"name\": \"John\", \"age\": 30"; // Missing closing brace
        String validArrayJson = "[{\"name\": \"John\"}, {\"name\": \"Jane\"}]";
        String invalidArrayJson = "[{\"name\": \"John\"}, {\"name\": \"Jane\"";

        System.out.println(isValidJson(validJson)); // Output: true
        System.out.println(isValidJson(invalidJson)); // Output: false
        System.out.println(isValidJson(validArrayJson)); // Output: true
        System.out.println(isValidJson(invalidArrayJson)); // Output: false
    }
}
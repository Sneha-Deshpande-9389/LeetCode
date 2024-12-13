package Docusign;

public class ManualJsonValidator {

    public static boolean isValidJson(String jsonString) {
        // Trim leading and trailing spaces
        jsonString = jsonString.trim();

        // Check if the string starts and ends with valid JSON characters
        if ((jsonString.startsWith("{") && jsonString.endsWith("}")) ||
            (jsonString.startsWith("[") && jsonString.endsWith("]"))) {
            try {
                // Validate structure using recursion
                return validateJsonStructure(jsonString);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                return false;
            }
        }
        return false; // Invalid if it doesn't start with { or [
    }

    private static boolean validateJsonStructure(String jsonString) {
        int len = jsonString.length();

        if (jsonString.startsWith("{")) {
            // Validate JSON object
            return validateObject(jsonString.substring(1, len - 1));
        } else if (jsonString.startsWith("[")) {
            // Validate JSON array
            return validateArray(jsonString.substring(1, len - 1));
        }
        return false;
    }

    private static boolean validateObject(String content) {
        // Split by commas but ensure commas inside nested structures are ignored
        String[] pairs = splitIgnoringBraces(content, ',');

        for (String pair : pairs) {
            String[] keyValue = splitIgnoringBraces(pair, ':');
            if (keyValue.length != 2) {
                return false; // Each pair must have a key and a value
            }

            String key = keyValue[0].trim();
            String value = keyValue[1].trim();

            // Validate key
            if (!isValidString(key)) {
                return false;
            }

            // Validate value
            if (!isValidValue(value)) {
                return false;
            }
        }
        return true;
    }

    private static boolean validateArray(String content) {
        // Split by commas but ensure commas inside nested structures are ignored
        String[] items = splitIgnoringBraces(content, ',');

        for (String item : items) {
            if (!isValidValue(item.trim())) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidValue(String value) {
        value = value.trim();

        if (value.startsWith("\"") && value.endsWith("\"")) {
            return isValidString(value); // String
        } else if (value.equals("true") || value.equals("false") || value.equals("null")) {
            return true; // Boolean or null
        } else if (value.matches("-?\\d+(\\.\\d+)?")) {
            return true; // Number
        } else if (value.startsWith("{") && value.endsWith("}")) {
            return validateObject(value.substring(1, value.length() - 1)); // Nested object
        } else if (value.startsWith("[") && value.endsWith("]")) {
            return validateArray(value.substring(1, value.length() - 1)); // Nested array
        }
        return false; // Invalid value
    }

    private static boolean isValidString(String str) {
        // A valid JSON string must start and end with double quotes
        return str.startsWith("\"") && str.endsWith("\"");
    }

    private static String[] splitIgnoringBraces(String content, char delimiter) {
        int braceCount = 0;
        int bracketCount = 0;
        StringBuilder current = new StringBuilder();
        java.util.List<String> result = new java.util.ArrayList<>();

        for (char c : content.toCharArray()) {
            if (c == '{') braceCount++;
            if (c == '}') braceCount--;
            if (c == '[') bracketCount++;
            if (c == ']') bracketCount--;

            if (c == delimiter && braceCount == 0 && bracketCount == 0) {
                result.add(current.toString().trim());
                current = new StringBuilder();
            } else {
                current.append(c);
            }
        }

        if (current.length() > 0) {
            result.add(current.toString().trim());
        }

        return result.toArray(new String[0]);
    }

    public static void main(String[] args) {
        // Test cases
        String validJsonObject = """
        {
            "name": "John",
            "age": 30,
            "isStudent": false,
            "addresses": [{
                "city": "New York",
                "zip": 10001
            },
            {
                "city": "New York1",
                "zip": 10002
            }],
            "scores": [95, 88, 76]
        }
        """;

        String invalidJsonObject = """
        {
            "name": "John",
            "age": "30",,
            "isStudent": false,
            "address": {
                "city": "New York"
            }
        }
        """;

        System.out.println("Valid JSON Object: " + isValidJson(validJsonObject)); // Output: true
        System.out.println("Invalid JSON Object: " + isValidJson(invalidJsonObject)); // Output: false
    }
}
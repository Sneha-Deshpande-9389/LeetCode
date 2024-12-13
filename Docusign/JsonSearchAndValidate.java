package Docusign;

/*import org.json.JSONArray;
import org.json.JSONObject;*/

import java.util.HashMap;
import java.util.Map;


public class JsonSearchAndValidate {
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
    public static boolean jsonManual(String jsonString) {
        return validateJsonStructure(jsonString);
    }

    public static void main(String[] args) {
        // Define a schema
        String schemaString = """
        {
            "name": "string",
            "age": "number",
            "isStudent": "boolean",
            "address": {
                "city": "string",
                "zip": "number"
            },
            "scores": ["number"]
        }
        """;

        // Define a valid JSON object
        String validJsonString = """
        {
            "address": {
                "city": "New York",
                "zip": 10001
            },
            "scores": [95, 88, 76]
        }
        """;

        // Define an invalid JSON object
        String invalidJsonString = """
        {
            "name": "John",
            "age": "twenty-five",
            "isStudent": true,
            "address": {
                "city": "New York",
                "zip": "10001"
            },
            "scores": [95, "eighty-eight", 76]
        }
        """;

        // Define a key to search for
        String keyToSearch = "city";

        jsonManual(validJsonString); // Output: true
        jsonManual(invalidJsonString); // Output: false

       /* // Parse JSON strings
        JSONObject schema = new JSONObject(schemaString);
        JSONObject validJson = new JSONObject(validJsonString);
        JSONObject invalidJson = new JSONObject(invalidJsonString);

        // Validate JSON against schema
        System.out.println("Valid JSON matches schema: " + validateJsonSchema(validJson, schema)); // Output: true
        System.out.println("Invalid JSON matches schema: " + validateJsonSchema(invalidJson, schema)); // Output: false

        // Search for a key
        System.out.println("Key '" + keyToSearch + "' found in valid JSON: " + searchKey(validJson, keyToSearch)); // Output: true
        System.out.println("Key '" + keyToSearch + "' found in invalid JSON: " + searchKey(invalidJson, keyToSearch)); // Output: true*/
    }
}
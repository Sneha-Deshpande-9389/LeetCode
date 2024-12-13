package Confluent;


import java.util.HashMap;
import java.util.Random;

// "static void main" must be defined in a public class.
public class TinyUrl {
    public static void main(String[] args) {
        UrlShortener shortner = new UrlShortener();
        String encoded = shortner.encode("longurl");
        System.out.println(encoded);
        System.out.println(shortner.decode(encoded));

    }
}

class UrlShortener {
    HashMap<String, String> shortKeyToLongUrlMap;

    String charSet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /*
    unique keys needed = 1B
    6 or 7 characters length
    1 B keys = 1 * 10 ^ 9 keys
    permutations possible with 62 characters
    Using 62 chars, 6 characters at a time for one key , we get 44B keys
    */

    Random rand ;

    String urlPrefix;

    UrlShortener() {
        rand = new Random();
        shortKeyToLongUrlMap = new HashMap<>();
        urlPrefix = "https:/tinyurl.com/";
    }

    private String getRandShortKey() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< 7; i++) {
            sb.append(charSet.charAt(rand.nextInt(62)));
        }
        return sb.toString();
    }

    public String encode(String longUrl) {
        String shortKey = getRandShortKey();
        while(shortKeyToLongUrlMap.containsKey(shortKey)) {
            shortKey = getRandShortKey();
        }
        shortKeyToLongUrlMap.put(shortKey, longUrl);
        return urlPrefix+shortKey;
    }

    public String decode(String shortUrl) {
        return shortKeyToLongUrlMap.getOrDefault(shortUrl.replace(urlPrefix, ""), null);
    }


}

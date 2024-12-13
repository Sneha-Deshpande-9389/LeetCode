package Confluent;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

class WindowedMapNode {
    String key;
    Long val;

    long time;

    WindowedMapNode(String k, long v) {
        this.key = k;
        this.val = v;
        this.time = System.currentTimeMillis();
    }
}
public class WindowedMap {
    public static void main(String[] args) throws Exception {
        WindowedMap map = new WindowedMap(3 );
        map.put("foo", 40);
        map.put("bar", 70);
        System.out.println(map.getAverage());

        System.out.println("Initial");
        System.out.println(map.get("foo"));
        System.out.println(map.get("bar"));
        map.put("foo", 50);
        System.out.println(map.getAverage());

        Thread.sleep(5 * 1000);

        System.out.println("After sleep");
        System.out.println(map.get("foo"));
        System.out.println(map.get("bar"));
        System.out.println(map.getAverage());

        System.out.println("After adding foo again");
        map.put("foo", 50);
        System.out.println(map.getAverage());


    }
    LinkedHashMap<String, WindowedMapNode> map;
    long windowMs;

    long sum;
    WindowedMap(int windowMillis) {
        map = new LinkedHashMap<>();
        this.windowMs = windowMillis;
    }
    private void expire() {
        long currTime = System.currentTimeMillis();
        Iterator<Map.Entry<String, WindowedMapNode>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            var entry = iterator.next();
            if(windowMs < currTime - entry.getValue().time) {
                iterator.remove();
                sum -= entry.getValue().val;
            }
        }
    }

    public void put(String key, long val) {
        expire();
        if(map.containsKey(key)) {
            sum -= map.get(key).val;
            map.remove(key);
        }
        sum += val;
        map.put(key, new WindowedMapNode(key, val));
    }

    public long get(String key) {
        expire();
        return !map.isEmpty() && map.containsKey(key) ? map.get(key).val : 0;
    }

    public long getAverage() {
        expire();
        if(map.size() == 0) {
            return 0;
        }
        return sum/map.size();
    }
}

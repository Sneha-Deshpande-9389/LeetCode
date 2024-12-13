package Confluent;

import javafx.util.Pair;

import java.util.LinkedHashMap;
import java.util.PriorityQueue;

class LRUCacheTTLNode {
    Integer key, value;
    Long expireTimeStamp;

    LRUCacheTTLNode(int k, int v, long expireTimeStamp) {
        this.key = k;
        this.value = v;
        this.expireTimeStamp = expireTimeStamp;
    }

    @Override
    public String toString() {
        return "LRUCacheTTLNode{" +
                "key=" + key +
                ", value=" + value +
                ", expireTimeStamp=" + expireTimeStamp +
                '}';
    }
}
public class LRUCacheTTL {
    LinkedHashMap<Integer, LRUCacheTTLNode> keyToNodeMap ;
    PriorityQueue<Pair<Long, Integer>> timeStampMinheap;
    int capacity;

    public static void main(String[] args) {
        LRUCacheTTL cache = new LRUCacheTTL(2);
        cache.put(1, 3 ,10);
        cache.put(2, 4 ,5);
        System.out.println("cache.get(1, 1) " + cache.get(1, 1));

        System.out.println("cache.get(1, 11) " + cache.get(1, 11));

        System.out.println("cache.get(2, 5) " + cache.get(2, 5));

        System.out.println("cache.get(2, 6) " + cache.get(2, 6));

        cache.put(2, 5 ,6);
        System.out.println("cache.get(2, 6) " + cache.get(2, 6));

        cache.put(3, 100, 10);

        System.out.println("cache.get(2, 6) " + cache.get(2, 6));

        cache.put(4, 400, 10);
        cache.put(5, 500, 10);

        System.out.println("cache.get(3, 10) " + cache.get(3, 6));
        System.out.println("cache.get(4, 10) " + cache.get(4, 6));
        System.out.println("cache.get(5, 10) " + cache.get(5, 6));
    }

    public LRUCacheTTL(int capacity) {
        keyToNodeMap = new LinkedHashMap<>();
        this.capacity = capacity;
        timeStampMinheap = new PriorityQueue<>(
                //Long.compare()
                (a, b) -> {
                    if(a.getKey() < (b.getKey())) {
                        return -1;
                    }
                    return 1;
                });
    }

    // get the value from keyToNodeMap if its expireTimeStamp >= timeStamp-ToDo
    public int get(int key, int timeStamp) {
        if(keyToNodeMap.containsKey(key) && (keyToNodeMap.get(key).expireTimeStamp >= timeStamp)) {
            int val = keyToNodeMap.get(key).value;
            long existingTimeStamp = keyToNodeMap.get(key).expireTimeStamp;
            System.out.println("Removing - " + key + " " + val + " " + existingTimeStamp);
            keyToNodeMap.remove(key);

            keyToNodeMap.put(key, new LRUCacheTTLNode(key, val, existingTimeStamp));
            //timeStampMinheap.add(new Pair<>(existingTimeStamp, key));

            System.out.println("Removed and added key-" + key + " map - " + keyToNodeMap.toString() + " minHeap: "
                    + timeStampMinheap.toString());
            return val;
        }
        return -1;
    }

    private void removeExpiredKeys(Long currTimestamp) {
        if(!timeStampMinheap.isEmpty() && timeStampMinheap.peek().getKey() > currTimestamp) {
            return;
        }
        while(!keyToNodeMap.isEmpty()
                && keyToNodeMap.size() >= capacity) {
            var expiringEntry = timeStampMinheap.poll();
            Integer expiringKey = expiringEntry.getValue();
            keyToNodeMap.remove(expiringKey);
        }
    }

    private void removeLRUKeys() {
        while(keyToNodeMap.size() > 0 && keyToNodeMap.size() >= capacity) {
            var expiringKey = keyToNodeMap.get(0);
            keyToNodeMap.remove(expiringKey);
        }
    }

    // set or update-ToDo
    public void put(int key, int value, long expireTimeStamp) {
        if(keyToNodeMap.containsKey(key)) {
            System.out.println("Putting new value for - " + key);
            long existingTimeStamp = keyToNodeMap.get(key).expireTimeStamp;
            keyToNodeMap.remove(key);

            if(timeStampMinheap.removeIf(pair -> pair.getKey().equals(existingTimeStamp) && pair.getValue().equals(key))) {
                System.out.println("Element found to be removed- "+ existingTimeStamp);
            }
        }
        removeExpiredKeys(expireTimeStamp);
        removeLRUKeys();
        LRUCacheTTLNode node = new LRUCacheTTLNode(key, value, expireTimeStamp);
        keyToNodeMap.put(key, node);
        timeStampMinheap.offer(new Pair<>(expireTimeStamp, key));
        System.out.println("Added new key-" + keyToNodeMap.toString() + " minHeap: "
                + timeStampMinheap.toString());
    }
}

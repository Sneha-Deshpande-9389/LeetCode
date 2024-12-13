package Confluent;

import javafx.util.Pair;

import java.util.HashMap;


import java.util.PriorityQueue;

abstract class LRUCacheNode {
}

interface LRUCache<T> {
    void put(T key, T value, Long time);
    T get(T key, Long time);
}
class TTLLRUCacheNode extends LRUCacheNode {
    Integer key;
    Integer val;

    Long timestamp;

    TTLLRUCacheNode next, prev;

    TTLLRUCacheNode(int k, int v, Long time) {
        this.key = k;
        this.val = v;
        this.next = null;
        this.prev = null;
        this.timestamp = time;
    }
}
public class TTLCache implements LRUCache<Integer>{
    TTLLRUCacheNode head , tail ;
    HashMap<Integer, TTLLRUCacheNode> keyToNodeMap;

    PriorityQueue<Pair<Long, Integer>> timeStampMinheap;
    int size;
    TTLCache(int size) {
        this.keyToNodeMap = new HashMap<>();
        this.timeStampMinheap = new PriorityQueue<>((a, b) -> {
            if(a.getKey() < (b.getKey())) {
                return -1;
            }
            return 1;
        });
        this.head = new TTLLRUCacheNode(0, 0, Long.MAX_VALUE);
        this.tail = new TTLLRUCacheNode(0, 0, Long.MAX_VALUE);
        this.head.next = tail;
        this.tail.prev = head;
        this.size = size;
    }

    public static void main(String[] args) {
        TTLCache cache = new TTLCache(2);
        cache.put(1, 3 ,10L);
        cache.put(2, 4 ,5L);
        System.out.println("cache.get(1, 1) " + cache.get(1, 1L));

        System.out.println("cache.get(1, 11) " + cache.get(1, 11L));

        System.out.println("cache.get(2, 5) " + cache.get(2, 5L));

        System.out.println("cache.get(2, 6) " + cache.get(2, 6L));

        cache.put(2, 5 ,6L);
        System.out.println("cache.get(2, 6) " + cache.get(2, 6L));

        cache.put(3, 100, 10L);

        System.out.println("cache.get(2, 6) " + cache.get(2, 6L));

        cache.put(4, 400, 10L);
        cache.put(5, 500, 10L);

        System.out.println("cache.get(3, 10) " + cache.get(3, 6L));
        System.out.println("cache.get(4, 10) " + cache.get(4, 6L));
        System.out.println("cache.get(5, 10) " + cache.get(5, 6L));
    }


    private void removeExpiredNodes(Long time) {
        TTLLRUCacheNode node =  head.next;

        while(node != tail && node.timestamp < time) {
            System.out.println("deleting n " + node.key);
            removeNode(node);
            if(node == tail) {
                return;
            }
            node = node.next;
        }
    }
    private void removeLRUNodes() {
        if(keyToNodeMap.size() == size) {
            removeNode(head.next);
        }
    }
    // remove from anywhere
    private int removeNode(TTLLRUCacheNode node) {
        TTLLRUCacheNode currPrev = node.prev;
        currPrev.next = node.next;
        node.next.prev = currPrev;

        keyToNodeMap.remove(node.key);
        timeStampMinheap.removeIf(pair ->
                pair.getKey().equals(node.timestamp)
                        && pair.getValue().equals(node.key));
        return node.key;
    }
    // add new node before tail
    private void addNode(TTLLRUCacheNode node) {
        TTLLRUCacheNode curr = tail.prev;
        node.prev = curr;
        node.next = tail;

        tail.prev = node;
        curr.next = node;

        keyToNodeMap.put(node.key, node);
        timeStampMinheap.offer(new Pair(node.timestamp, node.key));
    }

    @Override
    public void put(Integer key, Integer val, Long time) {
        if(keyToNodeMap.containsKey(key)) {
            TTLLRUCacheNode node = keyToNodeMap.get(key);
            removeNode(node);
        }
        removeExpiredNodes(time);
        removeLRUNodes();
        TTLLRUCacheNode node = new TTLLRUCacheNode(key, val, time);
        addNode(node);
    }

    @Override
    public Integer get(Integer k, Long expTime) {
        if(!keyToNodeMap.containsKey(k)) {
            return -1;
        }
        TTLLRUCacheNode node = keyToNodeMap.get(k);
        if(node.timestamp < expTime) {
            return -1;
        }
        removeNode(node);
        addNode(node);
        return node.val;
    }
}

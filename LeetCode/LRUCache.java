package LeetCode;

import java.util.HashMap;
import java.util.List;

class LRUCacheNode {
    Integer key;
    Integer val;

    LRUCacheNode next, prev;

    LRUCacheNode(int k, int v) {
        this.key = k;
        this.val = v;
        this.next = null;
        this.prev = null;
    }
}
public class LRUCache {
    LRUCacheNode head , tail ;
    HashMap<Integer, LRUCacheNode> keyToNodeMap;
    int size;
    LRUCache(int size) {
        keyToNodeMap = new HashMap<>();
        LRUCacheNode head = new LRUCacheNode(0, 0);
        LRUCacheNode tail = new LRUCacheNode(0, 0);
        head.next = tail;
        tail.prev = head;
        this.size = size;
    }

    public void add(int key, int val) {
        if(keyToNodeMap.containsKey(key)) {
            LRUCacheNode node = keyToNodeMap.get(key);
            removeNode(node);
        }
        if(keyToNodeMap.size() == size) {
            removeNode(head.next);
        }
        LRUCacheNode node = new LRUCacheNode(key, val);
        addNode(node);
    }

    public int get(int k) {
        if(!keyToNodeMap.containsKey(k)) {
            return -1;
        }
        LRUCacheNode node = keyToNodeMap.remove(k);
        removeNode(node);
        addNode(node);
        return node.val;
    }

    private int removeNode(LRUCacheNode node) {
        LRUCacheNode currPrev = node.prev;
        currPrev.next = node.next;
        node.next.prev = currPrev;

        keyToNodeMap.remove(node.key);
        return node.key;
    }

    private void addNode(LRUCacheNode node) {
        LRUCacheNode curr = tail.prev;
        node.prev = curr;
        node.next = tail;

        tail.prev = node;
        curr.next = node;

        keyToNodeMap.put(node.key, node);
    }
}

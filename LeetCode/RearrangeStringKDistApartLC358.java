package LeetCode;

import java.util.*;

public class RearrangeStringKDistApartLC358 {
    public static void main(String[] args) {
        System.out.println(new RearrangeStringKDistApartLC358().rearrangeString("aabbcc", 3));

        System.out.println(new RearrangeStringKDistApartLC358().rearrangeString("aaabc", 3));
        System.out.println(new RearrangeStringKDistApartLC358().rearrangeString("aaabc", 2));
    }
    public String rearrangeString(String str, int k) {

        StringBuilder rearranged = new StringBuilder();
        //count frequency of each char
        Map<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }

        //construct a max heap using self-defined comparator, which holds all Map entries, Java is quite verbose
        Queue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> entry1, Map.Entry<Character, Integer> entry2) {
                return entry2.getValue() - entry1.getValue();
            }
        });

        Queue<Map.Entry<Character, Integer>> waitQueue = new LinkedList<>();
        maxHeap.addAll(map.entrySet());

        while (!maxHeap.isEmpty()) {

            Map.Entry<Character, Integer> current = maxHeap.poll();
            rearranged.append(current.getKey());
            current.setValue(current.getValue() - 1);
            waitQueue.offer(current);

            if (waitQueue.size() < k) { // intial k-1 chars, waitQueue not full yet
                continue;
            }
            // release from waitQueue if char is already k apart
            Map.Entry<Character, Integer> front = waitQueue.poll();
            //note that char with 0 count still needs to be placed in waitQueue as a place holder
            if (front.getValue() > 0) {
                maxHeap.offer(front);
            }
        }

        return rearranged.length() == str.length() ? rearranged.toString() : "";
    }
    public String rearrange(String s, int k) {
        StringBuffer sb = new StringBuffer();
        int index = 0;
        PriorityQueue<TaskNode> taskNodePriorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        Map<Character, Integer> taskFreqMap = new HashMap<>();
        for(Character c : s.toCharArray()) {
            taskFreqMap.put(c, taskFreqMap.getOrDefault(c, 0) + 1);
        }
        for(Map.Entry<Character, Integer> taskToFreqEntry : taskFreqMap.entrySet()) {
            taskNodePriorityQueue.offer(new TaskNode(taskToFreqEntry.getKey(),
                    taskToFreqEntry.getValue()));
        }

        Queue<Map.Entry<TaskNode, Integer>> frequentTaskQueue = new LinkedList<>();
        while (!taskNodePriorityQueue.isEmpty() || !frequentTaskQueue.isEmpty()) {
            index++;
            System.out.println("index: " + index + " Heap: " + taskNodePriorityQueue.toString() + " queue: " + frequentTaskQueue.toString());
            if(!taskNodePriorityQueue.isEmpty()) {
                TaskNode currNode = taskNodePriorityQueue.poll();
                sb.append(currNode.taskId);

                int freq = currNode.getFreq() - 1;
                if(freq > 0) {
                    frequentTaskQueue.offer(new AbstractMap.SimpleEntry<>(new TaskNode(currNode.getTaskId(), freq),
                            index+k));
                }
            }

            if(!frequentTaskQueue.isEmpty()) {
                int nextValidIndex = frequentTaskQueue.peek().getValue();
                if(nextValidIndex == index)
                    taskNodePriorityQueue.offer(frequentTaskQueue.poll().getKey());
            }

        }

        return sb.toString();
    }
}

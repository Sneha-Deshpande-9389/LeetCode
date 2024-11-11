package LeetCode;

import java.util.*;

class TaskNode implements Comparable<TaskNode> {
    Character taskId;
    Integer freq;

    public TaskNode(Character taskId, Integer freq) {
        this.taskId = taskId;
        this.freq = freq;
    }

    @Override
    public int compareTo(TaskNode o) {
        return this.freq.compareTo(o.freq);
    }

    public Character getTaskId() {
        return taskId;
    }

    public void setTaskId(Character taskId) {
        this.taskId = taskId;
    }

    public Integer getFreq() {
        return freq;
    }

    public void setFreq(Integer freq) {
        this.freq = freq;
    }

    @Override
    public String toString() {
        return "TaskNode{" +
                "taskId=" + taskId +
                ", freq=" + freq +
                '}';
    }
}
public class TaskScheduler {
    public static void main(String[] args) {
       char[] tasks = {'A','A','A','B','B','B'};
       System.out.println(new TaskScheduler().leastInterval(tasks, 2));

       tasks = new char[]{'A', 'C', 'A', 'B', 'D', 'B' };
       System.out.println(new TaskScheduler().leastInterval(tasks, 1));

       tasks = new char[]{'A', 'A', 'A', 'B', 'B', 'B' };
        System.out.println(new TaskScheduler().leastInterval(tasks, 3));
    }

    public int leastInterval(char[] tasks, int n) {
        int time = 0;
        PriorityQueue<TaskNode> freqMaxHeap = new PriorityQueue<>(Collections.reverseOrder());
        HashMap<Character, Integer> taskToFreqMap = new HashMap<>();
        for(char task : tasks) {
            taskToFreqMap.put(task, taskToFreqMap.getOrDefault(task, 0) + 1);
        }
        for(Character key : taskToFreqMap.keySet()) {
            freqMaxHeap.offer(new TaskNode(key, taskToFreqMap.get(key)));
        }
        List<Character> res = new ArrayList<>();
        Queue<Map.Entry<TaskNode, Integer>> taskQueue = new LinkedList<>();
        while(!freqMaxHeap.isEmpty() || !taskQueue.isEmpty()) {
            time++;
            if(!freqMaxHeap.isEmpty()) {
                TaskNode currTask = freqMaxHeap.poll();
                int freq = currTask.getFreq() - 1;
                res.add(currTask.taskId);
                if(freq > 0) {
                    taskQueue.offer(new AbstractMap.SimpleEntry<>(new TaskNode(currTask.getTaskId(), freq),
                            time+n));
                }
            } else {
                res.add('I');
            }
            if(!taskQueue.isEmpty()) {
                int nextValidTime = taskQueue.peek().getValue();
                if(nextValidTime == time)
                    freqMaxHeap.offer(taskQueue.poll().getKey());
            }

        }
        System.out.println(res);
        return time;
    }

}

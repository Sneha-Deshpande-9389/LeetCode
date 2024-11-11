package LeetCode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TaskScheduler2LC2365 {
    public static void main(String[] args) {
        System.out.println(new TaskScheduler2LC2365().taskSchedulerII(new int[]{1, 2, 1, 2, 3, 1}, 3));
    }


    public long taskSchedulerII(int[] tasks, int space) {
        Map<Integer, Integer> taskToIndexMap = new HashMap<>();
        //Queue<Integer> taskOrder = new LinkedList<>();

        //for(int task: tasks) {
        //   taskOrder.offer(task);
        //}

        int index = 0, i = 0;
        while (i<tasks.length) {
            index++;
            int taskToDo = tasks[i];
            //System.out.println("Day: " +  index + " taskToDo-" + taskToDo);

            if(taskToIndexMap.isEmpty() || !taskToIndexMap.containsKey(taskToDo)) {
                taskToIndexMap.put(taskToDo, index);
                // System.out.println("Day: " +  index + " taskToDo: " + taskToDo + " First occurance of task "  );
                i++;
            }
            else {
                int currSpace = index - taskToIndexMap.get(taskToDo);
                if(currSpace >= space+1) {

                    //System.out.println("Day: " +  index + " Task-" +  taskToDo + " already exists at " + taskToIndexMap.get(taskToDo));

                    taskToIndexMap.put(taskToDo, index);
                    i++;
                }
            }
        }
        return index;

    }
}

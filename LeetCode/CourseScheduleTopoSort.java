package LeetCode;

import java.util.*;

public class CourseScheduleTopoSort {
    public int[] canFinish(int numCourses, int[][] prerequisites) {
        //base case
        if(prerequisites.length == 0) {
            return new int[0];
        }

        Map<Integer, Integer> indegree = new HashMap<>();
        HashMap<Integer, List<Integer>> topoMap = new HashMap<>();
        for(int i=0; i < prerequisites.length; i++) {
            int src = prerequisites[i][1];
            int dst = prerequisites[i][0];
            topoMap.putIfAbsent(src, new ArrayList<>());
            topoMap.putIfAbsent(dst, new ArrayList<>());
            topoMap.get(src).add(dst);

            indegree.putIfAbsent(src, 0);
            indegree.putIfAbsent(dst, 0);
            indegree.put(dst, indegree.get(dst) + 1);
        }
        Queue<Integer> queue = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry : indegree.entrySet()) {
            if(entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }
        int[] res = new int[numCourses];
        int base = 0;

        while(!indegree.isEmpty()) {
            boolean flag = false;   // use to check whether there is cycle
            int key = queue.remove(); // find nodes with 0 indegree
            if(indegree.get(key) == 0) {
                res[base ++] = key;
                List<Integer> children = topoMap.get(key);  // get the node's children, and minus their inDegree
                for(int child : children) {
                    indegree.put(child, indegree.get(child) - 1);
                    if(indegree.get(child) == 0) {
                            queue.offer(child);
                    }
                }
                indegree.remove(key);      // remove the current node with 0 degree and start over
                flag = true;
            }
            if(!flag)  // there is a circle --> All Indegree are not 0
                return new int[0];
        }
        return res;
    }

    public static void main(String[] args) {
        //System.out.println(Arrays.toString(new LeetCode.CourseScheduleTopoSort().canFinish(5, new int[][]{ {1,0}, {2,0}, {3,1}, {3,2} })));
        System.out.println(Arrays.toString(new CourseScheduleTopoSort().canFinish(4, new int[][]{{1,0},{2,0},{3,1}, {3,2}})));
        //System.out.println(Arrays.toString(new LeetCode.CourseScheduleTopoSort().canFinish(5, new int[][]{ {1,4}, {2,4}, {3,1}, {3,2} })));

    }
}

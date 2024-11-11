package LeetCode;

import java.util.*;

public class ParallelCourses {
    public static void main(String[] args) {
        System.out.println(new ParallelCourses().minimumSemesters(3, new int[][]{{1,3}, {2,3}}));
    }
    public int minimumSemesters(int n, int[][] relations) {
        // base case

        // initialization
        HashMap<Integer, Integer> indegree = new HashMap<>();
        HashMap<Integer, List<Integer>> topoMap = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            indegree.put(i, 0);
            topoMap.put(i, new ArrayList<Integer>());
        }

        for (int[] relation : relations) {
            int prevCourse = relation[0];
            int nextCourse = relation[1];
            topoMap.get(prevCourse).add(nextCourse);
            indegree.put(nextCourse, indegree.get(nextCourse) + 1);
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (indegree.get(i) == 0) {
                queue.offer(i);
            }
        }
        int step = 0, studiedCount = 0;
        while (!queue.isEmpty()) {

            step++;
            Queue<Integer> queueNew = new LinkedList<>();
            while(!queue.isEmpty()) {
                int key = queue.poll();
                studiedCount++;

                for (int child : topoMap.get(key)) {
                    indegree.put(child, indegree.get(child) - 1);
                    //topoMap.remove(child);
                    if (indegree.get(child) == 0) {
                        queueNew.offer(child);
                    }

                }

            }
            queue = queueNew;

        }

        return studiedCount == n ? step : -1;
    }
}

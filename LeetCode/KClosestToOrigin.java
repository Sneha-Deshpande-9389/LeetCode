package LeetCode;

import java.util.*;

public class KClosestToOrigin {
    //class LeetCode.Solution {
        public static void main(String[] args) {
            System.out.println(Arrays.deepToString(kClosest(new int[][]{{3,3},{5,-1},{-2,4}}, 2)));
        }
        public static int[][] kClosest(int[][] points, int k) {
            PriorityQueue<Double> queue = new PriorityQueue<>(k, Collections.reverseOrder());
            HashMap<Double, int[]> map = new HashMap<>();

            int x1 = 0, y1 = 0;
            for(int[] point: points) {
                    int x2 = point[0];
                    int y2 = point[1];
                    double dist = Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2));
                    map.put(dist, new int[]{x2,y2});
                    queue.offer(dist);
                    if(queue.size() > k) {
                        queue.poll();
                    }
            }

            System.out.println(Arrays.deepToString(queue.toArray()));

            int[][] res = new int[k][2];
            int row = 0;
            for(int i =0; i < k;i++) {
                Double dist = queue.poll();
                res[row++] = map.get(dist);
            }
            return res;
        }
   // }
}

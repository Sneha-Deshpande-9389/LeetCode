package LeetCode;

import java.util.*;

public class NetworkDelay {
    public static void main(String[] args) {
        int[][] time = {{2,1,1},{2,3,1},{3,4,1}};
        int n = 4, k = 2;
        System.out.println(new NetworkDelay().networkDelayTime(time, n, k));
    }

    Map<Integer, List<int[]>> adj = new HashMap<>();

    private void dijkstra(int[] signalReceivedAt, int source, int n) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.add(new int[]{0, source});

        // Time for starting node is 0
        signalReceivedAt[source] = 0;

        while (!pq.isEmpty()) {
            int[] topPair = pq.remove();

            int currNode = topPair[0];
            int currNodeTime = topPair[1];

            if (currNodeTime > signalReceivedAt[currNode]) {
                continue;
            }

            if (!adj.containsKey(currNode)) {
                continue;
            }

            // Broadcast the signal to adjacent nodes
            for (int[] edge : adj.get(currNode)) {
                int time = edge[0];
                int neighborNode = edge[1];

                // Fastest signal time for neighborNode so far
                // signalReceivedAt[currNode] + time :
                // time when signal reaches neighborNode
                if (signalReceivedAt[neighborNode] > currNodeTime + time) {
                    signalReceivedAt[neighborNode] = currNodeTime + time;
                    pq.add(new int[]{signalReceivedAt[neighborNode], neighborNode});
                }
            }
        }
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        // Build the adjacency list
        for (int[] time : times) {
            int source = time[0];
            int dest = time[1];
            int travelTime = time[2];

            adj.putIfAbsent(source, new ArrayList<>());
            adj.get(source).add(new int[]{travelTime, dest});
        }

        int[] signalReceivedAt = new int[n + 1];
        Arrays.fill(signalReceivedAt, Integer.MAX_VALUE);

        dijkstra(signalReceivedAt, k, n);

        int answer = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, signalReceivedAt[i]);
        }

        // INT_MAX signifies atleat one node is unreachable
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}
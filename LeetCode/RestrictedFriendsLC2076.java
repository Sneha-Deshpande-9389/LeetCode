package LeetCode;

import java.util.*;

public class RestrictedFriendsLC2076 {
    public static void main(String[] args) {

    }
    int[] parent, rank;
    Map<Integer, List<Integer>> restrictionsMap;
    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        parent = new int[n];
        rank = new int[n];
        restrictionsMap = new HashMap<>();

        for(int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
            restrictionsMap.put(i, new ArrayList<>());
        }

        for(int[] restriction: restrictions) {
            List<Integer> list = restrictionsMap.get(restriction[0]);
            list.add(restriction[1]);
            restrictionsMap.put(restriction[0], list);

            list = restrictionsMap.get(restriction[1]);
            list.add(restriction[0]);
            restrictionsMap.put(restriction[1], list);
        }

        boolean[] result = new boolean[requests.length];
        int resultIndex = 0;
        for(int[] request: requests) {
            int friendPossible = combine(request[0], request[1], restrictionsMap);
            if(friendPossible == -1) {
                result[resultIndex++] = false;
            }
            else {
                result[resultIndex++] = true;
            }
        }
        return result;
    }

    private int find(int u) {
        if(u == parent[u]) {
            return u;
        }
        return parent[u] = find(parent[u]);
    }

    int combine (int u, int v, Map<Integer, List<Integer>> restrictions) {
        int rootU = find(u);
        int rootV = find(v);

        if(rootV == rootU) {
            return 0;
        }
        if(restrictions.containsKey(rootU) && restrictions.get(rootU).contains(rootV)
                || restrictions.containsKey(rootV) && restrictions.get(rootV).contains(rootU)) {
            return -1;
        }

        if(rank[rootU] >= rank[rootV]) {
            parent[rootV] = rootU;
            rank[rootU] += rank[rootV];

            for (int node: restrictions.get(rootV)){
                restrictions.get(rootU).add(find(node));
            }
        } else {
            parent[rootU] = rootV;
            rank[rootV] += rank[rootU];

            for (int node: restrictions.get(rootU)){
                restrictions.get(rootV).add(find(node));
            }
        }
        return 1;
    }
}

/*
n=3
parent
0 1 2
0 1 0

{0,2}-true, 
{2,1}-false

size
0 1 2
2 1 1
*/
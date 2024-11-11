package LeetCode;

import java.util.*;
import java.util.stream.Collectors;

public class GoogleQuesParty {
    public static void main(String[] args) {
        int[][] nodes = new int[][] {
                {1,1}, {2,1}, {3,0}, {3,3}, {4,2}
        };
        int n = 4;
        List<List<int[]>> res = new GoogleQuesParty().findLargestGraph(nodes, 1.5);
    }
    public List<List<int[]>> findLargestGraph(int[][] nodes, double d) {
        //check base condition- ToDo

        List<List<int[]>> res = new ArrayList<>();
        // sort nodes
        Arrays.sort(nodes, Comparator.comparingDouble(val -> val[0]));

        Map<int[], List<int[]>> map = new HashMap<>();
        for (int i = 0; i < nodes.length; i++) {
            int[] node1 = nodes[i];
            for(int j = i+1; j < nodes.length; j++) {
                int[] node2 = nodes[j];
                double curr_d = calculateDist(node1, node2);// ToDo
                if(curr_d > d) {
                    break;
                } else {
                    map.putIfAbsent(node1, new ArrayList<>());
                    List<int[]> connections = map.get(node1);
                    connections.add(node2);
                    map.put(node1, connections);
                }
            }
        }

        for(Map.Entry<int[], List<int[]>> entry : map.entrySet()) {

            int[] last = entry.getValue().getLast();
            List<int[]> list = new ArrayList<>();
            list.add(entry.getKey());
            list.add(last);

            res.add(list);
            System.out.print("(");
            list.stream().forEach(a -> System.out.print(Arrays.toString(a)));
            System.out.println(")");
        }


        return res;
    }

    private double calculateDist(int[] node1, int[] node2) {
        int x1 = node1[0];
        int x2 = node2[0];

        int y1 = node1[1];
        int y2 = node2[1];

        return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
    }
}
/*
double dist = Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2));
[1,1], [2,1], [3,0], [3,3], [4,2]
d = 1.5
1,1 - 2,1 = sqrt(1 exp 2 + 0 exp 2) = 1 - valid
1,1 - 3,0 = sqrt(4 + 1) = 2
1,1 - 3,3 = sqrt(4 + 4) = 2.5
2,1 - 3,0 = sqrt(1 + 1) = 1 - valid
2,1 - 3,3 = sqrt(1 + 4) = 2.5
3,0 - 3,3 = sqrt(0 + 6) = 3

max - heap
1 (1,1 : 2,1)
1 (2,1 - 3,0)

1. create map of nodes adj check threshold
loop(node for each other nodes) {
    if(lastDist > threshold) {
       break;
    }
}

2. using map construct res


1,1 - 2,1
2,1 -  3,0,
3,0 -
3,3 - 4,2
4,2




a - b = x < d
b - c = y < d

 */
package Karat;
import javafx.util.Pair;

import java.util.*;

/*
Suppose we have an unsorted log file of accesses to web resources. Each log entry consists of an access time,
the ID of the user making the access, and the resource ID.
The access time is represented as seconds since 00:00:00, and all times are assumed to be in the same day.
For example:

logs1 = {
{"58523", "user_1", "resource_1"},
{"62314", "user_2", "resource_2"},
{"54001", "user_1", "resource_3"},
{"200", "user_6", "resource_5"},
{"215", "user_6", "resource_4"},
{"54060", "user_2", "resource_3"},
{"53760", "user_3", "resource_3"},
{"58522", "user_22", "resource_1"},
{"53651", "user_5", "resource_3"},
{"2", "user_6", "resource_1"},
{"100", "user_6", "resource_6"},
{"400", "user_7", "resource_2"},
{"100", "user_8", "resource_6"},
{"54359", "user_1", "resource_3"},
}
Example 2:

logs2 = {
{"300", "user_1", "resource_3"},
{"599", "user_1", "resource_3"},
{"900", "user_1", "resource_3"},
{"1199", "user_1", "resource_3"},
{"1200", "user_1", "resource_3"},
{"1201", "user_1", "resource_3"},
{"1202", "user_1", "resource_3"}
}
Write a function that takes the logs and returns each users min and max access timestamp Example Output:

user_3:{53760,53760}
user_2:{54060,62314}
user_1:{54001,58523}
user_7:{400,400}
user_6:{2,215}
user_5:{53651,53651}
user_4:{58522,58522}
user_8:{100,100}
 */
    public class ResourceAccessLog {
        public static void main(String[] args) {
            String[][] log1 = new String[][]{{"58523", "user_1", "resource_1"},
                    {"62314", "user_2", "resource_2"},
                    {"54001", "user_1", "resource_3"},
                    {"200", "user_6", "resource_5"},
                    {"215", "user_6", "resource_4"},
                    {"54060", "user_2", "resource_3"},
                    {"53760", "user_3", "resource_3"},
                    {"58522", "user_22", "resource_1"},
                    {"53651", "user_5", "resource_3"},
                    {"2", "user_6", "resource_1"},
                    {"100", "user_6", "resource_6"},
                    {"400", "user_7", "resource_2"},
                    {"100", "user_8", "resource_6"},
                    {"54359", "user_1", "resource_3"}
            };

            String[][] log2 = {
                    {"300", "user_1", "resource_3"},
                    {"599", "user_1", "resource_3"},
                    {"900", "user_1", "resource_3"},
                    {"1199", "user_1", "resource_3"},
                    {"1200", "user_1", "resource_3"},
                    {"1201", "user_1", "resource_3"},
                    {"1202", "user_1", "resource_3"}
            };
            List<String[][]> list = new ArrayList<>();
            list.add(log1);
            System.out.println(logParser(list));
        }

        public static HashMap<String, Pair<Integer, Integer>> logParser(List<String[][]> logs) {
            HashMap<String, Integer> minMap = new HashMap<>();
            HashMap<String, Integer> maxMap = new HashMap<>();
            for (String[][] log : logs) {
                for(String[] logLine : log) {
                    String userName = logLine[1];
                    String time = logLine[0];
                    minMap.putIfAbsent(userName, Integer.MAX_VALUE);
                    minMap.put(userName, Math.min(minMap.get(userName), Integer.parseInt(time)));

                    maxMap.putIfAbsent(userName, Integer.MIN_VALUE);
                    maxMap.put(userName, Math.max(maxMap.get(userName), Integer.parseInt(time)));
                }

            }
            HashMap<String, Pair<Integer, Integer>> res = new HashMap<>();
            for (var entry : minMap.entrySet()) {

                String name = entry.getKey();
                Integer minVal = minMap.get(name);
                Integer maxVal = maxMap.get(name);

                res.put(name, new Pair<>(minVal, maxVal));
            }


            return res;
        }
    }


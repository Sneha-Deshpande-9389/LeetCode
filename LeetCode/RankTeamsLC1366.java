package LeetCode;

import java.util.*;

public class RankTeamsLC1366 {
    public static void main(String[] args) {
        System.out.println(new RankTeamsLC1366().rankTeams(new String[] {"ABC","ACB","ABC","ACB","ACB"}).toString());
    }
    public String rankTeams(String[] votes) {
        int len = votes[0].length();
        HashMap<Character, int[]> map = new HashMap<>();
        for(int i = 0; i < 26; i++) {
            map.put((char) (i + 'A'), new int[len]);
        }

        for(int i = 0; i < votes.length; i++){
            String s = votes[i];
            for(int j = 0; j < len; j++){
                map.get(s.charAt(j))[j]++;
            }
        }
        List<Map.Entry<Character, int[]>> list = new LinkedList<>(map.entrySet());

        Collections.sort(list, (a, b) -> {
            int[] aArray = a.getValue();
            int[] bArray = b.getValue();
            for(int i = 0; i < len; i++){
                if(aArray[i] < bArray[i]) return 1;
                if(aArray[i] > bArray[i]) return -1;
            }
            return 0;
        });

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len; i++){
            sb.append(list.get(i).getKey());
        }
        return sb.toString();
    }
}

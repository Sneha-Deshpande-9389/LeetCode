package LeetCode;

import java.util.*;


public class TopVotedCandidate {

    TreeMap<Integer, Integer> timedWinner;
    int lead;
    public TopVotedCandidate(int[] persons, int[] times) {
        int n = times.length;
        lead = -1;
        timedWinner = new TreeMap();
        Map<Integer, Integer> votes = new HashMap();
        for(int i=0; i<n; i++){
            votes.put(persons[i], votes.getOrDefault(persons[i], 0)+1);
            if(i==0 || votes.get(persons[i])>=votes.get(lead)){
                lead = persons[i];
            }
            timedWinner.put(times[i], lead);
        }
    }

    public int q(int t) {
        System.out.println(timedWinner + " q: " + t);
        return timedWinner.floorEntry(t).getValue();
    }
}

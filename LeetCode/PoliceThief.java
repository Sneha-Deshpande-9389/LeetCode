package LeetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class PoliceThief {
    public static void main(String[] args) {
        System.out.println(new PoliceThief().numberOfThivesCaught(new char[] { 'P', 'T', 'T', 'P', 'T'}, 1));
    }
    public int numberOfThivesCaught(char[] arr, int k) {
        HashSet<Integer> police = new HashSet<>();
        HashSet<Integer> thief = new HashSet<>();

        for(int i =0; i < arr.length;i++) {
            if(arr[i] == 'P') {
                police.add(i);
            } else {
                thief.add(i);
            }
        }
        int res = 0;
        int[] indexes = new int[2*k];
        int val = 1;
        for(int i = 0; i < 2*k; i=i+2) {

            indexes[i] = val;
            indexes[i+1] = Math.negateExact(val++);

        }
        System.out.println(Arrays.toString(indexes));
        for (Iterator<Integer> it = police.iterator(); it.hasNext(); ) {
            int policeIndex = it.next();
            int thiefIndex = policeIndex;
            int i = 0;
            while(thiefIndex + indexes[i] < arr.length && thiefIndex + indexes[i] >= 0) {
                if(thief.contains(thiefIndex + indexes[i])) {
                    it.remove();
                    thief.remove(thiefIndex + indexes[i]);
                    res++;
                    // police work is done
                    break;
                } else {
                    i++;
                }
            }

        }

        return res;
    }

}

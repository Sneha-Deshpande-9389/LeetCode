package LeetCode;

import java.util.HashMap;
import java.util.HashSet;

public class FruitBasket {
    public static void main(String[] args) {
        System.out.println(new FruitBasket().totalFruit(new int[] {3,3,3,1,2,1,1,2,3,3,4}));
    }
    public int totalFruit(int[] fruits) {
        int i  =0, j = 0, max = 0, n = fruits.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        while(j < n) {
            // calculate cnt, update map
            map.put(fruits[j], map.getOrDefault(fruits[j], 0) + 1);

            int cnt = map.size();
            if(cnt > 2) {
                while(map.size() > 2) {
                    map.put(fruits[i], map.get(fruits[i]) - 1);
                    if(map.get(fruits[i]) == 0) {
                        map.remove(fruits[i]);
                    }
                    i++;
                }
                j++;
            } else if (cnt == 2){
                int curFruits = j-i+1;
                max = Math.max(max, curFruits);
                j++;
            }
            else {

                j++;
            }
        }

        return max;

    }
  
}


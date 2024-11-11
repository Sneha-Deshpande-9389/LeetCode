package LeetCode;

import java.util.*;

class Edges {
    public static int[][] DIRECTIONS = new int[][] {
            {0,1}, {1, 0}, {0,-1}, {-1, 0}};


/*
   2,1,2,3,3,1

   1,1,3,4,5,1
 */
    public static String addStrings(String num1, String num2) {


        if(num1.length() > num2.length()) {
            // swap
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }


        List<Integer> char1 = new ArrayList<>();
        for(int i =0 ; i < num1.length(); i++) {
            char1.add(num1.charAt(i) - '0');
        }
        List<Integer> char2 = new ArrayList<>();
        for(int i =0 ; i < num2.length(); i++) {
            char2.add(num2.charAt(i) - '0');
        }
        Collections.reverse(char1);
        Collections.reverse(char2);

        int i = 0;
        int carry = 0;
        List<Integer> res = new ArrayList<>();
        while(i < char1.size()) {
            int sum = char1.get(i) + char2.get(i) + carry;
            if(sum > 9) {
                carry = sum / 10;
                sum = sum % 10;
            } else {
                carry = 0;
            }
            res.add(sum);
            i++;
        }

        if(char1.size() < char2.size()) {
            int sum = char2.get(i) + carry;
            if(sum > 9) {
                carry = sum / 10;
                sum = sum % 10;
            } else {
                carry = 0;
            }
            res.add(sum);
            i = char1.size() + 1;
            while(i < char2.size()) {
                sum = char2.get(i) + carry;
                if(sum > 9) {
                    carry = sum / 10;
                    sum = sum % 10;
                } else {
                    carry = 0;
                }
                res.add(sum);
                i++;
            }
        } else if(carry != 0) {
            res.add(carry);
        }
        Collections.reverse(res);
        System.out.println(res);
        StringBuilder resStr = new StringBuilder();
        for(Integer val : res) {
            resStr.append(val);
        }
        return resStr.toString();
    }
    public static int[] twoSum() {
        int[] nums = {2,7,3,1};
        int target = 9;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i =0; i < nums.length; i++) {
            if(!map.isEmpty() && map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{0,0};
    }
    public static void main(String[] args) {
       /* int[][] arr = {
                {1, 1, 0, 1, 0},
                {1, 0, 0, 1, 1},
                {1, 0, 1, 9, 1},
                {1, 1, 1, 0, 0},
                {1, 0, 1, 1, 1}};
       //System.out.println("Value: " + isPathExists(arr));

        int[][] arr1 = {{1, 0, 0, 1, 0},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 1, 0},
                {1, 0, 1, 9, 1},
                {1, 0, 1, 0, 1}};
       // System.out.println("Value: " + isPathExists(arr1));

        int[][] arr3 = { { 1, 1, 0 },
                {1, 9, 0 },
                {1, 1, 0 }};
        //System.out.println("Value: " + isPathExists(arr3));*/

        // Initializing a list of type Linkedlist
        List<Integer> l = new LinkedList<>();
        l.add(10);
        l.add(15);
        l.add(20);
        System.out.println(l);

        // Initializing a collection to be appended to list
        ArrayList<Integer> arr = new ArrayList<Integer>();
        arr.add(100);
        arr.add(200);
        arr.add(300);
        System.out.println(arr);

        l.addAll(arr);

        System.out.println(l);

        arr.removeLast();

        System.out.println(l);

    }


    public int numberOfSubarrays(int[] nums, int k) {
        if(nums.length < k) {
            return 0;
        }

        int res = 0, left = 0, right = k-1;
        int countOddNumbers = checkCount(right, nums);

        while(right < nums.length) {
            // valid subarray
            if(countOddNumbers == k) {
                res++;
                left++ ;

                if(nums[left]/2 == 0) {
                    left++;
                } else {
                    countOddNumbers--;
                    left++;
                }
            } else if(countOddNumbers > k) {
                countOddNumbers--;
                res++;
                if(nums[left]/2 != 0) {
                    left++;
                }
            } else {
                // invalid subarray
                if (nums[right]/2 != 0) {
                    countOddNumbers++;
                }
                right++;
            }

        }

        return res;
    }

    public int checkCount(int r, int[] nums) {
        int cnt = 0;
        for(int i = 0; i <= r; i++) {
            if(nums[i]/2 != 0) {
                cnt++;
            }
        }
        return cnt;
    }


    public static boolean isPathExists(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        HashMap<List<Integer>, List<int[]>> map = new LinkedHashMap<>();
        int[] indexOfDest = new int[]{-1, -1};

        for(int i = 0;i < m ;i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 9) {
                    indexOfDest = new int[]{i, j};
                }
                // add dest to map
                if (matrix[i][j] != 0) {

                    for (int[] direction : Edges.DIRECTIONS) {
                        int newRow = i + direction[0];
                        int newCol = j + direction[1];
                        if (newRow > m - 1 || newRow < 0 || newCol > n - 1 || newCol < 0) {
                            continue;
                        }
                        if (matrix[newRow][newCol] == 1) {
                            List<Integer> key = new ArrayList<>(Arrays.asList(i, j));
                            map.putIfAbsent(key, new ArrayList<>());
                            map.get(key).add(new int[]{newRow, newCol});

                            List<Integer> newKey = new ArrayList<>(Arrays.asList(newRow, newCol));
                            map.putIfAbsent(newKey, new ArrayList<>());
                            map.get(newKey).add(new int[]{i, j});
                        }
                    }
                }
            }
        }
        //find for indexOfDest from map
        Queue<List<Integer>> queue = new LinkedList<>();
        List<List<Integer>> seen = new ArrayList<>();
        //System.out.println(map.keySet().toString());
        queue.offer(new ArrayList<>(Arrays.asList(0, 0)));

            while (!queue.isEmpty()) {
                //System.out.println("polling: " + queue.peek());
                List<Integer> key = queue.poll();
                seen.add(key);
                for (int[] child : map.get(key)) {
                    if (child[0] == indexOfDest[0] && child[1] == indexOfDest[1]) {
                        return true;
                    }
                    List<Integer> l = Arrays.asList(child[0], child[1]);
                    if (!seen.contains(l)) {
                        seen.add(l);
                        queue.offer(l);
                    }
                }
            }

        return false;
    }




    /*
    { { 1, 1, 0 },
      {1, 9, 0 },
      {1, 1, 0 }

      0,0 - 0,1 - 1,1
      0,0 - 1,0 - 1,1
      DIRECTIONS - {{0,1}, {1,0}, {-1,0}, {0,-1}}
      index of 9 = 1,1

    LeetCode.Node - adj matrix
    (0,0) - (0,1), (1,0)
    (0,1) - (0,0), (1,1)
    (1,0) - (0,0), (2,0), (1,1)
    (2,0) - (2,1), (1,0)
    (2,1) - (2,0), (1,1)

    q - , 0,1,  1,0, 2,0
    seen
   0,0 ,  0,1,  1,0, 2,0
     */
}

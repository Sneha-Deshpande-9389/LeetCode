package LeetCode;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public class NextGreaterElement {
    public static void main(String[] args) {
        //int[] nums1 = {4,1,2}, nums2 = {1,3,4,2};

        System.out.println(Arrays.toString(new NextGreaterElement().nextGreaterElement(new int[]{2,4}, new int[]{1,2,3,4})));
    }
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Deque<Integer> st = new LinkedList<>();
        int[] res = new int[nums1.length];
//O(n2) + O(n1)
        for(int i : nums2) {
            while(!st.isEmpty() && st.peek() < i) {
                map.put(st.pop(), i);
            }
            st.push(i);
        }

        for(int i = 0; i < nums1.length; i++) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }

        return res;
    }
}

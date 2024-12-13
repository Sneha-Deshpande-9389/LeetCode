package Confluent;

/*
Find the middle element of a linked list.
https://leetcode.com/discuss/interview-question/5987161/Confluent-Interview-or-Senior-software-engineer-or-oct-2024
 */
public class MiddleElementInLL {
    public static void main(String[] args) {

    }
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;

        while(slow != null && slow.next!=null && fast !=null && fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}

class ListNode {
    int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

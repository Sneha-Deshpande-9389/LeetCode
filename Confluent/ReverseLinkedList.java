package Confluent;

/*
Reverse a linked list.
 */
public class ReverseLinkedList {
    public static ListNode reverseLinkedList(ListNode head) {
        if(head == null) {
            return null;
        }

        if(head.next == null) {
            return head;
        }
        ListNode prev = head;
        ListNode node = head.next;
        ListNode next =  head.next;

        while (next != null) {
            node.next = prev;
            prev = node;
            node = next;
            next = next.next;

        }
        return prev;
    }
}

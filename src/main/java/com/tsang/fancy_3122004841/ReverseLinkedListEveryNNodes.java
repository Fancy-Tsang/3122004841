package com.tsang.fancy_3122004841;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class ReverseLinkedListEveryNNodes {

    // Function to reverse a sublist starting from start and going for N nodes
    private ListNode reverseSublist(ListNode start, int n) {
        ListNode prev = null;
        ListNode curr = start;
        for (int i = 0; i < n; i++) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev; // prev will now be the new head of the sublist
    }

    // Main function to reverse the linked list every N nodes
    public ListNode reverseN(ListNode head, int n) {
        if (head == null || head.next == null || n <= 1) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (head != null) {
            ListNode next = head;
            // Move 'next' pointer by N steps
            for (int i = 0; i < n && next != null; i++) {
                next = next.next;
            }

            // If next is null, we've reached the end of the list
            if (next == null) {
                break;
            }

            // Store the next of the current sublist
            ListNode nextOfSublist = next.next;

            // Reverse the sublist
            head = reverseSublist(head, n);

            // Relink the sublist with the rest of the list
            prev.next = head;

            // Move prev and head to the next node after the reversed sublist
            for (int i = 0; i < n - 1; i++) {
                prev = prev.next;
            }
            head = prev.next.next; // Skip the last node of the previous sublist

            // Cut the connection to avoid loops
            prev.next.next = null;

            // Prepare for the next sublist
            prev = prev.next;
        }

        return dummy.next;
    }

    // Utility function to print the list
    public void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        ReverseLinkedListEveryNNodes solution = new ReverseLinkedListEveryNNodes();

        // Test case 1
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        solution.printList(solution.reverseN(head1, 2));

        // Test case 2
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(5);
        solution.printList(solution.reverseN(head2, 1));
    }
}
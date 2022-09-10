package com.company;

class Node {
    Node next;
    int val;

    Node(int val) {
        this.val = val;
        this.next = null;
    }

    // build linked list
    void buildLinkedList(Node curr, int[] arr) {
        for (int val : arr) {
            curr.next = new Node(val);
            curr = curr.next;
        }
    }

    // get the size of the linked list
    int linkedListSize() {
        Node head = this;
        int size = 0;

        while (head != null) {
            size += 1;
            head = head.next;
        }

        return size;
    }

    // print linked list
    void printLinkedList() {
        Node curr = this;

        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }

        System.out.println();
    }
}

public class BubbleSort {
    // perform bubble sort on linked list
    Node bubbleSortLinkedList(Node head) {
        // get the size of the linked list
        int sizeOfLinkedList = head.linkedListSize();

        // build a sentinelNode which contains dummy value
        Node sentinelNode = new Node(-1);

        // create a ref to head of the linked list
        sentinelNode.next = head;

        // run the bubble sort algorithm on linked list
        for (int i = 0; i < sizeOfLinkedList; i++) {
            Node prevNode = sentinelNode;
            Node currentNode = prevNode.next;
            while (currentNode.next != null) {
                if (currentNode.val > currentNode.next.val) {
                    Node currNode = currentNode;
                    Node nextNode = currentNode.next;
                    prevNode.next = nextNode;
                    currNode.next = nextNode.next;
                    nextNode.next = currNode;
                    currentNode = prevNode.next;
                } else {
                    prevNode = currentNode;
                    currentNode = currentNode.next;
                }
            }
        }

        return sentinelNode.next;
    }

    public static void main(String[] args) {
	    // create the head of linked list
        Node curr = new Node(2);

        // build an array of linked list values
        int[] arr = new int[]{8, 5, 3, 9, 4, 1};

        // add the values to the head of the linked list
        curr.buildLinkedList(curr, arr);

        System.out.println("LinkedList before sorting: ");

        curr.printLinkedList();

        BubbleSort sortLL = new BubbleSort();
        Node updated = sortLL.bubbleSortLinkedList(curr);
        System.out.println("LinkedList after sorting: ");
        updated.printLinkedList();
    }
}

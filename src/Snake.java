//Name: Cameron Bartlett
//Date: 01/21/2025
//Lab 2 - Objects - Snake Game
//Description: Snake class for a snake game

public class Snake {

    // Inner Node class to define Snake segments
    private static class Node {

        // Fields to store position and reference the next node
        int x; // The x-coordinate
        int y; // The y-coordinate
        Node next; // Reference

        // Node constructor
        public Node(int x, int y) {

            this.x = x;
            this.y = y;
            this.next = null;

        }

        // Print current node for debugging
        @Override
        public String toString() {return "(" + x + ", " + y + ")";}
        // System.out.println(node.toString()); to call

    }
    
    // Snake head and tail
    private Node head; //First node of the snake
    private Node tail; // Last node of the snake

    // Snake constructor
    public Snake(int startX, int startY) {

        // Create initial node at starting position
        Node initialNode = new Node(startX, startY);
        head = initialNode;
        tail = initialNode;

    }

    // Adds a new head to snake at specified coordinates
    public void addHead(int x, int y) {

        // Create new node at given position
        Node newHead = new Node(x, y);

        // Link the new node to the current head
        newHead.next = head;

        // Update the head reference
        head = newHead;

        // Update tail reference for initialization and movement
        if (tail == null) {tail = head;}

    }

    // Removes tail node to help simulate more realisitic movement on the game grid
    public void removeTail() {

        // Case to handle one segment in the snake
        if (head == null || head.next == null) {

            head = null;
            tail = null;

        } else {

            // Traverse the list to find the second to last node
            Node current = head;
            while (current.next != null && current.next.next != null) {current = current.next;}

            // Update the tail to be the second to last node
            tail = current;
            // Remove the reference to the last node
            tail.next = null;

        }
    }

    // Getter method for head, used for testing and collision
    public int[] getHeadPosition() {

        // Check to handle the case where head might be null
        if (head == null) {throw new IllegalStateException("Snake has no head!");}
        
        return new int[] {head.x, head.y};

    }

}
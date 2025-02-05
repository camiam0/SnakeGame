//Name: Cameron Bartlett
//Date: 02/04/2025
//Lab 2&3 - Objects - Snake Game
//Description: Snake class for a snake game

package main;

public class Snake {

    // inner Node class to define Snake segments
    static class Node {

        // fields to store position and reference the next node
        int x; // the x-coordinate
        int y; // the y-coordinate
        Node next; // reference

        // Node constructor
        public Node(int x, int y) {

            this.x = x;
            this.y = y;
            this.next = null;

        } // end of Node constructor

        // print current node for debugging
        @Override
        public String toString() {return "(" + x + ", " + y + ")";}
        // System.out.println(node.toString()); to call

    } // end of inner Node class
    
    // snake head and tail
    private Node head; // first node of the snake
    private Node tail; // last node of the snake

    // Snake constructor
    public Snake(int startX, int startY) {

        // create initial node at starting position by calling reset method
        reset(startX, startY);

    } // end of Snake constructor

    // resets the snake to its initial state
    public void reset(int startX, int startY) {

        // create a new initial node at starting position
        Node initialNode = new Node(startX, startY);
        head = initialNode;
        tail = initialNode;

    } // end of reset

    // adds a new head to the snake at specified coordinates
    public void addHead(int x, int y) {

        // create new node at given position
        Node newHead = new Node(x, y);

        // link the new node to the current head
        newHead.next = head;

        // update the head reference
        head = newHead;

        // update tail reference for initialization and movement
        if (tail == null) {tail = head;}

    } // end of addHead

    // adds a new head but doesn't update the tail reference, adding the new segment to the snake
    public void growSnake(int x, int y) {

        // create new node at given position
        Node newHead = new Node(x, y);

        // link the new node to the current head
        newHead.next = head;

        // update the head reference
        head = newHead;
    
    } // end of growSnake

    // removes tail node to help simulate more realisitic movement on the game grid
    public void removeTail() {

        // check if the snake is empty
        if (head == null) {

            System.err.println("Error: Snake is empty! Nothing to remove.");
            return; // exit the method

        } // end of if

        // case to handle one segment in the snake
        if (head == null || head.next == null) {

            head = null;
            tail = null;

        } else {

            // traverse the list to find the second to last node
            Node current = head;
            while (current.next != null && current.next.next != null) {current = current.next;}

            // update the tail to be the second to last node
            tail = current;
            // remove the reference to the last node
            tail.next = null;

        } // end of if/else
    } // end of removeTail

    // getter method for head, used for testing and collision
    public int[] getHeadPosition() {

        // check to handle the case where head might be null
        if (head == null) {throw new IllegalStateException("Snake has no head!");}
        
        return new int[] {head.x, head.y};

    } // end of getHeadPosition

    // getter method for Node object
    public Node getHead() {

        // check to handle the case where head might be null
        if (head == null) {throw new IllegalStateException("Snake has no head!");}

        return head;

    } // end of getHead

    // returns the length of the snake (# of nodes in the list)
    public int getSnakeLength() {

        int count = 0;
        Node current = head;

        // traverse the list and increment the counter
        while (current != null) {

            count++;
            current = current.next;

        } // end of while loop

        return count;

    } // end of getSnakeLength

    // checks if the snake has collided with itself
    public boolean checkSnakeCollision() {

        // traverse the linked list, starting from the second segment (head is excluded)
        Node current = head.next; // skip head

        while (current != null) {

            // checks if the head's position matches any segment in the body
            if (head.x == current.x && head.y == current.y) {return true;} // Collision detected

            current = current.next; // move to the next node

        }

        return false; // no collision detected

    } // end of checkSnakeCollision

    // checks if the snake has collided with a wall
    public boolean checkWallCollision(int gridWidth, int gridHeight) {

        if (head.x < 0 || head.x >= gridWidth || head.y < 0 || head.y >= gridHeight) {return true;} // Collision detected

        return false; // no collision detected

    } // end of checkWallCollision
    
    // combines collision checks into one method
    public boolean checkCollision(int gridWidth, int gridHeight) {return checkSnakeCollision() || checkWallCollision(gridWidth, gridHeight);} // end of checkCollision
} // end of Snake class
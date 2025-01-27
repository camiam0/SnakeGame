public class TestSnake {
    
    public static void main(String[] args) {

        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test5 = false;

        // Test 1: Constructor initializes the snake at the correct position
        System.out.println("Testing Constructor...");
        Snake snake = new Snake (5, 5);
        // Get the head position
        int [] headPosition = snake.getHeadPosition();
        System.out.println("Initial Head: " + java.util.Arrays.toString(headPosition));
        // Check if the head pos matches the expected values 5, 5
        if (headPosition[0] == 5 && headPosition[1] == 5) {test1 = true;}
        // Case for fail
        else {System.err.println("Error: Expected [5, 5]");}
        System.out.println("Test 1 passed: " + test1);

        // Test 2: addHead updates the head position correctly
        System.out.println("\nTesting addHead...");
        snake.addHead(6, 5);
        // Get new head position
        int [] newHead = snake.getHeadPosition();
        System.out.println("New Head: " + java.util.Arrays.toString(newHead));
        // Check if the head pos matches the expected values 6, 5
        if (newHead[0] == 6 && newHead[1] == 5) {test2 = true;}
        // Case for fail
        else {System.err.println("Error: Expected [6, 5]");}
        System.out.println("Test 2 passed: " + test2);

        // Test 3: removeTail removes the tail correctly
        System.out.println("\nTesting removeTail...");
        snake.removeTail();
        // Get snake length
        int snakeLength = snake.getSnakeLength();
        System.out.println("\nSnake Length: " + snakeLength);
        // Check to see if the tail was removed
        if (snakeLength == 1) {test3 = true;}
        // Case for fail
        else {System.err.println("Error: Expected length of 1");}
        System.out.println("Test 3 passed: " + test3);

        // Test 4: removeTail on an empty snake
        System.out.println("\nTesting removeTail on an empty snake...");
        snake.removeTail(); // Delete last node
        snake.removeTail(); // Call again to trigger error

        // Test 5: reset method works as intended
        System.out.println("\nTesting reset...");
        snake.reset(0, 0);
        // Check snake length
        snakeLength = snake.getSnakeLength();
        System.out.println("New Snake Length: " + snakeLength);
        // Get the head position
        headPosition = snake.getHeadPosition();
        System.out.println("New initial Head: " + java.util.Arrays.toString(headPosition));
        // Check if length and position are correct
        if (snakeLength == 1 && headPosition[0] == 0 && headPosition[1] == 0) {test5 = true;}
        // Case for fail
        else {System.err.println("Error: Expected length of 2 and position of [0, 0]");}
        System.out.println("Test 5 passed: " + test5);

    }
}
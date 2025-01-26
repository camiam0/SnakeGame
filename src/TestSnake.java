public class TestSnake {
    
    public static void main(String[] args) {

        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;

        // Test 1: Constructor initializes the snake at the correct position
        System.out.println("Testing Constructor...");
        Snake snake = new Snake (5, 5);
        // Get the head position
        int [] headPosition = snake.getHeadPosition();
        System.out.println("Initial Head: " + java.util.Arrays.toString(headPosition));
        // Check if the head pos matches the expected values 5, 5
        if (headPosition[0] == 5 && headPosition[1] == 5) {test1 = true;}
        // Case for fail
        else {System.out.println("Expected (5, 5) but got " + java.util.Arrays.toString(headPosition));}
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
        else {System.out.println("Expected (6, 5) but got " + java.util.Arrays.toString(newHead));}
        System.out.println("Test 2 passed: " + test2);

    }

}

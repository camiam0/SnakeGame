//Names: Cameron Bartlett, Yosyp Vasyliev, John Kerstetter
//Date: 02/04/2025
//Lab 2&3 - Objects - Snake Game
//Description: Class for game

package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

// this class is for setting up the game window and the game loop
// as well as sprites
public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    int FPS = 60; // frames per second

    KeyHandler keyH = new KeyHandler(this); // pass GamePanel to KeyHandler
    Thread gameThread;

    private Snake snake; // define snake
    private Fruit fruit; // define fruit

    //MOVEMENT
    private String lastDirection = "NONE"; // default starting direction
    private boolean waitingForInput = true; // ensures movement is blocked until the player presses a key
    private int moveDelay = 175; // time in miliseconds between movements
    private long lastMoveTime = System.currentTimeMillis(); // tracks when the snake last moved

    public GamePanel() {

        // initialize game window
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        // initialize the snake
        snake = new Snake(5 * tileSize, 5 * tileSize, tileSize);

        // initialize the fruit
        fruit = new Fruit(tileSize, maxScreenCol, maxScreenRow);

    } // end of GamePanel constructor

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    } // end of startGameThread

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if(delta >= 1) {
                
                update();
                repaint();
                delta--;
            
            } // end of if
        } // end of while loop
    } // end of run

    // method for updating on screen information in real time (60 FPS)
    // reads changes
    public void update() {

        // prevent movement until the player presses a key after reset
        if (waitingForInput) {
        
            if (!keyH.lastInput.equals("NONE")) {
                
                lastDirection = keyH.lastInput;
                waitingForInput = false; // allow movement after input
        
            }
    
            return; // block update until input is received

        }

        long currentTime = System.currentTimeMillis();

        // only move the snake if enough time has passed
        if (currentTime - lastMoveTime < moveDelay) {return;} // exit update() and wait for the next frame

        lastMoveTime = currentTime; // update the time when the movement happens

        // PLAYER POSITION
        // gets the current position of snake's head
        int[] headPos = snake.getHeadPosition();
        // create variables for Snake movement
        int snakeX = headPos[0];
        int snakeY = headPos[1];
        int snakeSpeed = tileSize;

        // wait for player input before starting movement
        if (waitingForInput) {
            
            if (!keyH.lastInput.equals("NONE")) {
                
                lastDirection = keyH.lastInput;
                waitingForInput = false; // allow movement after input
            
            } // end of if
            
            return; // completely block update() until input is received
        
        } // end of if
        
        // prevents reversing direction into itself
        if (keyH.lastInput.equals("UP") && !lastDirection.equals("DOWN")) { lastDirection = "UP"; }
        else if (keyH.lastInput.equals("DOWN") && !lastDirection.equals("UP")) { lastDirection = "DOWN"; }
        else if (keyH.lastInput.equals("LEFT") && !lastDirection.equals("RIGHT")) { lastDirection = "LEFT"; }
        else if (keyH.lastInput.equals("RIGHT") && !lastDirection.equals("LEFT")) { lastDirection = "RIGHT"; }

        // move in the last stored direction
        switch (lastDirection) {

            case "UP": snakeY -= snakeSpeed; break;
            case "DOWN": snakeY += snakeSpeed; break;
            case "LEFT": snakeX -= snakeSpeed; break;
            case "RIGHT": snakeX += snakeSpeed; break;

        } // end of lastDirection switch case

        // move the snake: add a new head at the new position
        snake.addHead(snakeX, snakeY);

        // check for snake collision with wall or itself
        // and prevents movement if the next position is out of bounds 
        if (snakeX < 0 || snakeX + tileSize > screenWidth || 
            snakeY < 0 || snakeY + tileSize > screenHeight || 
            snake.checkCollision(screenWidth, screenHeight)) {

            System.out.println("Collision detected!");
            stopGame(); // call stopGame
            return; // stop further execution

        } // end of if

        // check if snake head is at the same position as a fruit
        int[] fruitPos = fruit.getFruitPosition();
        if (snakeX == fruitPos[0] && snakeY == fruitPos[1]) {

            System.out.println("Fruit eaten! Growing snake...");
            fruit.randomizePosition(maxScreenCol, maxScreenRow);
            snake.growSnake(snakeX, snakeY); // add a new segment to the snake

        } // end of if

        // remove the tail to simulate movement (unless growing)
        snake.removeTail();
    
    } // end of update

    // other method for updating on screen information in real time (60 FPS)
    // visualizes changes
    public void paintComponent(Graphics g) {

        
        super.paintComponent(g); // erase previous frame
        Graphics2D g2 = (Graphics2D)g; // improve 2D graphics

        // draw a grid
        g2.setColor(Color.DARK_GRAY); // grid color
        for (int x = 0; x < screenWidth; x += tileSize) {

            for (int y = 0; y < screenHeight; y += tileSize) {

                g2.drawRect(x, y, tileSize, tileSize); // draw each grid cell

            } // end of inner for loop

        } // end of for loop

        g2.setColor(Color.green); // sets component color (snake)
        
        // defines head position
        Snake.Node currentNode = snake.getHead();
        // traverse the linked list and draw each segment
        while (currentNode != null) {
        
            g2.fillRect(currentNode.x, currentNode.y, tileSize, tileSize); // draws segment
            currentNode = currentNode.next; // move to the next node
        
        }

        fruit.paint(g2);
        
        g2.dispose();

    } // end of paintComponent

    // getter method for lastDirection
    public String getLastDirection() {return lastDirection;}

    public void resetGame() {

        // fully reset all movement variables
        lastDirection = "NONE";
        keyH.lastInput = "NONE";
        lastMoveTime = System.currentTimeMillis();

        // reset the snake with a clean state
        snake = new Snake(5 * tileSize, 5 * tileSize, tileSize);

        // prevent update() from running immediately after reset
        waitingForInput = true;

        if (gameThread == null) {startGameThread();} // restarts game thread

    } // end of resetGame

    public void stopGame() {

        System.out.println("Game Over!");

        gameThread = null; // stop game loop

        // reset snake after a short delay
        new java.util.Timer().schedule(

            new java.util.TimerTask() {
                
                @Override
                public void run() {resetGame();}
            
            }, // end of timer task

            2000 // 2 second delay before restarting

        ); // end of scheduled timer
    } // end of stopGame
} // end of GamePanel class
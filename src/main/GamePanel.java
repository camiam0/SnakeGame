//Name: Cameron Bartlett
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

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    private Snake snake; // define snake

    private String lastDirection = "RIGHT"; // default starting direction

    public GamePanel() {

        // initialize game window
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        // initialize the snake
        snake = new Snake(5 * tileSize, 5 * tileSize);

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

        // PLAYER POSITION
        // gets the current position of snake's head
        int[] headPos = snake.getHeadPosition();
        // create variables for Snake movement
        int snakeX = headPos[0];
        int snakeY = headPos[1];
        int snakeSpeed = 4;

        if(keyH.upPressed == true) {
            
            snakeY -= snakeSpeed;
            lastDirection = "UP";
        
        } else if(keyH.downPressed == true) {
            
            snakeY += snakeSpeed;
            lastDirection = "DOWN";
        
        } else if(keyH.leftPressed == true) {
            
            snakeX -= snakeSpeed;
            lastDirection = "LEFT";
        
        } else if(keyH.rightPressed == true) {
            
            snakeX += snakeSpeed;
            lastDirection = "RIGHT";
        
        } else {

            // if no key is pressed, continue in the last direction
            switch (lastDirection) {

                case "UP": snakeY -= snakeSpeed; break;
                case "DOWN": snakeY += snakeSpeed; break;
                case "LEFT": snakeX -= snakeSpeed; break;
                case "RIGHT": snakeX += snakeSpeed; break;

            } // end of lastDirection switch case

        } // end of if/else

        // move the snake: add a new head at the new position
        snake.addHead(snakeX, snakeY);

        // check for snake collision with wall or itself (or fruit eventually)
        // and prevents movement if the next position is out of bounds 
        if (snakeX < 0 || snakeX + tileSize > screenWidth || snakeY < 0 || snakeY + tileSize > screenHeight || snake.checkCollision(screenWidth, screenHeight)) {

            System.out.println("Collision detected!");
            stopGame(); // call stopGame
            return; // stop further execution

        } // end of if

        // remove the tail to simulate movement (unless growing)
        snake.removeTail(); // NEED TO ADD IF STATEMENT FOR FRUIT
    
    } // end of update

    // other method for updating on screen information in real time (60 FPS)
    // visualizes changes
    public void paintComponent(Graphics g) {

        
        super.paintComponent(g); // erase previous frame
        Graphics2D g2 = (Graphics2D)g; // improve 2D graphics

        g2.setColor(Color.green); // sets component color (snake)
        
        // defines head position
        Snake.Node currentNode = snake.getHead();
        // traverse the linked list and draw each segment
        while (currentNode != null) {
        
            g2.fillRect(currentNode.x, currentNode.y, tileSize, tileSize); // draws segment
            currentNode = currentNode.next; // move to the next node
        
        }
        
        g2.dispose();

    } // end of paintComponent

    public void resetGame() {

        snake.reset(5 * tileSize, 5 * tileSize); // reset snake
        lastDirection = "RIGHT"; // reset starting direction
        startGameThread(); // restart the game

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
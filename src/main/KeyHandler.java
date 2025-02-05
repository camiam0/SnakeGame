//Names: Cameron Bartlett, Yosyp Vasyliev, John Kerstetter
//Date: 02/04/2025
//Lab 2&3 - Objects - Snake Game
//Description: Class to handle keyboard inputs from user

package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// this class is responsible for collecting keyboard inputs and translating them to actions in game 
// ie player movement, combat ect
public class KeyHandler implements KeyListener{

    private GamePanel gamePanel; // reference to GamePanel
    public String lastInput = "RIGHT"; // default direction

    // KEYBOARD INPUTS
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    // constructor to accept GamePanel reference
    public KeyHandler(GamePanel gamePanel) {this.gamePanel = gamePanel;}

    // unused method required for KeyHandler
    @Override
    public void keyTyped(KeyEvent e) {}

    // method for moving player
    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if(gamePanel.getLastDirection().equals("NONE") || !gamePanel.getLastDirection().equals("NONE")) { // game hasn't started
        
            if(code == KeyEvent.VK_W) {lastInput = "UP";}

            if(code == KeyEvent.VK_S) {lastInput = "DOWN";}

            if(code == KeyEvent.VK_A) {lastInput = "LEFT";}

            if(code == KeyEvent.VK_D) {lastInput = "RIGHT";}
        
        } // end of if
    
    } // end of keyPressed

    // method for moving player
    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {upPressed = false;}

        if(code == KeyEvent.VK_S) {downPressed = false;}

        if(code == KeyEvent.VK_A) {leftPressed = false;}

        if(code == KeyEvent.VK_D) {rightPressed = false;}

    } // end of keyReleased
} // end of KeyHandler class
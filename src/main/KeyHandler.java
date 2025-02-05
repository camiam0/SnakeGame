//Name: Cameron Bartlett
//Date: 02/04/2025
//Lab 2&3 - Objects - Snake Game
//Description: Class to handle keyboard inputs from user

package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// this class is responsible for collecting keyboard inputs and translating them to actions in game 
// ie player movement, combat ect
public class KeyHandler implements KeyListener{

    // KEYBOARD INPUTS
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    // unused method required for KeyHandler
    @Override
    public void keyTyped(KeyEvent e) {}

    // method for moving player
    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {upPressed = true;}

        if(code == KeyEvent.VK_S) {downPressed = true;}

        if(code == KeyEvent.VK_A) {leftPressed = true;}

        if(code == KeyEvent.VK_D) {rightPressed = true;}
    
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
//Names: Cameron Bartlett, Yosyp Vasyliev, John Kerstetter
//Date: 02/04/2025
//Lab 2&3 - Objects - Snake Game
//Description: Main program

package main;

import javax.swing.JFrame;

// class for running the game
public class Main {

    public static void main (String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Snake Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();

    } // end of main
} // end of Main class
//Names: Cameron Bartlett, Yosyp Vasyliev, John Kerstetter
//Date: 02/04/2025
//Lab 2&3 - Objects - Snake Game
//Description: Fruit Class for Snake Game

package main;

import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;

public class Fruit {
    
    // position
    private int fruitX;
    private int fruitY;
    private Random rand;
    private int tileSize;

    // constructor
    public Fruit(int tileSize, int gridWidth, int gridHeight) {
        
        this.tileSize = tileSize;
        rand = new Random();
        randomizePosition(gridWidth, gridHeight);
    
    } // end of constructor

    // move the fruit to a random position on the board
    public void randomizePosition(int gridWidth, int gridHeight) {
        
        fruitX = rand.nextInt(gridWidth) * tileSize;
        fruitY = rand.nextInt(gridHeight) * tileSize;
    
    } // end of randomizePosition

    // draw the fruit
    public void paint(Graphics g) {
        
        g.setColor(Color.RED);
        g.fillRect(fruitX, fruitY, tileSize, tileSize);
    
    } // end of paint

    // getter method for fruit position
    public int[] getFruitPosition() {return new int[] {fruitX, fruitY};}
} // end of Fruit Class
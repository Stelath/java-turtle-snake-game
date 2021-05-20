
/**
 * Write a description of class Snake here.
 *
 * @author Alex K
 * @version 5/17/2021
 */

import java.util.*;

public class Snake
{
    // Instance Variables
    private int[][] game;
    private int size, initialLength, direction, lastDirection, score;
    private int headX, headY;
    private ArrayList<Integer> snakeXCoords, snakeYCoords;
    
    /**
     * Creates the game int array with the desired size
     */
    public void createGameWithSize(int size, int length)
    {
        if(size % 8 != 0)
        {
            while(size % 8 != 0)
                size++;
        }
        
        game = new int[size][size];
        game[size / 2][(size / 2) - 2] = 1;
        game[size / 2][(size / 2) - 3] = 1;
        game[size / 2][(size / 2) - 4] = 1;
        game[size / 2][(size / 2) + 2] = 2;
        
        snakeXCoords = new ArrayList<Integer>();
        snakeYCoords = new ArrayList<Integer>();
        
        snakeXCoords.add((size / 2) - 4);
        snakeXCoords.add((size / 2) - 3);
        snakeXCoords.add((size / 2) - 2);
        snakeYCoords.add(size / 2);
        snakeYCoords.add(size / 2);
        snakeYCoords.add(size / 2);
        headX = (size / 2) - 2;
        headY = size / 2;
        
        direction = 1;
        lastDirection = 1;
        score = 0;
    }
    
    /**
     * Constructor for objects of class Snake
     * Creates the game with a default size
     */
    public Snake()
    {
        createGameWithSize(16, 3);
        
        size = 16;
        initialLength = 3;
    }
    
    /**
     * Create the game with a custom size
     * Has to be a multiple of 8 or else will initialize with next multiple
     */
    public Snake(int s)
    {
        createGameWithSize(s, 3);
        
        size = s;
        initialLength = 3;
    }
    
    /**
     * Create the game with a custom size and length
     * Has to be a multiple of 8 or else will initialize with next multiple
     */
    public Snake(int s, int l)
    {
        createGameWithSize(s, l);
        
        size = s;
        initialLength = l;
    }

    /**
     * Moves the snake forward
     * @return the resulting scene or null if the game is over
     */
    public int[][] move()
    {
        if(game != null)
        {
            if(direction == 0 && (headY != 0))
                headY--;
            else if(direction == 1 && (headX != game.length - 1))
                headX++;
            else if(direction == 2 && (headY != game.length - 1))
                headY++;
            else if(direction == 3 && (headX != 0))
                headX--;
            else
            {
                game = null;
                return game;
            }
                
            switch(game[headY][headX])
            {
                case 0:
                    game[headY][headX] = 1;
                    game[snakeYCoords.remove(0)][snakeXCoords.remove(0)] = 0;
                    snakeYCoords.add(headY);
                    snakeXCoords.add(headX);
                    break;
                case 1:
                    game = null;
                    break;
                case 2:
                    score++;
                    game[headY][headX] = 1;
                    snakeYCoords.add(headY);
                    snakeXCoords.add(headX);
                    
                    boolean newApple = false;
                    while(!newApple)
                    {
                        int appleX = (int)(Math.random() * game.length);
                        int appleY = (int)(Math.random() * game.length);
                        if(game[appleY][appleX] == 0)
                        {
                            game[appleY][appleX] = 2;
                            newApple = true;
                        }
                    }
                    break;
            }
        }
        lastDirection = direction;
        return game;
    }
    
    /**
     * Turns the snake in the requested direction if possible
     */
    public void turn(int d)
    {
        if(d != (lastDirection - 2) && !(d == 3 && lastDirection == 1))
            direction = d;
    }
    
    /**
     * Resets the game
     */
    public void reset()
    {
        createGameWithSize(size, initialLength);
    }
    
    /**
     * {@return the current scene}
     */
    public int[][] getScene()
    {
        return game;
    }
    
    /**
     * {@return the current score}
     */
    public int getScore()
    {
        return score;
    }
}

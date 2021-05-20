
/**
 * Write a description of class Project here.
 *
 * @author Alex K
 * @version 5/11/2021
 */

import galapagos.*;
import java.util.*;

public class SnakeGame
{
    private static Snake snake;
    private static boolean playing, reset;
    
    public static void keyPressed(int c)
    {
        switch(c)
        {
            case 38:
            case 87:
                snake.turn(0);
                break;
            case 39:
            case 68:
                snake.turn(1);
                break;
            case 40:
            case 83:
                snake.turn(2);
                break;
            case 37:
            case 65:
                snake.turn(3);
                break;
            case 82:
                reset = true;
        }
        
        playing = true;
    }
    
    public static void main(String[] args)
    {
        int canvasSizePX = 512, canvasSizeU = 16; // canvasSizePX has to be a multiple of canvasSizeU
        double speed = 0.20; // How long it takes for the snake to move one unit in seconds
        playing = false; // Kepeps track of if the person is currently playing
        boolean endScreen = false;
        boolean end = false;
        long lastMoveTime = System.currentTimeMillis();
        
        // Creates a SnakeRenderer object
        SnakeRenderer renderer = new SnakeRenderer(canvasSizePX, canvasSizeU);
        
        // Creates a InputHandler object
        SnakeInputHandler inputHandler = new SnakeInputHandler(renderer.getCanvas());
        
        // Creates a Snake object and renders it
        snake = new Snake();
        renderer.renderScene(snake.getScene());
        
        while(true)
        {
            if(end && reset)
            {
                snake.reset();
                playing = false;
                end = false;
                renderer.renderScene(snake.getScene());
                try
                {
                    Thread.sleep(500);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
            }
            
            reset = false;
            
            // Moves the snake every "speed" seconds
            if(System.currentTimeMillis() - lastMoveTime - (int)(speed * 1000) > 0 && playing)
            {
                // Set the last move time to the current time
                lastMoveTime = System.currentTimeMillis();
                
                // Move the snake and render it
                if(snake.move() != null)
                    renderer.renderScene(snake.getScene());
                else
                {
                    playing = false;
                    endScreen = true;
                    end = true;
                }
            }
            
            if(!playing && endScreen)
            {
                System.out.println("Game Over!");
                System.out.println("Score: " + snake.getScore());
                System.out.println("Press R to restart");
                System.out.println();
                endScreen = false;
            }
            
            try
            {
                Thread.sleep(1);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
    }
}

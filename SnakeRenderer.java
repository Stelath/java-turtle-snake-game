
/**
 * Write a description of class SnakeRender here.
 *
 * @author Alex K
 * @version 5/11/2021
 */

import galapagos.*;
import java.awt.*;

public class SnakeRenderer
{
    private Turtle t;
    private int canvasSizePX, canvasSizeU, canvasSizeScale; // Has to be multiple of 8
    private TurtleDrawingWindow canvas;
    
    /**
     * Constructor for objects of class SnakeRenderer
     */
    public SnakeRenderer(int csPX, int csU)
    {
        this.t = t;
        
        canvasSizePX = csPX;
        canvasSizeU = csU;
        
        canvasSizeScale = (canvasSizePX / canvasSizeU);
        
        // Create a Playground for the Turtles
        canvas = new TurtleDrawingWindow();
        canvas.clear();
        canvas.setGrid(false);
        canvas.setUnit(canvasSizeScale / 2);
        canvas.setSize(canvasSizePX + (canvasSizeScale / 2), canvasSizePX + canvasSizeScale);
        canvas.setVisible(true);
        canvas.setOrigin(0, 0);
        
        // Creates and adds Turtle objects to playground
        t = new Turtle(Turtle.NO_DEFAULT_WINDOW);
        canvas.add(t);
        t.hide();
        t.speed(100000);
    }
    
    /**
     * Renders the passed scene to the turtle canvas
     */
    public void renderScene(int[][] scene)
    {
        // Clear the canvas
        canvas.clear();
        
        //Setup turtle
        t.penUp();
        t.penSize(canvasSizePX / canvasSizeU);
        t.heading(0);
        
        for(int r = 0; r < scene.length; r++)
        {
            t.jumpTo(-canvasSizeU + 1, canvasSizeU - (r * 2) - 1);
            for(int c = 0; c < scene[0].length; c++)
            {
                // Start the turtle drawing
                t.penDown();
                
                // Change the color and draw the segment
                if(scene[r][c] == 0)
                    t.penColor(Color.black);
                else if(scene[r][c] == 1)
                    t.penColor(Color.green);
                else if(scene[r][c] == 2)
                    t.penColor(Color.red);
                t.move(2);
                
                // Stop the turtle from drawing
                t.penUp();
            }
        }
    }
    
    /**
     * {@return the turtle canvas}
     */
    public TurtleDrawingWindow getCanvas()
    {
        return canvas;
    }
}

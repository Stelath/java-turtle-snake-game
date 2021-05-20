
/**
 * Write a description of class InputHandler here.
 *
 * @author Alex K
 * @version 5/17/2021
 */

import java.awt.*;
import java.awt.event.*;

public class SnakeInputHandler
{
    /**
     * Creates a new InputHandler that listens for key presses
     * on the passed JFrame object
     */
    public SnakeInputHandler(Frame window)
    {
        window.addKeyListener(new SnakeKeyListener());
    }
}

class SnakeKeyListener implements  KeyListener
{
    @Override
    public void keyPressed(KeyEvent e) 
    {
        SnakeGame.keyPressed(e.getKeyCode());
    }
    
    @Override
    public void keyReleased(KeyEvent e){}
    
    @Override
    public void keyTyped(KeyEvent e){}
}

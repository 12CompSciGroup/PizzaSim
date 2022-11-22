import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class WaittingBar here.
 * 
 * @author Yixin 
 * @version (a version number or a date)
 */
public class WaittingBar extends Actor
{
    private GifImage[] MOOD_GIF_IMAGES = {
        new GifImage ("1happy.gif"),
        new GifImage ("2annoyed.gif"),
        new GifImage ("3frustrated.gif"),
        new GifImage ("4crying.gif"),
        new GifImage ("5mad.gif"),
        new GifImage ("6angry.gif")
    };
    
    private final int WAIT_DELAY = 300;
    
    /**
     * Act - do whatever the WaittingBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
    }
}

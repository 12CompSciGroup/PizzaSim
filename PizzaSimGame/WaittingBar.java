import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class WaittingBar here.
 * 
 * @author Yixin Cai 
 * @version (a version number or a date)
 */
public class WaittingBar extends Actor
{
    private GifImage[] moodBarGif = {
        new GifImage ("mood0.gif"),
        new GifImage ("mood1.gif"),
        new GifImage ("mood2.gif"),
        new GifImage ("mood3.gif"),
        new GifImage ("mood4.gif"),
        new GifImage ("mood5.gif")
    };
    
    private final int WAIT_DELAY = 420;
    private final int ROUND_COUNT = WAIT_DELAY / moodBarGif.length;
    
    private int tik;
    
    public WaittingBar() {
        tik = 0;
    }
    
    /**
     * Act - do whatever the WaittingBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setImage(moodBarGif[tik / ROUND_COUNT].getCurrentImage());
        tik++;
        
        if (tik > 420) {
            getWorld().removeObject(this);
        }
    }
}

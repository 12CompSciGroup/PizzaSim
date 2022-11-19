import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartWorld here.
 * 
 * @author Yixin Cai
 * @version (a version number or a date)
 */
public class StartWorld extends World
{
    // initialize variables
    private Label startLabel;
    
    /**
     * Constructor for objects of class StartWorld.
     */
    public StartWorld()
    {   
        // Create a new world with 1024x800 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1);
        startLabel = new Label("Press Enter to Start", 75, Color.BLACK);
        
        GreenfootImage image = new GreenfootImage ("StartScreen.png");
        
        setBackground(image);
        addObject(startLabel, getWidth() / 2, getHeight() - 50);
    }
    
    public void act() {
       if (Greenfoot.isKeyDown("Enter")) {
           Greenfoot.setWorld(new SettingWorld());
       }
    }
    
    public void started() {
        BackgroundSound.getInstance().playSound();
    }
    
    public void stopped() {
        BackgroundSound.getInstance().pauseSound();
    }
}

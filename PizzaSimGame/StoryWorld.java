import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StoryWorld here.
 * 
 * @author Gloria Chan & Yixin Cai
 * @version (a version number or a date)
 */
public class StoryWorld extends World
{
    private int clickIndex;
    private FlashingText nextPageText;
    private static GreenfootImage[] storyPanels;
    
    /**
     * Constructor for objects of class storyWorld.
     * 
     */
    public StoryWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        clickIndex = 1;
        nextPageText = new FlashingText(new GreenfootImage("toNextPageText.png"));
        storyPanels = new GreenfootImage[] {
            new GreenfootImage("JordanRamseyPanel1 copy.png"),
            new GreenfootImage("JordanRamseyPanel2 copy.png"),
            new GreenfootImage("StoryPanel1.png"), 
            new GreenfootImage("StoryPanel2.png"), 
            new GreenfootImage("StoryPanel3.png"), 
            new GreenfootImage("StoryPanel4.png"),
        };
        setBackground(storyPanels[0]);
        addObject(nextPageText, 500, 700);
    }
    
    public void act() {
        if (checkClicked() && clickIndex < 6) {
            setBackground(storyPanels[clickIndex]);
            clickIndex++;
        }
        else if(clickIndex == 6 && checkClicked()) {
            Greenfoot.setWorld(new SettingWorld());
        }
    }
    
    private boolean checkClicked() {
        return Greenfoot.mouseClicked(this) || Greenfoot.mouseClicked(nextPageText);
    }
    
    public void started() {
        Utils.backgroundSound.playLoop();
    }
    
    public void stopped() {
        Utils.backgroundSound.pause();
    }
}

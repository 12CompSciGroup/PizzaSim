import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnterPrompt here.
 * 
 * @author Gloria Chan
 * @version (a version number or a date)
 */
public class FlashingText extends Effects
{
    private int time = 0;
    private GreenfootImage pressEnterText;
    private boolean blink = true;
    
    public FlashingText(GreenfootImage pressEnterText)
    {
        this.pressEnterText = pressEnterText;
        setImage(pressEnterText);
    }
    
    /**
     * Act - do whatever the EnterPrompt wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(time == 0 || time == 250)
        blink = !blink;
            
        if(blink)
        time -= 2;
            
        else
        time += 2;
        
        if (time % 5 == 0)
        {
            getImage().setTransparency(time);
        }
    }
}

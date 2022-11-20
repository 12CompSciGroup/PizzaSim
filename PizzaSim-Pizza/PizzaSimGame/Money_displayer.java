import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Money_displayer here.
 * 
 * @author Yuxin Li(your name) 
 * @version (a version number or a date)
 */
public class Money_displayer extends Actor
{
    /**
     * Act - do whatever the Money_displayer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int money;
    private static final Color transparent = new Color(0,0,0,0);
    public Money_displayer(int value){
        money=value;
    }
    public void addedToWorld(World w){
        setDisplayer(0);
    }
    public void setDisplayer(int newValue)
    {
        money = newValue;
        updateImage();
    }
    
    public void updateImage()
    {
        //display number for displayer
        GreenfootImage displayer = new GreenfootImage("Current Income: $ "+ money, 25, Color.BLACK, transparent);
        setImage(displayer);
    }
    
    public int getMoney(){
        return money;
    }
}

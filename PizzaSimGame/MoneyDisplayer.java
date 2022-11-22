import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Money displayer is a Greenfoot Actor used to display and update the current money of each kitchen 
 * 
 * @author Yuxin Li
 * @version November 2022
 */
public class MoneyDisplayer extends Actor
{
    
    private int money;
    private static final Color transparent = new Color(0,0,0,0);
    /**
     * Store the initial money of the kitchen 
     * @param value Initial money of the kitchen
     */
    public MoneyDisplayer(int value){
        money = value;
    }
    /**
     * when the money displayer is added to world, show the initail money
     */
    public void addedToWorld(World w){
        setDisplayer(money);
    }
    /**
     * set the new value of displayer and update the displayer
     * @param newValue new value of money 
     */
    public void setDisplayer(int newValue)
    {
        money = newValue;
        updateImage();
    }
    
    /**
     * Change the money value and rewrite it on the screen 
     */
    public void updateImage()
    {
        //display number for displayer
        GreenfootImage displayer = new GreenfootImage("Current Income: $ "+ money, 20, Color.BLACK, transparent);
        setImage(displayer);
    }
    
    /**
     * return the current money of restaurant
     */
    public int getMoney(){
        return money;
    }
}


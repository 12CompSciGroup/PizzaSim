import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ingredients here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ingredient extends Actor
{
    private double exactX;
    private double exactY;
    private GreenfootImage image;
    
    public Ingredient(String ingredient) {
        image = new GreenfootImage(ingredient + ".png");
        setImage(image);
    }
    
    /**
     * Act - do whatever the Ingredients wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    /**
     * Set the location using exact coordinates.
     * 
     * @param x the new x location
     * @param y the new y location
     */
    public void setLocation(double x, double y) 
    {
        exactX = x;
        exactY = y;
        super.setLocation((int) (x + 0.5), (int) (y + 0.5));
    }
    
    /**
     * Set the location using integer coordinates.
     * (Overrides the method in Actor.)    
     * 
     * @param x the new x location
     * @param y the new y location
     */
    @Override
    public void setLocation(int x, int y) 
    {
        exactX = x;
        exactY = y;
        super.setLocation(x, y);
    }
}

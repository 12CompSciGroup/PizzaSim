import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class People here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class People extends Actor
{
    private double exactX;
    private double exactY;
    private double rotation;
    private boolean staticRotation = false;
    /**
     * Act - do whatever the People wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    protected static final int UP = 0, DOWN = 180, LEFT = 270, RIGHT = 90;
    
    protected static int numOfCustomers = 0;
    
    protected static boolean cash1IsFree = true;
    protected static boolean cash2IsFree = true;
    
    protected static boolean wait1IsFree = true;
    protected static boolean wait2IsFree = true;
    protected static boolean wait3IsFree = true;
    
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

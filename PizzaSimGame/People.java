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
<<<<<<< HEAD
<<<<<<< HEAD
=======
    private int imageIndex = 0;
    
    private SimpleTimer animTimer = new SimpleTimer();
>>>>>>> parent of 49bbf5a (interactAnim)
=======
    private int imageIndex = 0;
    
    private SimpleTimer animTimer = new SimpleTimer();
>>>>>>> parent of df43296 (Finished merge with main/started code abstraction/cleanup)
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
    
<<<<<<< HEAD
=======
    public void animate(GreenfootImage[] up, GreenfootImage[] down, GreenfootImage[] left, GreenfootImage[] right, int rotation){
        rotation = correctNegRotation(rotation);
        
        if(imageIndex == 9){
            imageIndex = 0;
        }
        
        if (animTimer.millisElapsed() > 80){
            switch (rotation){
                case UP:
                    setImage(up[imageIndex]);
                    break;
                case DOWN:
                    setImage(down[imageIndex]);
                    break;
                case LEFT:
                    setImage(left[imageIndex]);
                    break;
                case RIGHT:
                    setImage(right[imageIndex]);
                    break;
            }
            imageIndex++;
            animTimer.mark();
        }
    }
    
    public void standStill(GreenfootImage up, GreenfootImage down, GreenfootImage left, GreenfootImage right, int rotation){
        rotation = correctNegRotation(rotation);
        
        switch (rotation){
            case UP:
                setImage(up);
                break;
            case DOWN:
                setImage(down);
                break;
            case LEFT:
                setImage(left);
                break;
            case RIGHT:
                setImage(right);
                break;
        }
        
    }
    
    public int correctNegRotation(int rotation){
        if (rotation < 0){
            if (rotation == -270){
                rotation = RIGHT;
            } else if (rotation == -180){
                rotation = DOWN;
            } else if (rotation == -90){
                rotation = LEFT;
            }
        }
        return rotation;
    }
    
    public void addCustomer1(){
        numberOfCustomers1++;
    }
    
    public void addCustomer2(){
        numberOfCustomers2++;
    }
    
    public void removeCustomer1(){
        numberOfCustomers1--;
    }
    
    public void removeCustomer2(){
        numberOfCustomers2--;
    }
    
<<<<<<< HEAD
>>>>>>> parent of 49bbf5a (interactAnim)
=======
>>>>>>> parent of df43296 (Finished merge with main/started code abstraction/cleanup)
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

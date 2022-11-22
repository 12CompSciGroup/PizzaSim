import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class People here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class People extends Actor
{
    private double exactX;
    private double exactY;
    private double rotation;
    private boolean staticRotation = false, checkedOvenLocation;
    private int imageIndex = 0;
    private int interactIndex = 0;
    
    private SimpleTimer animTimer = new SimpleTimer();
    private SimpleTimer timer = new SimpleTimer();
    protected int interactCounter = 0;
    private Pizza assignedPizza;
    
    /**
     * Act - do whatever the People wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public static final int UP = 0, DOWN = 180, LEFT = 270, RIGHT = 90;
    
    protected static int numberOfCustomers1 = 0, numberOfCustomers2 = 0;
    
    public void act()
    {
    
    }
    
    public void animate(GreenfootImage[] up, GreenfootImage[] down, GreenfootImage[] left, GreenfootImage[] right, int rotation){
        rotation = correctNegRotation(rotation);
        
        if(imageIndex >= up.length){
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
    
    public void assignPizza(Pizza pizza)
    {
        assignedPizza = pizza;
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
    
    public void interact(GreenfootImage[] a, GreenfootImage[] b, int rotation){
        rotation = correctNegRotation(rotation);
        
        if(interactIndex == a.length){
            interactIndex = 0;
        }
        
        if(interactCounter >= 0){
            if (timer.millisElapsed() > 100){
                switch (rotation){
                    case UP:
                        setImage(a[interactIndex]);
                        break;
                    case DOWN:
                        setImage(b[interactIndex]);
                        break;
                    case LEFT:
                        setImage(b[interactIndex]);
                        break;
                    case RIGHT:
                        setImage(a[interactIndex]);
                }
                interactIndex++;
                interactCounter--;
                timer.mark();
            }
        }
    }
    
    public void interact(GreenfootImage[] up){
        if(interactIndex == up.length){
            interactIndex  = 0;
        }
        
        if(interactCounter >= 0){
            if (timer.millisElapsed() > 100){
                setImage(up[interactIndex]);
                interactIndex++;
                interactCounter--;
                timer.mark();
            }
        }
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
    
    public Pizza getPizza()
    {
        return assignedPizza;
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

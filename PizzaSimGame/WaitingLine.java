import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WaitingLine here.
 * 
 * @author (Andy) 
 * @version (a version number or a date)
 */
public class WaitingLine extends Target
{
    private boolean isEmpty, isReserved = false;
    
    /**
     * Act - do whatever the WaitingLine wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public boolean checkIfEmpty()
    {
        if(getOneObjectAtOffset(0, 0, Customer.class) != null)
        {
            isEmpty = false;
        } else
        {
            isEmpty = true;
        }
        return isEmpty;
    }
    
    public void reserve(boolean reservation)
    {
        isReserved = reservation;
    }
    
    public boolean isReserved()
    {
        return isReserved;
    }
}

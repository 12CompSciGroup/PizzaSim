import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Oven here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Oven extends Target
{
    private boolean isEmpty, isReserved = false, isPickUpReserved = false;
    
    public Oven()
    {
        getImage().scale(50, 50);
        getImage().setTransparency(255);
    }
    
    public boolean checkIfEmpty()
    {
        if(getOneObjectAtOffset(0, 0, Pizza.class) == null && !isTouching(OvenCover.class))
        {
            isEmpty = true;
        }
        else
        {
            isEmpty = false;
        }
        return isEmpty;
    }
    
    public boolean canPickUp()
    {
        if(!checkIfEmpty() && !isTouching(OvenCover.class))
        {
            Pizza pizza = (Pizza) getOneObjectAtOffset(0, 0, Pizza.class);
            if(pizza.isCooked())
            {
                return true;
            }
            return false;
        }
        return false;
    }
    
    public void pickUpReserve(boolean reservation)
    {
        isPickUpReserved = reservation;
    }
    
    public void reserve(boolean reservation)
    {
        isReserved = reservation;
    }
    
    public boolean isReserved()
    {
        return isReserved;
    }
    
    public boolean isPickUpReserved()
    {
        return isPickUpReserved;
    }
}

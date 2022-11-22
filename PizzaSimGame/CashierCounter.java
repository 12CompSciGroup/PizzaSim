import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class CashierCounter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CashierCounter extends Target
{
    private boolean isEmpty, isReserved = false;
    
    /**
     * Act - do whatever the CashierCounter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public void act()
    {
    }
    
    //reservation system for cashiers, same code as waiting line and overlaps with anson's oven class, abstracting is v possible
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

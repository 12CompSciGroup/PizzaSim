import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class KitchenCounter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class KitchenCounter extends Target
{
    private boolean canMakePizza;
    private int pizzaria;
    
    public KitchenCounter(int pizzaria)
    {
        getImage().scale(25, 25);
        getImage().setTransparency(0);
        this.pizzaria = pizzaria;
    }
    /**
     * Act - do whatever the KitchenCounter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public boolean checkCanMakePizza()
    {
        if(isTouching(Pizza.class))
        {
            canMakePizza = false;
        }
        else if(!checkCounterChef())
        {
            canMakePizza = false;
        }
        else
        {
            canMakePizza = true;
        }
        return canMakePizza;
    }
    /*
    /**
     * check if there is a chef around counter
     */
    public boolean checkCounterChef(){
        if(!getWorld().getObjectsAt(getX() - (50 * pizzaria), getY(), null).isEmpty()){
            return true;
        }
        else
        {
            return false;
        }
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MoneyInterface here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MoneyInterface extends Actor
{
    private Utils utils;
    
    public MoneyInterface(Utils utils)
    {
        this.utils = utils;
    }
    
    public void changeMoney(int store, int money)
    {
        if(store == -1)
        {
            utils.resturantMoneyOneAdd(money);
            MoneyDisplayer money_displayer=(MoneyDisplayer)getWorld().getObjectsAt(300, 30, MoneyDisplayer.class).get(0);
            money_displayer.setDisplayer(utils.getResturantMoneyOne());
        }
        if(store == 1)
        {
            utils.resturantMoneyTwoAdd(money);
            MoneyDisplayer money_displayer=(MoneyDisplayer)getWorld().getObjectsAt(724, 30, MoneyDisplayer.class).get(0);
            money_displayer.setDisplayer(utils.getResturantMoneyTwo());
        }
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class used for editing and containing global static variables such as money of resturants and speed of characters.
 * 
 * @author Anson Ho
 * @version V1.0
 */
public class Utils extends Actor
{
    public static int resturantMoneyOne = 0;
    public static int resturantMoneyTwo = 0;
    public static int resturantLevelOne = 1;
    public static int resturantLevelTwo = 1;
    
    public static int volume = 60;
    public static final GreenfootSound backgroundSound = new GreenfootSound("Run-Amok.wav");
    
    public static final int moveSpeed = 1;
  
    public static final int enterY = 710;
    public static final int exitY = 760;
    public static final int door1X = 449, door2X = 573;
    
    public static final int cashier1X = 333, cashier2X = 410;
    public static final int chefX = 120, chef1Y = 300, chef2Y = 400;
    
    public static final int wait1X = 160, wait2X = 222, wait3X = 280;
    
    public static final int kitchenCounterX = 70, kitchenCounterY1 = 300, kitchenCounterY2 = 400;
    public static final int counterY = 610;
    
    public static final int oven1X = 160, oven2X = 222, oven3X = 280, ovenY = 190;
    public static final int pizzaFinalY = 550;
    
    public void resturantMoneyOneAdd(int money)
    {
        resturantMoneyOne += money;
    }
    
    public void resturantMoneyTwoAdd(int money)
    {
        resturantMoneyTwo += money;
    }
    
    public int getResturantMoneyOne()
    {
        return resturantMoneyOne;
    }
    
    public int getResturantMoneyTwo()
    {
        return resturantMoneyTwo;
    }
}

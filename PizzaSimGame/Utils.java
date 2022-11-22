import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class used for editing and containing global static variables such as money of resturants and speed of characters.
 * 
 * @author Anson Ho
 * @version V1.0
 */
public class Utils extends Actor
{
    public Utils()
    {
        getImage().setTransparency(0);
    }
    
    private int resturantMoneyOne = 0;
    private int resturantMoneyTwo = 0;
    private int resturantLevelOne = 1;
    private int resturantLevelTwo = 1;
    public static final int moveSpeed = 1;
    
    public static int volume = 60;
    public static final GreenfootSound backgroundSound = new GreenfootSound("Run-Amok.wav");
    
    public static final int enterY = 710;
    public static final int exitY = 760;
    public static final int door1X = 449, door2X = 573;
    
    public static final int cashier1X = 333, cashier2X = 410, cashier3X = 614, cashier4X = 691, cashierY = 460;
    public static final int chefXLeft = 120, chefXRight = 904, chef1Y = 250, chef2Y = 325, chef3Y = 400;
    
    public static final int wait1X = 160, wait2X = 222, wait3X = 280, wait4X = 744, wait5X = 802, wait6X = 864;
    
    public static final int kitchenCounterXLeft = 70, kitchenCounterXRight = 954, kitchenCounterY1 = 250, kitchenCounterY2 = 325, kitchenCounterY3 = 400;
    public static final int counterY = 610;
    
    public static final int oven1X = 160, oven2X = 222, oven3X = 280, oven4X = 864, oven5X = 802, oven6X = 744, ovenY = 190;
    public static final int pizzaFinalY = 550;
    
    public static final int MAMA = -1, PAPA = 1;

    public void resturantMoneyOneAdd(int money)
    {
        resturantMoneyOne += money;
    }
    
    public void resturantMoneyTwoAdd(int money)
    {
        resturantMoneyTwo += money;
    }
    
    public void addResturantLevelOne()
    {
        resturantLevelOne++;
    }

    public void addResturantLevelTwo()
    {
        resturantLevelTwo++;
    }
    
    public int getResturantMoneyOne()
    {
        return resturantMoneyOne;
    }
    
    public int getResturantMoneyTwo()
    {
        return resturantMoneyTwo;
    }
    
    public int getResturantLevelOne()
    {
        return resturantLevelOne;
    }

    public int getResturantLevelTwo()
    {
        return resturantLevelTwo;
    }
}

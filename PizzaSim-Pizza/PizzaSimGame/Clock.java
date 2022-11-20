import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class clock here.
 * 
 * @author (Yuxin Li) 
 * @version (a version number or a date)
 */
public class Clock extends Actor
{
    /**
     * Act - do whatever the clock wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int time;
    private static GreenfootImage[] clocks={
        new GreenfootImage("clock_1.png"), 
        new GreenfootImage("clock_2.png"), 
        new GreenfootImage("clock_3.png"), 
        new GreenfootImage("clock_4.png"), 
        new GreenfootImage("clock_5.png"), 
        new GreenfootImage("clock_6.png"), 
        new GreenfootImage("clock_7.png"), 
        new GreenfootImage("clock_8.png"), 
        new GreenfootImage("clock_9.png"), 
        new GreenfootImage("clock_10.png"), 
        new GreenfootImage("clock_11.png"), 
        new GreenfootImage("clock_12.png"), 
    };
    private int interval;
    private Pizza pizza;
    private int imageIndex;
    private int timeIndex;
    private int burnTime;
    private int burnAct;
    private int x, y;
    public Clock(int time, Pizza pizza){
        this.time=time;
        imageIndex=0;
        timeIndex=time/12;
        burnTime=450;
        burnAct=0;
        this.pizza=pizza;
    }
    /**
     * countdown act
     */
    public void act()
    {
        timeIndex--;
        if(imageIndex==11){
            x=this.getX();
            y=this.getY();
            setImage(clocks[imageIndex]);
            imageIndex++;
            timeIndex=time/12;
            pizza.cookPizza();
        }
        else if(imageIndex==12){
            burnTime--;
            clock_Alarm();
            if(burnTime==0){
                pizza.burnPizza();
            }
        }
        else if(imageIndex<11&&timeIndex==0){
            setImage(clocks[imageIndex]);
            imageIndex++;
            timeIndex=time/12;
        }
        removeClock();
    }
    /**
     * When the countdown is finished, the clock will shake and alarm 
     */
    public void clock_Alarm(){
        burnAct++;
        if(burnAct==1){
            setLocation(x-5, y);
        }
        if(burnAct==2){
            setLocation(x, y);
        }
        if(burnAct==3){
            setLocation(x+5, y);
            burnAct=0;
        }
    }
    // when the pizza is picked up by the cashier
    // remove clock
    public void removeClock(){
        if(this.pizza.isInOven()==false){
            getWorld().removeObject(this);
        }
    }
}

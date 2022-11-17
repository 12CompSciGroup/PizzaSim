import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Robber here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Robber extends People
{
    private int resturant;
    private int location;
    /**
     * Act - do whatever the Robber wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Robber(int resturant)
    {
        this.resturant = resturant;

    }

    public void act()
    {
        // Add your action code here.
        moveToDoorAndMoney();

        stealMoney();
    }

    public void moveToDoorAndMoney()
    {
        checkLocation();
        if(location ==1)
        {
            move1();
        }
        else if(location ==2)
        {
            move2();
        }
        if(location ==3)
        {
            move3();
        }
    }

    public void stealMoney()
    {
        if(resturant ==1)
        {

            if(getX()==400&&getY()==630)
            {
                Utils.resturantMoneyOne--;
                getWorld().removeObject(this);
            }
        }
        if(resturant ==2)
        {

            if(getX()==610&&getY()==630)
            {
                Utils.resturantMoneyTwo--;
                getWorld().removeObject(this);
            }
        }
    }
    public void checkLocation()
    {
        if(getX()==500&&getY()==100)
        {
            location =1;
        }
        else if(getX() == 500&&getY()==710)
        {
            location = 2;
        }
        else if((getX()==400||getX()==610)&&getY() ==710)
        {
            location =3;
        }
        
    }
    public void move1()
    {
        if(getY() != 710)
        {
            setLocation(getX(),getY()+1);
        }
    }
    public void move2()
    {
        if(resturant ==1)
            {
                if(getX() != 610)
                {
                    setLocation(getX()+1,getY());
                }
            }
            else if(resturant == 2)
            {
                if(getX() != 400)
                {
                    setLocation(getX()-1,getY());
                }
            }
    }
    public void move3()
    {
        if(getY() != 630)
            {
             setLocation(getX(),getY()-1);
            }
    }

}

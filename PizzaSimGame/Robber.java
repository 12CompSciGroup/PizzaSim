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
    private boolean stolen = false;
    private boolean position = false;
    private GreenfootImage robberf = new GreenfootImage("robberf.png");
    private GreenfootImage robberb = new GreenfootImage("robberb.png");
    private GreenfootImage robberl = new GreenfootImage("robberl.png");
    private GreenfootImage robberr;
    private GreenfootImage[] robberfw = new GreenfootImage[8];
    private int imageIndex = 0;
    private SimpleTimer timer = new SimpleTimer();
    
    /**
     * Act - do whatever the Robber wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Robber(int resturant)
    {
        this.resturant = resturant;
        setImage(robberf);
        robberr = new GreenfootImage(robberl);
        robberr.mirrorHorizontally();
        for(int i=0;i<robberfw.length;i++)
        {
            robberfw[i] = new GreenfootImage("images/tile00" + i + ".png");
        }
    }

    public void act()
    {

        // Add your action code here.
        if(stolen == false)
        {
            moveToDoorAndMoney();
            stealMoney();
            checkStolen();
        }
        if(stolen == true)
        {
            exit();
            checkPosition();
            if(position == true)
            {
                leave();
            }
        }
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
        else if(location ==3)
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
                
            }
        }
        if(resturant ==2)
        {

            if(getX()==610&&getY()==630)
            {
                Utils.resturantMoneyTwo--;
                
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
            animatef();
        }
    }

    public void move2()
    {
        if(resturant ==1)
        {
            if(getX() != 610)
            {
                setImage(robberr);
                setLocation(getX()+1,getY());
            }
        }
        else if(resturant == 2)
        {
            if(getX() != 400)
            {
                setImage(robberl);
                setLocation(getX()-1,getY());
            }
        }
    }

    public void move3()
    {
        if(getY() != 630)
        {
            setImage(robberb);
            setLocation(getX(),getY()-1);
        }
    }

    public void exit()
    {
        if(resturant == 1)
        {
           
            
                if(getX() != 500)
                {
                    setImage(robberl);
                    setLocation(getX()-1, getY());
                }
            
        }
        if(resturant == 2)
        {
            
            
                if(getX() != 500)
                {
                    setImage(robberr);
                    setLocation(getX()+1,getY());
                }
            
        }

    }

    public void leave()
    {
        if(getY() != 800)
        {
            setImage(robberf);
            setLocation(getX(),getY()+1);
        }
        if(getY() == 800)
        {
            setImage(robberf);
            getWorld().removeObject(this);
        }
    }

    public void checkStolen()
    {
        if((getX() == 400 || getX() == 610)&&getY() == 630)
        {

            stolen = true;
        }

    }
    public void checkPosition()
    {
        if(getX() == 500 && getY() == 630)
        {
            position = true;
        }
        
    }
    public void animatef()
    {
        timer.mark();
        if(timer.millisElapsed()<100)
            {
                return;
            }
        setImage(robberfw[imageIndex]);
        imageIndex = (imageIndex+1) % robberfw.length;
        
        
    }

}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class WaittingBar here.
 * 
 * @author Yixin Cai 
 * @version (a version number or a date)
 */
public class WaittingBar extends Effects
{
    private GifImage[] moodBarGif = new GifImage[6];
    
    private Customer c;
    private int gifIndex;
    private int increment = 4800; //time, changeable
    private int tips = 5;
    
    GreenfootSound angry = new GreenfootSound ("hmp.wav");
    private MoneyInterface moneyInterface;
    
    private boolean playedSound = false;
    
    public WaittingBar(Customer c){
        this.c = c;
        
        for (int i = 0; i < moodBarGif.length; i++){
            moodBarGif[i] = new GifImage("mood" + i + ".gif");
            for (GreenfootImage img : moodBarGif[i].getImages()){
                img.scale((int)(img.getWidth() * 0.55), (int)(img.getHeight() * 0.55)); 
            }
        }
    }
    
    public void act(){
        //changes the gif based on individual customer wait timer
        //also changes how many tips they give
        moneyInterface = getWorld().getObjectsAt(0, 0, MoneyInterface.class).get(0);
        if(c.getInStore() && c.getPickedUp() == false){
            if(c.getWaitTime() < increment){
                gifIndex = 0;
                tips = 5;
            } else if (c.getWaitTime() < (increment * 2)){
                gifIndex = 1;
                tips = 4;
            } else if (c.getWaitTime() < (increment * 3)){
                gifIndex = 2;
                tips = 3;
            } else if (c.getWaitTime() < (increment * 4)){
                gifIndex = 3;
                tips = 2;
            } else if (c.getWaitTime() < (increment * 5)){
                gifIndex = 4;
                tips = 1;
            } else {
                gifIndex = 5;
                tips = 0;
                
                //plays angry sound when super angry
                if (!playedSound){
                    angry.play();
                    playedSound = true;
                }
            }
            
            setImage(moodBarGif[gifIndex].getCurrentImage());
        } else if (c.getPickedUp() == true){
            moneyInterface.changeMoney(c.getStore(), tips);
            
            getWorld().removeObject(this);
        }
        
        //sets location based on customer
        setLocation(c.getX(), c.getY() + c.getImage().getHeight()/2 + 5);
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TryAgainButton here.
 * 
 * @author Yixin Cai
 * @version (a version number or a date)
 */
public class TryAgainButton extends Button
{
    private MyWorld myWorld;
    
    public TryAgainButton() {
        super();
        
        this.image = new GreenfootImage("tryAgain.png");
        this.downImage = new GreenfootImage("tryAgainDOWN.png");
        this.hoverImage = new GreenfootImage("tryAgainHOVER.png");
    }
    
    protected void onClick() {
        //myWorld.removeAllActors();
        //set up a new sim
        Greenfoot.setWorld(new StartWorld());
    }
}

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
    
    public TryAgainButton(MyWorld myWorld) {
        super();
        this.myWorld = myWorld;
        
        this.image = new GreenfootImage("tryAgain1.png");
        this.downImage = new GreenfootImage("tryAgain1.png");
        this.hoverImage = new GreenfootImage("tryAgain1.png");
    }
    
    protected void onClick() {
        myWorld.removeAllActors();
        //set up a new sim
        Greenfoot.setWorld(new StartWorld());
    }
}

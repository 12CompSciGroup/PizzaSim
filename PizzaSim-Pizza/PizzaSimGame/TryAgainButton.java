import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TryAgainButton here.
 * 
 * @author Yixin Cai
 * @version (a version number or a date)
 */
public class TryAgainButton extends Button
{
    public static GreenfootImage tryAgainImage = new GreenfootImage("tryAgain1.png");
    public static GreenfootImage tryAgainDownImage = new GreenfootImage("tryAgain2.png");
    
    private MyWorld myWorld;
    
    public TryAgainButton(MyWorld myWorld) {
        super(tryAgainImage, tryAgainDownImage);
        this.myWorld = myWorld;
    }
    
    /**
     * Act - do whatever the TryAgainButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (myWorld.checkSimOver()) {
            //show the button on the screen
            setImage(image);
            if (Greenfoot.mouseClicked(this)) {
                //remove all of the actors
                myWorld.removeAllActors();
                //set up a new sim
                Greenfoot.setWorld(new StartWorld());
            }
        }
    }
    
    protected void onClick() {
    }
}

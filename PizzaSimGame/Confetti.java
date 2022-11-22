import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class confetti here.
 * 
 * @author Yixin Cai
 * @version (a version number or a date)
 */
public class Confetti extends Effects
{
    private GifImage confetti;
    
    public Confetti() {
        confetti = new GifImage("confetti.gif");
        setImage(confetti.getCurrentImage());
    }
    
    /**
     * Act - do whatever the confetti wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setImage(confetti.getCurrentImage());
    }
}

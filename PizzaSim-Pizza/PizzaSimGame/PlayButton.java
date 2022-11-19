import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartButton here.
 * 
 * @author Yixin Cai
 * @version (a version number or a date)
 */
public class PlayButton extends Button
{
    public PlayButton() {
        super(new GreenfootImage("play.png"), new GreenfootImage("playDown.png"));
    }
    
    public PlayButton(GreenfootImage playImage, GreenfootImage playDownImage) {
        super(playImage, playDownImage);
    }
    
    protected void onClick() {
        SettingWorld sw = (SettingWorld) getWorld();
        Greenfoot.setWorld(new MyWorld(sw));
    }
}

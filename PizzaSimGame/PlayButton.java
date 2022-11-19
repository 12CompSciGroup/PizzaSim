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
        super();
        
        this.image = new GreenfootImage("playButton.png");
        this.downImage = new GreenfootImage("playButtonDown.png");
        this.hoverImage = new GreenfootImage("playButtonHover.png");
    }
    
    protected void onClick() {
        SettingWorld sw = (SettingWorld) getWorld();
        Greenfoot.setWorld(new MyWorld(sw));
    }
}

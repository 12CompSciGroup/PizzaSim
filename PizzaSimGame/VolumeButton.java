import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class VolumeButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class VolumeButton extends Button
{
    private boolean isUp;
    
    public VolumeButton(boolean isUp) {
        super();
        this.isUp = isUp;
        
        if (isUp) {
            this.image = new GreenfootImage("volumeUp.png");
            this.downImage = new GreenfootImage("volumeUpDown.png");
            this.hoverImage = new GreenfootImage("volumeUpHover.png");
        }
        else {
            this.image = new GreenfootImage("volumeDown.png");
            this.downImage = new GreenfootImage("volumeDownDown.png");
            this.hoverImage = new GreenfootImage("volumeDownHover.png");
        }
    }
    
    public void onClick() {
        World world = getWorld();
        if (world instanceof MyWorld) {
            MyWorld mw = (MyWorld) world;
            mw.updateVolume(isUp);
        }
        else if (world instanceof SettingWorld) {
            SettingWorld sw = (SettingWorld) world;
            sw.updateVolume(isUp);
        }
    }
}

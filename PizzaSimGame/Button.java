import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Button extends Actor implements ISoundCentre
{
    protected GreenfootImage image;
    protected GreenfootImage downImage;
    protected GreenfootSound sound;
    protected boolean isImageDown;
    
    public Button(GreenfootImage image, GreenfootImage downImage) {
        this.image = image;
        this.downImage = downImage;
        this.sound = new GreenfootSound("click.wav");
        isImageDown = false;
        setImage(image);
    }
    
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        //if(Greenfoot.mouseClicked(this)) {
        //    onClick();
        //    playSound();
        //}
        //onHover();
        
        if (Greenfoot.mouseClicked(this)) {
            setImage(downImage);
            isImageDown = true;
            onClick();
            playSound();
        }
        // this boolean is to help reduce number of setImage calls
        // thus can increase efficiency.
        else if (isImageDown){
            setImage(image);
            isImageDown = false;
        }
    }
    
    protected abstract void onClick();
    //protected abstract void onHover();
    
    /**
     * Start playing sound if there is sound
     */
    public void playSound() {
        if (sound != null) {
            sound.play();
        }
    }
    
    /**
     * Pause playing sound if there is sound
     */
    public void pauseSound() {
        if (sound != null) {
            sound.pause();
        }
    }
    
    /**
     * Update volume of the sounds
     * @param volume The current volume
     */
    public void setVolume(int volume) {
        if (sound != null) {
            sound.setVolume(volume);
        }
    }
}

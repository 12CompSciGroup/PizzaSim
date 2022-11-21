import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author Yixin Cai
 * @version (a version number or a date)
 */
public abstract class Button extends Actor implements ISound
{   
    protected GreenfootImage image;
    protected GreenfootImage downImage;
    protected GreenfootImage hoverImage;

    private static GreenfootSound[] sounds = {
        new GreenfootSound("click.wav"),
        new GreenfootSound("click.wav"),
        new GreenfootSound("click.wav"),
        new GreenfootSound("click.wav"),
        new GreenfootSound("click.wav"),
        new GreenfootSound("click.wav"),
        new GreenfootSound("click.wav"),
        new GreenfootSound("click.wav"),
        new GreenfootSound("click.wav"),
        new GreenfootSound("click.wav")
    };
    private int soundNum;
    private int soundIndex;
    private int downTik;
    private boolean isHovered;
    
    public Button() {
        this.downTik = 0;
        this.soundNum = sounds.length;
        this.soundIndex = 0;
        this.isHovered = false;
    }
    
    public void addedToWorld(World w) {
        for (GreenfootSound sound : sounds) {
            sound.setVolume(Utils.volume);
        }
    }
    
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            downTik = 5;
            onClick();
            playSound();
        }
        else if (Greenfoot.mouseMoved(this)) {
            isHovered = true;
        }
        else if (Greenfoot.mouseMoved(null)) {
            isHovered = false;
        }
        
        if (downTik > 0) {
            setImage(downImage);
            downTik--;
        }
        else if (isHovered) {
            setImage(hoverImage);
        }
        else {
            setImage(image);
        }
    }
    
    protected abstract void onClick();
    
    /**
     * Start playing sound if there is sound
     */
    public void playSound() {
        soundIndex++;
        if (soundIndex > (sounds.length - 1)) {
            soundIndex = 0;
        }
        sounds[soundIndex].play();
    }
    
    /**
     * Pause playing sound if there is sound
     */
    public void pauseSound() {
        sounds[soundIndex].pause();
    }
    public boolean isSoundPlaying () {
        return sounds[soundIndex].isPlaying();
    }
    
    public GreenfootSound getSound (){
        return sounds[soundIndex];
    }
    
    /**
     * Update volume of the sounds
     * @param volume The current volume
     */
    public void setVolume(int volume) {
        for (GreenfootSound sound : sounds) {
            sound.setVolume(volume);
        }
    }
}

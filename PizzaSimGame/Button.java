import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author Yixin Cai
 * @version (a version number or a date)
 */
public abstract class Button extends Actor implements ISoundCentre
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
    
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            downTik = 20;
            onClick();
            playSound(soundIndex);
            soundIndex++;
            if (soundIndex > (sounds.length - 1)) {
                soundIndex = 0;
            }
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
    //protected abstract void onHover();
    
    /**
     * Start playing sound if there is sound
     */
    public void playSound(int soundIndex) {
        if (sounds != null) {
            sounds[soundIndex].play();
        }
    }
    
    /**
     * Pause playing sound if there is sound
     */
    public void pauseSound(int soundIndex) {
        if (sounds != null) {
            sounds[soundIndex].pause();
        }
    }
    public boolean isSoundPlaying (int index) {
        return sounds[index].isPlaying();
    }
    
    public GreenfootSound getSound (int index){
        return sounds[index];
    }
    
    public int getSoundNumber (){
        if (soundIndex == 0){
            return sounds.length;
        }
        return soundIndex - 1;
    }
    
    /**
     * Update volume of the sounds
     * @param volume The current volume
     */
    public void setVolume(int volume, int soundIndex) {
        if (sounds != null) {
            sounds[soundIndex].setVolume(volume);
        }
    }
}

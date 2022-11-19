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
    //protected GreenfootSound sound;
    protected GreenfootSound[] sounds;
    private int soundNum;
    private static int soundIndex;
    protected boolean isImageDown;
    
    public Button(GreenfootImage image, GreenfootImage downImage) {
        this.image = image;
        soundNum = 10;
        soundIndex = 0;
        this.downImage = downImage;
        isImageDown = false;
        setImage(image);
        sounds = new GreenfootSound[soundNum];
        for (int i = 0; i < soundNum; i++) {
            sounds[i] = new GreenfootSound("click.wav");
        }
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
        String key = Greenfoot.getKey();
        
        if (Greenfoot.mouseClicked(this)) {
            setImage(downImage);
            isImageDown = true;
            onClick();
            //playSound();
            //if ((this).equals(key)) {
                //sounds[0].play();
                playSound(soundIndex);
                soundIndex++;
                if (soundIndex > (sounds.length - 1)) {
                    soundIndex = 0;
                }
            //}
        }
        // this boolean is to help reduce number of setImage calls
        // thus can increase efficiency.
        else if (isImageDown) {
            setImage(image);
            isImageDown = false;
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

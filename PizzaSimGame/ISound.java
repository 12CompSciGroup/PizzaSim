import greenfoot.*;
/**
 * Write a description of interface ISound here.
 * 
 * @author Yixin Cai
 * @version (a version number or a date)
 */
public interface ISound
{
    /**
     * Set volume
     * @param volume The current volume
     */
    public void setVolume(int volume);
    
    /**
     * Play sound
     */
    public void playSound();
    
    /**
     * Pause sound
     */
    public void pauseSound();
    
    /**
     * Check if sound is playing
     */
    public boolean isSoundPlaying();
    
    /**
     * Get sound
     */
    public GreenfootSound getSound();
}

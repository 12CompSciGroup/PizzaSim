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
    public void playSound(int soundIndex);
    
    /**
     * Pause sound
     */
    public void pauseSound(int soundIndex);
    
    public int getSoundNumber();
    public boolean isSoundPlaying(int soundIndex);
    public GreenfootSound getSound(int soundIndex);
}

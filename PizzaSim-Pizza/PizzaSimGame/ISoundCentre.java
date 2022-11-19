import greenfoot.*;
/**
 * Write a description of class SoundCentre here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface ISoundCentre
{
    /**
     * Set volume
     * @param volume The current volume
     */
    public void setVolume(int volume, int soundIndex);
    
    /**
     * Play sound
     */
    public void playSound(int soundIndex);
    
    /**
     * Pause sound
     */
    public void pauseSound(int soundIndex);
    
    public int getSoundNumber ();
    public boolean isSoundPlaying (int soundIndex);
    public GreenfootSound getSound (int soundIndex);
}

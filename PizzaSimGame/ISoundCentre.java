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
    public void setVolume(int volume);
    
    /**
     * Play sound
     */
    public void playSound();
    
    /**
     * Pause sound
     */
    public void pauseSound();
}

import greenfoot.GreenfootSound;

/**
 * Write a description of class BackgroundSound here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BackgroundSound  
{
    private static BackgroundSound instance;
    
    private GreenfootSound sound;
    
    private BackgroundSound() {
        sound = new GreenfootSound("Run-Amok.wav");
    }
    
    public static BackgroundSound getInstance() {
        if (instance == null) {
            instance = new BackgroundSound();
        }
        return instance;
    }
    
    /**
     * Set volume
     * @param volume The current volume
     */
    public void setVolume(int volume) {
        sound.setVolume(volume);
    };
    
    /**
     * Play sound
     */
    public void playSound() {
        sound.playLoop();
    };
    
    /**
     * Stop sound
     */
    public void pauseSound() {
        sound.pause();
    };
}

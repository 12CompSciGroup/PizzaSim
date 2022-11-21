import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class clock here.
 * 
 * @author (Yuxin Li) 
 * @version (a version number or a date)
 */
public class Clock extends Actor implements ISound
{
    /**
     * Act - do whatever the clock wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootSound tikSound = new GreenfootSound("tik.wav");
    private GreenfootSound alarmSound = new GreenfootSound("alarm.wav");
    private GreenfootSound sound;
    private int time;
    private static GreenfootImage[] clocks={
        new GreenfootImage("clock_1.png"), 
        new GreenfootImage("clock_2.png"), 
        new GreenfootImage("clock_3.png"), 
        new GreenfootImage("clock_4.png"), 
        new GreenfootImage("clock_5.png"), 
        new GreenfootImage("clock_6.png"), 
        new GreenfootImage("clock_7.png"), 
        new GreenfootImage("clock_8.png"), 
        new GreenfootImage("clock_9.png"), 
        new GreenfootImage("clock_10.png"), 
        new GreenfootImage("clock_11.png"), 
        new GreenfootImage("clock_12.png"), 
    };
    private int interval;
    private Pizza pizza;
    private int imageIndex;
    private int timeIndex;
    private int burnTime;
    private int burnAct;
    private int x, y;
    public Clock(int time, Pizza pizza){
        this.time=time;
        imageIndex=0;
        timeIndex=time/12;
        burnTime=450;
        burnAct=0;
        this.pizza=pizza;
        sound = tikSound;
    }
    
    public void addedToWorld(World w) {
        tikSound.setVolume(Utils.volume);
        alarmSound.setVolume(Utils.volume);
        playSound();
    }
    
    /**
     * countdown act
     */
    public void act()
    {
        timeIndex--;
        if(imageIndex==11){
            x=this.getX();
            y=this.getY();
            setImage(clocks[imageIndex]);
            imageIndex++;
            timeIndex=time/12;
            pizza.cookPizza();
        }
        else if(imageIndex==12){
            burnTime--;
            clockAlarm();
            if(burnTime==0){
                pizza.burnPizza();
                //smoke flowing
            }
        }
        else if(imageIndex<11&&timeIndex==0){
            setImage(clocks[imageIndex]);
            imageIndex++;
            timeIndex=time/12;
        }
        removeClock();
    }
    /**
     * When the countdown is finished, the clock will shake and alarm 
     */
    public void clockAlarm(){
        burnAct++;
        if(burnAct==1){
            setLocation(x-5, y);
        }
        if(burnAct==2){
            setLocation(x, y);
        }
        if(burnAct==3){
            setLocation(x+5, y);
            burnAct=0;
        }
        switchSound();
    }
    // when the pizza is picked up by the cashier
    // remove clock
    public void removeClock(){
        if(this.pizza.isInOven()==false){
            getWorld().removeObject(this);
            sound.stop();
        }
    }
    
    private void switchSound() {
        sound.stop();
        sound = alarmSound;
        playSound();
    }
    
    /**
     * Start playing sound if there is sound
     */
    public void playSound() {
        sound.playLoop();
    }
    
    /**
     * Pause playing sound if there is sound
     */
    public void pauseSound() {
        sound.pause();
    }
    
    public boolean isSoundPlaying () {
        return sound.isPlaying();
    }
    
    public GreenfootSound getSound (){
        return sound;
    }
    
    /**
     * Update volume of the sounds
     * @param volume The current volume
     */
    public void setVolume(int volume) {
        tikSound.setVolume(volume);
        alarmSound.setVolume(volume);
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Clock is a Greenfoot actor that shows the time rest for the pizza to be cooked.
 * <p>Displays new a clock image after a certain time passes 
 * 
 * @author Yuxin Li
 * @version November 2022
 * 
 * clock sequence by Nendra Beluci from:
 * <a href="https://www.vecteezy.com/free-vector/cartoon-cloud">Cartoon Cloud Vectors by Vecteezy</a>
 */
public class Clock extends Effects implements ISound
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
        new GreenfootImage("/images/clock_0.png"),
        new GreenfootImage("/images/clock_1.png"), 
        new GreenfootImage("/images/clock_2.png"), 
        new GreenfootImage("/images/clock_3.png"), 
        new GreenfootImage("/images/clock_4.png"), 
        new GreenfootImage("/images/clock_5.png"), 
        new GreenfootImage("/images/clock_6.png"), 
        new GreenfootImage("/images/clock_7.png"), 
        new GreenfootImage("/images/clock_8.png"), 
        new GreenfootImage("/images/clock_9.png"), 
        new GreenfootImage("/images/clock_10.png"), 
        new GreenfootImage("/images/clock_11.png"), 
        new GreenfootImage("/images/clock_12.png"), 
    };
    private int interval;
    private Pizza pizza;
    private int imageIndex;
    private int timeIndex;
    private int burnTime;
    private int burnAct;
    private int x, y;
    /**
     * Clock is a Greenfoot actor that shows the time rest for the pizza to be cooked. 
     * @param time The amount of time required to cook a pizza
     * @param pizza The pizza that is put into an oven
     */
    public Clock(int time, Pizza pizza){
        setImage(clocks[0]);
        this.time = time;
        imageIndex = 0;
        timeIndex = time/12;
        burnTime = 450;
        burnAct = 0;
        this.pizza = pizza;
        sound = tikSound;
    }
    
    /**
     * When the clock is added to the World, start the counting down sound effect
     */
    public void addedToWorld(World w) {
        tikSound.setVolume(Utils.volume);
        alarmSound.setVolume(Utils.volume);
        playSound();
    }
    
    /**
     * countdown act
     * set a new clock image after a certain time passes.
     * After the pizza is cooked, start countdown the burn time.
     */
    public void act()
    {
        timeIndex--;
        if(imageIndex==12){
            x=this.getX();
            y=this.getY();
            setImage(clocks[imageIndex]);
            imageIndex++;
            timeIndex=time/12;
            pizza.cookPizza();
        }
        else if(imageIndex==13){
            burnTime--;
            clockAlarm();
            if(burnTime==0){
                pizza.burnPizza();
            }
        }
        else if(imageIndex < 12 && timeIndex == 0){
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
    /**
     * when the pizza is picked up by the cashier and is no longer in oven 
     * remove the clock and stop the alarm sound
     */
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

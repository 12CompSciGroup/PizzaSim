import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class MyWorld here.
 * 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private boolean isSimOver;
    private SettingWorld settingWorld;
    // used to show current volume to player
    //private int volume;
    private Label volumeLabel;
    private int customerSpawnRate, customerDir;
    //variables for spawning customers
    private int dir, startingY, dirRNG;
    private ArrayList<GreenfootSound> pausedSounds;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld(SettingWorld settingWorld)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1);
        this.settingWorld = settingWorld;
        isSimOver = false;
        pausedSounds = new ArrayList<GreenfootSound>();
        // set initial volue
        //volume = 60;
        // add volume control
        volumeLabel = new Label(Utils.volume + "%");
        addObject(new VolumeButton(false), 850, 40);
        addObject(new VolumeButton(true), 970, 40);
        addObject(volumeLabel,910, 40);
        //adds oven objects
        addObject(new Oven(), Utils.oven1X, Utils.ovenY);
        addObject(new Oven(), Utils.oven2X, Utils.ovenY);
        addObject(new Oven(), Utils.oven3X, Utils.ovenY);
        //adds chef objects
        addObject(new Chef(Utils.chefX, Utils.chef1Y, 100, 100), Utils.chefX, Utils.chef1Y);
        addObject(new Chef(Utils.chefX, Utils.chef2Y, 100, 100), Utils.chefX, Utils.chef2Y);
        //adds cashier objects
        addObject(new Cashier(Utils.cashier1X, 460, 100, 100), Utils.cashier1X, 460);
        addObject(new Cashier(Utils.cashier2X, 460, 100, 100), Utils.cashier2X, 460);
        //adds kitchen counters
        addObject(new KitchenCounter(), Utils.kitchenCounterX, Utils.kitchenCounterY1);
        addObject(new KitchenCounter(), Utils.kitchenCounterX, Utils.kitchenCounterY2);
        
        addObject(new Door(), Utils.door1X, Utils.enterY);
        addObject(new Door(), Utils.door1X, Utils.exitY);

        addObject(new Door(), Utils.door2X, Utils.enterY);
        addObject(new Door(), Utils.door2X, Utils.exitY);

        addObject(new CashierCounter(), Utils.cashier1X, Utils.counterY);
        addObject(new CashierCounter(), Utils.cashier2X, Utils.counterY);

        addObject(new WaitingLine(), Utils.wait1X, Utils.counterY);
        addObject(new WaitingLine(), Utils.wait2X, Utils.counterY);
        addObject(new WaitingLine(), Utils.wait3X, Utils.counterY);
        addObject(new Robber(1),500,100);
        //addObject(new Pizza(
        addObject(new MoneyDisplayer(0), 150, 40);
        
    }
    
    public void act() {
        spawnCustomer();
    }
    
    public void spawnCustomer()
    {
        dirRNG = Greenfoot.getRandomNumber(2);
        
        if(dirRNG == 1)
        {
            dir = 1;
            startingY = 81;
        }
        else if(dirRNG == 0)
        {
            dir = -1;
            startingY = getHeight();
        }
        
        int rng = Greenfoot.getRandomNumber(120);
        
        if(rng == 0)
        {
            addObject(new Customer(dir), Greenfoot.getRandomNumber(124) + 449, startingY);
        }
        
    }
    public void spawnRobber()
    {
        if(Utils.resturantMoneyOne>5)
        {
            addObject(new Robber(2), 500,100);
        }
        if(Utils.resturantMoneyTwo>5)
        {
            addObject(new Robber(1),500,100);
        }
        
    }
    
    public void removeAllActors() {
        ArrayList<Actor> actors = (ArrayList) getObjects(null);
        for(Actor actor : actors) {
           removeObject(actor); 
        }
    }
    
    public boolean checkSimOver() {
        return this.isSimOver;
    }
    
    public void setUpNewSim() {
        
    }
    
    public void stopped() {
        // stop all sounds
        pausedSounds.clear();
        ArrayList<ISound> sounds = (ArrayList<ISound>) getObjects(ISound.class);
        for (ISound sound : sounds) {
            if (sound.isSoundPlaying()) {
                sound.pauseSound();
                pausedSounds.add(sound.getSound());
            }
        }
        // stop background sound
        Utils.backgroundSound.pause();
    }
    
    public void started() {
        // play all sounds
        ArrayList<ISound> sounds = (ArrayList<ISound>) getObjects(ISound.class);
        for (ISound sound : sounds) {
            if (pausedSounds.contains(sound)) {
                sound.playSound();
            }
        }
        // play background sound in loop
        Utils.backgroundSound.playLoop();
    }
    
    /**
     * Update volume.
     * @param isUp True if turn volume up, Flase otherwise.
     */
    public void updateVolume(boolean isUp) {
        if(isUp) {
            Utils.volume = Math.min(Utils.volume + 20, 100);
        }
        else {
            Utils.volume = Math.max(Utils.volume - 20, 0);
        }
        ArrayList<ISound> sounds = (ArrayList<ISound>) getObjects(ISound.class);
        for (ISound sound : sounds) {
            sound.setVolume(Utils.volume);
        }
        //  Update background sound volume.
        Utils.backgroundSound.setVolume(Utils.volume);
        // Update volume label.
        volumeLabel.updateLabel(Utils.volume + "%");
    }
}

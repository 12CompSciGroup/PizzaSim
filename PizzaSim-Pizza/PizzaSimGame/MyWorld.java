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
    private int customerSpawnRate, customerDir;
    //variables for spawning customers
    private int dir, startingY, dirRNG;
    private ArrayList<GreenfootSound> pausedSounds;
    private int current_money;
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
        
        addObject(new Money_displayer(0), 200, 40);
        //addObject(new Pizza(
        
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
        ArrayList<ISoundCentre> sounds = (ArrayList<ISoundCentre>) getObjects(ISoundCentre.class);
        for (ISoundCentre sound : sounds) {
            int index = sound.getSoundNumber();
            if (sound.isSoundPlaying(index)) {
                sound.pauseSound(index);
                pausedSounds.add(sound.getSound(index));
            }
        }
        // stop background sound
        BackgroundSound.getInstance().pauseSound();
    }
    
    public void started() {
        // play all sounds
        ArrayList<ISoundCentre> sounds = (ArrayList<ISoundCentre>) getObjects(ISoundCentre.class);
        for (ISoundCentre sound : sounds) {
            if (pausedSounds.contains(sound)) {
                sound.playSound(sound.getSoundNumber());
            }
        }
        // play background sound in loop
        BackgroundSound.getInstance().playSound();
    }
    
}
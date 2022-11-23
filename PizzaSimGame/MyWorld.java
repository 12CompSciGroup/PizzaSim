import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * <h1>The Pizza Simulation finished by Group 1.</h1>
 * <p> Group member: Yixin Cai, Anson Ho, Yuxin Li, Gloria Chan, Andy Li, Eric Zheng</p>
 * <h2>Credit:</h2>
 * 
 * <p>
 * Background music:
 * Run Amok by Kevin MacLeod | https://incompetech.com/
 * Music promoted by https://www.chosic.com/free-music/all/
 * Creative Commons CC BY 3.0
 * https://creativecommons.org/licenses/by/3.0/
 * </p>
 * <p>
 * Click music:
 * Sound Effect by <a href="https://pixabay.com/users/sennafoxy-177
 * 62344/?utm_source=link-attribution&amp;utm_medium=referral&amp;ut
 * m_campaign=music&amp;utm_content=14388">SennaFoxy</a> from <a href
 * ="https://pixabay.com//?utm_source=link-attribution&amp;utm_medium=
 * referral&amp;utm_campaign=music&amp;utm_content=14388">Pixabay</a>
 * </p>
 * <p>Angry hmp sound clips: Sound Effect from <a href="https://pixabay.com/
 * sound-effects/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm
 * _campaign=music&amp;utm_content=38556">Pixabay</a>
 * </p>
 * <p>Clock tik sound clip: Sound Effect from <a href="https://pixabay.com/sound
 * -effects/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign
 * =music&amp;utm_content=55420">Pixabay</a>
 * </p>
 * <p>Other Sound clips: https://pixabay.com/music/</p>
 * 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private boolean isSimOver, changedLevelTwoLeft, changedLevelTwoRight, changedLevelThreeLeft, changedLevelThreeRight;
    private SettingWorld settingWorld;
    private int customerSpawnRate, customerDir, previousMoneyMama = 0, previousMoneyPapa = 0;
    //variables for spawning customers
    private Label volumeLabel;
    private int dir, startingY, dirRNG;
    private ArrayList<GreenfootSound> pausedSounds;
    
    private boolean cash2Mama = false, cash2Papa = false;
    
    Utils utils = new Utils();
    
    CashierCover coverRight = new CashierCover("right");
    CashierCover coverLeft = new CashierCover("left");
    
    OvenCover cover1 = new OvenCover();
    OvenCover cover2 = new OvenCover();
    OvenCover cover3 = new OvenCover();
    OvenCover cover4 = new OvenCover();
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld(SettingWorld settingWorld)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1);
        addObject(utils, 0, 0);
        addObject(new MoneyInterface(utils), 0, 0);
        addObject(new LevelUp(), 0, 0);
        this.settingWorld = settingWorld;
        
        utils.setRobbingMoneyMama(settingWorld.getRobberStealMama() * -1);
        utils.setRobbingMoneyPapa(settingWorld.getRobberStealPapa() * -1);
        
        isSimOver = false;
        pausedSounds = new ArrayList<GreenfootSound>();
        // add volume control
        volumeLabel = new Label(Utils.volume + "%");
        addObject(new VolumeButton(false), 850, 40);
        addObject(new VolumeButton(true), 970, 40);
        addObject(volumeLabel,910, 40);
        //adds oven objects left
        addObject(new Oven(), Utils.oven1X, Utils.ovenY);
        addObject(new Oven(), Utils.oven2X, Utils.ovenY);
        addObject(new Oven(), Utils.oven3X, Utils.ovenY);
        addObject(cover1, Utils.oven2X - 1, Utils.ovenY);
        addObject(cover2, Utils.oven3X + 5, Utils.ovenY);
        //adds oven objects right
        addObject(new Oven(), Utils.oven4X, Utils.ovenY);
        addObject(new Oven(), Utils.oven5X, Utils.ovenY);
        addObject(new Oven(), Utils.oven6X, Utils.ovenY);
        addObject(cover3, Utils.oven5X + 5, Utils.ovenY);
        addObject(cover4, Utils.oven6X - 5, Utils.ovenY);
        
        //adds chef objects left
        addObject(new Chef(Utils.chef1Y, 100, 100, -1), Utils.chefXLeft, Utils.chef1Y);
        if (settingWorld.getChefNumMama() == 2){
            addObject(new Chef(Utils.chef2Y, 100, 100, -1), Utils.chefXLeft, Utils.chef2Y);
        }
        if (settingWorld.getChefNumMama() == 3){
            addObject(new Chef(Utils.chef2Y, 100, 100, -1), Utils.chefXLeft, Utils.chef2Y);
            addObject(new Chef(Utils.chef3Y, 100, 100, -1), Utils.chefXLeft, Utils.chef3Y);
        }
        
        //adds chef objects right
        addObject(new Chef(Utils.chef1Y, 100, 100, 1), Utils.chefXRight, Utils.chef1Y);
        if (settingWorld.getChefNumPapa() == 2){
            addObject(new Chef(Utils.chef2Y, 100, 100, 1), Utils.chefXRight, Utils.chef2Y);
        }
        if (settingWorld.getChefNumPapa() == 3){
            addObject(new Chef(Utils.chef2Y, 100, 100, 1), Utils.chefXRight, Utils.chef2Y);
            addObject(new Chef(Utils.chef3Y, 100, 100, 1), Utils.chefXRight, Utils.chef3Y);
        }
    
        //adds cashier objects left
        addObject(new Cashier(Utils.cashier1X, Utils.cashierY, 100, 100, -1), Utils.cashier1X, 460);
        addObject(coverLeft, 408, 504);
        if (settingWorld.getCashierNumMama() == 2){
            addObject(new Cashier(Utils.cashier2X, Utils.cashierY, 100, 100, -1), Utils.cashier2X, 460);
            removeObject(coverLeft);
            cash2Mama = true;
        }
        
        //adds cashier objects right
        addObject(new Cashier(Utils.cashier4X, Utils.cashierY, 100, 100, 1), Utils.cashier4X, 460);
        addObject(coverRight, 617, 507);
        if (settingWorld.getCashierNumPapa() == 2){
            addObject(new Cashier(Utils.cashier3X, Utils.cashierY, 100, 100, 1), Utils.cashier3X, 460);
            removeObject(coverRight);
            cash2Papa = true;
        }
        //adds kitchen counters left
        addObject(new KitchenCounter(-1), Utils.kitchenCounterXLeft, Utils.kitchenCounterY1);
        addObject(new KitchenCounter(-1), Utils.kitchenCounterXLeft, Utils.kitchenCounterY2);
        addObject(new KitchenCounter(-1), Utils.kitchenCounterXLeft, Utils.kitchenCounterY3);
        //adds kitchen counters right
        addObject(new KitchenCounter(1), Utils.kitchenCounterXRight, Utils.kitchenCounterY1);
        addObject(new KitchenCounter(1), Utils.kitchenCounterXRight, Utils.kitchenCounterY2);
        addObject(new KitchenCounter(1), Utils.kitchenCounterXRight, Utils.kitchenCounterY3);
        //adds doors left
        addObject(new Door(), Utils.door1X, Utils.enterY);
        addObject(new Door(), Utils.door1X, Utils.exitY);
        //adds doors right
        addObject(new Door(), Utils.door2X, Utils.enterY);
        addObject(new Door(), Utils.door2X, Utils.exitY);
        //adds cashier counters left
        addObject(new CashierCounter(), Utils.cashier1X, Utils.counterY);
        addObject(new CashierCounter(), Utils.cashier2X, Utils.counterY);
        //adds cashier counters right
        addObject(new CashierCounter(), Utils.cashier3X, Utils.counterY);
        addObject(new CashierCounter(), Utils.cashier4X, Utils.counterY);
        //adds waiting locations left
        addObject(new WaitingLine(), Utils.wait1X, Utils.counterY);
        addObject(new WaitingLine(), Utils.wait2X, Utils.counterY);
        addObject(new WaitingLine(), Utils.wait3X, Utils.counterY);
        //adds waiting locations right
        addObject(new WaitingLine(), Utils.wait4X, Utils.counterY);
        addObject(new WaitingLine(), Utils.wait5X, Utils.counterY);
        addObject(new WaitingLine(), Utils.wait6X, Utils.counterY);
        //addObject
        addObject(new MoneyDisplayer(this.settingWorld.getMoneyNumMama()), 335, 37);
        addObject(new MoneyDisplayer(this.settingWorld.getMoneyNumPapa()), 670, 37);
        
        utils.resturantMoneyOneAdd(settingWorld.getMoneyNumMama());
        utils.resturantMoneyTwoAdd(settingWorld.getMoneyNumPapa());
        
        if(utils.getResturantMoneyOne() > 200)
        {
            utils.addResturantLevelOne();
            utils.addResturantLevelOne();
        }
        if(utils.getResturantMoneyTwo() > 200)
        {
            utils.addResturantLevelTwo();
            utils.addResturantLevelTwo();
        }
        
    }
    
    public void act() {
        spawnCustomer();
        checkLevel();
        spawnRobber();
    }
    
    public void checkLevel()
    {
        //changes level for left store
        if((utils.getResturantLevelOne() == 2 || utils.getResturantMoneyOne() > 100) && !changedLevelTwoLeft)
        {
            changedLevelTwoLeft = true;
            removeObject(cover1);
            
            if (settingWorld.getCashierNumMama() == 1)
            {
                addObject(new Cashier(Utils.cashier2X, Utils.cashierY, 100, 100, -1), Utils.cashier2X, 460);
                removeObject(coverLeft);
                cash2Mama = true;
            }
            
            if(settingWorld.getChefNumMama() == 1)
            {
                addObject(new Chef(Utils.chef2Y, 100, 100, -1), Utils.chefXLeft, Utils.chef2Y);
            }
            if(settingWorld.getChefNumMama() == 2)
            {
                addObject(new Chef(Utils.chef3Y, 100, 100, -1), Utils.chefXLeft, Utils.chef3Y);
            }            
        }
        if(utils.getResturantLevelOne() == 3 && !changedLevelThreeLeft)
        {
            changedLevelThreeLeft = true;
            removeObject(cover2);
            
            if(settingWorld.getChefNumMama() == 1)
            {
                addObject(new Chef(Utils.chef3Y, 100, 100, -1), Utils.chefXLeft, Utils.chef3Y);
            }
        }
        //changes level for right store
        if((utils.getResturantLevelTwo() == 2 || utils.getResturantMoneyTwo() > 100) && !changedLevelTwoRight)
        {
            changedLevelTwoRight = true;
            removeObject(cover3);
            
            if (settingWorld.getCashierNumPapa() == 1)
            {
                addObject(new Cashier(Utils.cashier3X, Utils.cashierY, 100, 100, 1), Utils.cashier3X, 460);
                removeObject(coverRight);
                cash2Papa = true;
            }
            
            if(settingWorld.getChefNumPapa() == 1)
            {
                addObject(new Chef(Utils.chef2Y, 100, 100, 1), Utils.chefXRight, Utils.chef2Y);
            }
            if(settingWorld.getChefNumPapa() == 2)
            {
                addObject(new Chef(Utils.chef3Y, 100, 100, 1), Utils.chefXRight, Utils.chef3Y);
            }
        }
        if(utils.getResturantLevelTwo() == 3 && !changedLevelThreeRight)
        {
            changedLevelThreeRight = true;
            removeObject(cover4);
            
            if(settingWorld.getChefNumPapa() == 1)
            {
                addObject(new Chef(Utils.chef3Y, 100, 100, 1), Utils.chefXRight, Utils.chef3Y);
            }
        }
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
        int customerSpawnX = Greenfoot.getRandomNumber(80) + 470;
        
        if(rng == 0)
        {
            addObject(new Customer(dir, customerSpawnX, cash2Mama, cash2Papa), customerSpawnX, startingY);
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
    
    public void spawnRobber()
    {
        if(utils.getResturantMoneyOne() - previousMoneyMama > 50)
        {
            previousMoneyMama = utils.getResturantMoneyOne();
            addObject(new Robber(1), 512, 100);
        }
        if(utils.getResturantMoneyTwo() - previousMoneyPapa > 50)
        {
            previousMoneyPapa = utils.getResturantMoneyTwo();
            addObject(new Robber(-1), 512, 100);
        }
    }
}

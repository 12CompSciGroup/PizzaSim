import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * default
 * 
 * @author Yixin Cai
 * @version (a version number or a date)
 */
public class SettingWorld extends World
{
    // Declare variables, objects
    private GreenfootImage imageMama;
    private GreenfootImage imagePapa;
    
    // Declare buttons
    private PlusButton chefPlus;
    private MinusButton chefMinus;
    private PlusButton cashierPlus;
    private MinusButton cashierMinus;
    private PlusButton moneyPlus;
    private MinusButton moneyMinus;
    private PlusButton ovenPlus;
    private MinusButton ovenMinus;
    private PlusButton robberStealPlus;
    private MinusButton robberStealMinus;
    private SwitchButton switchButton;
    private PlayButton playButton;
    
    // Declare labels
    private Label chefNumLabel;
    private Label cashierNumLabel;
    private Label moneyNumLabel;
    private Label ovenNumLabel;
    private Label robberStealLabel;
    private Label volumeLabel;
    
    // Declare final variables
    private final int DEFAULT_CHEF_NUM = 1;
    private final int DEFAULT_CASHIER_NUM = 1;
    private final int DEFAULT_MONEY_NUM = 0;
    private final int DEFAULT_OVEN_NUM = 1;
    private final int DEFAULT_ROBBER_STEAL = 15;
    
    // Declare variables
    private int chefNumMama;
    private int cashierNumMama;
    private int moneyNumMama;
    private int ovenNumMama;
    private int robberStealMama;
    
    private int chefNumPapa;
    private int cashierNumPapa;
    private int moneyNumPapa;
    private int ovenNumPapa;
    private int robberStealPapa;
    
    // Declare boolean 
    private boolean isMama;
    
    /**
     * Constructor for objects of class SettingWorld.
     * 
     */
    public SettingWorld()
    {    
        // Create a new world with 1024x800 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1);
        
        // Initiate variables and objects for both pizzeria Mama and Papa
        // True if it is in Papa's pizzeria, False if it is in Papa's pizzeria
        this.isMama = false;
        
        // Set the default values for each variables.
        this.chefNumMama = DEFAULT_CHEF_NUM;
        this.cashierNumMama = DEFAULT_CASHIER_NUM;
        this.moneyNumMama = DEFAULT_MONEY_NUM;
        this.ovenNumMama = DEFAULT_OVEN_NUM;
        this.robberStealMama = DEFAULT_ROBBER_STEAL;
        
        this.chefNumPapa = DEFAULT_CHEF_NUM;
        this.cashierNumPapa = DEFAULT_CASHIER_NUM;
        this.moneyNumPapa = DEFAULT_MONEY_NUM;
        this.ovenNumPapa = DEFAULT_OVEN_NUM;
        this.robberStealPapa = DEFAULT_ROBBER_STEAL;
        
        this.imageMama = new GreenfootImage("settingScreenMAMA.png");
        this.imagePapa = new GreenfootImage("settingScreenPAPA.png");
        
        this.switchButton = new SwitchButton();
        this.playButton = new PlayButton();
        
        this.chefPlus = new PlusButton();
        this.chefMinus = new MinusButton();
        this.chefNumLabel = new Label("");
        
        this.cashierPlus = new PlusButton();
        this.cashierMinus = new MinusButton();
        this.cashierNumLabel = new Label("");
        
        this.moneyPlus = new PlusButton();
        this.moneyMinus = new MinusButton();
        this.moneyNumLabel = new Label("");
        
        this.ovenPlus = new PlusButton();
        this.ovenMinus = new MinusButton();
        this.ovenNumLabel = new Label("");
        
        this.robberStealPlus = new PlusButton();
        this.robberStealMinus = new MinusButton();
        this.robberStealLabel = new Label("");
        
        volumeLabel = new Label(Utils.volume + "%");
        
        // Add objects into SettingWorld
        addObject(new VolumeButton(false), 850, 40);
        addObject(new VolumeButton(true), 970, 40);
        addObject(volumeLabel,910, 40);
        
        addObject(this.chefPlus, 900, 275);
        addObject(this.chefMinus, 800, 275);
        addObject(this.chefNumLabel, 850, 275);
        
        addObject(this.cashierPlus, 900, 330);
        addObject(this.cashierMinus, 800, 330);
        addObject(this.cashierNumLabel, 850, 330);
        
        addObject(this.moneyPlus, 900, 440);
        addObject(this.moneyMinus, 800, 440);
        addObject(this.moneyNumLabel, 850, 440);
        
        addObject(this.ovenPlus, 900, 385);
        addObject(this.ovenMinus, 800, 385);
        addObject(this.ovenNumLabel, 850, 385);
        
        addObject(this.robberStealPlus, 900, 505);
        addObject(this.robberStealMinus, 800, 500);
        addObject(this.robberStealLabel, 850, 505);
        
        addObject(this.switchButton, 230, 710);
        
        // Set background image for SettingWorld
        setBackground(imageMama);
        
        switchSettings();
    }
    
    /**
     * Act - do whatever the SettingWorld wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        // Switch the setting image, variables, objects to another pizzeria 
        // if mouse clicked switchButton.
        if (Greenfoot.mouseClicked(switchButton)) {
            switchSettings();
        }

        // If is in Mama pizzeria, update and show the label of Mama
        if (isMama) {
            // The maximum number of chef of Mama that player can set is 3, the minimum is 1
            if (Greenfoot.mouseClicked(chefPlus) && this.chefNumMama < 3) {
                this.chefNumMama++;
                this.chefNumLabel.updateLabel(this.chefNumMama);
            }
            if (Greenfoot.mouseClicked(chefMinus) && this.chefNumMama > 1) {
                this.chefNumMama--;
                this.chefNumLabel.updateLabel(this.chefNumMama);
            }
            // The maximum number of cashier of Mama that player can set is 2, the minimum is 1
            if (Greenfoot.mouseClicked(cashierPlus) && this.cashierNumMama < 2) {
                this.cashierNumMama++;
                this.cashierNumLabel.updateLabel(this.cashierNumMama);
            }
            if (Greenfoot.mouseClicked(cashierMinus) && this.cashierNumMama > 1) {
                this.cashierNumMama--;
                this.cashierNumLabel.updateLabel(this.cashierNumMama);
            }
            // The maximum number of money of Mama that player can set is 250, the minimum is 0
            if (Greenfoot.mouseClicked(moneyPlus) && moneyNumMama < 250) {
                this.moneyNumMama += 10;
                this.moneyNumLabel.updateLabel(this.moneyNumMama);
            }
            if (Greenfoot.mouseClicked(moneyMinus) && this.moneyNumMama > 0) {
                this.moneyNumMama -= 10;
                this.moneyNumLabel.updateLabel(this.moneyNumMama);
            }
            // The maximum number of oven of Mama that player can set is 3, the minimum is 1
            if (Greenfoot.mouseClicked(ovenPlus) && ovenNumMama < 3) {
                this.ovenNumMama += 1;
                this.ovenNumLabel.updateLabel(this.ovenNumMama);
            }
            if (Greenfoot.mouseClicked(ovenMinus) && this.ovenNumMama > 1) {
                this.ovenNumMama -= 1;
                this.ovenNumLabel.updateLabel(this.ovenNumMama);
            }
            // The maximum number of robber of Mama can steals is 25, the minimum is 10
            if (Greenfoot.mouseClicked(robberStealPlus) && this.robberStealMama < 25) {
                this.robberStealMama += 5;
                this.robberStealLabel.updateLabel(this.robberStealMama);
            }
            if (Greenfoot.mouseClicked(robberStealMinus) && this.robberStealMama > 10) {
                this.robberStealMama -= 5;
                this.robberStealLabel.updateLabel(this.robberStealMama);
            }
        }
        // If is in Papa pizzeria, update and show the label of Papa
        else {
            // The maximum number of chef of Papa that player can set is 3, the minimum is 1
            if (Greenfoot.mouseClicked(chefPlus) && this.chefNumPapa < 3) {
                this.chefNumPapa++;
                this.chefNumLabel.updateLabel(this.chefNumPapa);
            }
            if (Greenfoot.mouseClicked(chefMinus) && this.chefNumPapa > 1) {
                this.chefNumPapa--;
                this.chefNumLabel.updateLabel(this.chefNumPapa);
            }
            // The maximum number of cashier of Papa that player can set is 2, the minimum is 1
            if (Greenfoot.mouseClicked(cashierPlus) && this.cashierNumPapa < 2) {
                this.cashierNumPapa++;
                this.cashierNumLabel.updateLabel(this.cashierNumPapa);
            }
            if (Greenfoot.mouseClicked(cashierMinus) && this.cashierNumPapa > 1) {
                this.cashierNumPapa--;
                this.cashierNumLabel.updateLabel(this.cashierNumPapa);
            }
            // The maximum number of money of Papa that player can set is 250, the minimum is 0
            if (Greenfoot.mouseClicked(moneyPlus) && this.moneyNumPapa < 250) {
                this.moneyNumPapa += 10;
                this.moneyNumLabel.updateLabel(this.moneyNumPapa);
            }
            if (Greenfoot.mouseClicked(moneyMinus) && this.moneyNumPapa > 0) {
                this.moneyNumPapa -= 10;
                this.moneyNumLabel.updateLabel(this.moneyNumPapa);
            }
            // The maximum number of oven of Papa that player can set is 3, the minimum is 1
            if (Greenfoot.mouseClicked(ovenPlus) && ovenNumPapa < 3) {
                this.ovenNumPapa += 1;
                this.ovenNumLabel.updateLabel(this.ovenNumPapa);
            }
            if (Greenfoot.mouseClicked(ovenMinus) && this.ovenNumPapa > 1) {
                this.ovenNumPapa -= 1;
                this.ovenNumLabel.updateLabel(this.ovenNumPapa);
            }
            // The maximum number of robber of Papa can steals is 25, the minimum is 10
            if (Greenfoot.mouseClicked(robberStealPlus) && this.robberStealPapa < 25) {
                this.robberStealPapa += 5;
                this.robberStealLabel.updateLabel(this.robberStealPapa);
            }
            if (Greenfoot.mouseClicked(robberStealMinus) && this.robberStealPapa > 10) {
                this.robberStealPapa -= 5;
                this.robberStealLabel.updateLabel(this.robberStealPapa);
            }
        }
    }
    
    /**
     * This method is called by the Greenfoot system when the execution has started.
     * Play background sound in loop once the execution has started.
     */
    public void started() {
        Utils.backgroundSound.playLoop();
    }
    
    /**
     * This method is called by the Greenfoot system when the execution has stopped.
     * Pause background sound in loop once the execution has stopped so that when it
     * started again, the sound will play coherently.
     */ 
    public void stopped() {
        Utils.backgroundSound.pause();
    }
    
    /**
     * Switch the background images and the numbers of different pizzerias.
     * Switch the locations of buttons and change the boolean isMama.
     * Only add playButton in Papa pizzeria, remove it when it is in Mama's pizzeria.
     */
    private void switchSettings() {
        if (this.isMama) {
            // If is in Mama, switch variables into Papa's variables.
            setBackground(imagePapa);
            this.switchButton.switchImage(this.isMama);
            this.chefNumLabel.updateLabel(this.chefNumPapa);
            this.cashierNumLabel.updateLabel(this.cashierNumPapa);
            this.moneyNumLabel.updateLabel(this.moneyNumPapa);
            this.ovenNumLabel.updateLabel(this.ovenNumPapa);
            this.robberStealLabel.updateLabel(this.robberStealPapa);
            addObject(playButton, 780, 710);
        }
        else {
            // If is in Papa, switch variables into Mama's variables
            setBackground(imageMama);
            this.switchButton.switchImage(this.isMama);
            this.chefNumLabel.updateLabel(this.chefNumMama);
            this.cashierNumLabel.updateLabel(this.cashierNumMama);
            this.moneyNumLabel.updateLabel(this.moneyNumMama);
            this.ovenNumLabel.updateLabel(this.ovenNumMama);
            this.robberStealLabel.updateLabel(this.robberStealMama);
            removeObject(playButton);
        }
        // Switch the locations of buttons.
        switchLocation();
        // turn the boolean into oppsite.
        this.isMama = !this.isMama;
    }
    
    /**
     * Switch Buttons locations according to the boolean isMama. 
     */
    private void switchLocation() {
        int labelX, plusX, minusX;
        if (isMama) {
            labelX = 445;
            plusX = 505;
            minusX = 395;
        }
        else {
            labelX = 850;
            plusX = 900;
            minusX = 800;
        }
        
        this.chefNumLabel.setLocation(labelX, 275);
        this.cashierNumLabel.setLocation(labelX, 330);
        this.moneyNumLabel.setLocation(labelX, 440);
        this.ovenNumLabel.setLocation(labelX, 385);
        this.robberStealLabel.setLocation(labelX, 505);
        this.chefPlus.setLocation(plusX, 275);
        this.chefMinus.setLocation(minusX, 275);
        this.cashierPlus.setLocation(plusX, 330);
        this.cashierMinus.setLocation(minusX, 330);
        this.moneyPlus.setLocation(plusX, 440);
        this.moneyMinus.setLocation(minusX, 440);
        this.ovenPlus.setLocation(plusX, 385);
        this.ovenMinus.setLocation(minusX, 385);
        this.robberStealPlus.setLocation(plusX, 505);
        this.robberStealMinus.setLocation(minusX, 505);
    }
    
    /**
     * Getter to give access of the chef's number of Mama pizzeria.
     * @return int The chef's number of Mama pizzeria.
     */
    public int getChefNumMama() {
        return this.chefNumMama;
    }
    
    
    /**
     * Getter to give access of the cashier's number of Mama pizzeria.
     * @return int The cashier's number of Mama pizzeria.
     */
    public int getCashierNumMama() {
        return this.cashierNumMama;
    }
    
    
    /**
     * Getter to give access of the money's number of Mama pizzeria.
     * @return int The money's number of Mama pizzeria.
     */
    public int getMoneyNumMama() {
        return this.moneyNumMama;
    }
    

    /**
     * Getter to give access of the oven's number of Mama pizzeria.
     * @return int The oven's number of Mama pizzeria.
     */
    public int getOvenNumMama() {
        return this.ovenNumMama;
    }
    
    /**
     * Getter to give access of the money that robber can steals of Mama pizzeria.
     * @return int The the money that robber can steals of Mama pizzeria.
     */
    public int getRobberStealMama() {
        return this.robberStealMama;
    }
    
    /**
     * Getter to give access of the chef's number of Papa pizzeria.
     * @return int The chef's number of Papa pizzeria.
     */
    public int getChefNumPapa() {
        return this.chefNumPapa;
    }
    
    /**
     * Getter to give access of the cashier's number of Papa pizzeria.
     * @return int The cashier's number of Papa pizzeria.
     */
    public int getCashierNumPapa() {
        return this.cashierNumPapa;
    }
    
    /**
     * Getter to give access of the money's number of Papa pizzeria.
     * @return int The money's number of Papa pizzeria.
     */
    public int getMoneyNumPapa() {
        return this.moneyNumPapa;
    }
    
    /**
     * Getter to give access of the oven's number of Papa pizzeria.
     * @return int The oven's number of Papa pizzeria.
     */
    public int getOvenNumPapa() {
        return this.ovenNumPapa;
    }
    
    /**
     * Getter to give access of the money that robber can steals of Mama pizzeria.
     * @return int The the money that robber can steals of Mama pizzeria.
     */
    public int getRobberStealPapa() {
        return this.robberStealPapa;
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

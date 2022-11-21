import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class SettingWorld here.
 * 
 * @author Yixin Cai
 * @version (a version number or a date)
 */
public class SettingWorld extends World
{
    private GreenfootImage imageMama;
    private GreenfootImage imagePapa;
    
    private PlusButton chefPlus;
    private MinusButton chefMinus;
    private PlusButton cashierPlus;
    private MinusButton cashierMinus;
    private PlusButton moneyPlus;
    private MinusButton moneyMinus;
    private PlusButton robberStealPlus;
    private MinusButton robberStealMinus;
    private SwitchButton switchButton;
    private PlayButton playButton;
    
    private Label chefNumLabel;
    private Label cashierNumLabel;
    private Label moneyNumLabel;
    private Label robberStealLabel;
    private Label volumeLabel;
    
    private final int DEFAULT_CHEF_NUM = 1;
    private final int DEFAULT_CASHIER_NUM = 1;
    private final int DEFAULT_MONEY_NUM = 0;
    private final int DEFAULT_ROBBER_STEAL = 15;
    
    private int chefNumMama;
    private int cashierNumMama;
    private int moneyNumMama;
    private int robberStealMama;
    
    private int chefNumPapa;
    private int cashierNumPapa;
    private int moneyNumPapa;
    private int robberStealPapa;
    
    private boolean isMama;
    
    /**
     * Constructor for objects of class SettingWorld.
     * 
     */
    public SettingWorld()
    {    
        // Create a new world with 1024x800 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1);
        
        this.isMama = false;
        
        this.chefNumMama = DEFAULT_CHEF_NUM;
        this.cashierNumMama = DEFAULT_CASHIER_NUM;
        this.moneyNumMama = DEFAULT_MONEY_NUM;
        this.robberStealMama = DEFAULT_ROBBER_STEAL;
        
        this.chefNumPapa = DEFAULT_CHEF_NUM;
        this.cashierNumPapa = DEFAULT_CASHIER_NUM;
        this.moneyNumPapa = DEFAULT_MONEY_NUM;
        this.robberStealPapa = DEFAULT_ROBBER_STEAL;
        
        this.imageMama = new GreenfootImage("settingScreenMAMA.png");
        this.imagePapa = new GreenfootImage("settingScreenPAPA.png");
        
        setBackground(imageMama);
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
        
        this.robberStealPlus = new PlusButton();
        this.robberStealMinus = new MinusButton();
        this.robberStealLabel = new Label("");
        
        volumeLabel = new Label(Utils.volume + "%");
        addObject(new VolumeButton(false), 850, 40);
        addObject(new VolumeButton(true), 970, 40);
        addObject(volumeLabel,910, 40);
        
        addObject(this.chefPlus, 900, 310);
        addObject(this.chefMinus, 800, 310);
        addObject(this.chefNumLabel, 850, 310);
        
        addObject(this.cashierPlus, 900, 365);
        addObject(this.cashierMinus, 800, 365);
        addObject(this.cashierNumLabel, 850, 365);
        
        addObject(this.moneyPlus, 900, 425);
        addObject(this.moneyMinus, 800, 425);
        addObject(this.moneyNumLabel, 850, 425);
        
        addObject(this.robberStealPlus, 900, 495);
        addObject(this.robberStealMinus, 800, 500);
        addObject(this.robberStealLabel, 850, 495);
        
        addObject(this.switchButton, 230, 710);
        
        switchSettings();
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(switchButton)) {
            switchSettings();
        }

        if (isMama) {
            if (Greenfoot.mouseClicked(chefPlus) && this.chefNumMama < 3) {
                this.chefNumMama++;
                this.chefNumLabel.updateLabel(this.chefNumMama);
            }
            if (Greenfoot.mouseClicked(chefMinus) && this.chefNumMama > 1) {
                this.chefNumMama--;
                this.chefNumLabel.updateLabel(this.chefNumMama);
            }
            if (Greenfoot.mouseClicked(cashierPlus) && this.cashierNumMama < 2) {
                this.cashierNumMama++;
                this.cashierNumLabel.updateLabel(this.cashierNumMama);
            }
            if (Greenfoot.mouseClicked(cashierMinus) && this.cashierNumMama > 1) {
                this.cashierNumMama--;
                this.cashierNumLabel.updateLabel(this.cashierNumMama);
            }
            if (Greenfoot.mouseClicked(moneyPlus) && moneyNumMama < 250) {
                this.moneyNumMama += 10;
                this.moneyNumLabel.updateLabel(this.moneyNumMama);
            }
            if (Greenfoot.mouseClicked(moneyMinus) && this.moneyNumMama > 0) {
                this.moneyNumMama -= 10;
                this.moneyNumLabel.updateLabel(this.moneyNumMama);
            }
            if (Greenfoot.mouseClicked(robberStealPlus) && this.robberStealMama < 25) {
                this.robberStealMama += 5;
                this.robberStealLabel.updateLabel(this.robberStealMama);
            }
            if (Greenfoot.mouseClicked(robberStealMinus) && this.robberStealMama > 10) {
                this.robberStealMama -= 5;
                this.robberStealLabel.updateLabel(this.robberStealMama);
            }
        }
        else {
            if (Greenfoot.mouseClicked(chefPlus) && this.chefNumPapa < 3) {
                this.chefNumPapa++;
                this.chefNumLabel.updateLabel(this.chefNumPapa);
            }
            if (Greenfoot.mouseClicked(chefMinus) && this.chefNumPapa > 1) {
                this.chefNumPapa--;
                this.chefNumLabel.updateLabel(this.chefNumPapa);
            }
            if (Greenfoot.mouseClicked(cashierPlus) && this.cashierNumPapa < 2) {
                this.cashierNumPapa++;
                this.cashierNumLabel.updateLabel(this.cashierNumPapa);
            }
            if (Greenfoot.mouseClicked(cashierMinus) && this.cashierNumPapa > 1) {
                this.cashierNumPapa--;
                this.cashierNumLabel.updateLabel(this.cashierNumPapa);
            }
            if (Greenfoot.mouseClicked(moneyPlus) && this.moneyNumPapa < 250) {
                this.moneyNumPapa += 10;
                this.moneyNumLabel.updateLabel(this.moneyNumPapa);
            }
            if (Greenfoot.mouseClicked(moneyMinus) && this.moneyNumPapa > 0) {
                this.moneyNumPapa -= 10;
                this.moneyNumLabel.updateLabel(this.moneyNumPapa);
            }
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
    
    public void started() {
        Utils.backgroundSound.playLoop();
    }
    
    public void stopped() {
        Utils.backgroundSound.pause();
    }
    
    private void switchSettings() {
        if (this.isMama) {
            setBackground(imagePapa);
            this.switchButton.switchImage(this.isMama);
            this.chefNumLabel.updateLabel(this.chefNumPapa);
            this.cashierNumLabel.updateLabel(this.cashierNumPapa);
            this.moneyNumLabel.updateLabel(this.moneyNumPapa);
            this.robberStealLabel.updateLabel(this.robberStealPapa);
            addObject(playButton, 780, 710);
        }
        else {
            setBackground(imageMama);
            this.switchButton.switchImage(this.isMama);
            this.chefNumLabel.updateLabel(this.chefNumMama);
            this.cashierNumLabel.updateLabel(this.cashierNumMama);
            this.moneyNumLabel.updateLabel(this.moneyNumMama);
            this.robberStealLabel.updateLabel(this.robberStealMama);
            removeObject(playButton);
        }
        
        switchLocation();
        
        this.isMama = !this.isMama;
    }
    
    private void switchLocation() {
        int labelX, plusX, minusX;
        if (isMama) {
            labelX = 445;
            plusX = 495;
            minusX = 395;
        }
        else {
            labelX = 850;
            plusX = 900;
            minusX = 800;
        }
        
        this.chefNumLabel.setLocation(labelX, 310);
        this.cashierNumLabel.setLocation(labelX, 365);
        this.moneyNumLabel.setLocation(labelX, 425);
        this.robberStealLabel.setLocation(labelX, 495);
        this.chefPlus.setLocation(plusX, 310);
        this.chefMinus.setLocation(minusX, 310);
        this.cashierPlus.setLocation(plusX, 365);
        this.cashierMinus.setLocation(minusX, 365);
        this.moneyPlus.setLocation(plusX, 425);
        this.moneyMinus.setLocation(minusX, 425);
        this.robberStealPlus.setLocation(plusX, 495);
        this.robberStealMinus.setLocation(minusX, 495);
    }
    
    public int getChefNumMama() {
        return this.chefNumMama;
    }
    
    public int getCashierNumMama() {
        return this.cashierNumMama;
    }
    
    public int getMoneyNumMama() {
        return this.moneyNumMama;
    }
    
    public int getRobberStealMama() {
        return this.robberStealMama;
    }
    
    public int getChefNumPapa() {
        return this.chefNumPapa;
    }
    
    public int getCashierNumPapa() {
        return this.cashierNumPapa;
    }
    
    public int getMoneyNumPapa() {
        return this.moneyNumPapa;
    }
    
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

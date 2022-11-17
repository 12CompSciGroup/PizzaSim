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
    
    private SettingButton chefAdd;
    private SettingButton chefMinus;
    private SettingButton cashierAdd;
    private SettingButton cashierMinus;
    private SettingButton moneyAdd;
    private SettingButton moneyMinus;
    private SettingButton robberCostAdd;
    private SettingButton robberCostMinus;
    private SettingButton switchButton;
    private PlayButton playButton;
    private Label chefNumLabel;
    private Label cashierNumLabel;
    private Label moneyNumLabel;
    private Label robberCostLabel;
    
    private GreenfootImage switchImageRight;
    private GreenfootImage switchDownImageRight;
    private GreenfootImage switchImageLeft;
    private GreenfootImage switchDownImageLeft;
    private GreenfootImage playImage;
    private GreenfootImage playDownImage;
    
    private final int DEFAULT_CHEF_NUM = 2;
    private final int DEFAULT_CASHIER_NUM = 1;
    private final int DEFAULT_MONEY_NUM = 30;
    private final int DEFAULT_ROBBER_COST = 10;
    
    private int chefNumMama;
    private int cashierNumMama;
    private int moneyNumMama;
    private int robberCostMama;
    
    private int chefNumPapa;
    private int cashierNumPapa;
    private int moneyNumPapa;
    private int robberCostPapa;
    
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
        this.robberCostMama = DEFAULT_ROBBER_COST;
        
        this.chefNumPapa = DEFAULT_CHEF_NUM;
        this.cashierNumPapa = DEFAULT_CASHIER_NUM;
        this.moneyNumPapa = DEFAULT_MONEY_NUM;
        this.robberCostPapa = DEFAULT_ROBBER_COST;
        
        this.imageMama = new GreenfootImage("settingScreenMAMA.png");
        this.imagePapa = new GreenfootImage("settingScreenPAPA.png");
        
        setBackground(imageMama);
        
        GreenfootImage plusImage = new GreenfootImage("plusIcon.png");
        GreenfootImage minusImage = new GreenfootImage("minusIcon.png");
        GreenfootImage plusDownImage = new GreenfootImage("plusDownIcon.png");
        GreenfootImage minusDownImage = new GreenfootImage("minusDownIcon.png");
        
        switchImageRight = new GreenfootImage("rightButton.png");
        switchDownImageRight = new GreenfootImage("rightButtonDown.png");
        switchImageLeft = new GreenfootImage("leftButton.png");
        switchDownImageLeft = new GreenfootImage("leftButtonDown.png");
        playImage = new GreenfootImage("play.png");
        playDownImage = new GreenfootImage("playDown.png");
        
        this.switchButton = new SettingButton(switchImageRight, switchDownImageRight);
        this.playButton = new PlayButton(playImage, playDownImage);
        
        this.chefAdd = new SettingButton(plusImage, plusDownImage);
        this.chefMinus = new SettingButton(minusImage, minusDownImage);
        this.chefNumLabel = new Label("");
        
        this.cashierAdd = new SettingButton(plusImage, plusDownImage);
        this.cashierMinus = new SettingButton(minusImage, minusDownImage);
        this.cashierNumLabel = new Label("");
        
        this.moneyAdd = new SettingButton(plusImage, plusDownImage);
        this.moneyMinus = new SettingButton(minusImage, minusDownImage);
        this.moneyNumLabel = new Label("");
        
        this.robberCostAdd = new SettingButton(plusImage, plusDownImage);
        this.robberCostMinus = new SettingButton(minusImage, minusDownImage);
        this.robberCostLabel = new Label("");
        
        addObject(this.chefAdd, 900, 300);
        addObject(this.chefMinus, 800, 300);
        addObject(this.chefNumLabel, 850, 300);
        
        addObject(this.cashierAdd, 900, 380);
        addObject(this.cashierMinus, 800, 380);
        addObject(this.cashierNumLabel, 850, 380);
        
        addObject(this.moneyAdd, 900, 460);
        addObject(this.moneyMinus, 800, 460);
        addObject(this.moneyNumLabel, 850, 460);
        
        addObject(this.robberCostAdd, 900, 540);
        addObject(this.robberCostMinus, 800, 540);
        addObject(this.robberCostLabel, 850, 540);
        
        addObject(this.switchButton, 200, 710);
        
        switchSettings();
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(switchButton)) {
            switchSettings();
        }

        if (isMama) {
            if (Greenfoot.mouseClicked(chefAdd)) {
                this.chefNumMama++;
                this.chefNumLabel.updateLabel(this.chefNumMama);
            }
            if (Greenfoot.mouseClicked(chefMinus) && this.chefNumMama > 1) {
                this.chefNumMama--;
                this.chefNumLabel.updateLabel(this.chefNumMama);
            }
            if (Greenfoot.mouseClicked(cashierAdd)) {
                this.cashierNumMama++;
                this.cashierNumLabel.updateLabel(this.cashierNumMama);
            }
            if (Greenfoot.mouseClicked(cashierMinus) && this.cashierNumMama > 1) {
                this.cashierNumMama--;
                this.cashierNumLabel.updateLabel(this.cashierNumMama);
            }
            if (Greenfoot.mouseClicked(moneyAdd)) {
                this.moneyNumMama += 10;
                this.moneyNumLabel.updateLabel(this.moneyNumMama);
            }
            if (Greenfoot.mouseClicked(moneyMinus) && this.moneyNumMama > 10) {
                this.moneyNumMama -= 10;
                this.moneyNumLabel.updateLabel(this.moneyNumMama);
            }
            if (Greenfoot.mouseClicked(robberCostAdd)) {
                this.robberCostMama += 5;
                this.robberCostLabel.updateLabel(this.robberCostMama);
            }
            if (Greenfoot.mouseClicked(robberCostMinus) && this.robberCostMama > 5) {
                this.robberCostMama -= 5;
                this.robberCostLabel.updateLabel(this.robberCostMama);
            }
        }
        else {
            if (Greenfoot.mouseClicked(chefAdd)) {
                this.chefNumPapa++;
                this.chefNumLabel.updateLabel(this.chefNumPapa);
            }
            if (Greenfoot.mouseClicked(chefMinus) && this.chefNumPapa > 1) {
                this.chefNumPapa--;
                this.chefNumLabel.updateLabel(this.chefNumPapa);
            }
            if (Greenfoot.mouseClicked(cashierAdd)) {
                this.cashierNumPapa++;
                this.cashierNumLabel.updateLabel(this.cashierNumPapa);
            }
            if (Greenfoot.mouseClicked(cashierMinus) && this.cashierNumPapa > 1) {
                this.cashierNumPapa--;
                this.cashierNumLabel.updateLabel(this.cashierNumPapa);
            }
            if (Greenfoot.mouseClicked(moneyAdd)) {
                this.moneyNumPapa += 10;
                this.moneyNumLabel.updateLabel(this.moneyNumPapa);
            }
            if (Greenfoot.mouseClicked(moneyMinus) && this.moneyNumPapa > 10) {
                this.moneyNumPapa -= 10;
                this.moneyNumLabel.updateLabel(this.moneyNumPapa);
            }
            if (Greenfoot.mouseClicked(robberCostAdd)) {
                this.robberCostPapa += 5;
                this.robberCostLabel.updateLabel(this.robberCostPapa);
            }
            if (Greenfoot.mouseClicked(robberCostMinus) && this.robberCostPapa > 5) {
                this.robberCostPapa -= 5;
                this.robberCostLabel.updateLabel(this.robberCostPapa);
            }
        }
    }
    
    public void started() {
        BackgroundSound.getInstance().playSound();
    }
    
    public void stopped() {
        BackgroundSound.getInstance().pauseSound();
    }
    
    private void switchSettings() {
        // TODO: update (buttons and lables) locations
        if (!this.isMama) {
            setBackground(imageMama);
            this.switchButton.updateImages(switchImageRight, switchDownImageRight);
            this.chefNumLabel.updateLabel(this.chefNumMama);
            this.cashierNumLabel.updateLabel(this.cashierNumMama);
            this.moneyNumLabel.updateLabel(this.moneyNumMama);
            this.robberCostLabel.updateLabel(this.robberCostMama);
            
            removeObject(playButton);
        }
        else {
            setBackground(imagePapa);
            this.switchButton.updateImages(switchImageLeft, switchDownImageLeft);
            this.chefNumLabel.updateLabel(this.chefNumPapa);
            this.cashierNumLabel.updateLabel(this.cashierNumPapa);
            this.moneyNumLabel.updateLabel(this.moneyNumPapa);
            this.robberCostLabel.updateLabel(this.robberCostPapa);
            addObject(playButton, 800, 720);
        }
        
        switchLocation();
        
        this.isMama = !this.isMama;
    }
    
    private void switchLocation() {
        if (isMama) {
            this.chefNumLabel.setLocation(445, 300);
            this.cashierNumLabel.setLocation(445, 380);
            this.moneyNumLabel.setLocation(445, 460);
            this.robberCostLabel.setLocation(445, 540);
            this.chefAdd.setLocation(495, 300);
            this.chefMinus.setLocation(395, 300);
            this.cashierAdd.setLocation(495, 380);
            this.cashierMinus.setLocation(395, 380);
            this.moneyAdd.setLocation(495, 460);
            this.moneyMinus.setLocation(395, 460);
            this.robberCostAdd.setLocation(495, 540);
            this.robberCostMinus.setLocation(395, 540);
        }
        else {
            this.chefNumLabel.setLocation(850, 300);
            this.cashierNumLabel.setLocation(850, 380);
            this.moneyNumLabel.setLocation(850, 460);
            this.robberCostLabel.setLocation(850, 540);
            this.chefAdd.setLocation(900, 300);
            this.chefMinus.setLocation(800, 300);
            this.cashierAdd.setLocation(900, 380);
            this.cashierMinus.setLocation(800, 380);
            this.moneyAdd.setLocation(900, 460);
            this.moneyMinus.setLocation(800, 460);
            this.robberCostAdd.setLocation(900, 540);
            this.robberCostMinus.setLocation(800, 540);
        }
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
    
    public int getRobberCostMama() {
        return this.robberCostMama;
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
    
    public int getRobberCostPapa() {
        return this.robberCostPapa;
    }
}

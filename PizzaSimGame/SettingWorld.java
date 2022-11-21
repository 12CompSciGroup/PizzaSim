import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SettingWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SettingWorld extends World
{
    private GreenfootImage image;
    
    SettingButton chefAdd;
    SettingButton chefMinus;
    SettingButton cashierAdd;
    SettingButton cashierMinus;
    SettingButton moneyAdd;
    SettingButton moneyMinus;
    SettingButton robberCostAdd;
    SettingButton robberCostMinus;
    Label chefNumLabel;
    Label chefLabel;
    Label cashierNumLabel;
    Label cashierLabel;
    Label moneyNumLabel;
    Label moneyLabel;
    Label robberCostLabel;
    Label robberLabel;
    
    private int chefNum;
    private int cashierNum;
    private int moneyNum;
    private int robberCost;
    
    /**
     * Constructor for objects of class SettingWorld.
     * 
     */
    public SettingWorld()
    {    
        // Create a new world with 1024x800 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1);
        
        this.chefNum = 2;
        this.cashierNum = 1;
        this.moneyNum = 10;
        this.robberCost = 5;
        
        GreenfootImage plusImage = new GreenfootImage("plusIcon.png");
        GreenfootImage minusImage = new GreenfootImage("minusIcon.png");
        GreenfootImage plusDownImage = new GreenfootImage("plusDownIcon.png");
        GreenfootImage minusDownImage = new GreenfootImage("minusDownIcon.png");
        GreenfootImage playImage = new GreenfootImage("play.png");
        GreenfootImage playDownImage = new GreenfootImage("playDown.png");
        
        this.chefAdd = new SettingButton(plusImage, plusDownImage);
        this.chefMinus = new SettingButton(minusImage, minusDownImage);
        this.chefNumLabel = new Label(this.chefNum);
        this.chefLabel = new Label("Chef's Number");
        
        this.cashierAdd = new SettingButton(plusImage, plusDownImage);
        this.cashierMinus = new SettingButton(minusImage, minusDownImage);
        this.cashierNumLabel = new Label(this.cashierNum);
        this.cashierLabel = new Label("Cashier's Number");
        
        this.moneyAdd = new SettingButton(plusImage, plusDownImage);
        this.moneyMinus = new SettingButton(minusImage, minusDownImage);
        this.moneyNumLabel = new Label(this.moneyNum);
        this.moneyLabel = new Label("Starting Money");
        
        this.robberCostAdd = new SettingButton(plusImage, plusDownImage);
        this.robberCostMinus = new SettingButton(minusImage, minusDownImage);
        this.robberCostLabel = new Label(this.robberCost);
        this.robberLabel = new Label("Cost of One Robber");
        
        addObject(this.chefAdd, getWidth() / 2 + 200, getHeight() - 500);
        addObject(this.chefMinus, getWidth() / 2 + 100, getHeight() - 500);
        addObject(this.chefNumLabel, getWidth() / 2 + 150, getHeight() - 500);
        addObject(this.chefLabel, getWidth() / 2 - 100, getHeight() - 500);
        
        addObject(this.cashierAdd, getWidth() / 2 + 200, getHeight() - 400);
        addObject(this.cashierMinus, getWidth() / 2 + 100, getHeight() - 400);
        addObject(this.cashierNumLabel, getWidth() / 2 + 150, getHeight() - 400);
        addObject(this.cashierLabel, getWidth() / 2 - 135, getHeight() - 400);
        
        addObject(this.moneyAdd, getWidth() / 2 + 200, getHeight() - 300);
        addObject(this.moneyMinus, getWidth() / 2 +100, getHeight() - 300);
        addObject(this.moneyNumLabel, getWidth() / 2 + 150, getHeight() - 300);
        addObject(this.moneyLabel, getWidth() / 2 - 100, getHeight() - 300);
        
        addObject(this.robberCostAdd, getWidth() / 2 + 200, getHeight() - 200);
        addObject(this.robberCostMinus, getWidth() / 2 +100, getHeight() - 200);
        addObject(this.robberCostLabel, getWidth() / 2 + 150, getHeight() - 200);
        addObject(this.robberLabel, getWidth() / 2 - 150, getHeight() - 200);
        
        addObject(new PlayButton(playImage, playDownImage), getWidth() / 2, getHeight() - 100);
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(chefAdd)) {
            this.chefNum++;
            this.chefNumLabel.updateLabel(this.chefNum);
        }
        if (Greenfoot.mouseClicked(chefMinus) && this.chefNum > 1) {
            this.chefNum--;
            this.chefNumLabel.updateLabel(this.chefNum);
        }
        if (Greenfoot.mouseClicked(cashierAdd)) {
            this.cashierNum++;
            this.cashierNumLabel.updateLabel(this.cashierNum);
        }
        if (Greenfoot.mouseClicked(cashierMinus) && this.cashierNum > 1) {
            this.cashierNum--;
            this.cashierNumLabel.updateLabel(this.cashierNum);
        }
        if (Greenfoot.mouseClicked(moneyAdd)) {
            this.moneyNum += 10;
            this.moneyNumLabel.updateLabel(this.moneyNum);
        }
        if (Greenfoot.mouseClicked(moneyMinus) && this.moneyNum > 10) {
            this.moneyNum -= 10;
            this.moneyNumLabel.updateLabel(this.moneyNum);
        }
        if (Greenfoot.mouseClicked(robberCostAdd)) {
            this.robberCost += 5;
            this.robberCostLabel.updateLabel(this.robberCost);
        }
        if (Greenfoot.mouseClicked(robberCostMinus) && this.robberCost > 5) {
            this.robberCost -= 5;
            this.robberCostLabel.updateLabel(this.robberCost);
        }
    }
    
    public void started() {
        BackgroundSound.getInstance().playSound();
    }
    
    public void stopped() {
        BackgroundSound.getInstance().pauseSound();
    }
    
    public int getChefNum() {
        return this.chefNum;
    }
    
    public int getCashierNum() {
        return this.cashierNum;
    }
    
    public int getMoneyNum() {
        return this.moneyNum;
    }
    
    public int getRobberCost() {
        return this.robberCost;
    }
    
}

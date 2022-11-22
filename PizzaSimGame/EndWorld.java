import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * EndWorld is an class that show the final result fo the simulation and display
 * confetti effect.
 * 
 * @author Yixin Cai & Gloria Chan
 * @version (a version number or a date)
 */
public class EndWorld extends World
{
    private int result;
    private int level;
    private int money;
    private GreenfootImage backgroundImages[];
    private GreenfootImage winTexts[];
    private FlashingText winFlashingText;
    private Label levelNumLabel;
    private Label amountMoneyLabel;
    private TryAgainButton tryAgainButton;
    private Confetti confetti;
    private GreenfootSound winSound;
    
    /**
     * Constructor for objects of class EndWorld.
     * 
     */
    public EndWorld(int result, int level, int money)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 800, 1); 
        this.result = result;
        this.level = level;
        this.money = money;
        winTexts = new GreenfootImage[] {
            new GreenfootImage("mamaWinText.png"),
            new GreenfootImage("papaWinText.png")
        };
        backgroundImages = new GreenfootImage[] {
            new GreenfootImage("mamaWin.png"),
            new GreenfootImage("papaWin.png")
        };
        this.levelNumLabel = new Label(level, 50, new Color(253, 218, 13));
        this.amountMoneyLabel = new Label (money, 50, new Color(253, 218, 13));
        this.tryAgainButton = new TryAgainButton();
        this.confetti = new Confetti();
        winSound = new GreenfootSound("win.wav");
        winSound.play();
        if (result == 0 ) {
            setBackground(backgroundImages[result]);
            winFlashingText = new FlashingText(new GreenfootImage(winTexts[result]));
        }
        else {
            setBackground(backgroundImages[result]);
            winFlashingText = new FlashingText(new GreenfootImage(winTexts[result]));
        }
        
        addObject(levelNumLabel, 450, 620);
        addObject(amountMoneyLabel, 450, 705);
        addObject(winFlashingText, 296, 150);
        addObject(confetti, 512, 400);
        addObject(tryAgainButton, 830, 750);
    }
    
    public void started() {
        Utils.backgroundSound.playLoop();
        winSound.play();
    }
    
    public void stopped() {
        Utils.backgroundSound.pause();
        winSound.pause();
    }
}


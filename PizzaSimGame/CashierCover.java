import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CashierTill here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CashierCover extends LevelUp
{
    Color rose = new Color(225, 148, 148);
    Color tileLight = new Color(251, 247, 240);
    Color tileDark = new Color(247, 236, 221);
    
    public CashierCover(String store)
    {
        GreenfootImage cover = new GreenfootImage("cashier cover " + store + ".png");
        setImage(cover);
    }
    
    /**
     * Act - do whatever the CashierTill wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}

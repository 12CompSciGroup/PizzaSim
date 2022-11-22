import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CashierTill here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CashierCover extends LevelUp
{
    public CashierCover(String store)
    {
        GreenfootImage cover = new GreenfootImage("cashier cover " + store + ".png");
        setImage(cover);
    }
}

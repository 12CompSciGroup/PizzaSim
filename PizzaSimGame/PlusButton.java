import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AddButton here.
 * 
 * @author Yixin Cai
 * @version (a version number or a date)
 */
public class PlusButton extends Button
{
    public PlusButton() {
        super();
        
        this.image = new GreenfootImage("plusButton.png");
        this.downImage = new GreenfootImage("plusButtonDown.png");
        this.hoverImage = new GreenfootImage("plusButtonHover.png");
    }
    
    protected void onClick() {   
    }
}

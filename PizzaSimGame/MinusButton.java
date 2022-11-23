import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A button with Minus image. To be clicked to reduce value in SettingWorld
 * 
 * @author Yixin Cai
 * @version (a version number or a date)
 */
public class MinusButton extends Button
{
    public MinusButton() {
        super();
        
        this.image = new GreenfootImage("minusButton.png");
        this.downImage = new GreenfootImage("minusButtonDOWN.png");
        this.hoverImage = new GreenfootImage("minusButtonHOVER.png");
    }
    
    protected void onClick() {   
    }
}

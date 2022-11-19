import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MinusButton here.
 * 
 * @author Yixin Cai
 * @version (a version number or a date)
 */
public class MinusButton extends Button
{
    public MinusButton() {
        super();
        
        this.image = new GreenfootImage("minusButton.png");
        this.downImage = new GreenfootImage("minusButtonDown.png");
        this.hoverImage = new GreenfootImage("minusButtonHover.png");
    }
    
    protected void onClick() {   
    }
}

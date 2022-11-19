import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SwitchButton here.
 * 
 * @author Yixin Cai
 * @version (a version number or a date)
 */
public class SwitchButton extends Button
{
    GreenfootImage switchImageRight = new GreenfootImage("rightButton.png");
    GreenfootImage switchDownImageRight = new GreenfootImage("rightButtonDown.png");
    GreenfootImage switchImageHoverRight = new GreenfootImage("rightButtonHover.png");
    GreenfootImage switchImageLeft = new GreenfootImage("leftButton.png");
    GreenfootImage switchDownImageLeft = new GreenfootImage("leftButtonDown.png");
    GreenfootImage switchImageHoverLeft = new GreenfootImage("leftButtonHover.png");
    
    public SwitchButton() {
        super();
        
        this.image = new GreenfootImage("rightButton.png");
        this.downImage = new GreenfootImage("rightButtonDown.png");
        this.hoverImage = new GreenfootImage("rightButtonHover.png");
    }
    
    public void switchImage(boolean toLeft) {
        if (toLeft) {
            this.image = switchImageLeft;
            this.downImage = switchDownImageLeft;
            this.hoverImage = switchImageHoverLeft;
        }
        else {
            this.image = switchImageRight;
            this.downImage = switchDownImageRight;
            this.hoverImage = switchImageHoverRight;
        }
    }
    
    protected void onClick() {   
    }
}

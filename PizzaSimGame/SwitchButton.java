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
    GreenfootImage switchDownImageRight = new GreenfootImage("rightButtonDOWN.png");
    GreenfootImage switchImageHoverRight = new GreenfootImage("rightButtonHOVER.png");
    GreenfootImage switchImageLeft = new GreenfootImage("leftButton.png");
    GreenfootImage switchDownImageLeft = new GreenfootImage("leftButtonDOWN.png");
    GreenfootImage switchImageHoverLeft = new GreenfootImage("leftButtonHOVER.png");
    
    public SwitchButton() {
        super();
        
        this.image = switchImageLeft;
        this.downImage = switchDownImageLeft;
        this.hoverImage = switchImageHoverLeft;
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

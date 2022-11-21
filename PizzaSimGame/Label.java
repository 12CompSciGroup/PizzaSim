import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class Label represents a text label on the world.
 * 
 * @author Yixin Cai
 * @version 2022-11-10
 */

public class Label extends Actor {
    // instance variables
    private String labelText;
    private int labelSize;
    private Color labelColor;
    
    /**
     * Constructors of Label
     */
    public Label(int labelText) {
        this(labelText + "");
    }
    
    /**
     * Constructors of Label
     */
    public Label(String labelText) {
        this(labelText, 40);
    }
    
    /**
     * Constructors of Label
     */
    public Label(String labelText, int labelSize) {
        this(labelText, labelSize, Color.BLACK);
    }
    
    /**
     * King Constructors of Label
     */
    public Label(String labelText, int labelSize, Color labelColor) {
        this.labelText = labelText;
        this.labelSize = labelSize;
        this.labelColor = labelColor;
        setLabelImage();
    }
    
    /**
     * Mehtod to set the text to image
     */
    private void setLabelImage() {
        setImage(new GreenfootImage(this.labelText, this.labelSize, this.labelColor, new Color(0, 0, 0, 0)));
    }
    
    /**
     * Method to update the Label
     */
    public void updateLabel(int labelText) {
        this.labelText = labelText + "";
        setLabelImage();
    }
    
    /**
     * Method to update the Label
     */
    public void updateLabel(String labelText) {
        this.labelText = labelText;
        setLabelImage();
    }
}

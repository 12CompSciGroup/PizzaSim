import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Order here.
 * 
 * @Yuxin Li (your name) 
 * @version (a version number or a date)
 * 
 * chatBox image by Sallie from: 
 * https://www.vhv.rs/viewpic/oobJ_pixel-chat-bubble-png-transparent-png/
 */
public class Order extends Actor
{
    /**
     * Act - do whatever the Order wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private String[] toppings;
    private GreenfootImage chatBox=getImage();
    private GreenfootImage topping;
    private GreenfootImage dough;
    private int a;
    public Order(String[] strings){
        toppings=strings;
        dough=new GreenfootImage("dough.png");
        chatBox.drawImage(dough, 25, 0);
        a=0;
        
    }
    /**
     * draw the order picture above the head of each customer
     */
    public void drawOrder(){
        for(int i=0; i<toppings.length; i++){
            topping = new GreenfootImage(toppings[i] + ".png");
            chatBox.drawImage(topping, 25, 0);
        }
    }
    public void addedToWorld(World w){
        drawOrder();
    }
}

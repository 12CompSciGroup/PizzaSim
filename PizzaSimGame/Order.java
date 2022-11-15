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
    private String sauce;
    private GreenfootImage chatBox=getImage();
    private GreenfootImage topping;
    private GreenfootImage theSauce;
    private GreenfootImage dough;
    private Customer customer;
    private int a;
    public Order(String sauceType, String[] toppingTypes, Customer theCustomer){
        toppings=toppingTypes;
        sauce=sauceType;
        dough=new GreenfootImage("dough.png");
        chatBox.drawImage(dough, 12, 2);
        customer=theCustomer;
        a=0;
        
    }
    /**
     * move the order 
     */
    public void act(){
        moveMe();
    }
    public void addedToWorld(World w){
        drawOrder();
    }
    /**
     * draw the order picture above the head of each customer
     */
    public void drawOrder(){
        theSauce=new GreenfootImage("sauce"+sauce+".png");
        chatBox.drawImage(theSauce,12,2);
        for(int i=0; i<toppings.length; i++){
            topping = new GreenfootImage(toppings[i] + ".png");
            chatBox.drawImage(topping, 12, 2);
        }
    }
    
    /**
     * move the order with the customer
     */
    public void moveMe(){
        setLocation(customer.getX(), customer.getY()-(customer.getImage()).getHeight()/2-20);
    }
}
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
    private String[] toppings = new String[1];
    private String sauce;
    private GreenfootImage chatBox = getImage();
    private GreenfootImage topping, theSauce, dough, cheese, crust = new GreenfootImage ("cooked.png");
    private boolean hasCheese;
    private Customer customer;
    private boolean madePizza = false;
    private KitchenCounter kitchen1, kitchen2, kitchen3;

    private int store; 
    private int price = 5;
    
    public Order(String sauceType, boolean cheese, String[] toppingTypes, Customer customer, int store){
        this.store = store;
        toppings = toppingTypes;
        this.customer = customer;
        sauce = sauceType;
        hasCheese = cheese;
        this.cheese = new GreenfootImage("cheese.png");
        dough = new GreenfootImage("pizzaBase.png");
        crust.setTransparency(90);
        chatBox.scale(60, 70);
        chatBox.drawImage(dough, 12, 7);
    }
    
    public void act(){
        setLocation(customer.getX() + 22, customer.getY() - (customer.getImage().getHeight() / 2) - (this.getImage().getHeight() / 2) + 16);
        
        if (customer.getPickedUp()){
            getWorld().removeObject(this);
        }
    }

    /**
     * draw the order picture above the head of each customer
     */
    public void drawOrder(){
        theSauce = new GreenfootImage("sauce" + sauce + ".png");
        chatBox.drawImage(theSauce, 12, 7);
        
        if(hasCheese){
            chatBox.drawImage(cheese, 12, 7);
        }
        
        for(int i = 0; i < toppings.length - 1; i++){
            topping = new GreenfootImage(toppings[i] + ".png");
            chatBox.drawImage(topping, 12, 7);
        }
        
        chatBox.drawImage(crust, 12, 7);
    }
    
    public void makePizza()
    {
        if (store == Utils.MAMA)
        {
            kitchen1 = (KitchenCounter)getWorld().getObjectsAt(Utils.kitchenCounterXLeft, Utils.kitchenCounterY1, KitchenCounter.class).get(0);
            kitchen2 = (KitchenCounter)getWorld().getObjectsAt(Utils.kitchenCounterXLeft, Utils.kitchenCounterY2, KitchenCounter.class).get(0);
            kitchen3 = (KitchenCounter)getWorld().getObjectsAt(Utils.kitchenCounterXLeft, Utils.kitchenCounterY3, KitchenCounter.class).get(0);
        } 
        if(store == Utils.PAPA)
        {
            kitchen1 = (KitchenCounter)getWorld().getObjectsAt(Utils.kitchenCounterXRight, Utils.kitchenCounterY1, KitchenCounter.class).get(0);
            kitchen2 = (KitchenCounter)getWorld().getObjectsAt(Utils.kitchenCounterXRight, Utils.kitchenCounterY2, KitchenCounter.class).get(0);
            kitchen3 = (KitchenCounter)getWorld().getObjectsAt(Utils.kitchenCounterXRight, Utils.kitchenCounterY3, KitchenCounter.class).get(0);
        }
        
        if(kitchen1.checkCanMakePizza())
        {
            getWorld().addObject(new Pizza(toppings, sauce, hasCheese), kitchen1.getX(), kitchen1.getY());
        }
        else if(kitchen2.checkCanMakePizza())
        {
            getWorld().addObject(new Pizza(toppings, sauce, hasCheese), kitchen2.getX(), kitchen2.getY());
        }
        else if(kitchen3.checkCanMakePizza())
        {
            getWorld().addObject(new Pizza(toppings, sauce, hasCheese), kitchen3.getX(), kitchen3.getY());
        }
    }
    
    public void addedToWorld(World w){
        drawOrder();
        makePizza();
    }
}
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
    private GreenfootImage chatBox = getImage();
    private GreenfootImage topping, theSauce, dough;
    private Customer customer;
    private boolean madePizza = false;
    private static KitchenCounter kitchen1, kitchen2;
    
    public Order(String sauceType, String[] toppingTypes, Customer theCustomer){
        toppings = toppingTypes;
        sauce = sauceType;
        customer=theCustomer;
        dough = new GreenfootImage("pizzaBase.png");
        chatBox.scale(60, 70);
        chatBox.drawImage(dough, 12, 5);
        
    }
    public void act(){
        moveMe();
        
    }
    /**
     * draw the order picture above the head of each customer
     */
    public void drawOrder(){
        theSauce = new GreenfootImage("sauce" + sauce + ".png");
        chatBox.drawImage(theSauce, 12, 5);
        for(int i = 0; i < toppings.length - 1; i++){
            topping = new GreenfootImage(toppings[i] + ".png");
            chatBox.drawImage(topping, 12, 5);
        }
    }
    public void makePizza()
    {
        kitchen1 = (KitchenCounter)getWorld().getObjectsAt(Utils.kitchenCounterX, Utils.kitchenCounterY1, KitchenCounter.class).get(0);
        kitchen2 = (KitchenCounter)getWorld().getObjectsAt(Utils.kitchenCounterX, Utils.kitchenCounterY2, KitchenCounter.class).get(0);
        if(kitchen1.checkCanMakePizza())
        {
            Pizza pizza=new Pizza(toppings, sauce, customer);
            customer.setPizza(pizza);
            getWorld().addObject(pizza, Utils.kitchenCounterX, Utils.kitchenCounterY1);
        }
        else if(kitchen2.checkCanMakePizza())
        {
            Pizza pizza=new Pizza(toppings, sauce, customer);
            customer.setPizza(pizza);
            getWorld().addObject(pizza, Utils.kitchenCounterX, Utils.kitchenCounterY2);
        }
        
    }
    
    public void addedToWorld(World w){
        drawOrder();
        makePizza();
    }
    
    /**
     * move the order with the customer
     */
    public void moveMe(){
        setLocation(customer.getX(), customer.getY()-(customer.getImage()).getHeight()/2-20);
    }
    
}
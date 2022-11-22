import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Pizza here.
 * 
 * @Yuxin Li (Yuxin Li) 
 * @version (a version number or a date)
 * 
 */ 
public class Pizza extends Actor
{
    /**
     * Act - do whatever the Pizza wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    // constructor 
    // variables: burn, firstStage finish, fully cooked, discard or not
    // attached to chef, cookTime, burnTime

    private String[] toppings;
    private String sauce;
    private boolean burn = false;
    private boolean doughFinished = false, toppingsFinished = false, sauceFinished = false, cheeseFinished = false;
    private boolean firstStage_finished = false;
    private boolean cooked = false;
    private Cashier cashier;
    private Customer customer;
    private int chef_Xoffset = 50, chef_Yoffset = 0;
    private boolean inOven;
    private int cookTime = 900, price;
    private int toppingTime = 60;
    private SimpleTimer timer = new SimpleTimer();
    
    private boolean hasCheese;
    
    private GreenfootImage pizza = new GreenfootImage("pizzaBase.png");
    private GreenfootImage imageSauce;
    /*
    private static GreenfootImage[] doughSequence={
        new GreenfootImage("dough1.png"),
        new GreenfootImage("dough2.png"),
        new GreenfootImage("dough3.png"),
        new GreenfootImage("dough4.png"),
        new GreenfootImage("dough5.png"),
    };
    */
    private static GreenfootImage crust = new GreenfootImage("cooked.png");
    private static GreenfootImage burned=new GreenfootImage("burned.png");
    private GreenfootImage cheese = new GreenfootImage("cheese.png");
    private int imageIndex = 0, toppingIndex = 0;
    private int changeTime = 0;
    private boolean hasCashier = false, hasChef = false;
    private boolean atCashierCounter = false, paid = false;
    
    private double exactX;
    private double exactY;
    
    // andy code
    private boolean customerPickedUp = false;
    
    /**
     * initialize a pizza that correspond to a customer's order after 
     * a customer comes in the store and orders
    */
    public Pizza(String[] strings, String sauce, boolean cheese){
        toppings = strings;
        this.sauce = sauce;
        hasCheese = cheese; 
        atCashierCounter = false;
    }
    
    public void act()
    {
        if(customerPickedUp == false){
            if (!doughFinished)
            {
                spreadDough();
            }
            if(doughFinished && !sauceFinished)
            {   
                toppingTime--;
                addSauce(sauce);
            }
            if(doughFinished && sauceFinished && !cheeseFinished){
                addCheese(hasCheese);
            }
            if(doughFinished && sauceFinished && cheeseFinished && !toppingsFinished)
            {
                toppingTime--;
                addToppings(toppings);
            }
        } else {
            //andy code
            //if picked up by a customer, set location based on customers direction
            switch (customer.getRotation()){
                case People.UP:
                    setLocation(customer.getX(), customer.getY() - (customer.getImage().getHeight()/2 + getImage().getHeight()/2));
                    break;
                case People.DOWN:
                    setLocation(customer.getX(), customer.getY() +  getImage().getHeight()/2);
                    break;
                case People.LEFT:
                    setLocation(customer.getX() - getImage().getWidth() + 5, customer.getY());
                    break;
                case People.RIGHT:
                    setLocation(customer.getX()  + getImage().getWidth() - 5, customer.getY());
                    break;
            }
            //removes actor when at edge
            atEdge();
        }
    }
    
    /**
     * an animation of the dough spreading process
     */
    public void spreadDough(){
        pizza.scale(50,50);
        setImage(pizza);
        doughFinished = true;
    }
    
    public void addSauce(String sauce)
    {
        if(toppingTime == 0)
        {
            imageSauce = new GreenfootImage("sauce" + sauce + ".png");
            imageSauce.scale(50, 50);
            getImage().drawImage(imageSauce, 0 , 0);
            sauceFinished = true;
            toppingTime = 60;
        }
    }
    
    public void addCheese(boolean hasCheese){
        if (hasCheese){
            cheese.scale(50, 50);
            getImage().drawImage(cheese, 0 , 0);
        }
        cheeseFinished = true;
    }
    
    /**
     * add one new topping every 60 acts
     */
    public void addToppings(String[] strings){
        if(toppingIndex < strings.length && toppingTime==0){
            GreenfootImage topping = new GreenfootImage(toppings[toppingIndex] + ".png");
            topping.scale(50,50);
            getImage().drawImage(topping, 0, 0);
            toppingIndex++;
            toppingTime = 60;
        }
        if(toppingIndex == strings.length)
        {
            toppingsFinished = true;
        }
    }
    
    /**
     * calculate the cook time required for the pizza
    */
    public int getCookTime(String[] strings){
        //return cooktime
        //add the time for all toppings
        cookTime = 60 * (strings.length + 2);
        return cookTime;
    }
    
    /**
     * if the pizza is cooked and a cashier comes, return has cashier
     * if hasCashier, the clocked will be removed
     */
    public boolean isPickedUp(){
        //if the pizza is in oven and the pizza is cooked
        //find the chef picking up the pizza
        if(cooked && inOven == true && hasChef == false){
            ArrayList<Cashier> cashierNear = (ArrayList<Cashier>)getObjectsInRange(50, Cashier.class);
            cashier = cashierNear.get(0);
            hasCashier = true;
            inOven = false;
        }
        return hasCashier;
    }
    
    public boolean isInOven(){
        return inOven;
    }
    
    public void goInOven(){
        getWorld().addObject(new Clock(cookTime, this), getX(), getY()-50);
        inOven = true;
    }
    
    /**
     * dough finished getter method
     */
    public boolean isDoughFinished()
    {
        return doughFinished;
    }
    
    /**
     * finished adding toppings getter method
     */
    public boolean toppingsFinished(){
        return toppingsFinished;
    }
    
    /**
     * check if the pizza is burned
     */
    public boolean isBurned(){
        return burn;
    }
    
    /**
     * check if the pizza is cooked 
     */
    public boolean isCooked(){
        return cooked;
    }
    
    public void setAtCashierCounter(){
        atCashierCounter = true;
    }
    
    public boolean isAtCashierCounter(){
        return atCashierCounter;
    }
    
    public void burnPizza(){
        //add a dark layer on the pizza
        //drawImage
        burned.scale(50,50);
        burned.setTransparency(100);
        getImage().drawImage(burned,0,0);
        burn=true;
    }
    
    public void cookPizza(){
        //add a golden crust layer on pizza
        crust.scale(50,50);
        crust.setTransparency(90);

        getImage().drawImage(crust,0,0);
        cooked=true;
    }
    
    //andy code, setter called by customer to set locations
    public void setCPU(Customer customer){
        customerPickedUp = true;
        this.customer = customer;
    }
    
    public void atEdge(){
        if(getY() <= 81 || getY() >= 799){
            getWorld().removeObject(this);
        }
    }
    
    public String[] getToppings(){
        return toppings;
    }
    
    public String getSauce(){
        return sauce;
    }
    
    /**
     * Set the location using exact coordinates.
     * 
     * @param x the new x location
     * @param y the new y location
     */
    public void setLocation(double x, double y) 
    {
        exactX = x;
        exactY = y;
        super.setLocation((int) (x + 0.5), (int) (y + 0.5));
    }
}

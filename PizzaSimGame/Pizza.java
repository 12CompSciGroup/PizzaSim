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
    private boolean doughFinished = false, toppingsFinished = false, sauceFinished = false;
    private boolean cooked = false;
    private Cashier cashier;
    private int chef_Xoffset = 50, chef_Yoffset = 0;
    private boolean inOven;
    
    private SimpleTimer timer = new SimpleTimer();

    
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

    private int imageIndex = 0, toppingIndex = 0;
    private int changeTime = 0;
    private boolean hasCashier = false, hasChef = false;
    
    private double exactX;
    private double exactY;
    
    /**
     * initialize a pizza that correspond to a customer's order after 
     * a customer comes in the store and orders
    */
    public Pizza(String[] strings, String sauce){
        toppings = strings;
        this.sauce = sauce;
    }
    
    public void act()
    {
        if(!doughFinished)
        {
            spreadDough();
        }
        if(doughFinished && !sauceFinished)
        {
            addSauce(sauce);
        }
        if(doughFinished && sauceFinished && !toppingsFinished)
        {
            addToppings(toppings);
        }
    }
    
    /**
     * an animation of the dough spreading process
     */
    public void spreadDough(){
        setImage(pizza);
        doughFinished = true;
        /*
        //If there is a chef next to a table
        //start spreading the dough
        if(imageIndex >= 5){
            //add the ingredients
            doughFinished = true;
        }
        else if(imageIndex < 5 && changeTime == 0){
            setImage(doughSequence[imageIndex]);
            imageIndex++;
            changeTime = 5;
        }
        changeTime--;
        */
    }
    
    public void addSauce(String sauce)
    {
        if(timer.millisElapsed() > 1000)
        {
            timer.mark();
            imageSauce = new GreenfootImage("sauce" + sauce + ".png");
            getImage().drawImage(imageSauce, 0 , 0);
            sauceFinished = true;
        }
    }
    
    public void addToppings(String[] strings){
        //calculate total cooktime
        //placeholders
        if(timer.millisElapsed() > 1000)
        {
            timer.mark();
            for(int i=0; i<toppings.length; i++){
                GreenfootImage topping = new GreenfootImage(toppings[i] + ".png");
                getImage().drawImage(topping, 0, 0);
                toppingIndex++;
            }
            if(toppingIndex == toppings.length)
            {
                toppingsFinished = true;
            }
        }
    }
    /**
     * calculate the cook time required for the pizza
    */
    public int getCookTime(String[] strings){
        //return cooktime
        //add the time for all toppings
        return 300;
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
    
    public void goInOven(){
        inOven = true;
    }
    
    /**
     * dough finished getter method
     */
    public boolean isDoughFinished(){
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
    
    public void burnPizza(){
        //add a dark layer on the pizza
        //drawImage
        burn=true;
    }
    
    public void cookPizza(){
        //add a golden crust layer on pizza
        cooked=true;
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

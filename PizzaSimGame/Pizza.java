import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Pizza here.
 * 
 * @Yuxin Li (your name) 
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
    private Customer customer;
    private int chef_Xoffset = 50, chef_Yoffset = 0;
    private boolean inOven;
    private int cookTime;
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
<<<<<<< HEAD
    private static GreenfootImage crust=new GreenfootImage("cooked.png");
    private GreenfootImage theSauce;
    private String sauce;
    private int imageIndex;
    private int changeTime;
    private boolean hasChef;
=======
    */
    private static GreenfootImage crust=new GreenfootImage("cooked.png");
    private int imageIndex = 0, toppingIndex = 0;
    private int changeTime = 0;
    private boolean hasCashier = false, hasChef = false;
    private boolean atCashierCounter = false;
>>>>>>> main
    
    private double exactX;
    private double exactY;
    
    /**
     * initialize a pizza that correspond to a customer's order after 
     * a customer comes in the store and orders
<<<<<<< HEAD
     */
    public Pizza(String sauce, String[] strings){
        firstStage_finished=false;
        doughFinished=false;
        imageIndex=0;
        changeTime=0;
        //this.customer=customer;
        this.sauce=sauce;
        hasChef=false;
        toppings=strings;
        burn=false;
=======
    */
    public Pizza(String[] strings, String sauce, Customer theCustomer){
        toppings = strings;
        this.sauce = sauce;
        customer = theCustomer;
        atCashierCounter = false;
>>>>>>> main
    }
    
    public void act()
    {
        getCookTime(toppings);
        if(!doughFinished)
        {
            spreadDough();
        }
<<<<<<< HEAD
        
        if(doughFinished&&firstStage_finished==false){
            addToppings(sauce, toppings);
=======
        if(doughFinished && !sauceFinished)
        {
            addSauce(sauce);
        }
        if(doughFinished && sauceFinished && !toppingsFinished)
        {
            addToppings(toppings);
>>>>>>> main
        }
        moveMe();
    }
    
    public void moveMe(){
        if(atCashierCounter){
            customer.setPickedUp();
            setLocation(customer.getX(), customer.getY());
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
<<<<<<< HEAD
    public void addToppings(String sauce, String[] strings){
        //calculate total cooktime
        //placeholders
        theSauce=new GreenfootImage("sauce"+sauce+".png");
        getImage().drawImage(theSauce,0,0);
        for(int i=0; i<toppings.length; i++){
            GreenfootImage topping = new GreenfootImage(toppings[i] + ".png");
            getImage().drawImage(topping, 0, 0);
=======
    
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
>>>>>>> main
        }
    }
    /**
     * calculate the cook time required for the pizza
<<<<<<< HEAD
     */
    public int getCookTime(){
        //return cooktime
        //add the time for all toppings
        return 100;
=======
    */
    public int getCookTime(String[] strings){
        //return cooktime
        //add the time for all toppings
        cookTime=60*(strings.length+2);
        return cookTime;
>>>>>>> main
    }
    /**
     * if the pizza is cooked and a cashier comes, return has cashier
     * if hasCashier, the clocked will be removed
     */
    public void isPickedUp(){
        //if the pizza is in oven and the pizza is cooked
        //find the chef picking up the pizza
        inOven = false;

    }
    
    public boolean isInOven(){
        return inOven;
    }
    
    public void goInOven(){
<<<<<<< HEAD
        //set transparency to 0 
        //getImage().setTransparency(0);
        //start timer
        //getWorld.addObject(new Timer(cookTime), ovenX, ovenY);
        //set the oven filled 
        //find the position of the oven(oven1 oven2 oven3)
        inOven=true;
=======
        getWorld().addObject(new Clock(cookTime, this), getX(), getY());
        inOven = true;
>>>>>>> main
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
     * return if the pizza is burned
     */
    public boolean getBurn(){
        return burn;
    }
    
    /**
     * return if the pizza is cooked
     */
    public boolean getCooked(){
        return cooked;
    }
<<<<<<< HEAD
=======
    public void setAtCashierCounter(){
        atCashierCounter=true;
    }
    public boolean isAtCashierCounter(){
        return atCashierCounter;
    }
>>>>>>> main
    public void burnPizza(){
        //add a dark layer on the pizza
        //drawImage
        burn=true;
    }
    
    public void cookPizza(){
        //add a golden crust layer on pizza
<<<<<<< HEAD
        crust.setTransparency(150);
=======
        crust.setTransparency(80);
>>>>>>> main
        getImage().drawImage(crust,0,0);
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

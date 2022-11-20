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
    private boolean firstStage_finished;
    private boolean cooked = false;
    private Cashier cashier;
    private Customer customer;
    private int chef_Xoffset = 50, chef_Yoffset = 0;
    private boolean inOven;
    private int cookTime, price;
    private int toppingTime=60;
    

    
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
    private GreenfootImage theSauce;
    private static GreenfootImage crust=new GreenfootImage("cooked.png");
    private int imageIndex = 0, toppingIndex = 0;
    private int changeTime = 0;
    private boolean hasCashier = false, hasChef = false;
    private boolean atCashierCounter = false, paid=false;

    
    private double exactX;
    private double exactY;
    
    /**
     * initialize a pizza that correspond to a customer's order after 
     * a customer comes in the store and orders

     */
    public Pizza(String sauce, String[] strings){
        firstStage_finished=false;
        doughFinished=false;
        imageIndex=0;
        changeTime=0;
        //this.customer=customer;
        cookTime=300;
        this.sauce=sauce;
        hasChef=false;
        toppings=strings;
        burn=false;
    }
    
    public Pizza(String[] strings, String sauce, Customer theCustomer){
        toppings = strings;
        this.sauce = sauce;
        customer = theCustomer;
        atCashierCounter = false;
        cookTime=60*(strings.length+2);
        price=3*strings.length;
    }
    
    public void act(){
        if(!doughFinished)
        {
            spreadDough();
        }
        if(doughFinished && !sauceFinished)
        {
            toppingTime--;
            addSauce(sauce);
        }
        if(doughFinished && sauceFinished && !toppingsFinished)
        {
            toppingTime--;
            addToppings(toppings);

        }
        if(atCashierCounter&&paid==false){
            //Money_displayer money_displayer=(Money_displayer)getWorld().getObjectsAt(200, 40, Money_displayer.class).get(0);
            //money_displayer.setDisplayer(money_displayer.getMoney()+price);
            paid=true;
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
        pizza.scale(50,50);
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

    //public void addToppings(String sauce, String[] strings){
        //theSauce=new GreenfootImage("sauce"+sauce+".png");
        //getImage().drawImage(theSauce, 0, 0);
        //for(int i=0; i<toppings.length; i++){
            //GreenfootImage topping = new GreenfootImage(toppings[i] + ".png");
            //getImage().drawImage(topping, 0, 0);
        //}
    //}

    public void addSauce(String sauce)
    {
        if(toppingTime==0)
        {
            imageSauce = new GreenfootImage("sauce" + sauce + ".png");
            imageSauce.scale(50,50);
            getImage().drawImage(imageSauce, 0 , 0);
            sauceFinished = true;
            toppingTime=60;
        }
    }
    
    public void addToppings(String[] strings){
        if(toppingIndex < strings.length && toppingTime==0){
            GreenfootImage topping = new GreenfootImage(toppings[toppingIndex] + ".png");
            topping.scale(50,50);
            getImage().drawImage(topping, 0, 0);
            toppingIndex++;
            toppingTime=60;
        }
        if(toppingIndex == strings.length)
        {
            toppingsFinished = true;
        }
    }
    /**
     * get the cook time required for the pizz
     */
    public int getCookTime(){
        //return cooktime
        //add the time for all toppings
        return cookTime;

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

        //set transparency to 0 
        //getImage().setTransparency(0);
        //start timer
        //getWorld.addObject(new Timer(cookTime), ovenX, ovenY);
        //set the oven filled 
        //find the position of the oven(oven1 oven2 oven3)
        
        getWorld().addObject(new Clock(cookTime, this), getX(), getY()-50);
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
    public boolean isCooked(){
        return cooked;
    }
    public void setAtCashierCounter(){
        atCashierCounter=true;
    }
    public boolean isAtCashierCounter(){
        return atCashierCounter;
    }

    public void burnPizza(){
        //add a dark layer on the pizza
        //drawImage
        burn=true;
    }
    
    public void cookPizza(){
        //add a golden crust layer on pizza
        crust.scale(50,50);
        crust.setTransparency(90);

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

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
    private boolean burn;
    private boolean firstStage_finished;
    private boolean doughFinished;
    private boolean cooked;
    private Chef chef;
    private int chef_Xoffset=50, chef_Yoffset=0;
    private Customer customer;
    private boolean inOven;
    private static GreenfootImage[] doughSequence={
        new GreenfootImage("dough1.png"),
        new GreenfootImage("dough2.png"),
        new GreenfootImage("dough3.png"),
        new GreenfootImage("dough4.png"),
        new GreenfootImage("dough5.png"),
    };
    private GreenfootImage theSauce;
    private String sauce;
    private int imageIndex;
    private int changeTime;
    private boolean hasChef;
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
        this.sauce=sauce;
        hasChef=false;
        toppings=strings;
        burn=false;
    }
    public void act()
    {
        
        if(checkCounterChef()&&doughFinished==false){
            spreadDough();
        }
        
        if(doughFinished&&firstStage_finished==false){
            addToppings(sauce, toppings);
        }
        isPickedUp();
    }
    /**
     * check if there is a chef around counter
     */
    public boolean checkCounterChef(){
        Chef chef1=(Chef)getWorld().getObjectsAt(110, 300, Chef.class).get(0);
        Chef chef2=(Chef)getWorld().getObjectsAt(110, 380, Chef.class).get(0);
        if(chef1!=null&&chef2==null){
            setLocation(chef1.getX()-chef_Xoffset, chef1.getY());
            return true;
        }
        if(chef1==null&&chef2!=null){
            setLocation(chef2.getX()-chef_Xoffset, chef2.getY());
            return true;
        }
        if(chef1!=null&&chef2!=null){
            setLocation(chef1.getX()-chef_Xoffset, chef1.getY());
            return true;
        }
        return false;
    }
    /**
     * an animation of the dough spreading process
     */
    public void spreadDough(){
        //If there is a chef next to a table
        //start spreading the dough
        if(imageIndex>=5){
            //add the ingredients
            doughFinished=true;
        }
        else if(imageIndex<5&&changeTime==0){
            setImage(doughSequence[imageIndex]);
            imageIndex++;
            changeTime=5;
        }
        changeTime--;
    }
    public void addToppings(String sauce, String[] strings){
        //calculate total cooktime
        //placeholders
        theSauce=new GreenfootImage("sauce"+sauce+".png");
        getImage().drawImage(theSauce,0,0);
        for(int i=0; i<toppings.length; i++){
            GreenfootImage topping = new GreenfootImage(toppings[i] + ".png");
            getImage().drawImage(topping, 0, 0);
        }
    }
    /**
     * calculate the cook time required for the pizza
     */
    public int getCookTime(){
        //return cooktime
        //add the time for all toppings
        return 100;
    }
    /**
     * if the pizza is cooked and a chef comes, return has chef
     * if hasChef, the clocked will be removed
     */
    public boolean isPickedUp(){
        //if the pizza is in oven and the pizza is cooked
        //find the chef picking up the pizza
        if(cooked&&inOven==true&&hasChef==false){
            ArrayList<Chef> chefsNear=(ArrayList<Chef>)getObjectsInRange(50, Chef.class);
            chef=chefsNear.get(0);
            hasChef=true;
            inOven=false;
        }
        return hasChef;
    }
    
    public void goInOven(){
        //set transparency to 0 
        //getImage().setTransparency(0);
        //start timer
        //getWorld.addObject(new Timer(cookTime), ovenX, ovenY);
        //set the oven filled 
        //find the position of the oven(oven1 oven2 oven3)
        inOven=true;
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
    public boolean isFirstStageFinished(){
        return firstStage_finished;
    }
    
    /**
     * 
     */
    public boolean getBurn(){
        return burn;
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
}

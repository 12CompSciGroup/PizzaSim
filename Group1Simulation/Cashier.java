import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math.*;

/**
 * Actor that moves pizza from oven to counter for customers to pick up
 * 
 * @author Anson Ho 
 * @version Novemeber 2022
 */
public class Cashier extends People
{
    private int rotationIndex = 180, startRotationIndex, imageIndex = 0, pizzaria;
    private int ovenXCoord, ovenYCoord, counterXCoord, counterYCoord, cookedOven = 4;
    private int pizzaXOffset = 0, pizzaYOffset = -50;
    private int pizzaXCoord, pizzaYCoord, rotationIndexRadians;
    private boolean currentlyMovingToOven = false, currentlyMovingPizza = false, atOven = false, atCounter = false, foundPizza = false;
    private boolean canPickUp, checkedOvenLocation = false;
    private Oven oven1, oven2, oven3;
    
    private SimpleTimer timer = new SimpleTimer();
    private SimpleTimer animationTimer = new SimpleTimer();
    
    GreenfootImage walkUp[] = new GreenfootImage[9];
    GreenfootImage walkDown[] = new GreenfootImage[9];
    GreenfootImage walkRight[] = new GreenfootImage[9];
    GreenfootImage walkLeft[] = new GreenfootImage[9];
    GreenfootImage rightInteract[] = new GreenfootImage[6];
    GreenfootImage downInteract[] = new GreenfootImage[6];
    
    private Pizza assignedPizza;
    
    //andy code
    private int startX, startY;
    private boolean atCashier = true;
    
    public Cashier (int counterXCoord, int counterYCoord, int scaleX, int scaleY, int pizzaria)
    {
        this.counterXCoord = counterXCoord;
        this.counterYCoord = counterYCoord;
        this.pizzaria = pizzaria;
        
        //andy code
        startX = counterXCoord;
        startY = counterYCoord;
        
        //Sets all image arrays for animation
        for(int i = 0; i < walkUp.length; i++)
        {
            walkUp[i] = new GreenfootImage("images/Cashier Animation/walkUp" + i + ".png");
            walkUp[i].scale(scaleX, scaleY);
            walkDown[i] = new GreenfootImage("images/Cashier Animation/walkDown" + i + ".png");
            walkDown[i].scale(scaleX, scaleY);
            walkRight[i] = new GreenfootImage("images/Cashier Animation/walkRight" + i + ".png");
            walkRight[i].scale(scaleX, scaleY);
            walkLeft[i] = new GreenfootImage("images/Cashier Animation/walkLeft" + i + ".png");
            walkLeft[i].scale(scaleX, scaleY);
        }
        
        for(int i = 0; i < rightInteract.length; i++)
        {
            rightInteract[i] = new GreenfootImage("images/Cashier Animation/interactUp" + i + ".png");
            rightInteract[i].scale(scaleX, scaleY);
            downInteract[i] = new GreenfootImage("images/Cashier Animation/interactDown" + i + ".png");
            downInteract[i].scale(scaleX, scaleY);
        }
        
        //Sets initial values
        setImage(walkDown[0]);
        startRotationIndex = rotationIndex;
    }
    
    public void act()
    {
        if(interactCounter > 0){
            interact(rightInteract, downInteract, rotationIndex);
        } else {
            if (atCashier){
                standStill(walkUp[0], walkDown[0], walkLeft[0], walkRight[0], rotationIndex);
            } else {
                animate(walkUp, walkDown, walkLeft, walkRight, rotationIndex);
            }
            
            if(pizzaria == -1 && !checkedOvenLocation)
            {
                oven1 = (Oven)getWorld().getObjectsAt(Utils.oven1X, Utils.ovenY, Oven.class).get(0);
                oven2 = (Oven)getWorld().getObjectsAt(Utils.oven2X, Utils.ovenY, Oven.class).get(0);
                oven3 = (Oven)getWorld().getObjectsAt(Utils.oven3X, Utils.ovenY, Oven.class).get(0);
                checkedOvenLocation = true;
            }
            if(pizzaria == 1 && !checkedOvenLocation)
            {
                oven1 = (Oven)getWorld().getObjectsAt(Utils.oven4X, Utils.ovenY, Oven.class).get(0);
                oven2 = (Oven)getWorld().getObjectsAt(Utils.oven5X, Utils.ovenY, Oven.class).get(0);
                oven3 = (Oven)getWorld().getObjectsAt(Utils.oven6X, Utils.ovenY, Oven.class).get(0);
                checkedOvenLocation = true;
            }
            
            if((canPickUpPizza() || currentlyMovingToOven) && !atCounter)
            {
                currentlyMovingToOven = true;
                moveToOven();
            }
            if(atOven || currentlyMovingPizza)
            {
                currentlyMovingPizza = true;
                moveToCounter(counterYCoord);
            }
            if(atCounter)
            {
                moveToCashierCounter(counterXCoord, counterYCoord);
            }
            
            if (getX() == counterXCoord && getY() == counterYCoord){
                atCashier = true;
            } else {
                atCashier = false;
            }
        }
    }

    /**
     * Moves cashier to oven
     */
    public void moveToOven()
    {
        if(cookedOven == 4)
        {
            checkCookedOven();
        }
        //rotate chef and pizza 
        if(cookedOven != 4)
        {
            if(rotationIndex != 0 && timer.millisElapsed() > 200 && getX() != ovenXCoord && getY() != ovenYCoord + 50)
            {
                timer.mark();
                rotate(-90 * pizzaria); 
            }
            //move y axis to oven
            if(getY() != ovenYCoord + 50 && rotationIndex == 0 && getX() != ovenXCoord)
            {
                setLocation(getX(), getY() - 1);
            }
            //rotate to walk to oven
            if(getY() == ovenYCoord + 50 && getX() != ovenXCoord && rotationIndex != startRotationIndex + (-90 * pizzaria))
            {
                rotate(90);
            }
            //move X axis to oven
            if(getY() == ovenYCoord + 50 && rotationIndex != startRotationIndex + (90 * pizzaria) && getX() != ovenXCoord)
            {
                setLocation(getX() + (1 * pizzaria), getY());
            }
            //rotate to face oven
            if(getY() == ovenYCoord + 50 && getX() == ovenXCoord && rotationIndex != 0)
            {
                rotate(-90 * pizzaria);
                currentlyMovingToOven = false;
                atOven = true;
            }
        }
    }
    
    /**
     * Moves cashier to counter with pizza in hand to give to customers
     * @param counterYCoord Y coordinate of counter
     */
    public void moveToCounter(int counterYCoord)
    {
        if(!foundPizza)
        {
            interactCounter = 5;
            Pizza pizza = (Pizza)getOneObjectAtOffset(pizzaXOffset, pizzaYOffset, Pizza.class);
            assignPizza(pizza);
            foundPizza = true;
        }
        assignedPizza.getImage().setTransparency(255);
        assignedPizza.isPickedUp();
        atOven = false; 
        //rotate chef and pizza 
        if(rotationIndex != startRotationIndex && timer.millisElapsed() > 200)
        {
            timer.mark();
            rotate(90, assignedPizza, this);             
        }
        //move y axis to cashier counter
        if(getY() != counterYCoord && rotationIndex == startRotationIndex)
        {
            setLocation(getX(), getY() + 1);
            pizzaYCoord += 1;   
            assignedPizza.setLocation(pizzaXCoord, pizzaYCoord);
        }
        
        if(assignedPizza.getY() != Utils.pizzaFinalY && getY() == counterYCoord)
        {
            pizzaYCoord += 1;
            assignedPizza.setLocation(pizzaXCoord, pizzaYCoord); 
        }
        
        if(assignedPizza.getY() == Utils.pizzaFinalY)
        {
            interactCounter = 5;
            currentlyMovingPizza = false;
            atCounter = true;
        }
    }
    
    /**
     * Moves cashier back to original position
     * @param counterXCoord original x coordinate 
     * @param counterYCoord original y coordinate
     */
    public void moveToCashierCounter(int counterXCoord, int counterYCoord)
    {
        //rotate chef and pizza 
        if(rotationIndex != startRotationIndex + (90 * pizzaria) && timer.millisElapsed() > 200 && getX() != counterXCoord)
        {
            timer.mark();
            rotate(90); 
        }
        //move x axis to oven
        if(getX() != counterXCoord && rotationIndex == startRotationIndex + (90 * pizzaria))
        {
            setLocation(getX() + (-1 * pizzaria), getY());
        }
        //resets booleans
        if(getX() == counterXCoord && rotationIndex != startRotationIndex)
        {
            rotate(-90 * pizzaria);
            if(cookedOven == 1)
            {
                oven1.pickUpReserve(false);
            }
            if(cookedOven == 2)
            {
                oven2.pickUpReserve(false);
            }
            if(cookedOven == 3)
            {
                oven3.pickUpReserve(false);
            }
            cookedOven = 4;
            atCounter = false;
            foundPizza = false;
        }
    }
    
    /**
     * Checks if cashier is able to pick up pizza
     * @return boolean
     */
    public boolean canPickUpPizza()
    {
        if(getX() == counterXCoord && getY() == counterYCoord)
        {
            return true;
        }
        return false;
    }
    
    /**
     * Assigns pizza instance to cashier
     * @param pizza Pizza instance
     */
    public void assignPizza(Pizza pizza)
    {
        assignedPizza = pizza;
    }
    
    /**
     * Check for cooked pizza in which oven. Sets oven x coordinates and oven y coordinates
     */
    public void checkCookedOven()
    {
        //checks for empty oven and reserves it
        if(oven1.canPickUp() && !oven1.isPickUpReserved())
        {
            oven1.pickUpReserve(true);
            cookedOven = 1;
            ovenXCoord = oven1.getX();
            ovenYCoord = oven1.getY();
        }
        else if(oven2.canPickUp() && !oven2.isPickUpReserved())
        {
            oven2.pickUpReserve(true);
            cookedOven = 2;
            ovenXCoord = oven2.getX();
            ovenYCoord = oven2.getY();
        }
        else if(oven3.canPickUp() && !oven3.isPickUpReserved())
        {
            oven3.pickUpReserve(true);
            cookedOven = 3;
            ovenXCoord = oven3.getX();
            ovenYCoord = oven3.getY();
        }
        else
        {
            cookedOven = 4;
        }
    }
    
    /**
     * Sets image based on rotation
     * @param degrees amount of degrees you want to turn
     */
    public void rotate(int degrees)
    {
        //zero degrees starts facing up/north
        rotationIndex += degrees;
        if(rotationIndex == 360 || rotationIndex == -360)
        {
            rotationIndex = 0;
        }
        if(rotationIndex == 0)
        {
            setImage(walkUp[0]);
        }
        if(rotationIndex == 90 || rotationIndex == -270)
        {
            setImage(walkRight[0]);
        }
        if(rotationIndex == 180 || rotationIndex == -180)
        {
            setImage(walkDown[0]);
        }
        if(rotationIndex == 270 || rotationIndex == -90)
        {
            setImage(walkLeft[0]);
        }
    }
    
    /**
     * Overrides rotation. Sets image based on rotation as well as sets pizza x and y coordinates
     * @param degrees degrees you want to turn
     * @param pizza pizza instance
     * @param cashier cashier instance
     */
    public void rotate(int degrees, Pizza pizza, Cashier cashier)
    {
        //zero degrees starts facing up/north
        rotationIndex += degrees;
        if(rotationIndex == 360 || rotationIndex == -360)
        {
            rotationIndex = 0;
        }
        if(rotationIndex == 0)
        {
            setImage(walkUp[0]);
            pizzaXCoord = cashier.getX();
            pizzaYCoord = cashier.getY() - 50;
        }
        if(rotationIndex == 90 || rotationIndex == -270)
        {
            setImage(walkRight[0]);
            pizzaXCoord = cashier.getX() + 50;
            pizzaYCoord = cashier.getY();
        }
        if(rotationIndex == 180 || rotationIndex == -180)
        {
            setImage(walkDown[0]);
            pizzaXCoord = cashier.getX();
            pizzaYCoord = cashier.getY() + 50;
        }
        if(rotationIndex == 270 || rotationIndex == -90)
        {
            setImage(walkLeft[0]);
            pizzaXCoord = cashier.getX() - 50;
            pizzaYCoord = cashier.getY();
        }
        pizza.setLocation(pizzaXCoord, pizzaYCoord);
    }
    
    //andy code, checks if cashier has returned to starting position
    public boolean atStart(){
        if (getX() == startX && getY() == startY){
            return true;
        } else {
            return false;
        }
    }
}

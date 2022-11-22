import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math.*;

/**
 * Write a description of class Chef here.
 * 
 * @author Anson Ho 
 * @version V.1
 */
public class Cashier extends People
{
    //oven locations
    /*
        addObject(new Oven(), 140, 190);
        addObject(new Oven(), 210, 190);
        addObject(new Oven(), 280, 190);
     */
    private int rotationIndex = 180, imageIndex = 0;
    private int ovenXCoord, ovenYCoord, counterXCoord, counterYCoord, cookedOven = 4;
    private int pizzaXOffset = 0, pizzaYOffset = -50;
    private double pizzaXCoord, pizzaYCoord, rotationIndexRadians;
    private boolean currentlyMovingToOven = false, currentlyMovingPizza = false, atOven = false, atCounter = false;
    private boolean canPickUp, checkedOvenLocation = false;
    private static Oven oven1, oven2, oven3;
    private boolean pizza1IsCooked, pizza2IsCooked, pizza3IsCooked;
    
    private SimpleTimer timer = new SimpleTimer();
    private SimpleTimer animationTimer = new SimpleTimer();
    
    GreenfootImage walkUp[] = new GreenfootImage[9];
    GreenfootImage walkDown[] = new GreenfootImage[9];
    GreenfootImage walkRight[] = new GreenfootImage[9];
    GreenfootImage walkLeft[] = new GreenfootImage[9];
    GreenfootImage leftInteract[] = new GreenfootImage[6];
    GreenfootImage rightInteract[] = new GreenfootImage[6];
    
    public Cashier (int counterXCoord, int counterYCoord, int scaleX, int scaleY)
    {
        this.counterXCoord = counterXCoord;
        this.counterYCoord = counterYCoord;
        
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
        /*
        for(int i = 0; i < downWalk.length; i++)
        {
            rightIdle[i] = new GreenfootImage("idle" + i + ".png");
            rightIdle[i].scale(x, y);

            leftIdle[i] = new GreenfootImage("idle" + i + ".png");
            leftIdle[i].scale(x, y);
            leftIdle[i].mirrorHorizontally();
        }
        */
        setImage(walkDown[0]);
    }
    
    public void act()
    {
<<<<<<< HEAD
        animate();
        if(!checkedOvenLocation)
=======
        if (atCashier){
            standStill(walkUp[0], walkDown[0], walkLeft[0], walkRight[0], rotationIndex);
        } else {
            animate(walkUp, walkDown, walkLeft, walkRight, rotationIndex);
        }
        
        if(pizzaria == -1 && !checkedOvenLocation)
>>>>>>> parent of 49bbf5a (interactAnim)
        {
            oven1 = (Oven)getWorld().getObjectsAt(Utils.oven1X, Utils.ovenY, Oven.class).get(0);
            oven2 = (Oven)getWorld().getObjectsAt(Utils.oven2X, Utils.ovenY, Oven.class).get(0);
            oven3 = (Oven)getWorld().getObjectsAt(Utils.oven3X, Utils.ovenY, Oven.class).get(0);
            checkedOvenLocation = true;
        }
        
        if((checkCookedPizza() || currentlyMovingToOven) && !atCounter)
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
        
        //moveToCounter(330, 460);
        //moveToCashierCounter(530, 460);
<<<<<<< HEAD
    }
    
    public void animate()
    {
        if(animationTimer.millisElapsed() < 100)
        {
            return;
        }
        animationTimer.mark();
        //Changes actor's image depending on conditions to create animation
        if(rotationIndex == 0)
        {
            setImage(walkUp[imageIndex]);
            imageIndex = (imageIndex + 1) % walkUp.length;
        }
        if(rotationIndex == 90 || rotationIndex == -270)
        {
            setImage(walkRight[imageIndex]);
            imageIndex = (imageIndex + 1) % walkRight.length;
        }
        if(rotationIndex == 180 || rotationIndex == -180)
        {
            setImage(walkDown[imageIndex]);
            imageIndex = (imageIndex + 1) % walkDown.length;
        }
        if(rotationIndex == 270 || rotationIndex == -90) 
        {
            setImage(walkLeft[imageIndex]);
            imageIndex = (imageIndex + 1) % walkLeft.length;
        }
=======
        
        if (getX() == counterXCoord && getY() == counterYCoord){
            atCashier = true;
        } else {
            atCashier = false;
        }
>>>>>>> parent of 49bbf5a (interactAnim)
    }

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
                rotate(90); 
            }
            //move y axis to oven
            if(getY() != ovenYCoord + 50 && rotationIndex == 0 && getX() != ovenXCoord)
            {
                setLocation(getX(), getY() - 1);
            }
            //rotate to walk to oven
            if(getY() == ovenYCoord + 50 && getX() != ovenXCoord && rotationIndex != -90)
            {
                rotate(-90);
            }
            //move X axis to oven
            if(getY() == ovenYCoord + 50 && rotationIndex == -90 && getX() != ovenXCoord)
            {
                setLocation(getX() - 1, getY());
            }
            //rotate to face oven
            if(getY() == ovenYCoord + 50 && getX() == ovenXCoord && rotationIndex != 0)
            {
                rotate(90);
                currentlyMovingToOven = false;
                atOven = true;
            }
        }
    }
    
    public void moveToCounter(int counterYCoord)
    {
<<<<<<< HEAD
        Pizza pizza = (Pizza)getOneObjectAtOffset(pizzaXOffset, pizzaYOffset, Pizza.class);
        pizza.getImage().setTransparency(255);
=======
        if(!foundPizza)
        {
            Pizza pizza = (Pizza)getOneObjectAtOffset(pizzaXOffset, pizzaYOffset, Pizza.class);
            assignPizza(pizza);
            foundPizza = true;
        }
        getPizza().getImage().setTransparency(255);
        getPizza().isPickedUp();
>>>>>>> parent of e8578ac (final interaction animation)
        atOven = false; 
        //rotate chef and pizza 
        if(rotationIndex != 180 && timer.millisElapsed() > 200)
        {
            timer.mark();
            rotate(90);
            rotationIndexRadians = Math.toRadians(rotationIndex);
            pizzaXCoord = getX() + (50 * Math.sin(rotationIndexRadians));
            pizzaYCoord = getY() - (50 * Math.cos(rotationIndexRadians));
            pizzaXOffset = (int)(50 * Math.sin(rotationIndexRadians));
            pizzaYOffset = (int)(50 * Math.cos(rotationIndexRadians)) * -1;
            pizza.setLocation(pizzaXCoord, pizzaYCoord);  
        }
        //move y axis to cashier counter
        if(getY() != counterYCoord && rotationIndex == 180)
        {
            setLocation(getX(), getY() + 1);
            pizzaYCoord += 1;   
            pizza.setLocation(pizzaXCoord, pizzaYCoord);
        }
        if(getY() == counterYCoord)
        {
<<<<<<< HEAD
=======
            pizzaYCoord += 1;
            getPizza().setLocation(pizzaXCoord, pizzaYCoord); 
        }
        
        if(getPizza().getY() == Utils.pizzaFinalY)
        {
<<<<<<< HEAD
>>>>>>> parent of e8578ac (final interaction animation)
=======
>>>>>>> parent of 49bbf5a (interactAnim)
            currentlyMovingPizza = false;
            atCounter = true;
            pizzaXOffset = 0;
            pizzaYOffset = -50;
        }
    }
    
    public void moveToCashierCounter(int counterXCoord, int counterYCoord)
    {
        //rotate chef and pizza 
        if(rotationIndex != 90 && timer.millisElapsed() > 200 && getX() != counterXCoord)
        {
            timer.mark();
            rotate(-90); 
        }
        //move x axis to oven
        if(getX() != counterXCoord && rotationIndex == 90)
        {
            setLocation(getX() + 1, getY());
        }
        //rotate to walk to oven
        if(getX() == counterXCoord && rotationIndex != 180)
        {
            rotate(90);
            if(cookedOven == 1)
            {
                oven1.pickUpReserve(false);
            }
            if(cookedOven == 2)
            {
                oven1.pickUpReserve(false);
            }
            if(cookedOven == 3)
            {
                oven1.pickUpReserve(false);
            }
            cookedOven = 4;
            atCounter = false;
        }
    }
    
    public boolean checkCookedPizza()
    {
        if(!oven1.checkIfEmpty())
        {
            Pizza pizza = (Pizza)getWorld().getObjectsAt(Utils.oven1X, Utils.ovenY, Pizza.class).get(0);
            if(pizza.isCooked())
            {
                pizza1IsCooked = true;
                return true;
            }
            pizza1IsCooked = false;
            return false;
        }
        else if(!oven2.checkIfEmpty())
        {
            Pizza pizza = (Pizza)getWorld().getObjectsAt(Utils.oven2X, Utils.ovenY, Pizza.class).get(0);
            if(pizza.isCooked())
            {
                pizza2IsCooked = true;
                return true;
            }
            pizza2IsCooked = false;
            return false;
        }
        else if(!oven3.checkIfEmpty())
        {
            Pizza pizza = (Pizza)getWorld().getObjectsAt(Utils.oven3X, Utils.ovenY, Pizza.class).get(0);
            if(pizza.isCooked())
            {
                pizza3IsCooked = true;
                return true;
            }
            pizza3IsCooked = false;
            return false;
        }
        else
        {
            return false;
        }
    }
    
    public void checkCookedOven()
    {
        //checks for empty oven and reserves it
        checkCookedPizza();
        if(pizza1IsCooked && !oven1.isPickUpReserved())
        {
            oven1.pickUpReserve(true);
            cookedOven = 1;
            ovenXCoord = oven1.getX();
            ovenYCoord = oven1.getY();
        }
        else if(pizza2IsCooked && !oven2.isPickUpReserved())
        {
            oven2.pickUpReserve(true);
            cookedOven = 2;
            ovenXCoord = oven2.getX();
            ovenYCoord = oven2.getY();
        }
        else if(pizza3IsCooked && !oven3.isPickUpReserved())
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
}

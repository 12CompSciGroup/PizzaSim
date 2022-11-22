import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math.*;

/**
 * Write a description of class Chef here.
 * 
 * @author Anson Ho 
 * @version V.1
 */
public class Chef extends People
{
    private int rotationIndex, startRotationIndex, pizzaria;
    private int ovenXCoord, ovenYCoord, counterXCoord, counterYCoord, openOven = 4;
    private int pizzaXOffset, pizzaYOffset = 0;
    private double pizzaXCoord, pizzaYCoord;
    private boolean currentlyMovingPizza = false, foundPizza = false, moving;
    private boolean canPickUp, checkedOvenLocation = false;
    private Oven oven1, oven2, oven3;
    
    private SimpleTimer timer = new SimpleTimer();
    
    GreenfootImage walkUp[] = new GreenfootImage[9];
    GreenfootImage walkDown[] = new GreenfootImage[9];
    GreenfootImage walkRight[] = new GreenfootImage[9];
    GreenfootImage walkLeft[] = new GreenfootImage[9];
    GreenfootImage upInteract[] = new GreenfootImage[6];
    GreenfootImage leftInteract[] = new GreenfootImage[6];
    
    private Pizza assignedPizza;
    
    public Chef(int counterYCoord, int scaleX, int scaleY, int pizzaria)
    {
        this.counterYCoord = counterYCoord;
        this.pizzaria = pizzaria;
        
        pizzaXOffset = 50 * pizzaria;
        pizzaYOffset = 0;
        
        for(int i = 0; i < walkUp.length; i++)
        {
            walkUp[i] = new GreenfootImage("images/Chef Animation/walkUp" + i + ".png");
            walkUp[i].scale(scaleX, scaleY);
            walkDown[i] = new GreenfootImage("images/Chef Animation/walkDown" + i + ".png");
            walkDown[i].scale(scaleX, scaleY);
            walkRight[i] = new GreenfootImage("images/Chef Animation/walkRight" + i + ".png");
            walkRight[i].scale(scaleX, scaleY);
            walkLeft[i] = new GreenfootImage("images/Chef Animation/walkLeft" + i + ".png");
            walkLeft[i].scale(scaleX, scaleY);
        }
        
        for(int i = 0; i < upInteract.length; i++)
        {
            upInteract[i] = new GreenfootImage("images/Chef Animation/interactUp" + i + ".png");
            upInteract[i].scale(scaleX, scaleY);
            leftInteract[i] = new GreenfootImage("images/Chef Animation/interactLeft" + i + ".png");
            leftInteract[i].scale(scaleX, scaleY);
        }

        if(pizzaria == -1)
        {
            setImage(walkLeft[0]);
            rotationIndex = -90;
            counterXCoord = Utils.chefXLeft;
            startRotationIndex = -90;
        }
        if(pizzaria == 1)
        {
            setImage(walkRight[0]);
            rotationIndex = 90;
            counterXCoord = Utils.chefXRight;
            startRotationIndex = 90;
        }
    }
    
    public void act()
    {   
        if (interactCounter > 0){
            interact(upInteract, leftInteract, rotationIndex);
        } else {
            if(getX() != counterXCoord || getY() != counterYCoord)
            {
                moving = true;
            }
            else
            {
                moving = false;
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
            
            if(!currentlyMovingPizza)
            {
                moveToCounter(counterXCoord, counterYCoord);
            }
            if(canPickUpPizza() || currentlyMovingPizza)
            {
                currentlyMovingPizza = true;
                moveToOven();
            }
            
            if (!moving){
                standStill(walkUp[0], walkDown[0], walkLeft[0], walkRight[0], rotationIndex);
            } else {
                animate(walkUp, walkDown, walkLeft, walkRight, rotationIndex);
            }   
        }
    }

    public void moveToOven()
    {
        if(!foundPizza)
        {
            Pizza pizza = (Pizza)getOneObjectAtOffset(pizzaXOffset, pizzaYOffset, Pizza.class);
            assignPizza(pizza);
            foundPizza = true;
        }
        
        if(openOven == 4)
        {
            checkOpenOven();
        }
        //rotate chef and pizza
        if(openOven != 4)
        {
            if(rotationIndex != startRotationIndex + (-180 * pizzaria) && timer.millisElapsed() > 200)
            {
                timer.mark();
                rotate(-90 * pizzaria, assignedPizza, this);
            }
            //move x axis to oven
            if(getX() != ovenXCoord && rotationIndex == startRotationIndex + (-180 * pizzaria))
            {
                setLocation(getX() - (1 * pizzaria), getY());
                pizzaXCoord -= (1 * pizzaria);   
                assignedPizza.setLocation(pizzaXCoord, pizzaYCoord);
            }
            //rotate to face oven
            if(getX() == ovenXCoord && rotationIndex != 0)
            {
                rotate(90 * pizzaria, assignedPizza, this);
            }
            //move y axis to oven
            if(assignedPizza.getY() != ovenYCoord && rotationIndex == 0 && getX() == ovenXCoord)
            {
                setLocation(getX(), getY() - 1);
                pizzaYCoord -= 1;
                assignedPizza.setLocation(pizzaXCoord, pizzaYCoord);
            }
            //reset boolean
            if(assignedPizza.getY() == ovenYCoord && assignedPizza.getX() == ovenXCoord)
            {
                currentlyMovingPizza = false;
                assignedPizza.getImage().setTransparency(0);
                assignedPizza.goInOven();
                foundPizza = false;
                interactCounter = 5;
            }
        }
    }
    
    public void moveToCounter(int counterXCoord, int counterYCoord)
    {
        pizzaXOffset = 50 * pizzaria;
        pizzaYOffset = 0;
        if(rotationIndex != (180 * pizzaria) && timer.millisElapsed() > 200 && getX() != counterYCoord && getY() != counterYCoord)
        {
            timer.mark();
            rotate(90 * pizzaria);
        }
        //move chef y axis to counter
        if(getY() != counterYCoord && rotationIndex == (180 * pizzaria))
        {
            setLocation(getX(), getY() + 1);
        }
        //rotate chef
        if(rotationIndex != startRotationIndex && getY() == counterYCoord)
        {
            rotate(-90 * pizzaria);
        }
        //move chef x axis to counter
        if(getX() != counterXCoord && rotationIndex == startRotationIndex && getY() == counterYCoord)
        {
            setLocation(getX() + (1 * pizzaria), getY());
            if(openOven == 1)
            {
                oven1.reserve(false);
            }
            if(openOven == 2)
            {
                oven2.reserve(false);
            }
            if(openOven == 3)
            {
                oven3.reserve(false);
            }
            openOven = 4;
        }
    }
    
    public void assignPizza(Pizza pizza)
    {
        assignedPizza = pizza;
    }
    
    public boolean canPickUpPizza()
    {
        Pizza pizza = (Pizza)getOneObjectAtOffset(pizzaXOffset, pizzaYOffset, Pizza.class);        
        if(pizza != null && getX() == counterXCoord)
        {
            if(pizza.toppingsFinished())
            {
                canPickUp = true;
            }
        }
        else
        {
            canPickUp = false;
        }
        return canPickUp;
    }
        
    public void checkOpenOven()
    {
        //checks for empty oven and reserves it
        if(oven1.checkIfEmpty() && !oven1.isReserved())
        {
            openOven = 1;
            oven1.reserve(true);
            ovenXCoord = oven1.getX();
            ovenYCoord = oven1.getY();
        }
        else if(oven2.checkIfEmpty() && !oven2.isReserved())
        {
            openOven = 2;
            oven2.reserve(true);
            ovenXCoord = oven2.getX();
            ovenYCoord = oven2.getY();
        }
        else if(oven3.checkIfEmpty() && !oven3.isReserved())
        {
            openOven = 3;
            oven3.reserve(true);
            ovenXCoord = oven3.getX();
            ovenYCoord = oven3.getY();
        }
        else
        {
            openOven = 4;
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
    
    public void rotate(int degrees, Pizza pizza, Chef chef)
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
            pizzaXCoord = chef.getX();
            pizzaYCoord = chef.getY() - 50;
        }
        if(rotationIndex == 90 || rotationIndex == -270)
        {
            setImage(walkRight[0]);
            pizzaXCoord = chef.getX() + 50;
            pizzaYCoord = chef.getY();
        }
        if(rotationIndex == 180 || rotationIndex == -180)
        {
            setImage(walkDown[0]);
            pizzaXCoord = chef.getX();
            pizzaYCoord = chef.getY() + 50;
        }
        if(rotationIndex == 270 || rotationIndex == -90)
        {
            setImage(walkLeft[0]);
            pizzaXCoord = chef.getX() - 50;
            pizzaYCoord = chef.getY();
        }
        pizza.setLocation(pizzaXCoord, pizzaYCoord);
    }
    
    public boolean getCurrentlyMoving(){
        return currentlyMovingPizza;
    }
    
    public boolean getMoving(){
        return moving;
    }
}

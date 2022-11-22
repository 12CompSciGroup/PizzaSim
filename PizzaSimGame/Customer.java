import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Customer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Customer extends People
{
    private int dir, storeRNG, store, toppingRNG;
    
    private boolean inStore, ordered, pickedUp;
    
    private String[] order = new String[10];
    private String dough = "thin", sauce = "tomato", topping;
    
    private int imageRNG, rotation;
    private String gender;
    private GreenfootImage upIMG, downIMG, leftIMG, rightIMG;
    
<<<<<<< HEAD
    public Customer (int dir) {
=======
    GreenfootImage[] walkUp = new GreenfootImage[9];
    GreenfootImage[] walkDown = new GreenfootImage[9];
    GreenfootImage[] walkLeft = new GreenfootImage[9];
    GreenfootImage[] walkRight = new GreenfootImage[9];
    
    private int imageIndex = 0;
    
    private SimpleTimer animTimer = new SimpleTimer();
    private SimpleTimer waitTimer = new SimpleTimer();
    
    private boolean hasEmotionBar = false;
    
    private boolean checkedLocations = false;
    
    private Utils utils;
    
    private int enterDIR, exitDIR;
    
    private boolean isCash2Open = false;
    
    public Customer (int dir, int spawnX, boolean mamaCash2, boolean papaCash2) {
>>>>>>> parent of 2353e15 (initial merge)
        this.dir = dir;
        
        if (dir == -1){
            rotation = UP;
        } else {
            rotation = DOWN;
        }
            
        storeRNG = Greenfoot.getRandomNumber(2);
        imageRNG = Greenfoot.getRandomNumber(2);
        
        if (storeRNG == 0){
            store = -1;
        } else {
            store = 1;
        }
        
        if (imageRNG == 0){
            gender = "boy";
        } else {
            gender = "girl";
        }
        upIMG = new GreenfootImage(gender + "_U.png");
        upIMG.scale(38, 70);
        downIMG = new GreenfootImage(gender + "_D.png");
        downIMG.scale(38, 70);
        leftIMG = new GreenfootImage(gender + "_L.png");
        leftIMG.scale(38, 70);
        rightIMG = new GreenfootImage(gender + "_R.png");
        rightIMG.scale(38, 70);
        
<<<<<<< HEAD
        for(int i = 0; i < order.length; i++)
=======
        //sets animation images and scales them up
        for(int i = 0; i < 9; i++){
            walkUp[i] = new GreenfootImage("images/" + gender + "_Animations/Up/" + (i + 1) + ".png");
            walkUp[i].scale((int)(walkUp[i].getWidth() * 1.5), (int)(walkUp[i].getHeight() * 1.5));
            walkDown[i] = new GreenfootImage("images/" + gender + "_Animations/Down/" + (i + 1) + ".png");
            walkDown[i].scale((int)(walkDown[i].getWidth() * 1.5), (int)(walkDown[i].getHeight() * 1.5));
            walkLeft[i] = new GreenfootImage("images/" + gender + "_Animations/Left/" + (i + 1) + ".png");
            walkLeft[i].scale((int)(walkLeft[i].getWidth() * 1.5), (int)(walkLeft[i].getHeight() * 1.5));
            walkRight[i] = new GreenfootImage("images/" + gender + "_Animations/Right/" + (i + 1) + ".png");
            walkRight[i].scale((int)(walkRight[i].getWidth() * 1.5), (int)(walkRight[i].getHeight() * 1.5));
        }
        
        if (dir == 1){
            setImage(walkDown[0]);
        } else {
            setImage(walkUp[0]);
        }
        
        //randomizes toppings 
        for(int i = 0; i < toppings.length; i++)
>>>>>>> parent of 2353e15 (initial merge)
        {
            toppingRNG = Greenfoot.getRandomNumber(3);
            switch (toppingRNG){
            case 0:
                topping = "pepperoni";
                break;
            case 1:
                topping = "peppers";
                break;
            case 2:
                topping = "ham";
                break;
            }
            order[i] = topping;
        }
    }
    
    public void act (){
<<<<<<< HEAD
<<<<<<< HEAD
        setRotation();
        
        if (inStore == false) {
            if (numOfCustomers >= 5 || (ordered == true && pickedUp == true)){
=======
=======
>>>>>>> parent of 49bbf5a (interactAnim)
        if(waiting){
            standStill(walkUp[0], walkDown[0], walkLeft[0], walkRight[0], rotation);
        } else {
            animate(walkUp, walkDown, walkLeft, walkRight, rotation);
        }
        
        //set locations for cashiers and waitingline
        if(!checkedLocations){
            if(store == Utils.MAMA){
                cash1 = getWorld().getObjectsAt(Utils.cashier1X, Utils.counterY, CashierCounter.class).get(0);
                cash2 = getWorld().getObjectsAt(Utils.cashier2X, Utils.counterY, CashierCounter.class).get(0);
        
                wait1 = getWorld().getObjectsAt(Utils.wait1X, Utils.counterY, WaitingLine.class).get(0);
                wait2 = getWorld().getObjectsAt(Utils.wait2X, Utils.counterY, WaitingLine.class).get(0);
                wait3 = getWorld().getObjectsAt(Utils.wait3X, Utils.counterY, WaitingLine.class).get(0);
            } else {
                cash1 = getWorld().getObjectsAt(Utils.cashier4X, Utils.counterY, CashierCounter.class).get(0);
                cash2 = getWorld().getObjectsAt(Utils.cashier3X, Utils.counterY, CashierCounter.class).get(0);
        
                wait1 = getWorld().getObjectsAt(Utils.wait6X, Utils.counterY, WaitingLine.class).get(0);
                wait2 = getWorld().getObjectsAt(Utils.wait5X, Utils.counterY, WaitingLine.class).get(0);
                wait3 = getWorld().getObjectsAt(Utils.wait4X, Utils.counterY, WaitingLine.class).get(0);
            }
            moneyInterface = getWorld().getObjectsAt(0, 0, MoneyInterface.class).get(0);
            checkedLocations = true;
        }
        
        //moves towards door if havent bought pizza yet, else walks away with pizza
        //also ignores doors if there are more than 5 customers
        if (!inStore) {
            if ((numberOfCustomers1 >= 5 && store == Utils.MAMA) || (numberOfCustomers2 >= 5 && store == Utils.PAPA)){
<<<<<<< HEAD
>>>>>>> parent of 2353e15 (initial merge)
=======
>>>>>>> parent of 49bbf5a (interactAnim)
                setLocation(getX(), getY() + (Utils.moveSpeed * dir));
                
                if (dir == 1){
                    rotation = DOWN;
                } else if (dir == -1){
                    rotation = UP;
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
                }
            } else if (!cash1.checkIfEmpty() && (isCash2Open && !cash2.checkIfEmpty())){
                setLocation(getX(), getY() + (Utils.moveSpeed * dir));
                
                if (dir == 1){
                    rotation = DOWN;
                } else if (dir == -1){
                    rotation = UP;
>>>>>>> parent of 49bbf5a (interactAnim)
                }
            } else if(ordered && pickedUp){
                if (getX() == spawnX){
                    setLocation(getX(), getY() + (Utils.moveSpeed * dir));
                
                    if (dir == 1){
                        rotation = DOWN;
                    } else if (dir == -1){
                        rotation = UP;
                    }
                } else {
                    setLocation(getX() + (Utils.moveSpeed * store * -1), getY());
>>>>>>> parent of 2353e15 (initial merge)
                }
            } else {    
                moveToDoor();
            }
        }
        
        if (inStore == true){
            if (ordered == false && pickedUp == false){
                moveToCashier();
            } else if (ordered == true){
                if (pickedUp == false){
                    lineUp();
                } else {
                    leave();
                }
            }
        }
        
        atEdge();
    }
    
    public void setRotation(){
        switch (rotation){
            case UP:
                setImage(upIMG);
                break;
            case DOWN:
                setImage(downIMG);
                break;
            case LEFT:
                setImage(leftIMG);
                break;
            case RIGHT:
                setImage(rightIMG);
                break;
        }
    }
    
    public void moveToDoor(){
        if(this.getObjectsInRange(100, Door.class).size() > 0){
            if (store == -1){
                rotation = LEFT;
            } else {
                rotation = RIGHT;
            }
            
            if(getX() > Utils.door1X && getX() < Utils.door2X){
                setLocation(getX() + (Utils.moveSpeed * store), getY());
            }
            
            if(getY() != Utils.enterY){
                setLocation(getX(), getY() + (Utils.moveSpeed * dir));
            }
            
        } else {
            setLocation(getX(), getY() + (Utils.moveSpeed * dir));
        }
        
        if(getX() <= Utils.door1X && getY() == Utils.enterY){
            inStore = true;
            numOfCustomers++;
        }
    }
    
    public void moveToCashier (){
        if (rotation == LEFT){
            if (cash1IsFree){
                
                if(getX() != Utils.cashier1X){
                    setLocation(getX() + (Utils.moveSpeed * store), getY());
                } else {
                    rotation = UP;
                    cash1IsFree = false;
                }
            } else if (cash2IsFree){
                
                if(getX() != Utils.cashier2X){
                    setLocation(getX() + (Utils.moveSpeed * store), getY());
                } else {
                    rotation = UP;
                    cash2IsFree = false;
                }
            }
        }
        
        if (rotation == UP){
            if (getY() != Utils.counterY){
                setLocation(getX(), getY() - 1);
            } else {
                ordered = true;
                order();
                if (getX() == Utils.cashier1X){
                    cash1IsFree = true;
                } else {
<<<<<<< HEAD
                    cash2IsFree = true;
=======
                    kitchenCounter1 = getWorld().getObjectsAt(Utils.kitchenCounterXRight, Utils.kitchenCounterY1, KitchenCounter.class).get(0);
                    kitchenCounter2 = getWorld().getObjectsAt(Utils.kitchenCounterXRight, Utils.kitchenCounterY2, KitchenCounter.class).get(0);
                    kitchenCounter3 = getWorld().getObjectsAt(Utils.kitchenCounterXRight, Utils.kitchenCounterY3, KitchenCounter.class).get(0);
                    
                    isChef1 = getWorld().getObjectsAt(Utils.chefXRight, Utils.chef1Y, Chef.class).isEmpty();
                    isChef2 = getWorld().getObjectsAt(Utils.chefXRight, Utils.chef2Y, Chef.class).isEmpty();
                    isChef3 = getWorld().getObjectsAt(Utils.chefXRight, Utils.chef3Y, Chef.class).isEmpty();
                    
                    if(!isChef1){
                        chef1 = getWorld().getObjectsAt(Utils.chefXRight, Utils.chef1Y, Chef.class).get(0);
                    }
                    if(!isChef2){
                        chef2 = getWorld().getObjectsAt(Utils.chefXRight, Utils.chef2Y, Chef.class).get(0);
                    }
                    if(!isChef3){
                        chef3 = getWorld().getObjectsAt(Utils.chefXRight, Utils.chef3Y, Chef.class).get(0);
                    }
                }
                
                waiting = true;
                
                //starts a happiness meter
                
                if (!hasEmotionBar){
                    waitTimer.mark();
                    getWorld().addObject(new WaittingBar(this), getX(), getY() + 10);
                    hasEmotionBar = true;
                }
                
                //only orders if there is a cashier infront of them, a chef at the table, and the chef isn't making a pizza
                if ((cashier != null && cashier.atStart())){
                    if(!isChef1 && kitchenCounter1.checkCanMakePizza()){
                        if(!chef1.getCurrentlyMoving()){
                            order();
                        }
                    } else if (!isChef2 && kitchenCounter2.checkCanMakePizza()){
                        if(!chef2.getCurrentlyMoving()){
                            order();
                        }
                    } else if (!isChef3 && kitchenCounter3.checkCanMakePizza()){
                        if(!chef3.getCurrentlyMoving()){
                            order();
                        }
                    }
>>>>>>> parent of e8578ac (final interaction animation)
                }
            }
        }
    }
    
    public void order (){
        getWorld().addObject(new Order(sauce, order), getX() + 20, getY() - (getImage().getHeight() / 2) - 20);
    }
    
    public void lineUp(){
        if (rotation == UP){
            if (getX() == Utils.cashier1X || getX() == Utils.cashier2X){
                rotation = DOWN;
            } else {
                if (getY() != Utils.counterY){
                    setLocation(getX(), getY() - 1);
                } else {
                    pickedUp = true;
                    
                    if (getX() == Utils.wait1X){
                        wait1IsFree = true;
                    } else if (getX() == Utils.wait2X){
                        wait2IsFree = true;
                    } else {
                        wait3IsFree = true;
                    }
                }
            }
        }
        
        if (rotation == DOWN){
            setLocation(getX(), getY() + 1);
            if (getY() == Utils.enterY){
                rotation = LEFT;
            }
        }
        
        if (rotation == LEFT){
            if (wait1IsFree){
                if(getX() != Utils.wait1X){
                    setLocation(getX() + (Utils.moveSpeed * store), getY());
                } else {
                    rotation = UP;
                    wait1IsFree = false;
                }
            } else if (wait2IsFree){
                if(getX() != Utils.wait2X){
                    setLocation(getX() + (Utils.moveSpeed * store), getY());
                } else {
                    rotation = UP;
                    wait2IsFree = false;
                }
            } else if (wait3IsFree){
                if(getX() != Utils.wait3X){
                    setLocation(getX() + (Utils.moveSpeed * store), getY());
                } else {
                    rotation = UP;
                    wait3IsFree = false;
                }
            } 
        }
    }
    
<<<<<<< HEAD
=======
    public void pizzaPickup() {
        //checks all 3 possible locations of pizza, based on current location
        if (getX() == wait1.getX()){
            pizza1 = (Pizza) getOneObjectAtOffset(0, Utils.pizzaFinalY - Utils.counterY, Pizza.class);
            pizza2 = (Pizza) getOneObjectAtOffset(wait2.getX() - getX(), Utils.pizzaFinalY - Utils.counterY, Pizza.class);
            pizza3 = (Pizza) getOneObjectAtOffset(wait3.getX() - getX(), Utils.pizzaFinalY - Utils.counterY, Pizza.class);
        } else if (getX() == wait2.getX()){
            pizza1 = (Pizza) getOneObjectAtOffset(wait1.getX() - getX(), Utils.pizzaFinalY - Utils.counterY, Pizza.class);
            pizza2 = (Pizza) getOneObjectAtOffset(0, Utils.pizzaFinalY - Utils.counterY, Pizza.class);
            pizza3 = (Pizza) getOneObjectAtOffset(wait3.getX() - getX(), Utils.pizzaFinalY - Utils.counterY, Pizza.class);
        } else if (getX() == wait3.getX()){
            pizza1 = (Pizza) getOneObjectAtOffset(wait1.getX() - getX(), Utils.pizzaFinalY - Utils.counterY, Pizza.class);
            pizza2 = (Pizza) getOneObjectAtOffset(wait2.getX() - getX(), Utils.pizzaFinalY - Utils.counterY, Pizza.class);
            pizza3 = (Pizza) getOneObjectAtOffset(0, Utils.pizzaFinalY - Utils.counterY, Pizza.class);
        }
        
        //only picks up the one that matches the order
        if (pizza1 != null && pizza1.getY() == Utils.pizzaFinalY){
            if (pizza1.getToppings() == toppings && pizza1.getSauce() == sauce){
                pizza1.setCPU(this);
                pickedUp = true;
            }
        }
        if (pizza2 != null && pizza2.getY() == Utils.pizzaFinalY){
            if (pizza2.getToppings() == toppings && pizza2.getSauce() == sauce){
                pizza2.setCPU(this);
                pickedUp = true;
            }
        }
        if (pizza3 != null && pizza3.getY() == Utils.pizzaFinalY){
            if (pizza3.getToppings() == toppings && pizza3.getSauce() == sauce){
                pizza3.setCPU(this);
                pickedUp = true;
            }
        }
    }
    
>>>>>>> parent of e8578ac (final interaction animation)
    public void leave(){
        if (rotation == UP){
            rotation = DOWN;
        }
        
        if (rotation == DOWN){
            if (getX() == Utils.wait1X || getX() == Utils.wait2X || getX() == Utils.wait2X){
                if (getY() != Utils.enterY){
                    setLocation(getX(), getY() + 1);
                } else {
                    rotation = LEFT;
                }
            } else {
                if (getY() != Utils.exitY){
                    setLocation(getX(), getY() + 1);
                } else {
                    rotation = RIGHT;
                }
            }
        }
        
        if (rotation == LEFT){
            if (getX() != 47){
                setLocation(getX() - 1, getY());
            } else {
                rotation = DOWN;
            }
        }
        
        if (rotation == RIGHT){
            if (getX() != Utils.door1X){
                setLocation(getX() + 1, getY());
            }
        }
        
        if (getX() == Utils.door1X && getY() == Utils.exitY){
            inStore = false;
            numOfCustomers--;
        }
    }
    
    public void atEdge(){
        if (dir == 1 && getY() == 799){
            getWorld().removeObject(this);
        } else if (dir == -1 && getY() == 81){
            getWorld().removeObject(this);
        }
    }
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> parent of 49bbf5a (interactAnim)
    
    public boolean getPickedUp(){
        return pickedUp;
    }
    
    public boolean getInStore(){
        return inStore;
    }
    
    public int getStore()
    {
        return store;
    }
    
    public int getWaitTime(){
        return waitTimer.millisElapsed();
    }
>>>>>>> parent of 2353e15 (initial merge)
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * The code for all of the customers actions </p>
 * <ul>
 * <li>  Moves to door when they haven't ordered
 * <li>  Ignores door when there are a lot of customers in the store
 * <li>  Checks for and moves to open cashier
 * <li>  Places an order when the order can be fulfilled
 * <li>  Checks for and moves to open waiting line
 * <li>  Picks up the correct pizza and leaves the store
 * </ul>
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Customer extends People
{
    private Utils utils;
    
    private int spawnX, dir, storeRNG, store, toppingRNG, cheeseRNG, sauceRNG;
    
    private boolean inStore, ordered, atLineUp, pickedUp, waiting, cheese;
    
    private String[] toppings = new String[3];
    private String sauce, topping;
    private int costOfPizza = 5;
    
    private int openCash = -1, openWait = -1;
    
    private CashierCounter cash1, cash2;
    private WaitingLine wait1, wait2, wait3;
    private KitchenCounter kitchenCounter1, kitchenCounter2, kitchenCounter3;
    private Chef chef1, chef2, chef3;
    private boolean isChef1, isChef2, isChef3;
    private Cashier cashier;
    private Pizza pizza1, pizza2, pizza3;
    private MoneyInterface moneyInterface;
    
    private int imageRNG, rotation;
    private String gender;
    private GreenfootImage upIMG, downIMG, leftIMG, rightIMG;
    
    GreenfootImage[] walkUp = new GreenfootImage[9];
    GreenfootImage[] walkDown = new GreenfootImage[9];
    GreenfootImage[] walkLeft = new GreenfootImage[9];
    GreenfootImage[] walkRight = new GreenfootImage[9];
    GreenfootImage[] interact = new GreenfootImage[6];
    
    private int imageIndex = 0;
    
    private SimpleTimer animTimer = new SimpleTimer();
    private SimpleTimer waitTimer = new SimpleTimer();
    
    private boolean hasEmotionBar = false;
    
    private boolean checkedLocations = false;
    
    private int enterDIR, exitDIR;
    
    private boolean isCash2Open = false;
    
    GreenfootSound cashierSound = new GreenfootSound ("cashier.wav");
    
    public Customer (int dir, int spawnX, boolean mamaCash2, boolean papaCash2) {
        this.dir = dir;
        this.spawnX = spawnX;
        
        //sets starting frame based on direction
        if (dir == -1){
            rotation = UP;
        } else {
            rotation = DOWN;
        }
        
        //randomizes which store and gender they are
        storeRNG = Greenfoot.getRandomNumber(2);
        imageRNG = Greenfoot.getRandomNumber(2);
        
        /**
         * randomizes the store for each customer
         */
        if (storeRNG == 0){
            store = Utils.MAMA;
            
            enterDIR = LEFT;
            exitDIR = RIGHT;
            
            if(mamaCash2){
                isCash2Open = true;
            }
        } else {
            store = Utils.PAPA;
            
            enterDIR = RIGHT;
            exitDIR = LEFT;
            
            if(papaCash2){
                isCash2Open = true;
            }
        }
        
        /**
         * randomly picks a model for the customer
         */
        if (imageRNG == 0){
            gender = "boy";
        } else {
            gender = "girl";
        }
        
        /**
         * initiates the walking and interacting animations for each customer
         */
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
        
        for(int i = 0; i < 6; i++){
            interact[i] = new GreenfootImage("images/" + gender + "_Animations/Interaction/" + i + ".png");
            interact[i].scale((int)(interact[i].getWidth() * 1.5), (int)(interact[i].getHeight() * 1.5));
        }
        
        if (dir == 1){
            setImage(walkDown[0]);
        } else {
            setImage(walkUp[0]);
        }
        
        /**
         * randomizes each customer's order and adds to the total cost of the order
         */
        for(int i = 0; i < toppings.length; i++)
        {
            toppingRNG = Greenfoot.getRandomNumber(4);
            switch (toppingRNG){
            case 0:
                topping = "pepperoni";
                costOfPizza += 1;
                break;
            case 1:
                topping = "peppers";
                costOfPizza += 2;
                break;
            case 2:
                topping = "ham";
                costOfPizza += 3;
                break;
            case 3:
                topping = "olives";
                costOfPizza += 2;
            }
            toppings[i] = topping;
        }
        
        /**
         * randomizes, is there cheese on the pizza or not
         */
        cheeseRNG = Greenfoot.getRandomNumber(2);
        if (cheeseRNG == 0){
            cheese = true;
            costOfPizza += 2;
        } else {
            cheese = false;
        }
        
        /**
         * randomizes the sauce thats on the pizza
         */
        sauceRNG = Greenfoot.getRandomNumber(2);
        if (sauceRNG == 0){
            sauce = "tomato";
            costOfPizza += 1;
        } else {
            sauce = "bbq";
            costOfPizza += 2;
        }
    }
    
    public void act (){
        utils = getWorld().getObjectsAt(0, 0, Utils.class).get(0);
        
        if(interactCounter > 0){
            interact(interact);
        } else {
        //plays necessary animations
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
        
        /**
         * moves towards door if they haven't bought pizza yet
         * walks away with the pizza after they receive it
         * ignores the door if theres more than 5 people in their designated store
         */
        if (!inStore) {
            if ((utils.numberOfCustomers1 >= 5 && store == Utils.MAMA) || (utils.numberOfCustomers2 >= 5 && store == Utils.PAPA)){
                moveVertical();
            } else if (!cash1.checkIfEmpty() && (isCash2Open && !cash2.checkIfEmpty())){
                moveVertical();
            } else if(ordered && pickedUp){
                if (getX() == spawnX){
                    moveVertical();
                } else {
                    setLocation(getX() + (Utils.moveSpeed * store * -1), getY());
                }
            } else {    
                moveToDoor();
            }
        }
        
        /**
         * moves the customer to cashier, then to the lineup, then leaves when they have received their pizza
         */
        if (inStore){
            if (!ordered && !pickedUp){
                moveToCashier();
            } else if (ordered){
                if (!pickedUp){
                    if (!atLineUp){
                        lineUp();
                    } else {
                        pizzaPickup();
                    }
                } else {
                    leave();
                }
            }
        }
        
        //removes actor when at edge
        atEdge();
        }
    }
        
    public int getRotation(){
        return rotation;
    }
    
    /**
     * looks for a door, then moves towards the store they are designated
     */
    public void moveToDoor(){
        //moves towards the door if its in range
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
            //keeps moving until it detects a door
            setLocation(getX(), getY() + (Utils.moveSpeed * dir));
        }
        
        //checks if they enter the door
        if((getX() <= Utils.door1X || getX() >= Utils.door2X) && getY() == Utils.enterY){
            inStore = true;
            //customer counter for each store, static variable in persons class
            if(store == Utils.MAMA){
                utils.addCustomer1();
            } else {
                utils.addCustomer2();
            }            
        }
    }
    
    /**
     * checks for empty cashier, and moves towards it
     * once at cashier, the emotion bar and timer starts
     * if there is a free cashier and chef, the order is placed and they pay
     */
    public void moveToCashier (){
        //reserves an open cash
        if(openCash == -1){
            if (cash1.checkIfEmpty() && !cash1.isReserved()){
                openCash = 1;
                cash1.reserve(true);
            } else if (cash2.checkIfEmpty() && !cash2.isReserved() && isCash2Open){
                openCash = 2;
                cash2.reserve(true);
            } else {
                openCash = -1;
            }
        }
        
        if (rotation == enterDIR){
            if (openCash == 1){
                
                if(getX() != cash1.getX()){
                    setLocation(getX() + (Utils.moveSpeed * store), getY());
                } else {
                    rotation = UP;
                }
            } else if (openCash == 2){
                
                if(getX() != cash2.getX()){
                    setLocation(getX() + (Utils.moveSpeed * store), getY());
                } else {
                    rotation = UP;
                }
            }
        }
        
        if (rotation == UP){
            if (getY() != Utils.counterY){
                setLocation(getX(), getY() - 1);
            } else {
                cashier = (Cashier) getOneObjectAtOffset(0, Utils.cashierY - Utils.counterY, Cashier.class);
                
                if (store == -1){
                    kitchenCounter1 = getWorld().getObjectsAt(Utils.kitchenCounterXLeft, Utils.kitchenCounterY1, KitchenCounter.class).get(0);
                    kitchenCounter2 = getWorld().getObjectsAt(Utils.kitchenCounterXLeft, Utils.kitchenCounterY2, KitchenCounter.class).get(0);
                    kitchenCounter3 = getWorld().getObjectsAt(Utils.kitchenCounterXLeft, Utils.kitchenCounterY3, KitchenCounter.class).get(0);
                    
                    isChef1 = getWorld().getObjectsAt(Utils.chefXLeft, Utils.chef1Y, Chef.class).isEmpty();
                    isChef2 = getWorld().getObjectsAt(Utils.chefXLeft, Utils.chef2Y, Chef.class).isEmpty();
                    isChef3 = getWorld().getObjectsAt(Utils.chefXLeft, Utils.chef3Y, Chef.class).isEmpty();
                    
                    if(!isChef1){
                        chef1 = getWorld().getObjectsAt(Utils.chefXLeft, Utils.chef1Y, Chef.class).get(0);
                    }
                    if(!isChef2){
                        chef2 = getWorld().getObjectsAt(Utils.chefXLeft, Utils.chef2Y, Chef.class).get(0);
                    }
                    if(!isChef3){
                        chef3 = getWorld().getObjectsAt(Utils.chefXLeft, Utils.chef3Y, Chef.class).get(0);
                    }
                } else {
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
                        if(!chef1.getMoving() && !chef1.getCurrentlyMoving()){
                            order();
                        }
                    } else if (!isChef2 && kitchenCounter2.checkCanMakePizza()){
                        if(!chef2.getMoving() && !chef2.getCurrentlyMoving()){
                            order();
                        }
                    } else if (!isChef3 && kitchenCounter3.checkCanMakePizza()){
                        if(!chef3.getMoving() && !chef3.getCurrentlyMoving()){
                            order();
                        }
                    }
                }
            }
        }
    }
    
    /**
     * places the order, adds money to the store, plays the pay sound
     */
    public void order (){
        getWorld().addObject(new Order(sauce, cheese, toppings, this, store), getX() + 20, getY() - (getImage().getHeight() / 2) - 20);
        moneyInterface.changeMoney(store, costOfPizza);
        ordered = true;
        waiting = false;
        cashierSound.play();
    }
    
    /**
     * checks for an empty waiting spot, then moves towards it
     */
    public void lineUp(){
        //reserves a waiting spot
        if(openWait == -1){
            if (wait1.checkIfEmpty() && !wait1.isReserved()){
                openWait = 1;
                wait1.reserve(true);
            } else if (wait2.checkIfEmpty() && !wait2.isReserved()){
                openWait = 2;
                wait2.reserve(true);
            } else if (wait3.checkIfEmpty() && !wait3.isReserved()){
                openWait = 3;
                wait3.reserve(true);
            } else {
                openWait = -1;
            }
        }
        
        if (rotation == UP){
            if (getX() == cash1.getX()) {
                rotation = DOWN;
                cash1.reserve(false);
            } else if (getX() == cash2.getX()){
                rotation = DOWN;
                cash2.reserve(false);
            } else {
                if (getY() != Utils.counterY){
                    setLocation(getX(), getY() - 1);
                } else {
                    waiting = true;
                    atLineUp = true;
                }
            }
        }
        
        if (rotation == DOWN){
            setLocation(getX(), getY() + 1);
            
            if (getY() == Utils.enterY){
                rotation = enterDIR;
            }
        }
        
        if (rotation == enterDIR){
            if (openWait == 1){
                if(getX() != wait1.getX()){
                    setLocation(getX() + (Utils.moveSpeed * store), getY());
                } else {
                    rotation = UP;
                }
            } else if (openWait == 2){
                if(getX() != wait2.getX()){
                    setLocation(getX() + (Utils.moveSpeed * store), getY());
                } else {
                    rotation = UP;
                }
            } else if (openWait == 3){
                if(getX() != wait3.getX()){
                    setLocation(getX() + (Utils.moveSpeed * store), getY());
                } else {
                    rotation = UP;
                }
            } 
        }
    }
    
    /**
     * checks for all the pizzas at the counter, picks up the one that matches their order
     */
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
                interactCounter = 5;
                pizza1.setCPU(this);
                pickedUp = true;
            }
        }
        if (pizza2 != null && pizza2.getY() == Utils.pizzaFinalY){
            if (pizza2.getToppings() == toppings && pizza2.getSauce() == sauce){
                interactCounter = 5;
                pizza2.setCPU(this);
                pickedUp = true;
            }
        }
        if (pizza3 != null && pizza3.getY() == Utils.pizzaFinalY){
            if (pizza3.getToppings() == toppings && pizza3.getSauce() == sauce){
                interactCounter = 5;
                pizza3.setCPU(this);
                pickedUp = true;
            }
        }
    }
    
    /**
     * leaves the store
     */
    public void leave(){
        if (rotation == UP){
            rotation = DOWN;
            
            if (getX() == wait1.getX()){
                wait1.reserve(false);
            } else if (getX() == wait2.getX()){
                wait2.reserve(false);
            } else {
                wait3.reserve(false);
            }
            
            waiting = false;
            atLineUp = false;
        }
        
        if (rotation == DOWN){
            if (getY() != Utils.exitY){
                setLocation(getX(), getY() + 1);
            } else {
                rotation = exitDIR;
            }
        }
        
        if (rotation == exitDIR){
            if (getX() != Utils.door1X || getX() != Utils.door2X){
                setLocation(getX() + (Utils.moveSpeed * store * -1), getY());
            }
        }
        
        if ((getX() == Utils.door1X || getX() == Utils.door2X) && getY() == Utils.exitY){
            inStore = false;
            
            //removes a customer from the counter
            if(store == Utils.MAMA){
                utils.removeCustomer1();
            } else {
                utils.removeCustomer2();
            }
        }
    }
    
    //removes the actor when they reach the edge of world
    public void atEdge(){
        if (dir == 1 && getY() == 799){
            getWorld().removeObject(this);
        } else if (dir == -1 && getY() == 81){
            getWorld().removeObject(this);
        }
    }
    
    //standard vertical movement
    public void moveVertical(){
        setLocation(getX(), getY() + (Utils.moveSpeed * dir));
                
        if (dir == 1){
            rotation = DOWN;
        } else if (dir == -1){
            rotation = UP;
        }
    }
    
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
}

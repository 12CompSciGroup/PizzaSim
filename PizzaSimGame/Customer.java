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
    private int dir, storeRNG, store;
    
    private boolean inStore, ordered, pickedUp;
    
    
    private int imageRNG, rotation;
    private String gender;
    private GreenfootImage upIMG, downIMG, leftIMG, rightIMG;
    
    public Customer (int dir) {
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
        downIMG = new GreenfootImage(gender + "_D.png");
        leftIMG = new GreenfootImage(gender + "_L.png");
        rightIMG = new GreenfootImage(gender + "_R.png");
    }
    
    public void act (){
        setRotation();
        
        if (inStore == false) {
            if (numOfCustomers >= 5 || (ordered == true && pickedUp == true)){
                setLocation(getX(), getY() + (Utils.moveSpeed * dir));
                
                if (dir == 1){
                    rotation = DOWN;
                } else if (dir == -1){
                    rotation = UP;
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
                
                if (getX() == Utils.cashier1X){
                    cash1IsFree = true;
                } else {
                    cash2IsFree = true;
                }
            }
        }
    }
    
    public void order (){
        
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
}

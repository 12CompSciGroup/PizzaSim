import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LevelUpImages here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LevelUp extends Actor
{
    private Utils utils;
    
    public LevelUp(){
        getImage().setTransparency(0);
    }
    
    /**
     * Act - do whatever the LevelUpImages wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        this.utils = getWorld().getObjectsAt(0, 0, Utils.class).get(0);
        if((utils.getResturantMoneyOne() > 100 && utils.getResturantMoneyOne() < 200 && utils.getResturantLevelOne() < 2) || (utils.getResturantMoneyOne() >  200 && utils.getResturantLevelOne() < 3))
        {
            utils.addResturantLevelOne();
            if(utils.getRobbingMoneyMama() < -10)
            {
                utils.setRobbingMoneyMama(5);
            }
            LevelDisplayer level_displayer = (LevelDisplayer)getWorld().getObjectsAt(213, 50, LevelDisplayer.class).get(0);
            level_displayer.setDisplayer(utils.getResturantLevelOne());
        }
        
        if((utils.getResturantMoneyTwo() > 100 && utils.getResturantMoneyTwo() < 200 && utils.getResturantLevelTwo() < 2) || (utils.getResturantMoneyTwo() > 200 && utils.getResturantLevelTwo() < 3))
        {
            utils.addResturantLevelTwo();
            if(utils.getRobbingMoneyPapa() < -10)
            {
                utils.setRobbingMoneyPapa(5);
            }
            LevelDisplayer level_displayer = (LevelDisplayer)getWorld().getObjectsAt(795, 50, LevelDisplayer.class).get(0);
            level_displayer.setDisplayer(utils.getResturantLevelTwo());
        }
        
        if((utils.getResturantMoneyOne() > 500))
        {
            Greenfoot.setWorld(new EndWorld(0, utils.getResturantLevelOne(), utils.getResturantMoneyOne()));
        }
        if((utils.getResturantMoneyTwo() > 500))
        {
            Greenfoot.setWorld(new EndWorld(1, utils.getResturantLevelTwo(), utils.getResturantMoneyTwo()));
        }
    }
}

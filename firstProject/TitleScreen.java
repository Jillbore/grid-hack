import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{

    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        // Create a new world with 46x28 cells with a cell size of 26x26 pixels.
        super(46, 28, 26); 
        GreenfootImage bg = getBackground();
        
        GreenfootImage title = new GreenfootImage("titlescreen.png");
        bg.drawImage(title, 0, 0); // draws the titlescreen onto the background
    }
    
    public void act()
    {
        if(Greenfoot.isKeyDown("enter"))
        {
            start();
        }
    }
    
    public void start()
    {
        removeObjects(getObjects(TitleScreen.class));  
        Greenfoot.setWorld(new ActorWorld());
    }
}

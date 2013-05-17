import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Mushrooms are dropped by dwarves. They may be used by the player.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mushroom extends GridActor
{
    private String name;
    
    /**
     * Constructs a mushroom.
     */
    public Mushroom()
    {
        name = "Mushroom";
    }
    
    /**
     * @return the item's name
     */
    public String getItemName()
    {
        return name;
    }
}



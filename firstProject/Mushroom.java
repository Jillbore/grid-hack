import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class Mushroom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mushroom  extends Item
{

    // lose 5% of color value in each step

    /**
     * Constructs a pink mushroom.
     */
    public Mushroom()
    {
        setColor(Color.RED);
    }

    // Just sits still and does nothing
    public void act()
    {
        // No action method required
    }
}



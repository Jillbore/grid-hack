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
        Color c = getColor();
        int red = (int) (c.getRed() * (0.5));
        int green = (int) (c.getGreen() * (0.5));
        int blue = (int) (c.getBlue() * (0.5));

        setColor(new Color(red, green, blue));
    }
}



import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Wall class serves no purpose other than to restrict the player's 
 * movement.
 * 
 * @author Michael Goin 
 * @version May 9, 2013
 */
public class Wall  extends GridActor
{
    // Constructs a wall
    public Wall()
    {
        
    }

    // Constructs a wall at a coordinate
    public Wall(int x, int y)
    {
        /*Location loc = new Location(x, y);
        GridActor gr = new GridActor();
        putSelfInGrid(this, loc);*/
    }

    /**
     * Act - Sits still and blocks movement
     */
    public void act() 
    {
        // No action code required
    }    
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player  extends GridActor
{
    private int count = 0;

    // Creates a player for the user to move with
    public Player()
    {
        
    }
    
    /**
     * Act - Does whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w"))
        {
            move(0);
            count++;
            //TextMessage("hello", 50);
        }
        if(Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d"))
        {
            move(90);
            count++;
        }
        if(Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s"))
        {
            move(180);
            count++;
        }
        if(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a"))
        {
            move(270);
            count++;
        }
        Greenfoot.delay(2);
    }
    
    /**
     * Move - Moves where the player specified if player can move there
     */
    public void move(int direction)
    {
        Grid<GridActor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(direction);
               
        // moves the user to the 'next' space
        if (gr.isValid(next) && canMove(direction))
            moveTo(next);
    }
    
     /**
     * Tests whether the player can move into a location that is empty or
     * contains an item.
     * @return true if the player can move.
     */
    public boolean canMove(int direction)
    {
        Grid<GridActor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(direction);
        if (!gr.isValid(next))
            return false;
        GridActor neighbor = gr.get(next);
        return (neighbor == null) || (neighbor instanceof Item);
        // ok to move into empty location or onto item
        // not ok to move onto any other actor
    }
    
     /**
      * @return the number of moves the player has done
      */
    public int getTime()
    {
        return count;
    }
    
    
}

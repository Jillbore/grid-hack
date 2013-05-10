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
	private int hp, str, luck;

    // Creates a player for the user to move with
    public Player()
    {
        hp = 10;
		str = 10;
		luck = 2;
    }
    
    /**
     * Act - Does whatever the Player has specified to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("j")) // player goes up
        {
            move(0);
            count++;
            //TextMessage("hello", 50);
        }
        if(Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("l")) // player goes right
        {
            move(90);
            count++;
        }
        if(Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("k")) // player goes down
        {
            move(180);
            count++;
        }
        if(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("h")) // player goes left
        {
            move(270);
            count++;
        }
		if(Greenfoot.isKeyDown("q")) // player drinks from fountain (if fountain is there)
		{
			quaff();
			count++;
		}
		if(Greenfoot.isKeyDown("s")) // player searches for hidden areas
		{
			search();
			count++;
		}
		
		// delays next key check to get more accurate results
        Greenfoot.delay(4);
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
	
	 /**
	  * Searches for hidden squares for doors, success is based on chance + luck
	  */
	private void search()
	{
		double prob = 0.0;
		prob = Math.random() * luck;
		
		// search is successful
		if(prob > 0.78)
		{
			// process all actors 1 space away and remove any secret walls/doors
		}
	}
	
	private void quaff()
	{
	    
	}
    
    
}

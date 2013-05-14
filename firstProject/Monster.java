import greenfoot.*;
import java.util.ArrayList;

/**
 * Write a description of class Monster here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Monster extends Critter
{
    private boolean moved = false;
    
    /**
     * Finds all actors and processes them
     */
    public void act()
    {
        ArrayList<GridActor> actors = super.getActors();
        processActors(actors);
    }
    
    public void processActors(ArrayList<GridActor> actors)
    {
        if(!moved)  // only allows one move
        {
            for(GridActor a : actors)   // for each neighbouring actor
            {
                if(a instanceof Player) // change to allow M.v.M. combat
                    attack(a);          // attacks player
            }
        }
        moved = true;
    }
    
    public void attack(GridActor target)
    {
     Player.takeDamage((int)(Math.random()*3));   
    }
}

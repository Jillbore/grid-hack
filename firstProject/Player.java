import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.awt.Color;

/**
 * Player is what the user controls in able to interact with the environment
 * 
 */
public class Player extends GridActor
{
    
    private int count = 0;
    private int str;
    private static int hp;
    private double luck;
    private String text = new String(); 
    private Inventory list = new Inventory();
    private boolean trapped = false;

    // Creates a player with stats for the user to move
    public Player()
    {
        hp = 10;
        str = 10;
        luck = 1.5;
    }

    // Creates a player at a certain coordinate
    public Player(int x, int y)
    {
        hp = 10;
        str = 10;
        luck = 1.5;
        Location loc = new Location(x, y);
        GridActor gr = new GridActor();
        //putSelfInGrid(this, loc);
    }
    
    public Location getLoc()
    {
        Location loc = getLocation();
        return loc;
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
        }
        if(Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("l")) // player goes right
        {
            move(90);
            count++;
        }
        if(Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("j")) // player goes down
        {
            move(180);
            count++;
        }
        if(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("k")) // player goes left
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
        if(Greenfoot.isKeyDown(",")) // player goes down a stairwell
        {
            desend();
            count++;
        }
        //setImage("Player1norm.png");
        
        
        if(Greenfoot.isKeyDown("1")) // player searches for hidden areas
        {
            getWorld().setBackground(new GreenfootImage("ani_starfield.gif"));
        }
        
        // delays next key check to get more accurate results
        Greenfoot.delay(3);
    }

     public void buttonCheck()
     {
        //while(Greenfoot.isKeyDown(""));
        //    buttonCheck();
   
     }
    
    /**
     * Moves where the player specified if player can move there
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
        return (neighbor == null) || (neighbor instanceof Item) 
            || (neighbor instanceof OpenDoor);
        // ok to move into empty location or onto item
        // not ok to move onto any other actor
    }
    
     /**
      * Searches for hidden squares for doors, success is based on chance + luck
      */
    private void search()
    {
        double prob = Math.random() * luck;
         
        // pulls actors from surrounding grid
        ArrayList<GridActor> actors = getGrid().getNeighbors(getLocation()); 
        
        // search is successful
        if(prob > 0.94)
        {
            // process all actors 1 space away and remove any secret walls/doors
                for(GridActor a : actors)   // for each neighbouring actor
                {
                    if(a instanceof SecretWall) // if a secret wall is found
                        a.removeSelfFromGrid();          // removes secret wall
                }
        }
    }

     /**
      * Drinks from a fountain 
        */
     private void quaff()
     {
        Grid<GridActor> gr = getGrid();
        Location loc = getLocation(); // stores the current location of player
        Location next = loc.getAdjacentLocation(0); // it chooses up by default
        
        if(Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("l")) // player chooses right
        {
            next = loc.getAdjacentLocation(90); // gets actor to the east
        }
        if(Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("k")) // player chooses down
        {
            next = loc.getAdjacentLocation(180); // gets actor to the south
        }
        if(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("h")) // player chooses left
        {
            next = loc.getAdjacentLocation(270); // gets actor to the west
        }

        GridActor neighbor = gr.get(next);
        if(neighbor instanceof Fountain)
        {
            setText("You drink from the muddy water.\n");

            // chooses your "reward"
            double prob = Math.random() * luck;

            if(prob < 0.2)
            {
                addToText("The water goes up your nose and you lose 1 hp!\n");
                takeDamage(1);
            }
            
            getWorld().addObject(getMsgbox(), 36, 5); // display at coordinates of your choice 
        }

     }
     
     /**
      * Initializes a new world and transports player there
      */
     public void desend()
     {
         
     }
     
     /**
      * Picks up an item and adds it to an inventory
      */
     /*public void pickup()
     {
        Grid<GridActor> gr = getGrid();
        Location loc = getLocation(); // stores the current location of player
        Location next = loc.getAdjacentLocation(0); // it chooses up by default
        
        if(Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("l")) // player chooses right
        {
            next = loc.getAdjacentLocation(90); // gets actor to the east
        }
        if(Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("k")) // player chooses down
        {
            next = loc.getAdjacentLocation(180); // gets actor to the south
        }
        if(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("h")) // player chooses left
        {
            next = loc.getAdjacentLocation(270); // gets actor to the west
        }

        GridActor neighbor = gr.get(next);
        
        // adds a mushroom to your inventory
        //if(neighbor instanceof Mushroom)
            //int i;
            //neighbor.addItem(neighbor, "Mushroom");
     }*/
     
    //causes the player's hp to reduce accordingly
     public static void takeDamage(int dmg)
     {
        hp -= dmg;    
     }
     
     /**
      * @return the number of moves the player has done
      */
    public int getTime()
    {
        return count;
    }
    
     /**
      * @return current hit points of player
      */
    public int getHP()
    {
        return hp;
    }
     
    
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Kitten here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Kitten extends Monster
{
    private boolean moved = false; 
    private boolean attacking = true;
    private int hp, lvl;
    int aggression = 50;
    int range = 1;
    int damage = 0;
    double accuracy = 0.6; //0 to 1
    private String text = "";
    private Message msgbox = ActorWorld.getMessageBox(); // getting the messagebox object  
    
    //creates a dwarf with stats
    public Kitten()
    {
        hp = 5;
        lvl = 1;
    }
    
    public void act()
    {
        //ArrayList<GridActor> actors = super.getActors();
        //processActors(actors);
        if(true)
        {
            if(!attacking)
                wander();
            if(attacking)
                attack(ActorWorld.getPlayer());
        }
    }
    public void wander()
    {   
        int rand = ((int)(Math.random()*4));
        Location next = getLocation().getAdjacentLocation(rand*90);
        Grid<GridActor> gr = getGrid();
        switch(rand) 
        {
        case 0: if(gr.isValid(next) && canWander(rand*90))
                moveTo(next);
                break;
        case 1: if(gr.isValid(next) && canWander(rand*90))
                moveTo(next);
                break;
        case 2: if(gr.isValid(next) && canWander(rand*90))
                moveTo(next);
                break;
        case 3: if(gr.isValid(next) && canWander(rand*90))
                moveTo(next);
                break;
        }
        if(getDistanceToPlayer(ActorWorld.getPlayer()) <= aggression)
            attacking = true;
    }
    
    
    /**
     * Tests whether the monster can move into a location that is empty or
     * contains an item.
     * @return true if the monster can move.
     */
    public boolean canWander(int direction)
    {
        Grid<GridActor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(direction);
        if (!gr.isValid(next))
            return false;
        GridActor neighbor = gr.get(next);
        return (neighbor == null) || (neighbor instanceof OpenDoor);
        // ok to move into empty location
        // not ok to move onto any other actor
    }
    
    public void attack(Player player)
    {
        double prob = Math.random();
        int dmg = (int) (Math.random() * damage); //randomizes the damage given to the player
        
        
        //Attacks if within the attack range (limited to 1 in our limited version)
        if(getDistanceToPlayer(ActorWorld.getPlayer()) == range)
        {
        // 60% chance to hit attack
        if((prob > accuracy)/* && (target instanceof Player)*/)
        {
            text = "The kitten paws at you.";
            Player.takeDamage(dmg);
        }
        // 40% chance to miss attack
        if((prob <= accuracy)/* && (target instanceof Player)*/)
        {
            text = "The kitten meows.";
        }
        
        msgbox.setText(text); // calling the method that changes the text of the message  
        getWorld().addObject(msgbox, 23, 40); // display at coordinates of your choice 
        }
        
        else //Player is in sight
        {
            //Move closer
            
        Location playerLoc = player.getLoc();
        int playerY = playerLoc.getRow();
        int playerX = playerLoc.getCol();
        Location monsterLoc = this.getLocation();
        int monsterY = monsterLoc.getRow();
        int monsterX = monsterLoc.getCol();
        
        
         int xDiff = monsterX-playerX;
         int yDiff = monsterY-playerY;
         
         if(prob>0.5)//randomly choose which direction to go
         {
             
             if(yDiff > 0)
             {
                 Location next = getLocation().getAdjacentLocation(0);          //move up
                 Grid<GridActor> gr = getGrid();
                 if(gr.isValid(next) && canWander(0))
                 moveTo(next);
             }
             else if (yDiff < 0)
             {
                 Location next = getLocation().getAdjacentLocation(180);       //move down
                 Grid<GridActor> gr = getGrid();
                 if(gr.isValid(next) && canWander(180))
                 moveTo(next);
             }
         }
         else if(!(prob>0.5))//randomly choose which direction to go
         {
             
             if(xDiff > 0)
             {
                 Location next = getLocation().getAdjacentLocation(270);          //move up
                 Grid<GridActor> gr = getGrid();
                 if(gr.isValid(next) && canWander(270))
                 moveTo(next);
             }
             else if (xDiff < 0)
             {
                 Location next = getLocation().getAdjacentLocation(90);       //move down
                 Grid<GridActor> gr = getGrid();
                 if(gr.isValid(next) && canWander(90))
                 moveTo(next);
             }
         }
    
    
        if(getDistanceToPlayer(ActorWorld.getPlayer()) > aggression)
            attacking = false;
    }
}
    
    public int getDistanceToPlayer(Player player)
    {
        Location playerLoc = player.getLoc();
        int playerY = playerLoc.getRow();
        int playerX = playerLoc.getCol();
        Location monsterLoc = this.getLocation();
        int monsterY = monsterLoc.getRow();
        int monsterX = monsterLoc.getCol();
        return Math.abs(Math.abs((monsterY-playerY)) + Math.abs((monsterX-playerX)));
    }
}

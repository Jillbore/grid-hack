/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 */

/*
 * Slightly modified for use in Greenfoot.
 * @author Michael Kolling
 * @author Poul Henriksen
 */

import java.awt.Color;
import greenfoot.GreenfootImage;

/**
 * An <code>GridActor</code> is an entity with a color and direction that can act.
 * <br />
 * The API of this class is testable on the AP CS A and AB exams.
 */
public class GridActor extends greenfoot.Actor  
{
    private Location location;
    private Color color;

    /**
     * Constructs a blue actor that is facing north.
     */
    public GridActor()
    {
        //color = Color.BLUE;
        setDirection(Location.NORTH);
        location = null;
    }

    /**
     * Gets the color of this actor.
     * @return the color of this actor
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * Sets the color of this actor.
     * @param newColor the new color
     */
    public void setColor(Color newColor)
    {
        color = newColor;
    }

    /**
     * Gets the current direction of this actor.
     * @return the direction of this actor, an angle between 0 and 359 degrees
     */
    public int getDirection()
    {
        return getRotation();
    }

    /**
     * Sets the current direction of this actor.
     * @param newDirection the new direction. The direction of this actor is set
     * to the angle between 0 and 359 degrees that is equivalent to
     * <code>newDirection</code>.
     */
    public void setDirection(int newDirection)
    {
        int direction = newDirection % Location.FULL_CIRCLE;
        if (direction < 0)
            direction += Location.FULL_CIRCLE;
            
        setRotation(direction);  //Greenfoot: Set the rotation for greenfoot                       
    }

    /**
     * Gets the grid in which this actor is located.
     * @return the grid of this actor, or <code>null</code> if this actor is
     * not contained in a grid
     */
    public Grid<GridActor> getGrid()
    {
        return ((ActorWorld)getWorld()).getGrid();
    }

    /**
     * Gets the location of this actor. <br />
     * Precondition: This actor is contained in a grid
     * @return the location of this actor
     */
    public Location getLocation()
    {
        return location;
    }

    /**
     * Puts this actor into a grid. If there is another actor at the given
     * location, it is removed. <br />
     * Precondition: (1) This actor is not contained in a grid (2)
     * <code>loc</code> is valid in <code>gr</code>
     * @param gr the grid into which this actor should be placed
     * @param loc the location into which the actor should be placed
     */
    public void putSelfInGrid(Grid<GridActor> gr, Location loc)
    {
        if (getWorld() != null)
            throw new IllegalStateException(
                    "This actor is already contained in a grid.");
        gr.put(loc, this);
        location = loc;
    }
    
    /**
     * Removes this actor from its grid. <br />
     * Precondition: This actor is contained in a grid
     */
    public void removeSelfFromGrid()
    {
        if (getWorld() == null)
            throw new IllegalStateException(
                    "This actor is not contained in a grid.");
        if (getGrid().get(location) != this)
            throw new IllegalStateException(
                    "The grid contains a different actor at location "
                            + location + ".");               
        getGrid().remove(location);
        location = null;
    }

    /**
     * Moves this actor to a new location. If there is another actor at the
     * given location, it is removed. <br />
     * Precondition: (1) This actor is contained in a grid (2)
     * <code>newLocation</code> is valid in the grid of this actor
     * @param newLocation the new location
     */
    public void moveTo(Location newLocation)
    {
        Grid<GridActor> grid = getGrid();
        if (grid == null)
            throw new IllegalStateException("This actor is not in a grid.");
         
        if (grid.get(location) != this)
            throw new IllegalStateException(
                    "The grid contains a different actor at location "
                            + location + ".");
                        
        if (!grid.isValid(newLocation))
            throw new IllegalArgumentException("Location " + newLocation
                    + " is not valid.");

        if (newLocation.equals(location))
            return;
        grid.remove(location);
        location = newLocation;
        grid.put(location, this);

    }

    /**
     * Reverses the direction of this actor. Override this method in subclasses
     * of <code>GridActor</code> to define types of actors with different behavior
     * 
     */
    public void act()
    {
        setDirection(getDirection() + Location.HALF_CIRCLE);        
    }

    /**
     * Creates a string that describes this actor.
     * @return a string with the location, direction, and color of this actor
     */
    public String toString()
    {
        return getClass().getName() + "[location=" + location + ",direction="
                + getRotation() + ",color=" + color + "]";
    }
    
   
    
    /**
     * For Greenfoot.
     * <p>
     * 
     * Second initialization method in Greenfoot. Updates the
     * environment when objects are added to the world.
     * @param world    world where objects are added.
     */
    protected void addedToWorld(greenfoot.World world)  
    {
        location = new Location(getY(), getX());
    }
    
    /**
     * For Greenfoot.
     * <p>
     * 
     * Overrides setLocation so that setting the location from greenfoot 
     * changes the location in the grid.
     * 
     */
    public void setLocation(int x, int y) {
        Location newLocation=new Location( y, x);
        if (getGrid() != null && ! (getX() == x && getY() == y)) {
            // Check if there are any objects at the new location.           
            Object o = getGrid().get(newLocation);
            if(o == null) {
                // In GridWorld you can only put the Actor in a cell that is empty.
                super.setLocation(x, y);
                location = new Location(y, x);
            }
        } else if (getWorld() != null){  
            super.setLocation(x, y);
            location = newLocation;
        }
    }   
    
    /**
     * Overrides getImage so that setting the color will change the image.
     * @return    the Greenfoot image of this actor.
     */
    public GreenfootImage getImage()
    {
        return ColoredImage.getImage(this, super.getImage(), getColor());
    }
}
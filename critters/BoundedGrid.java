/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
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
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */

/*
 * Greenfoot note: This class has been modified for the Greenfoot version. Instead of
 * holding the grid itself, it is just a facade for the Greenfoot world (which has a
 * grid built in).
 * 
 * @author Michael Kolling
 */
import java.util.List;
import java.util.ArrayList;

/**
 * A <code>BoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class BoundedGrid<E> extends AbstractGrid<E>
{
    private ActorWorld world;
    
    /**
     * Constructs an empty bounded grid with the given dimensions.
     */
    public BoundedGrid(ActorWorld world)
    {
        this.world = world;
    }

    public int getNumRows()
    {
        return world.getHeight();
    }

    public int getNumCols()
    {
        return world.getWidth();
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {
            for (int c = 0; c < getNumCols(); c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }

        return theLocations;
    }

    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");

        List occupants=getObjectsAt(loc);
        if (occupants.isEmpty())
            return null;
        else
            return (E) occupants.get(0);
    }
    
    /**
     * The implementation of this method differs a bit from the implementation in 
     * greenfoot.World. When objects are rotated, greenfoot.World would see the
     * GridActors as occupying several grid locations. That is not what we want for
     * GridWorld, hence this method will only return objects that have the given 
     * logical position, ignoring the extent of the image. 
     */
    private ArrayList<E> getObjectsAt(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");

        ArrayList<E> actors = new ArrayList<E>();
        List occupants = world.getObjectsAt(loc.getCol(), loc.getRow(), null);
        for (int i=0; i<occupants.size(); i++)
        {
            if (((GridActor)occupants.get(i)).getLocation ().equals(loc))
            {
                actors.add((E)occupants.get(i));
            }
        }
         return actors;
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = get(loc);
        // make sure old objects are removed, since we allow only one object in each location
        List occupants = getObjectsAt(loc);
        world.removeObjects(occupants);
        
        world.addObject((GridActor)obj, loc.getCol(), loc.getRow());
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.       
        E r = get(loc);
        world.removeObject((GridActor)r);
        return r;
    }
   
}

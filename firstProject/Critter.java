import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
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

 
import java.util.ArrayList;

/**
 * A <code>Critter</code> is an actor that moves through its world, processing
 * other actors in some way and then picking a new location. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class Critter extends GridActor
{
    /**
     * A critter acts by getting a list of its neighbors, processing them,
     * getting locations to move to, selecting one of them, and moving to the
     * selected location.
     */
    public void act()
    {
        if (getGrid() == null)
            return;
        ArrayList<GridActor> actors = getActors();
        processActors(actors);
        ArrayList<Location> moveLocs = getMoveLocations();
        Location loc = selectMoveLocation(moveLocs);
        makeMove(loc);
    }

    /**
     * Gets the actors for processing. Implemented to return the actors that
     * occupy neighboring grid locations. Override this method in subclasses to
     * look elsewhere for actors to process.<br />
     * Postcondition: (1) The selected actors are contained in the same grid as
     * this critter. (2) The state of all actors is unchanged.
     * @return a list of actors that this critter wishes to process.
     */
    public ArrayList<GridActor> getActors()
    {
        return getGrid().getNeighbors(getLocation());
    }

    /**
     * Processes selected actors. Implemented to "eat" (i.e. remove) selected
     * actors that are not rocks or critters. Override this method in subclasses
     * to process actors in a different way. <br />
     * Precondition: All elements of <code>actors</code> are contained in the
     * same grid as this critter. <br />
     * Postcondition: (1) The state of all grid occupants other than this
     * critter and the elements of <code>actors</code> is unchanged. New
     * occupants may be added to empty locations. (2) The location of this
     * critter is unchanged.
     * @param actors the actors to be processed
     */
    public void processActors(ArrayList<GridActor> actors)
    {
        for (GridActor a : actors)
        {
            if (!(a instanceof Critter))
                a.removeSelfFromGrid();
        }
    }

    /**
     * Gets the possible locations for the next move. Implemented to return the
     * empty neighboring locations. Override this method in subclasses to look
     * elsewhere for move locations.<br />
     * Postcondition: (1) The move locations are valid in the grid of this
     * critter. (2) The state of all actors is unchanged.
     * @return a list of possible locations for the next move
     */
    public ArrayList<Location> getMoveLocations()
    {
        return getGrid().getEmptyAdjacentLocations(getLocation());
    }

    /**
     * Selects the location for the next move. Implemented to randomly pick one
     * of the possible locations, or to return the current location if
     * <code>locs</code> has size 0. Override this method in subclasses that
     * have another mechanism for selecting the next move location. <br />
     * Precondition: All locations in <code>locs</code> are valid in the grid
     * of this critter. <br />
     * Postcondition: (1) The returned location is an element of
     * <code>locs</code>, this Critter's current location, or
     * <code>null</code>. (2) The state of all actors is unchanged.
     * @param locs the possible locations for the next move
     * @return the location that was selected for the next move.
     */
    public Location selectMoveLocation(ArrayList<Location> locs)
    {
        int n = locs.size();
        if (n == 0)
            return getLocation();
        int r = (int) (Math.random() * n);
        return locs.get(r);
    }

    /**
     * Moves this critter to the given location. Implemented to remove this
     * critter from its grid if <code>loc</code> is <code>null</code>, and
     * to move to <code>loc</code> otherwise. Override this method in
     * subclasses that want to carry out other actions (for example, turning
     * this critter or adding occupants in empty locations). <br />
     * Precondition: <code>loc</code> is valid in the grid of this critter or
     * <code>null</code>.<br />
     * Postcondition: (1) The critter's location is <code>loc</code>. (2) The
     * state of all other grid occupants is unchanged. A new occupant may be
     * added to the critter's old location.
     * @param loc the location to move to
     */
    public void makeMove(Location loc)
    {
        if (loc == null)
            removeSelfFromGrid();
        else
            moveTo(loc);
    }
}

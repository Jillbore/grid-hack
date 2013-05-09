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
 */

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;

import java.util.ArrayList;
import java.awt.Color;

/**
 * An <code>ActorWorld</code> is occupied by actors. <br />
 * This class is not tested on the AP CS A and AB exams.
 */

public class ActorWorld extends World
{
    private static final int DEFAULT_ROWS = 10;
    private static final int DEFAULT_COLS = 10;

    private Grid<GridActor> grid;
    
    /**
     * Constructs an actor world with a default grid.
     */
    public ActorWorld()
    {
        super(DEFAULT_ROWS, DEFAULT_COLS, 49);
        paintGrid();
        grid = new BoundedGrid<GridActor>(this);
        boxBugSetup();
    }

    /**
     * Adds an actor to this world at a given location.
     * @param loc the location at which to add the actor
     * @param occupant the actor to add
     */
    public void add(Location loc, GridActor occupant)
    {
        occupant.putSelfInGrid(getGrid(), loc);
    }

    /**
     * Adds an occupant at a random location.
     * @param occupant the occupant to add
     */
    public void add(GridActor occupant)
    {
        Location loc = getRandomEmptyLocation();
        if (loc != null)
            add(loc, occupant);
    }

    /**
     * Removes an actor from this world.
     * @param loc the location from which to remove an actor
     * @return the removed actor, or null if there was no actor at the given
     * location.
     */
    public GridActor remove(Location loc)
    {
        GridActor occupant = getGrid().get(loc);
        if (occupant == null)
            return null;
        occupant.removeSelfFromGrid();
        return occupant;
    }

    /**
     * Gets a random empty location in this world.
     * @return a random empty location
     */
    public Location getRandomEmptyLocation()
    { 
        int rows = getHeight();
        int cols = getWidth();

        // get all valid empty locations and pick one at random
        ArrayList<Location> emptyLocs = new ArrayList<Location>();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
            {
                Location loc = new Location(i, j);
                if (grid.isValid(loc) && grid.get(loc) == null)
                    emptyLocs.add(loc);
            }
        if (emptyLocs.size() == 0)
            return null;
        int r = Greenfoot.getRandomNumber(emptyLocs.size());
        return emptyLocs.get(r);
    }
        
    /**
     * Greenfoot: Paint the grid pattern onto the background.
     */
    public Grid<GridActor> getGrid()
    {
        return grid;
    }
    
    /**
     * Greenfoot: Paint the grid pattern onto the background.
     */
    private void paintGrid()
    {
        GreenfootImage bg = getBackground();
        int cellSize = getCellSize();
        bg.setColor(java.awt.Color.BLACK);
        for (int x = 0; x < bg.getWidth(); x += cellSize) {
            bg.drawLine(x, 0, x, bg.getHeight());
        }
        for (int y = 0; y < bg.getHeight(); y += cellSize) {
            bg.drawLine(0, y, bg.getWidth(), y);
        }
        setBackground(bg);
    }

    /**
     * This method contains the code from the 'BoxBugRunner' class from the 
     * 'boxBug' example from the original version.
     */
    public void boxBugSetup() 
    {
        BoxBug alice = new BoxBug(6);
        alice.setColor(Color.ORANGE);
        BoxBug bob = new BoxBug(3);
        add(new Location(7, 8), alice);
        add(new Location(5, 5), bob);
    }
}
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
        critterSetup();
        //chameleonSetup();  // use any of these three here, or inovke from world menu
        //crabSetup();
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
     * This method contains the code from the 'CritterRunner' class from the 
     * 'critters' example from the original version.
     */
    public void critterSetup() 
    {
        removeObjects(getObjects(null));  // remove all existing objects
        add(new Location(7, 8), new Rock());
        add(new Location(3, 3), new Rock());
        add(new Location(2, 8), new Flower(Color.BLUE));
        add(new Location(5, 5), new Flower(Color.PINK));
        add(new Location(1, 5), new Flower(Color.RED));
        add(new Location(7, 2), new Flower(Color.YELLOW));
        add(new Location(4, 4), new Critter());
        add(new Location(5, 8), new Critter());
    }
    
    /**
     * This method contains the code from the 'ChameleonRunner' class from the 
     * 'critters' example from the original version.
     */
    public void chameleonSetup() 
    {
        removeObjects(getObjects(null));  // remove all existing objects
        add(new Location(7, 8), new Rock());
        add(new Location(3, 3), new Rock());
        add(new Location(2, 8), new Rock(Color.BLUE));
        add(new Location(5, 5), new Rock(Color.PINK));
        add(new Location(1, 5), new Rock(Color.RED));
        add(new Location(7, 2), new Rock(Color.YELLOW));
        add(new Location(4, 4), new ChameleonCritter());
        add(new Location(5, 8), new ChameleonCritter());
    }
    
    /**
     * This method contains the code from the 'CrabRunner' class from the 
     * 'critters' example from the original version.
     */
    public void crabSetup() 
    {
        removeObjects(getObjects(null));  // remove all existing objects
        add(new Location(7, 5), new Rock());
        add(new Location(5, 4), new Rock());
        add(new Location(5, 7), new Rock());
        add(new Location(7, 3), new Rock());
        add(new Location(7, 8), new Flower());
        add(new Location(2, 2), new Flower());
        add(new Location(3, 5), new Flower());
        add(new Location(3, 8), new Flower());
        add(new Location(6, 5), new Bug());
        add(new Location(5, 3), new Bug());
        add(new Location(4, 5), new CrabCritter());
        add(new Location(6, 1), new CrabCritter());
        add(new Location(7, 4), new CrabCritter());
    }
}
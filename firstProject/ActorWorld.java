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
import greenfoot.GreenfootSound;

import java.util.ArrayList;

/**
 * An <code>ActorWorld</code> is occupied by actors. <br />
 * This class is not tested on the AP CS A and AB exams.
 */

public class ActorWorld extends World
{
    private static final int DEFAULT_ROWS = 46;
    private static final int DEFAULT_COLS = 28;

    private Grid<GridActor> grid;
    
    /**
     * Constructs an actor world with a default grid.
     */
    public ActorWorld()
    {
        super(DEFAULT_ROWS, DEFAULT_COLS, 25);
        paintGrid();
        grid = new BoundedGrid<GridActor>(this);
        firstProjectSetup();
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
     * This method contains the code from the 'BugRunner' class from the 
     * 'firstProject' example from the original version.
     */
    public void firstProjectSetup() 
    {
        GreenfootSound bgm = new GreenfootSound("The Na�ve Bard.wav");  // background music
        bgm.playLoop();
        bgm.setVolume(50);
        //first digit is the row, each other digit is a column
        //together, they form an (x,y) point
        
        //You can thank me now, Michael; I thought of a genius system    -Tyler
        
        int [] [] map = {
        {0, 2, 3, 4, 5, 6, 7, 8, 9},
        {1, 1, 10},
        {2, 1, 10, 14, 15, 16},
        {3, 1, 11, 12, 13, 17, 18},
        {4, 2, 3, 4, 11, 16, 19, 20, 21, 22, 23, 24, 25, 26},
        {5, 1, 7, 9, 11, 13, 15, 20, 27},
        {6, 1, 4, 5, 7, 8, 9, 13, 14, 20, 27},
        {7, 2, 3, 9, 12, 27},
        {8, 1, 5, 6, 7, 10, 14, 15, 20, 27},
        {9, 2, 3, 5, 7, 12, 15, 16, 19, 20, 27},
        {10, 1, 7, 8, 9, 10, 12, 14, 17, 18, 19, 20, 21, 22, 23, 24, 27},
        {11, 1, 10, 12, 23, 24, 27},
        {12, 1, 8, 10, 12, 14, 15, 16, 17, 18, 20, 23, 24, 27},
        {13, 1, 7, 10,  11, 12, 18, 20, 22, 23, 27},
        {14, 1, 7, 9, 10, 12, 14, 18, 20, 21, 22, 23, 24, 25, 26, 27},
        {15, 2, 4, 5, 6, 7, 8, 12, 14, 15, 16, 17, 18, 19, 20, 23, 27},
        {16, 2, 4, 10, 12, 20, 23, 27},
        {17, 2, 6, 7, 9, 10, 13, 14, 16, 20, 27},
        {18, 2, 3, 4, 6, 10, 11, 14, 16, 20, 23, 27},
        {19, 2, 6, 11, 12, 16, 20, 23, 24, 26},
        {20, 2, 6, 13, 14, 16, 17, 18, 19, 20, 21, 22, 23, 24, 26},
        {21, 2, 6, 10, 11, 18, 21, 26},
        {22, 2, 4, 5, 6, 10, 11, 13, 14, 15, 16, 18, 21, 26},
        {23, 2, 4, 10, 11, 15, 16, 22, 26},
        {24, 2, 4, 6, 10, 16, 18, 22, 26},
        {25, 2, 6, 7, 8, 9, 10, 16, 18, 21, 26},
        {26, 2, 4, 5, 6, 7, 8, 10, 16, 18, 21, 24, 26},
        {27, 1, 10, 11, 15, 18, 21, 22, 23, 26},
        {28, 1, 10, 12, 14, 15, 18, 19, 20, 24, 26},
        {29, 1, 10, 11, 15, 18, 26},
        {30, 1, 10, 16, 18, 26},
        {31, 1, 10, 11, 15, 16, 18, 26},
        {32, 2, 3, 4, 5, 6, 7, 8, 9, 11, 15, 16, 18, 22, 26},
        {33, 11, 15, 16, 18, 23, 26},
        {34, 10, 16, 18, 24, 26},
        {35, 11, 15, 16, 24, 26},
        {36, 12, 14, 17, 18, 19, 20, 21, 22, 23, 24, 25}};
        
        for(int r = 0; r < map.length; r++)     //passes each set of points through build()
        build(map[r]);
        
        add(new Location(2, 3), new Fountain()); // creates a fountain from which to drink
        add(new Location(2, 2), new Player());  //creates the player
        
    }
    
    public void build(int row[])
    {
        for(int k = 1; k < row.length; k++)//places a wall at each point made by the array
        add(new Location(row[k]-1, row[0]), new Wall());
    }
}

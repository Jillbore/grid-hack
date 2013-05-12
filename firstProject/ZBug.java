import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ZBug here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ZBug  extends Bug
{
    private int steps;
	private int sideLength;
	private int count;

	public ZBug(int length)
	{
		steps = 0;
		sideLength = length;
		count = 0;
		setDirection(Location.EAST);
	}

	public void act()
	{
		if (count != 3)
		{
			if (!canMove())
				count = 3;

			else if (steps < sideLength && canMove())
			{
				move();
				steps++;
			}

			else
			{
				steps = 0;
				count ++;

				if (count == 1)
					setDirection(Location.SOUTHWEST);
				else if (count == 2)
					setDirection(Location.EAST);
			}
		}
	}
}

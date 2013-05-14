import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Dwarf here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dwarf extends Monster
{
    private boolean moved = false; 
    private int hp, lvl;
    
    public Dwarf()
    {
        hp = 5;
        lvl = 1;
    }
    
    public void attack(GridActor target)
    {
        double prob = Math.random();
        int dmg = (int) (Math.random() * 3);
        
        // 40% chance to miss attack
        if((prob > 0.4) && (target instanceof Player))
        {
            System.out.println("The dwarf throws booze at your face, dealing " + dmg + " damage");
            Player.takeDamage(dmg);
        }
    }
}

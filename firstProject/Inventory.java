import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Inventory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Inventory extends GridActor
{
    public static ArrayList<GridActor> list;
    
    public Inventory()
    {
        list = new ArrayList<GridActor>();
        list.add(new Wall());
    }
    
    /**
     * Adds an item to your inventory
     */
    public void addItem(GridActor item, String itemName)
    {
        list.add(item);
        setText("" + itemName + " has been added to your inventory.");
        getWorld().addObject(getMsgbox(), 23, 40); // display at coordinates of your choice 
    }
    
    /**
     * Prints out inventory to see
     */
    public static void showInv()
    {
        System.out.println("------------------------");
        for(int k = 0; k < list.size(); k++)
            System.out.println("| " + list.get(k) + "   |");
        System.out.println("------------------------");
    }
}

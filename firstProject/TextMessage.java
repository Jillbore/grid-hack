import greenfoot.*;  // (World"," Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.*;
/**
 * Write a description of class TextMessage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TextMessage  extends Actor
{
    String characterArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZ:   0123456789";
    GreenfootImage font = new GreenfootImage("font.gif");
    static GreenfootImage character = new GreenfootImage(40,30);
    GreenfootImage display = new GreenfootImage(400,30);
  
    int timer = 0;
    final int time = 180;
    
    public TextMessage(String message,int delay){
        timer = -delay;
        int length = message.length();
        for(int i=0;i<length;i++){
            String subChar = message.substring(i,i+1);
            int offset = characterArray.indexOf(subChar);
            int xoff = (offset%6)*52 + 5;
            int yoff = (offset/6)*53 + 13;
            character.clear();
            character.drawImage(font,-xoff,-yoff);
            display.drawImage(character,i*24+(200-(length*12-12))-20,0);
        }
        setImage(display);
        display.setTransparency(0);
    }
    
    public void act() 
    {
        timer++;
        if(timer<128 && timer>0){
            display.setTransparency(timer*2);
        }
        if(timer>time){
            display.setTransparency((time-timer+127)*2);
        }
        if(timer-127==time){
            getWorld().removeObject(this);
        }
    }    
}

import greenfoot.*;
import greenfoot.GreenfootImage; 
import java.awt.Color;  
  
public class Message extends Actor  
{  
    public Message()  
    {  
        updateImage("");
    }  
      
    public Message(String text)  
    {  
        updateImage(text);  
    }  
      
    public void setText(String text)  
    {  
        updateImage(text);  
    }  
      
    private void updateImage(String text)  
    {  
        setImage(new GreenfootImage(text, 20, Color.black, Color.white));  
    }  
}  
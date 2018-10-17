package com.lanmosoft.util;
import java.awt.AWTException;  
import java.awt.Dimension;  
import java.awt.Robot;  
import java.awt.Toolkit;  
import java.awt.event.InputEvent;  
  
public class App
{  
    public static void main(String[] args)  
    {  
        try  
        {  
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  
            Robot robot = new Robot();  
            robot.mouseMove(screenSize.width - 30, 5);  
            robot.delay(1000);  
            robot.mousePress(InputEvent.BUTTON1_MASK);  
            robot.delay(300);  
            robot.mouseRelease(InputEvent.BUTTON1_MASK);  
        }  
        catch (AWTException e)  
        {  
            e.printStackTrace();  
        }  
    }  
}  
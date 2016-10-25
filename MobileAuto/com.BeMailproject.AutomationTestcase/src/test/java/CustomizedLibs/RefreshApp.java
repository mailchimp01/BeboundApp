package CustomizedLibs;

import org.openqa.selenium.Dimension;

import io.appium.java_client.android.AndroidDriver;

public class RefreshApp {
	public void RefreshScreen(AndroidDriver driver) throws InterruptedException{
		//Get the size of screen. 
		Dimension size = driver.manage().window().getSize(); 
		//Find swipe start and end point from screen's width and height. 
		//Find starty point which is at bottom side of screen. 
		int starty = (int) (size.height * 0.90); 
		//Find endy point which is at top side of screen. 
		int endy = (int) (size.height * 0.20); 
		//Find horizontal point where you wants to swipe. It is in middle of screen width. 
		int startx = size.width / 2; 
		//Swipe from Bottom to Top. driver.swipe(startx, starty, startx, endy, 3000); 
		Thread.sleep(10000); 
		//Swipe from Top to Bottom. 
		driver.swipe(startx, endy, startx, starty, 3000);
	}

}

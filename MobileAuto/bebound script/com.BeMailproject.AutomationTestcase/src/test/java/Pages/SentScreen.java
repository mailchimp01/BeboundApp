package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class SentScreen {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(className="android.widget.ImageButton")
	WebElement btnHome;
	
	
	public SentScreen(WebDriver driver){
		this.driver=driver;
		wait=new WebDriverWait(driver, 120);
		PageFactory.initElements(driver, this);
	}
	
	
	public void clickHomeButton(){
		btnHome.click();
	}
	
	/**
	 * this function is used for click HOme button. 
	 * to go back to Home Screen
	 */
	public void backToHomeScreen(){
		clickHomeButton();
	}
	
	/**
	 * this function is use for refresh scren
	 * @param dirver
	 * @throws InterruptedException
	 */
	public void refreshScreen(WebDriver dirver) throws InterruptedException{
		
		Dimension size=driver.manage().window().getSize();
		int startx = (int) (size.width * 0.70);
		int endx = (int) (size.width * 0.30);
		int starty = size.height / 2;

		//Swipe from Right to Left.
		((AndroidDriver)driver).swipe(startx, starty, endx, starty, 6000);
		Thread.sleep(60000);
	}
	
	
	/**
	 * this function is used for check if an email exist in the current screen.
	 * it will check based on the subject of the mail
	 * @param subject 
	 * @return return true if exist, return false otherwise.
	 */
	public boolean checkIfEmailExist(String subject){
		boolean isExist=true;
		if(driver.findElements(By.name(subject)).isEmpty())
		{
			isExist=false;
		}
		return isExist;
	}
	
	
	/**
	 * this function is used for clicking on an email based on the subject of the email
	 * @param subject
	 */
	public void clickOnAnEmail(String subject){
		try{
		driver.findElement(By.name(subject)).click();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * this function is used for wait a mail is load in the folder. 
	 * @param byName is the subject of the mail
	 */
	public void waitUntilMailDisplayed(String byName){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(byName)));
	}
	
	
	
	
}

package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InboxScreen {
	
	WebDriver driver;
	WebDriverWait wait;
	
	// button Compose 
	@FindBy(id="com.bebound.mail:id/action_compose")
	WebElement btnCompose;
	
	//button Home
	@FindBy(className="android.widget.ImageButton")
	WebElement btnHome;
	
	//label Subject
	@FindBy(id="com.bebound.mail:id/lbl_subject")
	WebElement lblSubject;
	
	
	
	
	/** 
	 * this function is used for click compose button at the inbox screen
	 */
		public void clickComposeButton(){
		btnCompose.click();
	}
	
		
	//click home button
	public void clickHomeHomeButton(){
		btnHome.click();
	}
	
	public void goToHomeScreen(){
		clickHomeHomeButton();
	}
	
	public InboxScreen(WebDriver driver){
		this.driver=driver;
		wait=new WebDriverWait(driver, 120);
		PageFactory.initElements(driver, this);
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
	public void clickOnAnEmail(String Subject){
		try{
		driver.findElement(By.name(Subject)).click();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	
	/**
	 * this function is used for wait a mail is load in the folder. 
	 * @param byName is the subject of the mail
	 */
	public void waitUntilMailIsDisplayed(String byName){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(byName)));
	}
	
	
	

}

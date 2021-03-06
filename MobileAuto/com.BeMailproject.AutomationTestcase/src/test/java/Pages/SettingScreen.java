package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SettingScreen {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(name="Add mailbox")
	WebElement btnAddMailBox;
	
	
	
	public SettingScreen(WebDriver driver){
		this.driver=driver;
		wait=new WebDriverWait(driver, 120);
		PageFactory.initElements(driver, this);
	}
	
	public void clickAddMailBox(){
		btnAddMailBox.click();
	}
	
	

}

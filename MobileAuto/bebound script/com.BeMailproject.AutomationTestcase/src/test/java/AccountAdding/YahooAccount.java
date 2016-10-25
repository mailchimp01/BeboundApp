package AccountAdding;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YahooAccount {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(id="com.bebound.mail:id/edit_email")
	WebElement txtEmail;
	
	@FindBy(id="com.bebound.mail:id/edit_password")
	WebElement txtPassword;
	
	@FindBy(id="com.bebound.mail:id/bt_add")
	WebElement btnAdd;
	
	
	@FindBy(id="com.bebound.mail:id/bt_next")
	WebElement btnNextToMailBox;
	
	public YahooAccount(WebDriver driver){
		this.driver=driver;
		wait=new WebDriverWait(driver, 120);
		PageFactory.initElements(driver, this);
	}
	
	private void setEmail(String pEmail){
		wait.until(ExpectedConditions.visibilityOf(txtEmail));
		txtEmail.sendKeys(pEmail);
	}
	private void setPassword(String pPassword){
		wait.until(ExpectedConditions.visibilityOf(txtPassword));
		txtPassword.sendKeys(pPassword);
	}
	private void clickAddMailBoxButton(){
		wait.until(ExpectedConditions.elementToBeClickable(btnAdd));
		btnAdd.click();
	}
	
	private void clickToGoToMailBox(){
		wait.until(ExpectedConditions.elementToBeClickable(btnNextToMailBox));
		btnNextToMailBox.click();
	}
	
	public void loginYahoo(String pEmail, String pPassword){
		setEmail(pEmail);
		setPassword(pPassword);
		clickAddMailBoxButton();
	}
	
	public void goToMailBox(){
		clickToGoToMailBox();
	}
}

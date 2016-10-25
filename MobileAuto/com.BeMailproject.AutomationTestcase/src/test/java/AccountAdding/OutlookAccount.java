package AccountAdding;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OutlookAccount {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(id="i0116")
	WebElement txtEmail;
	
	@FindBy(id="i0118")
	WebElement txtPassword;
	
	@FindBy(id="idSIButton9")
	WebElement btnSignIn;
	
	// button yes at screen "Let this app access your info"
	@FindBy(id="idBtn_Accept")
	WebElement btnYes;
	
	// button next to go to the mail box
	@FindBy(id="com.bebound.mail:id/bt_next")
	WebElement btnNext;
	
	public OutlookAccount(WebDriver driver){
		this.driver=driver;
		wait=new WebDriverWait(driver, 120);
		PageFactory.initElements(driver, this);
	}
	
	private void setEmail(String pEmail){
		txtEmail.sendKeys(pEmail);
	}
	
	private void setPassword(String pPassword){
		txtPassword.sendKeys(pPassword);
	}
	
	private void clickSignInButton(){
		btnSignIn.click();
	}
	
	
	private void clickNextToMailBox(){
		//wait.until(ExpectedConditions.visibilityOf(btnNext));
		wait.until(ExpectedConditions.elementToBeClickable(btnNext));
		btnNext.click();
	}
	private void clickYesButtonToAccessInfo() throws InterruptedException{
		Thread.sleep(10000);
		btnYes.click();
	}
	
	public void loginToOutlook(String pEmail,String pPassword) throws InterruptedException{
		setEmail(pEmail);
		setPassword(pPassword);
		clickSignInButton();
		clickYesButtonToAccessInfo();
	}
	
	public void goToMailBox() throws InterruptedException{
		clickNextToMailBox();
	}
}

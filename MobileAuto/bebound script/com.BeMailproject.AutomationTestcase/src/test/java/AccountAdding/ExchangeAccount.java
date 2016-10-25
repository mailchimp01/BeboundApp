package AccountAdding;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExchangeAccount {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(id="com.bebound.mail:id/edit_email")
	WebElement txtEmail;
	
	@FindBy(id="com.bebound.mail:id/edit_password")
	WebElement txtPassword;
		
	@FindBy(id="com.bebound.mail:id/bt_add")
	WebElement btnAdd;
	
	@FindBy(id="com.bebound.mail:id/bt_next")
	WebElement btnNexttoMailbox;
	
	public ExchangeAccount(WebDriver driver){
		this.driver=driver;
		wait=new WebDriverWait(driver, 120);
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * this function is used for setting the email for email text field
	 * @param pEmail email of exchange account
	 */
	private void setEmail(String pEmail){
		txtEmail.sendKeys(pEmail);
	}
	
	
	/**
	 * this function is used for setting the password for password text filed
	 * @param pPassword password of exchange account
	 */
	private void setPassword(String pPassword){
		txtPassword.sendKeys(pPassword);
	}
	
	
	/**
	 * this function is used for clicking the Add mail box button
	 */
	private void clickAddMailBoxButton(){
		btnAdd.click();
	}
	
	private void goToMailBox() throws InterruptedException{
		wait.until(ExpectedConditions.visibilityOf(btnNexttoMailbox));		
		btnNexttoMailbox.click();
	}
	
	
	/**
	 * this function is used for logining to Exchange server
	 * @param pEmail email of account
	 * @param pPassword password of account
	 * @throws InterruptedException
	 */
	public void loginToExchange(String pEmail,String pPassword) throws InterruptedException{
		setEmail(pEmail);
		setPassword(pPassword);
		clickAddMailBoxButton();
		goToMailBox();
	}
}

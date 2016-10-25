package AccountAdding;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class OtherAccount {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(id="com.bebound.mail:id/edit_email")
	WebElement txtEmail;
	
	@FindBy(id="com.bebound.mail:id/edit_password")
	WebElement txtPassword;
	
	@FindBy(id="com.bebound.mail:id/bt_more_details")
	WebElement btnMoreOption;
	
	@FindBy(id="com.bebound.mail:id/edit_username")
	WebElement txtUsername;
	
	@FindBy(id="com.bebound.mail:id/edit_server")
	WebElement txtServer;
	
	@FindBy(id="com.bebound.mail:id/spinner_server")
	WebElement ddownSeverType;
	
	@FindBy(id="com.bebound.mail:id/bt_add")
	WebElement btnAddMailBox;
	
	@FindBy(id="com.bebound.mail:id/bt_next")
	WebElement btnNext;
	
	public OtherAccount(WebDriver driver){
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
	
	private void clickAddMailBoxButton(){
		btnAddMailBox.click();
	}
	
	private void clickAddMoreSetupOption(){
		btnMoreOption.click();
	}
	
	private void setUserName(String pUsername){
		txtUsername.sendKeys(pUsername);
	}
	
	private void setServerName(String pServername){
		txtServer.sendKeys(pServername);
	}
	
	private void clickNextToMailBox(){
		//wait.until(ExpectedConditions.visibilityOf(btnNext));
		wait.until(ExpectedConditions.elementToBeClickable(btnNext));
		btnNext.click();
	}
	
	private void setServerType(String pServertype){
		driver.findElement(By.id("com.bebound.mail:id/spinner_server")).click();
		driver.findElement(By.name(pServertype)).click();
		
	}
	
	public void loginToOtherAccount(String pEmail, String pPassword,String pUsername,String pServername,String pServertype){
		setEmail(pEmail);
		setPassword(pPassword);
		clickAddMoreSetupOption();
		setUserName(pUsername);
		setServerName(pServername);
		((AndroidDriver)driver).scrollTo("IMAP");
		setServerType(pServertype);
		clickAddMailBoxButton();
		clickNextToMailBox();
	}

}

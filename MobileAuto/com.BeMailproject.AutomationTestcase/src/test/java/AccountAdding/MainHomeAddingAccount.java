package AccountAdding;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.WebDriverWait;

public class MainHomeAddingAccount {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath="//android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='0']/android.widget.RelativeLayout[@index='0']/android.widget.ImageView[@index='0']")
	WebElement lbelGmail;
	
	@FindBy(xpath="//android.widget.LinearLayout[@index='1']/android.widget.RelativeLayout[@index='0']/android.widget.RelativeLayout[@index='0']/android.widget.ImageView[@index='0']")
	WebElement lbelExchange;
	
	@FindBy(xpath="//android.widget.LinearLayout[@index='2']/android.widget.RelativeLayout[@index='0']/android.widget.RelativeLayout[@index='0']/android.widget.ImageView[@index='0']")
	WebElement lbelYahoo;
	
	@FindBy(xpath="//android.widget.LinearLayout[@index='3']/android.widget.RelativeLayout[@index='0']/android.widget.RelativeLayout[@index='0']/android.widget.ImageView[@index='0']")
	WebElement lbelOutlook;
	
	@FindBy(name="Other")
	WebElement lbelOther;
	
	public MainHomeAddingAccount(WebDriver driver){
		this.driver=driver;
		wait=new WebDriverWait(driver, 120);
		PageFactory.initElements(driver, this);
	
	}
	
	/**
	 * this function is used for clicking the gmail account 
	 */
	public void selectGmailAccount(){
		lbelGmail.click();
	}
	
	
	/**
	 * this function is used for clicking exchange account
	 */
	public void selectExchangeAccount(){
		lbelExchange.click();
	}
	
	
	/**
	 * this function is used for clicking yahoo account
	 */
	public void selectYahooAccount(){
		lbelYahoo.click();
	}
	
	
	/**
	 * this function is used for clicking outlook account
	 */
	public void selectOutlookAccount(){
		lbelOutlook.click();
	}
	
	
	/**
	 * this function is used for selecting other account
	 */
	public void selectOtherAccount(){
		lbelOther.click();
	}
		
		
}

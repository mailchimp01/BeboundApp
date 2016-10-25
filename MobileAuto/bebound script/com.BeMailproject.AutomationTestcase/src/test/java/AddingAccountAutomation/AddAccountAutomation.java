package AddingAccountAutomation;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import AccountAdding.ExchangeAccount;
import AccountAdding.GmailAccount;
import AccountAdding.MainHomeAddingAccount;
import AccountAdding.OtherAccount;
import AccountAdding.OutlookAccount;
import AccountAdding.YahooAccount;
import CustomizedLibs.AccountInfo;
import CustomizedLibs.SwitchApp;
import Pages.HomeScreen;
import Pages.SettingScreen;
import io.appium.java_client.android.AndroidDriver;

public class AddAccountAutomation {
	
	AndroidDriver driver;
	AccountInfo objAccount;
	MainHomeAddingAccount objMainHomeAddingAccount;
	SwitchApp objSwitchApp;
	GmailAccount objGmail;
	ExchangeAccount objExchange;
	YahooAccount objYahoo;
	OutlookAccount objOutlook;
	OtherAccount objOther;
	HomeScreen objHome;
	SettingScreen objSetting;
	String fileName=System.getProperty("user.dir")+"/src/main/resources/Data.xlsx";
	
	public AddAccountAutomation(AndroidDriver driver){
		this.driver=driver;
	}
	
	public void addMailBoxAccount() throws IOException, InterruptedException{
		objAccount = new AccountInfo(fileName, "Accounts");
		objMainHomeAddingAccount= new MainHomeAddingAccount(driver);
		objSwitchApp= new SwitchApp(driver);
		objGmail= new GmailAccount(driver);
		objExchange= new ExchangeAccount(driver);
		objYahoo=new YahooAccount(driver);
		objOutlook=new OutlookAccount(driver);
		objOther=new OtherAccount(driver);
		objHome= new HomeScreen(driver);
		objSetting= new SettingScreen(driver);
		for(int i=1;i<=3;i++){
			// check the email type 
			String emailType=objAccount.getTypeOfEmail(i);
			AddingAccounts(i,emailType);
		}
	}
	
	private void AddingAccounts(int indexOfUser,String pemailType) throws InterruptedException{
			
		if(indexOfUser==3){
			System.out.println("sleep sometime");
			Thread.sleep(10000);
		}
		
		
		String emailAddress=objAccount.getAccountEmail(indexOfUser);
		String passWord=objAccount.getPassword(indexOfUser);
		
		if(pemailType.equals("Gmail")){
			objMainHomeAddingAccount.selectGmailAccount();
			addingGmailAccount(emailAddress, passWord);
		}
		else if(pemailType.equals("Exchange")){
			objMainHomeAddingAccount.selectExchangeAccount();
			addingExchangeAccount(emailAddress, passWord);
		}
		else if(pemailType.equals("Yahoo")){
			objMainHomeAddingAccount.selectYahooAccount();
			addingYahooAccount(emailAddress, passWord);
		}
		else if(pemailType.equals("Outlook")){
			objMainHomeAddingAccount.selectOutlookAccount();
			addingOutlookAccount(emailAddress, passWord);			
		}
		else if(pemailType.equals("Other")){
			String username=objAccount.getUserNameToLoginServer(indexOfUser);
			String servername=objAccount.getServerName(indexOfUser);
			String servertype=objAccount.getServerType(indexOfUser);
			objMainHomeAddingAccount.selectOtherAccount();
			addingOtherAccount(emailAddress,passWord,username,servername,servertype);
		}
		
		if(indexOfUser<3){
			gobackToAddAccountScreen();
		}
	}
	
	private void addingGmailAccount(String pEmailAddress, String pPassword) throws InterruptedException{
		objSwitchApp.switchToWebView();
		objGmail.loginGmail(pEmailAddress, pPassword);
		objSwitchApp.switchToNativeApp();
		objGmail.goToMailBox();
	}
	
	private void addingExchangeAccount(String pEmailAddress, String pPassword) throws InterruptedException{
		objExchange.loginToExchange(pEmailAddress, pPassword);		
	}
	
	private void addingYahooAccount(String pEmailAddress, String pPassword){
		objYahoo.loginYahoo(pEmailAddress, pPassword);
		objYahoo.goToMailBox();
	}
	
	private void addingOutlookAccount(String pEmailAddress, String pPassword) throws InterruptedException{
		objSwitchApp.switchToWebView();
		objOutlook.loginToOutlook(pEmailAddress, pPassword);
		objSwitchApp.switchToNativeApp();
		objOutlook.goToMailBox();
	}
	
	private void addingOtherAccount(String pEmailAddress, String pPassword, String pUsername, String pServerName, String pServerType){
		objOther.loginToOtherAccount(pEmailAddress, pPassword, pUsername, pServerName, pServerType);
	}
	
	private void gobackToAddAccountScreen(){
		objHome.clickHomeButton();
		objHome.clickSettingButton();
		objSetting.clickAddMailBox();
	}
	
	

}

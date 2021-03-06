package UnitTestforAddingAccount;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import AccountAdding.MainHomeAddingAccount;
import AccountAdding.OutlookAccount;
import AccountAdding.YahooAccount;
import CustomizedLibs.SetUpAndroidProfile;
import CustomizedLibs.SwitchApp;
import io.appium.java_client.android.AndroidDriver;

public class TestoutllookAccount {
	
	AndroidDriver driver;
	SetUpAndroidProfile objProfile;
	MainHomeAddingAccount mainHomeAddingAccountObj;
	OutlookAccount outlookObj;
	SwitchApp switchObj;
	String fileName=System.getProperty("user.dir")+"/src/main/resources/Data.xlsx";	
	
	
	@BeforeTest
	public void setUp() throws IOException, InterruptedException{
		System.out.println("--set up for env----");
		objProfile= new SetUpAndroidProfile(fileName,"PhoneProfile");
		DesiredCapabilities cap = objProfile.getDesizedCapability();
		driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		mainHomeAddingAccountObj= new MainHomeAddingAccount(driver);
		outlookObj= new OutlookAccount(driver);
		switchObj= new SwitchApp(driver);
		Thread.sleep(10000);
		mainHomeAddingAccountObj.selectOutlookAccount();	
	}
	
	
	@Test
	public void testingaddOutlook() throws InterruptedException{
		switchObj.switchToWebView();
		outlookObj.loginToOutlook("cdinhnguyen@outlook.com", "dinh123456");
		switchObj.switchToNativeApp();
		outlookObj.goToMailBox();
		
	}
	
	
	@AfterTest	
	public void tearDown(){
		System.out.println("--finish test case----");
		}
	

}

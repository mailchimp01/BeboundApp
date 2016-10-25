package UnitTestforAddingAccount;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import AccountAdding.ExchangeAccount;
import AccountAdding.MainHomeAddingAccount;
import AccountAdding.OutlookAccount;
import CustomizedLibs.SetUpAndroidProfile;
import CustomizedLibs.SwitchApp;
import io.appium.java_client.android.AndroidDriver;

public class TestExchangeAccount {
	AndroidDriver driver;
	SetUpAndroidProfile objProfile;
	MainHomeAddingAccount mainHomeAddingAccountObj;
	ExchangeAccount exChangeObj;
	SwitchApp switchObj;
	String fileName=System.getProperty("user.dir")+"/src/main/resources/Data.xlsx";	
	
	
	@BeforeTest
	public void setUp() throws IOException, InterruptedException{
		System.out.println("--set up for env----");
		objProfile= new SetUpAndroidProfile(fileName,"PhoneProfile");
		DesiredCapabilities cap = objProfile.getDesizedCapability();
		driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		mainHomeAddingAccountObj= new MainHomeAddingAccount(driver);
		exChangeObj= new ExchangeAccount(driver);
		switchObj= new SwitchApp(driver);
		Thread.sleep(10000);
		mainHomeAddingAccountObj.selectExchangeAccount();	
	}
	
	
	@Test
	public void testingaddOutlook() throws InterruptedException{
		exChangeObj.loginToExchange("testqa@be-bound.com", "bebound2016");		
	}
	
	
	@AfterTest	
	public void tearDown(){
		System.out.println("--finish test case----");
		}

}

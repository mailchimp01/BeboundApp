package UnitTestforAddingAccount;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import AccountAdding.MainHomeAddingAccount;
import AccountAdding.OtherAccount;
import AccountAdding.OutlookAccount;
import CustomizedLibs.SetUpAndroidProfile;
import CustomizedLibs.SwitchApp;
import io.appium.java_client.android.AndroidDriver;

public class TestotherAccount {
	AndroidDriver driver;
	SetUpAndroidProfile objProfile;
	MainHomeAddingAccount mainHomeAddingAccountObj;
	OtherAccount otherObj;
	SwitchApp switchObj;
	String fileName=System.getProperty("user.dir")+"/src/main/resources/Data.xlsx";	
	
	
	@BeforeTest
	public void setUp() throws IOException, InterruptedException{
		System.out.println("--set up for env----");
		objProfile= new SetUpAndroidProfile(fileName,"PhoneProfile");
		DesiredCapabilities cap = objProfile.getDesizedCapability();
		driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		mainHomeAddingAccountObj= new MainHomeAddingAccount(driver);
		otherObj= new OtherAccount(driver);
		switchObj= new SwitchApp(driver);
		
		Thread.sleep(10000);
		mainHomeAddingAccountObj.selectOtherAccount();
	}
	
	
	@Test
	public void testingaddOtherAccount() throws InterruptedException{
		otherObj.loginToOtherAccount("dinh.nguyenminh@elinext.com", "dinhw0rk1nvn", "dinh.nguyenminh@elinext.com", "mail.elinext.com", "IMAP");
	}
	
	
	@AfterTest	
	public void tearDown(){
		System.out.println("--finish test case----");
		}
}

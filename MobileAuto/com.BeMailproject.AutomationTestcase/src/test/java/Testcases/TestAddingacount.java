package Testcases;

import java.io.IOException;
import java.net.URL;
import java.util.Dictionary;
import java.util.Hashtable;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.chrome.handler.ChromedriverHandler;

import AddingAccountAutomation.AddAccountAutomation;
import CustomizedLibs.AccountInfo;
import CustomizedLibs.MailSetUp;
import CustomizedLibs.NetWorkUtility;
import CustomizedLibs.RefreshApp;
import CustomizedLibs.SetUpAndroidProfile;
import Pages.ComposeScreen;
import Pages.EmailDetailScreen;
import Pages.HomeScreen;
import Pages.InboxScreen;
import Pages.SentScreen;
import io.appium.java_client.android.AndroidDriver;

public class TestAddingacount {
	
	AndroidDriver driver;
	SetUpAndroidProfile objProfile;
	AccountInfo objAccount;
	HomeScreen objHome;
	AddAccountAutomation objAddAccount;
	String fileName=System.getProperty("user.dir")+"/src/main/resources/Data.xlsx";
	Dictionary<String,String> dict = new Hashtable<String,String>();
	RefreshApp objRefresh;
	
	
	@BeforeTest
	public void setUp() throws IOException, InterruptedException{
		ChromedriverHandler.chromeDriverHandlerThread().start();
		System.out.println("--set up for env----");
		objProfile= new SetUpAndroidProfile(fileName,"PhoneProfile");
		objAccount = new AccountInfo(fileName,"Accounts");
		
		
		
		DesiredCapabilities cap = objProfile.getDesizedCapability();
		driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		objHome= new HomeScreen(driver);
		objAddAccount= new AddAccountAutomation(driver);
		
		Thread.sleep(10000*2);
		
		// adding accounts automation
		objAddAccount.addMailBoxAccount();
			
	}
	
	@Test
	public void testing(){
		System.out.println("done");
	}
	
	@AfterTest
	public void tearDown(){
		ChromedriverHandler.chromeDriverHandlerThread().stop();
	}

}

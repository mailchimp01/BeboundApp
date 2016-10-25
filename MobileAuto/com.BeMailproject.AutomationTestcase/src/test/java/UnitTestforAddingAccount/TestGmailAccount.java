package UnitTestforAddingAccount;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.URL;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

import org.apache.commons.exec.util.StringUtils;
import org.apache.tools.ant.taskdefs.Sleep;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.Iterator;

import AccountAdding.GmailAccount;
import AccountAdding.MainHomeAddingAccount;
import CustomizedLibs.AccountInfo;
import CustomizedLibs.MailSetUp;
import CustomizedLibs.NetWorkUtility;
import CustomizedLibs.RefreshApp;
import CustomizedLibs.SetUpAndroidProfile;
import CustomizedLibs.SwitchApp;
import Pages.ComposeScreen;
import Pages.EmailDetailScreen;
import Pages.SentScreen;
import Pages.HomeScreen;
import Pages.InboxScreen;

import bsh.StringUtil;

import io.appium.java_client.android.AndroidDriver;


public class TestGmailAccount {
	AndroidDriver driver;
	SetUpAndroidProfile objProfile;
	MainHomeAddingAccount mainHomeAddingAccountObj;
	GmailAccount gmailObj;
	SwitchApp switchObj;
	String fileName=System.getProperty("user.dir")+"/src/main/resources/Data.xlsx";
	
	
	
	
	@BeforeTest
	public void setUp() throws IOException, InterruptedException{
		System.out.println("--set up for env----");
		objProfile= new SetUpAndroidProfile(fileName,"PhoneProfile");
		DesiredCapabilities cap = objProfile.getDesizedCapability();
		driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		mainHomeAddingAccountObj= new MainHomeAddingAccount(driver);
		gmailObj= new GmailAccount(driver);
		switchObj= new SwitchApp(driver);
		Thread.sleep(10000);
		mainHomeAddingAccountObj.selectGmailAccount();	
	}
	
	
	@Test
	public void testingadd() throws InterruptedException{
		switchObj.switchToWebView();
		gmailObj.loginGmail("adingnguyen", "dinh123456");
		switchObj.switchToNativeApp();
		gmailObj.goToMailBox();
	}
	
	
	@AfterTest	
	public void tearDown(){
		System.out.println("--finish test case----");
		}
	
	
}

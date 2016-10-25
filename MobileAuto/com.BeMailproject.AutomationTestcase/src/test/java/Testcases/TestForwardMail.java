package Testcases;
import java.io.IOException;
import java.net.URL;
import java.util.Dictionary;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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


public class TestForwardMail {
	AndroidDriver driver;
	SetUpAndroidProfile objProfile;
	AccountInfo objAccount;
	MailSetUp objMail;
	HomeScreen objHome;
	InboxScreen objInbox;
	EmailDetailScreen objMailDetail;
	ComposeScreen objCompose;
	SentScreen objSent;
	NetWorkUtility objNetwork;
	EmailDetailScreen objEmailDetail;
	String fileName=System.getProperty("user.dir")+"/src/main/resources/Data.xlsx";
	Dictionary<String,String> dict = new Hashtable<String,String>();
	RefreshApp objRefresh;
	
	
	@BeforeTest
	public void setUp() throws IOException{
		System.out.println("--set up for env----");
		objProfile= new SetUpAndroidProfile(fileName,"PhoneProfile");
		objAccount = new AccountInfo(fileName,"Accounts");
		objNetwork= new NetWorkUtility(fileName, "DataTransferMode");
		objMail= new MailSetUp();
		objRefresh= new RefreshApp();
		
		
		DesiredCapabilities cap = objProfile.getDesizedCapability();
		driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		objHome= new HomeScreen(driver);
		
		// check and turn on the mode of network
		TurnOnModeNetworkForTesting();
		
		// copy file attach to device 
		CopyFileAttacheToDevice();
		
		
			
	}
	
	@BeforeGroups(groups="sendMail")
	public void goToHomeScreenOfAccount1(){
		// we have to switch to accoutn 1 first, because our flow will be 
		// account 1 send mail to account 2
		// then account 2 forward to account 3.
		String account1=objAccount.getAccountName(1);
		System.out.println(account1);
		
		System.out.println("Go to Home Screen");
		objHome.clickHomeButton();
		
		System.out.println("Switch to account 1");
		objHome.switchMailAccount(account1);
		
		
		// click inbox button   
		System.out.println("Go to Inbox screen");
		objHome.gotoInboxScreen();
		objInbox = new InboxScreen(driver);
		objCompose= new ComposeScreen(driver);
		System.out.println("=====sending mail will be beginning now ====");
	}
	
	
	@Test(priority=1, dataProvider="dataForForward",groups="sendMail")
	public void account01sendMailToAccount02(String sendTo, String sendCC, String sendBCC, String sendSubject, String sendBody, String sendIsFileAttached, String sendFileName, String forwardTo, String forwardCC, String forwardBCC,String forwardSubject, String forwardBody, String forwardIsFileAttached, String forwardFileName) throws InterruptedException{
		
		// click compose button to compose a mail
		objInbox.clickComposeButton();
		
		//send email		
		objCompose.sendMail(sendTo, sendCC, sendBCC, sendSubject, sendBody, sendIsFileAttached, sendFileName);
		
		//wait until mail is sent
		objCompose.waitUntilMailIsSent();
		
		
	}
	
	@AfterGroups(groups="sendMail")
	public void swtichToAccount2() throws InterruptedException{
	
		System.out.println("Wait 5 min ");
		Thread.sleep(5*60*1000);
		
		// click home button
		System.out.println("Go to Home Screen");
		objInbox.clickHomeHomeButton();
		
		String account2=objAccount.getAccountName(2);
		
		System.out.println("Switch to accoutn 2");
		objHome.switchMailAccount(account2);
		
		
		System.out.println("Go to Inbox screen");
		objHome.gotoInboxScreen();
		// refresh screen
		objRefresh.RefreshScreen(driver);
		objMailDetail= new EmailDetailScreen(driver);
		
	}
	
	@Test(priority=2,dataProvider="dataForForward",groups="forwardMail")
	public void account2ForwardMailToAccount3(String sendTo, String sendCC, String sendBCC, String sendSubject, String sendBody, String sendIsFileAttached, String sendFileName, String forwardTo, String forwardCC, String forwardBCC,String forwardSubject, String forwardBody, String forwardIsFileAttached, String forwardFileName){
		System.out.println("Wait until the mail with subject : "+sendSubject+" exist in the Inbox folder of account 2");
		 objInbox.waitUntilMailIsDisplayed(sendSubject);
		
		System.out.println("Click on a email");
		objInbox.clickOnAnEmail(sendSubject);
		
		System.out.println("Wait until the mail content is fully loaded");
		objMailDetail.waitUntilMailBodyIsFullyLoaded();
		
		System.out.println("Go to forward mail ");

		objMailDetail.goToForwardEmail();
		
		if(forwardSubject!=null){
			System.out.println("Forward mail with new subject");
			objCompose.forwardMail(forwardTo, forwardCC, forwardBCC, forwardSubject, forwardBody, forwardIsFileAttached, forwardFileName);
		}
		else{
			System.out.println("Forward mail with same subject");
			objCompose.forwardMail(forwardTo, forwardCC, forwardBCC, forwardBody, forwardIsFileAttached, forwardFileName);
		}
		
		
		System.out.println("Wait until mail sent");
		objCompose.waitUntilMailIsSent();
		
		System.out.println("Go back to InBox screen");
		objCompose.goBackInboxScreen();
		
		
	}
	
	@AfterGroups(groups="forwardMail")
	public void switchToSentBoxAccount2ToCheckForwardSuccessfully() throws InterruptedException{
		System.out.println("Go back to Home Screen");
		objInbox.goToHomeScreen();
		System.out.println("Go to Sent folder");
		objHome.goToSentFolder();
		System.out.println("Refresh screen");
		objRefresh.RefreshScreen(driver);
		objSent= new SentScreen(driver);
	}
	 
	 @Test(priority=3,dataProvider="dataForForward",groups="Account2ForwardMail")
	 public void checkAccount2ForwardMailSuccessfully(String sendTo, String sendCC, String sendBCC, String sendSubject, String sendBody, String sendIsFileAttached, String sendFileName, String forwardTo, String forwardCC, String forwardBCC,String forwardSubject, String forwardBody, String forwardIsFileAttached, String forwardFileName){
		objEmailDetail = new EmailDetailScreen(driver);
		String subject="Fwd: ";
		
		if(forwardSubject!=null){
			subject+=forwardSubject;
		}
		else{
			subject+=sendSubject;
		}
		
		objSent.waitUntilMailDisplayed(subject);
		
		System.out.println("=======check if email " + subject+" exist ========");
		Assert.assertTrue(objSent.checkIfEmailExist(subject));
		
		
		objSent.clickOnAnEmail(subject);
		
		objMailDetail.waitUntilMailBodyIsFullyLoaded();
		
		dict.put(subject, objMailDetail.getBodyOfMailForward());
		
		objMailDetail.backToPreviuosScreen();
		 
		 
	 }
	 
	@AfterGroups(groups="Account2ForwardMail")
	public void switchToAccount3AndWaitForNewMails() throws InterruptedException{
		
		 // back to HomeScren
		System.out.println("Go to Home Screen to switch to account 03");
		 objInbox.goToHomeScreen();
		 
		String account3=objAccount.getAccountName(3);
		System.out.println("The account 03 is "+account3);
		
		
		System.out.println("Switch to account 3");
		objHome.switchMailAccount(account3);
		
		// sleep 5 minutes for the app syncing to server to get new mail.
		System.out.println("Wait 5 min ");
		Thread.sleep(5*60*1000);
		
		System.out.println("Go to Inbox screen ");
		objHome.gotoInboxScreen();
		
		
		// refresh screen
		RefreshApp obj= new RefreshApp();
		obj.RefreshScreen(driver);
		 
	}
	
	@Test(priority=4,dataProvider="dataForForward")
	public void testAccount3ReceiveCorrectForwardEmail(String sendTo, String sendCC, String sendBCC, String sendSubject, String sendBody, String sendIsFileAttached, String sendFileName, String forwardTo, String forwardCC, String forwardBCC,String forwardSubject, String forwardBody, String forwardIsFileAttached, String forwardFileName){
		String subject="Fwd: ";
		
		if(forwardSubject!=null){
			subject+=forwardSubject;
		}
		else{
			subject+=sendSubject;
		}
		objInbox.waitUntilMailIsDisplayed(subject);
		
		// check if existing a email with the subject which account 02 forwarded.
		Assert.assertTrue(objInbox.checkIfEmailExist(subject));
		
		// click on a email.
		objInbox.clickOnAnEmail(subject);
		
		// wait until mail is fully loaded. 
		objEmailDetail.waitUntilMailForwardBodyIsFullyLoaded();
		
		// we get the content of mail in account 03 and check with the value of dictionary has the key is the same subject. 
		// remember we already create a key-value (subject-content mail of mail in sent box of account02)
		// now on inbox of account 03, we will get the content of subject which is the same in dictionary and check if the value of dictionary is the same with content of this mail. 
		Assert.assertTrue(objEmailDetail.checkContentOfEmailForwardIsSame(dict.get(subject)));
		
		// back to Inbox screen.
		objEmailDetail.backToPreviuosScreen();		
	}
	
	
	@DataProvider(name="dataForForward")
	public Object[][] DataForForward() throws IOException{
		return objMail.getDataForForward(fileName, "ForwardMail");
	}
	
	@AfterTest
	public void tearDown(){
		System.out.println("--finish test case----");
		if(objMail.DeleteTempFolder())
		{
			System.out.println("==Remove temp folder successfully=====");
		}
		driver.quit();
	}
	
	public void TurnOnModeNetworkForTesting(){
		// check the current network mode
		String mode=objNetwork.getModeOfTransferData();
		System.out.println("The current mode is  : "+mode);
		
		if(mode.equals("Wifi Connected")){
			// turn on wifi
			System.out.println("mode wifi is being turned on");
			objNetwork.turnOnAllData(driver);
		}
		
		if(mode.equals("Wifi and Data disable")){
			System.out.println("Wifi and Data is being turned off");
			// turn off data 
			objNetwork.turnOffWifiAndData(driver);
		}		
	}
	
	public void CopyFileAttacheToDevice() throws IOException{
		
		objMail.CopyFileAttachedToDevcieForForwardMail(fileName,"ForwardMail");
	}

}

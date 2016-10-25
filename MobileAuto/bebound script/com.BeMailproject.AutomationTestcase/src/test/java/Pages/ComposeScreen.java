package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ComposeScreen {
		WebDriver driver;
		WebDriverWait wait;
	
		// text box To 
		@FindBy(id="com.bebound.mail:id/edit_to")
		WebElement txtTo;
		
		
		// button show CC 
		@FindBy(id="com.bebound.mail:id/bt_show_cc")
		WebElement btnShowCC;
		
		//button show BCC
		@FindBy(id="com.bebound.mail:id/bt_show_bcc")
		WebElement btnShowBCC;
		
		
		// text box  CC
		@FindBy(id="com.bebound.mail:id/edit_cc")
		WebElement txtCC;
		
		
		// text box BCC
		@FindBy(id="com.bebound.mail:id/edit_bcc")
		WebElement txtBCC;
		
		
		// text box Subject
		@FindBy(id="com.bebound.mail:id/edit_subject")
		WebElement txtSubject;
		
		
		// text box body
		@FindBy(id="com.bebound.mail:id/edit_body")
		WebElement txtBody;
		
		
		// button attach
		@FindBy(id="com.bebound.mail:id/bt_attachment")
		WebElement btnAttached;
		
		
		
		// folder contains attachment
		@FindBy(name="_Temp")
		WebElement folderTemp;
		
		
		//button send mail
		@FindBy(id="com.bebound.mail:id/action_send")
		WebElement btnSendMail;
		
		//button YES
		@FindBy(id="com.bebound.mail:id/buttonDefaultPositive")
		WebElement btnYES;
		
		// button Back 
		@FindBy(className="android.widget.ImageButton")
		WebElement btnBack;
		
		
		public ComposeScreen(WebDriver driver){
			this.driver=driver;
			wait=new WebDriverWait(driver, 120);
			PageFactory.initElements(driver, this);
		}

		/**
		 * this function is used for set the text for field To
		 * @param mailToAddress 
		 */
		private void  setToAddress(String mailToAddress){
			txtTo.sendKeys(mailToAddress);
		}
		
		
		/**
		 * this function is used for set the text for field CC
		 * @param mailCCAddress
		 */
		private void setCCAddress(String mailCCAddress){
			txtCC.sendKeys(mailCCAddress);
		}
		
		
		/*
		 * this function is used for click button show CC
		 */
		private void clickShowCCButton(){
			btnShowCC.click();
		}
		
		
		/**
		 * this function is used for showing BCC text box
		 */
		private void clickShowBCCButton(){
			btnShowBCC.click();
		}
		
		
		/**
		 * this functio is used for set BCC mail address
		 * @param mailBCCAddress
		 */
		private void setBCCAddress(String mailBCCAddress){
			txtBCC.sendKeys(mailBCCAddress);
		}
		
		
		/**
		 * this function is used for to set the text of field subject.
		 * @param subject
		 */
		private void setSubjectMail(String subject){
			txtSubject.sendKeys(subject);
		}
		
		
		/**
		 * this function is used for set the text of body 
		 * @param body
		 */
		private void setBodyMail(String body){
			txtBody.sendKeys(body);
		}
		
		
		
		/**
		 * this function is used for clicking attach button
		 */
		private void clickAttachedButton(){
			btnAttached.click();
		}
		
		
		/**
		 * this function is used for select file to attach
		 * @param fileName
		 */
		private void selectFileAttachment(String fileName){
			folderTemp.click();
			driver.findElement(By.name(fileName)).click();;
		}
		
		
		// click send mail button
		private void clickSendMailButton(){
			btnSendMail.click();
		}
		
		private void clickYesButton(){
			btnYES.click();
		}
		
		/**
		 * this function is used for clicking the Back arrow button to go back the Inbox Screen.
		 * 
		 */
		private void clickBackButton(){
			btnBack.click();
		}
		/**
		 * this function is used for clear text of Subject.
		 */
		private void clearSubject(){
			txtSubject.clear();
		}
		/**
		 * this function is used for sending email 
		 * @param pmailToAddress 
		 * @param pmailCCAdress
		 * @param pSubject
		 * @param pBody
		 * @param pIsFileAttached
		 * @param pfileName
		 * @throws InterruptedException 
		 */
		public void sendMail(String pmailToAddress, String pmailCCAdress, String pmailBCCAddress, String pSubject, String pBody, String pIsFileAttached, String pfileName) throws InterruptedException{
				
			//incase, user input many To addresses, and each of addresses will be inputed in new line (\n) in the row of excel. 
			// so we will replace the "\n" with 1 space " " between addresses
			// then we have 1 string of address, and each of addresses will be delimited by space.
			pmailToAddress=pmailToAddress.replaceAll("\n", " ");
			setToAddress(pmailToAddress);
			
			
			// we will click the button show CC in case : user input the CC address 
			// if user does not input CC address then we will not click show button CC.
			if(pmailCCAdress!=null){
				clickShowCCButton();
				//in case many addresses are inputed in the file.
				pmailCCAdress=pmailCCAdress.replaceAll("\n", " ");
				setCCAddress(pmailCCAdress);
				if(pmailBCCAddress!=null){
					clickShowBCCButton();
					pmailBCCAddress=pmailBCCAddress.replaceAll("\n", " ");
					setBCCAddress(pmailBCCAddress);
				}
			}
			
			
			setSubjectMail(pSubject);
			
			if(pBody!=null){
				setBodyMail(pBody);
			}
			
			// we will check if this mail has file attached.
			// in case we have file attached then we will click attach button, otherwise we will not click attach button.
			int count=0;
			if(pIsFileAttached.equals("Yes")){
				String[] listFilesAttached=pfileName.split("\n");
				for(String fname : listFilesAttached){
					count++;
					clickAttachedButton();
					selectFileAttachment(fname);
					WebDriverWait wait=new WebDriverWait(driver, 60);
					if(count==1){
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Attachment added")));
					}
					
				}
			}
			clickSendMailButton();
			
				
			}
		
		
		/**
		 * This function is used for Forward mail which new subject. 
		 * 
		 * @param forwardTo 
		 * @param forwardCC
		 * @param forwardBCC
		 * @param forwardSubject
		 * @param forwardBody
		 * @param forwardIsFileAttached
		 * @param forwardFileName
		 */
		public void forwardMail(String forwardTo, String forwardCC, String forwardBCC, String forwardSubject, String forwardBody, String forwardIsFileAttached, String forwardFileName){
			
			//in case, user input many To addresses, and each of addresses will be inputed in new line (\n) in the row of excel. 
			// so we will replace the "\n" with 1 space " " between addresses
			// then we have 1 string of address, and each of addresses will be delimited by space.
			forwardTo=forwardTo.replaceAll("\n", " ");
			setToAddress(forwardTo);
			
			
			// we will click the button show CC in case : user input the CC address 
			// if user does not input CC address then we will not click show button CC.
			if(forwardCC!=null){
				clickShowCCButton();
				//in case many addresses are inputed in the file.
				forwardCC=forwardCC.replaceAll("\n", " ");
				setCCAddress(forwardCC);
				if(forwardBCC!=null){
					clickShowBCCButton();
					forwardBCC=forwardBCC.replaceAll("\n", " ");
					setBCCAddress(forwardBCC);
				}
			}
			
			if(forwardSubject!=null){
				clearSubject();
				setSubjectMail(forwardSubject);
			}
			
			try{
			System.out.println(forwardBody);
			setBodyMail(forwardBody);
			}
			catch(Exception ex){
				System.out.println(ex.getMessage());
			}
			// we will check if this mail has file attached.
			// in case we have file attached then we will click attach button, otherwise we will not click attach button.
			int count=0;
			if(forwardIsFileAttached.equals("Yes")){
				String[] listFilesAttached=forwardFileName.split("\n");
				for(String fname : listFilesAttached){
					count++;
					clickAttachedButton();
					selectFileAttachment(fname);
					WebDriverWait wait=new WebDriverWait(driver, 60);
					if(count==1){
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Attachment added")));
					}
					
				}
			}
			clickSendMailButton();
		}
		

		/**
		 * This function is used for Forward mail which keeps the old subject 
		 * 
		 * @param forwardTo 
		 * @param forwardCC
		 * @param forwardBCC
		 * @param forwardBody
		 * @param forwardIsFileAttached
		 * @param forwardFileName
		 */
		public void forwardMail(String forwardTo, String forwardCC, String forwardBCC, String forwardBody, String forwardIsFileAttached, String forwardFileName){
			
			//in case, user input many To addresses, and each of addresses will be inputed in new line (\n) in the row of excel. 
			// so we will replace the "\n" with 1 space " " between addresses
			// then we have 1 string of address, and each of addresses will be delimited by space.
			forwardTo=forwardTo.replaceAll("\n", " ");
			setToAddress(forwardTo);
			
			
			// we will click the button show CC in case : user input the CC address 
			// if user does not input CC address then we will not click show button CC.
			if(forwardCC!=null){
				clickShowCCButton();
				//in case many addresses are inputed in the file.
				forwardCC=forwardCC.replaceAll("\n", " ");
				setCCAddress(forwardCC);
				if(forwardBCC!=null){
					clickShowBCCButton();
					forwardBCC=forwardBCC.replaceAll("\n", " ");
					setBCCAddress(forwardBCC);
				}
			}
			
			setBodyMail(forwardBody);
		
			// we will check if this mail has file attached.
			// in case we have file attached then we will click attach button, otherwise we will not click attach button.
			int count=0;
			if(forwardIsFileAttached.equals("Yes")){
				String[] listFilesAttached=forwardFileName.split("\n");
				for(String fname : listFilesAttached){
					count++;
					clickAttachedButton();
					selectFileAttachment(fname);
					WebDriverWait wait=new WebDriverWait(driver, 60);
					if(count==1){
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Attachment added")));
					}
					
				}
			}
			clickSendMailButton();
		}
			
			
		/**
		 * this function is used for wait until the mail is sent successfully. 
		 * to know if the mail is sent or not.
		 * When the mail is sent successfully.
		 * Then the is a notification is shown with text "Email sent".
		 */
		public void waitUntilMailIsSent(){
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Email sent")));
		}
		
		/** 
		 * this function is used for click back button to go back the previous screen.
		 */
		public void goBackInboxScreen(){
			clickBackButton();
		}

}

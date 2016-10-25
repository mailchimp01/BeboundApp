package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmailDetailScreen {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(className="android.widget.ImageButton")
	WebElement btnBack;
	
	@FindBy(id="com.bebound.mail:id/lbl_body")
	WebElement txtBody;
	
	//label Delete
	@FindBy(id="com.bebound.mail:id/action_delete")
	WebElement btnDelete;
		
	//btn menu: reply, reply all, forward
	@FindBy(className="android.widget.ImageView")
	WebElement btnmenu;
	
	
	// btn forward
	@FindBy(name="Forward")
	WebElement btnForward;
	
	//btn Yes 
	@FindBy(name="YES")
	WebElement btnYes;
	
	
	public EmailDetailScreen(WebDriver driver){
		this.driver=driver;
		wait=new WebDriverWait(driver, 120);
		PageFactory.initElements(driver, this);
	}
	
	
	/**
	 * this function is used for getting the body of email
	 * @return
	 */
	/*public String getBodyOfMail(){
		String body=txtBody.getText();
		int index=body.indexOf("\n--");
		if(index==0){
			body=" ";
		}
		else{
		body=body.substring(0, index-1);
		}
		return body;
		
	}*/
	
	public String getBodyOfMail(){
		String body=txtBody.getText();
		int index=body.indexOf("--");
				
		if(index==0){
			body=" ";
		}		
		else{
			body=body.substring(0, index-1);
		}
		return body;
		
	}
	
	/**
	 * this function is used for get the body of mail forwarded. 
	 * @return
	 */
	/*public String getBodyOfMailForward(){
		String body=txtBody.getText();
		int index=body.indexOf("\n>--");		
		body=body.substring(0, index-1);
		return body;
	}
	*/
	
	public String getBodyOfMailForward(){
		String body=txtBody.getText();
		int index=body.indexOf("--");		
		body=body.substring(0, index-1);
		return body;
	}
	
	
	
	/**
	 * this function is used for navigating the previous screen.
	 */
	public void backToPreviuosScreen(){
		btnBack.click();
	}
	
	
	/**
	 * this function is used for compare content of 2 emails
	 * @param subject
	 * @param body
	 * @return true if they are the same, return false otherwise.
	 */
	public boolean checkContentOfEmailIsSame(String sentMessage){
		boolean isSameContent=true;
		String receivedMessage=getBodyOfMail();
		if(!receivedMessage.equals(sentMessage)){
			isSameContent=false;
		}
		
		return isSameContent;
	}
	
	/**
	 * this function is used for checking if the content of parameter provided is the same with the content of the body mail forward.
	 * @param forwarMessage
	 * @return
	 */
	public boolean checkContentOfEmailForwardIsSame(String forwarMessage){
		boolean isSameContent=true;
		// get the content of forward mail
		String receiveMassage=getBodyOfMailForward();
		if(!receiveMassage.equals(forwarMessage)){
			isSameContent=false;
		}
		return isSameContent;
	}
	private void clickDeleteButton(){
		btnDelete.click();
	}
	
	private void clickYesButton(){
		btnYes.click();
	}
	
	public void deleteMail(){
		clickDeleteButton();
		clickYesButton();
	}
	
	/**
	 * this function is used for click menu.
	 * When you click this item.
	 * Then sub menus will be show 
	 * + Reply
	 * + Forward
	 * + Reply All
	 */
	private void clickMenu(){
		btnmenu.click();
	}
	
	/**
	 * this function is used for select Forward menu
	 */
	private void clickForwardButton(){
		btnForward.click();
	}
	
	public void goToForwardEmail(){
		clickMenu();
		clickForwardButton();
	}
	
	/**
	 * this function is used for wait a mail until the body is loaded fully. 
	 * Because the last text of the mail body is "Sent by Be-Mail without internet".
	 * So we will base on this text and we will know if the mail is loaded fully or not.
	 * in case the mail body has this text :  then the mail is loaded fully.
	 * in case the mail body doesn't have this text : then the mail still is not loaded fully.
	 */
	public void waitUntilMailBodyIsFullyLoaded(){
		wait.until(ExpectedConditions.textToBePresentInElement(txtBody, "Sent by Be-Mail without internet"));
	}
	
	public void waitUntilMailForwardBodyIsFullyLoaded(){
		wait.until(ExpectedConditions.textToBePresentInElement(txtBody, ">Sent by Be-Mail without internet"));
	}
	
	
	


}

package CustomizedLibs;
import java.io.IOException;

public class AccountInfo {
	ExcelUtility excelObject;
	public AccountInfo()  {		
		
	}
	
	public AccountInfo(String fileName, String sheetName) throws IOException{
		excelObject= new ExcelUtility(fileName, sheetName);
	}
	
	/**
	 * This function is used for getting the AccountName at the row index (indexOfUser) and the column 1 in the tab "Accounts" of file excel 
	 * @param indexOfUser : the row index which content the Account
	 * @return the Account : the Account name in the row indexOfUser and the column index =1
	 * <br> see below example,
	 * <table border="2">
	 * 	<tr>
	 * 		<td></td>
	 * 		<td>Account</td>
	 * 		<td>Username</td>
	 * 		<td>Password</td>
	 * 		<td>Type</td>
	 * </tr>
	 * <tr>
	 * 		<td>Account1</td>
	 * 		<td>accountA</td>
	 * 		<td>userA</td>
	 * 		<td>passwordA</td>
	 * 		<td>typeA</td>
	 * </tr>
	 * <tr>
	 * 		<td>Account2</td>
	 * 		<td>accountB</td>
	 * 		<td>userB</td>
	 * 		<td>passwordB</td>
	 * 		<td>typeB</td>
	 * </tr>
	 * <tr>
	 * 		<td>Account3</td>
	 * 		<td>accountC</td>
	 * 		<td>userC</td>
	 * 		<td>passwordC</td>
	 * 		<td>typeC</td>
	 * </tr>
	 * </table>
	 * <br/>when user input indexOfUser : 1 then AccountA will return
	 * <br/>when user input indexOfUser : 2 then AccountB will return
	 * <br/>when user input indexOfUser : 3 then AccountC will return
	 * 
	 */
	public String getAccountName(int indexOfUser){
		try{
			return excelObject.getValueAt(indexOfUser, 1);
		}
		catch(Exception ex){
			return "Can't not find the accountname. "+ex.getMessage();
		}
	}
	
	/**
	 * this function is used for getting the email account of user at index 
	 * @param indexOfUser index of user
	 * @return
	 */
	public String getAccountEmail(int indexOfUser){
		try{
			return excelObject.getValueAt(indexOfUser, 1);
		}
		catch(Exception ex){
			return "Can't not find the email account. "+ex.getMessage();
		}
	}
	
	/**
	 * this function is used for getting the username of user
	 * @param indexOfUser index of user
	 * @return
	 */
	public String getUserName(int indexOfUser){
		try{
			return excelObject.getValueAt(indexOfUser, 2);
		}
		catch(Exception ex){
			return "Can't not find the username. "+ex.getMessage();
		}
	}
	
	/**
	 * this function is used for getting the password of user
	 * @param indexOfUser index of user
	 * @return
	 */
	public String getPassword(int indexOfUser){
		try{
			return excelObject.getValueAt(indexOfUser, 3);
		}
		catch(Exception ex){
			return "Can't not find the password. "+ex.getMessage();			}
	}
	
	
	/**
	 * this function is used for getting the email type of user
	 * @param indexOfUser index of user
	 * @return
	 */
	public String getTypeOfEmail(int indexOfUser){
		try{
			return excelObject.getValueAt(indexOfUser, 4);
		}
		catch(Exception ex){
			return "Can't not find type of email. "+ex.getMessage();
		}
	}
	
	
	/**
	 * this function is used for getting the username to login to server of user, only applicable for server type : OTHER
	 * @param indexOfUser index of user
	 * @return
	 */
	public String getUserNameToLoginServer(int indexOfUser){
		try{
			return excelObject.getValueAt(indexOfUser, 5);
		}
		catch(Exception ex){
			return "Can't not find the user to login to server. "+ex.getMessage();
		}
	}
	
	/**
	 * this function is used for getting the servername of user, only applicable for server type : OTHER
	 * @param indexOfUser index of user
	 * @return
	 */
	public String getServerName(int indexOfUser){
		try{
			return excelObject.getValueAt(indexOfUser, 6);
		}
		catch(Exception ex){
			return "Can't not find the server name. "+ex.getMessage();
		}
	}
	
	
	/**
	 * this function is used for getting the server type of user, only applicable for server type : OTHER
	 * @param indexOfUser index of user
	 * @return
	 */
	public String getServerType(int indexOfUser){
		try{
			return excelObject.getValueAt(indexOfUser, 7);
		}
		catch(Exception ex){
			return "Can't not find the server type. "+ex.getMessage();
		}
	}
	
	

}

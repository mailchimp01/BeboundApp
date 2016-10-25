package CustomizedLibs;

import java.io.IOException;

public class MailSetUp {
	ExcelUtility excelObj;
	ShellExecuter shellObj= new ShellExecuter();	
	
	
	public MailSetUp(){
		
	}
	
	/**
	 * This function is used for getting the data of file excel's sheet.
	 * @param fileName the file name of excel.
	 * @param sheetName the sheet name of sheet.
	 * @return two-dimensional array (data[][]) 
	 * <br/>And each of element of that array will be the value in the sheet
	 * <br/> for example data[0][0] will be the value of cell with row index=0 and column index=0
	 * <br/> data[0][1] will be the value of the cell with row index=0 and column index=1
	 * <br/>....
	 * <br/> the return of this function will be the source for data driven testing.
	 * @throws IOException in case it can not find the file excel or the sheet. 
	 */
	public Object[][] getDataForSendMail(String fileName, String sheetName) throws IOException{
		
		excelObj= new ExcelUtility(fileName,sheetName);
		int rowCount=excelObj.GetTotalRows();
		System.out.println(rowCount);
		Object[][]data= new Object[rowCount][7];		
		for(int i=0;i<rowCount;i++){
			int temp=i+1;
			data[i][0]=excelObj.getValueAt(temp,0);
			data[i][1]=excelObj.getValueAt(temp,1);
			data[i][2]=excelObj.getValueAt(temp,2);
			data[i][3]=excelObj.getValueAt(temp,3);
			data[i][4]=excelObj.getValueAt(temp,4);
			data[i][5]=excelObj.getValueAt(temp,5);
			data[i][6]=excelObj.getValueAt(temp,6);
		}
		return data;	
	}
	
public Object[][] getDataForForward(String fileName, String sheetName) throws IOException{
		
	excelObj= new ExcelUtility(fileName,sheetName);
	int rowCount=excelObj.GetTotalRows();
	System.out.println("Total record is : " + rowCount);
	Object[][]data= new Object[rowCount][14];		
	for(int i=0;i<rowCount;i++){
		int temp=i+1;
		data[i][0]=excelObj.getValueAt(temp,0);
		data[i][1]=excelObj.getValueAt(temp,1);
		data[i][2]=excelObj.getValueAt(temp,2);
		data[i][3]=excelObj.getValueAt(temp,3);
		data[i][4]=excelObj.getValueAt(temp,4);
		data[i][5]=excelObj.getValueAt(temp,5);
		data[i][6]=excelObj.getValueAt(temp,6);
		data[i][7]=excelObj.getValueAt(temp,7);
		data[i][8]=excelObj.getValueAt(temp,8);
		data[i][9]=excelObj.getValueAt(temp,9);
		data[i][10]=excelObj.getValueAt(temp,10);
		data[i][11]=excelObj.getValueAt(temp,11);
		data[i][12]=excelObj.getValueAt(temp,12);
		data[i][13]=excelObj.getValueAt(temp, 13);
	}
	return data;	
	}
	
	
	/**
	 * This function is used for copying the files attached which are used for sending mail test case.
	 * @param fileName
	 * @param sheetName
	 * @return
	 * @throws IOException
	 */
	public boolean CopyFileAttachedToDevcieForSendMail(String fileName, String sheetName) throws IOException{
		excelObj= new ExcelUtility(fileName, sheetName);
		int rowCount=excelObj.GetTotalRows();
		try
		{
			Object[][]data= getDataForSendMail(fileName,sheetName);
			for(int i=0;i<rowCount;i++){
				if(data[i][5].toString().equals("Yes")){
					String strfileName=data[i][6].toString();
					
					String [] arrFileAttached=strfileName.split("\n");
					String path=System.getProperty("user.dir");
					String newpath=path.replaceAll("\\\\","/");
					StringBuilder command=new StringBuilder();
					for(String var:arrFileAttached){
						command.append("adb push ");
						command.append(newpath);
						command.append("/src/main/resources/");
						command.append(var);
						command.append(" ");
						command.append("/sdcard/_Temp/");
						command.append(var);
						System.out.println(command);
						shellObj.ExecuterCommand(command.toString());
						command.setLength(0);
						
					}						
										
				}
			}
			return true;
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
			return false;
		}
	}
	
	
	public boolean CopyFileAttachedToDevcieForForwardMail(String fileName, String sheetName) throws IOException{
		excelObj= new ExcelUtility(fileName, sheetName);
		int rowCount=excelObj.GetTotalRows();
		try
		{
			Object[][]data= getDataForForward(fileName,sheetName);
			for(int i=0;i<rowCount;i++){
				//copy file attached for sending mail
				if(data[i][5].toString().equals("Yes")){
					String strfileName=data[i][6].toString();
					
					String [] arrFileAttached=strfileName.split("\n");
					String path=System.getProperty("user.dir");
					String newpath=path.replaceAll("\\\\","/");
					StringBuilder command=new StringBuilder();
					for(String var:arrFileAttached){
						command.append("adb push ");
						command.append(newpath);
						command.append("/src/main/resources/");
						command.append(var);
						command.append(" ");
						command.append("/sdcard/_Temp/");
						command.append(var);
						System.out.println(command);
						shellObj.ExecuterCommand(command.toString());
						command.setLength(0);
						
					}						
										
				}
				//copy file attached for forward mail
				if(data[i][12].toString().equals("Yes")){
					String strfileName=data[i][13].toString();
					
					String [] arrFileAttached=strfileName.split("\n");
					String path=System.getProperty("user.dir");
					String newpath=path.replaceAll("\\\\","/");
					StringBuilder command=new StringBuilder();
					for(String var:arrFileAttached){
						command.append("adb push ");
						command.append(newpath);
						command.append("/src/main/resources/");
						command.append(var);
						command.append(" ");
						command.append("/sdcard/_Temp/");
						command.append(var);
						System.out.println(command);
						shellObj.ExecuterCommand(command.toString());
						command.setLength(0);
						
					}						
										
				}
			}
			return true;
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
			return false;
		}
	}
	
	/**
	 * This function is used for delete the Temp folder on the device after the test cases are done.
	 * @return true if delete folder successfully, otherwise false will return
	 */
	public boolean DeleteTempFolder(){
		try{
			String command="adb shell rm -r /sdcard/_Temp";
			shellObj.ExecuterCommand(command);
			return true;
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			return false;
		}
	}
}

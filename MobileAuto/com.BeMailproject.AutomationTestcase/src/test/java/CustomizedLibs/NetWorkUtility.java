package CustomizedLibs;



import java.io.IOException;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.Connection;

public class NetWorkUtility {
	
	ExcelUtility objExcel;
	
	public NetWorkUtility(String fileName,String sheetName ) throws IOException{
		objExcel= new ExcelUtility(fileName, sheetName);
		
	}
	
	// tun on all data mode
	public void turnOnAllData(AndroidDriver driver){
		try{
		driver.setConnection(Connection.ALL);
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	
	// turn on the airplan mode
	public void turnOnAirplanMode(AndroidDriver driver){
		try{
		driver.setConnection(Connection.AIRPLANE);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	// turn off wifi and data mode
	public void turnOffWifiAndData(AndroidDriver driver){
		try{
		driver.setConnection(Connection.NONE);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	// get the current mode
	public String getModeOfTransferData(){
		return objExcel.getValueAt(1, 1);
	}
	
	
	
	
	

}

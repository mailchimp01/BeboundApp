package CustomizedLibs;
import java.io.IOException;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SetUpAndroidProfile {
	ExcelUtility excelobj;
	
	public  SetUpAndroidProfile() {		
		
	}
	
	public SetUpAndroidProfile(String fileName, String sheetName) throws IOException{
		excelobj= new ExcelUtility(fileName,sheetName);
	}
	
	public DesiredCapabilities getDesizedCapability(){				
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", excelobj.getValueAt(1, 1));
		cap.setCapability("browserName", excelobj.getValueAt(2, 1));
		cap.setCapability("platformVersion", excelobj.getValueAt(3, 1));
		cap.setCapability("platformName", excelobj.getValueAt(4,1));
		cap.setCapability("appPackage", excelobj.getValueAt(5, 1));
		cap.setCapability("appActivity", excelobj.getValueAt(6, 1));
		cap.setCapability("newCommandTimeout",60*10);
		return cap;
		
	}

}

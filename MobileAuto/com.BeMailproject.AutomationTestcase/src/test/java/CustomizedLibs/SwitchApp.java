package CustomizedLibs;

import java.util.Set;


import io.appium.java_client.android.AndroidDriver;

public class SwitchApp {
	AndroidDriver driver;
	public SwitchApp(AndroidDriver driver){
		this.driver=driver;
	}
	
	public void switchToWebView(){
		Set<String> contexts=driver.getContextHandles();
		for(String s:contexts){
			if(s.contains("WEBVIEW")){
				driver.context(s);
				System.out.println("Switch to Webview");
			}
		}
	}
	
	public void switchToNativeApp(){
		Set<String> contexts=driver.getContextHandles();
		for(String s:contexts){
			if(s.contains("NATIVE")){
				driver.context(s);
				System.out.println("Switch to Native");
			}
		}

	}

}

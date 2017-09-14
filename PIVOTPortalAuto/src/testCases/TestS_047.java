package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.AdminConfig;
import pivotModules.eDeliveryAutomate;

public class TestS_047 {
	private WebDriver driver;
	private Log log;
	
	public TestS_047(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;
	}
	
	public Boolean test47() throws Exception {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);
		String RandNo = Integer.toString(paperID);
		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		boolean Retuser3=false, Retuser5=false;
		String SetWind1 = null;
		
		driver.switchTo().defaultContent();
		
		AdminConfig test = new AdminConfig(driver, log);
		//String SetWind = test.ClientAppsrch(RandNo, Testname);
		/*
		if (SetWind.equals("")) {			
			return false;			
		}
		
		test.ChangeHostedType(RandNo, Testname);
		
		driver.close();		
		driver.switchTo().window(SetWind);
				
		*/
		eDeliveryAutomate edel = new eDeliveryAutomate(driver, log);		
		
		SetWind1 = edel.eDeliveryLogin(RandNo, Testname, "1", "Regression Testing - DO NOT TOUCH", "ALL");
		
		if (!SetWind1.equals(null)) {						
			
			Retuser3 = edel.DeliveryOptions(Testname, "online");
			if (Retuser3){				
				log.logLine(Testname, false, "Verification of Delivery options page is successful");
			} else {
				log.logLine(Testname, true, "Verification of Delivery options page is Unsuccessful");				
			}		
			
		}		
		
		//driver.close();
		//driver.switchTo().window(SetWind1);	
		
		//edel.eDeliverLogout(RandNo, Testname);
		
		return Retuser5;
		
		}

}

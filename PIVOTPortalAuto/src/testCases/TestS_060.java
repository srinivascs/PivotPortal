
package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.CampaignManager;

public class TestS_060 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_060(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test60() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
		
		boolean Retuser1, Retuser2 = false, Retuser3 = false, Retuser4 = false;;
		CampaignManager CM = new CampaignManager(driver, log);
		Retuser1 = CM.CampaignManager_ManageRecipient(AccNo, Testname);
		
		if (Retuser1) {
			
			
				log.logLine(Testname, false, "Verifying Campaign Manager - Manage Recipient funtionality  is successful");
			} else {
				log.logLine(Testname, true, "Verifying Campaign Manager - Manage Recipient funtionality  is unsuccessful");
			}			
			
		return Retuser4;		
	}		
}


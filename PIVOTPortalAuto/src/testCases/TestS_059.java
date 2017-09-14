
package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.CampaignManager;

public class TestS_059 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_059(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	/*int paperID = (int) Math.round(Math.random() * (9999 - 1000 + 1) + 1000);
	public String AccNo1 = Integer.toString(paperID);*/
	
	public Boolean test59() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
		
		boolean Retuser1;
		boolean Retuser2;
		CampaignManager CM = new CampaignManager(driver, log);
		Retuser1 = CM.CampaignManager_AdvanceSearch_2(AccNo, Testname);
	    Retuser2 = CM.CampaignManager_AdvanceSearch_1(AccNo, Testname);

	if (Retuser2) {
			
				log.logLine(Testname, false, "Verifying Campaign Manager - Advance Search funtionality  is successful");
			} else {
				log.logLine(Testname, true, "Verifying Campaign Manager - Advance Search funtionality  is unsuccessful");
			}	
		
		return Retuser1;		
	}	
	
}


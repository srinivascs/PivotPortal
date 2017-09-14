
package testCases;

import launchAuto.Initialization;
import launchAuto.Log;
import org.openqa.selenium.WebDriver;

import pivotModules.CampaignManagement;
import pivotModules.HTMLFileOperations;

public class TestS_057 {

	private WebDriver driver;
	private Log log;
	
	public static long startTime;
	
	public	TestS_057(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
			public Boolean test57() throws Exception  {
				 
				
				int paperID = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);		
				String AccNo = Integer.toString(paperID);				
					
				int paperID2 = (int) Math.round(Math.random() * (99999 - 100 + 1) + 100);
				String AccNo1 = Integer.toString(paperID2);
				
				
				
				String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
				
				boolean Retuser1 = false;
				boolean caslct = false;
				
				CampaignManagement  fle = new CampaignManagement(driver, log);	
				caslct=fle.eDeliver_ClientAppSelvalidatn(AccNo,Testname);
				Retuser1 = fle.NewCampaignManager(AccNo,AccNo1,Testname);
				
				
				if (caslct) {		
					log.logLine(Testname, false, "Verification of edeliver client app selection with all related actions is successful");
					
				} else {
					log.logLine(Testname, true, "Verification of edeliver client app selection with all related actions is unsuccessful");
				}
				
						
				if (Retuser1) {		
					log.logLine(Testname, false, "Verification of New Campaign Manager with all related actions is successful");
					
				} else {
					log.logLine(Testname, true, "Verification of New Campaign Manager with all related actions is unsuccessful");
				}
				
				return Retuser1;
				}	
	
	}

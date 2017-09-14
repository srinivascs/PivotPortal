package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;
import pivotModules.JobTrackingQuickSearch;


public class TestS_040 {	
	private WebDriver driver;
	private Log log;
	
	public	TestS_040(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test40() throws Exception  {
		
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		
		
		boolean Retuser1=false, Retuser2=false;	
		
		JobTrackingQuickSearch track = new JobTrackingQuickSearch(driver, log);
		Retuser1 = track.ClientAppSel(AccNo, Testname);
		
		if (Retuser1) {			
				log.logLine(Testname, false, "Verification of the client app selection is successfull");
			} else {				
				log.logLine(Testname, true, "Verification of the client app selection is unsuccessfull");
				throw new Exception("Verification of the client app selection is unsuccessfull");
			}
		Retuser2 = track.QuickSearch(AccNo, Testname);
		if (Retuser2) {			
			log.logLine(Testname, false, "Verification of the various quick search operation is successfull");
		} else {				
			log.logLine(Testname, true, "Verification of the various quick search operation is unsuccessfull");
			throw new Exception("Verification of the various quick search operation is unsuccessfull");
		}
	
	
	return Retuser2;
	}
}
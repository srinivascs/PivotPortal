package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;
import pivotModules.JobTrackingSavedSearch;


public class TestS_041 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_041(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;
	}

	public Boolean test41() throws Exception {
		
		int paperID = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);
		String AccNo = Integer.toString(paperID);
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		
		boolean Retuser1, Retuser2=false;		
		
		JobTrackingSavedSearch Jt = new JobTrackingSavedSearch(driver, log);
		Retuser1 = Jt.ClientAppSel(AccNo, Testname);
		
		if (Retuser1) {
			Retuser2 = Jt.SavedSearch(AccNo,Testname);
			
			if (Retuser2){
				log.logLine(Testname, false, "Verifying Saved Search functionality of Job tracking is successful");
			} else {				
				log.logLine(Testname, true, "Verifying Saved Search functionality of Job tracking is Unsuccessful");
				throw new Exception("Verifying Saved Search functionality of Job tracking is Unsuccessful");
			}
		}
		
		return Retuser2;

	}

}

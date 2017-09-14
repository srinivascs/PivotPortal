package testCases;

import launchAuto.Log;
import org.openqa.selenium.WebDriver;
import pivotModules.JobTrackingOrderSearch;


public class TestS_042 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_042(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;
	}

	public Boolean test42() throws Exception {
		
		int paperID = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);
		String AccNo = Integer.toString(paperID);
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		
		boolean Retuser1, Retuser2, Retuser3, Retuser4=false;		
		
		JobTrackingOrderSearch Jt = new JobTrackingOrderSearch(driver, log);
		Retuser1 = Jt.ClientAppSel(AccNo, Testname);
		
		
		if (Retuser1) {
			Retuser2 = Jt.OrderSearch(AccNo,Testname);
			
			if (Retuser2){
				log.logLine(Testname, false, "<<<< Verifying Order Search functionality of Job tracking is Passed >>>>");
			
				Retuser3 = Jt.MoreAdvancedSearch(AccNo, Testname);
				if (Retuser3){
					log.logLine(Testname, false, "<<<< Advance Search functionality of Job tracking is Passed >>>>");	
				} else {
					log.logLine(Testname, true, "Verifying Advance Search functionality of Job tracking is failed");
					throw new Exception("Verifying Advance Search functionality of Job tracking is");
				}
				
				Retuser4=Jt.commadelimiter(AccNo, Testname);
				if (Retuser4){
					log.logLine(Testname, false, "<<<< Verifying Comma Delimited Search functionality of Job tracking is Passed >>>>");	
				} else {
					log.logLine(Testname, true, "Verifying Comma Delimited Search functionality of Job tracking is failed");
					throw new Exception("Verifying Comma Delimited Search functionality of Job tracking is failed");
				}
				
				
			} else {
				log.logLine(Testname, true, "Verifying Order Search functionality of Job tracking is Failed");
				throw new Exception("Verifying Order Search functionality of Job tracking is Failed");
			}	

		
		}
		
		return Retuser4;
	}
}





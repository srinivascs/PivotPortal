package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.JobTrackingRecentActivity;


public class TestS_043 {	
	private WebDriver driver;
	private Log log;
	
	public	TestS_043(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test43() throws Exception  {
		
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		
		
		boolean Retuser1=false, Retuser2=false;	
		
		JobTrackingRecentActivity track = new JobTrackingRecentActivity(driver, log);
		Retuser1 = track.ClientAppSel(AccNo, Testname);
		
		if (Retuser1) {			
				log.logLine(Testname, false, "Verification of the client app selection is successfull");
			} else {				
				log.logLine(Testname, true, "Verification of the client app selection is unsuccessfull");
				throw new Exception("Verification of the client app selection is unsuccessfull");
			}
		Retuser2 = track.RecentActivity(AccNo, Testname);
		if (Retuser2) {			
			log.logLine(Testname, false, "Verification of the various operation of the recent activity page is successfull");
		} else {				
			log.logLine(Testname, true, "Verification of the various operation of the recent activity page is unsuccessfull");
			throw new Exception("Verification of the various operation of the recent activity page is unsuccessfull");
		}
	
	
	return Retuser2;
	}
}
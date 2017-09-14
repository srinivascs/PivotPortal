package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.JobTrackingSearches;


public class TestS_062 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_062(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test62() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
		
		boolean Retuser1, Retuser2=false;		
		JobTrackingSearches job = new JobTrackingSearches(driver, log);
		Retuser1 = job.ClientAppSel(AccNo, Testname);
		
		if (Retuser1) {
			Retuser2 = job.ClientAppvalidation(AccNo,Testname);
			
			if (Retuser2){
				log.logLine(Testname, false, "<<<<<Verifying the Client Name and Application Name accross different searches in jobtracking is Passed>>>>>");
			} else {				
				log.logLine(Testname, true, "!!!!!Verifying the Client Name and Application Name accross different searches in jobtracking is Failed!!!!!");
				throw new Exception("!!!!!Verifying the Client Name and Application Name accross different searches in jobtracking is Failed!!!!!");
			}
		}
		
		return Retuser2;

	}

}






package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.ViewProofs;

public class TestS_011 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_011(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test11() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
		
		boolean Retuser1, Retuser2 = false, Retuser3 = false;
		ViewProofs repts = new ViewProofs(driver, log);
		Retuser1 = repts.proofViewer(AccNo, Testname);
		
		if (Retuser1) {
			Retuser2 = repts.rejectstatus(Testname);			
			if (Retuser2){
				log.logLine(Testname, false, "Verifying the modification of proofs status to reject with comments/upload is successful");
			} else {
				log.logLine(Testname, true, "Verifying the modification of proofs status to reject with comments/upload is unsuccessful");
			}
			
			Retuser3 = repts.approvestatus(Testname);			
			if (Retuser3){
				log.logLine(Testname, false, "Verifying the modification of proofs status to approve is successful");
			} else {
				log.logLine(Testname, true, "Verifying the modification of proofs status to approve is unsuccessful");
			}	
		}
		
		return Retuser3;		
	}	
	
}


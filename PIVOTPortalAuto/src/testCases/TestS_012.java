package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.ViewProofs;

public class TestS_012 {
	private WebDriver driver;
	private Log log;
	
	public TestS_012(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;
	}
	
	public Boolean test12() throws Exception {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);
		String RandNo = Integer.toString(paperID);
		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		boolean Retuser1, Retuser2=false;
		
		ViewProofs profvi = new ViewProofs(driver, log);
		Retuser1 = profvi.proofViewer(RandNo, Testname);
		
		if (Retuser1) {
			
			Retuser2 = profvi.proofEnhancements(RandNo, Testname);
			if (Retuser2){
				
				profvi.VerifyComments(Testname);
				
				log.logLine(Testname, false, "Verification of bulk actions and 3.5 enhacements of proof is successful");
			} else {
				log.logLine(Testname, true, "Verification of bulk actions and 3.5 enhacements of proof is unsuccessful");
				throw new Exception("Verification of bulk actions and 3.5 enhacements of proof is unsuccessful");
			}
		}
		
		return Retuser2;
		
		}

}

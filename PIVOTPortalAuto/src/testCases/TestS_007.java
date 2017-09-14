package testCases;
import launchAuto.Initialization;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.UploadProof;
import pivotModules.ViewProofs;

public class TestS_007 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_007(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test7() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();	
		boolean Retuser1, Retuser2 = false;
		
		ViewProofs repts = new ViewProofs(driver, log);
		Retuser1 = repts.proofViewer(AccNo, Testname);
		repts.VerifyUserAccess(AccNo,Testname);
		
		UploadProof proof = new UploadProof(driver, log);
		boolean Retval = proof.Uploadproof(AccNo, Testname, "Multiple");
		
		if (Retval && (Initialization.AutoMultipleUser.equalsIgnoreCase("yes"))) {
			return true;
		}
		
		if (Retval){
			log.logLine(Testname, false, "Proofs - Uploading multiple proofs files is successful");
		} else {
			log.logLine(Testname, true, "Proofs - Uploading multiple proofs files is unsuccessful");
			throw new Exception("Proofs - Uploading multiple proofs files is unsuccessful");
		}		
		
		return Retval;		
	}
}


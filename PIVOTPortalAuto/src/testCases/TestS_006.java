package testCases;
import launchAuto.Initialization;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.UploadProof;

public class TestS_006 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_006(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test6() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
		
		UploadProof proof = new UploadProof(driver, log);
		boolean Retval = proof.Uploadproof(AccNo, Testname, "Single");
		
		if (Retval && (Initialization.AutoMultipleUser.equalsIgnoreCase("yes"))) {
			return true;
		}
		
		if (Retval){
			log.logLine(Testname, false, "Proofs - Uploading a pdf proof file is successful");
		} else {
			log.logLine(Testname, true, "Proofs - Uploading a pdf proof file is unsuccessful");
			throw new Exception("Proofs - Uploading a pdf proof file is unsuccessful");
		}		
		
		return Retval;		
	}
}


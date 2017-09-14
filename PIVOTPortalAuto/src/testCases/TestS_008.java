package testCases;
import launchAuto.Initialization;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.UploadProof;

public class TestS_008 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_008(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test8() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
		
		UploadProof proof = new UploadProof(driver, log);
		boolean Retval = proof.Uploadproof(AccNo, Testname, "IllegalFile");
		
		if (Retval && (Initialization.AutoMultipleUser.equalsIgnoreCase("yes"))) {
			return true;
		}
		
		if (Retval){
			log.logLine(Testname, false, "Verification of uploading a illegal file and upload information validation is successful");
		} else {
			log.logLine(Testname, true, "Verification of uploading a illegal file and upload information validation is unsuccessful");
			throw new Exception("Verification of uploading a illegal file and upload information validation is unsuccessful");
		}		
		
		return Retval;		
	}
}


package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.SSOScripts;

public class TestS_080 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_080(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test80() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
		
		boolean Retuser1;
		SSOScripts repts = new SSOScripts(driver, log);
		
		//Call the First script for automation
		Retuser1 = repts.eDeliverScript1(AccNo, Testname);
			
		
		if (Retuser1) {		
			log.logLine(Testname, false, "SSO Script-1: Verifying the PDF file opened when clicking on Submit button with latest date and time is successful");
		} else {
			log.logLine(Testname, true, "SSO Script-1: Verifying the PDF file opened when clicking on Submit button with latest date and time is unsuccessful");
			throw new Exception("Verifying the PDF file opened when clicking on Submit button with latest date and time is unsuccessful");
		}		
		
		return Retuser1;		
	}	
	
}


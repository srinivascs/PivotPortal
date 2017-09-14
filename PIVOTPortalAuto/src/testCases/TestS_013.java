package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.AuditSearch;

public class TestS_013 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_013(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test13() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);		
		String AccNo = Integer.toString(paperID);		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		
		boolean Retuser1, Retuser2 = false;
		
		AuditSearch repts = new AuditSearch(driver, log);	
		Retuser1 = repts.Auditspage(AccNo, Testname);
		
		if (Retuser1) {			
			Retuser2 = repts.quickAdvancedSearch(Testname);
		
			if (Retuser2) {		
				log.logLine(Testname, false, "Verify the audit search based on various search methods is successful");
			} else {
				log.logLine(Testname, true, "Verify the audit search based on various search methods is unsuccessful");
				throw new Exception("Verify the audit search based on various search methods is unsuccessful");
			}
		}
		
		return Retuser2;		
	}	
	
}


package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.ViewReport;

public class TestS_003 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_003(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test3() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
		
		boolean Retuser1, Retuser2 = false;
		ViewReport repts = new ViewReport(driver, log);
		Retuser1 = repts.reportViewer(AccNo, Testname);
		
		if (Retuser1) {
			Retuser2 = repts.quickAdvancedSearch(Testname);			
			
			if (Retuser2){
				log.logLine(Testname, false, "Verification of Report Viewer functionality based on various search methods is successful");
			} else {
				log.logLine(Testname, true, "Verification of Report Viewer functionality based on various search methods is unsuccessful");
				throw new Exception("Verification of Report Viewer functionality based on various search methods is unsuccessful");
			}			
			
		}
		
		return Retuser2;
	}
	
	
}


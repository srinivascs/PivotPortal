package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.ViewReport;

public class TestS_004 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_004(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test4() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
		
		boolean Retuser1, Retuser2 = false;
		
		ViewReport repts = new ViewReport(driver, log);
		Retuser1 = repts.reportViewer(AccNo, Testname);
		
		if (Retuser1) {
			Retuser2 = repts.AdvsrchNegativecase(Testname);			
			
			if (Retuser2){
				log.logLine(Testname, false, "Verifying the negative cases of advanced search funtionality in report Viewer is successful");
			} else {
				log.logLine(Testname, true, "Verifying the negative cases of advanced search funtionality in report Viewer is unsuccessful");
				throw new Exception("Verifying the negative cases of advanced search funtionality in report Viewer is unsuccessful");
			}		
		}
		
		return Retuser2;		
	}
	
	
}


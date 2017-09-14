

package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.ReportsInsertingOrder;


public class TestS_033 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_033(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;
	}

	public Boolean test33() throws Exception {
		
		int paperID = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);
		String AccNo = Integer.toString(paperID);
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		
		boolean Retuser1, Retuser2=false;	
		
		ReportsInsertingOrder rpts = new ReportsInsertingOrder(driver, log);
		Retuser1 = rpts.ClientAppSel(AccNo, Testname);
		
		if (Retuser1) {
			Retuser2 = rpts.ReportInsertingOrder(AccNo,Testname);
			
			if (Retuser2){
				log.logLine(Testname, false, "Verification of the Inserting By Order report under ReportWriter is successfull");
			} else {				
				log.logLine(Testname, true, "Verification of the Inserting By Order report under ReportWriter is unsuccessfull");
				throw new Exception("Verification of the Inserting By Order report under ReportWriter is unsuccessfull");
			}
		}
		
		return Retuser2;

	}

}

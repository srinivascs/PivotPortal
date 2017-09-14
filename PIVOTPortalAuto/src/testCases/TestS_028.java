package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.ReportsPostalFrieghtApplication;

public class TestS_028 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_028(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;
	}

	public Boolean test28() throws Exception {

		int paperID = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);
		String AccNo = Integer.toString(paperID);
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		
		boolean Retuser1, Retuser2=false;
						
		ReportsPostalFrieghtApplication rpts = new ReportsPostalFrieghtApplication(driver, log);
		Retuser1 = rpts.ClientAppSel(AccNo, Testname);
		
		if (Retuser1) {
			Retuser2 = rpts.ReportGroupByApplication(AccNo,Testname);
			
			if (Retuser2){
				log.logLine(Testname, false, "Verify the Postal Frieght Application Under ReportWriter is successfull");
			} else {				
				log.logLine(Testname, true, "Verify the Postal Frieght Application Under ReportWriter is Unsuccessfull");
				throw new Exception("Verify the Postal Frieght Application Under ReportWriter is Un successfull");
			}
		}
		
		return Retuser2;

	}

}

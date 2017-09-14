package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;
import pivotModules.ReportsProductionByOrderSummary;


public class TestS_030 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_030(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;
	}

	public Boolean test30() throws Exception {
		
		int paperID = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);
		String AccNo = Integer.toString(paperID);
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		
		boolean Retuser1=false, Retuser2=false;
	
		ReportsProductionByOrderSummary rpts = new ReportsProductionByOrderSummary(driver, log);
		Retuser1 = rpts.ClientAppSel(AccNo, Testname);
		
		if (Retuser1) {
			Retuser2 = rpts.ReportGroupOrderSummary(AccNo,Testname);
			
			if (Retuser2){
				log.logLine(Testname, false, "Verify the Production By Order Summary Under ReportWriter is successfull");
			} else {				
				log.logLine(Testname, true, "Verify the Production By Order Summary Under ReportWriter is Unsuccessfull");
				throw new Exception("Verify the Production By Order Summary Under ReportWriter is Unsuccessfull");
			}
		}
		
		return Retuser2;

	}

}

package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.ReportsProductionbyOrderDetail;

public class TestS_032 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_032(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;
	}

	public Boolean test32() throws Exception {
		
		int paperID = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);
		String AccNo = Integer.toString(paperID);
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		
		boolean Retuser1, Retuser2=false;	
		
		ReportsProductionbyOrderDetail rpts = new ReportsProductionbyOrderDetail(driver, log);
		Retuser1 = rpts.ClientAppSel(AccNo, Testname);
		
		if (Retuser1) {
			Retuser2 = rpts.ReportProductionOrder(AccNo,Testname);
			
			if (Retuser2){
				log.logLine(Testname, false, "Verification of the Production By Order detail report under ReportWriter is successfull");
			} else {				
				log.logLine(Testname, true, "Verification of the Production By Order detail report under ReportWriter is unsuccessfull");
				throw new Exception("Verification of the Production By Order detail report under ReportWriter is unsuccessfull");
			}
		}
		
		return Retuser2;

	}

}

package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.ReportsPostageFreightPlant;


public class TestS_031 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_031(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;
	}

	public Boolean test31() throws Exception {
		
		int paperID = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);
		String AccNo = Integer.toString(paperID);
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		
		boolean Retuser1, Retuser2=false;	
		
		ReportsPostageFreightPlant rpts = new ReportsPostageFreightPlant(driver, log);
		Retuser1 = rpts.ClientAppSel(AccNo, Testname);
		
		if (Retuser1) {
			Retuser2 = rpts.ReportGroupByPlant(AccNo,Testname);
			
			if (Retuser2){
				log.logLine(Testname, false, "Verify the Postage Freight Plant Under ReportWriter is successfull");
			} else {				
				log.logLine(Testname, true, "Verify the Postage Freight Plant Under ReportWriter is Unsuccessfull");
				throw new Exception("Verify the Postage Freight Plant Under ReportWriter is Unsuccessfull");
			}
		}
		
		return Retuser2;

	}

}

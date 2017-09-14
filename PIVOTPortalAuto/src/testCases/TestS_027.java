package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.Infoshare_trackingreport;
import pivotModules.Infoshare_trackingreport;

public class TestS_027 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_027(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;
	}

	public Boolean test27() throws Exception {
		
		int paperID = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);
		String AccNo = Integer.toString(paperID);
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
	/*	
		boolean Retuser1, Retuser2=false;		
		
		ReportsInventoryStatusSummary rpts = new ReportsInventoryStatusSummary(driver, log);
		Retuser1 = rpts.ClientAppSel(AccNo, Testname);
		
		if (Retuser1) {
			Retuser2 = rpts.ReportStatusSummary(AccNo,Testname);
			
			if (Retuser2){
				log.logLine(Testname, false, "Verify the Inventory Status Summary Under ReportWriter is successfull");
			} else {				
				log.logLine(Testname, true, "Verify the Inventory Status Summary Under ReportWriter is Unsuccessfull");
				throw new Exception("Verify the Inventory Status Summary Under ReportWriter is Un successfull");
			}
		}
		
		return Retuser2;
		
*/
		
		boolean Retuser1, Retuser2=false;		
		
		Infoshare_trackingreport rpts = new Infoshare_trackingreport(driver, log);
		Retuser1 = rpts.ClientAppSel(AccNo, Testname);
		
		if (Retuser1) {
			Retuser2 = rpts.Infosharetrack(AccNo,Testname);
			
			if (Retuser2){
				log.logLine(Testname, false, "Verify the Infoshare tracking report in ReportWriter is successfull");
			} else {				
				log.logLine(Testname, true, "Verify the Infoshare tracking report in ReportWriter is Unsuccessfull");
				throw new Exception("Verify the Infoshare tracking report in ReportWriter is unsuccessfull");
			}
		}
		
		return Retuser2;

	}

}

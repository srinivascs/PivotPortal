package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;



import pivotModules.ReportsClientUserAccessServices;

public class TestS_029 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_029(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;
	}

	public Boolean test29() throws Exception {
		
		int paperID = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);
		String AccNo = Integer.toString(paperID);
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		
		boolean Retuser1, Retuser2=false,Retuser3=false;		
		
		ReportsClientUserAccessServices rpts = new ReportsClientUserAccessServices(driver, log);
		Retuser1 = rpts.ClientAppSel(AccNo, Testname);
		
		if (Retuser1) {
			Retuser2 = rpts.clientuseraccess(AccNo,Testname);
			
			if (Retuser2){
				log.logLine(Testname, false, "Verify ***'Client user access report'*** is successfull");
			} else {				
				log.logLine(Testname, true, "Verify ***'Client user access report'*** is is Unsuccessfull");
				throw new Exception("Verify ***'Client user access report'*** is Un successfull");
			}
		}
		
			Retuser3 = rpts.Clientuseraccesslog(AccNo,Testname);
			
			if (Retuser3){
				log.logLine(Testname, false, "Verify ***'Client user access Login report'*** is successfull");
			} else {				
				log.logLine(Testname, true, "Verify ***'Client user access Login report'*** is is Unsuccessfull");
				throw new Exception("Verify ***'Client user access Login report'*** is is Un successfull");
			}
		
		return Retuser3;

	}

}


package testCases;

import launchAuto.Log;

import org.openqa.selenium.WebDriver;


import pivotModules.PullsMgmt;

public class TestS_044 {

	private WebDriver driver;
	private Log log;
	
	public	TestS_044(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test44() throws Exception  {		 
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);	
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
		
		boolean Retuser1 = false ;	
		PullsMgmt repts = new PullsMgmt(driver, log);	
		Retuser1 = repts.Pullspage(AccNo, Testname);
				
		if (Retuser1) {		
			log.logLine(Testname, false, "Navigation to audits page successful");		
			
		} else {
			log.logLine(Testname, true, "Navigation to audits page unsuccessful");
		}		
		 
		return Retuser1;		
	}	
	
}


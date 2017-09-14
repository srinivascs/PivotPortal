package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.Status_systemhealth;

public class TestS_072 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_072(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test72() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
		
		boolean Retuser1;
		Status_systemhealth repts = new Status_systemhealth(driver, log);
		
		//Call the First script for automation
		Retuser1 = repts.VerifyUserAccess(AccNo, Testname);
					
		if (Retuser1) {		
			log.logLine(Testname, false, "Validations of systemhealth functionalities in HA admin tool is successful");
		} else {
			log.logLine(Testname, true, "Validations of systemhealth functionalities in HA admin tool is unsuccessful");
			throw new Exception("Validations of systemhealth functionalities in HA admin tool is unsuccessful");
		}		
		
		return Retuser1;		
	}	
	
}


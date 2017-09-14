package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.AdminConfig;
import pivotModules.ArchiveReprintBeta;
	

public class TestS_025 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_025(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;
	}

	public Boolean test25() throws Exception {
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);
		String AccNo = Integer.toString(paperID);
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		
		boolean Retuser1, Retuser2=false;
		String SetWindow1=null;
		
		AdminConfig admn = new AdminConfig(driver, log);
		SetWindow1 = admn.ClientAppsrch(AccNo, Testname);
		
		if (SetWindow1.equals("")) {
			return false;
		}		
		
		
		//Ensure that Reprint Settings is available in Archives
		admn.ReprintSettingsAdmin(AccNo, Testname, SetWindow1);		
		
		//admn.ClientAppsrch(AccNo, Testname);
		admn.ChangetoAdvanceSrch(AccNo, Testname, SetWindow1);
		
		ArchiveReprintBeta arch = new ArchiveReprintBeta(driver, log);
		Retuser1 = arch.ArchiveClientAppSel(AccNo, Testname, SetWindow1);
		
		if (Retuser1) {
			
			Retuser2 = arch.ReprintsettingsBeta(AccNo,Testname);			
			if (Retuser2){
				log.logLine(Testname, false, "Verify the Reprint Settings in Archives page is successfull");
			} else {				
				log.logLine(Testname, true, "Verify the Reprint Settings in Archives page is unsuccessfull");
				throw new Exception("Verify the Reprint Settings in Archives page is unsuccessfull");
			}
		}
		
		return Retuser2;

	}

}

package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.AdminConfig;
import pivotModules.ArchivesAdvanceSrch;
	

public class TestS_019 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_019(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;
	}

	public Boolean test19() throws Exception {
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);
		String AccNo = Integer.toString(paperID);
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		boolean Retuser1, Retuser2=false;
		
		AdminConfig admn = new AdminConfig(driver, log);
		String SetWindow = admn.ClientAppsrch(AccNo, Testname);
		
		if (SetWindow.equals("")) {
			return false;
		}
		
		//Ensure that advanced search is available in Archives
		admn.ChangetoAdvanceSrch(AccNo, Testname, SetWindow);
		
		ArchivesAdvanceSrch arch = new ArchivesAdvanceSrch(driver, log);
		Retuser1 = arch.ArchiveClientAppSel(AccNo, Testname, SetWindow);
		
		if (Retuser1) {
			Retuser2 = arch.AdvanceArchiveSearch(AccNo,Testname);
			if (Retuser2){
				log.logLine(Testname, false, "Verify the Advance search functionality in Archives page is successfull");
			} else {				
				log.logLine(Testname, true, "Verify the Advance search functionality in Archives page is unsuccessfull");
				throw new Exception("Verify the Advance search functionality in Archives page is unsuccessfull");
			}
		}
		
		return Retuser2;

	}

}

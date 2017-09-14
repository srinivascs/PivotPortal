package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.AdminConfig;
import pivotModules.ArchivesSimpleSrch;
	

public class TestS_018 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_018(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;
	}

	public Boolean test18() throws Exception {
		
		int paperID = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);
		String AccNo = Integer.toString(paperID);		
		
		int paperID1 = (int) Math.round(Math.random() * (99999999 - 10000000 + 1) + 10000000);
		@SuppressWarnings("unused")
		String AccNo1 = Integer.toString(paperID1);
		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		boolean Retuser1 = false, Retuser2 = false, Retuser3 = false;
			
		
		AdminConfig admn = new AdminConfig(driver, log);
		String SetWindow = admn.ClientAppsrch(AccNo, Testname);
		
		if (SetWindow.equals("")) {
			return false;			
		}
		
		//Ensure that simple search is available in Archives
		admn.ChangetoSimpleSrch(AccNo, Testname, SetWindow);
		admn.ArchiveColmsSetup(AccNo, Testname, SetWindow);
		
		ArchivesSimpleSrch arch = new ArchivesSimpleSrch(driver, log);
		Retuser1 = arch.ArchiveClientAppSel(AccNo, Testname, SetWindow);
		
		
		if (Retuser1) {
			
			Retuser2 = arch.SimpleArchieveSearch(AccNo, Testname);
			if (Retuser2){
				log.logLine(Testname, false, "Archives - SimpleSearch1 is successfull");			
			} else {
				log.logLine(Testname, true, "Archives - SimpleSearch1 is unsuccessfull");
				throw new Exception("Archives - SimpleSearch1 is unsuccessfull");
			}	
			
			Retuser3 = arch.simpleArchiveSearch2(AccNo, Testname);
			if (Retuser3){
				log.logLine(Testname, false, "Archives - SimpleSearch2 is successfull");				
			} else {
				log.logLine(Testname, true, "Archives - SimpleSearch2 is unsuccessfull");
				throw new Exception("Archives - SimpleSearch2 is unsuccessfull");
			}					

		}
		return Retuser3;

	}

}

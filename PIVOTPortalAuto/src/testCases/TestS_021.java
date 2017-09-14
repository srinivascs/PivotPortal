package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.ArchieveFileMgmtbeta;


public class TestS_021 {
	private WebDriver driver;
	private Log log;
	public TestS_021(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;
	}

	public Boolean test21() throws Exception {
		
		int paperID = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);
		String AccNo = Integer.toString(paperID);
		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		boolean Retuser1=false, Retuser2=false, Retuser3 = false;					
		
		ArchieveFileMgmtbeta arch = new ArchieveFileMgmtbeta(driver, log);
		Retuser1 = arch.ArchiveClientAppSel(AccNo, Testname);		
		
		if (Retuser1) {
			Retuser2 = arch.Filemanagement(Testname);
			if (Retuser2){
				log.logLine(Testname, false, "Verify The Archive Filemanagement beta is Successfull");
				
				Retuser3 = arch.deleteZipFile(Testname);
				if (Retuser3){
					log.logLine(Testname, false, "Verify The Archive Filemanagement Delete is is Successfull");
				} else {
					log.logLine(Testname, true, "Verify The Archive Filemanagement Delete is unsuccessfull");
					throw new Exception("Verify The Archive Filemanagement Delete is unsuccessfull");
				}
				
			} else {
				log.logLine(Testname, true, "Verify The Archive Filemanagement beta is unsuccessfull");
				throw new Exception("Verify The Archive Filemanagement beta is unsuccessfull");
			}	

		}
		
		return Retuser3;
	}

}


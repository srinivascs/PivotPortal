package testCases;
import launchAuto.Initialization;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.AdminConfig;
import pivotModules.ArchieveFileMgmtbeta;
import pivotModules.ArchivesSimpleSrch;
import pivotModules.ReportsReconEmailUpdate;
import pivotModules.SSOScripts;
import pivotModules.SSOUserLogin;

public class TestS_058 {	
	private WebDriver driver;
	private Log log;

	public TestS_058(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}

	public Boolean test58() throws Exception  {

		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		

		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		boolean Retuser1, Retuser2=false;		

		if(!Initialization.ServerName.contains("WVLLAB-MWE-PV01")){
			SSOUserLogin Lgn = new SSOUserLogin(driver, log);
			Retuser1 = Lgn.ClientAppSel(AccNo, Testname);
			if (Retuser1) {
				Retuser2 = Lgn.LoginPasswordReset(AccNo,Testname);
				if (Retuser2){
					log.logLine(Testname, false, "Verifying SSO User Login and Password reset Functionality is successful");
				} else {				
					log.logLine(Testname, true, "Verifying SSO User Login and Password reset Functionality is unsuccessful");
					throw new Exception("Verifying SSO User Login and Password reset Functionality is unsuccessful");
				}

				return Retuser2;
			}
		}else{

			log.logLine(Testname, false, "Verifying SSO User Login and Password reset Functionality "
					+ "is not compaitable in remotemachine, as BE code to validation for GMail is feasible for windows 2008 server");
			return true;
		}

		return Retuser2;

	}

}


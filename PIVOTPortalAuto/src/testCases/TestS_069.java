package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import pivotModules.AdminConfig;
import pivotModules.ArchieveFileMgmtbeta;
import pivotModules.ArchivesSimpleSrch;
import pivotModules.FavoriteFeature;
import pivotModules.ManageUsersAdvancesearch;
import pivotModules.ReportsPostalFrieghtApplication;
import pivotModules.ReportsReconEmailUpdate;
import pivotModules.SSOScripts;
import pivotModules.eDeliveryAcknowledge;
import pivotModules.ServicetypesFunctionality;


public class TestS_069 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_069(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test69() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);		
		String AccNo = Integer.toString(paperID);
		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		
	
		boolean Retuser1=false;
						
		ServicetypesFunctionality ser = new ServicetypesFunctionality(driver, log);
		Retuser1 = ser.Commonfileloader(Testname);
		
		if (Retuser1) {
			log.logLine(Testname, false,"***** Verifying Edit service types via Pivot CFL Adminis Passed *****");			
		} else {
			log.logLine(Testname, false,"!!!!! Verifying Edit service types via Pivot CFL Admin is Failed !!!!!");			
		}
		return Retuser1;

	}

}

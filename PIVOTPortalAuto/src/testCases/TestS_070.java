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
import pivotModules.WebTemplate;


public class TestS_070 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_070(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test70() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);		
		String AccNo = Integer.toString(paperID);
		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		
	
		boolean Retuser1=false;
						
		WebTemplate ser = new WebTemplate(driver, log);
		Retuser1 = ser.Templateid_duplicity(AccNo,Testname);
		
		if (Retuser1) {
			log.logLine(Testname, false,"***** Verify link id duplicity in Web Templates Passed *****");			
		} else {
			log.logLine(Testname, false,"!!!!! Verify link id duplicity in Web Templates Failed !!!!!");			
		}
		return Retuser1;

	}

}

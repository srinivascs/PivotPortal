package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.AdminConfig;
import pivotModules.ArchieveFileMgmtbeta;
import pivotModules.ArchivesSimpleSrch;
import pivotModules.FavoriteFeature;
import pivotModules.ManageUsersAdvancesearch;
import pivotModules.ReportsPostalFrieghtApplication;
import pivotModules.ReportsReconEmailUpdate;
import pivotModules.SSOScripts;
import pivotModules.eDeliveryAcknowledge;



public class TestS_067 {	
	private WebDriver driver;
	private Log log;

	public TestS_067(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}

	public Boolean test67() throws Exception  {

		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		

		String Testname = new Object(){}.getClass().getEnclosingClass().getName();


		boolean Retuser1, Retuser2,Retuser3 =false;

		ManageUsersAdvancesearch Ack = new ManageUsersAdvancesearch(driver, log);

		
		Retuser1 = Ack.Clientappsearch(AccNo, Testname);

		if (Retuser1) {
			log.logLine(Testname, false, "<<<<< Verifying Advance search feature in Manageuser is Passed >>>>>");
		} else {				
			log.logLine(Testname, true, "!!!!! Verifying Advance search feature in Manageuser  Failed !!!!!");
			throw new Exception("!!!!! Verifying Advance search feature in Manageuser  Failed !!!!!");
		}

		return Retuser1;

	}

}

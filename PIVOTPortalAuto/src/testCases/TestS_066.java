package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.AdminConfig;
import pivotModules.ArchieveFileMgmtbeta;
import pivotModules.ArchivesSimpleSrch;
import pivotModules.FavoriteFeature;
import pivotModules.ManageUsersSimpleSearch;
import pivotModules.ReportsPostalFrieghtApplication;
import pivotModules.ReportsReconEmailUpdate;
import pivotModules.SSOScripts;
import pivotModules.eDeliveryAcknowledge;



public class TestS_066 {	
	private WebDriver driver;
	private Log log;

	public TestS_066(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}

	public Boolean test66() throws Exception  {

		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		

		String Testname = new Object(){}.getClass().getEnclosingClass().getName();


		boolean Retuser1, Retuser2,Retuser3 =false;

		ManageUsersSimpleSearch Ack = new ManageUsersSimpleSearch(driver, log);

		Retuser2 = Ack.Accessmodification(AccNo, Testname);

		if (Retuser2) {
			log.logLine(Testname, false, "<<<<< Verifying modify access feature is successful >>>>>");
		} else {				
			log.logLine(Testname, true, "!!!!! Verifying modify access feature Failed !!!!!");
			throw new Exception("!!!!! Verifying modify access feature  Failed !!!!!");
		}
		Retuser3 = Ack.Modifyaccess_simplesearch(AccNo, Testname);

		if (Retuser3) {
			log.logLine(Testname, false, "<<<<< Verifying User/Group permission under Manger Users is successful >>>>>");
		} else {				
			log.logLine(Testname, true, "!!!!! Verifying User/Group permission under Manger Users is Failed !!!!!");
			throw new Exception("!!!!! Verifying User/Group permission under Manger Users is Failed !!!!!");
		}

		Retuser1 = Ack.Clientappsearch(AccNo, Testname);

		if (Retuser1) {
			log.logLine(Testname, false, "<<<<< Verifying Simple search feature in Manageuser is Passed >>>>>");
		} else {				
			log.logLine(Testname, true, "!!!!! Verifying Simple search feature in Manageuser  Failed !!!!!");
			throw new Exception("!!!!! Verifying Simple search feature in Manageuser  Failed !!!!!");
		}

		return Retuser1;

	}

}

package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.AdminConfig;
import pivotModules.ArchieveFileMgmtbeta;
import pivotModules.ArchivesSimpleSrch;
import pivotModules.DirectToDocument;
import pivotModules.FavoriteFeature;
import pivotModules.ReportsPostalFrieghtApplication;
import pivotModules.ReportsReconEmailUpdate;
import pivotModules.SSOScripts;
import pivotModules.eDeliveryAcknowledge;



public class TestS_068 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_068(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test68() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);		
		String AccNo = Integer.toString(paperID);
		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		
	
		boolean Retuser1, Retuser2=false;
						
		DirectToDocument Drct = new DirectToDocument(driver, log);
		Retuser1 = Drct.Clientappsearch(AccNo, Testname);
		
		if (Retuser1) {
				log.logLine(Testname, false, "<<<<< Verifying Direct to Document functionality is passed >>>>>");
			} else {				
				log.logLine(Testname, true, "!!!!! Verifying Direct to Document functionality is Failed !!!!!");
				throw new Exception("!!!!! Verifying Direct to Document functionality is Failed !!!!!");
			}
		
		return Retuser1;

	}

}

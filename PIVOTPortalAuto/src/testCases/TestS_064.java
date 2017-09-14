package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.AdminConfig;
import pivotModules.ArchieveFileMgmtbeta;
import pivotModules.ArchivesSimpleSrch;
import pivotModules.FavoriteFeature;
import pivotModules.ReportsPostalFrieghtApplication;
import pivotModules.ReportsReconEmailUpdate;
import pivotModules.SSOScripts;
import pivotModules.eDeliveryAcknowledge;



public class TestS_064 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_064(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test64() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		
		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		
	
		boolean Retuser1, Retuser2=false;
						
		eDeliveryAcknowledge Ack = new eDeliveryAcknowledge(driver, log);
		Retuser1 = Ack.ClientAppSel(AccNo, Testname);
		
		if (Retuser1) {
			Retuser2 = Ack.AcknowledgeFeature(AccNo,Testname);
			
			if (Retuser2){
				log.logLine(Testname, false, "<<<<< Verifying eDelivery Acknowledge Feature of Archives is Passed >>>>>");
			} else {				
				log.logLine(Testname, true, "!!!!! Verifying eDelivery Acknowledge Feature of Archives is Failed !!!!!");
				throw new Exception("!!!!! Verifying eDelivery Acknowledge Feature of Archives is Failed !!!!!");
			}
		}
		
		return Retuser2;

	}

}

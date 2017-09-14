package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.AdminConfig;
import pivotModules.ArchieveFileMgmtbeta;
import pivotModules.ArchivesSimpleSrch;
import pivotModules.FavoriteFeature;
import pivotModules.ReportsReconEmailUpdate;
import pivotModules.SSOScripts;


public class TestS_063 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_063(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test63() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		
		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		boolean Retuser1=false;
		
		FavoriteFeature Favorite = new FavoriteFeature(driver, log);
		Retuser1 = Favorite.ClientAppSel(AccNo, Testname);
		
		if (Retuser1){
			log.logLine(Testname, false, "Verifying the funtionality of Favorite feature of the portal is successful");
		} else {
			log.logLine(Testname, true, "Verifying the funtionality of Favorite feature of the portal is unsuccessful");
			throw new Exception("Verifying the funtionality of Favorite feature of the portal is unsuccessful");
		}		
		
		return Retuser1;		
	}
}



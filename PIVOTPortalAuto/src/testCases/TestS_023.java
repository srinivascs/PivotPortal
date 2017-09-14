package testCases;

import launchAuto.Log;
import pivotModules.AdminConfig;
import org.openqa.selenium.WebDriver;

public class TestS_023 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_023(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test23() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
		
		boolean Retuser2=false, Retuser3=false, Retuser4=false ;
		String Retuser1=null;		
		
		AdminConfig admn = new AdminConfig(driver, log);		
		Retuser1 = admn.ClientAppsrch(AccNo, Testname);
		
		if (Retuser1.equals("")) {
			return false;
		}
	    			
		if (!Retuser1.equals("")){	
			
			Retuser2 = admn.clientappsettings(AccNo, Testname, Retuser1);	
			if (Retuser2){
				log.logLine(Testname, false, "Verification of the client/app admin settings for manage users consent is successful");
			} else {
				log.logLine(Testname, true, "Verification of the client/app admin settings for manage users consent is unsuccessful");
			}
			
			Retuser3 = admn.ManageUsers(AccNo, Testname, Retuser1);		
			if (Retuser3){
				log.logLine(Testname, false, "Verification of the manage users consent in user Configuration is successful");
			} else {
				log.logLine(Testname, true, "Verification of the manage users consent in user Configuration is  unsuccessful");
			}
			
			Retuser4 = admn.EditManageUserconsent(AccNo, Testname, Retuser1);		
			if (Retuser4){
				log.logLine(Testname, false, "Verification of Editing the manage users consent in user Configuration is successful");
			} else {
				log.logLine(Testname, true, "Verification of Editing the manage users consent in user Configuration is  unsuccessful");
			}
		}
		
		if (!Retuser1.equals("")){
			driver.switchTo().window(Retuser1);
		}
		
		return Retuser4;
		
	}
}



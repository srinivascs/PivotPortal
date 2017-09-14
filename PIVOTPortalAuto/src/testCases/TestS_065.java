package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.LoginFeatureUsingDifferentUsers;




public class TestS_065 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_065(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test65() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		
		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		
	
		boolean Retuser1, Retuser2=false;
						
		LoginFeatureUsingDifferentUsers Lgn = new LoginFeatureUsingDifferentUsers(driver, log);
		Retuser1 = Lgn.VerifyUserAccess(AccNo, Testname);
		
		if (Retuser1) {
				log.logLine(Testname, false, "<<<<< Verifying Login Feature Using Different Users is Passed >>>>>");
			} else {				
				log.logLine(Testname, true, "!!!!! Verifying Login Feature Using Different Users is Failed !!!!!");
				throw new Exception("!!!!! Verifying Login Feature Using Different Users is Failed !!!!!");
			}

		
		return Retuser2;

	}

}

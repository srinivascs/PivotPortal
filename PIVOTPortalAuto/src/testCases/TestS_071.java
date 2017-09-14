package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import pivotModules.edeliver_activity;



public class TestS_071 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_071(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test71() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);		
		String AccNo = Integer.toString(paperID);
		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		
	
		boolean Retuser1=false;
						
		edeliver_activity Jt=new edeliver_activity(driver, log);
		Retuser1 = Jt.Advancesearch_activity(AccNo, Testname);		
		
		if (Retuser1) {
			log.logLine(Testname, false,"***** Compare email type list in admin & edeliver activity is successful *****");			
		} else {
			log.logLine(Testname, true,"!!!!! Failed to Compare email type list in admin & edeliver activity !!!!!");			
		}
		return Retuser1;

	}

}

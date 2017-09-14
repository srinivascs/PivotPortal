package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;


import pivotModules.AuditsWorkFlow;
import pivotModules.ProofsWorkFlow;



public class TestS_077 {	
	private WebDriver driver;
	private Log log;

	public TestS_077(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}

	public Boolean test77() throws Exception  {


	String Testname = new Object(){}.getClass().getEnclosingClass().getName();

		boolean Retuser1, Retuser2=false;

		AuditsWorkFlow pfs = new AuditsWorkFlow(driver, log);
		Retuser1 = pfs.Clientappsearch(Testname);

		if (Retuser1) {
			Retuser2 = pfs.AuditsWorkFlowfunctionality(Testname);
			if (Retuser2){
				log.logLine(Testname, false, "<<<<< Verifying Audits WorkFlow functionality is passed >>>>>");

			} else {
				log.logLine(Testname, true, "!!!!! Verifying Audits WorkFlow functionality is Failed !!!!!");
				throw new Exception("!!!!! Verifying Audits WorkFlow functionality is Failed !!!!!");
			}	

		}

		return Retuser2;
	}

}





package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;


import pivotModules.ProofsWorkFlow;



public class TestS_075 {	
	private WebDriver driver;
	private Log log;

	public TestS_075(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}

	public Boolean test75() throws Exception  {


	String Testname = new Object(){}.getClass().getEnclosingClass().getName();

		boolean Retuser1, Retuser2=false;

		ProofsWorkFlow pfs = new ProofsWorkFlow(driver, log);
		Retuser1 = pfs.Clientappsearch(Testname);

		if (Retuser1) {
			Retuser2 = pfs.ProofsWorkFlowfunctionality(Testname);
			if (Retuser2){
				log.logLine(Testname, false, "<<<<< Verifying Proofs WorkFlow functionality is passed >>>>>");

			} else {
				log.logLine(Testname, true, "!!!!! Verifying Proofs WorkFlow functionality is Failed !!!!!");
				throw new Exception("!!!!! Verifying Proofs WorkFlow functionality is Failed !!!!!");
			}	

		}

		return Retuser2;
	}

}





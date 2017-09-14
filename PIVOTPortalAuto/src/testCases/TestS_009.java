package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.ViewProofs;

public class TestS_009 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_009(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test9() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
		
		boolean Retuser1, Retuser2, Retuser3 = false;
		
		ViewProofs repts = new ViewProofs(driver, log);
		Retuser1 = repts.proofViewer(AccNo, Testname);
		
		if (Retuser1) {
			Retuser2 = repts.quickAdvancedSearch(Testname);			
			
			if (Retuser2){
				log.logLine(Testname, false, "Verification of view proofs functionality based on various search methods is successful");
			} else {
				log.logLine(Testname, true, "Verification of view proofs functionality based on various search methods is unsuccessful");
				throw new Exception("Verification of view proofs functionality based on various search methods is unsuccessful");
			}			
		}
		
		Retuser3 = repts.EmptyResultSearch(Testname, "Litho");
		
		if (Retuser3){
			log.logLine(Testname, false, "Verification Of Info Message based on Empty Results search is successful");
		} else {
			log.logLine(Testname, true, "Verification Of Info Message based on Empty Results search is unsuccessful");
			throw new Exception("Verification Of Info Message based on Empty Results search is unsuccessful");
		}
		return Retuser3;
	}
	
	
}


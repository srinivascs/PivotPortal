package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.AdminConfig;
import pivotModules.Archieveconsentusers;;


public class TestS_074{
	private WebDriver driver;
	private Log log;

	public TestS_074(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;
	}

	public Boolean test74() throws Exception {

		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);
		String RandNo = Integer.toString(paperID);

		String Testname = new Object(){}.getClass().getEnclosingClass().getName();

		boolean Retuser1=false,Retuser2=false;;

		Archieveconsentusers consent = new Archieveconsentusers(driver, log);	
		

		Retuser1=consent.edelivery_consent(Testname);		
		

		if (Retuser1){				
			log.logLine(Testname, false, "Verification of Aricheve & skyblue history data is successful");
		} else {
			log.logLine(Testname, true, "Verification of Aricheve & skyblue history data  is Unsuccessful");				
		}

		return Retuser1;

	}

}


package testCases;
import launchAuto.Initialization;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.Compareprooftest;

public class TestS_073{	
	private WebDriver driver;
	private Log log;

	public TestS_073(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}

	public Boolean test73() throws Exception  {

		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);	
		String AccNo1 = Integer.toString(paperID+1);	
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();		

		Compareprooftest proof = new Compareprooftest(driver, log);
		
		proof.prooflogin(AccNo,Testname);
		
		boolean Retval=proof.Compareprooftest1(Testname,AccNo,AccNo1);

		if (Retval && (Initialization.AutoMultipleUser.equalsIgnoreCase("yes"))) {
			return true;
		}

		if (Retval){
			log.logLine(Testname, false, "Proofs -Comparing a pdf proof file is successful");
		} else {
			log.logLine(Testname, true, "Proofs - Comparing a pdf proof file is unsuccessful");
			throw new Exception("Proofs - Comparing a pdf proof file is unsuccessful");
		}		

		return Retval;		
	}
}


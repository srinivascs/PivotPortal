
package testCases;

import launchAuto.Log;
import org.openqa.selenium.WebDriver;
import pivotModules.TemplateMgmt;


public class TestS_053 {

	private WebDriver driver;
	private Log log;
	
	public	TestS_053(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}

	public Boolean test53() throws Exception  {
		 
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);	
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
		
		boolean Retuser1 = false;	
		
		TemplateMgmt tmpl = new TemplateMgmt(driver, log);		
		Retuser1 = tmpl.xmlSchemaAnd_eDelvery_FldVal(Testname, AccNo);
				
		if (Retuser1) {			
			log.logLine(Testname, false, "Verification of the XML schema and eDelivery operation of TM is successfull");
		} else {				
			log.logLine(Testname, true, "Verification of the XML schema and eDelivery operation of TM is unsuccessfull");
			throw new Exception("Verification of the XML schema and eDelivery operation of TM is unsuccessfull");
		}	
		
				 
		return Retuser1;		
	}	

}

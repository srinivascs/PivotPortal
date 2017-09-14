package testCases;

import launchAuto.Log;
import org.openqa.selenium.WebDriver;
import pivotModules.PublicDocuments;

public class TestS_046 {

	private WebDriver driver;
	private Log log;
	
	public	TestS_046(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}

		public Boolean test46() throws Exception  {
			 
			
			int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
			Integer.toString(paperID);	
			String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
			
			boolean Retuser2=false;	
			PublicDocuments pd = new PublicDocuments(driver, log);				
			
			Retuser2 = pd.SrchPDFTPupload(Testname);
			if (Retuser2) {		
				log.logLine(Testname, false, "Verification of searching the public documents is sucessful");
				
			} else {
				log.logLine(Testname, true, "Verification of searching the public documents is unsucessful");
			}		
			
			
			return Retuser2;		
		}	

}

package testCases;

import launchAuto.Log;
import org.openqa.selenium.WebDriver;
import pivotModules.TemplateMgmtHTML;

public class TestS_050 {

private WebDriver driver;
private Log log;

public	TestS_050(WebDriver driver, Log log) {
	this.driver = driver;
	this.log = log;	
}

		public Boolean test50() throws Exception  {
			 
			
			int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
			String AccNo = Integer.toString(paperID);	
			String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
			
			boolean Retuser1 = false, Retuser2=false ;	
			TemplateMgmtHTML tmpl = new TemplateMgmtHTML(driver, log);	
			Retuser1 = tmpl.TemplateText(AccNo, Testname);
					
			if (Retuser1) {		
				log.logLine(Testname, false, "Verification of the HTML email type in Template Management is sucessful");
				
			} else {
				log.logLine(Testname, true, "Verification of the HTML email type in Template Management is unsucessful");
			}
			
			Retuser2 = tmpl.InsertLink(AccNo, Testname);			
			if (Retuser1) {		
				log.logLine(Testname, false, "Verification of insert link of the HTML email type in Template Management is sucessful");
				
			} else {
				log.logLine(Testname, true, "Verification of insert link of the HTML email type in Template Management is unsucessful");
			}
			
			 
			return Retuser2;		
		}	

}





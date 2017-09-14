package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;
import pivotModules.AdminConfig;
import pivotModules.AuditKeys;
import pivotModules.AuditRules;
import pivotModules.AuditSubSegments;


public class TestS_017 {	
	private WebDriver driver;
	private Log log;
	
	public	TestS_017(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test17() throws Exception  {	 
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);	
		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName(); 
		
		boolean user1 = true, user2= true  , user3=true, user4 = true, user5 = true;
		
	//	*********************Admit setting and select client Name and Application name*****************
		if (user1) {		
			AdminConfig admn = new AdminConfig(driver, log);
			admn.ClientAppsrch_Audit(AccNo, Testname);		
			log.logLine(Testname, false, "Client app admin settings for audit keys, sub-segments and Rules is successful");
			
		} else {
			log.logLine(Testname, true, "Client app admin settings for audit keys, sub-segments and Rules is unsuccessful");
		}
		
		
   //		*********************Deletion of Audit Rules*************************************************
		
		if (user2) {		
		
			AuditRules adtrule = new AuditRules(driver, log);	
			adtrule.AuditRulesDelete(AccNo, Testname );
		
			log.logLine(Testname, false, "Audit Rules deletion is successful");
			
		} else {
			log.logLine(Testname, true, "Audit Rules deletion is NOT unsuccessful");
		}
		
   //		*********************All validations for Audit Keys*************************************************
		
		if (user3) {	
			AuditKeys adt = new AuditKeys(driver, log);	
			adt.Auditspage(AccNo, Testname );		
			log.logLine(Testname, false, "Verification and validation of the audit keys operations is successful");
			
		} else {
			log.logLine(Testname, true, "Verification and validation of the audit keys operations is unsuccessful");
		}
			
		
	 //		*********************All validations for Audit Sub Segments*************************************************		
		
	/*	if (user4) {		
			AuditSubSegments audtseg = new AuditSubSegments(driver, log);	
			audtseg.Auditspage(AccNo, Testname );
		
		
			log.logLine(Testname, false, "Verification and validation of the sub-segmenst operations is successful");
			
		} else {
			log.logLine(Testname, true, "Verification and validation of the sub-segmenst operations is unsuccessful");
		}*/
        
		
	 //		*********************All validations for Audit Rules*************************************************
		
		if (user5) {	
		AuditRules adtrul = new AuditRules(driver, log);	
		adtrul.Auditspage(AccNo, Testname );
		
			
			log.logLine(Testname, false, "Verification and validation of the audit rules operations is successful");
			
		} else {
			log.logLine(Testname, true, "Verification and validation of the audit rules operations is unsuccessful");
		}

	
		return user5;		
	}	
	
}


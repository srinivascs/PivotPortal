package testCases;

import launchAuto.Log;

import org.openqa.selenium.WebDriver;
	
import pivotModules.TemplateMgmtPermissions;
	
	public class TestS_052 {

	private WebDriver driver;
	private Log log;
	
	public	TestS_052(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
			public Boolean test52() throws Exception  {
				 
				
				int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
				String AccNo = Integer.toString(paperID);	
				String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
				
				boolean Retuser1 = false , Retuser2=false , Retuser3=false;	
				
				TemplateMgmtPermissions tmpl = new TemplateMgmtPermissions(driver, log);	
				Retuser1 = tmpl.clientAppSelect(AccNo, Testname);			
				
				if (Retuser1) {		
					log.logLine(Testname, false, "Verification of the client/application selection and clicking on Template Management tab is navigated to the Template Management page");
					
				} else {
					log.logLine(Testname, true, "Verification of the client/application selection and clicking on Template Management tab is not navigated to the Template Management page");
				}
				
				
				Retuser2 = tmpl.accessTM_adminEmailProjects(AccNo, Testname);
				
				if (Retuser2) {		
					log.logLine(Testname, false, "Verifying the access to the TM page from Email types Templates under Projects of Email builder in Admin Settings of Template Management is sucessful");
					
				} else {
					log.logLine(Testname, true, "Verifying the access to the TM page from Email types Templates under Projects of Email builder in Admin Settings of Template Management is unsucessful");
				}
				
				Retuser3 = tmpl.accessTM_ClientAdminEdelivery(Testname);
				
				if (Retuser3) {		
					log.logLine(Testname, false, "Verifying the access to the TM page from eDelivery Tool  under Client/app Admin Settings of Template Management is sucessful");
					
				} else {
					log.logLine(Testname, true, "Verifying the access to the TM page from eDelivery Tool  under Client/app Admin Settings of Template Management is unsucessful");
				}
				
			
				return Retuser3;		
			}	
	
	}

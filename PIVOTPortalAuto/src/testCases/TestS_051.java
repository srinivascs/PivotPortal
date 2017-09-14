	package testCases;
	
	import launchAuto.Log;
	
	import org.openqa.selenium.WebDriver;
	
import pivotModules.TemplateMgmtPermissions;
	
	public class TestS_051 {

	private WebDriver driver;
	private Log log;
	
	public	TestS_051(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
			public Boolean test51() throws Exception  {
				 
				
				int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
				String AccNo = Integer.toString(paperID);	
				String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
				
				boolean Retuser1 = false , Retuser2=false , Retuser3=false , Retuser4=false,Retuser5=false, Retuser6=false, Retuser7=false;	
				
				TemplateMgmtPermissions tmpl = new TemplateMgmtPermissions(driver, log);	
				Retuser1 = tmpl.clientAppSelect(AccNo, Testname);
						
				if (Retuser1) {		
					log.logLine(Testname, false, "Verification the client/application selection of Template Management is sucessful");
					
				} else {
					log.logLine(Testname, true, "Verification the client/application selection of Template Management is unsucessful");
				}
				
				
				Retuser2 = tmpl.EmailAdminSettings(AccNo, Testname);
				
				if (Retuser2) {		
					log.logLine(Testname, false, "Verification the Email Admin Settings of Template Management is sucessful");
					
				} else {
					log.logLine(Testname, true, "Verification the Email Admin Settings of Template Management is unsucessful");
				}
				
				Retuser3 = tmpl.AddTextTemplate(AccNo, Testname);			
				
				if (Retuser3) {		
					log.logLine(Testname, false, "Verification of adding the Text templates to Email type templates in Template Management is sucessful");
					
				} else {
					log.logLine(Testname, true, "Verification of adding the Text templates to Email type templates in Template Management is unsucessful");
				}	
				
				
				Retuser4 = tmpl.userAdminDisableAccess(Testname);
				
				if (Retuser4) {		
					log.logLine(Testname, false, "Verification of disabling the user permissions for edit , promote and delete operations of Email type templates in Template Management is sucessful");
					
				} else {
					log.logLine(Testname, true, "Verification of disabling the user permissions for edit , promote and delete operations of Email type templates in Template Management is unsucessful");
				}
				
				
				Retuser5 = tmpl.verifyPermissions_Disabled(AccNo, Testname);
				
				if (Retuser5) {		
					log.logLine(Testname, false, "Validation of disabled permissions of Email type templates in Template Management is sucessful");
					
				} else {
					log.logLine(Testname, true, "Validation of disabled permissions of Email type templates in Template Management is unsucessful");
				}
				
				
				Retuser6 = tmpl.userAdminEnableAccess(Testname);
				
				if (Retuser6) {		
					log.logLine(Testname, false, "Verification of enabling the user permissions for edit , promote and delete operations of Email type templates in Template Management is sucessful");
					
				} else {
					log.logLine(Testname, true, "Verification of enabling the user permissions for edit , promote and delete operations of Email type templates in Template Management is unsucessful");
				}				
				
				
				Retuser7 = tmpl.verifyPermissions_Enabled(AccNo, Testname);
				
				if (Retuser7) {		
					log.logLine(Testname, false, "Validation of enabled permissions of Email type templates in Template Management is sucessful");
					
				} else {
					log.logLine(Testname, true, "Validation of enabled permissions of Email type templates in Template Management is unsucessful");
				}
				
				return Retuser7;		
			}	
	
	}

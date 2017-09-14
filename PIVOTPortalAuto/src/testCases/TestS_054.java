package testCases;

import launchAuto.Log;	
import org.openqa.selenium.WebDriver;	
import pivotModules.TemplateMgmt;
import pivotModules.TemplateMgmtPermissions;
	
public class TestS_054 {

	private WebDriver driver;
	private Log log;
	
	public	TestS_054(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
			public Boolean test54() throws Exception  {
				 
				
				int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
				String AccNo = Integer.toString(paperID);	
				String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
				
				boolean Retuser1 = false , Retuser2=false , Retuser3=false , Retuser4=false;	
				
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
				
				//From here the xml schema scenarios starts
				
				TemplateMgmt tmpls = new TemplateMgmt(driver, log);				
		       
				tmpls.SavedTypesDropDown(Testname);		        
		        
				tmpl.ClickXMLSchemaButton(Testname, "ClickLoadXMLBtn");
		        
				Retuser4 = tmpl.LoadXMLSchemaValidation(Testname);		        
				
				if (Retuser3) {		
					log.logLine(Testname, false, "Verifying the XML Schema valiadtion of the Template Management is successful ");
					
				} else {
					log.logLine(Testname, true, "Verifying the XML Schema valiadtion of the Template Management is unsuccessful ");
				}	
				
				 
				return Retuser4;		
			}	
	
	}

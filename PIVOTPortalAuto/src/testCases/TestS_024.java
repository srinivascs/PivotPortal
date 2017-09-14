package testCases;

import launchAuto.Log;
import pivotModules.AdminConfig;
import pivotModules.ArchiveEmailsettingsBeta;
import org.openqa.selenium.WebDriver;

public class TestS_024 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_024(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test24() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
		
		boolean Retuser2=false, Retuser3=false, Retuser4=false ;
		String SetWindow1=null, SetWindow2=null;
		
		AdminConfig admn1 = new AdminConfig(driver, log);
		SetWindow1 = admn1.ClientAppsrch(AccNo, Testname);
		
		if (SetWindow1.equals("")) {
			return false;
			
		}else {
			admn1.ChangetoAdvanceSrch(AccNo, Testname, SetWindow1);
			
		}
		
		
		AdminConfig admn = new AdminConfig(driver, log);		
		SetWindow2 = admn.ClientAppsrch(AccNo, Testname);	    			
		
		if (!SetWindow2.equals(null)){		
			
			Retuser2 = admn.EnableEmailFlag(AccNo, Testname, SetWindow2);	
			if (Retuser2){
				log.logLine(Testname, false, "Verification of the emailflag enable is successful");
				
				ArchiveEmailsettingsBeta emailset = new ArchiveEmailsettingsBeta(driver, log);		
				Retuser3 = emailset.ArchiveClientAppSel(AccNo, Testname, SetWindow2);	
			    			
				if (Retuser3){	
					
					Retuser4 = emailset.EmailsettingsBeta(AccNo, Testname);	
					if (Retuser4){
						log.logLine(Testname, false, "Verification of email settings beta for archives is successful");
					} else {
						log.logLine(Testname, true, "Verification of email settings beta for archives is unsuccessful");
					}			
					
				}
			} else {
				log.logLine(Testname, true, "Verification of the emailflag enable is unsuccessful");
			}
				
			
		}		
		
		return Retuser4;
		
	}
}



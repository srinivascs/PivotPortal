package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.AuditSearch;

public class TestS_016 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_016(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test16() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
				
		boolean Retuser1;
		
		AuditSearch repts = new AuditSearch(driver, log);	
		Retuser1 = repts.Auditspage(AccNo, Testname);
				
		Thread.sleep(1000);
	    driver.switchTo().frame("iframeContainer");	
		
		//List all pending audits before approve
		repts.AdvancedSearch(Testname, "AuditStatus", "Pending", "", "");
		
		//Approve the first audit
		String FirstAudit1 = repts.ChangeStatus(Testname, "Approve Audit");
		
		//List all approved audits
		repts.AdvancedSearch(Testname, "AuditStatus", "Approved", "", "");
		
		if (driver.getPageSource().contains(FirstAudit1)) {
			log.logLine(Testname, false, "Change in Audit status from pending to Approved is successful");
		} else {
			log.logLine(Testname, true, "Change in Audit status from pending to Approved is unsuccessful");		
		}		
	    
		
	    //Clear button and cancel
		repts.fillupAdvancedSrch(Testname, "Yes");	
		
		
		
		//List all pending audits before Disapprove
		repts.AdvancedSearch(Testname, "AuditStatus", "Pending", "", "");
		
		//Approve the first audit
		String FirstAudit2 = repts.ChangeStatus(Testname, "Reject/Disapprove Audit");
		
		//List all Disapproved audits
		repts.AdvancedSearch(Testname, "AuditStatus", "Disapproved", "", "");
		
		if (driver.getPageSource().contains(FirstAudit2)) {
			log.logLine(Testname, false, "Change in Audit status from pending to Disapproved is successful");
		} else {
			log.logLine(Testname, true, "Change in Audit status from pending to Disapproved is unsuccessful");		
		}		
	    
	    //Clear button and cancel
		repts.fillupAdvancedSrch(Testname, "Yes");
		
			  		
		 
		//List all pending audits before Cancel
		repts.AdvancedSearch(Testname, "AuditStatus", "Approved", "", "");
		
		//Approve the first audit
		String FirstAudit3 = repts.ChangeStatus(Testname, "Cancel Audit");
		
		if (!(FirstAudit3.equals(""))) {
			
			//List all Cancelled audits
			repts.AdvancedSearch(Testname, "AuditStatus", "Cancelled", "", "");
			
			if (driver.getPageSource().contains(FirstAudit3)) {
				log.logLine(Testname, false, "Change in Audit status from pending to Cancelled is successful");
			} else {
				log.logLine(Testname, true, "Change in Audit status from pending to Cancelled is unsuccessful");		
			}
			
			if (Retuser1 ) {		
				log.logLine(Testname, false, "Verify the change in status of the audits is successful");
			} else {
				log.logLine(Testname, true, "Verify the change in status of the audits is unsuccessful");
				throw new Exception("Verify the change in status of the audits is unsuccessful");
			}
		}	
		
		driver.switchTo().defaultContent();
		return Retuser1;		
	}	
	
}


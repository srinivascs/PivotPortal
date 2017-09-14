package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.AuditSearch;

public class TestS_014 {	
	private WebDriver driver;
	private Log log;

	public TestS_014(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}

	public Boolean test14() throws Exception  {

		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();		

		boolean Retuser1, Retuser2,	Retuser3, Retuser4, Retuser5 =  false;

		AuditSearch repts = new AuditSearch(driver, log);
		Retuser1 = repts.Auditspage(AccNo, Testname);		

		if (Retuser1) {

			Thread.sleep(2000);
			driver.switchTo().frame("iframeContainer"); 

			// Get the reports based on the Job code with no matching data entered	    
			repts.AdvancedSearch(Testname, "FileName", "campo 1", "NOMATCHING DATA", "");

			// Fill up advanced search field and clear, click cancel button
			Retuser2 = repts.fillupAdvancedSrch(Testname, "Yes");        	    

			Thread.sleep(1000);
			driver.switchTo().defaultContent();  

			if (Retuser2) {		
				log.logLine(Testname, false, "Verify the negative cases of advanced search in Audits is successful");
			} else {
				log.logLine(Testname, true, "Verify the negative cases of advanced search in Audits is unsuccessful");
				throw new Exception("Verify the negative cases of advanced search in Audits is unsuccessful");
			}

		}

		Retuser3=repts.Auditstatus(Testname,"Pending");
		
		Thread.sleep(1000);
		driver.switchTo().defaultContent();  

		if (Retuser3) {		
			log.logLine(Testname, false, "Validation of 'Audit staus' , approve & reject icons is successful ");
		} else {
			log.logLine(Testname, true, "Validation of 'Audit staus' , approve & reject icons is unsuccessful ");
			throw new Exception("Validation of 'Audit staus' , approve & reject icons is unsuccessful    ");
		}

		Retuser4=repts.AuditsApprove(AccNo, Testname, "Approved");
		
		Thread.sleep(1000);
		driver.switchTo().defaultContent();  

		if (Retuser4) {		
			log.logLine(Testname, false, "Validation of 'Audit staus' , approve Button is successful ");
		} else {
			log.logLine(Testname, true, "Validation of 'Audit staus' , approve Button is unsuccessful ");
			throw new Exception("Validation of 'Audit staus' , approve Button is unsuccessful    ");
		}
		
		Retuser5=repts.AuditsReject(AccNo, Testname, "Pending", "Disapproved");
		
		Thread.sleep(1000);
		driver.switchTo().defaultContent();  

		if (Retuser5) {		
			log.logLine(Testname, false, "Validation of 'Audit staus' , Reject/Disapproved Button is successful ");
		} else {
			log.logLine(Testname, true, "Validation of 'Audit staus' , Reject/Disapproved Button is unsuccessful ");
			throw new Exception("Validation of 'Audit staus' , Reject/Disapproved Button is unsuccessful    ");
		}
		return Retuser5;
	}	

}


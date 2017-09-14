package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.ReportsReconEmailUpdate;


public class TestS_035 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_035(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;
	}

	public Boolean test35() throws Exception {
		
		int paperID = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);
		String AccNo = Integer.toString(paperID);
		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		boolean Retuser1, Retuser2, Retuser3=false;		
		
		ReportsReconEmailUpdate rpts = new ReportsReconEmailUpdate(driver, log);
		Retuser1 = rpts.ClientAppSel(AccNo, Testname);
		
		if (Retuser1) {
			Retuser2 = rpts.ReconReport(AccNo,Testname);
			
			if (Retuser2){
				log.logLine(Testname, false, "Verify the Recon Email Update(Activity Report) Under ReportWriter is successfull");
			} else {				
				log.logLine(Testname, true, "Verify the Recon Email Update(Activity Report) Under ReportWriter is Unsuccessfull");
				throw new Exception("Verify the Recon Email Update(Activity Report) Under ReportWriter is Unsuccessfull");
			}
		}
		
		Retuser3 = rpts.DocumentRetrievalActivity(AccNo,Testname);
		
		if (Retuser3){
			log.logLine(Testname, false, "Verify the Document Retrieval Activity Report Under ReportWriter is successfull");
		} else {				
			log.logLine(Testname, true, "Verify the Document Retrieval Activity Report Under ReportWriter is Unsuccessfull");
			throw new Exception("Verify the Document Retrieval Activity Report Under ReportWriter is Unsuccessfull");
		}
		
		return Retuser3;

	}

}

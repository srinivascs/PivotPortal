package testCases;
import launchAuto.InputOutputData;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.JobTracking;

public class TestS_039 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_039(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;
	}
	
	public Boolean test39() throws Exception {		
		
		int paperID = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);
		String AccNo = Integer.toString(paperID);
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		
		boolean Retuser1=false, Retuser2=false, Retuser3=false;
		Integer.toString(paperID);
		
		JobTracking jtrck = new JobTracking(driver, log);
		Retuser1 = jtrck.JobTrackingpage(AccNo, Testname);
		
		if (Retuser1) {			
			Retuser2 = jtrck.EnvelopeSearch(Testname);
			
			if (Retuser2) {								
				
				//Date Range Search1
				jtrck.performSearch(Testname, "Advanced", "01/01/2013", "", "Data Processed by RRD Facility Date", "Envelope/Package", "", "", "", "");
				
				
				//Date Range Search2
				jtrck.performSearch(Testname, "Advanced", "01/01/2013", "", "Expected Mail Date", "Envelope/Package", "", "", "", "");
				
				
				//Date Range Search3
				jtrck.performSearch(Testname, "Advanced", "01/01/2013", "", "Actual Mail Date", "Envelope/Package", "", "", "", "");
				
				
				//Basic Search Criteria
				jtrck.performSearch(Testname, "Advanced", "01/01/2013", "", "Data Processed by RRD Facility Date", "Envelope/Package", "", "", "Logan", "");
				
				
				//Advanced Options Search1
				jtrck.performSearch(Testname, "Advanced", "01/01/2013", "", "Data Processed by RRD Facility Date", "Envelope/Package", "Document #", "0000020", "", "");
				
				
				//Advanced Options Search2
				jtrck.performSearch(Testname, "Advanced", "01/01/2013", "", "Data Processed by RRD Facility Date", "Envelope/Package", "", "", "", "yes");
						
				log.logLine(Testname, false, "Verification of the Envelope/Package Search is successfull");
				
			} else {				
				log.logLine(Testname, true, "Verification of the Envelope/Package Search is unsuccessfull");
				throw new Exception("Verification of the Envelope/Package Search is unsuccessfull");
			}	
		}
		
		
 		
		Retuser3 = jtrck.ClientAppSel2(Testname);
		return Retuser3;
		
	}
	
	
	
	
	
	

}


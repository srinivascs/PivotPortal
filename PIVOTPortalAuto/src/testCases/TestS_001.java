package testCases;
import launchAuto.Initialization;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.UploadReport;

public class TestS_001 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_001(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test1() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
		
		UploadReport report = new UploadReport(driver, log);
		 report.reportViewerchkaction(AccNo,Testname);
		boolean Retval = report.Uploadreport(AccNo, Testname, "Single");
		
		if (Retval && (Initialization.AutoMultipleUser.equalsIgnoreCase("yes"))) {
			return true;
		}
		
		if (Retval){
			log.logLine(Testname, false, "Report Viewer - Uploading a report file is successful");
		} else {
			log.logLine(Testname, true, "Report Viewer - Uploading a report file is unsuccessful");
			throw new Exception("Report Viewer - Uploading a report file is unsuccessful");
		}		
		
		return Retval;		
	}
}


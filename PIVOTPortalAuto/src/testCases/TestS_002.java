package testCases;
import launchAuto.Initialization;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.UploadReport;

public class TestS_002 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_002(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test2() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
		
		UploadReport report = new UploadReport(driver, log);
		boolean Retval = report.Uploadreport(AccNo, Testname, "Multiple");
		
		if (Retval && (Initialization.AutoMultipleUser.equalsIgnoreCase("yes"))) {
			return true;
		}
		
		if (Retval){
			log.logLine(Testname, false, "Report Viewer - Uploading multiple report files is successful");
		} else {
			log.logLine(Testname, true, "Report Viewer - Uploading multiple report files is unsuccessful");
			throw new Exception("Report Viewer - Uploading multiple report files is unsuccessful");
		}		
		
		return Retval;		
	}
}


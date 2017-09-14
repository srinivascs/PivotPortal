package testCases;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.AuditSearch;

public class TestS_015 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_015(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test15() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
		
		boolean Retuser1=false;
		
		AuditSearch repts = new AuditSearch(driver, log);		
		Retuser1 = repts.Auditspage(AccNo, Testname);			
		
		if (Retuser1) {
			
			SimpleDateFormat gmtDateFormat = new SimpleDateFormat("MM/dd/yyyy");		
			gmtDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
		    String Todateval = gmtDateFormat.format(new Date());
		    
		    //Change the driver focus to frame
		    driver.switchTo().frame("iframeContainer");
		    
		    repts.AdvancedSearch(Testname, "FROMTOdate", "01/02/2014", Todateval, "Yes");
			
			log.logLine(Testname, false, "Verify the audit viewing with comments and validating the same is successful");
		} else {
			log.logLine(Testname, true, "Verify the audit viewing with comments and validating the same is unsuccessful");
			throw new Exception("Verify the audit viewing with comments and validating the same is unsuccessful");
		}		
		
		return Retuser1;		
	}	
	
}


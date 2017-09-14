
package testCases;

import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.CampaignManagement;

public class TestS_061 {

	private WebDriver driver;
	private Log log;
	
	public static long startTime;
	
	public	TestS_061(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
			public Boolean test61() throws Exception  {				 
				
				int paperID = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);		
				String AccNo = Integer.toString(paperID);				
					
				int paperID2 = (int) Math.round(Math.random() * (9999 - 100 + 1) + 100);
				String AccNo1 = Integer.toString(paperID2);
						
				
				String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
				
				boolean Retuser1 = false;	
				
				CampaignManagement  fle = new CampaignManagement(driver, log);	
				
				log.logLine(Testname, false, "We are performing bulk hold & release action validations");
				
				fle.Hold_Releaseactions(AccNo,Testname);
				
				
				String RowNumber1  = fle.CampaignManagerFunctionality(AccNo,"CM_PlainRecipient_AutoTest",Testname);
				log.logLine(Testname, false, "campaign creation with templatetype CM_VariableRecipient_AutoTest is successful");
				
			
				String RowNumber2 = fle.CampaignManagerFunctionality(AccNo1,"CM_VariableRecipient_AutoTest",Testname);
				log.logLine(Testname, false, " campaign creation with templatetype CM_VariableRecipient_AutoTest is successful");
				
				Thread.sleep(150000);
				fle.GmailLogin(Testname);
				Thread.sleep(5000);
				fle.CampaignGmailVerification("Plain Text Template for CM testing",Testname);
				Thread.sleep(50000);
				fle.CampaignGmailVerification("Variable Text Template for CM testing",Testname);
				
				Retuser1=fle.ApplicationBaseState(Testname, RowNumber1, RowNumber2,"Auto" +AccNo1);
				
				if (Retuser1) {		
					log.logLine(Testname, false, "Verification of New Campaign Manager with gmail validation is successful");
					
				} else {
					log.logLine(Testname, true, "Verification of New Campaign Manager with gmail validation is unsucessful");
				}				
				return Retuser1;
				}	
	
	}

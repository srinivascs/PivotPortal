
package testCases;

import launchAuto.Initialization;
import launchAuto.Log;
import org.openqa.selenium.WebDriver;

import pivotModules.HTMLFileOperations;

public class TestS_056 {

	private WebDriver driver;
	private Log log;
	
	public static long startTime;
	
	public	TestS_056(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
			public Boolean test56() throws Exception  {
				 
				
				int paperID = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);		
				String AccNo = Integer.toString(paperID);	
				String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
				
				boolean Retuser1 = false , Retuser2=false , Retuser3=false , Retuser4=false, Retuser5=false;	
				
				HTMLFileOperations  fle = new HTMLFileOperations(driver, log);	
				Retuser1 = fle.FileOperationspage(AccNo, Testname);
						
				if (Retuser1) {		
					log.logLine(Testname, false, "Verification the client/application selection of  Archives is sucessful");
					
				} else {
					log.logLine(Testname, true, "Verification the client/application selection of Archives is unsucessful");
				}
				
				
				//wait for the file to upload			
 				 long timenow = System.currentTimeMillis();
		 		 long testime = timenow - Initialization.startTime;
		 		 int totalTime =(int) ((testime/(1000*60)));	 
		 		 
		 		 if (25 > totalTime) {
		 			 int WaitTime = 25 - totalTime; 
		 			 log.logLine(Testname, false, "Going to wait for "+WaitTime +"minutes, please wait...");
		 			 Thread.sleep(WaitTime*1000*60);		 
		 		 }
				 
				
				Retuser2 = fle.FileVerification(AccNo, Testname);
				
				if (Retuser2) {		
					log.logLine(Testname, false, "Verification the Archives search/open file verification is sucessful");
					
				} else {
					log.logLine(Testname, true, "Verification the Archives search/open file verification is unsucessful");
				}
				
			if ((Initialization.Browser.equalsIgnoreCase("ff")) || (Initialization.Browser.equalsIgnoreCase("firefox"))) {
				
					Retuser3 = fle.FileVerificationDownload_FF(AccNo, Testname);
					
					if (Retuser3) {		
						log.logLine(Testname, false, "Verification the Archives search/open file verification is sucessful");
						
					} else {
						log.logLine(Testname, true, "Verification the Archives search/open file verification is unsucessful");
					}
			} else if (Initialization.Browser.equalsIgnoreCase("chrome") || Initialization.Browser.equalsIgnoreCase("ie") || Initialization.Browser.equalsIgnoreCase("internetexplorer")) {
					
				//Retuser2 = fle.FileVerificationDownload_FF(AccNo, Testname);
				Retuser3 = fle.FileVerificationDownload(AccNo, Testname);
					
					if (Retuser3) {		
						log.logLine(Testname, false, "Verification the Archives search/open file verification is sucessful");
						
					} else {
						log.logLine(Testname, true, "Verification the Archives search/open file verification is unsucessful");
					}
			}
			
				Retuser4 = fle.VerifyOptions(AccNo, Testname);
				if (Retuser4) {		
					log.logLine(Testname, false, "Verification of the document options under Choose Action operations is sucessful");
					
				} else {
					log.logLine(Testname, true, "Verification of the document options under Choose Action operations is unsucessful");
				}
			
			/*
				Retuser5 = fle.TestHarnessSearch(AccNo, Testname);
				if (Retuser5) {		
					log.logLine(Testname, false, "Verification the Test Harness search operations is sucessful");
					
				} else {
					log.logLine(Testname, true, "Verification the Test Harness search operations is unsucessful");
				}
				
				*/
				
				return Retuser4;		
			}	
	
	}

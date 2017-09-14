package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.UploadProof;
import pivotModules.ViewProofs;

public class TestS_010 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_010(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test10() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
		
		boolean Retuser1, Retuser2 = false, Retuser3 = false, Retuser4 = false;;
		ViewProofs repts = new ViewProofs(driver, log);
		Retuser1 = repts.proofViewer(AccNo, Testname);
		
		if (Retuser1) {
			//Verify the negative cases of advanced search
			Retuser2 = repts.AdvsrchNegativecase(Testname);		
			if (Retuser2){
				log.logLine(Testname, false, "Verifying the negative cases of advanced search funtionality in proofs is successful");
			} else {
				log.logLine(Testname, true, "Verifying the negative cases of advanced search funtionality in proofs is unsuccessful");
			}	
			
			
			//Verify the Edit proofing
			Retuser3 = repts.editProofing(Testname, AccNo);			
			if (Retuser3){
				log.logLine(Testname, false, "Verifying the editing of proof is successful");
			} else {
				log.logLine(Testname, true, "Verifying the editing of proof is unsuccessful");
			}
			
			UploadProof proof = new UploadProof(driver, log);
			boolean Retuploadval = proof.Uploadproof(AccNo, Testname, "Multiple");
			
			if (Retuploadval) {
				
				//Verify the Delete proofing
				Retuser4 = repts.deleteProofing(Testname, AccNo);				
				if (Retuser4){
					log.logLine(Testname, false, "Verifying the deleting of proof is successful");
				} else {
					log.logLine(Testname, true, "Verifying the deleting of proof is unsuccessful");
					throw new Exception("Verifying the deleting of proof is unsuccessful");

				}			
				
			}
			/*
			Set<String> handles = driver.getWindowHandles();
		    String firstWinHandle = driver.getWindowHandle(); 
		    handles.remove(firstWinHandle);
		    
		    boolean browserexist = handles.iterator().hasNext();
		    if (browserexist) {
			    String winHandle=handles.iterator().next();
			    if (winHandle!=firstWinHandle){
			    	//To retrieve the handle of second window, extracting the handle which does not match to first window handle
			    	String secondWinHandle=winHandle; //Storing handle of second window handle
			    
			    	//Switch control to new window
			    	driver.switchTo().window(secondWinHandle);			    	
			    	
			    	Thread.sleep(1000);
			    	driver.close();
			    	
			    	// Switching back to parent window
				    driver.switchTo().window(firstWinHandle);
			    }
		    }
			*/	 
			
		}
		
		return Retuser4;		
	}	
	
}


package testCases;

import launchAuto.Log;
import org.openqa.selenium.WebDriver;
import pivotModules.PublicDocuments;

public class TestS_045 {

	private WebDriver driver;
	private Log log;
	
	public	TestS_045(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}

		public Boolean test45() throws Exception  {
			 
			
			int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
			String AccNo = Integer.toString(paperID);	
			String Testname = new Object(){}.getClass().getEnclosingClass().getName();		
			
			boolean Retuser1 = false , Retuser2=false , Retuser3=false, Retuser4=false, Retuser5=false, Retuser6=false, Retuser7=false, Retuser8=false;	
			PublicDocuments pd = new PublicDocuments(driver, log);	
			
			Retuser1 = pd.ClientAdminSettings(Testname, AccNo);
					
			if (Retuser1) {		
				log.logLine(Testname, false, "Verification of the client/app admin settings is sucessful");
				
			} else {
				log.logLine(Testname, true, "Verification of the client/app admin settings  is unsucessful");
			}	
			
			
			Retuser2 = pd.ArchiveClientAppSelection(Testname);
			if (Retuser2) {		
				log.logLine(Testname, false, "Verification of selecting the client and application in Archives is sucessful");
				
			} else {
				log.logLine(Testname, true, "Verification of selecting the client and application in Archives is unsucessful");
			}
			
			pd.clickOnPublicDocmntTool(Testname);
			
			Retuser3 = pd.UploadPublicDocs(Testname);
			if (Retuser3) {		
				log.logLine(Testname, false, "Verification of uploading the public documents is sucessful");
				
			} else {
				log.logLine(Testname, true, "Verification of uploading the public documents is unsucessful");
			}
			
			Retuser4 = pd.SearchPublicDocs(Testname);
			if (Retuser4) {		
				log.logLine(Testname, false, "Verification of searching the public documents is sucessful");
				
			} else {
				log.logLine(Testname, true, "Verification of searching the public documents is unsucessful");
			}
		
			
			Retuser5 = pd.pd_displayActions(Testname, AccNo);
			if (Retuser5) {		
				log.logLine(Testname, false, "Verification of the various operations as Edit, Cancel, Update and Delete is sucessful");
				
			} else {
				log.logLine(Testname, true, "Verification of the various operations as Edit, Cancel, Update and Delete is unsucessful");
			}
		
			Retuser6 = pd.pd_MultipleRecordViewableActions(Testname);
			if (Retuser6) {		
				log.logLine(Testname, false, "Verification of the make viewable documents in Multi Actions is sucessful");
				
			} else {
				log.logLine(Testname, true, "Verification of the make viewable documents in Multi Actions is unsucessful");
			}
			
			
			
			Retuser7 = pd.pd_MultipleRecordDeleteActions(Testname);
			if (Retuser7) {		
				log.logLine(Testname, false, "Verification of deleting the multi records is sucessful");
				
			} else {
				log.logLine(Testname, true, "Verification of deleting the multi records is unsucessful");
			}
			
			Retuser8 = pd.pd_DeleteRecords(Testname);
			if (Retuser8) {		
				log.logLine(Testname, false, "Verification of deleting the multi records is sucessful");
				
			} else {
				log.logLine(Testname, true, "Verification of deleting the multi records is unsucessful");
			}
			
			
			return Retuser8;		
		}	

}

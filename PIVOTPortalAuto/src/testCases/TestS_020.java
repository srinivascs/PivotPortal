package testCases;

import launchAuto.Log;
import pivotModules.AdminConfig;
import pivotModules.ArchiveConcatPDF;
import pivotModules.ArchivesSimpleSrch;
import org.openqa.selenium.WebDriver;

public class TestS_020 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_020(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;	
	}
	
	public Boolean test20() throws Exception  {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		
		
		boolean Retuser2=false,Retuser7=false, Retuser3=false, Retuser4=false, Retuser5=false, Retuser6=false, Retuser8=false, Retuser9=false,Retuser11=false, Retuser12=false, Retuser13=false,Retuser14=false,Retuser15=false;;
		String Retuser1=null, Retuser10=null;
		
		AdminConfig admn = new AdminConfig(driver, log);
		Retuser1 = admn.ClientAppsrch(AccNo, Testname);	
		
		if (Retuser1.equals("")) {
			return false;
		}
		
		if (!Retuser1.equals(null)){			
				
			Retuser2 = admn.consentSettings(AccNo, Testname, Retuser1);	
			if (Retuser2){
				log.logLine(Testname, false, "Verification of the pdf concatenation settings and display mail in client app admin is successful");
			} else {
				log.logLine(Testname, true, "Verification of the pdf concatenation settings and display mail in client app admin is  unsuccessful");
			}
		
		
			//Retuser2 = admn.ChangetoSimpleSrch(AccNo, Testname);	
			Retuser3 = admn.PDFSettingsSendMail(AccNo, Testname);	
			if (Retuser3){
				log.logLine(Testname, false, "Verification of the pdf concatenation settings and display mail in client app admin is successful");
			} else {
				log.logLine(Testname, true, "Verification of the pdf concatenation settings and display mail in client app admin is  unsuccessful");
			}
			
			Retuser4 = admn.ChangetoSimpleSrch(AccNo, Testname, Retuser1);		
			if (Retuser4){
				log.logLine(Testname, false, "Verification of changing to simple search is successful");
			} else {
				log.logLine(Testname, true, "Verification of changing to simple search is  unsuccessful");
			}
		
			
			//ArchivesSimpleSrch arch = new ArchivesSimpleSrch(driver, log);	
			//Retuser3 = arch.ArchiveClientAppSel(AccNo, Testname);	
			Retuser5 = admn.ManageUsers(AccNo, Testname, Retuser1);		
			if (Retuser5){
				log.logLine(Testname, false, "Verification of the archives client app selection in user admin is successful");
			} else {
				log.logLine(Testname, true, "Verification of the archives client app selection in user admin is  unsuccessful");
			}
			
			Retuser6 = admn.EditManageUserconsent(AccNo, Testname, Retuser1);
			
			if (Retuser6){
				log.logLine(Testname, false, "Verification of Editing the manage users consent in user Configuration is successful");
			} else {
				log.logLine(Testname, true, "Verification of Editing the manage users consent in user Configuration is  unsuccessful");
			}		
			

			ArchivesSimpleSrch arch = new ArchivesSimpleSrch(driver, log);	
			Retuser7 = arch.ArchiveClientAppSel(AccNo, Testname, Retuser1);
			if (Retuser7){
				log.logLine(Testname, false, "Verification of client and application selection in Archives is successful");
			} else {
				log.logLine(Testname, true, "Verification of client and application selection in Archives is  unsuccessful");
			}
			
			
			//ArchivesSimpleSrch arch1= new ArchivesSimpleSrch(driver, log);	
			Retuser8 = arch.simpleArchiveSearch2(AccNo, Testname);
			if (Retuser8){
				log.logLine(Testname, false, "Verification of editing the document by attachment of pdf  is successful");
			} else {
				log.logLine(Testname, true, "Verification of editing the document by attachment of pdf  is  unsuccessful");
			}			
			
			
			ArchiveConcatPDF conc = new ArchiveConcatPDF(driver, log);
			Retuser9 = conc.CombinePDF(AccNo, Testname);
			if (Retuser9){
				log.logLine(Testname, false, "Verification of combining the pdf's is successful");
			} else {
				log.logLine(Testname, true, "Verification of combining the pdf's is  unsuccessful");
			}
			
					
			Retuser10 = admn.ClientAppsrch(AccNo, Testname);	
			if (!Retuser10.equals(null)){
				log.logLine(Testname, false, "Verification of selecting client and application in client/app admin is successful");
			} else {
				log.logLine(Testname, true, "Verification of selecting client and application in client/app admin is  unsuccessful");
			}
			
					
			Retuser11 = admn.PDFSettingsNoMail(AccNo, Testname);	
			if (Retuser11){
				log.logLine(Testname, false, "Verification of the pdf concatenation settings and display no mail in client app admin is successful");
			} else {
				log.logLine(Testname, true, "Verification of the pdf concatenation settings and display no mail in client app admin is successful");
			}
			
			//ArchivesSimpleSrch arch = new ArchivesSimpleSrch(driver, log);	
			Retuser12 = arch.ArchiveClientAppSel(AccNo, Testname, Retuser10);
			if (Retuser12){
				log.logLine(Testname, false, "Verification of client and application selection in Archives is successful");
			} else {
				log.logLine(Testname, true, "Verification of client and application selection in Archives is  unsuccessful");
			}
			
			Retuser13 = conc.CombinePDF(AccNo, Testname);
			if (Retuser13){
				log.logLine(Testname, false, "Verification of combining the pdf's is successful");
			} else {
				log.logLine(Testname, true, "Verification of combining the pdf's is unsuccessful");
			}
		
		
			ArchivesSimpleSrch arch1 = new ArchivesSimpleSrch(driver, log);	
			Retuser14 = arch1.ArchiveClientAppSel1(AccNo, Testname, Retuser10);
			if (Retuser14){
				log.logLine(Testname, false, "Verification of client and application selection in Archives is successful");
			} else {
				log.logLine(Testname, true, "Verification of client and application selection in Archives is  unsuccessful");
			}
			ArchiveConcatPDF conc1 = new ArchiveConcatPDF(driver, log);
			Retuser15 = conc1.SkyblueCombinePDF(AccNo, Testname);
			if (Retuser15){
				log.logLine(Testname, false, "Verification of combining the pdf's is successful");
			} else {
				log.logLine(Testname, true, "Verification of combining the pdf's is unsuccessful");
			}
			
		}
		
		return true;
	
	}
}
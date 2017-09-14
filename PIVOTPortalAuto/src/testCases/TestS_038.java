package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;
import pivotModules.ProofTesting;


    public class TestS_038 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_038(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;
	}

	public Boolean test38() throws Exception {		
		
		int paperID = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);
		String AccNo = Integer.toString(paperID);
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		
		boolean Retuser1, Retuser2, Retuser3 , Retuser4, Retuser5, Retuser6, Retuser7, Retuser8;
		String RandNo = Integer.toString(paperID);
		
	ProofTesting proof = new ProofTesting(driver, log);
	
	Retuser1 = proof.ProfileSettings(AccNo, Testname);
		
		if (Retuser1) {
			
			log.logLine(Testname, false, "Verification of the profile changes is successfull");
		} else {				
			log.logLine(Testname, true, "Verification of the profile changes is unsuccessfull");
			throw new Exception("Verification of the profile changes is unsuccessfull");
		}
	
		Retuser2 = proof.SingleUploadproof(AccNo, Testname, "Single");		
		
		if (Retuser2){
			log.logLine(Testname, false, "Proofs - Uploading a single pdf proof file is successful");
		} else {
			log.logLine(Testname, true, "Proofs - Uploading a single pdf proof file is unsuccessful");
		}

		Retuser3 = proof.MultipleUploadproof(AccNo, Testname, "Multiple");
		if (Retuser3){
			log.logLine(Testname, false, "Proofs - Uploading a Multiple pdf proof file is successful");
		} else {
			log.logLine(Testname, true, "Proofs - Uploading a Multiple pdf proof file is unsuccessful");
		}
		
		
		Retuser4 = proof.quickAdvancedSearch(Testname);
		if (Retuser4){
			log.logLine(Testname, false, "Proofs - quicksearch of the data is successful");
		} else {
			log.logLine(Testname, true, "Proofs - quicksearch of the data is unsuccessful");
		}
		
		Retuser5 = proof.rejectstatus(Testname);
		if (Retuser5){
			log.logLine(Testname, false, "Proofs - rejection of proof file is successful");
		} else {
			log.logLine(Testname, true, "Proofs - rejection of proof file is unsuccessful");
		}
		
		Retuser6 = proof.VerifyComments(Testname);
		if (Retuser6){
			log.logLine(Testname, false, "Proofs - add and view comments of  proof file is successful");
		} else {
			log.logLine(Testname, true, "Proofs - add and view comments of  proof file is unsuccessful");
		}

		
		Retuser7 = proof.proofEnhancements(RandNo, Testname);
		if (Retuser7){
			log.logLine(Testname, false, "Proofs - multi record action of proof file is successful");
		} else {
			log.logLine(Testname, true, "Proofs - multi record action of file is unsuccessful");
		}
					
		Retuser8 = proof.approvestatus(Testname);
		if (Retuser8){
			log.logLine(Testname, false, "Proofs - Approving of proof file is successful");
		} else {
			log.logLine(Testname, true, "Proofs - Approving of proof proof file is unsuccessful");
		}
		
		return Retuser8;

	}

}


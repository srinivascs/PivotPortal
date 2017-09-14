package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.ProofofReview;

public class TestS_037 {	
	private WebDriver driver;
	private Log log;

	public TestS_037(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;
	}

	public Boolean test37() throws Exception {

		int paperID = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);
		String AccNo = Integer.toString(paperID);

		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		boolean Retuser1, Retuser2=false, Retuser3=false,Retuser4=false,Retuser5=false;

		ProofofReview rpts = new ProofofReview(driver, log);
		Retuser1 = rpts.ClientAppSel(AccNo, Testname);

		// App ID-ABC1234 and All application as Yes

		if (Retuser1) {

			Retuser2 = rpts.UserActivityDetail(AccNo,Testname);
			//rpts.UserActivityDetail1(AccNo, Testname);


			// App ID-ABC0001 and All application as Yes
			rpts.ClientAppSel1(AccNo, Testname);
			rpts.UserActivityDetail(AccNo, Testname);

			//rpts.UserActivityDetail1(AccNo, Testname);

			if (Retuser2){
				log.logLine(Testname, false, "Verify the UserActivityDetail Under ReportWriter for Multiple Application is successfull");
			} else {				
				log.logLine(Testname, true, "Verify the UserActivityDetail Under ReportWriter for Multiple Application is Unsuccessfull");
				throw new Exception("Verify the UserActivityDetail Under ReportWriter for Multiple Application is Unsuccessfull");
			}

			// App ID-ABC1234 and All application as Yes
			rpts.ClientAppSel(AccNo, Testname);

			Retuser3 = rpts.UserLoginActivity(AccNo,Testname);
			//rpts.UserLoginActivity1(AccNo, Testname);


			// App ID-ABC0001 and All application as Yes
			rpts.ClientAppSel1(AccNo, Testname);
			rpts.UserLoginActivity(AccNo, Testname);
			//rpts.UserLoginActivity1(AccNo, Testname);

			if (Retuser3){
				log.logLine(Testname, false, "Verify the UserLoginActivity Under ReportWriter for Multiple Application is successfull");
			} else {
				log.logLine(Testname, true, "Verify the UserLoginActivity Under ReportWriter for Multiple Application is Unsuccessfull");
				throw new Exception("Verify the UserLoginActivity Under ReportWriter for Multiple Application is Unsuccessfull");
			}

			rpts.ClientAppSel(AccNo, Testname);

			Retuser4 = rpts.LinkTracking(AccNo,Testname);			
			if (Retuser4){
				log.logLine(Testname, false, "Verify the LinkTracking Under ReportWriter is successfull");
			} else {
				log.logLine(Testname, true, "Verify the LinkTracking Under ReportWriter is Unsuccessfull");
				throw new Exception("Verify the LinkTracking Under ReportWriter is Unsuccessfull");
			}

			Retuser5 = rpts.EmailTracking(AccNo,Testname);			
			if (Retuser5){
				log.logLine(Testname, false, "Verify the EmailTracking Under ReportWriter is successfull");
			} else {
				log.logLine(Testname, true, "Verify the EmailTracking Under ReportWriter is Unsuccessfull");
				throw new Exception("Verify the EmailTracking Under ReportWriter is Unsuccessfull");
			}

		}

		return Retuser5;

	}

}


package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.AdminConfig;
import pivotModules.eDeliveryAutomate;

public class TestS_026 {
	private WebDriver driver;
	private Log log;

	public TestS_026(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;
	}

	public Boolean test26() throws Exception {

		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);
		String RandNo = Integer.toString(paperID);

		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		boolean Retuser1=false, Retuser2=false, Retuser3=false, Retuser4=false, Retuser5=false, Retuser6=false,Retuser7=false;
		String SetWind1 = null, SetWind2 = null;


		AdminConfig test = new AdminConfig(driver, log);
		String SetWind = test.ClientAppsrch(RandNo, Testname);

		if (SetWind.equals("")) {			
			return false;			
		}

		test.ChangeHostedType(RandNo, Testname, SetWind);

		driver.close();		
		driver.switchTo().window(SetWind);


		eDeliveryAutomate edel = new eDeliveryAutomate(driver, log);		
		
		SetWind1 = edel.eDeliveryLogin(RandNo, Testname, "1", "ABC Company", "ABC1234 - ABC1234");

		if (!SetWind1.equals(null)) {

			Retuser2 = edel.SearchDocuments(RandNo, Testname, "1");
			if (Retuser2){				
				log.logLine(Testname, false, "Verification of document search with various methods in eDelivery application is successful");
			} else {
				log.logLine(Testname, true, "Verification of document search with various methods in eDelivery application is unsuccessful");				
			}			

			Retuser3 = edel.DeliveryOptions(Testname, "");
			if (Retuser3){				
				log.logLine(Testname, false, "Verification of Delivery options page is successful");
			} else {
				log.logLine(Testname, true, "Verification of Delivery options page is Unsuccessful");				
			}

			Retuser4 = edel.ProfilePage(Testname, RandNo);
			if (Retuser4){				
				log.logLine(Testname, false, "Verification of Profiles page is successful");
			} else {
				log.logLine(Testname, true, "Verification of Profiles page is Unsuccessful");				
			}
		}	
		driver.close();
		driver.switchTo().window(SetWind1);	



		//CSR page verification
		SetWind2 = edel.eDeliveryLogin(RandNo, Testname, "2", "Veterinary Pet Insurance", "ALL");

		if (!SetWind2.equals(null)) {

			Retuser1 = edel.SearchDocuments(RandNo, Testname, "2");
			if (Retuser1){				
				log.logLine(Testname, false, "Verification of document search with various methods using CSR user is successful");

				Retuser5 = edel.UserConsent(Testname);
				if (Retuser5){				
					log.logLine(Testname, false, "Verification of Consent/users page is successful");
				} else {
					log.logLine(Testname, true, "Verification of Consent/users page is Unsuccessful");				
				}

			} else {
				log.logLine(Testname, true, "Verification of document search with various methods using CSR user is Unsuccessful");				
			}

		}

		driver.close();
		driver.switchTo().window(SetWind1);

	
		Retuser6=edel.edelivery_consent(Testname);		

		log.logLine(Testname,false, "execution successful");


		return Retuser6;

	}

}

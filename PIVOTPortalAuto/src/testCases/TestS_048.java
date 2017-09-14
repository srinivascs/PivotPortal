package testCases;

import launchAuto.Log;
import org.openqa.selenium.WebDriver;
import pivotModules.RegistrationAndConsetpassword;


public class TestS_048 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_048(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;
	}

	public Boolean test48() throws Exception {
		
		int paperID = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);
		String AccNo = Integer.toString(paperID);
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		
		String Retuser1, Retuser3, Retuser5, firstWinHandle, SecondWinHandle, ThirdWinHandle;
		boolean Retuser2=false, Retuser4=false, Retuser6=false;	
		
		RegistrationAndConsetpassword Regis = new RegistrationAndConsetpassword(driver, log);
		firstWinHandle = Regis.ClientAppSettings(AccNo, Testname, "Registration");
		
		Retuser1 = Regis.RegistrationReq(Testname, firstWinHandle);
		
		Thread.sleep(20000);
		if (Retuser1.equalsIgnoreCase("")) {			
			return false;		
		}else if (Retuser1.equalsIgnoreCase("yes")) {
			Retuser2 = Regis.applicationRegister(AccNo,Testname, firstWinHandle);
			
			if (Retuser2){
				log.logLine(Testname, false, "Verification of the 3.7 Registration is successful");
				driver.close();
				driver.switchTo().window(firstWinHandle);
			} else {				
				log.logLine(Testname, true, "Verification of 3.7 Registration is unsuccessful");
				driver.close();
				driver.switchTo().window(firstWinHandle);
				throw new Exception("Verification of 3.7 Registration is unsuccessful");	
			}
		}
		
		/*//Consent password Reset
		SecondWinHandle = Regis.ClientAppSettings(AccNo, Testname, "PasswordReset");
		
		Retuser3 = Regis.DontPromptPasswd(Testname, SecondWinHandle);
		
		if (Retuser3.equalsIgnoreCase("")) {			
			return false;		
		}else if (Retuser3.equalsIgnoreCase("yes")) {
			Retuser4 = Regis.PasswordConsent(AccNo,Testname, SecondWinHandle, "");
			
			if (Retuser4){
				log.logLine(Testname, false, "Verification of Do not Prompt Password Reset for First Login is successful");
				driver.close();
				driver.switchTo().window(SecondWinHandle);
			} else {				
				log.logLine(Testname, true, "Verification of Do not Prompt Password Reset for First Login is unsuccessful");
				driver.close();
				driver.switchTo().window(SecondWinHandle);
				throw new Exception("Verification of Do not Prompt Password Reset for First Login is unsuccessful");	
			}
		}
		
		
		//Consent password Reset
		ThirdWinHandle = Regis.ClientAppSettings(AccNo, Testname, "PasswordReset");
		
		Retuser5 = Regis.DoPromptPasswd(Testname, ThirdWinHandle);
		
		if (Retuser5.equalsIgnoreCase("")) {			
			return false;		
		}else if (Retuser5.equalsIgnoreCase("yes")) {
			Retuser6 = Regis.PasswordConsent(AccNo,Testname, ThirdWinHandle, "yes");
			
			if (Retuser6){
				log.logLine(Testname, false, "Verification of Do Prompt Password Reset for First Login is successful");
				driver.close();
				driver.switchTo().window(ThirdWinHandle);
			} else {				
				log.logLine(Testname, true, "Verification of Do Prompt Password Reset for First Login is unsuccessful");
				driver.close();
				driver.switchTo().window(ThirdWinHandle);
				throw new Exception("Verification of Do Prompt Password Reset for First Login is unsuccessful");	
			}
		}*/
		
		
		return true;

	}

}
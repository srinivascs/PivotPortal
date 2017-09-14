package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.ArchivesMsgCenter;
import pivotModules.ConfigMsgCenter;
	

public class TestS_022 {	
	private WebDriver driver;
	private Log log;
	
	public TestS_022(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;
	}

	public Boolean test22() throws Exception {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);
		String AccNo = Integer.toString(paperID);
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();
		
		boolean Retuser2=false, Retuser4=false, Retuser6=false;
		String Retuser1=null, Retuser3=null, Retuser5=null;
		
		ConfigMsgCenter admn = new ConfigMsgCenter(driver, log);		
		Retuser1 = admn.MessageConfig1(AccNo, Testname);
		
		if (Retuser1.equals("")) {
			return false;
		}
	
	    if (!Retuser1.equals("")) {
	    	
			ArchivesMsgCenter DocAdmn = new ArchivesMsgCenter(driver, log);
			Retuser2 = DocAdmn.MessageVerify1(AccNo, Testname, Retuser1);		
			if (Retuser2){
				log.logLine(Testname, false, "Verifying of the message1 added in the previous step of Configuration1 is successful");
			} else {
				log.logLine(Testname, true, "Verifying of the message1 added in the previous step of Configuration1 is unsuccessful");
			}
			
			//ConfigMsgCenter admn = new ConfigMsgCenter(driver, log);
			Retuser3 = admn.MessageConfig2(AccNo, Testname);			
			if (!Retuser3.equals(null)){
				log.logLine(Testname, false, "Editing the message1 in the search document section of the message center is successful");
			} else {
				log.logLine(Testname, true, "Editing the message1 in the search document section of the message center is is unsuccessful");
			}
			
			
			Retuser4 = DocAdmn.MessageVerify2(AccNo, Testname, Retuser3);
			if (Retuser4){
				log.logLine(Testname, false, "Verifying the edited message1 in message center envelope successful");
			} else {
				log.logLine(Testname, true, "Verifying the edited message1 in message center envelope is unsuccessful");
			}
			
			
            Retuser5 = admn.MessageConfig3(AccNo, Testname);			
			if (!Retuser5.equals(null)){
				log.logLine(Testname, false, "Deleting the edited message1 in search document section of the message center in Documentation Admin section");
			} else {
				log.logLine(Testname, true, "Deleting the edited message1 in search document section of the message center in Documentation Admin section is unsuccessful");
			}
			
			
			Retuser6 = DocAdmn.MessageVerify3(AccNo, Testname, Retuser5);			
			if (Retuser6){
				log.logLine(Testname, false, "Verifying the deleted message does not exist in message center successful");
			} else {
				log.logLine(Testname, true, "Verifying the deleted message does not exist in message center is unsuccessful");
			}
		
		
	    }
		
		return Retuser6;
	}
	
}


package testCases;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

import pivotModules.Modules_Workflow;


public class TestS_076{
	private WebDriver driver;
	private Log log;

	public TestS_076(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;
	}

	public Boolean test76() throws Exception {

		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);
		String RandNo = Integer.toString(paperID);

		String Testname = new Object(){}.getClass().getEnclosingClass().getName();

		boolean Retuser1=false,Retuser2=false;;

		Modules_Workflow  val= new Modules_Workflow(driver, log);	
		
		
		Retuser1=val.Notificationtemplate_update(Testname);		

		if (Retuser1){				
			log.logLine(Testname, false, "Validations of Notification template changes in workflow module is successful");
		} else {
			log.logLine(Testname, true, "Validations of Notification template changes in workflow module is Unsuccessful");				
		}

		return Retuser1;

	}

}


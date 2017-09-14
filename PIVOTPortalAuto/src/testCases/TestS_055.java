package testCases;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import launchAuto.Initialization;
import launchAuto.Log;

import org.openqa.selenium.WebDriver;

public class TestS_055 {
	private WebDriver driver;
	private Log log;
	
	public TestS_055(WebDriver driver, Log log) {
		this.driver = driver;
		this.log = log;
	}
	
	public Boolean test55() throws Exception {
		
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);
		String RandNo = Integer.toString(paperID);		
		String Testname = new Object(){}.getClass().getEnclosingClass().getName();	
				
		String command="C:/Pivot_Portal/SAML_Auto/SAML_Auto.exe "+Initialization.EnvirSite;		
		
		try
        {			
			log.logLine(Testname, false, "Starting the Pivot SAML window verification...");
            Process proc = Runtime.getRuntime().exec(command);
                        
            proc.waitFor();
            proc.destroy();
            
            int exitVal = proc.exitValue();
            System.out.println("Process exitValue: " + exitVal);
            
            BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
              System.out.println("value expected " +line);
            }
            if (exitVal != 0) {
            	log.logLine(Testname, true, "Verification of Pivot SAML test is unsuccesssful");
            	throw new Exception("Verification of Pivot SAML test is unsuccesssful");
            }else {
            	log.logLine(Testname, false, "Verification of Pivot SAML test is successsful");
            }
            
        } catch (Throwable t) {
        	log.logLine(Testname, true, "Verification of Pivot SAML test is unsuccesssful");
        	t.printStackTrace();
        	throw new Exception("Verification of Pivot SAML test is unsuccesssful");
        }
	
		return true;
	}

}

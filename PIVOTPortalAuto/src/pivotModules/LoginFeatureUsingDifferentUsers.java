package pivotModules;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import launchAuto.Initialization;
import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class LoginFeatureUsingDifferentUsers extends Page{

	public LoginFeatureUsingDifferentUsers(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	} 
	@Override
	protected void load() {}
	@Override

	protected void isLoaded() throws Error {}

	public boolean VerifyUserAccess(String AccNo, String Testname) throws Exception { 		

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile")); 		
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();


		//driver.switchTo().frame("iframeContainer");
		Thread.sleep(1000);
		driver.switchTo().defaultContent();


		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");
			//throw new Exception("Clicking on Cancel button in Client/App popup is failed");
		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("SignOutBtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("SignOutBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Sign Out button");
			
		}else if (doesElementExist2(properties.getProperty("SignOutBtn1"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("SignOutBtn1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Sign Out button");
			
		}else if (doesElementExist2(properties.getProperty("SignOutBtn2"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("SignOutBtn2")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Sign Out button");
		}
		
		else {
			log.logLine(Testname, true, "Clicking on Sign Out button is failed");

		}

		FirstUser(Testname);
		SecondUser(Testname);
		ThirdUser(Testname);
		FourthUser(Testname);
		Relogin(Testname);

		return true;
	}

	public boolean FirstUser(String Testname) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		String Usrnme = test.readColumnData("UserName", sheetname);
		String Paswd = test.readColumnData("Password", sheetname);

		if (doesElementExist2(properties.getProperty("UserName"), 5)) {	    
			WebElement Username = driver.findElement(By.cssSelector(properties.getProperty("UserName")));
			Username.sendKeys(Usrnme);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entering the User Name as" +Usrnme);
		} else {
			log.logLine(Testname, true, "Entering user name is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
		}

		if (doesElementExist2(properties.getProperty("Password"), 5)) {	    
			WebElement Password = driver.findElement(By.cssSelector(properties.getProperty("Password")));
			Password.sendKeys(Paswd);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entering the Password as" +Paswd);
		} else {
			log.logLine(Testname, true, "Entering Password is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
		}


		if (doesElementExist2(properties.getProperty("loginBtn"), 5)) {	    
			WebElement lgnbtn = driver.findElement(By.cssSelector(properties.getProperty("loginBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", lgnbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Click on Login Button");
		} else {
			log.logLine(Testname, true, "Click on Login Button is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("FrstuserModule"), 5)) {	    
			String Mod = driver.findElement(By.cssSelector(properties.getProperty("FrstuserModule"))).getText();
			log.logLine(Testname, false, "Module present for this user is >>>>> "+Mod);
		} else {
			log.logLine(Testname, true, "No Modules Present");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("FrstuserOtherModules"), 5)) {	    
			String Mod = driver.findElement(By.cssSelector(properties.getProperty("FrstuserOtherModules"))).getText();
			log.logLine(Testname, false, "Module is present for this user is "+Mod);
		} else {
			log.logLine(Testname, false, "Other Modules are not Present for this user");
		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("FrstusrSignOutBtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("FrstusrSignOutBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicking on Sign Out button");
			
		}else if (doesElementExist2(properties.getProperty("FrstusrSignOutBtn1"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("FrstusrSignOutBtn1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicking on Sign Out button for First user");
			
		}else if (doesElementExist2(properties.getProperty("FrstusrSignOutBtn2"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("FrstusrSignOutBtn2")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicking on Sign Out button for First User");
		}
		else if (doesElementExist2(properties.getProperty("FrstusrSignOutBtn3"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("FrstusrSignOutBtn3")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicking on Sign Out button for First user");
		}
		else {
			log.logLine(Testname, true, "Clicking on Sign Out button is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
		}


		return true;
	}


	public boolean SecondUser(String Testname) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		String Usrnme = test.readColumnData("UserName", sheetname);
		String Paswd = test.readColumnData("Password", sheetname);

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("UserName"), 5)) {	    
			WebElement Username = driver.findElement(By.cssSelector(properties.getProperty("UserName")));
			Username.sendKeys(Usrnme);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entering the User Name as" +Usrnme);
		} else {
			log.logLine(Testname, true, "Entering user name is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);	
		}

		if (doesElementExist2(properties.getProperty("Password"), 5)) {	    
			WebElement Password = driver.findElement(By.cssSelector(properties.getProperty("Password")));
			Password.sendKeys(Paswd);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entering the Password as" +Paswd);
		} else {
			log.logLine(Testname, true, "Entering Password is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
		}


		if (doesElementExist2(properties.getProperty("loginBtn"), 5)) {	    
			WebElement lgnbtn = driver.findElement(By.cssSelector(properties.getProperty("loginBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", lgnbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Click on Login Button");
		} else {
			log.logLine(Testname, true, "Click on Login Button is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);	
		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
		}

		if (doesElementExist2(properties.getProperty("ScnduserModule"), 5)) {	    
			String Mod = driver.findElement(By.cssSelector(properties.getProperty("ScnduserModule"))).getText();
			log.logLine(Testname, false, "Module present for this user is >>>>> "+Mod);
			
		}else if (doesElementExist2(properties.getProperty("ScnduserModule1"), 5)) {	    
			String Mod = driver.findElement(By.cssSelector(properties.getProperty("ScnduserModule1"))).getText();
			log.logLine(Testname, false, "Module present for this user is >>>>> "+Mod);
		}else {
			log.logLine(Testname, true, "No Modules Present");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
		}

		if (doesElementExist2(properties.getProperty("ScnduserOtherModules"), 5)) {	    
			String Mod = driver.findElement(By.cssSelector(properties.getProperty("ScnduserOtherModules"))).getText();
			log.logLine(Testname, false, "Module present for this user is "+Mod);
		} else {
			log.logLine(Testname, false, "Other Modules are not Present for this user");

		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("ScndusrSignOutBtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("ScndusrSignOutBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Sign Out button for Second user");
			
		}else if (doesElementExist2(properties.getProperty("ScndusrSignOutBtn1"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("ScndusrSignOutBtn1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Sign Out button for Second user");
			
		}else if (doesElementExist2(properties.getProperty("ScndusrSignOutBtn2"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("ScndusrSignOutBtn2")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Sign Out button for Second user");
			
		}else if (doesElementExist2(properties.getProperty("ScndusrSignOutBtn3"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("ScndusrSignOutBtn3")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Sign Out button for Second user");
		}
		
		else {
			log.logLine(Testname, true, "Clicking on Sign Out button is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);

		}


		return true;
	}


	public boolean ThirdUser(String Testname) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		String Usrnme = test.readColumnData("UserName", sheetname);
		String Paswd = test.readColumnData("Password", sheetname);

		if (doesElementExist2(properties.getProperty("UserName"), 5)) {	    
			WebElement Username = driver.findElement(By.cssSelector(properties.getProperty("UserName")));
			Username.sendKeys(Usrnme);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entering the User Name as" +Usrnme);
		} else {
			log.logLine(Testname, true, "Entering the User Name is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);		
		}

		if (doesElementExist2(properties.getProperty("Password"), 5)) {	    
			WebElement Password = driver.findElement(By.cssSelector(properties.getProperty("Password")));
			Password.sendKeys(Paswd);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entering the Password as" +Paswd);
		} else {
			log.logLine(Testname, true, "Enterring the User Name is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);		
		}


		if (doesElementExist2(properties.getProperty("loginBtn"), 5)) {	    
			WebElement lgnbtn = driver.findElement(By.cssSelector(properties.getProperty("loginBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", lgnbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Click on Login Button");
		} else {
			log.logLine(Testname, true, "Click on Login Button is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);	
		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);		
		}

		if (doesElementExist2(properties.getProperty("ThrduserModule"), 5)) {	    
			String Mod = driver.findElement(By.cssSelector(properties.getProperty("ThrduserModule"))).getText();
			log.logLine(Testname, false, "Module present for this user is >>>>> "+Mod);
		} else {
			log.logLine(Testname, true, "No Modules Present");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);	
		}

		if (doesElementExist2(properties.getProperty("ThrduserOtherModules"), 5)) {	    
			String Mod = driver.findElement(By.cssSelector(properties.getProperty("ThrduserOtherModules"))).getText();
			log.logLine(Testname, false, "Module present for this user is >>>>> "+Mod);
		} else {
			log.logLine(Testname, false, "Other Modules are not Present for this user");

		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("ThrdusrSignOutBtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("ThrdusrSignOutBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Sign Out button for Third user");

		}else if (doesElementExist2(properties.getProperty("ThrdusrSignOutBtn1"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("ThrdusrSignOutBtn1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Sign Out button for Third user");

		}	else if (doesElementExist2(properties.getProperty("ThrdusrSignOutBtn2"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("ThrdusrSignOutBtn2")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Sign Out button for Third user");
			
		}else if (doesElementExist2(properties.getProperty("ThrdusrSignOutBtn3"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("ThrdusrSignOutBtn3")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Sign Out button for Third user");
		}
		
		else {
			log.logLine(Testname, true, "Clicking on Sign Out button is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);

		}

		return true;
	}


	public boolean FourthUser(String Testname) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		String Usrnme = test.readColumnData("UserName", sheetname);
		String Paswd = test.readColumnData("Password", sheetname);

		if (doesElementExist2(properties.getProperty("UserName"), 5)) {	    
			WebElement Username = driver.findElement(By.cssSelector(properties.getProperty("UserName")));
			Username.sendKeys(Usrnme);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entering the User Name as" +Usrnme);
		} else {
			log.logLine(Testname, true, "Entering the User Name is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);	
		}

		if (doesElementExist2(properties.getProperty("Password"), 5)) {	    
			WebElement Password = driver.findElement(By.cssSelector(properties.getProperty("Password")));
			Password.sendKeys(Paswd);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entering the Password as" +Paswd);
		} else {
			log.logLine(Testname, true, "Entering the Password is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);	
		}


		if (doesElementExist2(properties.getProperty("loginBtn"), 5)) {	    
			WebElement lgnbtn = driver.findElement(By.cssSelector(properties.getProperty("loginBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", lgnbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Click on Login Button");
		} else {
			log.logLine(Testname, true, "Click on Login Button is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);	
		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
		}

		if (doesElementExist2(properties.getProperty("FrthuserModule"), 5)) {	    
			String Mod = driver.findElement(By.cssSelector(properties.getProperty("FrthuserModule"))).getText();
			log.logLine(Testname, false, "Module present for this user is >>>>> "+Mod);
		} else {
			log.logLine(Testname, true, "No Modules Present");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
		}

		if (doesElementExist2(properties.getProperty("FrthuserOtherModules"), 5)) {	    
			String Mod = driver.findElement(By.cssSelector(properties.getProperty("FrthuserOtherModules"))).getText();
			log.logLine(Testname, false, "Module present for this user is >>>>> "+Mod);
		} else {
			log.logLine(Testname, false, "Other Modules are not Present for this user");
		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("FrthusrSignOutBtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("FrthusrSignOutBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Sign Out button for Fourth user");

		}else if (doesElementExist2(properties.getProperty("FrthusrSignOutBtn1"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("FrthusrSignOutBtn1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Sign Out button for Fourth user");
			
		}else if (doesElementExist2(properties.getProperty("FrthusrSignOutBtn2"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("FrthusrSignOutBtn2")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Sign Out button for Fourth user");

		}else if (doesElementExist2(properties.getProperty("FrthusrSignOutBtn3"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("FrthusrSignOutBtn3")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Sign Out button for Fourth user");

		}
		
		else {
			log.logLine(Testname, true, "Clicking on Sign Out button is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);

		}

		return true;
	}



	

}

package pivotModules;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

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



public class ReportsClientUserAccessServices extends Page{
	public static String ClntName;
	public static String AppName;

	public ReportsClientUserAccessServices(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);

	} 
	@Override
	protected void load() {}
	@Override

	protected void isLoaded() throws Error {}


	public boolean ClientAppSel(String AccNo, String Testname) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(1000);
		driver.switchTo().defaultContent();

		Thread.sleep(2000);
		WebElement retelm2 = waitForElement(properties.getProperty("selClint1")); 


		if (doesElementExist2(properties.getProperty("Reports"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Reports")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);	    	 
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Click on Reports Module is Successful");
		} else {
			log.logLine(Testname, true, "Click on Reports Module is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Reports Module is failed");
		}



		//Selecting the Client and Application name from popup
		Thread.sleep(1000);
		boolean CliSelected = false;
		ClntName = test.readColumnData("ClientName1", sheetname);

		if (doesElementExist2(properties.getProperty("selClint1"), 5)) {
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selClint1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on ClientName dropdown..");


			if (doesElementExist2(properties.getProperty("ClinetOpts1"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ClinetOpts1")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().contains(ClntName)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the ClientName "+ClntName +" from the popup..");
						CliSelected = true;
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Client Name options are not displayed");
				throw new Exception("Client Name options are not displayed");
			}

		} else {
			log.logLine(Testname, true, "Client Name dropdown element does not exist");
			throw new Exception("Client Name dropdown element does not exist");
		}

		if (!CliSelected) {
			if (doesElementExist2(properties.getProperty("selClint2"), 5)) {	   
				WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selClint2")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

				Thread.sleep(1000);
				log.logLine(Testname, false, "Clicking on ClientName dropdown..");

				if (doesElementExist2(properties.getProperty("ClinetOpts2"), 5)) {
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ClinetOpts2")));
					for (WebElement lnk:selopts) {
						if (lnk.getText().contains(ClntName)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false, "Selecting the ClientName "+ClntName +" from the dropdown..");							
							break;
						}				
					}

				} else {
					log.logLine(Testname, true, "Client Name options are not displayed");
					throw new Exception("Client Name options are not displayed");
				}

			} else {
				log.logLine(Testname, true, "Client Name dropdown element does not exist");
				throw new Exception("Client Name dropdown element does not exist");
			}	    	
		}

		boolean AppSelected = false;
		AppName = test.readColumnData("ApplicationName1", sheetname);

		if (doesElementExist2(properties.getProperty("selAppl1"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selAppl1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on ApplicationName dropdown..");

			if (doesElementExist2(properties.getProperty("ApplOpts1"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ApplOpts1")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().contains(AppName)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Application Name "+AppName +" from the popup..");
						AppSelected = true;
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Application Name options are not displayed");
				throw new Exception("Application Name options are not displayed");
			}

		} else {
			log.logLine(Testname, true, "Application Name dropdown element does not exist");
			throw new Exception("Application Name dropdown element does not exist");
		}

		if (!AppSelected) {
			if (doesElementExist2(properties.getProperty("selAppl2"), 5)) {	   
				WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selAppl2")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

				Thread.sleep(1000);
				log.logLine(Testname, false, "Clicking on ApplicationName dropdown..");

				if (doesElementExist2(properties.getProperty("ApplOpts2"), 5)) {
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ApplOpts2")));
					for (WebElement lnk:selopts) {
						if (lnk.getText().contains(AppName)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false, "Selecting the Application Name "+AppName +" from the dropdown..");							
							break;
						}				
					}

				} else {
					log.logLine(Testname, true, "Application Name options are not displayed");
					throw new Exception("Application Name options are not displayed");
				}

			} else {
				log.logLine(Testname, true, "Application Name dropdown element does not exist");
				throw new Exception("Application Name dropdown element does not exist");
			}	    	
		}

		PleasewaitDisappear();

		//Click on Ok button
		if (doesElementExist2(properties.getProperty("Okbtn"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Okbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on OK button");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the Reports is failed");
			throw new Exception("Clicking on OK button to view the Reports is failed");
		}


		return true;
	}


	public boolean clientuseraccess(String AccNo,String Testname) throws Exception {
		
		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(5000);
		driver.switchTo().frame("iframeContainer");
		Thread.sleep(5000);
		String repname= test.readColumnData("Reportname", sheetname);
		
		if (doesElementExist2(properties.getProperty("ReportTypes"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("ReportTypes")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on ReportTypes Field drop down list in Report Writer");


			if (doesElementExist2(properties.getProperty("ReportTypeoptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ReportTypeoptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(repname)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						PleasewaitDisappear();
						Thread.sleep(6000);						
						log.logLine(Testname, false, "Selecting the Report option "+repname+" from the dropdown");
						break;
					}
				}
			} else {
				log.logLine(Testname, true, "Selecting the Report option  is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Selecting the Report option  is failed");
			}
		}else {
			log.logLine(Testname, true, "Clicking on the ReportTypes is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on the ReportTypes is failed");
		}

		//selecting the "all application"from list
		if (doesElementExist2(properties.getProperty("Plantbtn"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("Plantbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(500);
			log.logLine(Testname, false, "Clicking on Select Field Dropdown List");


			if (doesElementExist2(properties.getProperty("OptionType"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("OptionType")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("All Applications")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						PleasewaitDisappear();
						log.logLine(Testname, false, "Selecting the Plant option from the dropdown");
						break;
					}
				}
			} else {
				log.logLine(Testname, true, "Selecting the Plant option from the dropdown is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Selecting the Plant option from the dropdown is failed");
			}
		}else {
			log.logLine(Testname, true, "Clicking on the Options field is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on the Options field is failed");
		}

		Thread.sleep(3000);
		log.logLine(Testname, false, "Selecting the status 'Yes' from all applications");
		appstatus(Testname,"Yes");
		Thread.sleep(3000);
		reportvalidation(Testname);
		Thread.sleep(3000);
		log.logLine(Testname, false, "Selecting the status 'No' from all applications");
		driver.switchTo().frame("iframeContainer");
		appstatus(Testname,"No");
		reportvalidation(Testname);
		Thread.sleep(2000);

		return true;}


	public void Highlight(WebElement choseacts) throws Exception{
		for (int i = 0; i < 2; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			//    WebElement choseacts = null;
			js.executeScript( "arguments[0].setAttribute('style', arguments[1]);", choseacts , "color: red; border: 5px solid red;");
			Thread.sleep(1000);
			js.executeScript( "arguments[0].setAttribute('style', arguments[1]);", choseacts, "");
		}
	}


	private void reportvalidation(String Testname) throws Exception
	{
		//Run Report
		if (doesElementExist2(properties.getProperty("RunReport"), 5)) {
			WebElement rnrptbtn = driver.findElement(By.cssSelector(properties.getProperty("RunReport")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rnrptbtn);
			log.logLine(Testname, false, "Click on Run Report is Successful");
			waitUntilLoadElementDisappear4();
			Thread.sleep(20000);


			Set<String> handles = driver.getWindowHandles();
			String firstWinHandle = driver.getWindowHandle(); 
			handles.remove(firstWinHandle);

			boolean browserexist = handles.iterator().hasNext();
			if (browserexist) {
				String winHandle=handles.iterator().next();
				if (winHandle!=firstWinHandle){

					//Switch control to new window
					driver.switchTo().window(winHandle);
					waitUntilLoadElementDisappear4();
					driver.manage().window().maximize();

					Thread.sleep(60000);

					if (doesElementExist2(properties.getProperty("reporclient"), 5)) {	    
						WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("reporclient")));
						Highlight(optr);
						String reprtclintnme=optr.getText();
						if(reprtclintnme.equalsIgnoreCase(ClntName)){
							Thread.sleep(1000);
							log.logLine(Testname, false, "Client name in the report "+reprtclintnme+"  matches with the one selected by the user");}
						else{
							log.logLine(Testname, false, "Client name in the report  "+reprtclintnme+" match with the one created by the user");
						}}
					else{
						log.logLine(Testname, false, "Client name field doesn't exist");
						negativeCase(Testname, firstWinHandle, "", "Client name field doesn't exist");	}

					Thread.sleep(2000);

					if (doesElementExist2(properties.getProperty("reportappnme"), 5)) {	    
						WebElement optr1 = driver.findElement(By.cssSelector(properties.getProperty("reportappnme")));
						String reprtappnme=optr1.getText();
						if(reprtappnme.equalsIgnoreCase(AppName)){
							Thread.sleep(1000);
							log.logLine(Testname, false, "Client name in the report  "+reprtappnme+" matches and all the data in the report belong to single client");}
						else if(reprtappnme.contains(AppName)){
							Thread.sleep(1000);
							log.logLine(Testname, false, "Client name in the report  "+reprtappnme+"  matches and all the data in the report belong to multiple clients");}
						else {
							log.logLine(Testname, false, "Client name in the report doesn't match with the one created by the user");
						}}
					else{
						log.logLine(Testname, false, "App name field doesn't exist");
						negativeCase(Testname, firstWinHandle, "", "Client name field doesn't exist");}

					Thread.sleep(5000);
					driver.close();

					// Switching back to parent window
					driver.switchTo().window(firstWinHandle);}}}}


	private void appstatus(String Testname,String string) throws Exception, IOException {
		if (doesElementExist2(properties.getProperty("optionlst"), 5)) {
			Thread.sleep(3000);
			WebElement rnrptbtn = driver.findElement(By.cssSelector(properties.getProperty("optionlst")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rnrptbtn);
			log.logLine(Testname, false, "Click on optionlist is successful");
			Thread.sleep(2000);
			if (doesElementExist2(properties.getProperty("options"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("options")));
				for (WebElement lnk:selopts) {
					String abc=lnk.getText();
					if (lnk.getText().equals(string)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						PleasewaitDisappear();
						log.logLine(Testname, false, "Selecting the all application status");
						break;
					}
				}
			} else {
				log.logLine(Testname, true, "Selecting the all application status failed");
				driver.switchTo().defaultContent();
				throw new Exception("Selecting the all application status failed");
			}
		}
	}
	public boolean Clientuseraccesslog(String AccNo,String Testname) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		String repname= test.readColumnData("Reportname", sheetname);

		Thread.sleep(10000);
		driver.switchTo().frame("iframeContainer");
		Thread.sleep(1000);

		if (doesElementExist2(properties.getProperty("ReportTypes"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("ReportTypes")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on ReportTypes Field drop down list in Report Writer");


			if (doesElementExist2(properties.getProperty("ReportTypeoptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ReportTypeoptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(repname)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						PleasewaitDisappear();
						Thread.sleep(6000);						
						log.logLine(Testname, false, "Selecting the Report option "+repname+" from the dropdown");
						break;
					}
				}
			} else {
				log.logLine(Testname, true, "Selecting the Report option  is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Selecting the Report option  is failed");
			}
		}else {
			log.logLine(Testname, true, "Clicking on the ReportTypes is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on the ReportTypes is failed");
		}

		//selecting the "all application"from list
		if (doesElementExist2(properties.getProperty("Plantbtn"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("Plantbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(500);
			log.logLine(Testname, false, "Clicking on Select Field Dropdown List");


			if (doesElementExist2(properties.getProperty("OptionType"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("OptionType")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("All Applications")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						PleasewaitDisappear();
						log.logLine(Testname, false, "Selecting the Plant option from the dropdown");
						break;
					}
				}
			} else {
				log.logLine(Testname, true, "Selecting the Plant option from the dropdown is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Selecting the Plant option from the dropdown is failed");
			}
		}else {
			log.logLine(Testname, true, "Clicking on the Options field is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on the Options field is failed");
		}

		Thread.sleep(3000);
		log.logLine(Testname, false, "Selecting the status 'Yes' from all applications");
		appstatus(Testname,"Yes");
		Thread.sleep(3000);
		reportvalidation1(Testname);

		Thread.sleep(3000);
		log.logLine(Testname, false, "Selecting the status 'No' from all applications");
		driver.switchTo().frame("iframeContainer");
		appstatus(Testname,"No");
		reportvalidation1(Testname);

		return true;}

	private void tableValidation(String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("tablehead"), 5)) {
			WebElement rnrptbtn = driver.findElement(By.cssSelector(properties.getProperty("tablehead")));
			log.logLine(Testname, false, "Grid is displayed with the heading");
		}
		else{
			log.logLine(Testname, false, "Grid is not displayed with the heading as there might be  no data to display");
		}}




	private void reportvalidation1(String Testname) throws Exception
	{
		//Run Report
		if (doesElementExist2(properties.getProperty("RunReport"), 5)) {
			WebElement rnrptbtn = driver.findElement(By.cssSelector(properties.getProperty("RunReport")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rnrptbtn);
			log.logLine(Testname, false, "Click on Run Report is Successful");
			waitUntilLoadElementDisappear4();
			Thread.sleep(20000);


			Set<String> handles = driver.getWindowHandles();
			String firstWinHandle = driver.getWindowHandle(); 
			handles.remove(firstWinHandle);

			boolean browserexist = handles.iterator().hasNext();
			if (browserexist) {
				String winHandle=handles.iterator().next();
				if (winHandle!=firstWinHandle){

					//Switch control to new window
					driver.switchTo().window(winHandle);
					waitUntilLoadElementDisappear4();
					driver.manage().window().maximize();

					Thread.sleep(60000);

					if (doesElementExist2(properties.getProperty("reporclient"), 5)) {	    
						WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("reporclient")));
						Highlight(optr);
						String reprtclintnme=optr.getText();
						if(reprtclintnme.equalsIgnoreCase(ClntName)){
							Thread.sleep(1000);
							log.logLine(Testname, false, "Client name in the report "+reprtclintnme+"  matches with the one selected by the user");}
						else{
							log.logLine(Testname, false, "Client name in the report  "+reprtclintnme+" match with the one created by the user");
						}}
					else{
						log.logLine(Testname, false, "Client name field doesn't exist");
						negativeCase(Testname, firstWinHandle, "", "Client name field doesn't exist");	}

					Thread.sleep(2000);

					if (doesElementExist2(properties.getProperty("reportappnme"), 5)) {	    
						WebElement optr1 = driver.findElement(By.cssSelector(properties.getProperty("reportappnme")));
						String reprtappnme=optr1.getText();
						if(reprtappnme.equalsIgnoreCase(AppName)){
							Thread.sleep(1000);
							log.logLine(Testname, false, "Client name in the report  "+reprtappnme+" matches and all the data in the report belong to single client");}
						else if(reprtappnme.contains(AppName)){
							Thread.sleep(1000);
							log.logLine(Testname, false, "Client name in the report  "+reprtappnme+"  matches and all the data in the report belong to multiple clients");}
						else {
							log.logLine(Testname, false, "Client name in the report doesn't match with the one created by the user");
						}}
					else{
						log.logLine(Testname, false, "App name field doesn't exist");
						negativeCase(Testname, firstWinHandle, "", "Client name field doesn't exist");}

					tableValidation(Testname);
					Thread.sleep(5000);
					driver.close();

					// Switching back to parent window
					driver.switchTo().window(firstWinHandle);}}}}


}





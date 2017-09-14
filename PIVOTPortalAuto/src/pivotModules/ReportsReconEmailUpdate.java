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

public class ReportsReconEmailUpdate extends Page{
	public static String ClntName;
	public static String AppName;
	public static String Username="atestsuperad";

	public ReportsReconEmailUpdate(WebDriver driver, Log log) throws InvalidFormatException, IOException {
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
		ClntName = test.readColumnData("ClientName", sheetname);

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
		AppName = test.readColumnData("ApplicationName", sheetname);

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


	public boolean ReconReport(String AccNo,String Testname) throws Exception {

		Thread.sleep(5000);
		driver.switchTo().frame("iframeContainer");
		Thread.sleep(1000);

		if (doesElementExist2(properties.getProperty("ReportTypes"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("ReportTypes")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on ReportTypes Field drop down list in Report Writer");


			if (doesElementExist2(properties.getProperty("ReportTypeoptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ReportTypeoptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Activity Report")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);

						PleasewaitDisappear();
						Thread.sleep(6000);						
						log.logLine(Testname, false, "Selecting the Report option from the dropdown");
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


		//////////////// 

		DateRangevalidationBillable(Testname);

		DateRangevalidationNonBillable(Testname);

		Thread.sleep(2000);
		driver.switchTo().frame("iframeContainer");

		if (doesElementExist2(properties.getProperty("ChrBillable"), 5)) {	    
			WebElement dt = driver.findElement(By.cssSelector(properties.getProperty("ChrBillable")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", dt);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Click on Date Range Dropdown");

			if (doesElementExist2(properties.getProperty("ChrBillableOptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ChrBillableOptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("All")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);

						PleasewaitDisappear();
						Thread.sleep(5000);
						log.logLine(Testname, false, "Selecting the ChrBillableOptions as from the dropdown");
						break;
					}
				}
			} else {
				log.logLine(Testname, true, "Selecting the ChrBillableOptions from the dropdown is failed");
				throw new Exception("Selecting the ChrBillableOptions from the dropdown is failed");
			}
		}else {
			log.logLine(Testname, true, "ChrBillableOptions does not exists");
			throw new Exception("ChrBillableOptions does not exists");
		}

		if (doesElementExist(properties.getProperty("DateRange2"), 5)) {	    
			WebElement dt = driver.findElement(By.xpath(properties.getProperty("DateRange2")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", dt);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Click on Date Range Dropdown");

			if (doesElementExist2(properties.getProperty("DateRngOptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("DateRngOptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Monthly Current")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);

						PleasewaitDisappear();
						Thread.sleep(5000);
						log.logLine(Testname, false, "Selecting the Date Range option as from the dropdown");
						break;
					}
				}
			} else {
				log.logLine(Testname, true, "Selecting the Date Range option from the dropdown is failed");
				throw new Exception("Selecting the Date Range option from the dropdown is failed");
			}
		}else {
			log.logLine(Testname, true, "Date Range Option does not exists");
			throw new Exception("Date Range Option does not exists");
		}

		//Run Report
		if (doesElementExist2(properties.getProperty("RunReport"), 5)) {
			WebElement rnrptbtn = driver.findElement(By.cssSelector(properties.getProperty("RunReport")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rnrptbtn);
			log.logLine(Testname, false, "Click on Run Report is Successful");
			PleasewaitDisappear();
			waitUntilLoadElementDisappear4();
			Thread.sleep(65000);



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

					Thread.sleep(25000);

					if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetExplorer"))) {
						if (doesElementExist2(properties.getProperty("OverrideLnk"), 5)) {
							driver.get("javascript:document.getElementById('overridelink').click();");
						}
					}



					WebElement retEle = waitForElement(properties.getProperty("LoginTblHeader1"));
					log.logLine(Testname, false, "Report Name is found on the page..");

					/*
			    	if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) || (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) 
		    			driver.get("javascript:document.getElementById('overridelink').click();");
			    	}	
			    	/*
			    	Thread.sleep(10000);
			    	if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) || (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) {

			    		if (doesElementExist2(properties.getProperty("Tablevalidtn1"), 5)) {
				    		String Clnt = driver.findElement(By.cssSelector(properties.getProperty("Tablevalidtn1"))).getText();
				    		 Thread.sleep(2000);
				    		log.logLine(Testname, false, "Reading The Dats as:    "+Clnt);
				    	}else{
					    	log.logLine(Testname, true, "Reading The Data is unsuccessfull in report window");
					    	driver.close();
					    	driver.switchTo().window(firstWinHandle);
					    	throw new Exception("Reading The Data is unsuccessfull in report window");
				    	}


				    	if (doesElementExist2(properties.getProperty("Tablevalidtn2"), 5)) {
				    		List<WebElement> Dte = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn2")));
				    		 for (WebElement lnk:Dte) {
				    			 if (lnk.getText().equals(ClntName)){
				    				 Thread.sleep(1000);
				    				 log.logLine(Testname, false, "Client Name   "+lnk.getText()+  "Matches With the  "+ClntName+"   used to login for Reports");
				    				 Thread.sleep(2000);
				    				 break;
				    			 }
				    		 }
				    	 }


				    	if (doesElementExist2(properties.getProperty("Tablevalidtn1"), 5)) {
				    		List<WebElement> Prjts = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn1")));
				    		 for (WebElement lnk:Prjts) {
				    			 if (lnk.getText().equals("Project(s): " )){
				    				 Thread.sleep(1000);
				    				 log.logLine(Testname, false, "Reading The Data as:    "+lnk.getText());
				    				 Thread.sleep(2000);
				    				 break;
				    			 }
				    		 }
				    	 }

				    	if (doesElementExist2(properties.getProperty("Tablevalidtn2"), 5)) {
				    		List<WebElement> Dte = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn2")));
				    		 for (WebElement lnk:Dte) {
				    			 if (lnk.getText().contains(AppName)){
				    				 Thread.sleep(1000);
				    				 log.logLine(Testname, false, "Application Name   "+lnk.getText()+"  Matches With the  "+AppName+"   used to login for Reports");
				    				 Thread.sleep(2000);
				    				 break;
				    			 }
				    		 }
				    	 }

		 }else if ((Initialization.Browser.equals("FF")) || (Initialization.Browser.equals("ff")) || (Initialization.Browser.equals("firefox")) || (Initialization.Browser.equals("Firefox")) || (Initialization.Browser.equals("FIREFOX"))) {

			 if (doesElementExist2(properties.getProperty("Tbvldn1"), 5)) {
		    		List<WebElement> Prjts = driver.findElements(By.cssSelector(properties.getProperty("Tbvldn1")));
		    		 for (WebElement lnk:Prjts) {
		    			 if (lnk.getText().equals("Client: " )){
		    				 Thread.sleep(1000);
		    				 log.logLine(Testname, false, "Reading The Data as:    "+lnk.getText());
		    				 Thread.sleep(2000);
		    				 break;
		    			 }
		    		 }
		    	 }


		    	if (doesElementExist2(properties.getProperty("Tbvldn2"), 5)) {
		    		List<WebElement> Dte = driver.findElements(By.cssSelector(properties.getProperty("Tbvldn2")));
		    		 for (WebElement lnk:Dte) {
		    			 if (lnk.getText().equals(ClntName)){
		    				 Thread.sleep(1000);
		    				 log.logLine(Testname, false, "Client Name "  +lnk.getText()+  "Matches With the  "+ClntName+"  used to login for Reports");
		    				 Thread.sleep(2000);
		    				 break;
		    			 }
		    		 }
		    	 }


		    	if (doesElementExist2(properties.getProperty("Tbvldn1"), 5)) {
		    		List<WebElement> Prjts = driver.findElements(By.cssSelector(properties.getProperty("Tbvldn1")));
		    		 for (WebElement lnk:Prjts) {
		    			 if (lnk.getText().equals("Project(s): " )){
		    				 Thread.sleep(1000);
		    				 log.logLine(Testname, false, "Reading The Data as:    "+lnk.getText());
		    				 Thread.sleep(2000);
		    				 break;
		    			 }
		    		 }
		    	 }

		    	if (doesElementExist2(properties.getProperty("Tbvldn2"), 5)) {
		    		List<WebElement> Dte = driver.findElements(By.cssSelector(properties.getProperty("Tbvldn2")));
		    		 for (WebElement lnk:Dte) {
		    			 if (lnk.getText().contains(AppName)){
		    				 Thread.sleep(1000);
		    				 log.logLine(Testname, false, "Application Name  "+lnk.getText()+"  Matches With the  "+AppName+"  used to login for Reports");
		    				 Thread.sleep(2000);
		    				 break;
		    			 }
		    		 }
		    	 }

			} else if ((Initialization.Browser.equals("Chrome")) || (Initialization.Browser.equals("chrome")) || (Initialization.Browser.equals("CHROME"))) {
					 */
					if (doesElementExist2(properties.getProperty("Tablevalidtn2"), 5)) {
						List<WebElement> Dte = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn2")));
						for (WebElement lnk:Dte) {
							if (lnk.getText().equals(ClntName)){
								Thread.sleep(2000);
								log.logLine(Testname, false, "Client Name   "+lnk.getText()+"  Matches With the  "+ClntName+"   used to login for Reports");
								Thread.sleep(2000);
								break;
							}
						}
					}


					if (doesElementExist2(properties.getProperty("Tablevalidtn1"), 5)) {
						List<WebElement> Dte = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn2")));
						for (WebElement lnk:Dte) {
							if (lnk.getText().contains(AppName)){
								Thread.sleep(2000);
								log.logLine(Testname, false, "Application Name   "+lnk.getText()+"  Matches With the  "+AppName+"   used to login for Reports");
								Thread.sleep(2000);
								break;
							}
						}
					}




					//Run Date
					/*if (doesElementExist2(properties.getProperty("Rundte"), 5)) {
		    		String Rndte = driver.findElement(By.cssSelector(properties.getProperty("Rundte"))).getText();
		    		Thread.sleep(2000);
		    		log.logLine(Testname, false, "Reading The Run Date as:    "+Rndte);
		    		Thread.sleep(2000);
		    	}else{
			    	log.logLine(Testname, true, "Reading The Run Date is unsuccessfull");
			    	driver.close();
			    	driver.switchTo().window(firstWinHandle);
			    	throw new Exception("Reading The Data is unsuccessfull in report window");
		    	}


		    	// Date Range
		    	if (doesElementExist2(properties.getProperty("Dterng"), 5)) {
		    		String Dtrng = driver.findElement(By.cssSelector(properties.getProperty("Dterng"))).getText();
		    		 Thread.sleep(2000);
		    		log.logLine(Testname, false, "Reading The Date Range as:    "+Dtrng);
		    		Thread.sleep(2000);
		    	}else{
			    	log.logLine(Testname, true, "Reading The Date Range is unsuccessfull");
					//throw new Exception("Reading the date is unSuccessful");
		    	}*/

					//Logins Table headers validation

					if (doesElementExist2(properties.getProperty("LoginTblHeader1"), 5)) {
						String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("LoginTblHeader1"))).getText();
						Thread.sleep(2000);
						log.logLine(Testname, false, "Reading The 1st Column data of Login table as:    "+Emlsmry1);
						Thread.sleep(2000);
					}else{
						log.logLine(Testname, true, "Reading The 1st Column data of Email Summary table is unsuccessfull");
						driver.close();
						driver.switchTo().window(firstWinHandle);
						throw new Exception("Reading The 1st Column data of Email Summary table is unsuccessfull");
					}

					if (doesElementExist2(properties.getProperty("LoginTblHeader2"), 5)) {
						String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("LoginTblHeader2"))).getText();
						Thread.sleep(2000);
						log.logLine(Testname, false, "Reading The 2nd Column data of Login table as:    "+Emlsmry1);
						Thread.sleep(2000);
					}else{
						log.logLine(Testname, true, "Reading The 2nd Column data of Email Summary table is unsuccessfull");
						driver.close();
						driver.switchTo().window(firstWinHandle);
						throw new Exception("Reading The 2nd Column data of Email Summary table is unsuccessfull");
					}

					if (doesElementExist2(properties.getProperty("LoginTblHeader3"), 5)) {
						String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("LoginTblHeader3"))).getText();
						Thread.sleep(2000);
						log.logLine(Testname, false, "Reading The 3rd Column data of Login table as:    "+Emlsmry1);
						Thread.sleep(2000);
					}else{
						log.logLine(Testname, true, "Reading The 3rd Column data of Email Summary table is unsuccessfull");
						driver.close();
						driver.switchTo().window(firstWinHandle);
						throw new Exception("Reading The 3rd Column data of Email Summary table is unsuccessfull");
					}


					if (doesElementExist2(properties.getProperty("LoginTblHeader4"), 5)) {
						String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("LoginTblHeader4"))).getText();
						Thread.sleep(2000);
						log.logLine(Testname, false, "Reading The 4th Column data of Login table as:    "+Emlsmry1);
						Thread.sleep(2000);
					}else{
						log.logLine(Testname, true, "Reading The 4th Column data of Email Summary table is unsuccessfull");
						driver.close();
						driver.switchTo().window(firstWinHandle);
						throw new Exception("Reading The 4th Column data of Email Summary table is unsuccessfull");
					}

					if (doesElementExist2(properties.getProperty("LoginTblHeader5"), 5)) {
						String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("LoginTblHeader5"))).getText();
						Thread.sleep(2000);
						log.logLine(Testname, false, "Reading The 5th Column data of Login table as:    "+Emlsmry1);
						Thread.sleep(2000);
					}else{
						log.logLine(Testname, true, "Reading The 5th Column data of Email Summary table is unsuccessfull");
						driver.close();
						driver.switchTo().window(firstWinHandle);
						throw new Exception("Reading The 5th Column data of Email Summary table is unsuccessfull");
					}

					if (doesElementExist2(properties.getProperty("LoginTblHeader6"), 5)) {
						String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("LoginTblHeader6"))).getText();
						Thread.sleep(2000);
						log.logLine(Testname, false, "Reading The 6th Column data of Login table as:    "+Emlsmry1);
						Thread.sleep(2000);
					}else{
						log.logLine(Testname, true, "Reading The 6th Column data of Email Summary table is unsuccessfull");
						driver.close();
						driver.switchTo().window(firstWinHandle);
						throw new Exception("Reading The 6th Column data of Email Summary table is unsuccessfull");
					}


					/*//Data Loads Table headers validation

		    	if (doesElementExist2(properties.getProperty("DataLoadsTblHeader1"), 5)) {
		    		String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("DataLoadsTblHeader1"))).getText();
		    		Thread.sleep(2000);
		    		log.logLine(Testname, false, "Reading The 1st Column data of Data loads table as:    "+Emlsmry1);
		    		Thread.sleep(2000);
		    	}else{
			    	log.logLine(Testname, true, "Reading The 1st Column data of Data loads table is unsuccessfull");
			    	driver.close();
			    	driver.switchTo().window(firstWinHandle);
					//throw new Exception("Reading The 1st Column data of Data loads table is unsuccessfull");
		    	}

		    	if (doesElementExist2(properties.getProperty("DataLoadsTblHeader2"), 5)) {
		    		String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("DataLoadsTblHeader2"))).getText();
		    		Thread.sleep(2000);
		    		log.logLine(Testname, false, "Reading The 2nd Column data of Data loads table as:    "+Emlsmry1);
		    		Thread.sleep(2000);
		    	}else{
			    	log.logLine(Testname, true, "Reading The 2nd Column data of Data loads table is unsuccessfull");
			    	driver.close();
			    	driver.switchTo().window(firstWinHandle);
					//throw new Exception("Reading The 2nd Column data of Data loads table is unsuccessfull");
		    	}

		    	if (doesElementExist2(properties.getProperty("DataLoadsTblHeader3"), 5)) {
		    		String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("DataLoadsTblHeader3"))).getText();
		    		Thread.sleep(2000);
		    		log.logLine(Testname, false, "Reading The 3rd Column data of Data loads table as:    "+Emlsmry1);
		    		Thread.sleep(2000);
		    	}else{
			    	log.logLine(Testname, true, "Reading The 3rd Column data of Data loads table is unsuccessfull");
			    	driver.close();
			    	driver.switchTo().window(firstWinHandle);
					//throw new Exception("Reading The 3rd Column data of Data loads table is unsuccessfull");
		    	}

		    	if (doesElementExist2(properties.getProperty("DataLoadsTblHeader4"), 5)) {
		    		String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("DataLoadsTblHeader4"))).getText();
		    		Thread.sleep(2000);
		    		log.logLine(Testname, false, "Reading The 4th Column data of Data loads table as:    "+Emlsmry1);
		    		Thread.sleep(2000);
		    	}else{
			    	log.logLine(Testname, true, "Reading The 4th Column data of Data loads table is unsuccessfull");
			    	driver.close();
			    	driver.switchTo().window(firstWinHandle);
					//throw new Exception("Reading The 4th Column data of Data loads table is unsuccessfull");
		    	}

		    	if (doesElementExist2(properties.getProperty("DataLoadsTblHeader5"), 5)) {
		    		String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("DataLoadsTblHeader5"))).getText();
		    		Thread.sleep(2000);
		    		log.logLine(Testname, false, "Reading The 5th Column data of Data loads table as:    "+Emlsmry1);
		    		Thread.sleep(2000);
		    	}else{
			    	log.logLine(Testname, true, "Reading The 5th Column data of Data loads table is unsuccessfull");
			    	driver.close();
			    	driver.switchTo().window(firstWinHandle);
					//throw new Exception("Reading The 5th Column data of Data loads table is unsuccessfull");
		    	}

		    	if (doesElementExist2(properties.getProperty("DataLoadsTblHeader6"), 5)) {
		    		String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("DataLoadsTblHeader6"))).getText();
		    		Thread.sleep(2000);
		    		log.logLine(Testname, false, "Reading The 6th Column data of Data loads table as:    "+Emlsmry1);
		    		Thread.sleep(2000);
		    	}else{
			    	log.logLine(Testname, true, "Reading The 6th Column data of Data loads table is unsuccessfull");
			    	driver.close();
			    	driver.switchTo().window(firstWinHandle);
					//throw new Exception("Reading The 6th Column data of Data loads table is unsuccessfull");
		    	}

		    	if (doesElementExist2(properties.getProperty("DataLoadsTblHeader7"), 5)) {
		    		String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("DataLoadsTblHeader7"))).getText();
		    		Thread.sleep(2000);
		    		log.logLine(Testname, false, "Reading The 7th Column data of Data loads table as:    "+Emlsmry1);
		    		Thread.sleep(2000);
		    	}else{
			    	log.logLine(Testname, true, "Reading The 7th Column data of Data loads table is unsuccessfull");
			    	driver.close();
			    	driver.switchTo().window(firstWinHandle);
					//throw new Exception("Reading The 7th Column data of Data loads table is unsuccessfull");
		    	}

		    	if (doesElementExist2(properties.getProperty("DataLoadsTblHeader8"), 5)) {
		    		String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("DataLoadsTblHeader8"))).getText();
		    		Thread.sleep(2000);
		    		log.logLine(Testname, false, "Reading The 8th Column data of Data loads table as:    "+Emlsmry1);
		    		Thread.sleep(2000);
		    	}else{
			    	log.logLine(Testname, true, "Reading The 8th Column data of Data loads table is unsuccessfull");
			    	driver.close();
			    	driver.switchTo().window(firstWinHandle);
					//throw new Exception("Reading The 8th Column data of Data loads table is unsuccessfull");
		    	}

		    	//Retrievals table headers validation			    	

		    	if (doesElementExist2(properties.getProperty("RetrievalsTblHeader1"), 5)) {
		    		String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("RetrievalsTblHeader1"))).getText();
		    		Thread.sleep(3000);
		    		log.logLine(Testname, false, "Reading The 1st Column data of Retrievals table as:    "+Emlsmry1);
		    	}else{
			    	log.logLine(Testname, true, "Reading The 1st Column data of Retrievals table is unsuccessfull");
					throw new Exception("Reading The 1st Column data of Retrievals table is unsuccessfull");
		    	}

		    	if (doesElementExist2(properties.getProperty("RetrievalsTblHeader2"), 5)) {
		    		String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("RetrievalsTblHeader2"))).getText();
		    		Thread.sleep(2000);
		    		log.logLine(Testname, false, "Reading The 2nd Column data of Retrievals table as:    "+Emlsmry1);
		    	}else{
			    	log.logLine(Testname, true, "Reading The 2nd Column data of Retrievals table is unsuccessfull");
					throw new Exception("Reading The 2nd Column data of Retrievals table is unsuccessfull");
		    	}

		    	if (doesElementExist2(properties.getProperty("RetrievalsTblHeader3"), 5)) {
		    		String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("RetrievalsTblHeader3"))).getText();
		    		Thread.sleep(2000);
		    		log.logLine(Testname, false, "Reading The 3rd Column data of Retrievals table as:    "+Emlsmry1);
		    	}else{
			    	log.logLine(Testname, true, "Reading The 3rd Column data of Retrievals table is unsuccessfull");
					throw new Exception("Reading The 3rd Column data of Retrievals table is unsuccessfull");
		    	}

		    	if (doesElementExist2(properties.getProperty("RetrievalsTblHeader4"), 5)) {
		    		String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("RetrievalsTblHeader4"))).getText();
		    		Thread.sleep(2000);
		    		log.logLine(Testname, false, "Reading The 4th Column data of Retrievals table as:    "+Emlsmry1);
		    	}else{
			    	log.logLine(Testname, true, "Reading The 4th Column data of Retrievals table is unsuccessfull");
					throw new Exception("Reading The 4th Column data of Retrievals table is unsuccessfull");
		    	}

		    	if (doesElementExist2(properties.getProperty("RetrievalsTblHeader5"), 5)) {
		    		String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("RetrievalsTblHeader5"))).getText();
		    		Thread.sleep(2000);
		    		log.logLine(Testname, false, "Reading The 5th Column data of Retrievals table as:    "+Emlsmry1);
		    	}else{
			    	log.logLine(Testname, true, "Reading The 5th Column data of Retrievals table is unsuccessfull");
					throw new Exception("Reading The 5th Column data of Retrievals table is unsuccessfull");
		    	}

		    	if (doesElementExist2(properties.getProperty("RetrievalsTblHeader6"), 5)) {
		    		String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("RetrievalsTblHeader6"))).getText();
		    		Thread.sleep(2000);
		    		log.logLine(Testname, false, "Reading The 6th Column data of Retrievals table as:    "+Emlsmry1);
		    	}else{
			    	log.logLine(Testname, true, "Reading The 6th Column data of Retrievals table is unsuccessfull");
					throw new Exception("Reading The 6th Column data of Retrievals table is unsuccessfull");
		    	}

		    	if (doesElementExist2(properties.getProperty("RetrievalsTblHeader7"), 5)) {
		    		String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("RetrievalsTblHeader7"))).getText();
		    		Thread.sleep(2000);
		    		log.logLine(Testname, false, "Reading The 7th Column data of Retrievals table as:    "+Emlsmry1);
		    	}else{
			    	log.logLine(Testname, true, "Reading The 7th Column data of Retrievals table is unsuccessfull");
					throw new Exception("Reading The 7th Column data of Retrievals table is unsuccessfull");
		    	}

		    	if (doesElementExist2(properties.getProperty("RetrievalsTblHeader8"), 5)) {
		    		String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("RetrievalsTblHeader8"))).getText();
		    		Thread.sleep(2000);
		    		log.logLine(Testname, false, "Reading The 8th Column data of Retrievals table as:    "+Emlsmry1);
		    	}else{
			    	log.logLine(Testname, true, "Reading The 8th Column data of Retrievals table is unsuccessfull");
					throw new Exception("Reading The 8th Column data of Retrievals table is unsuccessfull");
		    	}

		    	//Activity table headers validation
		    	if (doesElementExist2(properties.getProperty("ActyHistoryTblHeader1"), 5)) {
		    		String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("ActyHistoryTblHeader1"))).getText();
		    		Thread.sleep(2000);
		    		log.logLine(Testname, false, "Reading The 1st Column data of Activity History table as:    "+Emlsmry1);
		    	}else{
			    	log.logLine(Testname, true, "Reading The 1st Column data of Activity History table is unsuccessfull");
					throw new Exception("Reading The 1st Column data of Activity History table is unsuccessfull");
		    	}

		    	if (doesElementExist2(properties.getProperty("ActyHistoryTblHeader2"), 5)) {
		    		String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("ActyHistoryTblHeader2"))).getText();
		    		Thread.sleep(2000);
		    		log.logLine(Testname, false, "Reading The 2nd Column data of Activity History table as:    "+Emlsmry1);
		    	}else{
			    	log.logLine(Testname, true, "Reading The 2nd Column data of Activity History table is unsuccessfull");
					throw new Exception("Reading The 2nd Column data of Activity History table is unsuccessfull");
		    	}

		    	if (doesElementExist2(properties.getProperty("ActyHistoryTblHeader3"), 5)) {
		    		String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("ActyHistoryTblHeader3"))).getText();
		    		Thread.sleep(2000);
		    		log.logLine(Testname, false, "Reading The 3rd Column data of Activity History table as:    "+Emlsmry1);
		    	}else{
			    	log.logLine(Testname, true, "Reading The 3rd Column data of Activity History table is unsuccessfull");
					throw new Exception("Reading The 3rd Column data of Activity History table is unsuccessfull");
		    	}

		    	if (doesElementExist2(properties.getProperty("ActyHistoryTblHeader4"), 5)) {
		    		String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("ActyHistoryTblHeader4"))).getText();
		    		Thread.sleep(2000);
		    		log.logLine(Testname, false, "Reading The 4th Column data of Activity History table as:    "+Emlsmry1);
		    	}else{
			    	log.logLine(Testname, true, "Reading The 4th Column data of Activity History table is unsuccessfull");
					throw new Exception("Reading The 4th Column data of Activity History table is unsuccessfull");
		    	}

		    	if (doesElementExist2(properties.getProperty("ActyHistoryTblHeader5"), 5)) {
		    		String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("ActyHistoryTblHeader5"))).getText();
		    		Thread.sleep(2000);
		    		log.logLine(Testname, false, "Reading The 5th Column data of Activity History table as:    "+Emlsmry1);
		    	}else{
			    	log.logLine(Testname, true, "Reading The 5th Column data of Activity History table is unsuccessfull");
					throw new Exception("Reading The 5th Column data of Activity History table is unsuccessfull");
		    	}

		    	if (doesElementExist2(properties.getProperty("ActyHistoryTblHeader6"), 5)) {
		    		String Emlsmry1 = driver.findElement(By.cssSelector(properties.getProperty("ActyHistoryTblHeader6"))).getText();
		    		Thread.sleep(2000);
		    		log.logLine(Testname, false, "Reading The 6th Column data of Activity History table as:    "+Emlsmry1);
		    	}else{
			    	log.logLine(Testname, true, "Reading The 6th Column data of Activity History table is unsuccessfull");
					throw new Exception("Reading The 6th Column data of Activity History table is unsuccessfull");
		    	}*/



					Thread.sleep(5000);
					driver.close();

					// Switching back to parent window
					driver.switchTo().window(firstWinHandle);
				}
			}
		}

		return true;
	}

	public boolean DocumentRetrievalActivity(String AccNo,String Testname) throws Exception {

		Thread.sleep(5000);
		driver.switchTo().frame("iframeContainer");
		Thread.sleep(1000);

		if (doesElementExist2(properties.getProperty("ReportTypes"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("ReportTypes")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on ReportTypes Field drop down list in Report Writer");


			if (doesElementExist2(properties.getProperty("ReportTypeoptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ReportTypeoptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Document Retrieval Activity")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);

						PleasewaitDisappear();
						Thread.sleep(6000);						
						log.logLine(Testname, false, "Selecting the Report option from the dropdown");
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


		if (doesElementExist2(properties.getProperty("DateRange1"), 5)) {	    
			WebElement dt = driver.findElement(By.cssSelector(properties.getProperty("DateRange1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", dt);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Click on Date Range Dropdown");

			if (doesElementExist2(properties.getProperty("DateRngOptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("DateRngOptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Monthly Current")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);

						PleasewaitDisappear();
						Thread.sleep(5000);
						log.logLine(Testname, false, "Selecting the Date Range option as from the dropdown");
						break;
					}
				}
			} else {
				log.logLine(Testname, true, "Selecting the Date Range option from the dropdown is failed");
				throw new Exception("Selecting the Date Range option from the dropdown is failed");
			}
		}else {
			log.logLine(Testname, true, "Date Range Option does not exists");
			throw new Exception("Date Range Option does not exists");
		}
		
		
		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Addbtn"), 5)) {
			WebElement addbtn = driver.findElement(By.cssSelector(properties.getProperty("Addbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", addbtn);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Click on Add button");
		}else {
			log.logLine(Testname, true, "Add button does not exists");
		}
		
		
		/*if (doesElementExist2(properties.getProperty("Useridallows"), 5)) {	    
			WebElement dt = driver.findElement(By.cssSelector(properties.getProperty("Useridallows")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", dt);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Click on Date Range Dropdown");

			if (doesElementExist2(properties.getProperty("Useridallowsoptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("DateRngOptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("User ID (allows *)")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(5000);
						log.logLine(Testname, false, "Selecting the User option as "+lnk.getText()+" from the dropdown");
						break;
					}
				}
			} else {
				log.logLine(Testname, true, "Selecting the User option from the dropdown is failed");
				throw new Exception("Selecting the User option from the dropdown is failed");
			}
		}else {
			log.logLine(Testname, true, "User option does not exists");
	//		throw new Exception("User option does not exists");
		}
		
		
		if (doesElementExist2(properties.getProperty("Useridtext"), 5)) {
			WebElement usernme = driver.findElement(By.cssSelector(properties.getProperty("Useridtext")));
			usernme.clear();
			usernme.sendKeys(Username);
			Thread.sleep(5000);
    		log.logLine(Testname, false, "Entering the user name as "+Username);
    	}else{
	    	log.logLine(Testname, true, "Entering the user name is unsuccessfull");
			throw new Exception("Entering the user name is unsuccessfull");
    	}
		
		*/

		//Run Report
		if (doesElementExist2(properties.getProperty("RunReport"), 5)) {
			WebElement rnrptbtn = driver.findElement(By.cssSelector(properties.getProperty("RunReport")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rnrptbtn);
			log.logLine(Testname, false, "Click on Run Report is Successful");
			PleasewaitDisappear();
			waitUntilLoadElementDisappear4();
			Thread.sleep(65000);



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

					Thread.sleep(25000);

					if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetExplorer"))) {
						if (doesElementExist2(properties.getProperty("OverrideLnk"), 5)) {
							driver.get("javascript:document.getElementById('overridelink').click();");
						}
					}



					WebElement retEle = waitForElement(properties.getProperty("LoginTblHeader1"));
					log.logLine(Testname, false, "Report Name is found on the page..");


					if (doesElementExist2(properties.getProperty("Tablevalidtn2"), 5)) {
						List<WebElement> Dte = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn2")));
						for (WebElement lnk:Dte) {
							if (lnk.getText().equals(ClntName)){
								Thread.sleep(2000);
								log.logLine(Testname, false, "Client Name   "+lnk.getText()+"  Matches With the  "+ClntName+"   used to login for Reports");
								Thread.sleep(2000);
								break;
							}
						}
					}


					if (doesElementExist2(properties.getProperty("Tablevalidtn1"), 5)) {
						List<WebElement> Dte = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn2")));
						for (WebElement lnk:Dte) {
							if (lnk.getText().contains(AppName)){
								Thread.sleep(2000);
								log.logLine(Testname, false, "Application Name   "+lnk.getText()+"  Matches With the  "+AppName+"   used to login for Reports");
								Thread.sleep(2000);
								break;
							}
						}
					}

					if (doesElementExist2(properties.getProperty("Usernamevlidtn"), 5)) {
						List<WebElement> Dte = driver.findElements(By.cssSelector(properties.getProperty("Usernamevlidtn")));
						for (WebElement lnk:Dte) {
							if (lnk.getText().contains(AppName)){
								Thread.sleep(2000);
								log.logLine(Testname, false, "User Name  "+lnk.getText()+"  Matches With the  "+AppName+"  used as User Name for Validation");
								Thread.sleep(2000);
								break;
							}
						}
					}





					Thread.sleep(5000);
					driver.close();

					// Switching back to parent window
					driver.switchTo().window(firstWinHandle);
				}
			}
		}

		return true;
	}

	public boolean DateRangevalidationBillable(String Testname) throws Exception { 


		if (doesElementExist2(properties.getProperty("ChrBillable"), 5)) {	    
			WebElement dt = driver.findElement(By.cssSelector(properties.getProperty("ChrBillable")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", dt);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Click on Date Range Dropdown");

			if (doesElementExist2(properties.getProperty("ChrBillableOptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ChrBillableOptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Billable")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);

						PleasewaitDisappear();
						Thread.sleep(5000);
						log.logLine(Testname, false, "Selecting the ChrBillableOptions as from the dropdown");
						break;
					}
				}
			} else {
				log.logLine(Testname, true, "Selecting the ChrBillableOptions from the dropdown is failed");
				throw new Exception("Selecting the ChrBillableOptions from the dropdown is failed");
			}
		}else {
			log.logLine(Testname, true, "ChrBillableOptions does not exists");
			throw new Exception("ChrBillableOptions does not exists");
		}

		if (doesElementExist(properties.getProperty("DateRange2"), 5)) {	    
			WebElement dt = driver.findElement(By.xpath(properties.getProperty("DateRange2")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", dt);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Click on Date Range Dropdown");

			if (doesElementExist2(properties.getProperty("DateRngOptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("DateRngOptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Custom")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);

						PleasewaitDisappear();
						Thread.sleep(5000);
						log.logLine(Testname, false, "Selecting the Date Range option as from the dropdown");
						break;
					}
				}
			} else {
				log.logLine(Testname, true, "Selecting the Date Range option from the dropdown is failed");
				//throw new Exception("Selecting the Date Range option from the dropdown is failed");
			}
		}else {
			log.logLine(Testname, true, "Date Range Option does not exists");
			//throw new Exception("Date Range Option does not exists");
		}

		if (doesElementExist2(properties.getProperty("Startdate"), 5)) {	    
			WebElement dt = driver.findElement(By.cssSelector(properties.getProperty("Startdate")));
			dt.sendKeys("8/1/2016");
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entering the start date");
		}

		if (doesElementExist2(properties.getProperty("EndDate"), 5)) {	    
			WebElement dt = driver.findElement(By.cssSelector(properties.getProperty("EndDate")));
			dt.sendKeys("9/6/2017");
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entering the start date");
		}



		//Run Report
		if (doesElementExist2(properties.getProperty("RunReport"), 5)) {
			WebElement rnrptbtn = driver.findElement(By.cssSelector(properties.getProperty("RunReport")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rnrptbtn);
			log.logLine(Testname, false, "Click on Run Report is Successful");
			PleasewaitDisappear();
			waitUntilLoadElementDisappear4();
			Thread.sleep(65000);



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

					Thread.sleep(25000);

					if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetExplorer"))) {
						if (doesElementExist2(properties.getProperty("OverrideLnk"), 5)) {
							driver.get("javascript:document.getElementById('overridelink').click();");
						}
					}



					WebElement retEle = waitForElement(properties.getProperty("LoginTblHeader1"));
					log.logLine(Testname, false, "Report Name is found on the page..");

					if (doesElementExist2(properties.getProperty("Dterng"), 5)) {
						String Rndte = driver.findElement(By.cssSelector(properties.getProperty("Dterng"))).getText();
						Thread.sleep(2000);
						log.logLine(Testname, false, "Reading The Run Date as:    "+Rndte);
						Thread.sleep(2000);
					}else{
						log.logLine(Testname, true, "Reading The Run Date is unsuccessfull");
						/*    	driver.close();
					    	driver.switchTo().window(firstWinHandle);
					    	throw new Exception("Reading The Data is unsuccessfull in report window");*/
					}	


					if (doesElementExist2(properties.getProperty("Billable"), 5)) {
						String Rndte = driver.findElement(By.cssSelector(properties.getProperty("Billable"))).getText();
						Thread.sleep(2000);
						log.logLine(Testname, false, "Reading The ChrBillableOptions as:    "+Rndte);
						Thread.sleep(2000);
					}else{
						log.logLine(Testname, true, "Reading The ChrBillableOptions is unsuccessfull");
						/*driver.close();
					    	driver.switchTo().window(firstWinHandle);
					    	throw new Exception("Reading The Data is unsuccessfull in report window");*/
					}	




					Thread.sleep(5000);
					driver.close();

					// Switching back to parent window
					driver.switchTo().window(firstWinHandle);
				}
			}
		}


		return true;
	}



	public boolean DateRangevalidationNonBillable(String Testname) throws Exception { 

		Thread.sleep(2000);
		driver.switchTo().frame("iframeContainer");

		if (doesElementExist2(properties.getProperty("ChrBillable"), 5)) {	    
			WebElement dt = driver.findElement(By.cssSelector(properties.getProperty("ChrBillable")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", dt);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Click on Date Range Dropdown");

			if (doesElementExist2(properties.getProperty("ChrBillableOptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ChrBillableOptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Non Billable")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);

						PleasewaitDisappear();
						Thread.sleep(5000);
						log.logLine(Testname, false, "Selecting the ChrBillableOptions as from the dropdown");
						break;
					}
				}
			} else {
				log.logLine(Testname, true, "Selecting the ChrBillableOptions from the dropdown is failed");
				throw new Exception("Selecting the ChrBillableOptions from the dropdown is failed");
			}
		}else {
			log.logLine(Testname, true, "ChrBillableOptions does not exists");
			throw new Exception("ChrBillableOptions does not exists");
		}

		if (doesElementExist(properties.getProperty("DateRange2"), 5)) {	    
			WebElement dt = driver.findElement(By.xpath(properties.getProperty("DateRange2")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", dt);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Click on Date Range Dropdown");

			if (doesElementExist2(properties.getProperty("DateRngOptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("DateRngOptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Custom")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);

						PleasewaitDisappear();
						Thread.sleep(5000);
						log.logLine(Testname, false, "Selecting the Date Range option as from the dropdown");
						break;
					}
				}
			} else {
				log.logLine(Testname, true, "Selecting the Date Range option from the dropdown is failed");
				throw new Exception("Selecting the Date Range option from the dropdown is failed");
			}
		}else {
			log.logLine(Testname, true, "Date Range Option does not exists");
			throw new Exception("Date Range Option does not exists");
		}

		if (doesElementExist2(properties.getProperty("Startdate"), 5)) {	    
			WebElement dt = driver.findElement(By.cssSelector(properties.getProperty("Startdate")));
			dt.clear();
			dt.sendKeys("8/1/2016");
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entering the start date");
		}

		if (doesElementExist2(properties.getProperty("EndDate"), 5)) {	    
			WebElement dt = driver.findElement(By.cssSelector(properties.getProperty("EndDate")));
			dt.clear();
			dt.sendKeys("9/6/2017");
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entering the start date");
		}


		//Run Report
		if (doesElementExist2(properties.getProperty("RunReport"), 5)) {
			WebElement rnrptbtn = driver.findElement(By.cssSelector(properties.getProperty("RunReport")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rnrptbtn);
			log.logLine(Testname, false, "Click on Run Report is Successful");
			PleasewaitDisappear();
			waitUntilLoadElementDisappear4();
			Thread.sleep(65000);



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

					Thread.sleep(25000);

					if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetExplorer"))) {
						if (doesElementExist2(properties.getProperty("OverrideLnk"), 5)) {
							driver.get("javascript:document.getElementById('overridelink').click();");
						}
					}



					WebElement retEle = waitForElement(properties.getProperty("LoginTblHeader1"));
					log.logLine(Testname, false, "Report Name is found on the page..");

					if (doesElementExist2(properties.getProperty("Dterng"), 5)) {
						String Rndte = driver.findElement(By.cssSelector(properties.getProperty("Dterng"))).getText();
						Thread.sleep(2000);
						log.logLine(Testname, false, "Reading The Run Date as:    "+Rndte);
						Thread.sleep(2000);
					}else{
						log.logLine(Testname, true, "Reading The Run Date is unsuccessfull");
						/*driver.close();
				    	driver.switchTo().window(firstWinHandle);
				    	throw new Exception("Reading The Data is unsuccessfull in report window");*/
					}	


					if (doesElementExist2(properties.getProperty("Billable"), 5)) {
						String Rndte = driver.findElement(By.cssSelector(properties.getProperty("Billable"))).getText();
						Thread.sleep(2000);
						log.logLine(Testname, false, "Reading The ChrBillableOptions as:    "+Rndte);
						Thread.sleep(2000);
					}else{
						log.logLine(Testname, true, "Reading The ChrBillableOptions is unsuccessfull");
						/*	driver.close();
				    	driver.switchTo().window(firstWinHandle);
				    	throw new Exception("Reading The Data is unsuccessfull in report window");*/
					}	

					Thread.sleep(5000);
					driver.close();

					// Switching back to parent window
					driver.switchTo().window(firstWinHandle);
				}
			}
		}


		return true;
	}

}


//Checking for the Plant and order_num fields and passing the value in the fields

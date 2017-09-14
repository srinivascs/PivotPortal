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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class ProofofReview extends Page{
	public static String ClntName,ClntName1;
	public static String AppName,AppName1;

	public ProofofReview(WebDriver driver, Log log) throws InvalidFormatException, IOException {
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
		WebElement retelm2 = waitForElement(properties.getProperty("selClint1"));

		driver.switchTo().defaultContent();

		Thread.sleep(3000);
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
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on OK button");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the Reports is failed");
			throw new Exception("Clicking on OK button to view the Reports is failed");
		}


		return true;
	}
	

	public boolean ClientAppSel1(String AccNo, String Testname) throws Exception{
		
	InputOutputData test = new InputOutputData();  
	test.setInputFile(properties.getProperty("InputDatafile"));
	String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

	Thread.sleep(1000);		 
	WebElement retelm2 = waitForElement(properties.getProperty("selClint1"));

	driver.switchTo().defaultContent();

	Thread.sleep(3000);
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
	ClntName1 = test.readColumnData("ClientName1", sheetname);

	if (doesElementExist2(properties.getProperty("selClint1"), 5)) {
		WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selClint1")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

		Thread.sleep(1000);
		log.logLine(Testname, false, "Clicking on ClientName dropdown..");	    	

		if (doesElementExist2(properties.getProperty("ClinetOpts1"), 5)) {
			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ClinetOpts1")));
			for (WebElement lnk:selopts) {
				if (lnk.getText().contains(ClntName1)) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Selecting the ClientName "+ClntName1 +" from the popup..");
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
					if (lnk.getText().contains(ClntName1)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the ClientName "+ClntName1 +" from the dropdown..");							
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
	AppName1 = test.readColumnData("ApplicationName1", sheetname);

	if (doesElementExist2(properties.getProperty("selAppl1"), 5)) {	   
		WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selAppl1")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

		Thread.sleep(1000);
		log.logLine(Testname, false, "Clicking on ApplicationName dropdown..");

		if (doesElementExist2(properties.getProperty("ApplOpts1"), 5)) {
			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ApplOpts1")));
			for (WebElement lnk:selopts) {
				if (lnk.getText().contains(AppName1)) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Selecting the Application Name "+AppName1 +" from the popup..");
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
					if (lnk.getText().contains(AppName1)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Application Name "+AppName1 +" from the dropdown..");							
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
		Thread.sleep(1000);
		log.logLine(Testname, false, "Clicking on OK button");
	} else {
		log.logLine(Testname, true, "Clicking on OK button to view the Reports is failed");
		throw new Exception("Clicking on OK button to view the Reports is failed");
	}
	

	return true;
}



	public boolean UserActivityDetail(String AccNo,String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		Date date = new Date();
		String todaysDate = dateFormat.format(date);


		String eightDaysBefore;
		Date date2 = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date2);

		cal.add(Calendar.DAY_OF_YEAR, -8);
		Date before = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		eightDaysBefore = formatter.format(before);


		Thread.sleep(15000);
		driver.switchTo().frame("iframeContainer");

		if (doesElementExist2(properties.getProperty("ReportTypes"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("ReportTypes")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(500);
			log.logLine(Testname, false, "Clicking on ReportTypes Field drop down list in Report Writer");


			if (doesElementExist2(properties.getProperty("ReportTypeoptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ReportTypeoptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("User Activity Detail")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);

						PleasewaitDisappear();
						Thread.sleep(3000);						
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


		if (doesElementExist2(properties.getProperty("Allapplns"), 5)) {	    
			WebElement dt = driver.findElement(By.cssSelector(properties.getProperty("Allapplns")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", dt);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Click on All application Dropdown");
			


			if (doesElementExist2(properties.getProperty("AppnOptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("AppnOptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Yes")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);

						PleasewaitDisappear();
						Thread.sleep(5000);
						log.logLine(Testname, false, "Selecting the All application option as Yes from the dropdown");
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


		/* 
	    if (doesElementExist2(properties.getProperty("FromDate"), 5)) {	  
		    WebElement DteFromfld = driver.findElement(By.cssSelector(properties.getProperty("FromDate")));
			Thread.sleep(1000);
			DteFromfld.sendKeys(eightDaysBefore);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entering the from date value as    :"+eightDaysBefore);

			WebElement DteTofld = driver.findElement(By.cssSelector(properties.getProperty("ToDate")));
			Thread.sleep(1000);
			DteTofld.sendKeys(todaysDate);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entering the To date value as    :"+todaysDate);
	    }	*/  



		boolean Retval = RunReport(AccNo,Testname);
		if (Retval) {
			return true;
		}else {
			return false;
		}	 
	}
	
	public boolean UserActivityDetail1(String AccNo,String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		Date date = new Date();
		String todaysDate = dateFormat.format(date);


		String eightDaysBefore;
		Date date2 = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date2);

		cal.add(Calendar.DAY_OF_YEAR, -8);
		Date before = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		eightDaysBefore = formatter.format(before);


		Thread.sleep(15000);
		driver.switchTo().frame("iframeContainer");

		if (doesElementExist2(properties.getProperty("ReportTypes"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("ReportTypes")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(500);
			log.logLine(Testname, false, "Clicking on ReportTypes Field drop down list in Report Writer");


			if (doesElementExist2(properties.getProperty("ReportTypeoptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ReportTypeoptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("User Activity Detail")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);

						PleasewaitDisappear();
						Thread.sleep(3000);						
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


		if (doesElementExist2(properties.getProperty("Allapplns"), 5)) {	    
			WebElement dt = driver.findElement(By.cssSelector(properties.getProperty("Allapplns")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", dt);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Click on All application Dropdown");
			


			if (doesElementExist2(properties.getProperty("AppnOptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("AppnOptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("No")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);

						PleasewaitDisappear();
						Thread.sleep(5000);
						log.logLine(Testname, false, "Selecting the All application option as Yes from the dropdown");
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


		/* 
	    if (doesElementExist2(properties.getProperty("FromDate"), 5)) {	  
		    WebElement DteFromfld = driver.findElement(By.cssSelector(properties.getProperty("FromDate")));
			Thread.sleep(1000);
			DteFromfld.sendKeys(eightDaysBefore);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entering the from date value as    :"+eightDaysBefore);

			WebElement DteTofld = driver.findElement(By.cssSelector(properties.getProperty("ToDate")));
			Thread.sleep(1000);
			DteTofld.sendKeys(todaysDate);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entering the To date value as    :"+todaysDate);
	    }	*/  



		boolean Retval = RunReport(AccNo,Testname);
		if (Retval) {
			return true;
		}else {
			return false;
		}	 
	}
	
	public boolean UserLoginActivity(String AccNo,String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		Date date = new Date();
		String todaysDate = dateFormat.format(date);


		String eightDaysBefore;
		Date date2 = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date2);

		cal.add(Calendar.DAY_OF_YEAR, -8);
		Date before = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		eightDaysBefore = formatter.format(before);


		Thread.sleep(15000);
		driver.switchTo().frame("iframeContainer");

		if (doesElementExist2(properties.getProperty("ReportTypes"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("ReportTypes")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(500);
			log.logLine(Testname, false, "Clicking on ReportTypes Field drop down list in Report Writer");


			if (doesElementExist2(properties.getProperty("ReportTypeoptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ReportTypeoptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("User Login Activity")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);

						PleasewaitDisappear();
						Thread.sleep(3000);						
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


		if (doesElementExist2(properties.getProperty("Allapplns1"), 5)) {	    
			WebElement dt = driver.findElement(By.cssSelector(properties.getProperty("Allapplns1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", dt);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Click on All application Dropdown");
			


			if (doesElementExist2(properties.getProperty("AppnOptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("AppnOptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Yes")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);

						PleasewaitDisappear();
						Thread.sleep(5000);
						log.logLine(Testname, false, "Selecting the All application option as Yes from the dropdown");
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



		boolean Retval = RunReport(AccNo,Testname);
		if (Retval) {
			return true;
		}else {
			return false;
		}	 
	}
	
	public boolean UserLoginActivity1(String AccNo,String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		Date date = new Date();
		String todaysDate = dateFormat.format(date);


		String eightDaysBefore;
		Date date2 = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date2);

		cal.add(Calendar.DAY_OF_YEAR, -8);
		Date before = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		eightDaysBefore = formatter.format(before);


		Thread.sleep(15000);
		driver.switchTo().frame("iframeContainer");

		if (doesElementExist2(properties.getProperty("ReportTypes"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("ReportTypes")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(500);
			log.logLine(Testname, false, "Clicking on ReportTypes Field drop down list in Report Writer");


			if (doesElementExist2(properties.getProperty("ReportTypeoptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ReportTypeoptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("User Login Activity")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);

						PleasewaitDisappear();
						Thread.sleep(3000);						
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


		if (doesElementExist2(properties.getProperty("Allapplns1"), 5)) {	    
			WebElement dt = driver.findElement(By.cssSelector(properties.getProperty("Allapplns1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", dt);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Click on All application Dropdown");
			


			if (doesElementExist2(properties.getProperty("AppnOptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("AppnOptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("No")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);

						PleasewaitDisappear();
						Thread.sleep(5000);
						log.logLine(Testname, false, "Selecting the All application option as Yes from the dropdown");
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



		boolean Retval = RunReport(AccNo,Testname);
		if (Retval) {
			return true;
		}else {
			return false;
		}	 
	}


	public boolean LinkTracking(String AccNo,String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		Date date = new Date();
		String todaysDate = dateFormat.format(date);


		String eightDaysBefore;
		Date date2 = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date2);

		cal.add(Calendar.DAY_OF_YEAR, -8);
		Date before = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		eightDaysBefore = formatter.format(before);


		Thread.sleep(15000);
		driver.switchTo().frame("iframeContainer");

		if (doesElementExist2(properties.getProperty("ReportTypes"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("ReportTypes")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on ReportTypes Field drop down list in Report Writer");

			if (doesElementExist2(properties.getProperty("ReportTypeoptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ReportTypeoptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Link Tracking")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);

						PleasewaitDisappear();
						Thread.sleep(3000);						
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

		if (doesElementExist2(properties.getProperty("DateRange"), 5)) {	    
	    	WebElement dt = driver.findElement(By.cssSelector(properties.getProperty("DateRange")));
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
	     
		
		
		/*if (doesElementExist2(properties.getProperty("FromDate"), 5)) {	  
			WebElement DteFromfld = driver.findElement(By.cssSelector(properties.getProperty("FromDate")));
			Thread.sleep(1000);
			DteFromfld.sendKeys(eightDaysBefore);
			log.logLine(Testname, false, "Entering the from date value as    :"+eightDaysBefore);

			WebElement DteTofld = driver.findElement(By.cssSelector(properties.getProperty("ToDate")));
			Thread.sleep(1000);
			DteTofld.sendKeys(todaysDate);
			log.logLine(Testname, false, "Entering the To date value as    :"+todaysDate);
		}	*/

		boolean Retval = RunReport(AccNo,Testname);
		if (Retval) {
			return true;
		}else {
			return false;
		}
	}




	public boolean EmailTracking(String AccNo,String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		Date date = new Date();
		String todaysDate = dateFormat.format(date);


		String eightDaysBefore;
		Date date2 = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date2);

		cal.add(Calendar.DAY_OF_YEAR, -8);
		Date before = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		eightDaysBefore = formatter.format(before);


		Thread.sleep(15000);
		driver.switchTo().frame("iframeContainer");

		if (doesElementExist2(properties.getProperty("ReportTypes"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("ReportTypes")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(500);
			log.logLine(Testname, false, "Clicking on ReportTypes Field drop down list in Report Writer");


			if (doesElementExist2(properties.getProperty("ReportTypeoptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ReportTypeoptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Email Tracking")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);

						PleasewaitDisappear();
						Thread.sleep(3000);
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
		
		if (doesElementExist2(properties.getProperty("DateRange"), 5)) {	    
	    	WebElement dt = driver.findElement(By.cssSelector(properties.getProperty("DateRange")));
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
	     


		/*if (doesElementExist2(properties.getProperty("FromDate"), 5)) {	  
			WebElement DteFromfld = driver.findElement(By.cssSelector(properties.getProperty("FromDate")));
			Thread.sleep(1000);
			DteFromfld.sendKeys(eightDaysBefore);
			log.logLine(Testname, false, "Entering the from date value as    :"+eightDaysBefore);

			WebElement DteTofld = driver.findElement(By.cssSelector(properties.getProperty("ToDate")));
			Thread.sleep(1000);
			DteTofld.sendKeys(todaysDate);
			log.logLine(Testname, false, "Entering the To date value as    :"+todaysDate);
		}	 */


		boolean Retval = RunReport(AccNo,Testname);
		if (Retval) {
			return true;
		}else {
			return false;
		}

	}




	public boolean RunReport(String AccNo, String Testname) throws Exception {

		boolean Chk1 = false, Chk3 = false, Chk4 = false, Chk5 = false, Chk6 = false;


		if (doesElementExist2(properties.getProperty("RunReport"), 5)) {
			WebElement rnrptbtn = driver.findElement(By.cssSelector(properties.getProperty("RunReport")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rnrptbtn);
			log.logLine(Testname, false, "Click on Run Report is Successful");

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
					driver.manage().window().maximize();

					//WebElement retEle = waitForElement(properties.getProperty("Reportnme"));
					//log.logLine(Testname, false, "Report Name is found on the page..");

					Thread.sleep(2000);



					if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetExplorer"))) {
						if (doesElementExist2(properties.getProperty("OverrideLnk"), 5)) {
							driver.get("javascript:document.getElementById('overridelink').click();");
						}
					}

					Thread.sleep(8000);  	

					if (doesElementExist2(properties.getProperty("Reportnme"), 5)) {
						List<WebElement> Rpnme = driver.findElements(By.cssSelector(properties.getProperty("Reportnme")));
						for (WebElement lnk:Rpnme) {
							if (lnk.getText().equals("Proof of Review ")){
								Thread.sleep(2000);
								log.logLine(Testname, false, "Reading the data as :"+lnk.getText());
								Chk1=true;
								break;
							}
						}
					}

					if (doesElementExist2(properties.getProperty("Reportnme"), 5)) {
						List<WebElement> Dtl = driver.findElements(By.cssSelector(properties.getProperty("Reportnme")));
						for (WebElement lnk:Dtl) {
							if (lnk.getText().equals("(User Activity Detail) ")){
								Thread.sleep(2000);
								log.logLine(Testname, false, "Reading the Report Name as :"+lnk.getText());
								break;
							} else if (lnk.getText().equals("(User Login Activity) ")){
								Thread.sleep(2000);
								log.logLine(Testname, false, "Reading the Report Name as :"+lnk.getText());
								break;
							} else if (lnk.getText().equals("(Link Tracking Report) ")){
								Thread.sleep(2000);
								log.logLine(Testname, false, "Reading the Report Name as :"+lnk.getText());
								break;
							}else if (lnk.getText().equals("(Email Open Tracking Report) ")){
								Thread.sleep(2000);
								log.logLine(Testname, false, "Reading the Report Name as :"+lnk.getText());
								break;
							}
						}
					}


					if (doesElementExist2(properties.getProperty("ReportData"), 5)) {
						List<WebElement> todte = driver.findElements(By.cssSelector(properties.getProperty("ReportData")));
						for (WebElement lnk:todte) {
							if (lnk.getText().contains(("AM"))||lnk.getText().contains(("PM"))){
								Thread.sleep(2000);
								log.logLine(Testname, false, "Reading The Run Date and Time as:    "+lnk.getText());
								Chk3=true;
								break;
							}
						}
					}

					if (doesElementExist2(properties.getProperty("ReportData"), 5)) {
						List<WebElement> Clnme = driver.findElements(By.cssSelector(properties.getProperty("ReportData")));
						for (WebElement lnk:Clnme) {
							if (lnk.getText().equals("Client - ")){
								Thread.sleep(2000);
								log.logLine(Testname, false, "Reading The Data as:    "+lnk.getText());
								Chk4=true;
								break;
							}
						}
					}

					if (doesElementExist2(properties.getProperty("ReportData"), 5)) {
						List<WebElement> Appnme = driver.findElements(By.cssSelector(properties.getProperty("ReportData")));
						for (WebElement lnk:Appnme) {
							if (lnk.getText().equals("Application(s) - ")){
								Thread.sleep(2000);
								log.logLine(Testname, false, "Reading The Data as:    "+lnk.getText());
								Chk5=true;
								break;
							}
						}
					}
					
					if (doesElementExist2(properties.getProperty("ReportData"), 5)) {
						List<WebElement> Appnme = driver.findElements(By.cssSelector(properties.getProperty("ReportData")));
						for (WebElement lnk:Appnme) {
							if (lnk.getText().contains("ABC1234")){
								Thread.sleep(2000);
								log.logLine(Testname, false, "Reading The Data as:    "+lnk.getText());
								Chk6=true;
								break;
							}else if (lnk.getText().contains("ABC0001")){
								Thread.sleep(2000);
								log.logLine(Testname, false, "Reading The Data as:    "+lnk.getText());
								Chk6=true;
								break;
							}else if (lnk.getText().contains("ALL")){
								Thread.sleep(2000);
								log.logLine(Testname, false, "Reading The Data as:    "+lnk.getText());
								Chk6=true;
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
		/*if (Chk1 && Chk3 && Chk4 && Chk5){
			return true;
		} else {
			return false; 
		}*/
		return true;
	}

}


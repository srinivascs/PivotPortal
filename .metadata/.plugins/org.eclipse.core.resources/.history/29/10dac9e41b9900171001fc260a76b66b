package pivotModules;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

public class ArchieveFileMgmtbeta extends Page{

	private static final Object[] Loaded = null;
	private static final boolean ascending = false;
	private static final boolean descending = false;
	public static String Fnlzipnme;


	public ArchieveFileMgmtbeta(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	} 
	@Override
	protected void load() {}
	@Override

	protected void isLoaded() throws Error {}

	public boolean ArchiveClientAppSel(String AccNo, String Testname) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		String Array[]=Initialization.ArchiveZIP1.split(".zip");
		Fnlzipnme=Array[0].trim();

		Thread.sleep(4000);
		driver.switchTo().defaultContent();
		WebElement retelm2 = waitForElement(properties.getProperty("selClint1"));


		if (doesElementExist2(properties.getProperty("Archives"), 5)) {
			WebElement arclnk = driver.findElement(By.cssSelector(properties.getProperty("Archives")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", arclnk);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Navigation to Archives page is successfull");
		} else {
			log.logLine(Testname, true, "Clicking on Archives menu is failed");
			throw new Exception("Clicking on Archives menu is failed");
		}    


		//Selecting the Client and Application name from popup
		Thread.sleep(1000);
		boolean CliSelected = false;
		String ClntName = test.readColumnData("ClientName", sheetname);

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
		String AppName = test.readColumnData("ApplicationName", sheetname);

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
			log.logLine(Testname, false, "Clicking on OK button to view the Archives");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the Archives is failed");
			throw new Exception("Clicking on OK button to view the Archives is failed");
		}


		//Verify User permission
		if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSUPER")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSITE")) 
				|| (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDCLIENT")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDUSER"))) {

			if (doesElementExist2(properties.getProperty("FileMngBtn"), 5)) {

				log.logLine(Testname, false, "Permission Verified: RRD Super, RRD Site, RRD Client & RRD User's  have access to File Management in Archives");

			} else {

				log.logLine(Testname, true, "Access denied - RRD Super, RRD Site, RRD Client & RRD User's does not have access to File Management in Archives");				
				throw new Exception("Access denied - RRD Super, RRD Site, RRD Client & RRD User's does not have access to File Management in Archives");
			}
		} else if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTUSER")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTADMIN"))) {

			if (doesElementExist2(properties.getProperty("FileMngBtn"), 5)) {

				log.logLine(Testname, true, "Client Admin & Client User should not have access to File Management in Archives");
				throw new Exception("Client Admin & Client User should not have access to File Management in Archives");

			} else {

				log.logLine(Testname, false, "Permission Verified: Client Admin & Client User does not have permission to access to File Management in Archives");
				return false; 
			}

		}



		if (doesElementExist2(properties.getProperty("FileMngBtn"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("FileMngBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);	    	 
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Click on File Management button is successfull");
		} else {
			log.logLine(Testname, true, "File Management menu is not displayed");
			driver.switchTo().defaultContent();
			throw new Exception("File Management menu is not displayed");
		}

		return true;
	}	



	public boolean Filemanagement(String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	    

		Thread.sleep(10000);
		driver.switchTo().frame("iframeContainer");	  
		PleasewaitDisappear();

		String[] Sort = new String[150];
		int length = Sort.length;
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		Date date = new Date();
		String todaysDate = dateFormat.format(date);


		if (doesElementExist(properties.getProperty("Page"), 5)) {	 
			WebElement optr = driver.findElement(By.xpath(".//*[@id='archive-sections-file-mgmt-grid']/div/span[1]/span[1]/span/span[2]/span"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			log.logLine(Testname, false, "Click on Page Selection button");
			Thread.sleep(1000);

			List<WebElement> editbtn = driver.findElements(By.cssSelector(properties.getProperty("Select")));
			for (WebElement lnk:editbtn) {
				if (lnk.getText().equals("50")){
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Selecting the Option 50 Per Page from Drop down");
					break;
				} 
			}
		}

		//Sorting the Column data for Loaded Column  
		/*
 		String row = "tr";
 		List<WebElement> DataCnt= driver.findElements(By.xpath("//div[@id='archive-sections-file-mgmt-grid']/table/tbody/tr"));

 		if(doesElementExist2(properties.getProperty("Loaded"), 5)){
 			Actions action = new Actions(driver);
 			List<WebElement> editbtn = driver.findElements(By.cssSelector(properties.getProperty("Loaded")));
 			for (WebElement lnk:editbtn) {
 				if (lnk.getText().equals("Loaded")){
 					action.click(lnk).perform();
 					log.logLine(Testname, false, "Loaded option exists..");
 					break;
 				} else {
					log.logLine(Testname, true, "Loaded option not exists..");
					driver.switchTo().defaultContent();
					throw new Exception("Loaded option not exists..");
					}
 				}
 			}

 		if(doesElementExist2(properties.getProperty("Loaded"), 5)){
 			for(int i = 0; i < DataCnt.size(); i++) {
 				Sort[i] = driver.findElement(By.cssSelector("div[id='archive-sections-file-mgmt-grid'] table tbody "+row+" td a[title='Reload']")).getText();
 				row = row + "+tr";
 				}
 			log.logLine(Testname, false, "Iterating through the Rows..");
 		}

 		for (int i = 0; i < length ; i++) {
 			if (Sort[i].compareTo(Sort[i+1])<0) {
 				log.logLine(Testname, false, "Dates are in Ascending order");
 				break;
 			}else if (Sort[i].compareTo(Sort[i])<0) {
 				log.logLine(Testname, false, "Dates are in Descending order");
 				break;
 				}
 			}

	    if (doesElementExist2(properties.getProperty("Arrow"), 5)) {
	    	WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Arrow")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
	    	log.logLine(Testname, false, "Clicking on Arrow button is successfull");
	    	Thread.sleep(1000);
	    } else {
	    	log.logLine(Testname, true, "Clicking on Arrow button is failed");
	    	driver.switchTo().defaultContent();
	    	throw new Exception("Clicking on Arrow button is failed");
	    	}

	    String row1 = "tr";
	    List<WebElement> DataCnt1= driver.findElements(By.xpath("//div[@id='archive-sections-file-mgmt-grid']/table/tbody/tr"));
	    if(doesElementExist2(properties.getProperty("Loaded"), 5)){
	    	for(int i = 0; i < DataCnt1.size(); i++) {
	    		Sort[i] = driver.findElement(By.cssSelector("div[id='archive-sections-file-mgmt-grid'] table tbody "+row1+" td a[title='Reload']")).getText();
	    		row1 = row1+ "+tr";
	    		}
	    	log.logLine(Testname, false, "Iterating through the Rows..");
	    	}

		for (int i = 0; i < length ; i++) {
			if (Sort[i].compareTo(Sort[i+1])>0) {
				log.logLine(Testname, false, "Dates are in descending order");
				break;
			}else if (Sort[i].compareTo(Sort[i])<0) {
				log.logLine(Testname, false, "Dates are in Ascending order");
				break;
				}
			}

		 */

		//Sorting the Column data for Viewable Column  
		String row2 = "tr";
		List<WebElement> DataCnt2= driver.findElements(By.xpath("//div[@id='archive-sections-file-mgmt-grid']/table/tbody/tr"));
		if(doesElementExist2(properties.getProperty("Viewable"), 5)){
			Actions action = new Actions(driver);
			List<WebElement> editbtn = driver.findElements(By.cssSelector(properties.getProperty("Viewable")));
			for (WebElement lnk:editbtn) {
				if (lnk.getText().equals("Viewable")){
					action.click(lnk).perform();
					Thread.sleep(6000);
					log.logLine(Testname, false, "Viewable option exists..");
					break;
				} else {
					log.logLine(Testname, true, "Viewable option not exists..");
					driver.switchTo().defaultContent();
					throw new Exception("Viewable option not exists..");
				}
			}
		}



		if (doesElementExist2(properties.getProperty("ViewTrue"), 5)) {
			Actions action = new Actions(driver);
			List<WebElement> tre = driver.findElements(By.cssSelector(properties.getProperty("ViewTrue")));
			for (WebElement lnk:tre) {
				if (lnk.getText().equals("True")){
					// action.click(lnk).perform();
					log.logLine(Testname, false, "Viewable First Row is True before sorting");
					break;
				} else if (lnk.getText().equals("False")){
					// action.click(lnk).perform();
					log.logLine(Testname, false, "Viewable First Row is False before sorting");
					break;
				}
			}
		}

		if(doesElementExist2(properties.getProperty("Viewable"), 5)){
			WebElement bill = driver.findElement(By.cssSelector(properties.getProperty("Viewable")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", bill);
			log.logLine(Testname, false, "Clicking on Viewable Option..");

		}

		if (doesElementExist2(properties.getProperty("ViewTrue"), 5)) {
			Actions action = new Actions(driver);
			List<WebElement> tre = driver.findElements(By.cssSelector(properties.getProperty("ViewTrue")));
			for (WebElement lnk:tre) {
				if (lnk.getText().equals("True")){
					// action.click(lnk).perform();
					log.logLine(Testname, false, "Viewable Option Sorted in True Series Follwed By False series after sorting");
					break;
				} else if (lnk.getText().equals("False")){
					// action.click(lnk).perform();
					log.logLine(Testname, false, "Viewable Option Sorted in False Series Follwed By True series after sorting");
					break;
				}
			}
		}

		//Sorting the Column data for Billable Column  
		String row3 = "tr";
		List<WebElement> DataCnt3= driver.findElements(By.xpath("//div[@id='archive-sections-file-mgmt-grid']/table/tbody/tr"));
		if(doesElementExist2(properties.getProperty("Billable"), 5)){
			Actions action = new Actions(driver);
			List<WebElement> editbtn = driver.findElements(By.cssSelector(properties.getProperty("Billable")));
			for (WebElement lnk:editbtn) {
				if (lnk.getText().equals("Billable")){
					action.click(lnk).perform();
					Thread.sleep(6000);
					log.logLine(Testname, false, "Billable option exists..");
					break;
				} else {
					log.logLine(Testname, true, "Billable option not exists..");
					driver.switchTo().defaultContent();
					throw new Exception("Billable option not exists..");
				}
			}
		}

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("BillableTrue"), 5)) {
			Actions action = new Actions(driver);
			List<WebElement> tre = driver.findElements(By.cssSelector(properties.getProperty("BillableTrue")));
			for (WebElement lnk:tre) {
				if (lnk.getText().equals("True")){
					// action.click(lnk).perform();
					log.logLine(Testname, false, "Billable First Row is True before sorting");
					break;
				} else if (lnk.getText().equals("False")){
					// action.click(lnk).perform();
					log.logLine(Testname, false, "Billable First Row is False before sorting");
					break;
				}
			}
		}

		Thread.sleep(2000);
		if(doesElementExist2(properties.getProperty("Billable"), 5)){
			WebElement bill = driver.findElement(By.cssSelector(properties.getProperty("Billable")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", bill);
			log.logLine(Testname, false, "Clicking on Billable Option..");
		}

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("BillableTrue"), 5)) {
			Actions action = new Actions(driver);
			List<WebElement> tre = driver.findElements(By.cssSelector(properties.getProperty("BillableTrue")));
			for (WebElement lnk:tre) {
				if (lnk.getText().equals("True")){
					// action.click(lnk).perform();
					log.logLine(Testname, false, "Billable Option Sorted in True Series Follwed By False series after sorting");
					break;
				} else if (lnk.getText().equals("False")){
					// action.click(lnk).perform();
					log.logLine(Testname, false, "Billable Option Sorted in False Series Follwed By True series after sorting");
					break;
				}
			}
		}


	

		//Click On Advance search Button
		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Click on Advance Search button is successfull");
		} else {
			log.logLine(Testname, true, "Click on Advance Search button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Advance Search button is failed");
		}
		
		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("FromDate"), 5)) {
            String frmdte = driver.findElement(By.cssSelector(properties.getProperty("FromDate"))).getAttribute("value");
            Thread.sleep(2000);
            if(todaysDate.equals(frmdte)){
                log.logLine(Testname, false, "From Date field <<<< "+frmdte+" >>>> set to and Matches with today's date <<<< "+todaysDate+" >>>> ");

            } else {
                log.logLine(Testname, true, "From Date does not matches with todays date ");
                driver.switchTo().defaultContent();
            }
        } else {
            log.logLine(Testname, true, "From date field does not exists");
            driver.switchTo().defaultContent();
            throw new Exception("From date field does not exists");
        }
        
        if (doesElementExist2(properties.getProperty("ToDate"), 5)) {
            String todte = driver.findElement(By.cssSelector(properties.getProperty("ToDate"))).getAttribute("value");
            Thread.sleep(2000);
            if(todaysDate.equals(todte)){
                log.logLine(Testname, false, "To Date field <<<< "+todte+" >>>> set to and Matches with today's date <<<< "+todaysDate+" >>>> ");

            } else {
                log.logLine(Testname, true, "To Date does not matches with todays date ");
                driver.switchTo().defaultContent();
            }

        } else {
            log.logLine(Testname, true, "To date field does not exists");
            driver.switchTo().defaultContent();
            throw new Exception("To date field does not exists");
        }



		long timenow = System.currentTimeMillis();
		long testime = timenow - Initialization.startTime;
		int totalTime =(int) ((testime/(1000*60)));	 

		if (18 > totalTime) {
			int WaitTime = 18 - totalTime; 
			log.logLine(Testname, false, "Going to wait for "+WaitTime +"minutes, please wait...");
			Thread.sleep(WaitTime*1000*60);		 
		}

		/*
		 Thread.sleep(2000);
		 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		 Date date = new Date();
		 String todaysDate = dateFormat.format(date);


		 if (doesElementExist2(properties.getProperty("FromDate"), 5)) {
			 WebElement selcalendbtn = driver.findElement(By.cssSelector(properties.getProperty("FromDate")));
			 //((JavascriptExecutor) driver).executeScript("arguments[0].click()", selcalendbtn);
			 selcalendbtn.clear();
			 selcalendbtn.sendKeys(todaysDate);
			 log.logLine(Testname, false, "Selecting Today's date from the Calender  "+todaysDate);
		} else {
		  	 log.logLine(Testname, true, "Selecting Today's date from the Calender is failed");
			 driver.switchTo().defaultContent();
			 throw new Exception("Selecting Today's date from the Calender is failed");
		}		
		 */

		PleasewaitDisappear();
		if (doesElementExist2(properties.getProperty("Fld"), 5)) {
			WebElement fld = driver.findElement(By.cssSelector(properties.getProperty("Fld"))); 			 
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", fld);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on Lable Field is Successfull");
			Actions action=new Actions(driver);
			List<WebElement> cdl = driver.findElements(By.cssSelector(properties.getProperty("Zipfilename")));
			for (WebElement lnk:cdl){
				if(lnk.getText().equals("ZIP File Name")){
					Thread.sleep(1000);
					action.click(lnk).perform();
					Thread.sleep(2000);
					log.logLine(Testname, false, "Selecting the ZIP File Name option from the Lable field");
					break;
				}
			}
		}

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("Addbtn"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Addbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Add button is successfull");
		} else {
			log.logLine(Testname, true, "Clicking on Add button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Add button is failed");
		}

		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("SecndFld"), 5)) {
			WebElement fld = driver.findElement(By.xpath(properties.getProperty("SecndFld"))); 			 
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", fld);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on Lable Field is Successfull");
			Actions action=new Actions(driver);
			List<WebElement> cdl = driver.findElements(By.cssSelector(properties.getProperty("Zipfilename")));
			for (WebElement lnk:cdl){
				if(lnk.getText().equals("ZIP File Name")){
					Thread.sleep(1000);
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);		     
					Thread.sleep(2000);
					log.logLine(Testname, false, "Selecting the ZIP File Name option from the Lable field");
					break;
				}
			}
		}	

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Alertmsg"), 5)) {	    
			String msg = driver.findElement(By.cssSelector(properties.getProperty("Alertmsg"))).getText();		   
			log.logLine(Testname, false, "Reading the pop up message as "+ msg);
		} else {
			log.logLine(Testname, true, "Reading the pop up message is failed");

		}

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("Alertbtn"), 5)) {	    
			WebElement clkadv = driver.findElement(By.cssSelector(properties.getProperty("Alertbtn")));		   
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);		     
			Thread.sleep(5000);
			log.logLine(Testname, false, "Click ok button for pop up message");
		} else {
			log.logLine(Testname, true, "Click ok button for pop up message is failed");

		}	 

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("Clearbtn"), 5)) {	    
			WebElement clkadv = driver.findElement(By.cssSelector(properties.getProperty("Clearbtn")));		   
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);		     
			Thread.sleep(5000);
			log.logLine(Testname, false, "Click ok Clear button for pop up message");
		} else {
			log.logLine(Testname, true, "Click ok Clear button for pop up message is failed");

		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Fld"), 5)) {
			WebElement fld = driver.findElement(By.cssSelector(properties.getProperty("Fld"))); 			 
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", fld);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on Lable Field is Successfull");
			Actions action=new Actions(driver);
			List<WebElement> cdl = driver.findElements(By.cssSelector(properties.getProperty("Zipfilename")));
			for (WebElement lnk:cdl){
				if(lnk.getText().equals("ZIP File Name")){
					Thread.sleep(1000);
					action.click(lnk).perform();
					Thread.sleep(2000);
					log.logLine(Testname, false, "Selecting the ZIP File Name option from the Lable field");
					break;
				}
			}
		}

		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("Txtfld1"), 5)) {
			WebElement Txt = driver.findElement(By.xpath(properties.getProperty("Txtfld1")));
			Txt.click();
			Txt.clear();
			Txt.sendKeys(Fnlzipnme);
			log.logLine(Testname, false, "The Enterd value in text field is " +Fnlzipnme);		 
		}else{
			log.logLine(Testname, true,"Unable to enter the text in text field");
			throw new Exception("Unable to  enter the text in text field");
		}

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button is successfull");
		} else {
			log.logLine(Testname, true, "Clicking on Search button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Search button is failed");
		}

		PleasewaitDisappear();

		Thread.sleep(2000);		 
		if (doesElementExist2(properties.getProperty("ViewTrue"), 5)) {
			Actions action = new Actions(driver);
			List<WebElement> tre = driver.findElements(By.cssSelector(properties.getProperty("ViewTrue")));
			for (WebElement lnk:tre) {
				if (lnk.getText().equals("True")){
					// action.click(lnk).perform();
					log.logLine(Testname, false, "Confirm whether Viewable option is True/False ---> And the Option is TRUE..");
					break;
				} else if (lnk.getText().equals("False")){
					// action.click(lnk).perform();
					log.logLine(Testname, false, "Confirm whether Viewable option is True/False ---> And the Option is FALSE..");
					break;
				}
			}
		}

		//Clicking on ChooseAction Button and Selecting Viewable/Non Viewable option
		Thread.sleep(1000);
		if (doesElementExist2(properties.getProperty("Chooseactn"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Chooseactn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			log.logLine(Testname, false, "Clicking on Choose action button is successfull");
		} else {
			log.logLine(Testname, true, "Clicking on Choose action button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Choose action button is failed");
		}

		Thread.sleep(2000);
		if(doesElementExist2(properties.getProperty("Selmakenonviewablebtn"), 5)){
			Actions action = new Actions(driver);
			List<WebElement> nonview = driver.findElements(By.cssSelector(properties.getProperty("Selmakenonviewablebtn")));
			for (WebElement lnk:nonview) {
				if (lnk.getText().equals("Make non-viewable")){
					action.click(lnk).perform();
					log.logLine(Testname, false, "Selecting on Make non-viewable option under choose action is successfull..");
					break;
				}else if(lnk.getText().equals("Make viewable")){
					Actions actions = new Actions(driver);
					actions.click(lnk).perform();
					log.logLine(Testname, false, "Selecting on Make viewable option under choose action is successfull..");
					break;
				}
			}
		}


		if (doesElementExist2(properties.getProperty("Alrtok"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Alrtok")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			log.logLine(Testname, false, "Clicking on Ok button is successfull");
			PleasewaitDisappear();
		} else {
			log.logLine(Testname, true, "Clicking on Ok button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Ok button is failed");
		}


		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("ViewTrue"), 5)) {
			Actions action = new Actions(driver);
			List<WebElement> tre = driver.findElements(By.cssSelector(properties.getProperty("ViewTrue")));
			for (WebElement lnk:tre) {
				if (lnk.getText().equals("True")){
					// action.click(lnk).perform();
					log.logLine(Testname, false, "Confirm whether Viewable option is True/False---> And the Option is TRUE..");
					break;
				}
				else if (lnk.getText().equals("False")){
					// action.click(lnk).perform();
					log.logLine(Testname, false, "Confirm whether Viewable option is True/False---> And the Option is FALSE..");
					break;
				}
			}
		}



		//Clicking on ChooseAction Button and Selecting Billable/Non Billable option
		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("BillableTrue"), 5)) {
			Actions action = new Actions(driver);
			List<WebElement> tre = driver.findElements(By.cssSelector(properties.getProperty("BillableTrue")));
			for (WebElement lnk:tre) {
				if (lnk.getText().equals("True")){
					// action.click(lnk).perform();
					log.logLine(Testname, false, "Confirm whether Billable option is True/False---> And the Option is TRUE..");
					break;
				}
				else if (lnk.getText().equals("False")){
					// action.click(lnk).perform();
					log.logLine(Testname, false, "Confirm whether Billable option is True/False---> And the Option is FALSE..");
					break;
				}
			}
		}


		Thread.sleep(1000);
		if (doesElementExist2(properties.getProperty("Chooseactn"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Chooseactn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			log.logLine(Testname, false, "Clicking on Choose action button is successfull");
		} else {
			log.logLine(Testname, true, "Clicking on Choose action button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Choose action button is failed");
		}

		Thread.sleep(2000);
		if(doesElementExist2(properties.getProperty("Selmakebillablebtn"), 5)){
			Actions action = new Actions(driver);
			List<WebElement> nonview = driver.findElements(By.cssSelector(properties.getProperty("Selmakebillablebtn")));
			for (WebElement lnk:nonview) {
				if (lnk.getText().equals("Make non-billable")){
					action.click(lnk).perform();
					log.logLine(Testname, false, "Selecting on Make non-billable option under choose action is successfull..");
					break;
				}else if(lnk.getText().equals("Make billable")){
					Actions actions = new Actions(driver);
					actions.click(lnk).perform();
					log.logLine(Testname, false, "Selecting on Make billable option under choose action is successfull..");
					break;
				}
			}
		}


		if (doesElementExist2(properties.getProperty("Alrtok"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Alrtok")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			log.logLine(Testname, false, "Clicking on Ok button is successfull");
			PleasewaitDisappear();
		} else {
			log.logLine(Testname, true, "Clicking on Ok button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Ok button is failed");
		}

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("BillableTrue"), 5)) {
			Actions action = new Actions(driver);
			List<WebElement> tre = driver.findElements(By.cssSelector(properties.getProperty("BillableTrue")));
			for (WebElement lnk:tre) {
				if (lnk.getText().equals("True")){
					// action.click(lnk).perform();
					log.logLine(Testname, false, "Confirm whether Billable option is True/False---> And the Option is TRUE..");
					break;
				}
				else if (lnk.getText().equals("False")){
					// action.click(lnk).perform();
					log.logLine(Testname, false, "Confirm whether Billable option is True/False---> And the Option is FALSE..");
					break;
				}
			}
		}
		Thread.sleep(2000);

		//Mouse over extra field information
		PleasewaitDisappear();
		if (doesElementExist2(properties.getProperty("extrainfo"), 5)) {
			Actions action = new Actions(driver);
			WebElement extrainfofld = driver.findElement(By.cssSelector(properties.getProperty("extrainfo")));
			action.moveToElement(extrainfofld).perform();
			log.logLine(Testname, false, "Mouse over on extra field is successfull");


			if (doesElementExist2(properties.getProperty("Filecontentdetails"), 5)) {
				WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Filecontentdetails")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
				Thread.sleep(4000);
				log.logLine(Testname, false, "Clicking on Filecontentdetails button is successfull");
			} else {
				log.logLine(Testname, true, "Clicking on Filecontentdetails button is unsuccessful");
			}

			Set<String> handles = driver.getWindowHandles();
			String firstWinHandle = driver.getWindowHandle();
			handles.remove(firstWinHandle);
			boolean browserexist = handles.iterator().hasNext();
			if (browserexist) {
				String secondWinHandle = handles.iterator().next();
				if (secondWinHandle != firstWinHandle){
					//To retrieve the handle of second window, extracting the handle which does not match to first window handle
					//Switch control to new window
					driver.switchTo().window(secondWinHandle);
					driver.manage().window().maximize();
					Thread.sleep(2000);

					if ((Initialization.EnvirSite.equals("TEST")) || (Initialization.EnvirSite.equals("Test")) || (Initialization.EnvirSite.equals("test"))) {
						if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer"))
								|| (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER")))
							driver.get("javascript:document.getElementById('overridelink').click();");
					}

					Thread.sleep(5000);

					driver.close();
					// Switching back to parent window
					driver.switchTo().window(firstWinHandle);
					Thread.sleep(2000);
					//driver.switchTo().frame("iframeContainer");
				}
			} else {
				log.logLine(Testname, true, "View extra info field is not exist");
			}
		}


		return true;

	}

	public boolean deleteZipFile(String Testname) throws Exception { 		

		driver.switchTo().frame("iframeContainer");

		if (doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			log.logLine(Testname, false, "Click on Advance Search button is successfull");
		} else {
			log.logLine(Testname, true, "Click on Advance Search button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Advance Search button is failed");
		}

		PleasewaitDisappear();
		if (doesElementExist2(properties.getProperty("Fld"), 5)) {
			WebElement fld = driver.findElement(By.cssSelector(properties.getProperty("Fld")));
			Thread.sleep(1000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", fld);
			log.logLine(Testname, false, "Clicking on Lable Field is Successfull");
			Actions action=new Actions(driver);
			List<WebElement> cdl = driver.findElements(By.cssSelector(properties.getProperty("Zipfilename")));
			for (WebElement lnk:cdl){
				if(lnk.getText().equals("ZIP File Name")){
					Thread.sleep(1000);
					action.click(lnk).perform();
					log.logLine(Testname, false, "Selecting the ZIP File Name option from the Lable field");
					break;
				}
			}
		}


		if (doesElementExist(properties.getProperty("Txtfld1"), 5)) {
			WebElement Txt = driver.findElement(By.xpath(properties.getProperty("Txtfld1")));
			Txt.click();
			Txt.clear();
			Txt.sendKeys(Fnlzipnme);
			log.logLine(Testname, false, "The Enterd value in text field is " +Fnlzipnme);		 
		}else{
			log.logLine(Testname, true,"Unable to enter the text in text field");
			throw new Exception("Unable to  enter the text in text field");
		}


		if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button is successfull");
		} else {
			log.logLine(Testname, true, "Clicking on Search button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Search button is failed");
		}

		PleasewaitDisappear();
		//Clicking on Chooseaction and Selecting Delete option
		if (doesElementExist2(properties.getProperty("Chooseactn"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Chooseactn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			log.logLine(Testname, false, "Clicking on Choose action button is successfull");
		} else {
			log.logLine(Testname, true, "Clicking on Choose action button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Choose action button is failed");
		}


		if(doesElementExist2(properties.getProperty("Del"), 5)){
			Actions action = new Actions(driver);
			List<WebElement> editbtn = driver.findElements(By.cssSelector(properties.getProperty("Del")));
			for (WebElement lnk:editbtn) {
				if (lnk.getText().equals("Delete")){
					action.click(lnk).perform();
					log.logLine(Testname, false, "Selecting Delete option under choose action is successfull..");
					break;
				}
			}
		}


		if (doesElementExist2(properties.getProperty("Alrtok"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Alrtok")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			log.logLine(Testname, false, "Clicking on Ok button is successfull");
		} else {
			log.logLine(Testname, true, "Clicking on Ok button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Ok button is failed");
		}

		PleasewaitDisappear();
		// Checking for the deleted Record
		if (doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			log.logLine(Testname, false, "Click on Advance Search is successfull");
		} else {
			log.logLine(Testname, true, "Click on Advance Search is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Advance Search is failed");
		}

		PleasewaitDisappear();
		if (doesElementExist2(properties.getProperty("Fld"), 5)) {
			WebElement fld = driver.findElement(By.cssSelector(properties.getProperty("Fld")));
			Thread.sleep(1000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", fld);
			log.logLine(Testname, false, "Clicking on Lable Field is Successfull");
			Actions action=new Actions(driver);
			List<WebElement> cdl = driver.findElements(By.cssSelector(properties.getProperty("Zipfilename")));
			for (WebElement lnk:cdl){
				if(lnk.getText().equals("ZIP File Name")){
					Thread.sleep(1000);
					action.click(lnk).perform();
					log.logLine(Testname, false, "Clicking on ZIP File Name option is successfull");
					break;
				}
			}
		}


		if (doesElementExist2(properties.getProperty("Txtfld"), 5)) {
			WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("Txtfld")));
			Txt.clear();
			Txt.sendKeys(Initialization.ArchiveZIP1);
			log.logLine(Testname, false, "The Enterd value in text field is "+Initialization.ArchiveZIP1);	
		}else{
			log.logLine(Testname, true,"Unable to enter the text in text field");
			throw new Exception("Unable to  enter the text in text field");
		}


		if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button is successfull and searched record does not exists");
		} else {
			log.logLine(Testname, true, "Clicking on Search button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Search button is failed");
		}

		PleasewaitDisappear();
		if (doesElementExist2(properties.getProperty("NoData"), 5)) {
			log.logLine(Testname, false, "File Mgmt Delete - Deleted record no more exists"); 			
		}else {
			log.logLine(Testname, true, "File Mgmt Delete - Deleted record exists");
		}

		return true;


	}

} 	

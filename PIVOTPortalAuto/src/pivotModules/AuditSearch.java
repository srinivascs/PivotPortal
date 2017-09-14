package pivotModules;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
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

public class AuditSearch extends Page{

	public AuditSearch(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}	
	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}

	public static String Text="VerifyAutoComments 5555";
	public static String FileNamedata;
	public static String FileNamedataReject;
	public boolean Auditspage(String RandNo, String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(1000);
		driver.switchTo().defaultContent();
		WebElement retelm2 = waitForElement(properties.getProperty("selClint1"));		

		if (doesElementExist2(properties.getProperty("Audits"), 5)) {	    
			WebElement reportsmnu = driver.findElement(By.cssSelector(properties.getProperty("Audits")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", reportsmnu);

			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Navigation to Audits page successful");
		} else {
			log.logLine(Testname, true, "Clicking on Audits menu is failed");
			throw new Exception("Clicking on Audits menu is failed");
		}	    

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

		if (doesElementExist2(properties.getProperty("Okbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Okbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on OK button to view the Audits");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the Audits is failed");
			throw new Exception("Clicking on OK button to view the Audits is failed");
		}

		return true;
	}


	public boolean quickAdvancedSearch(String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	    

		Thread.sleep(2000);
		driver.switchTo().frame("iframeContainer");

		UsernameAscending(Testname);
		Thread.sleep(2000);
		UsernameDescending(Testname);

		Thread.sleep(2000);
		Parametervalidation(Testname);


		if (!(doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5))) {	   
			log.logLine(Testname, true, "Clicking on Advanced Search button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Advanced Search button is failed");

		} else {
			List<WebElement> clntdd = driver.findElements(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			for (WebElement btn:clntdd) {
				if (btn.getText().equals("Advanced Search")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Clicking on Advanced Search button ");
					break;
				}
			}


			Thread.sleep(2000);
			if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	    
				WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
				Thread.sleep(2000);
				PleasewaitDisappear();
				log.logLine(Testname, false, "General Search - Clicking on Search button to view the audits");
			} else {
				log.logLine(Testname, true, "General Search - Clicking on Search button to view the audits is failed");
				driver.switchTo().defaultContent();
				throw new Exception("General Search - Clicking on Search button to view the audits is failed");
			}

			if (doesElementExist2(properties.getProperty("ReportTable"), 5)) {
				log.logLine(Testname, false, "Audit General search is successful");		    		    	
			} else {
				log.logLine(Testname, false, "Audit General search is unsuccessful");
			}
		}




		String Auditstat = test.readColumnData("AuditStatus", sheetname);
		AdvancedSearch(Testname, "AuditStatus", Auditstat, "", "");

		//Clear button and cancel
		fillupAdvancedSrch(Testname, "Yes");

		// Get the reports based on the Filename selected from the dropdown
		String FileNamesrch = test.readColumnData("FileName", sheetname);
		AdvancedSearch(Testname, "FileName", FileNamesrch, "Test", "");

		//Clear button and cancel
		fillupAdvancedSrch(Testname, "Yes");	    	

		// Display the reports between from and to dates	    
		String Fromdateval = test.readColumnData("FromDate", sheetname);	    
		SimpleDateFormat gmtDateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		gmtDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
		String Todateval = gmtDateFormat.format(new Date());

		AdvancedSearch(Testname, "FROMTOdate", Fromdateval, Todateval, "");    		    

		return true;
	}	

	public boolean Parametervalidation(String Testname)throws Exception {

		if (!(doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5))) {	   
			log.logLine(Testname, true, "Clicking on Advanced Search button is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Clicking on Advanced Search button is failed");

		} else {
			List<WebElement> clntdd = driver.findElements(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			for (WebElement btn:clntdd) {
				if (btn.getText().equals("Advanced Search")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Clicking on Advanced Search button ");
					break;
				}
			}
		}


		if (doesElementExist2(properties.getProperty("FileNameAdsrch"), 5)) {	    
			WebElement flnm = driver.findElement(By.cssSelector(properties.getProperty("FileNameAdsrch")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", flnm);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on File Name drop down list in advance search");

			List<WebElement> cmp = driver.findElements(By.cssSelector(properties.getProperty("Compo")));
			for (WebElement btn:cmp) {
				if (btn.getText().equals("campo 1")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Selecting the File Name from drop down list in advance search");
					break;
				}
			}
		}else {
			log.logLine(Testname, true, "Unable to select the File Name from the dropdown ");
			throw new Exception("Unable to select the File Name from the dropdown");
		}


		if (doesElementExist2(properties.getProperty("Addbtn"), 5)) {	    
			WebElement clkadv = driver.findElement(By.cssSelector(properties.getProperty("Addbtn")));		   
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);		     
			log.logLine(Testname, false, "Click on add button");
		} else {
			log.logLine(Testname, true, "Click on add button is failed");

		}	

		if (doesElementExist(properties.getProperty("FileNameAdsrch1"), 5)) {	    
			WebElement flnm = driver.findElement(By.xpath(properties.getProperty("FileNameAdsrch1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", flnm);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on File Name drop down list in advance search");

			List<WebElement> cmp = driver.findElements(By.cssSelector("div[class='k-animation-container'] div[id='adv-search-dropdown-basic-search-criteria-field-list'] div ul[id='adv-search-dropdown-basic-search-criteria-field_listbox'] li[role='option']"));
			for (WebElement btn:cmp) {
				if (btn.getText().equals("campo 1")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Selecting the File Name from drop down list in advance search");
					break;
				}
			}
		}else {
			log.logLine(Testname, true, "Unable to select the File Name from the dropdown ");
			//throw new Exception("Unable to select the File Name from the dropdown");
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


		if (doesElementExist2(properties.getProperty("ClearbtnAdsrch"), 5)) {
			WebElement Clrbtn = driver.findElement(By.cssSelector(properties.getProperty("ClearbtnAdsrch")));
			Clrbtn.click();

			log.logLine(Testname, false, "Clicking on clear button in advanced search dialog");
		} else {
			log.logLine(Testname, true, "Clear button in advanced dialog does not exist");
			driver.switchTo().defaultContent();
			//	throw new Exception("Clear button in advanced dialog does not exist");
		}

		Thread.sleep(2000);
		//Click on Cancel button in advanced search
		if (doesElementExist2(properties.getProperty("CancelbtnAdsrch"), 5)) {
			WebElement Cancelbtn = driver.findElement(By.cssSelector(properties.getProperty("CancelbtnAdsrch")));
			Cancelbtn.click();

			log.logLine(Testname, false, "Clicking on Cancel button in advanced search dialog");
		} else {
			log.logLine(Testname, true, "Cancel button in advanced dialog does not exist");
			driver.switchTo().defaultContent();
			//throw new Exception("Cancel button in advanced dialog does not exist");
		}




		return true;
	}

	public boolean UsernameAscending(String Testname) throws Exception {

		String[] Sort = new String[150];
		int length = Sort.length;
		String row = "tr";

		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("Rcptdtearrow"), 5)) {
			WebElement eDelive = driver.findElement(By.xpath(properties.getProperty("Rcptdtearrow")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
			log.logLine(Testname, false, "Click on Arrow button on Receipt Date column is Successful");
		} else {
			log.logLine(Testname, true, "Click on Arrow button on Receipt Date column is failed");
			//throw new Exception("Click on Arrow button on Receipt Date column is failed");
		}

		List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='audit-search-grid']/table/tbody/tr"));
		if(doesElementExist2(properties.getProperty("Rcptdtecolumn"), 5)){
			for(int i = 0; i < DataCnt.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='audit-search-grid'] table tbody "+row+" td div a[title='View Audit']")).getText();

				row = row + "+tr";
			}
			log.logLine(Testname, false, "Iterating through the Rows..");
		}


		for (int i = 0; i < DataCnt.size()-1 ; i++) {
			if (Sort[i].compareToIgnoreCase(Sort[i+1])<0) {
				log.logLine(Testname, false, "Receipt Dates  <<<<"+Sort[i]+">>>>   <<<<"+Sort[i+1]+">>>>  are in Ascending order");

				if(i>=DataCnt.size()-2)
					break;

			}else if (Sort[i].compareToIgnoreCase(Sort[i+1])==0) {
				log.logLine(Testname, false, "Receipt Dates  <<<<"+Sort[i]+">>>>   <<<<"+Sort[i+1]+">>>>  are in Same Order");
				if(i>=DataCnt.size()-2)
					break;	

			}else if (Sort[i].contains("AM")&& Sort[i+1].contains("PM")) {
				log.logLine(Testname, false, "Receipt Dates  <<<<"+Sort[i]+">>>>   <<<<"+Sort[i+1]+">>>>  are in in Ascending order");
				if(i>=DataCnt.size()-2)
					break;	
			}else {
				log.logLine(Testname, true, "Receipt Dates  <<<<"+Sort[i]+">>>>   <<<<"+Sort[i+1]+">>>>  are not in Ascending order");
				//throw new Exception("User Name  <<<<"+Sort[i]+">>>>   <<<<"+Sort[i+1]+">>>>  are not in Ascending order");

			}
		}


		return true;
	}


	public boolean UsernameDescending(String Testname) throws Exception {

		String[] Sort = new String[150];
		int length = Sort.length;
		String row = "tr";

		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("Rcptdtearrow"), 5)) {
			WebElement eDelive = driver.findElement(By.xpath(properties.getProperty("Rcptdtearrow")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
			log.logLine(Testname, false, "Click on Arrow button on Receipt Date column is Successful");
		} else {
			log.logLine(Testname, true, "Click on Arrow button on Receipt Date column is failed");
			//throw new Exception("Click on Arrow button on Receipt Date column is failed");
		}

		List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='audit-search-grid']/table/tbody/tr"));
		if(doesElementExist2(properties.getProperty("Rcptdtecolumn"), 5)){
			for(int i = 0; i < DataCnt.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='audit-search-grid'] table tbody "+row+" td div a[title='View Audit']")).getText();

				row = row + "+tr";
			}
			log.logLine(Testname, false, "Iterating through the Rows..");
		}


		for (int i = 0; i < DataCnt.size()-1 ; i++) {
			if (Sort[i].compareToIgnoreCase(Sort[i+1])>0) {
				log.logLine(Testname, false, "Receipt Dates  <<<<"+Sort[i]+">>>>   <<<<"+Sort[i+1]+">>>>  are in Descending order");

				if(i>=DataCnt.size()-2)
					break;

			}else if (Sort[i].compareToIgnoreCase(Sort[i+1])==0) {
				log.logLine(Testname, false, "Receipt Dates  <<<<"+Sort[i]+">>>>   <<<<"+Sort[i+1]+">>>>  are in Same Order");
				if(i>=DataCnt.size()-2)
					break;	

			}else if (Sort[i].contains("PM")&& Sort[i+1].contains("AM")) {
				log.logLine(Testname, false, "Receipt Dates  <<<<"+Sort[i]+">>>>   <<<<"+Sort[i+1]+">>>>  are in in Descending order");
				if(i>=DataCnt.size()-2)
					break;	
			}else {
				log.logLine(Testname, true, "Receipt Dates  <<<<"+Sort[i]+">>>>   <<<<"+Sort[i+1]+">>>>  are not in Descending order");
				//throw new Exception("User Name  <<<<"+Sort[i]+">>>>   <<<<"+Sort[i+1]+">>>>  are not in Ascending order");

			}
		}


		return true;
	}


	public void AdvancedSearch(String Testname, String SrchType, String testdata1, String testdata2, String Comments) throws Exception {

		//  Actions actions = new Actions(driver);
		//  WebElement icon = driver.findElement(By.cssSelector(properties.getProperty("Iconbtn")));
		//  actions.moveToElement(icon).build().perform();

		//  String tooltip = driver.findElement(By.cssSelector(properties.getProperty("Iconbtn"))).get;

		if (!(doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5))) {	   
			log.logLine(Testname, true, "Clicking on Advanced Search button is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Clicking on Advanced Search button is failed");

		} else {
			List<WebElement> clntdd = driver.findElements(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			for (WebElement btn:clntdd) {
				if (btn.getText().equals("Advanced Search")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Clicking on Advanced Search button ");
					break;
				}
			}


			Thread.sleep(3000);
			switch (SrchType) {

			case "FROMTOdate":
				if (doesElementExist2(properties.getProperty("FromDate"), 5)) {	  
					WebElement DteFromfld = driver.findElement(By.cssSelector(properties.getProperty("FromDate")));
					DteFromfld.clear();
					Thread.sleep(1000);
					DteFromfld.sendKeys(testdata1);
					log.logLine(Testname, false, "Entering the from date value in advanced search");

					WebElement DteTofld = driver.findElement(By.cssSelector(properties.getProperty("ToDate")));
					DteTofld.clear();
					Thread.sleep(1000);
					DteTofld.sendKeys(testdata2);
					log.logLine(Testname, false, "Entering the To date value in advanced search");
				}	            	
				break;


			case "AuditStatus":

				if (doesElementExist2(properties.getProperty("AuditStatusAdsrch"), 5)) {	    
					WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("AuditStatusAdsrch")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Clicking on Seacrh criteria Field drop down list in advance search");

					List<WebElement> pnd = driver.findElements(By.cssSelector(properties.getProperty("Pending")));
					for (WebElement btn:pnd) {
						if (btn.getText().equals(testdata1)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
							log.logLine(Testname, false, "Selecting the Audit Status as "+testdata1+" from drop down list in advance search");
							break;
						}
					}
				}else {
					log.logLine(Testname, true, "Unable to select the Audit Status from the dropdown in Advanced search dialog");
					throw new Exception("Unable to select the Audit Status from the dropdown in Advanced search dialog");
				}	
				break;


			case "FileName":

				if (doesElementExist2(properties.getProperty("FileNameAdsrch"), 5)) {	    
					WebElement flnm = driver.findElement(By.cssSelector(properties.getProperty("FileNameAdsrch")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", flnm);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Clicking on File Name drop down list in advance search");

					List<WebElement> cmp = driver.findElements(By.cssSelector(properties.getProperty("Compo")));
					for (WebElement btn:cmp) {
						if (btn.getText().equals("campo 1")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
							log.logLine(Testname, false, "Selecting the File Name from drop down list in advance search");
							break;
						}
					}
				}else {
					log.logLine(Testname, true, "Unable to select the File Name from the dropdown ");
					throw new Exception("Unable to select the File Name from the dropdown");
				}

				if (doesElementExist2(properties.getProperty("FileNameFldAdsrch"), 5)) {
					WebElement filnamefld = driver.findElement(By.cssSelector(properties.getProperty("FileNameFldAdsrch")));
					filnamefld.clear();
					filnamefld.sendKeys(testdata2);	    				
					log.logLine(Testname, false, "The Entered text in field box is - "+testdata2+" ");
				}else {
					log.logLine(Testname, true, "Unable to Enter - "+testdata2+" in the Field");
					throw new Exception("Unable to Enter - "+testdata2+" in the Field");
				}
				break;	        			
			}


			Thread.sleep(3000);
			if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	    
				WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
				Thread.sleep(2000);
				PleasewaitDisappear();
				log.logLine(Testname, false, "Clicking on Search button to view the audits based on "+SrchType);
			} else {
				log.logLine(Testname, true, "Clicking on Search button to view the audits based on "+SrchType +" is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on Search button to view the audits based on "+SrchType +" is failed");
			}

			if (doesElementExist2(properties.getProperty("ReportTable"), 5)) {
				log.logLine(Testname, false, "Advanced search - Successfully displayed the reports for "+testdata1 +" " +testdata2 +" entered in "+SrchType+" field");		    		    	
			} else {
				log.logLine(Testname, false, "Advanced search - No reports found for "+testdata1  +" " +testdata2 +" entered in "+SrchType+" field");
			}	

			Thread.sleep(2000);

			Actions action = new Actions(driver);
			if (doesElementExist2(properties.getProperty("extrainfo"), 5)) {
				WebElement extrainfofld = driver.findElement(By.cssSelector(properties.getProperty("extrainfo")));		    		
				action.moveToElement(extrainfofld).perform(); 

				if (doesElementExist2(properties.getProperty("tooltipinfo"), 5)) {		    		
					String tooltip = driver.findElement(By.cssSelector(properties.getProperty("tooltipinfo"))).getText();
					if (!tooltip.equals("")) {
						log.logLine(Testname, false, "View audit  is successful");	
						log.logLine(Testname, false, "View audits extra details contains: "+tooltip+"");		

					} else {
						log.logLine(Testname, true, "View audit extra details is unsuccessful");
					}
				} else {
					log.logLine(Testname, true, "Tooltip box may not be exist");		
				}
			} else {
				log.logLine(Testname, false, "As this is a negative validation View audit extra details field doesnot exist as there is no data in grid");		
			}


			//Actions action = new Actions(driver);
			//Download the report file from reports grid
			if (SrchType.equals("FROMTOdate")) {

				if (doesElementExist2(properties.getProperty("ViewFirstRpt"), 5)) {
					WebElement viewrptbtn = driver.findElement(By.cssSelector(properties.getProperty("ViewFirstRpt")));		    		
					action.click(viewrptbtn).perform(); //Click
					Thread.sleep(18000);
					log.logLine(Testname, false, "Click on Date link to view the audit in the grid is successful");

					if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) || (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) {

						//Delete the existing file before it downloads	
						try {			
							File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/AuditPDF");
							if (fileTemp1.exists()){
								fileTemp1.delete();
								log.logLine(Testname, false, "The Existing AuditPDF has been deleted from download folder");
							}else{
								log.logLine(Testname, false, "AuditPDF file does not exists in folder");
							}
						} catch(Exception e){
							// if any error occurs
							e.printStackTrace();
						}	


						Robot robot=new Robot();
						robot.keyPress(KeyEvent.VK_ALT);
						robot.keyPress(KeyEvent.VK_S);
						robot.keyRelease(KeyEvent.VK_ALT);
						robot.keyRelease(KeyEvent.VK_S);

						log.logLine(Testname, false, "Click on Save popup to save the audit is successful");
						Thread.sleep(5000);
						try {			
							File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/AuditPDF");
							if (fileTemp1.exists()){
								log.logLine(Testname, false, "The Saved AuditPDF file exists ");
							}else{
								log.logLine(Testname, true, "Saved AuditPDF file does not exists");
							}
						} catch(Exception e){
							// if any error occurs
							e.printStackTrace();
						}



					} else if ((Initialization.Browser.equals("FF")) || (Initialization.Browser.equals("ff")) || (Initialization.Browser.equals("firefox")) || (Initialization.Browser.equals("Firefox")) || (Initialization.Browser.equals("FIREFOX")) || (Initialization.Browser.equals("Chrome")) || (Initialization.Browser.equals("chrome")) || (Initialization.Browser.equals("CHROME"))) {

						Thread.sleep(5000);
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

								if ((Initialization.EnvirSite.equals("TEST")) || (Initialization.EnvirSite.equals("Test")) || (Initialization.EnvirSite.equals("test"))) {
									if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) 
											|| (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) 
										driver.get("javascript:document.getElementById('overridelink').click();");
								}					    	

								Thread.sleep(3000);				    	
								driver.close();

								// Switching back to parent window
								driver.switchTo().window(firstWinHandle);

								Thread.sleep(2000);
								driver.switchTo().frame("iframeContainer");
							}
						}							    

						log.logLine(Testname, false, "Click on Date link to view the audit in the grid is successful");
					}
				}

				//Mouse over extra field information
				if (doesElementExist2(properties.getProperty("extrainfo"), 5)) {
					WebElement extrainfofld = driver.findElement(By.cssSelector(properties.getProperty("extrainfo")));		    		
					action.moveToElement(extrainfofld).perform(); 

					if (doesElementExist2(properties.getProperty("tooltipinfo"), 5)) {		    		
						String tooltip = driver.findElement(By.cssSelector(properties.getProperty("tooltipinfo"))).getText();
						if (!tooltip.equals("")) {
							log.logLine(Testname, false, "View audit extra details is successful");		    		    	
						} else {
							log.logLine(Testname, true, "View audit extra details is unsuccessful");
						}
					} else {
						log.logLine(Testname, true, "Tooltip box may not be exist");		
					}
				} else {
					log.logLine(Testname, false, "As this is a negative validation View audit extra details field does not exist as there is no data in the grid");		
				}


				if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) || (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) {

					try {			
						File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/AuditPDF");
						if (fileTemp1.exists()){
							fileTemp1.delete();
							log.logLine(Testname, false, "The Existing AuditPDF has been deleted from download folder");
						}else{
							log.logLine(Testname, false, " AuditPDF file does not exists in folder");
						}
					} catch(Exception e){
						// if any error occurs
						e.printStackTrace();
					}	

					if (doesElementExist2(properties.getProperty("ChoseAct2"), 5)) {	
						Thread.sleep(1000);
						log.logLine(Testname, false, "Advanced search based on From and To dates successful");

						WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("ChoseAct2")));
						log.logLine(Testname, false, "Clicking on Choose Action to view the report..");
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
						Thread.sleep(1000);

						List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SelViewAuditLnk")));	
						for (WebElement lnk:vwrpts) {
							if (lnk.getText().equals("View Audit")) {
								((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
								//lnk.click();
								Thread.sleep(10000);
								log.logLine(Testname, false, "Clicking on View Auidt link under choose action in the report grid");
								break;
							}
						}
					}

					Robot robot=new Robot();
					robot.keyPress(KeyEvent.VK_ALT);
					robot.keyPress(KeyEvent.VK_S);
					robot.keyRelease(KeyEvent.VK_ALT);
					robot.keyRelease(KeyEvent.VK_S);

					log.logLine(Testname, false, "Click on Save popup to save the audit is successful");
					Thread.sleep(5000);


					try {			
						File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/AuditPDF");
						if (fileTemp1.exists()){
							log.logLine(Testname, false, "The Saved AuditPDF file exists ");
						}else{
							log.logLine(Testname, true, "Saved AuditPDF file does not exists");
						}
					} catch(Exception e){
						// if any error occurs
						e.printStackTrace();
					}

					driver.switchTo().defaultContent();

				}else if (doesElementExist2(properties.getProperty("ChoseAct2"), 5)) {	
					Thread.sleep(1000);
					log.logLine(Testname, false, "Advanced search based on From and To dates successful");

					WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("ChoseAct2")));
					log.logLine(Testname, false, "Clicking on Choose Action to view the report..");
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
					Thread.sleep(1000);

					List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SelViewAuditLnk")));	
					for (WebElement lnk:vwrpts) {
						if (lnk.getText().equals("View Audit")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							//lnk.click();
							Thread.sleep(20000);

							Set<String> handles = driver.getWindowHandles();
							String firstWinHandle = driver.getWindowHandle(); 
							handles.remove(firstWinHandle);

							boolean browserexist = handles.iterator().hasNext();
							if (browserexist) {
								String winHandle=handles.iterator().next();
								if (winHandle!=firstWinHandle){
									//To retrieve the handle of second window, extracting the handle which does not match to first window handle
									String secondWinHandle=winHandle; //Storing handle of second window handle

									//Switch control to new window
									driver.switchTo().window(secondWinHandle);

									//if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) || (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) 
									//driver.get("javascript:document.getElementById('overridelink').click();");


									Thread.sleep(3000);
									driver.close();

									// Switching back to parent window
									driver.switchTo().window(firstWinHandle);

									Thread.sleep(2000);
									driver.switchTo().frame("iframeContainer");
								}
							}

							log.logLine(Testname, false, "Clicking on View Auidt link under choose action in the report grid");
							driver.switchTo().defaultContent();
							break;
						}
					}
					log.logLine(Testname, false, "Clicking on ChooseActions in the report grid");
				} 

				else {
					log.logLine(Testname, true, "Clicking on ChooseActions in the report grid is failed");
					driver.switchTo().defaultContent();
					throw new Exception("Clicking on ChooseActions in the report grid is failed");
				}	

				//Closing the Adobe processes running
				Runtime.getRuntime().exec("taskkill /F /IM AcroRd32.exe");      


				if (Comments.equals("Yes")) {	    	
					VerifyComments(Testname);

				}			    

			}		
		}		

	}

	public void Auditstatusnapprovals(String Testname) throws Exception {

		String[] Sort = new String[50];
		String[] Sort1 = new String[50];
		String[] Sort2 = new String[50];

		List<WebElement> DataCnt1= driver.findElements(By.xpath(".//*[@id='audit-search-grid']/table/tbody/tr"));
		int abc=DataCnt1.size();
		if(abc==0){
			if(doesElementExist(properties.getProperty("noitem"), 5)) {	    
				WebElement lgnbtn = driver.findElement(By.xpath(properties.getProperty("noitem")));	
				log.logLine(Testname, false, "No item exists to display");	}
		}
		else if(doesElementExist2(properties.getProperty("auditstat"), 5)){
			WebElement lgnbtn = driver.findElement(By.cssSelector(properties.getProperty("auditstat")));
			String text=lgnbtn.getText();
			if(text.equalsIgnoreCase("Audits Status and Approvals")){
				log.logLine(Testname, false, "'Audits Status and Approvals column exists");}
			for(int i = 1; i <= DataCnt1.size(); i++) {
				Sort[i]= driver.findElement(By.xpath("html/body/div[1]/div[4]/table/tbody/tr["+i+"]/td[2]/div/div[1]")).getText();
				Sort1[i]= driver.findElement(By.xpath(".//*[@id='audit-search-grid']/table/tbody/tr["+i+"]/td[2]/div/div[2]/button[1]")).getAttribute("title");
				Sort2[i]= driver.findElement(By.xpath(".//*[@id='audit-search-grid']/table/tbody/tr["+i+"]/td[2]/div/div[2]/button[2]")).getAttribute("title");

				if((Sort[i].equalsIgnoreCase("Pending")) && (Sort1[i].equalsIgnoreCase("Approve")) && (Sort2[i].equalsIgnoreCase("Reject"))){
					log.logLine(Testname, false, "Audit status  "+Sort[i]+" and  approval icons "+Sort1[i]+" "+Sort2[i]+" appears in the grid");
				}
				else{
					log.logLine(Testname, false, "Audit status  "+Sort[i]+" and  approval icons "+Sort1[i]+" "+Sort2[i]+" doesnot appear in the grid");
					log.logLine(Testname, false, "Validation of 'Audit staus , approve & reject icons is failed");
					break;
				}			
			}}
		else {
			log.logLine(Testname, true, "'Audits Status and Approvals' column doesnot exists");
			driver.switchTo().defaultContent();
			throw new Exception("'Audits Status and Approvals' column doesnot exists");
		}	
	}

	public boolean Auditstatus(String Testname, String Auditstat1) throws Exception {

		InputOutputData test = new InputOutputData();
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		driver.switchTo().frame("iframeContainer");

		if (!(doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5))) {	   
			log.logLine(Testname, true, "Clicking on Advanced Search button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Advanced Search button is failed");

		} else {
			List<WebElement> clntdd = driver.findElements(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			for (WebElement btn:clntdd) {
				if (btn.getText().equals("Advanced Search")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Clicking on Advanced Search button ");
					break;
				}
			}}

		if (doesElementExist2(properties.getProperty("AuditStatusAdsrch"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("AuditStatusAdsrch")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on Seacrh criteria Field drop down list in advance search");
			List<WebElement> pnd = driver.findElements(By.cssSelector(properties.getProperty("Pending")));
			for (WebElement btn:pnd) {
				if (btn.getText().equals(Auditstat1)) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Selecting the Audit Status as "+Auditstat1+" from drop down list in advance search");
					break;
				}
			}
		}else {
			log.logLine(Testname, true, "Unable to select the "+Auditstat1+" from the dropdown in Advanced search dialog");
			throw new Exception("Unable to select the "+Auditstat1+" from the dropdown in Advanced search dialog");
		}	

		Thread.sleep(3000);
		if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	    
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button to view the audits");
		} else {
			log.logLine(Testname, true, "Clicking on Search button to view the audits is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Search button to view the audits is failed");
		}

		Thread.sleep(12000);;

		Auditstatusnapprovals(Testname);
		return true ;
	}	    


	public boolean fillupAdvancedSrch(String Testname, String ClrCancel) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		if (!(doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5))) {	   
			log.logLine(Testname, true, "Clicking on Advanced Search button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Advanced Search button is failed");

		} else {
			List<WebElement> clntdd = driver.findElements(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			for (WebElement btn:clntdd) {
				if (btn.getText().equals("Advanced Search")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Clicking on Advanced Search button ");
					break;
				}
			}

			String testdata1 = test.readColumnData("FromDate", sheetname);		
			SimpleDateFormat gmtDateFormat = new SimpleDateFormat("MM/dd/yyyy");		
			gmtDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
			String testdata2 = gmtDateFormat.format(new Date());

			if (doesElementExist2(properties.getProperty("FromDate"), 5)) {	  
				WebElement DteFromfld = driver.findElement(By.cssSelector(properties.getProperty("FromDate")));
				DteFromfld.clear();
				Thread.sleep(1000);
				DteFromfld.sendKeys(testdata1);
				log.logLine(Testname, false, "Entering the from date value "+testdata1+" in advanced search");

				WebElement DteTofld = driver.findElement(By.cssSelector(properties.getProperty("ToDate")));
				DteTofld.clear();
				Thread.sleep(1000);
				DteTofld.sendKeys(testdata2);
				log.logLine(Testname, false, "Entering the To date value "+testdata2+" in advanced search");
			}     


			String Auditstat = test.readColumnData("AuditStatus", sheetname);       
			if (doesElementExist2(properties.getProperty("AuditStatusAdsrch"), 5)) {	    
				WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("AuditStatusAdsrch")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);

				Thread.sleep(1000);
				log.logLine(Testname, false, "Clicking on Seacrh criteria Field drop down list in advance search");

				List<WebElement> pnd = driver.findElements(By.cssSelector(properties.getProperty("Pending")));
				for (WebElement btn:pnd) {
					if (btn.getText().equals("Pending")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
						log.logLine(Testname, false, "Selecting the Audit Status as Pending from drop down list in advance search");
						break;
					}
				}
			}else {
				log.logLine(Testname, true, "Unable to select the Audit Status from the dropdown in Advanced search dialog");
				throw new Exception("Unable to select the Audit Status from the dropdown in Advanced search dialog");
			}	

			String FileNamesrch = test.readColumnData("FileName", sheetname);
			String FileNamedata = test.readColumnData("FileData", sheetname);

			if (doesElementExist2(properties.getProperty("FileNameAdsrch"), 5)) {	    
				WebElement flnm = driver.findElement(By.cssSelector(properties.getProperty("FileNameAdsrch")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", flnm);
				Thread.sleep(1000);
				log.logLine(Testname, false, "Clicking on File Name Drop down list in advance search");

				List<WebElement> cmp = driver.findElements(By.cssSelector(properties.getProperty("Compo")));
				for (WebElement btn:cmp) {
					if (btn.getText().equals("campo 1")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
						log.logLine(Testname, false, "Selecting the File Name "+FileNamesrch+" from drop down list in advance search");
						break;
					}
				}
			}else {
				log.logLine(Testname, true, "Unable to select the File Name from the dropdown ");
				throw new Exception("Unable to select the File Name from the dropdown");
			}


			if (doesElementExist2(properties.getProperty("FileNameFldAdsrch"), 5)) {
				WebElement filnamefld = driver.findElement(By.cssSelector(properties.getProperty("FileNameFldAdsrch")));
				filnamefld.clear();
				filnamefld.sendKeys(FileNamedata);	    				
				log.logLine(Testname, false, "The Entered text in field box is - "+FileNamedata+" ");
			}else {
				log.logLine(Testname, true, "Unable to Enter - "+FileNamedata+" in the Field");
				throw new Exception("Unable to Enter - "+FileNamedata+" in the Field");
			}


			//Click on Clear button in advanced search
			Thread.sleep(2000);
			if (ClrCancel.equals("Yes")) {
				if (doesElementExist2(properties.getProperty("ClearbtnAdsrch"), 5)) {
					WebElement Clrbtn = driver.findElement(By.cssSelector(properties.getProperty("ClearbtnAdsrch")));
					Clrbtn.click();

					log.logLine(Testname, false, "Clicking on clear button in advanced search dialog");
				} else {
					log.logLine(Testname, true, "Clear button in advanced dialog does not exist");
					driver.switchTo().defaultContent();
					throw new Exception("Clear button in advanced dialog does not exist");
				}

				Thread.sleep(2000);
				//Click on Cancel button in advanced search
				if (doesElementExist2(properties.getProperty("CancelbtnAdsrch"), 5)) {
					WebElement Cancelbtn = driver.findElement(By.cssSelector(properties.getProperty("CancelbtnAdsrch")));
					Cancelbtn.click();

					log.logLine(Testname, false, "Clicking on Cancel button in advanced search dialog");
				} else {
					log.logLine(Testname, true, "Cancel button in advanced dialog does not exist");
					driver.switchTo().defaultContent();
					throw new Exception("Cancel button in advanced dialog does not exist");
				}	

				return true;
			}	    

			Thread.sleep(3000);
			if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	    
				WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
				PleasewaitDisappear();
				log.logLine(Testname, false, "Clicking on Search button to view the audits");
			} else {
				log.logLine(Testname, true, "Clicking on Search button to view the audits is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on Search button to view the audits is failed");
			}	
		}

		return true;		
	}


	public void VerifyComments(String Testname) throws Exception {


		driver.switchTo().frame("iframeContainer");
		Actions action = new Actions(driver);

		if (doesElementExist2(properties.getProperty("CommentIcon"), 5)) 
		{
			WebElement CommentIconfld = driver.findElement(By.cssSelector(properties.getProperty("CommentIcon")));
			action.moveToElement(CommentIconfld).build().perform();

			if (doesElementExist2(properties.getProperty("CommentIcontip"), 5)) 
			{
				String CommentIcontooltip = driver.findElement(By.cssSelector(properties.getProperty("CommentIcontip"))).getAttribute("value");
				log.logLine(Testname, false,"View Comment field Contains"+CommentIcontooltip);
				if(CommentIcontooltip.equals("")){
					log.logLine(Testname, false,"Comment is not yet added,the icon is not bolded");				
				}

			}else {
				log.logLine(Testname, false,"Comment Tool tip does not exist");
			}

		}else {
			log.logLine(Testname, false," Comment does not exist");
		}




		if (doesElementExist2(properties.getProperty("ChoseAct2"), 5)) {
			log.logLine(Testname, false, "Clicking on Choose Action to add comments..");

			WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("ChoseAct2")));
			log.logLine(Testname, false, "Clicking on AddComments under choose action");

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
			Thread.sleep(1000);

			List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SelViewAuditLnk")));	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals("Add Comments")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					//lnk.click();
					Thread.sleep(3000);
				}
			}
		} else {
			log.logLine(Testname, true, "Clicking on Choose actions failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Choose actions failed");
		}



		if (doesElementExist2(properties.getProperty("Comtstxtbox"), 5)) {	    
			WebElement btxt = driver.findElement(By.cssSelector(properties.getProperty("Comtstxtbox")));

			int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
			String AccNo = Integer.toString(paperID);

			btxt.sendKeys(Text);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entering the comments in textarea field with"+Text);

		} else {
			log.logLine(Testname, true, "Entering the comments in textarea field is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the comments in textarea field is failed");
		}	


		if (doesElementExist2(properties.getProperty("ComtsClrbtn"), 5)) {	    
			WebElement clrbtn = driver.findElement(By.cssSelector(properties.getProperty("ComtsClrbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clrbtn);
			log.logLine(Testname, false, "Clicking on Clear button to clear the comments");
		} else {
			log.logLine(Testname, true, "Clicking on Clear button to clear the comments is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Clear button to clear the comments is failed");
		}

		if (doesElementExist2(properties.getProperty("ComtsCancelbtn"), 5)) {	    
			WebElement canclbtn = driver.findElement(By.cssSelector(properties.getProperty("ComtsCancelbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", canclbtn);
			log.logLine(Testname, false, "Clicking on Cancel button to close the comments textbox");		

		} else {
			log.logLine(Testname, true, "Clicking on Cancel button to close the comments textbox is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Cancel button to close the comments textbox is failed");
		}


		//Click on Cancel button
		if (doesElementExist2(properties.getProperty("ChoseAct2"), 5)) {
			log.logLine(Testname, false, "Clicking on Choose Action to add comments..");

			WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("ChoseAct2")));
			log.logLine(Testname, false, "Clicking on AddComments under choose action");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
			Thread.sleep(1000);

			List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SelViewAuditLnk")));	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals("Add Comments")) {
					//lnk.click();
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(4000);
				}
			}
		} else {
			log.logLine(Testname, true, "Clicking on Choose actions failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Choose actions failed");
		}


		if (doesElementExist2(properties.getProperty("Comtstxtbox"), 5)) {	    
			WebElement btxt = driver.findElement(By.cssSelector(properties.getProperty("Comtstxtbox")));

			int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
			String AccNo = Integer.toString(paperID);

			btxt.sendKeys(Text);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entering the comments in textarea field with "+"AutoComments_"+AccNo);

			/*
			if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer")))
				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + "AutoComments_"+AccNo +"')", btxt);
			else			
				btxt.sendKeys(Text);

			log.logLine(Testname, false, "Entering the comments in textarea field with "+Text);
			 */
		} else {
			log.logLine(Testname, true, "Entering the comments in textarea field is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the comments in textarea field is failed");
		}	


		if (doesElementExist2(properties.getProperty("ComtsCancelbtn"), 5)) {	    
			WebElement canclbtn = driver.findElement(By.cssSelector(properties.getProperty("ComtsCancelbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", canclbtn);
			log.logLine(Testname, false, "Clicking on Cancel button to close the comments textbox");

			if (doesElementExist2(properties.getProperty("Comtstxtbox"), 5)) 
				log.logLine(Testname, false, "Add Comments - Cancel is successful");
			else 
				log.logLine(Testname, true, "Add Comments - Cancel is unsuccessful");

		} else {
			log.logLine(Testname, true, "Clicking on Cancel button to close the comments textbox is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Cancel button to close the comments textbox is failed");
		}

		//Add Comments and Save 

		if (doesElementExist2(properties.getProperty("ChoseAct2"), 5)) {
			log.logLine(Testname, false, "Clicking on Choose Action to add comments..");

			WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("ChoseAct2")));
			log.logLine(Testname, false, "Clicking on AddComments under choose action");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
			Thread.sleep(1000);

			List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SelViewAuditLnk")));	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals("Add Comments")) {
					//lnk.click();
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(4000);
				}
			}
		} else {
			log.logLine(Testname, true, "Clicking on Choose actions failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Choose actions failed");
		}

		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		

		if (doesElementExist2(properties.getProperty("Comtstxtbox"), 5)) {	    
			WebElement btxt = driver.findElement(By.cssSelector(properties.getProperty("Comtstxtbox")));
			btxt.sendKeys(Text);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entering the comments in textarea field with "+Text);

			/*
			if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer")))
				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + "AutoComments_"+AccNo +"')", btxt);
			else		
				btxt.sendKeys(Text);

			log.logLine(Testname, false, "Entering the comments in textarea field with "+"AutoComments_"+AccNo);
			 */

		} else {
			log.logLine(Testname, true, "Entering the comments in textarea field is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the comments in textarea field is failed");
		}	


		if (doesElementExist2(properties.getProperty("ComtsSavebtn"), 5)) {	    
			WebElement canclbtn = driver.findElement(By.cssSelector(properties.getProperty("ComtsSavebtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", canclbtn);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicking on Save button to add comments for audit");

			Thread.sleep(5000);
			if (doesElementExist2(properties.getProperty("Comtstxtbox"), 5)) 
				log.logLine(Testname, false, "Add Comments - Save is successful");
			else 
				log.logLine(Testname, true, "Add Comments - Save is unsuccessful");

		} else {
			log.logLine(Testname, true, "Clicking on Save button to add comments for audit is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Save button to add comments for audit is failed");
		}


		if (doesElementExist2(properties.getProperty("CommentIcon"), 5)) 
		{
			WebElement CommentIconfld = driver.findElement(By.cssSelector(properties.getProperty("CommentIcon")));
			action.moveToElement(CommentIconfld).build().perform();


			Thread.sleep(5000);

			if (doesElementExist2(properties.getProperty("CommentIcontip"), 5)) 
			{
				String CommentIcontooltip = driver.findElement(By.cssSelector(properties.getProperty("CommentIcontip"))).getAttribute("value");
				log.logLine(Testname, false,"View Comment field Contains"+CommentIcontooltip);

				Thread.sleep(2000);
				if(CommentIcontooltip.equals(Text))
				{
					log.logLine(Testname, false,"View Comment Matches the Added comments and the Icon is bolded");				
				}

			}else {
				log.logLine(Testname, true,"View Comment field does not exist");
			}

		}else {
			log.logLine(Testname, false," Comment does not exist");
		}



		//View Comments
		if (doesElementExist2(properties.getProperty("ChoseAct2"), 5)) {
			log.logLine(Testname, false, "Clicking on Choose Action to add comments..");

			WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("ChoseAct2")));
			log.logLine(Testname, false, "Clicking on View Comments under choose action");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
			Thread.sleep(1000);

			List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SelViewAuditLnk")));	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals("View Comments")) {
					//lnk.click();
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);			
					Thread.sleep(8000);
				}
			}
		} else {
			log.logLine(Testname, true, "Clicking on Choose actions failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Choose actions failed");
		}



		if (doesElementExist2(properties.getProperty("ComtsView"), 5)) {	    
			String Comments = driver.findElement(By.cssSelector(properties.getProperty("ComtsView"))).getAttribute("value");
			log.logLine(Testname, false, "Verify the added comments,..");

			if (Comments.contains(Text)) {
				Thread.sleep(2000);
				log.logLine(Testname, false, "View Comments is successful");				
			} else {
				log.logLine(Testname, true, "View Comments is unsuccessful,May be comment not added");
			}			

		} else {
			log.logLine(Testname, true, "Entering the comments in textarea field is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the comments in textarea field is failed");
		}	


		if (doesElementExist2(properties.getProperty("ComtsOKbtn"), 5)) {	    
			WebElement canclbtn = driver.findElement(By.cssSelector(properties.getProperty("ComtsOKbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", canclbtn);
			log.logLine(Testname, false, "Clicking on OK button after viewing the comments");

		} else {
			log.logLine(Testname, true, "Clicking on OK button after viewing the comments is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on OK button after viewing the comments is failed");
		}
	}



	public String ChangeStatus(String Testname, String status) throws Exception {

		String FirstAudit = "", FlagS = "";
		if (doesElementExist2(properties.getProperty("FirstAuditName"), 5)) {
			FirstAudit = driver.findElement(By.cssSelector(properties.getProperty("FirstAuditName"))).getText();
		} else {
			log.logLine(Testname, true, "Unable to capture the first audit name");			
		}

		if (doesElementExist2(properties.getProperty("ChoseAct2"), 5)) {

			log.logLine(Testname, false, "Clicking on Choose Action to "+status);			
			WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("ChoseAct2")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);

			List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SelViewAuditLnk")));	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals(status)) {
					log.logLine(Testname, false, "Clicking on "+status +" under choose actions");
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);

					FlagS = "Yes";

					//Verify access permission
					if ((status.equalsIgnoreCase("Cancel Audit")) && (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSUPER"))) {

						log.logLine(Testname, false, "Permission Verified: RRD Super user has only access to Cancel Audit");

					} else if (((status.equalsIgnoreCase("Cancel Audit"))) && ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTADMIN"))
							|| (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSITE")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDCLIENT"))
							|| (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDUSER")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTUSER")))) {

						log.logLine(Testname, true, "Client Admin, Client User, RRD Site, RRD Client & RRD User should not have access to Cancel Audit");
						throw new Exception("Client Admin, Client User, RRD Site, RRD Client & RRD User should not have access to Cancel Audit");
					}

					Thread.sleep(4000);
					break;
				}
			}
		} else {
			log.logLine(Testname, true, "Clicking on Choose actions failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Choose actions failed");
		}		


		if ((status.equalsIgnoreCase("Cancel Audit")) && (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSUPER"))) {
			if (FlagS.equals("")) {
				log.logLine(Testname, true, "Permission Denied: RRD Super user Should have access to Cancel Audit");	
			}
		}

		if (FlagS.equals("Yes")) {
			String Popupmsg = driver.findElement(By.cssSelector(properties.getProperty("Popupmsg"))).getText();
			log.logLine(Testname, false, "Reading the pop up message as "+Popupmsg+"");
			String Array[]=Popupmsg.split("\\?");
			String Array1[]=Array[0].split("\\,");
			String Array2[]=Array1[1].split("file name -");
			String Auditname=Array2[1].trim();
			log.logLine(Testname, false, "Audit name present in the pop up is "+Auditname+"");

			if (doesElementExist2(properties.getProperty("ConfrmAuditstat"), 5)) {
				WebElement confirmalert = driver.findElement(By.cssSelector(properties.getProperty("ConfrmAuditstat")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", confirmalert);
				PleasewaitDisappear();
				Thread.sleep(2000);
				log.logLine(Testname, false, "Clicking on Yes button to confirm audit status change");
			} else {
				log.logLine(Testname, true, "Unable to find confirmation alert for audit status change");			
			}
		}else {
			log.logLine(Testname, false, "Permission Verified: Client Admin, Client User, RRD SiteAdmin, RRD Client Admin & RRD User does not have access to Cancel Audit");
			return "";
		}


		return FirstAudit;	
	}
	
	
	public boolean AuditsApprove(String accNo, String Testname, String Auditstat1) throws Exception {
		
		driver.switchTo().frame("iframeContainer");
		
		if (!(doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5))) {	   
			log.logLine(Testname, true, "Clicking on Advanced Search button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Advanced Search button is failed");

		} else {
			List<WebElement> clntdd = driver.findElements(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			for (WebElement btn:clntdd) {
				if (btn.getText().equals("Advanced Search")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Clicking on Advanced Search button ");
					break;
				}
			}
		}

		Thread.sleep(3000);
		if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	    
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button to view the audits");
		} else {
			log.logLine(Testname, true, "Clicking on Search button to view the audits is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Search button to view the audits is failed");
		}
		
		Thread.sleep(2000);
		if(doesElementExist(properties.getProperty("FileName"), 5)){
			FileNamedata = driver.findElement(By.xpath(properties.getProperty("FileName"))).getText();
			log.logLine(Testname, false, "FileName is "+FileNamedata+"");
		} else {
			log.logLine(Testname, true, "Fetching of FileName is Failed");
		}
		
		if(doesElementExist2(properties.getProperty("ApproveBtn"), 5)){
			WebElement Apprvebtn = driver.findElement(By.cssSelector(properties.getProperty("ApproveBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Apprvebtn);
			log.logLine(Testname, false, "Clicking on Approve button is Successful");
		} else {
			log.logLine(Testname, true, "Clicking on Approve button is UnSuccessful");
			throw new Exception("Clicking on Approve button is UnSuccessful");
		}
		
		if(doesElementExist2(properties.getProperty("ApproveALRTMsg"), 5)){
			String Apprvealrtmsg = driver.findElement(By.cssSelector(properties.getProperty("ApproveALRTMsg"))).getText();
			log.logLine(Testname, false, "Approve Alert Message is "+Apprvealrtmsg+"");
		} else {
			log.logLine(Testname, true, "Fetching of Alert Message on Approve is Failed");
		}
		
		if(doesElementExist2(properties.getProperty("Yesbtn"), 5)){
			WebElement Yesbtn = driver.findElement(By.cssSelector(properties.getProperty("Yesbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Yesbtn);
			log.logLine(Testname, false, "Clicking on Approve Yes button is Successful");
		} else {
			log.logLine(Testname, true, "Clicking on Approve Yes button is UnSuccessful");
			throw new Exception("Clicking on Approve Yes button is UnSuccessful");
		}
		PleasewaitDisappear();
		
		if (!(doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5))) {	   
			log.logLine(Testname, true, "Clicking on Advanced Search button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Advanced Search button is failed");

		} else {
			List<WebElement> clntdd = driver.findElements(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			for (WebElement btn:clntdd) {
				if (btn.getText().equals("Advanced Search")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Clicking on Advanced Search button ");
					break;
				}
			}
		}
		
		if (doesElementExist2(properties.getProperty("AuditStatusAdsrch"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("AuditStatusAdsrch")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on Seacrh criteria Field drop down list in advance search");
			
			List<WebElement> pnd = driver.findElements(By.cssSelector(properties.getProperty("Approved")));
			for (WebElement btn:pnd) {
				if (btn.getText().equals(Auditstat1)) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Selecting the Audit Status as "+Auditstat1+" from drop down list in advance search");
					break;
				}
			}
		}else {
			log.logLine(Testname, true, "Unable to select the "+Auditstat1+" from the dropdown in Advanced search dialog");
			throw new Exception("Unable to select the "+Auditstat1+" from the dropdown in Advanced search dialog");
		}	

		if (doesElementExist2(properties.getProperty("FileNameFldAdsrch"), 5)) {
			WebElement filnamefld = driver.findElement(By.cssSelector(properties.getProperty("FileNameFldAdsrch")));
			filnamefld.clear();
			filnamefld.sendKeys(FileNamedata);	    				
			log.logLine(Testname, false, "The Entered text in field box is - "+FileNamedata+" ");
		}else {
			log.logLine(Testname, true, "Unable to Enter - "+FileNamedata+" in the Field");
			throw new Exception("Unable to Enter - "+FileNamedata+" in the Field");
		}
		
		if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	    
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button to view the audits");
		} else {
			log.logLine(Testname, true, "Clicking on Search button to view the audits is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Search button to view the audits is failed");
		}
		
		PleasewaitDisappear();
		Thread.sleep(3000);
		if(doesElementExist(properties.getProperty("ApprovedFileName"), 5)){
			String FileNamedata1 = driver.findElement(By.xpath(properties.getProperty("ApprovedFileName"))).getText();
			if (FileNamedata1.equals(FileNamedata)) {
				log.logLine(Testname, false, "FileName is "+FileNamedata1+" is equalto "+FileNamedata+"");
			} else {
			log.logLine(Testname, true, "FileName is "+FileNamedata1+" is Notequalto "+FileNamedata+"");
			}
		}
		
		if(doesElementExist(properties.getProperty("StatusChange"), 5)){
			String changeStatus = driver.findElement(By.xpath(properties.getProperty("StatusChange"))).getText();
			log.logLine(Testname, false, "Status is "+changeStatus+"");
		} else {
			log.logLine(Testname, true, "Fetching of Status is Failed");
		}


		return true ;
	}
	
	public boolean AuditsReject(String accNo, String Testname, String Auditstat2, String Auditstat1) throws Exception{
		
		driver.switchTo().frame("iframeContainer");
		
		if (!(doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5))) {	   
			log.logLine(Testname, true, "Clicking on Advanced Search button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Advanced Search button is failed");

		} else {
			List<WebElement> clntdd = driver.findElements(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			for (WebElement btn:clntdd) {
				if (btn.getText().equals("Advanced Search")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Clicking on Advanced Search button ");
					break;
				}
			}
		}
		
		if (doesElementExist2(properties.getProperty("AuditStatusAdsrch"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("AuditStatusAdsrch")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on Seacrh criteria Field drop down list in advance search");
			
			List<WebElement> pnd = driver.findElements(By.cssSelector(properties.getProperty("Pending")));
			for (WebElement btn:pnd) {
				if (btn.getText().equals(Auditstat2)) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Selecting the Audit Status as "+Auditstat2+" from drop down list in advance search");
					break;
				}
			}
		}else {
			log.logLine(Testname, true, "Unable to select the "+Auditstat2+" from the dropdown in Advanced search dialog");
			throw new Exception("Unable to select the "+Auditstat2+" from the dropdown in Advanced search dialog");
		}
		
		if (doesElementExist2(properties.getProperty("FileNameFldAdsrch"), 5)) {
			WebElement filnamefld = driver.findElement(By.cssSelector(properties.getProperty("FileNameFldAdsrch")));
			filnamefld.clear();
		}

		Thread.sleep(3000);
		if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	    
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button to view the audits");
		} else {
			log.logLine(Testname, true, "Clicking on Search button to view the audits is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Search button to view the audits is failed");
		}
		
		if(doesElementExist(properties.getProperty("FileName"), 5)){
			FileNamedataReject = driver.findElement(By.xpath(properties.getProperty("FileName"))).getText();
			log.logLine(Testname, false, "FileName is "+FileNamedataReject+"");
		} else {
			log.logLine(Testname, true, "Fetching of FileName is Failed");
		}
		
		if(doesElementExist2(properties.getProperty("Rejectbtn"), 5)){
			WebElement Apprvebtn = driver.findElement(By.cssSelector(properties.getProperty("Rejectbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Apprvebtn);
			log.logLine(Testname, false, "Clicking on Reject button is Successful");
		} else {
			log.logLine(Testname, true, "Clicking on Reject button is UnSuccessful");
			throw new Exception("Clicking on Reject button is UnSuccessful");
		}
		
		if(doesElementExist2(properties.getProperty("RejectALRTMsg"), 5)){
			String Rejectalrtmsg = driver.findElement(By.cssSelector(properties.getProperty("RejectALRTMsg"))).getText();
			log.logLine(Testname, false, "Reject Alert Message is "+Rejectalrtmsg+"");
		} else {
			log.logLine(Testname, true, "Fetching of Alert Message on Reject is Failed");
		}
		
		if(doesElementExist2(properties.getProperty("Yesbtn"), 5)){
			WebElement Yesbtn = driver.findElement(By.cssSelector(properties.getProperty("Yesbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Yesbtn);
			log.logLine(Testname, false, "Clicking on Reject Yes button is Successful");
		} else {
			log.logLine(Testname, true, "Clicking on Reject Yes button is UnSuccessful");
			throw new Exception("Clicking on Reject Yes button is UnSuccessful");
		}
		
		PleasewaitDisappear();
		Thread.sleep(3000);
		if (!(doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5))) {	   
			log.logLine(Testname, true, "Clicking on Advanced Search button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Advanced Search button is failed");

		} else {
			List<WebElement> clntdd = driver.findElements(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			for (WebElement btn:clntdd) {
				if (btn.getText().equals("Advanced Search")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Clicking on Advanced Search button ");
					break;
				}
			}
		}
		
		if (doesElementExist2(properties.getProperty("AuditStatusAdsrch"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("AuditStatusAdsrch")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on Seacrh criteria Field drop down list in advance search");
			
			List<WebElement> pnd = driver.findElements(By.cssSelector(properties.getProperty("Disapproved")));
			for (WebElement btn:pnd) {
				if (btn.getText().equals(Auditstat1)) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Selecting the Audit Status as "+Auditstat1+" from drop down list in advance search");
					break;
				}
			}
		}else {
			log.logLine(Testname, true, "Unable to select the "+Auditstat1+" from the dropdown in Advanced search dialog");
			throw new Exception("Unable to select the "+Auditstat1+" from the dropdown in Advanced search dialog");
		}	
		
		Thread.sleep(3000);
		if (doesElementExist2(properties.getProperty("FileNameFldAdsrch"), 5)) {
			WebElement filnamefld = driver.findElement(By.cssSelector(properties.getProperty("FileNameFldAdsrch")));
			filnamefld.clear();
			filnamefld.sendKeys(FileNamedataReject);	    				
			log.logLine(Testname, false, "The Entered text in field box is - "+FileNamedataReject+" ");
		}else {
			log.logLine(Testname, true, "Unable to Enter - "+FileNamedataReject+" in the Field");
			throw new Exception("Unable to Enter - "+FileNamedataReject+" in the Field");
		}
		
		if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	    
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button to view the audits");
		} else {
			log.logLine(Testname, true, "Clicking on Search button to view the audits is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Search button to view the audits is failed");
		}
		
		Thread.sleep(5000);
		if(doesElementExist(properties.getProperty("RejectedFileName"), 5)){
			String FileNamedata1 = driver.findElement(By.xpath(properties.getProperty("RejectedFileName"))).getText();
			if (FileNamedata1.equals(FileNamedataReject)) {
				log.logLine(Testname, false, "FileName is "+FileNamedata1+" is equalto "+FileNamedataReject+"");
			} else {
			log.logLine(Testname, true, "FileName is "+FileNamedata1+" is Notequalto "+FileNamedataReject+"");
			}
		}
		
		if(doesElementExist(properties.getProperty("StatusChange"), 5)){
			String changeStatus = driver.findElement(By.xpath(properties.getProperty("StatusChange"))).getText();
			log.logLine(Testname, false, "Status is "+changeStatus+"");
		} else {
			log.logLine(Testname, true, "Fetching of Status is Failed");
		}
		return true;
}

}
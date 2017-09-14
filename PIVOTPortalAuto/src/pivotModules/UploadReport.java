package pivotModules;

import java.io.IOException;
import java.util.List;

import launchAuto.Initialization;
import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;



public class UploadReport extends Page{
	public String check ="checked";
	public String uncheck ="unchecked";
	
	public UploadReport(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}	
	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}	
	
	
	public void reportViewerchkaction(String AccNo, String Testname)throws Exception {
		
		Thread.sleep(1000);
		reportViewer(AccNo, Testname);
		Thread.sleep(2000);
		driver.switchTo().frame("iframeContainer");
		Thread.sleep(3000);
		log.logLine(Testname, false, "bulk chk action");
		selectingCheckBox(Testname, check);
		Thread.sleep(3000);
		log.logLine(Testname, false, "bulk unchk action");
		selectingCheckBox(Testname, uncheck);
		Thread.sleep(3000);
		log.logLine(Testname, false, "individual unchk action");
		individualunchk(Testname);
		Thread.sleep(3000);
	}


//check & uncheck actions.....

	private void selectingCheckBox(String Testname, String status)throws Exception {
		int count=0;
		int count1=0;
		if (doesElementExist2(properties.getProperty("bulkchk"), 5)) {	  
			WebElement bulkchk1 = driver.findElement(By.cssSelector(properties.getProperty("bulkchk")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",bulkchk1);
			log.logLine(Testname, false, "Selecting the bulkreport checkbox ");
			Thread.sleep(2000);
			// Finding total no of rows in the grid

			int NumOfRowsInTable=0 ;
			List<WebElement> Rows = driver.findElements(By.cssSelector(("div[id='report-viewer-grid'] table tbody tr[role='row']")));
			NumOfRowsInTable = Rows.size() ;
			log.logLine(Testname, false, "Number of Rows in the table  are "+NumOfRowsInTable);

			// validating if all the rows are selected or not

			if (doesElementExist2(properties.getProperty("individualchk"), 5)) {
				//.....validating 'checked condition'.....
				for (int N =1 ; N <= NumOfRowsInTable; N++)
				{			 
					WebElement indchk = driver.findElement(By.xpath((".//*[@id='report-viewer-grid']/table/tbody/tr["+N+"]/td[8]/div/label")));
					Thread.sleep(2000);
					if(status.equalsIgnoreCase("checked")){

						if(indchk.isEnabled())
						{
							count =count+1;
						}

					} else  if(!status.equalsIgnoreCase("checked")){
						if(!indchk.isSelected())
						{
							count1 =count1+1;
						}

					}

				}
				if (count == NumOfRowsInTable) {
					log.logLine(Testname, false,"All individual rows are selected");
				} else if (count1 == NumOfRowsInTable) {
					log.logLine(Testname, false,"All individual rows are deselected");
				} else {
					log.logLine(Testname, true,"bulk chk action is not working as expected");
					throw new Exception("bulk chk action is not working as expected");
				}
			}

		}

		else
		{log.logLine(Testname, true, "Selecting the bulkreport checkbox failed");
		throw new Exception("Selecting the bulkreport checkbox failed");
		} }

//Individual uncheck action.....

	private void individualunchk(String Testname) throws Exception {
		if (doesElementExist2(properties.getProperty("bulkchk"), 5)) {
			WebElement bulkchk1 = driver.findElement(By.cssSelector(properties.getProperty("bulkchk")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",bulkchk1);
			log.logLine(Testname, false, "Selecting the bulkreport checkbox ");
			Thread.sleep(2000);
			// Finding total no of rows in the grid

			int NumOfRowsInTable = 0;
			List<WebElement> Rows = driver.findElements(By.cssSelector(("div[id='report-viewer-grid'] table tbody tr[role='row']")));
			NumOfRowsInTable = Rows.size();
			log.logLine(Testname, false, "Number of Rows in the table  are "+ NumOfRowsInTable);

			// validating if all the rows are selected or not

			//String[] indchk = new String[NumOfRowsInTable];
			for (int N = 1; N <= NumOfRowsInTable; N++) {
				if (doesElementExist2(properties.getProperty("individualchk"),5)) {
					WebElement indunchk = driver.findElement(By.xpath((".//*[@id='report-viewer-grid']/table/tbody/tr["+ N +"]/td[8]/div/label")));
					Thread.sleep(2000);
					indunchk.click();
					Thread.sleep(1000);
					if (!indunchk.isSelected()) {
						log.logLine(Testname, false,"individual unchecking is possible ");
					} else {
						log.logLine(Testname, true,"individual unchecking is not possible ");
						throw new Exception("individual unchecking is not possible ");
					}
				}
			}
		}
	}


  
public boolean reportViewer(String AccNo, String Testname) throws Exception  {
	
	InputOutputData test = new InputOutputData();		
    test.setInputFile(properties.getProperty("InputDatafile"));
    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
    
    Thread.sleep(1000);
    driver.switchTo().defaultContent();
	WebElement retelm2 = waitForElement(properties.getProperty("selClint1"));		     

	
    if (doesElementExist2(properties.getProperty("Reports"), 5)) {	    
		WebElement reportsmnu = driver.findElement(By.cssSelector(properties.getProperty("Reports")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", reportsmnu);
		PleasewaitDisappear();	
		Thread.sleep(1000);
		log.logLine(Testname, false, "Navigation to Reports page successful");
    } else {
		log.logLine(Testname, true, "Clicking on Reports menu is failed");
		throw new Exception("Clicking on Reports menu is failed");
	}
    
    if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
		WebElement cancelbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", cancelbtn);
		PleasewaitDisappear();
		log.logLine(Testname, false, "Clicking on Cancel button to view the Report");
    } else {
		log.logLine(Testname, true, "Clicking on Cancel button to view the Report is failed");
		throw new Exception("Clicking on Cancel button to view the Report is failed");
	}
    
    if (doesElementExist2(properties.getProperty("RepViewerLnk"), 5)) {	    
		WebElement rptviewlnk = driver.findElement(By.cssSelector(properties.getProperty("RepViewerLnk")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", rptviewlnk);
		PleasewaitDisappear();
		Thread.sleep(2000);
		log.logLine(Testname, false, "Clicking on Report viewer button..");
    } else {
		log.logLine(Testname, true, "Clicking on Report viewer button is failed");
		throw new Exception("Clicking on Report viewer button is failed");
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
				if (lnk.getText().equals(ClntName)) {
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
					if (lnk.getText().equals(ClntName)) {
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
				if (lnk.getText().equals(AppName)) {
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
					if (lnk.getText().equals(AppName)) {
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
		log.logLine(Testname, false, "Clicking on OK button to view the Report");
    } else {
		log.logLine(Testname, true, "Clicking on OK button to view the Report is failed");
		throw new Exception("Clicking on OK button to view the Report is failed");
	}
    
    return true;
}


	public boolean Uploadreport(String AccNo, String Testname, String MultiFilesupld) throws Exception  {
		
		String ReptName = null;		
		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
	    
	    Thread.sleep(1000);		 
		WebElement retelm2 = waitForElement(properties.getProperty("selClint1"));
			
		
	    driver.switchTo().defaultContent();
	    if (doesElementExist2(properties.getProperty("Reports"), 5)) {	    
			WebElement reportsmnu = driver.findElement(By.cssSelector(properties.getProperty("Reports")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", reportsmnu);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicking on Reports menu..");
	    } else {
			log.logLine(Testname, true, "Clicking on Reports menu is failed");
			throw new Exception("Clicking on Reports menu is failed");
		}
	    
	    if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement cancelbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", cancelbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button to view the Report");
	    } else {
			log.logLine(Testname, true, "Clicking on Cancel button to view the Report is failed");
			throw new Exception("Clicking on Cancel button to view the Report is failed");
		}
	    
	    if (doesElementExist2(properties.getProperty("RepViewerLnk"), 5)) {	    
			WebElement rptviewlnk = driver.findElement(By.cssSelector(properties.getProperty("RepViewerLnk")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rptviewlnk);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Report viewer button..");
	    } else {
			log.logLine(Testname, true, "Clicking on Report viewer button is failed");
			throw new Exception("Clicking on Report viewer button is failed");
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
					if (lnk.getText().equalsIgnoreCase(ClntName)) {
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
						if (lnk.getText().equalsIgnoreCase(ClntName)) {
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
					if (lnk.getText().equalsIgnoreCase(AppName)) {
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
						if (lnk.getText().equalsIgnoreCase(AppName)) {
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
			Thread.sleep(3000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on OK button to view the Report");
	    } else {
			log.logLine(Testname, true, "Clicking on OK button to view the Report is failed");
			throw new Exception("Clicking on OK button to view the Report is failed");
		}
	    
	       
	    Thread.sleep(2000);
	    driver.switchTo().frame("iframeContainer");
	    
	    
	    //Verify User permission
		if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTADMIN")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTUSER"))) {					
		
			if (doesElementExist2(properties.getProperty("Uploadbtn"), 5)) {
				
				log.logLine(Testname, false, "Permission Verified: Client Admin and Client User does not have permission to access Upload a report");
				
				return true;
			} else {
				
				log.logLine(Testname, false, "Permission Verified: Client Admin and Client User does not have permission to access Upload a report");
				
				return true;
			}
		} else if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSUPER")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSITE")) || 
				(PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDCLIENT")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDUSER"))) {
			
			if (doesElementExist2(properties.getProperty("Uploadbtn"), 5)) {
				
				log.logLine(Testname, false, "Permission Verified:RRD Super, RRD Site, RRD Client & RRD User have access to Upload a report");
								
			} else {
				
				log.logLine(Testname, true, "RRD Super, RRD Site, RRD Client & RRD User does not have access to Upload a report");
				throw new Exception("RRD Super, RRD Site, RRD Client & RRD User does not have access to Upload a report");
			}
			
		}
	    
	    	    
	    if (doesElementExist2(properties.getProperty("Uploadbtn"), 5)) {	    
			WebElement uplbtn = driver.findElement(By.cssSelector(properties.getProperty("Uploadbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", uplbtn);
			Thread.sleep(500);
			//if ((Initialization.Browser.equalsIgnoreCase("IE")) || (Initialization.Browser.equalsIgnoreCase("ie")) 
				//	|| (Initialization.Browser.equalsIgnoreCase("InternetExplorer")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer"))) {
					//uplbtn.click();
			//}
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Upload button in the Report viewer");
	    } else {
			log.logLine(Testname, true, "Clicking on Upload button in the Report viewer is failed");
			throw new Exception("Clicking on Upload button in the Report viewer is failed");
	    }    
	    
	    
	    if (MultiFilesupld.equalsIgnoreCase("IllegalFile")) {
	    	IllegalFileupload(Testname, AccNo);
	    	driver.switchTo().defaultContent();
	    	return true;
	    }
	    
      
	    String RefCdfld = test.readColumnData("Refcode", sheetname);	    
	    if (doesElementExist2(properties.getProperty("RefFld"), 5)) {	    
			WebElement reffld = driver.findElement(By.cssSelector(properties.getProperty("RefFld")));
			reffld.sendKeys(RefCdfld);			
			log.logLine(Testname, false, "Entering the Ref code into upload report popup");
	    } else {
			log.logLine(Testname, true, "Entering the Ref code into upload report popup is failed");
			throw new Exception("Entering the Ref code into upload report popup is failed");
		}
	    
	    String JobCdfld = test.readColumnData("JobCode", sheetname);	    
	    if (doesElementExist2(properties.getProperty("JobCodeFld"), 5)) {	    
			WebElement reffld = driver.findElement(By.cssSelector(properties.getProperty("JobCodeFld")));
			reffld.sendKeys(JobCdfld);			
			log.logLine(Testname, false, "Entering the Job code into upload report popup");
	    } else if (doesElementExist2(properties.getProperty("JobCodeFld1"), 5)) {	    
			WebElement reffld = driver.findElement(By.cssSelector(properties.getProperty("JobCodeFld1")));
			reffld.sendKeys(JobCdfld);			
			log.logLine(Testname, false, "Entering the Job code into upload report popup");
	    } else {
			log.logLine(Testname, true, "Entering the Job code into upload report popup is failed");
			throw new Exception("Entering the Job code into upload report popup is failed");
		}
	    
	    ReptName = test.readColumnData("ReportName", sheetname);	    
	    if (doesElementExist2(properties.getProperty("ReportNamFld"), 5)) {	    
			WebElement reffld = driver.findElement(By.cssSelector(properties.getProperty("ReportNamFld")));
			reffld.sendKeys(ReptName+AccNo+"0");			
			log.logLine(Testname, false, "Entering the Report Name into upload report popup");
	    } else {
			log.logLine(Testname, true, "Entering the Report Name into upload report popup is failed");
			throw new Exception("Entering the Report Name into upload report popup is failed");
		}
	    
	    String upldfilepath = test.readColumnData("FilePath", sheetname);  
	    if (doesElementExist(properties.getProperty("FileUpload2"), 5)) {
	    	WebElement Uplod = driver.findElement(By.xpath(properties.getProperty("FileUpload2")))	;
	    	Uplod.sendKeys(upldfilepath);
	    	Thread.sleep(1000);
	    }else{
	    	log.logLine(Testname, true, "File upload failed");
			throw new Exception("File upload report popup is failed");
	    }
		    
		if (MultiFilesupld.equalsIgnoreCase("Multiple")) {
			if (doesElementExist2(properties.getProperty("mulfileadd"), 5)) {
		    	WebElement muluplbtn = driver.findElement(By.cssSelector(properties.getProperty("mulfileadd")));
		    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", muluplbtn);
		    	Thread.sleep(2000);
		    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", muluplbtn);
		    	log.logLine(Testname, false, "Clicked on Addfile(+) report row..");
		    } else {
		    	log.logLine(Testname, true, "Clicked on Addfile(+) report row is failed");			
		    }
			multipleuploads(Testname, AccNo);
		}		
	    
	    if (doesElementExist2(properties.getProperty("reportUploadbtn"), 5)) {
	    	WebElement uplbtn = driver.findElement(By.cssSelector(properties.getProperty("reportUploadbtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", uplbtn);
	    	PleasewaitDisappear();
	    	log.logLine(Testname, false, "Upload Report File....");
	    } else {
	    	log.logLine(Testname, true, "Upload Report File is failed");			
	    }	    
	    
	    Thread.sleep(2000);
	    if (MultiFilesupld.equalsIgnoreCase("Multiple")) {
	    	for (int z=0; z<3; z++) {    	
			    if (doesElementExist2(properties.getProperty("VerifyReportNam"), 5)) {	   
			    	List<WebElement> clntdd = driver.findElements(By.cssSelector(properties.getProperty("VerifyReportNam")));
					for (WebElement btn:clntdd) {
						if (btn.getText().equalsIgnoreCase(ReptName+AccNo+z)) {
							log.logLine(Testname, false, "Uploading "+(z+1) +" report file in report viewer is successful");
							break;
						}				
					}
					Thread.sleep(500);			
			    } else {
					log.logLine(Testname, true, "Uploading report file in report viewer is unsuccessful");				
				}
	    	}
	    }
	    
	    driver.switchTo().defaultContent();
	    	    
	    return true;
	}		
	
	
	public void multipleuploads(String Testname, String AccNo) throws Exception {		
		
		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();		
		
		for (int i=1; i<3; i++) {

		    	    
		    String RefCdfld = test.readColumnData("Refcode"+Integer.toString(i), sheetname);	    
		    if (doesElementExist2("input[id='["+i+"].OrderNumber']", 5)) {	    
				WebElement reffld = driver.findElement(By.cssSelector("input[id='["+i+"].OrderNumber']"));
				reffld.sendKeys(RefCdfld);			
				log.logLine(Testname, false, "Entering the Ref code into upload report popup");
		    } else {
				log.logLine(Testname, true, "Entering the Ref code into upload report popup is failed");
				throw new Exception("Entering the Ref code into upload report popup is failed");
			}
		    
		    String JobCdfld = test.readColumnData("JobCode"+Integer.toString(i), sheetname);	    
		    if (doesElementExist2("input[id='["+i+"].JobCode']", 5)) {	    
				WebElement reffld = driver.findElement(By.cssSelector("input[id='["+i+"].JobCode']"));
				reffld.sendKeys(JobCdfld);			
				log.logLine(Testname, false, "Entering the Job code into upload report popup");
		    } else {
				log.logLine(Testname, true, "Entering the Job code into upload report popup is failed");
				throw new Exception("Entering the Job code into upload report popup is failed");
			}
		    
		    String ReptName = test.readColumnData("ReportName", sheetname);	    
		    if (doesElementExist2("input[id='["+i+"].ReportName']", 5)) {	    
				WebElement reffld = driver.findElement(By.cssSelector("input[id='["+i+"].ReportName']"));
				reffld.sendKeys(ReptName+AccNo+i);			
				log.logLine(Testname, false, "Entering the Report Name into upload report popup");
		    } else {
				log.logLine(Testname, true, "Entering the Report Name into upload report popup is failed");
				throw new Exception("Entering the Report Name into upload report popup is failed");
			}
		    
		    String upldfilepath = test.readColumnData("FilePath"+Integer.toString(i), sheetname);        
		    driver.findElement(By.cssSelector("input[id='["+i+"].UploadFile']")).sendKeys(upldfilepath);
		    Thread.sleep(1000);	    
		    
		}
	}
	
	public void IllegalFileupload(String Testname, String AccNo) throws Exception {
		
		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
		
		if (doesElementExist2(properties.getProperty("CancelUploadbtn"), 5)) {	    
			WebElement uplbtn = driver.findElement(By.cssSelector(properties.getProperty("CancelUploadbtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", uplbtn);
			Thread.sleep(500);			
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in the Upload file popup");
	    } else {
			log.logLine(Testname, true, "Clicking on Cancel button in the Upload file popup is failed");
			throw new Exception("Clicking on Cancel button in the Upload file popup is failed");
	    }
		
		
		if (doesElementExist2(properties.getProperty("Uploadbtn"), 5)) {	    
			WebElement uplbtn = driver.findElement(By.cssSelector(properties.getProperty("Uploadbtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", uplbtn);

			Thread.sleep(500);
			if ((Initialization.Browser.equalsIgnoreCase("IE")) || (Initialization.Browser.equalsIgnoreCase("ie")) 
					|| (Initialization.Browser.equalsIgnoreCase("InternetExplorer")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer"))) {
					uplbtn.click();
			}
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Upload button in the Report viewer");
	    } else {
			log.logLine(Testname, true, "Clicking on Upload button in the Report viewer is failed");
			throw new Exception("Clicking on Upload button in the Report viewer is failed");
	    }			
		
		String upldillgalfile = test.readColumnData("IllegalFile", sheetname);        
	    driver.findElement(By.xpath(properties.getProperty("Fileupload2"))).sendKeys(upldillgalfile);
	    Thread.sleep(1000);
		
	    if (doesElementExist2(properties.getProperty("reportUploadbtn"), 5)) {
	    	WebElement uplbtn = driver.findElement(By.cssSelector(properties.getProperty("reportUploadbtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", uplbtn);
	    	PleasewaitDisappear();
	    	log.logLine(Testname, false, "Upload Report File....");
	    } else {
	    	log.logLine(Testname, true, "Upload Report File is failed");			
	    }
	    
	    if (doesElementExist2(properties.getProperty("illegalfilemsg"), 5)) {	    	
	    	log.logLine(Testname, false, "Verification of upload illegal file is successful");
	    	
	    	if (doesElementExist2(properties.getProperty("CancelUploadbtn"), 5)) {	    
				WebElement uplbtn = driver.findElement(By.cssSelector(properties.getProperty("CancelUploadbtn")));
		    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", uplbtn);
				Thread.sleep(500);			
				PleasewaitDisappear();
				log.logLine(Testname, false, "Clicking on Cancel button in the Upload file popup");
		    }
	    } else {
	    	log.logLine(Testname, true, "Verification of upload illegal file is unsuccessful");			
	    }	
		
	    if (doesElementExist2(properties.getProperty("Uploadbtn"), 5)) {	    
			WebElement uplbtn = driver.findElement(By.cssSelector(properties.getProperty("Uploadbtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", uplbtn);
	    	
			Thread.sleep(500);
			if ((Initialization.Browser.equalsIgnoreCase("IE")) || (Initialization.Browser.equalsIgnoreCase("ie")) 
					|| (Initialization.Browser.equalsIgnoreCase("InternetExplorer")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer"))) {
					uplbtn.click();
			}
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Upload button in the Report writer");
	    } else {
			log.logLine(Testname, true, "Clicking on Upload button in the Report writer is failed");
			throw new Exception("Clicking on Upload button in the Report writer is failed");
	    }
		
		String upldfilepath = test.readColumnData("FilePath", sheetname);  
		if ((Initialization.Browser.equalsIgnoreCase("IE")) || (Initialization.Browser.equalsIgnoreCase("ie")) 
				|| (Initialization.Browser.equalsIgnoreCase("InternetExplorer")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer"))) {
			driver.findElement(By.xpath(properties.getProperty("Fileupload2"))).sendKeys(upldfilepath);
		} else {
			driver.findElement(By.xpath(properties.getProperty("FileUploadXpath"))).sendKeys(upldfilepath);
		}
	    
	    Thread.sleep(1000);    		
					    
	    String RefCdfld = test.readColumnData("Refcode", sheetname);	    
	    if (doesElementExist2(properties.getProperty("RefFld"), 5)) {	    
			WebElement reffld = driver.findElement(By.cssSelector(properties.getProperty("RefFld")));
			reffld.sendKeys(RefCdfld);			
			log.logLine(Testname, false, "Entering the Ref code into upload report popup");
	    } else {
			log.logLine(Testname, true, "Entering the Ref code into upload report popup is failed");
			throw new Exception("Entering the Ref code into upload report popup is failed");
		}
	    
	    String JobCdfld = test.readColumnData("JobCode", sheetname);	    
	    if (doesElementExist2(properties.getProperty("JobCodeFld"), 5)) {	    
			WebElement reffld = driver.findElement(By.cssSelector(properties.getProperty("JobCodeFld")));
			reffld.sendKeys(JobCdfld);			
			log.logLine(Testname, false, "Entering the Job code into upload report popup");
	    } else {
			log.logLine(Testname, true, "Entering the Job code into upload report popup is failed");
			throw new Exception("Entering the Job code into upload report popup is failed");
		}
	    
	    String ReptName = test.readColumnData("ReportName", sheetname);	    
	    if (doesElementExist2(properties.getProperty("ReportNamFld"), 5)) {	    
			WebElement reffld = driver.findElement(By.cssSelector(properties.getProperty("ReportNamFld")));
			reffld.sendKeys(ReptName+AccNo);			
			log.logLine(Testname, false, "Entering the Report Name into upload report popup");
	    } else {
			log.logLine(Testname, true, "Entering the Report Name into upload report popup is failed");
			throw new Exception("Entering the Report Name into upload report popup is failed");
		}	    
	    
	    if (doesElementExist2(properties.getProperty("UploadFileDialog"), 5)) {	    	
	    	log.logLine(Testname, false, "Upload information validation is successful");
	    } else {
	    	log.logLine(Testname, true, "Upload information validation is unsuccessful");			
	    }
	    
	    if (doesElementExist2(properties.getProperty("CancelUploadbtn"), 5)) {	    
			WebElement uplbtn = driver.findElement(By.cssSelector(properties.getProperty("CancelUploadbtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", uplbtn);
	    	
			Thread.sleep(500);			
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in the Upload file popup");
	    } else {
			log.logLine(Testname, true, "Clicking on Cancel button in the Upload file popup is failed");
			throw new Exception("Clicking on Cancel button in the Upload file popup is failed");
	    }		
		
	}

}

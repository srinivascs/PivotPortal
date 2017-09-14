package pivotModules;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import launchAuto.Initialization;
import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class JobTrackingQuickSearch extends Page{

	public static String row = "tr";
	public static String stsType1;
	public static String stsType2;
	public static String stsType3;
	public static String stsType4;
	public static String stsType5;
	public static String FrmDte;
	public static String todaysDate;
	public static String actval, actval1;
	public static String refernum;
	
	public JobTrackingQuickSearch(WebDriver driver, Log log) throws InvalidFormatException, IOException {
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

	if (doesElementExist2(properties.getProperty("JobTracking"), 5)) {
    	WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("JobTracking")));
    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);	    	 
    	 Thread.sleep(2000);
    	 PleasewaitDisappear();
    	log.logLine(Testname, false, "Click on Job Tracking Module is Successful");
    } else {
    	log.logLine(Testname, true, "Click on Job Tracking Module is failed");
    	driver.switchTo().defaultContent();    
    	throw new Exception("Click on Job Tracking Module is failed");
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
 
    
    return true;
	}



public boolean QuickSearch(String AccNo,String Testname) throws Exception {
		
		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
	    
	    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		Date date = new Date();	
		todaysDate = dateFormat.format(date);
    
		String Text=test.readColumnData("TextName", sheetname);
		
		/*String stsType1=test.readColumnData("StatusType1", sheetname);
		String stsType2=test.readColumnData("StatusType2", sheetname);
		String stsType3=test.readColumnData("StatusType3", sheetname);
		String stsType4=test.readColumnData("StatusType4", sheetname);
		*/
		//String stsType5=test.readColumnData("StatusType5", sheetname);
		
		
    
    Thread.sleep(8000);
    driver.switchTo().frame("iframeContainer");
    
    if (doesElementExist2(properties.getProperty("Recntactybtn"), 5)) {	    
		WebElement rectact = driver.findElement(By.cssSelector(properties.getProperty("Recntactybtn")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", rectact);
		PleasewaitDisappear();
		Thread.sleep(4000);
		log.logLine(Testname, false, "Clicked on recent Activity button in JobTracking page");	

	} else {
		log.logLine(Testname, true, "Clicked on recent Activity button in JobTracking page is failed");
		driver.switchTo().defaultContent();
		throw new Exception("Clicked on recent Activity button in JobTracking page is failed");
	}
 
  
	stsType5 = test.readColumnData("StatusType5", sheetname);
    Quick_Search(Testname, "StatusType5", stsType5, "","");
    
	stsType1 = test.readColumnData("StatusType1", sheetname);
    Quick_Search(Testname, "StatusType1", stsType1, "","");
    
    Thread.sleep(2000);
    stsType2 = test.readColumnData("StatusType2", sheetname);
    Quick_Search(Testname, "StatusType2", stsType2, "","");
    
    stsType3 = test.readColumnData("StatusType3", sheetname);
    Quick_Search(Testname, "StatusType3", stsType3, "","");
    
    stsType4 = test.readColumnData("StatusType4", sheetname);
    Quick_Search(Testname, "StatusType4", stsType4, "","");
   
    
    FrmDte = test.readColumnData("FromDate", sheetname);
   
    Quick_Search(Testname, "FromToDate",stsType5, FrmDte, todaysDate);
    
 //   driver.switchTo().frame("iframeContainer");    
    
    Thread.sleep(5000);
    if (doesElementExist(properties.getProperty("RefNumber"), 5)) {
    	WebElement refnum = driver.findElement(By.xpath(properties.getProperty("RefNumber")));
    	 refernum=refnum.getText();    	
	   	log.logLine(Testname, false, "Getting the value of the reference number");
	} else {
	    log.logLine(Testname, true, "Getting the value of the reference number is failed");
	    throw new Exception("Getting the value of the reference number is failed");
	}
    
    if (doesElementExist(properties.getProperty("ProcessDate"), 5)) {
    	WebElement prcdte = driver.findElement(By.xpath(properties.getProperty("ProcessDate")));
    	 String processdate=prcdte.getText(); 
    	 String procdte[]=processdate.split(" ");
    	 actval = procdte[0].trim();
	   	log.logLine(Testname, false, "Getting the value of the Process Date");
	} else {
	    log.logLine(Testname, true, "Getting the value of the ProcessDate is failed");
	    throw new Exception("Getting the value of the ProcessDate is failed");
	}
    
    Quick_Search(Testname, "SrchProcDate",stsType5, actval, actval);
    Thread.sleep(5000);
    
    
    
    
    
	/*
	//Selecting the Date Type as Expected Mail Date
	
	 if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
	    	WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
	    	Thread.sleep(1000);
		   	PleasewaitDisappear();
		   	log.logLine(Testname, false, "Clicking on Search button");
		} else {
		    log.logLine(Testname, true, "Clicking on Search is failed");
		    throw new Exception("Clicking on Search is failed");
		}
	
	
	
	
	if (doesElementExist2(properties.getProperty("DateType"), 5)) {	   
    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("DateType")));
    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
    	
		log.logLine(Testname, false, "Clicking on Date Type dropdown..");
		
		if (doesElementExist2(properties.getProperty("StatusType"), 5)) {
			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Dtetyplst")));
			for (WebElement lnk:selopts) {
				if (lnk.getText().equals("Expected Mail Date")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Selecting the Date Type Name from the dropdown..");							
					break;
				}				
			}
			
		} else {
			log.logLine(Testname, true, "Date Type options are not displayed");
			throw new Exception("Date Type options are not displayed");
		}
    }
	
	
	if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
    	WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
    	Thread.sleep(1000);
	   	PleasewaitDisappear();
	   	log.logLine(Testname, false, "Clicking on Serach button");
	} else {
	    log.logLine(Testname, true, "Clicking on Serach button is failed");
	    throw new Exception("Clicking on Serach button is failed");
	}
	
	String[] Sort6 = new String[20];
	row = "tr";
	List<WebElement> DataCnt6= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));
	
	if(doesElementExist2(properties.getProperty("ExpectedMaildte"), 5)){
		for(int i = 0; i < DataCnt6.size(); i++) {
			Sort6[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row+" td+td+td+td[role='gridcell']")).getText();
			row = row + "+tr";
			}
		log.logLine(Testname, false, "Iterating through the Rows....Expected Mail Dates Fall under the date range selected");
	}
	
	
		 
	//Selecting the Date Type as Actual Mail Date
	
	 if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
	    	WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
	    	Thread.sleep(1000);
		   	PleasewaitDisappear();
		   	log.logLine(Testname, false, "Clicking on Search button");
		} else {
		    log.logLine(Testname, true, "Clicking on Search is failed");
		    throw new Exception("Clicking on Search is failed");
		}
	
	
	
	
	if (doesElementExist2(properties.getProperty("DateType"), 5)) {	   
    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("DateType")));
    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
    	
		log.logLine(Testname, false, "Clicking on Date Type dropdown..");
		
		if (doesElementExist2(properties.getProperty("StatusType"), 5)) {
			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Dtetyplst")));
			for (WebElement lnk:selopts) {
				if (lnk.getText().equals("Actual Mail Date")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Selecting the Date Type Name from the dropdown..");							
					break;
				}				
			}
			
		} else {
			log.logLine(Testname, true, "Date Type options are not displayed");
			throw new Exception("Date Type options are not displayed");
		}
    }
	
	
	if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
    	WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
    	Thread.sleep(1000);
	   	PleasewaitDisappear();
	   	log.logLine(Testname, false, "Clicking on Serach button");
	} else {
	    log.logLine(Testname, true, "Clicking on Serach button is failed");
	    throw new Exception("Clicking on Serach button is failed");
	}
	
	String[] Sort7 = new String[20];
	row = "tr";
	List<WebElement> DataCnt7= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));
	
	if(doesElementExist2(properties.getProperty("ActualMaildte"), 5)){
		for(int i = 0; i < DataCnt7.size(); i++) {
			Sort7[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row+" td+td+td+td+td+td+td+td+td+td+td[role='gridcell']")).getText();
			row = row + "+tr";
			}
		log.logLine(Testname, false, "Iterating through the Rows....Actual Mail Dates Fall under the date range selected");
	}
	
	
	// Basic Search-----Selecting Basic search type as LOGAN
	
	if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
    	WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
    	Thread.sleep(1000);
	   	PleasewaitDisappear();
	   	log.logLine(Testname, false, "Clicking on Search button");
	} else {
	    log.logLine(Testname, true, "Clicking on Search is failed");
	    throw new Exception("Clicking on Search is failed");
	}




	if (doesElementExist2(properties.getProperty("Basicsrchtype"), 5)) {	   
		WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Basicsrchtype")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
		
		log.logLine(Testname, false, "Clicking on Basic Search Type dropdown..");
		
		if (doesElementExist2(properties.getProperty("Basicsrchoptn"), 5)) {
			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Basicsrchoptn")));
			for (WebElement lnk:selopts) {
				if (lnk.getText().equals("Logan")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Selecting the Basic Search option from the dropdown..");							
					break;
				}				
			}
			
		} else {
			log.logLine(Testname, true, "Basic Search options are not displayed");
			throw new Exception("Basic Search options are not displayed");
		}
	}
	
	
	if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
		WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
		Thread.sleep(1000);
	   	PleasewaitDisappear();
	   	log.logLine(Testname, false, "Clicking on Serach button");
	} else {
	    log.logLine(Testname, true, "Clicking on Serach button is failed");
	    throw new Exception("Clicking on Serach button is failed");
	}
	
	String[] Sort8 = new String[20];
	row = "tr";
	List<WebElement> DataCnt8= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));
	
	if(doesElementExist2(properties.getProperty("RRDPlant"), 5)){
		for(int i = 0; i < DataCnt8.size(); i++) {
			Sort8[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row+" td+td+td+td+td+td+td[role='gridcell']")).getText();
			row = row + "+tr";
			}
		log.logLine(Testname, false, "Iterating through the Rows....Reading the RRD Plant As LOGAN");
	}
	
	
	// Selecting RRD Order Number
	

	if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
    	WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
    	Thread.sleep(1000);
	   	PleasewaitDisappear();
	   	log.logLine(Testname, false, "Clicking on Search button");
	} else {
	    log.logLine(Testname, true, "Clicking on Search is failed");
	    throw new Exception("Clicking on Search is failed");
	}
	
	
	if (doesElementExist2(properties.getProperty("Basicsrchtype"), 5)) {	   
		WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Basicsrchtype")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
		
		log.logLine(Testname, false, "Clicking on Basic Search Type dropdown..");
		
		if (doesElementExist2(properties.getProperty("Basicsrchoptn"), 5)) {
			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Basicsrchoptn")));
			for (WebElement lnk:selopts) {
				if (lnk.getText().equals("All")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Selecting the Basic Search option from the dropdown..");							
					break;
				}				
			}
			
		} else {
			log.logLine(Testname, true, "Basic Search options are not displayed");
			throw new Exception("Basic Search options are not displayed");
		}
	}
	
	
	
	if (doesElementExist2(properties.getProperty("Advanceoptions"), 5)) {	   
		WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Advanceoptions")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
		
		log.logLine(Testname, false, "Clicking on Advance Search Options Search Type dropdown..");
		
	
		if (doesElementExist2(properties.getProperty("Advnceoptnslst"), 5)) {
			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Advnceoptnslst")));
			for (WebElement lnk:selopts) {
				if (lnk.getText().equals("RRD Order #")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Selecting the Advance Search Option Type from the dropdown..");							
					break;
				}				
			}
			
		} else {
			log.logLine(Testname, true, "Advance Search options are not displayed");
			throw new Exception("Advance Search options are not displayed");
		}
	}
	

	if (doesElementExist2(properties.getProperty("TextField"), 5)) {
    	WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("TextField")));
    	Txt.clear();
    	Txt.sendKeys(Text);
	   	log.logLine(Testname, false, "Entering the RRD Order Number  "+Text+"  on Text Field");
	} else {
	    log.logLine(Testname, true, "Entering the RRD Order Number is Failed");
	    throw new Exception("Entering the RRD Order Number is Failed");
	}
	
	 
	
	if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
		WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
		Thread.sleep(1000);
	   	PleasewaitDisappear();
	   	log.logLine(Testname, false, "Clicking on Serach button");
	} else {
	    log.logLine(Testname, true, "Clicking on Serach button is failed");
	    throw new Exception("Clicking on Serach button is failed");
	}
	
	String[] Sort9 = new String[20];
	row = "tr";
	List<WebElement> DataCnt9= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));
	
	if(doesElementExist2(properties.getProperty("RRDNumber"), 5)){
		for(int i = 0; i < DataCnt9.size(); i++) {
			Sort9[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row+" td+td+td+td+td+td[role='gridcell']")).getText();
			Sort9[i].equals(Text);
			log.logLine(Testname, false, "RRDNumber "+Text+ "  Matches with the  "  +Sort9[i]);
			row = row + "+tr";
			}
		}
		*/

	return true;
	}

     
public boolean Quick_Search(String Testname, String SrchType, String testdata1, String testdata2, String testdata3) throws Exception {
	
	if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
    	WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
    	Thread.sleep(4000);
	   	PleasewaitDisappear();
	   	log.logLine(Testname, false, "Clicking on Search button");
	} else {
	    log.logLine(Testname, true, "Clicking on Search is failed");
	    throw new Exception("Clicking on Search is failed");
	}
    
   	 
    if (doesElementExist2(properties.getProperty("SrchType"), 5)) {	   
    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("SrchType")));
    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);    	  
		log.logLine(Testname, false, "Clicking on SrchType dropdown..");
		
		if (doesElementExist2(properties.getProperty("TypeQuick"), 5)) {
			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("TypeQuick")));
			for (WebElement lnk:selopts) {
				if (lnk.getText().equals("Quick (Order)")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					 /*Actions action = new Actions(driver);
			    	   action.moveByOffset(10, 0).doubleClick();*/
					Thread.sleep(4000);
					log.logLine(Testname, false, "Selecting the Search Type Name from the dropdown..");							
					break;
				}				
			}
			
		} else {
			log.logLine(Testname, true, "Search Type options are not displayed");
			throw new Exception("Search Type options are not displayed");
		}
    }
    
    
    if (doesElementExist2(properties.getProperty("DateType"), 5)) {	   
    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("DateType")));
    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
    	
		log.logLine(Testname, false, "Clicking on Date Type dropdown..");
		
		if (doesElementExist2(properties.getProperty("Dtetyplst"), 5)) {
			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Dtetyplst")));
			for (WebElement lnk:selopts) {
				if (lnk.getText().equals("Data Processed by RRD Facility Date")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(4000);
					 /*Actions action = new Actions(driver);
			    	   action.moveByOffset(10, 0).doubleClick();*/
					log.logLine(Testname, false, "Selecting the Date Type Name from the dropdown..");							
					break;
				}				
			}
			
		} else {
			log.logLine(Testname, true, "Date Type options are not displayed");
			throw new Exception("Date Type options are not displayed");
		}
    }
    
    if (doesElementExist2(properties.getProperty("Basicsrchvalue1"), 5)) {	   
		WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Basicsrchvalue1")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
		
		log.logLine(Testname, false, "Clicking on Basic Search Type dropdown..");
		
		if (doesElementExist2(properties.getProperty("BasicsrchtypeOptn"), 5)) {
			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("BasicsrchtypeOptn")));
			for (WebElement lnk:selopts) {
				if (lnk.getText().equals("RRD Plant")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					/* Actions action = new Actions(driver);
			    	   action.moveByOffset(10, 0).doubleClick();*/
					Thread.sleep(4000);
					log.logLine(Testname, false, "Selecting the Basic Search option from the dropdown..");							
					break;
				}				
			}
			
		} else {
			log.logLine(Testname, true, "Basic Search options are not displayed");
			throw new Exception("Basic Search options are not displayed");
		}
	}
    
    
    
    
    if (doesElementExist2(properties.getProperty("BasicsrchValue"), 5)) {	   
		WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("BasicsrchValue")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
		
		log.logLine(Testname, false, "Clicking on Basic Search Type dropdown..");
		
		if (doesElementExist2(properties.getProperty("BasicsrchValueOptn"), 5)) {
			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("BasicsrchValueOptn")));
			for (WebElement lnk:selopts) {
				if (lnk.getText().equals("Logan")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					 /*Actions action = new Actions(driver);
			    	   action.moveByOffset(10, 0).doubleClick();*/
					Thread.sleep(4000);
					log.logLine(Testname, false, "Selecting the Basic Search option from the dropdown..");							
					break;
				}				
			}
			
		} else {
			log.logLine(Testname, true, "Basic Search options are not displayed");
			throw new Exception("Basic Search options are not displayed");
		}
	}
		switch (SrchType) {
		
            case "StatusType1":
            	if (doesElementExist2(properties.getProperty("Status"), 5)) {	   
                	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Status")));
                	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
                	Thread.sleep(5000);
            		log.logLine(Testname, false, "Clicking on Status dropdown..");
            		
            		if (doesElementExist2(properties.getProperty("StatusType"), 5)) {
            			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("StatusType")));
            			for (WebElement lnk:selopts) {
            				if (lnk.getText().equals(testdata1)) {
            					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
            					Thread.sleep(4000);
            					log.logLine(Testname, false, "Selecting the Status Type Name from the dropdown..");							
            					break;
            				}				
            			}
            			
            		} else {
            			log.logLine(Testname, true, "Status Type options are not displayed");
            			throw new Exception("Status Type options are not displayed");
            		}
            		
            		if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
            	    	WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
            	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
            	    	Thread.sleep(4000);
            		   	PleasewaitDisappear();
            		   	log.logLine(Testname, false, "Clicking on Serach button");
            		} else {
            		    log.logLine(Testname, true, "Clicking on Serach button is failed");
            		    throw new Exception("Clicking on Serach button is failed");
            		}
            		
            		
                    if (doesElementExist2(properties.getProperty("Records"), 5)) {
                    	log.logLine(Testname, false, "Quick search - Successfully displayed the records for entered text in srchType field");		    		    	
                    } else {
                	    	log.logLine(Testname, false, "Quick search - No records displayed for entered text in srchType field");
                    }
                    
                    String[] Sort1 = new String[20];
                    row = "tr";
                    List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));
                		
                		if(doesElementExist2(properties.getProperty("PrintStatus"), 5)){
                			for(int i = 0; i < DataCnt.size(); i++) {
                				Sort1[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row+" td+td+td[role='gridcell']")).getText();
                				Thread.sleep(5000);
                				if(Sort1[i].equalsIgnoreCase(testdata1)){
                					log.logLine(Testname, false, "Status matches with "+Sort1[i]);
                				}else{
                					log.logLine(Testname, false, "Status does not matches with "+Sort1[i]);
                					break;
                				}
                				row = row + "+tr";
                				log.logLine(Testname, false, "Iterating through the Rows....Rows Have the Status as "+Sort1[i]);
                				}
                			
                		}
                    
                    
            		
                }            	
                break;
                
            case "StatusType2":
            	if (doesElementExist2(properties.getProperty("Status"), 5)) {	   
                	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Status")));
                	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
                	
            		log.logLine(Testname, false, "Clicking on Status dropdown..");
            		
            		if (doesElementExist2(properties.getProperty("StatusType"), 5)) {
            			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("StatusType")));
            			for (WebElement lnk:selopts) {
            				if (lnk.getText().equals(testdata1)) {
            					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
            					Thread.sleep(4000);
            					log.logLine(Testname, false, "Selecting the Status Type Name from the dropdown..");							
            					break;
            				}				
            			}
            			
            		} else {
            			log.logLine(Testname, true, "Status Type options are not displayed");
            			throw new Exception("Status Type options are not displayed");
            		}
            		
            		if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
            	    	WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
            	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
            	    	Thread.sleep(5000);
            		   	PleasewaitDisappear();
            		   	log.logLine(Testname, false, "Clicking on Serach button");
            		} else {
            		    log.logLine(Testname, true, "Clicking on Serach button is failed");
            		    throw new Exception("Clicking on Serach button is failed");
            		}
            		
            		
                    if (doesElementExist2(properties.getProperty("Records"), 5)) {
                    	log.logLine(Testname, false, "Quick search - Successfully displayed the records for entered text in srchType field");		    		    	
                    } else {
                	    	log.logLine(Testname, false, "Quick search - No records displayed for entered text in srchType field");
                    }
                    
                    String[] Sort1 = new String[20];
                    row = "tr";
                    List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));
                		
                		if(doesElementExist2(properties.getProperty("PrintStatus"), 5)){
                			for(int i = 0; i < DataCnt.size(); i++) {
                				Sort1[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row+" td+td+td[role='gridcell']")).getText();
                				Thread.sleep(5000);
                				if(Sort1[i].equalsIgnoreCase(testdata1)){
                					log.logLine(Testname, false, "Status matches with "+Sort1[i]);
                				}else{
                					log.logLine(Testname, true, "Status does not matches with "+Sort1[i]);
                					break;
                				}
                				row = row + "+tr";
                				log.logLine(Testname, false, "Iterating through the Rows....Rows Have the Status as "+Sort1[i]);
                				}
                			
                		}
                }            	
                break;
                
            case "StatusType3":
            	if (doesElementExist2(properties.getProperty("Status"), 5)) {	   
                	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Status")));
                	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
                	Thread.sleep(2000);
            		log.logLine(Testname, false, "Clicking on Status dropdown..");
            		
            		if (doesElementExist2(properties.getProperty("StatusType"), 5)) {
            			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("StatusType")));
            			for (WebElement lnk:selopts) {
            				if (lnk.getText().equals(testdata1)) {
            					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
            					Thread.sleep(4000);
            					log.logLine(Testname, false, "Selecting the Status Type Name from the dropdown..");							
            					break;
            				}				
            			}
            			
            		} else {
            			log.logLine(Testname, true, "Status Type options are not displayed");
            			throw new Exception("Status Type options are not displayed");
            		}
            		
            		if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
            	    	WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
            	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
            	    	Thread.sleep(4000);
            		   	PleasewaitDisappear();
            		   	log.logLine(Testname, false, "Clicking on Serach button");
            		} else {
            		    log.logLine(Testname, true, "Clicking on Serach button is failed");
            		    throw new Exception("Clicking on Serach button is failed");
            		}
            		
            		
                    if (doesElementExist2(properties.getProperty("Records"), 5)) {
                    	log.logLine(Testname, false, "Quick search - Successfully displayed the records for entered text in srchType field");		    		    	
                    } else {
                	    	log.logLine(Testname, false, "Quick search - No records displayed for entered text in srchType field");
                    }
                    
                    String[] Sort1 = new String[20];
                    row = "tr";
                    List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));
                    Thread.sleep(5000);
                		if(doesElementExist2(properties.getProperty("PrintStatus"), 5)){
                			for(int i = 0; i < DataCnt.size(); i++) {
                				Sort1[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row+" td+td+td[role='gridcell']")).getText();
                				if(Sort1[i].equalsIgnoreCase(testdata1)){
                					log.logLine(Testname, false, "Status matches with "+Sort1[i]);
                				}else{
                					log.logLine(Testname, true, "Status does not matches with "+Sort1[i]);
                					break;
                				}
                				row = row + "+tr";
                				log.logLine(Testname, false, "Iterating through the Rows....Rows Have the Status as "+Sort1[i]);
                				}
                			
                		}
                }            	
                break;
                
            case "StatusType4":
            	if (doesElementExist2(properties.getProperty("Status"), 5)) {	   
                	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Status")));
                	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
                	Thread.sleep(2000);
            		log.logLine(Testname, false, "Clicking on Status dropdown..");
            		
            		if (doesElementExist2(properties.getProperty("StatusType"), 5)) {
            			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("StatusType")));
            			for (WebElement lnk:selopts) {
            				if (lnk.getText().equals(testdata1)) {
            					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
            					Thread.sleep(4000);
            					log.logLine(Testname, false, "Selecting the Status Type Name from the dropdown..");							
            					break;
            				}				
            			}
            			
            		} else {
            			log.logLine(Testname, true, "Status Type options are not displayed");
            			throw new Exception("Status Type options are not displayed");
            		}
            		
            		if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
            	    	WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
            	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
            	    	Thread.sleep(4000);
            		   	PleasewaitDisappear();
            		   	log.logLine(Testname, false, "Clicking on Serach button");
            		} else {
            		    log.logLine(Testname, true, "Clicking on Serach button is failed");
            		    throw new Exception("Clicking on Serach button is failed");
            		}
            		
            		
                    if (doesElementExist2(properties.getProperty("Records"), 5)) {
                    	log.logLine(Testname, false, "Quick search - Successfully displayed the records for entered text in srchType field");		    		    	
                    } else {
                	    	log.logLine(Testname, false, "Quick search - No records displayed for entered text in srchType field");
                    }
                    
                    String[] Sort1 = new String[20];
                    row = "tr";
                    List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));
                    Thread.sleep(5000);
                		if(doesElementExist2(properties.getProperty("PrintStatus"), 5)){
                			for(int i = 0; i < DataCnt.size(); i++) {
                				Sort1[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row+" td+td+td[role='gridcell']")).getText();
                				if(Sort1[i].equalsIgnoreCase(testdata1)){
                					log.logLine(Testname, false, "Status matches with "+Sort1[i]);
                				}else{
                					log.logLine(Testname, true, "Status does not matches with "+Sort1[i]);
                					break;
                				}
                				row = row + "+tr";
                				log.logLine(Testname, false, "Iterating through the Rows....Rows Have the Status as "+Sort1[i]);
                				}
                			
                		}
                }            	
                break;
                
            case "StatusType5":
            	if (doesElementExist2(properties.getProperty("Status"), 5)) {	   
                	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Status")));
                	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
                	Thread.sleep(2000);
            		log.logLine(Testname, false, "Clicking on Status dropdown..");
            		
            		if (doesElementExist2(properties.getProperty("StatusType"), 5)) {
            			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("StatusType")));
            			for (WebElement lnk:selopts) {
            				if (lnk.getText().equals(testdata1)) {
            					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
            					Thread.sleep(4000);
            					log.logLine(Testname, false, "Selecting the Status Type Name from the dropdown..");							
            					break;
            				}				
            			}
            			
            		} else {
            			log.logLine(Testname, true, "Status Type options are not displayed");
            			throw new Exception("Status Type options are not displayed");
            		}
            		
            		if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
            	    	WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
            	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
            	    	Thread.sleep(4000);
            		   	PleasewaitDisappear();
            		   	log.logLine(Testname, false, "Clicking on Serach button");
            		} else {
            		    log.logLine(Testname, true, "Clicking on Serach button is failed");
            		    throw new Exception("Clicking on Serach button is failed");
            		}
            		
            		Thread.sleep(5000);
                    if (doesElementExist2(properties.getProperty("Records"), 5)) {
                    	log.logLine(Testname, false, "Quick search - Successfully displayed the records for entered text in srchType field");		    		    	
                    } else {
                	    	log.logLine(Testname, false, "Quick search - No records displayed for entered text in srchType field");
                    }
                    
                   
                }            	
                break;
                
                
            case "FromToDate":
            	if (doesElementExist2(properties.getProperty("Status"), 5)) {	   
                	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Status")));
                	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
                	Thread.sleep(5000);
            		log.logLine(Testname, false, "Clicking on Status dropdown..");
            		
            		if (doesElementExist2(properties.getProperty("StatusType"), 5)) {
            			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("StatusType")));
            			for (WebElement lnk:selopts) {
            				if (lnk.getText().equals(testdata1)) {
            					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
            					Thread.sleep(4000);
            					log.logLine(Testname, false, "Selecting the Status Type Name from the dropdown..");							
            					break;
            				}				
            			}
            			
            		} else {
            			log.logLine(Testname, true, "Status Type options are not displayed");
            			throw new Exception("Status Type options are not displayed");
            		}
            		
            		
            		if (doesElementExist2(properties.getProperty("FromDate"), 5)) {	  
            		    WebElement DteFromfld = driver.findElement(By.cssSelector(properties.getProperty("FromDate")));
            			DteFromfld.clear();
            			Thread.sleep(2000);
            			DteFromfld.sendKeys(FrmDte);
            			log.logLine(Testname, false, "Entering the from date value in advanced search");
            			
            			WebElement DteTofld = driver.findElement(By.cssSelector(properties.getProperty("ToDate")));
            			DteTofld.clear();
            			Thread.sleep(2000);
            			DteTofld.sendKeys(todaysDate);
            			log.logLine(Testname, false, "Entering the To date value in advanced search");
            	    }
            		
            		if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
            	    	WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
            	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
            	    	Thread.sleep(4000);
            		   	PleasewaitDisappear();
            		   	log.logLine(Testname, false, "Clicking on Serach button");
            		} else {
            		    log.logLine(Testname, true, "Clicking on Serach button is failed");
            		    throw new Exception("Clicking on Serach button is failed");
            		}
            		
            		Thread.sleep(5000);
                    if (doesElementExist2(properties.getProperty("Records"), 5)) {
                    	log.logLine(Testname, false, "Quick search - Successfully displayed the records for entered text in srchType field");		    		    	
                    } else {
                	    	log.logLine(Testname, false, "Quick search - No records displayed for entered text in srchType field");
                    }
                    
                   
                }            	
                break;
                
            case "SrchProcDate":
            	if (doesElementExist2(properties.getProperty("Status"), 5)) {	   
                	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Status")));
                	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
                	Thread.sleep(5000);
            		log.logLine(Testname, false, "Clicking on Status dropdown..");
            		
            		if (doesElementExist2(properties.getProperty("StatusType"), 5)) {
            			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("StatusType")));
            			for (WebElement lnk:selopts) {
            				if (lnk.getText().equals(testdata1)) {
            					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
            					Thread.sleep(4000);
            					log.logLine(Testname, false, "Selecting the Status Type Name from the dropdown..");							
            					break;
            				}				
            			}
            			
            		} else {
            			log.logLine(Testname, true, "Status Type options are not displayed");
            			throw new Exception("Status Type options are not displayed");
            		}
            		
            		
            		if (doesElementExist2(properties.getProperty("FromDate"), 5)) {	  
            		    WebElement DteFromfld = driver.findElement(By.cssSelector(properties.getProperty("FromDate")));
            			DteFromfld.clear();
            			Thread.sleep(2000);
            			DteFromfld.sendKeys(actval);
            			log.logLine(Testname, false, "Entering the from date value in advanced search");
            			
            			WebElement DteTofld = driver.findElement(By.cssSelector(properties.getProperty("ToDate")));
            			DteTofld.clear();
            			Thread.sleep(2000);
            			DteTofld.sendKeys(actval);
            			log.logLine(Testname, false, "Entering the To date value in advanced search");
            	    }
            		
            		if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
            	    	WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
            	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
            	    	Thread.sleep(4000);
            		   	PleasewaitDisappear();
            		   	log.logLine(Testname, false, "Clicking on Serach button");
            		} else {
            		    log.logLine(Testname, true, "Clicking on Serach button is failed");
            		    throw new Exception("Clicking on Serach button is failed");
            		}
            		
            		Thread.sleep(5000);
                    if (doesElementExist2(properties.getProperty("Records"), 5)) {
                    	log.logLine(Testname, false, "Quick search - Successfully displayed the records for entered text in srchType field");		    		    	
                    } else {
                	    	log.logLine(Testname, false, "Quick search - No records displayed for entered text in srchType field");
                    }
                    
                    String[] Sort1 = new String[20];
                    row = "tr";
                    List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));
                    Thread.sleep(5000);
                		if(doesElementExist2(properties.getProperty("PrintStatus"), 5)){
                			for(int i = 0; i < DataCnt.size(); i++) {
                				Sort1[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row+" td+td+td[role='gridcell']")).getText();
                				String clck=Sort1[i];
                				if(i>0){
                				((JavascriptExecutor) driver).executeScript("arguments[0].click()", clck);
                				}
                				if (doesElementExist(properties.getProperty("ProcessDate"), 5)) {
                			    	WebElement prcdte = driver.findElement(By.xpath(properties.getProperty("ProcessDate")));
                			    	 String processdate=prcdte.getText(); 
                			    	 String procdte[]=processdate.split(" ");
                			    	 actval1 = procdte[0].trim();
                			    	 if(actval1.equals(actval)){
                			    		 log.logLine(Testname, false, "Date matches with ");
                			    	 }else{
                     					log.logLine(Testname, true, "Status does not matches with "+Sort1[i]);
                    					break;
                    				}
                			    	 
                				   	log.logLine(Testname, false, "Getting the value of the process date");
                				} else {
                				    log.logLine(Testname, true, "Getting the value of the process date is failed");
                				    throw new Exception("Getting the value of the process date is failed");
                				}
                				
                				row = row + "+tr";
                				log.logLine(Testname, false, "Iterating through the Rows....Rows Have the Status as "+Sort1[i]);
                				Thread.sleep(2000);
                			}
                			
                		}
                    
            	}            	
                break;
                
		}
		
		
		return true;    		    
		
		}
		
		
	}	
	
	



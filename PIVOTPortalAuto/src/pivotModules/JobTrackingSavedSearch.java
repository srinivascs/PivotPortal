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

public class JobTrackingSavedSearch extends Page{

public static String newsvetxt;
public static String cpysvetxt;

	public JobTrackingSavedSearch(WebDriver driver, Log log) throws InvalidFormatException, IOException {
 			super(driver, log);
 	} 
 	@Override
 	protected void load() {}
 	@Override
 	
 	protected void isLoaded() throws Error {}
 	
 	Actions action = new Actions(driver);
 
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
	    	log.logLine(Testname, false, "Click on JobTracking Module is Successful");
	    } else {
	    	log.logLine(Testname, true, "Click on JobTracking Module is failed");
	    	driver.switchTo().defaultContent();
	    	throw new Exception("Click on JobTracking Module is failed");
	    }
	    
		
		
		//Selecting the Client and Application name from popup
		Thread.sleep(1000);
	    boolean CliSelected = false;
	    String ClntName = test.readColumnData("ClientName", sheetname);
	    
	    if (doesElementExist2(properties.getProperty("selClint1"), 5)) {
	    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selClint1")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
	    	Thread.sleep(1000);
	    	log.logLine(Testname, false, "Clicked on ClientName dropdown..");
	    	
			
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
				log.logLine(Testname, false, "Clicked on ClientName dropdown..");
				
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
			log.logLine(Testname, false, "Clicked on ApplicationName dropdown..");
			
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
				log.logLine(Testname, false, "Clicked on ApplicationName dropdown..");
				
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
		   	log.logLine(Testname, false, "Clicked on OK button");
		} else {
		    log.logLine(Testname, true, "Clicked on OK button to view the Reports is failed");
		    throw new Exception("Clicked on OK button to view the Reports is failed");
		}
     
	    
	    return true;
 	}	
 	
 	
 	public boolean SavedSearch(String AccNo,String Testname) throws Exception {
 		
 		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	
  		String Text=test.readColumnData("TextName", sheetname);
  		String Copy=test.readColumnData("CopyName", sheetname);
  		
  		Actions action = new Actions(driver);

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
	    
	    if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
	    	WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
	    	Thread.sleep(1000);
		   	PleasewaitDisappear();
		   	log.logLine(Testname, false, "Clicked on Search button");
		} else {
		    log.logLine(Testname, true, "Clicked on Search is failed");
		    throw new Exception("Clicked on Search is failed");
		}
	    
	   	 
	    if (doesElementExist2(properties.getProperty("SrchType"), 5)) {	   
	    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("SrchType")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
	    	
	    	Thread.sleep(1000);	    	
			log.logLine(Testname, false, "Clicked on SrchType dropdown..");
			
			if (doesElementExist2(properties.getProperty("TypeQuick"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("TypeQuick")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Quick (Order)")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Search Type Name from the dropdown..");							
						break;
					}				
				}
				
			} else {
				log.logLine(Testname, true, "Search Type options are not displayed");
				throw new Exception("Search Type options are not displayed");
			}
	    }
	    
	    
	    if (doesElementExist2(properties.getProperty("Cancelbtn"), 5)) {
	    	WebElement cnlbtn = driver.findElement(By.cssSelector(properties.getProperty("Cancelbtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnlbtn);
	    	Thread.sleep(3000);
		   	log.logLine(Testname, false, "Click on Cancel button");
		} else {
		    log.logLine(Testname, true, "Click on Cancel button is failed");
		    throw new Exception("Click on Cancel button is failed");
		}
	    
	    
	    if (doesElementExist2(properties.getProperty("Savebtn"), 5)) {
	    	WebElement svebtn = driver.findElement(By.cssSelector(properties.getProperty("Savebtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", svebtn);
	    	Thread.sleep(10000);
		   	log.logLine(Testname, false, "Clicked on Save button");
		} else {
		    log.logLine(Testname, true, "Clicked on Save button is failed");
		    throw new Exception("Clicked on Save button is failed");
		}
	    
	    WebElement retEle = waitForElement(properties.getProperty("SerachName"));
		log.logLine(Testname, false, "Search Name is found on the page..");
	    
	    
	    if (doesElementExist2(properties.getProperty("SerachName"), 5)) {	  
			 WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("SerachName")));
			 Txt.clear();
			 Txt.sendKeys(Text+AccNo);
			 log.logLine(Testname, false, "The Value Entered in text field as  " +Text+AccNo);		 
		}else{
			log.logLine(Testname, true,"Unable to enter the text in text field");
			throw new Exception("Unable to  enter the text in text field");
		}
	    
	    if (doesElementExist2(properties.getProperty("Status"), 5)) {	   
	    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Status")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
	    	Thread.sleep(1000);
			log.logLine(Testname, false, "Clicked on Status dropdown..");
			
			if (doesElementExist2(properties.getProperty("StatusType"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("StatusType")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("All")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Status Type Name from the dropdown");							
						break;
					}				
				}
				
			} else {
				log.logLine(Testname, true, "Status Type options are not displayed");
				throw new Exception("Status Type options are not displayed");
			}
	    }
 		
	    
	    if (doesElementExist2(properties.getProperty("DateType"), 5)) {	   
	    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("DateType")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
	    	Thread.sleep(1000);
			log.logLine(Testname, false, "Clicked on Date Type dropdown..");
			
			if (doesElementExist2(properties.getProperty("Dtetyplst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Dtetyplst")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Data Processed by RRD Facility Date")) {
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
	    
	    
	    if (doesElementExist2(properties.getProperty("Dterngdrpdwn"), 5)) {	   
	    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Dterngdrpdwn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
	    	Thread.sleep(1000);
			log.logLine(Testname, false, "Clicked on Date Range dropdown..");
			
			if (doesElementExist2(properties.getProperty("Dterngdrpdwnlist"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Dterngdrpdwnlist")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Daily")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Date Range Name from the dropdown..");							
						break;
					}				
				}
				
			} else {
				log.logLine(Testname, true, "Date Range options are not displayed");
				throw new Exception("Date Range options are not displayed");
			}
	    }
		
	    
	    if (doesElementExist2(properties.getProperty("SearchSave"), 5)) {
	    	WebElement svebtn = driver.findElement(By.cssSelector(properties.getProperty("SearchSave")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", svebtn);
	    	Thread.sleep(4000);
		   	log.logLine(Testname, false, "Clicked on Search Save button");
		} else {
		    log.logLine(Testname, true, "Clicked on Search Save button is failed");
		    throw new Exception("Clicked on Search Save button is failed");
		}
	    
	    
	    if (doesElementExist2(properties.getProperty("AlertClose"), 5)) {
	    	WebElement Alrt = driver.findElement(By.cssSelector(properties.getProperty("AlertClose")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", Alrt);
	    	Thread.sleep(3000);
		   	log.logLine(Testname, false, "Clicked on Ok for Alert Close Pop Up");
		} else {
		    log.logLine(Testname, true, "Clicked on Ok for Alert Close Pop Up is failed");
		    throw new Exception("Clicked on Ok for Alert Close Pop Up is failed");
		}
	    
	    
	    if (doesElementExist2(properties.getProperty("Cancelbtn"), 5)) {
	    	WebElement cnlbtn = driver.findElement(By.cssSelector(properties.getProperty("Cancelbtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnlbtn);
	    	Thread.sleep(3000);
		   	log.logLine(Testname, false, "Click on Cancel button under Search Information form");
		} else {
		    log.logLine(Testname, true, "Click on Cancel button under Search Information form is failed");
		    throw new Exception("Click on Cancel button under Search Information form is failed");
		}
	    
	    
	    if (doesElementExist2(properties.getProperty("SavedSerachbtn"), 5)) {
	    	WebElement save = driver.findElement(By.cssSelector(properties.getProperty("SavedSerachbtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
	    	Thread.sleep(3000);
		   	log.logLine(Testname, false, "Clicked on Saved Serach button");
		} else {
		    log.logLine(Testname, true, "Clicked on Saved Serach button is failed");
		    throw new Exception("Clicked on Saved Serachbtn is failed");
		}
		
	    
	    if (doesElementExist2(properties.getProperty("Savedsrchtxt"), 5)) {
	    	String svetxt = driver.findElement(By.cssSelector(properties.getProperty("Savedsrchtxt"))).getText();
	    	Thread.sleep(2000);
	    	if(svetxt.equals(Text+AccNo)){
			log.logLine(Testname, false, "The text "+ svetxt+"  Matches With the Copy of Saved Text  " +Text+AccNo);
	    	}else {
			    log.logLine(Testname, true, "Text doesnot Matches with the Saved Search Text");
	    	}
	    	
		} else {
		    log.logLine(Testname, true, "Text doesnot Matches with the Saved Search Text");
		    throw new Exception("Text doesnot Matches with the Saved Search Text");
		}
	    
	    
	    // Edit
	    
	    if (doesElementExist2(properties.getProperty("Editbtn"), 5)) {
	    	WebElement save = driver.findElement(By.cssSelector(properties.getProperty("Editbtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
	    	Thread.sleep(3000);
		   	log.logLine(Testname, false, "Clicked on Edit button");
		} else {
		    log.logLine(Testname, true, "Clicked on Edit button is failed");
		    throw new Exception("Clicked on Edit button is failed");
		}
	    
	    if (doesElementExist2(properties.getProperty("SerachName"), 5)) {	  
			 WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("SerachName")));
			 Txt.clear();
			 Txt.sendKeys(Text+AccNo+1);
			 log.logLine(Testname, false, "The Value Entered in text field as  " +Text+AccNo+1);		 
		}else{
			log.logLine(Testname, true,"Unable to enter the text in text field");
			throw new Exception("Unable to  enter the text in text field");
		}
	    
	    
	    if (doesElementExist2(properties.getProperty("SearchSave"), 5)) {
	    	WebElement svebtn = driver.findElement(By.cssSelector(properties.getProperty("SearchSave")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", svebtn);
	    	Thread.sleep(4000);
		   	log.logLine(Testname, false, "Clicked on Search Save button");
		} else {
		    log.logLine(Testname, true, "Clicked on Search Save button is failed");
		    throw new Exception("Clicked on Search Save button is failed");
		}
	    
	    if (doesElementExist2(properties.getProperty("AlertClose"), 5)) {
	    	WebElement Alrt = driver.findElement(By.cssSelector(properties.getProperty("AlertClose")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", Alrt);
	    	Thread.sleep(3000);
	    	log.logLine(Testname, false, "Clicked on Ok for Alert Close Pop Up");
		} else {
		    log.logLine(Testname, true, "Clicked on Ok for Alert Close Pop Up is failed");
		    throw new Exception("Clicked on Ok for Alert Close Pop Up is failed");
		}
	    
	    
	    if (doesElementExist2(properties.getProperty("Cancelbtn"), 5)) {
	    	WebElement cnlbtn = driver.findElement(By.cssSelector(properties.getProperty("Cancelbtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnlbtn);
	    	Thread.sleep(3000);
		   	log.logLine(Testname, false, "Click on Cancel button under Search Information form");
		} else {
		    log.logLine(Testname, true, "Click on Cancel button under Search Information form is failed");
		    throw new Exception("Click on Cancel button under Search Information form is failed");
		}
	    
	    
	    if (doesElementExist2(properties.getProperty("SavedSerachbtn"), 5)) {
	    	WebElement save = driver.findElement(By.cssSelector(properties.getProperty("SavedSerachbtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
	    	Thread.sleep(3000);
		   	log.logLine(Testname, false, "Clicked on Saved Serach button");
		} else {
		    log.logLine(Testname, true, "Clicked on Saved Serach button is failed");
		    throw new Exception("Clicked on Saved Serachbtn is failed");
		}
	    
	    
	    if (doesElementExist2(properties.getProperty("SavedSerachbtn"), 5)) {
	    	WebElement save = driver.findElement(By.cssSelector(properties.getProperty("SavedSerachbtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
	    	Thread.sleep(3000);
		   	log.logLine(Testname, false, "Clicked on Saved Serach button");
		} else {
		    log.logLine(Testname, true, "Clicked on Saved Serach button is failed");
		    throw new Exception("Clicked on Saved Serachbtn is failed");
		}
	    
	    if (doesElementExist2(properties.getProperty("Savedsrchtxt"), 5)) {
	    	newsvetxt = driver.findElement(By.cssSelector(properties.getProperty("Savedsrchtxt"))).getText();
	    	Thread.sleep(2000);
	    	if(newsvetxt.equals(Text+AccNo+1)){
			log.logLine(Testname, false, "The text "+ newsvetxt+"  Matches With the Edited Text  " +Text+AccNo+1);
	    	}else {
			    log.logLine(Testname, true, "Text doesnot Matches with the Saved Search Text");
	    	}
	    	
		} else {
		    log.logLine(Testname, true, "Text doesnot Matches with the Saved Search Text");
		    throw new Exception("Text doesnot Matches with the Saved Search Text");
		}
	    
	   
	    // Copy
	    
	    if (doesElementExist2(properties.getProperty("Copybtn"), 5)) {
	    	WebElement save = driver.findElement(By.cssSelector(properties.getProperty("Copybtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
	    	Thread.sleep(3000);
		   	log.logLine(Testname, false, "Clicked on Copy button");
		} else {
		    log.logLine(Testname, true, "Clicked on Copy button is failed");
		    throw new Exception("Clicked on Copy button is failed");
		}
	    
	    if (doesElementExist2(properties.getProperty("SerachName"), 5)) {	  
			 WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("SerachName")));
			 Txt.clear();
			 Txt.sendKeys(Copy+Text+AccNo+1);
			 log.logLine(Testname, false, "The Value Entered in text field as  " +Copy+Text+AccNo+1);		 
		}else{
			log.logLine(Testname, true,"Unable to enter the text in text field");
			throw new Exception("Unable to  enter the text in text field");
		}
	    
	    
	    if (doesElementExist2(properties.getProperty("SearchSave"), 5)) {
	    	WebElement svebtn = driver.findElement(By.cssSelector(properties.getProperty("SearchSave")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", svebtn);
	    	Thread.sleep(4000);
		   	log.logLine(Testname, false, "Clicked on Search Save button");
		} else {
		    log.logLine(Testname, true, "Clicked on Search Save button is failed");
		    throw new Exception("Clicked on Search Save button is failed");
		}
	    
	    if (doesElementExist2(properties.getProperty("AlertClose"), 5)) {
	    	WebElement Alrt = driver.findElement(By.cssSelector(properties.getProperty("AlertClose")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", Alrt);
	    	Thread.sleep(3000);
	    	log.logLine(Testname, false, "Clicked on Ok for Alert Close Pop Up");
		} else {
		    log.logLine(Testname, true, "Clicked on Ok for Alert Close Pop Up is failed");
		    throw new Exception("Clicked on Ok for Alert Close Pop Up is failed");
		}
	    
	    
	    if (doesElementExist2(properties.getProperty("Cancelbtn"), 5)) {
	    	WebElement cnlbtn = driver.findElement(By.cssSelector(properties.getProperty("Cancelbtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnlbtn);
	    	Thread.sleep(3000);
		   	log.logLine(Testname, false, "Click on Cancel button under Search Information form");
		} else {
		    log.logLine(Testname, true, "Click on Cancel button under Search Information form is failed");
		    throw new Exception("Click on Cancel button under Search Information form is failed");
		}
	    
	    Thread.sleep(3000);
	    if (doesElementExist2(properties.getProperty("SavedSerachbtn"), 5)) {
	    	WebElement save = driver.findElement(By.cssSelector(properties.getProperty("SavedSerachbtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
	    	Thread.sleep(3000);
		   	log.logLine(Testname, false, "Clicked on Saved Serach button");
		} else {
		    log.logLine(Testname, true, "Clicked on Saved Serach button is failed");
		    throw new Exception("Clicked on Saved Serachbtn is failed");
		}
	    
	    Thread.sleep(3000);
	    if (doesElementExist2(properties.getProperty("SavedSerachbtn"), 5)) {
	    	WebElement save = driver.findElement(By.cssSelector(properties.getProperty("SavedSerachbtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
	    	Thread.sleep(3000);
		   	log.logLine(Testname, false, "Clicked on Saved Serach button");
		} else {
		    log.logLine(Testname, true, "Clicked on Saved Serach button is failed");
		    throw new Exception("Clicked on Saved Serachbtn is failed");
		}
	    
	    if (doesElementExist2(properties.getProperty("Savedsrchtxt"), 5)) {
	    	cpysvetxt = driver.findElement(By.cssSelector(properties.getProperty("Savedsrchtxt"))).getText();
	    	Thread.sleep(2000);
	    	if(cpysvetxt.equals(Copy+Text+AccNo+1)){
		   	log.logLine(Testname, false, "The text "+ cpysvetxt+"  Matches With the Edited Text  " +Copy+Text+AccNo+1);
	    	}else {
			    log.logLine(Testname, true, "Text doesnot Matches with the Copy of Saved Text");
	    	}
	    	
		} else {
		    log.logLine(Testname, true, "Text doesnot Matches with the Saved Search Text");
		    throw new Exception("Text doesnot Matches with the Saved Search Text");
		}
	    
	    if (doesElementExist2(properties.getProperty("Savedsrchtxt"), 5)) {
	    	if(cpysvetxt.equals(Copy+Text+AccNo+1)){
			    if (doesElementExist2(properties.getProperty("Deletebtn"), 5)) {
			    	WebElement save = driver.findElement(By.cssSelector(properties.getProperty("Deletebtn")));
			    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
			    	Thread.sleep(3000);
				   	log.logLine(Testname, false, "Clicked on Delete button and deleted the "+Copy+Text+AccNo+1+"----- copied record");
				} else {
				    log.logLine(Testname, true, "Clicked on Delete button is failed");
				    throw new Exception("Clicked on Delete button is failed");
				}
		    }else {
			    log.logLine(Testname, true, "Text does not Exists");
		    }
	  }else {
		    log.logLine(Testname, true, "Clicked on Saved Search is failed");
	    } 
	    
	    
	    if (doesElementExist2(properties.getProperty("Confirmdelete"), 5)) {
	    	WebElement save = driver.findElement(By.cssSelector(properties.getProperty("Confirmdelete")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
	    	Thread.sleep(3000);
		   	log.logLine(Testname, false, "Clicked Ok for Confirm Delete Alert Pop Up");
		} else {
		    log.logLine(Testname, true, "Clicked Ok for Confirm Delete Alert Pop Up is failed");
		    throw new Exception("Clicked Ok for Confirm Delete Alert Pop Up is failed");
		}
	    
	    
	    if (doesElementExist2(properties.getProperty("Savedsrchtxt"), 5)) {
	    	if(newsvetxt.equals(Text+AccNo+1)){
			    if (doesElementExist2(properties.getProperty("Deletebtn"), 5)) {
			    	WebElement save = driver.findElement(By.cssSelector(properties.getProperty("Deletebtn")));
			    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
			    	Thread.sleep(3000);
				   	log.logLine(Testname, false, "Clicked on Delete button and deleted the "+Text+AccNo+1+"----- saved record");
				} else {
				    log.logLine(Testname, true, "Clicked on Delete button is failed");
				    throw new Exception("Clicked on Delete button is failed");
				}
		    }else {
			    log.logLine(Testname, true, "Text does not Exists");
		    }
	  }else {
		    log.logLine(Testname, true, "Clicked on Saved Search is failed");
	    } 
	    
	   
	    if (doesElementExist2(properties.getProperty("Confirmdelete"), 5)) {
	    	WebElement save = driver.findElement(By.cssSelector(properties.getProperty("Confirmdelete")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
	    	Thread.sleep(3000);
		   	log.logLine(Testname, false, "Clicked Ok for Confirm Delete Alert Pop Up");
		} else {
		    log.logLine(Testname, true, "Clicked Ok for Confirm Delete Alert Pop Up is failed");
		    throw new Exception("Clicked Ok for Confirm Delete Alert Pop Up is failed");
		}
	    
	   
	    if (doesElementExist2(properties.getProperty("Savedsrchtxt"), 5)) {
	    String Verfntxt = driver.findElement(By.cssSelector(properties.getProperty("Savedsrchtxt"))).getText();
	    	Thread.sleep(2000);
	    	if(Verfntxt.equals(newsvetxt)){
		   	log.logLine(Testname, true, "Saved Record Still Exists" +newsvetxt);
	    	}else {
			    log.logLine(Testname, false , "Saved Record  "+newsvetxt+"  Does Not Exists");
	    	}
	    	
	    	if(Verfntxt.equals(cpysvetxt)){
			   	log.logLine(Testname, true, "Copy of Saved Record Still Exists" +cpysvetxt);
		    	}else {
				    log.logLine(Testname, false , "Copy of Saved Record  "+cpysvetxt+"  Does Not Exists");
		    	}
	    	
	    	
		} else {
		    log.logLine(Testname, true, "Text doesnot Matches with the Saved Search Text");
		    throw new Exception("Text doesnot Matches with the Saved Search Text");
		}
	    
	    
	    return true;
 	}
}

	   
 		

   	
			       		 	
			    
	    
	
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

public class ArchiveReprintBeta extends Page{

	public ArchiveReprintBeta(WebDriver driver, Log log) throws InvalidFormatException, IOException {
 			super(driver, log);
 	} 
 	@Override
 	protected void load() {}
 	@Override
 	
 	protected void isLoaded() throws Error {}
 
 	public boolean ArchiveClientAppSel(String AccNo, String Testname, String SetWindow) throws Exception { 		
 		  
		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile")); 		
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
		
		Thread.sleep(1000);
		driver.switchTo().window(SetWindow);
		
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
    

	    
	    if (doesElementExist2(properties.getProperty("Okbtn"), 5)) {
	    	WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Okbtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
	    	Thread.sleep(1000);
	    	PleasewaitDisappear();
	    	Thread.sleep(1000);
	    	log.logLine(Testname, false, "Clicking on OK button to view the Archives");
	    } else {
	    	log.logLine(Testname, true, "Clicking on OK button to view the Archives is failed");
	    	throw new Exception("Clicking on OK button to view the Archives is failed");
	    }
	    
	    return true;
 	}
 	
 	public boolean ReprintsettingsBeta(String AccNo, String Testname) throws Exception {
 		
 		
 		if (doesElementExist2(properties.getProperty("Archivesearchmodule"), 5)) {
	    	WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Archivesearchmodule")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);	    	 
	    	 Thread.sleep(2000);
	    	 PleasewaitDisappear();
	    	log.logLine(Testname, false, "Click on Archivesearchmodule is successfull");
	    } else {
	    	log.logLine(Testname, true, "Archivesearchmodule is not displayed");
	    	driver.switchTo().defaultContent();
	    	throw new Exception("Archivesearchmodule is not displayed");
	    }
 		
 		Thread.sleep(5000);
	    driver.switchTo().frame("iframeContainer");	    
	   
	    InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	
	    
	    String TxtField=test.readColumnData("TextField", sheetname);
	    String First =test.readColumnData("FirstLine", sheetname);
		String Second =test.readColumnData("SecondLine", sheetname);
		String Third =test.readColumnData("ThirdLine", sheetname);
		String Fourth =test.readColumnData("FourthLine", sheetname);
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		Date date = new Date();
		String todaysDate = dateFormat.format(date);

		long timenow = System.currentTimeMillis();
		long testime = timenow - Initialization.startTime;
		int totalTime =(int) ((testime/(1000*60)));	 
		 
		if (21 > totalTime) {
			 int WaitTime = 21 - totalTime; 
			 log.logLine(Testname, false, "Going to wait for "+WaitTime +"minutes, please wait...");
			 Thread.sleep(WaitTime*1000*60);		 
		}

	    Thread.sleep(14000);
	     //Clicking on Advance search button	    
	    if (doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5)) {
			 WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			 log.logLine(Testname, false, "Click on Advance Search is successfull");
		 } else {
			 log.logLine(Testname, true, "Click on Advance Search is failed");
			 driver.switchTo().defaultContent();
			 throw new Exception("Click on Advance Search is failed");
		 }
	     
	     
	  
	     if (doesElementExist2(properties.getProperty("Alldts"), 5)) {
			 WebElement btn = driver.findElement(By.cssSelector(properties.getProperty("Alldts")));
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
			 Thread.sleep(2000);
			 log.logLine(Testname, false, "Click on All Date Checkbox is Successfull");
		 } else {
			 log.logLine(Testname, true, "Click on All Date Checkbox is Failed");
			 driver.switchTo().defaultContent();
			 throw new Exception("Click on All Date Checkbox is Failed");
		 }
	    
     
		 Thread.sleep(3000);
		
		 if (doesElementExist2(properties.getProperty("Fieldbutton"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("Fieldbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(2000);
			
			log.logLine(Testname, false, "Clicking on Seacrh criteria Field drop down list in advance search");			
			if (doesElementExist2(properties.getProperty("SelFieltOpt"), 5)) {
				
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelFieltOpt")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("CEDULA")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(2000);
						log.logLine(Testname, false, "Selecting the CEDULA Field option from the dropdown");
						break;
					}						
				}
			} else {				
				log.logLine(Testname, true, "Selecting the field option is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Selecting the field option is failed");				
			}		
			
		 }else {
			 log.logLine(Testname, true, "Clicking on the Field is failed");
			 driver.switchTo().defaultContent();
			 throw new Exception("Clicking on the Field is failed");
		 }
		 
			
		 if (doesElementExist2(properties.getProperty("Operatorbutton"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("Operatorbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(1000);
			
			log.logLine(Testname, false, "Clicking on Seacrh criteria Operator drop down list in advance search");
			
			if (doesElementExist2(properties.getProperty("SelOpertOpt"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelOpertOpt")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Equals")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(2000);
						log.logLine(Testname, false, "Selecting the Equals operator option from the dropdown");
						break;
					}						
				}
			} else {				
				log.logLine(Testname, true, "Selecting the operator option is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Selecting the operator option is failed");				
			}		
		}else {				
			log.logLine(Testname, true, "Clicking on the operator is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on the operator is failed");				
		}
	
		 
		Thread.sleep(1000);
		if (doesElementExist2(properties.getProperty("Txtfld"), 5)) {	  
			 WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("Txtfld")));
			 Txt.click();
			 Txt.clear();
 			 Txt.sendKeys(Initialization.EightDig2);
 			 Thread.sleep(2000);
 			 log.logLine(Testname, false, "The Enterd value in text field is"  +Initialization.EightDig2);		 
 		}else{
			log.logLine(Testname, true,"Unable to enter the text in text field");
			throw new Exception("Unable to  enter the text in text field");
 		}
		
		 
		if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	 
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			Thread.sleep(4000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button is successfull");
		} else {
			log.logLine(Testname, true, "Clicking on Search button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Search button is failed");
		}

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("Chooseactn"), 5)) {
			 WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Chooseactn")));
			 
			 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",btnsrch);
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			 //test
			 log.logLine(Testname, false, "Clicking on Choose action button is successfull");
		} else {
				log.logLine(Testname, true, "Clicking on Choose action button is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on Choose action button is failed");
			}
		
				
		
		if(doesElementExist2(properties.getProperty("Reprint"), 5)){
			List<WebElement> Reprnt = driver.findElements(By.cssSelector(properties.getProperty("Reprint")));
			 for (WebElement lnk:Reprnt) {
				 if (lnk.getText().equals("Reprint (Available For Reprint)")){
					 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					 Thread.sleep(1000);
					 log.logLine(Testname, false, "Selecting Available For Reprint option under choose action is successfull..");
					 break;
					 }
				 }
	    }
		
		if (doesElementExist2(properties.getProperty("MailingAddFstlne"), 5)) {
			 WebElement LinefstFld = driver.findElement(By.cssSelector(properties.getProperty("MailingAddFstlne")));
			 LinefstFld.clear();
			 LinefstFld.sendKeys(First);
			 log.logLine(Testname, false, "Entering The "+First+" in Address field");
			 }
		
		if (doesElementExist2(properties.getProperty("MailingAddSndlne"), 5)) {
			 WebElement Linesndfld = driver.findElement(By.cssSelector(properties.getProperty("MailingAddSndlne")));
			 Linesndfld.clear();
			 Linesndfld.sendKeys(Second);
			 log.logLine(Testname, false, "Entering The "+Second+" in Address field");
			 }
		
		if (doesElementExist2(properties.getProperty("MailingAddTrdlne"), 5)) {
			 WebElement Linetrdfld = driver.findElement(By.cssSelector(properties.getProperty("MailingAddTrdlne")));
			 Linetrdfld.clear();
			 Linetrdfld.sendKeys(Third);
			 log.logLine(Testname, false, "Entering The "+Third+" in Address field");
			 }
		
		if (doesElementExist2(properties.getProperty("MailingAddFurlne"), 5)) {
			 WebElement Linrfurfld = driver.findElement(By.cssSelector(properties.getProperty("MailingAddFurlne")));
			 Linrfurfld.clear();
			 Linrfurfld.sendKeys(Fourth);
			 log.logLine(Testname, false, "Entering The "+Fourth+" in Address field");
			 }
		

	    if (doesElementExist2(properties.getProperty("Domesticradbtn"), 5)) {
		    WebElement Domesticradbtn = driver.findElement(By.cssSelector(properties.getProperty("Domesticradbtn")));
		    
		    if ( Domesticradbtn.isSelected())
		     {
		    	log.logLine(Testname, false, "checkbox is already selected");
		     }else{
		    	 Domesticradbtn.click();
		     }
		     log.logLine(Testname, false, "Selecting the Domestic check box is Successful");
	    } else {
		     log.logLine(Testname, true, "Selecting the Domestic check box is failed");
		     driver.switchTo().defaultContent();
		     //throw new Exception("Selecting the less than check box in advance search is failed");
		}
		
	    if (doesElementExist2(properties.getProperty("ReprintReason"), 5)) {	    
			WebElement flnm = driver.findElement(By.cssSelector(properties.getProperty("ReprintReason")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", flnm);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on Reprint Reason drop down list in advance search");			

			if(doesElementExist2(properties.getProperty("LostOriginal"), 5)){
				List<WebElement> Reprnt = driver.findElements(By.cssSelector(properties.getProperty("LostOriginal")));
				 for (WebElement lnk:Reprnt) {
					 if (lnk.getText().equals("Lost Original")){
						 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						 log.logLine(Testname, false, "Selecting Change of Address option under choose action is successfull..");
						 break;
						 }
					 }
				 }
		}else {
			log.logLine(Testname, true, "Unable to select the Reprint Reason from the dropdown ");
			}
		
	    
	    if (doesElementExist2(properties.getProperty("Save"), 5)) {	 
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Save")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Save button is successfull");
		} else {
			log.logLine(Testname, true, "Clicking on Save button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Save button is failed");
		}
	    
	    
		if (doesElementExist2(properties.getProperty("AlertOk"), 5)) {
			 WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("AlertOk")));
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			 PleasewaitDisappear();
			 log.logLine(Testname, false, "Clicking AlertOk button is successfull");
			} else {
				log.logLine(Testname, true, "Clicking AlertOk action button is failed");
				throw new Exception("Clicking AlertOk Send button is failed");
			}
		
		
		if (doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5)) {
			 WebElement advsrch = driver.findElement(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			 
			 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",advsrch);
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", advsrch);
			 
			 log.logLine(Testname, false, "Click on Advance Search is successfull");
		 } else {
			 log.logLine(Testname, true, "Click on Advance Search is failed");
			 driver.switchTo().defaultContent();
			 throw new Exception("Click on Advance Search is failed");
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
		
    	Thread.sleep(2000);	
		if (doesElementExist2(properties.getProperty("Chooseactn"), 5)) {
			 WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Chooseactn")));
			 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",btnsrch);
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			 Thread.sleep(1000);
			 log.logLine(Testname, false, "Clicking on Choose action button is successfull");
			} else {
				log.logLine(Testname, true, "Clicking on Choose action button is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on Choose action button is failed");
				}
		
		Thread.sleep(2000);
		if(doesElementExist2(properties.getProperty("Reprint"), 5)){
			 List<WebElement> nonview = driver.findElements(By.cssSelector(properties.getProperty("Reprint")));
			 for (WebElement lnk:nonview) {
				 if (lnk.getText().contains("Reprint (In Reprint Queue)")){
					 log.logLine(Testname, false, "The Reprint option is in Reprint (In Reprint Queue) status ");
					 break;
					}else if(lnk.getText().contains("Reprint (Last Reprint Date:)")){
						Actions actions = new Actions(driver);
						log.logLine(Testname, false, "The Reprint option is in Reprint (Last Reprint Date:) status");
						break;
						}
				 }
		}  
		
		//Multiple Record Check and Reprint
				
		if (doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5)) {
			 WebElement advsrch = driver.findElement(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",advsrch);
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", advsrch);
			 log.logLine(Testname, false, "Click on Advance Search is successfull");
		 } else {
			 log.logLine(Testname, true, "Click on Advance Search is failed");
			 driver.switchTo().defaultContent();
			 throw new Exception("Click on Advance Search is failed");
		 }
	     
		
		//Clicking on Clear Button	 
		if (doesElementExist2(properties.getProperty("ClearbtnAdsrch"), 5)) {
			WebElement Clrbtn = driver.findElement(By.cssSelector(properties.getProperty("ClearbtnAdsrch")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Clrbtn);	
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on clear button in advanced search dialog");
		} else {
			 log.logLine(Testname, true, "Clear button in advanced dialog does not exist");
			 driver.switchTo().defaultContent();
			 throw new Exception("Clear button in advanced dialog does not exist");
		 }
		
		
	     if (doesElementExist2(properties.getProperty("Alldts"), 5)) {
			 WebElement btn = driver.findElement(By.cssSelector(properties.getProperty("Alldts")));
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
			 log.logLine(Testname, false, "Click on All Date Checkbox is Successfull");
		 } else {
			 log.logLine(Testname, true, "Click on All Date Checkbox is Failed");
			 driver.switchTo().defaultContent();
			 throw new Exception("Click on All Date Checkbox is Failed");
		 }
		 
	     
		 Thread.sleep(3000);		
		 if (doesElementExist2(properties.getProperty("Fieldbutton"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("Fieldbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(1000);
			
			log.logLine(Testname, false, "Clicking on Seacrh criteria Field drop down list in advance search");
			
			if (doesElementExist2(properties.getProperty("SelFieltOpt"), 5)) {
				
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelFieltOpt")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("CEDULA")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						log.logLine(Testname, false, "Selecting the CEDULA Field option from the dropdown");
						break;
					}						
				}
			} else {				
				log.logLine(Testname, true, "Selecting the field option is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Selecting the field option is failed");				
			}		
			
		 }else {
			 log.logLine(Testname, true, "Clicking on the Field is failed");
			 driver.switchTo().defaultContent();
			 throw new Exception("Clicking on the Field is failed");
		 }
		 
			
		 if (doesElementExist2(properties.getProperty("Operatorbutton"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("Operatorbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(1000);
			
			log.logLine(Testname, false, "Clicking on Seacrh criteria Operator drop down list in advance search");
			
			if (doesElementExist2(properties.getProperty("SelOpertOpt"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelOpertOpt")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Starts with (wildcard)")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						log.logLine(Testname, false, "Selecting the Starts with (wildcard) operator option from the dropdown");
						break;
					}						
				}
			} else {				
				log.logLine(Testname, true, "Selecting the operator option is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Selecting the operator option is failed");				
			}		
		}else {				
			log.logLine(Testname, true, "Clicking on the operator is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on the operator is failed");				
		}

		 
		Thread.sleep(1000);
		if (doesElementExist2(properties.getProperty("Txtfld"), 5)) {	  
			 WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("Txtfld")));
			 Txt.clear();
			 Txt.sendKeys(Initialization.EightDig2);
			 log.logLine(Testname, false, "The Enterd value in text field is"  +Initialization.EightDig2);		 
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
		
		
		//Selecting First and Second Consumer details		
		String FirCons;
		if (doesElementExist2(properties.getProperty("Firstconsumer"), 5)) {
			 WebElement Firstconsumer = driver.findElement(By.cssSelector(properties.getProperty("Firstconsumer")));
			 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",Firstconsumer);
			 FirCons = Firstconsumer.getText();
			 log.logLine(Testname, false, "Reading The First Consumer Record in multipe records is successfull");
		} else {
			log.logLine(Testname, true, "Reading The First Consumer Record in multipe records is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Reading The First Consumer Record in multipe records is failed");
			}
		
		
		String SecCons;
		if (doesElementExist2(properties.getProperty("Secondconsumer"), 5)) {
			 WebElement Secondconsumer = driver.findElement(By.cssSelector(properties.getProperty("Secondconsumer")));
			SecCons= Secondconsumer.getText();
			 log.logLine(Testname, false, "Reading The Second Consumer Record in multipe records is successfull");
		} else {
			log.logLine(Testname, true, "Reading The Second Consumer Record in multipe records is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Reading The Second Consumer Record in multipe records is failed");
			}
	
		
		if (doesElementExist2(properties.getProperty("MultipleCheckboxfst"), 5)) {
			 WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("MultipleCheckboxfst")));
			 Thread.sleep(3000);
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			 log.logLine(Testname, false, "Clicking on Checkbox for First records is successfull");
			} else {
				log.logLine(Testname, true, "Clicking on Checkbox for First records is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on Checkbox for multipe records is failed");
				}
		
		if (doesElementExist2(properties.getProperty("MultipleCheckboxsnd"), 5)) {
			 WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("MultipleCheckboxsnd")));
			 Thread.sleep(3000);
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			 log.logLine(Testname, false, "Clicking on Checkbox for multipe records is successfull");
			} else {
				log.logLine(Testname, true, "Clicking on Checkbox for Second records is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on Checkbox for Second records is failed");
				}
		
		if (doesElementExist2(properties.getProperty("MultipleReprintbtn"), 5)) {
			 WebElement Mulrptbtn = driver.findElement(By.cssSelector(properties.getProperty("MultipleReprintbtn")));
			 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",Mulrptbtn);
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Mulrptbtn);
			 log.logLine(Testname, false, "Clicking on Reprint button for multipe records is successfull");
			} else {
				log.logLine(Testname, true, "Clicking on Reprint button for multipe records is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on Reprint button for multipe records is failed");
				}
		
		if (doesElementExist2(properties.getProperty("MailingAddFstlne"), 5)) {
			 WebElement LinefstFld = driver.findElement(By.cssSelector(properties.getProperty("MailingAddFstlne")));
			 LinefstFld.clear();
			 LinefstFld.sendKeys(First);
			 log.logLine(Testname, false, "Entering The "+First+" Address field");
			 }
		
		if (doesElementExist2(properties.getProperty("MailingAddSndlne"), 5)) {
			 WebElement Linesndfld = driver.findElement(By.cssSelector(properties.getProperty("MailingAddSndlne")));
			 Linesndfld.clear();
			 Linesndfld.sendKeys(Second);
			 log.logLine(Testname, false, "Entering The "+Second+" Address field");
			 }
		
		if (doesElementExist2(properties.getProperty("MailingAddTrdlne"), 5)) {
			 WebElement Linetrdfld = driver.findElement(By.cssSelector(properties.getProperty("MailingAddTrdlne")));
			 Linetrdfld.clear();
			 Linetrdfld.sendKeys(Third);
			 log.logLine(Testname, false, "Entering The "+Third+" Address field");
			 }
		
		if (doesElementExist2(properties.getProperty("MailingAddFurlne"), 5)) {
			 WebElement Linrfurfld = driver.findElement(By.cssSelector(properties.getProperty("MailingAddFurlne")));
			 Linrfurfld.clear();
			 Linrfurfld.sendKeys(Fourth);
			 log.logLine(Testname, false, "Entering The "+Fourth+" Address field");
			 }
		

	    if (doesElementExist2(properties.getProperty("Domesticradbtn"), 5)) {
		    WebElement Domesticradbtn = driver.findElement(By.cssSelector(properties.getProperty("Domesticradbtn")));
		    
		    if ( Domesticradbtn.isSelected())
		     {
		    	log.logLine(Testname, false, "checkbox is already selected");
		     }else{
		    	 Domesticradbtn.click();
		     }
		     log.logLine(Testname, false, "Selecting the Domestic check box is Successful");
	    } else {
		     log.logLine(Testname, true, "Selecting the Domestic check box is failed");
		     driver.switchTo().defaultContent();
		     //throw new Exception("Selecting the less than check box in advance search is failed");
		}
		
	    if (doesElementExist2(properties.getProperty("ReprintReason"), 5)) {	    
			WebElement flnm = driver.findElement(By.cssSelector(properties.getProperty("ReprintReason")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", flnm);
			log.logLine(Testname, false, "Clicking on Reprint Reason drop down list in advance search");
			Thread.sleep(1000);

			if(doesElementExist2(properties.getProperty("LostOriginal"), 5)){
				List<WebElement> Reprnt = driver.findElements(By.cssSelector(properties.getProperty("LostOriginal")));
				 for (WebElement lnk:Reprnt) {
					 if (lnk.getText().equals("Lost Original")){
						 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						 log.logLine(Testname, false, "Selecting Lost Original option under choose action is successfull..");
						 break;
						 }
					 }
				 }
		}else {
			log.logLine(Testname, true, "Unable to select the Reprint Reason from the dropdown ");
			}
		
	    
	    if (doesElementExist2(properties.getProperty("Save"), 5)) {	 
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Save")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Save button is successfull");
		} else {
			log.logLine(Testname, true, "Clicking on Save button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Save button is failed");
		}
	    
	    
		if (doesElementExist2(properties.getProperty("AlertOk"), 5)) {
			 WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("AlertOk")));
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			 PleasewaitDisappear();
			 log.logLine(Testname, false, "Clicking AlertOk button is successfull");
			} else {
				log.logLine(Testname, true, "Clicking AlertOk action button is failed");
				throw new Exception("Clicking AlertOk Send button is failed");
			}
		
		// Refresh the Page
		
		if (doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5)) {
			 WebElement advsrch = driver.findElement(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",advsrch);
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", advsrch);
			 log.logLine(Testname, false, "Click on Advance Search is successfull");
		 } else {
			 log.logLine(Testname, true, "Click on Advance Search is failed");
			 driver.switchTo().defaultContent();
			 throw new Exception("Click on Advance Search is failed");
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
	
		// Verification of First Consumer Details
		
		if (doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5)) {
			 WebElement advsrch = driver.findElement(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", advsrch);
			 log.logLine(Testname, false, "Click on Advance Search is successfull");
		 } else {
			 log.logLine(Testname, true, "Click on Advance Search is failed");
			 driver.switchTo().defaultContent();
			 throw new Exception("Click on Advance Search is failed");
		 }
		
		
		
	
			//Clicking on Clear Button	 
		if (doesElementExist2(properties.getProperty("ClearbtnAdsrch"), 5)) {
			WebElement Clrbtn = driver.findElement(By.cssSelector(properties.getProperty("ClearbtnAdsrch")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Clrbtn);	
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on clear button in advanced search dialog");
		} else {
			 log.logLine(Testname, true, "Clear button in advanced dialog does not exist");
			 driver.switchTo().defaultContent();
			 throw new Exception("Clear button in advanced dialog does not exist");
		 }
		
		
		
	     if (doesElementExist2(properties.getProperty("Alldts"), 5)) {
			 WebElement btn = driver.findElement(By.cssSelector(properties.getProperty("Alldts")));
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
			 log.logLine(Testname, false, "Click on All Date Checkbox is Successfull");
		 } else {
			 log.logLine(Testname, true, "Click on All Date Checkbox is Failed");
			 driver.switchTo().defaultContent();
			 throw new Exception("Click on All Date Checkbox is Failed");
		 }
	 
	     Thread.sleep(3000);			
		 if (doesElementExist2(properties.getProperty("Fieldbutton"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("Fieldbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(1000);
			
			log.logLine(Testname, false, "Clicking on Seacrh criteria Field drop down list in advance search");
			
			if (doesElementExist2(properties.getProperty("SelFieltOpt"), 5)) {
				
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelFieltOpt")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("CEDULA")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the CEDULA Field option from the dropdown");
						break;
					}						
				}
			} else {				
				log.logLine(Testname, true, "Selecting the field option is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Selecting the field option is failed");				
			}		
			
		 }else {
			 log.logLine(Testname, true, "Clicking on the Field is failed");
			 driver.switchTo().defaultContent();
			 throw new Exception("Clicking on the Field is failed");
		 }
		 
			
		 if (doesElementExist2(properties.getProperty("Operatorbutton"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("Operatorbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(1000);
			
			log.logLine(Testname, false, "Clicking on Seacrh criteria Operator drop down list in advance search");
			
			if (doesElementExist2(properties.getProperty("SelOpertOpt"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelOpertOpt")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Equals")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Equals operator option from the dropdown");
						break;
					}						
				}
			} else {				
				log.logLine(Testname, true, "Selecting the operator option is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Selecting the operator option is failed");				
			}		
		}else {				
			log.logLine(Testname, true, "Clicking on the operator is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on the operator is failed");				
		}
	
		if (doesElementExist2(properties.getProperty("Txtfld"), 5)) {	  
			 WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("Txtfld")));
			 Txt.clear();
			 Txt.sendKeys(FirCons);
			 log.logLine(Testname, false, "The Enterd value in text field is"  +FirCons);		 
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
		
		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("Chooseactn"), 5)) {
			 WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Chooseactn")));
			 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",btnsrch);
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			 Thread.sleep(1000);
			 log.logLine(Testname, false, "Clicking on Choose action button is successfull");
			} else {
				log.logLine(Testname, true, "Clicking on Choose action button is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on Choose action button is failed");
				}
		
		Thread.sleep(1000);
		if(doesElementExist2(properties.getProperty("Reprint"), 5)){
			 List<WebElement> Rept = driver.findElements(By.cssSelector(properties.getProperty("Reprint")));
			 for (WebElement lnk:Rept) {
				 if (lnk.getText().contains("Reprint (In Reprint Queue)")){
					 log.logLine(Testname, false, "The Reprint option is in Reprint (In Reprint Queue) status ");
					 break;
					}else if(lnk.getText().contains("Reprint (Last Reprint Date:)")){
						Actions actions = new Actions(driver);
						log.logLine(Testname, false, "The Reprint option is in Reprint (Last Reprint Date:) status");
						break;
						}
				 }
		}  
		
		// Verification for second Consumer
	
		if (doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5)) {
			 WebElement advsrch = driver.findElement(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",advsrch);
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", advsrch);
			 log.logLine(Testname, false, "Click on Advance Search is successfull");
		 } else {
			 log.logLine(Testname, true, "Click on Advance Search is failed");
			 driver.switchTo().defaultContent();
			 throw new Exception("Click on Advance Search is failed");
		 }
		
		//Clicking on Clear Button	 
		if (doesElementExist2(properties.getProperty("ClearbtnAdsrch"), 5)) {
			WebElement Clrbtn = driver.findElement(By.cssSelector(properties.getProperty("ClearbtnAdsrch")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Clrbtn);	
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on clear button in advanced search dialog");
		} else {
			 log.logLine(Testname, true, "Clear button in advanced dialog does not exist");
			 driver.switchTo().defaultContent();
			 throw new Exception("Clear button in advanced dialog does not exist");
		 }
		
		
		
	     if (doesElementExist2(properties.getProperty("Alldts"), 5)) {
			 WebElement btn = driver.findElement(By.cssSelector(properties.getProperty("Alldts")));
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
			 log.logLine(Testname, false, "Click on All Date Checkbox is Successfull");
		 } else {
			 log.logLine(Testname, true, "Click on All Date Checkbox is Failed");
			 driver.switchTo().defaultContent();
			 throw new Exception("Click on All Date Checkbox is Failed");
		 }
		 
	     
		 Thread.sleep(2000);
		 if (doesElementExist2(properties.getProperty("Fieldbutton"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("Fieldbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(1000);
			
			log.logLine(Testname, false, "Clicking on Seacrh criteria Field drop down list in advance search");
			
			if (doesElementExist2(properties.getProperty("SelFieltOpt"), 5)) {
				
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelFieltOpt")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("CEDULA")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						log.logLine(Testname, false, "Selecting the CEDULA Field option from the dropdown");
						break;
					}						
				}
			} else {				
				log.logLine(Testname, true, "Selecting the field option is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Selecting the field option is failed");				
			}		
			
		 }else {
			 log.logLine(Testname, true, "Clicking on the Field is failed");
			 driver.switchTo().defaultContent();
			 throw new Exception("Clicking on the Field is failed");
		 }
		 
			
		 if (doesElementExist2(properties.getProperty("Operatorbutton"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("Operatorbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(1000);
			
			log.logLine(Testname, false, "Clicking on Seacrh criteria Operator drop down list in advance search");
			
			if (doesElementExist2(properties.getProperty("SelOpertOpt"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelOpertOpt")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Equals")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						log.logLine(Testname, false, "Selecting the Equals operator option from the dropdown");
						break;
					}						
				}
			} else {				
				log.logLine(Testname, true, "Selecting the operator option is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Selecting the operator option is failed");				
			}		
		}else {				
			log.logLine(Testname, true, "Clicking on the operator is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on the operator is failed");				
		}
	
		if (doesElementExist2(properties.getProperty("Txtfld"), 5)) {	  
			 WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("Txtfld")));
			 Txt.clear();
			 Txt.sendKeys(SecCons);
			 log.logLine(Testname, false, "The Enterd value in text field is"  +SecCons);		 
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
			throw new Exception("Clicking on Search button is failed");
		}
		
	
	
		if (doesElementExist2(properties.getProperty("Chooseactn"), 5)) {
			 WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Chooseactn")));
			 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",btnsrch);
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			 Thread.sleep(1000);
			 log.logLine(Testname, false, "Clicking on Choose action button is successfull");
			} else {
				log.logLine(Testname, true, "Clicking on Choose action button is failed");
				throw new Exception("Clicking on Choose action button is failed");
				}
		
		Thread.sleep(1000);
		if(doesElementExist2(properties.getProperty("Reprint"), 5)){
			 List<WebElement> Rept = driver.findElements(By.cssSelector(properties.getProperty("Reprint")));
			 for (WebElement lnk:Rept) {
				 if (lnk.getText().contains("Reprint (In Reprint Queue)")){
					 log.logLine(Testname, false, "The Reprint option is in Reprint (In Reprint Queue) status ");
					 break;
					}else if(lnk.getText().contains("Reprint (Last Reprint Date:)")){
						log.logLine(Testname, false, "The Reprint option is in Reprint (Last Reprint Date:) status");
						break;
						}
				 }
		}  

		return true;
	}
}
 	
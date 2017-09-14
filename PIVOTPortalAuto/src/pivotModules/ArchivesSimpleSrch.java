package pivotModules;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import launchAuto.Initialization;
import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class ArchivesSimpleSrch extends Page{

	public ArchivesSimpleSrch(WebDriver driver, Log log) throws InvalidFormatException, IOException {
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
 			
 			// Switching back to parent window
		   driver.switchTo().window(SetWindow);
    
 			Thread.sleep(1000); 			
 			//driver.switchTo().frame("iframeContainer");
 			WebElement retelm2 = waitForElement(properties.getProperty("selClint1"));
 			
 			
 			driver.navigate().refresh();   
 			driver.switchTo().defaultContent();
 			
 			if (doesElementExist2(properties.getProperty("cancelbtn"), 5)) {    
				WebElement canlbtn = driver.findElement(By.cssSelector(properties.getProperty("cancelbtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", canlbtn);
				Thread.sleep(2000);
			    log.logLine(Testname, false, "Clicked on the cancel button of the clent/app selection pop up window");
		    } else {
			    log.logLine(Testname, true, "Clicking on the cancel button  of the clent/app selection pop up window is failed");
			    throw new Exception("Clicking on the cancel button of the clent/app selection pop up window is failed");
		    }
 			
 			if (doesElementExist2(properties.getProperty("Archives"), 5)) {    
				WebElement arclnk = driver.findElement(By.cssSelector(properties.getProperty("Archives")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", arclnk);
				Thread.sleep(3000);
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
				Thread.sleep(2000);
		   		PleasewaitDisappear();
		   		log.logLine(Testname, false, "Clicking on OK button to view the Archives");
 		    } else {
 		    	log.logLine(Testname, true, "Clicking on OK button to view the Archives is failed");
			    throw new Exception("Clicking on OK button to view the Archives is failed");
		    }
 		    
 		    
 		    
 		   Thread.sleep(10000);
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
 		    
			    
			return true;
	}
 	
 	public boolean ArchiveClientAppSel1(String AccNo, String Testname, String SetWindow) throws Exception {
 		  
			InputOutputData test = new InputOutputData();  
			test.setInputFile(properties.getProperty("InputDatafile"));
			String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
			
			// Switching back to parent window
			driver.switchTo().window(SetWindow);

			Thread.sleep(1000); 			
			//driver.switchTo().frame("iframeContainer");
		
			driver.switchTo().defaultContent();
			
			if (doesElementExist2(properties.getProperty("cancelbtn"), 5)) {    
			WebElement canlbtn = driver.findElement(By.cssSelector(properties.getProperty("cancelbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", canlbtn);
			Thread.sleep(2000);
		    log.logLine(Testname, false, "Clicked on the cancel button of the clent/app selection pop up window");
	    } else {
		    log.logLine(Testname, true, "Clicking on the cancel button  of the clent/app selection pop up window is failed");
		    throw new Exception("Clicking on the cancel button of the clent/app selection pop up window is failed");
	    }
			
			if (doesElementExist2(properties.getProperty("Archives"), 5)) {    
			WebElement arclnk = driver.findElement(By.cssSelector(properties.getProperty("Archives")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", arclnk);
			Thread.sleep(3000);
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
			Thread.sleep(2000);
	   		PleasewaitDisappear();
	   		log.logLine(Testname, false, "Clicking on OK button to view the Archives");
		    } else {
		    	log.logLine(Testname, true, "Clicking on OK button to view the Archives is failed");
		    throw new Exception("Clicking on OK button to view the Archives is failed");
	    }
		    
		    
		    
		   Thread.sleep(10000);
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
		    
		    
		return true;
}
 	
 	
 	
 	
 	
 	
 	
 
 	public boolean SimpleArchieveSearch(String AccNo, String Testname) throws Exception {
 		
 		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
	    	    
	    	    
	    Thread.sleep(2000);
	    driver.switchTo().frame("iframeContainer");
	    
	    String FirstName =test.readColumnData("FIRSTNAME", sheetname);
	    String CEDULA =test.readColumnData("CEDULA", sheetname);
	    String PlanNumber =test.readColumnData("PLANNUMBER", sheetname);
	    String StmtNumber=test.readColumnData("STATEMENTNUMBER", sheetname);
	    String PlanType=test.readColumnData("PLANTYPE", sheetname);
	    String LastName=test.readColumnData("LASTNAME", sheetname);
	    String ReprintAddr=test.readColumnData("REPRINTADDRESS", sheetname);
	    String Phone=test.readColumnData("PHONENUMBER", sheetname);
	    String FinancialAdvisor=test.readColumnData("FINANCIALADVISOR", sheetname);
	    String ZipCode=test.readColumnData("ZIPCODE", sheetname);
	    

	    //Check for Simple-search field and entering the text in it
    	WebElement retelm = waitForElement(properties.getProperty("SimplesearchTextBox"));
    	
	    if (doesElementExist2(properties.getProperty("SimplesearchTextBox"), 5)) {
	    	log.logLine(Testname, false, "Simple Search is enabled in Archives page");
	    	WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SimplesearchTextBox")));
	    			
	    	btnsrch.sendKeys(FirstName);
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
	    	PleasewaitDisappear();
			log.logLine(Testname, false, "Entered the search text "+FirstName+" in Simple Search ");
		} else {
			log.logLine(Testname, true, "Entering the search text "+FirstName+" in Simple Search failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the search text "+FirstName+" in Simple Search failed");
		}
	    
	    //Clicking on Magnifier Glass icon
	    if (doesElementExist2(properties.getProperty("Icon"), 5)) {
	    	WebElement mgnfierclick = driver.findElement(By.cssSelector(properties.getProperty("Icon")));
	    		((JavascriptExecutor) driver).executeScript("arguments[0].click()", mgnfierclick);
	    			Thread.sleep(2000);
	    			PleasewaitDisappear();
	    			log.logLine(Testname, false, "Clicking on search icon of the simple search");
		    		} else {
		    			log.logLine(Testname, true, "Clicking on search icon of the simple search is failed");
		    			driver.switchTo().defaultContent();
		    			throw new Exception("Clicking on search icon of the simple search is failed");
		    		}
	    // Verifing the Expected result with the actual result
	    if (doesElementExist(properties.getProperty("Items"), 5)) {	    
			String val = driver.findElement(By.xpath(".//*[@id='archive-search-grid']/div/span[2]")).getText();
			
				String arr[] = val.split("of");
				String actval = arr[1].trim();
				log.logLine(Testname, false, "The total number of documents displayed with the search text "+FirstName+" equal to :" +actval);
					
	    	}
	    
	    SearchResultsValidation(AccNo, Testname, FirstName);
	    
	    
	  //Check for Simplesearch field and entering the text in it
	    if (doesElementExist2(properties.getProperty("SimplesearchTextBox"), 5)) {
	    	WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SimplesearchTextBox")));
	    		btnsrch.clear();
	    		btnsrch.sendKeys(CEDULA);
	    			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
	    	
	    			PleasewaitDisappear();
	    			log.logLine(Testname, false, "Entered the search text "+CEDULA+" in Simple Search ");
	    			} else {
		    			log.logLine(Testname, true, "Entering the search text "+CEDULA+" in Simple Search failed");
		    			driver.switchTo().defaultContent();
		    			throw new Exception("Entering the search text "+CEDULA+" in Simple Search failed");
		    			}
	    
	    //Clicking on Magnifier Glass icon
	    	if (doesElementExist2(properties.getProperty("Icon"), 5)) {
		    	WebElement mgnfierclick = driver.findElement(By.cssSelector(properties.getProperty("Icon")));
		    		((JavascriptExecutor) driver).executeScript("arguments[0].click()", mgnfierclick);
		    			Thread.sleep(2000);
		    				PleasewaitDisappear();
		    					log.logLine(Testname, false, "Clicking on search icon of the simple search");
			    	} else {
			    		log.logLine(Testname, true, "Clicking on search icon of the simple search is failed");
			    		driver.switchTo().defaultContent();
			    		throw new Exception("Clicking on search icon of the simple search is failed");
			    		}
		    
		  
	    	// Verifing the Expected result with the actual result
	    		if (doesElementExist(properties.getProperty("Items"), 5)) {	    
				String val = driver.findElement(By.xpath(".//*[@id='archive-search-grid']/div/span[2]")).getText();
				
				String arr[] = val.split("of");
				String actval = arr[1].trim();
				
				log.logLine(Testname, false, "The total number of documents displayed with the search text "+CEDULA+" equal to :" +actval);
				
				}
	    		
	    		    		
	    		SearchResultsValidation(AccNo, Testname, CEDULA);
	    
	    
	    		//Check for Simplesearch field and entering the text in it
	    if (doesElementExist2(properties.getProperty("SimplesearchTextBox"), 5)) {
	    		WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SimplesearchTextBox")));
	    			btnsrch.clear();
	    			btnsrch.sendKeys(PlanNumber);
	    				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
	    	
	    				PleasewaitDisappear();
			    		log.logLine(Testname, false, "Entered the search text "+PlanNumber+" in Simple Search ");
			    		} else {
			    			log.logLine(Testname, true, "Entering the search text "+PlanNumber+" in Simple Search failed");
			    			driver.switchTo().defaultContent();
			    			throw new Exception("Entering the search text "+PlanNumber+" in Simple Search failed");
			    			}
	    
	    //Clicking on Magnifier Glass icon
	    if (doesElementExist2(properties.getProperty("Icon"), 5)) {
	    		WebElement mgnfierclick = driver.findElement(By.cssSelector(properties.getProperty("Icon")));
		    		((JavascriptExecutor) driver).executeScript("arguments[0].click()", mgnfierclick);
		    			Thread.sleep(2000);
		    			PleasewaitDisappear();
		    				log.logLine(Testname, false, "Clicking on search icon of the simple search");
			    		} else {
			    			log.logLine(Testname, true, "Clicking on search icon of the simple search is failed");
			    			driver.switchTo().defaultContent();
			    			throw new Exception("Clicking on search icon of the simple search is failed");
		    			}
	    
	    
	 // Verifing the Expected result with the actual result
	    if (doesElementExist(properties.getProperty("Items"), 5)) {	    
				String val = driver.findElement(By.xpath(".//*[@id='archive-search-grid']/div/span[2]")).getText();
				
				String arr[] = val.split("of");
				String actval = arr[1].trim();
				
				log.logLine(Testname, false, "The total number of documents displayed with the search text "+PlanNumber+" equal to :" +actval);
				
				}
	    
	    SearchResultsValidation(AccNo, Testname, PlanNumber);
	    
	    
	    if (doesElementExist2(properties.getProperty("SimplesearchTextBox"), 5)) {
	    	log.logLine(Testname, false, "Simple Search is enabled in Archives page");
	    		WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SimplesearchTextBox")));
	    		    btnsrch.clear();
	    			btnsrch.sendKeys(StmtNumber);
	    				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
	    	
	    				PleasewaitDisappear();
	    				log.logLine(Testname, false, "Entered the search text "+StmtNumber+" in Simple Search ");
	    				} else {
		    				log.logLine(Testname, true, "Entering the search text "+StmtNumber+" in Simple Search failed");
		    				driver.switchTo().defaultContent();
		    				throw new Exception("Entering the search text "+StmtNumber+" in Simple Search failed");
		    			}
	    
	    //Clicking on Magnifier Glass icon
	    if (doesElementExist2(properties.getProperty("Icon"), 5)) {
	    	WebElement mgnfierclick = driver.findElement(By.cssSelector(properties.getProperty("Icon")));
	    		((JavascriptExecutor) driver).executeScript("arguments[0].click()", mgnfierclick);
	    			Thread.sleep(2000);
	    			PleasewaitDisappear();
	    			log.logLine(Testname, false, "Clicking on search icon of the simple search");
		    		} else {
		    			log.logLine(Testname, true, "Clicking on search icon of the simple search is failed");
		    			driver.switchTo().defaultContent();
		    			throw new Exception("Clicking on search icon of the simple search is failed");
		    		}
	    // Verifing the Expected result with the actual result
	    if (doesElementExist(properties.getProperty("Items"), 5)) {	    
			String val = driver.findElement(By.xpath(".//*[@id='archive-search-grid']/div/span[2]")).getText();
			
				String arr[] = val.split("of");
				String actval = arr[1].trim();
				log.logLine(Testname, false, "The total number of documents displayed with the search text "+StmtNumber+" equal to :" +actval);
					
	    	}
	    
	    SearchResultsValidation(AccNo, Testname, StmtNumber);
	    
	    
	    if (doesElementExist2(properties.getProperty("SimplesearchTextBox"), 5)) {
	    	log.logLine(Testname, false, "Simple Search is enabled in Archives page");
	    		WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SimplesearchTextBox")));
	    		    btnsrch.clear();
	    			btnsrch.sendKeys(PlanType);
	    				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
	    	
	    				PleasewaitDisappear();
	    				log.logLine(Testname, false, "Entered the search text "+PlanType+" in Simple Search ");
	    				} else {
		    				log.logLine(Testname, true, "Entering the search text "+PlanType+" in Simple Search failed");
		    				driver.switchTo().defaultContent();
		    				throw new Exception("Entering the search text "+PlanType+" in Simple Search failed");
		    			}
	    
	    //Clicking on Magnifier Glass icon
	    if (doesElementExist2(properties.getProperty("Icon"), 5)) {
	    	WebElement mgnfierclick = driver.findElement(By.cssSelector(properties.getProperty("Icon")));
	    		((JavascriptExecutor) driver).executeScript("arguments[0].click()", mgnfierclick);
	    			Thread.sleep(2000);
	    			PleasewaitDisappear();
	    			log.logLine(Testname, false, "Clicking on search icon of the simple search");
		    		} else {
		    			log.logLine(Testname, true, "Clicking on search icon of the simple search is failed");
		    			driver.switchTo().defaultContent();
		    			throw new Exception("Clicking on search icon of the simple search is failed");
		    		}
	    // Verifing the Expected result with the actual result
	    if (doesElementExist(properties.getProperty("Items"), 5)) {	    
			String val = driver.findElement(By.xpath(".//*[@id='archive-search-grid']/div/span[2]")).getText();
			
				String arr[] = val.split("of");
				String actval = arr[1].trim();
				log.logLine(Testname, false, "The total number of documents displayed with the search text "+PlanType+" equal to :" +actval);
					
	    	}
	    
	    SearchResultsValidation(AccNo, Testname, PlanType);
	   
	    if (doesElementExist2(properties.getProperty("SimplesearchTextBox"), 5)) {
	    	log.logLine(Testname, false, "Simple Search is enabled in Archives page");
	    		WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SimplesearchTextBox")));
	    		    btnsrch.clear();
	    			btnsrch.sendKeys(LastName);
	    				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
	    	
	    				PleasewaitDisappear();
	    				log.logLine(Testname, false, "Entered the search text "+LastName+" in Simple Search ");
	    				} else {
		    				log.logLine(Testname, true, "Entering the search text "+LastName+" in Simple Search failed");
		    				driver.switchTo().defaultContent();
		    				throw new Exception("Entering the search text "+LastName+" in Simple Search failed");
		    			}
	    
	    //Clicking on Magnifier Glass icon
	    if (doesElementExist2(properties.getProperty("Icon"), 5)) {
	    	WebElement mgnfierclick = driver.findElement(By.cssSelector(properties.getProperty("Icon")));
	    		((JavascriptExecutor) driver).executeScript("arguments[0].click()", mgnfierclick);
	    			Thread.sleep(2000);
	    			PleasewaitDisappear();
	    			log.logLine(Testname, false, "Clicking on search icon of the simple search");
		    		} else {
		    			log.logLine(Testname, true, "Clicking on search icon of the simple search is failed");
		    			driver.switchTo().defaultContent();
		    			throw new Exception("Clicking on search icon of the simple search is failed");
		    		}
	    // Verifing the Expected result with the actual result
	    if (doesElementExist(properties.getProperty("Items"), 5)) {	    
			String val = driver.findElement(By.xpath(".//*[@id='archive-search-grid']/div/span[2]")).getText();
			
				String arr[] = val.split("of");
				String actval = arr[1].trim();
				log.logLine(Testname, false, "The total number of documents displayed with the search text "+LastName+" equal to :" +actval);
					
	    	}
	    
	    SearchResultsValidation(AccNo, Testname, LastName);	    
	   
	    
	    if (doesElementExist2(properties.getProperty("SimplesearchTextBox"), 5)) {
	    	log.logLine(Testname, false, "Simple Search is enabled in Archives page");
	    		WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SimplesearchTextBox")));
	    		    btnsrch.clear();
	    			btnsrch.sendKeys(ReprintAddr);
	    				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
	    	
	    				PleasewaitDisappear();
	    				log.logLine(Testname, false, "Entered the search text "+ReprintAddr+" in Simple Search ");
	    				} else {
		    				log.logLine(Testname, true, "Entering the search text "+ReprintAddr+" in Simple Search failed");
		    				driver.switchTo().defaultContent();
		    				throw new Exception("Entering the search text "+ReprintAddr+" in Simple Search failed");
		    			}
	    
	    //Clicking on Magnifier Glass icon
	    if (doesElementExist2(properties.getProperty("Icon"), 5)) {
	    	WebElement mgnfierclick = driver.findElement(By.cssSelector(properties.getProperty("Icon")));
	    		((JavascriptExecutor) driver).executeScript("arguments[0].click()", mgnfierclick);
	    			Thread.sleep(2000);
	    			PleasewaitDisappear();
	    			log.logLine(Testname, false, "Clicking on search icon of the simple search");
		    		} else {
		    			log.logLine(Testname, true, "Clicking on search icon of the simple search is failed");
		    			driver.switchTo().defaultContent();
		    			throw new Exception("Clicking on search icon of the simple search is failed");
		    		}
	    // Verifing the Expected result with the actual result
	    if (doesElementExist(properties.getProperty("Items"), 5)) {	    
			String val = driver.findElement(By.xpath(".//*[@id='archive-search-grid']/div/span[2]")).getText();
			
				String arr[] = val.split("of");
				String actval = arr[1].trim();
				log.logLine(Testname, false, "The total number of documents displayed with the search text "+ReprintAddr+" equal to :" +actval);
					
	    	}
	    
	    SearchResultsValidation(AccNo, Testname, ReprintAddr);
	    
	    
	    if (doesElementExist2(properties.getProperty("SimplesearchTextBox"), 5)) {
	    	log.logLine(Testname, false, "Simple Search is enabled in Archives page");
	    		WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SimplesearchTextBox")));
	    		    btnsrch.clear();
	    			btnsrch.sendKeys(Phone);
	    				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
	    	
	    				PleasewaitDisappear();
	    				log.logLine(Testname, false, "Entered the search text "+Phone+" in Simple Search ");
	    				} else {
		    				log.logLine(Testname, true, "Entering the search text "+Phone+" in Simple Search failed");
		    				driver.switchTo().defaultContent();
		    				throw new Exception("Entering the search text "+Phone+" in Simple Search failed");
		    			}
	    
	    //Clicking on Magnifier Glass icon
	    if (doesElementExist2(properties.getProperty("Icon"), 5)) {
	    	WebElement mgnfierclick = driver.findElement(By.cssSelector(properties.getProperty("Icon")));
	    		((JavascriptExecutor) driver).executeScript("arguments[0].click()", mgnfierclick);
	    			Thread.sleep(2000);
	    			PleasewaitDisappear();
	    			log.logLine(Testname, false, "Clicking on search icon of the simple search");
		    		} else {
		    			log.logLine(Testname, true, "Clicking on search icon of the simple search is failed");
		    			driver.switchTo().defaultContent();
		    			throw new Exception("Clicking on search icon of the simple search is failed");
		    		}
	    // Verifing the Expected result with the actual result
	    if (doesElementExist(properties.getProperty("Items"), 5)) {	    
			String val = driver.findElement(By.xpath(".//*[@id='archive-search-grid']/div/span[2]")).getText();
			
				String arr[] = val.split("of");
				String actval = arr[1].trim();
				log.logLine(Testname, false, "The total number of documents displayed with the search text "+Phone+" equal to :" +actval);
					
	    	}
	    
	    SearchResultsValidation(AccNo, Testname, Phone);
	    
	    
	    if (doesElementExist2(properties.getProperty("SimplesearchTextBox"), 5)) {
	    	log.logLine(Testname, false, "Simple Search is enabled in Archives page");
	    		WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SimplesearchTextBox")));
	    		    btnsrch.clear();
	    			btnsrch.sendKeys(FinancialAdvisor);
	    				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
	    	
	    				PleasewaitDisappear();
	    				log.logLine(Testname, false, "Entered the search text "+FinancialAdvisor+" in Simple Search ");
	    				} else {
		    				log.logLine(Testname, true, "Entering the search text "+FinancialAdvisor+" in Simple Search failed");
		    				driver.switchTo().defaultContent();
		    				throw new Exception("Entering the search text "+FinancialAdvisor+" in Simple Search failed");
		    			}
	    
	    //Clicking on Magnifier Glass icon
	    if (doesElementExist2(properties.getProperty("Icon"), 5)) {
	    	WebElement mgnfierclick = driver.findElement(By.cssSelector(properties.getProperty("Icon")));
	    		((JavascriptExecutor) driver).executeScript("arguments[0].click()", mgnfierclick);
	    			Thread.sleep(2000);
	    			PleasewaitDisappear();
	    			log.logLine(Testname, false, "Clicking on search icon of the simple search");
		    		} else {
		    			log.logLine(Testname, true, "Clicking on search icon of the simple search is failed");
		    			driver.switchTo().defaultContent();
		    			throw new Exception("Clicking on search icon of the simple search is failed");
		    		}
	    // Verifing the Expected result with the actual result
	    if (doesElementExist(properties.getProperty("Items"), 5)) {	    
			String val = driver.findElement(By.xpath(".//*[@id='archive-search-grid']/div/span[2]")).getText();
			
				String arr[] = val.split("of");
				String actval = arr[1].trim();
				log.logLine(Testname, false, "The total number of documents displayed with the search text "+FinancialAdvisor+" equal to :" +actval);
					
	    	}
	    
	    SearchResultsValidation(AccNo, Testname, FinancialAdvisor);
	    
	    
	    if (doesElementExist2(properties.getProperty("SimplesearchTextBox"), 5)) {
	    	log.logLine(Testname, false, "Simple Search is enabled in Archives page");
	    		WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SimplesearchTextBox")));
	    		    btnsrch.clear();
	    			btnsrch.sendKeys(ZipCode);
	    				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
	    	
	    				PleasewaitDisappear();
	    				log.logLine(Testname, false, "Entered the search text "+ZipCode+" in Simple Search ");
	    				} else {
		    				log.logLine(Testname, true, "Entering the search text "+ZipCode+" in Simple Search failed");
		    				driver.switchTo().defaultContent();
		    				throw new Exception("Entering the search text "+ZipCode+" in Simple Search failed");
		    			}
	    
	    //Clicking on Magnifier Glass icon
	    if (doesElementExist2(properties.getProperty("Icon"), 5)) {
	    	WebElement mgnfierclick = driver.findElement(By.cssSelector(properties.getProperty("Icon")));
	    		((JavascriptExecutor) driver).executeScript("arguments[0].click()", mgnfierclick);
	    			Thread.sleep(2000);
	    			PleasewaitDisappear();
	    			log.logLine(Testname, false, "Clicking on search icon of the simple search");
		    		} else {
		    			log.logLine(Testname, true, "Clicking on search icon of the simple search is failed");
		    			driver.switchTo().defaultContent();
		    			throw new Exception("Clicking on search icon of the simple search is failed");
		    		}
	    // Verifing the Expected result with the actual result
	    if (doesElementExist(properties.getProperty("Items"), 5)) {	    
			String val = driver.findElement(By.xpath(".//*[@id='archive-search-grid']/div/span[2]")).getText();
			
				String arr[] = val.split("of");
				String actval = arr[1].trim();
				log.logLine(Testname, false, "The total number of documents displayed with the search text "+ZipCode+" equal to :" +actval);
					
	    	}
	    
	    SearchResultsValidation(AccNo, Testname, ZipCode);	    
	    
	    driver.switchTo().defaultContent();
	    
	    return true;
 	}
 	
	
 	public boolean simpleArchiveSearch2(String Randno, String Testname) throws Exception {
 		
		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	    
	    
	    Thread.sleep(2000);
	    driver.switchTo().frame("iframeContainer");   
		      
				
		if (doesElementExist2(properties.getProperty("Simplesearchtextbox"), 5)) {	    
				    WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Simplesearchtextbox")));
				    btnsrch.clear();
				    //String Comments = test.readColumnData("SearchComments", sheetname);
				    String Cedula= Initialization.EightDig1;
					btnsrch.sendKeys(Cedula);
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
					//WebElement icon = driver.findElement(By.cssSelector(properties.getProperty("iconsearch")));
					//((JavascriptExecutor) driver).executeScript("arguments[0].click()", icon);
					
					 PleasewaitDisappear();
					 log.logLine(Testname, false, "Entering the search comments "+Cedula+" in simple search for verification of the documents");
		    } else {
					log.logLine(Testname, true, "Entering the search comments  in simple search for verification of the documents failed");
					driver.switchTo().defaultContent();
					throw new Exception("Entering the search comments  in simple search for verification of the documents failed");
			}
	
			
		if (doesElementExist2(properties.getProperty("iconsearch"), 5)) {	    
					WebElement mgnfierclick = driver.findElement(By.cssSelector(properties.getProperty("iconsearch")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", mgnfierclick);
					Thread.sleep(2000);
					PleasewaitDisappear();		
					log.logLine(Testname, false, "Clicking on search icon of the simple search");
		} else {
					log.logLine(Testname, true, "Clicking on search icon of the simple search is failed");
					driver.switchTo().defaultContent();
					throw new Exception("Clicking on search icon of the simple search is failed");
		}
		         
		Thread.sleep(2000);
	     if(doesElementExist2(properties.getProperty("ChoseAct"), 5)){	    	 	
		     Actions action = new Actions(driver);
		     List<WebElement> editbtn = driver.findElements(By.cssSelector(properties.getProperty("ChoseAct")));
		     for (WebElement lnk:editbtn) {
		    	 
		    	 if (lnk.getText().equals("Choose Action...")){
		    		 action.click(lnk).perform();	
		    		 Thread.sleep(2000);
		    		 log.logLine(Testname, false, "Clicking on choose actions of the searched document in simple search");
		    		 break;
		    		 } 
		    	 }
		     }

	     Thread.sleep(3000);
	     if(doesElementExist2(properties.getProperty("seleditbtn"), 5)){	    	 	
		     Actions action = new Actions(driver);
		     List<WebElement> editbtn = driver.findElements(By.cssSelector(properties.getProperty("seleditbtn")));
		     for (WebElement lnk:editbtn) {
		    	 
		    	 if (lnk.getText().equals("Edit")){
		    		 //action.click(lnk).perform();
		    		 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
		    		 Thread.sleep(2000);
		    		 log.logLine(Testname, false, "Selecting the Edit option from the choose action drop down list in simple search");
		    		 break;
		    		 } 
		    	 }
		     }
	     
	     DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
			Date date = new Date();
			String todaysDate = dateFormat.format(date);
	     	
			if (doesElementExist2(properties.getProperty("caldate"), 5)) {	    
			     WebElement selcalendbtn = driver.findElement(By.cssSelector(properties.getProperty("caldate")));
			     Thread.sleep(2000);
			     selcalendbtn.clear();
			     selcalendbtn.sendKeys(todaysDate);
			     log.logLine(Testname, false, "Entered the current date  "+todaysDate+" in calender textarea");
			} else {
			     log.logLine(Testname, true, "Entering the current date  "+todaysDate+" in calender textarea is failed");
			     driver.switchTo().defaultContent();
			     throw new Exception("Entering the current date  "+todaysDate+" in calender textarea is failed");
			}

		     
	     String stmtnum = "AutoArchive_"+Randno;	    		 
	     if (doesElementExist2(properties.getProperty("stmtno"), 5)) {
	    	 WebElement stmtno = driver.findElement(By.cssSelector(properties.getProperty("stmtno")));
	    	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", stmtno);
	    	 Thread.sleep(5000);
	    	 stmtno.clear();
	    	 stmtno.sendKeys(stmtnum);
	    	 log.logLine(Testname, false, "Eneter the statement no. "+stmtnum+" in editbeta form of the Archives ");
	    	 
	    	 } else {
	    		 log.logLine(Testname, true, "Enetering the statement no. "+stmtnum+" in editbeta form of the Archives is failed");
	    		 driver.switchTo().defaultContent();
	    		 throw new Exception("Enetering the statement no. "+stmtnum+" in editbeta form of the Archives is failed");
		}
  
     			
	
		String FileUplpad = AdminConfig.EDITbetaPDFCreate(Randno);
		if (doesElementExist2(properties.getProperty("browsebtn"), 5)) {
			driver.findElement(By.cssSelector(properties.getProperty("browsebtn"))).sendKeys(FileUplpad);
			Thread.sleep(2000);
		    log.logLine(Testname, false, "click on browse button to upload a pdf file "+FileUplpad+" ");
		} else {
		      log.logLine(Testname, true, "clicking on browse button to upload a pdf file "+FileUplpad+" is failed");
		      driver.switchTo().defaultContent();
		      throw new Exception("clicking on browse button to upload a pdf file "+FileUplpad+" is failed");
		}
		
		String emailadrr= test.readColumnData("EmailId", sheetname);
		if (doesElementExist2(properties.getProperty("email"), 5)) {	    
		    WebElement useremail = driver.findElement(By.cssSelector(properties.getProperty("email")));
		    useremail.clear();
		    useremail.sendKeys(emailadrr);
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", useremail);
		    log.logLine(Testname, false, "Entered the Email address as "+emailadrr+" in userid textfield in the editbeta form");
		} else {
		     log.logLine(Testname, true, "Entering the Email address in userid textfield in the editbeta form is failed");
		     driver.switchTo().defaultContent();
		     throw new Exception("Entering the Email address in userid textfield in the editbeta form is failed");
		}	
		
		
		String password= test.readColumnData("Password", sheetname);
		if (doesElementExist2(properties.getProperty("password"), 5)) {	    
		    WebElement pass = driver.findElement(By.cssSelector(properties.getProperty("password")));
		    pass.clear();
		    pass.sendKeys(password);
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", pass);
		    log.logLine(Testname, false, "Entered password value in password textfield in the editbeta form");
		} else {
		     log.logLine(Testname, true, "Entering the password value in password textfield is failed");
		     //driver.switchTo().defaultContent();
		     //throw new Exception("Entering the password in password textfield is failed");
		}
		
		
		if (doesElementExist2(properties.getProperty("yescheckbox"), 5)) {	    
		    WebElement yesbox = driver.findElement(By.cssSelector(properties.getProperty("yescheckbox")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", yesbox);
		    log.logLine(Testname, false, "Checking the check box before the yes label of the editbeta form");
		} else {
		     log.logLine(Testname, true, "Checking the check box before the yes label of the editbeta form is failed");
		     driver.switchTo().defaultContent();
		     throw new Exception("Clicking the check box before the yes label of the editbeta form is failed");
		}
		
		
		if (doesElementExist2(properties.getProperty("savebtn"), 5)) {	    
		    WebElement savebtn = driver.findElement(By.cssSelector(properties.getProperty("savebtn")));
		    savebtn.click();
		  //  ((JavascriptExecutor) driver).executeScript("arguments[0].click()", savebtn);
		   // Uploading();
		    Thread.sleep(8000);
		   // PleasewaitDisappear();		   
		    log.logLine(Testname, false, "Clicking on the save button of the EditBeta form is successfull");
		} else {
		     log.logLine(Testname, true, "Clicking on the save button of the EditBeta form is failed");
		     driver.switchTo().defaultContent();
		     throw new Exception("Clicking on the save button of the EditBeta form is failed");
		}
		/*
		// waitUntilUploadingElementDisappear();
		 log.logLine(Testname, false, "Checking whether the clicking on save button shows any errors on the form..."); 
		if (doesElementExist2(properties.getProperty("savebtn"), 5)) {
			log.logLine(Testname, false, "EditBeta - No-Errors after clicking on Save button");
		}else {
		     log.logLine(Testname, true, "EditBeta-Errors After Clicking the save button is successfull");
		     driver.switchTo().defaultContent();
		     throw new Exception("EditBeta-Errors After Clicking the save button is successfull");
		}
		 */  
		
	/*
		Thread.sleep(5000);	
		Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		
		robot.keyRelease(KeyEvent.VK_CONTROL);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	*/
		Thread.sleep(20000);
	    WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
		  
		/*  
	    Thread.sleep(5000);	
	    if(isAlertPresent()){
	    	
	    	log.logLine(Testname, false, "Alert Exists---->Click Ok on Alert pop up is successfull");
	  }else {
		     log.logLine(Testname, true, "Alert does not Exists");
	  }
	*/
		
		
		/*
		if (doesElementExist(properties.getProperty("EditOkBtn1"), 5)) {	    
		    WebElement okbtn = driver.findElement(By.xpath(".//*[@id='modal-alert-ok']"));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
		    log.logLine(Testname, false, "Clicking the Ok button of the Edit form once the modification is completed ");
		} else {
		     log.logLine(Testname, true, "Clicking the Ok button of the Edit form failed");
		     driver.switchTo().defaultContent();
		     throw new Exception("Clicking the Ok button of the Edit form failed");
		}
		
		
		waitUntilLoadElementDisappear4();
		*/
		Thread.sleep(5000);
	     if(doesElementExist2(properties.getProperty("ChoseAct1"), 5)){	    	 	
		     Actions action = new Actions(driver);
		     List<WebElement> editbtn = driver.findElements(By.cssSelector(properties.getProperty("ChoseAct1")));
		     for (WebElement lnk:editbtn) {
		    	 
		    	 if (lnk.getText().equals("Choose Action...")){
		    		 action.click(lnk).perform();	
		    		 Thread.sleep(2000);
		    		 log.logLine(Testname, false, "Clicking on choose actions of the searched document in simple search");
		    		 break;
		    		 } 
		    	 }
	     }

	     
	     if(doesElementExist2(properties.getProperty("seleditbtn"), 5)){	    	 	
		     Actions action = new Actions(driver);
		     List<WebElement> editbtn = driver.findElements(By.cssSelector(properties.getProperty("seleditbtn")));
		     for (WebElement lnk:editbtn) {
		    	 
		    	 if (lnk.getText().equals("Edit")){
		    		 //action.click(lnk).perform();
		    		 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
		    		 
		    		 log.logLine(Testname, false, "Selecting the Edit option from the choose action drop down list in simple search");
		    		 break;
		    		 } 
		    	 }
	     }
	     
	     Thread.sleep(3000);
		if (doesElementExist2(properties.getProperty("caldate"), 5)) {	    
		     WebElement selcalendbtn = driver.findElement(By.cssSelector(properties.getProperty("caldate")));		   
		     selcalendbtn.clear();
		     selcalendbtn.sendKeys(todaysDate);
		     log.logLine(Testname, false, "Entered the current date  "+todaysDate+" in calender textarea");
	    } else {
		     log.logLine(Testname, true, "Entering the current date  "+todaysDate+" in calender textarea is failed");
		     driver.switchTo().defaultContent();
		     throw new Exception("Entering the current date  "+todaysDate+" in calender textarea is failed");
		}
		
		String FileUplpad1 = AdminConfig.EDITbetaPDFCreate1(Randno);
		if (doesElementExist2(properties.getProperty("browsebtn"), 5)) {
			driver.findElement(By.cssSelector(properties.getProperty("browsebtn"))).sendKeys(FileUplpad1);
			Thread.sleep(2000);
		    log.logLine(Testname, false, "click on browse button to upload a pdf file "+FileUplpad1+" ");
		} else {
		      log.logLine(Testname, true, "clicking on browse button to upload a pdf file "+FileUplpad1+" is failed");
		      driver.switchTo().defaultContent();
		      throw new Exception("clicking on browse button to upload a pdf file "+FileUplpad1+" is failed");
		}
		
		String stmtnum1 = "AutoArchive1_"+Randno;	    		 
	     if (doesElementExist2(properties.getProperty("stmtno"), 5)) {
	    	 WebElement stmtno = driver.findElement(By.cssSelector(properties.getProperty("stmtno")));
	    	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", stmtno);
	    	 Thread.sleep(5000);
	    	 stmtno.clear();
	    	 stmtno.sendKeys(stmtnum1);
	    	 log.logLine(Testname, false, "Eneter the statement no. "+stmtnum1+" in editbeta form of the Archives ");
	    	 
	    	 } else {
	    		 log.logLine(Testname, true, "Enetering the statement no. "+stmtnum1+" in editbeta form of the Archives is failed");
	    		 driver.switchTo().defaultContent();
	    		 throw new Exception("Enetering the statement no. "+stmtnum1+" in editbeta form of the Archives is failed");
   	 }
		
		String emailadrr1= test.readColumnData("EmailId", sheetname);
		if (doesElementExist2(properties.getProperty("email"), 5)) {	    
		    WebElement useremail = driver.findElement(By.cssSelector(properties.getProperty("email")));
		    useremail.clear();
		    useremail.sendKeys(emailadrr1);
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", useremail);
		    log.logLine(Testname, false, "Entered the Email address as "+emailadrr+" in userid textfield in the editbeta form");
		} else {
		     log.logLine(Testname, true, "Entering the Email address in userid textfield in the editbeta form is failed");
		     driver.switchTo().defaultContent();
		     throw new Exception("Entering the Email address in userid textfield in the editbeta form is failed");
		}	
		
		
		String password1= test.readColumnData("Password", sheetname);
		if (doesElementExist2(properties.getProperty("password"), 5)) {	    
		    WebElement pass = driver.findElement(By.cssSelector(properties.getProperty("password")));
		    pass.clear();
		    pass.sendKeys(password1);
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", pass);
		    log.logLine(Testname, false, "Entered password value in password textfield in the editbeta form");
		} else {
		     log.logLine(Testname, true, "Entering the password value in password textfield is failed");
		     //driver.switchTo().defaultContent();
		     //throw new Exception("Entering the password in password textfield is failed");
		}
		
		if (doesElementExist2(properties.getProperty("savebtn"), 5)) {	    
		    WebElement savebtn = driver.findElement(By.cssSelector(properties.getProperty("savebtn")));
		    savebtn.click();
		    //((JavascriptExecutor) driver).executeScript("arguments[0].click()", savebtn);
		   // Uploading();
		    Thread.sleep(8000);
		   // PleasewaitDisappear();		   
		    log.logLine(Testname, false, "Clicking on the save button of the EditBeta form is successfull");
		} else {
		     log.logLine(Testname, true, "Clicking on the save button of the EditBeta form is failed");
		     driver.switchTo().defaultContent();
		     throw new Exception("Clicking on the save button of the EditBeta form is failed");
		}
		
		/*
		 waitUntilUploadingElementDisappear();
		 log.logLine(Testname, false, "Checking whether the clicking on save button shows any errors on the form..."); 
		if (doesElementExist2(properties.getProperty("savebtn"), 5)) {
			log.logLine(Testname, false, "EditBeta - No-Errors after clicking on Save button");
			Thread.sleep(4000);
		}else {
		     log.logLine(Testname, true, "EditBeta-Errors After Clicking the save button is successfull");
		     driver.switchTo().defaultContent();
		     throw new Exception("EditBeta-Errors After Clicking the save button is successfull");
		}
		
		   
		Thread.sleep(2000);			
		if (doesElementExist(properties.getProperty("EditOkBtn1"), 5)) {	    
		    WebElement okbtn = driver.findElement(By.xpath(".//*[@id='modal-alert-ok']"));
		   ((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
		    log.logLine(Testname, false, "Clicking the Ok button of the Edit form once the modification is completed ");
		} else {
		     log.logLine(Testname, true, "Clicking the Ok button of the Edit form failed");
		     driver.switchTo().defaultContent();
		     throw new Exception("Clicking the Ok button of the Edit form failed");
		}
		
		waitUntilLoadElementDisappear4();
		*/
/*
		Thread.sleep(5000);	
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		
		robot.keyRelease(KeyEvent.VK_CONTROL);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	*/
		
		Thread.sleep(25000);
	    WebDriverWait wait1 = new WebDriverWait(driver, 2);
        wait1.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
	/*
	    Thread.sleep(5000);	
		if(isAlertPresent()){
		    	
		    	log.logLine(Testname, false, "Alert Exists---->Click Ok on Alert pop up is successfull");
		  }else {
			     log.logLine(Testname, true, "Alert does not Exists");
		  }
	    */
        
		Thread.sleep(5000);
	
		if (doesElementExist2(properties.getProperty("Simplesearchtextbox"), 5)) {			
		    WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Simplesearchtextbox")));
		    btnsrch.clear();		    
			btnsrch.sendKeys(stmtnum);
			
			WebElement icon = driver.findElement(By.cssSelector(properties.getProperty("iconsearch")));			
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", icon);
			
			PleasewaitDisappear();
			Thread.sleep(4000);
			log.logLine(Testname, false, "Verification for the editted document with the statement number "+stmtnum+"  ");
	    } else {
			log.logLine(Testname, true, "Verification for the editted document with the statement number is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Verification for the editted document with the statement number is failed");
		}
			
		//Verification for the edited text file.		
		if (isTextPresent(stmtnum)) {
			log.logLine(Testname, false, "Edit Statement number -"+stmtnum+" is present in the result columns");	
			log.logLine(Testname, false, "Archive simple search - verfication of Editbeta is successsful");	    
		} else {
			log.logLine(Testname, true, "Archive simple search - verfication of Editbeta is unsuccesssful");		     
		}
		
		driver.switchTo().defaultContent();
		return true;
    }
 	
 	public boolean isAlertPresent()	{ 
	    try 
	    { 
	    	Alert alert = driver.switchTo().alert(); 
	    	alert.accept();
	        
	        return true; 
	    }   // try 
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }
	}	
 	
 	public boolean SearchResultsValidation(String AccNo, String Testname, String SearchCriteria) throws Exception {
 		
 		
 		log.logLine(Testname, false, "Verification and presence of the search criteria text in simple search with the result grid values starts here....");
 		
 		
 		WebElement Webtable=driver.findElement(By.id("archive-search-grid")); // Replace TableID with Actual Table ID or Xpath
 		
 		List<WebElement> TotalRowCount= Webtable.findElements(By.xpath("//div[@id='archive-search-grid']/table/tbody/tr"));
 		
        
 		log.logLine(Testname, false, "Total No. of Rows in the WebTable which contains search criteria data : " +TotalRowCount.size());
 		
 		
 		//System.out.println("No. of Rows in the WebTable: "+TotalRowCount.size());
 		// Now we will Iterate the Table and print the Values   
 		int RowIndex=1;
 		
 		for(WebElement rowElement:TotalRowCount)
 			{
 			
 		      List<WebElement> TotalColumnCount=rowElement.findElements(By.xpath("td"));
 		      //log.logLine(Testname, false, "No. of columns in the WebTable:" +TotalColumnCount.size());
 		      int ColumnIndex=0;
 		      for(WebElement colElement:TotalColumnCount){
 		    	  /*
 		    	  
 		    	  if(ColumnIndex == (TotalColumnCount.size()-2)){ 		    		
			    		Actions action = new Actions(driver);
			    		action.moveToElement(colElement).perform(); 
			    		   		
			    		
			     		List<WebElement> Tooltip = Webtable.findElements(By.cssSelector("div[class='k-tooltip-content'] div table tbody tr"));
			     		for(WebElement EachTTdata:Tooltip) {
			     			
			     			String InfoTT = EachTTdata.findElement(By.cssSelector("div[class='k-tooltip-content'] div table tbody tr td+td")).getText();
			     			if (InfoTT.contains(SearchCriteria)) {		    		
			     				log.logLine(Testname, false, "Search criteria - "+SearchCriteria+" matches with the Row "+RowIndex+" and Column "+ColumnIndex+" data - "+InfoTT);
			 		        	break;
				    		} else {
								log.logLine(Testname, true, "Tooltip box may not be exist");		
							}
			     		}
				    		
					} else {
						log.logLine(Testname, true, "View audit extra details field is not exist");		
					} 
					*/		    	   		    	  
 		    	  
 		    	  //System.out.println("Row "+RowIndex+" Column "+ColumnIndex+" Data "+colElement.getText());
 		          log.logLine(Testname, false, "Row "+RowIndex+" Column "+ColumnIndex+" contains Data  "+colElement.getText());
 		          
 		          if(colElement.getText().contains(SearchCriteria)){
 		        	  log.logLine(Testname, false, "Search criteria data "+SearchCriteria+" matches with the Row "+RowIndex+" _ Column "+ColumnIndex+" data "+colElement.getText());
 		        	  break;
 		        	  
 		        	 //System.out.println("The result data matches with the search value");
 		        	  }
 		          
 		          ColumnIndex=ColumnIndex+1;
 		          //System.out.println("Column count is:" +ColumnIndex);
 		          }
 		      
 		      RowIndex=RowIndex+1;
 		      
 		      }
 		return true;
 		}

}


package pivotModules;

import java.io.IOException;
import java.util.List;
import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class JobTrackingSearches extends Page{
	
	
	public static String ClntName;
	public static String AppName;
	public static String JobClientname;
	public static String JobAppname;

	
	public JobTrackingSearches(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}	
	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}
	
	public boolean ClientAppSel(String RandNo, String Testname) throws Exception {
		
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
		   	log.logLine(Testname, false, "Clicking on OK button");
		} else {
		    log.logLine(Testname, true, "Clicking on OK button to view the Reports is failed");
		    throw new Exception("Clicking on OK button to view the Reports is failed");
		}
     
	    
	    return true;
	}
		
	
public boolean ClientAppvalidation(String AccNo,String Testname) throws Exception {
	
	
	 InputOutputData test = new InputOutputData();		
	 test.setInputFile(properties.getProperty("InputDatafile"));
	 String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	
	 driver.switchTo().defaultContent();	
		
	 if (doesElementExist2(properties.getProperty("JobTracking"), 5)) {	    
			WebElement jobsmnu = driver.findElement(By.cssSelector(properties.getProperty("JobTracking")));
			
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", jobsmnu);
			PleasewaitDisappear();
			Thread.sleep(4000);
			log.logLine(Testname, false, "Navigation to JobTracking page is successful");
	    } else {
			log.logLine(Testname, true, "Clicking on JobTracking menu is failed");
			throw new Exception("Clicking on JobTracking menu is failed");
		}	 
	 
	 
	Thread.sleep(5000);
    driver.switchTo().frame("iframeContainer");
    
    
    
    if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
    	WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
    	Thread.sleep(2000);
	   	PleasewaitDisappear();
	   	log.logLine(Testname, false, "Clicking on Search button");
	} else {
	    log.logLine(Testname, true, "Clicking on Search is failed");
	    throw new Exception("Clicking on Search is failed");
	}
 
    driver.switchTo().defaultContent();
	Thread.sleep(2000);
	 
	 if (doesElementExist2(properties.getProperty("cancelpopupbtn"), 5)) {	    
			String cancel = driver.findElement(By.cssSelector(properties.getProperty("cancelpopupbtn"))).getText();
			WebElement cancl = driver.findElement(By.cssSelector(properties.getProperty("cancelpopupbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", cancl);
			Thread.sleep(2000);
			log.logLine(Testname, false, "**** Client App pop up appeared in order to perform actions for jobtracking ****");
	    } else {
			log.logLine(Testname, true, "!!!! Client App pop up did not appear in order to perform actions for jobtracking !!!!");			
		}
	
	 driver.switchTo().defaultContent();
	 Thread.sleep(2000);
	 
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
    JobClientname = test.readColumnData("ClientName", sheetname);
    
    if (doesElementExist2(properties.getProperty("selClint1"), 5)) {
    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selClint1")));
    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
    	Thread.sleep(1000);
    	log.logLine(Testname, false, "Clicking on ClientName dropdown..");
    	
		
		if (doesElementExist2(properties.getProperty("ClinetOpts1"), 5)) {
			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ClinetOpts1")));
			for (WebElement lnk:selopts) {
				if (lnk.getText().contains(JobClientname)) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Selecting the ClientName "+JobClientname +" from the popup..");
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
					if (lnk.getText().contains(JobClientname)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the ClientName "+JobClientname +" from the dropdown..");							
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
    JobAppname = test.readColumnData("ApplicationName", sheetname);
    
    if (doesElementExist2(properties.getProperty("selAppl1"), 5)) {	   
    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selAppl1")));
    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
    	Thread.sleep(1000);
		log.logLine(Testname, false, "Clicking on ApplicationName dropdown..");
		
		if (doesElementExist2(properties.getProperty("ApplOpts1"), 5)) {
			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ApplOpts1")));
			for (WebElement lnk:selopts) {
				if (lnk.getText().contains(JobAppname)) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Selecting the Application Name "+JobAppname +" from the popup..");
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
					if (lnk.getText().contains(JobAppname)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Application Name "+JobAppname +" from the dropdown..");							
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
	   	log.logLine(Testname, false, "Clicking on OK button");
	} else {
	    log.logLine(Testname, true, "Clicking on OK button to view the Reports is failed");
	    throw new Exception("Clicking on OK button to view the Reports is failed");
	}
 
    Thread.sleep(4000);
    if (doesElementExist2(properties.getProperty("JobTracking"), 5)) {	    
		WebElement jobsmnu = driver.findElement(By.cssSelector(properties.getProperty("JobTracking")));
		
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", jobsmnu);
		PleasewaitDisappear();
		Thread.sleep(4000);
		log.logLine(Testname, false, "**** Navigation to JobTracking page is successful ****");
    } else {
		log.logLine(Testname, true, "!!!! Clicking on JobTracking menu is failed !!!!");
		throw new Exception("!!!! Clicking on JobTracking menu is failed !!!!");
	}
    
    
    Thread.sleep(5000);
    driver.switchTo().frame("iframeContainer");
    
    if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
    	WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
    	Thread.sleep(2000);
	   	PleasewaitDisappear();
	   	log.logLine(Testname, false, "Clicking on Search button");
	} else {
	    log.logLine(Testname, true, "Clicking on Search is failed");
	    throw new Exception("Clicking on Search is failed");
	}
    
    Thread.sleep(5000);
    if (doesElementExist2(properties.getProperty("Cancelbtn"), 5)) {
    	WebElement cnclbtn = driver.findElement(By.cssSelector(properties.getProperty("Cancelbtn")));
    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnclbtn);
    	Thread.sleep(1000);
	   	PleasewaitDisappear();
	   	log.logLine(Testname, false, "Clicking on Cancel button");
	} else {
	    log.logLine(Testname, true, "Clicking on Cancel is failed");
	    throw new Exception("Clicking on Cancel is failed");
	}
    
    
    Thread.sleep(2000);
	driver.switchTo().defaultContent();
    
	Thread.sleep(5000);
    if (doesElementExist(properties.getProperty("VerifyClientname"), 5)) {	    
		String clntvldn = driver.findElement(By.xpath(properties.getProperty("VerifyClientname"))).getText();
		if(clntvldn.contains(JobClientname)){
			log.logLine(Testname, false, "**** Client App pop did not appear to perform job tracking actions and Client Name "+clntvldn+" Matches with the "+JobClientname+" ****");
		} else {
			log.logLine(Testname, true, "!!!! Client App pop appeared to perform actions in jobtracking !!!!");
			}
	 } else {
			log.logLine(Testname, true, "!!!! Failed to validate the Client Name for in JobTracking !!!!");
			}
	 
	 if (doesElementExist(properties.getProperty("VerifyAppname"), 5)) {	    
		String appvldn = driver.findElement(By.xpath(properties.getProperty("VerifyAppname"))).getText();
		if(appvldn.contains(JobAppname)){
			log.logLine(Testname, false, "**** Client App pop did not appear to perform job tracking actions and Application Name "+appvldn+" Matches with the "+JobAppname+" ****");
		} else {
			log.logLine(Testname, true, "!!!! Client App pop appeared to perform actions in jobtracking !!!!");
			}
	 } else {
			log.logLine(Testname, true, "!!!! Failed to validate the Application Name in JobTracking !!!!");
			}
	 
	 
	 Thread.sleep(8000);
	 driver.switchTo().frame("iframeContainer");
	 
	 if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
    	WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
    	Thread.sleep(2000);
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
					 Actions action = new Actions(driver);
			    	   action.moveByOffset(10, 0).doubleClick();
					Thread.sleep(2000);
					log.logLine(Testname, false, "**** Selecting the Search Type Name as Quick (Order) from the dropdown ****");							
					break;
				}				
			}
			
		} else {
			log.logLine(Testname, true, "!!!! Search Type options are not displayed !!!!");
			throw new Exception("!!!! Search Type options are not displayed !!!!");
		}
    }
    
    Thread.sleep(2000);
    if (doesElementExist2(properties.getProperty("Cancelbtn"), 5)) {
    	WebElement cnclbtn = driver.findElement(By.cssSelector(properties.getProperty("Cancelbtn")));
    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnclbtn);
    	Thread.sleep(1000);
	   	PleasewaitDisappear();
	   	log.logLine(Testname, false, "Clicking on Cancel button");
	} else {
	    log.logLine(Testname, true, "Clicking on Cancel is failed");
	    throw new Exception("Clicking on Cancel is failed");
	}
    
    Thread.sleep(2000);
    driver.switchTo().defaultContent();
	    
	Thread.sleep(5000);
    if (doesElementExist(properties.getProperty("VerifyClientname"), 5)) {	    
		String clntvldn = driver.findElement(By.xpath(properties.getProperty("VerifyClientname"))).getText();
		if(clntvldn.contains(JobClientname)){
			log.logLine(Testname, false, "**** Client Name "+clntvldn+" for Quick (Order) Search Matches with the "+JobClientname+" selected for JobTracking Module ****");
		} else {
			log.logLine(Testname, true, "!!!! Client Name "+clntvldn+" doesnot Matches with the "+JobClientname+" !!!!");
			}
	 } else {
			log.logLine(Testname, true, "!!!! Failed to validate the Client Name for Quick (Order) Search in JobTracking !!!!");
			}
	 
	 if (doesElementExist(properties.getProperty("VerifyAppname"), 5)) {	    
		String appvldn = driver.findElement(By.xpath(properties.getProperty("VerifyAppname"))).getText();
		if(appvldn.contains(JobAppname)){
			log.logLine(Testname, false, "**** Application Name "+appvldn+" for Quick (Order) Search Matches with the "+JobAppname+" selected for JobTracking Module****");
		} else {
			log.logLine(Testname, true, "!!!! Application Name "+appvldn+" doesnot Matches with the "+JobAppname+" !!!!");
			}
	 } else {
			log.logLine(Testname, true, "Failed to validate the Application Name for Quick (Order) Search in JobTracking");
			}
			 
	 Thread.sleep(8000);
	 driver.switchTo().frame("iframeContainer");
	 
	 if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
    	WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
    	Thread.sleep(2000);
	   	PleasewaitDisappear();
	   	log.logLine(Testname, false, "Clicking on Search button");
	} else {
	    log.logLine(Testname, true, "Clicking on Search is failed");
	    throw new Exception("Clicking on Search is failed");
	}
	 
	 if (doesElementExist2(properties.getProperty("SrchType"), 5)) {	   
    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("SrchType")));
    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
    	Thread.sleep(1000);
		log.logLine(Testname, false, "Clicking on SearchType dropdown..");
		
	 if (doesElementExist2(properties.getProperty("TypeAdvance"), 5)) {
		List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("TypeAdvance")));
		for (WebElement lnk:selopts) {
			if (lnk.getText().equals("Advanced")) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
				Thread.sleep(2000);
				log.logLine(Testname, false, "**** Selecting the Search Type as Advanced from the dropdown ****");							
				break;
				}				
			}
			
		} else {
			log.logLine(Testname, true, "!!!! Search Type options are not displayed !!!!");
			throw new Exception("!!!! Search Type options are not displayed !!!!");
		}
	}
	 
	 Thread.sleep(2000);
	 if (doesElementExist2(properties.getProperty("Cancelbtn"), 5)) {
    	WebElement cnclbtn = driver.findElement(By.cssSelector(properties.getProperty("Cancelbtn")));
    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnclbtn);
    	Thread.sleep(1000);
	   	PleasewaitDisappear();
	   	log.logLine(Testname, false, "Clicking on Cancel button");
	} else {
	    log.logLine(Testname, true, "Clicking on Cancel is failed");
	    throw new Exception("Clicking on Cancel is failed");
	}
	 
	 Thread.sleep(2000);
	 driver.switchTo().defaultContent();
	 
	 Thread.sleep(5000);
	 if (doesElementExist(properties.getProperty("VerifyClientname"), 5)) {	    
		String clntvldn = driver.findElement(By.xpath(properties.getProperty("VerifyClientname"))).getText();
		if(clntvldn.contains(JobClientname)){
			log.logLine(Testname, false, "**** Client Name "+clntvldn+" for Advance Search Matches with the "+JobClientname+" selected for JobTracking Module ****");
		} else {
			log.logLine(Testname, true, "!!!! Client Name "+clntvldn+" doesnot Matches with the "+JobClientname+" !!!!");
			}
	 } else {
			log.logLine(Testname, true, "!!!! Failed to validate the Client Name for Advance Search in JobTracking !!!!");
			}
	 
	 if (doesElementExist(properties.getProperty("VerifyAppname"), 5)) {	    
		String appvldn = driver.findElement(By.xpath(properties.getProperty("VerifyAppname"))).getText();
		if(appvldn.contains(JobAppname)){
			log.logLine(Testname, false, "**** Application Name "+appvldn+" for Advance Search Matches with the "+JobAppname+" selected for JobTracking Module****");
		} else {
			log.logLine(Testname, true, "!!!! Application Name "+appvldn+" doesnot Matches with the "+JobAppname+" !!!!");
			}
	 } else {
			log.logLine(Testname, true, "!!!! Failed to validate the Application Name for Advance Search in JobTracking !!!!");
			}
			 
	 
	return true;
	
	}
}

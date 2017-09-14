package pivotModules;
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
import org.openqa.selenium.support.ui.Select;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;


public class ViewReport extends Page{
	
	public ViewReport(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}	
	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}
	
	public boolean IEcheck=false;	

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
	
	
	public boolean quickAdvancedSearch(String Testname) throws Exception {
		
		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	    
	    
	    Thread.sleep(2000);
	    driver.switchTo().frame("iframeContainer");    	    
		 
	    if (doesElementExist2(properties.getProperty("QuickSrch"), 5)) {	
	    	log.logLine(Testname, false, "Clicking on quick search button ");
	    	
			WebElement quksrch = driver.findElement(By.cssSelector(properties.getProperty("QuickSrch")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", quksrch);
			List<WebElement> dayseven = driver.findElements(By.cssSelector(properties.getProperty("days7")));	
			for (WebElement lnk:dayseven) {
				if (lnk.getText().equals("Last 7 Days")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					PleasewaitDisappear();
					log.logLine(Testname, false, "Clicking on last 7 days option to view the reports in the report grid");
					break;
				}
			}
			if (doesElementExist2(properties.getProperty("ReportTable"), 5)) {
		    	log.logLine(Testname, false, "Quick search - Successfully displayed the reports for last 7 days");		    	
		    } else {
		    	log.logLine(Testname, false, "Quick search - No reports found for the last 7 days");
		    }
			//log.logLine(Testname, false, "Report is successfully hidden");
		 } else {
			log.logLine(Testname, true, "Clicking on quick search button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on quick search button is failed");
		}	
	   
	    driver.switchTo().defaultContent();
	   if (doesElementExist2(properties.getProperty("RepViewerLnk"), 5)) {	    
			WebElement rptviewlnk = driver.findElement(By.cssSelector(properties.getProperty("RepViewerLnk")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rptviewlnk);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Report viewer button..");
	    } else {
			log.logLine(Testname, true, "Clicking on Report viewer button is failed");
			throw new Exception("Clicking on Report viewer button is failed");
		}
	    
	    
		driver.switchTo().frame("iframeContainer");  
	    Thread.sleep(5000);
	    if (doesElementExist2(properties.getProperty("QuickSrch"), 5)) {	
	    	log.logLine(Testname, false, "Clicking on quick search button ");
	    	
			WebElement quksrch = driver.findElement(By.cssSelector(properties.getProperty("QuickSrch")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", quksrch);
			List<WebElement> daythree = driver.findElements(By.cssSelector(properties.getProperty("days3")));	
			for (WebElement lnk:daythree) {
				if (lnk.getText().equals("Last 3 Days")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					PleasewaitDisappear();
					log.logLine(Testname, false, "Clicking on last 3 days option to view the reports in the report grid");
					break;
				}
			}
			if (doesElementExist2(properties.getProperty("ReportTable"), 5)) {
		    	log.logLine(Testname, false, "Quick search - Successfully displayed the reports for last 3 days");		    	
		    } else {
		    	log.logLine(Testname, false, "Quick search - No reports found for the last 3 days");
		    }
			//log.logLine(Testname, false, "Report is successfully hidden");
	    } else {
			log.logLine(Testname, true, "Clicking on quick search button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on quick search button is failed");
		}
	   
	    // Get the reports based on the Job code
	    String JobCodefld = test.readColumnData("JobCode_Adsrch", sheetname);
	    AdvancedSearch(Testname, "JobCode", JobCodefld, "");			
	    
	    // Fill up advanced search field and clear, click cancel button
	    fillupAdvancedSrch(Testname, "Yes", "");
	    
	    // Get the reports based on the Location selected from the dropdown
	    String Locationfld = test.readColumnData("Location_Adsrch", sheetname);
	    AdvancedSearch(Testname, "Location", Locationfld, "");
	    
	    // Fill up advanced search field and clear, click cancel button
	    fillupAdvancedSrch(Testname, "Yes", "");
	    
	    // Get the reports based on the ReportName selected from the dropdown
	    String ReportNamefld = test.readColumnData("ReportName_Adsrch", sheetname);
	    AdvancedSearch(Testname, "ReportName", ReportNamefld, "");
	    
	    // Fill up advanced search field and clear, click cancel button
	    fillupAdvancedSrch(Testname, "Yes", "");
	    	
	    
	    // Display the reports between from and to dates	    
	    String Fromdateval = test.readColumnData("FromDate", sheetname);	    
	    SimpleDateFormat gmtDateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		gmtDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
	    String Todateval = gmtDateFormat.format(new Date());
	    
	    AdvancedSearch(Testname, "FROMTOdate", Fromdateval, Todateval);    
	    
	    	    
	    return true;
	}		
	
	
	public void AdvancedSearch(String Testname, String SrchType, String testdata1, String testdata2) throws Exception {
		
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
			
			switch (SrchType) {
			
	            case "FROMTOdate":
	            	if (doesElementExist2(properties.getProperty("FromDate"), 5)) {	  
	        		    WebElement DteFromfld = driver.findElement(By.cssSelector(properties.getProperty("FromDate")));
	        			DteFromfld.clear();
	        			DteFromfld.sendKeys(testdata1);
	        			log.logLine(Testname, false, "Entering the from date value in advanced search");
	        			
	        			WebElement DteTofld = driver.findElement(By.cssSelector(properties.getProperty("ToDate")));
	        			DteTofld.clear();
	        			DteTofld.sendKeys(testdata2);
	        			log.logLine(Testname, false, "Entering the To date value in advanced search");
	        	    }	            	
	                break;
	                
	            case "Location":	            
	            	
	            	if (doesElementExist2(properties.getProperty("LocationAdsrch"), 5)) {	
	    		    	
	    				WebElement quksrch = driver.findElement(By.cssSelector(properties.getProperty("LocationAdsrch")));
	    				((JavascriptExecutor) driver).executeScript("arguments[0].click()", quksrch);
	    				log.logLine(Testname, false, "Clicking on the Location dropdown in advanced search dialog");
	    				
	    				List<WebElement> selloca = driver.findElements(By.cssSelector(properties.getProperty("locationoption")));	
	    				for (WebElement lnk:selloca) {
	    					if (lnk.getText().equals("Logan")) {
	    						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
	    						PleasewaitDisappear();
	    						log.logLine(Testname, false, "Selecting the Logan option from the location dropdown list in advanced search dialog");
	    						break;
	    					}
	    				}
	    			}
	    				else {
	    				log.logLine(Testname, true, "Clicking on location dropdown list in advanced search dialog is failed");
	    				driver.switchTo().defaultContent();
	    				throw new Exception("Clicking on location dropdown list in advanced search dialog is failed");
	    			}
	                break;
	                
	            case "JobCode":
	            	if (doesElementExist2(properties.getProperty("JobCodeAdsrch"), 5)) {
	    				WebElement jobcod = driver.findElement(By.cssSelector(properties.getProperty("JobCodeAdsrch")));
	    				jobcod.clear();
	    				jobcod.sendKeys(testdata1);
	    				
	    				log.logLine(Testname, false, "Entering the "+testdata1 +" into job code value in advanced search dialog");
	    			} else {
	    				log.logLine(Testname, true, "Job Code field in Advanced search dialog does not exist");
	    				throw new Exception("Job Code field in Advanced search dialog does not exist");
	    			}
	                break;
	                
	         
	            case "ReportName":
	            	if (doesElementExist2(properties.getProperty("ReportNameAdsrch"), 5)) {	
	    		    	
	    				WebElement quksrch = driver.findElement(By.cssSelector(properties.getProperty("ReportNameAdsrch")));
	    				((JavascriptExecutor) driver).executeScript("arguments[0].click()", quksrch);
	    				log.logLine(Testname, false, "Clicking on the report name  dropdown list in advanced search dialog");
	    				
	    				List<WebElement> selloca = driver.findElements(By.cssSelector(properties.getProperty("reportoption")));	
	    				for (WebElement lnk:selloca) {
	    					if (lnk.getText().contains(testdata1)) {
	    						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
	    						PleasewaitDisappear();
	    						log.logLine(Testname, false, "Selecting the tEST3 option from the report dropdown list in advanced search dialog");
	    						break;
	    					}
	    				}
	    			}
	    				else {
	    				log.logLine(Testname, true, "Clicking on report dropdown list in advanced search dialog is failed");
	    				driver.switchTo().defaultContent();
	    				throw new Exception("Clicking on location report list in advanced search dialog is failed");
	    			}
	                break;
	            
	        }			
			
			if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	    
				WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
				Thread.sleep(2000);
				PleasewaitDisappear();
				log.logLine(Testname, false, "Clicking on Search button to view the Reports based on "+SrchType);
		    } else {
				log.logLine(Testname, true, "Clicking on Search button to view the Reports based on "+SrchType +" is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on Search button to view the Reports based on "+SrchType +" is failed");
			}
			
			if (doesElementExist2(properties.getProperty("ReportTable"), 5)) {
		    	log.logLine(Testname, false, "Advanced search - Successfully displayed the reports for "+testdata1 +" " +testdata2 +" entered in "+SrchType+" field");		    		    	
		    } else {
		    	log.logLine(Testname, false, "Advanced search - No reports found for "+testdata1  +" " +testdata2 +" entered in "+SrchType+" field");
		    }			
			
			boolean Chkdownld = false;
			Actions action = new Actions(driver);
			//Download the report file from reports grid
			if (SrchType.equals("FROMTOdate")) {	
				
				if (doesElementExist2(properties.getProperty("ViewFirstRpt"), 5)) {
		    		WebElement viewrptbtn = driver.findElement(By.cssSelector(properties.getProperty("ViewFirstRpt")));		    		
		    		action.click(viewrptbtn).perform(); //Click
		    		Thread.sleep(9000);
		    		
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
		    		
		    		log.logLine(Testname, false, "Click on Date link to view the report in the grid is successful");
		    	}
				
				
				Thread.sleep(1000);
			    if (doesElementExist2(properties.getProperty("ChoseAct2"), 5)) {	
			    	log.logLine(Testname, false, "Advanced search based on From and To dates successful");
			    	
					WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("ChoseAct2")));
					log.logLine(Testname, false, "Clicking on Choose Action to view the report..");
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
					List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SelReportLnk")));	
					for (WebElement lnk:vwrpts) {
						if (lnk.getText().equals("View Report")) {
							action.click(lnk).perform();
				    		Thread.sleep(10000);
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
							    	
							    	/*if ((Initialization.EnvirSite.equals("TEST")) || (Initialization.EnvirSite.equals("Test")) || (Initialization.EnvirSite.equals("test"))) {
								    	if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) 
								    			|| (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) 
							    			driver.get("javascript:document.getElementById('overridelink').click();");
							    	}*/
							    	
							    	Thread.sleep(3000);
							    	driver.close();
							    	
							    	// Switching back to parent window
								    driver.switchTo().window(firstWinHandle);
								    
								    Thread.sleep(2000);
								    driver.switchTo().frame("iframeContainer");
							    }
						    }
						    
							log.logLine(Testname, false, "Clicking on ViewReport link under choose action in the report grid");
							Chkdownld = true;
							driver.switchTo().defaultContent();
							break;
						}
					}
					log.logLine(Testname, false, "Clicking on ChooseActions in the report grid");
			    } else {
					log.logLine(Testname, true, "Clicking on ChooseActions in the report grid is failed");
					driver.switchTo().defaultContent();
					throw new Exception("Clicking on ChooseActions in the report grid is failed");
				}	
			    
			    //Closing the Adobe processes running
			    Runtime.getRuntime().exec("taskkill /F /IM AcroRd32.exe");
			    
			    if (Chkdownld) {
				    Thread.sleep(500);
				    //ViewDownloadReport(Testname);
				    
			    }			    
			    
			}		
		}		
		
	}
	
	
	public boolean AdvsrchNegativecase(String Testname) throws Exception {
				
		Thread.sleep(2000);
	    driver.switchTo().frame("iframeContainer"); 
	    
	    // Get the reports based on the Job code with no matching data entered	    
	    AdvancedSearch(Testname, "JobCode", "NOMATCHING DATA", "");
	    
	    
	    // Fill up advanced search field and clear, click cancel button
	    fillupAdvancedSrch(Testname, "Yes", "");  
	    
	    fillupAdvancedSrch(Testname, "", "");	    
	    
		if (!(doesElementExist2(properties.getProperty("ReportTable"), 5))) {
			log.logLine(Testname, false, "Advanced search - Hidden reports were not found for specified date");		    	
	    } else {
	    	log.logLine(Testname, false, "Advanced search - Successfully displayed the hidden reports for specified date");		    	
	    	
	    	if (doesElementExist2(properties.getProperty("ChoseAct2"), 5)) {	
		    	log.logLine(Testname, false, "Advanced search based on From and To dates successful");
		    	
				WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("ChoseAct2")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
				Thread.sleep(1000);
				List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SelReportLnk")));				
				for (WebElement lnk:vwrpts) {
					if (lnk.getText().equals("Hide Report")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						log.logLine(Testname, false, "Clicked on HideReport link under choose action in the report grid");
						PleasewaitDisappear();
						
						//Verify access permission
						if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSUPER")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSITE")) 
								|| (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDCLIENT")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDUSER"))) {
							
							log.logLine(Testname, false, "Permission Verified: RRD Super, RRD Site, RRD Client & RRD User have access to Hide the report in RV");
							
						} else if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTADMIN")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTUSER"))) {
							
							log.logLine(Testname, true, "Client Admin / Client User should not have access to Hide report in RV");
						}						
						
						break;
					}
				}
				log.logLine(Testname, false, "Report is successfully hidden");				
				
		    } else {
				log.logLine(Testname, true, "Clicking on ChooseActions in the report grid is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on ChooseActions in the report grid is failed");
			}
	    	
	    }
		
	    // View reports using advanced search and bulk hide
	    fillupAdvancedSrch(Testname, "", "");
	    
	    if (!(doesElementExist2(properties.getProperty("ReportTable"), 5))) {
			log.logLine(Testname, false, "Advanced search - reports were not found for specified date");		    	
	    } else {
	    	log.logLine(Testname, false, "Advanced search - Successfully displayed the reports for specified date");		    	
	    	
	    	if (doesElementExist2(properties.getProperty("lable1chkbox"), 5)) {	
				WebElement lab1 = driver.findElement(By.cssSelector(properties.getProperty("lable1chkbox")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", lab1);
				log.logLine(Testname, false, "First row data is checked for bulk hiding");
				
				if (doesElementExist2(properties.getProperty("label2chkbox"), 5)) {	
					WebElement lab2 = driver.findElement(By.cssSelector(properties.getProperty("label2chkbox")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lab2);
					log.logLine(Testname, false, "Second row data is checked for bulk hiding");
				}
				
				if (doesElementExist2(properties.getProperty("label3chkbox"), 5)) {	
					WebElement lab3 = driver.findElement(By.cssSelector(properties.getProperty("label3chkbox")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lab3);
					log.logLine(Testname, false, "Third row data is checked for bulk hiding");
				}
				
				if (doesElementExist2(properties.getProperty("BulkHidebtn"), 5)) {	
					WebElement btnbulkhide = driver.findElement(By.cssSelector(properties.getProperty("BulkHidebtn")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnbulkhide);
					PleasewaitDisappear();
					log.logLine(Testname, false, "Multiple reports are successfully hidden");				
					
					//Verify access permission
					if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSUPER")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSITE")) 
							|| (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDCLIENT")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDUSER"))) {
						
						log.logLine(Testname, false, "Permission Verified: RRD Super, RRD Site, RRD Client & RRD User have access to Bulk-Hide the report in RV");
						
					} else if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTADMIN")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTUSER"))) {
						
						log.logLine(Testname, true, "Client Admin / Client User should not have access to bulk-Hide report in RV");
					}
				}				
				
		    } else {
				log.logLine(Testname, true, "Clicking on ChooseActions in the report grid is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on ChooseActions in the report grid is failed");
			}
	    	
	    }
	    
	    // Get the hidden reports and unhide them
	    fillupAdvancedSrch(Testname, "", "Yes");
	    if (!(doesElementExist2(properties.getProperty("ReportTable"), 5))) {
			log.logLine(Testname, false, "Advanced search - hidden reports were not found for specified date");		    	
	    } else {
	    	log.logLine(Testname, false, "Advanced search - Successfully displayed the hidden reports for specified date");	
	    	
	    	String css2="";
	    	for (int k=1; k<21; k++) {
	    		
	    		String css1 ="div[id='report-viewer-grid'] table tbody ";
	    		if (k == 1) 
	    			css2 = "tr";
	    		else
	    			css2 =css2 +"+tr";
	    		
	    		String css3 =" td+td+td+td+td+td+td div span span span[class='k-input']";	    		
	    		String Csspath = css1+css2+css3;
	    		boolean found = false;
		    	if (doesElementExist2(Csspath, 5)) {				    				    	
					WebElement choseacts = driver.findElement(By.cssSelector(Csspath));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
					Thread.sleep(1000);
					
					if (doesElementExist2(properties.getProperty("SelReportLnk"), 5)) {
					
						List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SelReportLnk")));					
						for (WebElement lnk:vwrpts) {
							if (lnk.getText().equals("Unhide Report")) {
					
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							PleasewaitDisappear();
							log.logLine(Testname, false, "Clicking on Unhide report link under choose action in the report grid");
							found = true;
							
							//Verify access permission
							if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSUPER")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSITE")) 
									|| (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDCLIENT")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDUSER"))) {
								
								log.logLine(Testname, false, "Permission Verified: RRD Super, RRD Site, RRD Client & RRD User have access to Unhide the report in RV");
								
							} else if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTADMIN")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTUSER"))) {
								
								log.logLine(Testname, true, "Client Admin & Client User should not have access to Unhide report in RV");
							}			
							
							break;
						}
					}
					}
					log.logLine(Testname, false, "Reports is successfully unhidden");
					if (found) {						
						break;
					} else {
						if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSUPER")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSITE")) 
								|| (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDCLIENT")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDUSER"))) {
							
							log.logLine(Testname, true, "Permission Denied: RRD Super, RRD Site, RRD Client & RRD User does not have access to Unhide the report in RV");
							
						} else if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTADMIN")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTUSER"))) {
							
							log.logLine(Testname, false, "Permission Verified: Client Admin & Client User does not have access to Unhide report in RV");
						}
						
					}
					
			    } else {
					log.logLine(Testname, true, "Clicking on ChooseActions in the report grid is failed when unhiding report");
					driver.switchTo().defaultContent();
					throw new Exception("Clicking on ChooseActions in the report grid is failed when unhiding report");
				}
		    	
	    	}
	    	
	    }	    
	    
	    Thread.sleep(1000);
	    driver.switchTo().defaultContent();  	
		
		return true;		
	}
	
	
	public void fillupAdvancedSrch(String Testname, String ClrCancel, String Hide) throws Exception {
		
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
				
			String Fromdateval = test.readColumnData("FromDate", sheetname);	    
			
			SimpleDateFormat gmtDateFormat = new SimpleDateFormat("MM/dd/yyyy");		
			gmtDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
		    String Todateval = gmtDateFormat.format(new Date());	
		    
		    
			if (doesElementExist2(properties.getProperty("FromDate"), 5)) {	  
    		    WebElement DteFromfld = driver.findElement(By.cssSelector(properties.getProperty("FromDate")));
    			DteFromfld.clear();
    			DteFromfld.sendKeys(Fromdateval);
    			log.logLine(Testname, false, "Entering the from date value in advanced search");
    			
    			WebElement DteTofld = driver.findElement(By.cssSelector(properties.getProperty("ToDate")));
    			DteTofld.clear();
    			DteTofld.sendKeys(Todateval);
    			log.logLine(Testname, false, "Entering the To date value in advanced search");
    	    }
			
			String Locationfld = test.readColumnData("Location_Adsrch", sheetname);
			if (doesElementExist2(properties.getProperty("LocationAdsrch"), 5)) {	
		    	
				WebElement quksrch = driver.findElement(By.cssSelector(properties.getProperty("LocationAdsrch")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", quksrch);
				log.logLine(Testname, false, "clicking on the Location dropdown list in advanced search dialog");
				
				List<WebElement> selloca = driver.findElements(By.cssSelector(properties.getProperty("locationoption")));	
				for (WebElement lnk:selloca) {
					if (lnk.getText().equals(Locationfld)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						PleasewaitDisappear();
						log.logLine(Testname, false, "Selecting the Las Vegas option from the location dropdown list ");
						break;
					}
				}
			}
				else {
				log.logLine(Testname, true, "Clicking on location dropdown list  in advanced search dialog is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on location dropdown list  in advanced search dialog is failed");
			}
				
			String JobCodefld = test.readColumnData("JobCode_Adsrch", sheetname);
			if (doesElementExist2(properties.getProperty("JobCodeAdsrch"), 5)) {
				WebElement jobcod = driver.findElement(By.cssSelector(properties.getProperty("JobCodeAdsrch")));				
				jobcod.clear();
				jobcod.sendKeys(JobCodefld);
				
				log.logLine(Testname, false, "Entering the "+JobCodefld +" into job code value in advanced search dialog");
			} else {
				log.logLine(Testname, true, "Job Code field in Advanced search dialog does not exist");
				driver.switchTo().defaultContent();
				throw new Exception("Job Code field in Advanced search dialog does not exist");
			}
			
			//Click on Clear button in advanced search
			if (ClrCancel.equalsIgnoreCase("Yes")) {
				if (doesElementExist2(properties.getProperty("ClearbtnAdsrch"), 5)) {
					WebElement Clrbtn = driver.findElement(By.cssSelector(properties.getProperty("ClearbtnAdsrch")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Clrbtn);
					
					log.logLine(Testname, false, "Clicking on clear button in advanced search dialog");
				} else {
					log.logLine(Testname, true, "Clear button in advanced dialog does not exist");
					driver.switchTo().defaultContent();
					throw new Exception("Clear button in advanced dialog does not exist");
				}
				
				//Click on Cancel button in advanced search
				if (doesElementExist2(properties.getProperty("CancelbtnAdsrch"), 5)) {
					WebElement Cancelbtn = driver.findElement(By.cssSelector(properties.getProperty("CancelbtnAdsrch")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Cancelbtn);
					
					log.logLine(Testname, false, "Clicking on Cancel button in advanced search dialog");
				} else {
					log.logLine(Testname, true, "Cancel button in advanced dialog does not exist");
					driver.switchTo().defaultContent();
					throw new Exception("Cancel button in advanced dialog does not exist");
				}	
				return;
			}  
	    
	    
		    // View hidden reports using advanced search
			if (Hide.equalsIgnoreCase("Yes")) {			
				
				if (doesElementExist2(properties.getProperty("InclHiddenrepchkbox"), 5)) {
					WebElement inclhiddenrepts = driver.findElement(By.cssSelector(properties.getProperty("InclHiddenrepchkbox")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", inclhiddenrepts);

					
					log.logLine(Testname, false, "Checking the include hidden reports in advanced search dialog");
				} else {
					log.logLine(Testname, true, "Checking the include hidden reports in advanced search dialog is failed");
					driver.switchTo().defaultContent();
					throw new Exception("Checking the include hidden reports in advanced search dialog is failed");
				}	
			}
			
			if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	    
				WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
				PleasewaitDisappear();
				log.logLine(Testname, false, "Clicking on Search button to view the Reports");
		    } else {
				log.logLine(Testname, true, "Clicking on Search button to view the Reports is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on Search button to view the Reports is failed");
			}	
		}
		
		
	}
	

}

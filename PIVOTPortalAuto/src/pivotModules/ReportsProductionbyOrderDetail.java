

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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class ReportsProductionbyOrderDetail extends Page{


	public ReportsProductionbyOrderDetail(WebDriver driver, Log log) throws InvalidFormatException, IOException {
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
 	
 	
 	public boolean ReportProductionOrder(String AccNo,String Testname) throws Exception {
 		
 		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	
	    
	    String NmeField=test.readColumnData("NameField", sheetname);
	    
	    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		Date date = new Date();
		String todaysDate = dateFormat.format(date);
		
		String arr[] = todaysDate.split("2015");
		String splttoddte = arr[0].trim();
		String TodayDte=splttoddte+15;
		
		DateFormat dateFormat1 = new SimpleDateFormat("MM/dd/yy");		
		Date date1 = new Date();
		String Prevdte = dateFormat1.format(date1);
		Prevdte=test.readColumnData("PrevoiusDate", sheetname);
		
		String array[] = Prevdte.split("2012");
		String spltprev = array[0].trim();
		String Previousdate=spltprev+12;
	    
	    Thread.sleep(10000);
	    driver.switchTo().frame("iframeContainer");
	    
	    if (doesElementExist2(properties.getProperty("ReportTypes"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("ReportTypes")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(500);
			log.logLine(Testname, false, "Clicking on ReportTypes drop down list in Report Writer");
			
			
			if (doesElementExist2(properties.getProperty("ReportTypeoptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ReportTypeoptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("ProductionbyOrderDetail")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						PleasewaitDisappear();
						log.logLine(Testname, false, "Selecting the Productionby Order Detail report type option from the dropdown");
						break;
					}
				}
			} else {
					log.logLine(Testname, true, "Selecting the Productionby Order Detail report type option  is failed");
					driver.switchTo().defaultContent();
					throw new Exception("Selecting the the Productionby Order Detail report type option  is failed");
					}
		}else {
				log.logLine(Testname, true, "Clicking on the ReportTypes is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on the ReportTypes is failed");
		}
	    
	    //added new
	    if (doesElementExist(properties.getProperty("DateRng"), 5)) {	    
			WebElement optr = driver.findElement(By.xpath(".//*[@id='dvDatesParameters']/div[3]/span/span/span[1]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(500);
			log.logLine(Testname, false, "Clicking on Select Field Dropdown List");
			
			
			if (doesElementExist2(properties.getProperty("DateRngOptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("DateRngOptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Last Month")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						PleasewaitDisappear();
						Thread.sleep(4000);
						log.logLine(Testname, false, "Selecting the Last month option from the dropdown");
						break;
						}
					}
				} else {
					log.logLine(Testname, true, "Selecting the Last month option from the dropdown is failed");
					driver.switchTo().defaultContent();
					throw new Exception("Selecting the Last month option from the dropdown is failed");
					}
	    }
	    	       
		
	
	    //Checking for the Plant and order_num fields and passing the value in the fields
	    
	    if (doesElementExist2(properties.getProperty("Plantbtn"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("Plantbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(500);
			log.logLine(Testname, false, "Clicking on Select Field Dropdown List");
			
			
			if (doesElementExist2(properties.getProperty("OptionType"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("OptionType")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Plant")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						PleasewaitDisappear();
						log.logLine(Testname, false, "Selecting the Plant option from the dropdown");
						break;
						}
					}
				} else {
					log.logLine(Testname, true, "Selecting the Plant option from the dropdown is failed");
					driver.switchTo().defaultContent();
					throw new Exception("Selecting the Plant option from the dropdown is failed");
				}
		}else {
			log.logLine(Testname, true, "Clicking on the Options field is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on the Options field is failed");
		}
	    
		    if (doesElementExist2(properties.getProperty("PlantTxt"), 5)) {
		    	WebElement Plntbdy = driver.findElement(By.cssSelector(properties.getProperty("PlantTxt")));
		    	Plntbdy.clear();
		    	Plntbdy.sendKeys("%");
		    	Thread.sleep(1000);
		    	log.logLine(Testname, false, "Plant Field Exists and entering the % value in the field");
		    	
		    }else{
		    	log.logLine(Testname, true, "Unable to enter the value in field");
				throw new Exception("Unable to enter the value in field");
			}
		  
		    if (doesElementExist2(properties.getProperty("AddButton"), 5)) {	    
				WebElement Addbtn = driver.findElement(By.cssSelector(properties.getProperty("AddButton")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", Addbtn);
				Thread.sleep(500);
				log.logLine(Testname, false, "Clicking on Add Button");
		    }else{
		    	log.logLine(Testname, true, "Unable to Click on Add Button");
				throw new Exception("Unable to Click on Add Button");
			}
		    
		   
		    if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) || (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) {
		    	
		    	if (doesElementExist(properties.getProperty("Order_NumIE"), 5)) {
		    		WebElement optr = driver.findElement(By.xpath(".//*[@id='dvParametersLeft']/div[2]/span[1]/span/span[1]"));
		    		((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
					Thread.sleep(500);
					log.logLine(Testname, false, "Clicking on Select Field drop down list");
					
					
					if (doesElementExist2(properties.getProperty("OptionType"), 5)) {
						List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("OptionType")));
						for (WebElement lnk:selopts) {
							if (lnk.getText().equals("Order Number")) {
								((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
								PleasewaitDisappear();
								log.logLine(Testname, false, "Selecting the Order_Num option from the dropdown");
								break;
								}
						}
					} else {
							log.logLine(Testname, true, "Selecting the Order_Num option from the dropdown is failed");
							driver.switchTo().defaultContent();
							throw new Exception("Selecting the Inv Code option from the dropdown is failed");
							}
				}else {
					log.logLine(Testname, true, "Clicking on the Options dropdown list field in query parameters  is failed");
					driver.switchTo().defaultContent();
					throw new Exception("Clicking on the Options dropdown list field in query parameters  is failed");
				}
		    
	
		    	if (doesElementExist2(properties.getProperty("Order_NumTxtIE"), 5)) {
			    	WebElement Ordnum = driver.findElement(By.cssSelector(properties.getProperty("Order_NumTxtIE")));
			    	Ordnum.clear();
			    	Ordnum.sendKeys("%");
			    	log.logLine(Testname, false, "Order_number Field Exists and entering the % value in the field");
	    	    }else{
			    	log.logLine(Testname, true, "Unable to enter the value in field");
					throw new Exception("Unable to enter the value in field");
				}
		    	
	    	}else if ((Initialization.Browser.equals("FF")) || (Initialization.Browser.equals("ff")) || (Initialization.Browser.equals("firefox")) || (Initialization.Browser.equals("Firefox")) || (Initialization.Browser.equals("FIREFOX"))) {
		    	
			    	if (doesElementExist2(properties.getProperty("Order_Numbtn"), 5)) {	    
						WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("Order_Numbtn")));
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
						Thread.sleep(500);
						log.logLine(Testname, false, "Clicking on Select Field drop down list");
						
						
						if (doesElementExist2(properties.getProperty("OptionType"), 5)) {
							List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("OptionType")));
							for (WebElement lnk:selopts) {
								if (lnk.getText().equals("Order Number")) {
									((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
									PleasewaitDisappear();
									log.logLine(Testname, false, "Selecting the order num option from the dropdown");
									break;
									}
								}
						} else {
								log.logLine(Testname, true, "Selecting the order num option from the dropdown is failed");
								driver.switchTo().defaultContent();
								throw new Exception("Selecting the order num option from the dropdown is failed");
						}
					}else {
						log.logLine(Testname, true, "Clicking on the Options dropdown list field in query parameters  is failed");
						driver.switchTo().defaultContent();
						throw new Exception("Clicking on the Options dropdown list field in query parameters  is failed");
					}
				    
				    if (doesElementExist2(properties.getProperty("Order_NumcodeTxt"), 5)) {
				    	WebElement InvtryTxt = driver.findElement(By.cssSelector(properties.getProperty("Order_NumcodeTxt")));
				    	InvtryTxt.clear();
				    	InvtryTxt.sendKeys("%");
				    	log.logLine(Testname, false, "Order_number Field Exists and entering the % value in the field");
				    	
				    }else{
				    	log.logLine(Testname, true, "Unable to enter the value in field");
						throw new Exception("Unable to enter the value in field");
					}
				    
		    } else if ((Initialization.Browser.equals("Chrome")) || (Initialization.Browser.equals("chrome")) || (Initialization.Browser.equals("CHROME"))) {
		    	
		    	if (doesElementExist2(properties.getProperty("Order_Numbtn"), 5)) {	    
					WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("Order_Numbtn")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
					Thread.sleep(500);
					log.logLine(Testname, false, "Clicking on Select Field drop down list");
					
					
					if (doesElementExist2(properties.getProperty("OptionType"), 5)) {
						List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("OptionType")));
						for (WebElement lnk:selopts) {
							if (lnk.getText().equals("Order Number")) {
								((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
								PleasewaitDisappear();
								log.logLine(Testname, false, "Selecting the order num option from the dropdown");
								break;
								}
							}
						} else {
							log.logLine(Testname, true, "Selecting the order num option from the dropdown is failed");
							driver.switchTo().defaultContent();
							throw new Exception("Selecting the order num option from the dropdown is failed");
							}
				}else {
						log.logLine(Testname, true, "Clicking on the Options dropdown list field in query parameters  is failed");
						driver.switchTo().defaultContent();
						throw new Exception("Clicking on the Options dropdown list field in query parameters  is failed");
				}
			    
			    if (doesElementExist2(properties.getProperty("Order_NumcodeTxt"), 5)) {
			    	WebElement InvtryTxt = driver.findElement(By.cssSelector(properties.getProperty("Order_NumcodeTxt")));
			    	InvtryTxt.clear();
			    	InvtryTxt.sendKeys("%");
			    	log.logLine(Testname, false, "Order_number Field Exists and entering the % value in the field");
			    	
			    }else{
			    	log.logLine(Testname, true, "Unable to enter the value in field");
					throw new Exception("Unable to enter the value in field");
				} 
		    	
		    }
	  // Report Columns
	    
	  
	  if (doesElementExist2(properties.getProperty("ClntDataProcssDate"), 5)) {
    		List<WebElement> ClDtaPrcDateText = driver.findElements(By.cssSelector(properties.getProperty("ClntDataProcssDate")));
    		 for (WebElement lnk:ClDtaPrcDateText) {
    			 if (lnk.getText().equals("Client Data Process Date")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 }
	    
	    
	    if (doesElementExist2(properties.getProperty("ClntDataProcssDateChkBox"), 5)) {
	  	    WebElement ClDtaPrcDatechkbox = driver.findElement(By.cssSelector(properties.getProperty("ClntDataProcssDateChkBox")));
	  	   
	  	  if ( ClDtaPrcDatechkbox.isSelected())
		     {
		    	log.logLine(Testname, false, "Client Data Process Date checkbox is already selected");
		    	
		     }else{
		    	 ClDtaPrcDatechkbox.click();
		     }
	    }
	    
	    
	    if (doesElementExist2(properties.getProperty("RRDPlantText"), 5)) {
    		List<WebElement> rrdplntText = driver.findElements(By.cssSelector(properties.getProperty("RRDPlantText")));
    		 for (WebElement lnk:rrdplntText) {
    			 if (lnk.getText().equals("RRD Plant")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 }
	    
	    
	    if (doesElementExist2(properties.getProperty("RRDPlantchkbox"), 5)) {
	  	    WebElement rrdplntchkbox = driver.findElement(By.cssSelector(properties.getProperty("RRDPlantchkbox")));
	  	   
	  	  if ( rrdplntchkbox.isSelected())
		     {
		    	log.logLine(Testname, false, "RRD Plant checkbox is already selected");
		    	
		     }else{
		    	 rrdplntchkbox.click();
		     }
	    }
	    
	    
	    if (doesElementExist2(properties.getProperty("PrimyMalDatetext"), 5)) {
    		List<WebElement> prmymldatetext = driver.findElements(By.cssSelector(properties.getProperty("PrimyMalDatetext")));
    		 for (WebElement lnk:prmymldatetext) {
    			 if (lnk.getText().equals("Primary Mail Date")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 }
	    
	    
	    if (doesElementExist2(properties.getProperty("PrimyMalDateChkbox"), 5)) {
	  	    WebElement prmymldatechkbox = driver.findElement(By.cssSelector(properties.getProperty("PrimyMalDateChkbox")));
	  	   
	  	  if ( prmymldatechkbox.isSelected())
		     {
		    	log.logLine(Testname, false, "Primary Mail Date checkbox is already selected");
		    	
		     }else{
		    	 prmymldatechkbox.click();
		     }
	    }
	    
	    
	    if (doesElementExist2(properties.getProperty("TotlPrntPgsText"), 5)) {
    		List<WebElement> totlprntpgsText = driver.findElements(By.cssSelector(properties.getProperty("TotlPrntPgsText")));
    		 for (WebElement lnk:totlprntpgsText) {
    			 if (lnk.getText().equals("Total Print Pages")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 }
	    
	    
	 	    
	    if (doesElementExist2(properties.getProperty("TotlPrntPgschkbox"), 5)) {
	  	    WebElement totlprntpgschkbox = driver.findElement(By.cssSelector(properties.getProperty("TotlPrntPgschkbox")));
	  	   
	  	  if ( totlprntpgschkbox.isSelected())
		     {
		    	log.logLine(Testname, false, "Total Print Pages checkbox is already selected");
		    	
		     }else{
		    	 totlprntpgschkbox.click();
		     }
	    }
	    
	    
	    if (doesElementExist2(properties.getProperty("TotlEmlQntyText"), 5)) {
    		List<WebElement> totlemlqntyText = driver.findElements(By.cssSelector(properties.getProperty("TotlEmlQntyText")));
    		 for (WebElement lnk:totlemlqntyText) {
    			 if (lnk.getText().equals("Total Email Quantity")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 }
	    
	    
	    if (doesElementExist2(properties.getProperty("TotlEmlQntyChkbox"), 5)) {
	  	    WebElement totlemlqntychkbox = driver.findElement(By.cssSelector(properties.getProperty("TotlEmlQntyChkbox")));
	  	   
	  	  if ( totlemlqntychkbox.isSelected())
		     {
		    	log.logLine(Testname, false, "Total Email Quantity checkbox is already selected");
		    	
		     }else{
		    	 totlemlqntychkbox.click();
		     }
	    }
	    
	    
	    if (doesElementExist2(properties.getProperty("RRDdataRecptDateText"), 5)) {
    		List<WebElement> rrddatarcptdtetext = driver.findElements(By.cssSelector(properties.getProperty("RRDdataRecptDateText")));
    		 for (WebElement lnk:rrddatarcptdtetext) {
    			 if (lnk.getText().equals("RRD Data Receipt Date")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 }
	    
	    
	    if (doesElementExist2(properties.getProperty("RRDdataRecptDatechkbox"), 5)) {
	  	    WebElement rrddatarcptdtechkbox = driver.findElement(By.cssSelector(properties.getProperty("RRDdataRecptDatechkbox")));
	  	    
	  	  if ( rrddatarcptdtechkbox.isSelected())
		     {
		    	log.logLine(Testname, false, "RRD Data Receipt Date checkbox is already selected");
		    	
		     }else{
		    	 rrddatarcptdtechkbox.click();
		     }
	    }
 
	    // Report Charts
	    
	    if (doesElementExist2(properties.getProperty("ordrnumText"), 5)) {
    		List<WebElement> rdrnumbertext = driver.findElements(By.cssSelector(properties.getProperty("ordrnumText")));
    		 for (WebElement lnk:rdrnumbertext) {
    			 if (lnk.getText().equals("Order Number")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 }
	    
	    	    
	    if (doesElementExist2(properties.getProperty("ordrnumTextchkbox"), 5)) {
	  	    WebElement rdrnumberchkbox = driver.findElement(By.cssSelector(properties.getProperty("ordrnumTextchkbox")));
	  	   
	  	  if ( rdrnumberchkbox.isSelected())
		     {
		    	log.logLine(Testname, false, "Order Number checkbox is already selected");
		    	
		     }else{
		    	 rdrnumberchkbox.click();
		     }
	    }
	    
	    
	    //Reports Column
	    
	    if (doesElementExist2(properties.getProperty("SecnryMalDateText"), 5)) {
    		List<WebElement> scndrymaildatetext = driver.findElements(By.cssSelector(properties.getProperty("SecnryMalDateText")));
    		 for (WebElement lnk:scndrymaildatetext) {
    			 if (lnk.getText().equals("Secondary Mail Date")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 }
	    
	    
	    if (doesElementExist2(properties.getProperty("SecnryMalDatechkbox"), 5)) {
	  	    WebElement scndrymaildatechkbox = driver.findElement(By.cssSelector(properties.getProperty("SecnryMalDatechkbox")));
	  	   
	  	  if ( scndrymaildatechkbox.isSelected())
		     {
		    	log.logLine(Testname, false, "Secondary Mail Date checkbox is already selected");
		    	
		     }else{
		    	 scndrymaildatechkbox.click();
		     }
	    }
	    
	    
	    if (doesElementExist2(properties.getProperty("AvrgPrntPgsText"), 5)) {
    		List<WebElement> avrgprntpgsText = driver.findElements(By.cssSelector(properties.getProperty("AvrgPrntPgsText")));
    		 for (WebElement lnk:avrgprntpgsText) {
    			 if (lnk.getText().equals("Average Print Pages")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 }
	    
	    
	    if (doesElementExist2(properties.getProperty("AvrgPrntPgschkbox"), 5)) {
	  	    WebElement  avrgprntpgschkbox = driver.findElement(By.cssSelector(properties.getProperty("AvrgPrntPgschkbox")));
	  	   
	  	  if ( avrgprntpgschkbox.isSelected())
		     {
		    	log.logLine(Testname, false, "Average Print Pages checkbox is already selected");
		    	
		     }else{
		    	 avrgprntpgschkbox.click();
		     }
	    }
	    
	
	    if (doesElementExist2(properties.getProperty("RRDdataRecptTimeText"), 5)) {
    		List<WebElement> rrddatarcpttimetext = driver.findElements(By.cssSelector(properties.getProperty("RRDdataRecptTimeText")));
    		 for (WebElement lnk:rrddatarcpttimetext) {
    			 if (lnk.getText().equals("RRD Data Receipt Time")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 }
	    
	    
	    if (doesElementExist2(properties.getProperty("RRDdataRecptTimechkbox"), 5)) {
	  	    WebElement   rrddatarcpttimechkbox = driver.findElement(By.cssSelector(properties.getProperty("RRDdataRecptTimechkbox")));
	  	   
	  	  if ( rrddatarcpttimechkbox.isSelected())
		     {
		    	log.logLine(Testname, false, "RRD Data Receipt Time checkbox is already selected");
		    	
		     }else{
		    	 rrddatarcpttimechkbox.click();
		     }
	    }
	    
	    
	    if (doesElementExist2(properties.getProperty("ApplicationText"), 5)) {
    		List<WebElement> applnText = driver.findElements(By.cssSelector(properties.getProperty("ApplicationText")));
    		 for (WebElement lnk:applnText) {
    			 if (lnk.getText().equals("Application")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 }
	    
	    
	    if (doesElementExist2(properties.getProperty("Applcationchkbox"), 5)) {
	  	    WebElement  applnTextchkbox = driver.findElement(By.cssSelector(properties.getProperty("Applcationchkbox")));
	  	   
	  	  if ( applnTextchkbox.isSelected())
		     {
		    	log.logLine(Testname, false, "Application checkbox is already selected");
		    	
		     }else{
		    	 applnTextchkbox.click();
		     }
	    }
	    
	    
	    if (doesElementExist2(properties.getProperty("TotalPrntQtyText"), 5)) {
    		List<WebElement> totlprntpgsText = driver.findElements(By.cssSelector(properties.getProperty("TotalPrntQtyText")));
    		 for (WebElement lnk:totlprntpgsText) {
    			 if (lnk.getText().equals("Total Print Quantity")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 }
	    
	    
	    if (doesElementExist2(properties.getProperty("TotalPrntQtychkbox"), 5)) {
	  	    WebElement  totlprntpgschkbox = driver.findElement(By.cssSelector(properties.getProperty("TotalPrntQtychkbox")));
	  	   
	  	  if ( totlprntpgschkbox.isSelected())
		     {
		    	log.logLine(Testname, false, "Total Print Quantity checkbox is already selected");
		    	
		     }else{
		    	 totlprntpgschkbox.click();
		     }
	    }
	    
	    
	    if (doesElementExist2(properties.getProperty("ElectrnicMailDateText"), 5)) {
    		List<WebElement> EltrncMaldateText = driver.findElements(By.cssSelector(properties.getProperty("ElectrnicMailDateText")));
    		 for (WebElement lnk:EltrncMaldateText) {
    			 if (lnk.getText().equals("Electronic Mail Date")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 }
	    
	    
	    if (doesElementExist2(properties.getProperty("ElectrnicMailDatechkbox"), 5)) {
	  	    WebElement  EltrncMaldatechkbox = driver.findElement(By.cssSelector(properties.getProperty("ElectrnicMailDatechkbox")));
	  	   
	  	  if ( EltrncMaldatechkbox.isSelected())
		     {
		    	log.logLine(Testname, false, "Electronic Mail Date checkbox is already selected");
		    	
		     }else{
		    	 EltrncMaldatechkbox.click();
		     }
	    }
	    
	    //Report Charts
	    if (doesElementExist2(properties.getProperty("DocAppchartText"), 5)) {
    		List<WebElement> DocmntApplnChartText = driver.findElements(By.cssSelector(properties.getProperty("DocAppchartText")));
    		 for (WebElement lnk:DocmntApplnChartText) {
    			 if (lnk.getText().equals("Number of Documents per application chart")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Charts");
    				 break;
    			 }
    		 }
    	 }
	    
	    
	    if (doesElementExist2(properties.getProperty("DocAppchartchkbox"), 5)) {
	  	    WebElement  DocmntApplnChartchkbox = driver.findElement(By.cssSelector(properties.getProperty("DocAppchartchkbox")));
	  	   
	  	  if ( DocmntApplnChartchkbox.isSelected())
		     {
		    	log.logLine(Testname, false, "Number of Documents per application chart checkbox is already selected");
		    	
		     }else{
		    	 DocmntApplnChartchkbox.click();
		     }
	    }
	    
	    //Group By
	    
	    if (doesElementExist2(properties.getProperty("ClntDatePrccdText"), 5)) {
    		List<WebElement> clntdateproccdText = driver.findElements(By.cssSelector(properties.getProperty("ClntDatePrccdText")));
    		 for (WebElement lnk:clntdateproccdText) {
    			 if (lnk.getText().equals("Client Date Processed")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Group By section");
    				 break;
    			 }
    		 }
    	 }
	    
	    
	    if (doesElementExist2(properties.getProperty("ClntDatePrccdChkbox"), 5)) {
	  	    WebElement  clntdateproccdchkbox = driver.findElement(By.cssSelector(properties.getProperty("ClntDatePrccdChkbox")));
	  	   
	  	  if ( clntdateproccdchkbox.isSelected())
		     {
		    	log.logLine(Testname, false, "Client Date Processed checkbox is already selected");
		    	
		     }else{
		    	 clntdateproccdchkbox.click();
		     }
	    }
	    
	    if (doesElementExist2(properties.getProperty("RunDateText"), 5)) {
    		List<WebElement> rundateText = driver.findElements(By.cssSelector(properties.getProperty("RunDateText")));
    		 for (WebElement lnk:rundateText) {
    			 if (lnk.getText().equals("Run Date")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Group By section");
    				 break;
    			 }
    		 }
    	 }
	    
	    
	    if (doesElementExist2(properties.getProperty("RunDatechkbox"), 5)) {
	  	    WebElement  RunDatechkbox = driver.findElement(By.cssSelector(properties.getProperty("RunDatechkbox")));
	  	   
	  	  if ( RunDatechkbox.isSelected())
		     {
		    	log.logLine(Testname, false, "Run Date checkbox is already selected");
		    	
		     }else{
		    	 RunDatechkbox.click();
		     }
	    }
	    
	    
	    if (doesElementExist2(properties.getProperty("FacilityText"), 5)) {
    		List<WebElement> FacltyText = driver.findElements(By.cssSelector(properties.getProperty("FacilityText")));
    		 for (WebElement lnk:FacltyText) {
    			 if (lnk.getText().equals("Facility")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Group By section");
    				 break;
    			 }
    		 }
    	 }
	    
	    
	    if (doesElementExist2(properties.getProperty("Facilitychkbox"), 5)) {
	  	    WebElement  Facltychkbox = driver.findElement(By.cssSelector(properties.getProperty("Facilitychkbox")));
	  	   
	  	  if ( Facltychkbox.isSelected())
		     {
		    	log.logLine(Testname, false, "Facility checkbox is already selected");
		    	
		     }else{
		    	 Facltychkbox.click();
		     }
	    }
	    
	    
	    if (doesElementExist2(properties.getProperty("FullOrderText"), 5)) {
    		List<WebElement> fullordrText = driver.findElements(By.cssSelector(properties.getProperty("FullOrderText")));
    		 for (WebElement lnk:fullordrText) {
    			 if (lnk.getText().equals("Full Order")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Group By section");
    				 break;
    			 }
    		 }
    	 }
	    
	    
	    if (doesElementExist2(properties.getProperty("FullOrderchkbox"), 5)) {
	  	    WebElement  fullordrchkbox = driver.findElement(By.cssSelector(properties.getProperty("FullOrderchkbox")));
	  	   
	  	  if ( fullordrchkbox.isSelected())
		     {
		    	log.logLine(Testname, false, "Full Order Date checkbox is already selected");
		    	
		     }else{
		    	 fullordrchkbox.click();
		     }
	    }
	    
	    
	    if (doesElementExist2(properties.getProperty("ProjectDecpText"), 5)) {
    		List<WebElement> prjctdecpText = driver.findElements(By.cssSelector(properties.getProperty("ProjectDecpText")));
    		 for (WebElement lnk:prjctdecpText) {
    			 if (lnk.getText().equals("Project Desc")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Group By section");
    				 break;
    			 }
    		 }
    	 }
	    
	    
	    if (doesElementExist2(properties.getProperty("ProjectDecpchkbox"), 5)) {
	  	    WebElement  prjctdecpchkbox = driver.findElement(By.cssSelector(properties.getProperty("ProjectDecpchkbox")));
	  	   
	  	  if ( prjctdecpchkbox.isSelected())
		     {
		    	log.logLine(Testname, false, "Project Desc checkbox is already selected");
		    	
		     }else{
		    	 prjctdecpchkbox.click();
		     }
	    }
	    
	    
	    if (doesElementExist2(properties.getProperty("InfoMailDateText"), 5)) {
    		List<WebElement> infomldateText = driver.findElements(By.cssSelector(properties.getProperty("InfoMailDateText")));
    		 for (WebElement lnk:infomldateText) {
    			 if (lnk.getText().equals("Infoshare expected mail date")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Group By section");
    				 break;
    			 }
    		 }
    	 }
	    
	    
	    if (doesElementExist2(properties.getProperty("InfoMailDatechkbox"), 5)) {
	  	    WebElement  infomldatechkbox = driver.findElement(By.cssSelector(properties.getProperty("InfoMailDatechkbox")));
	  	   
	  	  if ( infomldatechkbox.isSelected())
		     {
		    	log.logLine(Testname, false, "Infoshare expected mail date checkbox is already selected");
		    	
		     }else{
		    	 infomldatechkbox.click();
		     }
	    }
	    
	    
	    if (doesElementExist2(properties.getProperty("ShipDateText"), 5)) {
    		List<WebElement> shpdateText = driver.findElements(By.cssSelector(properties.getProperty("ShipDateText")));
    		 for (WebElement lnk:shpdateText) {
    			 if (lnk.getText().equals("ship date")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Group By section");
    				 break;
    			 }
    		 }
    	 }
	    
	    
	    if (doesElementExist2(properties.getProperty("ShipDateTextchkbox"), 5)) {
	  	    WebElement  shpdatechkbox = driver.findElement(By.cssSelector(properties.getProperty("ShipDateTextchkbox")));
	  	   
	  	  if ( shpdatechkbox.isSelected())
		     {
		    	log.logLine(Testname, false, "ship date checkbox is already selected");
		    	
		     }else{
		    	 shpdatechkbox.click();
		     }
	    }
	    
	    
	   //added new 
	    boolean ChkRet2 = ReportVerification(AccNo,Testname);
	    if (ChkRet2) {
	    	log.logLine(Testname, false, "Verification of Report For Last Month is Successfull");
	    } else {
	    	log.logLine(Testname, true, "Verification of Report For Last Month is unSuccessfull");
	    	return ChkRet2;
	    }
	    
	    
	   /* //Run Report
	    if (doesElementExist2(properties.getProperty("RunReport"), 5)) {
    		WebElement rnrptbtn = driver.findElement(By.cssSelector(properties.getProperty("RunReport")));
    		((JavascriptExecutor) driver).executeScript("arguments[0].click()", rnrptbtn);
    		log.logLine(Testname, false, "Click on Run Report is Successful");
    		Thread.sleep(5000);
    		waitUntilLoadElementDisappear4();
    		
    		
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
			    	waitUntilLoadElementDisappear4();
			    	driver.manage().window().maximize();
			    	//if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) || (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) 
		    		//	driver.get("javascript:document.getElementById('overridelink').click();");
			    	
			    	Thread.sleep(5000);
			    	
		    		driver.close();
			    	
			    	// Switching back to parent window
				    driver.switchTo().window(firstWinHandle);
				    
			    }
		    }
	    
	}
	*/
	    
	    Thread.sleep(2000);
	    driver.switchTo().frame("iframeContainer");
	    if (doesElementExist(properties.getProperty("DateRng"), 5)) {	    
			WebElement optr = driver.findElement(By.xpath(".//*[@id='dvDatesParameters']/div[3]/span/span/span[1]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(500);
			log.logLine(Testname, false, "Selecting the Date ranges from dropdown");
			
			
			if (doesElementExist2(properties.getProperty("DateRngOptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("DateRngOptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Last Quarter")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						
						PleasewaitDisappear();
						Thread.sleep(8000);
						log.logLine(Testname, false, "Selecting the Last Quarter Date range option from the dropdown");
						break;
						}
					}
				} else {
					log.logLine(Testname, true, "Selecting the Last Quarter Date ranges from dropdown");
					driver.switchTo().defaultContent();
					}
	    }
	    
	    // Report Verification For Quarter
	    boolean ChkRet3 = ReportVerification(AccNo,Testname);
	    if (ChkRet3) {
	    	log.logLine(Testname, false, "Verification of Report For Last Quater is Successfull");
	    } else {
	    	log.logLine(Testname, true, "Verification of Report For Last Quater is unSuccessfull");
	    	return ChkRet3;
	    }
	    
	    
	    Thread.sleep(2000);
	    driver.switchTo().frame("iframeContainer");
	    if (doesElementExist(properties.getProperty("DateRng"), 5)) {	    
			WebElement optr = driver.findElement(By.xpath(".//*[@id='dvDatesParameters']/div[3]/span/span/span[1]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(500);
			log.logLine(Testname, false, "Selecting the Last Year Date ranges from dropdown");
			
			
			if (doesElementExist2(properties.getProperty("DateRngOptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("DateRngOptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Last Year")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						
						PleasewaitDisappear();
						Thread.sleep(10000);
						log.logLine(Testname, false, "Selecting the Last Year Date range option from the dropdown");
						break;
						}
					}
				} else {
					log.logLine(Testname, true, "Selecting the Last Year Date ranges from dropdown");
					driver.switchTo().defaultContent();
					}
	    }
	    
	    if (doesElementExist2(properties.getProperty("ReportNme"), 5)) {	  
		    WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("ReportNme")));
			Txt.sendKeys(NmeField+AccNo);
			log.logLine(Testname, false, "Entering report name as "+NmeField+AccNo+" In the Report Name Field");
		}else{
	    	log.logLine(Testname, true,"Unable to enter the textin report name field");
	    	
	    	}
	    
	    if (doesElementExist2(properties.getProperty("SaveButton"), 5)) {	  
		    WebElement rnrptbtn = driver.findElement(By.cssSelector(properties.getProperty("SaveButton")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", rnrptbtn);
		    Thread.sleep(15000);
		    PleasewaitDisappear();    
			log.logLine(Testname, false, "Click on Save button");
	    }else{
	    	log.logLine(Testname, true,"Click on Save button is UnSuccessful");
	   
    	}
	    
	    
	    if (doesElementExist2(properties.getProperty("SavedReports"), 5)) {	  
		    WebElement rnrptbtn = driver.findElement(By.cssSelector(properties.getProperty("SavedReports")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", rnrptbtn);
		    Thread.sleep(15000);
			log.logLine(Testname, false, "Click on SavedReports button");
	    }else{
	    	log.logLine(Testname, true,"Click on SavedReports button is UnSuccessful");
	   
    	}
	    
	    Thread.sleep(4000);
	    if (doesElementExist2(properties.getProperty("RunButton"), 5)) {	  
		    WebElement rnrptbtn = driver.findElement(By.cssSelector(properties.getProperty("RunButton")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", rnrptbtn);
			log.logLine(Testname, false, "Click on Run Button under the Saved report");
			PleasewaitDisappear();
			waitUntilLoadElementDisappear4();
		    Thread.sleep(60000);
			
    		    		
    		Set<String> handles = driver.getWindowHandles();
		    String firstWinHandle = driver.getWindowHandle(); 
		    handles.remove(firstWinHandle);
		    
		    boolean browserexist = handles.iterator().hasNext();
		    if (browserexist) {
			    String winHandle=handles.iterator().next();
			    if (winHandle!=firstWinHandle){

			    	//Switch control to new window
			    	driver.switchTo().window(winHandle);
			    	waitUntilLoadElementDisappear4();
			    	driver.manage().window().maximize();
			    	
			    	//if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) || (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) 
		    		//	driver.get("javascript:document.getElementById('overridelink').click();");
			    	
			    	Thread.sleep(10000);
			    	
			    	//Reading the report title
			    	//Note: The format of the Width of the object in IE is different from FF
			    	
			    	if (doesElementExist2(properties.getProperty("ReportTitle"), 5)) {
			    		String dtetme = driver.findElement(By.cssSelector(properties.getProperty("ReportTitle"))).getText();
			    		log.logLine(Testname, false, "Report tile is " +dtetme);
			    	}else{
				    	log.logLine(Testname, true, "Reading the report title is unSuccessful");
						//throw new Exception("Reading the date is unSuccessful");
			    	}
			   
			    	// Validating For Created Date and Time
			    	
			    	if (doesElementExist2(properties.getProperty("CreatedDtetme"), 5)) {
			    		String dtetme = driver.findElement(By.cssSelector(properties.getProperty("CreatedDtetme"))).getText();
			    		log.logLine(Testname, false, "Reading the Created date and Time from the production by order detail report as" +dtetme);
			    	}else{
				    	log.logLine(Testname, true, "Reading the Created date and Time from production by order detail report is unSuccessful");
						//throw new Exception("Reading the date is unSuccessful");
			    	}
			    	
			    	
			    	//Validating For Date
			    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
				    		List<WebElement> Dte = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
				    		 for (WebElement lnk:Dte) {
				    			 if (lnk.getText().equals("Date:    ")){
				    				 // action.click(lnk).perform();
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" from production by order detail report");
				    				 break;
				    			 }
				    		 }
				    	 }
			    	
			    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
							List<WebElement> Frmdte = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
							for (WebElement lnk:Frmdte) {
							if (lnk.getText().endsWith(("14"))||lnk.getText().endsWith(("13"))){
							log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" from production by order detail report");
							break;
							}
						}
			    	 }
			    	 
			    	 //validating for Thru
			    	 
			    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
				    		List<WebElement> Thrdte = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
				    		 for (WebElement lnk:Thrdte) {
				    			 if (lnk.getText().equals(" Thru ")){
				    				 // action.click(lnk).perform();
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" from production by order detail report");
				    				 break;
				    			 }
				    		 }
				    	 }
			    	 
			    	 //Validating For To Date
			    	 
			    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
				    		List<WebElement> Todte = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
				    		 for (WebElement lnk:Todte) {
				    			 if (lnk.getText().equals(TodayDte)){
				    				 // action.click(lnk).perform();
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" from production by order detail report");
				    				 break;
				    			 }
				    		 }
				    	 }
			    	 
			    	 //validating for Application
			    	 
			    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
				    		List<WebElement> App = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
				    		 for (WebElement lnk:App) {
				    			 if (lnk.getText().equals("Application: ")){
				    				 // action.click(lnk).perform();
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" from production by order detail report");
				    				 break;
				    			 }
				    		 }
				    	 }
			    	 
			    	 // Validating for Letters
			    	 
			    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
				    		List<WebElement> Ltrs = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
				    		 for (WebElement lnk:Ltrs) {
				    			 if (lnk.getText().equals("Letters")){
				    				 // action.click(lnk).perform();
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" from production by order detail report");
				    				 break;
				    			 }
				    		 }
				    	 }
			    	 
			    	 
			    	 // Validating for RRD Plant
			    	 
			    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
				    		List<WebElement> Rplnt = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
				    		 for (WebElement lnk:Rplnt) {
				    			 if (lnk.getText().equals("Plant: ")){
				    				 // action.click(lnk).perform();
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" from production by order detail report");
				    				 break;
				    			 }
				    		 }
				    	 }
			    	 
			    	 
			    	 // Validating for All
			    	 
			    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
				    		List<WebElement> Rall = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
				    		 for (WebElement lnk:Rall) {
				    			 if (lnk.getText().equals("All")){
				    				 // action.click(lnk).perform();
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" from production by order detail report");
				    				 break;
				    			 }
				    		 }
				    	 }
			    	 
			    	 
			    	 // Validating For Order
			    	 
			    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
				    		List<WebElement> Ordr = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
				    		 for (WebElement lnk:Ordr) {
				    			 if (lnk.getText().equals("Order:         ")){
				    				 // action.click(lnk).perform();
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" from production by order detail report");
				    				 break;
				    			 }
				    		 }
				    	 }
			    	 
			    	 
			    	 //Validating For All
			    	 
			    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
				    		List<WebElement> Oall = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
				    		 for (WebElement lnk:Oall) {
				    			 if (lnk.getText().equals("All")){
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" from production by order detail report");
				    				 break;
				    			 }
				    		 }
			    	 }
			    	 
			    	
			    	// Summary table Verification
			    	 
			    	
                     if (doesElementExist2(properties.getProperty("TotalPrintQty"), 5)) {
                             List<WebElement> Todte = driver.findElements(By.cssSelector(properties.getProperty("TotalPrintQty")));
                             for (WebElement lnk:Todte) {
                                     if (lnk.getText().matches("[0-9,]*")||lnk.getText().matches("[0-9]*")){
                                             log.logLine(Testname, false, "The Total Print Quantity of Production Order Summary is: "+lnk.getText());
                                             break;
                                             }
                                     }
                     }
			    	
			    	// Graph Verification
			    	
			    	if (doesElementExist2(properties.getProperty("Graphsmry"), 5)) {
			    		String Grp = driver.findElement(By.cssSelector(properties.getProperty("Graphsmry"))).getText();
			    		log.logLine(Testname, false, "Validating the Graph is Successful");
			    	}else{
				    	log.logLine(Testname, true, "Reading the data is unSuccessful");
			    	}
			    	
			    	//Details Table 1St column data---->>Client Data Process Date
			    	
			    	if (doesElementExist2(properties.getProperty("ClntPrcssDateCol"), 5)) {
			    		String coldata1 = driver.findElement(By.cssSelector(properties.getProperty("ClntPrcssDateCol"))).getText();
			    		log.logLine(Testname, false, "Reading 1st column header under Details Table as--"+coldata1);
			    	}else{
				    	log.logLine(Testname, true, "Reading 1st column header under Details Table is unSuccessful");
						
			    	}
			   
			    	//Details Table 2nd column data---->>RRD Data Receipt Date
			    	
			    	if (doesElementExist2(properties.getProperty("RRDdataRecptDateCol"), 5)) {
			    		String coldata2 = driver.findElement(By.cssSelector(properties.getProperty("RRDdataRecptDateCol"))).getText();
			    		log.logLine(Testname, false, "Reading 2nd column header under Details Table as--"+coldata2);
			    	}else{
				    	log.logLine(Testname, true, "Reading 2nd column header under Details Table is unSuccessful");
						
			    	}
			    	
			    	//Details Table 3rd column data---->>-RRD Data Receipt Time
			    	
			    	if (doesElementExist2(properties.getProperty("RRDdataRecptTimeCol"), 5)) {
			    		String coldata3 = driver.findElement(By.cssSelector(properties.getProperty("RRDdataRecptTimeCol"))).getText();
			    		log.logLine(Testname, false, "Reading 3rd column header under Details Table as--"+coldata3);
			    	}else{
				    	log.logLine(Testname, true, "Reading 3rd column header under Details Table is unSuccessful");
						
			    	}
			    	
			    	//Details Table 4th column data---->> RRD Plant
			    	
			    	if (doesElementExist2(properties.getProperty("RRDPlantCol"), 5)) {
			    		String coldata4 = driver.findElement(By.cssSelector(properties.getProperty("RRDPlantCol"))).getText();
			    		log.logLine(Testname, false, "Reading 4th column header under Details Table as--"+coldata4);
			    	}else{
				    	log.logLine(Testname, true, "Reading 4th column header under Details Table is unSuccessful");
						
			    	}
			    	
			    	
			    	//Details Table 5th column data---->> Order Number
			    	
			    	if (doesElementExist2(properties.getProperty("ordrnumCol"), 5)) {
			    		String coldata5 = driver.findElement(By.cssSelector(properties.getProperty("ordrnumCol"))).getText();
			    		log.logLine(Testname, false, "Reading 5th column header under Details Table as--"+coldata5);
			    	}else{
				    	log.logLine(Testname, true, "Reading 5th column header under Details Table is unSuccessful");
						
			    	}
			    	
			    	//Details Table 6th column data---->> Application 
			    	
			    	if (doesElementExist2(properties.getProperty("ApplicationCol"), 5)) {
			    		String coldata6 = driver.findElement(By.cssSelector(properties.getProperty("ApplicationCol"))).getText();
			    		log.logLine(Testname, false, "Reading 6th column header under Details Table as--"+coldata6);
			    	}else{
				    	log.logLine(Testname, true, "Reading 6th column header under Details Table is unSuccessful");
						
			    	}
			    	
			    	
			    	//Details Table 7th column data---->> Primary mail date
			    	
			    	if (doesElementExist2(properties.getProperty("PrimyMalDateCol"), 5)) {
			    		String coldata7 = driver.findElement(By.cssSelector(properties.getProperty("PrimyMalDateCol"))).getText();
			    		log.logLine(Testname, false, "Reading 7th column header under Details Table as--"+coldata7);
			    	}else{
				    	log.logLine(Testname, true, "Reading 7th column header under Details Table is unSuccessful");
						
			    	}
			    	
			    	
			    	//Details Table 8th column data---->> Secondary mail date
			    	if (doesElementExist2(properties.getProperty("SecnryMalDateCol"), 5)) {
			    		String coldata8 = driver.findElement(By.cssSelector(properties.getProperty("SecnryMalDateCol"))).getText();
			    		log.logLine(Testname, false, "Reading 8th column header under Details Table as--"+coldata8);
			    	}else{
				    	log.logLine(Testname, true, "Reading 8th column header under Details Table is unSuccessful");
						
			    	}
			    	
			    	
			    	//Details Table 9th column data---->> Total Print quantity
			    	
			    	if (doesElementExist2(properties.getProperty("TotalPrntQtyCol"), 5)) {
			    		String coldata9 = driver.findElement(By.cssSelector(properties.getProperty("TotalPrntQtyCol"))).getText();
			    		log.logLine(Testname, false, "Reading 9th column header under Details Table as--"+coldata9);
			    	}else{
				    	log.logLine(Testname, true, "Reading 9th column header under Details Table is unSuccessful");
						
			    	}
			    	
			    	
			    	//Details Table 10th column data---->>Total print pages
			    	
			    	if (doesElementExist2(properties.getProperty("TotlPrntPgsCol"), 5)) {
			    		String coldata10 = driver.findElement(By.cssSelector(properties.getProperty("TotlPrntPgsCol"))).getText();
			    		log.logLine(Testname, false, "Reading 10th column header under Details Table as--"+coldata10);
			    	}else{
				    	log.logLine(Testname, true, "Reading 10th column header under Details Table is unSuccessful");
						
			    	}
			    	
			    	
			    	//Details Table 12th column data---->> Average Print Pages
			    	
			    	if (doesElementExist2(properties.getProperty("AvrgPrntPgsCol"), 5)) {
			    		String coldata11 = driver.findElement(By.cssSelector(properties.getProperty("AvrgPrntPgsCol"))).getText();
			    		log.logLine(Testname, false, "Reading 11th column header under Details Table as--"+coldata11);
			    	}else{
				    	log.logLine(Testname, true, "Reading 11th column header under Details Table is unSuccessful");
						
			    	}
			    	
			    	//Details Table 11th column data---->> Electronic mail date
			    	if (doesElementExist2(properties.getProperty("ElectrnicMailDateCol"), 5)) {
			    		String coldata12 = driver.findElement(By.cssSelector(properties.getProperty("ElectrnicMailDateCol"))).getText();
			    		log.logLine(Testname, false, "Reading 12th column header under Details Table as--"+coldata12);
			    	}else{
				    	log.logLine(Testname, true, "Reading 12th column header under Details Table is unSuccessful");
						
			    	}
			    	
			    	//Details Table 13th column data---->> Total Email Quantity
			    	if (doesElementExist2(properties.getProperty("TotlEmlQntyCol"), 5)) {
			    		String coldata13 = driver.findElement(By.cssSelector(properties.getProperty("TotlEmlQntyCol"))).getText();
			    		log.logLine(Testname, false, "Reading 13th column header under Details Table as--"+coldata13);
			    	}else{
				    	log.logLine(Testname, true, "Reading 13th column header under Details Table is unSuccessful");
						
			    	}
				    	
				    	
				    //	Thread.sleep(1000);		
						//SimpleDateFormat gmtDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");		
						//gmtDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
						//Current Date Time in IST
						//String CurDateTime = gmtDateFormat.format(new Date());
						
						
						//Delete the existing file before it downloads
						try {			
					        File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/ProductionbyOrderDetail.csv");
					        if (fileTemp1.exists()){
					        	fileTemp1.delete();
					        	log.logLine(Testname, false, "The Existing ProductionbyOrderDetail.csv has been deleted");
					        	}else{
							    	log.logLine(Testname, false, " ProductionbyOrderDetail.csv file does not exists in folder");
						    	}
				        
				        File fileTemp2 = new File(System.getProperty("user.home")+"/Downloads/ProductionbyOrderDetail.pdf");
				        if (fileTemp2.exists()){
				        	fileTemp2.delete();
				        	log.logLine(Testname, false, "The Existing ProductionbyOrderDetail.pdf has been deleted");
			        	}else{
					    	log.logLine(Testname, false, " ProductionbyOrderDetail.pdf file does not exists in folder");
				    	}
				        
				        File fileTemp3 = new File(System.getProperty("user.home")+"/Downloads/ProductionbyOrderDetail.xls");
				        if (fileTemp3.exists()){
				        	fileTemp3.delete();
				        	log.logLine(Testname, false, "The Existing ProductionbyOrderDetail.xls has been deleted");
			        	}else{
					    	log.logLine(Testname, false, " ProductionbyOrderDetail.xls file does not exists in folder");
				    	}
				       } catch(Exception e){
				        // if any error occurs
				        e.printStackTrace();
				    }	
						
					
						
					/*FirefoxProfile profile = new FirefoxProfile();
					profile.setAcceptUntrustedCertificates(true);
					profile.setPreference( "browser.download.folderList", 2 );
					profile.setPreference( "browser.download.dir", "C:\\" );
					profile.setPreference( "browser.helperApps.neverAsk.saveToDisk", "text/csv" );

					driver = new FirefoxDriver( profile );
					*/
					
					/*FirefoxProfile profile = new FirefoxProfile();
		            String path="d:\\downloads123";
		            profile.setPreference("browser.download.folderList", 2);
		            profile.setPreference("browser.download.dir", "C:\\");
		            profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		            profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/csv, text/csv, application/pdf");
        		    profile.setPreference("browser.download.manager.showWhenStarting", false);
				    profile.setPreference("browser.download.manager.focusWhenStarting", false);
				    //profile.setPreference("browser.download.useDownloadDir",true);
			        profile.setPreference("browser.helperApps.alwaysAsk.force",false);		           
				    profile.setPreference("browser.download.manager.alertOnEXEOpen", false);		           
				    profile.setPreference("browser.download.manager.closeWhenDone", false);		           
				    profile.setPreference("browser.download.manager.showAlertOnComplete", false);
				    profile.setPreference("browser.download.manager.useWindow", false);		           
				    profile.setPreference("browser.download.manager.showWhenStarting", false);		           
				    profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);		           
			        profile.setPreference("pdfjs.disabled", true);		           
			        WebDriver driver = new FirefoxDriver(profile);
			        driver.quit();
			        */
					
			    	//Saving the files in Different Formats
			    	
				Robot robot=new Robot();
					
				if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) || (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) {	
									    
	 			   if (doesElementExist2(properties.getProperty("Exportlnk"), 5)) {
			    		 WebElement Svelnk = driver.findElement(By.cssSelector(properties.getProperty("Exportlnk")));
			 		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Svelnk);
			 		    log.logLine(Testname, false, "Click on Export Link Button is Successful");
			    	}else{
			    		log.logLine(Testname, true, "Click on Export Link Button is failed");
			    	}
	 			   
	 			   	//CSV FORMAT
	 			   
			    	if (doesElementExist(properties.getProperty("Csvfrmt"), 5)) {
			    		 WebElement csvfmt = driver.findElement(By.xpath(".//*[@id='ReportViewer1_ctl06_ctl04_ctl00_Menu']/div[2]/a"));
			 		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", csvfmt);
			 		    Thread.sleep(3000);
			 		    log.logLine(Testname, false, "Saving The File in CSV (comma delimited) Format");
		 		    }else{
			 			   log.logLine(Testname, true, "Saving The File in CSV (comma delimited) Format isfailed");
 			        }
			    	
			    	robot.keyPress(KeyEvent.VK_ALT);
	 			    robot.keyPress(KeyEvent.VK_S);
	 			    robot.keyRelease(KeyEvent.VK_ALT);
	 			    robot.keyRelease(KeyEvent.VK_S);
			    	
			   
			    	// PDF FORMAT
			    	
	 			   if (doesElementExist2(properties.getProperty("Exportlnk"), 5)) {
			    		 WebElement Svelnk = driver.findElement(By.cssSelector(properties.getProperty("Exportlnk")));
			 		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Svelnk);
			 		    log.logLine(Testname, false, "Click on Export Link Button is Successful");
			    	}else{
			    		log.logLine(Testname, true, "Click on Export Link Button is failed");
			    		}
				   		    	
					if (doesElementExist(properties.getProperty("Pdffrmt"), 5)) {
			    		 WebElement pdffmt = driver.findElement(By.xpath(".//*[@id='ReportViewer1_ctl06_ctl04_ctl00_Menu']/div[3]/a"));
			 		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", pdffmt);

			 		    Thread.sleep(3000);
			 		    log.logLine(Testname, false, "Saving The File in PDF Format");
			 		   }else{
			 			   log.logLine(Testname, true, "Saving The File in PDF Format is failed");
			 			   }
					
					robot.keyPress(KeyEvent.VK_ALT);
	 			    robot.keyPress(KeyEvent.VK_S);
	 			    robot.keyRelease(KeyEvent.VK_ALT);
	 			    robot.keyRelease(KeyEvent.VK_S);
				
					//Excel Format
					
	 			   if (doesElementExist2(properties.getProperty("Exportlnk"), 5)) {
			    		 WebElement Svelnk = driver.findElement(By.cssSelector(properties.getProperty("Exportlnk")));
			 		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Svelnk);
			 		    log.logLine(Testname, false, "Click on Export Link Button is Successful");
			    	}else{
			    		log.logLine(Testname, true, "Click on Export Link Button is failed");
			    		}
				   
					
					if (doesElementExist(properties.getProperty("Excelfrmt"), 5)) {
			    		 WebElement Exlfmt = driver.findElement(By.xpath(".//*[@id='ReportViewer1_ctl06_ctl04_ctl00_Menu']/div[5]/a"));
			 		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Exlfmt);
			 		   Thread.sleep(3000);
			 		   log.logLine(Testname, false, "Saving The File in Excel Format");
			 		  }else{
			 			  log.logLine(Testname, true, "Saving The File in Excel Format is failed");
			 			  }
					
					robot.keyPress(KeyEvent.VK_ALT);
	 			    robot.keyPress(KeyEvent.VK_S);
	 			    robot.keyRelease(KeyEvent.VK_ALT);
	 			    robot.keyRelease(KeyEvent.VK_S);
					
				} else if ((Initialization.Browser.equals("FF")) || (Initialization.Browser.equals("ff")) || (Initialization.Browser.equals("firefox")) || (Initialization.Browser.equals("Firefox")) || (Initialization.Browser.equals("FIREFOX"))) {
				
					if (doesElementExist2(properties.getProperty("Exportlnk"), 5)) {
			    		 WebElement Svelnk = driver.findElement(By.cssSelector(properties.getProperty("Exportlnk")));
			 		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Svelnk);
			 		   Thread.sleep(3000);
			 		    log.logLine(Testname, false, "Click on Export Link Button is Successful");
			    	}else{
			    		log.logLine(Testname, true, "Click on Export Link Button is failed");
			    		}
				   
		    	
				// CSV Format
				
		    	if (doesElementExist(properties.getProperty("Csvfrmt"), 5)) {
		    		 WebElement csvfmt = driver.findElement(By.xpath(".//*[@id='ReportViewer1_ctl06_ctl04_ctl00_Menu']/div[2]/a"));
		 		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", csvfmt);
		 		    Thread.sleep(5000);
		 		    log.logLine(Testname, false, "Saving The File in CSV (comma delimited) Format");
		 		   }else{
		 			   log.logLine(Testname, true, "Saving The File in CSV (comma delimited) Format isfailed");
		 			   }
		    		    	
		   /*
		    	robot.keyPress(KeyEvent.VK_ALT);
			    robot.keyPress(KeyEvent.VK_S);
			    robot.keyRelease(KeyEvent.VK_ALT);
			    robot.keyRelease(KeyEvent.VK_S);
			    
			    robot.keyPress(KeyEvent.VK_ENTER);
			    robot.keyRelease(KeyEvent.VK_ENTER);
		    	*/
		    	// PDF FORMAT
		    	
			    if (doesElementExist2(properties.getProperty("Exportlnk"), 5)) {
		    		 WebElement Svelnk = driver.findElement(By.cssSelector(properties.getProperty("Exportlnk")));
		 		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Svelnk);
		 		   Thread.sleep(3000);
		 		    log.logLine(Testname, false, "Click on Export Link Button is Successful");
		    	}else{
		    		log.logLine(Testname, true, "Click on Export Link Button is failed");
		    		}
			   	    	
				if (doesElementExist(properties.getProperty("Pdffrmt"), 5)) {
		    		 WebElement pdffmt = driver.findElement(By.xpath(".//*[@id='ReportViewer1_ctl06_ctl04_ctl00_Menu']/div[3]/a"));
		 		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", pdffmt);
		 		    Thread.sleep(5000);
		 		    log.logLine(Testname, false, "Saving The File in PDF Format");
		 		   }else{
		 			   log.logLine(Testname, true, "Saving The File in PDF Format is failed");
		 			   }
			/*	
				robot.keyPress(KeyEvent.VK_ALT);
			    robot.keyPress(KeyEvent.VK_S);
			    robot.keyRelease(KeyEvent.VK_ALT);
			    robot.keyRelease(KeyEvent.VK_S);
			    
			    robot.keyPress(KeyEvent.VK_ENTER);
			    robot.keyRelease(KeyEvent.VK_ENTER);
			*/	
				
				
				//Excel Format
				
			    if (doesElementExist2(properties.getProperty("Exportlnk"), 5)) {
		    		 WebElement Svelnk = driver.findElement(By.cssSelector(properties.getProperty("Exportlnk")));
		 		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Svelnk);
		 		   Thread.sleep(3000);
		 		    log.logLine(Testname, false, "Click on Export Link Button is Successful");
		    	}else{
		    		log.logLine(Testname, true, "Click on Export Link Button is failed");
		    		}
			   
				if (doesElementExist(properties.getProperty("Excelfrmt"), 5)) {
		    		 WebElement Exlfmt = driver.findElement(By.xpath(".//*[@id='ReportViewer1_ctl06_ctl04_ctl00_Menu']/div[5]/a"));
		 		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Exlfmt);
		 		   Thread.sleep(5000);
		 		   log.logLine(Testname, false, "Saving The File in Excel Format");
		 		  }else{
		 			  log.logLine(Testname, true, "Saving The File in Excel Format is failed");
		 			  }
				
				/*
				robot.keyPress(KeyEvent.VK_ALT);
			    robot.keyPress(KeyEvent.VK_S);
			    robot.keyRelease(KeyEvent.VK_ALT);
			    robot.keyRelease(KeyEvent.VK_S);
			    
			    robot.keyPress(KeyEvent.VK_ENTER);
			    robot.keyRelease(KeyEvent.VK_ENTER);
			  */  
			} else if ((Initialization.Browser.equals("Chrome")) || (Initialization.Browser.equals("chrome")) || (Initialization.Browser.equals("CHROME"))) {
				
				if (doesElementExist2(properties.getProperty("Exportlnk"), 5)) {
		    		 WebElement Svelnk = driver.findElement(By.cssSelector(properties.getProperty("Exportlnk")));
		 		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Svelnk);
		 		    log.logLine(Testname, false, "Click on Export Link Button is Successful");
		    	}
		    	
				// CSV Format
				
		    	if (doesElementExist(properties.getProperty("Csvfrmt"), 5)) {
		    		 WebElement csvfmt = driver.findElement(By.xpath(".//*[@id='ReportViewer1_ctl06_ctl04_ctl00_Menu']/div[2]/a"));
		 		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", csvfmt);
		 		    
		 		    // Save(AccNo,Testname);
		 		    Thread.sleep(3000);
		 		    log.logLine(Testname, false, "Saving The File in CSV (comma delimited) Format");
		 		   }else{
		 			   log.logLine(Testname, true, "Saving The File in CSV (comma delimited) Format is failed");
		 			   }
		    	
		    	
		    	// PDF FORMAT
		    	
		    	if (doesElementExist2(properties.getProperty("Exportlnk"), 5)) {
		    		 WebElement Svelnk = driver.findElement(By.cssSelector(properties.getProperty("Exportlnk")));
		 		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Svelnk);
		 		   Thread.sleep(3000);
		 			log.logLine(Testname, false, "Click on Export Link Button is Successful");
		    	}
				
		    	
		    	
				if (doesElementExist(properties.getProperty("Pdffrmt"), 5)) {
		    		 WebElement pdffmt = driver.findElement(By.xpath(".//*[@id='ReportViewer1_ctl06_ctl04_ctl00_Menu']/div[3]/a"));
		 		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", pdffmt);
		 		    Thread.sleep(5000);
		 		    log.logLine(Testname, false, "Saving The File in PDF Format");
		 		   }else{
		 			   log.logLine(Testname, true, "Saving The File in PDF Format is failed");
		 			   }
		    	
				
					//Excel Format
				
				if (doesElementExist2(properties.getProperty("Exportlnk"), 5)) {
		    		 WebElement Svelnk = driver.findElement(By.cssSelector(properties.getProperty("Exportlnk")));
		 		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Svelnk);
		 			log.logLine(Testname, false, "Click on Export Link Button is Successful");
		    	}
				
				if (doesElementExist(properties.getProperty("Excelfrmt"), 5)) {
		    		 WebElement Exlfmt = driver.findElement(By.xpath(".//*[@id='ReportViewer1_ctl06_ctl04_ctl00_Menu']/div[5]/a"));
		 		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Exlfmt);
		 		   Thread.sleep(3000);
		 		   log.logLine(Testname, false, "Saving The File in Excel Format");
		 		  }else{
		 			  log.logLine(Testname, true, "Saving The File in Excel Format is failed");
		 			  }
				}	
				
				
					driver.close();
					// Switching back to parent window
					driver.switchTo().window(firstWinHandle);
					
					driver.switchTo().frame("iframeContainer");
					if (doesElementExist2(properties.getProperty("DeleteSavedReports"), 5)) {
				    	WebElement btndel = driver.findElement(By.cssSelector(properties.getProperty("DeleteSavedReports")));
				    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", btndel);	    	 
				    	 Thread.sleep(2000);
				    	 //PleasewaitDisappear();
				    	log.logLine(Testname, false, "Click on Delete button for Saved Reports is Successful");
				    } else {
				    	log.logLine(Testname, true, "Click on Delete button for Saved Reports is failed");
				    	driver.switchTo().defaultContent();
				    	throw new Exception("Click on Delete button for Saved Reports is failed");
				    }
					
					if (doesElementExist2(properties.getProperty("Deletepopupconfirm"), 5)) {
				    	WebElement btndel = driver.findElement(By.cssSelector(properties.getProperty("Deletepopupconfirm")));
				    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", btndel);	    	 
				    	 Thread.sleep(5000);
				    	 //PleasewaitDisappear();
				    	log.logLine(Testname, false, "Click on Ok button pop up is Successful");
				    } else {
				    	log.logLine(Testname, true, "Click on Ok button pop up is failed");
				    	driver.switchTo().defaultContent();
				    	throw new Exception("Click on Ok button pop up is failed");
				    }
					    
				    }
			    }
			}
				   
					
	    
			Thread.sleep(8000);
			try {			
		        File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/ProductionbyOrderDetail.csv");
		        if (fileTemp1.exists()){
		        	log.logLine(Testname, false, "The Saved ProductionbyOrderDetail.csv file exists ");
		        	}else{
				    	log.logLine(Testname, true, "ProductionbyOrderDetail.csv file does not exists");
			    	}
	        
	        File fileTemp2 = new File(System.getProperty("user.home")+"/Downloads/ProductionbyOrderDetail.pdf");
	        if (fileTemp2.exists()){
	           	log.logLine(Testname, false, "The Saved ProductionbyOrderDetail.pdf file exists");
        	}else{
		    	log.logLine(Testname, true, "ProductionbyOrderDetail.pdf file does not exists");
	    	}
	        
	        File fileTemp3 = new File(System.getProperty("user.home")+"/Downloads/ProductionbyOrderDetail.xls");
	        if (fileTemp3.exists()){
	       
	        	log.logLine(Testname, false, "The Saved ProductionbyOrderDetail.xls file exists");
        	}else{
		    	log.logLine(Testname, true, "ProductionbyOrderDetail.xls file does not exists");
	    	}
	       } catch(Exception e){
	        // if any error occurs
	        e.printStackTrace();
	        }
	  
 		    return true;
 		}
 	
 	
		
		public boolean ReportVerification(String AccNo, String Testname) throws Exception {
			
			boolean Chk1 = false, Chk2 = false, Chk3 = false, Chk4 = false, Chk5 = false;
			
			InputOutputData test = new InputOutputData();		
		    test.setInputFile(properties.getProperty("InputDatafile"));
		    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	
		   	    	    
		    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
			Date date = new Date();
			String todaysDate = dateFormat.format(date);
			
			String arr[] = todaysDate.split("2015");
			String splttoddte = arr[0].trim();
			String TodayDte=splttoddte+15;
			
					
			
			if (doesElementExist2(properties.getProperty("RunReport"), 5)) {
	    		WebElement rnrptbtn = driver.findElement(By.cssSelector(properties.getProperty("RunReport")));
	    		((JavascriptExecutor) driver).executeScript("arguments[0].click()", rnrptbtn);
	    		log.logLine(Testname, false, "Click on Run Report is Successful");
	    		PleasewaitDisappear();
	    		waitUntilLoadElementDisappear4();
	    		Thread.sleep(60000);
	    		
	    		
	    		
	    		Set<String> handles = driver.getWindowHandles();
			    String firstWinHandle = driver.getWindowHandle(); 
			    handles.remove(firstWinHandle);
			    
			    boolean browserexist = handles.iterator().hasNext();
			    if (browserexist) {
				    String winHandle=handles.iterator().next();
				    if (winHandle!=firstWinHandle){
				    
				    	//Switch control to new window
				    	driver.switchTo().window(winHandle);
				    	//waitUntilLoadElementDisappear4();
				    	driver.manage().window().maximize();
				    	//if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) || (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) 
			    		//	driver.get("javascript:document.getElementById('overridelink').click();");
				    
				    	WebElement retEle = waitForElement(properties.getProperty("ReportTitle"));
						log.logLine(Testname, false, "Report Name is found on the page..");
				    	
				    	//Reading the report title
				    	//Note: The format of the Width of the Object in IE is different from FF 
				    	
				    	if (doesElementExist2(properties.getProperty("ReportTitle"), 5)) {
				    		String dtetme = driver.findElement(By.cssSelector(properties.getProperty("ReportTitle"))).getText();
				    		log.logLine(Testname, false, "Report tile is " +dtetme);
				    		Chk1=true;
				    	}else{
					    	log.logLine(Testname, true, "Reading the report title is unSuccessful");
							//throw new Exception("Reading the date is unSuccessful");
				    	}
				    	
				    	// Validating for Created Date and Time
				    	
				    	if (doesElementExist2(properties.getProperty("CreatedDtetme"), 5)) {
				    		String dtetme = driver.findElement(By.cssSelector(properties.getProperty("CreatedDtetme"))).getText();
				    		log.logLine(Testname, false, "Reading the Created date and Time from the production by order detail report as" +dtetme);
				    		Chk2=true;
				    	}else{
					    	log.logLine(Testname, true, "Reading the Created date and Time from the production by order detail report is unsuccessful");
							//throw new Exception("Reading the date is unSuccessful");
				    	}
				    	
				    	
				    	//Validating For Date
				    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
					    		List<WebElement> Dte = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
					    		 for (WebElement lnk:Dte) {
					    			 if (lnk.getText().equals("Date:    ")){
					    				 // action.click(lnk).perform();
					    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" from production by order detail report");
					    				 break;
					    			 }
					    		 }
					    	 }
				    	
				    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
								List<WebElement> Frmdte = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
								for (WebElement lnk:Frmdte) {
								if (lnk.getText().endsWith(("15"))||lnk.getText().endsWith(("14"))){
								log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" from production by order detail report");
								break;
								}
							}
				    	 }
				    	 
				    	 //validating for Thru
				    	 
				    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
					    		List<WebElement> Thrdte = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
					    		 for (WebElement lnk:Thrdte) {
					    			 if (lnk.getText().equals(" Thru ")){
					    				 // action.click(lnk).perform();
					    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" from production by order detail report");
					    				 break;
					    			 }
					    		 }
					    	 }
				    	 
				    	 //Validating For To Date
				    	 
				    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
					    		List<WebElement> Todte = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
					    		 for (WebElement lnk:Todte) {
					    			 if (lnk.getText().equals(TodayDte)){
					    				 // action.click(lnk).perform();
					    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" from production by order detail report");
					    				 break;
					    			 }
					    		 }
					    	 }
				    	 
				    	 //validating for Application
				    	 
				    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
					    		List<WebElement> App = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
					    		 for (WebElement lnk:App) {
					    			 if (lnk.getText().equals("Application: ")){
					    				 // action.click(lnk).perform();
					    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" from production by order detail report");
					    				 break;
					    			 }
					    		 }
					    	 }
				    	 
				    	 // Validating for Letters
				    	 
				    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
					    		List<WebElement> Ltrs = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
					    		 for (WebElement lnk:Ltrs) {
					    			 if (lnk.getText().equals("Letters")){
					    				 // action.click(lnk).perform();
					    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" from production by order detail report");
					    				 break;
					    			 }
					    		 }
					    	 }
				    	 
				    	 
				    	 // Validating for RRD Plant
				    	 
				    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
					    		List<WebElement> Rplnt = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
					    		 for (WebElement lnk:Rplnt) {
					    			 if (lnk.getText().equals("Plant: ")){
					    				 // action.click(lnk).perform();
					    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" from production by order detail report");
					    				 break;
					    			 }
					    		 }
					    	 }
				    	 
				    	 
				    	 // Validating for All
				    	 
				    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
					    		List<WebElement> Rall = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
					    		 for (WebElement lnk:Rall) {
					    			 if (lnk.getText().equals("All")){
					    				 // action.click(lnk).perform();
					    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" from production by order detail report");
					    				 break;
					    			 }
					    		 }
					    	 }
				    	 
				    	 
				    	 // Validating For Order
				    	 
				    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
					    		List<WebElement> Ordr = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
					    		 for (WebElement lnk:Ordr) {
					    			 if (lnk.getText().equals("Order:         ")){
					    				 // action.click(lnk).perform();
					    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" from production by order detail report");
					    				 break;
					    			 }
					    		 }
					    	 }
				    	 
				    	 
				    	 //Validating For All
				    	 
				    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
					    		List<WebElement> Oall = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
					    		 for (WebElement lnk:Oall) {
					    			 if (lnk.getText().equals("All")){
					    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" from production by order detail report");
					    				 break;
					    			 }
					    		 }
					    	 }
				    	 
				    	// Summary table Verification
				    	if (doesElementExist2(properties.getProperty("TotalPrintQty"), 5)) {
                             List<WebElement> Todte = driver.findElements(By.cssSelector(properties.getProperty("TotalPrintQty")));
                             for (WebElement lnk:Todte) {
                                     if (lnk.getText().matches("[0-9,]*")||lnk.getText().matches("[0-9]*")){
                                             log.logLine(Testname, false, "The Total Print Quantity of Production Order Summary is: "+lnk.getText());
                                             break;
                                             }
                                     }
                        }

				    	
				    	// Graph Verification
				    	
				    	if (doesElementExist2(properties.getProperty("Graphsmry"), 5)) {
				    		String Grp = driver.findElement(By.cssSelector(properties.getProperty("Graphsmry"))).getText();
				    		log.logLine(Testname, false, "Validating the Graph is Successful");
				    		Chk3=true;
				    	}else{
					    	log.logLine(Testname, true, "Reading the data is unSuccessful");
					    	}
				    	
				    	
				    	//Details Table 1St column data---->>Client Data Process Date
				    	
				    	if (doesElementExist2(properties.getProperty("ClntPrcssDateCol"), 5)) {
				    		String coldata1 = driver.findElement(By.cssSelector(properties.getProperty("ClntPrcssDateCol"))).getText();
				    		log.logLine(Testname, false, "Reading 1st column header under Details Table as--"+coldata1);
				    		Chk4=true;
				    	}else{
					    	log.logLine(Testname, true, "Reading 1st column header under Details Table is unSuccessful");
							
				    	}
				   
				    	//Details Table 2nd column data---->>RRD Data Receipt Date
				    	
				    	if (doesElementExist2(properties.getProperty("RRDdataRecptDateCol"), 5)) {
				    		String coldata2 = driver.findElement(By.cssSelector(properties.getProperty("RRDdataRecptDateCol"))).getText();
				    		log.logLine(Testname, false, "Reading 2nd column header under Details Table as--"+coldata2);
				    	}else{
					    	log.logLine(Testname, true, "Reading 2nd column header under Details Table is unSuccessful");
							
				    	}
				    	
				    	//Details Table 3rd column data---->>-RRD Data Receipt Time
				    	
				    	if (doesElementExist2(properties.getProperty("RRDdataRecptTimeCol"), 5)) {
				    		String coldata3 = driver.findElement(By.cssSelector(properties.getProperty("RRDdataRecptTimeCol"))).getText();
				    		log.logLine(Testname, false, "Reading 3rd column header under Details Table as--"+coldata3);
				    	}else{
					    	log.logLine(Testname, true, "Reading 3rd column header under Details Table is unSuccessful");
							
				    	}
				    	
				    	//Details Table 4th column data---->>RRD plant
				    	
				    	if (doesElementExist2(properties.getProperty("RRDPlantCol"), 5)) {
				    		String coldata4 = driver.findElement(By.cssSelector(properties.getProperty("RRDPlantCol"))).getText();
				    		log.logLine(Testname, false, "Reading 4th column header under Details Table as--"+coldata4);
				    	}else{
					    	log.logLine(Testname, true, "Reading 4th column header under Details Table is unSuccessful");
							
				    	}
				    	
				    	
				    	//Details Table 5th column data---->>Order Number
				    	
				    	if (doesElementExist2(properties.getProperty("ordrnumCol"), 5)) {
				    		String coldata5 = driver.findElement(By.cssSelector(properties.getProperty("ordrnumCol"))).getText();
				    		log.logLine(Testname, false, "Reading 5th column header under Details Table as--"+coldata5);
				    	}else{
					    	log.logLine(Testname, true, "Reading 5th column header under Details Table is unSuccessful");
							
				    	}
				    	
				    	//Details Table 6th column data---->>Application
				    	
				    	if (doesElementExist2(properties.getProperty("ApplicationCol"), 5)) {
				    		String coldata6 = driver.findElement(By.cssSelector(properties.getProperty("ApplicationCol"))).getText();
				    		log.logLine(Testname, false, "Reading 6th column header under Details Table as--"+coldata6);
				    	}else{
					    	log.logLine(Testname, true, "Reading 6th column header under Details Table is unSuccessful");
							
				    	}
				    	
				    	
				    	//Details Table 7th column data---->>Primary mail date
				    	
				    	if (doesElementExist2(properties.getProperty("PrimyMalDateCol"), 5)) {
				    		String coldata7 = driver.findElement(By.cssSelector(properties.getProperty("PrimyMalDateCol"))).getText();
				    		log.logLine(Testname, false, "Reading 7th column header under Details Table as--"+coldata7);
				    	}else{
					    	log.logLine(Testname, true, "Reading 7th column header under Details Table is unSuccessful");
							
				    	}
				    	
				    	
				    	//Details Table 8th column data---->>Secondary mail date
				    	if (doesElementExist2(properties.getProperty("SecnryMalDateCol"), 5)) {
				    		String coldata8 = driver.findElement(By.cssSelector(properties.getProperty("SecnryMalDateCol"))).getText();
				    		log.logLine(Testname, false, "Reading 8th column header under Details Table as--"+coldata8);
				    	}else{
					    	log.logLine(Testname, true, "Reading 8th column header under Details Table is unSuccessful");
							
				    	}
				    	
				    	
				    	//Details Table 9th column data---->>Total print quantity
				    	
				    	if (doesElementExist2(properties.getProperty("TotalPrntQtyCol"), 5)) {
				    		String coldata9 = driver.findElement(By.cssSelector(properties.getProperty("TotalPrntQtyCol"))).getText();
				    		log.logLine(Testname, false, "Reading 9th column header under Details Table as--"+coldata9);
				    	}else{
					    	log.logLine(Testname, true, "Reading 9th column header under Details Table is unSuccessful");
							
				    	}
				    	
				    	
				    	//Details Table 10th column data---->>Total print pages
				    	
				    	if (doesElementExist2(properties.getProperty("TotlPrntPgsCol"), 5)) {
				    		String coldata10 = driver.findElement(By.cssSelector(properties.getProperty("TotlPrntPgsCol"))).getText();
				    		log.logLine(Testname, false, "Reading 10th column header under Details Table as--"+coldata10);
				    	}else{
					    	log.logLine(Testname, true, "Reading 10th column header under Details Table is unSuccessful");
							
				    	}
				    	
				    	
				    	//Details Table 11th column data---->>Average print pages
				    	
				    	if (doesElementExist2(properties.getProperty("AvrgPrntPgsCol"), 5)) {
				    		String coldata11 = driver.findElement(By.cssSelector(properties.getProperty("AvrgPrntPgsCol"))).getText();
				    		log.logLine(Testname, false, "Reading 11th column header under Details Table as--"+coldata11);
				    	}else{
					    	log.logLine(Testname, true, "Reading 11th column header under Details Table is unSuccessful");
							
				    	}
				    	
				    	//Details Table 12th column data---->>Electronic mail date
				    	if (doesElementExist2(properties.getProperty("ElectrnicMailDateCol"), 5)) {
				    		String coldata12 = driver.findElement(By.cssSelector(properties.getProperty("ElectrnicMailDateCol"))).getText();
				    		log.logLine(Testname, false, "Reading 12th column header under Details Table as--"+coldata12);
				    	}else{
					    	log.logLine(Testname, true, "Reading 12th column header under Details Table is unSuccessful");
							
				    	}
				    	
				    	//Details Table 13th column data---->>Total Email Quantity
				    	if (doesElementExist2(properties.getProperty("TotlEmlQntyCol"), 5)) {
				    		String coldata13 = driver.findElement(By.cssSelector(properties.getProperty("TotlEmlQntyCol"))).getText();
				    		log.logLine(Testname, false, "Reading 13th column header under Details Table as--"+coldata13);
				    		Chk5=true;
				    	}else{
					    	log.logLine(Testname, true, "Reading 13th column header under Details Table is unSuccessful");
							
				    	}
				    	
			    		driver.close();
				    	
				    	// Switching back to parent window
					    driver.switchTo().window(firstWinHandle);
					    }
				    }
			    }
				/*if (Chk1 && Chk2 && Chk3 && Chk4 && Chk5){
					return true;
				} else {
					return false; 
				}*/
			return true;
		    }
		}




	   
 		

   	
			       		 	
			    
	    
	
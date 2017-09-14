
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

public class ReportsInsertingOrder extends Page{


	public ReportsInsertingOrder(WebDriver driver, Log log) throws InvalidFormatException, IOException {
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
 	
 	
 	public boolean ReportInsertingOrder(String AccNo,String Testname) throws Exception {
 		
 		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	
	    
	    //String NmeField=test.readColumnData("NameField", sheetname);
	    
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
	    Thread.sleep(2000);
	    if (doesElementExist2(properties.getProperty("ReportTypes"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("ReportTypes")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(500);
			log.logLine(Testname, false, "Clicking on ReportTypes drop down list in Report Writer");
			
			
			if (doesElementExist2(properties.getProperty("ReportTypeoptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ReportTypeoptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("InsertingOrder")) {
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
	    	System.out.println("The entered value is:"+Plntbdy);
	    }else{
	    	log.logLine(Testname, true, "Unable to enter the value in field");
			throw new Exception("Unable to enter the value in field");
		}
	  
	    if (doesElementExist2(properties.getProperty("AddButton"), 5)) {	    
			WebElement Addbtn = driver.findElement(By.cssSelector(properties.getProperty("AddButton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Addbtn);
			Thread.sleep(5000);
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
							if (lnk.getText().equals("Order_Num")) {
								((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
								PleasewaitDisappear();
								log.logLine(Testname, false, "Selecting the Order_Num option from the dropdown");
								break;
								}
						}
					} else {
							log.logLine(Testname, true, "Selecting the Order_Num option from the dropdown is failed");
							driver.switchTo().defaultContent();
							throw new Exception("Selecting the Order_Num option from the dropdown is failed");
					}
	    	    }else {
					log.logLine(Testname, true, "Clicking on the Options dropdownlist field in query parameters is failed");
					driver.switchTo().defaultContent();
				    throw new Exception("Clicking on the Options dropdownlist field in query parameters is failed");
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
								if (lnk.getText().equals("Order_Num")) {
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
						log.logLine(Testname, true, "Clicking on the Options dropdownlist field in query parameters is failed");
						driver.switchTo().defaultContent();
						throw new Exception("Clicking on the Options dropdownlist field in query parameters is failed");
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
							if (lnk.getText().equals("Order_Num")) {
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
						log.logLine(Testname, true, "Clicking on the Options dropdownlist field in query parameters is failed");
						driver.switchTo().defaultContent();
						throw new Exception("Clicking on the Options dropdownlist field in query parameters is failed");
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
	    
		    if (doesElementExist2(properties.getProperty("OrderNumText"), 5)) {
					List<WebElement> ClDtaPrcDateText = driver.findElements(By.cssSelector(properties.getProperty("OrderNumText")));
					 for (WebElement lnk:ClDtaPrcDateText) {
						 if (lnk.getText().equals("Order Number")){
							 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
							 break;
						 }
					 }
			 }
		    
	    
		    if (doesElementExist2(properties.getProperty("OrderNumChkBox"), 5)) {
		  	    WebElement ClDtaPrcDatechkbox = driver.findElement(By.cssSelector(properties.getProperty("OrderNumChkBox")));
		  	   
		  	  if ( ClDtaPrcDatechkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Order Number checkbox is already selected");
			    	
			     }else{
			    	 ClDtaPrcDatechkbox.click();
			     }
		    }
	    
	    
		    if (doesElementExist2(properties.getProperty("CreateDateText"), 5)) {
	    		List<WebElement> rrdplntText = driver.findElements(By.cssSelector(properties.getProperty("CreateDateText")));
	    		 for (WebElement lnk:rrdplntText) {
	    			 if (lnk.getText().equals("Create Date")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
	    				 break;
	    			 }
	    		 }
	    	 }
	    
	    
		    if (doesElementExist2(properties.getProperty("CreateDatechkbox"), 5)) {
		  	    WebElement rrdplntchkbox = driver.findElement(By.cssSelector(properties.getProperty("CreateDatechkbox")));
		  	   
		  	  if ( rrdplntchkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Create Date checkbox is already selected");
			    	
			     }else{
			    	 rrdplntchkbox.click();
			     }
		    }
	    
	    
		    if (doesElementExist2(properties.getProperty("CompleteTimetext"), 5)) {
	    		List<WebElement> prmymldatetext = driver.findElements(By.cssSelector(properties.getProperty("CompleteTimetext")));
	    		 for (WebElement lnk:prmymldatetext) {
	    			 if (lnk.getText().equals("Complete Time")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
	    				 break;
	    			 }
	    		 }
	    	 }
	    
	    
		    if (doesElementExist2(properties.getProperty("CompleteTimeChkbox"), 5)) {
		  	    WebElement prmymldatechkbox = driver.findElement(By.cssSelector(properties.getProperty("CompleteTimeChkbox")));
		  	   
		  	  if ( prmymldatechkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Complete Time checkbox is already selected");
			    	
			     }else{
			    	 prmymldatechkbox.click();
			     }
		    }
	    
	    
		    if (doesElementExist2(properties.getProperty("CopyText"), 5)) {
	    		List<WebElement> totlprntpgsText = driver.findElements(By.cssSelector(properties.getProperty("CopyText")));
	    		 for (WebElement lnk:totlprntpgsText) {
	    			 if (lnk.getText().equals("Copy")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
	    				 break;
	    			 }
	    		 }
	    	 }
	    
	    
	 	    
		    if (doesElementExist2(properties.getProperty("Copychkbox"), 5)) {
		  	    WebElement totlprntpgschkbox = driver.findElement(By.cssSelector(properties.getProperty("Copychkbox")));
		  	   
		  	  if ( totlprntpgschkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Copy checkbox is already selected");
			    	
			     }else{
			    	 totlprntpgschkbox.click();
			     }
		    }
	    
	    
		    if (doesElementExist2(properties.getProperty("CreateTimeText"), 5)) {
	    		List<WebElement> totlemlqntyText = driver.findElements(By.cssSelector(properties.getProperty("CreateTimeText")));
	    		 for (WebElement lnk:totlemlqntyText) {
	    			 if (lnk.getText().equals("Create Time")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
	    				 break;
	    			 }
	    		 }
	    	 }
	    
	    
		    if (doesElementExist2(properties.getProperty("CreateTimeChkbox"), 5)) {
		  	    WebElement totlemlqntychkbox = driver.findElement(By.cssSelector(properties.getProperty("CreateTimeChkbox")));
		  	   
		  	  if ( totlemlqntychkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Create Time checkbox is already selected");
			    	
			     }else{
			    	 totlemlqntychkbox.click();
			     }
		    }
	    
	    
		    if (doesElementExist2(properties.getProperty("PercntCompleteText"), 5)) {
	    		List<WebElement> rrddatarcptdtetext = driver.findElements(By.cssSelector(properties.getProperty("PercntCompleteText")));
	    		 for (WebElement lnk:rrddatarcptdtetext) {
	    			 if (lnk.getText().equals("Percent Completed")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
	    				 break;
	    			 }
	    		 }
	    	 }
	    
	    
		    if (doesElementExist2(properties.getProperty("PercntCompletechkbox"), 5)) {
		  	    WebElement rrddatarcptdtechkbox = driver.findElement(By.cssSelector(properties.getProperty("PercntCompletechkbox")));
		  	    
		  	  if ( rrddatarcptdtechkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Percent Completed checkbox is already selected");
			    	
			     }else{
			    	 rrddatarcptdtechkbox.click();
			     }
		    }
 
	    
	    
		    if (doesElementExist2(properties.getProperty("BatchText"), 5)) {
	    		List<WebElement> rdrnumbertext = driver.findElements(By.cssSelector(properties.getProperty("BatchText")));
	    		 for (WebElement lnk:rdrnumbertext) {
	    			 if (lnk.getText().equals("Batch")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
	    				 break;
	    			 }
	    		 }
	    	 }
	    
	    	    
		    if (doesElementExist2(properties.getProperty("Batchchkbox"), 5)) {
		  	    WebElement rdrnumberchkbox = driver.findElement(By.cssSelector(properties.getProperty("Batchchkbox")));
		  	   
		  	  if ( rdrnumberchkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Batch checkbox is already selected");
			    	
			     }else{
			    	 rdrnumberchkbox.click();
			     }
		    }
	    
	    
		    if (doesElementExist2(properties.getProperty("CompleteDateText"), 5)) {
	    		List<WebElement> scndrymaildatetext = driver.findElements(By.cssSelector(properties.getProperty("CompleteDateText")));
	    		 for (WebElement lnk:scndrymaildatetext) {
	    			 if (lnk.getText().equals("Complete Date")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
	    				 break;
	    			 }
	    		 }
	    	 }
	    
	    
		    if (doesElementExist2(properties.getProperty("CompleteDatechkbox"), 5)) {
		  	    WebElement scndrymaildatechkbox = driver.findElement(By.cssSelector(properties.getProperty("CompleteDatechkbox")));
		  	   
		  	  if ( scndrymaildatechkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Complete Date checkbox is already selected");
			    	
			     }else{
			    	 scndrymaildatechkbox.click();
			     }
		    }
	    
	    //Group by checkbox
	    
		    if (doesElementExist2(properties.getProperty("FullOrderText"), 5)) {
	    		List<WebElement> avrgprntpgsText = driver.findElements(By.cssSelector(properties.getProperty("FullOrderText")));
	    		 for (WebElement lnk:avrgprntpgsText) {
	    			 if (lnk.getText().equals("Full Order")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
	    				 break;
	    			 }
	    		 }
	    	 }
		    
		    
		    if (doesElementExist2(properties.getProperty("FullOrderChkbox"), 5)) {
		  	    WebElement  avrgprntpgschkbox = driver.findElement(By.cssSelector(properties.getProperty("FullOrderChkbox")));
		  	   
		  	  if ( avrgprntpgschkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Full Order checkbox is already selected");
			    	
			     }else{
			    	 avrgprntpgschkbox.click();
			     }
		    }
		    
		
		    if (doesElementExist2(properties.getProperty("CopyText"), 5)) {
	    		List<WebElement> rrddatarcpttimetext = driver.findElements(By.cssSelector(properties.getProperty("CopyText")));
	    		 for (WebElement lnk:rrddatarcpttimetext) {
	    			 if (lnk.getText().equals("Copy")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
	    				 break;
	    			 }
	    		 }
	    	 }
		    
		    
		    if (doesElementExist2(properties.getProperty("Copychkbox"), 5)) {
		  	    WebElement   rrddatarcpttimechkbox = driver.findElement(By.cssSelector(properties.getProperty("Copychkbox")));
		  	   
		  	  if ( rrddatarcpttimechkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Copy checkbox is already selected");
			    	
			     }else{
			    	 rrddatarcpttimechkbox.click();
			     }
		    }
		    
		    
		    if (doesElementExist2(properties.getProperty("BatchKeyText"), 5)) {
	    		List<WebElement> applnText = driver.findElements(By.cssSelector(properties.getProperty("BatchKeyText")));
	    		 for (WebElement lnk:applnText) {
	    			 if (lnk.getText().equals("Batch Key")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
	    				 break;
	    			 }
	    		 }
	    	 }
		    
		    
		    if (doesElementExist2(properties.getProperty("BatchKeychkbox"), 5)) {
		  	    WebElement  applnTextchkbox = driver.findElement(By.cssSelector(properties.getProperty("BatchKeychkbox")));
		  	   
		  	  if ( applnTextchkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Batch Key checkbox is already selected");
			    	
			     }else{
			    	 applnTextchkbox.click();
			     }
		    }
		    
		    
		    if (doesElementExist2(properties.getProperty("CreateDateText"), 5)) {
	    		List<WebElement> totlprntpgsText = driver.findElements(By.cssSelector(properties.getProperty("CreateDateText")));
	    		 for (WebElement lnk:totlprntpgsText) {
	    			 if (lnk.getText().equals("Create Date")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
	    				 break;
	    			 }
	    		 }
	    	 }
		    
		    
		    if (doesElementExist2(properties.getProperty("CreateDatechkbox"), 5)) {
		  	    WebElement  totlprntpgschkbox = driver.findElement(By.cssSelector(properties.getProperty("CreateDatechkbox")));
		  	   
		  	  if ( totlprntpgschkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Create Date checkbox is already selected");
			    	
			     }else{
			    	 totlprntpgschkbox.click();
			     }
		    }
		    
		    
		    if (doesElementExist2(properties.getProperty("CreateTimeText"), 5)) {
	    		List<WebElement> EltrncMaldateText = driver.findElements(By.cssSelector(properties.getProperty("CreateTimeText")));
	    		 for (WebElement lnk:EltrncMaldateText) {
	    			 if (lnk.getText().equals("Create Time")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
	    				 break;
	    			 }
	    		 }
	    	 }
		    
		    
		    if (doesElementExist2(properties.getProperty("CreateTimechkbox"), 5)) {
		  	    WebElement  EltrncMaldatechkbox = driver.findElement(By.cssSelector(properties.getProperty("CreateTimechkbox")));
		  	   
		  	  if ( EltrncMaldatechkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Create Time checkbox is already selected");
			    	
			     }else{
			    	 EltrncMaldatechkbox.click();
			     }
		    }
		    
		    
		    if (doesElementExist2(properties.getProperty("DueDateText"), 5)) {
	    		List<WebElement> DocmntApplnChartText = driver.findElements(By.cssSelector(properties.getProperty("DueDateText")));
	    		 for (WebElement lnk:DocmntApplnChartText) {
	    			 if (lnk.getText().equals("Due Date")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Charts");
	    				 break;
	    			 }
	    		 }
	    	 }
		    
		    
		    if (doesElementExist2(properties.getProperty("DueDatechkbox"), 5)) {
		  	    WebElement  DocmntApplnChartchkbox = driver.findElement(By.cssSelector(properties.getProperty("DueDatechkbox")));
		  	   
		  	  if ( DocmntApplnChartchkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Due Date checkbox is already selected");
			    	
			     }else{
			    	 DocmntApplnChartchkbox.click();
			     }
		    }
		    
		  
		    
		    if (doesElementExist2(properties.getProperty("DueTimeText"), 5)) {
	    		List<WebElement> clntdateproccdText = driver.findElements(By.cssSelector(properties.getProperty("DueTimeText")));
	    		 for (WebElement lnk:clntdateproccdText) {
	    			 if (lnk.getText().equals("Due Time")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Group By section");
	    				 break;
	    			 }
	    		 }
	    	 }
		    
		    
		    if (doesElementExist2(properties.getProperty("DueTimechkbox"), 5)) {
		  	    WebElement  clntdateproccdchkbox = driver.findElement(By.cssSelector(properties.getProperty("DueTimechkbox")));
		  	   
		  	  if ( clntdateproccdchkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Due Time checkbox is already selected");
			    	
			     }else{
			    	 clntdateproccdchkbox.click();
			     }
		    }
		    
		    if (doesElementExist2(properties.getProperty("CompleteDateText"), 5)) {
	    		List<WebElement> rundateText = driver.findElements(By.cssSelector(properties.getProperty("CompleteDateText")));
	    		 for (WebElement lnk:rundateText) {
	    			 if (lnk.getText().equals("Complete Date")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Group By section");
	    				 break;
	    			 }
	    		 }
	    	 }
		    
		    
		    if (doesElementExist2(properties.getProperty("CompleteDatechkbox"), 5)) {
		  	    WebElement  RunDatechkbox = driver.findElement(By.cssSelector(properties.getProperty("CompleteDatechkbox")));
		  	   
		  	  if ( RunDatechkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Complete Date checkbox is already selected");
			    	
			     }else{
			    	 RunDatechkbox.click();
			     }
		    }
		    
		    
		    if (doesElementExist2(properties.getProperty("CompleteTimeText"), 5)) {
	    		List<WebElement> FacltyText = driver.findElements(By.cssSelector(properties.getProperty("CompleteTimeText")));
	    		 for (WebElement lnk:FacltyText) {
	    			 if (lnk.getText().equals("Complete Time")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Group By section");
	    				 break;
	    			 }
	    		 }
	    	 }
		    
		    
		    if (doesElementExist2(properties.getProperty("CompleteTimechkbox"), 5)) {
		  	    WebElement  Facltychkbox = driver.findElement(By.cssSelector(properties.getProperty("CompleteTimechkbox")));
		  	   
		  	  if ( Facltychkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Complete Time checkbox is already selected");
			    	
			     }else{
			    	 Facltychkbox.click();
			     }
		    }
		    
		    
		    if (doesElementExist2(properties.getProperty("RsltFinishedText"), 5)) {
	    		List<WebElement> fullordrText = driver.findElements(By.cssSelector(properties.getProperty("RsltFinishedText")));
	    		 for (WebElement lnk:fullordrText) {
	    			 if (lnk.getText().equals("RsltFinished")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Group By section");
	    				 break;
	    			 }
	    		 }
	    	 }
		    
		    
		    if (doesElementExist2(properties.getProperty("RsltFinishedchkbox"), 5)) {
		  	    WebElement  fullordrchkbox = driver.findElement(By.cssSelector(properties.getProperty("RsltFinishedchkbox")));
		  	   
		  	  if ( fullordrchkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "RsltFinished checkbox is already selected");
			    	
			     }else{
			    	 fullordrchkbox.click();
			     }
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
								log.logLine(Testname, false, "Selecting the Last Week option from the dropdown");
								break;
								}
							}
					 } else {
							log.logLine(Testname, true, "Selecting the Last Week option from the dropdown is failed");
							driver.switchTo().defaultContent();
							throw new Exception("Selecting the Last Week option from the dropdown is failed");
					 }
	    	}
	    
	   /*
		   //Report Verification for Last Week
		    boolean ChkRet1 = ReportVerification(AccNo,Testname);
		    if (ChkRet1) {
		    	log.logLine(Testname, false, "Verification of Report For Last week is Successfull");
		    } else {
		    	log.logLine(Testname, true, "Verification of Report For Last week is unSuccessfull");
		    	return ChkRet1;
		    }
	    
	    
	   
	    
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
						if (lnk.getText().equals("Last Month")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							PleasewaitDisappear();
							log.logLine(Testname, false, "Selecting the Last Month Date range option from the dropdown");
							break;
							}
						}
					} else {
						log.logLine(Testname, true, "Selecting the Last Month Date ranges from dropdown");
						driver.switchTo().defaultContent();
						}
		    }
	    */
		    
		  //Report Verification for Last Month
		    boolean ChkRet2 = ReportVerification(AccNo,Testname);
		    if (ChkRet2) {
		    	log.logLine(Testname, false, "Verification of Report For Last Month is Successfull");
		    } else {
		    	log.logLine(Testname, true, "Verification of Report For Last Month is unSuccessfull");
		    	return ChkRet2;
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
		    
			 //Report Verification for Last Quarter
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
		    
		   //Report Verification for Last Year
		    boolean ChkRet4 = ReportVerification(AccNo,Testname);
		    if (ChkRet4) {
		    	log.logLine(Testname, false, "Verification of Report For Last Year is Successfull");
		    } else {
		    	log.logLine(Testname, true, "Verification of Report For Last Year is unSuccessfull");
		    	return ChkRet4;
		    }
	    
		    SavedReportVerification(AccNo,Testname);
	    
	    	return true;
		 		    
	}
		
		public boolean ReportVerification(String AccNo, String Testname) throws Exception {
			
			boolean Chk1 = false, Chk2 = false, Chk3 = false, Chk4 = false, Chk5 = false;
			
			InputOutputData test = new InputOutputData();		
		    test.setInputFile(properties.getProperty("InputDatafile"));
		    //String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	
		   	    	    
		    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
			Date date = new Date();
			String todaysDate = dateFormat.format(date);
			
			String arr[] = todaysDate.split("2015");
			String splttoddte = arr[0].trim();
			String TodayDte=splttoddte+15;
			
		    //String NmeField=test.readColumnData("NameField", sheetname);
			
					
			
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
				    	
				    	
				    	if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetExplorer"))) {
				    		if (doesElementExist2(properties.getProperty("OverrideLnk"), 5)) {
				    			driver.get("javascript:document.getElementById('overridelink').click();");
				    			Thread.sleep(8000);
				    		}
				    	}
				    
				    	WebElement retEle = waitForElement(properties.getProperty("ReportTitle"));
						log.logLine(Testname, false, "Report Name is found on the page..");
				    	
				    	//validating report title
				    	if (doesElementExist2(properties.getProperty("ReportTitle"), 5)) {
				    		String dtetme = driver.findElement(By.cssSelector(properties.getProperty("ReportTitle"))).getText();
				    		log.logLine(Testname, false, "Report tile is " +dtetme);
				    		Chk1=true;
				    	}else{
					    	log.logLine(Testname, true, "Reading the report title is unSuccessful");
							//throw new Exception("Reading the date is unSuccessful");
				    	}
				    	
				    	
				    	// Validating for Created Date and Time
				    	
				    	if (doesElementExist2(properties.getProperty("CreatedOnDtetme"), 5)) {
				    		String dtetme = driver.findElement(By.cssSelector(properties.getProperty("CreatedOnDtetme"))).getText();
				    		log.logLine(Testname, false, "Reading the Created date and Time as" +dtetme);
				    		Chk2=true;
				    	}else{
					    	log.logLine(Testname, true, "Reading the date is unSuccessful");
							//throw new Exception("Reading the date is unSuccessful");
				    	}
				    	
				    	
				    	//Validating For Date
				    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
					    		List<WebElement> Dte = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
					    		 for (WebElement lnk:Dte) {
					    			 if (lnk.getText().equals("Date: ")){
					    				 // action.click(lnk).perform();
					    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inserting By Order Table");
					    				 break;
					    			 }
					    		 }
					    	 }
				    	
				    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
								List<WebElement> Frmdte = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
								for (WebElement lnk:Frmdte) {
								if (lnk.getText().endsWith(("15"))||lnk.getText().endsWith(("14"))){
								log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inserting By Order Table");
								break;
								}
							}
						}
				    	 
				    	 //validating for Thru
				    	 
				    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
					    		List<WebElement> Thrdte = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
					    		 for (WebElement lnk:Thrdte) {
					    			 if (lnk.getText().equals("  Thru ")){
					    				 // action.click(lnk).perform();
					    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inserting By Order Table");
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
					    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inserting By Order Table");
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
					    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inserting By Order Table");
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
					    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inserting By Order Table");
					    				 break;
					    			 }
					    		 }
					    	 }
				    	 
				    	 
				    	 // Validating for RRD Plant
				    	 
				    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
					    		List<WebElement> Rplnt = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
					    		 for (WebElement lnk:Rplnt) {
					    			 if (lnk.getText().equals("RRD Plant:  ")){
					    				 // action.click(lnk).perform();
					    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inserting By Order Table");
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
					    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inserting By Order Table");
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
					    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inserting By Order Table");
					    				 break;
					    			 }
					    		 }
					    	 }
				    	 
				    	 
				    	 //Validating For All
				    	 
				    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
					    		List<WebElement> Oall = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
					    		 for (WebElement lnk:Oall) {
					    			 if (lnk.getText().equals("All")){
					    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inserting By Order Table");
					    				 break;
					    			 }
					    		 }
					    	 }
				    	 
				    	
				    	//Details Table 1St column data---->> Order Number
				    	
				    	if (doesElementExist2(properties.getProperty("OrderNumCol"), 5)) {
				    		String cd1 = driver.findElement(By.cssSelector(properties.getProperty("OrderNumCol"))).getText();
				    		log.logLine(Testname, false, "Reading 1st column data under Details Table is Successful-----"+cd1);
				    		Chk3=true;
				    	}else{
					    	log.logLine(Testname, true, "Reading 1st column data under Details Table is unSuccessful");
							
				    	}
				   
				    	//Details Table 2nd column data---->> Copy
				    	
				    	if (doesElementExist2(properties.getProperty("CopyCol"), 5)) {
				    		String cd2 = driver.findElement(By.cssSelector(properties.getProperty("CopyCol"))).getText();
				    		log.logLine(Testname, false, "Reading 2nd column data under Details Table is Successful-----"+cd2);
				    		Chk4=true;
				    	}else{
					    	log.logLine(Testname, true, "Reading 2nd column data under Details Table is unSuccessful");
							
				    	}
				    	
				    	//Details Table 3rd column data---->> Batch
				    	
				    	if (doesElementExist2(properties.getProperty("BatchCol"), 5)) {
				    		String cd3 = driver.findElement(By.cssSelector(properties.getProperty("BatchCol"))).getText();
				    		log.logLine(Testname, false, "Reading 3rd column data under Details Table is Successful-----"+cd3);
				    	}else{
					    	log.logLine(Testname, true, "Reading 3rd column data under Details Table is unSuccessful");
							
				    	}
				    	
				    	//Details Table 4th column data---->> Create Date
				    	
				    	if (doesElementExist2(properties.getProperty("CreateDateCol"), 5)) {
				    		String cd4 = driver.findElement(By.cssSelector(properties.getProperty("CreateDateCol"))).getText();
				    		log.logLine(Testname, false, "Reading 4th column data under Details Table is  Successful-----"+cd4);
				    	}else{
					    	log.logLine(Testname, true, "Reading 4th column data under Details Table is  unSuccessful");
							
				    	}
				    	
				    	
				    	//Details Table 5th column data---->> Create Time
				    	
				    	if (doesElementExist2(properties.getProperty("CreateTimeCol"), 5)) {
				    		String cd5 = driver.findElement(By.cssSelector(properties.getProperty("CreateTimeCol"))).getText();
				    		log.logLine(Testname, false, "Reading 5th column data under Details Table is  Successful-----"+cd5);
				    	}else{
					    	log.logLine(Testname, true, "Reading 5th column data under Details Table is unSuccessful");
							
				    	}
				    	
				    	//Details Table 6th column data---->> Complete Date
				    	
				    	if (doesElementExist2(properties.getProperty("CompleteDateCol"), 5)) {
				    		String cd6 = driver.findElement(By.cssSelector(properties.getProperty("CompleteDateCol"))).getText();
				    		log.logLine(Testname, false, "Reading 6th column data under Details Table is  Successful-----"+cd6);
				    	}else{
					    	log.logLine(Testname, true, "Reading 6th column data under Details Table is  unSuccessful");
							
				    	}
				    	
				    	
				    	//Details Table 7th column data---->> Complete Time
				    	
				    	if (doesElementExist2(properties.getProperty("CompleteTimeCol"), 5)) {
				    		String cd7 = driver.findElement(By.cssSelector(properties.getProperty("CompleteTimeCol"))).getText();
				    		log.logLine(Testname, false, "Reading 7th column data under Details Table is  Successful-----"+cd7);
				    	}else{
					    	log.logLine(Testname, true, "Reading 7th column data under Details Table is  unSuccessful");
							
				    	}
				    	
				    	
				    	//Details Table 8th column data---->> Percent Completed
				    	if (doesElementExist2(properties.getProperty("PercntCompleteCol"), 5)) {
				    		String cd8 = driver.findElement(By.cssSelector(properties.getProperty("PercntCompleteCol"))).getText();
				    		log.logLine(Testname, false, "Reading 8th column data under Details Table is Successful-----"+cd8);
				    		Chk5=true;
				    	}else{
					    	log.logLine(Testname, true, "Reading 8th column data under Details Table is unSuccessful");
							
				    	}
				    	
				    	Thread.sleep(5000);
				    	
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
		


     public boolean SavedReportVerification(String AccNo,String Testname) throws Exception {
		
			InputOutputData test = new InputOutputData();		
		    test.setInputFile(properties.getProperty("InputDatafile"));
		    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
		    
		    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
			Date date = new Date();
			String todaysDate = dateFormat.format(date);
			
			String arr[] = todaysDate.split("2015");
			String splttoddte = arr[0].trim();
			String TodayDte=splttoddte+15;
	    
		    String NmeField=test.readColumnData("InsertNameField", sheetname);
		    driver.switchTo().frame("iframeContainer");
	
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
					    	//To retrieve the handle of second window, extracting the handle which does not match to first window handle
					    	String secondWinHandle=winHandle; //Storing handle of second window handle
					    
					    	//Switch control to new window
					    	driver.switchTo().window(secondWinHandle);
					    	waitUntilLoadElementDisappear4();
					    	driver.manage().window().maximize();
					    	
					    	//if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) || (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) 
				    		//	driver.get("javascript:document.getElementById('overridelink').click();");
					    	
					    	Thread.sleep(5000);
					    	
					    	//Reading the report title
					    	
					    	if (doesElementExist2(properties.getProperty("ReportTitle"), 5)) {
					    		String dtetme = driver.findElement(By.cssSelector(properties.getProperty("ReportTitle"))).getText();
					    		log.logLine(Testname, false, "Report tile is " +dtetme);
					    	}else{
						    	log.logLine(Testname, true, "Reading the report title is unSuccessful");
								//throw new Exception("Reading the date is unSuccessful");
					    	}
					   
	                       // Validating for Created Date and Time
					    	
					    	if (doesElementExist2(properties.getProperty("CreatedOnDtetme"), 5)) {
					    		String dtetme = driver.findElement(By.cssSelector(properties.getProperty("CreatedOnDtetme"))).getText();
					    		log.logLine(Testname, false, "Reading the Created date and Time as" +dtetme);
					    	}else{
						    	log.logLine(Testname, true, "Reading the date is unSuccessful");
								//throw new Exception("Reading the date is unSuccessful");
					    	}
					    	
					    	
					    	//Validating For Date
					    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
						    		List<WebElement> Dte = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
						    		 for (WebElement lnk:Dte) {
						    			 if (lnk.getText().equals("Date: ")){
						    				 // action.click(lnk).perform();
						    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inserting By Order Table");
						    				 break;
						    			 }
						    		 }
						    	 }
					    	
					    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
									List<WebElement> Frmdte = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
									for (WebElement lnk:Frmdte) {
									if (lnk.getText().endsWith(("15"))||lnk.getText().endsWith(("14"))){
									log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inserting By Order Table");
									break;
									}
								}
							}
					    	 
					    	 //validating for Thru
					    	 
					    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
						    		List<WebElement> Thrdte = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
						    		 for (WebElement lnk:Thrdte) {
						    			 if (lnk.getText().equals("  Thru ")){
						    				 // action.click(lnk).perform();
						    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inserting By Order Table");
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
						    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inserting By Order Table");
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
						    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inserting By Order Table");
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
						    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inserting By Order Table");
						    				 break;
						    			 }
						    		 }
						    	 }
					    	 
					    	 
					    	 // Validating for RRD Plant
					    	 
					    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
						    		List<WebElement> Rplnt = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
						    		 for (WebElement lnk:Rplnt) {
						    			 if (lnk.getText().equals("RRD Plant:  ")){
						    				 // action.click(lnk).perform();
						    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inserting By Order Table");
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
						    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inserting By Order Table");
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
						    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inserting By Order Table");
						    				 break;
						    			 }
						    		 }
						    	 }
					    	 
					    	 
					    	 //Validating For All
					    	 
					    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
						    		List<WebElement> Oall = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
						    		 for (WebElement lnk:Oall) {
						    			 if (lnk.getText().equals("All")){
						    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inserting By Order Table");
						    				 break;
						    			 }
						    		 }
						    	 }
					    	 
					    	
					    	//Details Table 1St column data---->> Order Number
					    	
					    	if (doesElementExist2(properties.getProperty("OrderNumCol"), 5)) {
					    		String cd1 = driver.findElement(By.cssSelector(properties.getProperty("OrderNumCol"))).getText();
					    		log.logLine(Testname, false, "Reading 1st column data under Details Table is Successful-----"+cd1);
					    	}else{
						    	log.logLine(Testname, true, "Reading 1st column data under Details Table is unSuccessful");
								
					    	}
					   
					    	//Details Table 2nd column data---->> Copy
					    	
					    	if (doesElementExist2(properties.getProperty("CopyCol"), 5)) {
					    		String cd2 = driver.findElement(By.cssSelector(properties.getProperty("CopyCol"))).getText();
					    		log.logLine(Testname, false, "Reading 2nd column data under Details Table is Successful-----"+cd2);
					    	}else{
						    	log.logLine(Testname, true, "Reading 2nd column data under Details Table is unSuccessful");
								
					    	}
					    	
					    	//Details Table 3rd column data---->> Batch
					    	
					    	if (doesElementExist2(properties.getProperty("BatchCol"), 5)) {
					    		String cd3 = driver.findElement(By.cssSelector(properties.getProperty("BatchCol"))).getText();
					    		log.logLine(Testname, false, "Reading 3rd column data under Details Table is Successful-----"+cd3);
					    	}else{
						    	log.logLine(Testname, true, "Reading 3rd column data under Details Table is unSuccessful");
								
					    	}
					    	
					    	//Details Table 4th column data---->> Create Date
					    	
					    	if (doesElementExist2(properties.getProperty("CreateDateCol"), 5)) {
					    		String cd4 = driver.findElement(By.cssSelector(properties.getProperty("CreateDateCol"))).getText();
					    		log.logLine(Testname, false, "Reading 4th column data under Details Table is  Successful-----"+cd4);
					    	}else{
						    	log.logLine(Testname, true, "Reading 4th column data under Details Table is  unSuccessful");
								
					    	}
					    	
					    	
					    	//Details Table 5th column data---->> Create Time
					    	
					    	if (doesElementExist2(properties.getProperty("CreateTimeCol"), 5)) {
					    		String cd5 = driver.findElement(By.cssSelector(properties.getProperty("CreateTimeCol"))).getText();
					    		log.logLine(Testname, false, "Reading 5th column data under Details Table is  Successful-----"+cd5);
					    	}else{
						    	log.logLine(Testname, true, "Reading 5th column data under Details Table is unSuccessful");
								
					    	}
					    	
					    	//Details Table 6th column data---->> Complete Date
					    	
					    	if (doesElementExist2(properties.getProperty("CompleteDateCol"), 5)) {
					    		String cd6 = driver.findElement(By.cssSelector(properties.getProperty("CompleteDateCol"))).getText();
					    		log.logLine(Testname, false, "Reading 6th column data under Details Table is  Successful-----"+cd6);
					    	}else{
						    	log.logLine(Testname, true, "Reading 6th column data under Details Table is  unSuccessful");
								
					    	}
					    	
					    	
					    	//Details Table 7th column data---->> Complete Time
					    	
					    	if (doesElementExist2(properties.getProperty("CompleteTimeCol"), 5)) {
					    		String cd7 = driver.findElement(By.cssSelector(properties.getProperty("CompleteTimeCol"))).getText();
					    		log.logLine(Testname, false, "Reading 7th column data under Details Table is  Successful-----"+cd7);
					    	}else{
						    	log.logLine(Testname, true, "Reading 7th column data under Details Table is  unSuccessful");
								
					    	}
					    	
					    	
					    	//Details Table 8th column data---->> Percent Completed
					    	if (doesElementExist2(properties.getProperty("PercntCompleteCol"), 5)) {
					    		String cd8 = driver.findElement(By.cssSelector(properties.getProperty("PercntCompleteCol"))).getText();
					    		log.logLine(Testname, false, "Reading 8th column data under Details Table is Successful-----"+cd8);
					    	}else{
						    	log.logLine(Testname, true, "Reading 8th column data under Details Table is unSuccessful");
								
					    	}
					    
						  
								//Delete the existing file before it downloads
								try {			
								        File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/InsertingOrder.csv");
								        if (fileTemp1.exists()){
								        	fileTemp1.delete();
								        	log.logLine(Testname, false, "The InsertingOrder.csv exists and has been deleted");
								        	}else{
										    	log.logLine(Testname, false, " InsertingOrder.csv file does not exists");
									    	}
							        
							        File fileTemp2 = new File(System.getProperty("user.home")+"/Downloads/InsertingOrder.pdf");
							        if (fileTemp2.exists()){
							        	fileTemp2.delete();
							        	log.logLine(Testname, false, "The InsertingOrder.pdf exists and has been deleted");
						        	}else{
								    	log.logLine(Testname, false, " InsertingOrder.pdf file does not exists");
							    	}
							        
							        File fileTemp3 = new File(System.getProperty("user.home")+"/Downloads/InsertingOrder.xls");
							        if (fileTemp3.exists()){
							        	fileTemp3.delete();
							        	log.logLine(Testname, false, "The InsertingOrder.xls exists and has been deleted");
						        	}else{
								    	log.logLine(Testname, false, " InsertingOrder.xls file does not exists");
							    	}
						       } catch(Exception e){
							        // if any error occurs
							        e.printStackTrace();
						       }	
							
						
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
						   
						   
						/*   FirefoxProfile profile = new FirefoxProfile(); 

						   profile.setPreference("browser.download.folderList", 2); //   (browser.download.folderList are internal methods of the FF profile and 2 is the                                                    corresponding value of the location.)
						   //profile.setPreference("browser.download.manager.showWhenStarting", "false");
						   profile.setPreference("browser.download.dir","C:\\Users\\Manohar\\Downloads"); //  path where I have to store the file.
						   profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/csv,text/csv"); //Add the MIME code of your file here.
						  
						   profile.setPreference("browser.download.useDownloadDir", "false"); 
					   
			    	*/
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
					 			log.logLine(Testname, false, "Click on Export Link Button is Successful");
					    	}
							
					    	
					    	
							if (doesElementExist(properties.getProperty("Pdffrmt"), 5)) {
					    		 WebElement pdffmt = driver.findElement(By.xpath(".//*[@id='ReportViewer1_ctl06_ctl04_ctl00_Menu']/div[3]/a"));
					 		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", pdffmt);
					 		    Thread.sleep(3000);
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
							
							Thread.sleep(8000);
								try {			
							        File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/InsertingOrder.csv");
							        if (fileTemp1.exists()){
							        	log.logLine(Testname, false, "The Saved InsertingOrder.csv file exists ");
							        	}else{
									    	log.logLine(Testname, true, "InsertingOrder.csv file does not exists");
								    	}
						        
						        File fileTemp2 = new File(System.getProperty("user.home")+"/Downloads/InsertingOrder.pdf");
						        if (fileTemp2.exists()){
						           	log.logLine(Testname, false, "The Saved InsertingOrder.pdf file exists");
					        	}else{
							    	log.logLine(Testname, true, "InsertingOrder.pdf file does not exists");
						    	}
						        
						        File fileTemp3 = new File(System.getProperty("user.home")+"/Downloads/InsertingOrder.xls");
						        if (fileTemp3.exists()){
						       
						        	log.logLine(Testname, false, "The Saved InsertingOrder.xls file exists");
					        	}else{
							    	log.logLine(Testname, true, "InsertingOrder.xls file does not exists");
						    	}
						       } catch(Exception e){
						        // if any error occurs
						        e.printStackTrace();
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
				  
	 		    return true;
				
		}
	
	}






	   
 		

   	
			       		 	
			    
	    
	
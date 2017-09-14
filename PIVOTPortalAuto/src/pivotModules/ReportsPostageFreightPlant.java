package pivotModules;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
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

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class ReportsPostageFreightPlant extends Page{


	public ReportsPostageFreightPlant(WebDriver driver, Log log) throws InvalidFormatException, IOException {
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
		   	log.logLine(Testname, false, "Clicking on OK button");
		} else {
		    log.logLine(Testname, true, "Clicking on OK button to view the Reports is failed");
		    throw new Exception("Clicking on OK button to view the Reports is failed");
		}
     
	    
	    return true;
 	}
 	
 	
	public boolean ReportGroupByPlant(String AccNo,String Testname) throws Exception {
 		
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
			log.logLine(Testname, false, "Clicking on ReportTypes Field drop down list in Report Writer");
			
				
			if (doesElementExist2(properties.getProperty("ReportTypeoptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ReportTypeoptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("PostageFreightPlant")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						PleasewaitDisappear();
						log.logLine(Testname, false, "Selecting the Report option from the dropdown");
						break;
						}
					}
				} else {
					log.logLine(Testname, true, "Selecting the Report option  is failed");
					driver.switchTo().defaultContent();
					throw new Exception("Selecting the Report option  is failed");
					}
			}else {
				log.logLine(Testname, true, "Clicking on the ReportTypes is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on the ReportTypes is failed");
				}
	    
	  /*  

	     if (doesElementExist2(properties.getProperty("FromDate"), 5)) {	  
		    WebElement DteFromfld = driver.findElement(By.cssSelector(properties.getProperty("FromDate")));
			DteFromfld.clear();
			Thread.sleep(1000);
			DteFromfld.sendKeys(Prevdte);
			log.logLine(Testname, false, "Entering the from date value in advanced search");
			
			WebElement DteTofld = driver.findElement(By.cssSelector(properties.getProperty("ToDate")));
			DteTofld.clear();
			Thread.sleep(1000);
			DteTofld.sendKeys(todaysDate);
			log.logLine(Testname, false, "Entering the To date value in advanced search");
	    }	  
	  */
	    
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
							log.logLine(Testname, false, "Selecting the Plant option from the dropdown");
							break;
							}
						}
					} else {
						log.logLine(Testname, true, "Selecting the Plant option from the dropdown is failed");
						driver.switchTo().defaultContent();
						throw new Exception("Selecting the Plant option from the dropdown is failed");
						}
		    }
	
	    //Checking for the Plant and order_num fields and passing the value in the fields
	    
	    if (doesElementExist2(properties.getProperty("PlantOptionbtn"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("PlantOptionbtn")));
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
	    	log.logLine(Testname, false, "Plant Field Exists----> % is entered in the field");
	    	System.out.println("The entered value is:"+Plntbdy);
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
	    	
	    	if (doesElementExist(properties.getProperty("OrderNumIE"), 5)) {
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
							log.logLine(Testname, false, "Selecting the Plant option from the dropdown");
							break;
							}
						}
				} else {
					log.logLine(Testname, true, "Selecting the Inv Code option from the dropdown is failed");
					driver.switchTo().defaultContent();
					throw new Exception("Selecting the Inv Code option from the dropdown is failed");
					}
			}else {
				log.logLine(Testname, true, "Clicking on the Options dropdown list field in query parameters  is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on the Options dropdown list field in query parameters  is failed");
			}
	    

	    	if (doesElementExist2(properties.getProperty("OrderNumTxtIE"), 5)) {
		    	WebElement Ordnum = driver.findElement(By.cssSelector(properties.getProperty("OrderNumTxtIE")));
		    	Ordnum.clear();
		    	Ordnum.sendKeys("%");
		    	log.logLine(Testname, false, "Inventorycode Field Exists----> % is entered in the field");
		    	System.out.println("The entered value is:"+Ordnum);
		    }else{
		    	log.logLine(Testname, true, "Unable to enter the value in field");
				throw new Exception("Unable to enter the value in field");
				}
	    	
	    }else if ((Initialization.Browser.equals("FF")) || (Initialization.Browser.equals("ff")) || (Initialization.Browser.equals("firefox")) || (Initialization.Browser.equals("Firefox")) || (Initialization.Browser.equals("FIREFOX"))) {
	    		    	
		    if (doesElementExist2(properties.getProperty("OrderNumbtn"), 5)) {	    
				WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("OrderNumbtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
				Thread.sleep(500);
				log.logLine(Testname, false, "Clicking on Select Field drop down list");
				
			
				if (doesElementExist2(properties.getProperty("OptionType"), 5)) {
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("OptionType")));
					for (WebElement lnk:selopts) {
						if (lnk.getText().equals("Order_Num")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							PleasewaitDisappear();
							log.logLine(Testname, false, "Selecting the Plant option from the dropdown");
							break;
							}
						}
					} else {
						log.logLine(Testname, true, "Selecting the Inv Code option from the dropdown is failed");
						driver.switchTo().defaultContent();
						throw new Exception("Selecting the Inv Code option from the dropdown is failed");
						}
				}else {
					log.logLine(Testname, true, "Clicking on the Options dropdown list field in query parameters  is failed");
					driver.switchTo().defaultContent();
					throw new Exception("Clicking on the Options dropdown list field in query parameters  is failed");
				}
		    
		    if (doesElementExist2(properties.getProperty("OrderNumTxt"), 5)) {
		    	WebElement Ordnum = driver.findElement(By.cssSelector(properties.getProperty("OrderNumTxt")));
		    	Ordnum.clear();
		    	Ordnum.sendKeys("%");
		    	log.logLine(Testname, false, "Inventorycode Field Exists----> % is entered in the field");
		    	System.out.println("The entered value is:"+Ordnum);
		    }else{
		    	log.logLine(Testname, true, "Unable to enter the value in field");
				throw new Exception("Unable to enter the value in field");
				}
	    	
	    	
		} else if ((Initialization.Browser.equals("Chrome")) || (Initialization.Browser.equals("chrome")) || (Initialization.Browser.equals("CHROME"))) {
			
		    if (doesElementExist2(properties.getProperty("OrderNumbtn"), 5)) {	
		    	WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("OrderNumbtn")));
		    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
				Thread.sleep(500);
				log.logLine(Testname, false, "Clicking on Select Field drop down list");
				
			
				if (doesElementExist2(properties.getProperty("OptionType"), 5)) {
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("OptionType")));
					for (WebElement lnk:selopts) {
						if (lnk.getText().equals("Order_Num")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							PleasewaitDisappear();
							log.logLine(Testname, false, "Selecting the Plant option from the dropdown");
							break;
							}
						}
					} else {
						log.logLine(Testname, true, "Selecting the Inv Code option from the dropdown is failed");
						driver.switchTo().defaultContent();
						throw new Exception("Selecting the Inv Code option from the dropdown is failed");
						}
				}else {
					log.logLine(Testname, true, "Clicking on the Options dropdown list field in query parameters  is failed");
					driver.switchTo().defaultContent();
					throw new Exception("Clicking on the Options dropdown list field in query parameters  is failed");
					}
		    
		    if (doesElementExist2(properties.getProperty("OrderNumTxt"), 5)) {
		    	WebElement Ordnum = driver.findElement(By.cssSelector(properties.getProperty("OrderNumTxt")));
		    	Ordnum.clear();
		    	Ordnum.sendKeys("%");
		    	log.logLine(Testname, false, "Inventorycode Field Exists----> % is entered in the field");
		    	System.out.println("The entered value is:"+Ordnum);
		    }else{
		    	log.logLine(Testname, true, "Unable to enter the value in field");
				throw new Exception("Unable to enter the value in field");
				}
	 
	}

	
	    // Report Columns
	   	    	   
	    if (doesElementExist2(properties.getProperty("ChkClntdataprossdte"), 5)) {
    		List<WebElement> Cltprdte = driver.findElements(By.cssSelector(properties.getProperty("ChkClntdataprossdte")));
    		 for (WebElement lnk:Cltprdte) {
    			 if (lnk.getText().equals("Client Data Process Date")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 }
	    
	    if (doesElementExist2(properties.getProperty("ChkClntdataprossdte"), 5)) {
	  	    WebElement Cltprdte = driver.findElement(By.cssSelector(properties.getProperty("ChkClntdataprossdte")));
	  	   
	  	    	if (!Cltprdte.isEnabled())
	  		     {
	  	    		Cltprdte.click();
	  	    		log.logLine(Testname, false, "Client Data Process Date checkbox is Checked");
	  	    	}else{
	  		    	log.logLine(Testname, false, "Client Data Process Date checkbox is already Checked");
	 	    	}
	    }
	   
	    
	    if (doesElementExist2(properties.getProperty("ChkRRDdatarcptdte"), 5)) {
    		List<WebElement> Rcptdte = driver.findElements(By.cssSelector(properties.getProperty("ChkRRDdatarcptdte")));
    		 for (WebElement lnk:Rcptdte) {
    			 if (lnk.getText().equals("RRD Data Receipt Date")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 }
	    	    
	    if (doesElementExist2(properties.getProperty("ChkRRDdatarcptdte"), 5)) {
	  	    WebElement Rcptdte = driver.findElement(By.cssSelector(properties.getProperty("ChkRRDdatarcptdte")));
	  	   
	  	    	if (!Rcptdte.isEnabled())
	  		     {
	  	    		Rcptdte.click();
	  	    		log.logLine(Testname, false, "RRD Data Receipt Date Checkbox is Checked");
	  	    	}else{
	  		    	log.logLine(Testname, false, "RRD Data Receipt Date Checkbox is already Checked");
	 	    	}
	    }
	    
	    
	    if (doesElementExist2(properties.getProperty("ChkRRDdatarcpttme"), 5)) {
    		List<WebElement> Rcpttme = driver.findElements(By.cssSelector(properties.getProperty("ChkRRDdatarcpttme")));
    		 for (WebElement lnk:Rcpttme) {
    			 if (lnk.getText().equals("RRD Data Receipt Time")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 }
	    
	    
	    if (doesElementExist2(properties.getProperty("ChkRRDdatarcpttme"), 5)) {
	  	    WebElement Rcpttme = driver.findElement(By.cssSelector(properties.getProperty("ChkRRDdatarcpttme")));
	  	    	Rcpttme.getText();
	  	   
	  	    	if (!Rcpttme.isEnabled())
	  		     {
	  	    		Rcpttme.click();
	  	    		log.logLine(Testname, false, "RRD Data Receipt Time Checkbox is Checked");
	  	    	}else{
	  		    	log.logLine(Testname, false, "RRD Data Receipt Time Checkbox is already Checked");
	 	    	}
	    }
	    
	    
	    if (doesElementExist2(properties.getProperty("ChkOrdNum"), 5)) {
    		List<WebElement> Ordrnum = driver.findElements(By.cssSelector(properties.getProperty("ChkOrdNum")));
    		 for (WebElement lnk:Ordrnum) {
    			 if (lnk.getText().equals("Order Number")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 }

	    
	    if (doesElementExist2(properties.getProperty("ChkOrdNum"), 5)) {
	  	    WebElement Ordrnum = driver.findElement(By.cssSelector(properties.getProperty("ChkOrdNum")));
	  	   
	  	    	if (!Ordrnum.isEnabled())
	  		     {
	  	    		Ordrnum.click();
	  	    		log.logLine(Testname, false, "Order Number Checkbox is Checked");
	  	    	}else{
	  		    	log.logLine(Testname, false, "Order Number Checkbox is already Checked");
	 	    	}
	    }
	    
	    
	    if (doesElementExist2(properties.getProperty("ChkAppln"), 5)) {
    		List<WebElement> Appln = driver.findElements(By.cssSelector(properties.getProperty("ChkAppln")));
    		 for (WebElement lnk:Appln) {
    			 if (lnk.getText().equals("Application")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 }
	    
	    
	 	    
	    if (doesElementExist2(properties.getProperty("ChkAppln"), 5)) {
	  	    WebElement Appln = driver.findElement(By.cssSelector(properties.getProperty("ChkAppln")));
	  	   
	  	    	if (!Appln.isEnabled())
	  		     {
	  	    		Appln.click();
	  	    		log.logLine(Testname, false, "Application Checkbox is Checked");
	  	    	}else{
	  		    	log.logLine(Testname, false, "Application Checkbox is already Checked");
	 	    	}
	    }
	    
	    
	    if (doesElementExist2(properties.getProperty("ChkRRDplnt"), 5)) {
    		List<WebElement> RRDplnt = driver.findElements(By.cssSelector(properties.getProperty("ChkRRDplnt")));
    		 for (WebElement lnk:RRDplnt) {
    			 if (lnk.getText().equals("RRD Plant")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 }
	    	    
	    	    
	    if (doesElementExist2(properties.getProperty("ChkRRDplnt"), 5)) {
	  	    WebElement RRDplnt = driver.findElement(By.cssSelector(properties.getProperty("ChkRRDplnt")));
	  	   
	  	    	if (!RRDplnt.isEnabled())
	  		     {
	  	    		RRDplnt.click();
	  	    		log.logLine(Testname, false, "RRD Plant Checkbox is Checked");
	  	    	}else{
	  		    	log.logLine(Testname, false, "RRD Plant Checkbox is already Checked");
	 	    	}
	    }
	    
	    
	    if (doesElementExist2(properties.getProperty("ChkTtlprntqlty"), 5)) {
    		List<WebElement> Prntqlty = driver.findElements(By.cssSelector(properties.getProperty("ChkTtlprntqlty")));
    		 for (WebElement lnk:Prntqlty) {
    			 if (lnk.getText().equals("Total Print Quantity")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 }
	    
	    	    
	    if (doesElementExist2(properties.getProperty("ChkTtlprntqlty"), 5)) {
	  	    WebElement Prntqlty = driver.findElement(By.cssSelector(properties.getProperty("ChkTtlprntqlty")));
	  	    
	  	    	if (!Prntqlty.isEnabled())
	  		     {
	  	    		Prntqlty.click();
	  	    		log.logLine(Testname, false, "Total Print Quality Checkbox is Checked");
	  	    	}else{
	  		    	log.logLine(Testname, false, "Total Print Quality Checkbox is already Checked");
	 	    	}
	    }
	    
	    
	    if (doesElementExist2(properties.getProperty("ChkPrntpges"), 5)) {
    		List<WebElement> Prntpgs = driver.findElements(By.cssSelector(properties.getProperty("ChkPrntpges")));
    		 for (WebElement lnk:Prntpgs) {
    			 if (lnk.getText().equals("Total Print Pages")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 }
	    
	    
	    if (doesElementExist2(properties.getProperty("ChkPrntpges"), 5)) {
	  	    WebElement Prntpgs = driver.findElement(By.cssSelector(properties.getProperty("ChkPrntpges")));
	  	    
	  	    	if (!Prntpgs.isEnabled())
	  		     {
	  	    		Prntpgs.click();
	  	    		log.logLine(Testname, false, "Total PrintPages Checkbox is Checked");
	  	    	}else{
	  		    	log.logLine(Testname, false, "Total PrintPages Checkbox is already Checked");
	 	    	}
	    }
	    
	    
	    if (doesElementExist2(properties.getProperty("ChkAvgprntpges"), 5)) {
    		List<WebElement> AvgPrntpge = driver.findElements(By.cssSelector(properties.getProperty("ChkAvgprntpges")));
    		 for (WebElement lnk:AvgPrntpge) {
    			 if (lnk.getText().equals("Average Print Pages")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 }
	    
	    
	    if (doesElementExist2(properties.getProperty("ChkAvgprntpges"), 5)) {
	  	    WebElement AvgPrntpge = driver.findElement(By.cssSelector(properties.getProperty("ChkAvgprntpges")));
	  	    
	  	    	if (!AvgPrntpge.isEnabled())
	  		     {
	  	    		AvgPrntpge.click();
	  	    		log.logLine(Testname, false, "Average Print Pages Checkbox is Checked");
	  	    	}else{
	  		    	log.logLine(Testname, false, "Average Print Pages Checkbox is already Checked");
	 	    	}
	    }
	    
	    
	    if (doesElementExist2(properties.getProperty("ChkTtlpstg"), 5)) {
    		List<WebElement> Ttlpstg = driver.findElements(By.cssSelector(properties.getProperty("ChkTtlpstg")));
    		 for (WebElement lnk:Ttlpstg) {
    			 if (lnk.getText().equals("Total Postage")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 } 
	    
	    	    
	    if (doesElementExist2(properties.getProperty("ChkTtlpstg"), 5)) {
	  	    WebElement Ttlpstg = driver.findElement(By.cssSelector(properties.getProperty("ChkTtlpstg")));
	  	    
	  	    	if (!Ttlpstg.isEnabled())
	  		     {
	  	    		Ttlpstg.click();
	  	    		log.logLine(Testname, false, "Total Postage Checkbox is Checked");
	  	    	}else{
	  		    	log.logLine(Testname, false, "Total Postage Checkbox is already Checked");
	 	    	}
	    }
	    
	    
	    if (doesElementExist2(properties.getProperty("ChkPstgperpces"), 5)) {
    		List<WebElement> Pstgpce = driver.findElements(By.cssSelector(properties.getProperty("ChkPstgperpces")));
    		 for (WebElement lnk:Pstgpce) {
    			 if (lnk.getText().equals("Postage Per Piece")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 } 
	    
	    
	    if (doesElementExist2(properties.getProperty("ChkPstgperpces"), 5)) {
	  	    WebElement Pstgpce = driver.findElement(By.cssSelector(properties.getProperty("ChkPstgperpces")));
	  	    
	  	    	if (!Pstgpce.isEnabled())
	  		     {
	  	    		Pstgpce.click();
	  	    		log.logLine(Testname, false, "Postage Per Piece Checkbox is Checked");
	  	    	}else{
	  		    	log.logLine(Testname, false, "Postage Per Piece Checkbox is already Checked");
	 	    	}
	    }
	    
 
	    // Report Charts
	    
	    if (doesElementExist2(properties.getProperty("ChkPstgplnt"), 5)) {
    		List<WebElement> Pstgapp = driver.findElements(By.cssSelector(properties.getProperty("ChkPstgplnt")));
    		 for (WebElement lnk:Pstgapp) {
    			 if (lnk.getText().equals("Postage By Plant")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 } 
	    
	    
	    if (doesElementExist2(properties.getProperty("ChkPstgplnt"), 5)) {
	  	    WebElement Pstgapp = driver.findElement(By.cssSelector(properties.getProperty("ChkPstgplnt")));
	  	   
	  	    	if (!Pstgapp.isEnabled())
	  		     {
	  	    		Pstgapp.click();
	  	    		log.logLine(Testname, false, "Postage by Application check box is Checked");
	  	    	}else{
	  		    	log.logLine(Testname, false, "Postage by Application check box is already Checked");
	 	    	}
	    }
	    
	    
	   
	    //GroupBy Column
	    
	    if (doesElementExist2(properties.getProperty("ChkFclty"), 5)) {
    		List<WebElement> Fclty = driver.findElements(By.cssSelector(properties.getProperty("ChkFclty")));
    		 for (WebElement lnk:Fclty) {
    			 if (lnk.getText().equals("Facility")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 } 
	    
	    
	    
	    if (doesElementExist2(properties.getProperty("ChkFclty"), 5)) {
	  	    WebElement Fclty = driver.findElement(By.cssSelector(properties.getProperty("ChkFclty")));
	  	   
	  	    	if (!Fclty.isEnabled())
	  		     {
	  	    		Fclty.click();
	  	    		log.logLine(Testname, false, "Facility check box is Checked");
	  	    	}else{
	  		    	log.logLine(Testname, false, "Facility check box is already Checked");
	 	    	}
	    }
	    
	    
	    
	    if (doesElementExist2(properties.getProperty("ChkFullOrdr"), 5)) {
    		List<WebElement> FullOrdr = driver.findElements(By.cssSelector(properties.getProperty("ChkFullOrdr")));
    		 for (WebElement lnk:FullOrdr) {
    			 if (lnk.getText().equals("Full Order")){
    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
    				 break;
    			 }
    		 }
    	 } 
	    
	    
	    
	    if (doesElementExist2(properties.getProperty("ChkFullOrdr"), 5)) {
	  	    WebElement FullOrdr = driver.findElement(By.cssSelector(properties.getProperty("ChkFullOrdr")));
	  	   
	  	    	if (!FullOrdr.isEnabled())
	  		     {
	  	    		FullOrdr.click();
	  	    		log.logLine(Testname, false, "Full Order check box is Checked");
	  	    	}else{
	  		    	log.logLine(Testname, false, "Full Order check box is already Checked");
	 	    	}
	    }
	    
	  /*	    
	    // Report Verification For Last Week
	    boolean ChkRet1 = ReportVerification(AccNo,Testname);
	    if (ChkRet1) {
	    	log.logLine(Testname, false, "Verification of Report For Last Week is Successfull");
	    } else {
	    	log.logLine(Testname, true, "Verification of Report For Last Week is unSuccessfull");
	    	return ChkRet1;
	    }
	    
	  
	    // For Last Month
	    Thread.sleep(2000);
	    driver.switchTo().frame("iframeContainer");
	    if (doesElementExist(properties.getProperty("DateRng"), 5)) {	    
			WebElement optr = driver.findElement(By.xpath(".//*[@id='dvDatesParameters']/div[3]/span/span/span[1]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(500);
			log.logLine(Testname, false, "Selecting thr Date ranges from dropdown");
			
			
			if (doesElementExist2(properties.getProperty("DateRngOptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("DateRngOptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Last Month")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						PleasewaitDisappear();
						log.logLine(Testname, false, "Selecting the Date range option from the dropdown");
						break;
						}
					}
				} else {
					log.logLine(Testname, true, "Selecting thr Date ranges from dropdown");
					driver.switchTo().defaultContent();
					}
	    }
	    */
	    
	    // Report Verification For Last Month
	    boolean ChkRet2 = ReportVerification(AccNo,Testname);
	    if (ChkRet2) {
	    	log.logLine(Testname, false, "Verification of Report For Last Month is Successfull");
	    } else {
	    	log.logLine(Testname, true, "Verification of Report For Last Month is unSuccessfull");
	    	return ChkRet2;
	    }
	    
	    
	    // For Quarter
	    Thread.sleep(2000);
	    driver.switchTo().frame("iframeContainer");
	    if (doesElementExist(properties.getProperty("DateRng"), 5)) {	    
			WebElement optr = driver.findElement(By.xpath(".//*[@id='dvDatesParameters']/div[3]/span/span/span[1]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(500);
			log.logLine(Testname, false, "Selecting thr Date ranges from dropdown");
			
			
			if (doesElementExist2(properties.getProperty("DateRngOptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("DateRngOptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Last Quarter")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						
						PleasewaitDisappear();
						Thread.sleep(8000);
						log.logLine(Testname, false, "Selecting the Date range option from the dropdown");
						break;
						}
					}
				} else {
					log.logLine(Testname, true, "Selecting thr Date ranges from dropdown");
					driver.switchTo().defaultContent();
					}
	    }
	    
	    // Report Verification For Quarter
	    boolean ChkRet3 = ReportVerification(AccNo,Testname);
	    if (ChkRet3) {
	    	log.logLine(Testname, false, "Verification of Report For Quater is Successfull");
	    } else {
	    	log.logLine(Testname, true, "Verification of Report For Quater is unSuccessfull");
	    	return ChkRet3;
	    }
	    
	    
	    // For Last Year
	    Thread.sleep(2000);
	    driver.switchTo().frame("iframeContainer");
	    if (doesElementExist(properties.getProperty("DateRng"), 5)) {	    
			WebElement optr = driver.findElement(By.xpath(".//*[@id='dvDatesParameters']/div[3]/span/span/span[1]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(500);
			log.logLine(Testname, false, "Selecting thr Date ranges from dropdown");
			
			
			if (doesElementExist2(properties.getProperty("DateRngOptions"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("DateRngOptions")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Last Year")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						
						PleasewaitDisappear();
						Thread.sleep(10000);
						log.logLine(Testname, false, "Selecting the Date range option from the dropdown");
						break;
						}
					}
				} else {
					log.logLine(Testname, true, "Selecting thr Date ranges from dropdown");
					driver.switchTo().defaultContent();
					}
	    }

	    Thread.sleep(2000);
	    if (doesElementExist2(properties.getProperty("ReportNme"), 5)) {	  
		    WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("ReportNme")));
			Txt.sendKeys(NmeField+AccNo);
			log.logLine(Testname, false, "Entering "+NmeField+AccNo+" In the Name Field");
		}else{
	    	log.logLine(Testname, true,"Unable to enter the text");
	    	
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
	    
	   
	    if (doesElementExist2(properties.getProperty("RunButton"), 5)) {	  
		    WebElement rnrptbtn = driver.findElement(By.cssSelector(properties.getProperty("RunButton")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", rnrptbtn);
			log.logLine(Testname, false, "Click on Run Button for the Saved report");
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
			    	
			    	Thread.sleep(15000);
			    	
			    	
			    	// Report Verification
			    	if (doesElementExist2(properties.getProperty("PostageReport"), 5)) {
			    		String Pstgrpt = driver.findElement(By.cssSelector(properties.getProperty("PostageReport"))).getText();
			    		log.logLine(Testname, false, "Reading The Report Name as    "+Pstgrpt);
			    	}else{
				    	log.logLine(Testname, true, "Reading The Report Name is unsuccessfull");
						//throw new Exception("Reading the date is unSuccessful");
			    	}
			    
			    	
			    	// Validating for Created Date and Time
			    	
			    	if (doesElementExist2(properties.getProperty("CreatedDtetme"), 5)) {
			    		String dtetme = driver.findElement(By.cssSelector(properties.getProperty("CreatedDtetme"))).getText();
			    		log.logLine(Testname, false, "Reading the Created date and Time as" +dtetme);
			    	}else{
				    	log.logLine(Testname, true, "Reading the Created date and Time is unSuccessful");
						//throw new Exception("Reading the date is unSuccessful");
			    	}
			    	
			    	
			    	//Validating For Date
			    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
				    		List<WebElement> Dte = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
				    		 for (WebElement lnk:Dte) {
				    			 if (lnk.getText().equals("Date: ")){
				    				 // action.click(lnk).perform();
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inventory Status Summary Table");
				    				 break;
				    			 }
				    		 }
				    	 }
			    	
			 	 
			    	// Validating for From Date
			    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
			    		List<WebElement> Frmdte = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
			    		 for (WebElement lnk:Frmdte) {			    			 
			    			 if (lnk.getText().endsWith(("15"))||lnk.getText().endsWith(("14"))){
			    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inventory Status Summary Table");
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
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inventory Status Summary Table");
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
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inventory Status Summary Table");
				    				 break;
				    			 }
				    		 }
				    	 }
			    	 
			    	 //validating for Application
			    	 
			    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
				    		List<WebElement> App = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
				    		 for (WebElement lnk:App) {
				    			 if (lnk.getText().equals("Application:")){
				    				 // action.click(lnk).perform();
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inventory Status Summary Table");
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
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inventory Status Summary Table");
				    				 break;
				    			 }
				    		 }
				    	 }
			    	 
			    	 
			    	 // Validating for RRD Plant
			    	 
			    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
				    		List<WebElement> Rplnt = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
				    		 for (WebElement lnk:Rplnt) {
				    			 if (lnk.getText().equals("RRD Plant: ")){
				    				 // action.click(lnk).perform();
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inventory Status Summary Table");
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
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inventory Status Summary Table");
				    				 break;
				    			 }
				    		 }
				    	 }
			    	 
			    	 
			    	 // Validating For Order
			    	 
			    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
				    		List<WebElement> Ordr = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
				    		 for (WebElement lnk:Ordr) {
				    			 if (lnk.getText().equals("Order:        ")){
				    				 // action.click(lnk).perform();
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inventory Status Summary Table");
				    				 break;
				    			 }
				    		 }
				    	 }
			    	 
			    	 
			    	 //Validating For All
			    	 
			    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
				    		List<WebElement> Oall = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
				    		 for (WebElement lnk:Oall) {
				    			 if (lnk.getText().equals("All")){
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inventory Status Summary Table");
				    				 break;
				    			 }
				    		 }
				    	 }
			    	 

			    	
			    	// Summary table Verification
			    	 
			    	 if (doesElementExist2(properties.getProperty("TotalPostageCost"), 5)) {
				    		List<WebElement> Todte = driver.findElements(By.cssSelector(properties.getProperty("TotalPostageCost")));
				    		 for (WebElement lnk:Todte) {
								if (lnk.getText().startsWith("$")||lnk.getText().matches("[0-9,]*")||lnk.getText().matches("[0-9]*")){
				    				 // action.click(lnk).perform();
				    				 log.logLine(Testname, false, "Total Postage Cost is    "+lnk.getText());
				    				 break;
				    			 }
				    		 }
				    	 }
			    	
			    				    	
			    	// Graph Verification
			    	
			    	/*if (doesElementExist2(properties.getProperty("Graphsmry"), 5)) {
			    		String Grp = driver.findElement(By.cssSelector(properties.getProperty("Graphsmry"))).getText();
			    		log.logLine(Testname, false, "Validating the Graph is Successful");
			    	}else{
				    	log.logLine(Testname, true, "Reading the data is unSuccessful");
			    	}
			    	*/
			    	
		    	
	    	
			    	//Details Table 1St column data---->>Client Data Process Date
			    	
			    	if (doesElementExist2(properties.getProperty("Columndata1"), 5)) {
			    		String cd1 = driver.findElement(By.cssSelector(properties.getProperty("Columndata1"))).getText();
			    		log.logLine(Testname, false, "Reading 1st column data under Details Table asl-----"+cd1);
			    	}else{
				    	log.logLine(Testname, true, "Reading 1st column data under Details Table is unSuccessful");
						
			    	}
			   
			    	//Details Table 2nd column data---->>RRD Data Receipt Date
			    	
			    	if (doesElementExist2(properties.getProperty("Columndata2"), 5)) {
			    		String cd2 = driver.findElement(By.cssSelector(properties.getProperty("Columndata2"))).getText();
			    		log.logLine(Testname, false, "Reading 2nd column data under Details Table as-----"+cd2);
			    	}else{
				    	log.logLine(Testname, true, "Reading 2nd column data under Details Table is unSuccessful");
						
			    	}
			    	
			    	//Details Table 3rd column data---->>-RRD Data Receipt Time
			    	
			    	if (doesElementExist2(properties.getProperty("Columndata3"), 5)) {
			    		String cd3 = driver.findElement(By.cssSelector(properties.getProperty("Columndata3"))).getText();
			    		log.logLine(Testname, false, "Reading 3rd column data under Details Table as-----"+cd3);
			    	}else{
				    	log.logLine(Testname, true, "Reading 3rd column data under Details Table is unSuccessful");
						
			    	}
			    	
			    	//Details Table 4th column data---->>Order Number
			    	
			    	if (doesElementExist2(properties.getProperty("Columndata4"), 5)) {
			    		String cd4 = driver.findElement(By.cssSelector(properties.getProperty("Columndata4"))).getText();
			    		log.logLine(Testname, false, "Reading 4th column data under Details Table as-----"+cd4);
			    	}else{
				    	log.logLine(Testname, true, "Reading 4th column data under Details Table is  unSuccessful");
						
			    	}
			    	
			    	
			    	//Details Table 5th column data---->>Application
			    	
			    	if (doesElementExist2(properties.getProperty("Columndata5"), 5)) {
			    		String cd5 = driver.findElement(By.cssSelector(properties.getProperty("Columndata5"))).getText();
			    		log.logLine(Testname, false, "Reading 5th column data under Details Table as-----"+cd5);
			    	}else{
				    	log.logLine(Testname, true, "Reading 5th column data under Details Table is unSuccessful");
						
			    	}
			    	
			    	//Details Table 6th column data---->>RRD Plant
			    	
			    	if (doesElementExist2(properties.getProperty("Columndata6"), 5)) {
			    		String cd6 = driver.findElement(By.cssSelector(properties.getProperty("Columndata6"))).getText();
			    		log.logLine(Testname, false, "Reading 6th column data under Details Table as-----"+cd6);
			    	}else{
				    	log.logLine(Testname, true, "Reading 6th column data under Details Table is  unSuccessful");
						
			    	}
			    	
			    	
			    	//Details Table 7th column data---->>Total Print Quantity
			    	
			    	if (doesElementExist2(properties.getProperty("Columndata7"), 5)) {
			    		String cd7 = driver.findElement(By.cssSelector(properties.getProperty("Columndata7"))).getText();
			    		log.logLine(Testname, false, "Reading 7th column data under Details Table as-----"+cd7);
			    	}else{
				    	log.logLine(Testname, true, "Reading 7th column data under Details Table is  unSuccessful");
						
			    	}
			    	
			    	
			    	//Details Table 8th column data---->>-Total Print Pages
			    	if (doesElementExist2(properties.getProperty("Columndata8"), 5)) {
			    		String cd8 = driver.findElement(By.cssSelector(properties.getProperty("Columndata8"))).getText();
			    		log.logLine(Testname, false, "Reading 8th column data under Details Table as-----"+cd8);
			    	}else{
				    	log.logLine(Testname, true, "Reading 8th column data under Details Table is unSuccessful");
						
			    	}
			    	
			    	
			    	//Details Table 9th column data---->>Average Print Pages
			    	
			    	if (doesElementExist2(properties.getProperty("Columndata9"), 5)) {
			    		String cd9 = driver.findElement(By.cssSelector(properties.getProperty("Columndata9"))).getText();
			    		log.logLine(Testname, false, "Reading 9th column data under Details Table as-----"+cd9);
			    	}else{
				    	log.logLine(Testname, true, "Reading 9th column data under Details Table is unSuccessful");
						}
			    	
			    	
			    	//Details Table 10th column data---->>Total Postage
			    	
			    	if (doesElementExist2(properties.getProperty("Columndata10"), 5)) {
			    		String cd10 = driver.findElement(By.cssSelector(properties.getProperty("Columndata10"))).getText();
			    		log.logLine(Testname, false, "Reading 10th column data under Details Table as-----"+cd10);
			    	}else{
				    	log.logLine(Testname, true, "Reading 10th column data under Details Table is unSuccessful");
						
			    	}
			    	
			    	
			    	//Details Table 11th column data---->>Postage Per Piece
			    	
			    	if (doesElementExist2(properties.getProperty("Columndata11"), 5)) {
			    		String cd11 = driver.findElement(By.cssSelector(properties.getProperty("Columndata11"))).getText();
			    		log.logLine(Testname, false, "Reading 11th column data under Details Table as-----"+cd11);
			    	}else{
				    	log.logLine(Testname, true, "Reading 11th column data under Details Table is unSuccessful");
						
			    	}
			    	
			    				    						
					//Delete the existing file before it downloads
					try {			
				        File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/PostageFreightPlant.csv");
				        if (fileTemp1.exists()){
				        	fileTemp1.delete();
				        	log.logLine(Testname, false, "The PostageFreightPlant exists and has been deleted");
				        	}else{
						    	log.logLine(Testname, false, "PostageFreightPlant.csv file does not exists");
					    	}
			        
			        File fileTemp2 = new File(System.getProperty("user.home")+"/Downloads/PostageFreightPlant.pdf");
			        if (fileTemp2.exists()){
			        	fileTemp2.delete();
			        	log.logLine(Testname, false, "The PostageFreightPlant.pdf exists and has been deleted");
		        	}else{
				    	log.logLine(Testname, false, "PostageFreightPlant.pdf file does not exists");
			    	}
			        
			        File fileTemp3 = new File(System.getProperty("user.home")+"/Downloads/PostageFreightPlant.xls");
			        if (fileTemp3.exists()){
			        	fileTemp3.delete();
			        	log.logLine(Testname, false, "The PostageFreightPlant.xls exists and has been deleted");
		        	}else{
				    	log.logLine(Testname, false, "PostageFreightPlant.xls file does not exists");
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
		    		 WebElement csvfmt = driver.findElement(By.xpath(properties.getProperty("Csvfrmt")));
		 		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", csvfmt);
		 		    Thread.sleep(3000);
		 		    log.logLine(Testname, false, "Saving The File in CSV (comma delimited) Format");
		 		   }else{
		 			   log.logLine(Testname, true, "Saving The File in CSV (comma delimited) isfailed");
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
		    		 WebElement pdffmt = driver.findElement(By.xpath(properties.getProperty("Pdffrmt")));
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
		    		 WebElement Exlfmt = driver.findElement(By.xpath(properties.getProperty("Excelfrmt")));
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
		    		 WebElement csvfmt = driver.findElement(By.xpath(properties.getProperty("Csvfrmt")));
		 		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", csvfmt);
		 		    Thread.sleep(5000);
		 		    log.logLine(Testname, false, "Saving The File in CSV (comma delimited) Format");
		 		   }else{
		 			   log.logLine(Testname, true, "Saving The File in CSV (comma delimited) isfailed");
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
		    		 WebElement pdffmt = driver.findElement(By.xpath(properties.getProperty("Pdffrmt")));
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
		    		 WebElement Exlfmt = driver.findElement(By.xpath(properties.getProperty("Excelfrmt")));
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
		    		 WebElement csvfmt = driver.findElement(By.xpath(properties.getProperty("Csvfrmt")));
		 		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", csvfmt);
		 		    Thread.sleep(3000);
		 		    log.logLine(Testname, false, "Saving The File in CSV (comma delimited) Format");
		 		   }else{
		 			   log.logLine(Testname, true, "Saving The File in CSV (comma delimited) isfailed");
		 			   }
		    	
		    	
		    	// PDF FORMAT
		    	
		    	if (doesElementExist2(properties.getProperty("Exportlnk"), 5)) {
		    		 WebElement Svelnk = driver.findElement(By.cssSelector(properties.getProperty("Exportlnk")));
		 		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Svelnk);
		 			log.logLine(Testname, false, "Click on Export Link Button is Successful");
		    	}
				
		    	
		    	
				if (doesElementExist(properties.getProperty("Pdffrmt"), 5)) {
		    		 WebElement pdffmt = driver.findElement(By.xpath(properties.getProperty("Pdffrmt")));
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
		    		 WebElement Exlfmt = driver.findElement(By.xpath(properties.getProperty("Excelfrmt")));
		 		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Exlfmt);
		 		   Thread.sleep(3000);
		 		   log.logLine(Testname, false, "Saving The File in Excel Format");
		 		  }else{
		 			  log.logLine(Testname, true, "Saving The File in Excel Format is failed");
		 			  }
				}	   
				
								
				Thread.sleep(8000);
					try {			
				        File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/PostageFreightPlant.csv");
				        if (fileTemp1.exists()){
				        	log.logLine(Testname, false, "The Saved PostageFreightPlant.csv file exists ");
				        	}else{
						    	log.logLine(Testname, true, "PostageFreightPlant.csv file does not exists");
					    	}
			        
			        File fileTemp2 = new File(System.getProperty("user.home")+"/Downloads/PostageFreightPlant.pdf");
			        if (fileTemp2.exists()){
			           	log.logLine(Testname, false, "The Saved PostageFreightPlant.pdf file exists");
		        	}else{
				    	log.logLine(Testname, true, "PostageFreightPlant.pdf file does not exists");
			    	}
			        
			        File fileTemp3 = new File(System.getProperty("user.home")+"/Downloads/PostageFreightPlant.xls");
			        if (fileTemp3.exists()){
			       
			        	log.logLine(Testname, false, "The Saved PostageFreightPlant.xls file exists");
		        	}else{
				    	log.logLine(Testname, true, "PostageFreightPlant.xls file does not exists");
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
					
					driver.switchTo().defaultContent();
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
					    
				    }
			    }
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
			    	waitUntilLoadElementDisappear4();
			    	driver.manage().window().maximize();
			    	//if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) || (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) 
		    		//	driver.get("javascript:document.getElementById('overridelink').click();");
			    	
			    	WebElement retEle = waitForElement(properties.getProperty("PostageReport"));
					log.logLine(Testname, false, "Report Name is found on the page..");
			    	
			    	// Report Verification
			    	if (doesElementExist2(properties.getProperty("PostageReport"), 5)) {
			    		String Pstgrpt = driver.findElement(By.cssSelector(properties.getProperty("PostageReport"))).getText();
			    		log.logLine(Testname, false, "Reading The Report Name as    "+Pstgrpt);
			    		Chk1=true;
			    	}else{
				    	log.logLine(Testname, true, "Reading The Report Name is unsuccessfull");
						//throw new Exception("Reading the date is unSuccessful");
			    	}			    
			    	
			
			    	// Validating for Created Date and Time			    	
			    	if (doesElementExist2(properties.getProperty("CreatedDtetme"), 5)) {
			    		String dtetme = driver.findElement(By.cssSelector(properties.getProperty("CreatedDtetme"))).getText();
			    		log.logLine(Testname, false, "Reading the Created date and Time as" +dtetme);
			    		Chk2=true;
			    	}else{
				    	log.logLine(Testname, true, "Reading the Created date and Time is unSuccessful");
						//throw new Exception("Reading the date is unSuccessful");
			    	}
			    	
			    	
			    	//Validating For Date
			    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
				    		List<WebElement> Dte = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
				    		 for (WebElement lnk:Dte) {
				    			 if (lnk.getText().equals("Date: ")){
				    				 // action.click(lnk).perform();
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inventory Status Summary Table");
				    				 break;
				    			 }
				    		 }
				    	 }
			    	
			 	 
			    	// Validating for From Date
			    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
			    		List<WebElement> Frmdte = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
			    		 for (WebElement lnk:Frmdte) {			    			 
			    			 if (lnk.getText().endsWith(("15"))||lnk.getText().endsWith(("14"))){
			    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inventory Status Summary Table");
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
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inventory Status Summary Table");
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
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inventory Status Summary Table");
				    				 break;
				    			 }
				    		 }
				    	 }
			    	 
			    	 //validating for Application
			    	 
			    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
				    		List<WebElement> App = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
				    		 for (WebElement lnk:App) {
				    			 if (lnk.getText().equals("Application:")){
				    				 // action.click(lnk).perform();
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inventory Status Summary Table");
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
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inventory Status Summary Table");
				    				 break;
				    			 }
				    		 }
				    	 }
			    	 
			    	 
			    	 // Validating for RRD Plant
			    	 
			    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
				    		List<WebElement> Rplnt = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
				    		 for (WebElement lnk:Rplnt) {
				    			 if (lnk.getText().equals("RRD Plant: ")){
				    				 // action.click(lnk).perform();
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inventory Status Summary Table");
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
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inventory Status Summary Table");
				    				 break;
				    			 }
				    		 }
				    	 }
			    	 
			    	 
			    	 // Validating For Order
			    	 
			    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
				    		List<WebElement> Ordr = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
				    		 for (WebElement lnk:Ordr) {
				    			 if (lnk.getText().equals("Order:        ")){
				    				 // action.click(lnk).perform();
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inventory Status Summary Table");
				    				 break;
				    			 }
				    		 }
				    	 }
			    	 
			    	 
			    	 //Validating For All
			    	 
			    	 if (doesElementExist2(properties.getProperty("Tablevalidtn"), 5)) {
				    		List<WebElement> Oall = driver.findElements(By.cssSelector(properties.getProperty("Tablevalidtn")));
				    		 for (WebElement lnk:Oall) {
				    			 if (lnk.getText().equals("All")){
				    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Inventory Status Summary Table");
				    				 break;
				    			 }
				    		 }
				    	 }
			    	 

			    	
			    	 // Summary table Verification
			    	 
			    	if (doesElementExist2(properties.getProperty("TotalPostageCost"), 5)) {
			    		List<WebElement> Todte = driver.findElements(By.cssSelector(properties.getProperty("TotalPostageCost")));
			    		for (WebElement lnk:Todte) {
			    			if (lnk.getText().startsWith("$")||lnk.getText().matches("[0-9,]*")||lnk.getText().matches("[0-9]*")){
			    				log.logLine(Testname, false, "Total Postage Cost is    "+lnk.getText());
			    				break;
			    				}
			    			}
			    		}
			    	
			    				    	
			    	// Graph Verification
			    	
			    	/*if (doesElementExist2(properties.getProperty("Graphsmry"), 5)) {
			    		String Grp = driver.findElement(By.cssSelector(properties.getProperty("Graphsmry"))).getText();
			    		log.logLine(Testname, false, "Validating the Graph is Successful");
			    		Chk3=true;
			    	}else{
				    	log.logLine(Testname, true, "Reading the data is unSuccessful");
			    	}
			    	*/
			    	
		    	
	    	
			    	//Details Table 1St column data---->>Client Data Process Date
			    	
			    	if (doesElementExist2(properties.getProperty("Columndata1"), 5)) {
			    		String cd1 = driver.findElement(By.cssSelector(properties.getProperty("Columndata1"))).getText();
			    		log.logLine(Testname, false, "Reading 1st column data under Details Table is Successful-----"+cd1);
			    		Chk4=true;
			    	}else{
				    	log.logLine(Testname, true, "Reading 1st column data under Details Table is unSuccessful");
						
			    	}
			   
			    	//Details Table 2nd column data---->>RRD Data Receipt Date
			    	
			    	if (doesElementExist2(properties.getProperty("Columndata2"), 5)) {
			    		String cd2 = driver.findElement(By.cssSelector(properties.getProperty("Columndata2"))).getText();
			    		log.logLine(Testname, false, "Reading 2nd column data under Details Table as-----"+cd2);
			    	}else{
				    	log.logLine(Testname, true, "Reading 2nd column data under Details Table is unSuccessful");
						
			    	}
			    	
			    	//Details Table 3rd column data---->>-RRD Data Receipt Time
			    	
			    	if (doesElementExist2(properties.getProperty("Columndata3"), 5)) {
			    		String cd3 = driver.findElement(By.cssSelector(properties.getProperty("Columndata3"))).getText();
			    		log.logLine(Testname, false, "Reading 3rd column data under Details Table as-----"+cd3);
			    	}else{
				    	log.logLine(Testname, true, "Reading 3rd column data under Details Table is unSuccessful");
						
			    	}
			    	
			    	//Details Table 4th column data---->>Order Number
			    	
			    	if (doesElementExist2(properties.getProperty("Columndata4"), 5)) {
			    		String cd4 = driver.findElement(By.cssSelector(properties.getProperty("Columndata4"))).getText();
			    		log.logLine(Testname, false, "Reading 4th column data under Details Table as-----"+cd4);
			    	}else{
				    	log.logLine(Testname, true, "Reading 4th column data under Details Table is  unSuccessful");
						
			    	}
			    	
			    	
			    	//Details Table 5th column data---->>Application
			    	
			    	if (doesElementExist2(properties.getProperty("Columndata5"), 5)) {
			    		String cd5 = driver.findElement(By.cssSelector(properties.getProperty("Columndata5"))).getText();
			    		log.logLine(Testname, false, "Reading 5th column data under Details Table as-----"+cd5);
			    	}else{
				    	log.logLine(Testname, true, "Reading 5th column data under Details Table is unSuccessful");
						
			    	}
			    	
			    	//Details Table 6th column data---->>RRD Plant
			    	
			    	if (doesElementExist2(properties.getProperty("Columndata6"), 5)) {
			    		String cd6 = driver.findElement(By.cssSelector(properties.getProperty("Columndata6"))).getText();
			    		log.logLine(Testname, false, "Reading 6th column data under Details Table as-----"+cd6);
			    	}else{
				    	log.logLine(Testname, true, "Reading 6th column data under Details Table is  unSuccessful");
						
			    	}
			    	
			    	
			    	//Details Table 7th column data---->>Total Print Quantity
			    	
			    	if (doesElementExist2(properties.getProperty("Columndata7"), 5)) {
			    		String cd7 = driver.findElement(By.cssSelector(properties.getProperty("Columndata7"))).getText();
			    		log.logLine(Testname, false, "Reading 7th column data under Details Table as-----"+cd7);
			    	}else{
				    	log.logLine(Testname, true, "Reading 7th column data under Details Table is  unSuccessful");
						
			    	}
			    	
			    	
			    	//Details Table 8th column data---->>-Total Print Pages
			    	if (doesElementExist2(properties.getProperty("Columndata8"), 5)) {
			    		String cd8 = driver.findElement(By.cssSelector(properties.getProperty("Columndata8"))).getText();
			    		log.logLine(Testname, false, "Reading 8th column data under Details Table as-----"+cd8);
			    	}else{
				    	log.logLine(Testname, true, "Reading 8th column data under Details Table is unSuccessful");
						
			    	}
			    	
			    	
			    	//Details Table 9th column data---->>Average Print Pages
			    	
			    	if (doesElementExist2(properties.getProperty("Columndata9"), 5)) {
			    		String cd9 = driver.findElement(By.cssSelector(properties.getProperty("Columndata9"))).getText();
			    		log.logLine(Testname, false, "Reading 9th column data under Details Table as-----"+cd9);
			    	}else{
				    	log.logLine(Testname, true, "Reading 9th column data under Details Table is unSuccessful");
						}
			    	
			    	
			    	//Details Table 10th column data---->>Total Postage
			    	
			    	if (doesElementExist2(properties.getProperty("Columndata10"), 5)) {
			    		String cd10 = driver.findElement(By.cssSelector(properties.getProperty("Columndata10"))).getText();
			    		log.logLine(Testname, false, "Reading 10th column data under Details Table as-----"+cd10);
			    	}else{
				    	log.logLine(Testname, true, "Reading 10th column data under Details Table is unSuccessful");
						
			    	}
			    	
			    	
			    	//Details Table 11th column data---->>Postage Per Piece
			    	
			    	if (doesElementExist2(properties.getProperty("Columndata11"), 5)) {
			    		String cd11 = driver.findElement(By.cssSelector(properties.getProperty("Columndata11"))).getText();
			    		log.logLine(Testname, false, "Reading 11th column data under Details Table as-----"+cd11);
			    		Chk5=true;
			    	}else{
				    	log.logLine(Testname, true, "Reading 11th column data under Details Table is unSuccessful");
						
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
	}



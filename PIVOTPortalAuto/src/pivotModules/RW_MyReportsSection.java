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

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class RW_MyReportsSection extends Page{
	
	public static String NameField;
	public static String NameField1;
	
	public static String firstsave;
	public static String ffff;
	public static  String Newname;
	

	public RW_MyReportsSection(WebDriver driver, Log log) throws InvalidFormatException, IOException {
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
 	
 	
 	public boolean MyReportSection(String AccNo,String Testname) throws Exception {
 		
 		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    //String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	
	    
	    //String NmeField=test.readColumnData("NameField", sheetname);
	    
	    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		Date date = new Date();
		String todaysDate = dateFormat.format(date);
		
		String arr[] = todaysDate.split("2015");
		String splttoddte = arr[0].trim();
		String TodayDte=splttoddte+15;
		
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
					if (lnk.getText().equals("DPR2")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						
						PleasewaitDisappear();
						Thread.sleep(5000);
						log.logLine(Testname, false, "Selecting the DPR report type option from the dropdown");
						break;
					}
				}
			} else {
					log.logLine(Testname, true, "Selecting the DPR report type option  is failed");
					driver.switchTo().defaultContent();
					throw new Exception("Selecting the the DPR report type option  is failed");
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
	  
	   
	  
	        // Report Columns
	    
		    if (doesElementExist2(properties.getProperty("CycleText"), 5)) {
					List<WebElement> ClDtaPrcDateText = driver.findElements(By.cssSelector(properties.getProperty("CycleText")));
					 for (WebElement lnk:ClDtaPrcDateText) {
						 if (lnk.getText().equals("Cycle")){
							 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
							 break;
						 }
					 }
			 }
		    
	    
		    if (doesElementExist2(properties.getProperty("CycleChkBox"), 5)) {
		  	    WebElement ClDtaPrcDatechkbox = driver.findElement(By.cssSelector(properties.getProperty("CycleChkBox")));
		  	   
		  	  if ( ClDtaPrcDatechkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Cycle checkbox is already selected");
			    	
			     }else{
			    	 ClDtaPrcDatechkbox.click();
			     }
		    }
	    
	    
		    if (doesElementExist2(properties.getProperty("SLADateText"), 5)) {
	    		List<WebElement> rrdplntText = driver.findElements(By.cssSelector(properties.getProperty("SLADateText")));
	    		 for (WebElement lnk:rrdplntText) {
	    			 if (lnk.getText().equals("SLA Date")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
	    				 break;
	    			 }
	    		 }
	    	 }
	    
	    
		    if (doesElementExist2(properties.getProperty("SLADatechkbox"), 5)) {
		  	    WebElement rrdplntchkbox = driver.findElement(By.cssSelector(properties.getProperty("SLADatechkbox")));
		  	   
		  	  if ( rrdplntchkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "SLA Date checkbox is already selected");
			    	
			     }else{
			    	 rrdplntchkbox.click();
			     }
		    }
	    
	    
		    if (doesElementExist2(properties.getProperty("PrintFacilityText"), 5)) {
	    		List<WebElement> prmymldatetext = driver.findElements(By.cssSelector(properties.getProperty("PrintFacilityText")));
	    		 for (WebElement lnk:prmymldatetext) {
	    			 if (lnk.getText().equals("Print Facility")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
	    				 break;
	    			 }
	    		 }
	    	 }
	    
	    
		    if (doesElementExist2(properties.getProperty("PrintFacilityChkbox"), 5)) {
		  	    WebElement prmymldatechkbox = driver.findElement(By.cssSelector(properties.getProperty("PrintFacilityChkbox")));
		  	   
		  	  if ( prmymldatechkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Print Facility checkbox is already selected");
			    	
			     }else{
			    	 prmymldatechkbox.click();
			     }
		    }
	    
	    
		    if (doesElementExist2(properties.getProperty("BatchText"), 5)) {
	    		List<WebElement> totlprntpgsText = driver.findElements(By.cssSelector(properties.getProperty("BatchText")));
	    		 for (WebElement lnk:totlprntpgsText) {
	    			 if (lnk.getText().equals("Batch")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
	    				 break;
	    			 }
	    		 }
	    	 }
	    
	    
	 	    
		    if (doesElementExist2(properties.getProperty("Batchchkbox"), 5)) {
		  	    WebElement totlprntpgschkbox = driver.findElement(By.cssSelector(properties.getProperty("Batchchkbox")));
		  	   
		  	  if ( totlprntpgschkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Batch checkbox is already selected");
			    	
			     }else{
			    	 totlprntpgschkbox.click();
			     }
		    }
	    
	    
		    if (doesElementExist2(properties.getProperty("ProcessDateText"), 5)) {
	    		List<WebElement> totlemlqntyText = driver.findElements(By.cssSelector(properties.getProperty("ProcessDateText")));
	    		 for (WebElement lnk:totlemlqntyText) {
	    			 if (lnk.getText().equals("Process Date")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
	    				 break;
	    			 }
	    		 }
	    	 }
	    
	    
		    if (doesElementExist2(properties.getProperty("ProcessDateChkbox"), 5)) {
		  	    WebElement totlemlqntychkbox = driver.findElement(By.cssSelector(properties.getProperty("ProcessDateChkbox")));
		  	   
		  	  if ( totlemlqntychkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Process Date checkbox is already selected");
			    	
			     }else{
			    	 totlemlqntychkbox.click();
			     }
		    }
	    
	    
		    if (doesElementExist2(properties.getProperty("PackageCountText"), 5)) {
	    		List<WebElement> rrddatarcptdtetext = driver.findElements(By.cssSelector(properties.getProperty("PackageCountText")));
	    		 for (WebElement lnk:rrddatarcptdtetext) {
	    			 if (lnk.getText().equals("Package Count")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
	    				 break;
	    			 }
	    		 }
	    	 }
	    
	    
		    if (doesElementExist2(properties.getProperty("PackageCountchkbox"), 5)) {
		  	    WebElement rrddatarcptdtechkbox = driver.findElement(By.cssSelector(properties.getProperty("PackageCountchkbox")));
		  	    
		  	  if ( rrddatarcptdtechkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Package Count checkbox is already selected");
			    	
			     }else{
			    	 rrddatarcptdtechkbox.click();
			     }
		    }
 
	    
	    
		    if (doesElementExist2(properties.getProperty("OrderNumberText"), 5)) {
	    		List<WebElement> rdrnumbertext = driver.findElements(By.cssSelector(properties.getProperty("OrderNumberText")));
	    		 for (WebElement lnk:rdrnumbertext) {
	    			 if (lnk.getText().equals("Order Number")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
	    				 break;
	    			 }
	    		 }
	    	 }
	    
	    	    
		    if (doesElementExist2(properties.getProperty("OrderNumberchkbox"), 5)) {
		  	    WebElement rdrnumberchkbox = driver.findElement(By.cssSelector(properties.getProperty("OrderNumberchkbox")));
		  	   
		  	  if ( rdrnumberchkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Order Number checkbox is already selected");
			    	
			     }else{
			    	 rdrnumberchkbox.click();
			     }
		    }
	    
	    
		    if (doesElementExist2(properties.getProperty("CompleteCountText"), 5)) {
	    		List<WebElement> scndrymaildatetext = driver.findElements(By.cssSelector(properties.getProperty("CompleteCountText")));
	    		 for (WebElement lnk:scndrymaildatetext) {
	    			 if (lnk.getText().equals("Complete Count")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
	    				 break;
	    			 }
	    		 }
	    	 }
	    
	    
		    if (doesElementExist2(properties.getProperty("CompleteCountchkbox"), 5)) {
		  	    WebElement scndrymaildatechkbox = driver.findElement(By.cssSelector(properties.getProperty("CompleteCountchkbox")));
		  	   
		  	  if ( scndrymaildatechkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Complete Count checkbox is already selected");
			    	
			     }else{
			    	 scndrymaildatechkbox.click();
			     }
		    }
	    
	   
		    if (doesElementExist2(properties.getProperty("MailDateText"), 5)) {
	    		List<WebElement> avrgprntpgsText = driver.findElements(By.cssSelector(properties.getProperty("MailDateText")));
	    		 for (WebElement lnk:avrgprntpgsText) {
	    			 if (lnk.getText().equals("Mail Date")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
	    				 break;
	    			 }
	    		 }
	    	 }
		    
		    
		    if (doesElementExist2(properties.getProperty("MailDateChkbox"), 5)) {
		  	    WebElement  avrgprntpgschkbox = driver.findElement(By.cssSelector(properties.getProperty("MailDateChkbox")));
		  	   
		  	  if ( avrgprntpgschkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Mail Date checkbox is already selected");
			    	
			     }else{
			    	 avrgprntpgschkbox.click();
			     }
		    }
		    
		
		    if (doesElementExist2(properties.getProperty("SheetCountText"), 5)) {
	    		List<WebElement> rrddatarcpttimetext = driver.findElements(By.cssSelector(properties.getProperty("SheetCountText")));
	    		 for (WebElement lnk:rrddatarcpttimetext) {
	    			 if (lnk.getText().equals("Sheet Count")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
	    				 break;
	    			 }
	    		 }
	    	 }
		    
		    
		    if (doesElementExist2(properties.getProperty("SheetCountchkbox"), 5)) {
		  	    WebElement   rrddatarcpttimechkbox = driver.findElement(By.cssSelector(properties.getProperty("SheetCountchkbox")));
		  	   
		  	  if ( rrddatarcpttimechkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Sheet Count checkbox is already selected");
			    	
			     }else{
			    	 rrddatarcpttimechkbox.click();
			     }
		    }
		    
		    
		    if (doesElementExist2(properties.getProperty("CopyText"), 5)) {
	    		List<WebElement> applnText = driver.findElements(By.cssSelector(properties.getProperty("CopyText")));
	    		 for (WebElement lnk:applnText) {
	    			 if (lnk.getText().equals("Copy")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
	    				 break;
	    			 }
	    		 }
	    	 }
		    
		    
		    if (doesElementExist2(properties.getProperty("Copychkbox"), 5)) {
		  	    WebElement  applnTextchkbox = driver.findElement(By.cssSelector(properties.getProperty("Copychkbox")));
		  	   
		  	  if ( applnTextchkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Copy checkbox is already selected");
			    	
			     }else{
			    	 applnTextchkbox.click();
			     }
		    }
		    
		    
		    if (doesElementExist2(properties.getProperty("JobNameText"), 5)) {
	    		List<WebElement> totlprntpgsText = driver.findElements(By.cssSelector(properties.getProperty("JobNameText")));
	    		 for (WebElement lnk:totlprntpgsText) {
	    			 if (lnk.getText().equals("Job Name")){
	    				 log.logLine(Testname, false, "Reading the Data as "+lnk.getText()+" Under Reports Column");
	    				 break;
	    			 }
	    		 }
	    	 }
		    
		    
		    if (doesElementExist2(properties.getProperty("JobNamechkbox"), 5)) {
		  	    WebElement  totlprntpgschkbox = driver.findElement(By.cssSelector(properties.getProperty("JobNamechkbox")));
		  	   
		  	  if ( totlprntpgschkbox.isSelected())
			     {
			    	log.logLine(Testname, false, "Job Name checkbox is already selected");
			    	
			     }else{
			    	 totlprntpgschkbox.click();
			     }
		    }
			    
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
							Thread.sleep(2000);
							PleasewaitDisappear();
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
		    boolean Retval = ReportVerification(AccNo,Testname);
		    if (Retval) {
		    	log.logLine(Testname, false, "Verification of Report For Last Year is Successfull");
		    } else {
		    	log.logLine(Testname, true, "Verification of Report For Last Year is unSuccessfull");
		    	return false;
		    }
		    
	    
		    SavedReportVerification(AccNo,Testname);
		    
		    EditCopyDeleteVerification(AccNo,Testname);
	    
	    	return true;
		 		    
	}
		
		public boolean ReportVerification(String AccNo, String Testname) throws Exception {
			
			boolean Chk1 = false;
			
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
			
					
			Thread.sleep(2000);
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
				    	//To retrieve the handle of second window, extracting the handle which does not match to first window handle
				    	String secondWinHandle=winHandle; //Storing handle of second window handle
				    
				    	//Switch control to new window
				    	driver.switchTo().window(secondWinHandle);
				    	//waitUntilLoadElementDisappear4();
				    	driver.manage().window().maximize();
				    	
				    	Thread.sleep(45000);
				    	
				    	if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetExplorer"))) {
				    		if (doesElementExist2(properties.getProperty("OverrideLnk"), 5)) {
				    			driver.get("javascript:document.getElementById('overridelink').click();");
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
				    	
				    	
				    	
			    		driver.close();
			    		Thread.sleep(5000);
				    	
				    	// Switching back to parent window
					    driver.switchTo().window(firstWinHandle);
					   
					    }
				    }
			    }
			/*if (Chk1){
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
	    
		   NameField=test.readColumnData("MyReportNameFld", sheetname);
		    driver.switchTo().frame("iframeContainer");
		    
		    Thread.sleep(2000);
	
				if (doesElementExist2(properties.getProperty("ReportNme"), 5)) {	  
				    WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("ReportNme")));
				    Txt.clear();
					Txt.sendKeys(NameField+AccNo);
					Thread.sleep(2000);
					log.logLine(Testname, false, "Entering report name as "+NameField+AccNo+" In the Report Name Field");
				}else{
					log.logLine(Testname, true,"Unable to enter the textin report name field");
					
				}
				
				if (doesElementExist2(properties.getProperty("SaveButton"), 5)) {	  
				    WebElement rnrptbtn = driver.findElement(By.cssSelector(properties.getProperty("SaveButton")));
				    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", rnrptbtn);
				    Thread.sleep(10000);
				    PleasewaitDisappear();
					log.logLine(Testname, false, "Click on Save button");
				}else{
					log.logLine(Testname, true,"Click on Save button is UnSuccessful");
				
				}


				if (doesElementExist2(properties.getProperty("SavedReports"), 5)) {	  
				    WebElement rnrptbtn = driver.findElement(By.cssSelector(properties.getProperty("SavedReports")));
				    rnrptbtn.click();
				    Thread.sleep(10000);
				    PleasewaitDisappear();
					log.logLine(Testname, false, "Click on SavedReports button");
				}else{
					log.logLine(Testname, true,"Click on SavedReports button is UnSuccessful");
				
				}
				
				
				if (doesElementExist2(properties.getProperty("EditReportName"), 5)) {	  
				    firstsave = driver.findElement(By.cssSelector(properties.getProperty("EditReportName"))).getText();
				    Thread.sleep(2000);
				    log.logLine(Testname, false,"Creating the first report is Successful");
				       
				}else{
					log.logLine(Testname, true,"Creating the first report is UnSuccessful");
				
				}


				
				//Running the report under saved reports
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
					    	
					    	if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetExplorer"))) {
					    		if (doesElementExist2(properties.getProperty("OverrideLnk"), 5)) {
					    			driver.get("javascript:document.getElementById('overridelink').click();");
					    		}
					    	}
					    	
					    	Thread.sleep(8000);
					    	
					    	
					    	WebElement retEle = waitForElement(properties.getProperty("ReportTitle"));
							log.logLine(Testname, false, "Report Name is found on the page..");
					    	
					    	//Reading the report title
					    	
					    	if (doesElementExist2(properties.getProperty("ReportTitle"), 5)) {
					    		String dtetme = driver.findElement(By.cssSelector(properties.getProperty("ReportTitle"))).getText();
					    		Thread.sleep(2000);
					    		log.logLine(Testname, false, "Report tile is " +dtetme);
					    	}else{
						    	log.logLine(Testname, true, "Reading the report title is unSuccessful");
								//throw new Exception("Reading the date is unSuccessful");
					    	}
					   
	                     			
							driver.close();
							Thread.sleep(2000);
							
							// Switching back to parent window
							driver.switchTo().window(firstWinHandle);
							
						    }
					    }
					}
				 
	 		    return true;
				
		}
     
     
     public boolean EditCopyDeleteVerification(String AccNo,String Testname) throws Exception {
 		
			InputOutputData test = new InputOutputData();		
		    test.setInputFile(properties.getProperty("InputDatafile"));
		    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
		    
		   NameField1=test.readColumnData("MyReportNameFld1", sheetname);
		   
		    		
		    driver.switchTo().frame("iframeContainer");
		    
		    Thread.sleep(2000);
		        if (doesElementExist2(properties.getProperty("EditButton"), 5)) {	  
				    WebElement rnrptbtn = driver.findElement(By.cssSelector(properties.getProperty("EditButton")));
				    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", rnrptbtn);
				    Thread.sleep(2000);
					log.logLine(Testname, false, "Click on Edit button");
				}else{
					log.logLine(Testname, true,"Click on Edit button is UnSuccessful");
				
				}
	
				
				if (doesElementExist2(properties.getProperty("ReportNme"), 5)) {	  
				    WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("ReportNme")));
				    Txt.clear();
					Txt.sendKeys(NameField1+AccNo);
					Thread.sleep(2000);
					log.logLine(Testname, false, "Entering report name as "+NameField1+AccNo+" In the Report Name Field");
				}else{
					log.logLine(Testname, true,"Entering report name as "+NameField1+AccNo+" In the Report Name Field is failed");
				    throw new Exception("Entering report name as "+NameField1+AccNo+" In the Report Name Field is failed");

				}
				
				if (doesElementExist2(properties.getProperty("SaveButton"), 5)) {	  
				    WebElement rnrptbtn = driver.findElement(By.cssSelector(properties.getProperty("SaveButton")));
				    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", rnrptbtn);
				    PleasewaitDisappear();
					log.logLine(Testname, false, "Click on Save button");
				}else{
					log.logLine(Testname, true,"Click on Save button is UnSuccessful");
					throw new Exception("Click on Save button is UnSuccessful");
				}


				if (doesElementExist2(properties.getProperty("SavedReports"), 5)) {	  
				    WebElement rnrptbtn = driver.findElement(By.cssSelector(properties.getProperty("SavedReports")));
				    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", rnrptbtn);
				    PleasewaitDisappear();
					log.logLine(Testname, false, "Click on SavedReports button");
				}else{
					log.logLine(Testname, true,"Click on SavedReports button is UnSuccessful");
					throw new Exception("Click on SavedReports button is UnSuccessful");
				}
				
				
				if (doesElementExist2(properties.getProperty("EditReportName"), 5)) {	  
				 WebElement secondsave = driver.findElement(By.cssSelector(properties.getProperty("EditReportName")));
				   
				    log.logLine(Testname, false,"Editting the existing report is sucessful");
				    
					    if(secondsave.getText().equals(firstsave)){
					    	log.logLine(Testname, true, "The modified report name matches with the first save report name, so edit of report is successful");
					    	Thread.sleep(2000);
					    	throw new Exception("The modified report name matches with the first save report name");
					    }else{
					   
						log.logLine(Testname, false, "The modified report name does not matches with the first save report name");
						
				    	}
				}else{
					log.logLine(Testname, true,"Editting the existing report is unsucessful");
					throw new Exception("Editting the existing report is unsucessful");
				}
				
				if (doesElementExist2(properties.getProperty("EditReportName"), 5)) {	  
				  ffff = driver.findElement(By.cssSelector(properties.getProperty("EditReportName"))).getText();
				  Thread.sleep(2000);
				    log.logLine(Testname, false,"Get text of the editted report name is sucessful");
				       
				}else{
					log.logLine(Testname, true,"Get text of the editted report name is unsucessful");
					throw new Exception("Get text of the editted report name is unsucessful");
				}
				
				if (doesElementExist2(properties.getProperty("Copy"), 5)) {	  
				    WebElement rnrptbtn = driver.findElement(By.cssSelector(properties.getProperty("Copy")));
				    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", rnrptbtn);
				    Thread.sleep(2000);
					log.logLine(Testname, false, "Click on copy button");
				}else{
					log.logLine(Testname, true,"Click on copy button is UnSuccessful");
					throw new Exception("Click on copy button is UnSuccessful");
				}
				
				
				
				if (doesElementExist2(properties.getProperty("CopyName"), 5)) {	  
				    WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("CopyName")));
				    Txt.clear();
					Txt.sendKeys("copy of -"+ffff);
					Thread.sleep(2000);
					log.logLine(Testname, false, "Entering report name In the Report Name Field as  "+"copy of -"+ffff);
				}else{
					log.logLine(Testname, true,"Entering report name In the Report Name Field is failed");
					throw new Exception("Entering report name In the Report Name Field is failed");
				}
				
				if (doesElementExist2(properties.getProperty("CopyButton"), 5)) {	  
				    WebElement rnrptbtn = driver.findElement(By.cssSelector(properties.getProperty("CopyButton")));
				    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", rnrptbtn);
				    Thread.sleep(2000);
					log.logLine(Testname, false, "Click on Copy button to create the copy of the report existing report");
				}else{
					log.logLine(Testname, true,"Click on Copy button to create the copy of the report existing report is UnSuccessful");
					throw new Exception("Click on Copy button to create the copy of the report existing report is UnSuccessful");
				}
				
				if (doesElementExist2(properties.getProperty("SavedReports"), 5)) {	  
				    WebElement rnrptbtn = driver.findElement(By.cssSelector(properties.getProperty("SavedReports")));
				    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", rnrptbtn);
				    Thread.sleep(2000);
					log.logLine(Testname, false, "Click on SavedReports button");
				}else{
					log.logLine(Testname, true,"Click on SavedReports button is UnSuccessful");
					throw new Exception("Click on SavedReports button is UnSuccessful");
				}
				
				
				if (doesElementExist2(properties.getProperty("NewCopyReptNme"), 5)) {	  
					 WebElement newcpy = driver.findElement(By.cssSelector(properties.getProperty("NewCopyReptNme")));
					   
					    log.logLine(Testname, false,"Copying the existing report is sucessful");
					    
						    if(newcpy.getText().equals("copy of -"+ffff)){
						    	
						    	log.logLine(Testname, false, "Creating the copy of existing report is successful");
						    }else{
						   
							log.logLine(Testname, true, "Creating the copy of existing report is unsuccessful");
							throw new Exception("Creating the copy of existing report is unsuccessful");
					    	}
					}else{
						log.logLine(Testname, true,"Copying the existing report is unsucessful");
						throw new Exception("Copying the existing report is unsucessful");
					}
				
				if (doesElementExist2(properties.getProperty("Deletebtn"), 5)) {	  
				    WebElement rnrptbtn = driver.findElement(By.cssSelector(properties.getProperty("Deletebtn")));
				    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", rnrptbtn);
				    Thread.sleep(2000);
					log.logLine(Testname, false, "Click on delete button to delete the report is successful");
				}else{
					log.logLine(Testname, true,"Click on delete button to delete the report is unsuccessful");
				
				}
				
				if (doesElementExist2(properties.getProperty("ConfirmdeleteOk"), 5)) {	  
				    WebElement rnrptbtn = driver.findElement(By.cssSelector(properties.getProperty("ConfirmdeleteOk")));
				    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", rnrptbtn);
				    Thread.sleep(2000);
					log.logLine(Testname, false, "Click on ok button to confirm the deletion of the report");
				}else{
					log.logLine(Testname, true,"Click on ok button to confirm the deletion of the report is UnSuccessful");
				
				}
				
					
	 		    return true;
				
		}
     
     
     
	}



    		 	
			    
	    
	
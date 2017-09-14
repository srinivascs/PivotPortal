package pivotModules;
import java.io.IOException;
import java.util.List;

import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class ArchivesAdvanceSrch extends Page{
	
	public ArchivesAdvanceSrch(WebDriver driver, Log log) throws InvalidFormatException, IOException {
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
		
		// Switching back to parent window
	    driver.switchTo().window(SetWindow);		
				
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
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
	    
	  //Validation of Search Verbiage in Archive>Search Tab
	    String subTitle = "Search";
	    if(doesElementExist2(properties.getProperty("Search"),5)){
	    	String addText=driver.findElement(By.cssSelector(properties.getProperty("Search"))).getText();
	    	if(addText.equals(subTitle)){
	    		log.logLine(Testname, false, " The Validation of "+subTitle+" is sucessful");		
	    	}else{
	    		log.logLine(Testname, true, " The Validation of "+subTitle+" is not sucessful");	
	    	}
	    }
	    
	    return true;
 	}
 	
 	
 	public boolean AdvanceArchiveSearch(String AccNo, String Testname) throws Exception {
 		 
	 	InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	  
	    
	    //Fetching the Text data from sheet
	    String Textname1=test.readColumnData("CEDULAEQUAL", sheetname);
	    String Textname2=test.readColumnData("CEDULA-LESSTHAN", sheetname);
	    String Textname3=test.readColumnData("CEDULA-GREATERTHAN", sheetname);
	    String Textname4=test.readColumnData("FIRSTNAME", sheetname);	    
	    String Textname5=test.readColumnData("PLANNUMBER", sheetname);
	    String Textname6=test.readColumnData("STATEMENTNUMBER", sheetname);
	    String Textname7=test.readColumnData("PLANTYPE", sheetname);
	    String Textname8=test.readColumnData("LASTNAME", sheetname);
	    String Textname9=test.readColumnData("REPRINTADDRESS", sheetname);
	    String Textname10=test.readColumnData("PHONENUMBER", sheetname);
	    String Textname11=test.readColumnData("FINANCIALADVISOR", sheetname);
	    String Textname12=test.readColumnData("ZIPCODE", sheetname);
	    
	    
	    
	    Thread.sleep(5000);
	    driver.switchTo().frame("iframeContainer");	  
	    
	    //Validation of Archive Search Results Verbiage
	    String SearchRes = "Archive Search Results";
	    if(doesElementExist2(properties.getProperty("SearchRes"),5)){
	    	String addText1=driver.findElement(By.cssSelector(properties.getProperty("SearchRes"))).getText();
	    	String arr[] = addText1.split("Reprint Combine to PDF");
	    	String Final=arr[0].trim();
	    	
	    	if(SearchRes.equals(Final)){
	    		log.logLine(Testname, false, " The Validation of "+Final+" is sucessful");		
	    	}else{
	    		log.logLine(Testname, true, " The Validation of "+Final+" is not sucessful");	
	    	}
	    }
	   
	   
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

		 
		 //Click on Add button
		 if (doesElementExist2(properties.getProperty("Add"), 5)) {
			 WebElement btn = driver.findElement(By.cssSelector(properties.getProperty("Add")));
			 
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
			 log.logLine(Testname, false, "Additional search criteria1 is shown");			 
			 
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
			 log.logLine(Testname, false, "Additional search criteria2 is shown");
			 
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
			 log.logLine(Testname, false, "Additional search criteria3 is shown");
			 
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
			 log.logLine(Testname, false, "Additional search criteria4 is shown");
			 
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
			 log.logLine(Testname, false, "Additional search criteria5 is shown");
			 
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
			 log.logLine(Testname, false, "Additional search criteria6 is shown");
			 
		 } else {
			 log.logLine(Testname, true, "Additional search criteria field is not exist");
			 driver.switchTo().defaultContent();
			 throw new Exception("Additional search criteria field is not exist");
		 }	 
		 
		 
		 //Click on Cancel button
		 if (doesElementExist2(properties.getProperty("Cancel"), 5)) {
			 WebElement btn = driver.findElement(By.cssSelector(properties.getProperty("Cancel")));			 
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
			 log.logLine(Testname, false, "Additional search criteria1 is removed");		 
			 
		 } else {
			 log.logLine(Testname, true, "Additional search criteria Remove1 is not exist");
			 driver.switchTo().defaultContent();
			 throw new Exception("Additional search criteria Remove1 is not exist");
		 }	 
		 
		 if (doesElementExist2(properties.getProperty("Cancel"), 5)) {
			 WebElement btn = driver.findElement(By.cssSelector(properties.getProperty("Cancel")));			 
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
			 log.logLine(Testname, false, "Additional search criteria2 is removed");		 
			 
		 } else {
			 log.logLine(Testname, true, "Additional search criteria Remove2 is not exist");
			 driver.switchTo().defaultContent();
			 throw new Exception("Additional search criteria Remove2 is not exist");
		 }
		 
		 if (doesElementExist2(properties.getProperty("Cancel"), 5)) {
			 WebElement btn = driver.findElement(By.cssSelector(properties.getProperty("Cancel")));			 
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
			 log.logLine(Testname, false, "Additional search criteria3 is removed");		 
			 
		 } else {
			 log.logLine(Testname, true, "Additional search criteria Remove3 is not exist");
			 driver.switchTo().defaultContent();
			 throw new Exception("Additional search criteria Remove3 is not exist");
		 }
		 
		 if (doesElementExist2(properties.getProperty("Cancel"), 5)) {
			 WebElement btn = driver.findElement(By.cssSelector(properties.getProperty("Cancel")));			 
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
			 log.logLine(Testname, false, "Additional search criteria4 is removed");		 
			 
		 } else {
			 log.logLine(Testname, true, "Additional search criteria Remove4 is not exist");
			 driver.switchTo().defaultContent();
			 throw new Exception("Additional search criteria Remove4 is not exist");
		 }
		 
		 if (doesElementExist2(properties.getProperty("Cancel"), 5)) {
			 WebElement btn = driver.findElement(By.cssSelector(properties.getProperty("Cancel")));			 
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
			 log.logLine(Testname, false, "Additional search criteria5 is removed");		 
			 
		 } else {
			 log.logLine(Testname, true, "Additional search criteria Remove5 is not exist");
			 driver.switchTo().defaultContent();
			 throw new Exception("Additional search criteria Remove5 is not exist");
		 }
		 
		 if (doesElementExist2(properties.getProperty("Cancel"), 5)) {
			 WebElement btn = driver.findElement(By.cssSelector(properties.getProperty("Cancel")));			 
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
			 log.logLine(Testname, false, "Additional search criteria6 is removed");		 
			 
		 } else {
			 log.logLine(Testname, true, "Additional search criteria Remove6 is not exist");
			 driver.switchTo().defaultContent();
			 throw new Exception("Additional search criteria Remove6 is not exist");
		 }
		 
		 
		 
		 if (doesElementExist2(properties.getProperty("CancelbtnAdsrch"), 5)) {
			 WebElement btn = driver.findElement(By.cssSelector(properties.getProperty("CancelbtnAdsrch")));
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
			 log.logLine(Testname, false, "Click on Cancel button");
		 } else {
			 log.logLine(Testname, true, "Click on Cancel button is failed");
			 driver.switchTo().defaultContent();
			 throw new Exception("Click on Cancel button is failed");			  
		 }	 
		 
		 // Parameter Validation
		 
		 Thread.sleep(5000);
		 ParameterValidation(Testname);
		 
		 Thread.sleep(5000);
		 MultiAdvancedSrch(Testname, "CEDULA", "Equals", Textname1);		 
		 SearchResultsValidation(AccNo, Testname, Textname1);
		 
		 MultiAdvancedSrch(Testname, "CEDULA", "< (less than)", Textname2);		 
		 SearchResultsValidation(AccNo, Testname, Textname2);
			 
		 MultiAdvancedSrch(Testname, "CEDULA", "> (greater than)", Textname3);
		 SearchResultsValidation(AccNo, Testname, Textname3);
		 
		 MultiAdvancedSrch(Testname, "First Name", "Equals", Textname4);
		 SearchResultsValidation(AccNo, Testname, Textname4);
		 
		 MultiAdvancedSrch(Testname, "Plan #", "Equals", Textname5);
		 SearchResultsValidation(AccNo, Testname, Textname5);
		 
		 MultiAdvancedSrch(Testname, "Statement #", "Equals", Textname6);
		 SearchResultsValidation(AccNo, Testname, Textname6);
		 
		 MultiAdvancedSrch(Testname, "Plan Type", "Equals", Textname7);
		 SearchResultsValidation(AccNo, Testname, Textname7);
		 
		 MultiAdvancedSrch(Testname, "Last Name", "Starts with (wildcard)", Textname8);
		 SearchResultsValidation(AccNo, Testname, Textname8);
		 		 
		 MultiAdvancedSrch(Testname, "Reprint Address", "Equals", Textname9);
		 SearchResultsValidation(AccNo, Testname, Textname9);
		 
		 MultiAdvancedSrch(Testname, "Phone", "Equals", Textname10);
		 SearchResultsValidation(AccNo, Testname, Textname10);
		 
		 MultiAdvancedSrch(Testname, "Financial Advisor", "Equals", Textname11);
		 SearchResultsValidation(AccNo, Testname, Textname11);
		 
		 MultiAdvancedSrch(Testname, "Zip Code", "Equals", Textname12);
		 SearchResultsValidation(AccNo, Testname, Textname12);
		 
		 driver.switchTo().defaultContent();
		 return true;
	}
 	
 	
 	
 	public boolean ParameterValidation(String Testname) throws Exception { 		
 	
 		Thread.sleep(1000);
 		if (doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5)) {
 			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
 			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
 			Thread.sleep(2000);
 			log.logLine(Testname, false, "Click on Advance Search is successfull");
 		} else {
 			log.logLine(Testname, true, "Click on Advance Search is failed");
 			driver.switchTo().defaultContent();
 			throw new Exception("Click on Advance Search is failed");
 		}



 		Actions action=new Actions(driver);
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
 						log.logLine(Testname, false, "Selecting the Field option  "+lnk.getText() +" from the dropdown");
 						break;
 					}						
 				}
 			} else {				
 				log.logLine(Testname, true, "Selecting the field option  is failed");
 				driver.switchTo().defaultContent();
 				throw new Exception("Selecting the field option is failed");				
 			}		

 		}else {
 			log.logLine(Testname, true, "Clicking on the Field is failed");
 			driver.switchTo().defaultContent();
 			throw new Exception("Clicking on the Field is failed");
 		}


 		Thread.sleep(5000);
 		if (doesElementExist2(properties.getProperty("Add"), 5)) {
 			WebElement btn = driver.findElement(By.cssSelector(properties.getProperty("Add")));
 			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
 			Thread.sleep(2000);
 			log.logLine(Testname, false, "Additional search criteria1 is shown");	
 		}

 		if (doesElementExist(properties.getProperty("Fieldbutton1"), 5)) {	    
 			WebElement optr = driver.findElement(By.xpath(properties.getProperty("Fieldbutton1")));
 			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
 			Thread.sleep(1000);

 			log.logLine(Testname, false, "Clicking on Seacrh criteria Field drop down list in advance search");

 			if (doesElementExist2(properties.getProperty("SelFieltOpt1"), 5)) {

 				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelFieltOpt1")));
 				for (WebElement lnk:selopts) {
 					if (lnk.getText().equals("CEDULA")) {
 						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
 						log.logLine(Testname, false, "Selecting the Field option  "+lnk.getText() +" from the dropdown");
 						break;
 					}						
 				}
 			} else {				
 				log.logLine(Testname, true, "Selecting the field option  is failed");
 				driver.switchTo().defaultContent();
 				throw new Exception("Selecting the field option is failed");				
 			}		

 		}else {
 			log.logLine(Testname, true, "Clicking on the Field is failed");
 			driver.switchTo().defaultContent();
 			throw new Exception("Clicking on the Field is failed");
 		}

 		Thread.sleep(5000);
 		if (doesElementExist2(properties.getProperty("Alertmsg"), 5)) {	    
 			String msg = driver.findElement(By.cssSelector(properties.getProperty("Alertmsg"))).getText();		   
 			log.logLine(Testname, false, "Reading the pop up message as "+ msg);
 		} else {
 			log.logLine(Testname, true, "Reading the pop up message is failed");

 		}

 		Thread.sleep(2000);
 		if (doesElementExist2(properties.getProperty("Alertbtn"), 5)) {	    
 			WebElement clkadv = driver.findElement(By.cssSelector(properties.getProperty("Alertbtn")));		   
 			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);		     
 			Thread.sleep(5000);
 			log.logLine(Testname, false, "Click ok button for pop up message");
 		} else {
 			log.logLine(Testname, true, "Click ok button for pop up message is failed");

 		}

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

 		if (doesElementExist2(properties.getProperty("CancelbtnAdsrch"), 5)) {
 			WebElement btn = driver.findElement(By.cssSelector(properties.getProperty("CancelbtnAdsrch")));
 			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
 			log.logLine(Testname, false, "Click on Cancel button");
 		} else {
 			log.logLine(Testname, true, "Click on Cancel button is failed");
 			driver.switchTo().defaultContent();
 			throw new Exception("Click on Cancel button is failed");			  
 		}	 


 		return true;
 	}
 	
 	 	
 	public boolean SearchResultsValidation(String AccNo, String Testname, String SearchCriteria) throws Exception { 		
 		
 		log.logLine(Testname, false, "Verification and presence of the search criteria text in simple search with the result grid values starts here...."); 		
 		WebElement Webtable=driver.findElement(By.id("archive-search-grid")); // Replace TableID with Actual Table ID or Xpath		

 		List<WebElement> TotalRowCount= Webtable.findElements(By.xpath("//div[@id='archive-search-grid']/table/tbody/tr")); 		
        log.logLine(Testname, false, "Total No. of Rows in the WebTable which contains search criteria data : " +TotalRowCount.size());
 				
 		//System.out.println("No. of Rows in the WebTable: "+TotalRowCount.size());
 		// Now we will Iterate the Table and print the Values   
 		int RowIndex=1; 		
 		for(WebElement rowElement:TotalRowCount) {
 			
 		      List<WebElement> TotalColumnCount=rowElement.findElements(By.xpath("td"));
 		      //log.logLine(Testname, false, "No. of columns in the WebTable:" +TotalColumnCount.size());
 		      int ColumnIndex=0;
 		      for(WebElement colElement:TotalColumnCount){   	  	    	   		    	  
 		    	  
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
 	
 	
 	public void MultiAdvancedSrch(String Testname, String Field, String Operator, String TData) throws Exception {
 		
 		Thread.sleep(1000);
		if (doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			Thread.sleep(2000);
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
 
		 //Clicking on Date Checkbox
		 if (doesElementExist2(properties.getProperty("Alldts"), 5)) {
			 WebElement btn = driver.findElement(By.cssSelector(properties.getProperty("Alldts")));
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
			 log.logLine(Testname, false, "Click on All Date Checkbox is Successfull");
		 } else {
			 log.logLine(Testname, true, "Click on All Date Checkbox is Failed");
			 driver.switchTo().defaultContent();
			 throw new Exception("Click on All Date Checkbox is Failed");
		 }
		 	 
		 
		 Actions action=new Actions(driver);
		 if (doesElementExist2(properties.getProperty("Fieldbutton"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("Fieldbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(1000);
			
			log.logLine(Testname, false, "Clicking on Seacrh criteria Field drop down list in advance search");
			
			if (doesElementExist2(properties.getProperty("SelFieltOpt"), 5)) {
				
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelFieltOpt")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(Field)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						log.logLine(Testname, false, "Selecting the Field option  "+Field +" from the dropdown");
						break;
					}						
				}
			} else {				
				log.logLine(Testname, true, "Selecting the field option "+Field+" is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Selecting the field option "+Field+" is failed");				
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
					if (lnk.getText().equals(Operator)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(500);
						log.logLine(Testname, false, "Selecting the operator option  "+Operator +" from the dropdown");
						break;
					}						
				}
			} else {				
				log.logLine(Testname, true, "Selecting the operator option "+Operator+" is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Selecting the operator option "+Operator+" is failed");				
			}		
		}else {				
			log.logLine(Testname, true, "Clicking on the operator is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on the operator is failed");				
		}
		 
		
		 
		Thread.sleep(1000);
		if (doesElementExist2(properties.getProperty("Txtfld"), 5)) {	  
			WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("Txtfld")));
			Txt.sendKeys(TData);
			log.logLine(Testname, false, "Entered Value in text field is:" +TData);			
		}else {
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
		
		if (doesElementExist(properties.getProperty("Items"), 5)) {	    
			String val = driver.findElement(By.xpath(".//*[@id='archive-search-grid']/div/span[2]")).getText();
			String arr[] = val.split("of");
			String actval = arr[1].trim();
			log.logLine(Testname, false, "The total number of documents displayed are:" +actval);
		}
			
	}
 	
 	
}


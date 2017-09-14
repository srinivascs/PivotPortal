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

import org.openqa.selenium.Alert;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class eDeliveryAcknowledge extends Page{


	public eDeliveryAcknowledge(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	} 
	@Override
	protected void load() {}
	@Override

	protected void isLoaded() throws Error {}

	Actions action = new Actions(driver);
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
	Date date = new Date();
	String todaysDate = dateFormat.format(date);


	public static String stmtnum;
	public static String Editstmtnum;
	public static String Beforeupdte;
	public static String Afterupdte;

	//public static String Text="01213";
	public static String SearchText;



	public boolean ClientAppSel(String AccNo, String Testname) throws Exception {


		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile")); 		
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();


		String ClntName = test.readColumnData("ClientName", sheetname);	  
		String AppName = test.readColumnData("ApplicationName", sheetname);
		String AckText = test.readColumnData("DocumentText", sheetname);

		Thread.sleep(1000);
		driver.switchTo().defaultContent();


		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");
			//throw new Exception("Clicking on Cancel button in Client/App popup is failed");
		}



		Thread.sleep(10000);
		Actions builder = new Actions(driver);                
		WebElement mnuElement = driver.findElement(By.xpath(properties.getProperty("AdminMenu")));        
		if (doesElementExist(properties.getProperty("AdminMenu"), 10)) {                          
			// Move cursor to the Main Menu Element  
			builder.moveToElement(mnuElement).perform();                
			// Clicking on the Hidden SubMenu  
			Thread.sleep(10000);
			if (doesElementExist2(properties.getProperty("Adminlnk"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Adminlnk")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().contains("HA Admin")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Clicking on Legacy Admin..");                
						break;
					}                                
				}

			} else {
				log.logLine(Testname, true, "Clicking on Legacy Admin.. is failed");
				throw new Exception("Clicking on Legacy Admin.. is failed");                        
			}
		}

		Thread.sleep(20000);
		Set<String> handles = driver.getWindowHandles();
		String firstWinHandle = driver.getWindowHandle(); 
		handles.remove(firstWinHandle);

		boolean browserexist = handles.iterator().hasNext();
		if (browserexist) {
			String winHandle=handles.iterator().next();
			if (winHandle!=firstWinHandle){

				//Switch control to new window
				driver.switchTo().window(winHandle);
				driver.manage().window().maximize();

				if (Initialization.EnvirSite.equalsIgnoreCase("test")) {
					if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetExplorer"))) { 
						driver.get("javascript:document.getElementById('overridelink').click();");
					}
				}

				// Wait till the page loads all the elements in it.
				WebElement retelm2 = waitForElement(properties.getProperty("ProfileAdmin"));				



				//Verify User permission



				if (doesElementExist(properties.getProperty("Clientapplnk"), 5)) {
					WebElement clntappmenu = driver.findElement(By.xpath(properties.getProperty("Clientapplnk")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", clntappmenu);
					Thread.sleep(3000);
					log.logLine(Testname, false, "Navigating to Admin - Clientapp admin link..");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Navigating to Admin - Clientapp admin link is failed");					
				}

				WebElement retelm9 = waitForElement(properties.getProperty("AppNamefld"));
				log.logLine(Testname, false, "AppNamefld is found on the page..");


				//Move the mouse on Welcome text to avoid blinking
				WebElement hellolbl = driver.findElement(By.cssSelector(properties.getProperty("HelloUserName")));
				action.moveToElement(hellolbl).perform();
				Thread.sleep(1000);				


				if (doesElementExist2(properties.getProperty("clientname"), 5)) {
					WebElement cntnme = driver.findElement(By.cssSelector(properties.getProperty("clientname")));
					cntnme.clear();
					//((JavascriptExecutor) driver).executeScript("arguments[0].click()", cntnme);
					action.sendKeys(cntnme, ClntName).perform();
					//cntnme.sendKeys(ClntName);	
					Thread.sleep(1000);
					log.logLine(Testname, false, "Entered the client name "+ClntName+" in the client name text field in Client/App tool");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Entering the client name "+ClntName+" in the client name text field in Client/App tool is failed");
				}

				//Entering Application name in ApplicationId text box


				if (doesElementExist2(properties.getProperty("ApplicationId"), 5)) {
					WebElement applid = driver.findElement(By.cssSelector(properties.getProperty("ApplicationId")));

					action.sendKeys(applid, AppName).perform();
					//applid.sendKeys(AppName);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Entered the application name "+AppName+" in the Application name text field in Client/App tool");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Entering the application name "+AppName+" in the Application name text field in Client/App tool is failed");
				}	   


				if (doesElementExist(properties.getProperty("AnyTool1"), 5)) {
					WebElement AnyTool = driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_lstLayers']"));
					Select select = new Select(AnyTool); 
					List <WebElement> options = select.getOptions(); 
					for (WebElement option: options) { 
						if (option.getText().equalsIgnoreCase("PIVOT eDelivery")){ 
							option.click(); 
							break; 
						}
					}
					log.logLine(Testname, false, "Clicked on AnyTool drop down list and selected the option PIVOT eDelivery..");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Clicking on AnyTool drop down list and selecting the option PIVOT eDelivery is  failed");					
				}	     

				//clicking on search button	    
				if (doesElementExist2(properties.getProperty("searchbtn"), 5)) {
					WebElement srcbtn = driver.findElement(By.cssSelector(properties.getProperty("searchbtn")));

					log.logLine(Testname, false, "Clicked on search button of the client/app/Tool Admin");
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", srcbtn);
					Thread.sleep(2000);
					waitUntilRetrive();

				} else {
					negativeCase(Testname, firstWinHandle, "", "Clicking on search button of the client/app/Tool Admin is failed");			    	
				}


				if (doesElementExist2(properties.getProperty("ModifyTool"), 5)) {
					WebElement modtool = driver.findElement(By.cssSelector(properties.getProperty("ModifyTool")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", modtool);

					waitUntilRetrive();			
					log.logLine(Testname, false, "Clicked on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin ");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Clicking on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin is failed");
				}	


				if (doesElementExist2(properties.getProperty("edelAppdataEdit"), 5)) {
					WebElement edeldataedit = driver.findElement(By.cssSelector(properties.getProperty("edelAppdataEdit")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", edeldataedit);
					waitUntilRetrive();
					Thread.sleep(2000);
					log.logLine(Testname, false, "Clicked on the edit button under the eDelivery Application Specific Data tab");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Clicking on the edit button under the eDelivery Application Specific Data tab is failed");    		
				}

				/*
			    if (doesElementExist2(properties.getProperty("Dupdocoptntype"), 5)) {
		    	    WebElement edeldataedit = driver.findElement(By.cssSelector(properties.getProperty("Dupdocoptntype")));
		    	    ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",edeldataedit);
		    	    Highlight(edeldataedit);
		    	    edeldataedit.click();

		    	     	   // ((JavascriptExecutor) driver).executeScript("arguments[0].click()", edeldataedit);

		    	    Thread.sleep(2000);
		    	   	log.logLine(Testname, false, "Click on Type drop down option under Duplicate document options section");
		    	} else {
		    		negativeCase(Testname, firstWinHandle, "", "Click on Type drop down option under Duplicate document options section is failed");    		
		    	}

				 */  
				if (doesElementExist2(properties.getProperty("Dupdocoptntype"), 5)) {
					WebElement docoptn = driver.findElement(By.cssSelector("select[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_ctlServices_ctlSrvM_frmMain_lstDupType']"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",docoptn);
					Select select = new Select(docoptn); 
					List <WebElement> options = select.getOptions(); 
					for (WebElement option: options) { 
						if (option.getText().equalsIgnoreCase("Acknowledge")){ 
							option.click(); 
							break; 
						}
					}
					log.logLine(Testname, false, "Click on Type drop down option and selected the option **** Acknowledge **** under Duplicate document options section");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Click on Type drop down option and selected the option Acknowledge under Duplicate document options section is failed");					
				}	 


				if (doesElementExist(properties.getProperty("DocText"), 5)) {
					WebElement doctxt = driver.findElement(By.xpath(properties.getProperty("DocText")));
					doctxt.clear();
					doctxt.sendKeys(AckText);
					Thread.sleep(2000);
					log.logLine(Testname, false, "Entering the Text as **** "+AckText+" ****");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Entering the Text is failed");    		
				}


				if (doesElementExist2(properties.getProperty("edelAppdataupdatebtn"), 5)) {
					WebElement edeldataedit = driver.findElement(By.cssSelector(properties.getProperty("edelAppdataupdatebtn")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", edeldataedit);
					waitUntilRetrive();
					Thread.sleep(8000);
					log.logLine(Testname, false, "Clicked on the update button under the eDelivery Application Specific Data tab");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Clicking on the update button under the eDelivery Application Specific Data tab is failed");	    	
				}


			}
			//Close the old pivot window
			driver.close();
			driver.switchTo().window(firstWinHandle);

		}	    


		//driver.switchTo().frame("iframeContainer");
		WebElement retelm2 = waitForElement(properties.getProperty("selClint1"));

		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		if (doesElementExist2(properties.getProperty("Archives"), 5)) {    
			WebElement arclnk = driver.findElement(By.cssSelector(properties.getProperty("Archives")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", arclnk);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Navigation to Archives page is successfull");
		} else {
			log.logLine(Testname, true, "Clicking on Archives menu is failed");
			throw new Exception("Clicking on Archives menu is failed");
		}    


		Thread.sleep(3000);
	    boolean CliSelected = false;
	    
	    
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



	public boolean AcknowledgeFeature(String AccNo,String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	

		SearchText = test.readColumnData("Text", sheetname);


		Thread.sleep(25000);
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


		if (doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			log.logLine(Testname, false, "Click on Advance Search Button is successfull");
		} else {
			log.logLine(Testname, true, "Click on Advance Search Button is failed");
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
			Thread.sleep(5000);

			log.logLine(Testname, false, "Clicking on Seacrh criteria Operator drop down list in advance search");

			if (doesElementExist2(properties.getProperty("SelOpertOpt"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelOpertOpt")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Starts with (wildcard)")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(5000);
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

		Thread.sleep(5000);

		if (doesElementExist2(properties.getProperty("Txtfld"), 5)) {	  
			WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("Txtfld")));
			Txt.click();
			Txt.clear();
			Txt.sendKeys(Initialization.EightDig2);
			Thread.sleep(5000);
			log.logLine(Testname, false, "The Enterd value in text field is "+Initialization.EightDig2+"");		 
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

		Thread.sleep(8000);
		if (doesElementExist(properties.getProperty("Items"), 5)) {	    
			String val = driver.findElement(By.xpath(".//*[@id='archive-search-grid']/div/span[2]")).getText();
			String arr[] = val.split("of");
			Beforeupdte = arr[1].trim();
			log.logLine(Testname, false, "The total number of documents displayed before editing and updating the document are: **** "+Beforeupdte+" ****");
		}


		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("Chooseactn"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Chooseactn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);

			log.logLine(Testname, false, "Clicking on Choose action button is successfull");
		} else {
			log.logLine(Testname, true, "Clicking on Choose action button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Choose action button is failed");
		}


		if(doesElementExist2(properties.getProperty("Editoptn"), 5)){
			List<WebElement> Reprnt = driver.findElements(By.cssSelector(properties.getProperty("Editoptn")));
			for (WebElement lnk:Reprnt) {
				if (lnk.getText().equals("Edit")){
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Selecting Edit option under choose action is successfull..");
					break;
				}
			}
		}




		if (doesElementExist2(properties.getProperty("Caldate"), 5)) {	    
			WebElement selcalendbtn = driver.findElement(By.cssSelector(properties.getProperty("Caldate")));
			Thread.sleep(2000);
			selcalendbtn.clear();
			selcalendbtn.sendKeys(todaysDate);
			log.logLine(Testname, false, "Entered the current date  "+todaysDate+" in calender textarea");
		} else {
			log.logLine(Testname, true, "Entering the current date  "+todaysDate+" in calender textarea is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the current date  "+todaysDate+" in calender textarea is failed");
		}


		stmtnum = "Acknowledge_"+AccNo;
		if (doesElementExist2(properties.getProperty("Stmtno"), 5)) {
			WebElement stmtno = driver.findElement(By.cssSelector(properties.getProperty("Stmtno")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", stmtno);
			Thread.sleep(5000);
			stmtno.clear();
			stmtno.sendKeys(stmtnum);
			log.logLine(Testname, false, "Entering the statement number **** "+stmtnum+" **** in editbeta form of the Archives ");

		} else {
			log.logLine(Testname, true, "Enetering the statement number "+stmtnum+" in editbeta form of the Archives is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Enetering the statement number "+stmtnum+" in editbeta form of the Archives is failed");
		}

		if (doesElementExist2(properties.getProperty("Savebtn"), 5)) {	    
			WebElement savebtn = driver.findElement(By.cssSelector(properties.getProperty("Savebtn")));
			savebtn.click();
			Thread.sleep(8000);
			log.logLine(Testname, false, "Clicking on the save button of the EditBeta form is successfull");
		} else {
			log.logLine(Testname, true, "Clicking on the save button of the EditBeta form is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on the save button of the EditBeta form is failed");
		}



		//Thread.sleep(5000);
		//Alert Aler = driver.switchTo().alert();
		//Aler.accept();	  


		if (doesElementExist2(properties.getProperty("Alrtok"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Alrtok")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			log.logLine(Testname, false, "Clicking on Ok button is successfull");
		} else {
			log.logLine(Testname, true, "Clicking on Ok button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Ok button is failed");
		}




		if (doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			log.logLine(Testname, false, "Click on Advance Search is successfull");
		} else {
			log.logLine(Testname, true, "Click on Advance Search is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Advance Search is failed");
		}

		/*
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


		if (doesElementExist2(properties.getProperty("FromDate"), 5)) {
			WebElement selcalendbtn = driver.findElement(By.cssSelector(properties.getProperty("FromDate")));
			//((JavascriptExecutor) driver).executeScript("arguments[0].click()", selcalendbtn);
			selcalendbtn.clear();
			selcalendbtn.sendKeys(todaysDate);
			log.logLine(Testname, false, "Selecting Today's date from the Calender  "+todaysDate);
		} else {
			log.logLine(Testname, true, "Selecting Today's date from the Calender is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Selecting Today's date from the Calender is failed");
		}	


		if (doesElementExist2(properties.getProperty("ToDate"), 5)) {
			WebElement selcalendbtn = driver.findElement(By.cssSelector(properties.getProperty("FromDate")));
			//((JavascriptExecutor) driver).executeScript("arguments[0].click()", selcalendbtn);
			selcalendbtn.clear();
			selcalendbtn.sendKeys(todaysDate);
			log.logLine(Testname, false, "Selecting Today's date from the Calender  "+todaysDate);
		} else {
			log.logLine(Testname, true, "Selecting Today's date from the Calender is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Selecting Today's date from the Calender is failed");
		}	

		 */
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


		Thread.sleep(4000);
		if (doesElementExist(properties.getProperty("Items"), 5)) {	    
			String val = driver.findElement(By.xpath(".//*[@id='archive-search-grid']/div/span[2]")).getText();
			String arr[] = val.split("of");
			Afterupdte = arr[1].trim();

			log.logLine(Testname, false, "The total number of documents displayed after editing and updating the document are: **** "+Afterupdte+" ****");
		}


		if(Afterupdte.equals(Beforeupdte)){

			log.logLine(Testname, true, "The number of documents in the grid are not increased after Acknowledging the document");
		}else{
			log.logLine(Testname, false, "The number of documents in the grid are increased after Acknowledging the document");
		}



		String[] Sort = new String[150];
		int length = Sort.length;

		String row = "tr";
		List<WebElement> DataCnt= driver.findElements(By.xpath("//div[@id='archive-search-grid']/table/tbody/tr"));
		if(doesElementExist2(properties.getProperty("Stmtnum"), 5)){
			for(int i = 0; i < DataCnt.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='archive-search-grid'] table tbody "+row+" td+td+td+td+td+td+td+td+td+td+td+td")).getText();
				String Text=Sort[i];


				if(Text.equals(stmtnum)){
					log.logLine(Testname, false, "Edited document present in the grid and Contains the statement number as **** "+stmtnum+" **** in the grid");
					//Sort[i] = driver.findElement(By.cssSelector("div[id='archive-search-grid'] table tbody "+row+" td a img]")).getText();
					//log.logLine(Testname, true, "Column contains date as "+Sort[i]+"");
					break;

				}

				row = row + "+tr";
				log.logLine(Testname, false, "Iterating through the Rows..");
			}

		}


		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		if (doesElementExist2(properties.getProperty("Archives"), 5)) {    
			WebElement arclnk = driver.findElement(By.cssSelector(properties.getProperty("Archives")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", arclnk);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Navigation to Archives page is successfull");
		} else {
			log.logLine(Testname, true, "Clicking on Archives menu is failed");
			throw new Exception("Clicking on Archives menu is failed");
		}

		Setback(AccNo,Testname);


		return true;
	}


	public boolean Setback(String AccNo, String Testname) throws Exception {


		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile")); 		
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();


		String ClntName = test.readColumnData("ClientName", sheetname);	  
		String AppName = test.readColumnData("ApplicationName", sheetname);
		String RplaceText = test.readColumnData("DocumentText", sheetname);


		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");
			//throw new Exception("Clicking on Cancel button in Client/App popup is failed");
		}



		Thread.sleep(10000);
		Actions builder = new Actions(driver);                
		WebElement mnuElement = driver.findElement(By.xpath(properties.getProperty("AdminMenu")));        
		if (doesElementExist(properties.getProperty("AdminMenu"), 10)) {                          
			// Move cursor to the Main Menu Element  
			builder.moveToElement(mnuElement).perform();                
			// Clicking on the Hidden SubMenu  
			Thread.sleep(10000);
			if (doesElementExist2(properties.getProperty("Adminlnk"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Adminlnk")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().contains("HA Admin")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Clicking on Legacy Admin..");                
						break;
					}                                
				}

			} else {
				log.logLine(Testname, true, "Clicking on Legacy Admin.. is failed");
				throw new Exception("Clicking on Legacy Admin.. is failed");                        
			}
		}

		Thread.sleep(10000);
		Set<String> handles = driver.getWindowHandles();
		String firstWinHandle = driver.getWindowHandle(); 
		handles.remove(firstWinHandle);

		boolean browserexist = handles.iterator().hasNext();
		if (browserexist) {
			String winHandle=handles.iterator().next();
			if (winHandle!=firstWinHandle){

				//Switch control to new window
				driver.switchTo().window(winHandle);
				driver.manage().window().maximize();

				if (Initialization.EnvirSite.equalsIgnoreCase("test")) {
					if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetExplorer"))) { 
						driver.get("javascript:document.getElementById('overridelink').click();");
					}
				}

				// Wait till the page loads all the elements in it.
				WebElement retelm2 = waitForElement(properties.getProperty("ProfileAdmin"));				



				//Verify User permission



				if (doesElementExist(properties.getProperty("Clientapplnk"), 5)) {
					WebElement clntappmenu = driver.findElement(By.xpath(properties.getProperty("Clientapplnk")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", clntappmenu);
					Thread.sleep(3000);
					log.logLine(Testname, false, "Navigating to Admin - Clientapp admin link..");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Navigating to Admin - Clientapp admin link is failed");					
				}

				WebElement retelm9 = waitForElement(properties.getProperty("AppNamefld"));
				log.logLine(Testname, false, "AppNamefld is found on the page..");


				//Move the mouse on Welcome text to avoid blinking
				WebElement hellolbl = driver.findElement(By.cssSelector(properties.getProperty("HelloUserName")));
				action.moveToElement(hellolbl).perform();
				Thread.sleep(1000);				


				if (doesElementExist2(properties.getProperty("clientname"), 5)) {
					WebElement cntnme = driver.findElement(By.cssSelector(properties.getProperty("clientname")));
					cntnme.clear();
					//((JavascriptExecutor) driver).executeScript("arguments[0].click()", cntnme);
					action.sendKeys(cntnme, ClntName).perform();
					//cntnme.sendKeys(ClntName);	
					Thread.sleep(1000);
					log.logLine(Testname, false, "Entered the client name "+ClntName+" in the client name text field in Client/App tool");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Entering the client name "+ClntName+" in the client name text field in Client/App tool is failed");
				}

				//Entering Application name in ApplicationId text box


				if (doesElementExist2(properties.getProperty("ApplicationId"), 5)) {
					WebElement applid = driver.findElement(By.cssSelector(properties.getProperty("ApplicationId")));

					action.sendKeys(applid, AppName).perform();
					//applid.sendKeys(AppName);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Entered the application name "+AppName+" in the Application name text field in Client/App tool");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Entering the application name "+AppName+" in the Application name text field in Client/App tool is failed");
				}	   


				if (doesElementExist(properties.getProperty("AnyTool1"), 5)) {
					WebElement AnyTool = driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_lstLayers']"));
					Select select = new Select(AnyTool); 
					List <WebElement> options = select.getOptions(); 
					for (WebElement option: options) { 
						if (option.getText().equalsIgnoreCase("PIVOT eDelivery")){ 
							option.click(); 
							break; 
						}
					}
					log.logLine(Testname, false, "Clicked on AnyTool drop down list and selected the option PIVOT eDelivery..");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Clicking on AnyTool drop down list and selecting the option PIVOT eDelivery is  failed");					
				}	     

				//clicking on search button	    
				if (doesElementExist2(properties.getProperty("searchbtn"), 5)) {
					WebElement srcbtn = driver.findElement(By.cssSelector(properties.getProperty("searchbtn")));

					log.logLine(Testname, false, "Clicked on search button of the client/app/Tool Admin");
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", srcbtn);
					Thread.sleep(2000);
					waitUntilRetrive();

				} else {
					negativeCase(Testname, firstWinHandle, "", "Clicking on search button of the client/app/Tool Admin is failed");			    	
				}


				if (doesElementExist2(properties.getProperty("ModifyTool"), 5)) {
					WebElement modtool = driver.findElement(By.cssSelector(properties.getProperty("ModifyTool")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", modtool);

					waitUntilRetrive();			
					log.logLine(Testname, false, "Clicked on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin ");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Clicking on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin is failed");
				}	


				if (doesElementExist2(properties.getProperty("edelAppdataEdit"), 5)) {
					WebElement edeldataedit = driver.findElement(By.cssSelector(properties.getProperty("edelAppdataEdit")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", edeldataedit);
					waitUntilRetrive();
					Thread.sleep(2000);
					log.logLine(Testname, false, "Clicked on the edit button under the eDelivery Application Specific Data tab");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Clicking on the edit button under the eDelivery Application Specific Data tab is failed");    		
				}

				/*
			    if (doesElementExist2(properties.getProperty("Dupdocoptntype"), 5)) {
		    	    WebElement edeldataedit = driver.findElement(By.cssSelector(properties.getProperty("Dupdocoptntype")));
		    	    ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",edeldataedit);
		    	    Highlight(edeldataedit);
		    	    edeldataedit.click();

		    	     	   // ((JavascriptExecutor) driver).executeScript("arguments[0].click()", edeldataedit);

		    	    Thread.sleep(2000);
		    	   	log.logLine(Testname, false, "Click on Type drop down option under Duplicate document options section");
		    	} else {
		    		negativeCase(Testname, firstWinHandle, "", "Click on Type drop down option under Duplicate document options section is failed");    		
		    	}

				 */  
				if (doesElementExist2(properties.getProperty("Dupdocoptntype"), 5)) {
					WebElement docoptn = driver.findElement(By.cssSelector("select[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_ctlServices_ctlSrvM_frmMain_lstDupType']"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",docoptn);
					Select select = new Select(docoptn); 
					List <WebElement> options = select.getOptions(); 
					for (WebElement option: options) { 
						if (option.getText().equalsIgnoreCase("Replace")){ 
							option.click(); 
							break; 
						}
					}
					log.logLine(Testname, false, "Click on Type drop down option and selected the option Replace under Duplicate document options section");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Click on Type drop down option and selected the option Acknowledge under Duplicate document options section is failed");					
				}	 


				if (doesElementExist(properties.getProperty("DocText"), 5)) {
					WebElement doctxt = driver.findElement(By.xpath(properties.getProperty("DocText")));
					doctxt.clear();
					doctxt.sendKeys(RplaceText);
					Thread.sleep(2000);
					log.logLine(Testname, false, "Entering the Text as **** "+RplaceText+" ****");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Entering the Text is failed");    		
				}


				if (doesElementExist2(properties.getProperty("edelAppdataupdatebtn"), 5)) {
					WebElement edeldataedit = driver.findElement(By.cssSelector(properties.getProperty("edelAppdataupdatebtn")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", edeldataedit);
					waitUntilRetrive();
					Thread.sleep(8000);
					log.logLine(Testname, false, "Clicked on the update button  under the eDelivery Application Specific Data tab");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Clicking on the update button  under the eDelivery Application Specific Data tab is failed");	    	
				}


			}
			//Close the old pivot window
			driver.close();
			driver.switchTo().window(firstWinHandle);

		}	    


		//driver.switchTo().frame("iframeContainer");
		WebElement retelm2 = waitForElement(properties.getProperty("selClint1"));

		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		if (doesElementExist2(properties.getProperty("Archives"), 5)) {    
			WebElement arclnk = driver.findElement(By.cssSelector(properties.getProperty("Archives")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", arclnk);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Navigation to Archives page is successfull");
		} else {
			log.logLine(Testname, true, "Clicking on Archives menu is failed");
			throw new Exception("Clicking on Archives menu is failed");
		}    


		Thread.sleep(3000);

		boolean CliSelected = false;
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


		Thread.sleep(5000);
		driver.switchTo().frame("iframeContainer");	    


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
					if (lnk.getText().equals("Starts with (wildcard)")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(2000);
						log.logLine(Testname, false, "Selecting the Starts with (wildcard operator option from the dropdown");
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
			log.logLine(Testname, false, "The Enterd value in text field is "+Initialization.EightDig2+" ");		 
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

		Thread.sleep(4000);
		if (doesElementExist(properties.getProperty("Items"), 5)) {	    
			String val = driver.findElement(By.xpath(".//*[@id='archive-search-grid']/div/span[2]")).getText();
			String arr[] = val.split("of");
			Beforeupdte = arr[1].trim();

			log.logLine(Testname, false, "The total number of documents displayed before editing and updating the document are: **** "+Beforeupdte+" ****");
		}


		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("Chooseactn"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Chooseactn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);

			log.logLine(Testname, false, "Clicking on Choose action button is successfull");
		} else {
			log.logLine(Testname, true, "Clicking on Choose action button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Choose action button is failed");
		}


		if(doesElementExist2(properties.getProperty("Editoptn"), 5)){
			List<WebElement> Reprnt = driver.findElements(By.cssSelector(properties.getProperty("Editoptn")));
			for (WebElement lnk:Reprnt) {
				if (lnk.getText().equals("Edit")){
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Selecting Edit option under choose action is successfull..");
					break;
				}
			}
		}




		if (doesElementExist2(properties.getProperty("Caldate"), 5)) {	    
			WebElement selcalendbtn = driver.findElement(By.cssSelector(properties.getProperty("Caldate")));
			Thread.sleep(2000);
			selcalendbtn.clear();
			selcalendbtn.sendKeys(todaysDate);
			log.logLine(Testname, false, "Entered the current date  "+todaysDate+" in calender textarea");
		} else {
			log.logLine(Testname, true, "Entering the current date  "+todaysDate+" in calender textarea is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the current date  "+todaysDate+" in calender textarea is failed");
		}


		Editstmtnum = "EditAcknowledge_"+AccNo;
		if (doesElementExist2(properties.getProperty("Stmtno"), 5)) {
			WebElement stmtno = driver.findElement(By.cssSelector(properties.getProperty("Stmtno")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", stmtno);
			Thread.sleep(5000);
			stmtno.clear();
			stmtno.sendKeys(Editstmtnum);
			log.logLine(Testname, false, "Entering the statement number **** "+Editstmtnum+" **** in editbeta form of the Archives ");

		} else {
			log.logLine(Testname, true, "Enetering the statement number "+Editstmtnum+" in editbeta form of the Archives is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Enetering the statement number "+Editstmtnum+" in editbeta form of the Archives is failed");
		}

		if (doesElementExist2(properties.getProperty("Savebtn"), 5)) {	    
			WebElement savebtn = driver.findElement(By.cssSelector(properties.getProperty("Savebtn")));
			savebtn.click();
			Thread.sleep(8000);
			log.logLine(Testname, false, "Clicking on the save button of the EditBeta form is successfull");
		} else {
			log.logLine(Testname, true, "Clicking on the save button of the EditBeta form is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on the save button of the EditBeta form is failed");
		}



		//Thread.sleep(5000);
		//Alert Aler = driver.switchTo().alert();
		//Aler.accept();	  


		if (doesElementExist2(properties.getProperty("Alrtok"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Alrtok")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			log.logLine(Testname, false, "Clicking on Ok button is successfull");
		} else {
			log.logLine(Testname, true, "Clicking on Ok button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Ok button is failed");
		}




		if (doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			log.logLine(Testname, false, "Click on Advance Search is successfull");
		} else {
			log.logLine(Testname, true, "Click on Advance Search is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Advance Search is failed");
		}

		/*
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


		if (doesElementExist2(properties.getProperty("FromDate"), 5)) {
			WebElement selcalendbtn = driver.findElement(By.cssSelector(properties.getProperty("FromDate")));
			//((JavascriptExecutor) driver).executeScript("arguments[0].click()", selcalendbtn);
			selcalendbtn.clear();
			selcalendbtn.sendKeys(todaysDate);
			log.logLine(Testname, false, "Selecting Today's date from the Calender  "+todaysDate);
		} else {
			log.logLine(Testname, true, "Selecting Today's date from the Calender is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Selecting Today's date from the Calender is failed");
		}	


		if (doesElementExist2(properties.getProperty("ToDate"), 5)) {
			WebElement selcalendbtn = driver.findElement(By.cssSelector(properties.getProperty("FromDate")));
			//((JavascriptExecutor) driver).executeScript("arguments[0].click()", selcalendbtn);
			selcalendbtn.clear();
			selcalendbtn.sendKeys(todaysDate);
			log.logLine(Testname, false, "Selecting Today's date from the Calender  "+todaysDate);
		} else {
			log.logLine(Testname, true, "Selecting Today's date from the Calender is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Selecting Today's date from the Calender is failed");
		}	

		 */
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


		Thread.sleep(4000);
		if (doesElementExist(properties.getProperty("Items"), 5)) {	    
			String val = driver.findElement(By.xpath(".//*[@id='archive-search-grid']/div/span[2]")).getText();
			String arr[] = val.split("of");
			Afterupdte = arr[1].trim();

			log.logLine(Testname, false, "The total number of documents displayed after editing and updating the document are: **** "+Afterupdte+" ****");
		}


		if(Afterupdte.equals(Beforeupdte)){

			log.logLine(Testname, false, "The number of documents in the grid are not increased after Replacing the document");
		}else{
			log.logLine(Testname, true, "The number of documents in the grid are increased after Replacing the document");
		}



		String[] Sort = new String[150];
		int length = Sort.length;

		String row = "tr";
		List<WebElement> DataCnt= driver.findElements(By.xpath("//div[@id='archive-search-grid']/table/tbody/tr"));
		if(doesElementExist2(properties.getProperty("Stmtnum"), 5)){
			for(int i = 0; i < DataCnt.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='archive-search-grid'] table tbody "+row+" td+td+td+td+td+td+td+td+td+td+td+td")).getText();
				String Text1=Sort[i];


				if(Text1.equals(Editstmtnum)){
					log.logLine(Testname, false, "Edited document present in the grid and Contains the statement number as **** "+Editstmtnum+" **** in the grid");
					//Sort[i] = driver.findElement(By.cssSelector("div[id='archive-search-grid'] table tbody "+row+" td a img]")).getText();
					//log.logLine(Testname, true, "Column contains date as "+Sort[i]+"");
					break;

				}

				row = row + "+tr";
				log.logLine(Testname, false, "Iterating through the Rows..");
			}

		}

		return true;
	}





	public void Highlight(WebElement choseacts) throws Exception{
		for (int i = 0; i < 2; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			//    WebElement choseacts = null;
			js.executeScript( "arguments[0].setAttribute('style', arguments[1]);", choseacts , "color: red; border: 5px solid red;");
			Thread.sleep(1000);
			js.executeScript( "arguments[0].setAttribute('style', arguments[1]);", choseacts, "");
		}
	}


}



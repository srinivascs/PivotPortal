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

import launchAuto.CreateAppData;
import launchAuto.Initialization;
import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class DirectToDocument extends Page{

	public DirectToDocument(WebDriver driver, Log log) throws InvalidFormatException, IOException {
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
	String Subject="Documents";
	
	String firstWinHandle = null;	
	String secondWinHandle = null;
	String thirdWinHandle = null;
	public static long startTime,startTime1,startTime2;	



	public boolean Clientappsearch(String AccNo, String Testname) throws Exception {

		
		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

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

		/* Actions builder = new Actions(driver);	        
	    WebElement mnuElement = driver.findElement(By.xpath(properties.getProperty("AdminMenu")));	
		if (doesElementExist(properties.getProperty("AdminMenu"), 10)) {			  
			// Move cursor to the Main Menu Element  
			builder.moveToElement(mnuElement).perform();		
			// Clicking on the Hidden SubMenu  
			WebElement oldpivt = driver.findElement(By.cssSelector(properties.getProperty("Adminlnk")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", oldpivt);		
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicking on Legacy Admin..");		      
		} else {
			log.logLine(Testname, true, "Clicking on Legacy Admin.. is failed");
			throw new Exception("Clicking on Legacy Admin.. is failed");			
		}
		 */

		Actions builder = new Actions(driver);	
		WebElement mnuElement = driver.findElement(By.xpath(properties.getProperty("AdminMenu")));	
		if (doesElementExist(properties.getProperty("AdminMenu"), 10)) {	
			// Move cursor to the Main Menu Element 
			builder.moveToElement(mnuElement).perform();	
			// Clicking on the Hidden SubMenu 
			Thread.sleep(1000);
			if (doesElementExist2(properties.getProperty("Adminlnk"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Adminlnk")));
				for (WebElement lnk:selopts) {
					Thread.sleep(1000);
					if (lnk.getText().contains("HA Admin")) {
						Thread.sleep(1000);
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



		Thread.sleep(25000);
		Set<String> handles = driver.getWindowHandles();
		firstWinHandle = driver.getWindowHandle(); 
		handles.remove(firstWinHandle);


		boolean browserexist = handles.iterator().hasNext();

		if (browserexist) {
			secondWinHandle=handles.iterator().next();
			if (secondWinHandle != firstWinHandle){			    	
				//Switch control to new window
				driver.switchTo().window(secondWinHandle);


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
	

				/*String ClntName = test.readColumnData("ClientName", sheetname);				
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
				}*/

				//Entering Application name in ApplicationId text box
				String AppName = test.readColumnData("ApplicationName", sheetname);

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

			}
		
		int paperID1 = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);		
		String AccNo1 = Integer.toString(paperID1);	
				
		int paperID2 = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);		
		String AccNo2 = Integer.toString(paperID2);	

	
		
		//Enable Link to Login
		Thread.sleep(5000);
		EnableLinktoLoginPage(Testname,firstWinHandle);
		
		startTime = System.currentTimeMillis();		
		CreateAppData Data=new CreateAppData(log);
		Data.CreateArchivesHTML(AccNo, "");
		
		long timenow = System.currentTimeMillis();
		long testime = timenow - startTime;
		int totalTime =(int) ((testime/(1000*60)));	 
 		 
		if (11 > totalTime) {
			int WaitTime = 11 - totalTime; 
			log.logLine(Testname, false, "Going to wait for "+WaitTime +"minutes, please wait...");
			Thread.sleep(WaitTime*1000*60);		 
		}
		
		GmailLogin(Testname);
		
		driver.close();
		driver.switchTo().window(firstWinHandle);

		
		//Login Required Checked for Direct to Document and User not logged in for SKYBLUE App
		Clientapp(AccNo,Testname);
		DirecttodocLoginrequired(Testname,firstWinHandle);
		
		startTime1 = System.currentTimeMillis();		
		Data.CreateArchivesHTML(AccNo1, "");

		long timenow1 = System.currentTimeMillis();
		long testime1 = timenow1 - startTime1;
		int totalTime1 =(int) ((testime1/(1000*60)));	 
 		 
		if (12 > totalTime1) {
			int WaitTime = 12 - totalTime1; 
			log.logLine(Testname, false, "Going to wait for "+WaitTime +"minutes, please wait...");
			Thread.sleep(WaitTime*1000*60);		 
		}
		
		
		GmailLogin1(Testname);
		
		
		//Login Required Checked for Direct to Document and User already logged in for SKYBLUE App and direct download		
		GmailLogin2(Testname);
		
		driver.close();

		driver.switchTo().window(firstWinHandle);
		
		
		//Login Required Unchecked for Direct to Document and User not logged in for SKYBLUE App and direct download	
		Clientapp(AccNo,Testname);
		DirecttodocLoginnotrequired(Testname,firstWinHandle);
		
		startTime2 = System.currentTimeMillis();	
		Data.CreateArchivesHTML(AccNo2, "");
	
		long timenow2 = System.currentTimeMillis();
		long testime2 = timenow2 - startTime2;
		int totalTime2 =(int) ((testime2/(1000*60)));	 
 		 
		if (12 > totalTime1) {
			int WaitTime = 12 - totalTime2; 
			log.logLine(Testname, false, "Going to wait for "+WaitTime +"minutes, please wait...");
			Thread.sleep(WaitTime*1000*60);		 
		}
		
		
		GmailLogin2(Testname);
		
		
		driver.close();
		// Switching back to parent window
		driver.switchTo().window(firstWinHandle);
		
		}	

	
		return true;

	}


	public void EnableLinktoLoginPage(String Testname,String firstWinHandle) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();


		if (doesElementExist2(properties.getProperty("eDelApplSpecificdata"), 5)) {
		    WebElement eDelspecidata = driver.findElement(By.cssSelector(properties.getProperty("eDelApplSpecificdata")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelspecidata);
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Clicked on the eDelivery Client Overrides tab under view pivot information");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the eDelivery Client Overrides tab under view pivot information is failed");
		}
	    
		if (doesElementExist(properties.getProperty("edelAppdataEdit"), 5)) {
		    WebElement edeldataedit = driver.findElement(By.xpath(properties.getProperty("edelAppdataEdit")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", edeldataedit);
		    Thread.sleep(1000);
		    waitUntilRetrive();
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Clicked on the edit button under the eDelivery Application Specific Data tab");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the edit button under the eDelivery Application Specific Data tab is failed");
	   	}
	    
		 if (doesElementExist2(properties.getProperty("CltappDocmntNotification"), 5)) {
		        WebElement chkboxdocnotifcn = driver.findElement(By.cssSelector(properties.getProperty("CltappDocmntNotification")));
		        
		        if ( chkboxdocnotifcn.isSelected())
		         {
		        	log.logLine(Testname, false, "DocumentNotification checkbox is already selected in eNotification Settings in client app admin");
		         }else{
		        	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxdocnotifcn);
		         }
		         log.logLine(Testname, false, "Selected the DocumentNotification check box in eNotification Settings in client app admin sucessfull");
		    } else {
		    	 negativeCase(Testname, firstWinHandle, "", "Selecting the DocumentNotification check box in eNotification Settings in client app admin is failed");	         
		    }
		    
		    Thread.sleep(2000);
		    if (doesElementExist2(properties.getProperty("Linktolgnpage"), 5)) {
		        WebElement chkboxdocAttach = driver.findElement(By.cssSelector(properties.getProperty("Linktolgnpage")));
		        
		        if ( chkboxdocAttach.isSelected())
		         {
		        	log.logLine(Testname, false, "Document Attachment checkbox is already selected in eNotification Settings in client app admin");
		         }else{
		        	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxdocAttach);
		        	 Thread.sleep(2000);
		         }
		         log.logLine(Testname, false, "Selected the Link to Login Page radio button in eNotification Settings in client app admin sucessfull");
		    } else {
		    	 negativeCase(Testname, firstWinHandle, "", "Selecting the Link to Login Page radio button in eNotification Settings in client app admin is failed");	         
		    }

			/*String ccmailadd = test.readColumnData("CCMail", sheetname);
  
		    if (doesElementExist2(properties.getProperty("eDelApplSpecificdata"), 5)) {
			    WebElement ccmail = driver.findElement(By.cssSelector(properties.getProperty("eDelApplSpecificdata")));
			    ccmail.clear();
			    ccmail.sendKeys(ccmailadd);
			    Thread.sleep(2000);
			   	log.logLine(Testname, false, "Clicked on the eDelivery Client Overrides tab under view pivot information");
			} else {
				negativeCase(Testname, firstWinHandle, "", "Clicking on the eDelivery Client Overrides tab under view pivot information is failed");
			}
		
		    
		    String bccmailadd = test.readColumnData("BCCMail", sheetname);
		    
		    if (doesElementExist2(properties.getProperty("eDelApplSpecificdata"), 5)) {
			    WebElement ccmail = driver.findElement(By.cssSelector(properties.getProperty("eDelApplSpecificdata")));
			    ccmail.clear();
			    ccmail.sendKeys(bccmailadd);
			    Thread.sleep(2000);
			   	log.logLine(Testname, false, "Clicked on the eDelivery Client Overrides tab under view pivot information");
			} else {
				negativeCase(Testname, firstWinHandle, "", "Clicking on the eDelivery Client Overrides tab under view pivot information is failed");
			}*/
		
		    if (doesElementExist2(properties.getProperty("edelAppdataupdatebtn"), 5)) {
	    	    WebElement edeldataedit = driver.findElement(By.cssSelector(properties.getProperty("edelAppdataupdatebtn")));
	    	    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", edeldataedit);
	    	    waitUntilRetrive();
	    	    Thread.sleep(2000);
	    	   	log.logLine(Testname, false, "Clicked on the update button  under the eDelivery Application Specific Data tab");
	    	} else {
	    		negativeCase(Testname, firstWinHandle, "", "Clicking on the update button  under the eDelivery Application Specific Data tab is failed");
	    	}
		
	}

	
	public void DirecttodocLoginrequired(String Testname,String firstWinHandle) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();


		if (doesElementExist2(properties.getProperty("eDelApplSpecificdata"), 5)) {
		    WebElement eDelspecidata = driver.findElement(By.cssSelector(properties.getProperty("eDelApplSpecificdata")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelspecidata);
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Clicked on the eDelivery Client Overrides tab under view pivot information");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the eDelivery Client Overrides tab under view pivot information is failed");
		}
	    
		if (doesElementExist(properties.getProperty("edelAppdataEdit"), 5)) {
		    WebElement edeldataedit = driver.findElement(By.xpath(properties.getProperty("edelAppdataEdit")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", edeldataedit);
		    Thread.sleep(1000);
		    waitUntilRetrive();
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Clicked on the edit button under the eDelivery Application Specific Data tab");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the edit button under the eDelivery Application Specific Data tab is failed");
	   	}
	    
		 if (doesElementExist2(properties.getProperty("CltappDocmntNotification"), 5)) {
		        WebElement chkboxdocnotifcn = driver.findElement(By.cssSelector(properties.getProperty("CltappDocmntNotification")));
		        
		        if ( chkboxdocnotifcn.isSelected())
		         {
		        	log.logLine(Testname, false, "DocumentNotification checkbox is already selected in eNotification Settings in client app admin");
		         }else{
		        	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxdocnotifcn);
		        	 Thread.sleep(2000);
		         }
		         log.logLine(Testname, false, "Selected the DocumentNotification check box in eNotification Settings in client app admin sucessfull");
		    } else {
		    	 negativeCase(Testname, firstWinHandle, "", "Selecting the DocumentNotification check box in eNotification Settings in client app admin is failed");	         
		    }
		    
		    Thread.sleep(2000);
		    if (doesElementExist(properties.getProperty("DirecttoDoc"), 5)) {
		    	WebElement chkboxdocAttach = driver.findElement(By.xpath(properties.getProperty("DirecttoDoc")));
		    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxdocAttach);
		    	Thread.sleep(2000);
		    } else {
		    	negativeCase(Testname, firstWinHandle, "", "Selecting the Direct to Document radio button in eNotification Settings in client app admin is failed");	         
		    }
		    
		    /*if (doesElementExist2(properties.getProperty("ConfirmPopup"), 5)) {
	    	    WebElement cnfpopup = driver.findElement(By.cssSelector(properties.getProperty("ConfirmPopup")));
	    	    String Popup=driver.findElement(By.cssSelector(properties.getProperty("ConfirmPopup"))).getText();
	    	    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnfpopup);
	    	    Thread.sleep(2000);
	    	   	log.logLine(Testname, false, "Clicked on the 1st Confirmation pop up");
	    	} else {
	    		negativeCase(Testname, firstWinHandle, "", "Clicked on the 1st Confirmation pop up is failed");
	    	}
		    
		    if (doesElementExist2(properties.getProperty("ConfirmPopup"), 5)) {
	    	    WebElement cnfpopup = driver.findElement(By.cssSelector(properties.getProperty("ConfirmPopup")));
	    	    String Popup1=driver.findElement(By.cssSelector(properties.getProperty("ConfirmPopup"))).getText();
	    	    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnfpopup);
	    	    Thread.sleep(2000);
	    	   	log.logLine(Testname, false, "Clicked on the 2nd Confirmation pop up");
	    	} else {
	    		negativeCase(Testname, firstWinHandle, "", "Clicked on the 2nd Confirmation pop up is failed");
	    	}
		    
		    if (doesElementExist2(properties.getProperty("ConfirmPopup"), 5)) {
	    	    WebElement cnfpopup = driver.findElement(By.cssSelector(properties.getProperty("ConfirmPopup")));
	    	    String Popup2=driver.findElement(By.cssSelector(properties.getProperty("ConfirmPopup"))).getText();

	    	    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnfpopup);
	    	    Thread.sleep(2000);
	    	   	log.logLine(Testname, false, "Clicked on the 3rd Confirmation pop up");
	    	} else {
	    		negativeCase(Testname, firstWinHandle, "", "Clicked on the 3rd Confirmation pop up is failed");
	    	}*/
		    
			String confirmText1 = test.readColumnData("confText1", sheetname);
  
			if (doesElementExist2(properties.getProperty("confirmnpopup1"), 5)) {
				String confpopuptxt = driver.findElement(By.cssSelector(properties.getProperty("confpopupcontent1"))).getText();
				if(confpopuptxt.equalsIgnoreCase(confirmText1)){
					Thread.sleep(1000);
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("proceed")));
					for (WebElement lnk:selopts) {
						if (lnk.getText().equals("Proceed")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(2000);
							log.logLine(Testname, false, "Clicked on Proceed button of 1st confirmation page");
							break;
						}
					}
				}else {
					log.logLine(Testname, true, "failed to click Proceed button of 1st confirmation page");
					throw new Exception("failed to click Proceed button of 1st confirmation page");
				}
			}
			else {
				negativeCase(Testname, firstWinHandle, "", "Clicking on the PIVOT directtodoc link radio button in client/app/Tool Admin is failed");
			}

			Thread.sleep(2000);
			String confirmText2 = test.readColumnData("confText2", sheetname);

			if (doesElementExist2(properties.getProperty("confirmnpopup2"), 5)) {
				String confpopuptxt = driver.findElement(By.cssSelector(properties.getProperty("confpopupcontent2"))).getText();
				if(confpopuptxt.equalsIgnoreCase(confirmText2)){
					Thread.sleep(1000);
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("proceed")));
					for (WebElement lnk:selopts) {
						if (lnk.getText().equals("Proceed")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(2000);
							log.logLine(Testname, false, "Clicked on Proceed button of 2nd confirmation page");
							break;
						}
					}
				}else {
					log.logLine(Testname, true, "failed to click Proceed button of 2nd confirmation page");
					throw new Exception("failed to click Proceed button of 2nd confirmation page");
				}
			}
			else {
				negativeCase(Testname, firstWinHandle, "", "Clicking on the PIVOT directtodoc link radio button in client/app/Tool Admin is failed");
			}


			Thread.sleep(2000);
			String confirmText3 =test.readColumnData("confText3", sheetname);

			if (doesElementExist2(properties.getProperty("confpopupcontent3"), 5)) {
				String confpopuptxt = driver.findElement(By.cssSelector(properties.getProperty("confpopupcontent3"))).getText();
				if(confpopuptxt.contains(confirmText3)){
					Thread.sleep(1000);
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("proceed")));
					for (WebElement lnk:selopts) {
						if (lnk.getText().equals("OK")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(2000);
							log.logLine(Testname, false, "Clicked on Proceed button of 3rd confirmation page");
							break;
						}
					}
				}else {
					log.logLine(Testname, true, "failed to click Proceed button of 3rd confirmation page");
					throw new Exception("failed to click Proceed button of 3rd confirmation page");
				}
			}
			else {
				negativeCase(Testname, firstWinHandle, "", "Clicking on the PIVOT directtodoc link radio button in client/app/Tool Admin is failed");
			}
			
			

		    Thread.sleep(2000);
		    if (doesElementExist2(properties.getProperty("Loginrequired"), 5)) {
		        WebElement chkboxdocAttach = driver.findElement(By.cssSelector(properties.getProperty("Loginrequired")));
		        
		        if ( chkboxdocAttach.isSelected())
		         {
		        	log.logLine(Testname, false, "Document Attachment checkbox is already selected in eNotification Settings in client app admin");
		         }else{
		        	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxdocAttach);
		        	 Thread.sleep(2000);
		         }
		         log.logLine(Testname, false, "Selected the Login Required checkbox is sucessfull");
		    } else {
		    	 negativeCase(Testname, firstWinHandle, "", "Selected the Login Required checkbox is failed");	         
		    }
		    
		    
		   /* if (doesElementExist2(properties.getProperty("eDelApplSpecificdata"), 5)) {
			    WebElement Lgnexpry = driver.findElement(By.cssSelector(properties.getProperty("eDelApplSpecificdata")));
			    Lgnexpry.click();
			    Lgnexpry.clear();
			    Lgnexpry.sendKeys("14");
			    Thread.sleep(2000);
			   	log.logLine(Testname, false, "Clicked on the eDelivery Client Overrides tab under view pivot information");
			} else {
				negativeCase(Testname, firstWinHandle, "", "Clicking on the eDelivery Client Overrides tab under view pivot information is failed");
			}
		    */

			
		    if (doesElementExist2(properties.getProperty("edelAppdataupdatebtn"), 5)) {
	    	    WebElement edeldataedit = driver.findElement(By.cssSelector(properties.getProperty("edelAppdataupdatebtn")));
	    	    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", edeldataedit);
	    	    waitUntilRetrive();
	    	    Thread.sleep(2000);
	    	   	log.logLine(Testname, false, "Clicked on the update button  under the eDelivery Application Specific Data tab");
	    	} else {
	    		negativeCase(Testname, firstWinHandle, "", "Clicking on the update button  under the eDelivery Application Specific Data tab is failed");
	    	}
		
	}

	
	public void DirecttodocLoginnotrequired(String Testname,String firstWinHandle) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();


		if (doesElementExist2(properties.getProperty("eDelApplSpecificdata"), 5)) {
		    WebElement eDelspecidata = driver.findElement(By.cssSelector(properties.getProperty("eDelApplSpecificdata")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelspecidata);
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Clicked on the eDelivery Client Overrides tab under view pivot information");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the eDelivery Client Overrides tab under view pivot information is failed");
		}
	    
		if (doesElementExist(properties.getProperty("edelAppdataEdit"), 5)) {
		    WebElement edeldataedit = driver.findElement(By.xpath(properties.getProperty("edelAppdataEdit")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", edeldataedit);
		    Thread.sleep(1000);
		    waitUntilRetrive();
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Clicked on the edit button under the eDelivery Application Specific Data tab");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the edit button under the eDelivery Application Specific Data tab is failed");
	   	}
	    
		 if (doesElementExist2(properties.getProperty("CltappDocmntNotification"), 5)) {
		        WebElement chkboxdocnotifcn = driver.findElement(By.cssSelector(properties.getProperty("CltappDocmntNotification")));
		        
		        if ( chkboxdocnotifcn.isSelected())
		         {
		        	log.logLine(Testname, false, "DocumentNotification checkbox is already selected in eNotification Settings in client app admin");
		         }else{
		        	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxdocnotifcn);
		        	 Thread.sleep(2000);
		         }
		         log.logLine(Testname, false, "Selected the DocumentNotification check box in eNotification Settings in client app admin sucessfull");
		    } else {
		    	 negativeCase(Testname, firstWinHandle, "", "Selecting the DocumentNotification check box in eNotification Settings in client app admin is failed");	         
		    }
		    
		    Thread.sleep(2000);
		    if (doesElementExist(properties.getProperty("DirecttoDoc"), 5)) {
		    	WebElement chkboxdocAttach = driver.findElement(By.xpath(properties.getProperty("DirecttoDoc")));
		    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxdocAttach);
		    	Thread.sleep(2000);
		    } else {
		    	negativeCase(Testname, firstWinHandle, "", "Selecting the Link to Login Page radio button in eNotification Settings in client app admin is failed");	         
		    }
		    
		    /*if (doesElementExist2(properties.getProperty("ConfirmPopup"), 5)) {
	    	    WebElement cnfpopup = driver.findElement(By.cssSelector(properties.getProperty("ConfirmPopup")));
	    	    String Popup=driver.findElement(By.cssSelector(properties.getProperty("ConfirmPopup"))).getText();
	    	    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnfpopup);
	    	    Thread.sleep(2000);
	    	   	log.logLine(Testname, false, "Clicked on the 1st Confirmation pop up");
	    	} else {
	    		negativeCase(Testname, firstWinHandle, "", "Clicked on the 1st Confirmation pop up is failed");
	    	}
		    
		    if (doesElementExist2(properties.getProperty("ConfirmPopup"), 5)) {
	    	    WebElement cnfpopup = driver.findElement(By.cssSelector(properties.getProperty("ConfirmPopup")));
	    	    String Popup1=driver.findElement(By.cssSelector(properties.getProperty("ConfirmPopup"))).getText();
	    	    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnfpopup);
	    	    Thread.sleep(2000);
	    	   	log.logLine(Testname, false, "Clicked on the 2nd Confirmation pop up");
	    	} else {
	    		negativeCase(Testname, firstWinHandle, "", "Clicked on the 2nd Confirmation pop up is failed");
	    	}
		    
		    if (doesElementExist2(properties.getProperty("ConfirmPopup"), 5)) {
	    	    WebElement cnfpopup = driver.findElement(By.cssSelector(properties.getProperty("ConfirmPopup")));
	    	    String Popup2=driver.findElement(By.cssSelector(properties.getProperty("ConfirmPopup"))).getText();

	    	    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnfpopup);
	    	    Thread.sleep(2000);
	    	   	log.logLine(Testname, false, "Clicked on the 3rd Confirmation pop up");
	    	} else {
	    		negativeCase(Testname, firstWinHandle, "", "Clicked on the 3rd Confirmation pop up is failed");
	    	}*/
		    
			String confirmText1 = test.readColumnData("confText1", sheetname);
  
			if (doesElementExist2(properties.getProperty("confirmnpopup1"), 5)) {
				String confpopuptxt = driver.findElement(By.cssSelector(properties.getProperty("confpopupcontent1"))).getText();
				if(confpopuptxt.equalsIgnoreCase(confirmText1)){
					Thread.sleep(1000);
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("proceed")));
					for (WebElement lnk:selopts) {
						if (lnk.getText().equals("Proceed")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(2000);
							log.logLine(Testname, false, "Clicked on Proceed button of 1st confirmation page");
							break;
						}
					}
				}else {
					log.logLine(Testname, true, "Click Proceed button of 1st confirmation page");
					throw new Exception("Clicked Proceed button of 1st confirmation page");
				}
			}
			else {
				negativeCase(Testname, firstWinHandle, "", "Clicking on the PIVOT directtodoc link radio button in client/app/Tool Admin is failed");
			}

			Thread.sleep(2000);
			String confirmText2 = test.readColumnData("confText2", sheetname);

			if (doesElementExist2(properties.getProperty("confpopupcontent2"), 5)) {
				String confpopuptxt = driver.findElement(By.cssSelector(properties.getProperty("confpopupcontent2"))).getText();
				if(confpopuptxt.equalsIgnoreCase(confirmText2)){
					Thread.sleep(1000);
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("proceed")));
					for (WebElement lnk:selopts) {
						if (lnk.getText().equals("Proceed")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(2000);
							log.logLine(Testname, false, "Clicked on Proceed button of 2nd confirmation page");
							break;
						}
					}
				}else {
					log.logLine(Testname, true, "failed to click Proceed button of 2nd confirmation page");
					throw new Exception("failed to click Proceed button of 2nd confirmation page");
				}
			}
			else {
				negativeCase(Testname, firstWinHandle, "", "Clicking on the PIVOT directtodoc link radio button in client/app/Tool Admin is failed");
			}


			Thread.sleep(2000);
			String confirmText3 =test.readColumnData("confText3", sheetname);

			if (doesElementExist2(properties.getProperty("confpopupcontent3"), 5)) {
				String confpopuptxt = driver.findElement(By.cssSelector(properties.getProperty("confpopupcontent3"))).getText();
				if(confpopuptxt.contains(confirmText3)){
					Thread.sleep(1000);
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("proceed")));
					for (WebElement lnk:selopts) {
						if (lnk.getText().equals("OK")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(2000);
							log.logLine(Testname, false, "Clicked on Proceed button of 3rd confirmation page");
							break;
						}
					}
				}else {
					log.logLine(Testname, true, "failed to click Proceed button of 3rd confirmation page");
					throw new Exception("failed to click Proceed button of 3rd confirmation page");
				}
			}
			else {
				negativeCase(Testname, firstWinHandle, "", "Clicking on the PIVOT directtodoc link radio button in client/app/Tool Admin is failed");
			}
			
			

			Thread.sleep(2000);
			if (doesElementExist2(properties.getProperty("Loginrequired"), 5)) {
				WebElement chkboxdocAttach = driver.findElement(By.cssSelector(properties.getProperty("Loginrequired")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxdocAttach);
				Thread.sleep(2000);
			} else {
				negativeCase(Testname, firstWinHandle, "", "Selected the Login Required checkbox is failed");	         
			}
		    
		    
		   /* if (doesElementExist2(properties.getProperty("eDelApplSpecificdata"), 5)) {
			    WebElement Lgnexpry = driver.findElement(By.cssSelector(properties.getProperty("eDelApplSpecificdata")));
			    Lgnexpry.click();
			    Lgnexpry.clear();
			    Lgnexpry.sendKeys("14");
			    Thread.sleep(2000);
			   	log.logLine(Testname, false, "Clicked on the eDelivery Client Overrides tab under view pivot information");
			} else {
				negativeCase(Testname, firstWinHandle, "", "Clicking on the eDelivery Client Overrides tab under view pivot information is failed");
			}
		    */
			
			Thread.sleep(2000);
			String confirmText4 = test.readColumnData("confText4", sheetname);

			if (doesElementExist2(properties.getProperty("confpopupcontent4"), 5)) {
				String confpopuptxt = driver.findElement(By.cssSelector(properties.getProperty("confpopupcontent4"))).getText();
		
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("proceed")));
					for (WebElement lnk:selopts) {
						if (lnk.getText().equals("Proceed")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(2000);
							log.logLine(Testname, false, "Clicked on Proceed button of 4th confirmation page");
							break;
						}

					}
			}
			else {
				negativeCase(Testname, firstWinHandle, "", "Clicking on the PIVOT directtodoc link radio button in client/app/Tool Admin is failed");
			}


			Thread.sleep(2000);
			String confirmText5 =test.readColumnData("confText5", sheetname);

			if (doesElementExist2(properties.getProperty("confpopupcontent5"), 5)) {
				String confpopuptxt = driver.findElement(By.cssSelector(properties.getProperty("confpopupcontent5"))).getText();
				
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("proceed")));
					for (WebElement lnk:selopts) {
						if (lnk.getText().equals("OK")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(2000);
							log.logLine(Testname, false, "Clicked on Proceed button of 5th confirmation page");
							break;
						}

					}
			}
			else {
				negativeCase(Testname, firstWinHandle, "", "Clicking on the PIVOT directtodoc link radio button in client/app/Tool Admin is failed");
			}
			

			
		    if (doesElementExist2(properties.getProperty("edelAppdataupdatebtn"), 5)) {
	    	    WebElement edeldataedit = driver.findElement(By.cssSelector(properties.getProperty("edelAppdataupdatebtn")));
	    	    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", edeldataedit);
	    	    waitUntilRetrive();
	    	    Thread.sleep(2000);
	    	   	log.logLine(Testname, false, "Clicked on the update button  under the eDelivery Application Specific Data tab");
	    	} else {
	    		negativeCase(Testname, firstWinHandle, "", "Clicking on the update button  under the eDelivery Application Specific Data tab is failed");
	    	}
	}
	
	public boolean Clientapp(String AccNo, String Testname) throws Exception {


		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(1000);
		driver.switchTo().defaultContent();



		Actions builder = new Actions(driver);	
		WebElement mnuElement = driver.findElement(By.xpath(properties.getProperty("AdminMenu")));	
		if (doesElementExist(properties.getProperty("AdminMenu"), 10)) {	
			// Move cursor to the Main Menu Element 
			builder.moveToElement(mnuElement).perform();	
			// Clicking on the Hidden SubMenu 
			Thread.sleep(1000);
			if (doesElementExist2(properties.getProperty("Adminlnk"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Adminlnk")));
				for (WebElement lnk:selopts) {
					Thread.sleep(1000);
					if (lnk.getText().contains("HA Admin")) {
						Thread.sleep(1000);
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



		Thread.sleep(25000);
		Set<String> handles = driver.getWindowHandles();
		firstWinHandle = driver.getWindowHandle(); 
		handles.remove(firstWinHandle);


		boolean browserexist = handles.iterator().hasNext();

		if (browserexist) {
			secondWinHandle=handles.iterator().next();
			if (secondWinHandle != firstWinHandle){			    	
				//Switch control to new window
				driver.switchTo().window(secondWinHandle);


				if (doesElementExist(properties.getProperty("Clientapplnk"), 5)) {
					WebElement clntappmenu = driver.findElement(By.xpath(properties.getProperty("Clientapplnk")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", clntappmenu);
					Thread.sleep(3000);
					log.logLine(Testname, false, "Navigating to Admin - Clientapp admin link..");				} else {
					negativeCase(Testname, firstWinHandle, "", "Navigating to Admin - Clientapp admin link is failed");					
				}

				WebElement retelm9 = waitForElement(properties.getProperty("AppNamefld"));
				log.logLine(Testname, false, "AppNamefld is found on the page..");
	

				/*String ClntName = test.readColumnData("ClientName", sheetname);				
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
				}*/

				//Entering Application name in ApplicationId text box
				//String AppName = test.readColumnData("ApplicationName", sheetname);
				  
				String Applcnname="SKB0100";
			
				if (doesElementExist2(properties.getProperty("ApplicationId"), 5)) {
					WebElement applid = driver.findElement(By.cssSelector(properties.getProperty("ApplicationId")));

					action.sendKeys(applid, Applcnname).perform();
					//applid.sendKeys(AppName);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Entered the application name "+Applcnname+" in the Application name text field in Client/App tool");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Entering the application name "+Applcnname+" in the Application name text field in Client/App tool is failed");
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

			}
		}else{
			log.logLine(Testname, false, "Click on Admin link is Failed");
		}
		return true;
	}
	
	
	public void GmailLogin(String Testname) throws Exception {

		driver.get("https://www.googlemail.com");
		Thread.sleep(9000);

		if (doesElementExist2(properties.getProperty("Gmail_ID"), 5)) {
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Gmail_ID")));
			if (!(gmailid.getAttribute("class").equalsIgnoreCase("email-input hidden"))) {
				gmailid.clear();
				gmailid.sendKeys("automationpivot@gmail.com");
				log.logLine(Testname, false, "Entering the Gamil ID..");
				Thread.sleep(2000);
			
			}
			
		}else if (doesElementExist2(properties.getProperty("SrchMail"), 5)) {
			log.logLine(Testname, false, "Gmail already logged in..");
			
		} else {
			log.logLine(Testname, true, "Entering the Gamil ID is failed");
			driver.close();
			throw new Exception("Entering the Gamil ID is failed");
		}
	
		
		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Gmail_Passwd"), 5)) {
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Gmail_Passwd")));
			gmailid.clear();
			gmailid.sendKeys("miracle@123");
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entering the Gamil password..");

		} else if (doesElementExist2(properties.getProperty("Gmail_NxtBtn"), 5)) {
			WebElement gmailnxt = driver.findElement(By.cssSelector(properties.getProperty("Gmail_NxtBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", gmailnxt);
			Thread.sleep(4000);

			if (doesElementExist2(properties.getProperty("Gmail_Passwd"), 5)) {
				WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Gmail_Passwd")));
				gmailid.clear();
				gmailid.sendKeys("miracle@123");
				Thread.sleep(2000);
				log.logLine(Testname, false,"Entering the Gamil password..");

			} else {
				log.logLine(Testname, true,"Entering the password ID is failed");
				throw new Exception("Entering the Gamil password is failed");
			}
			
		}else if (doesElementExist2(properties.getProperty("SrchMail"), 5)) {
			log.logLine(Testname, false, "Gmail already logged in..");

		} else {
			log.logLine(Testname, true,"Entering the password ID is failed");
			throw new Exception("Entering the Gamil password is failed");
		}

		Thread.sleep(1000);
		if (doesElementExist2(properties.getProperty("Gmail_SignIn"), 5)) {
			WebElement gmailSign = driver.findElement(By.cssSelector(properties.getProperty("Gmail_SignIn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", gmailSign);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicked on the Gamil SignIn");
		}else if (doesElementExist2(properties.getProperty("SrchMail"), 5)) {
			log.logLine(Testname, false, "Gmail already logged in..");

		} else {
			log.logLine(Testname, true,"Clicked on the Gamil SignIn is failed");
			throw new Exception("Clicked on the Gamil SignIn is failed");
		}

		long timenow = System.currentTimeMillis();
		Thread.sleep(20000);
		
		if (doesElementExist2(properties.getProperty("SrchMail"), 5)) {
			WebElement srchfld = driver.findElement(By.cssSelector(properties.getProperty("SrchMail")));
			srchfld.sendKeys(Subject);
			log.logLine(Testname, false, "Entering the Search text..");
			srchfld.sendKeys(Keys.ENTER);
			Thread.sleep(4000);
		} else {
			log.logLine(Testname, true, "Entering the Search text is failed");
		}

		if (doesElementExist2(properties.getProperty("Documentmail"), 5)) {
			WebElement OpenMail = driver.findElement(By.cssSelector(properties.getProperty("Documentmail")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", OpenMail);
			Thread.sleep(4000);
			log.logLine(Testname, false,"Clicked on the First email to read");

		} else {
			log.logLine(Testname, true,"Clicking on the Plain Template Notification email to read is failed");
		}
		
		if (doesElementExist2(properties.getProperty("GmailContent"), 5)) {
			WebElement contentBody = driver.findElement(By.cssSelector(properties.getProperty("GmailContent")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", contentBody);
			Thread.sleep(2000);
			log.logLine(Testname, false,"Clicked on the Document Link is successfull");

		} else {
			log.logLine(Testname, false,"Clicked on the Document Link is Failed");
		}
		
		Thread.sleep(15000);
		Set<String> handles2 = driver.getWindowHandles();
		handles2.remove(firstWinHandle);
		handles2.remove(secondWinHandle);

 
		boolean browserexist2 = handles2.iterator().hasNext();		    
		if (browserexist2) {

			thirdWinHandle = handles2.iterator().next();
			if (thirdWinHandle != firstWinHandle && thirdWinHandle != secondWinHandle){			    	
				//Switch control to new window
				driver.switchTo().window(thirdWinHandle);
				
				Thread.sleep(10000);
				if (doesElementExist2(properties.getProperty("UserName"), 5)) {
					WebElement Usernme = driver.findElement(By.cssSelector(properties.getProperty("UserName")));
					Usernme.sendKeys("Skb003");
					Thread.sleep(2000);
					log.logLine(Testname, false, "Entered the User name under edelivery application");
					
				} else if (doesElementExist(properties.getProperty("UserName1"), 5)) {
					WebElement Usernme = driver.findElement(By.xpath(properties.getProperty("UserName1")));
					Usernme.sendKeys("Skb003");
					Thread.sleep(2000);
					log.logLine(Testname, false, "Entered the User name under edelivery application");
				}
					else {
			
					log.logLine(Testname, true, "Entering the User name under edelivery application failed");
					//throw new Exception("Entering the User name under edelivery application failed");
				}
				
				
		        Thread.sleep(5000);
		        if (doesElementExist2(properties.getProperty("Password"), 5)) {
					WebElement Psswd = driver.findElement(By.cssSelector(properties.getProperty("Password")));
					Psswd.sendKeys("Spring*5");
					Thread.sleep(2000);
					log.logLine(Testname, false, "Entered the Password under edelivery application");
			
				} else if (doesElementExist(properties.getProperty("Password1"), 5)) {
					WebElement Psswd = driver.findElement(By.xpath(properties.getProperty("Password1")));
					Psswd.sendKeys("Spring*5");
					log.logLine(Testname, false, "Entered the Password under edelivery application");
				}
		        
		        else {
					log.logLine(Testname, true, "Entering the Password under edelivery application failed");
					//throw new Exception("Entering the Password under edelivery application failed");
				}
		        
		       //Click on Login Button
		        Thread.sleep(5000);
		    	if (doesElementExist2(properties.getProperty("LoginBtn"), 5)) {
					WebElement Lgnbtn = driver.findElement(By.cssSelector(properties.getProperty("LoginBtn")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Lgnbtn);
					Thread.sleep(4000);
					log.logLine(Testname, false, "Click on Login button is successful");
					
				} else if (doesElementExist(properties.getProperty("LoginBtn1"), 5)) {
					WebElement Lgnbtn = driver.findElement(By.xpath(properties.getProperty("LoginBtn1")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Lgnbtn);
					Thread.sleep(4000);
					log.logLine(Testname, false, "Click on Login button is successful");
				}  
		    	else {
					log.logLine(Testname, true, "Click on Login button is unsuccessful");
					//throw new Exception("Click on Login button is unsuccessful");
				}
		    	
		    	Thread.sleep(5000);
		    	if (doesElementExist(properties.getProperty("Logout"), 5)) {
					WebElement Logout = driver.findElement(By.xpath(properties.getProperty("Logout")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Logout);
					Thread.sleep(4000);
					log.logLine(Testname, false, "Click on Logout Button is successful");
				} else {
					log.logLine(Testname, false, "Application downloaded directly");
						
				}
		    	
		    	driver.close();
		    	driver.switchTo().window(secondWinHandle);
	
			}

	
	} else {
		log.logLine(Testname, false,"User already logged in to Skyblue application, Hence document downloaded directly");
	}
	
	}
	
	public void GmailLogin1(String Testname) throws Exception {

		driver.get("https://www.googlemail.com");
		Thread.sleep(9000);

		if (doesElementExist2(properties.getProperty("Gmail_ID"), 5)) {
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Gmail_ID")));
			if (!(gmailid.getAttribute("class").equalsIgnoreCase("email-input hidden"))) {
				gmailid.clear();
				gmailid.sendKeys("automationpivot@gmail.com");
				Thread.sleep(2000);
				log.logLine(Testname, false, "Entering the Gamil ID..");
			
			}
			
		}else if (doesElementExist2(properties.getProperty("SrchMail"), 5)) {
			log.logLine(Testname, false, "Gmail already logged in..");
			
		} else {
			log.logLine(Testname, true, "Entering the Gamil ID is failed");
			driver.close();
			throw new Exception("Entering the Gamil ID is failed");
		}
	
		
		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Gmail_Passwd"), 5)) {
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Gmail_Passwd")));
			gmailid.clear();
			gmailid.sendKeys("miracle@123");
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entering the Gamil password..");

		} else if (doesElementExist2(properties.getProperty("Gmail_NxtBtn"), 5)) {
			WebElement gmailnxt = driver.findElement(By.cssSelector(properties.getProperty("Gmail_NxtBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", gmailnxt);
			Thread.sleep(4000);

			if (doesElementExist2(properties.getProperty("Gmail_Passwd"), 5)) {
				WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Gmail_Passwd")));
				gmailid.clear();
				gmailid.sendKeys("miracle@123");
				Thread.sleep(2000);
				log.logLine(Testname, false,"Entering the Gamil password..");

			} else {
				log.logLine(Testname, true,"Entering the password ID is failed");
				throw new Exception("Entering the Gamil password is failed");
			}
			
		}else if (doesElementExist2(properties.getProperty("SrchMail"), 5)) {
			log.logLine(Testname, false, "Gmail already logged in..");

		} else {
			log.logLine(Testname, true,"Entering the password ID is failed");
			throw new Exception("Entering the Gamil password is failed");
		}

		Thread.sleep(1000);
		if (doesElementExist2(properties.getProperty("Gmail_SignIn"), 5)) {
			WebElement gmailSign = driver.findElement(By.cssSelector(properties.getProperty("Gmail_SignIn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", gmailSign);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the Gamil SignIn");
		}else if (doesElementExist2(properties.getProperty("SrchMail"), 5)) {
			log.logLine(Testname, false, "Gmail already logged in..");

		} else {
			log.logLine(Testname, true,"Clicked on the Gamil SignIn is failed");
			throw new Exception("Clicked on the Gamil SignIn is failed");
		}

		long timenow = System.currentTimeMillis();
		Thread.sleep(10000);
		
		if (doesElementExist2(properties.getProperty("SrchMail"), 5)) {
			WebElement srchfld = driver.findElement(By.cssSelector(properties.getProperty("SrchMail")));
			srchfld.sendKeys(Subject);
			log.logLine(Testname, false, "Entering the Search text..");
			srchfld.sendKeys(Keys.ENTER);
			Thread.sleep(2000);
		} else {
			log.logLine(Testname, true, "Entering the Search text is failed");
		}

		if (doesElementExist2(properties.getProperty("Documentmail"), 5)) {
			WebElement OpenMail = driver.findElement(By.cssSelector(properties.getProperty("Documentmail")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", OpenMail);
			Thread.sleep(4000);
			log.logLine(Testname, false,"Clicked on the First email to read");

		} else {
			log.logLine(Testname, true,"Clicking on the Plain Template Notification email to read is failed");
		}
		
		if (doesElementExist2(properties.getProperty("GmailContent"), 5)) {
			WebElement contentBody = driver.findElement(By.cssSelector(properties.getProperty("GmailContent")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", contentBody);
			Thread.sleep(4000);
			log.logLine(Testname, false,"Clicked on the Document Link is successfull");

		} else {
			log.logLine(Testname, false,"Clicked on the Document Link is Failed");
		}
		
		Thread.sleep(15000);
		Set<String> handles2 = driver.getWindowHandles();
		handles2.remove(firstWinHandle);
		handles2.remove(secondWinHandle);

 
		boolean browserexist2 = handles2.iterator().hasNext();		    
		if (browserexist2) {

			thirdWinHandle = handles2.iterator().next();
			if (thirdWinHandle != firstWinHandle && thirdWinHandle != secondWinHandle){			    	
				//Switch control to new window
				driver.switchTo().window(thirdWinHandle);
		
				if (doesElementExist2(properties.getProperty("UserName"), 5)) {
					WebElement Usernme = driver.findElement(By.cssSelector(properties.getProperty("UserName")));
					Usernme.sendKeys("Skb003");
					Thread.sleep(2000);
					log.logLine(Testname, false, "Entered the User name under edelivery application");
					
				} else if (doesElementExist(properties.getProperty("UserName1"), 5)) {
					WebElement Usernme = driver.findElement(By.xpath(properties.getProperty("UserName1")));
					Usernme.sendKeys("Skb003");
					Thread.sleep(2000);
					log.logLine(Testname, false, "Entered the User name under edelivery application");
				}
					else {
			
					log.logLine(Testname, true, "Entering the User name under edelivery application failed");
					//throw new Exception("Entering the User name under edelivery application failed");
				}
				
				
		        Thread.sleep(5000);
		        if (doesElementExist2(properties.getProperty("Password"), 5)) {
					WebElement Psswd = driver.findElement(By.cssSelector(properties.getProperty("Password")));
					Psswd.sendKeys("Spring*5");
					Thread.sleep(2000);
					log.logLine(Testname, false, "Entered the Password under edelivery application");
			
				} else if (doesElementExist(properties.getProperty("Password1"), 5)) {
					WebElement Psswd = driver.findElement(By.xpath(properties.getProperty("Password1")));
					Psswd.sendKeys("Spring*5");
					Thread.sleep(2000);
					log.logLine(Testname, false, "Entered the Password under edelivery application");
				}
		        
		        else {
					log.logLine(Testname, true, "Entering the Password under edelivery application failed");
					//throw new Exception("Entering the Password under edelivery application failed");
				}
		        
		       //Click on Login Button
		        Thread.sleep(5000);
		    	if (doesElementExist2(properties.getProperty("LoginBtn"), 5)) {
					WebElement Lgnbtn = driver.findElement(By.cssSelector(properties.getProperty("LoginBtn")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Lgnbtn);
					Thread.sleep(4000);
					log.logLine(Testname, false, "Click on Login button is successful");
					
				} else if (doesElementExist(properties.getProperty("LoginBtn1"), 5)) {
					WebElement Lgnbtn = driver.findElement(By.xpath(properties.getProperty("LoginBtn1")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Lgnbtn);
					Thread.sleep(4000);
					log.logLine(Testname, false, "Click on Login button is successful");
				}  
		    	else {
					log.logLine(Testname, true, "Click on Login button is unsuccessful");
					//throw new Exception("Click on Login button is unsuccessful");
				}
		    	
		    	/*if (doesElementExist(properties.getProperty("Logout"), 5)) {
					WebElement Logout = driver.findElement(By.xpath(properties.getProperty("Logout")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Logout);
					Thread.sleep(4000);
					log.logLine(Testname, false, "Click on Logout Button is successful");
				} else {
					log.logLine(Testname, false, "Application downloaded directly");
						
				}*/
		    	
		    	driver.close();
		    	driver.switchTo().window(secondWinHandle);
	
			}

	
	} else {
		log.logLine(Testname, false,"User already logged in to Skyblue application, Hence document downloaded directly");
	}
	
	}
	
	
	public void GmailLogin2(String Testname) throws Exception {

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("SrchMail"), 5)) {
			WebElement srchfld = driver.findElement(By.cssSelector(properties.getProperty("SrchMail")));
			srchfld.sendKeys(Subject);
			log.logLine(Testname, false, "Entering the Search text..");
			srchfld.sendKeys(Keys.ENTER);
			Thread.sleep(4000);
		} else {
			log.logLine(Testname, true, "Entering the Search text is failed");
		}

		if (doesElementExist2(properties.getProperty("Documentmail"), 5)) {
			WebElement OpenMail = driver.findElement(By.cssSelector(properties.getProperty("Documentmail")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", OpenMail);
			Thread.sleep(4000);
			log.logLine(Testname, false,"Clicked on the First email to read");

		} else {
			log.logLine(Testname, true,"Clicking on the Plain Template Notification email to read is failed");
		}
		
		if (doesElementExist2(properties.getProperty("GmailContent"), 5)) {
			WebElement contentBody = driver.findElement(By.cssSelector(properties.getProperty("GmailContent")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", contentBody);
			Thread.sleep(4000);
			log.logLine(Testname, false,"Clicked on the Document Link is successfull");

		} else {
			log.logLine(Testname, false,"Clicked on the Document Link is Failed");
		}
		
		Thread.sleep(15000);
		Set<String> handles2 = driver.getWindowHandles();
		handles2.remove(firstWinHandle);
		handles2.remove(secondWinHandle);

 
		boolean browserexist2 = handles2.iterator().hasNext();		    
		if (browserexist2) {

			thirdWinHandle = handles2.iterator().next();
			if (thirdWinHandle != firstWinHandle && thirdWinHandle != secondWinHandle){			    	
				//Switch control to new window
				driver.switchTo().window(thirdWinHandle);
				
				log.logLine(Testname, false,"Document is downloaded sucesssfully without logging in to skyblue application");

				/*if (doesElementExist2(properties.getProperty("UserName"), 5)) {
					WebElement Usernme = driver.findElement(By.cssSelector(properties.getProperty("UserName")));
					Usernme.sendKeys("Skb003");
					log.logLine(Testname, false, "Entered the User name under edelivery application");
					
				} else if (doesElementExist(properties.getProperty("UserName1"), 5)) {
					WebElement Usernme = driver.findElement(By.xpath(properties.getProperty("UserName1")));
					Usernme.sendKeys("Skb003");
					log.logLine(Testname, false, "Entered the User name under edelivery application");
				}
					else {
			
					log.logLine(Testname, true, "Entering the User name under edelivery application failed");
					//throw new Exception("Entering the User name under edelivery application failed");
				}
				
				
		        Thread.sleep(5000);
		        if (doesElementExist2(properties.getProperty("Password"), 5)) {
					WebElement Psswd = driver.findElement(By.cssSelector(properties.getProperty("Password")));
					Psswd.sendKeys("Spring*5");
					log.logLine(Testname, false, "Entered the Password under edelivery application");
			
				} else if (doesElementExist(properties.getProperty("Password1"), 5)) {
					WebElement Psswd = driver.findElement(By.xpath(properties.getProperty("Password1")));
					Psswd.sendKeys("Spring*5");
					log.logLine(Testname, false, "Entered the Password under edelivery application");
				}
		        
		        else {
					log.logLine(Testname, true, "Entering the Password under edelivery application failed");
					//throw new Exception("Entering the Password under edelivery application failed");
				}
		        
		       //Click on Login Button
		        Thread.sleep(5000);
		    	if (doesElementExist2(properties.getProperty("LoginBtn"), 5)) {
					WebElement Lgnbtn = driver.findElement(By.cssSelector(properties.getProperty("LoginBtn")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Lgnbtn);
					Thread.sleep(4000);
					log.logLine(Testname, false, "Click on Login button is successful");
					
				} else if (doesElementExist(properties.getProperty("LoginBtn1"), 5)) {
					WebElement Lgnbtn = driver.findElement(By.xpath(properties.getProperty("LoginBtn1")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Lgnbtn);
					Thread.sleep(4000);
					log.logLine(Testname, false, "Click on Login button is successful");
				}  
		    	else {
					log.logLine(Testname, true, "Skyblue Application dint open as user already logged in");
					//throw new Exception("Click on Login button is unsuccessful");
				}
		    	
		    	if (doesElementExist(properties.getProperty("Logout"), 5)) {
					WebElement Logout = driver.findElement(By.xpath(properties.getProperty("Logout")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Logout);
					Thread.sleep(4000);
					log.logLine(Testname, false, "Click on Logout Button is successful");
				} else {
					log.logLine(Testname, false, "Application downloaded directly");
						
				}*/
		    	
		    	driver.close();
		    	driver.switchTo().window(secondWinHandle);
	
			}

	
	} else {
		log.logLine(Testname, false,"User already logged in to Skyblue application, Hence document downloaded directly");
	}
	
	}
	
	



}



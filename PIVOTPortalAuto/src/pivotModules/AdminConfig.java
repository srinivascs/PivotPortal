package pivotModules;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import launchAuto.Initialization;
import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.ibm.icu.util.Calendar;


public class AdminConfig extends Page{
	
	public AdminConfig(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}	
	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}
	
	public static String EmailFrm;
	public static String EmailSub;
	public static String EmailBdy;	
	public static String adtMaxKeys;
    public static String adtMaxRule;
    public static String subsegmax;
    
    Actions action = new Actions(driver);

	
	public String ClientAppsrch(String AccNo, String Testname) throws Exception {
	
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
			
		    
	    /*if ((doesElementExist2(properties.getProperty("Adminlnk"), 5))) {			    
			WebElement oldpivt = driver.findElement(By.cssSelector(properties.getProperty("Adminlnk")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", oldpivt);
		
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicking on Legacy Admin..");		      
		}*/
	
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
				if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSUPER")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSITE")) 
						|| (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDCLIENT"))) {					
				
					if (doesElementExist(properties.getProperty("Clientapplnk"), 5)) {						
						log.logLine(Testname, false, "Permission Verified: RRD Super, RRD Site & RRD Client users have access to Client/App in Legacy Admin");
						
					} else {
						
						log.logLine(Testname, true, "Access denied - RRD Super, RRD Site & RRD Client users does not have access to Client/App in Legacy Admin");
						driver.close();
						driver.switchTo().window(firstWinHandle);
						return "";
					}
				} else if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTUSER")) || 
						(PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTADMIN")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDUSER"))) {
					
					if (doesElementExist(properties.getProperty("Clientapplnk"), 5)) {
						
						negativeCase(Testname, firstWinHandle, "", "RRD User, Client Admin & Client User should not have access to Client/App in Legacy Admin");
												
					} else {
						
						log.logLine(Testname, false, "Permission Verified: Client Admin, Client User & RRD User does not have permission to access to Client/App in Legacy Admin");
						driver.close();
						driver.switchTo().window(firstWinHandle);
						return ""; 
					}
					
				}			
				
				
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
				
				String ClntName = test.readColumnData("ClientName", sheetname);				
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
	    }   
	    
		return firstWinHandle;
 	}
	
	

	public boolean ChangetoSimpleSrch(String AccNo, String Testname, String firstWinHandle) throws Exception {
		
		//Clicking on the modify tool button
	    /*if (doesElementExist2(properties.getProperty("ModifyTool"), 5)) {
	    	WebElement modtool = driver.findElement(By.cssSelector(properties.getProperty("ModifyTool")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", modtool);
			
			waitUntilRetrive();			
			log.logLine(Testname, false, "Clicked on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin ");
		} else {
		    log.logLine(Testname, true, "Clicking on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin is failed");
		    throw new Exception("Clicking on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin is failed");
		}
		*/
	    
	    if (doesElementExist2(properties.getProperty("eDelApplSpecificdata"), 5)) {
		    WebElement eDelspecidata = driver.findElement(By.cssSelector(properties.getProperty("eDelApplSpecificdata")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelspecidata);
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Clicked on the eDelivery Client Overrides tab under view pivot information");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the eDelivery Client Overrides tab under view pivot information is failed");
		}
	    
		if (doesElementExist2(properties.getProperty("AppdataEdit"), 5)) {
		    WebElement edeldataedit = driver.findElement(By.cssSelector(properties.getProperty("AppdataEdit")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", edeldataedit);
		    Thread.sleep(1000);
		    waitUntilRetrive();
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Clicked on the edit button under the eDelivery Application Specific Data tab");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the edit button under the eDelivery Application Specific Data tab is failed");
	   	}
	    
	   	    	    
	    if (doesElementExist2(properties.getProperty("eprsnthostingtype"), 5)) {
			Select prsnthosttype = new Select(driver.findElement(By.cssSelector(properties.getProperty("eprsnthostingtype"))));
			prsnthosttype.selectByVisibleText("PIVOT Hosted");
			log.logLine(Testname, false, "Selecting pivot hosted option from epresent hosting type dropdown list in eDelivery Application Specific Data tab");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Selecting pivot hosted option from epresent hosting type dropdown list in eDelivery Application Specific Data tab is failed");
		}
	    
	    
	    if (doesElementExist2(properties.getProperty("eArchostingtype"), 5)) {
			Select archhosttype = new Select(driver.findElement(By.cssSelector(properties.getProperty("eArchostingtype"))));
			archhosttype.selectByVisibleText("PIVOT Hosted");
			log.logLine(Testname, false, "Selecting pivot hosted option from eArchive hosting type dropdown list in eDelivery Application Specific Data tab");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Selecting pivot hosted option from eArchive hosting type dropdown list in eDelivery Application Specific Data tab is failed");
		}

	    
	    if (doesElementExist2(properties.getProperty("DupDocOption"), 5)) {
			Select droplist = new Select(driver.findElement(By.cssSelector(properties.getProperty("DupDocOption"))));
			droplist.selectByVisibleText("Replace");
			log.logLine(Testname, false, "Selecting In Row option from the dropdown for the Field First Name SERGIO is successfull");
		} else {
			negativeCase(Testname, firstWinHandle, "", "The Option In Row has already been selected");			
		}
	    
	    
	    if (doesElementExist2(properties.getProperty("DupDocOptionText"), 5)) {
		    WebElement edeldataedit = driver.findElement(By.cssSelector(properties.getProperty("DupDocOptionText")));
		    edeldataedit.clear();
		    
		    ((JavascriptExecutor) driver).executeScript("arguments[0].value='THIS IS A REPLACE DOCUMENT TEST'", edeldataedit);
		    
		    action.sendKeys(edeldataedit, "THIS IS A REPLACE DOCUMENT TEST").perform();

		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Entering text in duplicate document option text is successful ");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Entering text in duplicate document option text is failed");	    	
		}
	    
	    
	    if (doesElementExist2(properties.getProperty("Key1"), 5)) {
			Select droplist = new Select(driver.findElement(By.cssSelector(properties.getProperty("Key1"))));
			droplist.selectByVisibleText("Generic Field 1");
			log.logLine(Testname, false, "Selecting In Row option from the dropdown for the Field First Name SERGIO is successfull");
		} else {
			negativeCase(Testname, firstWinHandle, "", "The Option In Row has already been selected");			
		}
	    
	    
		if (doesElementExist2(properties.getProperty("updatebtn"), 5)) {
		    WebElement edeldataedit = driver.findElement(By.cssSelector(properties.getProperty("updatebtn")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", edeldataedit);
		    waitUntilRetrive();
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Clicked on the update button  under the eDelivery Application Specific Data tab");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the update button  under the eDelivery Application Specific Data tab is failed");	    	
		}
	    
	    
	    //clicking on the eDeliveryClient Overrides tab
	    if (doesElementExist2(properties.getProperty("eDelClntOverride"), 5)) {
		    WebElement eDelclntOver = driver.findElement(By.cssSelector(properties.getProperty("eDelClntOverride")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelclntOver);
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Clicked on the eDelivery Client Overrides tab under view pivot information");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the eDelivery Client Overrides tab under view pivot information is failed");	    	
		}
	    
	    
	    List<WebElement> Edelopts = driver.findElements(By.cssSelector(properties.getProperty("SeleDeliver2Opt")));	
		for (WebElement lnk:Edelopts) {
			if (lnk.getText().equalsIgnoreCase("2. eArchive/ePresent Search and Result Configuration Over Ride")) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
				Thread.sleep(2000);
				log.logLine(Testname, false, "Clicked eArchive/ePresent Search and Result Configuration Over Ride tab under view pivot information ");
				break;
			}
		}
	    
		 
	    //click on edit button	    
	    if (doesElementExist2(properties.getProperty("Editbutton"), 5)) {
		    WebElement Editbutton = driver.findElement(By.cssSelector(properties.getProperty("Editbutton")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Editbutton);
		    waitUntilRetrive();
	    	log.logLine(Testname, false, "Clicked edit button under section eArchive/ePresent Search and Result Configuration Over Ride ");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking edit button under section eArchive/ePresent Search and Result Configuration Over Ride is failed");	    	
		}
	    
    
	    if (doesElementExist2(properties.getProperty("simplesearchradiobtn"), 5)) {
		    WebElement smplradiobtn = driver.findElement(By.cssSelector(properties.getProperty("simplesearchradiobtn")));
		    
		    if ( smplradiobtn.isSelected())
		     {
		    	log.logLine(Testname, false, "Simple search radiobutton is already selected");
		     }else{
		    	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", smplradiobtn);
		     }
		     log.logLine(Testname, false, "Selecting the Simple search radiobutton is failed");
	    } else {
	    	 negativeCase(Testname, firstWinHandle, "", "Selecting the Simple search radiobutton is failed");		     
		}    
	    
	    
	    //clicking on update button
	    if (doesElementExist2(properties.getProperty("Updatebtn"), 5)) {
		    WebElement Updatebtn = driver.findElement(By.cssSelector(properties.getProperty("Updatebtn")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Updatebtn);
		    waitUntilRetrive();
		    Thread.sleep(2000);
	    	log.logLine(Testname, false, "Clicked on the Update button in eArchive/ePresent Search and Result Configuration Over Ride ");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the Update button eArchive/ePresent Search and Result Configuration Over Ride is failed");
		} 
	    
	    
	    return true;	   
	    		     
	}
	
	
	public void ChangeHostedType(String AccNo, String Testname, String firstWinHandle) throws Exception {
		
		if (doesElementExist2(properties.getProperty("AppdataEdit"), 5)) {
		    WebElement edeldataedit = driver.findElement(By.cssSelector(properties.getProperty("AppdataEdit")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", edeldataedit);
		    waitUntilRetrive();
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Clicked on the edit button under the eDelivery Application Specific Data tab");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the edit button under the eDelivery Application Specific Data tab is failed");	    	
		}
	    
	    
	    	    
	    if (doesElementExist2(properties.getProperty("eprsnthostingtype"), 5)) {
			Select prsnthosttype = new Select(driver.findElement(By.cssSelector(properties.getProperty("eprsnthostingtype"))));
			prsnthosttype.selectByVisibleText("RRD Hosted");
			log.logLine(Testname, false, "Selecting pivot hosted option from epresent hosting type dropdown list in eDelivery Application Specific Data tab");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Selecting pivot hosted option from epresent hosting type dropdown list in eDelivery Application Specific Data tab is failed");
		}
	    
	    
	    if (doesElementExist2(properties.getProperty("eArchostingtype"), 5)) {
			Select archhosttype = new Select(driver.findElement(By.cssSelector(properties.getProperty("eArchostingtype"))));
			archhosttype.selectByVisibleText("RRD Hosted");
			log.logLine(Testname, false, "Selecting pivot hosted option from eArchive hosting type dropdown list in eDelivery Application Specific Data tab");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Selecting pivot hosted option from eArchive hosting type dropdown list in eDelivery Application Specific Data tab is failed");
		}
	    

	    if (doesElementExist2(properties.getProperty("updatebtn"), 5)) {
		    WebElement edeldataedit = driver.findElement(By.cssSelector(properties.getProperty("updatebtn")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", edeldataedit);
		    waitUntilRetrive();
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Clicked on the update button  under the eDelivery Application Specific Data tab");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the update button  under the eDelivery Application Specific Data tab is failed");		    
		}
		
		
	}
	
	
	public void ChangetoAdvanceSrch(String AccNo, String Testname, String firstWinHandle) throws Exception {
		//Clicking on the modify tool button
	  
		/* if (doesElementExist2(properties.getProperty("ModifyTool"), 5)) {
	    	WebElement modtool = driver.findElement(By.cssSelector(properties.getProperty("ModifyTool")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", modtool);
			Thread.sleep(1000);
			waitUntilRetrive();
			log.logLine(Testname, false, "Clicked on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin ");
		} else {
		    log.logLine(Testname, true, "Clicking on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin is failed");
		    throw new Exception("Clicking on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin is failed");
		}
		*/
	    
	    
	    //clicking on the eDeliveryClient Overrides tab
	    if (doesElementExist2(properties.getProperty("eDelClntOverride"), 5)) {
		    WebElement eDelclntOver = driver.findElement(By.cssSelector(properties.getProperty("eDelClntOverride")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelclntOver);
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Clicking on the eDelivery Client Overrides tab under view pivot information is successful");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the eDelivery Client Overrides tab under view pivot information is failed");	    	
		}
	    
	    
	    List<WebElement> Edelopts = driver.findElements(By.cssSelector(properties.getProperty("SeleDeliver2Opt")));	
		for (WebElement lnk:Edelopts) {
			if (lnk.getText().equalsIgnoreCase("2. eArchive/ePresent Search and Result Configuration Over Ride")) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
				Thread.sleep(2000);
				log.logLine(Testname, false, "Clicking eArchive/ePresent Search and Result Configuration Over Ride is successful");
				break;
			}
		}
	    
		
	    //click on edit button	    
	    if (doesElementExist2(properties.getProperty("Editbutton"), 5)) {
		    WebElement Editbutton = driver.findElement(By.cssSelector(properties.getProperty("Editbutton")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Editbutton);
		    waitUntilRetrive();
	    	log.logLine(Testname, false, "Clicking edit button under section eArchive/ePresent Search and Result Configuration Over Ride is successful");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking edit button under section eArchive/ePresent Search and Result Configuration Over Ride is failed");
	    }
	    
	    
	    //click on simple search radio button
	    if (doesElementExist2(properties.getProperty("Advancesearchradiobtn"), 5)) {
		    WebElement AdvanceRadioBtn = driver.findElement(By.cssSelector(properties.getProperty("Advancesearchradiobtn")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", AdvanceRadioBtn);
	    	Thread.sleep(1000);
	    	log.logLine(Testname, false, "Clicking advance search radio button is successful");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking advance search radio button is failed");	    	
		}
	  
	    
	    if (doesElementExist2(properties.getProperty("grtthancheckbox"), 5)) {
		    WebElement greateropertchkbox = driver.findElement(By.cssSelector(properties.getProperty("grtthancheckbox")));
		   
	    	if ( !greateropertchkbox.isSelected()) {
	    		((JavascriptExecutor) driver).executeScript("arguments[0].click()", greateropertchkbox);		    		
		     }
		     log.logLine(Testname, false, "Selected the greater than check box in advance search ");
		} else {
			 negativeCase(Testname, firstWinHandle, "", "Selecting the greater than check box in advance search is failed");			     
		}
	    
	 
	    if (doesElementExist2(properties.getProperty("lessthanckeckbox"), 5)) {
		    WebElement lessopertchkbox = driver.findElement(By.cssSelector(properties.getProperty("lessthanckeckbox")));
		    
		    if ( !lessopertchkbox.isSelected())
		     {
		    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", lessopertchkbox);		    	
		     }
		     log.logLine(Testname, false, "Selected the less than check box in advance search ");
	    } else {
	    	negativeCase(Testname, firstWinHandle, "", "Selecting the less than check box in advance search is failed");
	    }
	    
	    
	    //clicking on update button
	    if (doesElementExist2(properties.getProperty("Updatebtn"), 5)) {
		    WebElement Updatebtn = driver.findElement(By.cssSelector(properties.getProperty("Updatebtn")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Updatebtn);
		    waitUntilRetrive();
	    	log.logLine(Testname, false, "Clicked on update button in eArchive/ePresent Search and Result Configuration Over Ride ");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on update button in eArchive/ePresent Search and Result Configuration Over Ride is failed");	    	
		} 
	    
	    
	    //Close the old pivot window
	    driver.close();
	    driver.switchTo().window(firstWinHandle);
		
	}
	
		
	public void ArchiveColmsSetup(String AccNo, String Testname, String firstWinHandle) throws Exception {
		
		/*if (doesElementExist2(properties.getProperty("ModifyTool"), 5)) {
			WebElement modtool = driver.findElement(By.cssSelector(properties.getProperty("ModifyTool")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", modtool);
			Thread.sleep(1000);
			waitUntilRetrive();
			log.logLine(Testname, false, "Clicked on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin ");
		} else {
		    log.logLine(Testname, true, "Clicking on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin is failed");
		    throw new Exception("Clicking on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin is failed");
		}
		*/
		
		//clicking on the eDeliveryClient Overrides tab		
		if (doesElementExist2(properties.getProperty("eDelClntOverride"), 5)) {
			WebElement eDelclntOver = driver.findElement(By.cssSelector(properties.getProperty("eDelClntOverride")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelclntOver);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on the eDelivery Client Overrides tab under view pivot information is successfull");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the eDelivery Client Overrides tab under view pivot information is failed");			
		}
		
		
		List<WebElement> Edelopts = driver.findElements(By.cssSelector(properties.getProperty("SeleDeliver2Opt")));
		for (WebElement lnk:Edelopts) {
			if (lnk.getText().equalsIgnoreCase("3. eArchive/ePresent Generic Index Configuration Over Ride")) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
				Thread.sleep(2000);
				log.logLine(Testname, false, "Clicking eArchive/ePresent Search and Result Configuration Over Ride is successfull");
				break;
			}
		}
	
		//Checking First Name Field lable exists		
		if (doesElementExist2(properties.getProperty("FldlblFrstNme"), 5)) {
			WebElement fldfrtnme = driver.findElement(By.cssSelector(properties.getProperty("FldlblFrstNme")));
			log.logLine(Testname, false, "First Name SERGIO column exits in the generic index configuration ");
		} else {
			negativeCase(Testname, firstWinHandle, "", "First Name SERGIO column exits in the generic index configuration is failed");
		}
		
		// Clicking on Edit action button	
		if (doesElementExist2(properties.getProperty("EditActionsFN"), 5)) {
			WebElement edtactn = driver.findElement(By.cssSelector(properties.getProperty("EditActionsFN")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", edtactn);
			Thread.sleep(2000);
			waitUntilRetrive();
			log.logLine(Testname, false, "Clicking on the Edit Action button in generic index configuration");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the Edit Action button in generic index configuration is failed");
		}
		
		
		//Selecting In Row option from dropdown box		
		if (doesElementExist2(properties.getProperty("SelectInrowFN"), 5)) {
			Select droplist = new Select(driver.findElement(By.cssSelector(properties.getProperty("SelectInrowFN"))));
			droplist.selectByVisibleText("In Row");
			log.logLine(Testname, false, "Selecting In Row option from the dropdown for the Field First Name SERGIO is successfull");
		} else {
			log.logLine(Testname, true, "The Option In Row has already been selected");
			//throw new Exception("The Option In Row has already been selected");
		}
		
		
		//Clicking on Rightcheck mark		
		if (doesElementExist2(properties.getProperty("RightcheckmarkFN"), 5)) {
			WebElement rgtchmrk = driver.findElement(By.cssSelector(properties.getProperty("RightcheckmarkFN")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rgtchmrk);
			waitUntilRetrive();
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on the Rightcheckmark button is successful");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the Rightcheckmark button is failed");			
		}
		
		
		
		//Checking Last Name Field lable exists
		if (doesElementExist2(properties.getProperty("LastNme"), 5)) {
			WebElement fldfrtnme = driver.findElement(By.cssSelector(properties.getProperty("LastNme")));
			log.logLine(Testname, false, "First Name SERGIO column exits in the generic index configuration ");
		} else {
			negativeCase(Testname, firstWinHandle, "", "First Name SERGIO column exits in the generic index configuration is failed");			
		}
		
		
		// Clicking on Edit action button		
		if (doesElementExist2(properties.getProperty("EditActionsLN"), 5)) {
			WebElement edtactn = driver.findElement(By.cssSelector(properties.getProperty("EditActionsLN")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", edtactn);
			Thread.sleep(2000);
			waitUntilRetrive();
			log.logLine(Testname, false, "Clicking on the Edit Action button in generic index configuration");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the Edit Action button in generic index configuration is failed");			
		}
		
		
		//Selecting In Row option from dropdown box		
		if (doesElementExist2(properties.getProperty("SelectInrowLN"), 5)) {
			Select droplist = new Select(driver.findElement(By.cssSelector(properties.getProperty("SelectInrowLN"))));
			droplist.selectByVisibleText("In Row");
			log.logLine(Testname, false, "Selecting In Row option from the dropdown for the Field First Name SERGIO is successfull");
		} else {
			log.logLine(Testname, true, "The Option In Row has already been selected");
			//throw new Exception("The Option In Row has already been selected");
		}
		
		
		//Clicking on Rightcheck mark		
		if (doesElementExist2(properties.getProperty("RightcheckmarkLN"), 5)) {
			WebElement rgtchmrk = driver.findElement(By.cssSelector(properties.getProperty("RightcheckmarkLN")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rgtchmrk);
			waitUntilRetrive();
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on the Rightcheckmark button is successful");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the Rightcheckmark button is failed");			
		}	
		
		
		//Checking ReprintAdd Field lable exists		
		if (doesElementExist2(properties.getProperty("ReprintAdd"), 5)) {
			WebElement fldfrtnme = driver.findElement(By.cssSelector(properties.getProperty("ReprintAdd")));
			log.logLine(Testname, false, "First Name SERGIO column exits in the generic index configuration ");
		} else {
			negativeCase(Testname, firstWinHandle, "", "First Name SERGIO column exits in the generic index configuration is failed");			
		}
		
		// Clicking on Edit action button		
		if (doesElementExist2(properties.getProperty("EditActionsRA"), 5)) {
			WebElement edtactn = driver.findElement(By.cssSelector(properties.getProperty("EditActionsRA")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", edtactn);
			Thread.sleep(2000);
			waitUntilRetrive();
			log.logLine(Testname, false, "Clicking on the Edit Action button in generic index configuration");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the Edit Action button in generic index configuration is failed");			
		}
		
		
		//Selecting In Row option from dropdown box		
		if (doesElementExist2(properties.getProperty("SelectInrowRA"), 5)) {
			Select droplist = new Select(driver.findElement(By.cssSelector(properties.getProperty("SelectInrowRA"))));
			droplist.selectByVisibleText("In Row");
			log.logLine(Testname, false, "Selecting In Row option from the dropdown for the Field First Name SERGIO is successfull");
		} else {
			log.logLine(Testname, true, "The Option In Row has already been selected");
			//throw new Exception("The Option In Row has already been selected");
		}
		
		
		//Clicking on Rightcheck mark		
		if (doesElementExist2(properties.getProperty("RightcheckmarkRA"), 5)) {
			WebElement rgtchmrk = driver.findElement(By.cssSelector(properties.getProperty("RightcheckmarkRA")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rgtchmrk);
			waitUntilRetrive();
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on the Rightcheckmark button is successful");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the Rightcheckmark button is failed");			
		}		

		
		//Checking Plan Type Field lable exists
		if (doesElementExist2(properties.getProperty("PlanType"), 5)) {
			WebElement fldfrtnme = driver.findElement(By.cssSelector(properties.getProperty("PlanType")));
			log.logLine(Testname, false, "First Name SERGIO column exits in the generic index configuration ");
		} else {
			negativeCase(Testname, firstWinHandle, "", "First Name SERGIO column exits in the generic index configuration is failed");			
		}
		
		
		// Clicking on Edit action button		
		if (doesElementExist2(properties.getProperty("EditActionsPT"), 5)) {
			WebElement edtactn = driver.findElement(By.cssSelector(properties.getProperty("EditActionsPT")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", edtactn);
			Thread.sleep(2000);
			waitUntilRetrive();
			log.logLine(Testname, false, "Clicking on the Edit Action button in generic index configuration");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the Edit Action button in generic index configuration is failed");			
		}
		
		
		//Selecting In Row option from dropdown box		
		if (doesElementExist2(properties.getProperty("SelectInrowPT"), 5)) {
			Select droplist = new Select(driver.findElement(By.cssSelector(properties.getProperty("SelectInrowPT"))));
			droplist.selectByVisibleText("In Row");
			log.logLine(Testname, false, "Selecting In Row option from the dropdown for the Field First Name SERGIO is successfull");
		} else {
			log.logLine(Testname, true, "The Option In Row has already been selected");
			//throw new Exception("The Option In Row has already been selected");
		}
		
		//Clicking on Rightcheck mark		
		if (doesElementExist2(properties.getProperty("RightcheckmarkPT"), 5)) {
			WebElement rgtchmrk = driver.findElement(By.cssSelector(properties.getProperty("RightcheckmarkPT")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rgtchmrk);
			waitUntilRetrive();
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on the Rightcheckmark button is successful");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the Rightcheckmark button is failed");			
		}		
				
		//Checking Plan# Field lable exists		
		if (doesElementExist2(properties.getProperty("PlanNum"), 5)) {
			WebElement pln = driver.findElement(By.cssSelector(properties.getProperty("PlanNum")));
			log.logLine(Testname, false, "Plannum column exits in the generic index configuration ");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Plannum column exits in the generic index configuration is failed");			
		}
		
		
		// Clicking on Edit action button		
		if (doesElementExist2(properties.getProperty("EditActionsPN"), 5)) {
			WebElement edtactn = driver.findElement(By.cssSelector(properties.getProperty("EditActionsPN")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", edtactn);
			Thread.sleep(2000);
			waitUntilRetrive();
			log.logLine(Testname, false, "Clicking on the Edit Action button is successful");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the Edit Action button is failed");			
		}
		
		
		//Selecting In Row option from dropdown box		
		if (doesElementExist2(properties.getProperty("SelectInrowPN"), 5)) {
			Select drop = new Select(driver.findElement(By.cssSelector(properties.getProperty("SelectInrowPN"))));
			drop.selectByVisibleText("In Row");
			log.logLine(Testname, false, "Selecting In Row option from the dropdown for the Field PlanNum is successfull");
		} else {
			log.logLine(Testname, true, "The Option In Row has already been selected");
			//throw new Exception("The Option In Row has already been selected");
		}
		
		
		//Clicking on Rightcheck mark		
		if (doesElementExist2(properties.getProperty("RightcheckmarkPN"), 5)) {
			WebElement rgtchmrk = driver.findElement(By.cssSelector(properties.getProperty("RightcheckmarkPN")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rgtchmrk);
			waitUntilRetrive();
			log.logLine(Testname, false, "Clicking on the Rightcheckmark button is successfull");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the Rightcheckmark button is failed");			
		}
		
		
		//Checking Zipcode Field lable exists		
		if (doesElementExist2(properties.getProperty("Zipcode"), 5)) {
			WebElement stmtnum = driver.findElement(By.cssSelector(properties.getProperty("Zipcode")));
		 	log.logLine(Testname, false, "Statementnum column exits in the generic index configuration");
		 } else {
			 negativeCase(Testname, firstWinHandle, "", "Existance of the Statementnum Field lable is failed");			 
		}
		
		
		// Clicking on Edit action button		
		if (doesElementExist2(properties.getProperty("EditActionsZC"), 5)) {
			WebElement edtactn = driver.findElement(By.cssSelector(properties.getProperty("EditActionsZC")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", edtactn);
			waitUntilRetrive();
			log.logLine(Testname, false, "Clicking on the Edit Action button is successful");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the Edit Action button is failed");			
		}
		
		
		//Selecting In Row option from dropdown box		
		if (doesElementExist2(properties.getProperty("SelectInrowZC"), 5)) {
			Select droplst = new Select(driver.findElement(By.cssSelector(properties.getProperty("SelectInrowZC"))));
			droplst.selectByVisibleText("In Row");
			log.logLine(Testname, false, "Selecting In Row option from the dropdown for the Field StatemnetNum is successfull");
		} else {
		  	log.logLine(Testname, true, "The Option In Row has already been selected");
		   	//throw new Exception("The Option In Row has already been selected");
		}
		
		//Clicking on Rightcheck mark		
		if (doesElementExist2(properties.getProperty("RightcheckmarkZC"), 5)) {
			WebElement rgtchmrk = driver.findElement(By.cssSelector(properties.getProperty("RightcheckmarkZC")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rgtchmrk);
			waitUntilRetrive();
		  	log.logLine(Testname, false, "Clicking on the Rightcheckmark button is successful");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the Rightcheckmark button is failed");			 
		}
		
		//Checking Phone Field lable exists		
		if (doesElementExist2(properties.getProperty("Phone"), 5)) {
			WebElement stmtnum = driver.findElement(By.cssSelector(properties.getProperty("Phone")));
		 	log.logLine(Testname, false, "Statementnum column exits in the generic index configuration");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Existance of the Statementnum Field lable is failed");
			 
		}
		
		// Clicking on Edit action button		
		if (doesElementExist2(properties.getProperty("EditActionsPH"), 5)) {
			WebElement edtactn = driver.findElement(By.cssSelector(properties.getProperty("EditActionsPH")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", edtactn);
			waitUntilRetrive();
			log.logLine(Testname, false, "Clicking on the Edit Action button is successful");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the Edit Action button is failed");
		}
		
		
		//Selecting In Row option from dropdown box		
		if (doesElementExist2(properties.getProperty("SelectInrowPH"), 5)) {
			Select droplst = new Select(driver.findElement(By.cssSelector(properties.getProperty("SelectInrowPH"))));
			droplst.selectByVisibleText("In Row");
			log.logLine(Testname, false, "Selecting In Row option from the dropdown for the Field StatemnetNum is successfull");
		} else {
		  	log.logLine(Testname, true, "The Option In Row has already been selected");
		   	//throw new Exception("The Option In Row has already been selected");
		   	}
		
		//Clicking on Rightcheck mark
		
		if (doesElementExist2(properties.getProperty("RightcheckmarkPH"), 5)) {
			WebElement rgtchmrk = driver.findElement(By.cssSelector(properties.getProperty("RightcheckmarkPH")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rgtchmrk);
			waitUntilRetrive();
		  	log.logLine(Testname, false, "Clicking on the Rightcheckmark button is successful");
		} else {
			  negativeCase(Testname, firstWinHandle, "", "Clicking on the Rightcheckmark button is failed");			  
		}
		
		
		//Checking FinacialAdv Field lable exists		
		if (doesElementExist2(properties.getProperty("Finadv"), 5)) {
			WebElement stmtnum = driver.findElement(By.cssSelector(properties.getProperty("Finadv")));
		 	log.logLine(Testname, false, "Statementnum column exits in the generic index configuration");
		} else {
			 negativeCase(Testname, firstWinHandle, "", "Existance of the Statementnum Field lable is failed");			 
		}
		
		// Clicking on Edit action button		
		if (doesElementExist2(properties.getProperty("EditActionsFA"), 5)) {
			WebElement edtactn = driver.findElement(By.cssSelector(properties.getProperty("EditActionsFA")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", edtactn);
			waitUntilRetrive();
			log.logLine(Testname, false, "Clicking on the Edit Action button is successful");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the Edit Action button is failed");
		}
		
		//Selecting In Row option from dropdown box		
		if (doesElementExist2(properties.getProperty("SelectInrowFA"), 5)) {
			Select droplst = new Select(driver.findElement(By.cssSelector(properties.getProperty("SelectInrowFA"))));
			droplst.selectByVisibleText("In Row");
			log.logLine(Testname, false, "Selecting In Row option from the dropdown for the Field StatemnetNum is successfull");
		} else {
		  	log.logLine(Testname, true, "The Option In Row has already been selected");
		   	//throw new Exception("The Option In Row has already been selected");
		}
		
		//Clicking on Rightcheck mark		
		if (doesElementExist2(properties.getProperty("RightcheckmarkFA"), 5)) {
			WebElement rgtchmrk = driver.findElement(By.cssSelector(properties.getProperty("RightcheckmarkFA")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rgtchmrk);
			waitUntilRetrive();
		  	log.logLine(Testname, false, "Clicking on the Rightcheckmark button is successful");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the Rightcheckmark button is failed");			  
		}
		

		//Checking Statement# Field lable exists		
		if (doesElementExist2(properties.getProperty("Statementnum"), 5)) {
			WebElement stmtnum = driver.findElement(By.cssSelector(properties.getProperty("Statementnum")));
		 	log.logLine(Testname, false, "Statementnum column exits in the generic index configuration");
		} else {
			 negativeCase(Testname, firstWinHandle, "", "Existance of the Statementnum Field lable is failed");
		}
		
		// Clicking on Edit action button		
		if (doesElementExist2(properties.getProperty("EditActionsSN"), 5)) {
			WebElement edtactn = driver.findElement(By.cssSelector(properties.getProperty("EditActionsSN")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", edtactn);
			waitUntilRetrive();
			log.logLine(Testname, false, "Clicking on the Edit Action button is successful");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the Edit Action button is failed");
		}
		
		//Selecting In Row option from dropdown box		
		if (doesElementExist2(properties.getProperty("SelectInrowSN"), 5)) {
			Select droplst = new Select(driver.findElement(By.cssSelector(properties.getProperty("SelectInrowSN"))));
			droplst.selectByVisibleText("In Row");
			log.logLine(Testname, false, "Selecting In Row option from the dropdown for the Field StatemnetNum is successfull");
		} else {
		  	log.logLine(Testname, true, "The Option In Row has already been selected");
		   	//throw new Exception("The Option In Row has already been selected");
		}
		
		//Clicking on Rightcheck mark		
		if (doesElementExist2(properties.getProperty("RightcheckmarkSN"), 5)) {
			WebElement rgtchmrk = driver.findElement(By.cssSelector(properties.getProperty("RightcheckmarkSN")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rgtchmrk);
			waitUntilRetrive();
		  	log.logLine(Testname, false, "Clicking on the Rightcheckmark button is successful");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the Rightcheckmark button is failed");			  
		}
		
		ManageUsers(AccNo, Testname, firstWinHandle);	
		EditManageUserconsent(AccNo, Testname, firstWinHandle);	
		
		//Thread.sleep(2000);
    	//driver.close();   	
		
	}
	
	public static String EDITbetaPDFCreate(String Randno) throws Exception {
		
		InputStream inStream = null;
        OutputStream outStream = null;
        
        File file1, file2 = null;
        
        try { 
            file1 = new File("C:/Pivot_Portal/Test Data/AutoTestData.pdf");
            file2 = new File("C:/Pivot_Portal/Test Data/AutoPDF_"+Randno+".pdf");
            
            
            inStream = new FileInputStream(file1);
            outStream = new FileOutputStream(file2); // for override file content
 
            byte[] buffer = new byte[1024]; 
            int length;
            while ((length = inStream.read(buffer)) > 0){
                outStream.write(buffer, 0, length);
            }
 
            if (inStream != null)inStream.close();
            if (outStream != null)outStream.close();
            
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        return (file2.getAbsolutePath());	
		
	}
	
	
	public static String EDITbetaPDFCreate1(String Randno) throws Exception {
		
		InputStream inStream = null;
        OutputStream outStream = null;
        
        File file1, file2 = null;
        
        try { 
            file1 = new File("C:/Pivot_Portal/Test Data/AutoTestData3.pdf");
            file2 = new File("C:/Pivot_Portal/Test Data/AttachPDF_1_"+Randno+".pdf");
            
            
            inStream = new FileInputStream(file1);
            outStream = new FileOutputStream(file2); // for override file content
 
            byte[] buffer = new byte[1024]; 
            int length;
            while ((length = inStream.read(buffer)) > 0){
                outStream.write(buffer, 0, length);
            }
 
            if (inStream != null)inStream.close();
            if (outStream != null)outStream.close();
            
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        return (file2.getAbsolutePath());	
		
	}

public static String CreateXML(String Randno) throws Exception {
		
		InputStream inStream = null;
        OutputStream outStream = null;
        
        File file1, file2 = null;
        
        try { 
            file1 = new File("C:/Pivot_Portal/Test Data/CFA_FirstName_XML.txt");
            file2 = new File("C:/Pivot_Portal/Test Data/CFA_FirstName_XML"+Randno+".txt");
            
            
            inStream = new FileInputStream(file1);
            outStream = new FileOutputStream(file2); // for override file content
 
            byte[] buffer = new byte[1024]; 
            int length;
            while ((length = inStream.read(buffer)) > 0){
                outStream.write(buffer, 0, length);
            }
 
            if (inStream != null)inStream.close();
            if (outStream != null)outStream.close();
            
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        return (file2.getAbsolutePath());	
		
	}

public static String CreateRecipient(String Randno) throws Exception {
	
	InputStream inStream = null;
    OutputStream outStream = null;
    
    File file1, file2 = null;
    
    try { 
        file1 = new File("C:/Pivot_Portal/Test Data/CM_Recipient_AutoTest.txt");
        file2 = new File("C:/Pivot_Portal/Test Data/CM_Recipient_AutoTest"+Randno+".txt");
        
        
        inStream = new FileInputStream(file1);
        outStream = new FileOutputStream(file2); // for override file content

        byte[] buffer = new byte[1024]; 
        int length;
        while ((length = inStream.read(buffer)) > 0){
            outStream.write(buffer, 0, length);
        }

        if (inStream != null)inStream.close();
        if (outStream != null)outStream.close();
        
        
    }catch(IOException e){
        e.printStackTrace();
    }
    
    return (file2.getAbsolutePath());	
	
}

  
    public boolean clientappsettings(String AccNo, String Testname, String firstWinHandle) throws Exception {
    	
    	InputOutputData test = new InputOutputData();		
        test.setInputFile(properties.getProperty("InputDatafile"));
        String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
        
        Thread.sleep(1000);
        //driver.switchTo().defaultContent();     
         
     
    	/*if (doesElementExist2(properties.getProperty("ModifyTool"), 5)) {
    		WebElement modtool = driver.findElement(By.cssSelector(properties.getProperty("ModifyTool")));
    		((JavascriptExecutor) driver).executeScript("arguments[0].click()", modtool);
    		
    		waitUntilRetrive();			
    		log.logLine(Testname, false, "Clicked on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin ");
    	} else {
    	    log.logLine(Testname, true, "Clicking on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin is failed");
    	    throw new Exception("Clicking on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin is failed");
    	}
    	*/
    	
    	if (doesElementExist2(properties.getProperty("eDelApplSpecificdata"), 5)) {
    	    WebElement eDelspecidata = driver.findElement(By.cssSelector(properties.getProperty("eDelApplSpecificdata")));
    	    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelspecidata);
    	    Thread.sleep(2000);
    	   	log.logLine(Testname, false, "Clicked on the eDelivery Client Overrides tab under view pivot information");
    	} else {
    		log.logLine(Testname, true, "Clicking on the eDelivery Client Overrides tab under view pivot information is failed");
    		//throw new Exception("Clicking on the eDelivery Client Overrides tab under view pivot information is failed");
    	}

       //email settings
    	emailArchiveSettings(AccNo, Testname, firstWinHandle);
        
        //eConsent Settings in eDelivery Application Specific Data tab under View PIVOT eDelivery Information section
        	    
        consentSettings(AccNo, Testname, firstWinHandle);
        
        //reprint settings
        
        reprintSettings(AccNo, Testname, firstWinHandle);
        
        //reprint settings
        PDFSettingsSendMail(AccNo, Testname);
        
        return true;        
        
    }
    

    public boolean emailArchiveSettings(String AccNo, String Testname, String firstWinHandle) throws Exception { 
    	
    	InputOutputData test = new InputOutputData();		
    	test.setInputFile(properties.getProperty("InputDatafile"));
    	String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
    	
    	Thread.sleep(1000);
    	//driver.switchTo().defaultContent();
        
    	if (doesElementExist2(properties.getProperty("edelAppdataEdit"), 5)) {
    	    WebElement edeldataedit = driver.findElement(By.cssSelector(properties.getProperty("edelAppdataEdit")));
    	    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", edeldataedit);
    	    waitUntilRetrive();
    	    Thread.sleep(2000);
    	   	log.logLine(Testname, false, "Clicked on the edit button under the eDelivery Application Specific Data tab");
    	} else {
    		negativeCase(Testname, firstWinHandle, "", "Clicking on the edit button under the eDelivery Application Specific Data tab is failed");    		
    	}

	    //eArchive Mail Settings in eDelivery Application Specific Data tab under View PIVOT eDelivery Information section 
	
	    if (doesElementExist2(properties.getProperty("CltappEmailFlag"), 5)) {
	        WebElement chkboxEmailFlag = driver.findElement(By.cssSelector(properties.getProperty("CltappEmailFlag")));
	        
	        if (chkboxEmailFlag.isSelected())
	         {
	        	log.logLine(Testname, false, "EmailFlag checkbox is already selected in eArchive Mail Settings in client app admin");
	         }else{
	        	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxEmailFlag);
	        	 log.logLine(Testname, false, "Selected the EmailFlag check box in eArchive Mail Settings in client app admin is sucessfull");
	         }
	         //log.logLine(Testname, false, "Selected the EmailFlag check box in eArchive Mail Settings in client app admin is sucessfull");
	    } else {
	    	 negativeCase(Testname, firstWinHandle, "", "Selecting the EmailFlag check box in eArchive Mail Settings in client app admin is failed");
	    }
	
	    if (doesElementExist2(properties.getProperty("Cltappfourdigitpin"), 5)) {
	        WebElement chkboxfourdigitpin = driver.findElement(By.cssSelector(properties.getProperty("Cltappfourdigitpin")));
	        
	        if ( chkboxfourdigitpin.isSelected())
	         {
	        	log.logLine(Testname, false, "4 digit PIN encryption checkbox is already selected in eArchive Mail Settings in client app admin");
	         }else{
	        	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxfourdigitpin);
	         }
	         log.logLine(Testname, false, "Selected the 4 digit PIN encryption check box in eArchive Mail Settings in client app admin sucessfull");
	    } else {
	    	 negativeCase(Testname, firstWinHandle, "", "Selecting the 4 digit PIN encryption check box in eArchive Mail Settings in client app admin is failed");	         
	    }
	
	    if (doesElementExist2(properties.getProperty("CltappEmailHistory"), 5)) {
	        WebElement chkboxemalhistory = driver.findElement(By.cssSelector(properties.getProperty("CltappEmailHistory")));
	        
	        if ( chkboxemalhistory.isSelected())
	         {
	        	log.logLine(Testname, false, "Email History checkbox is already selected in eArchive Mail Settings in client app admin");
	         }else{
	        	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxemalhistory);
	         }
	         log.logLine(Testname, false, "Selected the Email History check box in eArchive Mail Settings in client app admin sucessfull");
	    } else {
	    	 negativeCase(Testname, firstWinHandle, "", "Selecting the Email History check box in eArchive Mail Settings in client app admin is failed");	         
	    }
	
	    Thread.sleep(2000);
	    String fromemailid = test.readColumnData("FromEmail", sheetname);
	    if (doesElementExist2(properties.getProperty("CltappEmailFrom"), 5)) {
    	    WebElement emlfrm = driver.findElement(By.cssSelector(properties.getProperty("CltappEmailFrom")));
    	    emlfrm.clear();
    	    if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer")) ) { 
    	    	((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + fromemailid +"')", emlfrm);
    	    } else {
    	    	emlfrm.sendKeys(fromemailid);
    	    }
    	    log.logLine(Testname, false, "Entered the from email id in the EmailFrom text field in eArchiveMailSettings under eDelivery Application Specific Data tab.");
    	} else {
    		log.logLine(Testname, true, "Entering the from email id in the EmailFrom text field in eArchiveMailSettings under eDelivery Application Specific Data tab is failed");
    	}
	
	
	    String emlsubject = test.readColumnData("SubjectEmail", sheetname); 
	    if (doesElementExist2(properties.getProperty("CltappEmailSubject"), 5)) {
	        WebElement emlsub = driver.findElement(By.cssSelector(properties.getProperty("CltappEmailSubject")));
	        //((JavascriptExecutor) driver).executeScript("arguments[0].click()", cntnme); 
	        emlsub.clear();
	        emlsub.sendKeys(emlsubject);			    
	        log.logLine(Testname, false, "Entered the email subject as "+emlsubject+" in the EmailSubject text field in eArchiveMailSettings under eDelivery Application Specific Data tab.");
	    } else {
	       	log.logLine(Testname, true, "Entering the email subject as as "+emlsubject+" in the EmailSubject text field in eArchiveMailSettings under eDelivery Application Specific Data tab is failed");
	    }
	
	
	    String emlbody = test.readColumnData("BodyEmail", sheetname); 
	    if (doesElementExist2(properties.getProperty("CltappEmailBody"), 5)) {
	        WebElement emlbdy = driver.findElement(By.cssSelector(properties.getProperty("CltappEmailBody")));
	       
	        emlbdy.clear();
	        emlbdy.sendKeys(emlbody);			    
	        log.logLine(Testname, false, "Entered the email body as "+emlbody+" in the Email body text field in eArchiveMailSettings under eDelivery Application Specific Data tab.");
	    } else {
	       	log.logLine(Testname, true, "Entering the mail body as "+emlbody+" in the Email body text field in eArchiveMailSettings under eDelivery Application Specific Data tab is failed");
	    }
	
	
	
	
	    //eNotification Settings in eDelivery Application Specific Data tab under View PIVOT eDelivery Information section
	
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
	    if (doesElementExist2(properties.getProperty("CltappDocmntAttachmnt"), 5)) {
	        WebElement chkboxdocAttach = driver.findElement(By.cssSelector(properties.getProperty("CltappDocmntAttachmnt")));
	        
	        if ( chkboxdocAttach.isSelected())
	         {
	        	log.logLine(Testname, false, "Document Attachment checkbox is already selected in eNotification Settings in client app admin");
	         }else{
	        	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxdocAttach);
	         }
	         log.logLine(Testname, false, "Selected the Document Attachment check box in eNotification Settings in client app admin sucessfull");
	    } else {
	    	 negativeCase(Testname, firstWinHandle, "", "Selecting the Document Attachment check box in eNotification Settings in client app admin is failed");	         
	    }
	
	    
	    if (doesElementExist2(properties.getProperty("CltappBounceReport"), 5)) {
	        WebElement chkboxBounceRpt = driver.findElement(By.cssSelector(properties.getProperty("CltappBounceReport")));
	        
	        if ( chkboxBounceRpt.isSelected())
	         {
	        	log.logLine(Testname, false, "Bounce report checkbox is already selected in eNotification Settings in client app admin");
	         }else{
	        	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxBounceRpt);
	         }
	         log.logLine(Testname, false, "Selected the Bounce report check box in eNotification Settings in client app admin sucessfull");
	    } else {
	    	 negativeCase(Testname, firstWinHandle, "", "Selecting the Bounce report check box in eNotification Settings in client app admin is failed");	         
	    }
	    
	
	    if (doesElementExist2(properties.getProperty("SendViaEmail"), 5)) {
	        WebElement chkboxsndviaEml = driver.findElement(By.cssSelector(properties.getProperty("SendViaEmail")));
	        
	        if ( chkboxsndviaEml.isSelected())
	         {
	        	log.logLine(Testname, false, "Send via Email checkbox is already selected in eNotification Settings in client app admin");
	         }else{
	        	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxsndviaEml);
	         }
	         log.logLine(Testname, false, "Selected the Send via Emailcheck box in eNotification Settings in client app admin sucessfull");
	    } else {
	    	 negativeCase(Testname, firstWinHandle, "", "Selecting the Send via Email check box in eNotification Settings in client app admin is failed");	         
	    }
	    
	
	    String bouncrptAddr = test.readColumnData("BounceRptEmailAddr", sheetname); 
	    if (doesElementExist2(properties.getProperty("BounceReptAddress"), 5)) {
	        WebElement bonceAddr = driver.findElement(By.cssSelector(properties.getProperty("BounceReptAddress")));
	        //((JavascriptExecutor) driver).executeScript("arguments[0].click()", cntnme);
	        bonceAddr.clear();
	        bonceAddr.sendKeys(bouncrptAddr);			    
	        log.logLine(Testname, false, "Entered the email address as "+bouncrptAddr+" in the Email address text field in eNotification Settings in client app admin  under eDelivery Application Specific Data tab.");
	    } else {
	       	log.logLine(Testname, true, "Entering the email address as "+bouncrptAddr+" in the Email address text field in eNotification Settings in client app admin  under eDelivery Application Specific Data tab is failed");
	    }
	    
	    
	    if (doesElementExist(properties.getProperty("SelectLocation"), 5)) {
		    WebElement AnyTool = driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_ctlServices_ctlSrvM_frmMain_ddlLocation']"));
		   Select select = new Select(AnyTool); 
		   List <WebElement> options = select.getOptions(); 
		   for (WebElement option: options) { 
			if (option.getText().contains("Logan")){ 
				option.click(); 
				break; 
		  }
			}
			log.logLine(Testname, false, "Selecting Location is successful.");
	    } else {
	       	log.logLine(Testname, true, "Selecting Location is failed");
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
	    
	
	    return true;
    }


    public boolean consentSettings(String AccNo, String Testname, String firstWinHandle) throws Exception {

    	InputOutputData test = new InputOutputData();		
    	test.setInputFile(properties.getProperty("InputDatafile"));
    	String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
    	
    	Thread.sleep(1000);
    	//driver.switchTo().defaultContent();
    	
    	/*if (doesElementExist2(properties.getProperty("ModifyTool"), 5)) {
			WebElement modtool = driver.findElement(By.cssSelector(properties.getProperty("ModifyTool")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", modtool);
			
			waitUntilRetrive();			
			log.logLine(Testname, false, "Clicked on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin ");
		} else {
		    log.logLine(Testname, true, "Clicking on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin is failed");
		    throw new Exception("Clicking on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin is failed");
		}
		*/
    	
    	if (doesElementExist2(properties.getProperty("edelAppdataEdit"), 5)) {
    	    WebElement edeldataedit = driver.findElement(By.cssSelector(properties.getProperty("edelAppdataEdit")));
    	    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", edeldataedit);
    	    waitUntilRetrive();
    	    Thread.sleep(2000);
    	   	log.logLine(Testname, false, "Clicked on the edit button under the eDelivery Application Specific Data tab");
    	} else {
    		negativeCase(Testname, firstWinHandle, "", "Clicking on the edit button under the eDelivery Application Specific Data tab is failed");        	
    	}
    	

    	if (doesElementExist2(properties.getProperty("ConsentEdelOption"), 5)) {
    	WebElement chkboxCsntdelvyOptn = driver.findElement(By.cssSelector(properties.getProperty("ConsentEdelOption")));
    	
	    	if ( chkboxCsntdelvyOptn.isSelected()) {
	    		log.logLine(Testname, false, "Consent Delivery Options checkbox is already selected in eNotification Settings in client app admin");
	    	 }else{
	    		 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxCsntdelvyOptn);    		 
	    	 }
	    	 log.logLine(Testname, false, "Selected the Consent Delivery Options check box in eConsent  Settings in client app admin sucessfull");
    	} else {
    		 negativeCase(Testname, firstWinHandle, "", "Selecting the Consent Delivery Options check box in eConsent  Settings in client app admin is failed");	    	 
    	}
    	
    	
    	if (doesElementExist2(properties.getProperty("ConsentNotifyOption"), 5)) {
    	    WebElement chkboxCsntNtfyOptn = driver.findElement(By.cssSelector(properties.getProperty("ConsentNotifyOption")));
    	
	    	if ( chkboxCsntNtfyOptn.isSelected()) {
	    		log.logLine(Testname, false, "Consent Notify Options checkbox is already selected in eNotification Settings in client app admin");
	    	 }else{
	    		 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxCsntNtfyOptn);
	    	 }
	    	log.logLine(Testname, false, "Selected the Consent Notify Options check box in eConsent  Settings in client app admin sucessfull");
    	} else {
    		 negativeCase(Testname, firstWinHandle, "", "Selecting the Consent Notify Options check box in eConsent  Settings in client app admin is failed");
    	}
    	
 
    	if (doesElementExist2(properties.getProperty("ConsentOnlineOnly"), 5)) {
    	WebElement chkboxCsntOnlne = driver.findElement(By.cssSelector(properties.getProperty("ConsentOnlineOnly")));
    	
	    	if ( chkboxCsntOnlne.isSelected())
	    	 {
	    		log.logLine(Testname, false, "Consent  Online Only checkbox is already selected in eNotification Settings in client app admin");
	    	 }else{
	    		 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxCsntOnlne);
	    	 }
	    	log.logLine(Testname, false, "Selected the Consent  Online Only check box in eConsent Settings in client app admin sucessfull");
    	} else {
    		negativeCase(Testname, firstWinHandle, "", "Selecting the Consent  Online Only check box in eConsent Settings in client app admin is failed");
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

    	
    	return true;
    	
    }
        //eReprint Settings
        
    public boolean reprintSettings(String AccNo, String Testname, String firstWinHandle) throws Exception {
        	
    		InputOutputData test = new InputOutputData();		
    	    test.setInputFile(properties.getProperty("InputDatafile"));
    	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
    	    
     	  	 String First =test.readColumnData("FirstLine", sheetname);
    	  	 String Second =test.readColumnData("SecondLine", sheetname);
    	  	 String Third =test.readColumnData("ThirdLine", sheetname);
    	  	 String Fourth =test.readColumnData("FourthLine", sheetname);
    	    
    	    Thread.sleep(1000);
    	    driver.switchTo().defaultContent();
        
    	    if (doesElementExist2(properties.getProperty("edelRprntEdit"), 5)) {
    		    WebElement RprntEdit = driver.findElement(By.cssSelector(properties.getProperty("edelRprntEdit")));
    		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", RprntEdit);
    		    waitUntilRetrive();
    		    Thread.sleep(2000);
    		   	log.logLine(Testname, false, "Clicked on the edit button in Reprint Settings under the eDelivery Application Specific Data tab");
    		} else {
    			negativeCase(Testname, firstWinHandle, "", "Clicking on the edit button in Reprint Settings under the eDelivery Application Specific Data tab is failed");
    		}

    	    
    	    if (doesElementExist2(properties.getProperty("ReprintAllowed"), 5)) {
    		    WebElement chkboxRpntAllow = driver.findElement(By.cssSelector(properties.getProperty("ReprintAllowed")));
    		    
    		    if ( chkboxRpntAllow.isSelected())
    		     {
    		    	log.logLine(Testname, false, "ReprintAllowed checkbox is already selected in Reprint Settings in client app admin");
    		     }else{
    		    	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxRpntAllow);
    		     }
    		     log.logLine(Testname, false, "Selected the ReprintAllowed check box in Reprint Settings in client app admin sucessfull");
    	    } else {
    		     log.logLine(Testname, true, "Selecting the ReprintAllowed check box in Reprint Settings in client app admin is failed");
    		     driver.switchTo().defaultContent();
    		     throw new Exception("Selecting the ReprintAllowed check box in Reprint Settings in client app admin is failed");
    		}
    	    
    	    String spfyJob = test.readColumnData("ReprintSpecifyJob", sheetname); 
    	    if (doesElementExist2(properties.getProperty("RprntSpcfyJob"), 5)) {
    		    WebElement rprntSpcfyjob = driver.findElement(By.cssSelector(properties.getProperty("RprntSpcfyJob")));
    		    //((JavascriptExecutor) driver).executeScript("arguments[0].click()", cntnme);
    		    rprntSpcfyjob.clear();
    		    rprntSpcfyjob.sendKeys(spfyJob);			    
    		    log.logLine(Testname, false, "Entered the job name as "+spfyJob+" in the specify job text field in Reprint Settings in client app admin  under eDelivery Application Specific Data tab.");
    		} else {
    		   	log.logLine(Testname, true, "Entering the job name as "+spfyJob+" in the specify job text field in Reprint Settings in client app admin  under eDelivery Application Specific Data tab is failed");
    		}
    	    
    	    
    	    if (doesElementExist2(properties.getProperty("SpecfyLcn"), 5)) {	    
      			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("SpecfyLcn")));
      			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
      			log.logLine(Testname, false, "Clicking on Specify loaction drop down list");
          	
      		    	List<WebElement> Lcn = driver.findElements(By.cssSelector(properties.getProperty("NtcLcn")));
      				for (WebElement btn:Lcn) {
      					if (btn.getText().equalsIgnoreCase("NTCLocation")) {
      						((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
      						log.logLine(Testname, false, "Selecting the Audit Status as Pending from drop down list in advance search");
      						break;
      					}
      				}
      			}else {
    				log.logLine(Testname, true, "Unable to select the Audit Status from the dropdown in Advanced search dialog");
    				throw new Exception("Unable to select the Audit Status from the dropdown in Advanced search dialog");
      			}
        
    	   
    		if (doesElementExist2(properties.getProperty("LineFst"), 5)) {
    		    WebElement LinefstFld = driver.findElement(By.cssSelector(properties.getProperty("LineFst")));
    		    LinefstFld.clear();
    		    LinefstFld.sendKeys(First);
    	    	log.logLine(Testname, false, "Entering the Txt in Return address line 1 Text Field");
		    }else {
    		   	log.logLine(Testname, true, "Entering the Txt in Return address line 1 Text Field is failed");
    		   	throw new Exception("Entering the Txt in Return address line 1 Text Field is failed");
		    }
    		
    		if (doesElementExist2(properties.getProperty("LineSnd"), 5)) {
    		    WebElement Linesndfld = driver.findElement(By.cssSelector(properties.getProperty("LineSnd")));
    		    Linesndfld.clear();
    		    Linesndfld.sendKeys(Second);
    	    	log.logLine(Testname, false, "Entering the Txt in Return address line 2 Text Field");
    		}else {
    		   	log.logLine(Testname, true, "Entering the Txt in Return address line 2 Text Field is failed");
    		   	throw new Exception("Entering the Txt in Return address line 2 Text Field is failed");
    		}
    			    		
    		if (doesElementExist2(properties.getProperty("LineTrd"), 5)) {
    			WebElement Linetrdfld = driver.findElement(By.cssSelector(properties.getProperty("LineTrd")));
    			Linetrdfld.clear();
    			Linetrdfld.sendKeys(Third);
    			log.logLine(Testname, false, "Entering the Txt in Return address line 3 Text Field");
    		}else {
    		   	log.logLine(Testname, true, "Entering the Txt in Return address line 3 Text Field is failed");
    		   	throw new Exception("Entering the Txt in Return address line 3 Text Field is failed");
    		}
    		
    		if (doesElementExist2(properties.getProperty("LineFur"), 5)) {
    			WebElement Linrfurfld = driver.findElement(By.cssSelector(properties.getProperty("LineFur")));
    			Linrfurfld.clear();
    			Linrfurfld.sendKeys(Fourth);
    	    	log.logLine(Testname, false, "Entering the Txt in Return address line 4 Text Field");
    		}else {
    		   	log.logLine(Testname, true, "Entering the Txt in Return address line 4 Text Field is failed");
    		   	throw new Exception("Entering the Txt in Return address line 4 Text Field is failed");
    		}
    		
      	    	
    	    
    	    if (doesElementExist2(properties.getProperty("ReprintUpdateBtn"), 5)) {
    		    WebElement repritUpdtBtn= driver.findElement(By.cssSelector(properties.getProperty("ReprintUpdateBtn")));
    		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", repritUpdtBtn);
    		    waitUntilRetrive();
    		    Thread.sleep(2000);
    		   	log.logLine(Testname, false, "Clicked on the update button  of Reprint Settings under the eDelivery Application Specific Data tab");
    		} else {
    	    	log.logLine(Testname, true, "Clicking on the update button  of Reprint Settings  under the eDelivery Application Specific Data tab is failed");
    	    	throw new Exception("Clicking on the update button  of Reprint Settings  under the eDelivery Application Specific Data tab is failed");
    		}
    	    
    	return true;
    	    
    }
    	    
        
    public boolean PDFSettingsNoMail(String AccNo, String Testname) throws Exception {
        	
		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
	    
	    Thread.sleep(1000);
	    driver.switchTo().defaultContent();   	    
	
    
	    //PDF Concatenation Setup
	    if (doesElementExist2(properties.getProperty("PDFConcatEdit"), 5)) {
		    WebElement pdfedit = driver.findElement(By.cssSelector(properties.getProperty("PDFConcatEdit")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", pdfedit);
		    waitUntilRetrive();
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Clicked on the edit button of PDF Concatenated Setup under the eDelivery Application Specific Data tab");
		} else {
	    	log.logLine(Testname, true, "Clicking on the edit button of PDF Concatenated Setup under the eDelivery Application Specific Data tab is failed");
	    	throw new Exception("Clicking on the edit button of PDF Concatenated Setup under the eDelivery Application Specific Data tab is failed");
		}
	    
	    
	    if (doesElementExist2(properties.getProperty("pdfConcatAllowed"), 5)) {
		    WebElement chkboxpdfconcatAllow = driver.findElement(By.cssSelector(properties.getProperty("pdfConcatAllowed")));
		    
		    if ( chkboxpdfconcatAllow.isSelected())
		     {
		    	log.logLine(Testname, false, "PDF Concatenated Allowed checkbox is already selected in PDF Concatenated Setup in client app admin");
		     }else{
		    	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxpdfconcatAllow);
		     }
		     log.logLine(Testname, false, "Selected the PDF Concatenated Allowed check box in PDF Concatenated Setup in client app admin sucessfull");
	    } else {
		     log.logLine(Testname, true, "Selecting the PDF Concatenated Allowed check box in PDF Concatenated Setup in client app admin is failed");
		     driver.switchTo().defaultContent();
		     throw new Exception("Selecting the PDF Concatenated Allowed check box in PDF Concatenated Setup in client app admin is failed");
		}
	    
	    if (doesElementExist2(properties.getProperty("MsgandNoEmail"), 5)) {
		    WebElement radiomsgandsendmail= driver.findElement(By.cssSelector(properties.getProperty("MsgandNoEmail")));
		    
		    if ( radiomsgandsendmail.isSelected())
		     {
		    	log.logLine(Testname, false, "Display message and send email  radio button is already selected in PDF Concatenated Setup in client app admin");
		     }else{
		    	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", radiomsgandsendmail);
		     }
		     log.logLine(Testname, false, "Selected the Display message and send email  radio button in PDF Concatenated Setup in client app admin sucessfull");
	    } else {
		     log.logLine(Testname, true, "Selecting the Display message and send email  radio button in PDF Concatenated Setup in client app admin is failed");
		     driver.switchTo().defaultContent();
		     throw new Exception("Selecting the Display message and send email  radio button in PDF Concatenated Setup in client app admin is failed");
		}
	    
	    if (doesElementExist2(properties.getProperty("PDFRRDhosted"), 5)) {
		    WebElement chboxrrdhosted= driver.findElement(By.cssSelector(properties.getProperty("PDFRRDhosted")));
		    
		    if ( chboxrrdhosted.isSelected())
		     {
		    	log.logLine(Testname, false, "RRD Hosted checkbox is already selected in PDF Concatenated Setup in client app admin");
		     }else{
		    	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chboxrrdhosted);
		     }
		     log.logLine(Testname, false, "Selected the RRD Hosted checkbox in PDF Concatenated Setup in client app admin sucessfull");
	    } else {
		     log.logLine(Testname, true, "Selecting the RRD Hosted checkbox in PDF Concatenated Setup in client app admin is failed");
		     driver.switchTo().defaultContent();
		     throw new Exception("Selecting the RRD Hosted checkbox in PDF Concatenated Setup in client app admin is failed");
		}
	    
	    if (doesElementExist2(properties.getProperty("PDfPivothosted"), 5)) {
		    WebElement chkboxpivothosted= driver.findElement(By.cssSelector(properties.getProperty("PDfPivothosted")));
		    
		    if ( chkboxpivothosted.isSelected())
		     {
		    	log.logLine(Testname, false, "Pivot Hosted checkbox is already selected in PDF Concatenated Setup in client app admin");
		     }else{
		    	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxpivothosted);
		     }
		     log.logLine(Testname, false, "Selected the Pivot Hosted checkbox in PDF Concatenated Setup in client app admin sucessfull");
	    } else {
		     log.logLine(Testname, true, "Selecting the Pivot Hosted checkbox in PDF Concatenated Setup in client app admin is failed");
		     driver.switchTo().defaultContent();
		     throw new Exception("Selecting the Pivot Hosted checkbox in PDF Concatenated Setup in client app admin is failed");
		}
	    
	    if (doesElementExist2(properties.getProperty("PDFPortal"), 5)) {
		    WebElement chkboxportal= driver.findElement(By.cssSelector(properties.getProperty("PDFPortal")));
		    
		    if ( chkboxportal.isSelected())
		     {
		    	log.logLine(Testname, false, "Portal checkbox is already selected in PDF Concatenated Setup in client app admin");
		     }else{
		    	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxportal);
		     }
		     log.logLine(Testname, false, "Selected the Portal checkbox in PDF Concatenated Setup in client app admin sucessfull");
	    } else {
		     log.logLine(Testname, true, "Selecting the Portal checkbox in PDF Concatenated Setup in client app admin is failed");
		     driver.switchTo().defaultContent();
		     throw new Exception("Selecting the Portal checkbox in PDF Concatenated Setup in client app admin is failed");
		}
	    
	    if (doesElementExist2(properties.getProperty("PDfupdatebtn"), 5)) {
		    WebElement pdfconcatUpdtBtn= driver.findElement(By.cssSelector(properties.getProperty("PDfupdatebtn")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", pdfconcatUpdtBtn);
		    waitUntilRetrive();
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Clicked on the update button  of PDF Concatenated Setup under the eDelivery Application Specific Data tab");
		} else {
	    	log.logLine(Testname, true, "Clicking on the update button  of PDF Concatenated Setup  under the eDelivery Application Specific Data tab is failed");
	    	throw new Exception("Clicking on the update button  of PDF Concatenated Setup  under the eDelivery Application Specific Data tab is failed");
		}
	    
	    driver.close();
	    return true;
	}
        
        
    public boolean PDFSettingsSendMail(String AccNo, String Testname) throws Exception {
    	
		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
	    
	    Thread.sleep(1000);
	    driver.switchTo().defaultContent();
	    
	   /* if (doesElementExist2(properties.getProperty("ModifyTool"), 5)) {
			WebElement modtool = driver.findElement(By.cssSelector(properties.getProperty("ModifyTool")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", modtool);
			
			waitUntilRetrive();			
			log.logLine(Testname, false, "Clicked on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin ");
		} else {
		    log.logLine(Testname, true, "Clicking on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin is failed");
		    throw new Exception("Clicking on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin is failed");
		}
		*/
	    
	    
		/*if (doesElementExist2(properties.getProperty("eDelApplSpecificdata"), 5)) {
		    WebElement eDelspecidata = driver.findElement(By.cssSelector(properties.getProperty("eDelApplSpecificdata")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelspecidata);
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Clicked on the eDelivery Client Overrides tab under view pivot information");
		} else {
			log.logLine(Testname, true, "Clicking on the eDelivery Client Overrides tab under view pivot information is failed");
			//throw new Exception("Clicking on the eDelivery Client Overrides tab under view pivot information is failed");
		}
		*/
    
    //PDF Concatenation Setup
	    if (doesElementExist2(properties.getProperty("PDFConcatEdit"), 5)) {
		    WebElement pdfedit = driver.findElement(By.cssSelector(properties.getProperty("PDFConcatEdit")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", pdfedit);
		    waitUntilRetrive();
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Clicked on the edit button of PDF Concatenated Setup under the eDelivery Application Specific Data tab");
		} else {
	    	log.logLine(Testname, true, "Clicking on the edit button of PDF Concatenated Setup under the eDelivery Application Specific Data tab is failed");
	    	throw new Exception("Clicking on the edit button of PDF Concatenated Setup under the eDelivery Application Specific Data tab is failed");
		}
	    
	    
	    if (doesElementExist2(properties.getProperty("pdfConcatAllowed"), 5)) {
		    WebElement chkboxpdfconcatAllow = driver.findElement(By.cssSelector(properties.getProperty("pdfConcatAllowed")));
		    
		    if ( chkboxpdfconcatAllow.isSelected())
		     {
		    	log.logLine(Testname, false, "PDF Concatenated Allowed checkbox is already selected in PDF Concatenated Setup in client app admin");
		     }else{
		    	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxpdfconcatAllow);
		     }
		     log.logLine(Testname, false, "Selected the PDF Concatenated Allowed check box in PDF Concatenated Setup in client app admin sucessfull");
	    } else {
		     log.logLine(Testname, true, "Selecting the PDF Concatenated Allowed check box in PDF Concatenated Setup in client app admin is failed");
		     driver.switchTo().defaultContent();
		     throw new Exception("Selecting the PDF Concatenated Allowed check box in PDF Concatenated Setup in client app admin is failed");
		}
	    
	    if (doesElementExist2(properties.getProperty("MsgandEmail"), 5)) {
		    WebElement radiomsgandsendmail= driver.findElement(By.cssSelector(properties.getProperty("MsgandEmail")));
		    
		    if ( radiomsgandsendmail.isSelected())
		     {
		    	log.logLine(Testname, false, "Display message and send email  radio button is already selected in PDF Concatenated Setup in client app admin");
		     }else{
		    	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", radiomsgandsendmail);
		     }
		     log.logLine(Testname, false, "Selected the Display message and send email  radio button in PDF Concatenated Setup in client app admin sucessfull");
	    } else {
		     log.logLine(Testname, true, "Selecting the Display message and send email  radio button in PDF Concatenated Setup in client app admin is failed");
		     driver.switchTo().defaultContent();
		     throw new Exception("Selecting the Display message and send email  radio button in PDF Concatenated Setup in client app admin is failed");
		}
	    
	    if (doesElementExist2(properties.getProperty("PDFRRDhosted"), 5)) {
		    WebElement chboxrrdhosted= driver.findElement(By.cssSelector(properties.getProperty("PDFRRDhosted")));
		    
		    if ( chboxrrdhosted.isSelected())
		     {
		    	log.logLine(Testname, false, "RRD Hosted checkbox is already selected in PDF Concatenated Setup in client app admin");
		     }else{
		    	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chboxrrdhosted);
		     }
		     log.logLine(Testname, false, "Selected the RRD Hosted checkbox in PDF Concatenated Setup in client app admin sucessfull");
	    } else {
		     log.logLine(Testname, true, "Selecting the RRD Hosted checkbox in PDF Concatenated Setup in client app admin is failed");
		     driver.switchTo().defaultContent();
		     throw new Exception("Selecting the RRD Hosted checkbox in PDF Concatenated Setup in client app admin is failed");
		}
	    
	    if (doesElementExist2(properties.getProperty("PDfPivothosted"), 5)) {
		    WebElement chkboxpivothosted= driver.findElement(By.cssSelector(properties.getProperty("PDfPivothosted")));
		    
		    if ( chkboxpivothosted.isSelected())
		     {
		    	log.logLine(Testname, false, "Pivot Hosted checkbox is already selected in PDF Concatenated Setup in client app admin");
		     }else{
		    	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxpivothosted);
		     }
		     log.logLine(Testname, false, "Selected the Pivot Hosted checkbox in PDF Concatenated Setup in client app admin sucessfull");
	    } else {
		     log.logLine(Testname, true, "Selecting the Pivot Hosted checkbox in PDF Concatenated Setup in client app admin is failed");
		     driver.switchTo().defaultContent();
		     throw new Exception("Selecting the Pivot Hosted checkbox in PDF Concatenated Setup in client app admin is failed");
		}
	    
	    if (doesElementExist2(properties.getProperty("PDFPortal"), 5)) {
		    WebElement chkboxportal= driver.findElement(By.cssSelector(properties.getProperty("PDFPortal")));
		    
		    if ( chkboxportal.isSelected())
		     {
		    	log.logLine(Testname, false, "Portal checkbox is already selected in PDF Concatenated Setup in client app admin");
		     }else{
		    	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxportal);
		     }
		     log.logLine(Testname, false, "Selected the Portal checkbox in PDF Concatenated Setup in client app admin sucessfull");
	    } else {
		     log.logLine(Testname, true, "Selecting the Portal checkbox in PDF Concatenated Setup in client app admin is failed");
		     driver.switchTo().defaultContent();
		     throw new Exception("Selecting the Portal checkbox in PDF Concatenated Setup in client app admin is failed");
		}
	    
	    if (doesElementExist2(properties.getProperty("PDfupdatebtn"), 5)) {
		    WebElement pdfconcatUpdtBtn= driver.findElement(By.cssSelector(properties.getProperty("PDfupdatebtn")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", pdfconcatUpdtBtn);
		    waitUntilRetrive();
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Clicked on the update button  of PDF Concatenated Setup under the eDelivery Application Specific Data tab");
		} else {
	    	log.logLine(Testname, true, "Clicking on the update button  of PDF Concatenated Setup  under the eDelivery Application Specific Data tab is failed");
	    	throw new Exception("Clicking on the update button  of PDF Concatenated Setup  under the eDelivery Application Specific Data tab is failed");
		}
	    
	    
	    
	    return true;
	}
        
    public boolean ManageUsers(String AccNo, String Testname, String firstWinHandle) throws Exception {
        	
    		InputOutputData test = new InputOutputData();		
    	    test.setInputFile(properties.getProperty("InputDatafile"));
    	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
    	    
    	    Thread.sleep(1000);
    	    //driver.switchTo().defaultContent();
    	    /*
    		if ((doesElementExist2(properties.getProperty("prevPivotlnk"), 5))) {			    
    			WebElement oldpivt = driver.findElement(By.cssSelector(properties.getProperty("prevPivotlnk")));
    			((JavascriptExecutor) driver).executeScript("arguments[0].click()", oldpivt);
    		
    			Thread.sleep(3000);
    			log.logLine(Testname, false, "Clicking on old Pivot..");
    	
    			// Wait till the page loads all the elements in it.
    			WebElement retelm2 = waitForElement(properties.getProperty("Adminmnu"));      
    		}
        	*/
        
    	    Actions builder = new Actions(driver);			
    		//WebElement mnuElement = driver.findElement(By.cssSelector(properties.getProperty("Adminmnu")));
    		
    		// Move cursor to the Main Menu Element
    		//builder.moveToElement(mnuElement).click().perform();
    	    if (doesElementExist(properties.getProperty("UserAdminlnk"), 5)) {
	    		WebElement clntappmenu = driver.findElement(By.xpath(properties.getProperty("UserAdminlnk")));
	    		((JavascriptExecutor) driver).executeScript("arguments[0].click()", clntappmenu);
	    		log.logLine(Testname, false, "Navigating to Admin - user admin link..");
	    		Thread.sleep(3000);
    	    }
    		
    		//WebElement retelm9 = waitForElement(properties.getProperty("AppNamefld"));
    		//log.logLine(Testname, false, "AppNamefld is found on the page..");
    		
    		
    		//Move the mouse on Welcome text to avoid blinking
    		if (doesElementExist2(properties.getProperty("HelloUserName"), 5)) {
    			WebElement hellolbl = driver.findElement(By.cssSelector(properties.getProperty("HelloUserName")));
    			builder.moveToElement(hellolbl).perform();
    		}
    	
    	
    		//String useradmnid = "Manohar";
    		String useradmnid = test.readColumnData("AdminUserId", sheetname);
    	    if (doesElementExist2(properties.getProperty("UserId"), 5)) {
    		    WebElement cntnme = driver.findElement(By.cssSelector(properties.getProperty("UserId")));
    		    //((JavascriptExecutor) driver).executeScript("arguments[0].click()", cntnme);
    		    action.sendKeys(cntnme, useradmnid).perform();
    		    //cntnme.sendKeys(useradmnid);			    
    		    log.logLine(Testname, false, "Entered the userid "+useradmnid+" in the user id text field in user/admin tool");
    		} else {
    		   	log.logLine(Testname, true, "Entering the userid "+useradmnid+" in the user id text field in user/admin tool is failed");
    		}
        
    	    //Entering Application name in ApplicationId text box
    	    String userappid = test.readColumnData("Applicationname", sheetname);
    	    if (doesElementExist2(properties.getProperty("UserApplicationId"), 5)) {
    	        WebElement applid = driver.findElement(By.cssSelector(properties.getProperty("UserApplicationId")));
    	        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", applid);
    	        //applid.sendKeys(userappid);			
    	        action.sendKeys(applid, userappid).perform();
    	    	log.logLine(Testname, false, "Entered the application name "+userappid+" in the Application name text field in Client/App tool");
    	    } else {
    		    log.logLine(Testname, true, "Entering the application name "+userappid+" in the Application name text field in Client/App tool is failed");
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
    	    	waitUntilRetrive();
    		} else {
    			negativeCase(Testname, firstWinHandle, "", "Clicking on search button of the user Admin is failed");    	    	
    	    }
    	    
    	    return true;
    	    
    }
    

    public boolean EditManageUserconsent(String AccNo, String Testname, String firstWinHandle) throws Exception {
    	
		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
	    
	    Thread.sleep(1000);
	    //driver.switchTo().defaultContent();
    
	    if (doesElementExist2(properties.getProperty("ModifyTool"), 5)) {
	    	WebElement modtool = driver.findElement(By.cssSelector(properties.getProperty("ModifyTool")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", modtool);
			
			Thread.sleep(1000);
			waitUntilRetrive();
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicked on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin ");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the PIVOT eDelivery ModifyTool button in user Admin is failed");
		}
	    
    
	    if (doesElementExist2(properties.getProperty("edelAppdataEdit"), 5)) {
		    WebElement edeldataedit = driver.findElement(By.cssSelector(properties.getProperty("edelAppdataEdit")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", edeldataedit);
		    waitUntilRetrive();
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Clicked on the edit button under the view pivot eDelivery information section");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the edit button under the view pivot eDelivery information section is failed");
		}
    
   
	     if (doesElementExist2(properties.getProperty("edelInformCancelbtn"), 5)) {
		    WebElement edelcanclbtn = driver.findElement(By.cssSelector(properties.getProperty("edelInformCancelbtn")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", edelcanclbtn);
		    waitUntilRetrive();
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Clicking on the cancel button before editing under the view pivot eDelivery information section");
	     } else {
	    	log.logLine(Testname, true, "Clicking on the cancel button before editing under the view pivot eDelivery information section is failed");
	    	//throw new Exception("Clicking on the edit button under the eDelivery Application Specific Data tab is failed");
	     }
    
    
	    if (doesElementExist2(properties.getProperty("edelAppdataEdit"), 5)) {
		    WebElement edeldataedit = driver.findElement(By.cssSelector(properties.getProperty("edelAppdataEdit")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", edeldataedit);
		    waitUntilRetrive();
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Clicked on the edit button under the view pivot eDelivery information section");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the edit button under the view pivot eDelivery information section is failed");
	    }
	
    
	    if (doesElementExist2(properties.getProperty("CltappEmailFlag"), 5)) {
		    WebElement chkboxEmailFlag = driver.findElement(By.cssSelector(properties.getProperty("CltappEmailFlag")));
		    
		    if ( chkboxEmailFlag.isSelected())
		     {
		    	log.logLine(Testname, false, "EmailFlag checkbox is already selected");
		     }else{
		    	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxEmailFlag);
		     }
		     log.logLine(Testname, false, "Selected the EmailFlag check box in view pivot edel information section");
	    } else {
	    	 negativeCase(Testname, firstWinHandle, "", "Selecting the EmailFlag check box in view pivot edel information section is failed");		     
		}
    
	    /*
	    if (doesElementExist2(properties.getProperty("FileSuccessEmail"), 5)) {
		    WebElement chkboxFileSuccessEmail = driver.findElement(By.cssSelector(properties.getProperty("FileSuccessEmail")));
		    
		    if ( chkboxFileSuccessEmail.isSelected())
		     {
		    	log.logLine(Testname, false, "FileSuccessEmail checkbox is already selected");
		     }else{
		    	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxFileSuccessEmail);
		     }
		    log.logLine(Testname, false, "Selected the FileSuccessEmail check box in view pivot edel information section");
	    } else {
	    	 negativeCase(Testname, firstWinHandle, "", "Selecting the FileSuccessEmail check box in view pivot edel information section is failed");		     
		}
    
	    
	    if (doesElementExist2(properties.getProperty("FileFailureEmail"), 5)) {
		    WebElement chkboxFileFailureEmail = driver.findElement(By.cssSelector(properties.getProperty("FileFailureEmail")));
		    
		    if ( chkboxFileFailureEmail.isSelected())
		     {
		    	log.logLine(Testname, false, "FileFailureEmail checkbox is already selected");
		     }else{
		    	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxFileFailureEmail);
		     }
		     log.logLine(Testname, false, "Selected the FileFailureEmail check box in view pivot edel information section");
	    } else {
	    	 negativeCase(Testname, firstWinHandle, "", "Selecting the FileFailureEmail check box in view pivot edel information section is failed");		     
		}
    
	    if (doesElementExist2(properties.getProperty("ManageUserConsent"), 5)) {
		    WebElement chkboxManageUserConsent = driver.findElement(By.cssSelector(properties.getProperty("ManageUserConsent")));
		    
		    if ( chkboxManageUserConsent.isSelected())
		     {
		    	log.logLine(Testname, false, "ManageUserConsent checkbox is already selected");
		     }else{
		    	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxManageUserConsent);
		     }
		     log.logLine(Testname, false, "Selecting the ManageUserConsent check box in view pivot edel information section");
	    } else {
	    	 negativeCase(Testname, firstWinHandle, "", "Selecting the ManageUserConsent check box in view pivot edel information section is failed");		     
		}
    
	    
	    if (doesElementExist2(properties.getProperty("ViewDataLoads"), 5)) {
		    WebElement chkboxViewDataLoads = driver.findElement(By.cssSelector(properties.getProperty("ViewDataLoads")));
		    
		    if ( chkboxViewDataLoads.isSelected())
		     {
		    	log.logLine(Testname, false, "ViewDataLoads checkbox is already selected");
		     }else{
		    	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxViewDataLoads);
		     }
		     log.logLine(Testname, false, "Selecting the ViewDataLoads check box in view pivot edel information section ");
	    } else {
	    	 negativeCase(Testname, firstWinHandle, "", "Selecting the ViewDataLoads check box in view pivot edel information section is failed");
	    }
    
	    if (doesElementExist2(properties.getProperty("EditDataLoads"), 5)) {
		    WebElement chkboxEditDataLoads = driver.findElement(By.cssSelector(properties.getProperty("EditDataLoads")));
		    
		    if ( chkboxEditDataLoads.isSelected())
		     {
		    	log.logLine(Testname, false, "EditDataLoads checkbox is already selected");
		     }else{
		    	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxEditDataLoads);
		     }
		     log.logLine(Testname, false, "Selecting the EditDataLoads check box in view pivot edel information section ");
	    } else {
	    	 negativeCase(Testname, firstWinHandle, "", "Selecting the EditDataLoads check box in view pivot edel information section is failed");
	    }
    
	    if (doesElementExist2(properties.getProperty("DirectDataloads"), 5)) {
		    WebElement chkboxDirectDataloads = driver.findElement(By.cssSelector(properties.getProperty("DirectDataloads")));
		    
		    if ( chkboxDirectDataloads.isSelected())
		     {
		    	log.logLine(Testname, false, "DirectDataloads checkbox is already selected");
		     }else{
		    	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxDirectDataloads);
		     }
		     log.logLine(Testname, false, "Selecting the DirectDataloads check box in view pivot edel information section ");
	    } else {
	    	 negativeCase(Testname, firstWinHandle, "", "Selecting the DirectDataloads check box in view pivot edel information section is failed");
	    }
    
	    if (doesElementExist2(properties.getProperty("PdfConcat"), 5)) {
		    WebElement chkboxPdfConcat = driver.findElement(By.cssSelector(properties.getProperty("PdfConcat")));
		    
		    if ( chkboxPdfConcat.isSelected())
		     {
		    	log.logLine(Testname, false, "PdfConcat checkbox is already selected");
		     }else{
		    	 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxPdfConcat);
		     }
		     log.logLine(Testname, false, "Selecting the PdfConcat check box in view pivot edel information section ");
	    } else {
	    	 negativeCase(Testname, firstWinHandle, "", "Selecting the PdfConcat check box in view pivot edel information section is failed");
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
	    
	    Thread.sleep(2000);
    	driver.close();
    	
    	driver.switchTo().window(firstWinHandle);
	    
	    return true;
	  
	}
    
    
    public boolean EnableEmailFlag(String AccNo, String Testname, String firstWinHandle) throws Exception {

  	  InputOutputData test = new InputOutputData();		
  	  test.setInputFile(properties.getProperty("InputDatafile"));
  	  String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	  	    
  	
  	  EmailFrm =test.readColumnData("EmailFrom", sheetname);
      EmailSub =test.readColumnData("EmailSubject", sheetname);
      EmailBdy =test.readColumnData("EmailBody", sheetname);
  	
  	//Clicking on the modify tool button
      if (doesElementExist2(properties.getProperty("ModifyTool"), 5)) {
      	WebElement modtool = driver.findElement(By.cssSelector(properties.getProperty("ModifyTool")));
  		((JavascriptExecutor) driver).executeScript("arguments[0].click()", modtool);
  		
  		waitUntilRetrive();			
  		log.logLine(Testname, false, "Clicked on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin ");
  	} else {
  	    log.logLine(Testname, true, "Clicking on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin is failed");
  	    driver.close();
		driver.switchTo().window(firstWinHandle);
  	    throw new Exception("Clicking on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin is failed");
  	}
      
   
      //click on edit button
      
      if (doesElementExist2(properties.getProperty("Editbutton1"), 5)) {
  	    WebElement Editbutton = driver.findElement(By.cssSelector(properties.getProperty("Editbutton1")));
  	    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Editbutton);
  	    waitUntilRetrive();
      	log.logLine(Testname, false, "Clicked edit button under section eArchive/ePresent Search and Result Configuration Over Ride ");
  	} else {
      	log.logLine(Testname, true, "Clicking edit button under section eArchive/ePresent Search and Result Configuration Over Ride is failed");
      	driver.close();
		driver.switchTo().window(firstWinHandle);
      	throw new Exception("Clicking edit button under section eArchive/ePresent Search and Result Configuration Over Ride is failed");
  	}
      
      if (doesElementExist2(properties.getProperty("Emailflg"), 5)) {
  	    WebElement Emailchkbox = driver.findElement(By.cssSelector(properties.getProperty("Emailflg")));
  	   
  	    	if ( !Emailchkbox.isSelected())
  		     {
  	    		((JavascriptExecutor) driver).executeScript("arguments[0].click()", Emailchkbox);
  		     }
  	    	
  	    		if (doesElementExist2(properties.getProperty("EmailFrom"), 5)) {
  	    		    WebElement Emailfrmfld = driver.findElement(By.cssSelector(properties.getProperty("EmailFrom")));
  	    		    Emailfrmfld.clear();
  	    		    action.sendKeys(Emailfrmfld, EmailFrm).perform();
  	    		  Thread.sleep(2000);
  	    		    //Emailfrmfld.sendKeys(EmailFrm);
  	    	    	log.logLine(Testname, false, "Entering the "+EmailFrm+"in EmailFrom Text Field");

  	    		    }
  	    		
  	    		if (doesElementExist2(properties.getProperty("EmailSubject"), 5)) {
  	    		    WebElement Emailsub = driver.findElement(By.cssSelector(properties.getProperty("EmailSubject")));
  	    		    Emailsub.clear();
  	    		    action.sendKeys(Emailsub, EmailSub).perform();
  	    		  Thread.sleep(2000);
  	    		    //Emailsub.sendKeys(EmailSub);
  	    	    	log.logLine(Testname, false, "Entering the "+EmailSub+"in EmailSubject Text Field");

  	    		    }
  	    		
  	    		if (doesElementExist2(properties.getProperty("EmailBody"), 5)) {
  	    		    WebElement Emailbdy = driver.findElement(By.cssSelector(properties.getProperty("EmailBody")));
  	    		    Emailbdy.clear();
  	    		    action.sendKeys(Emailbdy, EmailBdy).perform();
  	    		  Thread.sleep(2000);
  	    		    //Emailbdy.sendKeys(EmailBdy);
  	    	    	log.logLine(Testname, false, "Entering the "+EmailBdy+"in EmailBody Text Field");
  	    		    }
  	    		
  		     log.logLine(Testname, false, "Enabled the Email check box ");
  		} else {
  		     log.logLine(Testname, true, "Enabled the Email check box failed");
  		     driver.close();
  		     driver.switchTo().window(firstWinHandle);
  		     throw new Exception("Enabled the Email check box is failed");
  		}
      
      if (doesElementExist2(properties.getProperty("Pinencrptn"), 5)) {
  	    WebElement PinEncription = driver.findElement(By.cssSelector(properties.getProperty("Pinencrptn")));
  	   
  	    	if ( !PinEncription.isSelected()) {
  	    		((JavascriptExecutor) driver).executeScript("arguments[0].click()", PinEncription);
  		     }
  		     log.logLine(Testname, false, "Enabled the PinEncription Checkbox ");
  		} else {
  		     log.logLine(Testname, true, "Enabled the PinEncription Checkbox is failed");
  		     driver.close();
  		     driver.switchTo().window(firstWinHandle);
  		     throw new Exception("Enabled the PinEncription Checkbox is failed");
  		}
      Thread.sleep(5000);
      	if (doesElementExist2(properties.getProperty("Updatebtn1"), 5)) {
	  	    WebElement Updatebutton = driver.findElement(By.cssSelector(properties.getProperty("Updatebtn1")));
	  	    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Updatebutton);
	  	  Thread.sleep(2000);
	  	    waitUntilRetrive();
	      	log.logLine(Testname, false, "Clicked on Update Button Under Edelivery Application Specific Data Module ");
  		} else {
	      	log.logLine(Testname, true, "Clicked on Update Button Under Edelivery Application Specific Data Module is failed");
	      	driver.close();
			driver.switchTo().window(firstWinHandle);
	      	throw new Exception("Clicked on Update Button Under Edelivery Application Specific Data Module is failed");
  		}
      	
      	driver.close();
      	driver.switchTo().window(firstWinHandle);
      
      	return true;
    }
    
    
    public boolean ReprintSettingsAdmin(String AccNo, String Testname, String firstWinHandle) throws Exception {

  	  InputOutputData test = new InputOutputData();		
  	  test.setInputFile(properties.getProperty("InputDatafile"));
  	  String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	
  	    
  	  
  	 String Spcjb =test.readColumnData("SpecifyJob", sheetname);
  	 String First =test.readColumnData("FirstLine", sheetname);
  	 String Second =test.readColumnData("SecondLine", sheetname);
  	 String Third =test.readColumnData("ThirdLine", sheetname);
  	 String Fourth =test.readColumnData("FourthLine", sheetname);

  	
  		//Clicking on the modify tool button
  	if (doesElementExist2(properties.getProperty("ModifyTool"), 5)) {
  		WebElement modtool = driver.findElement(By.cssSelector(properties.getProperty("ModifyTool")));
  			((JavascriptExecutor) driver).executeScript("arguments[0].click()", modtool);
  			waitUntilRetrive();			
  		log.logLine(Testname, false, "Clicked on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin ");
  	} else {
  	    log.logLine(Testname, true, "Clicking on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin is failed");
  	    driver.close();
		driver.switchTo().window(firstWinHandle);
  	    throw new Exception("Clicking on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin is failed");
  	}


  	//click on edit button

  	if (doesElementExist2(properties.getProperty("ReprntEdit"), 5)) {
	    WebElement Editbutton = driver.findElement(By.cssSelector(properties.getProperty("ReprntEdit")));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Editbutton);
	    waitUntilRetrive();
  		log.logLine(Testname, false, "Clicked edit button for Reprint settings under eDelivery Application Specific Data ");
  	} else {
  		log.logLine(Testname, true, "Clicked edit button for Reprint settings under eDelivery Application Specific Data is failed");
  		driver.close();
		driver.switchTo().window(firstWinHandle);
  		throw new Exception("Clicked edit button for Reprint settings under eDelivery Application Specific Data is failed");
  		}
  	
  	if (doesElementExist2(properties.getProperty("ReprntAlwdchcbox"), 5)) {
  		WebElement Emailchkbox = driver.findElement(By.cssSelector(properties.getProperty("ReprntAlwdchcbox")));
  				   
    	if ( !Emailchkbox.isSelected())	{
    		Emailchkbox.click();
	    }
    	if (doesElementExist2(properties.getProperty("SpecfyJob"), 5)) {
    		    WebElement specyJobfld = driver.findElement(By.cssSelector(properties.getProperty("SpecfyJob")));
    		    specyJobfld.clear();
    		    action.sendKeys(specyJobfld, Spcjb).perform();
    		    //specyJobfld.sendKeys(Spcjb);
    	    	log.logLine(Testname, false, "Entering the in SpecifyJob Field");

    	}
		
	 	if (doesElementExist2(properties.getProperty("SpecfyLcn"), 5)) {	    
  			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("SpecfyLcn")));
  			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
  			log.logLine(Testname, false, "Clicking on Specify loaction drop down list");
      	
  		    	List<WebElement> Lcn = driver.findElements(By.cssSelector(properties.getProperty("NtcLcn")));
  				for (WebElement btn:Lcn) {
  					if (btn.getText().equalsIgnoreCase("NTCLocation")) {
  						((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
  						log.logLine(Testname, false, "Selecting the Audit Status as Pending from drop down list in advance search");
  						break;
  					}
  				}
  			}else {
				log.logLine(Testname, true, "Unable to select the Audit Status from the dropdown in Advanced search dialog");
				throw new Exception("Unable to select the Audit Status from the dropdown in Advanced search dialog");
  			}
    
		
		if (doesElementExist2(properties.getProperty("LineFst"), 5)) {
		    WebElement LinefstFld = driver.findElement(By.cssSelector(properties.getProperty("LineFst")));
		    LinefstFld.clear();
		    action.sendKeys(LinefstFld, First).perform();
		    //LinefstFld.sendKeys(First);
	    	log.logLine(Testname, false, "Entering the Txt in Line First Address Text Field as    "+First );
		    }
		
		if (doesElementExist2(properties.getProperty("LineSnd"), 5)) {
		    WebElement Linesndfld = driver.findElement(By.cssSelector(properties.getProperty("LineSnd")));
		    Linesndfld.clear();
		    action.sendKeys(Linesndfld, Second).perform();
		    //Linesndfld.sendKeys(Second);
	    	log.logLine(Testname, false, "Entering the Txt in Line Second Address Text Field as    "+Second);
	    	}
		
			    		
		if (doesElementExist2(properties.getProperty("LineTrd"), 5)) {
			WebElement Linetrdfld = driver.findElement(By.cssSelector(properties.getProperty("LineTrd")));
			Linetrdfld.clear();
			action.sendKeys(Linetrdfld, Third).perform();
			//Linetrdfld.sendKeys(Third);
			log.logLine(Testname, false, "Entering the Txt in Line Third Address Text Field as    "+Third);
			}
		
		if (doesElementExist2(properties.getProperty("LineFur"), 5)) {
			WebElement Linrfurfld = driver.findElement(By.cssSelector(properties.getProperty("LineFur")));
			Linrfurfld.clear();
			action.sendKeys(Linrfurfld, Fourth).perform();
			//Linrfurfld.sendKeys(Fourth);
	    	log.logLine(Testname, false, "Entering the Txt in Line Fourth Address Text Field as    "+Fourth);
	    }
		
  	    	log.logLine(Testname, false, "Enabled the Reprint Settings check box ");
  	    } else {
  	    	log.logLine(Testname, true, "Enabled the Reprint Settings check box failed");
  	    	driver.close();
  			driver.switchTo().window(firstWinHandle);
  	    	throw new Exception("Enabled the Reprint Settings check box is failed");
  	    	}

  		if (doesElementExist2(properties.getProperty("ReprntUpdateBtn"), 5)) {
  		    WebElement Updatebutton = driver.findElement(By.cssSelector(properties.getProperty("ReprntUpdateBtn")));
  		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Updatebutton);
  		    waitUntilRetrive();
  			log.logLine(Testname, false, "Clicked on Update Button Under Edelivery Application Specific Data Module ");
  		} else {
  			log.logLine(Testname, true, "Clicked on Update Button Under Edelivery Application Specific Data Module is failed");
  			driver.close();
  			driver.switchTo().window(firstWinHandle);
  			throw new Exception("Clicked on Update Button Under Edelivery Application Specific Data Module is failed");
  		}


  		return true;	
  	
    }	
    
    
public boolean ClientAppsrch_Audit(String AccNo, String Testname) throws Exception {
        
        InputOutputData test = new InputOutputData();        
        test.setInputFile(properties.getProperty("InputDatafile"));
        String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
        
        
        adtMaxKeys = test.readColumnData("AuditMaxKey", sheetname);
        adtMaxRule = test.readColumnData("AuditMaxRules", sheetname);
        subsegmax = test.readColumnData("SubSegtMax", sheetname);
        
        
        Thread.sleep(5000);
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

        
        if ((doesElementExist2(properties.getProperty("Adminlnk"), 5))) {                
            WebElement oldpivt = driver.findElement(By.cssSelector(properties.getProperty("Adminlnk")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", oldpivt);
        
            Thread.sleep(3000);
            log.logLine(Testname, false, "Clicking on Legacy Admin..");              
        }
        
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
                driver.manage().window().maximize();
                
                // Wait till the page loads all the elements in it.
                WebElement retelm2 = waitForElement(properties.getProperty("ProfileAdmin"));                
                Actions builder = new Actions(driver);            
                
                WebElement clntappmenu = driver.findElement(By.xpath(properties.getProperty("Clientapplnk")));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click()", clntappmenu);
                Thread.sleep(3000);
                log.logLine(Testname, false, "Navigating to Admin - Clientapp admin link..");
                
                WebElement retelm9 = waitForElement(properties.getProperty("AppNamefld"));
                log.logLine(Testname, false, "AppNamefld is found on the page..");
                
                
                //Move the mouse on Welcome text to avoid blinking
                WebElement hellolbl = driver.findElement(By.cssSelector(properties.getProperty("HelloUserName")));
                builder.moveToElement(hellolbl).perform();
                Thread.sleep(1000);
                
                String ClntName = test.readColumnData("ClientName", sheetname);
                
                if (doesElementExist2(properties.getProperty("clientname"), 5)) {
                    WebElement cntnme = driver.findElement(By.cssSelector(properties.getProperty("clientname")));
                    cntnme.clear();
                    //((JavascriptExecutor) driver).executeScript("arguments[0].click()", cntnme);
                    action.sendKeys(cntnme, ClntName).perform();
                    //cntnme.sendKeys(ClntName);    
                    Thread.sleep(1000);
                    log.logLine(Testname, false, "Entered the client name "+ClntName+" in the client name text field in Client/App tool");
                } else {
                       log.logLine(Testname, true, "Entering the client name "+ClntName+" in the client name text field in Client/App tool is failed");
                }
                
                //Entering Application name in ApplicationId text box
                String AppName = test.readColumnData("ApplicationName", sheetname);

                if (doesElementExist2(properties.getProperty("ApplicationId"), 5)) {
                    WebElement applid = driver.findElement(By.cssSelector(properties.getProperty("ApplicationId")));
                    //((JavascriptExecutor) driver).executeScript("arguments[0].click()", applid);
                    action.sendKeys(applid, AppName).perform();
                    //applid.sendKeys(AppName);
                    Thread.sleep(1000);
                    log.logLine(Testname, false, "Entered the application name "+AppName+" in the Application name text field in Client/App tool");
                } else {
                    log.logLine(Testname, true, "Entering the application name "+AppName+" in the Application name text field in Client/App tool is failed");
                }       

               
                Actions action = new Actions(driver);
                if (doesElementExist(properties.getProperty("AnyTool1"), 5)) {
                    WebElement AnyTool = driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_lstLayers']"));
                   Select select = new Select(AnyTool); 
                   List <WebElement> options = select.getOptions(); 
                   for (WebElement option: options) { 
                    if (option.getText().equalsIgnoreCase("PIVOT Audit")){ 
                        option.click(); 
                        break; 
                  }
                    }
                    log.logLine(Testname, false, "Clicked on AnyTool drop down list and selected the option PIVOT eDelivery..");
                } else {
                    log.logLine(Testname, true, "Clicking on AnyTool drop down list and selecting the option PIVOT eDelivery is  failed");
                    driver.switchTo().defaultContent();
                    throw new Exception("Clicking on AnyTool drop down list and selecting the option PIVOT eDelivery is  failed");
                }         
                
                //clicking on search button        
                if (doesElementExist2(properties.getProperty("searchbtn"), 5)) {
                    WebElement srcbtn = driver.findElement(By.cssSelector(properties.getProperty("searchbtn")));
                                        
                    log.logLine(Testname, false, "Clicked on search button of the client/app/Tool Admin");
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", srcbtn);
                    waitUntilRetrive();
                } else {
                    log.logLine(Testname, true, "Clicking on search button of the client/app/Tool Admin is failed");
                    throw new Exception("Clicking on search button of the client/app/Tool Admin is failed");
                }
                
                
                if (doesElementExist2(properties.getProperty("ModifyTool"), 5)) {
                    WebElement modtool = driver.findElement(By.cssSelector(properties.getProperty("ModifyTool")));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", modtool);
                    
                    waitUntilRetrive();            
                    log.logLine(Testname, false, "Clicked on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin ");
                } else {
                    log.logLine(Testname, true, "Clicking on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin is failed");
                    throw new Exception("Clicking on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin is failed");
                }
                
               
                if (doesElementExist2(properties.getProperty("AudtMaxKey"), 5)) {
                    WebElement adtmaxkey = driver.findElement(By.cssSelector(properties.getProperty("AudtMaxKey")));
                    adtmaxkey.clear();
                    action.sendKeys(adtmaxkey, adtMaxKeys).perform();
                    //adtmaxkey.sendKeys(adtMaxKeys);
                    log.logLine(Testname, false, "Entering max number of Audit Keys");
                } else {
                    log.logLine(Testname, true, "Entering max number of Audit Keys is failed");
                    throw new Exception("Entering max number of Audit Keys is failed");
                }
                
                
                 
                if (doesElementExist2(properties.getProperty("AudtMaxRule"), 5)) {
                    WebElement adtmaxRule = driver.findElement(By.cssSelector(properties.getProperty("AudtMaxRule")));
                    adtmaxRule.clear();
                    action.sendKeys(adtmaxRule, adtMaxRule).perform();
                    //adtmaxRule.sendKeys(adtMaxRule);
                    log.logLine(Testname, false, "Entering max number of Audit Rules");
                } else {
                    log.logLine(Testname, true, "Entering max number of Audit Rules is failed");
                    throw new Exception("Entering max number of Audit Rules is failed");
                }
                
                
                if (doesElementExist2(properties.getProperty("SubSegmntChkBox"), 5)) {
                    WebElement subsegchkbox = driver.findElement(By.cssSelector(properties.getProperty("SubSegmntChkBox")));
                    
                    if ( subsegchkbox.isSelected())
                     {
                        log.logLine(Testname, false, "Sub segment check box is already selected");
                     }else{
                         subsegchkbox.click();
                     }
                     log.logLine(Testname, false, "Selecting the Sub segment check box is passed");
                } else {
                     log.logLine(Testname, true, "Sub segment check box is not selected");
                     driver.switchTo().defaultContent();
                     throw new Exception("Sub segment check box is not selected");
                }
                
                 
                if (doesElementExist2(properties.getProperty("SubSegmntMax"), 5)) {
                    WebElement subsegMax = driver.findElement(By.cssSelector(properties.getProperty("SubSegmntMax")));
                    subsegMax.clear();
                    action.sendKeys(subsegMax, subsegmax).perform();
                    //subsegMax.sendKeys(subsegmax);
                    log.logLine(Testname, false, "Entering max number of Sub Segments");
                } else {
                    log.logLine(Testname, true, "Entering max number of Sub Segments is failed");
                    throw new Exception("Entering max number of Sub Segments is failed");
                }
                
                if (doesElementExist2(properties.getProperty("AuditSeltnChkBox"), 5)) {
                    WebElement subsegchkbox = driver.findElement(By.cssSelector(properties.getProperty("AuditSeltnChkBox")));
                    
                    if ( subsegchkbox.isSelected())
                     {
                        log.logLine(Testname, false, "Audit Selection check box is already selected");
                     }else{
                         subsegchkbox.click();
                     }
                     log.logLine(Testname, false, "Selecting the Audit Selection check box is passed");
                } else {
                     log.logLine(Testname, true, "Audit Selection check box is not selected");
                     driver.switchTo().defaultContent();
                     throw new Exception("Audit Selection check box is not selected");
                }
                
                
                if (doesElementExist2(properties.getProperty("AuditInterfaceType"), 5)) {
                    Select adtinterfceTyp = new Select(driver.findElement(By.cssSelector(properties.getProperty("AuditInterfaceType"))));
                    adtinterfceTyp.selectByVisibleText("By Request");
                    log.logLine(Testname, false, "Selecting By Request from interface type dropdown list in View PIVOT Audit Information Section");
                } else {
                    log.logLine(Testname, true, "Selecting By Request from interface type dropdown list in View PIVOT Audit Information Section is failed");
                    throw new Exception("Selecting By Request from interface type dropdown list in View PIVOT Audit Information Section is failed");
                }
                
                String adutactAlrtTime = test.readColumnData("AdtActAlrtTime", sheetname); 
                if (doesElementExist2(properties.getProperty("AudtActnAlrtTime"), 5)) {
                    WebElement audtAlrt = driver.findElement(By.cssSelector(properties.getProperty("AudtActnAlrtTime")));
                    audtAlrt.clear();
                    
                    action.sendKeys(audtAlrt, adutactAlrtTime).perform();
                    //audtAlrt.sendKeys(adutactAlrtTime);
                    log.logLine(Testname, false, "Entering Audit Action Alert Time");
                } else {
                    log.logLine(Testname, true, "Entering Audit Action Alert Time is failed");
                    throw new Exception("Entering Audit Action Alert Time is failed");
                }
                
                
                if (doesElementExist2(properties.getProperty("AudtRecveAlrtchkBox"), 5)) {
                    WebElement adtRecchkbox = driver.findElement(By.cssSelector(properties.getProperty("AudtRecveAlrtchkBox")));
                    
                    if ( adtRecchkbox.isSelected())
                     {
                        log.logLine(Testname, false, "Audit Receive Alert check box is already selected");
                     }else{
                         adtRecchkbox.click();
                     }
                     log.logLine(Testname, false, "Selecting the Audit Receive Alert check box is passed");
                } else {
                     log.logLine(Testname, true, "Audit Receive Alert check box is not selected");
                     driver.switchTo().defaultContent();
                     throw new Exception("Audit Receive Alert check box is not selected");
                }
                
                
                if (doesElementExist2(properties.getProperty("AudtUpdateBtn"), 5)) {
                    WebElement modtool = driver.findElement(By.cssSelector(properties.getProperty("AudtUpdateBtn")));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", modtool);
                    
                    waitUntilRetrive();            
                    log.logLine(Testname, false, "Clicked on the Update button in View PIVOT Audit Information Section ");
                } else {
                    log.logLine(Testname, true, "Clicking on the Update button in View PIVOT Audit Information Section is failed");
                    throw new Exception("Clicking on the Update button in View PIVOT Audit Information Section is failed");
                }
                
                
                
                
                Thread.sleep(10000);
                String updateaudit = "PIVOT Audit updated successfully" ;
                if (isTextPresent(updateaudit)) {
                    
                    log.logLine(Testname, false, "Message "+updateaudit+" is present at the top of the page");    
                        
                } else {
                    log.logLine(Testname, true, "Message "+updateaudit+" is not present at the top of the page");
                    throw new Exception("Message "+updateaudit+" is not present at the top of the page");
                }
                
                
                driver.close();
                
                driver.switchTo().window(firstWinHandle);
                
            }
        }   
        
return true;
     }
    	
}
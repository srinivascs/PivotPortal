package pivotModules;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import launchAuto.CreateAppData;
import launchAuto.Initialization;
import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ibm.icu.util.Calendar;

public class PublicDocuments extends Page {


	int paperID = (int) Math.round(Math.random() * (9999 - 1000 + 1) + 1000);
	public String AccNo = Integer.toString(paperID);

	public String Description, doc1, doc2; 
	public String futureDate;
	public String futureDate1, futureDate2, futureDate3, futureDate4;

	public String pubDoc4;
	public String descpr2;

	public String pubDoc;
	public String descpr;	
	public String[] FutDate= new String[7];
	public int cnt;
	public static String Cpytxt;

	public PublicDocuments(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}	
	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}

	Actions action = new Actions(driver);



	public boolean ClientAdminSettings(String Testname, String RandNo) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		driver.switchTo().defaultContent();
		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App Selection popup");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button in Client/App Selection popup is failed");
			throw new Exception("Clicking on Cancel button in Client/App Selection popup is failed");
		}

		/*Actions builder = new Actions(driver);	        
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
		}*/

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

				if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetExplorer"))) {
					if (doesElementExist2(properties.getProperty("OverrideLnk"), 5)) {
						driver.get("javascript:document.getElementById('overridelink').click();");
						Thread.sleep(8000);
					}
				}

				// Wait till the page loads all the elements in it.
				WebElement retelm2 = waitForElement(properties.getProperty("ProfileAdmin"));				


				if (doesElementExist(properties.getProperty("Clientapplnk"), 5)) {
					WebElement clntappmenu = driver.findElement(By.xpath(properties.getProperty("Clientapplnk")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", clntappmenu);
					Thread.sleep(3000);
					log.logLine(Testname, false, "Navigating to Admin - Clientapp admin link..");
				} else {
					log.logLine(Testname, true, "Navigating to Admin - Clientapp admin link is failed");
					throw new Exception("Navigating to Admin - Clientapp admin link is failed");
				}

				WebElement retelm9 = waitForElement(properties.getProperty("AppNamefld"));
				log.logLine(Testname, false, "AppNamefld is found on the page..");


				/*//Move the mouse on Welcome text to avoid blinking
					WebElement hellolbl = driver.findElement(By.cssSelector(properties.getProperty("HelloUserName")));
					builder.moveToElement(hellolbl).perform();
					Thread.sleep(1000);*/

				String ClntName = test.readColumnData("ClientName", sheetname);					
				if (doesElementExist2(properties.getProperty("clientname"), 5)) {
					WebElement cntnme = driver.findElement(By.cssSelector(properties.getProperty("clientname")));
					cntnme.clear();
					Thread.sleep(2000);
					cntnme.sendKeys(ClntName);	
					Thread.sleep(2000);
					log.logLine(Testname, false, "Entered the client name "+ClntName+" in the client name text field in Client/App tool");
				} else {
					log.logLine(Testname, true, "Entering the client name "+ClntName+" in the client name text field in Client/App tool is failed");
				}

				//Entering Application name in ApplicationId text box
				String AppName = test.readColumnData("ApplicationId", sheetname);

				if (doesElementExist2(properties.getProperty("ApplicationId"), 5)) {
					WebElement applid = driver.findElement(By.cssSelector(properties.getProperty("ApplicationId")));
					applid.clear();
					Thread.sleep(2000);
					applid.sendKeys(AppName);
					Thread.sleep(2000);
					log.logLine(Testname, false, "Entered the application name "+AppName+" in the Application name text field in Client/App tool");
				} else {
					log.logLine(Testname, true, "Entering the application name "+AppName+" in the Application name text field in Client/App tool is failed");
				}	

				ClickOnAnyTool_inLegacyAdmin(Testname);
				ClickOnSearchBtninAdmin(Testname);
				ClickOnModifyToolinAdmin(Testname);


				String srvceRetnDays = test.readColumnData("ServiceRetention", sheetname);					
				if (doesElementExist2(properties.getProperty("ServiceRetentionDays"), 5)) {
					WebElement srvdays = driver.findElement(By.cssSelector(properties.getProperty("ServiceRetentionDays")));
					srvdays.clear();
					srvdays.sendKeys(srvceRetnDays);	
					Thread.sleep(1000);
					log.logLine(Testname, false, "Entered the service rentention days as "+srvceRetnDays+" in the text field of Client/App admin");
				} else {
					log.logLine(Testname, true, "Entering the service rentention days as "+srvceRetnDays+" in the text field of Client/App admin is failed");
					throw new Exception("Entering the service rentention days as "+srvceRetnDays+" in the text field of Client/App admin is failed");
				}

				ClickOnUpdateBtn(Testname);    

				if (doesElementExist(properties.getProperty("UserAdminlnk"), 5)) {
					WebElement clntappmenu = driver.findElement(By.xpath(properties.getProperty("UserAdminlnk")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", clntappmenu);
					Thread.sleep(3000);
					log.logLine(Testname, false, "Navigating to Admin - User admin link..");
				} else {
					log.logLine(Testname, true, "Navigating to Admin - User admin link is failed");
					throw new Exception("Navigating to Admin - User admin link is failed");
				}

				String useradmnid = test.readColumnData("AdminUserId", sheetname);
				if (doesElementExist2(properties.getProperty("UserId"), 5)) {
					WebElement cntnme = driver.findElement(By.cssSelector(properties.getProperty("UserId")));
					cntnme.sendKeys(useradmnid);			    
					log.logLine(Testname, false, "Entered the userid "+useradmnid+" in the user id text field in user/admin tool");
				} else {
					log.logLine(Testname, true, "Entering the userid "+useradmnid+" in the user id text field in user/admin tool is failed");
					throw new Exception("Entering the userid "+useradmnid+" in the user id text field in user/admin tool is failed");
				}

				//Entering Application name in ApplicationId text box
				String userappid = test.readColumnData("ApplicationId", sheetname);
				if (doesElementExist2(properties.getProperty("ApplicationId"), 5)) {
					WebElement applid = driver.findElement(By.cssSelector(properties.getProperty("ApplicationId")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", applid);
					applid.sendKeys(userappid);			    
					log.logLine(Testname, false, "Entered the application name "+userappid+" in the Application name text field in Client/App tool");
				} else {
					log.logLine(Testname, true, "Entering the application name "+userappid+" in the Application name text field in Client/App tool is failed");
					throw new Exception("Entering the application name "+userappid+" in the Application name text field in Client/App tool is failed");
				}


				ClickOnAnyTool_inLegacyAdmin(Testname);				    
				ClickOnSearchBtninAdmin(Testname);
				ClickOnModifyToolinAdmin(Testname);

				UserAuthorization(Testname);
				ClickOnUpdateBtn(Testname);


				driver.close();

				driver.switchTo().window(firstWinHandle);


			}

		}
		return true;
	}



	public boolean ArchiveClientAppSelection(String Testname) throws Exception{

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		driver.switchTo().defaultContent();


		if (doesElementExist2(properties.getProperty("Archives"), 5)) {    
			WebElement arclnk = driver.findElement(By.cssSelector(properties.getProperty("Archives")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", arclnk);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Navigation to Archives page is successfull");
		} else {
			log.logLine(Testname, true, "Clicking on Archives menu is failed");
			throw new Exception("Clicking on Archives menu is failed");
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
						Thread.sleep(2000);
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
							Thread.sleep(3000);
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
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on OK button to view the Archives");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the Archives is failed");
			throw new Exception("Clicking on OK button to view the Archives is failed");
		}

		return true;

	}

	public boolean clickOnPublicDocmntTool(String Testname) throws Exception{    	

		if (doesElementExist2(properties.getProperty("PublicDocumentsLink"), 5)) {	 

			List<WebElement> pblDoc = driver.findElements(By.cssSelector(properties.getProperty("PublicDocumentsLink")));
			for (WebElement lnk:pblDoc) {
				if (lnk.getText().contains("Public Documents")){
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(2000);
					log.logLine(Testname, false, "Clicking on the \"Public Documents\" is sucessful");
					break;
				} 
			}
		}else{
			log.logLine(Testname, false, "Clicking on the \"Public Documents\" is unsucessful");
			throw new Exception("Clicking on the \"Public Documents\" is unsucessful");
		}


		driver.switchTo().frame("iframeContainer").switchTo().frame("iframePublicDocs");

		return true;
	}

	/*public boolean UploadPublicDocs(String Testname) throws Exception{

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		String futureDate1, futureDate2, futureDate3, futureDate4;

		String pubDoc1 = test.readColumnData("DocumentName1", sheetname);
		String pubDoc2 = test.readColumnData("DocumentName2", sheetname);
		String pubDoc3 = test.readColumnData("DocumentName3", sheetname);
		pubDoc4 = test.readColumnData("DocumentName4", sheetname);

		String descpr1 = test.readColumnData("DocDescription1", sheetname);
		descpr2 = test.readColumnData("DocDescription2", sheetname);
		String descpr3 = test.readColumnData("DocDescription3", sheetname);
		String descpr4 = test.readColumnData("DocDescription4", sheetname);

		Calendar currentDate1 = Calendar.getInstance(); 
		Calendar currentDate2 = Calendar.getInstance(); 
		Calendar currentDate3 = Calendar.getInstance();
		Calendar currentDate4 = Calendar.getInstance();
		SimpleDateFormat formatter= new SimpleDateFormat("MM/dd/yyyy");

		currentDate1.add(Calendar.DAY_OF_MONTH, 2);	
		currentDate2.add(Calendar.DAY_OF_MONTH, 3);
		currentDate3.add(Calendar.DAY_OF_MONTH, 4);
		currentDate4.add(Calendar.DAY_OF_MONTH, 5);


		futureDate1 = formatter.format(currentDate1.getTime());
		futureDate2 = formatter.format(currentDate2.getTime());
		futureDate3 = formatter.format(currentDate3.getTime());
		futureDate4 = formatter.format(currentDate4.getTime());

		uploadDocumentsBtn(Testname);		

		if (doesElementExist2(properties.getProperty("Browse1"), 5)) {	    
			WebElement docfld = driver.findElement(By.cssSelector(properties.getProperty("Browse1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", docfld);
			docfld.sendKeys(pubDoc1);			
			log.logLine(Testname, false, "Entering the Ref code into upload report popup");
	    } else {
			log.logLine(Testname, true, "Entering the Ref code into upload report popup is failed");
			throw new Exception("Entering the Ref code into upload report popup is failed");
		}

		if (doesElementExist2(properties.getProperty("EffEndDate1"), 5)) {	    
			WebElement date = driver.findElement(By.cssSelector(properties.getProperty("EffEndDate1")));
			date.clear();
			date.sendKeys(futureDate1);
			log.logLine(Testname, false, "Entering the Ref code into upload report popup");
	    } else {
			log.logLine(Testname, true, "Entering the Ref code into upload report popup is failed");
			throw new Exception("Entering the Ref code into upload report popup is failed");
		}


	    if (doesElementExist2(properties.getProperty("DocDescption1"), 5)) {	    
			WebElement descptn = driver.findElement(By.cssSelector(properties.getProperty("DocDescption1")));
			descptn.clear();
			descptn.sendKeys(descpr1);			
			log.logLine(Testname, false, "Entering the Ref code into upload report popup");
	    } else {
			log.logLine(Testname, true, "Entering the Ref code into upload report popup is failed");
			throw new Exception("Entering the Ref code into upload report popup is failed");
		}

	    if (doesElementExist2(properties.getProperty("ViewChkBox1"), 5)) {
	    	WebElement vewchkbox = driver.findElement(By.cssSelector(properties.getProperty("ViewChkBox1")));

	    	if(vewchkbox.isSelected()){
	    		log.logLine(Testname, false, "Viewable check box is already selected");
	    	}else{
	    		((JavascriptExecutor) driver).executeScript("arguments[0].click()", vewchkbox);
	    		Thread.sleep(1000);
	    		log.logLine(Testname, false, "Selecting the Viewable check box ");
	    	}

	    	log.logLine(Testname, false, "Selecting the viewable checkbox is successful");
	    }else{
	    	log.logLine(Testname, true, "Unbale to select the viewable chekbox");
	    	throw new Exception("Unbale to select the viewable chekbox");

	    }

	    uploadNewDocumentsCompleteBtn(Testname);
	    checkAlert();


	    uploadDocumentsBtn(Testname);

		if (doesElementExist2(properties.getProperty("Browse1"), 5)) {	    
			WebElement docfld = driver.findElement(By.cssSelector(properties.getProperty("Browse1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", docfld);
			docfld.sendKeys(pubDoc2);			
			log.logLine(Testname, false, "Entering the Ref code into upload report popup");
	    } else {
			log.logLine(Testname, true, "Entering the Ref code into upload report popup is failed");
			throw new Exception("Entering the Ref code into upload report popup is failed");
		}

		if (doesElementExist2(properties.getProperty("EffEndDate1"), 5)) {	    
			WebElement date = driver.findElement(By.cssSelector(properties.getProperty("EffEndDate1")));
			date.clear();
			date.sendKeys(futureDate2);
			log.logLine(Testname, false, "Entering the Ref code into upload report popup");
	    } else {
			log.logLine(Testname, true, "Entering the Ref code into upload report popup is failed");
			throw new Exception("Entering the Ref code into upload report popup is failed");
		}


	    if (doesElementExist2(properties.getProperty("DocDescption1"), 5)) {	    
			WebElement descptn = driver.findElement(By.cssSelector(properties.getProperty("DocDescption1")));
			descptn.clear();
			descptn.sendKeys(descpr2);			
			log.logLine(Testname, false, "Entering the Ref code into upload report popup");
	    } else {
			log.logLine(Testname, true, "Entering the Ref code into upload report popup is failed");
			throw new Exception("Entering the Ref code into upload report popup is failed");
		}

	    if (doesElementExist2(properties.getProperty("ViewChkBox1"), 5)) {
	    	WebElement vewchkbox = driver.findElement(By.cssSelector(properties.getProperty("ViewChkBox1")));

	    	if(vewchkbox.isSelected()){
	    		log.logLine(Testname, false, "Viewable check box is already selected");
	    	}else{
	    		((JavascriptExecutor) driver).executeScript("arguments[0].click()", vewchkbox);
	    		Thread.sleep(1000);
	    		log.logLine(Testname, false, "Selecting the Viewable check box ");
	    	}

	    	log.logLine(Testname, false, "Selecting the viewable checkbox is successful");
	    }else{
	    	log.logLine(Testname, true, "Unbale to select the viewable chekbox");
	    	throw new Exception("Unbale to select the viewable chekbox");

	    }

	    uploadNewDocumentsCompleteBtn(Testname);
	    checkAlert();



	    uploadDocumentsBtn(Testname);

		if (doesElementExist2(properties.getProperty("Browse1"), 5)) {	    
			WebElement docfld = driver.findElement(By.cssSelector(properties.getProperty("Browse1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", docfld);
			docfld.sendKeys(pubDoc3);			
			log.logLine(Testname, false, "Entering the Ref code into upload report popup");
	    } else {
			log.logLine(Testname, true, "Entering the Ref code into upload report popup is failed");
			throw new Exception("Entering the Ref code into upload report popup is failed");
		}

		if (doesElementExist2(properties.getProperty("EffEndDate1"), 5)) {	    
			WebElement date = driver.findElement(By.cssSelector(properties.getProperty("EffEndDate1")));
			date.clear();
			date.sendKeys(futureDate3);
			log.logLine(Testname, false, "Entering the Ref code into upload report popup");
	    } else {
			log.logLine(Testname, true, "Entering the Ref code into upload report popup is failed");
			throw new Exception("Entering the Ref code into upload report popup is failed");
		}


	    if (doesElementExist2(properties.getProperty("DocDescption1"), 5)) {	    
			WebElement descptn = driver.findElement(By.cssSelector(properties.getProperty("DocDescption1")));
			descptn.clear();
			descptn.sendKeys(descpr3);			
			log.logLine(Testname, false, "Entering the Ref code into upload report popup");
	    } else {
			log.logLine(Testname, true, "Entering the Ref code into upload report popup is failed");
			throw new Exception("Entering the Ref code into upload report popup is failed");
		}

	    if (doesElementExist2(properties.getProperty("ViewChkBox1"), 5)) {
	    	WebElement vewchkbox = driver.findElement(By.cssSelector(properties.getProperty("ViewChkBox1")));

	    	if(vewchkbox.isSelected()){
	    		log.logLine(Testname, false, "Viewable check box is already selected");
	    	}else{
	    		((JavascriptExecutor) driver).executeScript("arguments[0].click()", vewchkbox);
	    		Thread.sleep(1000);
	    		log.logLine(Testname, false, "Selecting the Viewable check box ");
	    	}

	    	log.logLine(Testname, false, "Selecting the viewable checkbox is successful");
	    }else{
	    	log.logLine(Testname, true, "Unbale to select the viewable chekbox");
	    	throw new Exception("Unbale to select the viewable chekbox");

	    }

	    uploadNewDocumentsCompleteBtn(Testname);
	    checkAlert();


	    uploadDocumentsBtn(Testname);

		if (doesElementExist2(properties.getProperty("Browse1"), 5)) {	    
			WebElement docfld = driver.findElement(By.cssSelector(properties.getProperty("Browse1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", docfld);
			docfld.sendKeys(pubDoc4);			
			log.logLine(Testname, false, "Entering the Ref code into upload report popup");
	    } else {
			log.logLine(Testname, true, "Entering the Ref code into upload report popup is failed");
			throw new Exception("Entering the Ref code into upload report popup is failed");
		}

		if (doesElementExist2(properties.getProperty("EffEndDate1"), 5)) {	    
			WebElement date = driver.findElement(By.cssSelector(properties.getProperty("EffEndDate1")));
			date.clear();
			date.sendKeys(futureDate4);
			log.logLine(Testname, false, "Entering the Ref code into upload report popup");
	    } else {
			log.logLine(Testname, true, "Entering the Ref code into upload report popup is failed");
			throw new Exception("Entering the Ref code into upload report popup is failed");
		}


	    if (doesElementExist2(properties.getProperty("DocDescption1"), 5)) {	    
			WebElement descptn = driver.findElement(By.cssSelector(properties.getProperty("DocDescption1")));
			descptn.clear();
			descptn.sendKeys(descpr4);			
			log.logLine(Testname, false, "Entering the Ref code into upload report popup");
	    } else {
			log.logLine(Testname, true, "Entering the Ref code into upload report popup is failed");
			throw new Exception("Entering the Ref code into upload report popup is failed");
		}

	    if (doesElementExist2(properties.getProperty("ViewChkBox1"), 5)) {
	    	WebElement vewchkbox = driver.findElement(By.cssSelector(properties.getProperty("ViewChkBox1")));

	    	if(vewchkbox.isSelected()){
	    		log.logLine(Testname, false, "Viewable check box is already selected");
	    	}else{
	    		((JavascriptExecutor) driver).executeScript("arguments[0].click()", vewchkbox);
	    		Thread.sleep(1000);
	    		log.logLine(Testname, false, "Selecting the Viewable check box ");
	    	}

	    	log.logLine(Testname, false, "Selecting the viewable checkbox is successful");
	    }else{
	    	log.logLine(Testname, true, "Unbale to select the viewable chekbox");
	    	throw new Exception("Unbale to select the viewable chekbox");

	    }

	    uploadNewDocumentsCompleteBtn(Testname);
	    checkAlert();


		return true;
	}*/


	public boolean SrchPDFTPupload(String Testname) throws Exception{

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		driver.switchTo().defaultContent();


		if (doesElementExist2(properties.getProperty("Archives"), 5)) {    
			WebElement arclnk = driver.findElement(By.cssSelector(properties.getProperty("Archives")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", arclnk);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Navigation to Archives page is successfull");
		} else {
			log.logLine(Testname, true, "Clicking on Archives menu is failed");
			throw new Exception("Clicking on Archives menu is failed");
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
						Thread.sleep(2000);
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
							Thread.sleep(3000);
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
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on OK button to view the Archives");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the Archives is failed");
			throw new Exception("Clicking on OK button to view the Archives is failed");
		}


		clickOnPublicDocmntTool(Testname);

		//Search document uploaded through FTP
		MultiSrchOperations(Testname, "Document Name", CreateAppData.PDocument1);

		if (doesElementExist2(properties.getProperty("ReadDocName"),5)){
			String name= driver.findElement(By.cssSelector(properties.getProperty("ReadDocName"))).getText(); 
			if(name.equals(pubDoc4)){
				log.logLine(Testname, false, "Uploaded Searched documents through FTP exist in the public documents grid");
			}else{
				log.logLine(Testname, true, "Uploaded Searched documents through FTP exist in the public documents grid is failed");
			}
		}else{
			log.logLine(Testname, true, "Expected document(FTP uplaoded) is not displayed and search operation is unsucessful");
			driver.switchTo().defaultContent();
			throw new Exception("Expected document(FTP uplaoded) is not displayed and search operation is unsucessful");
		}

		driver.switchTo().defaultContent();
		return true;
	}


	public boolean SearchPublicDocs(String Testname) throws Exception{

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	    

		pubDoc4 = test.readColumnData("DocumentName6", sheetname);
		descpr2 = test.readColumnData("DocDescription4", sheetname);
		String descpr1 = test.readColumnData("DocDescription2", sheetname);


		MultiSrchOperations(Testname, "Description", descpr2);

		if (doesElementExist2(properties.getProperty("ReadDesp"),5)){
			String decptn= driver.findElement(By.cssSelector(properties.getProperty("ReadDesp"))).getText(); 
			if(decptn.equals(descpr2)){
				log.logLine(Testname, false, "Searched documents exist in the public documents grid");
			}else{
				log.logLine(Testname, true, "Searched documents does not exist in the public documents grid");
			}
			log.logLine(Testname, false, "Expected document is displayed and search operation is sucessful");
		}else{
			log.logLine(Testname, true, "Expected document is not displayed and search operation is unsucessful");
			throw new Exception("Expected document is not displayed and search operation is unsucessful");
		}


		MultiSrchOperations(Testname, "Document Name", pubDoc4);

		if (doesElementExist2(properties.getProperty("ReadDocName"),5)){
			String name= driver.findElement(By.cssSelector(properties.getProperty("ReadDocName"))).getText(); 
			if(name.equals(pubDoc4)){
				log.logLine(Testname, false, "Searched documents exist in the public documents grid");
			}else{
				log.logLine(Testname, true, "Searched documents does not exist in the public documents grid");
			}
			log.logLine(Testname, false, "Expected document is displayed and search operation is sucessful");
		}else{
			log.logLine(Testname, true, "Expected document is not displayed and search operation is unsucessful");
			throw new Exception("Expected document is not displayed and search operation is unsucessful");
		}


		MultiSrchOperations(Testname, "Description", descpr1);

		if (doesElementExist2(properties.getProperty("ReadDesp"),5)){
			String decptn= driver.findElement(By.cssSelector(properties.getProperty("ReadDesp"))).getText(); 
			if(decptn.equals(descpr1)){
				log.logLine(Testname, false, "Searched documents exist in the public documents grid");
			}else{
				log.logLine(Testname, true, "Searched documents does not exist in the public documents grid");
			}
			log.logLine(Testname, false, "Expected document is displayed and search operation is sucessful");
		}else{
			log.logLine(Testname, true, "Expected document is not displayed and search operation is unsucessful");
			throw new Exception("Expected document is not displayed and search operation is unsucessful");
		}


		MultiSrchViewable(Testname, "Viewable", "No");


		if (doesElementExist2(properties.getProperty("ReadViewableNo"), 5)) {
			WebElement no = driver.findElement(By.cssSelector(properties.getProperty("ReadViewableNo")));
			log.logLine(Testname, false, "Non Viewable documents is dispalyed in the grid");
		} else {
			log.logLine(Testname, true, "Non Viewable documents is not dispalyed in the grid");
			throw new Exception("Non Viewable documents is not dispalyed in the grid");
		}

		MultiSrchViewable(Testname, "Viewable", "Yes");

		if (doesElementExist2(properties.getProperty("ReadViewableYes"), 5)) {
			WebElement yes = driver.findElement(By.cssSelector(properties.getProperty("ReadViewableYes")));
			log.logLine(Testname, false, "Viewable documents is dispalyed in the grid");
		} else {
			log.logLine(Testname, true, "Viewable documents is not dispalyed in the grid");
			throw new Exception("Viewable documents is not dispalyed in the grid");
		}


		/*



		SearchButton(Testname);

		if (doesElementExist2(properties.getProperty("clear"), 5)) {
			 WebElement clr = driver.findElement(By.cssSelector(properties.getProperty("clear")));
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", clr);
			 Thread.sleep(1000);
			 log.logLine(Testname, false, "Click on All Date Checkbox is Successfull");
		} else {
			 log.logLine(Testname, true, "Click on All Date Checkbox is Failed");
			 driver.switchTo().defaultContent();
			 throw new Exception("Click on All Date Checkbox is Failed");
		 }


		if (doesElementExist2(properties.getProperty("AllDatesChkBox"), 5)) {
			 WebElement alldte = driver.findElement(By.cssSelector(properties.getProperty("AllDatesChkBox")));
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", alldte);
			 Thread.sleep(1000);
			 log.logLine(Testname, false, "Click on All Date Checkbox is Successfull");
		} else {
			 log.logLine(Testname, true, "Click on All Date Checkbox is Failed");
			 driver.switchTo().defaultContent();
			 throw new Exception("Click on All Date Checkbox is Failed");
		 }

		if (doesElementExist2(properties.getProperty("PublicDocSearch"), 5)) {    
	    	WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("PublicDocSearch")));
	   		((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(2000);
	   		PleasewaitDisappear();
	   		log.logLine(Testname, false, "Clicking on Search button to view the Public Documents");
	    } else {
	    	log.logLine(Testname, true, "Clicking on Search button to view the Public Documents is failed");
		    throw new Exception("Clicking on Search button to view the Public Documents is failed");
	    }*/




		return true;
	}



	public boolean pd_displayActions(String Testname, String RandNo) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Description = test.readColumnData("DocDescription", sheetname);

		Calendar currentDate = Calendar.getInstance(); 
		SimpleDateFormat formatter= new SimpleDateFormat("MM/dd/yyyy"); 
		currentDate.add(Calendar.DAY_OF_MONTH, 7); 
		futureDate = formatter.format(currentDate.getTime()); 


		if (doesElementExist2(properties.getProperty("LoadDate"),5)){
			String date= driver.findElement(By.cssSelector(properties.getProperty("LoadDate"))).getText(); 
			log.logLine(Testname, false, "Documents are displayed once clicked on Public Documents link");
		}else{
			log.logLine(Testname, true, "Documents are not displayed once clicked on Public Documents link");
		} 

		if (doesElementExist2(properties.getProperty("ViewDoc" ), 5)) {
			WebElement viewrptbtn = driver.findElement(By.cssSelector(properties.getProperty("ViewDoc")));		    		
			action.click(viewrptbtn).perform(); //Click	    		
			//((JavascriptExecutor)driver).executeScript("arguments[0].style.visibility = 'visible'; arguments[0].style.height = '1px'; arguments[0].style.width = '1px'; arguments[0].style.opacity = 1", viewrptbtn);
			Thread.sleep(15000);

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

					if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetExplorer"))) {
						if (doesElementExist2(properties.getProperty("OverrideLnk"), 5)) {
							driver.get("javascript:document.getElementById('overridelink').click();");
							Thread.sleep(8000);
						}
					}

					Thread.sleep(3000);
					driver.close();

					// Switching back to parent window
					driver.switchTo().window(firstWinHandle);

					Thread.sleep(2000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("iframeContainer").switchTo().frame("iframePublicDocs");
					//driver.switchTo().frame("iframeContainer");
				}
			}							    

			log.logLine(Testname, false, "Click on Document  link to view the Document in the public documents grid is successful");
		}else{
			log.logLine(Testname, false, "Click on Document  link to view the Document in the public documents grid is unsuccessful");
			throw new Exception("Click on Document  link to view the Document in the public documents grid is unsuccessful");
		}


		Thread.sleep(1000);
		if (doesElementExist(".//*[@id='grid-publicdocuments']/div[2]/table/tbody/tr[1]/td[7]/span/span/span[1]", 5)) {
			log.logLine(Testname, false, "Advanced search based on From and To dates successful");

			WebElement choseacts = driver.findElement(By.xpath(".//*[@id='grid-publicdocuments']/div[2]/table/tbody/tr[1]/td[7]/span/span/span[1]"));
			log.logLine(Testname, false, "Clicking on Choose Action to view the document..");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
			Thread.sleep(1000);
			List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SelectOptnFrmChooseAction")));	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals("View Item")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					//action.click(lnk).perform();
					
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

							Thread.sleep(3000);
							driver.close();

							// Switching back to parent window
							driver.switchTo().window(firstWinHandle);

							Thread.sleep(2000);
							driver.switchTo().defaultContent();
							driver.switchTo().frame("iframeContainer").switchTo().frame("iframePublicDocs");
						}
					}							    
					break;
				}
			}
			log.logLine(Testname, false, "Click on \"View Item\" option form choose actions list in the public documents grid is successful");
		}else{
			log.logLine(Testname, false, "Clicking on \"View Item\" option form choose actions list in the public documents grid is unsuccessful");
			throw new Exception("Clicking on \"View Item\" option form choose actions list in the public documents grid is unsuccessful");

		}

		SelectOptionFromChooseActions(Testname, "Edit");
		SelectOptionFromChooseActions(Testname, "Cancel");
		Thread.sleep(2000);
		SelectOptionFromChooseActions(Testname, "Edit");		    
		EditDocumentDescription(Testname);
		EditEffectiveEndDate(Testname);
		EditViewableChkBox(Testname);
		SelectOptionFromChooseActions(Testname, "Update");

		// Document View for Non-Viewable
        Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("LoadDate"),5)){
			String date= driver.findElement(By.cssSelector(properties.getProperty("LoadDate"))).getText(); 
			log.logLine(Testname, false, "Documents are displayed once clicked on Public Documents link");
		}else{
			log.logLine(Testname, true, "Documents are not displayed once clicked on Public Documents link");
		} 

		if (doesElementExist2(properties.getProperty("ViewDoc" ), 5)) {
			WebElement viewrptbtn = driver.findElement(By.cssSelector(properties.getProperty("ViewDoc")));		    		
			action.click(viewrptbtn).perform(); //Click	    		
			//((JavascriptExecutor)driver).executeScript("arguments[0].style.visibility = 'visible'; arguments[0].style.height = '1px'; arguments[0].style.width = '1px'; arguments[0].style.opacity = 1", viewrptbtn);
			Thread.sleep(25000);

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

					if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetExplorer"))) {
						if (doesElementExist2(properties.getProperty("OverrideLnk"), 5)) {
							driver.get("javascript:document.getElementById('overridelink').click();");
							Thread.sleep(8000);
						}
					}

					Thread.sleep(3000);
					driver.close();

					// Switching back to parent window
					driver.switchTo().window(firstWinHandle);

					Thread.sleep(2000);
					driver.switchTo().defaultContent();
					driver.switchTo().frame("iframeContainer").switchTo().frame("iframePublicDocs");
					//driver.switchTo().frame("iframeContainer");
				}
			}							    

			log.logLine(Testname, false, "Click on Document  link to view the Document in the public documents grid is successful");
		}else{
			log.logLine(Testname, false, "Click on Document  link to view the Document in the public documents grid is unsuccessful");
			throw new Exception("Click on Document  link to view the Document in the public documents grid is unsuccessful");
		}



		////////////Copy link///////////////

		//SelectOptionFromChooseActions(Testname, "Copy Link");
		
		/*if (doesElementExist2(properties.getProperty("Copylnk" ), 5)) {
			Cpytxt = driver.findElement(By.cssSelector(properties.getProperty("Copylnk"))).getAttribute("data-clipboard-text");	

		}*/

		/*if (doesElementExist(".//*[@id='grid-publicdocuments']/div[2]/table/tbody/tr[1]/td[7]/span/span/span[1]", 5)) {
			log.logLine(Testname, false, "Advanced search based on From and To dates successful");

			WebElement choseacts = driver.findElement(By.xpath(".//*[@id='grid-publicdocuments']/div[2]/table/tbody/tr[1]/td[7]/span/span/span[1]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
			log.logLine(Testname, false, "Clicking on Choose Action to view the document..");
			Thread.sleep(1000);
			List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SelectOptnFrmChooseAction")));	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals("Copy Link")) {
					Highlight(lnk);
					Cpytxt=lnk.getText().toString();
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(5000);
					log.logLine(Testname, false, "Click on  choose actions list in the public documents and selecting the option as Copy Link");
					break;
				}
			}
		}else{
			log.logLine(Testname, false, "Clicking on \"View Item\" option form choose actions list in the public documents grid is unsuccessful");
			throw new Exception("Clicking on \"View Item\" option form choose actions list in the public documents grid is unsuccessful");

		}*/

	/*//	driver.switchTo().defaultContent();

		Actions builder = new Actions(driver);	
	    WebElement mnuElement = driver.findElement(By.xpath(properties.getProperty("AdminMenu")));	
	    if (doesElementExist(properties.getProperty("AdminMenu"), 10)) {	
	    	// Move cursor to the Main Menu Element 
	    	builder.moveToElement(mnuElement).perform();	
	    	// Clicking on the Hidden SubMenu 
	    	Thread.sleep(5000);
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

		Thread.sleep(15000);
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
				driver.get(Cpytxt);
			
				
				Thread.sleep(15000);

				driver.close();
				driver.switchTo().window(firstWinHandle);
			}
		}

*/
		////////////////////////////////////

	//	driver.switchTo().frame("iframeContainer").switchTo().frame("iframePublicDocs");
		Thread.sleep(5000);
		SelectOptionFromChooseActions(Testname, "Delete");

		Thread.sleep(8000);
		Alert alrt = driver.switchTo().alert();
		alrt.dismiss();
		log.logLine(Testname, false, "Cancelling the delete public document operation");

		SelectOptionFromChooseActions(Testname, "Delete");
		Thread.sleep(8000);
		alrt.accept();
		log.logLine(Testname, false, "Allowing the delete public document operation");

		Thread.sleep(8000);
		alrt.accept();



		return true;
	}


	public void checkAlert() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.alertIsPresent());
			Thread.sleep(5000);
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public boolean uploadDocumentsBtn(String Testname) throws Exception{

		if (doesElementExist2(properties.getProperty("UploadDocBtn"), 5)) {    
			WebElement uplDoc = driver.findElement(By.cssSelector(properties.getProperty("UploadDocBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", uplDoc);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicking on \"Upload Documnets\" to upload the public documents");
		} else {
			log.logLine(Testname, true, "Clicking on \"Upload Documnets\" to upload the public documents is failed");
			throw new Exception("Clicking on \"Upload Documnets\" to upload the public documents is failed");
		} 

		return true;
	}


	public boolean uploadNewDocumentsCompleteBtn(String Testname) throws Exception{

		if (doesElementExist2(properties.getProperty("UploadProcessBtn"), 5)) {    
			WebElement uplDoc = driver.findElement(By.cssSelector(properties.getProperty("UploadProcessBtn")));
			//uplDoc.click();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", uplDoc);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicking on \"Upload Documnets\" to upload the public documents");
		} else {
			log.logLine(Testname, true, "Clicking on \"Upload Documnets\" to upload the public documents is failed");
			throw new Exception("Clicking on \"Upload Documnets\" to upload the public documents is failed");
		} 


		return true;
	}


	public boolean pd_MultipleRecordDeleteActions(String Testname)  throws Exception{


		if (doesElementExist2(properties.getProperty("DocumentName1"), 5)) {    
			doc1 = driver.findElement(By.cssSelector(properties.getProperty("DocumentName1"))).getText();
			log.logLine(Testname, false, "Reading the first document file name");
		} else {
			log.logLine(Testname, true, "Reading the first document file name is failed");
			throw new Exception("Reading the first document file name is failed");
		} 

		if (doesElementExist2(properties.getProperty("DocumentName2"), 5)) {    
			doc2 = driver.findElement(By.cssSelector(properties.getProperty("DocumentName2"))).getText();
			log.logLine(Testname, false, "Reading the second document file name");
		} else {
			log.logLine(Testname, true, "Reading the second document file name is failed");
			throw new Exception("Reading the second document file name is failed");
		}


		if (doesElementExist2(properties.getProperty("ViewChk1"), 5)) {    
			WebElement view1 = driver.findElement(By.cssSelector(properties.getProperty("ViewChk1")));

			if(view1.isSelected()){
				log.logLine(Testname, false, "First checkbox is already selected ");
			}else{
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", view1);
				log.logLine(Testname, false, "Selecting the First checkbox from public documents grid");
			}

			log.logLine(Testname, false, "Clicking the first check box is successfull");
		} else {
			log.logLine(Testname, true, "Clicking the first check box is unsuccessfull");
			throw new Exception("Clicking the first check box is unsuccessfull");
		} 


		if (doesElementExist2(properties.getProperty("ViewChk2"), 5)) {    
			WebElement view2 = driver.findElement(By.cssSelector(properties.getProperty("ViewChk2")));

			if(view2.isSelected()){
				log.logLine(Testname, false, "First checkbox is already selected ");
			}else{
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", view2);
				Thread.sleep(1000);
				log.logLine(Testname, false, "Selecting the First checkbox from public documents grid");
			}

			log.logLine(Testname, false, "Clicking the first check box is successfull");
		} else {
			log.logLine(Testname, true, "Clicking the first check box is unsuccessfull");
			throw new Exception("Clicking the first check box is unsuccessfull");
		} 


		if (doesElementExist(properties.getProperty("MultiActions"), 5)) {	    	
			WebElement multiacts = driver.findElement(By.xpath(properties.getProperty("MultiActions")));
			log.logLine(Testname, false, "Clicking on Choose Action to is sucessful..");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", multiacts);
			Thread.sleep(1000);

			List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SelectOptnFrmMultiActions")));	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals("Delete")) {
					action.click(lnk).perform();
					Thread.sleep(8000);
					log.logLine(Testname, false, "Selecting the option of choose actions is sucessful");
					break;
				}

			} 
		}else {
			log.logLine(Testname, true, "Clicking on ChooseActions in the documents grid is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on ChooseActions in the documents grid is failed");
		}

		checkAlert();

		Thread.sleep(6000);
		Alert alrt = driver.switchTo().alert();
		alrt.accept();

		Thread.sleep(3000);



		String[] Sort1 = new String[20];
		String row = "tr";
		List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='grid-publicdocuments']/div[2]/table/tbody/tr"));

		if(doesElementExist2(properties.getProperty("DocNameHeader"), 5)){
			for(int i = 0; i < DataCnt.size(); i++) {
				Sort1[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row+" td+td a")).getText();
				if(!Sort1[i].equals(doc1)||Sort1[i].equals(doc2)){
					log.logLine(Testname, false, "Deleting the document is sucessful");
				}else{
					log.logLine(Testname, true, "Deletion of the multiple documents is not successful");
					break;
				}
				row = row + "+tr";
				log.logLine(Testname, false, "Iterating through the Rows....Rows Have the Status as "+Sort1[i]);
			}

		}	

		return true;
	}



	public boolean pd_DeleteRecords(String Testname)  throws Exception{


		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();



		for(int cnt=0; cnt<7; cnt++){

			driver.switchTo().defaultContent();
			driver.switchTo().frame("iframeContainer").switchTo().frame("iframePublicDocs");

			pubDoc = test.readColumnData("DocumentName"+Integer.toString(cnt), sheetname);			


			SearchButton(Testname);

			if (doesElementExist2(properties.getProperty("clear"), 5)) {
				WebElement clr = driver.findElement(By.cssSelector(properties.getProperty("clear")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", clr);
				Thread.sleep(1000);
				log.logLine(Testname, false, "Click on Clear button is Successfull");
			} else {
				log.logLine(Testname, true, "Click on Clear button is Failed");
				driver.switchTo().defaultContent();
				throw new Exception("Click on Clear button is Failed");
			}


			if (doesElementExist2(properties.getProperty("AllDatesChkBox"), 5)) {
				WebElement alldte = driver.findElement(By.cssSelector(properties.getProperty("AllDatesChkBox")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", alldte);
				Thread.sleep(1000);
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
				Thread.sleep(500);

				log.logLine(Testname, false, "Clicking on Seacrh criteria Field drop down list in advance search");

				if (doesElementExist2(properties.getProperty("SelectOptnFrmChooseAction"), 5)) {

					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelectOptnFrmChooseAction")));
					for (WebElement lnk:selopts) {
						if (lnk.getText().equals("Document Name")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false, "Selecting the Field option Document Name from the dropdown");
							break;
						}						
					}
				} else {				
					log.logLine(Testname, true, "Selecting the field option Document Name is failed");
					driver.switchTo().defaultContent();
					throw new Exception("Selecting the field option Document Name is failed");				
				}		

			}else {
				log.logLine(Testname, true, "Clicking on the Field is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on the Field is failed");
			}


			if (doesElementExist2(properties.getProperty("SearchText"), 5)) {
				WebElement searchtxt = driver.findElement(By.cssSelector(properties.getProperty("SearchText")));
				searchtxt.clear();
				searchtxt.sendKeys(pubDoc);
				Thread.sleep(1000);
				log.logLine(Testname, false, "Click on All Date Checkbox is Successfull");
			} else {
				log.logLine(Testname, true, "Click on All Date Checkbox is Failed");
				driver.switchTo().defaultContent();
				throw new Exception("Click on All Date Checkbox is Failed");
			}


			if (doesElementExist2(properties.getProperty("PublicDocSearch"), 5)) {    
				WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("PublicDocSearch")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
				Thread.sleep(2000);
				PleasewaitDisappear();
				log.logLine(Testname, false, "Clicking on Search button to view the Public Documents");
			} else {
				log.logLine(Testname, true, "Clicking on Search button to view the Public Documents is failed");
				throw new Exception("Clicking on Search button to view the Public Documents is failed");
			}

			if (doesElementExist2(properties.getProperty("DocumentName1"), 5)) {    
				doc1 = driver.findElement(By.cssSelector(properties.getProperty("DocumentName1"))).getText();
				log.logLine(Testname, false, "Searched document exist in the grid");

				SelectOptionFromChooseActions(Testname, "Delete");
				checkAlert();
				log.logLine(Testname, false, "Allowing the delete public document operation");

				Thread.sleep(8000);
				Alert alrt = driver.switchTo().alert();
				alrt.accept();

			} else if (doesElementExist2(properties.getProperty("NoDocuments"), 5)){
				log.logLine(Testname, false, "No Documents exist in the grid");
			} else{
				log.logLine(Testname, false, "No Documents exist in the grid");
			}


			driver.switchTo().defaultContent();	    		   



		}

		return true;
	}



	public boolean pd_MultipleRecordViewableActions(String Testname)  throws Exception{

		MultiSrchViewable(Testname, "Viewable", "No");		

		if (doesElementExist2(properties.getProperty("DocumentName1"), 5)) {    
			doc1 = driver.findElement(By.cssSelector(properties.getProperty("DocumentName1"))).getText();
			log.logLine(Testname, false, "Reading the first document file name");
		} else {
			log.logLine(Testname, true, "Reading the first document file name is failed");

		} 

		if (doesElementExist2(properties.getProperty("DocumentName2"), 5)) {    
			doc2 = driver.findElement(By.cssSelector(properties.getProperty("DocumentName2"))).getText();
			log.logLine(Testname, false, "Reading the second document file name");
		} else {
			log.logLine(Testname, true, "Reading the second document file name is failed");

		}


		if (doesElementExist2(properties.getProperty("ViewChk1"), 5)) {    
			WebElement view1 = driver.findElement(By.cssSelector(properties.getProperty("ViewChk1")));

			if(view1.isSelected()){
				log.logLine(Testname, false, "First checkbox is already selected ");
			}else{
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", view1);
				log.logLine(Testname, false, "Selecting the First checkbox from public documents grid");
			}

			log.logLine(Testname, false, "Clicking the first check box is successfull");
		} else {
			log.logLine(Testname, true, "Clicking the first check box is unsuccessfull");
			//throw new Exception("Clicking the first check box is unsuccessfull");
		} 


		if (doesElementExist2(properties.getProperty("ViewChk2"), 5)) {    
			WebElement view2 = driver.findElement(By.cssSelector(properties.getProperty("ViewChk2")));

			if(view2.isSelected()){
				log.logLine(Testname, false, "First checkbox is already selected ");
			}else{
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", view2);
				Thread.sleep(1000);
				log.logLine(Testname, false, "Selecting the First checkbox from public documents grid");
			}

			log.logLine(Testname, false, "Clicking the first check box is successfull");
		} else {
			log.logLine(Testname, true, "Clicking the first check box is unsuccessfull");
			//throw new Exception("Clicking the first check box is unsuccessfull");
		} 


		if (doesElementExist(properties.getProperty("MultiActions"), 5)) {	    	
			WebElement multiacts = driver.findElement(By.xpath(properties.getProperty("MultiActions")));
			log.logLine(Testname, false, "Clicking on Choose Action to is sucessful..");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", multiacts);
			Thread.sleep(1000);

			List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SelectOptnFrmMultiActions")));	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals("Make Viewable")) {
					action.click(lnk).perform();
					Thread.sleep(8000);
					log.logLine(Testname, false, "Selecting the option of choose actions is sucessful");
					break;
				}

			} 
		}else {
			log.logLine(Testname, true, "Clicking on ChooseActions in the documents grid is failed");
			throw new Exception("Clicking on ChooseActions in the documents grid is failed");
		}

		checkAlert();

		MultiSrchViewable(Testname, "Viewable", "Yes");


		String[] Sort1 = new String[20];
		String row = "tr";
		List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='grid-publicdocuments']/div[2]/table/tbody/tr"));

		if(doesElementExist2(properties.getProperty("DocNameHeader"), 5)){
			for(int i = 0; i < DataCnt.size(); i++) {
				Sort1[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row+" td+td a")).getText();
				if(Sort1[i].equals(doc1)||Sort1[i].equals(doc2)){
					log.logLine(Testname, false, "Making Non viewable document to viewable is sucessful");
				}
				row = row + "+tr";
				log.logLine(Testname, false, "Iterating through the viewable Rows....Rows Have the Status as "+Sort1[i]);
			}

		}


		return true;
	}





	public void MultiSrchOperations(String Testname, String Field, String TData) throws Exception {


		SearchButton(Testname);

		if (doesElementExist2(properties.getProperty("clear"), 5)) {
			WebElement clr = driver.findElement(By.cssSelector(properties.getProperty("clear")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clr);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on All Date Checkbox is Successfull");
		} else {
			log.logLine(Testname, true, "Click on All Date Checkbox is Failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on All Date Checkbox is Failed");
		}


		if (doesElementExist2(properties.getProperty("AllDatesChkBox"), 5)) {
			WebElement alldte = driver.findElement(By.cssSelector(properties.getProperty("AllDatesChkBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", alldte);
			Thread.sleep(4000);
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
			Thread.sleep(4000);

			log.logLine(Testname, false, "Clicking on Seacrh criteria Field drop down list in advance search");

			if (doesElementExist2(properties.getProperty("SelectOptnFrmChooseAction"), 5)) {

				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelectOptnFrmChooseAction")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(Field)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(4000);
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


		if (doesElementExist2(properties.getProperty("SearchText"), 5)) {
			WebElement searchtxt = driver.findElement(By.cssSelector(properties.getProperty("SearchText")));
			searchtxt.clear();
			searchtxt.sendKeys(TData);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on All Date Checkbox is Successfull");
		} else {
			log.logLine(Testname, true, "Click on All Date Checkbox is Failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on All Date Checkbox is Failed");
		}


		if (doesElementExist2(properties.getProperty("PublicDocSearch"), 5)) {    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("PublicDocSearch")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(8000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button to view the Public Documents");
		} else {
			log.logLine(Testname, true, "Clicking on Search button to view the Public Documents is failed");
			throw new Exception("Clicking on Search button to view the Public Documents is failed");
		}


	}


	public void MultiSrchViewable(String Testname, String Field1, String Field2) throws Exception {


		SearchButton(Testname);

		if (doesElementExist2(properties.getProperty("clear"), 5)) {
			WebElement clr = driver.findElement(By.cssSelector(properties.getProperty("clear")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clr);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Click on All Date Checkbox is Successfull");
		} else {
			log.logLine(Testname, true, "Click on All Date Checkbox is Failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on All Date Checkbox is Failed");
		}


		if (doesElementExist2(properties.getProperty("AllDatesChkBox"), 5)) {
			WebElement alldte = driver.findElement(By.cssSelector(properties.getProperty("AllDatesChkBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", alldte);
			Thread.sleep(1000);
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

			if (doesElementExist2(properties.getProperty("SelectOptnFrmChooseAction"), 5)) {

				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelectOptnFrmChooseAction")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(Field1)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Field option  "+Field1 +" from the dropdown");
						break;
					}						
				}
			} else {				
				log.logLine(Testname, true, "Selecting the field option "+Field1+" is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Selecting the field option "+Field1+" is failed");				
			}		

		}else {
			log.logLine(Testname, true, "Clicking on the Field is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on the Field is failed");
		}

		if (doesElementExist(".//*[@id='archive-sections-public-docs']/div[1]/div[1]/div/div/div[1]/div[2]/div[2]/div/span[3]/span/span[1]", 5)) {	    
			WebElement optr = driver.findElement(By.xpath(".//*[@id='archive-sections-public-docs']/div[1]/div[1]/div/div/div[1]/div[2]/div[2]/div/span[3]/span/span[1]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(1000);

			log.logLine(Testname, false, "Clicking on Seacrh criteria Field drop down list in advance search");

			if (doesElementExist2(properties.getProperty("SelectOptnFrmChooseAction"), 5)) {

				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelectOptnFrmChooseAction")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(Field2)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Field option  "+Field2 +" from the dropdown");
						break;
					}						
				}
			} else {				
				log.logLine(Testname, true, "Selecting the field option "+Field2+" is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Selecting the field option "+Field2+" is failed");				
			}		

		}else {
			log.logLine(Testname, true, "Clicking on the Field is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on the Field is failed");
		}

		if (doesElementExist2(properties.getProperty("PublicDocSearch"), 5)) {    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("PublicDocSearch")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button to view the Public Documents");
		} else {
			log.logLine(Testname, true, "Clicking on Search button to view the Public Documents is failed");
			throw new Exception("Clicking on Search button to view the Public Documents is failed");
		}

	}


	public boolean SelectOptionFromChooseActions(String Testname, String Option) throws Exception {

		Thread.sleep(3000);
		if (doesElementExist(".//*[@id='grid-publicdocuments']/div[2]/table/tbody/tr[1]/td[7]/span/span/span[1]", 5)) {	
			log.logLine(Testname, false, "Advanced search based on From and To dates successful");

			WebElement choseacts = driver.findElement(By.xpath(".//*[@id='grid-publicdocuments']/div[2]/table/tbody/tr[1]/td[7]/span/span/span[1]"));
			log.logLine(Testname, false, "Clicking on Choose Action to is sucessful..");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
			Thread.sleep(1000);
			List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SelectOptnFrmChooseAction")));	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals(Option)) {
					action.click(lnk).perform();
					Thread.sleep(2000);
					log.logLine(Testname, false, "Selecting the option of choose actions is sucessful");
					break;
				}

			} 
		}else {
			log.logLine(Testname, true, "Clicking on ChooseActions in the documents grid is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Clicking on ChooseActions in the documents grid is failed");
		}

		return true;
	}

	public boolean EditDocumentDescription(String Testname) throws Exception {

		WebElement retelm = waitForElement(properties.getProperty("DocDecrption"));
		if (doesElementExist2(properties.getProperty("DocDecrption"), 5)){	    	
			WebElement docDesc = driver.findElement(By.cssSelector(properties.getProperty("DocDecrption")));
			docDesc.clear();
			docDesc.sendKeys(Description);
			log.logLine(Testname, false, "Entering the description in Document Description field as "+Description);	
		}else {
			log.logLine(Testname, true, "Entering the description in Document Description field is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the description in Document Description field is failed");
		}

		return true;
	}

	public boolean EditEffectiveEndDate(String Testname) throws Exception {

		WebElement retelm = waitForElement(properties.getProperty("EfftveEndDate"));
		if (doesElementExist2(properties.getProperty("EfftveEndDate"), 5)){ 	
			WebElement effDate = driver.findElement(By.cssSelector(properties.getProperty("EfftveEndDate")));
			effDate.clear();
			effDate.sendKeys(futureDate);
			log.logLine(Testname, false, "Entering the date in Effective EndDate field as "+futureDate);

		}else {
			log.logLine(Testname, true, "Entering the date in Effective EndDate field is failed");
			throw new Exception("Entering the date in Effective EndDate field is failed");
		}

		return true;
	}


	public boolean EditViewableChkBox(String Testname) throws Exception{
		Thread.sleep(2000);

		if (doesElementExist2(properties.getProperty("ViewableChkBox"), 5)){ 	
			WebElement view = driver.findElement(By.cssSelector(properties.getProperty("ViewableChkBox")));

			if (view.isSelected()){
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", view);
				log.logLine(Testname, false, "Viewable checkbox is selected so changing it deselect");
			}else{
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", view);
				log.logLine(Testname, false, "Viewable checkbox is not selected so changing it select");
			}
			log.logLine(Testname, false, "Viewable checkbox is present");
		}else {
			log.logLine(Testname, true, "Viewable checkbox is not present");
			throw new Exception("Viewable checkbox is not present");
		}

		return true;
	}


	public boolean SearchButton(String Testname) throws Exception{

		if (doesElementExist2(properties.getProperty("SearchBtn"), 5)) {    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("SearchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button to view the Public Documents");
		} else {
			log.logLine(Testname, true, "Clicking on Search button to view the Public Documents is failed");
			throw new Exception("Clicking on Search button to view the Public Documents is failed");
		}

		return true;
	}


	public boolean ClickOnAnyTool_inLegacyAdmin(String Testname) throws Exception {

		Thread.sleep(5000);
		if (doesElementExist(properties.getProperty("AnyTool1"), 5)) {
			WebElement AnyTool = driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_lstLayers']"));
			Select select = new Select(AnyTool); 
			List <WebElement> options = select.getOptions(); 
			for (WebElement option: options) { 
				if (option.getText().equalsIgnoreCase("PIVOT Public Documents")){ 
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
		return true;

	}

	public boolean ClickOnSearchBtninAdmin(String Testname) throws Exception {    
		if (doesElementExist2(properties.getProperty("searchbtn"), 5)) {
			WebElement srcbtn = driver.findElement(By.cssSelector(properties.getProperty("searchbtn")));
			log.logLine(Testname, false, "Clicked on search button of the client/app/Tool Admin");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srcbtn);
			waitUntilRetrive();
		} else {
			log.logLine(Testname, true, "Clicking on search button of the client/app/Tool Admin is failed");
			throw new Exception("Clicking on search button of the client/app/Tool Admin is failed");
		}

		return true;
	}

	public boolean ClickOnModifyToolinAdmin(String Testname) throws Exception {
		if (doesElementExist2(properties.getProperty("ModifyTool"), 5)) {
			WebElement modtool = driver.findElement(By.cssSelector(properties.getProperty("ModifyTool")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", modtool);
			waitUntilRetrive();			
			log.logLine(Testname, false, "Clicked on the Public Documents ModifyTool button in client/app/Tool Admin ");
		} else {
			log.logLine(Testname, true, "Clicking on the Public Documents ModifyTool button in client/app/Tool Admin is failed");
			throw new Exception("Clicking on the Public Documents ModifyTool button in client/app/Tool Admin is failed");
		}

		return true;
	}

	public boolean ClickOnUpdateBtn(String Testname) throws Exception {
		if (doesElementExist2(properties.getProperty("UpdateBtn"), 5)) {
			WebElement updtebtn = driver.findElement(By.cssSelector(properties.getProperty("UpdateBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", updtebtn);
			waitUntilRetrive();			
			log.logLine(Testname, false, "Clicked on the update button in client/app/Tool Admin ");
		} else {
			log.logLine(Testname, true, "Clicking on the the update button in client/app/Tool Admin is failed");
			throw new Exception("Clicking on the the update button in client/app/Tool Admin is failed");
		}
		return true;
	}


	public boolean UserAuthorization(String Testname) throws Exception {

		//String[] titles = new String[10];
		String row= "tr"+"+tr"; 

		WebElement table = driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_ctlServices_ctlSrvM_grdUserPermissions']"));

		List<WebElement> msgtitleCnt= driver.findElements(By.xpath(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_ctlServices_ctlSrvM_grdUserPermissions']/tbody/tr"));

		if(doesElementExist2(properties.getProperty("AuthoHeader"), 5)){
			for(int j = 0; j < msgtitleCnt.size()-1; j++) {
				WebElement rd = driver.findElement(By.cssSelector("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_ctlServices_ctlSrvM_grdUserPermissions'] tbody "+row+" td+td+td span input"));

				if(rd.isSelected()){

					log.logLine(Testname, false, "Authorize radio button is already selected");

				}else{
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", rd);
					log.logLine(Testname, false, "Selecting the  Authorize radio button is Sucessful");
				}
				row= row + "+tr";

			}

		}

		return true;
	}



	public boolean UploadPublicDocs(String Testname) throws Exception{

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();


		/*pubDoc2 = test.readColumnData("DocumentName2", sheetname);
		pubDoc3 = test.readColumnData("DocumentName3", sheetname);
		pubDoc4 = test.readColumnData("DocumentName4", sheetname);

		descpr1 = test.readColumnData("DocDescription1", sheetname);
		descpr2 = test.readColumnData("DocDescription2", sheetname);
		descpr3 = test.readColumnData("DocDescription3", sheetname);
		descpr4 = test.readColumnData("DocDescription4", sheetname);*/

		Calendar currentDate1 = Calendar.getInstance(); 
		Calendar currentDate2 = Calendar.getInstance(); 
		Calendar currentDate3 = Calendar.getInstance();
		Calendar currentDate4 = Calendar.getInstance();
		Calendar currentDate5 = Calendar.getInstance();
		Calendar currentDate6 = Calendar.getInstance();
		Calendar currentDate7 = Calendar.getInstance();
		SimpleDateFormat formatter= new SimpleDateFormat("MM/dd/yyyy");

		currentDate1.add(Calendar.DAY_OF_MONTH, 2);	
		currentDate2.add(Calendar.DAY_OF_MONTH, 3);
		currentDate3.add(Calendar.DAY_OF_MONTH, 4);
		currentDate4.add(Calendar.DAY_OF_MONTH, 5);
		currentDate5.add(Calendar.DAY_OF_MONTH, 6);
		currentDate6.add(Calendar.DAY_OF_MONTH, 7);
		currentDate7.add(Calendar.DAY_OF_MONTH, 8);


		FutDate[0] = formatter.format(currentDate1.getTime());
		FutDate[1] = formatter.format(currentDate2.getTime());
		FutDate[2] = formatter.format(currentDate3.getTime());
		FutDate[3] = formatter.format(currentDate4.getTime());
		FutDate[4] = formatter.format(currentDate5.getTime());
		FutDate[5] = formatter.format(currentDate6.getTime());
		FutDate[6] = formatter.format(currentDate7.getTime());

		for(int cnt=0; cnt<7; cnt++){

			pubDoc = test.readColumnData("DocumentName"+Integer.toString(cnt), sheetname);
			descpr = test.readColumnData("DocDescription"+Integer.toString(cnt), sheetname);

			futureDate 	= new String("futureDate"+Integer.toString(cnt));	

			Thread.sleep(25000);
			uploadDocumentsBtn(Testname);
			Thread.sleep(10000);

			if (doesElementExist2(properties.getProperty("Browse1"), 5)) {	    
				WebElement docfld = driver.findElement(By.cssSelector(properties.getProperty("Browse1")));
				//((JavascriptExecutor) driver).executeScript("arguments[0].click()", docfld);
				docfld.sendKeys(pubDoc);		
				Thread.sleep(5000);
				log.logLine(Testname, false, "Entering the docuement name into upload documents  popup");
			} else {
				log.logLine(Testname, true, "Entering the docuement name into upload documents  popup is failed");
				throw new Exception("Entering the docuement name into upload documents  popup is failed");
			}


			if (doesElementExist2(properties.getProperty("EffEndDate1"), 5)) {	    
				WebElement date = driver.findElement(By.cssSelector(properties.getProperty("EffEndDate1")));
				date.clear();
				Thread.sleep(4000);
				date.sendKeys(FutDate[cnt]);
				Thread.sleep(5000);
				log.logLine(Testname, false, "Entering the effective end date into upload documents  popup");
			} else {
				log.logLine(Testname, true, "Entering the effective end date into upload documents  popup is failed");
				throw new Exception("Entering the effective end date into upload documents  popup is failed");
			}



			if (doesElementExist2(properties.getProperty("DocDescption1"), 5)) {	    
				WebElement descptn = driver.findElement(By.cssSelector(properties.getProperty("DocDescption1")));
				descptn.clear();
				Thread.sleep(6000);
				descptn.sendKeys(descpr);	
				Thread.sleep(2000);
				log.logLine(Testname, false, "Entering the document description into upload documents  popup");
			} else {
				log.logLine(Testname, true, "Entering the document description into upload documents  popup is failed");
				throw new Exception("Entering the document description into upload documents  popup is failed");
			}

			if (doesElementExist2(properties.getProperty("ViewChkBox1"), 5)) {
				WebElement vewchkbox = driver.findElement(By.cssSelector(properties.getProperty("ViewChkBox1")));
				Thread.sleep(5000);
				if(vewchkbox.isSelected()){
					log.logLine(Testname, false, "Viewable check box is already selected");
					Thread.sleep(2000);
				}else{
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", vewchkbox);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Selecting the Viewable check box ");
				}

				log.logLine(Testname, false, "Selecting the viewable checkbox is successful");
			}else{
				log.logLine(Testname, true, "Unbale to select the viewable chekbox");
				throw new Exception("Unbale to select the viewable chekbox");

			}  

			Thread.sleep(10000);
			uploadNewDocumentsCompleteBtn(Testname);
			Thread.sleep(5000);
			checkAlert();

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


} //closing of the class






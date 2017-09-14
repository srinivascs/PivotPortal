package pivotModules;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import launchAuto.Initialization;
import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.PrintStream;

public class AuditKeys extends Page{


	public PrintStream changeLog;
	public String newline , Testname;
	public String path;

	public String keynme2;
	public String RegularEx2;
	public String keydesp2;

	public String keyname1;
	public String RegularEx1;
	public String keydecp1;

	public String keyName , sheetname;
	public String regExpr;
	public String Descrip , cnt , N, Y ;
	

	public AuditKeys(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}	
	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}	
	
	
	Actions action = new Actions(driver);

	public boolean Auditspage(String AccNo, String Testname  ) throws Exception {
		
		Thread.sleep(1000);

		driver.switchTo().defaultContent();
		if (doesElementExist2(properties.getProperty("Audits"), 5)) {	    
			WebElement reportsmnu = driver.findElement(By.cssSelector(properties.getProperty("Audits")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", reportsmnu);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Navigation to Audits page successful");
		} else {
			log.logLine(Testname, true, "Clicking on Audits menu is failed");
			throw new Exception("Clicking on Audits menu is failed");
		}	    

		Thread.sleep(1000);
		boolean CliSelected = false;
		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();		
		String adtMaxKeys=test.readColumnData("AMaxKey", "AuditMaxKeys");			
		int cntMaxKeys=Integer.parseInt(adtMaxKeys);		
		int cntdel=Integer.parseInt(adtMaxKeys);
		
		

		String ClntName = test.readColumnData("ClientName", sheetname);

		if (doesElementExist2(properties.getProperty("selClint1"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selClint1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

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
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on OK button to view the Audits");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the Audits is failed");
			throw new Exception("Clicking on OK button to view the Audits is failed");
		}	

		//__________________________________________Check if Audit Keys Form appear __________________________________________________________________________________________________		    

		
		clkAuditRules (Testname);
		clkAuditKeys(Testname);
		Thread.sleep(2000);
		auditFormAppear(Testname ) 	;
		cleanUp(Testname , cntdel);		
		CancelButton(Testname);

		//____________________________________________Add more keys than allowed and check for Maximum Key Alert______________________________________________________________________________________________

		clkAuditKeys (Testname );
	
		addNewKey(Testname , "N" , cntdel , cntMaxKeys) ;		
		
		if (doesElementExist2(properties.getProperty("AddButton"), 5)) {	    
			WebElement addbtn = driver.findElement(By.cssSelector(properties.getProperty("AddButton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", addbtn);

			log.logLine(Testname, false, "Clicking on add button of Audit Keys");
		} else {
			log.logLine(Testname, true, "Clicking on add button of Audit Keys is failed");
			throw new Exception("Clicking on add button of Audit Keys is failed");
		}
		
		//AlertMaxKeys(Testname);
		Thread.sleep(2000);
		Alert Al = driver.switchTo().alert() ;
		Al.accept();

		ClkSaveButton(Testname);
		
		//ClickOKAlertMaxKeys(Testname) ;
	/*	
		if (doesElementExist2("div+div+div div button[style='margin-right:5px; width:100px;']", 5)) {	

			WebElement AlertMessage = driver.findElement(By.cssSelector("div+div+div div button[style='margin-right:5px; width:100px;']"));
			
		
		Actions action = new Actions(driver);
		action.click(AlertMessage).build().perform();
		}
		
		*/
	//	clkAuditKeys (Testname );
	//	cleanUp(Testname , cntdel);		

		//____________________________________________Add Duplicate key & check for Alert______________________________________________________________________________________________

	//	addNewKey(Testname , "N" , cntdel , cntMaxKeys) ;	
	//	cleanUp(Testname , cntdel);	
		//CancelButton(Testname);
		clkAuditKeys (Testname );
		cleanUp(Testname , cntdel);
		addNewKey(Testname, "Y" , cntdel , cntMaxKeys);
		
		ClkSaveButton(Testname);

		Alert Alrt = driver.switchTo().alert() ;
		Alrt.accept();
		
	
		//AlertDuplicateKey(Testname);	
		
		//____________________________________________Add Bad data & check for Alert___________
		Thread.sleep(2000);
		clkAuditKeys (Testname );	

		addNewKey(Testname , "Bad Data" , cntdel , cntMaxKeys) ;			
		//	RegularExpression(Testname);		
		ClkSaveButton(Testname);
		
		Thread.sleep(2000);
		Alert Alrt1 = driver.switchTo().alert() ;
		Alrt1.accept();
		
		CancelButton(Testname);


		//AlertBadData(Testname);		
		
		//____________________________________________Modify existing Key and Validate that its modified___________
		clkAuditKeys(Testname );
		addNewKey(Testname , "N" , cntdel , cntMaxKeys) ;		

		//	RegularExpression(Testname);
		ClkSaveButton(Testname);	
		clkAuditKeys (Testname );

		ModifyEx(Testname);	
		ClkSaveButton(Testname);

		clkAuditKeys (Testname );

		validateModify(Testname);
		
		clkAuditKeys (Testname );
	//	addNewKey(Testname , "N" , cntdel , cntMaxKeys) ;	
	//	ClkSaveButton(Testname);
	//	clkAuditKeys (Testname );
	//	//	RegularExpression(Testname);
	//	AlertMaxKeys(Testname) ;
	//	ClickOKAlertMaxKeys(Testname) ;
	//	DeleteKeys(Testname);   
		CancelButton(Testname);
	//	clkAuditKeys(Testname);
	//	ClkSaveButton(Testname);
		return true;
	}


	//******************************************Method to Clean up*************************************************************************************

	public boolean cleanUp(String Testname , int cntdel) throws Exception  {		

		
			if (doesElementExist2(properties.getProperty("DeleteButton"), 5)) {

				for (int i=1; i<=cntdel; i++){

					WebElement kDelBtn = driver.findElement(By.cssSelector(properties.getProperty("DeleteButton")));
					Thread.sleep(2000);			
					//kDelBtn.click();				
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", kDelBtn);
					
					if (doesElementExist2("div[id='extAlertDialog']+div div button[class='k-button']", 5)) {	
						WebElement SavRul = driver.findElement(By.cssSelector("div[id='extAlertDialog']+div div button[class='k-button']"));
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", SavRul);
						Thread.sleep(1000);
				//		WebDriverWait wait = new WebDriverWait(driver, 15);
				//		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("SavRul")));						
				//		SavRul.click();
						}					

					log.logLine(Testname, false, "Clicked  on Delete Button to delete the existing audit keys");

				}

			}
		
		return true;
	}

	//***************************************Method to click Audit Rules button*******************************************************************************************		

	public boolean clkAuditRules ( String Testname ) throws Exception {


		if (doesElementExist2(properties.getProperty("AuditRules"), 5)) {	    
			WebElement ruleslnk = driver.findElement(By.cssSelector(properties.getProperty("AuditRules")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ruleslnk);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Audit Rules...under Audits");
		} else {
			log.logLine(Testname, true, "Audit Rules link does not exist");
			throw new Exception("Audit Rules link does not exist");
		}	

		driver.switchTo().frame("iframeContainer");				

		return true;
	}	
	//******************************************Method to click Audit Keys button************************************************************************************

	public boolean clkAuditKeys( String Testname  ) throws Exception {	
		Thread.sleep(2000);			
		if (doesElementExist2(properties.getProperty("AuditKeys"), 5)) {	    
			WebElement auditKeysBtn = driver.findElement(By.cssSelector(properties.getProperty("AuditKeys")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", auditKeysBtn);

			log.logLine(Testname, false, "Clicking on Audit Keys button on top right");
		} else {
			log.logLine(Testname, true, "Audit Keys button does not exist");
			throw new Exception("Audit Keys button does not exist");
		}	

		return true;
	}	
	//*************************************Method to check if Audit Keys Form appears ****************************************************************************************************************************************************						

	public boolean auditFormAppear ( String Testname ) throws Exception {			

		
		if (doesElementExist2(properties.getProperty("DescriptionHeader"), 5)) {
			WebElement DescHead = driver.findElement(By.cssSelector(properties.getProperty("DescriptionHeader")));

			if(	DescHead.isDisplayed()){				  
				log.logLine(Testname, false, "The Audit Keys Form is displayed");
	
			} else {
				log.logLine(Testname, true, "Audit Keys Form is NOT displayed");
				throw new Exception("Audit Keys Form is NOT displayed");
			}						
		} else {
			log.logLine(Testname, true, "Description Header element is not displayed");
			throw new Exception("Description Header element is not displayed");
		}

		return true;
	}	

	//*****************************************Method to add new key**********************************************************************************************************************

	public boolean addNewKey( String Testname , String  Duplicate , int cntdel , int cntMaxKeys)  throws Exception  {	
		

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	
		int colNum = 1 ;
			path ="div";
		try {   
			
				for (int cnt=1; cnt<=cntMaxKeys;cnt++){
				
					if ((Duplicate.equals("Y"))) {
						
						colNum = 1 ;
						cntMaxKeys = 2 ;						
						
					}	
						
					keyName=test.readColumnData("Key"+Integer.toString(colNum), sheetname);
					regExpr=test.readColumnData("Reg"+Integer.toString(colNum), sheetname);
					Descrip=test.readColumnData("Descp"+Integer.toString(colNum), sheetname);


					if (doesElementExist2("div[class='filter panel-container'] "+path+" input[name='keyname']", 5)) {	    
						WebElement kNametxt = driver.findElement(By.cssSelector("div[class='filter panel-container'] "+path+" input[name='keyname']"));
						Thread.sleep(1000);
						kNametxt.clear();
						
						//((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', arguments[1])", kNametxt, keyName);
						action.sendKeys(kNametxt, keyName).perform();
						log.logLine(Testname, false, "Entered "+keyName+" in the Key Name textbox");
					}

					if (!(Duplicate.equals("Bad Data"))){
						if (doesElementExist2("div[class='filter panel-container'] "+path+" input[name='keyregex']", 5)) {	    
							WebElement kRegEx = driver.findElement(By.cssSelector("div[class='filter panel-container'] "+path+" input[name='keyregex']"));
							Thread.sleep(1000);
							kRegEx.clear();
							
							//((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', arguments[1])", kRegEx, regExpr);
							action.sendKeys(kRegEx, regExpr).perform();
							log.logLine(Testname, false, "Entered "+regExpr+" in the Regular Expression textbox");
						}	
	
						if (doesElementExist2("div[class='filter panel-container'] "+path+" input[name='keydescription']", 5)) {	    
							WebElement kdesc = driver.findElement(By.cssSelector("div[class='filter panel-container'] "+path+" input[name='keydescription']"));
							Thread.sleep(1000);
							kdesc.clear();
							
							//((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', arguments[1])", kdesc, Descrip);
							action.sendKeys(kdesc, Descrip).perform();
							log.logLine(Testname, false, "Entered "+Descrip+" in the Key Description textbox");
	
						}					
					}
					
					if ( colNum<cntMaxKeys) {
						if (doesElementExist2(properties.getProperty("AddButton"), 5)) {	    
							WebElement addbtn = driver.findElement(By.cssSelector(properties.getProperty("AddButton")));
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", addbtn);
	
							log.logLine(Testname, false, "Clicking on add button of Audit Keys");
						} else {
							log.logLine(Testname, true, "Clicking on add button of Audit Keys is failed");
							throw new Exception("Clicking on add button of Audit Keys is failed");
						}
					}
					path= path+ "+div";		
					colNum = colNum + 1 ;			
				}			
				
				
		}
		
		catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return true ;
		
	}

	//******************************************Method to click + button ****************************************************************************************************

	public boolean AddButton(String Testname) throws Exception {	 

		if (doesElementExist2(properties.getProperty("AddKeys"), 5)) {	    
			WebElement kAdd = driver.findElement(By.cssSelector(properties.getProperty("AddKeys")));
			Thread.sleep(2000);
			kAdd.click();					
			log.logLine(Testname, false, "Clicked on add/plus button to add the audit keys");
		} else {
			log.logLine(Testname, true, "Add/plus Button does not exist");
			throw new Exception("Add/plus Button does not exist");
		}	
		return true;	
	}	

	//****************************************Method to click Save button on the form *********************************************************************************

	public boolean ClkSaveButton(String Testname) throws Exception {			  

		if (doesElementExist2(properties.getProperty("Savebutton"), 5)) {
			WebElement kSaveBtn = driver.findElement(By.cssSelector(properties.getProperty("Savebutton")));
			//kSaveBtn.click();
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", kSaveBtn);

			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicked Save Button to save the audit keys");

		} else {
			log.logLine(Testname, true, "Save Button does not exist");
			throw new Exception("Save Button does not exist");

		}	

		return true;

	}

	//******************************** Method to Verify that the Alert appears for maximum keys allowed*****************************************************************		
	public boolean AlertMaxKeys(String Testname) throws Exception{	

		

		Thread.sleep(2000);		
		if (doesElementExist2(properties.getProperty("popupMaxKeys"), 5)) {
			String kAlert = driver.findElement(By.cssSelector(properties.getProperty("popupMaxKeys"))).getText();

			if (kAlert.equals("Adding another key would exceed limits. Not allowed.")){
				log.logLine(Testname, false, "Alert for adding greater than the Max keys not allowed pop is displayed ");
			}
			else {	
				log.logLine(Testname, true, "Alert for adding greater than the Max keys not allowed pop is not displayed ");
			} 
		}else {
			log.logLine(Testname, true, "Alert for max keys pop up is not displayed ");
			//	throw new Exception("Alert for max keys pop up is not displayed ");

		}

		return true ;
	}	

	public boolean ClickOKAlertMaxKeys(String Testname) throws Exception{	

		if (doesElementExist2(properties.getProperty("OkButton"), 5)) {
			WebElement kOkBtn = driver.findElement(By.cssSelector(properties.getProperty("OkButton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", kOkBtn);
		//	kOkBtn.click();			
			log.logLine(Testname, false, "Clicked OK button on the Alert max keys pop up ");	

		} else {
			log.logLine(Testname, true, "Clicking on OK button on the Alert max keys pop up is failed");
			//throw new Exception("Clicking on OK button on the Alert max keys pop up is failed");
		}

		return true;	
	}
	//****************************************** Method to Remove/Delete keys **************************************************************************		  

	public boolean DeleteKeys(String Testname) throws Exception{		

		if (doesElementExist2(properties.getProperty("DeleteButton"), 5)) {
			WebElement kDelBtn = driver.findElement(By.cssSelector(properties.getProperty("DeleteButton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", kDelBtn);
			log.logLine(Testname, false, "Clicked Delete Button to delete the audit key");

		} else {
			log.logLine(Testname, true, "Clicking Delete Button to delete the audit key is failed");
			throw new Exception("Clicking Delete Button to delete the audit key is failed");
		}

		return true;

	}		

	//********************Method to Verify that the Alert appears for Bad data in the fields"***************************************************************************************************************************************			

	public boolean AlertBadData(String Testname) throws Exception{		


		if (doesElementExist2(properties.getProperty("AlertBadDataTxt"), 5)) {
			String kABadDataTxt = driver.findElement(By.cssSelector(properties.getProperty("AlertBadDataTxt"))).getText();

			if(kABadDataTxt.equals("Bad data in the following field/s: (RegEx)(Description)")){
				log.logLine(Testname, false, "Alert Message is displayed : for bad data ");
			}else{
				log.logLine(Testname, true, "Alert Message is not displayed : for bad data ");
			}

		}else{
			log.logLine(Testname, true, "Alert Message is not displayed : for bad data ");
			throw new Exception("Alert Message is not displayed :  for bad data");
		}

		if (doesElementExist2(properties.getProperty("OkButton"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("OkButton")));
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			log.logLine(Testname, false, "clicked on ok button of the Bad Data pop up "); 
		}else {

			log.logLine(Testname, true, "clicked on ok button of the Bad Data pop up is failed"); 
			throw new Exception("clicked on ok button of the Bad Data pop up is failed");

		}


		CancelButton(Testname);

		return true;
	}	


	//******************************************Method to verify Duplicate Key Alert appears**************************************************************			

	public boolean AlertDuplicateKey(String Testname) throws Exception{	


		if (doesElementExist2(properties.getProperty("AlertDupText"), 5)) {

			String kAlertDupl = driver.findElement(By.cssSelector(properties.getProperty("AlertDupText"))).getText();

			if(kAlertDupl.equals("This Key already exists for this Client and cannot be duplicated")){

				log.logLine(Testname, false, "Alert message: for adding the duplicate keys is displayed");
			}else{
				log.logLine(Testname, true, "Alert message: for adding the duplicate keys is not displayed");
			}
		} else {
			log.logLine(Testname, true, "Alert pop for adding Duplicate keys is NOT displayed ");
			throw new Exception("Alert pop for adding Duplicate keys is NOT displayed ");

		}


		if (doesElementExist2(properties.getProperty("OkButton"), 5)) {
			WebElement kOkBtn = driver.findElement(By.cssSelector(properties.getProperty("OkButton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", kOkBtn);
		//	kOkBtn.click();			
			log.logLine(Testname, false, "Clicked OK button on the pop up ");				
		} else {

			log.logLine(Testname, true, "Clicking on OK button on the pop up is failed ");
			throw new Exception("Clicking on OK button on the pop up is failed ");
		}

		CancelButton(Testname);
		return true;	
	}


	//****************************Method to Modifying an existing Key***************************************************************************************************************************************


	public boolean ModifyEx(String Testname) throws Exception{

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		
		keynme2 = test.readColumnData("ModifyKeyname", sheetname);
		RegularEx2 = test.readColumnData("ModifiedRegularExprn", sheetname);
		keydesp2 = test.readColumnData("ModifiedkeyDecp", sheetname);

		keyName=test.readColumnData("Key11", sheetname);
		regExpr=test.readColumnData("Reg11", sheetname);
		Descrip=test.readColumnData("Descp11", sheetname);

		Thread.sleep(2000);

		if (doesElementExist2(properties.getProperty("KeyName"), 5)) {
			WebElement kNametxt2 = driver.findElement(By.cssSelector(properties.getProperty("KeyName")));
			Thread.sleep(2000);
			kNametxt2.clear();
			
			//((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', arguments[1])", kNametxt2, keyName);
			action.sendKeys(kNametxt2, keyName).perform();			
			log.logLine(Testname, false, "Entered Key Name in the key Name textbox");
		}


		if (doesElementExist2(properties.getProperty("RegularExpression"), 5)) {	    
			WebElement kRegEx2 = driver.findElement(By.cssSelector(properties.getProperty("RegularExpression")));
			Thread.sleep(2000);
			kRegEx2.clear();

			//((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', arguments[1])", kRegEx2, regExpr);
			action.sendKeys(kRegEx2, regExpr).perform();
			log.logLine(Testname, false, "Entered RegularExpression in the Regular Expression textbox");
		}

		if (doesElementExist2(properties.getProperty("keyDescription"), 5)) {	    
			WebElement kdesc2 = driver.findElement(By.cssSelector(properties.getProperty("keyDescription")));
			Thread.sleep(2000);
			kdesc2.clear();
			
			//((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', arguments[1])", kdesc2, Descrip);			
			action.sendKeys(kdesc2, Descrip).perform();			
			log.logLine(Testname, false, "Entered Key Description in the Key Description textbox");

		}

		

		return true; 
	}



	//*************************************Method to Click Cancel button*********************************************************************************************

	public boolean CancelButton(String Testname) throws Exception{	

		if (doesElementExist2(properties.getProperty("CancelButton"), 5)) {	    

			WebElement kCnclBtn = driver.findElement(By.cssSelector(properties.getProperty("CancelButton")));
			//kCnclBtn.click();	
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", kCnclBtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked Cancel Button of the Audit Keys");

		} else {
			log.logLine(Testname, true, "Clicking Cancel Button of the Audit Keys is failed");
			throw new Exception("Clicking Cancel Button of the Audit Keys is failed");
		}		
		
		return true; 

	}	


	public boolean validateModify(String Testname) throws Exception{


		if (doesElementExist2(properties.getProperty("KeyName"), 5)) {
			WebElement kNametxt2 = driver.findElement(By.cssSelector(properties.getProperty("KeyName")));

			String val = kNametxt2.getAttribute("value");
			if(val.equals(keyName)){
				log.logLine(Testname, false, "Modification of the keyname is successful");

			}else{
				log.logLine(Testname, true, "Modification of the keyname is unsuccessful");
			}

		}

		if (doesElementExist2(properties.getProperty("RegularExpression"), 5)) {	    
			WebElement kRegEx2 = driver.findElement(By.cssSelector(properties.getProperty("RegularExpression")));

			String val = kRegEx2.getAttribute("value");

			if(val.equals(regExpr)){
				log.logLine(Testname, false, "Modification of the Regular Expression is successful");

			}else{
				log.logLine(Testname, true, "Modification of the Regular Expression is unsuccessful");
			}
		}

		if (doesElementExist2(properties.getProperty("keyDescription"), 5)) {	    
			WebElement kdesc2 = driver.findElement(By.cssSelector(properties.getProperty("keyDescription")));

			String val = kdesc2.getAttribute("value");

			if(val.equals(Descrip)){
				log.logLine(Testname, false, "Modification of the Key Decsription is successful");

			}else{
				log.logLine(Testname, true, "Modification of the Key Decsription is unsuccessful");
			}
		}	

		CancelButton(Testname);

		return true;
	}

}

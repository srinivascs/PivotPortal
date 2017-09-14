package pivotModules;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class PullsMgmt extends Page {
	
	int paperID = (int) Math.round(Math.random() * (9999 - 1000 + 1) + 1000);
	public String AccNo = Integer.toString(paperID);
	
	public String Text;
	public static String row;
	public String EditName;
	
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
	Date date = new Date();
	String todaysDate = dateFormat.format(date);

	private static final boolean False = false;
	int i = 0 ;
	WebElement attnTxtB ,  CSZTextB1 , CSZTextB2 , CSZTextB3 , CanBtn , CreateUpFrm ,OKbutton;
	String attnText , CSZTextB1Text , CSZTextB2Text , CSZTextB3Text , Save , Update , SearchCriteriaDateTo , SearchCriteriaDateFrom , DateFrom , DateTo ;
	int tsize , tablesize , currentTableSize;

	public PullsMgmt(WebDriver driver, Log log) throws InvalidFormatException,
	IOException {
		super(driver, log);
	}

	@Override
	protected void load() {
	}

	@Override
	protected void isLoaded() throws Error {
	} 
	

	public boolean Pullspage(String AccNo, String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(1000);
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("Pullsmenu"), 5)) {	    
			WebElement mnu = driver.findElement(By.cssSelector(properties.getProperty("Pullsmenu")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", mnu);
			PleasewaitDisappear();
			Thread.sleep(2000);
			log.logLine(Testname, false, "Navigation to Pulls page successful");
		} else {
			log.logLine(Testname, true, "Clicking on Pulls menu is failed");
			throw new Exception("Clicking on Pulls menu is failed");
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
			PleasewaitDisappear();
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on OK button to view the Audits");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the Audits is failed");
			throw new Exception("Clicking on OK button to view the Audits is failed");
		}	    



		//________________Validations according to Testcases__________________________________________________________________________________________________		    

		driver.switchTo().frame("iframeContainer");			

		AddNewPullInstructionValidation( Testname ) ;
		clkSaveNewButton(Testname);
		AlertPullMissingInfo(Testname );
		Cancelbutton(Testname) ;
		
		Thread.sleep(5000);
		AddNewPullInstructionValidation( Testname ) ;
		BuildNewPullInsFromDate(Testname) ;
		BuildNewPullInsToDate(Testname )  ;
		BuildNewPullInsSelDrpdwn(Testname )  ;
		Match(Testname ) ;
		SpecialHandling(Testname  ) ;
		ClientLabel1(Testname  ) ;
		ClientLabel2(Testname  ) ;		
		SpecialHandling(Testname  ) ;
 		clkSaveNewButton(Testname);
		ConfrmNewPullInstructionCreated(Testname) ;
		//Cancelbutton(Testname) ; ---------------Need to check whether this is needed or not
 
		AddNewPullInstructionValidation( Testname ) ;
		BuildNewPullInsFromDate(Testname) ;
		BuildNewPullInsToDate(Testname )  ;
		Match(Testname ) ;
		SpecialHandling(Testname  ) ;
		ClientLabel1(Testname  ) ;
		Clearbutton(Testname ) ;
		ValidateClear(Testname ) ;

		ClientLabel1(Testname  ) ;
		Cancelbutton(Testname) ;
		ValidateFormdisappear(Testname ) ;

		EditPull(Testname ) ;
		UpdateButtonCl(Testname ) ;	
		ValidateEditPull(Testname ) ; 

		EditPullSaveAs(Testname ) ;
		clkSaveNewButton(Testname ) ;
		ValidateEditPull(Testname ) ; 

		CalculateTablesize(Testname ) ;
		ActionDelete(Testname ) ;
		ActionDeleteConfirm(Testname ) ;
		ValidateDeletePull(Testname , tablesize ) ;

		AdvanceSearch(Testname ) ;
		DateClearAdvSearch(Testname ) ;
		SearchButtonAdvSearch(Testname ) ;
		ValidateStartDateRequired(Testname );
		ValidateEndDateRequired(Testname );

		AdvancesearchCancelButton(Testname);


		AdvanceSearch(Testname ) ;		
		SearchwithDateAdvanceSearch(Testname , "4/23/2012" , todaysDate) ;
		SearchButtonAdvSearch(Testname ) ;
		ValidateDateAdvancesearch(Testname) ;

		AdvanceSearch(Testname ) ;		
		SearchwithField1(Testname) ;
  		SearchButtonAdvSearch(Testname ) ;
		ValidateDateAdvancesearchField1(Testname) ;

		AdvanceSearch(Testname ) 	;
		SearchwithDateAdvanceSearch(Testname , "4/23/2000" , todaysDate) ;
		SearchButtonAdvSearch(Testname ) ;
		ValidateDateAdvancesearch(Testname) ;

		AdvanceSearch(Testname ) 	;
		SearchwithField1(Testname) ;
		AdvanceClearBtn(Testname) ;
		ValidateFieldsClear(Testname) ;


		SearchwithField1(Testname) ;
		AdvancesearchCancelButton(Testname) ;
		ValidateFormClose(Testname) ;

		AdvanceSearch(Testname ) 	;
		SearchwithDateAdvanceSearch(Testname , "4/23/2012" , todaysDate) ;
		SearchButtonAdvSearch(Testname ) ;
		ExtraDetails(Testname , DateFrom, DateTo ) ;

		return true;
	}

	//*************************************Method to Add new pull instruction validation - From date**************************************

	public boolean AddNewPullInstructionValidation(String Testname ) throws Exception {

		if (doesElementExist2(properties.getProperty("NewPullInstructionButton"), 5)) {	    
			WebElement NewPullBtn = driver.findElement(By.cssSelector(properties.getProperty("NewPullInstructionButton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", NewPullBtn);
			PleasewaitDisappear();
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on \"New Pull Instruction\" Button...");
		} else {
			log.logLine(Testname, true, "Could not click on \"New Pull Instruction\" Button. It does NOT exist");
			throw new Exception("Could not click on \"New Pull Instruction\" Button. It does NOT exist");
		}	


		return true;
	}			



	//*************************************Method to Build new pull instruction - From Date**************************************

	public boolean BuildNewPullInsFromDate(String Testname ) throws Exception {


		if (doesElementExist2(properties.getProperty("CreateUpdatePullWindow"), 5)) {	    
			WebElement CrtUpdtPullWin = driver.findElement(By.cssSelector(properties.getProperty("CreateUpdatePullWindow")));
			if (doesElementExist2(properties.getProperty("textboxfromDate"), 5)) {	    
				WebElement txtboxfrmDt = driver.findElement(By.cssSelector(properties.getProperty("textboxfromDate")));
				Thread.sleep(3000);
				txtboxfrmDt.clear();
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", txtboxfrmDt);
				txtboxfrmDt.sendKeys("02/01/2014");
				log.logLine(Testname, false, "Entered \"From Date\" in the textbox ");
			}else{
				log.logLine(Testname, true, "Could not enter \"From Date\" because textbox does NOT exist");
				throw new Exception("Could not enter \"From Date\" because textbox does NOT exist");
			}

		}

		return true;
	}		


	//*************************************Method to Build new pull instruction - To Date**************************************

	public boolean BuildNewPullInsToDate(String Testname ) throws Exception {

		
	
		
		if (doesElementExist2(properties.getProperty("textboxToDate"), 5)) {	    
			WebElement txtboxToDt = driver.findElement(By.cssSelector(properties.getProperty("textboxToDate")));
			txtboxToDt.clear();
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", txtboxToDt);
			Thread.sleep(2000);
			txtboxToDt.sendKeys(todaysDate);
			log.logLine(Testname, false, "Entered \"To Date\" in the textbox ");
		}else{
			log.logLine(Testname, true, "Could not enter \"To Date\" because textbox does not exist");
			throw new Exception("Could not enter \"To Date\" because textbox does not exist");
		}

		return true;
	}	

	//*************************************Method to Build new pull instruction - Selection Criteria Dropdown**************************************

	public boolean BuildNewPullInsSelDrpdwn(String Testname ) throws Exception {
		
		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
	    String Zip=test.readColumnData("ZipCode", sheetname);
	    String Firstname="Jordan";
	    
	    
	    
	    if (doesElementExist2(properties.getProperty("SelectionCriteriaDropdownFirstNme"), 5)) { 
			WebElement Frstname=driver.findElement(By.cssSelector(properties.getProperty("SelectionCriteriaDropdownFirstNme")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Frstname);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on \"Selection Criteria\" Dropdown ");
			
			if (doesElementExist2(properties.getProperty("Seldropdwnoption"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Seldropdwnoption")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("First Name")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the First Name option from the dropdown");
						break;
					}						
				}
		}else{
			log.logLine(Testname, true, "Could not click on \"Selection Criteria\" because Dropdown does NOT exist");
			throw new Exception("Could not click on \"Selection Criteria\" because Dropdown does NOT exist");
		}
	}

		if (doesElementExist2(properties.getProperty("SelectiontextboxFN"), 5)) {
			WebElement SelTxBx = driver.findElement(By.cssSelector(properties.getProperty("SelectiontextboxFN")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", SelTxBx);
			SelTxBx.sendKeys(Firstname+"_"+AccNo);
			log.logLine(Testname, false, "Entered text "+AccNo+" in the Textbox against \"Selection Criteria\"  ");
		}else {
			log.logLine(Testname, true, "Could not enter text because  Textbox against \"Selection Criteria\" does NOT exist");
			throw new Exception("Could not enter text because  Textbox against \"Selection Criteria\" does NOT exist");
		}		
		
		
		if (doesElementExist2(properties.getProperty("SelectionCriteriaAddButton"), 5)) {
			WebElement AddBtn = driver.findElement(By.cssSelector(properties.getProperty("SelectionCriteriaAddButton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", AddBtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on \"+\" button  ");
		}else {
			log.logLine(Testname, true, "Could not clicked on \"+\" button because it does Not exist");
			throw new Exception("Could not clicked on \"+\" button because it does Not exist");
		}
		
	 
		for (int i = 1 ; i < 5 ;  i++) {
			
			Text=test.readColumnData("SelectionValue"+Integer.toString(i), sheetname);
			  
			
			WebElement SelCrDropdown;
			if (doesElementExist2(properties.getProperty("SelectionCriteriaDropdown_"+i), 5)) { 
				SelCrDropdown = driver.findElement(By.cssSelector(properties.getProperty("SelectionCriteriaDropdown_"+i)));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", SelCrDropdown);
				Thread.sleep(2000);
				log.logLine(Testname, false, "Clicked on \"Selection Criteria\" Dropdown ");
			}else{
				log.logLine(Testname, true, "Could not click on \"Selection Criteria\" because Dropdown does NOT exist");
				throw new Exception("Could not click on \"Selection Criteria\" because Dropdown does NOT exist");
			}

			Thread.sleep(2000);
			if (doesElementExist(properties.getProperty("SelectionCriteriaDropdownItem_"+i), 5)) {
				List<WebElement> SelCrDrpdwnItem = driver.findElements(By.xpath(properties.getProperty("SelectionCriteriaDropdownItem_"+i )));
				String L = SelCrDrpdwnItem.get(i).getText() ;
				for (WebElement option:SelCrDrpdwnItem)
				{Thread.sleep(2000);
					String C = option.getText() ;
					if (L.equals(C)) {
						Thread.sleep(1000);
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", option);
						Thread.sleep(2000);
						log.logLine(Testname, false, "Selected an item from \"Selection Criteria\" Dropdown ");
					}
				}

			}
			Thread.sleep(2000);
			if (doesElementExist2(properties.getProperty("SelectionCriteriaTextBox_"+i), 5)) {
				WebElement SelCrTxBx = driver.findElement(By.cssSelector(properties.getProperty("SelectionCriteriaTextBox_"+i)));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", SelCrTxBx);
				SelCrTxBx.sendKeys(Text.concat(AccNo));
				log.logLine(Testname, false, "Entered text "+Text+AccNo+" in the Textbox against \"Selection Criteria\"  ");
			}else {
				log.logLine(Testname, true, "Could not enter text because  Textbox against \"Selection Criteria\" does NOT exist");
				throw new Exception("Could not enter text because  Textbox against \"Selection Criteria\" does NOT exist");
			}				
			
			if (doesElementExist2(properties.getProperty("OKEdit"), 5)) {
				WebElement OKEd = driver.findElement(By.cssSelector(properties.getProperty("OKEdit" )));
				if(OKEd.isDisplayed())
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", OKEd);
				Thread.sleep(2000);
			}
			log.logLine(Testname, false, "Selected an item from \"Selection Criteria\" dropdown") ;

			Thread.sleep(2000);
			if (doesElementExist2(properties.getProperty("SelectionCriteriaAddButton_"+i), 5)) {
				WebElement AddBtn = driver.findElement(By.cssSelector(properties.getProperty("SelectionCriteriaAddButton_"+i)));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", AddBtn);
				Thread.sleep(2000);
				log.logLine(Testname, false, "Clicked on \"+\" button  ");
			}else {
				log.logLine(Testname, true, "Could not clicked on \"+\" button because it does Not exist");
				throw new Exception("Could not clicked on \"+\" button because it does Not exist");
			}
			Thread.sleep(2000);
			if (doesElementExist2(properties.getProperty("OKEdit"), 5)) {
				WebElement OKEd = driver.findElement(By.cssSelector(properties.getProperty("OKEdit" )));
				if(OKEd.isDisplayed())
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", OKEd);
				Thread.sleep(2000);
				}
			
			}
		
		if (doesElementExist2(properties.getProperty("SelectionCriteriaDropdownZip"), 5)) { 
			WebElement Zipcde=driver.findElement(By.cssSelector(properties.getProperty("SelectionCriteriaDropdownZip")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Zipcde);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on \"Selection Criteria\" Dropdown ");
			
			if (doesElementExist2(properties.getProperty("Seldropdwnoption"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Seldropdwnoption")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Zip code")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Zip code option from the dropdown");
						break;
					}						
				}
		}else{
			log.logLine(Testname, true, "Could not click on \"Selection Criteria\" because Dropdown does NOT exist");
			throw new Exception("Could not click on \"Selection Criteria\" because Dropdown does NOT exist");
		}
	}

		if (doesElementExist2(properties.getProperty("SelectiontextboxZip"), 5)) {
			WebElement SelTxBx = driver.findElement(By.cssSelector(properties.getProperty("SelectiontextboxZip")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", SelTxBx);
			SelTxBx.sendKeys(AccNo);
			log.logLine(Testname, false, "Entered text "+AccNo+" in the Textbox against \"Selection Criteria\"  ");
		}else {
			log.logLine(Testname, true, "Could not enter text because  Textbox against \"Selection Criteria\" does NOT exist");
			throw new Exception("Could not enter text because  Textbox against \"Selection Criteria\" does NOT exist");
		}				
		
		
			if (doesElementExist(properties.getProperty("MatchAudit"), 5)) {
				WebElement MAudit = driver.findElement(By.xpath(properties.getProperty("MatchAudit")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", MAudit);
				Thread.sleep(2000);
				log.logLine(Testname, false, "Clicked on \"Yes\" option button , to \"Show matched item(s) in audit file?\"  ");
			}else {
				log.logLine(Testname, true, "Could not clicked on \"Yes\" option button, to \"Show matched item(s) in audit file?\" because it does Not exist");
				throw new Exception("Could not clicked on \"Yes\" option button, to \"Show matched item(s) in audit file?\" because it does Not exist");
			}

		return true;
	}	

	//*************************************Method to click Save New ********************************************************

	public boolean clkSaveNewButton(String Testname ) throws Exception {

		Save = "SaveNew" ;
		if (doesElementExist2(properties.getProperty("SaveNewbutton"), 5)) {	    
			WebElement SavNewbtn = driver.findElement(By.cssSelector(properties.getProperty("SaveNewbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", SavNewbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked on \"Save new\" Button");
		} else {
			log.logLine(Testname, true, "Could not clicked on \"Save new\" Button,because it does Not exist");
			throw new Exception("Could not clicked on \"Save new\" Button,because it does Not exist");
		}

		return true;
	}

	//*************************************Method to check Alert for missing information ********************************************************

	public boolean AlertPullMissingInfo(String Testname ) throws Exception {

		if (doesElementExist2(properties.getProperty("AlertPullMissInfo"), 5)) {	    
			WebElement AlPullMInfo = driver.findElement(By.cssSelector(properties.getProperty("AlertPullMissInfo")));
			String Altext= AlPullMInfo.getText();
			String Messagetext = "At least one of the first four index fields must have and entry";
			if(Altext.contains(Messagetext))
			{
				log.logLine(Testname, false, "Alert message \""+Messagetext+"\"  is displayed for missing information");

			}else {
				log.logLine(Testname, true, "Alert message \""+Messagetext+"\"  is NOT displayed for missing information");
				throw new Exception("Alert message \""+Messagetext+"\"  is NOT displayed for missing information");
			}

			if (doesElementExist2(properties.getProperty("OkButton"), 5)) {	    
				WebElement OkBtn = driver.findElement(By.cssSelector(properties.getProperty("OkButton")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", OkBtn);
				Thread.sleep(2000);
				log.logLine(Testname, false, "Clicked on \"OK\" button of the missing informatin alert message popup");

			}else {
				log.logLine(Testname, true, "Could not clicked on \"OK\" button, because it is Not displayed");
				throw new Exception("Could not clicked on \"OK\" button, because it is Not displayed");
			}


		}
		return true;
	}


	//*************************************Method to define action on Match ********************************************************

	public boolean Match(String Testname  ) throws Exception, InvalidFormatException, IOException {
		
		List<WebElement> Matchdrp ;		
		if (doesElementExist2(properties.getProperty("Matchdropdown"), 5)) {	    
			Matchdrp = driver.findElements(By.cssSelector(properties.getProperty("Matchdropdown")));
			//Matchdrp.stream();
			Matchdrp.toArray();

			WebElement Matdrp = driver.findElement(By.cssSelector(properties.getProperty("Matchdropdown")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Matdrp);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicked on \"Action to take on match:\" dropdown");

		}else {
			log.logLine(Testname, true, "Could not clicked on \"Action to take on match:\" dropdown, because it is NOT displayed");
			throw new Exception("Could not clicked on \"Action to take on match:\" dropdown, because it is NOT displayed");
		}

		for (int i = 0 ; i < Matchdrp.size() ;  i++) { 

			if (doesElementExist2(properties.getProperty("MatchdropdownItem_"+i), 5)) {
				List<WebElement> MatchdrpItem = driver.findElements(By.cssSelector(properties.getProperty("MatchdropdownItem_"+i )));
				String MItem = MatchdrpItem.get(i).getText() ;
				for ( WebElement opt : MatchdrpItem)
				{
					String Optext = opt.getText() ;
					if (MItem.equals(Optext))
					{
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", opt);
						Thread.sleep(2000);
						log.logLine(Testname, false, "Selected text item \""+Optext+" \" from \"Action to take on match:\" dropdown");
						break;
					}
				}

			}	

		}
		return true;
	}

	//*************************************Method to define Special handling Instruction ********************************************************

	public boolean SpecialHandling(String Testname  ) throws Exception, InvalidFormatException, IOException {
		List<WebElement> SpeHanInsDrp ;
		WebElement SpHInsItem ;
		String SpeHInsItemText = null ;
		if (doesElementExist2(properties.getProperty("SpecialHandInstrDropdown"), 5)) {	    
			SpeHanInsDrp = driver.findElements(By.cssSelector(properties.getProperty("SpecialHandInstrDropdown")));

			if (doesElementExist2(properties.getProperty("SpecialHandInstrDropdown"), 5)) {	
				SpHInsItem = driver.findElement(By.cssSelector(properties.getProperty("SpecialHandInstrDropdown")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", SpHInsItem);
				Thread.sleep(2000);

			}
			log.logLine(Testname, false, "Clicked on \"Special Handling Instructions:\" dropdown");
			
			
			if (doesElementExist2(properties.getProperty("SpecialHandInstrItem"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SpecialHandInstrItem")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Inserted Unsealed")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Equals operator option from the dropdown");
						break;
					}						
				}
		}else{
			log.logLine(Testname, true, "Could not click on \"Selection Criteria\" because Dropdown does NOT exist");
			throw new Exception("Could not click on \"Selection Criteria\" because Dropdown does NOT exist");
		}
	}
		return true;
	}

	//*************************************Method to define Client label 1 ********************************************************

	public boolean ClientLabel1(String Testname  ) throws Exception, InvalidFormatException, IOException {
		
		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
	    		
		String Text1=test.readColumnData("AttentionText", sheetname);
		String Text2=test.readColumnData("City", sheetname);
		String Text3=test.readColumnData("State", sheetname);
		String Text4=test.readColumnData("ZipCode", sheetname);

		WebElement cltLabel1 ;
		WebElement delMethodOpbtn ;
		WebElement attnTxtB ;
		WebElement CSZTextB1 ;
		WebElement CSZTextB2 ;
		WebElement CSZTextB3 ;

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("clientLabel1"), 5)) {	    
			cltLabel1 = driver.findElement(By.cssSelector(properties.getProperty("clientLabel1")));		
			if (!(cltLabel1.isSelected())){
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", cltLabel1);	
				Thread.sleep(2000);
			}
			log.logLine(Testname, false, "Clicked on \"Client Label 1\" ");
		}else {
			log.logLine(Testname, true, "\"Client Label 1\" is NOT displayed");
			throw new Exception("\"Client Label 1\" is NOT displayed");
		}

		/*Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("deliverMethodOptionButton1"), 5)) {	    
			delMethodOpbtn = driver.findElement(By.xpath(properties.getProperty("deliverMethodOptionButton1")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", delMethodOpbtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on \"Mail\" \"Delivery Method: \" option button");
		}else {
			log.logLine(Testname, true, "\"Mail\" \"Delivery Method: \" option button is NOT displayed");
			//throw new Exception("\"Mail\" \"Delivery Method: \" option button is NOT displayed");
		}
	*/	
		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("attnTextBox"), 5)) {	    
			attnTxtB = driver.findElement(By.cssSelector(properties.getProperty("attnTextBox")));
			attnTxtB.clear();
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", attnTxtB);
			Thread.sleep(2000);
			attnTxtB.sendKeys(Text1);
			log.logLine(Testname, false, "Entered text "+Text1+"  in \"Attn: \" Text Box ");
		}else{
			log.logLine(Testname, true, "\"Attn: \" Text Box does NOT exist");
			throw new Exception("\"Attn: \" Text Box does NOT exist");
		}

		if (doesElementExist2(properties.getProperty("CSZTextBox1"), 5)) {	    
			CSZTextB1 = driver.findElement(By.cssSelector(properties.getProperty("CSZTextBox1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", CSZTextB1);
			CSZTextB1.clear();
			CSZTextB1.sendKeys(Text2);
			log.logLine(Testname, false, "Entered text "+Text2+" in \"CSZ: \" Text Box for City");
		}else{
			log.logLine(Testname, true, "\"CSZ: \" Text Box for City does NOT exist");
			throw new Exception("\"CSZ: \" Text Box for City does NOT exist");
		}

		if (doesElementExist2(properties.getProperty("CSZTextBox2"), 5)) {	    
			CSZTextB2 = driver.findElement(By.cssSelector(properties.getProperty("CSZTextBox2")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", CSZTextB2);
			CSZTextB2.clear();
			CSZTextB2.sendKeys(Text3);
			log.logLine(Testname, false, "Entered text "+Text3+" in \"CSZ: \" Text Box for State");
		}else{
			log.logLine(Testname, true, " \"CSZ: \" Text Box for State does NOT exist");
			throw new Exception(" \"CSZ: \" Text Box for State does NOT exist");
		}

		if (doesElementExist2(properties.getProperty("CSZTextBox3"), 5)) {	    
			CSZTextB3 = driver.findElement(By.cssSelector(properties.getProperty("CSZTextBox3")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", CSZTextB3);
			CSZTextB3.clear();
			CSZTextB3.sendKeys(Text4);
			log.logLine(Testname, false, "Entered text "+Text4+" in \"CSZ: \" Text Box for Zip Code");
		}else{
			log.logLine(Testname, true, "\"CSZ: \" Text Box for Zip Code does NOT exist");
			throw new Exception("\"CSZ: \" Text Box for Zip Codedoes NOT exist");
		}
		return true;
	}


	//*************************************Method to define Client label 2 ********************************************************

	public boolean ClientLabel2(String Testname  ) throws Exception, InvalidFormatException, IOException {
		
		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
	    		
		String Text1=test.readColumnData("AttentionText", sheetname);
		String Text2=test.readColumnData("City", sheetname);
		String Text3=test.readColumnData("State", sheetname);
		String Text4=test.readColumnData("ZipCode", sheetname);

		WebElement cltLabel2 ;
		WebElement delMethodOpbtnA ;
		WebElement attnTxtBA ;
		WebElement CSZTextBA ;
		WebElement CSZTextBB ;
		WebElement CSZTextBC ;
		WebElement addressTypeOptBtnA ;
		Thread.sleep(4000);

		if (doesElementExist2(properties.getProperty("clientLabel2"), 5)) {	    
			cltLabel2 = driver.findElement(By.cssSelector(properties.getProperty("clientLabel2")));		
			if (!(cltLabel2.isSelected())){
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", cltLabel2);	
				Thread.sleep(2000);
			}
			log.logLine(Testname, false, "Clicked on \"Client Label 2\" ");
		}else {
			log.logLine(Testname, true, " \"Client Label 2\"is NOT displayed");
			throw new Exception(" \"Client Label 2\" is NOT displayed");
		}
/*
		if (doesElementExist(properties.getProperty("deliverMethodOptionButtonA"), 5)) {	    
			delMethodOpbtnA = driver.findElement(By.xpath(properties.getProperty("deliverMethodOptionButtonA")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", delMethodOpbtnA);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on \"Mail\" \"Delivery Method: \" option button");
		}else {
			log.logLine(Testname, true, "\"Mail\" \"Delivery Method: \" option button is NOT displayed");
			//throw new Exception("\"Mail\" \"Delivery Method: \" option button is NOT displayed");
		}
*/
		if (doesElementExist2(properties.getProperty("addressTypeOptionButtonDomestic"), 5)) {	    
			addressTypeOptBtnA = driver.findElement(By.cssSelector(properties.getProperty("addressTypeOptionButtonDomestic")));			
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", addressTypeOptBtnA);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on \"Domestic \" \"Address Type \" option button");
		}else {
			log.logLine(Testname, true, "\"Mail\" \"Delivery Method: \" option button is NOT displayed");
			throw new Exception("\"Mail\" \"Delivery Method: \" option button is NOT displayed");
		}
		
		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("attnTextBoxA"), 5)) {	    
			attnTxtBA = driver.findElement(By.cssSelector(properties.getProperty("attnTextBoxA")));
			attnTxtBA.clear();
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", attnTxtBA);
			Thread.sleep(2000);
			attnTxtBA.sendKeys(Text1);
			
			log.logLine(Testname, false, "Entered text "+Text1+" in \"Attn: \" Text Box ");
		}else{
			log.logLine(Testname, true, " \"Attn: \" Text Box does NOT exist");
			throw new Exception("\"Attn: \" Text Box does NOT exist");
		}

		if (doesElementExist2(properties.getProperty("CSZTextBoxA"), 5)) {	    
			CSZTextBA = driver.findElement(By.cssSelector(properties.getProperty("CSZTextBoxA")));
			if (CSZTextBA.isDisplayed()){
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", CSZTextBA);
				CSZTextBA.clear();
				CSZTextBA.sendKeys(Text2);
				log.logLine(Testname, false, "Entered text "+Text2+" in \"CSZ: \" Text Box for City");			}
		}else{
			log.logLine(Testname, true, "\"CSZ: \" Text Box for City does NOT exist");
			throw new Exception("\"CSZ: \" Text Box for City does NOT exist");
		}

		if (doesElementExist2(properties.getProperty("CSZTextBoxB"), 5)) {	    
			CSZTextBB = driver.findElement(By.cssSelector(properties.getProperty("CSZTextBoxB")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", CSZTextBB);

			if (CSZTextBA.isDisplayed()){
				CSZTextBB.clear();
				CSZTextBB.sendKeys(Text3);
				log.logLine(Testname, false, "Entered text "+Text3+" in \"CSZ: \" Text Box for State");
			}
		}else{
			log.logLine(Testname, true, " \"CSZ: \" Text Box for State does NOT exist");
			throw new Exception(" \"CSZ: \" Text Box for State does NOT exist");
		}

		if (doesElementExist2(properties.getProperty("CSZTextBoxC"), 5)) {	    
			CSZTextBC = driver.findElement(By.cssSelector(properties.getProperty("CSZTextBoxC")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", CSZTextBC);
			if (CSZTextBC.isDisplayed()){
				CSZTextBC.clear();
				CSZTextBC.sendKeys(Text4);
				log.logLine(Testname, false, "Entered text "+Text4+" in \"CSZ: \" Text Box for Zip Code");

			}
		}else{
			log.logLine(Testname, true, "\"CSZ: \" Text Box for Zip Code does NOT exist");
			throw new Exception("\"CSZ: \" Text Box for Zip Codedoes NOT exist");
		}

		return true;
	}
	//	*****************************Method to confirm new pull instruction created succesfully**********************************************

	
	public boolean ConfrmNewPullInstructionCreated(String Testname) throws Exception {
		
		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
	    
		
        String AccNum = test.readColumnData("SelectionValue2", sheetname);
		
		String[] Sort1 = new String[20];
         row = "tr";
        List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='pulls-grid']/table/tbody/tr"));
    		
    		if(doesElementExist2(properties.getProperty("AccountNumber"), 5)){
    			for(int i = 0; i < DataCnt.size(); i++) {
    				Sort1[i] = driver.findElement(By.cssSelector("div[id='pulls-grid'] table tbody "+row+" td+td+td+td+td+td+td[role='gridcell']")).getText();
    				
    				if(Sort1[i].equals(AccNum+AccNo)){
    					log.logLine(Testname, false, "Account Number Matches and hence pull is added sucessfully "+Sort1[i]);
    					break;
    				}else{
    					log.logLine(Testname, false, "Account Number does not Matches and pull is not added sucessfully "+Sort1[i]);
    					
    				}
				
    				row = row + "+tr";
				log.logLine(Testname, false, "Iterating through the Rows....Rows Have the Status as "+Sort1[i]);
				}
    			
    		}
        
		
    		/*if(doesElementExist2(properties.getProperty("AccountNumber"), 5)){
    			String verAcc = driver.findElement(By.cssSelector("div[id='pulls-grid'] table tbody tr td+td+td+td+td+td+td[role='gridcell']")).getText();
    				
    				if(verAcc.equals(AccNum.concat(AccNo))){
    					log.logLine(Testname, false, "Account Number Matches and hence pull is added sucessfully");
    				}else{
    					log.logLine(Testname, true, "Account Number does not Matches and pull is not added sucessfully");
    				}
    				
    		}
    		*/
		
    			return true;
		}	
    		


	//*************************************Method to clear button**************************************

	public boolean Clearbutton(String Testname ) throws Exception {

		if (doesElementExist2(properties.getProperty("clearButton"), 5)) {	    
			WebElement NewPullBtn = driver.findElement(By.cssSelector(properties.getProperty("clearButton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", NewPullBtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked on \"Clear\" button on \"Create/Update Pull Instruction\" modal dialog ");
		} else {
			log.logLine(Testname, true, "\"Clear\" button on \"Create/Update Pull Instruction\" modal dialog does Not exist");
			throw new Exception("\"Clear\" button on \"Create/Update Pull Instruction\" modal dialog does Not exist");
		}	
		return true;

	}

	//*********************************Method to validate the the values are cleared ************************************

	public boolean ValidateClear(String Testname ) throws Exception {


		if (doesElementExist2(properties.getProperty("attnTextBox"), 5)) {	    
			attnTxtB = driver.findElement(By.cssSelector(properties.getProperty("attnTextBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", attnTxtB);			
			attnText = attnTxtB.getAttribute("value") ;
			attnTxtB.getText() ;
			log.logLine(Testname, false, "Reading text from \"Attn: \" Text Box ");
		}else{
			log.logLine(Testname, true, "Could not read text because from \"Attn: \" Text Box  beacuse it does NOT exist");
			throw new Exception("Could not read text because from \"Attn: \" Text Box  beacuse it does NOT exist");
		}

		if (doesElementExist2(properties.getProperty("CSZTextBox1"), 5)) {	    
			CSZTextB1 = driver.findElement(By.cssSelector(properties.getProperty("CSZTextBox1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", CSZTextB1);
			Thread.sleep(2000);
			CSZTextB1Text = CSZTextB1.getAttribute("value") ;
			log.logLine(Testname, false, "Reading text from \"CSZ: \" Text Box for City ");
		}else{
			log.logLine(Testname, true, "Could not read text from \"CSZ: \" Text Box for City  beacuse it does NOT exist ");
			throw new Exception("Could not read text from \"CSZ: \" Text Box for City  beacuse it does NOT exist ");
		}

		if (doesElementExist2(properties.getProperty("CSZTextBox2"), 5)) {	    
			CSZTextB2 = driver.findElement(By.cssSelector(properties.getProperty("CSZTextBox2")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", CSZTextB2);
			Thread.sleep(2000);
			CSZTextB2Text = CSZTextB2.getAttribute("value") ;
			log.logLine(Testname, false, "Reading text from \"CSZ: \" Text Box for State ");
		}else{
			log.logLine(Testname, true, "Could not read text from \"CSZ: \" Text Box for State  beacuse it does NOT exist ");
			throw new Exception("Could not read text from \"CSZ: \" Text Box for State  beacuse it does NOT exist ");
		}

		if (doesElementExist2(properties.getProperty("CSZTextBox3"), 5)) {	    
			CSZTextB3 = driver.findElement(By.cssSelector(properties.getProperty("CSZTextBox3")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", CSZTextB3);
			Thread.sleep(2000);
			CSZTextB3Text = CSZTextB3.getAttribute("value") ;
			log.logLine(Testname, false, "Reading text from \"CSZ: \" Text Box for Zip Code ");
		}else{
			log.logLine(Testname, true, "Could not read text from \"CSZ: \" Text Box for Zip Code  beacuse it does NOT exist ");
			throw new Exception("Could not read text from \"CSZ: \" Text Box for Zip Code  beacuse it does NOT exist ");
		}

		if (attnText.length() >= 1  || CSZTextB1Text.length()  >= 1 || CSZTextB2Text.length()  >= 1 || CSZTextB3Text.length()  >= 1  )
		{
			log.logLine(Testname, true, "Data on the on \"Create/Update Pull Instruction\" modal dialog could NOT be  cleared");

		} else {
			log.logLine(Testname, false, "All the Data on \"Create/Update Pull Instruction\" modal dialog was cleared successfully !! ");
		}

		return true;
	}			

	//		***************************Method to click Cancel button ********************************************************

	public boolean Cancelbutton(String Testname ) throws Exception {

		if (doesElementExist2(properties.getProperty("CancelButton"), 5)) {	    
			CanBtn = driver.findElement(By.cssSelector(properties.getProperty("CancelButton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", CanBtn);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicked on \"Cancel\" Button ");

		}else{
			log.logLine(Testname, true, "\"Cancel\" Button does NOT exist");
			throw new Exception("\"Cancel\" Button does NOT exist");
		}
		return true;
	}

	//			***************************Method to validate form disappeared ********************************************************

	public boolean ValidateFormdisappear(String Testname ) throws Exception {
		
		/*if (doesElementExist2(properties.getProperty("CreateUpdateForm"), 5)) {	    
			CreateUpFrm = driver.findElement(By.cssSelector(properties.getProperty("CreateUpdateForm")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", CanBtn);
			log.logLine(Testname, false, "Clicked  \"Cancel\" Button on \"Create/Update Pull Instruction\" modal dialog ");
		}else{
			log.logLine(Testname, true, "Could not click\"Cancel\" Button because it does NOT exist on \"Create/Update Pull Instruction\" modal dialog");
			throw new Exception("Could not click\"Cancel\" Button because it does NOT exist on \"Create/Update Pull Instruction\" modal dialog");
		}
		 */
		if (doesElementExist2(properties.getProperty("CreateUpdateForm"), 5)) {	    
			CreateUpFrm = driver.findElement(By.cssSelector(properties.getProperty("CreateUpdateForm")));
		
				if (!CreateUpFrm.isDisplayed())
				{
					log.logLine(Testname, false, "Validated Successfully - that \"Create/Update Pull Instruction\" modal dialog disappeared and clicking cancel button is successful");
				}else {
					log.logLine(Testname, true, "Validated failed - \"Create/Update Pull Instruction\" modal dialog did NOT disappear and clicking cancel button is unsuccessful");
					throw new Exception("Validated failed - \"Create/Update Pull Instruction\" modal dialog did NOT disappear and clicking cancel button is unsuccessful");
				}
				
		}else{
			log.logLine(Testname, true, "\"Create/Update Pull Instruction\" window is not present");
			throw new Exception("\"Create/Update Pull Instruction\" window is not present");
		}
		Thread.sleep(2000);
		return true;
	}

	//				***************************Method to click Update button ********************************************************

	public boolean UpdateButtonCl(String Testname ) throws Exception {

		Save = "Update" ;
		if (doesElementExist2(properties.getProperty("UpdateButton"), 5)) {	    
			WebElement	UpdateBtn = driver.findElement(By.cssSelector(properties.getProperty("UpdateButton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", UpdateBtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked  \"Update\" Button on \"Create/Update Pull Instruction\" modal dialog ");
		}else{
			log.logLine(Testname, true, "Could not click\"Update\" Button because it does NOT exist on \"Create/Update Pull Instruction\" modal dialog");
			throw new Exception("Could not click\"Update\" Button because it does NOT exist on \"Create/Update Pull Instruction\" modal dialog");
		}
		return true ;
	}				

	//	***************************Method to Edit Pull ********************************************************

	public boolean EditPull(String Testname ) throws Exception {
		
		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
	    
	    
	    EditName="JAMES_";
	    
		Thread.sleep(2000);
		WebElement Actiondrpdwn ;
		WebElement opt = null ;					
		List<WebElement> Matchdrp ;
		List<WebElement> Actionlist;
		WebElement ListElement ;
		Boolean PullHoldFound = false ;
		WebElement OKEd ;

	
		
		log.logLine(Testname, false, "----------- Editing a Pull Instruction -------------");		
		
		if (doesElementExist2(properties.getProperty("chooseActiondropdown"), 5)) {	    
			Actiondrpdwn = driver.findElement(By.cssSelector(properties.getProperty("chooseActiondropdown")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Actiondrpdwn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on \"Choose Action...\" dropdown under \"Actions\" column");
		}else{
			log.logLine(Testname, true, "Could not click on \"Choose Action...\" dropdown because it does NOT exist under \"Actions\" column");
			//throw new Exception("Could not click on \"Choose Action...\" dropdown because it does NOT exist under \"Actions\" column");
		}

		if (doesElementExist2(properties.getProperty("chooseActionEdit"), 5)) {
			List<WebElement> ActionEdit = driver.findElements(By.cssSelector(properties.getProperty("chooseActionEdit")));
			for (WebElement lnk:ActionEdit) {
				if (lnk.getText().equals("Edit Instruction/Rule")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(2000);
					log.logLine(Testname, false, "Selecting the Edit Action");
					break;
				}				
			}
			log.logLine(Testname, false, "Selected \"Edit Instruction/Rule\" item from \"Choose Action...\" dropdown");
		
		}else if(doesElementExist2(properties.getProperty("chooseActionEdit1"), 5)) {
			List<WebElement> ActionEdit = driver.findElements(By.cssSelector(properties.getProperty("chooseActionEdit1")));
			for (WebElement lnk:ActionEdit) {
				if (lnk.getText().equals("Edit Instruction/Rule")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(2000);
					log.logLine(Testname, false, "Selecting the Edit Action");
					break;
				}				
			}
			log.logLine(Testname, false, "Selected \"Edit Instruction/Rule\" item from \"Choose Action...\" dropdown");
		}
		else {
			log.logLine(Testname, true, "Could not Select \"Edit Instruction/Rule\" item from \"Choose Action...\" dropdown because it does NOT exist");
			//throw new Exception("Could not Select \"Edit Instruction/Rule\" item from \"Choose Action...\" dropdown because it does NOT exist");
		}
		
		
		

		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("Matdropdown"), 5)) {	    
			Matchdrp = driver.findElements(By.xpath(properties.getProperty("Matdropdown")));
			WebElement Matdrp = driver.findElement(By.xpath(properties.getProperty("Matdropdown")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Matdrp);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on \"Action to take on match:\" dropdown") ;
		}else {
			log.logLine(Testname, true, "\"Action to take on match:\" dropdown is NOT displayed on \"Create/Update Pull Instruction\" modal dialog");
			//throw new Exception("\"Action to take on match:\" dropdown is NOT displayed on \"Create/Update Pull Instruction\" modal dialog");
		}
		//Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("MatchdropdownItem_1"), 5)) {
			List<WebElement> MatchdrpItem = driver.findElements(By.cssSelector(properties.getProperty("MatchdropdownItem_1" )));
			for (WebElement ListEl : MatchdrpItem){
				if (ListEl.getText().equals("Pull and Hold") ) {
					PullHoldFound = true ;
				} else {
					PullHoldFound = false ;
				}
			}
			Thread.sleep(2000);
			if (PullHoldFound = true ){
				Thread.sleep(2000);
				for ( WebElement Lis : MatchdrpItem){
					Thread.sleep(2000);
					if (Lis.getText().equals("Pull and Hold") ) {
						Thread.sleep(2000);
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", Lis);
						Thread.sleep(2000);
						break ;
					}

				}
				log.logLine(Testname, false, "Selected \"Pull and Hold\" item from \"Action to take on match:\" dropdown");
			}else { 				
				for (WebElement  Li : MatchdrpItem){
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Li);
					break ;
				}
				log.logLine(Testname, false, "Selected an item from \"Action to take on match:\" dropdown");
			}
		}else{
			log.logLine(Testname, true, "Could NOT select any item from \"Action to take on match:\" dropdown ");
			//throw new Exception("Could NOT select any item from \"Action to take on match:\" dropdown ");
		}

		

		WebElement SelCrDropdown;
		for (int i=0 ; i<1 ; i++){
		if (doesElementExist2(properties.getProperty("SelectionCriteriaDropdown_"+i), 5)) {
			SelCrDropdown = driver.findElement(By.cssSelector(properties.getProperty("SelectionCriteriaDropdown_"+i)));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", SelCrDropdown);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on \"Selection Criteria\" dropdown") ;
			
			
			if (doesElementExist2(properties.getProperty("SelectionCriteriaDropdownFirstname"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelectionCriteriaDropdownFirstname")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("First Name")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the First Name option from the dropdown");
						break;
					}						
				}
		}else{
			log.logLine(Testname, true, "Could not click on \"Selection Criteria\" because Dropdown does NOT exist");
			//throw new Exception("Could not click on \"Selection Criteria\" because Dropdown does NOT exist");
		}
	}
		
				
		if (doesElementExist2(properties.getProperty("OKEdit"), 5)) {
			OKEd = driver.findElement(By.cssSelector(properties.getProperty("OKEdit" )));
			if(OKEd.isDisplayed())
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", OKEd);
			Thread.sleep(2000);
		}
		log.logLine(Testname, false, "Selected an item from \"Selection Criteria\" dropdown") ;

		

		if (doesElementExist2(properties.getProperty("SelectionCriteriaTextBox_"+i), 5)) {
			WebElement SelCrTxBx = driver.findElement(By.cssSelector(properties.getProperty("SelectionCriteriaTextBox_"+i)));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", SelCrTxBx);
			Thread.sleep(2000);
			SelCrTxBx.clear();
			SelCrTxBx.sendKeys(EditName.concat(AccNo));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", SelCrTxBx);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entered "+EditName+AccNo+" in Text Box against \"Selection Criteria\" dropdown ");
		}else {
			log.logLine(Testname, true, "Could NOT entered text because there exists NO Text Box against \"Selection Criteria\" dropdown  ");
			//throw new Exception("Could NOT entered text because there exists NO Text Box against \"Selection Criteria\" dropdown ");
		}	
		
		}
		
		return true ;				

	}
	
	
public boolean EditPullSaveAs(String Testname ) throws Exception {
		
		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
	    
	    
	    EditName="JIM_";
	    
		Thread.sleep(2000);
		WebElement Actiondrpdwn ;
		WebElement opt = null ;					
		List<WebElement> Matchdrp ;
		List<WebElement> Actionlist;
		WebElement ListElement ;
		Boolean PullHoldFound = false ;
		WebElement OKEd ;

	
		
		log.logLine(Testname, false, "----------- Editing a Pull Instruction -------------");		
		
		if (doesElementExist2(properties.getProperty("chooseActiondropdown"), 5)) {	    
			Actiondrpdwn = driver.findElement(By.cssSelector(properties.getProperty("chooseActiondropdown")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Actiondrpdwn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on \"Choose Action...\" dropdown under \"Actions\" column");
		}else{
			log.logLine(Testname, true, "Could not click on \"Choose Action...\" dropdown because it does NOT exist under \"Actions\" column");
			throw new Exception("Could not click on \"Choose Action...\" dropdown because it does NOT exist under \"Actions\" column");
		}

		if (doesElementExist2(properties.getProperty("chooseActionEdit"), 5)) {
			List<WebElement> ActionEdit = driver.findElements(By.cssSelector(properties.getProperty("chooseActionEdit")));
			for (WebElement lnk:ActionEdit) {
				if (lnk.getText().equals("Edit Instruction/Rule")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(2000);
					log.logLine(Testname, false, "Selecting the Edit Action");
					break;
				}				
			}
			log.logLine(Testname, false, "Selected \"Edit Instruction/Rule\" item from \"Choose Action...\" dropdown");
		
		}else if(doesElementExist2(properties.getProperty("chooseActionEdit1"), 5)) {
			List<WebElement> ActionEdit = driver.findElements(By.cssSelector(properties.getProperty("chooseActionEdit1")));
			for (WebElement lnk:ActionEdit) {
				if (lnk.getText().equals("Edit Instruction/Rule")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(2000);
					log.logLine(Testname, false, "Selecting the Edit Action");
					break;
				}				
			}
			log.logLine(Testname, false, "Selected \"Edit Instruction/Rule\" item from \"Choose Action...\" dropdown");
		}
		else {
			log.logLine(Testname, true, "Could not Select \"Edit Instruction/Rule\" item from \"Choose Action...\" dropdown because it does NOT exist");
			throw new Exception("Could not Select \"Edit Instruction/Rule\" item from \"Choose Action...\" dropdown because it does NOT exist");
		}
		
		
		

		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("Matdropdown"), 5)) {	    
			Matchdrp = driver.findElements(By.xpath(properties.getProperty("Matdropdown")));
			WebElement Matdrp = driver.findElement(By.xpath(properties.getProperty("Matdropdown")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Matdrp);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on \"Action to take on match:\" dropdown") ;
		}else {
			log.logLine(Testname, true, "\"Action to take on match:\" dropdown is NOT displayed on \"Create/Update Pull Instruction\" modal dialog");
			throw new Exception("\"Action to take on match:\" dropdown is NOT displayed on \"Create/Update Pull Instruction\" modal dialog");
		}
		//Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("MatchdropdownItem_1"), 5)) {
			List<WebElement> MatchdrpItem = driver.findElements(By.cssSelector(properties.getProperty("MatchdropdownItem_1" )));
			for (WebElement ListEl : MatchdrpItem){
				if (ListEl.getText().equals("Pull and Hold") ) {
					PullHoldFound = true ;
				} else {
					PullHoldFound = false ;
				}
			}
			Thread.sleep(2000);
			if (PullHoldFound = true ){
				Thread.sleep(2000);
				for ( WebElement Lis : MatchdrpItem){
					Thread.sleep(2000);
					if (Lis.getText().equals("Pull and Hold") ) {
						Thread.sleep(2000);						
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", Lis);
						Thread.sleep(2000);
						break ;
					}

				}
				log.logLine(Testname, false, "Selected \"Pull and Hold\" item from \"Action to take on match:\" dropdown");
			}else { 				
				for (WebElement  Li : MatchdrpItem){					
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Li);
					break ;
				}
				log.logLine(Testname, false, "Selected an item from \"Action to take on match:\" dropdown");
			}
		}else{
			log.logLine(Testname, true, "Could NOT select any item from \"Action to take on match:\" dropdown ");
			throw new Exception("Could NOT select any item from \"Action to take on match:\" dropdown ");
		}

		

		WebElement SelCrDropdown;
		for (int i=0 ; i<1 ; i++){
		if (doesElementExist2(properties.getProperty("SelectionCriteriaDropdown_"+i), 5)) {
			SelCrDropdown = driver.findElement(By.cssSelector(properties.getProperty("SelectionCriteriaDropdown_"+i)));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", SelCrDropdown);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on \"Selection Criteria\" dropdown") ;
			
			
			if (doesElementExist2(properties.getProperty("SelectionCriteriaDropdownFirstname"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelectionCriteriaDropdownFirstname")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("First Name")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the First Name option from the dropdown");
						break;
					}						
				}
		}else{
			log.logLine(Testname, true, "Could not click on \"Selection Criteria\" because Dropdown does NOT exist");
			throw new Exception("Could not click on \"Selection Criteria\" because Dropdown does NOT exist");
		}
	}
		
		
				
		if (doesElementExist2(properties.getProperty("OKEdit"), 5)) {
			OKEd = driver.findElement(By.cssSelector(properties.getProperty("OKEdit" )));
			if(OKEd.isDisplayed())
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", OKEd);
			Thread.sleep(2000);
		}
		log.logLine(Testname, false, "Selected an item from \"Selection Criteria\" dropdown") ;

		

		if (doesElementExist2(properties.getProperty("SelectionCriteriaTextBox_"+i), 5)) {
			WebElement SelCrTxBx = driver.findElement(By.cssSelector(properties.getProperty("SelectionCriteriaTextBox_"+i)));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", SelCrTxBx);
			SelCrTxBx.clear() ;
			SelCrTxBx.sendKeys(EditName.concat(AccNo));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", SelCrTxBx);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entered "+EditName+AccNo+" in Text Box against \"Selection Criteria\" dropdown ");
		}else {
			log.logLine(Testname, true, "Could NOT entered text because there exists NO Text Box against \"Selection Criteria\" dropdown  ");
			throw new Exception("Could NOT entered text because there exists NO Text Box against \"Selection Criteria\" dropdown ");
		}	
		
		}
		
		return true ;				

	}	
	//***********************Validate Edit ******************************************************************************************************			
	public boolean ValidateEditPull(String Testname ) throws Exception {
		
				
		List<WebElement> ListOfRows  ;
		WebElement tbl;
		if (doesElementExist2(properties.getProperty("tablePullInstruction"), 15)) {		

			tbl = driver.findElement(By.cssSelector(properties.getProperty("tablePullInstruction")));							
			ListOfRows = tbl.findElements(By.tagName("tr")) ;
			int tablesize = ListOfRows.size() ;
	
			ListOfRows = tbl.findElements(By.tagName("tr")) ;
			if ( Save =="SaveNew"){
				if (tbl.findElements(By.tagName("tr")).get(1).getText().contains(EditName.concat(AccNo)) )
				{
					log.logLine(Testname, false, "Validated successfully that the \"Pull Instruction\" was Edited ");
				}else {
					log.logLine(Testname, true, "\"Pull Instruction\"  could NOT be Edited");
				}

			}else {
				Thread.sleep(2000);
				ListOfRows = tbl.findElements(By.tagName("tr")) ;
				if (ListOfRows.get(1).getText().contains(EditName.concat(AccNo)))
				{
					log.logLine(Testname, false, "Validated successfully that the \"Pull Instruction\" was Edited ");
				}else {
					log.logLine(Testname, false, "\"Pull Instruction\"  could NOT be Edited");
				}
			} 
		}
		return true ;						
	}		

	//***********************Validate Edit ******************************************************************************************************			
	public boolean OKbtn(String Testname ) throws Exception {	

		if (doesElementExist2(properties.getProperty("OKEdit"), 5)) {
			OKbutton = driver.findElement(By.cssSelector(properties.getProperty("OKEdit")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", OKbutton);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on \"OK\" button");
		}else {
			log.logLine(Testname, true, "Could NOT clicked on \"OK\" button because it does NOT exist");
			throw new Exception("Could NOT clicked on \"OK\" button because it does NOT exist");
		}
		return true ;						
	}

	//******************Choose Action delete**************************************************************************************

	public boolean ActionDelete(String Testname ) throws Exception {
		WebElement	Actiondrpdwn ;
		List<WebElement> ActionDel ;	
		log.logLine(Testname, false, "--------- Deleting a Pull Instruction -------------");
		
		if (doesElementExist2(properties.getProperty("chooseActiondropdown"), 5)) {	    
			Actiondrpdwn = driver.findElement(By.cssSelector(properties.getProperty("chooseActiondropdown")));			
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Actiondrpdwn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on \"Choose Action...\" dropdown under \"Actions\" column");
		}else{
			log.logLine(Testname, true, "Could not click on \"Choose Action...\" dropdown because it does NOT exist under \"Actions\" column");
			throw new Exception("Could not click on \"Choose Action...\" dropdown because it does NOT exist under \"Actions\" column");
		}

		if (doesElementExist2(properties.getProperty("chooseActionDelete"), 5)) {	    
			ActionDel = driver.findElements(By.cssSelector(properties.getProperty("chooseActionDelete")));
			for (WebElement lnk:ActionDel) {
				if (lnk.getText().equals("Delete Instruction/Rule")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(2000);
					log.logLine(Testname, false, "Selecting the Delete Action");
					break;
				}	
			}
			log.logLine(Testname, false, "Selected \"Delete Instruction/Rule\" item from \"Choose Action...\" dropdown");

		}else if (doesElementExist2(properties.getProperty("chooseActionDelete1"), 5)) {	    
			ActionDel = driver.findElements(By.cssSelector(properties.getProperty("chooseActionDelete1")));
			for (WebElement lnk:ActionDel) {
				if (lnk.getText().equals("Delete Instruction/Rule")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(2000);
					log.logLine(Testname, false, "Selecting the Delete Action");
					break;
				}	
			}
			log.logLine(Testname, false, "Selected \"Delete Instruction/Rule\" item from \"Choose Action...\" dropdown");

		}
		else{
			log.logLine(Testname, true, "Could not Select \"Delete Instruction/Rule\" item from \"Choose Action...\" dropdown because it does NOT exist");
			throw new Exception("Could not Select \"Delete Instruction/Rule\" item from \"Choose Action...\" dropdown because it does NOT exist");

		}
		return true ;	
	}

	//******************Choose  delete Action Confirm **************************************************************************************

	public boolean ActionDeleteConfirm(String Testname ) throws Exception {

		if (doesElementExist2(properties.getProperty("Delete"), 5)) {
			WebElement ConfirmActionDelete = driver.findElement(By.cssSelector(properties.getProperty("Delete")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ConfirmActionDelete);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on \"OK\" button on \"Delete Pull\" dialog box to Confirm Delete");
		} else {
			log.logLine(Testname, true, "Could not click on \"OK\" button to Confirm Delete, because it does NOT exist on \"Delete Pull\" dialog box ");
			throw new Exception("Could not click on \"OK\" button to Confirm Delete, because it does NOT exist on \"Delete Pull\" dialog box ");
		}
		return true ;	
	}

	//***********************Calculate Table size ******************************************************************************************************			
	public boolean CalculateTablesize(String Testname ) throws Exception {						
		List<WebElement> ListOfRows  ;
		WebElement tbl;
		if (doesElementExist2(properties.getProperty("tablePullInstruction"), 15)) {
			tbl = driver.findElement(By.cssSelector(properties.getProperty("tablePullInstruction")));							
			ListOfRows = tbl.findElements(By.tagName("tr")) ;
			tablesize = ListOfRows.size() ;
			tsize = tablesize ;
			log.logLine(Testname, false, "\"Pull Instruction\" table consists of "+tsize+"rows");
		}
		else {
			log.logLine(Testname, true, "\"Pull Instruction\" table does NOT exist ");
			throw new Exception("\"Pull Instruction\" table does NOT exist ");
		}
		return true ;						
	}		


	//***********************Validate Delete Pull ******************************************************************************************************			
	public boolean ValidateDeletePull(String Testname , int tablesize) throws Exception {						
		List<WebElement> ListOfRows  ;
		WebElement tbl;
		if (doesElementExist2(properties.getProperty("tablePullInstruction"), 15)) {
			tbl = driver.findElement(By.cssSelector(properties.getProperty("tablePullInstruction")));							
			ListOfRows = tbl.findElements(By.tagName("tr")) ;
			currentTableSize = ListOfRows.size() ;	

			if ( !(currentTableSize ==tablesize)){
				log.logLine(Testname, false, "Validated that Row from \"Pull Instruction\" table got Deleted succcessfully");
			}else {
				log.logLine(Testname, true, " Row could NOT be Deleted from \"Pull Instruction\" table");
				throw new Exception("Row could NOT be Deleted from \"Pull Instruction\" table");
			}
		}else {
			log.logLine(Testname, true, "Table \"Pull Instruction\" does Not exist");
			throw new Exception("Table \"Pull Instruction\" does Not exist");
		} 
		return true ;						
	}	

	//*************************************Method to click Advance Search Button**************************************

	public boolean AdvanceSearch(String Testname ) throws Exception {

		if (doesElementExist2(properties.getProperty("AdvanceSearch"), 5)) {	    
			WebElement AdvSearch = driver.findElement(By.cssSelector(properties.getProperty("AdvanceSearch")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", AdvSearch);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked on \"Advance Search\" Button...");
		} else {
			log.logLine(Testname, true, "\"Advance Search\" Button does NOT exist");
			throw new Exception("\"Advance Search\" Button does NOT exist");
		}	
		return true;
	}	

	//*************************************Method to Clear Dates from Advance search**************************************

	public boolean DateClearAdvSearch(String Testname ) throws Exception {
		if (doesElementExist2(properties.getProperty("DateFromAdvanceSearch"), 5)) {	    
			WebElement DateFromAdvSearch = driver.findElement(By.cssSelector(properties.getProperty("DateFromAdvanceSearch")));
			DateFromAdvSearch.clear();
			log.logLine(Testname, false, "Cleared \"From Date\" on Advance Search modal window");
		} else {
			log.logLine(Testname, true, "\"From Date\" on Advance Search modal window could NOT be cleared");
			throw new Exception("\"From Date\" on Advance Search modal window could NOT be cleared");
		}	

		if (doesElementExist2(properties.getProperty("DateToAdvanceSearch"), 5)) {	    
			WebElement DateToAdvSearch = driver.findElement(By.cssSelector(properties.getProperty("DateToAdvanceSearch")));
			DateToAdvSearch.clear();
			log.logLine(Testname, false, "Cleared \"To Date\" on Advance Search modal window");
		} else {
			log.logLine(Testname, true, "\"To Date\" Input box on Advance Search modal window does NOT exist");
			throw new Exception("\"To Date\" Input Box on Advance Search modal window does NOT exist");
		}	
		return true;
	}	

	//*************************************Method to click search button Advance search**************************************

	public boolean SearchButtonAdvSearch(String Testname ) throws Exception {
		if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {	    
			WebElement Searchbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Searchbtn)	;
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked \"Search Button\" on Advance Search modal window");
		} else {
			log.logLine(Testname, true, "\"Search Button\" does NOT exist on Advance Search modal window");
			throw new Exception("\"Search Button\" does NOT exist on Advance Search modal window");
		}	
		return true;
	}	

	//*************************************Method to validate message for Start Dates required **************************************

	public boolean ValidateStartDateRequired(String Testname ) throws Exception {
		
		
		if (doesElementExist2(properties.getProperty("StartDateToolTip"), 5)) {	    
			WebElement StartDateTTip = driver.findElement(By.cssSelector(properties.getProperty("StartDateToolTip")));
			String TextStartDateTTip = StartDateTTip.getAttribute("title") ;
			if(StartDateTTip.isDisplayed())	{
				log.logLine(Testname, false, "Message appears that \"Start Date\" is required, exact message is "+TextStartDateTTip+" ");
			}
		} else {
			log.logLine(Testname, true, "Message does NOT appear that \"Start Date\" is required");
			throw new Exception( "Message does NOT appear that \"Start Date\" is required");
		}
			
		return true;
	}	

	//*************************************Method to validate message for End Date required **************************************

	public boolean ValidateEndDateRequired(String Testname ) throws Exception {
		
				
		if (doesElementExist2(properties.getProperty("EndDateToolTip"), 5)) {	    
			WebElement EndDateTlTip = driver.findElement(By.cssSelector(properties.getProperty("EndDateToolTip")));
			String TextEndDateTlTip = EndDateTlTip.getAttribute("title") ;
			if((EndDateTlTip.isDisplayed())){
				log.logLine(Testname, false, "Validated succesfully that Message appears that \"End Date\" is required, exact message is "+TextEndDateTlTip+" ");
			}
		} else {
			log.logLine(Testname, true, "Message does NOT appear that \"End Date\" is required");
			throw new Exception( "Message does NOT appear that \"End Date\" is required");
		}	
		return true;
	}	

	//*************************************Method to validate that extra details show up for Pull **************************************

	public boolean ExtraDetails(String Testname , String DateFrom , String DateTo ) throws Exception {

		Thread.sleep(2000);
		List<WebElement> tableRow ;		
		WebElement tableElement ;

		log.logLine(Testname, false, "-------------Validating that Extra Details are shown for Pulls-------------");
		if (doesElementExist2(properties.getProperty("tablePullInstruction"), 15)) {	
			tableElement = driver.findElement(By.cssSelector(properties.getProperty("tablePullInstruction")));	
			log.logLine(Testname, false, "Table \"Pull Instruction\" is displayed ");
		}else {
			log.logLine(Testname, true, "Table \"Pull Instruction\" does NOT exist");
			throw new Exception("Table \"Pull Instruction\" does NOT exist");
		}			

		if (doesElementExist2(properties.getProperty("tablePullRow"), 15)) {	
			tableRow = driver.findElements(By.cssSelector(properties.getProperty("tablePullRow")));	
			for (int rowNum=0 ; rowNum <tableRow.size() && rowNum <16; rowNum++ )
			{	
				int i = rowNum ;
				WebElement columns = tableRow.get(rowNum).findElement(By.cssSelector(properties.getProperty("tdComment"+i))) ;
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", columns)	;
				List<WebElement> col = tableRow.get(rowNum).findElements(By.tagName("td")) ;
				int numOfCols = col.size();

				Actions action = new Actions(driver);
				action.moveToElement(columns).build().perform();

				if (doesElementExist2(properties.getProperty("ExtraDetailTitle"+i), 15)) {
					WebElement ExtDetTitle = tableRow.get(rowNum).findElement(By.cssSelector(properties.getProperty("ExtraDetailTitle"+i))) ;

					String TextExtDetTitle = ExtDetTitle.getAttribute("title") ;

					int LenExtDetTitle =  TextExtDetTitle.length() ;			

					if (TextExtDetTitle.equals("No additional information"))
					{
						log.logLine(Testname, false, "Row "+i+" \" "+TextExtDetTitle+" \" is displayed in the comment box");
					}else
						if (LenExtDetTitle > 0){

							log.logLine(Testname, false, "Row "+i+" extra information \" "+TextExtDetTitle+" \" is displayed in the comment box");
						}else{
							log.logLine(Testname, true, "Row "+i+" has"+numOfCols+" but  extra information is NOT displayed in the comment box");
							throw new Exception("Row "+i+" has"+numOfCols+" but  extra information is NOT displayed in the comment box");
						}
				}

			}

		} else {
			log.logLine(Testname, false, "The pulls in this date range searched are NOT displayed ");
			if (doesElementExist2(properties.getProperty("NoItemsToDisplay"), 15)) {	
				tableElement = driver.findElement(By.cssSelector(properties.getProperty("NoItemsToDisplay")));	
				String TexNoItems = tableElement.getText() ;
				log.logLine(Testname, false, "Message \" "+TexNoItems+" \" is displayed" );
				//throw new Exception("The pulls in this date range searched are NOT displayed ");
			}else{

			}			
	    }		
		return true;
	}	 

	//********************Clear Table***********************************************

	public boolean tableClear(String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("tablePullInstruction"), 15)) {		
			Thread.sleep(2000);

			WebElement tbl = driver.findElement(By.cssSelector(properties.getProperty("tablePullInstruction")));	
			List<WebElement> ListOfRows = tbl.findElements(By.tagName("tr")) ;
			currentTableSize = ListOfRows.size() ;	

			for (WebElement Record : ListOfRows){
				tbl = driver.findElement(By.cssSelector(properties.getProperty("tablePullInstruction")));
				currentTableSize = ListOfRows.size() ;	
				ActionDelete(Testname ) ;
				Thread.sleep(2000);
				ActionDeleteConfirm(Testname ) ;
				driver.switchTo().defaultContent();
				if (doesElementExist2(properties.getProperty("PullInstructions"), 15)) {		
					Thread.sleep(2000);

					WebElement	PullIn = driver.findElement(By.cssSelector(properties.getProperty("PullInstructions")));	
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", PullIn)	;
					PleasewaitDisappear();			
					driver.switchTo().frame("iframeContainer");
				}

			}

			log.logLine(Testname, false, "Table pull Instruction Cleared successfully");
		} else {

			log.logLine(Testname, true, "Table pull Instruction does NOT exist");
			throw new Exception("Table pull Instruction does NOT exist");
		}

		return true;

	}
	//********************Advance search - Cancel Button***********************************************

	public boolean AdvancesearchCancelButton(String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("AdvCancelbutton"), 15)) {		
			Thread.sleep(2000);
			WebElement AdvCancelbtn = driver.findElement(By.cssSelector(properties.getProperty("AdvCancelbutton")));	
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", AdvCancelbtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked \"Cancel\" button on \"Advance Search\" modal window");
		} else {

			log.logLine(Testname, true, "Could not click \"Cancel\" button beacuse it does NOT exist on \"Advance Search\" modal window ");
			throw new Exception("Could not click \"Cancel\" button beacuse it does NOT exist on \"Advance Search\" modal window ");
		}
		return true;
	}

	//********************Advance search - Clear Button***********************************************

	public boolean AdvanceClearBtn(String Testname) throws Exception {
		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("AdvClear"), 15)) {					
			WebElement AdvClearbt = driver.findElement(By.cssSelector(properties.getProperty("AdvClear")));	
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", AdvClearbt)	;
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked \"Clear\" button on \"Advance Search\" modal window");
		} else {

			log.logLine(Testname, true, "Could not click \"Clear\" button beacuse it does NOT exist on \"Advance Search\" modal window ");
			throw new Exception("Could not click \"Clear\" button beacuse it does NOT exist on \"Advance Search\" modal window ");
		}
		return true;
	}			

	//********************AdvanceSearch -Enter Valid dates ***********************************************

	public boolean SearchwithDateAdvanceSearch(String Testname , String SearchCriteriaDateFrom ,String SearchCriteriaDateTo) throws Exception {

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("DateFromAdvanceSearch"), 5)) {	    
			WebElement DateFromAdvSearch = driver.findElement(By.cssSelector(properties.getProperty("DateFromAdvanceSearch")));
			DateFromAdvSearch.clear();
			DateFromAdvSearch.sendKeys(SearchCriteriaDateFrom);
			DateFrom = SearchCriteriaDateFrom  ;
			log.logLine(Testname, false, "Clearing \"From\" Date in \"Advance search\"");
		} else {
			log.logLine(Testname, true, "\"From\" Date in \"Advance search\" could NOT be cleared");
			throw new Exception(" \"From\" Date in \"Advance search\" could NOT be cleared");
		}	

		if (doesElementExist2(properties.getProperty("DateToAdvanceSearch"), 5)) {	    
			WebElement DateToAdvSearch = driver.findElement(By.cssSelector(properties.getProperty("DateToAdvanceSearch")));
			DateToAdvSearch.clear();
			DateToAdvSearch.sendKeys(SearchCriteriaDateTo);
			DateTo = SearchCriteriaDateTo  ;
			log.logLine(Testname, false, "Clearing \"To\" Date in \"Advance search\"");
		} else {
			log.logLine(Testname, true, "\"To\" Date in \"Advance search\" could NOT be cleared");
			throw new Exception("\"To\" Date in \"Advance search\" could NOT be cleared");
		}		
		log.logLine(Testname, false, "\"Pull\" Dates searched in \"Advance Search\" criteria are \"" +SearchCriteriaDateFrom+ " to " +SearchCriteriaDateTo+"\"");
		return true;
	}	

	//********************AdvanceSearch - validate pulls are displayed on the basis of date searched ***********************************************

	public boolean ValidateDateAdvancesearch(String Testname) throws Exception {
		Thread.sleep(2000);
		List<WebElement> tableRow ;		
		WebElement tableElement ;
		String EffectiveDateRange = null;
		log.logLine(Testname, false, "-------------Validating that \"Pulls\" are displayed on the basis of dates searched-------------");
		if (doesElementExist2(properties.getProperty("tablePullInstruction"), 15)) {	
			tableElement = driver.findElement(By.cssSelector(properties.getProperty("tablePullInstruction")));	

		}else {
			log.logLine(Testname, true, "Table \"Pull Instruction\" does NOT exist");
			throw new Exception("Table \"Pull Instruction\" does NOT exist");
		}

		if (doesElementExist2(properties.getProperty("tablePullRow"), 15)) {	
			tableRow = driver.findElements(By.cssSelector(properties.getProperty("tablePullRow")));	
			
			for (int rowNum=1 ; rowNum <tableRow.size() ; rowNum++ )
			{					
				WebElement columns = tableRow.get(rowNum).findElement(By.cssSelector(properties.getProperty("td"))) ;				
				EffectiveDateRange = columns.getText() ;
				log.logLine(Testname, false, "\"Pull\" record is displayed for " +EffectiveDateRange + " dates in row " +rowNum );				
			}
		} else {
			
			if (doesElementExist2(properties.getProperty("NoItemsToDisplay"), 15)) {	
				tableElement = driver.findElement(By.cssSelector(properties.getProperty("NoItemsToDisplay")));	
				String TexNoItems = tableElement.getText() ;
				log.logLine(Testname, false, "Message displayed  "+"'"+TexNoItems+"'" );
			}else{
			log.logLine(Testname, true, "The pulls in this date range searched are NOT displayed ");
			throw new Exception("Pulls are NOT displayed for date range searched  ");
			}
		}		
		return true;
	}	

	//********************AdvanceSearch - validate Fields are cleared on the basis of data searched ***********************************************

	public boolean ValidateFieldsClear(String Testname) throws Exception {

		WebElement SelCrDropdown = null ;
		WebElement  SelCrDropdownhover  = null ;
		String TextDisplayedSelCrDropdown  = null ;
		String TextExpectedSelCrDropdown = null ;
		for (int i = 0 ; i < 1 ;  i++) 
		{ 
			Thread.sleep(2000);
			List<WebElement> SelCrDrpdwnItem ;
			if (doesElementExist2(properties.getProperty("AdvanceSelectionDropdown_"+i), 5))
			{
				SelCrDropdown = driver.findElement(By.cssSelector(properties.getProperty("AdvanceSelectionDropdown_"+i)));
				TextExpectedSelCrDropdown = "Select one" ;
				TextDisplayedSelCrDropdown = SelCrDropdown.getText() ;
				if (TextDisplayedSelCrDropdown.contains(TextExpectedSelCrDropdown))
				{
					log.logLine(Testname, false, "Validated Successfully that Fields are cleared on \"Advance search\"");
					break;
				}
			}
			else{
				if (doesElementExist2(properties.getProperty("AdvanceSelectionDropdown_hover"), 5))
				{
					SelCrDropdownhover = driver.findElement(By.cssSelector(properties.getProperty("AdvanceSelectionDropdown_hover")));				

					TextExpectedSelCrDropdown = "Select one..." ;
					TextDisplayedSelCrDropdown = SelCrDropdownhover.getText() ;
					if (TextDisplayedSelCrDropdown.contains(TextExpectedSelCrDropdown))
					{
						log.logLine(Testname, false, "Fields are cleared on \"Advance search\"");
					break ;
					}
				}
				log.logLine(Testname, true, "\"Selection Criteria\" Dropdown does NOT exist");
				throw new Exception("\"Selection Criteria\" Dropdown does NOT exist");
			}
		}
		return true;
	}	

	//*************************************Method to search with - Selection Criteria Field1**************************************

	public boolean SearchwithField1(String Testname ) throws Exception {
		for (int i = 0 ; i < 1 ;  i++) { 
			WebElement SelCrDropdown;

			List<WebElement> SelCrDrpdwnItem ;
			WebElement SelCrDrpItem ;
			
			Thread.sleep(2000);

			if (doesElementExist2(properties.getProperty("AdvanceSelectionDropdown_"+i), 5)) {
				SelCrDropdown = driver.findElement(By.cssSelector(properties.getProperty("AdvanceSelectionDropdown_"+i)));				
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", SelCrDropdown);
				Thread.sleep(2000);
				log.logLine(Testname, false, "Clicked on \"Selection Criteria\" Dropdown ");

				//AdvanceFirtsDropdown= driver.findElements(By.cssSelector(properties.getProperty("AdvanceSelectionDropdown_"+i )));				
			}else{
				log.logLine(Testname, true, "\"Selection Criteria\" Dropdown does not exist");
				throw new Exception("\"Selection Criteria \" Dropdown does not exist");
			}

			if (doesElementExist(properties.getProperty("AdvanceAllDropdownItem_"+i), 5)) {
				SelCrDrpdwnItem = driver.findElements(By.xpath(properties.getProperty("AdvanceAllDropdownItem_"+i )));
				SelCrDrpItem = driver.findElement(By.xpath(properties.getProperty("AdvanceAllDropdownItem_"+i )));

				for (WebElement option:SelCrDrpdwnItem)
				{

					if (option.getText().equals("Action"))
					{
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", option);
						Thread.sleep(2000);
						log.logLine(Testname, false, "Selected \"Action\" item from \"Selection Criteria\" Dropdown");
						break;
					}
				}
			}else{	
				log.logLine(Testname, true, " \"Action\" item does NOT exist in \"Selection Criteria\" Dropdown");
				throw new Exception(" \"Action\" item does NOT exist in \"Selection Criteria\" Dropdown");
				

				}

			if (doesElementExist2(properties.getProperty("AdvSelectionDropdown_"+i), 5)) {
				WebElement SelCrTxBx = driver.findElement(By.cssSelector(properties.getProperty("AdvSelectionDropdown_"+i)));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", SelCrTxBx);
				log.logLine(Testname, false, "Clicked on another dropdown displayed against \"Action\" field in \"Selection Criteria\" ");
			}else {
				log.logLine(Testname, true, "NO dropdown is displayed against \"Action\" field in \"Selection Criteria\" ");
				throw new Exception("NO dropdown is displayed against \"Action\" field in \"Selection Criteria\" ");
			}	
			if (doesElementExist2(properties.getProperty("AdvSelectionDropdownItem_"+i), 5)) {
				List<WebElement> AdvSelCrDrpdwnItem = driver.findElements(By.cssSelector(properties.getProperty("AdvSelectionDropdownItem_"+i )));

				for (WebElement opt:AdvSelCrDrpdwnItem)
				{
					if (opt.getText().equals("Alternative Routing"))					
					{
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", opt);
						Thread.sleep(2000);
						log.logLine(Testname, false, "Selected \"Alternative Routing\" item from dropdown displayed against \"Action\" field under \"Selection Criteria\" ");
						break;
					}
				}				
			}else{
				log.logLine(Testname, true, " \"Alternative Routing\"  item does NOT exist in dropdown displayed against \"Action\" field under \"Selection Criteria\"");
				throw new Exception(" \"Alternative Routing\"  item does NOT exist in dropdown displayed against \"Action\" field under \"Selection Criteria\"");
			}
		}
		return true;
	}	

	//********************AdvanceSearch - validate form close**********************************************

	public boolean ValidateFormClose(String Testname) throws Exception {
		
		WebElement SelCrTxBx = null;
		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("AdvsearchForm"), 5)) {
			SelCrTxBx = driver.findElement(By.cssSelector(properties.getProperty("AdvsearchForm")));
		}
		if (SelCrTxBx.isDisplayed())
		{
			log.logLine(Testname, true, "Advance Search Form did NOT close ");
			throw new Exception("Advance Search Form did NOT close ");
		}else
		{
			log.logLine(Testname, false, "Validated Successfully that Advance Search Form closed  ");
		}	
		return true;
	}	
	//********************AdvanceSearch - validate pulls are displayed on the basis of Field1 searched ***********************************************

	public boolean ValidateDateAdvancesearchField1(String Testname) throws Exception {
		List<WebElement> tableRow ;		
		WebElement tableElement ;
		WebElement Actiondrpdwn ;
		WebElement opt = null ;					
		List<WebElement> Matchdrp ;
		List<WebElement> Actionlist;
		WebElement ListElement ;
		Boolean PullHoldFound = false ;

		if (doesElementExist2(properties.getProperty("tablePullInstruction"), 15)) {	
			tableElement = driver.findElement(By.cssSelector(properties.getProperty("tablePullInstruction")));					  
		}

		if (doesElementExist2(properties.getProperty("tablePullRow"), 15)) {	
			tableRow = driver.findElements(By.cssSelector(properties.getProperty("tablePullRow")));	
			for (int rowNum=0 ; rowNum <(tableRow.size()) ; rowNum++ )
			{	  
				int i= rowNum ;
				int u= tableRow.size() ;
				PleasewaitDisappear();
				Thread.sleep(2000);
				if (doesElementExist2(properties.getProperty("chooseActiondropdown_"+i), 5)) {	    
					Actiondrpdwn = driver.findElement(By.cssSelector(properties.getProperty("chooseActiondropdown_"+i)));
					//	Actionlist = driver.findElements(By.cssSelector(properties.getProperty("chooseActiondropdown")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Actiondrpdwn);
					Thread.sleep(2000);
					log.logLine(Testname, false, "Clicked on \"Choose Action\" dropdownn ");

				}else{
					log.logLine(Testname, true, "\"Choose Action\" dropdownn does NOT exist");
					throw new Exception("\"Choose Action\" dropdownn does NOT exist");
				}

				if (doesElementExist2(properties.getProperty("chooseActionEdit"), 5)) {
					List<WebElement> ActionEdit = driver.findElements(By.cssSelector(properties.getProperty("chooseActionEdit")));
					for (WebElement lnk:ActionEdit) {


						if (lnk.getText().equals("Edit Instruction/Rule")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(2000);
							log.logLine(Testname, false, "Selected \"Edit Instruction/Rule\" Action on \"Choose Action\" dropdownn");
							break;
						}				
					}
				}else if(doesElementExist2(properties.getProperty("chooseActionEdit1"), 5)) {
					List<WebElement> ActionEdit = driver.findElements(By.cssSelector(properties.getProperty("chooseActionEdit1")));
					for (WebElement lnk:ActionEdit) {
						if (lnk.getText().equals("Edit Instruction/Rule")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(2000);
							log.logLine(Testname, false, "Selecting the Edit Action");
							break;
						}				
					}
					log.logLine(Testname, false, "Selected \"Edit Instruction/Rule\" item from \"Choose Action...\" dropdown");
				} 
				else {
					log.logLine(Testname, true, "\"Choose Action\" droprdown is NOT displayed");
					throw new Exception("\"Choose Action\" droprdown is NOT displayed");
				}

				if (doesElementExist(properties.getProperty("Matdropdown"), 5)) {	    
					Matchdrp = driver.findElements(By.xpath(properties.getProperty("Matdropdown")));
					WebElement Matdrp = driver.findElement(By.xpath(properties.getProperty("Matdropdown")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Matdrp);
					Thread.sleep(2000);
					log.logLine(Testname, false, "clicked on \"Action to take on match:\" dropdown ") ;
				}else {
					log.logLine(Testname, true, "\"Action to take on match:\" dropdown is NOT displayed");
					throw new Exception("\"Action to take on match:\" dropdown is NOT displayed");
				}

				if (doesElementExist2(properties.getProperty("MatchdropdownItem_0"), 5)) {
					List<WebElement> MatchdrpItem = driver.findElements(By.cssSelector(properties.getProperty("MatchdropdownItem_0" )));


					for (WebElement ListEl : MatchdrpItem){

						if (ListEl.getText().equals("Alternative Routing") ) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", ListEl);
							Thread.sleep(2000);
							log.logLine(Testname, false, "Results are displayed in the table with searched Field 1 Alternative Routing correctly");
							break ;
						} 
					}
					Cancelbutton(Testname) ; 
				}
			}	
		}
		return true;
	}
}




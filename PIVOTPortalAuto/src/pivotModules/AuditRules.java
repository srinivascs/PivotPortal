package pivotModules;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import launchAuto.Initialization;
import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AuditRules extends Page {

	public String newline , Testname;
	public String path;
	public String segementid1;
	public String segmentdescription1;
	public String segementid2;
	public String segmentdescription2;
	public String subsegmentid;
	public String subsegmentdescription;
	public int k;
	public int QuantityAppTextVal = 0 ;	
	public int j, KeyNameIncreaseCount, KeyValIncreaseCount;
	/*public  enum colKeyVal {
		KeyValue1,KeyValue2,KeyValue3,KeyValue4,KeyValue5,KeyValue6,KeyValue7, KeyValue8, KeyValue9, KeyValue10 
	}
	 */
	public String KeyDesc1, KeyDesc2, KeyDesc3, KeyDesc4, KeyDesc5;

	public String keyName , u="li";
	public String regExpr, fieldNameSearched="A", SubSegSearched, SubSeg;
	public String Descrip;
	public String KeyV;
	public String field="B", value=null;

	WebDriverWait wait = new WebDriverWait(driver, 55);

	public AuditRules(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}
	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}


	public boolean Auditspage(String AccNo, String Testname) throws Exception {
		InputOutputData test = new InputOutputData();		
		test.setInputFile("C:/Pivot_Portal/Test Data/AuditKeys.xls");
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();		
		String adtMaxKeys=test.readColumnData("AMaxKey", "AuditMaxKeys");			
		int cntMaxKeys=Integer.parseInt(adtMaxKeys);		
		int cntdel=Integer.parseInt(adtMaxKeys);
		test.setInputFile("C:/Pivot_Portal/Test Data/AuditKeys.xls");
		sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		KeyDesc1=test.readColumnData("KeyDescp1", sheetname);
		KeyDesc2=test.readColumnData("KeyDescp2", sheetname);
		KeyDesc3=test.readColumnData("KeyDescp3", sheetname);
		KeyDesc4=test.readColumnData("KeyDescp4", sheetname);
		KeyDesc5=test.readColumnData("KeyDescp5", sheetname);

		String[] Sort = new String[50];
		String[] Descp = new String[50];

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

		if (doesElementExist2(properties.getProperty("Okbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Okbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on OK button to view the Audits");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the Audits is failed");
			throw new Exception("Clicking on OK button to view the Audits is failed");
		}

		//________________________________ creating Audit Keys to be used in Rules  _____________________________________________________________________________________________________________________________________________________________________________________________	

		test.setInputFile("C:/Pivot_Portal/Test Data/AuditKeys.xls");
		adtMaxKeys=test.readColumnData("AMaxKey", "AuditMaxKeys");			
		cntMaxKeys=Integer.parseInt(adtMaxKeys);		
		cntdel=Integer.parseInt(adtMaxKeys);
		Thread.sleep(3000);
		ClkAuditRules(Testname );
		AuditKeys AuditKey_Inst = new AuditKeys(driver, log);
		AuditKey_Inst.clkAuditKeys (Testname );	
		AuditKey_Inst.cleanUp(Testname , cntdel);
		AuditKey_Inst.addNewKey(Testname , "N" , cntdel , cntMaxKeys) ;			
		AuditKey_Inst.ClkSaveButton(Testname);


		//__________________________________Check for Invalid data while saving Audit Rule Functionality _________________________________________________________________________________________________________________________________________________________________________________________________________________________________

		//		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("AddAuditRule")));
		Thread.sleep(2000);
		ClkAddAuditRule(Testname);
		selectKeyForAuditRule(Testname , "N" ,"Invalid Data", cntdel , cntMaxKeys) ;	
		Enterinvalidvalue(Testname);
		EnterBeginDate(Testname);
		Thread.sleep(2000);
		EnterEndDate(Testname);
		Enterworkflownme(Testname);
		clickSave(Testname ) ;
		clickSaveRuleOnPopUP(Testname ) ;
		Alert alert = driver.switchTo().alert();
		alert.accept();
		ifAlertClkOK(Testname) ;
		Thread.sleep(2000);
		ClickCancel(Testname);
		Thread.sleep(2000);
		AdvSearchBtn(Testname);	
		AdvanceSearchForKeyValue(Testname, "Invaliddata");
		Thread.sleep(2000);

		//___________________________________ Check for Tool tip for Key Value Required________________________________________________________________________________________________________________________________________________________________________________________________________________________________________			
		ClkAddAuditRule( Testname );
		selectKeyForAuditRule(Testname , "N", "ToolTipKeyValue",  cntdel , cntMaxKeys) ;
		EnterBeginDate(Testname);
		Thread.sleep(2000);
		EnterEndDate(Testname);
		Quantity(Testname, "Create") ;
		Hold(Testname );
		Subseg(Testname);
		clickSave(Testname ) ;
		clickSaveRuleOnPopUP(Testname ) ;
		//Alert alert = driver.switchTo().alert();
		alert.accept();				
		ClickCancel(Testname);

		//___________________________________ Check for Maximum rules Alert  ________________________________________________________________________________________________________________________________________________________________________________________________________________________________	


		for(int i=1,KeyNameIncreaseCount=1 ; i<=cntMaxKeys+1 ; i++, KeyNameIncreaseCount++){
            Thread.sleep(2000);
			ClkAddAuditRule( Testname );
			selectKeyForAuditRule(Testname , "N" , "MaximumRulesAlert", cntdel , cntMaxKeys+1) ;
			Thread.sleep(2000);
			EnterKeyValueForRule (Testname , "Maximum"  ) ;			
			EnterBeginDate(Testname);
			EnterEndDate(Testname );
			Quantity(Testname, "Create") ;
			Hold(Testname);
			Subseg(Testname);
			Enterworkflownme(Testname);
			clickSave(Testname ) ;
			clickSaveRuleOnPopUP(Testname) ;
			Thread.sleep(2000);
			if(i==cntMaxKeys+1)
			{//Alert alert = driver.switchTo().alert();
				alert.accept();
				Thread.sleep(1000);
				log.logLine(Testname, false, "We are successful in max rule validation");
				ClickCancel(Testname);
				break;
			}

		}




		//___________________________________ Check if Audit rules are editable________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________		
		
		Thread.sleep(2000);
		AdvanceSearchFromToDate(Testname);	
		AdvanceSearchSearchButton(Testname);
		Thread.sleep(2000);
		ActionEdit(Testname);
		Thread.sleep(2000);
		EnterKeyValueForRule(Testname, "Edit"); 
		Thread.sleep(2000);
		Editworkflow(Testname,"workflow_test1");
		Thread.sleep(1000);
		EditKeyfield(Testname);
		Thread.sleep(1000);
		Editsubseg(Testname);
		Thread.sleep(3000);
		ClickUpdate(Testname);
		Thread.sleep(5000);
		ActionEdit(Testname);
		ActionEditCancel(Testname);
		Thread.sleep(3000);


		//___________________________________ Check if Audit rules Advance search________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________			

		String field=readKeyFieldFromAuditTable(Testname );
		AdvSearchWithKeyField(Testname);
		AdvanceSearchSearchButton(Testname);
		readKeyFieldFromAuditTable(Testname ); 
		ValidateKeyFieldName(Testname ,field, fieldNameSearched) ;
		AdvanceSearchSubSegment(Testname);
		AdvanceSearchSearchButton(Testname);
		ClickCancel(Testname);	
		AdvanceSearchKeyValue(Testname, value); 
		AdvanceSearchFromToDate(Testname);
		AdvanceSearchSearchButton(Testname);
		AdvanceSearchFromToDate(Testname);
		AdvanceSearchCancel(Testname);

		return true ; 

	}
	//***************************************************************************************************************************************************************************************************************************************************************
	//                                             End of Method Auditspage
	//**************************************************************************************************************************************************************************************************************************************************************	

	//*****************************************************************************************************************************************************************************************************************************************************************************************************
	//                                                    Methods
	//*****************************************************************************************************************************************************************************************************************************************************************************************************

	public boolean clickSave(String Testname ) throws Exception  {
		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("SaveAdtRule"), 5)) {	
			WebElement SavRul = driver.findElement(By.cssSelector(properties.getProperty("SaveAdtRule")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", SavRul);
			Thread.sleep(2000);
			//	SavRul.click();
			log.logLine(Testname, false, "Clicked on Save button after adding the Audit Rule ");

		}else{	
			log.logLine(Testname, true, "Clicked on Save button after adding the Audit Rule is failed");
			throw new Exception("Clicked on Save button after adding the Audit Rule is failed");
		}
		return true;
	}	

	public boolean clickSaveRuleOnPopUP(String Testname ) throws Exception  {
		if (doesElementExist2(properties.getProperty("SaveRule"), 5)) {	
			Thread.sleep(2000);
			WebElement SavRul = driver.findElement(By.cssSelector(properties.getProperty("SaveRule")));
			SavRul.click();
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on Save Rule button on the pop up window ");

		}else{	
			log.logLine(Testname, true, "Save Rule button does NOT appear on the pop up window ");
			throw new Exception("Save Rule button does NOT appear on the pop up window ");
		}
		return true;
	}

	private boolean selectKeyForAuditRule(String Testname , String  Duplicate ,String  OperationType,  int cntdel , int cntMaxKeys) throws Exception {

		InputOutputData test = new InputOutputData();	
		test.setInputFile("C:/Pivot_Portal/Test Data/AuditKeys.xls");	
		int colNum = 1 ;
		String colKeyName = "" ;

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("selKey"), 5)) {	   
			WebElement KeySel = driver.findElement(By.cssSelector(properties.getProperty("selKey")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", KeySel);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on Key Field dropdown..");

			switch (OperationType) { 
			case "Create" :				
				cntMaxKeys = cntMaxKeys;
				colKeyName = "Key"+KeyNameIncreaseCount;	
				break;
			case "MaximumRulesAlert":
				cntMaxKeys = cntMaxKeys+1;
				KeyNameIncreaseCount = KeyNameIncreaseCount+1;
				colKeyName = "Key"+KeyNameIncreaseCount;				
				break;
			case "ToolTipKeyValue" :
				cntMaxKeys = 1 ;
				KeyNameIncreaseCount = KeyNameIncreaseCount+1;
				colKeyName = "Key"+KeyNameIncreaseCount;	
				break;
			case "Bad Data" :
				cntMaxKeys = 1 ;
				KeyNameIncreaseCount = KeyNameIncreaseCount+1;
				colKeyName = "Key"+KeyNameIncreaseCount;	
				break;
			case "CreateAll" :
				cntMaxKeys = cntMaxKeys;  
				KeyNameIncreaseCount = KeyNameIncreaseCount+1;
				colKeyName = "Key"+KeyNameIncreaseCount;
				break;
			}
		}

		for(int Cnt=1; Cnt<=cntMaxKeys; Cnt++){
			if (doesElementExist2("div[class='k-animation-container'] div ul li[role='option']", 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector("div[class='k-animation-container'] div ul li[role='option']"));
				keyName=test.readColumnData(colKeyName, "AuditRules");
				for (WebElement lnk:selopts) {
					if (lnk.getText().startsWith(keyName)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Key Field Name "+keyName+"   from the dropdown..");
						break;
					}				

				}
			} else {
				log.logLine(Testname, true, " Key Field Name options are not displayed");
				//throw new Exception(" Key Field Name are not displayed");
			} 
		}
		return true;		
	}


	public boolean AdvanceSearchCancel(String Testname) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		Date date = new Date();	
		String todaysDate = dateFormat.format(date);	

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("CancelBtn"), 5)) {	    
			WebElement clkadv = driver.findElement(By.cssSelector(properties.getProperty("CancelBtn")));		   
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);		     
			log.logLine(Testname, false, "Clicking cancel button on Advance Search is successful");
		} else {
			log.logLine(Testname, true, "Clicking cancel button on Advance Search is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Clicking cancel button on Advance Search is failed");
		}

		if (doesElementExist2(properties.getProperty("AdvSrchContainer"), 5)) {	    
			WebElement clkadv = driver.findElement(By.cssSelector(properties.getProperty("AdvSrchContainer")));
			if(clkadv.isDisplayed()){
				log.logLine(Testname, true, "Advance search container is displayed ");
			}else{
				log.logLine(Testname, false, "Advance search container is not displayed ");
			}

		} else {
			log.logLine(Testname, true, "Clicking cancel button is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Clicking cancel button is failed");
		}
		return true;
	}

	public String AdvSearchWithKeyField(String Testname)throws Exception {

		if (doesElementExist2(properties.getProperty("AdvanceSearch"), 5)) {	    
			WebElement clkadv = driver.findElement(By.cssSelector(properties.getProperty("AdvanceSearch")));		   
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);		     
			log.logLine(Testname, false, "Click on advancve search under Audit Rules");
		} else {
			log.logLine(Testname, true, "Click on advancve search under Audit Rules is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on advancve search under Audit Rules is failed");
		}
		AdvanceSearchClearBtn(Testname);
		Actions action = new Actions(driver);
		if (doesElementExist2("div+div[id='audits-sections-audit-rules-search-dropdown'] div+div div span[class='k-widget k-dropdown k-header dropdownlist ddlCriteria'] span span[class='k-input']", 5)) {
			WebElement SeaCriteriaKeyField = driver.findElement(By.cssSelector("div+div[id='audits-sections-audit-rules-search-dropdown'] div+div div span[class='k-widget k-dropdown k-header dropdownlist ddlCriteria'] span span[class='k-input']"));


			((JavascriptExecutor) driver).executeScript("arguments[0].click()", SeaCriteriaKeyField);
			log.logLine(Testname, false, "Clicking on \" Search Criteria \" dropdown in advance Search ");
			Thread.sleep(2000);

			List<WebElement> vwrpts = driver.findElements(By.cssSelector("ul[id='ddlSearchRule_listbox'] li"));
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().contains("Key Field")) {

					action.click(lnk).perform(); 					
					PleasewaitDisappear();		
					log.logLine(Testname, false, "Selecting the option  \"Key Field\" as search criteria from the dropdown in Advance search");	

					if (doesElementExist2("div[id='audits-sections-audit-rules-search-dropdown'] span+span+span span span[class='k-input']", 5)) {
						WebElement SearchCriteriaKeyFieldName = driver.findElement(By.cssSelector("div[id='audits-sections-audit-rules-search-dropdown'] span+span+span span span[class='k-input']"));
						String SelectOne = SearchCriteriaKeyFieldName.getText();
						int SelectOnelen =  SelectOne.length();

						((JavascriptExecutor) driver).executeScript("arguments[0].click()", SearchCriteriaKeyFieldName);


						if (doesElementExist2("div[class='k-list-container k-popup k-reset adv-search-dropdown1'] div+div+div+div+div+div+div[class='k-animation-container'] div[id='ddlSearchRules-list'] ul[id='ddlSearchRules_listbox'] li[role='option']", 5)) {
							List<WebElement>  SeaCriteriaKeyFieldName = driver.findElements(By.cssSelector("div[class='k-list-container k-popup k-reset adv-search-dropdown1'] div+div+div+div+div+div+div[class='k-animation-container'] div[id='ddlSearchRules-list'] ul[id='ddlSearchRules_listbox'] li[role='option']"));

							for (WebElement liink:SeaCriteriaKeyFieldName) {

								int liinklen = liink.getText().length() ;
								if (!(liinklen==SelectOnelen)) {
									fieldNameSearched = liink.getText();

									((JavascriptExecutor) driver).executeScript("arguments[0].click()", liink);

									break;
								}
							}
						}
					}
				}
				break;
			}
		}
		return fieldNameSearched;
	}

	public boolean AdvanceSearchKeyField(String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("AdvanceSearch"), 5)) {	    
			WebElement clkadv = driver.findElement(By.cssSelector(properties.getProperty("AdvanceSearch")));		   
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);		     
			log.logLine(Testname, false, "Click on advancve search under Audit Rules");
		} else {
			log.logLine(Testname, true, "Click on advancve search under Audit Rules is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on advancve search under Audit Rules is failed");
		}
		AdvanceSearchClearBtn(Testname);
		Actions action = new Actions(driver);
		if (doesElementExist2(properties.getProperty("Selectone"), 5)) {
			WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("Selectone")));
			log.logLine(Testname, false, "Clicking on select one drop down to select the option key field value");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
			Thread.sleep(1000);

			List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SelectKeyField")));	
			for (int j=1 ; j<=3 ; j++){
				switch (j) {

				case 1:	
					for (WebElement lnk:vwrpts) {
						if (lnk.getText().contains("Key Field")) {
							action.click(lnk).perform(); 
							Thread.sleep(3000);
							PleasewaitDisappear();		
							log.logLine(Testname, false, "Selecting the option as key field value");	
							for (int i=0 ; i<=20 ; i++){	
								if (doesElementExist2("html body div div+div+div+div+div+div div span+span[class='k-pager-info k-label']", 5)) {
									WebElement NoDocs = driver.findElement(By.cssSelector("html body div div+div+div+div+div+div div span+span[class='k-pager-info k-label']"));
									String A = NoDocs.getText();
									if(NoDocs.getText().equals("No documents found")){
										log.logLine(Testname, false, "All Rules deleted");	
										break ;
									}	else {
										ActionDelete(Testname) ;
									}

								}
							}
							break;

						}
					}
				case 2:		
					for (WebElement lnk:vwrpts) {
						if (lnk.getText().contains("Key Value")) {
							action.click(lnk).perform(); 
							Thread.sleep(3000);
							PleasewaitDisappear();		
							log.logLine(Testname, false, "Selecting the option as key field value");			
							for (int i=0 ; i<=20 ; i++){	
								if (doesElementExist2("html body div div+div+div+div+div+div div span+span[class='k-pager-info k-label']", 5)) {
									WebElement NoDocs = driver.findElement(By.cssSelector("html body div div+div+div+div+div+div div span+span[class='k-pager-info k-label']"));
									String A = NoDocs.getText();
									if(NoDocs.getText().equals("No documents found")){
										log.logLine(Testname, false, "All Rules deleted");	
										break ;
									}	else {
										ActionDelete(Testname) ;
									}

								}
							}
							break;
						}
					}
				case 3:	
					for (WebElement lnk:vwrpts) {
						if (lnk.getText().contains("Sub-Segments")) {
							action.click(lnk).perform(); 
							Thread.sleep(3000);
							PleasewaitDisappear();		
							log.logLine(Testname, false, "Selecting the option as Sub segment");	
							for (int i=0 ; i<=20 ; i++){	
								if (doesElementExist2("html body div div+div+div+div+div+div div span+span[class='k-pager-info k-label']", 5)) {
									WebElement NoDocs = driver.findElement(By.cssSelector("html body div div+div+div+div+div+div div span+span[class='k-pager-info k-label']"));
									String A = NoDocs.getText();
									if(NoDocs.getText().equals("No documents found")){
										log.logLine(Testname, false, "All Rules deleted");	
										break ;
									}	else {
										ActionDelete(Testname) ;
									}

								}
							}
							break;
						}
					}

				}

			}
		}

		if (doesElementExist2(properties.getProperty("SearchBtn"), 5)) {	    
			WebElement clkadv = driver.findElement(By.cssSelector(properties.getProperty("SearchBtn")));		   
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Click on search button under advance search of  Audit Rules ");
		} else {
			log.logLine(Testname, true, "Click on search button under advance search of Audit Rules is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on search button under advance search of Audit Rules is failed");
		}

		if (doesElementExist2(properties.getProperty("AuditTable"), 5)) {
			log.logLine(Testname, false, "Advanced search - Successfully displayed the audits for entered text in srchType field");		    		    	
		} else {
			log.logLine(Testname, false, "Advanced search - No audits displayed the audits for entered text in srchType field");
		}	
		return true;
	}

	public boolean AdvanceSearchKeyValue(String Testname, String value) throws Exception {

		Actions action = new Actions(driver);
		if (doesElementExist2(properties.getProperty("AdvanceSearch"), 5)) {	    
			WebElement clkadv = driver.findElement(By.cssSelector(properties.getProperty("AdvanceSearch")));		   
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);		     
			log.logLine(Testname, false, "Click on advancve search under Audit Rules");
		} else {
			log.logLine(Testname, true, "Click on advancve search under Audit Rules is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Click on advancve search under Audit Rules is failed");
		}

		AdvanceSearchClearBtn(Testname);

		if (doesElementExist2(properties.getProperty("SelectSearch"), 5)) {

			WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("SelectSearch")));
			log.logLine(Testname, false, "Clicking on search criteria to select the option key field from list");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
			Thread.sleep(2000);
			List<WebElement> vwrpts = driver.findElements(By.cssSelector("div[class='k-animation-container'] div[id='ddlSearchRule-list'] ul[id='ddlSearchRule_listbox'] li[role='option']"));	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals("Key Value")) {
					action.click(lnk).perform();
					Thread.sleep(2000);
					PleasewaitDisappear();
					log.logLine(Testname, false, "Selecting the option key value from list ");			
					break;
				}
			}
		} else {
			log.logLine(Testname, true, "Clicking on search criteria to select the option key field from list is failed");
			//throw new Exception("Clicking on search criteria to select the option key field from list is failed");
		}


		if (doesElementExist2("div+div[id='audits-sections-audit-rules-search-dropdown'] div+div div input[class='k-input k-textbox']", 5)) {	    
			WebElement AdvSeaKeyValue = driver.findElement(By.cssSelector("div+div[id='audits-sections-audit-rules-search-dropdown'] div+div div input[class='k-input k-textbox']"));
			AdvSeaKeyValue.clear();
			if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer"))){
				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value +"')", AdvSeaKeyValue);}
			else
				{AdvSeaKeyValue.sendKeys("12345678");
			Thread.sleep(4000);
			log.logLine(Testname, false, "Entering the key value in text box as "+value);
		}} if (doesElementExist2("div+div[id='audits-sections-audit-rules-search-dropdown'] div+div div input[class='k-input k-textbox k-valid']", 5)) {	    
			WebElement AdvSeaKeyValue = driver.findElement(By.cssSelector("div+div[id='audits-sections-audit-rules-search-dropdown'] div+div div input[class='k-input k-textbox k-valid']"));
			AdvSeaKeyValue.clear();
			if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer"))){
				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value +"')", AdvSeaKeyValue);}
			else
				{AdvSeaKeyValue.sendKeys("12345678");
			Thread.sleep(4000);
			log.logLine(Testname, false, "Entering the key value in text box as "+value);
		}}else {
			log.logLine(Testname, true, "Entering the key value in text box is failed");
			//driver.switchTo().defaultContent();
			//throw new Exception("Entering the key value in text box is failed");
		}

		if (doesElementExist2(properties.getProperty("SearchBtn"), 5)) {	    
			WebElement clkadv = driver.findElement(By.cssSelector(properties.getProperty("SearchBtn")));		   
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Click on search button under advance search of  Audit Rules ");
		} else {
			log.logLine(Testname, true, "Click on search button under advance search of Audit Rules is failed");
			//driver.switchTo().defaultContent();
			//throw new Exception("Click on search button under advance search of Audit Rules is failed");
		}

		if (doesElementExist2(properties.getProperty("AuditTable"), 5)) {
			log.logLine(Testname, false, "Advanced search - Successfully displayed the audits for entered text in srchType field");		    		    	
		} else {
			log.logLine(Testname, false, "Advanced search - No audits displayed the audits for entered text in srchType field");
		}	
		return true;
	}

	public String AdvanceSearchSubSegment(String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("AdvanceSearch"), 5)) {	    
			WebElement clkadv = driver.findElement(By.cssSelector(properties.getProperty("AdvanceSearch")));		   
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);		     
			log.logLine(Testname, false, "Click on advancve search under Audit Rules");
		} else {
			log.logLine(Testname, true, "Click on advancve search under Audit Rules is failed");
			driver.switchTo().defaultContent();
			//	throw new Exception("Click on advancve search under Audit Rules is failed");
		}
		AdvanceSearchClearBtn(Testname);
		Actions action = new Actions(driver);
		if (doesElementExist2(properties.getProperty("SelectSearch"), 5)) {
			WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("SelectSearch")));
			log.logLine(Testname, false, "Clicking on search criteria to select the option Sub-Segment from list");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
			Thread.sleep(2000);
			List<WebElement> vwrpts = driver.findElements(By.cssSelector("ul[id='ddlSearchRule_listbox'] li"))	;
			//	List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SearchKeyField")))	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals("Sub-Segment")) {					
					action.click(lnk).perform();
					Thread.sleep(2000);
					PleasewaitDisappear();
					log.logLine(Testname, false, "Selecting the option subsegment from list");			
					break;
				}
			}
		} else {
			log.logLine(Testname, true, "Clicking on search criteria to select the option Sub-Segment from list is failed");
			//throw new Exception("Clicking on search criteria to select the option Sub-Segment from list is failed");
		}

		if (doesElementExist2("div+div[id='audits-sections-audit-rules-search-dropdown'] div+div div span+span+span span span[class='k-input']", 5)) {					
			WebElement  SeaCriteriaSubSegdrpdown = driver.findElement(By.cssSelector("div+div[id='audits-sections-audit-rules-search-dropdown'] div+div div span+span+span span span[class='k-input']"));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", SeaCriteriaSubSegdrpdown);
			Thread.sleep(1000);
			if (doesElementExist2("div[id='ddlSearchRules-list'] ul[id='ddlSearchRules_listbox'] li[role='option']", 5)) {
				List<WebElement>  SeaCriteriaKeyFieldName = driver.findElements(By.cssSelector("div[id='ddlSearchRules-list'] ul[id='ddlSearchRules_listbox'] li[role='option']"));

				//	List<WebElement> vwrpts = driver.findElements(By.cssSelector("ul[id='ddlSearchRule_listbox'] li"))	;

				for (WebElement lnk:SeaCriteriaKeyFieldName) {
					SubSegSearched=lnk.getText();
					if (lnk.getText().contains("All")) {
						action.click(lnk).perform() ;
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						log.logLine(Testname, false, "Selecting the Subsegement as All from drop down");

						break;
					}						
				}
			}
		}



		return SubSegSearched;
	}

	public boolean AdvanceSearchSearchButton(String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("SearchBtn"), 5)) {	    
			WebElement clkadv = driver.findElement(By.cssSelector(properties.getProperty("SearchBtn")));		   
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Click on search button under advance search of  Audit Rules ");
		} else {
			log.logLine(Testname, true, "Click on search button under advance search of Audit Rules is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Click on search button under advance search of Audit Rules is failed");
		}

		if (doesElementExist2(properties.getProperty("AuditTable"), 5)) {
			log.logLine(Testname, false, "Advanced search - Successfully displayed the audits for entered text in srchType field");		    		    	
		} else {
			log.logLine(Testname, false, "Advanced search - No audits displayed the audits for entered text in srchType field");
		}	
		return true;
	}

	public boolean AdvanceSearchFromToDate(String Testname) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		Date date = new Date();	
		String todaysDate = dateFormat.format(date);

		//	AdvanceSearchClearBtn(Testname);

		if (doesElementExist2(properties.getProperty("AdvanceSearch"), 5)) {	    
			WebElement clkadv = driver.findElement(By.cssSelector(properties.getProperty("AdvanceSearch")));		   
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);		     
			log.logLine(Testname, false, "Click on advancve search under Audit Rules");
		} else {
			log.logLine(Testname, true, "Click on advancve search under Audit Rules is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Click on advancve search under Audit Rules is failed");
		}
		
		AdvanceSearchClearBtn(Testname);
		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("AdvSrhBeginDate"), 5)) {	    
			WebElement begndate = driver.findElement(By.cssSelector(properties.getProperty("AdvSrhBeginDate")));
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(properties.getProperty("AdvSrhBeginDate"))));

			begndate.clear();
			begndate.sendKeys("12/01/2005");		     
			log.logLine(Testname, false, "Enter from date value in Begin date field of Advance Search");
		} else {
			log.logLine(Testname, true, "Enter from date value in Begin date field of Advance Search is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Enter from date value in Begin date field of Advance Search is failed");
		}

		if (doesElementExist2(properties.getProperty("AdvSrhEndDate"), 5)) {	    
			WebElement enddate = driver.findElement(By.cssSelector(properties.getProperty("AdvSrhEndDate")));
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(properties.getProperty("AdvSrhEndDate"))));
			enddate.clear();			
			enddate.sendKeys("12/01/2020");		     
			log.logLine(Testname, false, "Enter todate value in End date field of Advance Search ");
		} else {
			log.logLine(Testname, true, "Enter todate value in End date field of Advance Search  is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Enter todate value in End date field of Advance Search  is failed");
		}


		return true;
	}

	public boolean ActionEditCancel(String Testname) throws Exception {
		Thread.sleep(2000);
		if (doesElementExist2("span[class='k-widget k-dropdown k-header ddlUpdateActions'] span span[class='k-input']", 5)) {
			WebElement chooseActCancel = driver.findElement(By.cssSelector("span[class='k-widget k-dropdown k-header ddlUpdateActions'] span span[class='k-input']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", chooseActCancel);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on Choose Action to of audit rules");
			Thread.sleep(2000);
			List<WebElement> ItemEdit = driver.findElements(By.cssSelector("div[class='k-animation-container'] div ul[role='listbox'] li"));				
			for (WebElement lnk:ItemEdit) {
				String abc=lnk.getText();
				if (lnk.getText().equalsIgnoreCase("Cancel")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);							
					Thread.sleep(2000);
					log.logLine(Testname, false, "Clicked on \" Cancel \" button under choose actions");			
					break;
				}
			}
		}
		else {
			log.logLine(Testname, true, "Clicking on \" Cancel \" button under choose actions is failed");
			//throw new Exception("Clicking on \" Cancel \" button under choose actions is failed");
		}
		return true;
	}

	public boolean ActionDelete(String Testname) throws Exception {	

		String u = "li";
		if (doesElementExist2("span[class='k-widget k-dropdown k-header ddlActions'] span span[class='k-input']", 5)) {
			WebElement choseactions = driver.findElement(By.cssSelector("span[class='k-widget k-dropdown k-header ddlActions'] span span[class='k-input']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseactions);
			log.logLine(Testname, false, "Clicking on Choose Action of audit rules is successful");
			Thread.sleep(1000);
		}else {
			log.logLine(Testname, true, "Clicking on Choose Action of audit rules is failed");
			throw new Exception("Clicking on Choose Action of audit rules is failed");
		}

		u = u+"+li";
		if (doesElementExist2("li+li+li[role='option']", 5)) {
			List<WebElement> del= driver.findElements(By.cssSelector("li+li+li[role='option']"));

			for(WebElement litem:del){
				String Text = litem.getText();
				if (litem.getText().contains("Delete")){
					litem.click();
					//((JavascriptExecutor) driver).executeScript("arguments[0].click()", litem);
					Thread.sleep(3000);
					log.logLine(Testname, false, "Clicking on delete button to delete the audit rule");
					Alert alert = driver.switchTo().alert();
					alert.accept();
					log.logLine(Testname, false, "Clicking ok button to delete the audit rule is sucessful");	
					Thread.sleep(5000);
					break ; 
				}
			}
		}else if (doesElementExist2("div[class='k-animation-container km-popup'] div ul "+u+"[role='option']", 5)) {
			WebElement del= driver.findElement(By.cssSelector("div[class='k-animation-container km-popup'] div ul "+u+"[role='option']"));
			String Text = del.getText();
			if (del.getText().equals("Delete")){
				del.click();
				Thread.sleep(2000);
				//((JavascriptExecutor) driver).executeScript("arguments[0].click()", del);
				log.logLine(Testname, false, "Clicking on delete button to delete the audit rule");
				Alert alert = driver.switchTo().alert();
				alert.accept();
				log.logLine(Testname, false, "Clicking ok button to delete the audit rule is sucessful");	
				Thread.sleep(5000);
				//	break  ;
			}
		}

		return true;
	}

	public boolean ActionEdit(String Testname) throws Exception {

		if (doesElementExist2("span[class='k-widget k-dropdown k-header ddlActions'] span span[class='k-input']", 5)) {
			WebElement choseacts = driver.findElement(By.cssSelector("span[class='k-widget k-dropdown k-header ddlActions'] span span[class='k-input']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
			Thread.sleep(1000);			
			log.logLine(Testname, false, "Clicking on Choose Action to of audit rules");
			Thread.sleep(2000);
			List<WebElement> vwrpts = driver.findElements(By.cssSelector("div[class='k-animation-container'] div+div ul li[role='option']"));	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals("Edit")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);	   	
					Thread.sleep(5000);
					log.logLine(Testname, false, "Clicking on edit button under choose actions to modify");			
					break;
				}
			}
		} else {
			log.logLine(Testname, true, "Clicking on Choose Action of audit rules is failed");
			//throw new Exception("Clicking on Choose Action of audit rules is failed");
		}

		return true;
	}
	public boolean EditQuanity(String Testname) throws Exception{
		Actions action = new Actions(driver);
		if (doesElementExist2("span span input[type='text']", 5)) {	    
			WebElement qty = driver.findElement(By.cssSelector("span span input[type='text']"));
			//action.moveToElement(qty).click().perform();
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", qty);
			String quan = "2" ;
			qty.clear();

			if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer")))
				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + quan +"')", qty);
			else
				qty.sendKeys(quan);			

			log.logLine(Testname, false, "Successfully clicked on Choose actions");
		} else {
			log.logLine(Testname, true, "clicking on Choose actions is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the quantity value in quantity text box is failed");
		}
		return true;
	}
	public boolean ClickUpdate(String Testname) throws Exception{
		Actions action = new Actions(driver);
		if (doesElementExist2(properties.getProperty("ChooseAction1"), 5)) {
			WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("ChooseAction1")));
			wait.until(ExpectedConditions.visibilityOf(choseacts));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on Choose Action to update the audit rule");

			List<WebElement> vwrpts = driver.findElements(By.cssSelector("div+div+div+div+div+div+div+div+div+div+div+div+div[class='k-animation-container'] div ul[role='listbox'] li+li[role='option']"));	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals("Update")) {

					action.click(lnk).perform() ;	
					Thread.sleep(5000);
					log.logLine(Testname, false, "Clicking on Update button to save the modification");			
					break;
				}
			}
		} else {
			log.logLine(Testname, true, "Clicking on Choose Action to update the audit rule is failed");
			//	throw new Exception("Clicking on Choose Action to update the audit rule is failed");
		}
		return true;
	}

	public boolean AlertMaxAuditRule(String Testname) throws Exception{
		if (doesElementExist2(properties.getProperty("alertMaxSubSeg"), 5)) {
			WebElement SubMaxAlert = driver.findElement(By.cssSelector(properties.getProperty("alertMaxSubSeg")));
			String kAlertText = SubMaxAlert.getText();
			if (kAlertText.equals("You may not add another selection - doing so would exceed allowance")){
				log.logLine(Testname, false, "Alert for adding greater than the Max audit rule not allowed pop is displayed ");
			}
			else {	
				log.logLine(Testname, true, "Alert for adding greater than the Max audit rule not allowed pop is not displayed ");
			} 	
			log.logLine(Testname, false, "Validation Pass - Alert appears for adding greater than the Max audit rule & displays the message : " +kAlertText);
		} else {
			log.logLine(Testname, true, "Validation Fail - Alert does not appears for adding greater than the Max audit rule");
			throw new Exception("Validation Fail - Alert does not appears for adding greater than the Max audit rule ");
		}

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("OkButton"), 5)) {
			WebElement okAlert = driver.findElement(By.cssSelector(properties.getProperty("OkButton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okAlert);
			log.logLine(Testname, false, "Clicked OK button on Max audit rules not allowed pop up Alert  " );
		} else {
			log.logLine(Testname, true, "Max audit rules Alert is not displayed hence did not click on OK button");
			throw new Exception("Max audit rules Alert is not displayed hence did not click on OK button");
		}
		ClkExtraCancelButton(Testname);
		Thread.sleep(5000);

		return true;
	}		

	/*public boolean KeyRequired(String Testname) throws Exception{
		if (doesElementExist2(properties.getProperty("tooltipValidationMsg"), 5)) {	
			Thread.sleep(2000);
			WebElement tooltipValMsg = driver.findElement(By.cssSelector(properties.getProperty("tooltipValidationMsg")));
			log.logLine(Testname, false, "Warning to Validate Empty Key value of Audit Rule  appears");
		}else{	
			log.logLine(Testname, true, "Warning to Validate Empty Key value of Audit Rule does NOT appears");
		//	throw new Exception("Warning to Validate Empty Key value of Audit Rule does NOT appears");
		}
		//	ClkExtraCancelButton(Testname);
		return true;
	}	*/

	public boolean ClkAuditRules(String Testname ) throws Exception {
		if (doesElementExist2(properties.getProperty("AuditRules"), 5)) {	    
			WebElement ruleslnk = driver.findElement(By.cssSelector(properties.getProperty("AuditRules")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ruleslnk);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Audit Rules...");
		} else {
			log.logLine(Testname, true, "Audit Rules link does not exist");
			throw new Exception("Audit Rules link does not exist");
		}	
		driver.switchTo().frame("iframeContainer");		
		return true;
	}

	public boolean ClkSaveButton(String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("Savebutton"), 5)) {	 

			WebElement kSaveBtn = driver.findElement(By.cssSelector(properties.getProperty("Savebutton")));
			Thread.sleep(2000);
			kSaveBtn.click();				
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked Save Button");

		} else {

			log.logLine(Testname, true, "Save Button does not exist");
			throw new Exception("Save Button does not exist");

		}	

		return true;

	}

	public boolean ClkAddAuditRule( String Testname  ) throws Exception {	

		if (doesElementExist2(properties.getProperty("AddAuditRule"), 20)) {	    
			WebElement AddAuditRuleBtn = driver.findElement(By.cssSelector(properties.getProperty("AddAuditRule")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", AddAuditRuleBtn);

			log.logLine(Testname, false, "Clicking on AddAuditRule button on top right");
		} else {
			log.logLine(Testname, true, "AddAuditRule button does not exist");
			throw new Exception("AddAuditRule button does not exist");
		}		

		return true;
	}

	public boolean ClkCancelButton( String Testname ) throws Exception {			

		if(doesElementExist2(properties.getProperty("CancelButton"), 5)) {	  
			WebElement CancelBtn = driver.findElement(By.cssSelector(properties.getProperty("CancelButton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", CancelBtn);
			log.logLine(Testname, false, "Clicked Cancel Button ");
		}else {
			log.logLine(Testname, true, "Cancel does not exist");
			throw new Exception("Cancel does not exist");

		}	

		return true;
	}

	public boolean ClkExtraCancelButton( String Testname ) throws Exception {			

		if(doesElementExist2(properties.getProperty("ExtraAdtCancel"), 5)) {	  
			WebElement CancelBtn = driver.findElement(By.cssSelector(properties.getProperty("ExtraAdtCancel")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", CancelBtn);
			log.logLine(Testname, false, "Clicked Cancel Button ");
		}else {
			log.logLine(Testname, true, "Cancel does not exist");
			throw new Exception("Cancel does not exist");

		}	

		return true;
	}

	public boolean AuditRulesDelete(String AccNo, String Testname) throws Exception {

		//	***************************************************
		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();


		String[] Sort = new String[50];
		String[] Descp = new String[50];

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
					if (lnk.getText().equals(ClntName)) {
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
						if (lnk.getText().equals(ClntName)) {
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
					if (lnk.getText().equals(AppName)) {
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
						if (lnk.getText().equals(AppName)) {
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

		clickOKButton(Testname ) ;

		//	****************************************
		ClkAuditRules(Testname );
		//	driver.switchTo().frame("iframeContainer");		
		
		Parametervalidation(Testname);
		AdvanceSearchFromToDate(Testname);
		AdvanceSearchSearchButton(Testname);

		for (int i=0 ; i<=20 ; i++){	

			if (doesElementExist2("html body div div+div+div+div+div+div div span+span[class='k-pager-info k-label']", 5)) {
				WebElement NoDocs = driver.findElement(By.cssSelector("html body div div+div+div+div+div+div div span+span[class='k-pager-info k-label']"));
				String A = NoDocs.getText();
				if(NoDocs.getText().equals("No documents found")){
					log.logLine(Testname, false, "All Rules deleted");	
					break ;
				}	else {
					ActionDelete(Testname) ;
				}

			}
		}


		//	AdvanceSearchKeyField(Testname);
		return true;
	}

	public String readKeyFieldFromAuditTable( String Testname ) throws Exception {	

		if (doesElementExist2(properties.getProperty("AuditTable"), 5)) {
			field = driver.findElement(By.cssSelector(properties.getProperty("AuditTable"))).getText();
			log.logLine(Testname, false, "Getting the key field from the table grid");		    		    	
		} else {
			log.logLine(Testname, false, "Getting the key field from the table grid is failed");
		}	

		return field ;
	}	

	public String readSubSegFromAuditTable( String Testname ) throws Exception {	

		if (doesElementExist2("div+div+div+div+div+div[id='audits-sections-audit-rules-grid'] table tbody tr td+td+td+td+td+td+td", 5)) {
			SubSeg = driver.findElement(By.cssSelector("div+div+div+div+div+div[id='audits-sections-audit-rules-grid'] table tbody tr td+td+td+td+td+td+td")).getText();
			log.logLine(Testname, false, "Getting the key field from the table grid");		    		    	
		} else {
			log.logLine(Testname, false, "Getting the key field from the table grid is failed");
		}	

		return SubSeg ;
	}	


	public String readKeyFieldValueFromAuditTable( String Testname ) throws Exception {	

		if (doesElementExist2(properties.getProperty("GetKeyvalue"), 5)) {
			value = driver.findElement(By.cssSelector(properties.getProperty("GetKeyvalue"))).getText();
			log.logLine(Testname, false, "Getting the key value from the table grid");		    		    	
		} else {
			log.logLine(Testname, false, "Getting the key value from the table grid is failed");
		}
		return value ;
	}

	public boolean ValidateSubSegSearched(String Testname , String SubSegSearched    , String SubSeg) throws Exception {	

		if(SubSegSearched.equals(SubSeg)){
			log.logLine(Testname, false, "Validation Pass  Audit Table shows records with the searched \""+SubSegSearched+"\"") ;
		}

		return true;

	}

	public boolean ValidateKeyFieldName(String Testname,String FieldNameInTable, String FieldNameSearched)throws Exception {	

		if(FieldNameInTable.equals(FieldNameSearched)){
			log.logLine(Testname, false, "Validation Pass  Audit Table shows records with the searched \""+FieldNameSearched+"\"");
		}

		return true;

	}


	public boolean clickOKButton( String Testname ) throws Exception {	
		if (doesElementExist2(properties.getProperty("Okbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Okbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on OK button to view the Audits");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the Audits is failed");
			throw new Exception("Clicking on OK button to view the Audits is failed");
		}
		return true;
	}

	public boolean AdvanceSearchClear(String Testname) throws Exception {

		AdvanceSearchClearBtn(Testname);

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		Date date = new Date();	
		String todaysDate = dateFormat.format(date);
		if (doesElementExist2(properties.getProperty("AdvanceSearch"), 5)) {	    
			WebElement clkadv = driver.findElement(By.cssSelector(properties.getProperty("AdvanceSearch")));		   
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);		     
			log.logLine(Testname, false, "Click on advancve search under Audit Rules");
		} else {
			log.logLine(Testname, true, "Click on advancve search under Audit Rules is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on advancve search under Audit Rules is failed");

		}

		if (doesElementExist2(properties.getProperty("AdvSrhBeginDate"), 5)) {	    
			WebElement begndate = driver.findElement(By.cssSelector(properties.getProperty("AdvSrhBeginDate")));
			begndate.click();
			begndate.clear();
			begndate.sendKeys("12/01/2014");		     
			log.logLine(Testname, false, "Enter the begin date in advancve search under Audit Rules");
		} else {
			log.logLine(Testname, true, "Entering the begin date in advancve search under Audit Rules is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the begin date in advancve search under Audit Rules is failed");

		}

		if (doesElementExist2(properties.getProperty("AdvSrhEndDate"), 5)) {	    
			WebElement enddate = driver.findElement(By.cssSelector(properties.getProperty("AdvSrhEndDate")));
			enddate.click();
			enddate.clear();
			enddate.sendKeys(todaysDate);		     
			log.logLine(Testname, false, "Enter the end date in advancve search under Audit Rules");
		} else {
			log.logLine(Testname, true, "Entering the end date in advancve search under Audit Rules is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the end date in advancve search under Audit Rules is failed");

		}

		Actions action = new Actions(driver);
		if (doesElementExist2(properties.getProperty("SelectSearch"), 5)) {
			WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("SelectSearch")));
			log.logLine(Testname, false, "Clicking on search criteria to select the option Sub-Segment from list");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
			Thread.sleep(2000);

			List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SearchKeyField")));	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals("Sub-Segment")) {
					action.click(lnk).perform();
					Thread.sleep(2000);
					PleasewaitDisappear();

					log.logLine(Testname, false, "Selecting the option Sub-Segment from list");			
					break;
				}
			}
		} else {
			log.logLine(Testname, true, "Clicking on search criteria to select the option Sub-Segment from list is failed");
			throw new Exception("Clicking on search criteria to select the option Sub-Segment from list is failed");
		}

		if (doesElementExist2(properties.getProperty("ClearBtn"), 5)) {	    
			WebElement clkadv = driver.findElement(By.cssSelector(properties.getProperty("ClearBtn")));		   
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);		     
			log.logLine(Testname, false, "Click on clear button in advancve search under Audit Rules");
		} else {
			log.logLine(Testname, true, "Click on clear button in advancve search under Audit Rules is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on clear button in advancve search under Audit Rules is failed");

		}

		if (doesElementExist2(properties.getProperty("AdvSrhBeginDate"), 5)) {	    
			WebElement begndate = driver.findElement(By.cssSelector(properties.getProperty("AdvSrhBeginDate")));
			//begndate.getAttribute("value");
			if(begndate.getAttribute("value").equals("")){
				log.logLine(Testname, false, "Verification of the clear button of  the advance search values is successful");
			}else{
				log.logLine(Testname, true, "Verification of the clear button of  the advance search values is unsuccessful");
			}

		} else {
			log.logLine(Testname, true, "Verification of the clear button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Verification of the clear button is failed");

		}

		if (doesElementExist2(properties.getProperty("CancelBtn"), 5)) {	    
			WebElement clkadv = driver.findElement(By.cssSelector(properties.getProperty("CancelBtn")));		   
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);		     
			log.logLine(Testname, false, "Clicking cancel button is successful");
		} else {
			log.logLine(Testname, true, "Clicking cancel button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking cancel button is failed");
		}
		return true;
	}

	public void EditKeyfield(String Testname) throws Exception{

		if (doesElementExist2("div[id='audits-sections-audit-rules-grid'] table tbody tr td span[class='k-widget k-dropdown k-header ddlkeyrule'] span span", 5)) {
			WebElement keyfld = driver.findElement(By.cssSelector("div[id='audits-sections-audit-rules-grid'] table tbody tr td span[class='k-widget k-dropdown k-header ddlkeyrule'] span span[class='k-input']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", keyfld);
			Thread.sleep(1000);			
			log.logLine(Testname, false, "Clicking on Choosing keyfield in audit rules");
			Thread.sleep(2000);
			List<WebElement> vwrpts = driver.findElements(By.cssSelector("div[class='k-animation-container'] div  div ul[class='k-list k-reset'] li[role='option']"));	
			int si=vwrpts.size();			
			for (WebElement lnk:vwrpts) {
				String abc=lnk.getText();
				if (lnk.getText().equalsIgnoreCase("Account1")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);	   	
					Thread.sleep(2000);
					log.logLine(Testname, false, "choosing key field to edit is successful");			
					break;
				}
			}
		} else {
			log.logLine(Testname, true, "choosing key field to edit is failed");
			//throw new Exception("Clicking on Choose Action of audit rules is failed");
		}
	}

	public void Editsubseg(String Testname) throws Exception{

		if (doesElementExist2("div[id='audits-sections-audit-rules-grid'] table tbody tr td+td+td+td+td+td+td span[class='k-widget k-dropdown k-header ddlsubsegmentrule'] span span[class='k-input']", 5)) {
			WebElement subseg = driver.findElement(By.cssSelector("div[id='audits-sections-audit-rules-grid'] table tbody tr td+td+td+td+td+td+td span[class='k-widget k-dropdown k-header ddlsubsegmentrule'] span span[class='k-input']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", subseg);
			Thread.sleep(1000);		
			log.logLine(Testname, false, "Clicking on Choosing sub segment in audit rules");
			Thread.sleep(5000);
			List<WebElement> vwrpts = driver.findElements(By.cssSelector("div[class='k-animation-container'] div  div ul[class='k-list k-reset'] li[role='option']"));	
			for (WebElement lnk:vwrpts) {
				String abc=lnk.getText();
				if (lnk.getText().equalsIgnoreCase("All")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);	   	
					Thread.sleep(5000);
					log.logLine(Testname, false, "choosing sub segment is successful");			
					break;
				}
			}
		} else {
			log.logLine(Testname, true, "choosing sub segment is failed");
			//throw new Exception("Clicking on Choose Action of audit rules is failed");
		}}

	public void Editworkflow(String Testname,String newworkflw) throws Exception{

		if (doesElementExist("html/body/div[1]/div[6]/table/tbody/tr/td[9]/input", 15)) {		
			WebElement workflownme = driver.findElement(By.xpath("html/body/div[1]/div[6]/table/tbody/tr/td[9]/input"));		
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", workflownme);		
			workflownme.clear();
			workflownme.sendKeys(newworkflw);
			log.logLine(Testname, false, "Workflow name has been edited successfully");}
		else {
			log.logLine(Testname, true, "Unable to edit workflow name");
			//	throw new Exception("Workflow name cannot  be entered in the textbox");
		}
		return ;}




	public void Enterinvalidvalue(String Testname) throws Exception{

		if (doesElementExist2("input[id='mAR_txtAuditValue']", 15)) {		
			WebElement workflownme = driver.findElement(By.cssSelector("input[id='mAR_txtAuditValue']"));		
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", workflownme);		
			workflownme.clear();
			workflownme.sendKeys("Invaliddata");}
		else {
			log.logLine(Testname, true, "invalid data cannot  be entered in the textbox");
			throw new Exception("invalid data cannot  be entered in the textbox");
		}
		return ;}

	public void Enterworkflownme(String Testname) throws Exception{

		if (doesElementExist2("input[id='mAR_txtWorkflowName']", 15)) {		
			WebElement workflownme = driver.findElement(By.cssSelector("input[id='mAR_txtWorkflowName']"));		
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", workflownme);		
			workflownme.clear();
			workflownme.sendKeys("Test_workflow");}
		else {
			log.logLine(Testname, true, "Workflow name cannot  be entered in the textbox");
			//	throw new Exception("Workflow name cannot  be entered in the textbox");
		}
		return ;}

	public String EnterKeyValueForRule( String Testname , String OperationType) throws Exception {	
		InputOutputData test = new InputOutputData();		
		test.setInputFile("C:/Pivot_Portal/Test Data/AuditKeys.xls");
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
		int colNum = 1 ;
		String colKeyVal = null;

		switch (OperationType) {
		case "Invalid Data":					
			colKeyVal ="KeyValueInvalid" ;
			break;   

		case "Create1":					
			colKeyVal = "KeyValue1" ;
			break;   

		case "CreateAll":	
			KeyValIncreaseCount = KeyValIncreaseCount+1;
			colKeyVal = "KeyValue"+KeyValIncreaseCount;
			break;   

		case "Maximum":	
			KeyValIncreaseCount = KeyValIncreaseCount+1;
			colKeyVal = "KeyValue"+KeyValIncreaseCount;
			break;   

		case "Edit":	

			KeyValIncreaseCount = KeyValIncreaseCount+1;
			colKeyVal = "KeyValue"+KeyValIncreaseCount;
			break;  

		}	;


		String regExprKeyVal=test.readColumnData(colKeyVal , "AuditRules");
		if (OperationType=="Edit"){
			if (doesElementExist2("div[id='audits-sections-audit-rules-grid'] table tbody tr[class='k-grid-edit-row'] td+td input[class='k-input k-textbox']", 15)) {		
				WebElement KeyValRuleApp = driver.findElement(By.cssSelector("div[id='audits-sections-audit-rules-grid'] table tbody tr[class='k-grid-edit-row'] td+td input[class='k-input k-textbox']"));

				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[id='audits-sections-audit-rules-grid'] table tbody tr[class='k-grid-edit-row'] td+td input[class='k-input k-textbox']")));

				((JavascriptExecutor) driver).executeScript("arguments[0].click()", KeyValRuleApp);		
				KeyValRuleApp.clear();

				if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer")))
					((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + regExprKeyVal +"')", KeyValRuleApp);
				else
					KeyValRuleApp.sendKeys(regExprKeyVal);

				log.logLine(Testname, false, "Key Field Value  has been entered in the textbox "+regExprKeyVal);
			} else {
				log.logLine(Testname, true, "Key Field Value  could NOT be entered in the textbox");
				//	throw new Exception("Key Field Value could NOT be entered in the textbox");
			}
		}else{

			if (doesElementExist2("div input[id='mAR_txtAuditValue']", 15)) {		
				WebElement KeyValRuleApp = driver.findElement(By.cssSelector("div input[id='mAR_txtAuditValue']"));


				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div input[id='mAR_txtAuditValue']")));

				((JavascriptExecutor) driver).executeScript("arguments[0].click()", KeyValRuleApp);		
				KeyValRuleApp.clear();

				if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer")))
					((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + regExprKeyVal +"')", KeyValRuleApp);
				else
					KeyValRuleApp.sendKeys(regExprKeyVal);

				log.logLine(Testname, false, "Key Field Value  has been entered in the textbox "+regExprKeyVal);
			} else {
				log.logLine(Testname, true, "Key Field Value  could NOT be entered in the textbox");
				//	throw new Exception("Key Field Value could NOT be entered in the textbox");
			}
		}
		return value=regExprKeyVal;
	}

	//***********************************Method to Enter Quantity************************************************************************************

	public int Quantity( String Testname, String Operation ) throws Exception {		
		String QuantSheet = "1" ;
		int QuantityAppTextVal = 0 ;
		boolean QuanSelected = false;				
		InputOutputData test = new InputOutputData();		
		test.setInputFile("C:/Pivot_Portal/Test Data/AuditKeys.xls");

		switch (Operation) {
		case "Create" :				
			QuantSheet = test.readColumnData("QuantitySheet", "Quantity");	
			break;
		case "Edit" :			
			QuantSheet = test.readColumnData("QuantityEditSheet", "Quantity");	
			break;
		}


		int intQuantSheet = Integer.parseInt(QuantSheet) ;
		if (!QuanSelected) {			

			if (doesElementExist2("div[id='modal-add-audit-rule-v2'] div div span span span span span[title='Increase value']", 5)) {
				WebElement selQuantAppInc = driver.findElement(By.cssSelector("div[id='modal-add-audit-rule-v2'] div div span span span span span[title='Increase value']"));	
				while(QuantityAppTextVal<intQuantSheet) { 	

					Thread.sleep(1000);
					Actions builder = new Actions(driver);
					builder.moveToElement(selQuantAppInc).click().perform();
					QuantityAppTextVal++;


				}
				log.logLine(Testname, false, "Quantity Value selected for this rule is \""+QuantityAppTextVal+"\"");
			}else {
				log.logLine(Testname, true, "QuantAppIncrease does not exist");
				//throw new Exception("QuantApp does not exist");
			}				
			log.logLine(Testname, false, "Quantity Value" +intQuantSheet+ " has been entered in the textbox");
		} else {
			log.logLine(Testname, true, "Quantity Value" +intQuantSheet+ " could NOT be entered in the textbox");
			throw new Exception("Quantity Value "+intQuantSheet+" could NOT be entered in the textbox");
		}	


		return QuantityAppTextVal ;	
	}

	public boolean Hold(String Testname) throws Exception {	

		if (doesElementExist2("div[id='modal-add-audit-rule-v2'] div div label[for='mAR_chkHold_Yes']", 5)) {	    
			WebElement Hold = driver.findElement(By.cssSelector("div[id='modal-add-audit-rule-v2'] div div label[for='mAR_chkHold_Yes']"));		   
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Hold);	
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicking Hold checkbox is successful");
		} else {
			log.logLine(Testname, true, "Clicking Hold checkbox is failed");			
			//	throw new Exception("Clicking cancel button is failed");
		}


		return true;
	}

	public boolean Subseg(String Testname) throws Exception {	

		if (doesElementExist2("div[id='modal-add-audit-rule-grid'] table tbody tr td+td+td+td+td+td+td+td span span span", 5)) {	    
			WebElement subseg = driver.findElement(By.cssSelector("div[id='modal-add-audit-rule-grid'] table tbody tr td+td+td+td+td+td+td+td span span span"));		   
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", subseg);		   
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicking Subsegment dropdown is successful");
		} else {
			log.logLine(Testname, true, "Clicking Subsegment dropdown is failed");			
			//	throw new Exception("Clicking cancel button is failed");
		}

		if (doesElementExist2("div[class='k-animation-container'] div+div ul li[role='option']", 5)) {	   
			List<WebElement> clntdd = driver.findElements(By.cssSelector("div[class='k-animation-container'] div+div ul li[role='option']"));
			for (WebElement btn:clntdd) {
				if (btn.getText().equals("All")) {
					btn.click();
					//((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Selecting the Subsegment as All from the popup..");
					break;
				}						
			}

		} else {
			log.logLine(Testname, true, "Selecting the Subsegment as All from the popup is failed");
			throw new Exception("Selecting the Subsegment as All from the popup failed");
		}


		return true;
	}

	public boolean EnterBeginDate( String Testname ) throws Exception {    

		if (doesElementExist("html/body/div[1]/div[8]/div[2]/div[2]/div[1]/div[4]/span[2]/span/span/span/span", 5)) {        
			WebElement clkCalendar = driver.findElement(By.xpath("html/body/div[1]/div[8]/div[2]/div[2]/div[1]/div[4]/span[2]/span/span/span/span"));           
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkCalendar);             
			log.logLine(Testname, false, "Clicking begin date calender button is successful");
		} else {
			log.logLine(Testname, true, "Clicking begin date calender button is failed");            
			 throw new Exception("Clicking begin date calender button is failed");
		}
		Thread.sleep(2000);

		if (doesElementExist2("div[class='k-animation-container'] div div div a[class='k-link k-nav-today']", 5)) {        
			WebElement clkCalBeginDate = driver.findElement(By.cssSelector("div[class='k-animation-container'] div div div a[class='k-link k-nav-today']"));           
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkCalBeginDate);             
			log.logLine(Testname, false, "Cliking on today's date button is successful");
		} else {
			log.logLine(Testname, true, "Cliking on today's date is failed");            
			 throw new Exception("Cliking on today's date is failed");
		}

		return true;
	}    

	public boolean EnterEndDate( String Testname ) throws Exception {    
		if (doesElementExist("html/body/div[1]/div[8]/div[2]/div[2]/div[1]/div[4]/span[3]/span/span/span/span", 5)) {        
			WebElement clkCalendar = driver.findElement(By.xpath("html/body/div[1]/div[8]/div[2]/div[2]/div[1]/div[4]/span[3]/span/span/span/span"));           
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkCalendar);             
			log.logLine(Testname, false, "Clicking End date calender button is successful");
		} else {
			log.logLine(Testname, true, "Clicking End date calender button is failed");            
			//    throw new Exception("Clicking End date calender button is faileate d");
		}

		Thread.sleep(3000);
		if (doesElementExist2("tr+tr+tr+tr+tr+tr td+td+td+td+td+td+td a[class='k-link']", 5)) {        
			WebElement clkCalEndDate = driver.findElement(By.cssSelector("tr+tr+tr+tr+tr+tr td+td+td+td+td+td+td a[class='k-link']"));    
			Actions act = new Actions(driver);
			act.click(clkCalEndDate).build().perform();
			act.release(clkCalEndDate);
			Thread.sleep(4000);
			//    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkCalEndDate);             
			log.logLine(Testname, false, "selecting the end date is successful");
		} else {
			log.logLine(Testname, true, "selecting the end date is failed");            
			//    throw new Exception("selecting the end date is failed");
		}

		return true;
	}

	public boolean  AcceptAlert(String Testname) throws Exception {	
		Alert Aler = driver.switchTo().alert();
		Aler.accept();
		return true;
	}

	public boolean  ClickCancel(String Testname)throws Exception {	
		if (doesElementExist2("div tbody tr td button[id='modal-add-audit-rule-btn-cancel-v2']", 5)) {	    
			WebElement clkCancel = driver.findElement(By.cssSelector("div tbody tr td button[id='modal-add-audit-rule-btn-cancel-v2']"));		   
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkCancel);		     
			log.logLine(Testname, false, "Clicking cancel button is successful");
		} else {
			log.logLine(Testname, true, "Clicking cancel button is failed");			
			//	throw new Exception("Clicking cancel button is failed");
		}

		return true;
	}

	public boolean  ifAlertClkOK(String Testname)throws Exception {	


		if (doesElementExist2("div+div[id='extAlertDialog'] div div+div[style='display:inline-block;margin-left:45px;']", 5)) {	
			WebElement AlertN = driver.findElement(By.cssSelector("div+div[id='extAlertDialog'] div div+div[style='display:inline-block;margin-left:45px;']"));
			String AlertName = AlertN.getText();
			log.logLine(Testname, false, "Alert displayed is.....\""+AlertName+"   \" ");

			if (doesElementExist2("button[class='k-button']", 5)) {	
				WebElement SavRul = driver.findElement(By.cssSelector("button[class='k-button']"));
				Thread.sleep(5000);

				if (SavRul.isDisplayed()){
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", SavRul);
				}

				if (doesElementExist2("div+div[id='extAlertDialog']+div div button[style='margin-right:5px; width:100px;']", 5)) {	
					WebElement AlertButton = driver.findElement(By.cssSelector("div+div[id='extAlertDialog']+div div button[style='margin-right:5px; width:100px;']"));
					Thread.sleep(5000);

					if (AlertButton.isDisplayed()){
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", AlertButton);
					}
				}	
			}else{	
				log.logLine(Testname, true, "Clicked on Save button after adding the Audit Rule is failed");
				//throw new Exception("Clicked on Save button after adding the Audit Rule is failed");
			}
		}
		log.logLine(Testname, false, "Clicked on Save button after adding the Audit Rule ");		
		return true;
	}

	public boolean AlertBadData(String Testname) throws Exception{
		if (doesElementExist2(properties.getProperty("alertMaxSubSeg"), 5)) {
			WebElement SubMaxAlert = driver.findElement(By.cssSelector(properties.getProperty("alertMaxSubSeg")));
			String kAlertText = SubMaxAlert.getText();
			if (kAlertText.equals("You may not add another selection - doing so would exceed allowance")){
				log.logLine(Testname, false, "Alert for adding greater than the Max audit rule not allowed pop is displayed ");
			}
			else {	
				log.logLine(Testname, true, "Alert for adding greater than the Max audit rule not allowed pop is not displayed ");
			} 
			log.logLine(Testname, false, "Validation Pass - Alert appears for adding greater than the Max audit rule & displays the message : " +kAlertText);
		} else {
			log.logLine(Testname, true, "Validation Fail - Alert does not appears for adding greater than the Max audit rule");
			throw new Exception("Validation Fail - Alert does not appears for adding greater than the Max audit rule ");
		}
		Thread.sleep(2000);

		if (doesElementExist2(properties.getProperty("OkButton"), 5)) {
			WebElement okAlert = driver.findElement(By.cssSelector(properties.getProperty("OkButton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okAlert);
			log.logLine(Testname, false, "Clicked OK button on Max audit rules not allowed pop up Alert  " );
		} else {
			log.logLine(Testname, true, "Max audit rules Alert is not displayed hence did not click on OK button");
			throw new Exception("Max audit rules Alert is not displayed hence did not click on OK button");
		}
		ClkExtraCancelButton(Testname);
		Thread.sleep(10000);
		ClkAddAuditRule(Testname);

		return true;
	}
	public boolean AdvSearchBtn(String Testname) throws Exception{
		if (doesElementExist2(properties.getProperty("AdvanceSearch"), 5)) {	    
			WebElement clkadv = driver.findElement(By.cssSelector(properties.getProperty("AdvanceSearch")));		   
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);		     
			log.logLine(Testname, false, "Click on advancve search under Audit Rules");
		} else {
			log.logLine(Testname, true, "Click on advancve search under Audit Rules is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on advancve search under Audit Rules is failed");
		}
		return true;
	}

	public boolean AdvanceSearchForKeyValue(String Testname, String value) throws Exception {

		AdvanceSearchClearBtn(Testname);

		Thread.sleep(2000);

		Actions action = new Actions(driver);
		if (doesElementExist2(properties.getProperty("SelectSearch"), 5)) {
			WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("SelectSearch")));
			log.logLine(Testname, false, "Clicking on search criteria to select the option key field from list");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
			Thread.sleep(2000);

			if (doesElementExist2("div[class='k-animation-container'] div[id='ddlSearchRule-list'] ul[id='ddlSearchRule_listbox'] li[role='option']", 5)) {
				List<WebElement> vwrpts = driver.findElements(By.cssSelector("div[class='k-animation-container'] div[id='ddlSearchRule-list'] ul[id='ddlSearchRule_listbox'] li[role='option']"));	
				for (WebElement lnk:vwrpts) {
					if (lnk.getText().equals("Key Value")) {
						Thread.sleep(2000);
						action.click(lnk).perform();
						Thread.sleep(1000);
						PleasewaitDisappear();		
						log.logLine(Testname, false, "Selecting the option kay value from list ");			
						break;
					}
				}
			}
		} else {
			log.logLine(Testname, true, "Clicking on search criteria to select the option key field from list is failed");
			//	throw new Exception("Clicking on search criteria to select the option key field from list is failed");
		}

		if (doesElementExist2(properties.getProperty("SubSegmentTextBox"), 5)) {	    
			WebElement SubSegTextBox = driver.findElement(By.cssSelector(properties.getProperty("SubSegmentTextBox")));
			Thread.sleep(2000);
			SubSegTextBox.clear();
			SubSegTextBox.click();

			if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer")))
				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value +"')", SubSegTextBox);
			else
				Thread.sleep(2000);
			SubSegTextBox.sendKeys(value);

			Thread.sleep(3000);
			log.logLine(Testname, false, "Entering the key value in text box as "+value);
		} else {
			log.logLine(Testname, true, "Entering the key value in text box is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Entering the key value in text box is failed");
		}

		if (doesElementExist2(properties.getProperty("SearchBtn"), 5)) {	    
			WebElement clkadv = driver.findElement(By.cssSelector(properties.getProperty("SearchBtn")));		   
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Click on search button under advance search of  Audit Rules ");
		} else {
			log.logLine(Testname, true, "Click on search button under advance search of Audit Rules is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Click on search button under advance search of Audit Rules is failed");
		}

		if (doesElementExist2("div[id='audits-sections-audit-rules-grid'] table[role='grid'] tbody[role='rowgroup'] tr[role='row'] td+td[role='gridcell']", 5)) {
			WebElement KeyValExists = driver.findElement(By.cssSelector("div[id='audits-sections-audit-rules-grid'] table[role='grid'] tbody[role='rowgroup'] tr[role='row'] td+td[role='gridcell']"));

			log.logLine(Testname, false, "\"Advanced search - Successfully displayed the audits for entered text "+KeyValExists.getText()+"in srchType field\"");		
			log.logLine(Testname, true, "There is a bug found- Invalid Key value\" "+value+"\" has been being accepted while Rule creation");
		} else {
			log.logLine(Testname, false, "Advanced search - No audits displayed the audits for entered text in srchType field");
		}	
		return true;
	}
	
	
	public boolean Parametervalidation(String Testname) throws Exception {
	    
        if (doesElementExist2(properties.getProperty("AdvanceSearch"), 5)) {        
            WebElement clkadv = driver.findElement(By.cssSelector(properties.getProperty("AdvanceSearch")));           
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);             
            log.logLine(Testname, false, "Click on advancve search under Audit Rules");
        } else {
            log.logLine(Testname, true, "Click on advancve search under Audit Rules is failed");
            driver.switchTo().defaultContent();
            //throw new Exception("Click on advancve search under Audit Rules is failed");
        }
        AdvanceSearchClearBtn(Testname);
        
        Thread.sleep(2000);
        
        Actions action = new Actions(driver);
        if (doesElementExist2(properties.getProperty("SelectSearch"), 5)) {
            WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("SelectSearch")));
            log.logLine(Testname, false, "Clicking on search criteria to select the option key field from list");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
            Thread.sleep(2000);
            
            if (doesElementExist2("div[class='k-animation-container'] div[id='ddlSearchRule-list'] ul[id='ddlSearchRule_listbox'] li[role='option']", 5)) {
                List<WebElement> vwrpts = driver.findElements(By.cssSelector("div[class='k-animation-container'] div[id='ddlSearchRule-list'] ul[id='ddlSearchRule_listbox'] li[role='option']"));    
                for (WebElement lnk:vwrpts) {
                    if (lnk.getText().equals("Key Value")) {
                        Thread.sleep(2000);
                        action.click(lnk).perform();
                        Thread.sleep(1000);
                        PleasewaitDisappear();        
                        log.logLine(Testname, false, "Selecting the option key value from list ");            
                        break;
                    }
                }
            }
        } else {
            log.logLine(Testname, true, "Clicking on search criteria to select the option key field from list is failed");
            //    throw new Exception("Clicking on search criteria to select the option key field from list is failed");
        }
        
        if (doesElementExist2(properties.getProperty("Addbtn"), 5)) {        
            WebElement clkadv = driver.findElement(By.cssSelector(properties.getProperty("Addbtn")));           
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);             
            log.logLine(Testname, false, "Click on add button");
        } else {
            log.logLine(Testname, true, "Click on add button is failed");
            
        }    
        Thread.sleep(5000);
        if (doesElementExist(properties.getProperty("SelectSearch2"), 5)) {
            WebElement choseacts = driver.findElement(By.xpath(properties.getProperty("SelectSearch2")));
            log.logLine(Testname, false, "Clicking on search criteria to select the option key field from list");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
            Thread.sleep(2000);
            
            
            if (doesElementExist2("div[class='k-animation-container'] div[id='ddlSearchRule-list'] div ul[id='ddlSearchRule_listbox'] li[role='option']", 5)) {
                List<WebElement> vwrpts = driver.findElements(By.cssSelector("div[class='k-animation-container'] div[id='ddlSearchRule-list'] div ul[id='ddlSearchRule_listbox'] li[role='option']"));    
                for (WebElement lnk:vwrpts) {
                    if (lnk.getText().equals("Key Value")) {
                        Thread.sleep(2000);
                        action.click(lnk).perform();
                        Thread.sleep(1000);
                        PleasewaitDisappear();        
                        log.logLine(Testname, false, "Selecting the option key value from list ");            
                        break;
                    }
                }
            }
        } else {
            log.logLine(Testname, true, "Clicking on search criteria to select the option key field from list is failed");
            //    throw new Exception("Clicking on search criteria to select the option key field from list is failed");
        }
        
        
        if (doesElementExist2(properties.getProperty("Alertmsg"), 5)) {        
            String msg = driver.findElement(By.cssSelector(properties.getProperty("Alertmsg"))).getText();           
            log.logLine(Testname, false, "Reading the pop up message as "+ msg);
        } else {
            log.logLine(Testname, true, "Reading the pop up message is failed");
            
        }
    
        Thread.sleep(5000);
        if (doesElementExist2(properties.getProperty("Alertbtn"), 5)) {        
            WebElement clkadv = driver.findElement(By.cssSelector(properties.getProperty("Alertbtn")));           
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);
            Thread.sleep(5000);
            log.logLine(Testname, false, "Click ok button for pop up message");
        } else {
            log.logLine(Testname, true, "Click ok button for pop up message is failed");
            
        }
                
        AdvanceSearchClearBtn(Testname);
        
        if (doesElementExist2(properties.getProperty("Advcnclbtn"), 5)) {	    
			WebElement clkadv = driver.findElement(By.cssSelector(properties.getProperty("Advcnclbtn")));		   
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);		     
			log.logLine(Testname, false, "Click on Close button in advancve search under Audit Rules");
		} else {
			log.logLine(Testname, true, "Click on Close button in advancve search under Audit Rules is failed");
			driver.switchTo().defaultContent();
		//	throw new Exception("Click on clear button in advancve search under Audit Rules is failed");
		}
		
        Thread.sleep(2000);
        
        
        return true;
    }
	
	

	public boolean AdvanceSearchClearBtn(String Testname) throws Exception {
		if (doesElementExist2(properties.getProperty("ClearBtn"), 5)) {	    
			WebElement clkadv = driver.findElement(By.cssSelector(properties.getProperty("ClearBtn")));		   
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);		     
			log.logLine(Testname, false, "Click on clear button in advancve search under Audit Rules");
		} else {
			log.logLine(Testname, true, "Click on clear button in advancve search under Audit Rules is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on clear button in advancve search under Audit Rules is failed");

		}return true;}

}


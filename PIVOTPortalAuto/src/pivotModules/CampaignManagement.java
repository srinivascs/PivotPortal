package pivotModules;

import java.io.IOException;
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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CampaignManagement extends Page {

	int paperID = (int) Math.round(Math.random() * (9999 - 1000 + 1) + 1000);
	public String AccNo = Integer.toString(paperID);

	int paperID1 = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);
	String RandNo3 = Integer.toString(paperID1);


	int paperID2 = (int) Math.round(Math.random() * (99999 - 100 - 1) + 100);
	String RandNo5 = Integer.toString(paperID2);


	public String EmailtypTxt, rplyTxt;
	public String TemplateName;
	public String TempltDecsp;
	public String EditTemplateName;
	public String EditTempltDecsp;
	public String notemplts;
	public String spacialChar;
	public String name;
	public String ClntName;
	public String AppId;
	public String useradmnid;
	public String userappid;
	public String AppName;
	String secondWinHandle = null;
	long GmailstartTime;
	public boolean CliSelected = false;
	String currentStatus;
	String campaignRow;
	String campaignOtherRow;
	String otherRowCurrentStatus1;
	String currentStatus1;
	String otherRowCurrentStatus;
	public String PClntName=null;
	public String PAppName=null;
	public String DClntName=null;
	public String DAppName=null;
	public String RClntName=null;
	public String RAppName=null;
	String campnme1=" ";
	String campnme2=" ";

	public String InsImgUrl, AltImageUrlText; 
	public String URL, LinkID, LnkText, LnkToolTp;

	public CampaignManagement(WebDriver driver, Log log)
			throws InvalidFormatException, IOException {
		super(driver, log);
	}

	@Override
	protected void load() {
	}

	@Override
	protected void isLoaded() throws Error {
	}

	String firstWinHandle = null;
	WebDriverWait wait = new WebDriverWait(driver, 20);

	//...................................................................................................

	public boolean eDeliver_ClientAppSelvalidatn(String AccNo, String Testname) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile")); 		
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
		Thread.sleep(1000);
		driver.switchTo().defaultContent();



		//............................................First validation starts.........................................................................

		proofViewer(Testname);
		ClientApp_cancel(Testname,"proofpage");
		Thread.sleep(2000);
		ClickeDeliverTab(Testname);
		Thread.sleep(3000);
		if (doesElementExist2(properties.getProperty("selClint1"), 5)) {	    
			log.logLine(Testname, false, "Validating appearance of client app popup in eDelivery is successful");
		} else {
			log.logLine(Testname, true, "Validating appearance of client app popup in eDelivery is failed");
		}
		Thread.sleep(2000);
		ClientApp_cancel(Testname,"eDeliverypage");

		//.............................................................First validation ends.............................................................
		//.........................................................second validation starts.............................................................
		log.logLine(Testname, false, "Validating appearance of client app popup in eDelivery (navigating from proof(cancel) to edeliver)");
		Thread.sleep(1000);
		proofViewer(Testname);
		Thread.sleep(2000);
		ClientApp_cancel(Testname,"proofpage");
		Thread.sleep(2000);
		ClickeDeliverTab(Testname);
		Thread.sleep(2000);
		ClientApp_cancel(Testname,"eDeliverypage");
		Thread.sleep(2000);
		String ClntName = test.readColumnData("ClientName", sheetname);
		String apName = test.readColumnData("appName", sheetname);
		mainclientname_appselection(Testname,ClntName,apName);
		Thread.sleep(3000);

		//.........................................................second validation ends...............................................................
		//.........................................................Third validation starts.............................................................
		log.logLine(Testname, false, "Validating appearance of client app popup in eDelivery (navigating from proof(selecting applicable c/a) to edeliver)");
		Thread.sleep(1000);
		proofViewer(Testname);
		Thread.sleep(2000);
		PClntName = test.readColumnData("PROOFClientnme", sheetname);
		PAppName = test.readColumnData("Proofappnme", sheetname);
		Thread.sleep(2000);
		ClientAppSel(AccNo,Testname,PClntName,PAppName);
		Thread.sleep(2000);
		ClickeDeliverTab(Testname);
		Thread.sleep(2000);
		DClntName = test.readColumnData("eDeliverClientnme", sheetname);
		DAppName = test.readColumnData("eDeliverappnme", sheetname);
		Thread.sleep(2000);
		ClientAppSel(AccNo,Testname,DClntName,DAppName);
		Thread.sleep(6000);
		mainclientname_appselection(Testname,DClntName,DAppName);


		//.........................................................Third validation ends...............................................................
		//.........................................................Fourth validation starts...............................................................
		log.logLine(Testname, false, "Validating appearance of client app popup in Reports (navigating from Proof(cancelling the action) , edeliver(selecting applicable c/a) to Reports )");
		Thread.sleep(1000);
		proofViewer(Testname);
		Thread.sleep(3000);
		ClientApp_cancel(Testname,"proofpage");
		Thread.sleep(2000);
		ClickeDeliverTab(Testname);
		Thread.sleep(3000);
		ClientAppSel(AccNo,Testname,DClntName,DAppName);
		Thread.sleep(2000);
		Reportslink(Testname);
		Thread.sleep(2000);
		RClntName = test.readColumnData("reportClientnme", sheetname);
		RAppName = test.readColumnData("reportappnme", sheetname);
		ClientAppSel(AccNo,Testname,RClntName,RAppName);
		Thread.sleep(4000);
		mainclientname_appselection(Testname,RClntName,RAppName);
		Thread.sleep(2000);	
		return true;
	}

	//.........................................................Fourth validation ends.............................................................
	private void Reportslink(String Testname)throws Exception {
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
		}}


	public void popup(String Testname) throws Exception  {

		if (doesElementExist2(properties.getProperty("popup"), 5)) {	    
			WebElement proofsmnu = driver.findElement(By.cssSelector(properties.getProperty("Proofs")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", proofsmnu);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Navigation to Proofs page successful");
		} else {
			log.logLine(Testname, true, "Clicking on Proofs menu is failed");
			throw new Exception("Clicking on Proofs menu is failed");
		}
		return ;}



	public void ClickeDeliverTab(String Testname) throws Exception {
		if (doesElementExist2(properties.getProperty("EdeliveryTab"), 5)) {
			WebElement eDelive = driver.findElement(By.cssSelector(properties.getProperty("EdeliveryTab")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
			log.logLine(Testname, false, "Click on e-Delivery Tab is Successful");
		} else {
			log.logLine(Testname, true, "Click on e-Delivery Tab is failed");;
			throw new Exception("Click on e-Delivery Tab is failed");
		}
	}



	public void proofViewer(String Testname) throws Exception  {

		if (doesElementExist2(properties.getProperty("Proofs"), 5)) {	    
			WebElement proofsmnu = driver.findElement(By.cssSelector(properties.getProperty("Proofs")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", proofsmnu);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Navigation to Proofs page is successful");
		} else {
			log.logLine(Testname, true, "Clicking on Proofs menu is failed");
			throw new Exception("Clicking on Proofs menu is failed");
		}
		return ;}

	private void mainclientname_appselection(String Testname, String ClntName,String appName)throws Exception {
		if (doesElementExist2(properties.getProperty("mainclinme"), 5))
		{
			String mnclintnme = driver.findElement(By.xpath(properties.getProperty("mainclientname"))).getText();
			String mainappname = driver.findElement(By.xpath(properties.getProperty("mainappname"))).getText();

			if(mnclintnme.equalsIgnoreCase(ClntName) && mainappname.equalsIgnoreCase(appName))
			{  log.logLine(Testname, false, "Validating the text of client app selection list of main page is successful text selected in mainpage is "+mnclintnme+"text entered from popup is"+ClntName);
			log.logLine(Testname, false, "Appname in mainpage is "+mainappname+"app name selected from popup is "+mainappname);
			}
			else
			{  log.logLine(Testname, true , "Validating the text of client app selection list of main page is  failed text in main page is "+mnclintnme+"text entered from popup is"+ClntName);
			throw new Exception("Validating the text of client app selection list of main page is failed");
			}		
		}}

	public boolean ClientAppSel(String AccNo, String Testname,String ClntName,String AppName) throws Exception {
		Thread.sleep(1000);
		boolean CliSelected = false;

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
						PleasewaitDisappear();
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
							PleasewaitDisappear();
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
					if (lnk.getText().equals(AppName)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						PleasewaitDisappear();
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
							PleasewaitDisappear();
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
			log.logLine(Testname, false, "Clicking on OK button to view the Proofs");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the Proofs is failed");
			throw new Exception("Clicking on OK button to view the Proofs is failed");
		}

		return true;
	}

	public void ClientApp_cancel(String Testname,String page) throws Exception {
		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup of "+page);
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup of "+page+" is failed");
			//throw new Exception("Clicking on Cancel button in Client/App popup of "+page+" is failed");
		}
		return ;
	}


	public boolean NewCampaignManager(String RandNo3, String RandNo5,String Testname) throws Exception {

		InputOutputData test = new InputOutputData();
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object() {}.getClass().getEnclosingMethod().getName();

		ClientAndAppSel(Testname);
		String name1=" ";

		if (doesElementExist2(properties.getProperty("CampMangerCss"), 5)) {
			WebElement campmgr = driver.findElement(By.cssSelector(properties.getProperty("CampMangerCss")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",campmgr);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false,"Clicking on Campaign Manager link is successful");
		} else {
			log.logLine(Testname, true,"Clicking on Campaign Manager link is not successful");
			throw new Exception("Clicking on Campaign Manager link is not successful");
		}


		Thread.sleep(1000);
		driver.switchTo().frame("iframeContainer");
		PleasewaitDisappear();

		Thread.sleep(10000);

		RecipientCreation(Testname);

		NewCmpaignBtn(Testname);

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("cmpainCncl"), 5)) {
			WebElement recibtn = driver.findElement(By.cssSelector(properties.getProperty("cmpainCncl")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",recibtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false,"Clicking on Cancel button in New Campaign window is successful");
		} else {
			log.logLine(Testname, true,"Clicking on Cancel button in New Campaign window is not successful");
			throw new Exception("Clicking on Cancel button in New Campaign window is not successful");
		}

		NewCmpaignBtn(Testname);

		if (doesElementExist2(properties.getProperty("cmpaignNme"), 5)) {
			WebElement uploadfile = driver.findElement(By.cssSelector(properties.getProperty("cmpaignNme")));
			Thread.sleep(1000);
			name1="Auto" + RandNo3;
			uploadfile.sendKeys(name1);
			log.logLine(Testname, false,"Entering the campaign name "+name1);
		} else {
			log.logLine(Testname, true,"Entering the campaign name  is failed");
			throw new Exception("Entering the campaign name  is failed");
		}

		String ReciName = test.readColumnData("RecipientName", sheetname);


		if (doesElementExist2(properties.getProperty("cmpainReciList"), 5)) {
			WebElement reciSel = driver.findElement(By.cssSelector(properties.getProperty("cmpainReciList")));
			reciSel.click();
			Thread.sleep(1000);
			if (doesElementExist2(properties.getProperty("ReciList"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ReciList")));
				for (WebElement lnk : selopts) {
					if (lnk.getText().contains("CM_Recipient_AutoTest")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false,"Entering Recipient List name CM_Recipient_AutoTest in the combo field is successful");
						break;
					}
				}

			}

		} else {
			log.logLine(Testname, true,"Entering Recipient List name in the combo field is failed");
			throw new Exception("Entering Recipient List name in the combo field is not successful");
		}

		Thread.sleep(1000);
		if (doesElementExist2(properties.getProperty("cmpainNotes"), 5)) {
			WebElement reciSel = driver.findElement(By.cssSelector(properties.getProperty("cmpainNotes")));
			reciSel.clear();
			reciSel.sendKeys("This is automation regression testing.");
		} else {
			log.logLine(Testname, true,"Entering the campaignnotes is not successful");
			throw new Exception("Entering the campaignnotes is not successful");
		}

		Thread.sleep(1000);
		String TempName = test.readColumnData("TemplateName", sheetname);

		if (doesElementExist2(properties.getProperty("TempCombo"), 5)) {
			WebElement reciSel = driver.findElement(By.cssSelector(properties.getProperty("TempCombo")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",reciSel);	
			if (doesElementExist2(properties.getProperty("TempList"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("TempList")));
				for (WebElement lnk : selopts) {
					if (lnk.getText().contains(TempName)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false,"Selecting the tempname " + TempName+ " from the popup..");				
						break;
					}
				}

			} else {
				log.logLine(Testname, true,"Template Name options are not displayed");
				throw new Exception("Template Name options are not displayed");
			}
		}

		CampaignManagerFields(Testname);



		String CmpRow = CmpaignValidation(Testname, RandNo3);


		log.logLine(Testname, false,"Edit action starts");
		EditCampaignAction(Testname, CmpRow);
		Thread.sleep(2000);
		log.logLine(Testname, false,"Hold action starts");
		HoldCampaignAction(Testname, CmpRow,name1);
		Thread.sleep(2000);
		log.logLine(Testname, false,"Release action starts");
		ReleaseCampaignAction(Testname, CmpRow);
		Thread.sleep(2000);
		log.logLine(Testname, false,"view action starts");
		ViewCampaignAction(Testname, CmpRow);
		Thread.sleep(2000);
		log.logLine(Testname, false,"cancel action starts");
		CancelCampaignAction(Testname, CmpRow);
		Thread.sleep(2000);

		Thread.sleep(2000);
		log.logLine(Testname, false,"' DupliWithReciCagnAction' action starts");
		String Var1 = DuplicateWithRecipientCampaignAction(Testname, CmpRow);
		String CmpRow1 = CmpaignValidation(Testname, Var1);
		log.logLine(Testname, false,"delete action starts");
		DeleteCampaignAction(Testname, CmpRow1,"Auto" + Var1);
		CmpaigndeleteValidation(Testname,"Auto" + Var1);

		Thread.sleep(2000);
		log.logLine(Testname, false,"' DupliWithOutReciCagnAction' action starts");
		String Var2 = DupliWithOutReciCagnAction(Testname, CmpRow);
		Thread.sleep(2000);
		String CmpRow2 = CmpaignValidation(Testname, Var2);
		Thread.sleep(2000);
		DeleteCampaignAction(Testname, CmpRow2,"Auto" + Var2);
		CmpaigndeleteValidation(Testname,"Auto" + Var2);

		DeleteCampaignAction(Testname, CmpRow,name1);
		CmpaigndeleteValidation(Testname,name1);

		return true;
	}

	public void CmpaigndeleteValidation(String Testname, String campnme)throws Exception {
		String[] Sort1 = new String[50];
		String row = "tr";
		List<WebElement> DataCnt = driver.findElements(By.xpath(".//*[@id='management-campaigns-grid']/table/tbody/tr"));
		String data="yes";

		if (doesElementExist2(properties.getProperty("AccountNumber"), 5)) {
			for (int i = 0; i < DataCnt.size(); i++) {
				Sort1[i] = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ row + " td+td+td[role='gridcell']")).getText();
				if (Sort1[i].equals(campnme)) {
					log.logLine(Testname, true,"campaign name in grid "+Sort1[i]+" and newly created campaign "+campnme+" matches, hence Campaign Manager is still not deleted from the grid ");
					data="No";
					break;
				} 
				row = row + "+tr";			
			}
			if(data.equalsIgnoreCase("yes")){
				log.logLine(Testname,false,"The campaign "+campnme+" has been deleted from the  grid");
			}

		}
		return ;
	}
	private String  bulkHoldRealseExcution(String testname,InputOutputData inputOutputData, String sheetname, String AccNo)throws Exception {
		String campnme=" ";
		NewCmpaignBtn(testname);
		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("cmpainCncl"), 5)) {
			WebElement recibtn = driver.findElement(By.cssSelector(properties.getProperty("cmpainCncl")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",recibtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(testname, false,"Clicking on Cancel button in New Campaign window is successful");
		} else {
			log.logLine(testname, true,"Clicking on Cancel button in New Campaign window is not successful");
			throw new Exception("Clicking on Cancel button in New Campaign window is not successful");
		}

		NewCmpaignBtn(testname);

		if (doesElementExist2(properties.getProperty("cmpaignNme"), 5)) {
			WebElement uploadfile = driver.findElement(By.cssSelector(properties.getProperty("cmpaignNme")));
			Thread.sleep(1000);
			campnme="Auto" + AccNo;
			uploadfile.sendKeys("Auto" + AccNo);
			log.logLine(testname, false,"Entering the campaigname AutoCmpain" + AccNo+" to create a new campaign");
		} else {
			log.logLine(testname, true,"Entering the campaigname AutoCmpain" + AccNo+" to create a new campaign failed");
			throw new Exception("Entering the campaigname AutoCmpain" + AccNo+" to create a new campaign failed");
		}

		String reciName = inputOutputData.readColumnData("RecipientName",sheetname);

		if (doesElementExist2(properties.getProperty("cmpainReciList"), 5)) {
			WebElement reciSel = driver.findElement(By.cssSelector(properties.getProperty("cmpainReciList")));
			reciSel.click();
			Thread.sleep(1000);
			if (doesElementExist2(properties.getProperty("ReciList"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ReciList")));
				for (WebElement lnk : selopts) {
					if (lnk.getText().contains("CM_Recipient_AutoTest")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						log.logLine(testname, false,"Entering Recipient List name "+lnk.getText()+"in the combo field is successful");
						Thread.sleep(1000);
						break;
					}
				}

			}			
		} else {
			log.logLine(testname, true,"Entering Recipient List name in the combo field is not successful");
			throw new Exception("Entering Recipient List name in the combo field is not successful");
		}

		Thread.sleep(1000);
		if (doesElementExist2(properties.getProperty("cmpainNotes"), 5)) {
			WebElement reciSel = driver.findElement(By.cssSelector(properties.getProperty("cmpainNotes")));
			reciSel.clear();
			reciSel.sendKeys("This is automation regression testing.");
			log.logLine(testname, false,"Entering combo notes 'This is automation regression testing' is successful");
		} else {
			log.logLine(testname, true,"Entering combo notes 'This is automation regression testing' is not successful");
			throw new Exception("Entering combo notes 'This is automation regression testing' is not successful");
		}

		Thread.sleep(1000);
		String TempName = inputOutputData.readColumnData("TemplateName",sheetname);

		if (doesElementExist2(properties.getProperty("TempCombo"), 5)) {
			WebElement reciSel = driver.findElement(By.cssSelector(properties.getProperty("TempCombo")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",reciSel);
			if (doesElementExist2(properties.getProperty("TempList"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("TempList")));
				for (WebElement lnk : selopts) {
					if (lnk.getText().contains(TempName)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(testname, false,"Selecting the template name " + TempName+ " from the popup..");
						CliSelected = true;
						break;
					}
				}

			} else {
				log.logLine(testname, true,"Template Name options are not displayed");
				throw new Exception("Template Name options are not displayed");
			}
		}

		CampaignManagerFields(testname);
		return campnme;
	}

	public boolean ManageRecipientBtn(String Testname) throws Exception {
		if (doesElementExist(properties.getProperty("recipentsbtn"), 5)) {
			WebElement recibtn = driver.findElement(By.xpath(properties.getProperty("recipentsbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",recibtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false,"Clicking on Manage Recipients button is successful");
		} else {
			log.logLine(Testname, true,"Clicking on Manage Recipients button is not successful");
			throw new Exception("Clicking on Manage Recipients button is not successful");
		}
		return true;
	}

	public boolean NewCmpaignBtn(String Testname) throws Exception {
		if (doesElementExist(properties.getProperty("newCampaign"), 5)) {
			WebElement recibtn = driver.findElement(By.xpath(properties.getProperty("newCampaign")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",recibtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false,"Clicking on New Campaign button is successful");
		} else {
			log.logLine(Testname, true,"Clicking on New Campaign button is not successful");
			throw new Exception("Clicking on New Campaign button is not successful");
		}
		return true;
	}

	public String CmpaignValidation(String Testname, String Account)throws Exception {
		String[] Sort1 = new String[50];
		String row = "tr";
		List<WebElement> DataCnt = driver.findElements(By.xpath(".//*[@id='management-campaigns-grid']/table/tbody/tr"));
		String data="yes";
		String newcamp="Auto" + Account;
		if (doesElementExist2(properties.getProperty("AccountNumber"), 5)) {
			for (int i = 0; i < DataCnt.size(); i++) {
				Sort1[i] = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ row + " td+td+td[role='gridcell']")).getText();
				if (Sort1[i].equals(newcamp)) {
					log.logLine(Testname, false,"campaign name in grid "+Sort1[i]+" and newly created campaign "+newcamp+" matches, hence Campaign Manager is added sucessfully ");
					data="No";
					break;
				} 
				row = row + "+tr";			
			}
			if(data.equalsIgnoreCase("yes")){
				log.logLine(Testname,true,"The new campaign added "+newcamp+" doesnot exist in the grid");
			}

		}
		return row;
	}

	public boolean RecipientCreation(String Testname) throws Exception {

		InputOutputData test = new InputOutputData();
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object() {
		}.getClass().getEnclosingMethod().getName();

		ManageRecipientBtn(Testname);

		if (doesElementExist(properties.getProperty("ManageRecipientCancel"), 5)) {
			WebElement recicls = driver.findElement(By.xpath(properties.getProperty("ManageRecipientCancel")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",recicls);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false,"Clicking on Cancel button in Manage Recipient List Window is successful");
		} else {
			log.logLine(Testname, true,"Clicking on Cancel button in Manage Recipient List Window is not successful");
			throw new Exception("Clicking on Cancel button in Manage Recipient List Window is not successful");
		}

		ManageRecipientBtn(Testname);

		if (doesElementExist(properties.getProperty("ManageRecipientClose"), 5)) {
			WebElement recicls = driver.findElement(By.xpath(properties.getProperty("ManageRecipientClose")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",recicls);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false,"Clicking on Close button in Manage Recipient List Window is successful");
		} else {
			log.logLine(Testname, true,"Clicking on Close button in Manage Recipient List Window is not successful");
			throw new Exception("Clicking on Close button in Manage Recipient List Window is not successful");
		}

		ManageRecipientBtn(Testname);

		if (doesElementExist(properties.getProperty("uploadBtn"), 5)) {
			WebElement recibtn = driver.findElement(By.xpath(properties.getProperty("uploadBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",recibtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false,"Clicking on upload new button is successful");
		} else {
			log.logLine(Testname, true,"Clicking on upload new button is not successful");
			throw new Exception("Clicking on upload new button is not successful");
		}


		if (doesElementExist2(properties.getProperty("uloadListBtn"), 5)) {
			WebElement recibtn = driver.findElement(By.cssSelector(properties.getProperty("uloadListBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",recibtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false,"Clicking on uloadList button is successful");
		} else {
			log.logLine(Testname, true,"Clicking on uloadList button is not successful");
			throw new Exception("Clicking on uloadList button is not successful");
		}

		if (doesElementExist(properties.getProperty("uploadBtn"), 5)) {
			WebElement recibtn = driver.findElement(By.xpath(properties.getProperty("uploadBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",recibtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false,"Clicking on upload button is successful");
		} else {
			log.logLine(Testname, true,"Clicking on upload button is not successful");
			throw new Exception("Clicking on upload button is not successful");
		}

		WebElement Browse = driver.findElement(By.xpath("html/body/div[3]/div[5]/div[2]/div[2]/div/input[1]"));
		Thread.sleep(3000);
		Browse.sendKeys("C:\\Pivot_Portal\\Test Data\\CM_Recipient_AutoTest.txt");

		Thread.sleep(2000);

		if (doesElementExist2(properties.getProperty("uloadListBtn"), 5)) {
			WebElement recibtn = driver.findElement(By.cssSelector(properties.getProperty("uloadListBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",recibtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			Thread.sleep(1000);
			log.logLine(Testname, false,"Clicking on uloadListBtn is successful");
		} else {
			log.logLine(Testname, true,"Clicking on uloadListBtn is not successful");
			throw new Exception("Clicking on uloadListBtn is not successful");
		}

		if (doesElementExist(properties.getProperty("ManageRecipientCancel"), 5)) {
			WebElement recibtn = driver.findElement(By.xpath(properties.getProperty("ManageRecipientCancel")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",recibtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false,"Clicking on Manage Recipient cancel button is successful");
		} else {
			log.logLine(Testname, true,"Clicking on Manage Recipients cancel is not successful");
			throw new Exception("Clicking on Manage Recipients cancel is not successful");
		}
		return true;
	}

	public String DuplicateWithRecipientCampaignAction(String Testname,String ActRow) throws Exception {
		String abc="true";
		if (doesElementExist2(properties.getProperty("AccountNumber"), 5)) {
			WebElement cpnCombo = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ ActRow+ " td+td+td+td+td+td+td+td span span span[class='k-input']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",cpnCombo);
			Thread.sleep(5000);
			if (doesElementExist2(properties.getProperty("cmpnActionListbox"),5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("cmpnActionListbox")));
				for (WebElement lnk : selopts) {
					if (lnk.getText().contains("Duplicate With Recipients")) {
						lnk.click();
						Thread.sleep(5000);
						abc="false";
						log.logLine(Testname, false,"Selecting the Campaign Action 'Duplicate With Recipients' from the drop down is successful");
						break;
					}
				}

				if(abc.equalsIgnoreCase("true")){
					log.logLine(Testname, true,"selecting the 'Duplicate With Recipients' button from chooselist failed");}

			} else {
				log.logLine(Testname, true,"Actions on Campaign are not  displayed");
				throw new Exception("Actions on Campaign are not displayed");
			}
		}

		if (doesElementExist2(properties.getProperty("cmpaignNme"), 5)) {
			WebElement uploadfile = driver.findElement(By.cssSelector(properties.getProperty("cmpaignNme")));
			Thread.sleep(1000);
			uploadfile.sendKeys("Auto" + RandNo3);
			log.logLine(Testname, false,"Entering campaign name "+"Auto" + RandNo3+" is successful");
		} else {
			log.logLine(Testname, true,"Entering campaign name "+"Auto" + RandNo3+" is unsuccessful");
			throw new Exception("Entering campaign name "+"Auto" + RandNo3+" is unsuccessful");
		}

		CampaignManagerFields(Testname);
		return RandNo3;
	}

	public String DupliWithOutReciCagnAction(String Testname, String ActRow)throws Exception {

		InputOutputData test = new InputOutputData();
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object() {}.getClass().getEnclosingMethod().getName();

		String abc="true";

		if (doesElementExist2(properties.getProperty("AccountNumber"), 5)) {
			WebElement cpnCombo = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ ActRow+ " td+td+td+td+td+td+td+td span span span[class='k-input']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",cpnCombo);
			Thread.sleep(5000);
			if (doesElementExist2(properties.getProperty("cmpnActionListbox"),5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("cmpnActionListbox")));
				for (WebElement lnk : selopts) {
					if (lnk.getText().contains("Duplicate Without Recipients")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(5000);
						log.logLine(Testname,false,"Selecting the Campaign Action Duplicate Without Recipients from the drop down is successful");
						abc="false";
						break;
					}
				}
				if(abc.equalsIgnoreCase("true")){
					log.logLine(Testname, true,"selecting the 'Duplicate Without Recipients' button from chooselist failed");}

			} else {
				log.logLine(Testname, true,"Actions on Campaign are not are not displayed");
				throw new Exception("Actions on Campaign are not displayed");
			}
		}

		if (doesElementExist2(properties.getProperty("cmpaignNme"), 5)) {
			WebElement uploadfile = driver.findElement(By.cssSelector(properties.getProperty("cmpaignNme")));
			Thread.sleep(4000);
			String abc1="Auto" + RandNo5;
			uploadfile.sendKeys("Auto" + RandNo5);
			Thread.sleep(4000);
			log.logLine(Testname, false,"Entering campaign name "+abc1+" is successful");
		} else {
			log.logLine(Testname, true,"Entering campaign name  is not successful");
			throw new Exception("Entering campaign name  is not successful");
		}

		String ReciName1 = test.readColumnData("RecipientName2", sheetname);
		if (doesElementExist2(properties.getProperty("cmpainReciList"), 5)) {
			WebElement reciSel = driver.findElement(By.cssSelector(properties.getProperty("cmpainReciList")));		
			reciSel.click();	
			Thread.sleep(1000);
			if (doesElementExist2(properties.getProperty("ReciList"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ReciList")));
				for (WebElement lnk : selopts) {
					if (lnk.getText().contains(ReciName1)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(4000);
						break;
					}
				}

			}
			log.logLine(Testname, false,"Entering Recipient name "+ReciName1+"  in the combo field is successful");
		} else {
			log.logLine(Testname, true,"Entering Recipient name "+ReciName1+"  in the combo field is unsuccessful");
			throw new Exception("Entering Recipient name "+ReciName1+"  in the combo field is unsuccessful");
		}

		CampaignManagerFields(Testname);

		return RandNo5;
	}

	public boolean EditCampaignAction(String Testname, String ActRow)throws Exception {
		String abc="true";
		if (doesElementExist2(properties.getProperty("AccountNumber"), 5)) {
			WebElement cpnCombo = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ ActRow+ " td+td+td+td+td+td+td+td span span span[class='k-input']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",cpnCombo);
			Thread.sleep(5000);
			if (doesElementExist2(properties.getProperty("cmpnActionListbox"),5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties	.getProperty("cmpnActionListbox")));
				for (WebElement lnk : selopts) {
					if (lnk.getText().contains("Edit")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(5000);
						log.logLine(Testname, false,"Selecting the Campaign Action 'Edit' from the drop down is successful");
						abc="false";
						break;
					}
				}

				if(abc.equalsIgnoreCase("true")){
					log.logLine(Testname, true,"selecting the 'Edit' button from chooselist failed");}

			} else {
				log.logLine(Testname, true,"Actions on Campaign are not displayed");
				throw new Exception("Actions on Campaign are not displayed");
			}
		}

		CampaignManagerFields(Testname);
		Thread.sleep(2000);

		return true;
	}

	public boolean HoldCampaignAction(String Testname, String ActRow,String camp)throws Exception {

		String abc="true";
		if (doesElementExist2(properties.getProperty("AccountNumber"), 5)) {
			WebElement cpnCombo = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ ActRow+ " td+td+td+td+td+td+td+td span span span[class='k-input']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",cpnCombo);
			if (doesElementExist2(properties.getProperty("cmpnActionListbox"),5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("cmpnActionListbox")));
				for (WebElement lnk : selopts) {
					if (lnk.getText().contains("Hold")) {
						lnk.click();
						Thread.sleep(1000);
						abc="false";
						log.logLine(Testname, false,"Selecting the 'Hold' Campaign Action from the drop down for "+camp+" is successful");
						break;
					}
				}
				if(abc.equalsIgnoreCase("true")){
					log.logLine(Testname, true,"selecting the 'Hold' button from chooselist failed");}

			} else {
				log.logLine(Testname, true,"Actions on Campaign are not displayed");
				throw new Exception("Actions on Campaign are not displayed");
			}
		}

		Alert alert = driver.switchTo().alert();	
		alert.accept();

		Thread.sleep(6000);

		WebElement recibtn10 = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ ActRow + " td+td+td+td"));

		if (recibtn10.getText().contains("Hold")) {
			log.logLine(Testname, false,"Selected Campaign Manager status displayed 'Hold' successfully in the grid");
		} else {
			log.logLine(Testname,true,"Selected Campaign Manager status not displayed 'Hold' successfully in the grid");
		}

		return true;
	}

	public boolean ReleaseCampaignAction(String Testname, String ActRow)throws Exception {

		String abc="true";
		if (doesElementExist2(properties.getProperty("AccountNumber"), 5)) {
			WebElement cpnCombo = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ ActRow+ " td+td+td+td+td+td+td+td span span span[class='k-input']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",cpnCombo);
			Thread.sleep(5000);
			if (doesElementExist2(properties.getProperty("cmpnActionListbox"),5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("cmpnActionListbox")));
				for (WebElement lnk : selopts) {
					if (lnk.getText().contains("Release")) {
						lnk.click();
						Thread.sleep(5000);
						abc="false";
						log.logLine(Testname, false,"Selecting the Campaign Action Release from the drop down is successful");
						break;
					}
				}

				if(abc.equalsIgnoreCase("true")){
					log.logLine(Testname, true,"selecting the 'Release' button from chooselist failed");}

			} else {
				log.logLine(Testname, true,"Actions on Campaign are not are not displayed");
				throw new Exception("Actions on Campaign are not displayed");
			}
		}
		Thread.sleep(5000);
		Alert alert = driver.switchTo().alert();
		if (alert.getText().contains("release")) {
			log.logLine(Testname, false,"Selecting the Campaign Action 'Release' from the drop down is successful");
		} else {
			log.logLine(Testname, true,"Selecting the Campaign Action 'Release' from the drop down is not successful");
		}

		alert.accept();

		Thread.sleep(4000);

		WebElement recibtn = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ ActRow + " td+td+td+td"));

		if (recibtn.getText().contains("Ready")) {
			log.logLine(Testname, false,"Selected Campaign Manager status displayed 'Ready' successfully in the grid");
		} else {
			log.logLine(Testname,true,"Selected Campaign Manager status not displayed 'Ready' successfully in the grid");
		}
		return true;
	}

	public boolean RunCampaignAction(String Testname, String ActRow) throws Exception {

		if (doesElementExist2(properties.getProperty("AccountNumber"), 5)) {
			WebElement cpnCombo = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ActRow+" td+td+td+td+td+td+td+td span span span[class='k-input']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",cpnCombo);
			Thread.sleep(5000);
			if (doesElementExist2(properties.getProperty("cmpnActionListbox"),	5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("cmpnActionListbox")));
				for (WebElement lnk : selopts) {
					if (lnk.getText().contains("Run")) {
						lnk.click();
						Thread.sleep(4000);
						log.logLine(Testname, false,"Selecting the Campaign Run Action from the drop down is successful");
						break;
					}
				}

			} else {
				log.logLine(Testname, true,"Actions on Campaign are not are not displayed");
				throw new Exception("Actions on Campaign are not displayed");
			}
		}
		Thread.sleep(5000);
		Alert alert = driver.switchTo().alert();

		if (alert.getText().contains("run")) {
			log.logLine(Testname, false,"Selecting the Campaign Action 'Run' from the drop down is successful");
		} else {
			log.logLine(Testname, true,"Selecting the Campaign Action 'Run' from the drop down is not successful");
		}

		alert.accept();

		Thread.sleep(15000);

		WebElement recibtn = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ ActRow + " td+td+td+td"));
		if (recibtn.getText().contains("Run")) {
			log.logLine(Testname, false,"Selected Campaign Manager status displayed 'Run' successfully in the grid");
		} else {
			log.logLine(Testname, true,"Selected Campaign Manager status not displayed 'Run' successfully in the grid");
		}

		return true;
	}

	public boolean ViewCampaignAction(String Testname, String ActRow)throws Exception {
		String abc="true";

		if (doesElementExist2(properties.getProperty("AccountNumber"), 5)) {
			WebElement cpnCombo = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ ActRow+ " td+td+td+td+td+td+td+td span span span[class='k-input']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",cpnCombo);
			Thread.sleep(5000);
			if (doesElementExist2(properties.getProperty("cmpnActionListbox"),5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("cmpnActionListbox")));
				for (WebElement lnk : selopts) {
					if (lnk.getText().contains("View")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(5000);
						log.logLine(Testname, false,"Selecting the Campaign View Action from the drop down is successful");
						abc="false";
						break;
					}
				}

				if(abc.equalsIgnoreCase("true")){
					log.logLine(Testname, true,"selecting the 'Edit' button from chooselist failed");}


			} else {
				log.logLine(Testname, true,"Actions on Campaign are not are not displayed");
				throw new Exception("Actions on Campaign are not displayed");
			}
		}

		if (doesElementExist(properties.getProperty("CampaingView"), 5)) {
			WebElement NoDocs = driver.findElement(By.xpath(properties.getProperty("CampaingView")));
			if (NoDocs.getText().contains("Campaign Details")) {
				log.logLine(Testname, false,"Selected Campaign Action 'View' from the drop down is successful");
			} else {
				log.logLine(Testname, true,"Selected Campaign Action 'View' from the drop down is not successful");
			}
		}

		if (doesElementExist(properties.getProperty("CmpDetailsBar"), 5)) {
			WebElement cmpdetbar = driver.findElement(By.xpath(properties.getProperty("CmpDetailsBar")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",cmpdetbar);
			Thread.sleep(2000);
			log.logLine(Testname, false,"Clicking on Campaign Details bar to validate Campaign Name is successful");
		} else {
			log.logLine(Testname, true,"Clicking on Campaign Details bar to validate Campaign Name is not successful");
			throw new Exception("Clicking on Campaign Details bar to validate Campaign Name is not successful");
		}

		if (doesElementExist(properties.getProperty("CmpNameDetailsBar"), 5)) {
			WebElement NoDocs = driver.findElement(By.xpath(properties.getProperty("CmpNameDetailsBar")));
			if (NoDocs.getText().contains("Auto")) {
				log.logLine(Testname, false,"Verifying Campaign name in the Details grid is successful");
			} else {
				log.logLine(Testname, true,"Verifying Campaign name in the Details grid is not successful");
			}
		}

		if (doesElementExist(properties.getProperty("CmpProcessingDetails"), 5)) {
			WebElement procesdet = driver.findElement(By.xpath(properties.getProperty("CmpProcessingDetails")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",procesdet);
			Thread.sleep(2000);
			log.logLine(Testname, false,"Clicking on Processing Details bar to validate Scheduled Date is successful");
		} else {
			log.logLine(Testname,true,"Clicking on Processing Details bar to validate Scheduled Date is not successful");
			throw new Exception("Clicking on Processing Details bar to validate Scheduled Date is not successful");
		}

		Date date = new Date();
		String DATE_FORMAT = "MM/dd/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

		if (doesElementExist(properties.getProperty("CmpProcessingDetDate"), 5)) {
			WebElement NoDocs = driver.findElement(By.xpath(properties.getProperty("CmpProcessingDetDate")));
			if (NoDocs.getText().contains("11:30:00 PM")) {
				log.logLine(Testname, false,"Verifying Scheduled Date " + sdf.format(date)+ " in the Details grid is successful");
			} else {
				log.logLine(Testname, true,"Verifying Scheduled Date " + sdf.format(date)+ " in the Details grid is not successful");
			}
		}

		if (doesElementExist(properties.getProperty("CampaignHide"), 5)) {
			WebElement uploadfile = driver.findElement(By.xpath(properties.getProperty("CampaignHide")));
			Thread.sleep(1000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",uploadfile);
			log.logLine(Testname, false,"Clicking on Hide button is successful");
		} else {
			log.logLine(Testname, true,"Clicking on Hide button is not successful");
			throw new Exception("Clicking on Hide button is not successful");
		}

		return true;
	}

	public boolean CancelCampaignAction(String Testname, String ActRow)throws Exception {
		String abc="true";
		if (doesElementExist2(properties.getProperty("AccountNumber"), 5)) {
			WebElement cpnCombo = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ ActRow+ " td+td+td+td+td+td+td+td span span span[class='k-input']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",cpnCombo);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicking on Action List dropdown..");
			if (doesElementExist2(properties.getProperty("cmpnActionListbox"),5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("cmpnActionListbox")));
				for (WebElement lnk : selopts) {
					if (lnk.getText().contains("Cancel")) {
						lnk.click();
						Thread.sleep(5000);
						abc="false";
						log.logLine(Testname, false,"Selecting the Campaign Cancel Action from the drop down is successful");
						break;
					}
				}
				if(abc.equalsIgnoreCase("true")){
					log.logLine(Testname, true,"selecting the 'Cancel' button from chooselist failed");}

			} else {
				log.logLine(Testname, true,"Actions on Campaign are not are not displayed");
				throw new Exception("Actions on Campaign are not displayed");
			}
		}
		Thread.sleep(5000);
		Alert alert = driver.switchTo().alert();
		if (alert.getText().contains("cancel")) {
			log.logLine(Testname, false,"Selecting the Campaign Action 'Cancel' from the drop down is successful");
		} else {
			log.logLine(Testname, true,	"Selecting the Campaign Action 'Cancel' from the drop down is not successful");
		}

		alert.accept();
		Thread.sleep(4000);
		WebElement recibtn = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "
				+ ActRow + " td+td+td+td"));

		if (recibtn.getText().contains("Cancelled")) {
			log.logLine(Testname,false,"Selected Campaign Manager status displayed 'Cancelled' successfully in the grid");
		} else {
			log.logLine(Testname,true,"Selected Campaign Manager status not displayed 'Cancelled' successfully in the grid");
		}
		return true;
	}

	public void DeleteCampaignAction(String Testname, String ActRow,String camp)throws Exception {

		if (doesElementExist2(properties.getProperty("AccountNumber"), 5)) {

			WebElement cpnCombo = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ ActRow+ " td+td+td+td+td+td+td+td span span span[class='k-input']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",cpnCombo);
			Thread.sleep(4000);
			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("cmpnActionListbox")));
			for (WebElement lnk : selopts) {
				if (lnk.getText().contains("Delete")) {
					lnk.click();
					Thread.sleep(6000);
					log.logLine(Testname, false,"Selecting the Campaign "+camp+" to perform Delete Action from the drop down is successful");
					break;
				}
			}
		}

		Alert alert = driver.switchTo().alert(); 
		alert.accept();

		Thread.sleep(4000);
	}

	public boolean CampaignManagerFields(String Testname) throws Exception {

		Date date = new Date();
		String DATE_FORMAT = "MM/dd/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

		if (doesElementExist2(properties.getProperty("ScheduleStrtDate"), 5)) {
			WebElement dateSel = driver.findElement(By.cssSelector(properties.getProperty("ScheduleStrtDate")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",dateSel);
			Thread.sleep(500);
			dateSel.clear();
			dateSel.sendKeys("01/01/2020");
			log.logLine(Testname, false,"Entering Schedule Date 01/01/2020 field is successful");
		} else {
			log.logLine(Testname, true,"Entering Schedule Date 01/01/2020 field is not successful");
			throw new Exception("Entering Schedule Date field is not successful");
		}

		if (doesElementExist2(properties.getProperty("ScheduleTimeEdit"), 5)) {
			WebElement dateSel = driver.findElement(By.cssSelector(properties.getProperty("ScheduleTimeEdit")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",dateSel);
			Thread.sleep(500);
			dateSel.clear();
			dateSel.sendKeys("11:30 PM");
			log.logLine(Testname, false,"Entering Schedule Time 11:30 PM field is successful");
		} else {
			log.logLine(Testname, true,"Entering Schedule Time 11:30 PM field is not successful");
			throw new Exception("Entering Schedule Time field is not successful");
		}

		if (doesElementExist2(properties.getProperty("cmpainNotes"), 5)) {
			WebElement reciSel = driver.findElement(By.cssSelector(properties.getProperty("cmpainNotes")));
			reciSel.clear();
			reciSel.sendKeys("This is automation regression testing.");
			log.logLine(Testname, false,"Entering combo notes  'This is automation regression testing' is  successful");
		} else {
			log.logLine(Testname, true,"Entering combo notes 'This is automation regression testing' is not successful");
			throw new Exception("Entering combo notes 'This is automation regression testing' is not successful");
		}

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("cmpainSave"), 5)) {
			WebElement cmpSave = driver.findElement(By.cssSelector(properties.getProperty("cmpainSave")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",cmpSave);
			Thread.sleep(1000);
			log.logLine(Testname, false,"Clicking on  cmpainSave button is successful");
		} else {
			log.logLine(Testname, true,"Clicking on  cmpainSave button is not successful");
			throw new Exception("Clicking on  cmpainSave button is not successful");
		}
		return true;
	}

	public void GmailLogin(String Testname) throws Exception {
		{driver.get("https://www.googlemail.com");
		Thread.sleep(9000);

		if (doesElementExist2(properties.getProperty("Gmail_ID"), 5)) {
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Gmail_ID")));
			if (!(gmailid.getAttribute("class").equalsIgnoreCase("email-input hidden"))) {
				gmailid.clear();
				gmailid.sendKeys("automationpivot@gmail.com");
				log.logLine(Testname, false, "Entering the Gamil ID..");
			}
		} else {
			log.logLine(Testname, true, "Entering the Gamil ID is failed");
			driver.close();
			throw new Exception("Entering the Gamil ID is failed");
		}

		if (doesElementExist2(properties.getProperty("Gmail_NxtBtn"), 5)) {
			WebElement gmailnxt = driver.findElement(By.cssSelector(properties.getProperty("Gmail_NxtBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", gmailnxt);
			Thread.sleep(4000);}
		else {
			log.logLine(Testname, true, "Gmail_nxt button doesnot exist");
			driver.close();
			throw new Exception("Gmail_nxt button doesnot exist");
		}

		if (doesElementExist2(properties.getProperty("Gmail_Passwd"), 5)) {
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Gmail_Passwd")));
			gmailid.clear();
			gmailid.sendKeys("miracle@123");
			log.logLine(Testname, false, "Entering the Gmail password..");

		} else if (doesElementExist2(properties.getProperty("Gmail_NxtBtn"), 5)) {
			WebElement gmailnxt = driver.findElement(By.cssSelector(properties.getProperty("Gmail_NxtBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", gmailnxt);
			Thread.sleep(4000);
			if (doesElementExist2(properties.getProperty("Gmail_Passwd"), 5)) {
				WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Gmail_Passwd")));
				gmailid.clear();
				gmailid.sendKeys("miracle@123");
				log.logLine(Testname, false,"Entering the Gmail password..");
			} else {
				log.logLine(Testname, true,"Entering the password ID is failed");
				throw new Exception("Entering the Gmail password is failed");
			}

		} else {
			log.logLine(Testname, true,"Entering the password ID is failed");
			throw new Exception("Entering the Gmail password is failed");
		}

		Thread.sleep(1000);

		if (doesElementExist2(properties.getProperty("Gmail_PwdNxtBtn"), 5)) {
			WebElement gmailSign = driver.findElement(By.cssSelector(properties.getProperty("Gmail_PwdNxtBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", gmailSign);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the Gmail passwordnext button to sign in");
		} else {
			log.logLine(Testname, true,"Clicked on the gamil SignIn is failed");
			throw new Exception("Clicked on the gmail SignIn is failed");
		}

		long timenow = System.currentTimeMillis();
		}

	}

	public void CampaignGmailVerification(String Subject, String Testname)throws Exception {

		if (doesElementExist2(properties.getProperty("Refresh"), 5)) {
			WebElement refreshbtn = driver.findElement(By.cssSelector(properties.getProperty("Refresh")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",refreshbtn);
			log.logLine(Testname, false, "Clicking on refresh btn of gmail is successful");
		} else {
			log.logLine(Testname, true,"Failed to click on refresh btn of gmail");
			//throw new Exception("Failed to click on refresh btn of gmail");
		}

		Thread.sleep(1000);
		if (doesElementExist2(properties.getProperty("SrchMail"), 5)) {
			WebElement srchfld = driver.findElement(By.cssSelector(properties.getProperty("SrchMail")));
			srchfld.clear();
			srchfld.sendKeys(Subject);
			log.logLine(Testname, false, "Entering the Search text..");
			srchfld.sendKeys(Keys.ENTER);
		} else {
			log.logLine(Testname, true, "Entering the Search text is failed");
		}

		Thread.sleep(12000);

		if (Subject.contains("Plain Text Template for CM testing")) {
			if (doesElementExist2(properties.getProperty("PlainMailSel"), 5)) {
				WebElement OpenMail = driver.findElement(By.cssSelector(properties.getProperty("PlainMailSel")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", OpenMail);
				log.logLine(Testname, false,"Clicked on the First email to read with subject "+Subject);
			} else {
				log.logLine(Testname, true,"Failed to click on email with subject "+Subject);
			}
		} else {
			if (doesElementExist2(properties.getProperty("VariableMailSel"), 5)) {
				WebElement OpenMail = driver.findElement(By.cssSelector(properties.getProperty("VariableMailSel")));	
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", OpenMail);
				log.logLine(Testname, false,"Clicked on the First email to read with subject "+Subject);
			} else {
				log.logLine(Testname, true,"Failed to click on email with subject "+Subject);
			}

		}

		if (doesElementExist2(properties.getProperty("ContentImage"), 5)) {
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("ContentImage")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",gmailid);
			Thread.sleep(500);
			log.logLine(Testname, false, "Clicking on expand icon of the message");
		} else {
			log.logLine(Testname, false,"Clicking on expand icon of the message doesnot exist");
		}

		Thread.sleep(3000);

		if (doesElementExist2(properties.getProperty("GmailContent1"), 5)) {
			WebElement contentBody = driver.findElement(By.cssSelector(properties.getProperty("GmailContent1")));
			if ("Plain Text Template for CM testing".equals(Subject)) {
				if (contentBody.getText().contains("Plain")) {
					log.logLine(Testname, false,"Validating plain template notification is successful");
				} else {
					log.logLine(Testname, false,"Validating plain template notification is not successful");
				}}
			else {
				WebElement contentBody1 = driver.findElement(By.cssSelector(properties.getProperty("GmailContent")));
				if (contentBody1.getText().contains("Variable")) {
					log.logLine(Testname, false,"Validating Variable template notification is successful ");
				} else {
					log.logLine(Testname, false,"Validating Variable template notification is not successful");
				}
			}}else {
				log.logLine(Testname, false,"Gmail content doesnot exist");
			}
	}

	public void DeletingEmailNotifications(String Subject, String Testname)
			throws Exception {

		if (doesElementExist2(properties.getProperty("SrchMail"), 5)) {
			WebElement srchfld = driver.findElement(By.cssSelector(properties.getProperty("SrchMail")));
			srchfld.sendKeys(Subject);
			log.logLine(Testname, false, "Entering the Search text..");
			srchfld.sendKeys(Keys.ENTER);

		} else {
			log.logLine(Testname, true, "Entering the Search text is failed");
			throw new Exception("Entering the Search text is failed");
		}

		Thread.sleep(12000);

		if (Subject.contains("Plain Text Template for CM testing")) {
			if (doesElementExist2(properties.getProperty("PlainMailSel"), 5)) {
				WebElement OpenMail = driver.findElement(By.cssSelector(properties.getProperty("PlainMailSel")));
				Actions action = new Actions(driver);
				action.contextClick(OpenMail).build().perform();

				OpenMail.sendKeys(Keys.ARROW_DOWN);
				OpenMail.sendKeys(Keys.ARROW_DOWN);
				OpenMail.sendKeys(Keys.ARROW_DOWN);
				OpenMail.sendKeys(Keys.ENTER);

				log.logLine(Testname, false,
						"Clicked on the First email to read");

			} else {
				log.logLine(Testname, true,
						"Clicking on the Plain Template Notification email to read is failed");
				throw new Exception(
						"Clicking on the Plain Template Notification email to read is failed");
			}
		} else {
			if (doesElementExist2(properties.getProperty("VariableMailSel"), 5)) {
				WebElement OpenMail = driver
						.findElement(By.cssSelector(properties
								.getProperty("VariableMailSel")));
				OpenMail.sendKeys(Keys.BACK_SPACE);
				OpenMail.sendKeys(Keys.ARROW_DOWN);
				OpenMail.sendKeys(Keys.ARROW_DOWN);
				OpenMail.sendKeys(Keys.ARROW_DOWN);
				OpenMail.sendKeys(Keys.ENTER);

				log.logLine(Testname, false,
						"Clicked on the Varaible Template Notification email to read");

			} else {
				log.logLine(Testname, true,
						"Clicking on the Varaible Template Notification email to read is failed");
				throw new Exception(
						"Clicking on the Varaible Template Notification email to read is failed");
			}

		}

	}

	public void Hold_Releaseactions(String Acc, String Testname) throws Exception {

		InputOutputData test = new InputOutputData();
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object() {}.getClass().getEnclosingMethod().getName();

		ClientAndAppSel(Testname);

		if (doesElementExist2(properties.getProperty("CampMangerCss"), 5)) {
			WebElement campmgr = driver.findElement(By.cssSelector(properties.getProperty("CampMangerCss")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",campmgr);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false,"Clicking on Campaign Manager link is successful");
		} else {
			log.logLine(Testname, true,"Clicking on Campaign Manager link is not successful");
			throw new Exception("Clicking on Campaign Manager link is not successful");
		}

		Thread.sleep(1000);
		driver.switchTo().frame("iframeContainer");
		PleasewaitDisappear();

		// Bulk Hold & Release positive validation

		campnme1=bulkHoldRealseExcution(Testname, test, sheetname, AccNo);
		Thread.sleep(2000);
		campnme2=bulkHoldRealseExcution(Testname, test, sheetname, RandNo5);
		Thread.sleep(3000);
		campaignRow = compaignValidForBulkHoldRelease(Testname, AccNo);
		Thread.sleep(1000);
		currentStatus = fetchCompaignCurrentStatus(Testname, campaignRow,campnme1);
		Thread.sleep(2000);
		campaignOtherRow = compaignValidForBulkHoldRelease(Testname, RandNo5);
		Thread.sleep(1000);
		otherRowCurrentStatus = fetchCompaignCurrentStatus(Testname,campaignOtherRow,campnme2);
		selectingCheckBox(Testname, campaignRow,campnme1);
		selectingCheckBox(Testname, campaignOtherRow,campnme2);
		Thread.sleep(1000);
		enableMainChooseAction(Testname);
		MainchooseHoldCampaignAction(Testname, campaignRow, campaignOtherRow,currentStatus, otherRowCurrentStatus,campnme1,campnme2);
		Thread.sleep(1000);
		currentStatus1 = fetchCompaignCurrentStatus(Testname, campaignRow,campnme1);
		otherRowCurrentStatus1 = fetchCompaignCurrentStatus(Testname,campaignOtherRow,campnme2);
		log.logLine(Testname, false,"Status of "+campnme1 +"and"+ campnme2+" before Release are "+ currentStatus1 +" and "+ otherRowCurrentStatus1);
		Thread.sleep(2000);
		selectingCheckBox(Testname, campaignRow,campnme1);
		Thread.sleep(2000);
		selectingCheckBox(Testname, campaignOtherRow,campnme2);
		Thread.sleep(2000);
		mainChooseReleaseCampaignAction(Testname, campaignRow,campaignOtherRow, currentStatus1, otherRowCurrentStatus1,campnme1,campnme2);
		otherRowCurrentStatus1 = fetchCompaignCurrentStatus(Testname, campaignOtherRow,campnme2);
		currentStatus1 = fetchCompaignCurrentStatus(Testname, campaignRow,campnme1);

		log.logLine(Testname, false,"Status of "+campnme1 +"and"+ campnme2+" after positive Release are "+ currentStatus1 +" and "+ otherRowCurrentStatus1);

		if (currentStatus1.equalsIgnoreCase("Ready") && otherRowCurrentStatus1.equalsIgnoreCase("Ready")) {
			log.logLine(Testname, false,"Status changed now,Validation on Release is successful");
		}
		else{log.logLine(Testname, true,"Status is still not changed to Ready after 'Release' action");}


		// Negative validation

		log.logLine(Testname, false, "negative validation starts");
		HoldCampaignAction(Testname, campaignOtherRow,campnme2);
		Thread.sleep(1000);
		selectingCheckBox(Testname, campaignRow,campnme1);
		selectingCheckBox(Testname, campaignOtherRow,campnme2);
		enableMainChooseAction(Testname);
		Thread.sleep(3000);
		otherRowCurrentStatus1 = fetchCompaignCurrentStatus(Testname,campaignOtherRow,campnme2);
		log.logLine(Testname, false,"Status of  "+campnme1 +" before 'main hold' action is "+ currentStatus1);
		log.logLine(Testname, false,"Status of the  "+campnme2 +" before 'main hold' action is "+ otherRowCurrentStatus1);
		MainchooseHoldCampaignAction(Testname, campaignRow, campaignOtherRow,currentStatus1, otherRowCurrentStatus1,campnme1,campnme2);
		log.logLine(Testname, false,"Status of  "+campnme1 +" before 'main release' action is "+ currentStatus1);
		log.logLine(Testname, false,"Status of the  "+campnme2 +" before 'main release' action is "+ otherRowCurrentStatus1);
		mainChooseReleaseCampaignAction(Testname, campaignRow,campaignOtherRow, currentStatus1, otherRowCurrentStatus1,campnme1,campnme2);
		Thread.sleep(5000);
		DeleteCampaignAction(Testname, campaignRow,campnme1);
		CmpaigndeleteValidation(Testname,campnme1);
		Thread.sleep(10000);
		DeleteCampaignAction(Testname, campaignOtherRow,campnme2);
		CmpaigndeleteValidation(Testname,campnme2);

		Thread.sleep(4000);
	}

	public String CampaignManagerFunctionality(String Acc,String TemplateType, String Testname) throws Exception {

		ManageRecipientBtn(Testname);

		Thread.sleep(4000);
		if (doesElementExist(properties.getProperty("uploadBtn"), 5)) {
			WebElement recibtn = driver.findElement(By.xpath(properties.getProperty("uploadBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",recibtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false,"Clicking on upload new button is successful");
		} else {
			log.logLine(Testname, true,"Clicking on upload new button is not successful");
			throw new Exception("Clicking on upload new button is not successful");
		}

		if ("CM_PlainRecipient_AutoTest".equals(TemplateType)) {
			WebElement Browse = driver.findElement(By.xpath("html/body/div[3]/div[5]/div[2]/div[2]/div/input[1]"));
			Thread.sleep(3000);
			Browse.sendKeys("C:\\Pivot_Portal\\Test Data\\CM_PlainRecipient_AutoTest.txt");
			log.logLine(Testname, false,"Entering file name 'CM_PlainRecipient_AutoTest.txt' to upload");
		} else if ("CM_VariableRecipient_AutoTest".equals(TemplateType)) {
			WebElement Browse = driver.findElement(By.xpath("html/body/div[3]/div[5]/div[2]/div[2]/div/input[1]"));
			Thread.sleep(3000);
			Browse.sendKeys("C:\\Pivot_Portal\\Test Data\\CM_VariableRecipient_AutoTest.txt");
			log.logLine(Testname, false,"Entering file name 'CM_VariableRecipient_AutoTest.txt' to upload");
		}

		Thread.sleep(2000);

		if (doesElementExist2(properties.getProperty("uloadListBtn"), 5)) {
			WebElement recibtn = driver.findElement(By.cssSelector(properties.getProperty("uloadListBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",recibtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			Thread.sleep(1000);
			log.logLine(Testname, false,"Clicking on uploadlist button is successful");
		} else {
			log.logLine(Testname, true,"Clicking on uploadlist button is not successful");
			throw new Exception("Clicking on uploadlist button is not successful");
		}

		if (doesElementExist(properties.getProperty("ManageRecipientCancel"), 5)) {
			WebElement recibtn = driver.findElement(By.xpath(properties.getProperty("ManageRecipientCancel")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",recibtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false,"Clicking on Manage Recipients button is successful");
		} else {
			log.logLine(Testname, true,"Clicking on Manage Recipients button is not successful");
			throw new Exception("Clicking on Manage Recipients button is not successful");
		}

		NewCmpaignBtn(Testname);

		if (doesElementExist2(properties.getProperty("cmpaignNme"), 5)) {
			WebElement uploadfile = driver.findElement(By.cssSelector(properties.getProperty("cmpaignNme")));
			Thread.sleep(1000);
			uploadfile.sendKeys("Auto" + Acc);
			log.logLine(Testname, false,"Entering campaign name "+"Auto" + Acc+" is successful");
		} else {
			log.logLine(Testname, true,"Entering campaign name "+"Auto" + Acc+" is not successful");
			throw new Exception("Entering campaign name "+"Auto" + Acc+" is not successful");
		}

		if (doesElementExist2(properties.getProperty("cmpainReciList"), 5)) {
			WebElement reciSel = driver.findElement(By.cssSelector(properties.getProperty("cmpainReciList")));
			Thread.sleep(1000);
			reciSel.click();		
			Thread.sleep(1000);
			if (doesElementExist2(properties.getProperty("ReciList"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ReciList")));
				for (WebElement lnk : selopts) {
					if (lnk.getText().contains(TemplateType)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false,"Entering Recipient name "+TemplateType+" in the combo field is successful");
						break;
					}

				}
			}
		}
		else {
			log.logLine(Testname, true,"Entering Recipientname in the combo field is not successful");
			throw new Exception("Entering Recipient name in the combo field is not successful");
		}

		String TempName;

		if ("CM_PlainRecipient_AutoTest".equals(TemplateType)) {

			TempName = "PlainTemplateCM";
			if (doesElementExist2(properties.getProperty("TempCombo"), 5)) {
				WebElement reciSel = driver.findElement(By.cssSelector(properties.getProperty("TempCombo")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", reciSel);
				if (doesElementExist2(properties.getProperty("TempList"), 5)) {
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("TempList")));
					for (WebElement lnk : selopts) {
						if (lnk.getText().contains(TempName)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false,"Selecting the TemplateName " + TempName+ " from the popup..");
							break;
						}
					}

				} else {
					log.logLine(Testname, true,"Template Name options are not displayed");
					throw new Exception("Template Name options are not displayed");
				}
			}
		} else if ("CM_VariableRecipient_AutoTest".equals(TemplateType)) {
			TempName = "VariableTemplateCM";
			if (doesElementExist2(properties.getProperty("TempCombo"), 5)) {
				WebElement reciSel = driver.findElement(By.cssSelector(properties.getProperty("TempCombo")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", reciSel);	
				if (doesElementExist2(properties.getProperty("TempList"), 5)) {
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("TempList")));
					for (WebElement lnk : selopts) {
						if (lnk.getText().contains(TempName)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false,"Selecting the TemplateName " + TempName+ " from the popup..");
							break;
						}
					}

				} else {
					log.logLine(Testname, true,"Template Name options are not displayed");
					throw new Exception("Template Name options are not displayed");
				}
			}
		}

		CampaignManagerFields(Testname);

		String CmpRow = CmpaignValidation(Testname, Acc);

		RunCampaignAction(Testname, CmpRow);

		return CmpRow;
	}

	public boolean RecipientCreationFunctionality(String Testname)throws Exception {

		InputOutputData test = new InputOutputData();
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object() {}.getClass().getEnclosingMethod().getName();

		ManageRecipientBtn(Testname);

		if (doesElementExist(properties.getProperty("uploadBtn"), 5)) {
			WebElement recibtn = driver.findElement(By.xpath(properties.getProperty("uploadBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",recibtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false,"Clicking on upload new button is successful");
		} else {
			log.logLine(Testname, true,"Clicking on upload new button is not successful");
			throw new Exception("Clicking on upload new button is not successful");
		}

		if (doesElementExist2(properties.getProperty("uloadListBtn"), 5)) {
			WebElement recibtn = driver.findElement(By.cssSelector(properties.getProperty("uloadListBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",recibtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false,"Clicking on uloadListBtn button is successful");
		} else {
			log.logLine(Testname, true,"Clicking on uloadListBtn button is not successful");
			throw new Exception("Clicking on uloadListBtn button is not successful");
		}

		if (doesElementExist(properties.getProperty("uploadBtn"), 5)) {
			WebElement recibtn = driver.findElement(By.xpath(properties.getProperty("uploadBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",recibtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false,"Clicking on upload  button is successful");
		} else {
			log.logLine(Testname, true,"Clicking on upload button is not successful");
			throw new Exception("Clicking on upload  button is not successful");
		}

		String ReciName = test.readColumnData("RecipientName", sheetname);

		if (doesElementExist2(properties.getProperty("addReciField"), 5)) {
			WebElement uploadfile = driver.findElement(By.cssSelector(properties.getProperty("addReciField")));
			Thread.sleep(1000);
			uploadfile.sendKeys(ReciName);
			log.logLine(Testname, false,"Clicking on Manage Recipients button is successful");
		} else {
			log.logLine(Testname, true,"Clicking on Manage Recipients button is not successful");
			throw new Exception("Clicking on Manage Recipients button is not successful");
		}

		Thread.sleep(2000);

		if (doesElementExist2(properties.getProperty("uloadListBtn"), 5)) {
			WebElement recibtn = driver.findElement(By.cssSelector(properties.getProperty("uloadListBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",recibtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false,"Clicking on upload list button is successful");
		} else {
			log.logLine(Testname, true,"Clicking on upload list button is not successful");
			throw new Exception("Clicking on upload list button is not successful");
		}

		if (doesElementExist(properties.getProperty("ManageRecipientCancel"), 5)) {
			WebElement recibtn = driver.findElement(By.xpath(properties.getProperty("ManageRecipientCancel")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",recibtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false,"Clicking on Manage Recipients cancel button is successful");
		} else {
			log.logLine(Testname, true,"Clicking on Manage Recipients cancel button is not successful");
			throw new Exception("Clicking on Manage Recipients cancel button is not successful");
		}
		return true;
	}

	public void ClientAndAppSel(String Testname) throws Exception {

		InputOutputData test = new InputOutputData();
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object() {
		}.getClass().getEnclosingMethod().getName();

		driver.switchTo().defaultContent();
		Thread.sleep(2000);

		if (doesElementExist2(properties.getProperty("EdeliveryTab"), 5)) {
			WebElement eDelive = driver.findElement(By.cssSelector(properties.getProperty("EdeliveryTab")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",eDelive);
			log.logLine(Testname, false,"Click on e-Delivery Tab is Successful");
		} else {
			log.logLine(Testname, true, "Click on e-Delivery Tab is failed");
			throw new Exception("Click on e-Delivery Tab is failed");
		}

		Thread.sleep(1000);

		ClntName = test.readColumnData("ClientName", sheetname);

		if (doesElementExist2(properties.getProperty("selClint1"), 5)) {
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selClint1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",ClientSel);
			log.logLine(Testname, false, "Clicking on ClientName dropdown..");

			if (doesElementExist2(properties.getProperty("ClinetOpts1"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ClinetOpts1")));
				for (WebElement lnk : selopts) {
					if (lnk.getText().contains(ClntName)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false,"Selecting the ClientName " + ClntName+ " from the popup..");
						CliSelected = true;
						break;
					}
				}

			} else {
				log.logLine(Testname, true,"Client Name options are not displayed");
				throw new Exception("Client Name options are not displayed");
			}

		} else {
			log.logLine(Testname, true,"Client Name dropdown element does not exist");
			throw new Exception("Client Name dropdown element does not exist");
		}

		if (!CliSelected) {
			if (doesElementExist2(properties.getProperty("selClint2"), 5)) {
				WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selClint2")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

				log.logLine(Testname, false,"Clicking on ClientName dropdown..");

				if (doesElementExist2(properties.getProperty("ClinetOpts2"), 5)) {
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ClinetOpts2")));
					for (WebElement lnk : selopts) {
						if (lnk.getText().contains(ClntName)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false,"Selecting the ClientName " + ClntName+ " from the dropdown..");
							break;
						}
					}

				} else {
					log.logLine(Testname, true,"Client Name options are not displayed");
					throw new Exception("Client Name options are not displayed");
				}

			} else {
				log.logLine(Testname, true,"Client Name dropdown element does not exist");
				throw new Exception("Client Name dropdown element does not exist");
			}
		}

		boolean AppSelected = false;
		AppName = test.readColumnData("ApplicationName", sheetname);

		if (doesElementExist2(properties.getProperty("selAppl1"), 5)) {
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selAppl1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",ClientSel);
			log.logLine(Testname, false,"Clicking on ApplicationName dropdown..");

			if (doesElementExist2(properties.getProperty("ApplOpts1"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ApplOpts1")));
				for (WebElement lnk : selopts) {
					if (lnk.getText().contains(AppName)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false,"Selecting the Application Name " + AppName	+ " from the popup..");
						AppSelected = true;
						break;
					}
				}

			} else {
				log.logLine(Testname, true,"Application Name options are not displayed");
				throw new Exception("Application Name options are not displayed");
			}

		} else {
			log.logLine(Testname, true,"Application Name dropdown element does not exist");
			throw new Exception("Application Name dropdown element does not exist");
		}

		if (!AppSelected) {
			if (doesElementExist2(properties.getProperty("selAppl2"), 5)) {
				WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selAppl2")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
				log.logLine(Testname, false,"Clicking on ApplicationName dropdown..");

				if (doesElementExist2(properties.getProperty("ApplOpts2"), 5)) {
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ApplOpts2")));
					for (WebElement lnk : selopts) {
						if (lnk.getText().contains(AppName)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false,"Selecting the Application Name " + AppName+ " from the dropdown..");
							break;
						}
					}

				} else {
					log.logLine(Testname, true,"Application Name options are not displayed");
					throw new Exception("Application Name options are not displayed");
				}

			} else {
				log.logLine(Testname, true,"Application Name dropdown element does not exist");
				throw new Exception("Application Name dropdown element does not exist");
			}
		}

		PleasewaitDisappear();

		// Click on Ok button
		if (doesElementExist2(properties.getProperty("Okbtn"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Okbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",okbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on OK button");
		} else {
			log.logLine(Testname, true,"Clicking on OK button to view the Reports is failed");
			throw new Exception("Clicking on OK button to view the Reports is failed");
		}

	}

	public boolean ApplicationBaseState(String Testname, String RowCount1,String RowCount2,String camp) throws Exception {

		InputOutputData test = new InputOutputData();
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object() {
		}.getClass().getEnclosingMethod().getName();

		if ((Initialization.EnvirSite.equals("TEST"))|| (Initialization.EnvirSite.equals("Test"))|| (Initialization.EnvirSite.equals("test"))) {			
			driver.get(properties.getProperty("TESTURL"));		
			log.logLine("", false, "Navigated PA - TESTURL site");
		} else if ((Initialization.EnvirSite.equals("STAGE"))|| (Initialization.EnvirSite.equals("Stage"))|| (Initialization.EnvirSite.equals("stage"))) {
			driver.get(properties.getProperty("STAGEURL"));
			log.logLine("", false, "Navigated PA - STAGEURL site");
		} else if ((Initialization.EnvirSite.equals("PROD"))|| (Initialization.EnvirSite.equals("Prod"))|| (Initialization.EnvirSite.equals("prod"))) {
			driver.get(properties.getProperty("PRODURL"));
			log.logLine("", false, "Navigated PA - PRODURL site");
		} else if ((Initialization.EnvirSite.equals("SIT"))|| (Initialization.EnvirSite.equals("Sit"))|| (Initialization.EnvirSite.equals("sit"))) {
			driver.get(properties.getProperty("SITURL"));
			log.logLine("", false, "Navigated PA - SIT site");
		} else if ((Initialization.EnvirSite.equals("DR"))|| (Initialization.EnvirSite.equals("Dr"))|| (Initialization.EnvirSite.equals("dr"))) {
			driver.get(properties.getProperty("DRURL"));
			log.logLine("", false, "Navigated to PIVOT - DR site");
		}

		Thread.sleep(10000);

		ClientAndAppSel(Testname);

		if (doesElementExist2(properties.getProperty("CampMangerCss"), 5)) {
			WebElement campmgr = driver.findElement(By.cssSelector(properties.getProperty("CampMangerCss")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",campmgr);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false,"Clicking on Campaign Manager link is successful");
		} else {
			log.logLine(Testname, true,"Clicking on Campaign Manager link is not successful");
			throw new Exception("Clicking on Campaign Manager link is not successful");
		}

		Thread.sleep(1000);
		driver.switchTo().frame("iframeContainer");
		PleasewaitDisappear();

		WebElement recibtn = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "	+ RowCount2 + " td+td+td+td"));

		if (recibtn.getText().contains("Successful")) {
			log.logLine(Testname,false,"Selected Plain Text Campaign Manager status displayed 'Successful' successfully in the grid");
		} else {
			log.logLine(Testname,true,"Selected Plain Text Campaign Manager status not displayed 'Successful' successfully in the grid");
		}

		DeleteCampaignAction(Testname, RowCount2,camp);
		CmpaigndeleteValidation(Testname,camp);
		Thread.sleep(10000);

		WebElement recibtn1 = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "	+ RowCount1 + " td+td+td+td"));

		if (recibtn1.getText().contains("Successful")) {
			log.logLine(Testname,false,"Selected Variable Text Campaign Manager status displayed 'Successful' successfully in the grid");
			return true;
		} else {
			log.logLine(Testname,true,"Selected Variable Text Campaign Manager status not displayed 'Successful' successfully in the grid");
			throw new Exception("Selected Variable Text Campaign Manager status not displayed 'Successful' successfully in the grid");
		}
	}

	public boolean Alertcancelbutton(String Testname) throws Exception {
		if (doesElementExist2(properties.getProperty("Alertcancelbutton"), 5)) {
			WebElement cancelbtn = driver.findElement(By.cssSelector(properties.getProperty("Alertcancelbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",cancelbtn);
			log.logLine(Testname, false,"Clicking on Alertcancelbutton button is successful");
		} else {
			log.logLine(Testname, true,"Clicking on Alertcancelbutton button is not successful");
			throw new Exception("Clicking on Alertcancelbutton is not successful");
		}
		return true;
	}

	public boolean Alertokbutton(String Testname) throws Exception {
		if (doesElementExist(properties.getProperty("Alertokbutton"), 5)) {
			WebElement okbutton = driver.findElement(By.xpath(properties.getProperty("Alertokbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",okbutton);
			log.logLine(Testname, false,"Clicking on Alertokbutton button is successful");
		} else {
			log.logLine(Testname, true,"Clicking on Alertokbutton button is not successful");
			throw new Exception("Clicking on Alertokbutton is not successful");

		}
		return true;
	}

	public String compaignValidForBulkHoldRelease(String testname, String Accn)
			throws Exception {
		String[] Sort1 = new String[50];
		String row = "tr";
		String abc="yes";
		String newcamp="Auto" + Accn;
		List<WebElement> DataCnt = driver.findElements(By.xpath(".//*[@id='management-campaigns-grid']/table/tbody/tr"));

		if (doesElementExist2(properties.getProperty("AccountNumber"), 5)) {
			for (int i = 0; i < DataCnt.size(); i++) {
				Sort1[i] = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ row + " td+td+td[role='gridcell']")).getText();
				if (Sort1[i].equals(newcamp)) {
					log.logLine(testname, false,"Newly created Campaign name "+ Sort1[i]+" is present in the grid");
					Thread.sleep(2000);
					abc="no";		
					break;
				} 
				row = row + "+tr";
			}
			if(abc.equalsIgnoreCase("yes")){
				log.logLine(testname, true,"Newly created campaign doesnot exists in the grid");}
		}

		return row;
	}

	public String fetchCompaignCurrentStatus(String testname, String camprow,String fi)throws Exception {
		WebElement status = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ camprow + " td+td+td+td"));
		currentStatus=status.getText();
		if (currentStatus != null) {
			log.logLine(testname, false, "the current status of "+fi+" element is "+currentStatus);
		} else {
			log.logLine(testname, true,"the status of the newly created campaign is"+ currentStatus+"is showing null");
		}
		return currentStatus;
	}

	private void selectingCheckBox(String testname, String campaignRow,String fi)throws Exception {
		WebElement checkbx = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ campaignRow+ " td+td+td+td+td+td+td+td+td div label"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()",checkbx);
		log.logLine(testname, false, "Selecting the  "+fi+" check box is successful");
		Thread.sleep(1000);
	}

	private void enableMainChooseAction(String testname) throws Exception {
		if (doesElementExist(properties.getProperty("Mainchooseaction"), 5)) {
			WebElement recibtn = driver.findElement(By.xpath(properties.getProperty("Mainchooseaction")));
			if (recibtn.isEnabled()) {
				log.logLine(testname, false,"Main Choose action element is enabled ");
			} else {
				log.logLine(testname, false,"Main Choose action element is disabled ");
			}

			return;
		}
	}

	private void MainchooseHoldCampaignAction(String testname,String campaignRow, String campaignOtherRow, String currentStatus1,String CurrentStatus2,String element1,String element2) throws Exception {

		if (doesElementExist(properties.getProperty("Mainchooseaction"), 5)) {
			WebElement mainchoose = driver.findElement(By.xpath(properties.getProperty("Mainchooseaction")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",mainchoose);
			log.logLine(testname, false,"Clicking on MainchooseAction List dropdown..");
			Thread.sleep(1000);
			if (doesElementExist2(properties.getProperty("Mainchooseactionlist"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Mainchooseactionlist")));
				for (WebElement lnk : selopts) {
					if (lnk.getText().contains("Hold")) {
						lnk.click();
						Thread.sleep(4000);
						if (currentStatus1 != null && CurrentStatus2 != null) {

							if (currentStatus1.equalsIgnoreCase("Ready")&& CurrentStatus2.equalsIgnoreCase("Hold")|| (CurrentStatus2.equalsIgnoreCase("Ready") && currentStatus1.equalsIgnoreCase("Hold"))) {

								if (doesElementExist2(properties.getProperty("Holdalert1"), 5)) {								
									WebElement holalert = driver.findElement(By.cssSelector(properties.getProperty("Holdalert1")));								
									Thread.sleep(2000);							
									if (holalert.getText().contains("All the selected campaigns must have a status of [Ready]")) {
										log.logLine(testname,false,"Validation for Hold in main choose action for illegal combinations is successful");
										Alertcancelbutton(testname);
									} else {
										log.logLine(testname, true,"Validation for Hold in main choose action for illegal combinations is failed");
										Alertcancelbutton(testname);
									}
									Thread.sleep(6000);
								}
							} 

							else if (currentStatus1.equalsIgnoreCase("Ready")&& CurrentStatus2.equalsIgnoreCase("Ready")) {
								Thread.sleep(2000);
								if (doesElementExist(properties.getProperty("Holdconfirm"),5)) {						
									WebElement holalert = driver.findElement(By.xpath(properties.getProperty("Holdconfirm")));						
									Thread.sleep(1000);														
									if (holalert.getText().contains("Are you sure you want to place the campaign(s) selected on hold?")) {
										log.logLine(testname,false,"Validation for Hold in main choose action with both 'ready' combinations is successful");
										Alertokbutton(testname);
									} else {
										log.logLine(testname,true,"Validation for Hold in main choose action with both 'ready' combinations failed");
										Alertokbutton(testname);
									}
									Thread.sleep(12000);

									String changedstatus = fetchCompaignCurrentStatus(testname, campaignOtherRow,campnme2);
									String changedstatus1 = fetchCompaignCurrentStatus(testname, campaignRow,campnme1);

									log.logLine(testname, false,"changed status  of "+element1+" and "+element2+" is"+ changedstatus + " "+ changedstatus1);

									if (changedstatus.equalsIgnoreCase("Hold")&& changedstatus1.equalsIgnoreCase("Hold")) {
										log.logLine(testname, false,"Status changed now,Validation on hold is successful");
									}

								}

							}

							log.logLine(testname, false,"Selecting the Campaign Action from the main drop down is successful");

						}
					}
					break;
				}
			}
		} else {
			log.logLine(testname, true,"Actions on mainchooseaction are not displayed");
		}
	}

	public boolean Relogin(String Testname) throws Exception {

		log.logLine(Testname, false, "Logging in back to Super User to continue suite execution");

		PivotSignInOut loginPge = new PivotSignInOut(driver, log);
		loginPge.load(Initialization.Browser, Initialization.EnvirSite);
		Thread.sleep(6000);
		loginPge.loginAs(Initialization.UserID, Initialization.Passwd);

		return true;
	}

	private void mainChooseReleaseCampaignAction(String testname,String campaignRow, String campaignOtherRow, String currentStatus1,
			String CurrentStatus2,String element1,String element2) throws Exception {

		if (doesElementExist(properties.getProperty("Mainchooseaction"), 5)) {
			WebElement mainchoose = driver.findElement(By.xpath(properties.getProperty("Mainchooseaction")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",mainchoose);
			log.logLine(testname, false,"Clicking on MainchooseAction List dropdown..");
			Thread.sleep(1000);

			if (doesElementExist2(properties.getProperty("Mainchooseactionlist"), 5)) {
				if (doesElementExist(properties.getProperty("Release"), 5)){
					WebElement rel = driver.findElement(By.xpath(properties.getProperty("Release")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", rel);
					log.logLine(testname, false,"Clicking on Release now............");

					Thread.sleep(4000);
					if (currentStatus1 != null && CurrentStatus2 != null) {
						if ((currentStatus1.equalsIgnoreCase("Ready") && CurrentStatus2.equalsIgnoreCase("Hold"))
								|| ((CurrentStatus2.equalsIgnoreCase("Ready") && currentStatus1.equalsIgnoreCase("Hold")))||
								((CurrentStatus2.equalsIgnoreCase("Ready") && currentStatus1.equalsIgnoreCase("Ready")))) {

							if (doesElementExist2(properties.getProperty("Releasealert1"), 5)) {
								WebElement relalert = driver.findElement(By.cssSelector(properties.getProperty("Releasealert1")));		
								Thread.sleep(2000);
								Alertcancelbutton(testname);
								if (relalert.getText().contains("All the selected campaigns must have a status of [Hold]")) {
									log.logLine(testname,false,"Validation for Release in main choose action for illegal combinations is successful");
								} else {
									log.logLine(testname,true,"Validation for Release in main choose action for illegal combinations is failed");
								}

								Thread.sleep(5000);
							} }else if (currentStatus1.equalsIgnoreCase("Hold")&& CurrentStatus2.equalsIgnoreCase("Hold")) {
								Thread.sleep(2000);
								if (doesElementExist(properties.getProperty("Releaseconfirm"), 5)) {								
									WebElement relalert = driver.findElement(By.xpath(properties.getProperty("Releaseconfirm")));	
									Thread.sleep(1000);							
									if (relalert.getText().contains("Are you sure you want to release the hold on the campaign(s) selected?")) {
										log.logLine(testname,false,"Validation for Release in main choose action with both 'Hold' combinations is successful");
										Alertokbutton(testname);

									} else {
										log.logLine(testname,true,"Validation for Release in main choose action with both 'Hold' combinations failed");
										Alertokbutton(testname);
									}
									Thread.sleep(12000);}

							}

					}

					log.logLine(testname, false,"Selecting the Campaign Action from the main drop down is successful");

				}
			}
			else {
				log.logLine(testname, true,"Details of mainchooseaction didnot display");

			}
		}
	}	
}
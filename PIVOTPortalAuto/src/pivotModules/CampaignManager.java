package pivotModules;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TimeZone;
import java.awt.AWTException;
import java.awt.Frame;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import launchAuto.Initialization;
import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;



//import com.ibm.icu.util.Calendar;
import com.thoughtworks.selenium.webdriven.commands.WaitForPopup;

public class CampaignManager extends Page {
	public CampaignManager(WebDriver driver, Log log)throws InvalidFormatException, IOException {
		super(driver, log);}

	int paperID = (int) Math.round(Math.random() * (9999 - 1000 + 1) + 1000);
	public String AccNo1 = Integer.toString(paperID); 

	@Override
	protected void load() {
	}

	@Override
	protected void isLoaded() throws Error {
	}

	WebDriverWait wait = new WebDriverWait(driver, 50);

	Wait<WebDriver> waitFluent = new FluentWait<WebDriver>(driver)
			.withTimeout(180, TimeUnit.SECONDS)
			.pollingEvery(5, TimeUnit.SECONDS)
			.ignoring(NoSuchElementException.class,
					StaleElementReferenceException.class);
	Actions action = new Actions(driver);
	int NumOfRowsInTable = 0, M, N, i = 0;
	String StrGridCampaignName, CampaignNameFromGrid, TemplateNameFromGrid,
	CampaignNam, SendKeysSearchTextCampaignName;
	// String CampaignNames[] = new String[NumOfRowsInTable];
	String[] CampaignNames;
	// List<List<String>> CampaignNames;
	String str;
	int row = 0;
	int col = 0;
	int Count = 0;
	int rownum = 0;
	public String ClntName, InPutfileText = null, OutPutFileText = null;
	public String AppId;
	public String AppName, Filename,RecipientName,Recpntnme;
	public File fXmltxtFile, fXmlFile;
	public String TemplateName1;
	public String Rundate;
	public String Finalrundate;
	public boolean CliSelected = false;
	public String campaigncreatedusinguser1 = null;
	public String uid = null;
	public String pwd = null;
	String CampaignNamejustcreated = null;
	String tempnewlycreated=null;
	String inputFile;
	String uploadedfile,user;
	public static String Beforeupdte;
	public static String Afterupdte;
	public String TotalNoofrecpnts;
	Date date1 ;

	//****************************************************************************************************************************

	public boolean CampaignManager_Config1(String Testname)throws Exception {
		InputOutputData test = new InputOutputData();
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object() {
		}.getClass().getEnclosingMethod().getName();
		//Thread.sleep(4000);

		// sign out Manohar

		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false,"Clicking on Cancel button in Client/App popup");} 
		else {
			log.logLine(Testname, true,"Clicking on Cancel button in Client/App popup is failed");
			throw new Exception("Clicking on Cancel button in Client/App popup is failed");
		}

		Thread.sleep(3000);

		if ((doesElementExist2(properties.getProperty("signout"), 5))) {
			WebElement oldpivt = driver.findElement(By.cssSelector(properties.getProperty("signout")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",oldpivt);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Signed out of manohar");}
		else {
			log.logLine(Testname, true,"Unable to click signout button");
			throw new Exception("Unable to click signout button");
		}


		Thread.sleep(3000);

		// Switch to other user

		uid = test.readColumnData("UserID", sheetname);
		pwd = test.readColumnData("Password", sheetname);
		Thread.sleep(2000);

		Loginaction(uid, pwd, Testname);

		Thread.sleep(3000);
		campaigncreatedusinguser1=CampaignManager_AdvanceSearch(AccNo1,Testname);
		log.logLine(Testname, false, "campaign created using user1"+campaigncreatedusinguser1);
		driver.switchTo().defaultContent();

		Thread.sleep(3000);

		if ((doesElementExist2(properties.getProperty("signout"), 5))) {
			WebElement oldpivt = driver.findElement(By.cssSelector(properties.getProperty("signout")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",oldpivt);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Signed out of otheruser");}
		else {
			log.logLine(Testname, true,"Unable to click signout button");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
		}

		// sign in to 'Manohar' again

		Thread.sleep(3000);

		String uid1 = test.readColumnData("UserID1", sheetname);
		String pwd1 = test.readColumnData("Password1", sheetname);

		Loginaction(uid1, pwd1, Testname);

		Thread.sleep(3000);

		return true;
	}


	public boolean CampaignManager_AdvanceSearch_2(String AccNo, String Testname)throws Exception {
		Thread.sleep(2000);

		CampaignManager_Config1(Testname);

		Thread.sleep(1000);
		InputOutputData test = new InputOutputData();
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object() {
		}.getClass().getEnclosingMethod().getName();
		Thread.sleep(4000);
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		ClickeDeliverTab(Testname);
		selectClientApp(Testname);
		ClickCampaignManagerTab(Testname);
		driver.switchTo().frame("iframeContainer");
		Thread.sleep(5000);

		// Search crieteria validation.....starts......

		// .....1.campaign name validation

		log.logLine(Testname, false,"Starting with search criteria: Campaign name ");

		//CampaignNameFromGrid = ReadCampaignNameFromGrid(1);
		//Thread.sleep(5000);
		CampaignNamejustcreated =  NewCampaignManager(Testname,AccNo);
		//String CampaignName= test.readColumnData("campnme",sheetname);
		Thread.sleep(3000);
		ClickAdvanceSearchBtn(Testname);
		String CampaignNameSearched = SearchByCampaignName(Testname,CampaignNamejustcreated);
		Thread.sleep(5000);
		ClickAdvanceSearch_SearchBtn(Testname);
		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Alertpopup"), 5)) {
			WebElement eDelive = driver.findElement(By.cssSelector(properties.getProperty("Alertpopup")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",eDelive);
			log.logLine(Testname, false, "Click on Alert pop up is Successful");
		} else {
			log.logLine(Testname, true, "Click on Alert pop up is failed");
			throw new Exception("Click on Alert pop up is failed");
		}
		Thread.sleep(6000);
		//positive validation
		campainnamevalidation(Testname,CampaignNamejustcreated);
		Thread.sleep(2000);
		//negative validation
		String[] campnegative=CampaignNamejustcreated.split(" ");
		log.logLine(Testname, false, "campname for negative validation is"+campnegative[1]);
		campainnamevalidation1(Testname,campnegative[1]);
		Thread.sleep(2000);

		// .....2.Status validation.....

		log.logLine(Testname, false, "Starting with search criteria: Status ");

		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		ClickeDeliverTab(Testname);
		selectClientApp(Testname);
		ClickCampaignManagerTab(Testname);
		driver.switchTo().frame("iframeContainer");
		Thread.sleep(2000);
		ClickAdvanceSearchBtn(Testname);
		String Stat = test.readColumnData("Status", sheetname);
		Thread.sleep(2000);
		Searchcriteria_status(Testname, Stat);
		ClickAdvanceSearch_SearchBtn(Testname);
		Defaultpopup(Testname);
		Thread.sleep(5000);
		Statusvalidation(Testname, Stat);
		Thread.sleep(2000);

		// .....3.Template name validation.....

		log.logLine(Testname, false,"Starting with search criteria:Templatename ");

		driver.switchTo().defaultContent();
		ClickCampaignManagerTab(Testname);
		driver.switchTo().frame("iframeContainer");

		// TemplateNameFromGrid = ReadTemplateNameFromGrid(1) ;
		//String Templatenamejustcreated = test.readColumnData("TemplateName",sheetname);
		ClickAdvanceSearchBtn(Testname);
		Thread.sleep(3000);

		String TemplateNameSearched = SearchByTemplateName(Testname,tempnewlycreated);
		ClickAdvanceSearch_SearchBtn(Testname);
		Thread.sleep(6000);
		if (doesElementExist2(properties.getProperty("Alertpopup"), 5)) {
			WebElement eDelive = driver.findElement(By.cssSelector(properties.getProperty("Alertpopup")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",eDelive);
			log.logLine(Testname, false, "Click on Alert pop up is Successful");
		} else {
			log.logLine(Testname, true, "Click on Alert pop up is failed");
			throw new Exception("Click on Alert pop up is failed");
		}

		Thread.sleep(6000);
		if (doesElementExist(properties.getProperty("temname"), 5)) {
			int NoOfRowsInGridAfterSearch1 = CalculateNumOfRowsInTable(Testname);
			String[] AllTemplatenameFromGrid = ReadAllTemplatenameFromGrid(Testname, NoOfRowsInGridAfterSearch1);
			Thread.sleep(2000);
			compareTemplateNameInSearchAndGrid(Testname,NoOfRowsInGridAfterSearch1, AllTemplatenameFromGrid,TemplateNameSearched);

		}


		// .....4.Created by validation.....

		log.logLine(Testname, false,"Starting with search criteria:Created By ");
		driver.switchTo().defaultContent();
		ClickCampaignManagerTab(Testname);
		driver.switchTo().frame("iframeContainer");
		Thread.sleep(2000);
		ClickAdvanceSearchBtn(Testname);
		String Createdbyname = SearchByCreateduser(Testname, uid);
		Thread.sleep(5000);
		ClickAdvanceSearch_SearchBtn(Testname);
		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Alertpopup"), 5)) {
			WebElement eDelive = driver.findElement(By.cssSelector(properties.getProperty("Alertpopup")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",eDelive);
			log.logLine(Testname, false, "Click on Alert pop up is Successful");
		} else {
			log.logLine(Testname, true, "Click on Alert pop up is failed");
			throw new Exception("Click on Alert pop up is failed");
		}
		//String CampaignName1= test.readColumnData("campnme",sheetname);
		Thread.sleep(6000);


		if (doesElementExist(properties.getProperty("camname"), 5)) {
			int NoOfRowsInGridAfterSearch = CalculateNumOfRowsInTable(Testname);
			String[] AllCampaignNamesFromGrid = ReadAllCampaignNameFromGrid(Testname,NoOfRowsInGridAfterSearch);
			Thread.sleep(3000);
			compareCampaignNameInSearchAndGrid1(Testname,NoOfRowsInGridAfterSearch, AllCampaignNamesFromGrid, campaigncreatedusinguser1);
		}

		// Search criteria validation.....ends......

		// Run date validation.......starts...

		log.logLine(Testname, false, "Advance search:Run date");

		driver.switchTo().defaultContent();
		ClickCampaignManagerTab(Testname);
		Thread.sleep(2000);
		driver.switchTo().frame("iframeContainer");
		Thread.sleep(3000);
		ClickAdvanceSearchBtn(Testname);
		Thread.sleep(3000);
		String Rndte = test.readColumnData("RuStartDate", sheetname);
		// String Rndte= test.readColumnData("RuStartDate", sheetname);
		log.logLine(Testname, false, "runstartdate is " + Rndte);
		SimpleDateFormat gmtDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		gmtDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
		String testdata2 = gmtDateFormat.format(new Date());
		// String testdata= "11/10/2016";
		Thread.sleep(2000);
		AdvanceSrch_rundate(Testname, Rndte, testdata2);
		Thread.sleep(2000);
		ClickAdvanceSearch_SearchBtn(Testname);
		rundatevalidation(Testname, Rndte, testdata2);

		// scheduled date validation.......starts...

		log.logLine(Testname, false, "Advance search:scheduled date");

		driver.switchTo().defaultContent();
		ClickCampaignManagerTab(Testname);
		Thread.sleep(2000);
		driver.switchTo().frame("iframeContainer");
		Thread.sleep(3000);
		ClickAdvanceSearchBtn(Testname);
		Thread.sleep(3000);
		String data2 = test.readColumnData("Scheduledenddate", sheetname);
		//String data2 = "01/01/17";
		//String data2 = "01/01/2017";
		SimpleDateFormat gmtDateFormat1 = new SimpleDateFormat("MM/dd/yyyy");
		gmtDateFormat1.setTimeZone(TimeZone.getTimeZone("IST"));
		String data1 = gmtDateFormat1.format(new Date());
		Thread.sleep(2000);
		AdvanceSrch_scheduleddate(Testname, data1, data2);
		Thread.sleep(2000);
		ClickAdvanceSearch_SearchBtn(Testname);
		scheduleddatevalidation(Testname, data1, data2);

		// Scheduled date validation.......ends... 


		// Created date validation.......starts...


		log.logLine(Testname, false, "Advance search:Created date");
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		ClickCampaignManagerTab(Testname);
		Thread.sleep(2000);
		driver.switchTo().frame("iframeContainer");
		Thread.sleep(3000);
		ClickAdvanceSearchBtn(Testname);
		Thread.sleep(3000);

		SimpleDateFormat gmtDateFormat2 = new SimpleDateFormat("MM/dd/yyyy");
		gmtDateFormat2.setTimeZone(TimeZone.getTimeZone("IST"));
		String tdata1 = gmtDateFormat2.format(new Date());
		String tdata2 = gmtDateFormat2.format(new Date());
		Thread.sleep(2000);
		AdvanceSrch_createddate(Testname, tdata1, tdata2);
		Thread.sleep(2000);
		ClickAdvanceSearch_SearchBtn(Testname);
		createddatevalidation(Testname, tdata1, tdata2);
		// Created date validation.......ends...
		//Deleting few campaigns created ... starts...

		driver.switchTo().defaultContent();
		ClickCampaignManagerTab(Testname);
		Thread.sleep(2000);
		driver.switchTo().frame("iframeContainer");
		Thread.sleep(4000);
		DeleteCampaignAction(Testname,CampaignNamejustcreated);
		Thread.sleep(6000);
		DeleteCampaignAction(Testname,campaigncreatedusinguser1);

		//Deleting few campaigns created ....ends....	
		log.logLine(Testname, false, "End of advance search2");
		return true;
	}

	private void campainnamevalidation(String Testname, String CampaignName)throws Exception {
		if (doesElementExist(properties.getProperty("camname"), 5)) {
			int NoOfRowsInGridAfterSearch = CalculateNumOfRowsInTable(Testname);
			String[] AllCampaignNamesFromGrid = ReadAllCampaignNameFromGrid(Testname,NoOfRowsInGridAfterSearch);
			Thread.sleep(3000);
			compareCampaignNameInSearchAndGrid(Testname,NoOfRowsInGridAfterSearch, AllCampaignNamesFromGrid,CampaignName);
		}}

	private void campainnamevalidation1(String Testname, String CampaignName)throws Exception {
		if (doesElementExist(properties.getProperty("camname"), 5)) {
			int NoOfRowsInGridAfterSearch = CalculateNumOfRowsInTable(Testname);
			String[] AllCampaignNamesFromGrid = ReadAllCampaignNameFromGrid(Testname,NoOfRowsInGridAfterSearch);
			Thread.sleep(3000);
			compareCampaignNameInSearchAndGrid2(Testname,NoOfRowsInGridAfterSearch, AllCampaignNamesFromGrid,CampaignName);
		}}

	public void DeleteCampaignAction(String Testname,String camnmetodelete)throws Exception {
		String[] Sort1 = new String[50];
		String row = "tr";
		//CampaignNamejustcreated="AutoCmpain4220";
		if (doesElementExist(properties.getProperty("grid"), 5)) {
			List<WebElement> DataCnt = driver.findElements(By.xpath(properties.getProperty("grid")));
			for (int i = 0,k=1; i < (DataCnt.size()); i++,k++) {
				String cmnname1 = driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/table/tbody/tr["+k+"]/td[3]")).getText();
				if (cmnname1.equalsIgnoreCase(camnmetodelete)){
					WebElement cpnCombo = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ row+ " td+td+td+td+td+td+td+td span span span[class='k-input']"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()",cpnCombo);
					Thread.sleep(4000);
					log.logLine(Testname, false, "Clicking on Action List dropdown..");
					Thread.sleep(5000);
					if (doesElementExist2(properties.getProperty("CmpnActionListbox"),5)) {
						List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("CmpnActionListbox")));
						for (WebElement lnk : selopts) {
							if (lnk.getText().contains("Delete")) {
								lnk.click();
								Thread.sleep(6000);
								log.logLine(Testname, false,"Selecting the Campaign Delete Action from the drop down is successful");
								CliSelected = true;
							}
						}
					} else {
						log.logLine(Testname, true,"Actions on Campaign are not are not displayed");
						throw new Exception("Actions on Campaign are not displayed");
					}

					Thread.sleep(3000);
					Alert alert = driver.switchTo().alert();
					if (alert.getText().contains("delete")) {
						log.logLine(Testname, false,"Selecting the Campaign Action 'Delete' from the drop down is successful");
						alert.accept();
						break;
					} 
					else {
						log.logLine(Testname, true,"Selecting the Campaign Action 'Delete' from the drop down is not successful");
					}

				}
				row = row + "+tr";
			}
		}
		else {
			log.logLine(Testname, true,"grid not displayed");
			throw new Exception("grid not displayed");
		}
		Thread.sleep(4000);
		return;
	}

	public  void signout(String Testname)throws Exception{
		if ((doesElementExist2(properties.getProperty("signout"), 5))) {
			WebElement oldpivt = driver.findElement(By.cssSelector(properties.getProperty("signout")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",oldpivt);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Signed out of manohar");}
		else {
			log.logLine(Testname, true,"Unable to click signout button");
			throw new Exception("Unable to click signout button");
		}
	}
	private void Loginaction(String uid, String pwd, String Testname)throws Exception {

		Thread.sleep(1000);

		if ((doesElementExist2(properties.getProperty("user1"), 5))){
			WebElement UserGroupIdTxtBx = driver.findElement(By.cssSelector(properties.getProperty("user1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",UserGroupIdTxtBx);
			UserGroupIdTxtBx.sendKeys(uid);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Entered userid in login screen");}
		else {
			log.logLine(Testname, true,"userid field doesnot exist");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
		}

		Thread.sleep(3000);

		if ((doesElementExist2(properties.getProperty("pswd"), 5))) {
			WebElement UserGroupIdTxtBx = driver.findElement(By.cssSelector(properties.getProperty("pswd")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",UserGroupIdTxtBx);
			UserGroupIdTxtBx.sendKeys(pwd);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Entered password in login screen");
		}
		else {
			log.logLine(Testname, true,"pswd field doesnot exist");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
		}

		Thread.sleep(3000);

		if ((doesElementExist2(properties.getProperty("logon"), 5))) {
			WebElement oldpivt = driver.findElement(By.cssSelector(properties.getProperty("logon")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",oldpivt);
			Thread.sleep(3000);
			log.logLine(Testname, false, "logged into new user");
		}
		else {
			log.logLine(Testname, true,"logon field doesnot exist");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
		}

	}

	public String CampaignManager_AdvanceSearch(String AccNo1,String Testname)throws Exception {

		InputOutputData test = new InputOutputData();
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object() {
		}.getClass().getEnclosingMethod().getName();
		Thread.sleep(4000);

		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		user = driver.findElement(By.cssSelector(properties.getProperty("userid"))).getText();

		ClickeDeliverTab1(Testname);
		selectClientApp(Testname);
		ClickCampaignManagerTab(Testname);
		driver.switchTo().frame("iframeContainer");
		Thread.sleep(5000);
		String campnme =NewCampaignManager(Testname,AccNo1);

		return campnme  ;

	}

	private void createddatevalidation(String Testname, String tdata1,String tdata2) throws Exception {
		if (doesElementExist2(properties.getProperty("sectionnme"), 5)) {
			Thread.sleep(1000);
			if (doesElementExist(properties.getProperty("camname"), 5)) {
				int NoOfRowsInGridAfterSearch = CalculateNumOfRowsInTable(Testname);
				String[] AllCampaignNamesFromGrid = ReadAllCampaignNameFromGrid(Testname, NoOfRowsInGridAfterSearch);
				compareCampaignNameInSearchAndGrid(Testname,NoOfRowsInGridAfterSearch, AllCampaignNamesFromGrid,CampaignNamejustcreated);
				log.logLine(Testname, false,"Campaign created date has been validated successfully");
			} 
			else if (doesElementExist2(properties.getProperty("invliddatealert"), 5)) {
				Thread.sleep(2000);
				if (doesElementExist2(properties.getProperty("alertclose"), 5)) {
					WebElement alertclose = driver.findElement(By.cssSelector(properties.getProperty("alertclose")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", alertclose);
					Thread.sleep(1000);
					log.logLine(Testname, false,"please enter valid date in MMDDYYYY format");}}

			else if (doesElementExist2(properties.getProperty("futuredate"), 5)) {
				int NoOfRowsInGridAfterSearch2 = CalculateNumOfRowsInTable(Testname);
				WebElement Nodata= driver.findElement(By.cssSelector(properties.getProperty("futuredate")));
				String Nodata1=Nodata.getText();
				if(Nodata1=="No items to display"||NoOfRowsInGridAfterSearch2==0)
				{
					log.logLine(Testname, false,"Sorry no data available to display in this range");
				}}}
		else {
			log.logLine(Testname, true,"created datevalidation failed");
			throw new Exception("created datevalidation failed");}	}	


	private void scheduleddatevalidation(String Testname, String data1,String data2) throws Exception {
		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("sectionnme"), 5)) {
			Thread.sleep(1000);
			if (doesElementExist(properties.getProperty("schdt"), 5)) {
				int NoOfRowsInGridAfterSearch2 = CalculateNumOfRowsInTable(Testname);
				String[] AllschdateFromGrid = ReadAllschdateFromGrid(Testname,NoOfRowsInGridAfterSearch2);
				compareschdtInSearchAndGrid(Testname, NoOfRowsInGridAfterSearch2,AllschdateFromGrid, data1, data2);
				log.logLine(Testname, false,"Campaign scheduled date has been validated successfully");} 
			else if (doesElementExist2(properties.getProperty("invliddatealert"), 5)) {
				log.logLine(Testname, false,
						"please enter valid date in MMDDYYYY format");
				if (doesElementExist2(properties.getProperty("alertclose"), 5)) {
					WebElement alertclose = driver.findElement(By.cssSelector(properties.getProperty("alertclose")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", alertclose);
					Thread.sleep(1000);}}
			else if (doesElementExist2(properties.getProperty("futuredate"), 5)) {
				int NoOfRowsInGridAfterSearch2 = CalculateNumOfRowsInTable(Testname);
				WebElement Nodata= driver.findElement(By.cssSelector(properties.getProperty("futuredate")));
				String Nodata1=Nodata.getText();
				if(Nodata1=="No items to display"||NoOfRowsInGridAfterSearch2==0)
				{
					log.logLine(Testname, false,"Sorry no data available to display in this range");
				}}}
		else {
			log.logLine(Testname, true,"scheduleddatevalidation failed");
			throw new Exception("scheduleddatevalidation failed");}	
	}


	private void rundatevalidation(String Testname, String testdata1,String testdata2) throws Exception {
		if (doesElementExist2(properties.getProperty("sectionnme"), 5)) {
			Thread.sleep(2000);
			if (doesElementExist(properties.getProperty("rundt"), 5)) {
				WebElement data= driver.findElement(By.xpath(properties.getProperty("rundt")));
				if(data.getText().equalsIgnoreCase(" ")){
					log.logLine(Testname, false,"Sorry no data available to display in this rundate range");
				}
				else{
					int NoOfRowsInGridAfterSearch2 = CalculateNumOfRowsInTable(Testname);
					String[] AllRundateFromGrid = ReadAllrundateFromGrid(Testname,NoOfRowsInGridAfterSearch2);
					comparerundateInSearchAndGrid(Testname, NoOfRowsInGridAfterSearch2,AllRundateFromGrid, testdata1, testdata2);
					log.logLine(Testname, false,"Campaign run date has been validated successfully");}}
			else if (doesElementExist2(properties.getProperty("Nodata"), 5)) {
				int NoOfRowsInGridAfterSearch2 = CalculateNumOfRowsInTable(Testname);
				WebElement Nodata= driver.findElement(By.cssSelector(properties.getProperty("Nodata")));
				String Nodata1=Nodata.getText();
				if(Nodata1=="No items to display"||NoOfRowsInGridAfterSearch2==0)
				{
					log.logLine(Testname, false,"Sorry no data available to display in this range");
				}}	
			else if (doesElementExist2(properties.getProperty("invliddatealert"), 5)) {
				log.logLine(Testname, false,
						"please enter valid date in MMDDYYYY format");
				if (doesElementExist2(properties.getProperty("alertclose"), 5)) {
					WebElement alertclose = driver.findElement(By.cssSelector(properties.getProperty("alertclose")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", alertclose);
					Thread.sleep(1000);}
			}
		}
		else {
			log.logLine(Testname, true,"runddatevalidation failed");
			throw new Exception("rundatevalidation failed");}	}


	private void AdvanceSrch_createddate(String Testname, String tdata1,String tdata2) throws Exception {
		if (doesElementExist2(properties.getProperty("createdFromDate"), 5)) {
			WebElement DteFromfld = driver.findElement(By.cssSelector(properties.getProperty("createdFromDate")));
			DteFromfld.clear();
			Thread.sleep(1000);
			DteFromfld.sendKeys(tdata1);
			log.logLine(Testname, false,"Entering the from date value in created date advanced search");
			WebElement DteTofld = driver.findElement(By.cssSelector(properties.getProperty("createdToDate")));
			DteTofld.clear();
			Thread.sleep(1000);
			DteTofld.sendKeys(tdata2);
			log.logLine(Testname, false,"Entering the To date value in created date  advanced search");
		}
		else {
			log.logLine(Testname, true,"createddate fields doesnot exists");
			throw new Exception("createdDate fields doesnot exists");}


	}

	public String SearchByCreateduser(String Testname, String uid)throws Exception {
		if (doesElementExist2(properties.getProperty("srchcrit"),	5)) {
			WebElement drpdownCampaignName = driver.findElement(By.cssSelector(properties.getProperty("srchcrit")));
			Highlight(drpdownCampaignName);
			drpdownCampaignName.click();
			List<WebElement> listSerachCriteria = driver.findElements(By.cssSelector(("div ul li[class='k-item']")));
			for (WebElement li : listSerachCriteria) {
				if (li.getText().equalsIgnoreCase("Created By")) {
					Thread.sleep(2000);
					li.click();
					break;
				}
			}
			WebElement Txt = driver.findElement(By.xpath(properties.getProperty("srchcrittxtbx")));
			Txt.sendKeys(uid);
			SendKeysSearchTextCampaignName = Txt.getText();
			log.logLine(Testname, false,"Click on Search By  Created by in the dropdown successful");
		} else {
			log.logLine(Testname, true,"Click on Search By 'Created by' in the dropdown failed");
			throw new Exception("Click on Search By 'Created by' in the dropdown failed");
		}
		return uid;
	}

	private void compareschdtInSearchAndGrid(String testname,int noOfRowsInGridAfterSearch2, String[] allrundateFromGrid,String data1, String data2) throws Exception {
		for (M = 0; M <= noOfRowsInGridAfterSearch2 - 1; M++) {
			if (doesElementExist(properties.getProperty("schdt"), 5)) {

				try {
					String array[] = allrundateFromGrid[M].split(",");
					String array1[] = array[0].split(" ");
					String dte = array1[0].trim();

					if ((dte.compareTo(data1)>= 0) && (dte.compareTo(data2) <= 0)) {

						// ReadAllrundateFromGrid(testname, M);
						if (M == noOfRowsInGridAfterSearch2 - 1) {
							log.logLine(testname, false,"schdt in the grid is between specified range of advance search");

						}
					}

				}

				catch (StaleElementReferenceException e) {

				} catch (NullPointerException e1) {

				}
			}

		}
	}

	private String[] ReadAllschdateFromGrid(String testname,int noOfRowsInGridAfterSearch2) throws Exception {
		Thread.sleep(2000);
		String[] schdates = new String[noOfRowsInGridAfterSearch2];
		for (int N = 0, i = 0, k = 1; N <= (noOfRowsInGridAfterSearch2 - 1); N++, i++, k++) {
			if (doesElementExist(properties.getProperty("schdt"), 5)) {	

				WebElement schdate = driver.findElement(By.xpath((".//*[@id='management-campaigns-grid']/table/tbody/tr["+ k + "]/td[1]")));
				schdates[i] = schdate.getText();
				// log.logLine(testname, false, "rundates in the grid is "+
				// rundates[i] );

			} else {
				log.logLine(testname, true,"unable to read scheduled date lines");
				throw new Exception("unable to read scheduled date lines");

			}
		}

		return schdates;
	}

	// New campaign creation starts...

	private String NewCampaignManager(String Testname,String Accno) throws Exception {

		InputOutputData test = new InputOutputData();
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname1 = new Object() {
		}.getClass().getEnclosingMethod().getName();
		Thread.sleep(4000);
		// Here we are verifying new campaign button functionality
		NewCmpaignBtn(Testname);

		String camnname="AutoCmpain " + Accno;

		if (doesElementExist2(properties.getProperty("cmpaignNme"), 5)) {
			WebElement uploadfile = driver.findElement(By.cssSelector(properties.getProperty("cmpaignNme")));
			Thread.sleep(1000);
			uploadfile.sendKeys(camnname);
			log.logLine(Testname, false,"Clicking on New campaign button is successful");
		} else {
			log.logLine(Testname, true,"Clicking on New campaign button failed");


			if(Initialization.UserID.contains(user))
			{throw new Exception("Clicking on Manage Recipients button is not successful");}
			{log.logLine(Testname, true, "Signing out of this user");
			signout(Testname);
			Thread.sleep(2000);
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
			throw new Exception("Clicking on Manage Recipients button is not successful");
			}
		}

		String reciName = test.readColumnData("RecipientName", sheetname1);

		if (doesElementExist2(properties.getProperty("cmpainReciList"), 5)) {
			WebElement reciSel = driver.findElement(By.cssSelector(properties.getProperty("cmpainReciList")));
			reciSel.click();
			// reciSel.sendKeys("CM_Recipient_AutoTest");
			Thread.sleep(1000);
			if (doesElementExist2(properties.getProperty("ReciList"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ReciList")));
				for (WebElement lnk : selopts) {
					if (lnk.getText().contains("CM_Recipient_AutoTest")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(2000);
						log.logLine(Testname, false,"Selecting the ClientName s from the popup..");
						CliSelected = true;
						break;
					}
				}

			}
			// reciSel.sendKeys(Keys.ENTER);
			log.logLine(Testname, false,"Entering Recipient List name in the combo field is successful");
		} else {
			log.logLine(Testname, true,"Entering Recipient List name in the combo field failed");
			if(Initialization.UserID.contains(user))
			{throw new Exception("Entering Recipient List name in the combo field failed");}
			{log.logLine(Testname, true, "Signing out of this user");
			signout(Testname);
			Thread.sleep(2000);
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
			throw new Exception("Entering Recipient List name in the combo field failed");
			}
		}

		Thread.sleep(1000);
		if (doesElementExist2(properties.getProperty("cmpainNotes"), 5)) {
			WebElement reciSel = driver.findElement(By.cssSelector(properties.getProperty("cmpainNotes")));
			reciSel.clear();
			reciSel.sendKeys("This is automation regression testing.");
			log.logLine(Testname, false,"Entering Recipient List name in the combo field is successful");
		} else {
			log.logLine(Testname, true,"Entering Recipient List name in the combo field is failed");
			if(Initialization.UserID.contains(user))
			{throw new Exception("Entering Recipient List name in the combo field is failed");}
			{log.logLine(Testname, true, "Signing out of this user");
			signout(Testname);
			Thread.sleep(2000);
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
			throw new Exception("Entering Recipient List name in the combo field is failed");
			}
		}

		Thread.sleep(3000);
		String TempName = test.readColumnData("TemplateName", sheetname1);
		Thread.sleep(2000);

		if (doesElementExist2(properties.getProperty("TempCombo"), 5)) {
			WebElement reciSel = driver.findElement(By.cssSelector(properties.getProperty("TempCombo")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",reciSel);
			Thread.sleep(3000);
			log.logLine(Testname, false,"Clicking on Template Name List dropdown..");

			if (doesElementExist2(properties.getProperty("TempList"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("TempList")));
				for (WebElement lnk : selopts) {
					if (lnk.getText().contains(TempName)) {
						Thread.sleep(2000);
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false,"Selecting the ClientName " + TempName	+ " from the popup..");
						CliSelected = true;
						break;
					}
				}

			} else {
				log.logLine(Testname, true,	"Template Name options are not displayed");
				if(Initialization.UserID.contains(user))
				{throw new Exception("Template Name options are not displayed");}
				{log.logLine(Testname, true, "Signing out of this user");
				signout(Testname);
				Thread.sleep(2000);
				log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
				Relogin(Testname);
				throw new Exception("Template Name options are not displayed");
				}
			}
			tempnewlycreated=TempName;
		}

		CampaignManagerFields(Testname);

		return camnname;
	}

	public boolean NewCmpaignBtn(String Testname) throws Exception {
		if (doesElementExist(properties.getProperty("newCampaign"), 5)) {
			WebElement recibtn = driver.findElement(By.xpath(properties.getProperty("newCampaign")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",recibtn);
			Thread.sleep(1000);
			
			log.logLine(Testname, false,"Clicking on New Campaign button is successful");
		} else {
			log.logLine(Testname, true,"Clicking on New Campaign button is not successful");
			if(Initialization.UserID.contains(user))
			{throw new Exception("Clicking on New Campaign button is failed");}
			{log.logLine(Testname, true, "Signing out of this user");
			signout(Testname);
			Thread.sleep(2000);
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
			throw new Exception("Clicking on New Campaign button is failed");
			}
		}
		return true;
	}

	public void CampaignManagerFields(String Testname) throws Exception {

		Date date = new Date();
		String DATE_FORMAT = "MM/dd/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

		if (doesElementExist2(properties.getProperty("ScheduleStrtDate"), 5)) {
			WebElement dateSel = driver.findElement(By.cssSelector(properties.getProperty("ScheduleStrtDate")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",dateSel);
			Thread.sleep(500);
			dateSel.clear();
			dateSel.sendKeys("01/01/2020");
			// dateSel.sendKeys(sdf.format(date));
			log.logLine(Testname, false,"Entering Schedule Date field is successful");
		} else {
			log.logLine(Testname, true,"Entering Schedule Date field is not successful");
			if(Initialization.UserID.contains(user))
			{throw new Exception("Entering Schedule Date field is failed");}
			{log.logLine(Testname, true, "Signing out of this user");
			signout(Testname);
			Thread.sleep(2000);
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
			throw new Exception("Entering Schedule Date field is failed");
			}
		}

		if (doesElementExist2(properties.getProperty("ScheduleTimeEdit"), 5)) {
			WebElement dateSel = driver.findElement(By.cssSelector(properties.getProperty("ScheduleTimeEdit")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",dateSel);
			Thread.sleep(500);
			dateSel.clear();
			dateSel.sendKeys("11:30 PM");
			log.logLine(Testname, false,"Entering Schedule Time field is successful");
		} else {
			log.logLine(Testname, true,"Entering Schedule Time field is not successful");
			if(Initialization.UserID.contains(user))
			{throw new Exception("Entering Schedule Time field is failed");}
			{log.logLine(Testname, true, "Signing out of this user");
			signout(Testname);
			Thread.sleep(2000);
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
			throw new Exception("Entering Schedule Time field is failed");
			}
		}

		String TempName="Document Notification";

		if (doesElementExist2(properties.getProperty("TempCombo"), 5)) {
			WebElement reciSel = driver.findElement(By.cssSelector(properties.getProperty("TempCombo")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",reciSel);
			Thread.sleep(3000);
			log.logLine(Testname, false,"Clicking on Template Name List dropdown..");

			if (doesElementExist2(properties.getProperty("TempList"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("TempList")));
				for (WebElement lnk : selopts) {
					if (lnk.getText().contains(TempName)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false,"Selecting the ClientName " + TempName+ " from the popup..");
						CliSelected = true;
						break;
					}
				}
			}

		} else {
			log.logLine(Testname, true,"Template Name options are not displayed");
			throw new Exception("Template Name options are not displayed");
		}


		if (doesElementExist2(properties.getProperty("cmpainNotes"), 5)) {
			WebElement reciSel = driver.findElement(By.cssSelector(properties.getProperty("cmpainNotes")));
			reciSel.clear();
			reciSel.sendKeys("This is automation regression testing.");
			log.logLine(Testname, false,"Entering Recipient List name in the combo field is successful");
		} else {
			log.logLine(Testname, true,"Entering Recipient List name in the combo field is not successful");
			if(Initialization.UserID.contains(user))
			{throw new Exception("Entering Recipient List name in the combo field failed");}
			{log.logLine(Testname, true, "Signing out of this user");
			signout(Testname);
			Thread.sleep(2000);
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
			throw new Exception("Entering Schedule Time field is failed");
			}
		}

		Thread.sleep(3000);

		if (doesElementExist2(properties.getProperty("cmpainSave"), 5)) {
			WebElement cmpSave = driver.findElement(By.cssSelector(properties	.getProperty("cmpainSave")));
			// cmpSave.click();
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",cmpSave);
			Thread.sleep(2000);
			log.logLine(Testname, false,"Creation of new Campaign Manager is successful");
		} else {
			log.logLine(Testname, true,"Creation of new Campaign Manager is not successful");
			if(Initialization.UserID.contains(user))
			{throw new Exception("Creation of new Campaign Manager failed");}
			{log.logLine(Testname, true, "Signing out of this user");
			signout(Testname);
			Thread.sleep(2000);
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
			throw new Exception("Creation of new Campaign Manager failed");
			}
		}

		// return true;
	}



	// New campaign creation ends...

	private void AdvanceSrch_scheduleddate(String Testname, String data1,String data2) throws Exception {
		if (doesElementExist2(properties.getProperty("schFromDate"), 5)) {
			WebElement DteFromfld = driver.findElement(By.cssSelector(properties.getProperty("schFromDate")));
			DteFromfld.clear();
			Thread.sleep(1000);
			DteFromfld.sendKeys(data1);
			log.logLine(Testname, false,"Entering the from date value in scheduled date advanced search");

			WebElement DteTofld = driver.findElement(By.cssSelector(properties.getProperty("schToDate")));
			DteTofld.clear();
			Thread.sleep(1000);
			DteTofld.sendKeys(data2);
			log.logLine(Testname, false,"Entering the To date value in scheduled date  advanced search");
		} else {
			log.logLine(Testname, true,"Entering Schedule dates is not successful");
			throw new Exception("Entering Schedule dates is not successful");}

	}

	private void comparerundateInSearchAndGrid(String testname,	int noOfRowsInGridAfterSearch2, 
			String[] allrundateFromGrid,String testdata1, String testdata2) throws Exception {
		for (M = 0; M <= noOfRowsInGridAfterSearch2 - 1; M++) {
			waitFluent.until(ExpectedConditions.presenceOfElementLocated(By.xpath(properties.getProperty("rundt"))));
			try {
				String array[] = allrundateFromGrid[M].split(",");
				String array1[] = array[0].split(" ");
				String dte = array1[0].trim();
				System.out.println("dte :"+dte);
				System.out.println("startdate :"+testdata1);
				System.out.println("enddate :"+testdata2);
				Date Griddate,startdt,enddt;


				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				Griddate = df.parse(dte);
				startdt = df.parse(testdata1);
				enddt= df.parse(testdata2);


				if ((Griddate.compareTo(startdt) >= 0) && (Griddate.compareTo(enddt) <= 0)) {
					ReadAllrundateFromGrid(testname, M);
					if (M == noOfRowsInGridAfterSearch2 - 1) {
						log.logLine(testname, false,"rundate in the grid is between specified range of advance search");

					}
				} else {
					log.logLine(testname, true,"rundates validation failed");
					//throw new Exception("rundates validation failed");
				}


			}

			catch (StaleElementReferenceException e) {

			} catch (NullPointerException e1) {

			}
		}

	}

	
	private String[] ReadAllrundateFromGrid(String testname,int noOfRowsInGridAfterSearch2) throws Exception {
		String[] rundates = new String[noOfRowsInGridAfterSearch2];
		for (int N = 0, i = 0, k = 1; N <= (noOfRowsInGridAfterSearch2 - 1); N++, i++, k++) {
			if (doesElementExist(properties.getProperty("rundt"), 5)) 
			{
				WebElement rundate = driver.findElement(By.xpath((".//*[@id='management-campaigns-grid']/table/tbody/tr["+ k + "]/td[2]")));
				rundates[i] = rundate.getText();
				// log.logLine(testname, false, "rundates in the grid is "+
				// rundates[i] );

			} else {
				log.logLine(testname, true, "unable to read lines");
				throw new Exception("unable to read lines");
			}
		}

		return rundates;
	}

	private void AdvanceSrch_rundate(String Testname, String testdata1,String testdata2) throws Exception {
		if (doesElementExist2(properties.getProperty("RunFromDate"), 5)) {
			WebElement DteFromfld = driver.findElement(By.cssSelector(properties.getProperty("RunFromDate")));
			DteFromfld.clear();
			Thread.sleep(1000);
			DteFromfld.sendKeys(testdata1);
			log.logLine(Testname, false,"Entering the from date value in advanced search");

			WebElement DteTofld = driver.findElement(By.cssSelector(properties.getProperty("RunToDate")));
			DteTofld.clear();
			Thread.sleep(1000);
			DteTofld.sendKeys(testdata2);
			log.logLine(Testname, false,"Entering the To date value in advanced search");
		}
		else {
			log.logLine(Testname, true,"entering rundates failed");
			throw new Exception("entering rundates failed");
		}


	}

	public String[] ReadAllTemplatenameFromGrid(String Testname,int NoOfRowsInGridAfterSearch) throws Exception {
		String[] Templatenames = new String[NoOfRowsInGridAfterSearch];
		for (N = 0, i = 0; N <= NoOfRowsInGridAfterSearch; N++, i++) {
			if (N == 0) {
				if (doesElementExist(properties.getProperty("temname"), 5))
				{
					try {
						WebElement Templatename = driver.findElement(By.xpath(properties.getProperty("temname")));

						Templatenames[i] = Templatename.getText();
						log.logLine(Testname, false,"Template name in the grid is "+ Templatenames[i]);
					} catch (StaleElementReferenceException e) {

					} catch (NullPointerException e1) {

					}
				}
			} else {
				if (doesElementExist(("html/body/div[1]/div[2]/div[2]/table/tbody/tr[N]/td[6]"), 5))
				{
					WebElement Templatename = driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/table/tbody/tr[N]/td[6]"));
					Templatenames[i] = Templatename.getText();
				}
			}
		}
		return Templatenames;
	}

	private void Statusvalidation(String testname, String testdata1)throws Exception {
		String[] Sort1 = new String[50];
		String row = "tr";
		List<WebElement> DataCnt = driver.findElements(By.xpath(properties.getProperty("grid")));

		if (doesElementExist2(properties.getProperty("AccountNumber"), 5)) {
			for (int i = 0; i < DataCnt.size(); i++) {
				Sort1[i] = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ row + " td+td+td+td[role='gridcell']")).getText();
				if (Sort1[i].equals(testdata1)) {
					log.logLine(testname, false,"The status entered in search criteria matched "	+ Sort1[i]);
					Thread.sleep(2000);
					break;

				} else {
					log.logLine(testname, false,	"The status entered in search criteria didn't match "+ Sort1[i]);

				}

				row = row + "+tr";
				log.logLine(testname, false,"Iterating through the Rows....Rows Have the Status as "+ Sort1[i]);

			}

		}

		return;
	}

	private void Defaultpopup(String testname) throws Exception {
		if (doesElementExist2(properties.getProperty("Alertpopup"), 5)) {
			WebElement eDelive = driver.findElement(By.cssSelector(properties.getProperty("Alertpopup")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",eDelive);
			log.logLine(testname, false, "Click on Alert pop up is Successful");
		} else {
			log.logLine(testname, true, "Click on Alert pop up is failed");
			throw new Exception("Click on Alert pop up is failed");
		}

	}

	private void Searchcriteria_status(String testname, String testdata1)throws Exception {
		if (doesElementExist2(properties.getProperty("srchcrit"), 5))
		{
			WebElement drpdownsearchcri = driver.findElement(By	.cssSelector(properties
					.getProperty("srchcrit")));
			Highlight(drpdownsearchcri);
			drpdownsearchcri.click();
			List<WebElement> listSerachCriteria = driver.findElements(By.cssSelector(properties.getProperty("statlst")));
			for (WebElement li : listSerachCriteria)

			{
				Thread.sleep(1000);
				if (li.getText().equalsIgnoreCase("Status"))

				{
					Thread.sleep(5000);
					li.click();
					Thread.sleep(2000);
					if (doesElementExist(properties.getProperty("statusarrow"), 5))
					{
						WebElement drpdownstatus = driver.findElement(By.xpath(properties.getProperty("statusarrow")));
						drpdownstatus.click();
						List<WebElement> Statuslist = driver.findElements(By.cssSelector(properties.getProperty("statlst")));
						for (WebElement li1 : Statuslist)

						{
							Thread.sleep(1000);
							if (li1.getText().equalsIgnoreCase(testdata1)) {
								Thread.sleep(1000);
								li1.click();
								break;
							}
						}
					}
					break;
				}

			}

		} else {
			log.logLine(testname, true,
					"Click on Search By Status in the dropdown failed");
			throw new Exception(
					"Click on Search By Status  in the dropdown failed");
		}
		return;
	}

	// ****************************************************************************************************************************




	public boolean CampaignManager_Config(String AccNo, String Testname) throws Exception { 
		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile")); 		
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	
		Thread.sleep(4000);

		driver.switchTo().defaultContent();
		//	Thread.sleep(2000);
		selectClientApp(Testname);
		//	WebElement Client = waitForElement(properties.getProperty("selClint1"));		
		//	Highlight(Client);

		//	Thread.sleep(1000);
		//	boolean CliSelected = false;
		//	String ClntName = test.readColumnData("ClientName", sheetname);				

		// Switching back to parent window
		if ((doesElementExist2(properties.getProperty("Adminlnk"), 5))) { 
			WebElement oldpivt = driver.findElement(By.cssSelector(properties.getProperty("Adminlnk")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", oldpivt);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicking on Legacy Admin.."); 
		}
		String CurrentwinHandle = driver.getWindowHandle(); 
		for (String handle : driver.getWindowHandles()) {
			if(!(handle.equalsIgnoreCase(CurrentwinHandle))){
				System.out.println(CurrentwinHandle);
				driver.getWindowHandles().remove(CurrentwinHandle);
				driver.switchTo().window(handle); 
				break;
			}
		}

		// driver.switchTo().frame("iframeContainer");

		if ((doesElementExist2(properties.getProperty("UserAdmin"), 5))) { 
			WebElement User_Admin = driver.findElement(By.cssSelector(properties.getProperty("UserAdmin")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", User_Admin);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicked on USer Admin on Left Menu.."); 
		}

		if ((doesElementExist(properties.getProperty("UserGroupId_TxtBx"), 5))) { 
			WebElement UserGroupIdTxtBx = driver.findElement(By.xpath(properties.getProperty("UserGroupId_TxtBx")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", UserGroupIdTxtBx);
			UserGroupIdTxtBx.sendKeys("Manohar");
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicked on USer Admin on Left Menu.."); 
		}


		if ((doesElementExist(properties.getProperty("AppName_TxtBx"), 5))) { 
			WebElement AppName_TxtBx = driver.findElement(By.xpath(properties.getProperty("AppName_TxtBx")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", AppName_TxtBx);
			AppName_TxtBx.sendKeys("RGT1099");
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicked on USer Admin on Left Menu.."); 
		}


		if ((doesElementExist2(properties.getProperty("Search"), 5))) { 
			WebElement SearchBtn = driver.findElement(By.cssSelector(properties.getProperty("Search")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", SearchBtn);
			Thread.sleep(25000);
			log.logLine(Testname, false, "Clicked on Search Btn"); 
		}


		if ((doesElementExist(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_rptResults_ctl02_btnManageLayers']", 5))) { 
			WebElement AddToolBtn = driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_rptResults_ctl02_btnManageLayers']")); 

			AddToolBtn.click();

			try		{
				//		wait.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				alert.accept();
			}
			catch (Exception e){
				System.out.println("No alert");
				if ((doesElementExist(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_rptResults_ctl02_lstLayers']", 5))) { 
					WebElement oSelection = driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_rptResults_ctl02_lstLayers']")); 

					try{
						waitFluent.until(ExpectedConditions.visibilityOf(oSelection));
						oSelection.click();
						Select dropdown = new Select(driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_rptResults_ctl02_lstLayers")));

						dropdown.selectByVisibleText("PIVOT Campaign Manager");
					}catch(UnhandledAlertException ee){
						AcceptAlert(Testname) ;
					}
					try{
						driver.switchTo().alert();
						AcceptAlert(Testname) ;
					}catch (NoAlertPresentException ex) {
						// Alert not present
						//   ex.printStackTrace();						  
					}catch (Exception e2) {
					}
					//	}
				}else {

					waitFluent.until(ExpectedConditions.alertIsPresent());
					AcceptAlert(Testname) ;
				}

			}	

		}



		CurrentwinHandle = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			if(!(handle.equalsIgnoreCase(CurrentwinHandle))){
				System.out.println(CurrentwinHandle);
				driver.getWindowHandles().remove(CurrentwinHandle);
				driver.switchTo().window(handle); 
				break;
			}
		}


		return true ;
	}

	//****************************************************************************************************************************



	public boolean CampaignManager_AdvanceSearch_1(String AccNo, String Testname) throws Exception { 		

		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		ClickeDeliverTab(Testname);
		selectClientApp(Testname);
		ClickCampaignManagerTab(Testname);
		driver.switchTo().frame("iframeContainer"); 
		Thread.sleep(5000);
		CampaignNameFromGrid =  ReadCampaignNameFromGrid(0) ;
		Thread.sleep(5000);
		ClickAdvanceSearchBtn(Testname);
		Thread.sleep(5000);
		String CampaignNameSearched= SearchByCampaignName(Testname , CampaignNameFromGrid);
		Thread.sleep(5000);
		ClickAdvanceSearch_SearchBtn(Testname);
		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Alertpopup"), 5)) {
			WebElement eDelive = driver.findElement(By.cssSelector(properties.getProperty("Alertpopup")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
			log.logLine(Testname, false, "Click on Alert pop up is Successful");
		} else {
			log.logLine(Testname, true, "Click on Alert pop up is failed");
			throw new Exception("Click on Alert pop up is failed");
		}
		Thread.sleep(6000);
		waitFluent.until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[3]"))) ;
		int NoOfRowsInGridAfterSearch = CalculateNumOfRowsInTable(Testname);
		String[] AllCampaignNamesFromGrid =  ReadAllCampaignNameFromGrid(Testname, NoOfRowsInGridAfterSearch) ;		
		waitFluent.until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[3]"))) ;
		compareCampaignNameInSearchAndGrid12(Testname , NoOfRowsInGridAfterSearch ,  AllCampaignNamesFromGrid, CampaignNameSearched);

		driver.switchTo().defaultContent();
		ClickCampaignManagerTab(Testname);
		driver.switchTo().frame("iframeContainer");

		TemplateNameFromGrid =  ReadTemplateNameFromGrid(1) ;
		ClickAdvanceSearchBtn(Testname);
		String TemplateNameSearched= SearchByTemplateName(Testname);
		ClickAdvanceSearch_SearchBtn(Testname);
		Thread.sleep(6000);
		if (doesElementExist2(properties.getProperty("Alertpopup"), 5)) {
			WebElement eDelive = driver.findElement(By.cssSelector(properties.getProperty("Alertpopup")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
			log.logLine(Testname, false, "Click on Alert pop up is Successful");
		} else {
			log.logLine(Testname, true, "Click on Alert pop up is failed");
			throw new Exception("Click on Alert pop up is failed");
		}

		Thread.sleep(6000);

		gettingrundatebychangeinstatus(Testname);
		Campaigndetailsaccordion(Testname);
		Thread.sleep(5000);
		TemplatesortAscending(Testname);
		Thread.sleep(5000);
		TemplatesortDescending(Testname);
		Thread.sleep(5000);
		RunDateSortAscending(Testname);
		Thread.sleep(5000);
		RunDateSortDescending(Testname);
		driver.switchTo().defaultContent();
		ClickCampaignManagerTab(Testname);
		driver.switchTo().frame("iframeContainer");

		Thread.sleep(10000);
		ValidateTmpltnmeinTmpltmngrmodule(Testname);
		ValidateTemplateName(Testname);

		return true;		
	}

	public void gettingrundatebychangeinstatus(String Testname) throws Exception{

		String row="tr";

		if ((doesElementExist(("html/body/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[2]"), 5))) {			    
			WebElement rndte = driver.findElement(By.xpath(("html/body/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[2]")));	
			Highlight(rndte);
			Rundate=driver.findElement(By.xpath(("html/body/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[2]"))).getText();
			if(Rundate.isEmpty()){
				WebElement cseactns=driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ row + " td+td+td+td+td+td+td+td span span span[class='k-input']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()",cseactns);
				log.logLine(Testname, false, "Click on Choose action is Successful");

				if (doesElementExist2(properties.getProperty("CmpnActionListbox"), 5)) {
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("CmpnActionListbox")));
					for (WebElement lnk:selopts) {
						if (lnk.getText().contains("Run")) {
							lnk.click();
							//((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(4000);
							log.logLine(Testname, false, "Selecting the Campaign Action from the drop down is successful");
							break;
						}				
					} 
				}
				Thread.sleep(2000);

				Alert alert = driver.switchTo().alert();
				alert.accept();

				Thread.sleep(10000);
				ClickAdvanceSearchBtn(Testname);
				ClickAdvanceSearch_SearchBtn(Testname);

				Thread.sleep(5000);
				if (doesElementExist2(properties.getProperty("Alertpopup"), 5)) {
					WebElement eDelive = driver.findElement(By.cssSelector(properties.getProperty("Alertpopup")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
					log.logLine(Testname, false, "Click on Alert pop up is Successful");
				} else {
					log.logLine(Testname, true, "Click on Alert pop up is failed");
					throw new Exception("Click on Alert pop up is failed");
				}

				Thread.sleep(2000);
				String Status=driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ row + " td+td+td+td[role='gridcell']")).getText();
				log.logLine(Testname, false, "Status Changed to <<<<< "+Status+" >>>>> after clicking on Run option");

				Thread.sleep(15000);
				ClickAdvanceSearchBtn(Testname);
				ClickAdvanceSearch_SearchBtn(Testname);

				Thread.sleep(5000);
				if (doesElementExist2(properties.getProperty("Alertpopup"), 5)) {
					WebElement eDelive = driver.findElement(By.cssSelector(properties.getProperty("Alertpopup")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
					log.logLine(Testname, false, "Click on Alert pop up is Successful");
				} else {
					log.logLine(Testname, true, "Click on Alert pop up is failed");
					throw new Exception("Click on Alert pop up is failed");
				}

				Thread.sleep(2000);

				String Status1=driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ row + " td+td+td+td[role='gridcell']")).getText();
				log.logLine(Testname, false, "Status Changed to <<<<< "+Status1+" >>>>> after clicking on Run option");


				Thread.sleep(10000);
			
				ClickAdvanceSearchBtn(Testname);
				Thread.sleep(5000);
				ClickAdvanceSearch_SearchBtn(Testname);

				Thread.sleep(5000);
				if (doesElementExist2(properties.getProperty("Alertpopup"), 5)) {
					WebElement eDelive = driver.findElement(By.cssSelector(properties.getProperty("Alertpopup")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
					log.logLine(Testname, false, "Click on Alert pop up is Successful");
				} else {
					log.logLine(Testname, true, "Click on Alert pop up is failed");
					throw new Exception("Click on Alert pop up is failed");
				}

				Thread.sleep(2000);
				String Status2=driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ row + " td+td+td+td[role='gridcell']")).getText();
				log.logLine(Testname, false, "Status Changed to <<<<< "+Status2+" >>>>> after clicking on Run option");

				Thread.sleep(5000);}}

		else {
			log.logLine(Testname, true, "Rundate column doesnot exist");
			throw new Exception("Rundate column doesnot exist");
		}


	}





	public  void compareCampaignNameInSearchAndGrid12(String Testname , int NoOfRowsInGridAfterSearch, String[] allCampaignNamesFromGrid , String CampaignNameSearched1) throws Exception{

		for (M = 0 ; M < NoOfRowsInGridAfterSearch ; M++){			
			waitFluent.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='management-campaigns-grid']/table/tbody/tr[1]/td[3]")));
			try {
				if (allCampaignNamesFromGrid[M].equalsIgnoreCase(CampaignNameSearched1)) {
					ReadCampaignNameFromGrid1(M);
					log.logLine(Testname, false, "CampaignNames searched  "+CampaignNameSearched1 + " matches to the campaign name shown in the grid");
				}else{

				}

			}

			catch ( StaleElementReferenceException e ) {

			}
			catch (  NullPointerException e1 ){

			}
		}
	}
	public boolean CampaignManager_ManageRecipient(String AccNo, String Testname) throws Exception {try {	
		driver.switchTo().defaultContent();

		ClickeDeliverTab(Testname);
		selectClientApp(Testname);
		Thread.sleep(10000);
		//		ClickTemplateMgmntTab(Testname);
		ClickCampaignManagerTab(Testname);			

		driver.switchTo().frame("iframeContainer");
		Thread.sleep(5000);
		Filename = AdminConfig.CreateXML(AccNo);
		RecipientName=AdminConfig.CreateRecipient(AccNo);
		String array[]=RecipientName.split(".txt");
		String array1[]=array[0].split("Test Data");
		String arr[]=array1[1].split("CM_Recipient_AutoTest");
		String Recnum=arr[1].trim();
		Recpntnme="CM_Recipient_AutoTest".concat(Recnum);
		File InPutfilelocation= new File(Filename);
		InPutfileText = ReadXML(InPutfilelocation);



		ClickManageRecepientsBtn(Testname) ;
		Thread.sleep(5000);
		ClickUploadNewBtn(Testname) ;
		Thread.sleep(5000);
		EnterFilePath(Testname, RecipientName) ;
		Thread.sleep(5000);
		ClickUploadListBtn(Testname);
		Thread.sleep(5000);
		ValidateRecipientExists(Testname, Recpntnme);

		Thread.sleep(3000);
		ClickCloseBtn_ManageRecepients(Testname);


		NewCmpaignBtn(Testname);

		if (doesElementExist2(properties.getProperty("CmpaignNme"), 5)) {
			WebElement uploadfile = driver.findElement(By.cssSelector(properties.getProperty("CmpaignNme")));
			Thread.sleep(1000);
			uploadfile.sendKeys("AutoCmpain" + AccNo);
			log.logLine(Testname, false,"Clicking on Manage Recipients button is successful");
		} else {
			log.logLine(Testname, true,"Clicking on Manage Recipients button is not successful");
			throw new Exception("Clicking on Manage Recipients button is not successful");
		}


		if (doesElementExist2(properties.getProperty("CmpainReciList"), 5)) {
			WebElement reciSel = driver.findElement(By.cssSelector(properties.getProperty("CmpainReciList")));
			// reciSel.sendKeys(TemplateType);
			Thread.sleep(1000);
			reciSel.click();
			Thread.sleep(1000);
			if (doesElementExist2(properties.getProperty("ReciList"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ReciList")));
				for (WebElement lnk : selopts) {
					if (lnk.getText().equals(Recpntnme)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false,"Selecting the ClientName s from the popup..");
						break;
					}
				}

			}
			log.logLine(Testname, false,"Entering Recipient List name in the combo field is successful");
		} else {
			log.logLine(Testname, true,"Entering Recipient List name in the combo field is not successful");
			throw new Exception("Entering Recipient List name in the combo field is not successful");
		}

		Thread.sleep(5000);
		CampaignManagerFields(Testname);

		RecepntValidation(Testname,AccNo);

		//Checking for Duplicate Recipients

		Thread.sleep(5000);
		ClickManageRecepientsBtn(Testname) ;

		Thread.sleep(5000);
		if (doesElementExist(properties.getProperty("Items"), 5)) {	    
			String val = driver.findElement(By.xpath(properties.getProperty("Items"))).getText();
			String Item[] = val.split("of");
			Beforeupdte = Item[1].trim();

			log.logLine(Testname, false, "The total number of documents present in the Manage Recipient grid before uploading Duplicate recipient are: **** "+Beforeupdte+" ****");
		}

		ClickUploadNewBtn(Testname) ;
		Thread.sleep(5000);
		EnterFilePath(Testname, RecipientName) ;
		Thread.sleep(5000);
		ClickUploadListBtn(Testname);
		Thread.sleep(5000);


		Thread.sleep(4000);
		if (doesElementExist(properties.getProperty("Items"), 5)) {	    
			String val = driver.findElement(By.xpath(properties.getProperty("Items"))).getText();
			String Items[] = val.split("of");
			Afterupdte = Items[1].trim();

			log.logLine(Testname, false, "The total number of documents present in the Manage Recipient grid After uploading Duplicate recipient are: **** "+Afterupdte+" ****");
		}


		if(Afterupdte.equals(Beforeupdte)){
			log.logLine(Testname, false, "The number of documents in the grid are not increased hence uploading duplicate recipients cannot be done");
		}else{
			log.logLine(Testname, true, "The number of documents in the grid are not increased after Acknowledging the document");
		}


		Thread.sleep(3000);
		ClickCloseBtn_ManageRecepients(Testname);			




		///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		ClickManageRecepientsBtn(Testname) ;
		Thread.sleep(5000);
		ClickUploadNewBtn(Testname) ;
		Thread.sleep(5000);
		EnterFilePath(Testname, Filename) ;
		Thread.sleep(5000);
		ClickUploadListBtn(Testname); 
		Thread.sleep(5000);
		ValidateListExists(Testname, Filename,AccNo);		



		//	DeleteExistingFileBeforeItDownloads(Testname); 
		//ClickChooseActionDrpDwn(Testname) ;
		//ClickChooseActionDownloadInDrpDwn(Testname) 	;
		//	ValidateFileExists_AfterDownloads(Testname);  

		//File OutPutfilelocation = new File("C:\\Pivot_Portal\\Test Output\\CFA_FirstName_XML.txt");	     
		//OutPutFileText = ReadXML(OutPutfilelocation);

		//	if (InPutfileText.equals(OutPutFileText)){
		//	log.logLine(Testname, false, "Content of the downloaded file exactly matches the content of the file uploaded");
		//
		//	}else {
		//		log.logLine(Testname, false, "Content of the downloaded file does not match to the content of the file uploaded");
		//	}

		Thread.sleep(3000);
		ClickCloseBtn_ManageRecepients(Testname);


		//ClickManageRecepientsBtn(Testname);
		//	String BeforeDelete = ListNameExists(Testname) ; 

		//	ClickChooseActionDrpDwn(Testname);

		//	ClickChooseActionDelete_InDrpDwn(Testname);

		//	WebDriverWait wait = new WebDriverWait(driver,60);
		//	waitFluent.until(ExpectedConditions.alertIsPresent());

		//	AcceptAlert(Testname) ;
		//	Thread.sleep(3000);
		//ClickCloseBtn_ManageRecepients(Testname);

		driver.switchTo().defaultContent();		
		ClickCampaignManagerTab(Testname) ;
		//		NoOfRowsInGridAfterSearch = CalculateNumOfRowsInTable(Testname);
		driver.switchTo().frame("iframeContainer");
		//selectClientApp(Testname);
		ClickManageRecepientsBtn(Testname) ;
		/*
		String AfterDelete = ListNameExists(Testname);

		if(BeforeDelete.equalsIgnoreCase(AfterDelete)){
			log.logLine(Testname, false, "Listname Deletion is Successful");
		}else{
			log.logLine(Testname, false, "Listname couldnot  be Deleted ");
		}
		 */

		ClickUploadNewBtn(Testname) ;
		Filename = EnterFileName(Testname, "TDD") ;
		EnterFilePath(Testname, "C://Pivot_Portal//Test Data//TDD.png") ;

		ClickUploadListBtn(Testname) ;
		ValidateListExists1(Testname, Filename);	

		ClickUploadNewBtn(Testname) ;
		Filename = EnterFileName(Testname, "ArchivesSearch") ;
		EnterFilePath(Testname, "C://Pivot_Portal//Test Data//ArchivesSearch.xls") ;
		ClickUploadListBtn(Testname) ;
		ValidateListExists1(Testname, Filename);	

		ClickUploadNewBtn(Testname) ;
		Filename = EnterFileName(Testname, "AutoPDF") ;
		EnterFilePath(Testname, "C://Pivot_Portal//Test Data//AutoPDF.pdf") ;
		ClickUploadListBtn(Testname) ;
		ValidateListExists1(Testname, Filename);

		ClickCloseBtn_ManageRecepients(Testname);
	}
	catch ( StaleElementReferenceException e ) {

	}
	catch (  NullPointerException e1 ){

	} 
	return true;    
	}


	public String ListNameExists(String Testname) throws Exception { 
		String ListNamee = null ;
		if (doesElementExist2("div[id='campaign-manager-recipient-lists-grid'] table tbody tr td[role='gridcell']", 5)) {
			WebElement ListNam = driver.findElement(By.cssSelector("div[id='campaign-manager-recipient-lists-grid'] table tbody tr td[role='gridcell']"));

			ListNamee = ListNam.getText();
			//		((JavascriptExecutor) driver).executeScript("arguments[0].click()", ListNam);	
			log.logLine(Testname, false, "Click on Template management Module is Successful");
		} else {
			log.logLine(Testname, true, "Click on Template management Module is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Template management Module is failed");
		}

		return ListNamee ;
	} 


	public boolean AcceptAlert(String Testname) throws Exception {               
		//Error message for Empty Email Address
		Thread.sleep(4000);
		Alert alert2 = driver.switchTo().alert();
		alert2.accept();
		log.logLine(Testname, false, "Clicked on Alert successfull for file delete ");
		return true ;
	} 
	public void ClickChooseActionDelete_InDrpDwn(String Testname) throws Exception {
		//	if (doesElementExist("html/body/div[29]/div/ul/li[3]", 5)) {

		if ((doesElementExist2("ul li+li+li[role='option']", 5))) {			    
			List<WebElement> ChooseActionAddRecepient = driver.findElements(By.cssSelector("ul li+li+li[role='option']"));			
			//		ChooseActionAddRecepient.click();
			//		List<WebElement> listSerachCriteria = driver.findElements(By.cssSelector(("div ul li[class='k-item']")));
			for (WebElement li: ChooseActionAddRecepient)
			{		if (li.getText().equalsIgnoreCase("Delete"))
			{	

				Highlight(li);
				//	action.click(li).build().perform();
				li.click();
				//	action.contextClick();
				log.logLine(Testname, false, "Clicked successfully on Action delete ");
				break;
			}
			}	

		}else {
			log.logLine(Testname, true, "Clicking on Action delete Failed");
			throw new Exception("Clicking on Action delete Failed");
		}
	}

	public void selectClientApp(String Testname) throws Exception {{

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile")); 		
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	
		Thread.sleep(1000);

		driver.switchTo().defaultContent();
		//	Thread.sleep(2000);
		WebElement Client = waitForElement(properties.getProperty("selClint1"));		
		Highlight(Client);

		Thread.sleep(1000);
		boolean CliSelected = false;
		String ClntName = test.readColumnData("ClientName", sheetname);

		if (doesElementExist2(properties.getProperty("selClint1"), 5)) {
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selClint1")));
			Highlight(ClientSel);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
			log.logLine(Testname, false, "Clicking on ClientName dropdown..");


			if (doesElementExist2(properties.getProperty("ClinetOpts1"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ClinetOpts1")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().contains(ClntName)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);						
						Thread.sleep(1000);
						Highlight(lnk);
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

			if(Initialization.UserID.contains(user))
			{		throw new Exception("Client Name dropdown element does not exist");}
			{log.logLine(Testname, true, "Signing out of this user");
			signout(Testname);
			Thread.sleep(2000);
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
			throw new Exception("Client Name dropdown element does not exist");
			}

		}

		if (!CliSelected) {
			if (doesElementExist2(properties.getProperty("selClint2"), 5)) {	   
				WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selClint2")));
				Highlight(ClientSel);
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

				log.logLine(Testname, false, "Clicking on ClientName dropdown..");

				if (doesElementExist2(properties.getProperty("ClinetOpts2"), 5)) {
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ClinetOpts2")));
					for (WebElement lnk:selopts) {
						if (lnk.getText().contains(ClntName)) {
							Highlight(lnk);
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
				if(Initialization.UserID.contains(user))
				{		throw new Exception("Client Name dropdown element does not exist");}
				{log.logLine(Testname, true, "Signing out of this user");
				signout(Testname);
				Thread.sleep(2000);
				log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
				Relogin(Testname);
				throw new Exception("Client Name dropdown element does not exist");
				}

			}	    	
		}


		boolean AppSelected = false;
		String AppName = test.readColumnData("ApplicationName", sheetname);

		if (doesElementExist2(properties.getProperty("selAppl1"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selAppl1")));
			Highlight(ClientSel);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on ApplicationName dropdown..");

			if (doesElementExist2(properties.getProperty("ApplOpts1"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ApplOpts1")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().contains(AppName)) {
						Highlight(lnk);
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Application Name "+AppName+" from the popup..");
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
			if(Initialization.UserID.contains(user))
			{throw new Exception("Application Name dropdown element does not exist");}
			{log.logLine(Testname, true, "Signing out of this user");
			signout(Testname);
			Thread.sleep(2000);
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
			throw new Exception("Application Name dropdown element does not exis");
			}

		}

		if (!AppSelected) {
			if (doesElementExist2(properties.getProperty("selAppl2"), 5)) {	   
				WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selAppl2")));
				Highlight(ClientSel);
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

				if(Initialization.UserID.contains(user))
				{throw new Exception("Application Name dropdown element does not exist");}
				{log.logLine(Testname, true, "Signing out of this user");
				signout(Testname);
				Thread.sleep(2000);
				log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
				Relogin(Testname);
				throw new Exception("Application Name dropdown element does not exis");
				}

			}	    	
		}

		if (doesElementExist2(properties.getProperty("Okbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Okbtn")));
			Highlight(okbtn);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on OK button to view the Audits");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the Audits is failed");
			if(Initialization.UserID.contains(user))
			{throw new Exception("Clicking on OK button to view the Audits is failed");}
			{log.logLine(Testname, true, "Signing out of this user");
			signout(Testname);
			Thread.sleep(2000);
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
			throw new Exception("Clicking on OK button to view the Audits is failed");
			}

		}

		PleasewaitDisappear();

	}		    
	}

	public String ReadXML(File fXmlFile) {

		String Constants = "";
		try {						 
			//		File fXmlFile = new File("C:\\Pivot_Portal\\Test Data\\CFA_FirstName_XML.txt");

			System.out.println("fXmlFile="+fXmlFile);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize()	;	 
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("NewDataSet")	 ;
			System.out.println("----------------------------");

			for (int temp = 0; temp <= nList.getLength(); temp++) {		 
				Node nNode = nList.item(temp);	
				Element eElement = (Element) nNode;
				Constants =  
						eElement.getElementsByTagName("EmailId").item(0).getTextContent() + " "
								+ eElement.getElementsByTagName("FirstName").item(0).getTextContent()+ " "

								;
				break;			
				//		}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constants;
	}


	public void DeleteExistingFileBeforeItDownloads(String Testname) throws Exception {
		//Delete the existing file before it downloads
		try {			
			File fileTemp1 = new File(System.getProperty("user.home")+"\\Downloads\\CFA_FirstName_XML.txt");
			if (fileTemp1.exists()){
				fileTemp1.delete();
			}

			File fileTemp2 = new File("C:\\Pivot_Portal\\Test Output\\CFA_FirstName_XML.txt");		
			System.out.println(fileTemp2); 
			if (fileTemp2.exists()){
				fileTemp2.delete();
			}
		} catch(Exception e){
			// if any error occurs
			e.printStackTrace();
		}
		boolean retval = false;	
	}		

	public void ValidateFileExists_AfterDownloads(String Testname) throws Exception {
		try {			
			File fileTemp1 = new File(System.getProperty("user.home")+"\\Downloads\\CFA_FirstName_XML.txt");
			File fileTemp2 = new File("C:\\Pivot_Portal\\Test Output\\CFA_FirstName_XML.txt");
			System.out.println(fileTemp2);
			if (fileTemp2.exists()){
				log.logLine(Testname, false, "File is downloaded successfully");

				//retval = true;
			} else {
				log.logLine(Testname, false, "File download failed");

				//retval = false;
			}		        
		} catch(Exception e){
			// if any error occurs
			e.printStackTrace();
		}
	}  
	public void ClickChooseActionDrpDwn_ForNext(String Testname) throws Exception {
		Thread.sleep(4000);
		if (doesElementExist(".//*[@id='campaign-manager-recipient-lists-grid']/table/tbody/tr[2]/td[5]/span/span/span[1]", 5)) {
			WebElement ChooseActionDrpDwn = driver.findElement(By.xpath(".//*[@id='campaign-manager-recipient-lists-grid']/table/tbody/tr[2]/td[5]/span/span/span[1]"));
			Highlight(ChooseActionDrpDwn);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ChooseActionDrpDwn);  
			log.logLine(Testname, false, "Click successfully on Choose Action dropdown");
		} else {
			log.logLine(Testname, true, "Click on Choose Action dropdown failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Choose Action dropdown failed");
		}
	}
	public void ClickChooseActionDownloadInDrpDwn(String Testname) throws Exception {
		if ((doesElementExist2("ul li[role='option']", 5))) {

			List<WebElement> ChooseActionAddRecepient = driver.findElements(By.cssSelector("ul li[role='option']"));
			//		ChooseActionAddRecepient.click();
			//		List<WebElement> listSerachCriteria = driver.findElements(By.cssSelector(("div ul li[class='k-item']")));
			for (WebElement li: ChooseActionAddRecepient)
			{		String A = li.getText();
			if (li.getText().equalsIgnoreCase("Download"))
			{	
				Highlight(li);


				((JavascriptExecutor) driver).executeScript("arguments[0].click()", li);	
				/*			for(int i=1;i<4; i++){
				action.sendKeys(li, Keys.ARROW_DOWN ).build().perform();
				if(li.getText().equalsIgnoreCase("Download")){
				action.sendKeys(li, Keys.ENTER).click().build().perform();
				}

				}*/




				//	action.click();
				//		
				action.moveByOffset(-40, -90) ;

				//	li.click();
				log.logLine(Testname, false, "Clicked successfully on Downloads in Choose Action dropdown");

				break;
			}

			}
		}else {
			log.logLine(Testname, true, "Clicked on Downloads in Choose Action dropdown failed ");

			throw new Exception("Clicked on Downloads in Choose Action dropdown failed ");
		}
	}		
	public void ClickPlusBtn(String Testname) throws Exception {
		if (doesElementExist(".//*[@id='modal-add-recipient-to-list']/div[2]/div[1]/button[1]", 5)) {
			WebElement PlusBtn = driver.findElement(By.xpath(".//*[@id='modal-add-recipient-to-list']/div[2]/div[1]/button[1]"));
			Highlight(PlusBtn);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", PlusBtn);	
			Actions action = new Actions(driver);
			//	 action.moveToElement(AddRecepientBtn);
			//		 action.doubleClick(AddRecepientBtn).build().perform();
			Thread.sleep(2000);
			//	EmailField.sendKeys("Sricha.sachdeva@rrd.com");
			log.logLine(Testname, false, "Click on Plus Btns Successful");
		} else {
			log.logLine(Testname, true, "Click on Plus Btns failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Plus Btns failed");
		}
	}	

	public void ClickCancelBtn_OnAddRecepientPopup(String Testname) throws Exception {

		if (doesElementExist2("button[id='btnCancelAddRecipientToList']", 5)) {
			WebElement CancelBtn = driver.findElement(By.cssSelector("button[id='btnCancelAddRecipientToList']"));
			Highlight(CancelBtn);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", CancelBtn);	
			log.logLine(Testname, false, "Click Cancel on Add Recipient Popup is Successful");
		} else {
			log.logLine(Testname, true, "Click Cancel on Add Recipient Popup is  failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click Cancel on Add Recipient Popup is  failed");
		}
	}	
	public void ClickAddRecepientBtn(String Testname) throws Exception {
		//if (doesElementExist(".//*[@id='btnAddRecipientToList']", 5)) {	
		//	if (doesElementExist("html/body/div[3]/div[3]/div[2]/div[3]/button[1]", 5)) {
		//	if (doesElementExist2("button[type='submit']", 5)) {	
		if (doesElementExist2("div+div[id='modal-add-recipient-to-list'] div+div+div button[id='btnAddRecipientToList']", 5)) {	
			//	if (doesElementExist2("div[id='modal-add-recipient-to-list'] div[class='buttons-container btns'] button[id='btnAddRecipientToList']", 5)) {	
			//	WebElement AddRecepientBtn = driver.findElement(By.xpath(".//*[@id='btnAddRecipientToList']"));
			//	WebElement AddRecepientBtn = driver.findElement(By.xpath("html/body/div[3]/div[3]/div[2]/div[3]/button[1]"));
			WebElement AddRecepientBtn = driver.findElement(By.cssSelector("div[id='modal-add-recipient-to-list'] div[class='buttons-container btns'] button[id='btnAddRecipientToList']"));
			Highlight(AddRecepientBtn);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", AddRecepientBtn);	
			//	AddRecepientBtn.submit();
			Actions action = new Actions(driver);
			action.moveToElement(AddRecepientBtn);
			action.click().perform();
			//	Thread.sleep(2000);
			action.click().build().perform();
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", AddRecepientBtn);	

			log.logLine(Testname, false, "Click Add Recipient Btn is  Successful");
		} else {
			log.logLine(Testname, true, "Click Add Recipient Btn failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click Add Recipient Btn failed");
		}
	}	
	public void EnterEmailField(String Testname) throws Exception {
		if (doesElementExist(".//*[@id='modal-add-recipient-to-list']/div[2]/div/input[1]", 5)) {
			WebElement EmailField = driver.findElement(By.xpath(".//*[@id='modal-add-recipient-to-list']/div[2]/div/input[1]"));
			Highlight(EmailField);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", EmailField);	    	 
			Thread.sleep(2000);
			EmailField.clear();
			Count=Count+1;
			EmailField.sendKeys("richa"+Count+".sachdeva@rrd.com");
			log.logLine(Testname, false, "Entering data in the Emailfield is Successful");
		} else {
			log.logLine(Testname, true, "Entering data in the Emailfield failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering data in the Emailfield failed");
		}
	}	


	public void EnterXMLField(String Testname) throws Exception {
		if (doesElementExist(".//*[@id='modal-add-recipient-to-list']/div[2]/div/input[2]", 5)) {
			WebElement EnterXMLField = driver.findElement(By.xpath(".//*[@id='modal-add-recipient-to-list']/div[2]/div/input[2]"));
			Highlight(EnterXMLField);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", EnterXMLField);	    	 
			Thread.sleep(2000);
			EnterXMLField.clear();
			EnterXMLField.sendKeys("<NewDataSet><Generic_Xml_Data><FirstName>Richa</FirstName></Generic_Xml_Data></NewDataSet>");
			log.logLine(Testname, false, "Entering data in the XMLfield Successful");
		} else {
			log.logLine(Testname, true, "Entering data in the XMLfield failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering data in the XMLfield failed");
		}
	}	
	//	button id="btnUploadRecipientList")
	public void ClickChooseActionAddRecepientInDrpDwn(String Testname) throws Exception {
		if ((doesElementExist2("ul li+li[role='option']", 5))) {			    
			List<WebElement> ChooseActionAddRecepient = driver.findElements(By.cssSelector("ul li+li[role='option']"));
			//		ChooseActionAddRecepient.click();
			//		List<WebElement> listSerachCriteria = driver.findElements(By.cssSelector(("div ul li[class='k-item']")));
			for (WebElement li: ChooseActionAddRecepient)
			{		if (li.getText().equalsIgnoreCase("Add recipient"))
			{	
				Thread.sleep(2000);
				Highlight(li);
				li.click();
				log.logLine(Testname, true, "Clicking on Add Recipient btn in Choose Action dropdown successfull ");
				break;
			}
			}	
		}else {
			log.logLine(Testname, true, "Clicking on Add Recipient btn in Choose Action dropdown failed ");
			Thread.sleep(2000);
			throw new Exception("Clicking on Add Recipient btn in Choose Action dropdown failed ");
		}
	}	

	public int ClickChooseActionDrpDwn(String Testname) throws Exception {

		if (doesElementExist(".//*[@id='campaign-manager-recipient-lists-grid']/table/tbody/tr[1]/td[5]/span/span/span[1]", 5)) {
			WebElement ChooseActionDrpDwn = driver.findElement(By.xpath(".//*[@id='campaign-manager-recipient-lists-grid']/table/tbody/tr[1]/td[5]/span/span/span[1]"));
			Highlight(ChooseActionDrpDwn);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ChooseActionDrpDwn);	    
			rownum = 1 ;
			Thread.sleep(2000);
			//		PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Choose Action dropdown successfull ");
		} else {
			log.logLine(Testname, true, "Clicking Choose Action dropdown failed ");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking Choose Action dropdown failed");
		}
		return rownum;
	}	
	public int ValidateListExistsIntheGrid(String Testname, String Filename, int NumOfRowsInTable) throws Exception {
		Thread.sleep(4000);
		for (int a=0; a<NumOfRowsInTable; a++){

			if (doesElementExist(".//*[@id='management-campaigns-grid']/table/tbody/tr["+N+"]/td[5]", 5)) {
				WebElement ListnameUploaded = driver.findElement(By.xpath(".//*[@id='management-campaigns-grid']/table/tbody/tr["+N+"]/td[5]"));
				Highlight(ListnameUploaded);
				String NListname = ListnameUploaded.getText();    
				if(Filename.equalsIgnoreCase(NListname)){
					log.logLine(Testname, false, "File uploaded successfully, validation pass");
				}else{
					log.logLine(Testname, true, "File could not be uploaded, No list exists ");
				}
			} else {
				log.logLine(Testname, true, "No list exists, validation failed");
				driver.switchTo().defaultContent();
				throw new Exception("No list exists, validation failed");
			}
		}
		return rownum;
	}	

	public boolean ValidateListExists(String Testname, String Filename,String AccNo) throws Exception {
		Thread.sleep(4000);

		String[] Sort = new String[150];
		int length = Sort.length;
		String row="tr";


		if (doesElementExist2(properties.getProperty("Managerecipeintgrid"), 5)) {
			List<WebElement> DataCnt= driver.findElements(By.cssSelector("div[id='campaign-manager-recipient-lists-grid'] table tbody tr td"));
			for(int i = 0; i < DataCnt.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='campaign-manager-recipient-lists-grid'] table tbody "+row+" td")).getText();
				if(Sort[i].contains(AccNo)){
					log.logLine(Testname, false, "File uploaded successfully, validation pass");

					if (doesElementExist2("div[id='campaign-manager-recipient-lists-grid'] table tbody "+row+" td+td+td+td+td span span span", 5)) {
						WebElement ChooseActionDrpDwn = driver.findElement(By.cssSelector("div[id='campaign-manager-recipient-lists-grid'] table tbody "+row+" td+td+td+td+td span span span"));
						//Highlight(ChooseActionDrpDwn);
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", ChooseActionDrpDwn);	    

						Thread.sleep(4000);
						if (doesElementExist2(properties.getProperty("OptionType"), 5)) {
							List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("OptionType")));
							/*	
						Robot robot=new Robot();
						robot.keyPress(KeyEvent.VK_D);
						robot.keyPress(KeyEvent.VK_D);


						robot.keyPress(KeyEvent.VK_ENTER);
						Thread.sleep(5000);
						robot.keyRelease(KeyEvent.VK_D);
						robot.keyRelease(KeyEvent.VK_ENTER);

							 */


							for (WebElement lnk:selopts) {
								if (lnk.getText().equals("Delete")) {
									lnk.click();
									Thread.sleep(4000);
									//((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
									//PleasewaitDisappear();
									log.logLine(Testname, false, "Clicked successfully on delete in Choose Action dropdown");
									break;
								}
							}

							Thread.sleep(5000);
							WebDriverWait wait = new WebDriverWait(driver, 2);
							wait.until(ExpectedConditions.alertIsPresent());
							driver.switchTo().alert().accept();

						} else {
							log.logLine(Testname, true, "Clicking Choose Action dropdown failed ");
							//driver.switchTo().defaultContent();
							//throw new Exception("Clicking Choose Action dropdown failed");
						}
					}


					break;
				}
				row = row + "+tr";
			}


		}

		return true;
	}


	public boolean ValidateRecipientExists(String Testname, String Filename) throws Exception {
		Thread.sleep(4000);

		String[] Sort = new String[150];
		String[] Sort1 = new String[150];
		int length = Sort.length;
		String row="tr";


		if (doesElementExist2(properties.getProperty("Managerecipeintgrid"), 5)) {
			List<WebElement> DataCnt= driver.findElements(By.cssSelector("div[id='campaign-manager-recipient-lists-grid'] table tbody tr td"));
			for(int i = 0; i < DataCnt.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='campaign-manager-recipient-lists-grid'] table tbody "+row+" td")).getText();
				if(Sort[i].equals(Filename)){
					log.logLine(Testname, false, "File uploaded successfully....Uplaoded file is <<<<<"+Filename+" >>>>>");

					Sort1[i] = driver.findElement(By.cssSelector("div[id='campaign-manager-recipient-lists-grid'] table tbody "+row+" td+td+td+td")).getText();
					TotalNoofrecpnts=Sort1[i];
					log.logLine(Testname, false, "Number of Recipients are <<<<< "+TotalNoofrecpnts+">>>>> ");
					break;
				}
				row = row + "+tr";
			}


		}

		return true;
	}

	public int ValidateListExists1(String Testname, String Filename) throws Exception {
		Thread.sleep(4000);
		if (doesElementExist(".//*[@id='campaign-manager-recipient-lists-grid']/table/tbody/tr[1]/td[1]", 5)) {
			WebElement ListnameUploaded = driver.findElement(By.xpath(".//*[@id='campaign-manager-recipient-lists-grid']/table/tbody/tr[1]/td[1]"));
			Highlight(ListnameUploaded);
			String NListname = ListnameUploaded.getText();    
			if(Filename.equalsIgnoreCase(NListname)){
				log.logLine(Testname, false, "File uploaded successfully, validation pass");
			}else{
				log.logLine(Testname, false, "File could not be uploaded, It already exists");
			}
		} else {
			log.logLine(Testname, true, "No list exists, validation failed");
			driver.switchTo().defaultContent();
			throw new Exception("No list exists, validation failed");
		}
		return rownum;
	}	
	public void ClickUploadListBtn(String Testname) throws Exception {
		if (doesElementExist2("button[id='btnUploadRecipientList']", 5)) {
			WebElement UploadListBtn = driver.findElement(By.cssSelector("button[id='btnUploadRecipientList']"));
			Highlight(UploadListBtn);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", UploadListBtn);			
			log.logLine(Testname, false, "Click on Upload List Btn is Successful");
		} else {
			log.logLine(Testname, true, "Click on Upload List Btn is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Upload List Btn is failed");
		}
	}

	public void EnterFilePath(String Testname, String Filepath) throws Exception {
		if (doesElementExist2("input[class='recipientContentClass']", 5)) {
			WebElement BrowseBtn = driver.findElement(By.cssSelector("input[class='recipientContentClass']"));
			Highlight(BrowseBtn);
			//	BrowseBtn.clear();
			//	String Str="C:'\\Users'\\richa.sachdeva'\\Desktop'\\" ;
			//	BrowseBtn.sendKeys(Str+"CFA_FirstName_XML.txt");

			if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) || (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) {	
				Robot robot=new Robot();

				BrowseBtn.click();
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
				robot.keyPress(KeyEvent.VK_UP);
				robot.keyRelease(KeyEvent.VK_UP);
				robot.keyPress(KeyEvent.VK_UP);
				robot.keyRelease(KeyEvent.VK_UP);
				robot.keyPress(KeyEvent.VK_UP);
				robot.keyRelease(KeyEvent.VK_UP);

				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);

				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_O);
				robot.keyRelease(KeyEvent.VK_ALT);
				robot.keyRelease(KeyEvent.VK_O);



				Thread.sleep(2000);
			}
			else{
				BrowseBtn.sendKeys(Filepath);
			}
			// 		PleasewaitDisappear();
			// 		BrowseBtn.sendKeys("C:\\Users\\richa.sachdeva\\Desktop\\CFA_FirstName_XML.txt");
			// 		((JavascriptExecutor) driver).executeScript("arguments[0].click()", BrowseBtn);	    	 
			//	Thread.sleep(2000);
			//	
			log.logLine(Testname, false, "Entering Filepath in the field Successful");
		} else {
			log.logLine(Testname, true, "Entering Filepath in the field  failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering Filepath in the field failed");
		}
	}
	public String EnterFileName(String Testname, String Filename) throws Exception {
		if (doesElementExist2("input[id='recipient-list-name']", 5)) {
			WebElement BrowseBtn = driver.findElement(By.cssSelector("input[id='recipient-list-name']"));
			Highlight(BrowseBtn);
			BrowseBtn.clear();
			//	String Str="C:'\\Users'\\richa.sachdeva'\\Desktop'\\" ;
			//	BrowseBtn.sendKeys(Str+"CFA_FirstName_XML.txt");
			//		BrowseBtn.sendKeys("C:/Users/richa.sachdeva/Desktop/CFA_FirstName_XML.txt");

			BrowseBtn.sendKeys(Filename);

			//	    BrowseBtn.sendKeys("C:'\\Users'\\richa.sachdeva'\\Desktop'\\CFA_FirstName_XML.txt");
			//		((JavascriptExecutor) driver).executeScript("arguments[0].click()", BrowseBtn)    ;	 
			//	Thread.sleep(2000);
			//	
			log.logLine(Testname, false, "Entering FileNAme in the field Successful");
		} else {
			log.logLine(Testname, true, "Entering FileNAme in the field is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering FileNAme in the field is failed");
		}

		return Filename ;
	}
	public void ClickBrowseBtn_EnterXMLFilePath(String Testname) throws Exception {
		if (doesElementExist2("input[id='recipient-list-name']", 5)) {
			WebElement BrowseBtn = driver.findElement(By.cssSelector("input[id='recipient-list-name']"));
			Highlight(BrowseBtn);
			BrowseBtn.clear();
			//	String Str="C:'\\Users'\\richa.sachdeva'\\Desktop'\\" ;
			//	BrowseBtn.sendKeys(Str+"CFA_FirstName_XML.txt");
			BrowseBtn.sendKeys("C:\\Pivot_Portal\\Test Data\\CFA_FirstName_XML.txt");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", BrowseBtn);	    	 
			//	Thread.sleep(2000);
			//	PleasewaitDisappear();
			log.logLine(Testname, false, "Click on Browse Btn is Successful");
		} else {
			log.logLine(Testname, true, "Click on Browse Btn is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Browse Btn is failed");
		}
	}

	public void ClickUploadNewBtn(String Testname) throws Exception {
		if (doesElementExist2("button[class='k-button upload-recipient-list k-button-search-button-blue']", 5)) {
			WebElement UploadNewBtn = driver.findElement(By.cssSelector("button[class='k-button upload-recipient-list k-button-search-button-blue']"));
			Highlight(UploadNewBtn);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", UploadNewBtn);	    	 
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Click on Upload New Btn is Successful");
		} else {
			log.logLine(Testname, true, "Click on Upload New Btn is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Upload New Btn is failed");
		}
	}
	public void ClickManageRecepientsBtn(String Testname) throws Exception {
		//	if (doesElementExist2("div[id='management-sections-campaign-manager'] div div+div div div[id='campaign-manager-action-btns'] button[id='modal-add-new-campaign']+button[style='/*display: none; */']", 5)) {

		if (doesElementExist(".//*[@id='campaign-manager-action-btns']/button[2]",5)){
			WebElement ManageRecepientbtn = driver.findElement(By.xpath(".//*[@id='campaign-manager-action-btns']/button[2]"));
			Highlight(ManageRecepientbtn);
			//		WebElement ManageRecepientbtn = driver.findElement(By.cssSelector("div[id='management-sections-campaign-manager'] div div+div div div[id='campaign-manager-action-btns'] button[id='modal-add-new-campaign']+button[style='/*display: none; */']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ManageRecepientbtn);	    	 

			log.logLine(Testname, false, "Click on Manage Recepients Btn is Successful");
		} else {
			log.logLine(Testname, true, "Click on Manage Recepients Btn is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Manage Recepients Btn is failed");
		}
	}

	public void ClickTemplateMgmntTab(String Testname) throws Exception {
		if (doesElementExist2(properties.getProperty("TemplateMgmnt"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("TemplateMgmnt")));
			Highlight(btnsrch);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);	 
			log.logLine(Testname, false, "Click on Template management Tab is Successful");
		} else {
			log.logLine(Testname, true, "Click on Template management Tab is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Template management Tab is failed");
		}
	}

	public void ClickeDeliverTab1(String Testname) throws Exception {
		if (doesElementExist2(properties.getProperty("EdeliveryTab"), 5)) {
			WebElement eDelive = driver.findElement(By.cssSelector(properties.getProperty("EdeliveryTab")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
			log.logLine(Testname, false, "Click on e-Delivery Tab is Successful");
		} else {
			log.logLine(Testname, true, "Click on e-Delivery Tab is failed");
			if(Initialization.UserID.contains(user))
			{throw new Exception("Click on e-Delivery Tab is failed");}
			{ 
				log.logLine(Testname, true, "Signing out of this user");
				signout(Testname);
				Thread.sleep(2000);
				log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
				Relogin(Testname);
				throw new Exception("Click on e-Delivery Tab is failed");
			}
		}
	}

	public void ClickeDeliverTab(String Testname) throws Exception {
		if (doesElementExist2(properties.getProperty("EdeliveryTab"), 5)) {
			WebElement eDelive = driver.findElement(By.cssSelector(properties.getProperty("EdeliveryTab")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
			log.logLine(Testname, false, "Click on e-Delivery Tab is Successful");
		} else {
			log.logLine(Testname, true, "Click on e-Delivery Tab is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on e-Delivery Tab is failed");
		}
	}
	public void ClickCloseBtn_ManageRecepients(String Testname) throws Exception {
		if (doesElementExist2("div+div[id='modal-campaign-manager-manage-recipients'] div+div+div button[class='k-button close']", 5)) {
			WebElement CloseBtn = driver.findElement(By.cssSelector("div+div[id='modal-campaign-manager-manage-recipients'] div+div+div button[class='k-button close']"));
			Highlight(CloseBtn);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", CloseBtn);	   
			log.logLine(Testname, false, "Click on Close Btn on Manage Recepients Successful");
		} else {
			log.logLine(Testname, true, "Click on Close Btn on Manage Recepients failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Close Btn on Manage Recepients failed");
		}
	}

	public void ClickCampaignManagerTab(String Testname) throws Exception {
		if (doesElementExist2(properties.getProperty("CampaignManager"), 5)) {
			WebElement CampaignMgr = driver.findElement(By.cssSelector(properties.getProperty("CampaignManager")));
			Highlight(CampaignMgr);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", CampaignMgr);	
			Thread.sleep(10000);
			log.logLine(Testname, false, "Click on CampaignManager is Successful");
		} else {
			log.logLine(Testname, true, "Click on CampaignManager is failed");
			//driver.switchTo().defaultContent();
			if(Initialization.UserID.contains(user))
			{throw new Exception("Click on CampaignManager is failed");}
			{ 
				log.logLine(Testname, true, "Signing out of this user");
				signout(Testname);
				Thread.sleep(2000);
				log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
				Relogin(Testname);
				throw new Exception("Click on CampaignManager is failed,so relogged in to superuser");
			}

			//	
		}
	}


	public void ClickAdvanceSearchBtn(String Testname) throws Exception {
		if (doesElementExist2(properties.getProperty("AdvancedsearchPopup"), 5)) {
			WebElement AdvsearchPopup = driver.findElement(By.cssSelector(properties.getProperty("AdvancedsearchPopup")));
			Highlight(AdvsearchPopup);
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", AdvsearchPopup);	  
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on AdvancedsearchPopup is Successful");
		} else {
			log.logLine(Testname, true, "Click on AdvancedsearchPopup is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Click on AdvancedsearchPopup is failed");
		}
	}

	public boolean RecepntValidation(String Testname, String AccNo)throws Exception {

		String[] Sort1 = new String[50];
		String row = "tr";
		List<WebElement> DataCnt = driver.findElements(By.xpath(".//*[@id='management-campaigns-grid']/table/tbody/tr"));

		if (doesElementExist2(properties.getProperty("Recipnlst"), 5)) {
			for (int i = 0; i < DataCnt.size(); i++) {
				Sort1[i] = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ row + " td+td+td+td+td[role='gridcell']")).getText();

				if (Sort1[i].equals(Recpntnme)) {
					log.logLine(Testname, false,"RecipientName <<<<<"+Recpntnme+">>>>> Matches with <<<<<"+Sort1[i]+">>>>> hence Campaign Manager is added sucessfully");

					Thread.sleep(5000);
					WebElement cseactn=driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ row + " td+td+td+td+td+td+td+td span span span[class='k-input']"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()",cseactn);
					log.logLine(Testname, false, "Click on Choose action is Successful");

					if (doesElementExist2(properties.getProperty("CmpnActionListbox"), 5)) {
						List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("CmpnActionListbox")));
						for (WebElement lnk:selopts) {
							if (lnk.getText().contains("View")) {
								lnk.click();
								//((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
								Thread.sleep(4000);
								log.logLine(Testname, false, "Selecting the Campaign Action from the drop down is successful");
								break;
							}				
						} 
					}

					Thread.sleep(5000);
					if (doesElementExist2(properties.getProperty("Campaigndetails"), 5)) {
						WebElement chseactn = driver.findElement(By.cssSelector(properties.getProperty("Campaigndetails")));
						Thread.sleep(2000);
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", chseactn);	  
						Thread.sleep(2000);
						log.logLine(Testname, false, "Click on Campaign details accordion is Successful");
					} else {
						log.logLine(Testname, true, "Click on Campaign details accordion is failed");
						driver.switchTo().defaultContent();
						//throw new Exception("Click on AdvancedsearchPopup is failed");
					}

					Thread.sleep(5000);
					if (doesElementExist2(properties.getProperty("Vrfyrepcntsincampgndtl"), 5)) {
						String Recpnt = driver.findElement(By.cssSelector(properties.getProperty("Vrfyrepcntsincampgndtl"))).getText();

						if(Recpnt.equals(TotalNoofrecpnts)){
							log.logLine(Testname, false, "Recipient name <<<<< "+Recpnt+" >>>>> Matches with the <<<<< "+TotalNoofrecpnts+" >>>>>in Campaign details accordion");
						}else {
							log.logLine(Testname, true, "Template name does not matches in Campaign details accordion");
						}

					} else {
						log.logLine(Testname, true, "Click on Campaign details accordion is failed");

					}
					Thread.sleep(5000);

					if (doesElementExist2(properties.getProperty("Campaigndetails"), 5)) {
						WebElement chseactn = driver.findElement(By.cssSelector(properties.getProperty("Campaigndetails")));
						Thread.sleep(2000);
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", chseactn);	  
						Thread.sleep(2000);
						log.logLine(Testname, false, "Click on Campaign details accordion is Successful");
					} else {
						log.logLine(Testname, true, "Click on Campaign details accordion is failed");
					}

					Thread.sleep(5000);
					if (doesElementExist2(properties.getProperty("Hideaccordion"), 5)) {
						WebElement chseactn = driver.findElement(By.cssSelector(properties.getProperty("Hideaccordion")));
						Thread.sleep(2000);
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", chseactn);	  
						Thread.sleep(2000);
						log.logLine(Testname, false, "Click on Hide accordion button is Successful");
					} else {
						log.logLine(Testname, true, "Click on Hideaccordion button is failed");
						driver.switchTo().defaultContent();
						//throw new Exception("Click on AdvancedsearchPopup is failed");
					}


					WebElement cseactns=driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ row + " td+td+td+td+td+td+td+td span span span[class='k-input']"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()",cseactns);
					log.logLine(Testname, false, "Click on Choose action is Successful");

					if (doesElementExist2(properties.getProperty("CmpnActionListbox"), 5)) {
						List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("CmpnActionListbox")));
						for (WebElement lnk:selopts) {
							if (lnk.getText().contains("Run")) {
								lnk.click();
								//((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
								Thread.sleep(4000);
								log.logLine(Testname, false, "Selecting the Campaign Action from the drop down is successful");
								break;
							}				
						} 
					}
					Thread.sleep(2000);

					Alert alert = driver.switchTo().alert();
					alert.accept();

					Thread.sleep(5000);
					ClickAdvanceSearchBtn(Testname);
					ClickAdvanceSearch_SearchBtn(Testname);

					Thread.sleep(5000);
					if (doesElementExist2(properties.getProperty("Alertpopup"), 5)) {
						WebElement eDelive = driver.findElement(By.cssSelector(properties.getProperty("Alertpopup")));
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
						log.logLine(Testname, false, "Click on Alert pop up is Successful");
					} else {
						log.logLine(Testname, true, "Click on Alert pop up is failed");
						throw new Exception("Click on Alert pop up is failed");
					}

					Thread.sleep(2000);
					String Status=driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ row + " td+td+td+td[role='gridcell']")).getText();
					log.logLine(Testname, false, "Status Changed to <<<<< "+Status+" >>>>> after clicking on Run option");

					Thread.sleep(15000);
					ClickAdvanceSearchBtn(Testname);
					ClickAdvanceSearch_SearchBtn(Testname);

					Thread.sleep(5000);
					if (doesElementExist2(properties.getProperty("Alertpopup"), 5)) {
						WebElement eDelive = driver.findElement(By.cssSelector(properties.getProperty("Alertpopup")));
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
						log.logLine(Testname, false, "Click on Alert pop up is Successful");
					} else {
						log.logLine(Testname, true, "Click on Alert pop up is failed");
						throw new Exception("Click on Alert pop up is failed");
					}

					Thread.sleep(2000);

					String Status1=driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ row + " td+td+td+td[role='gridcell']")).getText();
					log.logLine(Testname, false, "Status Changed to <<<<< "+Status1+" >>>>> after clicking on Run option");


					Thread.sleep(60000);
					Thread.sleep(60000);
					ClickAdvanceSearchBtn(Testname);
					Thread.sleep(25000);
					ClickAdvanceSearch_SearchBtn(Testname);

					Thread.sleep(5000);
					if (doesElementExist2(properties.getProperty("Alertpopup"), 5)) {
						WebElement eDelive = driver.findElement(By.cssSelector(properties.getProperty("Alertpopup")));
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
						log.logLine(Testname, false, "Click on Alert pop up is Successful");
					} else {
						log.logLine(Testname, true, "Click on Alert pop up is failed");
						throw new Exception("Click on Alert pop up is failed");
					}

					Thread.sleep(2000);
					String Status2=driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ row + " td+td+td+td[role='gridcell']")).getText();
					log.logLine(Testname, false, "Status Changed to <<<<< "+Status2+" >>>>> after clicking on Run option");

					Thread.sleep(5000);
					WebElement Chseactn=driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+ row + " td+td+td+td+td+td+td+td span span span[class='k-input']"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()",Chseactn);
					log.logLine(Testname, false, "Click on Choose action is Successful");

					if (doesElementExist2(properties.getProperty("CmpnActionListbox"), 5)) {
						List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("CmpnActionListbox")));
						for (WebElement lnk:selopts) {
							if (lnk.getText().contains("View")) {
								lnk.click();
								//((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
								Thread.sleep(4000);
								log.logLine(Testname, false, "Selecting the Campaign Action from the drop down is successful");
								break;
							}				
						} 
					}

					if (doesElementExist2(properties.getProperty("Processingdetail"), 5)) {
						WebElement chseactn = driver.findElement(By.cssSelector(properties.getProperty("Processingdetail")));
						Thread.sleep(2000);
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", chseactn);	  
						Thread.sleep(2000);
						log.logLine(Testname, false, "Click on Processing detail accordion is Successful");
					} else {
						log.logLine(Testname, true, "Click on Processing detail accordion is failed");
					}	

					Thread.sleep(5000);
					if (doesElementExist2(properties.getProperty("Totalmailtobesentcomn"), 5)) {
						String Ttlmailtobesnt = driver.findElement(By.cssSelector(properties.getProperty("Totalmailtobesentcomn"))).getText();

						if(Ttlmailtobesnt.equals(TotalNoofrecpnts)){
							log.logLine(Testname, false, "Total Mail to be sent <<<<< "+Ttlmailtobesnt+" >>>>> is Same as Total number of Recipients <<<<< "+TotalNoofrecpnts+" >>>>> in Campaign Processing details accordion");


						}else {
							log.logLine(Testname, false, "Since Campaign is not in <<<<<Successful Status>>>>> Hence Total Mail to be sent <<<<<"+Ttlmailtobesnt+">>>>> does not matches Total number of Recipients <<<<<"+TotalNoofrecpnts+">>>>> in Campaign details accordion");
						}


					} else {
						log.logLine(Testname, true, "Click on Processing details accordion is failed");
					}

					Thread.sleep(5000);
					if (doesElementExist2(properties.getProperty("Totalmailsentcomn"), 5)) {
						String Ttlmailsnt = driver.findElement(By.cssSelector(properties.getProperty("Totalmailsentcomn"))).getText();

						if(Ttlmailsnt.equals(TotalNoofrecpnts)){
							log.logLine(Testname, false, "Total Mail sent <<<<< "+Ttlmailsnt+" >>>>> is Same as Total number of Recipients <<<<< "+TotalNoofrecpnts+" >>>>> in Campaign Processing details accordion");
						}else {
							log.logLine(Testname, false, "Since Campaign is in <<<<<Initiating Status>>>>> Hence Total Mail sent <<<<<"+Ttlmailsnt+">>>>> does not matches Total number of Recipients <<<<<"+TotalNoofrecpnts+">>>>> in Campaign details accordion");
						}

					} else {
						log.logLine(Testname, true, "Click on Processing details accordion is failed");
					}

					Thread.sleep(5000);
					if (doesElementExist2(properties.getProperty("Hideaccordion"), 5)) {
						WebElement chseactn = driver.findElement(By.cssSelector(properties.getProperty("Hideaccordion")));
						Thread.sleep(2000);
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", chseactn);	  
						Thread.sleep(2000);
						log.logLine(Testname, false, "Click on Hide accordion button is Successful");
					} else {
						log.logLine(Testname, true, "Click on Hideaccordion button is failed");
						driver.switchTo().defaultContent();
						//throw new Exception("Click on AdvancedsearchPopup is failed");
					}


					break;
				} 

				row = row + "+tr";
				log.logLine(Testname, false,"Iterating through the Rows");
			}

		}

		return true;
	}


	public Date EnterAdvanceFromDate(String Testname) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date FromDateEntered = new Date();
		if (doesElementExist2(properties.getProperty("AdvSrchFromDate"), 5)) {
			WebElement AdvFromDate = driver.findElement(By.cssSelector(properties.getProperty("AdvSrchFromDate")));
			Highlight(AdvFromDate);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", AdvFromDate);				
			FromDateEntered = dateFormat.parse("20/08/2015");			
			AdvFromDate.sendKeys(FromDateEntered.toString());
			log.logLine(Testname, false, "Click on AdvSrchFromDate is Successful");
		} else {
			log.logLine(Testname, true, "Click on AdvSrchFromDate is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on AdvSrchFromDate is failed");
		}		
		return FromDateEntered ;
	}

	public Date EnterAdvanceToDate(String Testname) throws Exception {
		//	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		Date ToDateEntered = new Date();
		if (doesElementExist2(properties.getProperty("AdvSrchToDate"), 5)) {
			WebElement AdvToDate = driver.findElement(By.cssSelector(properties.getProperty("AdvSrchToDate")));
			Highlight(AdvToDate);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", AdvToDate);	
			ToDateEntered = Calendar.getInstance().getTime();				
			AdvToDate.sendKeys(ToDateEntered.toString());
			log.logLine(Testname, false, "Click on AdvSrchToDate is Successful");
		} else {
			log.logLine(Testname, true, "Click on AdvSrchToDate is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on AdvSrchToDate is failed");
		}
		return ToDateEntered ;
	}


	public void ClickAdvanceSearch_SearchBtn(String Testname) throws Exception {
		Thread.sleep(2000);
		if (doesElementExist("html/body/div[1]/div[1]/div[1]/div/div/div[1]/div[5]/table/tbody/tr/td[2]/button", 5)) {
			WebElement SrcBtn = driver.findElement(By.xpath("html/body/div[1]/div[1]/div[1]/div/div/div[1]/div[5]/table/tbody/tr/td[2]/button"));
			Highlight(SrcBtn);
			//SrcBtn.click();
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", SrcBtn);	
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on SearchBtn is Successful");
		} else {
			log.logLine(Testname, true, "Click on SearchBtn is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Click on SearchBtn is failed");
		}
	}

	public int CalculateNumOfRowsInTable(String Testname) throws Exception{
		NumOfRowsInTable=0 ;
		//if ((doesElementExist2(("div div+div div+div div ul li span[class='k-state-selected']"), 5))) {			    
		//	WebElement Tabl = driver.findElement(By.cssSelector(("div div+div div+div div ul li span[class='k-state-selected']")));
		//		Highlight(Tabl);
		List<WebElement> Rows = driver.findElements(By.cssSelector(("div[id='management-campaigns-grid'] table[role='grid'] tbody[role='rowgroup'] tr[role='row']")));
		NumOfRowsInTable = Rows.size() ;
		log.logLine(Testname, false, "Number of Rows in the table after search are "+NumOfRowsInTable);
		//}
		return NumOfRowsInTable ;
	}

	public boolean ValidateSearchResultEmpty(String Testname) throws Exception {

		Thread.sleep(4000);
		if (doesElementExist(properties.getProperty("NoItems"), 5)) {
			String NoItems = driver.findElement(By.xpath(properties.getProperty("NoItems"))).getText();;
			//((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			log.logLine(Testname, false, "Grid Displaying as ------> "+NoItems+"  when searched with wrong Campaign Name");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the Archives is failed");
			//throw new Exception("Clicking on OK button to view the Archives is failed");

		}
		return true;
	}

	public boolean ValidateTmpltnmeinTmpltmngrmodule(String Testname) throws Exception {

		Thread.sleep(4000);
		Actions action = new Actions(driver);
		if ((doesElementExist(("html/body/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[6]/a"), 5))) {			    
			WebElement TemplateName = driver.findElement(By.xpath(("html/body/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[6]/a")));	
			Highlight(TemplateName);
			TemplateName1=driver.findElement(By.xpath(("html/body/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[6]/a"))).getText();
			log.logLine(Testname, false, "Reading the Template Name as" +TemplateName1);
			//((JavascriptExecutor) driver).executeScript("arguments[0].click()", TemplateName1);
			action.click(TemplateName).perform();
			log.logLine(Testname, false, "Clicking on "+TemplateName1+" is successful");

		} else {
			log.logLine(Testname, true, "Clicking on "+TemplateName1+" is failed");
			//throw new Exception("Clicking on OK button to view the Archives is failed");
		}

		return true;
	}


	public boolean ValidateTemplateName(String Testname ) throws Exception {

		Thread.sleep(15000);
		driver.switchTo().frame("iframeContainer");
		String[] EmlType = new String[100];
		String row = "li";

		if(doesElementExist(".//*[@id='ddl-management-saved-types-div']", 5)){
			driver.findElement(By.xpath(".//*[@id='ddl-management-saved-types-div']"));

			List<WebElement> DataCnt2= driver.findElements(By.xpath(".//*[@id='ul-management-saved-types']/li"));

			Thread.sleep(2000);
			if(doesElementExist2("ul "+row+" div[class='grid-btns'] div", 5)){
				for(int i = 0; i < DataCnt2.size(); i++) {
					if(doesElementExist2("ul "+row+" div[class='grid-btns'] div", 5)){
						EmlType[i] = driver.findElement(By.cssSelector("ul "+row+" div[class='grid-btns'] div")).getText();

						//************************************************Find Email type with account no.
						if(EmlType[i].equals(TemplateName1)){

							log.logLine(Testname, false, "Iterating through the Rows....and Reading the Type in \"Saved Types\" as "+TemplateName1);
							log.logLine(Testname, false, "The "+EmlType[i]+" Matches with " +TemplateName1);


							if (doesElementExist2("ul "+row+" div button+button[class='k-button btn-management-saved-template-edit']", 5)) {
								String edit=driver.findElement(By.cssSelector("ul "+row+" div button+button[class='k-button btn-management-saved-template-edit']")).getAttribute("title");
								log.logLine(Testname, false, "The "+edit+" Button Present under savedtypes of Text Template");
							}

							if (doesElementExist2("ul "+row+" div button[class='k-button btn-management-saved-template-promote']", 5)) {
								String promote=driver.findElement(By.cssSelector("ul "+row+" div button[class='k-button btn-management-saved-template-promote']")).getAttribute("title");
								log.logLine(Testname, false, "The "+promote+" Button Present under savedtypes of Text Template");
							}

							break;
						}
						row = row + "+li";
					}
				}

			}
		}

		return true;
	}


	public boolean Campaigndetailsaccordion(String Testname) throws Exception {

		Thread.sleep(4000);
		Actions action = new Actions(driver);

		if ((doesElementExist(("html/body/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[2]"), 5))) {			    
			WebElement rndte = driver.findElement(By.xpath(("html/body/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[2]")));	
			Highlight(rndte);
			Rundate=driver.findElement(By.xpath(("html/body/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[2]"))).getText();
			if(Rundate.isEmpty()){
				log.logLine(Testname, true, "The Rundate is still not displayed as the status change still didnot happen");
			}
			else{
				String array[]=Rundate.split("AM");
				String dte=array[0].trim();
				String arr[]=dte.split("\\s");
				Finalrundate=arr[0].trim();
				 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
				 date1 =dateFormat.parse(Finalrundate);
			
				 }}

		else {
			log.logLine(Testname, true, "Rundate column doesnot exist");
			throw new Exception("Rundate column doesnot exist");
		}

		if ((doesElementExist(("html/body/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[6]"), 5))) {			    
			WebElement TemplateName = driver.findElement(By.xpath(("html/body/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[6]")));	
			Highlight(TemplateName);
			TemplateName1=driver.findElement(By.xpath(("html/body/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[6]"))).getText();
		} else {
			log.logLine(Testname, true, "Clicking on "+TemplateName1+" is failed");
			//throw new Exception("Clicking on OK button to view the Archives is failed");
		}

		if (doesElementExist2(properties.getProperty("Chooseaction"), 5)) {
			WebElement chseactn = driver.findElement(By.cssSelector(properties.getProperty("Chooseaction")));
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", chseactn);	  
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on Chooseaction dropdown is Successful");
		} else {
			log.logLine(Testname, true, "Click on Chooseaction dropdown is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Click on AdvancedsearchPopup is failed");
		}

		if (doesElementExist2(properties.getProperty("CmpnActionListbox"), 5)) {
			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("CmpnActionListbox")));
			for (WebElement lnk:selopts) {
				if (lnk.getText().contains("View")) {
					lnk.click();
					//((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(4000);
					log.logLine(Testname, false, "Selecting the Campaign Action from the drop down is successful");
					break;
				}				
			} 
		}

		if (doesElementExist2(properties.getProperty("Campaigndetails"), 5)) {
			WebElement chseactn = driver.findElement(By.cssSelector(properties.getProperty("Campaigndetails")));
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", chseactn);	  
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on Campaign details accordion is Successful");
		} else {
			log.logLine(Testname, true, "Click on Campaign details accordion is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Click on AdvancedsearchPopup is failed");
		}

		if (doesElementExist2(properties.getProperty("Vrfytempnmeincampgndtl"), 5)) {
			String Tempnme = driver.findElement(By.cssSelector(properties.getProperty("Vrfytempnmeincampgndtl"))).getText();

			if(Tempnme.contains(TemplateName1)){
				log.logLine(Testname, false, "Template name "+Tempnme+" Matches with the "+TemplateName1+" in Campaign details accordion");
			}else {
				log.logLine(Testname, true, "Template name does not matches in Campaign details accordion");
			}

		} else {
			log.logLine(Testname, true, "Click on Campaign details accordion is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Click on AdvancedsearchPopup is failed");
		}

		if (doesElementExist2(properties.getProperty("Processingdetail"), 5)) {
			WebElement chseactn = driver.findElement(By.cssSelector(properties.getProperty("Processingdetail")));
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", chseactn);	  
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on Processing detail accordion is Successful");
		} else {
			log.logLine(Testname, true, "Click on Processing detail accordion is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Click on AdvancedsearchPopup is failed");
		}

		if (doesElementExist2(properties.getProperty("Vrfyprcssndtlrndte"), 5)) {
			String rndte = driver.findElement(By.cssSelector(properties.getProperty("Vrfyprcssndtlrndte"))).getText();
			 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
			 Date date2 =dateFormat.parse(rndte);
			if(date2.equals(date1)){
				log.logLine(Testname, false, "Run date "+rndte+" Matches with the "+Finalrundate+" in Campaign details accordion");
			}else {
				log.logLine(Testname, true, "Run date does not matches in Campaign details accordion");
			}

		} else {
			log.logLine(Testname, true, "Click on Processing details accordion is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Click on AdvancedsearchPopup is failed");
		}


		if (doesElementExist2(properties.getProperty("Campaignhistory"), 5)) {
			WebElement chseactn = driver.findElement(By.cssSelector(properties.getProperty("Campaignhistory")));
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", chseactn);	  
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on Campaign history detail accordion is Successful");
		} else {
			log.logLine(Testname, true, "Click on Campaign history detail accordion is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Click on AdvancedsearchPopup is failed");
		}	

		// Closing back all the accordion modules

		if (doesElementExist2(properties.getProperty("Campaignhistory"), 5)) {
			WebElement chseactn = driver.findElement(By.cssSelector(properties.getProperty("Campaignhistory")));
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", chseactn);	  
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on Campaign history detail accordion is Successful");
		} else {
			log.logLine(Testname, true, "Click on Campaign history detail accordion is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Click on AdvancedsearchPopup is failed");
		}	

		if (doesElementExist2(properties.getProperty("Processingdetail"), 5)) {
			WebElement chseactn = driver.findElement(By.cssSelector(properties.getProperty("Processingdetail")));
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", chseactn);	  
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on Processing detail accordion is Successful");
		} else {
			log.logLine(Testname, true, "Click on Processing detail accordion is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Click on AdvancedsearchPopup is failed");
		}


		if (doesElementExist2(properties.getProperty("Campaigndetails"), 5)) {
			WebElement chseactn = driver.findElement(By.cssSelector(properties.getProperty("Campaigndetails")));
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", chseactn);	  
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on Campaign details accordion is Successful");
		} else {
			log.logLine(Testname, true, "Click on Campaign details accordion is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Click on AdvancedsearchPopup is failed");
		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Hideaccordion"), 5)) {
			WebElement chseactn = driver.findElement(By.cssSelector(properties.getProperty("Hideaccordion")));
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", chseactn);	  
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on Hide accordion button is Successful");
		} else {
			log.logLine(Testname, true, "Click on Hideaccordion button is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Click on AdvancedsearchPopup is failed");
		}


		if (doesElementExist2(properties.getProperty("AdvancedsearchPopup"), 5)) {
			WebElement AdvsearchPopup = driver.findElement(By.cssSelector(properties.getProperty("AdvancedsearchPopup")));
			Highlight(AdvsearchPopup);
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", AdvsearchPopup);	  
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on AdvancedsearchPopup is Successful");
		} else {
			log.logLine(Testname, true, "Click on AdvancedsearchPopup is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Click on AdvancedsearchPopup is failed");
		}

		if (doesElementExist2(properties.getProperty("RunDateclndr"), 5)) {
			WebElement rndte = driver.findElement(By.cssSelector(properties.getProperty("RunDateclndr")));
			Highlight(rndte);
			rndte.click();
			//((JavascriptExecutor) driver).executeScript("arguments[0].click()", rndte);				
			rndte.sendKeys(Finalrundate);
			log.logLine(Testname, false, "Entering Rundate is Successful");
		} else {
			log.logLine(Testname, true, "Entering Rundate is failed");
			driver.switchTo().defaultContent();
		}		


		Thread.sleep(2000);
		if (doesElementExist("html/body/div[1]/div[1]/div[1]/div/div/div[1]/div[5]/table/tbody/tr/td[2]/button", 5)) {
			WebElement SrcBtn = driver.findElement(By.xpath("html/body/div[1]/div[1]/div[1]/div/div/div[1]/div[5]/table/tbody/tr/td[2]/button"));
			Highlight(SrcBtn);
			//SrcBtn.click();
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", SrcBtn);	
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on SearchBtn is Successful");
		} else {
			log.logLine(Testname, true, "Click on SearchBtn is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Click on SearchBtn is failed");
		}


		if ((doesElementExist(("html/body/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[2]"), 5))) {			    
			WebElement rndte = driver.findElement(By.xpath(("html/body/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[2]")));	
			String Rndteverfy=driver.findElement(By.xpath(("html/body/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[2]"))).getText();

			if(Rndteverfy.contains(Finalrundate)){
				log.logLine(Testname, false, "Advance search Run date "+Rndteverfy+" Matches with the "+Finalrundate+" in the grid");
			}else {
				log.logLine(Testname, true, "Advance search Run date does not matches in the grid");
			}

		} else {
			log.logLine(Testname, true, "Advance search Run date does not matches in the grid");
			//throw new Exception("Clicking on OK button to view the Archives is failed");
		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("AdvancedsearchPopup"), 5)) {
			WebElement AdvsearchPopup = driver.findElement(By.cssSelector(properties.getProperty("AdvancedsearchPopup")));
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", AdvsearchPopup);	  
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on AdvancedsearchPopup is Successful");
		} else {
			log.logLine(Testname, true, "Click on AdvancedsearchPopup is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Click on AdvancedsearchPopup is failed");
		}

		if (doesElementExist2(properties.getProperty("Clearbtn"), 5)) {
			WebElement AdvsearchPopup = driver.findElement(By.cssSelector(properties.getProperty("Clearbtn")));
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", AdvsearchPopup);	  
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on Clear button is Successful");
		} else {
			log.logLine(Testname, true, "Click on Clear button is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Click on AdvancedsearchPopup is failed");
		}

		Thread.sleep(2000);
		if (doesElementExist("html/body/div[1]/div[1]/div[1]/div/div/div[1]/div[5]/table/tbody/tr/td[2]/button", 5)) {
			WebElement SrcBtn = driver.findElement(By.xpath("html/body/div[1]/div[1]/div[1]/div/div/div[1]/div[5]/table/tbody/tr/td[2]/button"));
			Highlight(SrcBtn);
			//SrcBtn.click();
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", SrcBtn);	
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on SearchBtn is Successful");
		} else {
			log.logLine(Testname, true, "Click on SearchBtn is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Click on SearchBtn is failed");
		}

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("Alertpopup"), 5)) {
			WebElement eDelive = driver.findElement(By.cssSelector(properties.getProperty("Alertpopup")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
			log.logLine(Testname, false, "Click on Alert pop up is Successful");
		} else {
			log.logLine(Testname, true, "Click on Alert pop up is failed");
			throw new Exception("Click on Alert pop up is failed");
		}
		Thread.sleep(10000);
		return true;
	}


	public boolean TemplatesortAscending(String Testname) throws Exception {

		String[] Sort = new String[150];
		int length = Sort.length;
		String row = "tr";

		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("Templatearrow"), 5)) {
			WebElement eDelive = driver.findElement(By.xpath(properties.getProperty("Templatearrow")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
			log.logLine(Testname, false, "Click on Arrow button on Template column is Successful");
		} else {
			log.logLine(Testname, true, "Click on Arrow button on Template column is failed");
			throw new Exception("Click on Arrow button on Template column is failed");
		}

		List<WebElement> DataCnt= driver.findElements(By.xpath("//div[@id='management-campaigns-grid']/table/tbody/tr"));
		if(doesElementExist2(properties.getProperty("Templatecolumn"), 5)){
			for(int i = 0; i < DataCnt.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+row+" td+td+td+td+td a[id='TemplateLink']")).getText();
				row = row + "+tr";
			}
			log.logLine(Testname, false, "Iterating through the Rows..");
		}


		for (int i = 0; i < DataCnt.size()-1 ; i++) {
			if (Sort[i].compareTo(Sort[i+1])<0) {
				log.logLine(Testname, false, "Lists are in Ascending order");

				if(i>=DataCnt.size()-2)
					break;

			}else if (Sort[i].compareTo(Sort[i+1])>0) {
				log.logLine(Testname, false, "Lists are in Descending order");
				if(i>=DataCnt.size()-2)
					break;	

			}else if (Sort[i].compareTo(Sort[i+1])==0) {
				log.logLine(Testname, false, "Lists are in Same Name");
				if(i>=DataCnt.size()-2)
					break;	

			}
		}


		return true;
	}


	public boolean TemplatesortDescending(String Testname) throws Exception {

		String[] Sort = new String[150];
		int length = Sort.length;
		String row = "tr";

		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("Templatearrow"), 5)) {
			WebElement eDelive = driver.findElement(By.xpath(properties.getProperty("Templatearrow")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
			log.logLine(Testname, false, "Click on Arrow button on Template column is Successful");
		} else {
			log.logLine(Testname, true, "Click on Arrow button on Template column is failed");
			throw new Exception("Click on Arrow button on Template column is failed");
		}

		List<WebElement> DataCnt= driver.findElements(By.xpath("//div[@id='management-campaigns-grid']/table/tbody/tr"));
		if(doesElementExist2(properties.getProperty("Templatecolumn"), 5)){
			for(int i = 0; i < DataCnt.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+row+" td+td+td+td+td a[id='TemplateLink']")).getText();
				row = row + "+tr";
			}
			log.logLine(Testname, false, "Iterating through the Rows..");
		}



		for (int i = 0; i < DataCnt.size()-1 ; i++) {
			if (Sort[i].compareTo(Sort[i+1])<0) {
				log.logLine(Testname, false, "Lists are in Ascending order");

				if(i>=DataCnt.size()-2)
					break;

			}else if (Sort[i].compareTo(Sort[i+1])>0) {
				log.logLine(Testname, false, "Lists are in Descending order");
				if(i>=DataCnt.size()-2)
					break;	

			}else if (Sort[i].compareTo(Sort[i+1])==0) {
				log.logLine(Testname, false, "Lists are in Same Name");
				if(i>=DataCnt.size()-2)
					break;	

			}
		}


		return true;
	}



	public boolean RunDateSortAscending(String Testname) throws Exception {

		String[] Sort = new String[150];
		int length = Sort.length;
		String row = "tr";

		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("Datearrow"), 5)) {
			WebElement eDelive = driver.findElement(By.xpath(properties.getProperty("Datearrow")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
			log.logLine(Testname, false, "Click on Arrow button on Date column is Successful");
		} else {
			log.logLine(Testname, true, "Click on Arrow button on Date column is failed");
			throw new Exception("Click on Arrow button on Date column is failed");
		}

		/*if (doesElementExist(properties.getProperty("Datearrow"), 5)) {
			WebElement eDelive = driver.findElement(By.xpath(properties.getProperty("Datearrow")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
			log.logLine(Testname, false, "Click on Arrow button on Date column is Successful");
		} else {
			log.logLine(Testname, true, "Click on Arrow button on Date column is failed");
			throw new Exception("Click on Arrow button on Date column is failed");
		}*/

		List<WebElement> DataCnt= driver.findElements(By.xpath("//div[@id='management-campaigns-grid']/table/tbody/tr"));
		if(doesElementExist2(properties.getProperty("Datecolumn"), 5)){
			for(int i = 0; i < DataCnt.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+row+" td+td")).getText();
				row = row + "+tr";
			}
			log.logLine(Testname, false, "Iterating through the Rows..");
		}



		for (int i = 0; i < DataCnt.size()-1 ; i++) {
			if (Sort[i].compareTo(Sort[i+1])<0) {
				log.logLine(Testname, false, "Dates are in Ascending order");

				if(i>=DataCnt.size()-2)
					break;

			}else if (Sort[i].compareTo(Sort[i+1])>0) {
				log.logLine(Testname, false, "Dates are in Descending order");
				if(i>=DataCnt.size()-2)
					break;	

			}else if (Sort[i].compareTo(Sort[i+1])==0) {
				log.logLine(Testname, false, "Dates are in Same order");
				if(i>=DataCnt.size()-2)
					break;	

			}
		}


		return true;
	}

	public boolean RunDateSortDescending(String Testname) throws Exception {

		String[] Sort = new String[150];
		int length = Sort.length;
		String row = "tr";

		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("Datearrow"), 5)) {
			WebElement eDelive = driver.findElement(By.xpath(properties.getProperty("Datearrow")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
			log.logLine(Testname, false, "Click on Arrow button on Date column is Successful");
		} else {
			log.logLine(Testname, true, "Click on Arrow button on Date column is failed");
			throw new Exception("Click on Arrow button on Template column is failed");
		}

		List<WebElement> DataCnt= driver.findElements(By.xpath("//div[@id='management-campaigns-grid']/table/tbody/tr"));
		if(doesElementExist2(properties.getProperty("Datecolumn"), 5)){
			for(int i = 0; i < DataCnt.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='management-campaigns-grid'] table tbody "+row+" td+td")).getText();
				row = row + "+tr";
			}
			log.logLine(Testname, false, "Iterating through the Rows..");
		}



		for (int i = 0; i < DataCnt.size()-1 ; i++) {
			if (Sort[i].compareTo(Sort[i+1])<0) {
				log.logLine(Testname, false, "Dates are in Ascending order");

				if(i>=DataCnt.size()-2)
					break;

			}else if (Sort[i].compareTo(Sort[i+1])>0) {
				log.logLine(Testname, false, "Dates are in Descending order");
				if(i>=DataCnt.size()-2)
					break;	

			}else if (Sort[i].compareTo(Sort[i+1])==0) {
				log.logLine(Testname, false, "Dates are in Same Order");
				if(i>=DataCnt.size()-2)
					break;	

			}
		}


		return true;
	}



	/*waitFluent.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='management-campaigns-grid'] div span+span[class='k-pager-info k-label']"))) ;
		String TextFromRowsIntable = null ;
		//	if ((doesElementExist((".//*[@id='management-campaigns-grid']/div[1]/span[2]"), 5))) {	
		if ((doesElementExist2(("div[id='management-campaigns-grid'] div span+span[class='k-pager-info k-label']"), 5))) {
			WebElement RowsIntable = driver.findElement(By.cssSelector(("div[id='management-campaigns-grid'] div span+span[class='k-pager-info k-label']")));
			TextFromRowsIntable = RowsIntable.getText();
			if (TextFromRowsIntable.equalsIgnoreCase("No items to display")){
				log.logLine(Testname, false, "Search result is empty , shows  "+TextFromRowsIntable);
			}else {
				log.logLine(Testname, true, "Search result is not empty ,shows "+TextFromRowsIntable);
			}
		}
		return NumOfRowsInTable ;		
	}
	 */
	public Date ReadRunDateFromGrid(int N) throws Exception {
		Date GridDate = null ;
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		if ((doesElementExist((".//*[@id='management-campaigns-grid']/table/tbody/tr["+N+"]/td[2]"), 5))) {			    
			WebElement RunDt = driver.findElement(By.xpath((".//*[@id='management-campaigns-grid']/table/tbody/tr["+N+"]/td[2]")));
			Highlight(RunDt);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", RunDt);
			String	StrGridDate = RunDt.getText();
			try {
				GridDate = dateFormat.parse(StrGridDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return GridDate;
	}

	public String ReadCampaignNameFromGrid(int N) throws Exception {
		int M=N+1;
		String Browser = Initialization.Browser;
		if ((Browser.equalsIgnoreCase("ie")) || (Browser.equalsIgnoreCase("InternetExplorer"))) {
			if ((doesElementExist2(("td+td+td[role='gridcell']"), 5))) {	
				WebElement CampaignName = driver.findElement(By.cssSelector(("div[id='management-sections-campaign-manager'] div+div[id='management-campaigns-grid-container-left'] div+div[id='management-campaigns-grid'] table tbody tr td+td+td[role='gridcell']")));
				Highlight(CampaignName);
				CampaignNam = CampaignName.getText();
			}
		}else 
			if ((doesElementExist((".//*[@id='management-campaigns-grid']/table/tbody/tr/td[3]"), 5))) {			    
				WebElement CampaignName = driver.findElement(By.xpath((".//*[@id='management-campaigns-grid']/table/tbody/tr["+M+"]/td[3]")));
				Highlight(CampaignName);
				CampaignNam = CampaignName.getText();	
			}
		return CampaignNam;
	}

	public String ReadTemplateNameFromGrid(int N) throws Exception {

		if ((doesElementExist((".//*[@id='management-campaigns-grid']/table/tbody/tr/td[6]"), 5))) {			    
			WebElement TemplateName = driver.findElement(By.xpath((".//*[@id='management-campaigns-grid']/table/tbody/tr/td[6]")));
			Highlight(TemplateName);
			CampaignNam = TemplateName.getText();	
		}
		return CampaignNam;
	}



	/*	public String[] ReadAllCampaignNameFromGrid(String Testname,int NoOfRowsInGridAfterSearch) throws Exception {
		//	waitFluent.until(ExpectedConditions.refresh((".//*[@id='management-campaigns-grid']/table/tbody/tr[N]/td[6]"))) ;	

		//		Page.waitForElement2((".//*[@id='management-campaigns-grid']/table/tbody/tr[N]/td[6]"))			
		String[] CampaignNames = new String[NumOfRowsInTable];
		for (N = 0, i=0 ; N < NoOfRowsInGridAfterSearch ; N++, i++){
			if(N==0){
				if ((doesElementExist(("html/body/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[3]"), 5))) {			    
					WebElement CampaignName = driver.findElement(By.xpath(("html/body/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[3]")));	
					//waitFluent.until(ExpectedConditions.visibilityOf(TemplateName)) ;
					Highlight(CampaignName);
					CampaignNames[i] = CampaignName.getText();	
				}	
			}else{
				if ((doesElementExist((".//*[@id='management-campaigns-grid']/table/tbody/tr[N]/td[3]"), 5))) {			    
					WebElement CampaignName = driver.findElement(By.xpath((".//*[@id='management-campaigns-grid']/table/tbody/tr[N]/td[3]")));
					//waitFluent.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='management-campaigns-grid']/table/tbody/tr[N]/td[6]"))) ;
					Highlight(CampaignName);
					CampaignNames[i] = CampaignName.getText();	
				}
			}			
		}
		return CampaignNames;
	}*/


	public String[] ReadAllCampaignNameFromGrid(String Testname , int NoOfRowsInGridAfterSearch) throws Exception {	
		String[] CampaignNames = new String[NoOfRowsInGridAfterSearch];
		for (N =1, i=0 ; N <= NoOfRowsInGridAfterSearch; N++, i++)
		{
			if ((doesElementExist((".//*[@id='management-campaigns-grid']/table/tbody/tr["+N+"]/td[3]"), 5))) {			    
				WebElement CampaignName = driver.findElement(By.xpath((".//*[@id='management-campaigns-grid']/table/tbody/tr["+N+"]/td[3]")));
				CampaignNames[i] = CampaignName.getText();	
			}

		}
		return CampaignNames;
	}

	public String ReadCampaignNameFromGrid1(int N) throws Exception {
		String Browser = Initialization.Browser;
		if ((Browser.equalsIgnoreCase("ie")) || (Browser.equalsIgnoreCase("InternetExplorer"))) {
			if ((doesElementExist2(("td+td+td[role='gridcell']"), 5))) {	
				WebElement CampaignName = driver.findElement(By.cssSelector(("div[id='management-sections-campaign-manager'] div+div[id='management-campaigns-grid-container-left'] div+div[id='management-campaigns-grid'] table tbody tr td+td+td[role='gridcell']")));
				Highlight(CampaignName);
				CampaignNam = CampaignName.getText();
			}
		}else 
			if ((doesElementExist((".//*[@id='management-campaigns-grid']/table/tbody/tr/td[3]"), 5))) {			    
				WebElement CampaignName = driver.findElement(By.xpath((".//*[@id='management-campaigns-grid']/table/tbody/tr/td[3]")));
				Highlight(CampaignName);
				CampaignNam = CampaignName.getText();	
			}
		return CampaignNam;
	}


	public String[] ReadAllTemplateNameFromGrid(int NoOfRowsInGridAfterSearch) throws Exception {
		//	waitFluent.until(ExpectedConditions.refresh((".//*[@id='management-campaigns-grid']/table/tbody/tr[N]/td[6]"))) ;	

		//		Page.waitForElement2((".//*[@id='management-campaigns-grid']/table/tbody/tr[N]/td[6]"))			
		String[] TemplateNames = new String[NumOfRowsInTable];
		for (N = 0, i=0 ; N < NoOfRowsInGridAfterSearch ; N++, i++){
			if(N==0){
				if ((doesElementExist(("html/body/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[6]"), 5))) {			    
					WebElement TemplateName = driver.findElement(By.xpath(("html/body/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[6]")));	
					//waitFluent.until(ExpectedConditions.visibilityOf(TemplateName)) ;
					Highlight(TemplateName);
					TemplateNames[i] = TemplateName.getText();	
				}	
			}else{
				if ((doesElementExist((".//*[@id='management-campaigns-grid']/table/tbody/tr[N]/td[6]"), 5))) {			    
					WebElement TemplateName = driver.findElement(By.xpath((".//*[@id='management-campaigns-grid']/table/tbody/tr[N]/td[6]")));
					//waitFluent.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='management-campaigns-grid']/table/tbody/tr[N]/td[6]"))) ;
					Highlight(TemplateName);
					TemplateNames[i] = TemplateName.getText();	
				}
			}			
		}
		return TemplateNames;
	}
	/*
	public Collection<List<String>> ReadAllCampaignNameFromGrid2(int NoOfRowsInGridAfterSearch) {
	List<List<String>> CampaignNames = new ArrayList<List<String>>(NoOfRowsInGridAfterSearch);
	add( str, row, col);

	return CampaignNames;
	}

	public void add(String str, int row, int col) {
	    while (row >= CampaignNames.size())
	    	CampaignNames.add(new ArrayList<String>());

	    List<String> row_list = CampaignNames.get(row);
	    while (col >= row_list.size())
	        row_list.add("");

	    row_list.set(col, str);
	}
	 */
	public  void compareDatesByDateMethods(DateFormat dformat, Date SearchedFromDate, Date SearchedToDate, Date GridDate , String Testname  ) throws Exception{

		if (SearchedFromDate.equals(GridDate) || SearchedToDate.equals(GridDate)) {
			System.out.println("Date searched \"From/To\" "+dformat.format(SearchedFromDate) + " and Date in the Result Grid" + dformat.format(GridDate) + " are equal to each other");
		}else if (SearchedFromDate.before(GridDate) && SearchedToDate.after(GridDate)) {
			System.out.println("Grid Date "+dformat.format(GridDate) + " falls between the range  " + dformat.format(SearchedFromDate)+ " and " + dformat.format(SearchedToDate));
			log.logLine(Testname, true, "Grid Date "+dformat.format(GridDate) + " falls between the range  " + dformat.format(SearchedFromDate)+ " and " + dformat.format(SearchedToDate));

		}else {
			System.out.println("Grid Date "+dformat.format(GridDate) + " does not falls between the range  " + dformat.format(SearchedFromDate)+ "and " + dformat.format(SearchedToDate));
			log.logLine(Testname, true, "Grid Date "+dformat.format(GridDate) + " does not fall between the range  " + dformat.format(SearchedFromDate)+ " and " + dformat.format(SearchedToDate));
		}

	}

	public  void compareCampaignNameInSearchAndGrid2(String Testname , int NoOfRowsInGridAfterSearch, String[] allCampaignNamesFromGrid , String CampaignNameSearched) throws Exception{

		for (M = 0 ; M < NoOfRowsInGridAfterSearch ; M++){	
			System.out.println("M counts :"+M);	
			waitFluent.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='management-campaigns-grid']/table/tbody/tr/td[3]")));
			Thread.sleep(1000);
			if (allCampaignNamesFromGrid[M] != null && !allCampaignNamesFromGrid[M].equals("") && allCampaignNamesFromGrid[M].equalsIgnoreCase(CampaignNameSearched)){ 
				// System.out.println("Matched contents :"+allCampaignNamesFromGrid[M] + ":"+ CampaignNameSearched);	
				String nameread = ReadCampaignNameFromGrid(M);
				Thread.sleep(1000);
				log.logLine(Testname, true, "CampaignNames searched  "+ CampaignNameSearched + " matches to the campaign name shown in the grid"+nameread); 
				break;}
			else{
				log.logLine(Testname, false, "Since  "+ CampaignNameSearched + " is an invalid name(negative validation) doesn't matches to the campaign name shown in the grid"); 
			}
		}
		/*{ log.logLine(Testname, true, "Comparison failed,Campaign name searched did not matches to the campaign name shown in the grid");
					throw new Exception("Comparison failed,Campaign name searched did not matches to the campaign name shown in the grid");
	        }*/
	}
	public  void compareCampaignNameInSearchAndGrid(String Testname , int NoOfRowsInGridAfterSearch, String[] allCampaignNamesFromGrid , String CampaignNameSearched) throws Exception{
		String value="true";
		for (M = 0 ; M < NoOfRowsInGridAfterSearch ; M++){	
			System.out.println("M counts :"+M);	
			waitFluent.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='management-campaigns-grid']/table/tbody/tr/td[3]")));
			Thread.sleep(1000);
			if (allCampaignNamesFromGrid[M] != null && !allCampaignNamesFromGrid[M].equals("") && allCampaignNamesFromGrid[M].equalsIgnoreCase(CampaignNameSearched)){ 
				String nameread = ReadCampaignNameFromGrid(M);
				Thread.sleep(1000);
				value="false";
				log.logLine(Testname, false, "CampaignNames searched  "+ CampaignNameSearched + " matches to the campaign name shown in the grid"+nameread); 
				break;}
		}
		if(value.equalsIgnoreCase("true"))
		{ log.logLine(Testname, true, "Comparison failed,Campaign name searched did not matches to the campaign name shown in the grid(While performin created date validation)");
		//throw new Exception("Comparison failed,Campaign name searched did not matches to the campaign name shown in the grid");
		}
	}


	public  void compareCampaignNameInSearchAndGrid1(String Testname , int NoOfRowsInGridAfterSearch, String[] allCampaignNamesFromGrid , String campaigncreatedusinguser1 ) throws Exception{

		for (M = 0 ; M < NoOfRowsInGridAfterSearch ; M++){			
			waitFluent.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='management-campaigns-grid']/table/tbody/tr/td[3]")));
			if (allCampaignNamesFromGrid[M].equalsIgnoreCase(campaigncreatedusinguser1)){ 
				ReadCampaignNameFromGrid(M);
				log.logLine(Testname, false, "CampaignNames searched  "+ campaigncreatedusinguser1 + " matches to the campaign name shown in the grid"); 
				break;}}
		/* {log.logLine(Testname, true, "Comparison failed,Campaign name searched did not matches to the campaign name shown in the grid");
				throw new Exception("Comparison failed,Campaign name searched did not matches to the campaign name shown in the grid");
				}*/}





	public  void compareTemplateNameInSearchAndGrid(String Testname , int NumOfRowsInTable, String[] allTemplateNamesFromGrid , String TemplateNameSearched) throws Exception{
		for (M = 0 ; M < NumOfRowsInTable ; M++){
			waitFluent.until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[6]")));
			try {
				if (allTemplateNamesFromGrid[M].equalsIgnoreCase(TemplateNameSearched)) {
					ReadAllTemplateNameFromGrid(M);
					log.logLine(Testname, false, "TemplateNames searched  "+TemplateNameSearched + " matches to the Template name shown in the grid");

				}else{

				}

			}

			catch ( StaleElementReferenceException e ) {

			}
			catch (  NullPointerException e1 ){

			}
		}
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

	public  String SearchByCampaignName(String Testname , String CampaignNameFromGrid) throws Exception{
		if ((doesElementExist2(("div[id='management-sections-campaign-manager'] div[id='campaign-manager-search'] span[class='k-dropdown-wrap k-state-default'] span[class='k-input'] "), 5))) {			    
			WebElement drpdownCampaignName = driver.findElement(By.cssSelector(("div[id='management-sections-campaign-manager'] div[id='campaign-manager-search'] span[class='k-dropdown-wrap k-state-default'] span[class='k-input'] ")));
			Highlight(drpdownCampaignName);
			drpdownCampaignName.click();
			List<WebElement> listSerachCriteria = driver.findElements(By.cssSelector(("div ul li[class='k-item']")));
			for (WebElement li: listSerachCriteria)
			{		if (li.getText().equalsIgnoreCase("Campaign Name"))
			{	
				Thread.sleep(2000);
				li.click();
				break;
			}
			}	
			WebElement Txt = driver.findElement(By.xpath(("html/body/div[1]/div[1]/div[1]/div/div/div[1]/div[4]/div[2]/div/input[1]")));
			Txt.sendKeys(CampaignNameFromGrid);	
			SendKeysSearchTextCampaignName = Txt.getText() ;
			log.logLine(Testname, false, "Click on Search By Campaign name in the dropdown successful");
		}else {
			log.logLine(Testname, true, "Click on Search By Campaign name in the dropdown failed");
			throw new Exception("Click on Search By Campaign name in the dropdown failed");
		}
		return CampaignNameFromGrid ;
	}
	public String SearchByTemplateName(String Testname,String Templatenamejustcreated) throws Exception {
		if (doesElementExist2(properties.getProperty("srchcrit"), 5)) {
			WebElement drpdownTemplatName = driver.findElement(By.cssSelector(properties.getProperty("srchcrit")));
			Highlight(drpdownTemplatName);
			drpdownTemplatName.click();
			List<WebElement> listSerachCriteria = driver.findElements(By.cssSelector(("div ul li[class='k-item']")));
			for (WebElement li : listSerachCriteria) {
				if (li.getText().equalsIgnoreCase("Template Name")) {
					Thread.sleep(2000);
					li.click();
					break;
				}
			}
			WebElement Txt = driver.findElement(By.xpath(properties.getProperty("srchcrittxtbx")));;
			Highlight(Txt);
			Txt.clear();
			Txt.sendKeys(Templatenamejustcreated);
			log.logLine(Testname, false,"Click on Search By Template name in the dropdown successful");
		} else {
			log.logLine(Testname, true,"Click on Search By Template name in the dropdown failed");
			throw new Exception("Click on Search By Template name in the dropdown failed");
		}
		return Templatenamejustcreated;
	}


	public  String SearchByTemplateName(String Testname) throws Exception{
		if ((doesElementExist2(("div[id='management-sections-campaign-manager'] div[id='campaign-manager-search'] span[class='k-dropdown-wrap k-state-default'] span[class='k-input'] "), 5))) {			    
			WebElement drpdownCampaignName = driver.findElement(By.cssSelector(("div[id='management-sections-campaign-manager'] div[id='campaign-manager-search'] span[class='k-dropdown-wrap k-state-default'] span[class='k-input'] ")));
			Highlight(drpdownCampaignName);
			drpdownCampaignName.click();
			List<WebElement> listSerachCriteria = driver.findElements(By.cssSelector(("div ul li[class='k-item']")));
			for (WebElement li: listSerachCriteria)
			{		if (li.getText().equalsIgnoreCase("Template Name"))
			{	
				Thread.sleep(2000);
				li.click();
				break;
			}
			}	
			WebElement Txt = driver.findElement(By.xpath(("html/body/div[1]/div[1]/div[1]/div/div/div[1]/div[4]/div[2]/div/input[1]")));
			Highlight(Txt);
			Txt.clear();
			Txt.sendKeys(TemplateNameFromGrid);	
			log.logLine(Testname, false, "Click on Search By Template name in the dropdown successful");
		}else {
			log.logLine(Testname, true, "Click on Search By Template name in the dropdown failed");
			throw new Exception("Click on Search By Template name in the dropdown failed");
		}
		return TemplateNameFromGrid ;
	}

	public String readColumnData(String fieldname, String inputsheet) throws IOException  {

		this.inputFile = inputFile;
		File inputWorkbook = new File(inputFile);
		Workbook w;
		String cellData = null;
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			// Get the first sheet
			Sheet sheet = w.getSheet(inputsheet);
			// Loop over first 10 column and lines

			for (int j = 0; j < sheet.getColumns(); j++) {				
				Cell cell = sheet.getCell(j, 0);				
				if (cell.getContents().equals(fieldname)) {					
					cellData = sheet.getCell(j, 1).getContents();
					break;
				}				
			}		
		} catch (BiffException e) {
			e.printStackTrace();
		}		
		return cellData;
	}


}

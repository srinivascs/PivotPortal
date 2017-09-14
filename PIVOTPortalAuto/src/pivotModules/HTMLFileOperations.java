package pivotModules;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.Set;
import java.io.File;

import launchAuto.CreateAppData;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

//import com.sun.java.util.jar.pack.Package.File;


public class HTMLFileOperations extends Page{

	int paperID = (int) Math.round(Math.random() * (9999 - 1000 + 1) + 1000);

	public String AccNo = Integer.toString(paperID);		

	WebDriverWait wait = new WebDriverWait(driver, 60);

	public static String val1, val2, val3, val4, val5, val6, val7, val8, val9, val10;
	public static String ext1, ext2, ext3, ext4, ext5, ext6, ext7, ext8, ext9, ext10;
	public static String plntyp1,  bleky1, blunum, order;
	public static String downloadPath;
	public static String download;
	public static String Compurtnme;


	public HTMLFileOperations(WebDriver driver, Log log) throws InvalidFormatException, IOException {

		super(driver, log);

	}

	@Override

	protected void load() {}

	@Override

	protected void isLoaded() throws Error {}

	public boolean FileOperationspage(String AccNo, String Testname ) throws Exception {

		
		
        
		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));		
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		driver.switchTo().defaultContent();

		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");
			throw new Exception("Clicking on Cancel button in Client/App popup is failed");
		}


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

		waitForElement(properties.getProperty("selClint1"));

		boolean CliSelected = false;
		String ApplicId = test.readColumnData("ApplicationId", sheetname);

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
						if (lnk.getText().equals(ClntName)) {
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


		/*boolean AppSelected = false;

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
	    }*/

		PleasewaitDisappear();		

		//Click on Ok button
		if (doesElementExist2(properties.getProperty("Okbtn"), 5)) {		
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Okbtn")));			
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);			
			PleasewaitDisappear();			
			log.logLine(Testname, false, "Clicking on OK button");

		} else {		
			log.logLine(Testname, true, "Clicking on OK button to view the Reports is failed");			
			throw new Exception("Clicking on OK button to view the Reports is failed");

		}

		//wait for the file to upload

		/*long timenow = System.currentTimeMillis();
 		 long testime = timenow - Initialization.startTime;
 		 int totalTime =(int) ((testime/(1000*60)));	 

 		 if (18 > totalTime) {
 			 int WaitTime = 18 - totalTime; 
 			 log.logLine(Testname, false, "Going to wait for "+WaitTime +"minutes, please wait...");
 			 Thread.sleep(WaitTime*1000*60);		 
 		 }*/


		if ((doesElementExist2(properties.getProperty("Adminlnk"), 5))) {		

			WebElement oldpivt = driver.findElement(By.cssSelector(properties.getProperty("Adminlnk")));			
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", oldpivt);			
			log.logLine(Testname, false, "Clicking on Legacy Admin..");		
		}	


		Set<String> handles = driver.getWindowHandles();		
		String firstWinHandle = driver.getWindowHandle();		
		handles.remove(firstWinHandle);		
		boolean browserexist = handles.iterator().hasNext();		

		if (browserexist) {

			String winHandle=handles.iterator().next();

			if (winHandle!=firstWinHandle){

				driver.switchTo().window(winHandle);		
				driver.manage().window().maximize();
				Thread.sleep(2000);			


				if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetExplorer"))) {
					if (doesElementExist2(properties.getProperty("OverrideLnk"), 5)) {
						driver.get("javascript:document.getElementById('overridelink').click();");
						Thread.sleep(8000);
					}
				}

				waitForElement(properties.getProperty("ClientAppAdminLink"));
			}

		}

		if (doesElementExist2(properties.getProperty("ClientAppAdminLink"), 5)) {

			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("ClientAppAdminLink")));			
			log.logLine(Testname, false, "Clicking on ClientAppAdminLink from the leftContent Menu");			
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(5000);
		} else {		
			log.logLine(Testname, true, "Unable to click on the ClientAppAdminLink from the leftContent Menu");			
			throw new Exception("Unable to click on the ClientAppAdminLink from the leftContent Menu");

		}


		waitForElement(properties.getProperty("AppIdTxtBox"));


		if (doesElementExist2(properties.getProperty("AppIdTxtBox"), 5)) {

			WebElement AppIdTBox = driver.findElement(By.cssSelector(properties.getProperty("AppIdTxtBox")));			
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", AppIdTBox);			
			AppIdTBox.clear();
			AppIdTBox.sendKeys(ApplicId);			
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entering the description in the \"Description\" text box of Email Builder Section ");

		} else {		
			log.logLine(Testname, true, "Unable to Enter the description in the \"Description\" text box of Email Builder Section ");			
			throw new Exception("Unable to Enter the description in the \"Description\" text box of Email Builder Section ");

		}

		if (doesElementExist2(properties.getProperty("ToolNameFromDrpDwn"), 5)) {

			Select ToolNameDrpDwn = new Select(driver.findElement(By.cssSelector(properties.getProperty("ToolNameFromDrpDwn"))));			
			ToolNameDrpDwn.selectByVisibleText("PIVOT eDelivery");
			Thread.sleep(3000);
			log.logLine(Testname, false, "Selecting PIVOT eDelivery option from the \"Any Tool\" dropdown list on Admin Page ");

		} else {

			log.logLine(Testname, true, "Unable to Select PIVOT eDelivery option from the \"Any Tool\" dropdown list on Admin Page ");		
			throw new Exception("Unable to Select PIVOT eDelivery option from the \"Any Tool\" dropdown list on Admin Page ");

		}

		if (doesElementExist2(properties.getProperty("SearchButtonInAdmin"), 5)) {

			WebElement SearchBtnAdmin = driver.findElement(By.cssSelector(properties.getProperty("SearchButtonInAdmin")));			
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", SearchBtnAdmin);
			waitUntilRetrive();
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the Search Button on Admin Page");			
		} else {		
			log.logLine(Testname, true, "Unable to Click on the Search Button on Admin Page");			
			throw new Exception("Unable to Click on the Search Button on Admin Page");

		}

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(properties.getProperty("ModifyToolButton"))));		
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(properties.getProperty("ModifyToolButton"))));

		if (doesElementExist2(properties.getProperty("ModifyToolButton"), 5)) {

			WebElement ModifyTlBtn = driver.findElement(By.cssSelector(properties.getProperty("ModifyToolButton")));			
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ModifyTlBtn);
			waitUntilRetrive();
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the ModifyToolButton on Admin Page");

		} else {

			log.logLine(Testname, true, "Unable to Click on the ModifyToolButton on Admin Page");		
			throw new Exception("Unable to Click on the ModifyToolButton on Admin Page");

		}

		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(properties.getProperty("EditEDeliveryApplicationSpecificData"))));	

		if (doesElementExist2(properties.getProperty("EditEDeliveryApplicationSpecificData"), 5)) {

			WebElement EditEDelAppSpecData = driver.findElement(By.cssSelector(properties.getProperty("EditEDeliveryApplicationSpecificData")));			
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", EditEDelAppSpecData);
			PleasewaitDisappear();
			waitUntilRetrive();
			Thread.sleep(10000);
			log.logLine(Testname, false, "Clicked on the Edit Button for EDelivery Application SpecificData on Admin Page");

		} else {

			log.logLine(Testname, true, "Unable to Click on the Edit Button for EDelivery Application SpecificData on Admin Page");			
			//throw new Exception("Unable to Click on the Edit Button for EDelivery Application SpecificData on Admin Page");

		}



		if (doesElementExist2(properties.getProperty("LoadConfirmationCheckBox"), 5)) {

			WebElement LoadConCheckBox = driver.findElement(By.cssSelector(properties.getProperty("LoadConfirmationCheckBox")));			
			if (!(LoadConCheckBox.isSelected())){			

				((JavascriptExecutor) driver).executeScript("arguments[0].click()", LoadConCheckBox);
				waitUntilRetrive();
				log.logLine(Testname, false, "Clicked on the Load Confirmation CheckBox EDelivery Application Specific Data on Admin Page");
			} else {
				log.logLine(Testname, false, "Load Confirmation CheckBox EDelivery Application Specific Data on Admin Page is already selected");

			}

		} else {

			log.logLine(Testname, true, "Unable to Click on the LoadConfirmationCheckBox EDelivery Application Specific Data on Admin Page");						
			//	throw new Exception("Unable to Click on the LoadConfirmationCheckBox EDelivery Application Specific Data on Admin Page");

		}


		//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(properties.getProperty("LoadConfirmationDrpDwn"))));	



		if ((Initialization.EnvirSite.equals("TEST")) || (Initialization.EnvirSite.equals("Test")) || (Initialization.EnvirSite.equals("test"))) {

			if (doesElementExist2(properties.getProperty("LoadConfirmationDrpDwn"), 5)) {

				Select LoadConDrpDwn = new Select(driver.findElement(By.cssSelector(properties.getProperty("LoadConfirmationDrpDwn"))));				
				LoadConDrpDwn.selectByVisibleText("Logan Test");			
			}

		}else if ((Initialization.EnvirSite.equals("STAGE")) || (Initialization.EnvirSite.equals("Stage")) || (Initialization.EnvirSite.equals("stage"))) {

			if (doesElementExist2(properties.getProperty("LoadConfirmationDrpDwn"), 5)) {					
				Select LoadConDrpDwn = new Select(driver.findElement(By.cssSelector(properties.getProperty("LoadConfirmationDrpDwn"))));										
				LoadConDrpDwn.selectByVisibleText("Logan Stage");		
			}
		}else if ((Initialization.EnvirSite.equals("PROD")) || (Initialization.EnvirSite.equals("Prod")) || (Initialization.EnvirSite.equals("prod"))) {
			if (doesElementExist2(properties.getProperty("LoadConfirmationDrpDwn"), 5)) {
				Select LoadConDrpDwn = new Select(driver.findElement(By.cssSelector(properties.getProperty("LoadConfirmationDrpDwn"))));										
				LoadConDrpDwn.selectByVisibleText("Logan Prod");
			}			

			log.logLine(Testname, false, "Selecting Logan Stage option from the \"Load Confirmation\" dropdown list on Admin Page ");			
		} else {

			log.logLine(Testname, true, "Unable to Select Logan Stage option from the \"Load Confirmation\" dropdown list on Admin Page");			
			//	throw new Exception("Unable to Select Logan Stage option from the \"Load Confirmation\" dropdown list on Admin Page");		
		}


		if (doesElementExist2(properties.getProperty("UpdateButton"), 5)) {		

			WebElement UpdateBtn = driver.findElement(By.cssSelector(properties.getProperty("UpdateButton")));			
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", UpdateBtn);
			Thread.sleep(2000);			
			waitUntilRetrive();
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on \" Update \" btn on Admin Page ");

		} else {		
			log.logLine(Testname, true, "Unable to Click on \" Update \" btn on Admin Page ");			
			//	throw new Exception("Unable to Click on \" Update \" btn on Admin Page");		
		}


		driver.close();        
		driver.switchTo().window(firstWinHandle);  

		return true;

	}


	public boolean FileVerification(String AccNo, String Testname ) throws Exception {


		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));		
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();		

		for (int i=1; i<5; i++){		


			Thread.sleep(10000);
			if (doesElementExist2(properties.getProperty("Archivemodule"), 5)) {
				WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Archivemodule")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);	    	 
				Thread.sleep(2000);
				PleasewaitDisappear();
				log.logLine(Testname, false, "Click on Archive submodule button is successfull");
			} else {
				log.logLine(Testname, true, "File Management menu is not displayed");
				driver.switchTo().defaultContent();
				throw new Exception("File Management menu is not displayed");
			}


			driver.switchTo().frame("iframeContainer");	
			Thread.sleep(10000);

			//Clicking on Advance search button	    
			if (doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5)) {
				WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);

				Thread.sleep(1000);
				log.logLine(Testname, false, "Click on Advance Search is successfull");
			} else {
				log.logLine(Testname, true, "Click on Advance Search is failed");
				driver.switchTo().defaultContent();
				// throw new Exception("Click on Advance Search is failed");
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


			if (doesElementExist2(properties.getProperty("Alldts"), 5)) {
				WebElement btn = driver.findElement(By.cssSelector(properties.getProperty("Alldts")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
				log.logLine(Testname, false, "Click on All Date Checkbox is Successfull");
			} else {
				log.logLine(Testname, true, "Click on All Date Checkbox is Failed");
				driver.switchTo().defaultContent();
				throw new Exception("Click on All Date Checkbox is Failed");
			}

			if (i==1){	     
				selectsearch(Testname, "Consumer ID");
			}else if(i==2){
				selectsearch(Testname, "Plan Type"); 
			}else if(i==3){
				selectsearch(Testname, "Blue Key");
			}else if(i==4){
				selectsearch(Testname, "Consumer ID");	 
			}


			/*Thread.sleep(3000);
			 Actions action=new Actions(driver);
			 if (doesElementExist2(properties.getProperty("SearchBy"), 5)) {	    
				WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("SearchBy")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
				Thread.sleep(1000);

				log.logLine(Testname, false, "Clicking on Seacrh criteria Field drop down list in advance search");

				if (doesElementExist2(properties.getProperty("SelFieltOpt"), 5)) {

					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelFieltOpt")));
					for (WebElement lnk:selopts) {
						if (lnk.getText().equals("Consumer ID")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							log.logLine(Testname, false, "Selecting the Consumer ID Field option from the dropdown");
							break;
						}						
					}
				} else {				
					log.logLine(Testname, true, "Selecting the field option Consumer ID is failed");
					driver.switchTo().defaultContent();
					throw new Exception("Selecting the field option Consumer ID is failed");				
				}		

			 }else {
				 log.logLine(Testname, true, "Clicking on the Field is failed");
				 driver.switchTo().defaultContent();
				 throw new Exception("Clicking on the Field is failed");
			 }*/


			if (doesElementExist2(properties.getProperty("Operatorbutton"), 5)) {	    
				WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("Operatorbutton")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);

				Thread.sleep(1000);			
				log.logLine(Testname, false, "Clicking on Seacrh criteria Operator drop down list in advance search");

				if (doesElementExist2(properties.getProperty("SelOpertOpt"), 5)) {
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelOpertOpt")));
					for (WebElement lnk:selopts) {
						if (lnk.getText().equals("Equals")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							log.logLine(Testname, false, "Selecting the \"Equals\" operator option from the dropdown");
							break;
						}						
					}
				} else {				
					log.logLine(Testname, true, "Selecting the \"Equals\" operator option is failed");
					driver.switchTo().defaultContent();
					throw new Exception("Selecting the \"Equals\" operator option is failed");				
				}		
			}else {				
				log.logLine(Testname, true, "Clicking on the operator drop down is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on the operator drop down is failed");				
			}

			/* 
			val1 = "sco750";
			val2 = "SKB-cd75";
			val3 = "skb-752";
			val4 = "sco753";
			 */

			val1 = "sco"+CreateAppData.consId+"0";
			val2 = "SKB-cd"+CreateAppData.consId;
			val3 = "SKB2015";//+CreateAppData.consId+"2"; 		
			val4 = "sco"+CreateAppData.consId+"3";	


			if (doesElementExist2(properties.getProperty("Txtfld"), 5)) {	  
				WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("Txtfld")));
				//Txt.clear();
				if (i==1){
					Txt.sendKeys(val1);
				}else if (i==2){
					Txt.sendKeys(val2);
				}else if (i==3){
					Txt.sendKeys(val3);
				}else if (i==4){
					Txt.sendKeys(val4);
				}
				Thread.sleep(1000);
				log.logLine(Testname, false, "Entered Value in text field ");			
			}else {
				log.logLine(Testname, true,"Unable to enter the text in text field");
				driver.switchTo().defaultContent();
				throw new Exception("Unable to  enter the text in text field");
			}


			if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	 
				WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);

				PleasewaitDisappear();
				Thread.sleep(3000);			
				log.logLine(Testname, false, "Clicking on Search button is successfull");
			} else {
				log.logLine(Testname, true, "Clicking on Search button is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on Search button is failed");
			}


			/*if (doesElementExist2(properties.getProperty("ConsIdCol"), 5)) {	 
				String btnsrch = driver.findElement(By.cssSelector(properties.getProperty("ConsIdCol"))).getText();
				if(i==1) {
					if (btnsrch.equals(val1)){
						log.logLine(Testname, false, "Searched consumer Id exist in the results grid hence uploading file is successful");
					}
				} else if(i==2){
					if (btnsrch.equals(val2)){
						log.logLine(Testname, false, "Searched consumer Id exist in the results grid hence uploading file is successful");
					}
				} else if(i==3){
					if (btnsrch.equals(val3)){
						log.logLine(Testname, false, "Searched consumer Id exist in the results grid hence uploading file is successful");
					}
				} else if(i==4){
					if (btnsrch.equals(val4)){
						log.logLine(Testname, false, "Searched consumer Id exist in the results grid hence uploading file is successful");
					}
				}


			}else {
					log.logLine(Testname, true, "Searched consumer Id does not exist in the results grid hence uploading file is unsuccessful");
					driver.switchTo().defaultContent();
					throw new Exception("Searched consumer Id does not exist in the results grid hence uploading file is unsuccessful");
			}*/

			if ((i==1) || (i==2) || (i==3) ){

				if (doesElementExist2(properties.getProperty("viewDoc"), 5)) {	 
					WebElement vwdoc = driver.findElement(By.cssSelector(properties.getProperty("viewDoc")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", vwdoc);

					PleasewaitDisappear();
					Thread.sleep(10000);				
					log.logLine(Testname, false, "Clicking on Search button is successfull");
				} else {
					log.logLine(Testname, true, "Clicking on Search button is failed");
					driver.switchTo().defaultContent();
					throw new Exception("Clicking on Search button is failed");
				}

			} else if (i==4){

				if (doesElementExist2(properties.getProperty("ChooseAction"), 5)) {	    
					WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("ChooseAction")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
					Thread.sleep(1000);

					log.logLine(Testname, false, "Clicking on choose action drop down list in advance search");

					if (doesElementExist2(properties.getProperty("Seloption"), 5)) {
						List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Seloption")));
						for (WebElement lnk:selopts) {
							if (lnk.getText().equals("View Document")) {
								((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
								log.logLine(Testname, false, "Selecting the \"View Document\"  option from the dropdown");
								break;
							}						
						}
					} else {				
						log.logLine(Testname, true, "Selecting the \"View Document\" option is failed");
						driver.switchTo().defaultContent();
						throw new Exception("Selecting the \"View Document\" option is failed");				
					}		
				}else {				
					log.logLine(Testname, true, "Clicking on the \"View Document\" option is failed");    			
					throw new Exception("Clicking on the operator is failed");				
				}


			}				


			Set<String> handles = driver.getWindowHandles();		
			String firstWinHandle = driver.getWindowHandle();		
			handles.remove(firstWinHandle);		
			boolean browserexist = handles.iterator().hasNext();		

			if (browserexist) {

				String winHandle=handles.iterator().next();		
				if (winHandle!=firstWinHandle){

					driver.switchTo().window(winHandle);		
					driver.manage().window().maximize();
					Thread.sleep(5000);
					log.logLine(Testname, false, "Clicking on the view document link opened the file in new Tab and viewing the document is succesful ");

					driver.close();		
					Thread.sleep(1000);		
					driver.switchTo().window(firstWinHandle);

				}

			}		

		}

		return true;

	}


	public boolean selectsearch(String Testname, String Field)	 throws Exception {		

		Thread.sleep(2000);
		Actions action=new Actions(driver);
		if (doesElementExist2(properties.getProperty("SearchBy"), 5)) {

			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("SearchBy")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);

			Thread.sleep(1000);			
			log.logLine(Testname, false, "Clicking on Seacrh criteria Field drop down list in advance search");

			if (doesElementExist2(properties.getProperty("SelFieltOpt"), 5)) {

				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelFieltOpt")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(Field)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the "+Field+" Field option from the dropdown");
						break;
					}						
				}
			} else {				
				log.logLine(Testname, true, "Selecting the "+Field+" field option  is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Selecting the "+Field+" field option  is failed");				
			}		

		}else {
			log.logLine(Testname, true, "Clicking on the Field is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on the Field is failed");
		}

		return true;
	}



	public boolean FileVerificationDownload(String AccNo, String Testname ) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));		
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		driver.switchTo().frame("iframeContainer");	
		Thread.sleep(1000);

		for (int i=1; i<7; i++) {			

			//Clicking on Advance search button	    
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


			if (doesElementExist2(properties.getProperty("Alldts"), 5)) {
				WebElement btn = driver.findElement(By.cssSelector(properties.getProperty("Alldts")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
				log.logLine(Testname, false, "Click on All Date Checkbox is Successfull");
			} else {
				log.logLine(Testname, true, "Click on All Date Checkbox is Failed");
				driver.switchTo().defaultContent();
				throw new Exception("Click on All Date Checkbox is Failed");
			}

			Thread.sleep(3000);
			if (doesElementExist2(properties.getProperty("SearchBy"), 5)) {	    
				WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("SearchBy")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
				Thread.sleep(1000);

				log.logLine(Testname, false, "Clicking on Seacrh criteria Field drop down list in advance search");

				if (doesElementExist2(properties.getProperty("SelFieltOpt"), 5)) {

					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelFieltOpt")));
					for (WebElement lnk:selopts) {
						if (lnk.getText().equals("Consumer ID")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							log.logLine(Testname, false, "Selecting the Consumer ID Field option from the dropdown");
							break;
						}						
					}
				} else {				
					log.logLine(Testname, true, "Selecting the field option Consumer ID is failed");
					driver.switchTo().defaultContent();
					throw new Exception("Selecting the field option Consumer ID is failed");				
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
						if (lnk.getText().equals("Equals")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							log.logLine(Testname, false, "Selecting the Equals operator option from the dropdown");
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

			ext1 = ".xlsx";
			ext2 = ".doc";
			ext3 = ".docx";
			ext4 = ".xls";
			ext5 = ".pdf";
			ext6 = ".csv";


			val5 = "sco"+CreateAppData.consId+"4";
			val6 = "sco"+CreateAppData.consId+"5";
			val7 = "sco"+CreateAppData.consId+"6";		 
			val8 = "sco"+CreateAppData.consId+"7";	
			val9 = "sco"+CreateAppData.consId+"8";
			val10 = "sco"+CreateAppData.consId+"9";





			if (doesElementExist2(properties.getProperty("Txtfld"), 5)) {	  
				WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("Txtfld")));
				Txt.clear();
				if (i==1){
					Txt.sendKeys(val5);
				}else if (i==2){
					Txt.sendKeys(val6);
				}else if (i==3){
					Txt.sendKeys(val7);
				}else if (i==4){
					Txt.sendKeys(val8);
				}else if (i==5){
					Txt.sendKeys(val9);
				}else if (i==6){
					Txt.sendKeys(val10);
				}
				Thread.sleep(1000);
				log.logLine(Testname, false, "Entered Value in text field ");			
			}else {
				log.logLine(Testname, true,"Unable to enter the text in text field");
				throw new Exception("Unable to  enter the text in text field");
			}			 


			if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	 
				WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
				PleasewaitDisappear();
				Thread.sleep(2000);
				PleasewaitDisappear();
				log.logLine(Testname, false, "Clicking on Search button is successfull");
			} else {
				log.logLine(Testname, true, "Clicking on Search button is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on Search button is failed");
			}


			if (doesElementExist2(properties.getProperty("ConsIdCol"), 5)) {	 
				String btnsrch = driver.findElement(By.cssSelector(properties.getProperty("ConsIdCol"))).getText();
				if(i==1){
					if (btnsrch.equals(val5)){
						log.logLine(Testname, false, "Searched consumer Id exist in the results grid hence downloading file is successful");
					}
				} else if(i==2){
					if (btnsrch.equals(val6)){
						log.logLine(Testname, false, "Searched consumer Id exist in the results grid hence downloading file is successful");
					}
				} else if(i==3){
					if (btnsrch.equals(val7)){
						log.logLine(Testname, false, "Searched consumer Id exist in the results grid hence downloading file is successful");
					}
				} else if(i==4){
					if (btnsrch.equals(val8)){
						log.logLine(Testname, false, "Searched consumer Id exist in the results grid hence downloading file is successful");
					}
				}else if(i==5){
					if (btnsrch.equals(val9)){
						log.logLine(Testname, false, "Searched consumer Id exist in the results grid hence downloading file is successful");
					}
				}else if(i==6){
					if (btnsrch.equals(val10)){
						log.logLine(Testname, false, "Searched consumer Id exist in the results grid hence downloading file is successful");
					}
				}



			}else {
				log.logLine(Testname, true, "Searched consumer Id does not exist in the results grid hence uploading file is unsuccessful");
				throw new Exception("Searched consumer Id does not exist in the results grid hence uploading file is unsuccessful");
			}

			if(i!=5){

				if (doesElementExist2(properties.getProperty("viewDoc"), 5)) {	 
					WebElement vwdoc = driver.findElement(By.cssSelector(properties.getProperty("viewDoc")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", vwdoc);
					Thread.sleep(10000);
					PleasewaitDisappear();
					log.logLine(Testname, false, "Clicking on Search button is successfull");
				} else {
					log.logLine(Testname, true, "Clicking on Search button is failed");
					driver.switchTo().defaultContent();
					throw new Exception("Clicking on Search button is failed");
				}

				//java.lang.Runtime.getRuntime().exec("C:\\Pivot_Portal\\Download.exe");

				//computerName = InetAddress.getLocalHost().getHostName();
				Compurtnme=System.getProperty("user.home");
		        download = ""+Compurtnme+"\\Downloads";
  
				File getLatestFile = getLatestFilefromDir(download);
				String fileName = getLatestFile.getName();

				if (i==1){

					if(fileName.contains(ext1)) {
						log.logLine(Testname, false, "The downloaded file extension matches with the document which has extension "+ext1+" ");
					}else {
						log.logLine(Testname, true, "The downloaded file extension does not matches with the document which has extension "+ext1+" ");
						throw new Exception("The downloaded file extension does not matches with the document which has extension");
					}
				}else if (i==2){
					if(fileName.contains(ext2)) {
						log.logLine(Testname, false, "The downloaded file extension matches with the document which has extension "+ext2+" ");
					}else {
						log.logLine(Testname, true, "The downloaded file extension does not matches with the document which has extension "+ext2+" ");
						throw new Exception("The downloaded file extension does not matches with the document which has extension");
					}
				}else if (i==3){
					if(fileName.contains(ext3)) {
						log.logLine(Testname, false, "The downloaded file extension matches with the document which has extension "+ext3+" ");
					}else {
						log.logLine(Testname, true, "The downloaded file extension does not matches with the document which has extension "+ext3+" ");
						throw new Exception("The downloaded file extension does not matches with the document which has extension");
					}
				}else if (i==4){
					if(fileName.contains(ext4)) {
						log.logLine(Testname, false, "The downloaded file extension matches with the document which has extension "+ext4+" ");
					}else {
						log.logLine(Testname, true, "The downloaded file extension does not matches with the document which has extension "+ext4+" ");
						throw new Exception("The downloaded file extension does not matches with the document which has extension");
					}
				}else if (i==6){
					if(fileName.contains(ext6)) {
						log.logLine(Testname, false, "The downloaded file extension matches with the document which has extension "+ext6+" ");
					}else {
						log.logLine(Testname, true, "The downloaded file extension does not matches with the document which has extension "+ext6+" ");
						throw new Exception("The downloaded file extension does not matches with the document which has extension");
					}
				}		
			}
		}

		return true;		
	}


	private File getLatestFilefromDir(String dirPath){

		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile;
	}


	public boolean VerifyOptions(String AccNo, String Testname ) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));		
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		/*val1 = "sco750";
		val2 = "sco751";
		val3 = "sco752";
		val4 = "sco753";
		val5 = "sco754";
		val6 = "sco755";
		val7 = "sco756";
		val8 = "sco757";
		val9 = "sco758";
		val10 = "sco759";*/

		val1 = "sco"+CreateAppData.consId+"0";
		val2 = "sco"+CreateAppData.consId+"1";
		val3 = "sco"+CreateAppData.consId+"2";
		val4 = "sco"+CreateAppData.consId+"3";
		val5 = "sco"+CreateAppData.consId+"4";
		val6 = "sco"+CreateAppData.consId+"5";
		val7 = "sco"+CreateAppData.consId+"6";
		val8 = "sco"+CreateAppData.consId+"7";
		val9 = "sco"+CreateAppData.consId+"8";
		val10 = "sco"+CreateAppData.consId+"9";

		for(int i=1; i<10; i++){

			if (doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5)) {
				WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
				Thread.sleep(1000);
				log.logLine(Testname, false, "Click on Advance Search is successfull");
			} else {
				log.logLine(Testname, true, "Click on Advance Search is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Click on Advance Search is failed");
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


			if (doesElementExist2(properties.getProperty("Alldts"), 5)) {
				WebElement btn = driver.findElement(By.cssSelector(properties.getProperty("Alldts")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
				log.logLine(Testname, false, "Click on All Date Checkbox is Successfull");
			} else {
				log.logLine(Testname, true, "Click on All Date Checkbox is Failed");
				driver.switchTo().defaultContent();
				throw new Exception("Click on All Date Checkbox is Failed");
			}

			Thread.sleep(3000);
			Actions action=new Actions(driver);
			if (doesElementExist2(properties.getProperty("SearchBy"), 5)) {	    
				WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("SearchBy")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
				Thread.sleep(1000);

				log.logLine(Testname, false, "Clicking on Seacrh criteria Field drop down list in advance search");

				if (doesElementExist2(properties.getProperty("SelFieltOpt"), 5)) {

					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelFieltOpt")));
					for (WebElement lnk:selopts) {
						if (lnk.getText().equals("Consumer ID")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							log.logLine(Testname, false, "Selecting the Consumer ID Field option from the dropdown");
							break;
						}						
					}
				} else {				
					log.logLine(Testname, true, "Selecting the field option Consumer ID is failed");
					driver.switchTo().defaultContent();
					throw new Exception("Selecting the field option Consumer ID is failed");				
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
						if (lnk.getText().equals("Equals")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							log.logLine(Testname, false, "Selecting the Equals operator option from the dropdown");
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


			if (doesElementExist2(properties.getProperty("Txtfld"), 5)) {	  
				WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("Txtfld")));
				Txt.clear();
				if (i==1){
					Txt.sendKeys(val1);
				}else if (i==2){
					Txt.sendKeys(val2);
				}else if (i==3){
					Txt.sendKeys(val3);
				}else if (i==4){
					Txt.sendKeys(val4);
				}else if (i==5){
					Txt.sendKeys(val5);
				}else if (i==6){
					Txt.sendKeys(val6);
				}else if (i==7){
					Txt.sendKeys(val7);
				}else if (i==8){
					Txt.sendKeys(val8);
				}else if (i==9){
					Txt.sendKeys(val10);
				}
				Thread.sleep(1000);
				log.logLine(Testname, false, "Entered Value in text field ");			
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



			if (doesElementExist2(properties.getProperty("ChooseAction"), 5)) {	    
				WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("ChooseAction")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
				Thread.sleep(1000);

				log.logLine(Testname, false, "Clicking on Seacrh criteria Operator drop down list in advance search");

				if (doesElementExist2(properties.getProperty("Seloption"), 5)) {
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Seloption")));
					for (WebElement lnk:selopts) {
						if (!lnk.getText().equals("Email Document") ) {	
							log.logLine(Testname, false, "Choose Actions Options contains only View Document, Consent and Email History options for the file types .xlsx, .doc, .docx, etc other then pdf");

						}else{
							log.logLine(Testname, false, "Choose Actions Options contains other than View Document, Consent and Email History options for the file types .xlsx, .doc, .docx, etc which is of pdf file type");
							break;
						}
					}
				} else {				
					log.logLine(Testname, true, "Choose Actions Options contains other than View Document, Consent and Email History options for the file types other then pdf, which is unexpected");
					driver.switchTo().defaultContent();
					throw new Exception("Choose Actions Options contains other than View Document, Consent and Email History options for the file types other then pdf, which is unexpected");				
				}		
			}else {				
				log.logLine(Testname, true, "Clicking on the choose actions is failed");
				throw new Exception("Clicking on the choose actions is failed");				
			}


		}	

		return true;	
	}


	public boolean FileVerificationDownload_FF(String AccNo, String Testname ) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));		
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		driver.switchTo().frame("iframeContainer");	
		Thread.sleep(1000);

		for (int i=1; i<7; i++){

			//Clicking on Advance search button	    
			if (doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5)) {
				WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
				Thread.sleep(1000);
				log.logLine(Testname, false, "Click on Advance Search is successfull");
			} else {
				log.logLine(Testname, true, "Click on Advance Search is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Click on Advance Search is failed");
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


			if (doesElementExist2(properties.getProperty("Alldts"), 5)) {
				WebElement btn = driver.findElement(By.cssSelector(properties.getProperty("Alldts")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
				log.logLine(Testname, false, "Click on All Date Checkbox is Successfull");
			} else {
				log.logLine(Testname, true, "Click on All Date Checkbox is Failed");
				driver.switchTo().defaultContent();
				throw new Exception("Click on All Date Checkbox is Failed");
			}

			Thread.sleep(3000);
			if (doesElementExist2(properties.getProperty("SearchBy"), 5)) {	    
				WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("SearchBy")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
				Thread.sleep(1000);

				log.logLine(Testname, false, "Clicking on Seacrh criteria Field drop down list in advance search");

				if (doesElementExist2(properties.getProperty("SelFieltOpt"), 5)) {

					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelFieltOpt")));
					for (WebElement lnk:selopts) {
						if (lnk.getText().equals("Consumer ID")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							log.logLine(Testname, false, "Selecting the Consumer ID Field option from the dropdown");
							break;
						}						
					}
				} else {				
					log.logLine(Testname, true, "Selecting the field option Consumer ID is failed");
					driver.switchTo().defaultContent();
					throw new Exception("Selecting the field option Consumer ID is failed");				
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
						if (lnk.getText().equals("Equals")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							log.logLine(Testname, false, "Selecting the Equals operator option from the dropdown");
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


			ext1 = ".xlsx";
			ext2 = ".doc";
			ext3 = ".docx";
			ext4 = ".xls";
			ext5 = ".pdf";
			ext6 = ".csv";


			val5 = "sco"+CreateAppData.consId+"4";
			val6 = "sco"+CreateAppData.consId+"5";
			val7 = "sco"+CreateAppData.consId+"6";		 
			val8 = "sco"+CreateAppData.consId+"7";	
			val9 = "sco"+CreateAppData.consId+"8";
			val10 = "sco"+CreateAppData.consId+"9";	    


			if (doesElementExist2(properties.getProperty("Txtfld"), 5)) {	  
				WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("Txtfld")));
				Txt.clear();
				if (i==1){
					Txt.sendKeys(val5);
				}else if (i==2){
					Txt.sendKeys(val6);
				}else if (i==3){
					Txt.sendKeys(val7);
				}else if (i==4){
					Txt.sendKeys(val8);
				}else if (i==5){
					Txt.sendKeys(val9);
				}else if (i==6){
					Txt.sendKeys(val10);
				}
				Thread.sleep(1000);
				log.logLine(Testname, false, "Entered Value in text field ");			
			}else {
				log.logLine(Testname, true,"Unable to enter the text in text field");
				throw new Exception("Unable to  enter the text in text field");
			}




			if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	 
				WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
				PleasewaitDisappear();
				Thread.sleep(2000);
				PleasewaitDisappear();
				log.logLine(Testname, false, "Clicking on Search button is successfull");
			} else {
				log.logLine(Testname, true, "Clicking on Search button is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on Search button is failed");
			}


			if (doesElementExist2(properties.getProperty("ConsIdCol"), 5)) {	 
				String btnsrch = driver.findElement(By.cssSelector(properties.getProperty("ConsIdCol"))).getText();
				if(i==1){
					if (btnsrch.equals(val5)){
						log.logLine(Testname, false, "Searched consumer Id exist in the results grid hence uploading file is successful");
					}
				} else if(i==2){
					if (btnsrch.equals(val6)){
						log.logLine(Testname, false, "Searched consumer Id exist in the results grid hence uploading file is successful");
					}
				} else if(i==3){
					if (btnsrch.equals(val7)){
						log.logLine(Testname, false, "Searched consumer Id exist in the results grid hence uploading file is successful");
					}
				} else if(i==4){
					if (btnsrch.equals(val8)){
						log.logLine(Testname, false, "Searched consumer Id exist in the results grid hence uploading file is successful");
					}
				}else if(i==5){
					if (btnsrch.equals(val9)){
						log.logLine(Testname, false, "Searched consumer Id exist in the results grid hence uploading file is successful");
					}
				}else if(i==6){
					if (btnsrch.equals(val10)){
						log.logLine(Testname, false, "Searched consumer Id exist in the results grid hence uploading file is successful");
					}
				}			

			}else {
				log.logLine(Testname, true, "Searched consumer Id does not exist in the results grid hence uploading file is unsuccessful");
				throw new Exception("Searched consumer Id does not exist in the results grid hence uploading file is unsuccessful");
			}


			if (doesElementExist2(properties.getProperty("viewDoc"), 5)) {	 
				WebElement vwdoc = driver.findElement(By.cssSelector(properties.getProperty("viewDoc")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", vwdoc);
				Thread.sleep(10000);
				PleasewaitDisappear();
				log.logLine(Testname, false, "Clicking on Search button is successfull");
			} else {
				log.logLine(Testname, true, "Clicking on Search button is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on Search button is failed");
			}

		}

		return true;		
	}


	public boolean TestHarnessSearch(String AccNo, String Testname ) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));		
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();


		plntyp1 = "SKB-ab"+CreateAppData.consId;
		bleky1 = "SKB2015";
		blunum = "skb-"+CreateAppData.consId+"0";		
		order = CreateAppData.order; 

		val1 = "sco"+CreateAppData.consId+"0";
		val2 = "sco"+CreateAppData.consId+"1";
		val3 = "sco"+CreateAppData.consId+"2";


		driver.switchTo().defaultContent();		
		String firstWinHandle = null;

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(properties.getProperty("Archives"))));

		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");
			//throw new Exception("Clicking on Cancel button in Client/App popup is failed");
		}

		if (doesElementExist2(properties.getProperty("AdminMenu"), 5)) {
			List<WebElement> Mns = driver.findElements(By.cssSelector(properties.getProperty("AdminMenu")));
			for (WebElement Each:Mns) {
				if(Each.getText().equals("Admin")){	

					((JavascriptExecutor) driver).executeScript("return $(\"span:contains('Admin')\").mouseover();");
					Thread.sleep(1000);
					((JavascriptExecutor) driver).executeScript("return $(\"span:contains('Archive')\").mouseover();");
					Thread.sleep(1000);
					driver.findElement(By.cssSelector(properties.getProperty("TestHarnesMnu"))).click();

					Thread.sleep(4000);				

					log.logLine(Testname, false, "Clicked on Admin >> Archive >> Test Harness");
					break;
				}
			}

			Thread.sleep(1500);
		}


		Set<String> handles = driver.getWindowHandles();
		firstWinHandle = driver.getWindowHandle(); 
		handles.remove(firstWinHandle);

		boolean browserexist = handles.iterator().hasNext();
		if (browserexist) {

			String winHandle=handles.iterator().next();
			if (winHandle!=firstWinHandle){

				//Switch control to new window
				driver.switchTo().window(winHandle);
				driver.manage().window().maximize();

				if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetExplorer"))) {
					if (doesElementExist2(properties.getProperty("OverrideLnk"), 5)) {
						driver.get("javascript:document.getElementById('overridelink').click();");
						Thread.sleep(8000);
					}
				}	    	
			}
		}

		WebElement retelm = waitForElement(properties.getProperty("ClientID"));
		String ClientID = test.readColumnData("ClientId", sheetname);

		if (doesElementExist2(properties.getProperty("ClientID"), 5)) {
			WebElement Client = driver.findElement(By.cssSelector(properties.getProperty("ClientID")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Client);

			Thread.sleep(1500);
			//WebElement SelClt = driver.findElement(By.xpath(properties.getProperty("selClient"+Iter)));
			//((JavascriptExecutor) driver).executeScript("arguments[0].click()", SelClt);

			if (doesElementExist2(properties.getProperty("ClientIDSel"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ClientIDSel")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().contains(ClientID)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the ClientID as "+ClientID +" from the popup..");						
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Selecting the Client ID failed");
				driver.close();
				driver.switchTo().window(firstWinHandle);
				throw new Exception("Selecting the Client ID failed");
			}					

		} else {
			log.logLine(Testname, true, "Selecting the Client ID is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Selecting the Client ID is failed");
		}


		String uid = test.readColumnData("UserID", sheetname);
		if (doesElementExist2(properties.getProperty("UserID"), 5)) {
			WebElement userid = driver.findElement(By.cssSelector(properties.getProperty("UserID")));
			userid.clear();
			userid.sendKeys(uid);
			log.logLine(Testname, false, "UserID field is filled with "+uid);
		} else {
			log.logLine(Testname, true, "UserID field does not exist in application");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("UserID field does not exist in application");
		}


		String appid = test.readColumnData("ApplicationId", sheetname);	
		if (doesElementExist2(properties.getProperty("DocType"), 5)) {	    
			WebElement Client = driver.findElement(By.cssSelector(properties.getProperty("DocType")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Client);			
			Thread.sleep(1000);

			if (doesElementExist2(properties.getProperty("AppIDSel"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("AppIDSel")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(appid)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Application as "+ClientID +" from the popup..");						
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Selecting the Application ID failed");
				driver.close();
				driver.switchTo().window(firstWinHandle);
				throw new Exception("Selecting the Application ID failed");
			}

		} else {
			log.logLine(Testname, true, "Document type field does not exist in application");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Document type field does not exist in application");
		}


		if (doesElementExist2(properties.getProperty("RequestID"), 5)) {	    
			WebElement reqid = driver.findElement(By.cssSelector(properties.getProperty("RequestID")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", reqid);			
			Thread.sleep(1500);
			WebElement Selreqid = driver.findElement(By.cssSelector(properties.getProperty("SelReqID")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Selreqid);

			log.logLine(Testname, false, "RequestType is filled");
		} else {
			log.logLine(Testname, true, "RequestType field does not exist in application");	
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("RequestType field does not exist in application");
		}

		if (doesElementExist2(properties.getProperty("ConsumerID"), 5)) {	    
			WebElement doctype = driver.findElement(By.cssSelector(properties.getProperty("ConsumerID")));
			doctype.clear();
			log.logLine(Testname, false, "Clearing the Consumer Id is succesful");
		} else {
			log.logLine(Testname, true, "Clearing the Consumer Id is unsuccessful");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("ConsumerID field does not exist in application");
		}


		if (doesElementExist2(properties.getProperty("Submitbtn"), 5)) {	    
			WebElement Client = driver.findElement(By.cssSelector(properties.getProperty("Submitbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Client);			

			Thread.sleep(4000);			
			log.logLine(Testname, false, "Clicked on Submit button");
		} else {
			log.logLine(Testname, true, "Submit button does not exist in application");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Submit button does not exist in application");
		}


		WebElement retelm2 = waitForElement(properties.getProperty("ActeDeliverLink"));
		if (doesElementExist2(properties.getProperty("ActeDeliverLink"), 5)) {	    

			String actlink = driver.findElement(By.cssSelector(properties.getProperty("ActeDeliverLink"))).getText();			
			driver.get(actlink);
			Thread.sleep(30000);

			log.logLine(Testname, false, "Clicked on Link to open actual eDelivery page");
		} else {
			log.logLine(Testname, true, "Click on Link to open actual eDelivery page is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Click on Link to open actual eDelivery page is failed");
		}

		WebElement retelem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(properties.getProperty("WelcomeText"))));

		if(retelem != null) {
			log.logLine(Testname, false, "Navigation to eDelivery home is successful");
		} else {
			log.logLine(Testname, true, "Navigation to eDelivery home is unsuccessful");					
		}


		//MultieDelvrySrch(Testname, "Consumer ID", "Equals", val1);
		MultieDelvrySrch(Testname, "Consumer ID", "Equals", val1, firstWinHandle);


		String[] Sort1 = new String[20];
		String row = "tr";
		List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='archive-search-grid']/div[2]/table/tbody/tr"));

		if(doesElementExist2(properties.getProperty("ConsumerHeader"), 5)){
			for(int i = 0; i < DataCnt.size(); i++) {
				Sort1[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row+" td+td+td")).getText();
				if(Sort1[i].equals(val1)){
					log.logLine(Testname, false, "Searching for "+val1+" exist in the documents grid and verifying of the document is succesful");
				}else{
					log.logLine(Testname, true, "Searching for "+val1+" does not exist in the documents grid and verifying of the document is unsuccesful");
					throw new Exception("Searching for "+val1+" does not exist in the documents grid and verifying of the document is unsuccesful");

				}
				row = row + "+tr";
				log.logLine(Testname, false, "Iterating through the documents and verifying if the document as "+Sort1[i]);
			}

		}



		MultieDelvrySrch(Testname, "Consumer ID", "Starts with (wildcard)", "sco", firstWinHandle);

		String[] Sort2 = new String[20];
		String row1 = "tr";
		List<WebElement> DataCnt1= driver.findElements(By.xpath(".//*[@id='archive-search-grid']/div[2]/table/tbody/tr"));

		if(doesElementExist2(properties.getProperty("ConsumerHeader"), 5)){
			for(int i = 0; i < DataCnt1.size(); i++) {
				Sort2[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row1+" td+td+td")).getText();

				if(Sort2[i].contains("sco")){
					log.logLine(Testname, false, "Searching for \"sco\" exist in the documents grid and verifying of the document is succesful");
				}else{
					log.logLine(Testname, true, "Searching for \"sco\" does not exist in the documents grid and verifying of the document is unsuccesful");
					throw new Exception("Searching for \"sco\" does not exist in the documents grid and verifying of the document is unsuccesful");

				}
				row1 = row1 + "+tr";
				log.logLine(Testname, false, "Iterating through the documents and verifying if the document as "+Sort2[i]);
			}

		}

		String combn = val1 +","+ val2;


		MultieDelvrySrch(Testname, "Consumer ID", "Multiple (comma-separated)", combn, firstWinHandle);


		String[] Sort3 = new String[20];
		String row2 = "tr";
		List<WebElement> DataCnt2= driver.findElements(By.xpath(".//*[@id='archive-search-grid']/div[2]/table/tbody/tr"));

		if(doesElementExist2(properties.getProperty("ConsumerHeader"), 5)){
			for(int i = 0; i < DataCnt2.size(); i++) {
				Sort3[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row2+" td+td+td")).getText();

				if(Sort3[i].equals(val1) || Sort3[i].equals(val2) ){
					log.logLine(Testname, false, "Searching for "+val1+" and "+val2+" exist in the documents grid and verifying of the document is succesful");
				}else{
					log.logLine(Testname, true, "Searching for "+val1+" and "+val2+" does not exist in the documents grid and verifying of the document is unsuccesful");
					throw new Exception("Searching for "+val1+" and "+val2+" does not exist in the documents grid and verifying of the document is unsuccesful");
				}
				row2 = row2 + "+tr";
				log.logLine(Testname, false, "Iterating through the documents and verifying if the document as "+Sort3[i]);
			}

		}

		MultieDelvrySrch(Testname, "Consumer ID", "Equals", val2, firstWinHandle);

		if (doesElementExist(properties.getProperty("eDelViewDoc"), 5)) {	 
			WebElement vwdoc = driver.findElement(By.xpath(properties.getProperty("eDelViewDoc")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", vwdoc);
			Thread.sleep(5000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on view document is successful");
		} else {
			log.logLine(Testname, true, "Clicking on view document is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicking on view document is failed");
		}

		driver.navigate().back();

		/*MultieDelvrySrch(Testname, "Consumer ID", "Equals", val3);

	    		if (doesElementExist2(properties.getProperty("EdelChooseAction"), 5)) {	    
	    			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("EdelChooseAction")));
	    			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
	    			Thread.sleep(1000);

	    			log.logLine(Testname, false, "Clicking on choose action drop down list in advance search");

	    			if (doesElementExist2(properties.getProperty("Selopt"), 5)) {
	    				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Selopt")));
	    				for (WebElement lnk:selopts) {
	    					if (lnk.getText().equals("View Item")) {
	    						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
	    						log.logLine(Testname, false, "Selecting the \"View Item\" option from the dropdown");
	    						break;
	    					}						
	    				}
	    			} else {				
	    				log.logLine(Testname, true, "Selecting the \"View Item\" option is failed");
	    				driver.close();
		    			driver.switchTo().window(firstWinHandle);
	    				throw new Exception("Selecting the \"View Item\" option is failed");				
	    			}		
	    		}else {				
	    			log.logLine(Testname, true, "Clicking on choose action drop down list in advance search");
	    			driver.close();
	    			driver.switchTo().window(firstWinHandle);
	    			throw new Exception("Clicking on choose action drop down list in advance search");				
	    		}


	    		driver.navigate().back();*/

		MultieDelvrySrch(Testname, "Plan Type", "Equals", plntyp1, firstWinHandle);

		String[] Sort4 = new String[20];
		String row3 = "tr";
		List<WebElement> DataCnt3= driver.findElements(By.xpath(".//*[@id='archive-search-grid']/div[2]/table/tbody/tr"));

		if(doesElementExist2(properties.getProperty("PlantypeHeader"), 5)){
			for(int i = 0; i < DataCnt3.size(); i++) {
				Sort4[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row3+" td+td+td+td+td")).getText();

				if(Sort4[i].equals(plntyp1)){
					log.logLine(Testname, false, "Searching for "+plntyp1+" exist in the documents grid and verifying of the document is succesful");
				}else{
					log.logLine(Testname, true, "Searching for "+plntyp1+"  does not exist in the documents grid and verifying of the document is unsuccesful");
					throw new Exception("Searching for "+plntyp1+" does not exist in the documents grid and verifying of the document is unsuccesful");
				}
				row3 = row3 + "+tr";
				log.logLine(Testname, false, "Iterating through the documents and verifying if the document as "+Sort4[i]);
			}

		}

		MultieDelvrySrch(Testname, "Blue Key", "Equals", bleky1, firstWinHandle);


		String[] Sort5 = new String[20];
		String row4 = "tr";
		List<WebElement> DataCnt4= driver.findElements(By.xpath(".//*[@id='archive-search-grid']/div[2]/table/tbody/tr"));

		if(doesElementExist2(properties.getProperty("BlueKeyHeader"), 5)){
			for(int i = 0; i < DataCnt4.size(); i++) {
				Sort5[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row4+" td+td+td+td+td+td")).getText();

				if(Sort5[i].equals(bleky1)){
					log.logLine(Testname, false, "Searching for "+bleky1+" exist in the documents grid and verifying of the document is succesful");
				}else{
					log.logLine(Testname, true, "Searching for "+bleky1+"  does not exist in the documents grid and verifying of the document is unsuccesful");
					throw new Exception("Searching for "+bleky1+" does not exist in the documents grid and verifying of the document is unsuccesful");
				}
				row4 = row4 + "+tr";
				log.logLine(Testname, false, "Iterating through the documents and verifying if the document as "+Sort5[i]);
			}

		}



		MultieDelvrySrch(Testname, "Order Number", "Equals", order, firstWinHandle);

		String[] Sort6 = new String[20];
		String row5 = "tr";
		List<WebElement> DataCnt5= driver.findElements(By.xpath(".//*[@id='archive-search-grid']/div[2]/table/tbody/tr"));

		if(doesElementExist2(properties.getProperty("OrderHeader"), 5)){
			for(int i = 0; i < DataCnt5.size(); i++) {
				Sort6[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row5+" td+td+td+td+td+td+td")).getText();

				if(Sort6[i].equals(order)){
					log.logLine(Testname, false, "Searching for "+order+" exist in the documents grid and verifying of the document is succesful");
				}else{
					log.logLine(Testname, true, "Searching for "+order+"  does not exist in the documents grid and verifying of the document is unsuccesful");
					throw new Exception("Searching for "+order+" does not exist in the documents grid and verifying of the document is unsuccesful");
				}
				row5 = row5 + "+tr";
				log.logLine(Testname, false, "Iterating through the documents and verifying if the document as "+Sort6[i]);
			}

		}

		MultieDelvrySrch(Testname, "Blue Number", "Equals", blunum, firstWinHandle);

		String[] Sort7 = new String[20];
		String row6 = "tr";
		List<WebElement> DataCnt6= driver.findElements(By.xpath(".//*[@id='archive-search-grid']/div[2]/table/tbody/tr"));

		if(doesElementExist2(properties.getProperty("BlueNumHeader"), 5)){
			for(int i = 0; i < DataCnt6.size(); i++) {
				Sort7[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row6+" td+td+td+td+td+td+td+td")).getText();

				if(Sort7[i].equals(blunum)){
					log.logLine(Testname, false, "Searching for "+blunum+" exist in the documents grid and verifying of the document is succesful");
				}else{
					log.logLine(Testname, true, "Searching for "+blunum+"  does not exist in the documents grid and verifying of the document is unsuccesful");
					throw new Exception("Searching for "+blunum+" does not exist in the documents grid and verifying of the document is unsuccesful");
				}
				row6 = row6 + "+tr";
				log.logLine(Testname, false, "Iterating through the documents and verifying if the document as "+Sort7[i]);
			}

		}




		return true;
	}


	public void MultieDelvrySrch(String Testname, String Field, String Operator, String TData, String firstWinHandle) throws Exception {


		if (doesElementExist2(properties.getProperty("DocumentsLnk"), 5)) {	 
			WebElement docmnt = driver.findElement(By.cssSelector(properties.getProperty("DocumentsLnk")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", docmnt);
			Thread.sleep(10000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Docments tab is successfull");
		} else {
			log.logLine(Testname, true, "Clicking on Docments tab is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicking on Docments tab is failed");
		}


		if (doesElementExist2(properties.getProperty("edelAlldts"), 5)) {
			WebElement btn = driver.findElement(By.cssSelector(properties.getProperty("edelAlldts")));

			if (btn.isSelected()){
				log.logLine(Testname, false, "All Date Checkbox is already selected");
			}else{
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
				log.logLine(Testname, false, "Click on All Date Checkbox is Successfull");
			}

		} else {
			log.logLine(Testname, true, "Click on All Date Checkbox is Failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Click on All Date Checkbox is Failed");
		}


		Thread.sleep(3000);
		Actions action=new Actions(driver);
		if (doesElementExist2(properties.getProperty("edelSearchBy"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("edelSearchBy")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(1000);

			log.logLine(Testname, false, "Clicking on Seacrh criteria Field drop down list in advance search of edelivery");

			if (doesElementExist2(properties.getProperty("SelFieltOpt"), 5)) {

				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelFieltOpt")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(Field)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						log.logLine(Testname, false, "Selecting the "+Field+"  Field option from the dropdown");
						break;
					}						
				}
			} else {				
				log.logLine(Testname, true, "Selecting the "+Field+" field option Consumer ID is failed");
				driver.close();
				driver.switchTo().window(firstWinHandle);
				throw new Exception("Selecting the "+Field+" field option Consumer ID is failed");				
			}		

		}else {
			log.logLine(Testname, true, "Clicking on the Field is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicking on the Field is failed");
		}


		if (doesElementExist2(properties.getProperty("edelOperatorbutton"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("edelOperatorbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(1000);

			log.logLine(Testname, false, "Clicking on Seacrh criteria Operator drop down list in advance search of edelivery");

			if (doesElementExist2(properties.getProperty("SelOpertOpt"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelOpertOpt")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(Operator)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						log.logLine(Testname, false, "Selecting the "+Operator+" operator option from the dropdown");
						break;
					}						
				}
			} else {				
				log.logLine(Testname, true, "Selecting the "+Operator+" operator option is failed");
				driver.close();
				driver.switchTo().window(firstWinHandle);
				throw new Exception("Selecting the "+Operator+" operator option is failed");				
			}		
		}else {				
			log.logLine(Testname, true, "Clicking on the operator is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicking on the operator is failed");				
		}

		if (doesElementExist2(properties.getProperty("eDelTxtfld"), 5)) {	  
			WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("eDelTxtfld")));
			Txt.clear();
			Txt.sendKeys(TData);
			log.logLine(Testname, false,"Entering the "+TData+" in text field");
		}
		else{
			log.logLine(Testname, true,"Unable to enter the "+TData+" in text field");
			throw new Exception("Unable to  enter the "+TData+" in text field"); 
		}

		if (doesElementExist(".//*[@id='btnSearch']", 5)){
			WebElement btnsrch = driver.findElement(By.xpath(".//*[@id='btnSearch']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button is successfull");
		}else {
			log.logLine(Testname, true, "Clicking on Search button is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicking on Search button is failed");
		}


	}



}



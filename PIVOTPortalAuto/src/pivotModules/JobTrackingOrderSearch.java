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
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class JobTrackingOrderSearch extends Page{

	public static String row = "tr";
	public static String actval;
	public static String Textsrch ="815011006403";
	public static String ClientIDText1 ="D20161105,D110416";
	public static String ClientIDText2 ="D201";
	public static String RRDCopyText="02";
	public static String RRDCopyRefText="8150";
	public static String DocidText="0000999";
	public static String DocidText1="0000001,0000002";
	public static String ConsidText="12161-025,12390-555";
	public static String AccNumText="12195";
	public static String MailflagP="P";
	public static String MailflagC="C";
	public static String Lendernum="5052";
	public static String refernctxt;
	public static String refstart="8150";
	public static String Testdata="8150-1071-7403,8150-1058-7403,8150-1053-7403,8150-1050-7403,8150-1048-7403";

	public static String RefTextsrch="0123";
	public static String RRDCopyBatchText="H9D00101";
	public static String RRDCopynumText="14";

	public JobTrackingOrderSearch(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	} 
	@Override
	protected void load() {}
	@Override

	protected void isLoaded() throws Error {}

	public boolean ClientAppSel(String AccNo, String Testname) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();


		Thread.sleep(1000);
		driver.switchTo().defaultContent();

		Thread.sleep(2000);
		WebElement retelm2 = waitForElement(properties.getProperty("selClint1"));

		if (doesElementExist2(properties.getProperty("JobTracking"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("JobTracking")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);	    	 
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Click on JobTracking Module is Successful");
		} else {
			log.logLine(Testname, true, "Click on JobTracking Module is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on JobTracking Module is failed");
		}

		Thread.sleep(5000);
		PleasewaitDisappear();


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


		//Click on Ok button
		if (doesElementExist2(properties.getProperty("Okbtn"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Okbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on OK button");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the Reports is failed");
			throw new Exception("Clicking on OK button to view the Reports is failed");
		}

		return true;
	}	




	public boolean OrderSearch(String AccNo,String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		Date date = new Date();
		String todaysDate = dateFormat.format(date);
		String Prevdte=test.readColumnData("PrevoiusDate", sheetname);
		String Text=test.readColumnData("TextName", sheetname);
		String stockId=test.readColumnData("StockId", sheetname);



		Thread.sleep(8000);
		driver.switchTo().frame("iframeContainer");
		
		if (doesElementExist2(properties.getProperty("Recntactybtn"), 5)) {	    
			WebElement rectact = driver.findElement(By.cssSelector(properties.getProperty("Recntactybtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rectact);
			PleasewaitDisappear();
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicked on recent Activity button in JobTracking page");	

		} else {
			log.logLine(Testname, true, "Clicked on recent Activity button in JobTracking page is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicked on recent Activity button in JobTracking page is failed");
		}

		VerifyHyperlink(Testname);

		Thread.sleep(8000);
		ClientAppSel(AccNo, Testname);

		driver.switchTo().frame("iframeContainer");

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button");
		} else {
			log.logLine(Testname, true, "Clicking on Search is failed");
			throw new Exception("Clicking on Search is failed");
		}


		if (doesElementExist2(properties.getProperty("SrchType"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("SrchType")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
			Thread.sleep(8000);
			log.logLine(Testname, false, "Clicking on SearchType dropdown..");

			if (doesElementExist2(properties.getProperty("TypeAdvance"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("TypeAdvance")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Advanced")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(8000);
						log.logLine(Testname, false, "Selecting the Search Type as Advanced from the dropdown..");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Search Type options are not displayed");
				throw new Exception("Search Type options are not displayed");
			}
		}

		if (doesElementExist2(properties.getProperty("Advanceoptions"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Advanceoptions")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
			Thread.sleep(8000);
			log.logLine(Testname, false, "Clicking on Advance options dropdown..");

			if (doesElementExist2(properties.getProperty("Advnceoptnslst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Advnceoptnslst")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Ref number")) {
						Thread.sleep(5000);
						log.logLine(Testname, false, "**** Reading the Option as----" +lnk.getText()+" Under Advance Search Option Dropdown**** ");							
						break;
					}				
				}

			} 

			if (doesElementExist2(properties.getProperty("Advnceoptnslst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Advnceoptnslst")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Component Stock Type")) {
						Thread.sleep(1000);
						log.logLine(Testname, false, "**** Reading the Option as----" +lnk.getText()+" Under Advance Search Option Dropdown ****");							
						break;
					}				
				}

			} 

			if (doesElementExist2(properties.getProperty("Advnceoptnslst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Advnceoptnslst")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Component Stock Id")) {
						Thread.sleep(1000);
						log.logLine(Testname, false, "**** Reading the Option as----" +lnk.getText()+" Under Advance Search Option Dropdown ****");							
						break;
					}				
				}
			}

		}else {
			log.logLine(Testname, true, "Advance options are not displayed");
			throw new Exception("Advance options are not displayed");
		}


		// Setting the Status as Processed
		if (doesElementExist2(properties.getProperty("Status"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Status")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
			Thread.sleep(8000);
			log.logLine(Testname, false, "Clicking on Status dropdown..");

			if (doesElementExist2(properties.getProperty("StatusType"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("StatusType")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Processed")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(4000);
						log.logLine(Testname, false, "Selecting the Status as **Processed** from the Staus dropdown " );							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Status Type options are not displayed");
				throw new Exception("Status Type options are not displayed");
			}
		}


		if (doesElementExist2(properties.getProperty("FromDate"), 5)) {	  
			WebElement DteFromfld = driver.findElement(By.cssSelector(properties.getProperty("FromDate")));
			DteFromfld.clear();
			Thread.sleep(1000);
			DteFromfld.sendKeys(Prevdte);
			log.logLine(Testname, false, "Entering the from date value as "+Prevdte+"");

			WebElement DteTofld = driver.findElement(By.cssSelector(properties.getProperty("ToDate")));
			DteTofld.clear();
			Thread.sleep(1000);
			DteTofld.sendKeys(todaysDate);
			log.logLine(Testname, false, "Entering the To date value as "+todaysDate+"");
		}	  

		if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Serach button");
		} else {
			log.logLine(Testname, true, "Clicking on Serach button is failed");
			throw new Exception("Clicking on Serach button is failed");
		}


		String[] Sort1 = new String[20];
		row = "tr";
		List<WebElement> DataCnt1= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));

		if(doesElementExist2(properties.getProperty("PrintStatus"), 5)){
			for(int i = 0; i < DataCnt1.size(); i++) {
				Sort1[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row+" td+td+td+td[role='gridcell']")).getText();
				if(Sort1[i].equalsIgnoreCase("Processed")){
					log.logLine(Testname, false, " **** Print Status matches with "+Sort1[i]+" **** ");
				}else{
					log.logLine(Testname, false, "Print Status does not matches with "+Sort1[i]);
					break;
				}
				row = row + "+tr";
			}
		}


		// Status As Imaged

		if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button");
		} else {
			log.logLine(Testname, true, "Clicking on Search is failed");
			throw new Exception("Clicking on Search is failed");
		}


		if (doesElementExist2(properties.getProperty("Status"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Status")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Status dropdown..");

			if (doesElementExist2(properties.getProperty("StatusType"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("StatusType")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Imaged")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Status as **Imaged** from the Staus dropdown ");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Status Type options are not displayed");
				throw new Exception("Status Type options are not displayed");
			}
		}


		if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Serach button");
		} else {
			log.logLine(Testname, true, "Clicking on Serach button is failed");
			throw new Exception("Clicking on Serach button is failed");
		}


		String[] Sort2 = new String[20];
		row = "tr";
		List<WebElement>DataCnt2= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));

		if(doesElementExist2(properties.getProperty("PrintStatus"), 5)){
			for(int i = 0; i < DataCnt2.size(); i++) {
				Sort2[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row+" td+td+td+td[role='gridcell']")).getText();
				if(Sort2[i].equalsIgnoreCase("Imaged")){
					log.logLine(Testname, false, "**** Print Status matches with "+Sort2[i]+" **** ");
				}else{
					log.logLine(Testname, true, "Print Status does not matches with "+Sort2[i]);
					break;
				}
				row = row + "+tr";
			}
		}

		if(doesElementExist2("div[data-role='pager'] span[class='k-pager-info k-label']", 5)){
			String nodoc =driver.findElement(By.cssSelector(properties.getProperty("NoDocMesg"))).getText();
			if(nodoc.equals("No items to display")){
				log.logLine(Testname, false, "There are no documents matching with the search criteria");
			}

		} 			

		// Status As Inserted

		if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button");
		} else {
			log.logLine(Testname, true, "Clicking on Search is failed");
			throw new Exception("Clicking on Search is failed");
		}


		if (doesElementExist2(properties.getProperty("Status"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Status")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Status dropdown..");

			if (doesElementExist2(properties.getProperty("StatusType"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("StatusType")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Inserted")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Status as **Inserted** from the Staus dropdown");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Status Type options are not displayed");
				throw new Exception("Status Type options are not displayed");
			}
		}


		if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Serach button");
		} else {
			log.logLine(Testname, true, "Clicking on Serach button is failed");
			throw new Exception("Clicking on Serach button is failed");
		}


		String[] Sort3 = new String[20];
		row = "tr";
		List<WebElement>DataCnt3= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));

		if(doesElementExist2(properties.getProperty("PrintStatus"), 5)){
			for(int i = 0; i < DataCnt3.size(); i++) {
				Sort3[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row+" td+td+td+td[role='gridcell']")).getText();
				if(Sort3[i].equalsIgnoreCase("Inserted")){
					log.logLine(Testname, false, "**** Print Status matches with "+Sort3[i]+" ****");
				}else{
					log.logLine(Testname, true, "Print Status does not matches with "+Sort3[i]);
					break;
				}
				row = row + "+tr";
			}
		}


		if(doesElementExist2("div[data-role='pager'] span[class='k-pager-info k-label']", 5)){
			String nodoc =driver.findElement(By.cssSelector(properties.getProperty("NoDocMesg"))).getText();
			if(nodoc.equals("No items to display")){
				log.logLine(Testname, false, "There are no documents matching with the search criteria");
			}

		}

		// Status As Shipped

		if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button");
		} else {
			log.logLine(Testname, true, "Clicking on Search is failed");
			throw new Exception("Clicking on Search is failed");
		}


		if (doesElementExist2(properties.getProperty("Status"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Status")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Status dropdown..");

			if (doesElementExist2(properties.getProperty("StatusType"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("StatusType")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Shipped")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Status as **Shipped** from the Staus dropdown");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Status Type options are not displayed");
				throw new Exception("Status Type options are not displayed");
			}
		}


		if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Serach button");
		} else {
			log.logLine(Testname, true, "Clicking on Serach button is failed");
			throw new Exception("Clicking on Serach button is failed");
		}


		String[] Sort4 = new String[20];
		row = "tr";
		List<WebElement> DataCnt4= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));

		if(doesElementExist2(properties.getProperty("PrintStatus"), 5)){
			for(int i = 0; i < DataCnt4.size(); i++) {
				Sort4[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row+" td+td+td+td[role='gridcell']")).getText();
				if(Sort4[i].equalsIgnoreCase("Shipped")){
					log.logLine(Testname, false, "**** Print Status matches with "+Sort4[i]+" ****");
				}else{
					log.logLine(Testname, true, "Print Status does not matches with "+Sort4[i]);
					break;
				}
				row = row + "+tr";
			}
		}


		//Selecting the Date Type as Data Processed Date

		if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button");
		} else {
			log.logLine(Testname, true, "Clicking on Search is failed");
			throw new Exception("Clicking on Search is failed");
		}


		if (doesElementExist2(properties.getProperty("Status"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Status")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Status dropdown..");

			if (doesElementExist2(properties.getProperty("StatusType"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("StatusType")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("All")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Status as **All** from the Staus dropdown");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Status Type options are not displayed");
				throw new Exception("Status Type options are not displayed");
			}
		}


		if (doesElementExist2(properties.getProperty("DateType"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("DateType")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Date Type dropdown..");

			if (doesElementExist2(properties.getProperty("Dtetyplst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Dtetyplst")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Data Processed by RRD Facility Date")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Date Type Name as Data Processed by RRD Facility Date from the dropdown..");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Date Type options are not displayed");
				throw new Exception("Date Type options are not displayed");
			}
		}


		if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Serach button");
		} else {
			log.logLine(Testname, true, "Clicking on Serach button is failed");
			throw new Exception("Clicking on Serach button is failed");
		}

		String[] Sort5 = new String[20];
		row = "tr";
		List<WebElement> DataCnt5= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));

		if(doesElementExist2(properties.getProperty("Dataprocessdddte"), 5)){
			for(int i = 0; i < DataCnt5.size(); i++) {
				Sort5[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row+" td+td[role='gridcell']")).getText();
				row = row + "+tr";
			}
			log.logLine(Testname, false, "**** Iterating through the Rows....Data Processed by RRD Facility Dates Fall under the date range selected **** ");
		}


		//Selecting the Date Type as Expected Mail Date

		if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button");
		} else {
			log.logLine(Testname, true, "Clicking on Search is failed");
			throw new Exception("Clicking on Search is failed");
		}




		if (doesElementExist2(properties.getProperty("DateType"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("DateType")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Date Type dropdown..");

			if (doesElementExist2(properties.getProperty("StatusType"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Dtetyplst")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Expected Mail Date")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Date Type Name as "+lnk.getText()+" from the dropdown..");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Date Type options are not displayed");
				throw new Exception("Date Type options are not displayed");
			}
		}


		if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Serach button");
		} else {
			log.logLine(Testname, true, "Clicking on Serach button is failed");
			throw new Exception("Clicking on Serach button is failed");
		}

		String[] Sort6 = new String[20];
		row = "tr";
		List<WebElement> DataCnt6= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));

		if(doesElementExist2(properties.getProperty("ExpectedMaildte"), 5)){
			for(int i = 0; i < DataCnt6.size(); i++) {
				Sort6[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row+" td+td+td+td+td[role='gridcell']")).getText();
				row = row + "+tr";
			}
			log.logLine(Testname, false, "**** Iterating through the Rows....Expected Mail Dates Fall under the date range selected**** ");
		}



		//Selecting the Date Type as Actual Mail Date

		if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button");
		} else {
			log.logLine(Testname, true, "Clicking on Search is failed");
			throw new Exception("Clicking on Search is failed");
		}




		if (doesElementExist2(properties.getProperty("DateType"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("DateType")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Date Type dropdown..");

			if (doesElementExist2(properties.getProperty("StatusType"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Dtetyplst")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Actual Mail Date")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Date Type Name as "+lnk.getText()+" from the dropdown..");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Date Type options are not displayed");
				throw new Exception("Date Type options are not displayed");
			}
		}


		if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Serach button");
		} else {
			log.logLine(Testname, true, "Clicking on Serach button is failed");
			throw new Exception("Clicking on Serach button is failed");
		}

		String[] Sort7 = new String[20];
		row = "tr";
		List<WebElement> DataCnt7= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));

		if(doesElementExist2(properties.getProperty("ActualMaildte"), 5)){
			for(int i = 0; i < DataCnt7.size(); i++) {
				Sort7[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row+" td+td+td+td+td+td+td+td+td+td+td+td[role='gridcell']")).getText();
				row = row + "+tr";
			}
			log.logLine(Testname, false, " **** Iterating through the Rows....Actual Mail Dates Fall under the date range selected**** ");
		}


		// Basic Search-----Selecting Basic search type as LOGAN

		if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button");
		} else {
			log.logLine(Testname, true, "Clicking on Search is failed");
			throw new Exception("Clicking on Search is failed");
		}


		if (doesElementExist2(properties.getProperty("Basicsrchvalue"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Basicsrchvalue")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on RRD Plant Type dropdown..");

			if (doesElementExist2(properties.getProperty("Basicsrchvalueoption"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Basicsrchvalueoption")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("RRD Plant")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the criteria as "+lnk.getText()+" option from Basic Search criteria dropdown");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "RRD Plant option are not displayed");
				throw new Exception("RRD Plant option are not displayed");
			}
		}



		if (doesElementExist2(properties.getProperty("Basicsrchtype"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Basicsrchtype")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Basic Search Type dropdown..");

			if (doesElementExist2(properties.getProperty("Basicsrchtypeoption"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Basicsrchtypeoption")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Logan")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Basic Search option from the dropdown as "+lnk.getText()+"");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Basic Search options are not displayed");
				throw new Exception("Basic Search options are not displayed");
			}
		}


		if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Serach button");
		} else {
			log.logLine(Testname, true, "Clicking on Serach button is failed");
			throw new Exception("Clicking on Serach button is failed");
		}

		String[] Sort8 = new String[20];
		row = "tr";
		List<WebElement> DataCnt8= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));

		if(doesElementExist2(properties.getProperty("RRDPlant"), 5)){
			for(int i = 0; i < DataCnt8.size(); i++) {
				Sort8[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row+" td+td+td+td+td+td+td+td[role='gridcell']")).getText();
				if(Sort8[i].equalsIgnoreCase("Logan")){
					log.logLine(Testname, false, "**** RRD Plant Status matches with "+Sort8[i]+" ****");
				}else{
					log.logLine(Testname, true, "RRD Plant Status does not matches with "+Sort8[i]);
					break;
				}
				row = row + "+tr";
			}
		}


		// Selecting RRD Order Number


		if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button");
		} else {
			log.logLine(Testname, true, "Clicking on Search is failed");
			throw new Exception("Clicking on Search is failed");
		}


		if (doesElementExist2(properties.getProperty("Basicsrchtype"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Basicsrchtype")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Basic Search Type dropdown..");

			if (doesElementExist2(properties.getProperty("Basicsrchtypeoption"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Basicsrchtypeoption")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("All")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Basic Search option from the dropdown as "+lnk.getText());							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Basic Search options are not displayed");
				throw new Exception("Basic Search options are not displayed");
			}
		}



		if (doesElementExist2(properties.getProperty("Advanceoptions"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Advanceoptions")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Advance Search Options Search Type dropdown..");


			if (doesElementExist2(properties.getProperty("Advnceoptnslst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Advnceoptnslst")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Ref number")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(3000);
						log.logLine(Testname, false, "Selecting the Option "+lnk.getText()+" form advanced option dropdown");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Advance Search options are not displayed");
				throw new Exception("Advance Search options are not displayed");
			}
		}


		if (doesElementExist2(properties.getProperty("TextField"), 5)) {
			WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("TextField")));
			Txt.clear();
			Txt.sendKeys(Text);
			log.logLine(Testname, false, "Entering the Ref number as **"+Text+"** on Text Field");
		} else {
			log.logLine(Testname, true, "Entering the RRD Order Number is Failed");
			throw new Exception("Entering the RRD Order Number is Failed");
		}



		if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Serach button");
		} else {
			log.logLine(Testname, true, "Clicking on Serach button is failed");
			throw new Exception("Clicking on Serach button is failed");
		}

		String[] Sort9 = new String[20];
		row = "tr";
		List<WebElement> DataCnt9= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));

		if(doesElementExist2(properties.getProperty("RRDNumber"), 5)){
			for(int i = 0; i < DataCnt9.size(); i++) {
				Sort9[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row+" td+td+td+td+td+td+td[role='gridcell']")).getText();
				if(Sort9[i].equals(Text)){
					log.logLine(Testname, false, "**** Ref number "+Text+"  matches with "+Sort9[i]+" ****" );
				}else{
					log.logLine(Testname, true, "Ref number does not matches with "+Sort9[i]);
					break;
				}
				row = row + "+tr";
			}
		}

		if(doesElementExist2("div[data-role='pager'] span[class='k-pager-info k-label']", 5)){
			String nodoc =driver.findElement(By.cssSelector(properties.getProperty("NoDocMesg"))).getText();
			if(nodoc.equals("No items to display")){
				log.logLine(Testname, false, "There are no documents matching with the search criteria");
			}

		}	


		//Component stock id and type

		if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button");
		} else {
			log.logLine(Testname, true, "Clicking on Search is failed");
			throw new Exception("Clicking on Search is failed");
		}


		if (doesElementExist2(properties.getProperty("SrchType"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("SrchType")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on SearchType dropdown..");

			if (doesElementExist2(properties.getProperty("TypeAdvance"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("TypeAdvance")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Advanced")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(5000);
						log.logLine(Testname, false, "Selecting the Search Type as Advanced from the dropdown..");					
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Search Type options are not displayed");
				throw new Exception("Search Type options are not displayed");
			}
		}


		if (doesElementExist2(properties.getProperty("DateType"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("DateType")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Date Type dropdown..");

			if (doesElementExist2(properties.getProperty("Dtetyplst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Dtetyplst")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Data Processed by RRD Facility Date")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(3000);
						log.logLine(Testname, false, "Selecting the Date Type Name as "+lnk.getText()+" from the dropdown..");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Date Type options are not displayed");
				throw new Exception("Date Type options are not displayed");
			}
		}



		if (doesElementExist2(properties.getProperty("Advanceoptions"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Advanceoptions")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Advance Search Options Search Type dropdown..");


			if (doesElementExist2(properties.getProperty("Advnceoptnslst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Advnceoptnslst")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Ref number")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(3000);
						log.logLine(Testname, false, "Selecting the Option as Ref number form advanced option dropdown");						
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Advance Search options are not displayed");
				throw new Exception("Advance Search options are not displayed");
			}
		}

		if (doesElementExist2(properties.getProperty("TextField"), 5)) {
			WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("TextField")));
			Txt.clear();
			//Txt.sendKeys(Text);
			log.logLine(Testname, false, "Entering the RRD Order Number  on Text Field");
		} else {
			log.logLine(Testname, true, "Entering the RRD Order Number is Failed");
			throw new Exception("Entering the RRD Order Number is Failed");
		}

		if (doesElementExist2(properties.getProperty("PlusBtn"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("PlusBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on add/plus button");
		} else {
			log.logLine(Testname, true, "Clicking on add/plus is failed");
			throw new Exception("Clicking on add/plus is failed");
		}


		if (doesElementExist(properties.getProperty("Advanceoptions2"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.xpath(properties.getProperty("Advanceoptions2")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Advance Search Options Search Type dropdown..");


			if (doesElementExist2(properties.getProperty("Advnceoptnslst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Advnceoptnslst")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Component Stock Type")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(3000);
						log.logLine(Testname, false, "Selecting the Option as Component Stock Type form advanced option dropdown");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Advance Search options are not displayed");
				throw new Exception("Advance Search options are not displayed");
			}
		}

		if (doesElementExist2(properties.getProperty("PlusBtn"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("PlusBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on add/plus button");
		} else {
			log.logLine(Testname, true, "Clicking on add/plus is failed");
			throw new Exception("Clicking on add/plus is failed");
		}


		if (doesElementExist(properties.getProperty("Advanceoptions3"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.xpath(properties.getProperty("Advanceoptions3")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Advance Search Options Search Type dropdown..");


			if (doesElementExist2(properties.getProperty("Advnceoptnslst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Advnceoptnslst")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Component Stock Id")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(3000);
						log.logLine(Testname, false, "Selecting the Option as Component Stock Id form advanced option dropdown");						
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Advance Search options are not displayed");
				throw new Exception("Advance Search options are not displayed");
			}
		}

		Thread.sleep(1000);
		if (doesElementExist(properties.getProperty("CompIdTextBox"), 5)) {
			WebElement idtex = driver.findElement(By.xpath(properties.getProperty("CompIdTextBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", idtex);
			idtex.clear();
			idtex.sendKeys(stockId);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entering the stock id as "+stockId+" in stock Id text box");
		} else {
			log.logLine(Testname, true, "Clicking on add/plus button is failed");
			throw new Exception("Clicking on add/plus is failed");
		}


		// Setting the Status as All
		if (doesElementExist2(properties.getProperty("Status"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Status")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Status dropdown..");

			if (doesElementExist2(properties.getProperty("StatusType"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("StatusType")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("All")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(3000);
						log.logLine(Testname, false, "Selecting the Status as "+lnk.getText()+"from the Staus dropdown");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Status Type options are not displayed");
				throw new Exception("Status Type options are not displayed");
			}
		}


		if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Serach button");
		} else {
			log.logLine(Testname, true, "Clicking on Serach button is failed");
			throw new Exception("Clicking on Serach button is failed");
		}



		String[] CmpStockId = new String[20];
		row = "tr";
		List<WebElement> DataCnt10= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));

		if(doesElementExist2(properties.getProperty("CompoStockId"), 5)){
			for(int i = 0; i < DataCnt10.size(); i++) {
				CmpStockId[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row+" td[role='gridcell']")).getText();
				if(CmpStockId[i].equals(stockId) || CmpStockId[i].equals(" ")){
					log.logLine(Testname, false, "**** Record displayed with stock id "+stockId+" which is as expected ****");

				}else{
					log.logLine(Testname, true, "Record does not match with stock id "+stockId+" and which is unexpected ");
					break;
				}
				row = row + "+tr";
			}


		}
		return true;
	}



	public boolean MoreAdvancedSearch(String AccNo,String Testname) throws Exception {


		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	

		ClientAppSel(AccNo, Testname);

		driver.switchTo().frame("iframeContainer");
		
		if (doesElementExist2(properties.getProperty("Recntactybtn"), 5)) {	    
			WebElement rectact = driver.findElement(By.cssSelector(properties.getProperty("Recntactybtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rectact);
			PleasewaitDisappear();
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicked on recent Activity button in JobTracking page");	

		} else {
			log.logLine(Testname, true, "Clicked on recent Activity button in JobTracking page is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicked on recent Activity button in JobTracking page is failed");
		}


		MultiAdvancedSrch(Testname, "Ref number", "> (greater than)",Textsrch,"Order");
		SearchResultsValidation(Testname);

		MultiAdvancedSrch(Testname, "Ref number", "< (less than)",Textsrch,"Order");
		SearchResultsValidation(Testname);

		MultiAdvancedSrch(Testname, "Client Job #", "Multiple (comma-separated)",ClientIDText1,"Order");
		SearchResultsValidation1(Testname);

		MultiAdvancedSrch(Testname, "Client Job #", "Starts with (wildcard)",ClientIDText2,"Order");
		SearchResultsValidation2(Testname);


		MultiAdvancedSrch(Testname, "RRD Copy #", "> (greater than)",RRDCopyText,"Copy/Batch");
		SearchResultsValidation3(Testname);

		MultiAdvancedSrch(Testname, "RRD Copy #", "< (less than)",RRDCopyText,"Copy/Batch");
		SearchResultsValidation3(Testname);

		MultiAdvancedSrch(Testname, "Ref number", "Starts with (wildcard)",RRDCopyRefText,"Copy/Batch");
		SearchResultsValidation4(Testname);

		MultiAdvancedSrch(Testname, "Document #", "> (greater than)",DocidText,"Envelope/Package");
		SearchResultsValidation5(Testname);

		MultiAdvancedSrch(Testname, "Document #", "< (less than)",DocidText,"Envelope/Package");
		SearchResultsValidation5(Testname);

		MultiAdvancedSrch(Testname, "Consumer Id", "Multiple (comma-separated)",ConsidText,"Envelope/Package");
		SearchResultsValidation6(Testname);

		MultiAdvancedSrch(Testname, "Account #", "Starts with (wildcard)",AccNumText,"Envelope/Package");
		SearchResultsValidation7(Testname);


		Thread.sleep(5000);
		ClientAppSel1(AccNo, Testname);
		driver.switchTo().frame("iframeContainer");
		
		
		if (doesElementExist2(properties.getProperty("Recntactybtn"), 5)) {	    
			WebElement rectact = driver.findElement(By.cssSelector(properties.getProperty("Recntactybtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rectact);
			PleasewaitDisappear();
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicked on recent Activity button in JobTracking page");	

		} else {
			log.logLine(Testname, true, "Clicked on recent Activity button in JobTracking page is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicked on recent Activity button in JobTracking page is failed");
		}


		MultiAdvancedSrch(Testname, "Mail Flag", "Equals",MailflagP,"Envelope/Package");
		SearchResultsValidation8(Testname);

		MultiAdvancedSrch(Testname, "Mail Flag", "Equals",MailflagC,"Envelope/Package");
		SearchResultsValidation8(Testname);

		MultiAdvancedSrch(Testname, "Lender#", "Equals",Lendernum,"Envelope/Package");
		SearchResultsValidation9(Testname);

		MultiAdvancedSrch1(Testname, "Ref number", "> (greater than)",RefTextsrch,"Order");
		SearchResultsValidation10(Testname);
		Datevalidation(Testname);
		
		MultiAdvancedSrch1(Testname, "RRD Batch #", "Equals",RRDCopyBatchText,"Copy/Batch");
		SearchResultsValidation11(Testname);
		Datevalidation1(Testname);
		
		MultiAdvancedSrch1(Testname, "RRD Copy #", "Equals",RRDCopynumText,"Copy/Batch");
		SearchResultsValidation12(Testname);
		Datevalidation1(Testname);
		
		return true;
	}


	public void MultiAdvancedSrch(String Testname, String Field, String Operator,String Text,String Level) throws Exception {

		String Prevdte="1/1/2013"; 
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		Date date = new Date();
		String todaysDate = dateFormat.format(date);

		//String arr[]=Text.split("11006403");
		//actval = arr[0].trim();

		Thread.sleep(8000);
		if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(5000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button");
		} else {
			log.logLine(Testname, true, "Clicking on Search is failed");
			throw new Exception("Clicking on Search is failed");
		}



		if (doesElementExist2(properties.getProperty("SrchType"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("SrchType")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicking on SearchType dropdown..");

			if (doesElementExist2(properties.getProperty("TypeAdvance"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("TypeAdvance")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Advanced")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(4000);
						log.logLine(Testname, false, "Selecting the Search Type as Advanced from the dropdown..");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Search Type options are not displayed");
				throw new Exception("Search Type options are not displayed");
			}
		}



		if (doesElementExist2(properties.getProperty("FromDate"), 5)) {	  
			WebElement DteFromfld = driver.findElement(By.cssSelector(properties.getProperty("FromDate")));
			DteFromfld.clear();
			Thread.sleep(1000);
			DteFromfld.sendKeys(Prevdte);
			log.logLine(Testname, false, "Entering the from date value as "+Prevdte+"");

			WebElement DteTofld = driver.findElement(By.cssSelector(properties.getProperty("ToDate")));
			DteTofld.clear();
			Thread.sleep(1000);
			DteTofld.sendKeys(todaysDate);
			log.logLine(Testname, false, "Entering the To date value as "+todaysDate+"");
		}	  


		if (doesElementExist2(properties.getProperty("DateType"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("DateType")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Date Type dropdown..");

			if (doesElementExist2(properties.getProperty("Dtetyplst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Dtetyplst")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Data Processed by RRD Facility Date")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(3000);
						log.logLine(Testname, false, "Selecting the Date Type Name as Data Processed by RRD Facility Date from the dropdown..");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Date Type options are not displayed");
				throw new Exception("Date Type options are not displayed");
			}
		}


		if (doesElementExist2(properties.getProperty("Basicsrchvalue"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Basicsrchvalue")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on RRD Plant Type dropdown..");

			if (doesElementExist2(properties.getProperty("Basicsrchvalueoption"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Basicsrchvalueoption")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("RRD Plant")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the criteria as RRD Plant option from Basic Search criteria dropdown");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "RRD Plant option are not displayed");
				throw new Exception("RRD Plant option are not displayed");
			}
		}



		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("ClckLevel"), 5)) {	    
			WebElement srchbtn = driver.findElement(By.cssSelector(properties.getProperty("ClckLevel")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked on Search Level dropdown in Search dialog");


			if (doesElementExist2(properties.getProperty("SelLevel"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelLevel")));
				for (WebElement lnk:selopts) {

					if (lnk.getText().equals(Level)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(5000);
						log.logLine(Testname, false, "Selecting the Level as "+Level+" from the dropdown");							
						break;
					}				
				}
			}


		} else {
			log.logLine(Testname, false, "Clicking on SearchLevel dropdown in Search dialog is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on SearchType dropdown in Search dialog is failed");
		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Advanceoptions"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Advanceoptions")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Advance Search Options Search Type dropdown..");


			if (doesElementExist2(properties.getProperty("Advnceoptnslst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Advnceoptnslst")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(Field)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(3000);
						log.logLine(Testname, false, "Selecting the Option "+Field+" form advanced option dropdown");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Advance Search options are not displayed");
				throw new Exception("Advance Search options are not displayed");
			}

		} else if (doesElementExist2(properties.getProperty("Advoptns"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Advoptns")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Advance Search Options Search Type dropdown..");


			if (doesElementExist2(properties.getProperty("Advnceoptnslst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Advnceoptnslst")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(Field)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(3000);
						log.logLine(Testname, false, "Selecting the Option "+Field+" form advanced option dropdown");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Advance Search options are not displayed");
				throw new Exception("Advance Search options are not displayed");
			}
		}


		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Searchoption"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Searchoption")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Advance Search Options Search Type dropdown..");


			if (doesElementExist2(properties.getProperty("Searchoptionlst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Searchoptionlst")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(Operator)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(3000);
						log.logLine(Testname, false, "Selecting the Operator Option "+Operator+" form advanced option dropdown");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Advance Search options are not displayed");
				throw new Exception("Advance Search options are not displayed");
			}

		} else if (doesElementExist2(properties.getProperty("Srchoptn"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Srchoptn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Advance Search Options Search Type dropdown..");


			if (doesElementExist2(properties.getProperty("Searchoptionlst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Searchoptionlst")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(Operator)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(3000);
						log.logLine(Testname, false, "Selecting the Operator Option "+Operator+" form advanced option dropdown");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Advance Search options are not displayed");
				throw new Exception("Advance Search options are not displayed");
			}
		}


		if (doesElementExist2(properties.getProperty("TextField"), 5)) {
			WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("TextField")));
			Txt.clear();
			Txt.sendKeys(Text);
			log.logLine(Testname, false, "Entering the "+Field+" as "+Text+" on Text Field");
		} else {
			log.logLine(Testname, true, "Entering the "+Field+" is Failed");
			throw new Exception("Entering the "+Field+" is Failed");
		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(10000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Serach button");
		} else {
			log.logLine(Testname, true, "Clicking on Serach button is failed");
			throw new Exception("Clicking on Serach button is failed");
		}

	}
	
	public void MultiAdvancedSrch1(String Testname, String Field, String Operator,String Text,String Level) throws Exception {

		String Fromdate="4/26/2017"; 
		String Todate="5/26/2017"; 
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		Date date = new Date();
		String todaysDate = dateFormat.format(date);

		//String arr[]=Text.split("11006403");
		//actval = arr[0].trim();

		Thread.sleep(8000);
		if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(5000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button");
		} else {
			log.logLine(Testname, true, "Clicking on Search is failed");
			throw new Exception("Clicking on Search is failed");
		}



		if (doesElementExist2(properties.getProperty("SrchType"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("SrchType")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicking on SearchType dropdown..");

			if (doesElementExist2(properties.getProperty("TypeAdvance"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("TypeAdvance")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Advanced")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(4000);
						log.logLine(Testname, false, "Selecting the Search Type as Advanced from the dropdown..");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Search Type options are not displayed");
				throw new Exception("Search Type options are not displayed");
			}
		}



		if (doesElementExist2(properties.getProperty("FromDate"), 5)) {	  
			WebElement DteFromfld = driver.findElement(By.cssSelector(properties.getProperty("FromDate")));
			DteFromfld.clear();
			Thread.sleep(1000);
			DteFromfld.sendKeys(Fromdate);
			log.logLine(Testname, false, "Entering the from date value as "+Fromdate+"");

			WebElement DteTofld = driver.findElement(By.cssSelector(properties.getProperty("ToDate")));
			DteTofld.clear();
			Thread.sleep(1000);
			DteTofld.sendKeys(Todate);
			log.logLine(Testname, false, "Entering the To date value as "+Todate+"");
		}	  


		if (doesElementExist2(properties.getProperty("DateType"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("DateType")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Date Type dropdown..");

			if (doesElementExist2(properties.getProperty("Dtetyplst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Dtetyplst")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Data Processed by RRD Facility Date")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(3000);
						log.logLine(Testname, false, "Selecting the Date Type Name as Data Processed by RRD Facility Date from the dropdown..");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Date Type options are not displayed");
				throw new Exception("Date Type options are not displayed");
			}
		}


		if (doesElementExist2(properties.getProperty("Basicsrchvalue"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Basicsrchvalue")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on RRD Plant Type dropdown..");

			if (doesElementExist2(properties.getProperty("Basicsrchvalueoption"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Basicsrchvalueoption")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("RRD Plant")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the criteria as RRD Plant option from Basic Search criteria dropdown");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "RRD Plant option are not displayed");
				throw new Exception("RRD Plant option are not displayed");
			}
		}



		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("ClckLevel"), 5)) {	    
			WebElement srchbtn = driver.findElement(By.cssSelector(properties.getProperty("ClckLevel")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked on Search Level dropdown in Search dialog");


			if (doesElementExist2(properties.getProperty("SelLevel"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelLevel")));
				for (WebElement lnk:selopts) {

					if (lnk.getText().equals(Level)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(5000);
						log.logLine(Testname, false, "Selecting the Level as "+Level+" from the dropdown");							
						break;
					}				
				}
			}


		} else {
			log.logLine(Testname, false, "Clicking on SearchLevel dropdown in Search dialog is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on SearchType dropdown in Search dialog is failed");
		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Advanceoptions"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Advanceoptions")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Advance Search Options Search Type dropdown..");


			if (doesElementExist2(properties.getProperty("Advnceoptnslst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Advnceoptnslst")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(Field)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(3000);
						log.logLine(Testname, false, "Selecting the Option "+Field+" form advanced option dropdown");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Advance Search options are not displayed");
				throw new Exception("Advance Search options are not displayed");
			}

		} else if (doesElementExist2(properties.getProperty("Advoptns"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Advoptns")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Advance Search Options Search Type dropdown..");


			if (doesElementExist2(properties.getProperty("Advnceoptnslst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Advnceoptnslst")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(Field)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(3000);
						log.logLine(Testname, false, "Selecting the Option "+Field+" form advanced option dropdown");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Advance Search options are not displayed");
				throw new Exception("Advance Search options are not displayed");
			}
		}


		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Searchoption"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Searchoption")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Advance Search Options Search Type dropdown..");


			if (doesElementExist2(properties.getProperty("Searchoptionlst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Searchoptionlst")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(Operator)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(3000);
						log.logLine(Testname, false, "Selecting the Operator Option "+Operator+" form advanced option dropdown");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Advance Search options are not displayed");
				throw new Exception("Advance Search options are not displayed");
			}

		} else if (doesElementExist2(properties.getProperty("Srchoptn"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Srchoptn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Advance Search Options Search Type dropdown..");


			if (doesElementExist2(properties.getProperty("Searchoptionlst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Searchoptionlst")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(Operator)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(3000);
						log.logLine(Testname, false, "Selecting the Operator Option "+Operator+" form advanced option dropdown");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Advance Search options are not displayed");
				throw new Exception("Advance Search options are not displayed");
			}
		}


		if (doesElementExist2(properties.getProperty("TextField"), 5)) {
			WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("TextField")));
			Txt.clear();
			Txt.sendKeys(Text);
			log.logLine(Testname, false, "Entering the "+Field+" as "+Text+" on Text Field");
		} else {
			log.logLine(Testname, true, "Entering the "+Field+" is Failed");
			throw new Exception("Entering the "+Field+" is Failed");
		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(10000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Serach button");
		} else {
			log.logLine(Testname, true, "Clicking on Serach button is failed");
			throw new Exception("Clicking on Serach button is failed");
		}

	}


	public boolean SearchResultsValidation(String Testname) throws Exception {

		String[] Sort = new String[20];
		row = "tr";
		List<WebElement> DataCnt5= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));

		//int DataCnt5= driver.findElements(By.xpath("//div[@id='k-grid-content']/table/tbody/tr")).size();

		if(doesElementExist2(properties.getProperty("OrderRefGridresult"), 5)){
			for(int i = 0; i < DataCnt5.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='review-track-grid'] div[class='k-grid-content'] table tbody "+row+" td+td+td+td+td+td+td[role='gridcell']")).getText();
				String Result=Sort[i];
				String array[] = Result.split("-");
				String first = array[0].trim();
				String second = array[1].trim();
				String third = array[2].trim();	

				String Finalval=first+second+third;

				int Validtn = Finalval.compareTo(Textsrch);
				if(Validtn > 0)
					log.logLine(Testname, false, "**** Iterating through the Rows.... Results in the grid "+Finalval+" is greater than "+Textsrch+" **** ");

				else if(Validtn < 0)
					log.logLine(Testname, false, "**** Iterating through the Rows.... Results in the grid "+Finalval+" is lesser than "+Textsrch+" **** ");

				else if(Validtn == 0)
					log.logLine(Testname, false, "**** Iterating through the Rows.... Results in the grid "+Finalval+" is equals to "+Textsrch+" **** ");
				row = row + "+tr";
			}
		}

		return true;
	}

	
	


	public boolean SearchResultsValidation1(String Testname) throws Exception {

		String[] Sort = new String[20];
		row = "tr";
		List<WebElement> DataCnt5= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));

		String array[] = ClientIDText1.split(",");
		String first = array[0].trim();
		String second = array[1].trim();

		if(doesElementExist2(properties.getProperty("OrderClientIDGridresult"), 5)){
			for(int i = 0; i < DataCnt5.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='review-track-grid'] div[class='k-grid-content'] table tbody "+row+" td+td+td+td+td+td+td+td+td+td+td+td+td[role='gridcell']")).getText();
				String Result=Sort[i];

				if(Result.equals(first))
					log.logLine(Testname, false, "**** Iterating through the Rows....Results in the grid "+Result+" matches with the string "+first+" **** ");

				else if(Result.equals(second))
					log.logLine(Testname, false, "**** Iterating through the Rows....Results in the grid "+Result+" matches with the string "+second+" **** ");	

				row = row + "+tr";
			}
		}

		return true;
	}


	public boolean SearchResultsValidation2(String Testname) throws Exception {

		String[] Sort = new String[20];
		row = "tr";
		List<WebElement> DataCnt5= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));


		if(doesElementExist2(properties.getProperty("OrderClientIDGridresult"), 5)){
			for(int i = 0; i < DataCnt5.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='review-track-grid'] div[class='k-grid-content'] table tbody "+row+" td+td+td+td+td+td+td+td+td+td+td+td+td[role='gridcell']")).getText();
				String Result=Sort[i];

				if(Result.contains(ClientIDText2))
					log.logLine(Testname, false, "**** Iterating through the Rows.... Results in the grid "+Result+" contains the string "+ClientIDText2+" **** ");	

				row = row + "+tr";
			}
		}

		return true;
	}


	public boolean SearchResultsValidation3(String Testname) throws Exception {

		String[] Sort = new String[20];
		row = "tr";
		List<WebElement> DataCnt5= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));

		//int DataCnt5= driver.findElements(By.xpath("//div[@id='k-grid-content']/table/tbody/tr")).size();

		if(doesElementExist2(properties.getProperty("CopyBatchRRDCopyGridResult"), 5)){
			for(int i = 0; i < DataCnt5.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='review-track-grid'] div[class='k-grid-content'] table tbody "+row+" td+td+td+td+td+td+td+td+td+td+td[role='gridcell']")).getText();
				String Result=Sort[i];

				int Validtn = Result.compareTo(RRDCopyText);
				if(Validtn > 0)
					log.logLine(Testname, false, "**** Iterating through the Rows.... Results in the grid "+Result+" is greater than "+RRDCopyText+" **** ");

				else if(Validtn < 0)
					log.logLine(Testname, false, "**** Iterating through the Rows.... Results in the grid "+Result+" is lesser than "+RRDCopyText+" **** ");

				else if(Validtn == 0)
					log.logLine(Testname, false, "**** Iterating through the Rows.... Results in the grid "+Result+" is equals to "+RRDCopyText+" **** ");
				row = row + "+tr";
			}
		}

		return true;
	}


	public boolean SearchResultsValidation4(String Testname) throws Exception {

		String[] Sort = new String[20];
		row = "tr";
		List<WebElement> DataCnt5= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));


		if(doesElementExist2(properties.getProperty("CopyBatchRefGridresult"), 5)){
			for(int i = 0; i < DataCnt5.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='review-track-grid'] div[class='k-grid-content'] table tbody "+row+" td+td+td+td+td+td[role='gridcell']")).getText();
				String Result=Sort[i];

				if(Result.contains(RRDCopyRefText))
					log.logLine(Testname, false, "**** Iterating through the Rows.... Results in the grid "+Result+" contains the string "+RRDCopyRefText+" **** ");	

				row = row + "+tr";
			}
		}

		return true;
	}


	public boolean SearchResultsValidation5(String Testname) throws Exception {

		String[] Sort = new String[20];
		row = "tr";
		List<WebElement> DataCnt5= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));

		//int DataCnt5= driver.findElements(By.xpath("//div[@id='k-grid-content']/table/tbody/tr")).size();

		if(doesElementExist2(properties.getProperty("DocIDGridResult"), 5)){
			for(int i = 0; i < DataCnt5.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='review-track-grid'] div[class='k-grid-content'] table tbody "+row+" td+td+td+td+td[role='gridcell']")).getText();
				String Result=Sort[i];

				int Validtn = Result.compareTo(DocidText);
				if(Validtn > 0)
					log.logLine(Testname, false, "**** Iterating through the Rows.... "+Result+" is greater than "+DocidText+" **** ");

				else if(Validtn < 0)
					log.logLine(Testname, false, "**** Iterating through the Rows.... "+Result+" is lesser than "+DocidText+" **** ");

				else if(Validtn == 0)
					log.logLine(Testname, false, "**** Iterating through the Rows.... "+Result+" is equals to "+DocidText+" **** ");
				row = row + "+tr";
			}
		}

		return true;
	}


	public boolean SearchResultsValidation6(String Testname) throws Exception {

		String[] Sort = new String[20];
		row = "tr";
		List<WebElement> DataCnt5= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));

		String array[] = ConsidText.split(",");
		String first = array[0].trim();
		String second = array[1].trim();

		if(doesElementExist2(properties.getProperty("ConsIDGridResult"), 5)){
			for(int i = 0; i < DataCnt5.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='review-track-grid'] div[class='k-grid-content'] table tbody "+row+" td+td[role='gridcell']")).getText();
				String Result=Sort[i];

				if(Result.equals(first))
					log.logLine(Testname, false, "**** Iterating through the Rows....Results in the grid "+Result+" matches with the string "+first+" **** ");

				else if(Result.equals(second))
					log.logLine(Testname, false, "**** Iterating through the Rows....Results in the grid "+Result+" matches with the string "+second+" **** ");	

				row = row + "+tr";
			}
		}

		return true;
	}

	/*
 	public boolean SearchResultsValidation66(String Testname) throws Exception {

 		String[] Sort = new String[20];
		row = "tr";
		List<WebElement> DataCnt5= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));

		String array[] = DocidText1.split(",");
		String first = array[0].trim();
		String second = array[1].trim();

		if(doesElementExist2(properties.getProperty("DocIDGridResult"), 5)){
			for(int i = 0; i < DataCnt5.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='review-track-grid'] div[class='k-grid-content'] table tbody "+row+" td+td+td+td+td[role='gridcell']")).getText();
				String Result=Sort[i];

				if(Result.equals(first))
				log.logLine(Testname, false, "**** Iterating through the Rows....Results in the grid "+Result+" matches with the string "+first+" **** ");

				else if(Result.equals(second))
				log.logLine(Testname, false, "**** Iterating through the Rows....Results in the grid "+Result+" matches with the string "+second+" **** ");	

				row = row + "+tr";
				}
			}

 		return true;
 	}
	 */	


	public boolean SearchResultsValidation7(String Testname) throws Exception {

		String[] Sort = new String[20];
		row = "tr";
		List<WebElement> DataCnt5= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));


		if(doesElementExist2(properties.getProperty("AccNumGridresult"), 5)){
			for(int i = 0; i < DataCnt5.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='review-track-grid'] div[class='k-grid-content'] table tbody "+row+" td[role='gridcell']")).getText();
				String Result=Sort[i];

				if(Result.contains(AccNumText))
					log.logLine(Testname, false, "**** Iterating through the Rows.... Results in the grid "+Result+" contains the string "+AccNumText+" **** ");	

				row = row + "+tr";
			}
		}

		return true;
	}

	public boolean SearchResultsValidation8(String Testname) throws Exception {
		Thread.sleep(10000);
		String[] Sort = new String[20];
		row = "tr";
		List<WebElement> DataCnt5= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));


		if(doesElementExist2(properties.getProperty("MailFlagGridresult"), 5)){
			for(int i = 0; i < DataCnt5.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='review-track-grid'] div[class='k-grid-content'] table tbody "+row+" td+td+td+td+td+td+td[role='gridcell']")).getText();
				String Result=Sort[i];

				if(Result.contains(MailflagP)||Result.contains(MailflagC))
					log.logLine(Testname, false, "**** Iterating through the Rows.... Searched Results contains the string "+Result+" in the grid**** ");	

				row = row + "+tr";
			}
		}

		return true;
	}

	public boolean SearchResultsValidation9(String Testname) throws Exception {
		Thread.sleep(10000);
		String[] Sort = new String[20];
		row = "tr";
		List<WebElement> DataCnt5= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));


		if(doesElementExist2(properties.getProperty("LendernumGridresult"), 5)){
			for(int i = 0; i < DataCnt5.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='review-track-grid'] div[class='k-grid-content'] table tbody "+row+" td+td+td+td+td+td+td+td+td+td[role='gridcell']")).getText();
				String Result=Sort[i];

				if(Result.equals(Lendernum))
					log.logLine(Testname, false, "**** Iterating through the Rows.... Searched Results Matches with the string "+Lendernum+" in the grid**** ");	

				row = row + "+tr";
			}
		}

		return true;
	}
	
	public boolean SearchResultsValidation10(String Testname) throws Exception {

		String[] Sort = new String[20];
		row = "tr";
		List<WebElement> DataCnt5= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));

		//int DataCnt5= driver.findElements(By.xpath("//div[@id='k-grid-content']/table/tbody/tr")).size();

		if(doesElementExist2(properties.getProperty("OrderRefGridresult1"), 5)){
			for(int i = 0; i < DataCnt5.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='review-track-grid'] div[class='k-grid-content'] table tbody "+row+" td[role='gridcell']")).getText();
				String Result=Sort[i];
				String array[] = Result.split("-");
				String first = array[0].trim();
				
				String Finalval=first;

				int Validtn = Finalval.compareTo(RefTextsrch);
				if(Validtn > 0)
					log.logLine(Testname, false, "**** Iterating through the Rows.... Results in the grid "+Finalval+" is greater than "+RefTextsrch+" **** ");

				else if(Validtn < 0)
					log.logLine(Testname, false, "**** Iterating through the Rows.... Results in the grid "+Finalval+" is lesser than "+RefTextsrch+" **** ");

				else if(Validtn == 0)
					log.logLine(Testname, false, "**** Iterating through the Rows.... Results in the grid "+Finalval+" is equals to "+RefTextsrch+" **** ");
				row = row + "+tr";
			}
		}

		return true;
	}
	
	public boolean SearchResultsValidation11(String Testname) throws Exception {

		String[] Sort = new String[20];
		row = "tr";
		List<WebElement> DataCnt5= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));


		if(doesElementExist2(properties.getProperty("CopyBatchRRDBatchGridresult"), 5)){
			for(int i = 0; i < DataCnt5.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='review-track-grid'] div[class='k-grid-content'] table tbody "+row+" td+td+td+td[role='gridcell']")).getText();
				String Result=Sort[i];

				if(Result.equals(RRDCopyBatchText))
					log.logLine(Testname, false, "**** Iterating through the Rows.... Searched Results Matches with the string "+RRDCopyBatchText+" in the grid**** ");	

				row = row + "+tr";
			}
		}

		return true;
	}
	
	
	public boolean SearchResultsValidation12(String Testname) throws Exception {

		String[] Sort = new String[20];
		row = "tr";
		List<WebElement> DataCnt5= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));

		//int DataCnt5= driver.findElements(By.xpath("//div[@id='k-grid-content']/table/tbody/tr")).size();

		if(doesElementExist2(properties.getProperty("CopyBatchRRDCopyGridResult1"), 5)){
			for(int i = 0; i < DataCnt5.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='review-track-grid'] div[class='k-grid-content'] table tbody "+row+" td+td+td[role='gridcell']")).getText();
				String Result=Sort[i];

				if(Result.equals(RRDCopynumText))
					log.logLine(Testname, false, "**** Iterating through the Rows.... Searched Results Matches with the string "+RRDCopynumText+" in the grid**** ");	

				row = row + "+tr";
			}
		}


		return true;
	}
	
	public boolean Datevalidation(String Testname) throws Exception {

		String[] Sort = new String[20];
		row = "tr";
		List<WebElement> DataCnt5= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));

		
		if(doesElementExist2(properties.getProperty("Datevalidation"), 5)){
			for(int i = 0; i < DataCnt5.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='review-track-grid'] div[class='k-grid-content'] table tbody "+row+" td+td+td+td+td[role='gridcell']")).getText();
				String Result=Sort[i];

				log.logLine(Testname, false, "**** Iterating through the Rows.... Searched Date Results are "+Result+" in the grid**** ");	

				row = row + "+tr";
			}
		}


		return true;
	}

	public boolean Datevalidation1(String Testname) throws Exception {

		String[] Sort = new String[20];
		row = "tr";
		List<WebElement> DataCnt5= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));

	
		if(doesElementExist2(properties.getProperty("Datevalidation1"), 5)){
			for(int i = 0; i < DataCnt5.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='review-track-grid'] div[class='k-grid-content'] table tbody "+row+" td+td+td+td+td+td+td[role='gridcell']")).getText();
				String Result=Sort[i];

				log.logLine(Testname, false, "**** Iterating through the Rows.... Searched Date Results are "+Result+" in the grid**** ");	

				row = row + "+tr";
			}
		}


		return true;
	}
	
	
	public boolean ClientAppSel1(String AccNo, String Testname) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();


		Thread.sleep(1000);
		driver.switchTo().defaultContent();

		Thread.sleep(2000);
		WebElement retelm2 = waitForElement(properties.getProperty("selClint1"));

		if (doesElementExist2(properties.getProperty("JobTracking"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("JobTracking")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);	    	 
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Click on JobTracking Module is Successful");
		} else {
			log.logLine(Testname, true, "Click on JobTracking Module is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on JobTracking Module is failed");
		}

		Thread.sleep(5000);
		PleasewaitDisappear();


		//Selecting the Client and Application name from popup
		Thread.sleep(1000);
		boolean CliSelected = false;
		String ClntName = test.readColumnData("ClientName1", sheetname);

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
		String AppName = test.readColumnData("ApplicationName1", sheetname);

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
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on OK button");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the Reports is failed");
			throw new Exception("Clicking on OK button to view the Reports is failed");
		}

		return true;
	}	




	public boolean VerifyHyperlink(String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(4000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button");
		} else {
			log.logLine(Testname, true, "Clicking on Search is failed");
			throw new Exception("Clicking on Search is failed");
		}


		if (doesElementExist2(properties.getProperty("SrchType"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("SrchType")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on SearchType dropdown..");

			if (doesElementExist2(properties.getProperty("TypeAdvance"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("TypeAdvance")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Advanced")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(2000);
						log.logLine(Testname, false, "Selecting the Search Type as "+lnk.getText()+" from the dropdown..");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Search Type options are not displayed");
				throw new Exception("Search Type options are not displayed");
			}
		}


		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("ClckLevel"), 5)) {	    
			WebElement srchbtn = driver.findElement(By.cssSelector(properties.getProperty("ClckLevel")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked on Search Level dropdown in Search dialog");


			if (doesElementExist2(properties.getProperty("SelLevel"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelLevel")));
				for (WebElement lnk:selopts) {

					if (lnk.getText().equals("Order")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(2000);
						log.logLine(Testname, false, "Selecting the Level as Order from the dropdown");							
						break;
					}				
				}
			}


		} else {
			log.logLine(Testname, false, "Clicking on SearchLevel dropdown in Search dialog is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on SearchType dropdown in Search dialog is failed");
		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(5000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Serach button");
		} else {
			log.logLine(Testname, true, "Clicking on Serach button is failed");
			throw new Exception("Clicking on Serach button is failed");
		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("OrderRefhyperlink"), 5)) {
			WebElement Refnum = driver.findElement(By.cssSelector(properties.getProperty("OrderRefhyperlink")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Refnum);
			Thread.sleep(10000);
			log.logLine(Testname, false, "Clicking on Referenece Number Hyperlink");
			log.logLine(Testname, false, "****Clicking Hyperlink for Reference Number is Successful****");

		} else {
			log.logLine(Testname, true, "Clicking Hyperlink for Reference Number is Failed");
			throw new Exception("Clicking Hyperlink for Reference Number is Failed");
		}


		if (doesElementExist2(properties.getProperty("Packagesrch"), 5)) {
			String Pkgsrc = driver.findElement(By.cssSelector(properties.getProperty("Packagesrch"))).getText();
			Thread.sleep(1000);
			log.logLine(Testname, false, "**** Click on hyperlink Navigated to "+Pkgsrc+" screen successfully**** ");

		} else {
			log.logLine(Testname, true, "****Click on hyperlink navigation is failed****");
			throw new Exception("Click on hyperlink navigation is failed");
		}



		if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(4000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button");
		} else {
			log.logLine(Testname, true, "Clicking on Search is failed");
			throw new Exception("Clicking on Search is failed");
		}


		if (doesElementExist2(properties.getProperty("SrchType"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("SrchType")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on SearchType dropdown..");

			if (doesElementExist2(properties.getProperty("TypeAdvance"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("TypeAdvance")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Advanced")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(2000);
						log.logLine(Testname, false, "Selecting the Search Type as "+lnk.getText()+" from the dropdown..");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Search Type options are not displayed");
				throw new Exception("Search Type options are not displayed");
			}
		}


		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("ClckLevel"), 5)) {	    
			WebElement srchbtn = driver.findElement(By.cssSelector(properties.getProperty("ClckLevel")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked on Search Level dropdown in Search dialog");


			if (doesElementExist2(properties.getProperty("SelLevel"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelLevel")));
				for (WebElement lnk:selopts) {

					if (lnk.getText().equals("Copy/Batch")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(2000);
						log.logLine(Testname, false, "Selecting the Level as Copy/Batch from the dropdown");							
						break;
					}				
				}
			}


		} else {
			log.logLine(Testname, false, "Clicking on SearchLevel dropdown in Search dialog is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on SearchType dropdown in Search dialog is failed");
		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(5000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Serach button");
		} else {
			log.logLine(Testname, true, "Clicking on Serach button is failed");
			throw new Exception("Clicking on Serach button is failed");
		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("CopyRefhyperlink"), 5)) {
			WebElement Refnum = driver.findElement(By.cssSelector(properties.getProperty("CopyRefhyperlink")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Refnum);
			Thread.sleep(5000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Referenece Number Hyperlink");
			log.logLine(Testname, false, "****Clicking Hyperlink for Reference Number is Successful****");

		}else if (doesElementExist2(properties.getProperty("CopyRefhyperlink1"), 5)) {
			WebElement Refnum = driver.findElement(By.cssSelector(properties.getProperty("CopyRefhyperlink1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Refnum);
			Thread.sleep(5000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Referenece Number Hyperlink");
			log.logLine(Testname, false, "****Clicking Hyperlink for Reference Number is Successful****"); 	
		} else {
			log.logLine(Testname, true, "Clicking Hyperlink for Reference Number is Failed");
			throw new Exception("Clicking Hyperlink for Reference Number is Failed");
		}


		if (doesElementExist2(properties.getProperty("Packagesrch"), 5)) {
			String Pkgsrc = driver.findElement(By.cssSelector(properties.getProperty("Packagesrch"))).getText();
			Thread.sleep(1000);
			log.logLine(Testname, false, "**** Click on hyperlink Navigated to "+Pkgsrc+" screen successfully**** ");

		} else {
			log.logLine(Testname, true, "****Click on hyperlink navigation is failed****");
			throw new Exception("Click on hyperlink navigation is failed");
		}



		Thread.sleep(5000);
		driver.switchTo().defaultContent();

		if (doesElementExist2(properties.getProperty("JobSearchtab"), 5)) {
			WebElement jobsrch = driver.findElement(By.cssSelector(properties.getProperty("JobSearchtab")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", jobsrch);
			Thread.sleep(5000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "**** Clicking Job Search sub module is Successful **** ");

		} else {
			log.logLine(Testname, true, "Clicking Job Search sub module is Failed");
			throw new Exception("Clicking Job Search sub module is Failed");
		}

		return true;
	}

	//.................. comma delimiter validations......................................

	public boolean commadelimiter(String AccNo,String Testname) throws Exception {
		ClientAppSel(AccNo, Testname);

		Thread.sleep(12000);
		driver.switchTo().frame("iframeContainer");
		
		if (doesElementExist2(properties.getProperty("Recntactybtn"), 5)) {	    
			WebElement rectact = driver.findElement(By.cssSelector(properties.getProperty("Recntactybtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rectact);
			PleasewaitDisappear();
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicked on recent Activity button in JobTracking page");	

		} else {
			log.logLine(Testname, true, "Clicked on recent Activity button in JobTracking page is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicked on recent Activity button in JobTracking page is failed");
		}

		if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button");
		} else {
			log.logLine(Testname, true, "Clicking on Search is failed");
			throw new Exception("Clicking on Search is failed");
		}

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("Clearbtn"), 5)) {
			WebElement clear = driver.findElement(By.cssSelector(properties.getProperty("Clearbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clear);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on clear button");
		} else {
			log.logLine(Testname, true, "Clicking on clear button failed");
			//throw new Exception("Clicking on clear button failed");
		}


		Thread.sleep(1000);
		if (doesElementExist2(properties.getProperty("SrchType"), 5)) {	   
			WebElement Srchtype = driver.findElement(By.cssSelector(properties.getProperty("SrchType")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Srchtype);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on SearchType dropdown..");

			if (doesElementExist2(properties.getProperty("TypeAdvance"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("TypeAdvance")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Quick (Order)")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Search Type as 'Quick' from the dropdown..");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Search Type options are not displayed");
				throw new Exception("Search Type options are not displayed");
			}
		}

		if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
			WebElement srch = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srch);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button");
		} else {
			log.logLine(Testname, true, "Clicking on Search is failed");
			throw new Exception("Clicking on Search is failed");
		}

		Thread.sleep(6000);
		refernctxt=pickdatafromgrid(Testname);
		Thread.sleep(5000);
		commadelimitersrch(Testname,"Copy/Batch","Ref number","Multiple (comma-separated)",refernctxt);
		Thread.sleep(4000);
		commadelimiter_gridvalidation(Testname,refernctxt);
		
		return true;

	}

	public String pickdatafromgrid(String Testname) throws Exception {
		String reftxt="  ";
		String[] ref = new String[20];
		row = "tr";
		List<WebElement> Data= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));
		int abc=Data.size();
		if(doesElementExist2(properties.getProperty("Refno"), 5)){
			Thread.sleep(3000);
			for(int i = 0; i < Data.size(); i++) {
				ref[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row+" td[role='gridcell']")).getText();
				row = row + "+tr";
				if(ref[i].startsWith(refstart))
				{reftxt=reftxt+","+ref[i];}}}
		Thread.sleep(2000);
		if(reftxt.equalsIgnoreCase(null))
		{log.logLine(Testname, true, "Text to be searched is null");
		throw new Exception("Text to be searched is null");
		}
		else{
			log.logLine(Testname, false, "Text to be searched is "+reftxt);
		}
		return reftxt;

	}

	public void commadelimitersrch(String Testname,String Level,String Field,String Operator,String refernctxt) throws Exception {


		if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button");
		} else {
			log.logLine(Testname, true, "Clicking on Search is failed");
			throw new Exception("Clicking on Search is failed");
		}
		Thread.sleep(3000);

		if (doesElementExist2(properties.getProperty("SrchType"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("SrchType")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on SearchType dropdown..");

			if (doesElementExist2(properties.getProperty("TypeAdvance"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("TypeAdvance")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Advanced")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Search Type as Advanced from the dropdown..");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Search Type options are not displayed");
				throw new Exception("Search Type options are not displayed");
			}
		}
		Thread.sleep(3000);
		if (doesElementExist2(properties.getProperty("ClckLevel"), 5)) {	    
			WebElement srchbtn = driver.findElement(By.cssSelector(properties.getProperty("ClckLevel")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked on Search Level dropdown in Search dialog");


			if (doesElementExist2(properties.getProperty("SelLevel"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelLevel")));
				for (WebElement lnk:selopts) {

					if (lnk.getText().equals(Level)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(5000);
						log.logLine(Testname, false, "Selecting the Level as "+Level+" from the dropdown");							
						break;
					}				
				}
			}


		} else {
			log.logLine(Testname, false, "Clicking on SearchLevel dropdown in Search dialog is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on SearchType dropdown in Search dialog is failed");
		}



		Thread.sleep(5000);


		if (doesElementExist2(properties.getProperty("Advanceoptions"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Advanceoptions")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Advance Search Options Search Type dropdown..");


			if (doesElementExist2(properties.getProperty("Advnceoptnslst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Advnceoptnslst")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(Field)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(3000);
						log.logLine(Testname, false, "Selecting the Option "+Field+" form advanced option dropdown");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Advance Search options are not displayed");
				throw new Exception("Advance Search options are not displayed");
			}

		} else if (doesElementExist2(properties.getProperty("Advoptns"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Advoptns")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Advance Search Options Search Type dropdown..");


			if (doesElementExist2(properties.getProperty("Advnceoptnslst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Advnceoptnslst")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(Field)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(9000);
						log.logLine(Testname, false, "Selecting the Option "+Field+" form advanced option dropdown");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Advance Search options are not displayed");
				throw new Exception("Advance Search options are not displayed");
			}
		}


		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Searchoption"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Searchoption")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Advance Search Options Search Type dropdown..");


			if (doesElementExist2(properties.getProperty("Searchoptionlst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Searchoptionlst")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(Operator)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(3000);
						log.logLine(Testname, false, "Selecting the Operator Option "+Operator+" form advanced option dropdown");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Advance Search options are not displayed");
				throw new Exception("Advance Search options are not displayed");
			}

		} else if (doesElementExist2(properties.getProperty("Srchoptn"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Srchoptn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Advance Search Options Search Type dropdown..");


			if (doesElementExist2(properties.getProperty("Searchoptionlst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Searchoptionlst")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(Operator)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(5000);
						log.logLine(Testname, false, "Selecting the Operator Option "+Operator+" form advanced option dropdown");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Advance Search options are not displayed");
				throw new Exception("Advance Search options are not displayed");
			}
		}
		Thread.sleep(8000);

		if (doesElementExist2(properties.getProperty("TextField"), 5)) {
			WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("TextField")));
			Txt.clear();
			Txt.sendKeys(refernctxt);
			log.logLine(Testname, false, "Entering the Ref number as "+refernctxt +" on Text Field");
			Thread.sleep(2000);
			String Txt1 = driver.findElement(By.cssSelector(properties.getProperty("TextField"))).getAttribute("value");
			int Srnglngth=Txt1.length();

			if (Srnglngth>50){
				log.logLine(Testname, false, "Length of refernceText is: "+Srnglngth);
			}else if (Srnglngth<50){
				Txt.clear();
				Txt.sendKeys(Testdata);
				String Txt2= driver.findElement(By.cssSelector(properties.getProperty("TextField"))).getAttribute("value");
				int Srnglngth1=Txt2.length();
				log.logLine(Testname, false, "Length of Testdata is: "+Srnglngth1);
			}
			Txt.clear();
			Txt.sendKeys(refernctxt);
			Thread.sleep(2000);
		} else {
			log.logLine(Testname, true, "Entering the RRD Order Number is Failed");
			throw new Exception("Entering the RRD Order Number is Failed");
		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Search"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Search")));
			Highlight(okbtn);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);		
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Serach button");
		} else {
			log.logLine(Testname, true, "Clicking on Serach button is failed");
			throw new Exception("Clicking on Serach button is failed");
		}

		Thread.sleep(4000);}


	public boolean commadelimiter_gridvalidation(String Testname,String refernctxt) throws Exception {

		String array[] = refernctxt.split(",");
		Thread.sleep(1000);
		String[] Sort = new String[20];
		row = "tr";
		List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));

		if(doesElementExist2(properties.getProperty("OrderRefGridresult"), 5)){
			for(int i = 0; i < DataCnt.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='review-track-grid'] div[class='k-grid-content'] table tbody "+row+" td+td+td+td+td+td")).getText();
			/*	Thread.sleep(2000);
				String Text=array[i+1].trim();
				if(Sort[i].equals(Text)){
					log.logLine(Testname, false, "**** Ref number "+Text+"  matches with "+Sort[i]+" ****" );

				}else{
					log.logLine(Testname, true, "Ref number does not matches with "+Sort[i]);

				}*/
				
				if( refernctxt.contains(Sort[i])){
					log.logLine(Testname, false, "**** Ref number "+Sort[i] +" matches with the search data" );

				}else{
					log.logLine(Testname, true, "Ref number "+Sort[i]+" does not matches with search data");

				}
				
				row = row + "+tr";
			}
		}

		if(doesElementExist2("div[data-role='pager'] span[class='k-pager-info k-label']", 5)){
			String nodoc =driver.findElement(By.cssSelector(properties.getProperty("NoDocMesg"))).getText();
			if(nodoc.equals("No items to display")){
				log.logLine(Testname, false, "There are no documents matching with the search criteria");
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






























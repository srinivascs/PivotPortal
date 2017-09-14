package pivotModules;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class JobTrackingRecentActivity extends Page{

	public static String row = "tr";
	//public static String stsType1;
	//public static String stsType2;
	//public static String stsType3;
	//public static String stsType4;
	//public static String stsType5;
	public static String FrmDte;
	public static String todaysDate;
	public static String actval, actval1;
	public static String refernum;



	public JobTrackingRecentActivity(WebDriver driver, Log log) throws InvalidFormatException, IOException {
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
			log.logLine(Testname, false, "Click on Job Tracking Module is Successful");
		} else {
			log.logLine(Testname, true, "Click on Job Tracking Module is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Job Tracking Module is failed");
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
			log.logLine(Testname, false, "Clicking on OK button to view the Archives");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the Archives is failed");
			throw new Exception("Clicking on OK button to view the Archives is failed");
		}


		return true;
	}


	public boolean RecentActivity(String AccNo,String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		Date date = new Date();	
		todaysDate = dateFormat.format(date);

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
		

		if (doesElementExist2(properties.getProperty("ViewPkgeToolTip"), 5)) {
			String col = driver.findElement(By.cssSelector(properties.getProperty("ViewPkgeToolTip"))).getAttribute("title");
			log.logLine(Testname, false, "Reading 3rd column data under Details Table is Successful-----"+col);
		}else{
			log.logLine(Testname, true, "Reading 3rd column data under Details Table is unSuccessful");

		}


		if (doesElementExist2(properties.getProperty("TrackHeader1"), 5)) {
			String col1 = driver.findElement(By.cssSelector(properties.getProperty("TrackHeader1"))).getText();
			log.logLine(Testname, false, "Reading 3rd column data under Details Table is Successful-----"+col1);
		}else{
			log.logLine(Testname, true, "Reading 3rd column data under Details Table is unSuccessful");

		}

		if (doesElementExist2(properties.getProperty("TrackHeader2"), 5)) {
			String col2 = driver.findElement(By.cssSelector(properties.getProperty("TrackHeader2"))).getText();
			log.logLine(Testname, false, "Reading 3rd column data under Details Table is Successful-----"+col2);
		}else{
			log.logLine(Testname, true, "Reading 3rd column data under Details Table is unSuccessful");

		}

		if (doesElementExist2(properties.getProperty("TrackHeader3"), 5)) {
			String col3 = driver.findElement(By.cssSelector(properties.getProperty("TrackHeader3"))).getText();
			log.logLine(Testname, false, "Reading 3rd column data under Details Table is Successful-----"+col3);
		}else{
			log.logLine(Testname, true, "Reading 3rd column data under Details Table is unSuccessful");

		}

		if (doesElementExist2(properties.getProperty("TrackHeader4"), 5)) {
			String col4 = driver.findElement(By.cssSelector(properties.getProperty("TrackHeader4"))).getText();
			log.logLine(Testname, false, "Reading 3rd column data under Details Table is Successful-----"+col4);
		}else{
			log.logLine(Testname, true, "Reading 3rd column data under Details Table is unSuccessful");

		}

		if (doesElementExist2(properties.getProperty("TrackHeader5"), 5)) {
			String col5 = driver.findElement(By.cssSelector(properties.getProperty("TrackHeader5"))).getText();
			log.logLine(Testname, false, "Reading 3rd column data under Details Table is Successful-----"+col5);
		}else{
			log.logLine(Testname, true, "Reading 3rd column data under Details Table is unSuccessful");

		}


		//String[] Sort = new String[50];
		//int length = Sort.length;



		if (doesElementExist(properties.getProperty("Page"), 5)) {	 
			WebElement pge = driver.findElement(By.xpath(properties.getProperty("Page")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", pge);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Click on Page Selection button");

			List<WebElement> editbtn = driver.findElements(By.cssSelector(properties.getProperty("selectpage")));
			for (WebElement lnk:editbtn) {
				if (lnk.getText().equals("50")){
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(4000);
					log.logLine(Testname, false, "Selecting the Option 50 Per Page from Drop down");
					break;
				} 
			}
		}

		if (doesElementExist(properties.getProperty("Items"), 5)) {	    
			String val = driver.findElement(By.xpath(".//*[@id='review-track-grid']/div[3]/span[2]")).getText();
			String arr[] = val.split("of");
			String actval = arr[0].trim();
			String actval2=arr[1].trim();
			String array1[]=actval.split(":");
			String newval=array1[1].trim();
			String two[]=newval.split("-");
			String value=two[1].trim();
			log.logLine(Testname, false, "The total number of documents displayed are:" +newval);
		}

		if (doesElementExist2(properties.getProperty("Records"), 5)) {
			log.logLine(Testname, false, "Successfully displayed the records for entered text in srchType field");		    		    	
		} else {
			log.logLine(Testname, false, "No records displayed for entered text in srchType field");
		}


		if (doesElementExist(properties.getProperty("Page"), 5)) {	 
			WebElement pge = driver.findElement(By.xpath(properties.getProperty("Page")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", pge);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Click on Page Selection button");

			List<WebElement> editbtn = driver.findElements(By.cssSelector(properties.getProperty("selectpage")));
			for (WebElement lnk:editbtn) {
				if (lnk.getText().equals("100")){
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Selecting the Option 100 Per Page from Drop down");
					break;
				} 
			}
		}

		if (doesElementExist(properties.getProperty("Items"), 5)) {	    
			String val = driver.findElement(By.xpath(".//*[@id='review-track-grid']/div[3]/span[2]")).getText();
			String arr[] = val.split("of");
			String actval = arr[0].trim();
			String actval2=arr[1].trim();
			String array1[]=actval.split(":");
			String newval=array1[1].trim();
			String two[]=newval.split("-");
			String value=two[1].trim();
			log.logLine(Testname, false, "The total number of documents displayed are:" +newval);
		}

		if (doesElementExist2(properties.getProperty("Records"), 5)) {
			log.logLine(Testname, false, "Successfully displayed the records for entered text in srchType field");		    		    	
		} else {
			log.logLine(Testname, false, "No records displayed for entered text in srchType field");
		}


		if (doesElementExist(properties.getProperty("Page"), 5)) {	 
			WebElement pge = driver.findElement(By.xpath(properties.getProperty("Page")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", pge);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Click on Page Selection button");

			List<WebElement> editbtn = driver.findElements(By.cssSelector(properties.getProperty("selectpage")));
			for (WebElement lnk:editbtn) {
				if (lnk.getText().equals("20")){
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Selecting the Option 20 Per Page from Drop down");
					break;
				} 
			}
		}

		if (doesElementExist(properties.getProperty("Items"), 5)) {	    
			String val = driver.findElement(By.xpath(".//*[@id='review-track-grid']/div[3]/span[2]")).getText();
			String arr[] = val.split("of");
			String actval = arr[0].trim();
			String actval2=arr[1].trim();
			String array1[]=actval.split(":");
			String newval=array1[1].trim();
			String two[]=newval.split("-");
			String value=two[1].trim();
			log.logLine(Testname, false, "The total number of documents displayed are:" +newval);
		}

		if (doesElementExist2(properties.getProperty("Records"), 5)) {
			log.logLine(Testname, false, "Successfully displayed the records for entered text in srchType field");		    		    	
		} else {
			log.logLine(Testname, false, " No records displayed for entered text in srchType field");
		}


		//-------------------------------

		String[] Sort1 = new String[400];
		row = "tr";
		List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='review-track-grid']/div[2]/table/tbody/tr"));

		if(doesElementExist2(properties.getProperty("PrintStatus"), 5)){
			for(int i = 0; i < DataCnt.size(); i++) {
				Sort1[i] = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row+" td+td+td[role='gridcell']")).getText();

				WebElement RowClk = driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody "+row+" td+td+td[role='gridcell']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", RowClk);

				PleasewaitDisappear();

				String row1 = "tr";
				String row2 = "tr";
				List<WebElement> DataCnt1= driver.findElements(By.xpath(".//*[@id='panel-record-activity-status-grid']/table/tbody/tr"));

				if(doesElementExist2(properties.getProperty("Type"), 5)){
					for(int j = 0; j < DataCnt1.size(); j++) {
						Sort1[j] = driver.findElement(By.cssSelector("div[id='panel-record-activity-status-grid'] table tbody "+row1+" td")).getText();

						log.logLine(Testname, false, "Iterating through the Rows....Rows Have the Type in Activity/Status as "+Sort1[j]);

						row1 = row1 + "+tr";

					}

				}

				if(doesElementExist2(properties.getProperty("DateTime"), 5)){
					for(int k = 0; k < DataCnt1.size(); k++) {
						Sort1[k] = driver.findElement(By.cssSelector("div[id='panel-record-activity-status-grid'] table tbody "+row2+" td+td")).getText();
						log.logLine(Testname, false, "Iterating through the Rows....Rows Have the Date & Time in Activity/Status as "+Sort1[k]);

						row2 = row2 + "+tr";

					}

				}

				row = row + "+tr";
				log.logLine(Testname, false, "Iterating through the Rows....Rows Have the Status in Tracking main page as "+Sort1[i]);
			}

		}


		//Reading the activity/status in detail for status type PROCESSED

		//String stsType1 = test.readColumnData("StatusType1", sheetname);
		//  Quick_Search(Testname, "StatusType1", stsType1);

		//Reading the activity/status in detail for status type SHIPPED

		String stsType2 = test.readColumnData("StatusType2", sheetname);
		Quick_Search(Testname, "StatusType2", stsType2);

		//Reading the shipping info 

		/* if (doesElementExist2(properties.getProperty("ShippingInfo"), 5)) {	 
 				WebElement shipinfobtn = driver.findElement(By.cssSelector(properties.getProperty("ShippingInfo")));
 				((JavascriptExecutor) driver).executeScript("arguments[0].click()", shipinfobtn);
 				log.logLine(Testname, false, "Clicking on shipping info button on right corner of the recent activity page");
 			} else {
 				log.logLine(Testname, true, "Clicking on shipping info button on right corner of the recent activity page is failed");
 				driver.switchTo().defaultContent();
 				throw new Exception("Clicking on shipping info button on right corner of the recent activity page is failed");
 			}
		 */


		//List<WebElement> shipcnt= driver.findElements(By.cssSelector(".//*[@id='tabOrderPrintShipping']/tbody/tr"));


		if (doesElementExist2(properties.getProperty("ContentDetail"), 5)) {
			WebElement cntdtl = driver.findElement(By.cssSelector(properties.getProperty("ContentDetail")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", cntdtl);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on content detail button");
		} else {
			log.logLine(Testname, true, "Clicking on content detail is failed");
			throw new Exception("Clicking on content detail is failed");
		}

		List<WebElement> cnt= driver.findElements(By.xpath(".//*[@id='panel-file-details-grid']/table/tbody/tr"));

		String[] RefField = new String[20];
		String[] Details = new String[20];
		String row1 = "tr";
		String row2 = "tr";

		if(doesElementExist2(properties.getProperty("RefField"), 5)){
			for(int j = 0; j < cnt.size(); j++) {
				RefField[j] = driver.findElement(By.cssSelector("div[id='panel-file-details-grid'] table tbody "+row1+" td")).getText();

				log.logLine(Testname, false, "Ref field of the Content Detail is "+RefField[j]);

				row1 = row1 + "+tr";

			}

		}

		if(doesElementExist2(properties.getProperty("Details"), 5)){
			for(int j = 0; j < cnt.size(); j++) {
				Details[j] = driver.findElement(By.cssSelector("div[id='panel-file-details-grid'] table tbody "+row2+" td+td")).getText();

				log.logLine(Testname, false, "Details of the Content Detail is "+Details[j]);

				row2 = row2 + "+tr";

			}

		}

		if (doesElementExist2(properties.getProperty("Batches"), 5)) {
			WebElement cntdtl = driver.findElement(By.cssSelector(properties.getProperty("Batches")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", cntdtl);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on batches button of content detail under Recent Activity");
		} else {
			log.logLine(Testname, true, "Clicking on batches button of content detail under Recent Activity is failed");
			throw new Exception("Clicking on batches button of content detail under Recent Activity is failed");
		}

		if (doesElementExist2(properties.getProperty("BatchCol1"), 5)) {
			String col1 = driver.findElement(By.cssSelector(properties.getProperty("BatchCol1"))).getText();
			log.logLine(Testname, false, "Reading the first column of the batch table as-----"+col1);
		}else{
			log.logLine(Testname, true, "Reading the first column of the batch table is unSuccessful");

		}

		if (doesElementExist2(properties.getProperty("BatchCol2"), 5)) {
			String col2 = driver.findElement(By.cssSelector(properties.getProperty("BatchCol2"))).getText();
			log.logLine(Testname, false, "Reading the second column of the batch table as-----"+col2);
		}else{
			log.logLine(Testname, true, "Reading the second column of the batch table is unSuccessful");

		}

		if (doesElementExist2(properties.getProperty("BatchCol3"), 5)) {
			String col3 = driver.findElement(By.cssSelector(properties.getProperty("BatchCol3"))).getText();
			log.logLine(Testname, false, "Reading the third column of the batch table as-----"+col3);
		}else{
			log.logLine(Testname, true, "Reading the third column of the batch table is unSuccessful");

		}

		if (doesElementExist2(properties.getProperty("BatchCol4"), 5)) {
			String col4 = driver.findElement(By.cssSelector(properties.getProperty("BatchCol4"))).getText();
			log.logLine(Testname, false, "Reading the fourth column of the batch table as-----"+col4);
		}else{
			log.logLine(Testname, true, "Reading the fourth column of the batch table is unSuccessful");

		}

		if (doesElementExist2(properties.getProperty("BatchCol5"), 5)) {
			String col5 = driver.findElement(By.cssSelector(properties.getProperty("BatchCol5"))).getText();
			log.logLine(Testname, false, "Reading the fourth column of the batch table as-----"+col5);
		}else{
			log.logLine(Testname, true, "Reading the fourth column of the batch table is unSuccessful");

		}

		if (doesElementExist2(properties.getProperty("Components"), 5)) {
			WebElement cntdtl = driver.findElement(By.cssSelector(properties.getProperty("Components")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", cntdtl);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on Components button of content detail under Recent Activity");
		} else {
			log.logLine(Testname, true, "Clicking on Components button of content detail under Recent Activity is failed");
			throw new Exception("Clicking on Components button of content detail under Recent Activity is failed");
		}

		if (doesElementExist2(properties.getProperty("CompoCol1"), 5)) {
			String col1 = driver.findElement(By.cssSelector(properties.getProperty("CompoCol1"))).getText();
			log.logLine(Testname, false, "Reading the first column of the component table as-----"+col1);
		}else{
			log.logLine(Testname, true, "Reading the first column of the component table is unSuccessful");

		}

		if (doesElementExist2(properties.getProperty("CompoCol2"), 5)) {
			String col2 = driver.findElement(By.cssSelector(properties.getProperty("CompoCol2"))).getText();
			log.logLine(Testname, false, "Reading the second column of the component table as-----"+col2);
		}else{
			log.logLine(Testname, true, "Reading the second column of the component table is unSuccessful");

		}

		if (doesElementExist2(properties.getProperty("CompoCol3"), 5)) {
			String col3 = driver.findElement(By.cssSelector(properties.getProperty("CompoCol3"))).getText();
			log.logLine(Testname, false, "Reading the third column of the component table as-----"+col3);
		}else{
			log.logLine(Testname, true, "Reading the third column of the component table is unSuccessful");

		}



		return true;
	}


	public boolean Quick_Search(String Testname, String SrchType, String testdata1) throws Exception {

		if (doesElementExist2(properties.getProperty("Searchbtn"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on Search button");
		} else {
			log.logLine(Testname, true, "Clicking on Search is failed");
			throw new Exception("Clicking on Search is failed");
		}


		if (doesElementExist2(properties.getProperty("SrchType"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("SrchType")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on SrchType dropdown..");

			if (doesElementExist2(properties.getProperty("TypeQuick"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("TypeQuick")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Quick (Order)")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(6000);
						log.logLine(Testname, false, "Selecting the Search Type Name from the dropdown..");							
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
						Thread.sleep(5000);
						log.logLine(Testname, false, "Selecting the Date Type Name from the dropdown..");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Date Type options are not displayed");
				throw new Exception("Date Type options are not displayed");
			}
		}

		if (doesElementExist2(properties.getProperty("Basicsrchvalue1"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Basicsrchvalue1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Basic Search Type dropdown..");

			if (doesElementExist2(properties.getProperty("BasicsrchtypeOptn"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("BasicsrchtypeOptn")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("RRD Plant")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(5000);
						log.logLine(Testname, false, "Selecting the Basic Search option from the dropdown..");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Basic Search options are not displayed");
				throw new Exception("Basic Search options are not displayed");
			}
		}




		if (doesElementExist2(properties.getProperty("BasicsrchValue"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("BasicsrchValue")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			log.logLine(Testname, false, "Clicking on Basic Search Type dropdown..");

			if (doesElementExist2(properties.getProperty("BasicsrchValueOptn"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("BasicsrchValueOptn")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("All")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(5000);
						log.logLine(Testname, false, "Selecting the Basic Search option from the dropdown..");							
						break;
					}				
				}

			} else {
				log.logLine(Testname, true, "Basic Search options are not displayed");
				throw new Exception("Basic Search options are not displayed");
			}
		}
		switch (SrchType) {

		case "StatusType1":
			if (doesElementExist2(properties.getProperty("Status"), 5)) {	   
				WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Status")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
				Thread.sleep(4000);
				log.logLine(Testname, false, "Clicking on Status dropdown..");

				if (doesElementExist2(properties.getProperty("StatusType"), 5)) {
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("StatusType")));
					for (WebElement lnk:selopts) {
						if (lnk.getText().equals(testdata1)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(5000);
							log.logLine(Testname, false, "Selecting the Status Type Name from the dropdown as .."+testdata1);							
							break;
						}				
					}

				} else {
					log.logLine(Testname, true, "Status Type options are not displayed");
					throw new Exception("Status Type options are not displayed");
				}

				if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
					WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
					Thread.sleep(1000);
					PleasewaitDisappear();
					log.logLine(Testname, false, "Clicking on search button");
				} else {
					log.logLine(Testname, true, "Clicking on search button is failed");
					throw new Exception("Clicking on search button is failed");
				}


				if (doesElementExist2(properties.getProperty("Records"), 5)) {
					log.logLine(Testname, false, "Successfully displayed the records for entered text in srchType field");		    		    	
				} else {
					log.logLine(Testname, false, "No records displayed for entered text in srchType field");
				}

				String[] ProcessType = new String[20];
				String[] ProcessDate = new String[20];

				if(doesElementExist2(properties.getProperty("PrintStatus"), 5)){

					WebElement status= driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody tr td+td+td[role='gridcell']"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", status);
					log.logLine(Testname, false, "Clicking is pass");
				}else {
					log.logLine(Testname, true, "Clicking is fail");
					throw new Exception("Clicking is fail");
				}


				String row1 = "tr";
				String row2 = "tr";
				List<WebElement> DataCnt1= driver.findElements(By.xpath(".//*[@id='panel-record-activity-status-grid']/table/tbody/tr"));

				Thread.sleep(2000);
				if(doesElementExist2(properties.getProperty("Type"), 5)){
					for(int j = 0; j < DataCnt1.size(); j++) {
						ProcessType[j] = driver.findElement(By.cssSelector("div[id='panel-record-activity-status-grid'] table tbody "+row1+" td")).getText();

						if(ProcessType[j].equals("PowerStream Processed") || ProcessType[j].equals("Inserted")){

							log.logLine(Testname, false, "Iterating through the Rows....Reading the Type in Activity/Status as "+ProcessType[j]);
						}
						row1 = row1 + "+tr";

					}

				}

				Thread.sleep(2000);
				if(doesElementExist2(properties.getProperty("DateTime"), 5)){
					for(int k = 0; k < DataCnt1.size(); k++) {
						ProcessDate[k] = driver.findElement(By.cssSelector("div[id='panel-record-activity-status-grid'] table tbody "+row2+" td+td")).getText();
						log.logLine(Testname, false, "Iterating through the Rows....Reading the Date & Time in Activity/Status as "+ProcessDate[k]);

						row2 = row2 + "+tr";

					}

				}

			}            	
			break;

		case "StatusType2":
			if (doesElementExist2(properties.getProperty("Status"), 5)) {	   
				WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("Status")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
				Thread.sleep(4000);
				log.logLine(Testname, false, "Clicking on Status dropdown..");

				if (doesElementExist2(properties.getProperty("StatusType"), 5)) {
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("StatusType")));
					for (WebElement lnk:selopts) {
						if (lnk.getText().equals(testdata1)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(4000);
							log.logLine(Testname, false, "Selecting the Status Type Name from the dropdown as.."+testdata1);							
							break;
						}				
					}

				} else {
					log.logLine(Testname, true, "Status Type options are not displayed");
					throw new Exception("Status Type options are not displayed");
				}

				if (doesElementExist2(properties.getProperty("Searchbutton"), 5)) {
					WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Searchbutton")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
					Thread.sleep(1000);
					PleasewaitDisappear();
					log.logLine(Testname, false, "Clicking on Search button");
				} else {
					log.logLine(Testname, true, "Clicking on Search button is failed");
					throw new Exception("Clicking on Search button is failed");
				}


				if (doesElementExist2(properties.getProperty("Records"), 5)) {
					log.logLine(Testname, false, "Quick search - Successfully displayed the records for entered text in srchType field");		    		    	
				} else {
					log.logLine(Testname, false, "Quick search - No records displayed for entered text in srchType field");
				}


				if(doesElementExist2(properties.getProperty("PrintStatus"), 5)){

					WebElement status= driver.findElement(By.cssSelector("div[class='k-grid-content'] table tbody tr td+td+td[role='gridcell']"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", status);
					log.logLine(Testname, false, "Clicking is pass");
				}else {
					log.logLine(Testname, true, "Clicking is fail");
					throw new Exception("Clicking is fail");
				}

				String[] ShipType = new String[20];
				String[] ShipDate = new String[20];

				String row1 = "tr";
				String row2 = "tr";
				List<WebElement> DataCnt1= driver.findElements(By.xpath(".//*[@id='panel-record-activity-status-grid']/table/tbody/tr"));

				Thread.sleep(2000);
				if(doesElementExist2(properties.getProperty("Type"), 5)){
					for(int j = 0; j < DataCnt1.size(); j++) {
						ShipType[j] = driver.findElement(By.cssSelector("div[id='panel-record-activity-status-grid'] table tbody "+row1+" td")).getText();

						if(ShipType[j].equals("PowerStream Processed") || ShipType[j].equals("Inserted") || ShipType[j].equals("Imaged") || ShipType[j].equals("Shipped/Mailed")){

							log.logLine(Testname, false, "Iterating through the Rows....Reading the Type in Activity/Status as "+ShipType[j]);
						}
						row1 = row1 + "+tr";

					}

				}

				Thread.sleep(2000);
				if(doesElementExist2(properties.getProperty("DateTime"), 5)){
					for(int k = 0; k < DataCnt1.size(); k++) {
						ShipDate[k] = driver.findElement(By.cssSelector("div[id='panel-record-activity-status-grid'] table tbody "+row2+" td+td")).getText();
						log.logLine(Testname, false, "Iterating through the Rows....Reading the the Date & Time in Activity/Status as "+ShipDate[k]);

						row2 = row2 + "+tr";

					}

				}

			}            	
			break;

		}

		return true;
	}

}




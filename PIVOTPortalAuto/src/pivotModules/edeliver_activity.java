package pivotModules;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import launchAuto.Initialization;
import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;

public class edeliver_activity extends Page {

	int paperID = (int) Math.round(Math.random() * (9999 - 1000 + 1) + 1000);
	public String AccNo = Integer.toString(paperID);


	public edeliver_activity(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}	
	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}

	String firstWinHandle = null;
	WebDriverWait wait = new WebDriverWait(driver, 20);
	String value = " ";
	int Total=0;
	String data=" ";

	public boolean Advancesearch_activity(String RandNo, String Testname) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		waitForElement(properties.getProperty("selClint1"));

		ClickeDeliverTab(Testname);

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
				//    throw new Exception("Client Name options are not displayed");
			}

		} else {
			log.logLine(Testname, true, "Client Name dropdown element does not exist");
			//    throw new Exception("Client Name dropdown element does not exist");
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
					//    throw new Exception("Client Name options are not displayed");
				}

			} else {
				log.logLine(Testname, true, "Client Name dropdown element does not exist");
				//    throw new Exception("Client Name dropdown element does not exist");
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
				//    throw new Exception("Application Name options are not displayed");
			}

		} else {
			log.logLine(Testname, true, "Application Name dropdown element does not exist");
			//    throw new Exception("Application Name dropdown element does not exist");
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

							log.logLine(Testname, false, "Selecting the Application Name "+AppName +" from the dropdown..");							
							break;
						}				
					}

				} else {
					log.logLine(Testname, true, "Application Name options are not displayed");
					//    throw new Exception("Application Name options are not displayed");
				}

			} else {
				log.logLine(Testname, true, "Application Name dropdown element does not exist");
				//    throw new Exception("Application Name dropdown element does not exist");
			}	    	
		}

		PleasewaitDisappear();


		//Click on Ok button
		if (doesElementExist2(properties.getProperty("Okbtn"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Okbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on OK button");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the Reports is failed");
			//    throw new Exception("Clicking on OK button to view the Reports is failed");
		}

		Thread.sleep(3000);

		Actions builder = new Actions(driver);	        
		WebElement mnuElement = driver.findElement(By.xpath(properties.getProperty("Activity")));	
		if (doesElementExist(properties.getProperty("Activity"), 10)) {			  
			// Move cursor to the Main Menu Element  
			builder.moveToElement(mnuElement).perform();		
			// Clicking on the Hidden SubMenu  
			WebElement mail = driver.findElement(By.xpath(properties.getProperty("email")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",mail);		
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicking on 'Activity_Email' is successful..");		      
		} else {
			log.logLine(Testname, true, "Clicking on 'Activity_Email' is failed");
			//throw new Exception("Clicking on 'Activity_Email' is failed");			
		}
		Thread.sleep(2000);
		driver.switchTo().frame("iframeContainer");

		Email_activitytablevalidation(Testname);
		EMailtyperemoval(Testname);
		Email_Advancesearch(Testname);
		Emailremoval_validation(Testname);
		Thread.sleep(1000);
		Email_activitytablevalidation(Testname);
		Advancesearch(Testname);
		Thread.sleep(3000);
		Advancesearch_clear(Testname);
		Thread.sleep(2000);
		log.logLine(Testname, false, "we are re-checking the unchecked 'email type now");
		EMailtyperemoval(Testname);
		Thread.sleep(2000);
		Email_Advancesearch(Testname);
		Thread.sleep(2000);
		Email_typeAdmincomparison(Testname);

		return true;
	}
	//***********************************EmailType validation*************************************************

	public boolean EMailtyperemoval(String Testname) throws Exception {

		Advancesearch(Testname);
		Thread.sleep(3000);

		if (doesElementExist(properties.getProperty("srchcrie"), 5)) {
			WebElement srchcri= driver.findElement(By.xpath(properties.getProperty("srchcrie")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchcri);
			log.logLine(Testname, false, "Clicking on search crieteria");
			Thread.sleep(2000);
			List<WebElement> selopts = driver.findElements(By.xpath(properties.getProperty("srchcrilst")));
			for (WebElement lnk:selopts) {
				if (lnk.getText().equalsIgnoreCase("Email Type")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(5000);
					log.logLine(Testname, false, "Selecting 'Email type' from list");						
					break;
				}				
			}

		} else {
			log.logLine(Testname, true, "Unable to Click on search crieteria");	
		}		

		Thread.sleep(4000);

		if (doesElementExist(properties.getProperty("emailtypetxtbx"), 5)) {
			WebElement txt = driver.findElement(By.xpath(properties.getProperty("emailtypetxtbx")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", txt);
			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("emailtyplst")));
			for (WebElement lnk:selopts) {
				if (lnk.getAttribute("name").equalsIgnoreCase("Document Notification")) {				
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(4000);
					log.logLine(Testname, false, "Unchecking 'Document Notification' from campaigntype list is successful");						
					break;
				}	}
		} else {
			log.logLine(Testname, true, "emailtypetxtbx doesnot exist");
			throw new Exception("email type txt bx does not exist");
		}


		if ((doesElementExist2(properties.getProperty("searchbtn"), 5))) { 
			WebElement src= driver.findElement(By.cssSelector(properties.getProperty("searchbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", src);
			log.logLine(Testname, false, "Clicking on  search button");
		} else {
			log.logLine(Testname, true, "Clicking on  search button failed");
			throw new Exception("Clicking on  search button failed");
		}

		Thread.sleep(5000);

		return true;
	}



	public boolean Emailremoval_validation(String Testname) throws Exception {

		Advancesearch(Testname);
		Thread.sleep(3000);

		if (doesElementExist(properties.getProperty("srchcrie"), 5)) {
			WebElement srchcri= driver.findElement(By.xpath(properties.getProperty("srchcrie")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchcri);
			log.logLine(Testname, false, "Clicking on search crieteria");
			List<WebElement> selopts = driver.findElements(By.xpath(properties.getProperty("srchcrilst")));
			for (WebElement lnk:selopts) {
				if (lnk.getText().equalsIgnoreCase("Email Type")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Selecting 'Email type' from list");						
					break;
				}				
			}

		} else {
			log.logLine(Testname, true, "Unable to Click on search crieteria");	
		}		

		Thread.sleep(1000);

		if (doesElementExist(properties.getProperty("emailtypetxtbx"), 5)) {
			WebElement txt = driver.findElement(By.xpath(properties.getProperty("emailtypetxtbx")));
			value=txt.getText();
			String[] abc=new String[50];
			abc=value.split(",");	
			log.logLine(Testname, false, "We are now validating,Document Notification existance");
			for(int i=0;i<abc.length;i++){
				if(abc[i].equals("Document Notification")){
					data="false";			
					log.logLine(Testname, true, "Document Notification still exists");	
					throw new Exception("Document Notification still exists");
					
				}
			}
						
			if(!data.equals("false")){
				log.logLine(Testname, false, "Document Notification doesnot exist");
			}
		} else {
			log.logLine(Testname, true, "Email type text box exist");
			throw new Exception("email type txt bx does not exist");
		}
	

		if ((doesElementExist2(properties.getProperty("cancel"), 5))) { 
			WebElement can = driver.findElement(By.cssSelector(properties.getProperty("cancel")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", can);
			log.logLine(Testname, false, "Clicking on cancel button");
		} else {
			log.logLine(Testname, true, "Clicking on cancel button failed");
			throw new Exception("Clicking on cancel button failed");
		}

		return true;
	}


	public boolean Advancesearch_clear(String Testname) throws Exception {
		if ((doesElementExist2(properties.getProperty("cancel"), 5))) { 
			WebElement can = driver.findElement(By.cssSelector(properties.getProperty("cancel")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", can);
			log.logLine(Testname, false, "Clicking on cancel button");
		} else {
			log.logLine(Testname, true, "Clicking on cancel button failed");
			throw new Exception("Clicking on cancel button failed");
		}
		return true;}

	public boolean Email_Advancesearch(String Testname) throws Exception {


		Advancesearch(Testname);
		Thread.sleep(3000);

		if (doesElementExist(properties.getProperty("srchcrie"), 5)) {
			WebElement srchcri= driver.findElement(By.xpath(properties.getProperty("srchcrie")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchcri);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on search crieteria");
			List<WebElement> selopts = driver.findElements(By.xpath(properties.getProperty("srchcrilst")));
			for (WebElement lnk:selopts) {
				if (lnk.getText().equalsIgnoreCase("Email Type")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(5000);
					log.logLine(Testname, false, "Selecting 'Email type' from list");						
					break;
				}				
			}

		} else {
			log.logLine(Testname, true, "Unable to Click on search crieteria");	
		}		

		Thread.sleep(1000);

		if (doesElementExist(properties.getProperty("emailtypetxtbx"), 5)) {
			WebElement txt = driver.findElement(By.xpath(properties.getProperty("emailtypetxtbx")));
			value=txt.getText();
			String[] abc=new String[50];
			abc=value.split(",");
			Total=(abc.length)-1;
			log.logLine(Testname, false, "Total no. of email types in the textbox "+ Total);
			log.logLine(Testname, false, "Value in the edeliver-activity email type is "+ value);
		} else {
			log.logLine(Testname, true, "emailtypetxtbx doesnot exist");
			throw new Exception("email type txt bx does not exist");
		}

		Thread.sleep(1000);

		if ((doesElementExist2(properties.getProperty("cancel"), 5))) { 
			WebElement can = driver.findElement(By.cssSelector(properties.getProperty("cancel")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", can);
			log.logLine(Testname, false, "Clicking on cancel button");
		} else {
			log.logLine(Testname, true, "Clicking on cancel button failed");
			throw new Exception("Clicking on cancel button failed");
		}
		return true;
	}

	public void Advancesearch(String Testname) throws Exception {

		if ((doesElementExist2(properties.getProperty("advansrc"), 5))) { 
			WebElement adv = driver.findElement(By.cssSelector(properties.getProperty("advansrc")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", adv);
			log.logLine(Testname, false, "Clicking on advance search button");
		} else {
			log.logLine(Testname, true, "Clicking on advance search button failed");
			throw new Exception("Clicking on advance search button failed");
		}
	}

	//*******************************************************************************************************
	public boolean Email_typeAdmincomparison(String Testname) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(1000);
		driver.switchTo().defaultContent();


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

		Thread.sleep(25000);
		Set<String> handles = driver.getWindowHandles();
		firstWinHandle = driver.getWindowHandle(); 
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
			}
		} 

		Thread.sleep(20000);
		if (doesElementExist2(properties.getProperty("EmailLink"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("EmailLink")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			List<WebElement> Lcn = driver.findElements(By.cssSelector(properties.getProperty("EmailLink")));
			for (WebElement btn:Lcn) {
				if (btn.getText().equals("EMail")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					Thread.sleep(5000);
					log.logLine(Testname, false, "Clicking on Email link from the leftContent Menu");
					break;
				}
			}

		} else {
			log.logLine(Testname, true, "Unable to click on the Email link from the leftContent Menu");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Unable to click on the Email link from the leftContent Menu");
		}

		if (doesElementExist2(properties.getProperty("EmailBuilderLinkT"), 5)) {
			WebElement emlblder = driver.findElement(By.cssSelector(properties.getProperty("EmailBuilderLinkT")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", emlblder);
			Thread.sleep(5000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on EmailBuilder option from the Email menu list ");

		} else if (doesElementExist2(properties.getProperty("EmailBuilderLinkS"), 5)) {  
			WebElement emlblder = driver.findElement(By.cssSelector(properties.getProperty("EmailBuilderLinkS")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", emlblder);
			Thread.sleep(5000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on EmailBuilder option from the Email menu list ");

		} else if (doesElementExist2(properties.getProperty("EmailBuilderLinkP"), 5)) {  
			WebElement emlblder = driver.findElement(By.cssSelector(properties.getProperty("EmailBuilderLinkP")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", emlblder);
			Thread.sleep(5000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on EmailBuilder option from the Email menu list ");

		}else {
			log.logLine(Testname, true, "Unable to Click on EmailBuilder option from the Email menu list ");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Unable to Click on EmailBuilder option from the Email menu list ");
		}

		if (doesElementExist2(properties.getProperty("EmailTypes"), 5)) {	    
			WebElement emlty = driver.findElement(By.cssSelector(properties.getProperty("EmailTypes")));

			log.logLine(Testname, false, "Selecting the Email Types option of the Email Builder list");

			List<WebElement> lsteml = driver.findElements(By.cssSelector(properties.getProperty("EmailTypes")));
			for (WebElement btn:lsteml) {
				if (btn.getText().equalsIgnoreCase("Email Types")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					Thread.sleep(5000);
					log.logLine(Testname, false, "Clicking on Email Types option of the Email Builder list");
					break;
				}
			}
		}else {
			log.logLine(Testname, true, "Unable to Click on Email Types option of the Email Builder list");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Unable to Click on Email Types option of the Email Builder list");
		}

		Thread.sleep(15000);
		if (doesElementExist2(properties.getProperty("SelectApp"), 5)) {
			Select emltype = new Select(driver.findElement(By.cssSelector(properties.getProperty("SelectApp"))));
			emltype.selectByVisibleText("RGT1099 - RGT1099");
			Thread.sleep(15000);
			log.logLine(Testname, false, "Selecting the RGT1099 application in Stage from the Email Type Admin list in Template Management");
		}else {
			log.logLine(Testname, true, "Selecting the RGT1099 application from the Email Type Admin list in Template Management is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Selecting the ABC application from the Email Type Admin list in Template Management is failed");
		}

		Thread.sleep(3000);
		// Extracting the email types from the list and comparing it with the "email types" available in edeliver-advance search

		String[] EmailTemp = new String[100];
		String row1 = "tr";
		List<WebElement> DataCnt1= driver.findElements(By.xpath(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_dgViewEmailTypes']/tbody/tr"));
		Thread.sleep(2000);
		log.logLine(Testname, false, "Total no. of email types in admin are  "+ DataCnt1.size());
		if(doesElementExist2(properties.getProperty("EmailTypeHeader"), 5)){
			for(int j = 0; j < DataCnt1.size(); j++) {			
				EmailTemp[j] = driver.findElement(By.cssSelector("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_dgViewEmailTypes'] tbody "+row1+" td")).getText();
				if(value.contains(EmailTemp[j])){
					log.logLine(Testname, false, "Iterating through the Rows of the Email Type list   "+EmailTemp[j]+"   is present in edeliver_activity email typelist");			
				}
				row1 = row1 + "+tr";
			}

		}
		driver.close();
		driver.switchTo().window(firstWinHandle);
		return true;}


	//********************Edelivery tab********************************************************************************

	private void Email_activitytablevalidation(String Testname) throws Exception {


		String[] Sort1 = new String[15];
		String[] Sort2= new String[15];
		String[] Sort = new String[15];
		String  row = "tr";
		List<WebElement> rowCnt=driver.findElements(By.xpath(".//*[@id='BatchesGrid']/table/tbody/tr"));
		if (rowCnt.size()==0){
			log.logLine(Testname, false, "Email activity table is empty");
		}
		else{
			if(doesElementExist2(properties.getProperty("acc"), 5)){
				List<WebElement> DataCnt1=driver.findElements(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/thead/tr/th"));
				List<WebElement> columncount=driver.findElements(By.xpath(".//*[@id='BatchesGrid']/table/tbody/tr[1]/td"));			
				for(int j=2;j<= columncount.size();j++){
					Sort1[j]=driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/table/thead/tr/th["+j+"]")).getText();
					log.logLine(Testname, false, "We are reading the data of the month "+Sort1[j]);
					for(int i = 1; i <= rowCnt.size(); i++) {															
						Sort[i] = driver.findElement(By.xpath(".//*[@id='BatchesGrid']/table/tbody/tr["+i+"]/td["+j+"]")).getText();
						int m=1;
						Sort2[i] = driver.findElement(By.xpath(".//*[@id='BatchesGrid']/table/tbody/tr["+i+"]/td["+m+"]")).getText();					
						log.logLine(Testname, false, "The data in the  "+Sort2[i]+" of month "+Sort1[j]+" is "+Sort[i]);	
					}
				}
			}}}


	public void ClickeDeliverTab(String Testname) throws Exception {
		if (doesElementExist2(properties.getProperty("EdeliveryTab"), 5)) {
			WebElement eDelive = driver.findElement(By.cssSelector(properties.getProperty("EdeliveryTab")));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",eDelive);
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
			log.logLine(Testname, false, "Click on e-Delivery Tab is Successful");
		} else {
			log.logLine(Testname, true, "Click on e-Delivery Tab is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on e-Delivery Tab is failed");
		}
	}

}
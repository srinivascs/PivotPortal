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

public class TemplateMgmtHTML extends Page {

	int paperID = (int) Math.round(Math.random() * (9999 - 1000 + 1) + 1000);
	public String AccNo = Integer.toString(paperID);

	public String EmailtypTxt, InsImgUrl, AltImageUrlText, name,spacialChar;
	public String TemplateName;
	public String TempltDecsp;
	public String EditTemplateName;
	public String EditTempltDecsp;
	public String notemplts;
	public String URL, LinkID , LnkText ,LnkToolTp;

	public TemplateMgmtHTML(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}	
	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}

	String firstWinHandle = null;
	WebDriverWait wait = new WebDriverWait(driver, 20);
	public boolean TemplateText(String RandNo, String Testname) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		waitForElement(properties.getProperty("selClint1"));

		ClickeDeliverTab(Testname);


		/*if (doesElementExist2(properties.getProperty("TemplateMgmnt"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("TemplateMgmnt")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);	    	 
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Click on Template management Module is Successful");
		} else {
			log.logLine(Testname, true, "Click on Template management Module is failed");
			driver.switchTo().defaultContent();
			//    throw new Exception("Click on Template management Module is failed");
		}*/

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


		Actions builder = new Actions(driver);	        
		WebElement mnuElement = driver.findElement(By.xpath(properties.getProperty("TemplateManagement")));	
		if (doesElementExist(properties.getProperty("TemplateManagement"), 10)) {			  
			// Move cursor to the Main Menu Element  
			builder.moveToElement(mnuElement).perform();		
			// Clicking on the Hidden SubMenu  
			WebElement oldpivt = driver.findElement(By.xpath(properties.getProperty("EmailTemplate")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", oldpivt);		
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicking on Email Templates is successful..");		      
		} else {
			log.logLine(Testname, true, "Clicking on Email Templates is failed");
			throw new Exception("Clicking on Email Templates is failed");			
		}

		if ((doesElementExist2(properties.getProperty("Adminlnk"), 5))) {                
			WebElement oldpivt = driver.findElement(By.cssSelector(properties.getProperty("Adminlnk")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", oldpivt);
			log.logLine(Testname, false, "Clicking on Legacy Admin..");              
		}


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
				Thread.sleep(3000);
				driver.manage().window().maximize();


				if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetExplorer"))) {
					if (doesElementExist2(properties.getProperty("OverrideLnk"), 5)) {
						driver.get("javascript:document.getElementById('overridelink').click();");
						Thread.sleep(8000);
					}
				}
			}
		} 

		Thread.sleep(15000);


		//ImageAdmin(Testname );

		Thread.sleep(15000);
		if (doesElementExist2(properties.getProperty("EmailLink"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("EmailLink")));

			log.logLine(Testname, false, "Clicking on Email link from the leftContent Menu");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);

			List<WebElement> Lcn = driver.findElements(By.cssSelector(properties.getProperty("EmailLink")));
			for (WebElement btn:Lcn) {
				if (btn.getText().equals("EMail")) {
					Thread.sleep(2000);
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Clicking on Email link from the leftContent Menu");
					break;
				}
			}

		} else {
			log.logLine(Testname, true, "Unable to click on the Email link from the leftContent Menu");
			//    throw new Exception("Unable to click on the Email link from the leftContent Menu");
		}
		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("EmailBuilderLinkT"), 5)) {
			WebElement emlblder = driver.findElement(By.cssSelector(properties.getProperty("EmailBuilderLinkT")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", emlblder);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on EmailBuilder option from the Email menu list ");

			Thread.sleep(2000);

		} else if (doesElementExist2(properties.getProperty("EmailBuilderLinkS"), 5)) {  
			WebElement emlblder = driver.findElement(By.cssSelector(properties.getProperty("EmailBuilderLinkS")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", emlblder);
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on EmailBuilder option from the Email menu list ");

		} else if (doesElementExist2(properties.getProperty("EmailBuilderLinkP"), 5)) {  
			WebElement emlblder = driver.findElement(By.cssSelector(properties.getProperty("EmailBuilderLinkP")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", emlblder);
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on EmailBuilder option from the Email menu list ");   	

		}else {
			log.logLine(Testname, true, "Unable to Click on EmailBuilder option from the Email menu list ");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Unable to Click on EmailBuilder option from the Email menu list ");
		}


		if (doesElementExist2(properties.getProperty("EmailTypes"), 5)) {	    
			driver.findElement(By.cssSelector(properties.getProperty("EmailTypes")));
			log.logLine(Testname, false, "Selecting the Email Types option of the Email Builder list");
			List<WebElement> lsteml = driver.findElements(By.cssSelector(properties.getProperty("EmailTypes")));

			for (WebElement btn:lsteml) {
				if (btn.getText().equalsIgnoreCase("Email Types")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Clicking on Email Types option of the Email Builder list");
					break;
				}
			}
		}else 	{
			log.logLine(Testname, true, "Unable to Click on Email Types option of the Email Builder list");
			//    throw new Exception("Unable to Click on Email Types option of the Email Builder list");
		}


		SelectAppEmailTypeCreation(Testname , TemplateName);


		waitForElement(properties.getProperty("AddEmlType"));
		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("AddEmlType"), 5)) {
			WebElement addEmailTyp = driver.findElement(By.cssSelector(properties.getProperty("AddEmlType")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", addEmailTyp);

			log.logLine(Testname, false, "Clicking on OK button");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the Reports is failed");
			//throw new Exception("Clicking on OK button to view the Reports is failed");
		}



		EmailtypTxt=test.readColumnData("EmailType", sheetname);
		String DescpText=test.readColumnData("Description", sheetname);
		String FromTxt=test.readColumnData("From", sheetname);
		String FromFrndlyTxt=test.readColumnData("FromFreindlyName", sheetname);
		String rplyTxt=test.readColumnData("ReplyTo", sheetname);
		String rplyToFrndlyTxt=test.readColumnData("ReplyToFrndlyName", sheetname);

		String subTxt=test.readColumnData("Subject", sheetname);
		String qtyDay=test.readColumnData("QtyPerDay", sheetname);
		String qtyWeek=test.readColumnData("QtyPerWeek", sheetname);
		String qtyMonth=test.readColumnData("QtyPerMonth", sheetname);

		String qtyYear=test.readColumnData("QtyPerYear", sheetname);





		waitForElement(properties.getProperty("EmlTypeTxtBox"));

		if (doesElementExist2(properties.getProperty("EmlTypeTxtBox"), 5)) {
			WebElement emltyptxt = driver.findElement(By.cssSelector(properties.getProperty("EmlTypeTxtBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", emltyptxt);
			//emltyptxt.click();
			Thread.sleep(6000);
			emltyptxt.sendKeys(EmailtypTxt.concat(AccNo));
			Thread.sleep(4000);
			log.logLine(Testname, false, "Entering Email Type name as "+EmailtypTxt.concat(AccNo)+" in the text box of Email Builder Section");
		} else {
			log.logLine(Testname, true, "Unable to enter Email Type name as "+EmailtypTxt.concat(AccNo)+" in the text box of Email Builder Section");
			//throw new Exception("Unable to enter Email Type name as "+EmailtypTxt.concat(AccNo)+" in the text box of Email Builder Section");
		}

		if (doesElementExist2(properties.getProperty("DescriptionTxtBox"), 5)) {
			WebElement descpText = driver.findElement(By.cssSelector(properties.getProperty("DescriptionTxtBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", descpText);
			Thread.sleep(2000);
			descpText.sendKeys(DescpText);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Entering the description in the \"Description\" text box of Email Builder Section ");
		} else {
			log.logLine(Testname, true, "Unable to Enter the description in the \"Description\" text box of Email Builder Section ");
			//throw new Exception("Unable to Enter the description in the \"Description\" text box of Email Builder Section ");
		}

		if (doesElementExist2(properties.getProperty("FromTxtBox"), 5)) {
			WebElement frmTxt = driver.findElement(By.cssSelector(properties.getProperty("FromTxtBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", frmTxt);
			Thread.sleep(2000);
			if (Initialization.EnvirSite.equalsIgnoreCase("test")) {
				frmTxt.sendKeys("concat@ha.testpivot.rrd.com");
			} else if (Initialization.EnvirSite.equalsIgnoreCase("stage")) {
				frmTxt.sendKeys("concat@ha.stagepivot.rrd.com");
				Thread.sleep(4000);
			} else if (Initialization.EnvirSite.equalsIgnoreCase("prod")){
				frmTxt.sendKeys("concat@ha.pivot.rrd.com");
			}

			Thread.sleep(1000);
			log.logLine(Testname, false, "Entering the from email addresss in the \"From\" text box of Email Builder Section ");
		} else {
			log.logLine(Testname, true, "Unable to Enter the from email addresss in the \"From\" text box of Email Builder Section ");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Unable to Enter the from email addresss in the \"From\" text box of Email Builder Section ");
		}

		if (doesElementExist2(properties.getProperty("FromFrndlyNmeTxtBox"), 5)) {
			WebElement frmFrndlyText = driver.findElement(By.cssSelector(properties.getProperty("FromFrndlyNmeTxtBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", frmFrndlyText);
			Thread.sleep(4000);
			frmFrndlyText.sendKeys(FromFrndlyTxt);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Entering the name in the \"From Friendly Name\" text box of Email Builder Section ");
		} else {
			log.logLine(Testname, true, "Unable to Enter the name in the \"From Friendly Name\" text box of Email Builder Section ");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Unable to Enter the name in the \"From Friendly Name\" text box of Email Builder Section ");
		}

		if (doesElementExist2(properties.getProperty("ReplyToTxtBox"), 5)) {
			WebElement rpltoTx = driver.findElement(By.cssSelector(properties.getProperty("ReplyToTxtBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rpltoTx);
			Thread.sleep(4000);
			rpltoTx.sendKeys(rplyTxt);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Entering the email address in the \"Reply To\" text box of Email Builder Section ");
		} else {
			log.logLine(Testname, true, "Unable to Enter the email address in the \"Reply To\" text box of Email Builder Section ");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Unable to Enter the email address in the \"Reply To\" text box of Email Builder Section ");
		}

		if (doesElementExist2(properties.getProperty("ReplyToFrndlyNmeTxtBox"), 5)) {
			WebElement rpltofrndlyTx = driver.findElement(By.cssSelector(properties.getProperty("ReplyToFrndlyNmeTxtBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rpltofrndlyTx);
			Thread.sleep(4000);
			rpltofrndlyTx.sendKeys(rplyToFrndlyTxt);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Entering the name in the \"Reply to Freindly Name\" text box of Email Builder Section ");
		} else {
			log.logLine(Testname, true, "Unable to Enter the email address in the \"Reply to Freindly Name\" text box of Email Builder Section ");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Unable to Enter the email address in the \"Reply to Freindly Name\" text box of Email Builder Section ");
		}

		if (doesElementExist2(properties.getProperty("SubjectTxtBox"), 5)) {
			WebElement subjTxt = driver.findElement(By.cssSelector(properties.getProperty("SubjectTxtBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", subjTxt);
			Thread.sleep(4000);
			subjTxt.sendKeys(subTxt);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Entering the text in \"Subject\" text box of the Email Builder Section");
		} else {
			log.logLine(Testname, true, "Unable to Enter the text in \"Subject\" text box of the Email Builder Section");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Unable to Enter the text in \"Subject\" text box of the Email Builder Section");
		}


		if (doesElementExist2(properties.getProperty("HTMLEncodingDrpDwn"), 5)) {
			Select htmlEncde = new Select(driver.findElement(By.cssSelector(properties.getProperty("HTMLEncodingDrpDwn"))));
			htmlEncde.selectByVisibleText("quoted-printable");
			log.logLine(Testname, false, "Selecting quotable printed option from the \"HTML Encoding\" dropdown list of Email Builder Section ");
		} else {
			log.logLine(Testname, true, "Unable to Select quotable printed option from the \"HTML Encoding\" dropdown list of Email Builder Section ");
			throw new Exception("Unable to Select quotable printed option from the \"HTML Encoding\" dropdown list of Email Builder Section ");
		}

		if (doesElementExist2(properties.getProperty("TextEncodingDrpDwn"), 5)) {
			Select textEncde = new Select(driver.findElement(By.cssSelector(properties.getProperty("TextEncodingDrpDwn"))));
			textEncde.selectByVisibleText("quoted-printable");
			log.logLine(Testname, false, "Selecting quotable printed option from the \"Text Encoding\" dropdown list of Email Builder Section ");
		} else {
			log.logLine(Testname, true, "Unable to Select quotable printed option from the \"Text Encoding\" dropdown list of Email Builder Section ");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Unable to Select quotable printed option from the \"Text Encoding\" dropdown list of Email Builder Section ");
		}

		if (doesElementExist2(properties.getProperty("SanTpeDrpDwn"), 5)) {
			Select santyp = new Select(driver.findElement(By.cssSelector(properties.getProperty("SanTpeDrpDwn"))));
			santyp.selectByVisibleText("PCI");
			log.logLine(Testname, false, "Selecting PCI option from the \"San Type\" dropdown list of Email Builder Section ");
		} else {
			log.logLine(Testname, true, "Unable to Select PCI option from the \"San Type\" dropdown list of Email Builder Section ");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Unable to Select PCI option from the \"San Type\" dropdown list of Email Builder Section ");
		}

		if (doesElementExist2(properties.getProperty("EmlQty/Day"), 5)) {
			WebElement qtyday = driver.findElement(By.cssSelector(properties.getProperty("EmlQty/Day")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", qtyday);
			qtyday.sendKeys(qtyDay);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entering the value for \"Emails per day\" in Email Sender Section");
		} else {
			log.logLine(Testname, true, "Unable to Enter the value for \"Emails per day\" in Email Sender Section");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Unable to Enter the value for \"Emails per day\" in Email Sender Section");
		}

		if (doesElementExist2(properties.getProperty("EmlQty/Week"), 5)) {
			WebElement qtyweek = driver.findElement(By.cssSelector(properties.getProperty("EmlQty/Week")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", qtyweek);
			qtyweek.sendKeys(qtyWeek);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entering the value for \"Emails per Week\" in Email Sender Section");
		} else {
			log.logLine(Testname, true, "Unable to Enter the value for \"Emails per Week\" in Email Sender Section");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Unable to Enter the value for \"Emails per Week\" in Email Sender Section");
		}

		if (doesElementExist2(properties.getProperty("EmlQty/Month"), 5)) {
			WebElement qtymonth = driver.findElement(By.cssSelector(properties.getProperty("EmlQty/Month")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", qtymonth);
			qtymonth.sendKeys(qtyMonth);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entering the value for \"Emails per Month\" in Email Sender Section");
		} else {
			log.logLine(Testname, true, "Unable to Enter the value for \"Emails per Month\" in Email Sender Section");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Unable to Enter the value for \"Emails per Month\" in Email Sender Section");
		}

		if (doesElementExist2(properties.getProperty("EmlQty/Year"), 5)) {
			WebElement qtyyear = driver.findElement(By.cssSelector(properties.getProperty("EmlQty/Year")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", qtyyear);
			qtyyear.sendKeys(qtyYear);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entering the value for \"Emails per Year\" in Email Sender Section");
		} else {
			log.logLine(Testname, true, "Unable to Enter the value for \"Emails per Year\" in Email Sender Section");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Unable to Enter the value for \"Emails per Year\" in Email Sender Section");
		}

		if (doesElementExist2(properties.getProperty("PriorityDrpDwn"), 5)) {
			Select priority = new Select(driver.findElement(By.cssSelector(properties.getProperty("PriorityDrpDwn"))));
			priority.selectByVisibleText("Batch medium");
			log.logLine(Testname, false, "Selecting \"Batch medium\" option from the \" Priority \" dropdown list of Email Sender Section ");
		} else {
			log.logLine(Testname, true, "Unable to Select \"Batch medium\" option from the \" Priority \" dropdown list of Email Sender Section ");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Unable to Select \"Batch medium\" option from the \" Priority \" dropdown list of Email Sender Section ");
		}

		if (doesElementExist2(properties.getProperty("EmlTypeBindingDrpDwn"), 5)) {
			Select emlBinding = new Select(driver.findElement(By.cssSelector(properties.getProperty("EmlTypeBindingDrpDwn"))));
			emlBinding.selectByVisibleText("BCS-SR2-E24H");
			log.logLine(Testname, false, "Selecting \"BCS-SR2-E24H \" option from the \" Email Template Binding \" dropdown list of Email Sender Section ");
		} else {
			log.logLine(Testname, true, "Unable to Select \"BCS-SR2-E24H \" option from the \" Email Template Binding \" dropdown list of Email Sender Section ");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Unable to Select \"BCS-SR2-E24H \" option from the \" Email Template Binding \" dropdown list of Email Sender Section ");
		}

		if (doesElementExist2(properties.getProperty("AddButton"), 5)) {
			WebElement addbtn = driver.findElement(By.cssSelector(properties.getProperty("AddButton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", addbtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on \"Add\" button for adding the Email type in Template Management");
		} else {
			log.logLine(Testname, true, "Clicking on \"Add\" button for adding the Email type in Template Management is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicking on \"Add\" button for adding the Email type in Template Management is failed");
		}

		String successfulEml=test.readColumnData("SuccessfulEmlTypeText", sheetname);

		if (isTextPresent(successfulEml)) {
			Thread.sleep(2000);
			log.logLine(Testname, false, " The text "+successfulEml+" is present in the page");	
			log.logLine(Testname, false, "Template Management - adding text Email type is successsful");	    
		} else {
			log.logLine(Testname, true, "Template Management - adding text Email type is unsuccesssful");		     
		}

		String[] ProcessType = new String[100];
		String row1 = "tr";
		List<WebElement> DataCnt1= driver.findElements(By.xpath(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_dgViewEmailTypes']/tbody/tr"));

		Thread.sleep(2000);
		if(doesElementExist2(properties.getProperty("EmailTypeHeader"), 5)){
			for(int j = 0; j < DataCnt1.size(); j++) {
				ProcessType[j] = driver.findElement(By.cssSelector("div table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_dgViewEmailTypes'] tbody "+row1+" td")).getText();

				if(ProcessType[j].equals(EmailtypTxt.concat(AccNo))){

					log.logLine(Testname, false, "Iterating through the Rows of the Email Type list....and reading the Type in Email Type  as "+ProcessType[j]);
					break;
				}
				row1 = row1 + "+tr";

			}

		}
		Thread.sleep(5000);
		driver.close();

		driver.switchTo().window(firstWinHandle);

		driver.switchTo().frame("iframeContainer");


		Linkidduplicity(Testname);
		
		//Linkidwithduplicity(Testname);

		/*Thread.sleep(5000);
		ClickPlusButtononHTMLtemplate(Testname);

		Thread.sleep(5000);
		DeleteImages(Testname );

		Thread.sleep(5000);
		Closeandrefresh(Testname );

		Thread.sleep(5000);*/
		SavedTypesDropDown(Testname);

		Thread.sleep(5000);
		ClickPlusButtontoAddHTMLTemplate(Testname);

		TemplateName=test.readColumnData("TemplateName", sheetname);
		TempltDecsp=test.readColumnData("TemplateDecrip", sheetname);

		URL=test.readColumnData("URL", sheetname);
		LinkID=test.readColumnData("LinkID", sheetname);
		LnkText=test.readColumnData("LinkText", sheetname);
		LnkToolTp=test.readColumnData("LinkTltip", sheetname);


		WebElement TempltNamTxtBox = driver.findElement(By.cssSelector(properties.getProperty("TempltNameTxtBox")));

		wait.until(ExpectedConditions.elementToBeClickable(TempltNamTxtBox));		
		FillTemplateName(Testname , TemplateName) ;
		Thread.sleep(5000);

		//*********Check Clear Template and Save******************
		FillTemplateDescription(Testname , TempltDecsp);
		Thread.sleep(5000);
		ClickClearTemplateButton(Testname) ;  
		ClickConfirmButtonForClearAlert(Testname) ;

		//*********Check No Template and Save******************	
		Thread.sleep(5000);
		SavedTypesDropDown(Testname);
		VerifyNoTemplate(Testname);
		CancelSavedTmpl(Testname);     

		//*********Check Save Template without Body******************		
		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		ClickPlusButtontoAddHTMLTemplate(Testname);
		Thread.sleep(5000);
		FillTemplateName(Testname, TemplateName) ;    
		SaveTemplate(Testname);	
		Thread.sleep(5000);
		AcceptAlert(Testname) ;
		ClickExitEditorButton(Testname);
		Thread.sleep(2000);

		//*********Check Save Template with Body and Description******************
		TemplateName=test.readColumnData("TemplateName", sheetname);
		TempltDecsp=test.readColumnData("TemplateDecrip", sheetname);
		Thread.sleep(2000);
		//		SavedTypesDropDown(Testname);
		//		ClickPlusButtontoAddHTMLTemplate(Testname);        


		SavedTypesDropDown(Testname);
		ClickPlusButtontoAddHTMLTemplate(Testname);
		Thread.sleep(5000);
		FillTemplateName(Testname, TemplateName) ;   
		Thread.sleep(5000);
		FillTemplateDescription(Testname , TempltDecsp);
		Thread.sleep(25000);

		SaveTemplate(Testname);
		Thread.sleep(5000);
		ConfirmSave(Testname) ;
		Thread.sleep(5000);
		ClickExitEditorButton(Testname); 
		Thread.sleep(2000);      

		//*********Check Saved Template Exists******************
		SavedTypesDropDown(Testname);
		Operations_PreLiveTemplate(Testname, "Edit");

		//*********Check Edit Template and save******************		
		EditTemplNameDescptn(Testname);       
		Thread.sleep(5000);
		SaveTemplate(Testname);
		Thread.sleep(5000);
		ConfirmSave(Testname) ;
		Thread.sleep(5000);
		ClickExitEditorButton(Testname);
		Thread.sleep(5000);

		//*********Check Edited Template exists******************	
		SavedTypesDropDown(Testname);
		Operations_PreLiveTemplate(Testname, "ConfirmEditAndView");

		//*********Check Preview Send without Email Id*****************		
		ClickPreviewEmailButton(Testname) ;
		Thread.sleep(4000);
		PreviewSendBtn(Testname);
		Thread.sleep(6000);
		AcceptAlert(Testname) ;
		Thread.sleep(4000);

		//*********Check Preview Send with Email Id*****************		
		FillToEmailId(Testname,rplyTxt);
		Thread.sleep(5000);
		PreviewSendBtn(Testname);
		Thread.sleep(5000);
		ConfirmSave(Testname) ;
		Thread.sleep(5000);
		ClickExitEditorButton(Testname);
		Thread.sleep(5000);

		//*********Check Template exists for saved Email Id****************		
		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		Operations_PreLiveTemplate(Testname, "Delete");
		Thread.sleep(5000);

		//*********Check delete Template****************				
		ClickOKToConfirmDelete(Testname)  ;
		Thread.sleep(5000);
		ClickCloseOnSuccsessfullDelete(Testname) ;
		Thread.sleep(5000);
		CancelSavedTmpl(Testname);
		Thread.sleep(3000);

		//*********Check deleted Template does not exist***************		
		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		Operations_PreLiveTemplate(Testname, "VerifyDelete");
		Thread.sleep(5000);
		CancelSavedTmpl(Testname);
		Thread.sleep(5000);

		//*********Check Create New Template ****************		
		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		ClickPlusButtontoAddHTMLTemplate(Testname);	
		Thread.sleep(5000);
		FillTemplateName(Testname , TemplateName) ;
		Thread.sleep(5000);
		FillTemplateDescription(Testname , TempltDecsp)    ;
		Thread.sleep(5000);
		SaveTemplate(Testname);
		Thread.sleep(5000);
		ConfirmSave(Testname) ;
		Thread.sleep(5000);
		ClickExitEditorButton(Testname);	
		Thread.sleep(5000);


		//		SaveTemplate(Testname);	
		//		ConfirmSave(Testname) ;

		//*********Check Promote template to Live****************		
		Thread.sleep(5000);
		SavedTypesDropDown(Testname);
		Operations_PreLiveTemplate(Testname, "PromoteTempl");	
		Thread.sleep(5000);
		ClickOKToConfirmPromoteToLive(Testname);		
		Thread.sleep(5000);
		ClickCloseOnSuccessfullPromotionAlert(Testname) ;
		Thread.sleep(5000);
		CancelSavedTmpl(Testname);

		SavedTypesDropDown(Testname);
		Operations_PreLiveTemplate(Testname, "PromoteTempl");	
		Thread.sleep(5000);
		ClickOKToConfirmPromoteToLive(Testname);		
		Thread.sleep(5000);
		ClickCloseOnSuccessfullPromotionAlert(Testname) ;
		Thread.sleep(5000);
		CancelSavedTmpl(Testname);

		//*********Check Promoted Template exists in Live****************		
		Thread.sleep(5000);
		ClickLiveRadioButton(Testname);	
		Thread.sleep(5000);
		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		Operations_LiveTemplates(Testname, "View");	   


		//*********Check Insert Imange with URL ******************		
		//		EditTemplNameDescptn(Testname); 
		InsertImageUrl(Testname); 
		EnterImgUrlInTxtBx(Testname);
		InsertbtnPopup(Testname) ;
		Thread.sleep(5000);
		SaveTemplate(Testname);
		Thread.sleep(5000);
		ConfirmSave(Testname) ;
		Thread.sleep(5000);
		ClickExitEditorButton(Testname);
		Thread.sleep(5000);



		//3/3
		//********************************************Live templates*****************************************************************************************		
		//		Live(Testname, rplyTxt );

		//	ClickeDeliverTab(Testname);
		ClickLiveRadioButton(Testname);   
		Thread.sleep(5000);
		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		//SearchForLiveTemplate(Testname);

		Operations_LiveTemplates(Testname, "View");	 
		Thread.sleep(5000);
		ClickPreviewEmailButton(Testname);
		Thread.sleep(5000);
		EmailAddrTextBox(Testname, rplyTxt) ;	
		Thread.sleep(5000);
		PreviewSendBtn(Testname);
		Thread.sleep(5000);
		ConfirmSave(Testname );
		Thread.sleep(5000);
		ClickExitEditorButton(Testname);
		Thread.sleep(5000);

		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		Operations_LiveTemplates(Testname, "RollBackView");
		Thread.sleep(5000);
		ClickExitEditorButton(Testname);
		Thread.sleep(5000);

		SavedTypesDropDown(Testname);    
		Thread.sleep(5000);
		Operations_LiveTemplates(Testname, "Delete");
		Thread.sleep(5000);
		Log_DeleteTempAlert_InLive(Testname) ;
		Thread.sleep(5000);
		DelLiveOnlyBtn_OnAlert(Testname );
		Thread.sleep(5000);
		CancelSavedTmpl(Testname);
		Thread.sleep(5000);
		ClickPreLiveRadioButton(Testname);
		Thread.sleep(5000);
		ClickLiveRadioButton(Testname);		
		Thread.sleep(5000);

		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		Operations_LiveTemplates(Testname, "VerifyDeleteLiveOnlyInLiveTemplates");
		Thread.sleep(5000);
		CancelSavedTmpl(Testname);
		Thread.sleep(5000);
		ClickPreLiveRadioButton(Testname);
		Thread.sleep(5000);

		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		Operations_LiveTemplates(Testname, "VerifyTemplExistsInPreLive_DelInLiveOnly");
		Thread.sleep(5000);
		CancelSavedTmpl(Testname);
		Thread.sleep(5000);
		ClickLiveRadioButton(Testname);
		Thread.sleep(5000);

		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		Operations_LiveTemplates(Testname, "RollBack"); 
		Thread.sleep(5000);
		ConfirmRollBack(Testname) ;
		Thread.sleep(5000);		

		rolbackalert(Testname) ;
		Thread.sleep(5000);
		ClickClosebtn_SuccessfulRollBackMesg_LiveOnly( Testname ) ;
		Thread.sleep(5000);
		CancelSavedTmpl(Testname);
		Thread.sleep(5000);

		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		Operations_LiveTemplates(Testname, "VerifyRollBack");
		Thread.sleep(5000);
		CancelSavedTmpl(Testname);
		Thread.sleep(5000);


		//*********************************************************************************************************************************************************

		//From here the xml schema scenarios starts
		/*SavedTypesDropDown(Testname);
		ClickTemplate(Testname);
		ClickPreLiveRadioButton(Testname);
		LoadXMLSchemabutton_Exists( Testname,  rplyTxt) ;



		SavedTypesDropDown(Testname);
		ClickTemplate(Testname);
        Operations_PreLiveTemplate(Testname, "ClickLoadXMLBtn");
        SavedTypesDropDown(Testname);
        LoadXMLSchemaValidation(Testname);


		//*********************************************************************HTMLInsertLinkFormatFields

		SavedTypesDropDown(Testname);
		ClickPlusButtontoAddHTMLTemplate(Testname);	
		FillTemplateName(Testname , TemplateName) ;
		FillTemplateDescription(Testname , TempltDecsp)    ;
		HTMInsertLinkFormatFields(Testname);	*/

		return true;

	}

	public boolean InsertLink(String AccNo, String Testname) throws Exception {

		//	ClickeDeliverTab(Testname);
		ClickPreLiveRadioButton(Testname);
		Thread.sleep(5000);
		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		ClickPlusButtontoAddHTMLTemplate(Testname);	
		Thread.sleep(5000);
		FillTemplateName(Testname , TemplateName) ;
		Thread.sleep(5000);
		FillTemplateDescription(Testname , TempltDecsp);
		Thread.sleep(5000);
		HTMInsertLinkFormatFields(Testname);
		Thread.sleep(5000);



		TemplateManagermouseover(Testname);
		Thread.sleep(5000);

		driver.switchTo().frame("iframeContainer");

		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		VerifyTemplateingrid(Testname);
		Thread.sleep(5000);
		/*ClickOKToConfirmDelete(Testname);

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Confirmdelte"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Confirmdelte")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);	    	 
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Click on Close button for confirm delete pop up is successfull");
			log.logLine(Testname, false, "Deleting Template "+EmailtypTxt.concat(AccNo)+" via Template Manger is Successfull ");
		} else {
			log.logLine(Testname, true, "Click on Close button for confirm delete pop up is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Close button for confirm delete pop up is failed");
		}

		Thread.sleep(5000);

		driver.switchTo().defaultContent();

		DeleteTemplateviaadmin(Testname);
*/

		return true;
	}


	//********************FillTemplateName********************************************************************************

	public boolean FillTemplateName(String Testname , String TemplateName) throws Exception {       	

		if (doesElementExist2(properties.getProperty("TempltNameTxtBox"), 5)) {
			WebElement qtyday = driver.findElement(By.cssSelector(properties.getProperty("TempltNameTxtBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", qtyday);
			Thread.sleep(4000);
			//	qtyday.clear();
			qtyday.sendKeys(TemplateName);

			log.logLine(Testname, false, "Entering the text as "+TemplateName+" in \"Template Name\" textbox ");
		} else {
			log.logLine(Testname, true, "Unable to Enter the text as "+TemplateName+" in \"Template Name\" textbox ");
			//    throw new Exception("Unable to Enter the text as "+TemplateName+" in \"Template Name\" textbox ");
		}

		Actions action = new Actions(driver);
		//	action1.moveToElement(HTMLTempltDescpTxt).click().perform();
		action.moveByOffset(100, 100) ;
		return true;
	}

	//********************FillTemplateDescription********************************************************************************
	public boolean FillTemplateDescription(String Testname , String TempltDecsp) throws Exception {        
		if (doesElementExist("/html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div", 5)) {
			WebElement HTMLTempltDescpTxt = driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div"));	

			wait.until(ExpectedConditions.elementToBeClickable(HTMLTempltDescpTxt)) ;		
			HTMLTempltDescpTxt.isEnabled();
			Actions action1 = new Actions(driver);		
			//	action1.moveToElement(HTMLTempltDescpTxt).clickAndHold(HTMLTempltDescpTxt).clickAndHold().sendKeys(TempltDecsp).build().perform();
			action1.moveToElement(HTMLTempltDescpTxt).click().sendKeys(TempltDecsp).build().perform();
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entering the html as "+TempltDecsp+" in \"Template Description\" textbox ");

		} else {
			log.logLine(Testname, true, "Unable to Enter the text as "+TempltDecsp+" in \"Template Description\" textbox ");
			//		//    throw new Exception("Unable to Enter the text as "+TempltDecsp+" in \"Template Description\" textbox ");
		}
		return true ;
	}  	

	//********************ClickClearTemplateButton********************************************************************************

	public boolean ClickClearTemplateButton(String Testname) throws Exception {        	
		if (doesElementExist2(properties.getProperty("ClearTmplBtn"), 5)) {
			WebElement cler = driver.findElement(By.cssSelector(properties.getProperty("ClearTmplBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", cler);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on clear button ");
		} else {
			log.logLine(Testname, true, "Clicking on clear button  is failed");
			//    throw new Exception("Clicking on clear button  is failed");
		}
		return true ;
	}  	

	//********************ClickClearTemplateButton********************************************************************************

	public boolean ClickConfirmButtonForClearAlert(String Testname) throws Exception {   
		if (doesElementExist2(properties.getProperty("ConfrmClrOkBtn"), 5)) {
			WebElement CnfmClr = driver.findElement(By.cssSelector(properties.getProperty("ConfrmClrOkBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", CnfmClr);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on the confirm button of the  clear Alert message");
		} else {
			log.logLine(Testname, true, "Clicking on the confirm button of the  clear Alert message is failed");
			//    throw new Exception("Clicking on the confirm button of the  clear Alert message is failed");
		}
		return true ;
	}  	

	//********************ClickExitEditorButton********************************************************************************			

	public boolean ClickExitEditorButton(String Testname) throws Exception {   	
		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("ExitEditor"), 5)) {
			WebElement exitEdtr = driver.findElement(By.cssSelector(properties.getProperty("ExitEditor")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", exitEdtr);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on \"Exit Editor\" button after adding the template ");
		} else {
			log.logLine(Testname, true, "Clicking on \"Exit Editor\" button after adding the template is failed");
			//    throw new Exception("Clicking on \"Exit Editor\" button after adding the template is failed");
		}
		return true ;
	}  	

	//********************ConfirmSave********************************************************************************	        

	public boolean ConfirmSave(String Testname) throws Exception {   	
		Thread.sleep(1000);
		if (doesElementExist2(properties.getProperty("ConfirmSave"), 5)) {
			WebElement cnfmSve = driver.findElement(By.cssSelector(properties.getProperty("ConfirmSave")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnfmSve);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on OK button of the Confirm Save Alert message");
		} else {
			log.logLine(Testname, true, "Unable to Click on OK button of the Confirm Save Alert message");
			//    throw new Exception("Unable to Click on OK button of the Confirm Save Alert message");
		}
		return true ;
	}  

	//********************ClickPreviewEmailButton********************************************************************************	 

	public boolean ClickPreviewEmailButton(String Testname) throws Exception {          
		if (doesElementExist2(properties.getProperty("PreviewEmailBtn"), 5)) {
			WebElement previewbtn = driver.findElement(By.cssSelector(properties.getProperty("PreviewEmailBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", previewbtn);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on \"Preview Email\"  button");
		} else {
			log.logLine(Testname, true, "Clicking on \"Preview Email\"  button is failed");
			//    throw new Exception("Clicking on \"Preview Email\"  button is failed");
		}
		return true ;
	}         

	//********************ClickPreviewEmailButton*****************************************************************************
	public boolean AcceptAlert(String Testname) throws Exception {               
		//Error message for Empty Email Address
		Alert alert2 = driver.switchTo().alert();
		alert2.accept();
		return true ;
	} 

	//********************FillToEmailId*****************************************************************************
	public boolean FillToEmailId(String Testname,String rplyTxt) throws Exception {   
		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("EmailAddrTextBox"), 5)) {
			WebElement qtyday = driver.findElement(By.cssSelector(properties.getProperty("EmailAddrTextBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", qtyday);
			qtyday.clear();
			qtyday.sendKeys(rplyTxt);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entering the \"To Email address\" in the Management Email pop up window  ");
		} else {
			log.logLine(Testname, true, "Entering the \"To Email address\" in the Management Email pop up window is failed ");
			//    throw new Exception("Entering the \"To Email address\" in the Management Email pop up window  is failed");
		}
		return true ;
	} 

	//********************ClickOKToConfirmDelete*****************************************************************************
	public boolean ClickOKToConfirmDelete(String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("DeleteSveTempl"), 5)) {
			WebElement delSveTmplt = driver.findElement(By.cssSelector(properties.getProperty("DeleteSveTempl")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", delSveTmplt);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicking on confirm delete button");
		} else {
			log.logLine(Testname, true, "Clicking on confirm delete button is failed");
			throw new Exception("Clicking on confirm delete button is failed");
		}

		return true ;
	} 

	//********************ClickCloseOnSuccsessfullDelete*****************************************************************************
	public boolean ClickCloseOnSuccsessfullDelete(String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("ConfirmSave"), 5)) {
			WebElement cnfmSve = driver.findElement(By.cssSelector(properties.getProperty("ConfirmSave")));
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnfmSve);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on \"close\" button of the Alert message of the delete template");
		} else {
			log.logLine(Testname, true, "Clicking on \"close\" button of the Alert message of the delete template is failed");
			throw new Exception("Clicking on \"close\" button of the Alert message of the delete template is failed");
		}


		return true ;
	} 

	//********************ClickOKToConfirmPromoteToLive*****************************************************************************
	public boolean ClickOKToConfirmPromoteToLive(String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("ConfrmClrOkBtn"), 5)) {
			WebElement CnfmClr = driver.findElement(By.cssSelector(properties.getProperty("ConfrmClrOkBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", CnfmClr);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on confirm ok button for promoting the template to current live template");
		} else {
			log.logLine(Testname, true, "Clicking on confirm ok button for promoting the template to current live template is failed");
			//throw new Exception("Clicking on confirm ok button for promoting the template to current live template is failed");
		}

		return true ;
	} 

	//********************ClickCloseOnSuccessfullPromotionAlert*****************************************************************************
	public boolean ClickCloseOnSuccessfullPromotionAlert(String Testname) throws Exception {			

		if (doesElementExist2(properties.getProperty("ConfirmSave"), 5)) {
			WebElement cnfmSve = driver.findElement(By.cssSelector(properties.getProperty("ConfirmSave")));
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnfmSve);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on \"close\" button of the promote Alert message");
		} else {
			log.logLine(Testname, true, "Clicking on \"close\" button of the promote Alert message is failed");
			//throw new Exception("Clicking on \"close\" button of the promote Alert message is failed");
		}
		return true ;
	} 

	//********************ClickLiveRadioButton*****************************************************************************
	public boolean ClickLiveRadioButton(String Testname) throws Exception {	

		if (doesElementExist(properties.getProperty("LiveRadioBtn"), 5)) {
			WebElement livebtn = driver.findElement(By.xpath(properties.getProperty("LiveRadioBtn")));
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", livebtn);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on \"Live\" radio button under Template management");
		} else {
			log.logLine(Testname, true, "Clicking on \"Live\" radio button under Template management is failed");
			//throw new Exception("Clicking on \"Live\" radio button under Template management is failed");
		}
		return true ;
	} 

	//********************ClickPreLiveRadioButton*****************************************************************************
	public boolean ClickPreLiveRadioButton(String Testname ) throws Exception {

		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("PreLiveRadioBtn"), 5)) {
			WebElement livebtn = driver.findElement(By.xpath(properties.getProperty("PreLiveRadioBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", livebtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on \"Pre-Live\" radio button under Template management");
		} else {
			log.logLine(Testname, true, "Clicking on \"Pre-Live\" radio button under Template management is failed");
			//    throw new Exception("Clicking on \"Pre-Live\" radio button under Template management is failed");
		}
		return true;
	}
	//********************PreviewSendBtn*****************************************************************************

	public boolean PreviewSendBtn(String Testname ) throws Exception {

		if (doesElementExist2(properties.getProperty("SendEmlAddrBtn"), 5)) {	   
			WebElement sendPreEmlBtn = driver.findElement(By.cssSelector(properties.getProperty("SendEmlAddrBtn")));
			//((JavascriptExecutor) driver).executeScript("arguments[0].click()", sendPreEmlBtn);
			sendPreEmlBtn.click();
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on \"Send\" button of the management e-mail ..");
		} else {
			log.logLine(Testname, true, "Clicking on \"Send\" button of the management e-mail ..is failed ");
			//    throw new Exception("Clicking on \"Send\" button of the management e-mail ..is failed ");
		}
		return true;
	}

	//********************SavedTypesDropDown*****************************************************************************
	public boolean SavedTypesDropDown(String Testname ) throws Exception {

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("SavedTypesBtn"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("SavedTypesBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicked on \"Saved Types\" dropdown..");

		} else {
			log.logLine(Testname, true, "Unable to Click on \"Saved Types\" dropdown..");
			//throw new Exception("Unable to Click on \"Saved Types\" dropdown..");
		}

		return true;
	}

	//********************CancelSavedTmpl*****************************************************************************
	public boolean CancelSavedTmpl(String Testname ) throws Exception {


		if (doesElementExist2(properties.getProperty("CancelTemplt"), 5)) {	   
			WebElement cancel = driver.findElement(By.cssSelector(properties.getProperty("CancelTemplt")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", cancel);
			log.logLine(Testname, false, "Clicked on \"Cancel\" button of the SavedTypes dropdown..");

		} else {
			log.logLine(Testname, true, "Unable to Click on \"Cancel\" button of the SavedTypes dropdown..");
			//    throw new Exception("Unable to Click on \"Cancel\" button of the SavedTypes dropdown..");
		}

		return true;
	}

	//********************VerifyClickPlusButtontoAddHTMLTemplateHTML*****************************************************************************
	public boolean VerifyAddTemplateHTML(String Testname ) throws Exception {

		String[] EmlType = new String[100];
		String row = "li";

		driver.findElement(By.xpath(".//*[@id='ddl-management-saved-types-div']"));

		List<WebElement> DataCnt2= driver.findElements(By.xpath(".//*[@id='ul-management-saved-types']/li"));

		Thread.sleep(2000);
		if(doesElementExist2("ul "+row+" div[class='grid-btns'] div", 5)){
			for(int i = 0; i < DataCnt2.size(); i++) {
				EmlType[i] = driver.findElement(By.cssSelector("ul "+row+" div[class='grid-btns'] div")).getText();

				if(EmlType[i].equals(EmailtypTxt.concat(AccNo))){

					log.logLine(Testname, false, "Iterating through the Rows....and Reading the Type in \"Saved Types\" as "+EmailtypTxt.concat(AccNo));

					if (doesElementExist2("ul "+row+" div[class='grid-btns'] label", 5)) {
						WebElement htmlradio=driver.findElement(By.cssSelector("ul "+row+" div[class='grid-btns'] label+label+label"));
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", htmlradio);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Clicking on the \"Text\" radiobutton of the saved types ");

						if (doesElementExist2("ul "+row+" div button[class='k-button btn-management-add-template']", 5)) {
							WebElement plus=driver.findElement(By.cssSelector("ul "+row+" div button[class='k-button btn-management-add-template']"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", plus);
							log.logLine(Testname, false, "Clicking on the \"Add Template\" button of the saved types ");
						}
					}
					break;
				}
				row = row + "+li";
			}
		}
		return true;
	}

	//********************Verify No Template*****************************************************************************
	public boolean VerifyNoTemplate(String Testname ) throws Exception {

		String[] EmlType1 = new String[100];
		String row2 = "li";
		notemplts="No templates found!";
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='ddl-management-saved-types-div']"));

		List<WebElement> DataCnt3= driver.findElements(By.xpath(".//*[@id='ul-management-saved-types']/li"));


		if(doesElementExist2("ul "+row2+" div[class='grid-btns'] div", 5)){
			for(int i = 0; i < DataCnt3.size(); i++) {
				EmlType1[i] = driver.findElement(By.cssSelector("ul "+row2+" div[class='grid-btns'] div")).getText();

				if(EmlType1[i].equals(EmailtypTxt.concat(AccNo))){

					log.logLine(Testname, false, "Iterating through the Rows....and Reading the Type in Saved types as "+EmailtypTxt.concat(AccNo));



					if (doesElementExist2("label[for='radio-saved-type-2-"+i+"']", 5)) {
						WebElement htmlradio=driver.findElement(By.cssSelector("label[for='radio-saved-type-2-"+i+"']"));
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", htmlradio);
						log.logLine(Testname, false, "Clicking on the \"HTML\" radiobutton of the saved types ");

						//		driver.get(driver.getCurrentUrl());
						waitForElement(properties.getProperty("td ul+ul[class='ul-templates ui-sortable'] li[class='grid-btns']"));

						if (doesElementExist2("div table tbody tr td ul+ul li[class='grid-btns']", 5)) {
							String notmpltfnd = driver.findElement(By.cssSelector("div table tbody tr td ul+ul li[class='grid-btns']")).getText();
							if(notmpltfnd.contains(notemplts)){
								log.logLine(Testname, false, "No HTML templates are added to the Saved Email Type , hence clear template is sucessful");
							}else{
								log.logLine(Testname, true, "Temaplates are added after clearing the HTML hence clear template is unsucessful");
								//throw new Exception("Temaplates are added after clearing the text hence clear template is unsucessful");
							}

						} else {
							log.logLine(Testname, true, "Clicking on the \"HTML\" radiobutton of the saved types is failed");
							//throw new Exception("Clicking on the \"Text\" radiobutton of the saved types is failed");
						}
					}
					break; 
				}
				row2 = row2 + "+li";

			}

		}

		return true;
	}

	//********************Verify Live Template****************************************************************************
	/*	public boolean VerifyLiveTemplate(String Testname ) throws Exception {


		if (doesElementExist(".//*[@id='ddl-management-saved-types-div']", 5)) {
			List<WebElement> selopts = driver.findElements(By.xpath(".//*[@id='ul-management-saved-types']/li"));
			for (WebElement lnk:selopts) {
				if (lnk.getText().equals(EmailtypTxt.concat(AccNo))) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Prometed template is present in current live template , hence promote operation is sucessful");							
					break;
				}				
			}

		} else {
			log.logLine(Testname, true, "Promoted template is not present in current live template , hence promote operation is unsucessful");
			//    throw new Exception("Promoted template is not present in current live template , hence promote operation is unsucessful");
		}

		return true;
	}
	 */
	//*********** ***************    ************** ********** *************

	public boolean Operations_LiveTemplates(String Testname, String SrchType ) throws Exception {

		String[] EmlType1 = new String[50];

		WebElement LiveTemplate;
		String row_3 = "li";
		notemplts="No templates found!";

		WebElement table1 = driver.findElement(By.xpath(".//*[@id='ddl-management-saved-types-div']"));

		List<WebElement> DataCnt_3= driver.findElements(By.xpath(".//*[@id='ul-management-saved-types']/li"));

		Thread.sleep(3000);
		if(doesElementExist2("ul "+row_3+" div[class='grid-btns'] div", 5)){
			for(int k = 0; k < DataCnt_3.size(); k++) {

				Thread.sleep(3000);
				if(doesElementExist2("ul "+row_3+" div[class='grid-btns'] div", 5)){
					EmlType1[k] = driver.findElement(By.cssSelector("ul "+row_3+" div[class='grid-btns'] div")).getText();

					LiveTemplate=driver.findElement(By.cssSelector("ul "+row_3+" div[class='grid-btns'] div"));

					if(EmlType1[k].equals(EmailtypTxt.concat(AccNo))){
						Thread.sleep(1000);
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", LiveTemplate);

						log.logLine(Testname, false, "Iterating through the Rows....and clicking on the live Template promoted from pre-live template");

						switch (SrchType) {

						case "View":

							String veiwrow= "li";
							List<WebElement> DataCnt4= driver.findElements(By.xpath(".//*[@class='ul-templates ui-sortable']/li"));

							if(doesElementExist2("ul "+veiwrow+" div div+div[class='lbl-management-saved-type-name']", 5)){

								for(int j = 0; j < DataCnt4.size(); j++) {
									EmlType1[j] = driver.findElement(By.cssSelector("ul "+veiwrow+" div div+div[class='lbl-management-saved-type-name']")).getText();

									if(EmlType1[j].equals(TemplateName)){

										log.logLine(Testname, false, "Iterating through the Rows....Reading the Type in Saved type as "+EmlType1[j]);

										if (doesElementExist2("ul "+veiwrow+" div button[class='k-button btn-management-saved-template-view']", 5)) {
											WebElement viewLiveTemp=driver.findElement(By.cssSelector("ul "+veiwrow+" div button[class='k-button btn-management-saved-template-view']"));
											Thread.sleep(1000);
											((JavascriptExecutor) driver).executeScript("arguments[0].click()", viewLiveTemp);
											PleasewaitDisappear();
											log.logLine(Testname, false, "Clicking on the \"View\" button of the saved types Text Template in Live Templates");
										}
										break;
									}
									veiwrow = veiwrow + "+li";
								}
								break;
							}

						case "RollBackView":

							String rollbackview= "li";
							List<WebElement> cnt= driver.findElements(By.xpath(".//*[@class='ul-templates ui-sortable']/li"));


							if(doesElementExist2("ul "+rollbackview+" div div+div[class='lbl-management-saved-type-name']", 5)){

								for(int j = 0; j < cnt.size(); j++) {
									EmlType1[j] = driver.findElement(By.cssSelector("ul "+rollbackview+" div div+div[class='lbl-management-saved-type-name']")).getText();
									if(EmlType1[j].equals(TemplateName)){
										log.logLine(Testname, false, "Iterating through the Rows....Reading the Type in Saved type as "+EmlType1[j]);

										if (doesElementExist2("ul "+rollbackview+" div button+button+button[class='k-button btn-management-saved-template-rollbackview']", 5)) {
											WebElement viewLiveTemp=driver.findElement(By.cssSelector("ul "+rollbackview+" div button[class='k-button btn-management-saved-template-view']"));
											Thread.sleep(1000);
											((JavascriptExecutor) driver).executeScript("arguments[0].click()", viewLiveTemp);
											PleasewaitDisappear();
											log.logLine(Testname, false, "Clicking on the \"Rollback View\" button of the saved types HTML Template in Live Templates");
										}
										break;
									}
									rollbackview = rollbackview + "+li";
								}
								break;
							}

						case "VerifyTemplExistsInPreLive_DelInLiveOnly":
							String editrow1= "li";
							List<WebElement> DataCnt5= driver.findElements(By.xpath(".//*[@class='ul-templates ui-sortable']/li"));


							if(doesElementExist2("ul "+editrow1+" div div+div[class='lbl-management-saved-type-name']", 5)){

								for(int j = 0; j < DataCnt5.size(); j++) {
									EmlType1[j] = driver.findElement(By.cssSelector("ul "+editrow1+" div div+div[class='lbl-management-saved-type-name']")).getText();

									if(EmlType1[j].equals(TemplateName)){

										log.logLine(Testname, false, "Iterating through the Rows....and Reading the Type in saved Types as "+EmlType1[j]);
										log.logLine(Testname, false, "Text template is not deleted from pre-live templates after clicking on the \"Delete Live Only\" button ");
										break;
									}
									editrow1 = editrow1 + "+li";
								}
								break;
							}    


						case "Delete":
							String editrow2= "li";
							List<WebElement> DataCnt6= driver.findElements(By.xpath(".//*[@class='ul-templates ui-sortable']/li"));


							if(doesElementExist2("ul "+editrow2+" div div+div[class='lbl-management-saved-type-name']", 5)){

								for(int j = 0; j < DataCnt6.size(); j++) {
									EmlType1[j] = driver.findElement(By.cssSelector("ul "+editrow2+" div div+div[class='lbl-management-saved-type-name']")).getText();

									if(EmlType1[j].equals(TemplateName)){

										log.logLine(Testname, false, "Iterating through the Rows, Reading the Type in Saved types as "+EmlType1[j]+" and clicking on the delete button");


										if (doesElementExist2("ul "+editrow2+" div button+button[class='k-button btn-management-saved-template-delete']", 5)) {
											WebElement delete=driver.findElement(By.cssSelector("ul "+editrow2+" div button+button[class='k-button btn-management-saved-template-delete']"));
											Thread.sleep(1000);
											((JavascriptExecutor) driver).executeScript("arguments[0].click()", delete);
											log.logLine(Testname, false, "Clicking on the \"Delete\" button of the saved types Text Live Templates");
											PleasewaitDisappear();

										}
										break;
									}
									editrow2 = editrow2 + "+li";
								}
								break;
							}

						case "RollBack":          

							//WebElement table =driver.findElement(By.xpath(".//*[@id='ul-management-saved-types']/"+row_3+"/div[2]/table/tbody/tr/td"));
							if(doesElementExist2(" "+row_3+" button[title='Rollback']", 5)) {
								WebElement rollbackbtn=driver.findElement(By.cssSelector(""+row_3+" button[title='Rollback']"));           
								//           if(doesElementExist2("div[class='grid-btns'] button+button+button+button[class='k-button btn-management-saved-template-rollback']", 5)) {
								//               WebElement rollbackbtn=driver.findElement(By.cssSelector("div[class='grid-btns'] button+button+button+button[class='k-button btn-management-saved-template-rollback']"));
								((JavascriptExecutor) driver).executeScript("arguments[0].click()", rollbackbtn);
								PleasewaitDisappear();
								log.logLine(Testname, false, "Clicking on the \"RollBack \" button of the saved types Text Live Template");

							}else{
								log.logLine(Testname, false, "Clicking on the \"RollBack \" button of the saved types Text Live Template is failed");
								//throw new Exception("Clicking on the \"RollBack \" button of the saved types Text Live Template is failed");
							}

							break;    

						case "VerifyRollBack":
							Thread.sleep(2000);
							String verRollback= "li";
							List<WebElement> rollbackcnt= driver.findElements(By.xpath(".//*[@class='ul-templates ui-sortable']/li"));
							Thread.sleep(2000);
							if(doesElementExist2("ul "+verRollback+" div div+div[class='lbl-management-saved-type-name']", 5)){

								for(int j = 0; j < rollbackcnt.size(); j++) {
									Thread.sleep(2000);
									if(doesElementExist2("ul "+verRollback+" div div+div[class='lbl-management-saved-type-name']", 5)){

										//	WebElement RollBack =  driver.findElement(By.cssSelector("ul "+verRollback+" div div+div[class='lbl-management-saved-type-name']")) ;
										EmlType1[j] = driver.findElement(By.cssSelector("ul "+verRollback+" div div+div[class='lbl-management-saved-type-name']")).getText();

										if(EmlType1[j].equals(TemplateName)){

											log.logLine(Testname, false, "Iterating through the Rows....Reading the Type in Saved type as "+EmlType1[j]);

											log.logLine(Testname, false, " Text template "+TemplateName+" of Email Template is present after rollbacked from delete operation  ");

											break;

										}
										verRollback = verRollback + "+li";
									}
								}
								break;
							}

						case "VerifyDeleteLiveOnlyInLiveTemplates":

							notemplts="No templates found!";
							if (doesElementExist2("div table tbody tr td ul+ul li[class='grid-btns']", 5)) {
								String notmpltfnd = driver.findElement(By.cssSelector("div table tbody tr td ul+ul li[class='grid-btns']")).getText();
								if(notmpltfnd.equals("No templates found!")){
									log.logLine(Testname, false, " "+notmpltfnd+" is present hence \"Delete\" Live text template is sucessful");
								}else{
									log.logLine(Testname, true, " "+notmpltfnd+" is not present hence \"delete\" Live text template is unsucessful");
									//throw new Exception(""+notmpltfnd+" is not present hence \"delete\" text template is unsucessful");
								}
							} else {
								log.logLine(Testname, true, "Clicking on Delete button is failed");
								//throw new Exception("Clicking on Delete button is failed");
							}
						} //closing of scrhtype
						break; 
					}    
					row_3 = row_3 + "+li";
				}
			}
			return true;
		}
		return true;
	}

	//********************SaveTemplate***********************************************************************
	public boolean SaveTemplate(String Testname ) throws Exception {

		if (doesElementExist2(properties.getProperty("SaveTemplate"), 5)) {	   
			WebElement savebtn = driver.findElement(By.cssSelector(properties.getProperty("SaveTemplate")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", savebtn);
			log.logLine(Testname, false, "Clicked on \"Save\" button to add the template..");
		} else {
			log.logLine(Testname, true, "Clicking on \"Save\" button to add the template..is failed");
			//    throw new Exception("Clicking on \"Save\" button to add the template..is failed");
		}


		return true;
	}

	//********************Verify Save TextTemplate***********************************************************************

	public boolean Operations_PreLiveTemplate(String Testname,String SrchType) throws Exception {

		String[] EmlType1 = new String[100];
		String row_3 = "li";
		notemplts="No templates found!";
		driver.findElement(By.xpath(".//*[@id='ddl-management-saved-types-div']"));

		List<WebElement> DataCnt_3= driver.findElements(By.xpath(".//*[@id='ul-management-saved-types']/li"));

		Thread.sleep(3000);
		if(doesElementExist2("ul "+row_3+" div[class='grid-btns'] div", 5)){
			for(int k = 0,i=0; k < DataCnt_3.size(); k++, i++) {
				if(doesElementExist2("ul "+row_3+" div[class='grid-btns'] div", 5)){
					EmlType1[k] = driver.findElement(By.cssSelector("ul "+row_3+" div[class='grid-btns'] div")).getText();

					if(EmlType1[k].equals(EmailtypTxt.concat(AccNo))){

						log.logLine(Testname, false, "Iterating through the Rows....and Reading the Type in Saved type as "+EmailtypTxt.concat(AccNo));


						if (doesElementExist2("ul "+row_3+" div[class='grid-btns'] label", 5)) {
							WebElement textradio=driver.findElement(By.cssSelector("ul "+row_3+" div[class='grid-btns'] label"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", textradio);
							Thread.sleep(1000);
							log.logLine(Testname, false, "Clicking on the \"Text\" radiobutton of the saved types ");

							if (doesElementExist2("label[for='radio-saved-type-2-"+i+"']", 5)) {
								WebElement htmlradio=driver.findElement(By.cssSelector("label[for='radio-saved-type-2-"+i+"']"));
								((JavascriptExecutor) driver).executeScript("arguments[0].click()", htmlradio);
								log.logLine(Testname, false, "Clicked on the \"HTML\" radiobutton of the saved types ");


								switch (SrchType) {

								case "Edit":
									String editrow= "li";
									List<WebElement> DataCnt4= driver.findElements(By.xpath(".//*[@class='ul-templates ui-sortable']/li"));
									if(doesElementExist2("ul "+editrow+" div div+div[class='lbl-management-saved-type-name']", 5)){

										for(int j = 0; j < DataCnt4.size(); j++) {
											EmlType1[j] = driver.findElement(By.cssSelector("ul "+editrow+" div div+div[class='lbl-management-saved-type-name']")).getText();

											if(EmlType1[j].equals(TemplateName)){
												log.logLine(Testname, false, "Iterating through the Rows....Reading the Type in Saved type as "+EmlType1[j]);


												if (doesElementExist2("ul "+editrow+" div button+button[class='k-button btn-management-saved-template-edit']", 5)) {
													WebElement edit=driver.findElement(By.cssSelector("ul "+editrow+" div button+button[class='k-button btn-management-saved-template-edit']"));
													Thread.sleep(1000);
													((JavascriptExecutor) driver).executeScript("arguments[0].click()", edit);
													log.logLine(Testname, false, "Clicking on the \"Edit\" button of the saved types Text Template");
													PleasewaitDisappear();

												}
												break;
											}
											editrow = editrow + "+li";
										}
										break;						}

								case "PromoteTempl":
									String promrow= "li";
									List<WebElement> DataCnt8= driver.findElements(By.xpath(".//*[@class='ul-templates ui-sortable']/li"));

									if(doesElementExist2("ul "+promrow+" div div+div[class='lbl-management-saved-type-name']", 5)){

										for(int j = 0; j < DataCnt8.size(); j++) {
											EmlType1[j] = driver.findElement(By.cssSelector("ul "+promrow+" div div+div[class='lbl-management-saved-type-name']")).getText();

											if(EmlType1[j].equals(TemplateName)){

												log.logLine(Testname, false, "Iterating through the Rows....and Reading the Type in Saved type as "+EmlType1[j]);


												if (doesElementExist2("ul "+promrow+" div button[class='k-button btn-management-saved-template-promote']", 5)) {
													WebElement edit=driver.findElement(By.cssSelector("ul "+promrow+" div button[class='k-button btn-management-saved-template-promote']"));
													Thread.sleep(1000);
													((JavascriptExecutor) driver).executeScript("arguments[0].click()", edit);
													log.logLine(Testname, false, "Clicking on the \"Promote\" button of the saved types Text Template");
													PleasewaitDisappear();

												}
												break;

											}
											promrow = promrow + "+li";

										}

										break;
									}


								case "ConfirmEditAndView":
									String editrow1= "li";
									List<WebElement> DataCnt5= driver.findElements(By.xpath(".//*[@class='ul-templates ui-sortable']/li"));


									if(doesElementExist2("ul "+editrow1+" div div+div[class='lbl-management-saved-type-name']", 5)){

										for(int j = 0; j < DataCnt5.size(); j++) {
											EmlType1[j] = driver.findElement(By.cssSelector("ul "+editrow1+" div div+div[class='lbl-management-saved-type-name']")).getText();

											if(EmlType1[j].equals(EditTemplateName)){

												log.logLine(Testname, false, "Iterating through the Rows....and Reading the Type in saved Types as "+EmlType1[j]);


												if (doesElementExist2("ul "+editrow1+" div button+button+button[class='k-button btn-management-saved-template-view']", 5)) {
													WebElement view=driver.findElement(By.cssSelector("ul "+editrow1+" div button+button+button[class='k-button btn-management-saved-template-view']"));
													Thread.sleep(1000);
													((JavascriptExecutor) driver).executeScript("arguments[0].click()", view);
													log.logLine(Testname, false, "Clicking on the \"View\" button of the saved types Text Template");
													PleasewaitDisappear();

												}
												break;

											}
											editrow1 = editrow1 + "+li";
										}

										break;
									}

								case "Delete":
									String editrow2= "li";
									List<WebElement> DataCnt6= driver.findElements(By.xpath(".//*[@class='ul-templates ui-sortable']/li"));


									if(doesElementExist2("ul "+editrow2+" div div+div[class='lbl-management-saved-type-name']", 5)){

										for(int j = 0; j < DataCnt6.size(); j++) {
											EmlType1[j] = driver.findElement(By.cssSelector("ul "+editrow2+" div div+div[class='lbl-management-saved-type-name']")).getText();

											if(EmlType1[j].equals(EditTemplateName)){

												log.logLine(Testname, false, "Iterating through the Rows....and Reading the Type in Saved types as "+EmlType1[j]);


												if (doesElementExist2("ul "+editrow2+" div button+button+button+button[class='k-button btn-management-saved-template-delete']", 5)) {
													WebElement delete=driver.findElement(By.cssSelector("ul "+editrow2+" div button+button+button+button[class='k-button btn-management-saved-template-delete']"));
													Thread.sleep(1000);
													((JavascriptExecutor) driver).executeScript("arguments[0].click()", delete);
													log.logLine(Testname, false, "Clicking on the \"Delete\" button of the saved types Text Template");
													PleasewaitDisappear();

												}
												break;

											}
											editrow2 = editrow2 + "+li";

										}
										break;
									}


								case "VerifyDelete":

									notemplts="No templates found!";
									if (doesElementExist2("tr[style='vertical-align: top;'] td ul+ul[class='ul-templates ui-sortable'] li[class='grid-btns']", 5)) {
										String notmpltfnd = driver.findElement(By.cssSelector("tr[style='vertical-align: top;'] td ul+ul[class='ul-templates ui-sortable'] li[class='grid-btns']")).getText();
										if(notmpltfnd.contains("No templates found!")){
											log.logLine(Testname, false, " "+notmpltfnd+" is present hence \"delete\" text template is sucessful");
										}else{
											log.logLine(Testname, true, " "+notmpltfnd+" is not present hence \"delete\" text template is unsucessful");
											////    throw new Exception(""+notmpltfnd+" is not present hence \"delete\" text template is unsucessful");
										}

									} else {
										log.logLine(Testname, true, "Clicking on delete button is failed");
										//    throw new Exception("Clicking on delete button is failed");
									}

									break;

								case "ClickLoadXMLBtn":

									if (doesElementExist2("button[id='btn-load-xml-schema']", 5)) {	   
										WebElement xmlSchema = driver.findElement(By.cssSelector("button[id='btn-load-xml-schema']"));
										if(xmlSchema.isEnabled()){
											log.logLine(Testname, false, "XML Schema button is enabled");
											Thread.sleep(1000);
											((JavascriptExecutor) driver).executeScript("arguments[0].click()", xmlSchema);
										}else{
											log.logLine(Testname, true, "XML Schema button is disabled");
										}

										log.logLine(Testname, false, "Clicking on Load XML Schema button is sucessful..");

									} else {
										log.logLine(Testname, true, "Clicking on Load XML Schema button is unsucessful..");
										//    throw new Exception("Clicking on Load XML Schema button is unsucessful..");
									}

								}
								break; 
							}

						}
					}	
				}
				row_3 = row_3 + "+li";

			}

			return true;
		}
		return true;

	}

	//********************Edit Template Name Description***********************************************************************
	public boolean EditTemplNameDescptn(String Testname) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		EditTemplateName=test.readColumnData("EditTemplateName", sheetname);
		EditTempltDecsp=test.readColumnData("EditTemplateDecrip", sheetname);

		if (doesElementExist2(properties.getProperty("TempltNameTxtBox"), 5)) {
			WebElement qtyday = driver.findElement(By.cssSelector(properties.getProperty("TempltNameTxtBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", qtyday);
			qtyday.clear();
			qtyday.sendKeys(EditTemplateName);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Updating the existing text template is sucessful");
		} else {
			log.logLine(Testname, true, "Updating the existing text template is unsucessful");
			//    throw new Exception("Updating the existing text template is unsucessful");
		}

		return true;
	}

	//********************AddTemplNameDescptn**********************************************************************
	public boolean AddTemplNameDescptn(String Testname) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		TemplateName=test.readColumnData("TemplateName", sheetname);
		TempltDecsp=test.readColumnData("TemplateDecrip", sheetname);

		if (doesElementExist2(properties.getProperty("TempltNameTxtBox"), 5)) {
			WebElement qtyday = driver.findElement(By.cssSelector(properties.getProperty("TempltNameTxtBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", qtyday);
			qtyday.clear();
			qtyday.sendKeys(TemplateName);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entering the text as "+TemplateName+" in \"Template Name\" textbox ");
		} else {
			log.logLine(Testname, true, "Unable to Enter the text as "+TemplateName+" in \"Template Name\" textbox ");
			//    throw new Exception("Unable to Enter the text as "+TemplateName+" in \"Template Name\" textbox ");
		}

		if (doesElementExist2(properties.getProperty("TempltDescpTxtArea"), 5)) {
			WebElement qtyday = driver.findElement(By.cssSelector(properties.getProperty("TempltDescpTxtArea")));
			Actions AcDesc= new Actions(driver);
			AcDesc.moveToElement(qtyday);
			AcDesc.click();
			AcDesc.sendKeys(qtyday, TempltDecsp).perform();
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", qtyday);
			//	qtyday.clear();
			//	qtyday.sendKeys(TempltDecsp);
			//	Thread.sleep(3000);
			log.logLine(Testname, false, "Entering the text as "+TempltDecsp+" in \"Template Description\" textbox ");
		} else {
			log.logLine(Testname, true, "Unable to Enter the text as "+TempltDecsp+" in \"Template Description\" textbox ");
			//    throw new Exception("Unable to Enter the text as "+TempltDecsp+" in \"Template Description\" textbox ");
		}

		return true;


	}

	//********************Click Insert Icon to Insert Image Url**********************************************************************	
	public boolean InsertImageUrl(String Testname) throws Exception {       	

		//		InputOutputData test = new InputOutputData();  
		//		test.setInputFile(properties.getProperty("InputDatafile"));
		//		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		//		InsImgUrl=test.readColumnData("InsertImgUrl", sheetname);
		//		AltImageUrlText=test.readColumnData("AlternateImageUrlText", sheetname);

		if (doesElementExist("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[17]/a", 5)) {
			WebElement InsertImageBtn = driver.findElement(By.xpath("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[17]/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", InsertImageBtn);

			Thread.sleep(1000);
			log.logLine(Testname, false, "Entering the text as "+TemplateName+" in \"Template Name\" textbox ");
		} else {
			log.logLine(Testname, true, "Unable to Enter the text as "+TemplateName+" in \"Template Name\" textbox ");
			//	//    throw new Exception("Unable to Enter the text as "+TemplateName+" in \"Template Name\" textbox ");
		}

		return true;
	}


	//********************Enter url In TextBox**********************************************************************	
	public boolean EnterImgUrlInTxtBx(String Testname) throws Exception {       	

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		InsImgUrl=test.readColumnData("InsertImgUrl", sheetname);
		//			AltImageUrlText=test.readColumnData("AlternateImageUrlText", sheetname);

		if (doesElementExist2("input[id='k-editor-image-url']", 5)) {
			WebElement InsertImageBtn = driver.findElement(By.cssSelector("input[id='k-editor-image-url']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", InsertImageBtn);

			Thread.sleep(1000);
			InsertImageBtn.sendKeys(InsImgUrl);
			log.logLine(Testname, false, "Entering the text as "+TemplateName+" in \"Template Name\" textbox ");
		} else {
			log.logLine(Testname, true, "Unable to Enter the text as "+TemplateName+" in \"Template Name\" textbox ");
			//	//    throw new Exception("Unable to Enter the text as "+TemplateName+" in \"Template Name\" textbox ");
		}

		return true;
	}

	//********************Click Insert button on the popup **********************************************************************	
	public boolean InsertbtnPopup(String Testname) throws Exception {       

		if (doesElementExist2("div+div[class='k-widget k-window'] div button[class='k-dialog-insert k-button']", 5)) {
			WebElement InsBtn = driver.findElement(By.cssSelector("div+div[class='k-widget k-window'] div button[class='k-dialog-insert k-button']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", InsBtn);				
			log.logLine(Testname, false, "Entering the text as "+TemplateName+" in \"Template Name\" textbox ");
		} else {
			log.logLine(Testname, true, "Unable to Enter the text as "+TemplateName+" in \"Template Name\" textbox ");
			//	//    throw new Exception("Unable to Enter the text as "+TemplateName+" in \"Template Name\" textbox ");
		}

		return true;
	}

	//********************AddTemplate**********************************************************************
	public boolean ClickPlusButtontoAddHTMLTemplate(String Testname ) throws Exception {

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
						if(EmlType[i].equals(EmailtypTxt.concat(AccNo))){

							driver.findElement(By.cssSelector("ul "+row+" div[class='grid-btns'] div")).click();
							log.logLine(Testname, false, "Iterating through the Rows....and Reading the Type in \"Saved Types\" as "+EmailtypTxt.concat(AccNo));

							//************************************************Click HTML radio button 
							if (doesElementExist2("label[for='radio-saved-type-2-"+i+"']", 5)) {
								WebElement htmlradio=driver.findElement(By.cssSelector("label[for='radio-saved-type-2-"+i+"']"));
								wait.until(ExpectedConditions.elementToBeClickable(htmlradio));
								Thread.sleep(1000);
								((JavascriptExecutor) driver).executeScript("arguments[0].click()", htmlradio);
								log.logLine(Testname, false, "Clicked on the \"HTML\" radiobutton of the saved types ");
							}

							//************************************************Click Plus button to add HTML schema
							if (doesElementExist2("ul "+row+" div button[class='k-button btn-management-add-template']", 5)) {
								WebElement plus=driver.findElement(By.cssSelector("ul "+row+" div button[class='k-button btn-management-add-template']"));

								wait.until(ExpectedConditions.elementToBeClickable(plus));
								Thread.sleep(2000);
								((JavascriptExecutor) driver).executeScript("arguments[0].click()", plus);
								Thread.sleep(2000);

								log.logLine(Testname, false, "Clicking on the \"Add Template\" button of the saved types ");
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


	public boolean rolbackalert(String Testname) throws Exception{

		if (doesElementExist2(properties.getProperty("ConfrmClrOkBtn"), 5)) {
			WebElement CnfmClr = driver.findElement(By.cssSelector(properties.getProperty("ConfrmClrOkBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", CnfmClr);	
			log.logLine(Testname, false, "Clicking on the Ok button of the  RollBack Alert message");
		} else {
			log.logLine(Testname, true, "Clicking on the Ok button of the  RollBack Alert message is failed");
			//    throw new Exception("Clicking on the Ok button of the  RollBack Alert message is failed");
		}


		return true ;
	}	

	//***********************************LoadXMLSchemaValidation*****************************************************************************************************

	public boolean LoadXMLSchemaValidation(String Testname) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		spacialChar=test.readColumnData("SpecialChars", sheetname);
		name=test.readColumnData("Name", sheetname);

		//check for xmlschema form appears
		if (doesElementExist2("button[id='btn-load-xml-schema']", 5)) {	    
			WebElement xmlSchemaForm = driver.findElement(By.cssSelector("button[id='btn-load-xml-schema']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", xmlSchemaForm);
			if (xmlSchemaForm.isDisplayed())
			{
				log.logLine(Testname, false, "Validated Successfully - clicking on Load xml schema button is sucessful and xml container form appears ");
			}else {
				log.logLine(Testname, true, "Validated failed - clicking on Load xml schema button is sucessful and xml container form does not appears");
				//	//    throw new Exception("Validated failed - clicking on Load xml schema button is sucessful and xml container form does not appears");
			}

		}else{
			log.logLine(Testname, true, "clicking on Load xml schema button is sucessful and xml container form does not appears");
			//	//    throw new Exception("clicking on Load xml schema button is sucessful and xml container form does not appears");
		}

		//verify the document name in xml form
		if (doesElementExist2("label[id='load-xml-schema-title']", 5)) {	    
			String xmlSchemaForm = driver.findElement(By.cssSelector("label[id='load-xml-schema-title']")).getText();
			if(xmlSchemaForm.equals(EmailtypTxt.concat(AccNo))){
				log.logLine(Testname, false, "Document name exist in the XML schema form");
			}else{
				log.logLine(Testname, true, "Document name does not exist in the XML schema form");
			}
		}else{
			log.logLine(Testname, true, "Document name does not exist in the XML schema form");
			//    throw new Exception("Document name does not exist in the XML schema form"); 
		}

		//check default fields exist 
		if (doesElementExist2(properties.getProperty("DefaultFieldName1"), 5)) {	    
			String defltVal1 = driver.findElement(By.cssSelector(properties.getProperty("DefaultFieldName1"))).getAttribute("value");
			log.logLine(Testname, false, "First default value is  "+defltVal1+" ");
		}else{
			log.logLine(Testname, true, "First default value is not present ");
			//    throw new Exception("First default value is not present ");
		}

		if (doesElementExist2(properties.getProperty("DefaultFieldName2"), 5)) {	    
			String defltVal2 = driver.findElement(By.cssSelector(properties.getProperty("DefaultFieldName2"))).getAttribute("value");
			log.logLine(Testname, false, "Second default value is  "+defltVal2+" ");
		}else{
			log.logLine(Testname, true, "Second default value is not present ");
			//    throw new Exception("Second default value is not present ");
		}

		if (doesElementExist2(properties.getProperty("DefaultFieldName3"), 5)) {	    
			String defltVal3 = driver.findElement(By.cssSelector(properties.getProperty("DefaultFieldName3"))).getAttribute("value");
			log.logLine(Testname, false, "Third default value is  "+defltVal3+" ");
		}else{
			log.logLine(Testname, true, "Third default value is not present ");
			//    throw new Exception("Third default value is not present ");

		}

		if (doesElementExist2(properties.getProperty("DefaultFieldLabel1"), 5)) {	    
			String defltLbl1 = driver.findElement(By.cssSelector(properties.getProperty("DefaultFieldLabel1"))).getText();
			log.logLine(Testname, false, "First default value is  "+defltLbl1+" ");
		}else{
			log.logLine(Testname, true, "First default value is not present ");
			//    throw new Exception("First default value is not present ");
		}

		if (doesElementExist2(properties.getProperty("DefaultFieldLabel2"), 5)) {	    
			String defltLbl2 = driver.findElement(By.cssSelector(properties.getProperty("DefaultFieldLabel2"))).getText();
			log.logLine(Testname, false, "Second default value is  "+defltLbl2+" ");
		}else{
			log.logLine(Testname, true, "Second default value is not present ");
			//    throw new Exception("Second default value is not present ");
		}

		if (doesElementExist2(properties.getProperty("DefaultFieldLabel3"), 5)) {	    
			String defltLbl3 = driver.findElement(By.cssSelector(properties.getProperty("DefaultFieldLabel3"))).getText();
			log.logLine(Testname, false, "Third default value is  "+defltLbl3+" ");
		}else{
			log.logLine(Testname, true, "Third default value is not present ");
			//    throw new Exception("Third default value is not present ");

		}

		ClickPlusBtnOfXML(Testname);	

		if (doesElementExist2(properties.getProperty("FirstNewFieldVal"), 5)) {
			WebElement fldVal1 = driver.findElement(By.cssSelector(properties.getProperty("FirstNewFieldVal")));
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", fldVal1);

			fldVal1.clear();	
			fldVal1.sendKeys("ABC");
			fldVal1.sendKeys("#&^%$*");

			log.logLine(Testname, false, "Entering the special characters as "+spacialChar+"  in the first user added text box in XML schema container not allowed");
		} else {
			log.logLine(Testname, true, "Unable to Enter the special characters in the first text box in XML schema container");
			//    throw new Exception("Unable to Enter the special characters in the first text box in XML schema container");
		}

		ClickPlusBtnOfXML(Testname);

		//Enter second new field value to xml schema
		if (doesElementExist2(properties.getProperty("SecondNewFieldVal"), 5)) {
			WebElement fldVal2 = driver.findElement(By.cssSelector(properties.getProperty("SecondNewFieldVal")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", fldVal2);
			fldVal2.clear();
			fldVal2.sendKeys(name);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entering the name as "+name+" in the second user added text box in XML schema container");
		} else {
			log.logLine(Testname, true, "Unable to Enter the name as "+name+" in the second text box in XML schema container");
			//    throw new Exception("Unable to Enter the name as "+name+" in the second text box in XML schema container");
		}

		ClickSaveBtnOfXML(Testname);	
		NewAlert( Testname) ;
		log.logLine(Testname, false, "Alert pop up message \"Must fill all field values. They are required. \" appears");

		//check for xmlschema form appears
		if (doesElementExist2("button[id='btn-load-xml-schema']", 5)) {	    
			WebElement xmlSchemaForm = driver.findElement(By.cssSelector("button[id='btn-load-xml-schema']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", xmlSchemaForm);
			if (xmlSchemaForm.isDisplayed())
			{
				log.logLine(Testname, false, "Validated Successfully - clicking on Load xml schema button is sucessful and xml container form appears ");
			}else {
				log.logLine(Testname, true, "Validated failed - clicking on Load xml schema button is sucessful and xml container form does not appears");
				//	//    throw new Exception("Validated failed - clicking on Load xml schema button is sucessful and xml container form does not appears");
			}

		}else{
			log.logLine(Testname, true, "clicking on Load xml schema button is sucessful and xml container form does not appears");
			//	//    throw new Exception("clicking on Load xml schema button is sucessful and xml container form does not appears");
		}


		ClickClearBtnOfXML(Testname);
		ClickPlusBtnOfXML(Testname);
		//	NewAlert( Testname) ;
		if (doesElementExist2(properties.getProperty("FirstNewFieldVal"), 5)) {
			WebElement fldVal1 = driver.findElement(By.cssSelector(properties.getProperty("FirstNewFieldVal")));
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", fldVal1);
			Thread.sleep(2000);
			fldVal1.clear();
			fldVal1.sendKeys(name);

			log.logLine(Testname, false, "Entering the special characters as "+name+"  in the first user added text box in XML schema container");
		} else {
			log.logLine(Testname, true, "Unable to Enter the special characters in the first text box in XML schema container");
			//    throw new Exception("Unable to Enter the special characters in the first text box in XML schema container");
		}

		ClickSaveBtnOfXML(Testname);
		SaveTempAlrtMessage(Testname);
		ConfirmSaveXML(Testname);
		SavedTypesDropDown(Testname);
		ClickPlusButtontoAddHTMLTemplate(Testname);
		ValidateXMLFldAdded(Testname);
		ClickExitEditorButton(Testname);
		Thread.sleep(5000);
		ConfrmClrOkBtn(Testname);

		SavedTypesDropDown(Testname);
		Operations_PreLiveTemplate(Testname, "ClickLoadXMLBtn");
		ClickRemoveBtnOfXML(Testname);
		ClickSaveBtnOfXML(Testname);
		Thread.sleep(2000);
		SaveTempAlrtMessage(Testname);
		ConfirmSaveXML(Testname);
		SavedTypesDropDown(Testname);
		ClickPlusButtontoAddHTMLTemplate(Testname);
		ValidateXMLFldRemoved(Testname);
		ClickExitEditorButton(Testname);
		Thread.sleep(5000);
		ConfrmClrOkBtn(Testname);
		SavedTypesDropDown(Testname);
		Operations_PreLiveTemplate(Testname, "ClickLoadXMLBtn");	
		ClickBrowseBtnOfXML(Testname, "jpeg");
		//Alert pops up when uploading the file in .jpeg format as "Only .xsd, .xml or .txt  files can be uploaded"
		AlertAccept(Testname);	
		ClickBrowseBtnOfXML(Testname, "docx");
		//Alert pops up when uploading the file in .docx format as "Only .xsd, .xml or .txt  files can be uploaded"
		AlertAccept(Testname);
		ClickBrowseBtnOfXML(Testname, "xlsx");
		//Alert pops up when uploading the file in .xlsx format as "Only .xsd, .xml or .txt  files can be uploaded"
		AlertAccept(Testname);
		ClickBrowseBtnOfXML(Testname, "txt");
		ClickSaveBtnOfXML(Testname);

		SaveTempAlrtMessage(Testname);	
		ConfirmSaveXML(Testname);
		SavedTypesDropDown(Testname);
		ClickPlusButtontoAddHTMLTemplate(Testname);
		ValidateXMLFldAdded_Txt_xml_xsd_new(Testname, "txtfldd");
		ClickExitEditorButton(Testname);
		Thread.sleep(5000);
		ConfrmClrOkBtn(Testname);

		SavedTypesDropDown(Testname);
		Operations_PreLiveTemplate(Testname, "ClickLoadXMLBtn");
		ClickBrowseBtnOfXML(Testname, "xml");
		ClickSaveBtnOfXML(Testname);

		SaveTempAlrtMessage(Testname);	

		ConfirmSaveXML( Testname);

		SavedTypesDropDown(Testname);
		ClickPlusButtontoAddHTMLTemplate(Testname);
		ValidateXMLFldAdded_Txt_xml_xsd_new(Testname, "xmlflds");

		ClickExitEditorButton(Testname);
		Thread.sleep(5000);
		ConfrmClrOkBtn(Testname);

		SavedTypesDropDown(Testname);
		Operations_PreLiveTemplate(Testname, "ClickLoadXMLBtn");
		ClickBrowseBtnOfXML(Testname, "xsd");
		ClickSaveBtnOfXML(Testname);

		SaveTempAlrtMessage(Testname);	
		ConfirmSaveXML(Testname);
		SavedTypesDropDown(Testname);
		ClickPlusButtontoAddHTMLTemplate(Testname);
		ValidateXMLFldAdded_Txt_xml_xsd_new(Testname, "xsdflds");
		ClickExitEditorButton(Testname);
		Thread.sleep(5000);
		ConfrmClrOkBtn(Testname);


		return true;
	}

	//***********************************AlertAccept*******************************************************************************************
	public boolean AlertAccept(String Testname) throws Exception {
		Alert alert5 = driver.switchTo().alert();
		alert5.accept();
		return true;
	}
	//***********************************SaveTempAlrtMessage*******************************************************************************************
	public boolean SaveTempAlrtMessage(String Testname) throws Exception {
		if (doesElementExist2(properties.getProperty("SaveTempAlrtMessage"), 5)) {
			String sveAlrt = driver.findElement(By.cssSelector(properties.getProperty("SaveTempAlrtMessage"))).getText();

			if (sveAlrt.equals("XML Schema saved successfully")){
				log.logLine(Testname, false, "Alert message for confirming the \"Save\" XML Schema is sucessful ");
			}
			else {	
				log.logLine(Testname, true, "Alert message for confirming the \"Save\" XML Schema is unsucessful ");
			} 
		}else {
			log.logLine(Testname, true, "Alert message for confirming the \"Save\" XML Schema is not displayed ");
			//    throw new Exception("Alert message for confirming the \"Save\" XML Schema is not displayed ");

		}

		return true;
	}
	//***********************************ConfirmSave*******************************************************************************************
	public boolean ConfirmSaveXML(String Testname) throws Exception {


		if (doesElementExist2(properties.getProperty("ConfirmSave"), 5)) {
			WebElement cnfmSve = driver.findElement(By.cssSelector(properties.getProperty("ConfirmSave")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnfmSve);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on close button of Alert message to \"Save\" XML schema ");
		} else {
			log.logLine(Testname, true, "Unable to Click on close button of Alert message to \"Save\" XML schema ");
			//    throw new Exception("Unable to Click on close button of Alert message to \"Save\" XML schema ");
		}
		return true;
	}
	//***********************************ConfrmClrOkBtn*******************************************************************************************
	public boolean ConfrmClrOkBtn(String Testname) throws Exception {


		if (doesElementExist2(properties.getProperty("ConfrmClrOkBtn"), 5)) {
			WebElement CnfmClr = driver.findElement(By.cssSelector(properties.getProperty("ConfrmClrOkBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", CnfmClr);
			//	Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on the Ok button of the  Exit Editor Alert message");
		} else {
			log.logLine(Testname, true, "Clicking on the Ok button of the  Exit Editor Alert message is failed");
			//    throw new Exception("Clicking on the Ok button of the  Exit Editor Alert message is failed");
		}
		return true;
	}
	//***********************************ClickPlusBtnOfXML*******************************************************************************************
	public boolean ClickPlusBtnOfXML(String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("AddBtn_xmlSchema"), 5)) {	   
			WebElement plusBtn = driver.findElement(By.cssSelector(properties.getProperty("AddBtn_xmlSchema")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", plusBtn);

			log.logLine(Testname, false, "Clicked on \"Add\" button to add the new field in xml schema container..");

		} else {
			log.logLine(Testname, true, "Unable to Click on \"Add\" button to add the new field in xml schema container..");
			//    throw new Exception("Unable to Click on \"Add\" button to add the new field in xml schema container..");
		}

		return true;
	}
	//***********************************ClickSaveBtnOfXML******************************************************************************************

	public boolean ClickSaveBtnOfXML(String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("SaveXMLBtn"), 5)) {	   
			WebElement saveBtn = driver.findElement(By.cssSelector(properties.getProperty("SaveXMLBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", saveBtn);

			log.logLine(Testname, false, "Clicked on \"Save\" button to save the new field in xml schema container..");

		} else {
			log.logLine(Testname, true, "Unable to Click on \"Save\" button to save the new field in xml schema container..");
			//    throw new Exception("Unable to Click on \"Save\" button to save the new field in xml schema container..");
		}

		return true;
	}
	//**********************************ClickClearBtnOfXML********************************************************************************

	public boolean ClickClearBtnOfXML(String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("ClearXMLBtn"), 5)) {	   
			WebElement saveBtn = driver.findElement(By.cssSelector(properties.getProperty("ClearXMLBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", saveBtn);

			log.logLine(Testname, false, "Clicked on \"Clear\" button to clear the new field in xml schema container..");

		} else {
			log.logLine(Testname, true, "Unable to Click on \"Clear\" button to clear the new field in xml schema container..");
			//    throw new Exception("Unable to Click on \"Clear\" button to clear the new field in xml schema container..");
		}

		return true;
	}
	//**********************************ValidateXMLFldAdded*******************************************************************************

	public boolean ValidateXMLFldAdded(String Testname) throws Exception {


		if (doesElementExist2(properties.getProperty("ValidateXmlFldAdded"), 5)) {	    
			WebElement xmlFldName = driver.findElement(By.cssSelector(properties.getProperty("ValidateXmlFldAdded")));

			List<WebElement> xmlfld = driver.findElements(By.cssSelector(properties.getProperty("ValidateXmlFldAdded")));
			for (WebElement link:xmlfld) {
				if (link.getText().equals(name)) {

					log.logLine(Testname, false, "Newly Added xml field name exist in the template management pre live view of the  Editor form");
					break;
				}
			}

		}else {
			log.logLine(Testname, true, "Newly added xml field is not displayed in the template management pre live view");
			//    throw new Exception("Newly added xml field is not displayed in the template management pre live view");
		}

		return true;
	}


	//***********************************ClickRemoveBtnOfXML***************************************************************************

	public boolean ClickRemoveBtnOfXML(String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("RemoveBtn_xmlSchema"), 5)) {	   
			WebElement plusBtn = driver.findElement(By.cssSelector(properties.getProperty("RemoveBtn_xmlSchema")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", plusBtn);

			log.logLine(Testname, false, "Clicked on \"Remove\" button to delete the new field in xml schema container..");

		} else {
			log.logLine(Testname, true, "Unable to Click on \"Remove\" button to delete the new field in xml schema container..");
			//    throw new Exception("Unable to Click on \"Remove\" button to delete the new field in xml schema container..");
		}

		return true;
	}

	//***********************************ValidateXMLFldRemoved*****************************************************

	public boolean ValidateXMLFldRemoved(String Testname) throws Exception {


		if (doesElementExist2(properties.getProperty("ValidateXmlFldAdded"), 5)) {	    
			WebElement xmlFldName = driver.findElement(By.cssSelector(properties.getProperty("ValidateXmlFldAdded")));

			List<WebElement> xmlfld = driver.findElements(By.cssSelector(properties.getProperty("ValidateXmlFldAdded")));
			for (WebElement link:xmlfld) {
				if (!link.getText().equals(name)) {

					log.logLine(Testname, false, "Deleted xml field name does not exist in the template management pre live view of the  Editor form ");

				}/*else{

						log.logLine(Testname, false, "Deleted xml field name exist in the template management pre live view of the  Editor form");
						//    throw new Exception("Removing/Deletion of the added xml field name in the template management pre live view of the  Editor form is unsucessful ");

					}*/
			}
			log.logLine(Testname, false, "Removing/Deletion of the added xml field name in the template management pre live view of the  Editor form is sucessful ");

		}else {
			log.logLine(Testname, true, "Removing/Deletion of the added xml field name in the template management pre live view of the  Editor form is unsucessful ");
			//    throw new Exception("Removing/Deletion of the added xml field name in the template management pre live view of the  Editor form is unsucessful ");
		}

		return true;
	}
	//***********************************ClickBrowseBtnOfXML*******************************************************************

	public boolean ClickBrowseBtnOfXML(String Testname, String Format) throws Exception {

		if (doesElementExist2(properties.getProperty("BrowseXMLBtn"), 5)) {	   
			WebElement brwseBtn = driver.findElement(By.cssSelector(properties.getProperty("BrowseXMLBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", brwseBtn);

			log.logLine(Testname, false, "Clicked on \"Browse\" button to upload the .docx file..");

			switch (Format) {
			case "jpeg":

				brwseBtn.sendKeys("C:/Pivot_Portal/Test Data/TestImage.jpg");
				log.logLine(Testname, false, "uploading the \".jpeg\" file format");
				Thread.sleep(1000);
				break;

			case "docx":

				brwseBtn.sendKeys("C:/Pivot_Portal/Test Data/XmldocTest.docx");
				log.logLine(Testname, false, "uploading the \".docx\" file format");
				Thread.sleep(1000);
				break;	

			case "xlsx":

				brwseBtn.sendKeys("C:/Pivot_Portal/Test Data/XmlExcelTest.xlsx");
				log.logLine(Testname, false, "uploading the \".xlsx\" file format ");
				Thread.sleep(1000);
				break;

			case "txt":

				brwseBtn.sendKeys("C:/Pivot_Portal/Test Data/Xml.txt");
				log.logLine(Testname, false, "uploading the \".txt\" file format ");
				Thread.sleep(2000);
				break;	

			case "xml":

				brwseBtn.sendKeys("C:/Pivot_Portal/Test Data/Xml.xml");
				log.logLine(Testname, false, "uploading the \".xml\" file format ");
				Thread.sleep(2000);
				break;	


			case "xsd":

				brwseBtn.sendKeys("C:/Pivot_Portal/Test Data/Xml.xsd");
				log.logLine(Testname, false, "uploading the \".xsd\" file format ");
				Thread.sleep(2000);
				break;	

			}

		}

		return true;
	}

	//**********************************ValidateXMLFldAdded_Txt_xml_xsd_new**************************************************************

	public boolean ValidateXMLFldAdded_Txt_xml_xsd_new(String Testname, String type) throws Exception {

		String[] xmlfield = new String[15];
		String fld = "li";
		List<WebElement> fldcnt= driver.findElements(By.cssSelector("ul[id='ul-management-template-fields'] li"));

		for(int f=0; f < fldcnt.size(); f++ ){

			if(doesElementExist2("ul[id='ul-management-template-fields'] "+fld+" ", 5)){

				xmlfield[f]=driver.findElement(By.cssSelector("ul[id='ul-management-template-fields'] "+fld+" ")).getText();

				switch (type){
				case "txtfldd":
					if (xmlfield[f].equals("FirstName") || xmlfield[f].equals("LastName")) {

						log.logLine(Testname, false, "Newly Added xml field names from the \".txt\" file exists in the template management pre live view of the  Editor form");

					}
					break;
				case "xmlflds":
					if (xmlfield[f].equals("AccNo") || xmlfield[f].equals("ZipCode")) {

						log.logLine(Testname, false, "Newly Added xml field names from the \".xml\" file exists in the template management pre live view of the  Editor form");

					}

					break;

				case "xsdflds":

					if (xmlfield[f].equals("Addr1") || xmlfield[f].equals("Addr2") || xmlfield[f].equals("Addr3")) {

						log.logLine(Testname, false, "Newly Added xml field names from the \".xsd\" file exists in the template management pre live view of the  Editor form");

					}

					break;


				}

			}
			fld = fld + "+li";

		}


		return true;
	}

	public boolean Log_DeleteTempAlert_InLive(String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("DeleteTempltAlertMessage"), 5)) {
			String delTempAlrt = driver.findElement(By.cssSelector(properties.getProperty("DeleteTempltAlertMessage"))).getText();

			if (delTempAlrt.equals("Warning: Both Pre-Live and Live versions will be deleted, do you want to continue?")){
				log.logLine(Testname, false, "Alert message for confirming the \"Delete Live Template\" operation is sucessful ");
			}
			else {    
				log.logLine(Testname, true, "Alert message for confirming the \"Delete Live Template\" operation is unsucessful ");
			} 
		}else {
			log.logLine(Testname, true, "Alert message for confirming the \"Delete Live Template\" operation is not displayed ");
			//    throw new Exception("Alert message for confirming the \"Delete Live Template\" operation is not displayed ");

		}

		return true ;

	}

	public boolean EmailAddrTextBox(String Testname, String rplyTxt) throws Exception {


		if (doesElementExist2(properties.getProperty("EmailAddrTextBox"), 5)) {
			WebElement qtyday = driver.findElement(By.cssSelector(properties.getProperty("EmailAddrTextBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", qtyday);
			qtyday.clear();
			qtyday.sendKeys(rplyTxt);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entering the \"To Email address\" in the Management Email pop up window  ");
		} else {
			log.logLine(Testname, true, "Entering the \"To Email address\" in the Management Email pop up window is failed ");
			//    throw new Exception("Entering the \"To Email address\" in the Management Email pop up window  is failed");
		}

		return true ;		        

	}  

	public boolean ConfirmSave(String Testname , String TemplateName) throws Exception {    
		if (doesElementExist2(properties.getProperty("ConfirmSave"), 5)) {
			WebElement cnfmSve = driver.findElement(By.cssSelector(properties.getProperty("ConfirmSave")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnfmSve);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicking on Close button of the Confirm \"Email sent\" Alert message");
		} else {
			log.logLine(Testname, true, "Unable to Click on OK button of the Confirm \"Email sent\" Alert message");
			//    throw new Exception("Unable to Click on OK button of the Confirm \"Email sent\" Alert message");
		}
		return true;
	}

	public boolean DelLiveOnlyBtn_OnAlert(String Testname) throws Exception {    


		if (doesElementExist2(properties.getProperty("ConfirmDelLiveOnly"), 5)) {
			WebElement livebtn = driver.findElement(By.cssSelector(properties.getProperty("ConfirmDelLiveOnly")));
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", livebtn);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on \"Delete Live only\" button of the Delete operation in Live Templates");
		} else {
			log.logLine(Testname, true, "Clicking on \"Delete Live only\" button of the Delete operation in Live Templates is failed");
			//throw new Exception("Clicking on \"Delete Live only\" button of the Delete operation in Live Templates is failed");
		}

		return true;
	}


	public boolean ClickClosebtn_SuccessfulDeleteMesg_LiveOnly(String Testname ) throws Exception {

		if (doesElementExist2(properties.getProperty("DeleteAlert"), 5)) {
			WebElement livebtn = driver.findElement(By.cssSelector(properties.getProperty("DeleteAlert")));
			Thread.sleep(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", livebtn);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on \"close\" button of the Delete Alert message in Live Templates");
		} else {
			log.logLine(Testname, true, "Clicking on \"close\" button of the Delete Alert message in Live Templates is failed");
			//throw new Exception("Clicking on \"close\" button of the Delete Alert message in Live Templates is failed");
		}
		return true;

	}

	public boolean ConfirmRollBack(String Testname) throws Exception {




		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("DeleteTempltAlertMessage"), 5)) {
			String delTempAlrt = driver.findElement(By.cssSelector(properties.getProperty("DeleteTempltAlertMessage"))).getText();

			if (delTempAlrt.contains("You are about to restore the template \'"+TemplateName+"\', this action will overwrite the current live version (if any). Are you sure?")){
				log.logLine(Testname, false, "Alert message for confirming the \"RollBack\" operation in Live Templates is sucessful ");
			}
			else {    
				log.logLine(Testname, true, "Alert message for confirming the \"RollBack\" operation in Live Templates is unsucessful ");
			} 
		}else {
			log.logLine(Testname, true, "Alert is message for confirming the \"RollBack\" operation in Live Templates is not displayed ");
			//    throw new Exception("Alert message for confirming the \"RollBack\" operation in Live Templates is not displayed ");

		}

		return true ;
	}

	public boolean ClickClosebtn_SuccessfulRollBackMesg_LiveOnly(String Testname ) throws Exception {
		if (doesElementExist2(properties.getProperty("DeleteAlert"), 5)) {
			WebElement livebtn = driver.findElement(By.cssSelector(properties.getProperty("DeleteAlert")));
			Thread.sleep(1000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", livebtn);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on \"close\" button of the RollBack Alert message in Live Templates");
		} else {
			log.logLine(Testname, true, "Clicking on \"close\" button of the RollBack Alert message in Live Templates is failed");
			// throw new Exception("Clicking on \"close\" button of the RollBack Alert message in Live Templates is failed");
		}

		return true ;
	}

	public boolean LoadXMLSchemabutton_Exists(String Testname, String rplyTxt) throws Exception {
		if (doesElementExist2(properties.getProperty("LoadXMLSchemaBtn"), 5)) {	   
			WebElement xmlSchema = driver.findElement(By.cssSelector(properties.getProperty("LoadXMLSchemaBtn")));
			if(xmlSchema.isEnabled()){
				log.logLine(Testname, false, "XML Schema button is enabled");
			}else{
				log.logLine(Testname, false, "XML Schema button is disabled");
			}

			log.logLine(Testname, false, "Load XML Schema element is present..");

		} else {
			log.logLine(Testname, true, "Load XML Schema element is not present..");
			//    throw new Exception("Load XML Schema element is not present..");
		}
		return true;
	}

	public boolean ClickTemplate(String Testname ) throws Exception {

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

						if(EmlType[i].equals(EmailtypTxt.concat(AccNo))){
							driver.findElement(By.cssSelector("ul "+row+" div[class='grid-btns'] div")).click();
							log.logLine(Testname, false, "Iterating through the Rows....and Reading the Type in \"Saved Types\" as "+EmailtypTxt.concat(AccNo));

							if (doesElementExist2("label[for='radio-saved-type-2-"+i+"']", 5)) {
								WebElement htmlradio=driver.findElement(By.cssSelector("label[for='radio-saved-type-2-"+i+"']"));
								Thread.sleep(1000);
								((JavascriptExecutor) driver).executeScript("arguments[0].click()", htmlradio);
								log.logLine(Testname, false, "Clicked on the \"HTML\" radiobutton of the saved types ");
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

	public boolean HTMInsertLinkFormatFields(String Testname ) throws Exception {

		Actions action1 = new Actions(driver);		
		WebElement HTMLTempltDescpTxt = null ;
		if (doesElementExist("/html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div", 5)) {
			HTMLTempltDescpTxt = driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div"));	
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(HTMLTempltDescpTxt)) ;				
			log.logLine(Testname, false, "Entering the html as "+TempltDecsp+" in \"Template Description\" textbox ");		

			action1.moveToElement(HTMLTempltDescpTxt).build().perform();
			action1.sendKeys(Keys.chord(Keys.CONTROL, "a")).build().perform();
			action1.sendKeys(Keys.chord(Keys.DELETE)).build().perform();				
		}	else {
			log.logLine(Testname, true, "Failed to click on createLink button");
			//throw new Exception("Failed to click on createLink button");
		}


		if (doesElementExist("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[15]/a", 5)) {
			WebElement createLinkBtn = driver.findElement(By.xpath("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[15]/a"));	
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", createLinkBtn);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Click on createLink button successful");
		}else {
			log.logLine(Testname, true, "Failed to click on createLink button");
			//    throw new Exception("Failed to click on createLink button");
		}

		if (doesElementExist2("input[id='k-editor-link-url']", 5)) {
			WebElement EditorLinkUrltextbx = driver.findElement(By.cssSelector("input[id='k-editor-link-url']"));	
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", EditorLinkUrltextbx);	
			action1.moveToElement(EditorLinkUrltextbx).build().perform();
			action1.click().build().perform();
			action1.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			action1.sendKeys(Keys.chord(Keys.DELETE));
			action1.sendKeys(URL).build().perform();
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on EditorLinkUrltextbx successful");
		}else {
			log.logLine(Testname, true, "Failed to click on EditorLinkUrltextbx");
			//throw new Exception("Failed to click on EditorLinkUrltextbx");
		}		

		if (doesElementExist2("input[id='k-editor-link-id']", 5)) {
			WebElement LinkId = driver.findElement(By.cssSelector("input[id='k-editor-link-id']"));	
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", LinkId);	
			action1.moveToElement(LinkId).build().perform();
			action1.click().build().perform();
			action1.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			action1.sendKeys(Keys.chord(Keys.DELETE));
			action1.sendKeys(LinkID).build().perform();
			Thread.sleep(3000);
			log.logLine(Testname, false, "Click on EditorLinkUrltextbx successful");
		}else {
			log.logLine(Testname, true, "Failed to click on EditorLinkUrltextbx");
			//throw new Exception("Failed to click on EditorLinkUrltextbx");
		}			

		if (doesElementExist2("input[id='k-editor-link-text']", 5)) {
			WebElement Linktext = driver.findElement(By.cssSelector("input[id='k-editor-link-text']"));	
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Linktext);	
			action1.moveToElement(Linktext).build().perform();
			action1.click().build().perform();
			action1.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			action1.sendKeys(Keys.chord(Keys.DELETE));
			action1.sendKeys(LnkText).build().perform();
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on EditorLinkUrltextbx successful");
		}else {
			log.logLine(Testname, true, "Failed to click on EditorLinkUrltextbx");
			//throw new Exception("Failed to click on EditorLinkUrltextbx");
		}					

		if (doesElementExist2("input[id='k-editor-link-title']", 5)) {
			WebElement LinkTooltip = driver.findElement(By.cssSelector("input[id='k-editor-link-title']"));	
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", LinkTooltip);	
			action1.moveToElement(LinkTooltip).build().perform();
			action1.click().build().perform();
			action1.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			action1.sendKeys(Keys.chord(Keys.DELETE));
			action1.sendKeys(LnkToolTp).build().perform();
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on EditorLinkUrltextbx successful");
		}else {
			log.logLine(Testname, true, "Failed to click on EditorLinkUrltextbx");
			//throw new Exception("Failed to click on EditorLinkUrltextbx");
		}				

		if (doesElementExist2("input[id='k-editor-link-target']", 5)) {
			WebElement OpenLinkNewWindwCheckBx = driver.findElement(By.cssSelector("input[id='k-editor-link-target']"));	

			if (!(OpenLinkNewWindwCheckBx.isSelected())){
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", OpenLinkNewWindwCheckBx);
			}				
			log.logLine(Testname, false, "Click on EditorLinkUrltextbx successful");
		}else {
			log.logLine(Testname, true, "Failed to click on EditorLinkUrltextbx");
			// throw new Exception("Failed to click on EditorLinkUrltextbx");
		}

		if (doesElementExist2("button[id='createLinkWindowInsert']", 5)) {
			WebElement InsertBtn = driver.findElement(By.cssSelector("button[id='createLinkWindowInsert']"));				
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", InsertBtn);					
			log.logLine(Testname, false, "Click on EditorLinkUrltextbx successful");
		}else {
			log.logLine(Testname, true, "Failed to click on EditorLinkUrltextbx");
			//throw new Exception("Failed to click on EditorLinkUrltextbx");
		}				

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(HTMLTempltDescpTxt)) ;				
		log.logLine(Testname, false, "Entering the html as "+TempltDecsp+" in \"Template Description\" textbox ");		

		action1.moveToElement(HTMLTempltDescpTxt).click().build().perform();
		//	action1.sendKeys(Keys.chord(Keys.CONTROL, "a")).build().perform();s
		//	action1.sendKeys(Keys.chord(Keys.DELETE)).build().perform();				

		action1.moveToElement(HTMLTempltDescpTxt).build().perform();
		action1.sendKeys(TempltDecsp).build().perform();
		//action1.sendKeys(Keys.chord(Keys.CONTROL, "a")).build().perform();

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.linkText("Bold") ))) ;
		WebElement WebElBold = 	driver.findElement(By.linkText("Bold") ) ;
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", WebElBold);		

		if (doesElementExist("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[4]/a", 5)) {
			WebElement italicsBtn = driver.findElement(By.xpath("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[4]/a"));	
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", italicsBtn);	
			log.logLine(Testname, false, "Click on italics button successful");
		}else {
			log.logLine(Testname, true, "Failed to click on italics button");
			//    throw new Exception("Failed to click on italics button");
		}

		if (doesElementExist("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[4]/a", 5)) {
			WebElement italicsBtn = driver.findElement(By.xpath("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[4]/a"));	
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", italicsBtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on italics button successful");
		}else {
			log.logLine(Testname, true, "Failed to click on italics button");
			//throw new Exception("Failed to click on italics button");
		}



		if (doesElementExist("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[5]/a", 5)) {
			WebElement underlineBtn = driver.findElement(By.xpath("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[5]/a"));	
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", underlineBtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on underline button successful");
		}else {
			log.logLine(Testname, true, "Failed to click on underline button");
			// throw new Exception("Failed to click on underline button");
		}



		if (doesElementExist("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[6]/a", 5)) {
			WebElement strikeThruBtn = driver.findElement(By.xpath("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[6]/a"));	
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", strikeThruBtn);
			Thread.sleep(2000);				
			log.logLine(Testname, false, "Click on strikeThru button successful");
		}else {
			log.logLine(Testname, true, "Failed to click on strikeThru button");
			// throw new Exception("Failed to click on strikeThru button");
		}



		if (doesElementExist("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[7]/a", 5)) {
			WebElement alignleftBtn = driver.findElement(By.xpath("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[7]/a"));	
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", alignleftBtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on alignleft button successful");
		}else {
			log.logLine(Testname, true, "Failed to click on alignleft button");
			//    throw new Exception("Failed to click on alignleft button");
		}



		if (doesElementExist("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[8]/a", 5)) {
			WebElement centerTextBtn = driver.findElement(By.xpath("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[8]/a"));	
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", centerTextBtn);	
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on centerText button successful");
		}else {
			log.logLine(Testname, true, "Failed to click on centerText button");
			//    throw new Exception("Failed to click on centerText button");
		}


		if (doesElementExist("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[9]/a", 5)) {
			WebElement alignRightBtn = driver.findElement(By.xpath("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[9]/a"));	
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", alignRightBtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on alignRight button successful");
		}else {
			log.logLine(Testname, true, "Failed to click on alignRight button");
			//    throw new Exception("Failed to click on alignRight button");
		}

		if (doesElementExist("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[10]/a", 5)) {
			WebElement justifyTextBtn = driver.findElement(By.xpath("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[10]/a"));	
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", justifyTextBtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on justifyText button successful");
		}else {
			log.logLine(Testname, true, "Failed to click on justifyText button");
			//    throw new Exception("Failed to click on justifyText button");
		}

		if (doesElementExist("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[11]/a", 5)) {
			WebElement insertUnOrderedListBtn = driver.findElement(By.xpath("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[11]/a"));	
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", insertUnOrderedListBtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on insertUnOrderedList button successful");
		}else {
			log.logLine(Testname, true, "Failed to click on insertUnOrderedList button");
			//    throw new Exception("Failed to click on insertUnOrderedList button");
		}


		if (doesElementExist("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[12]/a", 5)) {
			WebElement insertOrderedListBtn = driver.findElement(By.xpath("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[12]/a"));	
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", insertOrderedListBtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on insertOrderedList button successful");
		}else {
			log.logLine(Testname, true, "Failed to click on insertOrderedList button");
			//    throw new Exception("Failed to click on insertOrderedList button");
		}

		if (doesElementExist("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[13]/a", 5)) {
			WebElement indentBtn = driver.findElement(By.xpath("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[13]/a"));	
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", indentBtn);	
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on indent button successful");
		}else {
			log.logLine(Testname, true, "Failed to click on indent button");
			//    throw new Exception("Failed to click on indent button");
		}


		if (doesElementExist("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[14]/a", 5)) {
			WebElement outdentBtn = driver.findElement(By.xpath("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[14]/a"));	
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", outdentBtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on outdent button successful");
		}else {
			log.logLine(Testname, true, "Failed to click on outdent button");
			//    throw new Exception("Failed to click on outdent button");
		}

		/*		if (doesElementExist("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[15]/a", 5)) {
				WebElement createLinkBtn = driver.findElement(By.xpath("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[15]/a"));	
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", createLinkBtn);	
				log.logLine(Testname, false, "Click on createLink button successful");
			}else {
				log.logLine(Testname, true, "Failed to click on createLink button");
				//    throw new Exception("Failed to click on createLink button");
			}
		 */	

		//	Alert Al = driver.switchTo().alert();
		//	Al.accept();









		if (doesElementExist("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[1]/a", 5)) {
			WebElement undoBtn = driver.findElement(By.xpath("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[1]/a"));	
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", undoBtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on undo button successful");
		}else {
			log.logLine(Testname, true, "Failed to click on undo button");
			//    throw new Exception("Failed to click on undo button");
		}

		if (doesElementExist("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[2]/a", 5)) {
			WebElement redoBtn = driver.findElement(By.xpath("html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div/table/tbody/tr[1]/td/ul/li[2]/a"));	
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", redoBtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on redo button successful");
		}else {
			log.logLine(Testname, true, "Failed to click on redo button");
			//    throw new Exception("Failed to click on redo button");
		}




		return true;
	}

	public boolean NewAlert(String Testname) throws Exception {
		if (doesElementExist2("button[id='kendoWindowAlertbtnClose']", 5)) {
			WebElement btn = driver.findElement(By.cssSelector("button[id='kendoWindowAlertbtnClose']"));
			btn.click();
			log.logLine(Testname, false, "Entering the name as "+name+" in the second user added text box in XML schema container");
		} else {
			log.logLine(Testname, true, "Unable to Enter the name as "+name+" in the second text box in XML schema container");
			//throw new Exception("Unable to Enter the name as "+name+" in the second text box in XML schema container");
		}
		return true;
	}

	public boolean SelectAppEmailTypeCreation(String Testname , String TemplateName) throws Exception {      

		Thread.sleep(15000);
		if (doesElementExist2(properties.getProperty("SelectApp"), 5)) {
			Select emltype = new Select(driver.findElement(By.cssSelector(properties.getProperty("SelectApp"))));		
			emltype.selectByVisibleText("RGT1099 - RGT1099")	;			
			log.logLine(Testname, false, "Selecting the Regression testing application from the Email Type Admin list in Template Management");
		}

		return true;
	}
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

	public void TemplateManagermouseover(String Testname) throws Exception {

		driver.switchTo().defaultContent();
		Actions builder = new Actions(driver);	        
		WebElement mnuElement = driver.findElement(By.xpath(properties.getProperty("TemplateManagement")));	
		if (doesElementExist(properties.getProperty("TemplateManagement"), 10)) {			  
			// Move cursor to the Main Menu Element  
			builder.moveToElement(mnuElement).perform();		
			// Clicking on the Hidden SubMenu  
			WebElement oldpivt = driver.findElement(By.xpath(properties.getProperty("EmailTemplate")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", oldpivt);		
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicking on Email Templates is successful..");		      
		} else {
			log.logLine(Testname, true, "Clicking on Email Templates is failed");
			throw new Exception("Clicking on Email Templates is failed");			
		}
	}

	public boolean VerifyTemplateingrid(String Testname) throws Exception {	
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
						if(EmlType[i].equals(EmailtypTxt.concat(AccNo))){

							driver.findElement(By.cssSelector("ul "+row+" div[class='grid-btns'] div")).click();
							log.logLine(Testname, false, "Iterating through the Rows....and Reading the Type in \"Saved Types\" as "+EmailtypTxt.concat(AccNo));

							//************************************************Click HTML radio button 
							if (doesElementExist2("label[for='radio-saved-type-2-"+i+"']", 5)) {
								WebElement htmlradio=driver.findElement(By.cssSelector("label[for='radio-saved-type-2-"+i+"']"));
								wait.until(ExpectedConditions.elementToBeClickable(htmlradio));
								Thread.sleep(5000);
								((JavascriptExecutor) driver).executeScript("arguments[0].click()", htmlradio);
								Thread.sleep(5000);
								log.logLine(Testname, false, "Clicked on the \"HTML\" radiobutton of the saved types ");
							}

							//************************************************Click Plus button to add HTML schema
							if (doesElementExist2(properties.getProperty("DeleteTemplatebtn"), 5)) {
								WebElement dltbtn = driver.findElement(By.cssSelector(properties.getProperty("DeleteTemplatebtn")));
								((JavascriptExecutor) driver).executeScript("arguments[0].click()", dltbtn);
								Thread.sleep(5000);
								PleasewaitDisappear();
								log.logLine(Testname, false, "Clicking on Delete button for template");
							} else {
								log.logLine(Testname, true, "Clicking on Delete button for template is failed");
								throw new Exception("Clicking on Delete button for template is failed");
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


	public boolean DeleteTemplateviaadmin(String Testname ) throws Exception {

		if ((doesElementExist2(properties.getProperty("Adminlnk"), 5))) {                
			WebElement oldpivt = driver.findElement(By.cssSelector(properties.getProperty("Adminlnk")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", oldpivt);


			log.logLine(Testname, false, "Clicking on Legacy Admin..");              
		}


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
				Thread.sleep(3000);
				driver.manage().window().maximize();


				if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetExplorer"))) {
					if (doesElementExist2(properties.getProperty("OverrideLnk"), 5)) {
						driver.get("javascript:document.getElementById('overridelink').click();");
						Thread.sleep(8000);
					}
				}
			}
		} 


		Thread.sleep(15000);
		if (doesElementExist2(properties.getProperty("EmailLink"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("EmailLink")));

			log.logLine(Testname, false, "Clicking on Email link from the leftContent Menu");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);

			List<WebElement> Lcn = driver.findElements(By.cssSelector(properties.getProperty("EmailLink")));
			for (WebElement btn:Lcn) {
				if (btn.getText().equals("EMail")) {
					Thread.sleep(5000);
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Clicking on Email link from the leftContent Menu");
					break;
				}
			}

		} else {
			log.logLine(Testname, true, "Unable to click on the Email link from the leftContent Menu");
			//    throw new Exception("Unable to click on the Email link from the leftContent Menu");
		}
		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("EmailBuilderLinkT"), 5)) {
			WebElement emlblder = driver.findElement(By.cssSelector(properties.getProperty("EmailBuilderLinkT")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", emlblder);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on EmailBuilder option from the Email menu list ");

			Thread.sleep(2000);

		} else if (doesElementExist2(properties.getProperty("EmailBuilderLinkS"), 5)) {  
			WebElement emlblder = driver.findElement(By.cssSelector(properties.getProperty("EmailBuilderLinkS")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", emlblder);
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on EmailBuilder option from the Email menu list ");

		} else if (doesElementExist2(properties.getProperty("EmailBuilderLinkP"), 5)) {  
			WebElement emlblder = driver.findElement(By.cssSelector(properties.getProperty("EmailBuilderLinkP")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", emlblder);
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on EmailBuilder option from the Email menu list ");   	

		}else {
			log.logLine(Testname, true, "Unable to Click on EmailBuilder option from the Email menu list ");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Unable to Click on EmailBuilder option from the Email menu list ");
		}

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("EmailTypes"), 5)) {	    
			driver.findElement(By.cssSelector(properties.getProperty("EmailTypes")));
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
		}else 	{
			log.logLine(Testname, true, "Unable to Click on Email Types option of the Email Builder list");
			//    throw new Exception("Unable to Click on Email Types option of the Email Builder list");
		}


		SelectAppEmailTypeCreation(Testname , TemplateName);
		
		Thread.sleep(15000);
		String[] ProcessType = new String[100];
		String row1 = "tr";
		List<WebElement> DataCnt1= driver.findElements(By.xpath(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_dgViewEmailTypes']/tbody/tr"));

		Thread.sleep(2000);
		if(doesElementExist2(properties.getProperty("EmailTypeHeader"), 5)){
			for(int j = 0; j < DataCnt1.size(); j++) {
				ProcessType[j] = driver.findElement(By.cssSelector("div table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_dgViewEmailTypes'] tbody "+row1+" td")).getText();

				if(ProcessType[j].equals(EmailtypTxt.concat(AccNo))){

					log.logLine(Testname, false, "Iterating through the Rows of the Email Type list....and reading the Type in Email Type  as "+ProcessType[j]);

					WebElement delete=driver.findElement(By.cssSelector("div table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_dgViewEmailTypes'] tbody "+row1+" td+td+td+td+td a"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", delete);
					Thread.sleep(5000);
					log.logLine(Testname, false, "Deleted the Template created "+EmailtypTxt.concat(AccNo)+"in admin section");
					break;
				}
				row1 = row1 + "+tr";
			}

		}

		Thread.sleep(5000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(2000);


		driver.close();

		driver.switchTo().window(firstWinHandle);



		return true;
	}

	public boolean ImageAdmin(String Testname ) throws Exception {

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("EmailLink"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("EmailLink")));

			log.logLine(Testname, false, "Clicking on Email link from the leftContent Menu");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);

			List<WebElement> Lcn = driver.findElements(By.cssSelector(properties.getProperty("EmailLink")));
			for (WebElement btn:Lcn) {
				if (btn.getText().equals("EMail")) {
					Thread.sleep(2000);
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Clicking on Email link from the leftContent Menu");
					break;
				}
			}

		} else {
			log.logLine(Testname, true, "Unable to click on the Email link from the leftContent Menu");
			//    throw new Exception("Unable to click on the Email link from the leftContent Menu");
		}

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("EmailSender"), 5)) {
			WebElement emlblder = driver.findElement(By.cssSelector(properties.getProperty("EmailSender")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", emlblder);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on EmailSender option from the Email menu list ");
		}
		else {
			log.logLine(Testname, true, "Clicking on EmailSender option from the Email menu list is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on EmailSender option from the Email menu list is failed");
		}

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("Configuration"), 5)) {
			WebElement emlblder = driver.findElement(By.cssSelector(properties.getProperty("Configuration")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", emlblder);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on Configuration option from the EmailSender");
		}
		else {
			log.logLine(Testname, true, "Clicking on Configuration option from the EmailSender is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Configuration option from the EmailSender is failed");
		}


		String row="tr";
		for(int i=1;i<=5;i++){

			Thread.sleep(5000);

			if (doesElementExist2(properties.getProperty("Imageoption"), 5)) {	    
				driver.findElement(By.cssSelector(properties.getProperty("Imageoption")));
				log.logLine(Testname, false, "Selecting the Imageoption of the Email Sender list");
				List<WebElement> lsteml = driver.findElements(By.cssSelector(properties.getProperty("Imageoption")));

				for (WebElement btn:lsteml) {
					if (btn.getText().equalsIgnoreCase("Image Admin")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
						log.logLine(Testname, false, "Clicking on Image Admin option of the Email Sender list");
						break;
					}
				}
			}else 	{
				log.logLine(Testname, true, "Unable to Click on Image Admin option of the Email Sender list");
				driver.switchTo().defaultContent();
				throw new Exception("Unable to Click on Email Types option of the Email Builder list");
			}

			Thread.sleep(10000);

			if (doesElementExist2(properties.getProperty("AddImage"), 5)) {
				WebElement eDelive = driver.findElement(By.cssSelector(properties.getProperty("AddImage")));
				//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",eDelive);
				//Thread.sleep(2000);
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
				log.logLine(Testname, false, "Click on AddImage button is Successful");
			} else {
				log.logLine(Testname, true, "Click on AddImage button is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Click on AddImage button is failed");
			}

			Thread.sleep(15000);
			Actions action = new Actions(driver);
			if (doesElementExist2(("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_dgImages'] tbody tr+tr+"+row+" td input[class='Button']"), 5)) {
				WebElement eDelive = driver.findElement(By.cssSelector(("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_dgImages'] tbody tr+tr+"+row+" td input[class='Button']")));
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",eDelive);
				Thread.sleep(2000);
				action.click(eDelive).perform();
				//((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);
				log.logLine(Testname, false, "Click on Browse button is Successful");
			} else {
				log.logLine(Testname, true, "Click on Browse button is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Click on Browse button is failed");
			}
			/*
			Thread.sleep(5000);
			Robot robot=new Robot();
			robot.keyPress(i);
			Thread.sleep(2000);
			robot.keyRelease(i);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);*/


			StringSelection ss = new StringSelection("C:\\Pivot_Portal\\Test Data\\Template\\"+i);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
			Robot robot=new Robot();
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);


			Thread.sleep(5000);
			if (doesElementExist2(("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_dgImages'] tbody tr+tr+"+row+" td+td+td+td+td+td a"), 5)) {
				WebElement eDelive = driver.findElement(By.cssSelector("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_dgImages'] tbody tr+tr+"+row+" td+td+td+td+td+td a"));
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",eDelive);
				Thread.sleep(15000);
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
				log.logLine(Testname, false, "Click on Update button is Successful");
			} else {
				log.logLine(Testname, true, "Click on Update button is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Click on Update button is failed");
			}
			row = row + "+tr";

		}


		return true;
	}

	public boolean ClickPlusButtononHTMLtemplate(String Testname ) throws Exception {

		for(int i=6;i<=10;i++){
			SavedTypesDropDown(Testname);

			if (doesElementExist((".//*[@id='ul-management-saved-types']/li[1]/div[1]/label[3]"), 5)) {
				WebElement Htmlbtn = driver.findElement(By.xpath((".//*[@id='ul-management-saved-types']/li[1]/div[1]/label[3]")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", Htmlbtn);	 
				log.logLine(Testname, false, "Click on HTML radio button is Successful");
			} else {
				log.logLine(Testname, true, "Click on HTML radio button is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Click on HTML radio radio button is failed");
			}

			if (doesElementExist2(properties.getProperty("AddbuttonHTML"), 5)) {
				WebElement Htmlbtn = driver.findElement(By.cssSelector(properties.getProperty("AddbuttonHTML")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", Htmlbtn);	 
				log.logLine(Testname, false, "Click on Add Add HTML button is Successful");
			} else {
				log.logLine(Testname, true, "Click on Add HTML button is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Click on Add HTML button is failed");
			}


			Thread.sleep(5000);
			if (doesElementExist2(properties.getProperty("UploadImage"), 5)) {
				WebElement Htmlbtn = driver.findElement(By.cssSelector(properties.getProperty("UploadImage")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", Htmlbtn);	 
				log.logLine(Testname, false, "Click on Upload Image button is Successful");
			} else {
				log.logLine(Testname, true, "Click on Upload Image button is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Click on Upload Image button is failed");
			}




			if (doesElementExist2(properties.getProperty("Addimagetolst"), 5)) {
				WebElement Htmlbtn = driver.findElement(By.cssSelector(properties.getProperty("Addimagetolst")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", Htmlbtn);	 
				log.logLine(Testname, false, "Click on Add image to list button is Successful");
			} else {
				log.logLine(Testname, true, "Click on Add image to list button is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Click on Add image to list button is failed");
			}

			Thread.sleep(2000);
			if (doesElementExist2(properties.getProperty("Imagenametxtbox"), 5)) {
				WebElement imgnme = driver.findElement(By.cssSelector(properties.getProperty("Imagenametxtbox")));
				imgnme.sendKeys("A"+i);
				//	((JavascriptExecutor) driver).executeScript("arguments[0].click()", Htmlbtn);	 
				log.logLine(Testname, false, "Click on Upload Image button is Successful");
			} else {
				log.logLine(Testname, true, "Click on Upload Image button is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Click on Upload Image button is failed");
			}

			if (doesElementExist2(properties.getProperty("Imagename"), 5)) {
				WebElement brwsbtn = driver.findElement(By.cssSelector(properties.getProperty("Imagename")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", brwsbtn);	 
				log.logLine(Testname, false, "Click on Browse image button is Successful");
			} else {
				log.logLine(Testname, true, "Click on Browse image button is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Click on Browse image button is failed");
			}


			Thread.sleep(5000);
			Actions builder = new Actions(driver);	  
			if (doesElementExist2(properties.getProperty("Browseimagebtn"), 5)) {
				WebElement brwsbtn = driver.findElement(By.cssSelector(properties.getProperty("Browseimagebtn")));
				//brwsbtn.click();
				builder.doubleClick(brwsbtn).perform();
				//((JavascriptExecutor) driver).executeScript("arguments[0].click()", brwsbtn);	 
				log.logLine(Testname, false, "Click on Browse image button is Successful");
			} else {
				log.logLine(Testname, true, "Click on Browse image button is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Click on Browse image button is failed");
			}


			Thread.sleep(5000);

			StringSelection ss = new StringSelection("C:\\Pivot_Portal\\Test Data\\Template\\"+i);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
			Robot robot=new Robot();
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);


			Thread.sleep(2000);
			if (doesElementExist2(properties.getProperty("Addbutton"), 5)) {
				WebElement Addbtn = driver.findElement(By.cssSelector(properties.getProperty("Addbutton")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", Addbtn);	 
				log.logLine(Testname, false, "Click on Add image button to upload image is Successful");
			} else {
				log.logLine(Testname, true, "Click on Add image button to upload image is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Click on Add image button to upload image is failed");
			}

			Thread.sleep(5000);
			Alert alert = driver.switchTo().alert();
			alert.accept();
			Thread.sleep(2000);

			if (doesElementExist2(properties.getProperty("Closebutton"), 5)) {
				WebElement Addbtn = driver.findElement(By.cssSelector(properties.getProperty("Closebutton")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", Addbtn);	 
				log.logLine(Testname, false, "Click on Close button  is Successful");
			} else {
				log.logLine(Testname, true, "Click on Close button is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Click on Close button is failed");
			}


			driver.switchTo().defaultContent();

			Thread.sleep(5000);
			Actions action = new Actions(driver);	        
			WebElement mnuElement = driver.findElement(By.xpath(properties.getProperty("TemplateManagement")));	
			if (doesElementExist(properties.getProperty("TemplateManagement"), 10)) {			  
				// Move cursor to the Main Menu Element  
				action.moveToElement(mnuElement).perform();		
				// Clicking on the Hidden SubMenu  
				WebElement oldpivt = driver.findElement(By.xpath(properties.getProperty("EmailTemplate")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", oldpivt);		
				Thread.sleep(4000);
				log.logLine(Testname, false, "Clicking on Email Templates is successful..");		      
			} else {
				log.logLine(Testname, true, "Clicking on Email Templates is failed");
				throw new Exception("Clicking on Email Templates is failed");			
			}


			driver.switchTo().frame("iframeContainer");

		}		



		return true;
	}


	public boolean Linkidduplicity(String Testname ) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		String Tempnme = test.readColumnData("TempName", sheetname);
		String Descnme = test.readColumnData("TempDesc", sheetname);
		String DupTempdesc = test.readColumnData("DuplicateTempdesc", sheetname);


		SavedTypesDropDown(Testname);
		
		Thread.sleep(5000);

		ClickPlusButtontoAddHTMLTemplate(Testname);
		
		Thread.sleep(8000);
		if (doesElementExist2(properties.getProperty("TempltNameTxtBox"), 5)) {
			WebElement qtyday = driver.findElement(By.cssSelector(properties.getProperty("TempltNameTxtBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", qtyday);
			Thread.sleep(2000);
			qtyday.sendKeys(Tempnme);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entering the text as "+Tempnme+" in Template Name textbox");
		} else {
			log.logLine(Testname, true, "Entering the text as "+Tempnme+" in Template Name textbox is failed");
			throw new Exception("Entering the text as "+Tempnme+" in Template Name textbox is failed");
		}
		
		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("Linktrackingchkbox"), 5)) {
	  	    WebElement lnkchkbx = driver.findElement(By.cssSelector(properties.getProperty("Linktrackingchkbox")));
	  	   
	  	    	if (!lnkchkbx.isSelected())
	  		     {
	  	    		lnkchkbx.click();
	  	    		Thread.sleep(2000);
	  	    		log.logLine(Testname, false, "RRD Data Receipt Date Checkbox is Checked");
	  	    	}else{
	  		    	log.logLine(Testname, false, "RRD Data Receipt Date Checkbox is already Checked");
	  	    	}
		} else {
			log.logLine(Testname, true, "Click on Link Tracking Checkbox is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Link Tracking Checkbox is failed");
		}
		

		if (doesElementExist2(properties.getProperty("HTMLTag"), 5)) {
			WebElement lnkchkbx = driver.findElement(By.cssSelector(properties.getProperty("HTMLTag")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnkchkbx);	 
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on HTML Tag is Successful");
		} else {
			log.logLine(Testname, true, "Click on HTML Tag is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on HTML Tag is failed");
		}
		

		if (doesElementExist2(properties.getProperty("HTMLDesc"), 5)) {
			WebElement HTMLdesc = driver.findElement(By.cssSelector(properties.getProperty("HTMLDesc")));
			HTMLdesc.clear();
			HTMLdesc.sendKeys(Descnme);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entering the html as "+Descnme+" in View Htmltextbox is successful ");
		} else {
			log.logLine(Testname, true, "Entering the html as "+Descnme+" in View Htmltextbox is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the html as "+Descnme+" in View Htmltextbox is failed");
		}

		if (doesElementExist2(properties.getProperty("ViewHTMLUpdatebtn"), 5)) {
			WebElement Updtebtn = driver.findElement(By.cssSelector(properties.getProperty("ViewHTMLUpdatebtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Updtebtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on Update button on View Html pop up is Successful");
		} else {
			log.logLine(Testname, true, "Update button on View Html pop up is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Update button on View Html pop up is failed");
		}


		SaveTemplate(Testname);
		Thread.sleep(5000);
		ConfirmSave(Testname) ;
		Thread.sleep(5000);
		
		
		if (doesElementExist2(properties.getProperty("HTMLTag"), 5)) {
			WebElement lnkchkbx = driver.findElement(By.cssSelector(properties.getProperty("HTMLTag")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnkchkbx);	 
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on HTML Tag is Successful");
		} else {
			log.logLine(Testname, true, "Click on HTML Tag is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on HTML Tag is failed");
		}


		if (doesElementExist2(properties.getProperty("HTMLDesc"), 5)) {
			WebElement HTMLdesc = driver.findElement(By.cssSelector(properties.getProperty("HTMLDesc")));
			HTMLdesc.clear();
			HTMLdesc.sendKeys(DupTempdesc);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entering the html as "+DupTempdesc+" in View Htmltextbox is successful ");
		} else {
			log.logLine(Testname, true, "Entering the html as "+DupTempdesc+" in View Htmltextbox is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the html as "+DupTempdesc+" in View Htmltextbox is failed");
		}
		

		if (doesElementExist2(properties.getProperty("ViewHTMLUpdatebtn"), 5)) {
			WebElement Updtebtn = driver.findElement(By.cssSelector(properties.getProperty("ViewHTMLUpdatebtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Updtebtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on Update button on View Html pop up is Successful");
		} else {
			log.logLine(Testname, true, "Update button on View Html pop up is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Update button on View Html pop up is failed");
		}


		SaveTemplate(Testname);
		Thread.sleep(6000);

		Alert alert = driver.switchTo().alert();
		String Text=alert.getText();
		Thread.sleep(2000);
		log.logLine(Testname, false, "Reading the message as: "+Text+" from alert pop up");

		alert.accept();
		
		Thread.sleep(5000);
	
		
		if (doesElementExist2(properties.getProperty("DelTmpltebtn"), 5)) {
			WebElement Htmlbtn = driver.findElement(By.cssSelector(properties.getProperty("DelTmpltebtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Htmlbtn);	 
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on Delete button On Html template is Successful");
		} else {
			log.logLine(Testname, true, "Click on Delete button On Html template is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Delete button On Html template is failed");
		}
		
		Thread.sleep(2000);
		ClickOKToConfirmDelete(Testname);
		
		Thread.sleep(6000);
		if (doesElementExist2(properties.getProperty("Confirmdelte"), 5)) {
			WebElement del = driver.findElement(By.cssSelector(properties.getProperty("Confirmdelte")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", del);	    	 
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Click on Close button for confirm delete pop up is successfull");
		} else {
			log.logLine(Testname, true, "Click on Close button for confirm delete pop up is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Close button for confirm delete pop up is failed");
		}

		Thread.sleep(5000);
		SavedTypesDropDown(Testname);

		return true;
	}


	public boolean Linkidwithduplicity(String Testname ) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		String Tempnme = test.readColumnData("TempName", sheetname);
		String DupTempdesc = test.readColumnData("DuplicateTempdesc", sheetname);


		SavedTypesDropDown(Testname);

		Thread.sleep(5000);
		ClickPlusButtontoAddHTMLTemplate(Testname);

		
		if (doesElementExist2(properties.getProperty("Linktrackingchkbox"), 5)) {
	  	    WebElement lnkchkbx = driver.findElement(By.cssSelector(properties.getProperty("Linktrackingchkbox")));
	  	   
	  	    	if (!lnkchkbx.isEnabled())
	  		     {
	  	    		lnkchkbx.click();
	  	    		Thread.sleep(2000);
	  	    		log.logLine(Testname, false, "Click on Link Tracking Checkbox is Checked");
	  	    	}else{
	  		    	log.logLine(Testname, false, "Click on Link Tracking Checkbox is already Checked");
	  	    	}
		} else {
			log.logLine(Testname, true, "Click on Link Tracking Checkbox is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Link Tracking Checkbox is failed");
		}
	 	    


		if (doesElementExist2(properties.getProperty("HTMLTag"), 5)) {
			WebElement lnkchkbx = driver.findElement(By.cssSelector(properties.getProperty("HTMLTag")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnkchkbx);	 
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on HTML Tag is Successful");
		} else {
			log.logLine(Testname, true, "Click on HTML Tag is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on HTML Tag is failed");
		}


		if (doesElementExist2(properties.getProperty("HTMLDesc"), 5)) {
			WebElement HTMLdesc = driver.findElement(By.cssSelector(properties.getProperty("HTMLDesc")));
			HTMLdesc.clear();
			HTMLdesc.sendKeys(DupTempdesc);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entering the html as "+DupTempdesc+" in View Htmltextbox is successful ");
		} else {
			log.logLine(Testname, true, "Entering the html as "+DupTempdesc+" in View Htmltextbox is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the html as "+DupTempdesc+" in View Htmltextbox is failed");
		}

		if (doesElementExist2(properties.getProperty("ViewHTMLUpdatebtn"), 5)) {
			WebElement Updtebtn = driver.findElement(By.cssSelector(properties.getProperty("ViewHTMLUpdatebtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Updtebtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on Update button on View Html pop up is Successful");
		} else {
			log.logLine(Testname, true, "Update button on View Html pop up is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Update button on View Html pop up is failed");
		}


		SaveTemplate(Testname);
		Thread.sleep(6000);

		Alert alert = driver.switchTo().alert();
		String Text=alert.getText();
		Thread.sleep(2000);
		log.logLine(Testname, false, "Reading the message as: "+Text+" from alert pop up");

		alert.accept();
		
		Thread.sleep(5000);
		ClickExitEditorButton(Testname); 
		Thread.sleep(5000);      

		
		
		if (doesElementExist2(properties.getProperty("DeleteTemplatebtn"), 5)) {
			WebElement Htmlbtn = driver.findElement(By.cssSelector(properties.getProperty("DeleteTemplatebtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Htmlbtn);	 
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on Edit button On Html template is Successful");
		} else {
			log.logLine(Testname, true, "Click on Edit button On Html template is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Edit button On Html template is failed");
		}
		
		Thread.sleep(2000);
		ClickOKToConfirmDelete(Testname);
		
		Thread.sleep(6000);
		if (doesElementExist2(properties.getProperty("Confirmdelte"), 5)) {
			WebElement del = driver.findElement(By.cssSelector(properties.getProperty("Confirmdelte")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", del);	    	 
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Click on Close button for confirm delete pop up is successfull");
		} else {
			log.logLine(Testname, true, "Click on Close button for confirm delete pop up is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Close button for confirm delete pop up is failed");
		}

		Thread.sleep(5000);
		SavedTypesDropDown(Testname);

		return true;
	}




	public boolean DeleteImages(String Testname ) throws Exception {

		Thread.sleep(5000);

		SavedTypesDropDown(Testname);

		if (doesElementExist((".//*[@id='ul-management-saved-types']/li[1]/div[1]/label[3]"), 5)) {
			WebElement Htmlbtn = driver.findElement(By.xpath((".//*[@id='ul-management-saved-types']/li[1]/div[1]/label[3]")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Htmlbtn);	 
			log.logLine(Testname, false, "Click on HTML radio button is Successful");
		} else {
			log.logLine(Testname, true, "Click on HTML radio button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on HTML radio radio button is failed");
		}

		if (doesElementExist2(properties.getProperty("AddbuttonHTML"), 5)) {
			WebElement Htmlbtn = driver.findElement(By.cssSelector(properties.getProperty("AddbuttonHTML")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Htmlbtn);	 
			log.logLine(Testname, false, "Click on Add Add HTML button is Successful");
		} else {
			log.logLine(Testname, true, "Click on Add HTML button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Add HTML button is failed");
		}


		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("UploadImage"), 5)) {
			WebElement Htmlbtn = driver.findElement(By.cssSelector(properties.getProperty("UploadImage")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Htmlbtn);	 
			log.logLine(Testname, false, "Click on Upload Image button is Successful");
		} else {
			log.logLine(Testname, true, "Click on Upload Image button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Upload Image button is failed");
		}

		Thread.sleep(5000);
		for(int i=1;i<=10;i++){
			if (doesElementExist2(properties.getProperty("Deletebutton"), 5)) {
				WebElement Dltebtn = driver.findElement(By.cssSelector(properties.getProperty("Deletebutton")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", Dltebtn);	
				Thread.sleep(5000);
				log.logLine(Testname, false, "Click on Delete button  is Successful");
			} else {
				log.logLine(Testname, true, "Click on Delete button is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Click on Delete button is failed");
			}

			Alert alert = driver.switchTo().alert();
			alert.accept();

			Thread.sleep(2000);

			if (doesElementExist2(properties.getProperty("Alertclose"), 5)) {
				WebElement Dltebtn = driver.findElement(By.cssSelector(properties.getProperty("Alertclose")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", Dltebtn);	
				Thread.sleep(5000);
				log.logLine(Testname, false, "Click on close button for alert popup is Successful");
			} else {
				log.logLine(Testname, true, "Click on close button for alert popup is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Click on close button for alert popup is failed");
			}

		}
		return true;

	}


	public boolean Closeandrefresh(String Testname ) throws Exception {

		Thread.sleep(2000);

		if (doesElementExist2(properties.getProperty("Closebutton"), 5)) {
			WebElement Addbtn = driver.findElement(By.cssSelector(properties.getProperty("Closebutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Addbtn);	 
			log.logLine(Testname, false, "Click on Close button  is Successful");
		} else {
			log.logLine(Testname, true, "Click on Close button is failed");
			driver.switchTo().defaultContent();
			//throw new Exception("Click on Close button is failed");
		}


		driver.switchTo().defaultContent();

		Thread.sleep(5000);
		Actions action = new Actions(driver);	        
		WebElement mnuElement = driver.findElement(By.xpath(properties.getProperty("TemplateManagement")));	
		if (doesElementExist(properties.getProperty("TemplateManagement"), 10)) {			  
			// Move cursor to the Main Menu Element  
			action.moveToElement(mnuElement).perform();		
			// Clicking on the Hidden SubMenu  
			WebElement oldpivt = driver.findElement(By.xpath(properties.getProperty("EmailTemplate")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", oldpivt);		
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicking on Email Templates is successful..");		      
		} else {
			log.logLine(Testname, true, "Clicking on Email Templates is failed");
			throw new Exception("Clicking on Email Templates is failed");			
		}


		driver.switchTo().frame("iframeContainer");

		return true;
	}

} //closing the class






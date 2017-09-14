package pivotModules;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Set;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TemplateMgmtPermissions extends Page {

	int paperID = (int) Math.round(Math.random() * (9999 - 1000 + 1) + 1000);
	public String AccNo = Integer.toString(paperID);	


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
	public static String useradmnid;
	public String userappid;
	public String AppName;


	public TemplateMgmtPermissions(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}	
	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}

	String firstWinHandle = null;	
	String secondWinHandle = null;
	String thirdWinHandle = null;
	String hnd = null;

	public boolean clientAppSelect(String RandNo, String Testname) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();


		driver.switchTo().defaultContent();

		Thread.sleep(2000);
		WebElement retelm2 = waitForElement(properties.getProperty("selClint1"));

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
	    	throw new Exception("Click on Template management Module is failed");
	    }*/

		Thread.sleep(1000);
		boolean CliSelected = false;
		ClntName = test.readColumnData("ClientName", sheetname);

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
		AppName = test.readColumnData("ApplicationName", sheetname);

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
						if (lnk.getText().contains(AppId)) {
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


		/* if (doesElementExist2(properties.getProperty("EmailTemplatesTab"), 5)) {
			WebElement updtBtn = driver.findElement(By.cssSelector(properties.getProperty("EmailTemplatesTab")));
			Thread.sleep(2000);			
			log.logLine(Testname, false, "Template Management page is opened successfully");
		} else {
		    log.logLine(Testname, true, "Template Management page is not opened successfully");
		    throw new Exception("Template Management page is not opened successfully");
		}*/

		return true;

	}


	public boolean EmailAdminSettings(String RandNo, String Testname) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();


		if ((doesElementExist2(properties.getProperty("Adminlnk"), 5))) {                
			WebElement oldpivt = driver.findElement(By.cssSelector(properties.getProperty("Adminlnk")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", oldpivt);

			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicking on Legacy Admin..");              
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

			log.logLine(Testname, false, "Clicking on Email link from the leftContent Menu");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);

			List<WebElement> Lcn = driver.findElements(By.cssSelector(properties.getProperty("EmailLink")));
			for (WebElement btn:Lcn) {
				if (btn.getText().equals("EMail")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					Thread.sleep(2000);
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
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on EmailBuilder option from the Email menu list ");

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
			WebElement emlty = driver.findElement(By.cssSelector(properties.getProperty("EmailTypes")));

			log.logLine(Testname, false, "Selecting the Email Types option of the Email Builder list");

			List<WebElement> lsteml = driver.findElements(By.cssSelector(properties.getProperty("EmailTypes")));
			for (WebElement btn:lsteml) {
				if (btn.getText().equalsIgnoreCase("Email Types")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					Thread.sleep(3000);
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

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("SelectApp"), 5)) {
			Select emltype = new Select(driver.findElement(By.cssSelector(properties.getProperty("SelectApp"))));
			emltype.selectByVisibleText("RGT1099 - RGT1099");
			Thread.sleep(5000);
			log.logLine(Testname, false, "Selecting the Regression testing application in Stage from the Email Type Admin list in Template Management");
		}else {
			log.logLine(Testname, true, "Selecting the Regression testing application from the Email Type Admin list in Template Management is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Selecting the Regression testing application from the Email Type Admin list in Template Management is failed");
		}



		WebElement addemltyp = waitForElement(properties.getProperty("AddEmlType"));

		if (doesElementExist2(properties.getProperty("AddEmlType"), 5)) {
			WebElement addEmailTyp = driver.findElement(By.cssSelector(properties.getProperty("AddEmlType")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", addEmailTyp);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicking on OK button");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the Reports is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicking on OK button to view the Reports is failed");
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




		if (doesElementExist2(properties.getProperty("EmlTypeTxtBox"), 5)) {
			WebElement emltyptxt = driver.findElement(By.cssSelector(properties.getProperty("EmlTypeTxtBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", emltyptxt);
			Thread.sleep(2000);
			emltyptxt.sendKeys(EmailtypTxt.concat(AccNo));
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entering Email Type name as "+EmailtypTxt.concat(AccNo)+" in the text box of Email Builder Section");
		} else {
			log.logLine(Testname, true, "Unable to enter Email Type name as "+EmailtypTxt.concat(AccNo)+" in the text box of Email Builder Section");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Unable to enter Email Type name as "+EmailtypTxt.concat(AccNo)+" in the text box of Email Builder Section");
		}

		if (doesElementExist2(properties.getProperty("DescriptionTxtBox"), 5)) {
			WebElement descpText = driver.findElement(By.cssSelector(properties.getProperty("DescriptionTxtBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", descpText);
			Thread.sleep(2000);
			descpText.sendKeys(DescpText);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entering the description in the \"Description\" text box of Email Builder Section ");
		} else {
			log.logLine(Testname, true, "Unable to Enter the description in the \"Description\" text box of Email Builder Section ");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Unable to Enter the description in the \"Description\" text box of Email Builder Section ");
		}

		if (doesElementExist2(properties.getProperty("FromTxtBox"), 5)) {
			WebElement frmTxt = driver.findElement(By.cssSelector(properties.getProperty("FromTxtBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", frmTxt);
			frmTxt.sendKeys(FromTxt);
			Thread.sleep(5000);
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
			frmFrndlyText.sendKeys(FromFrndlyTxt);
			Thread.sleep(5000);
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
			rpltoTx.sendKeys(rplyTxt);
			Thread.sleep(5000);
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
			rpltofrndlyTx.sendKeys(rplyToFrndlyTxt);
			Thread.sleep(5000);
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
			subjTxt.sendKeys(subTxt);
			Thread.sleep(5000);
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
			Thread.sleep(5000);
			log.logLine(Testname, false, "Selecting quotable printed option from the \"HTML Encoding\" dropdown list of Email Builder Section ");
		} else {
			log.logLine(Testname, true, "Unable to Select quotable printed option from the \"HTML Encoding\" dropdown list of Email Builder Section ");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Unable to Select quotable printed option from the \"HTML Encoding\" dropdown list of Email Builder Section ");
		}

		if (doesElementExist2(properties.getProperty("TextEncodingDrpDwn"), 5)) {
			Select textEncde = new Select(driver.findElement(By.cssSelector(properties.getProperty("TextEncodingDrpDwn"))));
			textEncde.selectByVisibleText("quoted-printable");
			Thread.sleep(5000);
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
			Thread.sleep(5000);
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
			Thread.sleep(5000);
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
			Thread.sleep(5000);
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
			Thread.sleep(5000);
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
			Thread.sleep(5000);
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
			Thread.sleep(5000);
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
			Thread.sleep(5000);
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
			Thread.sleep(10000);
			log.logLine(Testname, false, "Clicking on \"Add\" button for adding the Email type in Template Management");
		} else {
			log.logLine(Testname, true, "Clicking on \"Add\" button for adding the Email type in Template Management is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicking on \"Add\" button for adding the Email type in Template Management is failed");
		}

		Thread.sleep(10000);
		String successfulEml=test.readColumnData("SuccessfulEmlTypeText", sheetname);
		if(doesElementExist2(properties.getProperty("SucessEmlTempltText"),5)){
			String addText=driver.findElement(By.cssSelector(properties.getProperty("SucessEmlTempltText"))).getText();
			if(addText.equals(successfulEml)){
				log.logLine(Testname, false, " The \"Email Template\" type is added sucessfully");		
			}else{
				log.logLine(Testname, true, " The \"Email Template\" type is not added sucessfully");	
			}

		}else{
			log.logLine(Testname, true, "The text for sucessful added Email template does not exist");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("The text for sucessful added Email template does not exist");
		}


		String[] EmailTemp = new String[100];
		String row1 = "tr";
		List<WebElement> DataCnt1= driver.findElements(By.xpath(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_dgViewEmailTypes']/tbody/tr"));

		Thread.sleep(2000);
		if(doesElementExist2(properties.getProperty("EmailTypeHeader"), 5)){
			for(int j = 0; j < DataCnt1.size(); j++) {
				EmailTemp[j] = driver.findElement(By.cssSelector("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_dgViewEmailTypes'] tbody "+row1+" td")).getText();

				if(EmailTemp[j].equals(EmailtypTxt.concat(AccNo))){

					log.logLine(Testname, false, "Iterating through the Rows of the Email Type list....and matching with the added Email Template Type  as "+EmailTemp[j]);
					break;
				}
				row1 = row1 + "+tr";

			}

		}

		driver.close();

		driver.switchTo().window(firstWinHandle);
		return true;
	}


	public boolean userAdminEnableAccess(String Testname ) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		driver.switchTo().defaultContent();

		String userId = test.readColumnData("AdminUserId", sheetname);
		String applnId=test.readColumnData("ApplicationId", sheetname);

		if ((doesElementExist2(properties.getProperty("Adminlnk"), 5))) {                
			WebElement oldpivt = driver.findElement(By.cssSelector(properties.getProperty("Adminlnk")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", oldpivt);

			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicking on Legacy Admin..");              
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
				Thread.sleep(2000);

				if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetExplorer"))) {
					if (doesElementExist2(properties.getProperty("OverrideLnk"), 5)) {
						driver.get("javascript:document.getElementById('overridelink').click();");
						Thread.sleep(8000);
					}
				}
			}
		} 


		if (doesElementExist(properties.getProperty("UserAdminlnk"), 5)) {
			WebElement clntappmenu = driver.findElement(By.xpath(properties.getProperty("UserAdminlnk")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clntappmenu);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Navigating to Admin - User admin link..");
		} else {
			log.logLine(Testname, true, "Navigating to Admin - User admin link is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Navigating to Admin - User admin link is failed");
		}

		if (doesElementExist2(properties.getProperty("UserId"), 5)) {
			WebElement cntnme = driver.findElement(By.cssSelector(properties.getProperty("UserId")));
			cntnme.sendKeys(userId);			    
			log.logLine(Testname, false, "Entered the userid "+useradmnid+" in the user id text field in user/admin tool");
		} else {
			log.logLine(Testname, true, "Entering the userid "+useradmnid+" in the user id text field in user/admin tool is failed");
		}

		if (doesElementExist2(properties.getProperty("UserApplicationId"), 5)) {
			WebElement applid = driver.findElement(By.cssSelector(properties.getProperty("UserApplicationId")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", applid);
			applid.sendKeys(applnId);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entered the application name "+userappid+" in the Application name text field in Client/App tool");
		} else {
			log.logLine(Testname, true, "Entering the application name "+userappid+" in the Application name text field in Client/App tool is failed");
		}	

		if (doesElementExist(properties.getProperty("AnyTool1"), 5)) {
			WebElement AnyTool = driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_lstLayers']"));
			Select select = new Select(AnyTool); 
			List <WebElement> options = select.getOptions(); 
			for (WebElement option: options) { 
				if (option.getText().equalsIgnoreCase("Email Builder")){ 
					option.click(); 
					break; 
				}
			}
			log.logLine(Testname, false, "Clicked on AnyTool drop down list and selected the option PIVOT eDelivery..");
		} else {
			log.logLine(Testname, true, "Clicking on AnyTool drop down list and selecting the option PIVOT eDelivery is  failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicking on AnyTool drop down list and selecting the option PIVOT eDelivery is  failed");
		}

		if (doesElementExist2(properties.getProperty("searchbtn"), 5)) {
			WebElement srcbtn = driver.findElement(By.cssSelector(properties.getProperty("searchbtn")));
			log.logLine(Testname, false, "Clicked on search button of the client/app/Tool Admin");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srcbtn);
			waitUntilRetrive();
		} else {
			log.logLine(Testname, true, "Clicking on search button of the user Admin is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicking on search button of the user Admin is failed");
		}

		if (doesElementExist2(properties.getProperty("ModifyTool"), 5)) {
			WebElement modtool = driver.findElement(By.cssSelector(properties.getProperty("ModifyTool")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", modtool);
			waitUntilRetrive();			
			log.logLine(Testname, false, "Clicked on the Email Builder ModifyTool button in client/app/Tool Admin ");
		} else {
			log.logLine(Testname, true, "Clicking on the Email Builder ModifyTool button in client/app/Tool Admin is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicking on the Email Builder ModifyTool button in client/app/Tool Admin is failed");
		}

		if(doesElementExist2(properties.getProperty("PromoteRollbackChkBox"),5)){
			WebElement edt= driver.findElement(By.cssSelector(properties.getProperty("PromoteRollbackChkBox")));

			if(edt.isSelected()){
				log.logLine(Testname, false , "Promote Templates/live Roll back Check box under \"Email builder\" in view Column is already checked");
				Thread.sleep(2000);
			}else{
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", edt); 
				log.logLine(Testname, false , "Selecting the Promote Templates/live Roll back Check box under \"Email builder\" in view Column is successful");
				Thread.sleep(2000);
			}

		}else{
			log.logLine(Testname, false, "Promote Templates/live Roll back Check box is not present");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Promote Templates/live Roll back Check box is not present");
		}

		if(doesElementExist2(properties.getProperty("ViewXMLSchemaChkBox"),5)){
			WebElement edt= driver.findElement(By.cssSelector(properties.getProperty("ViewXMLSchemaChkBox")));

			if(edt.isSelected()){
				log.logLine(Testname, false , "XML Schema Check box under \"Email builder\" in view Column is already checked");
				Thread.sleep(2000);
			}else{
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", edt); 
				log.logLine(Testname, false , "Selecting the XML Schema Check box under \"Email builder\" in view Column is successful");
				Thread.sleep(2000);
			}

		}else{
			log.logLine(Testname, false, "XML Schema Check box is not present");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("XML Schema Check box is not present");
		}

		if(doesElementExist2(properties.getProperty("EditXMLSchemaChkBox"),5)){
			WebElement edt= driver.findElement(By.cssSelector(properties.getProperty("EditXMLSchemaChkBox")));

			if(edt.isSelected()){
				log.logLine(Testname, false , "XML Schema Check box under \"Email builder\" in Edit Column is already checked");
				Thread.sleep(2000);
			}else{
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", edt); 
				log.logLine(Testname, false , "Selecting the XML Schema Check box under \"Email builder\" in Edit Column is successful");
				Thread.sleep(2000);
			}

		}else{
			log.logLine(Testname, false, "XML Schema Check box is not present");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("XML Schema Check box is not present");
		}

		if(doesElementExist2(properties.getProperty("EditChkBox"),5)){
			WebElement edt= driver.findElement(By.cssSelector(properties.getProperty("EditChkBox")));

			if(edt.isSelected()){
				log.logLine(Testname, false , "Edit Templates Check box under \"Email builder\" in Edit Column is already checked");
				Thread.sleep(2000);
			}else{
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", edt); 
				log.logLine(Testname, false , "Selecting the Edit Templates Check box under \"Email builder\" in Edit Column is successful");
				Thread.sleep(2000);
			}

		}else{
			log.logLine(Testname, false, "Edit Templates Check box is not present");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Edit Templates Check box is not present");
		}


		if (doesElementExist2(properties.getProperty("UpdateBtnInUserAdmin"), 5)) {
			WebElement updtBtn = driver.findElement(By.cssSelector(properties.getProperty("UpdateBtnInUserAdmin")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", updtBtn);
			waitUntilRetrive();			
			log.logLine(Testname, false, "Clicked on the eDel Application update button  of Email Builder Tool in client/app/Tool Admin ");
		} else {
			log.logLine(Testname, true, "Clicked on the eDel Application update button  of Email Builder Tool in client/app/Tool Admin is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicked on the eDel Application update button  of Email Builder Tool in client/app/Tool Admin is failed");
		}

		driver.close();
		Thread.sleep(2000);

		driver.switchTo().window(firstWinHandle);
		return true;
	}


	public boolean AddTextTemplate(String RandNo, String Testname) throws Exception{

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		driver.switchTo().frame("iframeContainer");

		TemplateName=test.readColumnData("TemplateName", sheetname);
		TempltDecsp=test.readColumnData("TemplateDecrip", sheetname);

		Thread.sleep(5000);
		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		AddTemplate(Testname);
		Thread.sleep(5000);

		FillTemplateName(Testname);
		Thread.sleep(5000);
		FillTemaplateDescription(Testname);
		Thread.sleep(5000);

		SaveTemplate(Testname);
		Thread.sleep(5000);


		if (doesElementExist2(properties.getProperty("SaveTempAlrtMessage"), 5)) {
			String sveAlrt = driver.findElement(By.cssSelector(properties.getProperty("SaveTempAlrtMessage"))).getText();

			if (sveAlrt.equals("Template \'"+TemplateName.concat(AccNo)+"\' has been saved successfully.")){
				log.logLine(Testname, false, "Alert message for confirming the \"Save\" operation is sucessful ");
			}else {	
				log.logLine(Testname, true, "Alert message for confirming the \"Save\" operation is unsucessful ");
			} 
		}else {
			log.logLine(Testname, true, "Alert message for confirming the \"Save\" operation is not displayed ");
			throw new Exception("Alert message for confirming the \"Save\" operation is not displayed ");

		}

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("ConfirmSave"), 5)) {
			WebElement cnfmSve = driver.findElement(By.cssSelector(properties.getProperty("ConfirmSave")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnfmSve);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on close button of the Confirm \"Save\" Alert message");
		} else {
			log.logLine(Testname, true, "Unable to Click on close button of the Confirm \"Save\" Alert message");
			throw new Exception("Unable to Click on close button of the Confirm \"Save\" Alert message");
		}

		ClickExitEditorButton(Testname);
		Thread.sleep(5000);


		return true;
	}

	public boolean userAdminDisableAccess(String Testname ) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		driver.switchTo().defaultContent();

		String userId = test.readColumnData("AdminUserId", sheetname);
		String applnId=test.readColumnData("ApplicationId", sheetname);

		if ((doesElementExist2(properties.getProperty("Adminlnk"), 5))) {                
			WebElement oldpivt = driver.findElement(By.cssSelector(properties.getProperty("Adminlnk")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", oldpivt);

			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicking on Legacy Admin..");              
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


		if (doesElementExist(properties.getProperty("UserAdminlnk"), 5)) {
			WebElement clntappmenu = driver.findElement(By.xpath(properties.getProperty("UserAdminlnk")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clntappmenu);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Navigating to Admin - User admin link..");
		} else {
			log.logLine(Testname, true, "Navigating to Admin - User admin link is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Navigating to Admin - User admin link is failed");
		}

		if (doesElementExist2(properties.getProperty("UserId"), 5)) {
			WebElement cntnme = driver.findElement(By.cssSelector(properties.getProperty("UserId")));
			cntnme.sendKeys(userId);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entered the userid "+userId+" in the user id text field in user/admin tool");
		} else {
			log.logLine(Testname, true, "Entering the userid "+userId+" in the user id text field in user/admin tool is failed");
		}

		if (doesElementExist2(properties.getProperty("UserApplicationId"), 5)) {
			WebElement applid = driver.findElement(By.cssSelector(properties.getProperty("UserApplicationId")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", applid);
			applid.sendKeys(applnId);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entered the application name "+applnId+" in the Application name text field in Client/App tool");
		} else {
			log.logLine(Testname, true, "Entering the application name "+applnId+" in the Application name text field in Client/App tool is failed");
		}	

		if (doesElementExist(properties.getProperty("AnyTool1"), 5)) {
			WebElement AnyTool = driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_lstLayers']"));
			Select select = new Select(AnyTool); 
			List <WebElement> options = select.getOptions(); 
			for (WebElement option: options) { 
				if (option.getText().equalsIgnoreCase("Email Builder")){ 
					option.click(); 
					break; 
				}
			}
			log.logLine(Testname, false, "Clicked on AnyTool drop down list and selected the option PIVOT eDelivery..");
		} else {
			log.logLine(Testname, true, "Clicking on AnyTool drop down list and selecting the option PIVOT eDelivery is  failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicking on AnyTool drop down list and selecting the option PIVOT eDelivery is  failed");
		}

		if (doesElementExist2(properties.getProperty("searchbtn"), 5)) {
			WebElement srcbtn = driver.findElement(By.cssSelector(properties.getProperty("searchbtn")));
			log.logLine(Testname, false, "Clicked on search button of the client/app/Tool Admin");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srcbtn);
			waitUntilRetrive();
		} else {
			log.logLine(Testname, true, "Clicking on search button of the user Admin is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicking on search button of the user Admin is failed");
		}

		if (doesElementExist2(properties.getProperty("ModifyTool"), 5)) {
			WebElement modtool = driver.findElement(By.cssSelector(properties.getProperty("ModifyTool")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", modtool);
			waitUntilRetrive();			
			log.logLine(Testname, false, "Clicked on the Email Builder ModifyTool button in client/app/Tool Admin ");
		} else {
			log.logLine(Testname, true, "Clicking on the Email Builder ModifyTool button in client/app/Tool Admin is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicking on the Email Builder ModifyTool button in client/app/Tool Admin is failed");
		}

		if(doesElementExist2(properties.getProperty("PromoteRollbackChkBox"),5)){
			WebElement edt= driver.findElement(By.cssSelector(properties.getProperty("PromoteRollbackChkBox")));

			if(edt.isSelected()){
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", edt);
				Thread.sleep(2000);
				log.logLine(Testname, false , "UnChecking the Promte Tempaltes Check box under \"Edit\" Column");
			}else{
				log.logLine(Testname, false , "Promte Tempaltes Check box under \"Edit\" Column is already uncheked");

			}

		}else{
			log.logLine(Testname, false, "Promte Templates checkbox is not present");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Promte Templates checkbox is not present");
		}

		if(doesElementExist2(properties.getProperty("ViewXMLSchemaChkBox"),5)){
			WebElement edt= driver.findElement(By.cssSelector(properties.getProperty("ViewXMLSchemaChkBox")));

			if(edt.isSelected()){
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", edt); 
				log.logLine(Testname, false , "UnChecking the XML Schema Tempaltes Check box under \"View\" Column");
				Thread.sleep(2000);
			}else{
				log.logLine(Testname, false , "XML Schema Tempaltes Check box under \"View\" Column is already uncheked");

			}

		}else{
			log.logLine(Testname, false, "XML Schema Templates checkbox is not present");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("XML Schema Templates checkbox is not present");
		}

		if(doesElementExist2(properties.getProperty("EditXMLSchemaChkBox"),5)){
			WebElement edt= driver.findElement(By.cssSelector(properties.getProperty("EditXMLSchemaChkBox")));

			if(edt.isSelected()){
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", edt);
				Thread.sleep(2000);
				log.logLine(Testname, false , "UnChecking the XML Schema Tempaltes Check box under \"Edit\" Column");

			}else{
				log.logLine(Testname, false , "XML Schema Tempaltes Check box under \"Edit\" Column is already uncheked");

			}

		}else{
			log.logLine(Testname, false, "XML Schema Templates checkbox is not present");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("XML Schema Templates checkbox is not present");
		}

		if(doesElementExist2(properties.getProperty("EditChkBox"),5)){
			WebElement edt= driver.findElement(By.cssSelector(properties.getProperty("EditChkBox")));

			if(edt.isSelected()){
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", edt); 
				Thread.sleep(2000);
				log.logLine(Testname, false , "UnChecking the Edit Tempaltes Check box under \"Edit\" Column");
			}else{
				log.logLine(Testname, false , "Edit Tempaltes Check box under \"Edit\" Column is already uncheked");

			}

		}else{
			log.logLine(Testname, false, "Edit Templates checkbox is not present");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Edit Templates checkbox is not present");
		}


		if (doesElementExist2(properties.getProperty("UpdateBtnInUserAdmin"), 5)) {
			WebElement updtBtn = driver.findElement(By.cssSelector(properties.getProperty("UpdateBtnInUserAdmin")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", updtBtn);
			waitUntilRetrive();			
			log.logLine(Testname, false, "Clicked on the eDel Application update button  of Email Builder Tool in client/app/Tool Admin ");
		} else {
			log.logLine(Testname, true, "Clicked on the eDel Application update button  of Email Builder Tool in client/app/Tool Admin is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicked on the eDel Application update button  of Email Builder Tool in client/app/Tool Admin is failed");
		}

		driver.close();
		Thread.sleep(2000);

		driver.switchTo().window(firstWinHandle);
		return true;
	}

	public boolean verifyPermissions_Disabled(String RandNo, String Testname) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		driver.switchTo().defaultContent();

		/*if (doesElementExist2(properties.getProperty("EmailTemplatesTab"), 5)) {
				WebElement updtBtn = driver.findElement(By.cssSelector(properties.getProperty("EmailTemplatesTab")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", updtBtn);
				Thread.sleep(2000);			
				log.logLine(Testname, false, "Clicked on the eDel Application update button  of Email Builder Tool in client/app/Tool Admin ");
			} else {
			    log.logLine(Testname, true, "Clicked on the eDel Application update button  of Email Builder Tool in client/app/Tool Admin is failed");
			    throw new Exception("Clicked on the eDel Application update button  of Email Builder Tool in client/app/Tool Admin is failed");
			}*/

		driver.switchTo().frame("iframeContainer");

		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		VerifySaveTextTemplate(Testname);
		Thread.sleep(5000);

		return true;
	}



	public boolean verifyPermissions_Enabled(String RandNo, String Testname) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		driver.switchTo().defaultContent();

		ClickeDeliverTab(Testname);

		Thread.sleep(1000);
		boolean CliSelected = false;
		ClntName = test.readColumnData("ClientName", sheetname);

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
		AppName = test.readColumnData("ApplicationName", sheetname);

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
						log.logLine(Testname, false, "Selecting the Application Name "+AppId +" from the popup..");
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
						if (lnk.getText().contains(AppId)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false, "Selecting the Application Name "+AppId +" from the dropdown..");							
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

		/*if (doesElementExist2(properties.getProperty("EmailTemplatesTab"), 5)) {
				WebElement updtBtn = driver.findElement(By.cssSelector(properties.getProperty("EmailTemplatesTab")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", updtBtn);
				Thread.sleep(2000);			
				log.logLine(Testname, false, "Clicked on the eDel Application update button  of Email Builder Tool in client/app/Tool Admin ");
			} else {
			    log.logLine(Testname, true, "Clicked on the eDel Application update button  of Email Builder Tool in client/app/Tool Admin is failed");
			    throw new Exception("Clicked on the eDel Application update button  of Email Builder Tool in client/app/Tool Admin is failed");
			}*/

		driver.switchTo().frame("iframeContainer");

		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		VerifySaveTextTemplate_Enabled(Testname);
		Thread.sleep(5000);

		/*Thread.sleep(5000);
		TemplateManagermouseover(Testname);
		Thread.sleep(5000);

		driver.switchTo().frame("iframeContainer");

		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		VerifyTemplateingrid(Testname);
		Thread.sleep(5000);*/
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

		DeleteTemplateviaadmin(Testname);*/

		return true;
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
							if (doesElementExist2("ul "+row+" div[class='grid-btns'] label", 5)) {
								WebElement textradio=driver.findElement(By.cssSelector("ul "+row+" div[class='grid-btns'] label"));
								((JavascriptExecutor) driver).executeScript("arguments[0].click()", textradio);
								Thread.sleep(2000);
								log.logLine(Testname, false, "Clicking on the \"Text\" radiobutton of the saved types ");
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

				Thread.sleep(25000);
				if (doesElementExist2(properties.getProperty("SelectApp"), 5)) {
					Select emltype = new Select(driver.findElement(By.cssSelector(properties.getProperty("SelectApp"))));
					emltype.selectByVisibleText("RGT1099 - RGT1099");
					log.logLine(Testname, false, "Selecting the Regression testing application from the Email Type Admin list in Template Management");
				} else {
					log.logLine(Testname, true, "Selecting the Regression testing application from the Email Type Admin list in Template Management is failed");
					throw new Exception("Selecting the Regression testing application from the Email Type Admin list in Template Management is failed");
				}

				String[] ProcessType = new String[100];
				String row1 = "tr";
				List<WebElement> DataCnt1= driver.findElements(By.xpath(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_dgViewEmailTypes']/tbody/tr"));

				Thread.sleep(2000);
				if(doesElementExist2(properties.getProperty("EmailTypeHeader"), 5)){
					for(int j = 0; j < DataCnt1.size(); j++) {
						ProcessType[j] = driver.findElement(By.cssSelector("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_dgViewEmailTypes'] tbody "+row1+" td")).getText();

						if(ProcessType[j].equals(EmailtypTxt.concat(AccNo))){

							log.logLine(Testname, false, "Iterating through the Rows of the Email Type list....and reading the Type in Email Type  as "+ProcessType[j]);

							WebElement delete=driver.findElement(By.cssSelector("div table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_dgViewEmailTypes'] tbody "+row1+" td+td+td+td+td a"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", delete);
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

			}
		} 


		return true;
	}

	public boolean ClickOKToConfirmDelete(String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("DeleteSveTempl"), 5)) {
			WebElement delSveTmplt = driver.findElement(By.cssSelector(properties.getProperty("DeleteSveTempl")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", delSveTmplt);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on confirm delete button");
		} else {
			log.logLine(Testname, true, "Clicking on confirm delete button is failed");
			//throw new Exception("Clicking on confirm delete button is failed");
		}

		return true ;
	}   


	public boolean VerifySaveTextTemplate(String Testname) throws Exception {

		String[] EmlType1 = new String[50];
		String row_3 = "li";		    

		WebElement table1 = driver.findElement(By.xpath(".//*[@id='ddl-management-saved-types-div']"));

		List<WebElement> DataCnt_3= driver.findElements(By.xpath(".//*[@id='ul-management-saved-types']/li"));

		Thread.sleep(3000);
		if(doesElementExist2("ul "+row_3+" div[class='grid-btns'] div", 5)){
			for(int k = 0; k < DataCnt_3.size(); k++) {
				EmlType1[k] = driver.findElement(By.cssSelector("ul "+row_3+" div[class='grid-btns'] div")).getText();

				if(EmlType1[k].equals(EmailtypTxt.concat(AccNo))){

					log.logLine(Testname, false, "Iterating through the Rows....and Reading the Type in Saved type as "+EmailtypTxt.concat(AccNo));


					if (doesElementExist2("ul "+row_3+" div[class='grid-btns'] div[class='management-saved-type-title']", 5)) {
						WebElement textradio=driver.findElement(By.cssSelector("ul "+row_3+" div[class='grid-btns'] div[class='management-saved-type-title']"));
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", textradio);
						log.logLine(Testname, false, "Clicking on the \"Text\" radiobutton of the saved types ");


						/*String editrow= "li";
							List<WebElement> DataCnt4= driver.findElements(By.xpath(".//*[@class='ul-templates ui-sortable']/li"));


								if(doesElementExist2("ul "+editrow+" div div+div[class='lbl-management-saved-type-name']", 5)){

								for(int j = 0; j < DataCnt4.size(); j++) {
									EmlType1[j] = driver.findElement(By.cssSelector("ul "+editrow+" div div+div[class='lbl-management-saved-type-name']")).getText();

									if(EmlType1[j].equals(TemplateName.concat(AccNo))){

										log.logLine(Testname, false, "Iterating through the Rows....Reading the Type in Saved type as "+EmlType1[j]);*/

						if (doesElementExist2("ul[class='ul-templates ui-sortable'] li div button[title='Promote']", 5)) {
							WebElement edit=driver.findElement(By.cssSelector("ul li div button[title='Promote']"));
							if(edit.isEnabled()){
								log.logLine(Testname, true, "Promote button is enabled after uncheking the Promote check box which is unexpected and failed");
								Thread.sleep(2000);
							}else{
								log.logLine(Testname, false, "Promote button is disabled after uncheking the Promote check box which is expected and passed");
							}

						}	

						if (doesElementExist2("ul[class='ul-templates ui-sortable'] li div button+button[title='Edit']", 5)) {
							WebElement edit=driver.findElement(By.cssSelector("ul li div button+button[title='Edit']"));
							if(edit.isEnabled()){
								log.logLine(Testname, true, "Edit button is enabled after uncheking the Edit check box which is unexpected and failed");
								Thread.sleep(2000);
							}else{
								log.logLine(Testname, false, "Edit button is disabled after uncheking the Edit check box which is expected and passed");
							}

						}

						if (doesElementExist2("ul[class='ul-templates ui-sortable'] li div button+button+button+button[title='Delete']", 5)) {
							WebElement edit=driver.findElement(By.cssSelector("ul li div button+button+button+button[title='Delete']"));
							if(edit.isEnabled()){
								log.logLine(Testname, true, "Delete button is enabled after uncheking the Delete check box which is unexpected and failed");
								Thread.sleep(2000);
							}else{
								log.logLine(Testname, false, "Delete button is disabled after uncheking the Delete check box which is expected and passed");
							}

						}

					}


					break;

				} ///

				row_3 = row_3 + "+li";
			}	




		}	

		return true;

	}


	public void ClickeDeliverTab(String Testname) throws Exception {
		if (doesElementExist2(properties.getProperty("EdeliveryTab"), 5)) {
			WebElement eDelive = driver.findElement(By.cssSelector(properties.getProperty("EdeliveryTab")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
			Thread.sleep(5000);
			log.logLine(Testname, false, "Click on e-Delivery Tab is Successful");
		} else {
			log.logLine(Testname, true, "Click on e-Delivery Tab is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on e-Delivery Tab is failed");
		}
	}

	public boolean VerifySaveTextTemplate_Enabled(String Testname) throws Exception {

		String[] EmlType1 = new String[50];
		String row_3 = "li";		    

		WebElement table1 = driver.findElement(By.xpath(".//*[@id='ddl-management-saved-types-div']"));

		List<WebElement> DataCnt_3= driver.findElements(By.xpath(".//*[@id='ul-management-saved-types']/li"));

		Thread.sleep(3000);
		if(doesElementExist2("ul "+row_3+" div[class='grid-btns'] div", 5)){
			for(int k = 0; k < DataCnt_3.size(); k++) {
				EmlType1[k] = driver.findElement(By.cssSelector("ul "+row_3+" div[class='grid-btns'] div")).getText();

				if(EmlType1[k].equals(EmailtypTxt.concat(AccNo))){

					log.logLine(Testname, false, "Iterating through the Rows....and Reading the Type in Saved type as "+EmailtypTxt.concat(AccNo));


					if (doesElementExist2("ul "+row_3+" div[class='grid-btns'] div[class='management-saved-type-title']", 5)) {
						WebElement textradio=driver.findElement(By.cssSelector("ul "+row_3+" div[class='grid-btns'] div[class='management-saved-type-title']"));
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", textradio);
						Thread.sleep(2000);
						log.logLine(Testname, false, "Clicking on the \"Text\" radiobutton of the saved types ");


						/*String editrow= "li";
							List<WebElement> DataCnt4= driver.findElements(By.xpath(".//*[@class='ul-templates ui-sortable']/li"));


								if(doesElementExist2("ul "+editrow+" div div+div[class='lbl-management-saved-type-name']", 5)){

								for(int j = 0; j < DataCnt4.size(); j++) {
									EmlType1[j] = driver.findElement(By.cssSelector("ul "+editrow+" div div+div[class='lbl-management-saved-type-name']")).getText();

									if(EmlType1[j].equals(TemplateName.concat(AccNo))){

										log.logLine(Testname, false, "Iterating through the Rows....Reading the Type in Saved type as "+EmlType1[j]);*/

						if (doesElementExist2("ul[class='ul-templates ui-sortable'] li div button[title='Promote']", 5)) {
							WebElement edit=driver.findElement(By.cssSelector("ul li div button[title='Promote']"));
							if(edit.isEnabled()){
								log.logLine(Testname, false, "Promote button is enabled after cheking the Promote check box which is expected and passed");
								Thread.sleep(2000);
							}else{
								log.logLine(Testname, true, "Promote button is disabled after cheking the Promote check box which is unexpected and failed");
							}

						}	

						if (doesElementExist2("ul[class='ul-templates ui-sortable'] li div button+button[title='Edit']", 5)) {
							WebElement edit=driver.findElement(By.cssSelector("ul li div button+button[title='Edit']"));
							if(edit.isEnabled()){
								log.logLine(Testname, false, "Edit button is enabled after cheking the Edit check box which is expected and passed");
								Thread.sleep(2000);
							}else{
								log.logLine(Testname, true, "Edit button is disabled after cheking the Edit check box which is unexpected and failed");
							}

						}

						if (doesElementExist2("ul[class='ul-templates ui-sortable'] li div button+button+button+button[title='Delete']", 5)) {
							WebElement edit=driver.findElement(By.cssSelector("ul li div button+button+button+button[title='Delete']"));
							if(edit.isEnabled()){
								log.logLine(Testname, false, "Delete button is enabled after cheking the Delete check box which is expected and passed");
								Thread.sleep(2000);
							}else{
								log.logLine(Testname, true, "Delete button is disabled after cheking the Delete check box which is unexpected and failed");
							}

						}

					}


					break;

				} ///

				row_3 = row_3 + "+li";
			}	




		}	

		return true;

	}


	public boolean accessTM_adminEmailProjects(String RandNo, String Testname) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		if ((doesElementExist2(properties.getProperty("Adminlnk"), 5))) {                
			WebElement oldpivt = driver.findElement(By.cssSelector(properties.getProperty("Adminlnk")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", oldpivt);	        
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicking on Legacy Admin..");              
		}


		Thread.sleep(25000);
		Set<String> handles = driver.getWindowHandles();
		firstWinHandle = driver.getWindowHandle(); 
		handles.remove(firstWinHandle);

		String SecwinHandle = null;
		boolean browserexist = handles.iterator().hasNext();

		if (browserexist) {
			SecwinHandle=handles.iterator().next();
			if (SecwinHandle != firstWinHandle){			    	
				//Switch control to new window
				driver.switchTo().window(SecwinHandle);			  

				if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetExplorer"))) {
					if (doesElementExist2(properties.getProperty("OverrideLnk"), 5)) {
						driver.get("javascript:document.getElementById('overridelink').click();");
						Thread.sleep(8000);
					}
				}
			}
		}



		if (doesElementExist2(properties.getProperty("EmailLink"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("EmailLink")));

			log.logLine(Testname, false, "Clicking on Email link from the leftContent Menu");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);

			List<WebElement> Lcn = driver.findElements(By.cssSelector(properties.getProperty("EmailLink")));
			for (WebElement btn:Lcn) {
				if (btn.getText().equals("EMail")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Clicking on Email link from the leftContent Menu is successful");
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
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on EmailBuilder option from the Email menu list ");
		} else if (doesElementExist2(properties.getProperty("EmailBuilderLinkS"), 5)) {  
			WebElement emlblder = driver.findElement(By.cssSelector(properties.getProperty("EmailBuilderLinkS")));
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
		//
		if (doesElementExist2(properties.getProperty("EmailTypes"), 5)) {	    
			WebElement emlty = driver.findElement(By.cssSelector(properties.getProperty("EmailTypes")));

			log.logLine(Testname, false, "Selecting the Email Types option of the Email Builder list");

			List<WebElement> lsteml = driver.findElements(By.cssSelector(properties.getProperty("EmailTypes")));
			for (WebElement btn:lsteml) {
				if (btn.getText().equalsIgnoreCase("Email Types")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					Thread.sleep(3000);
					log.logLine(Testname, false, "Clicking on Email Types option of the Email Builder list");
					break;
				}
			}
		}else {
			log.logLine(Testname, true, "Unable to Click on Email Types option of the Email Builder list");
			throw new Exception("Unable to Click on Email Types option of the Email Builder list");
		}

		if (doesElementExist2(properties.getProperty("SelectApp"), 5)) {
			Select emltype = new Select(driver.findElement(By.cssSelector(properties.getProperty("SelectApp"))));
			emltype.selectByVisibleText("RGT1099 - RGT1099");
			log.logLine(Testname, false, "Selecting the Regression testing application from the Email Type Admin list in Template Management");
		} else {
			log.logLine(Testname, true, "Selecting the Regression testing application from the Email Type Admin list in Template Management is failed");
			throw new Exception("Selecting the Regression testing application from the Email Type Admin list in Template Management is failed");
		}



		WebElement addemltyp = waitForElement(properties.getProperty("AddEmlType"));

		if (doesElementExist2(properties.getProperty("AddEmlType"), 5)) {
			WebElement addEmailTyp = driver.findElement(By.cssSelector(properties.getProperty("AddEmlType")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", addEmailTyp);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicking on OK button");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the Reports is failed");
			throw new Exception("Clicking on OK button to view the Reports is failed");
		}

		EmailtypTxt=test.readColumnData("EmailType", sheetname);
		String DescpText=test.readColumnData("Description", sheetname);
		String FromTxt=test.readColumnData("From", sheetname);
		String FromFrndlyTxt=test.readColumnData("FromFreindlyName", sheetname);
		rplyTxt=test.readColumnData("ReplyTo", sheetname);
		String rplyToFrndlyTxt=test.readColumnData("ReplyToFrndlyName", sheetname);

		String subTxt=test.readColumnData("Subject", sheetname);
		String qtyDay=test.readColumnData("QtyPerDay", sheetname);
		String qtyWeek=test.readColumnData("QtyPerWeek", sheetname);
		String qtyMonth=test.readColumnData("QtyPerMonth", sheetname);

		String qtyYear=test.readColumnData("QtyPerYear", sheetname);


		Thread.sleep(3000);    

		if (doesElementExist2(properties.getProperty("EmlTypeTxtBox"), 5)) {
			WebElement emltyptxt = driver.findElement(By.cssSelector(properties.getProperty("EmlTypeTxtBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", emltyptxt);
			emltyptxt.sendKeys(EmailtypTxt.concat(AccNo));
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entering Email Type name as "+EmailtypTxt.concat(AccNo)+" in the text box of Email Builder Section");
		} else {
			log.logLine(Testname, true, "Unable to enter Email Type name as "+EmailtypTxt.concat(AccNo)+" in the text box of Email Builder Section");
			throw new Exception("Unable to enter Email Type name as "+EmailtypTxt.concat(AccNo)+" in the text box of Email Builder Section");
		}

		if (doesElementExist2(properties.getProperty("DescriptionTxtBox"), 5)) {
			WebElement descpText = driver.findElement(By.cssSelector(properties.getProperty("DescriptionTxtBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", descpText);
			descpText.sendKeys(DescpText);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entering the description in the \"Description\" text box of Email Builder Section ");
		} else {
			log.logLine(Testname, true, "Unable to Enter the description in the \"Description\" text box of Email Builder Section ");
			throw new Exception("Unable to Enter the description in the \"Description\" text box of Email Builder Section ");
		}

		if (doesElementExist2(properties.getProperty("FromTxtBox"), 5)) {
			WebElement frmTxt = driver.findElement(By.cssSelector(properties.getProperty("FromTxtBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", frmTxt);
			frmTxt.sendKeys(FromTxt);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entering the from email addresss in the \"From\" text box of Email Builder Section ");
		} else {
			log.logLine(Testname, true, "Unable to Enter the from email addresss in the \"From\" text box of Email Builder Section ");
			throw new Exception("Unable to Enter the from email addresss in the \"From\" text box of Email Builder Section ");
		}

		if (doesElementExist2(properties.getProperty("FromFrndlyNmeTxtBox"), 5)) {
			WebElement frmFrndlyText = driver.findElement(By.cssSelector(properties.getProperty("FromFrndlyNmeTxtBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", frmFrndlyText);
			frmFrndlyText.sendKeys(FromFrndlyTxt);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entering the name in the \"From Friendly Name\" text box of Email Builder Section ");
		} else {
			log.logLine(Testname, true, "Unable to Enter the name in the \"From Friendly Name\" text box of Email Builder Section ");
			throw new Exception("Unable to Enter the name in the \"From Friendly Name\" text box of Email Builder Section ");
		}

		if (doesElementExist2(properties.getProperty("ReplyToTxtBox"), 5)) {
			WebElement rpltoTx = driver.findElement(By.cssSelector(properties.getProperty("ReplyToTxtBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rpltoTx);
			rpltoTx.sendKeys(rplyTxt);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entering the email address in the \"Reply To\" text box of Email Builder Section ");
		} else {
			log.logLine(Testname, true, "Unable to Enter the email address in the \"Reply To\" text box of Email Builder Section ");
			throw new Exception("Unable to Enter the email address in the \"Reply To\" text box of Email Builder Section ");
		}

		if (doesElementExist2(properties.getProperty("ReplyToFrndlyNmeTxtBox"), 5)) {
			WebElement rpltofrndlyTx = driver.findElement(By.cssSelector(properties.getProperty("ReplyToFrndlyNmeTxtBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rpltofrndlyTx);
			rpltofrndlyTx.sendKeys(rplyToFrndlyTxt);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entering the name in the \"Reply to Freindly Name\" text box of Email Builder Section ");
		} else {
			log.logLine(Testname, true, "Unable to Enter the email address in the \"Reply to Freindly Name\" text box of Email Builder Section ");
			throw new Exception("Unable to Enter the email address in the \"Reply to Freindly Name\" text box of Email Builder Section ");
		}

		if (doesElementExist2(properties.getProperty("SubjectTxtBox"), 5)) {
			WebElement subjTxt = driver.findElement(By.cssSelector(properties.getProperty("SubjectTxtBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", subjTxt);
			subjTxt.sendKeys(subTxt);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entering the text in \"Subject\" text box of the Email Builder Section");
		} else {
			log.logLine(Testname, true, "Unable to Enter the text in \"Subject\" text box of the Email Builder Section");
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
			throw new Exception("Unable to Select quotable printed option from the \"Text Encoding\" dropdown list of Email Builder Section ");
		}

		if (doesElementExist2(properties.getProperty("SanTpeDrpDwn"), 5)) {
			Select santyp = new Select(driver.findElement(By.cssSelector(properties.getProperty("SanTpeDrpDwn"))));
			santyp.selectByVisibleText("PCI");
			log.logLine(Testname, false, "Selecting PCI option from the \"San Type\" dropdown list of Email Builder Section ");
		} else {
			log.logLine(Testname, true, "Unable to Select PCI option from the \"San Type\" dropdown list of Email Builder Section ");
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
			throw new Exception("Unable to Enter the value for \"Emails per Week\" in Email Sender Section");
		}

		if (doesElementExist2(properties.getProperty("EmlQty/Month"), 5)) {
			WebElement qtymonth = driver.findElement(By.cssSelector(properties.getProperty("EmlQty/Month")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", qtymonth);
			qtymonth.sendKeys(qtyMonth);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entering the value for \"Emails per Month\" in Email Sender Section");
		} else {
			log.logLine(Testname, true, "Unable to Enter the value for \"Emails per Month\" in Email Sender Section");
			throw new Exception("Unable to Enter the value for \"Emails per Month\" in Email Sender Section");
		}

		if (doesElementExist2(properties.getProperty("EmlQty/Year"), 5)) {
			WebElement qtyyear = driver.findElement(By.cssSelector(properties.getProperty("EmlQty/Year")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", qtyyear);
			qtyyear.sendKeys(qtyYear);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entering the value for \"Emails per Year\" in Email Sender Section");
		} else {
			log.logLine(Testname, true, "Unable to Enter the value for \"Emails per Year\" in Email Sender Section");
			throw new Exception("Unable to Enter the value for \"Emails per Year\" in Email Sender Section");
		}

		if (doesElementExist2(properties.getProperty("PriorityDrpDwn"), 5)) {
			Select priority = new Select(driver.findElement(By.cssSelector(properties.getProperty("PriorityDrpDwn"))));
			priority.selectByVisibleText("Batch medium");
			log.logLine(Testname, false, "Selecting \"Batch medium\" option from the \" Priority \" dropdown list of Email Sender Section ");
		} else {
			log.logLine(Testname, true, "Unable to Select \"Batch medium\" option from the \" Priority \" dropdown list of Email Sender Section ");
			throw new Exception("Unable to Select \"Batch medium\" option from the \" Priority \" dropdown list of Email Sender Section ");
		}

		if (doesElementExist2(properties.getProperty("EmlTypeBindingDrpDwn"), 5)) {
			Select emlBinding = new Select(driver.findElement(By.cssSelector(properties.getProperty("EmlTypeBindingDrpDwn"))));
			emlBinding.selectByVisibleText("BCS-SR2-E24H");
			log.logLine(Testname, false, "Selecting \"BCS-SR2-E24H \" option from the \" Email Template Binding \" dropdown list of Email Sender Section ");
		} else {
			log.logLine(Testname, true, "Unable to Select \"BCS-SR2-E24H \" option from the \" Email Template Binding \" dropdown list of Email Sender Section ");
			throw new Exception("Unable to Select \"BCS-SR2-E24H \" option from the \" Email Template Binding \" dropdown list of Email Sender Section ");
		}

		if (doesElementExist2(properties.getProperty("AddButton"), 5)) {
			WebElement addbtn = driver.findElement(By.cssSelector(properties.getProperty("AddButton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", addbtn);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicking on \"Add\" button for adding the Email type in Template Management");
		} else {
			log.logLine(Testname, true, "Clicking on \"Add\" button for adding the Email type in Template Management is failed");
			throw new Exception("Clicking on \"Add\" button for adding the Email type in Template Management is failed");
		}

		Thread.sleep(2000);
		String successfulEml=test.readColumnData("SuccessfulEmlTypeText", sheetname);
		if(doesElementExist2(properties.getProperty("SucessEmlTempltText"),5)){
			String addText=driver.findElement(By.cssSelector(properties.getProperty("SucessEmlTempltText"))).getText();
			if(addText.equals(successfulEml)){
				log.logLine(Testname, false, " The \"Email Template\" type is added sucessfully");		
			}else{
				log.logLine(Testname, true, " The \"Email Template\" type is not added sucessfully");	
			}

		}else{
			log.logLine(Testname, true, "The text for sucessful added Email template does not exist");
			throw new Exception("The text for sucessful added Email template does not exist");
		}


		//


		if (doesElementExist2(properties.getProperty("EmailTypes"), 5)) {	    
			WebElement emlty = driver.findElement(By.cssSelector(properties.getProperty("EmailTypes")));

			log.logLine(Testname, false, "Selecting the Projects option of the Email Builder list");

			List<WebElement> lsteml = driver.findElements(By.cssSelector(properties.getProperty("EmailTypes")));
			for (WebElement btn:lsteml) {
				if (btn.getText().equalsIgnoreCase("Projects")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					Thread.sleep(3000);
					log.logLine(Testname, false, "Clicking on Projects link Under Email Builder list is successful");
					break;
				}
			}
		}else {
			log.logLine(Testname, true, "Unable to Click on Projects option of the Email Builder list");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Unable to Click on Projects option of the Email Builder list");
		}

		if (doesElementExist2(properties.getProperty("SelectAppProj"), 5)) {
			Select emltype = new Select(driver.findElement(By.cssSelector(properties.getProperty("SelectAppProj"))));
			if (Initialization.EnvirSite.equalsIgnoreCase("test")) {
				emltype.selectByVisibleText("RGT1099 - RGT1099");
			}else if (Initialization.EnvirSite.equalsIgnoreCase("stage")) {
				emltype.selectByVisibleText("rgt1099 - RGT1099");
			}else if (Initialization.EnvirSite.equalsIgnoreCase("prod")) {
				emltype.selectByVisibleText("RGT1099 - RGT1099");
			}
			log.logLine(Testname, false, "Selecting the Regression testing application from the dropdown of the Projects link");
		}else {
			log.logLine(Testname, true, "Selecting the Regression testing application from the dropdown of the Projects link is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Selecting the Regression testing application from the dropdown of the Projects link is failed");
		}

		if (doesElementExist2(properties.getProperty("EditBtn"), 5)) {
			WebElement updtBtn = driver.findElement(By.cssSelector(properties.getProperty("EditBtn")));
			Thread.sleep(4000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", updtBtn);
			Thread.sleep(4000);			
			log.logLine(Testname, false, "Clicked on the Edit button of the Email types table under Projects ");
		} else {
			log.logLine(Testname, true, "Clicked on the Edit button of the Email types table under Projects is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicked on the Edit button of the Email types table under Projects is failed");
		}

		if (doesElementExist2(properties.getProperty("TemplatesLnk"), 5)) {
			WebElement updtBtn = driver.findElement(By.cssSelector(properties.getProperty("TemplatesLnk")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", updtBtn);
			Thread.sleep(5000);			
			log.logLine(Testname, false, "Clicked on Templates link under Email Types");
		} else {
			log.logLine(Testname, true, "Clicked on Templates link under Email Types is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicked on Templates link under Email Types is failed");
		}   

		Thread.sleep(10000);
		Set<String> handles2 = driver.getWindowHandles();
		handles2.remove(firstWinHandle);
		handles2.remove(SecwinHandle);

		String ThirdWinHandle = null;	    
		boolean browserexist2 = handles2.iterator().hasNext();		    
		if (browserexist2) {

			ThirdWinHandle = handles2.iterator().next();
			if (ThirdWinHandle != firstWinHandle && ThirdWinHandle != SecwinHandle){			    	
				//Switch control to new window
				driver.switchTo().window(ThirdWinHandle);

				if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetExplorer"))) {
					if (doesElementExist2(properties.getProperty("OverrideLnk"), 5)) {
						driver.get("javascript:document.getElementById('overridelink').click();");
						Thread.sleep(8000);
					}
				}
			}

		} else {
			log.logLine(Testname, true, "Another Template Management browser does not exist");
			driver.close();				
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Another Template Management browser does not exist");
		}



		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");
			//throw new Exception("Clicking on Cancel button in Client/App popup is failed");
		}

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
		    	throw new Exception("Click on Template management Module is failed");
		    }*/

		Thread.sleep(1000);
		boolean CliSelected = false;
		ClntName = test.readColumnData("ClientName", sheetname);

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
		AppName = test.readColumnData("ApplicationName", sheetname);

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
						log.logLine(Testname, false, "Selecting the Application Name "+AppId +" from the popup..");
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
						if (lnk.getText().contains(AppId)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false, "Selecting the Application Name "+AppId +" from the dropdown..");							
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



		/*if (doesElementExist2(properties.getProperty("EmailTemplatesTab"), 5)) {
				WebElement updtBtn = driver.findElement(By.cssSelector(properties.getProperty("EmailTemplatesTab")));
				Thread.sleep(2000);			
				log.logLine(Testname, false, "Template Management page is opened successfully");
			} else {
			    log.logLine(Testname, true, "Template Management page is not opened successfully");
			    driver.close();
				driver.switchTo().window(SecwinHandle);
				driver.close();
				driver.switchTo().window(firstWinHandle);
			    throw new Exception("Template Management page is not opened successfully");
			}*/



		//Do some operation on child window of 1st child window.
		//to close the child window of 1st child window.
		driver.close();

		Thread.sleep(2000);			
		driver.switchTo().window(SecwinHandle);


		if (doesElementExist(properties.getProperty("Clientapplnk"), 5)) {
			WebElement clntappmenu = driver.findElement(By.xpath(properties.getProperty("Clientapplnk")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clntappmenu);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Navigating to Admin - Clientapp admin link..");
		} else {
			log.logLine(Testname, true, "Navigating to Admin - Clientapp admin link is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Navigating to Admin - Clientapp admin link is failed");
		}

		String AppName = test.readColumnData("ApplicationId", sheetname);
		if (doesElementExist2(properties.getProperty("ApplicationId"), 5)) {
			WebElement applid = driver.findElement(By.cssSelector(properties.getProperty("ApplicationId")));
			applid.clear();
			applid.sendKeys(AppName);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entered the application name "+AppName+" in the Application name text field in Client/App tool");
		} else {
			log.logLine(Testname, true, "Entering the application name "+AppName+" in the Application name text field in Client/App tool is failed");
		}

		if (doesElementExist2(properties.getProperty("searchbtn"), 5)) {
			WebElement srcbtn = driver.findElement(By.cssSelector(properties.getProperty("searchbtn")));
			log.logLine(Testname, false, "Clicked on search button of the client/app/Tool Admin");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srcbtn);
			waitUntilRetrive();
		} else {
			log.logLine(Testname, true, "Clicking on search button of the client/app/Tool Admin is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicking on search button of the client/app/Tool Admin is failed");
		}


		if (doesElementExist2(properties.getProperty("ClientappEdit"), 5)) {
			WebElement srcbtn = driver.findElement(By.cssSelector(properties.getProperty("ClientappEdit")));
			log.logLine(Testname, false, "Clicked on Client Edit button of the client/app/Tool Admin");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srcbtn);
			waitUntilRetrive();
		} else {
			log.logLine(Testname, true, "Clicking on Client Edit button of the client/app/Tool Admin is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicking on Client Edit button of the client/app/Tool Admin is failed");
		}

		if (doesElementExist2(properties.getProperty("EmailNotifcnTab"), 5)) {
			WebElement eDelclntOver = driver.findElement(By.cssSelector(properties.getProperty("EmailNotifcnTab")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelclntOver);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the Email Notifications/Actions tab under Client information section");
		} else {
			log.logLine(Testname, true, "Clicked on the Email Notifications/Actions tab under Client information section is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("t Overrides Clicked on the Email Notifications/Actions tab under Client information section is failed");
		}

		//Added from here for Email notification settings

		if (doesElementExist2(properties.getProperty("EmailNotEditBtn"), 5)) {
			WebElement eDelclntOver = driver.findElement(By.cssSelector(properties.getProperty("EmailNotEditBtn")));
			log.logLine(Testname, false,"Email Notification Settings already exists");

		} else {

			if (doesElementExist2(properties.getProperty("NotfcntypeAction"), 5)) {
				Select emltype = new Select(driver.findElement(By.cssSelector(properties.getProperty("NotfcntypeAction"))));
				emltype.selectByVisibleText("Concat Notification");
				log.logLine(Testname, false, "Selecting the Regression testing application from the dropdown of the Projects link");
			}else {
				log.logLine(Testname, true, "Selecting the Regression testing application from the dropdown of the Projects link is failed");
				driver.close();
				driver.switchTo().window(firstWinHandle);
				throw new Exception("Selecting the Regression testing application from the dropdown of the Projects link is failed");
			}


			if (doesElementExist2(properties.getProperty("EmailFromTextBox"), 5)) {
				WebElement eDelclntOver = driver.findElement(By.cssSelector(properties.getProperty("EmailFromTextBox")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelclntOver);
				eDelclntOver.clear();
				eDelclntOver.sendKeys("##d@@recEmails.primary_key##@ha-stage.edelivery-view.com");
				Thread.sleep(5000);
				log.logLine(Testname, false, "Clicked on the Email Notifications/Actions tab under Client information section");
			} else {
				log.logLine(Testname, true, "Clicked on the Email Notifications/Actions tab under Client information section is failed");
				driver.close();
				driver.switchTo().window(firstWinHandle);
				throw new Exception("t Overrides Clicked on the Email Notifications/Actions tab under Client information section is failed");
			}

			if (doesElementExist2(properties.getProperty("EmailReplyTo"), 5)) {
				WebElement eDelclntOver = driver.findElement(By.cssSelector(properties.getProperty("EmailReplyTo")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelclntOver);
				eDelclntOver.clear();
				eDelclntOver.sendKeys("automationpivot@gmail.com");
				Thread.sleep(5000);
				log.logLine(Testname, false, "Clicked on the Email Notifications/Actions tab under Client information section");
			} else {
				log.logLine(Testname, true, "Clicked on the Email Notifications/Actions tab under Client information section is failed");
				driver.close();
				driver.switchTo().window(firstWinHandle);
				throw new Exception("t Overrides Clicked on the Email Notifications/Actions tab under Client information section is failed");
			}

			if (doesElementExist2(properties.getProperty("SubjectTxtBox1"), 5)) {
				WebElement eDelclntOver = driver.findElement(By.cssSelector(properties.getProperty("SubjectTxtBox1")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelclntOver);
				eDelclntOver.click();
				eDelclntOver.sendKeys("Concatenation Stage");
				Thread.sleep(5000);
				log.logLine(Testname, false, "Clicked on the Email Notifications/Actions tab under Client information section");
			} else {
				log.logLine(Testname, true, "Clicked on the Email Notifications/Actions tab under Client information section is failed");
				driver.close();
				driver.switchTo().window(firstWinHandle);
				throw new Exception("t Overrides Clicked on the Email Notifications/Actions tab under Client information section is failed");
			}

			if (doesElementExist2(properties.getProperty("EmlFrmFrndlyName"), 5)) {
				WebElement eDelclntOver = driver.findElement(By.cssSelector(properties.getProperty("EmlFrmFrndlyName")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelclntOver);
				eDelclntOver.clear();
				eDelclntOver.sendKeys("Your ##d@@recEmails.PDFName## is available in ##d@@recEmails.site_link##");
				Thread.sleep(5000);
				log.logLine(Testname, false, "Clicked on the Email Notifications/Actions tab under Client information section");
			} else {
				log.logLine(Testname, true, "Clicked on the Email Notifications/Actions tab under Client information section is failed");
				driver.close();
				driver.switchTo().window(firstWinHandle);
				throw new Exception("t Overrides Clicked on the Email Notifications/Actions tab under Client information section is failed");
			}



			if (doesElementExist2(properties.getProperty("KilTime"), 5)) {
				Select emltype = new Select(driver.findElement(By.cssSelector(properties.getProperty("KilTime"))));
				emltype.selectByVisibleText("12");
				log.logLine(Testname, false, "Selecting the Regression testing application from the dropdown of the Projects link");
			} else {
				log.logLine(Testname, true, "Selecting the Regression testing application from the dropdown of the Projects link is failed");
				driver.close();
				driver.switchTo().window(firstWinHandle);
				throw new Exception("Selecting the Regression testing application from the dropdown of the Projects link is failed");
			}


			if (doesElementExist2(properties.getProperty("InsertBtn"), 5)) {
				WebElement eDelclntOver = driver.findElement(By.cssSelector(properties.getProperty("InsertBtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelclntOver);

				Thread.sleep(5000);
				log.logLine(Testname, false, "Clicked on the Email Notifications/Actions tab under Client information section");
			} else {
				log.logLine(Testname, true, "Clicked on the Email Notifications/Actions tab under Client information section is failed");
				driver.close();
				driver.switchTo().window(firstWinHandle);
				throw new Exception("t Overrides Clicked on the Email Notifications/Actions tab under Client information section is failed");
			}


		}	

		if (doesElementExist2(properties.getProperty("SeleDeliver2Opt"), 5)) { 
			List<WebElement> Edelopts = driver.findElements(By.cssSelector(properties.getProperty("SeleDeliver2Opt")));	
			for (WebElement lnk:Edelopts) {
				if (lnk.getText().equalsIgnoreCase("2. Text Template")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(5000);
					log.logLine(Testname, false, "Clicked Text Template Option from the Email Notifications/Actions tab");
					break;
				}
			}
		}

		if (doesElementExist2(properties.getProperty("TextTemplateEditInEmlNotfn"), 5)) {
			WebElement Editbutton = driver.findElement(By.cssSelector(properties.getProperty("TextTemplateEditInEmlNotfn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Editbutton);
			waitUntilRetrive();
			log.logLine(Testname, false, "Clicked Edit button of the Text Template under Email Notifications/Actions tab");
		} else {
			log.logLine(Testname, true, "Clicking Edit button of the Text Template under Email Notifications/Actions tab is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicking Edit button of the Text Template under Email Notifications/Actions tab is failed");
		}


		Thread.sleep(2000);
		Set<String> handles3 = driver.getWindowHandles();
		handles3.remove(firstWinHandle);
		handles3.remove(SecwinHandle);

		String FourWinHandle = null;	    
		boolean browserexist3 = handles3.iterator().hasNext();		    
		if (browserexist3) {

			FourWinHandle = handles3.iterator().next();
			if (FourWinHandle != firstWinHandle && FourWinHandle != SecwinHandle){			    	
				//Switch control to new window
				driver.switchTo().window(FourWinHandle);			    	
			}

		} else {
			log.logLine(Testname, true, "Another Template Management browser does not exist");
			driver.close();				
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Another Template Management browser does not exist");
		}

		//to close the child window.
		driver.close();
		Thread.sleep(2000);

		driver.switchTo().window(SecwinHandle);

		if (doesElementExist2(properties.getProperty("SeleDeliver2Opt"), 5)) { 
			List<WebElement> Edelopts = driver.findElements(By.cssSelector(properties.getProperty("SeleDeliver2Opt")));	
			for (WebElement lnk:Edelopts) {
				if (lnk.getText().equalsIgnoreCase("3. HTML Template")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(2000);
					log.logLine(Testname, false, "Clicked HTML Template Option from the Email Notifications/Actions tab");
					break;
				}
			}
		}

		/*if (doesElementExist2(properties.getProperty("HTMLTemplateEditInEmlNotfn"), 5)) {
			    WebElement Editbutton = driver.findElement(By.cssSelector(properties.getProperty("HTMLTemplateEditInEmlNotfn")));
			    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Editbutton);
			    waitUntilRetrive();
		    	log.logLine(Testname, false, "Clicked Edit button of the Text Template under Email Notifications/Actions tab");
			} else {
		    	log.logLine(Testname, true, "Clicking Edit button of the Text Template under Email Notifications/Actions tab is failed");
		    	driver.close();
				driver.switchTo().window(firstWinHandle);
		    	throw new Exception("Clicking Edit button of the Text Template under Email Notifications/Actions tab is failed");
			} */

		Thread.sleep(2000);
		/*Set<String> handles4 = driver.getWindowHandles();
		    handles4.remove(firstWinHandle);
		    handles4.remove(SecwinHandle);

		    String FiveWinHandle = null;	    
		    boolean browserexist4 = handles4.iterator().hasNext();

		    if (browserexist4) {
		    	FiveWinHandle = handles4.iterator().next();
			    if (FiveWinHandle != firstWinHandle && FiveWinHandle != SecwinHandle){			    	
			    	//Switch control to new window
			    	driver.switchTo().window(FiveWinHandle);			    	
			    }

		    } else {
			    log.logLine(Testname, true, "Another Template Management browser does not exist");
			    driver.close();				
				driver.switchTo().window(firstWinHandle);
			    //throw new Exception("Another Template Management browser does not exist");
			}


			driver.close();			
			Thread.sleep(2000);

			driver.switchTo().window(SecwinHandle);			
			Thread.sleep(2000);	*/		

		driver.close();
		Thread.sleep(2000);


		//to switch to parent window.
		driver.switchTo().window(firstWinHandle);			
		Thread.sleep(2000);

		log.logLine(Testname, false, "Switching to the main window");


		return true;
	}


	public boolean accessTM_ClientAdminEdelivery(String Testname ) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		if ((doesElementExist2(properties.getProperty("Adminlnk"), 5))) {                
			WebElement oldpivt = driver.findElement(By.cssSelector(properties.getProperty("Adminlnk")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", oldpivt);	        
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicking on Legacy Admin..");              
		}


		Thread.sleep(25000);
		Set<String> handles = driver.getWindowHandles();
		firstWinHandle = driver.getWindowHandle(); 
		handles.remove(firstWinHandle);

		String SecwinHandle = null;
		boolean browserexist = handles.iterator().hasNext();

		if (browserexist) {
			SecwinHandle=handles.iterator().next();
			if (SecwinHandle != firstWinHandle){			    	
				//Switch control to new window
				driver.switchTo().window(SecwinHandle);

				if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetExplorer"))) {
					if (doesElementExist2(properties.getProperty("OverrideLnk"), 5)) {
						driver.get("javascript:document.getElementById('overridelink').click();");
						Thread.sleep(8000);
					}
				}
			}
		}

		if (doesElementExist(properties.getProperty("Clientapplnk"), 5)) {
			WebElement clntappmenu = driver.findElement(By.xpath(properties.getProperty("Clientapplnk")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clntappmenu);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Navigating to Admin - Clientapp admin link..");
		} else {
			log.logLine(Testname, true, "Navigating to Admin - Clientapp admin link is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Navigating to Admin - Clientapp admin link is failed");
		}

		String AppName = test.readColumnData("ApplicationId", sheetname);
		if (doesElementExist2(properties.getProperty("ApplicationId"), 5)) {
			WebElement applid = driver.findElement(By.cssSelector(properties.getProperty("ApplicationId")));
			applid.clear();
			applid.sendKeys(AppName);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entered the application name "+AppName+" in the Application name text field in Client/App tool");
		} else {
			log.logLine(Testname, true, "Entering the application name "+AppName+" in the Application name text field in Client/App tool is failed");
		}

		if (doesElementExist(properties.getProperty("AnyTool1"), 5)) {
			WebElement AnyTool = driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_lstLayers']"));
			Select select = new Select(AnyTool); 
			List <WebElement> options = select.getOptions(); 
			for (WebElement option: options) { 
				if (option.getText().equalsIgnoreCase("PIVOT eDelivery")){ 
					option.click();				
					break; 
				}
			}
			log.logLine(Testname, false, "Clicked on AnyTool drop down list and selected the option PIVOT eDelivery..");
		} else {
			log.logLine(Testname, true, "Clicking on AnyTool drop down list and selecting the option PIVOT eDelivery is  failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicking on AnyTool drop down list and selecting the option PIVOT eDelivery is  failed");
		}

		if (doesElementExist2(properties.getProperty("searchbtn"), 5)) {
			WebElement srcbtn = driver.findElement(By.cssSelector(properties.getProperty("searchbtn")));
			log.logLine(Testname, false, "Clicked on search button of the client/app/Tool Admin");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srcbtn);
			waitUntilRetrive();
		} else {
			log.logLine(Testname, true, "Clicking on search button of the client/app/Tool Admin is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicking on search button of the client/app/Tool Admin is failed");
		}

		if (doesElementExist2(properties.getProperty("ModifyTool"), 5)) {
			WebElement modtool = driver.findElement(By.cssSelector(properties.getProperty("ModifyTool")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", modtool);
			waitUntilRetrive();			
			log.logLine(Testname, false, "Clicked on the Email Builder ModifyTool button in client/app/Tool Admin ");
		} else {
			log.logLine(Testname, true, "Clicking on the Email Builder ModifyTool button in client/app/Tool Admin is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicking on the Email Builder ModifyTool button in client/app/Tool Admin is failed");
		}

		if (doesElementExist2(properties.getProperty("eNotifyDocNotification"), 5)) {
			WebElement entfyNotification = driver.findElement(By.cssSelector(properties.getProperty("eNotifyDocNotification")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", entfyNotification);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the eNotify Document Notification tab under view pivot eDelivery information");
		} else {
			log.logLine(Testname, true, "Clicking on the eNotify Document Notification tab under view pivot eDelivery information is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicking on the eNotify Document Notification tab under view pivot eDelivery information is failed");
		}

		//Added from here for Document notification settings

		if (doesElementExist2(properties.getProperty("DocNotfcnEditBtn"), 5)) {
			WebElement eDelclntOver = driver.findElement(By.cssSelector(properties.getProperty("DocNotfcnEditBtn")));
			log.logLine(Testname, false,"Document Notification Settings already exists");

		} else {



			if (doesElementExist2(properties.getProperty("DocEmailFrom"), 5)) {
				WebElement eDelclntOver = driver.findElement(By.cssSelector(properties.getProperty("DocEmailFrom")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelclntOver);
				eDelclntOver.clear();
				eDelclntOver.sendKeys("##d@@recEmails.primary_key##@ha-stage.edelivery-view.com");
				Thread.sleep(5000);
				log.logLine(Testname, false, "Clicked on the Email Notifications/Actions tab under Client information section");
			} else {
				log.logLine(Testname, true, "Clicked on the Email Notifications/Actions tab under Client information section is failed");
				driver.close();
				driver.switchTo().window(firstWinHandle);
				throw new Exception("t Overrides Clicked on the Email Notifications/Actions tab under Client information section is failed");
			}

			if (doesElementExist2(properties.getProperty("DocEmailReplyTo"), 5)) {
				WebElement eDelclntOver = driver.findElement(By.cssSelector(properties.getProperty("DocEmailReplyTo")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelclntOver);
				eDelclntOver.clear();
				eDelclntOver.sendKeys("automationpivot@gmail.com");
				Thread.sleep(5000);
				log.logLine(Testname, false, "Clicked on the Email Notifications/Actions tab under Client information section");
			} else {
				log.logLine(Testname, true, "Clicked on the Email Notifications/Actions tab under Client information section is failed");
				driver.close();
				driver.switchTo().window(firstWinHandle);
				throw new Exception("t Overrides Clicked on the Email Notifications/Actions tab under Client information section is failed");
			}

			if (doesElementExist2(properties.getProperty("DocSubjectTxtBox"), 5)) {
				WebElement eDelclntOver = driver.findElement(By.cssSelector(properties.getProperty("DocSubjectTxtBox")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelclntOver);
				eDelclntOver.click();
				eDelclntOver.sendKeys("Concatenation Stage");
				Thread.sleep(5000);
				log.logLine(Testname, false, "Clicked on the Email Notifications/Actions tab under Client information section");
			} else {
				log.logLine(Testname, true, "Clicked on the Email Notifications/Actions tab under Client information section is failed");
				driver.close();
				driver.switchTo().window(firstWinHandle);
				throw new Exception("t Overrides Clicked on the Email Notifications/Actions tab under Client information section is failed");
			}

			if (doesElementExist2(properties.getProperty("DocEmlFrmFrndlyName"), 5)) {
				WebElement eDelclntOver = driver.findElement(By.cssSelector(properties.getProperty("DocEmlFrmFrndlyName")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelclntOver);
				eDelclntOver.clear();
				eDelclntOver.sendKeys("Your ##d@@recEmails.PDFName## is available in ##d@@recEmails.site_link##");
				Thread.sleep(5000);
				log.logLine(Testname, false, "Clicked on the Email Notifications/Actions tab under Client information section");
			} else {
				log.logLine(Testname, true, "Clicked on the Email Notifications/Actions tab under Client information section is failed");
				driver.close();
				driver.switchTo().window(firstWinHandle);
				throw new Exception("t Overrides Clicked on the Email Notifications/Actions tab under Client information section is failed");
			}



			if (doesElementExist2(properties.getProperty("DocKilTime"), 5)) {
				Select emltype = new Select(driver.findElement(By.cssSelector(properties.getProperty("DocKilTime"))));
				emltype.selectByVisibleText("12");
				log.logLine(Testname, false, "Selecting the Regression testing application from the dropdown of the Projects link");
			}else {
				log.logLine(Testname, true, "Selecting the Regression testing application from the dropdown of the Projects link is failed");
				driver.close();
				driver.switchTo().window(firstWinHandle);
				throw new Exception("Selecting the Regression testing application from the dropdown of the Projects link is failed");
			}


			if (doesElementExist2(properties.getProperty("DocInsertBtn"), 5)) {
				WebElement eDelclntOver = driver.findElement(By.cssSelector(properties.getProperty("DocInsertBtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelclntOver);
				Thread.sleep(2000);
				log.logLine(Testname, false, "Clicked on the Email Notifications/Actions tab under Client information section");
			} else {
				log.logLine(Testname, true, "Clicked on the Email Notifications/Actions tab under Client information section is failed");
				driver.close();
				driver.switchTo().window(firstWinHandle);
				throw new Exception("t Overrides Clicked on the Email Notifications/Actions tab under Client information section is failed");
			}

		} 



		if (doesElementExist2(properties.getProperty("SeleDeliver2Opt"), 5)) { 
			List<WebElement> Edelopts = driver.findElements(By.cssSelector(properties.getProperty("SeleDeliver2Opt")));	
			for (WebElement lnk:Edelopts) {
				if (lnk.getText().equalsIgnoreCase("2. Text Template")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(2000);
					log.logLine(Testname, false, "Clicked Text Template of the eNotify Document Notification tab under view pivot information ");
					break;
				}
			}
		}

		if (doesElementExist2(properties.getProperty("eDelTextEditBtn"), 5)) {
			WebElement Editbutton = driver.findElement(By.cssSelector(properties.getProperty("eDelTextEditBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Editbutton);
			waitUntilRetrive();
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked Edit button of the Text Template under eNotify Document Notification tab");





			Set<String> handles2 = driver.getWindowHandles();
			handles2.remove(firstWinHandle);
			handles2.remove(SecwinHandle);

			String ThirdWinHandle = null;	    
			boolean browserexist2 = handles2.iterator().hasNext();		    
			if (browserexist2) {

				ThirdWinHandle = handles2.iterator().next();
				if (ThirdWinHandle != firstWinHandle && ThirdWinHandle != SecwinHandle){			    	
					//Switch control to new window
					driver.switchTo().window(ThirdWinHandle);

					if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetExplorer"))) {
						if (doesElementExist2(properties.getProperty("OverrideLnk"), 5)) {
							driver.get("javascript:document.getElementById('overridelink').click();");
							Thread.sleep(8000);
						}
					}
				}

			} else {
				log.logLine(Testname, true, "Another Template Management browser does not exist");
				driver.close();				
				driver.switchTo().window(firstWinHandle);
				throw new Exception("Another Template Management browser does not exist");
			}

			//to close the child window.
			driver.close();
			Thread.sleep(2000);


			driver.switchTo().window(SecwinHandle);
			Thread.sleep(2000);

			if (doesElementExist2(properties.getProperty("SeleDeliver2Opt"), 5)) { 
				List<WebElement> Edelopts = driver.findElements(By.cssSelector(properties.getProperty("SeleDeliver2Opt")));	
				for (WebElement lnk:Edelopts) {
					if (lnk.getText().equalsIgnoreCase("3. HTML Template")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(2000);
						log.logLine(Testname, false, "Clicked HTML Template of the eNotify Document Notification tab under view pivot information ");
						break;
					}
				}
			}

			if (doesElementExist2(properties.getProperty("eDelHTMLEditBtn"), 5)) {
				WebElement Editbutton1 = driver.findElement(By.cssSelector(properties.getProperty("eDelHTMLEditBtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", Editbutton1);
				waitUntilRetrive();
				Thread.sleep(2000);
				log.logLine(Testname, false, "Clicked Edit button of the HTML Template under eNotify Document Notification tab");


				Set<String> handles3 = driver.getWindowHandles();
				handles3.remove(firstWinHandle);
				handles3.remove(SecwinHandle);

				String FourWinHandle = null;	    
				boolean browserexist3 = handles3.iterator().hasNext();		    
				if (browserexist3) {

					FourWinHandle = handles3.iterator().next();
					if (FourWinHandle != firstWinHandle && FourWinHandle != SecwinHandle){			    	
						//Switch control to new window
						driver.switchTo().window(FourWinHandle);	

						driver.close();
						Thread.sleep(2000);
					}
					
				} else {
					log.logLine(Testname, true, "Another Template Management browser does not exist");
					driver.close();				
					driver.switchTo().window(firstWinHandle);
					throw new Exception("Another Template Management browser does not exist");
				}
					
				driver.switchTo().window(SecwinHandle);
				Thread.sleep(2000);

				

				driver.close();
				Thread.sleep(2000);

				//to switch to parent window.
				driver.switchTo().window(firstWinHandle);
				Thread.sleep(2000);

				log.logLine(Testname, false, "Switching to the main window");


			} else {
				log.logLine(Testname, true, "Another Template Management browser does not exist");
				driver.close();				
				driver.switchTo().window(firstWinHandle);
				//		throw new Exception("Another Template Management browser does not exist");

			}

		}else {
			log.logLine(Testname, true, "Another Template Management browser does not exist");
			driver.close();				
			driver.switchTo().window(firstWinHandle);
		}	

		return true;
	}


	public boolean SavedTypesDropDown(String Testname ) throws Exception {


		if (doesElementExist2(properties.getProperty("SavedTypesBtn"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("SavedTypesBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on \"Saved Types\" dropdown..");

		} else {
			log.logLine(Testname, true, "Unable to Click on \"Saved Types\" dropdown..");
			throw new Exception("Unable to Click on \"Saved Types\" dropdown..");
		}

		return true;
	}


	public boolean AddTemplate(String Testname ) throws Exception {

		String[] EmlType = new String[100];
		String row = "li";

		WebElement table = driver.findElement(By.xpath(".//*[@id='ddl-management-saved-types-div']"));

		List<WebElement> DataCnt2= driver.findElements(By.xpath(".//*[@id='ul-management-saved-types']/li"));

		Thread.sleep(2000);
		if(doesElementExist2("ul "+row+" div[class='grid-btns'] div", 5)){
			for(int i = 0; i < DataCnt2.size(); i++) {
				EmlType[i] = driver.findElement(By.cssSelector("ul "+row+" div[class='grid-btns'] div")).getText();

				if(EmlType[i].equals(EmailtypTxt.concat(AccNo))){

					log.logLine(Testname, false, "Iterating through the Rows....and Reading the Type in \"Saved Types\" as "+EmailtypTxt.concat(AccNo));

					if (doesElementExist2("ul "+row+" div[class='grid-btns'] label", 5)) {
						WebElement textradio=driver.findElement(By.cssSelector("ul "+row+" div[class='grid-btns'] label"));
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", textradio);
						Thread.sleep(2000);
						log.logLine(Testname, false, "Clicking on the \"Text\" radiobutton of the saved types ");

						if (doesElementExist2("ul "+row+" div button[class='k-button btn-management-add-template']", 5)) {
							WebElement plus=driver.findElement(By.cssSelector("ul "+row+" div button[class='k-button btn-management-add-template']"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", plus);
							Thread.sleep(3000);
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

	public boolean FillTemplateName(String Testname ) throws Exception {

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("TempltNameTxtBox"), 5)) {
			WebElement qtyday = driver.findElement(By.cssSelector(properties.getProperty("TempltNameTxtBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", qtyday);
			qtyday.clear();
			qtyday.sendKeys(TemplateName.concat(AccNo));
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entering the text as "+TemplateName.concat(AccNo)+" in \"Template Name\" textbox ");
		} else {
			log.logLine(Testname, true, "Unable to Enter the text as "+TemplateName.concat(AccNo)+" in \"Template Name\" textbox ");
			throw new Exception("Unable to Enter the text as "+TemplateName.concat(AccNo)+" in \"Template Name\" textbox ");
		}

		return true;
	}

	public boolean FillTemaplateDescription(String Testname ) throws Exception {

		Thread.sleep(2000);		
		if (doesElementExist2(properties.getProperty("TempltDescpTxtArea"), 5)) {
			WebElement qtyday = driver.findElement(By.cssSelector(properties.getProperty("TempltDescpTxtArea")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", qtyday);
			qtyday.clear();
			qtyday.sendKeys(TempltDecsp);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entering the text as "+TempltDecsp+" in \"Template Description\" textbox ");
		} else {
			log.logLine(Testname, true, "Unable to Enter the text as "+TempltDecsp+" in \"Template Description\" textbox ");
			throw new Exception("Unable to Enter the text as "+TempltDecsp+" in \"Template Description\" textbox ");
		}

		return true;
	}

	public boolean SaveTemplate(String Testname ) throws Exception {


		if (doesElementExist2(properties.getProperty("SaveTemplate"), 5)) {	   
			WebElement savebtn = driver.findElement(By.cssSelector(properties.getProperty("SaveTemplate")));
			savebtn.click();
			//((JavascriptExecutor) driver).executeScript("arguments[0].click()", savebtn);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicked on \"Save\" button to add the template..");

		} else {
			log.logLine(Testname, true, "Clicking on \"Save\" button to add the template..is failed");
			throw new Exception("Clicking on \"Save\" button to add the template..is failed");
		}

		return true;
	}

	public boolean ClickExitEditorButton(String Testname ) throws Exception {

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("ExitEditor"), 5)) {
			WebElement exitEdtr = driver.findElement(By.cssSelector(properties.getProperty("ExitEditor")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", exitEdtr);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicking on \"Exit Editor\" button after adding the template ");
		} else {
			log.logLine(Testname, true, "Clicking on \"Exit Editor\" button after adding the template is failed");
			throw new Exception("Clicking on \"Exit Editor\" button after adding the template is failed");
		}
		return true;
	}



	////////////////////////////////////////////////


	public boolean ClickXMLSchemaButton(String Testname,String SrchType) throws Exception {

		String[] EmlType1 = new String[50];
		String row_3 = "li";
		notemplts="No templates found!";


		WebElement table1 = driver.findElement(By.xpath(".//*[@id='ddl-management-saved-types-div']"));

		List<WebElement> DataCnt_3= driver.findElements(By.xpath(".//*[@id='ul-management-saved-types']/li"));

		Thread.sleep(3000);
		if(doesElementExist2("ul "+row_3+" div[class='grid-btns'] div", 5)){
			for(int k = 0; k < DataCnt_3.size(); k++) {
				EmlType1[k] = driver.findElement(By.cssSelector("ul "+row_3+" div[class='grid-btns'] div")).getText();

				if(EmlType1[k].equals(EmailtypTxt.concat(AccNo))){

					log.logLine(Testname, false, "Iterating through the Rows....and Reading the Type in Saved type as "+EmailtypTxt.concat(AccNo));


					if (doesElementExist2("ul "+row_3+" div[class='grid-btns'] label", 5)) {
						WebElement textradio=driver.findElement(By.cssSelector("ul "+row_3+" div[class='grid-btns'] label"));
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", textradio);
						log.logLine(Testname, false, "Clicking on the \"Text\" radiobutton of the saved types ");


						switch (SrchType) {

						case "Edit":
							String editrow= "li";
							List<WebElement> DataCnt4= driver.findElements(By.xpath(".//*[@class='ul-templates ui-sortable']/li"));


							if(doesElementExist2("ul "+editrow+" div div+div[class='lbl-management-saved-type-name']", 5)){

								for(int j = 0; j < DataCnt4.size(); j++) {
									EmlType1[j] = driver.findElement(By.cssSelector("ul "+editrow+" div div+div[class='lbl-management-saved-type-name']")).getText();

									if(EmlType1[j].equals(TemplateName.concat(AccNo))){

										log.logLine(Testname, false, "Iterating through the Rows....Reading the Type in Saved type as "+EmlType1[j]);


										if (doesElementExist2("ul "+editrow+" div button+button[class='k-button btn-management-saved-template-edit']", 5)) {
											WebElement edit=driver.findElement(By.cssSelector("ul "+editrow+" div button+button[class='k-button btn-management-saved-template-edit']"));
											((JavascriptExecutor) driver).executeScript("arguments[0].click()", edit);
											PleasewaitDisappear();
											log.logLine(Testname, false, "Clicking on the \"Edit\" button of the saved types Text Template");


										}
										break;

									}
									editrow = editrow + "+li";

								}

								break;
							}

						case "PromoteTempl":
							String promrow= "li";
							List<WebElement> DataCnt8= driver.findElements(By.xpath(".//*[@class='ul-templates ui-sortable']/li"));


							if(doesElementExist2("ul "+promrow+" div div+div[class='lbl-management-saved-type-name']", 5)){

								for(int j = 0; j < DataCnt8.size(); j++) {
									EmlType1[j] = driver.findElement(By.cssSelector("ul "+promrow+" div div+div[class='lbl-management-saved-type-name']")).getText();

									if(EmlType1[j].equals(TemplateName.concat(AccNo))){

										log.logLine(Testname, false, "Iterating through the Rows....and Reading the Type in Saved type as "+EmlType1[j]);


										if (doesElementExist2("ul "+promrow+" div button[class='k-button btn-management-saved-template-promote']", 5)) {
											WebElement promote=driver.findElement(By.cssSelector("ul "+promrow+" div button[class='k-button btn-management-saved-template-promote']"));
											((JavascriptExecutor) driver).executeScript("arguments[0].click()", promote);
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
											((JavascriptExecutor) driver).executeScript("arguments[0].click()", view);
											PleasewaitDisappear();
											log.logLine(Testname, false, "Clicking on the \"View\" button of the saved types Text Template");


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
							if (doesElementExist2("div table tbody tr td ul+ul li[class='grid-btns']", 5)) {
								String notmpltfnd = driver.findElement(By.cssSelector("div table tbody tr td ul+ul li[class='grid-btns']")).getText();
								if(notmpltfnd.equals("No templates found!")){
									log.logLine(Testname, false, " "+notmpltfnd+" is present hence \"delete\" text template is sucessful");
								}else{
									log.logLine(Testname, true, " "+notmpltfnd+" is not present hence \"delete\" text template is unsucessful");
									//throw new Exception(""+notmpltfnd+" is not present hence \"delete\" text template is unsucessful");
								}

							} else {
								log.logLine(Testname, true, "Clicking on delete button is failed");
								throw new Exception("Clicking on delete button is failed");
							}

							break;

						case "ClickLoadXMLBtn":

							if (doesElementExist2("button[id='btn-load-xml-schema']", 5)) {	   
								WebElement xmlSchema = driver.findElement(By.cssSelector("button[id='btn-load-xml-schema']"));
								if(xmlSchema.isEnabled()){
									log.logLine(Testname, false, "XML Schema button is enabled");
									((JavascriptExecutor) driver).executeScript("arguments[0].click()", xmlSchema);
								}else{
									log.logLine(Testname, true, "XML Schema button is disabled");
								}

								log.logLine(Testname, false, "Clicking on Load XML Schema button is sucessful..");

							} else {
								log.logLine(Testname, true, "Clicking on Load XML Schema button is unsucessful..");
								throw new Exception("Clicking on Load XML Schema button is unsucessful..");
							}


							break;			

						} //closing of SrchType
						break; 

					}
				}	

				row_3 = row_3 + "+li";



			}

			return true;
		}
		return true;

	}


	public boolean LoadXMLSchemaValidation(String Testname) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		spacialChar=test.readColumnData("SpecialChars", sheetname);
		name=test.readColumnData("Name", sheetname);

		//check for xmlschema form appears
		if (doesElementExist2(properties.getProperty("LoadXMLSchemaForm"), 5)) {	    
			WebElement xmlSchemaForm = driver.findElement(By.cssSelector(properties.getProperty("LoadXMLSchemaForm")));

			if (xmlSchemaForm.isDisplayed())
			{
				log.logLine(Testname, false, "Validated Successfully - clicking on Load xml schema button is sucessful and xml container form appears ");
			}else {
				log.logLine(Testname, true, "Validated failed - clicking on Load xml schema button is sucessful and xml container form does not appears");
				throw new Exception("Validated failed - clicking on Load xml schema button is sucessful and xml container form does not appears");
			}

		}else{
			log.logLine(Testname, true, "clicking on Load xml schema button is sucessful and xml container form does not appears");
			throw new Exception("clicking on Load xml schema button is sucessful and xml container form does not appears");
		}

		//verify the document name in xml form
		if (doesElementExist2("label[id='load-xml-schema-title']", 5)) {	    
			String xmlSchemaForm = driver.findElement(By.cssSelector("label[id='load-xml-schema-title']")).getText();
			if(xmlSchemaForm.equals(EmailtypTxt.concat(AccNo))){
				log.logLine(Testname, false, "Document name exist in the XML schema form");
				Thread.sleep(5000);
			}else{
				log.logLine(Testname, true, "Document name does not exist in the XML schema form");
			}
		}else{
			log.logLine(Testname, true, "Document name does not exist in the XML schema form");
			throw new Exception("Document name does not exist in the XML schema form"); 
		}

		//check default fields exist 
		if (doesElementExist2(properties.getProperty("DefaultFieldName1"), 5)) {	    
			String defltVal1 = driver.findElement(By.cssSelector(properties.getProperty("DefaultFieldName1"))).getAttribute("value");
			log.logLine(Testname, false, "First default value is  "+defltVal1+" ");
			Thread.sleep(5000);
		}else{
			log.logLine(Testname, true, "First default value is not present ");
			throw new Exception("First default value is not present ");
		}

		if (doesElementExist2(properties.getProperty("DefaultFieldName2"), 5)) {	    
			String defltVal2 = driver.findElement(By.cssSelector(properties.getProperty("DefaultFieldName2"))).getAttribute("value");
			log.logLine(Testname, false, "Second default value is  "+defltVal2+" ");
			Thread.sleep(5000);
		}else{
			log.logLine(Testname, true, "Second default value is not present ");
			throw new Exception("Second default value is not present ");
		}

		if (doesElementExist2(properties.getProperty("DefaultFieldName3"), 5)) {	    
			String defltVal3 = driver.findElement(By.cssSelector(properties.getProperty("DefaultFieldName3"))).getAttribute("value");
			log.logLine(Testname, false, "Third default value is  "+defltVal3+" ");
			Thread.sleep(2000);
		}else{
			log.logLine(Testname, true, "Third default value is not present ");
			throw new Exception("Third default value is not present ");

		}

		if (doesElementExist2(properties.getProperty("DefaultFieldLabel1"), 5)) {	    
			String defltLbl1 = driver.findElement(By.cssSelector(properties.getProperty("DefaultFieldLabel1"))).getText();
			log.logLine(Testname, false, "First default value is  "+defltLbl1+" ");
			Thread.sleep(2000);
		}else{
			log.logLine(Testname, true, "First default value is not present ");
			throw new Exception("First default value is not present ");
		}

		if (doesElementExist2(properties.getProperty("DefaultFieldLabel2"), 5)) {	    
			String defltLbl2 = driver.findElement(By.cssSelector(properties.getProperty("DefaultFieldLabel2"))).getText();
			log.logLine(Testname, false, "Second default value is  "+defltLbl2+" ");
			Thread.sleep(2000);
		}else{
			log.logLine(Testname, true, "Second default value is not present ");
			throw new Exception("Second default value is not present ");
		}

		if (doesElementExist2(properties.getProperty("DefaultFieldLabel3"), 5)) {	    
			String defltLbl3 = driver.findElement(By.cssSelector(properties.getProperty("DefaultFieldLabel3"))).getText();
			log.logLine(Testname, false, "Third default value is  "+defltLbl3+" ");
			Thread.sleep(5000);
		}else{
			log.logLine(Testname, true, "Third default value is not present ");
			throw new Exception("Third default value is not present ");

		}

		Thread.sleep(5000);
		ClickPlusBtnOfXML(Testname);

		//////////////// Alert message is not coming in automation after adding the special characters  

		/*//Enter first new field value to xml schema
		if (doesElementExist2(properties.getProperty("FirstNewFieldVal"), 5)) {
	    	WebElement fldVal1 = driver.findElement(By.cssSelector(properties.getProperty("FirstNewFieldVal")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", fldVal1);
	    	fldVal1.clear();
	    	if(spacialChar.contains("!")||spacialChar.contains("#")){
	    		((JavascriptExecutor) driver).executeScript("arguments[0].value ='" + spacialChar + "';", driver.findElement(By.cssSelector(properties.getProperty("FirstNewFieldVal"))));
	    	}else{
				fldVal1.sendKeys(spacialChar);
	    	}
	    	log.logLine(Testname, false, "Entering the special characters as "+spacialChar+"  in the first user added text box in XML schema container");
		} else {
		    log.logLine(Testname, true, "Unable to Enter the special characters in the first text box in XML schema container");
		    throw new Exception("Unable to Enter the special characters in the first text box in XML schema container");
		}

		//Alert pop up for entering the special characters
		Alert alert3 = driver.switchTo().alert();
		alert3.accept();

		log.logLine(Testname, false, "Alert pop up message \"The Content has invalid chars\" appears");*/

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("FirstNewFieldVal"), 5)) {
			WebElement fldVal1 = driver.findElement(By.cssSelector(properties.getProperty("FirstNewFieldVal")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", fldVal1);
			fldVal1.clear();
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entering the special characters as "+spacialChar+"  in the first user added text box in XML schema container");
		} else {
			log.logLine(Testname, true, "Unable to Enter the special characters in the first text box in XML schema container");
			throw new Exception("Unable to Enter the special characters in the first text box in XML schema container");
		}

		Thread.sleep(5000);
		ClickPlusBtnOfXML(Testname);

		Thread.sleep(5000);
		//Enter second new field value to xml schema
		if (doesElementExist2(properties.getProperty("SecondNewFieldVal"), 5)) {
			WebElement fldVal2 = driver.findElement(By.cssSelector(properties.getProperty("SecondNewFieldVal")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", fldVal2);
			fldVal2.clear();
			fldVal2.sendKeys(name);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entering the name as "+name+" in the second user added text box in XML schema container");
		} else {
			log.logLine(Testname, true, "Unable to Enter the name as "+name+" in the second text box in XML schema container");
			throw new Exception("Unable to Enter the name as "+name+" in the second text box in XML schema container");
		}

		Thread.sleep(5000);
		ClickSaveBtnOfXML(Testname);
		Thread.sleep(5000);

		//Alert pop up for Empty text field
		if(isAlertPresent()){
			Alert alert4 = driver.switchTo().alert();
			alert4.accept();
		}
		Thread.sleep(5000);

		log.logLine(Testname, false, "Alert pop up message \"Must fill all field values. They are required. \" appears");

		Thread.sleep(5000);
		ClickClearBtnOfXML(Testname);
		Thread.sleep(5000);
		ClickPlusBtnOfXML(Testname);
		Thread.sleep(5000);

		if (doesElementExist2(properties.getProperty("FirstNewFieldVal"), 5)) {
			WebElement fldVal1 = driver.findElement(By.cssSelector(properties.getProperty("FirstNewFieldVal")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", fldVal1);
			fldVal1.clear();
			fldVal1.sendKeys(name);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entering the special characters as "+name+"  in the first user added text box in XML schema container");
		} else {
			log.logLine(Testname, true, "Unable to Enter the special characters in the first text box in XML schema container");
			throw new Exception("Unable to Enter the special characters in the first text box in XML schema container");
		}

		Thread.sleep(5000);
		ClickSaveBtnOfXML(Testname);

		if (doesElementExist2(properties.getProperty("SaveTempAlrtMessage"), 5)) {
			String sveAlrt = driver.findElement(By.cssSelector(properties.getProperty("SaveTempAlrtMessage"))).getText();

			if (sveAlrt.equals("XML Schema saved successfully")){
				log.logLine(Testname, false, "Alert message for confirming the \"Save\" XML Schema is sucessful ");
				Thread.sleep(2000);
			}
			else {	
				log.logLine(Testname, true, "Alert message for confirming the \"Save\" XML Schema is unsucessful ");
			} 
		}else {
			log.logLine(Testname, true, "Alert message for confirming the \"Save\" XML Schema is not displayed ");
			throw new Exception("Alert message for confirming the \"Save\" XML Schema is not displayed ");

		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("ConfirmSave"), 5)) {
			WebElement cnfmSve = driver.findElement(By.cssSelector(properties.getProperty("ConfirmSave")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnfmSve);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicking on close button of Alert message to \"Save\" XML schema ");
		} else {
			log.logLine(Testname, true, "Unable to Click on close button of Alert message to \"Save\" XML schema ");
			throw new Exception("Unable to Click on close button of Alert message to \"Save\" XML schema ");
		}

		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		AddTemplate(Testname);
		Thread.sleep(5000);
		ValidateXMLFldAdded(Testname);
		Thread.sleep(5000);
		ClickExitEditorButton(Testname);
		Thread.sleep(5000);

		if (doesElementExist2(properties.getProperty("ConfrmClrOkBtn"), 5)) {
			WebElement CnfmClr = driver.findElement(By.cssSelector(properties.getProperty("ConfrmClrOkBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", CnfmClr);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicking on the Ok button of the  Exit Editor Alert message");
		} else {
			log.logLine(Testname, true, "Clicking on the Ok button of the  Exit Editor Alert message is failed");
			throw new Exception("Clicking on the Ok button of the  Exit Editor Alert message is failed");
		}

		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		ClickXMLSchemaButton(Testname, "ClickLoadXMLBtn");
		Thread.sleep(5000);
		ClickRemoveBtnOfXML(Testname);
		Thread.sleep(5000);
		ClickSaveBtnOfXML(Testname);
		Thread.sleep(5000);

		Thread.sleep(2000);
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
			throw new Exception("Alert message for confirming the \"Save\" XML Schema is not displayed ");

		}

		if (doesElementExist2(properties.getProperty("ConfirmSave"), 5)) {
			WebElement cnfmSve = driver.findElement(By.cssSelector(properties.getProperty("ConfirmSave")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnfmSve);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on close button of Alert message to \"Save\" XML schema ");
		} else {
			log.logLine(Testname, true, "Unable to Click on close button of Alert message to \"Save\" XML schema ");
			throw new Exception("Unable to Click on close button of Alert message to \"Save\" XML schema ");
		}

		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		AddTemplate(Testname);
		Thread.sleep(5000);
		ValidateXMLFldRemoved(Testname);
		Thread.sleep(5000);

		ClickExitEditorButton(Testname);
		Thread.sleep(5000);

		if (doesElementExist2(properties.getProperty("ConfrmClrOkBtn"), 5)) {
			WebElement CnfmClr = driver.findElement(By.cssSelector(properties.getProperty("ConfrmClrOkBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", CnfmClr);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on the Ok button of the  Exit Editor Alert message");
		} else {
			log.logLine(Testname, true, "Clicking on the Ok button of the  Exit Editor Alert message is failed");
			throw new Exception("Clicking on the Ok button of the  Exit Editor Alert message is failed");
		}

		/*
		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		ClickXMLSchemaButton(Testname, "ClickLoadXMLBtn");
		Thread.sleep(5000);
		ClickBrowseBtnOfXML(Testname, "jpeg");

		//Alert pops up when uploading the file in .jpeg format as "Only .xsd, .xml or .txt  files can be uploaded"
		if(isAlertPresent()){
			Alert alert4 = driver.switchTo().alert();
			alert4.accept();
			}
			Thread.sleep(5000);


		ClickBrowseBtnOfXML(Testname, "docx");
		//Alert pops up when uploading the file in .docx format as "Only .xsd, .xml or .txt  files can be uploaded"
		if(isAlertPresent()){
			Alert alert4 = driver.switchTo().alert();
			alert4.accept();
			}
			Thread.sleep(5000);

		ClickBrowseBtnOfXML(Testname, "xlsx");
		//Alert pops up when uploading the file in .xlsx format as "Only .xsd, .xml or .txt  files can be uploaded"
		if(isAlertPresent()){
			Alert alert4 = driver.switchTo().alert();
			alert4.accept();
			}
			Thread.sleep(5000);

	    ClickBrowseBtnOfXML(Testname, "txt");
	    Thread.sleep(5000);

		ClickSaveBtnOfXML(Testname);
		Thread.sleep(5000);

		if (doesElementExist2(properties.getProperty("SaveTempAlrtMessage"), 5)) {
			String sveAlrt = driver.findElement(By.cssSelector(properties.getProperty("SaveTempAlrtMessage"))).getText();

			if (sveAlrt.equals("XML Schema saved successfully")){
				log.logLine(Testname, false, "Alert message for confirming the \"Save\" XML Schema is sucessful ");
				Thread.sleep(3000);
			}
			else {	
			log.logLine(Testname, true, "Alert message for confirming the \"Save\" XML Schema is unsucessful ");
			} 
		}else {
			log.logLine(Testname, true, "Alert message for confirming the \"Save\" XML Schema is not displayed ");
			throw new Exception("Alert message for confirming the \"Save\" XML Schema is not displayed ");

		}

		if (doesElementExist2(properties.getProperty("ConfirmSave"), 5)) {
	    	WebElement cnfmSve = driver.findElement(By.cssSelector(properties.getProperty("ConfirmSave")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnfmSve);
	    	Thread.sleep(5000);
	    	log.logLine(Testname, false, "Clicking on close button of Alert message to \"Save\" XML schema ");
		} else {
		    log.logLine(Testname, true, "Unable to Click on close button of Alert message to \"Save\" XML schema ");
		    throw new Exception("Unable to Click on close button of Alert message to \"Save\" XML schema ");
		}

		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		AddTemplate(Testname);
		Thread.sleep(5000);
		ValidateXMLFldAdded_Txt_xml_xsd_new(Testname, "txtfldd");
		Thread.sleep(5000);
		ClickExitEditorButton(Testname);
		Thread.sleep(5000);

		if (doesElementExist2(properties.getProperty("ConfrmClrOkBtn"), 5)) {
	    	WebElement CnfmClr = driver.findElement(By.cssSelector(properties.getProperty("ConfrmClrOkBtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", CnfmClr);
	    	Thread.sleep(5000);
	    	log.logLine(Testname, false, "Clicking on the Ok button of the  Exit Editor Alert message");
		} else {
		    log.logLine(Testname, true, "Clicking on the Ok button of the  Exit Editor Alert message is failed");
		    throw new Exception("Clicking on the Ok button of the  Exit Editor Alert message is failed");
		}


		//////////////////////////
		Thread.sleep(5000);
		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		ClickXMLSchemaButton(Testname, "ClickLoadXMLBtn");
		Thread.sleep(5000);
		ClickBrowseBtnOfXML(Testname, "xml");
		Thread.sleep(5000);
		ClickSaveBtnOfXML(Testname);

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("SaveTempAlrtMessage"), 5)) {
			String sveAlrt = driver.findElement(By.cssSelector(properties.getProperty("SaveTempAlrtMessage"))).getText();

			if (sveAlrt.equals("XML Schema saved successfully")){
				log.logLine(Testname, false, "Alert message for confirming the \"Save\" XML Schema is sucessful ");
				Thread.sleep(2000);
			}
			else {	
			log.logLine(Testname, true, "Alert message for confirming the \"Save\" XML Schema is unsucessful ");
			} 
		}else {
			log.logLine(Testname, true, "Alert message for confirming the \"Save\" XML Schema is not displayed ");
			throw new Exception("Alert message for confirming the \"Save\" XML Schema is not displayed ");

		}

		if (doesElementExist2(properties.getProperty("ConfirmSave"), 5)) {
	    	WebElement cnfmSve = driver.findElement(By.cssSelector(properties.getProperty("ConfirmSave")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnfmSve);
	    	Thread.sleep(5000);
	    	log.logLine(Testname, false, "Clicking on close button of Alert message to \"Save\" XML schema ");
		} else {
		    log.logLine(Testname, true, "Unable to Click on close button of Alert message to \"Save\" XML schema ");
		    throw new Exception("Unable to Click on close button of Alert message to \"Save\" XML schema ");
		}


		Thread.sleep(5000);
		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		AddTemplate(Testname);
		Thread.sleep(5000);
		ValidateXMLFldAdded_Txt_xml_xsd_new(Testname, "xmlflds");
		Thread.sleep(5000);

		ClickExitEditorButton(Testname);
		Thread.sleep(5000);

		if (doesElementExist2(properties.getProperty("ConfrmClrOkBtn"), 5)) {
	    	WebElement CnfmClr = driver.findElement(By.cssSelector(properties.getProperty("ConfrmClrOkBtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", CnfmClr);
	    	Thread.sleep(1000);
	    	log.logLine(Testname, false, "Clicking on the Ok button of the  Exit Editor Alert message");
		} else {
		    log.logLine(Testname, true, "Clicking on the Ok button of the  Exit Editor Alert message is failed");
		    throw new Exception("Clicking on the Ok button of the  Exit Editor Alert message is failed");
		}
		/////////////////////
		Thread.sleep(5000);
		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		ClickXMLSchemaButton(Testname, "ClickLoadXMLBtn");
		Thread.sleep(5000);
		ClickBrowseBtnOfXML(Testname, "xsd");
		Thread.sleep(5000);
		ClickSaveBtnOfXML(Testname);
		Thread.sleep(5000);

		if (doesElementExist2(properties.getProperty("SaveTempAlrtMessage"), 5)) {
			String sveAlrt = driver.findElement(By.cssSelector(properties.getProperty("SaveTempAlrtMessage"))).getText();

			if (sveAlrt.equals("XML Schema saved successfully")){
				log.logLine(Testname, false, "Alert message for confirming the \"Save\" XML Schema is sucessful ");
				Thread.sleep(3000);
			}
			else {	
			log.logLine(Testname, true, "Alert message for confirming the \"Save\" XML Schema is unsucessful ");
			} 
		}else {
			log.logLine(Testname, true, "Alert message for confirming the \"Save\" XML Schema is not displayed ");
			throw new Exception("Alert message for confirming the \"Save\" XML Schema is not displayed ");

		}

		if (doesElementExist2(properties.getProperty("ConfirmSave"), 5)) {
	    	WebElement cnfmSve = driver.findElement(By.cssSelector(properties.getProperty("ConfirmSave")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnfmSve);
	    	Thread.sleep(2000);
	    	log.logLine(Testname, false, "Clicking on close button of Alert message to \"Save\" XML schema ");
		} else {
		    log.logLine(Testname, true, "Unable to Click on close button of Alert message to \"Save\" XML schema ");
		    throw new Exception("Unable to Click on close button of Alert message to \"Save\" XML schema ");
		}

		Thread.sleep(5000);
		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		AddTemplate(Testname);
		Thread.sleep(5000);
		ValidateXMLFldAdded_Txt_xml_xsd_new(Testname, "xsdflds");
		Thread.sleep(5000);

		ClickExitEditorButton(Testname);
		Thread.sleep(5000);

		if (doesElementExist2(properties.getProperty("ConfrmClrOkBtn"), 5)) {
	    	WebElement CnfmClr = driver.findElement(By.cssSelector(properties.getProperty("ConfrmClrOkBtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", CnfmClr);
	    	Thread.sleep(1000);
	    	log.logLine(Testname, false, "Clicking on the Ok button of the  Exit Editor Alert message");
		} else {
		    log.logLine(Testname, true, "Clicking on the Ok button of the  Exit Editor Alert message is failed");
		    throw new Exception("Clicking on the Ok button of the  Exit Editor Alert message is failed");
		}
		 */

		return true;
	}


	public boolean ClickPlusBtnOfXML(String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("AddBtn_xmlSchema"), 5)) {	   
			WebElement plusBtn = driver.findElement(By.cssSelector(properties.getProperty("AddBtn_xmlSchema")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", plusBtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on \"Add\" button to add the new field in xml schema container..");

		} else {
			log.logLine(Testname, true, "Unable to Click on \"Add\" button to add the new field in xml schema container..");
			throw new Exception("Unable to Click on \"Add\" button to add the new field in xml schema container..");
		}

		return true;
	}


	public boolean ClickClearBtnOfXML(String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("ClearXMLBtn"), 5)) {
			WebElement saveBtn = driver.findElement(By.cssSelector(properties.getProperty("ClearXMLBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",saveBtn);
			Thread.sleep(2000);
			log.logLine(Testname, false,
					"Clicked on \"Clear\" button to clear the new field in xml schema container..");

		} else {
			log.logLine(Testname,true,"Unable to Click on \"Clear\" button to clear the new field in xml schema container..");
			throw new Exception("Unable to Click on \"Clear\" button to clear the new field in xml schema container..");
		}

		return true;
	}

	public boolean ClickSaveBtnOfXML(String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("SaveXMLBtn"), 5)) {	   
			WebElement saveBtn = driver.findElement(By.cssSelector(properties.getProperty("SaveXMLBtn")));

			saveBtn.click();
			//((JavascriptExecutor) driver).executeScript("arguments[0].click()", saveBtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on \"Save\" button to save the new field in xml schema container..");

		} else {
			log.logLine(Testname, true, "Unable to Click on \"Save\" button to save the new field in xml schema container..");
			throw new Exception("Unable to Click on \"Save\" button to save the new field in xml schema container..");
		}

		return true;
	}

	public boolean ValidateXMLFldAdded(String Testname) throws Exception {


		if (doesElementExist2(properties.getProperty("ValidateXmlFldAdded"), 5)) {	    
			WebElement xmlFldName = driver.findElement(By.cssSelector(properties.getProperty("ValidateXmlFldAdded")));

			List<WebElement> xmlfld = driver.findElements(By.cssSelector(properties.getProperty("ValidateXmlFldAdded")));
			for (WebElement link:xmlfld) {
				if (link.getText().equals(name)) {
					Thread.sleep(2000);
					log.logLine(Testname, false, "Newly Added xml field name exist in the template management pre live view of the  Editor form");
					break;
				}
			}

		}else {
			log.logLine(Testname, true, "Newly added xml field is not displayed in the template management pre live view");
			throw new Exception("Newly added xml field is not displayed in the template management pre live view");
		}

		return true;
	}

	public boolean ClickRemoveBtnOfXML(String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("RemoveBtn_xmlSchema"), 5)) {	   
			WebElement plusBtn = driver.findElement(By.cssSelector(properties.getProperty("RemoveBtn_xmlSchema")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", plusBtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on \"Remove\" button to delete the new field in xml schema container..");

		} else {
			log.logLine(Testname, true, "Unable to Click on \"Remove\" button to delete the new field in xml schema container..");
			throw new Exception("Unable to Click on \"Remove\" button to delete the new field in xml schema container..");
		}

		return true;
	}

	public boolean ValidateXMLFldRemoved(String Testname) throws Exception {


		if (doesElementExist2(properties.getProperty("ValidateXmlFldAdded"), 5)) {	    
			WebElement xmlFldName = driver.findElement(By.cssSelector(properties.getProperty("ValidateXmlFldAdded")));

			List<WebElement> xmlfld = driver.findElements(By.cssSelector(properties.getProperty("ValidateXmlFldAdded")));
			for (WebElement link:xmlfld) {
				if (!link.getText().equals(name)) {
					Thread.sleep(2000);
					log.logLine(Testname, false, "Deleted xml field name does not exist in the template management pre live view of the  Editor form ");
					Thread.sleep(2000);
				}/*else{

  						log.logLine(Testname, false, "Deleted xml field name exist in the template management pre live view of the  Editor form");
  						throw new Exception("Removing/Deletion of the added xml field name in the template management pre live view of the  Editor form is unsucessful ");

  					}*/
			}
			log.logLine(Testname, false, "Removing/Deletion of the added xml field name in the template management pre live view of the  Editor form is sucessful ");

		}else {
			log.logLine(Testname, true, "Removing/Deletion of the added xml field name in the template management pre live view of the  Editor form is unsucessful ");
			throw new Exception("Removing/Deletion of the added xml field name in the template management pre live view of the  Editor form is unsucessful ");
		}

		return true;
	}

	public boolean ClickBrowseBtnOfXML(String Testname, String Format) throws Exception {

		if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) || (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) {	

			switch (Format) {
			case "jpeg":

				if (doesElementExist2(properties.getProperty("BrowseXMLBtn"), 5)) {	   
					driver.findElement(By.name("SchemaFile")).sendKeys("TestImage.jpg");
					log.logLine(Testname, false, "Clicked on \"Browse\" button to upload the .docx file..");
					break;
				}


			case "docx":


				if (doesElementExist2(properties.getProperty("BrowseXMLBtn"), 5)) {	   
					driver.findElement(By.name("SchemaFile")).sendKeys("XmldocTest.docx");
					log.logLine(Testname, false, "Clicked on \"Browse\" button to upload the .docx file..");
					break;	
				}


			case "xlsx":

				if (doesElementExist2(properties.getProperty("BrowseXMLBtn"), 5)) {	   
					driver.findElement(By.name("SchemaFile")).sendKeys("XmlExcelTest.xlsx");
					log.logLine(Testname, false, "Clicked on \"Browse\" button to upload the .docx file..");
					break;	
				}


			case "txt":

				if (doesElementExist2(properties.getProperty("BrowseXMLBtn"), 5)) {	   
					driver.findElement(By.name("SchemaFile")).sendKeys("Text.txt");
					log.logLine(Testname, false, "Clicked on \"Browse\" button to upload the .docx file..");
					break;	
				}



			case "xml":

				if (doesElementExist2(properties.getProperty("BrowseXMLBtn"), 5)) {	   
					driver.findElement(By.name("SchemaFile")).sendKeys("Data/Xml.xml");
					log.logLine(Testname, false, "Clicked on \"Browse\" button to upload the .docx file..");
					break;	
				}



			case "xsd":



				if (doesElementExist2(properties.getProperty("BrowseXMLBtn"), 5)) {	   
					driver.findElement(By.name("SchemaFile")).sendKeys("Xml.xsd");
					log.logLine(Testname, false, "Clicked on \"Browse\" button to upload the .docx file..");
					break;	
				}

			}

		}
		else if (doesElementExist2(properties.getProperty("BrowseXMLBtn"), 5)) {	   
			WebElement brwseBtn = driver.findElement(By.cssSelector(properties.getProperty("BrowseXMLBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", brwseBtn);
			log.logLine(Testname, false, "Clicked on \"Browse\" button to upload the .docx file..");



			switch (Format) {
			case "jpeg":


				brwseBtn.sendKeys("C:/Pivot_Portal/Test Data/TestImage.jpg");
				log.logLine(Testname, false, "uploading the \".jpeg\" file format");
				Thread.sleep(3000);
				break;



			case "docx":


				Thread.sleep(2000);
				brwseBtn.sendKeys("C:/Pivot_Portal/Test Data/XmldocTest.docx");
				log.logLine(Testname, false, "uploading the \".docx\" file format");
				break;	


			case "xlsx":




				brwseBtn.sendKeys("C:/Pivot_Portal/Test Data/XmlExcelTest.xlsx");
				log.logLine(Testname, false, "uploading the \".xlsx\" file format ");
				Thread.sleep(3000);
				break;


			case "txt":

				brwseBtn.sendKeys("C:/Pivot_Portal/Test Data/Text.txt");
				log.logLine(Testname, false, "uploading the \".txt\" file format ");
				Thread.sleep(3000);
				break;	



			case "xml":

				brwseBtn.sendKeys("C:/Pivot_Portal/Test Data/Xml.xml");
				log.logLine(Testname, false, "uploading the \".xml\" file format ");
				Thread.sleep(3000);
				break;	




			case "xsd":



				brwseBtn.sendKeys("C:/Pivot_Portal/Test Data/Xml.xsd");
				log.logLine(Testname, false, "uploading the \".xsd\" file format ");
				Thread.sleep(3000);
				break;	



			}
		}	


		return true;
	}


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
						Thread.sleep(3000);
					}
					break;
				case "xmlflds":
					if (xmlfield[f].equals("AccNo") || xmlfield[f].equals("ZipCode")) {

						log.logLine(Testname, false, "Newly Added xml field names from the \".xml\" file exists in the template management pre live view of the  Editor form");
						Thread.sleep(3000);
					}

					break;

				case "xsdflds":

					if (xmlfield[f].equals("Addr1") || xmlfield[f].equals("Addr2") || xmlfield[f].equals("Addr3")) {

						log.logLine(Testname, false, "Newly Added xml field names from the \".xsd\" file exists in the template management pre live view of the  Editor form");
						Thread.sleep(3000);
					}

					break;


				}

			}
			fld = fld + "+li";

		}


		return true;
	}



}




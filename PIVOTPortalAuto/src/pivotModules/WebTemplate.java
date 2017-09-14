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

public class WebTemplate extends Page {

	int paperID = (int) Math.round(Math.random() * (9999 - 1000 + 1) + 1000);
	public String AccNo = Integer.toString(paperID);

	public String TemplateName;
	public String TempltDecsp;
	public String EditTemplateName;
	public String EditTempltDecsp;
	public String notemplts;
	public String URL, LinkID , LnkText ,LnkToolTp;

	public WebTemplate(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}	
	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}

	String firstWinHandle = null;
	WebDriverWait wait = new WebDriverWait(driver, 20);

	public boolean Templateid_duplicity(String RandNo, String Testname) throws Exception {

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


		Actions builder = new Actions(driver);	        
		WebElement mnuElement = driver.findElement(By.xpath(properties.getProperty("TemplateManagement")));	
		if (doesElementExist(properties.getProperty("TemplateManagement"), 10)) {			  
			// Move cursor to the Main Menu Element  
			builder.moveToElement(mnuElement).perform();		
			// Clicking on the Hidden SubMenu  
			WebElement oldpivt = driver.findElement(By.xpath(properties.getProperty("WebTemplate")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", oldpivt);		
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicking on Web Templates is successful..");		      
		} else {
			log.logLine(Testname, true, "Clicking on Web Templates is failed");
			throw new Exception("Clicking on Web Templates is failed");			
		}

		Thread.sleep(2000);
		driver.switchTo().frame("iframeContainer");

		SavedTypesDropDown(Testname);

		Thread.sleep(6000);

		Linkidduplicity(Testname);


		return true;

	}

	//********************Edelivery tab********************************************************************************

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


	public boolean Linkidduplicity(String Testname ) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		String Tempnme = test.readColumnData("TempName", sheetname);
		String Descnme = test.readColumnData("TempDesc", sheetname);
		String DupTempdesc = test.readColumnData("DuplicateTempdesc", sheetname);


		if (doesElementExist2(properties.getProperty("savedtypetitle"), 5)) {
			WebElement title = driver.findElement(By.cssSelector(properties.getProperty("savedtypetitle")));
			if(title.getText().equalsIgnoreCase("eArchive-ConsentPage")){
				Thread.sleep(3000);
				if (doesElementExist2(properties.getProperty("Addbutton"), 5)) {
					WebElement addbtn = driver.findElement(By.cssSelector(properties.getProperty("Addbutton")));
					Highlight(addbtn);
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", addbtn);
					Thread.sleep(2000);
					log.logLine(Testname, false, "Click on Web Template add button is Successful");
				} else {
					log.logLine(Testname, true, "Click on Web Template add button is failed");
					driver.switchTo().defaultContent();
					new Exception("Click on Web Template add button is failed");
				}}else if(title.getText().equalsIgnoreCase("test template")){
					Thread.sleep(3000);
					if (doesElementExist2(properties.getProperty("Addbutton"), 5)) {
						WebElement addbtn = driver.findElement(By.cssSelector(properties.getProperty("Addbutton")));
						Highlight(addbtn);
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", addbtn);
						Thread.sleep(2000);
						log.logLine(Testname, false, "Click on Web Template add button is Successful");
					} else {
						log.logLine(Testname, true, "Click on Web Template add button is failed");
						driver.switchTo().defaultContent();
						new Exception("Click on Web Template add button is failed");
					}}}


		Thread.sleep(3000);

		if (doesElementExist2(properties.getProperty("TempltNameTxtBox"), 5)) {
			WebElement qtyday = driver.findElement(By.cssSelector(properties.getProperty("TempltNameTxtBox")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", qtyday);
			Thread.sleep(2000);
			qtyday.sendKeys(Tempnme);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entering the text as "+Tempnme+" in \"Template Name\" textbox ");
		} else {
			log.logLine(Testname, true, "Unable to Enter the text as "+Tempnme+" in \"Template Name\" textbox ");
			throw new Exception("Unable to Enter the text as "+TemplateName+" in \"Template Name\" textbox ");
		}


		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("Linktrackingchkbox"), 5)) {
			WebElement lnkchkbx = driver.findElement(By.cssSelector(properties.getProperty("Linktrackingchkbox")));

			if (!lnkchkbx.isSelected())
			{
				lnkchkbx.click();
				Thread.sleep(2000);
				log.logLine(Testname, false, "Link tracking Checkbox is Checked");
			}else{
				log.logLine(Testname, false, "Link tracking Checkbox is already Checked");
			}
		} else {
			log.logLine(Testname, true, "Click on Link Tracking Checkbox is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Link Tracking Checkbox is failed");
		}


		if (doesElementExist2(properties.getProperty("HTMLTag"), 5)) {
			WebElement htmltag = driver.findElement(By.cssSelector(properties.getProperty("HTMLTag")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", htmltag);	 
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on HTML Tag is Successful");
		} else {
			log.logLine(Testname, true, "Click on HTML Tag is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on HTML Tag is failed");
		}


		if (doesElementExist2(properties.getProperty("HTMLDesc"), 5)) {
			WebElement HTMLdesc = driver.findElement(By.cssSelector(properties.getProperty("HTMLDesc")));
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
		Thread.sleep(3000);
		ConfirmSave(Testname) ;
		Thread.sleep(1000);

		//Entering the duplicate link

		if (doesElementExist2(properties.getProperty("HTMLTag"), 5)) {
			WebElement htmltag = driver.findElement(By.cssSelector(properties.getProperty("HTMLTag")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", htmltag);	 
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on HTML Tag is Successful");
		} else {
			log.logLine(Testname, true, "Click on HTML Tag is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on HTML Tag is failed");
		}


		if (doesElementExist2(properties.getProperty("HTMLDesc"), 5)) {
			WebElement HTMLdesc = driver.findElement(By.cssSelector(properties.getProperty("HTMLDesc")));
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
		Thread.sleep(2000);

		Alert alert = driver.switchTo().alert();
		String Text=alert.getText();
		Thread.sleep(2000);
		log.logLine(Testname, false, "Reading the message as: "+Text+" from alert pop up");

		alert.accept();

		Thread.sleep(2000);


		if (doesElementExist2(properties.getProperty("DeleteTemplatebtn"), 5)) {
			WebElement Htmlbtn = driver.findElement(By.cssSelector(properties.getProperty("DeleteTemplatebtn")));
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

		Thread.sleep(2000);
		SavedTypesDropDown(Testname);

		return true;
	}

	public void Highlight(WebElement choseacts) throws Exception{
		for (int i = 0; i < 2; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript( "arguments[0].setAttribute('style', arguments[1]);", choseacts , "color: red; border: 5px solid red;");
			Thread.sleep(1000);
			js.executeScript( "arguments[0].setAttribute('style', arguments[1]);", choseacts, "");
		}
	}
} 






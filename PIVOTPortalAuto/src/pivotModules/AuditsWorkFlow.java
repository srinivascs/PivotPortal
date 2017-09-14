package pivotModules;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import launchAuto.Initialization;
import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class AuditsWorkFlow extends Page{

	public AuditsWorkFlow(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}	
	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}

	public String Wrkflwnme,dateclmn,AccNo,BeforeeditWrkflwid,Rqdapprvr,AftereditWrkflwid,Currentwrkflwid,Historywrkflwwid,Historywrkflnme,Historywrkflwdate;


	public boolean Clientappsearch(String Testname) throws Exception  {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(1000);
		driver.switchTo().defaultContent();
		WebElement retelm2 = waitForElement(properties.getProperty("selClint1"));		     


		if (doesElementExist2(properties.getProperty("Audits"), 5)) {	    
			WebElement proofsmnu = driver.findElement(By.cssSelector(properties.getProperty("Audits")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", proofsmnu);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Navigation to Proofs page successful");
		} else {
			log.logLine(Testname, true, "Clicking on Proofs menu is failed");
			throw new Exception("Clicking on Proofs menu is failed");
		}

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


		if (doesElementExist2(properties.getProperty("WorkFlowmodule"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("WorkFlowmodule")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);	    	 
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Click on WorkFlowmodule is successfull");
		} else {
			log.logLine(Testname, true, "WorkFlow module is not displayed");
			driver.switchTo().defaultContent();
			throw new Exception("WorkFlow module is not displayed");
		}

		return true;


		
				

	}

	public boolean AuditsWorkFlowfunctionality(String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	 

		int paperID = (int) Math.round(Math.random() * (9999 - 1000 + 1) + 1000);		
		AccNo = Integer.toString(paperID);

		Wrkflwnme = test.readColumnData("WorkFlowname", sheetname);
		Rqdapprvr= test.readColumnData("RequiredApprover", sheetname);

		Thread.sleep(10000);
		driver.switchTo().frame("iframeContainer");	  
		PleasewaitDisappear();

		String[] Sort = new String[150];
		int length = Sort.length;



		if (doesElementExist2(properties.getProperty("Createworkflowbtn"), 5)) {
			WebElement crtwrkbtn = driver.findElement(By.cssSelector(properties.getProperty("Createworkflowbtn")));
			//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",crtwrkbtn);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", crtwrkbtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on Create WorkFlow button is successfull");
			Thread.sleep(2000);
		} else {
			log.logLine(Testname, true, "Clicking on Create WorkFlow button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Create WorkFlow button is failed");
		}


		if (doesElementExist2(properties.getProperty("Workflowname"), 5)) {
			WebElement crtwrkbtn = driver.findElement(By.cssSelector(properties.getProperty("Workflowname")));
			crtwrkbtn.clear();
			crtwrkbtn.sendKeys(Wrkflwnme+AccNo);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entering theWorkflow name as <<<< "+Wrkflwnme+AccNo+" >>>> ");
			Thread.sleep(2000);
		} else {
			log.logLine(Testname, true, "Clicking on Create WorkFlow button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Create WorkFlow button is failed");
		}


		if (doesElementExist2(properties.getProperty("Suppressionchk"), 5)) {
            WebElement sprsnchk = driver.findElement(By.cssSelector(properties.getProperty("Suppressionchk")));

            if (!sprsnchk.isSelected())
            {
            	sprsnchk.click();
            	Thread.sleep(5000);
            	log.logLine(Testname, false, "Click on Suppression Checkbox");
            }else{
            	log.logLine(Testname, false, "Suppression Checkbox is already Checked");
            }
        }else{
            log.logLine(Testname, true, "Suppression Checkbox is not present");
            driver.switchTo().defaultContent();
            throw new Exception("Suppression Checkbox is not present");
        }


		if (doesElementExist2(properties.getProperty("Requiredapprover"), 5)) {
			WebElement crtwrkbtn = driver.findElement(By.cssSelector(properties.getProperty("Requiredapprover")));
			crtwrkbtn.sendKeys(Rqdapprvr);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entering the Required approver name as <<<< "+Rqdapprvr+" >>>> ");

			List<WebElement> editbtn = driver.findElements(By.cssSelector(properties.getProperty("Requiredaprvlist")));
			for (WebElement lnk:editbtn) {
				if (lnk.getText().equals("ABCReviewers")){
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(5000);
					log.logLine(Testname, false, "Selecting the Required Reviewer as <<<< "+Rqdapprvr+" >>>> from drop down list ");
					break;
				} 
			}
		}else{
            log.logLine(Testname, true, "Entering the Required approver name is failed");
            driver.switchTo().defaultContent();
            throw new Exception("Entering the Required approver name is failed");
        }
		
		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Otherapprover"), 5)) {
			WebElement crtwrkbtn = driver.findElement(By.cssSelector(properties.getProperty("Otherapprover")));
			crtwrkbtn.sendKeys(Rqdapprvr);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entering the Required approver name as <<<< "+Rqdapprvr+" >>>> ");

			List<WebElement> editbtn = driver.findElements(By.cssSelector(properties.getProperty("Otheraprlist")));
			for (WebElement lnk:editbtn) {
				if (lnk.getText().equals("ABCReviewers")){
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(5000);
					log.logLine(Testname, false, "Selecting the Required Reviewer as <<<< "+Rqdapprvr+" >>>> from drop down list ");
					break;
				} 
			}
		}else{
            log.logLine(Testname, true, "Entering the Required approver name is failed");
            driver.switchTo().defaultContent();
            throw new Exception("Entering the Required approver name is failed");
        }

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("Status"), 5)) {
			WebElement status = driver.findElement(By.cssSelector(properties.getProperty("Status")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", status);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Selecting the Status as Both");
		}else{
			log.logLine(Testname, true, "Selecting the Status as Both is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Create WorkFlow button is failed");
		}

		if (doesElementExist2(properties.getProperty("SaveWrkflowbtn"), 5)) {
			WebElement status = driver.findElement(By.cssSelector(properties.getProperty("SaveWrkflowbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", status);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Click on Save Workflow button");
		}else{
			log.logLine(Testname, true, "Click on Save Workflow button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Save Workflow button is failed");
		}

		Thread.sleep(10000);
		Pagecounts(Testname);
		Thread.sleep(5000);
		GridValidation(Testname,"Edit");
		Thread.sleep(2000);
		EditWorkflow(Testname);

		Thread.sleep(10000);
		Pagecounts(Testname);
		Thread.sleep(2000);
		GridValidation(Testname,"Edit");
		Thread.sleep(2000);
		EditValidation(Testname);

		Thread.sleep(2000);
		GridValidation(Testname,"View");
		Thread.sleep(2000);
		EditValidation1(Testname);

		Thread.sleep(2000);
		GridValidation(Testname,"History");
		Thread.sleep(2000);
		HistoryValidation(Testname);


		return true;
	}


	public boolean Pagecounts(String Testname) throws Exception{

		if (doesElementExist(properties.getProperty("Page"), 5)) {	 
			WebElement pge = driver.findElement(By.xpath(".//*[@id='workflow-list-grid']/div/span[1]/span/span/span[2]/span"));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",pge);
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", pge);
			log.logLine(Testname, false, "Click on Page Selection button");
			Thread.sleep(2000);

			List<WebElement> editbtn = driver.findElements(By.cssSelector(properties.getProperty("Select")));
			for (WebElement lnk:editbtn) {
				if (lnk.getText().equals("100")){
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					PleasewaitDisappear();
					Thread.sleep(2000);
					log.logLine(Testname, false, "Selecting the Option 100 Per Page from Drop down");
					break;
				} 
			}
		}
		return true;
	}


	public boolean GridValidation(String Testname,String Option) throws Exception {
		Thread.sleep(4000);

		String[] Sort = new String[150];
		String[] Sort1 = new String[150];
		int length = Sort.length;
		String row="tr";

		Thread.sleep(4000);
		if (doesElementExist2(properties.getProperty("Createworkflowbtn"), 5)) {
			WebElement crtwrkbtn = driver.findElement(By.cssSelector(properties.getProperty("Createworkflowbtn")));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",crtwrkbtn);
			Thread.sleep(8000);
		}

		if (doesElementExist2(properties.getProperty("WorkFlowgrid"), 5)) {
			List<WebElement> DataCnt= driver.findElements(By.cssSelector("div[id='workflow-list-grid'] table tbody tr td a"));
			for(int i = 0; i < DataCnt.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='workflow-list-grid'] table tbody "+row+" td a")).getText();
				if(Sort[i].equals(Wrkflwnme+AccNo)){
					log.logLine(Testname, false, "Work Flow created successfully....Created workflow is <<<<<"+Wrkflwnme+AccNo+" >>>>>");
					dateclmn= driver.findElement(By.cssSelector("div[id='workflow-list-grid'] table tbody "+row+" td+td")).getText();
					log.logLine(Testname, false, "Reading the WorkFlow date as <<<< "+dateclmn+" >>>>");

					WebElement chseactn= driver.findElement(By.cssSelector("div[id='workflow-list-grid'] table tbody "+row+" td+td+td+td+td div span span span[class='k-input']"));
					((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",chseactn);
					Thread.sleep(2000);
					chseactn.click();
					//((JavascriptExecutor) driver).executeScript("arguments[0].click()", chseactn);
					Thread.sleep(2000);
					log.logLine(Testname, false, "Click on Choose Action");

					List<WebElement> editbtn = driver.findElements(By.cssSelector(properties.getProperty("Chooseactnlst")));
					for (WebElement lnk:editbtn) {
						if (lnk.getText().equals(Option)){
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(10000);
							log.logLine(Testname, false, "Selecting the option as <<<< "+Option+" >>>> from drop down list ");
							break;
						} 
					}

					break;
				}
				row = row + "+tr";
			}

		} else {
			log.logLine(Testname, true, "No Data in the grid");
			driver.switchTo().defaultContent();
			throw new Exception("No Data in the grid");
		}

		return true;
	}

	public boolean EditWorkflow(String Testname) throws Exception {

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("Workflowname"), 5)) {
			String readonly = driver.findElement(By.cssSelector(properties.getProperty("Workflowname"))).getAttribute("readonly");
			log.logLine(Testname, false, "Workflow Name cannot be editted because mode of the text box is read only and readonly mode displays boolean value as <<<< "+readonly+" >>>>");
			Thread.sleep(2000);
		} else {
			log.logLine(Testname, true, "Workflow Name can be editted and validation is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Workflow Name can be editted and validation is failed");
		}

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("Workflowid"), 5)) {
			BeforeeditWrkflwid = driver.findElement(By.cssSelector(properties.getProperty("Workflowid"))).getText();
			Thread.sleep(2000);
			log.logLine(Testname, false, "WorkFlow ID before editing is << "+BeforeeditWrkflwid+" >>");
		}else{
			log.logLine(Testname, true, "Reading WorkFlow ID is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Reading WorkFlow ID is failed");
		}

		if (doesElementExist2(properties.getProperty("Deleteusrid"), 5)) {
			WebElement del = driver.findElement(By.cssSelector(properties.getProperty("Deleteusrid")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", del);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on delete for user id");
		}else{
			log.logLine(Testname, true, "Click on delete for user id is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on delete for user id is failed");
		}

		if (doesElementExist2(properties.getProperty("Requiredapprover"), 5)) {
			WebElement crtwrkbtn = driver.findElement(By.cssSelector(properties.getProperty("Requiredapprover")));
			crtwrkbtn.clear();
			Thread.sleep(2000);
			crtwrkbtn.sendKeys(Rqdapprvr);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Selecting the Required approver as <<<< "+Rqdapprvr+" >>>> ");

			List<WebElement> editbtn = driver.findElements(By.cssSelector(properties.getProperty("Requiredaprvlist")));
			for (WebElement lnk:editbtn) {
				if (lnk.getText().equals("ABCReviewers")){
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(5000);
					log.logLine(Testname, false, "Selecting back the Required Reviewer as <<<< "+Rqdapprvr+" >>>> from drop dwn list ");
					break;
				} 
			}
		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("SaveWrkflowbtn"), 5)) {
			WebElement status = driver.findElement(By.cssSelector(properties.getProperty("SaveWrkflowbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", status);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on Save Workflow button");
		}else{
			log.logLine(Testname, true, "Click on Save Workflow button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Save Workflow button is failed");
		}


		Thread.sleep(6000);
		Alert alert = driver.switchTo().alert();
		alert.accept();

		/*Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("SaveWrkflowbtn"), 5)) {
			WebElement status = driver.findElement(By.cssSelector(properties.getProperty("SaveWrkflowbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", status);
			log.logLine(Testname, false, "Click on Save Workflow button");
		}else{
			log.logLine(Testname, false, "Click on Save Workflow button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Save Workflow button is failed");
		}*/

		return true;
	}

	public boolean EditValidation(String Testname) throws Exception {

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("Workflowid"), 5)) {
			AftereditWrkflwid = driver.findElement(By.cssSelector(properties.getProperty("Workflowid"))).getText();
			if(!AftereditWrkflwid.equals(BeforeeditWrkflwid)){
				log.logLine(Testname, false, "WorkFlow ID After editing is << "+AftereditWrkflwid+" >>");
				Thread.sleep(2000);
			}else{
				log.logLine(Testname, true, "WorkFlow ID After editing is << "+AftereditWrkflwid+" >>");

			}
		}else{
			log.logLine(Testname, true, "WorkFlow Id's before and after editting are same");
			driver.switchTo().defaultContent();
			throw new Exception("WorkFlow Id's before and after editting are same");
		}

		if (doesElementExist2(properties.getProperty("Cancelwrkflwbtn"), 5)) {
			WebElement status = driver.findElement(By.cssSelector(properties.getProperty("Cancelwrkflwbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", status);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on Cancel Workflow button");
		}else{
			log.logLine(Testname, true, "Click on Cancel Workflow button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Cancel Workflow button is failed");
		}



		return true;
	}

	public boolean EditValidation1(String Testname) throws Exception {

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("Workflowid"), 5)) {
			Currentwrkflwid = driver.findElement(By.cssSelector(properties.getProperty("Workflowid"))).getText();
			if(Currentwrkflwid.equals(AftereditWrkflwid)){
				Thread.sleep(2000);
				log.logLine(Testname, false, "WorkFlow ID <<<< "+Currentwrkflwid+" >>>> is same as <<<< "+AftereditWrkflwid+" >>>> in View workflow window");
			}else{
				log.logLine(Testname, true, "WorkFlow ID <<<< "+Currentwrkflwid+" >>>> is same as <<<< "+AftereditWrkflwid+" >>>> in View workflow window");
			}
		}else{
			log.logLine(Testname, true, "WorkFlow Id's are not same");
			driver.switchTo().defaultContent();
			throw new Exception("WorkFlow Id's are not same");
		}

		if (doesElementExist2(properties.getProperty("Cancelwrkflwbtn"), 5)) {
			WebElement status = driver.findElement(By.cssSelector(properties.getProperty("Cancelwrkflwbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", status);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on Cancel Workflow button");
		}else{
			log.logLine(Testname, true, "Click on Cancel Workflow button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Cancel Workflow button is failed");
		}



		return true;
	}

	public boolean HistoryValidation(String Testname) throws Exception {

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("HistryWorkflowid"), 5)) {
			Historywrkflwwid = driver.findElement(By.cssSelector(properties.getProperty("HistryWorkflowid"))).getText();
			String arr[] =BeforeeditWrkflwid.split(":");
			String bfrwrkflwid=arr[1].trim();
			if(Historywrkflwwid.equals(bfrwrkflwid)){
				Thread.sleep(2000);
				log.logLine(Testname, false, "WorkFlow ID <<<< "+Historywrkflwwid+" >>>> is same as <<<< "+bfrwrkflwid+" >>>> in Workflow History window");
			}
			else{
				log.logLine(Testname, true, "WorkFlow ID <<<< "+Historywrkflwwid+" >>>> is same as <<<< "+bfrwrkflwid+" >>>> in Workflow History window");

			}
		}else{
			log.logLine(Testname, true, "WorkFlow Id's are not same");
			driver.switchTo().defaultContent();
			throw new Exception("WorkFlow Id's are not same");
		}

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("HistryWorkflowname"), 5)) {
			Historywrkflnme = driver.findElement(By.cssSelector(properties.getProperty("HistryWorkflowname"))).getText();
			if(Historywrkflnme.equals(Wrkflwnme+AccNo)){
				Thread.sleep(2000);
				log.logLine(Testname, false, "WorkFlow name <<<< "+Historywrkflnme+" >>>> matches with <<<< "+Wrkflwnme+AccNo+" >>>> in Workflow History window");
			}else{
				log.logLine(Testname, true, "WorkFlow name <<<< "+Historywrkflnme+" >>>> matches with <<<< "+Wrkflwnme+AccNo+" >>>> in Workflow History window");

			}
		}else{
			log.logLine(Testname, true, "WorkFlow names are not same");
			driver.switchTo().defaultContent();
			throw new Exception("WorkFlow names are not same");
		}

		if (doesElementExist2(properties.getProperty("HistryWorkflowdate"), 5)) {
			Historywrkflwdate = driver.findElement(By.cssSelector(properties.getProperty("HistryWorkflowdate"))).getText();
			if(Historywrkflwdate.contains(dateclmn)){
				Thread.sleep(2000);
				log.logLine(Testname, false, "WorkFlow Date <<<< "+Historywrkflwdate+" >>>> matches with <<<< "+dateclmn+" >>>> in Workflow History window");
			}else{
				log.logLine(Testname, true, "WorkFlow Date <<<< "+Historywrkflwdate+" >>>> matches with <<<< "+dateclmn+" >>>> in Workflow History window");

			}

		}else{
			log.logLine(Testname, true, "WorkFlow names are not same");
			driver.switchTo().defaultContent();
			throw new Exception("WorkFlow names are not same");
		}


		if (doesElementExist2(properties.getProperty("HistryWorkflowcrtdusr"), 5)) {
			String crtedusr = driver.findElement(By.cssSelector(properties.getProperty("HistryWorkflowcrtdusr"))).getText();
			Thread.sleep(2000);
			log.logLine(Testname, false, "Reading the Created User as <<<< "+crtedusr+" >>>>");
		}else{
			log.logLine(Testname, false, "Reading the Created User is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Reading the Created User is failed");
		}



		if (doesElementExist2(properties.getProperty("Historyclosebtn"), 5)) {
			WebElement status = driver.findElement(By.cssSelector(properties.getProperty("Historyclosebtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", status);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on Close button for History Workflow Popup");
		}else{
			log.logLine(Testname, false, "Click on Close button for History Workflow Popup is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on Close button for History Workflow Popup is failed");
		}



		return true;
	}

}



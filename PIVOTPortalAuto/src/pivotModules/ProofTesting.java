package pivotModules;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

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


public class ProofTesting extends Page{
	public static String pdfname;
	public static String decp1, decp2, decp3 ;

	public ProofTesting(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}	
	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}	


	public boolean ProfileSettings(String AccNo, String Testname) throws Exception  {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(1000);
		driver.switchTo().defaultContent();

		Thread.sleep(3000);
		if (doesElementExist2(properties.getProperty("cancelbtn"), 5)) {    
			WebElement canlbtn = driver.findElement(By.cssSelector(properties.getProperty("cancelbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", canlbtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the cancel button of the clent/app selection pop up window");
		} else {
			log.logLine(Testname, true, "Clicking on the cancel button  of the clent/app selection pop up window is failed");
			//throw new Exception("Clicking on the cancel button of the clent/app selection pop up window is failed");
		}	 

		Thread.sleep(5000);
		Actions builder = new Actions(driver);
		if (doesElementExist2(properties.getProperty("MyProfileLinkTest"), 5)) { 
			List<WebElement> myPrfle = driver.findElements(By.cssSelector(properties.getProperty("MyProfileLinkTest")));
			//Actions builder = new Actions(driver);
			for (WebElement lnk:myPrfle) {
				if (lnk.getText().equals("My Profile")) {
					//builder.moveToElement(lnk).perform();
					lnk.click();
					Thread.sleep(3000);
					log.logLine(Testname, false, "Navigating to  my profile link of menubar is succesful..");						
					break;
				}				
			}
		}else if (doesElementExist2(properties.getProperty("MyProfileLinkStage"), 5)) { 
			WebElement myPrfle = driver.findElement(By.cssSelector(properties.getProperty("MyProfileLinkStage")));
			builder.moveToElement(myPrfle).perform();
			Thread.sleep(3000);
			log.logLine(Testname, false, "Navigating to  my profile link of menubar is succesful..");
		}else{
			log.logLine(Testname, false, "Navigating to  my profile link of menubar is succesful.... is failed");
			throw new Exception("Navigating to  my profile link of menubar is succesful.... is failed");
		}


		Thread.sleep(5000);

		if (doesElementExist2(properties.getProperty("EditBtn"), 5)) {    
			WebElement edtbtn = driver.findElement(By.cssSelector(properties.getProperty("EditBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", edtbtn);
			Thread.sleep(6000);
			log.logLine(Testname, false, "Clicked on the edit button of the my profile pop up window");
		} else {
			log.logLine(Testname, true, "Clicked on the edit button of the my profile pop up window is failed");
			throw new Exception("Clicked on the edit button of the my profile pop up window is failed");
		}


		Thread.sleep(2000);
		String userName = test.readColumnData("UserName", sheetname);
		if (doesElementExist2(properties.getProperty("UserNameTextBox"), 5)) {    
			WebElement UsrText = driver.findElement(By.cssSelector(properties.getProperty("UserNameTextBox")));
			UsrText.clear();
			UsrText.sendKeys(userName);				
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entering user name in my profile pop up window");
		} else {
			log.logLine(Testname, true, "Entering user name in my profile pop up window is failed");
			throw new Exception("Entering user name in my profile pop up window is failed");
		}


		String emladr = test.readColumnData("EmailAddress", sheetname);
		if (doesElementExist2(properties.getProperty("EmailTextBox"), 5)) {    
			WebElement EmlText = driver.findElement(By.cssSelector(properties.getProperty("EmailTextBox")));
			EmlText.clear();
			EmlText.sendKeys(emladr);

			Thread.sleep(2000);
			log.logLine(Testname, false, "Entering email address in my profile pop up window");
		} else {
			log.logLine(Testname, true, "Entering email address in my profile pop up window is failed");
			throw new Exception("Entering email address in my profile pop up window is failed");
		}


		/*String oldpass = test.readColumnData("OldPassword", sheetname);
		if (doesElementExist2(properties.getProperty("OldPassWordTextBox"), 5)) {    
			WebElement oldpwd = driver.findElement(By.cssSelector(properties.getProperty("OldPassWordTextBox")));
			oldpwd.sendKeys(oldpass);
			Thread.sleep(2000);
		    log.logLine(Testname, false, "Entering old password in my profile pop up window");
	    } else {
		    log.logLine(Testname, true, "Entering old password in my profile pop up window is failed");
		    throw new Exception("Entering old password in my profile pop up window is failed");
	    }


		String newpass = test.readColumnData("NewPassword", sheetname);
		if (doesElementExist2(properties.getProperty("NewPasswordtextBox"), 5)) {    
			WebElement newpwd = driver.findElement(By.cssSelector(properties.getProperty("NewPasswordtextBox")));
			newpwd.sendKeys(newpass);
			Thread.sleep(2000);
		    log.logLine(Testname, false, "Entering new password in my profile pop up window");
	    } else {
		    log.logLine(Testname, true, "Entering new password in my profile pop up window is failed");
		    throw new Exception("Entering new password in my profile pop up window is failed");
	    }

		String confrmpass = test.readColumnData("ConfirmPassword", sheetname);
		if (doesElementExist2(properties.getProperty("ConfirmPasswordTextbox"), 5)) {    
			WebElement cnfrmpwd = driver.findElement(By.cssSelector(properties.getProperty("ConfirmPasswordTextbox")));
			cnfrmpwd.sendKeys(confrmpass);
			Thread.sleep(2000);
		    log.logLine(Testname, false, "Entering the samme password in my profile pop up window to confirm password");
	    } else {
		    log.logLine(Testname, true, "Entering the samme password in my profile pop up window to confirm password is failed");
		    throw new Exception("Entering the samme password in my profile pop up window to confirm password is failed");
	    }*/

		if (doesElementExist2(properties.getProperty("SaveBtn"), 5)) {    
			WebElement savebtn = driver.findElement(By.cssSelector(properties.getProperty("SaveBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", savebtn);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicked on the save button in my profile pop up window");
		} else {
			log.logLine(Testname, true, "Clicked on the save button in my profile pop up window is failed");
			throw new Exception("Clicked on the save button in my profile pop up window is failed");
		}			



		if (doesElementExist2(properties.getProperty("EditOkBtn1"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("EditOkBtn1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicking the Ok button of the Edit form once the modification is completed ");
		} else {
			log.logLine(Testname, true, "Clicking the Ok button of the Edit form failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking the Ok button of the Edit form failed");
		}

		driver.navigate().refresh();

		if (doesElementExist2(properties.getProperty("cancelbtn"), 5)) {    
			WebElement canlbtn = driver.findElement(By.cssSelector(properties.getProperty("cancelbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", canlbtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the cancel button of the clent/app selection pop up window");
		} else {
			log.logLine(Testname, true, "Clicking on the cancel button  of the clent/app selection pop up window is failed");
			throw new Exception("Clicking on the cancel button of the clent/app selection pop up window is failed");
		}

		if (doesElementExist2(properties.getProperty("VerifyUserName"), 5)) {
			WebElement rrdplntText = driver.findElement(By.cssSelector(properties.getProperty("VerifyUserName")));

			if (rrdplntText.getText().equals(userName)){
				log.logLine(Testname, false, "Modification of the username is successful");

			}else{
				log.logLine(Testname, true, "Modification of the username is unsuccessful");
			}
		}

		return true;
	}


	public boolean SingleUploadproof(String AccNo, String Testname, String Single) throws Exception  {

		String ReptName = null;		
		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(1000);
		WebElement retelm2 = waitForElement(properties.getProperty("selClint1"));

		String[] Sort = new String[50];
		int length = Sort.length;


		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("Proofs"), 5)) {	    
			WebElement proofsmnu = driver.findElement(By.cssSelector(properties.getProperty("Proofs")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", proofsmnu);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicking on Proofs menu..");
		} else {
			log.logLine(Testname, true, "Clicking on Proofs menu is failed");
			throw new Exception("Clicking on Proofs menu is failed");
		}

		log.logLine(Testname, false, "Navigation to Proofs Page is successful");	    
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
		}
		PleasewaitDisappear();


		if (doesElementExist2(properties.getProperty("Okbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Okbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on OK button to view the proof");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the proof is failed");
			throw new Exception("Clicking on OK button to view the proof is failed");
		}


				Thread.sleep(1000);
		driver.switchTo().frame("iframeContainer");
		PleasewaitDisappear();

		progressstatusinfoicon(Testname);
		progressstatusimagevalidation(Testname,"active pending","Ready for Review",1);
		progressstatusimagevalidation(Testname,"pending","Pending Predecessor",2);
		progressstatusimagevalidation(Testname,"approved","Approved",3);
		progressstatusimagevalidation(Testname,"rejected","Rejected",4);


		Thread.sleep(2000);

		if (doesElementExist2(properties.getProperty("ProofUploadbtn"), 5)) {	    
			WebElement uplbtn = driver.findElement(By.cssSelector(properties.getProperty("ProofUploadbtn")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", uplbtn);
			//uplbtn.click();
			Thread.sleep(500);
			if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) 
					|| (Initialization.Browser.equals("InternetExplorer")) || (Initialization.Browser.equals("internetexplorer"))) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", uplbtn);
			}
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Upload button in the Proofs");
		} else {
			log.logLine(Testname, true, "Clicking on Upload button in the Proofs is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Upload button in the Proofs is failed");
		}    


		String decp = test.readColumnData("Prf_Description", sheetname);	    
		if (doesElementExist2(properties.getProperty("pfDescription"), 5)) {	    
			WebElement decriptn = driver.findElement(By.cssSelector(properties.getProperty("pfDescription")));
			decriptn.sendKeys(decp);			
			log.logLine(Testname, false, "Entering the Description into upload proof popup");
		} else {
			log.logLine(Testname, true, "Entering the Description into upload proof popup is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the Description into upload proof popup is failed");
		}

		String RefCdfld = test.readColumnData("Prf_RefCode", sheetname);	    
		if (doesElementExist2(properties.getProperty("RefFld"), 5)) {	    
			WebElement reffld = driver.findElement(By.cssSelector(properties.getProperty("RefFld")));
			reffld.sendKeys(RefCdfld);			
			log.logLine(Testname, false, "Entering the Ref code into upload proof popup");
		} else {
			log.logLine(Testname, true, "Entering the Ref code into upload proof popup is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the Ref code into upload proof popup is failed");
		}

		String upldfilepath = test.readColumnData("Prf_FilePath", sheetname);        
		driver.findElement(By.xpath("//div[@class='test']/div/div/input[@type='file']")).sendKeys(upldfilepath);
		PleasewaitDisappear();


		Thread.sleep(1000);
		log.logLine(Testname, false, "Upload single PDF file from the path "+upldfilepath);


		if (doesElementExist2(properties.getProperty("reportUploadbtn"), 5)) {
			WebElement uplbtn = driver.findElement(By.cssSelector(properties.getProperty("reportUploadbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", uplbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Upload Proof File....");
		} else {
			log.logLine(Testname, true, "Upload Proof File is failed");
			throw new Exception("Upload Proof File is failed");
		}	    

		Thread.sleep(5000);	   
		if (doesElementExist2(properties.getProperty("ReportTable"), 5)) {
			log.logLine(Testname, false, "Proof PDF file uploading is successful");		    		    	
		} else {
			log.logLine(Testname, true, "Proof PDF file uploading is unsuccessful");
			throw new Exception("Proof PDF file uploading is unsuccessful");
		}

		if (doesElementExist2(properties.getProperty("Verifydecp"), 5)) {

			pdfname=driver.findElement(By.cssSelector(properties.getProperty("Verifydecp"))).getText();
			log.logLine(Testname, false,"Get text of the report name is sucessful");

		}else{
			log.logLine(Testname, true,"Get text of the report name is unsucessful");
			throw new Exception("Get text of the report name is unsucessful");
		}

		Thread.sleep(2000);

		Descriptionvalidation(Testname,decp);
		referencevalidation(Testname,RefCdfld);


		if (doesElementExist2(properties.getProperty("ApproveFirst"), 5)) {
			WebElement appr=driver.findElement(By.cssSelector(properties.getProperty("ApproveFirst")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", appr);
			PleasewaitDisappear();
			Thread.sleep(2000);
			log.logLine(Testname, false, "Approve of  PDF file uploading is successful");		    		    	
		} else {
			log.logLine(Testname, true, "Approve of  PDF file uploading is unsuccessful");
			throw new Exception("Approve of  PDF file uploading is unsuccessful");
		}


		if (!(doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5))) {	   
			log.logLine(Testname, true, "Clicking on Advanced Search button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Advanced Search button is failed");

		} else {
			List<WebElement> clntdd = driver.findElements(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			for (WebElement btn:clntdd) {
				if (btn.getText().equals("Advanced Search")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Clicking on Advanced Search button ");
					break;
				}
			}
		}

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("prftypedd"), 5)) {	

			WebElement quksrch = driver.findElement(By.cssSelector(properties.getProperty("prftypedd")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", quksrch);
			log.logLine(Testname, false, "Clicking on the proof status dropdown list in advanced search dialog");

			if (doesElementExist2(properties.getProperty("prfstatusAdsrch"), 5)) {
				List<WebElement> selloca = driver.findElements(By.cssSelector(properties.getProperty("prfstatusAdsrch")));	
				for (WebElement lnk:selloca) {
					if (lnk.getText().equals("Approved")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(4000);
						PleasewaitDisappear();
						log.logLine(Testname, false, "Selecting the Approved option from the proof status dropdown list in advanced search dialog");
						break;
					}
				}
			} else {
				log.logLine(Testname, true, "Selecting proof status dropdown list in advanced search dialog is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Selecting proof status dropdown list in advanced search dialog is failed");
			}

		} else {
			log.logLine(Testname, true, "Clicking on the proof status dropdown list in advanced search dialog is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on the proof status dropdown list in advanced search dialog is failed");
		}

		if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	    
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			Thread.sleep(2000);
			PleasewaitDisappear();
			//((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)", "");
			log.logLine(Testname, false, "Clicking on Search button to view the proofs ");
		} else {
			log.logLine(Testname, true, "Clicking on Search button to view the proofs is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Search button to view the proofs is failed");
		}

		String row = "tr";
		List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='proofing-grid']/div[2]/table/tbody/tr"));

		if(doesElementExist2(properties.getProperty("Description"), 5)){
			//Actions action = new Actions(driver);
			List<WebElement> editbtn = driver.findElements(By.cssSelector(properties.getProperty("Description")));
			for (WebElement lnk:editbtn) {
				if (lnk.getText().equals("Description")){
					log.logLine(Testname, false, "Description option exists..");
					break;
				} else {
					log.logLine(Testname, true, "Description option not exists..");
					driver.switchTo().defaultContent();
					throw new Exception("Description option not exists..");
				}
			}
		}

		if(doesElementExist2(properties.getProperty("Description"), 5)){
			for(int i = 0; i < DataCnt.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[class='k-grid-content k-auto-scrollable'] table tbody "+row+" td+td div[class='wordwrap']")).getText();
				row = row + "+tr";

			}
			log.logLine(Testname, false, "Iterating through the Rows..");
		}

		Thread.sleep(2000);
		for (int i = 0; i < length ; i++) {
			if (Sort[i].equals(pdfname)) {
				log.logLine(Testname, false, "matches");
				break;
			}else {
				log.logLine(Testname, false, "not matches");
			}
		}

		driver.switchTo().defaultContent();
		 
		return true;
	}	


	public boolean MultipleUploadproof(String AccNo, String Testname, String MultiFilesupld) throws Exception  {

		String ReptName = null;		
		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		String[] Sort = new String[50];
		int length = Sort.length;

		Thread.sleep(1000);
		driver.switchTo().frame("iframeContainer");
		PleasewaitDisappear();

		if (!(doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5))) {	   
			log.logLine(Testname, true, "Clicking on Advanced Search button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Advanced Search button is failed");

		} else {
			List<WebElement> clntdd = driver.findElements(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			for (WebElement btn:clntdd) {
				if (btn.getText().equals("Advanced Search")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Clicking on Advanced Search button ");
					break;
				}
			}
		}

		if (doesElementExist2(properties.getProperty("prftypedd"), 5)) {	

			WebElement quksrch = driver.findElement(By.cssSelector(properties.getProperty("prftypedd")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", quksrch);
			log.logLine(Testname, false, "Clicking on the proof status dropdown list in advanced search dialog");

			if (doesElementExist2(properties.getProperty("prfstatusAdsrch"), 5)) {
				List<WebElement> selloca = driver.findElements(By.cssSelector(properties.getProperty("prfstatusAdsrch")));	
				for (WebElement lnk:selloca) {
					if (lnk.getText().equals("Pending")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						PleasewaitDisappear();
						log.logLine(Testname, false, "Selecting the Pending option from the proof status dropdown list in advanced search dialog");
						break;
					}
				}
			} else {
				log.logLine(Testname, true, "Selecting proof status dropdown list in advanced search dialog is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Selecting proof status dropdown list in advanced search dialog is failed");
			}

		} else {
			log.logLine(Testname, true, "Clicking on the proof status dropdown list in advanced search dialog is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on the proof status dropdown list in advanced search dialog is failed");
		}

		if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	    
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			Thread.sleep(2000);
			PleasewaitDisappear();
			//((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)", "");
			log.logLine(Testname, false, "Clicking on Search button to view the proofs ");
		} else {
			log.logLine(Testname, true, "Clicking on Search button to view the proofs is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Search button to view the proofs is failed");
		}

		if (doesElementExist2(properties.getProperty("ProofUploadbtn"), 5)) {	    
			WebElement uplbtn = driver.findElement(By.cssSelector(properties.getProperty("ProofUploadbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", uplbtn);
			Thread.sleep(500);
			if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) 
					|| (Initialization.Browser.equals("InternetExplorer")) || (Initialization.Browser.equals("internetexplorer"))) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", uplbtn);
			}
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Upload button in the Proofs");
		} else {
			log.logLine(Testname, true, "Clicking on Upload button in the Proofs is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Upload button in the Proofs is failed");
		} 

		String JobCdfld = test.readColumnData("Description", sheetname);	    
		if (doesElementExist2(properties.getProperty("pfDescription"), 5)) {	    
			WebElement decriptn = driver.findElement(By.cssSelector(properties.getProperty("pfDescription")));
			decriptn.sendKeys(JobCdfld);			
			log.logLine(Testname, false, "Entering the Description into upload proof popup");
		} else {
			log.logLine(Testname, true, "Entering the Description into upload proof popup is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the Description into upload proof popup is failed");
		}

		String RefCdfld = test.readColumnData("Refcode", sheetname);	    
		if (doesElementExist2(properties.getProperty("RefFld"), 5)) {	    
			WebElement reffld = driver.findElement(By.cssSelector(properties.getProperty("RefFld")));
			reffld.sendKeys(RefCdfld);			
			log.logLine(Testname, false, "Entering the Ref code into upload proof popup");
		} else {
			log.logLine(Testname, true, "Entering the Ref code into upload proof popup is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the Ref code into upload proof popup is failed");
		}

		String upldfilepath = test.readColumnData("FilePath", sheetname);        
		driver.findElement(By.xpath("//div[@class='test']/div/div/input[@type='file']")).sendKeys(upldfilepath);
		PleasewaitDisappear();


		Thread.sleep(1000);
		log.logLine(Testname, false, "Upload single PDF file from the path "+upldfilepath);


		if (MultiFilesupld.equals("Multiple")) {
			if (doesElementExist2(properties.getProperty("mulfileadd"), 5)) {
				WebElement muluplbtn = driver.findElement(By.cssSelector(properties.getProperty("mulfileadd")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", muluplbtn);
				Thread.sleep(1000);
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", muluplbtn);
				Thread.sleep(1000);
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", muluplbtn);
				Thread.sleep(1000);
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", muluplbtn);
				Thread.sleep(1000);
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", muluplbtn);
				log.logLine(Testname, false, "Clicked on Addfile(+) proof row..");
			} else {
				log.logLine(Testname, true, "Clicked on Addfile(+) proof row is failed");
				throw new Exception("Clicked on Addfile(+) proof row is failed");
			}
			multipleuploads(Testname, AccNo);
		}


		if (doesElementExist2(properties.getProperty("ReportTable"), 5)) {
			log.logLine(Testname, false, "Proof PDF file uploading is successful");		    		    	
		} else {
			log.logLine(Testname, true, "Proof PDF file uploading is unsuccessful");
			throw new Exception("Proof PDF file uploading is unsuccessful");
		}


		if (doesElementExist2(properties.getProperty("reportUploadbtn"), 5)) {
			WebElement uplbtn = driver.findElement(By.cssSelector(properties.getProperty("reportUploadbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", uplbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Upload Proof File....");
		} else {
			log.logLine(Testname, true, "Upload Proof File is failed");
			throw new Exception("Upload Proof File is failed");
		}	    

		Thread.sleep(2000);

		if (MultiFilesupld.equals("Multiple")) {
			for (int z=0; z<3; z++) {    	
				if (doesElementExist2(properties.getProperty("VerifyReportNam"), 5)) {	   
					List<WebElement> clntdd = driver.findElements(By.cssSelector(properties.getProperty("VerifyReportNam")));
					for (WebElement btn:clntdd) {
						if (btn.getText().equals(ReptName+AccNo+z)) {
							log.logLine(Testname, false, "Uploading "+(z+1) +" proof file in Proofs is successful");
							break;
						}				
					}
					Thread.sleep(500);			
				} else {
					log.logLine(Testname, true, "Uploading report file in Proofs is unsuccessful");
					throw new Exception("Uploading report file in Proofs is unsuccessful");
				}
			}
		}



		/* 
	    String row = "tr";
			List<WebElement> DataCnt= driver.findElements(By.xpath("//div[@id='proofing-grid']/table/tbody/tr"));

			if(doesElementExist2(properties.getProperty("Description"), 5)){
				//Actions action = new Actions(driver);
				List<WebElement> editbtn = driver.findElements(By.cssSelector(properties.getProperty("Description")));
				for (WebElement lnk:editbtn) {
					if (lnk.getText().equals("Description")){
						//action.click(lnk).perform();
						log.logLine(Testname, false, "Loaded option exists..");
						break;
						} else {
							log.logLine(Testname, true, "Loaded option not exists..");
							driver.switchTo().defaultContent();
							throw new Exception("Loaded option not exists..");
							}
					}
				}

			if(doesElementExist2(properties.getProperty("Description"), 5)){
				for(int i = 0; i < DataCnt.size(); i++) {
					Sort[i] = driver.findElement(By.cssSelector("div[id='proofing-grid'] table tbody "+row+" td+td div[class='wordwrap']")).getText();
					row = row + "+tr";

					}
				log.logLine(Testname, false, "Iterating through the Rows..");
			}

			for (int i = 0; i < length ; i++) {
				if (Sort[i].equals(pdfname)) {
					log.logLine(Testname, false, "matches");
					break;
				}else {
					log.logLine(Testname, false, "not matches");
				}
		}*/


		driver.switchTo().defaultContent();

		return true;

	}


	public void multipleuploads(String Testname, String AccNo) throws Exception {		

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();		

		for (int i=1; i<6; i++) {

			String RefCdfld = test.readColumnData("Refcode"+Integer.toString(i), sheetname);	    
			if (doesElementExist2("input[name='["+i+"]_orderNumber']", 5)) {	    
				WebElement reffld = driver.findElement(By.cssSelector("input[name='["+i+"]_orderNumber']"));
				reffld.sendKeys(RefCdfld);			
				log.logLine(Testname, false, "Entering the Ref code into upload new proof popup");
			}
			else if (doesElementExist2("input[id='["+i+"].OrderNumber']", 5)) {	    
				WebElement reffld = driver.findElement(By.cssSelector("input[id='["+i+"].OrderNumber']"));
				reffld.sendKeys(RefCdfld);			
				log.logLine(Testname, false, "Entering the Ref code into upload report popup");

			} else {
				log.logLine(Testname, true, "Entering the Ref code into upload new proof popup is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Entering the Ref code into upload new proof popup is failed");
			}

			String JobCdfld = test.readColumnData("Description"+Integer.toString(i), sheetname);	    
			if (doesElementExist2("input[name='["+i+"]_description']", 5)) {	    
				WebElement reffld = driver.findElement(By.cssSelector("input[name='["+i+"]_description']"));
				reffld.sendKeys(JobCdfld+AccNo+(i+1));			
				log.logLine(Testname, false, "Entering the Description into upload new proof popup");
			}

			else if (doesElementExist2("input[id='["+i+"].Description']", 5)) {	    
				WebElement reffld = driver.findElement(By.cssSelector("input[id='["+i+"].Description']"));
				reffld.sendKeys(RefCdfld);			
				log.logLine(Testname, false, "Entering the Ref code into upload report popup");

			} else {
				log.logLine(Testname, true, "Entering the Description into upload new proof popup is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Entering the Description into upload new proof popup is failed");
			}


			String upldfilepath = test.readColumnData("FilePath"+Integer.toString(i), sheetname);

			if (doesElementExist2("input[name='["+i+"]_uploadFile']", 5)) {	    
				WebElement upload = driver.findElement(By.cssSelector("input[name='["+i+"]_uploadFile']"));
				upload.sendKeys(upldfilepath);			
				log.logLine(Testname, false, "Entering the Description into upload new proof popup");
			}

			else if (doesElementExist2("input[name='["+i+"].UploadFile']", 5)) {	    
				WebElement upload = driver.findElement(By.cssSelector("input[name='["+i+"].UploadFile']"));
				upload.sendKeys(upldfilepath);			
				log.logLine(Testname, false, "Uploading Multiple files in Upload New proof popup");

			} else {
				log.logLine(Testname, true, "Uploading Multiple files in Upload New proof popup is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Uploading Multiple files in Upload New proof popup is failed");
			} 


			/*
		    String upldfilepath = test.readColumnData("FilePath"+Integer.toString(i), sheetname);        
		    driver.findElement(By.cssSelector("input[name='["+i+"]_uploadFile']")).sendKeys(upldfilepath);
		    Thread.sleep(1000);	    
		    log.logLine(Testname, false, "Uploading Multiple files in Upload New proof popup");
			 */  
		}

	}

	public boolean proofEnhancements(String RandNo, String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		//upload few files for bulk actions
		ProofTesting proof = new ProofTesting(driver, log);
		proof.MultipleUploadproof(RandNo, Testname, "Multiple");

		Thread.sleep(6000);
		driver.switchTo().frame("iframeContainer");

		//Perform bulk delete in proofs page
		String ProofName1 = null, ProofName2 = null;
		if (doesElementExist2(properties.getProperty("Descipfld1"), 5)) {
			ProofName1 = driver.findElement(By.cssSelector(properties.getProperty("Descipfld1"))).getText();

			if (doesElementExist2(properties.getProperty("delFirstprf"), 5)) {
				WebElement delfir = driver.findElement(By.cssSelector(properties.getProperty("delFirstprf")));		    
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", delfir);
				PleasewaitDisappear();

				log.logLine(Testname, false, "Selecting first record in proofs grid table");
			} else {
				log.logLine(Testname, true, "Selecting first record in proofs grid table is failed");			
			}

			ProofName2 = driver.findElement(By.cssSelector(properties.getProperty("Descipfld2"))).getText();
			if (doesElementExist2(properties.getProperty("delSecondprf"), 5)) {
				WebElement delsec = driver.findElement(By.cssSelector(properties.getProperty("delSecondprf")));		    
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", delsec);
				Thread.sleep(2000);
				log.logLine(Testname, false, "Selecting second record in proofs grid table");
			} else {
				log.logLine(Testname, true, "Selecting second record in proofs grid table is failed");			
			}


			Actions action = new Actions(driver);
			if (doesElementExist2(properties.getProperty("BulkActions"), 5)) {
				WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("BulkActions")));
				log.logLine(Testname, false, "Clicking on Bulk Choose Action to Delete the proof..");
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);			
				Thread.sleep(1000);

				List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SelBulkacts")));	
				for (WebElement lnk:vwrpts) {
					if (lnk.getText().equals("Delete")) {
						action.click(lnk).perform();
						Thread.sleep(2000);	
						PleasewaitDisappear();

						log.logLine(Testname, false, "Clicking on Delete button under bulk actions");

						if (doesElementExist2(properties.getProperty("DelProofConf"), 5)) {
							WebElement delpfconf = driver.findElement(By.cssSelector(properties.getProperty("DelProofConf")));
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", delpfconf);
							PleasewaitDisappear();
							log.logLine(Testname, false, "Clicking on OK button on bulk proof delete popup");						    
						} else {
							log.logLine(Testname, true, "Clicking on OK button on bulk proof delete popup is failed");
							throw new Exception("Clicking on OK button on bulk proof delete popup is failed");
						}
						break;
					}
				}			

			} else {
				log.logLine(Testname, true, "Clicking on Bulk ChooseActions is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on bulk ChooseActions is failed");
			}		    

			Thread.sleep(2000);
			if (doesElementExist2(properties.getProperty("Descipfld1"), 5)) {
				WebElement firprf = driver.findElement(By.cssSelector(properties.getProperty("Descipfld1")));
				if (!firprf.getText().equals(ProofName1)) {

					if (doesElementExist2(properties.getProperty("Descipfld2"), 5)) {
						WebElement secprf = driver.findElement(By.cssSelector(properties.getProperty("Descipfld2")));
						if (secprf.getText().equals(ProofName2)) {		    
							log.logLine(Testname, true, "Bulk delete - Deleting multiple proofs is unsuccessful");
							throw new Exception("Bulk delete - Deleting multiple proofs is unsuccessful");
						} else {
							log.logLine(Testname, false, "Bulk delete - Deleting multiple proofs is successful");
						}
					} else {
						log.logLine(Testname, true, "Bulk delete - Deleting multiple proofs is unsuccessful");
						throw new Exception("Bulk delete - Deleting multiple proofs is unsuccessful");
					}
				} 
			} else {
				log.logLine(Testname, true, "Unable find First Proof record");
			}			
		}



		driver.switchTo().defaultContent();    

		//upload few files for bulk actions	    
		//proof.MultipleUploadproof(RandNo, Testname, "Multiple");

		Thread.sleep(1000);
		driver.switchTo().frame("iframeContainer");

		//Perform bulk delete in proofs page	    
		if (doesElementExist2(properties.getProperty("Descipfld1"), 5)) {
			ProofName1 = driver.findElement(By.cssSelector(properties.getProperty("Descipfld1"))).getText();

			if (doesElementExist2(properties.getProperty("delFirstprf"), 5)) {
				WebElement delfir = driver.findElement(By.cssSelector(properties.getProperty("delFirstprf")));		    
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", delfir);
				log.logLine(Testname, false, "Selecting first record in proofs grid table");
			} else {
				log.logLine(Testname, true, "Selecting first record in proofs grid table is failed");			
			}

			ProofName2 = driver.findElement(By.cssSelector(properties.getProperty("Descipfld2"))).getText();
			if (doesElementExist2(properties.getProperty("delSecondprf"), 5)) {
				WebElement delsec = driver.findElement(By.cssSelector(properties.getProperty("delSecondprf")));		    
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", delsec);
				Thread.sleep(2000);

				log.logLine(Testname, false, "Selecting second record in proofs grid table");
			} else {
				log.logLine(Testname, true, "Selecting second record in proofs grid table is failed");			
			}


			Actions action = new Actions(driver);
			if (doesElementExist2(properties.getProperty("BulkActions"), 5)) {
				WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("BulkActions")));
				log.logLine(Testname, false, "Clicking on Bulk Choose Action to Approve the proof..");

				((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);			
				Thread.sleep(1000);

				List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SelBulkacts")));	
				for (WebElement lnk:vwrpts) {
					if (lnk.getText().equals("Approve")) {
						action.click(lnk).perform();
						Thread.sleep(2000);			
						PleasewaitDisappear();

						log.logLine(Testname, false, "Clicking on Approve button under bulk actions");

						if (doesElementExist2(properties.getProperty("DelProofConf"), 5)) {
							WebElement delpfconf = driver.findElement(By.cssSelector(properties.getProperty("DelProofConf")));
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", delpfconf);
							PleasewaitDisappear();
							log.logLine(Testname, false, "Clicking on OK button on bulk proof Approve popup");						    
						} else {
							log.logLine(Testname, true, "Clicking on OK button on bulk proof Approve popup is failed");
							throw new Exception("Clicking on OK button on bulk proof Approve popup is failed");
						}
						break;
					}
				}			

			} else {
				log.logLine(Testname, true, "Clicking on Bulk ChooseActions is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on bulk ChooseActions is failed");
			}
		}
		Thread.sleep(6000);
		driver.switchTo().defaultContent();
		AdvancedSearch(Testname, "ProofStatus", "Approved", "");	
		driver.switchTo().frame("iframeContainer");

		if ((driver.getPageSource().contains(ProofName1)) && (driver.getPageSource().contains(ProofName2))) {
			log.logLine(Testname, false, "Verification of bulk proof approve is successful");
		} else {
			log.logLine(Testname, true, "Verification of bulk proof approve is unsuccessful");		
		}


		driver.switchTo().defaultContent();    
		proof.MultipleUploadproof(RandNo, Testname, "Multiple");

		Thread.sleep(6000);
		
		driver.switchTo().frame("iframeContainer");

		//Perform bulk delete in proofs page	    
		if (doesElementExist2(properties.getProperty("Descipfld1"), 5)) {
			ProofName1 = driver.findElement(By.cssSelector(properties.getProperty("Descipfld1"))).getText();

			if (doesElementExist2(properties.getProperty("delFirstprf"), 5)) {
				WebElement delfir = driver.findElement(By.cssSelector(properties.getProperty("delFirstprf")));		    
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", delfir);
				PleasewaitDisappear();

				log.logLine(Testname, false, "Selecting first record in proofs grid table");
			} else {
				log.logLine(Testname, true, "Selecting first record in proofs grid table is failed");			
			}

			ProofName2 = driver.findElement(By.cssSelector(properties.getProperty("Descipfld2"))).getText();
			if (doesElementExist2(properties.getProperty("delSecondprf"), 5)) {
				WebElement delsec = driver.findElement(By.cssSelector(properties.getProperty("delSecondprf")));		    
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", delsec);
				Thread.sleep(2000);

				log.logLine(Testname, false, "Selecting second record in proofs grid table");
			} else {
				log.logLine(Testname, true, "Selecting second record in proofs grid table is failed");			
			}


			Actions action = new Actions(driver);
			if (doesElementExist2(properties.getProperty("BulkActions"), 5)) {
				WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("BulkActions")));
				log.logLine(Testname, false, "Clicking on Bulk Choose Action to Reject the proof..");
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);			
				Thread.sleep(1000);

				List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SelBulkacts")));	
				for (WebElement lnk:vwrpts) {
					if (lnk.getText().equals("Reject with Comments")) {
						action.click(lnk).perform();
						Thread.sleep(2000);		
						PleasewaitDisappear();

						log.logLine(Testname, false, "Clicking on Reject with comments button under bulk actions");

						if (doesElementExist2(properties.getProperty("BulkReject"), 5)) {
							WebElement rejectcmmnts = driver.findElement(By.cssSelector(properties.getProperty("BulkReject")));
							rejectcmmnts.sendKeys("Bulk Reject Comments -"+RandNo);
							log.logLine(Testname, false, "Entering text for rejecting bulk proofs on popup");						    
						} else {
							log.logLine(Testname, true, "Entering text for rejecting bulk proofs on popup is failed");
							throw new Exception("Entering text for rejecting bulk proofs on popup is failed");
						}
						Thread.sleep(2000);

						if (doesElementExist2(properties.getProperty("BulkRejectConf"), 5)) {
							WebElement rejectcmmntsBtn = driver.findElement(By.cssSelector(properties.getProperty("BulkRejectConf")));
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", rejectcmmntsBtn);
							PleasewaitDisappear();
							log.logLine(Testname, false, "Clicking OK button on Reject with comments popup");						    
						} else {
							log.logLine(Testname, true, "Clicking OK button on Reject with comments popup is failed");
							throw new Exception("Clicking OK button on Reject with comments popup is failed");
						}
						break;
					}
				}			

			} else {
				log.logLine(Testname, true, "Clicking on Bulk ChooseActions is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on bulk ChooseActions is failed");
			}
		}
		Thread.sleep(8000);

		driver.switchTo().defaultContent();
		AdvancedSearch(Testname, "ProofStatus", "Rejected", "");	    
		driver.switchTo().frame("iframeContainer");

		if ((driver.getPageSource().contains(ProofName1)) && (driver.getPageSource().contains(ProofName2))) {
			log.logLine(Testname, false, "Verification of bulk proof Reject with comments is successful");
		} else {
			log.logLine(Testname, true, "Verification of bulk proof Reject with comments is unsuccessful");		
		}

		driver.switchTo().defaultContent(); 
		return true;
	}

	public boolean approvestatus(String Testname) throws Exception {

		Thread.sleep(2000);	    
		Actions action = new Actions(driver);

		// Get the proofs based on the status from the dropdown
		AdvancedSearch(Testname, "ProofStatus", "Pending", "");

		AdvancedSearch(Testname, "RefenceCode", "esceo03", "");
		Thread.sleep(9000);

		driver.switchTo().frame("iframeContainer");
		//Get the name of proof to verify after the same is rejected
		String ProofName = null;
		if (doesElementExist2(properties.getProperty("Descipfld1"), 5)) {
			ProofName = driver.findElement(By.cssSelector(properties.getProperty("Descipfld1"))).getText();
			log.logLine(Testname, false, "Get the Name of Proofs which has to be approved");
		}

		if (doesElementExist2(properties.getProperty("approvebtn1"), 5)) {
			WebElement apprbtb = driver.findElement(By.cssSelector(properties.getProperty("approvebtn1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", apprbtb);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Approve button for the proofs - "+ProofName);

			Thread.sleep(25000);
			if (doesElementExist2(properties.getProperty("Descipfld1"), 5)) {
				String NewProofName = driver.findElement(By.cssSelector(properties.getProperty("Descipfld1"))).getText();
				if (ProofName.equals(NewProofName)) {
					log.logLine(Testname, true, "Apporving the proof - "+ProofName +" is unsuccessful");
					throw new Exception("Apporving the proof - "+ProofName +" is unsuccessful");
				} else {
					log.logLine(Testname, false, "Apporving the proof - "+ProofName +" is successful");
				}
			} else {
				log.logLine(Testname, false, "Apporving the proof - "+ProofName +" is successful");
			}	    

		} else {
			log.logLine(Testname, true, "Approve button in the proofs grid is not exist for the first record");
		}

		driver.switchTo().defaultContent();
		return true;		

	}


	public boolean rejectstatus(String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(2000);	     
		Actions action = new Actions(driver);

		// Get the proofs based on the status from the dropdown
		AdvancedSearch(Testname, "ProofStatus", "Pending", "");

		AdvancedSearch(Testname, "RefenceCode", "esceo04", "");

		driver.switchTo().frame("iframeContainer");
		//Get the name of proof to verify after the same is rejected

		String ProofName = null;
		if (doesElementExist2(properties.getProperty("Descipfld1"), 5)) {
			ProofName = driver.findElement(By.cssSelector(properties.getProperty("Descipfld1"))).getText();
			log.logLine(Testname, false, "Get the Name of Proofs which has to be rejected with Comments");
		}

		String Proofstatus = null;	    	    
		if (doesElementExist2(properties.getProperty("Proofstatus1"), 5)) {
			Proofstatus = driver.findElement(By.cssSelector(properties.getProperty("Proofstatus1"))).getText();	  
			log.logLine(Testname, false, "Get the Status of Proofs which has to be rejected with Comments");
		}

		//Click on Cancel button while rejecting proof with Comments

		if (doesElementExist(properties.getProperty("rejectbtn1"), 5)) {
			WebElement Rejectbtn = driver.findElement(By.xpath(properties.getProperty("rejectbtn1")));	
			log.logLine(Testname, false, "On hover over reject button to choose Comments option");	    	

			//((JavascriptExecutor)driver).executeScript("$('Rejectbtn').hover();");
			action.moveToElement(Rejectbtn).build().perform();
			if (doesElementExist(properties.getProperty("rejectWCommts"), 5)) {
				WebElement rejcommt = driver.findElement(By.xpath(properties.getProperty("rejectWCommts")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", rejcommt);
				PleasewaitDisappear();

				log.logLine(Testname, false, "Clicking on reject with comments..");

				if (doesElementExist2(properties.getProperty("CmmtsTxtfld"), 5)) {
					WebElement rejcommttxt = driver.findElement(By.cssSelector(properties.getProperty("CmmtsTxtfld")));
					rejcommttxt.sendKeys(ProofName+" is rejected with Comments");
					log.logLine(Testname, false, "Entered the text before clicking on reject with comments..");  		

					if (doesElementExist2(properties.getProperty("CmmtsCancelbtn"), 5)) {
						WebElement rejectCancelbtn = driver.findElement(By.cssSelector(properties.getProperty("CmmtsCancelbtn")));
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", rejectCancelbtn);
						PleasewaitDisappear();
						log.logLine(Testname, false, "Clicking on Cancel button after entering text");
					}

					//if (doesElementExist2(properties.getProperty("CmmtsCancelbtn"), 5)) {
					//log.logLine(Testname, true, "Click on Cancel button while rejecting proof with Comments is unsuccessful");
					//}else {
					log.logLine(Testname, false, "Click on Cancel button while rejecting proof with Comments is successful");
					//}

				} else {
					log.logLine(Testname, true, "Text field does not exist in popup after clicking on Comments");			
				}

			} else {
				log.logLine(Testname, true, "Selecting reject with comments is failed");			
			}
		} else {
			log.logLine(Testname, true, "Reject button in proofs page may not exist");			
		}

		//Click on reject button while rejecting proof with Comments

		if (doesElementExist(properties.getProperty("rejectbtn1"), 5)) {
			WebElement Rejectbtn = driver.findElement(By.xpath(properties.getProperty("rejectbtn1")));	
			log.logLine(Testname, false, "On hover over reject button to choose Comments option");	    	

			//((JavascriptExecutor)driver).executeScript("$('Rejectbtn').hover();");
			action.moveToElement(Rejectbtn).build().perform();
			if (doesElementExist(properties.getProperty("rejectWCommts"), 5)) {
				WebElement rejcommt = driver.findElement(By.xpath(properties.getProperty("rejectWCommts")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", rejcommt);
				PleasewaitDisappear();

				log.logLine(Testname, false, "Clicking on reject with comments..");

				if (doesElementExist2(properties.getProperty("CmmtsTxtfld"), 5)) {
					WebElement rejcommttxt = driver.findElement(By.cssSelector(properties.getProperty("CmmtsTxtfld")));
					rejcommttxt.sendKeys(ProofName+" is rejected with Comments");
					log.logLine(Testname, false, "Entered the text before clicking on reject with comments..");  		

					if (doesElementExist2(properties.getProperty("CmmtsRejectbtn"), 5)) {
						WebElement rejectbtn = driver.findElement(By.cssSelector(properties.getProperty("CmmtsRejectbtn")));
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", rejectbtn);
						Thread.sleep(1000);
						PleasewaitDisappear();
						Thread.sleep(1000);
						log.logLine(Testname, false, "Clicking on Reject button after entering text in the pop-up");
					}

					if (doesElementExist2(properties.getProperty("Descipfld1"), 5)) {
						String NewProofName = driver.findElement(By.cssSelector(properties.getProperty("Descipfld1"))).getText();
						if (NewProofName.equals(ProofName)) {
							log.logLine(Testname, true, "Reject the proofs with comments is unsuccessful");
							throw new Exception("Reject the proofs with comments is unsuccessful");
						}else {
							log.logLine(Testname, false, "Reject the proofs with comments is successful");		        			
						}
					}		    		

				} else {
					log.logLine(Testname, true, "Text field does not exist in popup after clicking on Comments");			
				}

			} else {
				log.logLine(Testname, true, "Selecting reject with comments is failed");			
			}
		} else {
			log.logLine(Testname, true, "Reject button in proofs page may not exist");			
		}

		/*
    driver.switchTo().defaultContent();
    AdvancedSearch(Testname, "ProofStatus", "Rejected", "");
    Thread.sleep(1000);
    driver.switchTo().frame("iframeContainer");

    if (doesElementExist2(properties.getProperty("Descipfld1"), 5)) {
		String NewProofName = driver.findElement(By.cssSelector(properties.getProperty("Descipfld1"))).getText();
		if (NewProofName.equals(ProofName)) {
			log.logLine(Testname, false, "Reject the proofs with comments is successful");	    			
		}else {
			log.logLine(Testname, true, "Reject the proofs with comments is unsuccessful");
			throw new Exception("Reject the proofs with comments is unsuccessful");
		}
	}
		 */

		driver.switchTo().defaultContent();
		// Get the proofs based on the status from the dropdown
		AdvancedSearch(Testname, "ProofStatus", "Pending", "");

		AdvancedSearch(Testname, "RefenceCode", "esceo04", "");

		driver.switchTo().frame("iframeContainer");

		String ProofRejUpl = null;
		//Get the name of proof to verify after the same is rejected	    
		if (doesElementExist2(properties.getProperty("Descipfld1"), 5)) {
			ProofRejUpl = driver.findElement(By.cssSelector(properties.getProperty("Descipfld1"))).getText();
			log.logLine(Testname, false, "Get the Name of Proofs which has to be rejected with Upload");
		}	    


		String Rejectupload = test.readColumnData("RejectUpload", sheetname);

		//Click on Cancel button while rejecting proof with Upload
		if (doesElementExist(properties.getProperty("rejectbtn1"), 5)) {
			WebElement Rejectbtn = driver.findElement(By.xpath(properties.getProperty("rejectbtn1")));	
			log.logLine(Testname, false, "On hover over reject button to choose Comments option");	    	

			//((JavascriptExecutor)driver).executeScript("$('Rejectbtn').hover();");
			action.moveToElement(Rejectbtn).build().perform();

			//action.moveToElement(Rejectbtn).perform();
			if (doesElementExist(properties.getProperty("rejectWUpload"), 5)) {
				WebElement rejcommt = driver.findElement(By.xpath(properties.getProperty("rejectWUpload")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", rejcommt);
				PleasewaitDisappear();

				log.logLine(Testname, false, "Clicking on reject with Upload..");

				if (doesElementExist2(properties.getProperty("rejectWuploadfile"), 5)) {
					WebElement rejcommttxt = driver.findElement(By.cssSelector(properties.getProperty("rejectWuploadfile")));
					rejcommttxt.sendKeys(Rejectupload);
					PleasewaitDisappear();
					log.logLine(Testname, false, "Select the file before uploading..");  		

					if (doesElementExist2(properties.getProperty("UploadCancelbtn"), 5)) {
						WebElement rejectCancelbtn = driver.findElement(By.cssSelector(properties.getProperty("UploadCancelbtn")));
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", rejectCancelbtn);
						PleasewaitDisappear();
						log.logLine(Testname, false, "Clicking on Cancel button after uploading the file");
					}

					log.logLine(Testname, false, "Click on Cancel button while rejecting proof with Upload is successful");		    		

				} else {
					log.logLine(Testname, true, "Text field does not exist in popup after clicking on Upload");			
				}

			} else {
				log.logLine(Testname, true, "Selecting reject with Upload is failed");			
			}
		} else {
			log.logLine(Testname, true, "Reject button in proofs page may not exist");			
		}


		//Click on Reject button while rejecting proof with Upload
		if (doesElementExist(properties.getProperty("rejectbtn1"), 5)) {
			WebElement Rejectbtn = driver.findElement(By.xpath(properties.getProperty("rejectbtn1")));	
			log.logLine(Testname, false, "On hover over reject button to choose Comments option");	    	
			action.moveToElement(Rejectbtn).build().perform();

			if (doesElementExist(properties.getProperty("rejectWUpload"), 5)) {
				WebElement rejcommt = driver.findElement(By.xpath(properties.getProperty("rejectWUpload")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", rejcommt);
				PleasewaitDisappear();

				log.logLine(Testname, false, "Clicking on reject with Upload..");

				if (doesElementExist2(properties.getProperty("rejectWuploadfile"), 5)) {
					WebElement rejcommttxt = driver.findElement(By.cssSelector(properties.getProperty("rejectWuploadfile")));
					rejcommttxt.sendKeys(Rejectupload);
					PleasewaitDisappear();
					log.logLine(Testname, false, "Select the file before uploading..");  		

					if (doesElementExist2(properties.getProperty("UploadRejectbtn"), 5)) {
						WebElement rejectCancelbtn = driver.findElement(By.cssSelector(properties.getProperty("UploadRejectbtn")));
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", rejectCancelbtn);
						Thread.sleep(1000);
						PleasewaitDisappear();
						log.logLine(Testname, false, "Clicking on Reject button after uploading the file");
					}

					if (doesElementExist2(properties.getProperty("Descipfld1"), 5)) {
						String NewProofName = driver.findElement(By.cssSelector(properties.getProperty("Descipfld1"))).getText();
						if (NewProofName.equals(ProofRejUpl)) {
							log.logLine(Testname, false, "Reject the proofs with upload is successful");
						}else {
							log.logLine(Testname, true, "Reject the proofs with upload is unsuccessful");	
							throw new Exception("Reject the proofs with upload is unsuccessful");
						}
					}


				} else {
					log.logLine(Testname, true, "Text field does not exist in popup after clicking on Upload");			
				}	    		
			} else {
				log.logLine(Testname, true, "Selecting reject with Upload is failed");			
			}
		} else {
			log.logLine(Testname, true, "Reject button in proofs page may not exist");			
		}    

		driver.switchTo().defaultContent();  
		return true;			

	}

	public boolean VerifyComments(String Testname) throws Exception {		

		driver.switchTo().defaultContent();
		AdvancedSearch(Testname, "ProofStatus", "Pending", "");		

		driver.switchTo().frame("iframeContainer");
		if (doesElementExist2(properties.getProperty("ChoseAct2"), 5)) {
			log.logLine(Testname, false, "Clicking on Choose Action to add comments..");

			WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("ChoseAct2")));
			log.logLine(Testname, false, "Clicking on AddComments under choose action");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
			Thread.sleep(5000);

			List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SelReportLnk")));	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals("Add Comments")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(2000);
					PleasewaitDisappear();

				}
			}
		} else {
			log.logLine(Testname, true, "Clicking on Choose actions failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Choose actions failed");
		}


		if (doesElementExist2(properties.getProperty("Comtstxtbox"), 5)) {	    
			WebElement btxt = driver.findElement(By.cssSelector(properties.getProperty("Comtstxtbox")));

			int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
			String AccNo = Integer.toString(paperID);

			btxt.sendKeys("AutoComments_"+AccNo);	
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entering the comments in textarea field with "+"AutoComments_"+AccNo);
		} else {
			log.logLine(Testname, true, "Entering the comments in textarea field is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the comments in textarea field is failed");
		}	


		if (doesElementExist2(properties.getProperty("ComtsClrbtn"), 5)) {	    
			WebElement clrbtn = driver.findElement(By.cssSelector(properties.getProperty("ComtsClrbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clrbtn);
			log.logLine(Testname, false, "Clicking on Clear button to clear the comments");
		} else {
			log.logLine(Testname, true, "Clicking on Clear button to clear the comments is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Clear button to clear the comments is failed");
		}

		if (doesElementExist2(properties.getProperty("ComtsCancelbtn"), 5)) {	    
			WebElement canclbtn = driver.findElement(By.cssSelector(properties.getProperty("ComtsCancelbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", canclbtn);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicking on Cancel button to close the comments textbox");		

		} else {
			log.logLine(Testname, true, "Clicking on Cancel button to close the comments textbox is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Cancel button to close the comments textbox is failed");
		}


		//Click on Cancel button
		if (doesElementExist2(properties.getProperty("ChoseAct2"), 5)) {
			log.logLine(Testname, false, "Clicking on Choose Action to add comments..");

			WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("ChoseAct2")));
			log.logLine(Testname, false, "Clicking on AddComments under choose action");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
			Thread.sleep(5000);

			List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SelReportLnk")));	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals("Add Comments")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(2000);
					PleasewaitDisappear();

				}
			}
		} else {
			log.logLine(Testname, true, "Clicking on Choose actions failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Choose actions failed");
		}


		if (doesElementExist2(properties.getProperty("Comtstxtbox"), 5)) {	    
			WebElement btxt = driver.findElement(By.cssSelector(properties.getProperty("Comtstxtbox")));

			int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
			String AccNo = Integer.toString(paperID);

			btxt.sendKeys("AutoComments_"+AccNo);	
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entering the comments in textarea field with "+"AutoComments_"+AccNo);
		} else {
			log.logLine(Testname, true, "Entering the comments in textarea field is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the comments in textarea field is failed");
		}	


		if (doesElementExist2(properties.getProperty("ComtsCancelbtn"), 5)) {	    
			WebElement canclbtn = driver.findElement(By.cssSelector(properties.getProperty("ComtsCancelbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", canclbtn);
			log.logLine(Testname, false, "Clicking on Cancel button to close the comments textbox");
			Thread.sleep(5000);

			if (doesElementExist2(properties.getProperty("Comtstxtbox"), 5)) 
				log.logLine(Testname, false, "Add Comments - Cancel is successful");
			else 
				log.logLine(Testname, true, "Add Comments - Cancel is unsuccessful");

		} else {
			log.logLine(Testname, true, "Clicking on Cancel button to close the comments textbox is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Cancel button to close the comments textbox is failed");
		}

		//Add Comments and Save 
		if (doesElementExist2(properties.getProperty("ChoseAct2"), 5)) {
			log.logLine(Testname, false, "Clicking on Choose Action to add comments..");

			WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("ChoseAct2")));
			log.logLine(Testname, false, "Clicking on AddComments under choose action");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
			Thread.sleep(5000);

			List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SelReportLnk")));	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals("Add Comments")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(2000);
					PleasewaitDisappear();
				}
			}
		} else {
			log.logLine(Testname, true, "Clicking on Choose actions failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Choose actions failed");
		}

		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		String AccNo = Integer.toString(paperID);		

		if (doesElementExist2(properties.getProperty("Comtstxtbox"), 5)) {	    
			WebElement btxt = driver.findElement(By.cssSelector(properties.getProperty("Comtstxtbox")));		
			btxt.sendKeys("AutoComments_"+AccNo);		
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entering the comments in textarea field with "+"AutoComments_"+AccNo);
		} else {
			log.logLine(Testname, true, "Entering the comments in textarea field is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the comments in textarea field is failed");
		}	


		if (doesElementExist2(properties.getProperty("ComtsSavebtn"), 5)) {	    
			WebElement canclbtn = driver.findElement(By.cssSelector(properties.getProperty("ComtsSavebtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", canclbtn);
			PleasewaitDisappear();
			Thread.sleep(2000);

			log.logLine(Testname, false, "Clicking on Save button to add comments for audit");

			if (doesElementExist2(properties.getProperty("Comtstxtbox"), 5)) 
				log.logLine(Testname, false, "Add Comments - Save is successful");
			else 
				log.logLine(Testname, true, "Add Comments - Save is unsuccessful");

		} else {
			log.logLine(Testname, true, "Clicking on Save button to add comments for audit is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Save button to add comments for audit is failed");
		}


		//View Comments
		if (doesElementExist2(properties.getProperty("ChoseAct2"), 5)) {
			log.logLine(Testname, false, "Clicking on Choose Action to add comments..");

			WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("ChoseAct2")));
			log.logLine(Testname, false, "Clicking on View Comments under choose action");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
			Thread.sleep(5000);

			List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SelReportLnk")));	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals("View Comments")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);			
					Thread.sleep(2000);
					PleasewaitDisappear();

				}
			}
		} else {
			log.logLine(Testname, true, "Clicking on Choose actions failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Choose actions failed");
		}


		if (doesElementExist2(properties.getProperty("ComtsView"), 5)) {	    
			String Comments = driver.findElement(By.cssSelector(properties.getProperty("ComtsView"))).getText();
			log.logLine(Testname, false, "Verify the added comments,..");

			if (Comments.contains("AutoComments_"+AccNo)) {
				log.logLine(Testname, false, "View Comments is successful");				
			} else {
				log.logLine(Testname, true, "View Comments is unsuccessful");
			}			

		} else {
			log.logLine(Testname, true, "Entering the comments in textarea field is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the comments in textarea field is failed");
		}	


		if (doesElementExist2(properties.getProperty("ComtsOKbtn"), 5)) {	    
			WebElement canclbtn = driver.findElement(By.cssSelector(properties.getProperty("ComtsOKbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", canclbtn);
			PleasewaitDisappear();

			log.logLine(Testname, false, "Clicking on OK button after viewing the comments");

		} else {
			log.logLine(Testname, true, "Clicking on OK button after viewing the comments is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on OK button after viewing the comments is failed");
		}


		if (doesElementExist2(properties.getProperty("ChoseAct2"), 5)) {
			log.logLine(Testname, false, "Clicking on Choose Action to add comments..");

			WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("ChoseAct2")));
			log.logLine(Testname, false, "Clicking on AddComments under choose action");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
			Thread.sleep(5000);

			List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SelReportLnk")));	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals("Add Comments")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(2000);
					PleasewaitDisappear();
				}
			}
		} else {
			log.logLine(Testname, true, "Clicking on Choose actions failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Choose actions failed");
		}

		//int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		//String AccNo = Integer.toString(paperID);		

		if (doesElementExist2(properties.getProperty("Comtstxtbox"), 5)) {	    
			WebElement btxt = driver.findElement(By.cssSelector(properties.getProperty("Comtstxtbox")));		
			btxt.sendKeys("0 23456789A 23456789B 23456789C 23456789D 23456789E 23456789F 23456789G 23456789H 23456789I 23456789J 23456789K 23456789L 23456789M 23456789N 23456789O 23456789P 23456789Q 23456789R 23456789S 23456789T 23456789U 23456789V 23456789W 23456789X 23456789Y 2345");		
			log.logLine(Testname, false, "Entering the comments in textarea field with "+"AutoComments_"+AccNo);
		} else {
			log.logLine(Testname, true, "Entering the comments in textarea field is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the comments in textarea field is failed");
		}	


		if (doesElementExist2(properties.getProperty("ComtsSavebtn"), 5)) {	    
			WebElement canclbtn = driver.findElement(By.cssSelector(properties.getProperty("ComtsSavebtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", canclbtn);
			PleasewaitDisappear();

			log.logLine(Testname, false, "Clicking on Save button to add comments for audit");

			if (doesElementExist2(properties.getProperty("Comtstxtbox"), 5)) 
				log.logLine(Testname, false, "Add Comments - Save is successful");
			else 
				log.logLine(Testname, true, "Add Comments - Save is unsuccessful");

		} else {
			log.logLine(Testname, true, "Clicking on Save button to add comments for audit is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Save button to add comments for audit is failed");
		}


		//View Comments
		if (doesElementExist2(properties.getProperty("ChoseAct2"), 5)) {
			log.logLine(Testname, false, "Clicking on Choose Action to add comments..");

			WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("ChoseAct2")));
			log.logLine(Testname, false, "Clicking on View Comments under choose action");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
			Thread.sleep(5000);

			List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SelReportLnk")));	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals("View Comments")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);			
					Thread.sleep(2000);
					PleasewaitDisappear();

				}
			}
		} else {
			log.logLine(Testname, true, "Clicking on Choose actions failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Choose actions failed");
		}

		//if (lnk.getText().endsWith(("14"))||lnk.getText().endsWith(("13"))){


		if (doesElementExist2(properties.getProperty("ComtsView"), 5)) {	    
			String Comments = driver.findElement(By.cssSelector(properties.getProperty("ComtsView"))).getText();
			log.logLine(Testname, false, "Verify the added comments,..");

			if (Comments.contains(("0 23456789A 23456789B 23456789C 23456789D 23456789E 23456789F 23456789G 23456789H 23456789I 23456789J 23456789K 23456789L 23456789M 23456789N 23456789O 23456789P 23456789Q 23456789R 23456789S 23456789T 23456789U 23456789V 23456789W 23456789X 23456789Y 2345")) && Comments.contains(("AutoComments_"+AccNo))) {
				log.logLine(Testname, false, "View Comments is successful");				
			} else {
				log.logLine(Testname, true, "View Comments is unsuccessful");
			}			

		} else {
			log.logLine(Testname, true, "Entering the comments in textarea field is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the comments in textarea field is failed");
		}	


		if (doesElementExist2(properties.getProperty("ComtsOKbtn"), 5)) {	    
			WebElement canclbtn = driver.findElement(By.cssSelector(properties.getProperty("ComtsOKbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", canclbtn);
			PleasewaitDisappear();

			log.logLine(Testname, false, "Clicking on OK button after viewing the comments");

		} else {
			log.logLine(Testname, true, "Clicking on OK button after viewing the comments is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on OK button after viewing the comments is failed");
		}


		driver.switchTo().defaultContent();

		return true;


	}

	public boolean quickAdvancedSearch(String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	    

		Thread.sleep(2000);
		driver.switchTo().frame("iframeContainer");    

		// Search Validate(date)
		if (doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5)) {
			List<WebElement> clntdd = driver.findElements(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			for (WebElement btn:clntdd) {
				if (btn.getText().equals("Advanced Search")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					PleasewaitDisappear();
					log.logLine(Testname, false, "Clicking on Advanced Search button ");
					break;
				}
			}
		}else {
			log.logLine(Testname, true, "Clicking on Advanced Search button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Advanced Search button is failed");		
		}

		if (doesElementExist2(properties.getProperty("FromDate"), 5)) {
			log.logLine(Testname, false, "Clearing the default From date in advanced search dialog");
			WebElement frmfld = driver.findElement(By.cssSelector(properties.getProperty("FromDate")));
			frmfld.clear();
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", frmfld);

			WebElement tofld = driver.findElement(By.cssSelector(properties.getProperty("ToDate")));
			log.logLine(Testname, false, "Clearing the default To date in advanced search dialog");
			tofld.clear();		    		    		    
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", tofld);

			if (doesElementExist2(properties.getProperty("DateValidate"), 5)) {
				log.logLine(Testname, false, "Search validation - from & to date fields validated Successfully by displaying invalid tooltip message");		    	
			} else {
				log.logLine(Testname, false, "Search validation - invalid tooltip message is not displayed when from & to dates are empty");
			}

			//Click on Cancel button in advanced search
			if (doesElementExist2(properties.getProperty("CancelbtnAdsrch"), 5)) {
				WebElement Cancelbtn = driver.findElement(By.cssSelector(properties.getProperty("CancelbtnAdsrch")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", Cancelbtn);

				log.logLine(Testname, false, "Clicking on Cancel button in advanced search dialog");
			} else {
				log.logLine(Testname, true, "Cancel button in advanced dialog does not exist");
				driver.switchTo().defaultContent();
				throw new Exception("Cancel button in advanced dialog does not exist");
			}
		}


		if (doesElementExist2(properties.getProperty("QuickSrch"), 5)) {	
			WebElement quksrch = driver.findElement(By.cssSelector(properties.getProperty("QuickSrch")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", quksrch);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicking on quick search button ie Last 10 Days button ");
		} else {
			log.logLine(Testname, true, "Clicking on quick search button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on quick search button is failed");
		}

		if (doesElementExist2(properties.getProperty("ReportTable"), 5)) {
			log.logLine(Testname, false, "Quick search - Successfully displayed the proofs for last 10 days");		    	
		} else {
			log.logLine(Testname, false, "Quick search - No proofs found for the last 10 days");
		}

		driver.switchTo().defaultContent();

		// Get the proofs based on the type
		String prfType = test.readColumnData("ProofType", sheetname);
		AdvancedSearch(Testname, "ProofType", prfType, "");			


		// Get the proofs based on the RefenceCode
		String RefCode = test.readColumnData("RefenceCode", sheetname);
		AdvancedSearch(Testname, "RefenceCode", RefCode, "");


		// Get the proofs based on the status from the dropdown
		String prfStatus = test.readColumnData("ProofStatus", sheetname);
		AdvancedSearch(Testname, "ProofStatus", prfStatus, "");


		// Display the reports between from and to dates	    
		String Fromdateval = test.readColumnData("FromDate", sheetname);	    
		//String Todateval = test.readInputData("ToDate", sheetname);

		//SimpleDateFormat gmtDateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		//String Todateval = gmtDateFormat.format(new Date());

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		Date date = new Date();
		String Todateval = dateFormat.format(date);

		AdvancedSearch(Testname, "FROMTOdate", Fromdateval, Todateval);  	    

		return true;
	}		


	public void AdvancedSearch(String Testname, String SrchType, String testdata1, String testdata2) throws Exception {

		driver.switchTo().frame("iframeContainer");

		if (!(doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5))) {	   
			log.logLine(Testname, true, "Clicking on Advanced Search button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Advanced Search button is failed");

		} else {
			List<WebElement> clntdd = driver.findElements(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			for (WebElement btn:clntdd) {
				if (btn.getText().equals("Advanced Search")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Clicking on Advanced Search button ");
					break;
				}
			}

			switch (SrchType) {

			case "FROMTOdate":
				if (doesElementExist2(properties.getProperty("FromDate"), 5)) {	  
					WebElement DteFromfld = driver.findElement(By.cssSelector(properties.getProperty("FromDate")));
					DteFromfld.clear();
					//DteFromfld.click();
					DteFromfld.sendKeys(testdata1);
					log.logLine(Testname, false, "Entering the from date value in advanced search");

					WebElement DteTofld = driver.findElement(By.cssSelector(properties.getProperty("ToDate")));
					DteTofld.clear();
					//DteTofld.click();
					DteTofld.sendKeys(testdata2);	        			
					log.logLine(Testname, false, "Entering the To date value in advanced search");
				}	            	
				break;

			case "ProofType":


				if (doesElementExist2(properties.getProperty("ProofDD"), 5)) {	
					log.logLine(Testname, false, "Clicking on Proof type drop down");

					WebElement quksrch = driver.findElement(By.cssSelector(properties.getProperty("ProofDD")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", quksrch);

					List<WebElement> dayten = driver.findElements(By.cssSelector(properties.getProperty("SelProoftype")));	
					for (WebElement lnk:dayten) {
						if (lnk.getText().equals(testdata1)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							PleasewaitDisappear();
							log.logLine(Testname, false, "Selecting proof type as "+testdata1);
							break;
						}
					}

				} else {
					log.logLine(Testname, true, "Clicking on Proof type drop down is failed");
					driver.switchTo().defaultContent();
					throw new Exception("Clicking on Proof type drop down is failed");
				}	                
				break;

			case "RefenceCode":
				if (doesElementExist2(properties.getProperty("RefCodeAdsrch"), 5)) {
					WebElement refcod = driver.findElement(By.cssSelector(properties.getProperty("RefCodeAdsrch")));
					refcod.clear();
					refcod.sendKeys(testdata1);

					log.logLine(Testname, false, "Entering the "+testdata1 +" into Ref code value in advanced search dialog");
				} else {
					log.logLine(Testname, true, "Ref Code field in Advanced search dialog does not exist");
					throw new Exception("Ref Code field in Advanced search dialog does not exist");
				}
				break;

			case "ProofStatus":

				if (doesElementExist2(properties.getProperty("prftypedd"), 5)) {	

					WebElement quksrch = driver.findElement(By.cssSelector(properties.getProperty("prftypedd")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", quksrch);
					log.logLine(Testname, false, "Clicking on the proof status dropdown list in advanced search dialog");

					if (doesElementExist2(properties.getProperty("prfstatusAdsrch"), 5)) {
						List<WebElement> selloca = driver.findElements(By.cssSelector(properties.getProperty("prfstatusAdsrch")));	
						for (WebElement lnk:selloca) {
							if (lnk.getText().equals(testdata1)) {
								((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
								PleasewaitDisappear();
								log.logLine(Testname, false, "Selecting the Pending option from the proof status dropdown list in advanced search dialog");
								break;
							}
						}
					} else {
						log.logLine(Testname, true, "Selecting proof status dropdown list in advanced search dialog is failed");
						driver.switchTo().defaultContent();
						throw new Exception("Selecting proof status dropdown list in advanced search dialog is failed");
					}

				} else {
					log.logLine(Testname, true, "Clicking on the proof status dropdown list in advanced search dialog is failed");
					driver.switchTo().defaultContent();
					throw new Exception("Clicking on the proof status dropdown list in advanced search dialog is failed");
				}
				break;



			}			

			if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	    
				WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
				Thread.sleep(2000);
				PleasewaitDisappear();
				//((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)", "");
				log.logLine(Testname, false, "Clicking on Search button to view the proofs based on "+SrchType);
			} else {
				log.logLine(Testname, true, "Clicking on Search button to view the proofs based on "+SrchType +" is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on Search button to view the proofs based on "+SrchType +" is failed");
			}

			if (doesElementExist2(properties.getProperty("ReportTable"), 5)) {
				log.logLine(Testname, false, "Advanced search - Successfully displayed the proofs for "+testdata1 +" " +testdata2 +" entered in "+SrchType+" field");		    		    	
			} else {
				log.logLine(Testname, false, "Advanced search - No proofs found for "+testdata1  +" " +testdata2 +" entered in "+SrchType+" field");
			}			

			boolean Chkdownld = false;
			Actions action = new Actions(driver);
			//Download the report file from reports grid
			if (SrchType.equals("FROMTOdate")) {	

				if (doesElementExist2(properties.getProperty("ViewFirstRpt"), 5)) {
					WebElement viewrptbtn = driver.findElement(By.cssSelector(properties.getProperty("ViewFirstRpt")));		    		
					action.click(viewrptbtn).perform(); //Click
					Thread.sleep(60000);

					Set<String> handles = driver.getWindowHandles();
					String firstWinHandle = driver.getWindowHandle(); 
					handles.remove(firstWinHandle);

					boolean browserexist = handles.iterator().hasNext();
					if (browserexist) {
						String winHandle=handles.iterator().next();
						if (winHandle!=firstWinHandle){
							//To retrieve the handle of second window, extracting the handle which does not match to first window handle
							String secondWinHandle=winHandle; //Storing handle of second window handle

							//Switch control to new window
							driver.switchTo().window(secondWinHandle);


							if ((Initialization.EnvirSite.equals("TEST")) || (Initialization.EnvirSite.equals("Test")) || (Initialization.EnvirSite.equals("test"))) {
								if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) 
										|| (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) 
									driver.get("javascript:document.getElementById('overridelink').click();");
							}

							Thread.sleep(5000);
							driver.close();

							// Switching back to parent window
							driver.switchTo().window(firstWinHandle);

							Thread.sleep(2000);
							driver.switchTo().frame("iframeContainer");
						}
					}							    

					log.logLine(Testname, false, "Click on Date link to view the report in the grid is successful");
				}


				Thread.sleep(1000);
				if (doesElementExist2(properties.getProperty("ChoseAct2"), 5)) {	
					log.logLine(Testname, false, "Advanced search based on From and To dates successful");

					WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("ChoseAct2")));
					log.logLine(Testname, false, "Clicking on Choose Action to view the report..");
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
					List<WebElement> vwrpts = driver.findElements(By.cssSelector(properties.getProperty("SelReportLnk")));	
					for (WebElement lnk:vwrpts) {
						if (lnk.getText().equals("View Proof")) {
							action.click(lnk).perform();
							Thread.sleep(20000);
							Set<String> handles = driver.getWindowHandles();
							String firstWinHandle = driver.getWindowHandle(); 
							handles.remove(firstWinHandle);

							boolean browserexist = handles.iterator().hasNext();
							if (browserexist) {
								String winHandle=handles.iterator().next();
								if (winHandle!=firstWinHandle){
									//To retrieve the handle of second window, extracting the handle which does not match to first window handle
									String secondWinHandle=winHandle; //Storing handle of second window handle

									//Switch control to new window
									driver.switchTo().window(secondWinHandle);

									/*if ((Initialization.EnvirSite.equals("TEST")) || (Initialization.EnvirSite.equals("Test")) || (Initialization.EnvirSite.equals("test"))) {
							    	if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) 
							    			|| (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) 
						    			driver.get("javascript:document.getElementById('overridelink').click();");
						    	}*/

									Thread.sleep(10000);
									driver.close();

									// Switching back to parent window
									driver.switchTo().window(firstWinHandle);

									Thread.sleep(5000);
									driver.switchTo().frame("iframeContainer");
								}
							}

							log.logLine(Testname, false, "Clicking on View Proof link under choose action in the proofs grid");
							Chkdownld = true;							
							break;
						}
					}
					log.logLine(Testname, false, "Clicking on ChooseActions in the proofs grid successful");
				} else {
					log.logLine(Testname, true, "Clicking on ChooseActions in the proofs grid is failed");
					driver.switchTo().defaultContent();
					throw new Exception("Clicking on ChooseActions in the proofs grid is failed");
				}	

				//Closing the Adobe processes running
				Runtime.getRuntime().exec("taskkill /F /IM AcroRd32.exe");

				if (Chkdownld) {
					Thread.sleep(500);
					//ViewDownloadReport(Testname);

				}				    

			}			
		}	
		driver.switchTo().defaultContent();	

	}


	public void progressstatusinfoicon(String testname) throws Exception {

		Actions builder = new Actions(driver);
		if (doesElementExist(properties.getProperty("progressinfo"), 10)) {                          
			WebElement sinfoicon= driver.findElement(By.xpath(properties.getProperty("progressinfo")));
			builder.moveToElement(sinfoicon).perform();                	
			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("progressinfolist")));
			for(WebElement lnk:selopts){
				log.logLine(testname, false, "The text in the progress info icon is "+lnk.getText());}
			WebElement nme= driver.findElement(By.cssSelector(properties.getProperty("namehead")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", nme);
		}else {
			log.logLine(testname, false, "'ProgressInfoicon' doesnot exists ");
			driver.switchTo().defaultContent();
			throw new Exception("'ProgressInfoicon' doesnot exists ");
		}
	}

	public void progressstatusimagevalidation(String Testname,String icontext,String status,int a) throws Exception {
		Actions builder = new Actions(driver);
		if (doesElementExist(properties.getProperty("progressinfo"), 10)) {                          
			WebElement sinfoicon= driver.findElement(By.xpath(properties.getProperty("progressinfo")));
			builder.moveToElement(sinfoicon).perform();                	
			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("progressinfolist")));		
			WebElement nme= driver.findElement(By.xpath(".//*[@id='webuiPopover0']/div[2]/div/div/ul/li["+a+"]/img"));
			String data=nme.getAttribute("alt");		
			WebElement nme1= driver.findElement(By.xpath(".//*[@id='webuiPopover0']/div[2]/div/div/ul/li["+a+"]"));
			String hint=nme1.getText();
			if((hint.equalsIgnoreCase(status))&&(data.equalsIgnoreCase(icontext))){								
				log.logLine(Testname, false, data+" image exists across the status "+hint);			
			}
			else {
				log.logLine(Testname, true, data+" image doesnot exists in the 'Progress status list'");	
			}
		}
		else {
			log.logLine(Testname, false, "'ProgressInfoicon' doesnot exists ");
			driver.switchTo().defaultContent();
			throw new Exception("'ProgressInfoicon' doesnot exists ");

		}
	}

	public void Descriptionvalidation(String Testname,String val) throws Exception {
		String row = "tr";
		String data="No";
		List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='proofing-grid']/div[2]/table/tbody/tr"));

		if(doesElementExist2(properties.getProperty("Description"), 5)){
			for (int i=1;i<=DataCnt.size();i++) {
				WebElement descrip= driver.findElement(By.xpath(".//*[@id='proofing-grid']/div[2]/table/tbody/tr["+i+"]/td[2]/div"));
				String des=descrip.getText();
				String[] abc=new String[20];
				abc=des.split("%");
				if (val.contains(abc[0])){
					data="Yes";
					int count=des.length();
					int count1=val.length();
					log.logLine(Testname, false, "No. of characters in des entered "+count1 +" is greater than the no. chars of desc in grid "+count);
					log.logLine(Testname, false, "Description entered while creating upload file >>>>> "+val+" >>> is similar as the one in grid >>> "+des);
					break;
				} 			
			}
			if (data.equalsIgnoreCase("NO")) {
				log.logLine(Testname, true, "Description didnot match with any of the rows in the grid");
				driver.switchTo().defaultContent();
				throw new Exception("Description didnot match with any of the rows in the grid");
			}
		}
		else{
			log.logLine(Testname, true, "Heading description doesnot exists in the grid");
			driver.switchTo().defaultContent();
			throw new Exception("Heading description doesnot exists in the grid");
		}
	}

	public void referencevalidation(String Testname,String val) throws Exception {
		String row = "tr";
		String data="No";
		List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='proofing-grid']/div[2]/table/tbody/tr"));

		if(doesElementExist2(properties.getProperty("reference"), 5)){
			for (int i=1;i<=DataCnt.size();i++) {
				String res= driver.findElement(By.xpath(".//*[@id='proofing-grid']/div[2]/table/tbody/tr["+i+"]/td[4]/span")).getText();
				String[] abc=new String[20];
				abc=res.split("%");

				if (val.contains(abc[0])){
					data="Yes";
					int count=res.length();
					int count1=val.length();
					log.logLine(Testname, false,"No. of characters in res entered "+count1 +" is greater than the no. chars of res in grid "+count);
					log.logLine(Testname, false, "reference no entered while creating upload file >>> "+val+" >>> is same as the one in grid >> "+res);
					break;
				} 				
			}
			if (data.equalsIgnoreCase("NO")) {
				log.logLine(Testname, true, "reference no. didnot match with any of the rows in the grid");
				driver.switchTo().defaultContent();
				throw new Exception("reference no. didnot match with any of the rows in the grid");
			}
		}
		else{
			log.logLine(Testname, true, "Heading reference doesnot exists in the grid");
			driver.switchTo().defaultContent();
			throw new Exception("Heading reference doesnot exists in the grid");
		}
	}
}

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



public class ViewProofs extends Page{

	public static String Specialchar="@$#&%/()=?¡!:;[_¿+´'{.,-";

	public ViewProofs(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}	
	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}

	public boolean IEcheck=false;
	
	public boolean proofViewer(String AccNo,String Testname) throws Exception  {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(1000);
		driver.switchTo().defaultContent();
		WebElement retelm2 = waitForElement(properties.getProperty("selClint1"));		     


		if (doesElementExist2(properties.getProperty("Proofs"), 5)) {	    
			WebElement proofsmnu = driver.findElement(By.cssSelector(properties.getProperty("Proofs")));
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

		return true;
	}

	public boolean VerifyUserAccess(String AccNo,String Testname) throws Exception { 		

		InputOutputData test = new InputOutputData(); 
		test.setInputFile(properties.getProperty("InputDatafile")); 		
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		if (doesElementExist2(properties.getProperty("SignOutBtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("SignOutBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Sign Out button");

		}else if (doesElementExist2(properties.getProperty("SignOutBtn1"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("SignOutBtn1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Sign Out button");

		}else if (doesElementExist2(properties.getProperty("SignOutBtn2"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("SignOutBtn2")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Sign Out button");
		}

		else {
			log.logLine(Testname, true, "Clicking on Sign Out button is failed");

		}

		//PIVOTRTRRDSITE

		String Usrnme = test.readColumnData("UserName", sheetname);
		String Paswd = test.readColumnData("Password", sheetname);	
		signin(Testname,Usrnme,Paswd);
		Thread.sleep(4000);
		proofViewer(AccNo,Testname);
		Thread.sleep(4000);
		Proofsearch_validation(Testname,Usrnme);
		Thread.sleep(2000);	
		signout(Testname);
		Thread.sleep(5000);

		//PIVOTRTRRDCLIENT

		String Usrnme1 = test.readColumnData("UserName1", sheetname);
		String Paswd1 = test.readColumnData("Password", sheetname);
		signin(Testname,Usrnme1,Paswd1);
		Thread.sleep(4000);
		proofViewer(AccNo,Testname);
		Thread.sleep(4000);
		Proofsearch_validation(Testname,Usrnme1);
		Thread.sleep(2000);
		signout(Testname);
		Thread.sleep(5000);

		//PIVOTRTRRDUSER 

		String Usrnme2 = test.readColumnData("UserName2", sheetname);
		String Paswd2 = test.readColumnData("Password", sheetname);
		signin(Testname,Usrnme2,Paswd2);
		Thread.sleep(4000);
		proofViewer(AccNo,Testname);
		Thread.sleep(4000);
		Proofsearch_validation(Testname,Usrnme2);
		Thread.sleep(2000);
		signout(Testname);
		Thread.sleep(5000);
		
		//PIVOTRTCLIENTUSER 

		String Usrnme3 = test.readColumnData("UserName3", sheetname);
		String Paswd3 = test.readColumnData("Password", sheetname);
		signin(Testname,Usrnme3,Paswd3);
		Thread.sleep(4000);
		proofViewer(AccNo,Testname);
		Thread.sleep(4000);
		Proofsearch_validation(Testname,Usrnme3);
		Thread.sleep(2000);
		signout(Testname);
		Thread.sleep(5000);

		//Manohar
		
		String Usrnme4 = test.readColumnData("UserName4", sheetname);
		String Paswd4 = test.readColumnData("Password", sheetname);
		signin(Testname,Usrnme4,Paswd4);
		Thread.sleep(4000);
		proofViewer(AccNo,Testname);
		Thread.sleep(4000);
		Proofsearch_validation(Testname,Usrnme4);
		Thread.sleep(2000);

		return true;
	}


	public void Proofsearch_validation(String Testname,String user) throws Exception {
		if (doesElementExist2(properties.getProperty("searchnuplo"), 5)) {	    
			WebElement status = driver.findElement(By.cssSelector(properties.getProperty("searchnuplo")));
			String access=status.getText();
			log.logLine(Testname, false, "The label of the search button for user "+user+" is  '"+access+"' ");			

		} else {
			log.logLine(Testname, true, "Proof Search button didnot display");
			throw new Exception("Proof Search button didnot display");	
		}}


	public void signout(String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("usrSignOutBtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("usrSignOutBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicking on Sign Out button");

		}else if (doesElementExist2(properties.getProperty("usrSignOutBtn1"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("usrSignOutBtn1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicking on Sign Out button for First user");

		}else {
			log.logLine(Testname, true, "Clicking on Sign Out button is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
		}	
	}

	public void signin(String Testname,String User,String pwd)throws Exception {

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("UserName"), 5)) {	    
			WebElement Username = driver.findElement(By.cssSelector(properties.getProperty("UserName")));
			Username.sendKeys(User);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entering the User Name as" +User);
		} else {
			log.logLine(Testname, true, "Entering user name is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);	
		}

		if (doesElementExist2(properties.getProperty("Password"), 5)) {	    
			WebElement Password = driver.findElement(By.cssSelector(properties.getProperty("Password")));
			Password.sendKeys(pwd);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Entering the Password as" +pwd);
		} else {
			log.logLine(Testname, true, "Entering Password is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
		}


		if (doesElementExist2(properties.getProperty("loginBtn"), 5)) {	    
			WebElement lgnbtn = driver.findElement(By.cssSelector(properties.getProperty("loginBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", lgnbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Click on Login Button");
		} else {
			log.logLine(Testname, true, "Click on Login Button is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);	
		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
		}	
	}

	public boolean quickAdvancedSearch(String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	    


		String SubMenu1="Search & Upload";

		if (doesElementExist2(properties.getProperty("Srchuploadlbl"), 5)) {
			String submnu = driver.findElement(By.cssSelector(properties.getProperty("Srchuploadlbl"))).getText();
			if(submnu.equals(SubMenu1)){
				log.logLine(Testname, false, "SubMenu <<<< "+submnu+" >>>> Matches with <<<< " +SubMenu1+ " >>>> ");
			} else{
				log.logLine(Testname, true, "SubMenu not matching with requirement");
			}
		}

		/*String SubMenu2="Compare Proofs";

		if (doesElementExist2(properties.getProperty("Cmpreprofs"), 5)) {
			String submnu = driver5.findElement(By.cssSelector(properties.getProperty("Cmpreprofs"))).getText();
			if(submnu.equals(SubMenu2)){
				log.logLine(Testname, false, "SubMenu <<<< "+submnu+" >>>> Matches with <<<< " +SubMenu2+ " >>>> ");
			} else{
				log.logLine(Testname, true, "SubMenu not matching with requirement");
			}
		}
*/

		Thread.sleep(2000);
		driver.switchTo().frame("iframeContainer"); 


		String Srchrsltnme="Proof Search Results";

		if (doesElementExist2(properties.getProperty("Prfname"), 5)) {
			String prfnme = driver.findElement(By.cssSelector(properties.getProperty("Prfname"))).getText();
			if(prfnme.equals(Srchrsltnme)){
				log.logLine(Testname, false, "SubMenu <<<< "+prfnme+" >>>> Matches with <<<< " +Srchrsltnme+ " >>>> ");
			} else{
				log.logLine(Testname, true, "SubMenu not matching with requirement");
			}
		}


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
			frmfld.click();

			WebElement tofld = driver.findElement(By.cssSelector(properties.getProperty("ToDate")));
			log.logLine(Testname, false, "Clearing the default To date in advanced search dialog");
			tofld.clear();		    		    		    
			tofld.click();

			if (doesElementExist2(properties.getProperty("DateValidate"), 5)) {
				log.logLine(Testname, false, "Search validation - from & to date fields validated Successfully by displaying invalid tooltip message");		    	
			} else {
				log.logLine(Testname, false, "Search validation - invalid tooltip message is not displayed when from & to dates are empty");
			}

			if (doesElementExist2(properties.getProperty("ClearbtnAdvsrch"), 5)) {
				WebElement Cancelbtn = driver.findElement(By.cssSelector(properties.getProperty("ClearbtnAdvsrch")));
				Cancelbtn.click();

				log.logLine(Testname, false, "Clicking on Clear button in advanced search dialog");
			} else {
				log.logLine(Testname, true, "Clear button in advanced dialog does not exist");
				driver.switchTo().defaultContent();
				throw new Exception("Clear button in advanced dialog does not exist");
			}


			//Click on Cancel button in advanced search
			if (doesElementExist2(properties.getProperty("CancelbtnAdsrch"), 5)) {
				WebElement Cancelbtn = driver.findElement(By.cssSelector(properties.getProperty("CancelbtnAdsrch")));
				Cancelbtn.click();

				log.logLine(Testname, false, "Clicking on Cancel button in advanced search dialog");
			} else {
				log.logLine(Testname, true, "Cancel button in advanced dialog does not exist");
				driver.switchTo().defaultContent();
				throw new Exception("Cancel button in advanced dialog does not exist");
			}
		}

		Thread.sleep(2000);

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
		SimpleDateFormat gmtDateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		String Todateval = gmtDateFormat.format(new Date());	    
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
				/*


	            	if (doesElementExist2(properties.getProperty("prfTypeAdsrch"), 5)) {
	    				WebElement pftype = driver.findElement(By.cssSelector(properties.getProperty("prfTypeAdsrch")));	    				
	    				pftype.clear();
	    				pftype.sendKeys(testdata1);

	    				log.logLine(Testname, false, "Entering the ProofType - "+testdata1 +" in advanced search dialog");
	    			} else {
	    				log.logLine(Testname, true, "Unable to find the ProofType in Advanced search dialog");
	    				throw new Exception("Unable to find the ProofType in Advanced search dialog");
	    			}
				 */	                
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
				Thread.sleep(10000);
				PleasewaitDisappear();
				//((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)", "");
				log.logLine(Testname, false, "Clicking on Search button to view the proofs based on "+SrchType);
			} else {
				log.logLine(Testname, true, "Clicking on Search button to view the proofs based on "+SrchType +" is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on Search button to view the proofs based on "+SrchType +" is failed");
			}

			Thread.sleep(5000);
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
					Thread.sleep(25000);

					Set<String> handles = driver.getWindowHandles();
					String firstWinHandle = driver.getWindowHandle(); 
					handles.remove(firstWinHandle);

					boolean browserexist = handles.iterator().hasNext();
					if (browserexist) {
						String winHandle=handles.iterator().next();
						if (winHandle!=firstWinHandle){
							//To retrieve the handle of second window, extracting the handle which does not match to first window handle
							String secondWinHandle=winHandle; //Storing handle of second window handle
							log.logLine(Testname, false, "Proofs opened up and viewed successfully in the second window");

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


				Thread.sleep(5000);
				if (doesElementExist2(properties.getProperty("ChoseAct2"), 5)) {	
					log.logLine(Testname, false, "Advanced search based on From and To dates successful");

					WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("ChoseAct2")));
					log.logLine(Testname, false, "Clicking on Choose Action to view the report..");
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
					List<WebElement> vwrpts = driver.findElements(By.xpath(properties.getProperty("SelReportLnk")));	
					for (WebElement lnk:vwrpts) {
						if (lnk.getText().equals("View Proof")) {
							action.click(lnk).perform();
							Thread.sleep(25000);
							Set<String> handles = driver.getWindowHandles();
							String firstWinHandle = driver.getWindowHandle(); 
							handles.remove(firstWinHandle);

							boolean browserexist = handles.iterator().hasNext();
							if (browserexist) {
								String winHandle=handles.iterator().next();
								if (winHandle!=firstWinHandle){
									//To retrieve the handle of second window, extracting the handle which does not match to first window handle
									String secondWinHandle=winHandle; //Storing handle of second window handle
									log.logLine(Testname, false, "Proofs opened up and viewed successfully in the second window");

									//Switch control to new window
									driver.switchTo().window(secondWinHandle);

									/*if ((Initialization.EnvirSite.equals("TEST")) || (Initialization.EnvirSite.equals("Test")) || (Initialization.EnvirSite.equals("test"))) {
								    	if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) 
								    			|| (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) 
							    			driver.get("javascript:document.getElementById('overridelink').click();");
							    	}*/

									Thread.sleep(5000);
									driver.close();

									// Switching back to parent window
									driver.switchTo().window(firstWinHandle);

									Thread.sleep(2000);
									driver.switchTo().frame("iframeContainer");
								}
							}

							log.logLine(Testname, false, "Clicking on View Proof link under choose action in the proofs grid");
							Chkdownld = true;							
							break;
						}
					}
					log.logLine(Testname, false, "Clicking on ChooseActions in the proofs grid");
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


	public boolean AdvsrchNegativecase(String Testname) throws Exception {

		Thread.sleep(2000);

		// Get the reports based on the Ref code with no matching data entered	    
		AdvancedSearch(Testname, "RefenceCode", "NOMATCHING DATA", "");	    

		// Fill up advanced search field and click cancel button
		fillupAdvancedSrch(Testname, "Yes", "");  

		Thread.sleep(1000);	
		return true;		
	}


	public void fillupAdvancedSrch(String Testname, String ClrCancel, String Hide) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

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

			String Fromdateval = test.readColumnData("FromDate", sheetname);	    
			String Todateval = test.readColumnData("ToDate", sheetname);				
			if (doesElementExist2(properties.getProperty("FromDate"), 5)) {	  
				WebElement DteFromfld = driver.findElement(By.cssSelector(properties.getProperty("FromDate")));
				DteFromfld.clear();
				DteFromfld.sendKeys(Fromdateval);
				log.logLine(Testname, false, "Entering the from date value in advanced search");

				WebElement DteTofld = driver.findElement(By.cssSelector(properties.getProperty("ToDate")));
				DteTofld.clear();
				DteTofld.sendKeys(Todateval);
				log.logLine(Testname, false, "Entering the To date value in advanced search");
			}

			String RefCode = test.readColumnData("RefenceCode", sheetname);
			if (doesElementExist2(properties.getProperty("RefCodeAdsrch"), 5)) {
				WebElement jobcod = driver.findElement(By.cssSelector(properties.getProperty("RefCodeAdsrch")));
				jobcod.clear();
				jobcod.sendKeys(RefCode);

				log.logLine(Testname, false, "Entering the "+RefCode +" into Ref code value in advanced search dialog");
			} else {
				log.logLine(Testname, true, "Ref Code field in Advanced search dialog does not exist");
				driver.switchTo().defaultContent();
				throw new Exception("Ref Code field in Advanced search dialog does not exist");
			}

			String prfType = test.readColumnData("ProofType", sheetname);

			if (doesElementExist2(properties.getProperty("ProofDD"), 5)) {	
				log.logLine(Testname, false, "Clicking on Proof type drop down");

				WebElement quksrch = driver.findElement(By.cssSelector(properties.getProperty("ProofDD")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", quksrch);

				List<WebElement> dayten = driver.findElements(By.cssSelector(properties.getProperty("SelProoftype")));	
				for (WebElement lnk:dayten) {
					if (lnk.getText().equals(prfType)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						PleasewaitDisappear();
						log.logLine(Testname, false, "Selecting proof type as "+prfType);
						break;
					}
				}

			} else {
				log.logLine(Testname, true, "Clicking on Proof type drop down is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on Proof type drop down is failed");
			}
			/*
			if (doesElementExist2(properties.getProperty("prfTypeAdsrch"), 5)) {
				WebElement pftype = driver.findElement(By.cssSelector(properties.getProperty("prfTypeAdsrch")));
				pftype.clear();
				pftype.sendKeys(prfType);

				log.logLine(Testname, false, "Entering the ProofType - "+prfType +" in advanced search dialog");
			} else {
				log.logLine(Testname, true, "Unable to find the ProofType in Advanced search dialog");
				driver.switchTo().defaultContent();
				throw new Exception("Unable to find the ProofType in Advanced search dialog");
			}
			 */
			String prfStatus = test.readColumnData("ProofStatus", sheetname);
			if (doesElementExist2(properties.getProperty("prftypedd"), 5)) {	

				WebElement quksrch = driver.findElement(By.cssSelector(properties.getProperty("prftypedd")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", quksrch);
				log.logLine(Testname, false, "Clicking on the proof status dropdown list in advanced search dialog");

				List<WebElement> selloca = driver.findElements(By.cssSelector(properties.getProperty("prfstatusAdsrch")));	
				for (WebElement lnk:selloca) {
					if (lnk.getText().equals(prfStatus)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						PleasewaitDisappear();
						log.logLine(Testname, false, "Selecting the Pending option from the proof status dropdown list in advanced search dialog");
						break;
					}
				}
			}
			else {
				log.logLine(Testname, true, "Clicking on the proof status dropdown list in advanced search dialog is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on the proof status dropdown list in advanced search dialog is failed");
			}


			//Click on Clear button in advanced search
			if (ClrCancel.equals("Yes")) {			
				//Click on Cancel button in advanced search
				if (doesElementExist2(properties.getProperty("CancelbtnAdsrch"), 5)) {
					WebElement Cancelbtn = driver.findElement(By.cssSelector(properties.getProperty("CancelbtnAdsrch")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Cancelbtn);
					//Cancelbtn.click();

					log.logLine(Testname, false, "Clicking on Cancel button in advanced search dialog");
				} else {
					log.logLine(Testname, true, "Cancel button in advanced dialog does not exist");
					driver.switchTo().defaultContent();
					throw new Exception("Cancel button in advanced dialog does not exist");
				}	

			}

			driver.switchTo().defaultContent(); 
			return;
		}		
	}


	//Method for edit proofing
	public boolean editProofing(String Testname, String AccNo) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(2000);	    

		// Get the proofs based on the status from the dropdown
		String prfStatus = test.readColumnData("ProofStatus", sheetname);
		AdvancedSearch(Testname, "ProofStatus", prfStatus, "");

		driver.switchTo().frame("iframeContainer");
		editinternal(Testname, AccNo);

		//Click on Cancel button under choose action
		Thread.sleep(4000);
		Actions action = new Actions(driver);
		if (doesElementExist2(properties.getProperty("UpdChoseAct"), 5)) {
			WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("UpdChoseAct")));
			log.logLine(Testname, false, "Clicking on Choose Action to Cancel Edit the proof..");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);			
			Thread.sleep(2000);

			List<WebElement> vwrpts = driver.findElements(By.xpath(properties.getProperty("SelReportLnkcncl")));	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals("Cancel")) {
					action.click(lnk).perform();    
					Thread.sleep(5000);
					//PleasewaitDisappear();			

					log.logLine(Testname, false, "Clicking on Cancel button to unsave the modification");			
					break;
				}
			}

			Thread.sleep(10000);
			if (doesElementExist2(properties.getProperty("Descipfld1"), 5)) {
				WebElement desmodify = driver.findElement(By.cssSelector(properties.getProperty("Descipfld1")));
				if (desmodify.getText().equals("AutoProof"+AccNo)) {			    
					log.logLine(Testname, true, "Cancel Edit proof is unsuccessful");
				} else {
					log.logLine(Testname, false, "Cancel Edit proof is successful");
				}

			}			
		} else {
			log.logLine(Testname, true, "Clicking on ChooseActions in the proofs grid is failed");
			throw new Exception("Clicking on ChooseActions in the proofs grid is failed");
		}

		/* 
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

	    if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	    
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			Thread.sleep(2000);
			PleasewaitDisappear();
			//((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)", "");
			log.logLine(Testname, false, "Clicking on Search button is successful");
	    } else {
	    	log.logLine(Testname, false, "Cancel Search button is unsuccessful");
	    }
		 */
		editinternal(Testname, AccNo);

		//Click on Update button under choose action
		if (doesElementExist2(properties.getProperty("UpdChoseAct"), 5)) {
			WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("UpdChoseAct")));
			log.logLine(Testname, false, "Clicking on Choose Action to Edit the proof..");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);

			List<WebElement> vwrpts = driver.findElements(By.xpath(properties.getProperty("SelReportLnkcncl")));	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals("Update")) {
					action.click(lnk).perform();   
					Thread.sleep(6000);
					PleasewaitDisappear();

					log.logLine(Testname, false, "Clicking on Update button to save the modification");			
					break;
				}
			}

			Thread.sleep(6000);
			if (doesElementExist2(properties.getProperty("Descipfld1"), 5)) {
				WebElement desmodify = driver.findElement(By.cssSelector(properties.getProperty("Descipfld1")));
				if (desmodify.getText().equals("AutoProof"+AccNo)) {
					log.logLine(Testname, true, "Edit proof is unsuccessful");

				} else {
					log.logLine(Testname, false, "Edit proof is successful");
				}

			}			
		} else {
			log.logLine(Testname, true, "Clicking on ChooseActions in the proofs grid is failed");
			throw new Exception("Clicking on ChooseActions in the proofs grid is failed");
		}

		driver.switchTo().defaultContent();  
		return true;
	}


	public void editinternal(String Testname, String AccNo) throws Exception {

		boolean Found = false;
		Actions action = new Actions(driver);

		if (doesElementExist2(properties.getProperty("ChoseAct2"), 5)) {
			WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("ChoseAct2")));
			log.logLine(Testname, false, "Clicking on Choose Action to Edit the proof..");

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
			Thread.sleep(2000);

			List<WebElement> vwrpts = driver.findElements(By.xpath(properties.getProperty("SelReportLnk")));	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals("Edit")) {

					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);

					//Verify User permission
					if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTADMIN")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTUSER"))) {
						log.logLine(Testname, true, "Client Admin and Client User should not have permission to Edit proof");
						throw new Exception("Incorrect Permission: Client Admin and Client User should not have permission to access Edit proof");


					} else if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSUPER")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSITE")) || 
							(PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDCLIENT")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDUSER"))) {

						log.logLine(Testname, false, "Permission Verified:RRD Super, RRD Site, RRD Client & RRD User have access to Edit proof");

					}					

					PleasewaitDisappear();
					Thread.sleep(4000);					
					Found = true;
					log.logLine(Testname, false, "Clicking on Edit link under choose action in the proofs grid");			
					break;
				}
			}

			//Verify User permission
			if ((!Found) && ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTADMIN")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTUSER")))) {
				log.logLine(Testname, true, "Client Admin and Client User does not have permission to Edit proof");
				throw new Exception("Incorrect Permission: Client Admin and Client User should not have permission to access Edit proof");

			} else if ((!Found) && ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSUPER")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSITE")) || 
					(PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDCLIENT")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDUSER")))) {

				log.logLine(Testname, false, "Permission Verified:RRD Super, RRD Site, RRD Client & RRD User have access to Edit proof");  			
			}			

		} else {
			log.logLine(Testname, true, "Clicking on ChooseActions in the proofs grid is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on ChooseActions in the proofs grid is failed");
		}


		if (doesElementExist2(properties.getProperty("Description"), 5)) {
			WebElement desmodify = driver.findElement(By.cssSelector(properties.getProperty("Description")));
			desmodify.clear();
			desmodify.sendKeys("AutoProof"+AccNo);
			log.logLine(Testname, false, "Modifying the Description field in proof grid table");
		} else {
			log.logLine(Testname, true, "Description field in proof grid does not exist");			
		}

		if (doesElementExist2(properties.getProperty("Referfld"), 5)) {
			WebElement refMod = driver.findElement(By.cssSelector(properties.getProperty("Referfld")));
			refMod.clear();
			refMod.sendKeys(AccNo);
			log.logLine(Testname, false, "Modifying the Reference field in proof grid table");
		} else {
			log.logLine(Testname, true, "Reference field in proof grid does not exist");			
		}

		if (doesElementExist2(properties.getProperty("prooftypefld"), 5)) {	

			WebElement prftype = driver.findElement(By.cssSelector(properties.getProperty("prooftypefld")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", prftype);
			log.logLine(Testname, false, "Clicking on the proof type dropdown in edit link under choose actions list ");

			List<WebElement> selprftyp = driver.findElements(By.cssSelector(properties.getProperty("prftypeadd")));	
			for (WebElement lnk:selprftyp) {
				if (lnk.getText().equals("Audit")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					PleasewaitDisappear();
					log.logLine(Testname, false, "Selecting the Audit option from the proof type dropdown list in edit link ");
					break;
				}
			}
		}
		else {
			log.logLine(Testname, true, "Clicking on the proof type dropdown in edit link under choose actions list is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on the proof type dropdown in edit link under choose actions list is failed");
		}



	}



	//Method for delete proofing
	public boolean deleteProofing(String Testname, String AccNo) throws Exception {

		Thread.sleep(2000);
		boolean Found=false;

		// Get the proofs based on the status from the dropdown
		AdvancedSearch(Testname, "ProofStatus", "Pending", "");

		AdvancedSearch(Testname, "RefenceCode", "S2461", "");

		driver.switchTo().frame("iframeContainer");

		//Get the name of proof to verify after the same is deleted
		String ProofName = null;
		if (doesElementExist2(properties.getProperty("Descipfld1"), 5)) {
			ProofName = driver.findElement(By.cssSelector(properties.getProperty("Descipfld1"))).getText();   


			//Click on Cancel button under choose action
			Actions action = new Actions(driver);
			if (doesElementExist2(properties.getProperty("ChoseAct2"), 5)) {
				WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("ChoseAct2")));
				log.logLine(Testname, false, "Clicking on Choose Action to Delete the proof..");
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);	

				Thread.sleep(3000);

				List<WebElement> vwrpts = driver.findElements(By.xpath(properties.getProperty("SelReportLnk")));	
				for (WebElement lnk:vwrpts) {
					if (lnk.getText().equals("Delete")) {
						//((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						lnk.click();

						//Verify User permission
						if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTADMIN")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTUSER"))) {
							log.logLine(Testname, true, "Client Admin and Client User should not have permission to Delete proof");
							throw new Exception("Incorrect Permission: Client Admin and Client User should not have permission to delete proofs");	

						} else if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSUPER")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSITE")) || 
								(PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDCLIENT")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDUSER"))) {

							log.logLine(Testname, false, "Permission Verified:RRD Super, RRD Site, RRD Client & RRD User have access to Delete proof");

						}

						Thread.sleep(10000);				
						//PleasewaitDisappear();
						//log.logLine(Testname, false, "Clicking on Delete button to remove the proof created");
						//Found=true;

						/*Thread.sleep(5000);
					    WebDriverWait wait1 = new WebDriverWait(driver, 2);
				        wait1.until(ExpectedConditions.alertIsPresent());
				        driver.switchTo().alert().accept();
						log.logLine(Testname, false, "Clicking on Delete button to remove the proof created");

						Alert alert = driver5.switchTo().alert();
						alert.accept();*/


						if (doesElementExist2(properties.getProperty("DelProofConf"), 5)) {
							WebElement delpfconf = driver.findElement(By.cssSelector(properties.getProperty("DelProofConf")));
							String msg = driver.findElement(By.cssSelector(properties.getProperty("DelProofConf"))).getText();
							log.logLine(Testname, false, "Reading the button Name as"+ msg);	
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", delpfconf);
							PleasewaitDisappear();

							log.logLine(Testname, false, "Clicking on OK button on confirm proof delete popup");						    
						} else {
							log.logLine(Testname, true, "Clicking on OK button on confirm proof delete popup is failed");
							throw new Exception("Clicking on OK button on confirm proof delete popup is failed");
						}

						//	break;
					}
				}

				Thread.sleep(2000);		
				if (doesElementExist2(properties.getProperty("Descipfld1"), 5)) {
					WebElement desmodify = driver.findElement(By.cssSelector(properties.getProperty("Descipfld1")));
					if (desmodify.getText().equals(ProofName)) {			    
						log.logLine(Testname, true, "Delete a proof is unsuccessful");
						//throw new Exception("Delete a proof is unsuccessful");
					} else {
						log.logLine(Testname, false, "Deleting a proof is successful");


					}

				}	


				//Verify User permission
				if ((!Found) && ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTADMIN")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTUSER")))) {
					log.logLine(Testname, false, "Permission Verified: Client Admin and Client User does not have permission to Delete proof");

				} else if ((!Found) && ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSUPER")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSITE")) || 
						(PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDCLIENT")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDUSER")))) {

					log.logLine(Testname, true, "Permission Denied: RRD Super, RRD Site, RRD Client & RRD User Should have access to Delete proof");  			
				}
			} else {
				log.logLine(Testname, true, "Clicking on ChooseActions in the proofs grid is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on ChooseActions in the proofs grid is failed");
			}
		}

		/*
		 */ 	

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

		AdvancedSearch(Testname, "RefenceCode", "S2461", "");

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
			Thread.sleep(2000);
			if (doesElementExist(properties.getProperty("rejectWCommts"), 5)) {
				WebElement rejcommt = driver.findElement(By.xpath(properties.getProperty("rejectWCommts")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", rejcommt);
				PleasewaitDisappear();

				log.logLine(Testname, false, "Clicking on reject with comments..");

				if (doesElementExist2(properties.getProperty("CmmtsTxtfld"), 5)) {
					WebElement rejcommttxt = driver.findElement(By.cssSelector(properties.getProperty("CmmtsTxtfld")));
					rejcommttxt.sendKeys(ProofName+" is rejected with Comments");
					Thread.sleep(2000);
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
			Thread.sleep(2000);
			if (doesElementExist(properties.getProperty("rejectWCommts"), 5)) {
				WebElement rejcommt = driver.findElement(By.xpath(properties.getProperty("rejectWCommts")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", rejcommt);
				PleasewaitDisappear();
				Thread.sleep(5000);

				log.logLine(Testname, false, "Clicking on reject with comments..");

				if (doesElementExist2(properties.getProperty("CmmtsTxtfld"), 5)) {
					WebElement rejcommttxt = driver.findElement(By.cssSelector(properties.getProperty("CmmtsTxtfld")));
					rejcommttxt.sendKeys(ProofName+" is rejected with Comments");
					Thread.sleep(2000);
					log.logLine(Testname, false, "Entered the text before clicking on reject with comments..");  		

					if (doesElementExist2(properties.getProperty("CmmtsRejectbtn"), 5)) {
						WebElement rejectbtn = driver.findElement(By.cssSelector(properties.getProperty("CmmtsRejectbtn")));
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", rejectbtn);
						PleasewaitDisappear();
						Thread.sleep(20000);
						log.logLine(Testname, false, "Clicking on Reject button after entering text in the pop-up");
					}
					Thread.sleep(5000);
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

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		Date date = new Date();
		String todaysDate = dateFormat.format(date);


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
					if (lnk.getText().equals("Rejected")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(5000);
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

		}


		if (doesElementExist2(properties.getProperty("FromDate"), 5)) {	  
			WebElement DteFromfld = driver.findElement(By.cssSelector(properties.getProperty("FromDate")));
			DteFromfld.clear();
			//DteFromfld.click();
			DteFromfld.sendKeys(todaysDate);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entering the from date value in advanced search");
		}


		if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	    
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			Thread.sleep(5000);
			PleasewaitDisappear();
			//((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)", "");
			log.logLine(Testname, false, "Clicking on Search button to view the proofs");
		} else {
			log.logLine(Testname, true, "Clicking on Search button to view the proofs is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Search button to view the proofs is failed");
		}

		String[] Sort = new String[150];
		int length = Sort.length;
		String row="tr";

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Gridvalidation"), 5)) {
			List<WebElement> DataCnt= driver.findElements(By.cssSelector("div[id='proofing-grid'] table tbody tr td+td div"));
			for(int i = 0; i < DataCnt.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='proofing-grid'] table tbody "+row+" td+td div")).getText();
				if(Sort[i].equals(ProofName)){

					if (doesElementExist2("div[id='proofing-grid'] table tbody "+row+" td+td+td+td+td+td+td div button[class='k-button detailsproofinfo']", 5)) {
						WebElement extrainfofld = driver.findElement(By.cssSelector("div[id='proofing-grid'] table tbody "+row+" td+td+td+td+td+td+td div button[class='k-button detailsproofinfo'][class='k-button detailsproofinfo']"));		    		
						action.moveToElement(extrainfofld).perform(); 
						String extrainfomsg = driver.findElement(By.cssSelector("div[id='proofing-grid'] table tbody "+row+" td+td+td+td+td+td+td div button[class='k-button detailsproofinfo'][class='k-button detailsproofinfo']")).getAttribute("data-data");		    		


						log.logLine(Testname, false, "View Proofs extra details is successful");	
						log.logLine(Testname, false, "View Proofs extra details contains: "+extrainfomsg+"");		

					} else {
						log.logLine(Testname, true, "Tooltip box may not be exist");		
					}

					break;
				}
				row = row + "+tr";
			}
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

		AdvancedSearch(Testname, "RefenceCode", "S2461", "");

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
			Thread.sleep(2000);


			if (doesElementExist(properties.getProperty("rejectWUpload"), 5)) {
				WebElement rejcommt = driver.findElement(By.xpath(properties.getProperty("rejectWUpload")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", rejcommt);
				PleasewaitDisappear();

				log.logLine(Testname, false, "Clicking on reject with Upload..");

				if (doesElementExist2(properties.getProperty("rejectWuploadfile"), 5)) {
					WebElement rejcommttxt = driver.findElement(By.cssSelector(properties.getProperty("rejectWuploadfile")));
					rejcommttxt.sendKeys(Rejectupload);
					Thread.sleep(2000);
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

			//((JavascriptExecutor)driver).executeScript("$('Rejectbtn').hover();");
			action.moveToElement(Rejectbtn).build().perform();
			Thread.sleep(2000);

			if (doesElementExist(properties.getProperty("rejectWUpload"), 5)) {
				WebElement rejcommt = driver.findElement(By.xpath(properties.getProperty("rejectWUpload")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", rejcommt);
				PleasewaitDisappear();

				log.logLine(Testname, false, "Clicking on reject with Upload..");

				if (doesElementExist2(properties.getProperty("rejectWuploadfile"), 5)) {
					WebElement rejcommttxt = driver.findElement(By.cssSelector(properties.getProperty("rejectWuploadfile")));
					rejcommttxt.sendKeys(Rejectupload);
					Thread.sleep(2000);
					PleasewaitDisappear();
					log.logLine(Testname, false, "Select the file before uploading..");  		

					if (doesElementExist2(properties.getProperty("UploadRejectbtn"), 5)) {
						WebElement rejectCancelbtn = driver.findElement(By.cssSelector(properties.getProperty("UploadRejectbtn")));
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", rejectCancelbtn);
						Thread.sleep(5000);
						PleasewaitDisappear();
						log.logLine(Testname, false, "Clicking on Reject button after uploading the file");
					}

					Thread.sleep(2000);
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


	public boolean approvestatus(String Testname) throws Exception {

		Thread.sleep(2000);	    
		Actions action = new Actions(driver);

		// Get the proofs based on the status from the dropdown
		AdvancedSearch(Testname, "ProofStatus", "Pending", "");

		AdvancedSearch(Testname, "RefenceCode", "S2461", "");

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

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		Date date = new Date();
		String todaysDate = dateFormat.format(date);

		Thread.sleep(5000);
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
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on the proof status dropdown list in advanced search dialog");

			if (doesElementExist2(properties.getProperty("prfstatusAdsrch"), 5)) {
				List<WebElement> selloca = driver.findElements(By.cssSelector(properties.getProperty("prfstatusAdsrch")));	
				for (WebElement lnk:selloca) {
					if (lnk.getText().equals("Approved")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(5000);
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

		}


		if (doesElementExist2(properties.getProperty("FromDate"), 5)) {	  
			WebElement DteFromfld = driver.findElement(By.cssSelector(properties.getProperty("FromDate")));
			DteFromfld.clear();
			//DteFromfld.click();
			DteFromfld.sendKeys(todaysDate);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entering the from date value in advanced search");
		}



		if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	    
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			Thread.sleep(2000);
			PleasewaitDisappear();
			//((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)", "");
			log.logLine(Testname, false, "Clicking on Search button to view the proofs");
		} else {
			log.logLine(Testname, true, "Clicking on Search button to view the proofs is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Search button to view the proofs is failed");
		}

		String[] Sort = new String[150];
		int length = Sort.length;
		String row="tr";


		if (doesElementExist2(properties.getProperty("Gridvalidation"), 5)) {
			List<WebElement> DataCnt= driver.findElements(By.cssSelector("div[id='proofing-grid'] table tbody tr td+td div"));
			for(int i = 0; i < DataCnt.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='proofing-grid'] table tbody "+row+" td+td div")).getText();
				if(Sort[i].equals(ProofName)){

					if (doesElementExist2("div[id='proofing-grid'] table tbody "+row+" td+td+td+td+td+td+td div button[class='k-button detailsproofinfo']", 5)) {
						WebElement extrainfofld = driver.findElement(By.cssSelector("div[id='proofing-grid'] table tbody "+row+" td+td+td+td+td+td+td div button[class='k-button detailsproofinfo']"));		    		
						action.moveToElement(extrainfofld).perform(); 
						String extrainfomsg = driver.findElement(By.cssSelector("div[id='proofing-grid'] table tbody "+row+" td+td+td+td+td+td+td div button[class='k-button detailsproofinfo']")).getAttribute("data-data");		    		


						log.logLine(Testname, false, "View Proofs extra details is successful");	
						log.logLine(Testname, false, "View Proofs extra details contains: "+extrainfomsg+"");		

					} else {
						log.logLine(Testname, true, "Tooltip box may not be exist");		
					}

					break;
				}
				row = row + "+tr";
			}
		}
		driver.switchTo().defaultContent();
		return true;		

	}

	public boolean proofEnhancements(String RandNo, String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();    	    

		//upload few files for bulk actions
		UploadProof proof = new UploadProof(driver, log);
		proof.Uploadproof(RandNo, Testname, "Multiple");

		Thread.sleep(1000);
		boolean Found=false;
		driver.switchTo().frame("iframeContainer");

		String Referencemumber="Reference #";
		if (doesElementExist2(properties.getProperty("Referencecolumn"), 5)) {
			String refclmn = driver.findElement(By.cssSelector(properties.getProperty("Referencecolumn"))).getText();		    
			if (refclmn.equals(Referencemumber)){
				log.logLine(Testname, false, "Reference Column <<<< "+refclmn+" >>>>  Matches with <<<< "+Referencemumber+" >>>> ");
			}
		} else {
			log.logLine(Testname, true, "Reference Column name not macthes with the requirement");			
		}

		String Statuscolumn="Progress Status";

		if (doesElementExist2(properties.getProperty("Statuscolumn"), 5)) {
			String stsclmn = driver.findElement(By.cssSelector(properties.getProperty("Statuscolumn"))).getText();		    
			if (stsclmn.equals(Statuscolumn)){
				log.logLine(Testname, false, "Status Column <<<< "+stsclmn+" >>>>  Matches with <<<< "+Statuscolumn+" >>>> ");
			}
		} else {
			log.logLine(Testname, true, "Status Column name not macthes with the requirement");			
		}


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
				Thread.sleep(1000);
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


						//Verify User permission
						if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTADMIN")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTUSER"))) {
							log.logLine(Testname, true, "Client Admin and Client User should not have permission to bulk delete proofs");
							throw new Exception("Incorrect Permission: Client Admin and Client User should not have permission to bulk delete proofs");

						} else if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSUPER")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSITE")) || 
								(PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDCLIENT")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDUSER"))) {

							log.logLine(Testname, false, "Permission Verified:RRD Super, RRD Site, RRD Client & RRD User have access to Bulk Delete proofs");

						}					

						Thread.sleep(2000);	
						PleasewaitDisappear();
						Found=true;

						log.logLine(Testname, false, "Clicking on Delete button under bulk actions");

						if (doesElementExist2(properties.getProperty("DelProofpopupmsg"), 5)) {
							String popmsg = driver.findElement(By.cssSelector(properties.getProperty("DelProofpopupmsg"))).getText();
							log.logLine(Testname, false, "Reading the Pop up message as    "+popmsg );						    
						} else {
							log.logLine(Testname, true, "Reading the Pop up message is failed");
						}


						if (doesElementExist2(properties.getProperty("DelProofConf"), 5)) {
							WebElement delpfconf = driver.findElement(By.cssSelector(properties.getProperty("DelProofConf")));
							String msg = driver.findElement(By.cssSelector(properties.getProperty("DelProofConf"))).getText();
							log.logLine(Testname, false, "Reading the button Name as    "+ msg);	
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


				//Verify User permission
				if ((!Found) && ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTADMIN")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTUSER")))) {
					log.logLine(Testname, true, "Client Admin and Client User does not have permission to bulk delete proofs");
					throw new Exception("Incorrect Permission: Client Admin and Client User should not have permission to bulk delete proofs");

				} else if ((!Found) && ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSUPER")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSITE")) || 
						(PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDCLIENT")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDUSER")))) {

					log.logLine(Testname, false, "Permission Verified:RRD Super, RRD Site, RRD Client & RRD User should not have permission to Bulk Delete proofs");
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
							driver.switchTo().defaultContent();
							throw new Exception("Bulk delete - Deleting multiple proofs is unsuccessful");
						} else {
							log.logLine(Testname, false, "Bulk delete - Deleting multiple proofs is successful");
						}
					}
				}else{
					log.logLine(Testname, true, "Bulk delete - Deleting multiple proofs is unsuccessful");
					driver.switchTo().defaultContent();
					throw new Exception("Bulk delete - Deleting multiple proofs is unsuccessful");
				}
			} else {
				log.logLine(Testname, true, "Unable find First Proof record");
			}			
		}



		driver.switchTo().defaultContent();

		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);
		String RandNo1 = Integer.toString(paperID);

		//upload few files for bulk actions	    
		proof.Uploadproof(RandNo1, Testname, "Multiple");

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
						log.logLine(Testname, false, "Clicking on Approve button under bulk actions");

						if (doesElementExist2(properties.getProperty("DelProofpopupmsg"), 5)) {
							String popmsg = driver.findElement(By.cssSelector(properties.getProperty("DelProofpopupmsg"))).getText();
							log.logLine(Testname, false, "Reading the Pop up message as    "+popmsg );						    
						} else {
							log.logLine(Testname, true, "Reading the Pop up message is failed");
						}

						if (doesElementExist2(properties.getProperty("DelProofConf"), 5)) {
							WebElement delpfconf = driver.findElement(By.cssSelector(properties.getProperty("DelProofConf")));
							String msg = driver.findElement(By.cssSelector(properties.getProperty("DelProofConf"))).getText();
							log.logLine(Testname, false, "Reading the button Name as    "+ msg);					    

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

		driver.switchTo().defaultContent();
		AdvancedSearch(Testname, "ProofStatus", "Approved", "");	
		driver.switchTo().frame("iframeContainer");

		if ((driver.getPageSource().contains(ProofName1)) && (driver.getPageSource().contains(ProofName2))) {
			log.logLine(Testname, false, "Verification of bulk proof approve is successful");
		} else {
			log.logLine(Testname, true, "Verification of bulk proof approve is unsuccessful");		
		}


		driver.switchTo().defaultContent();    

		int paperID2 = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);
		String RandNo2 = Integer.toString(paperID2);

		//upload few files for bulk actions	    
		proof.Uploadproof(RandNo2, Testname, "Multiple");

		Thread.sleep(1000);
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

						if (doesElementExist2(properties.getProperty("BulkRejectpopupmsg"), 5)) {
							String blkmsg = driver.findElement(By.cssSelector(properties.getProperty("BulkRejectpopupmsg"))).getText();
							log.logLine(Testname, false, "Reading the pop up message as    "+blkmsg);						    
						} else {
							log.logLine(Testname, true, "Reading the pop up message is failed");
						}


						if (doesElementExist2(properties.getProperty("BulkRejectConf"), 5)) {
							WebElement rejectcmmntsBtn = driver.findElement(By.cssSelector(properties.getProperty("BulkRejectConf")));
							String msg = driver.findElement(By.cssSelector(properties.getProperty("BulkRejectConf"))).getText();
							log.logLine(Testname, false, "Reading the button Name as    "+ msg);	
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


	public void VerifyComments(String Testname) throws Exception {		

		driver.switchTo().defaultContent();
		AdvancedSearch(Testname, "ProofStatus", "Pending", "");		

		driver.switchTo().frame("iframeContainer");
		if (doesElementExist2(properties.getProperty("ChoseAct2"), 5)) {
			log.logLine(Testname, false, "Clicking on Choose Action to add comments..");

			WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("ChoseAct2")));
			log.logLine(Testname, false, "Clicking on AddComments under choose action");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
			Thread.sleep(4000);

			List<WebElement> vwrpts = driver.findElements(By.xpath(properties.getProperty("SelReportLnk")));	
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

			btxt.sendKeys("AutoComments_"+AccNo+Specialchar);		
			log.logLine(Testname, false, "Entering the comments in textarea field with "+"AutoComments_"+AccNo+Specialchar);
		} else {
			log.logLine(Testname, true, "Entering the comments in textarea field is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the comments in textarea field is failed");
		}	


		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("ComtsClrbtn"), 5)) {	    
			WebElement clrbtn = driver.findElement(By.cssSelector(properties.getProperty("ComtsClrbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clrbtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on Clear button to clear the comments");
		} else {
			log.logLine(Testname, true, "Clicking on Clear button to clear the comments is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Clear button to clear the comments is failed");
		}

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("ComtsCancelbtn"), 5)) {	    
			WebElement canclbtn = driver.findElement(By.cssSelector(properties.getProperty("ComtsCancelbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", canclbtn);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on Cancel button to close the comments textbox");		

		} else {
			log.logLine(Testname, true, "Clicking on Cancel button to close the comments textbox is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Cancel button to close the comments textbox is failed");
		}


		//Click on Cancel button
		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("ChoseAct2"), 5)) {
			log.logLine(Testname, false, "Clicking on Choose Action to add comments..");

			WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("ChoseAct2")));
			log.logLine(Testname, false, "Clicking on AddComments under choose action");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);
			Thread.sleep(4000);

			List<WebElement> vwrpts = driver.findElements(By.xpath(properties.getProperty("SelReportLnk")));	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals("Add Comments")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(2000);

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

			btxt.sendKeys("AutoComments_"+AccNo+Specialchar);		
			log.logLine(Testname, false, "Entering the comments in textarea field with "+"AutoComments_"+AccNo+Specialchar);
		} else {
			log.logLine(Testname, true, "Entering the comments in textarea field is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the comments in textarea field is failed");
		}	

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("ComtsCancelbtn"), 5)) {	    
			WebElement canclbtn = driver.findElement(By.cssSelector(properties.getProperty("ComtsCancelbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", canclbtn);
			log.logLine(Testname, false, "Clicking on Cancel button to close the comments textbox");

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
			Thread.sleep(4000);

			List<WebElement> vwrpts = driver.findElements(By.xpath(properties.getProperty("SelReportLnk")));	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals("Add Comments")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(2000);

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
			btxt.sendKeys("AutoComments_"+AccNo+Specialchar);		
			log.logLine(Testname, false, "Entering the comments in textarea field with "+"AutoComments_"+AccNo+Specialchar);
		} else {
			log.logLine(Testname, true, "Entering the comments in textarea field is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the comments in textarea field is failed");
		}	

		Thread.sleep(2000);
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
		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("ChoseAct2"), 5)) {
			log.logLine(Testname, false, "Clicking on Choose Action to add comments..");

			WebElement choseacts = driver.findElement(By.cssSelector(properties.getProperty("ChoseAct2")));
			log.logLine(Testname, false, "Clicking on View Comments under choose action");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choseacts);

			List<WebElement> vwrpts = driver.findElements(By.xpath(properties.getProperty("SelReportLnk")));	
			for (WebElement lnk:vwrpts) {
				if (lnk.getText().equals("View Comments")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);			
					Thread.sleep(2000);

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

			if (Comments.contains("AutoComments_"+AccNo+Specialchar)) {
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


	}
	
	/* Validation Of AdvanceSearch for EmptyResults*/
	public boolean EmptyResultSearch(String Testname, String testdata) throws Exception {
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


				if (doesElementExist2(properties.getProperty("ProofDD"), 5)) {	
					log.logLine(Testname, false, "Clicking on Proof type drop down");

					WebElement quksrch = driver.findElement(By.cssSelector(properties.getProperty("ProofDD")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", quksrch);

					List<WebElement> dayten = driver.findElements(By.xpath(properties.getProperty("Litho")));	
					for (WebElement lnk:dayten) {
						if (lnk.getText().equals(testdata)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							PleasewaitDisappear();
							log.logLine(Testname, false, "Selecting proof type as "+testdata);
							break;
						}
					}

				} else {
					log.logLine(Testname, true, "Clicking on Proof type drop down is failed");
					driver.switchTo().defaultContent();
					throw new Exception("Clicking on Proof type drop down is failed");
				}
				
				if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	    
					WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
					PleasewaitDisappear();
					log.logLine(Testname, false, "Clicking on Search button is Successful");
					} else {
						log.logLine(Testname, true, "Clicking on Search button is UnSuccessful");
						driver.switchTo().defaultContent();
						throw new Exception("Clicking on Search button is UnSuccessful");
					}
				
				String InfoMsg = "No proofs found. Please revise your search criteria.";
				if(doesElementExist2(properties.getProperty("EmptySearchInfoMsg"), 5)){
					String ErrorInfoMsg = driver.findElement(By.cssSelector(properties.getProperty("EmptySearchInfoMsg"))).getText();
					if (ErrorInfoMsg.equals(InfoMsg)) {
						log.logLine(Testname, false, "Information Message is "+ErrorInfoMsg+" is equalto "+InfoMsg+"");
					} else {
					log.logLine(Testname, true, "Information Message is "+ErrorInfoMsg+" is Notequalto "+InfoMsg+"");
					throw new Exception("Information Message is "+ErrorInfoMsg+" is Notequalto "+InfoMsg+"");
					}
				}
				
				return true;
		}
	}
}
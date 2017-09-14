package pivotModules;

import java.io.File;
import java.io.IOException;
import java.util.List;

import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class JobTracking extends Page{

	public JobTracking(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}	
	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}
	
	public static String Final;

	public boolean JobTrackingpage(String RandNo, String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(1000);
		driver.switchTo().defaultContent();

		Thread.sleep(2000);
		WebElement retelm2 = waitForElement(properties.getProperty("selClint1"));

		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button to view the JobTracking");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button to view the JobTracking is failed");			
		}


		if (doesElementExist2(properties.getProperty("JobTracking"), 5)) {	    
			WebElement reportsmnu = driver.findElement(By.cssSelector(properties.getProperty("JobTracking")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", reportsmnu);
			PleasewaitDisappear();
			Thread.sleep(4000);
			log.logLine(Testname, false, "Navigation to JobTracking page successful");
		} else {
			log.logLine(Testname, true, "Clicking on JobTracking menu is failed");
			throw new Exception("Clicking on JobTracking menu is failed");
		}	    

		Thread.sleep(5000);	  
		
		/*driver.switchTo().frame("iframeContainer");
		WebElement retelm3 = waitForElement(properties.getProperty("ResultCnt"));
		driver.switchTo().defaultContent();*/

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

		if (doesElementExist2(properties.getProperty("Okbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Okbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on OK button to view the JobTracking");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the JobTracking is failed");
			throw new Exception("Clicking on OK button to view the JobTracking is failed");
		}

		return true;
	}


	public boolean EnvelopeSearch(String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	    

		Thread.sleep(1000);
		driver.switchTo().frame("iframeContainer");

		Recentactivitybutton(Testname);
		
		Thread.sleep(2000);
		
		HideandShowAccordion(Testname);

		CopyBatchSection(Testname);


		driver.switchTo().defaultContent();



		// JobTrackingpage( "123",  "");

		ClientAppSelection(Testname);

		driver.switchTo().frame("iframeContainer");
		
		Recentactivitybutton(Testname);
		
		Thread.sleep(5000);
		Valideduplicatesearch(Testname);

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("RecentActPge"), 5)) {	    
			WebElement RecentAc = driver.findElement(By.cssSelector(properties.getProperty("RecentActPge")));

			if (RecentAc.getText().equals("Recent Activity"))
				log.logLine(Testname, false, "Search level is set to Recent activity");
			else if (RecentAc.getText().equals("Envelope/Package"))
				log.logLine(Testname, false, "Search level is set to Envelope/Package");
		} else {
			log.logLine(Testname, true, "Search level - Recent Activity is not displayed");
		}


		if (doesElementExist2(properties.getProperty("ActivityArr"), 5)) {	    
			WebElement Arrow = driver.findElement(By.cssSelector(properties.getProperty("ActivityArr")));

			if (Arrow.getAttribute("class").equals("k-icon k-i-arrow-n k-panelbar-collapse")) {
				log.logLine(Testname, false, "Activity/Status section is expanded");

			} else if (Arrow.getAttribute("class").equals("k-icon k-i-arrow-s k-panelbar-expand")){               
				log.logLine(Testname, false, "Activity/Status section is Collapsed");
			}
		} else {
			log.logLine(Testname, true, "Activity/Status section is not displayed in JobTracking page");
		}


		if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	    
			WebElement srchbtn = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked on Search button in JobTracking page");	

		} else {
			log.logLine(Testname, true, "Search button is not displayed in JobTracking page");
			driver.switchTo().defaultContent();
			throw new Exception("Search button is not displayed in JobTracking page");
		}


		if (doesElementExist2(properties.getProperty("ClckType"), 5)) {	    
			WebElement srchbtn = driver.findElement(By.cssSelector(properties.getProperty("ClckType")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked on SearchType dropdown in Search dialog");


			if (doesElementExist2(properties.getProperty("SelType"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelType")));
				for (WebElement lnk:selopts) {

					if (lnk.getText().equals("Advanced")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(2000);
						log.logLine(Testname, false, "Selecting the Type as Advanced from the dropdown..");							
						break;
					}				
				}
			}


		} else {
			log.logLine(Testname, false, "Clicking on SearchType dropdown in Search dialog is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on SearchType dropdown in Search dialog is failed");
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

					if (lnk.getText().equals("Envelope/Package")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Level as Envelope/Package from the dropdown..");							
						break;
					}				
				}
			}


		} else {
			log.logLine(Testname, false, "Clicking on SearchLevel dropdown in Search dialog is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on SearchType dropdown in Search dialog is failed");
		}


		if (doesElementExist2(properties.getProperty("ClckAdvanceEle"), 5)) {	    
			WebElement srchbtn = driver.findElement(By.cssSelector(properties.getProperty("ClckAdvanceEle")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked on Advanced Search options Search dialog");


			if (doesElementExist2(properties.getProperty("SelAdvanceEle"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelAdvanceEle")));
				for (WebElement lnk:selopts) {

					if (lnk.getText().equals("Account #")) {						
						log.logLine(Testname, false, "Account No is displayed in Advanced Search option as expected");
						Thread.sleep(2000);	
					}else if (lnk.getText().equals("Document #")) {
						log.logLine(Testname, false, "Document No is displayed in Advanced Search option as expected");
						Thread.sleep(2000);		
					}else if (lnk.getText().equals("Zip Code")) {						
						log.logLine(Testname, false, "ZipCode is displayed in Advanced Search option as expected");	
						Thread.sleep(2000);	
					} 
				}				
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchbtn);


		} else {
			log.logLine(Testname, true, "Clicking on Advanced Search options in Search dialog is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Advanced Search options in Search dialog is failed");
		}


		log.logLine(Testname, false, "Clicking on Extra field add..");
		if (doesElementExist2(properties.getProperty("addopts"), 5)) {	    
			WebElement addlnk = driver.findElement(By.cssSelector(properties.getProperty("addopts")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", addlnk);
			Thread.sleep(1000);
			log.logLine(Testname, false, "First time clicked on '+' sign to add extra search option");

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", addlnk);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Second time clicked on '+' sign to add extra search option");			

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", addlnk);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Third time clicked on '+' sign to add extra search option");

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", addlnk);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Fourth time clicked on '+' sign to add extra search option");

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", addlnk);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Fifth time clicked on '+' sign to add extra search option");

		} else {
			log.logLine(Testname, true, "Clicking on extra field add is failed");			
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on extra field add is failed");
		}


		log.logLine(Testname, false, "Clicking on Extra field remove..");
		if (doesElementExist2(properties.getProperty("Cancelopts"), 5)) {	    
			WebElement removelnk = driver.findElement(By.cssSelector(properties.getProperty("Cancelopts")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", removelnk);
			log.logLine(Testname, false, "First time clicked on '-' sign to remove extra search option");			
		} else {
			log.logLine(Testname, true, "Clicking on extra field remove1 is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on extra field remove1 is failed");
		}

		if (doesElementExist2(properties.getProperty("Cancelopts"), 5)) {	    
			WebElement removelnk = driver.findElement(By.cssSelector(properties.getProperty("Cancelopts")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", removelnk);
			log.logLine(Testname, false, "Second time clicked on '-' sign to remove extra search option");			
		} else {
			log.logLine(Testname, true, "Clicking on extra field remove2 is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on extra field remove2 is failed");
		}		

		if (doesElementExist2(properties.getProperty("Cancelopts"), 5)) {	    
			WebElement removelnk = driver.findElement(By.cssSelector(properties.getProperty("Cancelopts")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", removelnk);
			log.logLine(Testname, false, "Third time clicked on '-' sign to remove extra search option");			
		} else {
			log.logLine(Testname, true, "Clicking on extra field remove3 is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on extra field remove3 is failed");
		}

		if (doesElementExist2(properties.getProperty("Cancelopts"), 5)) {	    
			WebElement removelnk = driver.findElement(By.cssSelector(properties.getProperty("Cancelopts")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", removelnk);
			log.logLine(Testname, false, "Fourth time clicked on '-' sign to remove extra search option");			
		} else {
			log.logLine(Testname, true, "Clicking on extra field remove4 is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on extra field remove4 is failed");
		}

		if (doesElementExist2(properties.getProperty("Cancelopts"), 5)) {	    
			WebElement removelnk = driver.findElement(By.cssSelector(properties.getProperty("Cancelopts")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", removelnk);
			log.logLine(Testname, false, "Fifth time clicked on '-' sign to remove extra search option");			
		} else {
			log.logLine(Testname, true, "Clicking on extra field remove5 is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on extra field remove5 is failed");
		}


		if (doesElementExist2(properties.getProperty("SrchCancelBtn"), 5)) {	    
			WebElement srchCancelbtn = driver.findElement(By.cssSelector(properties.getProperty("SrchCancelBtn")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchCancelbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked on Cancel button in Search dialog in JobTracking page");			
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button in Search Dialop is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Cancel button in Search Dialop is failed");
		}


		driver.switchTo().defaultContent();	
		return true;		
	}
	
     public boolean selectEnvelop(String Testname) throws Exception{
    	 
    	 InputOutputData test = new InputOutputData();		
 		test.setInputFile(properties.getProperty("InputDatafile"));
 		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	    

 		Thread.sleep(1000);
 		driver.switchTo().frame("iframeContainer");

 		Recentactivitybutton(Testname);
 		
 		Thread.sleep(2000);
 		
 	
 		if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	    
			WebElement srchbtn = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked on Search button in JobTracking page");	

		} else {
			log.logLine(Testname, true, "Search button is not displayed in JobTracking page");
			driver.switchTo().defaultContent();
			throw new Exception("Search button is not displayed in JobTracking page");
		}
 		

 		if (doesElementExist2(properties.getProperty("ClckType"), 5)) {	    
 			WebElement srchbtn = driver.findElement(By.cssSelector(properties.getProperty("ClckType")));

 			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchbtn);
 			PleasewaitDisappear();
 			log.logLine(Testname, false, "Clicked on SearchType dropdown in Search dialog");


 			if (doesElementExist2(properties.getProperty("SelType"), 5)) {
 				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelType")));
 				for (WebElement lnk:selopts) {

 					if (lnk.getText().equals("Advanced")) {
 						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
 						Thread.sleep(2000);
 						log.logLine(Testname, false, "Selecting the Type as Advanced from the dropdown..");							
 						break;
 					}				
 				}
 			}


 		} else {
 			log.logLine(Testname, false, "Clicking on SearchType dropdown in Search dialog is failed");
 			driver.switchTo().defaultContent();
 			throw new Exception("Clicking on SearchType dropdown in Search dialog is failed");
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
 						Thread.sleep(1000);
 						log.logLine(Testname, false, "Selecting the Level as Order from the dropdown..");							
 						break;
 					}				
 				}
 			}


 		} else {
 			log.logLine(Testname, false, "Clicking on SearchLevel dropdown in Search dialog is failed");
 			driver.switchTo().defaultContent();
 			throw new Exception("Clicking on SearchType dropdown in Search dialog is failed");
 		}
 		
 		if (doesElementExist2(properties.getProperty("PerformSearch"), 5)) {	    
			WebElement srchCancelbtn = driver.findElement(By.cssSelector(properties.getProperty("PerformSearch")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchCancelbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked on Search button in Search dialog in JobTracking page");			
		} else {
			log.logLine(Testname, true, "Clicking on Search button in Search Dialop is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Search button in Search Dialog is failed");
		}
 		
 		if (doesElementExist2(properties.getProperty("Refnum"), 5)) {	    
			WebElement Refnum = driver.findElement(By.cssSelector(properties.getProperty("Refnum")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Refnum);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked on Refnum in JobTracking page");			
		} else {
			log.logLine(Testname, true, "Clicking on Refnum is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Refnum is failed");
		}
		
 		if (doesElementExist2(properties.getProperty("Downloadbtn"), 5)) {
 			WebElement downloanButton = driver.findElement(By.cssSelector(properties.getProperty("Downloadbtn")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", downloanButton);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked on download button");			
		} else {
			log.logLine(Testname, true, "Clicking on download is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on download button is failed");
 			
 		}
 		int paperID = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);
		String AccNo = Integer.toString(paperID);
		 		
 		String Name="download" +AccNo;
 		Final=Name+".xls";
 		
 		
		WebElement filename = driver.findElement(By.cssSelector(properties.getProperty("Filename")));
 		filename.sendKeys(Name);
 		
 		if (doesElementExist2(properties.getProperty("Confirm"), 5)) {
 			WebElement ConfirmButton = driver.findElement(By.cssSelector(properties.getProperty("Confirm")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ConfirmButton);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked on confirm button");			
		} else {
			log.logLine(Testname, true, "Clicking on confirm is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on confirm button is failed");
        }
 		
 		
 		if (doesElementExist2(properties.getProperty("OkBtn"), 5)) {
 			WebElement OKButton = driver.findElement(By.cssSelector(properties.getProperty("OkBtn")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", OKButton);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked on OkBtn button");			
		} else {
			log.logLine(Testname, true, "Clicking on OkBtn is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on OkBtn is failed");
        }
 		
 		
 		try {			
	        File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/"+Final);
	        if (fileTemp1.exists()){
	        	fileTemp1.delete();
	        	log.logLine(Testname, false, "Alredy existing file " +Final +" is deleted");
	        	}else{
			    	log.logLine(Testname, false, "File " +Final +" doesn't exists");
		    	}
 		 } catch(Exception e){
		        // if any error occurs
		        e.printStackTrace();
		    }
 		
 		
 		
 		if (doesElementExist2(properties.getProperty("DownloadbtnIcon"), 5)) {
 			WebElement DownloadbtnIcon = driver.findElement(By.cssSelector(properties.getProperty("DownloadbtnIcon")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", DownloadbtnIcon);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked on DownloadbtnIcon button");			
		} else {
			log.logLine(Testname, true, "Clicking on OkDownloadbtnIconBtn is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on DownloadbtnIcon is failed");
        }
 		
 		if (doesElementExist2(properties.getProperty("filedownload"), 5)) {
			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("filedownload")));
			for (WebElement lnk:selopts) {

				if (lnk.getText().contains(Final)) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(10000);
					log.logLine(Testname, false, "File downloaded");		
					break;
				}				
			}
		}

 		try {			
	        File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/" +Final);
	        if (fileTemp1.exists()){
	        	log.logLine(Testname, false, "File " +Final +" downloaded successfully and the file exists");
	        	}else{
			    	log.logLine(Testname, true, "File " +Final +" is no downloaded and the file doesn't exists");
		    	}
 		 } catch(Exception e){
		        // if any error occurs
		        e.printStackTrace();
		    }
				
 		
 		
 		return true;
		
 	}


	public boolean Valideduplicatesearch(String Testname) throws Exception {

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	    
			WebElement srchbtn = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked on Search button in JobTracking page");	

		} else {
			log.logLine(Testname, true, "Search button is not displayed in JobTracking page");
			driver.switchTo().defaultContent();
			throw new Exception("Search button is not displayed in JobTracking page");
		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("ClckType"), 5)) {	    
			WebElement srchbtn = driver.findElement(By.cssSelector(properties.getProperty("ClckType")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked on SearchType dropdown in Search dialog");

			Thread.sleep(5000);
			if (doesElementExist2(properties.getProperty("SelType"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelType")));
				for (WebElement lnk:selopts) {

					if (lnk.getText().equals("Advanced")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(10000);
						log.logLine(Testname, false, "Selecting the Type as Advanced from the dropdown..");							
						break;
					}				
				}
			}


		} else {
			log.logLine(Testname, false, "Clicking on SearchType dropdown in Search dialog is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on SearchType dropdown in Search dialog is failed");
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

					if (lnk.getText().equals("Envelope/Package")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(10000);
						log.logLine(Testname, false, "Selecting the Level as Envelope/Package from the dropdown..");							
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
		if (doesElementExist2(properties.getProperty("ClckAdvanceEle"), 5)) {	    
			WebElement srchbtn = driver.findElement(By.cssSelector(properties.getProperty("ClckAdvanceEle")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked on Advanced Search options Search dialog");
		}

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("SelAdvanceEle"), 5)) {
			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelAdvanceEle")));
			for (WebElement lnk:selopts) {
				if (lnk.getText().equals("Account #")) {						
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(4000);
					log.logLine(Testname, false, "Selecting the Search type as "+lnk );
				}
			}
		}

		log.logLine(Testname, false, "Clicking on Extra field add..");
		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("addopts"), 5)) {	    
			WebElement addlnk = driver.findElement(By.cssSelector(properties.getProperty("addopts")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", addlnk);
			Thread.sleep(2000);
			log.logLine(Testname, false, "First time clicked on '+' sign to add extra search option");
		}
		
		Thread.sleep(5000);
		if (doesElementExist(properties.getProperty("ClckAdvanceEle1"), 5)) {	    
			WebElement srchbtn = driver.findElement(By.xpath(properties.getProperty("ClckAdvanceEle1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchbtn);
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked on Advanced Search options Search dialog");
		}
		
		
		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("SelAdvanceEle1"), 5)) {
			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelAdvanceEle1")));
			for (WebElement lnk:selopts) {
				if (lnk.getText().equals("Account #")) {						
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(5000);
					log.logLine(Testname, false, "Selecting the Search type as "+lnk.getText());
					break;
				}
			}
		}

		Thread.sleep(10000);
		if (doesElementExist2(properties.getProperty("Alertmsg"), 5)) {	    
			String msg = driver.findElement(By.cssSelector(properties.getProperty("Alertmsg"))).getText();		   
			log.logLine(Testname, false, "Reading the pop up message as "+ msg);
		} else {
			log.logLine(Testname, true, "Reading the pop up message is failed");

		}

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("Alertbtn"), 5)) {	    
			WebElement clkadv = driver.findElement(By.cssSelector(properties.getProperty("Alertbtn")));		   
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);		     
			Thread.sleep(5000);
			log.logLine(Testname, false, "Click ok button for pop up message");
		} else {
			log.logLine(Testname, true, "Click ok button for pop up message is failed");

		}

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("SrchClearBtn"), 5)) {	    
			WebElement srchbtn = driver.findElement(By.cssSelector(properties.getProperty("SrchClearBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchbtn);
			PleasewaitDisappear();
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicked on Clear button in Search dialog in JobTracking page");	

		} else {
			log.logLine(Testname, true, "Clicking on Clear button in Search dialog is failed");
		}	
		
		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("SrchCancelBtn"), 5)) {	    
			WebElement srchCancelbtn = driver.findElement(By.cssSelector(properties.getProperty("SrchCancelBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchCancelbtn);
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked on Cancel button in Search dialog in JobTracking page");			
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button in Search Dialop is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Cancel button in Search Dialop is failed");
		}


		return true;
	}

	public boolean CopyBatchSection(String Testname) throws Exception {
		Thread.sleep(4000);
		if (doesElementExist2(properties.getProperty("JobResultTable"), 5)) {	    
			WebElement JbRslttble = driver.findElement(By.cssSelector(properties.getProperty("JobResultTable")));
			JbRslttble.click();
			//((JavascriptExecutor) driver).executeScript("arguments[0].click()", JbRslttble);
			PleasewaitDisappear();
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicked on Processed date hyperlink in JobTracking page");	

		} else {
			log.logLine(Testname, true, "Clicked on Processed date hyperlink in JobTracking page is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicked on Processed date hyperlink in JobTracking page is failed");
		}


		if (doesElementExist2(properties.getProperty("ContentDetail"), 5)) {	    
			WebElement Cntdtl = driver.findElement(By.cssSelector(properties.getProperty("ContentDetail")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Cntdtl);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicked on ContentDetail section in JobTracking page");	

		} else {
			log.logLine(Testname, true, "ContentDetail section is not displayed in JobTracking page");
			driver.switchTo().defaultContent();
			throw new Exception("ContentDetail section is not displayed in JobTracking page");
		}

		if (doesElementExist2(properties.getProperty("BatchTab"), 5)) {	    
			WebElement Btctb = driver.findElement(By.cssSelector(properties.getProperty("BatchTab")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Btctb);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicked on Batches under ContentDetail section in JobTracking page");	

		} else {
			log.logLine(Testname, true, "Batches under ContentDetail section is not displayed in JobTracking page");
			driver.switchTo().defaultContent();
			throw new Exception("Batches under ContentDetail section is not displayed in JobTracking page");
		}

		if (doesElementExist2(properties.getProperty("EyeDetail"), 5)) {	    
			WebElement Eyedtl = driver.findElement(By.cssSelector(properties.getProperty("EyeDetail")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Eyedtl);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicked on EyeDetail button under ContentDetail section in JobTracking page");	

		} else {
			log.logLine(Testname, true, "EyeDetail button under ContentDetail section is not displayed in JobTracking page");
			driver.switchTo().defaultContent();
			//throw new Exception("EyeDetail button under ContentDetail section is not displayed in JobTracking page");
		}

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("ContentBatchResults"), 5)) {	    
			String Cntdtlstxt = driver.findElement(By.cssSelector(properties.getProperty("ContentBatchResults"))).getText();
			Thread.sleep(1000);
			log.logLine(Testname, false, "Text Captured from the grid is---------------"+Cntdtlstxt+"");	

		} else {
			log.logLine(Testname, true, "Failed to Capture the Text form the grid");
			driver.switchTo().defaultContent();
			//throw new Exception("Failed to Capture the Text form the grid");
		}


		return true;
	}

	public boolean Recentactivitybutton(String Testname) throws Exception {
		
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
		
		return true;
	}
	
	
	public boolean HideandShowAccordion(String Testname) throws Exception {

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("DetailsTxt"), 5)) {	    
			String detlstxt = driver.findElement(By.cssSelector(properties.getProperty("DetailsTxt"))).getText();

			if(detlstxt.equals("Details For:")){

				log.logLine(Testname, false,"Text Captured from the grid matches with "+detlstxt+" ");

			}else {
				log.logLine(Testname, true, "Text is not dispalyed in JobTracking page");
			}

		} else {
			log.logLine(Testname, true, "Text is not dispalyed in JobTracking page");
		}


		if (doesElementExist2(properties.getProperty("ActivityStatus"), 5)) {	    
			String Actsts = driver.findElement(By.cssSelector(properties.getProperty("ActivityStatus"))).getText();

			if(Actsts.equals("Activity/Status")){

				log.logLine(Testname, false, "Activity/Status section is  displayed in JobTracking page");

			}else {
				log.logLine(Testname, true, "Activity/Status section is not displayed in JobTracking page");
			}

		} else {
			log.logLine(Testname, true, "Activity/Status section is not displayed in JobTracking page");
		}


		if (doesElementExist2(properties.getProperty("ContentDetail"), 5)) {	    
			String Cntdtls = driver.findElement(By.cssSelector(properties.getProperty("ContentDetail"))).getText();

			if(Cntdtls.equals("Content Details")){

				log.logLine(Testname, false, "Content Details section is  displayed in JobTracking page");

			}else {
				log.logLine(Testname, true, "Content Details section is not displayed in JobTracking page");
			}

		} else {
			log.logLine(Testname, true, "ContentDetail section is not displayed in JobTracking page");
		}



		if (doesElementExist2(properties.getProperty("ArrowButton"), 5)) {	    
			WebElement Fwdarr = driver.findElement(By.cssSelector(properties.getProperty("ArrowButton")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Fwdarr);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on Forward arrow button");			
		} else {
			log.logLine(Testname, true, "Clicking on Forward arrow button is failed");
			throw new Exception("Clicking on Forward arrow button is failed");
		}



		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("DetailsTxt"), 5)) {	    
			String detlstxt = driver.findElement(By.cssSelector(properties.getProperty("DetailsTxt"))).getText();

			if(detlstxt.equals("Details For:")){

				log.logLine(Testname, true,"Text Captured from the grid matches with "+detlstxt+" ");

			}else {
				log.logLine(Testname, false, "Text is not dispalyed in JobTracking page");
			}

		} else {
			log.logLine(Testname, true, "Text is not dispalyed in JobTracking page");
		}


		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("ActivityStatus"), 5)) {	    
			String Actsts = driver.findElement(By.cssSelector(properties.getProperty("ActivityStatus"))).getText();

			if(Actsts.equals("Activity/Status")){

				log.logLine(Testname, true, "Activity/Status section is  displayed in JobTracking page");

			}else {
				log.logLine(Testname, false, "Activity/Status section is not displayed in JobTracking page");
			}

		} else {
			log.logLine(Testname, true, "Activity/Status section is not displayed in JobTracking page");
		}


		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("ContentDetail"), 5)) {	    
			String Cntdtls = driver.findElement(By.cssSelector(properties.getProperty("ContentDetail"))).getText();

			if(Cntdtls.equals("Content Details")){

				log.logLine(Testname, true, "Content Details section is  displayed in JobTracking page");

			}else {
				log.logLine(Testname, false, "Content Details section is not displayed in JobTracking page");
			}

		} else {
			log.logLine(Testname, true, "ContentDetail section is not displayed in JobTracking page");
		}



		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("ArrowButton"), 5)) {	    
			WebElement Bckarrow = driver.findElement(By.cssSelector(properties.getProperty("ArrowButton")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Bckarrow);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on Backward arrow button");			
		} else {
			log.logLine(Testname, true, "Clicking on Backward arrow button is failed");
			throw new Exception("Clicking on Backward arrow button is failed");
		}


		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("DetailsTxt"), 5)) {	    
			String detlstxt = driver.findElement(By.cssSelector(properties.getProperty("DetailsTxt"))).getText();

			if(detlstxt.equals("Details For:")){

				log.logLine(Testname, false,"Text Captured from the grid matches with "+detlstxt+" ");

			}else {
				log.logLine(Testname, true, "Text is not dispalyed in JobTracking page");
			}

		} else {
			log.logLine(Testname, true, "Text is not dispalyed in JobTracking page");
		}


		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("ActivityStatus"), 5)) {	    
			String Actsts = driver.findElement(By.cssSelector(properties.getProperty("ActivityStatus"))).getText();

			if(Actsts.equals("Activity/Status")){

				log.logLine(Testname, false, "Activity/Status section is  displayed in JobTracking page");

			}else {
				log.logLine(Testname, true, "Activity/Status section is not displayed in JobTracking page");
			}

		} else {
			log.logLine(Testname, true, "Activity/Status section is not displayed in JobTracking page");
		}

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("ContentDetail"), 5)) {	    
			String Cntdtls = driver.findElement(By.cssSelector(properties.getProperty("ContentDetail"))).getText();

			if(Cntdtls.equals("Content Details")){

				log.logLine(Testname, false, "Content Details section is  displayed in JobTracking page");

			}else {
				log.logLine(Testname, true, "Content Details section is not displayed in JobTracking page");
			}

		} else {
			log.logLine(Testname, true, "ContentDetail section is not displayed in JobTracking page");
		}

		return true;
	}


	public boolean ClientAppSel2(String Testname)throws Exception {		


		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();


		if (doesElementExist2(properties.getProperty("JobSerachsubmenu"), 5)) {	    
			WebElement Jbsrchsubmnu = driver.findElement(By.cssSelector(properties.getProperty("JobSerachsubmenu")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Jbsrchsubmnu);
			PleasewaitDisappear();
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicked on Job Search Sub menu in JobTracking page");	

		} else {
			log.logLine(Testname, true, "Clicked on Job Search Sub menu in JobTracking page is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicked on Job Search Sub menu in JobTracking page is failed");
		}		


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

		if (doesElementExist2(properties.getProperty("Okbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Okbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on OK button to view the JobTracking");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the JobTracking is failed");
			throw new Exception("Clicking on OK button to view the JobTracking is failed");
		}
		
		selectEnvelop(Testname);

		return true;
	}
	
	public boolean ClientAppSelection(String Testname)throws Exception {


		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();


		if (doesElementExist2(properties.getProperty("JobSerachsubmenu"), 5)) {	    
			WebElement Jbsrchsubmnu = driver.findElement(By.cssSelector(properties.getProperty("JobSerachsubmenu")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Jbsrchsubmnu);
			PleasewaitDisappear();
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicked on Job Search Sub menu in JobTracking page");	

		} else {
			log.logLine(Testname, true, "Clicked on Job Search Sub menu in JobTracking page is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicked on Job Search Sub menu in JobTracking page is failed");
		}		


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

		if (doesElementExist2(properties.getProperty("Okbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Okbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on OK button to view the JobTracking");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the JobTracking is failed");
			throw new Exception("Clicking on OK button to view the JobTracking is failed");
		}

		return true;
	}



	public boolean performSearch(String Testname, String SrchType, String StDate, String EnDate, String DateType, String SrchLevel, String AdvOpts, String AdvSearchValu, String BasicSrcVal, String MoreAdvan) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	    

		Thread.sleep(1000);
		driver.switchTo().frame("iframeContainer");

		if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	    
			WebElement srchbtn = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchbtn);
			PleasewaitDisappear();
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicked on Search button in JobTracking page");	

		} else {
			log.logLine(Testname, true, "Search button is not displayed in JobTracking page");
			driver.switchTo().defaultContent();
			throw new Exception("Search button is not displayed in JobTracking page");
		}


		if (doesElementExist2(properties.getProperty("SrchClearBtn"), 5)) {	    
			WebElement srchbtn = driver.findElement(By.cssSelector(properties.getProperty("SrchClearBtn")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchbtn);
			PleasewaitDisappear();
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicked on Clear button in Search dialog in JobTracking page");	

		} else {
			log.logLine(Testname, true, "Clicking on Clear button in Search dialog is failed");
		}	


		if (!SrchType.equals("")) {
			if (doesElementExist2(properties.getProperty("ClckType"), 5)) {	    
				WebElement srchbtn = driver.findElement(By.cssSelector(properties.getProperty("ClckType")));

				((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchbtn);
				Thread.sleep(1000);
				PleasewaitDisappear();
				log.logLine(Testname, false, "Clicked on SearchType dropdown in Search dialog");


				if (doesElementExist2(properties.getProperty("SelType"), 5)) {
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelType")));
					for (WebElement lnk:selopts) {

						if (lnk.getText().equals(SrchType)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(4000);
							log.logLine(Testname, false, "Selecting the Type as "+SrchType +" from the dropdown..");							
							break;
						}				
					}
				}


			} else {
				log.logLine(Testname, false, "Clicking on SearchType dropdown in Search dialog is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on SearchType dropdown in Search dialog is failed");
			}
		}

		if (!StDate.equals("")) {
			if (doesElementExist2(properties.getProperty("StartDate"), 5)) {	    
				WebElement stdate = driver.findElement(By.cssSelector(properties.getProperty("StartDate")));
				stdate.clear();
				stdate.sendKeys(StDate);				
				log.logLine(Testname, false, "Entered Start Date in Search Dialog");				
			} else {
				log.logLine(Testname, true, "Start Date is not displayed in Search dialog");
				driver.switchTo().defaultContent();
				throw new Exception("Start Date is not displayed in Search dialog");
			}			
		}


		if (!EnDate.equals("")) {
			if (doesElementExist2(properties.getProperty("EndDate"), 5)) {	    
				WebElement Endate = driver.findElement(By.cssSelector(properties.getProperty("EndDate")));
				Endate.clear();
				Endate.sendKeys(EnDate);				
				log.logLine(Testname, false, "Entered End Date in Search Dialog");				
			} else {
				log.logLine(Testname, true, "End Date is not displayed in Search dialog");
				driver.switchTo().defaultContent();
				throw new Exception("End Date is not displayed in Search dialog");
			}			
		}

		if (!DateType.equals("")) {
			if (doesElementExist2(properties.getProperty("ClckDateType"), 5)) {	    
				WebElement srchbtn = driver.findElement(By.cssSelector(properties.getProperty("ClckDateType")));

				((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchbtn);
				PleasewaitDisappear();
				Thread.sleep(1000);
				log.logLine(Testname, false, "Clicked on DateType dropdown in Search dialog");


				if (doesElementExist2(properties.getProperty("SelDateType"), 5)) {
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelDateType")));
					for (WebElement lnk:selopts) {

						if (lnk.getText().equals(DateType)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false, "Selecting the DateType as "+DateType +" from the dropdown..");							
							break;
						}				
					}
				}				

			} else {
				log.logLine(Testname, false, "Clicking on DateType dropdown in Search dialog is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on DateType dropdown in Search dialog is failed");
			}
		}	


		if (!SrchLevel.equals("")) {
			if (doesElementExist2(properties.getProperty("ClckLevel"), 5)) {	    
				WebElement srchbtn = driver.findElement(By.cssSelector(properties.getProperty("ClckLevel")));

				((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchbtn);
				PleasewaitDisappear();
				Thread.sleep(1000);
				log.logLine(Testname, false, "Clicked on Search Level dropdown in Search dialog");


				if (doesElementExist2(properties.getProperty("SelLevel"), 5)) {
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelLevel")));
					for (WebElement lnk:selopts) {

						if (lnk.getText().equals(SrchLevel)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false, "Selecting the Level as "+SrchLevel+" from the dropdown..");							
							break;
						}				
					}
				}


			} else {
				log.logLine(Testname, false, "Clicking on SearchLevel dropdown in Search dialog is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on SearchType dropdown in Search dialog is failed");
			}
		}


		if (!BasicSrcVal.equals("")) {
			if (doesElementExist2(properties.getProperty("ClckBasicSrchVal"), 5)) {	    
				WebElement srchbtn = driver.findElement(By.cssSelector(properties.getProperty("ClckBasicSrchVal")));

				((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchbtn);
				PleasewaitDisappear();
				Thread.sleep(1000);
				log.logLine(Testname, false, "Clicked on Basic search criteria location in Search dialog");				

				if (doesElementExist2(properties.getProperty("SelBasicSrchVal"), 5)) {
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelBasicSrchVal")));
					for (WebElement lnk:selopts) {

						if (lnk.getText().equals(BasicSrcVal)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false, "Selecting the Basic search criteria value as "+BasicSrcVal+" from the dropdown..");
							Thread.sleep(500);
							break;
						}
					}
				}				
			} else {
				log.logLine(Testname, true, "Clicking on Basic search criteria value dropdown in Search dialog is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on Basic search criteria value dropdown in Search dialog is failed");
			}
		}		

		if (MoreAdvan.equalsIgnoreCase("yes")) {
			//MoreAdvancedSearch(Testname);
		} else {

			if (!AdvOpts.equals("")) {
				if (doesElementExist2(properties.getProperty("ClckAdvanceEle"), 5)) {	    
					WebElement srchbtn = driver.findElement(By.cssSelector(properties.getProperty("ClckAdvanceEle")));

					((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchbtn);
					PleasewaitDisappear();
					Thread.sleep(1000);
					log.logLine(Testname, false, "Clicked on Advanced Search options Search dialog");


					if (doesElementExist2(properties.getProperty("SelAdvanceEle"), 5)) {
						List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelAdvanceEle")));
						for (WebElement lnk:selopts) {

							if (lnk.getText().equals(AdvOpts)) {
								((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
								Thread.sleep(1000);
								log.logLine(Testname, false, "Selecting the Advanced options as "+AdvOpts+" from the dropdown..");							
								break;
							}
						}
					}

					if (!AdvSearchValu.equals("")) {
						if (doesElementExist2(properties.getProperty("AdvSrchVal"), 5)) {	    
							WebElement FldVal = driver.findElement(By.cssSelector(properties.getProperty("AdvSrchVal")));
							FldVal.clear();
							FldVal.sendKeys(AdvSearchValu);

							log.logLine(Testname, false, "Entered the advanced search value as "+AdvSearchValu+" in Search dialog in JobTracking page");			

						} else if (doesElementExist2(properties.getProperty("AdvSrchVal1"), 5)) {	    
							WebElement FldVal = driver.findElement(By.cssSelector(properties.getProperty("AdvSrchVal1")));
							FldVal.clear();
							FldVal.sendKeys(AdvSearchValu);

							log.logLine(Testname, false, "Entered the advanced search value as "+AdvSearchValu+" in Search dialog in JobTracking page");
						}else {
							log.logLine(Testname, true, "Entering value in advanced search field is failed");
							driver.switchTo().defaultContent();
							throw new Exception("Entering value in advanced search field is failed");
						}
					}

				} else {
					log.logLine(Testname, true, "Clicking on Advanced Search options in Search dialog is failed");
					driver.switchTo().defaultContent();
					throw new Exception("Clicking on Advanced Search options in Search dialog is failed");
				}
			}
		}


		if (doesElementExist2(properties.getProperty("PerformSearch"), 5)) {	    
			WebElement srchCancelbtn = driver.findElement(By.cssSelector(properties.getProperty("PerformSearch")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchCancelbtn);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked on Search button in Search dialog in JobTracking page");			
		} else {
			log.logLine(Testname, true, "Clicking on Search button in Search Dialop is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Search button in Search Dialop is failed");
		}


		if (doesElementExist2(properties.getProperty("TitleBlue1"), 5)) {	    
			String srchLevelTxt = driver.findElement(By.cssSelector(properties.getProperty("TitleBlue1"))).getText();

			log.logLine(Testname, false, "Search Level displayed as "+srchLevelTxt);			
		} else {
			log.logLine(Testname, true, "Search Level text is not displayed");			
		}


		if (doesElementExist2(properties.getProperty("TitleBlue2"), 5)) {	    
			String Context = driver.findElement(By.cssSelector(properties.getProperty("TitleBlue2"))).getText();

			log.logLine(Testname, false, "Context text displayed as "+Context);			
		} else {
			log.logLine(Testname, true, "Context text is not displayed");			
		}	


		if (doesElementExist2(properties.getProperty("ResultCnt"), 5)) {	    
			String Results = driver.findElement(By.cssSelector(properties.getProperty("ResultCnt"))).getText();

			if (Results.equals("No items to display")) {
				log.logLine(Testname, false, "No items to display for the specified search");
			}else {
				log.logLine(Testname, false, "Items "+Results+" displayed for the specified search");
			}			

		} else if (doesElementExist2(properties.getProperty("ResultCntF"), 5)) {
			log.logLine(Testname, true, "Results count may not be displayed");
			driver.switchTo().defaultContent();
			throw new Exception("Results count may not be displayed");
		}


		driver.switchTo().defaultContent();	
		return true;		
	}


	public void MoreAdvancedSearch(String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		String Field[];
		String FldValue[];

		Field = new String[] {"Account #", "Document #", "Zip Code", "Consumer Id", "Last Name", "First Name"};

		String Value1 = test.readColumnData("AccNum", sheetname);
		String Value2 = test.readColumnData("DocNum", sheetname);
		String Value3 = test.readColumnData("ZipCode", sheetname);
		String Value4 = test.readColumnData("ConsumerId", sheetname);
		String Value5 = test.readColumnData("LastName", sheetname);
		String Value6 = test.readColumnData("FirstName", sheetname);	

		FldValue = new String[] {Value1, Value2, Value3, Value4, Value5, Value6};


		log.logLine(Testname, false, "Clicking on Extra field add..");
		if (doesElementExist2(properties.getProperty("addopts"), 5)) {	    
			WebElement addlnk = driver.findElement(By.cssSelector(properties.getProperty("addopts")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", addlnk);
			log.logLine(Testname, false, "First time clicked on '+' sign to add extra search option");

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", addlnk);
			log.logLine(Testname, false, "Second time clicked on '+' sign to add extra search option");			

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", addlnk);
			log.logLine(Testname, false, "Third time clicked on '+' sign to add extra search option");

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", addlnk);
			log.logLine(Testname, false, "Fourth time clicked on '+' sign to add extra search option");

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", addlnk);
			log.logLine(Testname, false, "Fifth time clicked on '+' sign to add extra search option");

		} else {
			log.logLine(Testname, true, "Clicking on extra field add is failed");			
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on extra field add is failed");
		}


		String Incr="";		
		for (int i=0; i<6; i++) {			

			String SelAdvnce="div[id='advanced-search-criteria'] div+div div"+Incr+" span span span";
			String SelAdvnceVal="div[id='advanced-search-criteria'] div+div div"+Incr+" input[id='adv-search-dropdown-basic-search-criteria-value']";			

			if (doesElementExist2(SelAdvnce, 5)) {	    
				WebElement srchbtn = driver.findElement(By.cssSelector(SelAdvnce));

				((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchbtn);
				PleasewaitDisappear();
				Thread.sleep(1000);
				log.logLine(Testname, false, "Clicked on Advanced Search"+(i+1)+" options Search dialog");				

				if (doesElementExist2(properties.getProperty("SelAdvanceEle"), 5)) {
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelAdvanceEle")));
					for (WebElement lnk:selopts) {

						if (lnk.getText().equals(Field[i])) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false, "Selecting the Advanced options as "+Field[i]+" from the dropdown..");							
							break;
						}
					}
				}
			}


			if (doesElementExist2(SelAdvnceVal, 5)) {	    
				WebElement EditVal = driver.findElement(By.cssSelector(SelAdvnceVal));

				EditVal.clear();
				EditVal.sendKeys(FldValue[i]);

				log.logLine(Testname, false, "Entered the advanced search value as "+FldValue[i]+" in Search dialog in JobTracking page");			
			} else {
				log.logLine(Testname, true, "Entering value in advanced search field"+(i+1)+" is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Entering value in advanced search field"+(i+1)+" is failed");
			}		

			Incr = Incr + "+div";
		}	

	}
}

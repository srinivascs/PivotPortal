package pivotModules;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import launchAuto.Initialization;
import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;


public class UploadProof extends Page{
	
	public UploadProof(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}	
	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}	

	public boolean Uploadproof(String AccNo, String Testname, String MultiFilesupld) throws Exception  {
		
		String ReptName = null;		
		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
	    
	    Thread.sleep(1000);
	    //driver.switchTo().frame("iframeContainer");
	    WebElement retelm2 = waitForElement(properties.getProperty("selClint1"));
	    
	    	    
	    driver.switchTo().defaultContent();
	    if (doesElementExist2(properties.getProperty("Proofs"), 5)) {	    
			WebElement proofsmnu = driver.findElement(By.cssSelector(properties.getProperty("Proofs")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", proofsmnu);
			Thread.sleep(1000);
			PleasewaitDisappear();
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
					if (lnk.getText().equalsIgnoreCase(ClntName)) {
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
						if (lnk.getText().equalsIgnoreCase(ClntName)) {
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
					if (lnk.getText().equalsIgnoreCase(AppName)) {
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
						if (lnk.getText().equalsIgnoreCase(AppName)) {
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
	    
	    
	    //Verify User permission
  		if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTADMIN")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTUSER"))) {					
  		
  			if (doesElementExist2(properties.getProperty("ProofUploadbtn"), 5)) {
  				
  				log.logLine(Testname, true, "Client Admin and Client User should not have permission to access Upload a proof");
  				throw new Exception("Client Admin and Client User should not have permission to access Upload a proof");
  			} else {
  				
  				log.logLine(Testname, false, "Permission Verified: Client Admin and Client User does not have permission to access Upload a proof");				
  				return true;
  			}
  		} else if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSUPER")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSITE")) || 
  				(PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDCLIENT")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDUSER"))) {
  			
  			if (doesElementExist2(properties.getProperty("ProofUploadbtn"), 5)) {
  				
  				log.logLine(Testname, false, "Permission Verified:RRD Super, RRD Site, RRD Client & RRD User have access to Upload a proof");
  								
  			} else {
  				
  				log.logLine(Testname, true, "RRD Super, RRD Site, RRD Client & RRD User does not have access to Upload a proof");
  				throw new Exception("RRD Super, RRD Site, RRD Client & RRD User does not have access to Upload a proof");
  			}
  			
  		}
	    
	    
	    if (doesElementExist2(properties.getProperty("ProofUploadbtn"), 5)) {	    
			WebElement uplbtn = driver.findElement(By.cssSelector(properties.getProperty("ProofUploadbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", uplbtn);
			Thread.sleep(500);
			if ((Initialization.Browser.equalsIgnoreCase("IE")) || (Initialization.Browser.equalsIgnoreCase("ie")) 
					|| (Initialization.Browser.equalsIgnoreCase("InternetExplorer")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer"))) {
					uplbtn.click();
			}
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Upload button in the Proofs");
	    } else {
			log.logLine(Testname, true, "Clicking on Upload button in the Proofs is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Upload button in the Proofs is failed");
	    }    
	    
	    
	    if (MultiFilesupld.equalsIgnoreCase("IllegalFile")) {
	    	IllegalFileupload(Testname, AccNo);
	    	//driver.switchTo().defaultContent();
	    	return true;
	    }
	    
        
	    String Descfld = test.readColumnData("Description", sheetname);	    
	    if (doesElementExist2(properties.getProperty("pfDescription"), 5)) {	    
			WebElement reffld = driver.findElement(By.cssSelector(properties.getProperty("pfDescription")));
			reffld.sendKeys(Descfld+AccNo+1);			
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
	    log.logLine(Testname, false, "Upload PDF file from the path "+upldfilepath);
		    
		if (MultiFilesupld.equalsIgnoreCase("Multiple")) {
			if (doesElementExist2(properties.getProperty("mulfileadd"), 5)) {
		    	WebElement muluplbtn = driver.findElement(By.cssSelector(properties.getProperty("mulfileadd")));
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
	    if (MultiFilesupld.equalsIgnoreCase("Multiple")) {
	    	for (int z=0; z<3; z++) {    	
			    if (doesElementExist2(properties.getProperty("VerifyReportNam"), 5)) {	   
			    	List<WebElement> clntdd = driver.findElements(By.cssSelector(properties.getProperty("VerifyReportNam")));
					for (WebElement btn:clntdd) {
						if (btn.getText().equalsIgnoreCase(ReptName+AccNo+z)) {
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
	    
	    
	    
	    if (doesElementExist2(properties.getProperty("ReportTable"), 5)) {
	    	log.logLine(Testname, false, "Proof PDF file uploading is successful");		    		    	
	    } else if (doesElementExist2(properties.getProperty("ErrTitle"), 5)) {
	    	log.logLine(Testname, true, "Proof PDF file uploading is unsuccessful");
	    	throw new Exception("Proof PDF file uploading is unsuccessful");
	    }
	    
	    driver.switchTo().defaultContent();
	    	    
	    return true;
	}		
	
	public void multipleuploads(String Testname, String AccNo) throws Exception {		
		
		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();		
		
		for (int i=1; i<3; i++) {
			    
		    String RefCdfld = test.readColumnData("Refcode"+Integer.toString(i), sheetname);	    
		    if (doesElementExist2("input[name='["+i+"]_orderNumber']", 5)) {	    
				WebElement reffld = driver.findElement(By.cssSelector("input[name='["+i+"]_orderNumber']"));
				reffld.sendKeys(RefCdfld);
				Thread.sleep(2500);
				log.logLine(Testname, false, "Entering the Ref code into upload new proof popup");
		    }
		    else if (doesElementExist2("input[id='["+i+"].OrderNumber']", 5)) {	    
				WebElement reffld = driver.findElement(By.cssSelector("input[id='["+i+"].OrderNumber']"));
				reffld.sendKeys(RefCdfld);
				Thread.sleep(2500);
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
				Thread.sleep(2500);
				log.logLine(Testname, false, "Entering the Description into upload new proof popup");
		    }
		    
		    else if (doesElementExist2("input[id='["+i+"].Description']", 5)) {	    
				WebElement reffld = driver.findElement(By.cssSelector("input[id='["+i+"].Description']"));
				reffld.sendKeys(RefCdfld);	
				Thread.sleep(2500);
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
				Thread.sleep(2500);
				log.logLine(Testname, false, "Entering the Description into upload new proof popup");
		    }
		    
		    else if (doesElementExist2("input[name='["+i+"].UploadFile']", 5)) {	    
				WebElement upload = driver.findElement(By.cssSelector("input[name='["+i+"].UploadFile']"));
				upload.sendKeys(upldfilepath);	
				Thread.sleep(2500);
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
	
	public void IllegalFileupload(String Testname, String AccNo) throws Exception {
		
		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
		
		if (doesElementExist2(properties.getProperty("CancelUploadbtn"), 5)) {	    
			WebElement uplbtn = driver.findElement(By.cssSelector(properties.getProperty("CancelUploadbtn")));
			uplbtn.click();
			Thread.sleep(500);			
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in the Upload file popup");
	    } else {
			log.logLine(Testname, true, "Clicking on Cancel button in the Upload file popup is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Cancel button in the Upload file popup is failed");
	    }
		
		
		if (doesElementExist2(properties.getProperty("ProofUploadbtn"), 5)) {	    
			WebElement uplbtn = driver.findElement(By.cssSelector(properties.getProperty("ProofUploadbtn")));
			uplbtn.click();
			Thread.sleep(500);
			if ((Initialization.Browser.equalsIgnoreCase("IE")) || (Initialization.Browser.equalsIgnoreCase("ie")) 
					|| (Initialization.Browser.equalsIgnoreCase("InternetExplorer")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer"))) {
					uplbtn.click();
			}
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Upload button in the Proofs page");
	    } else {
			log.logLine(Testname, true, "Clicking on Upload button in the Proofs page is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Upload button in the Proofs page is failed");
	    }			
		
		String upldillgalfile = test.readColumnData("IllegalFile", sheetname);
		if (doesElementExist(properties.getProperty("Fileupload2"), 5)) {
	    	WebElement uplbtn = driver.findElement(By.xpath(properties.getProperty("Fileupload2")));
	    	uplbtn.sendKeys(upldillgalfile);
	    	Thread.sleep(1000);
	    	PleasewaitDisappear();
	    	log.logLine(Testname, false, "Selecting File upload....");
	    } else {
	    	log.logLine(Testname, true, "Selecting File upload is failed");
	    	throw new Exception("Selecting File upload is failed");
	    }	    
	    
		
	    if (doesElementExist2(properties.getProperty("reportUploadbtn"), 5)) {
	    	WebElement uplbtn = driver.findElement(By.cssSelector(properties.getProperty("reportUploadbtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", uplbtn);
	    	PleasewaitDisappear();
	    	log.logLine(Testname, false, "Upload Proofs File....");
	    } else {
	    	log.logLine(Testname, true, "Upload Proofs File is failed");
	    	throw new Exception("Upload Proofs File is failed");
	    }
	    
	    if (doesElementExist2(properties.getProperty("illegalfilemsg"), 5)) {	    	
	    	log.logLine(Testname, false, "Verification of upload illegal file is successful");
	    	
	    	if (doesElementExist2(properties.getProperty("CancelUploadbtn"), 5)) {	    
				WebElement uplbtn = driver.findElement(By.cssSelector(properties.getProperty("CancelUploadbtn")));
				uplbtn.click();
				Thread.sleep(500);			
				PleasewaitDisappear();
				log.logLine(Testname, false, "Clicking on Cancel button in the Upload file popup");
		    }
	    } else {
	    	log.logLine(Testname, true, "Verification of upload illegal file is unsuccessful");
	    	throw new Exception("Verification of upload illegal file is unsuccessful");
	    }	
		
	    if (doesElementExist2(properties.getProperty("ProofUploadbtn"), 5)) {	    
			WebElement uplbtn = driver.findElement(By.cssSelector(properties.getProperty("ProofUploadbtn")));
			uplbtn.click();
			Thread.sleep(500);
			if ((Initialization.Browser.equalsIgnoreCase("IE")) || (Initialization.Browser.equalsIgnoreCase("ie")) 
					|| (Initialization.Browser.equalsIgnoreCase("InternetExplorer")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer"))) {
					uplbtn.click();
			}
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Upload button in the Proofs page");
	    } else {
			log.logLine(Testname, true, "Clicking on Upload button in the Proofs page is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Upload button in the Proofs page is failed");
	    }
		
		String upldfilepath = test.readColumnData("FilePath", sheetname);  
		if ((Initialization.Browser.equalsIgnoreCase("IE")) || (Initialization.Browser.equalsIgnoreCase("ie")) 
				|| (Initialization.Browser.equalsIgnoreCase("InternetExplorer")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer"))) {
			driver.findElement(By.xpath(properties.getProperty("Fileupload2"))).sendKeys(upldfilepath);
		} else {
			driver.findElement(By.xpath(properties.getProperty("Fileupload2"))).sendKeys(upldfilepath);
		}
	    
	    Thread.sleep(1000);					    
	    String RefCdfld = test.readColumnData("Refcode", sheetname);	    
	    if (doesElementExist2(properties.getProperty("RefFld"), 5)) {	    
			WebElement reffld = driver.findElement(By.cssSelector(properties.getProperty("RefFld")));
			reffld.sendKeys(RefCdfld);			
			log.logLine(Testname, false, "Entering the Ref code into upload report popup");
	    } else {
			log.logLine(Testname, true, "Entering the Ref code into upload report popup is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the Ref code into upload report popup is failed");
		}
	    
	    String Descption = test.readColumnData("Description", sheetname);	    
	    if (doesElementExist2(properties.getProperty("pfDescription"), 5)) {	    
			WebElement descp = driver.findElement(By.cssSelector(properties.getProperty("pfDescription")));
			descp.sendKeys(Descption+AccNo);			
			log.logLine(Testname, false, "Entering the Description in Upload new proof popup");
	    } else {
			log.logLine(Testname, true, "Entering the Description in Upload new proof popup is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the Description in Upload new proof popup is failed");
		}
	  
	    Thread.sleep(400);
	    if (doesElementExist2(properties.getProperty("UploadFileDialog"), 5)) {	    	
	    	log.logLine(Testname, false, "Upload information validation is successful");
	    } else {
	    	log.logLine(Testname, true, "Upload information validation is unsuccessful");		
	    	throw new Exception("Upload information validation is unsuccessful");
	    }
	    
	    if (doesElementExist2(properties.getProperty("CancelUploadbtn"), 5)) {	    
			WebElement uplbtn = driver.findElement(By.cssSelector(properties.getProperty("CancelUploadbtn")));
			uplbtn.click();
			Thread.sleep(500);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in the Upload file popup");
	    } else {
			log.logLine(Testname, true, "Clicking on Cancel button in the Upload file popup is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Cancel button in the Upload file popup is failed");
	    }		
		
	}

}

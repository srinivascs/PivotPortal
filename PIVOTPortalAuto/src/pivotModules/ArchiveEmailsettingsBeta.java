package pivotModules;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import launchAuto.Initialization;
import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;


public class ArchiveEmailsettingsBeta extends Page{
	
	public ArchiveEmailsettingsBeta(WebDriver driver, Log log) throws InvalidFormatException, IOException {
 			super(driver, log);
 	} 
	
 	@Override
 	protected void load() {}
 	@Override 	
 	protected void isLoaded() throws Error {}
 
 	public boolean ArchiveClientAppSel(String AccNo, String Testname, String SetWindow) throws Exception { 		
  
		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile")); 		
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
		
		Thread.sleep(1000);
		driver.switchTo().window(SetWindow);
		
		//driver.switchTo().frame("iframeContainer");		
		WebElement retelm2 = waitForElement(properties.getProperty("selClint1"));
		
		/*
		if (!(doesElementExist2(properties.getProperty("prevPivotlnk"), 5))) { 
			if (doesElementExist2(properties.getProperty("newPivotlnk"), 5)) { 
				WebElement newpivt = driver.findElement(By.cssSelector(properties.getProperty("newPivotlnk")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", newpivt);
				
				//Thread.sleep(14000);
			} else if (doesElementExist2(properties.getProperty("newPivotlnk2"), 2)) {
				WebElement newpivt2 = driver.findElement(By.cssSelector(properties.getProperty("newPivotlnk2")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", newpivt2);
				//Thread.sleep(14000);
			}    
			
			
			log.logLine(Testname, false, "Clicking on New Pivot(Beta)!!..");
   
 			// Wait till the page loads all the elements in it.
			//driver.switchTo().frame("iframeContainer");
			WebElement retelm2 = waitForElement(properties.getProperty("selClint"));      
		}
		*/
		
		
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		if (doesElementExist2(properties.getProperty("Archives"), 5)) {    
			WebElement arclnk = driver.findElement(By.cssSelector(properties.getProperty("Archives")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", arclnk);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Navigation to Archives page is successfull");
 		} else {
 			log.logLine(Testname, true, "Clicking on Archives menu is failed");
 			throw new Exception("Clicking on Archives menu is failed");
 		}    
   
	    Thread.sleep(5000);

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
	    	log.logLine(Testname, false, "Clicking on OK button to view the Archives");
	    } else {
	    	log.logLine(Testname, true, "Clicking on OK button to view the Archives is failed");
	    	throw new Exception("Clicking on OK button to view the Archives is failed");
	    }
	    
	    return true;
 	}
 	
 	public boolean EmailsettingsBeta(String AccNo, String Testname) throws Exception {
	
 		if (doesElementExist2(properties.getProperty("Archivesearchmodule"), 5)) {
	    	WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Archivesearchmodule")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);	    	 
	    	 Thread.sleep(2000);
	    	 PleasewaitDisappear();
	    	log.logLine(Testname, false, "Click on Archivesearchmodule is successfull");
	    } else {
	    	log.logLine(Testname, true, "Archivesearchmodule is not displayed");
	    	driver.switchTo().defaultContent();
	    	throw new Exception("Archivesearchmodule is not displayed");
	    }
 		
 		Thread.sleep(5000);
	    driver.switchTo().frame("iframeContainer");	    
	   
	    InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	
	    
	    String TxtField=test.readColumnData("TextField", sheetname);
	    String EmailTo=test.readColumnData("EmailTo", sheetname);
	    String DgtPin=test.readColumnData("DigitPin", sheetname);
	    
	    
	    Thread.sleep(10000);
	     //Clicking on Advance search button	    
	     if (doesElementExist2(properties.getProperty("AdvanSrchBtn"), 5)) {
			 WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("AdvanSrchBtn")));
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			 log.logLine(Testname, false, "Click on Advance Search is successfull");
		 } else {
			 log.logLine(Testname, true, "Click on Advance Search is failed");
			 driver.switchTo().defaultContent();
			 throw new Exception("Click on Advance Search is failed");
		 }

		 
	     if (doesElementExist2(properties.getProperty("Alldts"), 5)) {
			 WebElement btn = driver.findElement(By.cssSelector(properties.getProperty("Alldts")));
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
			 log.logLine(Testname, false, "Click on All Date Checkbox is Successfull");
		 } else {
			 log.logLine(Testname, true, "Click on All Date Checkbox is Failed");
			 driver.switchTo().defaultContent();
			 throw new Exception("Click on All Date Checkbox is Failed");
		 }
		 	 
		 Thread.sleep(3000);
		 Actions action=new Actions(driver);
		 if (doesElementExist2(properties.getProperty("Fieldbutton"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("Fieldbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(1000);
			
			log.logLine(Testname, false, "Clicking on Seacrh criteria Field drop down list in advance search");
			
			if (doesElementExist2(properties.getProperty("SelFieltOpt"), 5)) {
				
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelFieltOpt")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("CEDULA")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						log.logLine(Testname, false, "Selecting the CEDULA Field option from the dropdown");
						break;
					}						
				}
			} else {				
				log.logLine(Testname, true, "Selecting the field option is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Selecting the field option is failed");				
			}		
			
		 }else {
			 log.logLine(Testname, true, "Clicking on the Field is failed");
			 driver.switchTo().defaultContent();
			 throw new Exception("Clicking on the Field is failed");
		 }
		 
			
		 if (doesElementExist2(properties.getProperty("Operatorbutton"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("Operatorbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(1000);
			
			log.logLine(Testname, false, "Clicking on Seacrh criteria Operator drop down list in advance search");
			
			if (doesElementExist2(properties.getProperty("SelOpertOpt"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelOpertOpt")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals("Equals")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						log.logLine(Testname, false, "Selecting the Equals operator option from the dropdown");
						break;
					}						
				}
			} else {				
				log.logLine(Testname, true, "Selecting the operator option is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Selecting the operator option is failed");				
			}		
		}else {				
			log.logLine(Testname, true, "Clicking on the operator is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on the operator is failed");				
		}
		 
		
		 
		Thread.sleep(1000);
		if (doesElementExist2(properties.getProperty("Txtfld"), 5)) {	  
			WebElement Txt = driver.findElement(By.cssSelector(properties.getProperty("Txtfld")));
			Txt.sendKeys(Initialization.EightDig1);
			log.logLine(Testname, false, "Entered Value "+Initialization.EightDig1+" in text field ");			
		}else {
			log.logLine(Testname, true,"Unable to enter the text in text field");
			throw new Exception("Unable to  enter the text in text field");
		}
		 
			 
		if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	 
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			Thread.sleep(2000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Search button is successfull");
		} else {
			log.logLine(Testname, true, "Clicking on Search button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Search button is failed");
		}
		
		
		if (doesElementExist2(properties.getProperty("Chooseactn"), 5)) {
			 WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Chooseactn")));
			 
			 //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnsrch);
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			 
			 Thread.sleep(2000);
			 log.logLine(Testname, false, "Clicking on Choose action button is successfull");
			} else {
				log.logLine(Testname, true, "Clicking on Choose action button is failed");
				driver.switchTo().defaultContent();
				throw new Exception("Clicking on Choose action button is failed");
				}
		
 		 if(doesElementExist2(properties.getProperty("Emaildoc"), 5)){
 			 List<WebElement> emailbtn = driver.findElements(By.cssSelector(properties.getProperty("Emaildoc")));
 			 for (WebElement lnk:emailbtn) {
 				 if (lnk.getText().equals("Email Document")){
 					 action.click(lnk).perform();
 					 log.logLine(Testname, false, "Selecting Email Document option under choose action is successfull..");
 					 break;
 					 }
 				 }
 			 }
 		 
 		 if (doesElementExist2(properties.getProperty("EmailTo"), 5)) {
 			 WebElement Emailfrm = driver.findElement(By.cssSelector(properties.getProperty("EmailTo")));
 			 Emailfrm.clear();
 			 Emailfrm.sendKeys(EmailTo);
 			 log.logLine(Testname, false, "Entering The Email-Id "+EmailTo+"in EmailTo field");
 			 }

		if (doesElementExist2(properties.getProperty("DigitPin"), 5)) {
		    WebElement Emailsub = driver.findElement(By.cssSelector(properties.getProperty("DigitPin")));
		    Emailsub.clear();
		    Emailsub.sendKeys(DgtPin);
		    log.logLine(Testname, false, "Entering The DigitalPin"+DgtPin+" in DigitalPin field");
		    }
		 
		if (doesElementExist2(properties.getProperty("Send"), 5)) {
			 WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Send")));
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			 PleasewaitDisappear();
			 log.logLine(Testname, false, "Clicking on Send button is successfull");
			} else {
				log.logLine(Testname, true, "Clicking on Send action button is failed");
				throw new Exception("Clicking on Choose Send button is failed");
				}
		
		if (doesElementExist2(properties.getProperty("AlertOk"), 5)) {
			 WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("AlertOk")));
			 ((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			 PleasewaitDisappear();
			 log.logLine(Testname, false, "Clicking AlertOk button is successfull");
			} else {
				log.logLine(Testname, true, "Clicking AlertOk action button is failed");
				throw new Exception("Clicking AlertOk Send button is failed");
			}
		
		Thread.sleep(60000);
		Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        try {
            Session session = Session.getInstance(props, null);
            Store store = session.getStore();            
            store.connect("imap.gmail.com", "automationpivot@gmail.com", "miracle@123");
            Folder inbox = store.getFolder("INBOX");
            driver.navigate().refresh();
            inbox.open(Folder.READ_ONLY);
            Message msg = inbox.getMessage(inbox.getMessageCount());
            Address[] in = msg.getFrom();
            for (Address address : in) {
            	Thread.sleep(1000);
                System.out.println("FROM:" + address.toString());
                if (address.toString().equals(AdminConfig.EmailFrm)){
                	log.logLine(Testname, false,"The "+address.toString()+" From Inbox Matches with the "+AdminConfig.EmailFrm+"");	
                	break;
                }else {
    				log.logLine(Testname, true, "From Address is not readed");
    				}
            }
            Multipart mp = (Multipart) msg.getContent();
            BodyPart bp = mp.getBodyPart(0);
            Thread.sleep(1000);
            System.out.println("SUBJECT:" + msg.getSubject());
            if (msg.getSubject().equals(AdminConfig.EmailSub)){
            	log.logLine(Testname, false,"The "+msg.getSubject()+" From Inbox Matches with the "+AdminConfig.EmailSub+" ");	
            	}else {
    				log.logLine(Testname, true, "Subject Line is not readed");
    				}
            
            Thread.sleep(1000);
            System.out.println("CONTENT:" + bp.getContent());
            if (bp.getContent().toString().contains(AdminConfig.EmailBdy)){
            	log.logLine(Testname, false,"The "+bp.getContent()+" From Inbox Matches with the "+AdminConfig.EmailBdy+"");	
            }else {
				log.logLine(Testname, true, "Content is not readed");
				}
        } catch (Exception mex) {
            mex.printStackTrace();
        }       
        
        
		 return true;
	}
}
 	
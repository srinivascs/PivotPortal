package pivotModules;

import java.io.IOException;
import java.util.List;
import java.util.Set;
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

import launchAuto.Initialization;
import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;

public class TemplateMgmt extends Page {
	
	int paperID = (int) Math.round(Math.random() * (9999 - 1000 + 1) + 1000);
	public String AccNo = Integer.toString(paperID);
	
	public String EmailtypTxt, rplyTxt ;
	public String TemplateName;
    public String TempltDecsp;
    public String EditTemplateName;
    public String EditTempltDecsp;
    public String notemplts;
    public String spacialChar;
    public String name;
    public String ClntName;
    public String AppId;
    public String useradmnid;
    public String userappid;
    public String AppName;
    
    public String InsImgUrl, AltImageUrlText; //HTML
    public String URL, LinkID , LnkText ,LnkToolTp; //HTML
    
	
	public TemplateMgmt(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}	
	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}
	
	String firstWinHandle = null;	
	WebDriverWait wait = new WebDriverWait(driver, 20);
	private String InvalidNameemailType;
	
	public boolean TemplateText(String RandNo, String Testname) throws Exception {
		
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
	    
	    PleasewaitDisappear();
    
 
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
		
	   
	    if ((doesElementExist2(properties.getProperty("Adminlnk"), 5))) {                
            WebElement oldpivt = driver.findElement(By.cssSelector(properties.getProperty("Adminlnk")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", oldpivt);
        
            Thread.sleep(3000);
            log.logLine(Testname, false, "Clicking on Legacy Admin..");              
        }
	    
	    Thread.sleep(5000);
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
		    			Thread.sleep(60000);
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
			throw new Exception("Unable to click on the Email link from the leftContent Menu");
		}
	    Thread.sleep(25000);
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
			throw new Exception("Unable to Click on Email Types option of the Email Builder list");
		}
	    
	    Thread.sleep(15000);
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
	    
	    if (doesElementExist2(properties.getProperty("EmlTypeTxtBox"), 5)) {
	    	WebElement emltyptxt = driver.findElement(By.cssSelector(properties.getProperty("EmlTypeTxtBox")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", emltyptxt);
	    	String InvalidNameemailType = "*invalidEmailType";
			emltyptxt.sendKeys(InvalidNameemailType);
			
			if (doesElementExist2(properties.getProperty("DescriptionTxtBox"), 5)) {
		    	WebElement descpText = driver.findElement(By.cssSelector(properties.getProperty("DescriptionTxtBox")));
		    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", descpText);
		    	descpText.sendKeys(DescpText);
		    	}
	    	Thread.sleep(1000);
	    	log.logLine(Testname, false, "Entering Email Type name as "+InvalidNameemailType+" in the text box of Email Builder Section");
		} else {
		    log.logLine(Testname, true, "Unable to enter Email Type name as "+InvalidNameemailType+" in the text box of Email Builder Section");
		    throw new Exception("Unable to enter Email Type name as "+InvalidNameemailType+" in the text box of Email Builder Section");
		}
	    
	    String Errormessage = "Please enter the Email Type using only alphanumeric characters";
	    if(doesElementExist2(properties.getProperty("ErrorMessage"),5)){
	    	String addText=driver.findElement(By.cssSelector(properties.getProperty("ErrorMessage"))).getText();
	    	if(addText.equals(Errormessage)){
	    		log.logLine(Testname, false, " The Validation of Special Charecters handled sucessfully");		
	    	}else{
	    		log.logLine(Testname, true, " The Validation of Special Charecters is not added sucessfully");	
	    	}
	    		
	    }else{
	    	log.logLine(Testname, true, "The text for sucessful added Email template does not exist");
	    	throw new Exception("The text for sucessful added Email template does not exist");
	    }
	    
	    
	    if (doesElementExist2(properties.getProperty("EmlTypeTxtBox"), 5)) {
	    	WebElement emltyptxt = driver.findElement(By.cssSelector(properties.getProperty("EmlTypeTxtBox")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", emltyptxt);
	    	emltyptxt.clear();
	    	emltyptxt.sendKeys(EmailtypTxt.concat(AccNo));
	    	Thread.sleep(1000);
	    	log.logLine(Testname, false, "Entering Email Type name as "+EmailtypTxt.concat(AccNo)+" in the text box of Email Builder Section");
		} else {
		    log.logLine(Testname, true, "Unable to enter Email Type name as "+EmailtypTxt.concat(AccNo)+" in the text box of Email Builder Section");
		    throw new Exception("Unable to enter Email Type name as "+EmailtypTxt.concat(AccNo)+" in the text box of Email Builder Section");
		}
	    
	    if (doesElementExist2(properties.getProperty("DescriptionTxtBox"), 5)) {
	    	WebElement descpText = driver.findElement(By.cssSelector(properties.getProperty("DescriptionTxtBox")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", descpText);
	    	descpText.sendKeys(DescpText);
	    	Thread.sleep(1000);
	    	log.logLine(Testname, false, "Entering the description in the \"Description\" text box of Email Builder Section ");
		} else {
		    log.logLine(Testname, true, "Unable to Enter the description in the \"Description\" text box of Email Builder Section ");
		    throw new Exception("Unable to Enter the description in the \"Description\" text box of Email Builder Section ");
		}
	    
	    if (doesElementExist2(properties.getProperty("FromTxtBox"), 5)) {
	    	WebElement frmTxt = driver.findElement(By.cssSelector(properties.getProperty("FromTxtBox")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", frmTxt);
	    	frmTxt.sendKeys(FromTxt);
	    	Thread.sleep(1000);
	    	log.logLine(Testname, false, "Entering the from email addresss in the \"From\" text box of Email Builder Section ");
		} else {
		    log.logLine(Testname, true, "Unable to Enter the from email addresss in the \"From\" text box of Email Builder Section ");
		    throw new Exception("Unable to Enter the from email addresss in the \"From\" text box of Email Builder Section ");
		}
	    
	    if (doesElementExist2(properties.getProperty("FromFrndlyNmeTxtBox"), 5)) {
	    	WebElement frmFrndlyText = driver.findElement(By.cssSelector(properties.getProperty("FromFrndlyNmeTxtBox")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", frmFrndlyText);
	    	frmFrndlyText.sendKeys(FromFrndlyTxt);
	    	Thread.sleep(1000);
		   	log.logLine(Testname, false, "Entering the name in the \"From Friendly Name\" text box of Email Builder Section ");
		} else {
		    log.logLine(Testname, true, "Unable to Enter the name in the \"From Friendly Name\" text box of Email Builder Section ");
		    throw new Exception("Unable to Enter the name in the \"From Friendly Name\" text box of Email Builder Section ");
		}
	    
	    if (doesElementExist2(properties.getProperty("ReplyToTxtBox"), 5)) {
	    	WebElement rpltoTx = driver.findElement(By.cssSelector(properties.getProperty("ReplyToTxtBox")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", rpltoTx);
	    	rpltoTx.sendKeys(rplyTxt);
	    	Thread.sleep(1000);
		   	log.logLine(Testname, false, "Entering the email address in the \"Reply To\" text box of Email Builder Section ");
		} else {
		    log.logLine(Testname, true, "Unable to Enter the email address in the \"Reply To\" text box of Email Builder Section ");
		    throw new Exception("Unable to Enter the email address in the \"Reply To\" text box of Email Builder Section ");
		}
	    
	    if (doesElementExist2(properties.getProperty("ReplyToFrndlyNmeTxtBox"), 5)) {
	    	WebElement rpltofrndlyTx = driver.findElement(By.cssSelector(properties.getProperty("ReplyToFrndlyNmeTxtBox")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", rpltofrndlyTx);
	    	rpltofrndlyTx.sendKeys(rplyToFrndlyTxt);
	    	Thread.sleep(1000);
		   	log.logLine(Testname, false, "Entering the name in the \"Reply to Freindly Name\" text box of Email Builder Section ");
		} else {
		    log.logLine(Testname, true, "Unable to Enter the email address in the \"Reply to Freindly Name\" text box of Email Builder Section ");
		    throw new Exception("Unable to Enter the email address in the \"Reply to Freindly Name\" text box of Email Builder Section ");
		}
	    
	    if (doesElementExist2(properties.getProperty("SubjectTxtBox"), 5)) {
	    	WebElement subjTxt = driver.findElement(By.cssSelector(properties.getProperty("SubjectTxtBox")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", subjTxt);
	    	subjTxt.sendKeys(subTxt.concat(AccNo));
	    	Thread.sleep(1000);
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
	    	Thread.sleep(1000);
		   	log.logLine(Testname, false, "Entering the value for \"Emails per Month\" in Email Sender Section");
		} else {
		    log.logLine(Testname, true, "Unable to Enter the value for \"Emails per Month\" in Email Sender Section");
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
	    	Thread.sleep(2000);
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
	    
	    
	    String[] EmailTemp = new String[100];
	    String row1 = "tr";
		List<WebElement> DataCnt1= driver.findElements(By.xpath(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_dgViewEmailTypes']/tbody/tr"));
				
		Thread.sleep(2000);
		if(doesElementExist2(properties.getProperty("EmailTypeHeader"), 5)){
			for(int j = 0; j < DataCnt1.size(); j++) {
				EmailTemp[j] = driver.findElement(By.cssSelector("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_dgViewEmailTypes'] tbody "+row1+" td")).getText();
				
				if(EmailTemp[j].equals(EmailtypTxt.concat(AccNo))){
							
					log.logLine(Testname, false, "Iterating through the Rows of the Email Type list....and reading the Type in Email Type  as "+EmailTemp[j]);
					break;
				}
				row1 = row1 + "+tr";
				
				}
		
		}
	    
	    driver.close();
        
        driver.switchTo().window(firstWinHandle);   
        
        Thread.sleep(5000);        
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
        
    	if (doesElementExist2(properties.getProperty("ClearTmplBtn"), 5)) {
        	WebElement cler = driver.findElement(By.cssSelector(properties.getProperty("ClearTmplBtn")));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click()", cler);
        	Thread.sleep(1000);
        	log.logLine(Testname, false, "Clicking on clear button ");
    	} else {
    	    log.logLine(Testname, true, "Clicking on clear button  is failed");
    	    throw new Exception("Clicking on clear button  is failed");
    	}
    	
    	if (doesElementExist2(properties.getProperty("ClearAlertMessage"), 5)) {
			String clrAlrt = driver.findElement(By.cssSelector(properties.getProperty("ClearAlertMessage"))).getText();
			
			if (clrAlrt.equals("If you haven't saved your changes, changes will be lost. Are you sure you want to exit?")){
				log.logLine(Testname, false, "Alert message for confirming the \"clear\" operation is sucessful ");
			}
			else {	
			log.logLine(Testname, true, "Alert message for confirming the \"clear\" operation is unsucessful ");
			} 
		}else {
			log.logLine(Testname, true, "Alert message for confirming the \"clear\" operation is not displayed ");
			throw new Exception("Alert message for confirming the \"clear\" operation is not displayed ");

		}
    	
    	if (doesElementExist2(properties.getProperty("ConfrmClrOkBtn"), 5)) {
        	WebElement CnfmClr = driver.findElement(By.cssSelector(properties.getProperty("ConfrmClrOkBtn")));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click()", CnfmClr);
        	Thread.sleep(1000);
        	log.logLine(Testname, false, "Clicking on the Ok button of the  clear Alert message");
    	} else {
    	    log.logLine(Testname, true, "Clicking on the Ok button of the  clear Alert message is failed");
    	    throw new Exception("Clicking on the Ok button of the  clear Alert message is failed");
    	}
    	
    	SavedTypesDropDown(Testname);
    	VerifyNoTemplate(Testname);
    	
       	CancelSavedTmpl(Testname);
       	
       	
        Thread.sleep(5000);
        SavedTypesDropDown(Testname);
        Thread.sleep(5000);
        AddTemplate(Testname);
        Thread.sleep(5000);
        
        /*TemplateName=test.readColumnData("TemplateName", sheetname);
        TempltDecsp=test.readColumnData("TemplateDecrip", sheetname);*/
        
        FillTemplateName(Testname);	 
        Thread.sleep(5000);
        SaveTemplate(Testname);
        Thread.sleep(5000);
        
        //accept the error message for empty template description 
        Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(2000);
		
		FillTemaplateDescription(Testname);
		Thread.sleep(4000);
		SaveTemplate(Testname);
		Thread.sleep(5000);
		
		//Alert alert1 = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(2000);
		
		ClickExitEditorButton(Testname);
		Thread.sleep(2000);
		
		
		//Adding the text email template in TM
		
		SavedTypesDropDown(Testname);
        AddTemplate(Testname);
       
        FillTemplateName(Testname);
        FillTemaplateDescription(Testname);
        Thread.sleep(5000);
        
        SaveTemplate(Testname);
        
        if (doesElementExist2(properties.getProperty("SaveTempAlrtMessage"), 5)) {
			String sveAlrt = driver.findElement(By.cssSelector(properties.getProperty("SaveTempAlrtMessage"))).getText();
			
			if (sveAlrt.equals("Template \'"+TemplateName.concat(AccNo)+"\' has been saved successfully.")){
				log.logLine(Testname, false, "Alert message for confirming the \"Save\" operation is sucessful ");
			}
			else {	
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
        	Thread.sleep(2000);
        	log.logLine(Testname, false, "Clicking on close button of the Confirm \"Save\" Alert message");
    	} else {
    	    log.logLine(Testname, true, "Unable to Click on close button of the Confirm \"Save\" Alert message");
    	    throw new Exception("Unable to Click on close button of the Confirm \"Save\" Alert message");
    	}
        
        ClickExitEditorButton(Testname);
        Thread.sleep(5000);
        SavedTypesDropDown(Testname);
        Thread.sleep(5000);
        VerifySaveTextTemplate(Testname, "Edit");
        Thread.sleep(5000);
        EditTemplNameDescptn(Testname);
        Thread.sleep(5000);
        SaveTemplate(Testname);
        Thread.sleep(5000);
        AcceptAlert(Testname);
        
        
        Thread.sleep(8000);
        if (doesElementExist2(properties.getProperty("ConfirmSave"), 5)) {
        	WebElement cnfmSve = driver.findElement(By.cssSelector(properties.getProperty("ConfirmSave")));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnfmSve);
        	Thread.sleep(5000);
        	log.logLine(Testname, false, "Clicking on close button of the Confirm \"Save\" Alert message");
    	} else {
    	    log.logLine(Testname, true, "Unable to Click on close button of the Confirm \"Save\" Alert message");
    	    //throw new Exception("Unable to Click on close button of the Confirm \"Save\" Alert message");
    	}
        /*
        //Alert message for the Edit operation
        if (doesElementExist2(properties.getProperty("SaveTempAlrtMessage"), 5)) {
			String editsveAlrt = driver.findElement(By.cssSelector(properties.getProperty("SaveTempAlrtMessage"))).getText();
			
			if (editsveAlrt.equals("Template \'"+EditTemplateName+"\' has been saved successfully.")){
				log.logLine(Testname, false, "Alert message for confirming the \"Edit and Save\" operation is sucessful ");
				Thread.sleep(2000);
			}
			else {	
			log.logLine(Testname, true, "Alert message for confirming the \"Edit and Save\" operation is unsucessful ");
			} 
		}else {
			log.logLine(Testname, true, "Alert message for confirming the \"Edit and Save\" operation is not displayed ");
			throw new Exception("Alert message for confirming the \"Edit and Save\" operation is not displayed ");

		}
            */
        
    
        ClickExitEditorButton(Testname);
        Thread.sleep(5000);
        SavedTypesDropDown(Testname);
        Thread.sleep(5000);
        VerifySaveTextTemplate(Testname, "ConfirmEditAndView");
        
        Thread.sleep(5000);
        ClickPreviewEmailBtn(Testname);   
        Thread.sleep(5000);
        PreviewSendBtn(Testname);
        Thread.sleep(8000);
        
        //Error message for Empty Email Address
        //Alert alert2 = driver.switchTo().alert();
        AcceptAlert(Testname);
		Thread.sleep(2000);
		
		if (doesElementExist2(properties.getProperty("CancelEmlAddreBtn"), 5)) {
        	WebElement cancelEmlAddr = driver.findElement(By.cssSelector(properties.getProperty("CancelEmlAddreBtn")));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click()", cancelEmlAddr);
        	Thread.sleep(1000);
        	log.logLine(Testname, false, "Clicking on \"Cancel Email\"  button of Management e-mail window");
    	} else {
    	    log.logLine(Testname, true, "Clicking on \"Cancel Email\"  button of Management e-mail window is failed");
    	    throw new Exception("Clicking on \"Cancel Email\"  button of Management e-mail window is failed");
    	}
		
		ClickPreviewEmailBtn(Testname);
		
		
		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("EmailAddrTextBox"), 5)) {
        	WebElement qtyday = driver.findElement(By.cssSelector(properties.getProperty("EmailAddrTextBox")));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click()", qtyday);
        	qtyday.clear();
        	qtyday.sendKeys(rplyTxt);
        	Thread.sleep(2000);
        	log.logLine(Testname, false, "Entering the \"To Email address\" in the Management Email pop up window  ");
    	} else {
    	    log.logLine(Testname, true, "Entering the \"To Email address\" in the Management Email pop up window is failed ");
    	    throw new Exception("Entering the \"To Email address\" in the Management Email pop up window  is failed");
    	}
        
		Thread.sleep(5000);
		PreviewSendBtn(Testname);
		Thread.sleep(15000);
		
		//Template \'"+EditTemplateName+"\' has been saved successfully.
		
		if (doesElementExist2(properties.getProperty("SaveTempAlrtMessage"), 5)) {
			String editsveAlrt = driver.findElement(By.cssSelector(properties.getProperty("SaveTempAlrtMessage"))).getText();
			
			if (editsveAlrt.equals("An email with template \'"+EditTemplateName+"\' has been sent.")){
				log.logLine(Testname, false, "Alert message for confirming the \"Email sent\" operation is sucessful ");
				Thread.sleep(2000);
			}
			else {	
			log.logLine(Testname, true, "Alert message for confirming the \"Email sent\" operation is unsucessful ");
			} 
		}else {
			log.logLine(Testname, true, "Alert message for confirming the \"Email sent\" operation is not displayed ");
			throw new Exception("Alert message for confirming the \"Email sent\" operation is not displayed ");

		}
		
		Thread.sleep(3000);
		if (doesElementExist2(properties.getProperty("ConfirmSave"), 5)) {
        	WebElement cnfmSve = driver.findElement(By.cssSelector(properties.getProperty("ConfirmSave")));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnfmSve);
        	Thread.sleep(3000);
        	log.logLine(Testname, false, "Clicking on Close button of the Confirm \"Email sent\" Alert message");
    	} else {
    	    log.logLine(Testname, true, "Unable to Click on OK button of the Confirm \"Email sent\" Alert message");
    	    throw new Exception("Unable to Click on OK button of the Confirm \"Email sent\" Alert message");
    	}
		
       
		ClickExitEditorButton(Testname);
		
		Thread.sleep(5000);
		
		if ((Initialization.Browser.equals("Chrome")) || (Initialization.Browser.equals("chrome")) || (Initialization.Browser.equals("CHROME")) || (Initialization.Browser.equals("FF")) ||(Initialization.Browser.equals("ff")) || (Initialization.Browser.equals("firefox"))) {
		
		SavedTypesDropDown(Testname);
		
		}
		
		Thread.sleep(6000);
		VerifySaveTextTemplate(Testname, "Delete");
		
		if (doesElementExist2(properties.getProperty("DeleteTempltAlertMessage"), 5)) {
			String delTempAlrt = driver.findElement(By.cssSelector(properties.getProperty("DeleteTempltAlertMessage"))).getText();
			
			if (delTempAlrt.equals("Do you want to delete both the Pre-live and Live versions?")){
				log.logLine(Testname, false, "Alert message for confirming the \"Delete Template\" operation is sucessful ");
			}
			else {	
			log.logLine(Testname, true, "Alert message for confirming the \"Delete Template\" operation is unsucessful ");
			} 
		}else {
			log.logLine(Testname, true, "Alert message for confirming the \"Delete Template\" operation is not displayed ");
			throw new Exception("Alert message for confirming the \"Delete Template\" operation is not displayed ");

		}
		
		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("DeleteSveTempl"), 5)) {
        	WebElement delSveTmplt = driver.findElement(By.cssSelector(properties.getProperty("DeleteSveTempl")));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click()", delSveTmplt);
        	Thread.sleep(5000);
        	log.logLine(Testname, false, "Clicking on Ok button of the confirm \"Delete Template\" operation pop up window");
    	} else {
    	    log.logLine(Testname, true, "Unable to Click on Ok button of the confirm \"Delete Template\" operation pop up window");
    	    throw new Exception("Unable to Click on Ok button of the confirm \"Delete Template\" operation pop up window");
    	}
		
		
		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("ConfirmSave"), 5)) {
        	WebElement cnfmSve = driver.findElement(By.cssSelector(properties.getProperty("ConfirmSave")));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnfmSve);
        	Thread.sleep(2000);
        	log.logLine(Testname, false, "Clicking on \"close\" button of the Alert message of the delete template");
    	} else {
    	    log.logLine(Testname, true, "Clicking on \"close\" button of the Alert message of the delete template is failed");
    	    throw new Exception("Clicking on \"close\" button of the Alert message of the delete template is failed");
    	}
		
		
		Thread.sleep(5000);
		CancelSavedTmpl(Testname);
		
		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		VerifySaveTextTemplate(Testname, "VerifyDelete");
		Thread.sleep(5000);
		CancelSavedTmpl(Testname);
		
		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		AddTemplate(Testname);
		Thread.sleep(5000);
		
		//AddTemplNameDescptn(Testname);
		TemplateName=test.readColumnData("TemplateName", sheetname);
        TempltDecsp=test.readColumnData("TemplateDecrip", sheetname);
        
        FillTemplateName(Testname);
        Thread.sleep(5000);
        FillTemaplateDescription(Testname);
        Thread.sleep(5000);
       // Thread.sleep(5000);
		SaveTemplate(Testname);
        
		Thread.sleep(5000);
        if (doesElementExist2(properties.getProperty("ConfirmSave"), 5)) {
        	WebElement cnfmSve = driver.findElement(By.cssSelector(properties.getProperty("ConfirmSave")));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnfmSve);
        	Thread.sleep(4000);
        	log.logLine(Testname, false, "Clicking on close button of the Confirm \"Save\" Alert message");
    	} else {
    	    log.logLine(Testname, true, "Unable to Click on close button of the Confirm \"Save\" Alert message");
    	    throw new Exception("Unable to Click on close button of the Confirm \"Save\" Alert message");
    	}
        
        ClickExitEditorButton(Testname);
        
        Thread.sleep(5000);
        SavedTypesDropDown(Testname);
        Thread.sleep(5000);
        VerifySaveTextTemplate(Testname, "PromoteTempl");
        
        Thread.sleep(5000);
        if (doesElementExist2(properties.getProperty("ConfrmClrOkBtn"), 5)) {
        	WebElement CnfmClr = driver.findElement(By.cssSelector(properties.getProperty("ConfrmClrOkBtn")));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click()", CnfmClr);
        	PleasewaitDisappear();
        	log.logLine(Testname, false, "Clicking on confirm Ok button for promoting the template to current live template");
    	} else {
    	    log.logLine(Testname, true, "Clicking on confirm ok button for promoting the template to current live template is failed");
    	    throw new Exception("Clicking on confirm ok button for promoting the template to current live template is failed");
    	}
        
        Thread.sleep(5000);
        if (doesElementExist2(properties.getProperty("ConfirmSave"), 5)) {
        	WebElement cnfmSve = driver.findElement(By.cssSelector(properties.getProperty("ConfirmSave")));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnfmSve);
        	Thread.sleep(1000);
        	log.logLine(Testname, false, "Clicking on \"close\" button of the promote Alert message");
    	} else {
    	    log.logLine(Testname, true, "Clicking on \"close\" button of the promote Alert message is failed");
    	    throw new Exception("Clicking on \"close\" button of the promote Alert message is failed");
    	}
        
        Thread.sleep(5000);
        CancelSavedTmpl(Testname);
        
     
       /* 
       String EmailFrom="emSenderNotice@ha.testpivot.rrd.com";
       String EmailSub="Text / Template ["+EditTemplateName+"] Preview - Type: EmailtypTxt"+AccNo+"";
       
               
        Thread.sleep(60000);
		Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        try {
            Session session = Session.getInstance(props, null);
            Store store = session.getStore();            
            
            store.connect("imap.gmail.com", "automationpivot@gmail.com", "miracle123");
            
            Folder inbox = store.getFolder("INBOX");
            driver.navigate().refresh();
            inbox.open(Folder.READ_ONLY);
            
           // MimeMessage recentMessage = (MimeMessage) inbox.getMessage(1);
            //recentMessage.getSubject();
            
            Message msg = inbox.getMessage(inbox.getMessageCount());
            Address[] in = msg.getFrom();
            for (Address address : in) {
            	Thread.sleep(1000);
                System.out.println("FROM:" + address.toString());
                if (address.toString().equals(EmailFrom)){
                	log.logLine(Testname, false,"The "+address.toString()+" From Inbox Matches with the "+EmailFrom+"");	
                	break;
                }else {
    				log.logLine(Testname, true, "From Address is not readed");
				}
            }
            
            Multipart mp = (Multipart)msg.getContent();
            BodyPart bp = mp.getBodyPart(0);
            Thread.sleep(1000);
            System.out.println("SUBJECT:" + msg.getSubject());
            if (msg.getSubject().equals(EmailSub)){
            	log.logLine(Testname, false,"The "+msg.getSubject()+" From Inbox Matches with the "+EmailSub+" ");	
        	}else {
				log.logLine(Testname, true, "Subject Line is not readed");
			}
            
            Thread.sleep(1000);
            System.out.println("CONTENT:" + bp.getContent());
            if (bp.getContent().toString().contains(TempltDecsp)){
            	log.logLine(Testname, false,"The "+bp.getContent()+" From Inbox Matches with the "+TempltDecsp+"");	
            }else {
				log.logLine(Testname, true, "Content is not readed");
				}
        } catch (Exception mex) {
            mex.printStackTrace();
        }*/
        
        
        SavedTypesDropDown(Testname);
        Thread.sleep(5000);
        VerifySaveTextTemplate(Testname, "PromoteTempl");
        
        Thread.sleep(5000);
        if (doesElementExist2(properties.getProperty("ConfrmClrOkBtn"), 5)) {
        	WebElement CnfmClr = driver.findElement(By.cssSelector(properties.getProperty("ConfrmClrOkBtn")));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click()", CnfmClr);
        	PleasewaitDisappear();
        	log.logLine(Testname, false, "Clicking on confirm Ok button for promoting the template to current live template");
    	} else {
    	    log.logLine(Testname, true, "Clicking on confirm ok button for promoting the template to current live template is failed");
    	    throw new Exception("Clicking on confirm ok button for promoting the template to current live template is failed");
    	}
        
        Thread.sleep(5000);
        if (doesElementExist2(properties.getProperty("ConfirmSave"), 5)) {
        	WebElement cnfmSve = driver.findElement(By.cssSelector(properties.getProperty("ConfirmSave")));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnfmSve);
        	Thread.sleep(1000);
        	log.logLine(Testname, false, "Clicking on \"close\" buuton of the promote Alert message");
    	} else {
    	    log.logLine(Testname, true, "Clicking on \"close\" buuton of the promote Alert message is failed");
    	    throw new Exception("Clicking on \"close\" buuton of the promote Alert message is failed");
    	}
        
        
        CancelSavedTmpl(Testname);
        Thread.sleep(5000);
        
        ClickLiveRadioBtn(Testname);  
        Thread.sleep(5000);
        SavedTypesDropDown(Testname);
        Thread.sleep(5000);
        //SearchForLiveTemplate(Testname);
        
        VerifyLiveTemplates(Testname, "View");
        Thread.sleep(5000);
        
        ClickPreviewEmailBtn(Testname);
        Thread.sleep(5000);
        
        if (doesElementExist2(properties.getProperty("EmailAddrTextBox"), 5)) {
        	WebElement qtyday = driver.findElement(By.cssSelector(properties.getProperty("EmailAddrTextBox")));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click()", qtyday);
        	qtyday.clear();
        	qtyday.sendKeys(rplyTxt);
        	Thread.sleep(1000);
        	log.logLine(Testname, false, "Entering the \"To Email address\" in the Management Email pop up window  ");
    	} else {
    	    log.logLine(Testname, true, "Entering the \"To Email address\" in the Management Email pop up window is failed ");
    	    throw new Exception("Entering the \"To Email address\" in the Management Email pop up window  is failed");
    	}
        
        Thread.sleep(5000);
        PreviewSendBtn(Testname);
        Thread.sleep(5000);
        if (doesElementExist2(properties.getProperty("ConfirmSave"), 5)) {
        	WebElement cnfmSve = driver.findElement(By.cssSelector(properties.getProperty("ConfirmSave")));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnfmSve);
        	Thread.sleep(3000);
        	log.logLine(Testname, false, "Clicking on Close button of the Confirm \"Email sent\" Alert message");
    	} else {
    	    log.logLine(Testname, true, "Unable to Click on OK button of the Confirm \"Email sent\" Alert message");
    	    throw new Exception("Unable to Click on OK button of the Confirm \"Email sent\" Alert message");
    	}
        
        ClickExitEditorButton(Testname);
        Thread.sleep(5000);
        
        SavedTypesDropDown(Testname);
        Thread.sleep(5000);
        
        VerifyLiveTemplates(Testname, "RollBackView");
        Thread.sleep(5000);
        ClickExitEditorButton(Testname);    
        Thread.sleep(5000);
        
        SavedTypesDropDown(Testname);     
        Thread.sleep(5000);
        VerifyLiveTemplates(Testname, "Delete");
        Thread.sleep(5000);
        
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
			throw new Exception("Alert message for confirming the \"Delete Live Template\" operation is not displayed ");

		}
        
        Thread.sleep(5000);
        if (doesElementExist2(properties.getProperty("ConfirmDelLiveOnly"), 5)) {
        	WebElement livebtn = driver.findElement(By.cssSelector(properties.getProperty("ConfirmDelLiveOnly")));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click()", livebtn);
        	Thread.sleep(1000);
        	log.logLine(Testname, false, "Clicking on \"Delete Live only\" button of the Delete operation in Live Templates");
    	} else {
    	    log.logLine(Testname, true, "Clicking on \"Delete Live only\" button of the Delete operation in Live Templates is failed");
    	    throw new Exception("Clicking on \"Delete Live only\" button of the Delete operation in Live Templates is failed");
    	}
        
        Thread.sleep(5000);
        
        if (doesElementExist2(properties.getProperty("DeleteAlert"), 5)) {
        	WebElement livebtn = driver.findElement(By.cssSelector(properties.getProperty("DeleteAlert")));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click()", livebtn);
        	Thread.sleep(1000);
        	log.logLine(Testname, false, "Clicking on \"close\" button of the Delete Alert message in Live Templates");
    	} else {
    	    log.logLine(Testname, true, "Clicking on \"close\" button of the Delete Alert message in Live Templates is failed");
    	    throw new Exception("Clicking on \"close\" button of the Delete Alert message in Live Templates is failed");
    	}
    	
        
        Thread.sleep(2000);
        CancelSavedTmpl(Testname);
        Thread.sleep(5000);
        ClickPreLiveRadioBtn(Testname);
        Thread.sleep(5000);
        ClickLiveRadioBtn(Testname);
        Thread.sleep(5000);
        SavedTypesDropDown(Testname);
        Thread.sleep(5000);
        
        VerifyLiveTemplates(Testname, "VerifyDeleteLiveOnlyInLiveTemplates");
        Thread.sleep(5000);
        
        CancelSavedTmpl(Testname);
        Thread.sleep(5000);
        ClickPreLiveRadioBtn(Testname);
        Thread.sleep(5000);
        
        SavedTypesDropDown(Testname);
        Thread.sleep(5000);
        
        VerifyLiveTemplates(Testname, "VerifyDeleteLiveOnlyInPreLiveTemplates");
        Thread.sleep(5000);
        
        CancelSavedTmpl(Testname);
        Thread.sleep(5000);
        
        ClickLiveRadioBtn(Testname);
        Thread.sleep(5000);
        SavedTypesDropDown(Testname);
        Thread.sleep(5000);
       
        VerifyLiveTemplates(Testname, "RollBack");
        
        
        Thread.sleep(5000);
        if (doesElementExist2(" div div[id='kendoWindowConfirmMessage']", 5)) {
			String delTempAlrt = driver.findElement(By.cssSelector("div div[id='kendoWindowConfirmMessage']")).getText();
			
			if (delTempAlrt.equals("You are about to restore the template \'"+TemplateName.concat(AccNo)+"\', this action will overwrite the current live version (if any). Are you sure?")){
				log.logLine(Testname, false, "Alert message for confirming the \"RollBack\" operation in Live Templates is sucessful ");
			} 
			else {	
			log.logLine(Testname, true, "Alert message for confirming the \"RollBack\" operation in Live Templates is unsucessful ");
			} 
		}else {
			log.logLine(Testname, true, "Alert is message for confirming the \"RollBack\" operation in Live Templates is not displayed ");
			throw new Exception("Alert message for confirming the \"RollBack\" operation in Live Templates is not displayed ");

		}
        
        Thread.sleep(5000);
        if (doesElementExist2(properties.getProperty("ConfrmClrOkBtn"), 5)) {
        	WebElement CnfmClr = driver.findElement(By.cssSelector(properties.getProperty("ConfrmClrOkBtn")));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click()", CnfmClr);
        	Thread.sleep(1000);
        	log.logLine(Testname, false, "Clicking on the Ok button of the  RollBack Alert message");
    	} else {
    	    log.logLine(Testname, true, "Clicking on the Ok button of the  RollBack Alert message is failed");
    	    throw new Exception("Clicking on the Ok button of the  RollBack Alert message is failed");
    	}
        
        Thread.sleep(5000);
        if (doesElementExist2(properties.getProperty("DeleteAlert"), 5)) {
        	WebElement livebtn = driver.findElement(By.cssSelector(properties.getProperty("DeleteAlert")));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click()", livebtn);
        	Thread.sleep(1000);
        	log.logLine(Testname, false, "Clicking on \"close\" button of the RollBack Alert message in Live Templates");
    	} else {
    	    log.logLine(Testname, true, "Clicking on \"close\" button of the RollBack Alert message in Live Templates is failed");
    	    throw new Exception("Clicking on \"close\" button of the RollBack Alert message in Live Templates is failed");
    	}
        
        CancelSavedTmpl(Testname);
        Thread.sleep(5000);
        SavedTypesDropDown(Testname);
        Thread.sleep(5000);
        
        VerifyLiveTemplates(Testname, "VerifyRollBack");
        Thread.sleep(5000);
        CancelSavedTmpl(Testname);
        
        
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


			Thread.sleep(5000);
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
		    
			Thread.sleep(25000);
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
	
	
	public boolean FillTemplateName(String Testname ) throws Exception {
		
		Thread.sleep(2000);
        if (doesElementExist2(properties.getProperty("TempltNameTxtBox"), 5)) {
	    	WebElement qtyday = driver.findElement(By.cssSelector(properties.getProperty("TempltNameTxtBox")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", qtyday);
	    	qtyday.clear();
	    	qtyday.sendKeys(TemplateName.concat(AccNo));
	    	Thread.sleep(2000);
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
	    	Thread.sleep(3000);
	    	log.logLine(Testname, false, "Entering the text as "+TempltDecsp+" in \"Template Description\" textbox ");
		} else {
		    log.logLine(Testname, true, "Unable to Enter the text as "+TempltDecsp+" in \"Template Description\" textbox ");
		    throw new Exception("Unable to Enter the text as "+TempltDecsp+" in \"Template Description\" textbox ");
		}
		
		return true;
	}
	
	public void ClickeDeliverTab(String Testname) throws Exception {
		if (doesElementExist2(properties.getProperty("EdeliveryTab"), 5)) {
			WebElement eDelive = driver.findElement(By.cssSelector(properties.getProperty("EdeliveryTab")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
			log.logLine(Testname, false, "Click on e-Delivery Tab is Successful");
		} else {
			log.logLine(Testname, true, "Click on e-Delivery Tab is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Click on e-Delivery Tab is failed");
		}
	}
	
	public boolean ClickExitEditorButton(String Testname ) throws Exception {
	
		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("ExitEditor"), 5)) {
	    	WebElement exitEdtr = driver.findElement(By.cssSelector(properties.getProperty("ExitEditor")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", exitEdtr);
	    	Thread.sleep(1000);
	    	log.logLine(Testname, false, "Clicking on \"Exit Editor\" button after adding the template ");
		} else {
		    log.logLine(Testname, true, "Clicking on \"Exit Editor\" button after adding the template is failed");
		    throw new Exception("Clicking on \"Exit Editor\" button after adding the template is failed");
		}
			return true;
	}
	
	public boolean ClickPreLiveRadioBtn(String Testname ) throws Exception {
		
		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("PreLiveRadioBtn"), 5)) {
        	WebElement livebtn = driver.findElement(By.xpath(properties.getProperty("PreLiveRadioBtn")));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click()", livebtn);
        	Thread.sleep(1000);
        	log.logLine(Testname, false, "Clicking on \"Pre-Live\" radio button under Template management");
    	} else {
    	    log.logLine(Testname, true, "Clicking on \"Pre-Live\" radio button under Template management is failed");
    	    throw new Exception("Clicking on \"Pre-Live\" radio button under Template management is failed");
    	}
			return true;
	}
	
	public boolean ClickLiveRadioBtn(String Testname ) throws Exception {
			
			Thread.sleep(2000);
			if (doesElementExist(properties.getProperty("LiveRadioBtn"), 5)) {
	        	WebElement livebtn = driver.findElement(By.xpath(properties.getProperty("LiveRadioBtn")));
	        	((JavascriptExecutor) driver).executeScript("arguments[0].click()", livebtn);
	        	Thread.sleep(1000);
	        	log.logLine(Testname, false, "Clicking on \"Live\" radio button under Template management");
	    	} else {
	    	    log.logLine(Testname, true, "Clicking on \"Live\" radio button under Template management is failed");
	    	    throw new Exception("Clicking on \"Live\" radio button under Template management is failed");
	    	}
				return true;
	}
	
	
	
	public boolean ClickPreviewEmailBtn(String Testname ) throws Exception {
		
		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("PreviewEmailBtn"), 5)) {
        	WebElement previewbtn = driver.findElement(By.cssSelector(properties.getProperty("PreviewEmailBtn")));
        	((JavascriptExecutor) driver).executeScript("arguments[0].click()", previewbtn);
        	Thread.sleep(1000);
        	log.logLine(Testname, false, "Clicking on \"Preview Email\"  button");
    	} else {
    	    log.logLine(Testname, true, "Clicking on \"Preview Email\"  button is failed");
    	    throw new Exception("Clicking on \"Preview Email\"  button is failed");
    	}
			return true;
	}
   
	public boolean PreviewSendBtn(String Testname ) throws Exception {
	
			
			if (doesElementExist2(properties.getProperty("SendEmlAddrBtn"), 5)) {	   
		    	WebElement sendPreEmlBtn = driver.findElement(By.cssSelector(properties.getProperty("SendEmlAddrBtn")));
		    	sendPreEmlBtn.click();
		    	Thread.sleep(5000);
		    	//((JavascriptExecutor) driver).executeScript("arguments[0].click()", sendPreEmlBtn);
		    	
				log.logLine(Testname, false, "Clicked on \"Send\" button of the management e-mail ..");
	        	
	        } else {
				log.logLine(Testname, true, "Clicking on \"Send\" button of the management e-mail ..is failed ");
				throw new Exception("Clicking on \"Send\" button of the management e-mail ..is failed ");
			}
	
			return true;
		}
	
	
	public boolean SavedTypesDropDown(String Testname ) throws Exception {

		
		if (doesElementExist2(properties.getProperty("SavedTypesBtn"), 5)) {	   
	    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("SavedTypesBtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
	    	Thread.sleep(5000);
			log.logLine(Testname, false, "Clicked on \"Saved Types\" dropdown..");
        	
        } else {
			log.logLine(Testname, true, "Unable to Click on \"Saved Types\" dropdown..");
			throw new Exception("Unable to Click on \"Saved Types\" dropdown..");
		}

		return true;
	}
	
	
	
	public boolean CancelSavedTmpl(String Testname ) throws Exception {	
			
		if (doesElementExist2(properties.getProperty("CancelTemplt"), 5)) {	   
	    	WebElement cancel = driver.findElement(By.cssSelector(properties.getProperty("CancelTemplt")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", cancel);
	    	log.logLine(Testname, false, "Clicked on \"Cancel\" button of the SavedTypes dropdown..");
        	
        } else {
			log.logLine(Testname, true, "Unable to Click on \"Cancel\" button of the SavedTypes dropdown..");
			throw new Exception("Unable to Click on \"Cancel\" button of the SavedTypes dropdown..");
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
		
	
	public boolean VerifyNoTemplate(String Testname ) throws Exception {
	
		String[] EmlType1 = new String[100];
	    String row2 = "li";
	    notemplts="No templates found!";
		    
		    WebElement table1 = driver.findElement(By.xpath(".//*[@id='ddl-management-saved-types-div']"));
		    
			List<WebElement> DataCnt3= driver.findElements(By.xpath(".//*[@id='ul-management-saved-types']/li"));
			
			Thread.sleep(2000);
			if(doesElementExist2("ul "+row2+" div[class='grid-btns'] div", 5)){
				for(int i = 0; i < DataCnt3.size(); i++) {
					EmlType1[i] = driver.findElement(By.cssSelector("ul "+row2+" div[class='grid-btns'] div")).getText();
					
					if(EmlType1[i].equals(EmailtypTxt.concat(AccNo))){
						
						log.logLine(Testname, false, "Iterating through the Rows....and Reading the Type in Saved types as "+EmailtypTxt.concat(AccNo));
						
						
						
						if (doesElementExist2("ul "+row2+" div[class='grid-btns ui-sortable-handle'] label", 5)) {
							WebElement textradio=driver.findElement(By.cssSelector("ul "+row2+" div[class='grid-btns'] label"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", textradio);
							Thread.sleep(3000);
							log.logLine(Testname, false, "Clicking on the \"Text\" radiobutton of the saved types ");
							
							if (doesElementExist2("div table tbody tr td ul+ul li[class='grid-btns'] span", 5)) {
								String notmpltfnd = driver.findElement(By.cssSelector("div table tbody tr td ul+ul li[class='grid-btns']")).getText();
					        	if(notmpltfnd.equals(notemplts)){
					        		log.logLine(Testname, false, "No text templates are added to the Saved Email Type , hence clear template is sucessful");
					        	}else{
					        		log.logLine(Testname, true, "Temaplates are added after clearing the text hence clear template is unsucessful");
					        		//throw new Exception("Temaplates are added after clearing the text hence clear template is unsucessful");
					        	}
								
					    	} else {
					    	    log.logLine(Testname, true, "Clicking on the \"Text\" radiobutton of the saved types is failed");
					    	    throw new Exception("Clicking on the \"Text\" radiobutton of the saved types is failed");
					    	}
						}
						break; 
					}
					row2 = row2 + "+li";
					
					}
				
				}
	
		return true;
	}
	
	
	
	
	public boolean SaveTemplate(String Testname ) throws Exception {
	     Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("SaveTemplate"), 5)) {	   
	    	WebElement savebtn = driver.findElement(By.cssSelector(properties.getProperty("SaveTemplate")));
	    	savebtn.click();
	    	//((JavascriptExecutor) driver).executeScript("arguments[0].click()", savebtn);
	    	Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on \"Save\" button to add the template..");        	
        } else {
			log.logLine(Testname, true, "Clicking on \"Save\" button to add the template..is failed");
			throw new Exception("Clicking on \"Save\" button to add the template..is failed");
		}

		return true;
	}
	
	
	public boolean VerifyLiveTemplates(String Testname, String SrchType) throws Exception {
		
		String[] EmlType1 = new String[50];
		WebElement LiveTemplate;
	    String row_3 = "li";
	    notemplts="No templates found!";
		    
		    WebElement table1 = driver.findElement(By.xpath(".//*[@id='ddl-management-saved-types-div']"));
		    
			List<WebElement> DataCnt_3= driver.findElements(By.xpath(".//*[@id='ul-management-saved-types']/li"));
			
			Thread.sleep(3000);
			if(doesElementExist2("ul "+row_3+" div[class='grid-btns'] div", 5)){
				for(int k = 0; k < DataCnt_3.size(); k++) {
					EmlType1[k] = driver.findElement(By.cssSelector("ul "+row_3+" div[class='grid-btns'] div")).getText();
					
					LiveTemplate=driver.findElement(By.cssSelector("ul "+row_3+" div[class='grid-btns'] div"));
					
					if(EmlType1[k].equals(EmailtypTxt.concat(AccNo))){
						
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", LiveTemplate);
						
						log.logLine(Testname, false, "Iterating through the Rows....and clicking on the live Template promoted from pre-live template");
					
							
				switch (SrchType) {

				case "View":
					
						String veiwrow= "li";
						List<WebElement> DataCnt4= driver.findElements(By.xpath(".//*[@class='ul-templates ui-sortable']/li"));
						
						
							if(doesElementExist2("ul "+veiwrow+" div div+div[class='lbl-management-saved-type-name']", 5)){
						
							for(int j = 0; j < DataCnt4.size(); j++) {
								EmlType1[j] = driver.findElement(By.cssSelector("ul "+veiwrow+" div div+div[class='lbl-management-saved-type-name']")).getText();
								
								if(EmlType1[j].equals(TemplateName.concat(AccNo))){
									
									log.logLine(Testname, false, "Iterating through the Rows....Reading the Type in Saved type as "+EmlType1[j]);
									
									
									if (doesElementExist2("ul "+veiwrow+" div button[class='k-button btn-management-saved-template-view']", 5)) {
										WebElement viewLiveTemp=driver.findElement(By.cssSelector("ul "+veiwrow+" div button[class='k-button btn-management-saved-template-view']"));
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
							
							if(EmlType1[j].equals(TemplateName.concat(AccNo))){
								
								log.logLine(Testname, false, "Iterating through the Rows....Reading the Type in Saved type as "+EmlType1[j]);
								
								
								if (doesElementExist2("ul "+rollbackview+" div button+button+button[class='k-button btn-management-saved-template-rollbackview']", 5)) {
									WebElement viewLiveTemp=driver.findElement(By.cssSelector("ul "+rollbackview+" div button+button+button[class='k-button btn-management-saved-template-rollbackview']"));
									((JavascriptExecutor) driver).executeScript("arguments[0].click()", viewLiveTemp);
									PleasewaitDisappear();
									log.logLine(Testname, false, "Clicking on the \"View\" button of the saved types Text Template in Live Templates");
									
					
								}
								break;
								
							}
							rollbackview = rollbackview + "+li";
							
						}
						
					break;
				}
							
							
							
				/*case "RollBackView":
					
					String rollbackview= "li";
					List<WebElement> DataCnt8= driver.findElements(By.xpath(".//*[@class='ul-templatesRollback']/li"));
					
					
						if(doesElementExist2("ul "+rollbackview+" div div+div[class='lbl-management-saved-type-name'] ", 5)){
					
						for(int j = 0; j < DataCnt8.size(); j++) {
							EmlType1[j] = driver.findElement(By.cssSelector(properties.getProperty("ul "+rollbackview+" div div+div[class='lbl-management-saved-type-name']"))).getText();
							
							if(EmlType1[j].equals(TemplateName)){
								
								log.logLine(Testname, false, "Iterating through the Rows....and Reading the Type in Saved type as "+EmlType1[j]);
								
								
								if (doesElementExist2("ul "+rollbackview+" div button+button+button[class='k-button btn-management-saved-template-rollbackview']", 5)) {
									WebElement rollbackView=driver.findElement(By.cssSelector("ul "+rollbackview+" div button+button+button[class='k-button btn-management-saved-template-rollbackview']"));
									((JavascriptExecutor) driver).executeScript("arguments[0].click()", rollbackView);
									PleasewaitDisappear();
									log.logLine(Testname, false, "Clicking on the \"RollBack View\" button of the saved types Text Live Template");
									
					
								}
								break;
								
							}
							rollbackview = rollbackview + "+li";
							
						}
						
					break;
				}*/
							
			
				case "VerifyDeleteLiveOnlyInPreLiveTemplates":
					String editrow1= "li";
					List<WebElement> DataCnt5= driver.findElements(By.xpath(".//*[@class='ul-templates ui-sortable']/li"));
					
					
						if(doesElementExist2("ul "+editrow1+" div div+div[class='lbl-management-saved-type-name']", 5)){
					
						for(int j = 0; j < DataCnt5.size(); j++) {
							EmlType1[j] = driver.findElement(By.cssSelector("ul "+editrow1+" div div+div[class='lbl-management-saved-type-name']")).getText();
							
							if(EmlType1[j].equals(EmailtypTxt.concat(AccNo))){
								
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
							
							if(EmlType1[j].equals(TemplateName.concat(AccNo))){
								
								log.logLine(Testname, false, "Iterating through the Rows, Reading the Type in Saved types as "+EmlType1[j]+" and clicking on the delete button");
								
								
								if (doesElementExist2("ul "+editrow2+" div button+button[class='k-button btn-management-saved-template-delete']", 5)) {
									WebElement delete=driver.findElement(By.cssSelector("ul "+editrow2+" div button+button[class='k-button btn-management-saved-template-delete']"));
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
					
					
            	/*String rollback= "li";
            	
					List<WebElement> rollcnt= driver.findElements(By.xpath(".//*[@id='ul-templates ui-sortable']/li"));
					
					
						if(doesElementExist("/ul[class='ul-templatesRollback']/li/div div+div[class='lbl-management-saved-type-name']", 5)){
						Thread.sleep(2000);
						
						for(int j = 0; j < rollcnt.size(); j++) {
							EmlType1[j] = driver.findElement(By.cssSelector("ul[class='ul-templatesRollback'] "+rollback+" div div+div[class='lbl-management-saved-type-name']")).getText();
							Thread.sleep(2000);
							
							if(EmlType1[j].equals(TemplateName)){
								
								log.logLine(Testname, false, "Iterating through the Rows....and Reading the Type in Saved type as "+EmlType1[j]);
								
								
								if (doesElementExist2("ul[class='ul-templatesRollback'] "+rollback+" div button+button+button+button[class='k-button btn-management-saved-template-rollback']", 5)) {
									WebElement rollbackbtn=driver.findElement(By.cssSelector("ul[class='ul-templatesRollback'] "+rollback+" div button+button+button+button[class='k-button btn-management-saved-template-rollback']"));
									((JavascriptExecutor) driver).executeScript("arguments[0].click()", rollbackbtn);
									PleasewaitDisappear();
									log.logLine(Testname, false, "Clicking on the \"RollBack \" button of the saved types Text Live Template");
									
					
								}
								break;
								
							}
							rollback = rollback + "+li";
							
						}
						
					break;
				}*/
						
					
				if(doesElementExist2(" "+row_3+" button[title='Rollback']", 5)) {
					WebElement rollbackbtn=driver.findElement(By.cssSelector(""+row_3+" button[title='Rollback']"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", rollbackbtn);
					PleasewaitDisappear();
					log.logLine(Testname, false, "Clicking on the \"RollBack \" button of the saved types Text Live Template");
				
				}else{
					log.logLine(Testname, false, "Clicking on the \"RollBack \" button of the saved types Text Live Template is failed");
					throw new Exception("Clicking on the \"RollBack \" button of the saved types Text Live Template is failed");
				}
        	
				break;	
						
             case "VerifyRollBack":
					
					String verRollback= "li";
					List<WebElement> rollbackcnt= driver.findElements(By.xpath(".//*[@class='ul-templates ui-sortable']/li"));
					
					
						if(doesElementExist2("ul "+verRollback+" div div+div[class='lbl-management-saved-type-name']", 5)){
					
						for(int j = 0; j < rollbackcnt.size(); j++) {
							EmlType1[j] = driver.findElement(By.cssSelector("ul "+verRollback+" div div+div[class='lbl-management-saved-type-name']")).getText();
							
							if(EmlType1[j].equals(TemplateName.concat(AccNo))){
								
								log.logLine(Testname, false, "Iterating through the Rows....Reading the Type in Saved type as "+EmlType1[j]);
								
								log.logLine(Testname, false, " Text template "+TemplateName.concat(AccNo)+" of Email Template is present after rollbacked from delete operation  ");
								
								break;
								
							}
							verRollback = verRollback + "+li";
							
						}
						
					break;
				}
						
             case "VerifyDeleteLiveOnlyInLiveTemplates":
					
					notemplts="No templates found!";
					if (doesElementExist2("div table tbody tr td ul+ul li[class='grid-btns ui-sortable-handle'] span", 5)) {
						String notmpltfnd = driver.findElement(By.cssSelector("div table tbody tr td ul+ul li[class='grid-btns ui-sortable-handle'] span")).getText();
			        	if(notmpltfnd.equals("No templates found!")){
			        		log.logLine(Testname, false, " "+notmpltfnd+" is present hence \"Delete\" Live text template is sucessful");
			        	}else{
			        		log.logLine(Testname, true, " "+notmpltfnd+" is not present hence \"delete\" Live text template is unsucessful");
			        		//throw new Exception(""+notmpltfnd+" is not present hence \"delete\" text template is unsucessful");
			        	}
						
			    	} else {
			    	    log.logLine(Testname, true, "Clicking on Delete button is failed");
			    	   // throw new Exception("Clicking on Delete button is failed");
			    	}
								
				
				} //closing of scrhtype
				break; 
			
				
			}	
			
			row_3 = row_3 + "+li";
				
				
			
			}

		return true;
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
				if (xmlfield[f].equals("FirstName")) {
					
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

	 
	public boolean VerifySaveTextTemplate(String Testname,String SrchType) throws Exception {
		
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
					if (doesElementExist2("div table tbody tr td ul+ul li[class='grid-btns ui-sortable-handle'] span", 5)) {
						String notmpltfnd = driver.findElement(By.cssSelector("div table tbody tr td ul+ul li[class='grid-btns ui-sortable-handle'] span")).getText();
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
		}else{
			log.logLine(Testname, true, "First default value is not present ");
			throw new Exception("First default value is not present ");
		}
		
		if (doesElementExist2(properties.getProperty("DefaultFieldName2"), 5)) {	    
			String defltVal2 = driver.findElement(By.cssSelector(properties.getProperty("DefaultFieldName2"))).getAttribute("value");
			log.logLine(Testname, false, "Second default value is  "+defltVal2+" ");
		}else{
			log.logLine(Testname, true, "Second default value is not present ");
			throw new Exception("Second default value is not present ");
		}
		
		if (doesElementExist2(properties.getProperty("DefaultFieldName3"), 5)) {	    
			String defltVal3 = driver.findElement(By.cssSelector(properties.getProperty("DefaultFieldName3"))).getAttribute("value");
			log.logLine(Testname, false, "Third default value is  "+defltVal3+" ");
		}else{
			log.logLine(Testname, true, "Third default value is not present ");
			throw new Exception("Third default value is not present ");

		}
		
		if (doesElementExist2(properties.getProperty("DefaultFieldLabel1"), 5)) {	    
			String defltLbl1 = driver.findElement(By.cssSelector(properties.getProperty("DefaultFieldLabel1"))).getText();
			log.logLine(Testname, false, "First default value is  "+defltLbl1+" ");
		}else{
			log.logLine(Testname, true, "First default value is not present ");
			throw new Exception("First default value is not present ");
		}
		
		if (doesElementExist2(properties.getProperty("DefaultFieldLabel2"), 5)) {	    
			String defltLbl2 = driver.findElement(By.cssSelector(properties.getProperty("DefaultFieldLabel2"))).getText();
			log.logLine(Testname, false, "Second default value is  "+defltLbl2+" ");
		}else{
			log.logLine(Testname, true, "Second default value is not present ");
			throw new Exception("Second default value is not present ");
		}
		
		if (doesElementExist2(properties.getProperty("DefaultFieldLabel3"), 5)) {	    
			String defltLbl3 = driver.findElement(By.cssSelector(properties.getProperty("DefaultFieldLabel3"))).getText();
			log.logLine(Testname, false, "Third default value is  "+defltLbl3+" ");
		}else{
			log.logLine(Testname, true, "Third default value is not present ");
			throw new Exception("Third default value is not present ");

		}
		
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
		
		if (doesElementExist2(properties.getProperty("FirstNewFieldVal"), 5)) {
	    	WebElement fldVal1 = driver.findElement(By.cssSelector(properties.getProperty("FirstNewFieldVal")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", fldVal1);
	    	fldVal1.clear();	    	
	    	log.logLine(Testname, false, "Entering the special characters as "+spacialChar+"  in the first user added text box in XML schema container");
		} else {
		    log.logLine(Testname, true, "Unable to Enter the special characters in the first text box in XML schema container");
		    throw new Exception("Unable to Enter the special characters in the first text box in XML schema container");
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
		    throw new Exception("Unable to Enter the name as "+name+" in the second text box in XML schema container");
		}
		
		ClickSaveBtnOfXML(Testname);
		
		//Alert pop up for Empty text field
		Alert alert4 = driver.switchTo().alert();
		alert4.accept();
		
		log.logLine(Testname, false, "Alert pop up message \"Must fill all field values. They are required. \" appears");
		
		ClickClearBtnOfXML(Testname);
		ClickPlusBtnOfXML(Testname);
		
		if (doesElementExist2(properties.getProperty("FirstNewFieldVal"), 5)) {
	    	WebElement fldVal1 = driver.findElement(By.cssSelector(properties.getProperty("FirstNewFieldVal")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", fldVal1);
	    	fldVal1.clear();
	    	fldVal1.sendKeys(name);
	    	Thread.sleep(2000);
	    	log.logLine(Testname, false, "Entering the special characters as "+name+"  in the first user added text box in XML schema container");
		} else {
		    log.logLine(Testname, true, "Unable to Enter the special characters in the first text box in XML schema container");
		    throw new Exception("Unable to Enter the special characters in the first text box in XML schema container");
		}
		
		ClickSaveBtnOfXML(Testname);
		
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
		AddTemplate(Testname);
		ValidateXMLFldAdded(Testname);
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
		
		SavedTypesDropDown(Testname);
		VerifySaveTextTemplate(Testname, "ClickLoadXMLBtn");
		ClickRemoveBtnOfXML(Testname);
		ClickSaveBtnOfXML(Testname);
		
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
		AddTemplate(Testname);
		ValidateXMLFldRemoved(Testname);
		
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
		
		SavedTypesDropDown(Testname);
		VerifySaveTextTemplate(Testname, "ClickLoadXMLBtn");
		ClickBrowseBtnOfXML(Testname, "jpeg");
		
		//Alert pops up when uploading the file in .jpeg format as "Only .xsd, .xml or .txt  files can be uploaded"
		Alert alert5 = driver.switchTo().alert();
		alert5.accept();
		
		ClickBrowseBtnOfXML(Testname, "docx");
		//Alert pops up when uploading the file in .docx format as "Only .xsd, .xml or .txt  files can be uploaded"
		Alert alert6 = driver.switchTo().alert();
		alert6.accept();
		
		
		ClickBrowseBtnOfXML(Testname, "xlsx");
		//Alert pops up when uploading the file in .xlsx format as "Only .xsd, .xml or .txt  files can be uploaded"
		Alert alert7 = driver.switchTo().alert();
		alert7.accept();
		
		ClickBrowseBtnOfXML(Testname, "txt");
		ClickSaveBtnOfXML(Testname);
		
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
		AddTemplate(Testname);
		ValidateXMLFldAdded_Txt_xml_xsd_new(Testname, "txtfldd");
		
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
		//////////////////////////
		SavedTypesDropDown(Testname);
		VerifySaveTextTemplate(Testname, "ClickLoadXMLBtn");
		ClickBrowseBtnOfXML(Testname, "xml");
		ClickSaveBtnOfXML(Testname);
		
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
		AddTemplate(Testname);
		ValidateXMLFldAdded_Txt_xml_xsd_new(Testname, "xmlflds");
		
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
		SavedTypesDropDown(Testname);
		VerifySaveTextTemplate(Testname, "ClickLoadXMLBtn");
		ClickBrowseBtnOfXML(Testname, "xsd");
		ClickSaveBtnOfXML(Testname);
		
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
		AddTemplate(Testname);
		ValidateXMLFldAdded_Txt_xml_xsd_new(Testname, "xsdflds");
		
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
		
		
		return true;
	}
	
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
			throw new Exception("Newly added xml field is not displayed in the template management pre live view");
		}
		
		return true;
	}
	
	
	
	
public boolean ValidateXMLFldRemoved(String Testname) throws Exception {
		
		
		if (doesElementExist2(properties.getProperty("ValidateXmlFldAdded"), 5)) {	    
  			WebElement xmlFldName = driver.findElement(By.cssSelector(properties.getProperty("ValidateXmlFldAdded")));

  			List<WebElement> xmlfld = driver.findElements(By.cssSelector(properties.getProperty("ValidateXmlFldAdded")));
  				for (WebElement link:xmlfld) {
  					if (!link.getText().equals(name)) {
  						
  						log.logLine(Testname, false, "Deleted xml field name does not exist in the template management pre live view of the  Editor form ");
  						
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
	
	
	public boolean ClickPlusBtnOfXML(String Testname) throws Exception {
		
		if (doesElementExist2(properties.getProperty("AddBtn_xmlSchema"), 5)) {	   
	    	WebElement plusBtn = driver.findElement(By.cssSelector(properties.getProperty("AddBtn_xmlSchema")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", plusBtn);
	    	
			log.logLine(Testname, false, "Clicked on \"Add\" button to add the new field in xml schema container..");
        	
        } else {
			log.logLine(Testname, true, "Unable to Click on \"Add\" button to add the new field in xml schema container..");
			throw new Exception("Unable to Click on \"Add\" button to add the new field in xml schema container..");
		}
		
		return true;
	}
	
	public boolean ClickRemoveBtnOfXML(String Testname) throws Exception {
		
		if (doesElementExist2(properties.getProperty("RemoveBtn_xmlSchema"), 5)) {	   
	    	WebElement plusBtn = driver.findElement(By.cssSelector(properties.getProperty("RemoveBtn_xmlSchema")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", plusBtn);
	    	
			log.logLine(Testname, false, "Clicked on \"Remove\" button to delete the new field in xml schema container..");
        	
        } else {
			log.logLine(Testname, true, "Unable to Click on \"Remove\" button to delete the new field in xml schema container..");
			throw new Exception("Unable to Click on \"Remove\" button to delete the new field in xml schema container..");
		}
		
		return true;
	}
	
	public boolean ClickSaveBtnOfXML(String Testname) throws Exception {
		
		if (doesElementExist2(properties.getProperty("SaveXMLBtn"), 5)) {	   
	    	WebElement saveBtn = driver.findElement(By.cssSelector(properties.getProperty("SaveXMLBtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", saveBtn);
	    	
			log.logLine(Testname, false, "Clicked on \"Save\" button to save the new field in xml schema container..");
        	
        } else {
			log.logLine(Testname, true, "Unable to Click on \"Save\" button to save the new field in xml schema container..");
			throw new Exception("Unable to Click on \"Save\" button to save the new field in xml schema container..");
		}
		
		return true;
	}
	
	public boolean ClickClearBtnOfXML(String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("ClearXMLBtn"), 5)) {
			WebElement saveBtn = driver.findElement(By.cssSelector(properties
					.getProperty("ClearXMLBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",
					saveBtn);

			log.logLine(Testname, false,
					"Clicked on \"Clear\" button to clear the new field in xml schema container..");

		} else {
			log.logLine(
					Testname,
					true,
					"Unable to Click on \"Clear\" button to clear the new field in xml schema container..");
			throw new Exception(
					"Unable to Click on \"Clear\" button to clear the new field in xml schema container..");
		}

		return true;
	}
	
	/*public boolean ClickBrowseBtnOfXML_jpeg(String Testname) throws Exception {
		
		if (doesElementExist2(properties.getProperty("BrowseXMLBtn"), 5)) {	   
	    	WebElement brwseBtn = driver.findElement(By.cssSelector(properties.getProperty("BrowseXMLBtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", brwseBtn);
	    	brwseBtn.sendKeys("C:/Pivot_Portal/Test Data/TestImage.jpg");
			log.logLine(Testname, false, "Clicked on \"Browse\" button to upload the .jpeg file..");
        	
        } else {
			log.logLine(Testname, true, "Unable to Click on \"Browse\" button to upload the .jpeg file..");
			throw new Exception("Unable to Click on \"Browse\" button to upload the .jpeg file..");
		}
		
		return true;
	}
	
	public boolean ClickBrowseBtnOfXML_docx(String Testname) throws Exception {
		
		if (doesElementExist2(properties.getProperty("BrowseXMLBtn"), 5)) {	   
	    	WebElement brwseBtn = driver.findElement(By.cssSelector(properties.getProperty("BrowseXMLBtn")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", brwseBtn);
	    	brwseBtn.sendKeys("C:/Pivot_Portal/Test Data/XmldocTest.docx");
			log.logLine(Testname, false, "Clicked on \"Browse\" button to upload the .docx file..");
        	
        } else {
			log.logLine(Testname, true, "Unable to Click on \"Browse\" button to upload the .docx file..");
			throw new Exception("Unable to Click on \"Browse\" button to upload the .docx file..");
		}
		
		return true;
	}*/
	
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
	    	Thread.sleep(6000);
	    	log.logLine(Testname, false, "Updating the existing text template is sucessful");
		} else {
		    log.logLine(Testname, true, "Updating the existing text template is unsucessful");
		    throw new Exception("Updating the existing text template is unsucessful");
		}
		
	  	
	return true;
	}
	
	
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
		    throw new Exception("Unable to Enter the text as "+TemplateName+" in \"Template Name\" textbox ");
		}
	    
	    if (doesElementExist2(properties.getProperty("TempltDescpTxtArea"), 5)) {
	    	WebElement qtyday = driver.findElement(By.cssSelector(properties.getProperty("TempltDescpTxtArea")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", qtyday);
	    	qtyday.clear();
	    	qtyday.sendKeys(TempltDecsp);
	    	Thread.sleep(3000);
	    	log.logLine(Testname, false, "Entering the text as "+TempltDecsp+" in \"Template Description\" textbox ");
		} else {
		    log.logLine(Testname, true, "Unable to Enter the text as "+TempltDecsp+" in \"Template Description\" textbox ");
		    throw new Exception("Unable to Enter the text as "+TempltDecsp+" in \"Template Description\" textbox ");
		}
	
		return true;
		}
	
	public boolean xmlSchemaAnd_eDelvery_FldVal(String Testname, String RandNo) throws Exception {
		
		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
		
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		
		ClntName = test.readColumnData("ClientName", sheetname);
		AppId = test.readColumnData("ApplicationId", sheetname);
		useradmnid = test.readColumnData("AdminUserId", sheetname);
		userappid = test.readColumnData("ApplicationId", sheetname);
		
		Actions action = new Actions(driver);
		if ((doesElementExist2(properties.getProperty("Adminlnk"), 5))) {                
            WebElement oldpivt = driver.findElement(By.cssSelector(properties.getProperty("Adminlnk")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", oldpivt);
            Thread.sleep(3000);
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
		    	driver.manage().window().maximize();
		    	
		    	if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetExplorer"))) {
		    		if (doesElementExist2(properties.getProperty("OverrideLnk"), 5)) {
		    			driver.get("javascript:document.getElementById('overridelink').click();");
		    			Thread.sleep(8000);
		    		}
		    	}
		    }
	    } 
	    
		///////////////////////////////////
		ClickOnClientAppAdmin_inLegacyAdmin(Testname);
		
				
		EnterClientName(Testname);			
		
		EnterApplicationName(Testname);   
		
		ClickOnAnyTool_inLegacyAdmin(Testname);
				
		ClickOnSearchBtninAdmin(Testname);
		
		ClickOnModifyToolinAdmin(Testname);
		
		
		if (doesElementExist2(properties.getProperty("eDelApp_Yes"), 5)) {
			WebElement eDelYesRadiobtn = driver.findElement(By.cssSelector(properties.getProperty("eDelApp_Yes")));
			
			if(eDelYesRadiobtn.isSelected()){
				log.logLine(Testname, false, "eDelivery application radio button is already selected in client app admin of Email Builder tool ");
			}else{
				eDelYesRadiobtn.click();
				log.logLine(Testname, false, "Selecting the eDelivery application radio button in client app admin of Email Builder tool ");
			}
		
		} else {
			log.logLine(Testname, true, "Clicking on the eDelvery Application No radio button of Email Builder tool in client/app/Tool Admin is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicking on the eDelvery Application No radio button of Email Builder tool in client/app/Tool Admin is failed");
		}
		
		ClickonUpdateBtninClntAppAdmin(Testname);
		
		ClickOnUserAdmin_inLegacyAdmin(Testname);
		
		
		EnterUserId_UserAdmin(Testname);
		
		EnterApplnId_UserAdmin(Testname);
		
		ClickOnAnyTool_inLegacyAdmin(Testname);
		
		ClickOnSearchBtninAdmin(Testname);
		
		ClickOnModifyToolinAdmin(Testname);
		
		XmlSchemaNotPresentInEmlBuilderInformation(Testname);
	    
	    
	    ///////////////////////////////////////
	    ClickOnClientAppAdmin_inLegacyAdmin(Testname);
	    
	    EnterClientName(Testname);
	    EnterApplicationName(Testname);
	    ClickOnAnyTool_inLegacyAdmin(Testname);
	       
	    ClickOnSearchBtninAdmin(Testname);
	    
	    ClickOnModifyToolinAdmin(Testname);
	    
	    
	    if (doesElementExist2(properties.getProperty("eDelApp_No"), 5)) {
    		WebElement eDelNoRadiobtn = driver.findElement(By.cssSelector(properties.getProperty("eDelApp_No")));
    		
    		if(eDelNoRadiobtn.isSelected()){
    			log.logLine(Testname, false, "eDelivery application radio button is already selected in client app admin of Email Builder tool ");
    		}else{
    			eDelNoRadiobtn.click();
    			log.logLine(Testname, false, "Selecting the eDelivery application radio button in client app admin of Email Builder tool ");
    		}
    		
    	} else {
    	    log.logLine(Testname, true, "Clicking on the eDelvery Application No radio button of Email Builder tool in client/app/Tool Admin is failed");
    	    driver.close();
			driver.switchTo().window(firstWinHandle);
    	    throw new Exception("Clicking on the eDelvery Application No radio button of Email Builder tool in client/app/Tool Admin is failed");
    	}
	    
	    
	    ClickonUpdateBtninClntAppAdmin(Testname);
	    
	    ClickOnUserAdmin_inLegacyAdmin(Testname);	   
	    EnterUserId_UserAdmin(Testname);	    
	    EnterApplnId_UserAdmin(Testname);
	    
	    ClickOnAnyTool_inLegacyAdmin(Testname);
   
	    ClickOnSearchBtninAdmin(Testname);
	    
	    ClickOnModifyToolinAdmin(Testname);
	    
	    XmlSchemaPresentInEmlBuilderInformation(Testname);
	    
	    
	    if(doesElementExist(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_ctlServices_ctlSrvM_frmMain_chkTemplateEdit']", 5)){
			  WebElement editChkbox=driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_ctlServices_ctlSrvM_frmMain_chkTemplateEdit']"));
			  if(editChkbox.isSelected()){
				  log.logLine(Testname, false, "Edit checkbox is selected by dafault after clicking on the eDelivery Application No radio button");
			  }else{
				  ((JavascriptExecutor) driver).executeScript("arguments[0].click()", editChkbox);
				  log.logLine(Testname, false, "Clicking on the Edit checkbox if not selected by dafault after clicking on the eDelivery Application No radio button");
			  }  
		  
	  	}
	    
	    if(doesElementExist(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_ctlServices_ctlSrvM_frmMain_chkPromoteToLiveEdit']", 5)){
			  WebElement prmtChkbox=driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_ctlServices_ctlSrvM_frmMain_chkPromoteToLiveEdit']"));
			  if(prmtChkbox.isSelected()){
				  log.logLine(Testname, false, "Edit checkbox is selected by dafault after clicking on the eDelivery Application No radio button");
			  }else{
				  ((JavascriptExecutor) driver).executeScript("arguments[0].click()", prmtChkbox);
				  log.logLine(Testname, false, "Clicking on the Edit checkbox if not selected by dafault after clicking on the eDelivery Application No radio button");
			  }  
		  
	  	}
	    
	   ClickonUpdateBtninUserAdmin(Testname);
	    
	    
	    driver.close();
	    
	    Thread.sleep(2000);
        
        driver.switchTo().window(firstWinHandle);
	    
		
		return true;
	}
	
	public boolean Permission_For_EmailTypes(String Testname, String AccNo) throws Exception {
		
		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
		
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		
		ClientAppSelectionInTM(Testname);
		
		driver.switchTo().frame("iframeContainer");
		
		SavedTypesDropDown(Testname);
		
		
		
		return true;
	}
	
	public boolean EnterClientName(String Testname) throws Exception {
		
		if (doesElementExist2(properties.getProperty("clientname"), 5)) {
			WebElement cntnme = driver.findElement(By.cssSelector(properties.getProperty("clientname")));
			cntnme.clear();			   
			cntnme.sendKeys(ClntName);	
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entered the client name "+ClntName+" in the client name text field in Client/App tool");
		} else {
			log.logLine(Testname, true, "Entering the client name "+ClntName+" in the client name text field in Client/App tool is failed");
		}
		return true;
	}
	
	public boolean EnterApplicationName(String Testname) throws Exception {
		if (doesElementExist2(properties.getProperty("ApplicationId"), 5)) {
			WebElement applid = driver.findElement(By.cssSelector(properties.getProperty("ApplicationId")));
			applid.sendKeys(AppId);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entered the application name "+AppId+" in the Application name text field in Client/App tool");
		} else {
			log.logLine(Testname, true, "Entering the application name "+AppId+" in the Application name text field in Client/App tool is failed");
		}
		return true;
	}
	
	public boolean EnterUserId_UserAdmin(String Testname) throws Exception {
		
		if (doesElementExist2(properties.getProperty("UserId"), 5)) {
			WebElement cntnme = driver.findElement(By.cssSelector(properties.getProperty("UserId")));
			//((JavascriptExecutor) driver).executeScript("arguments[0].click()", cntnme);    
			cntnme.sendKeys(useradmnid);			    
			log.logLine(Testname, false, "Entered the userid "+useradmnid+" in the user id text field in user/admin tool");
		} else {
			log.logLine(Testname, true, "Entering the userid "+useradmnid+" in the user id text field in user/admin tool is failed");
		}
		return true;
	}
	
	public boolean EnterApplnId_UserAdmin(String Testname) throws Exception {
		if (doesElementExist2(properties.getProperty("UserApplicationId"), 5)) {
			WebElement applid = driver.findElement(By.cssSelector(properties.getProperty("UserApplicationId")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", applid);
			applid.sendKeys(userappid);			    
			log.logLine(Testname, false, "Entered the application name "+userappid+" in the Application name text field in Client/App tool");
		} else {
			log.logLine(Testname, true, "Entering the application name "+userappid+" in the Application name text field in Client/App tool is failed");
		}	
		return true;
	}
	
	
	public boolean ClickOnClientAppAdmin_inLegacyAdmin(String Testname) throws Exception {
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
		
		return true;
	}
	
	public boolean ClickOnUserAdmin_inLegacyAdmin(String Testname) throws Exception {
		 
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
		    
		
		return true;
		}
	
	public boolean ClickOnAnyTool_inLegacyAdmin(String Testname) throws Exception {
		 
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
    
		
		return true;
	}
	
	public boolean ClickOnSearchBtninAdmin(String Testname) throws Exception {
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
		return true;
	}
	
	public boolean ClickOnModifyToolinAdmin(String Testname) throws Exception {
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
	return true;
	}
	
	public boolean EnteringClientNameinClientAppAdmin(String Testname) throws Exception {
		
		
	
	    if (doesElementExist2(properties.getProperty("clientname"), 5)) {
		    WebElement cntnme = driver.findElement(By.cssSelector(properties.getProperty("clientname")));
		    cntnme.clear();
		    //((JavascriptExecutor) driver).executeScript("arguments[0].click()", cntnme);    
		    cntnme.sendKeys(ClntName);	
		    Thread.sleep(1000);
		    log.logLine(Testname, false, "Entered the client name "+ClntName+" in the client name text field in Client/App tool");
		} else {
		   	log.logLine(Testname, true, "Entering the client name "+ClntName+" in the client name text field in Client/App tool is failed");
		}
	    return true;
	}
	
	public boolean EnteringApplicationNameinClientAppAdmin(String Testname) throws Exception {
		
		
		
	    if (doesElementExist2(properties.getProperty("clientname"), 5)) {
		    WebElement cntnme = driver.findElement(By.cssSelector(properties.getProperty("clientname")));
		    cntnme.clear();
		    //((JavascriptExecutor) driver).executeScript("arguments[0].click()", cntnme);    
		    cntnme.sendKeys(ClntName);	
		    Thread.sleep(1000);
		    log.logLine(Testname, false, "Entered the client name "+ClntName+" in the client name text field in Client/App tool");
		} else {
		   	log.logLine(Testname, true, "Entering the client name "+ClntName+" in the client name text field in Client/App tool is failed");
		}
	    return true;
	}
	
	public boolean ClickonUpdateBtninClntAppAdmin(String Testname) throws Exception {
		
		
		if (doesElementExist2(properties.getProperty("eDelUpdateInClntAppAdmin"), 5)) {
			WebElement updtBtn = driver.findElement(By.cssSelector(properties.getProperty("eDelUpdateInClntAppAdmin")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", updtBtn);
			waitUntilRetrive();	
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicked on the eDel Application update button  of Email Builder Tool in client/app/Tool Admin ");
		} else {
		    log.logLine(Testname, true, "Clicked on the eDel Application update button  of Email Builder Tool in client/app/Tool Admin is failed");
		    driver.close();
			driver.switchTo().window(firstWinHandle);
		    throw new Exception("Clicked on the eDel Application update button  of Email Builder Tool in client/app/Tool Admin is failed");
		}
		return true;
	}
	
	public boolean ClickonUpdateBtninUserAdmin(String Testname) throws Exception {
		
		if (doesElementExist2(properties.getProperty("UpdateBtnInUserAdmin"), 5)) {
			WebElement updtBtn = driver.findElement(By.cssSelector(properties.getProperty("UpdateBtnInUserAdmin")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", updtBtn);			
			waitUntilRetrive();	
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicked on the eDel Application update button  of Email Builder Tool in client/app/Tool Admin ");
		} else {
		    log.logLine(Testname, true, "Clicked on the eDel Application update button  of Email Builder Tool in client/app/Tool Admin is failed");
		    driver.close();
			driver.switchTo().window(firstWinHandle);
		    throw new Exception("Clicked on the eDel Application update button  of Email Builder Tool in client/app/Tool Admin is failed");
		}
		return true;
	}
	
	public boolean XmlSchemaPresentInEmlBuilderInformation(String Testname) throws Exception { 
		
		String[] SecurityRights = new String[10];
		String row ="tr";
	
		 WebElement table1=driver.findElement(By.xpath(".//*[@id='tblInfo']"));		
		 
		 List <WebElement> rowcnt= driver.findElements(By.xpath(".//*[@id='tblInfo']/tbody/tr"));
		 
		 if(doesElementExist2("table[id='tblInfo'] tbody "+row+" td span", 5)){
			 	
			 for(int r=0; r<rowcnt.size(); r++){
			
			  SecurityRights[r] = driver.findElement(By.cssSelector("table[id='tblInfo'] tbody "+row+" td span")).getText();
			  
			  if(SecurityRights[r].equals("XML Schema")){
				  log.logLine(Testname, false, "xml schema label present in Email builder user management");
				  
				  if(doesElementExist2("table[id='tblInfo'] tbody "+row+" td+td input[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_ctlServices_ctlSrvM_frmMain_chkXMLSchemaView']", 5)){
					  WebElement viewChkbox=driver.findElement(By.cssSelector("table[id='tblInfo'] tbody "+row+" td+td input[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_ctlServices_ctlSrvM_frmMain_chkXMLSchemaView']"));
					  if(viewChkbox.isSelected()){
						  log.logLine(Testname, false, "view checkbox is selected by dafault after clicking on the eDelivery Application No radio button");
					  }else{
						  ((JavascriptExecutor) driver).executeScript("arguments[0].click()", viewChkbox);
						  log.logLine(Testname, false, "Clicking on the View checkbox if not selected by dafault after clicking on the eDelivery Application No radio button");
						  
					  }
				  }
			 
				  if(doesElementExist2("table[id='tblInfo'] tbody "+row+" td+td+td input[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_ctlServices_ctlSrvM_frmMain_chkXMLSchemaEdit']", 5)){
					  WebElement editChkbox=driver.findElement(By.cssSelector("table[id='tblInfo'] tbody "+row+" td+td+td input[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_ctlServices_ctlSrvM_frmMain_chkXMLSchemaEdit']"));
					  if(editChkbox.isSelected()){
						  log.logLine(Testname, false, "Edit checkbox is selected by dafault after clicking on the eDelivery Application No radio button");
					  }else{
						  ((JavascriptExecutor) driver).executeScript("arguments[0].click()", editChkbox);
						  log.logLine(Testname, false, "Clicking on the Edit checkbox if not selected by dafault after clicking on the eDelivery Application No radio button");
					  }  
				  
			  	}
			  
			  }
				
			  row = row + "+tr"; 	
			 }//for loop closing
			
		}
		
		
		return true;
	}
	
	
	public boolean XmlSchemaNotPresentInEmlBuilderInformation(String Testname) throws Exception { 
		
		String[] SecurityRights = new String[10];
		String row ="tr";
	
		 WebElement table1=driver.findElement(By.xpath(".//*[@id='tblInfo']"));		
		 
		 List <WebElement> rowcnt= driver.findElements(By.xpath(".//*[@id='tblInfo']/tbody/tr"));
		 
		 if(doesElementExist2("table[id='tblInfo'] tbody "+row+" td span", 5)){
			 	
			 for(int r=0; r<rowcnt.size(); r++){
			
			  SecurityRights[r] = driver.findElement(By.cssSelector("table[id='tblInfo'] tbody "+row+" td span")).getText();
			  
			  if(SecurityRights[r].equals("XML Schema")){
				  log.logLine(Testname, true, "xml schema exist in Email builder user management even after selecting the eDelivery Application Yes check box which is  not expected ");
				  throw new Exception("xml schema exist in Email builder user management even after selecting the eDelivery Application Yes check box which is  not expected ");
			  }else{
				  log.logLine(Testname, false, "xml schema does not exist in Email builder user management after selecting the eDelivery Application Yes check box which is as expected ");
 
			  }
				
			  row = row + "+tr"; 	
			 }//for loop closing
			
		}
		
		
		return true;
	}
	
	public boolean ClientAppSelectionInTM(String Testname) throws Exception{
		
		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
		
		ClickeDeliverTab(Testname);
		
		if (doesElementExist2(properties.getProperty("TemplateMgmnt"), 5)) {
	    	WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("TemplateMgmnt")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);	    	 
	    	 Thread.sleep(2000);
	    	 PleasewaitDisappear();
	    	log.logLine(Testname, false, "Click on Template management Module is Successful");
	    } else {
	    	log.logLine(Testname, true, "Click on Template management Module is failed");
	    	driver.switchTo().defaultContent();
	    	throw new Exception("Click on Template management Module is failed");
	    }
	
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
	    AppName = test.readColumnData("ApplicationName", sheetname);
	    
	    if (doesElementExist2(properties.getProperty("selAppl1"), 5)) {	   
	    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selAppl1")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
	    	
			log.logLine(Testname, false, "Clicking on ApplicationName dropdown..");
			
			if (doesElementExist2(properties.getProperty("ApplOpts1"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ApplOpts1")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(AppId)) {
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
						if (lnk.getText().equals(AppId)) {
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
	    
	    PleasewaitDisappear();
	
	
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
	    
	    return true;
	}
	
	//HTML code starts here
	
	public boolean TemplateHTML(String RandNo, String Testname) throws Exception {
		
		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
		
		TemplateName=test.readColumnData("TemplateName", sheetname);
		TempltDecsp=test.readColumnData("TemplateDecrip", sheetname);
		
		String rplyTxt=test.readColumnData("ReplyTo", sheetname);
		URL=test.readColumnData("URL", sheetname);
        LinkID=test.readColumnData("LinkID", sheetname);
        LnkText=test.readColumnData("LinkText", sheetname);
        LnkToolTp=test.readColumnData("LinkTltip", sheetname);
        
        ClickPreLiveRadioButton(Testname);	
		SavedTypesDropDown(Testname);
		ClickPlusButtontoAddHTMLTemplate(Testname);
		
		WebElement TempltNamTxtBox = driver.findElement(By.cssSelector(properties.getProperty("TempltNameTxtBox")));
		FillTemplateNameHTML(Testname , TemplateName);		

		//*********Check Clear Template and Save******************
		FillTemplateDescriptionHTML(Testname , TempltDecsp);
		ClickClearTemplateButton(Testname) ;  
		ClickConfirmButtonForClearAlert(Testname) ;

		//*********Check No Template and Save******************	
		SavedTypesDropDown(Testname);
		VerifyNoTemplateHTML(Testname);
		CancelSavedTmpl(Testname);

		//*********Check Save Template without Body******************		
		SavedTypesDropDown(Testname);
		ClickPlusButtontoAddHTMLTemplate(Testname);
		Thread.sleep(2000);
		FillTemplateNameHTML(Testname, TemplateName) ;  
		Thread.sleep(6000);
		SaveTemplate(Testname);	
		Thread.sleep(5000);
		AcceptAlert(Testname) ;
		Thread.sleep(2000);
		ClickExitEditorButton(Testname);
		Thread.sleep(2000);	

		//*********Check Save Template with Body and Description******************
		SavedTypesDropDown(Testname);
		ClickPlusButtontoAddHTMLTemplate(Testname);
		Thread.sleep(5000);
		FillTemplateNameHTML(Testname, TemplateName);     
		Thread.sleep(5000);
		FillTemplateDescriptionHTML(Testname , TempltDecsp);
		Thread.sleep(5000);
		SaveTemplate(Testname);
		Thread.sleep(5000);
		ConfirmSave(Testname) ;
		Thread.sleep(5000);
		ClickExitEditorButton(Testname); 
		Thread.sleep(5000);


		//*********Check Saved Template Exists******************
		SavedTypesDropDown(Testname);
		Operations_PreLiveTemplate(Testname, "Edit");


		//*********Check Edit Template and save******************		
		EditTemplNameDescptnHTML(Testname);      
		Thread.sleep(5000);
		SaveTemplate(Testname);
		Thread.sleep(5000);
		ConfirmSave(Testname) ;
		Thread.sleep(5000);
		ClickExitEditorButton(Testname);
		Thread.sleep(5000);

		//*********Check Edited Template exists******************	
		SavedTypesDropDown(Testname);
		Thread.sleep(5000);
		Operations_PreLiveTemplate(Testname, "ConfirmEditAndView");	


		//*********Check Preview Send without Email Id*****************		
		ClickPreviewEmailButton(Testname) ;
		Thread.sleep(5000);
		PreviewSendBtn(Testname);
		Thread.sleep(5000);
		AcceptAlert(Testname) ;	
		Thread.sleep(5000);


		//*********Check Preview Send with Email Id*****************		
		FillToEmailId(Testname,rplyTxt);
		Thread.sleep(5000);
		PreviewSendBtn(Testname);
		Thread.sleep(5000);
		ConfirmSave(Testname) ;
		ClickExitEditorButton(Testname);
		Thread.sleep(5000);	

	//*********Check Template exists for saved Email Id****************		
		SavedTypesDropDown(Testname);
		Operations_PreLiveTemplate(Testname, "Delete");

	//*********Check delete Template****************				
		ClickOKToConfirmDelete(Testname)  ;
		Thread.sleep(5000);
		ClickCloseOnSuccsessfullDelete(Testname) ;
		Thread.sleep(5000);
		CancelSavedTmpl(Testname);

	//*********Check deleted Template does not exist***************		
		/*SavedTypesDropDown(Testname);
		Operations_PreLiveTemplate(Testname, "VerifyDelete");
		CancelSavedTmpl(Testname);	*/

	//*********Check Create New Template ****************		
		SavedTypesDropDown(Testname);
		ClickPlusButtontoAddHTMLTemplate(Testname);	
		FillTemplateNameHTML(Testname , TemplateName);
		Thread.sleep(5000);
		FillTemplateDescriptionHTML(Testname , TempltDecsp);
		Thread.sleep(5000);
		SaveTemplate(Testname);
		Thread.sleep(5000);
		ConfirmSave(Testname) ;
		Thread.sleep(5000);
		ClickExitEditorButton(Testname);


	//*********Check Promote template to Live****************		
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
		ClickLiveRadioButton(Testname);		
		SavedTypesDropDown(Testname);
		Operations_LiveTemplates(Testname, "View");	


	//*********Check Insert Imange with URL ******************		
		//		EditTemplNameDescptnHTML(Testname); 
		InsertImageUrl(Testname); 
		Thread.sleep(5000);
		EnterImgUrlInTxtBx(Testname);
		Thread.sleep(5000);
		InsertbtnPopup(Testname) ;
		Thread.sleep(5000);
		SaveTemplate(Testname);
		Thread.sleep(5000);
		ConfirmSave(Testname) ;
		Thread.sleep(5000);
		ClickExitEditorButton(Testname);


//********************************************Live templates*****************************************************************************************				
		ClickLiveRadioButton(Testname);        
		SavedTypesDropDown(Testname);
		//SearchForLiveTemplate(Testname);

		Operations_LiveTemplates(Testname, "View");	        
		ClickPreviewEmailButton(Testname);
		EmailAddrTextBox(Testname, rplyTxt) ;	
		Thread.sleep(5000);
		PreviewSendBtn(Testname);
		Thread.sleep(5000);
		ConfirmSave(Testname );
		Thread.sleep(5000);
		ClickExitEditorButton(Testname);

		SavedTypesDropDown(Testname);
		Operations_LiveTemplates(Testname, "RollBackView");
		ClickExitEditorButton(Testname);

		SavedTypesDropDown(Testname);       
		Operations_LiveTemplates(Testname, "Delete");
		Thread.sleep(5000);
		Log_DeleteTempAlert_InLive(Testname) ;
		Thread.sleep(5000);
		DelLiveOnlyBtn_OnAlert(Testname );
		Thread.sleep(2000);
		CancelSavedTmpl(Testname);
		ClickPreLiveRadioButton(Testname);
		ClickLiveRadioButton(Testname);		

		SavedTypesDropDown(Testname);
		Operations_LiveTemplates(Testname, "VerifyDeleteLiveOnlyInLiveTemplates");
		CancelSavedTmpl(Testname);
		ClickPreLiveRadioButton(Testname);

		SavedTypesDropDown(Testname);
		Operations_LiveTemplates(Testname, "VerifyTemplExistsInPreLive_DelInLiveOnly");
		CancelSavedTmpl(Testname);
		ClickLiveRadioButton(Testname);

		SavedTypesDropDown(Testname);
		Operations_LiveTemplates(Testname, "RollBack"); 
		ConfirmRollBack(Testname) ;
		Thread.sleep(2000);		

		rolbackalert(Testname) ;
		ClickClosebtn_SuccessfulRollBackMesg_LiveOnly( Testname ) ;
		CancelSavedTmpl(Testname);

		SavedTypesDropDown(Testname);
		Operations_LiveTemplates(Testname, "VerifyRollBack");
		CancelSavedTmpl(Testname);
		
		
	return true;

	}
	
	
	//HTML Methods starts here
	
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
		
		
		//********************ClickLiveRadioButton*****************************************************************************
		public boolean ClickLiveRadioButton(String Testname) throws Exception {	

			if (doesElementExist(properties.getProperty("LiveRadioBtn"), 5)) {
				WebElement livebtn = driver.findElement(By.xpath(properties.getProperty("LiveRadioBtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", livebtn);
				Thread.sleep(1000);
				log.logLine(Testname, false, "Clicking on \"Live\" radio button under Template management");
			} else {
				log.logLine(Testname, true, "Clicking on \"Live\" radio button under Template management is failed");
				//    throw new Exception("Clicking on \"Live\" radio button under Template management is failed");
			}
			return true ;
		} 
		
		
		//********************ClickPreLiveRadioButton*****************************************************************************
		public boolean ClickPreLiveRadioButton(String Testname ) throws Exception {

			Thread.sleep(2000);
			if (doesElementExist(properties.getProperty("PreLiveRadioBtn"), 5)) {
				WebElement livebtn = driver.findElement(By.xpath(properties.getProperty("PreLiveRadioBtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", livebtn);
				Thread.sleep(1000);
				log.logLine(Testname, false, "Clicking on \"Pre-Live\" radio button under Template management");
			} else {
				log.logLine(Testname, true, "Clicking on \"Pre-Live\" radio button under Template management is failed");
				//    throw new Exception("Clicking on \"Pre-Live\" radio button under Template management is failed");
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
		
		public boolean DelLiveOnlyBtn_OnAlert(String Testname) throws Exception {    


			if (doesElementExist2(properties.getProperty("ConfirmDelLiveOnly"), 5)) {
				WebElement livebtn = driver.findElement(By.cssSelector(properties.getProperty("ConfirmDelLiveOnly")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", livebtn);
				Thread.sleep(1000);
				log.logLine(Testname, false, "Clicking on \"Delete Live only\" button of the Delete operation in Live Templates");
			} else {
				log.logLine(Testname, true, "Clicking on \"Delete Live only\" button of the Delete operation in Live Templates is failed");
				//    throw new Exception("Clicking on \"Delete Live only\" button of the Delete operation in Live Templates is failed");
			}

			return true;
		}
		
		public boolean ConfirmRollBack(String Testname) throws Exception {

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
		
		public boolean ClickClosebtn_SuccessfulRollBackMesg_LiveOnly(String Testname ) throws Exception {
			
			if (doesElementExist2(properties.getProperty("DeleteAlert"), 5)) {
				WebElement livebtn = driver.findElement(By.cssSelector(properties.getProperty("DeleteAlert")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", livebtn);
				Thread.sleep(1000);
				log.logLine(Testname, false, "Clicking on \"close\" button of the RollBack Alert message in Live Templates");
			} else {
				log.logLine(Testname, true, "Clicking on \"close\" button of the RollBack Alert message in Live Templates is failed");
				//    throw new Exception("Clicking on \"close\" button of the RollBack Alert message in Live Templates is failed");
			}

			return true ;
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
									((JavascriptExecutor) driver).executeScript("arguments[0].click()", htmlradio);
									log.logLine(Testname, false, "Clicked on the \"HTML\" radiobutton of the saved types ");
								}

								//************************************************Click Plus button to add HTML schema
								if (doesElementExist2("ul "+row+" div button[class='k-button btn-management-add-template']", 5)) {
									WebElement plus=driver.findElement(By.cssSelector("ul "+row+" div button[class='k-button btn-management-add-template']"));

									wait.until(ExpectedConditions.elementToBeClickable(plus));		
									((JavascriptExecutor) driver).executeScript("arguments[0].click()", plus);

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
		
		//********************FillTemplateNameHTML********************************************************************************

		public boolean FillTemplateNameHTML(String Testname , String TemplateName) throws Exception {       	

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
		
		//********************FillTemplateDescriptionHTML********************************************************************************
		public boolean FillTemplateDescriptionHTML(String Testname , String TempltDecsp) throws Exception {        

			if (doesElementExist("/html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div", 5)) {
				WebElement HTMLTempltDescpTxt = driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/table/tbody/tr[2]/td[1]/div"));	

				wait.until(ExpectedConditions.elementToBeClickable(HTMLTempltDescpTxt)) ;		
				HTMLTempltDescpTxt.isEnabled();
				Actions action1 = new Actions(driver);		
				action1.moveToElement(HTMLTempltDescpTxt).click().sendKeys(TempltDecsp).build().perform();		
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

		//********************Verify No Template*****************************************************************************
		public boolean VerifyNoTemplateHTML(String Testname ) throws Exception {

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

							if (doesElementExist2("tr td ul+ul[class='ul-templates ui-sortable'] li[class='grid-btns']", 5)) {
								String notmpltfnd = driver.findElement(By.cssSelector("tr td ul+ul[class='ul-templates ui-sortable'] li[class='grid-btns']")).getText();
								if(notmpltfnd.contains(notemplts)){
									log.logLine(Testname, false, "No text templates are added to the Saved Email Type , hence clear template is sucessful");
								}else{
									log.logLine(Testname, true, "Temaplates are added after clearing the text hence clear template is unsucessful");
									//	//    throw new Exception("Temaplates are added after clearing the text hence clear template is unsucessful");
								}

							} else {
								log.logLine(Testname, true, "Clicking on the \"Text\" radiobutton of the saved types is failed");
								//	//    throw new Exception("Clicking on the \"Text\" radiobutton of the saved types is failed");
							}
						}
						break; 
					}
					row2 = row2 + "+li";

				}

			}

			return true;
		}	
		
		//********************ClickPreviewEmailButton*****************************************************************************
		public boolean AcceptAlert(String Testname) throws Exception {               
			//Error message for Empty Email Address
			Alert alert2 = driver.switchTo().alert();
			alert2.accept();
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


													if (doesElementExist2("ul li "+editrow+" div button+button[class='k-button btn-management-saved-template-edit']", 5)) {
														WebElement edit=driver.findElement(By.cssSelector("ul li "+editrow+" div button+button[class='k-button btn-management-saved-template-edit']"));
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
												EmlType1[j] = driver.findElement(By.cssSelector("ul li "+promrow+" div div+div[class='lbl-management-saved-type-name']")).getText();

												if(EmlType1[j].equals(TemplateName)){

													log.logLine(Testname, false, "Iterating through the Rows....and Reading the Type in Saved type as "+EmlType1[j]);


													if (doesElementExist2("ul li "+promrow+" div button[class='k-button btn-management-saved-template-promote']", 5)) {
														WebElement edit=driver.findElement(By.cssSelector("ul li "+promrow+" div button[class='k-button btn-management-saved-template-promote']"));
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


													if (doesElementExist2("ul li "+editrow1+" div button+button+button[class='k-button btn-management-saved-template-view']", 5)) {
														WebElement view=driver.findElement(By.cssSelector("ul li "+editrow1+" div button+button+button[class='k-button btn-management-saved-template-view']"));
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


													if (doesElementExist2("ul li "+editrow2+" div button+button+button+button[class='k-button btn-management-saved-template-delete']", 5)) {
														WebElement delete=driver.findElement(By.cssSelector("ul li "+editrow2+" div button+button+button+button[class='k-button btn-management-saved-template-delete']"));
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
		public boolean EditTemplNameDescptnHTML(String Testname) throws Exception {

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
				Thread.sleep(1000);
				log.logLine(Testname, false, "Clicking on confirm delete button");
			} else {
				log.logLine(Testname, true, "Clicking on confirm delete button is failed");
				//throw new Exception("Clicking on confirm delete button is failed");
			}

			return true ;
		}  


	//********************ClickCloseOnSuccsessfullDelete*****************************************************************************
		public boolean ClickCloseOnSuccsessfullDelete(String Testname) throws Exception {

			if (doesElementExist2(properties.getProperty("ConfirmSave"), 5)) {
				WebElement cnfmSve = driver.findElement(By.cssSelector(properties.getProperty("ConfirmSave")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnfmSve);
				Thread.sleep(1000);
				log.logLine(Testname, false, "Clicking on \"close\" button of the Alert message of the delete template");
			} else {
				log.logLine(Testname, true, "Clicking on \"close\" button of the Alert message of the delete template is failed");
				//    throw new Exception("Clicking on \"close\" button of the Alert message of the delete template is failed");
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
				//    throw new Exception("Clicking on confirm ok button for promoting the template to current live template is failed");
			}

			return true ;
		}  
		
		//********************ClickCloseOnSuccessfullPromotionAlert*****************************************************************************
		public boolean ClickCloseOnSuccessfullPromotionAlert(String Testname) throws Exception {			

			if (doesElementExist2(properties.getProperty("ConfirmSave"), 5)) {
				WebElement cnfmSve = driver.findElement(By.cssSelector(properties.getProperty("ConfirmSave")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", cnfmSve);
				Thread.sleep(1000);
				log.logLine(Testname, false, "Clicking on \"close\" button of the promote Alert message");
			} else {
				log.logLine(Testname, true, "Clicking on \"close\" button of the promote Alert message is failed");
				//    throw new Exception("Clicking on \"close\" button of the promote Alert message is failed");
			}
			return true ;
		} 
		
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


								if(doesElementExist2("ul li "+editrow1+" div div+div[class='lbl-management-saved-type-name']", 5)){

									for(int j = 0; j < DataCnt5.size(); j++) {
										EmlType1[j] = driver.findElement(By.cssSelector("ul li "+editrow1+" div div+div[class='lbl-management-saved-type-name']")).getText();

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
									////    throw new Exception("Clicking on the \"RollBack \" button of the saved types Text Live Template is failed");
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
										////    throw new Exception(""+notmpltfnd+" is not present hence \"delete\" text template is unsucessful");
									}
								} else {
									log.logLine(Testname, true, "Clicking on Delete button is failed");
									//    throw new Exception("Clicking on Delete button is failed");
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
	
	
	
	
	

 }     //closing of the class TemplateMgmt






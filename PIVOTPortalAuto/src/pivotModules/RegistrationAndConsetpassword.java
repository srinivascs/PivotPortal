package pivotModules;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import launchAuto.CreateAppData;
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


public class RegistrationAndConsetpassword extends Page{
	
	public RegistrationAndConsetpassword(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}	
	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}
	
	
	public String ClientAppSettings(String AccNo, String Testname, String CheckClnt) throws Exception {
	
		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
	    
	    Thread.sleep(1000);
	    driver.switchTo().defaultContent();	    
	    
	    if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
	    } else {
			log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");
			//throw new Exception("Clicking on Cancel button in Client/App popup is failed");
		}

	    
	    if ((doesElementExist2(properties.getProperty("HAadmin"), 5))) {			    
			WebElement oldpivt = driver.findElement(By.cssSelector(properties.getProperty("HAadmin")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", oldpivt);
		
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicking on Legacy Admin..");		      
	    } else {
			log.logLine(Testname, true, "Clicking on HA admin is failed");
			throw new Exception("Clicking on HA admin is failed");
		}
	    
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
		    	driver.manage().window().maximize();
		    	
		    	// Wait till the page loads all the elements in it.
				WebElement retelm2 = waitForElement(properties.getProperty("ProfileAdmin"));				
				Actions builder = new Actions(driver);			
				
				
				//Verify User permission
				if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSUPER")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSITE")) 
						|| (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDCLIENT"))) {					
				
					if (doesElementExist(properties.getProperty("Clientapplnk"), 5)) {						
						log.logLine(Testname, false, "Permission Verified: RRD Super, RRD Site & RRD Client users have access to Client/App in Legacy Admin");
						
					} else {
						
						log.logLine(Testname, true, "Access denied - RRD Super, RRD Site & RRD Client users does not have access to Client/App in Legacy Admin");
						driver.close();
						driver.switchTo().window(firstWinHandle);
						return "";
					}
				} else if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTUSER")) || 
						(PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTADMIN")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDUSER"))) {
					
					if (doesElementExist(properties.getProperty("Clientapplnk"), 5)) {
						
						log.logLine(Testname, true, "RRD User, Client Admin & Client User should not have access to Client/App in Legacy Admin");
						driver.close();
						driver.switchTo().window(firstWinHandle);
						throw new Exception("RRD User, Client Admin & Client User should not have access to Client/App in Legacy Admin");
						
					} else {
						
						log.logLine(Testname, false, "Permission Verified: Client Admin, Client User & RRD User does not have permission to access to Client/App in Legacy Admin");
						driver.close();
						driver.switchTo().window(firstWinHandle);
						return ""; 
					}
					
				}
				
				
				if (Initialization.Browser.equalsIgnoreCase("ie") || Initialization.Browser.equalsIgnoreCase("ie")) {
					driver.get("javascript:document.getElementById('overridelink').click();");
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
				
				WebElement retelm9 = waitForElement(properties.getProperty("AppNamefld"));
				log.logLine(Testname, false, "AppNamefld is found on the page..");
				
				
				//Move the mouse on Welcome text to avoid blinking
				if (doesElementExist2(properties.getProperty("HelloUserName"), 5)) {
					WebElement hellolbl = driver.findElement(By.cssSelector(properties.getProperty("HelloUserName")));
					builder.moveToElement(hellolbl).perform();
					Thread.sleep(1000);
				}
				
				String ClntName = "";
				if (CheckClnt.equalsIgnoreCase("Registration")) {
					ClntName = test.readColumnData("RegClientName", sheetname);
				}else if (CheckClnt.equalsIgnoreCase("PasswordReset")) {
					ClntName = test.readColumnData("PasswdClientName", sheetname);
				}
								
			    if (doesElementExist2(properties.getProperty("clientname"), 5)) {
				    WebElement cntnme = driver.findElement(By.cssSelector(properties.getProperty("clientname")));

				    cntnme.clear();    
				    cntnme.sendKeys(ClntName);	
				    Thread.sleep(1000);
				    log.logLine(Testname, false, "Entered the client name "+ClntName+" in the client name text field in Client/App tool");
				} else {
				   	log.logLine(Testname, true, "Entering the client name "+ClntName+" in the client name text field in Client/App tool is failed");
				}		    
			   
			    
			    //clicking on search button	    
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
			    
			    
			    if (doesElementExist2(properties.getProperty("ModifyClient"), 5)) {
		    		WebElement modtool = driver.findElement(By.cssSelector(properties.getProperty("ModifyClient")));
		    		((JavascriptExecutor) driver).executeScript("arguments[0].click()", modtool);
		    		
		    		waitUntilRetrive();
		    		Thread.sleep(1000);
		    		log.logLine(Testname, false, "Clicked on the ModifyTool button in client/app/Tool Admin ");
		    	} else {
		    	    log.logLine(Testname, true, "Clicking on the ModifyTool button in client/app/Tool Admin is failed");
		    	    driver.close();
					driver.switchTo().window(firstWinHandle);
		    	    throw new Exception("Clicking on the ModifyTool button in client/app/Tool Admin is failed");
		    	}
		    	
		    }
	    }   	
	    
	    if (doesElementExist2(properties.getProperty("eDeliveryConf"), 5)) {
		    WebElement eDelspecidata = driver.findElement(By.cssSelector(properties.getProperty("eDeliveryConf")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelspecidata);
		    Thread.sleep(1000);
		   	log.logLine(Testname, false, "Clicked on the eDelivery Configuration tab");
		} else {
	    	log.logLine(Testname, true, "Clicking on the eDelivery Configuration tab is failed");
	    	driver.close();
			driver.switchTo().window(firstWinHandle);
	    	throw new Exception("Clicking on the eDelivery Client Overrides tab under view pivot information is failed");
		}
	    
		if (doesElementExist(properties.getProperty("AuthenticationConf"), 5)) {
		    WebElement edeldataedit = driver.findElement(By.xpath(properties.getProperty("AuthenticationConf")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", edeldataedit);
		    
		    Thread.sleep(1000);
		   	log.logLine(Testname, false, "Clicked on the Authentication Configuration sub menu under eDelivery configuration");
		} else {
	    	log.logLine(Testname, true, "Clicked on the Authentication Configuration sub menu under eDelivery configuration is failed");
	    	driver.close();
			driver.switchTo().window(firstWinHandle);
	    	throw new Exception("Clicked on the Authentication Configuration sub menu under eDelivery configuration is failed");
		}
		
		return firstWinHandle;
		
	}
	
	
	public String RegistrationReq(String Testname, String firstWinHandle) throws Exception {
		
		
		if (doesElementExist2(properties.getProperty("ChkRegistrationReq"), 5)) {
		    WebElement CheckboxPassword = driver.findElement(By.cssSelector(properties.getProperty("ChkRegistrationReq")));
		    
		    //Check if the checkbox is enabled or not
		    Boolean checked = isAttribtuePresent(CheckboxPassword, "checked");
		    if (checked){
		    	log.logLine(Testname, false, "Registration Checkbox is enabled, hence Its not editing");		    	
		    	return "yes";		    	
		    } else {
		    	
		    	log.logLine(Testname, false, "Registration Checkbox is not enabled, hence Its editing the page");
		    	
		    	if (doesElementExist2(properties.getProperty("EditBtn"), 5)) {	    	
				    WebElement eDitbtn = driver.findElement(By.cssSelector(properties.getProperty("EditBtn")));				    
				    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDitbtn);
		    		
				    waitUntilRetrive();			
		    		log.logLine(Testname, false, "Clicked on the edit button in Authentication Configuration sub menu");
				    Thread.sleep(1000);
				    
				    //Enable the check box
				    if (doesElementExist2(properties.getProperty("EnableRegistrationChk"), 5)) {
					    WebElement CheckEnable = driver.findElement(By.cssSelector(properties.getProperty("EnableRegistrationChk")));
					    
					    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", CheckEnable);
					    log.logLine(Testname, false, "Enabled Registration Required check box");
				    } else {
				    	log.logLine(Testname, true, "Enabling Registration Required check box is failed");
				    	driver.close();
						driver.switchTo().window(firstWinHandle);
				    	throw new Exception("Enabling Registration Required check box is failed");
					}
				    
				    //Update the Check box
				    if (doesElementExist2(properties.getProperty("UpdateBtn"), 5)) {
					    WebElement UpdteBtn = driver.findElement(By.cssSelector(properties.getProperty("UpdateBtn")));
					    
					    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", UpdteBtn);
					    waitUntilRetrive();			
					    Thread.sleep(1000);
					    log.logLine(Testname, false, "Clicked on update button");
				    } else {
				    	log.logLine(Testname, true, "Clicking on update button is failed");
				    	driver.close();
						driver.switchTo().window(firstWinHandle);
				    	throw new Exception("Clicking on update button is failed");
					}
				    
				   	
				} else {
					log.logLine(Testname, true, "Clicking on the edit button in Authentication Configuration sub menu is failed");
					driver.close();
					driver.switchTo().window(firstWinHandle);
			    	throw new Exception("Clicking on the edit button in Authentication Configuration sub menu is failed");
				}
		    	
		    }				   	
		} else {
	    	log.logLine(Testname, true, "Registraion Checkbox may not exist in the page");
	    	driver.close();
			driver.switchTo().window(firstWinHandle);
	    	throw new Exception("Registraion Checkbox may not exist in the page");
		}		
		
		
		return "yes";		
	}

		
	
	public String DontPromptPasswd(String Testname, String firstWinHandle)  throws Exception {
		
		
		if (doesElementExist2(properties.getProperty("ChkPassword"), 5)) {
		    WebElement CheckboxPassword = driver.findElement(By.cssSelector(properties.getProperty("ChkPassword")));
		    
		    //Check if the checkbox is enabled or not
		    Boolean checked = isAttribtuePresent(CheckboxPassword, "checked");
		    if (checked){
		    	log.logLine(Testname, false, "Do not Prompt for password Checkbox is enabled, hence Its not editing");		    	
		    	return "yes";
		    	
		    } else {
		    	log.logLine(Testname, false, "Do not Prompt for password Checkbox is not enabled, hence Its editing the page");
		    	
		    	if (doesElementExist2(properties.getProperty("EditBtn"), 5)) {	    	
				    WebElement eDitbtn = driver.findElement(By.cssSelector(properties.getProperty("EditBtn")));				    
				    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDitbtn);
		    		
				    waitUntilRetrive();			
		    		log.logLine(Testname, false, "Clicked on the edit button in Authentication Configuration sub menu");
				    Thread.sleep(1000);
				    
				    //Enable the check box
				    if (doesElementExist2(properties.getProperty("EnablePasswdChk"), 5)) {
					    WebElement CheckEnable = driver.findElement(By.cssSelector(properties.getProperty("EnablePasswdChk")));
					    
					    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", CheckEnable);
					    log.logLine(Testname, false, "Enabled Don't prompt for new password upon first login check box");
				    } else {
				    	log.logLine(Testname, true, "Enabl Don't prompt for new password upon first login check box is failed");
				    	driver.close();
						driver.switchTo().window(firstWinHandle);
				    	throw new Exception("Enabled Don't prompt for new password upon first login check box is failed");
					}
				    
				    //Update the Check box
				    if (doesElementExist2(properties.getProperty("UpdateBtn"), 5)) {
					    WebElement UpdteBtn = driver.findElement(By.cssSelector(properties.getProperty("UpdateBtn")));
					    
					    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", UpdteBtn);
					    waitUntilRetrive();			
					    Thread.sleep(1000);
					    log.logLine(Testname, false, "Clicked on update button");
				    } else {
				    	log.logLine(Testname, true, "Clicking on update button is failed");
				    	driver.close();
						driver.switchTo().window(firstWinHandle);
				    	throw new Exception("Clicking on update button is failed");
					}
				    
				   	
				} else {
					log.logLine(Testname, true, "Clicking on the edit button in Authentication Configuration sub menu is failed");
					driver.close();
					driver.switchTo().window(firstWinHandle);
			    	throw new Exception("Clicking on the edit button in Authentication Configuration sub menu is failed");
				}
		    	
		    }
		    Thread.sleep(1000);		   	
		} else {
	    	log.logLine(Testname, true, "Password Checkbox may not exist in the page");
	    	driver.close();
			driver.switchTo().window(firstWinHandle);
	    	throw new Exception("Password Checkbox may not exist in the page");
		}		
		
		return "yes";		
	}
	
	
	public String DoPromptPasswd(String Testname, String firstWinHandle)  throws Exception {
		
		
		if (doesElementExist2(properties.getProperty("ChkPassword"), 5)) {
		    WebElement CheckboxPassword = driver.findElement(By.cssSelector(properties.getProperty("ChkPassword")));
		    
		    //Check if the checkbox is enabled or not
		    Boolean checked = isAttribtuePresent(CheckboxPassword, "checked");
		    if (checked){		    	
		    	log.logLine(Testname, false, "Do not Prompt for password Checkbox is enabled, hence Its editing the page");
		    	
		    	if (doesElementExist2(properties.getProperty("EditBtn"), 5)) {	    	
				    WebElement eDitbtn = driver.findElement(By.cssSelector(properties.getProperty("EditBtn")));				    
				    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDitbtn);
		    		
				    waitUntilRetrive();			
		    		log.logLine(Testname, false, "Clicked on the edit button in Authentication Configuration sub menu");
				    Thread.sleep(1000);
				    
				    //Enable the check box
				    if (doesElementExist2(properties.getProperty("EnablePasswdChk"), 5)) {
					    WebElement CheckEnable = driver.findElement(By.cssSelector(properties.getProperty("EnablePasswdChk")));
					    
					    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", CheckEnable);
					    log.logLine(Testname, false, "Disabled Don't prompt for new password upon first login check box");
				    } else {
				    	log.logLine(Testname, true, "Disable Don't prompt for new password upon first login check box is failed");
				    	driver.close();
						driver.switchTo().window(firstWinHandle);
				    	throw new Exception("Disable Don't prompt for new password upon first login check box is failed");
					}
				    
				    //Update the Check box
				    if (doesElementExist2(properties.getProperty("UpdateBtn"), 5)) {
					    WebElement UpdteBtn = driver.findElement(By.cssSelector(properties.getProperty("UpdateBtn")));
					    
					    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", UpdteBtn);
					    waitUntilRetrive();			
					    Thread.sleep(1000);
					    log.logLine(Testname, false, "Clicked on update button");
				    } else {
				    	log.logLine(Testname, true, "Clicking on update button is failed");
				    	driver.close();
						driver.switchTo().window(firstWinHandle);
				    	throw new Exception("Clicking on update button is failed");
					}
				    
				   	
				} else {
					log.logLine(Testname, true, "Clicking on the edit button in Authentication Configuration sub menu is failed");
					driver.close();
					driver.switchTo().window(firstWinHandle);
			    	throw new Exception("Clicking on the edit button in Authentication Configuration sub menu is failed");
				}
		    	
		    }else {		    
		    	log.logLine(Testname, false, "Do not Prompt for password Checkbox is not enabled, hence Its not editing");		    	
		    	return "yes";	    	
		    }
		    
		    Thread.sleep(1000);		   	
		} else {
	    	log.logLine(Testname, true, "Password Checkbox may not exist in the page");
	    	driver.close();
			driver.switchTo().window(firstWinHandle);
	    	throw new Exception("Password Checkbox may not exist in the page");
		}		
		
		return "yes";		
	}
	
	
	public boolean applicationRegister(String AccNo, String Testname, String firstWinHandle) throws Exception {
		
		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
		
	    if (Initialization.EnvirSite.equalsIgnoreCase("test")) {
	    	driver.get("https://skyblue-test.edelivery-view.com/");
	    } else if (Initialization.EnvirSite.equalsIgnoreCase("stage")) {
	    	driver.get("https://skyblue-stage.edelivery-view.com/");
	    } else if (Initialization.EnvirSite.equalsIgnoreCase("prod")) {		
	    	driver.get("https://skyblue.edelivery-view.com");
	    }
		
	    Thread.sleep(45000);
		//WebElement retelm2 = waitForElement(properties.getProperty("RegisterBtn"));
		
		if (doesElementExist(properties.getProperty("RegisterBtn"), 5)) {
		    WebElement Regbtn = driver.findElement(By.xpath(properties.getProperty("RegisterBtn")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Regbtn);
		    
		    WebElement retelm3 = waitForElement(properties.getProperty("ConsumID"));
		   	log.logLine(Testname, false, "Clicked on the Register button in Client's page");
		} else {
			log.logLine(Testname, true, "Clicking on the Register button in Client's page is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
	    	throw new Exception("Clicking on the Register button in Client's page is failed");
		}	
		
		Thread.sleep(2000);
		
		if (doesElementExist2(properties.getProperty("ConsumID"), 5)) {
		    WebElement OptAns1 = driver.findElement(By.cssSelector(properties.getProperty("ConsumID")));
		    OptAns1.sendKeys(CreateAppData.ConsumerID1);
		    //OptAns1.sendKeys("testing");
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Entered Consumer ID as "+CreateAppData.ConsumerID1);
		} else {
			log.logLine(Testname, true, "Unable to enter into consumerID");
			driver.close();
			driver.switchTo().window(firstWinHandle);
	    	//throw new Exception("Unable to enter into consumerID");
		}		
		
		
		if (doesElementExist2(properties.getProperty("CreatUser"), 5)) {
		    WebElement OptAns1 = driver.findElement(By.cssSelector(properties.getProperty("CreatUser")));
		    OptAns1.sendKeys("skb"+AccNo);
		    
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Entered CreateUser as skb"+AccNo);
		} else {
			log.logLine(Testname, true, "Unable to enter CreateUser");
			driver.close();
			driver.switchTo().window(firstWinHandle);
	    	//throw new Exception("Unable to enter CreateUser");
		}
		
		if (doesElementExist2(properties.getProperty("CreatePasswd"), 5)) {
		    WebElement crpasswd = driver.findElement(By.cssSelector(properties.getProperty("CreatePasswd")));
		    crpasswd.sendKeys("Spring*5");		    
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Entered CreatePasswd as Spring*5");
		} else {
			log.logLine(Testname, true, "Unable to enter CreatePasswd");
			driver.close();
			driver.switchTo().window(firstWinHandle);
	    	//throw new Exception("Unable to enter CreatePasswd");
		}
		
		if (doesElementExist2(properties.getProperty("ConfPasswd"), 5)) {
		    WebElement crpasswd = driver.findElement(By.cssSelector(properties.getProperty("ConfPasswd")));
		    crpasswd.sendKeys("Spring*5");		    
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Entered ConfPasswd as Spring*5");
		} else {
			log.logLine(Testname, true, "Unable to enter ConfPasswd");
			driver.close();
			driver.switchTo().window(firstWinHandle);
	    	//throw new Exception("Unable to enter ConfPasswd");
		}
		
		if (doesElementExist2(properties.getProperty("email"), 5)) {
		    WebElement crpasswd = driver.findElement(By.cssSelector(properties.getProperty("email")));
		    crpasswd.sendKeys("automationpivot@gmail.com");		    
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Entered email as Spring*5");
		} else {
			log.logLine(Testname, true, "Unable to enter ConfPasswd");
			driver.close();
			driver.switchTo().window(firstWinHandle);
	    	//throw new Exception("Unable to enter ConfPasswd");
		}
		
		if (doesElementExist2(properties.getProperty("cnfrmemail"), 5)) {
		    WebElement crpasswd = driver.findElement(By.cssSelector(properties.getProperty("cnfrmemail")));
		    crpasswd.sendKeys("automationpivot@gmail.com");		    
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Entered confirm email as Spring*5");
		} else {
			log.logLine(Testname, true, "Unable to enter ConfPasswd");
			driver.close();
			driver.switchTo().window(firstWinHandle);
	    	//throw new Exception("Unable to enter ConfPasswd");
		}
		
		String Questionopt1 = test.readColumnData("Question1", sheetname);
		if (doesElementExist2(properties.getProperty("SelQuest1"), 5)) {	   
	    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("SelQuest1")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
	    	Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on Question1 dropdown..");
			
			if (doesElementExist2(properties.getProperty("SelOpt1"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelOpt1")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(Questionopt1)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(2000);
						PleasewaitDisappear();
						log.logLine(Testname, false, "Selecting the Question option as "+Questionopt1);						
						break;
					}				
				}
				
			} else {
				log.logLine(Testname, true, "Question1 options are not displayed");
				driver.close();
				driver.switchTo().window(firstWinHandle);
				throw new Exception("Question1 options are not displayed");
			}
			
	    } else {
			log.logLine(Testname, true, "Question1 dropdown element does not exist");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Question1 dropdown element does not exist");
		}
		
		
		String Answeropt1 = test.readColumnData("Answer1", sheetname);
		if (doesElementExist2(properties.getProperty("Answer1"), 5)) {
		    WebElement OptAns1 = driver.findElement(By.cssSelector(properties.getProperty("Answer1")));
		    OptAns1.sendKeys(Answeropt1);
		    
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Entered Answered as"+Answeropt1);
		} else {
			log.logLine(Testname, true, "Unable to enter answer1");
			driver.close();
			driver.switchTo().window(firstWinHandle);
	    	throw new Exception("Unable to enter answer1");
		}
		
		
		String Questionopt2 = test.readColumnData("Question2", sheetname);
		if (doesElementExist2(properties.getProperty("SelQuest2"), 5)) {	   
	    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("SelQuest2")));
	    	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",ClientSel);
	    	Thread.sleep(2000);
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
	    	Thread.sleep(4000);
			log.logLine(Testname, false, "Clicking on Question2 dropdown..");
			
			if (doesElementExist2(properties.getProperty("SelOpt2"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelOpt2")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(Questionopt2)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(2000);
						PleasewaitDisappear();
						log.logLine(Testname, false, "Selecting the Question option as "+Questionopt2);						
						break;
					}				
				}
				
			} else {
				log.logLine(Testname, true, "Question2 options are not displayed");
				driver.close();
				driver.switchTo().window(firstWinHandle);
				throw new Exception("Question2 options are not displayed");
			}			
	    } else {
			log.logLine(Testname, true, "Question2 dropdown element does not exist");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Question2 dropdown element does not exist");
		}
		
		
		String Answeropt2 = test.readColumnData("Answer2", sheetname);
		if (doesElementExist2(properties.getProperty("Answer2"), 5)) {
		    WebElement OptAns1 = driver.findElement(By.cssSelector(properties.getProperty("Answer2")));
		    OptAns1.sendKeys(Answeropt2);
		    
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Entered Answered as"+Answeropt2);
		} else {
			log.logLine(Testname, true, "Unable to enter answer2");
			driver.close();
			driver.switchTo().window(firstWinHandle);
	    	throw new Exception("Unable to enter answer2");
		}
		
		
		if (doesElementExist2(properties.getProperty("EnterRegisterBtn"), 5)) {
		    WebElement Regbtn = driver.findElement(By.cssSelector(properties.getProperty("EnterRegisterBtn")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Regbtn);
		    
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Clicked on Register button after entering all the details");
		} else {
			log.logLine(Testname, true, "Clicking on Register button after entering all the details is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
	    	throw new Exception("Clicking on Register button after entering all the details is failed");
		}
		
		Thread.sleep(10000);
		WebElement retelm4 = waitForElement(properties.getProperty("DocsTab"));		
		if (doesElementExist2(properties.getProperty("DocsTab"), 5)) {		    	    
		   	log.logLine(Testname, false, "Navigated to Documents tab after the Registration successful");
		
		} else if (doesElementExist2(properties.getProperty("ValidateReg"), 5)) {		    	    
		   	log.logLine(Testname, true, "Error: Consumer id is not valid, Please check");
		   	driver.close();
			driver.switchTo().window(firstWinHandle);
		   	throw new Exception("Error: Consumer id is not valid, Please check");		
		
		} else {
			log.logLine(Testname, true, "Navigation to Documents tab is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
	    	throw new Exception("Navigation to Documents tab is failed");
		}
		
		Thread.sleep(8000);
		if (doesElementExist2(properties.getProperty("MyProfileTab"), 5)) {
			WebElement proftab = driver.findElement(By.cssSelector(properties.getProperty("MyProfileTab")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", proftab);		    
			Thread.sleep(1000);
			log.logLine(Testname, false, "Navigated to MyProfile tab");
		}
		else if (doesElementExist2(properties.getProperty("MyProfileTab1"), 5)) {
			WebElement proftab = driver.findElement(By.cssSelector(properties.getProperty("MyProfileTab1")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click()", proftab);		    
			Thread.sleep(1000);
			log.logLine(Testname, false, "Navigated to MyProfile tab");
		}
		else {
			log.logLine(Testname, true, "Navigated to MyProfile tab is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Navigated to MyProfile tab is failed");
		}
		
		
		WebElement retelm5 = waitForElement(properties.getProperty("FirstNamefld"));
		if (doesElementExist2(properties.getProperty("FirstNamefld"), 5)) {
		    String FirstNam = driver.findElement(By.cssSelector(properties.getProperty("FirstNamefld"))).getAttribute("value");
		    
		    if (FirstNam.equalsIgnoreCase("Manohar")) {
		    	log.logLine(Testname, false, "MyProfiles FirstName is matched with Uploaded registration FirstName");
		    } else {
		    	log.logLine(Testname, true, "MyProfiles FirstName is not matched with Uploaded registration FirstName");
		    }	   	
		} else {
			log.logLine(Testname, true, "FirstName field is not exist in MyProfiles page");
			driver.close();
			driver.switchTo().window(firstWinHandle);
	    	throw new Exception("FirstName field is not exist in MyProfiles page");
		}
		
		
		if (doesElementExist2(properties.getProperty("LastNamefld"), 5)) {
		    String LastNam = driver.findElement(By.cssSelector(properties.getProperty("LastNamefld"))).getAttribute("value");
		    
		    if (LastNam.equalsIgnoreCase("Basavaiah")) {
		    	log.logLine(Testname, false, "MyProfiles LastName is matched with Uploaded registration LastName");
		    } else {
		    	log.logLine(Testname, true, "MyProfiles LastName is not matched with Uploaded registration LastName");
		    }	   	
		} else {
			log.logLine(Testname, true, "LastName field is not exist in MyProfiles page");
			driver.close();
			driver.switchTo().window(firstWinHandle);
	    	throw new Exception("LastName field is not exist in MyProfiles page");
		}
		
		
		if (doesElementExist2(properties.getProperty("Emailfld"), 5)) {
		    String Email = driver.findElement(By.cssSelector(properties.getProperty("Emailfld"))).getAttribute("value");
		    
		    if (Email.equalsIgnoreCase("manohar.x.basavaiah@rrd.com")) {
		    	log.logLine(Testname, false, "MyProfiles Emailfld is matched with Uploaded registration Emailfld");
		    } else {
		    	log.logLine(Testname, true, "MyProfiles Emailfld is not matched with Uploaded registration Emailfld");
		    }	   	
		} else {
			log.logLine(Testname, true, "Emailfld field is not exist in MyProfiles page");
			driver.close();
			driver.switchTo().window(firstWinHandle);
	    	throw new Exception("Emailfld field is not exist in MyProfiles page");
		}
		
		
		if (doesElementExist2(properties.getProperty("LogoutProfile"), 5)) {
		    WebElement logout = driver.findElement(By.cssSelector(properties.getProperty("LogoutProfile")));
		    
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", logout);		    
		    Thread.sleep(5000);
		   	log.logLine(Testname, false, "Clicked in Logout button in MyProfiles Page");
		} else {
			log.logLine(Testname, true, "Clicking in Logout button in MyProfiles Page is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
	    	throw new Exception("Clicking in Logout button in MyProfiles Page is failed");
		}

		
		return true;		
	}
	
	
	
	public boolean PasswordConsent(String AccNo, String Testname, String firstWinHandle, String PasswdReset) throws Exception {
		
		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
		
		driver.get("https://bluestream-test.edelivery-view.com/");
		WebElement retelm2 = waitForElement(properties.getProperty("BluStrLoginBtn"));	
		
		
		if (doesElementExist2(properties.getProperty("BluStrUserid"), 5)) {
		    WebElement OptAns1 = driver.findElement(By.cssSelector(properties.getProperty("BluStrUserid")));
		    if (PasswdReset.equalsIgnoreCase("yes"))
		    	OptAns1.sendKeys(CreateAppData.User1);
		    else
		    	OptAns1.sendKeys(CreateAppData.User2);
		    
		    Thread.sleep(500);
		   	log.logLine(Testname, false, "Entered User ID as "+CreateAppData.User2);
		} else {
			log.logLine(Testname, true, "Unable to enter UserID ");
			driver.close();
			driver.switchTo().window(firstWinHandle);
	    	throw new Exception("Unable to enter into UserID");
		}		
		
		
		if (doesElementExist2(properties.getProperty("BluStrPasswd"), 5)) {
		    WebElement OptAns1 = driver.findElement(By.cssSelector(properties.getProperty("BluStrPasswd")));
		    OptAns1.sendKeys("Spring*5");
		    
		    Thread.sleep(500);
		   	log.logLine(Testname, false, "Entered Password as Spring*5");
		} else {
			log.logLine(Testname, true, "Unable to enter into Password");
			driver.close();
			driver.switchTo().window(firstWinHandle);
	    	throw new Exception("Unable to enter into Password");
		}
		
		
		if (doesElementExist2(properties.getProperty("BluStrLoginBtn"), 5)) {
		    WebElement Regbtn = driver.findElement(By.cssSelector(properties.getProperty("BluStrLoginBtn")));
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Regbtn);
		    
		    Thread.sleep(2000);
		   	log.logLine(Testname, false, "Clicked on Login button..");
		} else {
			log.logLine(Testname, true, "Clicking on Login button is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
	    	throw new Exception("Clicking on Login button is failed");
		}
		
		if (PasswdReset.equalsIgnoreCase("yes")){
			
			if (doesElementExist2(properties.getProperty("NewPasswdfld"), 5)) {
			    WebElement NewPass = driver.findElement(By.cssSelector(properties.getProperty("NewPasswdfld")));
			    NewPass.sendKeys("Spring*5");
			    
			    Thread.sleep(1000);
			   	log.logLine(Testname, false, "Enered new password in Reset page");
			} else {
				log.logLine(Testname, true, "Entering the new password in reset page is failed");
				driver.close();
				driver.switchTo().window(firstWinHandle);
		    	throw new Exception("Entering the new password in reset page is failed");
			}
			
			if (doesElementExist2(properties.getProperty("NewConfPasswdfld"), 5)) {
			    WebElement ConfPass = driver.findElement(By.cssSelector(properties.getProperty("NewConfPasswdfld")));
			    ConfPass.sendKeys("Spring*5");
			    
			    Thread.sleep(1000);
			   	log.logLine(Testname, false, "Entered the new Confirm password in Reset page");
			} else {
				log.logLine(Testname, true, "Entering the Confirm password in reset page is failed");
				driver.close();
				driver.switchTo().window(firstWinHandle);
		    	throw new Exception("Entering the Confirm password in reset page is failed");
			}
			
			if (doesElementExist2(properties.getProperty("PasswdUpdatebtn"), 5)) {
			    WebElement Regbtn = driver.findElement(By.cssSelector(properties.getProperty("PasswdUpdatebtn")));
			    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Regbtn);
			    
			    Thread.sleep(6000);
			   	log.logLine(Testname, false, "Clicked on Password Update button..");
			} else {
				log.logLine(Testname, true, "Clicking on Password update button is failed");
				driver.close();
				driver.switchTo().window(firstWinHandle);
		    	throw new Exception("Clicking on Password update button is failed");
			}
			
			
			if (doesElementExist2(properties.getProperty("thisLnk"), 5)) {
			    WebElement thislink = driver.findElement(By.cssSelector(properties.getProperty("thisLnk")));
			    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", thislink);
			    
			    Thread.sleep(6000);
			   	log.logLine(Testname, false, "Clicked on Password Update button..");
			} else {
				log.logLine(Testname, true, "Clicking on Password update button is failed");
				driver.close();
				driver.switchTo().window(firstWinHandle);
		    	throw new Exception("Clicking on Password update button is failed");
			}
			
			WebElement retelm3 = waitForElement(properties.getProperty("BluStrLoginBtn"));	
			
			
			if (doesElementExist2(properties.getProperty("BluStrUserid"), 5)) {
			    WebElement OptAns1 = driver.findElement(By.cssSelector(properties.getProperty("BluStrUserid")));
			    if (PasswdReset.equalsIgnoreCase("yes"))
			    	OptAns1.sendKeys(CreateAppData.User1);
			    else
			    	OptAns1.sendKeys(CreateAppData.User2);
			    
			    Thread.sleep(500);
			   	log.logLine(Testname, false, "Entered User ID as "+CreateAppData.User2);
			} else {
				log.logLine(Testname, true, "Unable to enter UserID ");
				driver.close();
				driver.switchTo().window(firstWinHandle);
		    	throw new Exception("Unable to enter into UserID");
			}		
			
			
			if (doesElementExist2(properties.getProperty("BluStrPasswd"), 5)) {
			    WebElement OptAns1 = driver.findElement(By.cssSelector(properties.getProperty("BluStrPasswd")));
			    OptAns1.sendKeys("Spring*5");
			    
			    Thread.sleep(500);
			   	log.logLine(Testname, false, "Entered Password as Spring*5");
			} else {
				log.logLine(Testname, true, "Unable to enter into Password");
				driver.close();
				driver.switchTo().window(firstWinHandle);
		    	throw new Exception("Unable to enter into Password");
			}
			
			
			if (doesElementExist2(properties.getProperty("BluStrLoginBtn"), 5)) {
			    WebElement Regbtn = driver.findElement(By.cssSelector(properties.getProperty("BluStrLoginBtn")));
			    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", Regbtn);
			    
			    Thread.sleep(2000);
			   	log.logLine(Testname, false, "Clicked on Login button..");
			} else {
				log.logLine(Testname, true, "Clicking on Login button is failed");
				driver.close();
				driver.switchTo().window(firstWinHandle);
		    	throw new Exception("Clicking on Login button is failed");
			}
			
		}
		
		
		WebElement retelm4 = waitForElement(properties.getProperty("BluStrProfile"));		
		if (doesElementExist2(properties.getProperty("BluStrProfile"), 5)) {		    	    
		   	log.logLine(Testname, false, "Navigated to Documents tab after the Login");
		
		} else if (doesElementExist2(properties.getProperty("IncorrectLogin"), 5)) {
			log.logLine(Testname, true, "Incorrect User/Password - Login is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
	    	throw new Exception("Incorrect User/Password - Login is failed");
		
		}else {
			log.logLine(Testname, true, "Navigation to Documents tab is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
	    	throw new Exception("Navigation to Documents tab is failed");		
		}
		
		
		if (doesElementExist2(properties.getProperty("BluStrLogout"), 5)) {
		    WebElement logout = driver.findElement(By.cssSelector(properties.getProperty("BluStrLogout")));
		    
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click()", logout);		    
		    Thread.sleep(5000);
		   	log.logLine(Testname, false, "Clicked in Logout button in MyProfiles Page");
		} else {
			log.logLine(Testname, true, "Clicking in Logout button in MyProfiles Page is failed");
			driver.close();
			driver.switchTo().window(firstWinHandle);
	    	throw new Exception("Clicking in Logout button in MyProfiles Page is failed");
		}

		
		return true;		
	}
	
	
}











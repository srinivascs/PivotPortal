package pivotModules;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

import launchAuto.Initialization;
import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class SSOUserLogin extends Page{


	public SSOUserLogin(WebDriver driver, Log log) throws InvalidFormatException, IOException {
 			super(driver, log);
 	} 
 	@Override
 	protected void load() {}
 	@Override
 	
 	protected void isLoaded() throws Error {}
 	
 	public static String TempPwd;
 	public static String UserName;
 	public static String FinalLink;
 
 	public boolean ClientAppSel(String AccNo, String Testname) throws Exception {
 		 
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
				log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
		    } else {
				log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");
				//throw new Exception("Clicking on Cancel button in Client/App popup is failed");
			}
		    
		 Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on Admin to select Archive >> test harness");	    
		    
		    if (doesElementExist2(properties.getProperty("AdminMenu"), 5)) {
				List<WebElement> Mns = driver.findElements(By.cssSelector(properties.getProperty("AdminMenu")));
				for (WebElement Each:Mns) {
					if(Each.getText().equals("Admin")){
						
						Actions action = new Actions(driver);
						if (Initialization.Browser.equalsIgnoreCase("ie") || Initialization.Browser.equalsIgnoreCase("internetexplorer")) {
							
							action.moveToElement(Each).build().perform();						
							Thread.sleep(1000);
							
							WebElement Archive = driver.findElement(By.cssSelector(properties.getProperty("ArchiveSubMnu")));
							action.moveToElement(Archive).build().perform();
							Thread.sleep(1000);
						} else {
						
							((JavascriptExecutor) driver).executeScript("return $(\"span:contains('Admin')\").mouseover();");
							Thread.sleep(1000);
							((JavascriptExecutor) driver).executeScript("return $(\"span:contains('Archive')\").mouseover();");
							Thread.sleep(1000);
						}
						
						if (doesElementExist2(properties.getProperty("TestHarnesMnu"), 5)) {
							WebElement TestHar = driver.findElement(By.cssSelector(properties.getProperty("TestHarnesMnu")));
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", TestHar);
							log.logLine(Testname, false, "Clicked on Test Harness link under Admin menu");
						} else {
							log.logLine(Testname, true, "Clicking on Test Harness link under Admin menu failed");
							throw new Exception("Clicking on Test Harness link under Admin menu failed");
						}
							
						Thread.sleep(4000);				
						
						log.logLine(Testname, false, "Clicked on Admin >> Archive >> Test Harness");
						break;
					}
				}
				
				Thread.sleep(1500);
		    } else {
				log.logLine(Testname, true, "Opening Admin window failed");
				throw new Exception("Opening Admin window failed");
			}
	    
	    return true;
 	}
 	
 	
 	
	public boolean LoginPasswordReset(String AccNo,String Testname) throws Exception {
 		
 		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	
	    String envi = Initialization.EnvirSite.toUpperCase();
	    
	    String Usrnme=test.readColumnData("UserName", sheetname);
	    String Pswd=test.readColumnData("Password", sheetname);
	    
	    String secuone=test.readColumnData("SecurityAnswerone", sheetname);
	    String sectwo=test.readColumnData("SecurityAnswertwo", sheetname);
	   // String Optionone=test.readColumnData("Tabone", sheetname);
	   // String Optiontwo=test.readColumnData("Tabtwo", sheetname);
	    String Fstnme=test.readColumnData("FirstName", sheetname);
	    String Lstnme=test.readColumnData("LastName", sheetname);
	    String Emladd=test.readColumnData("EmailAddress", sheetname);
	    
	    
		Set<String> handles = driver.getWindowHandles();
	    String firstWinHandle = driver.getWindowHandle(); 
	    handles.remove(firstWinHandle);
	    
	    boolean browserexist = handles.iterator().hasNext();
	    if (browserexist) {
		    String winHandle=handles.iterator().next();
		    if (winHandle!=firstWinHandle){
		    
		    	//Switch control to new window
		    	driver.switchTo().window(winHandle);
		    	waitUntilLoadElementDisappear4();
		    	driver.manage().window().maximize();
		    	driver.get("https://skyblue-"+envi+".edelivery-view.com/");
		    	//if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) || (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) 
	    		//	driver.get("javascript:document.getElementById('overridelink').click();");
		    	
		    	Thread.sleep(15000);
		    	
		    	
		    	//ForgotUserID(Testname);
		    	
		    	Thread.sleep(5000);			    	
		    	if (doesElementExist(properties.getProperty("ForgotPassword"), 5)) {
					WebElement Frgpwd = driver.findElement(By.xpath(properties.getProperty("ForgotPassword")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Frgpwd);
					log.logLine(Testname, false, "Clicked on Forgot Password Link under edelivery application");
				} else {
					log.logLine(Testname, true, "Clicked on Forgot Password Link under edelivery application failed");
					throw new Exception("Clicked on Forgot Password Link under edelivery application failed");
				}
		    	
		    	
		    	if (doesElementExist2(properties.getProperty("UserName"), 5)) {
					WebElement Usernme = driver.findElement(By.cssSelector(properties.getProperty("UserName")));
					Usernme.sendKeys(Usrnme);
					//Usernme.sendKeys(UserName);
					log.logLine(Testname, false, "Entered the User name as "+Usrnme+" under edelivery application");
					
				} else {
					log.logLine(Testname, true, "Entering the User name under edelivery application failed");
					throw new Exception("Entering the User name under edelivery application failed");
				}
		    	
		    	// Click on Forgot Password Link
		    	if (doesElementExist(properties.getProperty("ForgotPassword"), 5)) {
					WebElement Frgpwd = driver.findElement(By.xpath(properties.getProperty("ForgotPassword")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Frgpwd);
					log.logLine(Testname, false, "Clicked on Forgot Password Link under edelivery application");
				} else {
					log.logLine(Testname, true, "Clicked on Forgot Password Link under edelivery application failed");
					throw new Exception("Clicked on Forgot Password Link under edelivery application failed");
				}
		    	
		    	//Security Questions
		    	Thread.sleep(5000);
		    	if (doesElementExist2(properties.getProperty("Securqesone"), 5)) {
					WebElement Sec1 = driver.findElement(By.cssSelector(properties.getProperty("Securqesone")));
					Sec1.sendKeys(secuone);
					log.logLine(Testname, false, "Entered the Security answer for Name of the Pet as "+secuone+" Link under edelivery application");
				} else {
					log.logLine(Testname, true, "Entering the Security answer is failed");
					throw new Exception("Entering the Security answer is failed");
				}
		    	
		    	
		    	if (doesElementExist2(properties.getProperty("Securqestwo"), 5)) {
					WebElement Sec2 = driver.findElement(By.cssSelector(properties.getProperty("Securqestwo")));
					Sec2.sendKeys(sectwo);
					log.logLine(Testname, false, "Entered the Security answer for Favorite color as "+sectwo+" Link under edelivery application");
				} else {
					log.logLine(Testname, true, "Entering the Security answer is failed");
					throw new Exception("Entering the Security answer is failed");
				}
		    
		    	
		    	// Click on Send Button
		    	if (doesElementExist2(properties.getProperty("Sendbtn"), 5)) {
					WebElement Snd = driver.findElement(By.cssSelector(properties.getProperty("Sendbtn")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Snd);
					Thread.sleep(4000);
					log.logLine(Testname, false, "Click on Send button is successful");
				} else {
					log.logLine(Testname, true, "Click on Send button is unsuccessful");
					throw new Exception("Click on Send button is unsuccessful");
				}
		    	
		    	
		    	//Click on Link
		    	if (doesElementExist2(properties.getProperty("PasswordLink"), 5)) {
					WebElement Snd = driver.findElement(By.cssSelector(properties.getProperty("PasswordLink")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Snd);
					Thread.sleep(4000);
					log.logLine(Testname, false, "Click on Link is successful");
				} else {
					log.logLine(Testname, true, "Click on Link is unsuccessful");
					throw new Exception("Click on Link is unsuccessful");
				}
		    	
		    	
		    	Thread.sleep(120000);
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
		            	log.logLine(Testname, false,"The From Mail Reads as "+address.toString()+" in Inbox  ");	
	
		            }
		            Multipart mp = (Multipart) msg.getContent();
		            BodyPart bp = mp.getBodyPart(0);
		            Thread.sleep(1000);
		            System.out.println("SUBJECT:" + msg.getSubject());
		            log.logLine(Testname, false,"The Subject reads as "+msg.getSubject()+"");	
		            
		            Thread.sleep(1000);
		            System.out.println("CONTENT:" + bp.getContent());
		            log.logLine(Testname, false,"The Content reads as "+bp.getContent()+" in Inbox  ");
		            String arr[] =bp.getContent().toString().split("here a temporary password you can use to enter the site");
		            String name=arr[0];
		            String Content=arr[1];
		         //  String arra[] = bp.getContent().toString().split(""+name+""+Content+"");
		           // String Content = arra[1];
		            String array[]=Content.split("Login into https://skyblue-stage.edelivery-view.com and change the password once you login.");
		            TempPwd = array[0].trim();
		            
		            log.logLine(Testname, false,"The Temporary password read form the mail is   "+TempPwd+"");
		            log.logLine(Testname, false, "Validating the Email is successful");
		            
		            
		        } catch (Exception mex) {
		            mex.printStackTrace();
		        }       
		    	
		        // Entering the UserName and Temporary Password
		        if (doesElementExist2(properties.getProperty("UserName"), 5)) {
					WebElement Usernme = driver.findElement(By.cssSelector(properties.getProperty("UserName")));
					Usernme.sendKeys(Usrnme);
					log.logLine(Testname, false, "Entered the User name as "+Usrnme+" Link under edelivery application");
				} else {
					log.logLine(Testname, true, "Entering the User name under edelivery application failed");
					throw new Exception("Entering the User name under edelivery application failed");
				}
		        Thread.sleep(5000);
		        if (doesElementExist2(properties.getProperty("Password"), 5)) {
					WebElement Psswd = driver.findElement(By.cssSelector(properties.getProperty("Password")));
					Psswd.sendKeys(TempPwd);
					log.logLine(Testname, false, "Entered the Password as "+TempPwd+" under edelivery application");
				} else {
					log.logLine(Testname, true, "Entering the Password under edelivery application failed");
					throw new Exception("Entering the Password under edelivery application failed");
				}
		        
		       //Click on Login Button
		        Thread.sleep(5000);
		    	if (doesElementExist2(properties.getProperty("LoginBtn"), 5)) {
					WebElement Lgnbtn = driver.findElement(By.cssSelector(properties.getProperty("LoginBtn")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Lgnbtn);
					Thread.sleep(4000);
					log.logLine(Testname, false, "Click on Login button is successful");
				} else {
					log.logLine(Testname, true, "Click on Login button is unsuccessful");
					throw new Exception("Click on Login button is unsuccessful");
				}
		    	
		    	//Selecting the Change Password Pop Up
				Thread.sleep(15000);
		    	if (doesElementExist2(properties.getProperty("Changepwdpopup"), 5)) {
					WebElement Pwdpopup = driver.findElement(By.cssSelector(properties.getProperty("Changepwdpopup")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Pwdpopup);
					Thread.sleep(4000);
					log.logLine(Testname, false, "Click on Change Password is successful");
				} else {
					log.logLine(Testname, true, "Click on Change Password is unsuccessful");
					throw new Exception("Click on Change Password is unsuccessful");
				}
		    	
		    	
		    	// Entering the New Password
		    	if (doesElementExist(properties.getProperty("ResetPassword"), 5)) {
					WebElement RstPsswd = driver.findElement(By.xpath(properties.getProperty("ResetPassword")));
					RstPsswd.sendKeys(Pswd);
					log.logLine(Testname, false, "Reseted the Password as "+Pswd+" under edelivery application");
				
		    	if (doesElementExist(properties.getProperty("ConfirmPassword"), 5)) {
					WebElement Cnfmpwd = driver.findElement(By.xpath(properties.getProperty("ConfirmPassword")));
					Cnfmpwd.sendKeys(Pswd);
					log.logLine(Testname, false, "Reseted the Confirm Password as "+Pswd+" under edelivery application");
				}
		    }else {
					log.logLine(Testname, true, "Reseted the Password under edelivery application failed");
					throw new Exception("Reseted the Password under edelivery application failed");
				}
		    	
		    	
		    	// Click on Save Button
		    	if (doesElementExist(properties.getProperty("Savebtn"), 5)) {
					WebElement Svebtn = driver.findElement(By.xpath(properties.getProperty("Savebtn")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Svebtn);
					Thread.sleep(4000);
					log.logLine(Testname, false, "Click on Save Button for Password Reset is successful");
				} else {
					log.logLine(Testname, true, "Click on Save Button for Password Reset is unsuccessful");
					throw new Exception("Click on Save Button for Password Reset is unsuccessful");
				}
		    	
		    	
		    	// Validating the Password Update message
		    	if (doesElementExist(properties.getProperty("Passwordsavevalidation"), 5)) {
					String Pwdverfcn = driver.findElement(By.xpath(properties.getProperty("Passwordsavevalidation"))).getText();
					log.logLine(Testname, false, "Password Update message is Successful-------"+Pwdverfcn+"");
				} else {
					log.logLine(Testname, true, "Password Update message is unsuccessful");
					throw new Exception("Password Update message is unsuccessful");
				}
					
		    	
		    	// Click on Logout Button
		    	if (doesElementExist(properties.getProperty("Logout"), 5)) {
					WebElement Logout = driver.findElement(By.xpath(properties.getProperty("Logout")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Logout);
					Thread.sleep(4000);
					log.logLine(Testname, false, "Click on Logout Button is successful");
				} else {
					log.logLine(Testname, true, "Click on Logout Button is unsuccessful");
					throw new Exception("Click on Logout Button is unsuccessful");
					
				}
		    	
		    	// Entering the UN and PWD with new password
		    	if (doesElementExist2(properties.getProperty("UserName"), 5)) {
					WebElement Usernme = driver.findElement(By.cssSelector(properties.getProperty("UserName")));
					Usernme.sendKeys(Usrnme);
					log.logLine(Testname, false, "Entered the User name as "+Usrnme+" Link under edelivery application");
				} else {
					log.logLine(Testname, true, "Entering the User name under edelivery application failed");
					throw new Exception("Entering the User name under edelivery application failed");
				}
		        
		        if (doesElementExist2(properties.getProperty("Password"), 5)) {
					WebElement Psswd = driver.findElement(By.cssSelector(properties.getProperty("Password")));
					Psswd.sendKeys(Pswd);
					log.logLine(Testname, false, "Entered the Password as "+Pswd+" under edelivery application");
				} else {
					log.logLine(Testname, true, "Entering the Password under edelivery application failed");
					throw new Exception("Entering the Password under edelivery application failed");
				}
		    	
		        
		        // Click on Login Button
		    	if (doesElementExist2(properties.getProperty("LoginBtn"), 5)) {
					WebElement Lgnbtn = driver.findElement(By.cssSelector(properties.getProperty("LoginBtn")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Lgnbtn);
					Thread.sleep(4000);
					log.logLine(Testname, false, "Click on Login button is successful");
					log.logLine(Testname, false, "Login successful with New Password");
				} else {
					log.logLine(Testname, true, "Click on Login button is unsuccessful");
					throw new Exception("Click on Login button is unsuccessful");
				}
		    	
		    	
		    	//Click on MyProfile Tab
		    	Thread.sleep(5000);
		    	if (doesElementExist(properties.getProperty("MyProfile"), 5)) {
					WebElement Myprfle = driver.findElement(By.xpath(properties.getProperty("MyProfile")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Myprfle);
					Thread.sleep(4000);
					log.logLine(Testname, false, "Clicked on My Profile options menu");
				} else {
					log.logLine(Testname, true, "Clicked on My Profile is failed");
					throw new Exception("Clicked on My Profile is failed");
				}
				
		    	
		    	// Entering the First Name and Last Name
				if (doesElementExist(properties.getProperty("FirstName"), 5)) {
					WebElement FstNme = driver.findElement(By.xpath(properties.getProperty("FirstName")));
					FstNme.clear();
					FstNme.sendKeys(Fstnme);
					log.logLine(Testname, false, "Editing the First Name as "+Fstnme+" under My Profile Section");
				}else {
					log.logLine(Testname, true, "Editing the First Name is failed");
					throw new Exception("Editing the First Name is failed");
				}
				
				
				if (doesElementExist(properties.getProperty("LastName"), 5)) {
					WebElement LstNme = driver.findElement(By.xpath(properties.getProperty("LastName")));
					LstNme.clear();
					LstNme.sendKeys(Lstnme);
					log.logLine(Testname, false, "Editing the Last Name as "+Lstnme+" under My Profile Section");
				}else {
					log.logLine(Testname, true, "Editing the Last Name is failed");
					throw new Exception("Editing the Last Name is failed");
				}
				
				// Entering the Email Address
				if (doesElementExist(properties.getProperty("EmailAddress"), 5)) {
					WebElement EmlAddss = driver.findElement(By.xpath(properties.getProperty("EmailAddress")));
					EmlAddss.clear();
					EmlAddss.sendKeys(Emladd);
					log.logLine(Testname, false, "Editing the Email Address as "+Emladd+" under My Profile Section");
				}else {
					log.logLine(Testname, true, "Editing the Email Address is failed");
					throw new Exception("Editing the Email Address is failed");
				}
		    	
				//Click on Save Button
				if (doesElementExist(properties.getProperty("Savebtn"), 5)) {
					WebElement Svebtn = driver.findElement(By.xpath(properties.getProperty("Savebtn")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Svebtn);
					Thread.sleep(4000);
					log.logLine(Testname, false, "Click on Save Button after editing is successful");
				} else {
					log.logLine(Testname, true, "Click on Save Button is unsuccessful");
					throw new Exception("Click on Save Button is unsuccessful");
				}
				
				//Validating the message
				if (doesElementExist(properties.getProperty("Passwordsavevalidation"), 5)) {
					String Pwdverfcn = driver.findElement(By.xpath(properties.getProperty("Passwordsavevalidation"))).getText();
					log.logLine(Testname, false, "Editing First/Last/EmailAddress is Successful-------"+Pwdverfcn+"");
				} else {
					log.logLine(Testname, true, "Password Update message is unsuccessful");
					throw new Exception("Password Update message is unsuccessful");
				}
				
				
				
				/*
				 if (doesElementExist(properties.getProperty("SecQstnone"), 5)) {	    
						WebElement secone = driver.findElement(By.xpath(properties.getProperty("SecQstnone")));
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", secone);
						Thread.sleep(1000);
						
						log.logLine(Testname, false, "Clicking on Security Question one Drop Down list");
						
						if (doesElementExist2(properties.getProperty("SecQstoptionone"), 5)) {
							
							List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SecQstoptionone")));
							for (WebElement lnk:selopts) {
								if (lnk.getText().equals("What was the name of your first pet?")) {
									((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
									log.logLine(Testname, false, "Selecting What was the name of your first pet? option from the dropdown");
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
				 
				 */
				
				// Entering the security answers
				 Thread.sleep(5000);
			    	if (doesElementExist(properties.getProperty("SecAnsone"), 5)) {
						WebElement Sec1 = driver.findElement(By.xpath(properties.getProperty("SecAnsone")));
						Sec1.clear();
						Sec1.sendKeys(secuone);
						log.logLine(Testname, false, "Entered the Security answer for Name of the Pet as "+secuone+" Link under edelivery application");
					} else {
						log.logLine(Testname, true, "Entering the Security answer is failed");
						throw new Exception("Entering the Security answer is failed");
					} 
			
			    	
			    	/*
					
				 if (doesElementExist(properties.getProperty("SecQstntwo"), 5)) {	    
						WebElement secutwo = driver.findElement(By.xpath(properties.getProperty("SecQstntwo")));
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", secutwo);
						Thread.sleep(1000);
						
						log.logLine(Testname, false, "Clicking on Security Question two Drop Down list");
						
						if (doesElementExist2(properties.getProperty("SecQstOptiontwo"), 5)) {
							
							List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SecQstOptiontwo")));
							for (WebElement lnk:selopts) {
								if (lnk.getText().equals("What is your favorite color?")) {
									((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
									log.logLine(Testname, false, "Selecting What is your favorite color? option from the dropdown");
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
			*/		 	 
				 
				 if (doesElementExist(properties.getProperty("SecAnstwo"), 5)) {
					WebElement Sec2 = driver.findElement(By.xpath(properties.getProperty("SecAnstwo")));
					Sec2.clear();
					Sec2.sendKeys(sectwo);
					log.logLine(Testname, false, "Entered the Security answer for Favorite color as "+sectwo+" Link under edelivery application");
				} else {
					log.logLine(Testname, true, "Entering the Security answer is failed");
					throw new Exception("Entering the Security answer is failed");
				}
				 
				 
				 // Click on Save Button
				 if (doesElementExist(properties.getProperty("SaveSecButton"), 5)) {
						WebElement Svebtn = driver.findElement(By.xpath(properties.getProperty("SaveSecButton")));
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", Svebtn);
						Thread.sleep(4000);
						log.logLine(Testname, false, "Click on Save Button after editing is successful");
					} else {
						log.logLine(Testname, true, "Click on Save Button is unsuccessful");
						throw new Exception("Click on Save Button is unsuccessful");
					}
				
				 
				 // Validating the Security message
				 if (doesElementExist(properties.getProperty("SecurityValidation"), 5)) {
					String Secverfcn = driver.findElement(By.xpath(properties.getProperty("SecurityValidation"))).getText();
					log.logLine(Testname, false, "Editing Secutity Qustions and Answer is Successful-------"+Secverfcn+"");
				} else {
					log.logLine(Testname, true, "Password Update message is unsuccessful");
					//throw new Exception("Password Update message is unsuccessful");
				}
				 
				 
				 	// Click on Delivery Option Tab
				 	Thread.sleep(5000);
			    	if (doesElementExist(properties.getProperty("DeliveryOptions"), 5)) {
						WebElement Deloptn = driver.findElement(By.xpath(properties.getProperty("DeliveryOptions")));
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", Deloptn);
						Thread.sleep(4000);
						log.logLine(Testname, false, "Clicked on Delivery Options options menu");
					} else {
						log.logLine(Testname, true, "Clicked on Delivery Options is failed");
						//throw new Exception("Clicked on Delivery Options is failed");
					}
		    	
			    	
			    	// Click on Cancel Button
			    	Thread.sleep(8000);
			    	if (doesElementExist(properties.getProperty("CancelButton"), 5)) {
			    		String Cncl = driver.findElement(By.xpath(properties.getProperty("CancelButton"))).getAttribute("value");
						if(Cncl.equals("Cancel")){
							WebElement Cnclel = driver.findElement(By.xpath(properties.getProperty("CancelButton")));
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", Cnclel);
							log.logLine(Testname, false, "Click on Cancel button is successful");
						}else {
							log.logLine(Testname, false, "Cancel Button is not present");
							}
					} else {
						log.logLine(Testname, true, "Clicked on Cancel Button is failed");
						//throw new Exception("Clicked on Cancel Button is failed");
					}
			    	
			    	
			    	if (doesElementExist(properties.getProperty("CancelButton1"), 5)) {
			    		String Cancel = driver.findElement(By.xpath(properties.getProperty("CancelButton1"))).getAttribute("value");
						if(Cancel.equals("Cancel")){
							WebElement Cnclel = driver.findElement(By.xpath(properties.getProperty("CancelButton1")));
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", Cnclel);
							log.logLine(Testname, false, "Click on Cancel button for second time is successful");
						}else {
							log.logLine(Testname, false, "Cancel button is not present for second time");
						}
					} else {
						log.logLine(Testname, false, "Cancel button is not present for second time");
	
					}
			    	
			    	
			    	
			   		// Selecting the Radio Buttons	
			    	Thread.sleep(2000);
			    	if (doesElementExist(properties.getProperty("GoonlineBlue"), 5)) {
						WebElement Gordo = driver.findElement(By.xpath(properties.getProperty("GoonlineBlue")));
						if (!Gordo.isSelected())
			  		     {
							Gordo.click();
							log.logLine(Testname, false, "Selecting Go online and save the Planet for Blue document is successful");
			  	    	}else{
							log.logLine(Testname, false, "Selecting Go online and save the Planet for Blue document is already Checked");
			 	    	}
			    }
			    	
					/*			    	
			    	if (doesElementExist(properties.getProperty("GoonlineUser"), 5)) {
						WebElement Gordoio = driver.findElement(By.xpath(properties.getProperty("GoonlineUser")));
						if (!Gordoio.isSelected())
			  		     {
							Gordoio.click();
			  	    		log.logLine(Testname, false, "Selecting Go online and save the Planet for user is successful");
			  	    	}else{
			  		    	log.logLine(Testname, false, "Selecting Go online and save the Planet for user is already Checked");
			 	    	}
					}*/
			    	
			    	
			    	// Selecting the Agree check box
			    	if (doesElementExist(properties.getProperty("Agreecheckbox"), 5)) {
						WebElement Agreechck = driver.findElement(By.xpath(properties.getProperty("Agreecheckbox")));
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", Agreechck);
						Thread.sleep(4000);
						log.logLine(Testname, false, "Selecting Agree Check Box is successful");
					} else if (doesElementExist(properties.getProperty("Agreecheckbox1"), 5)) {
								WebElement Agreechck1 = driver.findElement(By.xpath(properties.getProperty("Agreecheckbox1")));
								((JavascriptExecutor) driver).executeScript("arguments[0].click()", Agreechck1);
								Thread.sleep(4000);
								log.logLine(Testname, false, "Selecting Agree Check Box is successful");
				} else {
						log.logLine(Testname, true, "Selecting Agree Check Box is failed");
						throw new Exception("Selecting Agree Check Box is failed");
					}
		    	
			    
			    	// Click on Save Button
			    	if (doesElementExist2(properties.getProperty("SaveBtn"), 5)) {
						WebElement Agreechck = driver.findElement(By.cssSelector(properties.getProperty("SaveBtn")));
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", Agreechck);
						Thread.sleep(4000);
						log.logLine(Testname, false, "Click on Save button is successful");
					} else {
						log.logLine(Testname, true, "Click on Save button is failed");
						//throw new Exception("Click on Save button is failed");
					}
			    	
			    	
			    	// Validating the Message
			    	if (doesElementExist(properties.getProperty("SaveValidation"), 5)) {
						String Vldn = driver.findElement(By.xpath(properties.getProperty("SaveValidation"))).getText();
						log.logLine(Testname, false, "Email Validation message is Successful -------"+Vldn+"");
					} else {
						log.logLine(Testname, true, "Email Validation message is failed");
						throw new Exception("Email Validation message is failed");
					}
			   
			    	
			    	//EmailValidation(Testname);
		
			         				    	
		    	// Switching back to parent window
		    	driver.close();
				driver.switchTo().window(firstWinHandle);
			    }
		    }
	
	return true;
	}
	
	
	public boolean EmailValidation(String Testname) throws Exception {
		
		Thread.sleep(150000);
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
            	log.logLine(Testname, false,"The From Mail Reads as "+address.toString()+" in Inbox  ");	

            }
            Multipart mp = (Multipart) msg.getContent();
            BodyPart bp = mp.getBodyPart(0);
            Thread.sleep(1000);
            System.out.println("SUBJECT:" + msg.getSubject());
            log.logLine(Testname, false,"The Subject reads as "+msg.getSubject()+"");	
            
            Thread.sleep(1000);
            System.out.println("CONTENT:" + bp.getContent());
            log.logLine(Testname, false,"The Content reads as "+bp.getContent()+" in Inbox  ");
            String arr[] =bp.getContent().toString().split("Please copy and past the link below into you internet browser to complete your consent confirmation process.");
            String Content=arr[1];
            String array[]=Content.split("Sky Bklue Team");
            String Link = array[0];
            String Link1[] = array[0].split("Regards,");
            String FinalLink= Link1[0].trim();
            log.logLine(Testname, false,"The Final Link is "+FinalLink+" in Inbox  ");
            

	    	driver.get(FinalLink);
	        
	    	// Validating the Content
            Thread.sleep(4000);
            if (doesElementExist(properties.getProperty("Deliveryverification"), 5)) {
				String Delvalidtn = driver.findElement(By.xpath(properties.getProperty("Deliveryverification"))).getText();
				log.logLine(Testname, false, "New Link contains the message as "+Delvalidtn+"" );
				log.logLine(Testname, false, "Validating the Email is successful");
			} else {
				log.logLine(Testname, true, "Opening the New link is failed");
				throw new Exception("Opening the New link is failed");
			}
    
            
        } catch (Exception mex) {
            mex.printStackTrace();
        }       
		
		return true;
	}
	
	
	
	public boolean ForgotUserID(String Testname) throws Exception {
		
		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	
	    String envi = Initialization.EnvirSite.toUpperCase();
		
		String Emladdress=test.readColumnData("EmailAddress", sheetname);
		String CnsmerID=test.readColumnData("ConsumerID", sheetname);
		String Passwrd=test.readColumnData("Password", sheetname);
		
		
		if (doesElementExist2(properties.getProperty("VerfyUsrID"), 5)) {
			String UsrId = driver.findElement(By.cssSelector(properties.getProperty("VerfyUsrID"))).getText();
			log.logLine(Testname, false, "Reading the Label as  "+UsrId+"");
		} else {
			log.logLine(Testname, true, "Reading the Label is failed");
			throw new Exception("Reading the Label is failed");
		}
		
		
		
		if (doesElementExist(properties.getProperty("ForgotUserID"), 5)) {
			WebElement Frgusrnme = driver.findElement(By.xpath(properties.getProperty("ForgotUserID")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Frgusrnme);
			log.logLine(Testname, false, "Clicked on Forgot UserID Link under edelivery application");
		} else {
			log.logLine(Testname, true, "Clicked on Forgot UserID Link under edelivery application failed");
			throw new Exception("Clicked on Forgot UserID Link under edelivery application failed");
		}
    	
    	
    	if (doesElementExist(properties.getProperty("ConsumerID"), 5)) {
			WebElement CnsmidID = driver.findElement(By.xpath(properties.getProperty("ConsumerID")));
			CnsmidID.clear();
			CnsmidID.sendKeys(CnsmerID);
			log.logLine(Testname, false, "Entered the Consumer ID as "+CnsmerID+" under edelivery application");
		} else {
			log.logLine(Testname, true, "Entering the Consumer ID under edelivery application failed");
			throw new Exception("Entering the Consumer ID under edelivery application failed");
		}
    	
    	
    	if (doesElementExist(properties.getProperty("UserEmailAddress"), 5)) {
			WebElement EmlAddss = driver.findElement(By.xpath(properties.getProperty("UserEmailAddress")));
			EmlAddss.clear();
			EmlAddss.sendKeys(Emladdress);
			log.logLine(Testname, false, "Entering the Email Address as "+Emladdress+"");
		}else {
			log.logLine(Testname, true, "Entering the Email Address is failed");
			throw new Exception("Entering the Email Address is failed");
		}
    	
    	
    	if (doesElementExist(properties.getProperty("SendBtn"), 5)) {
			WebElement Agreechck = driver.findElement(By.xpath(properties.getProperty("SendBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Agreechck);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Click on Send button is successful");
		} else {
			log.logLine(Testname, true, "Click on Send button is failed");
			throw new Exception("Click on Send button is failed");
		}
    	
    	
    	//Click on Link
    	if (doesElementExist2(properties.getProperty("UserLink"), 5)) {
			WebElement Snd = driver.findElement(By.cssSelector(properties.getProperty("UserLink")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Snd);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Click on Link is successful");
		} else {
			log.logLine(Testname, true, "Click on Link is unsuccessful");
			throw new Exception("Click on Link is unsuccessful");
		}
		
    	Thread.sleep(150000);
		Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        try {
            Session session = Session.getInstance(props, null);
            Store store = session.getStore();            
            store.connect("imap.gmail.com", "automationpivot@gmail.com", "miracle123");
            Folder inbox = store.getFolder("INBOX");
            driver.navigate().refresh();
            inbox.open(Folder.READ_ONLY);
            Message msg = inbox.getMessage(inbox.getMessageCount());
            Address[] in = msg.getFrom();
            for (Address address : in) {
            	Thread.sleep(1000);
                System.out.println("FROM:" + address.toString());
            	log.logLine(Testname, false,"The From Mail Reads as "+address.toString()+" in Inbox  ");	

            }
            Multipart mp = (Multipart) msg.getContent();
            BodyPart bp = mp.getBodyPart(0);
            Thread.sleep(1000);
            System.out.println("SUBJECT:" + msg.getSubject());
            log.logLine(Testname, false,"The Subject reads as "+msg.getSubject()+"");	
            
            Thread.sleep(1000);
            System.out.println("CONTENT:" + bp.getContent());
            log.logLine(Testname, false,"The Content reads as "+bp.getContent()+" in Inbox  ");
            String arr[] =bp.getContent().toString().split("Here is your user name:");
            String name=arr[0];
            String Content=arr[1];
         //  String arra[] = bp.getContent().toString().split(""+name+""+Content+"");
           // String Content = arra[1];
            String array[]=Content.split("Login into the site using https://skyblue-stage.edelivery-view.com");
            UserName= array[0].trim();
            
            log.logLine(Testname, false,"The User Name read form the mail is   "+UserName+"");
            log.logLine(Testname, false, "Validating the Email is successful");
            
            
            
         // Entering the UN and PWD with new user Id
	    	if (doesElementExist2(properties.getProperty("UserName"), 5)) {
				WebElement Usernme = driver.findElement(By.cssSelector(properties.getProperty("UserName")));
				Usernme.sendKeys(UserName);
				log.logLine(Testname, false, "Entered the User name as "+UserName+" Link under edelivery application");
			} else {
				log.logLine(Testname, true, "Entering the User name under edelivery application failed");
				throw new Exception("Entering the User name under edelivery application failed");
			}
	        
	        if (doesElementExist2(properties.getProperty("Password"), 5)) {
				WebElement Psswd = driver.findElement(By.cssSelector(properties.getProperty("Password")));
				Psswd.sendKeys(Passwrd);
				log.logLine(Testname, false, "Entered the Password as "+Passwrd+" under edelivery application");
			} else {
				log.logLine(Testname, true, "Entering the Password under edelivery application failed");
				throw new Exception("Entering the Password under edelivery application failed");
			}
	    	
	        
	        // Click on Login Button
	    	if (doesElementExist2(properties.getProperty("LoginBtn"), 5)) {
				WebElement Lgnbtn = driver.findElement(By.cssSelector(properties.getProperty("LoginBtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", Lgnbtn);
				Thread.sleep(4000);
				log.logLine(Testname, false, "Click on Login button is successful");
				log.logLine(Testname, false, "Login successful with New Password");
			} else {
				log.logLine(Testname, true, "Click on Login button is unsuccessful");
				throw new Exception("Click on Login button is unsuccessful");
			}
	    	

	    	// Click on Logout Button
	    	if (doesElementExist(properties.getProperty("UserLogout"), 5)) {
				WebElement Logout = driver.findElement(By.xpath(properties.getProperty("UserLogout")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", Logout);
				Thread.sleep(4000);
				log.logLine(Testname, false, "Click on Logout Button is successful");
			} else {
				log.logLine(Testname, true, "Click on Logout Button is unsuccessful");
				throw new Exception("Click on Logout Button is unsuccessful");
				
			}
	    	
            
            
        } catch (Exception mex) {
            mex.printStackTrace();
        }       
		
		return true;
	}
	
	
	}



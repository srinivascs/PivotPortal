package pivotModules;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.ibm.icu.util.Calendar;

   public class ConfigMsgCenter extends Page{
	
	public ConfigMsgCenter(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}	
	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}
	
	protected static String msgTitle;
	protected static String messDescrip;
	protected static String msgTitleEdit;
	protected static String messDescripEdit;
	
	Actions action = new Actions(driver);
    

	public String MessageConfig1(String Randno, String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));	    

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
	    
	    Thread.sleep(1000);
	    if ((doesElementExist2(properties.getProperty("Adminlnk"), 5))) {			    
			WebElement oldpivt = driver.findElement(By.cssSelector(properties.getProperty("Adminlnk")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", oldpivt);
		
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicking on Admin - HA");		      
		}else {
			
			negativeCase(Testname, "", "", "Navigation to old pivot failed");				
		}
	    
	    
	    Set<String> handles = driver.getWindowHandles();
	    String firstWinHandle = driver.getWindowHandle(); 
	    handles.remove(firstWinHandle);
	    
	    boolean browserexist = handles.iterator().hasNext();
	    if (browserexist) {
		    String winHandle=handles.iterator().next();
		    if (winHandle!=firstWinHandle){
		    	
		    	//Switch control to new window
		    	driver.switchTo().window(winHandle);
		    	driver.manage().window().maximize();
		    	
		    	// Wait till the page loads all the elements in it.					
		    	WebElement retelm2 = waitForElement(properties.getProperty("ProfileAdmin"));
		    	
		    	
		    	//Verify User permission
				if (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSUPER")) {					
				
					if (doesElementExist2(properties.getProperty("Configlnktest"), 5)) {
						
						log.logLine(Testname, false, "Permission Verified: RRD Super has only access to Configuration menu in Legacy Admin");
						
					} else {
						
						log.logLine(Testname, true, "Access denied - RRD Super user does not have access to Configuration menu in Legacy Admin");
						driver.close();
						driver.switchTo().window(firstWinHandle);
						return "";
					}
				} else if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTUSER")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSITE")) 
						|| (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTADMIN")) || 
						(PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDCLIENT")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDUSER"))) {
					
					if (doesElementExist2(properties.getProperty("Configlnktest"), 5)) {
						
						negativeCase(Testname, firstWinHandle, "", "RRD Site, RRD Admin, RRD User, Client Admin & Client User should not have access to Configuration menu in Legacy Admin");							
						
					} else {
						
						log.logLine(Testname, false, "Permission Verified: RRD Site, RRD Admin, RRD User, Client Admin & Client User does not have access to Configuration menu in Legacy Admin");
						driver.close();
						driver.switchTo().window(firstWinHandle);
						return "";
					}
					
				}
		    	
		    	
	    	    			
			    if (doesElementExist2(properties.getProperty("Configlnktest"), 5)) {
					WebElement configlnk = driver.findElement(By.cssSelector(properties.getProperty("Configlnktest")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", configlnk);
					Thread.sleep(3000);
					log.logLine(Testname, false, "Navigating to Admin - Configuaration link is successfull..");
			    
			    } else if (doesElementExist2(properties.getProperty("Configlnkstage"), 5)) {			
					WebElement configlnk = driver.findElement(By.cssSelector(properties.getProperty("Configlnkstage")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", configlnk);
					Thread.sleep(3000);
					log.logLine(Testname, false, "Navigating to Admin - Configuaration link is successfull..");
				
			    }else {
			    	
			    	negativeCase(Testname, firstWinHandle, "", "Navigating to Admin - Configuaration link is failed");				    	
			    }			
				
				//Move the mouse on Welcome text to avoid blinking
			    Actions builder = new Actions(driver);
				WebElement hellolbl = driver.findElement(By.cssSelector(properties.getProperty("HelloUserName")));
				builder.moveToElement(hellolbl).perform();		
				Thread.sleep(1000);
				
				if (doesElementExist2(properties.getProperty("DocumtAdmin"), 5)) {
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("DocumtAdmin")));
					for (WebElement lnk:selopts) {
						if (lnk.getText().equals("Documentation Admin")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							log.logLine(Testname, false, "Selecting the Documentation Admin in Admin->Config..");
							break;
						}
					}
					
				} else {
					negativeCase(Testname, firstWinHandle, "", "Clicking on the Admin/Config - Documentation Admin link is failed");						
				}						
				
				
				deleteMessages(Testname, firstWinHandle);
				
				Thread.sleep(4000);					
				msgTitle = "AutoMsg Title-"+Randno;
				if (doesElementExist2(properties.getProperty("MsgTitle"), 5)) {
					WebElement messagetitle = driver.findElement(By.cssSelector(properties.getProperty("MsgTitle")));
					messagetitle.clear();
					
					if (Initialization.Browser.equalsIgnoreCase("ie") || Initialization.Browser.equalsIgnoreCase("internetexplorer")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', arguments[1])", messagetitle, msgTitle);
					//action.sendKeys(messagetitle, msgTitle).perform();
					}else {
						messagetitle.sendKeys(msgTitle);
					}
					log.logLine(Testname, false, "Entering the message title in the Text box Message Title in - Documentation Admin link is successful");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Entering the message title in the Text box Message Title in - Documentation Admin link is failed");
					
				}
			
				messDescrip = "AutoMsg in BETA_"+Randno;
				if (doesElementExist2(properties.getProperty("MsgDescp"), 5)) {
					WebElement messageDescrip = driver.findElement(By.cssSelector(properties.getProperty("MsgDescp")));
					messageDescrip.clear();
					
					if (Initialization.Browser.equalsIgnoreCase("ie") || Initialization.Browser.equalsIgnoreCase("internetexplorer")) {
						//((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', arguments[1])", messageDescrip, messDescrip);
						((JavascriptExecutor) driver).executeScript("arguments[0].value='"+messDescrip+"';", messageDescrip);
					//action.sendKeys(messageDescrip, messDescrip).perform();
					}else {
						messageDescrip.sendKeys(messDescrip);
					}
					log.logLine(Testname, false, "Entering the Description of the message in the Text box Message Description in - Documentation Admin link ");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Entering the Description of the message in the Text box Message Description in - Documentation Admin link is failed");						
				}
			
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
				Date date = new Date();
				String todaysDate = dateFormat.format(date);
		
	     	
				if (doesElementExist2(properties.getProperty("StartDate"), 5)) {	    
				     WebElement selcalendbtn = driver.findElement(By.cssSelector(properties.getProperty("StartDate")));		   
				     selcalendbtn.clear();
				     action.sendKeys(selcalendbtn, todaysDate).perform();
				     //selcalendbtn.sendKeys(todaysDate);
				     log.logLine(Testname, false, "Entering the current date in the StartDate text box ");
			    } else {
			    	 negativeCase(Testname, firstWinHandle, "", "Entering the current date in the StartDate text box is failed");					     
			    }
			
				Calendar currentDate = Calendar.getInstance(); 
				SimpleDateFormat formatter= new SimpleDateFormat("MM/dd/yyyy"); 
				currentDate.add(Calendar.DAY_OF_MONTH, 5); 
				String futureDate = formatter.format(currentDate.getTime()); 
	
						
				if (doesElementExist2(properties.getProperty("EndDate"), 5)) {	    
				     WebElement selcalendend = driver.findElement(By.cssSelector(properties.getProperty("EndDate")));		   
				     selcalendend.clear();
				     action.sendKeys(selcalendend, futureDate).perform();
				     //selcalendbtn.sendKeys(futureDate);
				     log.logLine(Testname, false, "Entering the current date plus 5 days in the EndDate date text box");
			    } else {
			    	 negativeCase(Testname, firstWinHandle, "", "Entering the current date plus 5 days in the EndDate date text box is failed");					     
			    }
			
				if (doesElementExist2(properties.getProperty("DisplayChkBox"), 5)) {	    
					WebElement selcalendbtn = driver.findElement(By.cssSelector(properties.getProperty("DisplayChkBox")));		   
					if ( !selcalendbtn.isSelected())
				     {
				    	 selcalendbtn.click();
				     }
					log.logLine(Testname, false, "Checking the checkbox Display in Message center");
			     } else {
			    	 negativeCase(Testname, firstWinHandle, "", "Checking the checkbox Display in Message center is failed");					     
			     }
				
				
				if (doesElementExist2(properties.getProperty("selectservice"), 5)) {
					WebElement SelAllLay = driver.findElement(By.cssSelector(properties.getProperty("selectservice")));
					
					Select option = new Select(SelAllLay);
					option.deselectByVisibleText("(select service)");
					
					option.selectByVisibleText("All Layers");
					log.logLine(Testname, false, "Clicking on All layers option from the Tool list in Archives message center ");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Clicking on All layers option from the Tool list in Archives message center is failed");						
				}
				
				
				if (doesElementExist2(properties.getProperty("nonselectclient"), 5)) {
					WebElement SelAllLay = driver.findElement(By.cssSelector(properties.getProperty("nonselectclient")));
					
					Select option = new Select(SelAllLay);
					option.deselectByVisibleText("(select client)");
					
					option.selectByVisibleText("Regression Testing - DO NOT TOUCH");
					log.logLine(Testname, false, "Clicking on All clients nonskinned option from the client nonskinned list in Archives message center ");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Clicking on All clients nonskinned option from the client nonskinned list in Archives message center is failed");						
				}
				
				
				if (doesElementExist2(properties.getProperty("selectusertype"), 5)) {
					WebElement SelAllLay = driver.findElement(By.cssSelector(properties.getProperty("selectusertype")));
					
					Select option = new Select(SelAllLay);
					option.deselectByVisibleText("(select usertype)");
					
					option.selectByVisibleText("All Users");
					log.logLine(Testname, false, "Clicking on All users  option from the User Type list in Archives message center ");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Clicking on All users option from the User Type  list in Archives message center is failed");						
				}
				
						
				String FileUplpad = ConfigMsgCenter.EDITbetaPDFCreate(Randno);
				if (doesElementExist2(properties.getProperty("browsebtn"), 5)) {
					driver.findElement(By.cssSelector(properties.getProperty("browsebtn"))).sendKeys(FileUplpad);
					Thread.sleep(2000);
				    log.logLine(Testname, false, "click on browse button to upload a pdf file "+FileUplpad+" ");
				} else {
					negativeCase(Testname, firstWinHandle, "", "clicking on browse button to upload a pdf file "+FileUplpad+" is failed");					    
				}
			
				
				if (doesElementExist2(properties.getProperty("uploaddocbtn"), 5)) {
					WebElement dcumntAdlnk = driver.findElement(By.cssSelector(properties.getProperty("uploaddocbtn")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", dcumntAdlnk);
				  	log.logLine(Testname, false, "Clicking on the Admin/Config - Documentation Admin link is successful");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Clicking on the Admin/Config - Documentation Admin link is failed");						
				}
			
				Thread.sleep(10000);
				String upldsuccess = "Document Successfully Uploaded!" ;
				if (isTextPresent(upldsuccess)) {
					
					log.logLine(Testname, false, "Message "+upldsuccess+" is present at the top of the page");	
					    
				} else {
					negativeCase(Testname, firstWinHandle, "", "Message "+upldsuccess+" is not present at the top of the page");						
				}
				
				driver.close();
		    }
	    }			
		
		return firstWinHandle;
   	}
   
   
   public boolean deleteMessages(String Testname, String firstWinHandle) throws Exception {
   	
    	   String[] titles = new String[10];
    	   String row= "tr"; 
    	   
    	   //WebElement table = driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_gv_list']"));
    	   
    	   List<WebElement> msgtitleCnt= driver.findElements(By.xpath(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_gv_list']/tbody/tr"));
    	   
    	   if(doesElementExist2(properties.getProperty("MsgTitleHeader"), 5)){
    		   for(int m=0; m<msgtitleCnt.size();m++){
    			   
    			   if (!doesElementExist2("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_gv_list'] tbody "+row+" td", 5)) 
    				   return true;
    			   
    			   titles[m] = driver.findElement(By.cssSelector("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_gv_list'] tbody "+row+" td")).getText();
					
					if(titles[m].contains("Automation") || titles[m].contains("Auto")){
						
						log.logLine(Testname, false, "Iterating through the message title rows ");
						
						if (doesElementExist2("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_gv_list'] tbody "+row+" td+td+td+td+td+td input[onclick='javascript:return DeleteClient();']", 5)) {
							WebElement deletebtn=driver.findElement(By.cssSelector("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_gv_list'] tbody "+row+" td+td+td+td+td+td input[onclick='javascript:return DeleteClient();']"));
							//((JavascriptExecutor) driver).executeScript("arguments[0].click()", deletebtn);
							deletebtn.click();
							Thread.sleep(1000);
							log.logLine(Testname, false, "Clicking on the \"Delete\" button to delete the existing message titles");
						}
						
						Alert alert = driver.switchTo().alert();
						alert.accept();
						
						Thread.sleep(4000);
						
					} else {
						row = row + "+tr";
					}
    		   }
    		   
    		   
    		   
    	   }
    	   
    	   return true;
   }



   	public String MessageConfig2(String AccNo, String Testname) throws Exception {
	
		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));	    
    
	    Thread.sleep(1000);
	    if ((doesElementExist2(properties.getProperty("Adminlnk"), 5))) {			    
			WebElement oldpivt = driver.findElement(By.cssSelector(properties.getProperty("Adminlnk")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", oldpivt);
		
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicking on Admin - HA..");		      
		}else {
			negativeCase(Testname, "", "", "Navigation to old pivot failed");				
		}
	    
	    Thread.sleep(25000);
	    Set<String> handles = driver.getWindowHandles();
	    String firstWinHandle = driver.getWindowHandle(); 
	    handles.remove(firstWinHandle);
	    
	    boolean browserexist = handles.iterator().hasNext();
	    if (browserexist) {
		    String winHandle=handles.iterator().next();
		    if (winHandle!=firstWinHandle){
		    	
		    	//Switch control to new window
		    	driver.switchTo().window(winHandle);
		    	driver.manage().window().maximize();
		    	
			    			
			    if (doesElementExist2(properties.getProperty("Configlnktest"), 5)) {
					WebElement configlnk = driver.findElement(By.cssSelector(properties.getProperty("Configlnktest")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", configlnk);
					Thread.sleep(3000);
					log.logLine(Testname, false, "Navigating to Admin - Configuaration link is successfull..");		
					
			    } else if (doesElementExist2(properties.getProperty("Configlnkstage"), 5)) {			
					WebElement configlnk = driver.findElement(By.cssSelector(properties.getProperty("Configlnkstage")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", configlnk);
					Thread.sleep(3000);
					log.logLine(Testname, false, "Navigating to Admin - Configuaration link is successfull..");
				}else {
					negativeCase(Testname, firstWinHandle, "", "Clicking on the Admin/Config - Documentation Admin link is failed");						
				}
					
				//Move the mouse on Welcome text to avoid blinking
			    //Actions builder = new Actions(driver);
				/*if (doesElementExist2(properties.getProperty("HelloUserName"), 5)) {
					WebElement hellolbl = driver.findElement(By.cssSelector(properties.getProperty("HelloUserName")));
					builder.moveToElement(hellolbl).perform();
				}*/
				
				if (doesElementExist2(properties.getProperty("DocumtAdmin"), 5)) {						
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("DocumtAdmin")));
					for (WebElement lnk:selopts) {
						if (lnk.getText().equals("Documentation Admin")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							log.logLine(Testname, false, "Selecting the Documentation Admin in Admin->Config..");
							break;
						}
					}
					
				} else {
					negativeCase(Testname, firstWinHandle, "", "Clicking on the Admin/Config - Documentation Admin link is failed");						
				}
				
				Thread.sleep(4000);
				if (doesElementExist("//td[contains(text(), '"+ConfigMsgCenter.msgTitle+"')]/../td/input[@class='Button']", 5)) {
					driver.findElement(By.xpath("//td[contains(text(), '"+ConfigMsgCenter.msgTitle+"')]/../td/input[@class='Button']")).click();
					Thread.sleep(1000);
				  	log.logLine(Testname, false, "Clicking on Edit button to the edit already present message ");
			    } else {
			    	negativeCase(Testname, firstWinHandle, "", "Clicking on Edit button is failed");					   
				}
							
				
				int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);
				String AccNo2 = Integer.toString(paperID);
				
				msgTitleEdit = "Auto Edit-"+AccNo2;
				if (doesElementExist2(properties.getProperty("MsgTitle"), 5)) {
					WebElement messagetitle = driver.findElement(By.cssSelector(properties.getProperty("MsgTitle")));
					messagetitle.clear();
					
					if (Initialization.Browser.equalsIgnoreCase("ie") || Initialization.Browser.equalsIgnoreCase("internetexplorer")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].value='"+msgTitleEdit+"';", messagetitle);
						
					//action.sendKeys(messagetitle, msgTitleEdit).perform();
					}else {
						messagetitle.sendKeys(msgTitleEdit);
					}
					log.logLine(Testname, false, "Editing the message title in the Text box Message Title in - Documentation Admin link ");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Editing the message title in the Text box Message Title in - Documentation Admin link is failed");
				}
			
				//String messDescrip = test.readInputData("MessgDescrip", sheetname);
				messDescripEdit = "Modified msg in BETA";
				if (doesElementExist2(properties.getProperty("MsgDescp"), 5)) {
					WebElement messageDescrip = driver.findElement(By.cssSelector(properties.getProperty("MsgDescp")));
					messageDescrip.clear();
					
					if (Initialization.Browser.equalsIgnoreCase("ie") || Initialization.Browser.equalsIgnoreCase("internetexplorer")) {
						//((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', arguments[1])", messageDescrip, messDescripEdit);
						((JavascriptExecutor) driver).executeScript("arguments[0].value='"+messDescripEdit+"';", messageDescrip);
					//action.sendKeys(messageDescrip, messDescripEdit).perform();
					}else {
						messageDescrip.sendKeys(messDescripEdit);
					}
					log.logLine(Testname, false, "Entering the Description of the message in the Text box Message Description in - Documentation Admin link ");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Entering the Description of the message in the Text box Message Description in - Documentation Admin link is failed");
				}
				
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
				Date date = new Date();
				String todaysDate = dateFormat.format(date);
		
	     	
				if (doesElementExist2(properties.getProperty("StartDate"), 5)) {	    
				     WebElement selcalendbtn = driver.findElement(By.cssSelector(properties.getProperty("StartDate")));		   
				     selcalendbtn.clear();
				     action.sendKeys(selcalendbtn, todaysDate).perform();
				     //selcalendbtn.sendKeys(todaysDate);
				     log.logLine(Testname, false, "Entering the current date in the StartDate text box ");
			    } else {
			    	 negativeCase(Testname, firstWinHandle, "", "Entering the current date in the StartDate text box is failed");					     
			    }
								
				Calendar currentDate = Calendar.getInstance(); 
				SimpleDateFormat formatter= new SimpleDateFormat("MM/dd/yyyy"); 
				currentDate.add(Calendar.DAY_OF_MONTH, 5); 
				String futureDate = formatter.format(currentDate.getTime());		
						
				if (doesElementExist2(properties.getProperty("EndDate"), 5)) {	    
				     WebElement selcalendend = driver.findElement(By.cssSelector(properties.getProperty("EndDate")));		   
				     selcalendend.clear();
				     action.sendKeys(selcalendend, futureDate).perform();
				     //selcalendbtn.sendKeys(futureDate);
				     log.logLine(Testname, false, "Entering the current date plus 5 days in the EndDate date text box");
			    } else {
			    	 negativeCase(Testname, firstWinHandle, "", "Entering the current date plus 5 days in the EndDate date text box is failed");					     
			    }
				
				if (doesElementExist2(properties.getProperty("UpdtEdit"), 5)) {
				    WebElement updatebtn = driver.findElement(By.cssSelector(properties.getProperty("UpdtEdit")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", updatebtn);
					Thread.sleep(1000);
				  	log.logLine(Testname, false, "Clicked on the update button after editing the document in - Documentation Admin link ");
			    } else {
			    	negativeCase(Testname, firstWinHandle, "", "Clicking on the update button after editing the document in - Documentation Admin link  is failed");					    
				}
				
				Thread.sleep(10000);
				String Edtupldsuccess = "Document Successfully Updated!" ;
				if (isTextPresent(Edtupldsuccess)) {
					log.logLine(Testname, false, "Message "+Edtupldsuccess+" is present at the top of the page");	
					    
				} else {
					negativeCase(Testname, firstWinHandle, "", "Message "+Edtupldsuccess+" is not present at the top of the page");
				}
				
				
				driver.close();
		    }
	    }			
		
		return firstWinHandle;
   	}
	
			
	
	public String MessageConfig3(String AccNo, String Testname) throws Exception {
	
		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
				
		Thread.sleep(10000);
	    if ((doesElementExist2(properties.getProperty("Adminlnk"), 5))) {			    
			WebElement oldpivt = driver.findElement(By.cssSelector(properties.getProperty("Adminlnk")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", oldpivt);
		
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicking on old Pivot..");		      
		}else {
			negativeCase(Testname, "", "", "Navigation to old pivot failed");
		}
	    
	    Thread.sleep(25000);
	    Set<String> handles = driver.getWindowHandles();
	    String firstWinHandle = driver.getWindowHandle(); 
	    handles.remove(firstWinHandle);
	    
	    boolean browserexist = handles.iterator().hasNext();
	    if (browserexist) {
		    String winHandle=handles.iterator().next();
		    if (winHandle!=firstWinHandle){
		    
		    	//Switch control to new window
		    	driver.switchTo().window(winHandle);
		    	driver.manage().window().maximize();
		    	
		    	// Wait till the page loads all the elements in it.
				//WebElement retelm2 = waitForElement(properties.getProperty("ClintAppMnu"));
	    	    
			    Actions builder = new Actions(driver);			
			    if (doesElementExist2(properties.getProperty("Configlnktest"), 5)) {			
					WebElement configlnk = driver.findElement(By.cssSelector(properties.getProperty("Configlnktest")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", configlnk);
					Thread.sleep(3000);
					log.logLine(Testname, false, "Navigating to Admin - Configuaration link is successfull..");
				} else if (doesElementExist2(properties.getProperty("Configlnkstage"), 5)) {			
					WebElement configlnk = driver.findElement(By.cssSelector(properties.getProperty("Configlnkstage")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", configlnk);
					Thread.sleep(3000);
					log.logLine(Testname, false, "Navigating to Admin - Configuaration link is successfull..");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Clicking on the Admin/Config - Documentation Admin link is failed");						
				}
				
				if (doesElementExist2(properties.getProperty("HelloUserName"), 5)) {
					WebElement hellolbl = driver.findElement(By.cssSelector(properties.getProperty("HelloUserName")));
					builder.moveToElement(hellolbl).perform();
				}
	
	
				if (doesElementExist2(properties.getProperty("DocumtAdmin"), 5)) {
					
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("DocumtAdmin")));
					for (WebElement lnk:selopts) {
						if (lnk.getText().equals("Documentation Admin")) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(5000);
							log.logLine(Testname, false, "Selecting the Documentation Admin in Admin->Config..");
							break;
						}
					}
					
				} else {
					negativeCase(Testname, firstWinHandle, "", "Clicking on the Admin/Config - Documentation Admin link is failed");						
				}
				
				Thread.sleep(10000);
				if (doesElementExist("//td[contains(text(), '"+ConfigMsgCenter.msgTitleEdit+"')]/../td/input[@value='Delete']", 5)) {
					driver.findElement(By.xpath("//td[contains(text(), '"+ConfigMsgCenter.msgTitleEdit+"')]/../td/input[@value='Delete']")).click();
					Thread.sleep(3000);
					log.logLine(Testname, false, "Clicking on the Delete button for the search document - Documentation Admin link is successfull");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Clicking on the Delete button is failed");
				}
				
					
				Alert alert = driver.switchTo().alert();
				alert.accept();
				
				
				Thread.sleep(10000);
				String Deletesuccess = "Document Successfully Deleted!" ;
				if (isTextPresent(Deletesuccess)) {
					log.logLine(Testname, false, "Message "+Deletesuccess+" is present at the top of the page");	
					    
				} else {
					negativeCase(Testname, firstWinHandle, "", "Message "+Deletesuccess+" is not present at the top of the page");
				}

			driver.close();
		    }
	   	}			
		
		return firstWinHandle;
	}



   public static String EDITbetaPDFCreate(String Randno) throws Exception {
	
			InputStream inStream = null;
		    OutputStream outStream = null;
		    
		    File file1, file2 = null;
    
		    try { 
		        file1 = new File("C:/Pivot_Portal/Test Data/AutoTestData.pdf");
		        file2 = new File("C:/Pivot_Portal/Test Data/AutoPDF.pdf");
		        
		        
		        inStream = new FileInputStream(file1);
		        outStream = new FileOutputStream(file2); // for override file content
		
		        byte[] buffer = new byte[1024]; 
		        int length;
		        while ((length = inStream.read(buffer)) > 0){
		            outStream.write(buffer, 0, length);
		        }

		        if (inStream != null)inStream.close();
		        if (outStream != null)outStream.close();
		        
        
		    }catch(IOException e){
	        e.printStackTrace();
	    }
    
		    return (file2.getAbsolutePath());	
	
       }
   }
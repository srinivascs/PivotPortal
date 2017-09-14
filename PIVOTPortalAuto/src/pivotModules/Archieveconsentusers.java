package pivotModules;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import launchAuto.Initialization;
import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Keys;

import bsh.ParseException;

import com.opera.core.systems.scope.protos.EcmascriptProtos.EvalResult.Status;

public class Archieveconsentusers extends Page{

	public Archieveconsentusers(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}

	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}


	String firstWinHandle ;
	String secondWinHandle;
	int skybluegridrow=0;
	String[][] Skybluedata= new String[50][50];

	long GmailstartTime;
	String ClientIDS="ABC Company";
	String App="ABC1234 - ABC1234";
	String Cons="012139698";
	String linktext=null;
	String todaysDate=null;
	String fieldindx=null;
	String status=" ";
	String strI=" ";
	String consumerid=" ";
	String user=" ";
	String Text=" ";
	String ClientID=" ";


	public boolean edelivery_consent(String Testname) throws Exception {

		ClientAppsrch_eDelivery(Testname);
		Thread.sleep(4000);
		Skyblueapp(Testname);
		Thread.sleep(2000);
		consentselection_change(Testname);	
		return  true;
	}


	public void Skyblueapp(String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	
		String envi = Initialization.EnvirSite.toUpperCase();

		String Usrnme=test.readColumnData("UserName", sheetname);
		String Pswd=test.readColumnData("Password", sheetname);

		String Emladd=test.readColumnData("EmailAddress", sheetname);
		String Emladd1=test.readColumnData("EmailAddress1", sheetname);
		String Message=test.readColumnData("Message", sheetname);
		String Message1=test.readColumnData("Message1", sheetname);
		Thread.sleep(2000);

		if(envi.equalsIgnoreCase("stage")){
			driver.get("https://skyblue-stage.edelivery-view.com/");}
		else{driver.get("https://skyblue.edelivery-view.com/");}

		Thread.sleep(3000);
		WebElement retelm = waitForElement(properties.getProperty("Skblu_id"));


		if (doesElementExist2(properties.getProperty("Skblu_id"), 5)) {	    
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Skblu_id")));
			gmailid.clear();
			gmailid.sendKeys(Usrnme);
			log.logLine(Testname, false, "Entering the skyblue user id .."+Usrnme);				

		} else {
			negativeCase(Testname, firstWinHandle, "", "Entering the skyblue user id  failed");			
		}


		if (doesElementExist2(properties.getProperty("Skblu_pwd"), 5)) {	    
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Skblu_pwd")));
			gmailid.clear();
			gmailid.sendKeys(Pswd);
			log.logLine(Testname, false, "Entering the skyblue password.."+Pswd);			

		}  else {
			negativeCase(Testname, firstWinHandle, "", "Entering the password ID is failed");				
		} 	


		Thread.sleep(1000);		
		if (doesElementExist2(properties.getProperty("Login"), 5)) {	    
			WebElement skybluSign = driver.findElement(By.cssSelector(properties.getProperty("Login")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", skybluSign);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the skyblue SignIn");			
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicked on the skyblue SignIn is failed");			
		}

		Thread.sleep(4000);
		if (doesElementExist(properties.getProperty("Documents"), 5)) {	    
			WebElement skybluSign = driver.findElement(By.xpath(properties.getProperty("Documents")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", skybluSign);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the skyblue Documents button");			
		} 
		else if (doesElementExist2(properties.getProperty("Documents1"), 5)) {	    
			WebElement skybluSign = driver.findElement(By.cssSelector(properties.getProperty("Documents1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", skybluSign);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the skyblue Documents button");			
		} else if (doesElementExist2(properties.getProperty("Document_prod"), 5)) {	    
			WebElement skybluSign = driver.findElement(By.cssSelector(properties.getProperty("Document_prod")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", skybluSign);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the skyblue Documents button");			
		}else if (doesElementExist2(properties.getProperty("Document_prod1"), 5)) {	    
			WebElement skybluSign = driver.findElement(By.cssSelector(properties.getProperty("Document_prod1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", skybluSign);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the skyblue Documents button");			
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicked on the skyblue Documents button is failed");			
		}


		String Docstart= test.readColumnData("Docstartdate", sheetname);
		Doctype_generatefieldvalidation(Testname,Docstart);

		// Click on Delivery Option Tab
		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("DeliveryOptions"), 5)) {
			WebElement Deloptn = driver.findElement(By.cssSelector(properties.getProperty("DeliveryOptions")));
			String data=Deloptn.getText();
			if(data.equalsIgnoreCase("Delivery Options"))
			{((JavascriptExecutor) driver).executeScript("arguments[0].click()", Deloptn);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicked on Delivery Options options menu");
			}
			else if (doesElementExist2(properties.getProperty("DeliveryOptions1"), 5)) {
				WebElement Deloptn1 = driver.findElement(By.cssSelector(properties.getProperty("DeliveryOptions1")));
				String data1=Deloptn.getText();
				if(data1.equalsIgnoreCase("Delivery Options"))
				{((JavascriptExecutor) driver).executeScript("arguments[0].click()", Deloptn1);
				Thread.sleep(4000);
				log.logLine(Testname, false, "Clicked on Delivery Options options menu");
				}
				else if (doesElementExist2(properties.getProperty("DeliveryOptions2"), 5)) {
					WebElement Deloptn2 = driver.findElement(By.cssSelector(properties.getProperty("DeliveryOptions1")));
					String data2=Deloptn.getText();
					if(data2.equalsIgnoreCase("Delivery Options"))
					{((JavascriptExecutor) driver).executeScript("arguments[0].click()", Deloptn2);
					Thread.sleep(4000);
					log.logLine(Testname, false, "Clicked on Delivery Options options menu");
					}

				}else if (doesElementExist2(properties.getProperty("DeliveryOptions3"), 5)) {
					WebElement Deloptn2 = driver.findElement(By.cssSelector(properties.getProperty("DeliveryOptions3")));
					String data2=Deloptn.getText();
					if(data2.equalsIgnoreCase("Delivery Options"))
					{((JavascriptExecutor) driver).executeScript("arguments[0].click()", Deloptn2);
					Thread.sleep(4000);
					log.logLine(Testname, false, "Clicked on Delivery Options options menu");
					}

				}}}else {
					log.logLine(Testname, true, "Clicked on Delivery Options is failed");
					negativeCase(Testname, firstWinHandle, "", "Clicked on Delivery Options is failed");			
				}

		Thread.sleep(3000);

		if (doesElementExist2(properties.getProperty("GoonlineBlue"), 5)) {
			WebElement Gordo = driver.findElement(By.cssSelector(properties.getProperty("GoonlineBlue")));
			if (Gordo.isSelected())
			{log.logLine(Testname, false, "Selecting Go online and save the Planet for Blue document is already Checked");
			}else{
				Gordo.click();				
				log.logLine(Testname, false, "Selecting Go online and save the Planet for Blue document is successful");
			}
		}

		//Terms & conditions validations

		if (doesElementExist(properties.getProperty("terms&condhyperlink"), 5)) {	    
			WebElement terms = driver.findElement(By.xpath(properties.getProperty("terms&condhyperlink")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",terms);
			Thread.sleep(1000);
			String  termdata = driver.findElement(By.xpath(properties.getProperty("termpopuptitle"))).getText();
			if(termdata.equalsIgnoreCase("Terms & Conditions"))
			{log.logLine(Testname, false, "We are now navigated to conditions popup");
			WebElement termclose = driver.findElement(By.xpath(properties.getProperty("termclose")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",termclose);
			}
			else
			{log.logLine(Testname, true, "Unable to navigate to conditions popup");
			negativeCase(Testname, firstWinHandle, "", "Unable to navigate to conditions popup");	

			}					
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the Agree to terms & conditions checkbox failed");			
		}


		if (doesElementExist2(properties.getProperty("emailadd"), 5)) {	    
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("emailadd")));
			gmailid.clear();	
			log.logLine(Testname, false, "Deleting the email address");				
		} else {
			negativeCase(Testname, firstWinHandle, "", "Deleting the email address failed");			
		}

		if (doesElementExist2(properties.getProperty("chkagree"), 5)) {	    
			WebElement chkagree = driver.findElement(By.cssSelector(properties.getProperty("chkagree")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkagree);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the Agree to terms & conditions checkbox");			
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the Agree to terms & conditions checkbox failed");			
		}

		if (doesElementExist2(properties.getProperty("save"), 5)) {	    
			WebElement save = driver.findElement(By.cssSelector(properties.getProperty("save")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
			log.logLine(Testname, false, "Clicked on the save button");			
		} else if (doesElementExist(properties.getProperty("save1"), 5)) {	    
			WebElement save = driver.findElement(By.xpath(properties.getProperty("save1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
			log.logLine(Testname, false, "Clicked on the save button");			
		}else {
			log.logLine(Testname, true, "Selecting save button is  failed");	
			negativeCase(Testname, firstWinHandle, "", "Selecting save button is  failed");			
		}

		Thread.sleep(1000);

		if (doesElementExist(properties.getProperty("msg"), 5)) {	    		
			String  termdata = driver.findElement(By.xpath(properties.getProperty("msg"))).getText();
			if( termdata.contains("Could not save preferences. A valid email address is required to receive online delivery"))
			{log.logLine(Testname, false,termdata+ "message appeared");
			}
			else if(termdata.contains(Message))
			{log.logLine(Testname, false, Message +" message displayed");
			}
			else
			{log.logLine(Testname, true, "Email adderess needed statement didnot appear");
			}					
		} 

		if (doesElementExist2(properties.getProperty("emailadd"), 5)) {	    
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("emailadd")));
			gmailid.clear();
			gmailid.sendKeys(Emladd);
			log.logLine(Testname, false, "Entering the email address"+ Emladd);				

		} else {
			negativeCase(Testname, firstWinHandle, "", "Entering the email address  failed");			
		}


		if (doesElementExist2(properties.getProperty("save"), 5)) {	    
			WebElement save = driver.findElement(By.cssSelector(properties.getProperty("save")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
			log.logLine(Testname, false, "Clicked on the save button");			
		} else if (doesElementExist(properties.getProperty("save1"), 5)) {	    
			WebElement save = driver.findElement(By.xpath(properties.getProperty("save1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
			log.logLine(Testname, false, "Clicked on the save button");			
		}else {
			log.logLine(Testname, true, "Selecting save button is  failed");	
			negativeCase(Testname, firstWinHandle, "", "Selecting save button is  failed");			
		}

		Thread.sleep(3000);

		if (doesElementExist(properties.getProperty("Message"), 5)) {	    		
			String  review= driver.findElement(By.xpath(properties.getProperty("Message"))).getText();
			if( review.equalsIgnoreCase(Message))
			{log.logLine(Testname, false, Message+"message displayed");
			}
			else
			{log.logLine(Testname,true, "Email Addresses Updated Successfully! message didnot appear");
			}					
		} 

		Thread.sleep(2000);

		if (doesElementExist2(properties.getProperty("emailadd"), 5)) {	    
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("emailadd")));
			gmailid.clear();
			gmailid.sendKeys(Emladd1);
			log.logLine(Testname, false, "Entering the email address "+Emladd1);				

		} else {
			negativeCase(Testname, firstWinHandle, "", "Entering the email address failed");			
		}

		if (doesElementExist2(properties.getProperty("save"), 5)) {	    
			WebElement save = driver.findElement(By.cssSelector(properties.getProperty("save")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
			log.logLine(Testname, false, "Clicked on the save button");			
		} else if (doesElementExist(properties.getProperty("save1"), 5)) {	    
			WebElement save = driver.findElement(By.xpath(properties.getProperty("save1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
			log.logLine(Testname, false, "Clicked on the save button");			
		}else {
			log.logLine(Testname, true, "Selecting save button is  failed");	
			negativeCase(Testname, firstWinHandle, "", "Selecting save button is  failed");			
		}


		if (doesElementExist2(properties.getProperty("GoonlineBlue"), 5)) {
			WebElement Gordo = driver.findElement(By.cssSelector(properties.getProperty("GoonlineBlue")));
			if (Gordo.isSelected())
			{log.logLine(Testname, false, "Selecting Go online and save the Planet for Blue document is already Checked");
			}else{
				Gordo.click();				
				log.logLine(Testname, false, "Selecting Go online and save the Planet for Blue document is successful");
			}
		}

		if (doesElementExist2(properties.getProperty("save"), 5)) {	    
			WebElement save = driver.findElement(By.cssSelector(properties.getProperty("save")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the save button");			
		} else {
			log.logLine(Testname, true, "Selecting save button is  failed");	
			negativeCase(Testname, firstWinHandle, "", "Selecting save button is  failed");	
		}


		if (doesElementExist(properties.getProperty("msg"), 5)) {
			WebElement msg = driver.findElement(By.xpath(properties.getProperty("msg")));		
			if (msg.getText().contains(Message1)) {					
				log.logLine(Testname, false,Message1+ "msg displayed");						
			}				
		} else {
			log.logLine(Testname, true,Message1+ " msg didn't display");	
		}

		Thread.sleep(2000);

		if (doesElementExist(properties.getProperty("logout"), 5)) {
			WebElement Logout = driver.findElement(By.xpath(properties.getProperty("logout")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Logout);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Click on Logout Button is successful");
		} else {
			log.logLine(Testname, true, "Failed to click on logout button");
			negativeCase(Testname, firstWinHandle, "","Failed to click on logout button");	
		}

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("Skblu_id"), 5)) {	    
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Skblu_id")));
			gmailid.clear();
			gmailid.sendKeys(Usrnme);
			log.logLine(Testname, false, "Entering the skyblue user id .."+Usrnme);				

		} else {
			negativeCase(Testname, firstWinHandle, "", "Entering the skyblue user id  failed");			
		}

		if (doesElementExist2(properties.getProperty("Skblu_pwd"), 5)) {	    
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Skblu_pwd")));
			gmailid.clear();
			gmailid.sendKeys(Pswd);
			log.logLine(Testname, false, "Entering the skyblue password.."+Pswd);			

		}  else {
			negativeCase(Testname, firstWinHandle, "", "Entering the password ID is failed");				
		} 	

		Thread.sleep(1000);		
		if (doesElementExist2(properties.getProperty("Login"), 5)) {	    
			WebElement skybluSign = driver.findElement(By.cssSelector(properties.getProperty("Login")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", skybluSign);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the skyblue SignIn");			
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicked on the skyblue SignIn is failed");			
		}

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("DeliveryOptions"), 5)) {
			WebElement Deloptn = driver.findElement(By.cssSelector(properties.getProperty("DeliveryOptions")));
			String data=Deloptn.getText();
			if(data.equalsIgnoreCase("Delivery Options"))
			{((JavascriptExecutor) driver).executeScript("arguments[0].click()", Deloptn);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicked on Delivery Options options menu");
			}
			else if (doesElementExist2(properties.getProperty("DeliveryOptions1"), 5)) {
				WebElement Deloptn1 = driver.findElement(By.cssSelector(properties.getProperty("DeliveryOptions1")));
				String data1=Deloptn.getText();
				if(data1.equalsIgnoreCase("Delivery Options"))
				{((JavascriptExecutor) driver).executeScript("arguments[0].click()", Deloptn1);
				Thread.sleep(4000);
				log.logLine(Testname, false, "Clicked on Delivery Options options menu");
				}
				else if (doesElementExist2(properties.getProperty("DeliveryOptions2"), 5)) {
					WebElement Deloptn2 = driver.findElement(By.cssSelector(properties.getProperty("DeliveryOptions1")));
					String data2=Deloptn.getText();
					if(data2.equalsIgnoreCase("Delivery Options"))
					{((JavascriptExecutor) driver).executeScript("arguments[0].click()", Deloptn2);
					Thread.sleep(4000);
					log.logLine(Testname, false, "Clicked on Delivery Options options menu");
					}

				}}}else {
					log.logLine(Testname, true, "Clicked on Delivery Options is failed");
					negativeCase(Testname, firstWinHandle, "",  "Clicked on Delivery Options is failed");
				}


		Thread.sleep(2000);

		if (doesElementExist2(properties.getProperty("Cancel"), 5)) {
			WebElement can = driver.findElement(By.cssSelector(properties.getProperty("Cancel")));
			can.click();
			log.logLine(Testname, false, "Selecting cancel button is  successful");
		}else if (doesElementExist(properties.getProperty("Cancel1"), 5)) {
			WebElement can = driver.findElement(By.xpath(properties.getProperty("Cancel1")));
			can.click();
			log.logLine(Testname, false, "Selecting cancel button is  successful");
		}else{
			log.logLine(Testname, true, "Selecting cancel button is  failed");
		}

		Thread.sleep(1000);

		if (doesElementExist(properties.getProperty("logout"), 5)) {
			WebElement Logout = driver.findElement(By.xpath(properties.getProperty("logout")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Logout);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Click on Logout Button is successful");
		} else {
			log.logLine(Testname, true, "Failed to click on logout button");
			negativeCase(Testname, firstWinHandle, "","Failed to click on logout button");	
		}

		driver.close();
		driver.switchTo().window(firstWinHandle);

	}

	public void ClientAppsrch_eDelivery(String Testname) throws Exception {
		InputOutputData test = new InputOutputData();        
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(5000);
		driver.switchTo().defaultContent();


		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {        
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");
			throw new Exception("Clicking on Cancel button in Client/App popup is failed");
		}


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

				// Wait till the page loads all the elements in it.
				WebElement retelm2 = waitForElement(properties.getProperty("ProfileAdmin"));                
				Actions builder = new Actions(driver);            

				WebElement clntappmenu = driver.findElement(By.xpath(properties.getProperty("Clientapplnk")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", clntappmenu);
				Thread.sleep(3000);
				log.logLine(Testname, false, "Navigating to Admin - Clientapp admin link..");

				WebElement retelm9 = waitForElement(properties.getProperty("AppNamefld"));
				log.logLine(Testname, false, "AppNamefld is found on the page..");


				//Move the mouse on Welcome text to avoid blinking
				WebElement hellolbl = driver.findElement(By.cssSelector(properties.getProperty("HelloUserName")));
				builder.moveToElement(hellolbl).perform();
				Thread.sleep(1000);

				Actions action = new Actions(driver);

				//Entering Application name in ApplicationId text box
				String Appid = test.readColumnData("Applicationid", sheetname);

				if (doesElementExist2(properties.getProperty("ApplicationId"), 5)) {
					WebElement applid = driver.findElement(By.cssSelector(properties.getProperty("ApplicationId")));					
					applid.sendKeys(Appid);			
					Thread.sleep(1000);
					log.logLine(Testname, false, "Entered the application name "+Appid+" in the Application name text field in Client/App tool");
				} else {				
					log.logLine(Testname, true, "Entering the application name "+Appid+" in the Application name text field in Client/App tool is failed");
					negativeCase(Testname, firstWinHandle, "","Entering the application name "+Appid+" in the Application name text field in Client/App tool is failed");	
				}       


				if (doesElementExist(properties.getProperty("AnyTool1"), 5)) {
					WebElement AnyTool = driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_lstLayers']"));
					Select select = new Select(AnyTool); 
					List <WebElement> options = select.getOptions(); 
					for (WebElement option: options) { 
						if (option.getText().equalsIgnoreCase("PIVOT eDelivery")){ 
							option.click(); 
							break; 
						}
					}
					log.logLine(Testname, false, "Clicked on AnyTool drop down list and selected the option PIVOT eDelivery..");
				} else {
					log.logLine(Testname, true, "Clicking on AnyTool drop down list and selecting the option PIVOT eDelivery is  failed");
					negativeCase(Testname, firstWinHandle, "","Clicking on AnyTool drop down list and selecting the option PIVOT eDelivery is  failed");	
				}         



				//clicking on search button        
				if (doesElementExist2(properties.getProperty("searchbutton"), 5)) {
					WebElement srcbtn = driver.findElement(By.cssSelector(properties.getProperty("searchbutton")));
					log.logLine(Testname, false, "Clicked on search button of the client/app/Tool Admin");
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", srcbtn);
					waitUntilRetrive();
				} else {
					log.logLine(Testname, true, "Clicking on search button of the client/app/Tool Admin is failed");
					negativeCase(Testname, firstWinHandle, "","Clicking on search button of the client/app/Tool Admin is failed");	
				}

				Thread.sleep(2000);
				if (doesElementExist2(properties.getProperty("ModifyTool"), 5)) {
					WebElement modtool = driver.findElement(By.cssSelector(properties.getProperty("ModifyTool")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", modtool);
					waitUntilRetrive();            
					log.logLine(Testname, false, "Clicked on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin ");
				} else {
					log.logLine(Testname, true, "Clicking on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin is failed");
					negativeCase(Testname, firstWinHandle, "","Clicking on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin is failed");	
				}
				Thread.sleep(6000);
				if (doesElementExist2(properties.getProperty("clntoverride"), 5)) {
					WebElement clntover = driver.findElement(By.cssSelector(properties.getProperty("clntoverride")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", clntover);
					//waitUntilRetrive();            
					log.logLine(Testname, false, "Clicked on the 'eDelivery Client Overrides' button in client/app/Tool Admin ");
				} else {
					log.logLine(Testname, true, "Clicking on the 'eDelivery Client Overrides' button in client/app/Tool Admin failed");
					negativeCase(Testname, firstWinHandle, "", "Clicking on the 'eDelivery Client Overrides' button in client/app/Tool Admin failed");	
				}

				if (doesElementExist(properties.getProperty("genericindex"), 5)) {
					WebElement geneind = driver.findElement(By.xpath(properties.getProperty("genericindex")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", geneind);
					//waitUntilRetrive();            
					log.logLine(Testname, false, "Clicked on the '3.eArchive/ePresent Generic Index Configuration Over Ride' button in client/app/Tool Admin ");
				} else {
					log.logLine(Testname, true, "Clicking on the '3.eArchive/ePresent Generic Index Configuration Over Ride' button in client/app/Tool Admin failed");
					negativeCase(Testname, firstWinHandle, "", "Clicking on the '3.eArchive/ePresent Generic Index Configuration Over Ride' button in client/app/Tool Admin failed");	
				}

				List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_ctlServices_ctlSrvD_ctlGenCfg_grdGenerics']/tbody/tr"));
				if (DataCnt.size()==0){
					log.logLine(Testname, false, "Generic index grid is empty");}						
				else{
					fieldindx= driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_ctlServices_ctlSrvD_ctlGenCfg_grdGenerics']/tbody/tr["+(DataCnt.size()-1)+"]/td[1]")).getText();
					strI = Integer.toString(DataCnt.size()-1);

					if(doesElementExist(properties.getProperty("indexheader"), 5)){
						WebElement index= driver.findElement(By.xpath(properties.getProperty("indexheader")));
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", index);
						for(int i=1;i<DataCnt.size();i++){
							int j=i+1;												
							WebElement label= driver.findElement(By.xpath(".//*[@id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_ctlServices_ctlSrvD_ctlGenCfg_grdGenerics']/tbody/tr["+j+"]/td[3]"));
							if(label.getText().equalsIgnoreCase("Generic_Field")){
								status="exists";
								Thread.sleep(1000);
								log.logLine(Testname, false, " Generic field is already exists in the grid ");
								break;}
						}		
						if(status.equalsIgnoreCase(" ")){		
							log.logLine(Testname, true, " Generic field doesnot exists in the grid ");
							Genericfield_creation(Testname,"Generic_Field");
						}
					}}
			}
		}
	}

	public void Genericfield_creation(String Testname,String fieldlabel) throws Exception {
		Thread.sleep(3000);
		if(doesElementExist(properties.getProperty("Fieldindex"), 5)) {
			WebElement fldindx = driver.findElement(By.xpath(properties.getProperty("Fieldindex")));
			Select select = new Select(fldindx);
			List <WebElement> options = select.getOptions();
			for (WebElement option: options) { 
				if (option.getText().equalsIgnoreCase(strI)){ 
					option.click(); 
					break; 
				}
			}
			log.logLine(Testname, false, "Clicked on Fieldindex drop down list and selected the option ..");
		} else {
			log.logLine(Testname, true, "Clicking on Fieldindex drop down list and selected the option .. failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Fieldindex drop down list and selected the option .. failed");
		}


		if (doesElementExist(properties.getProperty("fieldlabel"), 5)) {
			WebElement fldlabel = driver.findElement(By.xpath(properties.getProperty("fieldlabel")));					
			fldlabel.clear();	
			Thread.sleep(1000);
			fldlabel.sendKeys(fieldlabel);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entered the fieldlabel "+ fieldlabel +" in the text box");
		}
		else {
			log.logLine(Testname, true, "Entering the fieldlabel "+ fieldlabel +" in the text box is failed");
		}   

		if (doesElementExist(properties.getProperty("epressrch"), 5)) {
			WebElement epressrh= driver.findElement(By.xpath(properties.getProperty("epressrch")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",epressrh);        
			log.logLine(Testname, false, "Clicked on the epresent search button nxt to field label");
		} else {
			log.logLine(Testname, true, " failed to Click on the epresent search button nxt to field label");
			throw new Exception("failed to Click on the epresent search button nxt to field label");
		}

		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("srchord"), 5)) {
			WebElement srchord = driver.findElement(By.xpath(properties.getProperty("srchord")));
			Select select = new Select(srchord); 
			List <WebElement> options = select.getOptions(); 
			for (WebElement option: options) { 
				if (option.getText().equalsIgnoreCase(fieldindx)){ 
					option.click(); 
					break; 
				}
			}
			log.logLine(Testname, false, "Clicked on searchorder drop down list and selected the option ..");
		} else {
			log.logLine(Testname, true, "Clicking on searchorder drop down list and selected the option .. failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on searchorder drop down list and selected the option. failed");
		}  
		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("ep-result"), 5)) {
			WebElement epres = driver.findElement(By.xpath(properties.getProperty("ep-result")));
			Select select = new Select(epres); 
			List <WebElement> options = select.getOptions(); 
			for (WebElement option: options) { 
				if (option.getText().equalsIgnoreCase("In Row")){ 
					option.click(); 
					break; 
				}
			}
			log.logLine(Testname, false, "Clicked on epresent result drop down list and selected the option 'In Row'..");
		} else {
			log.logLine(Testname, true, "Clicking on epresent result drop down list and selected the option 'In Row'.. failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on epresent result drop down list and selected the option 'In Row'.... failed");
		}   

		if (doesElementExist(properties.getProperty("resorder"), 5)) {
			WebElement resord = driver.findElement(By.xpath(properties.getProperty("resorder")));
			Select select = new Select(resord); 
			List <WebElement> options = select.getOptions(); 
			for (WebElement option: options) { 
				if (option.getText().equalsIgnoreCase(fieldindx)){ 
					option.click(); 
					break; 
				}
			}
			log.logLine(Testname, false, "Clicked on resultorder drop down list and selected the option ..");
		} else {
			log.logLine(Testname, true, "Clicking on resultorder drop down list and selected the option .. failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on resultorder drop down list and selected the option .. failed");
		}  


		if (doesElementExist(properties.getProperty("earchsrch"), 5)) {
			WebElement epressrh= driver.findElement(By.xpath(properties.getProperty("earchsrch")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",epressrh);        
			log.logLine(Testname, false, "Clicked on the epresent search button nxt to field label");
		} else {
			log.logLine(Testname, true, " failed to Click on the epresent search button nxt to field label");
			throw new Exception("failed to Click on the epresent search button nxt to field label");
		}

		if (doesElementExist(properties.getProperty("srchorder1"), 5)) {
			WebElement srchord1 = driver.findElement(By.xpath(properties.getProperty("srchorder1")));
			Select select = new Select(srchord1); 
			List <WebElement> options = select.getOptions(); 
			for (WebElement option: options) { 
				if (option.getText().equalsIgnoreCase(fieldindx)){ 
					option.click(); 
					break; 
				}
			}
			log.logLine(Testname, false, "Clicked on searchorder1 drop down list and selected the option ..");
		} else {
			log.logLine(Testname, true, "Clicking on searchorder1 drop down list and selected the option .. failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on searchorder1 drop down list and selected the option '5'.. failed");
		} 



		if (doesElementExist(properties.getProperty("earchres"), 5)) {
			WebElement earch = driver.findElement(By.xpath(properties.getProperty("earchres")));
			Select select = new Select(earch); 
			List <WebElement> options = select.getOptions(); 
			for (WebElement option: options) { 
				if (option.getText().equalsIgnoreCase("In Row")){ 
					option.click(); 
					break; 
				}
			}
			log.logLine(Testname, false, "Clicked on eArchive result drop down list and selected the option 'In Row'..");
		} else {
			log.logLine(Testname, true, "Clicking on eArchive result drop down list and selected the option 'In Row'.. failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on eArchive result drop down list and selected the option 'In Row'.... failed");
		}   

		if (doesElementExist(properties.getProperty("resorder1"), 5)) {
			WebElement resord1 = driver.findElement(By.xpath(properties.getProperty("resorder1")));
			Select select = new Select(resord1); 
			List <WebElement> options = select.getOptions(); 
			for (WebElement option: options) { 
				if (option.getText().equalsIgnoreCase(fieldindx)){ 
					option.click(); 
					break; 
				}
			}
			log.logLine(Testname, false, "Clicked on resultorder1 drop down list and selected the option ..");
		} else {
			log.logLine(Testname, true, "Clicking on resultorder1 drop down list and selected the option . failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on resultorder1 drop down list and selected the option '5'.. failed");
		} 

		if (doesElementExist(properties.getProperty("Insertbtn"), 5)) {
			WebElement insertbtn= driver.findElement(By.xpath(properties.getProperty("Insertbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",insertbtn);        
			log.logLine(Testname, false, "Clicked on the insert button to add new field");
		} else {
			log.logLine(Testname, true, " failed to Click on the insert button to add new field");
			throw new Exception("failed to Click on the insert button to add new field");
		}
	}


	public void Doctype_generatefieldvalidation(String Testname,String Docstart) throws Exception {

		String envi = Initialization.EnvirSite.toUpperCase();

		Thread.sleep(8000);
		if (doesElementExist2(properties.getProperty("Doctype1"), 5)) {
			WebElement doctype = driver.findElement(By.cssSelector(properties.getProperty("Doctype1")));
			log.logLine(Testname, false, "Clicking on Document type selection list");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", doctype);
			Thread.sleep(1000);
			List<WebElement> doclst = driver.findElements(By.cssSelector("ul[id='ddlDocumentType_listbox'] li"));					
			for (WebElement lnk:doclst) {
				if (lnk.getText().equals("Blue Documents")) {					
					((JavascriptExecutor) driver).executeScript("arguments[0].click()",lnk);
					Thread.sleep(2000);					
					log.logLine(Testname, false, "Selecting the option 'Blue Documents' from list");			
					break;
				}
			}
		} else {
			log.logLine(Testname, true, "Clicking on Doc type to select the option Blue document from list is failed");
			throw new Exception("Clicking on Doc type to select the option Blue document from list is failed");
		}
		Thread.sleep(2000);

		if(envi.equalsIgnoreCase("stage")){
			if (doesElementExist2(properties.getProperty("Docstartdate"), 5)) {	    
				WebElement docstart= driver.findElement(By.cssSelector(properties.getProperty("Docstartdate")));
				docstart.clear();
				docstart.sendKeys(Docstart);
				log.logLine(Testname, false, "Entering the Document start date ..");			

			}  else {
				negativeCase(Testname, firstWinHandle, "", "Entering the Document start date .. failed");				
			} }	

		if (doesElementExist2(properties.getProperty("srchbtn1"), 5)) {	    
			WebElement docsrch = driver.findElement(By.cssSelector(properties.getProperty("srchbtn1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",  docsrch);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the document search");			
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicked on the document searchfailed");			
		}	

		Thread.sleep(4000);
		if (doesElementExist(properties.getProperty("genericfieldgrid"), 5)) {	
			WebElement data = driver.findElement(By.xpath(properties.getProperty("genericfieldgrid")));
			if(data.getText().equalsIgnoreCase("No documents found")){
				log.logLine(Testname, false, "There is no data in the grid, so unable to validate the 'Generic field' grid");		
			}
			else{
				String available="No";
				log.logLine(Testname, false, "Validation of generate index column heading");		
				List<WebElement> DataCnt= driver.findElements(By.cssSelector("table[role='grid'] thead tr th a"));
				WebElement title = driver.findElement(By.xpath("html/body/form/div[3]/div[2]/div[1]/div[2]/div[5]/div[1]/div/table/thead/tr/th[1]/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", title);
				for (WebElement lnk:DataCnt) {
					String lnktxt=lnk.getText();
					if(lnktxt.equalsIgnoreCase("Generic_Field")){
						log.logLine(Testname, false, "The validation passed as the 'generate field' is present as Grid heading");
						available="Yes";
						break;
					}
				}
				if(available.equalsIgnoreCase("No")){
					log.logLine(Testname, true, "'Generic filed' column doesnot exist in Documents header");
				}	}}
	}

	//>>>>>>>>>>>>>>>>>>>>>>>>>Archieve consent/users starts>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	public boolean consentselection_change(String Testname) throws Exception {

		InputOutputData test = new InputOutputData();
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
		String envi = Initialization.EnvirSite.toUpperCase();

		Thread.sleep(5000);
		driver.switchTo().defaultContent();

		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");		
		}

		Thread.sleep(1000);


		if (doesElementExist2(properties.getProperty("AdminMenu"), 5)) {
			List<WebElement> Mns = driver.findElements(By.cssSelector(properties.getProperty("AdminMenu")));
			for (WebElement Each:Mns) {
				if(Each.getText().equals("Admin")){
					Actions action = new Actions(driver);				
					action.moveToElement(Each).build().perform();						
					Thread.sleep(2000);
					WebElement Archive = driver.findElement(By.xpath(properties.getProperty("ArchiveSubMnu")));
					action.moveToElement(Archive).build().perform();
					Thread.sleep(2000);			
					break;}}

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

		}

		else {
			log.logLine(Testname, true, "Opening Admin window failed");
			throw new Exception("Opening Admin window failed");
		}

		Thread.sleep(3000);

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
			}
		}

		Thread.sleep(3000);

		WebElement retelm = waitForElement(properties.getProperty("ClientID"));

		if(envi.equalsIgnoreCase("stage")){
			ClientID = test.readColumnData("ClientID", sheetname);

		}
		else{
			ClientID = test.readColumnData("ClientID1", sheetname);}


		if (doesElementExist2(properties.getProperty("ClientID1"), 5)) {
			WebElement Client = driver.findElement(By.cssSelector(properties.getProperty("ClientID1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Client);

			Thread.sleep(1500);

			if (doesElementExist2(properties.getProperty("ClientIDSel"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ClientIDSel")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().contains(ClientID)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the ClientID as "+ClientID +" from the popup..");						
						break;
					}				
				}

			} else {
				negativeCase(Testname, firstWinHandle, "", "Selecting the Client ID failed");				
			}					

		} else {
			negativeCase(Testname, firstWinHandle, "", "Selecting the Client ID failed");			
		}
		Thread.sleep(2000);
		String App = test.readColumnData("Appid", sheetname);

		if (doesElementExist2(properties.getProperty("DocType"), 5)) {	    
			WebElement Client = driver.findElement(By.cssSelector(properties.getProperty("DocType")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Client);
			Thread.sleep(1500);

			if (doesElementExist2(properties.getProperty("AppIDSel"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("AppIDSel")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().contains(App)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Application as "+App +" from the popup..");						
						break;
					}				
				}

			} else {
				negativeCase(Testname, firstWinHandle, "", "Selecting the Application ID failed");				
			}


		} else {
			negativeCase(Testname, firstWinHandle, "", "Document type field does not exist in application");			
		}
		String envi1 = Initialization.EnvirSite.toUpperCase();

		if(envi1.equalsIgnoreCase("stage")){
			consumerid= test.readColumnData("consumer id", sheetname);
			user= test.readColumnData("user", sheetname);
		}
		else{
			consumerid= test.readColumnData("consumer id1", sheetname);
			user= test.readColumnData("user1", sheetname);}



		if (doesElementExist2(properties.getProperty("consumerId"), 5)) {	    
			WebElement frstName = driver.findElement(By.cssSelector(properties.getProperty("consumerId")));						
			frstName.clear();
			frstName.sendKeys(consumerid);
			log.logLine(Testname, false, "Entering the consumer id "+consumerid);
			Thread.sleep(500);
		} else {
			log.logLine(Testname, true, "Entering the consumerid failed");	
			negativeCase(Testname, firstWinHandle, "", "Entering the consumerid failed");	
		}


		Thread.sleep(1000);

		if (doesElementExist2(properties.getProperty("Submitbtn"), 5)) {	    
			WebElement Client = driver.findElement(By.cssSelector(properties.getProperty("Submitbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Client);			
			Thread.sleep(4000);			
			log.logLine(Testname, false, "Clicked on Submit button");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Submit button does not exist in application");			
		}
		Thread.sleep(3000);


		if (doesElementExist2(properties.getProperty("ActeDeliverLink"), 5)) {	    
			String actlink = driver.findElement(By.cssSelector(properties.getProperty("ActeDeliverLink"))).getText();			
			driver.get(actlink);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicked on Link to open actual eDelivery page");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Click on Link to open actual eDelivery page is failed");			
		}

		Thread.sleep(10000);

		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("Documents"), 5)) {	    
			WebElement skybluSign = driver.findElement(By.xpath(properties.getProperty("Documents")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", skybluSign);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the skyblue Documents button");			
		} 
		else if (doesElementExist2(properties.getProperty("Documents1"), 5)) {	    
			WebElement skybluSign = driver.findElement(By.cssSelector(properties.getProperty("Documents1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", skybluSign);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the skyblue Documents button");			
		} else if (doesElementExist2(properties.getProperty("Document_prod"), 5)) {	    
			WebElement skybluSign = driver.findElement(By.cssSelector(properties.getProperty("Document_prod")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", skybluSign);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the skyblue Documents button");			
		} else if (doesElementExist2(properties.getProperty("Document_prod1"), 5)) {	    
			WebElement skybluSign = driver.findElement(By.cssSelector(properties.getProperty("Document_prod1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", skybluSign);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the skyblue Documents button");			
		} else if (doesElementExist(properties.getProperty("Doc"), 5)) {	    
			WebElement skybluSign = driver.findElement(By.cssSelector(properties.getProperty("Doc")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", skybluSign);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the skyblue Documents button");			
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicked on the skyblue Documents button is failed");			
		}


		if (doesElementExist(properties.getProperty("searchdoc"), 5)) {	    
			WebElement Client = driver.findElement(By.xpath(properties.getProperty("searchdoc")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Client);			
			Thread.sleep(4000);	
			log.logLine(Testname, false, "Clicked on Search button");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Search button does not exist in application");			
		}

		List<WebElement> DataCnt= driver.findElements(By.xpath("html/body/div[2]/div[2]/div/div[2]/div[5]/div[2]/table/tbody/tr"));

		String startdate= test.readColumnData("Docstartdate", sheetname);


		if(DataCnt.size()==0){
			log.logLine(Testname, false, "No data to be displayed in the specified date,so we are modifying the startdate now");
			if (doesElementExist(properties.getProperty("docstartdate"), 5)) {	    
				WebElement frstName = driver.findElement(By.xpath(properties.getProperty("docstartdate")));						
				frstName.clear();
				frstName.sendKeys(startdate);		
				log.logLine(Testname, false, "Entering the startdate in Documents"+startdate);	

				if (doesElementExist(properties.getProperty("searchdoc"), 5)) {	    
					WebElement Client = driver.findElement(By.xpath(properties.getProperty("searchdoc")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Client);			
					Thread.sleep(3000);
					log.logLine(Testname, false, "Clicked on Search button");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Search button does not exist in application");			
				}

			} else {
				log.logLine(Testname, true, "Entering the start date failed");	
				negativeCase(Testname, firstWinHandle, "", "Entering the start date failed");	
			}
		}

		Documentgridvalidation_date(Testname);
		Documentgridvalidation_Iicon(Testname);
		Additionalsearch(Testname);		
		Reprintvalidations(Testname);
		Documentgridvalidation_Msgicon(Testname);

		if (doesElementExist2(properties.getProperty("UserConsentMnu1"), 5)) {	    
			WebElement delmnu = driver.findElement(By.cssSelector(properties.getProperty("UserConsentMnu1")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", delmnu);
			log.logLine(Testname, false, "Clicked on User Consent Page menu");
		}
		else if (doesElementExist2(properties.getProperty("UserConsentMnu"), 5)) {	    
			WebElement delmnu = driver.findElement(By.cssSelector(properties.getProperty("UserConsentMnu")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", delmnu);
			log.logLine(Testname, false, "Clicked on User Consent Page menu");
		}			
		else {
			negativeCase(Testname, firstWinHandle, "", "Clicked on User Consent Page menu is failed");			
		}

		Thread.sleep(2000);


		if (doesElementExist2(properties.getProperty("UseridMnu"), 5)) {	    
			WebElement useridmnu = driver.findElement(By.cssSelector(properties.getProperty("UseridMnu")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",useridmnu);
			List<WebElement> Cnt= driver.findElements(By.cssSelector(properties.getProperty("usermnulst")));
			for (WebElement lnk:Cnt) {
				String txt=lnk.getText();
				if(txt.equalsIgnoreCase("Consumer ID")){
					((JavascriptExecutor) driver).executeScript("arguments[0].click()",lnk);
					log.logLine(Testname, false, "Selecting the user id option from list");
				}
				else if (doesElementExist2(properties.getProperty("UseridMnu"), 5)) {	    
					WebElement useridmnu1 = driver.findElement(By.cssSelector(properties.getProperty("UseridMnu")));						
					((JavascriptExecutor) driver).executeScript("arguments[0].click()",useridmnu1);
					List<WebElement> Cnt1= driver.findElements(By.cssSelector(properties.getProperty("usermnulst")));
					for (WebElement lnk1:Cnt1) {
						String txt1=lnk.getText();
						if((txt1.equalsIgnoreCase("Identification"))||(txt1.equalsIgnoreCase(" "))){
							((JavascriptExecutor) driver).executeScript("arguments[0].click()",lnk1);
							log.logLine(Testname, false, "Selecting the user id option from list");
						}
						Thread.sleep(2500);
					}}
				Thread.sleep(2500);
			}}

		else if (doesElementExist(properties.getProperty("userid"), 5)) {
			Select prsnthosttype = new Select(driver.findElement(By.id("ddl_SearchBy")));
			prsnthosttype.selectByVisibleText("Consumer ID");
			log.logLine(Testname, false, "Selecting 'Consumer ID' option  is successful");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Failed to Select 'User ID' option");
		}


		if (doesElementExist2(properties.getProperty("SearchFld"), 5)) {	    
			WebElement frstName = driver.findElement(By.cssSelector(properties.getProperty("SearchFld")));						
			frstName.clear();
			frstName.sendKeys(user);
			log.logLine(Testname, false, "Entering the user id "+user);
			Thread.sleep(500);
		} else {
			log.logLine(Testname, true, "Entering the user id failed");	
			negativeCase(Testname, firstWinHandle, "", "Entering the user id failed");	
		}

		if (doesElementExist2(properties.getProperty("SearchbtnCons"), 5)) {	    
			WebElement pradiobtn = driver.findElement(By.cssSelector(properties.getProperty("SearchbtnCons")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", pradiobtn);
			log.logLine(Testname, false, "Clicked on Search button in Consent page");
			Thread.sleep(3500);
		} else {
			log.logLine(Testname, true, "Clicked on Search button in Consent page is failed");
			negativeCase(Testname, firstWinHandle, "", "Clicked on Search button in Consent page is failed");
		}

		if (doesElementExist2(properties.getProperty("Historybtn"), 5)) {	    
			WebElement hisbtn = driver.findElement(By.cssSelector(properties.getProperty("Historybtn")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", hisbtn);
			log.logLine(Testname, false, "Clicked on History button in Consent page");
			Thread.sleep(3500);
		} else {
			log.logLine(Testname, true, "Clicked on History button in Consent page is failed");	
			negativeCase(Testname, firstWinHandle, "", "Clicked on History button in Consent page is failed");
		}

		Historygridvalidation_date(Testname);

		//Selecting the data from the grid

		List<WebElement> DataCnt1= driver.findElements(By.cssSelector("div[id='History-Consent-Pane-grid'] div table tbody tr"));

		skybluegridrow=DataCnt1.size();

		if(DataCnt1.size()==0){
			log.logLine(Testname, false , "Validation failed as there is no data to validate in the grid");
		}
		else if(doesElementExist(properties.getProperty("Datevldn"), 5)){
			List<WebElement> DataCn= driver.findElements(By.xpath(".//*[@id='History-Consent-Pane-grid']/div[2]/table/tbody/tr[1]/td"));
			log.logLine(Testname, false , "No. of rows in the skybluehistory" + skybluegridrow);
			for(int j = 1; j <= DataCnt1.size(); j++){	
				for(int i = 1; i <= DataCn.size(); i++) {
					Skybluedata[j][i]=driver.findElement(By.xpath(".//*[@id='History-Consent-Pane-grid']/div[2]/table/tbody/tr["+j+"]/td["+i+"]")).getText();
				}
			}}


		if (doesElementExist(properties.getProperty("close1"), 5)) {
			WebElement close1 = driver.findElement(By.xpath(properties.getProperty("close1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", close1);	    	 
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on close is successfull");
		} else {
			log.logLine(Testname, true, " Click on close is failed");
			negativeCase(Testname, firstWinHandle, "", "failed to click on close button of skyblue 'History'");
		}

		driver.close();
		driver.switchTo().window(firstWinHandle);

		ConsentUsersinArchieve(Testname);

		driver.switchTo().defaultContent();
		return true;
	}

	public void Reprintvalidations(String Testname) throws Exception {

		if (doesElementExist(properties.getProperty("selectchk"), 5)) {
			for(int i = 1; i <= 2; i++){ 
				WebElement bulkselect = driver.findElement(By.xpath("html/body/div[2]/div[2]/div/div[2]/div[5]/div[2]/table/tbody/tr["+i+"]/td[17]/div/input"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", bulkselect);	    	 
				Thread.sleep(2000);
				log.logLine(Testname, false, "Clicking on "+i+" element  checkbox is successful");
			}} else {
				log.logLine(Testname, true, " Clicking on bulk select checkbox is  failed");
				negativeCase(Testname, firstWinHandle, "", "Clicking on bulk select checkbox is failed");
			}

		if (doesElementExist(properties.getProperty("bulkchoose"), 5)) {	    
			WebElement flnm = driver.findElement(By.xpath(properties.getProperty("bulkchoose")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", flnm);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on bulk choose list is successful");
			Thread.sleep(1000);
			List<WebElement> searchbylst = driver.findElements(By.xpath(properties.getProperty("bulkchoselist")));
			for (WebElement btn:searchbylst) {
				if (btn.getText().equals("Reprint")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Selecting the 'Reprint' option from list is successful");
					break;
				}
			}
		}else {
			log.logLine(Testname, true, "Unable to select the Reprint from the dropdown ");
			negativeCase(Testname, firstWinHandle, "","Unable to select the Reprint from the dropdown ");
		}

		if (doesElementExist(properties.getProperty("reprintadd"), 5)) {
			WebElement srchtxt = driver.findElement(By.xpath(properties.getProperty("reprintadd")));
			srchtxt.sendKeys("ABCD_123456");
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entering the addresss data in search text box is successful");
		} else {
			log.logLine(Testname, true, " Entering the addresss data in search text box is failed");
			negativeCase(Testname, firstWinHandle, "","Unable to select the Reprint from the dropdown ");
		}

		if (doesElementExist(properties.getProperty("repreason"), 5)) {	    
			WebElement flnm = driver.findElement(By.xpath(properties.getProperty("repreason")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", flnm);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on reprint reason list is successful");

			List<WebElement> searchbylst = driver.findElements(By.xpath(properties.getProperty("repreasonlst")));
			for (WebElement btn:searchbylst) {
				if (btn.getText().equals("Lost Original")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Selecting the 'Lost Original' option from list is successful");
					break;
				}
			}
		}else {
			log.logLine(Testname, true, "Unable to select the Lost Original option from the dropdown ");
			negativeCase(Testname, firstWinHandle, "","Unable to select the Lost Original option from the dropdown ");
		}

		if (doesElementExist(properties.getProperty("repsave"), 5)) {
			WebElement save = driver.findElement(By.xpath(properties.getProperty("repsave")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);	    	 
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on reprint save is successful");
		} else {
			log.logLine(Testname, true, " Clicking on reprint save is  failed");
			negativeCase(Testname, firstWinHandle, "", "Clicking on reprint save is failed");
		}

		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("repalert"), 5)) {	   
			WebElement abc=driver.findElement(By.xpath(properties.getProperty("repalert")));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", abc);
			String msg = driver.findElement(By.xpath(properties.getProperty("repalert"))).getText();		   
			log.logLine(Testname, false, "Reading the pop up message as "+ msg);
		} else {
			log.logLine(Testname, true, "Reading the pop up message is failed");
			negativeCase(Testname, firstWinHandle, "","Reading the pop up message is failed");
		}

		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("repalertok"), 5)) {	    
			WebElement clkadv = driver.findElement(By.xpath(properties.getProperty("repalertok")));		   
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);		     
			Thread.sleep(1000);
			log.logLine(Testname, false, "Click ok button for pop up message");
		} else {
			log.logLine(Testname, true, "Click ok button for pop up message is failed");
			negativeCase(Testname, firstWinHandle, "","Click ok button for pop up message is failed");
		}

		Thread.sleep(12000);

		if (doesElementExist(properties.getProperty("searchdoc"), 5)) {	    
			WebElement Client = driver.findElement(By.xpath(properties.getProperty("searchdoc")));
			Highlight(Client);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Client);
			log.logLine(Testname, false, "Clicked on Search button");
			Thread.sleep(4000);		
		} else {
			negativeCase(Testname, firstWinHandle, "", "Search button does not exist in application");			
		}

		reprivalidation(Testname);
	}

	public void reprivalidation(String Testname) throws Exception {

		WebElement lnk= driver.findElement(By.xpath(".//*[@id='archive-search-grid']/div[2]/table/tbody/tr[1]/td[14]/div/span/span/span[1]"));
		lnk.click();
		String abc="Yes";
		List<WebElement> searchbylst = driver.findElements(By.cssSelector(properties.getProperty("chooseactlst")));	
		searchbylst.size();	
		for (WebElement btn:searchbylst) {
			Text=btn.getText();
			if (btn.getText().contains("Reprint (Last Reprint Date:")) {
				abc="No";
				break;

			}
		}		
		if(abc.equals("No")){
			log.logLine(Testname, false , "The Text in the reprint is " +Text);
		}
		else{
			log.logLine(Testname, false , "The Text in the reprint is " +Text);			
		}

		Thread.sleep(2000);
		if (Text.contains("Reprint (In Reprint Queue)")) {
			log.logLine(Testname, false, "Since reprint action is still in queue we are unable to fetch date for validation");
			Thread.sleep(5000);
			if (doesElementExist(properties.getProperty("searchdoc"), 5)) {	    
				WebElement Client = driver.findElement(By.xpath(properties.getProperty("searchdoc")));
				Highlight(Client);
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", Client);
				log.logLine(Testname, false, "Clicked on Search button");
				Thread.sleep(4000);		
			} else {
				negativeCase(Testname, firstWinHandle, "", "Search button does not exist in application");			
			}
			reprivalidation(Testname);
		}
		else if (Text.startsWith("Reprint (Last Reprint Date:")){			
			String[] date=new String[50];
			date=Text.split(": ");
			String[] neededdate=new String[50];
			neededdate=date[1].split("\\)");
			DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");		
			Date date1 = new Date();
			String todaysDate = dateFormat.format(date1);

			if(neededdate[0].equalsIgnoreCase(todaysDate)){
				log.logLine(Testname, false , " The Reprint date "+neededdate[0]+" matches with  todays date "+todaysDate);
				WebElement lnk12= driver.findElement(By.xpath(".//*[@id='archive-search-grid']/div[2]/table/tbody/tr[1]/td[14]/div/span/span/span[1]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk12);	}
			else
			{log.logLine(Testname, true , " The Reprint date  "+neededdate[0]+" doesnot match with today's date "+todaysDate+" might be due to day change");}
		}
	}

	public void Additionalsearch(String Testname) throws Exception {

		if (doesElementExist(properties.getProperty("searchby"), 5)) {	    
			WebElement flnm = driver.findElement(By.xpath(properties.getProperty("searchby")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", flnm);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on 'Searchby' list in additional search");

			List<WebElement> searchbylst = driver.findElements(By.cssSelector(properties.getProperty("searchbylst")));
			for (WebElement btn:searchbylst) {
				if (btn.getText().equals("Plan Type")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Selecting the 'Plan type' option from list is successful");
					break;
				}
			}
		}else {
			log.logLine(Testname, true, "Unable to select the Plan type from the dropdown ");
			negativeCase(Testname, firstWinHandle, "","Unable to select the Plan type from the dropdown ");
		}

		if (doesElementExist(properties.getProperty("Criteria"), 5)) {	    
			WebElement flnm = driver.findElement(By.xpath(properties.getProperty("Criteria")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", flnm);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on 'Crieteria' list in additional search");

			List<WebElement> crilst = driver.findElements(By.xpath(properties.getProperty("crielst")));
			for (WebElement btn:crilst) {
				if (btn.getText().equals("Equals")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Selecting the 'Equals' option from criteria list is successful");
					break;
				}
			}
		}else {
			log.logLine(Testname, true, "Unable to select the 'Equals' from criteria the dropdown ");
			negativeCase(Testname, firstWinHandle, "","Unable to select the 'Equals' from criteria the dropdown ");
		}



		if (doesElementExist(properties.getProperty("Addbtn"), 5)) {	    
			WebElement clkadv = driver.findElement(By.xpath(properties.getProperty("Addbtn")));		   
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);		     
			log.logLine(Testname, false, "Click on add button");
		} else {
			log.logLine(Testname, true, "Click on add button is failed");
			negativeCase(Testname, firstWinHandle, ""," Click on add button is failed ");

		}	

		if (doesElementExist(properties.getProperty("searchby1"), 5)) {	    
			WebElement flnm = driver.findElement(By.xpath(properties.getProperty("searchby1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", flnm);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on 'Searchby' list in additional search");

			List<WebElement> searchbylst = driver.findElements(By.xpath(properties.getProperty("searchbylst1")));
			for (WebElement btn:searchbylst) {
				if (btn.getText().equals("Plan Type")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
					log.logLine(Testname, false, "Selecting the 'Plan type' option from list is successful");
					break;
				}
			}
		}else {
			log.logLine(Testname, true, "Unable to select the Plan type from the dropdown ");
			negativeCase(Testname, firstWinHandle, "","Unable to select the Plan type from the dropdown ");
		}

		Thread.sleep(5000);
		if (doesElementExist(properties.getProperty("Alertmsg"), 5)) {	   
			WebElement abc=driver.findElement(By.xpath(properties.getProperty("Alertmsg")));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", abc);
			String msg = driver.findElement(By.xpath(properties.getProperty("Alertmsg"))).getText();		   
			log.logLine(Testname, false, "Reading the pop up message as "+ msg);
		} else {
			log.logLine(Testname, true, "Reading the pop up message is failed");
			negativeCase(Testname, firstWinHandle, "","Reading the pop up message is failed");
		}


		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("alertok"), 5)) {	    
			WebElement clkadv = driver.findElement(By.xpath(properties.getProperty("alertok")));		   
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clkadv);		     
			Thread.sleep(5000);
			log.logLine(Testname, false, "Click ok button for pop up message");
		} else {
			log.logLine(Testname, true, "Click ok button for pop up message is failed");
			negativeCase(Testname, firstWinHandle, "","Click ok button for pop up message is failed");

		}

		Thread.sleep(2000);
		//Click on Cancel button in advanced search
		if (doesElementExist(properties.getProperty("remove"), 5)) {
			WebElement remove= driver.findElement(By.xpath(properties.getProperty("remove")));
			remove.click();			
			log.logLine(Testname, false, "Clicking on remove button in advanced search dialog");
		} else {
			log.logLine(Testname, true, "remove in advanced dialog does not exist");
			negativeCase(Testname, firstWinHandle, "","remove in advanced dialog does not exist");
		}
	}


	public void Documentgridvalidation_date(String Testname) throws Exception {

		String[] Sort = new String[150];

		String row = "tr";
		List<WebElement> DataCnt= driver.findElements(By.xpath("html/body/div[2]/div[2]/div/div[2]/div[5]/div[2]/table/tbody/tr"));

		for(int i = 0; i < DataCnt.size(); i++) {
			Sort[i] = driver.findElement(By.cssSelector("div[id='archive-search-grid'] div table "+row+" td a")).getText();
			String Date=Sort[i];

			boolean datform= isValidFormat("m/dd/yyyy",Date);

			if(datform){
				log.logLine(Testname, false , " "+ Date +"is in mm/dd/yyyy format");

			}else{
				log.logLine(Testname, false , " "+ Date +"is not in mm/dd/yyyy format");						
			}
			row = row + "+tr";		
		}

	}


	public void Documentgridvalidation_Iicon(String Testname) throws Exception {

		Actions action = new Actions(driver);
		if (doesElementExist(properties.getProperty("Iicon"), 5)) {
			List<WebElement> DataCnt= driver.findElements(By.xpath("html/body/div[2]/div[2]/div/div[2]/div[5]/div[2]/table/tbody/tr"));

			WebElement infofld= driver.findElement(By.xpath(".//*[@id='archive-search-grid']/div[2]/table/tbody/tr[1]/td[15]/div/img"));		    		
			action.moveToElement(infofld).perform(); 
			List<WebElement> Cnt= driver.findElements(By.cssSelector("div[class='k-tooltip-content'] div table tbody tr"));
			String row="tr";
			for(int j=1;j<=Cnt.size();j++){
				WebElement lnk= driver.findElement(By.cssSelector("div[class='k-tooltip-content'] div table tbody "+row+" td+td span"));				    		
				WebElement lnk1= driver.findElement(By.cssSelector("div[class='k-tooltip-content'] div table tbody "+row+" td span"));	
				log.logLine(Testname, false, "Text in "+ lnk.getText()+"label of tool tip is "+lnk1.getText());	
				row=row+"+tr";
			}

		}else {
			log.logLine(Testname, true," Information icon is unavailable in 'Document' grid");		
		}
	}

	public void Documentgridvalidation_Msgicon(String Testname) throws Exception {

		if (doesElementExist(properties.getProperty("Msgicon"), 5)) {
			List<WebElement> DataCnt= driver.findElements(By.xpath("html/body/div[2]/div[2]/div/div[2]/div[5]/div[2]/table/tbody/tr"));
			for(int i=1;i<=DataCnt.size();i++){
				WebElement lnk= driver.findElement(By.xpath(".//*[@id='archive-search-grid']/div[2]/table/tbody/tr["+i+"]/td[16]/div/i"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
				Thread.sleep(4000);
				driver.switchTo().frame("modal-archive-archive-email-history-frame");
				//driver.switchTo().frame("iframeContainer");   
				List<WebElement> Cnt3= driver.findElements(By.xpath(".//*[@id='content_ctl00_email_panel']/table/tbody/tr"));

				if (Cnt3.size()==0){
					log.logLine(Testname, false , "The grid in the message icon is empty");
				}
				else {	
					String row="tr";
					for(int j=0;j<Cnt3.size();j++){
						WebElement lnk1= driver.findElement(By.cssSelector("div[id='pnlResults'] table tbody "+row+" td span"));				    		
						WebElement lnk2= driver.findElement(By.cssSelector("div[id='pnlResults'] table tbody "+row+" td"));	
						log.logLine(Testname, false, "Text in "+ lnk2.getText()+" : "+lnk1.getText());
						row=row+ "+tr";
					}

				}
				driver.switchTo().defaultContent();       
				WebElement close= driver.findElement(By.xpath(".//*[@id='modal-archive-archive-email-history-container']/div[2]/div[1]/div/a/span"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",  close);
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", close);

			}}
		else
		{
			log.logLine(Testname, true , " Information message icon is unavailable in 'Document' grid");}
	}


	public static boolean isValidFormat(String format, String value) throws ParseException{
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			date = sdf.parse(value);
			if (!value.equals(sdf.format(date))) {
				date = null;
			}
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return date != null;
	}

	public void Historygridvalidation_date(String Testname) throws Exception {

		//Consent validation based on date created
		String[] Sort = new String[150];
		int length = Sort.length;
		String[] Sort1 = new String[150];

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		Date date = new Date();
		todaysDate = dateFormat.format(date);


		String CurDate[]=todaysDate.split(" ");
		log.logLine(Testname, false , "Current date is " +todaysDate);
		String row = "tr";
		List<WebElement> DataCnt= driver.findElements(By.cssSelector("div[id='History-Consent-Pane-grid'] div table tbody tr"));
		if(DataCnt.size()==0){
			log.logLine(Testname, false , "There is no data in the grid to validate");
		}
		else if(doesElementExist2(properties.getProperty("Datevldngrid"), 5)){
			for(int i = 0; i < DataCnt.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='History-Consent-Pane-grid'] div table tbody "+row+" td")).getText();
				String Date=Sort[i];

				if(Date.contains(CurDate[0])){
					if(doesElementExist2(properties.getProperty("consentvldn"), 5)){
						Sort1[i] = driver.findElement(By.cssSelector("div[id='History-Consent-Pane-grid'] div table tbody "+row+" td+td+td+td")).getText();
						String consent=Sort1[i] ;
						if(consent.equalsIgnoreCase("Go On-line and Save the Planet")){
							log.logLine(Testname, false , "Reading the consent changed date as " +Date);
							log.logLine(Testname, false, "Consent change validation is successful as the changes to the consent is successfully saved");
							break;
						}}else {
							log.logLine(Testname, true, "Unable to verify the consent as Delivery method column doesnot exist");			
						}
				}
				row = row + "+tr";		
			}

		}

	}

	public void ConsentUsersinArchieve(String Testname) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();


		Thread.sleep(4000);
		driver.switchTo().defaultContent();

		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");		
		}



		WebElement retelm2 = waitForElement(properties.getProperty("selClint1"));


		if (doesElementExist2(properties.getProperty("Archives"), 5)) {
			WebElement arclnk = driver.findElement(By.cssSelector(properties.getProperty("Archives")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", arclnk);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Navigation to Archives page is successfull");
		} else {
			log.logLine(Testname, true, "Clicking on Archives menu is failed");
			throw new Exception("Clicking on Archives menu is failed");
		}    


		Thread.sleep(2000);

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
				//    throw new Exception("Client Name options are not displayed");
			}

		} else {
			log.logLine(Testname, true, "Client Name dropdown element does not exist");
			//    throw new Exception("Client Name dropdown element does not exist");
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
					//    throw new Exception("Client Name options are not displayed");
				}

			} else {
				log.logLine(Testname, true, "Client Name dropdown element does not exist");
				//    throw new Exception("Client Name dropdown element does not exist");
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
				//    throw new Exception("Application Name options are not displayed");
			}

		} else {
			log.logLine(Testname, true, "Application Name dropdown element does not exist");
			//    throw new Exception("Application Name dropdown element does not exist");
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

							log.logLine(Testname, false, "Selecting the Application Name "+AppName +" from the dropdown..");							
							break;
						}				
					}

				} else {
					log.logLine(Testname, true, "Application Name options are not displayed");
					//    throw new Exception("Application Name options are not displayed");
				}

			} else {
				log.logLine(Testname, true, "Application Name dropdown element does not exist");
				//    throw new Exception("Application Name dropdown element does not exist");
			}	    	
		}

		PleasewaitDisappear();


		//Click on Ok button
		if (doesElementExist2(properties.getProperty("Okbtn"), 5)) {
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Okbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on OK button");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the consent user page is failed");
			throw new Exception("Clicking on OK button to view the consent user page is failed");
		}


		Thread.sleep(8000);

		if (doesElementExist2(properties.getProperty("Consent_user"), 5)) {
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Consent_user")));
			Highlight(btnsrch);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);				
			Thread.sleep(4000);	
			log.logLine(Testname, false, "Click on ConsentUser module is successfull");
		} else {
			log.logLine(Testname, true, " ConsentUser module did not display");
			throw new Exception(" ConsentUser module did not display");
		}

		Thread.sleep(3000);

		String Acc = test.readColumnData("Account", sheetname);
		String Acc1 = test.readColumnData("Account1", sheetname);

		Thread.sleep(2000);
		driver.switchTo().frame("iframeContainer");   

		if (doesElementExist(properties.getProperty("Searchtxt"), 5)) {
			WebElement srchtxt = driver.findElement(By.xpath(properties.getProperty("Searchtxt")));

			if(Initialization.EnvirSite.equalsIgnoreCase("Stage")){
				srchtxt.sendKeys(Acc);
			}else{
				srchtxt.sendKeys(Acc1);
			}
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entering the data in search text box is successful");
		} else {
			log.logLine(Testname, true, " Entering the data in search text box is failed");
			throw new Exception(" Entering the data in search text box is failed");
		}

		if (doesElementExist(properties.getProperty("Searchbtn1"), 5)) {
			WebElement btnsrch = driver.findElement(By.xpath(properties.getProperty("Searchbtn1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);	    	 
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on Searchbtn is successfull");
		} else {
			log.logLine(Testname, true, " Click on Searchbtn is failed");
			throw new Exception(" Click on Searchbtn is  failed");
		}

		Thread.sleep(2000);

		if (doesElementExist(properties.getProperty("alertok"), 5)) {
			WebElement btnsrch = driver.findElement(By.xpath(properties.getProperty("alertok")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);	    	 
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on alertok is successfull");
		} else {
			log.logLine(Testname, true, " Click on alertok is failed");
			throw new Exception(" Click on alertok is  failed");
		}

		if (doesElementExist(properties.getProperty("history"), 5)) {
			WebElement btnsrch = driver.findElement(By.xpath(properties.getProperty("history")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);	    	 
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on history is successfull");
		} else {
			log.logLine(Testname, true, " Click on history is failed");
			throw new Exception(" Click on history is  failed");
		}


		Thread.sleep(1000);

		consentuserhistory(Testname);

		Thread.sleep(1000);

		if (doesElementExist(properties.getProperty("close"), 5)) {
			WebElement close = driver.findElement(By.xpath(properties.getProperty("close")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", close);	    	 
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on close button of history popup is successfull");
		} else {
			log.logLine(Testname, true, " Click on close button of history popup is failed");
			throw new Exception(" Click on close button of history popup is  failed");
		}

		if (doesElementExist(properties.getProperty("Goonline"), 5)) {
			WebElement Gordo = driver.findElement(By.xpath(properties.getProperty("Goonline")));
			Highlight(Gordo);
			if (Gordo.isSelected()){
				printactions(Testname);
				log.logLine(Testname, false, " goonline button is already selected before performing edit actions");				
			}else{
				log.logLine(Testname, false, "goonline button is not selected before performing edit actions");
			}
		}

		if (doesElementExist(properties.getProperty("edit"), 5)) {
			WebElement edit = driver.findElement(By.xpath(properties.getProperty("edit")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", edit);	    	 
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on edit buton is successfull");
		} else {
			log.logLine(Testname, true, "Click on edit buton is failed");
			throw new Exception(" Click on edit buton is  failed");
		}

		String mai = test.readColumnData("mailid", sheetname);

		if (doesElementExist(properties.getProperty("mail"), 5)) {
			WebElement srchtxt = driver.findElement(By.xpath(properties.getProperty("mail")));
			srchtxt.clear();
			srchtxt.sendKeys(mai);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entering the data in mail id textbox is successful");
		} else {
			log.logLine(Testname, true, " Entering the data in mail id textbox is failed");
			throw new Exception(" Entering the data in mail id textbox is failed");
		}

		String conmai = test.readColumnData("conmailid", sheetname);

		if (doesElementExist(properties.getProperty("conmail"), 5)) {
			WebElement srchtxt = driver.findElement(By.xpath(properties.getProperty("conmail")));
			srchtxt.clear();
			srchtxt.sendKeys(conmai);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entering the data in confirm mail id textbox is successful");
		} else {
			log.logLine(Testname, true, " Entering the data in confirm mail id textbox is failed");
			throw new Exception(" Entering the data in confirm mail id  text box is failed");
		}

		if (doesElementExist(properties.getProperty("Goonline"), 5)) {
			WebElement Gordo = driver.findElement(By.xpath(properties.getProperty("Goonline")));
			Highlight(Gordo);
			if (Gordo.isSelected()){
				log.logLine(Testname, false, "Selecting Go online and save the Planet for Blue document is already Checked");
			}else{	
				WebElement Gordo1 = driver.findElement(By.xpath(properties.getProperty("Goonline1")));
				Gordo1.click();
				log.logLine(Testname, false, "Selecting Go online and save the Planet for Blue document is successful");
			}
		}

		if (doesElementExist(properties.getProperty("savebt"), 5)) {
			WebElement savebt = driver.findElement(By.xpath(properties.getProperty("savebt")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", savebt);	    	 
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on save buton is successfull");
		} else {
			log.logLine(Testname, true, "Click on save buton is failed");
			throw new Exception(" Click on save buton is  failed");
		}

		Thread.sleep(4000);

		if (doesElementExist(properties.getProperty("alertmsg"), 5)) {
			WebElement alertmsg = driver.findElement(By.xpath(properties.getProperty("alertmsg")));
			String abc=	alertmsg.getText() ;  	 
			log.logLine(Testname, false, "We received the message ***** "+abc);
		} else {
			log.logLine(Testname, true, " We didnot receive any alert message after changing option from print to online ");
		}

		if (doesElementExist(properties.getProperty("alertok"), 5)) {
			WebElement btnsrch = driver.findElement(By.xpath(properties.getProperty("alertok")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);	    	 
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on alertok is successfull");
		} else {
			log.logLine(Testname, true, " alertok popup did not display");
			throw new Exception(" Click on alertok  popup did not display");
		}


		if (doesElementExist(properties.getProperty("edit"), 5)) {
			WebElement edit = driver.findElement(By.xpath(properties.getProperty("edit")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", edit);	    	 
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on edit buton is successfull");
		} else {
			log.logLine(Testname, true, "Click on edit buton is failed");
			throw new Exception(" Click on edit buton is  failed");
		}

		Thread.sleep(2000);

		if (doesElementExist(properties.getProperty("clear"), 5)) {
			WebElement clear = driver.findElement(By.xpath(properties.getProperty("clear")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clear);	    	 
			log.logLine(Testname, false, "Click on clear buton is successful");
		} else {
			log.logLine(Testname, true, "Click on clear buton is failed");
			throw new Exception(" Click on clear buton is  failed");
		}

		Thread.sleep(2000);
		driver.switchTo().defaultContent(); 

		if (doesElementExist2(properties.getProperty("AdminMenu"), 5)) {
			List<WebElement> Mns = driver.findElements(By.cssSelector(properties.getProperty("AdminMenu")));
			for (WebElement Each:Mns) {
				if(Each.getText().equals("Admin")){
					Actions action = new Actions(driver);				
					action.moveToElement(Each).build().perform();						
					Thread.sleep(2000);
					WebElement Archive = driver.findElement(By.xpath(properties.getProperty("ArchiveSubMnu")));
					action.moveToElement(Archive).build().perform();
					Thread.sleep(2000);			
					break;}}

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
		}
		else {
			log.logLine(Testname, true, "Opening Admin window failed");
			throw new Exception("Opening Admin window failed");
		}

		Thread.sleep(3000);

		Set<String> handles = driver.getWindowHandles();
		firstWinHandle = driver.getWindowHandle(); 
		handles.remove(firstWinHandle);

		boolean browserexist = handles.iterator().hasNext();
		if (browserexist) {
			String winHandle=handles.iterator().next();
			if (winHandle!=firstWinHandle){
				String secondWinHandle=winHandle; 
				driver.switchTo().window(secondWinHandle);
				driver.manage().window().maximize();
			}
		}

		GmailVerification(Testname);


	}

	public void GmailVerification(String Testname) throws Exception {

		driver.get("https://www.googlemail.com");
		Thread.sleep(9000);

		if (doesElementExist2(properties.getProperty("Gmail_ID"), 5)) {
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Gmail_ID")));
			if (!(gmailid.getAttribute("class").equalsIgnoreCase("email-input hidden"))) {
				gmailid.clear();
				gmailid.sendKeys("automationpivot@gmail.com");
				log.logLine(Testname, false, "Entering the Gamil ID..");
			}
		} else {
			negativeCase(Testname, firstWinHandle, "", "Entering the gmail id failed");		
		}

		if (doesElementExist2(properties.getProperty("Gmail_NxtBtn"), 5)) {
			WebElement gmailnxt = driver.findElement(By.cssSelector(properties.getProperty("Gmail_NxtBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", gmailnxt);
			Thread.sleep(4000);}
		else {
			negativeCase(Testname, firstWinHandle, "", "Gmail_nxt button doesnot exist");
		}

		if (doesElementExist2(properties.getProperty("Gmail_Passwd"), 5)) {
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Gmail_Passwd")));
			gmailid.clear();
			gmailid.sendKeys("miracle@123");
			log.logLine(Testname, false, "Entering the Gmail password..");

		} else if (doesElementExist2(properties.getProperty("Gmail_NxtBtn"), 5)) {
			WebElement gmailnxt = driver.findElement(By.cssSelector(properties.getProperty("Gmail_NxtBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", gmailnxt);
			Thread.sleep(4000);
			if (doesElementExist2(properties.getProperty("Gmail_Passwd"), 5)) {
				WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Gmail_Passwd")));
				gmailid.clear();
				gmailid.sendKeys("miracle@123");
				log.logLine(Testname, false,"Entering the Gmail password..");
			} else {
				log.logLine(Testname, true,"Entering the password ID is failed");
				throw new Exception("Entering the Gmail password is failed");
			}

		} else {
			log.logLine(Testname, true,"Entering the password ID is failed");
			throw new Exception("Entering the Gmail password is failed");
		}

		Thread.sleep(1000);

		if (doesElementExist2(properties.getProperty("Gmail_PwdNxtBtn"), 5)) {
			WebElement gmailSign = driver.findElement(By.cssSelector(properties.getProperty("Gmail_PwdNxtBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", gmailSign);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the Gmail passwordnext button to sign in");
		} else {
			log.logLine(Testname, true,"Clicked on the gamil SignIn is failed");
			throw new Exception("Clicked on the gmail SignIn is failed");
		}


		Thread.sleep(3000);
		WebElement ret = waitForElement(properties.getProperty("Refresh"));

		if (doesElementExist2(properties.getProperty("Refresh"), 5)) {
			WebElement refreshbtn = driver.findElement(By.cssSelector(properties.getProperty("Refresh")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",refreshbtn);
			log.logLine(Testname, false, "Clicking on refresh btn of gmail is successful");
		} else {
			log.logLine(Testname, true,"Failed to click on refresh btn of gmail");	
		}

		Thread.sleep(1000);
		if (doesElementExist2(properties.getProperty("SrchMail"), 5)) {
			WebElement srchfld = driver.findElement(By.cssSelector(properties.getProperty("SrchMail")));
			srchfld.clear();
			srchfld.sendKeys("in:inbox Consent Confirmation for skyblue");
			log.logLine(Testname, false, "Entering the Search text..");
			srchfld.sendKeys(Keys.ENTER);
		} else {

			negativeCase(Testname, firstWinHandle, "", "Entering the Search text is failed");
		}

		Thread.sleep(2000);    

		if (doesElementExist2(properties.getProperty("consentMail"), 5)) {	    
			WebElement OpenMail = driver.findElement(By.cssSelector(properties.getProperty("consentMail")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", OpenMail);
			log.logLine(Testname, false, "Clicked on the First email to read");					

		} else {	   
			negativeCase(Testname, firstWinHandle, "", "Clicking on the consentMail to read is failed");		
		}

		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("contentlink1"), 5)) {
			WebElement contentlnk = driver.findElement(By.cssSelector(properties.getProperty("contentlink1")));
			Highlight(contentlnk);
			linktext=contentlnk.getText();
			Thread.sleep(3000);
			log.logLine(Testname, false,"We successfully clicked on contentlink");
		}
		else if (doesElementExist2(properties.getProperty("contentlink"), 5)) {
			WebElement contentlnk = driver.findElement(By.cssSelector(properties.getProperty("contentlink")));
			Highlight(contentlnk);
			linktext=contentlnk.getText();
			Thread.sleep(4000);
			log.logLine(Testname, false,"Clicked on the Document Link is successfull");

		} else {
			negativeCase(Testname, firstWinHandle, "", "contentlink doesnot exsits");	
		}

		driver.get(linktext);

		if (doesElementExist2(properties.getProperty("ConsentMsg"), 5)) {	    
			WebElement conmsg = driver.findElement(By.cssSelector(properties.getProperty("ConsentMsg")));
			if (conmsg.getText().equalsIgnoreCase("Your delivery preferences have been saved!")) { 				
				log.logLine(Testname, false, "Confirmation message after clicking on mail link is "+conmsg.getText());
			}else {
				log.logLine(Testname, true, "Confirmation message failed after clicking on mail link");				
			}
		} else {
			negativeCase(Testname, secondWinHandle, "", "Consent Message window did not open");					
		}
		Thread.sleep(3000);

		driver.close();
		Thread.sleep(1000);
		driver.switchTo().window(firstWinHandle);
	}


	public void consentuserhistory(String Testname) throws Exception{

		String[][] Archievedata = new String[50][50];

		List<WebElement> DataCnt1= driver.findElements(By.cssSelector("div[id='History-Consent-Pane-grid'] div table tbody tr"));

		if(DataCnt1.size()==0){
			log.logLine(Testname, false , "Validation failed as there is no data to validate in the grid");
		}

		else if(doesElementExist(properties.getProperty("Datevldn"), 5)){
			List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='History-Consent-Pane-grid']/div[2]/table/tbody/tr[1]/td"));
			log.logLine(Testname, false , "No. of rows in the archievehistory" + DataCnt1.size());
			if(DataCnt1.size()==skybluegridrow){
				log.logLine(Testname, false, "No. of records in archievehistory & skybluehistory matches");			
			}
			else{
				log.logLine(Testname, false, "No. of records in archievehistory & skybluehistory didnot match");			
			}
			for(int j = 1; j <= DataCnt1.size(); j++){
				for(int i = 1; i <= DataCnt.size(); i++) {
					Archievedata[j][i]=driver.findElement(By.xpath(".//*[@id='History-Consent-Pane-grid']/div[2]/table/tbody/tr["+j+"]/td["+i+"]")).getText();

					if(Archievedata[j][i].equalsIgnoreCase(Skybluedata[j][i])){
						log.logLine(Testname, false, "The data in row "+j+" and "+i+" column of Archeivehistory "+Archievedata[j][i]+" matches with "+" skyblue history "+Skybluedata[j][i]);

					}
					else {
						log.logLine(Testname, false, "The data in row "+j+" and "+i+" column of Archeivehistory "+Archievedata[j][i]+" didnot match with "+" skyblue history "+Skybluedata[j][i]);		
						break;
					}
				}	}			
		}
	}

	public void printactions(String Testname) throws Exception{		


		if (doesElementExist(properties.getProperty("edit"), 5)) {
			WebElement edit = driver.findElement(By.xpath(properties.getProperty("edit")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", edit);	    	 
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on edit buton is successfull");
		} else {
			log.logLine(Testname, true, "Click on edit buton is failed");
			//throw new Exception(" Click on edit buton is  failed");
		}

		if (doesElementExist(properties.getProperty("printmail1"), 5)) {
			WebElement print = driver.findElement(By.xpath(properties.getProperty("printmail1")));
			if (!print.isSelected())			
			{	WebElement print1 = driver.findElement(By.xpath(properties.getProperty("printmail")));
			print1.click();
			log.logLine(Testname, false, "Selecting printnmail option  for Blue document is successful");
			}else{
				log.logLine(Testname, false, "Selecting printnmail option  for Blue document is already Checked");
			}
		}

		if (doesElementExist(properties.getProperty("savebt"), 5)) {
			WebElement savebt = driver.findElement(By.xpath(properties.getProperty("savebt")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", savebt);	    	 
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on save buton is successful");
		} else {
			log.logLine(Testname, true, "Click on save buton is failed");
			throw new Exception(" Click on save buton is  failed");
		}

		Thread.sleep(3000);

		if (doesElementExist(properties.getProperty("alertmsg"), 5)) {
			WebElement alertmsg = driver.findElement(By.xpath(properties.getProperty("alertmsg")));
			String abc=	alertmsg.getText() ;  	 
			log.logLine(Testname, false, "We received the message **** "+abc);
		} else {
			log.logLine(Testname, true, " We didnot receive any alert message after changing option from online to print");
		}

		if (doesElementExist(properties.getProperty("alertok"), 5)) {
			WebElement btnsrch = driver.findElement(By.xpath(properties.getProperty("alertok")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);	    	 
			Thread.sleep(2000);
			log.logLine(Testname, false, "Click on alertok is successfull");
		} else {
			log.logLine(Testname, true, " alertok popup did not display");
			throw new Exception(" Click on alertok is  failed");
		}}


	public void Highlight(WebElement choseacts) throws Exception{
		for (int i = 0; i < 2; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			//    WebElement choseacts = null;
			js.executeScript( "arguments[0].setAttribute('style', arguments[1]);", choseacts , "color: red; border: 5px solid red;");
			Thread.sleep(1000);
			js.executeScript( "arguments[0].setAttribute('style', arguments[1]);", choseacts, "");
		}
	}
}





package pivotModules;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

import launchAuto.Initialization;
import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.support.ui.Select;

public class Modules_Workflow extends Page{

	public Modules_Workflow(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	} 
	@Override
	protected void load() {}
	@Override

	protected void isLoaded() throws Error {}

	String firstWinHandle = " ";
	String secondWinHandle = " ";
	String  Initiatialtext=" ";


	public boolean Notificationtemplate_update(String Testname) throws Exception { 		

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile")); 		
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(1000);
		driver.switchTo().defaultContent();

		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement canbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", canbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button doesnot exist");	
			throw new Exception("Clicking on Cancel button in Client/App popup is failed");
		}

		String modi= test.readColumnData("modifydata", sheetname);
		String module= test.readColumnData("Module", sheetname);
		String sub= test.readColumnData("Subject", sheetname);
		int mailbeforeaction=Messagecentervalidation(Testname);
		log.logLine(Testname, false, "No. of "+sub+" mails before making any change in workflow is "+mailbeforeaction );
		proofViewer(Testname);
		Modulesworkflow_click(Testname);
		Enter_sendkeymethod(Testname,modi,"1",module);
		String title = test.readColumnData("tit", sheetname);
		String stat= test.readColumnData("status", sheetname);
		Approve_sendkeymethod1(Testname,modi,title,module);
		Thread.sleep(6000);

		driver.close();
		driver.switchTo().window(firstWinHandle);
		driver.navigate().refresh(); 

		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement canbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", canbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button doesnot exist");	
			//throw new Exception("Clicking on Cancel button in Client/App popup is failed");
		}


		log.logLine(Testname, false, "We are validating the no of messages in the message center after little modifications ");	
		int mailafteraction=Messagecentervalidation(Testname);
		log.logLine(Testname, false, "No. of "+sub+" mails after making change in workflow is "+mailafteraction );	
		proofViewer(Testname);
		Modulesworkflow_click(Testname);
		Thread.sleep(2000);
		refreshactions(Testname,module);
		Thread.sleep(3000);

		String data1= test.readColumnData("data1", sheetname);
		rejectaction(Testname,data1,"reject",module,stat);
		Thread.sleep(2000);

		refreshactions(Testname,module);
		Thread.sleep(2000);
		revertaction(Testname,data1,"revert",module,stat);


		refreshactions(Testname,module);
		Thread.sleep(2000);
		Approve_findmethod(Testname,data1,module,stat);
		Thread.sleep(2000);

		refreshactions(Testname,module);
		Thread.sleep(2000);
		String data2= test.readColumnData("data2", sheetname);
		Approve_sendkeymethod(Testname,data2,"1",module,stat);

		driver.close();
		driver.switchTo().window(firstWinHandle);

		Notificationchange(Testname);


		return true;
	}


	public void proofViewer(String Testname) throws Exception  {

		if (doesElementExist2(properties.getProperty("Proofs"), 5)) {	    
			WebElement proofsmnu = driver.findElement(By.cssSelector(properties.getProperty("Proofs")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", proofsmnu);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Navigation to Proofs page successful");
		} else {
			log.logLine(Testname, true, "Clicking on Proofs menu is failed");
			throw new Exception("Clicking on Proofs menu is failed");
		}
		return ;}

	public int Messagecentervalidation(String Testname) throws Exception {

		InputOutputData test = new InputOutputData(); 
		test.setInputFile(properties.getProperty("InputDatafile")); 		
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		int count=0;
		String sub= test.readColumnData("Subject", sheetname);
		String val="false";

		Actions builder = new Actions(driver);
		if (doesElementExist(properties.getProperty("msgcenterimg"), 5)) { 
			WebElement msgcenter = driver.findElement(By.xpath(properties.getProperty("msgcenterimg")));
			builder.moveToElement(msgcenter).perform();
			Thread.sleep(2000);
			log.logLine(Testname, false, "Navigating to message center envelope in new pivot link..");	

			List<WebElement> selopts = driver.findElements(By.xpath(properties.getProperty("msgcenterlist")));
			for (WebElement lnk:selopts) {
				String abc=lnk.getText();
				if (lnk.getText().contains(sub)) {
					count= count+1;	
					val="true";
				}  
			}

			if(val.equalsIgnoreCase("true")){
				log.logLine(Testname, false, "Email with subject "+sub+"exists in the message center");

			}else{
				log.logLine(Testname, true , "Email with subject "+sub+"does not exists in the message center");

			}

		}else {
			log.logLine(Testname, false, "Navigating to message center envelope in new pivot link.. is failed");
			throw new Exception("Navigating to message center envelope in new pivot link.. is failed");
		}

		return count;

	}

	public void Notificationchange(String Testname) throws Exception { 		

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile")); 		
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(6000);
		if (doesElementExist2(properties.getProperty("SignOutBtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("SignOutBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Sign Out button");
		}
		else {
			log.logLine(Testname, true, "Clicking on Sign Out button is failed");
		}

		log.logLine(Testname, false, "Validation of 1st user:  starts");
		String Usrnme = test.readColumnData("UserName", sheetname);
		String Paswd = test.readColumnData("Password", sheetname);	
		signin(Testname,Usrnme,Paswd);
		Thread.sleep(4000);
		driver.switchTo().defaultContent();
		Modulesworkflow_click(Testname);
		log.logLine(Testname, false, "Modifying the notification by user "+Usrnme);
		String data1 = test.readColumnData("Data1", sheetname);
		String data2= test.readColumnData("Data2", sheetname);
		String data3 = test.readColumnData("Data3", sheetname);
		String module= test.readColumnData("Module", sheetname);
		Enter_sendkeymethod(Testname,data1,"1",module);
		String ti= test.readColumnData("tit", sheetname);;
		reverticonvalidation_user1(Testname,Usrnme,ti);
		refreshactions(Testname,module);
		Enter_sendkeymethod(Testname,data2,"2",module);
		refreshactions(Testname,module);
		//Enter_sendkeymethod(Testname,data3,"3");
		driver.close();
		driver.switchTo().window(firstWinHandle);
		Thread.sleep(2000);
		signout(Testname);
		Thread.sleep(5000);

		log.logLine(Testname, false, "Validation of other user:  starts");
		String Usrnme2 = test.readColumnData("UserName1", sheetname);
		String Paswd2 = test.readColumnData("Password1", sheetname);
		String title = test.readColumnData("title", sheetname);
		String title1 = test.readColumnData("title1", sheetname);
		String title2 = test.readColumnData("title2", sheetname);
		signin(Testname,Usrnme2,Paswd2);
		Thread.sleep(4000);
		driver.switchTo().defaultContent();
		Modulesworkflow_click(Testname);
		reverticonvalidation_user1(Testname,Usrnme2,title);
		Approve_sendkeymethod1(Testname,data1,title,module);
		Thread.sleep(2000);
		String stat= test.readColumnData("status", sheetname);
		reject_sendkeymethod(Testname,data2,title1,module,stat);
		Thread.sleep(2000);
		refreshactions(Testname,module);
		String defaultdata = test.readColumnData("Data4_default", sheetname);
		String defaultdata1 = test.readColumnData("Data5_default", sheetname);
		Approve_sendkeymethod(Testname,defaultdata,"1",module,stat);		
		refreshactions(Testname,module);
		Thread.sleep(5000);
		Approve_sendkeymethod(Testname,defaultdata1,"2",module,stat);
		driver.close();
		driver.switchTo().window(firstWinHandle);
		return;

	}

	public void  reverticonvalidation_user1(String Testname,String Usrnme,String title) throws Exception {

		if (doesElementExist(properties.getProperty("icon"), 5)) {	    
			WebElement pendicon= driver.findElement(By.xpath(properties.getProperty("icon")));
			if((pendicon.getAttribute("title")).contains(title)){
				log.logLine(Testname, false, "Clicking on pending icon to validate the availability of revert button, title of the icon is "+pendicon.getAttribute("title"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", pendicon);}
			else{
				log.logLine(Testname, true, "The icon's status is still not changed to pending");
				negativeCase(Testname, firstWinHandle, "", "The icon's status is still not changed to pending");	
			}

		} else {
			log.logLine(Testname, true, "icon doesnot exist");
			negativeCase(Testname, firstWinHandle, "", "icon doesnot exist");
		}

		Thread.sleep(2000);
		if(Usrnme.equalsIgnoreCase("Manohar"))
		{if (doesElementExist(properties.getProperty("revertbtn"), 5)) {	    
			WebElement ref= driver.findElement(By.xpath(properties.getProperty("revertbtn")));
			log.logLine(Testname, true, " Revert button  is available for the user "+ Usrnme+" as the connected user is same");
		} else {
			log.logLine(Testname, false, "Revert button  is un available for the user "+ Usrnme+" as the changes are made  different user");		
		}
		}
		else{
			if (doesElementExist(properties.getProperty("revertbtn"), 5)) {	    
				WebElement ref= driver.findElement(By.xpath(properties.getProperty("revertbtn")));
				log.logLine(Testname, false, " Revert button  is available for the user "+ Usrnme+" as the connected user is same");
			} else {
				log.logLine(Testname, true, "Revert button  is un available for the user "+ Usrnme+" as the connected user is different");		
			}}

		Thread.sleep(1000);

		if (doesElementExist(properties.getProperty("closebtn"), 5)) {	    
			WebElement close= driver.findElement(By.xpath(properties.getProperty("closebtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", close);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicking on close button  is successful");
		} else {
			log.logLine(Testname, true, "clicking on close button is failed");
			negativeCase(Testname, firstWinHandle, "", "clicking on close button is failed");
		}
	}

	public void reject_sendkeymethod(String Testname,String data,String tit,String module,String stat) throws Exception {

		if (doesElementExist(properties.getProperty("icon1"), 5)) {	    
			WebElement pendicon= driver.findElement(By.xpath(properties.getProperty("icon1")));
			if((pendicon.getAttribute("title")).contains(tit)){
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", pendicon);
				PleasewaitDisappear();
				log.logLine(Testname, false, "Clicking on pending icon  is successful, title of the icon is "+pendicon.getAttribute("title"));}
			else{
				log.logLine(Testname, true, "The icon's status is still not changed to pending");
				negativeCase(Testname, firstWinHandle, "", "The icon's status is still not changed to pending");	
			}

		} else {
			log.logLine(Testname, true, "icon doesnot exist");
			negativeCase(Testname, firstWinHandle, "", "icon doesnot exist");
		}

		if (doesElementExist(properties.getProperty("rejectbtn"), 5)) {	    
			WebElement ref= driver.findElement(By.xpath(properties.getProperty("rejectbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ref);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicking on reject button  is successful");
		} else {
			log.logLine(Testname, true, "clicking on reject button is failed");
			negativeCase(Testname, firstWinHandle, "", "clicking on revert button is failed");
		}

		Thread.sleep(1000);
		Alert alert = driver.switchTo().alert(); 
		alert.accept();
		PleasewaitDisappear();
		log.logLine(Testname, false, "Clicking on the alert popup is successful");

		refreshactions(Testname,module);
		Thread.sleep(3000);

		if (doesElementExist(properties.getProperty("icon1"), 5)) {	    
			WebElement icon= driver.findElement(By.xpath(properties.getProperty("icon1")));
			if((icon.getAttribute("title")).equalsIgnoreCase(stat)){
				log.logLine(Testname, false, "Icon's status has been changed to green after rejection , status of icon now is"+icon.getAttribute("title"));}
			else{
				log.logLine(Testname, true, "Icon's status has not yet changed to green");
				negativeCase(Testname, firstWinHandle, "", "Icon's status has not yet changed to green");
			}

		} else {
			log.logLine(Testname, true, " icon doesnot exist");
			negativeCase(Testname, firstWinHandle, "", " icon doesnot exist");
		}

		Thread.sleep(1000);
		if (doesElementExist2(properties.getProperty("initiationtxt"), 5)) {	   
			WebElement Ini = driver.findElement(By.cssSelector(properties.getProperty("initiationtxt")));
			String text = Ini.getAttribute("value");	;		
			log.logLine(Testname, false,  text + "Data in Workflow initiation text box after rejection");
			if(text.equalsIgnoreCase(data)){
				log.logLine(Testname, true, "The data in the initiation text box has been changed to "+ text);
			}
			else{
				log.logLine(Testname,false, "The data in the initiation text box remains same as before even after rejecting the change "+text);
			}

		} else {
			log.logLine(Testname, true, "initiationtxt text box doesnot exist");
			negativeCase(Testname, firstWinHandle, "", "initiationtxt text box doesnot exist");
		}	
		return;
	}

	public void revert_sendkeymethod(String Testname,String data,String tit,String module,String stat) throws Exception {

		if (doesElementExist(properties.getProperty("icon2"), 5)) {	    
			WebElement pendicon= driver.findElement(By.xpath(properties.getProperty("icon2")));
			if((pendicon.getAttribute("title")).contains(tit)){
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", pendicon);
				PleasewaitDisappear();
				log.logLine(Testname, false, "Clicking on pending icon  is successful, status of icon is"+pendicon.getAttribute("title"));}
			else{
				log.logLine(Testname, true, "The icon's status is still not changed to pending");
				negativeCase(Testname, firstWinHandle, "", "The icon's status is still not changed to pending");	
			}

		} else {
			log.logLine(Testname, true, "icon doesnot exist");
			negativeCase(Testname, firstWinHandle, "", "icon doesnot exist");
		}

		if (doesElementExist(properties.getProperty("revertbtn"), 5)) {	

			WebElement rev= driver.findElement(By.xpath(properties.getProperty("revertbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", rev);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicking on revert button  is successful");
			Thread.sleep(1000);
			Alert alert = driver.switchTo().alert(); 
			alert.accept();
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on the alert popup is successful");

			refreshactions(Testname,module);
			Thread.sleep(3000);

			if (doesElementExist(properties.getProperty("icon2"), 5)) {	    
				WebElement icon= driver.findElement(By.xpath(properties.getProperty("icon2")));
				if((icon.getAttribute("title")).equalsIgnoreCase(stat)){
					log.logLine(Testname, false, "Icon's status has been changed to green after rejection ,status of the icon is "+icon.getAttribute("title"));}
				else{
					log.logLine(Testname, true, "Icon's status has not yet changed to green");
					negativeCase(Testname, firstWinHandle, "", "Icon's status has not yet changed to green");
				}

			} else {
				log.logLine(Testname, true, " icon doesnot exist");
				negativeCase(Testname, firstWinHandle, "", " icon doesnot exist");
			}

			Thread.sleep(1000);
			if (doesElementExist2(properties.getProperty("initiationtxt"), 5)) {	   
				WebElement Ini = driver.findElement(By.cssSelector(properties.getProperty("initiationtxt")));
				String text = Ini.getAttribute("value");	;		
				log.logLine(Testname, false,  text + "Data in Workflow initiation text box after rejection");
				if(text.equalsIgnoreCase(data)){
					log.logLine(Testname, true, "The data in the initiation text box has been changed to "+ text);
				}
				else{
					log.logLine(Testname,false, "The data in the initiation text box remains same as before even after rejecting the change "+text);
				}

			} else {
				log.logLine(Testname, true, "initiationtxt text box doesnot exist");
				negativeCase(Testname, firstWinHandle, "", "initiationtxt text box doesnot exist");
			} 	}  else {
				log.logLine(Testname, true, "clicking on revert button is failed");
				negativeCase(Testname, firstWinHandle, "", "clicking on revert button is failed");
			}	
		return;
	}


	public void signout(String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("usrSignOutBtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("usrSignOutBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicking on Sign Out button");
		}else {
			log.logLine(Testname, true, "Clicking on Sign Out button is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
		}	
	}

	public void Enter_sendkeymethod(String Testname,String data,String i,String module) throws Exception {

		if (doesElementExist2(("div[id='panelwftemplates'] div[id='template"+i+"'] input[id='txtwfGlobal_Template_Name']"), 5)) {	   
			WebElement Ini = driver.findElement(By.cssSelector("div[id='panelwftemplates'] div[id='template"+i+"'] input[id='txtwfGlobal_Template_Name']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",  Ini);
			Initiatialtext =Ini.getAttribute("value");		
			log.logLine(Testname, false,  Initiatialtext + " is the Data in Workflow initiation text box before rejection");
			Ini.clear();
			Ini.sendKeys(data);
		} else {
			log.logLine(Testname, true, "initiationtxt text box doesnot exist");
			negativeCase(Testname, firstWinHandle, "", "initiationtxt text box doesnot exist");
			Relogin(Testname);	
		}

		if (doesElementExist(properties.getProperty("save"), 5)) {	    
			WebElement save= driver.findElement(By.xpath(properties.getProperty("save")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
			log.logLine(Testname, false, "Clicking on save button in workflow passed");
		} else {
			log.logLine(Testname, true, "Clicking on save button  in workflow is failed");
			negativeCase(Testname, firstWinHandle, "", "Clicking on save button  in workflow is failed");
			Relogin(Testname);	
		}
		Thread.sleep(3000);

		if (doesElementExist(properties.getProperty("save"), 5)) {	    
			WebElement save= driver.findElement(By.xpath(properties.getProperty("save")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
			log.logLine(Testname, false, "Clicking on save button in workflow passed");
		} else {
			log.logLine(Testname, true, "Clicking on save button  in workflow is failed");
			negativeCase(Testname, firstWinHandle, "", "Clicking on save button  in workflow is failed");
			Relogin(Testname);	
		}

		refreshactions(Testname,module);
		Thread.sleep(5000);

		if (doesElementExist((".//*[@id='template"+i+"']/i"),5)) {	    
			WebElement icon= driver.findElement(By.xpath(".//*[@id='template"+i+"']/i"));
			if((icon.getAttribute("title")).equalsIgnoreCase("Pending Changes")){
				log.logLine(Testname, false, "The icon has been changed to pending from User1 , status of icon is"+icon.getAttribute("title"));}
			else{
				log.logLine(Testname, true, "The icon is still not changed to pending from user1");
				//negativeCase(Testname, firstWinHandle, "","The icon is still not changed to pending from user1");
			}}			
		else {
			log.logLine(Testname, true, "icon doesnot exist");
			/*	negativeCase(Testname, firstWinHandle, "", "icon doesnot exist");
			Relogin(Testname);	*/
		}

	}

	public void Approve_sendkeymethod1(String Testname,String data,String tit,String module) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile")); 		
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		String stat= test.readColumnData("status", sheetname);

		if (doesElementExist(properties.getProperty("icon"), 5)){
			WebElement icon= driver.findElement(By.xpath(properties.getProperty("icon")));
			if((icon.getAttribute("title")).contains(tit)){
				log.logLine(Testname, false, "The icon has been changed to pending from User1 , status now "+ icon.getAttribute("title"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", icon);}
			else{
				log.logLine(Testname, true, "The icon is still not changed to pending from user1");
				negativeCase(Testname, firstWinHandle, "","The icon is still not changed to pending from user1");
			}

		} else {
			log.logLine(Testname, true, "icon doesnot exist");
			negativeCase(Testname, firstWinHandle, "", "icon doesnot exist");	
		}
		Thread.sleep(2000);

		if (doesElementExist(properties.getProperty("approvebtn"), 5)) {	    
			WebElement approve= driver.findElement(By.xpath(properties.getProperty("approvebtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", approve);
			log.logLine(Testname, false, "Clicking on approve button  is successful");
		} else {
			log.logLine(Testname, true, "clicking on approve button is failed");
			negativeCase(Testname, firstWinHandle, "", "clicking on approve button is failed");
		}

		Alert alert = driver.switchTo().alert(); 
		alert.accept();
		PleasewaitDisappear();

		refreshactions(Testname,module);
		Thread.sleep(5000);


		if (doesElementExist(properties.getProperty("icon"), 5)) {	    
			WebElement icon= driver.findElement(By.xpath(properties.getProperty("icon")));
			if((icon.getAttribute("title")).equalsIgnoreCase(stat)){
				log.logLine(Testname, false, "Icon's status has been changed to green after approval , status now"+icon.getAttribute("title"));}
			else{
				log.logLine(Testname, true, "Icon's status has not yet changed to green");
				negativeCase(Testname, firstWinHandle, "", "Icon's status has not yet changed to green");
			}

		} else {
			log.logLine(Testname, true, " icon doesnot exist");
			negativeCase(Testname, firstWinHandle, "", " icon doesnot exist");
		}

		if (doesElementExist2(properties.getProperty("initiationtxt"), 5)) {	   
			WebElement Ini = driver.findElement(By.cssSelector(properties.getProperty("initiationtxt")));
			Initiatialtext = driver.findElement(By.cssSelector(properties.getProperty("initiationtxt"))).getAttribute("value");			
			log.logLine(Testname, false,  Initiatialtext + "Data in Workflow initiation text box after approval");
			if( Initiatialtext.equalsIgnoreCase(data)){
				log.logLine(Testname, false, "The data in the initiation text box has been changed to "+ Initiatialtext+" after the approval of data change");
			}
			else{
				log.logLine(Testname,true, "The data in the initiation text box remains same as before after approving the change "+ Initiatialtext);
			}

		} else {
			log.logLine(Testname, true, "initiationtxt text box doesnot exist");
			negativeCase(Testname, firstWinHandle, "", "initiationtxt text box doesnot exist");
		}	

	}



	public void refreshactions(String Testname,String data) throws Exception {

		if (doesElementExist(properties.getProperty("cancel"), 5)) {	    
			WebElement can= driver.findElement(By.xpath(properties.getProperty("cancel")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", can);
			log.logLine(Testname, false, "Clicking on cancel button in workflow passed");
			PleasewaitDisappear();
		} else {
			log.logLine(Testname, true, "Clicking on cancel button  in workflow is failed");
			negativeCase(Testname, firstWinHandle, "", "Clicking on cancel button  in workflow is failed");
		}


		Thread.sleep(2000);
		if(doesElementExist(properties.getProperty("pimodule"), 5)){
			WebElement choose = driver.findElement(By.xpath(properties.getProperty("pimodule")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choose);
			Thread.sleep(2000);	
			String abc1="No";
			List<WebElement> DataCnt= driver.findElements(By.cssSelector("div div[id='ddlwfmodules-list'] div ul li"));
			for (WebElement lnk:DataCnt) {			
				if (lnk.getText().contains(data)) {			
					((JavascriptExecutor) driver).executeScript("arguments[0].click()",lnk);
					abc1="Yes";
					log.logLine(Testname, false, "Selected  "+lnk.getText()+" option from Pivot module list");
					break;}	
			}

			if(abc1.equalsIgnoreCase("No")){
				log.logLine(Testname, true, data+" doesnot exist in Pivot module list");
				negativeCase(Testname, firstWinHandle, "", " doesnot exist in Pivot module list");
			}}
	}
	public void rejectaction(String Testname,String data1,String action,String module,String stat) throws Exception {

		if (doesElementExist2(properties.getProperty("initiationtxt"), 5)) {
			WebElement Ini = driver.findElement(By.cssSelector(properties.getProperty("initiationtxt")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Ini);
			Initiatialtext = driver.findElement(By.cssSelector(properties.getProperty("initiationtxt"))).getAttribute("value");		
			log.logLine(Testname, false,  Initiatialtext + "Data in Workflow initiation text box before rejection");
			Ini.clear();
			Ini.sendKeys(data1);

		} else {
			log.logLine(Testname, true, "initiationtxt text box doesnot exist");
			negativeCase(Testname, firstWinHandle, "", "initiationtxt text box doesnot exist");
		}

		if (doesElementExist(properties.getProperty("save"), 5)) {	    
			WebElement save= driver.findElement(By.xpath(properties.getProperty("save")));
			Highlight(save);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
			log.logLine(Testname, false, "Clicking on save button in workflow passed");
			PleasewaitDisappear();
		}else {
			log.logLine(Testname, true, "Clicking on save button  in workflow is failed");
			negativeCase(Testname, firstWinHandle, "", "Clicking on save button  in workflow is failed");
		}

		Thread.sleep(6000);

		if (doesElementExist(properties.getProperty("icon"), 5)) {	    
			WebElement pendicon= driver.findElement(By.xpath(properties.getProperty("icon")));
			if((pendicon.getAttribute("title")).equalsIgnoreCase("Pending Changes")){
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", pendicon);
				PleasewaitDisappear();
				log.logLine(Testname, false, "Clicking on pending icon  is successful, status now "+ pendicon.getAttribute("title"));}
			else{
				log.logLine(Testname, true, "The icon's status is still not changed to pending");
				//negativeCase(Testname, firstWinHandle, "", "The icon's status is still not changed to pending");	
			}

		} else {
			log.logLine(Testname, true, "icon doesnot exist");
			negativeCase(Testname, firstWinHandle, "", "icon doesnot exist");
		}
		if(action.equalsIgnoreCase("reject")){
			if (doesElementExist(properties.getProperty("rejectbtn"), 5)) {	    
				WebElement reject= driver.findElement(By.xpath(properties.getProperty("rejectbtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", reject);
				Thread.sleep(1000);
				log.logLine(Testname, false, "Clicking on reject button  is successful");
			} else {
				log.logLine(Testname, true, "clicking on reject button is failed");
				//negativeCase(Testname, firstWinHandle, "", "clicking on reject button is failed");
			}}
		else{
			if (doesElementExist(properties.getProperty("revertbtn"), 5)) {	    
				WebElement rev= driver.findElement(By.xpath(properties.getProperty("revertbtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", rev);
				Thread.sleep(3000);
				log.logLine(Testname, false, "Clicking on revert button  is successful");
			} else {
				log.logLine(Testname, true, "clicking on revert button is failed");
				negativeCase(Testname, firstWinHandle, "", "clicking on revert button is failed");
			}}

		Thread.sleep(1000);
		Alert alert = driver.switchTo().alert(); 
		alert.accept();
		PleasewaitDisappear();
		log.logLine(Testname, false, "Clicking on the alert popup is successful");

		refreshactions(Testname,module);
		Thread.sleep(3000);

		if (doesElementExist(properties.getProperty("icon"), 5)) {	    
			WebElement icon= driver.findElement(By.xpath(properties.getProperty("icon")));
			if((icon.getAttribute("title")).equalsIgnoreCase(stat)){
				log.logLine(Testname, false, "Icon's status has been changed to green after rejection, status of icon now is "+icon.getAttribute("title"));}
			else{
				log.logLine(Testname, true, "Icon's status has not yet changed to green");
				negativeCase(Testname, firstWinHandle, "", "Icon's status has not yet changed to green");
			}

		} else {
			log.logLine(Testname, true, " icon doesnot exist");
			negativeCase(Testname, firstWinHandle, "", " icon doesnot exist");
		}

		Thread.sleep(1000);
		if (doesElementExist2(properties.getProperty("initiationtxt"), 5)) {	   
			WebElement Ini = driver.findElement(By.cssSelector(properties.getProperty("initiationtxt")));
			String text = Ini.getAttribute("value");	;		
			log.logLine(Testname, false,  text + "Data in Workflow initiation text box after rejection");
			if(text.equalsIgnoreCase(data1)){
				log.logLine(Testname, true, "The data in the initiation text box has been changed to "+ text);
			}
			else{
				log.logLine(Testname,false, "The data in the initiation text box remains same as before even after rejecting the change "+text);
			}

		} else {
			log.logLine(Testname, true, "initiationtxt text box doesnot exist");
			negativeCase(Testname, firstWinHandle, "", "initiationtxt text box doesnot exist");
		}	
		return;
	}


	public void revertaction(String Testname,String data1,String action,String module,String stat) throws Exception {

		if (doesElementExist2(properties.getProperty("initiationtxt"), 5)) {
			WebElement Ini = driver.findElement(By.cssSelector(properties.getProperty("initiationtxt")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Ini);
			Initiatialtext = driver.findElement(By.cssSelector(properties.getProperty("initiationtxt"))).getAttribute("value");		
			log.logLine(Testname, false,  Initiatialtext + "Data in Workflow initiation text box before rejection");
			Ini.clear();
			Ini.sendKeys(data1);
		} else {
			log.logLine(Testname, true, "initiationtxt text box doesnot exist");
			negativeCase(Testname, firstWinHandle, "", "initiationtxt text box doesnot exist");
		}

		if (doesElementExist(properties.getProperty("save"), 5)) {	    
			WebElement save= driver.findElement(By.xpath(properties.getProperty("save")));
			Highlight(save);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
			log.logLine(Testname, false, "Clicking on save button in workflow passed");
			PleasewaitDisappear();
		} else {
			log.logLine(Testname, true, "Clicking on save button  in workflow is failed");
			negativeCase(Testname, firstWinHandle, "", "Clicking on save button  in workflow is failed");
		}

		Thread.sleep(3000);

		if (doesElementExist(properties.getProperty("save"), 5)) {	    
			WebElement save= driver.findElement(By.xpath(properties.getProperty("save")));
			Highlight(save);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
			log.logLine(Testname, false, "Clicking on save button in workflow passed");
			PleasewaitDisappear();
		}else {
			log.logLine(Testname, true, "Clicking on save button  in workflow is failed");
			negativeCase(Testname, firstWinHandle, "", "Clicking on save button  in workflow is failed");
		}

		Thread.sleep(6000);

		if (doesElementExist(properties.getProperty("icon"), 5)) {	    
			WebElement pendicon= driver.findElement(By.xpath(properties.getProperty("icon")));
			if((pendicon.getAttribute("title")).equalsIgnoreCase("Pending Changes")){
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", pendicon);
				PleasewaitDisappear();
				log.logLine(Testname, false, "Clicking on pending icon  is successful, status of icon now is "+pendicon.getAttribute("title"));}
			else{
				log.logLine(Testname, false, "The icon's status is still not changed to pending");
				negativeCase(Testname, firstWinHandle, "","The icon's status is still not changed to pending");
			}

		} else {
			log.logLine(Testname, true, "icon doesnot exist");
			negativeCase(Testname, firstWinHandle, "", "icon doesnot exist");
		}
		if(action.equalsIgnoreCase("reject")){
			if (doesElementExist(properties.getProperty("rejectbtn"), 5)) {	    
				WebElement reject= driver.findElement(By.xpath(properties.getProperty("rejectbtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", reject);
				Thread.sleep(3000);
				log.logLine(Testname, false, "Clicking on reject button  is successful");
			} else {
				log.logLine(Testname, true, "clicking on reject button is failed");
				negativeCase(Testname, firstWinHandle, "", "clicking on reject button is failed");
			}}
		else{
			if (doesElementExist(properties.getProperty("revertbtn"), 5)) {	    
				WebElement rev= driver.findElement(By.xpath(properties.getProperty("revertbtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", rev);
				Thread.sleep(3000);
				log.logLine(Testname, false, "Clicking on revert button  is successful");
			} else {
				log.logLine(Testname, true, "clicking on revert button is failed");
				negativeCase(Testname, firstWinHandle, "", "clicking on revert button is failed");
			}}

		refreshactions(Testname,module);
		Thread.sleep(3000);

		if (doesElementExist(properties.getProperty("icon"), 5)) {	    
			WebElement icon= driver.findElement(By.xpath(properties.getProperty("icon")));
			if((icon.getAttribute("title")).equalsIgnoreCase(stat)){
				log.logLine(Testname, false, "Icon's status has been changed to green after revert, current staus of icon is "+icon.getAttribute("title"));}
			else{
				log.logLine(Testname, true, "Icon's status has not yet changed to green");
				negativeCase(Testname, firstWinHandle, "", "Icon's status has not yet changed to green");
			}

		} else {
			log.logLine(Testname, true, " icon doesnot exist");
			negativeCase(Testname, firstWinHandle, "", " icon doesnot exist");
		}

		Thread.sleep(1000);
		if (doesElementExist2(properties.getProperty("initiationtxt"), 5)) {	   
			WebElement Ini = driver.findElement(By.cssSelector(properties.getProperty("initiationtxt")));
			String text = Ini.getAttribute("value");	;		
			log.logLine(Testname, false,  text + "Data in Workflow initiation text box after reverting");
			if(text.equalsIgnoreCase(data1)){
				log.logLine(Testname, true, "The data in the initiation text box has been changed to "+ text);
			}
			else{
				log.logLine(Testname,false, "The data in the initiation text box remains same as before after reverting the change "+text);
			}

		} else {
			log.logLine(Testname, true, "initiationtxt text box doesnot exist");
			negativeCase(Testname, firstWinHandle, "", "initiationtxt text box doesnot exist");
		}	
		return;
	}
	public void Approve_findmethod(String Testname,String data,String module,String stat) throws Exception {

		if (doesElementExist2(properties.getProperty("initiationtxt"), 5)) {	    
			Initiatialtext = driver.findElement(By.cssSelector(properties.getProperty("initiationtxt"))).getAttribute("value");	;		
			log.logLine(Testname, false,  Initiatialtext + "Data in Workflow initiation text box before Approval");
		} else {
			log.logLine(Testname, true, "initiationtxt text box doesnot exist");
			negativeCase(Testname, firstWinHandle, "", "initiationtxt text box doesnot exist");
		}

		if (doesElementExist(properties.getProperty("Find"), 5)) {	    
			WebElement find= driver.findElement(By.xpath(properties.getProperty("Find")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", find);
			log.logLine(Testname, false, "Clicking on find button in workflow passed");
		} else {
			log.logLine(Testname, true, "Clicking on find button  in workflow is failed");
			negativeCase(Testname, firstWinHandle, "", "Clicking on find button  in workflow is failed");
		}

		Thread.sleep(2000);

		if(doesElementExist(properties.getProperty("emailtype"), 5)){
			WebElement type= driver.findElement(By.xpath(properties.getProperty("emailtype")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", type);
			String abc1="No";
			List<WebElement> DataCnt= driver.findElements(By.cssSelector("div div[id='ddlwffpEmailType-list'] div ul li"));
			for (WebElement lnk:DataCnt) {			
				if (lnk.getText().equalsIgnoreCase("Proof Load")) {			
					((JavascriptExecutor) driver).executeScript("arguments[0].click()",lnk);
					abc1="Yes";
					log.logLine(Testname, false, "Selected  "+lnk.getText()+" option from emailtype list");
					break;}	
			}

			if(abc1.equalsIgnoreCase("No")){
				log.logLine(Testname, true, data+" doesnot exist in Pivot module list");
			}

		}else {
			log.logLine(Testname, true, "emailtype listbox doesnot exist "); 
			negativeCase(Testname, firstWinHandle, "", "emailtype listbox doesnot exist  ");
		}

		Thread.sleep(5000);

		if (doesElementExist(properties.getProperty("templatelnk"), 5)) {	    
			WebElement link= driver.findElement(By.xpath(properties.getProperty("templatelnk")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", link);
			log.logLine(Testname, false, "Clicking on template link is passed");
			Thread.sleep(2000);
		} else {
			log.logLine(Testname, true, "Clicking on template link is failed");
			negativeCase(Testname, firstWinHandle, "", "Clicking on template is failed");
		}
		Thread.sleep(2000);


		if (doesElementExist(properties.getProperty("save"), 5)) {	    
			WebElement save= driver.findElement(By.xpath(properties.getProperty("save")));
			Highlight(save);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
			log.logLine(Testname, false, "Clicking on save button in workflow passed");
			PleasewaitDisappear();
		}else {
			log.logLine(Testname, true, "Clicking on save button  in workflow is failed");
			negativeCase(Testname, firstWinHandle, "", "Clicking on save button  in workflow is failed");
		}


		Thread.sleep(7000);

		if (doesElementExist(properties.getProperty("icon"), 5)) {	    
			WebElement icon= driver.findElement(By.xpath(properties.getProperty("icon")));
			if((icon.getAttribute("title")).equalsIgnoreCase("Pending Changes")){
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", icon);
				log.logLine(Testname, false, "Clicking on pending icon  is successful, current status of icon is "+icon.getAttribute("title"));}
			else{
				log.logLine(Testname, false, "The icon's status is still not changed to pending");
				negativeCase(Testname, firstWinHandle, "", "The icon's status is still not changed to pending");
			}

		} else {
			log.logLine(Testname, true, "icon doesnot exist");
			negativeCase(Testname, firstWinHandle, "", "icon doesnot exist");
		}

		if (doesElementExist(properties.getProperty("approvebtn"), 5)) {	    
			WebElement approve= driver.findElement(By.xpath(properties.getProperty("approvebtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", approve);
			log.logLine(Testname, false, "Clicking on approve button  is successful");
		} else {
			log.logLine(Testname, true, "clicking on approve button is failed");
			negativeCase(Testname, firstWinHandle, "", "clicking on approve button is failed");
		}

		Alert alert = driver.switchTo().alert(); 
		alert.accept();
		PleasewaitDisappear();


		refreshactions(Testname,module);
		Thread.sleep(5000);

		if (doesElementExist(properties.getProperty("icon"), 5)) {	    
			WebElement icon= driver.findElement(By.xpath(properties.getProperty("icon")));
			if((icon.getAttribute("title")).equalsIgnoreCase(stat)){
				log.logLine(Testname, false, "Icon's status has been changed to green after approval, current status of icon is "+icon.getAttribute("title"));}
			else{
				log.logLine(Testname, true, "Icon's status has not yet changed to green");
				negativeCase(Testname, firstWinHandle, "", "Icon's status has not yet changed to green");
			}

		} else {
			log.logLine(Testname, true, " icon doesnot exist");
			negativeCase(Testname, firstWinHandle, "", " icon doesnot exist");
		}

		if (doesElementExist2(properties.getProperty("initiationtxt"), 5)) {	   
			WebElement Ini = driver.findElement(By.cssSelector(properties.getProperty("initiationtxt")));
			Initiatialtext = driver.findElement(By.cssSelector(properties.getProperty("initiationtxt"))).getAttribute("value");		
			log.logLine(Testname, false,  Initiatialtext + "Data in Workflow initiation text box after rejection");
			if( Initiatialtext.equalsIgnoreCase("Proof Load Html 1")){
				log.logLine(Testname, false, "The data in the initiation text box has been changed to "+ Initiatialtext+" after the approval of data change");
			}
			else{
				log.logLine(Testname,true, "The data in the initiation text box remains same as before after approving the change "+ Initiatialtext);
			}

		} else {
			log.logLine(Testname, true, "initiationtxt text box doesnot exist");
			negativeCase(Testname, firstWinHandle, "", "initiationtxt text box doesnot exist");
		}	

	}

	public void Approve_sendkeymethod(String Testname,String data,String k,String module,String stat) throws Exception {

		if (doesElementExist2(("div[id='panelwftemplates'] div[id='template"+k+"'] input[id='txtwfGlobal_Template_Name']"), 5)) {	   
			WebElement Ini = driver.findElement(By.cssSelector("div[id='panelwftemplates'] div[id='template"+k+"'] input[id='txtwfGlobal_Template_Name']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",  Ini);
			Initiatialtext = Ini.getAttribute("value");		
			log.logLine(Testname, false,  Initiatialtext + "Data in Workflow initiation text box before approval");
			Ini.clear();
			Ini.sendKeys(data);
		} else {
			log.logLine(Testname, true, "initiationtxt text box doesnot exist");
			negativeCase(Testname, firstWinHandle, "", "initiationtxt text box doesnot exist");
		}

		if (doesElementExist(properties.getProperty("save"), 5)) {	    
			WebElement save= driver.findElement(By.xpath(properties.getProperty("save")));
			Highlight(save);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
			log.logLine(Testname, false, "Clicking on save button in workflow passed");
			PleasewaitDisappear();
		} else {
			log.logLine(Testname, true, "Clicking on save button  in workflow is failed");
			negativeCase(Testname, firstWinHandle, "", "Clicking on save button  in workflow is failed");
		}

		refreshactions(Testname,module);
		Thread.sleep(5000);

		if (doesElementExist((".//*[@id='template"+k+"']/i"),5)) {	    
			WebElement icon= driver.findElement(By.xpath(".//*[@id='template"+k+"']/i"));
			if((icon.getAttribute("title")).equalsIgnoreCase("Pending Changes")){
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", icon);
				log.logLine(Testname, false, "Clicking on pending icon  is successful, current status of icon is "+icon.getAttribute("title"));}
			else{
				log.logLine(Testname, true, "The icon's status is still not changed to pending");
				negativeCase(Testname, firstWinHandle, "","The icon's status is still not changed to pending");
			}

		} else {
			log.logLine(Testname, true, "icon doesnot exist");
			negativeCase(Testname, firstWinHandle, "", "icon doesnot exist");
		}

		if (doesElementExist(properties.getProperty("approvebtn"), 5)) {	    
			WebElement approve= driver.findElement(By.xpath(properties.getProperty("approvebtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", approve);
			log.logLine(Testname, false, "Clicking on approve button  is successful");
		} else {
			log.logLine(Testname, true, "clicking on approve button is failed");
			negativeCase(Testname, firstWinHandle, "", "clicking on approve button is failed");
		}

		Alert alert = driver.switchTo().alert(); 
		alert.accept();
		PleasewaitDisappear();

		refreshactions(Testname,module);
		Thread.sleep(3000);

		if (doesElementExist((".//*[@id='template"+k+"']/i"),5)) {	    
			WebElement icon= driver.findElement(By.xpath(".//*[@id='template"+k+"']/i"));
			if((icon.getAttribute("title")).equalsIgnoreCase(stat)){
				log.logLine(Testname, false, "Icon's status has been changed to green after approval, current status of icon is "+icon.getAttribute("title"));}
			else{
				log.logLine(Testname, true, "Icon's status has not yet changed to green");
				negativeCase(Testname, firstWinHandle, "", "Icon's status has not yet changed to green");
			}

		} else {
			log.logLine(Testname, true, " icon doesnot exist");
			negativeCase(Testname, firstWinHandle, "", " icon doesnot exist");
		}

		if (doesElementExist2(("div[id='panelwftemplates'] div[id='template"+k+"'] input[id='txtwfGlobal_Template_Name']"), 5)) {	   
			WebElement Ini = driver.findElement(By.cssSelector("div[id='panelwftemplates'] div[id='template"+k+"'] input[id='txtwfGlobal_Template_Name']"));
			Initiatialtext = Ini.getAttribute("value");			
			log.logLine(Testname, false,  Initiatialtext + " is the Data in Workflow initiation text box after approval");
			if( Initiatialtext.equalsIgnoreCase(data)){
				log.logLine(Testname, false, "The data in the initiation text box has been changed to "+ Initiatialtext+" after the approval of data change");
			}
			else{
				log.logLine(Testname,true, "The data in the initiation text box remains same as before after approving the change "+ Initiatialtext);
			}

		} else {
			log.logLine(Testname, true, "initiationtxt text box doesnot exist");
			negativeCase(Testname, firstWinHandle, "", "initiationtxt text box doesnot exist");
		}	

	}

	public boolean Modulesworkflow_click(String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement canbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", canbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button doesnot exist");	
			throw new Exception("Clicking on Cancel button in Client/App popup is failed");
		}

		Thread.sleep(1000);

		Actions builder = new Actions(driver);                
		WebElement mnuElement = driver.findElement(By.xpath(properties.getProperty("AdminMenu")));        
		if (doesElementExist(properties.getProperty("AdminMenu"), 10)) {                          
			// Move cursor to the Main Menu Element  
			builder.moveToElement(mnuElement).perform();                
			// Clicking on the Hidden SubMenu  
			Thread.sleep(8000);
			if (doesElementExist2(properties.getProperty("Adminlnk"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Adminlnk")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().contains("HA Admin")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Clicking on HA Admin..");                
						break;
					}                                
				}

			} else {
				log.logLine(Testname, true, "Clicking on HA Admin.. is failed");
				throw new Exception("Clicking on HA Admin.. is failed");                        
			}
		}

		Thread.sleep(12000);

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
				Thread.sleep(15000);


				if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetExplorer"))) {
					if (doesElementExist2(properties.getProperty("OverrideLnk"), 5)) {
						driver.get("javascript:document.getElementById('overridelink').click();");
						Thread.sleep(60000);
					}
				}
			}
		}

		if (doesElementExist(properties.getProperty("Modules"), 5)) {	    
			WebElement mods = driver.findElement(By.xpath(properties.getProperty("Modules")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", mods);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on Modules button is successful");
		} else {
			log.logLine(Testname, true, "Clicking on Modules button is failed");
			negativeCase(Testname, firstWinHandle, "", "Clicking on Modules button is failed");
			Relogin(Testname);	
		}


		if (doesElementExist(properties.getProperty("Workflow"), 5)) {  
			WebElement workf= driver.findElement(By.xpath(properties.getProperty("Workflow")));
			builder.moveToElement(workf).perform();                		
			Thread.sleep(1000);
			if (doesElementExist(properties.getProperty("NotificationTemplates"), 5)) {
				WebElement nottemp= driver.findElement(By.xpath(properties.getProperty("NotificationTemplates")));
				builder.moveToElement(nottemp).perform(); 
				Thread.sleep(1000);
				((JavascriptExecutor) driver).executeScript("arguments[0].click()",nottemp);
				Thread.sleep(1000);
				log.logLine(Testname, false, "Clicking on Notification Templates is passed");		               
			} 
			else{log.logLine(Testname, true, "Clicking on Notification Templates option  is failed");
			negativeCase(Testname, firstWinHandle, "", "Clicking on Notification Templates option  is failed ");			
			}
		} 
		else {
			log.logLine(Testname, true, "Clicking on Workflow module is successful ");  
			negativeCase(Testname, firstWinHandle, "", "Clicking on Workflow module is successful ");
			Relogin(Testname);	
		}

		if(doesElementExist(properties.getProperty("pimodule"), 5)){
			WebElement choose = driver.findElement(By.xpath(properties.getProperty("pimodule")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", choose);
			Thread.sleep(2000);	
			String abc1="No";
			List<WebElement> DataCnt= driver.findElements(By.cssSelector("div div[id='ddlwfmodules-list'] div ul li"));
			for (WebElement lnk:DataCnt) {			
				if (lnk.getText().contains("Proof (PVT9012)")) {			
					((JavascriptExecutor) driver).executeScript("arguments[0].click()",lnk);
					abc1="Yes";
					log.logLine(Testname, false, "Selected  "+lnk.getText()+" option from Pivot module list");
					break;}	
			}

			if(abc1.equalsIgnoreCase("No")){
				log.logLine(Testname, true, "Proof (PVT9012) doesnot exist in Pivot module list");
			}

		}else {
			log.logLine(Testname, true, "Pivotmodule listbox doesnot exist "); 
			negativeCase(Testname, firstWinHandle, "", "Pivotmodule listbox doesnot exist  ");
			Relogin(Testname);	
		}

		return true;
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

	}

	public boolean Relogin(String Testname) throws Exception {
		log.logLine(Testname, false, "Logging in back to Super User to continue suite execution");
		PivotSignInOut loginPge = new PivotSignInOut(driver, log);
		loginPge.load(Initialization.Browser, Initialization.EnvirSite);
		Thread.sleep(6000);
		loginPge.loginAs(Initialization.UserID, Initialization.Passwd);
		return true;
	}

	public void Highlight(WebElement choseacts) throws Exception{
		for (int i = 0; i < 2; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript( "arguments[0].setAttribute('style', arguments[1]);", choseacts , "color: red; border: 5px solid red;");
			Thread.sleep(1000);
			js.executeScript( "arguments[0].setAttribute('style', arguments[1]);", choseacts, "");
		}
	}



}

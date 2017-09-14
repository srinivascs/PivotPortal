package pivotModules;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;

import launchAuto.Initialization;
import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class ManageUsersSimpleSearch extends Page{

	public ManageUsersSimpleSearch(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	} 
	@Override
	protected void load() {}
	@Override

	protected void isLoaded() throws Error {}

	Actions action = new Actions(driver);
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
	Date date = new Date();
	String todaysDate = dateFormat.format(date);


	public static String stmtnum;
	public static String Editstmtnum;
	public static String Beforeupdte;
	public static String Afterupdte;
	public static String user="Manohar";
	public static String user1="Latha";
	public static String userid="fe01";
	public static String user2="dddddddddddd";
	public static boolean result;
	public static boolean result1;
	public static boolean result2;
	public static boolean result3;
	String firstWinHandle = null;
	String secondWinHandle = null;

	//public static String Text="01213";
	public static String SearchText;



	public boolean Clientappsearch(String AccNo, String Testname) throws Exception {
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
		}

		Thread.sleep(6000);
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


		Thread.sleep(30000);

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

		if (doesElementExist2(properties.getProperty("ManageUsers"), 5)) {
			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ManageUsers")));
			for (WebElement lnk:selopts) {
				Thread.sleep(1000);
				if (lnk.getText().equals("Manage Users")) {
					Thread.sleep(1000);
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Clicking on Manage Users..");	
					break;
				}	
			}

		} else {
			log.logLine(Testname, true, "Clicking on Manage Users is failed");
			negativeCase(Testname, firstWinHandle, "", "Click on Manager User is failed");	
			throw new Exception("Clicking on Manage Users is failed");	
		}

		Thread.sleep(2000);
		//positive validations
		simplesearch(user,Testname);  
		Thread.sleep(3000);
		simplesearch(user,Testname); 
		boolean result=UserValidation(user,Testname);
		resultdisplay(result,Testname);
		Thread.sleep(4000);
		simplesearch(userid,Testname);
		Thread.sleep(2000);
		//simplesearch(userid,Testname);
		boolean resultid=UseridValidation(userid,Testname);
		resultdisplay(resultid,Testname);
		Thread.sleep(4000);

		//Negative validations
		simplesearch(user1,Testname);
		Thread.sleep(3000);
		simplesearch(user1,Testname);
		boolean result1=UserValidation(user1,Testname);
		resultdisplay(result1,Testname);
		Thread.sleep(4000);
		simplesearch(user2,Testname);
		Thread.sleep(3000);
		simplesearch(user2,Testname);
		boolean result2=UserValidation(user2,Testname);
		resultdisplay(result2,Testname);
		Thread.sleep(4000);

		UserNameSorting(Testname);

		driver.close();
		driver.switchTo().window(firstWinHandle);
		return true;
	}

	private boolean UserValidation(String user, String Testname) throws Exception {
		String[] Sort1 = new String[50];
		String  row = "tr";
		List<WebElement> DataCnt= driver.findElements(By.xpath("html/body/div[1]/div[4]/div[1]/div/div[1]/div/div[2]/div[3]/table/tbody/tr"));
		if (DataCnt.size()==0){
			log.logLine(Testname, false, "Sorry No data is there to display");

		}
		else{
			if(doesElementExist(properties.getProperty("accountno"), 5)){
				for(int i = 0; i < DataCnt.size(); i++) {
					Sort1[i] = driver.findElement(By.cssSelector("div[id='userGrid'] table tbody "+row+" td+td+td+td span[class='appToolTrigger']")).getText();

					if(Sort1[i].startsWith(user)){
						log.logLine(Testname, false, "The validation passed and the username in "+row+" is "+Sort1[i]);
					}else{
						log.logLine(Testname, false, "The validation failed and the username in "+row+" is "+Sort1[i]);

					}

					row = row + "+tr";
					log.logLine(Testname, false, "Iterating through the Rows....Rows Have the username as "+Sort1[i]);
				}

			}
		}
		return true;


	}

	public boolean cliappvalidation(String ClntName, String AppName,String Testname) throws Exception  {

		boolean CliSelected = false;
		Thread.sleep(1000);


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

	public void switchtohaadmin(String Testname) throws Exception{

		Thread.sleep(6000);
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


		Thread.sleep(30000);

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

		if (doesElementExist2(properties.getProperty("ManageUsers"), 5)) {
			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ManageUsers")));
			for (WebElement lnk:selopts) {
				Thread.sleep(1000);
				if (lnk.getText().equals("Manage Users")) {
					Thread.sleep(1000);
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Clicking on Manage Users..");	
					break;
				}	
			}

		} else {
			log.logLine(Testname, true, "Clicking on Manage Users is failed");
			negativeCase(Testname, firstWinHandle, "", "Click on Manager User is failed");	
			throw new Exception("Clicking on Manage Users is failed");	
		}

	}

	public void outputvalidation(String Testname,boolean proofoutput) throws Exception {
		if(proofoutput==true){
			log.logLine(Testname, false, "Selecting the clientapp is possible as the user has access to this feature with this client");
		}
		else{log.logLine(Testname, false, "Selecting the clientapp is not possible as the user has access to this feature with this client");}
	}


	public boolean Accessmodification(String AccNo, String Testname) throws Exception {
		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
		String ClntName = test.readColumnData("ClientName", sheetname);
		String AppName = test.readColumnData("ApplicationName", sheetname);
		String user = test.readColumnData("username", sheetname);

		//Pivot proof access validation
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


		Thread.sleep(2000);
		clientappvalidation(Testname,ClntName,AppName);
		switchtohaadmin(Testname);
		Thread.sleep(3000);

		//positive validations
		simplesearch(user,Testname);  
		Thread.sleep(10000);
		//simplesearch(user,Testname); 
		UserValidation1(user,Testname);
		Thread.sleep(2000);
		log.logLine(Testname, false, "unchecking pivot-proof access to the user");
		Thread.sleep(1000);
		Toolidentify(Testname,"PIVOT Proof");
		Thread.sleep(2000);
		savebutton(Testname);	
		Thread.sleep(5000);
		driver.close();
		driver.switchTo().window(firstWinHandle);
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);

		driver.switchTo().defaultContent();

		if (doesElementExist2(properties.getProperty("Proofs"), 5)) {	    
			WebElement proofsmnu = driver.findElement(By.cssSelector(properties.getProperty("Proofs")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", proofsmnu);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Navigation to Proofs page successful");
		} else {
			log.logLine(Testname, true, "Clicking on Proofs menu is failed");
			throw new Exception("Clicking on Proofs menu is failed");
		}

		clientappvalidation(Testname,ClntName,AppName);
		Thread.sleep(3000);
		switchtohaadmin(Testname);
		Thread.sleep(3000);
		String use = test.readColumnData("username", sheetname);
		//positive validations
		simplesearch(use,Testname);  
		Thread.sleep(3000);
		//simplesearch(use,Testname); 
		UserValidation1(use,Testname);
		Thread.sleep(10000);	
		log.logLine(Testname, false, "checking pivot-proof access to the user");
		Toolidentify(Testname,"PIVOT Proof");
		Thread.sleep(2000);
		savebutton(Testname);	
		Thread.sleep(5000);
		driver.close();
		driver.switchTo().window(firstWinHandle);
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		driver.switchTo().defaultContent();

		if (doesElementExist2(properties.getProperty("Proofs"), 5)) {	    
			WebElement proofsmnu = driver.findElement(By.cssSelector(properties.getProperty("Proofs")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", proofsmnu);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Navigation to Proofs page successful");
		} else {
			log.logLine(Testname, true, "Clicking on Proofs menu is failed");
			throw new Exception("Clicking on Proofs menu is failed");
		}

		clientappvalidation(Testname,ClntName,AppName);


		//Pivot Audits access validation

		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		WebElement retelm= waitForElement(properties.getProperty("selClint1"));	

		Thread.sleep(2000);

		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button to view the JobTracking");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button to view audit failed");
			throw new Exception("Clicking on Cancel button to view audit failed");
		}
		Thread.sleep(2000);

		if (doesElementExist2(properties.getProperty("Proofs"), 5)) {	    
			WebElement proofsmnu = driver.findElement(By.cssSelector(properties.getProperty("Proofs")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", proofsmnu);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Navigation to Audits page successful");
		} else {
			log.logLine(Testname, true, "Clicking on Audits menu is failed");
			throw new Exception("Clicking on Audits menu is failed");
		}
		Thread.sleep(2000);
		clientappvalidation(Testname,ClntName,AppName);
		switchtohaadmin(Testname);
		Thread.sleep(3000);
		String user1 = test.readColumnData("username", sheetname);
		//positive validations
		simplesearch(user1,Testname);  
		Thread.sleep(3000);
	//	simplesearch(user1,Testname); 
		UserValidation1(user1,Testname);
		Thread.sleep(12000);	
		log.logLine(Testname, false, "unchecking 'Pivot audit' access to the user");
		Toolidentify(Testname,"PIVOT Audit");
		Thread.sleep(2000);
		savebutton(Testname);	
		Thread.sleep(5000);
		driver.close();
		driver.switchTo().window(firstWinHandle);
		Thread.sleep(2000);
		driver.switchTo().defaultContent();

		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button to view the JobTracking");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button to view the JobTracking is failed");			
		}
		Thread.sleep(2000);

		if (doesElementExist2(properties.getProperty("Audits"), 5)) {	    
			WebElement Auditsmnu = driver.findElement(By.cssSelector(properties.getProperty("Audits")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Auditsmnu);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Navigation to Audits page successful");
		} else {
			log.logLine(Testname, true, "Clicking on Audits menu is failed");
			throw new Exception("Clicking on Audits menu is failed");
		}
		Thread.sleep(2000);
		clientappvalidation(Testname,ClntName,AppName);
		switchtohaadmin(Testname);
		Thread.sleep(3000);
		String user3 = test.readColumnData("username", sheetname);
		//positive validations
		simplesearch(user3,Testname);  
		Thread.sleep(3000);
		//simplesearch(user3,Testname); 
		UserValidation1(user3,Testname);
		Thread.sleep(10000);	
		log.logLine(Testname, false, "checking 'Pivot audit' access to the user");
		Toolidentify(Testname,"PIVOT Audit");
		Thread.sleep(2000);
		savebutton(Testname);	
		Thread.sleep(5000);
		driver.close();
		driver.switchTo().window(firstWinHandle);
		Thread.sleep(2000);
		driver.switchTo().defaultContent();

		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button to view the JobTracking");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button to view the JobTracking is failed");	
			throw new Exception("Clicking on Cancel button to view Audit is failed");
		}
		Thread.sleep(2000);

		if (doesElementExist2(properties.getProperty("Audits"), 5)) {	    
			WebElement Auditsmnu = driver.findElement(By.cssSelector(properties.getProperty("Audits")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Auditsmnu);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Navigation to Audits page successful");
		} else {
			log.logLine(Testname, true, "Clicking on Audits menu is failed");
			throw new Exception("Clicking on Audits menu is failed");
		}
		Thread.sleep(2000);
		clientappvalidation(Testname,ClntName,AppName);


		//Job tracking access validation
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		WebElement retele= waitForElement(properties.getProperty("selClint1"));	

		Thread.sleep(2000);

		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button to view the JobTracking");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button to view the JobTracking is failed");		
			throw new Exception("Clicking on Cancel button to view the JobTracking is failed");
		}
		Thread.sleep(2000);

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


		Thread.sleep(2000);
		clientappvalidation(Testname,ClntName,AppName);
		switchtohaadmin(Testname);
		Thread.sleep(3000);
		String user2 = test.readColumnData("username", sheetname);
		//positive validations
		simplesearch(user2,Testname);  
		Thread.sleep(3000);
		//simplesearch(user2,Testname); 
		UserValidation1(user2,Testname);
		Thread.sleep(12000);
		log.logLine(Testname, false, "unchecking 'pivot Jobtracking' access to the user");
		Thread.sleep(2000);
		Toolidentify(Testname,"PIVOT Job Tracking");
		Thread.sleep(2000);
		savebutton(Testname);	
		Thread.sleep(5000);
		driver.close();
		driver.switchTo().window(firstWinHandle);
		Thread.sleep(2000);
		driver.switchTo().defaultContent();

		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button to view the JobTracking");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button to view the JobTracking is failed");
			throw new Exception("Clicking on Cancel button to view the JobTracking is failed");
		}
		Thread.sleep(2000);

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
		Thread.sleep(2000);
		clientappvalidation(Testname,ClntName,AppName);
		switchtohaadmin(Testname);
		Thread.sleep(3000);
		String user4= test.readColumnData("username", sheetname);
		//positive validations
		simplesearch(user4,Testname);  
		Thread.sleep(3000);
		//simplesearch(user4,Testname); 
		UserValidation1(user4,Testname);
		Thread.sleep(10000);
		log.logLine(Testname, false, "checking 'pivot Jobtracking' access to the user");
		Toolidentify(Testname,"PIVOT Job Tracking");
		Thread.sleep(2000);
		savebutton(Testname);	
		Thread.sleep(5000);
		driver.close();
		driver.switchTo().window(firstWinHandle);
		Thread.sleep(2000);
		driver.switchTo().defaultContent();

		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button to view the JobTracking");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button to view the JobTracking is failed");	
			throw new Exception("Clicking on Cancel button to view the JobTracking is failed");
		}
		Thread.sleep(2000);

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
		Thread.sleep(2000);
		clientappvalidation(Testname,ClntName,AppName);

		return true;}

	private void clientappvalidation(String Testname,String ClntName,String AppName)throws Exception {

		boolean CliSelected = false;
		Thread.sleep(1000);


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

		Thread.sleep(3000);
		boolean AppSelected = false;


		if (doesElementExist2(properties.getProperty("selAppl1"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selAppl1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on ApplicationName dropdown..");

			if (doesElementExist2(properties.getProperty("ApplOpts1"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ApplOpts1")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().contains(AppName)) {
						log.logLine(Testname, false, "Appliction name  exists as there is access to this tool");	
						break;
					}
					else{
						log.logLine(Testname, false, "Appliction name validation is successful as "+lnk.getText()+" doesnot match with required app "+AppName);
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
		Thread.sleep(3000);

		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");
		}


	}


	public void savebutton(String Testname)throws Exception {
		if (doesElementExist2(properties.getProperty("savebutton"), 5)) {	    
			WebElement sve = driver.findElement(By.cssSelector(properties.getProperty("savebutton")));
			Highlight(sve);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", sve);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicking on save button after access modification");
		}else {
			log.logLine(Testname, true, "Clicking on save button failed");
			driver.switchTo().window(firstWinHandle);
			throw new Exception("Clicking on save buttonfailed");
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

	public boolean Modifyaccess_simplesearch(String AccNo,String Testname) throws Exception {
		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(1000);
		driver.switchTo().defaultContent();
		WebElement retelm= waitForElement(properties.getProperty("selClint1"));	

		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button ");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button ");			
		}
		Thread.sleep(2000);
		switchtohaadmin(Testname);
		Thread.sleep(3000);
		String use = test.readColumnData("username", sheetname);
		simplesearch(use,Testname);  
		Thread.sleep(3000);
		//simplesearch(use,Testname); 
		UserValidation1(use,Testname);
		Thread.sleep(10000);	
		log.logLine(Testname, false, "Validation of simple search functionality");

		//.......client possitive validation
		log.logLine(Testname, false, "Client validation starts...");
		Accessmodify_ss(Testname,"ABC");
		Thread.sleep(2000);
		clienticon(Testname);
		Thread.sleep(1000);
		searchclick(Testname);
		Thread.sleep(5000);
		clientvalidation(Testname,"ABC");
		Thread.sleep(8000);

		//...........client Negative validation

		Accessmodify_ss(Testname,"ABC1234[ABC1234]");
		Thread.sleep(5000);
		clienticon(Testname);
		Thread.sleep(1000);
		searchclick(Testname);
		Thread.sleep(5000);
		clientvalidation1(Testname,"ABC1234[ABC1234]");
		Thread.sleep(2000);

		//........App possitive validation
		log.logLine(Testname, false, "App validation starts...");
		Accessmodify_ss(Testname,"ABC1234[ABC1234]");
		Thread.sleep(2000);
		appicon(Testname);
		Thread.sleep(1000);
		searchclick(Testname);
		Thread.sleep(5000);
		appvalidation(Testname,"ABC1234[ABC1234]");
		Thread.sleep(2000);

		//...........App Negative validation.

		Accessmodify_ss(Testname,"Pivot Proof");
		Thread.sleep(5000);
		appicon(Testname);
		Thread.sleep(5000);
		searchclick(Testname);
		Thread.sleep(2000);
		appvalidation1(Testname,"Pivot Proof");
		Thread.sleep(2000);

		//............Tool positive validation
		log.logLine(Testname, false, "tool validation starts...");
		Accessmodify_ss(Testname,"Pivot Proof");
		Thread.sleep(5000);
		toolicon(Testname);
		Thread.sleep(5000);
		searchclick(Testname);
		Thread.sleep(5000);
		toolvalidation(Testname,"ABC1234[ABC1234]","Pivot Proof");
		Thread.sleep(2000);

		//..........Tool Negative validation

		Accessmodify_ss(Testname,"ABC");
		Thread.sleep(5000);
		toolicon(Testname);
		Thread.sleep(5000);
		searchclick(Testname);
		Thread.sleep(5000);
		toolvalidation1(Testname,"ABC1234[ABC1234]","Pivot Proof");
		Thread.sleep(8000);

		//..........default switch..........
		log.logLine(Testname, false, "Switching to default icon");
		clienticon(Testname);
		Thread.sleep(8000);
		driver.close();
		driver.switchTo().window(firstWinHandle);
		return true;}


	public void appvalidation(String Testname,String app) throws Exception {	
		String[] Sort = new String[150];
		int length = Sort.length;
		String row="li";
		Thread.sleep(2000);
		if (doesElementExist2(("div[id='appsTreeview'] ul li div span[class='k-icon k-plus']"),5)) {
			WebElement appbtn= driver.findElement(By.cssSelector("div[id='appsTreeview'] ul li div span[class='k-icon k-plus']"));
			Highlight(appbtn);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", appbtn);
		}
		Thread.sleep(1000);

		if (doesElementExist2(("div[id='appsTreeview'] ul li ul[role='group'] li[role='treeitem'] div span[class='k-in'] span"),5)) {
			List<WebElement> DataCnt1= driver.findElements(By.cssSelector("div[id='appsTreeview'] ul li ul[role='group'] li[role='treeitem'] div span[class='k-in'] span"));
			for(int i = 0; i < DataCnt1.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='appsTreeview'] ul li ul[role='group'] "+row+"[role='treeitem'] div span[class='k-in']")).getText();
				if(Sort[i].equalsIgnoreCase(app)){
					log.logLine(Testname, false,  "The app searched "+app+" matches with the one displayed in the result"+Sort[i]);
					break;
				}

				row = row + "+li";

			}

		}else {
			log.logLine(Testname, true, "The App result didn't display/not matched ");
		}}

	public void appvalidation1(String Testname,String app) throws Exception {	
		String[] Sort = new String[150];
		int length = Sort.length;
		String row="li";
		Thread.sleep(2000);
		if (doesElementExist2(("div[id='appsTreeview'] ul li div span[class='k-icon k-plus']"),5)) {
			WebElement appbtn= driver.findElement(By.cssSelector("div[id='appsTreeview'] ul li div span[class='k-icon k-plus']"));
			Highlight(appbtn);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", appbtn);
		}
		Thread.sleep(1000);

		if (doesElementExist2(("div[id='appsTreeview'] ul li ul[role='group'] li[role='treeitem'] div span[class='k-in'] span"),5)) {
			List<WebElement> DataCnt1= driver.findElements(By.cssSelector("div[id='appsTreeview'] ul li ul[role='group'] li[role='treeitem'] div span[class='k-in'] span"));
			for(int i = 0; i < DataCnt1.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='appsTreeview'] ul li ul[role='group'] "+row+"[role='treeitem'] div span[class='k-in']")).getText();
				if(Sort[i].equals(app)){
					log.logLine(Testname, true,  "The app searched "+app+" matches with the one displayed in the result"+Sort[i]);
					break;
				}

				row = row + "+li";

			}

		}else {
			log.logLine(Testname, false, "The App result didn't display/not matched as appname "+ app+"is not a proper app name");
		}}

	public void clientvalidation(String Testname,String client) throws Exception {	
		String[] Sort = new String[150];
		if (doesElementExist2(("div[id='appsTreeview'] ul li div span[class='k-in'] span"),5)) {
			List<WebElement> DataCnt1= driver.findElements(By.cssSelector("div[id='appsTreeview'] ul li div span[class='k-in']"));
			for (WebElement lnk:DataCnt1) {
				String abc=lnk.getText();
				if(lnk.getText().contains(client)){
					log.logLine(Testname, false, "The client searched "+ client +" matches with the one displayed in the result "+lnk.getText());
					break;
				}	
			}}else {
				log.logLine(Testname, true, "The clent result didn't display/not matched ");
			}}

	public void clientvalidation1(String Testname,String client) throws Exception {	
		String[] Sort = new String[150];
		if (doesElementExist2(("div[id='appsTreeview'] ul li div span[class='k-in'] span"),5)) {
			List<WebElement> DataCnt1= driver.findElements(By.cssSelector("div[id='appsTreeview'] ul li div span[class='k-in']"));
			for (WebElement lnk:DataCnt1) {
				String abc=lnk.getText();
				if(lnk.getText().contains(client)){
					log.logLine(Testname, true, "The client searched "+ client +" matches with the one displayed in the result "+lnk.getText());
					break;
				}	
			}}else {
				log.logLine(Testname, false, "The clent result didn't display/not matched since "+client+"is not a client name");
			}}

	public void Accessmodify_ss(String Testname,String data) throws Exception {	
		Thread.sleep(2000);
		if (doesElementExist2(("div[id='btn-searchbox'] input[id='searchBox']"),5)) {
			WebElement txt= driver.findElement(By.cssSelector("div[id='btn-searchbox'] input[id='searchBox']"));
			txt.clear();
			txt.sendKeys(data);
			Thread.sleep(5000);
			
		}

		if (doesElementExist(properties.getProperty("Information"), 5)) {
			WebElement btnsrch = driver.findElement(By.xpath(".//*[@id='icon-switcher-btn']"));
			Actions action = new Actions(driver);
			action.moveToElement(btnsrch).perform(); 
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicking on information button is successful");
		} else {
			log.logLine(Testname, true, "Clicking on information button is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on Choose information is failed");
		}
	}

	public void toolvalidation(String Testname,String app,String tool) throws Exception {
		if (doesElementExist2(("div[id='appsTreeview'] ul li div span[class='k-icon k-plus']"),5)) {
			WebElement appbtn= driver.findElement(By.cssSelector("div[id='appsTreeview'] ul li div span[class='k-icon k-plus']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", appbtn);
			Thread.sleep(5000);
		}

		String[] Sort = new String[150];
		int length = Sort.length;
		String row="li";
		String row1="li";

		if (doesElementExist2(("div[id='appsTreeview'] ul li ul[role='group'] li[role='treeitem'] div span[class='k-in'] span"),5)) {
			List<WebElement> DataCnt1= driver.findElements(By.cssSelector("div[id='appsTreeview'] ul li ul[role='group'] li[role='treeitem'] div span[class='k-in'] span"));
			for(int i = 0; i < DataCnt1.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='appsTreeview'] ul li ul[role='group'] "+row+"[role='treeitem'] div span[class='k-in']")).getText();

				if(Sort[i].equalsIgnoreCase(app)){
					List<WebElement> DataCnt2= driver.findElements(By.cssSelector("div[id='appsTreeview'] ul[role='tree'] li ul "+row+"[role='treeitem'] ul li div span[class='k-in']"));
					String tooltxt  = driver.findElement(By.cssSelector("div[id='appsTreeview'] ul[role='tree'] li ul "+row+"[role='treeitem'] ul li div span[class='k-in']")).getText();
					if(tooltxt.equalsIgnoreCase(tool)){
						Thread.sleep(5000);			
						log.logLine(Testname, false, "The tool searched "+tool+" matches with the one displayed in the outputlist");
						break;
					}
				}row = row + "+li";}}

		else {
			log.logLine(Testname, true, "The Tool result didn't display/ matched ");
		}}

	public void toolvalidation1(String Testname,String app,String tool) throws Exception {
		if (doesElementExist2(("div[id='appsTreeview'] ul li div span[class='k-icon k-plus']"),5)) {
			WebElement appbtn= driver.findElement(By.cssSelector("div[id='appsTreeview'] ul li div span[class='k-icon k-plus']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", appbtn);
		}

		String[] Sort = new String[150];
		int length = Sort.length;
		String row="li";
		String row1="li";

		if (doesElementExist2(("div[id='appsTreeview'] ul li ul[role='group'] li[role='treeitem'] div span[class='k-in'] span"),5)) {
			List<WebElement> DataCnt1= driver.findElements(By.cssSelector("div[id='appsTreeview'] ul li ul[role='group'] li[role='treeitem'] div span[class='k-in'] span"));
			for(int i = 0; i < DataCnt1.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='appsTreeview'] ul li ul[role='group'] "+row+"[role='treeitem'] div span[class='k-in']")).getText();

				if(Sort[i].equalsIgnoreCase(app)){
					List<WebElement> DataCnt2= driver.findElements(By.cssSelector("div[id='appsTreeview'] ul[role='tree'] li ul "+row+"[role='treeitem'] ul li div span[class='k-in']"));
					String tooltxt  = driver.findElement(By.cssSelector("div[id='appsTreeview'] ul[role='tree'] li ul "+row+"[role='treeitem'] ul li div span[class='k-in']")).getText();
					if(tooltxt.equalsIgnoreCase(tool)){
						Thread.sleep(2000);			
						log.logLine(Testname, true, "The tool searched "+tool+" matches with the one displayed in the outputlist");
						break;
					}
				}row = row + "+li";}}

		else {
			log.logLine(Testname, false, "The App result didn't display/not matched as appname "+tool+"is not a proper app name");
		}}

	public void appicon(String Testname) throws Exception {	
		if (doesElementExist2(properties.getProperty("appicon"), 5)) {
			WebElement btn = driver.findElement(By.cssSelector(properties.getProperty("appicon")));		
			Highlight(btn);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
			Thread.sleep(5000);
			String bt=btn.getText();
			log.logLine(Testname, false, "Clicking on "+bt+" is successful");
		} else {		
			negativeCase(Testname, firstWinHandle, "", "Clicking on appicon failed");
			throw new Exception("Clicking on toolicon failed");}				
	}

	public void toolicon(String Testname) throws Exception {
		if (doesElementExist2(properties.getProperty("toolicon"), 5)) {
			WebElement btn = driver.findElement(By.cssSelector(properties.getProperty("toolicon")));			
			Highlight(btn);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
			Thread.sleep(5000);
			String abc=btn .getText();
			log.logLine(Testname, false, "Clicking on "+abc+" is successful");
		}else {		
			negativeCase(Testname, firstWinHandle, "", "Clicking on toolicon failed");
			throw new Exception("Clicking on toolicon failed");}				
	}

	public void clienticon(String Testname) throws Exception {	
		String text;

		if (doesElementExist2(properties.getProperty("clienticon"), 5)) {
			WebElement btn = driver.findElement(By.cssSelector(properties.getProperty("clienticon")));			
			Highlight(btn);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);
			Thread.sleep(5000);
			text=btn.getText();		
			log.logLine(Testname, false, "Clicking on "+text+" is successful");
		} else {		
			negativeCase(Testname, firstWinHandle, "", "Clicking on clienticon failed");	
			throw new Exception("Clicking on clienticon failed");}		

	}

	public void searchclick(String Testname ) throws Exception {	
		if (doesElementExist(properties.getProperty("Searchclick"), 5)) {
			Thread.sleep(4000);
			WebElement btnsrch = driver.findElement(By.xpath(".//*[@id='btnTreeSearch']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			Highlight( btnsrch);
			log.logLine(Testname, false, "Clicking on searchicon is successful");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on searchicon is failed");
			throw new Exception("Clicking on searchicon is failed");}	

	}	


	public void Toolidentify(String Testname,String tool)throws Exception {

		Thread.sleep(5000);
		if (doesElementExist2(("div[id='btn-searchbox'] input[id='searchBox']"),5)) {
			WebElement txt= driver.findElement(By.cssSelector("div[id='btn-searchbox'] input[id='searchBox']"));
			txt.clear();
			txt.sendKeys("ABC1234[ABC1234]");
			Thread.sleep(1000);
		}
		
		Actions action = new Actions(driver);
		if (doesElementExist(properties.getProperty("Information"), 5)) {
			WebElement btnsrch = driver.findElement(By.xpath(".//*[@id='icon-switcher-btn']"));			
			action.moveToElement(btnsrch).perform(); 
			log.logLine(Testname, false, "Clicking on Icon switcher button is successful");
			Thread.sleep(5000);
			if (doesElementExist2(properties.getProperty("appicon"), 5)) {
				WebElement btn = driver.findElement(By.cssSelector(properties.getProperty("appicon")));	
				Highlight(btn);
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", btn);	
				Thread.sleep(5000);
			}
			else if (doesElementExist(properties.getProperty("Applninfo"), 5)) {
				WebElement btn = driver.findElement(By.xpath(properties.getProperty("Applninfo")));			
				action.moveToElement(btn).perform();
				btn.click();
				log.logLine(Testname, false, "Clicking on Application info icon is successful");}
			else {
				log.logLine(Testname, true, "Clicking on Application info icon is failed");
				//driver.switchTo().defaultContent();
			}			
		} else {
			log.logLine(Testname, true, "Clicking on Icon switcher button is failed");
			driver.switchTo().defaultContent();
		}

		Thread.sleep(4000);

		if (doesElementExist(properties.getProperty("Searchclick"), 5)) {

			WebElement btnsrch = driver.findElement(By.xpath(".//*[@id='btnTreeSearch']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicking on Search button is successful");
		} else {
			log.logLine(Testname, true, "Clicking on Search  button is failed");
			driver.switchTo().defaultContent();

		}

		if (doesElementExist2(("div[id='appsTreeview'] ul li div span[class='k-icon k-plus']"),5)) {
			WebElement appbtn= driver.findElement(By.cssSelector("div[id='appsTreeview'] ul li div span[class='k-icon k-plus']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", appbtn);
		}

		String[] Sort = new String[150];
		int length = Sort.length;
		String row="li";
		String row1="li";

		if (doesElementExist2(("div[id='appsTreeview'] ul li ul[role='group'] li[role='treeitem'] div span[class='k-in'] span"),5)) {
			List<WebElement> DataCnt1= driver.findElements(By.cssSelector("div[id='appsTreeview'] ul li ul[role='group'] li[role='treeitem'] div span[class='k-in'] span"));
			for(int i = 0; i < DataCnt1.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='appsTreeview'] ul li ul[role='group'] "+row+"[role='treeitem'] div span[class='k-in']")).getText();

				if(Sort[i].equals("ABC1234[ABC1234]")){

					List<WebElement> DataCnt2= driver.findElements(By.cssSelector("div[id='appsTreeview'] ul[role='tree'] li ul "+row+"[role='treeitem'] ul "+row1+" div span[class='k-in']"));
					for(int j = 0; j < DataCnt2.size(); j++) {
						Sort[j] = driver.findElement(By.cssSelector("div[id='appsTreeview'] ul[role='tree'] li ul "+row+"[role='treeitem'] ul "+row1+" div span[class='k-in']")).getText();
						if(Sort[j].equalsIgnoreCase(tool)){
							Thread.sleep(2000);
							WebElement chk=driver.findElement(By.cssSelector("div[id='appsTreeview'] ul[role='tree'] li ul "+row+"[role='treeitem'] ul "+row1+" div span[class='k-checkbox'] input"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", chk);
							log.logLine(Testname, false, "Clicking on the "+tool+" is successful");
							break;
						}
						row1 = row1 + "+li";
					}

					break;}
				row = row + "+li";

			}

		}

	}



	private boolean UseridValidation(String userid, String Testname) throws InvalidFormatException, IOException {
		String[] Sort1 = new String[50];
		String  row = "tr";
		List<WebElement> DataCnt= driver.findElements(By.xpath("html/body/div[1]/div[4]/div[1]/div/div[1]/div/div[2]/div[3]/table/tbody/tr"));

		if(doesElementExist(properties.getProperty("accountno"), 5)){
			for(int i = 0; i < DataCnt.size(); i++) {
				Sort1[i] = driver.findElement(By.cssSelector("div[id='userGrid'] table tbody "+row+" td+td+td span[class='appToolTrigger']")).getText();

				if(Sort1[i].contains(userid)){
					log.logLine(Testname, false, "The validation passed and the userid in "+row+" is "+Sort1[i]);
				}else{
					log.logLine(Testname, false, "The validation failed and the userid in "+row+" is "+Sort1[i]);

				}

				row = row + "+tr";
				log.logLine(Testname, false, "Iterating through the Rows....Rows Have the username as "+Sort1[i]);
			}

		}
		return true;

	}
	private void resultdisplay(boolean result,String Testname) throws Exception, IOException {
		if (result==true)
			log.logLine(Testname, false, "Username validation is successful");
		else
			log.logLine(Testname, true, "Username validation failed");
	}

	private void UserValidation1(String user, String Testname) throws Exception {
		String[] Sort1 = new String[50];
		String  row = "tr";
		String element = driver.findElement(By.xpath(".//*[@id='userGrid']/div/span[2]")).getText();
	
																  
		if (element.equals("No items to display")){
			log.logLine(Testname, false, "Sorry No data is there to display");

		}
		else{
			if(doesElementExist2(properties.getProperty("header"), 5)){
				List<WebElement> DataCnt = driver.findElements(By.cssSelector(properties.getProperty("header")));
				for(int i = 0; i < DataCnt.size(); i++) {
					Sort1[i] = driver.findElement(By.cssSelector("div[id='userGrid'] table tbody "+row+" td+td+td+td span[class='appToolTrigger']")).getText();

					if(Sort1[i].startsWith(user)){
						log.logLine(Testname, false, "The validation passed and the username in "+row+" is "+Sort1[i]);
						Thread.sleep(1000);

						if (doesElementExist(properties.getProperty("Chooseactn"), 5)) {
							Thread.sleep(3000);
							int j=i+1;
							WebElement btnsrch = driver.findElement(By.xpath(".//*[@id='userGrid']/table/tbody/tr["+j+"]/td[10]/span/span/span[1]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
							Thread.sleep(3000);
							log.logLine(Testname, false, "Clicking on Choose action button is successful");
						} else {
							log.logLine(Testname, true, "Clicking on Choose action button is failed");
							driver.switchTo().defaultContent();
							throw new Exception("Clicking on Choose action button is failed");
						}

						Thread.sleep(5000);
						if(doesElementExist2(properties.getProperty("chooseacticon"), 5)){
							Actions action = new Actions(driver);
							List<WebElement> nonview = driver.findElements(By.cssSelector(properties.getProperty("chooseactlst")));
							for (WebElement lnk:nonview) {
								if (lnk.getText().equalsIgnoreCase("Modify access")){
									action.click(lnk).perform();
									Thread.sleep(5000);
									log.logLine(Testname, false, "Selecting of Modify access under choose action is successful..");
									break;
								}}
						}else{ 
							log.logLine(Testname, true, "Choose action icon doesnot exist");
							driver.switchTo().defaultContent();
							throw new Exception("Choose action icon doesnot exist");}break;}
					else{
						log.logLine(Testname, false, "The validation failed and the username in "+row+" is "+Sort1[i]);}

					row = row + "+tr";
					log.logLine(Testname, false, "Iterating through the Rows....Rows Have the username as "+Sort1[i]);
				}

			}
		}
		return ;
	}



	private void simplesearch(String user,String Testname) throws Exception {
		if (doesElementExist2(properties.getProperty("simplesrchtxt"), 5)) {	    
			WebElement mnguser = driver.findElement(By.cssSelector(properties.getProperty("simplesrchtxt")));
			mnguser.clear();
			mnguser.sendKeys(user);
			log.logLine(Testname, false, "Entering values in userid/column textbox in Groups/users");
			if (doesElementExist2(properties.getProperty("Simplesearchicon"), 10)) {	    
				WebElement srchicon = driver.findElement(By.cssSelector(properties.getProperty("Simplesearchicon")));
				srchicon.click();
				log.logLine(Testname, false, "selecting seach icon passed");

			} else {
				log.logLine(Testname, true, "selecting seach icon failed");
				negativeCase(Testname, firstWinHandle, "", "Click on Manager User is failed");	
				throw new Exception("selecting seach icon failed");
			}	}
		else {
			log.logLine(Testname, true, "Entering values in userid/column textbox in Groups/users  failed");
			negativeCase(Testname, firstWinHandle, "", "Click on Manager User is failed");	
			throw new Exception("Entering values in userid/column textbox in Groups/users failed");
		}	   

	}

	public boolean UserNameSorting(String Testname) throws Exception {


		if (doesElementExist2(properties.getProperty("Simplesearchbtn"), 5)) {	    
			WebElement simplsrcbtn = driver.findElement(By.cssSelector(properties.getProperty("Simplesearchbtn")));
			simplsrcbtn.click();
			simplsrcbtn.clear();
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clear the text");
		} else {
			log.logLine(Testname, true, "Clear the text is failed");
			negativeCase(Testname, firstWinHandle, "", "Clear the text is failed");		
			throw new Exception("Clear the text is failed");	
		}


		if (doesElementExist2(properties.getProperty("Simplesearchicon"), 5)) {	    
			WebElement simplsrcicn = driver.findElement(By.cssSelector(properties.getProperty("Simplesearchicon")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", simplsrcicn);
			Thread.sleep(15000);
			log.logLine(Testname, false, "Click on Search icon");
		} else {
			log.logLine(Testname, true, "Click on Search icon is failed");
			negativeCase(Testname, firstWinHandle, "", "Click on Search icon is failed");		
			throw new Exception("Click on Search icon is failed");	
		}

		if (doesElementExist2(properties.getProperty("Usernameinfo"), 5)) {
			Thread.sleep(3000);
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Usernameinfo")));
			Actions action = new Actions(driver);
			action.moveToElement(btnsrch).perform(); 
			log.logLine(Testname, false, "Mouse over on information icon is successful");
		} else {
			log.logLine(Testname, true, "Mouse over on information icon is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Mouse over on information icon is failed");
		}


		if (doesElementExist2(properties.getProperty("Secretkey"), 5)) {
			List<WebElement> selopts2 = driver.findElements(By.cssSelector(properties.getProperty("Secretkey")));
			for (WebElement lnk:selopts2) {
				Thread.sleep(1000);
				if (lnk.getText().equals("UserKey")) {
					WebElement sekkey=driver.findElement(By.cssSelector(properties.getProperty("Secretkeyvalue")));
					String Value= sekkey.getText();
					String Keyvalue[]=Value.split("\n");
					Thread.sleep(1000);
					String Finalvalue=Keyvalue[0].trim();
					log.logLine(Testname, false, "Reading the User Key and its value as :" +Finalvalue);
					break;
				}else{
					log.logLine(Testname, true, "Reading the User Key and its value is failed");
					negativeCase(Testname, firstWinHandle, "", "Reading the User Key and its value is failed");        
					throw new Exception("Reading the User Key and its value is failed");    
				}
			}
		}

		UsernameAscending(Testname);
		UsernameDescending(Testname);

		UserIDAscending(Testname);
		UserIDDescending(Testname);


		return true;
	}

	public boolean UsernameAscending(String Testname) throws Exception {

		String[] Sort = new String[150];
		int length = Sort.length;
		String row = "tr";

		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("Usernamearrow"), 5)) {
			WebElement eDelive = driver.findElement(By.xpath(properties.getProperty("Usernamearrow")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
			log.logLine(Testname, false, "Click on Arrow button on User Name column is Successful");
		} else {
			log.logLine(Testname, true, "Click on Arrow button on User Name column is failed");
			throw new Exception("Click on Arrow button on User Name column is failed");
		}

		List<WebElement> DataCnt= driver.findElements(By.xpath("//div[@id='userGrid']/table/tbody/tr"));
		if(doesElementExist2(properties.getProperty("Usernamecolumn"), 5)){
			for(int i = 0; i < DataCnt.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='userGrid'] table tbody "+row+" td+td+td+td span[class='appToolTrigger']")).getText();
				row = row + "+tr";
			}
			log.logLine(Testname, false, "Iterating through the Rows..");
		}


		for (int i = 0; i < DataCnt.size()-1 ; i++) {
			if (Sort[i].compareToIgnoreCase(Sort[i+1])<0) {
				log.logLine(Testname, false, "User Name  <<<<"+Sort[i]+">>>>   <<<<"+Sort[i+1]+">>>>  are in Ascending order");

				if(i>=DataCnt.size()-2)
					break;

			}else if (Sort[i].compareToIgnoreCase(Sort[i+1])==0) {
				log.logLine(Testname, false, "User Name  <<<<"+Sort[i]+">>>>   <<<<"+Sort[i+1]+">>>>  are in Same Name");
				if(i>=DataCnt.size()-2)
					break;	

			}else {
				log.logLine(Testname, true, "User Name  <<<<"+Sort[i]+">>>>   <<<<"+Sort[i+1]+">>>>  are not in Ascending order");
				negativeCase(Testname, firstWinHandle, "", "User Names are not in Ascending Order");		
				throw new Exception("User Name  <<<<"+Sort[i]+">>>>   <<<<"+Sort[i+1]+">>>>  are not in Ascending order");

			}
		}


		return true;
	}

	public boolean UsernameDescending(String Testname) throws Exception {

		String[] Sort = new String[150];
		int length = Sort.length;
		String row = "tr";

		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("Usernamearrow"), 5)) {
			WebElement eDelive = driver.findElement(By.xpath(properties.getProperty("Usernamearrow")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
			log.logLine(Testname, false, "Click on Arrow button on Template column is Successful");
		} else {
			log.logLine(Testname, true, "Click on Arrow button on User Name column is failed");
			throw new Exception("Click on Arrow button on User Name column is failed");
		}

		List<WebElement> DataCnt= driver.findElements(By.xpath("//div[@id='userGrid']/table/tbody/tr"));
		if(doesElementExist2(properties.getProperty("Usernamecolumn"), 5)){
			for(int i = 0; i < DataCnt.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='userGrid'] table tbody "+row+" td+td+td+td span[class='appToolTrigger']")).getText();
				row = row + "+tr";
			}
			log.logLine(Testname, false, "Iterating through the Rows..");
		}


		for (int i = 0; i < DataCnt.size()-1 ; i++) {
			if (Sort[i].compareToIgnoreCase(Sort[i+1])>0) {
				log.logLine(Testname, false, "User Name  <<<<"+Sort[i]+">>>>   <<<<"+Sort[i+1]+">>>>  are in Descending order");

				if(i>=DataCnt.size()-2)
					break;

			}else if (Sort[i].compareToIgnoreCase(Sort[i+1])==0) {
				log.logLine(Testname, false, "User Name  <<<<"+Sort[i]+">>>>   <<<<"+Sort[i+1]+">>>>  are in Same Name");
				if(i>=DataCnt.size()-2)
					break;	

			}else {
				log.logLine(Testname, true, "User Name  <<<<"+Sort[i]+">>>>   <<<<"+Sort[i+1]+">>>>  are not in Descending order");
				negativeCase(Testname, firstWinHandle, "", "User Names are not in Descending Order");		
				throw new Exception("User Name  <<<<"+Sort[i]+">>>>   <<<<"+Sort[i+1]+">>>>  are not in Descending order");

			}
		}


		return true;
	}



	public boolean UserIDAscending(String Testname) throws Exception {

		String[] Sort = new String[150];
		int length = Sort.length;
		String row = "tr";

		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("Useridarrow"), 5)) {
			WebElement eDelive = driver.findElement(By.xpath(properties.getProperty("Useridarrow")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
			log.logLine(Testname, false, "Click on Arrow button on User ID column is Successful");
		} else {
			log.logLine(Testname, true, "Click on Arrow button on User ID column is failed");
			throw new Exception("Click on Arrow button on User ID column is failed");
		}

		List<WebElement> DataCnt= driver.findElements(By.xpath("//div[@id='userGrid']/table/tbody/tr"));
		if(doesElementExist2(properties.getProperty("Useridcolumn"), 5)){
			for(int i = 0; i < DataCnt.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='userGrid'] table tbody "+row+" td+td+td")).getText();
				row = row + "+tr";
			}
			log.logLine(Testname, false, "Iterating through the Rows..");
		}


		for (int i = 0; i < DataCnt.size()-1 ; i++) {
			if (Sort[i].compareToIgnoreCase(Sort[i+1])<0) {
				log.logLine(Testname, false, "User ID's  <<<<"+Sort[i]+">>>>   <<<<"+Sort[i+1]+">>>>  are in Ascending order");

				if(i>=DataCnt.size()-2)
					break;

			}else if (Sort[i].compareToIgnoreCase(Sort[i+1])==0) {
				log.logLine(Testname, false, "User ID's  <<<<"+Sort[i]+">>>>   <<<<"+Sort[i+1]+">>>>  are in Same Name");
				if(i>=DataCnt.size()-2)
					break;	

			}else {
				log.logLine(Testname, true, "User Id's  <<<<"+Sort[i]+">>>>   <<<<"+Sort[i+1]+">>>>  are not in Ascending order");
				negativeCase(Testname, firstWinHandle, "", "User ID's are not in Ascending Order");		
				throw new Exception("User Name  <<<<"+Sort[i]+">>>>   <<<<"+Sort[i+1]+">>>>  are not in Ascending order");

			}
		}

		return true;
	}

	public boolean UserIDDescending(String Testname) throws Exception {

		String[] Sort = new String[150];
		int length = Sort.length;
		String row = "tr";

		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("Useridarrow"), 5)) {
			WebElement eDelive = driver.findElement(By.xpath(properties.getProperty("Useridarrow")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", eDelive);	 
			log.logLine(Testname, false, "Click on Arrow button on User ID column is Successful");
		} else {
			log.logLine(Testname, true, "Click on Arrow button on User ID column is failed");
			throw new Exception("Click on Arrow button on User ID column is failed");
		}

		List<WebElement> DataCnt= driver.findElements(By.xpath("//div[@id='userGrid']/table/tbody/tr"));
		if(doesElementExist2(properties.getProperty("Useridcolumn"), 5)){
			for(int i = 0; i < DataCnt.size(); i++) {
				Sort[i] = driver.findElement(By.cssSelector("div[id='userGrid'] table tbody "+row+" td+td+td")).getText();
				row = row + "+tr";
			}
			log.logLine(Testname, false, "Iterating through the Rows..");
		}


		for (int i = 0; i < DataCnt.size()-1 ; i++) {
			if (Sort[i].compareToIgnoreCase(Sort[i+1])>0) {
				log.logLine(Testname, false, "User ID's  <<<<"+Sort[i]+">>>>   <<<<"+Sort[i+1]+">>>>  are in Descending order");

				if(i>=DataCnt.size()-2)
					break;

			}else if (Sort[i].compareToIgnoreCase(Sort[i+1])==0) {
				log.logLine(Testname, false, "User ID's  <<<<"+Sort[i]+">>>>   <<<<"+Sort[i+1]+">>>>  are in Same Name");
				if(i>=DataCnt.size()-2)
					break;	

			}else {
				log.logLine(Testname, true, "User Id's  <<<<"+Sort[i]+">>>>   <<<<"+Sort[i+1]+">>>>  are not in Descending order");
				negativeCase(Testname, firstWinHandle, "", "User Id's are not in Ascending Order");		
				throw new Exception("User Id's  <<<<"+Sort[i]+">>>>   <<<<"+Sort[i+1]+">>>>  are not in Descending order");

			}
		}


		return true;
	}



}



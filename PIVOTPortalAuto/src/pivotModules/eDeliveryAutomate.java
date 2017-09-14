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

import com.opera.core.systems.scope.protos.EcmascriptProtos.EvalResult.Status;

public class eDeliveryAutomate extends Page{

	public eDeliveryAutomate(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}

	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}


	String Actinsuredcode = "";
	String firstWinHandle ;
	String secondWinHandle;
	long GmailstartTime;
	String ClientIDS="ABC Company";
	String App="ABC1234 - ABC1234";
	String Cons="012139698";
	String linktext=null;
	String todaysDate=null;
	String fieldindx=null;
	String status=" ";
	String strI=" ";

	public String eDeliveryLogin(String RandNo, String Testname, String Iter, String ClientID, String AppID) throws Exception {

		InputOutputData test = new InputOutputData();
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");		
		}

		Thread.sleep(1000);
		log.logLine(Testname, false, "Clicking on Admin to select Archive >> test harness");	    


		Datevalidation(Testname);

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

		Thread.sleep(1500);

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

		WebElement retelm = waitForElement(properties.getProperty("ClientID"));
		if (doesElementExist2(properties.getProperty("ClientID"), 5)) {
			WebElement Client = driver.findElement(By.cssSelector(properties.getProperty("ClientID")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Client);

			Thread.sleep(1500);
			//WebElement SelClt = driver.findElement(By.xpath(properties.getProperty("selClient"+Iter)));
			//((JavascriptExecutor) driver).executeScript("arguments[0].click()", SelClt);

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

		if(Iter.equals("2"))
		{
			String consumerId = test.readColumnData("Iter2ConsumerId", sheetname);
			if (doesElementExist2(properties.getProperty("consumerId"), 5)) {
				WebElement consumerid = driver.findElement(By.cssSelector(properties.getProperty("consumerId")));
				consumerid.clear();
				consumerid.sendKeys(consumerId);
				log.logLine(Testname, false, "Consumer ID field is filled with "+consumerId);
			} else {
				negativeCase(Testname, firstWinHandle, "", "Consumer ID field does not exist in application");	    	
			}

		}
		String uid = test.readColumnData("UserID", sheetname);
		if (doesElementExist2(properties.getProperty("UserID"), 5)) {
			WebElement userid = driver.findElement(By.cssSelector(properties.getProperty("UserID")));
			userid.clear();
			userid.sendKeys(uid);
			log.logLine(Testname, false, "UserID field is filled with "+uid);
		} else {
			negativeCase(Testname, firstWinHandle, "", "UserID field does not exist in application");	    	
		}		


		if (doesElementExist2(properties.getProperty("DocType"), 5)) {	    
			WebElement Client = driver.findElement(By.cssSelector(properties.getProperty("DocType")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Client);

			Thread.sleep(1500);
			//WebElement SelClt = driver.findElement(By.xpath(properties.getProperty("SelDoc"+Iter)));
			//((JavascriptExecutor) driver).executeScript("arguments[0].click()", SelClt);

			if (doesElementExist2(properties.getProperty("AppIDSel"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("AppIDSel")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().contains(AppID)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Application as "+ClientID +" from the popup..");						
						break;
					}				
				}

			} else {
				negativeCase(Testname, firstWinHandle, "", "Selecting the Application ID failed");				
			}


		} else {
			negativeCase(Testname, firstWinHandle, "", "Document type field does not exist in application");			
		}


		if (!Iter.equals("2")) {
			String cid = test.readColumnData("ConsumerID", sheetname);
			if (doesElementExist2(properties.getProperty("ConsumerID"), 5)) {	    
				WebElement doctype = driver.findElement(By.cssSelector(properties.getProperty("ConsumerID")));
				doctype.clear();
				doctype.sendKeys(cid);
				log.logLine(Testname, false, "ConsumerID is filled with "+cid);
			} else {
				negativeCase(Testname, firstWinHandle, "", "ConsumerID field does not exist in application");
			}


			if (doesElementExist2(properties.getProperty("RequestID"), 5)) {	    
				WebElement reqid = driver.findElement(By.cssSelector(properties.getProperty("RequestID")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", reqid);

				Thread.sleep(1500);
				WebElement Selreqid = driver.findElement(By.cssSelector(properties.getProperty("SelReqID")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", Selreqid);

				log.logLine(Testname, false, "RequestType is filled");
			} else {
				log.logLine(Testname, true, "RequestType field does not exist in application");			
				//throw new Exception("RequestType field does not exist in application");
			}
		}

		if (doesElementExist2(properties.getProperty("Submitbtn"), 5)) {	    
			WebElement Client = driver.findElement(By.cssSelector(properties.getProperty("Submitbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Client);			

			Thread.sleep(4000);			
			log.logLine(Testname, false, "Clicked on Submit button");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Submit button does not exist in application");			
		}


		//Wait till the home page shows up
		WebElement retelm2 = waitForElement(properties.getProperty("ActeDeliverLink"));
		if (doesElementExist2(properties.getProperty("ActeDeliverLink"), 5)) {	    

			String actlink = driver.findElement(By.cssSelector(properties.getProperty("ActeDeliverLink"))).getText();			
			driver.get(actlink);
			Thread.sleep(30000);

			log.logLine(Testname, false, "Clicked on Link to open actual eDelivery page");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Click on Link to open actual eDelivery page is failed");			
		}


		//Verify User permission
		if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSUPER")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSITE")) 
				|| (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDCLIENT")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDUSER"))) {					

			if (!(doesElementExist(properties.getProperty("AuthFailurePge"), 5))) {						
				log.logLine(Testname, false, "Permission Verified: RRD Super, RRD Site, RRD Client & RRD User's have access to Test Harness site");

			} else {

				log.logLine(Testname, true, "Access denied - RRD Super, RRD Site, RRD Client & RRD User's does not have access to Test Harness site");				
				return null;
			}
		} else if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTADMIN")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTUSER"))) {

			if (!(doesElementExist(properties.getProperty("AuthFailurePge"), 5))) {

				log.logLine(Testname, true, "Client Admin & Client User should not have access to Test Harness site");				
				throw new Exception("Client Admin & Client User should not have access to Test Harness site");

			} else {

				log.logLine(Testname, false, "Permission Verified: Client Admin & Client User does not have permission to access to Test Harness site");				
				return null; 
			}

		}


		WebElement retelm3 = waitForElement(properties.getProperty("ShowHidesrch"));
		if(retelm3 != null) {
			log.logLine(Testname, false, "Navigation to eDelivery home is successful");
		} else {
			log.logLine(Testname, true, "Navigation to eDelivery home is unsuccessful");		
		}


		return firstWinHandle;
	}


	public boolean SearchDocuments(String  RandNo, String Testname, String Iter) throws Exception {


		if (Iter.equals("2")) {
			MultipleSearch(Testname, "Pet Name", "Equals", "", "", "Niko", "", "1", "12");

			if (doesElementExist2(properties.getProperty("InsuredCode"), 5)) {				
				Actinsuredcode = driver.findElement(By.cssSelector(properties.getProperty("InsuredCode"))).getText();							
				log.logLine(Testname, false, "Insured Code is captured from the search");

				/*if ((Actinsuredcode.equals("")) || (Actinsuredcode.equals(null))) {
					log.logLine(Testname, true, "Policy Number Search Returned 0 results Hence Insured Code search skipped");
					return false;
				}else {
					MultipleSearch(Testname, "Insured Code", "Equals", "", "", Actinsuredcode, "", "1", "1");
				}*/
			} else {
				log.logLine(Testname, false, "Insured Code is empty");
				//return false;
			}


		} else {

			//WebElement retelm = waitForElement(properties.getProperty("ShowHidesrch"));
			if (doesElementExist2(properties.getProperty("ShowHidesrch"), 5)) {	    
				WebElement showlnk = driver.findElement(By.cssSelector(properties.getProperty("ShowHidesrch")));
				if (showlnk.getText().equals("Show Search")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", showlnk);
					log.logLine(Testname, false, "Show search link is clicked");
				} else {
					log.logLine(Testname, false, "Show search link is already Clicked");
				}			
			} else {
				log.logLine(Testname, true, "Clicking on Show Search link is failed");			
				//throw new Exception("Clicking on Show Search link is failed");
			}


			if (doesElementExist2(properties.getProperty("showmorefewopts"), 5)) {	    
				WebElement showlnk = driver.findElement(By.cssSelector(properties.getProperty("showmorefewopts")));
				if (showlnk.getText().equals("Show More Search Options")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", showlnk);
					log.logLine(Testname, false, "Show more criteria link is clicked");
				} else {
					log.logLine(Testname, false, "Show more criteria link is already clicked");
				}
			} else {
				log.logLine(Testname, true, "Clicking on Show more criteria link is failed");			
				//throw new Exception("Clicking on Show more criteria link is failed");
			}


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

				((JavascriptExecutor) driver).executeScript("arguments[0].click()", addlnk);
				log.logLine(Testname, false, "Sixth time clicked on '+' sign to add extra search option");

				((JavascriptExecutor) driver).executeScript("arguments[0].click()", addlnk);
				log.logLine(Testname, false, "Seventh time clicked on '+' sign to add extra search option");

			} else {
				negativeCase(Testname, firstWinHandle, "", "Clicking on extra field add is failed");				
			}


			log.logLine(Testname, false, "Clicking on Extra field remove..");
			if (doesElementExist2(properties.getProperty("CancelBtn"), 5)) {	    
				WebElement removelnk = driver.findElement(By.cssSelector(properties.getProperty("CancelBtn")));						
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", removelnk);
				log.logLine(Testname, false, "First time clicked on '-' sign to add extra search option");			
			} else {
				negativeCase(Testname, firstWinHandle, "", "Clicking on extra field remove1 is failed");				
			}


			if (doesElementExist2(properties.getProperty("CancelBtn"), 5)) {	    
				WebElement removelnk = driver.findElement(By.cssSelector(properties.getProperty("CancelBtn")));						
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", removelnk);
				log.logLine(Testname, false, "Second time clicked on '-' sign to add extra search option");			
			} else {
				negativeCase(Testname, firstWinHandle, "", "Clicking on extra field remove2 is failed");				
			}


			if (doesElementExist2(properties.getProperty("CancelBtn"), 5)) {	    
				WebElement removelnk = driver.findElement(By.cssSelector(properties.getProperty("CancelBtn")));						
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", removelnk);
				log.logLine(Testname, false, "Third time clicked on '-' sign to add extra search option");			
			} else {
				negativeCase(Testname, firstWinHandle, "", "Clicking on extra field remove3 is failed");				
			}


			if (doesElementExist2(properties.getProperty("CancelBtn"), 5)) {	    
				WebElement removelnk = driver.findElement(By.cssSelector(properties.getProperty("CancelBtn")));						
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", removelnk);
				log.logLine(Testname, false, "Third time clicked on '-' sign to add extra search option");			
			} else {
				negativeCase(Testname, firstWinHandle, "", "Clicking on extra field remove4 is failed");				
			}


			if (doesElementExist2(properties.getProperty("CancelBtn"), 5)) {	    
				WebElement removelnk = driver.findElement(By.cssSelector(properties.getProperty("CancelBtn")));						
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", removelnk);
				log.logLine(Testname, false, "Fourth time clicked on '-' sign to add extra search option");			
			} else {
				negativeCase(Testname, firstWinHandle, "", "Clicking on extra field remove5 is failed");				
			}


			if (doesElementExist2(properties.getProperty("CancelBtn"), 5)) {	    
				WebElement removelnk = driver.findElement(By.cssSelector(properties.getProperty("CancelBtn")));						
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", removelnk);
				log.logLine(Testname, false, "Fifth time clicked on '-' sign to add extra search option");			
			} else {
				negativeCase(Testname, firstWinHandle, "", "Clicking on extra field remove6 is failed");				
			}


			if (doesElementExist2(properties.getProperty("CancelBtn"), 5)) {	    
				WebElement removelnk = driver.findElement(By.cssSelector(properties.getProperty("CancelBtn")));						
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", removelnk);
				log.logLine(Testname, false, "Sixth time clicked on '-' sign to add extra search option");			
			} else {
				negativeCase(Testname, firstWinHandle, "", "Clicking on extra field remove7 is failed");				
			}


			if (doesElementExist2(properties.getProperty("showmorefewopts"), 5)) {	    
				WebElement showlnk = driver.findElement(By.cssSelector(properties.getProperty("showmorefewopts")));
				if (showlnk.getText().equals("Show Fewer Search Options")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", showlnk);
					log.logLine(Testname, false, "Show fewer search options link is clicked");
				} else {
					log.logLine(Testname, false, "Show fewer search options link is already clicked");
				}
			} else {
				negativeCase(Testname, firstWinHandle, "", "Clicking on Show fewer search options link is failed");
			}


			if (doesElementExist2(properties.getProperty("ShowHidesrch"), 5)) {	    
				WebElement showlnk = driver.findElement(By.cssSelector(properties.getProperty("ShowHidesrch")));			
				if (showlnk.getText().equals("Hide Search")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", showlnk);
					log.logLine(Testname, false, "Hide search link is clicked");
				} else {
					log.logLine(Testname, false, "Hide search link is already Clicked");
				}
			} else {
				negativeCase(Testname, firstWinHandle, "", "Clicking on Hide Search link is failed");				
			}


			if (doesElementExist2(properties.getProperty("ShowHidesrch"), 5)) {	    
				WebElement showlnk = driver.findElement(By.cssSelector(properties.getProperty("ShowHidesrch")));
				if (showlnk.getText().equals("Show Search")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", showlnk);
					log.logLine(Testname, false, "Show search link is clicked");
				} else {
					log.logLine(Testname, false, "Show search link is already Clicked");
				}			
			} else {
				negativeCase(Testname, firstWinHandle, "", "Clicking on Show Search link is failed");				
			}


			if (doesElementExist2(properties.getProperty("showmorefewopts"), 5)) {	    
				WebElement showlnk = driver.findElement(By.cssSelector(properties.getProperty("showmorefewopts")));
				if (showlnk.getText().equals("Show More Search Options")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", showlnk);
					log.logLine(Testname, false, "Show more criteria link is clicked");
				} else {
					log.logLine(Testname, false, "Show more criteria link is already clicked");
				}
			} else {
				negativeCase(Testname, firstWinHandle, "", "Clicking on Show more criteria link is failed");		    	
			}		



			MultipleSearch(Testname, "Zip Code", "Equals", "Last Name", "Starts with (wildcard)", "1", "T", "2", "4");

			MultipleSearch(Testname, "Plan #", "Starts with (wildcard)", "", "", "59", "", "1", "49");

			MultipleSearch(Testname, "Last Name", "< (less than)", "", "", "PDF", "", "1", "21");

			MultipleSearch(Testname, "First Name", "Starts with (wildcard)", "", "", "012", "", "1", "37");

			MultipleSearch(Testname, "Plan #", "Starts with (wildcard)", "", "", "05", "", "1", "10");

			MultipleSearch(Testname, "First Name", "> (greater than)", "Last Name", "Multiple (comma-separated)", "5", "ABC, FOX", "2", "11");

			MultipleSearch(Testname, "Plan Type", "< (less than)", "", "", "Searches", "", "1", "59");

			//Documents per page
			if (doesElementExist2(properties.getProperty("perPage"), 5)) {	    
				WebElement Ppage = driver.findElement(By.cssSelector(properties.getProperty("perPage")));

				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Ppage);
				Thread.sleep(1000);
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", Ppage);

				Thread.sleep(500);
				List<WebElement> SelPage = driver.findElements(By.cssSelector(properties.getProperty("SelperPage")));

				for (WebElement Each:SelPage) {
					if (Each.getText().equals("50")) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", Each);
					}
				}


				log.logLine(Testname, false, "Selected per page count as 50");
			} else {
				negativeCase(Testname, firstWinHandle, "", "Per page 50 field does not exist in application");		    	
			}


			WebElement retelm3 = waitForElement(properties.getProperty("CntReport"));
			Thread.sleep(1000);

			if (doesElementExist2(properties.getProperty("CntReport"), 5)) {
				String Textcnt = retelm3.getText();

				if (Textcnt.trim().equals("No documents found")) {
					log.logLine(Testname, true, "No documents found for the specified search");				
				} else {			

					String[] Arr = Textcnt.split("of");
					String[] SubArr = Arr[0].trim().split("-");

					if(SubArr[1].trim().equals("50")) {
						log.logLine(Testname, false, "Displayed documents count per page after the change is successful");
					} else {
						log.logLine(Testname, true, "Displayed documents count per page after the change is unsuccessful");			
					}
				}
			}
		}

		return true;

	}


	public boolean DeliveryOptions(String Testname, String Online) throws Exception {

		InputOutputData test = new InputOutputData();
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		if (doesElementExist2(properties.getProperty("DelioptionsMnu"), 5)) {	    
			WebElement delmnu = driver.findElement(By.cssSelector(properties.getProperty("DelioptionsMnu")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", delmnu);
			log.logLine(Testname, false, "Clicked on Delivery options menu");
			Thread.sleep(5500);
		} else {
			negativeCase(Testname, firstWinHandle, "", "Click on Delivery options menu is failed");			
		}


		if (doesElementExist2(properties.getProperty("Cancelbtn"), 5)) {	    
			WebElement pradiobtn = driver.findElement(By.cssSelector(properties.getProperty("Cancelbtn")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", pradiobtn);
			log.logLine(Testname, false, "Clickin on Cancel button");
			Thread.sleep(8500);	    			
		}		


		if (Online.equalsIgnoreCase("online")) {
			/*
			if (doesElementExist2(properties.getProperty("savebtn"), 5)) {	    
				WebElement sbtn = driver.findElement(By.cssSelector(properties.getProperty("savebtn")));						
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", sbtn);
				log.logLine(Testname, false, "Click on Save button");
				Thread.sleep(5000);
		    } else {
				log.logLine(Testname, true, "Clicking on Save button is failed");			
			}
			 */

			if (doesElementExist2(properties.getProperty("RadOnline1"), 5)) {
				WebElement pradiobtn = driver.findElement(By.cssSelector(properties.getProperty("RadOnline1")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", pradiobtn);
				log.logLine(Testname, false, "Radio Button1 Go On-line and Save the Planet only is selected");
				Thread.sleep(500);

			}else if (doesElementExist2(properties.getProperty("RadSkyblue1"), 5)) {
				WebElement pradiobtn = driver.findElement(By.cssSelector(properties.getProperty("RadSkyblue1")));				
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", pradiobtn);
				log.logLine(Testname, false, "Sky blue Radio1 Button Go On-line and Save the Planet is selected");
				Thread.sleep(500);
			}

			if (doesElementExist2(properties.getProperty("RadOnline2"), 5)) {
				WebElement pradiobtn = driver.findElement(By.cssSelector(properties.getProperty("RadOnline2")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", pradiobtn);
				log.logLine(Testname, false, "Radio Button2 Go On-line and Save the Planet only is selected");
				Thread.sleep(500);

			}else if (doesElementExist2(properties.getProperty("RadSkyblue2"), 5)) {
				WebElement pradiobtn = driver.findElement(By.cssSelector(properties.getProperty("RadSkyblue2")));				
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", pradiobtn);
				log.logLine(Testname, false, "Sky blue Radio Button2 Go On-line and Save the Planet is selected");
				Thread.sleep(500);
			}


			if (doesElementExist2(properties.getProperty("agreeChkbx"), 5)) {	    
				WebElement chkboxagre = driver.findElement(By.cssSelector(properties.getProperty("agreeChkbx")));						
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxagre);
				log.logLine(Testname, false, "Agree terms and conditions clicked");
				Thread.sleep(500);
			} else {
				log.logLine(Testname, true, "Agree terms and conditions check box select is failed");			
			}


			String emailid = test.readColumnData("EmailID", sheetname);
			if (doesElementExist2(properties.getProperty("EmailFld"), 5)) {	    
				WebElement email = driver.findElement(By.cssSelector(properties.getProperty("EmailFld")));
				email.clear();
				email.sendKeys(emailid);
				log.logLine(Testname, false, "Email id entered into field :"+emailid);
				Thread.sleep(500);
			} else {
				log.logLine(Testname, true, "Entering the email is failed");			
			}


			if (doesElementExist2(properties.getProperty("BtnSavMail"), 5)) {	    
				WebElement sbtn = driver.findElement(By.cssSelector(properties.getProperty("BtnSavMail")));						
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", sbtn);
				log.logLine(Testname, false, "Click on SaveMail button");
				Thread.sleep(4500);
			} else {
				log.logLine(Testname, true, "Clicking on SaveMail button is failed");			
			}


			if (doesElementExist2(properties.getProperty("savebtn"), 5)) {	    
				WebElement sbtn = driver.findElement(By.cssSelector(properties.getProperty("savebtn")));						
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", sbtn);
				log.logLine(Testname, false, "Click on Save button");
				Thread.sleep(4500);
			} else {
				log.logLine(Testname, true, "Clicking on Save button is failed");			
			}

			Thread.sleep(10000);
			if (doesElementExist2(properties.getProperty("LabelConfrm"), 5)) {	    
				WebElement sbtn = driver.findElement(By.cssSelector(properties.getProperty("LabelConfrm")));
				//if ((sbtn.getText().equals("Your changes have been successfully applied to your profile.")) || 
				//(sbtn.getText().contains("An email has been sent to the email address provided."))) {

				if (sbtn.getText().contains("An email has been sent to the email address provided.")) {					

					log.logLine(Testname, false, "Label confirmation is successful");				

					//capture time 
					GmailstartTime = System.currentTimeMillis();	


					//Call gmail login verification
					GmailVerification(Testname);				

				}else{
					log.logLine(Testname, true, "Label confirmation has different message");			
					//negativeCase(Testname, firstWinHandle, "", "Update Label confirmation is Unsuccessful");					
				}

			} else {
				negativeCase(Testname, firstWinHandle, "", "Label confirmation is not displayed");				
			}		



		}else { 

			if (doesElementExist2(properties.getProperty("printMail"), 5)) {	    
				WebElement pradiobtn = driver.findElement(By.cssSelector(properties.getProperty("printMail")));						
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", pradiobtn);
				log.logLine(Testname, false, "Print and Mail radio button selected");
				Thread.sleep(500);
			} else {
				log.logLine(Testname, true, "Print and Mail radio button select is failed");			
			}

			if (doesElementExist2(properties.getProperty("agreeChkbx"), 5)) {	    
				WebElement chkboxagre = driver.findElement(By.cssSelector(properties.getProperty("agreeChkbx")));						
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxagre);
				log.logLine(Testname, false, "Agree terms and conditions clicked");
				Thread.sleep(500);
			} else {
				log.logLine(Testname, true, "Agree terms and conditions check box select is failed");			
			}		


			if (doesElementExist2(properties.getProperty("savebtn"), 5)) {	    
				WebElement sbtn = driver.findElement(By.cssSelector(properties.getProperty("savebtn")));						
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", sbtn);
				log.logLine(Testname, false, "Click on Save button");
				Thread.sleep(9500);
			} else {
				log.logLine(Testname, true, "Clicking on Save button is failed");			
			}

			Thread.sleep(10000);
			if (doesElementExist2(properties.getProperty("LabelConfrm"), 5)) {	    
				WebElement sbtn = driver.findElement(By.cssSelector(properties.getProperty("LabelConfrm")));
				if (sbtn.getText().equals("Your changes have been successfully applied to your profile.")) {
					log.logLine(Testname, false, "Label confirmation is successful");
				}else{
					log.logLine(Testname, true, "Label confirmation has different message");			
					//	negativeCase(Testname, firstWinHandle, "", "Update Label confirmation is Unsuccessful");					
				}

			} else {
				log.logLine(Testname, true, "Label confirmation is not displayed");			
			}


			//On-line only option
			if (doesElementExist2(properties.getProperty("Cancelbtn"), 5)) {	    
				WebElement pradiobtn = driver.findElement(By.cssSelector(properties.getProperty("Cancelbtn")));						
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", pradiobtn);
				log.logLine(Testname, false, "Clickin on Cancel button");
				Thread.sleep(8500);	    			
			}


			if (doesElementExist2(properties.getProperty("onlineonly"), 5)) {	    
				WebElement pradiobtn = driver.findElement(By.cssSelector(properties.getProperty("onlineonly")));						
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", pradiobtn);
				log.logLine(Testname, false, "On-line only radio button selected");
				Thread.sleep(500);
			} else {
				log.logLine(Testname, true, "On-line only radio button select is failed");			
			}

			if (doesElementExist2(properties.getProperty("agreeChkbx"), 5)) {	    
				WebElement chkboxagre = driver.findElement(By.cssSelector(properties.getProperty("agreeChkbx")));						
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxagre);
				log.logLine(Testname, false, "Agree terms and conditions clicked");
				Thread.sleep(500);
			} else {
				log.logLine(Testname, true, "Agree terms and conditions check box select is failed");			
			}


			String emailid = test.readColumnData("EmailID", sheetname);
			if (doesElementExist2(properties.getProperty("EmailFld"), 5)) {	    
				WebElement email = driver.findElement(By.cssSelector(properties.getProperty("EmailFld")));
				email.clear();
				email.sendKeys(emailid);
				log.logLine(Testname, false, "Email id entered into field :"+emailid);
				Thread.sleep(500);
			} else {
				log.logLine(Testname, true, "Entering the email is failed");			
			}

			if (doesElementExist2(properties.getProperty("saveEmailBtn"), 5)) {	    
				WebElement sbtn = driver.findElement(By.cssSelector(properties.getProperty("saveEmailBtn")));						
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", sbtn);
				log.logLine(Testname, false, "Click on Save Email button");
				Thread.sleep(9500);
			} else {
				log.logLine(Testname, true, "Clicking on Save Email button is failed");			
			}	

			Thread.sleep(1000);

			if (doesElementExist2(properties.getProperty("agreeChkbx"), 5)) {	    
				WebElement chkboxagre = driver.findElement(By.cssSelector(properties.getProperty("agreeChkbx")));						
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxagre);
				log.logLine(Testname, false, "Agree terms and conditions clicked");
				Thread.sleep(500);
			} else {
				log.logLine(Testname, true, "Agree terms and conditions check box select is failed");			
			}	

			Thread.sleep(5000);
			if (doesElementExist2(properties.getProperty("savebtn"), 5)) {	    
				WebElement sbtn = driver.findElement(By.cssSelector(properties.getProperty("savebtn")));						
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", sbtn);
				log.logLine(Testname, false, "Click on Save button");
				Thread.sleep(9500);
			} else {
				log.logLine(Testname, true, "Clicking on Save button is failed");			
			}

			Thread.sleep(2000);
			if (doesElementExist2(properties.getProperty("LabelConfrm"), 5)) {	    
				WebElement sbtn = driver.findElement(By.cssSelector(properties.getProperty("LabelConfrm")));
				if ((sbtn.getText().equals("Your changes have been successfully applied to your profile.")) || 
						(sbtn.getText().contains("An email has been sent to the email address provided."))) {
					log.logLine(Testname, false, "Label confirmation is successful");
				}else{
					log.logLine(Testname, true, "Label confirmation has different message");			
					//					negativeCase(Testname, firstWinHandle, "", "Update Label confirmation is Unsuccessful");					
				}

			} else {
				negativeCase(Testname, firstWinHandle, "", "Label confirmation is not displayed");
			}

		}

		return true;

	}


	public boolean ProfilePage(String Testname, String RandNo) throws Exception {

		if (doesElementExist2(properties.getProperty("ProfilesMnu"), 5)) {	    
			WebElement delmnu = driver.findElement(By.cssSelector(properties.getProperty("ProfilesMnu")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", delmnu);
			log.logLine(Testname, false, "Clicked on Profile Page menu");
			Thread.sleep(4500);
		} else {
			negativeCase(Testname, firstWinHandle, "", "Click on Profile Page menu is failed");			
		}


		if (doesElementExist2(properties.getProperty("FirstNamPro"), 5)) {	    
			WebElement frstName = driver.findElement(By.cssSelector(properties.getProperty("FirstNamPro")));						
			frstName.clear();
			frstName.sendKeys("Test");
			log.logLine(Testname, false, "Entering the first name in profiles page");
			Thread.sleep(500);
		} else {
			log.logLine(Testname, true, "Entering the first name in profiles page is failed");			
		}

		if (doesElementExist2(properties.getProperty("LastNamPro"), 5)) {	    
			WebElement lastName = driver.findElement(By.cssSelector(properties.getProperty("LastNamPro")));						
			lastName.clear();
			lastName.sendKeys("eDelivery_"+RandNo);
			log.logLine(Testname, false, "Entering the lastName in profiles page");
			Thread.sleep(500);
		} else {
			log.logLine(Testname, true, "Entering the lastName in profiles page is failed");			
		}

		if (doesElementExist2(properties.getProperty("PasswdPro"), 5)) {	    
			WebElement passwd = driver.findElement(By.cssSelector(properties.getProperty("PasswdPro")));						
			passwd.clear();
			passwd.sendKeys("Test123");
			log.logLine(Testname, false, "Entering the Password in profiles page");
			Thread.sleep(500);
		} else {
			log.logLine(Testname, true, "Entering the Password in profiles page is failed");			
		}

		if (doesElementExist2(properties.getProperty("ConfmPasswd"), 5)) {	    
			WebElement Confmpasswd = driver.findElement(By.cssSelector(properties.getProperty("ConfmPasswd")));						
			Confmpasswd.clear();
			Confmpasswd.sendKeys("Test123");
			log.logLine(Testname, false, "Entering the Confirm Password in profiles page");
			Thread.sleep(500);
		} else {
			log.logLine(Testname, true, "Entering the Confirm Password in profiles page is failed");			
		}


		if (doesElementExist2(properties.getProperty("ProfileSavbtn"), 5)) {	    
			WebElement savebtn = driver.findElement(By.cssSelector(properties.getProperty("ProfileSavbtn")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", savebtn);
			log.logLine(Testname, false, "Clicked on Save button");
			Thread.sleep(4500);
		} else {
			log.logLine(Testname, true, "Click on Save button is failed");			
		}

		if (doesElementExist2(properties.getProperty("ProfileLabelConfirm"), 5)) {	    
			WebElement sbtn = driver.findElement(By.cssSelector(properties.getProperty("ProfileLabelConfirm")));
			if (sbtn.getText().equals("Profile Information Updated Successfully!")) {
				log.logLine(Testname, false, "Profile Information Updated Successfully!");
			}else{
				log.logLine(Testname, true, "Profile Information Updated Unsuccessfully!");
			}

		} else {
			log.logLine(Testname, true, "Label confirmation is not displayed");			
		}


		return true;		
	}


	public boolean UserConsent(String Testname) throws Exception {

		if (Actinsuredcode.equals(null)){
			log.logLine(Testname, true, "Insured Code is empty hence returned");
			return false;
		}
		Thread.sleep(3000);
		//WebElement retelm3 = waitForElement(properties.getProperty("UserConsentMnu"));
		if (doesElementExist2(properties.getProperty("UserConsentMnu"), 5)) {	    
			WebElement delmnu = driver.findElement(By.cssSelector(properties.getProperty("UserConsentMnu")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", delmnu);
			log.logLine(Testname, false, "Clicked on User Consent Page menu");
			Thread.sleep(2500);
		}/*else if (doesElementExist2(properties.getProperty("UserConsentMnu"), 5)) {	    
			WebElement delmnu1 = driver.findElement(By.cssSelector(properties.getProperty("UserConsentMnu1")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", delmnu1);
			log.logLine(Testname, false, "Clicked on User Consent Page menu");
			Thread.sleep(2500);
		}*/
		else {
			negativeCase(Testname, firstWinHandle, "", "Clicked on User Consent Page menu is failed");			
		}


		WebElement retelm13 = waitForElement(properties.getProperty("SearchFld"));		
		if (doesElementExist2(properties.getProperty("SearchFld"), 5)) {	    
			WebElement frstName = driver.findElement(By.cssSelector(properties.getProperty("SearchFld")));						
			frstName.clear();
			frstName.sendKeys(Actinsuredcode);
			log.logLine(Testname, false, "Entering the insured code "+Actinsuredcode);
			Thread.sleep(500);
		} else {
			log.logLine(Testname, true, "Entering the first name in profiles page is failed");			
		}

		if (doesElementExist2(properties.getProperty("SearchbtnCons"), 5)) {	    
			WebElement pradiobtn = driver.findElement(By.cssSelector(properties.getProperty("SearchbtnCons")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", pradiobtn);
			log.logLine(Testname, false, "Clicked on Search button in Consent page");
			Thread.sleep(3500);
		} else {
			log.logLine(Testname, true, "Clicked on Search button in Consent page is failed");			
		}


		if (doesElementExist2(properties.getProperty("printMail"), 5)) {	    
			WebElement pradiobtn = driver.findElement(By.cssSelector(properties.getProperty("printMail")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", pradiobtn);
			log.logLine(Testname, false, "Print and Mail radio button selected");
			Thread.sleep(500);
		} else {
			log.logLine(Testname, true, "Print and Mail radio button select is failed");			
		}

		if (doesElementExist2(properties.getProperty("agreeChkbx"), 5)) {	    
			WebElement chkboxagre = driver.findElement(By.cssSelector(properties.getProperty("agreeChkbx")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxagre);
			log.logLine(Testname, false, "Agree terms and conditions clicked");
			Thread.sleep(500);
		} else {
			log.logLine(Testname, true, "Agree terms and conditions check box select is failed");			
		}


		if (doesElementExist2(properties.getProperty("savebtn"), 5)) {	    
			WebElement sbtn = driver.findElement(By.cssSelector(properties.getProperty("savebtn")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", sbtn);
			log.logLine(Testname, false, "Click on Save button");
			Thread.sleep(9500);
		} else {
			log.logLine(Testname, true, "Clicking on Save button is failed");			
		}

		if (doesElementExist2(properties.getProperty("LabelConfrm"), 5)) {	    
			WebElement sbtn = driver.findElement(By.cssSelector(properties.getProperty("LabelConfrm")));
			if (sbtn.getText().equals("Your changes have been successfully applied to your profile.")) {
				log.logLine(Testname, false, "Label confirmation is successful");
			}else{
				log.logLine(Testname, true, "Update Label confirmation is Unsuccessful");
			}

		} else {
			log.logLine(Testname, true, "Label confirmation is not displayed");			
		}


		//On-line only option
		if (doesElementExist2(properties.getProperty("Cancelbtn"), 5)) {	    
			WebElement pradiobtn = driver.findElement(By.cssSelector(properties.getProperty("Cancelbtn")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", pradiobtn);
			log.logLine(Testname, false, "Clickin on Cancel button");
			Thread.sleep(8500);	    			
		}


		if (doesElementExist2(properties.getProperty("onlineonly"), 5)) {	    
			WebElement pradiobtn = driver.findElement(By.cssSelector(properties.getProperty("onlineonly")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", pradiobtn);
			log.logLine(Testname, false, "On-line only radio button selected");
			Thread.sleep(500);
		} else {
			log.logLine(Testname, true, "On-line only radio button select is failed");			
		}

		if (doesElementExist2(properties.getProperty("agreeChkbx"), 5)) {	    
			WebElement chkboxagre = driver.findElement(By.cssSelector(properties.getProperty("agreeChkbx")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkboxagre);
			log.logLine(Testname, false, "Agree terms and conditions clicked");
			Thread.sleep(500);
		} else {
			log.logLine(Testname, true, "Agree terms and conditions check box select is failed");			
		}


		if (doesElementExist2(properties.getProperty("savebtn"), 5)) {	    
			WebElement sbtn = driver.findElement(By.cssSelector(properties.getProperty("savebtn")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", sbtn);
			log.logLine(Testname, false, "Click on Save button");
			Thread.sleep(9500);
		} else {
			log.logLine(Testname, true, "Clicking on Save button is failed");			
		}

		if (doesElementExist2(properties.getProperty("LabelConfrm"), 5)) {	    
			WebElement sbtn = driver.findElement(By.cssSelector(properties.getProperty("LabelConfrm")));
			if ((sbtn.getText().equals("Your changes have been successfully applied to your profile.")) || 
					(sbtn.getText().contains("An email has been sent to the email address provided."))) {
				log.logLine(Testname, false, "Label confirmation is successful");
			}else{
				log.logLine(Testname, true, "Update Label confirmation is Unsuccessful");
			}

		} else {
			log.logLine(Testname, true, "Label confirmation is not displayed");			
		}


		return true;
	}


	public void MultipleSearch(String Testname, String Search1, String SrchOper1, String Search2, String SrchOper2, String Data1, String Data2, String SrchCnt, String SrchResults) throws Exception{

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		if (doesElementExist2(properties.getProperty("CancelBtn"), 5)) {	    
			WebElement removelnk = driver.findElement(By.cssSelector(properties.getProperty("CancelBtn")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", removelnk);
			log.logLine(Testname, false, "First time clicked on '-' sign to add extra search option");			
		} else {
			log.logLine(Testname, false, "Clicking on extra field remove1 is failed");			
		}

		String FrmDate = test.readColumnData("FromDate", sheetname);
		WebElement retelm3 = waitForElement(properties.getProperty("DateSel"));

		if (doesElementExist2(properties.getProperty("DateSel"), 5)) {	    
			WebElement doctype = driver.findElement(By.cssSelector(properties.getProperty("DateSel")));
			doctype.clear();
			doctype.sendKeys(FrmDate);
			log.logLine(Testname, false, "From Date is filled - "+FrmDate);
		} else {
			negativeCase(Testname, firstWinHandle, "", "ConsumerID field does not exist in application");			
		}


		if (SrchCnt.equals("2")) {
			if (doesElementExist2(properties.getProperty("addopts"), 5)) {
				WebElement addlnk = driver.findElement(By.cssSelector(properties.getProperty("addopts")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", addlnk);
				log.logLine(Testname, false, "One Additional serach option added");
			}else {
				negativeCase(Testname, firstWinHandle, "", "One Additional serach option adding is failed");				
			}
		}else if (SrchCnt.equals("3")) {
			if (doesElementExist2(properties.getProperty("addopts"), 5)) {
				WebElement addlnk = driver.findElement(By.cssSelector(properties.getProperty("addopts")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", addlnk);
				log.logLine(Testname, false, "One Additional serach option added");			

				((JavascriptExecutor) driver).executeScript("arguments[0].click()", addlnk);
				log.logLine(Testname, false, "Two Additional serach option added");
			} else {
				negativeCase(Testname, firstWinHandle, "", "One+two Additional serach option adding is failed");				
			}
		}


		//ZipCode search
		if (doesElementExist2(properties.getProperty("SelField1"), 5)) {	    
			WebElement Client = driver.findElement(By.cssSelector(properties.getProperty("SelField1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Client);

			Thread.sleep(500);			
			if (doesElementExist2(properties.getProperty("FieldSel"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("FieldSel")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(Search1)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Field as "+Search1 +" from the popup..");						
						break;
					}				
				}

			} else {
				negativeCase(Testname, firstWinHandle, "", "Selecting field is failed");				
			}

		} else {
			negativeCase(Testname, firstWinHandle, "", "Field1 + "+Search1+" selection is failed");
		}


		if (doesElementExist2(properties.getProperty("SelOpert1"), 5)) {	    
			WebElement Client = driver.findElement(By.cssSelector(properties.getProperty("SelOpert1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Client);

			if (doesElementExist2(properties.getProperty("opertrSel"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("opertrSel")));

				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(SrchOper1)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the operator as "+SrchOper1 +" from the popup..");						
						break;
					}				
				}

			} else {
				negativeCase(Testname, firstWinHandle, "", "Selecting operator is failed");
			}	

		} else {
			negativeCase(Testname, firstWinHandle, "", "Operator1 + Equals selection is failed");			
		}


		if (doesElementExist2(properties.getProperty("TxtField1"), 5)) {	    
			WebElement txtfld = driver.findElement(By.cssSelector(properties.getProperty("TxtField1")));
			txtfld.clear();
			txtfld.sendKeys(Data1);
			log.logLine(Testname, false, "Filled text field1 with "+Data1);

		} else if (doesElementExist2(properties.getProperty("TxtField2"), 5)) {	    
			WebElement txtfld = driver.findElement(By.cssSelector(properties.getProperty("TxtField2")));
			txtfld.clear();
			txtfld.sendKeys(Data1);
			log.logLine(Testname, false, "Filled text field1 with "+Data1);

		} else{
			negativeCase(Testname, firstWinHandle, "", "Text field1 does not exist in the application");			
		}


		if (SrchCnt.equals("2")) {
			if (doesElementExist2(properties.getProperty("SelField2"), 5)) {	    
				WebElement Client = driver.findElement(By.cssSelector(properties.getProperty("SelField2")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", Client);

				if (doesElementExist2(properties.getProperty("FieldSel"), 5)) {
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("FieldSel")));


					for (WebElement lnk:selopts) {
						if (lnk.getText().equals(Search2)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false, "Selecting the Field2 as "+Search2 +" from the popup..");						
							break;
						}				
					}

				} else {
					negativeCase(Testname, firstWinHandle, "", "Selecting field2 is failed");					
				}
			} else {
				negativeCase(Testname, firstWinHandle, "", "Field2 + LastName selection is failed");				
			}


			if (doesElementExist2(properties.getProperty("SelOpert2"), 5)) {	    
				WebElement Client = driver.findElement(By.cssSelector(properties.getProperty("SelOpert2")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", Client);

				if (doesElementExist2(properties.getProperty("opertrSel"), 5)) {
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("opertrSel")));

					for (WebElement lnk:selopts) {
						if (lnk.getText().equals(SrchOper2)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false, "Selecting the operator2 as "+SrchOper2 +" from the popup..");						
							break;
						}				
					}

				} else {
					negativeCase(Testname, firstWinHandle, "", "Selecting operator2 is failed");					
				}
			} else {
				negativeCase(Testname, firstWinHandle, "", "Operator2 + WildCard selection is failed");				
			}			

			/*
			if (doesElementExist2(properties.getProperty("TxtField1"), 5)) {	    
				WebElement txtfld = driver.findElement(By.cssSelector(properties.getProperty("TxtField1")));
				txtfld.clear();
				txtfld.sendKeys(Data2);
				log.logLine(Testname, false, "Filled text field2 with "+Data2);

		    } else 
			 */	
			if (doesElementExist2(properties.getProperty("TxtField2"), 5)) {	    
				WebElement txtfld = driver.findElement(By.cssSelector(properties.getProperty("TxtField2")));
				txtfld.clear();
				txtfld.sendKeys(Data2);
				log.logLine(Testname, false, "Filled text field2 with "+Data2);

			} else{
				negativeCase(Testname, firstWinHandle, "", "Text field2 does not exist in the application");				
			}

		}


		if (doesElementExist2(properties.getProperty("SearchBtn"), 5)) {	    
			WebElement Client = driver.findElement(By.cssSelector(properties.getProperty("SearchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Client);

			Thread.sleep(1000);
			PleasewaitDisappear();
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicked on Search button");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Search button does not exist in the application");			
		}


		//Wait till the home page shows up
		WebElement retelm4 = waitForElement(properties.getProperty("CntReport"));
		Thread.sleep(1000);

		if (doesElementExist2(properties.getProperty("CntReport"), 5)) {
			String Textcnt = retelm4.getText();

			if (Textcnt.trim().equals("No documents found")) {
				log.logLine(Testname, true, "No documents found for the specified search");				
			} else {			

				String[] Arr = Textcnt.split("of");						
				if(Arr[1].trim().equals(SrchResults)) {
					log.logLine(Testname, false, "There are "+SrchResults+" documents displayed for the specified search");
				} else {
					log.logLine(Testname, true, "Actual Search documents count:"+Arr[1].trim() +" from the expected:"+SrchResults);			
				}
			}
		}
	}


	public void eDeliverLogout(String RandNo, String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("Logout"), 5)) {	    
			WebElement logoutlnk = driver.findElement(By.cssSelector(properties.getProperty("Logout")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", logoutlnk);
			log.logLine(Testname, false, "Clicked on eDelivery Logout button");			
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on eDelivery Logout button is failed");			
		}

		WebElement retelm = waitForElement(properties.getProperty("eDelUserName"));

	}

	public boolean Datevalidation(String Testname) throws Exception {

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

		Thread.sleep(1500);


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

				WebElement retelm = waitForElement(properties.getProperty("ClientID"));
				if (doesElementExist2(properties.getProperty("ClientID"), 5)) {
					WebElement Client = driver.findElement(By.cssSelector(properties.getProperty("ClientID")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Client);

					Thread.sleep(1500);
					//WebElement SelClt = driver.findElement(By.xpath(properties.getProperty("selClient"+Iter)));
					//((JavascriptExecutor) driver).executeScript("arguments[0].click()", SelClt);

					if (doesElementExist2(properties.getProperty("ClientIDSel"), 5)) {
						List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ClientIDSel")));
						for (WebElement lnk:selopts) {
							if (lnk.getText().contains(ClientIDS)) {
								((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
								Thread.sleep(1000);
								log.logLine(Testname, false, "Selecting the ClientID as "+ClientIDS +" from the popup..");						
								break;
							}				
						}

					} else {
						negativeCase(Testname, firstWinHandle, "", "Selecting the Client ID failed");				
					}					

				} else {
					negativeCase(Testname, firstWinHandle, "", "Selecting the Client ID failed");			
				}



				if (doesElementExist2(properties.getProperty("DocType"), 5)) {	    
					WebElement Client = driver.findElement(By.cssSelector(properties.getProperty("DocType")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Client);

					Thread.sleep(1500);
					//WebElement SelClt = driver.findElement(By.xpath(properties.getProperty("SelDoc"+Iter)));
					//((JavascriptExecutor) driver).executeScript("arguments[0].click()", SelClt);

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


				if (doesElementExist2(properties.getProperty("ConsumerID"), 5)) {	    
					WebElement cons = driver.findElement(By.cssSelector(properties.getProperty("ConsumerID")));
					cons.clear();
					cons.sendKeys(Cons);
					log.logLine(Testname, false, "ConsumerID is filled with "+Cons);
				} else {
					negativeCase(Testname, firstWinHandle, "", "ConsumerID field does not exist in application");
				}


				if (doesElementExist2(properties.getProperty("Submitbtn"), 5)) {	    
					WebElement Client = driver.findElement(By.cssSelector(properties.getProperty("Submitbtn")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", Client);			

					Thread.sleep(4000);			
					log.logLine(Testname, false, "Clicked on Submit button");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Submit button does not exist in application");			
				}


				WebElement retelm2 = waitForElement(properties.getProperty("ActeDeliverLink"));
				if (doesElementExist2(properties.getProperty("ActeDeliverLink"), 5)) {	    

					String actlink = driver.findElement(By.cssSelector(properties.getProperty("ActeDeliverLink"))).getText();			
					driver.get(actlink);
					Thread.sleep(30000);

					log.logLine(Testname, false, "Clicked on Link to open actual eDelivery page");
				} else {
					negativeCase(Testname, firstWinHandle, "", "Click on Link to open actual eDelivery page is failed");			
				}

			}

			if (doesElementExist2(properties.getProperty("UserConsentMnu"), 5)) {	    
				WebElement delmnu = driver.findElement(By.cssSelector(properties.getProperty("UserConsentMnu")));						
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", delmnu);
				log.logLine(Testname, false, "Clicked on User Consent Page menu");
				Thread.sleep(2500);
			} else {
				negativeCase(Testname, firstWinHandle, "", "Clicked on User Consent Page menu is failed");			
			}


			WebElement retelm13 = waitForElement(properties.getProperty("SearchFld"));		
			if (doesElementExist2(properties.getProperty("SearchFld"), 5)) {	    
				WebElement frstName = driver.findElement(By.cssSelector(properties.getProperty("SearchFld")));						
				frstName.clear();
				frstName.sendKeys(Cons);
				log.logLine(Testname, false, "Entering the insured code "+Actinsuredcode);
				Thread.sleep(500);
			} else {
				log.logLine(Testname, true, "Entering the first name in profiles page is failed");			
			}

			if (doesElementExist2(properties.getProperty("SearchbtnCons"), 5)) {	    
				WebElement pradiobtn = driver.findElement(By.cssSelector(properties.getProperty("SearchbtnCons")));						
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", pradiobtn);
				log.logLine(Testname, false, "Clicked on Search button in Consent page");
				Thread.sleep(3500);
			} else {
				log.logLine(Testname, true, "Clicked on Search button in Consent page is failed");			
			}

			if (doesElementExist2(properties.getProperty("Historybtn"), 5)) {	    
				WebElement hisbtn = driver.findElement(By.cssSelector(properties.getProperty("Historybtn")));						
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", hisbtn);
				log.logLine(Testname, false, "Clicked on Search button in Consent page");
				Thread.sleep(3500);
			} else {
				log.logLine(Testname, true, "Clicked on Search button in Consent page is failed");			
			}



			/*	if (doesElementExist2(properties.getProperty("div[id='History-Consent-Pane-grid'] div table tbody tr td"), 5)) {	    
					String hisbtn = driver.findElement(By.cssSelector(properties.getProperty("div[id='History-Consent-Pane-grid'] div table tbody tr td"))).getText();						

					log.logLine(Testname, false, "Clicked on Search button in Consent page");
					Thread.sleep(3500);
				} else {
					log.logLine(Testname, true, "Clicked on Search button in Consent page is failed");			
				}
			 */

			String[] Sort = new String[150];
			int length = Sort.length;

			String row = "tr";
			List<WebElement> DataCnt= driver.findElements(By.cssSelector("div[id='History-Consent-Pane-grid'] div table tbody tr"));
			int j=13;	
			if(doesElementExist2(properties.getProperty("Datevldn"), 5)){
				for(int i = 0; i < DataCnt.size(); i++) {
					Sort[i] = driver.findElement(By.cssSelector("div[id='History-Consent-Pane-grid'] div table tbody "+row+" td")).getText();
					String Date=Sort[i];
					log.logLine(Testname, true, "Reading the date as" +Date);	
					String Array[]=Date.split("/");
					String Month=Array[0].trim();

					if (Month.equals(j)){
						log.logLine(Testname, true, "Date Format is not in MM/DD/YYYY Format");			
					}else {
						log.logLine(Testname, false, "Date Format is in MM/DD/YYYY Format");			
					}

					if(i>=DataCnt.size()-1){
						break;
					}


					row = row + "+tr";
					log.logLine(Testname, false, "Iterating through the Rows..");
				}

			}



			driver.close();
			driver.switchTo().window(firstWinHandle);

		}	  


		return true;
	}


	public void GmailVerification(String Testname) throws Exception {


		driver.get("https://www.googlemail.com");
		Thread.sleep(3000);
		WebElement retelm = waitForElement(properties.getProperty("Gmal_ID"));


		if (doesElementExist2(properties.getProperty("Gmail_ID"), 5)) {	    
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Gmail_ID")));
			if (!(gmailid.getAttribute("class").equalsIgnoreCase("email-input hidden"))) {
				gmailid.clear();
				gmailid.sendKeys("automationpivot@gmail.com");
				log.logLine(Testname, false, "Entering the Gamil ID..");				
			}						
		} else {
			negativeCase(Testname, firstWinHandle, "", "Entering the Gamil ID is failed");			
		}


		if (doesElementExist2(properties.getProperty("Gmail_Passwd"), 5)) {	    
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Gmail_Passwd")));
			gmailid.clear();
			gmailid.sendKeys("miracle123");
			log.logLine(Testname, false, "Entering the Gamil password..");			

		} else if (doesElementExist2(properties.getProperty("Gmail_NxtBtn"), 5)) {
			WebElement gmailnxt = driver.findElement(By.cssSelector(properties.getProperty("Gmail_NxtBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", gmailnxt);
			Thread.sleep(4000);

			if (doesElementExist2(properties.getProperty("Gmail_Passwd"), 5)) {	    
				WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Gmail_Passwd")));
				gmailid.clear();
				gmailid.sendKeys("miracle123");
				log.logLine(Testname, false, "Entering the Gamil password..");			

			} else {
				negativeCase(Testname, firstWinHandle, "", "Entering the password ID is failed");				
			} 	


		} else {
			negativeCase(Testname, firstWinHandle, "", "Entering the password ID is failed");			
		}


		Thread.sleep(1000);		
		if (doesElementExist2(properties.getProperty("Gmail_SignIn"), 5)) {	    
			WebElement gmailSign = driver.findElement(By.cssSelector(properties.getProperty("Gmail_SignIn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", gmailSign);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the Gamil SignIn");			
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicked on the Gamil SignIn is failed");			
		}


		long timenow = System.currentTimeMillis();
		long testime = timenow - GmailstartTime;
		int totalTime =(int) ((testime/(1000*60)));	 

		if (2 > totalTime) {
			int WaitTime = 2 - totalTime; 
			log.logLine(Testname, false, "Going to wait for "+WaitTime +"minutes, please wait...");
			Thread.sleep(WaitTime*1000*60);		 
		}       

		if (doesElementExist2(properties.getProperty("SrchMail"), 5)) {	    
			WebElement srchfld = driver.findElement(By.cssSelector(properties.getProperty("SrchMail")));
			srchfld.sendKeys("in:inbox Consent Confirmation for SkyBlue");			
			log.logLine(Testname, false, "Entering the Search text..");			
		} else {
			negativeCase(Testname, firstWinHandle, "", "Entering the Search text is failed");			
		}


		if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {	    
			WebElement srchbtn = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchbtn);
			log.logLine(Testname, false, "Clicked on the Search Email");			
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the Search Email is failed");	    	
		}


		Thread.sleep(3000);        
		if (doesElementExist2(properties.getProperty("SelectMail"), 5)) {	    
			WebElement OpenMail = driver.findElement(By.cssSelector(properties.getProperty("SelectMail")));
			//if (OpenMail.getText().equalsIgnoreCase("Consent Confirmation for")) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", OpenMail);
			log.logLine(Testname, false, "Clicked on the First email to read");					

		} else if (doesElementExist2(properties.getProperty("SelectMail2"), 5)) {	    
			WebElement OpenMail2 = driver.findElement(By.cssSelector(properties.getProperty("SelectMail2")));
			//if (OpenMail2.getText().equalsIgnoreCase("Consent Confirmation for")) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", OpenMail2);
			log.logLine(Testname, false, "Clicked on the First email to read");

			/*}else {
				log.logLine(Testname, true, "Opening Consent email failed");
				driver.close();
				driver.switchTo().window(firstWinHandle);
				throw new Exception("Opening Consent email failed");
			}*/

		} else {	   
			negativeCase(Testname, firstWinHandle, "", "Clicking on the First email to read is failed");			
		}


		if (doesElementExist("//a[contains(text(), 'https://ha2-"+(Initialization.EnvirSite.toLowerCase())+".edelivery-')]", 5)) {	    
			WebElement linksel = driver.findElement(By.xpath("//a[contains(text(), 'https://ha2-"+(Initialization.EnvirSite.toLowerCase())+".edelivery-')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", linksel);
			log.logLine(Testname, false, "Clicked on the Consent Email link");			
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the Consent Email link is failed");			
		}


		Thread.sleep(5000);        
		Set<String> handles = driver.getWindowHandles();
		secondWinHandle = driver.getWindowHandle(); 
		handles.remove(firstWinHandle);
		handles.remove(secondWinHandle);

		boolean browserexist = handles.iterator().hasNext();
		if (browserexist) {
			String winHandle=handles.iterator().next();
			if ((winHandle!=firstWinHandle) && (winHandle!=secondWinHandle)) {		    

				//Switch control to second window
				driver.switchTo().window(winHandle);		    	
				Thread.sleep(3000);

				if (doesElementExist2(properties.getProperty("ConsentMsg"), 5)) {	    
					WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("ConsentMsg")));

					if (gmailid.getText().equalsIgnoreCase("Your delivery preferences have been saved!")) { 				
						log.logLine(Testname, false, "Confirmation message after clicking on mail link");
						driver.close();
						driver.switchTo().window(secondWinHandle);

					}else {
						negativeCase(Testname, secondWinHandle, "", "Confirmation message after clicking on mail link is failed");					
					}

				} else {
					negativeCase(Testname, secondWinHandle, "", "Consent Message window is not opened");					
				}
			}
		}      

		if (doesElementExist2(properties.getProperty("GmailID"), 5)) {	    
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("GmailID")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", gmailid);
			Thread.sleep(500);
			log.logLine(Testname, false, "Clicked on the Gmail Logout");			
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the Gmail Logout is failed");			
		}


		if (doesElementExist2(properties.getProperty("GmailLogout"), 5)) {	    
			WebElement logout = driver.findElement(By.cssSelector(properties.getProperty("GmailLogout")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", logout);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicked on the Gmail Logout");			
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the Gmail Logout is failed");			
		}

		//Closing the Gmail opened window
		driver.close();
		driver.switchTo().window(firstWinHandle);			

	}

	//>>>>>>>>>>>>>>>>>>>>>>>>>>edelivery consent code starts>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	public boolean edelivery_consent(String Testname) throws Exception {

		ClientAppsrch_eDelivery(Testname);
		Thread.sleep(15000);
		Skyblueapp(Testname);
		Thread.sleep(60000);
		GmailVerification1(Testname);
		Thread.sleep(2000);
		consentselection_change(Testname);
		driver.close();
		driver.switchTo().window(firstWinHandle);
		return  true;

	}


	public void consentselection_change(String Testname) throws Exception {

		InputOutputData test = new InputOutputData();
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(5000);
		driver.switchTo().defaultContent();

		Thread.sleep(1000);
		log.logLine(Testname, false, "Clicking on Admin to select Archive >> test harness");	    


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

		WebElement retelm = waitForElement(properties.getProperty("ClientID"));
		String ClientID = test.readColumnData("ClientID", sheetname);

		if (doesElementExist2(properties.getProperty("ClientID"), 5)) {
			WebElement Client = driver.findElement(By.cssSelector(properties.getProperty("ClientID")));
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
		if (doesElementExist2(properties.getProperty("Submitbtn"), 5)) {	    
			WebElement Client = driver.findElement(By.cssSelector(properties.getProperty("Submitbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Client);			
			Thread.sleep(4000);			
			log.logLine(Testname, false, "Clicked on Submit button");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Submit button does not exist in application");			
		}
		Thread.sleep(3000);
		//WebElement retelm2 = waitForElement(properties.getProperty("ActeDeliverLink"));
		if (doesElementExist2(properties.getProperty("ActeDeliverLink"), 5)) {	    
			String actlink = driver.findElement(By.cssSelector(properties.getProperty("ActeDeliverLink"))).getText();			
			driver.get(actlink);
			Thread.sleep(40000);
			log.logLine(Testname, false, "Clicked on Link to open actual eDelivery page");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Click on Link to open actual eDelivery page is failed");			
		}
		Thread.sleep(2000);
		String user= test.readColumnData("user", sheetname);



		if (doesElementExist2(properties.getProperty("UserConsentMnu"), 5)) {	    
			WebElement delmnu = driver.findElement(By.cssSelector(properties.getProperty("UserConsentMnu")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", delmnu);
			log.logLine(Testname, false, "Clicked on User Consent Page menu");

		}else if (doesElementExist2(properties.getProperty("UserConsentMnu1"), 5)) {	    
			WebElement delmnu = driver.findElement(By.cssSelector(properties.getProperty("UserConsentMnu1")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", delmnu);
			log.logLine(Testname, false, "Clicked on User Consent Page menu");

		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicked on User Consent Page menu is failed");			
		}

		Thread.sleep(2000);

		if (doesElementExist2(properties.getProperty("UseridMnu"), 5)) {	    
			WebElement useridmnu = driver.findElement(By.cssSelector(properties.getProperty("UseridMnu")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",useridmnu);
			log.logLine(Testname, false, "Clicked on User Id menu");
			List<WebElement> DataCnt= driver.findElements(By.cssSelector(properties.getProperty("usermnulst")));
			for (WebElement lnk:DataCnt) {
				String txt=lnk.getText();
				if(txt.equalsIgnoreCase("User ID")){
					((JavascriptExecutor) driver).executeScript("arguments[0].click()",lnk);
					log.logLine(Testname, false, "Selecting the user id option from list");
				}
				Thread.sleep(2500);
			}} else {
				log.logLine(Testname, true, "Failed to Click on User id menu");
				negativeCase(Testname, firstWinHandle, "", "Failed to Click on User id menu");			
			}


		//WebElement retelm13 = waitForElement(properties.getProperty("SearchFld"));		
		if (doesElementExist2(properties.getProperty("SearchFld"), 5)) {	    
			WebElement frstName = driver.findElement(By.cssSelector(properties.getProperty("SearchFld")));						
			frstName.clear();
			frstName.sendKeys(user);
			log.logLine(Testname, false, "Entering the user id "+user);
			Thread.sleep(500);
		} else {
			log.logLine(Testname, true, "Entering the user id failed");			
		}

		if (doesElementExist2(properties.getProperty("SearchbtnCons"), 5)) {	    
			WebElement pradiobtn = driver.findElement(By.cssSelector(properties.getProperty("SearchbtnCons")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", pradiobtn);
			log.logLine(Testname, false, "Clicked on Search button in Consent page");
			Thread.sleep(3500);
		} else {
			log.logLine(Testname, true, "Clicked on Search button in Consent page is failed");			
		}

		if (doesElementExist2(properties.getProperty("Historybtn"), 5)) {	    
			WebElement hisbtn = driver.findElement(By.cssSelector(properties.getProperty("Historybtn")));						
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", hisbtn);
			log.logLine(Testname, false, "Clicked on History button in Consent page");
			Thread.sleep(3500);
		} else {
			log.logLine(Testname, true, "Clicked on History button in Consent page is failed");			
		}
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
		Date date = new Date();
		todaysDate = dateFormat.format(date);

		//Consent validation based on date created
		String[] Sort = new String[150];
		int length = Sort.length;
		String[] Sort1 = new String[150];

		String CurDate[]=todaysDate.split(":");
		log.logLine(Testname, false , "Current date is " +todaysDate);
		String row = "tr";
		List<WebElement> DataCnt= driver.findElements(By.cssSelector("div[id='History-Consent-Pane-grid'] div table tbody tr"));

		if(doesElementExist2(properties.getProperty("Datevldn"), 5)){
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

		return ;
	}

	public void GmailVerification1(String Testname) throws Exception {

		driver.get("https://www.googlemail.com");

		Thread.sleep(3000);
		WebElement retelm = waitForElement(properties.getProperty("Gmail_ID"));


		if (doesElementExist2(properties.getProperty("Gmail_ID"), 5)) {	    
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Gmail_ID")));
			if (!(gmailid.getAttribute("class").equalsIgnoreCase("email-input hidden"))) {
				gmailid.clear();
				gmailid.sendKeys("automationpivot@gmail.com");
				log.logLine(Testname, false, "Entering the Gamil ID..");				
			}						
		} else {
			negativeCase(Testname, firstWinHandle, "", "Entering the Gamil ID is failed");			
		}


		if (doesElementExist2(properties.getProperty("Gmail_Passwd"), 5)) {	    
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Gmail_Passwd")));
			gmailid.clear();
			gmailid.sendKeys("miracle@123");
			log.logLine(Testname, false, "Entering the Gamil password..");			

		} else if (doesElementExist2(properties.getProperty("Gmail_NxtBtn"), 5)) {
			WebElement gmailnxt = driver.findElement(By.cssSelector(properties.getProperty("Gmail_NxtBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", gmailnxt);
			Thread.sleep(4000);

			if (doesElementExist2(properties.getProperty("Gmail_Passwd"), 5)) {	    
				WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Gmail_Passwd")));
				gmailid.clear();
				gmailid.sendKeys("miracle@123");
				log.logLine(Testname, false, "Entering the Gamil password..");			

			} else {
				negativeCase(Testname, firstWinHandle, "", "Entering the password ID is failed");				
			} 	


		} else {
			negativeCase(Testname, firstWinHandle, "", "Entering the password ID is failed");			
		}


		Thread.sleep(1000);	
		if (doesElementExist2(properties.getProperty("Gmail_SignIn"), 5)) {	    
			WebElement gmailSign = driver.findElement(By.cssSelector(properties.getProperty("Gmail_SignIn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", gmailSign);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the Gmaill SignIn");			
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicked on the Gmail SignIn is failed");			
		}


		long timenow = System.currentTimeMillis();
		long testime = timenow - GmailstartTime;
		int totalTime =(int) ((testime/(1000*60)));	 

		if (4 > totalTime) {
			int WaitTime = 2 - totalTime; 
			log.logLine(Testname, false, "Going to wait for "+WaitTime +"minutes, please wait...");
			Thread.sleep(WaitTime*1000*60);		 
		}       

		Thread.sleep(120000);
		if (doesElementExist2(properties.getProperty("SrchMail"), 5)) {	    
			WebElement srchfld = driver.findElement(By.cssSelector(properties.getProperty("SrchMail")));
			srchfld.sendKeys("in:inbox Consent Confirmation for skyblue");		
			log.logLine(Testname, false, "Entering the Search text..");			
		} else {
			log.logLine(Testname, true, "Entering the Search text is failed");
			negativeCase(Testname, firstWinHandle, "", "Entering the Search text is failed");			
		}
		Thread.sleep(2000);   

		if (doesElementExist2(properties.getProperty("SrchBtn"), 5)) {   
			WebElement srchbtn = driver.findElement(By.cssSelector(properties.getProperty("SrchBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srchbtn);
			log.logLine(Testname, false, "Clicked on the Search Email");			
		} else {
			log.logLine(Testname, true, "Clicked on the Search Email failed");
			negativeCase(Testname, firstWinHandle, "", "Clicking on the Search Email is failed");	    	
		}

		Thread.sleep(6000);    

		if (doesElementExist2(properties.getProperty("SelectMail"), 5)) {	    
			WebElement OpenMail = driver.findElement(By.cssSelector(properties.getProperty("SelectMail")));
			//if (OpenMail.getText().equalsIgnoreCase("Consent Confirmation for")) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", OpenMail);
			log.logLine(Testname, false, "Clicked on the First email to read");					

		} else if (doesElementExist2(properties.getProperty("SelectMail2"), 5)) {	    
			WebElement OpenMail2 = driver.findElement(By.cssSelector(properties.getProperty("SelectMail2")));
			//if (OpenMail2.getText().equalsIgnoreCase("Consent Confirmation for")) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", OpenMail2);
			log.logLine(Testname, false, "Clicked on the First email to read");

		} else {	   
			negativeCase(Testname, firstWinHandle, "", "Clicking on the First email to read is failed");		
		}


		Thread.sleep(2000);

		if (doesElementExist2(properties.getProperty("contentlink"), 5)) {
			WebElement contentlnk = driver.findElement(By.cssSelector(properties.getProperty("contentlink")));
			linktext=contentlnk.getText();
			Thread.sleep(4000);
			log.logLine(Testname, false,"Clicked on the Document Link is successfull");

		} else {
			log.logLine(Testname, false,"Clicked on the Document Link is Failed");
		}


		Thread.sleep(12000);

		driver.get(linktext);
		//driver.get("https://skyblue-stage.edelivery-view.com/confirm?auth=4534382B5469562F6169314B2B7849754A4C4E53365A626B45646138726C524E566C2F4E7741355955656D67752F4B70543148614863304A324F3030653069647267516B7535497A2B67417172727776367544496E366954787863462F442B487832787471386D454C525A5438684B646141393659773D3D");

		if (doesElementExist2(properties.getProperty("ConsentMsg"), 5)) {	    
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("ConsentMsg")));

			if (gmailid.getText().equalsIgnoreCase("Your delivery preferences have been saved!")) { 				
				log.logLine(Testname, false, "Confirmation message after clicking on mail link");

			}else {
				log.logLine(Testname, true, "Confirmation message failed after clicking on mail link");				
			}

		} else {
			negativeCase(Testname, secondWinHandle, "", "Consent Message window is not opened");					
		}
		Thread.sleep(3000);

		driver.close();
		Thread.sleep(1000);
		driver.switchTo().window(firstWinHandle);
	}

	public void Skyblueapp(String Testname) throws Exception {
		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();	
		String envi = Initialization.EnvirSite.toUpperCase();

		String Usrnme=test.readColumnData("UserName", sheetname);
		String Pswd=test.readColumnData("Password", sheetname);

		String Emladd=test.readColumnData("EmailAddress", sheetname);
		Thread.sleep(2000);
		if(envi.equalsIgnoreCase("stage")){
			driver.get("https://skyblue-"+envi+".edelivery-view.com/");}
		else{driver.get("https://skyblue.edelivery-view.com/");}

		Thread.sleep(3000);
		WebElement retelm = waitForElement(properties.getProperty("Skblu_id"));


		if (doesElementExist2(properties.getProperty("Skblu_id"), 5)) {	    
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Skblu_id")));
			gmailid.clear();
			gmailid.sendKeys(Usrnme);
			log.logLine(Testname, false, "Entering the skyblue user id ..");				

		} else {
			negativeCase(Testname, firstWinHandle, "", "Entering the skyblue user id  failed");			
		}


		if (doesElementExist2(properties.getProperty("Skblu_pwd"), 5)) {	    
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Skblu_pwd")));
			gmailid.clear();
			gmailid.sendKeys(Pswd);
			log.logLine(Testname, false, "Entering the skyblue password..");			

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
		String Docstart= test.readColumnData("Docstartdate", sheetname);
		//Document validation
		Doctype_generatefieldvalidation(Testname,Docstart);

		// Click on Delivery Option Tab
		Thread.sleep(5000);
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
				}}}else {
					log.logLine(Testname, true, "Clicked on Delivery Options is failed");
					throw new Exception("Clicked on Delivery Options is failed");
				}

		Thread.sleep(2000);
		WebElement submitbutton = driver.findElement(By.cssSelector(properties.getProperty("submit")));
		String data=submitbutton.getAttribute("value");
		if(data.equalsIgnoreCase("save")){
			if (doesElementExist2(properties.getProperty("save"), 5)) {	    
				WebElement save = driver.findElement(By.cssSelector(properties.getProperty("save")));
				Thread.sleep(2000);
				log.logLine(Testname, false, "Delivery option is set for Save button");			
			} else {
				log.logLine(Testname, false, "Selecting save button is  failed");		
			}}else if(data.equalsIgnoreCase("Cancel")){
				if (doesElementExist2(properties.getProperty("Cancel"), 5)) {
					WebElement can = driver.findElement(By.cssSelector(properties.getProperty("Cancel")));
					can.click();
					log.logLine(Testname, false, "Selecting cancel button is  successful");
				}else{
					log.logLine(Testname, false, "Selecting cancel button is  failed");
				}

			}

		if (doesElementExist2(properties.getProperty("DeliveryOptions"), 5)) {
			WebElement Deloptn = driver.findElement(By.cssSelector(properties.getProperty("DeliveryOptions")));
			String data1=Deloptn.getText();
			if(data1.equalsIgnoreCase("Delivery Options"))
			{((JavascriptExecutor) driver).executeScript("arguments[0].click()", Deloptn);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicked on Delivery Options options menu");
			}
			else if (doesElementExist2(properties.getProperty("DeliveryOptions1"), 5)) {
				WebElement Deloptn1 = driver.findElement(By.cssSelector(properties.getProperty("DeliveryOptions1")));
				String data2=Deloptn.getText();
				if(data2.equalsIgnoreCase("Delivery Options"))
				{((JavascriptExecutor) driver).executeScript("arguments[0].click()", Deloptn1);
				Thread.sleep(4000);
				log.logLine(Testname, false, "Clicked on Delivery Options options menu");
				}
			}
			else if (doesElementExist2(properties.getProperty("DeliveryOptions2"), 5)) {
				WebElement Deloptn1 = driver.findElement(By.cssSelector(properties.getProperty("DeliveryOptions2")));
				String data2=Deloptn.getText();
				if(data2.equalsIgnoreCase("Delivery Options"))
				{((JavascriptExecutor) driver).executeScript("arguments[0].click()", Deloptn1);
				Thread.sleep(4000);
				log.logLine(Testname, false, "Clicked on Delivery Options options menu");
				}
			}
			else if (doesElementExist2(properties.getProperty("DeliveryOptions3"), 5)) {
				WebElement Deloptn1 = driver.findElement(By.cssSelector(properties.getProperty("DeliveryOptions3")));
				String data2=Deloptn.getText();
				if(data2.equalsIgnoreCase("Delivery Options"))
				{((JavascriptExecutor) driver).executeScript("arguments[0].click()", Deloptn1);
				Thread.sleep(4000);
				log.logLine(Testname, false, "Clicked on Delivery Options options menu");
				}
			}
		}else {
			log.logLine(Testname, true, "Clicked on Delivery Options is failed");
			throw new Exception("Clicked on Delivery Options is failed");
		}


		/*if (doesElementExist(properties.getProperty("logout"), 5)) {
			WebElement Logout = driver.findElement(By.xpath(properties.getProperty("logout")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Logout);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Click on Logout Button is successful");
		} else {
			log.logLine(Testname, false, "Application downloaded directly");

		}
		//logging in again

		Thread.sleep(3000);
		WebElement retelm1 = waitForElement(properties.getProperty("Skblu_id"));


		if (doesElementExist2(properties.getProperty("Skblu_id"), 5)) {	    
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Skblu_id")));
			gmailid.clear();
			gmailid.sendKeys(Usrnme);
			log.logLine(Testname, false, "Entering the skyblue user id ..");				

		} else {
			negativeCase(Testname, firstWinHandle, "", "Entering the skyblue user id  failed");			
		}


		if (doesElementExist2(properties.getProperty("Skblu_pwd"), 5)) {	    
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Skblu_pwd")));
			gmailid.clear();
			gmailid.sendKeys(Pswd);
			log.logLine(Testname, false, "Entering the skyblue password..");			

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


		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("DeliveryOptions"), 5)) {
			WebElement Deloptn = driver.findElement(By.cssSelector(properties.getProperty("DeliveryOptions")));
			String data3=Deloptn.getText();
			if(data3.equalsIgnoreCase("Delivery Options"))
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
				}}}else {
					log.logLine(Testname, true, "Clicked on Delivery Options is failed");
					//throw new Exception("Clicked on Delivery Options is failed");
				}
		Thread.sleep(2000);*/

		if (doesElementExist2(properties.getProperty("GoonlineBlue"), 5)) {
			WebElement Gordo = driver.findElement(By.cssSelector(properties.getProperty("GoonlineBlue")));
			if (!Gordo.isSelected())
			{Gordo.click();
			log.logLine(Testname, false, "Selecting Go online and save the Planet for Blue document is successful");
			}else{
				log.logLine(Testname, false, "Selecting Go online and save the Planet for Blue document is already Checked");
			}

		}

		if (doesElementExist2(properties.getProperty("emailadd"), 5)) {	    
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("emailadd")));
			gmailid.clear();
			gmailid.sendKeys(Emladd);
			log.logLine(Testname, false, "Entering the email address");				

		} else {
			negativeCase(Testname, firstWinHandle, "", "Entering the email address  failed");			
		}	

		Thread.sleep(3000);

		if (doesElementExist2(properties.getProperty("chkagree"), 5)) {	    
			WebElement chkagree = driver.findElement(By.cssSelector(properties.getProperty("chkagree")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", chkagree);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the Agree to terms & conditions checkbox");			
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on the Agree to terms & conditions checkbox failed");			
		}

		Thread.sleep(2000);

		WebElement submitbutton2 = driver.findElement(By.cssSelector(properties.getProperty("submit")));
		String data2=submitbutton2.getAttribute("value");
		if(data2.equalsIgnoreCase("save")){
			if (doesElementExist2(properties.getProperty("save"), 5)) {	    
				WebElement save = driver.findElement(By.cssSelector(properties.getProperty("save")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
				Thread.sleep(2000);
				log.logLine(Testname, false, "Clicked on the save button");			
			} else {
				log.logLine(Testname, false, "Selecting save button is  failed");		
			}}else if(data2.equalsIgnoreCase("Cancel")){
				if (doesElementExist2(properties.getProperty("Cancel"), 5)) {
					WebElement can = driver.findElement(By.cssSelector(properties.getProperty("Cancel")));
					//			can.click();
					log.logLine(Testname, false, "Selecting cancel button is  successful");
				}else{
					log.logLine(Testname, false, "Selecting cancel button is  failed");
				}

			}	

		/*Thread.sleep(3000);
		WebElement submitbutton1 = driver.findElement(By.cssSelector(properties.getProperty("submit")));
		String data1=submitbutton1.getAttribute("value");
		if(data1.equalsIgnoreCase("save")){
			if (doesElementExist2(properties.getProperty("save"), 5)) {	    
				WebElement save = driver.findElement(By.cssSelector(properties.getProperty("save")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
				Thread.sleep(2000);
				log.logLine(Testname, false, "Clicked on the save button");			
			} else {
				log.logLine(Testname, false, "Selecting save button is  failed");		
			}}else if(data.equalsIgnoreCase("Cancel")){
				if (doesElementExist2(properties.getProperty("Cancel"), 5)) {
					WebElement can = driver.findElement(By.cssSelector(properties.getProperty("Cancel")));
					can.click();
					log.logLine(Testname, false, "Selecting cancel button is  successful");
				}else{
					log.logLine(Testname, false, "Selecting cancel button is  failed");;
				}

			}
		if (doesElementExist2(properties.getProperty("save"), 5)) {	    
			WebElement save = driver.findElement(By.cssSelector(properties.getProperty("save")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the save button");			
		} else {
			log.logLine(Testname, false, "Selecting save button button is  failed");		
		}*/

		if (doesElementExist2(properties.getProperty("msg"), 5)) {
			WebElement msg = driver.findElement(By.cssSelector(properties.getProperty("msg")));		
			if (msg.getText().contains("An email has been sent to the email address provided.")) {					
				log.logLine(Testname, false, "Email Addresses Updated Successfully! msg displayed");						
			}				
		} else {
			log.logLine(Testname, false, "Email Addresses Updated Successfully! msg didn't displayed");	
			//(Testname, firstWinHandle, "", "Email Addresses Updated Successfully! msg didn't display");				
		}

		Thread.sleep(3000);
		if (doesElementExist(properties.getProperty("logout"), 5)) {
			WebElement Logout = driver.findElement(By.xpath(properties.getProperty("logout")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Logout);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Click on Logout Button is successful");
		} else {
			log.logLine(Testname, false, "Application downloaded directly");

		}

		if (doesElementExist2(properties.getProperty("Skblu_id"), 5)) {	    
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Skblu_id")));
			gmailid.clear();
			gmailid.sendKeys(Usrnme);
			log.logLine(Testname, false, "Entering the skyblue user id ..");				

		} else {
			negativeCase(Testname, firstWinHandle, "", "Entering the skyblue user id  failed");			
		}


		if (doesElementExist2(properties.getProperty("Skblu_pwd"), 5)) {	    
			WebElement gmailid = driver.findElement(By.cssSelector(properties.getProperty("Skblu_pwd")));
			gmailid.clear();
			gmailid.sendKeys(Pswd);
			log.logLine(Testname, false, "Entering the skyblue password..");			

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



		if (doesElementExist2(properties.getProperty("DeliveryOptions"), 5)) {
			WebElement Deloptn = driver.findElement(By.cssSelector(properties.getProperty("DeliveryOptions")));
			String data1=Deloptn.getText();
			if(data1.equalsIgnoreCase("Delivery Options"))
			{((JavascriptExecutor) driver).executeScript("arguments[0].click()", Deloptn);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicked on Delivery Options options menu");
			}
			else if (doesElementExist2(properties.getProperty("DeliveryOptions1"), 5)) {
				WebElement Deloptn1 = driver.findElement(By.cssSelector(properties.getProperty("DeliveryOptions1")));
				String datas=Deloptn.getText();
				if(datas.equalsIgnoreCase("Delivery Options"))
				{((JavascriptExecutor) driver).executeScript("arguments[0].click()", Deloptn1);
				Thread.sleep(4000);
				log.logLine(Testname, false, "Clicked on Delivery Options options menu");
				}
			}
			else if (doesElementExist2(properties.getProperty("DeliveryOptions2"), 5)) {
				WebElement Deloptn1 = driver.findElement(By.cssSelector(properties.getProperty("DeliveryOptions2")));
				String datass=Deloptn.getText();
				if(datass.equalsIgnoreCase("Delivery Options"))
				{((JavascriptExecutor) driver).executeScript("arguments[0].click()", Deloptn1);
				Thread.sleep(4000);
				log.logLine(Testname, false, "Clicked on Delivery Options options menu");
				}
			}
			else if (doesElementExist2(properties.getProperty("DeliveryOptions3"), 5)) {
				WebElement Deloptn1 = driver.findElement(By.cssSelector(properties.getProperty("DeliveryOptions3")));
				String data3=Deloptn.getText();
				if(data3.equalsIgnoreCase("Delivery Options"))
				{((JavascriptExecutor) driver).executeScript("arguments[0].click()", Deloptn1);
				Thread.sleep(4000);
				log.logLine(Testname, false, "Clicked on Delivery Options options menu");
				}
			}
		}else {
			log.logLine(Testname, true, "Clicked on Delivery Options is failed");
			throw new Exception("Clicked on Delivery Options is failed");
		}




		if (doesElementExist2(properties.getProperty("save"), 5)) {	    
			WebElement save = driver.findElement(By.cssSelector(properties.getProperty("save")));
			//	((JavascriptExecutor) driver).executeScript("arguments[0].click()", save);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on the save button");			

		}else if (doesElementExist2(properties.getProperty("Cancel"), 5)) {
			WebElement can = driver.findElement(By.cssSelector(properties.getProperty("Cancel")));
			can.click();
			log.logLine(Testname, false, "Selecting cancel button is  successful");
		}else{
			log.logLine(Testname, false, "Selecting cancel button is  failed");
		}


		if (doesElementExist(properties.getProperty("logout"), 5)) {
			WebElement Logout = driver.findElement(By.xpath(properties.getProperty("logout")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Logout);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Click on Logout Button is successful");
		} else {
			log.logLine(Testname, false, "Application downloaded directly");

		}

	}





	public boolean ClientAppSel1(String Testname) throws Exception {

		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(1000);
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		//WebElement retelm2 = waitForElement(properties.getProperty("selClint1"));

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

		Thread.sleep(1500);

		return true;
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
					//((JavascriptExecutor) driver).executeScript("arguments[0].click()", applid);
					action.sendKeys(applid, Appid).perform();
					//applid.sendKeys(AppName);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Entered the application name "+Appid+" in the Application name text field in Client/App tool");
				} else {
					log.logLine(Testname, true, "Entering the application name "+Appid+" in the Application name text field in Client/App tool is failed");
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
					driver.switchTo().defaultContent();
					throw new Exception("Clicking on AnyTool drop down list and selecting the option PIVOT eDelivery is  failed");
				}         


				//clicking on search button        
				if (doesElementExist2(properties.getProperty("searchbtn1"), 5)) {
					WebElement srcbtn = driver.findElement(By.cssSelector(properties.getProperty("searchbtn1")));
					log.logLine(Testname, false, "Clicked on search button of the client/app/Tool Admin");
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", srcbtn);
					waitUntilRetrive();
				} else {
					log.logLine(Testname, true, "Clicking on search button of the client/app/Tool Admin is failed");
					throw new Exception("Clicking on search button of the client/app/Tool Admin is failed");
				}

				Thread.sleep(2000);
				if (doesElementExist2(properties.getProperty("ModifyTool"), 5)) {
					WebElement modtool = driver.findElement(By.cssSelector(properties.getProperty("ModifyTool")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", modtool);
					waitUntilRetrive();            
					log.logLine(Testname, false, "Clicked on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin ");
				} else {
					log.logLine(Testname, true, "Clicking on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin is failed");
					throw new Exception("Clicking on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin is failed");
				}
				Thread.sleep(6000);
				if (doesElementExist2(properties.getProperty("clntoverride"), 5)) {
					WebElement clntover = driver.findElement(By.cssSelector(properties.getProperty("clntoverride")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", clntover);
					//waitUntilRetrive();            
					log.logLine(Testname, false, "Clicked on the 'eDelivery Client Overrides' button in client/app/Tool Admin ");
				} else {
					log.logLine(Testname, true, "Clicking on the 'eDelivery Client Overrides' button in client/app/Tool Admin failed");
					throw new Exception("Clicked on the 'eDelivery Client Overrides' button in client/app/Tool Admin is failed");
				}

				if (doesElementExist(properties.getProperty("genericindex"), 5)) {
					WebElement geneind = driver.findElement(By.xpath(properties.getProperty("genericindex")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", geneind);
					//waitUntilRetrive();            
					log.logLine(Testname, false, "Clicked on the '3.eArchive/ePresent Generic Index Configuration Over Ride' button in client/app/Tool Admin ");
				} else {
					log.logLine(Testname, true, "Clicking on the '3.eArchive/ePresent Generic Index Configuration Over Ride' button in client/app/Tool Admin failed");
					throw new Exception("Clicked on the '3.eArchive/ePresent Generic Index Configuration Over Ride' button in client/app/Tool Admin  failed");
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
								break;}}		
						if(status.equalsIgnoreCase(" ")){	
							log.logLine(Testname, true, " Generic field doesnot exists in the grid ");
							Genericfield_creation(Testname,"Generic_Field");
						}}}
			}}
	}

	public void Doctype_generatefieldvalidation(String Testname,String Docstart) throws Exception {
		String envi = Initialization.EnvirSite.toUpperCase();
		Thread.sleep(10000);

		if (doesElementExist2(properties.getProperty("Documnentsoption"), 5)) {
			WebElement Deloptn = driver.findElement(By.cssSelector(properties.getProperty("Documnentsoption")));
			String data1=Deloptn.getText();
			if(data1.equalsIgnoreCase("Documents"))
			{((JavascriptExecutor) driver).executeScript("arguments[0].click()", Deloptn);
			Thread.sleep(4000);
			log.logLine(Testname, false, "Clicked on Delivery Options options menu");
			} 
		}
		else {
			log.logLine(Testname, true, "Clicking on Doc type to select the option Blue document from list is failed");
			throw new Exception("Clicking on Doc type to select the option Blue document from list is failed");
		}

		if (doesElementExist2(properties.getProperty("Doctype"), 5)) {
			WebElement doctype = driver.findElement(By.cssSelector(properties.getProperty("Doctype")));
			log.logLine(Testname, false, "Clicking on Document type selection list");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", doctype);
			Thread.sleep(2000);
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
		Thread.sleep(1000);

		if(envi.equalsIgnoreCase("stage")){
			if (doesElementExist2(properties.getProperty("Docstartdate"), 5)) {	    
				WebElement docstart= driver.findElement(By.cssSelector(properties.getProperty("Docstartdate")));
				docstart.clear();
				docstart.sendKeys(Docstart);
				log.logLine(Testname, false, "Entering the Document start date ..");			

			}  else {
				//log.logLine(Testname, false, "Date field doesnot exist for "+envi);	
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
		String available="No";
		log.logLine(Testname, false, "Validation of generate index column heading");		
		List<WebElement> DataCnt= driver.findElements(By.cssSelector("table[role='grid'] thead tr th a"));
		WebElement title = driver.findElement(By.xpath("html/body/form/div[3]/div[2]/div[1]/div[2]/div[5]/div[1]/div/table/thead/tr/th[1]/a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", title);
		for (WebElement lnk:DataCnt) {
			String lnktxt=lnk.getText();
			if(lnktxt.equalsIgnoreCase("Generic_Field")){
				log.logLine(Testname, false, "The validation passed as the 'Generic_field' is present as Grid heading");
				available="Yes";
				break;
			}}
			if(available.equalsIgnoreCase("No")){
				log.logLine(Testname, true, "'Generic_filed' column doesnot exist in Documents header");									
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
			/*	driver.switchTo().defaultContent();
			throw new Exception("Clicking on Fieldindex drop down list and selected the option .. failed");*/
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
}




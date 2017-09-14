package pivotModules;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;

import launchAuto.Initialization;
import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;

public class ArchiveConcatPDF extends Page{

	public ArchiveConcatPDF(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}	
	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}

	public static long startTime;
	public static String Filedname="Consumer ID";
	public static String Oprtr="Equals";
	public static String Txt="SKB002";
	
	
	

	public boolean PDFEnabledCheck(String AccNo, String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(1000);
		driver.switchTo().defaultContent();	    

		/*if (doesElementExist2(properties.getProperty("ModifyTool"), 5)) {
	    	WebElement modtool = driver.findElement(By.cssSelector(properties.getProperty("ModifyTool")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", modtool);

			waitUntilRetrive();			
			log.logLine(Testname, false, "Clicked on the PIVOT eDelivery ModifyTool button in client/app/Tool Admin ");
		} else {
		    log.logLine(Testname, true, "Clicking on the PIVOT eDelivery ModifyTool button in user Admin is failed");
		    throw new Exception("Clicking on the PIVOT eDelivery ModifyTool button in user Admin is failed");
		}*/



		if (doesElementExist2(properties.getProperty("IsPDfEnable"), 5)) {
			WebElement eDelspecidata = driver.findElement(By.cssSelector(properties.getProperty("IsPDfEnable")));

			if (eDelspecidata.getText().equals("Yes")){

				log.logLine(Testname, false, "PDF Concatenation is equal to Yes");
			}else{		    	
				log.logLine(Testname, true, "PDF Concatenation is not equal to Yes");
			}
		} else {
			log.logLine(Testname, true, "PDF Concatenation is not equal to Yes under view pivot information and is failed");
			//throw new Exception("Clicking on the eDelivery Client Overrides tab under view pivot information is failed");

		}


		return true;
	}

	public boolean CombinePDF(String AccNo, String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(1000);
		//driver.switchTo().defaultContent();
		//if(mytime.after(fromtime) && mytime.before(totime))




		Thread.sleep(3000);
		driver.switchTo().frame("iframeContainer");
		if (doesElementExist2(properties.getProperty("Simplesearchtextbox"), 5)) {	    
			WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Simplesearchtextbox")));
			btnsrch.clear();
			//String Comments = test.readInputData("SearchComments", sheetname);
			String Cedula= Initialization.EightDig1;
			btnsrch.sendKeys(Cedula);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);
			//WebElement icon = driver.findElement(By.cssSelector(properties.getProperty("iconsearch")));
			//((JavascriptExecutor) driver).executeScript("arguments[0].click()", icon);

			PleasewaitDisappear();
			log.logLine(Testname, false, "Entering the search comments "+Cedula+" in simple search for verification of the documents");
		} else {
			log.logLine(Testname, true, "Entering the search comments  in simple search for verification of the documents failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the search comments  in simple search for verification of the documents failed");
		}


		if (doesElementExist2(properties.getProperty("iconsearch"), 5)) {	    
			WebElement mgnfierclick = driver.findElement(By.cssSelector(properties.getProperty("iconsearch")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", mgnfierclick);
			Thread.sleep(2000);
			PleasewaitDisappear();		
			log.logLine(Testname, false, "Clicking on search icon of the simple search");
		} else {
			log.logLine(Testname, true, "Clicking on search icon of the simple search is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on search icon of the simple search is failed");
		}

		if (doesElementExist2(properties.getProperty("checkbox1"), 5)) {
			WebElement clickchkbox1 = driver.findElement(By.cssSelector(properties.getProperty("checkbox1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clickchkbox1);
			log.logLine(Testname, false, "Clicked on first checkbox under choose actions in archive search ");
		} else {
			log.logLine(Testname, true, "Clicked on first checkbox under choose actions in archive search  is failed");
			throw new Exception("Clicked on first checkbox under choose actions in archive search is failed");

		}

		if (doesElementExist2(properties.getProperty("checkbox2"), 5)) {
			WebElement clickchkbox2 = driver.findElement(By.cssSelector(properties.getProperty("checkbox2")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clickchkbox2);
			log.logLine(Testname, false, "Clicked on second checkbox under choose actions in archive search ");
		} else {
			log.logLine(Testname, true, "Clicked on second checkbox under choose actions in archive search  is failed");
			throw new Exception("Clicked on second checkbox under choose actions in archive search is failed");

		}

		if (doesElementExist2(properties.getProperty("combinepdfbuton"), 5)) {
			WebElement cmbnebtn = driver.findElement(By.cssSelector(properties.getProperty("combinepdfbuton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", cmbnebtn);
			log.logLine(Testname, false, "Clicked on combined pdf button in  archive search ");
		} else {
			log.logLine(Testname, true, "Clicked on combined pdf button in  archive search  is failed");
			throw new Exception("Clicked on combined pdf button in  archive search is failed");
		}
		
		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("EditOkBtnmsg"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("EditOkBtnmsg")));
			String msg = driver.findElement(By.cssSelector("EditOkBtnmsg")).getText();
			log.logLine(Testname, false, "Reading the message as :"+msg);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(3000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking the Ok button for the selected documents message displayed is successfull");
		} else {
			log.logLine(Testname, true, "Clicking the Ok button for the selected documents message displayed is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking the Ok button for the selected documents message displayed is failed");
		}


		if (doesElementExist(properties.getProperty("EditOkBtn1"), 5)) {	    
			WebElement okbtn = driver.findElement(By.xpath(".//*[@id='modal-pdf-concat-proceed']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(3000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking the Ok button for the selected documents message displayed is successfull");
		} else {
			log.logLine(Testname, true, "Clicking the Ok button for the selected documents message displayed is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking the Ok button for the selected documents message displayed is failed");
		}
		
		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("sucessokbtnmsg"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("sucessokbtnmsg")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(3000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking the Ok button of the documents successfully combined is successful");
		} else {
			log.logLine(Testname, true, "Clicking the Ok button of the documents successfully combined is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking the Ok button of the documents successfully combined is failed");
		}

		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("sucessokbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.xpath(".//*[@id='modal-alert-ok']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(3000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking the Ok button of the documents successfully combined is successful");
		} else {
			log.logLine(Testname, true, "Clicking the Ok button of the documents successfully combined is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking the Ok button of the documents successfully combined is failed");
		}
		
		

		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ss");
		//cal.add(Calendar.MINUTE, 1);
		String currentCombTime = sdf.format(cal.getTime());
		System.out.println( sdf.format(cal.getTime()) );
		log.logLine(Testname, false, "The current system time after clicking on combine button " +currentCombTime);
		String hhmmss[]= currentCombTime.split(":");
		String hh=hhmmss[0];
		String mm=hhmmss[1];
		String ss=hhmmss[2];

		String CombTime = hh+":"+mm;

		Integer hour = Integer.valueOf(hh);
		Integer minutes = Integer.valueOf(mm); //Integer.parseInt( s );
		Integer seconds = Integer.valueOf(ss) + 10;

		String CombinePdfTime= hour+":"+minutes;

		log.logLine(Testname, false, "current system time after clicking on combine button in hh:mm format : " +CombTime);

		driver.switchTo().defaultContent();

		Thread.sleep(15000);

		/* if (doesElementExist2(properties.getProperty("Archives"), 5)) {    
					WebElement arclnk = driver.findElement(By.cssSelector(properties.getProperty("Archives")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", arclnk);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Navigation to Archives page is successfull");
			} else {
 				log.logLine(Testname, true, "Clicking on Archives menu is failed");
 				throw new Exception("Clicking on Archives menu is failed");
			}*/

		if (doesElementExist2(properties.getProperty("CombineDocmntsTab"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("CombineDocmntsTab")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(8000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on the Combine Documents Tab in Archives");
		} else {
			log.logLine(Testname, true, "Clicking on the Combine Documents Tab in Archives is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on the Combine Documents Tab in Archives is failed");
		}

		driver.switchTo().frame("iframeContainer");		    
		Thread.sleep(3000);		

		if (doesElementExist2(properties.getProperty("LatestDocmnt"), 5)) {	    
			String val = driver.findElement(By.cssSelector(properties.getProperty("LatestDocmnt"))).getText();
			String splitStr[] = val.split(" ");
			String actval = splitStr[1].trim();
			String hrmm[] = actval.split(":");
			String hh1=hrmm[0].trim();
			String mm1=hrmm[1].trim();
			String ss1=hrmm[2].trim();
			String requesttime= hh1+":"+mm1;


			log.logLine(Testname, false, "The requested time of the view document in combine documents in hh:mm:ss format : " +actval);
			log.logLine(Testname, false, "The requested time of the view document in combine documents hh:mm format: " +requesttime);
			System.out.println(requesttime);
			//if (actval.equals(currentCombTime.trim())){
			if (requesttime.equals(CombTime.trim())){  


				log.logLine(Testname, false, "The requested time of the view document in combine documents matches with the combine time and document is successfully combined");

			}else {
				//log.logLine(Testname, true, "Document combining failed");
				if (seconds > 60 ){

					if (minutes == 59){
						hour++; 
						minutes = 00;

						log.logLine(Testname, false, "The requested time of the view document  after adding 10 seconds "+CombinePdfTime);

						if (requesttime.equals(CombinePdfTime.trim())){
							log.logLine(Testname, false, "The time matches after adding 10 seconds ");
						}else {
							log.logLine(Testname, true, "The time does not matches after adding 10 seconds ");
						}

					}else{
						minutes++;
						if (requesttime.equals(CombinePdfTime.trim())){
							log.logLine(Testname, false, "The time matches after incrementing minutes");
						}else {
							log.logLine(Testname, true, "The time does not matches after incrementing minutes");
						}
					}

				}


			}
		}   

		if (doesElementExist2(properties.getProperty("Status"), 5)) {
			Actions action = new Actions(driver);
			List<WebElement> tre = driver.findElements(By.cssSelector(properties.getProperty("Status")));
			for (WebElement lnk:tre) {
				if (lnk.getText().equals("Failed")){
					// action.click(lnk).perform();
					log.logLine(Testname, false, "Combining documents is not successful");
					break;
				} else if (lnk.getText().equals("Succeeded")){
					// action.click(lnk).perform();
					log.logLine(Testname, false, "Combining documents is successful and status is Succeeded");
					break;
				}
			}
		}




		return true;

	}	

	public boolean SkyblueCombinePDF(String AccNo, String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(4000);

		driver.switchTo().frame("iframeContainer");
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


		Actions action=new Actions(driver);
		if (doesElementExist2(properties.getProperty("Fieldbutton"), 5)) {	    
			WebElement optr = driver.findElement(By.cssSelector(properties.getProperty("Fieldbutton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", optr);
			Thread.sleep(1000);

			log.logLine(Testname, false, "Clicking on Seacrh criteria Field drop down list in advance search");


			
			if (doesElementExist2(properties.getProperty("SelFieltOpt"), 5)) {

				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("SelFieltOpt")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(Filedname)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						log.logLine(Testname, false, "Selecting the Field option "+Filedname+" from the dropdown");
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
					if (lnk.getText().equals(Oprtr)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(500);
						log.logLine(Testname, false, "Selecting the operator option as "+Oprtr+" from the dropdown");
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

		

		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("Txtfld"), 5)) {	  
			WebElement txt = driver.findElement(By.cssSelector(properties.getProperty("Txtfld")));
			txt.sendKeys(Txt);
			log.logLine(Testname, false, "Entered Value in text field is:" +Txt);			
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
		

		if (doesElementExist2(properties.getProperty("checkbox1"), 5)) {
			WebElement clickchkbox1 = driver.findElement(By.cssSelector(properties.getProperty("checkbox1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clickchkbox1);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on first checkbox under choose actions in archive search ");
		} else {
			log.logLine(Testname, true, "Clicked on first checkbox under choose actions in archive search  is failed");
			throw new Exception("Clicked on first checkbox under choose actions in archive search is failed");

		}

		if (doesElementExist2(properties.getProperty("checkbox2"), 5)) {
			WebElement clickchkbox2 = driver.findElement(By.cssSelector(properties.getProperty("checkbox2")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clickchkbox2);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked on second checkbox under choose actions in archive search ");
		} else {
			log.logLine(Testname, true, "Clicked on second checkbox under choose actions in archive search  is failed");
			throw new Exception("Clicked on second checkbox under choose actions in archive search is failed");

		}
		
		Thread.sleep(6000);
		if (doesElementExist2(properties.getProperty("Helpbtn"), 5)) {
			WebElement clickchkbox1 = driver.findElement(By.cssSelector(properties.getProperty("Helpbtn")));
			action.moveToElement(clickchkbox1).perform();
			//clickchkbox1.click();
			log.logLine(Testname, false, "Clicked on Help Button to read the content");
		} else {
			log.logLine(Testname, true, "Clicked on Help Button to read the content is failed");
			throw new Exception("Clicked on Help Button to read the content is failed");

		}
		
		Thread.sleep(6000);
		if (doesElementExist2(properties.getProperty("Helpmsg"), 5)) {
			String msg = driver.findElement(By.cssSelector(properties.getProperty("Helpmsg"))).getText();
			Thread.sleep(4000);
			log.logLine(Testname, false, "Reading the message as :"+msg);
		} else {
			log.logLine(Testname, true, "Reading the message is failed");
			throw new Exception("Reading the message is failed");

		}

		if (doesElementExist2(properties.getProperty("combinepdfbuton"), 5)) {
			WebElement cmbnebtn = driver.findElement(By.cssSelector(properties.getProperty("combinepdfbuton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", cmbnebtn);
			log.logLine(Testname, false, "Clicked on combined pdf button in  archive search ");
		} else {
			log.logLine(Testname, true, "Clicked on combined pdf button in  archive search  is failed");
			throw new Exception("Clicked on combined pdf button in  archive search is failed");
		}

		
		Thread.sleep(5000);
		if (doesElementExist2(properties.getProperty("EditOkBtnmsg"), 5)) {	    
			WebElement prcdbtn = driver.findElement(By.cssSelector(properties.getProperty("EditOkBtnmsg")));
			String msg = driver.findElement(By.cssSelector(properties.getProperty("EditOkBtnmsg"))).getText();
			log.logLine(Testname, false, "Reading the message as :"+msg);
			Thread.sleep(3000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking the Ok button for the selected documents message displayed is successfull");
		} else {
			log.logLine(Testname, true, "Clicking the Ok button for the selected documents message displayed is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking the Ok button for the selected documents message displayed is failed");
		}


		if (doesElementExist(properties.getProperty("EditOkBtn1"), 5)) {	    
			WebElement okbtn = driver.findElement(By.xpath(".//*[@id='modal-pdf-concat-proceed']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(3000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking the Ok button for the selected documents message displayed is successfull");
		} else {
			log.logLine(Testname, true, "Clicking the Ok button for the selected documents message displayed is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking the Ok button for the selected documents message displayed is failed");
		}
		
		Thread.sleep(2000);
		if (doesElementExist2(properties.getProperty("sucessokbtnmsg"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("sucessokbtnmsg")));
			String msg = driver.findElement(By.cssSelector(properties.getProperty("sucessokbtnmsg"))).getText();
			log.logLine(Testname, false, "Reading the message as :"+msg);
			Thread.sleep(3000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking the Ok button of the documents successfully combined is successful");
		} else {
			log.logLine(Testname, true, "Clicking the Ok button of the documents successfully combined is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking the Ok button of the documents successfully combined is failed");
		}

		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("sucessokbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.xpath(".//*[@id='modal-alert-ok']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(3000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking the Ok button of the documents successfully combined is successful");
		} else {
			log.logLine(Testname, true, "Clicking the Ok button of the documents successfully combined is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking the Ok button of the documents successfully combined is failed");
		}
		
		



		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ss");
		//cal.add(Calendar.MINUTE, 1);
		String currentCombTime = sdf.format(cal.getTime());
		System.out.println( sdf.format(cal.getTime()) );
		log.logLine(Testname, false, "The current system time after clicking on combine button " +currentCombTime);
		String hhmmss[]= currentCombTime.split(":");
		String hh=hhmmss[0];
		String mm=hhmmss[1];
		String ss=hhmmss[2];

		String CombTime = hh+":"+mm;

		Integer hour = Integer.valueOf(hh);
		Integer minutes = Integer.valueOf(mm); //Integer.parseInt( s );
		Integer seconds = Integer.valueOf(ss) + 10;

		String CombinePdfTime= hour+":"+minutes;

		log.logLine(Testname, false, "current system time after clicking on combine button in hh:mm format : " +CombTime);

		driver.switchTo().defaultContent();

		Thread.sleep(15000);

		/* if (doesElementExist2(properties.getProperty("Archives"), 5)) {    
					WebElement arclnk = driver.findElement(By.cssSelector(properties.getProperty("Archives")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", arclnk);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Navigation to Archives page is successfull");
			} else {
 				log.logLine(Testname, true, "Clicking on Archives menu is failed");
 				throw new Exception("Clicking on Archives menu is failed");
			}*/

		if (doesElementExist2(properties.getProperty("CombineDocmntsTab"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("CombineDocmntsTab")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			Thread.sleep(8000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on the Combine Documents Tab in Archives");
		} else {
			log.logLine(Testname, true, "Clicking on the Combine Documents Tab in Archives is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Clicking on the Combine Documents Tab in Archives is failed");
		}

		driver.switchTo().frame("iframeContainer");		    
		Thread.sleep(3000);		

		if (doesElementExist2(properties.getProperty("LatestDocmnt"), 5)) {	    
			String val = driver.findElement(By.cssSelector(properties.getProperty("LatestDocmnt"))).getText();
			String splitStr[] = val.split(" ");
			String actval = splitStr[1].trim();
			String hrmm[] = actval.split(":");
			String hh1=hrmm[0].trim();
			String mm1=hrmm[1].trim();
			String ss1=hrmm[2].trim();
			String requesttime= hh1+":"+mm1;


			log.logLine(Testname, false, "The requested time of the view document in combine documents in hh:mm:ss format : " +actval);
			log.logLine(Testname, false, "The requested time of the view document in combine documents hh:mm format: " +requesttime);
			System.out.println(requesttime);
			//if (actval.equals(currentCombTime.trim())){
			if (requesttime.equals(CombTime.trim())){  


				log.logLine(Testname, false, "The requested time of the view document in combine documents matches with the combine time and document is successfully combined");

			}else {
				//log.logLine(Testname, true, "Document combining failed");
				if (seconds > 60 ){

					if (minutes == 59){
						hour++; 
						minutes = 00;

						log.logLine(Testname, false, "The requested time of the view document  after adding 10 seconds "+CombinePdfTime);

						if (requesttime.equals(CombinePdfTime.trim())){
							log.logLine(Testname, false, "The time matches after adding 10 seconds ");
						}else {
							log.logLine(Testname, true, "The time does not matches after adding 10 seconds ");
						}

					}else{
						minutes++;
						if (requesttime.equals(CombinePdfTime.trim())){
							log.logLine(Testname, false, "The time matches after incrementing minutes");
						}else {
							log.logLine(Testname, true, "The time does not matches after incrementing minutes");
						}
					}

				}


			}
		}   

		if (doesElementExist2(properties.getProperty("Status"), 5)) {
			Actions actions = new Actions(driver);
			List<WebElement> tre = driver.findElements(By.cssSelector(properties.getProperty("Status")));
			for (WebElement lnk:tre) {
				if (lnk.getText().equals("Failed")){
					// action.click(lnk).perform();
					log.logLine(Testname, false, "Combining documents is not successful");
					break;
				} else if (lnk.getText().equals("Succeeded")){
					// action.click(lnk).perform();
					log.logLine(Testname, false, "Combining documents is successful and status is Succeeded");
					break;
				}
			}
		}




		return true;

	}


}




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


public class ServicetypesFunctionality extends Page{

	public ServicetypesFunctionality(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}	
	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}

	String  firstWinHandle=null;

	Actions action = new Actions(driver);

	public boolean Commonfileloader(String Testname) throws Exception {
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


		Actions builder = new Actions(driver);	
		WebElement mnuElement = driver.findElement(By.xpath(properties.getProperty("AdminMenu")));	
		if (doesElementExist(properties.getProperty("AdminMenu"), 10)) {	
			// Move cursor to the Main Menu Element 
			builder.moveToElement(mnuElement).perform();
			// Clicking on the Hidden SubMenu 
			Thread.sleep(1000);
			if (doesElementExist2(properties.getProperty("Adminlnk"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Adminlnk")));
				for (WebElement lnk:selopts) {
					Thread.sleep(1000);
					if (lnk.getText().contains("HA Admin")) {
						Thread.sleep(1000);
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Clicking on Legacy Admin..");	
						break;
					}	
				}

			} else {
				log.logLine(Testname, true, "Clicking on Legacy Admin.. is failed");
				throw new Exception("Clicking on Legacy Admin.. is failed");	
			}
		}
		Thread.sleep(2000);

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

				if (Initialization.EnvirSite.equalsIgnoreCase("test")) {
					if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetExplorer"))) { 
						driver.get("javascript:document.getElementById('overridelink').click();");
					}
				}

				// Wait till the page loads all the elements in it.
				WebElement retelm2 = waitForElement(properties.getProperty("ProfileAdmin"));				

				//Verify User permission
				if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSUPER")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDSITE")) 
						|| (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDCLIENT"))) {					

					if (doesElementExist(properties.getProperty("CFLlink"), 5)) {						
						log.logLine(Testname, false, "Permission Verified: RRD Super, RRD Site & RRD Client users have access to Client/App in Legacy Admin");

					} else {

						log.logLine(Testname, true, "Access denied - RRD Super, RRD Site & RRD Client users does not have access to Client/App in Legacy Admin");
						driver.close();
						driver.switchTo().window(firstWinHandle);
						//return "";
					}
				} else if ((PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTUSER")) || 
						(PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTCLIENTADMIN")) || (PivotSignInOut.Uname.equalsIgnoreCase("PIVOTRTRRDUSER"))) {

					if (doesElementExist(properties.getProperty("CFLlink"), 5)) {

						negativeCase(Testname, firstWinHandle, "", "RRD User, Client Admin & Client User should not have access to Client/App in Legacy Admin");

					} else {

						log.logLine(Testname, false, "Permission Verified: Client Admin, Client User & RRD User does not have permission to access to Client/App in Legacy Admin");
						driver.close();
						driver.switchTo().window(firstWinHandle);
						//return ""; 
					}

				}			

				if (doesElementExist(properties.getProperty("CFLlink"), 5)) {
					WebElement cflmenu = driver.findElement(By.xpath(properties.getProperty("CFLlink")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", cflmenu);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Navigating to Admin - CFL link..");
				} else {
					log.logLine(Testname,true,"Navigating to Admin -  CFL link is failed");	
					negativeCase(Testname, firstWinHandle, "", "Navigating to Admin -  CFL link is failed");					
				}

				Thread.sleep(1000);

				if (doesElementExist(properties.getProperty("Administration"), 5)) {
					WebElement admin = driver.findElement(By.xpath(properties.getProperty("Administration")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", admin);
					Thread.sleep(1000);
					log.logLine(Testname, false, "clicked on Administration link");
				} else {
					log.logLine(Testname,true, "unable to click on adminstration link");
					negativeCase(Testname, firstWinHandle, "", "unable to click on adminstration link");
				}

				Thread.sleep(1000);

				if (doesElementExist(properties.getProperty("config"), 5)) {
					WebElement confi = driver.findElement(By.xpath(properties.getProperty("config")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", confi);
					Thread.sleep(1000);
					log.logLine(Testname, false, "clicked on configuration link");
				} else {
					log.logLine(Testname,true,"unable to click on configuration link");
					negativeCase(Testname, firstWinHandle, "", "unable to click on configuration link");
				}

				Thread.sleep(1000);

				if (doesElementExist2(properties.getProperty("servmain"), 5)) {
					WebElement serv = driver.findElement(By.cssSelector(properties.getProperty("servmain")));
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", serv);
					Thread.sleep(1000);
					log.logLine(Testname, false, "clicked on service maintanence link");
				} else {
					log.logLine(Testname,true,"unable to click on service maintanence link");
					negativeCase(Testname, firstWinHandle, "", "unable to click on service maintanence link");
				}

				Thread.sleep(4000);
				ServiceType_Edits(Testname);
				Thread.sleep(2000);
				Servicename_Edit(Testname);
				driver.close();
				driver.switchTo().window(firstWinHandle);}}

		return true; 
	} 


	private void ServiceType_Edits(String Testname) throws Exception {
		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		if (doesElementExist(properties.getProperty("servicetype"), 5)) {
			WebElement serv = driver.findElement(By.xpath(properties.getProperty("servicetype")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", serv);
			Thread.sleep(1000);
			log.logLine(Testname, false, "clicking  on service types to expand it");
		} else {
			log.logLine(Testname,true,"unable to click on service types");
			negativeCase(Testname, firstWinHandle, "", "unable to click on service types");
		}

		//******Addition of new service type & its validation*********
		String ServicetypeName = test.readColumnData("Servtypnme", sheetname);				
		if (doesElementExist(properties.getProperty("typnme"), 5)) {
			WebElement sernme= driver.findElement(By.xpath(properties.getProperty("typnme")));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sernme);
			Thread.sleep(1000);
			sernme.clear();
			sernme.sendKeys(ServicetypeName);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entered the ServicetypeName  "+ServicetypeName +" in the service type text field in CFL tool");
		} else {
			log.logLine(Testname,true,"Entered the ServicetypeName *** "+ServicetypeName +" *** in the service type text field in CFL tool is failed");
			negativeCase(Testname, firstWinHandle, "","Entered the ServicetypeName  "+ServicetypeName +" in the service type text field in CFL tool is failed");
		}

		if (doesElementExist(properties.getProperty("addnw"), 5)) {
			WebElement addnw= driver.findElement(By.xpath(properties.getProperty("addnw")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", addnw);
			Alert alert = driver.switchTo().alert();
			String txt=alert.getText();
			if(alert.getText().contains("You are about to create a new type, do you want to continue?"))
			{alert.accept();
			log.logLine(Testname, false, "Clicked on addnew service button");
			}

		} else {
			log.logLine(Testname,true, "unable to click on addnew service button");
			negativeCase(Testname, firstWinHandle, "","unable to click on addnew service button");
		}

		Thread.sleep(2000);

		//Editing the newly created service type

		Edit_servicetype(ServicetypeName,Testname);

		//Cancelling the 'edit' option

		if (doesElementExist2(properties.getProperty("Cancel"), 5)) {
			WebElement can= driver.findElement(By.cssSelector(properties.getProperty("Cancel")));
			Highlight(can);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", can);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicked on edit cancel button");
		} else {
			log.logLine(Testname,true, "unable to click on  edit cancel button");
			negativeCase(Testname, firstWinHandle, "","unable to click on  edit cancel button");
		}

		Thread.sleep(2000);
		//Updating the newly created service type

		Edit_servicetype(ServicetypeName,Testname);

		String updservnme = test.readColumnData("updatedservnme", sheetname);				
		if (doesElementExist2(properties.getProperty("edittxtbx"), 5)) {
			WebElement sernme1= driver.findElement(By.cssSelector(properties.getProperty("edittxtbx")));
			sernme1.clear();
			sernme1.sendKeys(updservnme);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entered the updated ServicetypeName  "+updservnme +" in the service type text field in CFL tool");
		} else {
			log.logLine(Testname,true,"Entered the updated  ServicetypeName **** "+updservnme +" *** in the service type text field in CFL tool is failed");
			negativeCase(Testname, firstWinHandle, "","Entered the updated  ServicetypeName  "+updservnme +" in the service type text field in CFL tool is failed");
		}


		if (doesElementExist2(properties.getProperty("Update"), 5)) {
			WebElement upd= driver.findElement(By.cssSelector(properties.getProperty("Update")));
			Highlight(upd);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", upd);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicked on update button");
		} else {
			log.logLine(Testname,true, "unable to click on  update button");
			negativeCase(Testname, firstWinHandle, "","unable to click on  update button");
		}

		Thread.sleep(2000);
		servicetypevalidtion(updservnme,Testname);  

		//Duplicate service type validation

		if (doesElementExist(properties.getProperty("typnme"), 5)) {
			WebElement sernme= driver.findElement(By.xpath(properties.getProperty("typnme")));
			sernme.clear();
			sernme.sendKeys(updservnme);
			Thread.sleep(5000);
			log.logLine(Testname, false, "Entered the ServicetypeName  "+updservnme+" in the service type text field in CFL tool");
		} else {
			log.logLine(Testname,true,"Entered the ServicetypeName *** "+updservnme +" ***in the service type text field in CFL tool is failed");
			negativeCase(Testname, firstWinHandle, "","Entered the ServicetypeName *** "+updservnme +" ***in the service type text field in CFL tool is failed");
		}

		if (doesElementExist(properties.getProperty("addnw"), 5)) {
			WebElement addnw= driver.findElement(By.xpath(properties.getProperty("addnw")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", addnw);
			Alert alert = driver.switchTo().alert();
			String txt=alert.getText();
			if(alert.getText().contains("Service type  is already in use."))
			{alert.accept();
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicking ok button of the duplicate alert message is sucessful");	
			}
		} else {
			log.logLine(Testname,true, "unable to click on addnew service button");
			negativeCase(Testname, firstWinHandle, "","unable to click on addnew service button");
		}

		//Delete service type

		Thread.sleep(2000);
		Deleteservicetype(updservnme,Testname); }


	//Addition of new service name
	private void Servicename_Edit(String Testname)throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		//******Addition of new service name & its validation*********
		if (doesElementExist(properties.getProperty("addnew1"), 5)) {
			WebElement addnw1= driver.findElement(By.xpath(properties.getProperty("addnew1")));
			//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addnw1);
			Thread.sleep(1000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", addnw1	);				
			log.logLine(Testname, false, "Clicked on Add New service name button");
		} else {
			log.logLine(Testname,true, "unable to click on Add New service name button");
			negativeCase(Testname, firstWinHandle, "","unable to click on addnew service button");
		}
		Thread.sleep(1000);
		//	driver.switchTo().alert();
		String Servicenme = test.readColumnData("servicenme", sheetname);	

		if (doesElementExist(properties.getProperty("serverselect"), 5)) {
			WebElement serversele= driver.findElement(By.xpath(properties.getProperty("serverselect")));
			Highlight(serversele);
			serversele.click();
			//((JavascriptExecutor) driver).executeScript("arguments[0].click()", serversele);						
			List<WebElement> selopts = driver.findElements(By.xpath(properties.getProperty("servseleclst")));
			for (WebElement lnk:selopts) {				
				if (lnk.getText().contains("HAWIN-IWAS01")){
					lnk.click();
					//((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Selecting servername 'HAWIN-IWAP01'");	
					break;
				}else if (lnk.getText().contains("HAWIN-IWAP01")){
					lnk.click();
					//((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Selecting servername 'HAWIN-IWAP01'");	
					break;
				}
			}

		} else {
			log.logLine(Testname, true, "Clicking on serverselectlist failed");
			throw new Exception("Clicking on serverselectlist failed");	
		}

		Thread.sleep(1000);

		if (doesElementExist(properties.getProperty("servnmetxt"), 5)) {
			WebElement sernme= driver.findElement(By.xpath(properties.getProperty("servnmetxt")));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sernme);
			Thread.sleep(1000);
			sernme.clear();
			sernme.sendKeys(Servicenme);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Entered the ServiceName  "+Servicenme+" in the service name text field in CFL tool");
		} else {
			log.logLine(Testname,true,"Entered the ServiceName *** "+Servicenme+" *** in the service name text field in CFL tool is failed");
			negativeCase(Testname, firstWinHandle, "","Entered the ServicetypeName  "+Servicenme +" in the service type text field in CFL tool is failed");
		}

		Thread.sleep(2000);

		if (doesElementExist(properties.getProperty("save"), 5)) {
			WebElement sve= driver.findElement(By.xpath(properties.getProperty("save")));
			Highlight(sve);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", sve);
			Thread.sleep(2000);
			Alert alert = driver.switchTo().alert();
			String txt=alert.getText();
			if(alert.getText().contains("You are about to create a service maintenance, do you want to continue?"))
			{alert.accept();
			log.logLine(Testname, false, "Clicking ok button of save alert is sucessful");	
			}
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicked on save button");
		} else {
			log.logLine(Testname,true, "Unable to Click on save button");
			negativeCase(Testname, firstWinHandle, "","Unable to Click on save button");
		}

		Thread.sleep(5000);

		servicenamevalidtion(Servicenme,Testname);

		Updatevalidation(Testname,Servicenme);

		Thread.sleep(2000);
		Deletevalidation(Testname,Servicenme);

	}


	private void Deletevalidation(String Testname, String value) throws Exception {

		if (doesElementExist2(properties.getProperty("deletebtn"), 5)) {
			WebElement dele= driver.findElement(By.cssSelector(properties.getProperty("deletebtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", dele);				
			log.logLine(Testname, false, "Clicked on delete button");
			Alert alert = driver.switchTo().alert();
			alert.accept();

		} else {
			log.logLine(Testname,true, "unable to click on delete button");
			negativeCase(Testname, firstWinHandle, "","unable to click on delete button");
		}


		String[] Sort1 = new String[20];
		String  row = "tr";
		List<WebElement> DataCnt=driver.findElements(By.xpath("html/body/form/div[6]/div/div/div[1]/div[2]/div[3]/table/tbody/tr"));
		if (DataCnt.size()==0){
			log.logLine(Testname, false, "There are no service type to display");
		}
		else{
			if(doesElementExist(properties.getProperty("acc"), 5)){
				int ab=DataCnt.size();
				for(int i = 0; i < DataCnt.size(); i++) {
					Sort1[i] = driver.findElement(By.cssSelector("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_dgServicesInfo'] tbody "+row+" td+td span")).getText();
					if(Sort1[i].equalsIgnoreCase(value)){
						log.logLine(Testname, false, "The delete validation is failed, as the service name is not yet deleted from the table");
					}
					row = row + "+tr";	
				}
				log.logLine(Testname, false, "The delete validation is successful, as the service name is deleted from the table");
			}
		}
	}

	private void Updatevalidation(String Testname,String value) throws Exception{

		if (doesElementExist(properties.getProperty("servtypeselect"), 5)) {
			WebElement serversele= driver.findElement(By.xpath(properties.getProperty("servtypeselect")));
			serversele.click();
			//((JavascriptExecutor) driver).executeScript("arguments[0].click()", serversele);	
			List<WebElement> selopts = driver.findElements(By.xpath(properties.getProperty("servtyplst")));
			for (WebElement lnk:selopts) {
				Thread.sleep(1000);
				if (lnk.getText().contains("Uncompress")) {
					Thread.sleep(1000);
					lnk.click();
					//((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
					Thread.sleep(1000);
					log.logLine(Testname, false, "Selecting servicetype 'Uncompress'");	
					break;
				}	
			}

		} else {
			log.logLine(Testname, true, "Clicking on ServiceTypelst failed");
			throw new Exception("Clicking on ServiceTypelst failed");	
		}

		//changing 'enable' status from 'No' to 'Yes'

		String[] Sort1 = new String[15];
		String  row = "tr";
		List<WebElement> DataCnt=driver.findElements(By.cssSelector("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_dgServicesInfo'] tbody tr"));
		if (DataCnt.size()==0){
			log.logLine(Testname, false, "There are no service type to display");
		}
		else{
			if(doesElementExist(properties.getProperty("account1"), 5)){
				for(int i = 0; i < DataCnt.size(); i++) {
					Sort1[i] = driver.findElement(By.cssSelector("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_dgServicesInfo'] tbody "+row+" td+td span")).getText();
					if(Sort1[i].equalsIgnoreCase(value)){			
						WebElement enableval= driver.findElement(By.cssSelector("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_dgServicesInfo'] tbody "+row+" td table tbody tr td input"));
						String chktxt=enableval.getText();
						if(!enableval.isSelected())
						{ enableval.click();
						//((JavascriptExecutor) driver).executeScript("arguments[0].click()", enableval);
						log.logLine(Testname, false, "Modified the enabled status to 'Yes'");
						}
						break;
					}

					row = row + "+tr";}
			}}

		if (doesElementExist2(properties.getProperty("updatebtn"), 5)) {
			WebElement update= driver.findElement(By.cssSelector(properties.getProperty("updatebtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", update);				
			log.logLine(Testname, false, "Clicked on update button");
		} else {
			log.logLine(Testname,true, "unable to click on update button");
			negativeCase(Testname, firstWinHandle, "","unable to click on update button");
		}

		Thread.sleep(6000);
		if (doesElementExist2(properties.getProperty("updatesuccessful"), 5)) {
			WebElement update= driver.findElement(By.cssSelector(properties.getProperty("updatesuccessful")));						
			log.logLine(Testname, false, "The title has been changed to  "+update.getText());
		} else {
			log.logLine(Testname,true, "The title has not been changed ");
			negativeCase(Testname, firstWinHandle, "","The title has not been changed");
		}

	}

	public void Highlight(WebElement choseacts) throws Exception{
		for (int i = 0; i < 2; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript( "arguments[0].setAttribute('style', arguments[1]);", choseacts , "color: red; border: 5px solid red;");
			Thread.sleep(1000);
			js.executeScript( "arguments[0].setAttribute('style', arguments[1]);", choseacts, "");
		}
	}

	private String Edit_servicetype(String value, String Testname) throws Exception {
		String[] Sort = new String[15];
		String  row = "tr";
		List<WebElement> DataCnt= driver.findElements(By.cssSelector("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_grdvServiceType'] tbody tr"));
		if (DataCnt.size()==0){
			log.logLine(Testname, false, "There are no service type to display");
		}
		else{
			if(doesElementExist(properties.getProperty("account"), 5)){
				for(int i = 0; i < DataCnt.size(); i++) {
					Sort[i] = driver.findElement(By.cssSelector("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_grdvServiceType'] tbody "+row+" td+td span")).getText();
					if(Sort[i].equalsIgnoreCase(value)){
						WebElement edit= driver.findElement(By.cssSelector("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_grdvServiceType'] tbody "+row+" td input[value='Edit']"));
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", edit);
						Thread.sleep(5000);
						log.logLine(Testname, false, "Clicked on the edit button of newly created service type");
					} 
					row = row + "+tr";		}
			}

		}
		return row;}

	private void Deleteservicetype(String value, String Testname) throws Exception {
		String[] Sort1 = new String[15];
		String  row = "tr";
		List<WebElement> DataCnt= driver.findElements(By.cssSelector("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_grdvServiceType'] tbody tr"));
		if (DataCnt.size()==0){
			log.logLine(Testname, false, "There are no service type to display");
		}
		else{
			if(doesElementExist(properties.getProperty("account"), 5)){
				for(int i = 0; i < DataCnt.size(); i++) {
					Sort1[i] = driver.findElement(By.cssSelector("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_grdvServiceType'] tbody "+row+" td+td span")).getText();
					if(Sort1[i].equalsIgnoreCase(value)){
						WebElement del= driver.findElement(By.cssSelector("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_grdvServiceType'] tbody "+row+" td input[value='Delete']"));
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", del);
						Thread.sleep(5000);
						log.logLine(Testname, false, "Clicked on delete button");
						Alert alert = driver.switchTo().alert();
						String txt=alert.getText();
						if(alert.getText().contains("Are you sure you want to delete "))
						{alert.accept();
						log.logLine(Testname, false, "Clicking ok button of delete alert is sucessful");	
						}
					}


					row = row + "+tr";	}
			}
		}
	}


	private String servicetypevalidtion(String value, String Testname) throws Exception {
		String[] Sort1 = new String[15];
		String  row = "tr";
		List<WebElement> DataCnt=driver.findElements(By.cssSelector("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_grdvServiceType'] tbody tr"));
		if (DataCnt.size()==0){
			log.logLine(Testname, false, "There are no service type to display");
		}
		else{
			if(doesElementExist(properties.getProperty("account"), 5)){
				for(int i = 0; i < DataCnt.size(); i++) {
					Sort1[i] = driver.findElement(By.cssSelector("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_grdvServiceType'] tbody "+row+" td+td span")).getText();
					if(Sort1[i].equalsIgnoreCase(value)){
						log.logLine(Testname, false, "The validation passed as the servicetype added is in the list "+ Sort1[i]);
					}
					row = row + "+tr";	
				}

			}
		}
		return row;
	}

	private String servicenamevalidtion(String value, String Testname) throws Exception {
		String[] Sort1 = new String[15];
		String  row = "tr";
		List<WebElement> DataCnt=driver.findElements(By.cssSelector("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_dgServicesInfo'] tbody tr"));
		if (DataCnt.size()==0){
			log.logLine(Testname, false, "There are no service type to display");
		}
		else{
			if(doesElementExist(properties.getProperty("account1"), 5)){
				for(int i = 0; i < DataCnt.size(); i++) {
					Sort1[i] = driver.findElement(By.cssSelector("table[id='ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder1_dgServicesInfo'] tbody "+row+" td+td span")).getText();
					if(Sort1[i].equalsIgnoreCase(value)){
						log.logLine(Testname, false, "The validation passed as the servicename added is in the list "+ Sort1[i]);
						break;
					}
					row = row + "+tr";	
				}

			}
		}
		return row;
	}


}
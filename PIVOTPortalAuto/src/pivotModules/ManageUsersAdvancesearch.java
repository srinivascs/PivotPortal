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

public class ManageUsersAdvancesearch extends Page{

	public ManageUsersAdvancesearch(WebDriver driver, Log log) throws InvalidFormatException, IOException {
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
	String uid=null;
	String uid1=null;
	String useridcol=null;
	String unme=null;
	String unme1=null;
	String usertypecol=null;
	String utyp=null;
	String utyp1=null;
	String ssoreq=null;
	String data=null;



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
						Thread.sleep(10000);
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

		Advancesearch_functionality(Testname);

		Thread.sleep(3000);

		driver.close();
		driver.switchTo().window(firstWinHandle);
		return true;
	}



	private void clientselection(String Testname,String field)throws Exception {
		Actions builder = new Actions(driver); 
		if (doesElementExist(properties.getProperty("clientlistselect"), 10)) {
			WebElement mnuElement = driver.findElement(By.xpath(properties.getProperty("clientlistselect")));
			builder.moveToElement(mnuElement).perform();    
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",mnuElement);
			if (doesElementExist(properties.getProperty("clientlist"), 5)) {
				List<WebElement> selopts = driver.findElements(By.xpath(properties.getProperty("clientlist")));
				for (WebElement lnk:selopts) {
					data=lnk.getText();
					if (lnk.getText().equalsIgnoreCase(field)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						log.logLine(Testname, false, "We are now selecting the client "+field);                
						break;
					}                                
				}

			} else {
				log.logLine(Testname, true, "selecting the client "+field+" failed");
				throw new Exception("selecting the client "+field+" failed");	
			}           
		}else {
			negativeCase(Testname, firstWinHandle, "",  "All clients listbox doesnot exists");				
		}  
	}

	private void SC_Field(String Testname,String fi)throws Exception {
		Actions builder = new Actions(driver);
		if (doesElementExist(properties.getProperty("Field"), 10)) {
			WebElement mnuElement = driver.findElement(By.xpath(properties.getProperty("Field")));
			builder.moveToElement(mnuElement).perform();    
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",mnuElement);
			if (doesElementExist2(properties.getProperty("fieldlnk"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("fieldlnk")));
				int a=selopts.size();
				for (WebElement lnk:selopts) {
					data=lnk.getText();
					if (lnk.getText().equalsIgnoreCase(fi)) {					
						lnk.click();
						/*if(fi.equalsIgnoreCase("Pivot Tool")){
							lnk.click();}*/
						Thread.sleep(2000);
						log.logLine(Testname, false, "selecting on the field "+fi);                
						break;
					}                                
				}

			} else {
				log.logLine(Testname, true, "selecting on the field "+fi+" failed");
				throw new Exception("selecting on the field "+fi+" failed");	
			}           
		}else {
			negativeCase(Testname, firstWinHandle, "",  "Field listbox doesnot exists");				
		} 
	}

	private void Advancesearch(String Testname)throws Exception {
		if (doesElementExist2(properties.getProperty("advsrch"), 5)) {
			WebElement advsrc = driver.findElement(By.cssSelector(properties.getProperty("advsrch")));			
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", advsrc);
			log.logLine(Testname, false, "Clicking on Advance search button..");	

		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking on Advance search button..failed");		
		}

	}

	private void Advancesearch_functionality(String Testname)throws Exception {
		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();


		//No. of data displayed using 'allclients' and 'ABC company'
		Advancesearch(Testname);
		Thread.sleep(2000);
		String client=test.readColumnData("client", sheetname);
		clientselection(Testname,client);	
		String fiel=test.readColumnData("fieldname", sheetname);
		String tool=test.readColumnData("pivottool", sheetname);
		SC_Field(Testname,fiel);
		Thread.sleep(2000);
		pivottool_Text(Testname,tool);
		Thread.sleep(2000);	
		searchbtn(Testname);
		Thread.sleep(5000);
		int clientval=dataingrid(Testname);
		log.logLine(Testname, false, "No. of records displayed in the grid with the client "+client+" and field "+fiel+" is "+clientval);
		Thread.sleep(1000);
		Advancesearch(Testname);
		Thread.sleep(2000);
		clearbutton(Testname);
		Thread.sleep(2000);
		String client1=test.readColumnData("client1", sheetname);
		clientselection(Testname,client1);	
		SC_Field(Testname,fiel);
		Thread.sleep(1000);
		pivottool_Text(Testname,tool);
		Thread.sleep(2000);
		searchbtn(Testname);
		Thread.sleep(5000);
		int allclientval=dataingrid(Testname);
		log.logLine(Testname, false, "No. of records displayed in the grid with the client "+client1+" and field "+fiel+" is "+allclientval);
		Recordvalidations(Testname,clientval,allclientval,client);


		//Advance Search validation with Clear button
		log.logLine(Testname, false, "*******Advance Search validation with Clear button******");
		Advancesearch(Testname);
		Thread.sleep(2000);
		SC_Field(Testname,"User ID");
		Thread.sleep(2000);
		SC_operator(Testname,"Starts With");
		Thread.sleep(1000);
		uid = test.readColumnData("ID", sheetname);
		SC_txt(Testname,uid);
		Thread.sleep(1000);
		includeinactv(Testname);
		Thread.sleep(1000);
		clearbutton(Testname);
		Thread.sleep(3000);

		//UserID positive validation with 'startswith'
		log.logLine(Testname, false, "*******UserID positive validation with 'startswith'******");
		//Advancesearch(Testname);
		SC_Field(Testname,"User ID");
		Thread.sleep(2000);
		SC_operator(Testname,"Starts With");
		Thread.sleep(1000);
		uid = test.readColumnData("ID", sheetname);
		SC_txt(Testname,uid);
		Thread.sleep(1000);
		includeinactv(Testname);
		Thread.sleep(1000);
		searchbtn(Testname);
		Thread.sleep(5000);
		useridcol = test.readColumnData("useridcolumn", sheetname);
		advancesrchValidation_startswith(uid,Testname,useridcol);


		//UserID Negative validation with 'Equals'
		log.logLine(Testname, false, "*******UserID negative validation with 'Equals'******");
		Advancesearch(Testname);
		Thread.sleep(2000);
		SC_Field(Testname,"User ID");
		Thread.sleep(3000);
		SC_operator(Testname,"Equals");
		Thread.sleep(1000);
		SC_txt(Testname,uid);
		Thread.sleep(1000);
		searchbtn(Testname);
		Thread.sleep(2000);
		useridcol = test.readColumnData("useridcolumn", sheetname);
		negativeadvancesrchValidation_equals(uid,Testname,useridcol);

		//UserID Positive validation  with 'Equals'
		log.logLine(Testname, false, "*******UserID positive validation with 'Equals'******");
		Advancesearch(Testname);
		Thread.sleep(2000);
		SC_Field(Testname,"User ID");
		Thread.sleep(1000);
		SC_operator(Testname,"Equals");
		Thread.sleep(1000);
		uid1 = test.readColumnData("UserID1", sheetname);
		SC_txt(Testname,uid1);
		Thread.sleep(1000);
		searchbtn(Testname);
		Thread.sleep(2000);
		useridcol = test.readColumnData("useridcolumn", sheetname);
		positiveadvancesrchValidation_equals(uid1,Testname,useridcol);

		//Username positive validation with 'startswith'
		log.logLine(Testname, false, "*******Username positive validation with 'startswith'******");
		Advancesearch(Testname);
		Thread.sleep(2000);
		SC_Field(Testname,"User Name");
		Thread.sleep(1000);
		SC_operator(Testname,"Starts With");
		Thread.sleep(1000);
		unme= test.readColumnData("Name", sheetname);
		SC_txt(Testname,unme);
		Thread.sleep(1000);
		searchbtn(Testname);
		Thread.sleep(5000);
		advancesrchValidation1_startswith(unme,Testname);

		//UserName Negative validation with 'Equals'
		log.logLine(Testname, false, "*******UserName negative validation with 'Equals'******");
		Advancesearch(Testname);
		Thread.sleep(2000);
		SC_Field(Testname,"User Name");
		Thread.sleep(1000);
		SC_operator(Testname,"Equals");
		Thread.sleep(2000);
		unme= test.readColumnData("Name", sheetname);
		SC_txt(Testname,"jo");
		Thread.sleep(1000);
		searchbtn(Testname);
		Thread.sleep(5000);
		negativeadvancesrchValidation1_equals("jo",Testname);


		//UserName Positive validation  with 'Equals'
		log.logLine(Testname, false, "*******UserName positive validation with 'Equals'******");
		Advancesearch(Testname);
		Thread.sleep(2000);
		SC_Field(Testname,"User Name");
		Thread.sleep(1000);
		SC_operator(Testname,"Equals");
		Thread.sleep(1000);
		unme1 = test.readColumnData("UserName1", sheetname);
		SC_txt(Testname,unme1);
		Thread.sleep(1000);
		searchbtn(Testname);
		Thread.sleep(5000);
		positiveadvancesrchValidation1_equals(unme1,Testname);


		//UserType  validation 
		log.logLine(Testname, false, "*******UserType validation : Client Admin******");
		Advancesearch(Testname);
		Thread.sleep(2000);
		SC_Field(Testname,"User Type");
		Thread.sleep(1000);
		SC_operator(Testname,"Equals");
		Thread.sleep(1000);
		utyp = test.readColumnData("UserType", sheetname);
		System.out.println(utyp);
		Thread.sleep(1000);
		usertype_Text(Testname,utyp);
		Thread.sleep(2000);
		searchbtn(Testname);
		Thread.sleep(10000);
		usertypecol = test.readColumnData("usertypecolumn", sheetname);
		Thread.sleep(2000);
		positiveadvancesrchValidation_equals(utyp,Testname,usertypecol);

		//UserType  validation 
		log.logLine(Testname, false, "*******UserType validation : Client User******");
		Advancesearch(Testname);
		Thread.sleep(2000);
		SC_Field(Testname,"User Type");
		Thread.sleep(1000);
		SC_operator(Testname,"Equals");
		utyp = test.readColumnData("UserType1", sheetname);
		System.out.println(utyp);
		usertype_Text(Testname,utyp);
		Thread.sleep(2000);
		searchbtn(Testname);
		Thread.sleep(10000);
		usertypecol = test.readColumnData("usertypecolumn", sheetname);
		positiveadvancesrchValidation_equals(utyp,Testname,usertypecol);

		//UserType  validation 
		log.logLine(Testname, false, "*******UserType validation : RRD Client Admin******");
		Advancesearch(Testname);
		Thread.sleep(2000);
		SC_Field(Testname,"User Type");
		Thread.sleep(1000);
		SC_operator(Testname,"Equals");
		utyp = test.readColumnData("UserType2", sheetname);
		System.out.println(utyp);
		usertype_Text(Testname,utyp);
		Thread.sleep(2000);
		searchbtn(Testname);
		Thread.sleep(12000);
		usertypecol = test.readColumnData("usertypecolumn", sheetname);
		positiveadvancesrchValidation_equals(utyp,Testname,usertypecol);

		//UserType  validation 
		log.logLine(Testname, false, "*******UserType validation : RRD Super Admin******");
		Advancesearch(Testname);
		Thread.sleep(2000);
		SC_Field(Testname,"User Type");
		Thread.sleep(1000);
		SC_operator(Testname,"Equals");
		Thread.sleep(1000);
		utyp = test.readColumnData("UserType4", sheetname);
		System.out.println(utyp);
		usertype_Text(Testname,utyp);
		Thread.sleep(3000);
		searchbtn(Testname);
		Thread.sleep(12000);
		usertypecol = test.readColumnData("usertypecolumn", sheetname);
		positiveadvancesrchValidation_equals(utyp,Testname,usertypecol);

		//UserType  validation 
		log.logLine(Testname, false, "*******UserType validation : RRD User******");
		Advancesearch(Testname);
		Thread.sleep(2000);
		SC_Field(Testname,"User Type");
		Thread.sleep(1000);
		SC_operator(Testname,"Equals");
		Thread.sleep(1000);
		utyp = test.readColumnData("UserType5", sheetname);
		System.out.println(utyp);
		usertype_Text(Testname,utyp);
		Thread.sleep(2000);
		searchbtn(Testname);
		Thread.sleep(12000);
		usertypecol = test.readColumnData("usertypecolumn", sheetname);
		positiveadvancesrchValidation_equals(utyp,Testname,usertypecol);


		/*//SSO required 'True'....
		log.logLine(Testname, false, "*******SSO required : True******");
		Advancesearch(Testname);
		Thread.sleep(2000);
		SC_Field(Testname,"SSO Required");
		Thread.sleep(3000);
		SC_operator(Testname,"Equals");
		Thread.sleep(2000);
		SSOreq_Text(Testname,"TRUE");
		Thread.sleep(2000);
		//includeinactv(Testname);
		searchbtn(Testname);
		Thread.sleep(10000);
		advancesrch_SSOReq_Validation(Testname,"Require SSO: true","tr");
		Thread.sleep(1000);
		advancesrch_SSOReq_Validation(Testname,"Require SSO: true","tr+tr+tr+tr+tr");
		Thread.sleep(1000);


	//SSO required 'False'....
		log.logLine(Testname, false, "*******SSO required : False******");
		Advancesearch(Testname);
		Thread.sleep(2000);
		SC_Field(Testname,"SSO Required");
		Thread.sleep(3000);
		SC_operator(Testname,"Equals");
		Thread.sleep(3000);
		SSOreq_Text(Testname,"False");
		Thread.sleep(4000);
		includeinactv(Testname);
		searchbtn(Testname);
		Thread.sleep(8000);
		advancesrch_SSOReq_Validation(Testname,"Require SSO: false","tr");
		Thread.sleep(1000);
		advancesrch_SSOReq_Validation(Testname,"Require SSO: false","tr+tr+tr+tr+tr");
		Thread.sleep(1000);*/
	}

	private int dataingrid(String Testname) throws Exception {
		int value=0;
		String[] Sort1 = new String[50];
		String  row = "tr";
		List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='userGrid']/table/tbody/tr"));
		if (DataCnt.size()!=0){
			if(doesElementExist(properties.getProperty("itemdata"), 5)){
				String noele = driver.findElement(By.xpath(properties.getProperty("itemdata"))).getText(); 
				String arr[] = noele.split("of");
				String Fin=arr[1].trim();
				String abc[]=Fin.split(" ");
				String Final=abc[0].trim();
				value=Integer.parseInt(Final);

			}
			else {
				log.logLine(Testname, true, "No. of records in the grid statement didnot display");
			}

		}
		return value;

	}

	private void advancesrch_SSOReq_Validation(String Testname,String status,String row) throws Exception{
		String[] Sort1 = new String[50];		
		List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='userGrid']/table/tbody/tr"));
		if (DataCnt.size()==0){
			if(doesElementExist(properties.getProperty("noitem"), 5)){
				String noele = driver.findElement(By.xpath(properties.getProperty("noitem"))).getText(); 
				if (noele.equalsIgnoreCase("No items to display")){
					log.logLine(Testname, false, "Sorry No data is there to display");}
			}
		}
		else{Actions builder = new Actions(driver);
		if(doesElementExist(properties.getProperty("accountno"), 5)){		
			WebElement infoicon= driver.findElement(By.cssSelector("div[id='userGrid'] table tbody tr+ "+row+" td+td+td+td+td a"));
			//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",infoicon);
			Highlight(infoicon);
			builder.moveToElement(infoicon).perform(); 
			Thread.sleep(1000);
			/*List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("infolst")));
			for (WebElement lnk:selopts) {
				if (lnk.getText().contains("Require SSO:")) {*/					
			WebElement inf= driver.findElement(By.cssSelector("div[id*='webuiPopover'] div[class='webui-popover-content'] p"));
			String ptxt=inf.getText();
			String[] contentSplits =ptxt.split("\n");
			if (contentSplits[2].equalsIgnoreCase(status)) {					
				log.logLine(Testname, false, "SSO required validation is successful as the "+contentSplits[2]+" matches with the search data "+status);                
			}  
			/*WebElement infoicon1= driver.findElement(By.cssSelector("div[id='userGrid'] table tbody "+row+" td+td+td+td+td+td"));	
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", infoicon1);	*/
		}}
	}

	//}}

	public void Highlight(WebElement choseacts) throws Exception{
		for (int i = 0; i < 2; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			//    WebElement choseacts = null;
			js.executeScript( "arguments[0].setAttribute('style', arguments[1]);", choseacts , "color: red; border: 5px solid red;");
			Thread.sleep(1000);
			js.executeScript( "arguments[0].setAttribute('style', arguments[1]);", choseacts, "");
		}}

	private void Recordvalidations(String Testname,int data1,int data2,String client)throws Exception {
		if(data1<data2){
			log.logLine(Testname, false, "No. of records displayed for single client "+client+" "+data1+" is less than  to the no. of records displayed for all clients"+data2);
		}
		else if(data1==data2){
			log.logLine(Testname, false, "No. of records displayed for single client "+client+" "+data1+" is equal  to the no. of records displayed for all clients"+data2);
		}else{
			log.logLine(Testname, true, "No. of records displayed for single client "+client+" "+data1+" is not lessthan/equal  to the no. of records displayed for all clients"+data2);
		}		
	}

	private void SC_txt(String Testname,String text)throws Exception {
		if (doesElementExist(properties.getProperty("Sc_txt"),10)) {
			WebElement txt= driver.findElement(By.xpath(properties.getProperty("Sc_txt")));
			txt.clear();
			log.logLine(Testname, false, "Making entries in textbox  "+text);
			txt.sendKeys(text);
		}else {
			negativeCase(Testname, firstWinHandle, "","search criteria textbox doesnot exists");		

		}
	}

	private void clearbutton(String Testname)throws Exception {
		if (doesElementExist2(properties.getProperty("clear"), 5)) {
			WebElement clr = driver.findElement(By.cssSelector(properties.getProperty("clear")));			
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clr);
			log.logLine(Testname, false, "Clicking on clear button..");	

		} else {
			negativeCase(Testname, firstWinHandle, "","'clear' button doesnot exist");		
		}

	}

	private void includeinactv(String Testname)throws Exception {
		if (doesElementExist(properties.getProperty("inactvchk"), 5)) {
			WebElement chk = driver.findElement(By.xpath(properties.getProperty("inactvchk")));			
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", chk);
			log.logLine(Testname, false, "Clicking on include inactive chkbx..");	
		} else {
			negativeCase(Testname, firstWinHandle, "", "Clicking  on include inactive accounts checkbox  failed");	
		}
	}

	private void searchbtn(String Testname)throws Exception {
		if (doesElementExist2(properties.getProperty("srchbutton"), 5)) {
			WebElement srch= driver.findElement(By.cssSelector(properties.getProperty("srchbutton")));			
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srch);
			Highlight(srch);
			log.logLine(Testname, false, "Clicking on search button");	

		} else {
			negativeCase(Testname, firstWinHandle, "", "Search button doesnot exist");		
		}
	}




	private void clickonarea(String Testname)throws Exception {
		if (doesElementExist(properties.getProperty("area"), 5)) {
			WebElement ar= driver.findElement(By.xpath(properties.getProperty("area")));			
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ar);	
		} 
		else{log.logLine(Testname, false, "clicking on area failed");}
	}


	private boolean advancesrchValidation_startswith(String value, String Testname,String column) throws Exception {
		String[] Sort1 = new String[50];
		String  row = "tr";
		List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='userGrid']/table/tbody/tr"));
		if (DataCnt.size()==0){
			if(doesElementExist(properties.getProperty("noitem"), 5)){
				String noele = driver.findElement(By.xpath(properties.getProperty("noitem"))).getText(); 
				if (noele.contains("No items to display")){
					log.logLine(Testname, false, "Sorry No data is there to display");}
			}
		}
		else{
			if(doesElementExist(properties.getProperty("accountno"), 5)){
				for(int i = 0; i < DataCnt.size(); i++) {		
					Sort1[i] = driver.findElement(By.cssSelector("div[id='userGrid'] table tbody "+row+" "+column)).getText();

					StringBuilder sb = new StringBuilder(Sort1[i]);
					for (int index = 0; index < Sort1[i].length(); index++) {
						char c = sb.charAt(index);
						if (Character.isUpperCase(c)) {
							sb.setCharAt(index, Character.toLowerCase(c));		
						} 
					}

					if(sb.toString().startsWith(value)){
						log.logLine(Testname, false, "The validation passed and the userid  "+Sort1[i] +" matches with "+value);
					}else{
						log.logLine(Testname, true, "The validation failed and the userid  "+Sort1[i] +" doesnot match with "+value);

					}
					row = row + "+tr";	
				}

			}
		}
		return true;
	}


	private boolean negativeadvancesrchValidation_equals(String value, String Testname,String column) throws Exception {
		String[] Sort1 = new String[50];
		String  row = "tr";
		List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='userGrid']/table/tbody/tr"));
		if (DataCnt.size()==0){
			if(doesElementExist(properties.getProperty("noitem"), 5)){
				String noele = driver.findElement(By.xpath(properties.getProperty("noitem"))).getText(); 
				if (noele.equalsIgnoreCase("No items to display")){
					log.logLine(Testname, false, "Sorry No data is there to display");}
			}
		}
		else{
			if(doesElementExist(properties.getProperty("accountno"), 5)){
				for(int i = 0; i < DataCnt.size(); i++) {
					Sort1[i] = driver.findElement(By.cssSelector("div[id='userGrid'] table tbody "+row+" "+column)).getText();
					if(Sort1[i].equalsIgnoreCase(value)){
						log.logLine(Testname, true, "The validation failed and the userid  "+Sort1[i] +"  match with "+value);
					}else{
						log.logLine(Testname, false, "The validation passed and the userid  "+Sort1[i] +" doesnot match with "+value);

					}
					row = row + "+tr";
				}

			}
		}
		return true;
	}
	private boolean positiveadvancesrchValidation_equals(String value, String Testname,String column) throws Exception {
		String[] Sort1 = new String[50];
		String  row = "tr";
		List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='userGrid']/table/tbody/tr"));
		if (DataCnt.size()==0){
			if(doesElementExist(properties.getProperty("noitem"), 5)){
				String noele = driver.findElement(By.xpath(properties.getProperty("noitem"))).getText(); 
				if (noele.equalsIgnoreCase("No items to display")){
					log.logLine(Testname, false, "Sorry No data is there to display");}
			}
		}
		else{
			if(doesElementExist(properties.getProperty("accountno"), 5)){
				for(int i = 0; i < DataCnt.size(); i++) {
					Sort1[i] = driver.findElement(By.cssSelector("div[id='userGrid'] table tbody "+row+" "+column)).getText();
					if(Sort1[i].equalsIgnoreCase(value)){
						log.logLine(Testname, false, "The validation passed and the userid  "+Sort1[i] +"  match with "+value);
					}else{
						log.logLine(Testname, true, "The validation failed and the userid  "+Sort1[i] +" doesnot match with "+value);

					}
					if(i>=DataCnt.size()-2)
					break;
					row = row + "+tr";
				}

			}
		}
		return true;
	}

	private boolean advancesrchValidation1_startswith(String value, String Testname) throws Exception {
		String[] Sort1 = new String[50];
		String  row = "tr";
		List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='userGrid']/table/tbody/tr"));
		if (DataCnt.size()==0){
			if(doesElementExist(properties.getProperty("noitem"), 5)){
				String noele = driver.findElement(By.xpath(properties.getProperty("noitem"))).getText(); 
				if (noele.equalsIgnoreCase("No items to display")){
					log.logLine(Testname, false, "Sorry No data is there to display");}
			}
		}
		else{
			if(doesElementExist(properties.getProperty("accountno"), 5)){
				for(int i = 0; i < DataCnt.size(); i++) {
					Sort1[i] = driver.findElement(By.cssSelector("div[id='userGrid'] table tbody "+row+" td+td+td+td span[class='appToolTrigger']")).getText();

					StringBuilder sb = new StringBuilder(Sort1[i]);
					for (int index = 0; index < Sort1[i].length(); index++) {
						char c = sb.charAt(index);
						if (Character.isUpperCase(c)) {
							sb.setCharAt(index, Character.toLowerCase(c));		
						} 
					}

					if(sb.toString().startsWith(value)){
						log.logLine(Testname, false, "The validation passed and the username "+Sort1[i] +"  match with "+value);
					}else{
						log.logLine(Testname, true, "The validation failed and the username  "+Sort1[i] +"  doesnot match with "+value);

					}
					row = row + "+tr";
				}

			}
		}
		return true;
	}

	private boolean negativeadvancesrchValidation1_equals(String value, String Testname) throws Exception {
		String[] Sort1 = new String[50];
		String  row = "tr";
		List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='userGrid']/table/tbody/tr"));
		if (DataCnt.size()==0){
			if(doesElementExist(properties.getProperty("noitem"), 5)){
				String noele = driver.findElement(By.xpath(properties.getProperty("noitem"))).getText(); 
				if (noele.equalsIgnoreCase("No items to display")){
					log.logLine(Testname, false, "Sorry No data is there to display");}
			}
		}
		else{
			if(doesElementExist(properties.getProperty("accountno"), 5)){
				for(int i = 0; i < DataCnt.size(); i++) {
					Sort1[i] = driver.findElement(By.cssSelector("div[id='userGrid'] table tbody "+row+" td+td+td+td span[class='appToolTrigger']")).getText();
					if(Sort1[i].equalsIgnoreCase(value)){
						log.logLine(Testname, true, "The validation failed and the username  "+Sort1[i] +"  match with "+value);
					}else{
						log.logLine(Testname,false , "The validation passed and the username "+Sort1[i] +" doesnot  match with "+value);

					}
					row = row + "+tr";
				}

			}
		}
		return true;
	}


	private boolean positiveadvancesrchValidation1_equals(String value, String Testname) throws Exception {
		String[] Sort1 = new String[50];
		String  row = "tr";
		List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='userGrid']/table/tbody/tr"));
		if (DataCnt.size()==0){
			if(doesElementExist(properties.getProperty("noitem"), 5)){
				String noele = driver.findElement(By.xpath(properties.getProperty("noitem"))).getText(); 
				if (noele.equalsIgnoreCase("No items to display")){
					log.logLine(Testname, false, "Sorry No data is there to display");}
			}
		}
		else{
			if(doesElementExist(properties.getProperty("accountno"), 5)){
				for(int i = 0; i < DataCnt.size(); i++) {
					Sort1[i] = driver.findElement(By.cssSelector("div[id='userGrid'] table tbody "+row+" td+td+td+td span[class='appToolTrigger']")).getText();
					if(Sort1[i].equalsIgnoreCase(value)){
						log.logLine(Testname, false, "The validation passed and the username  "+Sort1[i] +"  match with "+value);
					}else{
						log.logLine(Testname, true, "The validation failed and the username "+Sort1[i] +" doesnot  match with "+value);

					}
					if(i>=DataCnt.size()-2)
						break;
					row = row + "+tr";
				}

			}
		}
		return true;
	}



	private void SC_operator(String Testname,String operator)throws Exception {
		Actions builder = new Actions(driver);
		if (doesElementExist(properties.getProperty("operator"), 10)) {
			WebElement mnuElement = driver.findElement(By.xpath(properties.getProperty("operator"))); 
			builder.moveToElement(mnuElement).perform(); 
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",mnuElement);	
			Thread.sleep(2000);
			if (doesElementExist2(properties.getProperty("operatorlnk"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("operatorlnk")));
				for (WebElement lnk:selopts) {
					String dd=lnk.getText();
					if (lnk.getText().contains(operator)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Clicking on the field "+ operator);                
						break;
					}                                
				}

			}else {
				log.logLine(Testname, true, "Clicking on the operator "+operator+" failed");
				throw new Exception("Clicking on the operator "+operator+" failed");	
			}   } else {
				negativeCase(Testname, firstWinHandle, "",  "operator listbox doesnot exists");				
			}         
	}


	private void usertype_Text(String Testname,String txt)throws Exception {
		Actions builder = new Actions(driver);
		if (doesElementExist(properties.getProperty("usertypetxt"), 10)) {
			WebElement mnuElement = driver.findElement(By.xpath(properties.getProperty("usertypetxt"))); 
			builder.moveToElement(mnuElement).perform(); 
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",mnuElement);  
			Thread.sleep(2000);
			if (doesElementExist2(properties.getProperty("usertypetxtlst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("usertypetxtlst")));
				for (WebElement lnk:selopts) {
					String dd=lnk.getText();
					if (lnk.getText().contains(txt)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Clicking on the field "+ txt);                
						break;
					}                                
				}
			}
		}
	}

	private void pivottool_Text(String Testname,String txt)throws Exception {
		Actions builder = new Actions(driver);
		if (doesElementExist(properties.getProperty("pivottooltxt"), 10)) {
			WebElement mnuElement = driver.findElement(By.xpath(properties.getProperty("pivottooltxt"))); 
			builder.moveToElement(mnuElement).perform(); 
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",mnuElement);  
			if (doesElementExist2(properties.getProperty("pivottxtlst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("pivottxtlst")));
				for (WebElement lnk:selopts) {
					String dd=lnk.getText();
					if (lnk.getText().contains(txt)) {
						Highlight(lnk);
						lnk.click();
						Thread.sleep(2000);			
						log.logLine(Testname, false, "We are selecting Pivot tool '"+ txt+"' from the list");                
						break;
					}                                
				}
			}
		}
					
	}

	private void SSOreq_Text(String Testname,String txt)throws Exception {
		Actions builder = new Actions(driver);
		if (doesElementExist(properties.getProperty("ssoreqtxt"), 10)) {
			WebElement mnuElement = driver.findElement(By.xpath(properties.getProperty("ssoreqtxt"))); 
			builder.moveToElement(mnuElement).perform(); 
			((JavascriptExecutor) driver).executeScript("arguments[0].click()",mnuElement);  
			Thread.sleep(2000);
			if (doesElementExist2(properties.getProperty("ssoreqlst"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ssoreqlst")));
				for (WebElement lnk:selopts) {
					String dd=lnk.getText();
					if (lnk.getText().equalsIgnoreCase(txt)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						log.logLine(Testname, false, "Clicking on the SSOreq_Text field "+ txt);                
						break;
					}                                
				}
			}}}


}



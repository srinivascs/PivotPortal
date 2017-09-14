package pivotModules;

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

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.support.ui.Select;



public class Status_systemhealth extends Page{

	public Status_systemhealth(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	} 
	@Override
	protected void load() {}
	@Override

	protected void isLoaded() throws Error {}

	String firstWinHandle = " ";
	String secondWinHandle = " ";
	String row=" ";
	String todaysDate=" ";
	String project="PVT9015";
	String form=" ";
	String Proid=" ";
	String eventmsg=" ";
	String componentsystem=" ";

	public boolean VerifyUserAccess(String AccNo, String Testname) throws Exception { 		

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
			log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");
			throw new Exception("Clicking on Cancel button in Client/App popup is failed");
		}

		Thread.sleep(6000);
		if (doesElementExist2(properties.getProperty("SignOutBtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("SignOutBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Sign Out button");

		}else if (doesElementExist2(properties.getProperty("SignOutBtn1"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("SignOutBtn1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Sign Out button");

		}else if (doesElementExist2(properties.getProperty("SignOutBtn2"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("SignOutBtn2")));
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
		Systemhealth_validation1(Testname,Usrnme);
		driver.close();
		driver.switchTo().window(firstWinHandle);
		Thread.sleep(2000);	
		signout(Testname);
		Thread.sleep(5000);

		log.logLine(Testname, false, "Validation of 2nd user:  starts");
		String Usrnme1 = test.readColumnData("UserName1", sheetname);
		String Paswd1 = test.readColumnData("Password", sheetname);
		signin(Testname,Usrnme1,Paswd1);
		Thread.sleep(4000);
		Systemhealth_validation1(Testname,Usrnme1);
		driver.close();
		driver.switchTo().window(firstWinHandle);
		Thread.sleep(2000);
		signout(Testname);
		Thread.sleep(5000);

		log.logLine(Testname, false, "Validation of other user:  starts");
		String Usrnme2 = test.readColumnData("UserName2", sheetname);
		String Paswd2 = test.readColumnData("Password", sheetname);
		signin(Testname,Usrnme2,Paswd2);
		Thread.sleep(4000);
		Systemhealth_validation1(Testname,Usrnme2);
		driver.close();
		driver.switchTo().window(firstWinHandle);
		Thread.sleep(2000);
		signout(Testname);
		Thread.sleep(5000);

		String Usrnme3 = test.readColumnData("UserName3", sheetname);
		String Paswd3 = test.readColumnData("Password", sheetname);
		signin(Testname,Usrnme3,Paswd3);
		Thread.sleep(4000);
		Systemhealth_validation1(Testname,Usrnme3);
		driver.close();
		driver.switchTo().window(firstWinHandle);
		Thread.sleep(2000);
		signout(Testname);
		Thread.sleep(5000);


		String Usrnme5 = test.readColumnData("UserName4", sheetname);
		String Paswd5 = test.readColumnData("Password", sheetname);
		signin(Testname,Usrnme5,Paswd5);
		Thread.sleep(4000);
		Systemhealth_validation1(Testname,Usrnme5);
		cancelpopupvalidation(Testname);
		Advancesearchvalidations(Testname);

		driver.close();
		driver.switchTo().window(firstWinHandle);

		return true;
	}

	public void Advancesearchvalidations(String Testname)throws Exception {

		//Result Format validations
		ClickAdvanceSearchBtn(Testname);
		Thread.sleep(2000);
		resultformat_summary(Testname);
		summary_validations(Testname);


		//severity info icon validation
		detailformat_summary(Testname);
		searchbutton(Testname);
		Thread.sleep(20000);
		severityinfoicon(Testname);
		Eventmessageicon(Testname);
		Thread.sleep(1000);

		//Additional search validations

		Proid=Readingdatafromgrid(Testname,"Project id",4);
		eventmsg=Readingdatafromgrid1(Testname,"eventmsg");
		componentsystem=Readingdatafromgrid2(Testname,"componentsystem");
		ClickAdvanceSearchBtn(Testname);
		Thread.sleep(2000);
		fieldselection(Testname,"Project ID",Proid,1);
		Thread.sleep(1000);
		addicon(Testname);
		fieldselection(Testname,"Event Message",eventmsg,2);
		Thread.sleep(1000);
		addicon(Testname);
		fieldselection(Testname,"Component System",componentsystem,3);
		searchbutton(Testname);
		Thread.sleep(10000);
		Additionalsearchvalidaions(Testname,Proid,eventmsg,componentsystem,4);


		//clear button validation
		ClickAdvanceSearchBtn(Testname);
		Thread.sleep(2000);
		clearvalidation(Testname);

		//Dynamic range validation
		ClickAdvanceSearchBtn(Testname);
		Thread.sleep(2000);
		dynamicselection(Testname,"Last 5 minutes");
		searchbutton(Testname);
		Thread.sleep(12000);
		dynamicrangevalidation(Testname,"Last 5 minutes");
		Thread.sleep(5000);

		ClickAdvanceSearchBtn(Testname);
		Thread.sleep(2000);
		dynamicselection(Testname,"Last 15 minutes");
		searchbutton(Testname);
		Thread.sleep(8000);
		dynamicrangevalidation(Testname,"Last 15 minutes");

		ClickAdvanceSearchBtn(Testname);
		Thread.sleep(2000);
		dynamicselection(Testname,"Last 30 minutes");
		searchbutton(Testname);
		Thread.sleep(8000);
		dynamicrangevalidation(Testname,"Last 30 minutes");

		ClickAdvanceSearchBtn(Testname);
		Thread.sleep(2000);
		dynamicselection(Testname,"Last hour");
		searchbutton(Testname);
		Thread.sleep(9000);
		dynamicrangevalidation(Testname,"Last hour");

		ClickAdvanceSearchBtn(Testname);
		Thread.sleep(2000);
		dynamicselection(Testname,"Last day");
		searchbutton(Testname);
		Thread.sleep(10000);
		dynamicrangevalidation(Testname,"Last day");


		//Previous days info validation
		project=readprojectid(Testname);
		ClickAdvanceSearchBtn(Testname);
		Thread.sleep(2000);
		dynamicselection(Testname,"Last 3 days");
		Thread.sleep(2000);
		searchbutton(Testname);
		Thread.sleep(3000);
		//validation of crieteria selection popup
		handlealert(Testname);
		projectidselection(Testname,project);
		Thread.sleep(2000);
		searchbutton(Testname);
		Thread.sleep(16000);
		dynamicrangevalidation(Testname,"Last 3 days");
		Thread.sleep(2000);

		ClickAdvanceSearchBtn(Testname);
		Thread.sleep(2000);
		dynamicselection(Testname,"Last 7 days");
		searchbutton(Testname);
		Thread.sleep(20000);
		dynamicrangevalidation(Testname,"Last 7 days"); 
		Thread.sleep(2000);

		//severity check
		ClickAdvanceSearchBtn(Testname);
		Thread.sleep(2000);
		clearbutton(Testname);
		Thread.sleep(2000);
		severityselection(Testname, "4");
		searchbutton(Testname);
		Thread.sleep(6000);
		severityselectionvalidation(Testname, "4");

		ClickAdvanceSearchBtn(Testname);
		severityallvalidation(Testname);
		Thread.sleep(1000);
		clearbutton(Testname);
		Thread.sleep(2000);

		//Field list validation	
		Fieldlist(Testname);
		clearbutton(Testname);
		addandremoveicon(Testname);
		clearbutton(Testname);	
		searchbutton(Testname);
		Thread.sleep(4000);


		//choose action validation
		Chooseactionselection(Testname,"Get Detail");
		Thread.sleep(8000);
		popuptitle(Testname,"Detail");
		columnheadingvalidations_eventdetail(Testname);
		Thread.sleep(2000);
		eventdetailpopupvalidation(Testname);
		popupclose(Testname);
		Thread.sleep(3000);


		Chooseactionselection(Testname,"Get Data");
		Thread.sleep(8000);
		popuptitle(Testname,"Data");
		columnheadingvalidations_eventdata(Testname);
		Thread.sleep(2000);
		eventdatapopupvalidation(Testname);
		popupclose(Testname);
		Thread.sleep(3000);

		Chooseactionselection(Testname,"Get Detail Supplemental");
		Thread.sleep(8000);
		popuptitle(Testname,"Supplemental");
		columnheadingvalidations_eventsupplimental(Testname);
		Thread.sleep(2000);
		supplimentalpopupvalidation(Testname);
		popupclose(Testname);

	}

	public void Additionalsearchvalidaions(String Testname,String info,String info1,String info2,int a) throws Exception {

		String[] Sort = new String[50];
		String[] Sort1 = new String[50];
		String[] Sort2 = new String[50];
		List<WebElement> DataCnt1= driver.findElements(By.xpath(".//*[@id='EventsErrorsGrid']/div[2]/table/tbody/tr"));
		if(DataCnt1.size()==0){
			if(doesElementExist(properties.getProperty("noitem"), 5)) {	    
				WebElement lgnbtn = driver.findElement(By.xpath(properties.getProperty("noitem")));	
				log.logLine(Testname, false, "No item exists to display");
				negativeCase(Testname, firstWinHandle, "", "Failed to read data from grid as the grid is empty");
			}	
		}else
			if(doesElementExist(properties.getProperty("heading"), 5)){
				for(int i = 1; i <= DataCnt1.size(); i++) {
					Sort[i-1] = driver.findElement(By.xpath(".//*[@id='EventsErrorsGrid']/div[2]/table/tbody/tr["+i+"]/td[4]")).getText();
					Sort1[i-1] = driver.findElement(By.xpath(".//*[@id='EventsErrorsGrid']/div[3]/table/tbody/tr["+i+"]/td[1]/div")).getText();
					Sort2[i-1] = driver.findElement(By.xpath(".//*[@id='EventsErrorsGrid']/div[3]/table/tbody/tr["+i+"]/td[2]")).getText();		
					String[] arr=new String[50];
					arr=Sort1[i-1] .split("\\...");
					String[] arr1=new String[50];
					arr1=arr[0].split(" o");
					String Sor=arr1[0];


					if((Sort[i-1].equalsIgnoreCase(info))&&(info1.contains(Sor))&&(Sort2[i-1].equalsIgnoreCase(info2))){
						log.logLine(Testname, false, " Data in the grid  "+Sort[i-1]+" , "+Sort1[i-1]+"  and  "+Sort2[i-1]+" "+" matches with the input values "+info+" , "+info1+" and "+info2+" respectively");
					}
					else{
						log.logLine(Testname, false, " Data in the grid  "+Sort[i-1]+" "+Sort1[i-1]+" "+Sort2[i-1]+" "+" doesnot match with the values "+info+" "+info1+" "+info2);
						break;
					}
				}}
	}



	public void severityselectionvalidation(String Testname,String info) throws Exception {

		String[] Sort = new String[50];
		List<WebElement> DataCnt1= driver.findElements(By.xpath(".//*[@id='EventsErrorsGrid']/div[2]/table/tbody/tr"));
		if(DataCnt1.size()==0){
			if(doesElementExist(properties.getProperty("noitem"), 5)) {	    
				WebElement lgnbtn = driver.findElement(By.xpath(properties.getProperty("noitem")));	
				negativeCase(Testname, firstWinHandle, "", "Failed to read data from grid as the grid is empty");
			}	
		}else
			if(doesElementExist(properties.getProperty("sever"), 5)){
				for(int i = 1; i <= DataCnt1.size(); i++) {
					Sort[i-1] = driver.findElement(By.xpath(".//*[@id='EventsErrorsGrid']/div[2]/table/tbody/tr["+i+"]/td[3]")).getText();

					if(Sort[i-1].equalsIgnoreCase(info)){
						log.logLine(Testname, false, " *******Severity "+Sort[i-1]+" in the grid matches with  "+info);
					}
					else{
						log.logLine(Testname, false, "Date range "+Sort[i-1]+" didnot match with "+info);
						break;
					}
				}}
	}

	public String Readingdatafromgrid(String Testname,String info,int j) throws Exception {

		String	Sort=" ";
		List<WebElement> DataCnt1= driver.findElements(By.xpath(".//*[@id='EventsErrorsGrid']/div[2]/table/tbody/tr"));
		if(DataCnt1.size()==0){
			if(doesElementExist(properties.getProperty("noitem"), 5)) {	    
				WebElement lgnbtn = driver.findElement(By.xpath(properties.getProperty("noitem")));	
				negativeCase(Testname, firstWinHandle, "", "Failed to read data from grid as the grid is empty");
			}	
		}else
			if(doesElementExist(properties.getProperty("heading"), 5)){		
				Sort= driver.findElement(By.xpath(".//*[@id='EventsErrorsGrid']/div[2]/table/tbody/tr[1]/td["+j+"]")).getText();			
				log.logLine(Testname, false, "The selected "+info+" from the grid is "+Sort);
			}
		return Sort;
	}


	public String Readingdatafromgrid1(String Testname,String info) throws Exception {

		String	Sort=" ";
		List<WebElement> DataCnt1= driver.findElements(By.xpath(".//*[@id='EventsErrorsGrid']/div[2]/table/tbody/tr"));
		if(DataCnt1.size()==0){
			if(doesElementExist(properties.getProperty("noitem"), 5)) {	    
				WebElement lgnbtn = driver.findElement(By.xpath(properties.getProperty("noitem")));	
				negativeCase(Testname, firstWinHandle, "", "Failed to read data from grid as the grid is empty");
			}	
		}else
			if(doesElementExist((".//*[@id='EventsErrorsGrid']/div[3]/table/tbody/tr[1]/td[1]/div/i"), 5)){
				WebElement lgnbtn = driver.findElement(By.xpath(".//*[@id='EventsErrorsGrid']/div[3]/table/tbody/tr[1]/td[1]/div/i"));	
				Highlight(lgnbtn);
				lgnbtn.click();
				if(doesElementExist2(("div[class='webui-popover pop bottom in'] div[class='webui-popover-inner'] div div p"), 5)){
					WebElement eventpopuptxt = driver.findElement(By.cssSelector(properties.getProperty("eventmsgpoptxt")));	
					String abc=eventpopuptxt.getText();
					if(!eventpopuptxt.getText().isEmpty()){
						log.logLine(Testname, false, " The text in the Event message popup is "+eventpopuptxt.getText());
						String[] arr=new String[50];
						arr=abc.split("\\.");
						Sort=arr[0];
					}}}
			else if(doesElementExist(properties.getProperty("heading"), 5)){		
				Sort= driver.findElement(By.xpath(".//*[@id='EventsErrorsGrid']/div[3]/table/tbody/tr[1]/td[1]/div")).getText();			
				log.logLine(Testname, false, "The selected "+info+" from the grid is "+Sort);

			}
		return Sort;
	}

	public String Readingdatafromgrid2(String Testname,String info) throws Exception {

		String	Sort=" ";
		List<WebElement> DataCnt1= driver.findElements(By.xpath(".//*[@id='EventsErrorsGrid']/div[2]/table/tbody/tr"));
		if(DataCnt1.size()==0){
			if(doesElementExist(properties.getProperty("noitem"), 5)) {	    
				WebElement lgnbtn = driver.findElement(By.xpath(properties.getProperty("noitem")));	
				negativeCase(Testname, firstWinHandle, "", "Failed to read data from grid as the grid is empty");
			}	
		}else
			if(doesElementExist(properties.getProperty("heading"), 5)){		
				Sort= driver.findElement(By.xpath(".//*[@id='EventsErrorsGrid']/div[3]/table/tbody/tr[1]/td[2]")).getText();			
				log.logLine(Testname, false, "The selected "+info+" from the grid is "+Sort);

			}
		return Sort;
	}


	public void Eventmessageicon(String Testname) throws Exception {

		Actions builder = new Actions(driver);
		String[] Sort = new String[50];
		List<WebElement> DataCnt1= driver.findElements(By.xpath(".//*[@id='EventsErrorsGrid']/div[2]/table/tbody/tr"));
		if(DataCnt1.size()==0){
			if(doesElementExist(properties.getProperty("noitem"), 5)) {	    
				WebElement lgnbtn = driver.findElement(By.xpath(properties.getProperty("noitem")));	
				//log.logLine(Testname, false, "No item exists to display");
				negativeCase(Testname, firstWinHandle, "", "Failed to read 'event message icon' data from grid as the grid is empty");
			}	
		}else
			if(doesElementExist(properties.getProperty("Eventmsg"), 5)){
				for(int j = 1; j <= DataCnt1.size(); j++) {
					if(doesElementExist((".//*[@id='EventsErrorsGrid']/div[3]/table/tbody/tr["+j+"]/td[1]/div/i"), 5)){
						WebElement lgnbtn = driver.findElement(By.xpath(".//*[@id='EventsErrorsGrid']/div[3]/table/tbody/tr["+j+"]/td[1]/div/i"));	
						Highlight(lgnbtn);
						lgnbtn.click();
						if(doesElementExist2(("div[class='webui-popover pop bottom in'] div[class='webui-popover-inner'] div div p"), 5)){
							WebElement eventpopuptxt = driver.findElement(By.cssSelector(properties.getProperty("eventmsgpoptxt")));	
							String abc=eventpopuptxt.getText();
							if(!eventpopuptxt.getText().isEmpty()){
								log.logLine(Testname, false, " The text in the Event message popup is "+eventpopuptxt.getText());
							}
							else{
								log.logLine(Testname, true, "The text in the Event message popup is empty");					
							}
							lgnbtn.click();
						}
					}}
			}else{
				negativeCase(Testname, firstWinHandle, "", "'Eventmsg ' column doesnot exist in the grid ");
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

	public void severityinfoicon(String testname) throws Exception {

		Actions builder = new Actions(driver);
		if (doesElementExist(properties.getProperty("severityinfo"), 10)) {                          
			WebElement sinfoicon= driver.findElement(By.xpath(properties.getProperty("severityinfo")));
			builder.moveToElement(sinfoicon).perform();                	
			List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("severityinfolist")));
			for(WebElement lnk:selopts){
				log.logLine(testname, false, "The text in the info icon is "+lnk.getText());}
			WebElement action= driver.findElement(By.xpath(properties.getProperty("action")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", action);
		}else {
			negativeCase(testname, firstWinHandle, "", "'severityinfo' icon doesnot exist in the grid ");
		}
	}

	public void handlealert(String Testname) throws Exception {


		if (doesElementExist(properties.getProperty("alert"), 5)) {
			WebElement data= driver.findElement(By.xpath(properties.getProperty("alert")));
			String abc=data.getText();
			if(abc.equalsIgnoreCase("When 'Dynamic' is over a day, at least one 'Additional Search Options' must be included."))
			{log.logLine(Testname, false, "Additional crieteria alert for when dynamic is 'overday' is displayed");
			} else {
				negativeCase(Testname, firstWinHandle, "", "Additional crieteria alert for when dynamic is 'overday' is didn't display");
			}}

		Thread.sleep(1000);

		if (doesElementExist(properties.getProperty("alertclose"), 5)) {
			WebElement alertclose= driver.findElement(By.xpath(properties.getProperty("alertclose")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", alertclose);	  
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicking on 'close' button of alert is successful");
		} else {
			negativeCase(Testname, firstWinHandle, "",  "Failed to Click on 'close' button of alert '");
		}	

	}
	public void popuptitle(String Testname,String Data) throws Exception {

		if (doesElementExist(properties.getProperty("popuptitle"), 5)) {
			WebElement data= driver.findElement(By.xpath(properties.getProperty("popuptitle")));
			String abc=data.getText();
			if(abc.contains(Data)){
				log.logLine(Testname, false, "Systemevent  popup with title '"+abc+"' appears");
			} else {
				negativeCase(Testname, firstWinHandle, "",  "Systemevent  popup with title '"+abc+"' didn't display");	
			}}	


	}

	public String readprojectid(String Testname) throws Exception {
		String pro =" ";
		if(doesElementExist(properties.getProperty("projectid"), 5)){
			pro= driver.findElement(By.xpath(".//*[@id='EventsErrorsGrid']/div[2]/table/tbody/tr[1]/td[4]")).getText();		
		}else if(doesElementExist(properties.getProperty("noitem"), 5)) {	    
			WebElement lgnbtn = driver.findElement(By.xpath(properties.getProperty("noitem")));	
			log.logLine(Testname, true , "No item exists to display");
			negativeCase(Testname, firstWinHandle, "", "No item exists to display, so unable to fetch data from grid");
		}
		return pro;}

	public void systemdetailsuppliment(String Testname) throws Exception {
		if (doesElementExist(properties.getProperty("detailsuppli"), 5)) {
			WebElement data= driver.findElement(By.xpath(properties.getProperty("detailsuppli")));
			log.logLine(Testname, false, "Systemevent detail supplimental popup appears");
		} else {

			negativeCase(Testname, firstWinHandle, "", "Systemevent detail supplimental popup didn't display");
		}

	}
	public void systemedata(String Testname) throws Exception {
		if (doesElementExist(properties.getProperty("systemdata"), 5)) {
			WebElement data= driver.findElement(By.xpath(properties.getProperty("systemdata")));
			log.logLine(Testname, false, "Systemevent data popup appears");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Systemevent data popup didn't display");
		}

	}

	public void popupclose(String Testname) throws Exception {
		if (doesElementExist2(properties.getProperty("popupclose"), 5)) {
			WebElement eventdet= driver.findElement(By.cssSelector(properties.getProperty("popupclose")));
			log.logLine(Testname, false, "Clicked on popupclose button");
		} else {	
			negativeCase(Testname, firstWinHandle, "", "Failed to Click on popupclose button");
		}

	}


	public void systemeventdetail(String Testname) throws Exception {
		if (doesElementExist(properties.getProperty("systemeventdetail"), 5)) {
			WebElement eventdet= driver.findElement(By.xpath(properties.getProperty("systemeventdetail")));
			log.logLine(Testname, false, "Systemevent detail popup appears");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Systemevent detail popup didn't display");
		}

		if (doesElementExist(properties.getProperty("systemeventdetail"), 5)) {
			WebElement eventdet= driver.findElement(By.xpath(properties.getProperty("systemeventdetail")));
			log.logLine(Testname, false, "Systemevent detail popup appears");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Systemevent detail popup didn't display");

		}

	}


	public void Chooseactionselection(String Testname,String act) throws Exception {

		String[] Sort = new String[50];

		List<WebElement> DataCnt1= driver.findElements(By.xpath(".//*[@id='EventsErrorsGrid']/div[2]/table/tbody/tr"));

		if(DataCnt1.size()==0){
			if(doesElementExist(properties.getProperty("noitem"), 5)) {	    
				WebElement lgnbtn = driver.findElement(By.xpath(properties.getProperty("noitem")));	
				if(lgnbtn.getText().equalsIgnoreCase("No items to display")){
					log.logLine(Testname, false, "No item exists to display");
				}}}
		else{
			if(doesElementExist(properties.getProperty("action"), 5)){
				String data="no";
				WebElement choose = driver.findElement(By.xpath(".//*[@id='EventsErrorsGrid']/div[2]/table/tbody/tr[1]/td[1]/span/span/span[1]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", choose);
				Thread.sleep(2000);			
				List<WebElement> DataCnt= driver.findElements(By.cssSelector("div[class='k-animation-container'] div ul li"));
				for (WebElement lnk:DataCnt) {
					Thread.sleep(1000);
					String abc1=lnk.getText();
					if (lnk.getText().equalsIgnoreCase(act)) {
						Thread.sleep(2000);
						((JavascriptExecutor) driver).executeScript("arguments[0].click()",lnk);
						Thread.sleep(1000);		
						data="yes";
						log.logLine(Testname, false, "Selected  "+act+" option from chooseaction list");
						break;}	
				}
				if(data.equalsIgnoreCase("no")){
					negativeCase(Testname, firstWinHandle, "", "Failed to select "+act+" option from chooseaction list ");
				}

			}
			else {
				negativeCase(Testname, firstWinHandle,"", "Action column doesnot exist"); 
			}}}

	public void addicon(String Testname) throws Exception {

		if (doesElementExist(properties.getProperty("add"), 5)) {
			WebElement addicon= driver.findElement(By.xpath(properties.getProperty("add")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", addicon);	  
			Thread.sleep(3000);
			log.logLine(Testname, false, "selecting 'add icon' is successful");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Failed to select 'add icon'");		
		}

	}
	public void addandremoveicon(String Testname) throws Exception {

		if (doesElementExist(properties.getProperty("add"), 5)) {
			WebElement addicon= driver.findElement(By.xpath(properties.getProperty("add")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", addicon);	  
			Thread.sleep(3000);
			log.logLine(Testname, false, "selecting 'add icon' is successful");
		} else {
			log.logLine(Testname, true, "Failed to select 'add icon'");
		}

		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("newcriebox"), 5)) {
			WebElement newtxt= driver.findElement(By.xpath(properties.getProperty("newcriebox")));
			log.logLine(Testname, false, "New search criteria text box arrived");
		} else {
			log.logLine(Testname, true, "New search criteria text box didnot arrive");
		}

		if (doesElementExist(properties.getProperty("remove"), 5)) {
			WebElement removeicon= driver.findElement(By.xpath(properties.getProperty("remove")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", removeicon);	  
			Thread.sleep(3000);
			log.logLine(Testname, false, "selecting 'remove icon' is successful");
		} else {
			log.logLine(Testname, true, "Failed to select 'remove icon'");
		}

		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("newcriebox"), 5)) {
			WebElement newtxt= driver.findElement(By.xpath(properties.getProperty("newcriebox")));
			log.logLine(Testname, false, "New search criteria text box still not removed");
		} else {
			log.logLine(Testname, false, "New search criteria text box has been removed now");
		}

	}


	public void fieldselection(String Testname,String fielddata,String value,int i) throws Exception {
		String[] Sort1 = new String[50];
		if(doesElementExist(".//*[@id='healthFilters']/div["+i+"]/span[1]/span/span[1]", 5)){
			WebElement fie= driver.findElement(By.xpath(".//*[@id='healthFilters']/div["+i+"]/span[1]/span/span[1]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", fie);	  
			List<WebElement> DataCnt1= driver.findElements(By.cssSelector("div[class='k-animation-container'] div ul li[role='option']"));
			for (WebElement lnk:DataCnt1) {
				Thread.sleep(1000);
				if (lnk.getText().equalsIgnoreCase(fielddata)) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()",lnk);
					Thread.sleep(1000);
					WebElement pro1= driver.findElement(By.xpath(".//*[@id='healthFilters']/div["+i+"]/input"));
					pro1.sendKeys(value);				
					log.logLine(Testname, false, "Entering the projectid to be searched is successful ");
					break;}								
			}
		}else {			
			negativeCase(Testname, firstWinHandle, "", "'Fieldlist' doesnot exist in additional search options");
		}
	}

	public void projectidselection(String Testname,String project) throws Exception {
		String[] Sort1 = new String[50];
		if(doesElementExist(properties.getProperty("field"), 5)){
			WebElement field= driver.findElement(By.xpath(properties.getProperty("field")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", field);	  
			List<WebElement> DataCnt1= driver.findElements(By.cssSelector("div[class='k-animation-container'] div ul li[role='option']"));
			for (WebElement lnk:DataCnt1) {
				Thread.sleep(1000);
				if (lnk.getText().equalsIgnoreCase("Project ID")) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click()",lnk);
					Thread.sleep(1000);
					WebElement pro1= driver.findElement(By.xpath(".//*[@id='healthFilters']/div/input"));
					pro1.sendKeys(project);				
					log.logLine(Testname, false, "Entering the projectid to be searched is successful ");
					break;}								
			}
		}else {			
			negativeCase(Testname, firstWinHandle, "", "'Fieldlist' doesnot exist in additional search options");
		}
	}



	public void Fieldlist(String Testname) throws Exception {
		String[] Sort1 = new String[50];
		if(doesElementExist(properties.getProperty("field"), 5)){
			WebElement field= driver.findElement(By.xpath(properties.getProperty("field")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", field);	  
			Thread.sleep(1000);
			List<WebElement> DataCnt1= driver.findElements(By.cssSelector("div[class='k-animation-container'] div ul li[role='option']"));
			int a=DataCnt1.size();		
			for (WebElement lnk:DataCnt1) {
				if(!lnk.getText().isEmpty()){
					log.logLine(Testname, false, "We are reading the contents of Field now  "+lnk.getText()); } }
			Thread.sleep(1000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", field);
		}else {			
			negativeCase(Testname, firstWinHandle, "", "'Fieldlist' doesnot exist in additional search options");
		}}

	public void severityallvalidation(String Testname) throws Exception {
		Thread.sleep(2000);

		if (doesElementExist(properties.getProperty("sevall"), 5)) {
			WebElement sevall= driver.findElement(By.xpath(properties.getProperty("sevall")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", sevall);	  
			Thread.sleep(3000);
			log.logLine(Testname, false, "selecting severity 'all' is successful");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Failed to select severity 'all'");
		}
		Thread.sleep(1000);
		if(doesElementExist(properties.getProperty("sever5"), 5)){
			WebElement sev5= driver.findElement(By.xpath(properties.getProperty("sever5")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", sev5);	
			log.logLine(Testname, false, "Unchecking the default severity"+sev5.getText());  		
		}else {
			negativeCase(Testname, firstWinHandle, "", "Failed to select default severity 'sever5'");
		}

		Thread.sleep(1000);

		if (doesElementExist(properties.getProperty("sevall"), 5)) {
			WebElement sevall= driver.findElement(By.xpath(properties.getProperty("sevall")));
			if(!sevall.isSelected()){
				log.logLine(Testname, false, "Due to the selection of other severity, the selection of 'all' is unchecked");
			}
			else{
				negativeCase(Testname, firstWinHandle, "","selection of severity 'all' is still checked inspite of choosing the other 'severity' ");	
			}}

	}

	public void severityselection(String Testname,String data) throws Exception {

		if(doesElementExist(properties.getProperty("sever5"), 5)){
			WebElement sev5= driver.findElement(By.xpath(properties.getProperty("sever5")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", sev5);	
			log.logLine(Testname, false, "Unchecking the default severity"+sev5.getText());  		
		}
		String abc="No";
		String[] Sort1 = new String[50];
		List<WebElement> DataCnt1= driver.findElements(By.xpath(".//*[@id='severity']/label"));

		for(int i = 1; i <= DataCnt1.size(); i++) {
			Sort1[i-1] = driver.findElement(By.xpath(".//*[@id='severity']/label["+i+"]")).getText();
			Thread.sleep(1000);
			if(Sort1[i-1].equalsIgnoreCase(data)){
				WebElement sev = driver.findElement(By.xpath(".//*[@id='severity']/label["+i+"]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", sev);	
				log.logLine(Testname, false, "Now we have selected severity "+sev.getText());
				abc="Yes";
				break;
			}
		}

		if(abc.equalsIgnoreCase("No")){
			negativeCase(Testname, firstWinHandle, "", "searched 'severity' didnot display in the grid");
		}

	}

	public void supplimentalpopupvalidation(String Testname) throws Exception {
		String columnname=" ";
		String[][] Sort = new String[50][50];
		List<WebElement> DataCnt1= driver.findElements(By.xpath(".//*[@id='EventDetailSupplementalGrid']/div[2]/table/tbody/tr"));		
		if(doesElementExist(properties.getProperty("noitemsuppli"), 5)) {	    
			WebElement lgnbtn = driver.findElement(By.xpath(properties.getProperty("noitemsuppli")));	
			log.logLine(Testname, false, "No item exists to display");
		}else
			if((doesElementExist(properties.getProperty("eventexcep_type"), 5))&&(doesElementExist(properties.getProperty("eventexcep_data"), 5))){
				String col1= driver.findElement(By.xpath(properties.getProperty("eventexcep_type"))).getText();
				String col2= driver.findElement(By.xpath(properties.getProperty("eventexcep_data"))).getText();

				List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='EventDetailSupplementalGrid']/div[2]/table/tbody/tr/td"));
				if((col1.equalsIgnoreCase("Event Exception Supplemental Detail Type"))&&(col2.equalsIgnoreCase("Event Exception Supplemental Detail Data"))){
					for(int j = 1; j <= DataCnt1.size(); j++){
						for(int i = 1; i <= DataCnt.size(); i++) {
							if(i==1){columnname=col1;}else if(i==2){columnname=col2;}
							Sort[j][i]=driver.findElement(By.xpath(".//*[@id='EventDetailSupplementalGrid']/div[2]/table/tbody/tr["+j+"]/td["+i+"]")).getText();
							if(!Sort[j][i].isEmpty()){
								log.logLine(Testname, false, "The data in row "+j+" and "+columnname+" column of 'System Event Detail Supplemental Grid' is "+Sort[j][i]);}
							else{log.logLine(Testname, false, "The data in row "+j+" and "+columnname+" column of 'System Event Detail Supplemental Grid' is "+Sort[j][i]);}
						}

					}}}else{log.logLine(Testname, true, "olumn headings didnot display in  supplimentalpopup grid");}
	}

	public void columnheadingvalidations_eventsupplimental(String Testname) throws Exception {		
		if((doesElementExist(properties.getProperty("eventexcep_type"), 5))&&(doesElementExist(properties.getProperty("eventexcep_data"), 5))){
			String col1= driver.findElement(By.xpath(properties.getProperty("eventexcep_type"))).getText();
			String col2= driver.findElement(By.xpath(properties.getProperty("eventexcep_data"))).getText();

			if((doesElementExist(properties.getProperty("eventexcep_type"), 5))&&(doesElementExist(properties.getProperty("eventexcep_data"), 5))){
				log.logLine(Testname, false, "column headings  "+col1+" , "+col2+" , "+" are present in the  'eventsupplimental popup' grid");	
			}
		}}



	public void columnheadingvalidations_eventdata(String Testname) throws Exception {		
		if((doesElementExist(properties.getProperty("Dockeyval"), 5))&&(doesElementExist(properties.getProperty("Docmiscinfo1"), 5))&&(doesElementExist(properties.getProperty("Docmiscinfo2"), 5))&&(doesElementExist(properties.getProperty("Docmiscinfo3"), 5))&&(doesElementExist(properties.getProperty("Docmiscinfo4"), 5))){
			String col1= driver.findElement(By.xpath(properties.getProperty("Dockeyval"))).getText();
			String col2= driver.findElement(By.xpath(properties.getProperty("Docmiscinfo1"))).getText();
			String col3= driver.findElement(By.xpath(properties.getProperty("Docmiscinfo2"))).getText();
			String col4= driver.findElement(By.xpath(properties.getProperty("Docmiscinfo3"))).getText();
			String col5= driver.findElement(By.xpath(properties.getProperty("Docmiscinfo4"))).getText();

			if((col1.equalsIgnoreCase("Document Key Value"))&&(col2.equalsIgnoreCase("Document Misc Info 1"))&&(col3.equalsIgnoreCase("Document Misc Info 2"))&&(col4.equalsIgnoreCase("Document Misc Info 3"))&&(col5.equalsIgnoreCase("Document Misc Info 4"))){
				log.logLine(Testname, false, "column headings  "+col1+" , "+col2+" , "+col3+" , "+col4+" , "+col5+" , "+" are present in the  'eventdata popup' grid");	
			}
		}}

	public void eventdatapopupvalidation(String Testname) throws Exception {
		String columnname=" ";
		String[][] Sort = new String[50][50];
		List<WebElement> DataCnt1= driver.findElements(By.xpath(".//*[@id='EventDocDataGrid']/div[2]/table/tbody/tr"));	
		if(doesElementExist(properties.getProperty("noitemdata"), 5)) {	    
			WebElement lgnbtn = driver.findElement(By.xpath(properties.getProperty("noitemdata")));	
			log.logLine(Testname, false, "No item exists to display");	
		}else
			if((doesElementExist(properties.getProperty("Dockeyval"), 5))&&(doesElementExist(properties.getProperty("Docmiscinfo1"), 5))&&(doesElementExist(properties.getProperty("Docmiscinfo2"), 5))&&(doesElementExist(properties.getProperty("Docmiscinfo3"), 5))&&(doesElementExist(properties.getProperty("Docmiscinfo4"), 5))){
				String col1= driver.findElement(By.xpath(properties.getProperty("Dockeyval"))).getText();
				String col2= driver.findElement(By.xpath(properties.getProperty("Docmiscinfo1"))).getText();
				String col3= driver.findElement(By.xpath(properties.getProperty("Docmiscinfo2"))).getText();
				String col4= driver.findElement(By.xpath(properties.getProperty("Docmiscinfo3"))).getText();
				String col5= driver.findElement(By.xpath(properties.getProperty("Docmiscinfo4"))).getText();

				List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='EventDocDataGrid']/div[2]/table/tbody/tr/td"));
				if((col1.equalsIgnoreCase("Document Key Value"))&&(col2.equalsIgnoreCase("Document Misc Info 1"))&&(col3.equalsIgnoreCase("Document Misc Info 2"))&&(col4.equalsIgnoreCase("Document Misc Info 3"))&&(col5.equalsIgnoreCase("Document Misc Info 4"))){
					for(int j = 1; j <= DataCnt1.size(); j++){
						for(int i = 1; i <= DataCnt.size(); i++) {
							if(i==1){columnname=col1;}else if(i==2){columnname=col2;}else if(i==3){columnname=col3;}else if(i==4){columnname=col4;}
							Sort[j][i]=driver.findElement(By.xpath(".//*[@id='EventDocDataGrid']/div[2]/table/tbody/tr["+j+"]/td["+i+"]")).getText();
							if(!Sort[j][i].isEmpty()){
								log.logLine(Testname, false, "The data in row "+j+" and "+columnname+" column of 'System Event Doc data Grid' is "+Sort[j][i]);}
							else{log.logLine(Testname, false, "The data in row "+j+" and "+columnname+" column of 'System Event Doc data Grid' is empty");}
						}

					}}}	else{
						negativeCase(Testname, firstWinHandle, "", " column headings didnot display in eventdata popup grid");

					}
	}


	public void columnheadingvalidations_eventdetail(String Testname) throws Exception {		
		if((doesElementExist(properties.getProperty("exceptionsource"), 5)) && (doesElementExist(properties.getProperty("exceptionpage"), 5))){
			String col1= driver.findElement(By.xpath(properties.getProperty("exceptionsource"))).getText();
			String col2= driver.findElement(By.xpath(properties.getProperty("exceptionpage"))).getText();

			if((col1.equalsIgnoreCase("Exception Source"))&&(col2.equalsIgnoreCase("Event Page Name"))){
				log.logLine(Testname, false, "column headings  "+col1+" , "+col2+" , "+" are present in the  'eventdetail popup' grid");	
			}
		}}



	public void eventdetailpopupvalidation(String Testname) throws Exception {
		String columnname=null;
		String[][] Sort = new String[50][50];
		List<WebElement> DataCnt1= driver.findElements(By.xpath(".//*[@id='EventDetailGrid']/div[2]/table/tbody/tr"));		
		if(doesElementExist(properties.getProperty("noitemdetail"), 5)) {	    
			WebElement lgnbtn = driver.findElement(By.xpath(properties.getProperty("noitemdetail")));	
			log.logLine(Testname, false, "No item exists to display");			
		}
		else if((doesElementExist(properties.getProperty("exceptionsource"), 5)) && (doesElementExist(properties.getProperty("exceptionpage"), 5))){
			String col1= driver.findElement(By.xpath(properties.getProperty("exceptionsource"))).getText();
			String col2= driver.findElement(By.xpath(properties.getProperty("exceptionpage"))).getText();

			List<WebElement> DataCnt= driver.findElements(By.xpath(".//*[@id='EventDetailGrid']/div[2]/table/tbody/tr/td"));
			if((col1.equalsIgnoreCase("Exception Source"))&&(col2.equalsIgnoreCase("Event Page Name"))){	
				for(int j = 1; j <= DataCnt1.size(); j++){
					for(int i = 1; i <= DataCnt.size(); i++) {
						if(i==1){columnname=col1;}else if(i==2){columnname=col2;}
						Sort[j][i] = driver.findElement(By.xpath(".//*[@id='EventDetailGrid']/div[2]/table/tbody/tr["+j+"]/td["+i+"]")).getText();
						if(!Sort[j][i].isEmpty()){
							log.logLine(Testname, false, "The data in row "+j+" and "+columnname+" column of 'System Event Detail Grid' is "+Sort[j][i]);}
						else{log.logLine(Testname, false, "The data in row "+j+" and "+columnname+" column of 'System Event Detail Grid' is empty");}
					}
				}}
		}
		else{
			negativeCase(Testname, firstWinHandle, "", "column headings didnot display in eventdetail popup grid");
		}

	}

	public void dynamicrangevalidation(String Testname,String info) throws Exception {

		if (doesElementExist(properties.getProperty("currentview"), 5)) {	    
			WebElement Username = driver.findElement(By.xpath(properties.getProperty("currentviewdata")));
			String value=Username.getText();
			if(value.equalsIgnoreCase(info)){
				log.logLine(Testname, false, "Selected Dynamic range is successfully displayed in current view");   
			}		
		} else {			
			log.logLine(Testname, true, "currentview info did not display");	
			negativeCase(Testname, firstWinHandle, "", "currentview info did not display ");
		}

		Thread.sleep(2000);

		String[] Sort1 = new String[50];
		String CurDate[]=new String[30];
		List<WebElement> DataCnt1= driver.findElements(By.xpath(".//*[@id='EventsErrorsGrid']/div[2]/table/tbody/tr"));
		if(DataCnt1.size()==0){
			if(doesElementExist(properties.getProperty("noitem"), 5)) {	    
				WebElement lgnbtn = driver.findElement(By.xpath(properties.getProperty("noitem")));	
				if(lgnbtn.getText().equalsIgnoreCase("No items to display")){
					log.logLine(Testname, false, "No item exists to display");
				}}}
		else{
			if(doesElementExist(properties.getProperty("Datetimelog"), 5)){
				for(int i = 1; i <= DataCnt1.size(); i++) {
					Sort1[i-1] = driver.findElement(By.xpath(".//*[@id='EventsErrorsGrid']/div[2]/table/tbody/tr["+i+"]/td[2]")).getText();
					Thread.sleep(2000);
					DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");		
					Date date = new Date();
					todaysDate = dateFormat.format(date);

					CurDate=Sort1[i-1].split(" ");

					/*	form="MM/dd/yyyy";
					boolean datform= isValidFormat(form,todaysDate);
					boolean datform1= isValidFormat(form,CurDate[0]);

					if(datform==datform1){		
						log.logLine(Testname, false,"System date & date displayed in grid are of same format "+form);		
					}
					else{
						log.logLine(Testname, true,"System date & date displayed in grid are not of same format");		
					}*/

					String result=Lastdays(todaysDate,-1);
					String res=Lastdays(todaysDate,-2);
					String result1=Lastdays(todaysDate,-3);
					String result2=Lastdays(todaysDate,-4);
					String result3=Lastdays(todaysDate,-5);
					String result4=Lastdays(todaysDate,-6);
					String result5=Lastdays(todaysDate,-7);

					if((info.equalsIgnoreCase("Last 5 minutes"))||(info.equalsIgnoreCase("Last 15 minutes"))
							||(info.equalsIgnoreCase("Last 30 minutes"))||(info.equalsIgnoreCase("Last hour"))){					

						if(CurDate[0].equalsIgnoreCase(todaysDate)){
							log.logLine(Testname, false, " *******Date range "+CurDate[0]+" in the grid matches with  todaysdate "+todaysDate);
						}else {	log.logLine(Testname, true, " *******Date range "+CurDate[0]+" in the grid didnot match with  todaysdate "+todaysDate);
						break;
						}

					}

					if(info.equalsIgnoreCase("Last day")){
						if(CurDate[0].equalsIgnoreCase(result)||(CurDate[0].equalsIgnoreCase(todaysDate))){
							log.logLine(Testname, false, " *******Date range "+CurDate[0]+" in the grid is within today and previousday "+todaysDate+" and "+result);
						}else {	log.logLine(Testname, true, " *******Date range "+CurDate[0]+" in the grid  is not within today and previousday  "+todaysDate+" and "+result);
						break;
						}
					}

					if(info.equalsIgnoreCase("Last 3 days")){
						if((CurDate[0].equalsIgnoreCase(todaysDate))||(CurDate[0].equalsIgnoreCase(res))||(CurDate[0].equalsIgnoreCase(result))||(CurDate[0].equalsIgnoreCase(result1))){
							log.logLine(Testname, false, " *******Date range "+CurDate[0]+" in the grid is within the range of Last3days "+todaysDate+" to "+result1);
						}else {	log.logLine(Testname, true, " *******Date range "+CurDate[0]+" in the grid didnot match within the range od  Last3days "+todaysDate+" to "+result1);
						break;
						}
					}

					if(info.equalsIgnoreCase("Last 7 days")){	
						if((CurDate[0].equalsIgnoreCase(todaysDate))||(CurDate[0].equalsIgnoreCase(res))||(CurDate[0].equalsIgnoreCase(result))||(CurDate[0].equalsIgnoreCase(result1))
								||(CurDate[0].equalsIgnoreCase(result2)||(CurDate[0].equalsIgnoreCase(result3))||(CurDate[0].equalsIgnoreCase(result4))
										||(CurDate[0].equalsIgnoreCase(result5)))){
							log.logLine(Testname, false, " *******Date range "+CurDate[0]+" in the grid is within the range of Last7days "+todaysDate+" to "+result5);
						}
						else{
							log.logLine(Testname,true, "Date range "+CurDate[0]+" in the grid is not within the range of Last7days "+todaysDate+" to "+result5 );
							break;
						}

					}}
			} 

		}
	}

	public static String previousDateString(String date) throws ParseException {

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		// Parse the given date string into a Date object.
		// Note: This can throw a ParseException.
		Date myDate = dateFormat.parse(date);

		// Use the Calendar class to subtract one day
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(myDate);
		calendar.add(Calendar.DAY_OF_YEAR, -1);

		// Use the date formatter to produce a formatted date string
		Date previousDate = calendar.getTime();
		String result = dateFormat.format(previousDate);
		return result;}

	public  String Last3days(String date,String dateingrid,String Testname) throws Exception {

		DateFormat dateFormat = new SimpleDateFormat(form);

		Date myDate = dateFormat.parse(date);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(myDate);
		calendar.add(Calendar.DAY_OF_YEAR, -3);

		Date previousDate = calendar.getTime();
		String result1 = dateFormat.format(previousDate);

		return result1;
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

	public static String Lastdays(String date,int Num) 
			throws ParseException {

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		Date myDate = dateFormat.parse(date);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(myDate);
		calendar.add(Calendar.DAY_OF_YEAR, Num);


		Date previousDate = calendar.getTime();
		String result1 = dateFormat.format(previousDate);
		return result1;
	}

	public void clearbutton(String Testname) throws Exception {
		if (doesElementExist(properties.getProperty("clear"), 5)) {
			WebElement clr= driver.findElement(By.xpath(properties.getProperty("clear")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clr);	  
			Thread.sleep(3000);
			log.logLine(Testname, false, "clicking on 'clear button' is successful");
		} else {
			log.logLine(Testname, true, "clicking on 'clear button' failed");
			//	driver.switchTo().window(firstWinHandle);	
		}}

	public void searchbutton(String Testname) throws Exception {
		Thread.sleep(1000);
		if (doesElementExist(properties.getProperty("searchbtn"), 5)) {
			WebElement srch= driver.findElement(By.xpath(properties.getProperty("searchbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", srch);	  
			log.logLine(Testname, false, "Clicked on search button");
			Thread.sleep(2000);
		} else {
			log.logLine(Testname, true, "Failed to click on search button");
			//negativeCase(Testname, firstWinHandle, "", "Failed to click on search button ");

		}
	}
	public void waitUntilRetrive1() throws Exception {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		for (int i = 0; i < 600; i++) {
			if (doesElementExist("//div/span[equalsIgnoreCase(text(), \"...\")]", 3)) 
				Thread.sleep(2000);
			else
				break;
		}
		log.logLine("", false,"Retriving... element did disappear after sometime!");
	}

	public boolean isTextPresent(String text){
		try{
			boolean b = driver.getPageSource().equalsIgnoreCase(text);
			return b;
		}
		catch(Exception e){
			return false;
		}
	}

	public void summary_validations(String Testname) throws Exception {

		if (doesElementExist(properties.getProperty("dynamicrange"), 5)) {
			Select prsnthosttype = new Select(driver.findElement(By.id("dynamicTimeRange")));
			List<WebElement> list_option=prsnthosttype.getOptions();
			log.logLine(Testname, false,"No. of options present in summay_dynamic range list is "+list_option.size());
			for(WebElement lst:list_option){
				String option=lst.getText();
				log.logLine(Testname, false, option+"is present in the dynamic range list");
			}		
		} else {
			negativeCase(Testname, firstWinHandle, "", "Dynamic range list box is not present ");
		}

		if(doesElementExist(properties.getProperty("summary_sever"), 5)){
			WebElement sev5= driver.findElement(By.xpath(properties.getProperty("summary_sever")));
			if(sev5.isSelected()){
				log.logLine(Testname, false, "Severity 5 is selected by default");} 
			else{
				log.logLine(Testname, false, "Severity 5 is not  selected by default");
			}
		}else {
			negativeCase(Testname, firstWinHandle, "", "'summary_sever' checkbox is not present");
		}

	}	

	public void dynamicselection(String Testname,String value) throws Exception {
		Thread.sleep(2000);	
		if (doesElementExist(properties.getProperty("dynamicrange"), 5)) {
			Select prsnthosttype = new Select(driver.findElement(By.id("dynamicTimeRange")));
			prsnthosttype.selectByVisibleText(value);
			log.logLine(Testname, false, "Selecting range value "+value+" is successful");
		} else {
			negativeCase(Testname, firstWinHandle, "", "Failed to Select range value "+value+" ");
		}

	}	

	public void resultformat_summary(String Testname) throws Exception {

		if (doesElementExist(properties.getProperty("summary"), 5)) {
			WebElement sevall= driver.findElement(By.xpath(properties.getProperty("summary")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", sevall);	  
			log.logLine(Testname, false, "selecting 'summary' option form result format is successful");
		} else {
			log.logLine(Testname, true, "selecting 'summary' option form result format is failed");
			negativeCase(Testname, firstWinHandle, "", "Failed to select'summary' option form result format");		
		}

	}

	public void detailformat_summary(String Testname) throws Exception {

		if (doesElementExist(properties.getProperty("detail"), 5)) {
			WebElement sevall= driver.findElement(By.xpath(properties.getProperty("detail")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", sevall);	  
			log.logLine(Testname, false, "selecting 'detail' option form result format is successful");
		} else {
			log.logLine(Testname, true, "selecting 'detail' option form result format is failed");
			negativeCase(Testname, firstWinHandle, "", "Failed to select'detail' option form result format");		
		}

	}

	public void resultformat_detail(String Testname) throws Exception {
		if (doesElementExist(properties.getProperty("detail"), 5)) {
			WebElement det= driver.findElement(By.xpath(properties.getProperty("detail")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", det);	  
			log.logLine(Testname, false, "selecting 'detail' option form result format is successful");
		} else {
			log.logLine(Testname, true, "selecting 'detail' option form result format is failed");
			negativeCase(Testname, firstWinHandle, "", "Failed to select'detail' option form result format");		
		}

	}	
	public void clearvalidation(String Testname) throws Exception {
		Thread.sleep(2000);

		if (doesElementExist(properties.getProperty("sevall"), 5)) {
			WebElement sevall= driver.findElement(By.xpath(properties.getProperty("sevall")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", sevall);	  
			Thread.sleep(3000);
			log.logLine(Testname, false, "selecting severity 'all' is successful");
		} else {
			log.logLine(Testname, true, "Failed to select severity 'all'");
			negativeCase(Testname, firstWinHandle, "", "Failed to select severity 'all'");		
		}
		Thread.sleep(1000);

		if (doesElementExist(properties.getProperty("clear"), 5)) {
			WebElement clr= driver.findElement(By.xpath(properties.getProperty("clear")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", clr);	  
			Thread.sleep(3000);
			log.logLine(Testname, false, "clicking on 'clear button' is successful");
		} else {
			log.logLine(Testname, true, "clicking on 'clear button' failed");
			negativeCase(Testname, firstWinHandle, "", "clicking on 'clear button' failed");		
		}
		Thread.sleep(3000);

		if (doesElementExist(properties.getProperty("sevall"), 5)) {
			WebElement sevall= driver.findElement(By.xpath(properties.getProperty("sevall")));
			if(!sevall.isSelected())
				log.logLine(Testname, false, "Clearing the advance search functionality is successful,severity has been reset to the default one now");
		} else {
			log.logLine(Testname, true, "Clearing the advance search functionality is failed ,as severity has not been reset to the default one ");
			negativeCase(Testname, firstWinHandle, "", "Clearing the advance search functionality is failed ,as severity has not been reset to the default one ");	
		}
		Thread.sleep(1000);
		if (doesElementExist(properties.getProperty("srchcancel"), 5)) {
			WebElement cancel= driver.findElement(By.xpath(properties.getProperty("srchcancel")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", cancel);	  
			Thread.sleep(3000);
			log.logLine(Testname, false, "clicking on 'cancel button' is successful");
		} else {
			log.logLine(Testname, true, "clicking on 'cancel button' failed");
			negativeCase(Testname, firstWinHandle, "", "clicking on 'cancel button' failed");
		}


	}

	public void cancelbtn(String Testname) throws Exception {	
		if (doesElementExist(properties.getProperty("srchcancel"), 5)) {
			WebElement cancel= driver.findElement(By.xpath(properties.getProperty("srchcancel")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", cancel);	  
			Thread.sleep(3000);
			log.logLine(Testname, false, "clicking on 'cancel button' is successful");
		} else {
			log.logLine(Testname, true, "clicking on 'cancel button' failed");
			negativeCase(Testname, firstWinHandle, "", "clicking on 'cancel button' failed");		
		}}

	public void ClickAdvanceSearchBtn(String Testname) throws Exception {
		if (doesElementExist(properties.getProperty("Advancedsearch"), 5)) {
			WebElement Advsearch= driver.findElement(By.xpath(properties.getProperty("Advancedsearch")));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",Advsearch);  
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Advsearch);	  
			Thread.sleep(3000);
			log.logLine(Testname, false, "Click on Advancedsearch is Successful");
		} else {
			log.logLine(Testname, true, "Click on Advancedsearch is failed");
			negativeCase(Testname, firstWinHandle, "", "Click on Advancedsearch is failed");	
		}
	}
	public void cancelpopupvalidation(String Testname)throws Exception {

		if (doesElementExist(properties.getProperty("cancelsrch"), 5)) {	    
			WebElement lgnbtn = driver.findElement(By.xpath(properties.getProperty("cancelsrch")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", lgnbtn);
			Thread.sleep(1000);
			waitUntilRetrive1();
			log.logLine(Testname, false, "Click on cancel Button of popup");
			driver.switchTo().frame("iframeContainer");	
		}else{ driver.switchTo().frame("iframeContainer");	 
		if (doesElementExist(properties.getProperty("griddata"), 5)) {	    	
			WebElement lgnbtn = driver.findElement(By.xpath(properties.getProperty("griddata")));	
			log.logLine(Testname, false, "Grid is getting displayed with data");
		}else if(doesElementExist(properties.getProperty("noitem"), 5)) {	    
			WebElement lgnbtn = driver.findElement(By.xpath(properties.getProperty("noitem")));	
			log.logLine(Testname, false, "No item exists to display");
		}
		}

		Thread.sleep(2000);

		if (doesElementExist(properties.getProperty("currentview"), 5)) {	    
			WebElement Username = driver.findElement(By.xpath(properties.getProperty("currentviewdata")));
			String value=Username.getText();
			if(value.equalsIgnoreCase("Timeframe=Last 15 minutes; Severity=5;")){
				log.logLine(Testname, false, "Right data is displayed in current view");   
			}		
			Thread.sleep(2000);		
		} else {			
			log.logLine(Testname, true, "currentview info did not display");
			negativeCase(Testname, firstWinHandle, "", "currentview info did not display");		
		}

		Thread.sleep(2000);

		if (doesElementExist(properties.getProperty("griddata"), 5)) {	    
			WebElement lgnbtn = driver.findElement(By.xpath(properties.getProperty("griddata")));
			Thread.sleep(8000);
			if (doesElementExist(properties.getProperty("Items"), 5)) {	    
				String val = driver.findElement(By.xpath(".//*[@id='EventsErrorsGrid']/div[4]/span[2]")).getText();
				String arr[] = val.split("of");
				String Beforeupdte = arr[1].trim();
				log.logLine(Testname, false, "Maximum number of documents displayed in the grid "+Beforeupdte+" ****");
				int abc=Integer.parseInt(Beforeupdte);
				if(abc<1000){
					log.logLine(Testname, false, "Data displayed in the grid is within 1000");
				}else{
					log.logLine(Testname, true, "Data displayed in the grid is exceeded 1000");
				}
			}
		}
		else{
			negativeCase(Testname, firstWinHandle, "", "There is no data in the grid to validate");
		}
		scrollactions(Testname);

	}

	public void scrollactions(String Testname)throws Exception {

		Thread.sleep(2000);
		if (doesElementExist(properties.getProperty("header"), 5)) {	    
			WebElement req = driver.findElement(By.xpath(properties.getProperty("requestid")));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",req);
			Thread.sleep(1000);
			log.logLine(Testname, false, "We are now able to view requestid info"+req.getText());

			Thread.sleep(2000);

			WebElement usr = driver.findElement(By.xpath(properties.getProperty("usrid")));
			Thread.sleep(1000);
			log.logLine(Testname, false, "We are now able to view userid info"+usr.getText());

			Thread.sleep(2000);

			WebElement sysevent = driver.findElement(By.xpath(properties.getProperty("sysevent")));
			Thread.sleep(1000);
			log.logLine(Testname, false, "We are now able to view userid info"+sysevent.getText());

			Thread.sleep(2000);
			WebElement sysmod = driver.findElement(By.xpath(properties.getProperty("sysmodule")));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",sysmod);	
			Thread.sleep(1000);
			log.logLine(Testname, false, "We are now able to view systemmodule info"+sysmod.getText());

		}else{log.logLine(Testname, true, "Header's of the grid is unavailable to validate");
		negativeCase(Testname, firstWinHandle, "", "Header's of the grid is unavailable to validate");	
		}	

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
		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
		}	
	}

	public void signout(String Testname) throws Exception {

		if (doesElementExist2(properties.getProperty("usrSignOutBtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("usrSignOutBtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicking on Sign Out button");

		}else if (doesElementExist2(properties.getProperty("usrSignOutBtn1"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("usrSignOutBtn1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			Thread.sleep(5000);
			log.logLine(Testname, false, "Clicking on Sign Out button for First user");
		}else {
			log.logLine(Testname, true, "Clicking on Sign Out button is failed");
			log.logLine(Testname, true, "Logging in back to Super User to continue suite execution");
			Relogin(Testname);
		}	
	}
	
	public boolean Systemhealth_validation1(String Testname,String user) throws Exception {

		Thread.sleep(1000);


		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");
			Relogin(Testname);
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
					if (lnk.getText().equalsIgnoreCase("HA Admin")) {
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


		Thread.sleep(10000);

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

		if((user.equalsIgnoreCase("Manohar"))||(user.equalsIgnoreCase("PIVOTRTRRDSITE"))){
			if (doesElementExist(properties.getProperty("status"), 5)) {	    
				WebElement status = driver.findElement(By.xpath(properties.getProperty("status")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", status);
				log.logLine(Testname, false, "Status module has access to the user "+user+" as he is a superadmin");
			} else {
				log.logLine(Testname, true, "Status module doesnot exist eventhough "+user+"is superadmin");

			}
			Thread.sleep(1000);

			if (doesElementExist(properties.getProperty("systemhealth"), 5)) {  
				WebElement syshlth = driver.findElement(By.xpath(properties.getProperty("systemhealth")));
				builder.moveToElement(syshlth).perform();                		
				Thread.sleep(2000);
				if (doesElementExist(properties.getProperty("sysevents/error"), 5)) {
					WebElement sysevent= driver.findElement(By.xpath(properties.getProperty("sysevents/error")));
					builder.moveToElement(sysevent).perform(); 
					Thread.sleep(1000);
					((JavascriptExecutor) driver).executeScript("arguments[0].click()",sysevent);		
					log.logLine(Testname, false, "Clicking on systemevent/error option  is passed");		               
				} 
				else if (doesElementExist2(properties.getProperty("sysevents/error1"), 5)) {
					WebElement sysevent= driver.findElement(By.cssSelector(properties.getProperty("sysevents/error1")));
					builder.moveToElement(sysevent).perform(); 
					Thread.sleep(1000);
					((JavascriptExecutor) driver).executeScript("arguments[0].click()",sysevent);			
					log.logLine(Testname, false, "Clicking on systemevent/error option  is passed");		               
				} 
				else{log.logLine(Testname, true, "Clicking on systemevent/error option  is failed");}
				log.logLine(Testname, false, "Clicking on System health is successful for"+user);} 
			else {
				log.logLine(Testname, true, "Clicking on systemevent/error option is successful for"+user);    	
			}

		}
		else {
			if (doesElementExist(properties.getProperty("status"), 5)) {	    
				WebElement status = driver.findElement(By.xpath(properties.getProperty("status")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", status);
				log.logLine(Testname, true, "Status module has access to the user "+user+" eventhough the user is not a superadmin");			

			} else {
				log.logLine(Testname, false, "Failed to click on 'status' button,because"+user+"doesnot have access to status module");
			}
			Thread.sleep(1000);

			if (doesElementExist(properties.getProperty("systemhealth"), 5)) {  
				WebElement syshlth = driver.findElement(By.xpath(properties.getProperty("systemhealth")));
				builder.moveToElement(syshlth).perform();                		
				Thread.sleep(4000);
				if (doesElementExist(properties.getProperty("sysevents/error"), 5)) {
					WebElement sysevent= driver.findElement(By.xpath(properties.getProperty("sysevents/error")));
					builder.moveToElement(sysevent).perform(); 
					Thread.sleep(1000);
					((JavascriptExecutor) driver).executeScript("arguments[0].click()",sysevent);
					Thread.sleep(1000);
					log.logLine(Testname, true, "Clicking on systemevent/error option  is passed for non superadmin");
				} 
				else{
					log.logLine(Testname, false, "Clicking on systemevent/error option  is failed,since only 'Superadminuser' has access to this module ");}
				log.logLine(Testname, true, "Clicking on systemevent/error option is successful for"+user); 
			} 
			else {
				log.logLine(Testname, false, "Clicking on System health is unsuccessful for"+user);
			}}

		return true;
	}
	public boolean Systemhealth_validation(String Testname,String user) throws Exception {

		Thread.sleep(1000);


		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");
			Relogin(Testname);
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
					if (lnk.getText().equalsIgnoreCase("HA Admin")) {
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


		Thread.sleep(10000);

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


		if (doesElementExist(properties.getProperty("status"), 5)) {	    
			WebElement status = driver.findElement(By.xpath(properties.getProperty("status")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", status);

			log.logLine(Testname, false, "Clicking 'status' button is successful");
		} else {
			log.logLine(Testname, true, "Failed to click on 'status' button eventhough user"+user+"have access to it");
			negativeCase(Testname, firstWinHandle, "","Failed to click on 'status' button eventhough user"+user+"have access to it");
		}
		Thread.sleep(1000);

		if (doesElementExist(properties.getProperty("systemhealth"), 5)) {  
			WebElement syshlth = driver.findElement(By.xpath(properties.getProperty("systemhealth")));
			builder.moveToElement(syshlth).perform();                		
			Thread.sleep(4000);
			if (doesElementExist(properties.getProperty("sysevents/error"), 5)) {
				WebElement sysevent= driver.findElement(By.xpath(properties.getProperty("sysevents/error")));
				builder.moveToElement(sysevent).perform(); 
				Thread.sleep(1000);
				((JavascriptExecutor) driver).executeScript("arguments[0].click()",sysevent);
				Thread.sleep(1000);
				log.logLine(Testname, false, "Clicking on systemevent/error option is successful");                
			}                                
		} else {
			log.logLine(Testname, false, "Clicking on systemevent/error option  is failed,since only 'Superadminuser' has access to this module ");		
			//negativeCase(Testname, firstWinHandle, "","Clicking on systemevent/error option  is failed,since only 'Superadminuser' has access to this module ");

		}

		return true;
	}

	public boolean Relogin(String Testname) throws Exception {
		log.logLine(Testname, false, "Logging in back to Super User to continue suite execution");
		PivotSignInOut loginPge = new PivotSignInOut(driver, log);
		loginPge.load(Initialization.Browser, Initialization.EnvirSite);
		Thread.sleep(6000);
		loginPge.loginAs(Initialization.UserID, Initialization.Passwd);
		return true;
	}


}

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
import java.util.concurrent.TimeUnit;

import launchAuto.Initialization;
import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;


public class Compareprooftest extends Page{

	public Compareprooftest(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}	
	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}	
	String firstWinHandle,secondWinHandle;
	String FileUpload1;
	String FileUpload2;

	public void prooflogin(String AccNo, String Testname) throws Exception  {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(1000);

		WebElement retelm2 = waitForElement(properties.getProperty("selClint1"));

		driver.switchTo().defaultContent();
		if (doesElementExist2(properties.getProperty("Proofs"), 5)) {	    
			WebElement proofsmnu = driver.findElement(By.cssSelector(properties.getProperty("Proofs")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", proofsmnu);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Proofs menu..");
		} else {
			log.logLine(Testname, true, "Clicking on Proofs menu is failed");
			throw new Exception("Clicking on Proofs menu is failed");
		}

		log.logLine(Testname, false, "Navigation to Proofs Page is successful");	    
		Thread.sleep(1000);
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
					if (lnk.getText().equalsIgnoreCase(ClntName)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
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
						if (lnk.getText().equalsIgnoreCase(ClntName)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
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
		String AppName = test.readColumnData("ApplicationName", sheetname);

		if (doesElementExist2(properties.getProperty("selAppl1"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selAppl1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on ApplicationName dropdown..");

			if (doesElementExist2(properties.getProperty("ApplOpts1"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ApplOpts1")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equalsIgnoreCase(AppName)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
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
						if (lnk.getText().equalsIgnoreCase(AppName)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
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
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on OK button to view the proof");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the proof is failed");
			throw new Exception("Clicking on OK button to view the proof is failed");
		}

	}		


	public boolean Compareprooftest1(String Testname,String Randno,String Randno1) throws Exception {
		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(10000);
		if (doesElementExist2(properties.getProperty("compareproof"), 5)) {	    
			WebElement comp= driver.findElement(By.cssSelector(properties.getProperty("compareproof")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", comp);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Navigation to Compare proofs is successful");
		}else if (doesElementExist(properties.getProperty("compareproof1"), 5)) {	    
			WebElement comp= driver.findElement(By.xpath(properties.getProperty("compareproof1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", comp);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Navigation to Compare proofs is successful");
		}  else {
			log.logLine(Testname, true, "Navigation to Compare proofs is failed");
			//throw new Exception("Navigation to Compare proofs is failed");
		}

		Thread.sleep(2000);
		driver.switchTo().frame("iframeContainer");       

		if (doesElementExist2(properties.getProperty("Test"), 5)) {	    
			WebElement test1= driver.findElement(By.cssSelector(properties.getProperty("Test")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", test1);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on Test button is successful");
		} else {
			log.logLine(Testname, true, "Clicking on Test button is failed");
			//throw new Exception("Clicking on Test button is failed");
		}
		Thread.sleep(2000);

		FileUpload1 =EDITbetaPDFCreate(Randno);

		if (doesElementExist(properties.getProperty("Browse1"), 5)) {	    
			WebElement descp = driver.findElement(By.xpath(properties.getProperty("Browse1")));
			descp.sendKeys(FileUpload1);			
			log.logLine(Testname, false, "Entering the 'File A' name "+FileUpload1+" in textbox is successful");
		} else {
			log.logLine(Testname, true, "Entering the 'File A' name "+FileUpload1+" in textbox is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the 'File A' name in textbox is failed");
		}
		Thread.sleep(2000);

		FileUpload2 = EDITbetaPDFCreate1(Randno1);

		if (doesElementExist(properties.getProperty("Browse2"), 5)) {	    
			WebElement descp = driver.findElement(By.xpath(properties.getProperty("Browse2")));
			descp.sendKeys(FileUpload2);			
			log.logLine(Testname, false, "Entering the 'File B' name "+FileUpload2+" in textbox is successful");
		} else {
			log.logLine(Testname, true, "Entering the 'File A' name "+FileUpload2+" in textbox is failed");
			driver.switchTo().defaultContent();
			throw new Exception("Entering the 'File A' name in textbox is failed");
		}

		log.logLine(Testname, false, "Deleting the 'ViewFile' from download before performing compare action");

		try {			
			File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/ViewFile");
			if (fileTemp1.exists()){
				fileTemp1.delete();
				log.logLine(Testname, false, "The Existing 'ViewFile' has been deleted from download folder");
			}else{
				log.logLine(Testname, false, " 'ViewFile' does not exists in folder");
			}
		} catch(Exception e){
			// if any error occurs
			e.printStackTrace();
		}	

		Thread.sleep(2000);

		if (doesElementExist(properties.getProperty("comparebutton"), 5)) {	    
			WebElement compare= driver.findElement(By.xpath(properties.getProperty("comparebutton")));
			//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", compare);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", compare);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on compare button is successful");
		} else {
			log.logLine(Testname, true, "Clicking on compare button is failed");
			throw new Exception("Clicking on compare button is failed");
		}


		Thread.sleep(3000);
		if (Initialization.Browser.equalsIgnoreCase("Chrome")){

			Set<String> handles = driver.getWindowHandles();
			firstWinHandle = driver.getWindowHandle(); 
			handles.remove(firstWinHandle);

			boolean browserexist = handles.iterator().hasNext();
			if (browserexist) {
				String winHandle=handles.iterator().next();
				if (winHandle!=firstWinHandle){
					//To retrieve the handle of second window, extracting the handle which does not match to first window handle
					secondWinHandle=winHandle; //Storing handle of second window handle

					//Switch control to new window
					driver.switchTo().window(secondWinHandle);
					log.logLine(Testname, false, "Pdf is getting displayed in second window and it is getting downloaded automatically");		 
					driver.close();
					driver.switchTo().window(firstWinHandle);

				}
				else 
				{				
					log.logLine(Testname, false, "Pdf is getting displayed in second window and it is getting downloaded automatically");		 

				}

			}

			else 
			{
				log.logLine(Testname, false, "Verifying the appearance of 'Viewfile' in downloads after clicking on compare");
				try {			
					File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/ViewFile");
					if (fileTemp1.exists()){
						log.logLine(Testname, false, "The pdf is automatically downloaded and the  Saved comparepdf(ViewFile) file exists in downloads ");					
						log.logLine(Testname, false, "Deleting the 'ViewFile' from download before performing click action in grid");
						fileTemp1.delete();
						log.logLine(Testname, false, "The Existing 'ViewFile' has been deleted from download folder");
					}
					else{
						log.logLine(Testname, true, "Saved comparePDF(ViewFile) file does not exists ");
					}

					driver.close();
					driver.switchTo().window(firstWinHandle);
				} catch(Exception e){
					// if any error occurs
					e.printStackTrace();
				}
			}

			Thread.sleep(2000);
			if (doesElementExist2(properties.getProperty("compareproof"), 5)) {	    
				WebElement comp= driver.findElement(By.cssSelector(properties.getProperty("compareproof")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", comp);
				Thread.sleep(1000);
				log.logLine(Testname, false, "Navigation to Compare proofs is successful");
			}else if (doesElementExist(properties.getProperty("compareproof1"), 5)) {	    
				WebElement comp= driver.findElement(By.xpath(properties.getProperty("compareproof1")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", comp);
				Thread.sleep(1000);
				log.logLine(Testname, false, "Navigation to Compare proofs is successful");
			}  else {
				log.logLine(Testname, true, "Navigation to Compare proofs is failed");
				//throw new Exception("Navigation to Compare proofs is failed");
			}


			log.logLine(Testname, false, "Validation of data in the grid  starts");	
			Thread.sleep(2000);
			String[] Sort = new String[50];
			String[] Sort1 = new String[50];
			String[] Sort2 = new String[50];
			String[] date= new String[50];

			driver.switchTo().frame("iframeContainer");       
			List<WebElement> DataCnt1= driver.findElements(By.xpath(".//*[@id='grdComparerPDF']/table/tbody/tr"));

			if(doesElementExist(properties.getProperty("docname"), 5)){
				if(DataCnt1.size()!='0'){
					for(int i = 1; i <= DataCnt1.size(); i++) {
						Sort[i-1] = driver.findElement(By.xpath(".//*[@id='grdComparerPDF']/table/tbody/tr["+i+"]/td[8]")).getText();
						Sort1[i-1] = driver.findElement(By.xpath(".//*[@id='grdComparerPDF']/table/tbody/tr["+i+"]/td[9]")).getText();
						Sort2[i-1] = driver.findElement(By.xpath("	.//*[@id='grdComparerPDF']/table/tbody/tr["+i+"]/td[6]")).getText();
						date=Sort2[i-1].split(" ");
						Thread.sleep(2000);
						DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");		
						Date date1 = new Date();
						String todaysDate = dateFormat.format(date1);

						String[] file1=new String[30];
						file1=FileUpload1.split("\\\\");

						String[] file2=new String[30];
						file2=FileUpload2.split("\\\\");




						if((Sort[i-1].equalsIgnoreCase(file1[3]))&&(Sort1[i-1].equalsIgnoreCase(file2[3]))&&(date[0].equalsIgnoreCase(todaysDate))){
							log.logLine(Testname, false, "Comparision between file1 "+file1[3]+" and file2 "+file2[3]+" is successful and the date of comparision is "+date[0]);
							WebElement docnamelnk= driver.findElement(By.xpath(".//*[@id='grdComparerPDF']/table/tbody/tr["+i+"]/td[5]/a"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", docnamelnk);

							Thread.sleep(6000);

							Set<String> handles1 = driver.getWindowHandles();
							firstWinHandle = driver.getWindowHandle(); 
							handles1.remove(firstWinHandle);

							boolean browserexist1 = handles1.iterator().hasNext();
							if (browserexist1) {
								String winHandle=handles1.iterator().next();
								if (winHandle!=firstWinHandle){
									//To retrieve the handle of second window, extracting the handle which does not match to first window handle
									secondWinHandle=winHandle; //Storing handle of second window handle

									driver.switchTo().window(secondWinHandle);
									log.logLine(Testname, false, "Pdf is getting displayed in second window and it is getting downloaded automatically");		 
									driver.close();
									driver.switchTo().window(firstWinHandle);

								}
								else 
								{				
									log.logLine(Testname, false, "Pdf is getting displayed in second window and it is getting downloaded automatically");		 

								}

							}

							else 
							{
								try {			
									File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/ViewFile");
									if (fileTemp1.exists()){
										log.logLine(Testname, false, "The pdf is automatically downloaded and the  Saved comparepdf(ViewFile) file exists in downloads ");
									}else{
										log.logLine(Testname, true, "Saved comparePDF(ViewFile) file does not exists ");
									}

									driver.close();
									driver.switchTo().window(firstWinHandle);
								} catch(Exception e){
									// if any error occurs
									e.printStackTrace();
								}
							}
							break;

						}
						else{
							log.logLine(Testname, false, "Comparision between file1 "+file1[3]+" and file2 "+file2[3]+" and date "+"  is failed");						
						}
					}
				}
				else{log.logLine(Testname, false, "There is no data in the grid to display");} }
			}

		else if((Initialization.Browser.equalsIgnoreCase("firefox"))||(Initialization.Browser.equalsIgnoreCase("FF"))){

			Set<String> handles = driver.getWindowHandles();
			firstWinHandle = driver.getWindowHandle(); 

			boolean browserexist = handles.iterator().hasNext();
			if (browserexist) {
				String winHandle=handles.iterator().next();
				if (winHandle!=firstWinHandle){				
					log.logLine(Testname, false, "Pdf is getting displayed in second window and it is getting downloaded automatically");	
					//driver.close();
				}
				else 
				{log.logLine(Testname, false, "Pdf is not getting displayed in second window");}
			}

			Thread.sleep(2000);

			log.logLine(Testname, false, "Verifying the appearance of 'Viewfile' in downloads after clicking on compare");

			try {			
				File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/ViewFile");
				if (fileTemp1.exists()){
					log.logLine(Testname, false, "The pdf is automatically downloaded and the  Saved comparepdf(ViewFile) file exists in downloads ");
				}else{
					log.logLine(Testname, true, "Saved comparePDF(ViewFile) file does not exists ");
				}
			} catch(Exception e){
				// if any error occurs
				e.printStackTrace();
			}

			Thread.sleep(3000);

			log.logLine(Testname, false, "Delete action the 'ViewFile' from download before performing click action in grid");

			try {			
				File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/ViewFile");
				if (fileTemp1.exists()){
					fileTemp1.delete();
					log.logLine(Testname, false, "The Existing 'ViewFile' has been deleted from download folder");
				}else{
					log.logLine(Testname, false, " 'ViewFile' does not exists in folder");
				}
			} catch(Exception e){
				// if any error occurs
				e.printStackTrace();
			}	


			Thread.sleep(3000);

			log.logLine(Testname, false, "Validation of data in the grid  starts");	
			Thread.sleep(2000);
			String[] Sort = new String[50];
			String[] Sort1 = new String[50];
			String[] Sort2 = new String[50];
			String[] date= new String[50];

			List<WebElement> DataCnt1= driver.findElements(By.xpath(".//*[@id='grdComparerPDF']/table/tbody/tr"));

			if(doesElementExist(properties.getProperty("docname"), 5)){
				if(DataCnt1.size()!='0'){
					for(int i = 1; i <= DataCnt1.size(); i++) {
						Sort[i-1] = driver.findElement(By.xpath(".//*[@id='grdComparerPDF']/table/tbody/tr["+i+"]/td[8]")).getText();
						Sort1[i-1] = driver.findElement(By.xpath(".//*[@id='grdComparerPDF']/table/tbody/tr["+i+"]/td[9]")).getText();
						Sort2[i-1] = driver.findElement(By.xpath("	.//*[@id='grdComparerPDF']/table/tbody/tr["+i+"]/td[6]")).getText();
						date=Sort2[i-1].split(" ");
						Thread.sleep(2000);
						DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");		
						Date date1 = new Date();
						String todaysDate = dateFormat.format(date1);


						String[] file1=new String[30];
						file1=FileUpload1.split("\\\\");

						String[] file2=new String[30];
						file2=FileUpload2.split("\\\\");


						if((Sort[i-1].equalsIgnoreCase(file1[3]))&&(Sort1[i-1].equalsIgnoreCase(file2[3]))&&(date[0].equalsIgnoreCase(todaysDate))){
							log.logLine(Testname, false, "Comparision between file1 "+file1[3]+" and file2 "+file2[3]+" is successful and the date of comparision is "+date[0]);
							WebElement docnamelnk= driver.findElement(By.xpath(".//*[@id='grdComparerPDF']/table/tbody/tr["+i+"]/td[5]/a"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", docnamelnk);

							Thread.sleep(2000);

							Set<String> handles1 = driver.getWindowHandles();
							firstWinHandle = driver.getWindowHandle(); 
							handles1.remove(firstWinHandle);

							boolean browserexist1 = handles.iterator().hasNext();
							if (browserexist1) {
								String winHandle=handles.iterator().next();
								if (winHandle!=firstWinHandle){				
									log.logLine(Testname, false, "Pdf is getting displayed in second window and it is getting downloaded automatically");	
									//driver.close();
								}}	
							Thread.sleep(1000);

							log.logLine(Testname, false, "Verifying the appearance of 'Viewfile' in downloads after clicking on link in the grid");

							try {			
								File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/ViewFile");
								if (fileTemp1.exists()){
									log.logLine(Testname, false, "The pdf is automatically downloaded and the  Saved comparepdf(ViewFile) file exists in downloads ");
								}else{
									log.logLine(Testname, true, "Saved comparePDF(ViewFile) file does not exists ");
								}
							} catch(Exception e){
								// if any error occurs
								e.printStackTrace();
							}

							break;

						}
						else{
							log.logLine(Testname, true, "Comparision between file1 "+file1[3]+" and file2 "+file2[3]+" and date "+"  is failed");						
						}
					}}
				else{log.logLine(Testname, true, "There is no data in the grid to display");} }


		}

		driver.switchTo().defaultContent();
		return true;
	}


	public static String EDITbetaPDFCreate(String Randno) throws Exception {

		InputStream inStream = null;
		OutputStream outStream = null;

		File file1, file2 = null;

		try { 
			file1 = new File("C:\\Pivot_Portal\\Test Data\\AutoTestData.pdf");
			file2 = new File("C:\\Pivot_Portal\\Test Data\\AutoPDF_"+Randno+".pdf");


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


	public static String EDITbetaPDFCreate1(String Randno) throws Exception {

		InputStream inStream = null;
		OutputStream outStream = null;

		File file1, file2 = null;

		try { 
			file1 = new File("C:\\Pivot_Portal\\Test Data\\AutoTestData3.pdf");
			file2 = new File("C:\\Pivot_Portal\\Test Data\\AttachPDF_1_"+Randno+".pdf");


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

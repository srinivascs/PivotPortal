package pivotModules;
import java.io.IOException;
import java.util.List;

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
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class AuditSubSegments extends Page {
	
	public String path;
	public String segementid1;
	public String segmentdescription1;
	public String segementid2;
	public String segmentdescription2;
	public String subsegmentid;
	public String subsegmentdescription;


	public AuditSubSegments(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}
	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}

	public boolean Auditspage(String AccNo, String Testname) throws Exception {

		InputOutputData test = new InputOutputData();		
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(1000);
		
		driver.switchTo().defaultContent();
		if (doesElementExist2(properties.getProperty("Audits"), 5)) {	    
			WebElement reportsmnu = driver.findElement(By.cssSelector(properties.getProperty("Audits")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", reportsmnu);
			Thread.sleep(1000);
			log.logLine(Testname, false, "Navigation to Audits page successful");
		} else {
			log.logLine(Testname, true, "Clicking on Audits menu is failed");
			throw new Exception("Clicking on Audits menu is failed");
		}	    


		
		boolean CliSelected = false;
		String ClntName = test.readColumnData("ClientName", sheetname);

		if (doesElementExist2(properties.getProperty("selClint1"), 5)) {	   
			WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selClint1")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);

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

				log.logLine(Testname, false, "Clicking on ApplicationName dropdown..");

				if (doesElementExist2(properties.getProperty("ApplOpts2"), 5)) {
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ApplOpts2")));
					for (WebElement lnk:selopts) {
						if (lnk.getText().contains(AppName)) {
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
		

		if (doesElementExist2(properties.getProperty("Okbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Okbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on OK button to view the Audits");
		} else {
			log.logLine(Testname, true, "Clicking on OK button to view the Audits is failed");
			throw new Exception("Clicking on OK button to view the Audits is failed");
		}	    


		//________________1 TC - Sub-Segments form-Audit Sub-Segments Form should be displayed__________________________________________________________________________________________________		    

		ClkAuditRules(Testname);		
		
		ClkSubSegment(Testname);
		SubSegmentFormAppear(Testname);
		
		CleanUp(Testname);
		ClkCancelButton(Testname );
		ClkSubSegment(Testname);
		
		AddNewSubsegment(Testname) ; 
		
		segementid1=test.readColumnData("ExtraSegId", sheetname);
		segmentdescription1=test.readColumnData("ExtraSegDecsp", sheetname);
		
		
		if (doesElementExist2("div[class='filter panel-container'] "+path+" input[name='subsegmentid']", 5)) {		
			WebElement kNametxt1 = driver.findElement(By.cssSelector("div[class='filter panel-container'] "+path+" input[name='subsegmentid']"));
			Thread.sleep(2000);
			kNametxt1.clear();
			
			if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer")))
				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + segementid1 +"')", kNametxt1);
			else
				kNametxt1.sendKeys(segementid1);
			
			PleasewaitDisappear();
			log.logLine(Testname, false, "Entered value in the sub-segment Id textbox in Sub-Segments of Audits Rules");
		}
		
		if (doesElementExist2("div[class='filter panel-container'] "+path+" input[name='subsegmentdescription']", 5)) {	 
			WebElement kRegEx1 = driver.findElement(By.cssSelector("div[class='filter panel-container'] "+path+" input[name='subsegmentdescription']"));
			Thread.sleep(2000);			
			kRegEx1.clear();
			
			if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer")))
				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + segmentdescription1 +"')", kRegEx1);
			else
				kRegEx1.sendKeys(segmentdescription1);
			
			
			PleasewaitDisappear();
			log.logLine(Testname, false, "Entered value in the sub-segment Description textbox in Sub-Segments of Audits Rules");
		}
		Thread.sleep(5000);
		ClkSaveButton(Testname);
		
		//AlertMaxSubSegments(Testname) ;	
		
		Thread.sleep(5000);
		Alert Al = driver.switchTo().alert() ;
		Al.accept();
		ClkCancelButton(Testname);
		ClkSubSegment(Testname);
		ClkSaveButton(Testname);
		
		ClkSubSegment(Testname);
		CleanUp(Testname);
		
		for(int cntSubMaxKeys=1; cntSubMaxKeys<3; cntSubMaxKeys++ ){     	
			
			if (doesElementExist2(properties.getProperty("subSegmentId"+cntSubMaxKeys), 5)) {
				WebElement kNametxt2 = driver.findElement(By.cssSelector(properties.getProperty("subSegmentId"+cntSubMaxKeys)));
				kNametxt2.clear();
				
				if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer")))
					((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + segementid1 +"')", kNametxt2);
				else
					kNametxt2.sendKeys(segementid1);
				
				PleasewaitDisappear();
				log.logLine(Testname, false, "Entered value in the sub-segment Id textbox in Sub-Segments of Audits Rules");
			}
				  
	
			if (doesElementExist2(properties.getProperty("subSegmentDescription"+cntSubMaxKeys), 5)) {	    
				WebElement kRegEx2 = driver.findElement(By.cssSelector(properties.getProperty("subSegmentDescription"+cntSubMaxKeys)));
				Thread.sleep(2000);
				kRegEx2.clear();
				
				if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer")))
					((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + segmentdescription1 +"')", kRegEx2);
				else
					kRegEx2.sendKeys(segmentdescription1);
				
				PleasewaitDisappear();
				log.logLine(Testname, false, "Entered value in the sub-segment Description textbox in Sub-Segments of Audits Rules");
			}
			
				AddButton(Testname) ; 
				
			}
		
		ClkSaveButton(Testname);
		
		//AlertDuplicateSubSegment(Testname);
		Thread.sleep(2000);
		Alert Alrt = driver.switchTo().alert() ;
		Alrt.accept();
		
		
		//ClkSubSegment(Testname);
		CleanUp(Testname);
		
		for(int cntSubMaxKeys=1; cntSubMaxKeys<3; cntSubMaxKeys++ ){      
			
			if (doesElementExist2(properties.getProperty("subSegmentId"+cntSubMaxKeys), 5)) {
				WebElement kNametxt2 = driver.findElement(By.cssSelector(properties.getProperty("subSegmentId"+cntSubMaxKeys)));
				kNametxt2.clear();
				
				if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer")))
					((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + segementid1 +"')", kNametxt2);
				else
					kNametxt2.sendKeys(segementid1);
				
				
				PleasewaitDisappear();
				log.logLine(Testname, false, "Entered value in the sub-segment Id textbox in Sub-Segments of Audits Rules");
			}
			  
			    
			    
		if (cntSubMaxKeys==2){	  

			if (doesElementExist2(properties.getProperty("subSegmentDescription"+cntSubMaxKeys), 5)) {	    
				WebElement kRegEx2 = driver.findElement(By.cssSelector(properties.getProperty("subSegmentDescription"+cntSubMaxKeys)));
				kRegEx2.clear();
				log.logLine(Testname, false, "Entered value in the sub-segment Description textbox in Sub-Segments of Audits Rules");
			}
		}else {
			if (doesElementExist2(properties.getProperty("subSegmentDescription"+cntSubMaxKeys), 5)) {	    
				WebElement kRegEx2 = driver.findElement(By.cssSelector(properties.getProperty("subSegmentDescription"+cntSubMaxKeys)));
				kRegEx2.clear();
				
				if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer")))
					((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + segmentdescription1 +"')", kRegEx2);
				else
					kRegEx2.sendKeys(segmentdescription1);
				
				PleasewaitDisappear();
				log.logLine(Testname, false, "Entered value in the sub-segment Description textbox in Sub-Segments of Audits Rules");
			}
		}
		
		
			if(cntSubMaxKeys==1){			
			AddButton(Testname) ;
			}
				
		}
		ClkSaveButton(Testname);
		//AlertBadData(Testname);
		
		Thread.sleep(2000);
		Alert Alrt1 = driver.switchTo().alert() ;
		Alrt1.accept();
		
		//ClkSubSegment(Testname);
		CleanUp(Testname);
		
		for(int cntSubMaxKeys=1; cntSubMaxKeys<3; cntSubMaxKeys++ ){
		
			
			if (doesElementExist2(properties.getProperty("subSegmentId"+cntSubMaxKeys), 5)) {
				WebElement kNametxt2 = driver.findElement(By.cssSelector(properties.getProperty("subSegmentId"+cntSubMaxKeys)));
				kNametxt2.clear();
				
				if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer")))
					((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + subsegmentid+cntSubMaxKeys +"')", kNametxt2);
				else
					kNametxt2.sendKeys(subsegmentid+cntSubMaxKeys);
				
				log.logLine(Testname, false, "Entered value in the sub-segment Id textbox in Sub-Segments of Audits Rules");
			}
				  
	
			if (doesElementExist2(properties.getProperty("subSegmentDescription"+cntSubMaxKeys), 5)) {	    
				WebElement kRegEx2 = driver.findElement(By.cssSelector(properties.getProperty("subSegmentDescription"+cntSubMaxKeys)));
				kRegEx2.clear();
				
				if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer")))
					((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + subsegmentdescription+cntSubMaxKeys +"')", kRegEx2);
				else
					kRegEx2.sendKeys(subsegmentdescription+cntSubMaxKeys);
				
				log.logLine(Testname, false, "Entered value in the sub-segment Description textbox in Sub-Segments of Audits Rules");
			}
			
			if(cntSubMaxKeys==1){
				AddButton(Testname) ;
			}
		
			
		
		}
		
		ClkSaveButton(Testname);
		
		SubSegModifyEx(Testname);
		
		
		
		
		return true;
		}

	//******************************************Method to Clear text*************************************************************************************

	public boolean Clear1(String Testname, String Cnt) throws Exception {


		if (doesElementExist2(properties.getProperty("subSegmentId"+Cnt), 5)) {

			WebElement subSegId1 = driver.findElement(By.cssSelector(properties.getProperty("subSegmentId"+Cnt)));
			String subSegValCl = subSegId1.getAttribute("value");
			int lenCl= subSegValCl.length();  				

			if(lenCl>1||lenCl==1){

				Keys[] keys = new Keys[lenCl]; 
				for (int i = 0 ; i < keys.length ; i++) 
					keys[i] = Keys.BACK_SPACE; 
				subSegId1.sendKeys(Keys.chord(keys)); 	

				waitUntilRetrive();	
				Thread.sleep(5000);
				log.logLine(Testname, false, "Cleared ID Text From Sub Segment" +Cnt);

			}

		} 


		if (doesElementExist2(properties.getProperty("subSegmentDescription"+Cnt), 5)) {

			WebElement subSegDesc = driver.findElement(By.cssSelector(properties.getProperty("subSegmentDescription"+Cnt)));
			String subSegDesVal = subSegDesc.getAttribute("value");
			int lenCl= subSegDesVal.length();  				

			if(lenCl>1||lenCl==1){
				//subSegDesc.clear();		

				Keys[] keys = new Keys[lenCl]; 
				for (int i = 0 ; i < keys.length ; i++) 
					keys[i] = Keys.BACK_SPACE; 
				subSegDesc.sendKeys(Keys.chord(keys)); 	

				waitUntilRetrive();	
				Thread.sleep(5000);
				log.logLine(Testname, false, "Cleared Description Text From Sub Segment" +Cnt);

			}
		} 

		Thread.sleep(4000);
		return true;

	}
	//******************************************Method to Clean up*************************************************************************************

	public boolean CleanUp(String Testname) throws Exception {


		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));	
		String adtSubMaxKeys=test.readColumnData("AMaxSubKey", "AuditMaxSubKeys");			
		int cntSubMaxKeys=Integer.parseInt(adtSubMaxKeys);		
		
	    
	    
	//    int cnt=Integer.parseInt(AdminConfig.subsegmax);
	    
	    
		if (doesElementExist2(properties.getProperty("DeleteButton"), 5)) {
			
			for (int i=1; i<=cntSubMaxKeys; i++){

			WebElement kDelBtn = driver.findElement(By.cssSelector(properties.getProperty("DeleteButton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", kDelBtn);					
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked  Delete Button");

		}
		
	}
		return true;

	}


	//***************************************Method to click Audit Rules button*******************************************************************************************		

	public boolean ClkAuditRules(String Testname ) throws Exception {
					

		if (doesElementExist2(properties.getProperty("AuditRules"), 5)) {	    
			WebElement ruleslnk = driver.findElement(By.cssSelector(properties.getProperty("AuditRules")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ruleslnk);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Audit Rules...under Audits");
		} else {
			log.logLine(Testname, true, "Audit Rules link does not exist");
			throw new Exception("Audit Rules link does not exist");
		}	

		driver.switchTo().frame("iframeContainer");				

		return true;
	}	

	//******************************************Method to click Sub segments button************************************************************************************		

	public boolean ClkSubSegment( String Testname  ) throws Exception {	
		
		if (doesElementExist2(properties.getProperty("subSegments"), 5)) {	    
			WebElement subSegmentsBtn = driver.findElement(By.cssSelector(properties.getProperty("subSegments")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", subSegmentsBtn);
			log.logLine(Testname, false, "Clicking on Sub Segments button on top right");
		} else {
			log.logLine(Testname, true, "Sub Segments button does not exist");
			throw new Exception("Sub Segments button does not exist");
		}		

		return true;
	}	


	//*************************************Method to check if Audit Sub Segments Form appears ****************************************************************************************************************************************************						


	public boolean SubSegmentFormAppear( String Testname ) throws Exception {			

		if (doesElementExist2(properties.getProperty("DescriptionHeader"), 25)){
			WebElement DescHead = driver.findElement(By.cssSelector(properties.getProperty("DescriptionHeader")));							  
			log.logLine(Testname, false, "Validation Pass - The Sub Segment Form is displayed");

		} else {
			log.logLine(Testname, true, "Validation Fail -The Sub Segment Form is NOT displayed");
		}		

		return true;
	}


	//**********************************Method to Click Cancel Button*************************************************************************************

	public boolean ClkCancelButton( String Testname ) throws Exception {			

		if(doesElementExist2(properties.getProperty("CancelButton"), 5)) {	  
			WebElement CancelBtn = driver.findElement(By.cssSelector(properties.getProperty("CancelButton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", CancelBtn);
			log.logLine(Testname, false, "Clicked Cancel Button ");
		}else {
			log.logLine(Testname, true, "Cancel does not exist");
			throw new Exception("Cancel does not exist");

		}	
		
		return true;
	}


	//***********************************Method to add New Sub Segment************************************************************************************

	public boolean AddNewSubsegment(String Testname) throws Exception {			

		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();
	    subsegmentid=test.readColumnData("SegmentId", sheetname);
		subsegmentdescription = test.readColumnData("SegmentDescp", sheetname);
		
		String adtSubMaxKeys=test.readColumnData("AMaxSubKey", "AuditMaxSubKeys");			
		int cntSubMaxKeys=Integer.parseInt(adtSubMaxKeys);		
		
		
		  
		         
	    path ="div";
	    
    	for (int Cnt=0; Cnt<cntSubMaxKeys;Cnt++){
	    	
	    	
	    	if (doesElementExist2("div[class='filter panel-container'] "+path+" input[name='subsegmentid']", 5)) {	    
				WebElement kNametxt = driver.findElement(By.cssSelector("div[class='filter panel-container'] "+path+" input[name='subsegmentid']"));
				kNametxt.clear();
				
				if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer")))
					((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + subsegmentid+Cnt +"')", kNametxt);
				else
					kNametxt.sendKeys(subsegmentid+Cnt);
				
				log.logLine(Testname, false, "Entered "+subsegmentid+Cnt+" in the segment id textbox");
	    	}else{
	    		log.logLine(Testname, false, "Entered "+subsegmentid+Cnt+" in the segment id textbox");
	    		throw new Exception("Entered "+subsegmentid+Cnt+" in the segment id textbox");
	    	}
			
			
	    	if (doesElementExist2("div[class='filter panel-container'] "+path+" input[name='subsegmentdescription']", 5)) {	    
				WebElement kRegEx = driver.findElement(By.cssSelector("div[class='filter panel-container'] "+path+" input[name='subsegmentdescription']"));
				kRegEx.clear();
				
				if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer")))
					((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + subsegmentdescription+Cnt +"')", kRegEx);
				else
					kRegEx.sendKeys(subsegmentdescription+Cnt);	
				log.logLine(Testname, false, "Entered "+subsegmentdescription+Cnt+" in the segment description textbox");
	    	}else{
	    		log.logLine(Testname, false, "Entered "+subsegmentdescription+Cnt+" in the segment description textbox");
	    		throw new Exception("Entered "+subsegmentdescription+Cnt+" in the segment description textbox");
	    	}
			
	    	
			if (doesElementExist2(properties.getProperty("AddButton"), 5)) {	    
				WebElement addbtn = driver.findElement(By.cssSelector(properties.getProperty("AddButton")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", addbtn);
				PleasewaitDisappear();
				log.logLine(Testname, false, "Clicking on add button of Sub-Segments");
		    } else {
				log.logLine(Testname, true, "Clicking on add button of Sub-Segments is failed");
				throw new Exception("Clicking on add button of Sub-Segments is failed");
			}
	    	
	    		
				path= path+ "+div";
		    
	   }
			
	  
		return true;

		
	}		

	//******************************************Method to validate if Sub Segments are created successfully********************************************************************

	public boolean ValidateSubSegmentsCreated( String Testname) throws Exception {
		
		//ClkSubSegment(Testname);
		
		for (int cntSubMaxKeys=1;cntSubMaxKeys<3;cntSubMaxKeys++){
			
			if(cntSubMaxKeys==2){

				if (doesElementExist2(properties.getProperty("subSegmentId"+cntSubMaxKeys), 5)) {
					WebElement kNametxt2 = driver.findElement(By.cssSelector(properties.getProperty("subSegmentId"+cntSubMaxKeys)));
					kNametxt2.click();
					String val = kNametxt2.getAttribute("value");
					if(val.equals(subsegmentid+cntSubMaxKeys)){
						log.logLine(Testname, false, "Modification of the segment id is successful");
						
					}else{
						log.logLine(Testname, true, "Modification of the segment id is unsuccessful");
					}
					  
				}
				
				
				if (doesElementExist2(properties.getProperty("subSegmentDescription"+cntSubMaxKeys), 5)) {
					WebElement kNametxt2 = driver.findElement(By.cssSelector(properties.getProperty("subSegmentDescription"+cntSubMaxKeys)));
					kNametxt2.click();
					String val = kNametxt2.getAttribute("value");
					if(val.equals(segmentdescription2)){
						log.logLine(Testname, false, "Modification of the segment description is successful");
						
					}else{
						log.logLine(Testname, true, "Modification of the segment description is unsuccessful");
					}
					  
				}
			}
		}
		
		ClkCancelButton(Testname);
	
		
		return true; 
	}


	//****************************************Method to click Save button on the form *********************************************************************************

	public boolean ClkSaveButton(String Testname) throws Exception {
		
		if (doesElementExist2(properties.getProperty("saveButton"), 5)) {	 

			WebElement SveBtn = driver.findElement(By.cssSelector(properties.getProperty("saveButton")));
			Thread.sleep(2000);
			SveBtn.click();				
			Thread.sleep(2000);
			log.logLine(Testname, false, "Clicked Save Button");

		} else {

			log.logLine(Testname, true, "Save Button does not exist");
			throw new Exception("Save Button does not exist");

		}	

		return true;

	}

	//******************************************Method to click + button ****************************************************************************************************

	public boolean AddButton(String Testname) throws Exception {
		


		if (doesElementExist2(properties.getProperty("AddButtonSubSeg"), 5)) {	    
			WebElement sAdd = driver.findElement(By.cssSelector(properties.getProperty("AddButtonSubSeg")));
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("scroll(0,250);");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", sAdd);
			((JavascriptExecutor) driver).executeScript("scroll(0,250);");

			log.logLine(Testname, false, "Clicked + Button");
		} else {
			log.logLine(Testname, true, "+ Button does not exist");
			throw new Exception("+ Button does not exist");
		}	
		return true;	
	}	

	//******************************** Method to Verify that the Alert appears for maximum Sub Segments allowed*****************************************************************			

	public boolean AlertMaxSubSegments(String Testname) throws Exception{
		
		ClkSaveButton(Testname);
		
		
		if (doesElementExist2(properties.getProperty("alertMaxSubSeg"), 5)) {

			WebElement SubMaxAlert = driver.findElement(By.cssSelector(properties.getProperty("alertMaxSubSeg")));
			String kAlertText = SubMaxAlert.getText();
			
				if (kAlertText.equals("You may not add another segment - doing so would exceed allowance")){
					log.logLine(Testname, false, "Alert for adding greater than the Max sub segment not allowed pop is displayed ");
				}
				else {	
				log.logLine(Testname, true, "Alert for adding greater than the Max sub segment not allowed pop is not displayed ");
				} 	

			log.logLine(Testname, false, "Validation Pass - Alert appears for adding greater than the Max sub segment & displays the message : " +kAlertText);

		} else {
			log.logLine(Testname, true, "Validation Fail - Alert does not appear for adding greater than the Max sub segment");
			throw new Exception("Validation Fail - Alert does not appear for adding greater than the Max sub segment ");
		}

		if (doesElementExist2(properties.getProperty("OkButton"), 5)) {


			WebElement okAlert = driver.findElement(By.cssSelector(properties.getProperty("OkButton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okAlert);

			log.logLine(Testname, false, "Clicked OK butoon on Max Segments not allowed pop up Alert  " );

		} else {
			log.logLine(Testname, true, "Max Segments Alert is not displayed hence did not click on OK button");
			throw new Exception("Max Segments Alert is not displayed hence did not click on OK button");
		}
		
		//DeleteSubSegments(Testname);   
		ClkCancelButton(Testname);
		ClkSubSegment(Testname);
		ClkSaveButton(Testname);


		return true;
	}		


	//****************************************** Method to Remove/Delete keys **************************************************************************		  

	public boolean DeleteSubSegments(String Testname) throws Exception{		

		if (doesElementExist2(properties.getProperty("DeleteButton"), 5)) {	    

			WebElement SubSegDelBtn = driver.findElement(By.cssSelector(properties.getProperty("DeleteButton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", SubSegDelBtn);
			log.logLine(Testname, false, "Clicked Delete Button to delete the sub segments");

		} else {
			log.logLine(Testname, true,  "Clicking on Delete Button to delete the sub segments is failed");
			throw new Exception("Clicking on Delete Button to delete the sub segments is failed");
		}

		return true;

	}

	//*************************************Method to Click Cancel button*********************************************************************************************

	public boolean SubSegCancelButton(String Testname) throws Exception{	

		if (doesElementExist2(properties.getProperty("CancelButton"), 5)) {	    

			WebElement SubSegCnclBtn = driver.findElement(By.cssSelector(properties.getProperty("CancelButton")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", SubSegCnclBtn);

			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicked Cancel Button");

		} else {

			log.logLine(Testname, true, "Cancel Button does not exist");


		}	
		return true; 

	}

	//********************Method to Verify that the Alert appears for Bad data in the fields"***************************************************************************************************************************************			

	public boolean AlertBadData(String Testname) throws Exception{		

			
		
		if (doesElementExist2(properties.getProperty("AlertBadDataTxt"), 5)) {
			String kABadDataTxt = driver.findElement(By.cssSelector(properties.getProperty("AlertBadDataTxt"))).getText();
			
			if(kABadDataTxt.equals("Bad data in the following field/s: (Description)")){
				log.logLine(Testname, false, "Alert Message is displayed : for bad data ");
			}else{
				log.logLine(Testname, true, "Alert Message is not displayed : for bad data ");
			}

		}else{
			log.logLine(Testname, true, "Alert Message is not displayed : for bad data ");
			throw new Exception("Alert Message is not displayed :  for bad data");
		}

		if (doesElementExist2(properties.getProperty("OkButton"), 5)) {
				WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("OkButton")));
				Thread.sleep(2000);
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
				log.logLine(Testname, false, "clicked on ok button of the Bad Data pop up "); 
		}else {

			log.logLine(Testname, true, "clicked on ok button of the Bad Data pop up is failed"); 
			throw new Exception("clicked on ok button of the Bad Data pop up is failed");

		}

		//DeleteSubSegments(Testname);   

		ClkCancelButton(Testname);

		return true;
	}	

	//****************************Method to validate that Sub Segment does NOT get created when clicked on CANCEL button**************************************************

	public boolean ValidateSubSegNotCreated(String Testname, String Cnt ) throws Exception{

		ClkSubSegment(Testname);
		Thread.sleep(2000);

		if (doesElementExist2(properties.getProperty("KeyName"+Cnt), 5)) {

			WebElement subSegNametxt = driver.findElement(By.cssSelector(properties.getProperty("KeyName"+Cnt)));
			String val = subSegNametxt.getAttribute("value");
			int len= val.length();  

			if(!(len==0)){  
				log.logLine(Testname, true , "Validation Failed - Sub Segment  "+Cnt+" was  created even after pressing CANCEL button");	
			} 
			else {
				log.logLine(Testname, false , "Validation Pass - Sub Segment  " +Cnt+ " was NOT created, all the non saved values were cleared ");
			}	
		}    

		return true; 

	}

	//******************************************Method to verify Duplicate Sub Segment Alert appears**************************************************************			

	public boolean AlertDuplicateSubSegment(String Testname) throws Exception{	


		/*AddButton(Testname);

		if (doesElementExist2(properties.getProperty("subSegmentId"), 20)) {		
			Thread.sleep(2000);
			WebElement SubSegId = driver.findElement(By.cssSelector(properties.getProperty("subSegmentId")));
			log.logLine(Testname, false, "Sub Segment Id  textbox is displayed");
			SubSegId.sendKeys("1");
			log.logLine(Testname, false, "Entered Duplicate Id in the text box");

			WebElement stxt2 = driver.findElement(By.cssSelector(properties.getProperty("subSegmentDescription2")));
			log.logLine(Testname, false, "Sub Segment Description  textbox  is displayed");
			Thread.sleep(2000);
			stxt2.sendKeys("|a-z|1");			
			log.logLine(Testname, false, "Entered Duplicate Description in the text box");

		} else {
			log.logLine(Testname, true, "Sub Segment Id textbox  is NOT displayed");
			throw new Exception("Sub Segment Description textbox  is NOT displayed");
		}	


		ClkSaveButton(Testname);
		Thread.sleep(5000); 

--------------------------------------------*/
		
		if (doesElementExist2(properties.getProperty("AlertDupText"), 5)) {  

			WebElement sAlertDupl = driver.findElement(By.cssSelector(properties.getProperty("AlertDupText")));   
			String sAlDupl = sAlertDupl.getText();
			if(sAlDupl.equals("Can not insert a duplicate segment id.")){
				
				log.logLine(Testname, false, "Alert message: for adding the duplicate sub segments is displayed");
			}else{
					log.logLine(Testname, true, "Alert message: for adding the duplicate sub segments is not displayed");
			}
			log.logLine(Testname, false, "Validation Pass - Duplicate Sub Segment Alert appears. Exact message: "+sAlDupl);


		} else {
			log.logLine(Testname, true, "Validation Fail - Alert for 'Duplicate sub segments not allowed' is NOT displayed ");				
			throw new Exception("Validation Fail - Alert for 'Duplicate sub segments not allowed' is NOT displayed ");
		}

		((JavascriptExecutor) driver).executeScript("scroll(0,800);");

		if (doesElementExist2(properties.getProperty("OkButton"), 5)) {
			WebElement sOkBtn = driver.findElement(By.cssSelector(properties.getProperty("OkButton")));
			sOkBtn.click();			
			log.logLine(Testname, false, "Clicked OK button on the pop up for Duplicate Sub segment");				
		} else {

			log.logLine(Testname, true, "OK button on Duplicate Sub segment pop up is not displayed ");

		}
		((JavascriptExecutor) driver).executeScript("scroll(0,800);");

		//DeleteSubSegments(Testname);
		//SubSegCancelButton(Testname);
		
		ClkCancelButton(Testname);

		return true;	
	}

	//***************************Method to validate that sub Segment is deleted ******************************************************************************************


	public boolean ValidateSubSegmentDeleted(String Testname, String Cnt) throws Exception{

		if (doesElementExist2(properties.getProperty("subSegmentId"+Cnt), 5)) {

			WebElement sSegId = driver.findElement(By.cssSelector(properties.getProperty("subSegmentId"+Cnt)));
			Thread.sleep(2000);
			log.logLine(Testname, false , "Sub Segment " +Cnt+ " exists");
			String aText =	sSegId.getText();
			String valSeg = sSegId.getAttribute("value");

			int lenCl= valSeg.length();  

			if(lenCl>0) {
				log.logLine(Testname, true , "Validation Failed- Sub Segment " +Cnt+" could NOT get deleted successfully, text in Sub Segment is "+aText+" attribute value in subsegment is "+valSeg);

				DeleteSubSegments(Testname) ;
				System.out.println("Text in "+Cnt+" textbox is "+aText);			
				System.out.println("Attribute value in "+Cnt+" textbox is "+valSeg);
			} else {

				log.logLine(Testname, false , "Validation Pass - that Sub Segment " +Cnt+ " got deleted successfully");
			}
		}
		return true;
	}


	//****************************Method to Modifying an existing Key***************************************************************************************************************************************


	public boolean SubSegModifyEx(String Testname) throws Exception{
		
		InputOutputData test = new InputOutputData();		
	    test.setInputFile(properties.getProperty("InputDatafile"));
	    String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		ClkSubSegment(Testname);
		
		
		
		   segementid2 = test.readColumnData("ModifySegmnetId", sheetname);
		   segmentdescription2 = test.readColumnData("ModifySegmnetDescp", sheetname);
		   
		   for(int cntSubMaxKeys=1; cntSubMaxKeys<3; cntSubMaxKeys++){
			   
			   if(cntSubMaxKeys==2){
			  /* if (doesElementExist2(properties.getProperty("subSegmentId"+cntSubMaxKeys), 5)) {
					WebElement kNametxt2 = driver.findElement(By.cssSelector(properties.getProperty("subSegmentId"+cntSubMaxKeys)));
					Thread.sleep(2000);
					kNametxt2.clear();
					kNametxt2.sendKeys(segementid2);				
					log.logLine(Testname, false, "Entered Key Name in the textbox");
				}
					  
		      */
				if (doesElementExist2(properties.getProperty("subSegmentDescription"+cntSubMaxKeys), 5)) {	    
					WebElement kRegEx2 = driver.findElement(By.cssSelector(properties.getProperty("subSegmentDescription"+cntSubMaxKeys)));
					Thread.sleep(2000);
					kRegEx2.clear();
		
					if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer")))
						((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + segmentdescription2 +"')", kRegEx2);
					else
						kRegEx2.sendKeys(segmentdescription2);
					
					log.logLine(Testname, false, "Entered sub segment description in the textbox");
				
				}
			   }
		   }
		ClkSaveButton(Testname);
		Thread.sleep(2000);
		Alert Alrt1 = driver.switchTo().alert() ;
		Alrt1.accept();
		
		
		ValidateSubSegmentsCreated(Testname) ;
		return true; 
	}		

}


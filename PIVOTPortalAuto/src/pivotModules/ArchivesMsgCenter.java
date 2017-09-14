package pivotModules;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import launchAuto.Initialization;
import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ArchivesMsgCenter extends Page{

 public ArchivesMsgCenter(WebDriver driver, Log log) throws InvalidFormatException, IOException {
 			super(driver, log);
 	} 
 	@Override
 	protected void load() {}
 	@Override
 	
 	protected void isLoaded() throws Error {}
 	
 	public boolean MessageVerify1(String Randno, String Testname, String SetWindow) throws Exception {
 		  
			InputOutputData test = new InputOutputData();  
			test.setInputFile(properties.getProperty("InputDatafile"));
			String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

			Thread.sleep(4000);			
			driver.switchTo().window(SetWindow);	
					
			driver.navigate().refresh();			
			Actions builder = new Actions(driver);
			
			
			
			if ((Initialization.Browser.equals("Chrome")) || (Initialization.Browser.equals("chrome")) || (Initialization.Browser.equals("CHROME"))) {
			  //Delete the existing file before it downloads
				try {			
				        File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/Viewpdf.pdf");
				        if (fileTemp1.exists()){
				        	fileTemp1.delete();
				        	log.logLine(Testname, false, "The Viewpdf.pdf file exists and has been deleted");
			        	}else{
						    	log.logLine(Testname, false, "The Viewpdf.pdf file does not exists in downloads folder");
				    	}
			        
		       } catch(Exception e){
			        // if any error occurs
			        e.printStackTrace();
		       }
	 
			}
			
			if (doesElementExist2(properties.getProperty("cancelbtn"), 5)) {    
				WebElement canlbtn = driver.findElement(By.cssSelector(properties.getProperty("cancelbtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", canlbtn);
				Thread.sleep(2000);
			    log.logLine(Testname, false, "Clicked on the cancel button of the clent/app selection pop up window");
		    } else {
			    log.logLine(Testname, true, "Clicking on the cancel button  of the clent/app selection pop up window is failed");
			    throw new Exception("Clicking on the cancel button of the clent/app selection pop up window is failed");
		    }
			
						
				// Move cursor to the Main Menu Element
			if (doesElementExist(properties.getProperty("msgcenterimg"), 5)) { 
				WebElement msgcenter = driver.findElement(By.xpath(properties.getProperty("msgcenterimg")));
				builder.moveToElement(msgcenter).perform();
		    	//((JavascriptExecutor)driver).executeScript("$('msgcenter').hover();");

				Thread.sleep(2000);
			    log.logLine(Testname, false, "Navigating to message center envelope in new pivot link..");
			
			}else {
				 log.logLine(Testname, false, "Navigating to message center envelope in new pivot link.. is failed");
				 throw new Exception("Navigating to message center envelope in new pivot link.. is failed");
			}
			 
		    if (doesElementExist2(properties.getProperty("gettitle"), 5)) {    
				WebElement title = driver.findElement(By.cssSelector(properties.getProperty("gettitle")));
				//builder.moveToElement(title).perform();
				if(title.getText().contains(ConfigMsgCenter.msgTitle)){
					log.logLine(Testname, false, "Message title matches with the string");
				}
				else{
					log.logLine(Testname, true, "Message title does not matches with the string");	
				}
				log.logLine(Testname, false, "Message title is displayed in the message center image");
			 
		    } else {
			    log.logLine(Testname, true, "Message title is not displayed in the message center image");
			    throw new Exception("Message title is not displayed in the message center image");
		    }
		    
		  		    	
		    
		    /*
		    
		    if ((Initialization.Browser.equals("Chrome")) || (Initialization.Browser.equals("chrome")) || (Initialization.Browser.equals("CHROME"))) {   
				   
		  
		    	
		    	if (doesElementExist2(properties.getProperty("opendocicon"), 5)) {    
						WebElement opendoc = driver.findElement(By.cssSelector(properties.getProperty("opendocicon")));
						Thread.sleep(2000);
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", opendoc);
						log.logLine(Testname, false, "Click on the open document icon to view the document of the messages grid"); 
				    }else{
				    	log.logLine(Testname, true, "Click on the open document icon to view the document of the messages grid is failed");
				    	throw new Exception("Click on the open document icon to view the document of the messages grid is failed");
				    }
				    
		    
		    
				  //Delete the existing file before it downloads
					try {			
					        File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/Viewpdf.pdf");
					        if (fileTemp1.exists()){
					        	log.logLine(Testname, false, "The viewed pdf file exists and viewing the message from the Envelope is successful");
				        	}else{
						    	log.logLine(Testname, false, "The viewed pdf file does not exists and viewing the message from the Envelope is unsuccessful");
					    	}
				        
			       } catch(Exception e){
				        // if any error occurs
				        e.printStackTrace();
			       }
		    }
		  
    if ((Initialization.Browser.equalsIgnoreCase("ff")) || (Initialization.Browser.equalsIgnoreCase("firefox")) || (Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("internetexplorer"))) {
		  */
		    try {			
		        File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/Viewpdf");
		        if (fileTemp1.exists()){
		        	fileTemp1.delete();
		        	log.logLine(Testname, false, "PDF file is deleted from Download folder");
					//retval = true;
		        } else {
					log.logLine(Testname, false, "PDF file does not exists");
					//retval = false;
				}
		        
			} catch(Exception e){
		        // if any error occurs
		        e.printStackTrace();
		    }
		        
		    
		    
		    
    	if (doesElementExist2(properties.getProperty("opendocicon"), 5)) {    
				WebElement opendoc = driver.findElement(By.cssSelector(properties.getProperty("opendocicon")));
				Thread.sleep(2000);
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", opendoc);
				 Thread.sleep(3000);
				log.logLine(Testname, false, "Click on the open document icon to view the document of the messages grid");  
				
				if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) || (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) {	
					
					Robot robots=new Robot();
				    robots.keyPress(KeyEvent.VK_ALT);
		   		    robots.keyPress(KeyEvent.VK_S);
				    
				    Thread.sleep(3000);
				    robots.keyRelease(KeyEvent.VK_ALT);    
				    robots.keyRelease(KeyEvent.VK_S);
				 
		  		    
				    
				    Thread.sleep(20000);
				    
				    try {			
				        File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/Viewpdf");
				        if (fileTemp1.exists()){
				        	log.logLine(Testname, false, "PDF file is downloaded from internet explorer after cliking on icon in message center grid");
							//retval = true;
				        } else {
							log.logLine(Testname, true, "PDF file is not downloaded from internet explorer after icon in message center grid");
							//retval = false;
						}
				        
					} catch(Exception e){
				        // if any error occurs
				        e.printStackTrace();
				    }
					
			} else if ((Initialization.Browser.equals("Chrome")) || (Initialization.Browser.equals("chrome")) || (Initialization.Browser.equals("CHROME"))|| (Initialization.Browser.equals("FF")) || (Initialization.Browser.equals("ff")) || (Initialization.Browser.equals("firefox")) || (Initialization.Browser.equals("Firefox")) || (Initialization.Browser.equals("FIREFOX"))) { 		    	
				Thread.sleep(20000);
				
			    
			    Set<String> handles = driver.getWindowHandles();
			    String firstWinHandle = driver.getWindowHandle(); 
			    handles.remove(firstWinHandle);
			    
			    boolean browserexist = handles.iterator().hasNext();
			    if (browserexist) {
				    String winHandle=handles.iterator().next();
				    if (winHandle!=firstWinHandle){
				    	//To retrieve the handle of second window, extracting the handle which does not match to first window handle
				    	String secondWinHandle=winHandle; //Storing handle of second window handle
				    
				    	//Switch control to new window
				    	driver.switchTo().window(secondWinHandle);
				    	
				    	if ((Initialization.EnvirSite.equals("TEST")) || (Initialization.EnvirSite.equals("Test")) || (Initialization.EnvirSite.equals("test"))) {
					    	if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) 
					    			|| (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) 
				    			driver.get("javascript:document.getElementById('overridelink').click();");
				    	}
				    	
				    	Thread.sleep(5000);
				    	driver.close();
				    	
				    	// Switching back to parent window
					    driver.switchTo().window(firstWinHandle);
					    
					    Thread.sleep(2000);
					    driver.switchTo().defaultContent();
				    }
			    }
			}
			    log.logLine(Testname, false, "Successfully opened the document icon in message center grid ");
		    } else {
			    log.logLine(Testname, true, "Clicking on the open document icon in message center grid is failed");
			    throw new Exception("Clicking on the open document icon in message center grid is failed");
		    }
		  
    //}
			
			return true;
 	}
 	
 	
 	public boolean MessageVerify2(String AccNo, String Testname, String SetWindow) throws Exception {
		  
		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(2000);			
		driver.switchTo().window(SetWindow);
		
		
		if ((Initialization.Browser.equals("Chrome")) || (Initialization.Browser.equals("chrome")) || (Initialization.Browser.equals("CHROME"))) {
			  //Delete the existing file before it downloads
				try {			
				        File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/Viewpdf.pdf");
				        if (fileTemp1.exists()){
				        	fileTemp1.delete();
				        	log.logLine(Testname, false, "The Viewpdf.pdf file exists and has been deleted");
			        	}else{
						    	log.logLine(Testname, false, "The Viewpdf.pdf file does not exists in downloads folder");
				    	}
			        
		       } catch(Exception e){
			        // if any error occurs
			        e.printStackTrace();
		       }
	 
		}
		
		driver.navigate().refresh();
		if (doesElementExist2(properties.getProperty("cancelbtn"), 5)) {    
			WebElement canlbtn = driver.findElement(By.cssSelector(properties.getProperty("cancelbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", canlbtn);
			Thread.sleep(2000);
		    log.logLine(Testname, false, "Clicked on the cancel button of the clent/app selection pop up window");
	    } else {
		    log.logLine(Testname, true, "Clicking on the cancel button  of the clent/app selection pop up window is failed");
		    throw new Exception("Clicking on the cancel button of the clent/app selection pop up window is failed");
	    } 
		
		Actions builder = new Actions(driver);			
		if (doesElementExist(properties.getProperty("msgcenterimg"), 5)) { 
			WebElement msgcenter = driver.findElement(By.xpath(properties.getProperty("msgcenterimg")));
			
			// Move cursor to the Main Menu Element
			builder.moveToElement(msgcenter).perform();
	    	//((JavascriptExecutor)driver).executeScript("$('msgcenter').hover();");
			Thread.sleep(1000);
		    log.logLine(Testname, false, "Navigating to message center envelope in new pivot link..");
		
		}else{
			 log.logLine(Testname, false, "Navigating to message center envelope in new pivot link.. is failed");
			 throw new Exception("Navigating to message center envelope in new pivot link.. is failed");
		} 
		
	    
	        
	    if (doesElementExist2(properties.getProperty("gettitle"), 5)) {    
			WebElement title = driver.findElement(By.cssSelector(properties.getProperty("gettitle")));
			//builder.moveToElement(title).perform();
			if(title.getText().contains(ConfigMsgCenter.msgTitleEdit)){
				log.logLine(Testname, true, "Message title matches with the string");
			}
			else{
				log.logLine(Testname, false, "Message title does not matches with the string");	
			}
			log.logLine(Testname, false, "Message title is displayed in the message center image");
		    
	    } else {
		    log.logLine(Testname, true, "Message title is not displayed in the message center image");
		    throw new Exception("Message title is not displayed in the message center image");
	    } 
/*
	    if ((Initialization.Browser.equals("Chrome")) || (Initialization.Browser.equals("chrome")) || (Initialization.Browser.equals("CHROME"))) {   
			   
			  
	    	
	    	if (doesElementExist2(properties.getProperty("opendocicon"), 5)) {    
					WebElement opendoc = driver.findElement(By.cssSelector(properties.getProperty("opendocicon")));
					Thread.sleep(2000);
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", opendoc);
					log.logLine(Testname, false, "Click on the open document icon to view the document of the messages grid"); 
			    }else{
			    	log.logLine(Testname, true, "Click on the open document icon to view the document of the messages grid is failed");
			    	throw new Exception("Click on the open document icon to view the document of the messages grid is failed");
			    }
			    
	    
	    
			  //Delete the existing file before it downloads
				try {			
				        File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/Viewpdf.pdf");
				        if (fileTemp1.exists()){
				        	log.logLine(Testname, false, "The viewed pdf file exists and viewing the message from the Envelope is successful");
			        	}else{
					    	log.logLine(Testname, false, "The viewed pdf file does not exists and viewing the message from the Envelope is unsuccessful");
				    	}
			        
		       } catch(Exception e){
			        // if any error occurs
			        e.printStackTrace();
		       }
	    }
	    
	    
    if ((Initialization.Browser.equals("FF")) || (Initialization.Browser.equals("ff")) || (Initialization.Browser.equals("firefox")) || (Initialization.Browser.equals("Firefox")) || (Initialization.Browser.equals("FIREFOX")) || (Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) || (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) {
    
    */
	    
	    try {			
	        File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/Viewpdf");
	        if (fileTemp1.exists()){
	        	fileTemp1.delete();
	        	log.logLine(Testname, false, "PDF file is deleted from Download folder");
				//retval = true;
	        } else {
				log.logLine(Testname, false, "PDF file does not exists");
				//retval = false;
			}
	        
		} catch(Exception e){
	        // if any error occurs
	        e.printStackTrace();
	    }
	    
	    
	    if (doesElementExist2(properties.getProperty("opendocicon"), 5)) {    
			WebElement opendoc = driver.findElement(By.cssSelector(properties.getProperty("opendocicon")));
			Thread.sleep(2000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", opendoc);
			 Thread.sleep(3000);
			log.logLine(Testname, false, "Click on the open document icon to view the document of the messages grid");  
			
			if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) || (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) {	
				
				Robot robots=new Robot();
			    robots.keyPress(KeyEvent.VK_ALT);
	   		    robots.keyPress(KeyEvent.VK_S);
			    
			    Thread.sleep(3000);
			    robots.keyRelease(KeyEvent.VK_ALT);    
			    robots.keyRelease(KeyEvent.VK_S);
			 
	  		    
			    
			    Thread.sleep(20000);
			    
			    try {			
			        File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/Viewpdf");
			        if (fileTemp1.exists()){
			        	log.logLine(Testname, false, "PDF file is downloaded from internet explorer after cliking on icon in message center grid");
						//retval = true;
			        } else {
						log.logLine(Testname, true, "PDF file is not downloaded from internet explorer after icon in message center grid");
						//retval = false;
					}
			        
				} catch(Exception e){
			        // if any error occurs
			        e.printStackTrace();
			    }
				
		} else if ((Initialization.Browser.equals("Chrome")) || (Initialization.Browser.equals("chrome")) || (Initialization.Browser.equals("CHROME"))|| (Initialization.Browser.equals("FF")) || (Initialization.Browser.equals("ff")) || (Initialization.Browser.equals("firefox")) || (Initialization.Browser.equals("Firefox")) || (Initialization.Browser.equals("FIREFOX"))) { 		    	
			Thread.sleep(20000);			
		    
		    Set<String> handles = driver.getWindowHandles();
		    String firstWinHandle = driver.getWindowHandle(); 
		    handles.remove(firstWinHandle);
		    
		    Thread.sleep(5000);		    		    
		    boolean browserexist = handles.iterator().hasNext();
		    if (browserexist) {
			    String winHandle=handles.iterator().next();
			    if (winHandle!=firstWinHandle){
			    	//To retrieve the handle of second window, extracting the handle which does not match to first window handle
			    	String secondWinHandle=winHandle; //Storing handle of second window handle
			    
			    	//Switch control to new window
			    	driver.switchTo().window(secondWinHandle);
			    	
			    	//if ((Initialization.EnvirSite.equals("TEST")) || (Initialization.EnvirSite.equals("Test")) || (Initialization.EnvirSite.equals("test"))) {
				    	//if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) 
				    			//|| (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) 
			    		//	driver.get("javascript:document.getElementById('overridelink').click();");
			    	//}
			    	
			    	Thread.sleep(5000);
			    	driver.close();
			    	
			    	// Switching back to parent window
				    driver.switchTo().window(firstWinHandle);
				    
				    Thread.sleep(2000);
				    driver.switchTo().defaultContent();
			    }
		    }
		}
		    log.logLine(Testname, false, "Successfully opened the document icon in message center grid ");
	    } else {
		    log.logLine(Testname, true, "Clicking on the open document icon in message center grid is failed");
		    throw new Exception("Clicking on the open document icon in message center grid is failed");
	    }	
			
		
    //}	
		return true;
 	}
 	
 	public boolean MessageVerify3(String AccNo, String Testname, String SetWindow) throws Exception {
		  
		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(2000);			
		driver.switchTo().window(SetWindow);
		
		/*
		if (!(doesElementExist2(properties.getProperty("prevPivotlnk"), 5))) { 
			if (doesElementExist2(properties.getProperty("newPivotlnk"), 5)) {    
				WebElement newpivt = driver.findElement(By.cssSelector(properties.getProperty("newPivotlnk")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", newpivt);

			} else if (doesElementExist2(properties.getProperty("newPivotlnk2"), 2)) {
				WebElement newpivt2 = driver.findElement(By.cssSelector(properties.getProperty("newPivotlnk2")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", newpivt2);
			}    
			
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicking on New Pivot(Beta)!!..");

			// Wait till the page loads all the elements in it.
			//driver.switchTo().frame("iframeContainer");
			      
		}
		
		*/
		//WebElement retelm2 = waitForElement(properties.getProperty("selClint1"));
        //driver.switchTo().defaultContent();
		
		driver.navigate().refresh();
		if (doesElementExist2(properties.getProperty("cancelbtn"), 5)) {    
			WebElement canlbtn = driver.findElement(By.cssSelector(properties.getProperty("cancelbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", canlbtn);
			Thread.sleep(2000);
		    log.logLine(Testname, false, "Clicked on the cancel button of the clent/app selection pop up window");
	    } else {
		    log.logLine(Testname, true, "Clicking on the cancel button  of the clent/app selection pop up window is failed");
		    throw new Exception("Clicking on the cancel button of the clent/app selection pop up window is failed");
	    } 
		
		Actions builder = new Actions(driver);
		
		if (doesElementExist(properties.getProperty("msgcenterimg"), 5)) { 
			WebElement msgcenter = driver.findElement(By.xpath(properties.getProperty("msgcenterimg")));
			
			// Move cursor to the Main Menu Element
			builder.moveToElement(msgcenter).build().perform();

	    	//((JavascriptExecutor)driver).executeScript("$('msgcenter').hover();");
			Thread.sleep(2000);
		    log.logLine(Testname, false, "Navigating to message center envelope in new pivot link..");
		   
		}else {
			 log.logLine(Testname, false, "Navigating to message center envelope in new pivot link.. is failed");
			 //throw new Exception("Navigating to message center envelope in new pivot link.. is failed");
		}
		
	    
	    if (doesElementExist2(properties.getProperty("gettitle"), 5)) {		
			log.logLine(Testname, true, "Falied - Message title still exist in the message center envelope");
		    
	    } else {
	    	log.logLine(Testname, false, "Success - Message title does not exist in the message center envelope");		   
	    }
		
		return true;
 	}
}
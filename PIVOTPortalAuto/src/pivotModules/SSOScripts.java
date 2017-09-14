package pivotModules;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import launchAuto.Initialization;
import launchAuto.Log;
import launchAuto.Page;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class SSOScripts extends Page{
	
	public SSOScripts(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);
	}	
	@Override
	protected void load() {}
	@Override
	protected void isLoaded() throws Error {}
	
	public boolean eDeliverScript1(String AccNo, String Testname) throws Exception {
		
		log.logLine(Testname, false, "SSO Script-1 execution is started...");
		//Launch the PA HA script HTML page
		if (Initialization.EnvirSite.equalsIgnoreCase("test")) {
			log.logLine(Testname, false, "Navigating to PIVOT HA TEST SSO w dockey Script - POW9999..");
			driver.get(properties.getProperty("TestSSO1"));
		
		} else if (Initialization.EnvirSite.equalsIgnoreCase("stage")) {
			log.logLine(Testname, false, "Navigating to PIVOT HA STAGE SSO w dockey Script - POW9999..");
			driver.get(properties.getProperty("StageSSO1"));
			
		} else if (Initialization.EnvirSite.equalsIgnoreCase("prod")) {
			log.logLine(Testname, false, "Navigating to PIVOT HA Prod SSO w dockey Script - POW9999..");
			driver.get(properties.getProperty("ProdSSO1"));
		}
		
		Thread.sleep(1000);			
		SimpleDateFormat gmtDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");		
		gmtDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
		//Current Date Time in IST
		String CurDateTime = gmtDateFormat.format(new Date());
		
		if (doesElementExist(properties.getProperty("Seckeyfld"), 5)) { 
			
			WebElement secKeyStr = driver.findElement(By.xpath(properties.getProperty("Seckeyfld")));	
			String[] temp = secKeyStr.getAttribute("value").split("\\|\\|");
			
			log.logLine(Testname, false, "Clearing the content in SecKey field");
			driver.findElement(By.xpath(properties.getProperty("Seckeyfld"))).clear();
			
			log.logLine(Testname, false, "Entering the content with latest date and time in SecKey field");
			
			String Subject = temp[0]+"||"+temp[1]+"||"+temp[2]+"||"+temp[3]+"||" +CurDateTime;
			if (Initialization.Browser.equalsIgnoreCase("ie") || Initialization.Browser.equalsIgnoreCase("internetexplorer")) {
				driver.findElement(By.xpath(properties.getProperty("Seckeyfld"))).sendKeys(temp[0]+"||");
				driver.findElement(By.xpath(properties.getProperty("Seckeyfld"))).sendKeys(temp[1]+"||");
				driver.findElement(By.xpath(properties.getProperty("Seckeyfld"))).sendKeys(temp[2]+"||");
				driver.findElement(By.xpath(properties.getProperty("Seckeyfld"))).sendKeys(temp[3]+"||");
				driver.findElement(By.xpath(properties.getProperty("Seckeyfld"))).sendKeys(CurDateTime);
							
			} else {
				driver.findElement(By.xpath(properties.getProperty("Seckeyfld"))).sendKeys(Subject);
			}
		} else{
			log.logLine(Testname, true, "Entering the content with latest date and time in SecKey field is failed");
		}
		
		//Delete the existing file before it downloads
		try {			
	        File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/PVT9005_searchDocExt.aspx");
	        if (fileTemp1.exists()){
	        	fileTemp1.delete();
	        	log.logLine(Testname, false, "PDF file is deleted from the downloads folder");
				//retval = true;
	        } else {
				log.logLine(Testname, false, "PDF file not exists in downloads folder");
				//retval = false;
			}

	        
	        File fileTemp2 = new File("C:/Pivot_Portal/Test Output/PVT9005_searchDocExt.aspx");
	        if (fileTemp2.exists()){
	        	fileTemp2.delete();
	        }
		} catch(Exception e){
	        // if any error occurs
	        e.printStackTrace();
	    }
			
		((JavascriptExecutor) driver).executeScript("window.focus();");
		if (doesElementExist(properties.getProperty("SubmitBtn"), 5)) {			
			WebElement submitbtn = driver.findElement(By.xpath(properties.getProperty("SubmitBtn")));
			log.logLine(Testname, false, "Clicking on submit button..");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", submitbtn);
		} else{
			log.logLine(Testname, true, "Clicking on submit button failed");
		}		
		
		Thread.sleep(20000);
		
		boolean retval = false;		
		
		if (doesElementExist2(properties.getProperty("ChromePdfweb"), 5)) {
			log.logLine(Testname, false, "PVT9005_searchDocExt.aspx file is opened after cliking on submit button");
			log.logLine(Testname, false, "SSO Script-1 execution is successfully completed.");
			return true;
		}
		
		if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) || (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) {	
						
			Robot robot=new Robot();
		    robot.keyPress(KeyEvent.VK_ALT);
   		    robot.keyPress(KeyEvent.VK_S);
		    
		    Thread.sleep(3000);
		    robot.keyRelease(KeyEvent.VK_ALT);    
		    robot.keyRelease(KeyEvent.VK_S);
		    
  		    
		    
		    Thread.sleep(20000);
		    
		    try {			
		        File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/PVT9005_searchDocExt.aspx");
		        if (fileTemp1.exists()){
		        	log.logLine(Testname, false, "PDF file is downloaded from internet explorer after cliking on submit button");
					log.logLine(Testname, false, "SSO Script-1 execution is successfully completed.");					
					retval = true;
		        } else {
					log.logLine(Testname, true, "PDF file is not downloaded from internet explorer after cliking on submit button");
					retval = false;
				}		        
			} catch(Exception e){
		        // if any error occurs
		        e.printStackTrace();
		    }
			
		} else if ((Initialization.Browser.equals("FF")) || (Initialization.Browser.equals("ff")) || (Initialization.Browser.equals("firefox")) || (Initialization.Browser.equals("Firefox")) || (Initialization.Browser.equals("FIREFOX"))) {
			if (doesElementExist2(properties.getProperty("ffpdfweb"), 5)) { 
				log.logLine(Testname, false, "PDF file is opened in firefox after cliking on submit button");
				log.logLine(Testname, false, "SSO Script-1 execution is successfully completed.");
				retval = true;
			} else {
				log.logLine(Testname, true, "PDF file is not opened in firefox after cliking on submit button");
				log.logLine(Testname, false, "SSO Script-1 execution is successfully completed.");
				retval = false;
			}			
			
		} else if ((Initialization.Browser.equals("Chrome")) || (Initialization.Browser.equals("chrome")) || (Initialization.Browser.equals("CHROME"))) {
			//if (doesElementExist2(properties.getProperty("ChromePdfweb"), 5)) {
			File file1 = new File(System.getProperty("user.home")+"/Downloads/PVT9005_searchDocExt.aspx");
			File file2 = new File(System.getProperty("user.home")+"/Downloads/PVT9005_searchDocExt.aspx.crdownload");
	        if (file1.exists() || file2.exists()){	        	
	        
				log.logLine(Testname, false, "PVT9005_searchDocExt.aspx file is downloaded in chrome after cliking on submit button");
				log.logLine(Testname, false, "SSO Script-1 execution is successfully completed.");
				retval = true;
			} else {
				//log.logLine(Testname, true, "PDF file is not opened in chrome after cliking on submit button");
				log.logLine(Testname, true, "PVT9005_searchDocExt.aspx file is not downloaded in chrome after cliking on submit button");
				log.logLine(Testname, false, "SSO Script-1 execution is successfully completed.");
				retval = false;
			}		
		}
		
		return retval;
	}
	
	
	public boolean eDeliverScript2(String AccNo, String Testname) throws Exception {
		
		boolean retval = false;
		
		//Launch the PA HA script HTML page		
		log.logLine(Testname, false, "SSO Script-2 execution is started...");
		if (Initialization.EnvirSite.equalsIgnoreCase("test")) {
			log.logLine(Testname, false, "Navigating to PIVOT HA TEST SSO Script - XML POW9999..");
			driver.get(properties.getProperty("TestSSO2"));
		
		} else if (Initialization.EnvirSite.equalsIgnoreCase("stage")) {
			log.logLine(Testname, false, "Navigating to PIVOT HA STAGE SSO Script - XML POW9999..");
			driver.get(properties.getProperty("StageSSO2"));
			
		} else if (Initialization.EnvirSite.equalsIgnoreCase("prod")) {
			log.logLine(Testname, false, "Navigating to PIVOT HA Prod SSO Script - XML POW9999..");
			driver.get(properties.getProperty("ProdSSO2"));
		}
		
		Thread.sleep(1000);		
		SimpleDateFormat gmtDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");		
		gmtDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
		//Current Date Time in IST
		String CurDateTime = gmtDateFormat.format(new Date());
		
		//Delete the existing file before it downloads
		try {			
	        File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/PVT9005_searchDocExt.aspx");
	        if (fileTemp1.exists()){
	        	fileTemp1.delete();
	        }
	        
	        File fileTemp2 = new File("C:/Pivot_Portal/Test Output/PVT9005_searchDocExt.xml");
	        if (fileTemp2.exists()){
	        	fileTemp2.delete();
	        }
		} catch(Exception e){
	        // if any error occurs
	        e.printStackTrace();
	    }		
				
		if (doesElementExist(properties.getProperty("Seckeyfld"), 5)) { 
			String secKeyStr = driver.findElement(By.xpath(properties.getProperty("Seckeyfld"))).getAttribute("value");	
			String[] temp = secKeyStr.split("\\|\\|");
			
			log.logLine(Testname, false, "Clearing the content in SecKey field");
			driver.findElement(By.xpath(properties.getProperty("Seckeyfld"))).clear();
			
			log.logLine(Testname, false, "Entering the content with latest date and time in SecKey field");
			
			String Subject = temp[0]+"||"+temp[1]+"||"+temp[2]+"||"+temp[3]+"||" +CurDateTime;
			if (Initialization.Browser.equalsIgnoreCase("ie") || Initialization.Browser.equalsIgnoreCase("internetexplorer")) {
				driver.findElement(By.xpath(properties.getProperty("Seckeyfld"))).sendKeys(temp[0]+"||");
				driver.findElement(By.xpath(properties.getProperty("Seckeyfld"))).sendKeys(temp[1]+"||");
				driver.findElement(By.xpath(properties.getProperty("Seckeyfld"))).sendKeys(temp[2]+"||");
				driver.findElement(By.xpath(properties.getProperty("Seckeyfld"))).sendKeys(temp[3]+"||");
				driver.findElement(By.xpath(properties.getProperty("Seckeyfld"))).sendKeys(CurDateTime);
							
			} else {
				driver.findElement(By.xpath(properties.getProperty("Seckeyfld"))).sendKeys(Subject);
			}
		} else{
			log.logLine(Testname, true, "Entering the content with latest date and time in SecKey field is failed");
		}
		
		
		((JavascriptExecutor) driver).executeScript("window.focus();");
		if (doesElementExist(properties.getProperty("SubmitBtn"), 5)) {			
			WebElement submitbtn = driver.findElement(By.xpath(properties.getProperty("SubmitBtn")));
			log.logLine(Testname, false, "Clicking on submit button..");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", submitbtn);
		} else{
			log.logLine(Testname, true, "Clicking on submit button failed");
		}
		
		
		Thread.sleep(10000);
		Robot robot=new Robot();
		/*
		if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) || (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) {
			
		    robot.keyPress(KeyEvent.VK_ALT);
		    robot.keyPress(KeyEvent.VK_S);
		    robot.keyRelease(KeyEvent.VK_S);
		    robot.keyRelease(KeyEvent.VK_ALT);
		    Thread.sleep(15000);
		    
		    try {			
		        File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/PVT9005_searchDocExt.pdf");
		        if (fileTemp1.exists()){
		        	log.logLine(Testname, false, "SSO Script-2:PDF file is downloaded from internet explorer after cliking on submit button");							
					
		        } else {
					log.logLine(Testname, true, "SSO Script-2: PDF file is not downloaded from internet explorer after cliking on submit button");					
					
				}		        
			} catch(Exception e) {
		        // if any error occurs
		        e.printStackTrace();
		    }
			
		} else */if ((Initialization.Browser.equals("FF")) || (Initialization.Browser.equals("ff")) || (Initialization.Browser.equals("firefox")) || (Initialization.Browser.equals("Firefox")) || (Initialization.Browser.equals("FIREFOX"))) {
		
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);			
		}
		
		//Wait until the file is downloaded
		Thread.sleep(12000);	
		
		/*
		Path original = Paths.get(System.getProperty("user.home")+"/Downloads/PVT9005_searchDocExt.aspx");
		Path destination = Paths.get("C:/Pivot_Portal/Test Output/PVT9005_searchDocExt.xml");
		try {			
			File fileTemp = new File(System.getProperty("user.home")+"/Downloads/PVT9005_searchDocExt.aspx");
	        if (fileTemp.exists()){
	        	Files.move(original, destination, StandardCopyOption.REPLACE_EXISTING);
	        	log.logLine(Testname, false, "Downloaded file has been moved to 'Test Output' folder");
	        	
	        	File filesXML = new File("C:/Pivot_Portal/Test Output/PVT9005_searchDocExt.xml");
	            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.parse(filesXML);

		        NodeList nList = doc.getElementsByTagName("documents");	
		        for (int i= 0; i< nList.getLength(); i++) {	
		            Node nNode = nList.item(i);	
		            
		            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		                Element eElement = (Element) nNode;
		                
		                log.logLine(Testname, false, "XML file content verification...");
		                if (eElement.getAttribute("message").equals("successful search")) {
		                	log.logLine(Testname, false, "SSO Script-2 execution is successfully completed.");
		                	log.logLine(Testname, false, "message: " + eElement.getAttribute("message"));
		                	log.logLine(Testname, false, "seckey: " + eElement.getAttribute("seckey"));
		                	retval = true;
		                } else {
		                	log.logLine(Testname, false, "SSO Script-2 execution is unsuccessful");
		                	retval = false;
		                }
		            }
		        }		        
	        	
	        }			
			
		} catch (IOException e) {			
		    //catch all for IO problems
			e.printStackTrace();
		}
		*/
		
		String Content = driver.getPageSource();
		log.logLine(Testname, false, "XML file content verification...");
        if (Content.contains("successful search")) {
        	log.logLine(Testname, false, "XML file opened with content Successful search");
        	log.logLine(Testname, false, "SSO Script-2 execution is successfully completed.");	
        	
        	retval = true;
        } else {
        	log.logLine(Testname, true, "XML file is not opened with content Successful search");
        	log.logLine(Testname, false, "SSO Script-2 execution is unsuccessful");
        	retval = false;
        }
		return retval;
		
	}
	
	
	
	public boolean eDeliverScript3(String AccNo, String Testname) throws Exception {
		
		Robot robot=new Robot();
		
		//Launch the PA HA script HTML page
		log.logLine(Testname, false, "SSO Script-3 execution is started...");
		if (Initialization.EnvirSite.equalsIgnoreCase("test")) {
			log.logLine(Testname, false, "Navigating to PIVOT HA TEST SSO Script - XML POW9999 - HTML..");
			driver.get(properties.getProperty("TestSSO3"));
		
		} else if (Initialization.EnvirSite.equalsIgnoreCase("stage")) {
			log.logLine(Testname, false, "Navigating to PIVOT HA STAGE SSO Script - XML POW9999 - HTML..");
			driver.get(properties.getProperty("StageSSO3"));
			
		} else if (Initialization.EnvirSite.equalsIgnoreCase("prod")) {
			log.logLine(Testname, false, "Navigating to PIVOT HA PROD SSO Script - XML POW9999 - HTML..");
			driver.get(properties.getProperty("ProdSSO3"));
		}
		
		Thread.sleep(1000);
		
		SimpleDateFormat gmtDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");		
		gmtDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
		//Current Date Time in IST
		String CurDateTime = gmtDateFormat.format(new Date());
		
		if (doesElementExist(properties.getProperty("Seckeyfld"), 5)) { 
			String secKeyStr = driver.findElement(By.xpath(properties.getProperty("Seckeyfld"))).getAttribute("value");	
			String[] temp = secKeyStr.split("\\|\\|");
			
			log.logLine(Testname, false, "Clearing the content in SecKey field");
			driver.findElement(By.xpath(properties.getProperty("Seckeyfld"))).clear();
			
			log.logLine(Testname, false, "Entering the content with latest date and time in SecKey field");
			
			String Subject = temp[0]+"||"+temp[1]+"||"+temp[2]+"||"+temp[3]+"||" +CurDateTime;
			if (Initialization.Browser.equalsIgnoreCase("ie") || Initialization.Browser.equalsIgnoreCase("internetexplorer")) {
				driver.findElement(By.xpath(properties.getProperty("Seckeyfld"))).sendKeys(temp[0]+"||");
				driver.findElement(By.xpath(properties.getProperty("Seckeyfld"))).sendKeys(temp[1]+"||");
				driver.findElement(By.xpath(properties.getProperty("Seckeyfld"))).sendKeys(temp[2]+"||");
				driver.findElement(By.xpath(properties.getProperty("Seckeyfld"))).sendKeys(temp[3]+"||");
				driver.findElement(By.xpath(properties.getProperty("Seckeyfld"))).sendKeys(CurDateTime);
							
			} else {
				driver.findElement(By.xpath(properties.getProperty("Seckeyfld"))).sendKeys(Subject);
			}
		} else{
			log.logLine(Testname, true, "Entering the content with latest date and time in SecKey field is failed");
		}
		
		
		((JavascriptExecutor) driver).executeScript("window.focus();");
		if (doesElementExist(properties.getProperty("SubmitBtn"), 5)) {			
			WebElement submitbtn = driver.findElement(By.xpath(properties.getProperty("SubmitBtn")));
			log.logLine(Testname, false, "Clicking on submit button..");
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", submitbtn);
		} else{
			log.logLine(Testname, true, "Clicking on submit button failed");
		}
		
		Thread.sleep(10000);
		
		//Click on First PDF link
		if (doesElementExist2(properties.getProperty("PDFLink1"), 5)) { 
			WebElement firstpdf = driver.findElement(By.cssSelector(properties.getProperty("PDFLink1")));		
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", firstpdf);
			log.logLine(Testname, false, "Clicking on First PDF Link in the page after clicking on Submit button");
		} else if (doesElementExist2(properties.getProperty("ViewAll"), 5)) {
			WebElement viewallbtn = driver.findElement(By.cssSelector(properties.getProperty("ViewAll")));		
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", viewallbtn);
			log.logLine(Testname, false, "Clicking on ViewAll button to view PDF's");
			Thread.sleep(10000);
			
			if (doesElementExist2(properties.getProperty("PDFLink1"), 5)) { 
				WebElement firstpdf = driver.findElement(By.cssSelector(properties.getProperty("PDFLink1")));		
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", firstpdf);
				log.logLine(Testname, false, "Clicking on First PDF Link in the page after clicking on Submit button");
			}
		} else {
			log.logLine(Testname, true, "No documents found: PDF is not displayed in the page after clicking on Submit button is failed");
			return false;
		}
		
		 try {			
		        File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/PVT9005_pdf.aspx");
		        if (fileTemp1.exists()){
		        	fileTemp1.delete();
		        	log.logLine(Testname, false, "PDF file is deleted from the downloads folder");
					//retval = true;
		        } else {
					log.logLine(Testname, false, "PDF file not exists in downloads folder");
					//retval = false;
				}
		        
			} catch(Exception e){
		        // if any error occurs
		        e.printStackTrace();
		    }
		 
		
		if ((Initialization.Browser.equals("IE")) || (Initialization.Browser.equals("ie")) || (Initialization.Browser.equals("InternetExplorer")) || (Initialization.Browser.equals("internetexplorer")) || (Initialization.Browser.equals("INTERNETEXPLORER"))) {	
			
			Robot robots=new Robot();
		    robots.keyPress(KeyEvent.VK_ALT);
   		    robots.keyPress(KeyEvent.VK_S);
		    
		    Thread.sleep(3000);
		    robots.keyRelease(KeyEvent.VK_ALT);    
		    robots.keyRelease(KeyEvent.VK_S);
		 
  		    
		    
		    Thread.sleep(20000);
		    
		    try {			
		        File fileTemp1 = new File(System.getProperty("user.home")+"/Downloads/PVT9005_pdf.aspx");
		        if (fileTemp1.exists()){
		        	log.logLine(Testname, false, "PDF file is downloaded from internet explorer after cliking on submit button");
					log.logLine(Testname, false, "SSO Script-3 execution is successfully completed.");					
					//retval = true;
		        } else {
					log.logLine(Testname, true, "PDF file is not downloaded from internet explorer after cliking on submit button");
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
		    if (winHandle!=firstWinHandle) {
		    	log.logLine(Testname, false, "Child window opened after clicking on PDF link");	
		    	//To retrieve the handle of second window, extracting the handle which does not match to first window handle
		    	String secondWinHandle=winHandle; //Storing handle of second window handle
		    
		    	//Switch control to new window
		    	driver.switchTo().window(secondWinHandle);		    	
		    	Thread.sleep(6000);
		    	
		    	if ((Initialization.Browser.equalsIgnoreCase("ie")) || (Initialization.Browser.equalsIgnoreCase("InternetExplorer"))) { 
	    			//driver.get("javascript:document.getElementById('overridelink').click();");		    							
    			    log.logLine(Testname, false, "SSO Script-3:PDF file is downloaded from internet explorer after cliking on submit button");    		
    							        
		    	} else if ((Initialization.Browser.equals("Chrome")) || (Initialization.Browser.equals("chrome")) || (Initialization.Browser.equals("CHROME"))) { 		    	
		    		if (doesElementExist2(properties.getProperty("ChromePdfweb"), 5)) { 
		    			log.logLine(Testname, false, "PDF file is opened in another Chrome window after cliking on submit button");					
					} else {
						log.logLine(Testname, true, "PDF file is not opened after cliking on submit button");					
					}
		    	} else if ((Initialization.Browser.equals("FF")) || (Initialization.Browser.equals("ff")) || (Initialization.Browser.equals("firefox")) || (Initialization.Browser.equals("Firefox")) || (Initialization.Browser.equals("FIREFOX"))) {
		    		if (doesElementExist2(properties.getProperty("ffpdfweb"), 5)) { 
		    			log.logLine(Testname, false, "PDF file is opened in another Firefox window after cliking on submit button");					
					} else {
						log.logLine(Testname, true, "PDF file is not opened after cliking on submit button");					
					}
		    		
		    	}
		    	
		    	//Close the child browser
		    	driver.close();
		    	Thread.sleep(2000);
		    	
		    	// Switching back to parent window
			    driver.switchTo().window(firstWinHandle);
			    
		    }
	    } 
	}
	    
	    log.logLine(Testname, false, "SSO Script-3 execution is successfully completed.");
	    return true;
	}
	
	
}

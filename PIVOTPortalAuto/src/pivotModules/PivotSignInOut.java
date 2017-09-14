package pivotModules;
import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import launchAuto.Initialization;
import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.assertTrue;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class PivotSignInOut extends Page{
	
	public PivotSignInOut(WebDriver driver, Log log) throws InvalidFormatException, IOException {
		super(driver, log);						
	}	
	
	@Override
	protected void load() { } 
	
	public static String Uname="";
	
	public void load(String Browser, String EnvirSite) throws InvalidFormatException, IOException, InterruptedException {
    	log.logLine("", false, "Navigate to PIVOT Portal login page");
    	
    	if (EnvirSite.equalsIgnoreCase("test")) {
    		driver.get(properties.getProperty("TESTURL"));
    		log.logLine("", false, "Launched "+Browser +" browser...");
    		
    		log.logLine("", false, "Navigated to PIVOT - QA site");
    		
    		if ((Browser.equalsIgnoreCase("ie")) || (Browser.equalsIgnoreCase("internetExplorer"))) {
    			if (doesElementExist2(properties.getProperty("OverrideLnk"), 5)) {
	    			driver.get("javascript:document.getElementById('overridelink').click();");
	    		}
    			
    		} else if ((Browser.equals("Safari")) || (Browser.equals("SAFARI")) || (Browser.equals("safari"))) {
    			 try {
    			        Alert alert = driver.switchTo().alert();

    			        // check if alert exists
    			        // TODO find better way
    			        alert.getText();

    			        // alert handling    			        
    			        alert.accept();
    			    } catch (Exception e) {
    			    }
    			//driver.switchTo().alert().accept();
    		}
    		
    	} else if (EnvirSite.equalsIgnoreCase("stage")) {
    		driver.get(properties.getProperty("STAGEURL"));
    		log.logLine("", false, "Navigated to PIVOT - STAGE site");
    		
    	} else if (EnvirSite.equalsIgnoreCase("prod")) {
    		driver.get(properties.getProperty("PRODURL"));
    		log.logLine("", false, "Navigated to PIVOT - PRODUCTION site");
    	}else if(EnvirSite.equalsIgnoreCase("DR")) {
    		driver.get(properties.getProperty("DRURL"));
    		log.logLine("", false, "Navigated to PIVOT - DR site");
    	}
    	
    	
    	Thread.sleep(2000);
    	
		driver.manage().window().maximize();
   
    }	
	
	protected void isLoaded() throws Error {    	
    	String url = driver.getCurrentUrl();
    	assertTrue("Not starting calander: " + url, url.compareTo(properties.getProperty("URL")) == 0);
    }
 
    public void loginAs(String username, String password) throws Exception {
    	
    	if (Initialization.AutoMultipleUser.equalsIgnoreCase("yes")) {
    		
    		String Pword = null;
    		   		
			File inputWorkbook = new File("C:/Pivot_Portal/Config/MultipleUsers.xls");
			Workbook w;
			String cellData = null;
			try {
				w = Workbook.getWorkbook(inputWorkbook);
				Sheet sheet = w.getSheet(0);				
				Cell cell = sheet.getCell(0, 0);
				
				if (cell.getContents().equalsIgnoreCase("Status")) {
					for (int j = 1; j < sheet.getRows(); j++) {
						cellData = sheet.getCell(0, j).getContents();
						if (cellData.equalsIgnoreCase("yes")) {
							Uname = sheet.getCell(1, j).getContents().trim();
							Pword = sheet.getCell(2, j).getContents().trim();
							break;							
						}
					}
				}
						
			} catch (BiffException e) {
				e.printStackTrace();
			}		
				
    		enterUsername(Uname);
    		enterPassword(Pword);
    		
    	} else {
    		enterUsername(username);     
    		enterPassword(password);
    	}
    	
        submitLogin();      
        
        //Wait till the admin menu loads in Home page
        waitForElement(properties.getProperty("Canbtn"));
    }
    
    public void enterUsername(String userName) throws Exception {
    	log.logLine("", false, "fill up user name = " + userName);
    	WebElement retelm = waitForElement(properties.getProperty("UserName"));
    	if (retelm.isDisplayed()) {
	    	WebElement usrnamefld = driver.findElement(By.cssSelector(properties.getProperty("UserName")));    	
	        clearAndType(usrnamefld, userName);    	
    	} else {
    		log.logLine("", true, "PA login page: UserName field does not exist to operate");
			throw new Exception("PA login page: UserName field does not exist to operate");
    	}
    }
    
    public void enterPassword(String passWord) throws InvalidFormatException, IOException {
    	log.logLine("", false, "fill up password = " + passWord);
    	WebElement passwdfld = driver.findElement(By.cssSelector(properties.getProperty("Password")));
    	clearAndType(passwdfld, passWord);    	
    }
    
    public void submitLogin() throws InterruptedException, InvalidFormatException, IOException {
    	log.logLine("", false, "click login");
    	driver.findElement(By.cssSelector(properties.getProperty("loginBtn"))).click();
    	Thread.sleep(3000);
    }       
    
    private void clearAndType(WebElement field, String text) {
        field.clear();
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', arguments[1])", field, text);
        
    }
    
    public void clickLogout() throws Exception {
    	log.logLine("", false, "clicking on logout button...");
    	
    	if (doesElementExist2(properties.getProperty("newsignout"), 5)) {
    		WebElement signout = driver.findElement(By.cssSelector(properties.getProperty("newsignout")));
    		((JavascriptExecutor) driver).executeScript("arguments[0].click()", signout);    		
    	} else if (doesElementExist2(properties.getProperty("userlogout1"), 5)) {
    		WebElement logout = driver.findElement(By.cssSelector(properties.getProperty("userlogout1")));
    		((JavascriptExecutor) driver).executeScript("arguments[0].click()", logout);    		
    	} else if (doesElementExist2(properties.getProperty("userlogout2"), 5)) {
    		WebElement logout = driver.findElement(By.cssSelector(properties.getProperty("userlogout2")));
    		((JavascriptExecutor) driver).executeScript("arguments[0].click()", logout);    		
    	}	
    	
		WebElement retelm = waitForElement(properties.getProperty("UserName"));		       	 
    }	
	
	public void closeBrowser() throws InvalidFormatException, IOException {
		log.logLine("", false, "Closing all the browsers launched by WebDriver.");
		driver.quit();
	}   
		
}

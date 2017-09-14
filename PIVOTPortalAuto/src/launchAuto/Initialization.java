package launchAuto;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import pivotModules.PivotSignInOut;

public class Initialization {	
	
	public static String Browser; 		public static Log log;
	public static WebDriver driver; 	public static boolean testval;	
	public static int mycnt=0;			public static String timestamp, ServerName;
	public static String myTimestamp; 	public static String host, AutoMultipleUser;	
	public static String UserID; 		public static String Passwd;
	public static String IterXML; 		public static String EnvirSite;
	public static String EmailIds;		public static String mydate, todaysDate;
	boolean Admin= false;				public static boolean remoteWebDriver;
	public static long startTime;		public static long endTime; 
	public static double totalTime;		public static String CurDateTime;
	public static String OSbits;		public static String eDelivery;
	public static String FileUploads;	public static String ArchiveZIP1, ArchiveZIP2, UploadedZIP3, ArchiveZIP3;
	public static int PassCnt=0, FailCnt=0, SkipCnt=0;
	public static String EightDig1, EightDig2;

	
	@BeforeSuite
	public void Initialize() throws Exception {
		
		// Capture the Start time of test execution
		startTime = System.currentTimeMillis();		
		
		SimpleDateFormat gmtDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");		
		gmtDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
		//Current Date Time in IST
		CurDateTime = gmtDateFormat.format(new Date());
		
		// Create time stamp to keep the session track
		int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);		
		myTimestamp = Integer.toString(paperID);	
		
		InputOutputData test = new InputOutputData();	
		
		//read the Config.xml elements
		String Consts = test.readConfigXML();

		String Vars = new String(Consts);
		String[] arr = Vars.split(" ");
		if (arr.length > 7) {
			IterXML = arr[0];
			EnvirSite = arr[1];
			UserID = arr[2];
			Passwd = arr[3];
			Browser = arr[4];
			AutoMultipleUser = arr[5];
			eDelivery = arr[6];
			FileUploads = arr[7];
			EmailIds = arr[8];			
		} else {
			throw new Exception("Attribute in Config XML status is 'completed', change the status inorder to run");
		}		
		
		//kill the already running process
		test.KillautoProcess(Browser);
		
		DateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy_HHmmss");		
		Date date = new Date();
		mydate = dateFormat.format(date);			
		try {			
			InetAddress thisIp = InetAddress.getLocalHost();
			log = new Log(thisIp.getHostAddress());			
			ServerName = thisIp.getHostName();
			
			//Create report folder with current time-stamp
			log.createFold("C:/Pivot_Portal/Report & Logs", mydate);
			
			//Copy the sample logs and report files
			test.CopyReportFile(myTimestamp);
			
			log.logLine("", false, "Log instance has been created for this round of execution with TimeStamp: "+myTimestamp);
			
			if (FileUploads.equalsIgnoreCase("yes")) {
				
				CreateAppData testdata = new CreateAppData(log);
				
				int paperID1 = (int) Math.round(Math.random() * (99999999 - 10000000 + 1) + 10000000);
				EightDig1 = Integer.toString(paperID1);				
				int paperID2 = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);
				String ThreeDig1 = Integer.toString(paperID2);
				
				paperID1 = (int) Math.round(Math.random() * (99999999 - 10000000 + 1) + 10000000);
				EightDig2 = Integer.toString(paperID1);				
				paperID2 = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);
				String ThreeDig2 = Integer.toString(paperID2);
				
				paperID1 = (int) Math.round(Math.random() * (99999999 - 10000000 + 1) + 10000000);
				String EightDig3 = Integer.toString(paperID1);				
				paperID2 = (int) Math.round(Math.random() * (999 - 100 + 1) + 100);
				String ThreeDig3 = Integer.toString(paperID2);			
				
				
				//Upload 2 zip files to archives in file management tab
				ArchiveZIP1 = testdata.CreateArchives(ThreeDig1, EightDig1, "");				
				ArchiveZIP2 = testdata.CreateArchives(ThreeDig2, EightDig2, "");	
								
				//Create two pending audits in audits search page
				testdata.CreateAudits(ThreeDig1, "");
				testdata.CreateAudits(ThreeDig2, "");
				
				//upload zip file for HTML 5 document testing
                ArchiveZIP3 = testdata.CreateArchivesHTML(ThreeDig2, "");
				
				//Create 1 Userzip and upload it throgh FTP
				testdata.PasswordRegistration(ThreeDig1, "", "SKB0100");
				testdata.PasswordRegistration(ThreeDig2, "", "GNE2000");
				
				//Public document
				testdata.CreatePublicDocs(ThreeDig1,"");
			}									
		} catch(IOException e) {
			log.logLine("", true, "Failed to create Run time folders for the execution - exiting");
			System.exit(1);
		}				
	}
	
	
	@BeforeTest
	public static void LaunchBrowser() throws Exception {	
		
		OSbits = System.getProperty("sun.arch.data.model");
		remoteWebDriver = false;
		File file = null;
		if ((Browser.equalsIgnoreCase("ie")) || (Browser.equalsIgnoreCase("InternetExplorer"))) {
			if (OSbits.equals("64")) { 
				file = new File("C:/Pivot_Portal/Selenium jars/IEDriverServer64.exe");
			} else if (OSbits.equals("32")) {
				file = new File("C:/Pivot_Portal/Selenium jars/IEDriverServer32.exe");
			}
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			DesiredCapabilities iecapabilities = DesiredCapabilities.internetExplorer();
			iecapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			iecapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			iecapabilities.setJavascriptEnabled(true);		
			iecapabilities.setCapability("requireWindowFocus", true);
			iecapabilities.setCapability("enablePersistentHover", false);
			iecapabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
			iecapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, "accept");
			iecapabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			iecapabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			
			driver = new InternetExplorerDriver(iecapabilities);			
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			
		} else if ((Browser.equalsIgnoreCase("safari"))) {
			
			Platform current = Platform.getCurrent();
		    if (Platform.MAC.is(current) || Platform.WINDOWS.is(current) || Platform.VISTA.is(current)) {
				DesiredCapabilities safariCapabilities = DesiredCapabilities.safari();
				safariCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				safariCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				safariCapabilities.setJavascriptEnabled(true);
				safariCapabilities.setCapability("requireWindowFocus", true);
				safariCapabilities.setCapability("enablePersistentHover", false);
				safariCapabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
				safariCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, "accept");
				safariCapabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
				safariCapabilities.setCapability("browser.helperApps.neverAsk.saveToDisk", "application/pdf,application/x-pdf");
	
				driver = new SafariDriver(safariCapabilities);
		    }
			
		} else if ((Browser.equalsIgnoreCase("ff")) || (Browser.equalsIgnoreCase("firefox"))) {
			String downloadPath = "C:\\Users\\user.home\\Downloads";
            FirefoxProfile firefoxProfile = new FirefoxProfile();
            firefoxProfile.setPreference("browser.download.folderList",2);
            firefoxProfile.setPreference("browser.download.manager.showWhenStarting",false);
            firefoxProfile.setPreference("browser.download.manager.alertOnEXEOpen", false); 
            firefoxProfile.setPreference("browser.download.dir", downloadPath);
            firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                                          "text/csv, application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.wordprocessingml.document, application/msword, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/pdf, image/png,image/jpeg,text/html,text/plain");
            
            //# disable Firefox's built-in PDF viewer
            firefoxProfile.setPreference("pdfjs.disabled", true);            

            //# disable Adobe Acrobat PDF preview plugin
            firefoxProfile.setPreference("plugin.scan.plid.all", false);
            firefoxProfile.setPreference("plugin.scan.Acrobat", "99.0");
            
            
            driver = new FirefoxDriver(firefoxProfile);		
			
			
		} else if (Browser.equalsIgnoreCase("chrome")) {
			file =new File("C:/Pivot_Portal/Selenium jars/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			DesiredCapabilities Chrcapabilities = DesiredCapabilities.chrome();
			Chrcapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			Chrcapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			Chrcapabilities.setJavascriptEnabled(true);
			//Chrcapabilities.
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			options.addArguments("disable-popup-blocking");
			Chrcapabilities.setCapability(ChromeOptions.CAPABILITY, options);
			Chrcapabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
			Chrcapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, "accept");
			Chrcapabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			//Chrcapabilities.setCapability(//Ca, value)
			
			driver = new ChromeDriver(Chrcapabilities);			
			
		} else if ((Browser.equalsIgnoreCase("headless")) || (Browser.equals("guiless"))) {
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setBrowserName("Mozilla/5.0 (X11; Linux x86_64; rv:24.0) Gecko/20100101 Firefox/24.0");
			capabilities.setVersion("24.0");
			
			//driver = new HtmlUnitDriver(capabilities);
		}		
		
		
		//Call sign in methods for Pivot portal login
		PivotSignInOut loginPge = new PivotSignInOut(driver, log);
		loginPge.load(Browser, EnvirSite);
		Thread.sleep(6000);
		loginPge.loginAs(UserID, Passwd);
		
		//WebElement cpnCombo= driver.findElement(By.cssSelector("div[id='modal-campaign-manager-configuration-manager'] table tbody tr td div div div div div div div[class='k-multiselect-wrap k-floatwrap']"));
		//((JavascriptExecutor) driver).executeScript("arguments[0].click()", cpnCombo);
		
	}	
	
		
	@BeforeMethod
	public static void ControlTest() throws IOException, Exception {
		
		InputOutputData test = new InputOutputData();
	    test.setInputFile("C:/Pivot_Portal/Config/Driver.xls");	    
	    String mystr = test.readControlFile("Control", "ControlFile", mycnt+1);
	    
	    if ((mystr.equalsIgnoreCase("y")) || (mystr.equalsIgnoreCase("yes"))) {
	    	testval = true;
		} else {
	    	testval = false;
		}	
	    mycnt = mycnt + 1;
	}
	
	
	@AfterMethod
	public void Screenshot(ITestResult result) throws IOException, InvalidFormatException {
		
	    if (!result.isSuccess()) { 
	    	if (testval) {
	    		FailCnt = FailCnt + 1;
	    		log.updateTestResults(result.getMethod().getMethodName(), "Fail");	
		        File imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		        String failImg = "/Pivot_Portal/Report & Logs/PATAReport_"+mydate+"/Failure_Screenshots/"+result.getMethod().getMethodName()+ "_" + myTimestamp + ".png";
		        File failureImageFile = new File(failImg);
		        FileUtils.moveFile(imageFile, failureImageFile);
		        Reporter.log("<a href='"+ failImg +"'> <img src='"+ failImg + "' height='100' width='100'/> </a>");
	    	} else {
	    		SkipCnt = SkipCnt + 1;
	    		log.updateTestResults(result.getMethod().getMethodName(), "Skip");
	    	}
	    } else {
	    	PassCnt = PassCnt + 1;
	    	log.updateTestResults(result.getMethod().getMethodName(), "Pass");
	    	File imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);	    	
	    	String passImg = "/Pivot_Portal/Report & Logs/PATAReport_"+mydate+"/Logs/"+result.getMethod().getMethodName()+ "_" + myTimestamp + ".png";
	        File passimgfile = new File(passImg);
	        FileUtils.moveFile(imageFile, passimgfile);
	        Reporter.log("<a href='"+ passImg +"'> <img src='"+ passImg + "' height='100' width='100'/> </a>");      
	     }
	}
	
	
	@AfterTest
	public static void closeBrowser() throws Exception {	
		
		PivotSignInOut logoutpge = new PivotSignInOut(driver, log);	
		
		if (!(eDelivery.equalsIgnoreCase("yes"))) {
			logoutpge.clickLogout();
		}
		
		logoutpge.closeBrowser();
		
		
		// Capture the end time of test execution
		endTime = System.currentTimeMillis();
		long testime = endTime - startTime;
		totalTime =(double) ((testime/(1000*60)));  
				
		log.updateTestSummary();
	}
	
	
	@AfterSuite
	public void UpdateConfig() throws Exception {	
		
		InputOutputData test = new InputOutputData();
		
		if (!(AutoMultipleUser.equalsIgnoreCase("yes"))) {			
			test.UpdateConfigXML(Integer.parseInt(IterXML));
		}
			
		DateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy");		
		Date date = new Date();
		todaysDate = dateFormat.format(date);
		
		//Send email to recipients attaching report to it
		if (AutoMultipleUser.equalsIgnoreCase("yes")) {
			
			test.SendReportEmail(EnvirSite+" Automation -"+PivotSignInOut.Uname +" on "+todaysDate+" Success-"+PassCnt+": Fail-"+FailCnt+": Skip-"+SkipCnt);			
		} else {
			
			test.SendReportEmail(EnvirSite+" Automation as on "+todaysDate+" Success-"+PassCnt+": Fail-"+FailCnt+": Skip-"+SkipCnt);
		}
	}
	
		
	
}


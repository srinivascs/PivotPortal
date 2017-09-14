package pivotModules;

import java.io.IOException;
import java.util.List;

import launchAuto.InputOutputData;
import launchAuto.Log;
import launchAuto.Page;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FavoriteFeature extends Page{


	public FavoriteFeature(WebDriver driver, Log log) throws InvalidFormatException, IOException {
 			super(driver, log);
 	} 
 	@Override
 	protected void load() {}
 	@Override
 	
 	protected void isLoaded() throws Error {}
 	
 	public static String ProofsClntName="Regression Testing - DO NOT TOUCH";
 	public static String ProofsAppName="RGT1099";
 	
 	public static String AuditsClntName="Regression Testing - DO NOT TOUCH";
 	public static String AuditsAppName="RGT1099";
 	public static String AuditsRulesAppName="Statements";
 	
 	public static String PullsstmntsClntName="Regression Testing - DO NOT TOUCH";
 	public static String PullsstmntsAppName="Statements";
 	
 	public static String ReportWriterClntName="ABC Company";
 	public static String ReportWriterAppName="ABC1234";
 	
 	public static String ArchiveClntName="ABC Company";
 	public static String ArchiveAppName="ABC1234";
 	
 	
 	
 	
 
 	public boolean ClientAppSel(String AccNo, String Testname) throws Exception {
 		 
 		InputOutputData test = new InputOutputData();  
		test.setInputFile(properties.getProperty("InputDatafile"));
		String sheetname = new Object(){}.getClass().getEnclosingMethod().getName();

		Thread.sleep(1000);
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		WebElement retelm2 = waitForElement(properties.getProperty("selClint1"));
		
		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");
			//throw new Exception("Clicking on Cancel button in Client/App popup is failed");
		}
		 
		 
	
		 Proofs(Testname);
		 AuditsSearch(Testname);
		 AuditsRules(Testname);
		 Pullsstmnts(Testname);
		 ReportWriter(Testname);
		 Archive(Testname);
		 
		 ProofsFavoriteValidation(Testname);
		 AuditsFavoriteValidation(Testname);
		 AuditsRulesFavoriteValidation(Testname);
		 PullsFavoriteValidation(Testname);
		 ReportWriterFavoriteValidation(Testname);
		 ArchiveFavoriteValidation(Testname);
		 
		 
		 DeleteFavoriteValidation(Testname);
		 
		 
		    return true;
	 	}

 	
 	public boolean Proofs(String Testname) throws Exception {
 		
	    //driver.switchTo().frame("iframeContainer");
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
	    
	    if (doesElementExist2(properties.getProperty("selClint1"), 5)) {	   
	    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selClint1")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
	    	
	    	Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on ClientName dropdown..");
			
			if (doesElementExist2(properties.getProperty("ClinetOpts1"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ClinetOpts1")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equalsIgnoreCase(ProofsClntName)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the ClientName "+ProofsClntName +" from the popup..");
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
						if (lnk.getText().equalsIgnoreCase(ProofsClntName)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false, "Selecting the ClientName "+ProofsClntName +" from the dropdown..");							
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
	    
	    
	    if (doesElementExist2(properties.getProperty("selAppl1"), 5)) {	   
	    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selAppl1")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
	    	
		    Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on ApplicationName dropdown..");
			
			if (doesElementExist2(properties.getProperty("ApplOpts1"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ApplOpts1")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equalsIgnoreCase(ProofsAppName)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Application Name "+ProofsAppName +" from the popup..");
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
						if (lnk.getText().equalsIgnoreCase(ProofsAppName)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false, "Selecting the Application Name "+ProofsAppName +" from the dropdown..");							
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
	    
	    Thread.sleep(10000);
	    
	    if (doesElementExist(properties.getProperty("MakefavoriteProofs"), 5)) {	    
			WebElement mke = driver.findElement(By.xpath(properties.getProperty("MakefavoriteProofs")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", mke);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicking on Make favorite button is successful");
	    } else {
			log.logLine(Testname, true, "Clicking on Make favorite button is Unsuccessful");
			throw new Exception("Clicking on Make favorite button is Unsuccessful");
		}
	    
	    if (doesElementExist(properties.getProperty("MakefavoriteProofs"), 5)) {	    
			String Mkefvr = driver.findElement(By.xpath(properties.getProperty("MakefavoriteProofs"))).getText();
			if(Mkefvr.equals("Make Favorite")){
				WebElement Mkefvror = driver.findElement(By.xpath(properties.getProperty("MakefavoriteProofs")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", Mkefvror);
				log.logLine(Testname, false, "Clicking on Make favorite for Proofs is successful");
				
			}else if(Mkefvr.equals("Remove Favorite")){
				log.logLine(Testname, false, "Proofs already selected as favorites");
				
			} 
		
	    } else {
			log.logLine(Testname, true, "Clicking on Make favorite for Proofs is unsuccessful");
			
		}
	
	    return true;
 	}
 	

		    
	
	public boolean AuditsSearch(String Testname) throws Exception {
 		
	    //driver.switchTo().frame("iframeContainer");
	 if (doesElementExist2(properties.getProperty("Audits"), 5)) {	    
			WebElement reportsmnu = driver.findElement(By.cssSelector(properties.getProperty("Audits")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", reportsmnu);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Navigation to Audits page successful");
	    } else {
			log.logLine(Testname, true, "Clicking on Audits menu is failed");
			throw new Exception("Clicking on Audits menu is failed");
		}	    
	   
	    Thread.sleep(1000);
	    boolean CliSelected = false;
	    
	    if (doesElementExist2(properties.getProperty("selClint1"), 5)) {	   
	    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selClint1")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
	    	
	    	Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on ClientName dropdown..");
			
			if (doesElementExist2(properties.getProperty("ClinetOpts1"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ClinetOpts1")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().contains(AuditsClntName)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the ClientName "+AuditsClntName +" from the popup..");
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
						if (lnk.getText().contains(AuditsClntName)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false, "Selecting the ClientName "+AuditsClntName +" from the dropdown..");							
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
	    
	    if (doesElementExist2(properties.getProperty("selAppl1"), 5)) {	   
	    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selAppl1")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
	    	
	    	Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on ApplicationName dropdown..");
			
			if (doesElementExist2(properties.getProperty("ApplOpts1"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ApplOpts1")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().contains(AuditsAppName)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Application Name "+AuditsAppName +" from the popup..");
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
						if (lnk.getText().contains(AuditsAppName)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false, "Selecting the Application Name "+AuditsAppName +" from the dropdown..");							
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
			PleasewaitDisappear();
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on OK button to view the Audits");
	    } else {
			log.logLine(Testname, true, "Clicking on OK button to view the Audits is failed");
			throw new Exception("Clicking on OK button to view the Audits is failed");
		}
	    
	    
	    Thread.sleep(10000);
	    
	    if (doesElementExist(properties.getProperty("MakefavoriteAudits"), 5)) {	    
			WebElement mke = driver.findElement(By.xpath(properties.getProperty("MakefavoriteAudits")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", mke);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicking on Make favorite button is successful");
	    } else {
			log.logLine(Testname, true, "Clicking on Make favorite button is Unsuccessful");
			throw new Exception("Clicking on Make favorite button is Unsuccessful");
		}
	    
	    if (doesElementExist(properties.getProperty("MakefavoriteAudits"), 5)) {	    
			String Mkefvr = driver.findElement(By.xpath(properties.getProperty("MakefavoriteAudits"))).getText();
			if(Mkefvr.equals("Make Favorite")){
				WebElement Mkefvror = driver.findElement(By.xpath(properties.getProperty("MakefavoriteAudits")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", Mkefvror);
				log.logLine(Testname, false, "Clicking on Make favorite for Audits is successful");
				
			}else if(Mkefvr.equals("Remove Favorite")){
				log.logLine(Testname, false, "Audits already selected as favorites");
				
			} 
		
	    } else {
			log.logLine(Testname, true, "Clicking on Make favorite for Audits is unsuccessful");
			
		}
	
	    return true;
 	}
	
		
	public boolean AuditsRules(String Testname) throws Exception {
 		
		if (doesElementExist2(properties.getProperty("AuditRules"), 5)) {	    
			WebElement ruleslnk = driver.findElement(By.cssSelector(properties.getProperty("AuditRules")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", ruleslnk);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Audit Rules");
		} else {
			log.logLine(Testname, true, "Audit Rules link does not exist");
			throw new Exception("Audit Rules link does not exist");
		}	
		
	    Thread.sleep(1000);
	    boolean CliSelected = false;
	    
	    if (doesElementExist2(properties.getProperty("selClint1"), 5)) {	   
	    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selClint1")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
	    	
	    	Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on ClientName dropdown..");
			
			if (doesElementExist2(properties.getProperty("ClinetOpts1"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ClinetOpts1")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().contains(AuditsClntName)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the ClientName "+AuditsClntName +" from the popup..");
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
						if (lnk.getText().contains(AuditsClntName)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false, "Selecting the ClientName "+AuditsClntName +" from the dropdown..");							
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
	    
	    if (doesElementExist2(properties.getProperty("selAppl1"), 5)) {	   
	    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selAppl1")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
	    	
	    	Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on ApplicationName dropdown..");
			
			if (doesElementExist2(properties.getProperty("ApplOpts1"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ApplOpts1")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().contains(AuditsRulesAppName)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Application Name "+AuditsRulesAppName +" from the popup..");
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
						if (lnk.getText().contains(AuditsRulesAppName)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false, "Selecting the Application Name "+AuditsRulesAppName +" from the dropdown..");							
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
			PleasewaitDisappear();
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on OK button to view the Audits");
	    } else {
			log.logLine(Testname, true, "Clicking on OK button to view the Audits is failed");
			throw new Exception("Clicking on OK button to view the Audits is failed");
		}
	    
	    
	    Thread.sleep(10000);
	    
	    if (doesElementExist(properties.getProperty("MakefavoriteAudits"), 5)) {	    
			WebElement mke = driver.findElement(By.xpath(properties.getProperty("MakefavoriteAudits")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", mke);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicking on Make favorite button is successful");
	    } else {
			log.logLine(Testname, true, "Clicking on Make favorite button is Unsuccessful");
			throw new Exception("Clicking on Make favorite button is Unsuccessful");
		}
	    
	    if (doesElementExist(properties.getProperty("MakefavoriteAudits"), 5)) {	    
			String Mkefvr = driver.findElement(By.xpath(properties.getProperty("MakefavoriteAudits"))).getText();
			if(Mkefvr.equals("Make Favorite")){
				WebElement Mkefvror = driver.findElement(By.xpath(properties.getProperty("MakefavoriteAudits")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", Mkefvror);
				log.logLine(Testname, false, "Clicking on Make favorite for Audits is successful");
				
			}else if(Mkefvr.equals("Remove Favorite")){
				log.logLine(Testname, false, "Audits already selected as favorites");
				
			} 
		
	    } else {
			log.logLine(Testname, true, "Clicking on Make favorite for Audits is unsuccessful");
			
		}
	
	    return true;
 	}
	
	
	public boolean Pullsstmnts(String Testname) throws Exception {
	 		
		if (doesElementExist2(properties.getProperty("Pullsmenu"), 5)) {	    
			WebElement mnu = driver.findElement(By.cssSelector(properties.getProperty("Pullsmenu")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", mnu);
			PleasewaitDisappear();
			Thread.sleep(2000);
			log.logLine(Testname, false, "Navigation to Pulls page successful");
		} else {
			log.logLine(Testname, true, "Clicking on Pulls menu is failed");
			throw new Exception("Clicking on Pulls menu is failed");
		}
		
			
	    Thread.sleep(1000);
	    boolean CliSelected = false;
	    
	    if (doesElementExist2(properties.getProperty("selClint1"), 5)) {	   
	    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selClint1")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
	    	
	    	Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on ClientName dropdown..");
			
			if (doesElementExist2(properties.getProperty("ClinetOpts1"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ClinetOpts1")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().contains(PullsstmntsClntName)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the ClientName "+PullsstmntsClntName +" from the popup..");
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
						if (lnk.getText().contains(PullsstmntsClntName)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false, "Selecting the ClientName "+PullsstmntsClntName +" from the dropdown..");							
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
	    
	    if (doesElementExist2(properties.getProperty("selAppl1"), 5)) {	   
	    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selAppl1")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
	    	
	    	Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on ApplicationName dropdown..");
			
			if (doesElementExist2(properties.getProperty("ApplOpts1"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ApplOpts1")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().contains(PullsstmntsAppName)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Application Name "+PullsstmntsAppName +" from the popup..");
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
						if (lnk.getText().contains(PullsstmntsAppName)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false, "Selecting the Application Name "+PullsstmntsAppName +" from the dropdown..");							
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
			PleasewaitDisappear();
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on OK button to view the Pulls");
	    } else {
			log.logLine(Testname, true, "Clicking on OK button to view the Pulls is failed");
			throw new Exception("Clicking on OK button to view the Pulls is failed");
		}
	    
	    
	    Thread.sleep(10000);
	    
	    if (doesElementExist(properties.getProperty("MakefavoritePulls"), 5)) {	    
			WebElement mke = driver.findElement(By.xpath(properties.getProperty("MakefavoritePulls")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", mke);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicking on Make favorite button is successful");
	    } else {
			log.logLine(Testname, true, "Clicking on Make favorite button is Unsuccessful");
			throw new Exception("Clicking on Make favorite button is Unsuccessful");
		}
	    
	    if (doesElementExist(properties.getProperty("MakefavoritePulls"), 5)) {	    
			String Mkefvr = driver.findElement(By.xpath(properties.getProperty("MakefavoritePulls"))).getText();
			if(Mkefvr.equals("Make Favorite")){
				WebElement Mkefvror = driver.findElement(By.xpath(properties.getProperty("MakefavoritePulls")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", Mkefvror);
				log.logLine(Testname, false, "Clicking on Make favorite for Pulls is successful");
				
			}else if(Mkefvr.equals("Remove Favorite")){
				log.logLine(Testname, false, "Pulls already selected as favorites");
				
			} 
		
	    } else {
			log.logLine(Testname, true, "Clicking on Make favorite for Pulls is unsuccessful");
			
		}
	
	    return true;
 	}
	
	
	
	public boolean ReportWriter(String Testname) throws Exception {
			 		
		if (doesElementExist2(properties.getProperty("Reports"), 5)) {
	    	WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Reports")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);	    	 
	    	 Thread.sleep(2000);
	    	 PleasewaitDisappear();
	    	log.logLine(Testname, false, "Click on Reports Module is Successful");
	    } else {
	    	log.logLine(Testname, true, "Click on Reports Module is failed");
	    	driver.switchTo().defaultContent();
	    	throw new Exception("Click on Reports Module is failed");
	    }  
		
			
	    Thread.sleep(1000);
	    boolean CliSelected = false;
	    
	    if (doesElementExist2(properties.getProperty("selClint1"), 5)) {	   
	    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selClint1")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
	    	
	    	Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on ClientName dropdown..");
			
			if (doesElementExist2(properties.getProperty("ClinetOpts1"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ClinetOpts1")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().contains(ReportWriterClntName)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the ClientName "+ReportWriterClntName +" from the popup..");
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
						if (lnk.getText().contains(ReportWriterClntName)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false, "Selecting the ClientName "+ReportWriterClntName +" from the dropdown..");							
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
	    
	    if (doesElementExist2(properties.getProperty("selAppl1"), 5)) {	   
	    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selAppl1")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
	    	
	    	Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on ApplicationName dropdown..");
			
			if (doesElementExist2(properties.getProperty("ApplOpts1"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ApplOpts1")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(ReportWriterAppName)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Application Name "+ReportWriterAppName +" from the popup..");
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
						if (lnk.getText().equals(ReportWriterAppName)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false, "Selecting the Application Name "+ReportWriterAppName +" from the dropdown..");							
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
			PleasewaitDisappear();
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on OK button to view the ReportWriter");
	    } else {
			log.logLine(Testname, true, "Clicking on OK button to view the ReportWriter is failed");
			throw new Exception("Clicking on OK button to view the ReportWriter is failed");
		}
	    
	    
	    Thread.sleep(10000);
	    
	    if (doesElementExist(properties.getProperty("MakefavoriteReports"), 5)) {	    
			WebElement mke = driver.findElement(By.xpath(properties.getProperty("MakefavoriteReports")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", mke);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicking on Make favorite button is successful");
	    } else {
			log.logLine(Testname, true, "Clicking on Make favorite button is Unsuccessful");
			throw new Exception("Clicking on Make favorite button is Unsuccessful");
		}
	    
	    if (doesElementExist(properties.getProperty("MakefavoriteReports"), 5)) {	    
			String Mkefvr = driver.findElement(By.xpath(properties.getProperty("MakefavoriteReports"))).getText();
			if(Mkefvr.equals("Make Favorite")){
				WebElement Mkefvror = driver.findElement(By.xpath(properties.getProperty("MakefavoriteReports")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", Mkefvror);
				Thread.sleep(3000);
				log.logLine(Testname, false, "Clicking on Make favorite for ReportWriter is successful");
				
			}else if(Mkefvr.equals("Remove Favorite")){
				log.logLine(Testname, false, "ReportWriter already selected as favorites");
				
			} 
		
	    } else {
			log.logLine(Testname, true, "Clicking on Make favorite for ReportWriter is unsuccessful");
			
		}
	
	    return true;
	}
	

	public boolean Archive(String Testname) throws Exception {
 		
		
		 Thread.sleep(15000);
		if (doesElementExist2(properties.getProperty("Archives"), 5)) {
			WebElement arclnk = driver.findElement(By.cssSelector(properties.getProperty("Archives")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", arclnk);
			Thread.sleep(1000);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Navigation to Archives page is successfull");
		} else {
			log.logLine(Testname, true, "Clicking on Archives menu is failed");
			throw new Exception("Clicking on Archives menu is failed");
		}    
		
			
	    Thread.sleep(1000);
	    boolean CliSelected = false;
	    
	    if (doesElementExist2(properties.getProperty("selClint1"), 5)) {	   
	    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selClint1")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
	    	
	    	Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on ClientName dropdown..");
			
			if (doesElementExist2(properties.getProperty("ClinetOpts1"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ClinetOpts1")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().contains(ArchiveClntName)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the ClientName "+ArchiveClntName +" from the popup..");
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
						if (lnk.getText().contains(ArchiveClntName)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false, "Selecting the ClientName "+ArchiveClntName +" from the dropdown..");							
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
	    
	    if (doesElementExist2(properties.getProperty("selAppl1"), 5)) {	   
	    	WebElement ClientSel = driver.findElement(By.cssSelector(properties.getProperty("selAppl1")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", ClientSel);
	    	
	    	Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on ApplicationName dropdown..");
			
			if (doesElementExist2(properties.getProperty("ApplOpts1"), 5)) {
				List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ApplOpts1")));
				for (WebElement lnk:selopts) {
					if (lnk.getText().equals(ArchiveAppName)) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
						Thread.sleep(1000);
						log.logLine(Testname, false, "Selecting the Application Name "+ArchiveAppName +" from the popup..");
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
						if (lnk.getText().equals(ArchiveAppName)) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							Thread.sleep(1000);
							log.logLine(Testname, false, "Selecting the Application Name "+ArchiveAppName +" from the dropdown..");							
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
			PleasewaitDisappear();
			Thread.sleep(1000);
			log.logLine(Testname, false, "Clicking on OK button to view the Pulls");
	    } else {
			log.logLine(Testname, true, "Clicking on OK button to view the Pulls is failed");
			throw new Exception("Clicking on OK button to view the Pulls is failed");
		}
	    
	    Thread.sleep(5000);
	    if (doesElementExist2(properties.getProperty("Archivesearchmodule"), 5)) {
	    	WebElement btnsrch = driver.findElement(By.cssSelector(properties.getProperty("Archivesearchmodule")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", btnsrch);	    	 
	    	 Thread.sleep(2000);
	    	 PleasewaitDisappear();
	    	log.logLine(Testname, false, "Click on Archivesearchmodule is successfull");
	    } else {
	    	log.logLine(Testname, true, "Archivesearchmodule is not displayed");
	    	driver.switchTo().defaultContent();
	    	throw new Exception("Archivesearchmodule is not displayed");
	    }
	    
	    Thread.sleep(10000);
	    
	    if (doesElementExist(properties.getProperty("MakefavoriteArchives"), 5)) {	    
			WebElement mke = driver.findElement(By.xpath(properties.getProperty("MakefavoriteArchives")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", mke);
			Thread.sleep(3000);
			log.logLine(Testname, false, "Clicking on Make favorite button is successful");
	    } else {
			log.logLine(Testname, true, "Clicking on Make favorite button is Unsuccessful");
			throw new Exception("Clicking on Make favorite button is Unsuccessful");
		}
	    
	    if (doesElementExist(properties.getProperty("MakefavoriteArchives"), 5)) {	    
			String Mkefvr = driver.findElement(By.xpath(properties.getProperty("MakefavoriteArchives"))).getText();
			if(Mkefvr.equals("Make Favorite")){
				WebElement Mkefvror = driver.findElement(By.xpath(properties.getProperty("MakefavoriteArchives")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", Mkefvror);
				log.logLine(Testname, false, "Clicking on Make favorite for Archive is successful");
				
			}else if(Mkefvr.equals("Remove Favorite")){
				log.logLine(Testname, false, "Archive already selected as favorites");
				
			} 
		
	    } else {
			log.logLine(Testname, true, "Clicking on Make favorite for Archive is unsuccessful");
			
		}
	
	    return true;
 	}
	

	public boolean ProofsFavoriteValidation(String Testname) throws Exception {
		
		Thread.sleep(8000);
		if (doesElementExist(properties.getProperty("FavoriteButton"), 5)) {	   
	    	WebElement fvr = driver.findElement(By.xpath(properties.getProperty("FavoriteButton")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", fvr);
	    	Thread.sleep(2000);
			log.logLine(Testname, false, "Clicking on Favorite Button..");
			
				if (doesElementExist2(properties.getProperty("Favoriteoption"), 5)) {
					List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Favoriteoption")));
					for (WebElement lnk:selopts) {
						if (lnk.getAttribute("Value").equals("Search & Upload")) {
							Thread.sleep(2000);
							((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
							PleasewaitDisappear();
							Thread.sleep(8000);
							log.logLine(Testname, false, "Selecting the option as "+lnk.getText() +" from the dropdown..");							
							break;
						}				
					}
					
				} else {
					log.logLine(Testname, true, " Options are not displayed");
					throw new Exception("Options are not displayed");
				}
				
		    } else {
				log.logLine(Testname, true, "Dropdown element does not exist");
				throw new Exception("Dropdown element does not exist");
				}	
		
		 
		if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
			WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
			PleasewaitDisappear();
			log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
		} else {
			log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");
			//throw new Exception("Clicking on Cancel button in Client/App popup is failed");
		}
		
	
		    if (doesElementExist2(properties.getProperty("Proofs"), 5)) {	    
		    	String proofsmnu = driver.findElement(By.cssSelector(properties.getProperty("Proofs"))).getText();;
				log.logLine(Testname, false, "Navigation to Proofs module is successful..");
		    } else {
				log.logLine(Testname, false, "Navigation to Proofs module is successful..");
		//		throw new Exception("Clicking on Proofs menu is failed");
			}
		    
		    /*log.logLine(Testname, false, "Navigation to Proofs Page is successful");	    
		    Thread.sleep(1000);
		    boolean CliSelected = false;
		    Thread.sleep(8000);
		 if (doesElementExist(properties.getProperty("Proofing"), 5)) {	    
			String Prfng = driver.findElement(By.xpath(properties.getProperty("Proofing"))).getText();
			log.logLine(Testname, false, ""+Prfng+"Sub module is present in Proofing Module");
	    } else {
			log.logLine(Testname, true, "Sub module is not present in Proofing Module");
			}
		 
		 Thread.sleep(2000);
		 if (doesElementExist(properties.getProperty("ProofingClientvalidation"), 5)) {	    
				String Prfngclntvldn = driver.findElement(By.xpath(properties.getProperty("ProofingClientvalidation"))).getText();
				if(Prfngclntvldn.equals(ProofsClntName)){
					log.logLine(Testname, false, ""+Prfngclntvldn+" Matches with the "+ProofsClntName+"");
			} else {
				log.logLine(Testname, true, ""+Prfngclntvldn+" doesnot Matches with the "+ProofsClntName+"");
				}
		 } else {
				log.logLine(Testname, true, "Failed to validate the Client and Application for Proofing");
				}
		 
		 if (doesElementExist(properties.getProperty("ProofingAppvalidation"), 5)) {	    
				String Prfngappvldn = driver.findElement(By.xpath(properties.getProperty("ProofingAppvalidation"))).getText();
				if(Prfngappvldn.equals(ProofsAppName)){
					log.logLine(Testname, false, ""+Prfngappvldn+" Matches with the "+ProofsAppName+"");
			} else {
				log.logLine(Testname, true, ""+Prfngappvldn+" doesnot Matches with the "+ProofsAppName+"");
				}
		 } else {
				log.logLine(Testname, true, "Failed to validate the Client and Application for Proofing");
				}
		 */
		 /*
		 if (doesElementExist(properties.getProperty("RemovefavoriteProofs"), 5)) {	    
				WebElement mke = driver.findElement(By.xpath(properties.getProperty("RemovefavoriteProofs")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", mke);
				Thread.sleep(3000);
				log.logLine(Testname, false, "Clicking on Remove favorite button is successful");
		    } else {
				log.logLine(Testname, true, "Clicking on Remove favorite button is Unsuccessful");
				throw new Exception("Clicking on Remove favorite button is Unsuccessful");
			}
		 
		 
		 if (doesElementExist(properties.getProperty("FavoriteButton"), 5)) {	   
		    	WebElement fvr = driver.findElement(By.xpath(properties.getProperty("FavoriteButton")));
		    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", fvr);
		    	Thread.sleep(1000);
				log.logLine(Testname, false, "Clicking Favorites Button dropdown..");
				
					if (doesElementExist2(properties.getProperty("Favoriteoption"), 5)) {
						List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Favoriteoption")));
						for (WebElement lnk:selopts) {
							if (lnk.getText().equals("RGT1099 — Proofing")) {
								
							}else {
								log.logLine(Testname, false, ""+lnk.getText() +" Is not present in Favorites list");
								break;
								}
							}
						
					} else {
						log.logLine(Testname, true, "Favorite Option failed to slide down");
						throw new Exception("Favorite Option failed to slide down");
					}
					
			    } else {
					log.logLine(Testname, true, "Favorites Button is not presen");
					throw new Exception("Favorites Button is not presen");
					}
		 
	*/
		 return true;
	}
	
	
	public boolean AuditsFavoriteValidation(String Testname) throws Exception {
		
		 if (doesElementExist(properties.getProperty("FavoriteButton"), 5)) {	   
		    	WebElement fvr = driver.findElement(By.xpath(properties.getProperty("FavoriteButton")));
		    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", fvr);
		    	Thread.sleep(1000);
				log.logLine(Testname, false, "Clicking on Favorite Button..");
				
					if (doesElementExist2(properties.getProperty("Favoriteoption"), 5)) {
						List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Favoriteoption")));
						for (WebElement lnk:selopts) {
							if (lnk.getAttribute("Value").equals("Audit Search")) {
								Thread.sleep(2000);
								((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
								PleasewaitDisappear();
								Thread.sleep(6000);
								log.logLine(Testname, false, "Selecting the option as "+lnk.getText() +" from the dropdown..");							
								break;
							}				
						}
						
					} else {
						log.logLine(Testname, true, " Options are not displayed");
						throw new Exception("Options are not displayed");
					}
					
			    } else {
					log.logLine(Testname, true, "Dropdown element does not exist");
					throw new Exception("Dropdown element does not exist");
					} 
		 			
		 
		 if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
				WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
				PleasewaitDisappear();
				log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
			} else {
				log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");
				//throw new Exception("Clicking on Cancel button in Client/App popup is failed");
			}
		 
		 if (doesElementExist2(properties.getProperty("Audits"), 5)) {	    
				String reportsmnu = driver.findElement(By.cssSelector(properties.getProperty("Audits"))).getText();
				log.logLine(Testname, false, "Navigation to Audits page successful");
		    } else {
				log.logLine(Testname, true, "Navigation to Audits menu is failed");
				//throw new Exception("Clicking on Audits menu is failed");
			}	
		 
		 
			 
	 	 /*Thread.sleep(8000);
		 if (doesElementExist(properties.getProperty("Auditsearch"), 5)) {	    
			String Adtssrch = driver.findElement(By.xpath(properties.getProperty("Auditsearch"))).getText();
			log.logLine(Testname, false, ""+Adtssrch+"Sub module is present in Audits Module");
	    } else {
			log.logLine(Testname, true, "Sub module is not present in Audits Module");
			}
		 
		 Thread.sleep(2000);
		 if (doesElementExist(properties.getProperty("AuditsClientvalidation"), 5)) {	    
				String Audtsclntvldn = driver.findElement(By.xpath(properties.getProperty("AuditsClientvalidation"))).getText();
				if(Audtsclntvldn.equals(AuditsClntName)){
					log.logLine(Testname, false, ""+Audtsclntvldn+" Matches with the "+AuditsClntName+"");
			} else {
				log.logLine(Testname, true, ""+Audtsclntvldn+" doesnot Matches with the "+AuditsClntName+"");
				}
		 } else {
				log.logLine(Testname, true, "Failed to validate the Client and Application for Audits");
				}
		 
		 if (doesElementExist(properties.getProperty("AuditsAppvalidation"), 5)) {	    
				String Audtsappvldn = driver.findElement(By.xpath(properties.getProperty("AuditsAppvalidation"))).getText();
				if(Audtsappvldn.equals(AuditsAppName)){
					log.logLine(Testname, false, ""+Audtsappvldn+" Matches with the "+AuditsAppName+"");
			} else {
				log.logLine(Testname, true, ""+Audtsappvldn+" doesnot Matches with the "+AuditsAppName+"");
				}
		 } else {
				log.logLine(Testname, true, "Failed to validate the Client and Application for Audits");
				}
		 */
		 return true;
		 }
	
	
	public boolean AuditsRulesFavoriteValidation(String Testname) throws Exception {
		
		 if (doesElementExist(properties.getProperty("FavoriteButton"), 5)) {	   
		    	WebElement fvr = driver.findElement(By.xpath(properties.getProperty("FavoriteButton")));
		    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", fvr);
		    	Thread.sleep(1000);
				log.logLine(Testname, false, "Clicking on Favorite Button..");
				
					if (doesElementExist2(properties.getProperty("Favoriteoption"), 5)) {
						List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Favoriteoption")));
						for (WebElement lnk:selopts) {
							if (lnk.getAttribute("Value").equals("Audit Rules")) {
								Thread.sleep(2000);
								((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
								PleasewaitDisappear();
								Thread.sleep(4000);
								log.logLine(Testname, false, "Selecting the option as "+lnk.getText() +" from the dropdown..");							
								break;
							}				
						}
						
					} else {
						log.logLine(Testname, true, " Options are not displayed");
						throw new Exception("Options are not displayed");
					}
					
			    } else {
					log.logLine(Testname, true, "Dropdown element does not exist");
					throw new Exception("Dropdown element does not exist");
					}	
		 
		 if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
				WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
				PleasewaitDisappear();
				log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
			} else {
				log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");
				//throw new Exception("Clicking on Cancel button in Client/App popup is failed");
			}
		 
		 
		 if (doesElementExist2(properties.getProperty("Audits"), 5)) {	    
				String reportsmnu = driver.findElement(By.cssSelector(properties.getProperty("Audits"))).getText();
				log.logLine(Testname, false, "Navigation to Audits page successful");
		    } else {
				log.logLine(Testname, true, "Navigation to Audits menu is failed");
				//throw new Exception("Clicking on Audits menu is failed");
			}	 
		 
		
		 Thread.sleep(8000);
		/* if (doesElementExist(properties.getProperty("AuditRules"), 5)) {	    
			String Adtrls = driver.findElement(By.xpath(properties.getProperty("AuditRules"))).getText();
			log.logLine(Testname, false, ""+Adtrls+"Sub module is present in Audits Module");
	    } else {
			log.logLine(Testname, true, "Sub module is not present in Audits Module");
			}*/
		 
		/* Thread.sleep(2000);
		 if (doesElementExist(properties.getProperty("AuditsClientvalidation"), 5)) {	    
				String Adtrlsclntvldn = driver.findElement(By.xpath(properties.getProperty("AuditsClientvalidation"))).getText();
				if(Adtrlsclntvldn.equals(AuditsClntName)){
					log.logLine(Testname, false, ""+Adtrlsclntvldn+" Matches with the "+AuditsClntName+"");
			} else {
				log.logLine(Testname, true, ""+Adtrlsclntvldn+" doesnot Matches with the "+AuditsClntName+"");
				}
		 } else {
				log.logLine(Testname, true, "Failed to validate the Client and Application for Audits");
				}
		 
		 if (doesElementExist(properties.getProperty("AuditsAppvalidation"), 5)) {	    
				String Adtrlsappvldn = driver.findElement(By.xpath(properties.getProperty("AuditsAppvalidation"))).getText();
				if(Adtrlsappvldn.equals(AuditsRulesAppName)){
					log.logLine(Testname, false, ""+Adtrlsappvldn+" Matches with the "+AuditsRulesAppName+"");
			} else {
				log.logLine(Testname, true, ""+Adtrlsappvldn+" doesnot Matches with the "+AuditsRulesAppName+"");
				}
		 } else {
				log.logLine(Testname, true, "Failed to validate the Client and Application for Audits");
				}*/
		 
		 return true;
		 
		 }
	
	
	public boolean PullsFavoriteValidation(String Testname) throws Exception {
		
		 if (doesElementExist(properties.getProperty("FavoriteButton"), 5)) {	   
		    	WebElement fvr = driver.findElement(By.xpath(properties.getProperty("FavoriteButton")));
		    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", fvr);
		    	Thread.sleep(1000);
				log.logLine(Testname, false, "Clicking on Favorite Button..");
				
					if (doesElementExist2(properties.getProperty("Favoriteoption"), 5)) {
						List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Favoriteoption")));
						for (WebElement lnk:selopts) {
							if (lnk.getAttribute("Value").equals("Pull Instructions")) {
								((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
								PleasewaitDisappear();
								Thread.sleep(4000);
								log.logLine(Testname, false, "Selecting the option as "+lnk.getText() +" from the dropdown..");							
								break;
							}				
						}
						
					} else {
						log.logLine(Testname, true, " Options are not displayed");
						throw new Exception("Options are not displayed");
					}
					
			    } else {
					log.logLine(Testname, true, "Dropdown element does not exist");
					throw new Exception("Dropdown element does not exist");
					}
		 
		 
		 if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
				WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
				PleasewaitDisappear();
				log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
			} else {
				log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");
				//throw new Exception("Clicking on Cancel button in Client/App popup is failed");
			}
		 
		 
		 if (doesElementExist2(properties.getProperty("Pullsmenu"), 5)) {	    
				String plsmnu = driver.findElement(By.cssSelector(properties.getProperty("Pullsmenu"))).getText();
				log.logLine(Testname, false, "Navigation to Pulls module successful");
		    } else {
				log.logLine(Testname, true, "Navigation to Pulls module is failed");
				//throw new Exception("Clicking on Audits menu is failed");
			}	 
		 
		
		     Thread.sleep(8000);
			 /*if (doesElementExist(properties.getProperty("PullInstruction"), 5)) {	    
				String Pullinst = driver.findElement(By.xpath(properties.getProperty("PullInstruction"))).getText();
				log.logLine(Testname, false, ""+Pullinst+"Sub module is present in Pulls Module");
		    } else {
				log.logLine(Testname, true, "Sub module is not present in Pulls Module");
				}
			 */
			 
			/* if (doesElementExist(properties.getProperty("PullsClientvalidation"), 5)) {	    
					String Pullsinstclntvldn = driver.findElement(By.xpath(properties.getProperty("PullsClientvalidation"))).getText();
					if(Pullsinstclntvldn.equals(PullsstmntsClntName)){
						log.logLine(Testname, false, ""+Pullsinstclntvldn+" Matches with the "+PullsstmntsClntName+"");
				} else {
					log.logLine(Testname, true, ""+Pullsinstclntvldn+" doesnot Matches with the "+PullsstmntsClntName+"");
					}
			 } else {
					log.logLine(Testname, true, "Failed to validate the Client and Application for Pulls");
					}
			 
			 if (doesElementExist(properties.getProperty("PullsAppvalidation"), 5)) {	    
					String Pullsinstappvldn = driver.findElement(By.xpath(properties.getProperty("PullsAppvalidation"))).getText();
					if(Pullsinstappvldn.equals(PullsstmntsAppName)){
						log.logLine(Testname, false, ""+Pullsinstappvldn+" Matches with the "+PullsstmntsAppName+"");
				} else {
					log.logLine(Testname, true, ""+Pullsinstappvldn+" doesnot Matches with the "+PullsstmntsAppName+"");
					}
			 } else {
					log.logLine(Testname, true, "Failed to validate the Client and Application for Pulls");
					}
			 */
			 return true;
			 
	}
	
	
	public boolean ReportWriterFavoriteValidation(String Testname) throws Exception {
		
		 if (doesElementExist(properties.getProperty("FavoriteButton"), 5)) {	   
		    	WebElement fvr = driver.findElement(By.xpath(properties.getProperty("FavoriteButton")));
		    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", fvr);
		    	Thread.sleep(1000);
				log.logLine(Testname, false, "Clicking on Favorite Button..");
				
					if (doesElementExist2(properties.getProperty("Favoriteoption"), 5)) {
						List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Favoriteoption")));
						for (WebElement lnk:selopts) {
							if (lnk.getAttribute("Value").equals("Report Writer")) {
								Thread.sleep(2000);
								((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
								PleasewaitDisappear();
								Thread.sleep(4000);
								log.logLine(Testname, false, "Selecting the option as "+lnk.getText() +" from the dropdown..");							
								break;
							}				
						}
						
					} else {
						log.logLine(Testname, true, " Options are not displayed");
						throw new Exception("Options are not displayed");
					}
					
			    } else {
					log.logLine(Testname, true, "Dropdown element does not exist");
					throw new Exception("Dropdown element does not exist");
					}	
		 
		 
		 if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
				WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
				PleasewaitDisappear();
				log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
			} else {
				log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");
				//throw new Exception("Clicking on Cancel button in Client/App popup is failed");
			}
		 
		 if (doesElementExist2(properties.getProperty("Reports"), 5)) {	    
				String rpts = driver.findElement(By.cssSelector(properties.getProperty("Reports"))).getText();
				log.logLine(Testname, false, "Navigation to Reports Module successful");
		    } else {
				log.logLine(Testname, true, "Navigation to Reports Module is failed");
				//throw new Exception("Clicking on Audits menu is failed");
			} 
		 
		 
		
		 Thread.sleep(8000);
		 /*if (doesElementExist(properties.getProperty("ReportWriter"), 5)) {	    
			String Rptwrt = driver.findElement(By.xpath(properties.getProperty("ReportWriter"))).getText();
			log.logLine(Testname, false, ""+Rptwrt+"Sub module is present in Reports Module");
	    } else {
			log.logLine(Testname, true, "Sub module is not present in Reports Module");
			}*/
		 
		/* Thread.sleep(2000);
		 if (doesElementExist(properties.getProperty("ReportWriterClientvalidation"), 5)) {	    
				String Rptwrtrclntvldn = driver.findElement(By.xpath(properties.getProperty("ReportWriterClientvalidation"))).getText();
				if(Rptwrtrclntvldn.equals(ReportWriterClntName)){
					log.logLine(Testname, false, ""+Rptwrtrclntvldn+" Matches with the "+ReportWriterClntName+"");
			} else {
				log.logLine(Testname, true, ""+Rptwrtrclntvldn+" doesnot Matches with the "+ReportWriterClntName+"");
				}
		 } else {
				log.logLine(Testname, true, "Failed to validate the Client and Application for Report Writer");
				}
		 
		 if (doesElementExist(properties.getProperty("ReportWriterAppvalidation"), 5)) {	    
				String Rptwrtrappvldn = driver.findElement(By.xpath(properties.getProperty("ReportWriterAppvalidation"))).getText();
				if(Rptwrtrappvldn.equals(ReportWriterAppName)){
					log.logLine(Testname, false, ""+Rptwrtrappvldn+" Matches with the "+ReportWriterAppName+"");
			} else {
				log.logLine(Testname, true, ""+Rptwrtrappvldn+" doesnot Matches with the "+ReportWriterAppName+"");
				}
		 } else {
				log.logLine(Testname, true, "Failed to validate the Client and Application for Report Writer");
				}*/
		 
		 return true;
		 }
	
	
	public boolean ArchiveFavoriteValidation(String Testname) throws Exception {
		
		 if (doesElementExist(properties.getProperty("FavoriteButton"), 5)) {	   
		    	WebElement fvr = driver.findElement(By.xpath(properties.getProperty("FavoriteButton")));
		    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", fvr);
		    	Thread.sleep(1000);
				log.logLine(Testname, false, "Clicking on Favorite Button..");
				
					if (doesElementExist2(properties.getProperty("Favoriteoption"), 5)) {
						List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("Favoriteoption")));
						for (WebElement lnk:selopts) {
							if (lnk.getAttribute("Value").equals("Search")) {
								Thread.sleep(2000);
								((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
								PleasewaitDisappear();
								Thread.sleep(4000);
								log.logLine(Testname, false, "Selecting the option as "+lnk.getText() +" from the dropdown..");							
								break;
							}				
						}
						
					} else {
						log.logLine(Testname, true, " Options are not displayed");
						throw new Exception("Options are not displayed");
					}
					
			    } else {
					log.logLine(Testname, true, "Dropdown element does not exist");
					throw new Exception("Dropdown element does not exist");
					}	
		
		 
		 
		 
		 if (doesElementExist2(properties.getProperty("Canbtn"), 5)) {	    
				WebElement okbtn = driver.findElement(By.cssSelector(properties.getProperty("Canbtn")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", okbtn);
				PleasewaitDisappear();
				log.logLine(Testname, false, "Clicking on Cancel button in Client/App popup");
			} else {
				log.logLine(Testname, true, "Clicking on Cancel button in Client/App popup is failed");
				//throw new Exception("Clicking on Cancel button in Client/App popup is failed");
			}
		 
		 
		 if (doesElementExist2(properties.getProperty("Archives"), 5)) {	    
				String arch = driver.findElement(By.cssSelector(properties.getProperty("Archives"))).getText();
				log.logLine(Testname, false, "Navigation to Archives module successful");
		    } else {
				log.logLine(Testname, true, "Navigation to Archives module is failed");
				//throw new Exception("Clicking on Audits menu is failed");
			} 
		 
		 Thread.sleep(8000);
		 /*if (doesElementExist(properties.getProperty("Archivesrch"), 5)) {	    
			String Archvs = driver.findElement(By.xpath(properties.getProperty("Archivesrch"))).getText();
			log.logLine(Testname, false, ""+Archvs+"Sub module is present in Archives Module");
	    } else {
			log.logLine(Testname, true, "Sub module is not present in Archives Module");
			}*/
		 
		 /*Thread.sleep(2000);
		 if (doesElementExist(properties.getProperty("ArchiveClientvalidation"), 5)) {	    
				String Archvsclntvldn = driver.findElement(By.xpath(properties.getProperty("ArchiveClientvalidation"))).getText();
				if(Archvsclntvldn.equals(ArchiveClntName)){
					log.logLine(Testname, false, ""+Archvsclntvldn+" Matches with the "+ArchiveClntName+"");
			} else {
				log.logLine(Testname, true, ""+Archvsclntvldn+" doesnot Matches with the "+ArchiveClntName+"");
				}
		 } else {
				log.logLine(Testname, true, "Failed to validate the Client and Application for Archives");
				}
		 
		 Thread.sleep(2000);
		 if (doesElementExist(properties.getProperty("ArchiveAppvalidation"), 5)) {	    
				String Archvsappvldn = driver.findElement(By.xpath(properties.getProperty("ArchiveAppvalidation"))).getText();
				if(Archvsappvldn.equals(ArchiveAppName)){
					log.logLine(Testname, false, ""+Archvsappvldn+" Matches with the "+ArchiveAppName+"");
			} else {
				log.logLine(Testname, true, ""+Archvsappvldn+" doesnot Matches with the "+ArchiveAppName+"");
				}
		 } else {
				log.logLine(Testname, true, "Failed to validate the Client and Application for Archives");
				}*/
		 
		 return true;
		 }

	
	public boolean DeleteFavoriteValidation(String Testname) throws Exception {
		
		 if (doesElementExist(properties.getProperty("FavoriteButton"), 5)) {	   
		    	WebElement fvr = driver.findElement(By.xpath(properties.getProperty("FavoriteButton")));
		    	((JavascriptExecutor) driver).executeScript("arguments[0].click()", fvr);
		    	Thread.sleep(1000);
				log.logLine(Testname, false, "Clicking on Favorite Button..");
				
				Thread.sleep(5000);
				
					if (doesElementExist2(properties.getProperty("ManageFavoritbtn"), 5)) {
						List<WebElement> selopts = driver.findElements(By.cssSelector(properties.getProperty("ManageFavoritbtn")));
						for (WebElement lnk:selopts) {
							if (lnk.getText().equals("Manage Favorites")) {
								((JavascriptExecutor) driver).executeScript("arguments[0].click()", lnk);
								PleasewaitDisappear();
								Thread.sleep(8000);
								log.logLine(Testname, false, "Selecting the option as "+lnk.getText() +" from the dropdown..");							
								break;
							}				
						}
						
					} else {
						log.logLine(Testname, true, " Options are not displayed");
						throw new Exception("Options are not displayed");
					}
					
			    } else {
					log.logLine(Testname, true, "Dropdown element does not exist");
					throw new Exception("Dropdown element does not exist");
					}	
		 
		 for(int i=1;i<=6;i++){
		 if (doesElementExist(properties.getProperty("DeleteFavorites"), 5)) {	 
			 	String Favours = driver.findElement(By.xpath(properties.getProperty("FavoritesText"))).getText();
				WebElement Delbtn = driver.findElement(By.xpath(properties.getProperty("DeleteFavorites")));
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", Delbtn);
				Thread.sleep(5000);
				log.logLine(Testname, false, "Deleting  "+Favours+"  from Favorite list is successful");
				i=i++;
			}
		 	
		 }
		 log.logLine(Testname, false, "Deleting added fovorites from Favorite list is successful");
		 
		 Thread.sleep(5000);
		 if (doesElementExist(properties.getProperty("CloseFavoritelistpopup"), 5)) {	    
			WebElement Clselst = driver.findElement(By.xpath(properties.getProperty("CloseFavoritelistpopup")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", Clselst);
			Thread.sleep(2000);
			log.logLine(Testname, false, "Closing the favorite list pop up is successful");
		} else {
			log.logLine(Testname, true, "Closing the favorite list pop up is failed");
			throw new Exception("Closing the favorite list pop up is failed");
		}
		 
		 
		 
			

			 return true;
			 
	}
	
}


 	

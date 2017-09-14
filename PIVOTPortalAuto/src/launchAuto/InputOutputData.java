package launchAuto;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import java.io.IOException;  
import java.util.Properties;  
import javax.mail.Folder;  
import javax.mail.Message;  
import javax.mail.MessagingException;  
import javax.mail.NoSuchProviderException;  
import javax.mail.Session;  
import com.sun.mail.pop3.POP3Store;  
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

public class InputOutputData {

	private String inputFile;	
	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}
	
	// This method reads the driver file 
	public String readControlFile(String fieldname, String inputsheet, int mycnt) throws IOException  {
		
		File inputWorkbook = new File(inputFile);
		Workbook w;
		String cellData = null;
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			Sheet sheet = w.getSheet(inputsheet);
			Cell cell1 = sheet.getCell(3,0);
			//System.out.println(cell1.getContents());
			if (cell1.getContents().equals(fieldname)) {
				Cell nxtcell = sheet.getCell(3, mycnt);			
				cellData = nxtcell.getContents();							
			}				
		} catch (BiffException e) {
			e.printStackTrace();
		}
		return cellData;
		
	}	
	
	// This method reads the data from the first cell of input data sheet
	public String readColumnData(String fieldname, String inputsheet) throws IOException  {
		File inputWorkbook = new File(inputFile);
		Workbook w;
		String cellData = null;
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			// Get the first sheet
			Sheet sheet = w.getSheet(inputsheet);
			// Loop over first 10 column and lines
			
			for (int j = 0; j < sheet.getColumns(); j++) {				
				Cell cell = sheet.getCell(j, 0);				
				if (cell.getContents().equals(fieldname)) {					
					cellData = sheet.getCell(j, 1).getContents();
					break;
				}				
			}		
		} catch (BiffException e) {
			e.printStackTrace();
		}		
		return cellData;
	}
	
	
	public String readConfigXML() {
		
		String Constants = "";
		try {						 
			File fXmlFile = new File("C:/Pivot_Portal/Config/Config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();		 
			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
					 
			NodeList nList = doc.getElementsByTagName("Client");		 
			//System.out.println("----------------------------");
		 
			for (int temp = 0; temp < nList.getLength(); temp++) {		 
				Node nNode = nList.item(temp);		 
				//System.out.println("\nCurrent Element :" + nNode.getNodeName());
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
					if (!((eElement.getElementsByTagName("Status").item(0).getTextContent()).equalsIgnoreCase("completed")))
							 {
			 
							Constants = temp + " " + eElement.getAttribute("env") + " "  
									+ eElement.getElementsByTagName("UserID").item(0).getTextContent() + " "
									+ eElement.getElementsByTagName("Passwd").item(0).getTextContent() + " "
									+ eElement.getElementsByTagName("Browser").item(0).getTextContent() + " "
									+ eElement.getElementsByTagName("ExeMultipleUser").item(0).getTextContent() + " "
									+ eElement.getElementsByTagName("eDelivery").item(0).getTextContent() + " "
									+ eElement.getElementsByTagName("FileUploads").item(0).getTextContent() + " "
									+ eElement.getElementsByTagName("EmailIds").item(0).getTextContent();
							break;											 
					}
				}
			}
		} catch (Exception e) {
		   	e.printStackTrace();
		}
		return Constants;
	}
	
	
	public void UpdateConfigXML(int iterXML) {		
		
		try {
			String filepath = "C:/Pivot_Portal/Config/Config.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
	 
			// Get the root element
			Node company = doc.getFirstChild();
	 
			// Get the staff element by tag name directly
			Node client = doc.getElementsByTagName("Client").item(iterXML);

			// loop the staff child node
			NodeList list = client.getChildNodes();	 
			for (int i = 0; i < list.getLength(); i++) {
	 
	           Node node = list.item(i);	 
			   // get the Status element, and update the value
			   if ("Status".equals(node.getNodeName())) {
				   node.setTextContent("Completed");
				   break;
			   }	 
			}
	 
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);
	 
			//System.out.println("Updated XML file");	 
		} catch (ParserConfigurationException pce) {
			   pce.printStackTrace();
		} catch (TransformerException tfe) {
			   tfe.printStackTrace();
		} catch (IOException ioe) {
			   ioe.printStackTrace();
		} catch (SAXException sae) {
			   sae.printStackTrace();
		}		
	}
	
	
	public void CopyReportFile(String tstamp) throws IOException {
		
		InputStream inStream = null;
        OutputStream outStream = null;
        //Copy the report file from Config folder to PA_Reports folder
        try{ 
            File file1 =new File("C:/Pivot_Portal/Config/SampleReport.xls");
            File file2 =new File("C:/Pivot_Portal/Report & Logs/PATAReport_"+Initialization.mydate+"/PAReport_"+tstamp+".xls");
 
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
      
	}
	
	
	
	@SuppressWarnings("static-access")
	public void SendReportEmail(String Subject) throws InvalidFormatException  {
		try {
			Properties props = new Properties();
		    props.put("mail.transport.protocol", "smtp");
		    props.put("mail.host", "smtp.gmail.com");
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.port", "587");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.debug", "false");
			
			/*props.put("mail.transport.protocol", "smtp");    
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			props.put("mail.smtp.socketFactory.port", "465");*/
		    
		    Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
		    	protected PasswordAuthentication getPasswordAuthentication() {
		    		return new PasswordAuthentication("automationpivot@gmail.com", "miracle@123");
		    	}
		    });
		    //mailSession.setDebug(true);

		    //Session mailSession = Session.getDefaultInstance(props, null);
		    Transport transport = mailSession.getTransport();

		    MimeMessage message = new MimeMessage(mailSession);
		    message.setSubject(Subject);
		    
		    // Create the message part 
	        BodyPart messageBodyPart = new MimeBodyPart();

	        String htmlBody = "Hi Team, <BR><BR>Please find the attached PIVOT automation report.<BR><BR> Thanks <BR>Srinivas<BR><BR> " +
							"Note: This is automatically generated email, reply to srinivas.chamalapura@rrd.com for any queries or concerns.";
	        
	        // Create a multi-part message
	        Multipart multipart = new MimeMultipart();
	        
	        MimeBodyPart htmlPart = new MimeBodyPart();
	        htmlPart.setContent(htmlBody, "text/html");

	        // Set text message part
	        multipart.addBodyPart(htmlPart);

	        // Part two is attachment
	        messageBodyPart = new MimeBodyPart();
	        
	        File dirpath = new File("C:/Pivot_Portal/Report & Logs/");
	        File filename = new File("C:/Pivot_Portal/Report & Logs/PATAReport_"+Initialization.mydate);//+"/PAReport_"+Initialization.myTimestamp+".xls";
	        String ZipAttach = "C:/Pivot_Portal/Report & Logs/PATAReport_"+Initialization.mydate+".zip";
	        
	        //Zip the folder before attaching it
	        List<File> fileList = new ArrayList<File>();		
	        CreateAppData.getAllFiles(filename, fileList);			
	        CreateAppData.writeZipFile(dirpath.getAbsolutePath(), filename, fileList);				        		        
	        
	        DataSource source = new FileDataSource(ZipAttach);
	        messageBodyPart.setDataHandler(new DataHandler(source));
	        messageBodyPart.setFileName(ZipAttach);
	        multipart.addBodyPart(messageBodyPart);

	        // Send the complete message parts
	        message.setContent(multipart );          
	        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(Initialization.EmailIds));      
	        message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("srinivas.chamalapura@rrd.com"));
	        message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("vinod.patil@rrd.com"));
		  
		    transport.connect();
		    //transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO, Message.RecipientType.CC));
		    transport.send(message);
		    transport.close();	 		    
		    
		    
		    //Delete the folder and files in it		    
		    removeDirectory(filename);		     
	      
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}	
	
	
	public static boolean removeDirectory(File directory) {

		  // System.out.println("removeDirectory " + directory);
	
		  if (directory == null)
		    return false;
		  if (!directory.exists())
		    return true;
		  if (!directory.isDirectory())
		    return false;
	
		  String[] list = directory.list();
	
		  // Some JVMs return null for File.list() when the
		  // directory is empty.
		  if (list != null) {
		    for (int i = 0; i < list.length; i++) {
		      File entry = new File(directory, list[i]);
	
		      //        System.out.println("\tremoving entry " + entry);
	
		      if (entry.isDirectory())
		      {
		        if (!removeDirectory(entry))
		          return false;
		      }
		      else
		      {
		        if (!entry.delete())
		          return false;
		      }
		    }
		  }
	
		  return directory.delete();
	}
	
	public void ReadMail() {
		
		Properties props = new Properties();
		  try {
		   props.load(new FileInputStream(new File("C:/Pivot_Portal/ReadMail.properties")));
		   Session session = Session.getDefaultInstance(props, null);

		   Store store = session.getStore("imaps");
		   store.connect("smtp.gmail.com", "automationpivot@gmail.com","miracle123");

		   Folder inbox = store.getFolder("inbox");
		   inbox.open(Folder.READ_ONLY);	   
		   
		   int messageCount = inbox.getMessageCount();

		   System.out.println("Total Messages:- " + messageCount);

		   Message[] messages = inbox.getMessages();
		   System.out.println("------------------------------");
		   for (int i = 0; i < 10; i++) {
		      System.out.println("Mail Subject:- " + messages[i].getSubject());
		      System.out.println("Mail Body:- " + messages[i].getContent().toString()); 
		   }
		   inbox.close(true);
		   store.close();

		  } catch (Exception e) {
		   e.printStackTrace();
		  }  
	   
	}
	
	public void KillautoProcess(String Browser) throws Exception {
		
		String OSbits = System.getProperty("sun.arch.data.model");			
		if (OSbits.equals("64")) { 
			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer64.exe");
		} else  {
			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer32.exe");
		}
	
		 Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
		
		//Kill all the process which are opened at this run
		Runtime.getRuntime().exec("taskkill /F /IM AcroRd32.exe");		
		
	}
	
	
	
	
}



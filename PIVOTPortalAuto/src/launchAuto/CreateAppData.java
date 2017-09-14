package launchAuto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;



import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;


public class CreateAppData {
	public static Log log;
	
	public CreateAppData(Log log) {		
		this.log = log;	
	}
	
	public static String ConsumerID1, ConsumerID2, ConsumerID3, ConsumerID4, User1, User2;
	public static String PDocument1,PDocument2,PDocument3,PDocument4,PDocument5,PDocument6,PDocument7,PDocument8,PDocument9,PDocument10;
	public static String consId, order ;
	public static long startTime;
	
	public void CreateAudits(String AccNo, String Testname) throws Exception {
		
		log.logLine(Testname, false, "Audits uploading from backend...");
		
		File dirpath = new File("C:/Pivot_Portal/Test Data/");
		
        if ((!dirpath.isDirectory())  || (!dirpath.exists())){
        	log.logLine(Testname, true, "Audit test data folder is not exist @ given path");            
            System.exit(0);
        }        
        
        String envi = Initialization.EnvirSite.toUpperCase();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");		
		Date date = new Date();
		String todaysDate = dateFormat.format(date);
		
		InputOutputData test = new InputOutputData();		
	    test.setInputFile("C:/Pivot_Portal/Test Data/AuditsSearch.xls");		
		String AppID = test.readColumnData("ApplicationName", "Auditspage");
		
		String NamingConv = AppID+"_L_PCIDFS"+envi +"_105_"+todaysDate +"_0002_auditv2_"+AccNo;
		String TxtContent = NamingConv+".pdf;00001;2;Automate-"+AccNo+";"+todaysDate+";0000000000;000;000000;111111;Field;0000007;Field";
		String AdtContent1 = NamingConv +".pdf||"+todaysDate+":00:00||000000001||00000002||||||";
		String AdtContent2 = NamingConv +".txt||"+todaysDate+" 02:00:00||000000001||00000002||||||";
		 
		//Create a audit folder
        File newDir = new File(dirpath.getAbsolutePath() + "/" +NamingConv);
        newDir.mkdir(); 
        log.logLine(Testname, false, "Audit inputdata creating of folder is done");
        
        //Create audit .txt file and add the content         
        PrintWriter writer = new PrintWriter(newDir+"/"+NamingConv+".txt", "UTF-8");
        log.logLine(Testname, false, "Audit txt file is created");
        writer.println(TxtContent);        
        writer.close();
        log.logLine(Testname, false, "Content has been added into Audit TXT file");
        
        //Create .pdf folder in audit folder
        File inputpdf = new File (dirpath.getAbsolutePath() + "/AutoTestData4.pdf");
		File actpdf = new File (newDir+"/"+NamingConv+".pdf");        
        if (inputpdf.exists()) {
        	FileUtils.copyFile(inputpdf, actpdf);
        	log.logLine(Testname, false, "Audit PDf file has been created");
        }
        
        //Create .adt file in audit folder
        PrintWriter writer2 = new PrintWriter(newDir+"/"+NamingConv+".adt", "UTF-8");
        log.logLine(Testname, false, "Audit adt file is created");
        writer2.println(AdtContent1);
        writer2.println(AdtContent2);
        writer2.close();
        log.logLine(Testname, false, "Content has been added into Audit ADT file");
		
     
        File directoryToZip = new File("C:/Pivot_Portal/Test Data/"+NamingConv);
        log.logLine(Testname, false, "Creating ZIP file before uploading..");

		List<File> fileList = new ArrayList<File>();		
		getAllFiles(directoryToZip, fileList);
		log.logLine(Testname, false, "Zip file creation is in-progress");
		writeZipFile(dirpath.getAbsolutePath(), directoryToZip, fileList);
		log.logLine(Testname, false, "Audit Zip file creation is successful");
     
		
		//Delete the folder and files in it
		InputOutputData.removeDirectory(directoryToZip);		
		
		//Upload the zip file to server through FTP		
		//String Retval = FTPFileUpload(Testname, directoryToZip, NamingConv);
		String Retval = SFTPFileUpload(Testname, directoryToZip, NamingConv);
	}	
	
	
	public String CreateArchives(String AccNo, String AccNo1, String Testname) throws Exception {
		log.logLine("", false, "Archive uploading from backend...");
		
		File dirpath = new File("C:/Pivot_Portal/Test Data/");
		
	    if ((!dirpath.isDirectory())  || (!dirpath.exists())){
	    	log.logLine("", true, "Archive test data folder is not exist @ given path");            
	        System.exit(0);
	    }        
	    
	    String envi = Initialization.EnvirSite.toUpperCase();
	    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");		
		Date date = new Date();
		String todaysDate = dateFormat.format(date);
		
		//String AppID = "RGT1099";
		String AppID = "ABC1234";
		
		String NamingConv = AppID+"_L_PCIDFS"+envi +"_107_"+todaysDate +"_"+AccNo+"_archive_v2_idx_01";
		String ArchContent1=NamingConv+".idx||"+todaysDate +" 12:02:04||10||102||||||";
		String ArchContent2=NamingConv+"_pdf_01.pdf||"+todaysDate +" 12:02:04||10||102||||||";
		String pdf="_pdf_01";
		String FinalNaming=NamingConv+pdf;
		String TxtContent1 = AppID+"||"+AccNo1+1+"||Baboon||Bat||Barb||Barn||Bear||Bee||Bird||Bongo||sd||9794-4268||"+todaysDate +"||"+FinalNaming+".pdf||048938000.pdf||000001||000002||manohar.x.basavaiah@rrd.com||||Y";
		String TxtContent2 = AppID+"||"+AccNo1+2+"||Camel||Cara||Cat||Chin||Chip||Chow||Cora||Crab||Cus||9794-4268||"+todaysDate +"||"+FinalNaming+".pdf||048938001.pdf||000002||000003||vinod.patil@rrd.com||||Y";
		String TxtContent3 = AppID+"||"+AccNo1+3+"||Deer||Dach||Dhol||Ding||Dodo||Duck||Donk||Dug||Dun||9794-4268||"+todaysDate +"||"+FinalNaming+".pdf||048938002.pdf||000003||000004||1ce29c44c0754049bf13f72adfab3a51@rrdfin.com||||Y";
		String TxtContent4 = AppID+"||"+AccNo1+4+"||Eagle||Emu||Ele||Earw||Edi||Ech||Empe||Eski||Ent||9794-4268||"+todaysDate +"||"+FinalNaming+".pdf||048938003.pdf||000004||000005||||||Y";
		String TxtContent5 = AppID+"||"+AccNo1+5+"||Fish||Fire||Fin||Flat||Fosa||Fly||Fox||Frog||Fur||9794-4268||"+todaysDate +"||"+FinalNaming+".pdf||048938004.pdf||000005||000006||||||Y";
		String TxtContent6 = AppID+"||"+AccNo1+6+"||Gar||Goat||Gira||Geck||Gibb||Gose||Grey||Gup||Guin||9794-4268||"+todaysDate +"||"+FinalNaming+".pdf||048938005.pdf||000006||000007||manohar.x.basavaiah@rrd.com||||Y";
		String TxtContent7 = AppID+"||"+AccNo1+7+"||Hen||Hore||Horn||Hors||Hone||Hyen||Him||Harr||High||9794-4268||"+todaysDate +"||"+FinalNaming+".pdf||048938006.pdf||000007||000008||vinod.patil@rrd.com||||Y";
		String TxtContent8 = AppID+"||"+AccNo1+8+"||Kak||Kang||Kiwi||King||Kudu||Komo||Kill||Koa||Keel||9794-4268||"+todaysDate +"||"+FinalNaming+".pdf||048938007.pdf||000008||000009||67668734807744979f2f01d552efc77d@rrdfin.com||||Y";
		String TxtContent9 = AppID+"||"+AccNo1+9+"||Maca||Mag||Main||Malt||Mana||Mars||May||Monk||Moo||9794-4268||"+todaysDate +"||"+FinalNaming+".pdf||048938008.pdf||000009||000010||||||Y";
		String TxtContent10 = AppID+"||"+AccNo1+"||Sea||Sand||Shih||Sheep||Seal||Sibe||Snail||Slow||South||9794-4268||"+todaysDate +"||"+FinalNaming+".pdf||048938009.pdf||000010||000011||||||Y";
		
		//Create a Archive folder
	    File newDir = new File(dirpath.getAbsolutePath() + "/" +NamingConv);
	    newDir.mkdir(); 
	    log.logLine("", false, "Archive inputdata creating of folder is done");
	    
	    
	  //Create .adt file in audit folder
	    PrintWriter writer= new PrintWriter(newDir+"/"+NamingConv+".adt", "UTF-8");
	    log.logLine("", false, "Archive adt file is created");
	    writer.println(ArchContent1);
	    writer.println(ArchContent2);
	    writer.close();
	    log.logLine("", false, "Content has been added into Archive ADT file");
	    
	    //Create audit .txt file and add the content         
	    PrintWriter writer1 = new PrintWriter(newDir+"/"+NamingConv+".idx", "UTF-8");
	    log.logLine("", false, "Archive idx file is created");
	    writer1.println(TxtContent1);
	    writer1.println(TxtContent2);
	    writer1.println(TxtContent3);
	    writer1.println(TxtContent4);
	    writer1.println(TxtContent5);
	    writer1.println(TxtContent6);
	    writer1.println(TxtContent7);
	    writer1.println(TxtContent8);
	    writer1.println(TxtContent9);
	    writer1.println(TxtContent10);
	    writer1.close();
	    log.logLine("", false, "Content has been added into Archive idx file");
	    
	  //Create .pdf folder in audit folder
	    File inputpdf = new File (dirpath.getAbsolutePath() + "/AutoTestData5.pdf");
		File actpdf = new File (newDir+"/"+NamingConv+"_pdf_01.pdf");        
	    if (inputpdf.exists()) {
	    	FileUtils.copyFile(inputpdf, actpdf);
	    	log.logLine("", false, "Archive PDf file has been created");
	    }
	    
	    File directoryToZip = new File("C:/Pivot_Portal/Test Data/"+NamingConv);
	    log.logLine("", false, "Creating ZIP file before uploading..");

		List<File> fileList = new ArrayList<File>();		
		getAllFiles(directoryToZip, fileList);
		log.logLine("", false, "Zip file creation is in-progress");
		writeZipFile(dirpath.getAbsolutePath(), directoryToZip, fileList);
		log.logLine("", false, "Archive Zip file creation is successful");
	 
		//Delete the folder and files in it
		String[]entries = directoryToZip.list();
		log.logLine("", false, "Delete Archive folder once the Zip file is created");
		for(String s: entries){
		    File currentFile = new File(directoryToZip.getPath(),s);
		    currentFile.delete();
		}
		if (directoryToZip.exists()){
			directoryToZip.delete();
			log.logLine("", false, "Delete Audit folder is successful");
	    }
		
		
		//Upload the zip file to server through FTP		
		//String Retval = FTPFileUpload(Testname, directoryToZip, NamingConv);
		String Retval = SFTPFileUpload(Testname, directoryToZip, NamingConv);
		
		return Retval;

	}
	
	
	public String CreateArchivesHTML(String AccNo, String Testname) throws Exception {
	
	    //startTime = System.currentTimeMillis();	
		
		log.logLine("", false, "Archive uploading from backend...");
			
			File dirpath = new File("C:/Pivot_Portal/Test Data/");
			
		    if ((!dirpath.isDirectory())  || (!dirpath.exists())){
		    	log.logLine("", true, "Archive test data folder is not exist @ given path");            
		        System.exit(0);
		    }        
		    
		    String envi = Initialization.EnvirSite.toUpperCase();
		    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");		
			Date date = new Date();
			String todaysDate = dateFormat.format(date);

			String NamingConv1 = "SKB0100_L_PCIDFS"+envi +"_107_"+todaysDate +"_"+AccNo+"_archive_v2_IDX";
			String NamingConv2 = "SKB0100_L_PCIDFS"+envi +"_107_"+todaysDate +"_"+AccNo+"_archive_v2_IDX_001";
			

			String ArchContent1=NamingConv1+"_nonpdf.zip||"+todaysDate +" 01:42:49||0||0||||||";
			String ArchContent2=NamingConv2+".idx||"+todaysDate +" 01:42:49||10||0||||||";
			
			int paperID2 = (int) Math.round(Math.random() * (99 - 10 + 1) + 10);
			consId = Integer.toString(paperID2);
			
			int paperOrd = (int) Math.round(Math.random() * (9999 - 1000 + 1) + 1000);
			order = Integer.toString(paperOrd);
			
			
			  String TxtContent1 = "SKB0100||sco"+consId+0+"||SKB-ab"+consId+"||SKB2015||"+order+"||skb-"+consId+0+"||stefaan01||||||||||||"+todaysDate +"||SKB0100_L_PCIDFS"+envi +"_107_"+todaysDate +"_"+AccNo+"_archive_v2_IDX_nonpdf.zip||SKB0100_L_PCIDFS"+envi +"_107_"+todaysDate +"_"+AccNo+"_archive_v2_IDX_001.html||1||1||anita.x.belgundi@rrd.com||Exp12345$||Y";
			  String TxtContent2 = "SKB0100||sco"+consId+1+"||SKB-cd"+consId+"||SKB2015||"+order+"||skb-"+consId+1+"||stefaan02||||||||||||"+todaysDate +"||SKB0100_L_PCIDFS"+envi +"_107_"+todaysDate +"_"+AccNo+"_archive_v2_IDX_nonpdf.zip||SKB0100_L_PCIDFS"+envi +"_107_"+todaysDate +"_"+AccNo+"_archive_v2_IDX_001.png||1||1||anita.x.belgundi@rrd.com||Exp12345$||Y";
			  String TxtContent3 = "SKB0100||sco"+consId+2+"||SKB-ef"+consId+"||SKB2015||"+order+"||skb-"+consId+2+"||stefaan03||||||||||||"+todaysDate +"||SKB0100_L_PCIDFS"+envi +"_107_"+todaysDate +"_"+AccNo+"_archive_v2_IDX_nonpdf.zip||SKB0100_L_PCIDFS"+envi +"_107_"+todaysDate +"_"+AccNo+"_archive_v2_IDX_001.jpg||1||1||anita.x.belgundi@rrd.com||Exp12345$||Y";
			  String TxtContent4 = "SKB0100||sco"+consId+3+"||SKB-gh"+consId+"||SKB2015||"+order+"||skb-"+consId+3+"||stefaan04||||||||||||"+todaysDate +"||SKB0100_L_PCIDFS"+envi +"_107_"+todaysDate +"_"+AccNo+"_archive_v2_IDX_nonpdf.zip||SKB0100_L_PCIDFS"+envi +"_107_"+todaysDate +"_"+AccNo+"_archive_v2_IDX_001.txt||1||1||anita.x.belgundi@rrd.com||Exp12345$||Y";
			  String TxtContent5 = "SKB0100||sco"+consId+4+"||SKB-ij"+consId+"||SKB2015||"+order+"||skb-"+consId+4+"||stefaan05||||||||||||"+todaysDate +"||SKB0100_L_PCIDFS"+envi +"_107_"+todaysDate +"_"+AccNo+"_archive_v2_IDX_nonpdf.zip||SKB0100_L_PCIDFS"+envi +"_107_"+todaysDate +"_"+AccNo+"_archive_v2_IDX_001.xlsx||1||1||anita.x.belgundi@rrd.com||Exp12345$||Y";
			  String TxtContent6 = "SKB0100||sco"+consId+5+"||SKB-kl"+consId+"||SKB2015||"+order+"||skb-"+consId+5+"||stefaan06||||||||||||"+todaysDate +"||SKB0100_L_PCIDFS"+envi +"_107_"+todaysDate +"_"+AccNo+"_archive_v2_IDX_nonpdf.zip||SKB0100_L_PCIDFS"+envi +"_107_"+todaysDate +"_"+AccNo+"_archive_v2_IDX_001.doc||1||1||anita.x.belgundi@rrd.com||Exp12345$||Y";
			  String TxtContent7 = "SKB0100||sco"+consId+6+"||SKB-mn"+consId+"||SKB2015||"+order+"||skb-"+consId+6+"||stefaan07||||||||||||"+todaysDate +"||SKB0100_L_PCIDFS"+envi +"_107_"+todaysDate +"_"+AccNo+"_archive_v2_IDX_nonpdf.zip||SKB0100_L_PCIDFS"+envi +"_107_"+todaysDate +"_"+AccNo+"_archive_v2_IDX_001.docx||1||1||anita.x.belgundi@rrd.com||Exp12345$||Y";
			  String TxtContent8 = "SKB0100||sco"+consId+7+"||SKB-op"+consId+"||SKB2015||"+order+"||skb-"+consId+7+"||stefaan08||||||||||||"+todaysDate +"||SKB0100_L_PCIDFS"+envi +"_107_"+todaysDate +"_"+AccNo+"_archive_v2_IDX_nonpdf.zip||SKB0100_L_PCIDFS"+envi +"_107_"+todaysDate +"_"+AccNo+"_archive_v2_IDX_001.xls||1||1||anita.x.belgundi@rrd.com||Exp12345$||Y";
			  String TxtContent9 = "SKB0100||sco"+consId+8+"||SKB-qr"+consId+"||SKB2015||"+order+"||skb-"+consId+8+"||stefaan08||||||||||||"+todaysDate +"||SKB0100_L_PCIDFS"+envi +"_107_"+todaysDate +"_"+AccNo+"_archive_v2_IDX_nonpdf.zip||SKB0100_L_PCIDFS"+envi +"_107_"+todaysDate +"_"+AccNo+"_archive_v2_IDX_001.pdf||1||1||anita.x.belgundi@rrd.com||Exp12345$||Y";
			  String TxtContent10 = "SKB0100||sco"+consId+9+"||SKB-st"+consId+"||SKB2015||"+order+"||skb-"+consId+9+"||stefaan08||||||||||||"+todaysDate +"||SKB0100_L_PCIDFS"+envi +"_107_"+todaysDate +"_"+AccNo+"_archive_v2_IDX_nonpdf.zip||SKB0100_L_PCIDFS"+envi +"_107_"+todaysDate +"_"+AccNo+"_archive_v2_IDX_001.csv||1||1||anita.x.belgundi@rrd.com||Exp12345$||Y";

			
			//Create a Archive folder
		    File newDir = new File(dirpath.getAbsolutePath() + "/" +NamingConv2);
		    newDir.mkdir(); 
		    log.logLine("", false, "Archive inputdata creating of folder is done");
		    
		    
		  //Create .adt file in audit folder
		    PrintWriter writer= new PrintWriter(newDir+"/"+NamingConv2+".adt", "UTF-8");
		    log.logLine("", false, "Archive adt file is created");
		    writer.println(ArchContent1);
		    writer.println(ArchContent2);
		    writer.close();
		    log.logLine("", false, "Content has been added into Archive ADT file");
		    
		    //Create audit .txt file and add the content         
		    PrintWriter writer1 = new PrintWriter(newDir+"/"+NamingConv2+".idx", "UTF-8");
		    log.logLine("", false, "Archive idx file is created");
		    writer1.println(TxtContent1);
		    writer1.println(TxtContent2);
		    writer1.println(TxtContent3);
		    writer1.println(TxtContent4);
		    writer1.println(TxtContent5);
		    writer1.println(TxtContent6);
		    writer1.println(TxtContent7);
		    writer1.println(TxtContent8);
		    writer1.println(TxtContent9);
		    writer1.println(TxtContent10);
		    writer1.close();
		    log.logLine("", false, "Content has been added into Archive idx file");
			
			//Create a Archive folder
		    File newDir1 = new File(newDir.getAbsolutePath() + "/" +NamingConv1+"_nonpdf");
		    newDir1.mkdir(); 
		    log.logLine("", false, "Archive inputdata creating of folder is done");
		    
		  //Create .xls file in nonpdf folder
		    File inputpdf = new File (dirpath.getAbsolutePath() + "/CreditCardApplication_1.html");
			File actpdf = new File (newDir1+"/"+NamingConv2+".html");        
		    if (inputpdf.exists()) {
		    	FileUtils.copyFile(inputpdf, actpdf);
		    	log.logLine("", false, "Archive PDf file has been created");
		    }
		    
		    //Create .docx file in nonpdf folder
		    File inputpdf1 = new File (dirpath.getAbsolutePath() + "/DocTestData.docx");
			File actpdf1 = new File (newDir1+"/"+NamingConv2+".docx");        
		    if (inputpdf1.exists()) {
		    	FileUtils.copyFile(inputpdf1, actpdf1);
		    	log.logLine("", false, "Archive PDf file has been created");
		    }
			
		    
		    //Create .xls file in nonpdf folder
		    File inputpdf2 = new File (dirpath.getAbsolutePath() + "/TestImage.jpg");
			File actpdf2 = new File (newDir1+"/"+NamingConv2+".jpg");        
		    if (inputpdf2.exists()) {
		    	FileUtils.copyFile(inputpdf2, actpdf2);
		    	log.logLine("", false, "Archive PDf file has been created");
		    }
		    
		    //Create .docx file in nonpdf folder
		    File inputpdf3 = new File (dirpath.getAbsolutePath() + "/SponsorAM.txt");
			File actpdf3 = new File (newDir1+"/"+NamingConv2+".txt");        
		    if (inputpdf3.exists()) {
		    	FileUtils.copyFile(inputpdf3, actpdf3);
		    	log.logLine("", false, "Archive PDf file has been created");
		    }
		    
		    
		    
		  //Create .docx file in nonpdf folder
		    File inputpdf4 = new File (dirpath.getAbsolutePath() + "/ClientAppDetails.xlsx");
			File actpdf4 = new File (newDir1+"/"+NamingConv2+".xlsx");        
		    if (inputpdf4.exists()) {
		    	FileUtils.copyFile(inputpdf4, actpdf4);
		    	log.logLine("", false, "Archive PDf file has been created");
		    }
		    
		    
		  //Create .docx file in nonpdf folder
		    File inputpdf5 = new File (dirpath.getAbsolutePath() + "/DocTestData.doc");
			File actpdf5 = new File (newDir1+"/"+NamingConv2+".doc");        
		    if (inputpdf5.exists()) {
		    	FileUtils.copyFile(inputpdf5, actpdf5);
		    	log.logLine("", false, "Archive PDf file has been created");
		    }
		    
		  //Create .docx file in nonpdf folder
		    File inputpdf6 = new File (dirpath.getAbsolutePath() + "/Fly.png");
			File actpdf6 = new File (newDir1+"/"+NamingConv2+".png");        
		    if (inputpdf6.exists()) {
		    	FileUtils.copyFile(inputpdf6, actpdf6);
		    	log.logLine("", false, "Archive PDf file has been created");
		    }
		    
		    
		  //Create .docx file in nonpdf folder
		    File inputpdf7 = new File (dirpath.getAbsolutePath() + "/ClientApplication.xls");
			File actpdf7 = new File (newDir1+"/"+NamingConv2+".xls");        
		    if (inputpdf7.exists()) {
		    	FileUtils.copyFile(inputpdf7, actpdf7);
		    	log.logLine("", false, "Archive PDf file has been created");
		    }
		    
		  //Create .docx file in nonpdf folder
		    File inputpdf8 = new File (dirpath.getAbsolutePath() + "/AutoPDF.pdf");
			File actpdf8 = new File (newDir1+"/"+NamingConv2+".pdf");        
		    if (inputpdf8.exists()) {
		    	FileUtils.copyFile(inputpdf8, actpdf8);
		    	log.logLine("", false, "Archive PDf file has been created");
		    }
		    
		    
		  //Create .docx file in nonpdf folder
		    File inputpdf9 = new File (dirpath.getAbsolutePath() + "/widgets.csv");
			File actpdf9 = new File (newDir1+"/"+NamingConv2+".csv");        
		    if (inputpdf9.exists()) {
		    	FileUtils.copyFile(inputpdf9, actpdf9);
		    	log.logLine("", false, "Archive PDf file has been created");
		    }
			
			File directoryToZip1 = new File("C:/Pivot_Portal/Test Data/"+NamingConv2+"/"+NamingConv1+"_nonpdf");
		    log.logLine("", false, "Creating pdf ZIP file before uploading..");
			
			List<File> fileList1 = new ArrayList<File>();		
			getAllFiles1(directoryToZip1, fileList1);
			log.logLine("", false, "Zip file creation is in-progress");
			writeZipFile1(newDir.getAbsolutePath(), directoryToZip1, fileList1);
			log.logLine("", false, "Archive Zip file creation is successful");
			
			
			//Delete the folder and files in it
			String[]entries1 = directoryToZip1.list();
			log.logLine("", false, "Delete Archive folder once the Zip file is created");
			for(String s: entries1){
			    File currentFile = new File(directoryToZip1.getPath(),s);
			    currentFile.delete();
			}
			if (directoryToZip1.exists()){
				directoryToZip1.delete();
				log.logLine("", false, "Delete Audit folder is successful");
		    }
			
		    
		    File directoryToZip = new File("C:/Pivot_Portal/Test Data/"+NamingConv2);
		    log.logLine("", false, "Creating ZIP file before uploading..");

			List<File> fileList = new ArrayList<File>();		
			getAllFiles(directoryToZip, fileList);
			log.logLine("", false, "Zip file creation is in-progress");
			writeZipFile(dirpath.getAbsolutePath(), directoryToZip, fileList);
			log.logLine("", false, "Archive Zip file creation is successful");
		 
			//Delete the folder and files in it
			String[]entries = directoryToZip.list();
			log.logLine("", false, "Delete Archive folder once the Zip file is created");
			for(String s: entries){
			    File currentFile = new File(directoryToZip.getPath(),s);
			    currentFile.delete();
			}
			if (directoryToZip.exists()){
				directoryToZip.delete();
				log.logLine("", false, "Delete Audit folder is successful");
		    }
			
			
			//Upload the zip file to server through FTP		
			//String Retval = FTPFileUpload(Testname, directoryToZip, NamingConv2);
			String Retval = SFTPFileUpload(Testname, directoryToZip, NamingConv2);
			return Retval;

	}
	
	
	public void CreatePublicDocs(String AccNo, String Testname) throws Exception {
		
	    //startTime = System.currentTimeMillis();	
		
		log.logLine("", false, "Public document uploading from backend...");
			
			File dirpath = new File("C:/Pivot_Portal/Test Data/");
			
	    if ((!dirpath.isDirectory())  || (!dirpath.exists())){
	    	log.logLine("", true, "Public document test data folder is not exist @ given path");            
	        System.exit(0);
	    }        
	    
	    String envi = Initialization.EnvirSite.toUpperCase();
	    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");		
	    DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String todaysDate = dateFormat.format(date);
		String todaysDate2 = dateFormat2.format(date);

		String AppID = "SKB0100";
		//String AppID = "rgt1099";
		
		String NamingConv = AppID+"_L_PCIDFS"+envi +"_111_"+todaysDate +"_"+AccNo+"_PublicDoc";
		
		PDocument1 = AppID+"_"+envi+"1"+todaysDate+"_"+AccNo+".pdf";
	    PDocument2 = AppID+"_"+envi+"2"+todaysDate+"_"+AccNo+".pdf";
	    PDocument3 = AppID+"_"+envi+"3"+todaysDate+"_"+AccNo+".pdf";
	    PDocument4 = AppID+"_"+envi+"4"+todaysDate+"_"+AccNo+".pdf";
	    PDocument5 = AppID+"_"+envi+"5"+todaysDate+"_"+AccNo+".pdf";
	    PDocument6 = AppID+"_"+envi+"6"+todaysDate+"_"+AccNo+".pdf";
	    PDocument7 = AppID+"_"+envi+"7"+todaysDate+"_"+AccNo+".pdf";
	    PDocument8 = AppID+"_"+envi+"8"+todaysDate+"_"+AccNo+".pdf";
	    PDocument9 = AppID+"_"+envi+"9"+todaysDate+"_"+AccNo+".pdf";
	    PDocument10 = AppID+"_"+envi+"10"+todaysDate+"_"+AccNo+".pdf";
	    

		String AdtCont1 = NamingConv + ".idx||"+todaysDate+" 00:00:00||10||1||||||";
		String AdtCont2 = PDocument1+"||"+todaysDate+" 00:00:00||1||7||||||";
		String AdtCont3 = PDocument2+"||"+todaysDate+" 00:00:00||1||7||||||";
		String AdtCont4 = PDocument3+"||"+todaysDate+" 00:00:00||1||7||||||";
		String AdtCont5 = PDocument4+"||"+todaysDate+" 00:00:00||1||7||||||";
		String AdtCont6 = PDocument5+"||"+todaysDate+" 00:00:00||1||7||||||";
		String AdtCont7 = PDocument6+"||"+todaysDate+" 00:00:00||1||7||||||";
		String AdtCont8 = PDocument7+"||"+todaysDate+" 00:00:00||1||7||||||";
		String AdtCont9 = PDocument8+"||"+todaysDate+" 00:00:00||1||7||||||";
		String AdtCont10 = PDocument9+"||"+todaysDate+" 00:00:00||1||7||||||";
		String AdtCont11 = PDocument10+"||"+todaysDate+" 00:00:00||1||7||||||";
		
		String idxCont1 = AppID+"~"+PDocument1+"~"+todaysDate2+"~Document 1 description~manohar.x.basavaiah@rrd.com~Automation Data 1~Y";
		String idxCont2 = AppID+"~"+PDocument2+"~"+todaysDate2+"~Document 2 description~manohar.x.basavaiah@rrd.com~Automation Data 2~Y";
		String idxCont3 = AppID+"~"+PDocument3+"~"+todaysDate2+"~Document 3 description~manohar.x.basavaiah@rrd.com~Automation Data 3~Y";
		String idxCont4 = AppID+"~"+PDocument4+"~"+todaysDate2+"~Document 4 description~manohar.x.basavaiah@rrd.com~Automation Data 4~Y";
		String idxCont5 = AppID+"~"+PDocument5+"~"+todaysDate2+"~Document 5 description~manohar.x.basavaiah@rrd.com~Automation Data 5~Y";
		String idxCont6 = AppID+"~"+PDocument6+"~"+todaysDate2+"~Document 6 description~manohar.x.basavaiah@rrd.com~Automation Data 6~Y";
		String idxCont7 = AppID+"~"+PDocument7+"~"+todaysDate2+"~Document 7 description~manohar.x.basavaiah@rrd.com~Automation Data 7~Y";
		String idxCont8 = AppID+"~"+PDocument8+"~"+todaysDate2+"~Document 8 description~manohar.x.basavaiah@rrd.com~Automation Data 7~Y";
		String idxCont9 = AppID+"~"+PDocument9+"~"+todaysDate2+"~Document 9 description~manohar.x.basavaiah@rrd.com~Automation Data 9~Y";
		String idxCont10 = AppID+"~"+PDocument10+"~"+todaysDate2+"~Document 10 description~manohar.x.basavaiah@rrd.com~Automation Data 10~Y";
		
		//Create a Archive folder
	    File newDir = new File(dirpath.getAbsolutePath() + "/" +NamingConv);
	    newDir.mkdir(); 
	    log.logLine("", false, "public document inputdata creating of folder is done");
	    
	    
	    //Create .adt file in audit folder
	    PrintWriter writer= new PrintWriter(newDir+"/"+NamingConv+".adt", "UTF-8");
	    log.logLine("", false, "public document adt file is created");
	    writer.println(AdtCont1);
	    writer.println(AdtCont2);
	    writer.println(AdtCont3);
	    writer.println(AdtCont4);
	    writer.println(AdtCont5);
	    writer.println(AdtCont6);
	    writer.println(AdtCont7);
	    writer.println(AdtCont8);
	    writer.println(AdtCont9);
	    writer.println(AdtCont10);
	    writer.println(AdtCont11);
	    
	    writer.close();
	    log.logLine("", false, "Content has been added into public document ADT file");
	    
	    //Create audit .txt file and add the content         
	    PrintWriter writer1 = new PrintWriter(newDir+"/"+NamingConv+".idx", "UTF-8");
	    log.logLine("", false, "Archive idx file is created");
	    writer1.println(idxCont1);
	    writer1.println(idxCont2);	    
	    writer1.println(idxCont3);
	    writer1.println(idxCont4);
	    writer1.println(idxCont5);
	    writer1.println(idxCont6);
	    writer1.println(idxCont7);
	    writer1.println(idxCont8);
	    writer1.println(idxCont9);
	    writer1.println(idxCont10);
	    
	    writer1.close();
	    log.logLine("", false, "Content has been added into public document idx file");
	    
	    //Create .pdf folder in audit folder  
	    
	    File inputpdf = new File (dirpath.getAbsolutePath() + "/AutoTestData5.pdf");
		File actpdf = new File (newDir+"/"+PDocument1);        
	    if (inputpdf.exists()) {
	    	FileUtils.copyFile(inputpdf, actpdf);
	    	log.logLine("", false, "Public document1 PDf file has been created");
	    }
	    
	    File inputpdf2 = new File (dirpath.getAbsolutePath() + "/AutoTestData4.pdf");
		File actpdf2 = new File (newDir+"/"+PDocument2);        
	    if (inputpdf2.exists()) {
	    	FileUtils.copyFile(inputpdf2, actpdf2);
	    	log.logLine("", false, "Public document2 PDf file has been created");
	    }
	    
	    File inputpdf3 = new File (dirpath.getAbsolutePath() + "/AutoTestData4.pdf");
		File actpdf3 = new File (newDir+"/"+PDocument3);        
	    if (inputpdf3.exists()) {
	    	FileUtils.copyFile(inputpdf3, actpdf3);
	    	log.logLine("", false, "Public document3 PDf file has been created");
	    }
	    
	    File inputpdf4 = new File (dirpath.getAbsolutePath() + "/AutoTestData4.pdf");
		File actpdf4 = new File (newDir+"/"+PDocument4);        
	    if (inputpdf4.exists()) {
	    	FileUtils.copyFile(inputpdf4, actpdf4);
	    	log.logLine("", false, "Public document4 PDf file has been created");
	    }
	    
	    File inputpdf5 = new File (dirpath.getAbsolutePath() + "/AutoTestData4.pdf");
		File actpdf5 = new File (newDir+"/"+PDocument5);        
	    if (inputpdf5.exists()) {
	    	FileUtils.copyFile(inputpdf5, actpdf5);
	    	log.logLine("", false, "Public document6 PDf file has been created");
	    }
	    
	    File inputpdf6 = new File (dirpath.getAbsolutePath() + "/AutoTestData4.pdf");
		File actpdf6 = new File (newDir+"/"+PDocument6);        
	    if (inputpdf6.exists()) {
	    	FileUtils.copyFile(inputpdf6, actpdf6);
	    	log.logLine("", false, "Public document6 PDf file has been created");
	    }
	    
	    File inputpdf7 = new File (dirpath.getAbsolutePath() + "/AutoTestData4.pdf");
		File actpdf7 = new File (newDir+"/"+PDocument7);        
	    if (inputpdf7.exists()) {
	    	FileUtils.copyFile(inputpdf7, actpdf7);
	    	log.logLine("", false, "Public document7 PDf file has been created");
	    }
	    
	    File inputpdf8 = new File (dirpath.getAbsolutePath() + "/AutoTestData4.pdf");
		File actpdf8 = new File (newDir+"/"+PDocument8);        
	    if (inputpdf8.exists()) {
	    	FileUtils.copyFile(inputpdf8, actpdf8);
	    	log.logLine("", false, "Public document8 PDf file has been created");
	    }
	    
	    File inputpdf9 = new File (dirpath.getAbsolutePath() + "/AutoTestData4.pdf");
		File actpdf9 = new File (newDir+"/"+PDocument9);        
	    if (inputpdf9.exists()) {
	    	FileUtils.copyFile(inputpdf9, actpdf9);
	    	log.logLine("", false, "Public document9 PDf file has been created");
	    }
	    
	    File inputpdf10 = new File (dirpath.getAbsolutePath() + "/AutoTestData4.pdf");
		File actpdf10 = new File (newDir+"/"+PDocument10);        
	    if (inputpdf10.exists()) {
	    	FileUtils.copyFile(inputpdf10, actpdf10);
	    	log.logLine("", false, "Public document10 PDf file has been created");
	    }
	    
	    File directoryToZip = new File("C:/Pivot_Portal/Test Data/"+NamingConv);
	    log.logLine("", false, "Creating ZIP file before uploading..");

		List<File> fileList = new ArrayList<File>();		
		getAllFiles(directoryToZip, fileList);
		log.logLine("", false, "Zip file creation is in-progress");
		writeZipFile(dirpath.getAbsolutePath(), directoryToZip, fileList);
		log.logLine("", false, "public document Zip file creation is successful");
	 
		//Delete the folder and files in it
		String[]entries = directoryToZip.list();
		log.logLine("", false, "Delete public document folder once the Zip file is created");
		for(String s: entries){
		    File currentFile = new File(directoryToZip.getPath(),s);
		    currentFile.delete();
		}
		if (directoryToZip.exists()){
			directoryToZip.delete();
			log.logLine("", false, "Delete public document folder is successful");
	    }
		
		
		//Upload the zip file to server through FTP		
		//String Retval = FTPFileUpload(Testname, directoryToZip, NamingConv);
		String Retval = SFTPFileUpload(Testname, directoryToZip, NamingConv);
		
		//return Retval;

	}	
	
	
	public void PasswordRegistration(String AccNo, String Testname, String AppID) throws Exception {
		
		String NamingConv="", TxtContent1="", TxtContent2="", AdtContent="";
		
		log.logLine(Testname, false, "Creating a user file and uploading via backend...");
		
		File dirpath = new File("C:/Pivot_Portal/Test Data/");
		
        if ((!dirpath.isDirectory())  || (!dirpath.exists())){
        	log.logLine(Testname, true, "Test data folder is not exist @ given path");            
            System.exit(0);
        }        
        
        String envi = Initialization.EnvirSite.toUpperCase();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");		
		Date date = new Date();
		String todaysDate = dateFormat.format(date);
		
		InputOutputData test = new InputOutputData();		
	    test.setInputFile("C:/Pivot_Portal/Test Data/RegistrationAndPasswordReset.xls");
	    
	    
	    String AppIDCode = "";
	    if (AppID.equals("")) {
	    	AppID = test.readColumnData("ApplicationName", "CheckRegistration");
	    }
		
		if (AppID.equalsIgnoreCase("SKB0100")) {
			if (envi.equalsIgnoreCase("Test")) 
					AppIDCode = "50626";
			else if (envi.equalsIgnoreCase("Stage"))
					AppIDCode = "50631";
			else if (envi.equalsIgnoreCase("Prod"))
				AppIDCode = "50588";
			
			
			ConsumerID1 = "skb4"+AccNo;
			ConsumerID2 = "skb2"+AccNo;
			NamingConv = "PVT9005_L_PCIDFS"+envi +"_104_"+todaysDate +"_"+AccNo+"_user_v1_"+AppIDCode;			
			TxtContent1 = AppID+"||||||"+ConsumerID1+"||Vinod||Patil||3333333||vinod.patil@rrd.com||||||||||||||||||||||";
			TxtContent2 = AppID+"||||||"+ConsumerID2+"||Srinivas||CS||2222222||srinivas.chamalapura@rrd.com||||||||||||||||||||||";
			AdtContent = NamingConv +".txt||"+todaysDate+" 10:00:00||2||0||||||";
						
		}else if (AppID.equalsIgnoreCase("GNE2000")) {
			if (envi.equalsIgnoreCase("Test")) 
					AppIDCode = "50453";
			else if (envi.equalsIgnoreCase("Stage"))
					AppIDCode = "50418";
			else if (envi.equalsIgnoreCase("Prod"))
					AppIDCode = "50340";
			
			
			ConsumerID3 = "gne1"+AccNo;
			ConsumerID4 = "gne2"+AccNo;
			User1="Manny"+AccNo;
			User2="Anny"+AccNo;
					
			NamingConv = "PVT9005_L_PCIDFS"+envi +"_104_"+todaysDate +"_"+AccNo+"_user_v1_"+AppIDCode;			
			TxtContent1 = AppID+"||"+User1+"||Spring*5||"+ConsumerID1+"||Manohar||Basavaiah||3333333||manohar.x.basavaiah@rrd.com||1||2||3||4||5||6||7||8||9||0||";
			TxtContent2 = AppID+"||"+User2+"||Spring*5||skb2"+ConsumerID2+"||Anita||Belgundi||2222222||anita.x.belgundi@rrd.com||1||2||3||4||5||6||7||8||9||0||";
			AdtContent = NamingConv +".txt||"+todaysDate+" 10:00:00||2||0||||||";
		}
		
		
		
		//Create a audit folder
        File newDir = new File(dirpath.getAbsolutePath() + "/" +NamingConv);
        newDir.mkdir(); 
        log.logLine(Testname, false, "User inputdata creating of folder is done");
        
        //Create User .txt file and add the content         
        PrintWriter writer = new PrintWriter(newDir+"/"+NamingConv+".txt", "UTF-8");
        log.logLine(Testname, false, "User txt file is created");
        writer.println(TxtContent1);
        writer.println(TxtContent2);
        writer.close();
        log.logLine(Testname, false, "Content has been added into User TXT file");
        
              
        //Create .adt file in User folder
        PrintWriter writer2 = new PrintWriter(newDir+"/"+NamingConv+".adt", "UTF-8");
        log.logLine(Testname, false, "User adt file is created");
        writer2.println(AdtContent);        
        writer2.close();
        log.logLine(Testname, false, "Content has been added into User ADT file");
		
     
        File directoryToZip = new File("C:/Pivot_Portal/Test Data/"+NamingConv);
        log.logLine(Testname, false, "Creating User ZIP file before uploading..");

		List<File> fileList = new ArrayList<File>();		
		getAllFiles(directoryToZip, fileList);
		log.logLine(Testname, false, "User Zip file creation is in-progress");
		writeZipFile(dirpath.getAbsolutePath(), directoryToZip, fileList);
		log.logLine(Testname, false, "User Zip file creation is successful");
     
		
		//Delete the folder and files in it
		InputOutputData.removeDirectory(directoryToZip);
		
		//Upload the zip file to server through FTP		
		//FTPFileUpload(Testname, directoryToZip, NamingConv);
		String Retval = SFTPFileUpload(Testname, directoryToZip, NamingConv);

	}
	
	
	
	public static void getAllFiles(File dir, List<File> fileList) throws InvalidFormatException {
		try {
			File[] files = dir.listFiles();
			for (File file : files) {
				fileList.add(file);
				if (file.isDirectory()) {
					System.out.println("directory:" + file.getCanonicalPath());					
					getAllFiles(file, fileList);
				} else {
					System.out.println("directory:" + file.getCanonicalPath());					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void getAllFiles1(File dir, List<File> fileList1) throws InvalidFormatException {
		try {
			File[] files = dir.listFiles();
			for (File file : files) {
				fileList1.add(file);
				if (file.isDirectory()) {
					System.out.println("directory:" + file.getCanonicalPath());					
					getAllFiles1(file, fileList1);
				} else {
					System.out.println("directory:" + file.getCanonicalPath());					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public static void writeZipFile(String Folder, File directoryToZip, List<File> fileList) throws InvalidFormatException {

		try {
			FileOutputStream fos = new FileOutputStream(Folder+"/"+directoryToZip.getName() + ".zip");
			ZipOutputStream zos = new ZipOutputStream(fos);

			for (File file : fileList) {
				if (!file.isDirectory()) { // we only zip files, not directories
					//addToZip(directoryToZip, file, zos);
					FileInputStream fis = new FileInputStream(file);

					// we want the zipEntry's path to be a relative path that is relative
					// to the directory being zipped, so chop off the rest of the path
					String zipFilePath = file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1,
							file.getCanonicalPath().length());
					//log.logLine("", false, "Writing '" + zipFilePath + "' to zip file");		
					ZipEntry zipEntry = new ZipEntry(zipFilePath);
					zos.putNextEntry(zipEntry);

					byte[] bytes = new byte[1024];
					int length;
					while ((length = fis.read(bytes)) >= 0) {
						zos.write(bytes, 0, length);
					}

					zos.closeEntry();
					fis.close();
				}
			}

			zos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public static void writeZipFile1(String Folder, File directoryToZip1, List<File> fileList1) throws InvalidFormatException {

		try {
			FileOutputStream fos = new FileOutputStream(Folder+"/"+directoryToZip1.getName() + ".zip");
			ZipOutputStream zos = new ZipOutputStream(fos);

			for (File file : fileList1) {
				if (!file.isDirectory()) { // we only zip files, not directories
					//addToZip(directoryToZip, file, zos);
					FileInputStream fis = new FileInputStream(file);

					// we want the zipEntry's path to be a relative path that is relative
					// to the directory being zipped, so chop off the rest of the path
					String zipFilePath = file.getCanonicalPath().substring(directoryToZip1.getCanonicalPath().length() + 1,
							file.getCanonicalPath().length());
					//log.logLine("", false, "Writing '" + zipFilePath + "' to zip file");		
					ZipEntry zipEntry = new ZipEntry(zipFilePath);
					zos.putNextEntry(zipEntry);

					byte[] bytes = new byte[1024];
					int length;
					while ((length = fis.read(bytes)) >= 0) {
						zos.write(bytes, 0, length);
					}

					zos.closeEntry();
					fis.close();
				}
			}

			zos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	
	public String FTPFileUpload(String Testname, File Directory, String NamingConv) throws Exception {
		
		log.logLine(Testname, false, "Connecting to RRD FTP server for file uploading");
		
		String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
		String host = "chicmbbs1.rrd.com";
		String user = "bcswbha1";
		String pass = "bc1011a1";
		
		
		// Code changes to test SFTP Uploads
		
		
		String filePath = Directory+".zip";
		// uploadPath is the file name of the file.
		String uploadPath = NamingConv+".zip";

		ftpUrl = String.format(ftpUrl, user, pass, host, uploadPath);
		log.logLine(Testname, false, "Upload URL: " + ftpUrl);		
		
		try {
			URL url = new URL(ftpUrl);
			URLConnection conn = url.openConnection();
			OutputStream outputStream = conn.getOutputStream();
			FileInputStream inputStream = new FileInputStream(filePath);
			
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			
			inputStream.close();
			outputStream.close();
			
			log.logLine(Testname, false, "Zip file upload is successful -"+uploadPath+".zip");
		} catch (IOException ex) {
			log.logLine(Testname, true, "Zip file upload is unsuccessful -"+uploadPath+".zip");
			ex.printStackTrace();		
		}
		
		return uploadPath;
		
	}
	
	public String SFTPFileUpload(String Testname, File Directory, String NamingConv) throws Exception {
		

		String SFTPHOST = "sftp2.rrd.int";
		int    SFTPPORT = 22;
		String SFTPUSER = "PCIDFS_BCS007";
		String SFTPPASS = "1s2b9GDo";
		String SFTPWORKINGDIR = "/Nonduplicate";
		 
		Session     session     = null;
		Channel     channel     = null;
		ChannelSftp channelSftp = null;
		String filePath = Directory+".zip";
		// uploadPath is the file name of the file.
		String uploadPath = NamingConv+".zip";
		
		try{
		            JSch jsch = new JSch();
		            session = jsch.getSession(SFTPUSER,SFTPHOST,SFTPPORT);
		            session.setPassword(SFTPPASS);
		            java.util.Properties config = new java.util.Properties();
		            config.put("StrictHostKeyChecking", "no");
		            session.setConfig(config);
		            session.connect();
		            channel = session.openChannel("sftp");
		            channel.connect();
		            channelSftp = (ChannelSftp)channel;
		            channelSftp.cd(SFTPWORKINGDIR);
		            File f = new File(filePath);
		            channelSftp.put(new FileInputStream(f), f.getName());
		}catch(Exception ex){
		ex.printStackTrace();
		}
		 
		return uploadPath;
		}
	
}


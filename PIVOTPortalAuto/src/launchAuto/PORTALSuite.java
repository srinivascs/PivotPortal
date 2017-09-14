package launchAuto;

import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import testCases.TestS_001;
import testCases.TestS_002;
import testCases.TestS_003;
import testCases.TestS_004;
import testCases.TestS_005;
import testCases.TestS_006;
import testCases.TestS_007;
import testCases.TestS_008;
import testCases.TestS_009;
import testCases.TestS_010;
import testCases.TestS_011;
import testCases.TestS_013;
import testCases.TestS_014;
import testCases.TestS_015;
import testCases.TestS_016;
import testCases.TestS_018;
import testCases.TestS_019;
import testCases.TestS_020;
import testCases.TestS_012;
import testCases.TestS_021;
import testCases.TestS_022;
import testCases.TestS_023;
import testCases.TestS_024;
import testCases.TestS_025;
import testCases.TestS_026;
import testCases.TestS_027;
import testCases.TestS_028;
import testCases.TestS_029;
import testCases.TestS_030;
import testCases.TestS_031;
import testCases.TestS_032;
import testCases.TestS_033;
import testCases.TestS_034;
import testCases.TestS_035;
import testCases.TestS_036;
import testCases.TestS_037;
import testCases.TestS_038;
import testCases.TestS_039;
import testCases.TestS_017;
import testCases.TestS_040;
import testCases.TestS_041;
import testCases.TestS_042;
import testCases.TestS_043;
import testCases.TestS_044;
import testCases.TestS_045;
import testCases.TestS_046;
import testCases.TestS_047;
import testCases.TestS_048;
import testCases.TestS_049;
import testCases.TestS_050;
import testCases.TestS_051;
import testCases.TestS_052;
import testCases.TestS_053;
import testCases.TestS_054;
import testCases.TestS_055;
import testCases.TestS_056;
import testCases.TestS_057;
import testCases.TestS_061;
import testCases.TestS_058;
import testCases.TestS_059;
import testCases.TestS_060;
import testCases.TestS_062;
import testCases.TestS_063;
import testCases.TestS_064;
import testCases.TestS_065;
import testCases.TestS_066;
import testCases.TestS_067;
import testCases.TestS_068;
import testCases.TestS_069;
import testCases.TestS_070;
import testCases.TestS_071;
import testCases.TestS_072;
import testCases.TestS_073;
import testCases.TestS_074;
import testCases.TestS_075;
import testCases.TestS_076;
import testCases.TestS_077;
import testCases.TestS_080;
import testCases.TestS_081;
import testCases.TestS_082;


public class PORTALSuite extends Initialization {


	@Test(enabled=true)
	public void Test01_Verify_Uploading_A_ValidFile_inReportViewer() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test01 - User opted to skip this test ");
		} else {		
			TestS_001 clasInst = new TestS_001(driver, log);
			Boolean retVal = clasInst.test1();		
			if (retVal) {
				Reporter.log("Report Viewer - Uploading a report file is successful");		
			} else {
				Reporter.log("Report Viewer - Uploading a report file is unsuccessful");	
			}
		}
	}

	@Test(enabled=true)
	public void Test02_Verify_Uploading_Multiple_ValidFiles_inReportViewer() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test02 - User opted to skip this test ");
		} else {		
			TestS_002 clasInst = new TestS_002(driver, log);
			Boolean retVal = clasInst.test2();		
			if (retVal) {
				Reporter.log("Report Viewer - Uploading multiple report files is successful");			
			} else {
				Reporter.log("Report Viewer - Uploading multiple report files is unsuccessful");			
			}
		}
	}

	@Test(enabled=true)	 
	public void Test03_Verify_ReportViewer_Functionality_basedon_Various_SearchMethods() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test03 - User opted to skip this test ");
		} else {		
			TestS_003 clasInst = new TestS_003(driver, log);
			Boolean retVal = clasInst.test3();		
			if (retVal) {
				Reporter.log("Verification of Report Viewer functionality based on various search methods is successful");			
			} else {
				Reporter.log("Verification of Report Viewer functionality based on various search methods is unsuccessful");			
			}
		}
	}

	@Test(enabled=true)
	public void Test04_Verify_NegativeCases_of_AdvancedSearch_Functionality_inReportViewer() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test04 - User opted to skip this test ");
		} else {		
			TestS_004 clasInst = new TestS_004(driver, log);
			Boolean retVal = clasInst.test4();		
			if (retVal) {
				Reporter.log("Verifying the negative cases of advanced search funationality in report Viewer is successful");		
			} else {
				Reporter.log("Verifying the negative cases of advanced search funationality in report Viewer is unsuccessful");	
			}
		}
	}	

	@Test(enabled=true)
	public void Test05_Verify_Uploading_IllegalFile_And_Upload_information_validation_inReportViewer() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test05 - User opted to skip this test ");
		} else {		
			TestS_005 clasInst = new TestS_005(driver, log);
			Boolean retVal = clasInst.test5();		
			if (retVal) {
				Reporter.log("Report Viewer - Verification of uploading a illegal file and upload information validation is successful");			
			} else {
				Reporter.log("Report Viewer - Verification of uploading a illegal file and upload information validation is unsuccessful");			
			}
		}
	}

	@Test(enabled=true)
	public void Test06_Verify_Uploading_A_ValidFile_inProofing() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test06 - User opted to skip this test ");
		} else {		
			TestS_006 clasInst = new TestS_006(driver, log);
			Boolean retVal = clasInst.test6();		
			if (retVal) {
				Reporter.log("Proofing - Uploading a pdf proof file is successful");		
			} else {
				Reporter.log("Proofing - Uploading a pdf proof file is unsuccessful");	
			}
		}
	}

	@Test(enabled=true)
	public void Test07_Verify_Uploading_Multiple_ValidFiles_inProofing() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test07 - User opted to skip this test ");
		} else {		
			TestS_007 clasInst = new TestS_007(driver, log);
			Boolean retVal = clasInst.test7();		
			if (retVal) {
				Reporter.log("Proofing - Uploading multiple pdf proof files is successful");			
			} else {
				Reporter.log("Proofing - Uploading multiple pdf proof files is unsuccessful");			
			}
		}
	}

	@Test(enabled=true)
	public void Test08_Verify_Uploading_IllegalFile_And_Upload_information_validation_inProofing() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test08 - User opted to skip this test ");
		} else {		
			TestS_008 clasInst = new TestS_008(driver, log);
			Boolean retVal = clasInst.test8();		
			if (retVal) {
				Reporter.log("Proofing - Verification of uploading a illegal file and upload information validation is successful");			
			} else {
				Reporter.log("Proofing - Verification of uploading a illegal file and upload information validation is unsuccessful");			
			}
		}
	}

	@Test(enabled=true)	 
	public void Test09_Verify_ViewProofs_Functionality_basedon_Various_SearchMethods() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test09 - User opted to skip this test ");
		} else {		
			TestS_009 clasInst = new TestS_009(driver, log);
			Boolean retVal = clasInst.test9();		
			if (retVal) {
				Reporter.log("Verification of View Proofs functionality based on various search methods is successful");			
			} else {
				Reporter.log("Verification of View Proofs functionality based on various search methods is unsuccessful");			
			}
		}
	}

	@Test(enabled=true)	 
	public void Test10_Verify_ProofEdit_ProofDelete_NegativeCases_of_AdvancedSearch_Functionality() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test10 - User opted to skip this test ");
		} else {		
			TestS_010 clasInst = new TestS_010(driver, log);
			Boolean retVal = clasInst.test10();		
			if (retVal) {
				Reporter.log("Verifying the proofEditing, proofDeleting and negative cases of advanced search funationality is successful");			
			} else {
				Reporter.log("Verifying the proofEditing, proofDeleting and negative cases of advanced search funationality is unsuccessful");			
			}
		}
	}


	@Test(enabled=true)	 
	public void Test11_Verify_Change_in_proofStatus_to_Approve_and_Reject_with_commentsOrupload() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test11 - User opted to skip this test ");
		} else {		
			TestS_011 clasInst = new TestS_011(driver, log);
			Boolean retVal = clasInst.test11();		
			if (retVal) {
				Reporter.log("Verifying the Change in proofs status to approve and reject with comments/upload is successful");			
			} else {
				Reporter.log("Verifying the Change in proofs status to approve and reject with comments/upload is unsuccessful");			
			}
		}
	}

	@Test(enabled=true)
	public void Test12_Verify_thebulkActions_and_proofEnhancements() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test12 - User opted to skip this test ");
		} else {
			TestS_012 clasInst = new TestS_012(driver, log);
			Boolean retVal = clasInst.test12();
			if (retVal) {
				Reporter.log("Verification of bulk actions and 3.5 enhacements of proof is successful");
			} else {
				Reporter.log("Verification of bulk actions and 3.5 enhacements of proof is unsuccessful");
			}
		}
	}


	@Test(enabled=true)	 
	public void Test13_Verify_TheAudit_Search_based_on_various_Search_methods() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test13 - User opted to skip this test ");
		} else {		
			TestS_013 clasInst = new TestS_013(driver, log);
			Boolean retVal = clasInst.test13();		
			if (retVal) {
				Reporter.log("Verify the audit search based on various search methods is successful");			
			} else {
				Reporter.log("Verify the audit search based on various search methods is unsuccessful");			
			}
		}
	}


	@Test(enabled=true)	 
	public void Test14_Verify_TheNegativeCases_of_AdvancedSearch_in_auditPage() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test14 - User opted to skip this test ");
		} else {		
			TestS_014 clasInst = new TestS_014(driver, log);
			Boolean retVal = clasInst.test14();		
			if (retVal) {
				Reporter.log("Verify the negative cases of advanced search in Audits is successful");			
			} else {
				Reporter.log("Verify the negative cases of advanced search in Audits is unsuccessful");			
			}
		}
	}


	@Test(enabled=true)	 
	public void Test15_Verify_view_audits_withComments_and_Validating_theSame() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test15 - User opted to skip this test ");
		} else {		
			TestS_015 clasInst = new TestS_015(driver, log);
			Boolean retVal = clasInst.test15();		
			if (retVal) {
				Reporter.log("Verify the audit viewing with comments and validating the same is successful");			
			} else {
				Reporter.log("Verify the audit viewing with comments and validating the same is unsuccessful");			
			}
		}
	}


	@Test(enabled=true)	 
	public void Test16_Verify_theChange_in_status_of_audits() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test16 - User opted to skip this test ");
		} else {		
			TestS_016 clasInst = new TestS_016(driver, log);
			Boolean retVal = clasInst.test16();		
			if (retVal) {
				Reporter.log("Verify the change in status of the audits is successful");			
			} else {
				Reporter.log("Verify the change in status of the audits is unsuccessful");			
			}
		}
	}


	@Test(enabled=true)	 
	public void Test17_Verify_the_Audit_Keys_Segments_And_AuditRules_Functionality() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test17 - User opted to skip this test ");
		} else {		
			TestS_017 clasInst = new TestS_017(driver, log);
			Boolean retVal = clasInst.test17();		
			if (retVal) {
				Reporter.log("Verifying Audit Keys, Sub-Segments And AuditRules functionality is successful");			
			} else {
				Reporter.log("Verifying Audit Keys, Sub-Segments And AuditRules functionality is unsuccessful");			
			}
		}
	}


	@Test(enabled=true)	 
	public void Test18_Verify_theSimpleSearch_in_Archives() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test18 - User opted to skip this test ");
		} else {		
			TestS_018 clasInst = new TestS_018(driver, log);
			Boolean retVal = clasInst.test18();		
			if (retVal) {
				Reporter.log("Verification of simple search in archievs page is successful");			
			} else {
				Reporter.log("Verification of simple search in archievs page is unsuccessful");			
			}
		}
	}	

	@Test(enabled=true)	 
	public void Test19_Verify_theAdvancedSearch_in_Archives() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test19 - User opted to skip this test ");
		} else {		
			TestS_019 clasInst = new TestS_019(driver, log);
			Boolean retVal = clasInst.test19();		
			if (retVal) {
				Reporter.log("Verification of advanced search functionality in archives page is successful");			
			} else {
				Reporter.log("Verification of advanced search functionality in archives page is unsuccessful");			
			}
		}
	}

	@Test(enabled=true)
	public void Test20_Verify_theConcatenationBeta_CombinedDocuments_inArchives() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test20 - User opted to skip this test ");
		} else {
			TestS_020 clasInst = new TestS_020(driver, log);
			Boolean retVal = clasInst.test20();
			if (retVal) {
				Reporter.log("Verification of ConcatenationBeta/Combined documents in archives page is successful");
			} else {
				Reporter.log("Verification of ConcatenationBeta/Combined documents in archives page is unsuccessful");
			}
		}
	}	


	@Test(enabled=true)
	public void Test21_Verify_FileManagementbeta_inArchives() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test21 - User opted to skip this test ");
		} else {
			TestS_021 clasInst = new TestS_021(driver, log);
			Boolean retVal = clasInst.test21();
			if (retVal) {
				Reporter.log("Verify The Archive Filemanagement beta is Successfull");
			} else {
				Reporter.log("Verify The Archive Filemanagement beta is unSuccessfull");
			}
		}
	}

	@Test(enabled=true)
	public void Test22_Verify_MessageCenter_inArchives() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test22 - User opted to skip this test ");
		} else {
			TestS_022 clasInst = new TestS_022(driver, log);
			Boolean retVal = clasInst.test22();
			if (retVal) {
				Reporter.log("Verify The Message Center in Archives is Successfull");
			} else {
				Reporter.log("Verify The Message Center in Archives is unSuccessfull");
			}
		}
	}

	@Test(enabled=true)	 
	public void Test23_Verify_ManageUserConsent_inArchives() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test23 - User opted to skip this test ");
		} else {		
			TestS_023 clasInst = new TestS_023(driver, log);
			Boolean retVal = clasInst.test23();		
			if (retVal) {
				Reporter.log("Verify The Archive's Manage user consent is Successfull");			
			} else {
				Reporter.log("Verify The Archive's Manage user consent is unSuccessfull");			
			}
		}
	}


	@Test(enabled=true)	 
	public void Test24_Verify_EmailSettingsBeta_forArchives() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test24 - User opted to skip this test ");
		} else {		
			TestS_024 clasInst = new TestS_024(driver, log);
			Boolean retVal = clasInst.test24();		
			if (retVal) {
				Reporter.log("Verify The Archive's Email settings beta is Successfull");			
			} else {
				Reporter.log("Verify The Archive's Email settings beta is unSuccessfull");			
			}
		}
	}

	@Test(enabled=true)	 
	public void Test25_Verify_ReprintSettings_inArchives() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test25 - User opted to skip this test ");
		} else {		
			TestS_025 clasInst = new TestS_025(driver, log);
			Boolean retVal = clasInst.test25();		
			if (retVal) {
				Reporter.log("Verify The Archive's Reprint settings beta is Successfull");			
			} else {
				Reporter.log("Verify The Archive's Reprint settings beta is unSuccessfull");			
			}
		}
	}

	@Test(enabled=true)
	public void Test26_Verify_theVarious_SearchDocuments_methods_ineDeliveryApplication() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test26 - User opted to skip this test ");
		} else {
			TestS_026 clasInst = new TestS_026(driver, log);
			Boolean retVal = clasInst.test26();
			if (retVal) {
				Reporter.log("Verification of document search with various methods in eDelivery application is successful");
			} else {
				Reporter.log("Verification of document search with various methods in eDelivery application is unsuccessful");
			}
		}
	}

	@Test(enabled=true)
	public void Test27_Verify_InfoShare_Tracking_Under_Reports() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test27 - User opted to skip this test ");
		} else {
			TestS_027 clasInst = new TestS_027(driver, log);
			Boolean retVal = clasInst.test27();
			if (retVal) {
				Reporter.log("Verification of Infoshare Tracking report Under ReportWriter is successfull");
			} else {
				Reporter.log("Verification of Infoshare Tracking report Under ReportWriter is unsuccessful");
			}
		}
	}

	@Test(enabled=true)
	public void Test28_Verify_Postal_Frieght_Application_Under_Reports() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test28 - User opted to skip this test ");
		} else {
			TestS_028 clasInst = new TestS_028(driver, log);
			Boolean retVal = clasInst.test28();
			if (retVal) {
				Reporter.log("Verification of Postal Frieght Application Under ReportWriter is successfull");
			} else {
				Reporter.log("Verification of Postal Frieght Application Under ReportWriter is unsuccessful");
			}
		}
	}

	@Test(enabled=true)
	public void Test29_Verify_Client_User_Access_Services_Under_Reports() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test29 - User opted to skip this test ");
		} else {
			TestS_029 clasInst = new TestS_029(driver, log);
			Boolean retVal = clasInst.test29();
			if (retVal) {
				Reporter.log("Verification of Client User Access Services Under ReportWriter is successfull");
			} else {
				Reporter.log("Verification of Client User Access Services Under ReportWriter is unsuccessful");
			}
		}
	}


	@Test(enabled=true)
	public void Test30_Verify_Production_By_Order_Summary_Under_Reports() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test30 - User opted to skip this test ");
		} else {
			TestS_030 clasInst = new TestS_030(driver, log);
			Boolean retVal = clasInst.test30();
			if (retVal) {
				Reporter.log("Verification of Production By Order Summary Under ReportWriter is successfull");
			} else {
				Reporter.log("Verification of Production By Order Summary Under ReportWriter is unsuccessful");
			}
		}
	}


	@Test(enabled=true)
	public void Test31_Verify_Postage_Freight_Plant_Under_Reports() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test31 - User opted to skip this test ");
		} else {
			TestS_031 clasInst = new TestS_031(driver, log);
			Boolean retVal = clasInst.test31();
			if (retVal) {
				Reporter.log("Verification of Postage Freight Plant Under ReportWriter is successfull");
			} else {
				Reporter.log("Verification of Postage Freight Plant Under ReportWriter is unsuccessful");
			}
		}
	}


	@Test(enabled=true)
	public void Test32_Verification_ofthe_ProductionbyOrderDetail_Report_ofReportWriter() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test32 - User opted to skip this test ");
		} else {
			TestS_032 clasInst = new TestS_032(driver, log);
			Boolean retVal = clasInst.test32();
			if (retVal) {
				Reporter.log("Verification of the Production By Order detail report under ReportWriter is passed");
			} else {
				Reporter.log("Verification of the Production By Order detail report under ReportWriter is failed");
			}
		}
	}

	@Test(enabled=true)
	public void Test33_Verification_ofthe_InsertingOrder_Report_ofReportWriter() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test33 - User opted to skip this test ");
		} else {
			TestS_033 clasInst = new TestS_033(driver, log);
			Boolean retVal = clasInst.test33();
			if (retVal) {
				Reporter.log("Verification of the Inserting By Order  report under ReportWriter is passed");
			} else {
				Reporter.log("Verification of the Inserting By Order  report under ReportWriter is failed");
			}
		}
	}


	@Test(enabled=true)
	public void Test34_Verification_ofthe_DPR_Report_ofReportWriter() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test34 - User opted to skip this test ");
		} else {
			TestS_034 clasInst = new TestS_034(driver, log);
			Boolean retVal = clasInst.test34();
			if (retVal) {
				Reporter.log("Verification of the DPR report under ReportWriter is passed");
			} else {
				Reporter.log("Verification of the DPR report under ReportWriter is failed");
			}
		}
	}

	@Test(enabled=true)
	public void Test35_Verification_Recon_Email_Update_Report() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test35 - User opted to skip this test ");
		} else {
			TestS_035 clasInst = new TestS_035(driver, log);
			Boolean retVal = clasInst.test35();
			if (retVal) {
				Reporter.log("Verification of Recon Email Update(Activity Report) Under ReportWriter is successfull");
			} else {
				Reporter.log("Verification of Recon Email Update(Activity Report) Under ReportWriter is failed");
			}
		}
	}


	@Test(enabled=true)
	public void Test36_Verification_ofthe_My_Report_Section_ofReportWriter() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test36 - User opted to skip this test ");
		} else {
			TestS_036 clasInst = new TestS_036(driver, log);
			Boolean retVal = clasInst.test36();
			if (retVal) {
				Reporter.log("Verification of the run , edit , copy and delete of my report section under ReportWriter is passed");
			} else {
				Reporter.log("Verification of the run , edit , copy and delete of my report section under ReportWriter is failed");
			}
		}
	}


	@Test(enabled=true)
	public void Test37_Proof_Of_Review_Of_ReportWriter() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test37 - User opted to skip this test ");
		} else {
			TestS_037 clasInst = new TestS_037(driver, log);
			Boolean retVal = clasInst.test37();
			if (retVal) {
				Reporter.log("Verification of Proof Of Review for different Report Types is Successfull");
			} else {
				Reporter.log("Verification of Proof Of Review for different Report Types is UnSuccessfull");
			}
		}
	}

	@Test(enabled=true)
	public void Test38_Verification_of_various_search_operation_proof_testing() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test38 - User opted to skip this test ");
		} else {
			TestS_038 clasInst = new TestS_038(driver, log);
			Boolean retVal = clasInst.test38();
			if (retVal) {
				Reporter.log("Verification of the various operation(upload, reject, and approve) of proof is passed");
			} else {
				Reporter.log("Verification of the various operation(upload, reject, and approve) of proof is failed");
			}
		}
	}


	@Test(enabled=true)
	public void Test39_Verification_of_EnvelopePackage_Search_in_JobTracking() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test39 - User opted to skip this test ");
		} else {
			TestS_039 clasInst = new TestS_039(driver, log);
			Boolean retVal = clasInst.test39();
			if (retVal) {
				Reporter.log("Verification of the Envelope/package search in Job tracking module is passed");
			} else {
				Reporter.log("Verification of the Envelope/package search in Job tracking module is failed");
			}
		}
	}	




	@Test(enabled=true)
	public void Test40_Verify_the_Quick_Search_Functionality_Of_JobTracking() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test40 - User opted to skip this test ");
		} else {
			TestS_040 clasInst = new TestS_040(driver, log);
			Boolean retVal = clasInst.test40();
			if (retVal) {
				Reporter.log("Verifying Quick Search functionality of Job tracking is successful");
			} else {
				Reporter.log("Verifying Quick Search functionality of Job tracking is unsuccessful");
			}
		}
	}


	@Test(enabled=true)
	public void Test41_Verify_the_Saved_Search_Functionality_Of_JobTracking() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test41 - User opted to skip this test ");
		} else {
			TestS_041 clasInst = new TestS_041(driver, log);
			Boolean retVal = clasInst.test41();
			if (retVal) {
				Reporter.log("Verifying Saved Search functionality of Job tracking is successful");
			} else {
				Reporter.log("Verifying Saved Search functionality of Job tracking is unsuccessful");
			}
		}
	}


	@Test(enabled=true)	 
	public void Test42_Verify_the_Order_Search_Functionality_Of_JobTracking() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test42 - User opted to skip this test ");
		} else {		
			TestS_042 clasInst = new TestS_042(driver, log);
			Boolean retVal = clasInst.test42();		
			if (retVal) {
				Reporter.log("Verifying Order Search functionality of Job tracking is successful");	


			} else {
				Reporter.log("Verifying Order Search functionality of Job tracking is unsuccessful");			
			}
		}
	}


	@Test(enabled=true)	 
	public void Test43_Verify_the_Recent_Activity_Functionality_Of_JobTracking() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test43 - User opted to skip this test ");
		} else {		
			TestS_043 clasInst = new TestS_043(driver, log);
			Boolean retVal = clasInst.test43();		
			if (retVal) {
				Reporter.log("Verifying Recent Activity functionality of Job tracking is successful");			
			} else {
				Reporter.log("Verifying Recent Activity  functionality of Job tracking is unsuccessful");			
			}
		}
	}

	@Test(enabled=true)	 
	public void Test44_Verify_the_Pulls_Management() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test44 - User opted to skip this test ");
		} else {		
			TestS_044 clasInst = new TestS_044(driver, log);
			Boolean retVal = clasInst.test44();		
			if (retVal) {
				Reporter.log("Verification of the Pulls functionality is successful");			
			} else {
				Reporter.log("Verification of the Pulls functionality is unsuccessful");			
			}
		}
	}


	@Test(enabled=true)	 
	public void Test45_Verify_3_7_Public_Documents() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test45 - User opted to skip this test ");
		} else {		
			TestS_045 clasInst = new TestS_045(driver, log);
			Boolean retVal = clasInst.test45();		
			if (retVal) {
				Reporter.log("Verification of the 3.7 public Documents is successful");			
			} else {
				Reporter.log("Verification of the 3.7 public Documents is Unsuccessful");			
			}
		}
	}


	@Test(enabled=true)	 
	public void Test46_Verify_publicDocuments_Uploade_via_FTP() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test46 - User opted to skip this test ");
		} else {		
			TestS_046 clasInst = new TestS_046(driver, log);
			Boolean retVal = clasInst.test46();		
			if (retVal) {
				Reporter.log("Verification of the 3.7 public Documents is successful");			
			} else {
				Reporter.log("Verification of the 3.7 public Documents is Unsuccessful");			
			}
		}
	}


	@Test(enabled=true)	 
	public void Test47_Verify_3_7_SSO_Consent() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test47 - User opted to skip this test ");
		} else {		
			TestS_047 clasInst = new TestS_047(driver, log);
			Boolean retVal = clasInst.test47();		
			if (retVal) {
				Reporter.log("Verification of the 3.7 SSO Consent is successful");			
			} else {
				Reporter.log("Verification of the 3.7 SSO Consent is Unsuccessful");			
			}
		}
	}


	@Test(enabled=true)	 
	public void Test48_Verify_3_7_RegistrationAnd_ConsentPassword_Reset() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test48 - User opted to skip this test ");
		} else {		
			TestS_048 clasInst = new TestS_048(driver, log);
			Boolean retVal = clasInst.test48();		
			if (retVal) {
				Reporter.log("Verification of the 3.7 Registration and Consent Password Reset is successful");			
			} else {
				Reporter.log("Verification of 3.7 Registration and Consent Password Reset is unsuccessful");			
			}
		}
	}


	@Test(enabled=true)	 
	public void Test49_Verify_the_Text_Email_Type_of_Template_Management () throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test49 - User opted to skip this test ");
		} else {		
			TestS_049 clasInst = new TestS_049(driver, log);
			Boolean retVal = clasInst.test49();		
			if (retVal) {
				Reporter.log("Verifying Text Email type templates in Template management is successful");			
			} else {
				Reporter.log("Verifying Text Email type templates in Template management is unsuccessful");			
			}
		}
	}


	@Test(enabled=true)	 
	public void Test50_Verify_the_HTML_Email_Type_of_Template_Management () throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test50 - User opted to skip this test ");
		} else {		
			TestS_050 clasInst = new TestS_050(driver, log);
			Boolean retVal = clasInst.test50();		
			if (retVal) {
				Reporter.log("Verifying HTML Email type templates in Template management is successful");			
			} else {
				Reporter.log("Verifying HTML Email type templates in Template management is unsuccessful");			
			}
		}
	}



	@Test(enabled=true)	 
	public void Test51_Verify_the_Promote_Edit_XMLSchema_Permissions_Of_Email_Type_of_Template_Management() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test51 - User opted to skip this test ");
		} else {		
			TestS_051 clasInst = new TestS_051(driver, log);
			Boolean retVal = clasInst.test51();		
			if (retVal) {
				Reporter.log("Verifying various permissions of Template management is successful");			
			} else {
				Reporter.log("Verifying various permissions of Template management is unsuccessful");			
			}
		}
	}

	@Test(enabled=true)	 
	public void Test52_Verify_the_various_waysto_Access_Template_Management_Tool() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test52 - User opted to skip this test ");
		} else {		
			TestS_052 clasInst = new TestS_052(driver, log);
			Boolean retVal = clasInst.test52();		
			if (retVal) {
				Reporter.log("Verifying the various ways to access the Template management Tool is successful");			
			} else {
				Reporter.log("Verifying the various ways to access the Template management Tool is unsuccessful");			
			}
		}
	}


	@Test(enabled=true)	 
	public void Test53_Verify_the_eDelivery_Field_validation_of_the_TemplateManagement() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test53 - User opted to skip this test ");
		} else {		
			TestS_053 clasInst = new TestS_053(driver, log);
			Boolean retVal = clasInst.test53();		
			if (retVal) {
				Reporter.log("Verifying the eDelivery fields validation of the Template management Tool is successful");			
			} else {
				Reporter.log("Verifying the eDelivery fields validation of the Template management Tool is unsuccessful");			
			}
		}
	}


	@Test(enabled=true)	 
	public void Test54_Verify_the_XML__Schema_of_the_TemplateManagement() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test54 - User opted to skip this test ");
		} else {		
			TestS_054 clasInst = new TestS_054(driver, log);
			Boolean retVal = clasInst.test54();		
			if (retVal) {
				Reporter.log("Verifying the XML schema of the Template management Tool is successful");			
			} else {
				Reporter.log("Verifying the  XML schema of the Template management Tool is unsuccessful");			
			}
		}
	}


	@Test(enabled=true)	 
	public void Test55_Verify_the_PivotSAML__tests() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test55 - User opted to skip this test ");
		} else {		
			TestS_055 clasInst = new TestS_055(driver, log);
			Boolean retVal = clasInst.test55();		
			if (retVal) {
				Reporter.log("Verifying the Pivot SAML tests is successful");			
			} else {
				Reporter.log("Verifying the Pivot SAML tests is unsuccessful");			
			}
		}
	}


	@Test(enabled=true)	 
	public void Test56_Verify_the_HTML5_documents_archivalProcess() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test56 - User opted to skip this test ");
		} else {		
			TestS_056 clasInst = new TestS_056(driver, log);
			Boolean retVal = clasInst.test56();		
			if (retVal) {
				Reporter.log("Verifying the HTML5 document archival process is successful");			
			} else {
				Reporter.log("Verifying the HTML5 document archival process is unsuccessful");			
			}
		}
	}




	//===================Test 0057 Starts======================

	@Test(enabled=true)	 
	public void Test57_Verifying_creation_of_New_Campaign_Manager() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test57 - User opted to skip this test ");
		} else {		
			TestS_057 clasInst = new TestS_057(driver, log);
			Boolean retVal = clasInst.test57();		
			if (retVal) {
				Reporter.log("Verification of New Campaign Manager with all related actions is successful");			
			} else {				
				Reporter.log("Verification of New Campaign Manager with all related actions is unsuccessful");			
			}
		}
	}



	@Test(enabled=true)	 
	public void Test58_Verify_SSO_User_Login_and_Password_reset_Functionality() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test64 - User opted to skip this test ");
		} else {		
			TestS_058 clasInst = new TestS_058(driver, log);
			Boolean retVal = clasInst.test58();		
			if (retVal) {
				Reporter.log("SSO User Login/Password:Verifying SSO User Login and Password reset Functionality is successful");			
			} else {
				Reporter.log("SSO User Login/Password:Verifying SSO User Login and Password reset Functionality is unsuccessful");			
			}
		}

	}

	@Test(enabled=true)
	public void Test59_Verify_Campaign_Manager_AdvanceSearch() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test59 - User opted to skip this test ");
		} else {
			TestS_059 clasInst = new TestS_059(driver, log);
			Boolean retVal = clasInst.test59();
			if (retVal) {
				Reporter.log("Verifying Campaign Manager Advance Search funtionality is successful");
			} else {
				Reporter.log("Verifying Campaign Manager Advance Search funtionality is unsuccessful");
			}
		}
	}

	@Test(enabled=true)
	public void Test60_Verify_Campaign_Manager_ManageRecipient() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test60 - User opted to skip this test ");
		} else {
			TestS_060 clasInst = new TestS_060(driver, log);
			Boolean retVal = clasInst.test60();
			if (retVal) {
				Reporter.log("Verifying Campaign Manager - Manage Recipient funtionality is successful");
			} else {
				Reporter.log("Verifying Campaign Manager - Manage Recipient funtionality is unsuccessful");
			}
		}
	}

	@Test(enabled=true)	 
	public void Test61_Verify_Campaign_Manager_Email_Verification_for_Plain_and_Variable_Text_Template() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test61 - User opted to skip this test ");
		} else {		
			TestS_061 clasInst = new TestS_061(driver, log);
			Boolean retVal = clasInst.test61();		
			if (retVal) {
				Reporter.log("Verification Campaign Manager Email Verification for Plain and Variable Text Template is successful");			
			} else{
				Reporter.log("Verification Campaign Manager Email Verification for Plain and Variable Text Template is unsuccessful");			
			}
		}
	}






	@Test(enabled=true)	 
	public void Test62_Verify_Client_Name_and_Application_Name_remains_same_across_the_searches_in_Jobtracking() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test61 - User opted to skip this test ");
		} else {		
			TestS_062 clasInst = new TestS_062(driver, log);
			Boolean retVal = clasInst.test62();		
			if (retVal) {
				Reporter.log("Verifying the Client Name and Application Name accross different searches in jobtracking is Passed");			
			} else{
				Reporter.log("!!!! Verifying the Client Name and Application Name accross different searches in jobtracking is Failed !!!!");			
			}
		}
	}

	@Test(enabled=true)	 
	public void Test63_Verify_Favorite_Feature_of_the_Portal() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test66 - User opted to skip this test ");
		} else {		
			TestS_063 clasInst = new TestS_063(driver, log);
			Boolean retVal = clasInst.test63();		
			if (retVal) {
				Reporter.log("Verifying the funtionality of Favorite feature of the portal is successful");			
			} else {
				Reporter.log("Verifying the funtionality of Favorite feature of the portal is unsuccessful");			
			}
		}

	}

	@Test(enabled=true)	 
	public void Test64_Verify_eDelivery_Acknowledge_Feature_of_Archives() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test66 - User opted to skip this test ");
		} else {		
			TestS_064 clasInst = new TestS_064(driver, log);
			Boolean retVal = clasInst.test64();		
			if (retVal) {
				Reporter.log("***** Verifying eDelivery Acknowledge Feature of Archives is Passed *****");			
			} else {
				Reporter.log("!!!!! Verifying eDelivery Acknowledge Feature of Archives is Failed !!!!!");			
			}
		}

	}
	
	
	@Test(enabled=true)	 
	public void Test65_Verify_Test_Login_Feature_Using_Different_Users() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test65 - User opted to skip this test ");
		} else {		
			TestS_065 clasInst = new TestS_065(driver, log);
			Boolean retVal = clasInst.test65();		
			if (retVal) {
				Reporter.log("***** Verifying Login Feature Using Different Users is Passed *****");			
			} else {
				Reporter.log("!!!!! Verifying Login Feature Using Different Users is Failed !!!!!");			
			}
		}

	}
	
	
	@Test(enabled=true)	 
	public void Test66_Manageuser_SimpleSearch_NewModule() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test66 - User opted to skip this test ");
		} else {		
			TestS_066 clasInst = new TestS_066(driver, log);
			Boolean retVal = clasInst.test66();		
			if (retVal) {
				Reporter.log("***** Verifying User appname & id advance search passed *****");			
			} else {
				Reporter.log("!!!!!Verifying User appname & id advance search failed !!!!!");			
			}
		}

	}
	
	
	@Test(enabled=true)	 
	public void Test67_Manageuser_AdvanceSearch_NewModule() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test66 - User opted to skip this test ");
		} else {		
			TestS_067 clasInst = new TestS_067(driver, log);
			Boolean retVal = clasInst.test67();		
			if (retVal) {
				Reporter.log("***** Verifying Advance search feature in Manageuser is Passed *****");			
			} else {
				Reporter.log("!!!!! Verifying Advance search feature in Manageuser is failed !!!!!");			
			}
		}

	}
	
	@Test(enabled=true)	 
	public void Test68_Verify_DirecttoDocument_Functionality() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test68 - User opted to skip this test ");
		} else {		
			TestS_068 clasInst = new TestS_068(driver, log);
			Boolean retVal = clasInst.test68();		
			if (retVal) {
				Reporter.log("***** Verifying Direct to Document functionality is passed *****");			
			} else {
				Reporter.log("!!!!!Verifying Direct to Document functionality is failed !!!!!");			
			}
		}

	}
	
	
	@Test(enabled=true)	 
	public void Test69_Verify_Servicetypes_pivotCFLAdmin_Functionality() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test69 - User opted to skip this test ");
		} else {		
			TestS_069 clasInst = new TestS_069(driver, log);
			Boolean retVal = clasInst.test69();		
			if (retVal) {
				Reporter.log("***** Verifying Edit service types via Pivot CFL Adminis Passed *****");			
			} else {
				Reporter.log("!!!!! Verifying Edit service types via Pivot CFL Admin is Failed !!!!!");			
			}
		}

	}
	
	@Test(enabled=true)	 
	public void Test70_Verify_webtemplates_functionality() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test70 - User opted to skip this test ");
		} else {		
			TestS_070 clasInst = new TestS_070(driver, log);
			Boolean retVal = clasInst.test70();		
			if (retVal) {
				Reporter.log("***** Verify link id duplicity in Web Templates Passed *****");			
			} else {
				Reporter.log("!!!!! Verify link id duplicity in Web Templates Failed !!!!!");			
			}
		}

	}
	
	@Test(enabled=true)	 
	public void Test71_Verify_Emailtype_activity_and_Compare_emailtype_list_functionality() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test70 - User opted to skip this test ");
		} else {		
			TestS_071 clasInst = new TestS_071(driver, log);
			Boolean retVal = clasInst.test71();		
			if (retVal) {
				Reporter.log("***** Compare email type list in admin & edeliver activity is Passed *****");			
			} else {
				Reporter.log("!!!!! Failed to Compare email type list in admin & edeliver activity !!!!!");			
			}
		}

	}
	
	@Test(enabled=true)	 
	public void Test72_Verify_Systmhealth_functionalities_NewHA_Admin() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test71 - User opted to skip this test ");
		} else {		
			TestS_072 clasInst = new TestS_072(driver, log);
			Boolean retVal = clasInst.test72();		
			if (retVal) {
				Reporter.log("***** Compare email type list in admin & edeliver activity is Passed *****");			
			} else {
				Reporter.log("!!!!! Failed to Compare email type list in admin & edeliver activity !!!!!");			
			}
		}

	}
	
	@Test(enabled=true)	 
	public void Test73_Verify_Proofs_Compareproofs() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test73 - User opted to skip this test");
		} else {		
			TestS_073 clasInst = new TestS_073(driver, log);
			Boolean retVal = clasInst.test73();		
			if (retVal) {
				Reporter.log("***** Proofs -Comparing a pdf proof file is Passed *****");			
			} else {
				Reporter.log("!!!!! Proofs - Comparing a pdf proof file is Failed !!!!!");			
			}
		}

	}
	
	@Test(enabled=true)	 
	public void Test74_Archive_ConsentUsers_Search_History_request() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test74 - User opted to skip this test");
		} else {		
			TestS_074 clasInst = new TestS_074(driver, log);
			Boolean retVal = clasInst.test74();		
			if (retVal) {
				Reporter.log("***** Aricheve & skyblue history data is Passed *****");			
			} else {
				Reporter.log("!!!!!Aricheve & skyblue history data is Failed !!!!!");			
			}
		}

	}
	
	@Test(enabled=true)	 
	public void Test75_Proofs_WorkFlow_Functionality() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test75 - User opted to skip this test ");
		} else {		
			TestS_075 clasInst = new TestS_075(driver, log);
			Boolean retVal = clasInst.test75();		
			if (retVal) {
				Reporter.log("***** Verifying Proofs WorkFlow functionality is passed *****");			
			} else {
				Reporter.log("!!!!! Verifying Proofs WorkFlow functionality is Failed !!!!!");			
			}
		}
	}
	
	
	@Test(enabled=true)	 
	public void Test76_Modules_Workflow_Functionality_Under_HAAdmin() throws Exception {
		if (!testval){
			throw new SkipException("Skipping Test76 - User opted to skip this test ");
		} else {		
			TestS_076 clasInst = new TestS_076(driver, log);
			Boolean retVal = clasInst.test76();		
			if (retVal) {
				Reporter.log("***** Validations of Notification template changes in workflow module is passed *****");			
			} else {
				Reporter.log("!!!!! Validations of Notification template changes in workflow module is Failed !!!!!");			
			}
		}
	}
	
	@Test(enabled=true)     
    public void Test77_Audits_WorkFlow_Functionality() throws Exception {
        if (!testval){
            throw new SkipException("Skipping Test77 - User opted to skip this test ");
        } else {        
            TestS_077 clasInst = new TestS_077(driver, log);
            Boolean retVal = clasInst.test77();        
            if (retVal) {
                Reporter.log("***** Verifying Audits WorkFlow functionality is passed *****");            
            } else {
                Reporter.log("!!!!! Verifying Audits WorkFlow functionality is Failed !!!!!");            
            }
        }
    }
		

	
	//================SSO Scripts======================

	@Test(enabled=true)	 
	public void Test80_Verify_SSOscript_PDFfileOpened_whenClicking_on_Submit_withLatest_DateAndTime() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test80 - User opted to skip this test ");
		} else {		
			TestS_080 clasInst = new TestS_080(driver, log);
			Boolean retVal = clasInst.test80();		
			if (retVal) {
				Reporter.log("SSO Script-1:Verifying the PDF file opened when clicking on Submit button with latest date and time is successful");			
			} else {
				Reporter.log("SSO Script-1:Verifying the PDF file opened when clicking on Submit button with latest date and time is unsuccessful");			
			}
		}
	}

	
	@Test(enabled=true)	 
	public void Test81_Verify_SSOscript_XMLRenamed_From_aspx_AfterClicking_on_Submit_withLatest_DateAndTime() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test81 - User opted to skip this test ");
		} else {		
			TestS_081 clasInst = new TestS_081(driver, log);
			Boolean retVal = clasInst.test81();		
			if (retVal) {
				Reporter.log("SSO Script-2:Verifying the XML file renamed from aspx after clicking on Submit button with latest date and time is successful");			
			} else {
				Reporter.log("SSO Script-2:Verifying the XML file renamed from aspx after clicking on Submit button with latest date and time is unsuccessful");			
			}
		}
	}


	@Test(enabled=true)	 
	public void Test82_Verify_SSOscript_PDFfileOpened_whenClicking_onViewPDFLink_AfterClicking_on_Submit_withLatest_DateAndTime() throws Exception {	
		if (!testval){
			throw new SkipException("Skipping Test82 - User opted to skip this test ");
		} else {		
			TestS_082 clasInst = new TestS_082(driver, log);
			Boolean retVal = clasInst.test82();		
			if (retVal) {
				Reporter.log("SSO Script-3:Verifying the PDF file opened when clicking on View PDF link after clicking Submit button with latest date and time is successful");			
			} else {
				Reporter.log("SSO Script-3:Verifying the PDF file opened when clicking on View PDF link after clicking Submit button with latest date and time is unsuccessful");			
			}
		}

	}



}


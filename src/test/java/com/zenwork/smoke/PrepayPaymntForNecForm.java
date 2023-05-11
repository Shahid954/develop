package com.zenwork.smoke;

import java.io.File;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.zenwork.library.AppLibrary;
import com.zenwork.library.TestBase;
import com.zenwork.pageObject.AddPayerForm;
import com.zenwork.pageObject.AddRecipientPage;
import com.zenwork.pageObject.DashboardPage;
import com.zenwork.pageObject.FilingHistory;
import com.zenwork.pageObject.Forms;
import com.zenwork.pageObject.HomePage;
import com.zenwork.pageObject.LoginPage;
import com.zenwork.pageObject.ManageFormPage;
import com.zenwork.pageObject.NECFormSingleFiling;
import com.zenwork.pageObject.NecSubmitPayersPage;
import com.zenwork.pageObject.NewForm;
import com.zenwork.pageObject.PaymentPage;
import com.zenwork.pageObject.SideNavigation;
import com.zenwork.pageObject.SubmissionConfirmationPage;

public class PrepayPaymntForNecForm extends TestBase {

	@DataProvider(name = "PrepayNecData")
	public Object[][] getData() throws Exception {
		String filePath = "Resources" + File.separator + "BusinessTypeAddRecipient.xls";
		Object object[][] = AppLibrary.readExcel(filePath, 0);
		return object;
	}

	@BeforeClass
	public void setUp() {
		appLibrary = new AppLibrary("PrepayPaymntForNecForm");
	}

	@Test(dataProvider = "PrepayNecData")
	public void CardPaymentMiscFormValidation(String businessName, String address, String city, String state,
			String zipCode, String country, String phone, String email, String exeIndicator) throws Exception {
		String payerEinNum = AppLibrary.generateRandomNumber(9);
		String recipientEinNum = AppLibrary.generateRandomNumber(9);
		String payerBusinessName = businessName + " " + AppLibrary.getFormattedDate().replace("_", "");
		String recipientBusinessName = businessName + " " + AppLibrary.getFormattedDate().replace("_", "");
		String disregardedEntity = AppLibrary.generateRandomNumber(9);
		String payerAddress = AppLibrary.randIntDigits(1111, 9999) + " " + address;
		String recipientAddress = AppLibrary.randIntDigits(1111, 9999) + " " + address;
		String payerPhoneNumber = phone + AppLibrary.generateRandomNumber(8);

		appLibrary.getDriverInstance();
		appLibrary.launchApp();// launching the application in browser
		HomePage ap = new HomePage(appLibrary);
		DashboardPage dp = new DashboardPage(appLibrary);
		SideNavigation sn = new SideNavigation(appLibrary);
		ap.verifyHomePageUi();
//		ap.acceptCookies();
		LoginPage lp = ap.navigateToLoginPage();
		lp.verifyLoginPageUi();
		lp.login(getUserID(), getPassword());
		dp.plansPopupCancel();
		dp.verifyDashboardPage();
		sn.verifySideNavigationUi();
		Forms f = sn.selectForms();
		f.verifyFormsOptionsUi();
		NewForm nf = f.selectNewForm();
		nf.selectYear();
		NECFormSingleFiling nef = nf.selectNecForm();
		nef.verifyNecFormPageUi();
		nef.addNoneEmployeeCompensation("1000");
		nef.addfederalIncomeTax("200");
		AddPayerForm apf = nef.addNewPayer();
		apf.verifyAddPayerPageUi();
		apf.fillAddPayerForm(payerEinNum, payerBusinessName, disregardedEntity, payerAddress, city, state, zipCode,
				country, payerPhoneNumber);
		apf.addPayerDetails();
		apf.verifyAlertMessage();
		apf.alertifyOk();
		AddRecipientPage arp = nef.addNewRecipient();
		arp.fillAddRecipientForm(recipientEinNum, recipientBusinessName, recipientAddress, city, state, zipCode,
				country, email);
		arp.addRecipientDetails();
		arp.VerifyRecipientConfirmation();
		NecSubmitPayersPage spp = nef.saveAndContinueNec();
		spp.verifysubmitPayersPageUi();
		spp.selectAllDetails();
		PaymentPage pp = spp.submitForThisPayer();
		pp.fillAddressDetails(payerAddress, city, zipCode, state);
		SubmissionConfirmationPage scp = pp.submitPayment();
		scp.verifyConfirmationPageUi();
		FilingHistory fh = scp.navigateToFilingHistory();
		fh.verifyFilingHistoryPageUi();
		String refNoFilingHistory = fh.getMessage();
		ManageFormPage mfp = fh.selectLatestRecord();
		mfp.verifyManageFormPageUi();
		String refNoManageForm = mfp.verifyLatestRecord();
		appLibrary.verificationwithtwoStrings(driver, refNoFilingHistory, refNoManageForm);

	}
}

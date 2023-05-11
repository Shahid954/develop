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
import com.zenwork.pageObject.Forms;
import com.zenwork.pageObject.HomePage;
import com.zenwork.pageObject.LoginPage;
import com.zenwork.pageObject.ManageFormPage;
import com.zenwork.pageObject.MiscFormSingleFiling;
import com.zenwork.pageObject.MiscSubmitPayersPage;
import com.zenwork.pageObject.NewForm;
import com.zenwork.pageObject.PaymentPage;
import com.zenwork.pageObject.SideNavigation;
import com.zenwork.pageObject.SubmissionConfirmationPage;

public class ValidateSendPdfPortalForSingleRecipient extends TestBase {

	public static String payerBusinessName;

	@DataProvider(name = "payerAndRecipientData")
	public Object[][] getData() throws Exception {
		String filePath = "Resources" + File.separator + "BusinessTypeAddRecipient.xls";
		Object object[][] = AppLibrary.readExcel(filePath, 0);
		return object;
	}

	@BeforeClass
	public void setUp() throws Exception {
		appLibrary = new AppLibrary("ValidateUspsMailingMultipleRecipient");

	}

	@Test(dataProvider = "payerAndRecipientData")
	public void createPayerAndRecipient(String businessName, String address, String city, String state, String zipCode,
			String country, String phone, String email, String exeIndicator) throws Exception {
		String payerEinNum = AppLibrary.generateRandomNumber(9);
		String recipientEinNum = AppLibrary.generateRandomNumber(9);
		payerBusinessName = businessName + " " + AppLibrary.getFormattedDate().replace("_", "");
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
		LoginPage lp = ap.navigateToLoginPage();
		lp.login(getUserID(), getPassword());
		dp.plansPopupCancel();
		Forms f = sn.selectForms();
		NewForm nf = f.selectNewForm();
		nf.selectYear();
		MiscFormSingleFiling mef = nf.selectMiscForm();
		mef.enterRent("1000");
		mef.addfederalIncomeTax("200");
		AddPayerForm apf = mef.addNewPayer();
		apf.fillAddPayerForm(payerEinNum, payerBusinessName, disregardedEntity, payerAddress, city, state, zipCode,
				country, payerPhoneNumber);
		apf.addPayerDetails();
		apf.alertifyOk();
		AddRecipientPage arp = mef.addNewRecipient();
		arp.fillAddRecipientForm(recipientEinNum, recipientBusinessName, recipientAddress, city, state, zipCode,
				country, email);
		arp.addRecipientDetails();
		arp.VerifyRecipientConfirmation();
		MiscSubmitPayersPage msp = mef.saveAndContinueMisc();
		msp.selectAllDetails();
		PaymentPage pp = msp.submitForThisPayer();
		pp.fillAddressDetails(payerAddress, city, zipCode, state);
		SubmissionConfirmationPage scp = pp.submitPayment();
		scp.verifyConfirmationPageUi();
		sn.selectForms();
		ManageFormPage mf = f.selectManageForm();
		mf.manageFormPayerDropdown(payerBusinessName);
		mf.manageFormTaxYearDropdown("2022");
		mf.sendPdfToPortalSingleRecipient();
	}

}

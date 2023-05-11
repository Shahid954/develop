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
import com.zenwork.pageObject.NECFormSingleFiling;
import com.zenwork.pageObject.NecSubmitPayersPage;
import com.zenwork.pageObject.NewForm;
import com.zenwork.pageObject.PaymentPage;
import com.zenwork.pageObject.SideNavigation;
import com.zenwork.pageObject.SubmissionConfirmationPage;

public class ValidateSendPdfPortalForMultipleRecipient extends TestBase {

	public static String payerBusinessName;

	@BeforeClass
	public void setUp() {
		appLibrary = new AppLibrary("ValidateSendPdfPortalForMultipleRecipient");
	}

	@DataProvider(name = "AddPayerData")
	public Object[][] getPayerData() throws Exception {
		String filePath = "Resources" + File.separator + "AddNewPayerData.xls";
		Object object[][] = AppLibrary.readExcel(filePath, 0);
		return object;
	}

	@DataProvider(name = "AddRecipientData")
	public Object[][] getRecipientData() throws Exception {
		String filePath = "Resources" + File.separator + "addMultiRecipients.xls";
		Object object[][] = AppLibrary.readExcel(filePath, 0);
		return object;
	}

	@Test(dataProvider = "AddPayerData")
	public void addPayer(String businessName, String address, String city, String state, String zipCode, String country,
			String phone, String exeIndicator) throws Exception {
		String payerEinNum = AppLibrary.generateRandomNumber(9);
		payerBusinessName = businessName + " " + AppLibrary.getFormattedDate().replace("_", "");
		String disregardedEntity = AppLibrary.generateRandomNumber(9);
		String payerAddress = AppLibrary.randIntDigits(1111, 9999) + " " + address;
		String payerPhoneNumber = phone + AppLibrary.generateRandomNumber(8);
//		String recipientAddress = AppLibrary.randIntDigits(1111, 9999) + " " + address;

		appLibrary.getDriverInstance();
		appLibrary.launchApp();// launching the application in browser
		HomePage ap = new HomePage(appLibrary);
		DashboardPage dp = new DashboardPage(appLibrary);
		SideNavigation sn = new SideNavigation(appLibrary);
		LoginPage lp = ap.navigateToLoginPage();
		lp.login(getUserID(), getPassword());
		dp.plansPopupCancel();
		Forms f = sn.selectForms();
		NewForm fr = f.selectNewForm();
		fr.selectYear();
		NECFormSingleFiling nc = fr.selectNecForm();
		AddPayerForm anp = nc.addNewPayer();
		anp.fillAddPayerForm(payerEinNum, payerBusinessName, disregardedEntity, payerAddress, city, state, zipCode,
				country, payerPhoneNumber);
		anp.addPayerDetails();
		anp.verifyAlertMessage();
		anp.alertifyOk();
	}

	@Test(dataProvider = "AddRecipientData", dependsOnMethods = "addPayer")
	public void addMultipleRecipients(String businessName, String address, String city, String state, String zipCode,
			String country, String email, String amount, String federalAmount, String noOfRecipient,
			String exeIndicator) throws Exception {

		if (exeIndicator.equalsIgnoreCase("Yes")) {

			appLibrary.getDriverInstance();
			appLibrary.launchApp();// launching the application in browser
			HomePage ap = new HomePage(appLibrary);
			DashboardPage dp = new DashboardPage(appLibrary);
			SideNavigation sn = new SideNavigation(appLibrary);
			LoginPage lp = ap.navigateToLoginPage();
			lp.login(getUserID(), getPassword());
			dp.plansPopupCancel();
			Forms f = sn.selectForms();
			NewForm fr = f.selectNewForm();
			fr.selectYear();
			NECFormSingleFiling nc = fr.selectNecForm();
			nc.addPayerFromDropdown(payerBusinessName);
			nc.addMultipleRecipient(businessName, address, city, state, zipCode, country, email, amount, federalAmount,
					noOfRecipient);
			NecSubmitPayersPage nsp = nc.saveAndContinueNec();
			nsp.selectAllDetails();
			PaymentPage pp = nsp.submitForThisPayer();
			pp.fillAddressDetails(address, city, zipCode, state);
			SubmissionConfirmationPage scp = pp.submitPayment();
			scp.verifyConfirmationPageUi();
			sn.selectForms();
			ManageFormPage mf = f.selectManageForm();
			mf.manageFormPayerDropdown(payerBusinessName);
			mf.manageFormTaxYearDropdown("2022");
			mf.multipleSendPdfPortal();
		}
	}

}